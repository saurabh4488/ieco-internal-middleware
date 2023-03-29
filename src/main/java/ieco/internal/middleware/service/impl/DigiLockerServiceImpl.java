package ieco.internal.middleware.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectWriter;
import ieco.internal.middleware.domain.request.DgCustomerReqVO;
import ieco.internal.middleware.domain.response.*;
import ieco.internal.middleware.enums.ResponseCodeEnum;
import ieco.internal.middleware.feignclient.DigiLockerClient;
import ieco.internal.middleware.model.DigiLockerClientDetails;
import ieco.internal.middleware.model.DigiLockerCustomers;
import ieco.internal.middleware.repository.DigiLockerClientRepository;
import ieco.internal.middleware.repository.DigiLockerCustomerRepository;
import ieco.internal.middleware.service.DigiLockerService;
import ieco.internal.middleware.util.JAXBUtil;
import ieco.internal.middleware.util.PDFGenarator;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import feign.FeignException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

@Service
public class DigiLockerServiceImpl extends AbstractResponse implements DigiLockerService {

    private static final int OUTPUT_BYTE_ARRAY_INITIAL_SIZE = 4096;


    private Logger log = LoggerFactory.getLogger(DigiLockerServiceImpl.class);
    @Value("${digilocker.granttype}")
    private String dgGrantType;

    @Value("${digilocker.baseurl}")
    private String dgBaseURL;
    @Autowired
    private DigiLockerClientRepository dgClientRepository;
    @Autowired
    private DigiLockerCustomerRepository dgCustRepository;
    @RestClient
    private DigiLockerClient dgClient;
    @Autowired
    private ObjectWriter objetWriter;

    @Override
    public ResponseObject retrieveAuthorizationUrl(String clientId, String redirectUR) {
        log.info("retrieveAuthorizationUrl request with clientid: {} and redirectUR:{}", clientId, redirectUR);
        List<String> mapKeyList = new ArrayList<String>(0);
        List<Object> attrList = new ArrayList<Object>(0);
        HttpHeaders headers = null;
        try {
            Optional<DigiLockerClientDetails> dgClientLookup = dgClientRepository.findByClientId(clientId);
            log.info("dgClientLookup {}" + dgClientLookup);
            if (dgClientLookup.isPresent() && dgClientLookup.get().getClientId().equals(clientId)) {
                DigiLockerClientDetails digiLockerClientDetails = dgClientLookup.get();
                log.info("digiLockerClientDetails {}" + digiLockerClientDetails);

                String url = dgBaseURL + "/1/authorize?response_type=code&client_id=" + digiLockerClientDetails.getDgClientId() + "&redirect_uri="
                        + digiLockerClientDetails.getRedirectURL() + "&state=" + digiLockerClientDetails.getState();
                log.info("dgurl{}",url);

                mapKeyList.addAll(Arrays.asList("authorizationEndpoint"));
                attrList = Arrays.asList(url);
                return responseEntityMultipleDataCookieSuccess("", ResponseCodeEnum.DIGILOCKER_DATA_FETCHED_SUCCESSFULLY,
                        attrList, mapKeyList);
            }
            return responseError("Invalid Client Id!", ResponseCodeEnum.DIGILOCKER_DATA_FETCH_FAILED);
        } catch (Exception e) {
            log.error("Error Occurred in retrieveAuthorizationUrl : {}",e.getLocalizedMessage());
        }
        return null;


    }

