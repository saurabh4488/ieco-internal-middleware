/*
 *
 */
package ieco.internal.middleware.util;

import java.util.regex.Pattern;

/**
 * The type App constant.
 */
public class AppConstant {
	/**
	 * The constant STATUS_SUCCESS.
	 */
	public static final String STATUS_SUCCESS = "success";
	/**
	 * The constant STATUS_FAILURE.
	 */
	public static final String STATUS_FAILURE = "failure";
	/**
	 * The constant FORGOT_USER_TYPE.
	 */
	public static final String FORGOT_USER_TYPE = "forgotusername";

	/**
	 * Due to fortify giving issue commenting unused variable having password in variable name

	 /**
	 * The constant FORGOT_PASSWORD_REQUEST_TRANSACTION_LOCKED.

	public static final String FORGOT_PASSWORD_REQUEST_TRANSACTION_LOCKED = "LOCKED";
	/**
	 * The constant FORGOT_PASSWORD_REQUEST_TRANSACTION_FORGOT.

	public static final String FORGOT_PASSWORD_REQUEST_TRANSACTION_FORGOT = "FORGOT";
	/**
	 * The constant FORGOT_PASSWORD_REQUEST_TYPE_PAN.
	 */
	/**
	 * The constant FORGOT_PASSWORD_REQUEST_TYPE_PAN.

	 public static final String FORGOT_PASSWORD_REQUEST_TYPE_PAN = "PAN";

	 /**
	 * The constant FORGOT_PASSWORD_REQUEST_TYPE_DOB.

	public static final String FORGOT_PASSWORD_REQUEST_TYPE_DOB = "DOB";
	/**
	 * The constant FORGOT_PASSWORD_TYPE.

	public static final String FORGOT_PASSWORD_TYPE = "forgotpassword";

	 /**
	 * The constant FORGOT_PASSWORD_ERROR.

	public static final String FORGOT_PASSWORD_ERROR = "Error in Retrieval of Customer Details from DB. Please check log";

	Fortify issue ended
	 /**
	 * The constant FORGOT_USERNAME_ERROR.
	 */
	public static final String FORGOT_USERNAME_ERROR = "Error in Retrieval of Customer Details from DB. Please check log";


	/**
	 * Key for attr in responsobject.
	 */
	public static final String OTP_DETAILS_KEY = "otpDetails";


	/**
	 * Key for master cache.
	 */
	public static final String MASTER_CACHE_MAP_KEY = "mastercachedetails";


	/**
	 * The Constant VALID_EMAIL_ADDRESS_REGEX.
	 */
	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);


	/**
	 * The Constant IECO_CACHE_MAP.
	 */
	public static final String IECO_CACHE_MAP = "ieco_map";

	/**
	 * The Constant IECO_CMS_CACHE_MAP.
	 */
	public static final String IECO_CMS_CACHE_MAP = "ieco_cms_map";

	/**
	 * The Constant IECO_MASTER_CACHE_MAP.
	 */
	public static final String IECO_MASTER_CACHE_MAP = "ieco_master_map";

	/**
	 * The Constant COMMON_DATE_FORMAT.
	 */
	public static final String COMMON_DATE_FORMAT = "dd-MM-yyyy";

	/**
	 * The Constant COMMON_DATE_FORMAT.
	 */
	public static final String SOLICT_DATE_FORMAT = "dd/MM/yyyy";


	/**
	 * The Constant SUCCESS.
	 */
	public static final String SUCCESS = "SUCCESS";
	/**
	 * The constant STATUS_FAILURE.
	 */
	public static final String FAILURE = "FAILURE";

	/**
	 * The constant NOTIFICATION_MSG_DATE_FORMAT.
	 */
	public static final String NOTIFICATION_MSG_DATE_FORMAT = "ddMMyyyyHHmmss";

	/**
	 * The constant COuntry code.
	 */
	public static final String COUNTRY_CODE = "91";

	public static final String TRANSACTION_TYPE_CHANGE_PASS = "CHANGE_PWD";
	public static final String TRANSACTION_TYPE_KYC = "KYC";
	public static final String TRANSACTION_TYPE_CLOSE_SAVING_ACC = "CLOSE_SAVING_ACC";
	public static final String TRANSACTION_TYPE_DE_PURCHASE = "DE_PURCHASE";
	public static final String TRANSACTION_TYPE_FD_PURCHASE = "FD_PURCHASE";
	public static final String TRANSACTION_TYPE_FORGOT_PASS = "FORGOT_PASSWORD";
	public static final String TRANSACTION_TYPE_FORGOT_USERNAME = "FORGOT_USERNAME";
	public static final String TRANSACTION_TYPE_LOGIN = "LOGIN";
	public static final String TRANSACTION_TYPE_NPS_PURCHASE = "NPS_PURCHASE";
	public static final String TRANSACTION_TYPE_OPEN_SAVING_ACC = "OPEN_SAVING_ACC";
	public static final String TRANSACTION_TYPE_OTP = "OTP";
	public static final String TRANSACTION_TYPE_RD_PURCHASE = "RD_PURCHASE";
	public static final String TRANSACTION_TYPE_REGISTRATION = "REGISTRATION";
	public static final String TRANSACTION_TYPE_REPORT_GENERATION = "REPORT_GENERATION";
	public static final String TRANSACTION_TYPE_TSFD_PURCHASE = "TSFD_PURCHASE";

	public static final String NOTIFICATION_TEMPLATE_KEY = "notification_template";

	/**
	 * The Constant IPV_DATE_FORMAT.
	 */
	public static final String IPV_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";

	public static final String AUTHORIZATION_KEY = "Authorization";
	public static final String CUSTOMER_ID_KEY = "customerID";
	public static final String HEADER_CUSTOMER_ID_KEY = "customerId";
}