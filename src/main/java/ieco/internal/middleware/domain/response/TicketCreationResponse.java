package ieco.internal.middleware.domain.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "modifiedTime",
        "subCategory",
        "statusType",
        "subject",
        "dueDate",
        "departmentId",
        "channel",
        "onholdTime",
        "language",
        "resolution",
        "sharedDepartments",
        "closedTime",
        "approvalCount",
        "isTrashed",
        "createdTime",
        "id",
        "isResponseOverdue",
        "customerResponseTime",
        "productId",
        "contactId",
        "threadCount",
        "secondaryContacts",
        "priority",
        "classification",
        "commentCount",
        "taskCount",
        "accountId",
        "phone",
        "webUrl",
        "isSpam",
        "status",
        "ticketNumber"
})
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TicketCreationResponse {

    @JsonProperty("modifiedTime")
    public String modifiedTime;
    @JsonProperty("subCategory")
    public Object subCategory;
    @JsonProperty("statusType")
    public String statusType;
    @JsonProperty("subject")
    public String subject;
    @JsonProperty("dueDate")
    public Object dueDate;
    @JsonProperty("departmentId")
    public String departmentId;
    @JsonProperty("channel")
    public String channel;
    @JsonProperty("onholdTime")
    public Object onholdTime;
    @JsonProperty("language")
    public Object language;
    @JsonProperty("resolution")
    public Object resolution;
    @JsonProperty("sharedDepartments")
    public List<Object> sharedDepartments = null;
    @JsonProperty("closedTime")
    public Object closedTime;
    @JsonProperty("approvalCount")
    public String approvalCount;
    @JsonProperty("isTrashed")
    public Boolean isTrashed;
    @JsonProperty("createdTime")
    public String createdTime;
    @JsonProperty("id")
    public String id;
    @JsonProperty("isResponseOverdue")
    public Boolean isResponseOverdue;
    @JsonProperty("customerResponseTime")
    public String customerResponseTime;
    @JsonProperty("productId")
    public Object productId;
    @JsonProperty("contactId")
    public String contactId;
    @JsonProperty("threadCount")
    public String threadCount;
    @JsonProperty("secondaryContacts")
    public List<Object> secondaryContacts = null;
    @JsonProperty("priority")
    public String priority;
    @JsonProperty("classification")
    public Object classification;
    @JsonProperty("commentCount")
    public String commentCount;
    @JsonProperty("taskCount")
    public String taskCount;
    @JsonProperty("accountId")
    public Object accountId;
    @JsonProperty("phone")
    public Object phone;
    @JsonProperty("webUrl")
    public String webUrl;
    @JsonProperty("isSpam")
    public Boolean isSpam;
    @JsonProperty("status")
    public String status;
    @JsonProperty("ticketNumber")
    public String ticketNumber;

    private String message;


}