    @Override
    public ResponseObject getCustomerData(DgCustomerReqVO req) {
        log.info("dg customer req {}", req);
        AadhaarResponseVO aadhaarRes = null;


        try {
            Optional<DigiLockerClientDetails> dgClientLookup = dgClientRepository.findByClientId(req.getClientId());
            if (dgClientLookup.isPresent() && dgClientLookup.get().getClientId().equals(req.getClientId())) {

                ResponseEntity<DigiLockerAccessTokenRes> resEntity = fetchAccessToken(req.getCode(), dgClientLookup.get());

                if (resEntity.getStatusCodeValue() == 200) {


                    DigiLockerAccessTokenRes tokenRes = resEntity.getBody();
                    log.info("accessToken res {}", tokenRes);

                    if (tokenRes.getEaadhaar().equalsIgnoreCase("Y")) {
                        final String isAadhaarDataReceived;
                        final String isAadhaarPdfReceived;
                        aadhaarRes = getAadhaarXML("Bearer " + tokenRes.getAccess_token());
                        log.info("aadhaar received for the customerId: {} and aadhaar res:{}", req.getIecoID(), aadhaarRes);
                        if (aadhaarRes != null) {
                            isAadhaarDataReceived = "Y";
                            String aadhaarDoc = generatePDF(aadhaarRes, req.getIecoID());
                            isAadhaarPdfReceived = aadhaarDoc != null ? "Y" : "N";
                            aadhaarRes.getAadhaarDataObject().setDocument(aadhaarDoc);
                            String aadharXML = aadhaarRes.getAadhaarDataObject().getAadhaarXML();
                            List<String> mapKeyList = new ArrayList<String>();
                            List<Object> attrList = new ArrayList<Object>();
                            mapKeyList.add("aadhaarData");
                            attrList.add(aadhaarRes.getAadhaarDataObject());
                            mapKeyList.add("aadhaarXmlFile");
                            String aadhaarXmlFileBase64 = Base64.getEncoder().encodeToString(aadharXML.getBytes(StandardCharsets.UTF_8));
                            attrList.add(aadhaarXmlFileBase64);
                            //TODO:
                            mapKeyList.add("xmlGeneratedDate");
                            attrList.add(aadhaarRes.getXmlGeneratedDate());
                            ResponseObject res = responseEntityMultipleDataCookieSuccess("Data fetched successfully!",
                                    ResponseCodeEnum.DIGILOCKER_DATA_FETCHED_SUCCESSFULLY, attrList, mapKeyList);

                            CompletableFuture.runAsync(() -> {
                                try {
                                    saveInDb(req.getIecoID(), tokenRes, isAadhaarDataReceived, isAadhaarPdfReceived, "N", "N", getStringFromJSON(res),
                                            aadharXML, aadhaarDoc, req.isCelusion());
                                } catch (InvocationTargetException e) {
                                    throw new RuntimeException(e);
                                } catch (IllegalAccessException e) {
                                    throw new RuntimeException(e);
                                }
                            });


                            return res;
                        }

                    } else {
                        log.info("Aadhaar not presented in DigiLocker for the customer {}", req.getIecoID());
                        ResponseObject res = responseError("Aadhaar not found in the Digi Locker", ResponseCodeEnum.DIGILOCKER_DATA_FETCH_FAILED);
                        CompletableFuture.runAsync(() -> {
                            try {
                                saveInDb(req.getIecoID(), tokenRes, "N", "N", "N", "N", getStringFromJSON(res));
                            } catch (InvocationTargetException e) {
                                throw new RuntimeException(e);
                            } catch (IllegalAccessException e) {
                                throw new RuntimeException(e);
                            }
                        });


                        return res;
                    }

                }
                log.info("invalid code {}", req.getIecoID());
                return responseError("Invalid code!", ResponseCodeEnum.DIGILOCKER_DATA_FETCH_FAILED);
            }
            log.info("invalid Client Id {}", req.getClientId());
            return responseError("Invalid Client Id!", ResponseCodeEnum.DIGILOCKER_DATA_FETCH_FAILED);


        } catch (FeignException e) {
            log.error("ResponseBody in feign exception: " + e.contentUTF8());
            log.error("feign status {}", e.status());
            return responseError("Digilocker failure!", ResponseCodeEnum.DIGILOCKER_DATA_FETCH_FAILED);
        } catch (Exception e) {
            log.error("error in getCustomerData {}",e.getLocalizedMessage());
            log.error("for customer {}", req.getIecoID());
            return responseError("Technical failure!", ResponseCodeEnum.DIGILOCKER_DATA_FETCH_FAILED);


        }
    }

