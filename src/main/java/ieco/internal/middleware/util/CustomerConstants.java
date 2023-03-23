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
package ieco.internal.middleware.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.regex.Pattern;

/**
 * The type Customer constants.
 *
 * @author "PwC"
 */
/**
 * @author rakeshm969
 *
 */
/**
 * @author rakeshm969
 *
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CustomerConstants {
	
	public static final String TEMP_CUST_ID_SESSION_CACHE_MAP = "temp_cust_id_session_cache";
	/**
	 * The constant INTERNAL_SERVER_ERROR.
	 */
	public static final String INTERNAL_SERVER_ERROR = "INTERNAL_SERVER_ERROR";
	/**
	 * The constant BAD_GATEWAY.
	 */
	public static final String BAD_GATEWAY = "BAD_GATEWAY";
	/**
	 * The constant PAN_ALREADY_EXIST.
	 */
	public static final String PAN_ALREADY_EXIST = "PAN already exist";
	/**
	 * The constant PAN_NOT_EXIST.
	 */
	public static final String PAN_NOT_EXIST = "PAN Does not exist";
	/**
	 * The constant EMAIL_ALREADY_EXIST.
	 */
	public static final String EMAIL_ALREADY_EXIST = "Email already exist";
	/**
	 * The constant EMAIL_NOT_EXIST.
	 */
	public static final String EMAIL_NOT_EXIST = "Email Does not exist";
	/**
	 * The constant CUSTOMER_SAVE_ERROR.
	 */
	public static final String CUSTOMER_SAVE_ERROR = "Could not save Customer Details";
	/**
	 * The constant CUSTOMER_SAVED_SUCCESS.
	 */
	public static final String CUSTOMER_SAVED_SUCCESS = "Customer Details Successfully Saved.";
	/**
	 * The constant TRANSACTION_ID_KEY.
	 */
	public static final String TRANSACTION_ID_KEY = "TransactionDetails";
	/**
	 * The constant SESSION_ID_KEY.
	 */
	public static final String SESSION_ID_KEY = "sessionId";

	/**
	 * The constant APP_SESSION_KEY.
	 */
	public static final String APP_SESSION_KEY = "deviceInfo";
	/**
	 * The constant CHAR_YES.
	 */
	public static final Character CHAR_YES = 'Y';
	/**
	 * The constant CHAR_NO.
	 */
	public static final Character CHAR_NO = 'N';
	/**
	 * The constant PAN_MUST_NOT_BLANK.
	 */
	public static final String PAN_MUST_NOT_BLANK = "PAN must not be blank";

	/**
	 * The constant TID_MUST_NOT_BLANK.
	 */
	public static final String TID_MUST_NOT_BLANK = "TransactionID is empty";
	/**
	 * The constant MOBILE_MUST_NOT_BLANK.
	 */
	public static final String MOBILE_MUST_NOT_BLANK = "Mobile Number must not be blank";
	/**
	 * The constant EMAIL_MUST_NOT_BLANK.
	 */
	public static final String EMAIL_MUST_NOT_BLANK = "customerId must not be blank";
	/**
	 * The constant SUCCESSFUL_LOG_IN.
	 */
	public static final String SUCCESSFUL_LOG_IN = "Customer Successfully logged in";
	/**
	 * The constant CHANGE_PASSWORD_SUCCESS.
	 */
	public static final String CHANGE_SECRET_SUCCESS = "Password has been Successfully Changed.";
	/**
	 * The constant CHANGE_PASSWORD_FAILURE.
	 */
	public static final String CHANGE_SECRET_FAILURE = "Error occurred while changing password, Error: %s";
	/**
	 * The constant CHANGE_PASSWORD_MATCH_ERROR.
	 */
	public static final String CHANGE_SECRET_MATCH_ERROR = "Old password provided is not matching with current password";
	/**
	 * The constant UNSUCCESSFUL_LOG_IN.
	 */
	public static final String UNSUCCESSFUL_LOG_IN = "Customer Could not be logged in";
	/**
	 * The constant INVALID_TRANSACTION_ID.
	 */
	public static final String INVALID_TRANSACTION_ID = "TransactionId is not Correct";
	/**
	 * The constant EMAIL_OTP_VERIFIED.
	 */
	public static final String EMAIL_OTP_VERIFIED = "Email OTP is verified";
	/**
	 * The constant MOBILE_OTP_VERIFIED.
	 */
	public static final String MOBILE_OTP_VERIFIED = "MOBILE OTP is verified.An otp is sent to your registered emailID";
	/**
	 * The constant INVALID_OTP_VERIFICATION.
	 */
	public static final String INVALID_OTP_VERIFICATION = "Could not verify the otp";
	/**
	 * The constant SET_PASSWORD_FAILURE.
	 */
	public static final String SET_SECRET_FAILURE = "Password could no be saved";
	/**
	 * The constant LOCAL_EMAIL_CHECK_FAILURE.
	 */
	public static final String LOCAL_EMAIL_CHECK_FAILURE = "Could not check the email availability";
	/**
	 * The constant LOCAL_PAN_CHECK_FAILURE.
	 */
	public static final String LOCAL_PAN_CHECK_FAILURE = "Could not check the pan availability";
	/**
	 * The constant NO_PASSWORD_HISTORY_FOUND_ERROR.
	 */
	public static final String NO_SECRET_HISTORY_FOUND_ERROR = "No Password history found";
	/**
	 * The constant APPLICATION_INITIATE_SUCCESS.
	 */
	public static final String APPLICATION_INITIATE_SUCCESS = " Application Session Initiated Successfully";
	/**
	 * The constant APPLICATION_INITIATE_FAILURE.
	 */
	public static final String APPLICATION_INITIATE_FAILURE = " Application Session Initiation Unsuccessful %s";
	/**
	 * The constant MOBILE_OTP_SENT_SUCCESS.
	 */
	public static final String MOBILE_OTP_SENT_SUCCESS = "An otp is sent to your registered mobile number ";
	/**
	 * The constant CUSTOMER_NOT_FOUND.
	 */
	public static final String CUSTOMER_NOT_FOUND = "No Customer found with given details";
	/**
	 * The constant BOTH_OTP_VERIFIED.
	 */
	public static final String BOTH_OTP_VERIFIED = "Both email and mobile otp is verified.";
	/**
	 * The constant RESET_PASSWORD_SUCCESS.
	 */
	public static final String RESET_SECRET_SUCCESS = "Password has been successfully reset.";
	/**
	 * The constant RESET_PASSWORD_FAILURE.
	 */
	public static final String RESET_SECRET_FAILURE = "Password reset unsuccessful.";

	/**
	 * The constant CUSTOMER_SAVED.
	 */
	public static final String CUSTOMER_SAVED = "Customer Saved Successfully";

	/**
	 * The constant FIELD_ERROR.
	 */
	public static final String FIELD_ERROR = "One or more fields have invalid data";
	/**
	 * The constant TRANS_TYPE_MASTER_KEY.
	 */
	public static final String TRANS_TYPE_MASTER_KEY = "TRANS_TYPE_MASTER";

	/**
	 * The constant MAIN_REPORT_NAME.
	 */
	public static final String MAIN_REPORT_NAME = "AwardNominationReport.jrxml";
	/**
	 * The constant AWARD_NOMINATION_REPORT_NAME.
	 */
	public static final String AWARD_NOMINATION_REPORT_NAME = "AwardNominationReport.jrxml";
	/**
	 * The constant LEADERSHIP_TEAM_SUBREPORT_NAME.
	 */
	public static final String LEADERSHIP_TEAM_SUBREPORT_NAME = "LeadershipTeamSubreport.jrxml";
	/**
	 * The constant TEAM_MEMBERS_SUBREPORT_NAME.
	 */
	public static final String TEAM_MEMBERS_SUBREPORT_NAME = "TeamMembersSubreport.jrxml";
	/**
	 * The constant MAIN_REPORT_DIRECTORY.
	 */
	public static final String MAIN_REPORT_DIRECTORY = "/report/";
	/**
	 * The constant COMPILED_REPORT_DIRECTORY.
	 */
	public static final String COMPILED_REPORT_DIRECTORY = "/tmp";
	/**
	 * The constant MAIN_REPORT.
	 */
	public static final String MAIN_REPORT = "/report/AwardNominationReport.jrxml";
	/**
	 * The constant SUBREPORT_DIRECTORY.
	 */
	public static final String SUBREPORT_DIRECTORY = "/tmp/";
	/**
	 * The constant SUBREPORT_DIRECTORY_KEY.
	 */
	public static final String SUBREPORT_DIRECTORY_KEY = "SUBREPORT_DIR";
	/**
	 * The constant LOGO_DIRECTORY.
	 */
	public static final String LOGO_DIRECTORY = "logo/";
	/**
	 * The constant LOGO_DIRECTORY_KEY.
	 */
	public static final String LOGO_DIRECTORY_KEY = "LOGO_DIR";

	/**
	 * The constant CVL_KRA_PREVIEW_FORM_GENERATED.
	 */
	public static final String CVL_KRA_PREVIEW_FORM_GENERATED = "PDF Content Generated";
	/**
	 * The constant TRANSACTION_RESPONSE_ID.
	 */
	public static final String TRANSACTION_RESPONSE_ID = "transactionId";

	/**
	 * The constant MOTHER_NAME_MUST_NOT_BLANK.
	 */
	public static final String MOTHER_NAME_MUST_NOT_BLANK = "Mother's name must not be blank";

	/**
	 * The constant KYC_DETAILS_SAVED_SUCCESS.
	 */
	public static final String KYC_DETAILS_SAVED_SUCCESS = "KYC Details Successfully Saved";

	/**
	 * The constant CVL_KRA_STAT_MODIFIED.
	 */
	public static final String CVL_KRA_STAT_MODIFIED = "MODIFICATION";

	/**
	 * The constant CVL_KRA_STAT_FRESH.
	 */
	public static final String CVL_KRA_STAT_FRESH = "FRESH";

	/**
	 * The constant CVL_KRA_STAT_NO_RESPONSE.
	 */
	public static final String CVL_KRA_STAT_NO_RESPONSE = "";

	/**
	 * The constant XML_DATE_ERROR.
	 */
	public static final String XML_DATE_ERROR = "Generated xml can not be more than 3 days old";

	/**
	 * The constant AADHAR_DETAILS_SAVED_SUCCESS.
	 */
	public static final String AADHAR_DETAILS_SAVED_SUCCESS = "Aadhar Details Successfully Saved";

	/**
	 * The constant FUZZY_LOGIC.
	 */
	public static final String FUZZY_LOGIC = "Aadhar name is not matching ";

	/**
	 * The constant AADHAR_MISSING_FIELD.
	 */
	public static final String AADHAR_MISSING_FIELD = "Name or Address or pincode is missing ";

	/**
	 * The constant TRANSACTION_SAVE_ERROR.
	 */
	public static final String TRANSACTION_SAVE_ERROR = "Transaction Details could not be saved";

	/**
	 * The constant OTP_CALL_FAILED.
	 */
	public static final String OTP_CALL_FAILED = "OTP call was unsuccessful";

	/**
	 * The constant OTP_GENERATION.
	 */
	public static final String OTP_GENERATION = "generate";

	/**
	 * The constant OTP_VERIFY.
	 */
	public static final String OTP_VERIFY = "verify";

	/**
	 * The constant IS_ACTIVE_Y.
	 */
	public static final String IS_ACTIVE_Y = "Y";
	
	public static final String IS_ACTIVE_E = "E";
	
	public static final String IS_ACTIVE_P = "P";

	/**
	 * The constant CHECK_TYPE_MASTER_KEY.
	 */
	public static final String CHECK_TYPE_MASTER_KEY = "CHECK_TYPE_MASTER";

	/**
	 * The constant IS_ACTIVE_N.
	 */
	public static final String IS_ACTIVE_N = "N";

	/**
	 * The constant BCIF_DEDUPE_CHECK_SUCCESS.
	 */
	public static final String BCIF_DEDUPE_CHECK_SUCCESS = "Dedupe Check Success";

	/**
	 * The constant BCIF_DEDUPE_CHECK_FAILURE.
	 */
	public static final String BCIF_DEDUPE_CHECK_FAILURE = "BCIF Dedupe Check Failure";
	/**
	 * The constant KSEC_DEDUPE_CHECK_FAILURE.
	 */
	public static final String KSEC_DEDUPE_CHECK_FAILURE = "KSEC Dedupe Check Failure";

	/**
	 * The constant NCIF_DEDUPE_CHECK_FAILURE.
	 */
	public static final String NCIF_DEDUPE_CHECK_FAILURE = "NCIF Dedupe Check Failure";

	/**
	 * The constant NSDL_DEDUPE_CHECK_FAILURE.
	 */
	public static final String NSDL_DEDUPE_CHECK_FAILURE = "NSDL Dedupe Check Failure";

	/**
	 * The constant XML_DATE_SUCCESS.
	 */
	public static final String XML_DATE_SUCCESS = "Generated xml date is valid";

	/**
	 * The constant NAME_MISMATCH.
	 */
	public static final String NAME_MISMATCH = "Provided name is not correct";

	/**
	 * The constant SHARECODE_MUST_NOT_BLANK.
	 */
	public static final String SHARECODE_MUST_NOT_BLANK = "Share Code must not be blank";

	/**
	 * The constant REGISTRATION_NOT_VERIFIED.
	 */
	public static final String REGISTRATION_NOT_VERIFIED = "Please verify the registration";

	/**
	 * The constant REGISTRATION_SUCCESSFUL.
	 */
	public static final String REGISTRATION_SUCCESSFUL = "Customer Successfully registered";

	/**
	 * The constant INVALID_EMAIL.
	 */
	public static final String INVALID_EMAIL = "EmailID is invalid";
	
	/**
	 * The constant INVALID_MOBILE.
	 */
	public static final String INVALID_MOBILE = "Mobile Number is invalid";

	/**
	 * The constant CUSTOMER_CONSENT_SUCCESSFULLY.
	 */
	public static final String CUSTOMER_CONSENT_SUCCESSFULLY = "Customer Consent Successfully Saved";

	/**
	 * The constant CUSTOMER_CONSENT_FAILURE.
	 */
	public static final String CUSTOMER_CONSENT_FAILURE = "Customer Consent transaction failed";

	/**
	 * The constant CUSTOMER_CONSENT_INVALID_TYPE.
	 */
	public static final String CUSTOMER_CONSENT_INVALID_TYPE = "Invalid consent type";

	/**
	 * The constant CUSTOMER_CONSENT_INVALID_VALUE.
	 */
	public static final String CUSTOMER_CONSENT_INVALID_VALUE = "Invalid consent value, consent value should be 'Yes' or 'No'";

	/**
	 * The constant CUSTOMER_CONSENT_INVALID_CUSTOMER.
	 */
	public static final String CUSTOMER_CONSENT_INVALID_CUSTOMER = "Customer does not exist, please provide correct value";

	/**
	 * The constant ADVISORY_AGREEMENT_CONSENT_KEY.
	 */
	public static final String ADVISORY_AGREEMENT_CONSENT_KEY = "ADVISORY_AGREEMENT_CONSENT";

	/**
	 * The constant BCIF_PAN.
	 */
	public static final String BCIF_PAN = "BCIF_PAN";

	/**
	 * The constant KSEC_PAN.
	 */
	public static final String KSEC_PAN = "KSEC_PAN";

	/**
	 * The constant NCIF_PAN_DOB.
	 */
	public static final String NCIF_PAN_DOB = "NCIF_PAN_DOB";

	/**
	 * The constant NCIF_FNAME_DOB.
	 */
	public static final String NCIF_FNAME_DOB = "NCIF_FNAME_DOB";

	/**
	 * The constant NSDL_PAN.
	 */
	public static final String NSDL_PAN = "NSDL_PAN";

	/**
	 * The constant PAN_DEDUPE.
	 */
	public static final String PAN_DEDUPE = "PAN_DEDUPE";

	/**
	 * The constant SUCCESS.
	 */
	public static final String SUCCESS = "SUCCESS";

	/**
	 * The constant FAILURE.
	 */
	public static final String FAILURE = "FAILURE";

	/**
	 * The constant NCIF_TECH_FAILURE.
	 */
	public static final String NCIF_TECH_FAILURE = "NCIF_TECH_FAILURE";

	/**
	 * The constant NCIF_PROBABALE_MATCH.
	 */
	public static final String NCIF_PROBABALE_MATCH = "NCIF_PROBABALE_MATCH";

	/**
	 * The constant INVALID_REQUEST.
	 */
	public static final String INVALID_REQUEST = "Invalid request";

	/**
	 * The constant CVL_KRA_STATUS_MAP_KEY.
	 */
	public static final String CVL_KRA_STATUS_MAP_KEY = "CVLKRAKYC";

	/**
	 * The constant KIAL_KYC_STATUS_MAP_KEY.
	 */
	public static final String KIAL_KYC_STATUS_MAP_KEY = "KIALKYC";

	/**
	 * The constant KYC_SKIP_CHECK_FURTHER_MAP_VALUE.
	 */
	public static final String KYC_SKIP_CHECK_FURTHER_MAP_VALUE = "SKIP_CHECK_FURTHER";

	/**
	 * The constant MOBILE_NUMBER_MASK_REGEX.
	 */
	public static final String MOBILE_NUMBER_MASK_REGEX = "(?<=.{2}).(?=.{2})";

	/** The Constant MOBILE_NUMBER_MASK_REGEX_PATTERN. */
	public static final Pattern MOBILE_NUMBER_MASK_REGEX_PATTERN = Pattern.compile(MOBILE_NUMBER_MASK_REGEX);
	/**
	 * The constant KYC_PAN_UPDATE_STATUS_MODIFICATION_UNDER_PROCESS.
	 */
	public static final String KYC_PAN_UPDATE_STATUS_MODIFICATION_UNDER_PROCESS = "Modification Under Process";

	/**
	 * The constant KYC_PAN_UPDATE_STATUS_MODIFICATION_REGISTERED.
	 */
	public static final String KYC_PAN_UPDATE_STATUS_MODIFICATION_REGISTERED = "Modification Registered";

	/**
	 * The constant KYC_PAN_UPDATE_STATUS_MODIFICATION_HOLD.
	 */
	public static final String KYC_PAN_UPDATE_STATUS_MODIFICATION_HOLD = "Modification Hold";

	/**
	 * The constant KYC_PAN_UPDATE_STATUS_MODIFICATION_REJECTED.
	 */
	public static final String KYC_PAN_UPDATE_STATUS_MODIFICATION_REJECTED = "Modification Rejected";

	/**
	 * The constant KYC_PAN_UPDATE_STATUS_NOT_AVAILABLE.
	 */
	public static final String KYC_PAN_UPDATE_STATUS_NOT_AVAILABLE = "Not available";

	/**
	 * The constant KYC_PAN_KYC_MODE_NORMAL_KYC.
	 */
	public static final String KYC_PAN_KYC_MODE_NORMAL_KYC = "Normal KYC";

	/**
	 * The constant KYC_PAN_KYC_MODE_EKYC_OTP.
	 */
	public static final String KYC_PAN_KYC_MODE_EKYC_OTP = "e-KYC with OTP";

	/**
	 * The constant KYC_PAN_KYC_MODE_EKYC_BIOMETRIC.
	 */
	public static final String KYC_PAN_KYC_MODE_EKYC_BIOMETRIC = "e-KYC with Biometric";

	/**
	 * The constant KYC_PAN_APP_STATUS_NOT_CHECKED_WITH_RESPECTIVE_KRA.
	 */
	public static final String KYC_PAN_APP_STATUS_NOT_CHECKED_WITH_RESPECTIVE_KRA = "Not Checked with respective KRA";

	/**
	 * The constant KYC_PAN_APP_STATUS_SUBMITTED.
	 */
	public static final String KYC_PAN_APP_STATUS_SUBMITTED = "Submitted";

	/**
	 * The constant KYC_PAN_APP_STATUS_KRA_VERIFIED.
	 */
	public static final String KYC_PAN_APP_STATUS_KRA_VERIFIED = "KRA Verified";

	/**
	 * The constant KYC_PAN_APP_STATUS_KRA_HOLD.
	 */
	public static final String KYC_PAN_APP_STATUS_KRA_HOLD = "Hold";

	/**
	 * The constant KYC_PAN_APP_STATUS_KRA_REJECTED.
	 */
	public static final String KYC_PAN_APP_STATUS_KRA_REJECTED = "Rejected";

	/**
	 * The constant KYC_PAN_APP_STATUS_KRA_NOT_AVAILABLE.
	 */
	public static final String KYC_PAN_APP_STATUS_KRA_NOT_AVAILABLE = "Not available";

	/**
	 * The constant KYC_PAN_APP_STATUS_KRA_DEACTIVATED.
	 */
	public static final String KYC_PAN_APP_STATUS_KRA_DEACTIVATED = "Deactivated";

	/**
	 * The constant KYC_PAN_APP_STATUS_KRA_EXISTING_KYC_SUBMITTED.
	 */
	public static final String KYC_PAN_APP_STATUS_KRA_EXISTING_KYC_SUBMITTED = "Existing KYC Submitted";

	/**
	 * The constant KYC_PAN_APP_STATUS_KRA_EXISTING_KYC_VERIFIED.
	 */
	public static final String KYC_PAN_APP_STATUS_KRA_EXISTING_KYC_VERIFIED = "Existing KYC Verified";

	/**
	 * The constant KYC_PAN_APP_STATUS_KRA_EXISTING_KYC_HOLD.
	 */
	public static final String KYC_PAN_APP_STATUS_KRA_EXISTING_KYC_HOLD = "Existing KYC hold";

	/**
	 * The constant KYC_PAN_APP_STATUS_KRA_EXISTING_KYC_REJECTED.
	 */
	public static final String KYC_PAN_APP_STATUS_KRA_EXISTING_KYC_REJECTED = "Existing KYC Rejected";

	/**
	 * The constant KYC_PAN_APP_STATUS_KRA_KYC_REGISTERED_WITH_CVLMF.
	 */
	public static final String KYC_PAN_APP_STATUS_KRA_KYC_REGISTERED_WITH_CVLMF = "KYC REGISTERED WITH CVLMF";

	/**
	 * The constant KYC_PAN_APP_STATUS_KRA_NOT_CHECKED_WITH_MULTIPLE_KRA.
	 */
	public static final String KYC_PAN_APP_STATUS_KRA_NOT_CHECKED_WITH_MULTIPLE_KRA = "Not Checked with Multiple KRA";

	/**
	 * The constant KYC_PAN_APP_STATUS_KRA_INVALID_PAN_FORMAT.
	 */
	public static final String KYC_PAN_APP_STATUS_KRA_INVALID_PAN_FORMAT = "Invalid PAN NO Format";

	/**
	 * The constant MF_KYC.
	 */
	public static final String MF_KYC = "MF_KYC";

	/**
	 * The constant KYC_INIT.
	 */
	public static final String KYC_INIT = "KYC_INIT";

	/**
	 * The constant MF_BLOCK_STATUS.
	 */
	public static final String MF_ALLOWED_STATUS = "mfAllowed";

	/**
	 * The constant DE_BLOCK_STATUS.
	 */
	public static final String DE_ALLOWED_STATUS = "deAllowed";

	/**
	 * The constant BP_BLOCK_STATUS.
	 */
	public static final String BP_ALLOWED_STATUS = "bpAllowed";

	/**
	 * The constant START.
	 */
	public static final String START = "START";

	/**
	 * The constant RETRY_TIME_EXCEED.
	 */
	public static final String RETRY_TIME_EXCEED = "SOLICIT_KRA_RETRY_TIME_EXCEED";

	/**
	 * The constant NO.
	 */
	public static final String NO = "NO";

	/**
	 * The constant KIAL_KYC.
	 */
	public static final String KIAL_KYC = "KIAL_KYC";

	/**
	 * The constant KRA_UPLOAD.
	 */
	public static final String KRA_UPLOAD = "KRA_UPLOAD";

	/**
	 * The constant YES.
	 */
	public static final String YES = "YES";

	/**
	 * The constant AADHAAR_EXTRACTION.
	 */
	public static final String AADHAAR_EXTRACTION = "AADHAAR_EXTRACTION";

	/**
	 * The constant COMPLETE.
	 */
	public static final String COMPLETE = "COMPLETE";

	/**
	 * The constant SUBMITTED.
	 */
	public static final String SUBMITTED = "SUBMITTED";

	/**
	 * The constant MODIFICATION_SUBMITTED.
	 */
	public static final String MODIFICATION_SUBMITTED = "MODIFICATION_SUBMITTED";

	/**
	 * The constant EMAIL_CHECK.
	 */
	public static final String EMAIL_CHECK = "IS_EMAIL_AADHAR_VERIFIED";

	/**
	 * The constant MOBILE_CHECK.
	 */
	public static final String MOBILE_CHECK = "IS_MOBILE_AADHAR_VERIFIED";

	/**
	 * The constant INCOMPLETE_ADDRESS.
	 */
	public static final String INCOMPLETE_ADDRESS = "INCOMPLETE_ADDRESS";

	/**
	 * The constant KYC_SKIP_CHECK_FURTHER_MAP_VALUE_WITH_EXPIRE_CHECK.
	 */
	public static final String KYC_SKIP_CHECK_FURTHER_MAP_VALUE_WITH_EXPIRE_CHECK = "POI_EXPIRE_CHECK";

	/**
	 * The constant CVL_KRA_STAT_CONDITIONAL.
	 */
	public static final String CVL_KRA_STAT_CONDITIONAL = "CVL_KRA_STAT_CONDITIONAL";

	/**
	 * The constant KIAL_KRA_STAT_INITIATED.
	 */
	public static final String KIAL_KRA_STAT_INITIATED = "INITIATED";

	/**
	 * The constant KIAL_KRA_STAT_NOT_INITIATED.
	 */
	public static final String KIAL_KRA_STAT_NOT_INITIATED = "NOT_INITIATED";

	/**
	 * The constant KIAL_KRA_STAT_REINITIATED.
	 */
	public static final String KIAL_KRA_STAT_REINITIATED = "REINITIATED";

	/**
	 * The constant KRA_UPLOAD_STATUS_NA.
	 */
	public static final String KRA_UPLOAD_STATUS_NA = "NA";

	/**
	 * The constant KIAL_KRA_STAT_VERIFIED.
	 */
	public static final String KIAL_KRA_STAT_VERIFIED = "VERIFIED";

	/**
	 * The constant KIAL_KRA_STAT_MODIFICATION_REINITIATED.
	 */

	/**
	 * The constant CVL_KRA_STAT_WAIT.
	 */
	public static final String CVL_KRA_STAT_WAIT = "WAIT";

	/**
	 * The constant TOKEN_KEY.
	 */
	public static final String TOKEN_KEY = "token";

	/**
	 * The constant USER_DETAILS_KEY.
	 */
	public static final String USER_DETAILS_KEY = "userDetails";

	/**
	 * The constant CUSTOMER_CHECK_SUCCESS.
	 */
	public static final String CUSTOMER_CHECK_SUCCESS = "CUSTOMER_CHECK_SUCCESS";

	/**
	 * The constant CUSTOMER_CHECK_FAIL.
	 */
	public static final String CUSTOMER_CHECK_FAIL = "CUSTOMER_CHECK_FAIL";

	/**
	 * The constant SOURCE_TYPE_MASTER_KEY.
	 */
	public static final String SOURCE_TYPE_MASTER_KEY = "SOURCE_TYPE_MASTER";

	/**
	 * The constant STAGE_MASTER_KEY.
	 */
	public static final String STAGE_MASTER_KEY = "STAGE_MASTER";

	/**
	 * The constant FEATURE_MASTER_KEY.
	 */
	public static final String FEATURE_MASTER_KEY = "FEATURE_MASTER";

	/**
	 * The constant CUST_ID_KEY.
	 */
	public static final String CUST_ID_KEY = "customerID";

	/**
	 * The constant KYC_DATA_FETCH_SUCCESS.
	 */
	public static final String KYC_DATA_FETCH_SUCCESS = "KYC data succesfully retreived";
	/**
	 * The constant KYC_DATA_FETCH_FAILURE.
	 */
	public static final String KYC_DATA_FETCH_FAILURE = "KYC data could not be retreived";

	/**
	 * The constant KYC_STATUS_SUBMITTED.
	 */
	public static final String KYC_STATUS_SUBMITTED = "SUBMITTED";

	/**
	 * The constant KYC_STATUS_RESUBMITTED.
	 */
	public static final String KYC_STATUS_RESUBMITTED = "RESUBMITTED";

	/**
	 * The constant KYC_STATUS_SUBMITTED_ERR.
	 */
	public static final String KYC_STATUS_SUBMITTED_ERR = "SUBMITTED_ERR";

	/**
	 * The constant KYC_STATUS_MODIFICATION_SUBMITTED.
	 */
	public static final String KYC_STATUS_MODIFICATION_SUBMITTED = "MODIFICATION_SUBMITTED";

	/**
	 * The constant KYC_STATUS_MODIFICATION_RESUBMITTED.
	 */
	public static final String KYC_STATUS_MODIFICATION_RESUBMITTED = "MODIFICATION_RESUBMITTED";

	/**
	 * The constant KYC_STATUS_SUBMIT_READY.
	 */
	public static final String KYC_STATUS_SUBMIT_READY = "SUBMIT_READY";

	/**
	 * The constant KYC_STATUS_WAIT.
	 */
	public static final String KYC_STATUS_WAIT = "WAIT";

	/**
	 * The constant KYC_STATUS_VERIFIED.
	 */
	public static final String KYC_STATUS_VERIFIED = "VERIFIED";

	/**
	 * The constant KYC_STATUS_ON_HOLD.
	 */
	public static final String KYC_STATUS_ON_HOLD = "ON_HOLD";

	/**
	 * The constant KYC_STATUS_REJECTED.
	 */
	public static final String KYC_STATUS_REJECTED = "REJECTED";

	/**
	 * The constant KYC_STATUS_MODIFICATION_REJECTED.
	 */
	public static final String KYC_STATUS_MODIFICATION_REJECTED = "MODIFICATION_REJECTED";

	/**
	 * The constant KYC_STATUS_BLOCKED.
	 */
	public static final String KYC_STATUS_BLOCKED = "BLOCKED";

	/**
	 * The constant KYC_STATUS_MODIFICATION_REGISTERED.
	 */
	public static final String KYC_STATUS_MODIFICATION_REGISTERED = "MODIFICATION_REGISTERED";

	/**
	 * The constant PUSH_KRA_JOB_SUCCESS.
	 */
	public static final String PUSH_KRA_JOB_SUCCESS = "Push KRA job successfully done";
	/**
	 * The constant PUSH_KRA_JOB_FAILED.
	 */
	public static final String PUSH_KRA_JOB_FAILED = "Push KRA job failed";

	/**
	 * The constant KYC_JOB_SUCCESS.
	 */
	public static final String KYC_JOB_SUCCESS = "KYC job successfully done";
	/**
	 * The constant KYC_JOB_FAILED.
	 */
	public static final String KYC_JOB_FAILED = "KYC job failed";

	/**
	 * The constant INVALID_PUSH_TYPE.
	 */
	public static final String INVALID_PUSH_TYPE = "Push type value is not Correct";

	/**
	 * The constant KRA_KYC.
	 */
	public static final String KRA_KYC = "KRA_KYC";

	/**
	 * The constant KRA_PUSH_TYPE_FRESH.
	 */
	public static final String KRA_PUSH_TYPE_FRESH = "01";

	/**
	 * The constant KRA_PUSH_TYPE_MODIFICATION.
	 */
	public static final String KRA_PUSH_TYPE_MODIFICATION = "02";

	/**
	 * The constant IPV_STATUS.
	 */
	public static final String IPV_STATUS = "IPV_STATUS";

	/**
	 * The constant PERSONAL_KYC_INFO.
	 */
	public static final String PERSONAL_KYC_INFO = "PERSONAL_KYC_INFO";
	/**
	 * The constant ADD_NOMINEE.
	 */
	public static final String ADD_NOMINEE = "ADD_NOMINEE";
	/**
	 * The constant NOT_REQUIRED.
	 */
	public static final String NOT_REQUIRED = "NOT_REQUIRED";
	/**
	 * The constant KYC_ROUTE.
	 */
	public static final String KYC_ROUTE = "KYC_ROUTE";
	/**
	 * The constant CVL_KRA_ROUTE_STAT.
	 */
	public static final String CVL_KRA_ROUTE_STAT = "CVL_KRA";
	/**
	 * The constant AADHAR_ROUTE_STAT.
	 */
	public static final String AADHAR_ROUTE_STAT = "AADHAR";
	/**
	 * The constant ADD_LINK_BANK_ACCOUNT.
	 */
	public static final String ADD_LINK_BANK_ACCOUNT = "ADD_LINK_BANK_ACCOUNT";
	/**
	 * The constant REQUIRED.
	 */
	public static final String REQUIRED = "REQUIRED";
	/**
	 * The constant INCOMPLETE.
	 */
	public static final String INCOMPLETE = "INCOMPLETE";

	/**
	 * The constant APP_STAGE_ERROR.
	 */
	public static final String APP_STAGE_ERROR = "Could not save Customer Stage Details";
	/**
	 * The constant MISSING_REQUIRED_FIELDS_PLEASE_CHECK.
	 */
	public static final String MISSING_REQUIRED_FIELDS_PLEASE_CHECK = "Missing Required Fields. Please check";

	/**
	 * The constant ERROR_MASTER_KEY.
	 */
	public static final String ERROR_MASTER_KEY = "ERROR_MASTER";

	/**
	 * The constant ERRORS_ALL_MASTER_DATA_KEY.
	 */
	public static final String ERRORS_ALL_MASTER_DATA_KEY = "ERRORS_ALL_MASTER_DATA_KEY";

	/**
	 * The constant WEALTHFY_DATE_FORMAT.
	 */
	public static final String WEALTHFY_DATE_FORMAT = "yyyy-MM-dd";

	/**
	 * The constant ACCOUNT_TYPE.
	 */
	public static final String ACCOUNT_TYPE = "SINGLE";

	/**
	 * The constant EQUITY_ONBOARDING.
	 */
	public static final String EQUITY_ONBOARDING = "EQUITY_ONBOARDING";

	/**
	 * The constant EXISTING_APPLICATION.
	 */
	public static final String EXISTING_APPLICATION = "EXISTING_APPLICATION";

	/**
	 * The constant VERFICATION_IN_PROGRESS.
	 */
	public static final String VERFICATION_IN_PROGRESS = "VERFICATION_IN_PROGRESS";

	/**
	 * The constant REJECTED.
	 */
	public static final String REJECTED = "REJECTED";

	/**
	 * The constant APPROVED.
	 */
	public static final String APPROVED = "APPROVED";

	/**
	 * The constant BANK_ACCOUNT_ADD_SUCCESS.
	 */
	public static final String BANK_ACCOUNT_ADD_SUCCESS = "Bank account added successfully";

	/**
	 * The constant BANK_ACCOUNT_ADD_FAILURE.
	 */
	public static final String BANK_ACCOUNT_ADD_FAILURE = "Failed to add bank account";

	/**
	 * The constant CUSTOMER_CHECK_INSERT_FAILURE.
	 */
	public static final String CUSTOMER_CHECK_INSERT_FAILURE = "Failed to add customer check details";

	/**
	 * The constant CUSTOMER_CHECKS_ADD_SUCCESS.
	 */
	public static final String CUSTOMER_CHECKS_ADD_SUCCESS = "Successfully added customer check details";

	/**
	 * The constant MISSING_CUSTOMER_ID.
	 */
	public static final String MISSING_CUSTOMER_ID = "Customer Id cannot be balnk";

	/**
	 * The constant SAVE_ONBOARDING_DATA_FAILURE.
	 */
	public static final String SAVE_ONBOARDING_DATA_FAILURE = "Failure in saving equity onboarding details";

	/**
	 * The constant SAVE_ONBOARDING_DATA_SUCCESS.
	 */
	public static final String SAVE_ONBOARDING_DATA_SUCCESS = "Successfully saved equity onboarding details";

	/**
	 * The constant PRODUCT_BANK_INSERT_FAILURE.
	 */
	public static final String PRODUCT_BANK_INSERT_FAILURE = "Failure in saving product bank mapping details";

	/**
	 * The Constant RISK_PROFILING_STATUS.
	 */
	public static final String RISK_PROFILING_STATUS = "RISK_PROFILING_STATUS";

	/**
	 * The constant REGULATORY_INFO.
	 */
	public static final String REGULATORY_INFO = "REGULATORY_INFO";
	/**
	 * The constant PREVIEW_KRA_FORM.
	 */
	public static final String PREVIEW_KRA_FORM = "PREVIEW_KRA_FORM";
	/**
	 * The constant AADHAR_AGREEMENT.
	 */
	public static final String AADHAR_AGREEMENT = "AADHAR_AGREEMENT";
	/**
	 * The constant MF_ONBOARDING.
	 */
	public static final String MF_ONBOARDING = "MF_ONBOARDING";
	/**
	 * The constant DE_ONBOARDING.
	 */
	public static final String DE_ONBOARDING = "DE_ONBOARDING";
	/**
	 * The constant CASA_ONBOARDING.
	 */
	public static final String CASA_ONBOARDING = "CASA_ONBOARDING";
	/**
	 * The constant TDRD_ONBOARDING.
	 */
	public static final String TDRD_ONBOARDING = "TDRD_ONBOARDING";

	/**
	 * The constant VERIFIED.
	 */
	public static final String VERIFIED = "VERIFIED";

	/**
	 * The constant GUARDIAN.
	 */
	public static final String GUARDIAN = "GUARDIAN";

	/**
	 * The constant NOMINEE.
	 */
	public static final String NOMINEE = "NOMINEE";

	/**
	 * The Constant ADMIN_OPS_CONFIG_MASTER_KEY.
	 */
	public static final String ADMIN_OPS_CONFIG_MASTER_KEY = "config";

	/**
	 * The Constant KYC_ORIGIN.
	 */
	public static final String KYC_ORIGIN = "KYC_ORIGIN";

	/**
	 * The Constant CUSTOMER_STATUS.
	 */
	public static final String CUSTOMER_STATUS = "CUSTOMER_STATUS";

	/**
	 * Invalid SessionId.
	 */
	public static final String INVALID_SESSION_ID = "Invalid SessionId";

	/**
	 * The Constant OTM_STATUS.
	 */
	public static final String OTM_STATUS = "OTM_STATUS";

	/**
	 * The Constant CAS_UPLOADED.
	 */
	public static final String CAS_UPLOADED = "CAS_UPLOADED";

	/**
	 * The Constant Has_Mutual_Fund.
	 */
	public static final String HAS_MUTUAL_FUND = "HAS_MUTUAL_FUND";

	/**
	 * The Constant Has_Direct_Equity.
	 */
	public static final String HAS_DIRECT_EQUITY = "HAS_DIRECT_EQUITY";

	/**
	 * The Constant Has_TD.
	 */
	public static final String HAS_TD = "HAS_TD";

	/**
	 * The Constant Has_.
	 */
	public static final String HAS_RD = "HAS_RD";

	/**
	 * The Constant CUSTOMER_STATUS_SUCCESS.
	 */
	public static final String CUSTOMER_STATUS_SUCCESS = "Successfully saved customer status";

	/**
	 * The Constant CUSTOMER_STATUS_FAILURE.
	 */
	public static final String CUSTOMER_STATUS_FAILURE = "Failed to save customer status";


	/** The Constant ERROR_RESPONSE. */
	public static final String ERROR_RESPONSE = "ERROR";


	/** The Constant COUNTRY_INDIA. */
	public static final String COUNTRY_INDIA = "India";

	/** The Constant CVL_KRA_DATE_FORMAT. */
	public static final String CVL_KRA_DATE_FORMAT = "dd/MM/yyyy";

	/** The Constant MALE. */
	public static final String MALE = "male";

	/** The Constant FEMALE. */
	public static final String FEMALE = "female";

	/** The Constant MR. */
	public static final String MR = "Mr";

	/** The Constant MRS. */
	public static final String MRS = "Mrs";

	/** The Constant MARRIED. */
	public static final String MARRIED = "married";

	/** The Constant UNMARRIED. */
	public static final String UNMARRIED = "unmarried";

	/** The Constant SEPARATED. */
	public static final String SEPARATED = "separated";

	/** The Constant MISS. */
	public static final String MISS = "Miss";

	/** The Constant BASE64_STRING_FOR_IMAGE. */
	public static final String BASE64_STRING_FOR_IMAGE = "data:image/jpeg;base64, ";

	/** The Constant KOTAK_BANK_ACOOUNT_NUMBER. */
	public static final String KOTAK_BANK_ACOOUNT_NUMBER = "06460050001139";

	/** The Constant KOTAK_BANK_ACCOUNT_IFSC. */
	public static final String KOTAK_BANK_ACCOUNT_IFSC = "KKBK1234567";

	/** The Constant KOTAK_TRANSACTION_CHANNEL_NAME. */
	public static final String KOTAK_TRANSACTION_CHANNEL_NAME = "INET";

	/** The Constant KOTAK_BENEFICIARY_ACCOUNT_TYPE. */
	public static final Short KOTAK_BENEFICIARY_ACCOUNT_TYPE = 10;

	/** The Constant KOTAK_ACQ_ID. */
	public static final Long KOTAK_ACQ_ID = 10009L;

	/** The Constant RES_CODE_ZERO. */
	public static final String RES_CODE_ZERO = "00";

	/** The Constant RES_CODE_ONE. */
	public static final String RES_CODE_ONE = "01";

	/** The Constant KYC_ROUTE_KEY_FOR_FRONTEND. */
	public static final String KYC_ROUTE_KEY_FOR_FRONTEND = "kycRoute";

	/** The Constant KYC_ORIGIN_KEY. */
	public static final String KYC_ORIGIN_KEY = "origin";

	/** The Constant KYC_DATE_FORMAT_DD_SLASH_MM_SLASH_YYYY. */
	public static final String KYC_DATE_FORMAT_DD_SLASH_MM_SLASH_YYYY = "dd/MM/yyyy";

	/** The Constant COMMON_DATE_FORMAT for BSE_UCC. */
	public static final String BSE_UCC_DATE_FORMAT = "yyyy-MM-dd";

	/**
	 * The constant RETRY_TIME_EXCEED.
	 */
	public static final String KYC_SOLICIT_RETRY_TIME_EXCEED = "RETRY_TIME_EXCEED";

	/** The Constant MALE_M. */
	public static final String MALE_M = "m";

	/** The Constant FEMALE_F. */
	public static final String FEMALE_F = "f";


	/** The Constant KRA_KYA_STAT_ADHAR. */
	public static final String KRA_KYA_STAT_ADHAR = "1";

	/** The Constant NO_POL_CONNECTION. */
	public static final String NO_POL_CONNECTION = "NA";

	/** The Constant PAN. */
	public static final String PAN = "PAN";

	/** The Constant IECO_APP_SPOURCE. */
	public static final String IECO_APP_SPOURCE = "IECO";

	/** The Constant RESIDENTIAL_STATUS. */
	public static final String RESIDENTIAL_STATUS = "RESIDENT INDIVIDUAL";

	/** The Constant KRA_KYA_STAT_CVL. */
	public static final String KRA_KYA_STAT_CVL = "0";

	/** The Constant ACC_TYPE_S. */
	public static final String ACC_TYPE_S = "S";

	/** The Constant ADHAR. */
	public static final String ADHAR = "ADHAAR";

	/** The Constant CUSTOMER_DETAIL_INSERT. */
	public static final String CUSTOMER_DETAIL_INSERT = "CUSTOMER_DETAIL_INSERT";

	/** The Constant CUSTOMER_SOURCE_MAPPING_INSERT. */
	public static final String CUSTOMER_SOURCE_MAPPING_INSERT = "CUSTOMER_SOURCE_MAPPING_INSERT";

	/** The Constant CUSTOMER_ATTRIBUTE_SOURCE_MAPPING_NSDL. */
	public static final String CUSTOMER_ATTRIBUTE_SOURCE_MAPPING_NSDL = "CUSTOMER_ATTRIBUTE_SOURCE_MAPPING_NSDL";

	/** The Constant CUSTOMER_ACCOUNT_INSERT. */
	public static final String CUSTOMER_ACCOUNT_INSERT = "CUSTOMER_ACCOUNT_INSERT";

	/** The Constant APPLICATION_SESSION_INSERT. */
	public static final String APPLICATION_SESSION_INSERT = "APPLICATION_SESSION_INSERT";

	/** The Constant CUSTOMER_TRANS_DETAILS_INSERT. */
	public static final String CUSTOMER_TRANS_DETAILS_INSERT = "CUSTOMER_TRANS_DETAILS_INSERT";

	/** The Constant CUSTOMER_ROLLBACK_SUCESS. */
	public static final String CUSTOMER_ROLLBACK_SUCESS = "Sucessfully rolled back repository";

	/** The Constant CUSTOMER_ROLLBACK_FAILURE. */
	public static final String CUSTOMER_ROLLBACK_FAILURE = "Failed to rollback repository changes";

	/** The Constant CUSTOMER_DETAIL_INSERT_FAILURE. */
	public static final String CUSTOMER_DETAIL_INSERT_FAILURE = "Failed to add customer details";

	/** The Constant NAME_ON_PAN_KEY. */
	public static final String NAME_ON_PAN_KEY = "nameOnPan";

	/** The Constant NAME_ON_SOLICIT_KEY. */
	public static final String NAME_ON_SOLICIT_KEY = "nameOnSolicit";

	/** The Constant NAME_ON_BANK_KEY. */
	public static final String NAME_ON_BANK_KEY = "nameOnBank";

	/** The Constant NAME_ON_AADHAR_KEY. */
	public static final String NAME_ON_AADHAR_KEY = "nameOnAadhar";

	/** The Constant TRANSGENDER. */
	public static final String TRANSGENDER = "transgender";

	/** The Constant TRANSGENDER_T. */
	public static final String TRANSGENDER_T = "t";

	/** The Constant NCIF_PAN_VALIDATION_FAILURE. */
	public static final String NCIF_PAN_VALIDATION_FAILURE = "NCIF PAN Validation Failure";

	/**
	 * The constant WEALTHFY_DATE_FORMAT.
	 */
	public static final String WEALTHFY_ERROR = "Wealthfy Error while register client";

	/** The constant USER_ID. */
	public static final String USER_ID_KEY = "userId";

	/** The Constant CAS_STATUS. */
	public static final String FLAG_STATUS = "customerFlagStatus";

	/** The Constant MF_INSTRUMENT_JOURNEY_STATUS. */
	public static final String MF_INSTRUMENT_JOURNEY_STATUS = "VISITED_INSTRUMENT_ID";

	/** The Constant GOAL_JOURNERY_STATUS. */
	public static final String GOAL_JOURNERY_STATUS = "VISITED_GOAL_ID";

	/** The Constant BSE_UCC_CREATION_STATUS. */
	public static final String BSE_UCC_CREATION_STATUS = "BSE_UCC_CREATION_STATUS";

	/** The Constant CATEGORY_KEY. */
	public static final String CATEGORY_KEY = "category";

	/** The Constant VALUE_KEY. */
	public static final String VALUE_KEY = "value";

	/** The Constant ID_KEY. */
	public static final String ID_KEY = "id";

	/** The Constant KEY_VAL. */
	public static final String KEY_VAL = "key";

	/** The Constant LOV_MAPPING_DATA_KEY. */
	public static final String LOV_MAPPING_DATA_KEY = "lov_mapping_data";

	/** The Constant ACTIVE_IPV_LINK. */
	public static final String ACTIVE_IPV_LINK = "ACTIVE_IPV_LINK";

	/** The Constant IPV_LINK_EXPIRED_AT. */
	public static final String IPV_LINK_EXPIRED_AT = "IPV_LINK_EXPIRED_AT";

	/** The Constant IPV_RESPONSE_DATE_FORMAT. */
	public static final String IPV_RESPONSE_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

	/** The Constant BLANK_SPACE. */
	public static final String BLANK_SPACE = " ";

	/** The Constant SIGNATURE_UPLOAD. */
	public static final String SIGNATURE_UPLOAD = "SIGNATURE_UPLOAD";

	/** The Constant IPV_SCHEDULED. */
	public static final String IPV_SCHEDULED = "IPV_SCHEDULED";

	/** The Constant NA. */
	public static final String NA = "NA";

	/** The Constant PLATFORM_ONBOARDING. */
	public static final String PLATFORM_ONBOARDING = "PLATFORM_ONBOARDING";

	/** The Constant RECENT_TEMP_CUST_ERROR. */
	public static final String RECENT_TEMP_CUST_ERROR = "Recent Temp Customer Found Error";

	/** The Constant WEALTH_PRE_LOGIN_JOURNEY. */
