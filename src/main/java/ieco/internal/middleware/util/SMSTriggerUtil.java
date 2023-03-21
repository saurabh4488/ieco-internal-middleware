package ieco.internal.middleware.util;

import ieco.internal.middleware.domain.request.SMSRequestVO;
import ieco.internal.middleware.domain.request.sms.SMSAPIReq;
import ieco.internal.middleware.domain.request.sms.SMSAPIReq.Header;
import ieco.internal.middleware.domain.response.GatewayTokenResponse;
import ieco.internal.middleware.domain.response.SMSResponseVO;
import ieco.internal.middleware.feignclient.GWAccessTokenClient;
import ieco.internal.middleware.feignclient.SMSClient;
import ieco.internal.middleware.xml.transformation.XMLTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import javax.xml.bind.JAXBException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class SMSTriggerUtil {

	private Logger log = LoggerFactory.getLogger(SMSTriggerUtil.class);
	@Autowired
	private XMLTransformer transformer;

	@Autowired
	private SMSClient gatewayClient;

	@Autowired
	private GWAccessTokenClient tokenClient;

	@Value("${sms.smsClientId}")
	private String clientId;

	@Value("${sms.smsClientSecret}")
	private String clientSecret;

	@Value("${finacle.grantType}")
	private String grantType;

	@Value("${sms.sourceappid}")
	private String sourceAppId;

	@Value("${sms.from}")
	private String from;
	public String sendSms(SMSRequestVO smsreq) {

		SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyHHmmss");
		SMSAPIReq sMSAPIReq = new SMSAPIReq();
		Header value = new Header();
		value.setRequestID(smsreq.getMsgReqID());
		value.setSrcAppCd(smsreq.getSrcAppCd());

		sMSAPIReq.setHeader(value);
		SMSAPIReq.Req smsRequestBody = new SMSAPIReq.Req(); // Request Body
		smsRequestBody.setMsgReqID(smsreq.getMsgReqID());
		smsRequestBody.setPriority(smsreq.getPriority());
		smsRequestBody.setSubAppID("");
		smsRequestBody.setTimestamp(sdf.format(new Date()));
		smsRequestBody.setType("TXN");

		SMSAPIReq.Req.Messages.Message messageBody = new SMSAPIReq.Req.Messages.Message(); // Message
																							// Body
		messageBody.setFrom(from);
		messageBody.setText(smsreq.getText());

		SMSAPIReq.Req.Messages.Message.Destinations.Destination destinationBody = new SMSAPIReq.Req.Messages.Message.Destinations.Destination();// destination
																																				// body
		destinationBody.setTo(smsreq.getTo());
		SMSAPIReq.Req.Messages.Message.Destinations destination = new SMSAPIReq.Req.Messages.Message.Destinations();
		destination.getDestination().add(destinationBody);

		messageBody.setDestinations(destination);
		
		SMSAPIReq.Req.Messages.Message.IndiaDlt indiaDlt = new SMSAPIReq.Req.Messages.Message.IndiaDlt();
        indiaDlt.setPrincipalEntityId(smsreq.getPrincipalId());
        indiaDlt.setContentTemplateId(smsreq.getContentTemplateId());
        messageBody.setIndiaDlt(indiaDlt);
		
		SMSAPIReq.Req.Messages message = new SMSAPIReq.Req.Messages();
		message.getMessage().add(messageBody);
		smsRequestBody.setMessages(message);

		smsRequestBody.setTrack("URL");
		smsRequestBody.setURLShortening(true);

		sMSAPIReq.setReq(smsRequestBody);

		try {
			String s = transformer.smstransformJSONToXML(sMSAPIReq);
			log.info("Payload {}", s);
			String res = gatewayClient.sendSMS(s, "Bearer " + getFinacleAccessToken());
			SMSResponseVO vo = transformer.smsftransformXMLToJSON(res);
			log.info("sms response {}", vo);
		} catch (JAXBException e) {
			log.error("error occured while transforming xml to pojo {}", e);

		}
		return "SUCCESS";
	}

	public String getFinacleAccessToken() {
		GatewayTokenResponse accessToken = null;

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		MultiValueMap<String, Object> requestMap = new LinkedMultiValueMap<String, Object>();

		requestMap.add("grant_type", grantType);
		requestMap.add("client_id", clientId);
		requestMap.add("client_secret", clientSecret);
		/*
		 * HttpEntity<MultiValueMap<String, String>> request = new
		 * HttpEntity<MultiValueMap<String, String>>(requestMap, headers);
		 * HttpEntity<String> amresponse = restTemplate.( accessTokenUrl,
		 * HttpMethod.POST, request, String.class);
		 * accessToken=mapper.readValue(amresponse.getBody(), AccessToken.class);
		 */
		accessToken = tokenClient.getToken(requestMap);
		log.info("Access token {}", accessToken.getAccess_token());
		return accessToken.getAccess_token();

	}
}
