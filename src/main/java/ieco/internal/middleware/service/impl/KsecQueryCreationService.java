package ieco.internal.middleware.service.impl;

import ieco.internal.middleware.domain.request.DeskContactCreationCustomFields;
import ieco.internal.middleware.domain.request.KsecTicketCreationRequest;
import ieco.internal.middleware.domain.request.ZohoTicketUpdateRequest;
import ieco.internal.middleware.domain.response.CustomerDetailsResponse;
import ieco.internal.middleware.domain.response.KsecTicketCreationResponse;
import ieco.internal.middleware.domain.response.TicketUpdateResponse;
import ieco.internal.middleware.feignclient.KsecTicketCreationClient;
import ieco.internal.middleware.feignclient.ZohoClient;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class KsecQueryCreationService {

	private org.slf4j.Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private KsecTicketCreationClient ksecTicketCreationClient;

	@Autowired
	private OtpService otpService;

	@Autowired
	private ZohoClient zohoClient;

	@Value("${orgId}")
	private String orgid;

	public KsecTicketCreationResponse createTicket(KsecTicketCreationRequest request) {
		log.info("request received from Zoho {}", request);
		log.info("InteractionId received from Zoho {}", request.getInteractionId());
		KsecTicketCreationRequest idRequest = new KsecTicketCreationRequest();

		idRequest.setQueryId("");

		idRequest.setDpClientId("");
		idRequest.setSrNatureId(fetchId(request.getSrNature()));
		idRequest.setProductId(fetchId(request.getProduct()));
		idRequest.setSubCallTypeId(fetchId(request.getSubCallType()));

		idRequest.setSourceId(fetchId(request.getSource()));
		if (request.getSeverity() != null) {
			idRequest.setSeverityId(fetchId(request.getSeverity()));
		}

		if (request.getStatus().equalsIgnoreCase("Inter")) {
			idRequest.setDepartmentId(fetchId(request.getDepartment()));
		}
		idRequest.setStatusId(getStatusID(request.getStatus()));
		idRequest.setInwardStamp(request.getInwardStamp());
		idRequest.setRemarks(request.getRemarks());
		idRequest.setIsPayout(request.getIsPayout());
		idRequest.setPayoutAmount(request.getPayoutAmount());
		idRequest.setCallingDone(request.getCallingDone());
		idRequest.setFwdUserId(request.getFwdUserId());
		idRequest.setFwdUserIdCC(request.getFwdUserIdCC());
		idRequest.setAttachmentId(null);
		idRequest.setIsSendSMS(request.getIsSendSMS());
		idRequest.setRejectionList(request.getRejectionList());
		idRequest.setReceivedBy(request.getReceivedBy());
		idRequest.setReceivedByType(request.getReceivedByType());
		idRequest.setInteractionId(request.getInteractionId());
		idRequest.setContactId(null);
		KsecTicketCreationResponse failureResponse = new KsecTicketCreationResponse();
		try {
			String userId = getUserID(request.getUserName());
			if (userId != null) {
				idRequest.setUserId(userId);
				if (request.getIecoId() != null) {
					CustomerDetailsResponse custDetailsRes = otpService
							.getCustomerdetails(Integer.parseInt(request.getIecoId()), null);
					log.info("custDetailsRes for ksec cliend id {}", custDetailsRes.toString());
					if (custDetailsRes.getStatus().equalsIgnoreCase("200 OK")
							&& custDetailsRes.getAttrs().getUserDetails().getKsecClientCode() != null) {
						idRequest.setClientCd(custDetailsRes.getAttrs().getUserDetails().getKsecClientCode());
					} else {
						idRequest.setClientCd("123NW");
					}
				} else {
					idRequest.setClientCd("123NW");
				}

				KsecTicketCreationResponse ksecTicketCreationResponse = ksecTicketCreationClient.ksecTicketCreation(
						idRequest,
						"l68O0MJUJyiE9P6w1+CJPyRpaL8Riv+k5DnwTGn5k9w0zmxrdXhk1GFBHP3uCwfrV7eHLdyLYtJIe/WoUrlTcYWctOStvLTvkzqRlZOWPaC2BxD+CY449OuB87IESVQVcVXCcNb+OUKNQYPezuNg3h1HwngIuuUThWWfvQLAsQJ9JERABV1OrZxgIYNj7h1dtcotpYfdeTM5IY9Ld2atUaw1i0FOBVcwr4qKjcV+u56ob60XLdwhShC+fjCWkxvaM/8DoI3TbzxqX6iSvnfbC6LUeKVaa6BVtGcbMO4cisuP7qbZONddj8f8JQ6fcutReAlF/ISXGmcZjoGOrID+oA==");
				log.info("ksecTicketCreationResponse {}", ksecTicketCreationResponse);
				if (ksecTicketCreationResponse.getResult().getTable().get(0).getERRORMESSAGE() != null
						&& ksecTicketCreationResponse.getResult().getTable().get(0).getERRORMESSAGE()
								.equalsIgnoreCase("Your query has been successfully posted")) {
					ZohoTicketUpdateRequest zohoTicketUpdateRequest = new ZohoTicketUpdateRequest();
					DeskContactCreationCustomFields cf = new DeskContactCreationCustomFields();
					cf.setCf_single_line_1k_sec_ticket_id(
							ksecTicketCreationResponse.getResult().getTable().get(0).getQueryId().toPlainString());
					zohoTicketUpdateRequest.setCf(cf);
					log.info("req for updating zoho ticket details {}",zohoTicketUpdateRequest);
					TicketUpdateResponse tktUpdateRes =	zohoClient.ticketUpdate(zohoTicketUpdateRequest,
							ksecTicketCreationResponse.getResult().getTable().get(0).getInteractionId());
					log.info("res for updating zoho ticket details {}",tktUpdateRes);
					return ksecTicketCreationResponse;
				}

			} else {
				failureResponse.setMessage("UnAuthorized");
				failureResponse.setStatusCode(401);
				return failureResponse;
			}

		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.info("Contact not found in customer with IecoID:{}", request.getIecoId());

		failureResponse.setMessage("Technical Failure");
		failureResponse.setStatusCode(500);

		return failureResponse;
	}

	int fetchId(String data) {

		data = data.substring(data.lastIndexOf("(") + 1, data.lastIndexOf(")"));
		return (int) Double.parseDouble(data);
	}

	public String getUserID(String userEmail) {
		String userID = null;

		switch (userEmail) {

		case "anamika.savita@kotak.com":
			userID = "KS6894";
			break;
		case "jay.khatnani@kotak.com":
			userID = "KS5591";
			break;
		case "ritesh.s@kotak.com":
			userID = "KS10267";
			break;
		case "vimala.manoharan@kotak.com":
			userID = "KS22141";
			break;
		case "sunil.kotian@kotak.com":
			userID = "KS8681";
			break;
		case "vikaschand.dubey@kotak.com":
			userID = "KS22313";
			break;
		case "anjalismishra@kotak.com":
			userID = "KS22376";
			break;
		case "ganesh.khadka@kotak.com":
			userID = "KS20196";
			break;

		default:
			log.info("User Email Not Found !!");
		}

		return userID;
	}

	public int getStatusID(String status) {
		String statusID = null;

		switch (status) {

		case "Open":
			statusID = "1.0";
			break;
		case "Resolved Closed":
			statusID = "2.0";
			break;
		case "Inter":
			statusID = "7.0";
			break;

		default:
			log.info("Status Not Found");

		}

		return (int) Double.parseDouble(statusID);
	}

}
