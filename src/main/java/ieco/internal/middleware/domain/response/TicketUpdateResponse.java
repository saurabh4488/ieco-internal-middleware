
package ieco.internal.middleware.domain.response;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "ticketNumber",
    "modifiedTime",
    "subCategory",
    "statusType",
    "subject",
    "dueDate",
    "departmentId",
    "channel",
    "description",
    "onholdTime",
    "resolution",
    "sharedDepartments",
    "closedTime",
    "approvalCount",
    "timeEntryCount",
    "channelRelatedInfo",
    "responseDueDate",
    "isDeleted",
    "isTrashed",
    "createdTime",
    "modifiedBy",
    "id",
    "isResponseOverdue",
    "email",
    "customerResponseTime",
    "cf",
    "productId",
    "contactId",
    "threadCount",
    "secondaryContacts",
    "priority",
    "classification",
    "assigneeId",
    "commentCount",
    "taskCount",
    "accountId",
    "phone",
    "webUrl",
    "teamId",
    "attachmentCount",
    "isSpam",
    "category",
    "status"
})
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Getter
@Setter
@ToString
public class TicketUpdateResponse {

    @JsonProperty("ticketNumber")
    private String ticketNumber;
    @JsonProperty("modifiedTime")
    private String modifiedTime;
    @JsonProperty("subCategory")
    private Object subCategory;
    @JsonProperty("statusType")
    private String statusType;
    @JsonProperty("subject")
    private String subject;
    @JsonProperty("dueDate")
    private Object dueDate;
    @JsonProperty("departmentId")
    private String departmentId;
    @JsonProperty("channel")
    private String channel;
    @JsonProperty("description")
    private String description;
    @JsonProperty("onholdTime")
    private Object onholdTime;
    @JsonProperty("resolution")
    private Object resolution;
    @JsonProperty("sharedDepartments")
    private List<Object> sharedDepartments = null;
    @JsonProperty("closedTime")
    private Object closedTime;
    @JsonProperty("approvalCount")
    private String approvalCount;
    @JsonProperty("timeEntryCount")
    private String timeEntryCount;
    @JsonProperty("channelRelatedInfo")
    private Object channelRelatedInfo;
    @JsonProperty("responseDueDate")
    private Object responseDueDate;
    @JsonProperty("isDeleted")
    private Boolean isDeleted;
    @JsonProperty("isTrashed")
    private Boolean isTrashed;
    @JsonProperty("createdTime")
    private String createdTime;
    @JsonProperty("modifiedBy")
    private String modifiedBy;
    @JsonProperty("id")
    private String id;
    @JsonProperty("isResponseOverdue")
    private Boolean isResponseOverdue;
    @JsonProperty("email")
    private Object email;
    @JsonProperty("customerResponseTime")
    private String customerResponseTime;
    @JsonProperty("cf")
    private Cf cf;
    @JsonProperty("productId")
    private Object productId;
    @JsonProperty("contactId")
    private String contactId;
    @JsonProperty("threadCount")
    private String threadCount;
    @JsonProperty("secondaryContacts")
    private List<Object> secondaryContacts = null;
    @JsonProperty("priority")
    private Object priority;
    @JsonProperty("classification")
    private Object classification;
    @JsonProperty("assigneeId")
    private Object assigneeId;
    @JsonProperty("commentCount")
    private String commentCount;
    @JsonProperty("taskCount")
    private String taskCount;
    @JsonProperty("accountId")
    private Object accountId;
    @JsonProperty("phone")
    private Object phone;
    @JsonProperty("webUrl")
    private String webUrl;
    @JsonProperty("teamId")
    private Object teamId;
    @JsonProperty("attachmentCount")
    private String attachmentCount;
    @JsonProperty("isSpam")
    private Boolean isSpam;
    @JsonProperty("category")
    private Object category;
    @JsonProperty("status")
    private String status;
    
    private String interactionId;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private String errorCode;
	private String message;
    @JsonProperty("ticketNumber")
    public String getTicketNumber() {
        return ticketNumber;
    }

