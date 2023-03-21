package ieco.internal.middleware.transformation;

import ieco.internal.middleware.domain.request.sms.SMSAPIReq;
import ieco.internal.middleware.domain.response.SMSResponseVO;
import ieco.internal.middleware.domain.response.sms.SMSAPIRes;
import ieco.internal.middleware.util.FinacleUtils;
import ieco.internal.middleware.util.JAXBUtil;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBException;

@Component
public class XMLTransformer {

	private Logger log = LoggerFactory.getLogger(XMLTransformer.class);

	@Autowired
	private ModelMapper modelMapper;

	@Value("${finacle.clientSecret}")
	private String encryptionkey;

	@Value("${sms.smsClientId}")
	private String smsClientId;

	@Value("${sms.smsClientSecret}")
	private String smsClientSecret;

	@Value("${sms.encKey}")
	private String smsEncKey;

	public String smstransformJSONToXML(SMSAPIReq sMSAPIReq) throws JAXBException {

		String xmlContent = null;
		String encryptedXml = null;
		try {

			xmlContent = JAXBUtil.marshall(sMSAPIReq, SMSAPIReq.class);
			log.info("Send SMS Request Body:{}", xmlContent);
			xmlContent = xmlContent.replace("xmlns=\"http://www.kotak.com/schemas/SMSAPIReq/SMSAPIReq.xsd\"", "");
			encryptedXml = FinacleUtils.encrypt(xmlContent, smsEncKey);
			log.info("Send SMS encrypted Request Body:{}", encryptedXml);

		} catch (Exception e) {
			log.error("Error occurred while smstransformJSONToXML", e);
		}

		return encryptedXml;
	}

	/**
	 * 
	 * @param message
	 * @return
	 * @throws JAXBException
	 */
	public SMSResponseVO smsftransformXMLToJSON(String message) throws JAXBException {
		SMSResponseVO smsResponseVO = null;
		try {
			String encryptedResponseXml = message;
			log.info("send SMS encrypted response:{}", encryptedResponseXml);

			String decryptedXml = FinacleUtils.decrypt(message, smsEncKey);

			log.info("send SMS decrypted response:{}", decryptedXml);

			SMSAPIRes smsapiRes = JAXBUtil.unmarshall(decryptedXml.replace("xmlns=\"\"",
					"xmlns=\"http://www.kotak.com/schemas/SMSAPIRes/SMSAPIRes.xsd\""), SMSAPIRes.class);

			smsResponseVO = modelMapper.map(smsapiRes, SMSResponseVO.class);

		} catch (Exception e) {
			log.error("Error occurred while smsftransformXMLToJSON", e);
		}

		return smsResponseVO;
	}

}
