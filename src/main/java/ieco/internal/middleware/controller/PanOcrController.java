package ieco.internal.middleware.controller;

import ieco.internal.middleware.domain.request.PanImageRequest;
import ieco.internal.middleware.enums.DmsDocumentTypeEnum;
import ieco.internal.middleware.feignclient.DmsIntegrationClient;
import ieco.internal.middleware.feignclient.KRAFileClient;
import ieco.internal.middleware.feignclient.PanOcrClient;
import ieco.internal.middleware.feignclient.PanOcrRetrieveImgClient;
import ieco.internal.middleware.repository.SocialMediaUsersRepository;
import ieco.internal.middleware.util.IecoCustomMultipartFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;

@Controller
public class PanOcrController {
    private Logger log = LoggerFactory.getLogger(PanOcrController.class);
    @Autowired
    private PanOcrClient panOcrClient;
    @Autowired
    private KRAFileClient kraFileClient;
    @Autowired
    private DmsIntegrationClient dmsIntegrationClient;
    @Autowired
    private HttpServletRequest httpServletRequest;
    @Autowired
    private PanOcrRetrieveImgClient panOcrRetrieveImgClient;
    @Autowired
    private SocialMediaUsersRepository socialMediaUsersRepository;
    @Value("${dmsDocsUploadServerPath}")
    private String dmsDocsUploadServerPath;

    public String fetch(String iecoid) {
        try {
            log.info("PanOcrController fetchPanDtls method started - {}", iecoid);
            PanImageRequest panImageRequest = PanImageRequest.builder().iecoid(iecoid).build();
            byte[] fetchImage = panOcrClient.fetchImage(panImageRequest);
            String base64Image = Base64.getEncoder().encodeToString(fetchImage);
            if (base64Image != null && base64Image.length() <= 1) {
                byte[] retrieveImage = panOcrRetrieveImgClient.retrieveImage(panImageRequest);
                base64Image = Base64.getEncoder().encodeToString(retrieveImage);
            }
            log.info("PanOcrController base64Image - {}", base64Image);
            return base64Image;
        } catch (Exception e) {
            log.error("Exception is PanOcrController fetchPanDtls method -{}", e.getMessage());
            e.printStackTrace();
        } finally {
            log.info("PanOcrController fetchPanDtls method ended - {}", iecoid);
        }
        return null;
    }

    @PostMapping("/fetchDMSDocs")
    @ResponseBody
    public HashMap<String, String> fetchDMSDocs(@RequestParam String customerId, @RequestParam String docTypeId) {
        HashMap<String, String> result = new HashMap<>();
        try {
            log.debug("fetchDMSDocs started for extraction");
            List<String> objectList = socialMediaUsersRepository.fetchDMSDocs(customerId, docTypeId);
            log.info("objectList - {}", objectList);
            String str = objectList.get(0);
            log.info("objectList str - {}", str);
            String[] res = str.split("[,]", 0);
            String custId = res[0];
            String dmsDocId = res[1];
            String fileName = res[2];
            String contentType = res[3];
            MultipartFile multipartFile = null;
            log.info("downloading file from dmsDocId:{}", dmsDocId);
            fileName = dmsDocId + "_" + fileName;
            multipartFile = downloadDocumentsAsByteArray(custId, fileName, dmsDocId, contentType, null);
            log.info("multipartFile :{}", multipartFile.getSize());
            byte[] fileContent = multipartFile.getBytes();
            String base64Image = Base64.getEncoder().encodeToString(fileContent);
            result.put("status", "Success");
            result.put("fileBase64", base64Image);
            result.put("contentType", contentType);
            result.put("dmsDocId", dmsDocId);
        } catch (Exception e) {
            log.error("Error while fetchDMSDocs file for extraction", e);
            result.put("status", "Error");
        }
        return result;
    }

    @PostMapping("/fetchPanDtls")
    @ResponseBody
    public String sentCvlZipFileForExtraction(@RequestParam String iecoid,
                                               @RequestHeader(value = "panDownload", required = false) String panDownload,
                                               @RequestParam(value = "dmsDocId", required = false) String dmsDocId,
                                               @RequestParam(value = "fileName", required = false) String fileName,
                                               @RequestParam(value = "contentType", required = false) String contentType,
                                               @RequestParam(value = "dmsDocumentType", required = false) String dmsDocumentType) {
        try {
            if (panDownload != null && panDownload.equals("true")) {
                return fetch(iecoid);
            } else {
                log.debug("Sending Cvl zip file for image extraction");
                MultipartFile zipFile = null;
                log.info("downloading CVL zip from dms:{}", iecoid);
                DmsDocumentTypeEnum dmsDocumentTypeEnum
                        = DmsDocumentTypeEnum.valueOf(dmsDocumentType);

                zipFile = downloadDocumentsAsByteArray(iecoid, fileName, dmsDocId, contentType, dmsDocumentTypeEnum);
                log.info("zipFile :{}", zipFile);
                if (zipFile != null) {
                    return "success";
                } else {
                    return "Error";
                }
            }
        } catch (Exception e) {
            log.error("Error while sending file for extraction", e);
            return "Error";
        }
    }

    public IecoCustomMultipartFile downloadDocumentsAsByteArray(String customerId, String fileName, String dmsDocId, String contentType, DmsDocumentTypeEnum dmsDocumentType) {
        log.info("Download Document As Byte Array [START] with [Customer ID]: {}", customerId);
        IecoCustomMultipartFile multipartFile = null;
        try {
            log.info("Retrieveing file from DMS for [DOC ID]: {}", dmsDocId);
            byte[] data = dmsIntegrationClient.downloadDocumentsAsByteArray(dmsDocId);
            log.info("data - {}", data.length);
            multipartFile = new IecoCustomMultipartFile(data, fileName, fileName, contentType);
            if (!multipartFile.isEmpty()) {
                String realPathtoUploads = dmsDocsUploadServerPath + customerId + "/";
                log.info("realPathtoUploads == {}", realPathtoUploads);
                if (!new File(realPathtoUploads).exists()) {
                    log.info("Folder is not created at the path....creating folder at path for {}", customerId);
                    new File(realPathtoUploads).mkdir();
                }
                dmsIntegrationClient.uploadDocuments("", multipartFile, dmsDocumentType, Integer.parseInt(customerId));
            }
        } catch (Exception e) {
            log.error("Download Document As Byte Array [ERROR] with [CUSTOMER ID]: {}", customerId, e);
        }
        log.info("Download Document As Byte Array [END] with [CUSTOMER ID]: {}", customerId);
        return multipartFile;
    }


}
