package ieco.internal.middleware.enums;

import lombok.Getter;

@Getter
public enum ZohoLeadStage {
    
    OTP_VERIFIED("MOBILE/EMAIL_VERIFIED"),
    REGISTRATION_SUCCESSFUL("REGISTRATION_SUCCESSFUL"),
	CRN_CLIENT_CODE_CREATED("CRN/CLIENT_CODE_CREATED"),
	SOCIAL_MEDIA("SOCIAL_MEDIA"),
	DISTRIBUTION("DISTRIBUTION");
	
    private String value;

    ZohoLeadStage(String value) {
        this.value = value;
    }
    
}
