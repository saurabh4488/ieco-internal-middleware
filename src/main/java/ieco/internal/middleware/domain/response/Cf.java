package ieco.internal.middleware.domain.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "cf_replicate_to_siebel",
        "cf_reference_number",
        "cf_counter_reading_of_the_machine",
        "cf_fulfilment_action",
        "cf_disputed_amount",
        "cf_received_by",
        "cf_lc_referral_code",
        "cf_replicate_to_k_bank",
        "cf_product_category",
        "cf_home_branch",
        "cf_email_assignee",
        "cf_payout_amount",
        "cf_business_segment",
        "cf_ticket_department",
        "cf_sr_type",
        "cf_sr_description",
        "cf_sub_department",
        "cf_client_number",
        "cf_interaction_channel",
        "cf_call_type",
        "cf_esc_un_ack",
        "cf_communication_preference",
        "cf_last_name",
        "cf_crn_type",
        "cf_ni_fa_no",
        "cf_narration",
        "cf_ref_no",
        "cf_first_name",
        "cf_closure_description",
        "cf_esc_non_closed",
        "cf_disputed_date",
        "cf_multiple_complaints",
        "cf_client_code1",
        "cf_ftr",
        "cf_sr_location",
        "cf_pan_fig",
        "cf_siebel_ticket_id",
        "cf_replicate_to_ksec_crm",
        "cf_product_type",
        "cf_replicate_in_k_sec",
        "cf_crn",
        "cf_sr_group",
        "cf_sub_call_type",
        "cf_no_of_times_re_opened",
        "cf_reopened_reason",
        "cf_inward_date",
        "cf_actual_user_id",
        "cf_summary",
        "cf_elapsed_time",
        "cf_k_bank_code",
        "cf_fa_location",
        "cf_mobile_number1",
        "cf_stage",
        "cf_source",
        "cf_hold_reason",
        "cf_should_show_replicate",
        "cf_response_channel_to_client",
        "cf_replicate_in_k_bank",
        "cf_fa_no",
        "cf_replicate_to_k_sec",
        "cf_cause_code",
        "cf_sr_nature",
        "cf_sr_created_by_dept",
        "cf_resolution_code",
        "cf_account_block1",
        "cf_account_block2",
        "cf_follow_up_date",
        "cf_severity",
        "cf_tat",
        "cf_response_channel_from_client",
        "cf_sr_lob",
        "cf_serial_no",
        "cf_k_sec_code",
        "cf_kotak_360_id",
        "cf_card_block",
        "cf_calling_done",
        "cf_company_name",
        "cf_kial_code",
        "cf_it_type",
        "cf_single_line_1k_sec_ticket_id",
        "cf_sr_classification"
})
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Getter
@Setter
@ToString
public class Cf {

