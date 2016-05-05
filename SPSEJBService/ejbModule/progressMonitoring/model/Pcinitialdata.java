package progressMonitoring.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


import javax.persistence.EmbeddedId;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;



@Entity
@Table(name="PCINITIALDATA")
public class Pcinitialdata implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@EmbeddedId
	private PcinitialdataPK id;
	
	
	
	@Column (name="PROJECT_NO")
	private String projectNo;
	
	@Column (name="FUND_ID")
	private String fundId;
	
	@Temporal(TemporalType.DATE)
	@Column (name="ES_ON_DATE")
	private Date esOnDate;
	
	@Column (name="EST_DGM_ON")
	private Date estDgmOn;
	
	@Column (name="DECRP")
	private String decrp;
	
	@Column (name="DEV_SEC")
	private String devSec;
	
	 @Temporal( TemporalType.DATE)
	@Column (name="LERECIVED_DATE")
	private Date lerecivedDate;
	
	@Column (name="STD_COST")
	private Float stdCost;
	
	@Column (name="EST_BY")
	private String estBy;
	
	@Column (name="AREA_CODE")
	private String areaCode;
	
	 @Temporal( TemporalType.DATE)
	@Column (name="JOB_ASS_DATE")
	private Date jobAssDate;
	
	 @Temporal( TemporalType.DATE)
	@Column (name="TARGET_DATE")
	private Date targetDate;
	
	 @Temporal( TemporalType.DATE)
	@Column (name="JOBNO_RECEDATE")
	private Date jobnoRecedate;
	 
	 @Temporal( TemporalType.DATE)
		@Column (name="JOBALLO_DATE")
		private Date jobAlloDate;
	
	 @Column (name="CODE_NUMBER")
		private String codeNumber;
	
	@Column (name="CON_BY")
	private String conBy;
	
	@Column (name="SUP_BY")
	private String supBy;
	
	@Column (name="EST_NO")
	private String estNo;
	
	@Column (name="HT_33")
	private Float ht33;
	
	@Column (name="LT_11")
	private Float lt11;
	
	@Column (name="SUB_33")
	private Float sub33;
	
	@Column (name="SUB_11")
	private Float sub11;
	
	@Column (name="SUB_CAPACITY")
	private Float subCapacity;
	
	@Column (name="LT3_PHASE")
	private Float lt3Phase;
	
	@Column (name="LTS_PHASE")
	private Float ltsPhase;
	
	@Column (name="ABC5W")
	private Float abc5w;
	
	@Column (name="ABC4W")
	private Float abc4w;
	
	@Column (name="ABCSECCT")
	private Float abcsecct;
	
	@Column (name="AUG_SUB ")
	private BigDecimal augSub;
	
	@Column (name="PRE_CAPACITY")
	private Float perCapacity;
	
	 @Temporal( TemporalType.DATE)
	@Column (name="PRO_LTR_TO_ES_ON")
	private Date proLtrToEsOn;
	 @Temporal( TemporalType.DATE)
	@Column (name="EST_RCD_ON")
	private Date estRcdOn;
	
	@Column (name="RE_AG_SF_SS")
	private Float reAgSfSs;
	
	@Column (name="RE_SF_CON_HT")
	private Float reSfConHt;
	
	@Column (name="RE_SF_LT ")
	private Float reSfLt;
	
	@Column (name="FI_RP_LBS_DDLO")
	private Float fiRpLbsDdlo;
	
	@Column (name="POLES")
	private Float poles;
	
	
	//Things I have added. In hear database table have Number datatype--doree
	@Column (name="DETAIL_COST")
	private Float detailCost;
	
	@Column (name="VARIANCE_N")
	private Float varianceNew;
	
	@Temporal( TemporalType.DATE)
	@Column (name="PRO_LTR_RCD_ON")
	private Date projectLetterReceivedOnDate;
	
	
	@Column (name="DISTRICT")
	private String district;
	
	@Column (name="SERVICEDEPONAME")
	private String serviceDepoName;
	
	
	@Column (name="PRO_FUND_AUT")
	private String pro_fund_aut;
	
	@Column (name="ELECTORATE")
	private String electorate;
	 @Temporal( TemporalType.DATE)
	@Column (name="ES_ALLOCATED_DATE")
	private Date esAllocatedDate;
	
	@Column (name="NEW_CAPACITY")
	private Float newCapacity;
	
	@Column (name="COMBINE_RUN")
	private Float combineRun;
	
	@Column (name="LT2_3")
	private Float lt23;
	
	@Column (name="LT1_3")
	private Float lt13;
	
	@Column (name="NO_POLE_SHIFTED")
	private Integer noPoleShifted;
	
	@Column (name="NO_POLE_TOBE_SHIFT")
	private Integer noPoleToBeShifted;
	
	@Column (name="FEEDERNAME")
	private String feedername;
	
	@Column (name="NO_HT_POLE")
	private Integer no_ht_pole;
	
	@Column (name="NO_LT_POLE")
	private Integer no_lt_pole;
	
	@Column (name="GANG_DAYS_REQUIRED")
	private Float ganDaysRequired;
	
	public Pcinitialdata()
	{
		
		
	}
