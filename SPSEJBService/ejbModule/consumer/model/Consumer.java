package consumer.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the CONSUMER database table.
 * 
 */
@Entity
public class Consumer implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ConsumerPK id;

	private String accountno;

	private String addresscorr;

	private String addresspremises;

	private String agadivision;

	@Column(name="AMT_ALLOCATED")
	private double amtAllocated;

	private BigDecimal apartment;

	private String apptype;

	private String capacityservi;

	private String cebarea;

	private String city;

	private String confirmby;

	private String confirmdate;

	private String consumerref;

	private String contactname;

	private String contactnumber;

	private String csc;

	private String customercode;

	private String demand;

	@Column(name="DEV_SEC")
	private String devSec;

	private String electorate;

	private String email;

	@Column(name="ENTER_DATE")
	private String enterDate;

	private String fullname;

	@Column(name="FUND_ID")
	private String fundId;

	@Column(name="FUND_SOURCE")
	private String fundSource;

	private String gsdivision;

	private BigDecimal hotel;

	@Column(name="ID_ISSUE_DATE")
	private String idIssueDate;

	private String idnumber;

	private BigDecimal industry;

	private String industrytype;

	private String jobdes;

	private BigDecimal landsale;

	private String location;

	private String natureofsupply;

	private String nometerpoint;

	private String noofphase;

	private BigDecimal officecomplex;

	private BigDecimal other;

	private String owner;

	private String proposer;

	private String representative1;

	private String representative2;

	@Column(name="SCH_NAME")
	private String schName;

	@Column(name="SCH_NO")
	private String schNo;

	private BigDecimal shop;

	private BigDecimal shopcomplex;

	private String sinnumber;

	private BigDecimal status;

	private String supplyno;

	private String supplyyes;

	private String tenant;

	private String tyidentifi;

    public Consumer() {
    }

	public ConsumerPK getId() {
		return this.id;
	}

	public void setId(ConsumerPK id) {
		this.id = id;
	}
	
	public String getAccountno() {
		return this.accountno;
	}

	public void setAccountno(String accountno) {
		this.accountno = accountno;
	}

	public String getAddresscorr() {
		return this.addresscorr;
	}

	public void setAddresscorr(String addresscorr) {
		this.addresscorr = addresscorr;
	}

	public String getAddresspremises() {
		return this.addresspremises;
	}

	public void setAddresspremises(String addresspremises) {
		this.addresspremises = addresspremises;
	}

	public String getAgadivision() {
		return this.agadivision;
	}

	public void setAgadivision(String agadivision) {
		this.agadivision = agadivision;
	}

	public double getAmtAllocated() {
		return this.amtAllocated;
	}

	public void setAmtAllocated(double amtAllocated) {
		this.amtAllocated = amtAllocated;
	}

	public BigDecimal getApartment() {
		return this.apartment;
	}

	public void setApartment(BigDecimal apartment) {
		this.apartment = apartment;
	}

	public String getApptype() {
		return this.apptype;
	}

	public void setApptype(String apptype) {
		this.apptype = apptype;
	}

	public String getCapacityservi() {
		return this.capacityservi;
	}

	public void setCapacityservi(String capacityservi) {
		this.capacityservi = capacityservi;
	}

	public String getCebarea() {
		return this.cebarea;
	}

	public void setCebarea(String cebarea) {
		this.cebarea = cebarea;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getConfirmby() {
		return this.confirmby;
	}

	public void setConfirmby(String confirmby) {
		this.confirmby = confirmby;
	}

	public String getConfirmdate() {
		return this.confirmdate;
	}

	public void setConfirmdate(String confirmdate) {
		this.confirmdate = confirmdate;
	}

	public String getConsumerref() {
		return this.consumerref;
	}

	public void setConsumerref(String consumerref) {
		this.consumerref = consumerref;
	}

	public String getContactname() {
		return this.contactname;
	}

	public void setContactname(String contactname) {
		this.contactname = contactname;
	}

	public String getContactnumber() {
		return this.contactnumber;
	}

	public void setContactnumber(String contactnumber) {
		this.contactnumber = contactnumber;
	}

	public String getCsc() {
		return this.csc;
	}

	public void setCsc(String csc) {
		this.csc = csc;
	}

	public String getCustomercode() {
		return this.customercode;
	}

	public void setCustomercode(String customercode) {
		this.customercode = customercode;
	}

	public String getDemand() {
		return this.demand;
	}

	public void setDemand(String demand) {
		this.demand = demand;
	}

	public String getDevSec() {
		return this.devSec;
	}

	public void setDevSec(String devSec) {
		this.devSec = devSec;
	}

	public String getElectorate() {
		return this.electorate;
	}

	public void setElectorate(String electorate) {
		this.electorate = electorate;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEnterDate() {
		return this.enterDate;
	}

	public void setEnterDate(String enterDate) {
		this.enterDate = enterDate;
	}

	public String getFullname() {
		return this.fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getFundId() {
		return this.fundId;
	}

	public void setFundId(String fundId) {
		this.fundId = fundId;
	}

	public String getFundSource() {
		return this.fundSource;
	}

	public void setFundSource(String fundSource) {
		this.fundSource = fundSource;
	}

	public String getGsdivision() {
		return this.gsdivision;
	}

	public void setGsdivision(String gsdivision) {
		this.gsdivision = gsdivision;
	}

	public BigDecimal getHotel() {
		return this.hotel;
	}

	public void setHotel(BigDecimal hotel) {
		this.hotel = hotel;
	}

	public String getIdIssueDate() {
		return this.idIssueDate;
	}

	public void setIdIssueDate(String idIssueDate) {
		this.idIssueDate = idIssueDate;
	}

	public String getIdnumber() {
		return this.idnumber;
	}

	public void setIdnumber(String idnumber) {
		this.idnumber = idnumber;
	}

	public BigDecimal getIndustry() {
		return this.industry;
	}

	public void setIndustry(BigDecimal industry) {
		this.industry = industry;
	}

	public String getIndustrytype() {
		return this.industrytype;
	}

	public void setIndustrytype(String industrytype) {
		this.industrytype = industrytype;
	}

	public String getJobdes() {
		return this.jobdes;
	}

	public void setJobdes(String jobdes) {
		this.jobdes = jobdes;
	}

	public BigDecimal getLandsale() {
		return this.landsale;
	}

	public void setLandsale(BigDecimal landsale) {
		this.landsale = landsale;
	}

	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getNatureofsupply() {
		return this.natureofsupply;
	}

	public void setNatureofsupply(String natureofsupply) {
		this.natureofsupply = natureofsupply;
	}

	public String getNometerpoint() {
		return this.nometerpoint;
	}

	public void setNometerpoint(String nometerpoint) {
		this.nometerpoint = nometerpoint;
	}

	public String getNoofphase() {
		return this.noofphase;
	}

	public void setNoofphase(String noofphase) {
		this.noofphase = noofphase;
	}

	public BigDecimal getOfficecomplex() {
		return this.officecomplex;
	}

	public void setOfficecomplex(BigDecimal officecomplex) {
		this.officecomplex = officecomplex;
	}

	public BigDecimal getOther() {
		return this.other;
	}

	public void setOther(BigDecimal other) {
		this.other = other;
	}

	public String getOwner() {
		return this.owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getProposer() {
		return this.proposer;
	}

	public void setProposer(String proposer) {
		this.proposer = proposer;
	}

	public String getRepresentative1() {
		return this.representative1;
	}

	public void setRepresentative1(String representative1) {
		this.representative1 = representative1;
	}

	public String getRepresentative2() {
		return this.representative2;
	}

	public void setRepresentative2(String representative2) {
		this.representative2 = representative2;
	}

	public String getSchName() {
		return this.schName;
	}

	public void setSchName(String schName) {
		this.schName = schName;
	}

	public String getSchNo() {
		return this.schNo;
	}

	public void setSchNo(String schNo) {
		this.schNo = schNo;
	}

	public BigDecimal getShop() {
		return this.shop;
	}

	public void setShop(BigDecimal shop) {
		this.shop = shop;
	}

	public BigDecimal getShopcomplex() {
		return this.shopcomplex;
	}

	public void setShopcomplex(BigDecimal shopcomplex) {
		this.shopcomplex = shopcomplex;
	}

	public String getSinnumber() {
		return this.sinnumber;
	}

	public void setSinnumber(String sinnumber) {
		this.sinnumber = sinnumber;
	}

	public BigDecimal getStatus() {
		return this.status;
	}

	public void setStatus(BigDecimal status) {
		this.status = status;
	}

	public String getSupplyno() {
		return this.supplyno;
	}

	public void setSupplyno(String supplyno) {
		this.supplyno = supplyno;
	}

	public String getSupplyyes() {
		return this.supplyyes;
	}

	public void setSupplyyes(String supplyyes) {
		this.supplyyes = supplyyes;
	}

	public String getTenant() {
		return this.tenant;
	}

	public void setTenant(String tenant) {
		this.tenant = tenant;
	}

	public String getTyidentifi() {
		return this.tyidentifi;
	}

	public void setTyidentifi(String tyidentifi) {
		this.tyidentifi = tyidentifi;
	}

	@Override
	public String toString() {
		return "Consumer [id=" + id + ", accountno=" + accountno
				+ ", addresscorr=" + addresscorr + ", addresspremises="
				+ addresspremises + ", agadivision=" + agadivision
				+ ", amtAllocated=" + amtAllocated + ", apartment=" + apartment
				+ ", apptype=" + apptype + ", capacityservi=" + capacityservi
				+ ", cebarea=" + cebarea + ", city=" + city + ", confirmby="
				+ confirmby + ", confirmdate=" + confirmdate + ", consumerref="
				+ consumerref + ", contactname=" + contactname
				+ ", contactnumber=" + contactnumber + ", csc=" + csc
				+ ", customercode=" + customercode + ", demand=" + demand
				+ ", devSec=" + devSec + ", electorate=" + electorate
				+ ", email=" + email + ", enterDate=" + enterDate
				+ ", fullname=" + fullname + ", fundId=" + fundId
				+ ", fundSource=" + fundSource + ", gsdivision=" + gsdivision
				+ ", hotel=" + hotel + ", idIssueDate=" + idIssueDate
				+ ", idnumber=" + idnumber + ", industry=" + industry
				+ ", industrytype=" + industrytype + ", jobdes=" + jobdes
				+ ", landsale=" + landsale + ", location=" + location
				+ ", natureofsupply=" + natureofsupply + ", nometerpoint="
				+ nometerpoint + ", noofphase=" + noofphase
				+ ", officecomplex=" + officecomplex + ", other=" + other
				+ ", owner=" + owner + ", proposer=" + proposer
				+ ", representative1=" + representative1 + ", representative2="
				+ representative2 + ", schName=" + schName + ", schNo=" + schNo
				+ ", shop=" + shop + ", shopcomplex=" + shopcomplex
				+ ", sinnumber=" + sinnumber + ", status=" + status
				+ ", supplyno=" + supplyno + ", supplyyes=" + supplyyes
				+ ", tenant=" + tenant + ", tyidentifi=" + tyidentifi + "]";
	}

}