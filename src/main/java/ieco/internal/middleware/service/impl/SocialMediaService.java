package ieco.internal.middleware.service.impl;

import ieco.internal.middleware.domain.request.DigitalLandingStaticContentVO;
import ieco.internal.middleware.domain.request.IncomingEmail;
import ieco.internal.middleware.domain.request.WaitingListDetailsVO;
import ieco.internal.middleware.domain.response.AbstractResponse;
import ieco.internal.middleware.domain.response.ResponseObject;
import ieco.internal.middleware.enums.ResponseCodeEnum;
import ieco.internal.middleware.model.AdminDetails;
import ieco.internal.middleware.model.SocialMediaContent;
import ieco.internal.middleware.model.UsersFromSocialMedia;
import ieco.internal.middleware.repository.AdminDetailsRepository;
import ieco.internal.middleware.repository.SocialMediaContentRepository;
import ieco.internal.middleware.repository.SocialMediaUsersRepository;
import ieco.internal.middleware.util.NullCheck;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Service
public class SocialMediaService extends AbstractResponse {

    @Autowired
    private SocialMediaUsersRepository socialMediaUsersRepository;

    @Autowired
    private SocialMediaContentRepository socialMediaContentRepository;

    @Autowired
	private AdminDetailsRepository adminDetailsRepository;

    private Logger log = LoggerFactory.getLogger(SocialMediaService.class);


    private static final String[] IP_HEADER_CANDIDATES = {
            "X-Forwarded-For",
            "Proxy-Client-IP",
            "WL-Proxy-Client-IP",
            "HTTP_X_FORWARDED_FOR",
            "HTTP_X_FORWARDED",
            "HTTP_X_CLUSTER_CLIENT_IP",
            "HTTP_CLIENT_IP",
            "HTTP_FORWARDED_FOR",
            "HTTP_FORWARDED",
            "HTTP_VIA",
            "REMOTE_ADDR"};

    public String getClientIpAddress(HttpServletRequest request) {
    	 log.info("request.getRemoteAddr() {}" ,request.getRemoteAddr());
 	    log.info("X-Forwarded-For {}",request.getHeader("X-Forwarded-For"));
        for (String header : IP_HEADER_CANDIDATES) {
            String ip = request.getHeader(header);
            if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
                return ip;
            }
        }
       
