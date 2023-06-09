package ieco.internal.middleware.controller;

import ieco.internal.middleware.domain.request.DigitalLandingStaticContentVO;
import ieco.internal.middleware.domain.response.ResponseObject;
import ieco.internal.middleware.service.impl.SocialMediaService;
import ieco.internal.middleware.util.NullCheck;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;

@RestController
public class DigitalLandingController {

	private Logger log = LoggerFactory.getLogger(DigitalLandingController.class);
	@Autowired
	private SocialMediaService socialMediaService;

	@PostMapping(value = "/saveCidDetails")
	public ResponseEntity<ResponseObject> saveCidDetails(@RequestParam("cid") String cid,@RequestParam("title") String title,
														 @RequestParam("bulletPoints") String bulletPoints,@RequestParam("formTitle") String formTitle,
														 @RequestParam(value = "webPhoto", required = false) MultipartFile webPhoto ,
														 @RequestParam(value = "mobPhoto", required = false) MultipartFile mobPhoto,
														 @RequestParam("thankyouMessage") String thankyouMessage,@RequestParam("campaignName") String campaignName,
														 @RequestParam("offerText") String offerText,@RequestParam("offerDisclaimer") String offerDisclaimer,
														 @RequestParam("offerTagURL") String offerTagURL,
														 @RequestParam("layout") String layout,@RequestParam("deepLinkingURL") String deepLinkingURL,
														 @RequestParam(value = "webImage", required = false) String webImage,
														 @RequestParam(value = "mobImage", required = false) String mobImage,
														 @RequestHeader(value = "token", required = true) String token) throws IOException {


		DigitalLandingStaticContentVO req = new DigitalLandingStaticContentVO();
		req.setCid(cid);
		req.setTitle(title);
		req.setBulletPoints(bulletPoints);
		req.setFormTitle(formTitle);
		req.setThankyouMessage(thankyouMessage);
		req.setCampaignName(campaignName);
		req.setOfferText(offerText);
		req.setOfferDisclaimer(offerDisclaimer);
		req.setOfferTagURL(offerTagURL);
		req.setLayout(layout);
		req.setDeepLinkingURL(deepLinkingURL);

		if (new NullCheck<>(webPhoto).isNotNull()) {
			req.setWebPhoto(webPhoto.getBytes());
		}else {
			if (new NullCheck<>(webImage).isNotNull()) {
				byte[] webImageDecodedContent = Base64.getDecoder().decode(webImage);
				req.setWebPhoto(webImageDecodedContent);
			}
		}

		if (new NullCheck<>(mobPhoto).isNotNull()) {
			req.setMobPhoto(mobPhoto.getBytes());
		}else {
			if (new NullCheck<>(mobImage).isNotNull()) {
				byte[] mobImageDecodedContent = Base64.getDecoder().decode(mobImage);
				req.setMobPhoto(mobImageDecodedContent);
			}
		}
		log.info("request for saveCidDetails {}", req.getCid());
		return new ResponseEntity<>(socialMediaService.saveDetails(req,token), HttpStatus.OK);

	}
	
	@GetMapping(value = "/deleteCidDetails")
	public ResponseEntity<ResponseObject> deleteCidDetails(@RequestParam("cid") String cid,@RequestHeader(value = "token", required = true) String token){
		
		log.info("request for deleteCidDetails {}", cid);
		return new ResponseEntity<>(socialMediaService.deleteCID(cid,token), HttpStatus.OK);
		
	}
	
	@GetMapping(value = "/fetchAllCidData")
	public ResponseEntity<ResponseObject> fetchAllCidData(@RequestHeader(value = "token", required = true) String token){
		
		log.info("request for fetchAllCidData");
		return new ResponseEntity<>(socialMediaService.getAllCIDData(token), HttpStatus.OK);
		
	}
}
