package ieco.internal.middleware.util;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

public class JAXBUtil {
	private JAXBUtil() {
		throw new IllegalStateException("JAXBUtil Utility class");
	}
	private static Map<String, JAXBContext> map = new HashMap<>();

	/**
	 * 
	 * @param xml
	 * @param clazz
	 * @return
	 * @throws JAXBException
	 */
	public static <T> T unmarshall(String xml, Class<T> clazz) throws JAXBException {
		T obj = null;
		try {
			map.putIfAbsent(clazz.getName(), JAXBContext.newInstance(clazz));
			JAXBContext jc = map.getOrDefault(clazz.getName(), JAXBContext.newInstance(clazz));
			Unmarshaller unmarshaller = jc.createUnmarshaller();

			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			dbf.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document document = db.parse(new ByteArrayInputStream(xml.getBytes()));
			obj = clazz.cast(unmarshaller.unmarshal(document));
			return obj;
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
		return obj;
	}
	/**
	 * This method typically used to avoid namespace issue while unmarshall
	 * 
	 * @param xml
	 * @param clazz
	 * @return
	 * @throws JAXBException
	 * @throws XMLStreamException
	 */
	
	public static <T> T unmarshallByStreamReader(String xml,Class<T> clazz) throws JAXBException, XMLStreamException {

	    map.putIfAbsent(clazz.getName(), JAXBContext.newInstance(clazz));
        JAXBContext jc =  map.getOrDefault(clazz.getName() , JAXBContext.newInstance(clazz));
        XMLInputFactory xif = XMLInputFactory.newFactory();
        xif.setProperty(XMLInputFactory.IS_NAMESPACE_AWARE, false);
		xif.setProperty(XMLInputFactory.IS_SUPPORTING_EXTERNAL_ENTITIES, false);
		xif.setProperty(XMLInputFactory.SUPPORT_DTD, false);
		XMLStreamReader xsr = xif.createXMLStreamReader(new ByteArrayInputStream(xml.getBytes()));
		Unmarshaller unmarshaller = jc.createUnmarshaller();
        
        T obj = clazz.cast(unmarshaller.unmarshal(xsr));
        return obj;
    }
	/**
	 * 
	 * @param xml
	 * @param clazz
	 * @return
	 * @throws JAXBException
	 */
	public static <T> String marshall(Object xml, Class<T> clazz) throws JAXBException {
		
		StringWriter sw = new StringWriter();
		map.putIfAbsent(clazz.getName(), JAXBContext.newInstance(clazz));
		JAXBContext jc =  map.getOrDefault(clazz.getName() , JAXBContext.newInstance(clazz));
		Marshaller marshaller = jc.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		marshaller.marshal(xml , sw);
		String xmlContent = sw.toString();
		return xmlContent;
	}
	
	
public static <T> String marshallwithNameSpace(Object xml, Class<T> clazz,String xmlNs) throws JAXBException {
        
        
        StringWriter sw = new StringWriter();
        map.putIfAbsent(clazz.getName(), JAXBContext.newInstance(clazz));
        JAXBContext jc =  map.getOrDefault(clazz.getName() , JAXBContext.newInstance(clazz));
        Marshaller marshaller = jc.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, xmlNs);
        marshaller.marshal(xml , sw);
        String xmlContent = sw.toString();
        return xmlContent;
    }
	
	
	/**
     * 
     * @param xml
     * @param clazz
     * @return
     * @throws JAXBException
	 * @throws IOException 
     */
    public static <T> T unmarshallUtf16Xml(String xml, Class<T> clazz) throws JAXBException, IOException, ParserConfigurationException, SAXException {
		map.putIfAbsent(clazz.getName(), JAXBContext.newInstance(clazz));
        JAXBContext jc =  map.getOrDefault(clazz.getName() , JAXBContext.newInstance(clazz));
        Unmarshaller unmarshaller = jc.createUnmarshaller();
        T obj=null;
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			dbf.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document document = db.parse(new ByteArrayInputStream(xml.getBytes()));
			obj = clazz.cast(unmarshaller.unmarshal(document));
			return obj;
		} catch (ParserConfigurationException | SAXException e) {
			e.printStackTrace();
		}
        return obj;
    }

}