    String getStringFromJSON(ResponseObject res) {
        try {
            return objetWriter.writeValueAsString(res);
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "";
    }

    private void saveInDb(String iecoID, DigiLockerAccessTokenRes tokenRes, String isAadhaarDataReceived,
                          String isAadhaarPdfReceived, String isPANDataReceived, String isPANimageReceived, String finalJSON,
                          String aadhaarXML, String aadhaarDoc, boolean isCelusion) throws InvocationTargetException, IllegalAccessException {
        Optional<DigiLockerCustomers> dgCust = dgCustRepository.findByCustomerId(iecoID);
        if (dgCust.isPresent()) {
            DigiLockerCustomers dgCustomer = dgCust.get();
            dgCustomer.setAccess_token(tokenRes.getAccess_token());
            dgCustomer.setRefresh_token(tokenRes.getRefresh_token());
            dgCustomer.setModifiedOn(new Date());
            dgCustomer.setIsAadhaarDataReceived(isAadhaarDataReceived);
            dgCustomer.setIsAadhaarPDFReceived(isAadhaarPdfReceived);
            dgCustomer.setIsPANDataReceived(isPANDataReceived);
            dgCustomer.setIsPANImageReceived(isPANimageReceived);
            dgCustomer.setFinalRes(finalJSON);
            dgCustomer.setAadhaarXML(aadhaarXML);
            dgCustomer.setAadhaarDoc(aadhaarDoc);
            dgCustomer.setIsCelusion(isCelusion ? "Y" : "N");
            dgCustRepository.save(dgCustomer);
        } else {
            DigiLockerCustomers dgCustomer = new DigiLockerCustomers();
            BeanUtils.copyProperties(tokenRes, dgCustomer);
            dgCustomer.setCustomerId(iecoID);
            dgCustomer.setCreatedOn(new Date());
            dgCustomer.setIsAadhaarDataReceived(isAadhaarDataReceived);
            dgCustomer.setIsAadhaarPDFReceived(isAadhaarPdfReceived);
            dgCustomer.setIsPANDataReceived(isPANDataReceived);
            dgCustomer.setIsPANImageReceived(isPANimageReceived);
            dgCustomer.setFinalRes(finalJSON);
            dgCustomer.setAadhaarXML(aadhaarXML);
            dgCustomer.setAadhaarDoc(aadhaarDoc);
            dgCustomer.setIsCelusion(isCelusion ? "Y" : "N");
            dgCustRepository.save(dgCustomer);
        }

    }

    private void saveInDb(String iecoID, DigiLockerAccessTokenRes tokenRes, String isAadhaarDataReceived,
                          String isAadhaarPdfReceived, String isPANDataReceived, String isPANimageReceived, String finalJSON) throws InvocationTargetException, IllegalAccessException {
        Optional<DigiLockerCustomers> dgCust = dgCustRepository.findByCustomerId(iecoID);
        if (dgCust.isPresent()) {
            DigiLockerCustomers dgCustomer = dgCust.get();
            dgCustomer.setAccess_token(tokenRes.getAccess_token());
            dgCustomer.setRefresh_token(tokenRes.getRefresh_token());
            dgCustomer.setModifiedOn(new Date());
            dgCustomer.setIsAadhaarDataReceived(isAadhaarDataReceived);
            dgCustomer.setIsAadhaarPDFReceived(isAadhaarPdfReceived);
            dgCustomer.setIsPANDataReceived(isPANDataReceived);
            dgCustomer.setIsPANImageReceived(isPANimageReceived);
            dgCustomer.setFinalRes(finalJSON);
            dgCustRepository.save(dgCustomer);
        } else {
            DigiLockerCustomers dgCustomer = new DigiLockerCustomers();
            BeanUtils.copyProperties(tokenRes, dgCustomer);
            dgCustomer.setCustomerId(iecoID);
            dgCustomer.setCreatedOn(new Date());
            dgCustomer.setIsAadhaarDataReceived(isAadhaarDataReceived);
            dgCustomer.setIsAadhaarPDFReceived(isAadhaarPdfReceived);
            dgCustomer.setIsPANDataReceived(isPANDataReceived);
            dgCustomer.setIsPANImageReceived(isPANimageReceived);
            dgCustomer.setFinalRes(finalJSON);
            dgCustRepository.save(dgCustomer);
        }

    }

    ResponseEntity<DigiLockerAccessTokenRes> fetchAccessToken(String code, DigiLockerClientDetails digiClientDetails) {

        MultiValueMap<String, Object> requestMap = new LinkedMultiValueMap<String, Object>();

        requestMap.add("code", code);
        requestMap.add("grant_type", dgGrantType);
        requestMap.add("client_id", digiClientDetails.getDgClientId());
        requestMap.add("client_secret", digiClientDetails.getDgClientSecret());
        requestMap.add("redirect_uri", digiClientDetails.getRedirectURL());
        log.info("dg token req {}", Arrays.asList(requestMap));
        return dgClient.fetchAccessToken(requestMap);
        }

    private AadhaarResponseVO getAadhaarXML(String accessToken) {

        AadhaarResponseVO resVO = null;
        try {
            OkHttpClient client = new OkHttpClient.Builder().build();
            Request request = new Request.Builder()
                    .url(dgBaseURL+"/3/xml/eaadhaar")
                    .get()
                    .addHeader("Authorization", accessToken)
                    .build();
            Response response = client.newCall(request).execute();

            if (response.code() == 200) {
                String aadharResponse = response.body().string();
                log.info("Aadhaar XML {}", aadharResponse);
                DigiLockerAadhaarRes aadhaarApiRes = JAXBUtil.unmarshall(aadharResponse, DigiLockerAadhaarRes.class);
                log.info("aadhaarApiRes {}", aadhaarApiRes);

                DigiLockerAadhaarRes.CertificateData.KycRes kycRes = aadhaarApiRes.getCertificateData().getKycRes();
                DigiLockerAadhaarRes.CertificateData.KycRes.UidData uidData = aadhaarApiRes.getCertificateData().getKycRes()
                        .getUidData();
                DigiLockerAadhaarRes.CertificateData.KycRes.UidData.POA poaData = aadhaarApiRes.getCertificateData()
                        .getKycRes().getUidData().getPoa();
                DigiLockerAadhaarRes.CertificateData.KycRes.UidData.POI poiData = aadhaarApiRes.getCertificateData()
                        .getKycRes().getUidData().getPoi();
                String address = poaData.getHouse() + "," + poaData.getStreet() + "," + poaData.getLandmark() + "," + poaData.getLoc() + "," + poaData.getVtc() + "," + poaData.getDist() + ","
                        + poaData.getState() + "," + poaData.getCountry() + "-" + poaData.getPc();

                //TODO:
                String xmlGeneratedDate=null;
                String xmlGeneratedDateNewFormat=null;
                if(aadhaarApiRes.getCertificateData().getKycRes().getTs()!=null) {
                    xmlGeneratedDate = aadhaarApiRes.getCertificateData().getKycRes().getTs();

                }
                log.info("The xml generated date before modify {}",xmlGeneratedDate);

                if(xmlGeneratedDate!=null) {
                    xmlGeneratedDateNewFormat = modifyXmlDate(xmlGeneratedDate);
                }
                log.info("The xml generated date after modify {}",xmlGeneratedDateNewFormat);

                return AadhaarResponseVO.builder().xmlGeneratedDate(xmlGeneratedDateNewFormat).AadhaarDataObject(AadhaarResponseVO.AadhaarData.builder()
                        .aadhaarNumber(uidData.getUid()).dob(poiData.getDob()).gender(poiData.getGender())
                        .name(poiData.getName()).photo(uidData.getPht()).address(address.replace("null,", "")).timeStamp(kycRes.getTs()).txn(kycRes.getTxn()).aadhaarXML(aadharResponse).PoaObject(AadhaarResponseVO.AadhaarData.Poa.builder()
                                .careof(poaData.getCo()).country(poaData.getCountry()).dist(poaData.getDist()).loc(poaData.getLoc())
                                .pc(poaData.getPc()).state(poaData.getState()).vtc(poaData.getVtc()).house(poaData.getHouse()).street(poaData.getStreet()).landmark(poaData.getLandmark()).build()).build()).build();


            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            log.error("error in get aadhaar xml {}",e.getLocalizedMessage());
        }
        return resVO;
    }



    private String modifyXmlDate(String xmlDate) throws ParseException {
        final String OLD_FORMAT = "yyyy-MM-dd";
        final String NEW_FORMAT = "dd-MM-yy";
        String oldDateString = xmlDate;
        String newDateString;

        SimpleDateFormat sdf = new SimpleDateFormat(OLD_FORMAT);
        Date d = sdf.parse(oldDateString);
        sdf.applyPattern(NEW_FORMAT);
        newDateString = sdf.format(d);

        log.info("xmlDate new date is {}", newDateString);
        return newDateString;
    }


    boolean clientIdValidation(String clientId) {
        Optional<DigiLockerClientDetails> dgLockerClientDetails = dgClientRepository.findByClientId(clientId);
        if (dgLockerClientDetails.isPresent() && dgLockerClientDetails.get().getClientId().equals(clientId)) {
            return true;
        }

        return false;
    }

    public String convertPDFtoImage(byte[] bytesPDF) {

        log.info("PDF to JPEG conversion started.......");
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PDDocument document = null;
        try {
            document = PDDocument.load(new ByteArrayInputStream(bytesPDF));
            PDFRenderer renderer = new PDFRenderer(document);
            int pageNumber = 0;
            BufferedImage bi = renderer.renderImageWithDPI(pageNumber, 300);
            ImageIO.write(bi, "jpeg", baos);
            baos.flush();
        } catch (Exception e) {
            log.error("error in pdf to image conversion {}",e.getLocalizedMessage());
        } finally {
            if (document != null) {
                try {
                    document.close();
                    baos.close();
                    log.info("End convert PDF to Images process");
                } catch (IOException e) {
                    log.error("error in pdf to image conversion {}",e.getLocalizedMessage());

                }
            }
        }
        return Base64.getEncoder().encodeToString(baos.toByteArray());
    }

    public String removeSignature(String pdf) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream(OUTPUT_BYTE_ARRAY_INITIAL_SIZE);
            PdfReader reader = new PdfReader(Base64.getDecoder().decode(pdf.getBytes()));

            AcroFields acroFields = reader.getAcroFields();
            acroFields.removeField("Signature1");
            PdfStamper stamper = new PdfStamper(reader, baos);
            stamper.close();
            reader.close();
            return Base64.getEncoder().encodeToString(baos.toByteArray());
        } catch (Exception e) {
            log.error("Exception in remove signature {}",e.getLocalizedMessage());
        }
        return null;
    }