    @JsonProperty("cf_replicate_to_siebel")
    private Object cfReplicateToSiebel;
    @JsonProperty("cf_reference_number")
    private Object cfReferenceNumber;
    @JsonProperty("cf_counter_reading_of_the_machine")
    private Object cfCounterReadingOfTheMachine;
    @JsonProperty("cf_fulfilment_action")
    private Object cfFulfilmentAction;
    @JsonProperty("cf_disputed_amount")
    private Object cfDisputedAmount;
    @JsonProperty("cf_received_by")
    private Object cfReceivedBy;
    @JsonProperty("cf_lc_referral_code")
    private Object cfLcReferralCode;
    @JsonProperty("cf_replicate_to_k_bank")
    private Object cfReplicateToKBank;
    @JsonProperty("cf_product_category")
    private Object cfProductCategory;
    @JsonProperty("cf_home_branch")
    private Object cfHomeBranch;
    @JsonProperty("cf_email_assignee")
    private Object cfEmailAssignee;
    @JsonProperty("cf_payout_amount")
    private Object cfPayoutAmount;
    @JsonProperty("cf_business_segment")
    private Object cfBusinessSegment;
    @JsonProperty("cf_ticket_department")
    private String cfTicketDepartment;
    @JsonProperty("cf_sr_type")
    private Object cfSrType;
    @JsonProperty("cf_sr_description")
    private Object cfSrDescription;
    @JsonProperty("cf_sub_department")
    private Object cfSubDepartment;
    @JsonProperty("cf_client_number")
    private Object cfClientNumber;
    @JsonProperty("cf_interaction_channel")
    private Object cfInteractionChannel;
    @JsonProperty("cf_call_type")
    private Object cfCallType;
    @JsonProperty("cf_esc_un_ack")
    private Object cfEscUnAck;
    @JsonProperty("cf_communication_preference")
    private Object cfCommunicationPreference;
    @JsonProperty("cf_last_name")
    private Object cfLastName;
    @JsonProperty("cf_crn_type")
    private String cfCrnType;
    @JsonProperty("cf_ni_fa_no")
    private Object cfNiFaNo;
    @JsonProperty("cf_narration")
    private Object cfNarration;
    @JsonProperty("cf_ref_no")
    private Object cfRefNo;
    @JsonProperty("cf_first_name")
    private Object cfFirstName;
    @JsonProperty("cf_closure_description")
    private Object cfClosureDescription;
    @JsonProperty("cf_esc_non_closed")
    private Object cfEscNonClosed;
    @JsonProperty("cf_disputed_date")
    private Object cfDisputedDate;
    @JsonProperty("cf_multiple_complaints")
    private Object cfMultipleComplaints;
    @JsonProperty("cf_client_code1")
    private Object cfClientCode1;
    @JsonProperty("cf_ftr")
    private Object cfFtr;
    @JsonProperty("cf_sr_location")
    private Object cfSrLocation;
    @JsonProperty("cf_pan_fig")
    private Object cfPanFig;
    @JsonProperty("cf_siebel_ticket_id")
    private Object cfSiebelTicketId;
    @JsonProperty("cf_replicate_to_ksec_crm")
    private Object cfReplicateToKsecCrm;
    @JsonProperty("cf_product_type")
    private String cfProductType;
    @JsonProperty("cf_replicate_in_k_sec")
    private Object cfReplicateInKSec;
    @JsonProperty("cf_crn")
    private Object cfCrn;
    @JsonProperty("cf_sr_group")
    private String cfSrGroup;
    @JsonProperty("cf_sub_call_type")
    private Object cfSubCallType;
    @JsonProperty("cf_no_of_times_re_opened")
    private Object cfNoOfTimesReOpened;
    @JsonProperty("cf_reopened_reason")
    private Object cfReopenedReason;
    @JsonProperty("cf_inward_date")
    private Object cfInwardDate;
    @JsonProperty("cf_actual_user_id")
    private Object cfActualUserId;
    @JsonProperty("cf_summary")
    private Object cfSummary;
    @JsonProperty("cf_elapsed_time")
    private Object cfElapsedTime;
    @JsonProperty("cf_k_bank_code")
    private Object cfKBankCode;
    @JsonProperty("cf_fa_location")
    private Object cfFaLocation;
    @JsonProperty("cf_mobile_number1")
    private Object cfMobileNumber1;
    @JsonProperty("cf_stage")
    private Object cfStage;
    @JsonProperty("cf_source")
    private Object cfSource;
    @JsonProperty("cf_hold_reason")
    private Object cfHoldReason;
    @JsonProperty("cf_should_show_replicate")
    private String cfShouldShowReplicate;
    @JsonProperty("cf_response_channel_to_client")
    private Object cfResponseChannelToClient;
    @JsonProperty("cf_replicate_in_k_bank")
    private Object cfReplicateInKBank;
    @JsonProperty("cf_fa_no")
    private Object cfFaNo;
    @JsonProperty("cf_replicate_to_k_sec")
    private Object cfReplicateToKSec;
    @JsonProperty("cf_cause_code")
    private Object cfCauseCode;
    @JsonProperty("cf_sr_nature")
    private String cfSrNature;
    @JsonProperty("cf_sr_created_by_dept")
    private Object cfSrCreatedByDept;
    @JsonProperty("cf_resolution_code")
    private Object cfResolutionCode;
    @JsonProperty("cf_account_block1")
    private Object cfAccountBlock1;
    @JsonProperty("cf_account_block2")
    private Object cfAccountBlock2;
    @JsonProperty("cf_follow_up_date")
    private Object cfFollowUpDate;
    @JsonProperty("cf_severity")
    private Object cfSeverity;
    @JsonProperty("cf_tat")
    private Object cfTat;
    @JsonProperty("cf_response_channel_from_client")
    private Object cfResponseChannelFromClient;
    @JsonProperty("cf_sr_lob")
    private Object cfSrLob;
    @JsonProperty("cf_serial_no")
    private Object cfSerialNo;
    @JsonProperty("cf_k_sec_code")
    private Object cfKSecCode;
    @JsonProperty("cf_kotak_360_id")
    private Object cfKotak360Id;
    @JsonProperty("cf_card_block")
    private Object cfCardBlock;
    @JsonProperty("cf_calling_done")
    private Object cfCallingDone;
    @JsonProperty("cf_company_name")
    private Object cfCompanyName;
    @JsonProperty("cf_kial_code")
    private Object cfKialCode;
    @JsonProperty("cf_it_type")
    private Object cfItType;
    @JsonProperty("cf_single_line_1k_sec_ticket_id")
    private String cfSingleLine1kSecTicketId;
    @JsonProperty("cf_sr_classification")
    private Object cfSrClassification;

    @JsonProperty("cf_ieco_id")
    private String customerId;


    private String cf_disposition;
    private Integer cf_attempt;
    private String cf_device_type;
    private String cf_remarks;
    private String cf_first_call_date;
    private String cf_connected_date;


}