        return request.getRemoteAddr();
    }

    public ResponseObject saveSocialMediaUser(HttpServletRequest httpReq, IncomingEmail request) {

        ResponseObject responseObject = null;
        try {
        	log.info("req for campaign app user save {}",request);
            UsersFromSocialMedia data = UsersFromSocialMedia.builder().name(request.getName()).email(request.getEmail())
                    .mobile(request.getMobile()).utmCampaign(request.getUtmCampaign()).utmMedium(request.getUtmMedium())
                    .utmContent(request.getUtmContent()).utmSource(request.getUtmSource()).utmTerm(request.getUtmTerm()).userIP(getClientIpAddress(httpReq)).leadVerified("N").createdOn(new Date()).tempCustomerId(request.getTempCustomerId()).cid(request.getCid()).build();

            socialMediaUsersRepository.save(data);

            
            responseObject = responseSuccess("Details Updated successfully",
                    ResponseCodeEnum.DETAILS_UPADTED_SUCCESSFULLY);
            return responseObject;
        } catch (Exception e) {
            e.printStackTrace();
            log.info("Contact creation Failed for saveSocialMediaUser {}", request);
            log.error("Exception in saveSocialMediaUser {}", e.getMessage());
            responseObject = responseError("Error While save SocialMediaUser Details",
            		ResponseCodeEnum.TECHNICAL_FAILURE);
        }

        return responseObject;
    }


    
    public ResponseObject getSocialMediaContent(String cid) {


        Optional<SocialMediaContent> entityData = socialMediaContentRepository.findByCid(cid);

        if (entityData.isPresent()) {
            SocialMediaContent data = entityData.get();
            String dataImage = "data:image/png;base64,";
            if (new NullCheck<>(data.getWebPhoto()).isNotNull()) {
                data.setWebImage(dataImage.concat(Base64.getEncoder().encodeToString(data.getWebPhoto())));
            }
            if (new NullCheck<>(data.getMobPhoto()).isNotNull()) {
                data.setMobImage(dataImage.concat(Base64.getEncoder().encodeToString(data.getMobPhoto())));
            }
            return responseSuccess("", ResponseCodeEnum.DATA_FOUND, data,
                    "data");
        } else {
            return responseSuccess("Details not found with given cid",
                    ResponseCodeEnum.DATA_NOT_FOUND);
        }


    }


    public ResponseObject updateDetails(WaitingListDetailsVO req) {

        try {
            UsersFromSocialMedia data = socialMediaUsersRepository.findByTempCustomerIdAndEmail(req.getTempCustomerId(), req.getEmailId());
            data.setLeadVerified("Y");
            socialMediaUsersRepository.save(data);
        } catch (Exception e) {
        	log.error("error while setting lead verified flag {}",e.getMessage());
        }
        return responseSuccess("Details Updated successfully",
                ResponseCodeEnum.DETAILS_UPADTED_SUCCESSFULLY);

    }

    public ResponseObject getAllCIDData(String token) {
        if (token != null && !token.isEmpty()) {

            if (validateToken(token)) {
                HttpHeaders headers = null;
                List<String> mapKeyList = new ArrayList<String>();
                List<Object> attrList;
                List<SocialMediaContent> socialMediaContentList = socialMediaContentRepository.findByIsActive("Y");
                List<SocialMediaContent> allData = new ArrayList<>();
                for (SocialMediaContent socialMediaContent:socialMediaContentList) {
                    if (new NullCheck<>(socialMediaContent.getWebPhoto()).isNotNull()) {
                        socialMediaContent.setWebImage(Base64.getEncoder().encodeToString(socialMediaContent.getWebPhoto()));
                    }
                    if (new NullCheck<>(socialMediaContent.getMobPhoto()).isNotNull()) {
                        socialMediaContent.setMobImage(Base64.getEncoder().encodeToString(socialMediaContent.getMobPhoto()));
                    }
                    allData.add(socialMediaContent);
                }
                mapKeyList.addAll(Arrays.asList("records"));
                attrList = Arrays.asList(allData);
                return responseEntityMultipleDataCookieSuccess("Records fetched successfully!",
                        ResponseCodeEnum.ACCESS_PROVIDED, attrList, headers, mapKeyList);
            }
            return responseError("Please provide valid token!", ResponseCodeEnum.EMAIL_NOT_EXISTS);
        }

        return responseError("Authentication is required to access this resource!", ResponseCodeEnum.EMAIL_NOT_EXISTS);
    }
    
    public ResponseObject saveDetails(DigitalLandingStaticContentVO req, String token) {
    	if (token != null && !token.isEmpty()) {

			if (validateToken(token)) {
    	 Optional<SocialMediaContent> entityData = socialMediaContentRepository.findByCid(req.getCid());

         if (entityData.isPresent()) {
        	 log.info("cid found and updaing details.....");
             SocialMediaContent data = entityData.get();
             BeanUtils.copyProperties(req, data);
             socialMediaContentRepository.save(data);
             return responseSuccess("Details Updated successfully",
                     ResponseCodeEnum.DETAILS_UPADTED_SUCCESSFULLY);
         }else {
        	 log.info("cid not found and inserting as new cid...");
        	 SocialMediaContent data = new SocialMediaContent();
        	 BeanUtils.copyProperties(req, data);
        	 data.setIsActive("Y");
        	 socialMediaContentRepository.save(data);
             return responseSuccess("Details saved successfully",
                     ResponseCodeEnum.DETAILS_UPADTED_SUCCESSFULLY);
         }
         
			}
			return responseError("Please provide valid token!", ResponseCodeEnum.EMAIL_NOT_EXISTS);
    	}
    	return responseError("Authentication is required to access this resource!", ResponseCodeEnum.EMAIL_NOT_EXISTS); 
    }
    
    public ResponseObject deleteCID(String cid,String token) {
    	if (token != null && !token.isEmpty()) {

			if (validateToken(token)) {
   	 Optional<SocialMediaContent> entityData = socialMediaContentRepository.findByCid(cid);

        if (entityData.isPresent()) {
            SocialMediaContent data = entityData.get();
            data.setIsActive("N");
            socialMediaContentRepository.save(data);
            return responseSuccess("Deleted successfully",
                    ResponseCodeEnum.DETAILS_UPADTED_SUCCESSFULLY);
        }
        return responseSuccess("Details not found with given cid",
                ResponseCodeEnum.DATA_NOT_FOUND);
			}
			return responseError("Please provide valid token!", ResponseCodeEnum.EMAIL_NOT_EXISTS);
    	}
    	return responseError("Authentication is required to access this resource!", ResponseCodeEnum.EMAIL_NOT_EXISTS); 
   }
    
    boolean validateToken(String token) {
		Optional<AdminDetails> adminDetails = adminDetailsRepository.findByToken(token);
		if (adminDetails.isPresent() && adminDetails.get().getToken().equals(token)) {
				return true;
		}
		return false;
	}
}