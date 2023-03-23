package ieco.internal.middleware.service.impl;

import ieco.internal.middleware.domain.request.DeskContactCreationCustomFields;
import ieco.internal.middleware.domain.request.DeskContactCreationRequest;
import ieco.internal.middleware.domain.request.IECOLeadCreationRequest;
import ieco.internal.middleware.domain.response.ContactDetailsResponse;
import ieco.internal.middleware.domain.response.ZohoContactCreationResponse;
import ieco.internal.middleware.enums.ZohoLeadStage;
import ieco.internal.middleware.feignclient.ZohoClient;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DeskContactCreationService {

	public static final String ZOHO_LEAD_CREATION_RESPONSE = "ZohoLeadCreationResponse {}";
	public static final String SUCCESS = "Success";
	public static final String CONTACT_DETAILS_IN_REGISTRATION = "Contact details in registration {}";
	public static final String VERIFIED = "Verified";
	public static final String CONTACT = "Contact";
	public static final String FAILURE = "Failure";
	public static final String DUPLICATE = "Duplicate";
	@Autowired
	private ZohoClient zohoClient;

	@Value("${orgId}")
	private String orgId;

	/*
	 * @Retryable(value = { Exception.class }, maxAttempts = 4, backoff
	 * = @Backoff(delay = 200, multiplier = 2, maxDelay = 600))
	 */
	public ZohoContactCreationResponse contactCreationBk(IECOLeadCreationRequest request) {

		org.slf4j.Logger log = LoggerFactory.getLogger(this.getClass());
		ZohoContactCreationResponse response = null;
		log.info("zoho enabled");
		log.info("Request received from ieco {}", request);
		try {
			if (request.getFirstName() == null || request.getFirstName().length() == 0) {
				request.setFirstName("NA");
			}

			if (request.getLastName() == null || request.getLastName().length() == 0) {
				request.setLastName("NA");
			}

			ResponseEntity<ContactDetailsResponse> searchContactRes = zohoClient
					.searchContact("email" + ":" + request.getEmail());
			log.info("contactCreation--Searching contact response in zoho :{}", searchContactRes);
			log.info("Dedupe check for before creating contact at zoho for email {} {}",
					searchContactRes.getStatusCodeValue(), request.getEmail());
			ContactDetailsResponse contactDetails;
			// contact not found and otp verified, creating user in zoho
			if (searchContactRes.getStatusCodeValue() == 204
					&& request.getStage().equalsIgnoreCase(ZohoLeadStage.OTP_VERIFIED.getValue())) {

				DeskContactCreationCustomFields deskContactCreationCustomFields = DeskContactCreationCustomFields
						.builder().cf_environment(request.getEnvironment())
						.cf_lead_reference_id(request.getCustomerId()).build();

				DeskContactCreationRequest ccrequest = DeskContactCreationRequest.builder().email(request.getEmail())
						.firstName(request.getFirstName()).lastName(request.getLastName()).mobile(request.getMobile())
						.type("Lead").cf(deskContactCreationCustomFields).build();
				log.info("ZohoLeadCreationResponse for the email {}", request.getEmail());
				ZohoContactCreationResponse res = zohoClient.contactCreate(ccrequest);
				log.info(ZOHO_LEAD_CREATION_RESPONSE, res);
				res.setStatus(SUCCESS);
				return res;
				// contact found and registration is success, updating user in zoho
			}else if (searchContactRes.getStatusCodeValue() == 200
					&& request.getStage().equalsIgnoreCase(ZohoLeadStage.OTP_VERIFIED.getValue())) {

				DeskContactCreationCustomFields deskContactCreationCustomFields = DeskContactCreationCustomFields
						.builder().cf_environment(request.getEnvironment())
						.cf_lead_reference_id(request.getCustomerId()).build();

				DeskContactCreationRequest ccrequest = DeskContactCreationRequest.builder().email(request.getEmail())
						.firstName(request.getFirstName()).lastName(request.getLastName()).mobile(request.getMobile())
						.type("Lead").cf(deskContactCreationCustomFields).build();
				log.info("ZohoLeadCreationResponse for the email {}", request.getEmail());
				ZohoContactCreationResponse res = zohoClient.contactUpdate(ccrequest,
						searchContactRes.getBody().getContactResponse().get(0).getId());
				log.info(ZOHO_LEAD_CREATION_RESPONSE, res);
				res.setStatus(SUCCESS);
				return res;
				// contact found and registration is success, updating user in zoho
			}
			
			
			
			else if (searchContactRes.getStatusCodeValue() == 200
					&& request.getStage().equalsIgnoreCase(ZohoLeadStage.REGISTRATION_SUCCESSFUL.getValue()) ) {
				log.info(CONTACT_DETAILS_IN_REGISTRATION, searchContactRes.getBody().toString());
				DeskContactCreationCustomFields cf = searchContactRes.getBody().getContactResponse().get(0).getCf();

				DeskContactCreationCustomFields deskContactCreationCustomFields = DeskContactCreationCustomFields
						.builder().cf_kotak_360(request.getCustomerId()).cf_environment(request.getEnvironment())
						.firstName(request.getFirstName()).cf_utm_campaign(cf.getCf_utm_campaign())
						.cf_utm_content(cf.getCf_utm_content()).cf_utm_medium(cf.getCf_utm_medium())
						.cf_utm_source(cf.getCf_utm_source()).cf_utm_term(cf.getCf_utm_term())
						.cf_lead_verified(VERIFIED).cf_environment(request.getEnvironment()).cf_gender(request.getGender()).build();

				DeskContactCreationRequest ccrequest = DeskContactCreationRequest.builder().email(request.getEmail())
						.lastName(request.getLastName()).firstName(request.getFirstName()).mobile("").type(CONTACT)
						.cf(deskContactCreationCustomFields).build();
				log.info("ZohoContactCreationResponse for the email {}", request.getEmail());
				ZohoContactCreationResponse res = zohoClient.contactUpdate(ccrequest,
						searchContactRes.getBody().getContactResponse().get(0).getId());
				log.info("ZohoContactCreationResponse for the email {} {}", request.getEmail(), res);
				res.setStatus(SUCCESS);
				return res;
				// contact not found and registration is success
			} else if (searchContactRes.getStatusCodeValue() == 204
					&& request.getStage().equalsIgnoreCase("REGISTRATION_SUCCESSFUL")) {
				log.info("Unable to convert to contact as user doesn't existed in zoho as a lead for the email {}",
						request.getEmail());
				ZohoContactCreationResponse res = ZohoContactCreationResponse.builder().status(FAILURE)
						.errMessage("Unable to convert to contact as user doesn't existed in zoho as a lead").build();

				return res;// contact found and otp verified is success
			}  else if (searchContactRes.getStatusCodeValue() == 200
					&& searchContactRes.getBody().getContactResponse().get(0).getType().equalsIgnoreCase(CONTACT)
					&& request.getStage().equalsIgnoreCase(ZohoLeadStage.CRN_CLIENT_CODE_CREATED.getValue())) {

				DeskContactCreationCustomFields deskContactCreationCustomFields = DeskContactCreationCustomFields
						.builder().cf_crn_present(request.getCrnCreated())
						.cf_client_code_present(request.getClientCodeCreated())
						.cf_products_purchased(String.join(";", request.getProductsPurchased())).build();

				DeskContactCreationRequest ccrequest = DeskContactCreationRequest.builder()
						.cf(deskContactCreationCustomFields).build();

				ZohoContactCreationResponse res = zohoClient.contactUpdate(ccrequest,
						searchContactRes.getBody().getContactResponse().get(0).getId());
				res.setStatus(SUCCESS);
				return res;
			}

			ZohoContactCreationResponse res = new ZohoContactCreationResponse();
			res.setStatus(FAILURE);
			res.setErrMessage("Technical Failure");
			return res;
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Contact creation Failed for ieco {}", request);
			log.error("Exception in contactCreation {}", e.getMessage());
			response = new ZohoContactCreationResponse();
			response.setStatus("Failed");
			response.setErrMessage(e.getMessage());
		}

		return null;
	}

	public ZohoContactCreationResponse contactCreation(IECOLeadCreationRequest request) {

		org.slf4j.Logger log = LoggerFactory.getLogger(this.getClass());
		ZohoContactCreationResponse response = null;
		log.info("zoho enabled");
		log.info("Request received from ieco {}", request);
		try {
			if (request.getFirstName() == null || request.getFirstName().length() == 0) {
				request.setFirstName("NA");
			}

			if (request.getLastName() == null || request.getLastName().length() == 0) {
				request.setLastName("NA");
			}

			ResponseEntity<ContactDetailsResponse> searchContactRes = zohoClient
					.searchContact("email" + ":" + request.getEmail());
			log.info("contactCreation--Searching contact response in zoho :{}", searchContactRes);
			log.info("Dedupe check for before creating contact at zoho for email {} {}",
					searchContactRes.getStatusCodeValue(), request.getEmail());
			ContactDetailsResponse contactDetails;

			if (searchContactRes.getStatusCodeValue() == 204) {
				if (request.getStage() != null) {
					if (request.getStage().equalsIgnoreCase(ZohoLeadStage.OTP_VERIFIED.getValue())) {
						DeskContactCreationCustomFields deskContactCreationCustomFields = DeskContactCreationCustomFields
								.builder().cf_environment(request.getEnvironment())
								.cf_lead_reference_id(request.getCustomerId()).build();

						DeskContactCreationRequest ccrequest = DeskContactCreationRequest.builder()
								.email(request.getEmail()).firstName(request.getFirstName())
								.lastName(request.getLastName()).mobile(request.getMobile()).type("Lead")
								.cf(deskContactCreationCustomFields).build();

						ZohoContactCreationResponse res = zohoClient.contactCreate(ccrequest);
						log.info(ZOHO_LEAD_CREATION_RESPONSE, res);
						res.setStatus(SUCCESS);
						return res;
					}
					if (request.getStage().equalsIgnoreCase(ZohoLeadStage.REGISTRATION_SUCCESSFUL.getValue())) {
						ZohoContactCreationResponse res = ZohoContactCreationResponse.builder().status(FAILURE)
								.errMessage("Unable to convert to contact as user doesn't existed in zoho as a lead")
								.build();

						return res;
					}
				} else {
					ZohoContactCreationResponse res = new ZohoContactCreationResponse();
					res.setStatus(FAILURE);
					res.setErrMessage("Contact Stage cannot be Empty");
					return res;
				}
			}
			if (searchContactRes.getStatusCodeValue() == 200) {
				if (request.getStage() != null) {
					if (request.getStage().equalsIgnoreCase(ZohoLeadStage.REGISTRATION_SUCCESSFUL.getValue())) {
						log.info(CONTACT_DETAILS_IN_REGISTRATION, searchContactRes.getBody().toString());
						DeskContactCreationCustomFields cf = searchContactRes.getBody().getContactResponse().get(0)
								.getCf();
						DeskContactCreationCustomFields deskContactCreationCustomFields = DeskContactCreationCustomFields
								.builder().cf_kotak_360(request.getCustomerId())
								.cf_environment(request.getEnvironment()).cf_ieco_id(request.getCustomerId())
								.firstName(request.getFirstName()).cf_utm_campaign(cf.getCf_utm_campaign())
								.cf_utm_content(cf.getCf_utm_content()).cf_utm_medium(cf.getCf_utm_medium())
								.cf_utm_source(cf.getCf_utm_source()).cf_utm_term(cf.getCf_utm_term())
								.cf_lead_verified(VERIFIED).cf_environment(request.getEnvironment())
								.cf_customer_type(request.isDistributionFlag() ? "Distribution" : "Advisory").cf_gender(request.getGender()).build();

						DeskContactCreationRequest ccrequest = DeskContactCreationRequest.builder()
								.email(request.getEmail()).lastName(request.getLastName())
								.firstName(request.getFirstName()).mobile("").type(CONTACT)
								.cf(deskContactCreationCustomFields).build();

						ZohoContactCreationResponse res = zohoClient.contactUpdate(ccrequest,
								searchContactRes.getBody().getContactResponse().get(0).getId());
						log.info("ZohoContactCreationResponse {}", res);
						res.setStatus(SUCCESS);
						return res;
					}

					if (request.getStage().equalsIgnoreCase(ZohoLeadStage.DISTRIBUTION.getValue()) && searchContactRes.getBody().getContactResponse().get(0).getType() != null &&  searchContactRes.getBody().getContactResponse().get(0).getType()
							.equalsIgnoreCase(CONTACT)){
								DeskContactCreationCustomFields deskContactCreationCustomFields = DeskContactCreationCustomFields
										.builder()
										.cf_customer_type(request.isDistributionFlag() ? "Distribution" : "Advisory")
										.build();

								DeskContactCreationRequest ccrequest = DeskContactCreationRequest.builder()
										.cf(deskContactCreationCustomFields).build();

								ZohoContactCreationResponse res = zohoClient.contactUpdate(ccrequest,
										searchContactRes.getBody().getContactResponse().get(0).getId());
								log.info("ZohocontactupdateResponse {}", res);
								res.setStatus(SUCCESS);
								return res;
					}
					if (request.getStage().equalsIgnoreCase(ZohoLeadStage.CRN_CLIENT_CODE_CREATED.getValue()) && searchContactRes.getBody().getContactResponse().get(0).getType() != null && searchContactRes.getBody().getContactResponse().get(0).getType()
							.equalsIgnoreCase(CONTACT)) {
								DeskContactCreationCustomFields deskContactCreationCustomFields = DeskContactCreationCustomFields
										.builder().cf_crn_present(request.getCrnCreated())
										.cf_client_code_present(request.getClientCodeCreated())
										.cf_products_purchased(String.join(";", request.getProductsPurchased()))
										.build();

								DeskContactCreationRequest ccrequest = DeskContactCreationRequest.builder()
										.cf(deskContactCreationCustomFields).build();

								ZohoContactCreationResponse res = zohoClient.contactUpdate(ccrequest,
										searchContactRes.getBody().getContactResponse().get(0).getId());
								log.info("ZohocontactupdateResponse {}", res);
								res.setStatus(SUCCESS);
								return res;
					}
					if (searchContactRes.getBody().getContactResponse().get(0).getType() != null) {
						if (searchContactRes.getBody().getContactResponse().get(0).getType().equalsIgnoreCase("Lead")) {

							DeskContactCreationCustomFields deskContactCreationCustomFields = DeskContactCreationCustomFields
									.builder().cf_lead_reference_id(searchContactRes.getBody().getContactResponse()
											.get(0).getCf().getCf_lead_reference_id())
									.build();
							log.info("Lead has been existed with email Id");
							ZohoContactCreationResponse res = ZohoContactCreationResponse.builder().status(DUPLICATE)
									.cf(deskContactCreationCustomFields)
									.lastName(searchContactRes.getBody().getContactResponse().get(0).getLastName())
									.mobile(searchContactRes.getBody().getContactResponse().get(0).getMobile())
									.id(searchContactRes.getBody().getContactResponse().get(0).getId())
									.errMessage("Lead has been existed with email Id " + request.getEmail()).build();

							return res;
						}
						if (searchContactRes.getBody().getContactResponse().get(0).getType()
								.equalsIgnoreCase(CONTACT)) {
							new DeskContactCreationCustomFields();
							DeskContactCreationCustomFields deskContactCreationCustomFields = DeskContactCreationCustomFields
									.builder().cf_lead_reference_id("").build();
							log.info("Contact has been existed with email Id");
							ZohoContactCreationResponse res = ZohoContactCreationResponse.builder()
									.cf(deskContactCreationCustomFields)
									.lastName(searchContactRes.getBody().getContactResponse().get(0).getLastName())
									.mobile(searchContactRes.getBody().getContactResponse().get(0).getMobile())
									.id(searchContactRes.getBody().getContactResponse().get(0).getId())
									.type(searchContactRes.getBody().getContactResponse().get(0).getType())
									.status(DUPLICATE)
									.errMessage("Contact has been existed with email Id " + request.getEmail()).build();

							return res;
						}
					} else {
						ZohoContactCreationResponse res = new ZohoContactCreationResponse();
						res.setStatus(FAILURE);
						res.setErrMessage("Contact Type cannot be Empty");
						return res;
					}

				} else {
					ZohoContactCreationResponse res = new ZohoContactCreationResponse();
					res.setStatus(FAILURE);
					res.setErrMessage("Contact Stage cannot be Empty");
					return res;
				}

			}

			ZohoContactCreationResponse res = new ZohoContactCreationResponse();
			res.setStatus(FAILURE);
			res.setErrMessage("Technical Failure");
			return res;
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Contact creation Failed for ieco {}", request);
			log.error("Exception in contactCreation {}", e.getMessage());
			response = new ZohoContactCreationResponse();
			response.setStatus("Failed");
			response.setErrMessage(e.getMessage());
		}

		return null;
	}

}
