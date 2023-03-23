/*
 *
 * Copyright (c) 2019 - 2020 PricewaterhouseCoopers Pvt Ltd
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package ieco.internal.middleware.enums;

import java.util.Arrays;

/**
 * The enum  Response code enum.
 *
 * @author PwC
 * @version <b>0.0.9</b>
 */
public enum ResponseCodeEnum {
	
	WAITINGLIST_NUMBER_GENERATED("WL001"),
	DEBOUNCE_VERIFICATION_FAILED("WL010"),
	DEBOUNCE_VERIFICATION_SUCCESS("WL011"),
	
	DIGILOCKER_DATA_FETCHED_SUCCESSFULLY("DG001"),
	
	DIGILOCKER_DATA_FETCH_FAILED("DG002"),
	
	ACCESS_PROVIDED("WL003"),
	
	ACCESS_NOT_PROVIDED("WL004"),
	
	REFERENCE_CODE_VERIFIED_SUCCESSFULLY("REF001"),
	
	REFERENCE_CODE_VERIFIED_FAILED("REF002"),
	
	DETAILS_UPADTED_SUCCESSFULLY("WL002"),
	
	DETAILS_UPADTED_FAILED("WL007"),
	
	TOKEN_VALIDATION_FAILED("WL008"),
	
	TECHNICAL_FAILURE("DL001"),
	/**
	 * Registration successful response code enum.
	 */
	REGISTRATION_SUCCESSFUL("REG001"),
	/**
	 * Invalid mobile response code enum.
	 */
	INVALID_MOBILE("REG002"),
	/**
	 * Invalid email response code enum.
	 */
	INVALID_EMAIL("REG003"),
	/**
	 * Minors response code enum.
	 */
	MINORS("REG004"),
	/**
	 * Invalid pan response code enum.
	 */
	INVALID_PAN("REG005"),
	/**
	 * Email exists response code enum.
	 */
	EMAIL_EXISTS("WL006"),
	
	DATA_FOUND("SC001"),
	
	DATA_NOT_FOUND("SC002"),
	
	EMAIL_NOT_EXISTS("WL005"),
	/**
	 * Reg your name in defaulter list response code enum.
	 */
	REG_YOUR_NAME_IN_DEFAULTER_LIST("REG007"),
	/**
	 * Reg ksec account already exist response code enum.
	 */
	REG_KSEC_ACCOUNT_ALREADY_EXIST("REG008"),
	/**
	 * Reg bcif account already exist response code enum.
	 */
	REG_BCIF_ACCOUNT_ALREADY_EXIST("REG009"),
	/**
	 * Pan doesnt exist response code enum.
	 */
	PAN_DOESNT_EXIST("REG010"),
	/**
	 * Reg please try again after some time response code enum.
	 */
	REG_PLEASE_TRY_AGAIN_AFTER_SOME_TIME("REG011"),
	/**
	 * Incorrect mobile otp response code enum.
	 */
	INCORRECT_MOBILE_OTP("REG012"),
	/**
	 * Incorrect email otp response code enum.
	 */
	INCORRECT_EMAIL_OTP("REG013"),
	/**
	 * Reg please check password match response code enum.
	 */
	REG_PLEASE_CHECK_PASSWORD_MATCH("REG014"),
	/**
	 * Invalid registration field response code enum.
	 */
	INVALID_REGISTRATION_FIELD("REG015"),
	/**
	 * Blank email response code enum.
	 */
	BLANK_EMAIL("REG016"),
	/**
	 * Blank pan response code enum.
	 */
	BLANK_PAN("REG017"),
	/**
	 * Pan exists response code enum.
	 */
	PAN_EXISTS("REG018"),
	/**
	 * Email doesnt exist response code enum.
	 */
	EMAIL_DOESNT_EXIST("REG019"),
	/**
	 * Customer details saved response code enum.
	 */
	CUSTOMER_DETAILS_SAVED("REG020"),
	/**
	 * Correct email otp response code enum.
	 */
	CORRECT_EMAIL_OTP("REG021"),
	/**
	 * Customer id not found response code enum.
	 */
	CUSTOMER_ID_NOT_FOUND("REG022"),
	/**
	 * Set password failure response code enum.
	 */
	SET_PASSWORD_FAILURE("REG023"),
	/**
	 * Otp not verified response code enum.
	 */
	OTP_NOT_VERIFIED("REG024"),
	/**
	 * Correct mobile otp response code enum.
	 */
	CORRECT_MOBILE_OTP("REG025"),
	/**
	 * Invalid transactionid response code enum.
	 */
	INVALID_TRANSACTIONID("COM001"),
	/**
	 * Invalid transaction response code enum.
	 */
	INVALID_TRANSACTION("COM002"),
	/**
	 * Invalid sessionid response code enum.
	 */
	INVALID_SESSIONID("COM003"),
	/**
	 * Login success response code enum.
	 */
	LOGIN_SUCCESS("LOG001"),
	/**
	 * Login username pwd incorrect response code enum.
	 */
	LOGIN_USERNAME_PWD_INCORRECT("LOG002"),
	/**
	 * Login incorrect otp response code enum.
	 */
	LOGIN_INCORRECT_OTP("LOG003"),
	/**
	 * Login combo mob pan doesnt match response code enum.
	 */
	LOGIN_COMBO_MOB_PAN_DOESNT_MATCH("LOG004"),
	/**
	 * Login username not found response code enum.
	 */
	LOGIN_USERNAME_NOT_FOUND("LOG005"),
	/**
	 * Login credential missmatch response code enum.
	 */
	LOGIN_CREDENTIAL_MISSMATCH("LOG006"),
	/**
	 * Login password decrypt error response code enum.
	 */
	LOGIN_PASSWORD_DECRYPT_ERROR("LOG007"),
	/**
	 * Login pls check password and retry response code enum.
	 */
	LOGIN_PLS_CHECK_PASSWORD_AND_RETRY("LOG008"),
	/**
	 * Login password incorrect retry response code enum.
	 */
	LOGIN_PASSWORD_INCORRECT_RETRY("LOG009"),
	/**
	 * Login otp verified response code enum.
	 */
	LOGIN_OTP_VERIFIED("LOG010"),
	/**
	 * Login error response code enum.
	 */
	LOGIN_ERROR("LOG011"),
	/**
	 * Get user details success response code enum.
	 */
	GET_USER_DETAILS_SUCCESS("ATH001"),
	/**
	 * Verify user success response code enum.
	 */
	VERIFY_USER_SUCCESS("ATH002"),
	/**
	 * Lock account success response code enum.
	 */
	LOCK_ACCOUNT_SUCCESS("LCK001"),
	/**
	 * Lock account failure response code enum.
	 */
	LOCK_ACCOUNT_FAILURE("LCK002"),
	/**
	 * Lock account unlock success response code enum.
	 */
	LOCK_ACCOUNT_UNLOCK_SUCCESS("LCK003"),
	/**
	 * Lock account unlock failure response code enum.
	 */
	LOCK_ACCOUNT_UNLOCK_FAILURE("LCK004"),
	/**
	 * Forgot password reset success response code enum.
	 */
	FORGOT_PASSWORD_RESET_SUCCESS("PWD001"),
	/**
	 * Forgot password reset failure response code enum.
	 */
	FORGOT_PASSWORD_RESET_FAILURE("PWD002"),
	/**
	 * Locked password reset success response code enum.
	 */
	LOCKED_PASSWORD_RESET_SUCCESS("PWD003"),
	/**
	 * Locked password reset failure response code enum.
	 */
	LOCKED_PASSWORD_RESET_FAILURE("PWD004"),
	/**
	 * User already unlocked response code enum.
	 */
	USER_ALREADY_UNLOCKED("PWD005"),
	/**
	 * Mobile otp sent response code enum.
	 */
	MOBILE_OTP_SENT("OTP001"),
	/**
	 * Email otp sent response code enum.
	 */
	EMAIL_OTP_SENT("OTP002"),
	/**
	 * Both otp sent response code enum.
	 */
	BOTH_OTP_SENT("OTP003"),
	/**
	 * Otp resend exceed response code enum.
	 */
	OTP_RESEND_EXCEED("OTP004"),
	/**
	 * Otp user blocked response code enum.
	 */
	OTP_USER_BLOCKED("OTP005"),
	/**
	 * Otp param check response code enum.
	 */
	OTP_PARAM_CHECK("OTP006"),
	/**
	 * Otp transaction invalid response code enum.
	 */
	OTP_TRANSACTION_INVALID("OTP007"),
	/**
	 * Otp validate response code enum.
	 */
	OTP_VALIDATE("OTP008"),
	/**
	 * Otp expire response code enum.
	 */
	OTP_EXPIRE("OTP009"),
	/**
	 * Otp validate limit response code enum.
	 */
	OTP_VALIDATE_LIMIT("OTP010"),
	/**
	 * Otp invalidate limit response code enum.
	 */
	OTP_INVALIDATE_LIMIT("OTP011"),
	/**
	 * Otp not valid response code enum.
	 */
	OTP_NOT_VALID("OTP012"),
	/**
	 * Otp invalid request response code enum.
	 */
	OTP_INVALID_REQUEST("OTP013"),
	/**
	 * Otp inactive response code enum.
	 */
	OTP_INACTIVE("OTP014"),
	/**
	 * Otp raised response code enum.
	 */
	OTP_RAISED("OTP015"),
	/**
	 * Otp same request type response code enum.
	 */
	OTP_SAME_REQUEST_TYPE("OTP016"),
	/**
	 * Otp invalid comm type response code enum.
	 */
	OTP_INVALID_COMM_TYPE("OTP017"),
	/**
	 * Changing password success response code enum.
	 */
	CHANGING_PASSWORD_SUCCESS("PWD006"),
	/**
	 * Changing password failure response code enum.
	 */
	CHANGING_PASSWORD_FAILURE("PWD007"),
	/**
	 * Changing password failure with wrong pwd response code enum.
	 */
	CHANGING_PASSWORD_WRONG_OLD_PWD("PWD009"),
	/**
	 * Password expired response code enum.
	 */
	PASSWORD_EXPIRED("PWD008"),
	/**
	 * Kyc customer id not valid response code enum.
	 */
	KYC_CUSTOMER_ID_NOT_VALID("KYC001"),
	/**
	 * Kyc pdf generated response code enum.
	 */
	KYC_PDF_GENERATED("KYC002"),
	/**
	 * Kyc initiate success response code enum.
	 */
	KYC_INITIATE_SUCCESS("KYC003"),
	/**
	 * Kyc initiate error response code enum.
	 */
	KYC_INITIATE_ERROR("KYC004"),
	/**
	 * Kyc bank details req invalid response code enum.
	 */
	KYC_BANK_DETAILS_REQ_INVALID("KYC005"),
	/**
	 * Kyc add bank account success response code enum.
	 */
	KYC_ADD_BANK_ACCOUNT_SUCCESS("KYC006"),
	/**
	 * Kyc add bank account error response code enum.
	 */
	KYC_ADD_BANK_ACCOUNT_ERROR("KYC007"),
	/**
	 * Kyc bank details by ifsc success response code enum.
	 */
	KYC_BANK_DETAILS_BY_IFSC_SUCCESS("KYC008"),
	/**
	 * Kyc bank details by ifsc error response code enum.
	 */
	KYC_BANK_DETAILS_BY_IFSC_ERROR("KYC009"),
	/**
	 * Kyc skip further check response code enum.
	 */
	KYC_SKIP_FURTHER_CHECK("KYC010"),
	/**
	 * Kyc next aadhar call response code enum.
	 */
	KYC_NEXT_AADHAR_CALL("KYC011"),
	/**
	 * Kyc details saved response code enum.
	 */
	KYC_DETAILS_SAVED("KYC012"),
	/**
	 * Aadhar field missing response code enum.
	 */
	AADHAR_FIELD_MISSING("KYC013"),
	/**
	 * Xml date success response code enum.
	 */
	XML_DATE_SUCCESS("KYC014"),
	/**
	 * Aadhar data save success response code enum.
	 */
	AADHAR_DATA_SAVE_SUCCESS("KYC015"),
	/**
	 * Name mismatch response code enum.
	 */
	NAME_MISMATCH("KYC016"),
	/**
	 * Aadhar data save failure response code enum.
	 */
	AADHAR_DATA_SAVE_FAILURE("KYC017"),
	/**
	 * Kyc retry time exceed response code enum.
	 */
	KYC_RETRY_TIME_EXCEED("KYC018"),
	/**
	 * Kyc next voip call response code enum.
	 */
	KYC_NEXT_IPV_CALL("KYC019"),
	/**
	 * Kyc invalid dob retry response code enum.
	 */
	KYC_INVALID_DOB_RETRY("KYC020"),
	/**
	 * Xml date error response code enum.
	 */
	XML_DATE_ERROR("KYC021"),
	/**
	 * Fuzzy error response code enum.
	 */
	FUZZY_ERROR("KYC022"),
	/**
	 * Blank sharecode response code enum.
	 */
	BLANK_SHARECODE("KYC023"),
	/**
	 * Cms could not find content response code enum.
	 */
	CMS_COULD_NOT_FIND_CONTENT("CMS001"),
	/**
	 * Cms not found error code response code enum.
	 */
	CMS_NOT_FOUND_ERROR_CODE("CMS002"),
	/**
	 * Nominee add error response code enum.
	 */
	NOMINEE_ADD_ERROR("NOM001"),
	/**
	 * Nominee add success response code enum.
	 */
	NOMINEE_ADD_SUCCESS("NOM002"),
	/**
	 * Integration response code enum.
	 */
	INTEGRATION("INTEGRATION"),
	/**
	 * Prepare cibil success response code enum.
	 */
	PREPARE_CIBIL_SUCCESS("CIB001"),
	/**
	 * Prepare cibil pdf success response code enum.
	 */
	PREPARE_CIBIL_PDF_SUCCESS("CIB002"),
	/**
	 * Prepare cibil error response code enum.
	 */
	PREPARE_CIBIL_ERROR("CIB003"),
	/**
	 * Prepare cibil pdf error response code enum.
	 */
	PREPARE_CIBIL_PDF_ERROR("CIB004"),
	/**
	 * Initiate app session success response code enum.
	 */
	INITIATE_APP_SESSION_SUCCESS("COM004"),
	/**
	 * Initiate app session failure response code enum.
	 */
	INITIATE_APP_SESSION_FAILURE("COM005"),
	/**
	 * Forgot username success response code enum.
	 */
	FORGOT_USERNAME_SUCCESS("FUS001"),
	/**
	 * Forgot username failure response code enum.
	 */
	FORGOT_USERNAME_FAILURE("FUS002"),
	/**
	 * Nsdl pan validation success valid pan response code enum.
	 */
	NSDL_PAN_VALIDATION_SUCCESS_VALID_PAN("NSD001"),
	/**
	 * Nsdl pan validation success invalid pan response code enum.
	 */
	NSDL_PAN_VALIDATION_SUCCESS_INVALID_PAN("NSD002"),
	/**
	 * Nsdl pan validation failure response code enum.
	 */
	NSDL_PAN_VALIDATION_FAILURE("NSD003"),
	/**
	 * Bcif pan validation success response code enum.
	 */
	BCIF_PAN_VALIDATION_SUCCESS("BCI001"),
	/**
	 * Bcif pan validation failure response code enum.
	 */
	BCIF_PAN_VALIDATION_FAILURE("BCI002"),
	/**
	 * Ncif pan validation success response code enum.
	 */
	NCIF_PAN_VALIDATION_SUCCESS("NCI001"),
	/**
	 * Ncif pan validation failure response code enum.
	 */
	NCIF_PAN_VALIDATION_FAILURE("NCI002"),
	/**
	 * Ncif pan validation technical failure response code enum.
	 */
	NCIF_PAN_VALIDATION_TECH_FAILURE("NCI003"),
	/**
	 * Ncif pan validation technical failure response code enum.
	 */
	NCIF_FNAME_DOB_MOB_VALIDATION_TECH_FAILURE("NCI004"),
	/**
	 * Ncif pan validation technical failure response code enum.
	 */
	NCIF_FNAME_DOB_MOB_VALIDATION_SUCCESS("NCI005"),
	/**
	 * Ncif pan validation technical failure response code enum.
	 */
	NCIF_FNAME_DOB_MOB_VALIDATION_PROBABLE_ERROR("NCI006"),
	/**
	 * Regulatory info add success response code enum.
	 */
	REGULATORY_INFO_ADD_SUCCESS("RGI001"),
	/**
	 * Regulatory info add failure response code enum.
	 */
	REGULATORY_INFO_ADD_FAILURE("RGI002"),
	/**
	 * Consent invalid type response code enum.
	 */
	CONSENT_INVALID_TYPE("CON001"),
	/**
	 * Consent invalid value response code enum.
	 */
	CONSENT_INVALID_VALUE("CON002"),
	/**
	 * Consent details saved response code enum.
	 */
	CONSENT_DETAILS_SAVED("CON003"),
	/**
	 * Consent details error response code enum.
	 */
	CONSENT_DETAILS_ERROR("CON004"),
	/**
	 * Consent invalid customer response code enum.
	 */
	CONSENT_INVALID_CUSTOMER("CON005"),

