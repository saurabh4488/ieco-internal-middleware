//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.05.29 at 12:16:38 PM IST 
//


package ieco.internal.middleware.domain.response.sms;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Header"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="SrcAppCd" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                   &lt;element name="RequestID" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="Res" maxOccurs="unbounded" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="from" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="toNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="MsgTxt" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="StatusCode" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                   &lt;element name="StatusDesc" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                   &lt;element name="AcceptanceTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "header",
    "res"
})
@XmlRootElement(name = "SMSAPIRes")
public class SMSAPIRes {

    @XmlElement(name = "Header", required = true)
    protected Header header;
    @XmlElement(name = "Res")
    protected List<Res> res;

    /**
     * Gets the value of the header property.
     * 
     * @return
     *     possible object is
     *     {@link Header }
     *     
     */
    public Header getHeader() {
        return header;
    }

    /**
     * Sets the value of the header property.
     * 
     * @param value
     *     allowed object is
     *     {@link Header }
     *     
     */
    public void setHeader(Header value) {
        this.header = value;
    }

    /**
     * Gets the value of the res property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the res property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRes().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Res }
     * 
     * 
     */
    public List<Res> getRes() {
        if (res == null) {
            res = new ArrayList<Res>();
        }
        return this.res;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;sequence&gt;
     *         &lt;element name="SrcAppCd" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *         &lt;element name="RequestID" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *       &lt;/sequence&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "srcAppCd",
        "requestID"
    })
    public static class Header {

        @XmlElement(name = "SrcAppCd", required = true)
        protected String srcAppCd;
        @XmlElement(name = "RequestID", required = true)
        protected String requestID;

        /**
         * Gets the value of the srcAppCd property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSrcAppCd() {
            return srcAppCd;
        }

        /**
         * Sets the value of the srcAppCd property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSrcAppCd(String value) {
            this.srcAppCd = value;
        }

        /**
         * Gets the value of the requestID property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getRequestID() {
            return requestID;
        }

        /**
         * Sets the value of the requestID property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setRequestID(String value) {
            this.requestID = value;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;sequence&gt;
     *         &lt;element name="from" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="toNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="MsgTxt" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="StatusCode" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *         &lt;element name="StatusDesc" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *         &lt;element name="AcceptanceTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *       &lt;/sequence&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "from",
        "toNumber",
        "msgTxt",
        "statusCode",
        "statusDesc",
        "acceptanceTime"
    })
    public static class Res {

        protected String from;
        protected String toNumber;
        @XmlElement(name = "MsgTxt")
        protected String msgTxt;
        @XmlElement(name = "StatusCode", required = true)
        protected String statusCode;
        @XmlElement(name = "StatusDesc", required = true)
        protected String statusDesc;
        @XmlElement(name = "AcceptanceTime")
        protected String acceptanceTime;

        /**
         * Gets the value of the from property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getFrom() {
            return from;
        }

        /**
         * Sets the value of the from property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setFrom(String value) {
            this.from = value;
        }

        /**
         * Gets the value of the toNumber property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getToNumber() {
            return toNumber;
        }

        /**
         * Sets the value of the toNumber property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setToNumber(String value) {
            this.toNumber = value;
        }

        /**
         * Gets the value of the msgTxt property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getMsgTxt() {
            return msgTxt;
        }

        /**
         * Sets the value of the msgTxt property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setMsgTxt(String value) {
            this.msgTxt = value;
        }

        /**
         * Gets the value of the statusCode property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getStatusCode() {
            return statusCode;
        }

        /**
         * Sets the value of the statusCode property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setStatusCode(String value) {
            this.statusCode = value;
        }

        /**
         * Gets the value of the statusDesc property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getStatusDesc() {
            return statusDesc;
        }

        /**
         * Sets the value of the statusDesc property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setStatusDesc(String value) {
            this.statusDesc = value;
        }

        /**
         * Gets the value of the acceptanceTime property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getAcceptanceTime() {
            return acceptanceTime;
        }

        /**
         * Sets the value of the acceptanceTime property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setAcceptanceTime(String value) {
            this.acceptanceTime = value;
        }

    }

}