    @JsonProperty("ticketNumber")
    public void setTicketNumber(String ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    @JsonProperty("modifiedTime")
    public String getModifiedTime() {
        return modifiedTime;
    }

    @JsonProperty("modifiedTime")
    public void setModifiedTime(String modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    @JsonProperty("subCategory")
    public Object getSubCategory() {
        return subCategory;
    }

    @JsonProperty("subCategory")
    public void setSubCategory(Object subCategory) {
        this.subCategory = subCategory;
    }

    @JsonProperty("statusType")
    public String getStatusType() {
        return statusType;
    }

    @JsonProperty("statusType")
    public void setStatusType(String statusType) {
        this.statusType = statusType;
    }

    @JsonProperty("subject")
    public String getSubject() {
        return subject;
    }

    @JsonProperty("subject")
    public void setSubject(String subject) {
        this.subject = subject;
    }

    @JsonProperty("dueDate")
    public Object getDueDate() {
        return dueDate;
    }

    @JsonProperty("dueDate")
    public void setDueDate(Object dueDate) {
        this.dueDate = dueDate;
    }

    @JsonProperty("departmentId")
    public String getDepartmentId() {
        return departmentId;
    }

    @JsonProperty("departmentId")
    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    @JsonProperty("channel")
    public String getChannel() {
        return channel;
    }

    @JsonProperty("channel")
    public void setChannel(String channel) {
        this.channel = channel;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("onholdTime")
    public Object getOnholdTime() {
        return onholdTime;
    }

    @JsonProperty("onholdTime")
    public void setOnholdTime(Object onholdTime) {
        this.onholdTime = onholdTime;
    }

    @JsonProperty("resolution")
    public Object getResolution() {
        return resolution;
    }

    @JsonProperty("resolution")
    public void setResolution(Object resolution) {
        this.resolution = resolution;
    }

    @JsonProperty("sharedDepartments")
    public List<Object> getSharedDepartments() {
        return sharedDepartments;
    }

    @JsonProperty("sharedDepartments")
    public void setSharedDepartments(List<Object> sharedDepartments) {
        this.sharedDepartments = sharedDepartments;
    }

    @JsonProperty("closedTime")
    public Object getClosedTime() {
        return closedTime;
    }

    @JsonProperty("closedTime")
    public void setClosedTime(Object closedTime) {
        this.closedTime = closedTime;
    }

    @JsonProperty("approvalCount")
    public String getApprovalCount() {
        return approvalCount;
    }

    @JsonProperty("approvalCount")
    public void setApprovalCount(String approvalCount) {
        this.approvalCount = approvalCount;
    }

    @JsonProperty("timeEntryCount")
    public String getTimeEntryCount() {
        return timeEntryCount;
    }

    @JsonProperty("timeEntryCount")
    public void setTimeEntryCount(String timeEntryCount) {
        this.timeEntryCount = timeEntryCount;
    }

    @JsonProperty("channelRelatedInfo")
    public Object getChannelRelatedInfo() {
        return channelRelatedInfo;
    }

    @JsonProperty("channelRelatedInfo")
    public void setChannelRelatedInfo(Object channelRelatedInfo) {
        this.channelRelatedInfo = channelRelatedInfo;
    }

    @JsonProperty("responseDueDate")
    public Object getResponseDueDate() {
        return responseDueDate;
    }

    @JsonProperty("responseDueDate")
    public void setResponseDueDate(Object responseDueDate) {
        this.responseDueDate = responseDueDate;
    }

    @JsonProperty("isDeleted")
    public Boolean getIsDeleted() {
        return isDeleted;
    }

    @JsonProperty("isDeleted")
    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    @JsonProperty("isTrashed")
    public Boolean getIsTrashed() {
        return isTrashed;
    }

    @JsonProperty("isTrashed")
    public void setIsTrashed(Boolean isTrashed) {
        this.isTrashed = isTrashed;
    }

    @JsonProperty("createdTime")
    public String getCreatedTime() {
        return createdTime;
    }

    @JsonProperty("createdTime")
    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    @JsonProperty("modifiedBy")
    public String getModifiedBy() {
        return modifiedBy;
    }

    @JsonProperty("modifiedBy")
    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("isResponseOverdue")
    public Boolean getIsResponseOverdue() {
        return isResponseOverdue;
    }

    @JsonProperty("isResponseOverdue")
    public void setIsResponseOverdue(Boolean isResponseOverdue) {
        this.isResponseOverdue = isResponseOverdue;
    }

    @JsonProperty("email")
    public Object getEmail() {
        return email;
    }

    @JsonProperty("email")
    public void setEmail(Object email) {
        this.email = email;
    }

    @JsonProperty("customerResponseTime")
    public String getCustomerResponseTime() {
        return customerResponseTime;
    }

    @JsonProperty("customerResponseTime")
    public void setCustomerResponseTime(String customerResponseTime) {
        this.customerResponseTime = customerResponseTime;
    }

    @JsonProperty("cf")
    public Cf getCf() {
        return cf;
    }

    @JsonProperty("cf")
    public void setCf(Cf cf) {
        this.cf = cf;
    }

    @JsonProperty("productId")
    public Object getProductId() {
        return productId;
    }

    @JsonProperty("productId")
    public void setProductId(Object productId) {
        this.productId = productId;
    }

    @JsonProperty("contactId")
    public String getContactId() {
        return contactId;
    }

    @JsonProperty("contactId")
    public void setContactId(String contactId) {
        this.contactId = contactId;
    }

    @JsonProperty("threadCount")
    public String getThreadCount() {
        return threadCount;
    }

    @JsonProperty("threadCount")
    public void setThreadCount(String threadCount) {
        this.threadCount = threadCount;
    }

    @JsonProperty("secondaryContacts")
    public List<Object> getSecondaryContacts() {
        return secondaryContacts;
    }

    @JsonProperty("secondaryContacts")
    public void setSecondaryContacts(List<Object> secondaryContacts) {
        this.secondaryContacts = secondaryContacts;
    }

    @JsonProperty("priority")
    public Object getPriority() {
        return priority;
    }

    @JsonProperty("priority")
    public void setPriority(Object priority) {
        this.priority = priority;
    }

    @JsonProperty("classification")
    public Object getClassification() {
        return classification;
    }

    @JsonProperty("classification")
    public void setClassification(Object classification) {
        this.classification = classification;
    }

    @JsonProperty("assigneeId")
    public Object getAssigneeId() {
        return assigneeId;
    }

    @JsonProperty("assigneeId")
    public void setAssigneeId(Object assigneeId) {
        this.assigneeId = assigneeId;
    }

    @JsonProperty("commentCount")
    public String getCommentCount() {
        return commentCount;
    }

    @JsonProperty("commentCount")
    public void setCommentCount(String commentCount) {
        this.commentCount = commentCount;
    }

    @JsonProperty("taskCount")
    public String getTaskCount() {
        return taskCount;
    }

    @JsonProperty("taskCount")
    public void setTaskCount(String taskCount) {
        this.taskCount = taskCount;
    }

    @JsonProperty("accountId")
    public Object getAccountId() {
        return accountId;
    }

    @JsonProperty("accountId")
    public void setAccountId(Object accountId) {
        this.accountId = accountId;
    }

    @JsonProperty("phone")
    public Object getPhone() {
        return phone;
    }

    @JsonProperty("phone")
    public void setPhone(Object phone) {
        this.phone = phone;
    }

    @JsonProperty("webUrl")
    public String getWebUrl() {
        return webUrl;
    }

    @JsonProperty("webUrl")
    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    @JsonProperty("teamId")
    public Object getTeamId() {
        return teamId;
    }

    @JsonProperty("teamId")
    public void setTeamId(Object teamId) {
        this.teamId = teamId;
    }

    @JsonProperty("attachmentCount")
    public String getAttachmentCount() {
        return attachmentCount;
    }

    @JsonProperty("attachmentCount")
    public void setAttachmentCount(String attachmentCount) {
        this.attachmentCount = attachmentCount;
    }

    @JsonProperty("isSpam")
    public Boolean getIsSpam() {
        return isSpam;
    }

    @JsonProperty("isSpam")
    public void setIsSpam(Boolean isSpam) {
        this.isSpam = isSpam;
    }

    @JsonProperty("category")
    public Object getCategory() {
        return category;
    }

    @JsonProperty("category")
    public void setCategory(Object category) {
        this.category = category;
    }

    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(String status) {
        this.status = status;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TicketUpdateResponse [ticketNumber=" + ticketNumber + ", modifiedTime=" + modifiedTime
				+ ", subCategory=" + subCategory + ", statusType=" + statusType + ", subject=" + subject + ", dueDate="
				+ dueDate + ", departmentId=" + departmentId + ", channel=" + channel + ", description=" + description
				+ ", onholdTime=" + onholdTime + ", resolution=" + resolution + ", sharedDepartments="
				+ sharedDepartments + ", closedTime=" + closedTime + ", approvalCount=" + approvalCount
				+ ", timeEntryCount=" + timeEntryCount + ", channelRelatedInfo=" + channelRelatedInfo
				+ ", responseDueDate=" + responseDueDate + ", isDeleted=" + isDeleted + ", isTrashed=" + isTrashed
				+ ", createdTime=" + createdTime + ", modifiedBy=" + modifiedBy + ", id=" + id + ", isResponseOverdue="
				+ isResponseOverdue + ", email=" + email + ", customerResponseTime=" + customerResponseTime + ", cf="
				+ cf + ", productId=" + productId + ", contactId=" + contactId + ", threadCount=" + threadCount
				+ ", secondaryContacts=" + secondaryContacts + ", priority=" + priority + ", classification="
				+ classification + ", assigneeId=" + assigneeId + ", commentCount=" + commentCount + ", taskCount="
				+ taskCount + ", accountId=" + accountId + ", phone=" + phone + ", webUrl=" + webUrl + ", teamId="
				+ teamId + ", attachmentCount=" + attachmentCount + ", isSpam=" + isSpam + ", category=" + category
				+ ", status=" + status + ", additionalProperties=" + additionalProperties + "]";
	}

}