	/**
	 * Recently viewed stocks success response code enum.
	 */
	RECENTLY_VIEWED_STOCKS_SUCCESS("DEQ001"),
	/**
	 * Recently viewed stocks error response code enum.
	 */
	RECENTLY_VIEWED_STOCKS_ERROR("DEQ002"),
	/**
	 * Blank stock id response code enum.
	 */
	BLANK_STOCK_ID("DEQ003"),
	/**
	 * Stock details success response code enum.
	 */
	STOCK_DETAILS_SUCCESS("DEQ004"),
	/**
	 * Stock details error response code enum.
	 */
	STOCK_DETAILS_ERROR("DEQ005"),
	/**
	 * Stocks found response code enum.
	 */
	STOCKS_FOUND("DEQ006"),
	/**
	 * Stocks not found response code enum.
	 */
	STOCKS_NOT_FOUND("DEQ007"),
	/**
	 * Error master success response code enum.
	 */
	GET_STOCK_NEWS_SUCCESS("DEQ008"),
	/**
	 * Error master success response code enum.
	 */
	ERROR_MASTER_SUCCESS("ERR001"),
	/**
	 * Error master fail response code enum.
	 */
	ERROR_MASTER_FAIL("ERR002"),
	/**
	 * Error master no data response code enum.
	 */
	ERROR_MASTER_NO_DATA("ERR003"),
	/**
	 * Pincode success response code enum.
	 */
	PINCODE_SUCCESS("COM006"),
	/**
	 * Pincode fail response code enum.
	 */
	PINCODE_FAIL("COM007"),
	/**
	 * Pincode no data response code enum.
	 */
	PINCODE_NO_DATA("COM008"),
	/**
	 * Master data success response code enum.
	 */
	MASTER_DATA_SUCCESS("MST001"),
	/**
	 * Master data fail response code enum.
	 */
	MASTER_DATA_FAIL("MST002"),
	/**
	 * Master data no data response code enum.
	 */
	MASTER_DATA_NO_DATA("MST003"),
	/**
	 * Customer technical exception response code enum.
	 */
	CUSTOMER_TECHNICAL_EXCEPTION("COM009"),
	/**
	 * Result not found response code enum.
	 */
	RESULT_NOT_FOUND("COM010"),

	/**
	 * Customer details success response code enum.
	 */
	CUSTOMER_DETAILS_SUCCESS("COM012"),

	/**
	 * Customer details invalid request code enum.
	 */
	CUSTOMER_INVALID_REQUEST("COM013"),

	/**
	 * Kyc link bank account error response code enum.
	 */
	KYC_LINK_BANK_ACCOUNT_ERROR("KYC024"),
	
	/**
	 * Kyc bank verification is in progress
	 */
	KYC_BANK_VERIFICATION_IN_PROGRESS("KYC094"),

	/**
	 * Kyc link bank account success response code enum.
	 */
	KYC_LINK_BANK_ACCOUNT_SUCCESS("KYC025"),
	/**
	 * Auth generate token success response code enum.
	 */
	AUTH_GENERATE_TOKEN_SUCCESS("ATH003"),
	/**
	 * Auth generate token error response code enum.
	 */
	AUTH_GENERATE_TOKEN_ERROR("ATH004"),
	/**
	 * Auth revoke token success response code enum.
	 */
	AUTH_REVOKE_TOKEN_SUCCESS("ATH005"),
	/**
	 * Auth revoke token error response code enum.
	 */
	AUTH_REVOKE_TOKEN_ERROR("ATH006"),
	/**
	 * Invalid checktype response code enum.
	 */
	INVALID_CHECKTYPE("ERRCHK001"),
	/**
	 * Auth set password expire success response code enum.
	 */
	AUTH_SET_PASSWORD_EXPIRE_SUCCESS("ATH007"),

	/**
	 * KYC batch job failed.
	 */
	KYC_JOB_FAILED("KYCJB001"),

	/**
	 * KYC batch job done.
	 */
	KYC_JOB_DONE("KYCJB002"),

	/**
	 * push KRA failed.
	 */
	PUSH_KRA_FAILED("KRA001"),

	/**
	 * PUSH KRA done.
	 */
	PUSH_KRA_DONE("KRA002"),

