//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.05.29 at 12:16:38 PM IST 
//


package ieco.internal.middleware.domain.response;


import lombok.ToString;

import javax.xml.bind.annotation.*;


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
@XmlType(name = "")
@XmlRootElement(name = "Certificate")
@ToString
public class DigiLockerAadhaarRes {

	@XmlElement(name = "CertificateData", required = true)
	protected CertificateData certificateData;

	/**
	 * @return the certificateData
	 */
	public CertificateData getCertificateData() {
		return certificateData;
	}

	/**
	 * @param certificateData the certificateData to set
	 */
	public void setCertificateData(CertificateData certificateData) {
		this.certificateData = certificateData;
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
			"kycRes"
	})
	@ToString
	public static class CertificateData {



		@XmlElement(name = "KycRes", required = true)
		protected KycRes kycRes;





		/**
		 * @return the kycRes
		 */
		public KycRes getKycRes() {
			return kycRes;
		}





		/**
		 * @param kycRes the kycRes to set
		 */
		public void setKycRes(KycRes kycRes) {
			this.kycRes = kycRes;
		}









		@XmlAccessorType(XmlAccessType.FIELD)
		@XmlType(name = "", propOrder = {
				"uidData"
		})
		@ToString
		public static class KycRes {

			@XmlElement(name = "UidData", required = true)
			protected UidData uidData;

			@XmlAttribute
			private String ts;

			@XmlAttribute
			private String txn;

			/**
			 * @return the ts
			 */
			public String getTs() {
				return ts;
			}





			/**
			 * @param ts the ts to set
			 */
			public void setTs(String ts) {
				this.ts = ts;
			}





			/**
			 * @return the txn
			 */
			public String getTxn() {
				return txn;
			}





			/**
			 * @param txn the txn to set
			 */
			public void setTxn(String txn) {
				this.txn = txn;
			}


			/**
			 * @return the uidData
			 */
			public UidData getUidData() {
				return uidData;
			}

			/**
			 * @param uidData the uidData to set
			 */
			public void setUidData(UidData uidData) {
				this.uidData = uidData;
			}

			@XmlAccessorType(XmlAccessType.FIELD)
			@XmlType(name = "", propOrder = {
					"poi",
					"poa",
					"pht",
					"tkn",
					"uid"
			})
			@ToString
			public static class UidData{

				@XmlElement(name = "Poi", required = true)
				protected POI poi;

				@XmlElement(name = "Poa", required = true)
				protected POA poa;

				@XmlElement(name = "Pht", required = true)
				private String pht;

				@XmlAttribute
				private String tkn;

				@XmlAttribute
				private String uid;
				/**
				 * @return the poi
				 */
				public POI getPoi() {
					return poi;
				}
				/**
				 * @param poi the poi to set
				 */
				public void setPoi(POI poi) {
					this.poi = poi;
				}
				/**
				 * @return the poa
				 */
				public POA getPoa() {
					return poa;
				}
				/**
				 * @param poa the poa to set
				 */
				public void setPoa(POA poa) {
					this.poa = poa;
				}
				/**
				 * @return the pht
				 */
				public String getPht() {
					return pht;
				}
				/**
				 * @param pht the pht to set
				 */
				public void setPht(String pht) {
					this.pht = pht;
				}
				/**
				 * @return the tkn
				 */
				public String getTkn() {
					return tkn;
				}
				/**
				 * @param tkn the tkn to set
				 */
				public void setTkn(String tkn) {
					this.tkn = tkn;
				}
				/**
				 * @return the uid
				 */
				public String getUid() {
					return uid;
				}
				/**
				 * @param uid the uid to set
				 */
				public void setUid(String uid) {
					this.uid = uid;
				}

				@XmlAccessorType(XmlAccessType.FIELD)
				@ToString
				public static class POI{
					@XmlAttribute
					protected String dob;
					@XmlAttribute
					protected String gender;
					@XmlAttribute
					protected String name;

					/**
					 * @return the dob
					 */
					public String getDob() {
						return dob;
					}

					/**
					 * @param dob the dob to set
					 */
					public void setDob(String dob) {
						this.dob = dob;
					}

					/**
					 * @return the gender
					 */
					public String getGender() {
						return gender;
					}

					/**
					 * @param gender the gender to set
					 */
					public void setGender(String gender) {
						this.gender = gender;
					}

					/**
					 * @return the name
					 */
					public String getName() {
						return name;
					}

					/**
					 * @param name the name to set
					 */
					public void setName(String name) {
						this.name = name;
					}


				}
				@XmlAccessorType(XmlAccessType.FIELD)
				@ToString
				public static class POA{
					@XmlAttribute
					protected String co;
					@XmlAttribute
					protected String country;
					@XmlAttribute
					protected String dist;
					@XmlAttribute
					protected String loc;
					@XmlAttribute
					protected String pc;
					@XmlAttribute
					protected String state;
					@XmlAttribute
					protected String vtc;
					@XmlAttribute
					protected String house;
					@XmlAttribute
					protected String street;
					@XmlAttribute(name="lm")
					protected String landmark;

					/**
					 * @return the co
					 */
					public String getCo() {
						return co;
					}
					/**
					 * @param co the co to set
					 */
					public void setCo(String co) {
						this.co = co;
					}
					/**
					 * @return the country
					 */
					public String getCountry() {
						return country;
					}
					/**
					 * @param country the country to set
					 */
					public void setCountry(String country) {
						this.country = country;
					}
					/**
					 * @return the dist
					 */
					public String getDist() {
						return dist;
					}
					/**
					 * @param dist the dist to set
					 */
					public void setDist(String dist) {
						this.dist = dist;
					}
					/**
					 * @return the loc
					 */
					public String getLoc() {
						return loc;
					}
					/**
					 * @param loc the loc to set
					 */
					public void setLoc(String loc) {
						this.loc = loc;
					}
					/**
					 * @return the pc
					 */
					public String getPc() {
						return pc;
					}
					/**
					 * @param pc the pc to set
					 */
					public void setPc(String pc) {
						this.pc = pc;
					}
					/**
					 * @return the state
					 */
					public String getState() {
						return state;
					}
					/**
					 * @param state the state to set
					 */
					public void setState(String state) {
						this.state = state;
					}
					/**
					 * @return the vtc
					 */
					public String getVtc() {
						return vtc;
					}
					/**
					 * @param vtc the vtc to set
					 */
					public void setVtc(String vtc) {
						this.vtc = vtc;
					}
					/**
					 * @return the house
					 */
					public String getHouse() {
						return house;
					}
					/**
					 * @param house the house to set
					 */
					public void setHouse(String house) {
						this.house = house;
					}
					/**
					 * @return the street
					 */
					public String getStreet() {
						return street;
					}
					/**
					 * @param street the street to set
					 */
					public void setStreet(String street) {
						this.street = street;
					}
					/**
					 * @return the landmark
					 */
					public String getLandmark() {
						return landmark;
					}
					/**
					 * @param landmark the landmark to set
					 */
					public void setLandmark(String landmark) {
						this.landmark = landmark;
					}


				}
			}
		}

	}



}
