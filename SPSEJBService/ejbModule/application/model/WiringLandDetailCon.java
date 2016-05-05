package application.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the WIRING_LAND_DETAIL database table.
 * 
 */
@Entity
@Table(name="WIRING_LAND_DETAIL_CON")
public class WiringLandDetailCon implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private WiringLandDetailConPK id;

	@Column(name="CON_REF_NO")
	private String consumerRefNo;

	@Column(name="AREA_CODE")
	private String areaCode;

	@Column(name="FUND_SOURCE")
	private String fundSource;

	@Column(name="REPRESENTATIVE")
	private String representative;

	@Column(name="SCHEME_NO")
	private String schemeNo;

	@Column(name="SCHEME_NAME")
	private String schemeName;

	@Column(name="IS_ELECTRICITY_HAVING")
	private String isElectricityhave;

	@Column(name="DES_OF_PREMISES")
	private String desOfPremises;

	@Column(name="ELECTORATE")
	private String electorate;

	@Column(name="DEV_SEC")
	private String devSec;

	@Column(name="GS_DIVISION")
	private String gsDivision;

	@Column(name="AGA_DIVISION")
	private String agaDivision;
	
	@Column(name="DISTRICT")
	private String district;


	@Column(name="NO_OF_METER_POINT")
	private BigDecimal noOfMeterPoints;

	@Column(name="CAP_OF_SERVICE")
	private BigDecimal capacityofService;
	
	@Column(name="REPRESENTATIVE2")
	private String representative2;

	@Column(name="REPCONTACT")
	private String repContact1;
	
	@Column(name="REP2CONTACT")
	private String repContact2;
	
	
	@Column(name="SERVICEDEPONAME")
	private String ServiceDepoName;
	
	@Column(name="ACCOUNT_NOS")
	private String acountNos;

    public WiringLandDetailConPK getId() {
		return id;
	}


	public void setId(WiringLandDetailConPK id) {
		this.id = id;
	}


	public String getConsumerRefNo() {
		return consumerRefNo;
	}


	public void setConsumerRefNo(String consumerRefNo) {
		this.consumerRefNo = consumerRefNo;
	}


	public String getAreaCode() {
		return areaCode;
	}


	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}


	public String getFundSource() {
		return fundSource;
	}


	public void setFundSource(String fundSource) {
		this.fundSource = fundSource;
	}


	public String getDistrict() {
		return district;
	}


	public void setDistrict(String district) {
		this.district = district;
	}


	public String getRepresentative() {
		return representative;
	}


	public void setRepresentative(String representative) {
		this.representative = representative;
	}


	public String getSchemeNo() {
		return schemeNo;
	}


	public void setSchemeNo(String schemeNo) {
		this.schemeNo = schemeNo;
	}


	public String getSchemeName() {
		return schemeName;
	}


	public void setSchemeName(String schemeName) {
		this.schemeName = schemeName;
	}


	public String getIsElectricityhave() {
		return isElectricityhave;
	}


	public void setIsElectricityhave(String isElectricityhave) {
		this.isElectricityhave = isElectricityhave;
	}


	public String getDesOfPremises() {
		return desOfPremises;
	}


	public void setDesOfPremises(String desOfPremises) {
		this.desOfPremises = desOfPremises;
	}


	public String getElectorate() {
		return electorate;
	}


	public void setElectorate(String electorate) {
		this.electorate = electorate;
	}


	public String getDevSec() {
		return devSec;
	}


	public void setDevSec(String devSec) {
		this.devSec = devSec;
	}


	public String getGsDivision() {
		return gsDivision;
	}


	public void setGsDivision(String gsDivision) {
		this.gsDivision = gsDivision;
	}


	public String getAgaDivision() {
		return agaDivision;
	}


	public void setAgaDivision(String agaDivision) {
		this.agaDivision = agaDivision;
	}


	public BigDecimal getNoOfMeterPoints() {
		return noOfMeterPoints;
	}


	public void setNoOfMeterPoints(BigDecimal noOfMeterPoints) {
		this.noOfMeterPoints = noOfMeterPoints;
	}


	public BigDecimal getCapacityofService() {
		return capacityofService;
	}


	public void setCapacityofService(BigDecimal capacityofService) {
		this.capacityofService = capacityofService;
	}


	public String getRepresentative2() {
		return representative2;
	}


	public void setRepresentative2(String representative2) {
		this.representative2 = representative2;
	}


	

	public String getRepContact1() {
		return repContact1;
	}


	public void setRepContact1(String repContact1) {
		this.repContact1 = repContact1;
	}


	public String getRepContact2() {
		return repContact2;
	}


	public void setRepContact2(String repContact2) {
		this.repContact2 = repContact2;
	}


	public String getServiceDepoName() {
		return ServiceDepoName;
	}


	public void setServiceDepoName(String serviceDepoName) {
		ServiceDepoName = serviceDepoName;
	}


	public String getAcountNos() {
		return acountNos;
	}


	public void setAcountNos(String acountNos) {
		this.acountNos = acountNos;
	}


	public WiringLandDetailCon() {
    }

	
	@Override
	public String toString() {
		return "WiringLandDetailCon [id=" + id + ", consumerRefNo=" + consumerRefNo
				+ ", areaCode=" + areaCode + ", fundSource="
				+ fundSource + ", representative=" + representative
				+ ", schemeNo=" + schemeNo + ", schemeName="
				+ schemeName + ", isElectricityhave=" + isElectricityhave
				+ ", desOfPremises=" + desOfPremises + ", electorate="
				+ electorate + ", devSec=" + devSec
				+ ", gsDivision=" + gsDivision + ", agaDivision=" + agaDivision
				+ ", noOfMeterPoints=" + noOfMeterPoints + ", capacityofService="
				+ capacityofService + "]";
	}
	
	

}