	/**
	 * Auth set password expire error response code enum.
	 */
	AUTH_SET_PASSWORD_EXPIRE_ERROR("ATH008"),
	/**
	 * Kyc bank account already exist response code enum.
	 */
	KYC_BANK_ACCOUNT_ALREADY_EXIST("KYC026"),
	/**
	 * Verify user error response code enum.
	 */
	VERIFY_USER_ERROR("ATH009"),
	/**
	 * Kyc max bank account reached response code enum.
	 */
	KYC_MAX_BANK_ACCOUNT_REACHED("KYC027"),
	/**
	 * Kyc bank account does not exist response code enum.
	 */
	KYC_BANK_ACCOUNT_DOES_NOT_EXIST("KYC028"),
	/**
	 * Kyc bank account otm update success response code enum.
	 */
	KYC_BANK_ACCOUNT_OTM_UPDATE_SUCCESS("KYC029"),
	/**
	 * Kyc bank account otm update error response code enum.
	 */
	KYC_BANK_ACCOUNT_OTM_UPDATE_ERROR("KYC030"),

	/**
	 * The app stage save error.
	 */
	APP_STAGE_SAVE_ERROR("APP034"),

	/**
	 * The app invalid stage input.
	 */
	APP_INVALID_STAGE_INPUT("APP035"),

	/**
	 * The app valid stage input.
	 */
	APP_VALID_STAGE_INPUT("APP036"),

	/**
	 * The app stage atrribute save success.
	 */
	APP_STAGE_ATRRIBUTE_SAVE_SUCCESS("APP038"),
	/**
	 * Kyc next personal kyc info response code enum.
	 */
	KYC_NEXT_PERSONAL_KYC_INFO("KYC031"),
	/**
	 * Kyc next regulatory info response code enum.
	 */
	KYC_NEXT_REGULATORY_INFO("KYC032"),
	/**
	 * Kyc next add nominee response code enum.
	 */
	KYC_NEXT_ADD_NOMINEE("KYC033"),
	/**
	 * Missing required field response code enum.
	 */
	MISSING_REQUIRED_FIELD("DE003"),

	/**
	 * Kyc next add link bank account response code enum.
	 */
	KYC_NEXT_ADD_LINK_BANK_ACCOUNT("KYC037"),
	/**
	 * The kyc get final kyc data success.
	 */
	KYC_GET_FINAL_KYC_DATA_SUCCESS("KYC039"),

	/**
	 * The get stock price success.
	 */
	GET_STOCK_PRICE_SUCCESS("DEQ009"),
	/**
	 * The ksec pan validation error.
	 */
	KSEC_PAN_VALIDATION_ERROR("KSEC001"),
	/**
	 * The ksec pan validation success.
	 */
	KSEC_PAN_VALIDATION_SUCCESS("KSEC002"),
	/**
	 * The ksec pan validation error sebi defaulter.
	 */
	KSEC_PAN_VALIDATION_ERROR_SEBI_DEFAULTER("KSEC003"),

	/**
	 * The login user locked.
	 */
	LOGIN_USER_LOCKED("LOG012"),

	/**
	 * The get latest news success.
	 */
	GET_LATEST_NEWS_SUCCESS("DEQ010"),
	/**
	 * The get price chart success.
	 */
	GET_PRICE_CHART_SUCCESS("DEQ011"),
	/**
	 * The get stock detailed success.
	 */
	GET_STOCK_DETAILED_SUCCESS("DEQ012"),
	/**
	 * The get comp financials success.
	 */
	GET_COMP_FINANCIALS_SUCCESS("DEQ013"),
	/**
	 * The fetch instrument success.
	 */
	FETCH_INSTRUMENT_SUCCESS("DEQ014"),

	/**
	 * Latest fetch instrument success response code enum.
	 */
	LATEST_FETCH_INSTRUMENT_SUCCESS("DEQ114"),

	/**
	 * The equity onboarding success.
	 */
	EQUITY_ONBOARDING_SUCCESS("DEQ015"),

	/**
	 * The equity onboarding failure.
	 */
	EQUITY_ONBOARDING_FAILURE("DEQ016"),

	/**
	 * The cache success.
	 */
	CACHE_SUCCESS("COM011"),

	/**
	 * The bank account add failure.
	 */
	BANK_ACCOUNT_ADD_FAILURE("DEQ017"),

	/**
	 * The customer checks add failure.
	 */
	CUSTOMER_CHECKS_ADD_SUCCESS("DEQ018"),

	/**
	 * The customer checks add failure.
	 */
	CUSTOMER_CHECKS_ADD_FAILURE("DEQ019"),

	/**
	 * The missing customer id.
	 */
	MISSING_CUSTOMER_ID("DEQ020"),

	/**
	 * The reset password failure old pwd.
	 */

	/**
	 * The save data idealake success.
	 */
	SAVE_DATA_IDEALAKE_SUCCESS("DEQ021"),

	/**
	 * The save data idealake failure.
	 */
	SAVE_DATA_IDEALAKE_FAILURE("DEQ022"),

	/**
	 * The invalid status.
	 */
	INVALID_STATUS("DEQ023"),

	/**
	 * The de acc activation success.
	 */
	DE_ACC_ACTIVATION_SUCCESS("DEQ024"),

	/**
	 * The de acc activation failure.
	 */
	DE_ACC_ACTIVATION_FAILURE("DEQ025"),

	/**
	 * The reset password failure old pwd.
	 */
	RESET_PASSWORD_FAILURE_OLD_PWD("PWD010"),

	/**
	 * The kyc already verified.
	 */
	KYC_ALREADY_VERIFIED("KYC040"),
	/**
	 * Kyc aadhar call error response code enum.
	 */
	KYC_AADHAR_CALL_ERROR("KYC041"),
	/**
	 * Kyc ipv check error response code enum.
	 */
	KYC_IPV_CHECK_ERROR("KYC042"),
	/**
	 * Kyc list nominee details error response code enum.
	 */
	KYC_LIST_NOMINEE_DETAILS_ERROR("KYC043"),

	/**
	 * Ticket data success response code enum.
	 */
	TICKET_DATA_SUCCESS("TKT001"),
	/**
	 * TICKET data fail response code enum.
	 */
	TICKET_DATA_FAIL("TKT002"),

	/**
	 * TICKET data fail response code enum.
	 */
	TICKET_INVALID_REQUEST("TKT003"),

	/**
	 * The kyc list nominee details success.
	 */
	KYC_LIST_NOMINEE_DETAILS_SUCCESS("KYC044"),

	/**
	 * The kyc customer personal info error.
	 */
	KYC_CUSTOMER_PERSONAL_INFO_ERROR("KYC045"),

	/**
	 * The kyc customer personal info success.
	 */
	KYC_CUSTOMER_PERSONAL_INFO_SUCCESS("KYC046"),

	/**
	 * The kyc update check status error.
	 */
	KYC_UPDATE_CHECK_STATUS_ERROR("KYC047"),

	/**
	 * The kyc list customer banks error.
	 */
	KYC_LIST_CUSTOMER_BANKS_ERROR("KYC048"),

	/**
	 * The kyc update check status success.
	 */
	KYC_UPDATE_CHECK_STATUS_SUCCESS("KYC049"),

	/**
	 * The kyc list customer banks success.
	 */
	KYC_LIST_CUSTOMER_BANKS_SUCCESS("KYC050"),

	/**
	 * The kyc next preview kra form.
	 */
	KYC_NEXT_PREVIEW_KRA_FORM("KYC051"),

	/**
	 * The kyc next advisory agreement.
	 */
	KYC_NEXT_ADVISORY_AGREEMENT("KYC052"),

	/**
	 * The kyc next aadhar agreement.
	 */
	KYC_NEXT_AADHAR_AGREEMENT("KYC053"),

	/**
	 * The logout success.
	 */
	LOGOUT_SUCCESS("LOG013"),
	/**
	 * The logout error.
	 */
	LOGOUT_ERROR("LOG014"),
	/**
	 * The kyc add nominee error name mismatch.
	 */
	KYC_ADD_NOMINEE_ERROR_NAME_MISMATCH("KYC054"),

	/**
	 * The kyc aadhar upload date expired error.
	 */
	KYC_AADHAR_UPLOAD_DATE_EXPIRED_ERROR("KYC055"),

	/**
	 * The dms service up.
	 */
	DMS_SERVICE_UP("DMS001"),

	/**
	 * The dms required field not found error.
	 */
	DMS_REQUIRED_FIELD_NOT_FOUND_ERROR("DMS002"),

	/**
	 * The get risk profiling status failure.
	 */
	GET_RISK_PROFILING_STATUS_FAILURE("DEQ027"),

	/**
	 * The kyc aadhar nsdl name missmatch error.
	 */
	KYC_AADHAR_NSDL_NAME_MISSMATCH_ERROR("KYC056"),

	/**
	 * The get risk profiling status success.
	 */
	GET_RISK_PROFILING_STATUS_SUCCESS("DEQ026"),
	/**
	 * The has de status success.
	 */
	HAS_DE_STATUS_SUCCESS("DEQ028"),

	/**
	 * The has de status success.
	 */
	HAS_DE_STATUS_ERROR("DEQ029"),

	/**
	 * The customer status save success.
	 */
	CUSTOMER_STATUS_SAVE_SUCCESS("CUST001"),

	/**
	 * The customer status save failure.
	 */
	CUSTOMER_STATUS_SAVE_FAILURE("CUST002"),
	/**
	 * Kyc push kra error response code enum.
	 */
	KYC_PUSH_KRA_ERROR("KYC057"),

	/** The kyc aadhar name address pincode unavailable. */
	KYC_AADHAR_NAME_ADDRESS_PINCODE_UNAVAILABLE("KYC058"),

	/** The kyc aadhar address length error. */
	KYC_AADHAR_ADDRESS_LENGTH_ERROR("KYC059"),

	/** The kyc country not india error. */
	KYC_COUNTRY_NOT_INDIA_ERROR("KYC060"),

	/** The STATE Master data success. */
	STATE_DATA_SUCCESS("ADM000"),

	/** The unable get STATE Master data error. */
	STATE_DATA_ERROR("ADM001"),

	/** The LOV Mapping data success. */
	LOV_MAPPING_DATA_SUCCESS("ADM002"),

	/** The unable get LOV Mapping data error. */
	LOV_MAPPING_DATA_ERROR("ADM003"),
	/**
	 * Kyc get final kyc data error response code enum.
	 */
	KYC_GET_FINAL_KYC_DATA_ERROR("KYC061"),

	/** The de invalid customer id. */
	DE_INVALID_CUSTOMER_ID("DEQ030"),

	/** The save preonboarding success. */
	SAVE_PREONBOARDING_SUCCESS("DEQ031"),

	/** The save preonboarding failure. */
	SAVE_PREONBOARDING_FAILURE("DEQ032"),

	/** The fetch customer onboarding status failure. */
	FETCH_CUSTOMER_ONBOARD_STATUS_FAILURE("DEQ033"),

	/** The fetch customer onboarding status success. */
	FETCH_CUSTOMER_ONBOARD_STATUS_SUCCESS("DEQ034"),

	/** The trading user id maxlength exceeded. */
	TRADING_USER_ID_MAXLENGTH_EXCEEDED("DEQ035"),