//Customer Pre / Post Login Stages
	public static final String WEALTH_PRE_LOGIN_JOURNEY = "Wealth Pre-Login Journey";

	/** The Constant DEPOSIT_PRE_LOGIN_JOURNEY. */
	public static final String DEPOSIT_PRE_LOGIN_JOURNEY = "Prelogin Deposit Tour";

	/** The Constant MF_PRE_LOGIN_JOURNEY. */
	public static final String MF_PRE_LOGIN_JOURNEY = "Prelogin MF Tour";

	/** The Constant PRE_LOGIN_DREAMS. */
	public static final String PRE_LOGIN_DREAMS = "Prelogin Dreams";

	/** The Constant MF_PERSONALIZED_PRE_LOGIN_JOURNEY. */
	public static final String MF_PERSONALIZED_PRE_LOGIN_JOURNEY = "MF Personalized Pre-Login Journey";

	/** The Constant MF_DIY_PRE_LOGIN_JOURNEY. */
	public static final String MF_DIY_PRE_LOGIN_JOURNEY = "MF DIY Pre-Login Journey";

	/** The Constant DIY_GOAL_PRE_LOGIN_JOURNEY. */
	public static final String DIY_GOAL_PRE_LOGIN_JOURNEY = "DIY Goal Pre-login Journey";

	/** The Constant LATEST_STAGE_FIND_ERROR. */
	public static final String LATEST_STAGE_FIND_ERROR = "Error in finding latest stages";

	/** The Constant PLATFORM_KYC. */
	public static final String PLATFORM_KYC = "Platform Kyc";

	/** The Constant WEALTH_POST_LOGIN_JOURNEY. */
	public static final String WEALTH_POST_LOGIN_JOURNEY = "Wealth Post-Login Journey";

	/** The Constant DEPOSIT_POST_LOGIN_JOURNEY. */
	public static final String DEPOSIT_POST_LOGIN_JOURNEY = "Postlogin Deposit Tour";

	/** The Constant MF_POST_LOGIN_JOURNEY. */
	public static final String MF_POST_LOGIN_JOURNEY = "Postlogin MF Tour";

	/** The Constant POST_LOGIN_DREAMS. */
	public static final String POST_LOGIN_DREAMS = "Postlogin Dreams";

	/** The Constant MF_PERSONALIZED_POST_LOGIN_JOURNEY. */
	public static final String MF_PERSONALIZED_POST_LOGIN_JOURNEY = "MF Personalized Post-Login Journey";

	/** The Constant MF_DIY_POST_LOGIN_JOURNEY. */
	public static final String MF_DIY_POST_LOGIN_JOURNEY = "MF DIY Post-Login Journey";

	/** The Constant DIY_GOAL_POST_LOGIN_JOURNEY. */
	public static final String DIY_GOAL_POST_LOGIN_JOURNEY = "DIY Goal Post-login Journey";

	/** The Constant MF_PRE_LOGIN_EXPLORE. */
	public static final String MF_PRE_LOGIN_EXPLORE = "Prelogin MF Explore";

	/** The Constant MF_POST_LOGIN_EXPLORE. */
	public static final String MF_POST_LOGIN_EXPLORE = "Postlogin MF Explore";

	/** The Constant KRA_COUNTRY_MAP_KEY. */
	public static final String KRA_COUNTRY_MAP_KEY = "101";

	/** The Constant DOC_TYPE_KEY. */
	public static final String DOC_TYPE_KEY = "doctype";

	/** The Constant DOC_DATA_ALL_KEY. */
	public static final String DOC_DATA_ALL_KEY = "all";

	/** The Constant JAVA_TEMP_DIRECTORY. */
	public static final String JAVA_TEMP_DIRECTORY = "java.io.tmpdir";

	/** The Constant IDFY_TASK_TYPE_VKYC_ASSISTED_KYC. */
	public static final String IDFY_TASK_TYPE_VKYC_ASSISTED_KYC = "vkyc.assisted_vkyc";

	/** The Constant IPV_CHECKER_COMPLETED_STATUS. */
	public static final String IPV_MAKER_COMPLETED_STATUS = "completed";

	/** The Constant BASE64_STRING_FOR_IMAGE. */
	public static final String BASE64_STRING_FOR_DATA = "data:%s;base64, ";

	/** The Constant KYC_SOURCE_CVL_KRA. */
	public static final String KYC_SOURCE_CVL_KRA = "CVLKRA";

	/** The Constant KYC_SOURCE_NDML. */
	public static final String KYC_SOURCE_NDML = "NDML";

	/** The Constant KYC_SOURCE_DOTEX. */
	public static final String KYC_SOURCE_DOTEX = "DOTEX";

	/** The Constant KYC_SOURCE_CAMS. */
	public static final String KYC_SOURCE_CAMS = "CAMS";

	/** The Constant KYC_SOURCE_KARVY. */
	public static final String KYC_SOURCE_KARVY = "KARVY";

	/** The Constant APP_SOURCE_STATUS_MAP_KEY. */
	public static final String APP_SOURCE_STATUS_MAP_KEY = "APP_KYC_SOURCE";

	/**
	 * The constant IPV_CHECKER_STATUS.
	 */
	public static final String IPV_CHECKER_STATUS = "IPV_CHECKER_STATUS";
	/**
	 * The constant IPV_MAKER_STATUS.
	 */
	public static final String IPV_MAKER_STATUS = "IPV_MAKER_STATUS";

	/**
	 * The constant UUID_REGEX.
	 */
	public static final String UUID_REGEX = "([a-f0-9]{8}(-[a-f0-9]{4}){4}[a-f0-9]{8})";

	/** The Constant UUID_REGEX_PATTERN. */
	public static final Pattern UUID_REGEX_PATTERN = Pattern.compile(UUID_REGEX, Pattern.CASE_INSENSITIVE);

	/**
	 * The constant IS_PRIMARY_BANK_ACCOUNT.
	 */
	public static final String IS_PRIMARY_BANK_ACCOUNT = "Y";
	/**
	 * The constant IS_BSE_UCC_REGISTERED.
	 */
	public static final String IS_BSE_UCC_REGISTERED = "Y";
	/**
	 * The constant OCCUPATION_KEY.
	 */
	public static final String OCCUPATION_KEY = "occupation";
	/**
	 * The constant LOV_KEY.
	 */
	public static final String LOV_KEY = "lov";
	/**
	 * The constant WEALTH_SOURCE_KEY.
	 */
	public static final String WEALTH_SOURCE_KEY = "wealth_source";
	/**
	 * The constant GROSS_INCOME_RANGE_KEY.
	 */
	public static final String GROSS_INCOME_RANGE_KEY = "gross_income_range";
	/**
	 * The constant BSE_STAR_CODE_KEY.
	 */
	public static final String BSE_STAR_CODE_KEY = "bseStarCode";
	/** The Constant DE_IECO_VALIDATE_BUY. */
	public static final String DE_IECO_VALIDATE_BUY = "DE IECO Validate Buy";

	/**
	 * The constant DE_IECO_VALIDATE_SELL.
	 */
	public static final String DE_IECO_VALIDATE_SELL = "DE IECO Validate Sell";
	/**
	 * The constant DE_KSEC_VALIDATE_BUY.
	 */
	public static final String DE_KSEC_VALIDATE_BUY = "DE KSEC Validate Buy";
	/**
	 * The constant DE_KSEC_VALIDATE_SELL.
	 */
	public static final String DE_KSEC_VALIDATE_SELL = "DE KSEC Validate Sell";
	/**
	 * The constant TRADE_ACC_ACTIVATION_REQ.
	 */
	public static final String TRADE_ACC_ACTIVATION_REQ = "Trade Account Activation Request";
	/**
	 * The constant ACTIVE_TRADE_ACC_KSEC_LOGIN_REDIRECT.
	 */
	public static final String ACTIVE_TRADE_ACC_KSEC_LOGIN_REDIRECT = "Active Trade Account KSEC Login Redirect";
	/**
	 * The constant REJECTED_OR_EXISTING_KSEC_BLOCKED.
	 */
	public static final String REJECTED_OR_EXISTING_KSEC_BLOCKED = "Rejected or Existing KSEC Blocked";
	/**
	 * The constant KSEC_VERIFICATION_IN_PROGRESS.
	 */
	public static final String KSEC_VERIFICATION_IN_PROGRESS = "KSEC Verification In Progress";
	/**
	 * The constant KSEC_CITY_NOT_SELECTED.
	 */
	public static final String KSEC_CITY_NOT_SELECTED = "KSEC City Not Selected";
	/**
	 * The constant KSEC_BANK_NOT_SELECTED.
	 */
	public static final String KSEC_BANK_NOT_SELECTED = "KSEC Bank Not Selected";
	/**
	 * The constant IDEALAKE_CONSENT_PAGE.
	 */
	public static final String IDEALAKE_CONSENT_PAGE = "Idealake Consent Page";

	/**
	 * The constant PAN_UPLOAD.
	 */
	public static final String PAN_UPLOAD = "PAN_UPLOAD";

	/**
	 * The constant MASTER_DATA_PRODUCT_KEY.
	 */
	public static final String MASTER_DATA_PRODUCT_KEY = "product";
	/**
	 * The constant MASTER_DATA_MF_KEY.
	 */
	public static final String MASTER_DATA_MF_KEY = "MF";

	/**
	 * The constant NAME_MISMATCH_CONTINUE.
	 */
	public static final String NAME_MISMATCH_CONTINUE = "NAME_MISMATCH_CONTINUE";
	/**
	 * The constant RELATIONSHIP_KEY.
	 */
	public static final String RELATIONSHIP_KEY = "relationship";
	/**
	 * The constant CUSTOMER_READINESS_DATA_KEY.
	 */
	public static final String CUSTOMER_READINESS_DATA_KEY = "customerReadinessData";
	/**
	 * The constant UNABLE_TO_FIND_ANY_MASTER_DATA.
	 */
	public static final String UNABLE_TO_FIND_ANY_MASTER_DATA = "Unable to find any master Data";
	/**
	 * The constant INTERNAL_FETCH_NON_KRA_DATA_KEY.
	 */
	public static final String INTERNAL_FETCH_NON_KRA_DATA_KEY = "INTERNAL-fetchNonKraData";

	/**
	 * The constant LOV_MASTER_CACHE_KEY.
	 */
	public static final String LOV_MASTER_CACHE_KEY = "LOV_ALL_MASTER_DATA_KEY";

	/**
	 * The constant CHECKED.
	 */
	public static final String CHECKED = "checked";
	/**
	 * The constant NOT_CHECKED.
	 */
	public static final String NOT_CHECKED = "";

	/**
	 * The constant TRIPLE_PERCENT_S.
	 */
	public static final String TRIPLE_PERCENT_S = "%s%s%s";
	/**
	 * The constant CERSAI_UPLOAD.
	 */
	public static final String CERSAI_UPLOAD = "CERSAI_UPLOAD";
	/**
	 * The constant CERSAI_IMAGE_UPLOAD.
	 */
	public static final String CERSAI_IMAGE_UPLOAD = "CERSAI_IMAGE_UPLOAD";

	/**
	 * The Constant NSE_WTOKEN.
	 */
	public static final String NSE_WTOKEN = "NSE_WTOKEN";

	/**
	 * The Constant BSE_WTOKEN.
	 */
	public static final String BSE_WTOKEN = "BSE_WTOKEN";

	/**
	 * The Constant BSE.
	 */
	public static final String BSE = "bse";

	/**
	 * The Constant NSE.
	 */
	public static final String NSE = "nse";

	/**
	 * The Constant buy equity.
	 */
	public static final String BUY_EQUITY = "Buy Equity";

	/**
	 * The Constant sell equity.
	 */
	public static final String SELL_EQUITY = "Sell Equity";

	/**
	 * The Constant Pre Ieco Buy Equity.
	 */
	public static final String PRE_IECO_BUY_EQUITY = "Pre Ieco Buy Equity";

	/**
	 * The Constant Pre Ksec Buy Equity.
	 */
	public static final String PRE_KSEC_BUY_EQUITY = "Pre Ksec Buy Equity";

	/**
	 * The Constant Pre Ieco Sell Equity.
	 */
	public static final String PRE_IECO_SELL_EQUITY = "Pre Ieco Sell Equity";

	/**
	 * The Constant Pre Ksec Sell Equity.
	 */
	public static final String PRE_KSEC_SELL_EQUITY = "Pre Ksec Sell Equity";

	/**
	 * The Constant Pre Ksec Buy Stock Basket.
	 */
	public static final String PRE_KSEC_BUY_STOCKBASKET = "Pre Ksec Buy Stock Basket";

	/**
	 * The Constant Pre Ksec Sell Stock Basket.
	 */
	public static final String POST_KSEC_BUY_STOCKBASKET = "Post Ksec Buy Stock Basket";

	/**
	 * The constant BASKET_ID.
	 */
	public static final String BASKET_ID = "BASKET_ID";
	/**
	 * The constant OTHER_SESSION_NOT_ACTIVE.
	 */
	public static final String OTHER_SESSION_NOT_ACTIVE = "OTHER_SESSION_NOT_ACTIVE";
	/**
	 * The constant OTHER_SESSION_ACTIVE.
	 */
	public static final String OTHER_SESSION_ACTIVE = "OTHER_SESSION_ACTIVE";
	/**
	 * The constant IPV_STATUS_NOT_MATCHED.
	 */
	public static final String IPV_STATUS_NOT_MATCHED = "IPV_STATUS_NOT_MATCHED";
	/**
	 * The constant IDFY_PACKAGE_TYPE.
	 */
	public static final String IDFY_PACKAGE_TYPE = "IDFY_PACKAGE_TYPE";
	/**
	 * The constant YYYY_DASH_MM_DASH_DD.
	 */
	public static final String YYYY_DASH_MM_DASH_DD = "yyyy-MM-dd";
	/**
	 * The constant IS_RETRY_ENABLED.
	 */
	public static final String IS_RETRY_ENABLED = "IS_RETRY_ENABLED";
	/**
	 * The constant APP_LATEST_DATE_KEY.
	 */
	public static final String APP_LATEST_DATE_KEY = "APP_LATEST_DATE_KEY";
	/**
	 * The constant DATE_FORMAT_DDMMYYYY.
	 */
	public static final String DATE_FORMAT_DDMMYYYY = "ddMMyyyy";
	/**
	 * The constant IPV_CHECKER_REJECTED_STATUS.
	 */
	public static final String IPV_CHECKER_REJECTED_STATUS = "rejected";
	/**
	 * The constant IPV_CHECKER_CANCELLED_STATUS.
	 */
	public static final String IPV_CHECKER_CANCELLED_STATUS = "CANCELLED";
	/**
	 * The constant IPV_CHECKER_FAILED_STATUS.
	 */
	public static final String IPV_CHECKER_FAILED_STATUS = "FAILED";
	/**
	 * The constant KIAL_KRA_STAT_REJECTED.
	 */
	public static final String KIAL_KRA_STAT_REJECTED = "REJECTED";
	/**
	 * The constant KIAL_KRA_STAT_MODIFICATION_REJECTED.
	 */
	public static final String KIAL_KRA_STAT_MODIFICATION_REJECTED = "MODIFICATION_REJECTED";
	/**
	 * The constant KIAL_KRA_STAT_MODIFICATION_SUBMITTED.
	 */
	public static final String KIAL_KRA_STAT_MODIFICATION_SUBMITTED = "MODIFICATION_SUBMITTED";
	/**
	 * The constant KIAL_KRA_STAT_MODIFICATION_RESUBMITTED.
	 */
	public static final String KIAL_KRA_STAT_MODIFICATION_RESUBMITTED = "MODIFICATION_RESUBMITTED";
	/**
	 * The constant KIAL_KRA_STAT_SUBMITTED.
	 */
	public static final String KIAL_KRA_STAT_SUBMITTED = "SUBMITTED";
	/**
	 * The constant KIAL_KRA_STAT_RESUBMITTED.
	 */
	public static final String KIAL_KRA_STAT_RESUBMITTED = "RESUBMITTED";
	/**
	 * The constant KIAL_KRA_STAT_SUBMIT_READY.
	 */
	public static final String KIAL_KRA_STAT_SUBMIT_READY = "SUBMIT_READY";
	/**
	 * The constant KIAL_KRA_STAT_BLOCKED.
	 */
	public static final String KIAL_KRA_STAT_BLOCKED = "BLOCKED";
	/**
	 * The constant KIAL_KRA_STAT_DONT_UPDATE.
	 */
	public static final String KIAL_KRA_STAT_DONT_UPDATE = "DONT_UPDATE";
	/**
	 * The constant PAN_APP_STATUS.
	 */
	public static final String PAN_APP_STATUS = "PAN_APP_STATUS";
	/**
	 * The constant PARTIAL_OR_NOT_COMPLETED.
	 */
	public static final String PARTIAL_OR_NOT_COMPLETED = "PARTIAL_OR_NOT_COMPLETED";
	/**
	 * The constant ALL_PRODUCT.
	 */
	public static final String ALL_PRODUCT = "ALL";
	/**
	 * The constant RETRY_KYC_COUNT.
	 */
	public static final String RETRY_KYC_COUNT = "RETRY_KYC_COUNT";
	/**
	 * The constant KRA_STATUS.
	 */
	public static final String KRA_STATUS = "KRA_STATUS";
	/**
	 * The constant KIAL_KRA_STAT_MODIFICATION_INITIATED.
	 */
	public static final String KIAL_KRA_STAT_MODIFICATION_INITIATED = "MODIFICATION_INITIATED";
	/**
	 * Initialize App GOALID ViewCount with 1
	 */
	public static final Integer INITIALIZE_APP_GOAL_VIEW_COUNT = 1;
	/**
	 * The constant APP_GOAL_VIEW_KEY.
	 */
	public static final String APP_GOAL_VIEW_KEY = "appGoalView";
	/**
	 * AppGoalView Success & Failure Message
	 */
	public static final String APP_GOAL_VIEW_SAVE_SUCCESS = "Viewcount saved successfully";
	/**
	 * The constant APP_GOAL_VIEW_SAVE_FAILURE.
	 */
	public static final String APP_GOAL_VIEW_SAVE_FAILURE = "Error while saving viewcount";
	/**
	 * The constant APP_GOAL_VIEW_SUCCESS.
	 */
	public static final String APP_GOAL_VIEW_SUCCESS = "GoalViews retrieved successfully";
	/**
	 * The constant APP_GOAL_VIEW_ERROR.
	 */
	public static final String APP_GOAL_VIEW_ERROR = "Error while retrieving GoalViews";
	/**
	 * The constant NOTIFICATION_SUCCESS.
	 */
	public static final String NOTIFICATION_SUCCESS = "SUCCESS";
	/**
	 * The constant SOURCE_TYPE_MASTER_IECO.
	 */
	public static final Object SOURCE_TYPE_MASTER_IECO = "IECO";
	/**
	 * The constant KRA_FROM_APP_SOURCE_KEY.
	 */
	public static final String KRA_FROM_APP_SOURCE_KEY = "KRA_FROM_APP_SOURCE_KEY";
	/**
	 * The constant AOF_STATUS.
	 */
	public static final String AOF_STATUS = "AOF_STATUS";
	/**
	 * The constant CVL_KRA_PUSH_UPLOAD_KEY.
	 */
	public static final String CVL_KRA_PUSH_UPLOAD_KEY = "CVL_KRA_PUSH_UPLOAD_KEY";
	/**
	 * The constant STATUS_ACCEPTED.
	 */
	public static final String STATUS_ACCEPTED = "Accepted";
	/**
	 * The constant STATUS_REJECTED.
	 */
	public static final String STATUS_REJECTED = "Rejected";
	/**
	 * The constant ERR_MSG_ACCOUNT_NUMBER_NOT_FOUND.
	 */
	public static final String ERR_MSG_ACCOUNT_NUMBER_NOT_FOUND = "Account number not found for this customer";
	/** The Constant CKYC_RESPONSE. */
	public static final String CKYC_RESPONSE = "cersaiKYCResponse";

	/**
	 * The constant ETB_CRN_BASIC_FAILURE.
	 */
	public static final String ETB_CRN_BASIC_FAILURE = "ETB CRN Basic Details Failure";
	/**
	 * The constant ETB_CRN_APAC_FAILURE.
	 */
	public static final String ETB_CRN_APAC_FAILURE = "ETB CRN APAC Fetch Failure";
	/**
	 * The constant ETB_ACCT_INQ_FAILURE.
	 */
	public static final String ETB_ACCT_INQ_FAILURE = "ETB Account Inq Failure";
	/**
	 * The constant ETB_ACCT_LIEN_INQ_FAILURE.
	 */
	public static final String ETB_ACCT_LIEN_INQ_FAILURE = "ETB Account Lien Inq Failure";
	/**
	 * The constant DMS_CURRENT_SESSION_ID.
	 */
	public static final String DMS_CURRENT_SESSION_ID = "custom-uuid-session-id";
	/**
	 * The constant PIN_BASED_SERVICE_UNAVAILABLE.
	 */
	public static final String PIN_BASED_SERVICE_UNAVAILABLE = "PIN_SRVC_UNAVAILABLE";
	/**
	 * The constant PIN_BASED_SERVICE_AVAILABLE.
	 */
	public static final String PIN_BASED_SERVICE_AVAILABLE = "PIN_SRVC_AVAILABLE";
	/**
	 * The constant ETB.
	 */
	public static final String BANK_STATUS = "BANK_STATUS";
	/**
	 * The Constant SA_CRN_PGN
	 */
	public static final String SA_CRN_PGN="SA_CRN_PGN";
	/**
	 * The Constant CRN_SOURCE
	 */
	public static final String CRN_SOURCE ="CRN_SOURCE";
	/**
	 * The constant ETB.
	 */
	public static final String ETB = "ETB";
	/**
	 * The constant NTB.
	 */
	public static final String NTB = "NTB";
	/**
	 * The constant RESTRCITED.
	 */
	public static final String RESTRICTED = "RESTRICTED";
	/**
	 * The constant BANK_STATUS_KEY.
	 */
	public static final String BANK_STATUS_KEY = "bankstatus";
	/**
	 * The constant CRN.
	 */
	public static final String CRN = "crn";
	/** The constant BANK_OAUTH_KEY */
	public static final String BANK_OAUTH_KEY = "BANK_OAUTH";
	/** The constant CKYC_VF_CRN_KEY */
    public static final String VF_CRN_KEY = "VF_CRN_MAPPING";
	/**
	 * The constant KRA_UPLOAD_STATUS_SUBMITTED.
	 */
	public static final String KRA_UPLOAD_STATUS_SUBMITTED = "01";
	/**
	 * The constant KRA_UPLOAD_STATUS_MODIFICATION_SUBMITTED.
	 */
	public static final String KRA_UPLOAD_STATUS_MODIFICATION_SUBMITTED = "02";
	/**
	 * The constant DATE_FORMAT_YYYY_MM_DD_T_HH_MM_SS_Z.
	 */
	public static final String DATE_FORMAT_YYYY_MM_DD_T_HH_MM_SS_Z = "yyyy-MM-dd'T'HH:mm:ss'Z'";
	/**
	 * The Constant DREAM_STOCK_BASKET_ID.
	 */
	public static final String DREAM_STOCK_BASKET_ID = "DREAM_STOCK_BASKET_ID";
	/**
	 * The Constant Dream Intermediate Stock Basket.
	 */
	public static final String DREAM_INTERMEDIATE_STOCK_BASKET = "Dream Intermediate Stock Basket";
	/**
	 * The constant UNAUTHORIZED.
	 */
	public static final String UNAUTHORIZED = "401 Unauthorized";
	/**
	 * The constant MS.
	 */
	public static final String MS = "Ms";
	/**
	 * The constant NCIF_COMPLETE.
	 */
	public static final String NCIF_COMPLETE = "NCIF_COMPLETE";
	/**
	 * The constant for Sservicable PIN Check.
	 */
	public static final String SERVICEABLE_PIN = "SERVICEABLE_PIN";
	/**
	 * Base62 characters table sorted to quickly calculate decimal equivalency by compensating.
	 */
	protected static final char[] BASE62 = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();
	
	/**
	 * The constant POST EQUITY_ONBOARDING Operation.
	 */
	public static final String POST_EQUITY_ONBOARDING_OPERATION = "POST_EQUITY_ONBOARDING_OPERATION";
	/**
	 * The constant POST EQUITY_ONBOARDING Operation: cust not found.
	 */
	public static final String POST_EQUITY_ONBOARDING_OPERATION_CUST_NOT_FOUND = "Customer Details not found";
	/**
	 * The constant POST EQUITY_ONBOARDING Operation: cust ieco account not found.
	 */
	public static final String POST_EQUITY_ONBOARDING_OPERATION_CUST_ACCOUNT_NOT_FOUND = "Customer IECO Account Details not found";
	/**
	 * The constant POST EQUITY_ONBOARDING Operation: exception has occurred
	 */
	public static final String POST_EQUITY_ONBOARDING_OPERATION_EXCEPTION ="Exception has Occurred";

	public static final String  CRN_IFNO_LOG = "CRN Eligibility Check failed for PAN:{} , CRN:{} , with Reason:{}";
	
	public static final String NCIF_EXACT_MATCH_KEY = "isExactMatch";
	
	/* constant for OPS workflow */
    public static final String PROCESS_ID_BLOCKUNBLOCK = "BlockUnblockProcess";
    public static final String PROCESS_ID_NCIF = "NCIFProcess";
    public static final String WORKFLOW_VALUE = "value";
    public static final String WORKFLOW_TYPE = "type";
    public static final String WORKFLOW_TYPE_STRING = "String";
    public static final String PROCESS_ID_KRAY = "KRAYProcess";
    public static final String PROCESS_ID_UAM = "UAMProcess";

	public static final String REPORT_DATE_FORMAT = "dd-MM-yyyy";
	
	public static final String AUTO_APPROVED = "AA";
	
	public static final String SOURCE_TYPE_AUTOMATED = "AUTOMATED";

	public static final String SOURCE_TYPE_MANUAL = "MANUAL";
	
	public static final String PRODUCT = "PRODUCT";
	
	public static final String NAME_MISMATCH_ERROR = "NAME_MISMATCH_ERROR";
	
	public static final String KYC_CACHE_MAP = "KYC_CACHE_MAP";
	
    public static final String BANK_ONBOARDING_ERROR = "BANK_ONBOARDING_ERROR";

	/**
     * The constant ORIGINAL_KYC_ROUTE.
     */
    public static final String ORIGINAL_KYC_ROUTE = "ORIGINAL_KYC_ROUTE";
    public static final String KRAY_IMAGE_EXTRACTION = "KRAY_IMAGE_EXTRACTION";
    

    /**
     * The Constant SIGNER_ID.
     */
    public static final String ESIGN_SIGNER_ID = "%s_SIGNER_ID";
    
    /**
     * The Constant DOCKET_ID.
     */
    public static final String ESIGN_DOCKET_ID = "%s_DOCKET_ID";
    
    /**
     * The Constant DOCUMENTID.
     */
    public static final String ESIGN_DOCUMENT_ID = "%s_DOCUMENT_ID";
    
    /**
     * The Constant ESIGNSTATUS.
     */
    public static final String ESIGN_STATUS = "%s_STATUS";
    
    public static final String ESIGN_PDF_FILE_NAME="%s-eSigned-KRA-AOF.pdf";

    public static final String CERSAI_CUSTOMER_REJECTIONCODE = "CERSAI_CUSTOMER_REJECTIONCODE";
    public static final String CERSAI_IMAGE_REJECTIONCODE = "CERSAI_IMAGE_REJECTIONCODE";
    
    /**
     * The constant ZOHO_LEAD_STATUS.
     */
    public static final String ZOHO_LEAD_STAGE = "ZOHO_LEAD_STAGE";


    /**
	 * The Constant Pre Ieco Create Stock Basket.
	 */
	public static final String PRE_IECO_CREATE_STOCK_BASKET= "Pre Ieco Create Stock Basket";
	
	public static final String IN_PROCESS = "IN PROCESS";

	/**
	 * Session id in calling build push kra from scheduler methods
	 */
	public static final String CVL_PUSH_SCHEDULER_SESSION = "SCHEDULER_SESSION";
	
	public static final String AOF_ESIGN_STATUS = "SIGNED_KYC_AOF_STATUS";
	
	public static final String INITIATED = "INITIATED";
		
	public static final String COPY_FILES_TO_KRADOC = "COPY_FILES_TO_KRADOC";

    public static final String MY_PROFILE = "MY_PROFILE";

    public static final String SKIP_BANK_AUTH = "SKIP_BANK_AUTH";

    public static final String EMAIL_KEY = "email";

    public static final String LOGIN_KEY = "loginId";
    
    public static final String ACCOUNT_ID_KEY = "accountId";
    
    /**
     *   Constants for NTB checks [START]
     *   
     */
    public static final String CRN_CREATION = "CRN_CREATION";
    
    public static final String CRN_CONFIRMATION = "CRN_CONFIRMATION";

    public static final String MOBILE_BANKING_CHANNEL_ACTIVATION = "MOBILE_BANKING_CHANNEL_ACTIVATION";

    public static final String FLEXI_INFO_UPDATE = "FLEXI_INFO_UPDATE";

    public static final String BCIF_APAC_PARTY_UPDATE = "BCIF_APAC_PARTY_UPDATE";

    public static final String SERVICEABLE_PIN_CHECK = "SERVICEABLE_PIN_CHECK";

    public static final String KYC_PERSONAL_INFO = "KYC_PERSONAL_INFO";
    /**
     *   Constants for NTB checks [END]
     *   
     */
    
    /** The Constant LOV_MAPPING_DATA_KEY for KMBL_CODE */
    public static final String KMBL_CODE = "kmblCode";
    
    /** The Constant for TD Consent during onboarding */
    public static final String TD_ONBOARDING_CONSENT = "TD_ONBOARDING_CONSENT";
    
    /** The Constant for RD Consent during onboarding */
    public static final String RD_ONBOARDING_CONSENT = "RD_ONBOARDING_CONSENT";
    
    /** The Constant for CA Consent during onboarding */
    public static final String CA_ONBOARDING_CONSENT = "CA_ONBOARDING_CONSENT";
    
    /** The Constant for TD Consent during onboarding */
    public static final String SA_ONBOARDING_CONSENT = "SA_ONBOARDING_CONSENT";
    
    /** Constants for SERVICEABLE_PIN_CHECK */
    public static final String AVAILABLE = "AVAILABLE";
    
    public static final String UNAVAILABLE = "UNAVAILABLE";
    
    public static final String TECH_FAILURE = "TECH_FAILURE";
    
    public static final String TD_NTB = "TD_NTB";
    
    public static final String PREF_CONTACT_CHECK = "PREF_CONTACT_CHECK";
    
    public static final String VALID_OVD = "valid_ovd";
    
    /** Constant for Has SA Flag*/
	public static final String HAS_SA = "HAS_SA";

    public static final String CUSTOMER_SOURCE_ATTRIBUTE_MAP = "customerSrcAttr";
    
    public static final String RESPONSE_CODE = "responseCode";
    
    public static final String RESPONSE_TYPE = "responseType";
    
    public static final String PENDING = "PENDING";
    
    public static final String AOF_IN_PROCESS = "AOF_IN_PROCESS";
    
    public static final String SA_UPD_BCIF_KEY = "SA_UPD_BCIF";

    public static final String EQUITY_JOURNEY = "EQUITY_JOURNEY";

    public static final String CERSAIDOC_SFTP_UPLOAD = "CERSAIDOC_SFTP_UPLOAD";
    
    /**
     * The constant DUPLICATE.
     */
    public static final String DUPLICATE = "DUPLICATE";
    
    public static final String REGISTRATION = "REGISTRATION";
    
    public static final String OTP_VERIFICATION = "OTP_VERIFICATION";
}
