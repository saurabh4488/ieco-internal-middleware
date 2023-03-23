package ieco.internal.middleware.controller;

import ieco.internal.middleware.domain.request.*;
import ieco.internal.middleware.domain.response.AbstractResponse;
import ieco.internal.middleware.domain.response.ResponseObject;
import ieco.internal.middleware.enums.PatternType;
import ieco.internal.middleware.enums.ResponseCodeEnum;
import ieco.internal.middleware.service.WaitingListService;
import ieco.internal.middleware.service.impl.SocialMediaService;
import ieco.internal.middleware.util.CommonUtils;
import ieco.internal.middleware.util.CustomerConstants;
import ieco.internal.middleware.util.NullCheck;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;


@RestController
public class WaitingListController {

	private Logger log = LoggerFactory.getLogger(WaitingListController.class);

	@Autowired
	private WaitingListService waitingListService;
	
	@Autowired
	private SocialMediaService socialMediaService;


	@PostMapping(value = "/getWaitingListNumber")
	public ResponseEntity<ResponseObject> getWaitingListNumber(@Valid @RequestBody IncomingEmail email) {
		log.info("request for getWaitingListNumber {}", email);
		if (new NullCheck<>(email).withEmpty(IncomingEmail::getEmail).isNotNullOrEmpty()) {
			if (!CommonUtils.validatePattern(email.getEmail(), PatternType.EMAIL_ADDRESS)) {
				return new ResponseEntity<>(
						AbstractResponse.responseError(CustomerConstants.INVALID_EMAIL, ResponseCodeEnum.INVALID_EMAIL),
						HttpStatus.OK);
			}

			return new ResponseEntity<>(waitingListService.createWaitingListNumber(email), HttpStatus.OK);
		}
		return new ResponseEntity<>(
				AbstractResponse.responseError(CustomerConstants.EMAIL_MUST_NOT_BLANK, ResponseCodeEnum.BLANK_EMAIL),
				HttpStatus.OK);
	}

	@PostMapping(value = "/verifyRefCode")
	public ResponseEntity<ResponseObject> verifyRefCode(@Valid @RequestBody IncomingRefCode refCode) {
		log.info("request for verifyRefCode {}", refCode);
		if (new NullCheck<>(refCode).withEmpty(IncomingRefCode::getRefCode).isNotNullOrEmpty()) {

			return new ResponseEntity<>(waitingListService.verifyRefCode(refCode.getRefCode()), HttpStatus.OK);
		}
		return new ResponseEntity<>(
				AbstractResponse.responseError(CustomerConstants.EMAIL_MUST_NOT_BLANK, ResponseCodeEnum.BLANK_EMAIL),
				HttpStatus.OK);
	}

	@PostMapping(value = "/updateDetails")
	public ResponseEntity<ResponseObject> updateDetails(@Valid @RequestBody WaitingListDetailsVO updateRequest) {
		log.info("request for updateDetails {}", updateRequest);

		return new ResponseEntity<>(socialMediaService.updateDetails(updateRequest), HttpStatus.OK);

	}

	@PostMapping(value = "/saveInvitationURL")
	public ResponseEntity<ResponseObject> saveInvitaionURL(HttpServletRequest httpReq,  @RequestBody IncomingEmail email) {
		if(email.getIsSocialMedia()) {
			
			log.info("request for saveInvitationURL {}", email);
			
			
			return new ResponseEntity<>(socialMediaService.saveSocialMediaUser(httpReq,email), HttpStatus.OK);
			
		}
		else {
			log.info("request for saveInvitationURL {}", email);
			if (new NullCheck<>(email).withEmpty(IncomingEmail::getEmail).isNotNullOrEmpty()) {
				if (!CommonUtils.validatePattern(email.getEmail(), PatternType.EMAIL_ADDRESS)) {
					return new ResponseEntity<>(
							AbstractResponse.responseError(CustomerConstants.INVALID_EMAIL, ResponseCodeEnum.INVALID_EMAIL),
							HttpStatus.OK);
				}

				return new ResponseEntity<>(waitingListService.saveInvitaionURL(email), HttpStatus.OK);
			}
			return new ResponseEntity<>(
					AbstractResponse.responseError(CustomerConstants.EMAIL_MUST_NOT_BLANK, ResponseCodeEnum.BLANK_EMAIL),
					HttpStatus.OK);
		}
		
	}