	/** The customer onboarding status successful. */
	GET_CUSTOMER_ONBOARDING_STATUS_SUCCESS("DEQ036"),

	/** The idealake cust failure. */
	IDEALAKE_CUST_FAILURE("DEQ037"),

	/**
	 * Kyc advisory agreement error response code enum.
	 */
	KYC_ADVISORY_AGREEMENT_ERROR("KYC062"),

	/**
	 * Kyc nominee name same as customer error response code enum.
	 */
	KYC_NOMINEE_NAME_SAME_AS_CUSTOMER_ERROR("KYC063"),
	/**
	 * Kyc guardian name same as customer error response code enum.
	 */
	KYC_GUARDIAN_NAME_SAME_AS_CUSTOMER_ERROR("KYC064"),
	/**
	 * Kyc upload cheque error response code enum.
	 */
	KYC_UPLOAD_CHEQUE_ERROR("KYC065"),

	/**
	 * available cash balance response code enum.
	 */
	GET_AVAILABLE_CASH_BALANCE("DEQ128"),
	/**
	 * initiate payin response code enum.
	 */
	INIT_PAY_IN("DEQ129"),
	/**
	 * initiate payin confirm response code enum.
	 */
	PAY_IN_CONFIRM_STATUS("DEQ130"),
	/**
	 * initiate payout response code enum.
	 */
	INIT_PAY_OUT("DEQ131"),
	/**
	 * initiate payout confirm response code enum.
	 */
	PAY_OUT_CONFIRM_STATUS("DEQ132"),
	/**
	 * get transaction history response code enum.
	 */
	TRANSACTION_HISTORY_STATUS("DEQ133"),
	/**
	 * create watchlist response code enum.
	 */
	CREATE_WATCHLIST_STATUS("DEQ134"),
	/**
	 * creation of already existing watchlist error code enum.
	 */
	USERID_PRESENT_ERROR("DEQ135"),
	/**
	 * RSA encryption error code enum.
	 */
	ENCRYPTION_ERROR("DEQ136"),
	/**
	 * RSA decryption error code enum.
	 */
	DECRYPTION_ERROR("DEQ137"),
	/**
	 * view watchlist response code enum.
	 */
	VIEW_WATCHLIST_STATUS("DEQ138"),
	/**
	 * add scrip response code enum.
	 */
	ADD_SCRIP_STATUS("DEQ139"),
	/**
	 * delete scrip response code enum.
	 */
	DELETE_SCRIP_STATUS("DEQ140"),
	/**
	 * get today's position response code enum.
	 */
	GET_TODAYS_POSITION("DEQ141"),
	/**
	 * get open position response code enum.
	 */
	GET_OPEN_POSITION("DEQ142"),

	/**
	 * Company bse nse announcement success response code enum.
	 */
	COMPANY_BSE_NSE_ANNOUNCEMENT_SUCCESS("DEQ143"),
	/**
	 * Research by stock success response code enum.
	 */
	RESEARCH_BY_STOCK_SUCCESS("DEQ144"),
	/**
	 * Corporate actions success response code enum.
	 */
	CORPORATE_ACTIONS_SUCCESS("DEQ145"),
	/**
	 * Broker recommendation success response code enum.
	 */
	BROKER_RECOMMENDATION_SUCCESS("DEQ146"),

	/**
	 * De ksec login error response code enum.
	 */
	DE_KSEC_LOGIN_ERROR("DEQ147"),
	/**
	 * De ksec login success response code enum.
	 */
	DE_KSEC_LOGIN_SUCCESS("DEQ148"),
	/**
	 * De ksec appinit fetch error response code enum.
	 */
	DE_KSEC_APPINIT_FETCH_ERROR("DEQ149"),

	/**
	 * De ksec logout success response code enum.
	 */
	DE_KSEC_LOGOUT_SUCCESS("DEQ050"),
	/**
	 * De ksec logout error response code enum.
	 */
	DE_KSEC_LOGOUT_ERROR("DEQ051"),

	/**
	 * De cmots recommended brokers success response code enum.
	 */
	DE_CMOTS_RECOMMENDED_BROKERS_SUCCESS("DEQ052"),
	/**
	 * De cmots recommended brokers error response code enum.
	 */
	DE_CMOTS_RECOMMENDED_BROKERS_ERROR("DEQ053"),

	/**
	 * De client holdings success response code enum.
	 */
	DE_CLIENT_HOLDINGS_SUCCESS("DEQ054"),

	/**
	 * De ksec login otp error response code enum.
	 */
	DE_KSEC_LOGIN_OTP_ERROR("DEQ055"),
	/**
	 * De ksec login otp success response code enum.
	 */
	DE_KSEC_LOGIN_OTP_SUCCESS("DEQ056"),
	/**
	 * Appinit info fetch successful response code enum.
	 */
	APPINIT_INFO_FETCH_SUCCESSFUL("DEQ108"),
	/**
	 * Exchange fetch success response code enum.
	 */
	EXCHANGE_FETCH_SUCCESS("DEQ157"),
	/**
	 * Kyc upload cheque success response code enum.
	 */
	KYC_UPLOAD_CHEQUE_SUCCESS("KYC066"),
	/**
	 * De cmots corporate info success response code enum.
	 */
	DE_CMOTS_CORPORATE_INFO_SUCCESS("DEQ158"),
	/**
	 * Kyc check ipv status for cust error response code enum.
	 */
	KYC_CHECK_IPV_STATUS_FOR_CUST_ERROR("KYC067"),
	/**
	 * Kyc ipv redirect success response code enum.
	 */
	KYC_IPV_REDIRECT_SUCCESS("KYC068"),
	/**
	 * Kyc ipv redirect error response code enum.
	 */
	KYC_IPV_REDIRECT_ERROR("KYC069"),
	/**
	 * De invalid trade code response code enum.
	 */
	DE_INVALID_TRADE_CODE("DEQ159"),
	/**
	 * Kyc ipv agent available response code enum.
	 */
	KYC_IPV_AGENT_AVAILABLE("KYC070"),
	/**
	 * Kyc ipv agent not available response code enum.
	 */
	KYC_IPV_AGENT_NOT_AVAILABLE("KYC071"),
	/**
	 * Kyc ipv agent error response code enum.
	 */
	KYC_IPV_AGENT_ERROR("KYC072"),
	/**
	 * Kyc ipv response save success response code enum.
	 */
	KYC_IPV_RESPONSE_SAVE_SUCCESS("KYC073"),
	/**
	 * Kyc ipv response save error response code enum.
	 */
	KYC_IPV_RESPONSE_SAVE_ERROR("KYC074"),
	/**
	 * Kyc ipv file upload success response code enum.
	 */
	KYC_IPV_FILE_UPLOAD_SUCCESS("KYC075"),
	/**
	 * Kyc ipv file upload error response code enum.
	 */
	KYC_IPV_FILE_UPLOAD_ERROR("KYC076"),
	/**
	 * External token validation success response code enum.
	 */
	EXTERNAL_TOKEN_VALIDATION_SUCCESS("EXTAPP001"),
	/**
	 * External token validation error response code enum.
	 */
	EXTERNAL_TOKEN_VALIDATION_ERROR("EXTAPP002"),
	/**
	 * External ref num validation error response code enum.
	 */
	EXTERNAL_REF_NUM_VALIDATION_ERROR("EXTAPP003"),
	/**
	 * App session timeout response code enum.
	 */
	APP_SESSION_TIMEOUT("APP039"),
	/**
	 * Fetch casupload success response code enum.
	 */
	FETCH_CASUPLOAD_SUCCESS("CAS001"),
	/**
	 * Fetch casupload failure response code enum.
	 */
	FETCH_CASUPLOAD_FAILURE("CAS002"),
	/**
	 * Kyc next signature upload call response code enum.
	 */
	KYC_NEXT_SIGNATURE_UPLOAD_CALL("KYC077"),

	/**
	 * Customer kyc readiness status success response code enum.
	 */
	CUSTOMER_KYC_READINESS_STATUS_SUCCESS("KYC078"),
	/**
	 * Customer kyc readiness status error response code enum.
	 */
	CUSTOMER_KYC_READINESS_STATUS_ERROR("KYC079"),

	/** Dashboard Enum. */
	MF_DASHBOARD_AGGRE_HOLDING_ERROR("DAS001"),
	/**
	 * Mf dashboard aggre holding success response code enum.
	 */
	MF_DASHBOARD_AGGRE_HOLDING_SUCCESS("DAS002"),
	/**
	 * Mf dashboard investment trend success response code enum.
	 */
	MF_DASHBOARD_INVESTMENT_TREND_SUCCESS("DAS003"),
	/**
	 * Mf dashboard investment trend error response code enum.
	 */
	MF_DASHBOARD_INVESTMENT_TREND_ERROR("DAS004"),
	/**
	 * Mf dashboard investment dist success response code enum.
	 */
	MF_DASHBOARD_INVESTMENT_DIST_SUCCESS("DAS005"),
	/**
	 * Mf dashboard investment dist error response code enum.
	 */
	MF_DASHBOARD_INVESTMENT_DIST_ERROR("DAS006"),
	/**
	 * Mf dashboard sip success response code enum.
	 */
	MF_DASHBOARD_SIP_SUCCESS("DAS007"),
	/**
	 * Mf dashboard sip error response code enum.
	 */
	MF_DASHBOARD_SIP_ERROR("DAS008"),
	/**
	 * Mf dashboard nudge success response code enum.
	 */
	MF_DASHBOARD_NUDGE_SUCCESS("DAS009"),
	/**
	 * Mf dashboard nudge error response code enum.
	 */
	MF_DASHBOARD_NUDGE_ERROR("DAS010"),
	/**
	 * Mf dashboard order items success response code enum.
	 */
	MF_DASHBOARD_ORDER_ITEMS_SUCCESS("DAS011"),
	/**
	 * Mf dashboard order items error response code enum.
	 */
	MF_DASHBOARD_ORDER_ITEMS_ERROR("DAS012"),
	/**
	 * Dashboard invalid request response code enum.
	 */
	DASHBOARD_INVALID_REQUEST("DAS015"),

	/** The app latest journey success. */
	APP_LATEST_JOURNEY_SUCCESS("COM014"),

	/** The app latest journey error. */
	APP_LATEST_JOURNEY_ERROR("COM015"),

	/**
	 * Wishlist absent code response code enum.
	 */
	WISHLIST_ABSENT_CODE("DEQ160"),
	/**
	 * Wishlist absent in ksec code response code enum.
	 */
	WISHLIST_ABSENT_IN_KSEC_CODE("DEQ161"),
	/**
	 * Onboarding status fetch failure code response code enum.
	 */
	ONBOARDING_STATUS_FETCH_FAILURE_CODE("DEQ162"),
	/**
	 * Dms upload document success response code enum.
	 */
	DMS_UPLOAD_DOCUMENT_SUCCESS("DMS003"),
	/**
	 * Dms upload document error response code enum.
	 */
	DMS_UPLOAD_DOCUMENT_ERROR("DMS004"),
	/**
	 * Dms download document success response code enum.
	 */
	DMS_DOWNLOAD_DOCUMENT_SUCCESS("DMS005"),
	/**
	 * Dms download document error response code enum.
	 */
	DMS_DOWNLOAD_DOCUMENT_ERROR("DMS006"),