    String generatePDF(AadhaarResponseVO aadhaarRes, String customerId) {

        String gender = aadhaarRes.getAadhaarDataObject().getGender();
        if (gender.equals("M")) {
            gender = "MALE";
        } else if (gender.equals("F")) {
            gender = "FEMALE";
        } else {
            gender = "TRANSGENDER";
        }

        Map<String, String> pdfdata = new HashMap<>();
        pdfdata.put("Reference Id:", aadhaarRes.getAadhaarDataObject().getTxn());
        pdfdata.put("Photo", aadhaarRes.getAadhaarDataObject().getPhoto());
        pdfdata.put("Aadhaar Number", aadhaarRes.getAadhaarDataObject().getAadhaarNumber());
        pdfdata.put("Name", aadhaarRes.getAadhaarDataObject().getName());
        pdfdata.put("DATE OF BIRTH", aadhaarRes.getAadhaarDataObject().getDob());
        pdfdata.put("GENDER", gender);
        pdfdata.put("ADDRESS", aadhaarRes.getAadhaarDataObject().getAddress());
        pdfdata.put("GENERATED ON", aadhaarRes.getAadhaarDataObject().getTimeStamp());
        PDFGenarator pdGen = new PDFGenarator();
        try {
            return pdGen.renderMergedOutputModel(pdfdata);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            log.error("exception in generate pdf {}", customerId);
        }
        return null;
    }
}
