package ieco.internal.middleware.domain.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@ToString
public class DeskContactCreationCustomFields {

    private String cf_kotak_360;

    private String cf_category;

    private String cf_ticket_department;

    private String cf_product_type;

    private String cf_sr_nature;

    private String cf_sr_group;

    private String cf_sr_type;

    private String cf_crn_type;

    private String cf_sr_classification;

    private String cf_lead_reference_id;

    @JsonProperty("firstName")
    private String firstName;

    private String cf_remarks;

    private String cf_single_line_1k_sec_ticket_id;

    private String cf_siebel_ticket_id;

    private String cf_kotak_360_id;

    private String cf_replicate_to_ksec_crm;

    private String cf_creation_mode;

    private String cf_replicate_to_siebel;

    private String cf_calling_done;

    private String cf_crn_present;

    private String cf_client_code_present;

    private String cf_products_purchased;

    private String cf_environment;

    // kbank ticket layout
    private String cf_sr_product;

    // kbank ticket layout
    private String cf_send_email;
    // kbank ticket layout
    private String cf_summary;

    // Ksec ticket layout
    private String cf_send_sms_1;

    private String cf_ieco_id;

    private String cf_created_date;

    private String cf_customer_journey;
    // custom fields for social campaign

    private String cf_utm_source;

    private String cf_utm_medium;

    private String cf_utm_content;

    private String cf_utm_campaign;

    private String cf_utm_term;

    private String cf_lead_verified;

    private String cf_customer_type;

    private String cf_disposition;
    private Integer cf_attempt;
    private String cf_device_type;
    private String cf_first_call_date;
    private String cf_connected_date;

    private String cf_gender;

    private String cf_registration_date;
    private String cf_onboarding_date;
    private String cf_kra_status;
    private String cf_onboarding_reason;
    private String cf_onboarding_status;
    private String classification;

    private String cf_followup_datetime;

    private String cf_followup_user_id;


    private String cf_followup_user_name;

    private String cf_notes;

    private String cf_cherry_bank_category;


}