	/** The mf dashboard calculate avg rating success. */
	MF_DASHBOARD_CALCULATE_AVG_RATING_SUCCESS("DAS013"),

	/** The mf dashboard calculate avg rating error. */
	MF_DASHBOARD_CALCULATE_AVG_RATING_ERROR("DAS014"),

	/** The kyc aadhaar xml tampered. */
	KYC_AADHAAR_XML_TAMPERED("KYC080"),

	/** The kyc not initated for customer. */
	KYC_NOT_INITATED_FOR_CUSTOMER("KYC081"),

	/** The de order place success. */
	DE_ORDER_PLACE_SUCCESS("DEQ163"),

	/**
	 * Kyc aadhar address house empty error response code enum.
	 */
	KYC_AADHAR_ADDRESS_HOUSE_EMPTY_ERROR("KYC082"),
	/** The kyc bseucc creation success. */
	KYC_BSEUCC_CREATION_SUCCESS("KYC083"),

	/** The kyc bseucc creation error. */
	KYC_BSEUCC_CREATION_ERROR("KYC084"),
	/**
	 * De order place failure response code enum.
	 */
	DE_ORDER_PLACE_FAILURE("DER029"),

	/**
	 * View popup status response code enum.
	 */
	VIEW_POPUP_STATUS("DEQ164"),

	/**
	 * Add popup status response code enum.
	 */
	ADD_POPUP_STATUS("DEQ165"),

	/** Notification success *. */
	NOTIFICATION_SUCCESS("NOTF001"),

	/** Notification invalid request *. */
	NOTIFICATION_INVALID_REQUEST("NOTF002"),

	/** Notification data not found *. */
	NOTIFICATION_NO_DATA("NOTF003"),

	/** Notification failed *. */
	NOTIFICATIONFAILED("NOTF004"),

	/**
	 * The get stock detailed error.
	 */
	GET_STOCK_DETAILED_ERROR("DER007"),

	/**
	 * The get stock detailed exception.
	 */
	GET_STOCK_DETAILED_EXCEPTION("DER008"),

	/**
	 * Appinit info fetch error code enum.
	 */
	APPINIT_INFO_FETCH_ERROR("DER001"),

	/** The appinit info fetch exception. */
	APPINIT_INFO_FETCH_EXCEPTION("DER009"),

	/**
	 * The get latest news error.
	 */
	GET_LATEST_NEWS_ERROR("DER002"),

	/** The get latest news exception. */
	GET_LATEST_NEWS_EXCEPTION("DER010"),

	/**
	 * available cash balance response code enum.
	 */
	AVL_CASH_BALANCE_ERROR("DER003"),

	/** The avl cash balance exception. */
	AVL_CASH_BALANCE_EXCEPTION("DER011"),

	/** The get stock news error. */
	GET_STOCK_NEWS_ERROR("DER004"),

	/** The get stock news exception. */
	GET_STOCK_NEWS_EXCEPTION("DER012"),

	/** The fetch instrument exception. */
	FETCH_INSTRUMENT_EXCEPTION("DER006"),

	/** The research by stock error. */
	RESEARCH_BY_STOCK_ERROR("DER013"),

	/** The research by stock exception. */
	RESEARCH_BY_STOCK_EXCEPTION("DER014"),

	/** The view popup exception. */
	VIEW_POPUP_EXCEPTION("DER050"),

	/** The add popup exception. */
	ADD_POPUP_EXCEPTION("DER049"),

	/** The corporate info error. */
	CORPORATE_INFO_ERROR("DER047"),

	/** The corporate info exception. */
	CORPORATE_INFO_EXCEPTION("DER048"),

	/** The share holding pattern error. */
	SHARE_HOLDING_PATTERN_ERROR("DER046"),

	/** The shareholding pattern success. */
	SHAREHOLDING_PATTERN_SUCCESS("DEQ166"),

	/** The shareholding pattern exception. */
	SHAREHOLDING_PATTERN_EXCEPTION("DER045"),

	/** The view watchlist exception. */
	VIEW_WATCHLIST_EXCEPTION("DER044"),

	/** The view watchlist error. */
	VIEW_WATCHLIST_ERROR("DER043"),

	/** The view recent instrument exception. */
	VIEW_RECENT_INSTRUMENT_EXCEPTION("DER042"),

	/** The index snapshot exception. */
	INDEX_SNAPSHOT_EXCEPTION("DER041"),

	/** The get price chart error. */
	GET_PRICE_CHART_ERROR("DER040"),

	/** The price chart exception. */
	PRICE_CHART_EXCEPTION("DER039"),

	/** The company bse nse announcement error. */
	COMPANY_BSE_NSE_ANNOUNCEMENT_ERROR("DER038"),

	/** The company bse nse announcement exception. */
	COMPANY_BSE_NSE_ANNOUNCEMENT_EXCEPTION("DER037"),

	/** The comp financials error. */
	COMP_FINANCIALS_ERROR("DER036"),

	/** The comp financials exception. */
	COMP_FINANCIALS_EXCEPTION("DER035"),

	/** The add scrip error. */
	ADD_SCRIP_ERROR("DER034"),

	/** The add scrip exception. */
	ADD_SCRIP_EXCEPTION("DER033"),

	/** The instrument detail exception. */
	INSTRUMENT_DETAIL_EXCEPTION("DER032"),

	/** The recommended brokers error. */
	RECOMMENDED_BROKERS_ERROR("DER031"),

	/** The recommended brokers exception. */
	RECOMMENDED_BROKERS_EXCEPTION("DER030"),

	/** The de order placement error. */
	DE_ORDER_PLACEMENT_ERROR("DER029"),

	/** The de order placement exception. */
	DE_ORDER_PLACEMENT_EXCEPTION("DER028"),

	/** The delete scrip error. */
	DELETE_SCRIP_ERROR("DER027"),

	/** The delete scrip exception. */
	DELETE_SCRIP_EXCEPTION("DER026"),

	/** The de cmots corporate actions success. */
	DE_CMOTS_CORPORATE_ACTIONS_SUCCESS("DEQ167"),

	/** The de cmots corporate actions error. */
	DE_CMOTS_CORPORATE_ACTIONS_ERROR("DER005"),

	/**
	 * Mf dashboard order items success response code enum.
	 */
	MF_DASHBOARD_INVESTMENT_TREND_BENCHMARK_SUCCESS("DAS013"),
	/**
	 * Mf dashboard order items error response code enum.
	 */
	MF_DASHBOARD_INVESTMENT_TREND_BENCHMARK_ERROR("DAS014"),
	/**
	 * Mf dashboard favorite success response code enum.
	 */
	MF_DASHBOARD_FAVORITE_SUCCESS("DAS016"),

	/**
	 * Mf dashboard favorite error response code enum.
	 */
	MF_DASHBOARD_FAVORITE_ERROR("DAS017"),

	/** The kyc next pan upload. */
	KYC_NEXT_PAN_UPLOAD("KYC085"),

	/** The fetch holding success. */
	FETCH_HOLDING_SUCCESS("DEQ168"),

	/** The fetch holding exception. */
	FETCH_HOLDING_EXCEPTION("DER025"),

	/** The init pay in error. */
	INIT_PAY_IN_ERROR("DER024"),

	/** The init pay in exception. */
	INIT_PAY_IN_EXCEPTION("DER023"),

	/** The pay in confirm error. */
	PAY_IN_CONFIRM_ERROR("DER022"),

	/** The pay in confirm exception. */
	PAY_IN_CONFIRM_EXCEPTION("DER021"),

	/** The init pay out error. */
	INIT_PAY_OUT_ERROR("DER020"),

	/** The pay out exception. */
	PAY_OUT_EXCEPTION("DER019"),

	/** The pay out confirm error. */
	PAY_OUT_CONFIRM_ERROR("DER018"),

	/** The pay out confirm exception. */
	PAY_OUT_CONFIRM_EXCEPTION("DER017"),

	/** The transaction history error. */
	TRANSACTION_HISTORY_ERROR("DER051"),

	/** The transaction history exception. */
	TRANSACTION_HISTORY_EXCEPTION("DER052"),

	/** The de ksec login invalid password. */
	DE_KSEC_LOGIN_INVALID_PASSWORD("DER053"),

	/** The de ksec login account locked. */
	DE_KSEC_LOGIN_ACCOUNT_LOCKED("DER054"),

	/** The de ksec login password expired. */
	DE_KSEC_LOGIN_PASSWORD_EXPIRED("DER055"),

	/** The ltp cached stock price success. */
	LTP_CACHED_STOCK_PRICE_SUCCESS("DEQ169"),

	/** The ltp cached stock price exception. */
	LTP_CACHED_STOCK_PRICE_EXCEPTION("DER056"),

	/** The ltp cached index success. */
	LTP_CACHED_INDEX_SUCCESS("DEQ170"),

	/** The ltp cached index failure. */
	LTP_CACHED_INDEX_FAILURE("DER058"),

	/** The ltp cached index exception. */
	LTP_CACHED_INDEX_EXCEPTION("DER059"),

	/** The ltp cached marke depth success. */
	LTP_CACHED_MARKE_DEPTH_SUCCESS("DEQ171"),

	/** The ltp cached marke depth failure. */
	LTP_CACHED_MARKE_DEPTH_FAILURE("DER060"),

	/** The ltp cached marke depth exception. */
	LTP_CACHED_MARKE_DEPTH_EXCEPTION("DER061"),

	/** The de stock basket fetch cart success. */
	DE_STOCK_BASKET_FETCH_CART_SUCCESS("DEQ172"),

	/** The de stock basket fetch cart failure. */
	DE_STOCK_BASKET_FETCH_CART_FAILURE("DER062"),

	/** The market data switch success. */
	MARKET_DATA_SWITCH_SUCCESS("DEQ173"),

	/** The market data switch exception. */
	MARKET_DATA_SWITCH_EXCEPTION("DER063"),

	/** The market status success. */
	MARKET_STATUS_SUCCESS("DEQ174"),

	/** The ksec market status success. */
	KSEC_MARKET_STATUS_SUCCESS("DEQ175"),

	/** The ksec market status failure. */
	KSEC_MARKET_STATUS_FAILURE("DER064"),

	/** The ksec market status exception. */
	KSEC_MARKET_STATUS_EXCEPTION("DER065"),

	/** The de save goal details success. */
	DE_SAVE_GOAL_DETAILS_SUCCESS("DEQ176"),

	/** The de save goal details failure. */
	DE_SAVE_GOAL_DETAILS_FAILURE("DER066"),