	@PostMapping(value = "/checkAccess")
	public ResponseEntity<ResponseObject> checkAccess(@Valid @RequestBody IncomingEmail email) {
		log.info("request for checkAccess {}", email);
		if (new NullCheck<>(email).withEmpty(IncomingEmail::getEmail).isNotNullOrEmpty()) {
			if (!CommonUtils.validatePattern(email.getEmail(), PatternType.EMAIL_ADDRESS)) {
				return new ResponseEntity<>(
						AbstractResponse.responseError(CustomerConstants.INVALID_EMAIL, ResponseCodeEnum.INVALID_EMAIL),
						HttpStatus.OK);
			}

			return new ResponseEntity<>(waitingListService.checkAccess(email.getEmail().toLowerCase().trim()),
					HttpStatus.OK);
		}
		return new ResponseEntity<>(
				AbstractResponse.responseError(CustomerConstants.EMAIL_MUST_NOT_BLANK, ResponseCodeEnum.BLANK_EMAIL),
				HttpStatus.OK);
	}

	@PostMapping(value = "/fetchAllUsers")
	public ResponseEntity<ResponseObject> fetchAllUsers(@Valid @RequestBody PaginationVO paging,
			@RequestHeader(value = "token", required = true) String token) {
		log.info("request for fetchAllUsers {}", paging);

		return new ResponseEntity<>(waitingListService.findPaginated(paging, token), HttpStatus.OK);
	}

	@PostMapping(value = "/findByEmailIdContaining/{searchChars}")
	public ResponseEntity<ResponseObject> findByEmailIdContaing(@PathVariable("searchChars") String searchChars,
			@RequestBody IncomingEmail request, @RequestHeader(value = "token", required = true) String token) {
		log.info("request for findByEmailIdContaining {}", searchChars);

		return new ResponseEntity<>(waitingListService.findByEmailIdContaing(request, searchChars, token),
				HttpStatus.OK);
	}

	@PostMapping(value = "/admin/login")
	public ResponseEntity<ResponseObject> login(@Valid @RequestBody IncomingEmail request) {
		log.info("request for admin {}", request);
		if (new NullCheck<>(request).allNotNull(request.getEmail(), request.getPassword()).isNotNull()) {
			return new ResponseEntity<>(waitingListService.login(request), HttpStatus.OK);
		}
		return AbstractResponse.responseEntityError("Body must not be blank or empty",
				ResponseCodeEnum.LOGIN_PLS_CHECK_PASSWORD_AND_RETRY);
	}

	@PostMapping(value = "/provideAccess")
	public ResponseEntity<ResponseObject> provideAccess(@Valid @RequestBody ProvideAccessVO request,
			@RequestHeader(value = "token", required = true) String token) {
		log.info("request for provideAccess {}", request);

		return new ResponseEntity<>(waitingListService.provideAccess(request, token), HttpStatus.OK);
	}

	@PostMapping(value = "/deleteUser")
	public ResponseEntity<ResponseObject> deleteUser(@Valid @RequestBody ProvideAccessVO request,
			@RequestHeader(value = "token", required = true) String token) {
		log.info("request for provideAccess {}", request);

		return new ResponseEntity<>(waitingListService.deleteUser(request, token), HttpStatus.OK);
	}

	@PostMapping(value = "/admin/logout")
	public ResponseEntity<ResponseObject> logout(@Valid @RequestBody IncomingEmail request,
			@RequestHeader(value = "token", required = true) String token) {
		log.info("request for logout {}", request);

		return new ResponseEntity<>(waitingListService.logout(request, token), HttpStatus.OK);
	}

	@GetMapping(value = "/admin/getReport")
	public void generateCsvResponse(HttpServletResponse response,
			@RequestHeader(value = "token", required = true) String token) {
		log.info("request for generateCsvResponse started");
		waitingListService.generateCsvResponse(response, token);
	}

	@GetMapping(value = "/debounceCheck")
	public ResponseEntity<ResponseObject> debounceCheck(@RequestParam("cid") String cid) {
		log.info("request for debounceCheck {}", cid);
		return new ResponseEntity<>(socialMediaService.getSocialMediaContent(cid), HttpStatus.OK);

	}
	
	@PostMapping(value = "/reduceWaitListNumber")
	public ResponseEntity<ResponseObject> reduceWaitingListNumber(@RequestParam("limit") String limit,@RequestHeader(value = "token", required = true) String token){
		
		log.info("request for reduceWaitListNumber {}", limit);
		return new ResponseEntity<>(waitingListService.reduceWaitingListNumber(limit,token), HttpStatus.OK);
		
	}
	
	
	
	
	
}