/////////////////////////////////////////////////////////////////////////////////////////////////////////
	public  Pcinitialdata(PcinitialdataPK id,String fileNo,String projectNo,String fundId,Date esOnDate,
			Date estDgmOn,String decrp,String codenumber,String devSec,String feedername,Date lerecivedDate,Float stdCost,String estBy,String areaCode,
			Date jobAssDate,Date targetDate,Date jobnoRecedate,Date jobAllodate,String conBy,String supBy,String estNo,Float ht33,
			Float lt11,Float sub33,Float sub11,Float subCapacity,Float lt3Phase,Float ltsPhase,Float abc5w,Float abc4w,
			Float abcsecct,BigDecimal augSub,Float perCapacity,Date proLtrToEsOn,Date estRcdOn,Float reAgSfSs,Float reSfConHt,
			Float reSfLt,Float fiRpLbsDdlo,Float poles, Float varianceNew,String dictrict,String csc,String electorate,Date esAllocOnDate ,Float combineRun,Float lt23,Float lt13,Integer noPoleShifted,Integer no_ht_pole,Integer no_lt_pole,String pro_fund_aut,Integer noPoleToBeShifted,Float ganDaysRequired) {
		
		super();
		this.id=id;
	//	this.fileNo=fileNo;
		this.projectNo=projectNo;
		this.fundId=fundId;
		this.esOnDate =esOnDate;
		this.estDgmOn=estDgmOn;
		this.decrp=decrp;
		this.codeNumber=codenumber;
		this.devSec=devSec;
		this.feedername=feedername;
		this.district=dictrict;
		this.esAllocatedDate=esAllocOnDate;
		this.electorate=electorate;
		this.lerecivedDate=lerecivedDate;
		this.stdCost=stdCost;
		this.estBy=estBy;
		this.areaCode=areaCode;
		this.jobAssDate=jobAssDate;
		this.targetDate=targetDate;
		this.jobnoRecedate=jobnoRecedate;
		this.jobAlloDate=jobAllodate;
		this.conBy=conBy;
		this.supBy=supBy;
		this.estNo=estNo;
		this.ht33=ht33;
		this.lt11=lt11;
		this.sub11=sub11;
		this.sub33=sub33;
		this.subCapacity=subCapacity;
		this.lt3Phase=lt3Phase;
		this.ltsPhase=ltsPhase;
	    this.abc5w=abc5w;
	    this.abc4w=abc4w;
	    this.abcsecct=abcsecct;
	    this.augSub=augSub;
	    this.perCapacity=perCapacity;
	    this.proLtrToEsOn=proLtrToEsOn;
	    this.estRcdOn=estRcdOn;
	    this.reAgSfSs=reAgSfSs;
	    this.reSfConHt=reSfConHt;
	    this.reSfLt=reSfLt;
	    this.fiRpLbsDdlo=fiRpLbsDdlo;
	    this.poles=poles;
	    this.varianceNew=varianceNew;
	    this.combineRun = combineRun;
		this.lt23 =lt23;
		this.lt13 =lt13;
		this.noPoleShifted = noPoleShifted;
		this.no_ht_pole = no_ht_pole;
		this.no_lt_pole = no_lt_pole;
		this.pro_fund_aut = pro_fund_aut;
		this.noPoleToBeShifted = noPoleToBeShifted;
		this.ganDaysRequired =ganDaysRequired;
	}
	
	//////////////////////////////////////////////////////////////////////////////////
	
	public Integer getNoPoleToBeShifted() {
		return noPoleToBeShifted;
	}

	public void setNoPoleToBeShifted(Integer noPoleToBeShifted) {
		this.noPoleToBeShifted = noPoleToBeShifted;
	}
	
	public String getPro_Fund_Aut() {
		return pro_fund_aut;
	}

	public void setPro_Fund_Aut(String pro_fund_aut) {
		this.pro_fund_aut = pro_fund_aut;
	}
	
	
	public Integer getNo_lt_pole() {
		return no_lt_pole;
	}

	public void setNo_lt_pole(Integer no_lt_pole) {
		this.no_lt_pole = no_lt_pole;
	}
	
	
	public Integer getNo_ht_pole() {
		return no_ht_pole;
	}

	public void setNo_ht_pole(Integer no_ht_pole) {
		this.no_ht_pole = no_ht_pole;
	}
	
	public Integer getNoPoleShifted() {
		return noPoleShifted;
	}

	public void setNoPoleShifted(Integer noPoleShifted) {
		this.noPoleShifted = noPoleShifted;
	}
	
	
	public Float getLt23() {
		return lt23;
	}

	public void setLt23(Float lt23) {
		this.lt23 = lt23;
	}
	
	
	public Float getLt13() {
		return lt13;
	}

	public void setLt13(Float lt13) {
		this.lt13 = lt13;
	}
	
	public Float getCombineRun() {
		return combineRun;
	}

	public void setCombineRun(Float combineRun) {
		this.combineRun = combineRun;
	}
	
	public Float getNewCapacity() {
		return newCapacity;
	}

	public void setNewCapacity(Float newCapacity) {
		this.newCapacity = newCapacity;
	}

	public Float getDetailCost() {
		return detailCost;
	}

	public void setDetailCost(Float detailCost) {
		this.detailCost = detailCost;
	}

	public Float getVarianceNew() {
		return varianceNew;
	}

	public void setVarianceNew(Float varianceNew) {
		this.varianceNew = varianceNew;
	}

	
	public Date getJobAlloDate() {
		return jobAlloDate;
	}

	public void setJobAlloDate(Date jobAlloDate) {
		this.jobAlloDate = jobAlloDate;
	}
	public Date getProjectLetterReceivedOnDate() {
		return projectLetterReceivedOnDate;
	}

	public void setProjectLetterReceivedOnDate(Date projectLetterReceivedOnDate) {
		this.projectLetterReceivedOnDate = projectLetterReceivedOnDate;
	}

	public void setId(PcinitialdataPK id) {
		this.id = id;
	}

	public PcinitialdataPK getId() {
		return id;
	}

	public void setPoles(Float poles) {
		this.poles = poles;
	}

	public Float getPoles() {
		return poles;
	}

	public void setFiRpLbsDdlo(Float fiRpLbsDdlo) {
		this.fiRpLbsDdlo = fiRpLbsDdlo;
	}

	public Float getFiRpLbsDdlo() {
		return fiRpLbsDdlo;
	}

	public void setReSfLt(Float reSfLt) {
		this.reSfLt = reSfLt;
	}

	public Float getReSfLt() {
		return reSfLt;
	}

	public void setReSfConHt(Float reSfConHt) {
		this.reSfConHt = reSfConHt;
	}

	public Float getReSfConHt() {
		return reSfConHt;
	}

	public void setReAgSfSs(Float reAgSfSs) {
		this.reAgSfSs = reAgSfSs;
	}

	public Float getReAgSfSs() {
		return reAgSfSs;
	}

	public void setEstRcdOn(Date estRcdOn) {
		this.estRcdOn = estRcdOn;
	}

	public Date getEstRcdOn() {
		return estRcdOn;
	}

	public void setProLtrToEsOn(Date proLtrToEsOn) {
		this.proLtrToEsOn = proLtrToEsOn;
	}

	public Date getProLtrToEsOn() {
		return proLtrToEsOn;
	}

	public void setPerCapacity(Float perCapacity) {
		this.perCapacity = perCapacity;
	}

	public Float getPerCapacity() {
		return perCapacity;
	}

	public void setAugSub(BigDecimal augSub) {
		this.augSub = augSub;
	}

	public BigDecimal getAugSub() {
		return augSub;
	}

	public void setAbcsecct(Float abcsecct) {
		this.abcsecct = abcsecct;
	}

	public Float getAbcsecct() {
		return abcsecct;
	}

	public void setAbc4w(Float abc4w) {
		this.abc4w = abc4w;
	}

	public Float getAbc4w() {
		return abc4w;
	}

	public void setAbc5w(Float abc5w) {
		this.abc5w = abc5w;
	}

	public Float getAbc5w() {
		return abc5w;
	}

	public void setLtsPhase(Float ltsPhase) {
		this.ltsPhase = ltsPhase;
	}

	public Float getLtsPhase() {
		return ltsPhase;
	}

	public void setLt3Phase(Float lt3Phase) {
		this.lt3Phase = lt3Phase;
	}

	public Float getLt3Phase() {
		return lt3Phase;
	}

	public void setSubCapacity(Float subCapacity) {
		this.subCapacity = subCapacity;
	}

	public Float getSubCapacity() {
		return subCapacity;
	}

	public void setSub11(Float sub11) {
		this.sub11 = sub11;
	}

	public Float getSub11() {
		return sub11;
	}

	public void setSub33(Float sub33) {
		this.sub33 = sub33;
	}

	public Float getSub33() {
		return sub33;
	}

	public void setLt11(Float lt11) {
		this.lt11 = lt11;
	}

	public Float getLt11() {
		return lt11;
	}
	
	public void setGanDaysRequired(Float ganDaysRequired) {
		this.ganDaysRequired = ganDaysRequired;
	}

	public Float getGanDaysRequired() {
		return ganDaysRequired;
	}

	public void setHt33(Float ht33) {
		this.ht33 = ht33;
	}

	public Float getHt33() {
		return ht33;
	}

	public void setEstNo(String estNo) {
		this.estNo = estNo;
	}
	public String getCodeNumber() {
		return codeNumber;
	}

	public void setCodeNumber(String codeNumber) {
		this.codeNumber = codeNumber;
	}
	
	public String getEstNo() {
		return estNo;
	}

	public void setSupBy(String supBy) {
		this.supBy = supBy;
	}

	public String getSupBy() {
		return supBy;
	}

	public void setConBy(String conBy) {
		this.conBy = conBy;
	}

	public String getConBy() {
		return conBy;
	}

	public void setJobnoRecedate(Date jobnoRecedate) {
		this.jobnoRecedate = jobnoRecedate;
	}

	public Date getJobnoRecedate() {
		return jobnoRecedate;
	}

	public void setTargetDate(Date targetDate) {
		this.targetDate = targetDate;
	}

	public Date getTargetDate() {
		return targetDate;
	}

	public void setJobAssDate(Date jobAssDate) {
		this.jobAssDate = jobAssDate;
	}

	public Date getJobAssDate() {
		return jobAssDate;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setEstBy(String estBy) {
		this.estBy = estBy;
	}

	public String getEstBy() {
		return estBy;
	}

	

	public Float getStdCost() {
		return stdCost;
	}

	public void setStdCost(Float stdCost) {
		this.stdCost = stdCost;
	}

	public void setLerecivedDate(Date lerecivedDate) {
		this.lerecivedDate = lerecivedDate;
	}

	public Date getLerecivedDate() {
		return lerecivedDate;
	}

	public void setDevSec(String devSec) {
		this.devSec = devSec;
	}

	public String getDevSec() {
		return devSec;
	}
	
	public void setFeedername(String feedername) {
		this.feedername = feedername;
	}

	public String getFeedername() {
		return feedername;
	}

	public void setDecrp(String decrp) {
		this.decrp = decrp;
	}

	public String getDecrp() {
		return decrp;
	}

	public void setEstDgmOn(Date estDgmOn) {
		this.estDgmOn = estDgmOn;
	}

	public Date getEstDgmOn() {
		return estDgmOn;
	}

	public void setEsOnDate(Date esOnDate) {
		this.esOnDate = esOnDate;
	}

	public Date getEsOnDate() {
		return esOnDate;
	}

	public void setFundId(String fundId) {
		this.fundId = fundId;
	}

	public String getFundId() {
		return fundId;
	}

	
	public void setProjectNo(String projectNo) {
		this.projectNo = projectNo;
	}

	public String getProjectNo() {
		return projectNo;
	}

	
	
	public Date getEsAllocatedDate() {
		return esAllocatedDate;
	}
	public void setEsAllocatedDate(Date esAllocatedDate) {
		this.esAllocatedDate = esAllocatedDate;
	}

	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getServiceDepoName() {
		return serviceDepoName;
	}
	public void setServiceDepoName(String serviceDepoName) {
		this.serviceDepoName = serviceDepoName;
	}
	public String getElectorate() {
		return electorate;
	}
	public void setElectorate(String electorate) {
		this.electorate = electorate;
	}
@Override
public String toString(){
	return "Pcinitialdata [ id="+id+" ,  projectNo="+projectNo+" , fundId="+fundId+" ,esOnDate="+esOnDate
	+ ", estDgmOn = "+estDgmOn+", decrp = "+decrp+", codeNumber = "+codeNumber+", devSec="+devSec+", feedername="+feedername+", lerecivesDate="+lerecivedDate+"" +
			",stdCost = "+stdCost+", estBy="+estBy+", areaCode="+areaCode+", jobAssDate="+jobAssDate+", jobAlloDate="+jobAlloDate+",targetDate="+targetDate+"" +
		", jobnoRecedate="+jobnoRecedate+", conBy= "+conBy+", supBy="+supBy+",estNo="+estNo+"" +
		", ht33="+ht33+", lt11="+lt11+", sub33="+sub33+", sub11="+sub11+",subCapacity="+subCapacity+"" +
	", lt3Phase="+lt3Phase+", lesPhase="+ltsPhase+", abc5w="+abc5w+",abc4w="+abc4w+", abcsecct="+abcsecct+"" +
			", augSub="+augSub+", perCapacity="+perCapacity+",proLtrToEsOn="+proLtrToEsOn+",estRcdOn="+estRcdOn+"" +
					",reAgSfSs="+reAgSfSs+",reSfConHt="+reSfConHt+",reSfLt="+reSfLt+",fiRpLbsDdlo="+fiRpLbsDdlo+" " +
			",poles="+poles+",combineRun="+combineRun+",lt23="+lt23+",lt13="+lt13+",noPoleShifted="+noPoleShifted+",no_ht_pole="+no_ht_pole+",no_lt_pole="+no_lt_pole+",pro_fund_aut="+pro_fund_aut+",noPoleToBeShifted="+noPoleToBeShifted+",ganDaysRequired="+ganDaysRequired+"]" ;
	
	
	
	}
}