	/** The de stock basket add to cart success. */
	DE_STOCK_BASKET_ADD_TO_CART_SUCCESS("DEQ177"),
	/**
	 * De stock basket add to cart failure response code enum.
	 */
	DE_STOCK_BASKET_ADD_TO_CART_FAILURE("DER067"),
	/** The  Config data update success. */
	CONFIG_DATA_UPDATE_SUCCESS("ADM004"),

	/** The  Config data update failure. */
	CONFIG_DATA_UPDATE_FAILURE("ADM005"),

	/**
	 * Report fetch data success response code enum.
	 */
	REPORT_FETCH_DATA_SUCCESS("ADM006"),
	/**
	 * Report fetch data failure response code enum.
	 */
	REPORT_FETCH_DATA_FAILURE("ADM007"),
	/**
	 * Report export success response code enum.
	 */
	REPORT_EXPORT_SUCCESS("ADM008"),
	/**
	 * Report export failure response code enum.
	 */
	REPORT_EXPORT_FAILURE("ADM009"),
	/**
	 * Report bulk upload success response code enum.
	 */
	REPORT_BULK_UPLOAD_SUCCESS("ADM010"),
	/**
	 * Report bulk upload failure response code enum.
	 */
	REPORT_BULK_UPLOAD_FAILURE("ADM011"),
	/**
	 * Report data update success response code enum.
	 */
	REPORT_DATA_UPDATE_SUCCESS("ADM012"),
	/**
	 * Report data update failure response code enum.
	 */
	REPORT_DATA_UPDATE_FAILURE("ADM013"),

	/**
	 * Invalid block unblock status response code enum.
	 */
	INVALID_BLOCK_UNBLOCK_STATUS("ADM016"),
	/**
	 * Block unblock success with email response code enum.
	 */
	BLOCK_UNBLOCK_SUCCESS_WITH_EMAIL("ADM014"),
	/**
	 * Block unblock success without email response code enum.
	 */
	BLOCK_UNBLOCK_SUCCESS_WITHOUT_EMAIL("ADM017"),
	/**
	 * Block unblock update failure response code enum.
	 */
	BLOCK_UNBLOCK_UPDATE_FAILURE("ADM015"),
	/**
	 * KRA NO success response code enum.
	 */
	KRA_NO_PROCESS_SUCCESS("ADM018"),
	/**
	 * KRA NO failure response code enum.
	 */
	KRA_NO_PROCESS_FAILED("ADM019"),

	/**
	 * Recent view exception response code enum.
	 */
	RECENT_VIEW_EXCEPTION("DER068"),


	/**
	 * De stock basket edit cart success response code enum.
	 */
	DE_STOCK_BASKET_EDIT_CART_SUCCESS("DEQ178"),
	/**
	 * De stock basket edit failure response code enum.
	 */
	DE_STOCK_BASKET_EDIT_FAILURE("DER068"),

	/**
	 * De stock basket remove from cart success response code enum.
	 */
	DE_STOCK_BASKET_REMOVE_FROM_CART_SUCCESS("DEQ179"),
	/**
	 * De stock basket remove from cart failure response code enum.
	 */
	DE_STOCK_BASKET_REMOVE_FROM_CART_FAILURE("DER069"),


	/** The kyc ipv verification rejected. */
	KYC_IPV_VERIFICATION_REJECTED("KYC086"),

	/** The kyc fetch regulatory info success. */
	KYC_FETCH_REGULATORY_INFO_SUCCESS("KYC087"),

	/**
	 * Kyc fetch regulatory info error response code enum.
	 */
	KYC_FETCH_REGULATORY_INFO_ERROR("KYC088"),

	/**
	 * Dreams dashboard data success response code enum.
	 */
	DR_DASHBOARD_DATA_SUCCESS("DAS020"),
	/**
	 * Dreams dashboard data error response code enum.
	 */
	DR_DASHBOARD_DATA_ERROR("DAS021"),

	/**
	 * Overall dashboard data success response code enum.
	 */
	OV_SEC_EXP_SUCCESS("DAS022"),
	/**
	 * Overall dashboard data error response code enum.
	 */
	OV_SEC_EXP_ERROR("DAS023"),

	/**
	 * Initiate transaction success response code enum.
	 */
	INITIATE_TRANSACTION_SUCCESS("TRANS001"),
	/**
	 * Initiate transaction failure response code enum.
	 */
	INITIATE_TRANSACTION_FAILURE("TRANS002"),
	/**
	 * Initiate transaction invalid request response code enum.
	 */
	INITIATE_TRANSACTION_INVALID_REQUEST("TRANS003"),

	/**
	 * Valuefy stock basket constant fetch success response code enum.
	 */
	VALUEFY_STOCK_BASKET_CONSTANT_FETCH_SUCCESS("DEQ180"),
	/**
	 * Valuefy stock basket constant fetch failure response code enum.
	 */
	VALUEFY_STOCK_BASKET_CONSTANT_FETCH_FAILURE("DER070"),
	/**
	 * Valuefy stock basket constant fetch error response code enum.
	 */
	VALUEFY_STOCK_BASKET_CONSTANT_FETCH_ERROR("DER071"),

	/**
	 * De fetch predefined stock basket success response code enum.
	 */
	DE_FETCH_PREDEFINED_STOCK_BASKET_SUCCESS("DEQ184"),
	/**
	 * De fetch predefined stock basket failure response code enum.
	 */
	DE_FETCH_PREDEFINED_STOCK_BASKET_FAILURE("DER074"),

	/**
	 * De fetch predefined stock basket success response code enum.
	 */
	DE_FETCH_SPECIFIC_PREDEFINED_STOCK_BASKET_SUCCESS("DEQ189"),
	/**
	 * De fetch predefined stock basket failure response code enum.
	 */
	DE_FETCH_SPECIFIC_PREDEFINED_STOCK_BASKET_FAILURE("DER095"),
	/**
	 * De fetch predefined stock basket failure response code enum.
	 */
	DE_FETCH_SPECIFIC_PREDEFINED_STOCK_BASKET_EXCEPTION(Constants.DER_096),

	/**
	 * De add journey failure response code enum.
	 */
	DE_ADD_JOURNEY_FAILURE("DER075"),
	/**
	 * De add journey success response code enum.
	 */
	DE_ADD_JOURNEY_SUCCESS("DEQ185"),

	/**
	 * De view journey failure response code enum.
	 */
	DE_VIEW_JOURNEY_FAILURE("DER076"),

	/**
	 * De view journey success response code enum.
	 */
	DE_VIEW_JOURNEY_SUCCESS("DEQ186"),
	/**
	 * Login error as user blocked response code enum.
	 */
	LOGIN_ERROR_AS_USER_BLOCKED("LOG015"),

	/**
	 * De stock basket confirm cart success response code enum.
	 */
	DE_STOCK_BASKET_CONFIRM_CART_SUCCESS("DEQ187"),
	/**
	 * De stock basket confirm cart failure response code enum.
	 */
	DE_STOCK_BASKET_CONFIRM_CART_FAILURE("DER077"),

	/**
	 * Fetch stock basket name fetch success response code enum.
	 */
	FETCH_STOCK_BASKET_NAME_FETCH_SUCCESS("DEQ188"),


	/**
	 * Error saving upi details response code enum.
	 */
	ERROR_SAVING_UPI_DETAILS("PAY001"),
	/**
	 * Payment bad request response code enum.
	 */
	PAYMENT_BAD_REQUEST("PAY002"),
	/**
	 * Error vpa already exists response code enum.
	 */
	ERROR_VPA_ALREADY_EXISTS("PAY003"),
	/**
	 * Success vpa saved response code enum.
	 */
	SUCCESS_VPA_SAVED("PAY004"),
	/**
	 * Success vpa verified response code enum.
	 */
	SUCCESS_VPA_VERIFIED("PAY005"),
	/**
	 * Error verifying vpa response code enum.
	 */
	ERROR_VERIFYING_VPA("PAY007"),
	/**
	 * Invalid vpa response code enum.
	 */
	INVALID_VPA("PAY006"),
	/**
	 * Failed collect response code enum.
	 */
	FAILED_COLLECT("PAY008"),
	/**
	 * Success collect response code enum.
	 */
	SUCCESS_COLLECT("PAY009"),
	/**
	 * Success payment response code enum.
	 */
	SUCCESS_PAYMENT("PAY010"),
	/**
	 * Failed pg order response code enum.
	 */
	FAILED_PG_ORDER("PAY011"),


	/**
	 * De order book success response code enum.
	 */
	DE_ORDER_BOOK_SUCCESS("DEQ189"),
	/**
	 * De order book failure response code enum.
	 */
	DE_ORDER_BOOK_FAILURE("DER078"),
	/**
	 * De order book exception response code enum.
	 */
	DE_ORDER_BOOK_EXCEPTION(Constants.DER_096),
	/**
	 * De trade book success response code enum.
	 */
	DE_TRADE_BOOK_SUCCESS("DEQ190"),
	/**
	 * De trade book failure response code enum.
	 */
	DE_TRADE_BOOK_FAILURE("DER079"),
	/**
	 * De trade book exception response code enum.
	 */
	DE_TRADE_BOOK_EXCEPTION("DER097"),
	/**
	 * De order history success response code enum.
	 */
	DE_ORDER_HISTORY_SUCCESS("DEQ191"),
	/**
	 * De order history failure response code enum.
	 */
	DE_ORDER_HISTORY_FAILURE("DER080"),
	/**
	 * De order history exception response code enum.
	 */
	DE_ORDER_HISTORY_EXCEPTION("DER095"),

	/**
	 * Fetch transaction success response code enum.
	 */
	FETCH_TRANSACTION_SUCCESS("DEQ192"),
	/**
	 * Fetch transaction exception response code enum.
	 */
	FETCH_TRANSACTION_EXCEPTION(Constants.DER_096),
	/**
	 * Get open position failure response code enum.
	 */
	GET_OPEN_POSITION_FAILURE("DER097"),
	/**
	 * Get open position exception response code enum.
	 */
	GET_OPEN_POSITION_EXCEPTION("DER098"),
	/**
	 * Get todays position failure response code enum.
	 */
	GET_TODAYS_POSITION_FAILURE("DER099"),
	/**
	 * Get todays position exception response code enum.
	 */
	GET_TODAYS_POSITION_EXCEPTION("DER100"),


	/**
	 * Stock news Failure response code enum.
	 */
	GET_STOCK_NEWS_FAILUR_ERROR("DER090"),
	/**
	 * Latest news Failure response code enum.
	 */
	GET_LATEST_NEWS_FAILUR_ERROR("DER091"),
	/**
	 * Company wise bse nse announcement Failure response code enum.
	 */
	COMPANY_BSE_NSE_ANNOUNCEMENT_FAILURE_ERROR("DER092"),
	/**
	 * Company financials Failure response code enum.
	 */
	COMP_FINANCIALS_FAILURE_ERROR("DER093"),
	/**
	 * Price chart Failure response code enum.
	 */
	GET_PRICE_CHART_FAILURE_ERROR("DER094"),
	/**
	 * Kyc ipv session already activated error response code enum.
	 */
	KYC_IPV_SESSION_ALREADY_ACTIVATED_ERROR("KYC087"),

