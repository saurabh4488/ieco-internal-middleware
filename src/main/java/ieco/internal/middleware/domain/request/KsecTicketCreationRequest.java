package ieco.internal.middleware.domain.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.validation.constraints.NotBlank;

@JsonInclude(JsonInclude.Include.NON_NULL)

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class KsecTicketCreationRequest {

	
private String userId;

private String queryId;

private String clientCd;

private String dpClientId;

private Integer srNatureId;

private Integer productId;

private Integer subCallTypeId;

private Integer sourceId;

private Integer severityId;

private Integer statusId;

private Integer departmentId;

private String inwardStamp;

private String remarks;

private Object isPayout;

private Object payoutAmount;

private Object callingDone;

private Object fwdUserId;

private Object fwdUserIdCC;

private Object attachmentId;

private Object isSendSMS;

private String rejectionList;

private Object receivedBy;

private Object receivedByType;

private String interactionId;

private String contactId;

@NotBlank(message = "srNature is mandatory")
private String srNature;
@NotBlank(message = "product is mandatory")
private String product;
@NotBlank(message = "subCallType is mandatory")
private String subCallType;
@NotBlank(message = "source is mandatory")
private String source;
//@NotBlank(message = "severityId is mandatory")
private String severity;
@NotBlank(message = "statusId is mandatory")
private String status;

private String department;
@NotBlank(message = "userName is mandatory")
private String userName;
//@NotBlank(message = "iecoId is mandatory")
private String iecoId;


}