package ieco.internal.middleware.enums;

import lombok.Getter;

import java.util.Arrays;

/**
 * Gets the value.
 */
@Getter
public enum DmsDocumentTypeEnum {

	/** The aadhar. */
	AADHAR("AADHAR"),

	/** The photo. */
	PHOTO("PHOTO"),

	/** The bank doc. */
	BANK_DOC("BANK_DOC"),

	/** The kra. */
	KRA("KRA"),

	/** The poi. */
	POI("POI"),

	/** The poa. */
	POA("POA"),

	/** The ipv. */
	IPV("IPV"),
	
	/** The EMAIL. */
    EMAIL("EMAIL"),
	/** The user form. */
	USER_FORM("USER_FORM"),

	/** Cvl kra zip */
	CVL_KRA_ZIP("CVL_KRA_ZIP"),

	/** Cvl kra zip */
	AOF_FORM("AOF_FORM"),
	OTHERS("OTHERS"),

	/** The AADHAR photo. */
	AADHAR_PHOTO("AADHAR_PHOTO"),
	
	/** The AADHAR zip. */
	AADHAR_ZIP("AADHAR_ZIP"),
    
	/** The Bank cheque image. */
	BANK_CHEQUE("BANK_CHEQUE"),

	/** The Bank passbook image. */
	BANK_PASSBOOK("BANK_PASSBOOK"),

	/** The pan */
	PAN("PAN"),
	
	/** The signature */
	SIGNATURE("SIGNATURE"),
	
	/** The Agent video */
	AGENT_IPV_VIDEO("AGENT_IPV_VIDEO"),
	
	/** The Customer video */
	CUSTOMER_IPV_VIDEO("CUSTOMER_IPV_VIDEO"),
	
	/** The IPV Photo */
	IPV_PHOTO("IPV_PHOTO"),
	
	/** The Signature proof */
	SIGNATURE_PROOF("SIGNATURE_PROOF"),
	
	IPV_PROFILE_REPORT("IPV_PROFILE_REPORT"),
	TD_AOF("TD_AOF"),
	
	SIGNED_KYC_AOF("SIGNED_KYC_AOF"),
	CRF("CRF"),
	SA_AOF_FORM("SA_AOF_FORM"),
	KRA_POI("KRA_POI"),
	KRA_POA("KRA_POA"),
	KRA_PAN("KRA_PAN"),
	KRA_PHOTO("KRA_PHOTO"),
	KRA_OTHER("KRA_OTHER");

	/** The value. */
	private String value;

	/**
	 * Instantiates a new dms document type enum.
	 *
	 * @param value the value
	 */
	DmsDocumentTypeEnum(String value) {
		this.value = value;
	}

	/**
	 * From value dms document type enum.
	 *
	 * @param value the value
	 * @return the dms document type enum
	 */
	public static DmsDocumentTypeEnum fromValue(String value) {
		for (DmsDocumentTypeEnum category : values()) {
			if (category.value.equalsIgnoreCase(value)) {
				return category;
			}
		}
		throw new IllegalArgumentException(
				"Unknown enum type " + value + ", Allowed values are " + Arrays.toString(values()));
	}
}