	/**
	 * Kyc ipv completed response code enum.
	 */
	KYC_IPV_COMPLETED("KYC088"),
	/**
	 * Dms update document error response code enum.
	 */
	DMS_UPDATE_DOCUMENT_ERROR("DMS007"),
	/**
	 * Dms update document success response code enum.
	 */
	DMS_UPDATE_DOCUMENT_SUCCESS("DMS008"),
	/**
	 * Dms delete document error response code enum.
	 */
	DMS_DELETE_DOCUMENT_ERROR("DMS009"),
	/**
	 * Dms delete document success response code enum.
	 */
	DMS_DELETE_DOCUMENT_SUCCESS("DMS010"),
	/**
	 * DASHBOARD fetch percent investment success response code enum.
	 */
	DASHBOARD_FETCH_PERCENT_INVESTMENT_SUCCESS("DAS024"),
	/**
	 * DASHBOARD fetch percent investment failure response code enum.
	 */
	DASHBOARD_FETCH_PERCENT_INVESTMENT_ERROR("DAS025"),


	/**
	 * Stock dashboard success response code enum.
	 */
	SB_DASHBOARD_AGGRE_HOLDING_SUCCESS("DAS026"),

	/**
	 * Stock dashboard failure response code enum.
	 */
	SB_DASHBOARD_AGRE_HOLDING_FAILURE("DAS027"),

	/**
	 * order cancel success.
	 */
	DE_ORDER_CANCEL_SUCCESS("DEQ193"),
	/**
	 * order cancel error.
	 */
	DE_ORDER_CANCEL_ERROR("DER101"),
	/**
	 * order cancel exception.
	 */
	DE_ORDER_CANCEL_EXCEPTION("DER102"),
	/**
	 * order modify success.
	 */
	DE_ORDER_MODIFY_SUCCESS("DEQ194"),
	/**
	 * order modify error.
	 */
	DE_ORDER_MODIFY_ERROR("DER103"),
	/**
	 * order modify exception.
	 */
	DE_ORDER_MODIFY_EXCEPTION("DER104"),
	/**
	 * Customer bank detail update success response code enum.
	 */
	CUSTOMER_BANK_DETAIL_UPDATE_SUCCESS("KYC089"),
	/**
	 * Customer bank detail update error response code enum.
	 */
	CUSTOMER_BANK_DETAIL_UPDATE_ERROR("KYC090"),

	/**
	 * Kyc next call show add nominee page response code enum.
	 */
	KYC_NEXT_CALL_SHOW_ADD_NOMINEE_PAGE("KYC091"),
	/**
	 * Kyc bank account partially added response code enum.
	 */
	KYC_BANK_ACCOUNT_PARTIALLY_ADDED("KYC092"),

	/**
	 * Mcap success response code enum.
	 */
	MCAP_SUCCESS("DEQ195"),
	/**
	 * Mcap error response code enum.
	 */
	MCAP_ERROR("DER105"),

	/**
	 * Mcap exception response code enum.
	 */
	MCAP_EXCEPTION("DER106"),
	/**
	 * The Customer app goal view success.
	 */
	/*
	 * GoalView respose code enum
	 */
	CUSTOMER_APP_GOAL_VIEW_SUCCESS("CUST003"),
	/**
	 * Customer app goal view failure response code enum.
	 */
	CUSTOMER_APP_GOAL_VIEW_FAILURE("CUST004"),
	/**
	 * The Customer app goal view update success.
	 */
	/*
	 * Goal view update response code enum
	 */
	CUSTOMER_APP_GOAL_VIEW_UPDATE_SUCCESS("CUST005"),
	/**
	 * Customer app goal view update failure response code enum.
	 */
	CUSTOMER_APP_GOAL_VIEW_UPDATE_FAILURE("CUST006"),


	/**
	 * Kyc user block due to multiple rekyc response code enum.
	 */
	KYC_USER_BLOCK_DUE_TO_MULTIPLE_REKYC("KYC093"),

	/**
	 * Listed bond success response code enum.
	 */
	LISTED_BOND_SUCCESS("DEQ196"),
	/**
	 * Listed bond error response code enum.
	 */
	LISTED_BOND_ERROR("DER106"),
	/**
	 * Listed bond exception response code enum.
	 */
	LISTED_BOND_EXCEPTION("DER107"),

	/**
	 * Sovereign gold success response code enum.
	 */
	SOVEREIGN_GOLD_SUCCESS("DEQ197"),
	/**
	 * Sovereign gold error response code enum.
	 */
	SOVEREIGN_GOLD_ERROR("DER107"),
	/**
	 * Sovereign gold exception response code enum.
	 */
	SOVEREIGN_GOLD_EXCEPTION("DER108"),

	/**
	 * Intraday Success code enum
	 */
	INTRADAY_SUCCESS("DEQ199"),
	/**
	 * Intraday error code enum
	 */
	INTRADAY_ERROR("DER110"),
	/**
	 * Intraday exception code enum
	 */
	INTRADAY_EXCEPTION("DER111"),
	/**
	 * Fetch all successfully ordered stock-basket list success response code
	 */
	DE_FETCH_STOCK_BASKET_LIST_SUCCESS("DEQ198"),
	/**
	 * Fetch all successfully ordered stock-basket list failure response code
	 */
	DE_FETCH_STOCK_BASKET_LIST_FAILURE("DER109"),
	/**
	 * Historical Success response code
	 */
	HISTORICAL_SUCCESS("DEQ200"),
	/**
	 * Historical Error response code
	 */
	HISTORICAL_ERROR("DER112"),
	/**
	 * Historical Exception response code
	 */
	HISTORICAL_EXCEPTION("DER113"),
	/**
	 * The Dashboard fetch mandates success.
	 */
	/*
	 * Dashboard fetch mandates success response code
	 */
	DASHBOARD_FETCH_MANDATES_SUCCESS("DAS028"),
	/**
	 * The Dasboard fetch mandates error.
	 */
	/*
	 * Dashboard fetch mandates error response code
	 */
	DASBOARD_FETCH_MANDATES_ERROR("DAS029"),

	/**
	 * Dashboar fetch holding success
	 */
	DEPOSIT_DASHBOARD_HOLDING_SUCCESS("DAS030"),
	/**
	 * Dashboar fetch holding error
	 */
	DEPOSIT_DASHBOARD_HOLDING_ERROR("DAS031"),
	/**
	 * Dashboar fetch transaction success
	 */
	DEPOSIT_DASHBOARD_TRANSACTION_SUCCESS("DAS031"),
	/**
	 * Dashboar fetch transaction error
	 */
	DEPOSIT_DASHBOARD_TRANSACTION_ERROR("DAS032"),
    /*
     * Dashboard fetch asset distribution success
     */
	DASHBOARD_ASSET_DISTRIBUTION_SUCCESS("DAS033"),
	/*
     * Dashboard fetch asset distribution failure
     */
	DASHBOARD_ASSET_DISTRIBUTION_FAILURE("DAS034"),
	/*
     * Dashboard fetch pending orders success
     */
	DASHBOARD_PENDING_ORDERS_SUCCESS("DAS035"),
	/*
     * Dashboard fetch pending orders failure
     */
	DASHBOARD_PENDING_ORDERS_FAILURE("DAS036"),

	/**
	 * Kyc penny drop response error response code enum.
	 */
	KYC_PENNY_DROP_RESPONSE_ERROR("KYC094"),
	/**
	 * CKYC Success response code
	 */
	CKYC_SUCCESS("KYC095"),
	/**
	 * CKYC Error response code
	 */
	CKYC_ERROR("KYC096"),
	/**
	 * CKYC Missing response code
	 */
	CKYC_MISSING_DATA("KYC099"),
	/**
	 * Kyc pin based service unavailable for first product response code enum.
	 */
	KYC_PIN_BASED_SERVICE_UNAVAILABLE_FOR_FIRST_PRODUCT("KYC097"),
	/**
	 * Kyc pin based service unavailable for second product response code enum.
	 */
	KYC_PIN_BASED_SERVICE_UNAVAILABLE_FOR_SECOND_PRODUCT("KYC098"),
	/**
	 * Kyc update nominee details success response code enum.
	 */
	KYC_UPDATE_NOMINEE_DETAILS_SUCCESS("KYC099"),
	/**
	 * Kyc update nominee details error response code enum.
	 */
	KYC_UPDATE_NOMINEE_DETAILS_ERROR("KYC102"),
	/**
	 * Kyc update nominee details not found error response code enum.
	 */
	KYC_UPDATE_NOMINEE_DETAILS_NOT_FOUND_ERROR("KYC103"),
	/**
	 * Kyc update user success enum.
	 */
	KYC_UPDATE_USER_SUCCESS("KYC100"),
	/**
	 * Kyc update user error enum.
	 */
	KYC_UPDATE_USER_ERROR("KYC101"),

	/**
	 * Kyc ipv maker status pending response code enum.
	 */
	KYC_IPV_MAKER_STATUS_PENDING("KYC104"),

	/**
	 * Kyc ipv maker status rejected response code enum.
	 */
	KYC_IPV_MAKER_STATUS_REJECTED("KYC105"),

	/**
	 * Kyc ipv maker status verified response code enum.
	 */
	KYC_IPV_MAKER_STATUS_VERIFIED("KYC106"),

	/**
	 * De fetch stock basket list limit exceeds response code enum.
	 */
	DE_FETCH_STOCK_BASKET_LIST_LIMIT_EXCEEDS("DER114"),

	/**
	 * The Crn basic details success.
	 */
//ETB CODES
	CRN_BASIC_DETAILS_SUCCESS("CRN001"),
	/**
	 * Crn basic details failure response code enum.
	 */
	CRN_BASIC_DETAILS_FAILURE("CRN002"),
	/**
	 * Crn apac fetch success response code enum.
	 */
	CRN_APAC_FETCH_SUCCESS("CAF001"),
	/**
	 * Crn apac fetch failure response code enum.
	 */
	CRN_APAC_FETCH_FAILURE("CAF002"),
	/**
	 * Account inq success response code enum.
	 */
	ACCOUNT_INQ_SUCCESS("AIN001"),
	/**
	 * Account inq failure response code enum.
	 */
	ACCOUNT_INQ_FAILURE("AIN002"),
	/**
	 * Account lien inq success response code enum.
	 */
	ACCOUNT_LIEN_INQ_SUCCESS("ALI001"),
	/**
	 * Account lien inq failure response code enum.
	 */
	ACCOUNT_LIEN_INQ_FAILURE("ALI002"),
	/**
	 * Etb eligibility success response code enum.
	 */
	ETB_ELIGIBILITY_SUCCESS("ETB001"),
	/**
	 * Etb eligibility failure response code enum.
	 */
	ETB_ELIGIBILITY_FAILURE("ETB002"),
	/**
	 * 
	 */
	ETB_ELIGIBILITY_MULTIPLE_CRN_FAILURE("ETB003"),
	/**
	 * Crn invalid type enum.
	 */
	CRN_INVALID_TYPE("CRN001"),
	/**
	 * crn invalid value enum.
	 */
	CRN_INVALID_VALUE("CRN002"),
	/**
	 * crn details saved enum.
	 */
	CRN_DETAILS_SAVED("CRN003"),
	/**
	 * crn details error enum.
	 */
	CRN_DETAILS_ERROR("CRN004"),
	/**
	 * crn invalid customer response code enum.
	 */
	CRN_INVALID_CUSTOMER("CRN005"),

	/**
	 * TD Bank authentication incomplete
	 */
	TD_BANK_AUTH_INCOMPLETE("KYC107"),


	/**
	 * Deposit Response codes
	 */
	GET_MATURITY_DETAILS_SUCCESS("DEP001"),
	/**
	 * Get maturity details failure response code enum.
	 */
	GET_MATURITY_DETAILS_FAILURE("DEP002"),
	/**
	 * Get all deposit interest rate success response code enum.
	 */
	GET_ALL_DEPOSIT_INTEREST_RATE_SUCCESS("DEP003"),
	/**
	 * Get all deposit interest rate failure response code enum.
	 */
	GET_ALL_DEPOSIT_INTEREST_RATE_FAILURE("DEP004"),
	/**
	 * Save temporary customer deposit data success response code enum.
	 */
	SAVE_TEMPORARY_CUSTOMER_DEPOSIT_DATA_SUCCESS("DEP005"),
	/**
	 * Save temporary customer deposit data failure response code enum.
	 */
	SAVE_TEMPORARY_CUSTOMER_DEPOSIT_DATA_FAILURE("DEP006"),
	/**
	 * Get master data deposits success response code enum.
	 */
	GET_MASTER_DATA_DEPOSITS_SUCCESS("DEP007"),
	/**
	 * Get master data deposits failure response code enum.
	 */
	GET_MASTER_DATA_DEPOSITS_FAILURE("DEP008"),
	/**
	 * Get temporary deposit data success response code enum.
	 */
	GET_TEMPORARY_DEPOSIT_DATA_SUCCESS("DEP009"),
	/**
	 * Get temporary deposit data failure response code enum.
	 */
	GET_TEMPORARY_DEPOSIT_DATA_FAILURE("DEP010"),
	/**
	 * Create term deposit failure response code enum.
	 */
	CREATE_TERM_DEPOSIT_FAILURE("DEP011"),
	/**
	 * Create term deposit success response code enum.
	 */
	CREATE_TERM_DEPOSIT_SUCCESS("DEP012"),
	/**
	 * Break term deposit success response code enum.
	 */
	BREAK_TERM_DEPOSIT_SUCCESS("DEP013"),
	/**
	 * Break term deposit failure response code enum.
	 */
	BREAK_TERM_DEPOSIT_FAILURE("DEP014"),
	/**
	 * Get term deposit details success response code enum.
	 */
	GET_TERM_DEPOSIT_DETAILS_SUCCESS("DEP015"),
	/**
	 * Get term deposit details failure response code enum.
	 */
	GET_TERM_DEPOSIT_DETAILS_FAILURE("DEP016"),

	/**
	 * Get existing term deposit details success response code enum.
	 */
	GET_EXISTING_TERM_DEPOSIT_DETAILS_SUCCESS("DEP017"),
	ADD_SERVICEABLE_PIN_SUCCESS("KYC092"),
	ADD_SERVICEABLE_PIN_FAILURE("KYC093"),
	
	PAN_RES_INDIAN("PAN001"),
	PAN_RES_NRI("PAN002"),
	PAN_RES_INVALID("PAN003"),

	/**
	 * Get existing term deposit details failure response code enum.
	 */
	GET_EXISTING_TERM_DEPOSIT_DETAILS_FAILURE("DEP018"),
	/**
	 * Get the term deposit default value success response code.
	 */
	GET_TD_DEFAULT_VALUE_SUCCESS("DEP020"), 
	/**
	 * Get the term deposit default value failure response code.
	 */
	GET_TD_DEFAULT_VALUE_FAILURE("DEP021"),
	/**
	 * Get temp customer id failure response code enum.
	 */
	GET_TEMP_CUSTOMER_ID_FAILURE("DEP019"),
	
	/**
	 * Get td report success.
	 */
	GET_TD_REPORT_SUCCESS("DEP022"),
	
	/**
	 * Get td report failure.
	 */
	GET_TD_REPORT_FAILURE("DEP023"),
	
	/**
	 * Get payment report success.
	 */
	GET_PAYMENT_REPORT_SUCCESS("DEP024"),
	
	/**
	 * Get payment report failure.
	 */
	GET_PAYMENT_REPORT_FAILURE("DEP025"),
	
	/**
	 * TD Report action success.
	 */
	TD_REPORT_ACTION_SUCCESS("DEP026"),
	
	/**
	 * TD Report action failure.
	 */
	TD_REPORT_ACTION_FAILURE("DEP027"),
	
	
	/**
	 * Td rejected response error response code enum.
	 */
	TD_REJECTED_RESPONSE_ERROR("KYC108"),
	/**
	 * Td product response success response code enum.
	 */
	TD_PRODUCT_RESPONSE_SUCCESS("KYC109"),
	
	/**
	 * Session data save success
	 */
    
    SESSION_DATA_SAVE_SUCCESS("COM016"),
    
    /**
     * Session data save success
     */
    
    SESSION_DATA_SAVE_FAILED("COM017"),
    
    
	/**
	 * Success code for Stock-basket intermediate stage save operation
	 */
	DE_INTERMEDIATE_STAGE_STORE_SUCCESS("DEQ201"),
	/**
	 * Failure code for Stock-basket intermediate stage save operation
	 */
	DE_INTERMEDIATE_STAGE_STORE_FAILURE("DER115"),
	/**
	 * Success code for Stock-basket intermediate stage fetch operation
	 */
	DE_INTERMEDIATE_STAGE_FETCH_SUCCESS("DEQ202"),
	/**
	 * Failure code for Stock-basket intermediate stage fetch operation
	 */
	DE_INTERMEDIATE_STAGE_FETCH_FAILURE("DER116"),
    /**
     *  Success code for ZOHO ticket count
     */
    ZOHO_TICKET_COUNT_SUCESS("ZOH001"),
    /**
     *  Failure code for ZOHO ticket count
     */
    ZOHO_TICKET_COUNT_FAILURE("ZOH002"),
    /**
     *  Success code for ZOHO authorization
     */
    ZOHO_AUTHORIZATION_SUCESS("ZOH003"),
    /**
     *  Failure code for ZOHO authorization
     */
    ZOHO_AUTHORIZATION_FAILURE("ZOH004"),
	/**
	 * Failure code for DE onboarding post activation valuefy call
	 */
	DE_ONBOARDING_POST_ACTIVATION_VALUEFY_FAILIURE("DER117"),
	/**
	 * Success code for fetching customer ieco account number for post idealake onboarding operation
	 */	
	POST_IDEALAKE_ONBOARDING_OPERATION_FETCH_CUST_IECO_ACCOUNT_SUCCESS("DEQ203"),
	/**
	 * Failure code for fetching customer ieco account number for post idealake onboarding operation
	 */
	POST_IDEALAKE_ONBOARDING_OPERATION_FETCH_CUST_IECO_ACCOUNT_FAILURE("DER118"),
	
	/**
     * DE rejected response error response code enum.
     */
    DE_REJECTED_RESPONSE_ERROR("KYC110"),
    /**
     * De product response success response code enum.
     */
    DE_PRODUCT_RESPONSE_SUCCESS("KYC111"),
    /**
     * User Partially blocked error response code enum.
     */
    USER_PARTIALLY_BLOCKED_ERROR("KYC112"),
    NCIF_CHECK_PENDING("KYC113"),
    ETB_VALIDATION_PENDING("KYC114"),
    SERVICEABLE_PIN_CHECK_PENDING("KYC115"),
    
    /**
     * SOR order history success
     */ 
    SOR_ORDER_HISTORY_SUCCESS("DEQ204"),
    
    /**
     * SOR order history failure
     */ 
    SOR_ORDER_HISTORY_FAILURE("DER119"),
    
    /**
     * SOR order history exception
     */ 
    SOR_ORDER_HISTORY_EXCEPTION("DER120"),
    
    /**
     * Scheduler LOG success
     */
    SCHEDULER_LOG_SUCESS("SCH001"),
    
    /**
     * Scheduler LOG success
     */
    SCHEDULER_LOG_FAILED("SCH002"), 
    
    /**
     * For NTB Bank Status
     */
    
    TD_RESPONSE_NTB("KYC116"),
 
    /**
     * PEnnydrop name match
     */
    PENNY_DROP_NAME_MATCHED("KYC095"),
    
    /**
     * Pennydrop name mismatch
     */
    PENNY_DROP_NAME_MISMATCHED("KYC096"),
    
    /**
     * Failed BSEUCC push job done
     */
    BSE_UCC_JOB_DONE("KYC097"),
    
    /**
     * Failed BSEUCC push job failed
     */
    BSE_UCC_JOB_FAILED("KYC098"),
    
    ESIGN_REQUEST_SUCCESS("ESGN001"),
    ESIGN_REQUEST_FAILED("ESGN002"),
    ESIGN_URL_SUCCESS("ESGN003"),
    ESIGN_URL_FAILED("ESGN004"),
    ESIGN_DOC_SUCCESS("ESGN005"),
    ESIGN_DOC_FAILED("ESGN006"),
    ESIGN_STATUS_SUCCESS("ESGN007"),
    ESIGN_STATUS_FAILED("ESGN008"),

	IPO_API_SUCCESS_STATUS("DEQ150"),
	IPO_CASE_API_FAILURE("DER123"),

	WHATSAPP_OTM_SUCCESS("OTM201"),
	WHATSAPP_OTM_FAILURE("OTM404");

	/**
	 * The value.
	 */
	private String value;

	/**
	 * Instantiates a new response code enum.
	 *
	 * @param value the value
	 */
	ResponseCodeEnum(String value) {
		this.value = value;
	}

	/**
	 * From value response code enum.
	 *
	 * @param value the value
	 * @return the response code enum
	 */
	public static ResponseCodeEnum fromValue(String value) {
		for (ResponseCodeEnum category : values()) {
			if (category.value.equalsIgnoreCase(value)) {
				return category;
			}
		}
		throw new IllegalArgumentException("Unknown enum type " + value + ", Allowed values are " + Arrays.toString(values()));
	}

	/**
	 * Gets value.
	 *
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	private static class Constants {
		public static final String DER_096 = "DER096";
	}
}