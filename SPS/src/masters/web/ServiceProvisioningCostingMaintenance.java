package masters.web;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import job.model.Pcesthmt;
import job.service.PcesthmtEjb;

import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.SessionAware;

import util.common.Format;
import util.common.Phase;
import util.common.Tariff;



import com.opensymphony.xwork2.ActionSupport;

import estimate.model.Spcstngm;
import estimate.model.SpcstngmPK;
import estimate.model.TariffCategory;
import estimate.service.SpcstngmEjb;
import estimate.service.TariffCategoryEjb;

@SuppressWarnings("serial")
public class ServiceProvisioningCostingMaintenance extends ActionSupport implements SessionAware, ParameterAware {


	private Map<String, Object> session;
	private Map <String, String[]> parameters;
	private String loggedInUserId;
	private static final String viewPath="Masters>Service Provisioning Costing Master Detials";
	private String path;
	private String lblError = null;
	private String lblSuccess = null;
	private List<Phase> phaseList;


	private String selectedPhase;

	private List<String> conectionTypesList;
	private String selectedConectionType;

	private List<Tariff> tarrifCategoryList;
	private String selectedTarrifCategory;
	private BigDecimal fixedCost;
	private BigDecimal securityDeposite;
	private BigDecimal temporaryDeposite;
	private BigDecimal wireMeterPrice;
	private String updateBy;
	private String updateDate;
	private String updateTime;
	private String costCenter;
	private List<String> fromLengthList;
	private List<String> toLengthList;
	private String selectedFromLength;
	private String selectedToLength;
	
	
	
	public String getSelectedFromLength() {
		return selectedFromLength;
	}
	public void setSelectedFromLength(String selectedFromLength) {
		this.selectedFromLength = selectedFromLength;
	}
	public String getSelectedToLength() {
		return selectedToLength;
	}
	public void setSelectedToLength(String selectedToLength) {
		this.selectedToLength = selectedToLength;
	}
	public List<String> getFromLengthList() {
		return fromLengthList;
	}
	public void setFromLengthList(List<String> fromLengthList) {
		this.fromLengthList = fromLengthList;
	}
	public List<String> getToLengthList() {
		return toLengthList;
	}
	public void setToLengthList(List<String> toLengthList) {
		this.toLengthList = toLengthList;
	}
	public String getCostCenter() {
		return costCenter;
	}
	public void setCostCenter(String costCenter) {
		this.costCenter = costCenter;
	}
	public String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public BigDecimal getFixedCost() {
		return fixedCost;
	}
	public void setFixedCost(BigDecimal fixedCost) {
		this.fixedCost = fixedCost;
	}
	public BigDecimal getSecurityDeposite() {
		return securityDeposite;
	}
	public void setSecurityDeposite(BigDecimal securityDeposite) {
		this.securityDeposite = securityDeposite;
	}
	public BigDecimal getTemporaryDeposite() {
		return temporaryDeposite;
	}
	public void setTemporaryDeposite(BigDecimal temporaryDeposite) {
		this.temporaryDeposite = temporaryDeposite;
	}
	public BigDecimal getWireMeterPrice() {
		return wireMeterPrice;
	}
	public void setWireMeterPrice(BigDecimal wireMeterPrice) {
		this.wireMeterPrice = wireMeterPrice;
	}
	public List<Phase> getPhaseList() {
		return phaseList;
	}
	public void setPhaseList(List<Phase> phaseList) {
		this.phaseList = phaseList;
	}	 
	public String getSelectedPhase() {
		return selectedPhase;
	}
	public void setSelectedPhase(String selectedPhase) {
		this.selectedPhase = selectedPhase;
	}
	public List<String> getConectionTypesList() {
		return conectionTypesList;
	}
	public void setConectionTypesList(List<String> conectionTypesList) {
		this.conectionTypesList = conectionTypesList;
	}
	public String getSelectedConectionType() {
		return selectedConectionType;
	}
	public void setSelectedConectionType(String selectedConectionType) {
		this.selectedConectionType = selectedConectionType;
	}


	public void setSelectedTarrifCategory(String selectedTarrifCategory) {
		this.selectedTarrifCategory = selectedTarrifCategory;
	}
	public String getLblError() {
		return lblError;
	}
	public void setLblError(String lblError) {
		this.lblError = lblError;
	}
	public String getLblSuccess() {
		return lblSuccess;
	}
	public void setLblSuccess(String lblSuccess) {
		this.lblSuccess = lblSuccess;
	}
	public String getLoggedInUserId() {
		return loggedInUserId;
	}
	public void setLoggedInUserId(String loggedInUserId) {
		this.loggedInUserId = loggedInUserId;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public Map<String, Object> getSession() {
		return session;
	}
	public Map<String, String[]> getParameters() {
		return parameters;
	}
	public List<Tariff> getTarrifCategoryList() {
		return tarrifCategoryList;
	}
	public void setTarrifCategoryList(List<Tariff> tarrifCategoryList) {
		this.tarrifCategoryList = tarrifCategoryList;
	}
	public String getSelectedTarrifCategory() {
		return selectedTarrifCategory;
	}
	@Override
	public void setParameters(Map<String, String[]> parameters) {
		// TODO Auto-generated method stub
		this.parameters=parameters;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		// TODO Auto-generated method stub
		this.session=session;
	}
	public String getSessionKey(String key) {
		return getSession().get(key).toString();
	}



	public String execute(){

		setLblError(null);
		
		setLoggedInUserId(getSession().get("userName").toString());
		setPath(viewPath);
		setCostCenter(getSessionKey("costCenterNo"));
		
		setPhaseList();
		setConnectionTypesList();
		setTarrifCategoryList();
		setFromLengthList();
		 setToLengthList();
		
		String phase,type,tariff,frmLngth,toLength;
		phase = getSelectedPhase();
		type = getSelectedConectionType();
		tariff = getSelectedTarrifCategory();
		frmLngth = getSelectedFromLength();
		toLength = getSelectedToLength();
		
		if (frmLngth == null)
			frmLngth = "0";
		if (toLength == null)
			toLength = "0";
		
		if (phase == null || type == null || tariff == null || frmLngth == null || toLength == null)
		{
		  getCostDetails("1","15","CP",getCostCenter(),"0","52");
		}else
		{
			 getCostDetails(phase,type,tariff,getCostCenter(),frmLngth,toLength);
		}
		
		System.out.println("getSelectedPhase :" + phase) ;
		System.out.println("getSelectedConectionType :" + type) ;
		System.out.println("getSelectedTarrifCategory :" + tariff) ;
		System.out.println("getLoggedInUserId :" + getLoggedInUserId()) ;
		
		return SUCCESS;
	}

	private void setPhaseList()
	{
		this.phaseList = new   ArrayList<Phase>();
		Phase single = new Phase("Single Phase", "1");
		Phase two = new Phase("2 Phase" , "2");
		Phase three = new Phase("3 Phase","3");
		this.phaseList.add(single);
		//this.phaseList.add(two);
		this.phaseList.add(three);

	}

	private void setConnectionTypesList()
	{
		this.conectionTypesList = new ArrayList<String>();
		this.conectionTypesList.add("15");
		this.conectionTypesList.add("30");
		this.conectionTypesList.add("60");
	}
	
	private void setFromLengthList()
	{
		this.fromLengthList = new ArrayList<String>();
		this.fromLengthList.add("0");
		this.fromLengthList.add("53");
	}
	
	private void setToLengthList()
	{
		this.toLengthList = new ArrayList<String>();
		this.toLengthList.add("52");
		this.toLengthList.add("110");
	}

	private void setTarrifCategoryList()
	{
		this.tarrifCategoryList = new ArrayList<Tariff>();

		TariffCategoryEjb ejb = new TariffCategoryEjb(getSessionKey("region"));
		List<TariffCategory> lst =  ejb.getAll();
		for (int i=0; i<=lst.size()-1;i++) {

			Tariff tarf = new Tariff(lst.get(i).getTariffCatName(), lst.get(i).getTariffCatCode()) ;
			this.tarrifCategoryList.add(tarf);
		} 


	}

	private void getCostDetails(String phase,String type,String tarif,String costCenterId,String fromLength,String toLength)
	{
		System.out.println("OXOXOXOXOX: " + phase + type + tarif);
		
		SpcstngmPK id = new SpcstngmPK();
		id.setConnectionType(Long.parseLong(type));
		id.setPhase(Long.parseLong(phase));
		id.setTariffCatCode(tarif);
		id.setDeptId(costCenterId);
		id.setFromLength(Long.parseLong(fromLength));
		id.setToLength(Long.parseLong(toLength));
		SpcstngmEjb ejb = new SpcstngmEjb(getSessionKey("region"));
	 
		Spcstngm spcstngm = null;
		try
		{
			  
			spcstngm = ejb.findById(id);
			  
		}catch(Exception e1)
		{
			System.out.println("OXO error: " + e1.toString());
			
			 setLblError("no cost detials");
			 return;
		}
		if (spcstngm == null)
		{
			BigDecimal bigD = new BigDecimal("0");
			 setFixedCost(bigD);
			 setSecurityDeposite(bigD);
			 setTemporaryDeposite(bigD);
			 setWireMeterPrice(bigD);
		}else
		{
		 setFixedCost(spcstngm.getFixedCost());
		 setSecurityDeposite(spcstngm.getSecurityDeposit());
		 setTemporaryDeposite(spcstngm.getTemporaryDeposit());
		 setWireMeterPrice(spcstngm.getWireMeterPrice());
		 setUpdateBy(spcstngm.getUpdUser());
		 Format ft = new Format();
		 setUpdateDate(ft.formatDate( spcstngm.getUpdDate()));
		 setUpdateTime(spcstngm.getUpdTime());
		}
	}
	
	public String Close(){
		return "close";
	}
	
	public String UpdateMaster(){
		System.out.println("Update Master") ; 
		setCostCenter(getSessionKey("costCenterNo"));
		BigDecimal fixedCst,securityDepst,temporaryDepst,wireMeterPrice;
		fixedCst = getFixedCost();
		securityDepst = getSecurityDeposite();
		temporaryDepst = getTemporaryDeposite();
		wireMeterPrice = getWireMeterPrice(); 
		
		if (fixedCst == null)
			fixedCst = new BigDecimal(0);
		if (securityDepst == null)
			securityDepst = new BigDecimal(0);
		if (temporaryDepst == null)
			temporaryDepst = new BigDecimal(0);
		if (wireMeterPrice == null)
			wireMeterPrice = new BigDecimal(0);
		
		SpcstngmPK id = getSpcstngmPKid();
		SpcstngmEjb ejb = new SpcstngmEjb(getSessionKey("region"));
	 
		Spcstngm spcstngm = null;
		try
		{			  
			spcstngm = ejb.findById(id);
			  
		}catch(Exception e1)
		{
			return SUCCESS;
		}
		
		Date dt = new Date();
		setLoggedInUserId(getSession().get("userName").toString());
		if (spcstngm == null)
		{
			spcstngm = new Spcstngm();
			spcstngm.setId(id);
			spcstngm.setFixedCost(fixedCst);
			spcstngm.setSecurityDeposit(securityDepst);
			spcstngm.setTemporaryDeposit(temporaryDepst);
			spcstngm.setWireMeterPrice(wireMeterPrice);
			Format ft =  new Format();
			spcstngm.setUpdTime(ft.FormatTime());
			spcstngm.setUpdDate(dt);
			spcstngm.setUpdUser(getLoggedInUserId());
			 
			ejb.createSpcstngm(spcstngm);
		}else
		{
			spcstngm.setFixedCost(fixedCst);
			spcstngm.setSecurityDeposit(securityDepst);
			spcstngm.setTemporaryDeposit(temporaryDepst);
			spcstngm.setWireMeterPrice(wireMeterPrice);
			Format ft =  new Format();
			spcstngm.setUpdTime(ft.FormatTime());
			spcstngm.setUpdDate(dt);
			spcstngm.setUpdUser(getLoggedInUserId());
			ejb.updateSpcstngm(spcstngm);
		}
		setLblSuccess("Updated successfully");
		execute();
		return SUCCESS;
	}

	public String Remove()
	{
		System.out.println("Remove Master") ;
		setCostCenter(getSessionKey("costCenterNo"));
 
		
		SpcstngmPK id = getSpcstngmPKid();
		SpcstngmEjb ejb = new SpcstngmEjb(getSessionKey("region"));
	 
		Spcstngm spcstngm = null;
		try
		{		
			 
			spcstngm = ejb.findById(id);
			ejb.removeSpcstngm(spcstngm);
		}catch(Exception e1)
		{
			execute();
			setLblError("Remove Failed." + e1.getMessage() );
			 
			return SUCCESS;
		}	 
		 
		setLblSuccess("Removed successfully");
		execute();
		return SUCCESS;
	}
	
	private  SpcstngmPK getSpcstngmPKid()
	{
		String phase,type,tariff,frmLngth,toLength;;
		phase = getSelectedPhase();
		type = getSelectedConectionType();
		tariff = getSelectedTarrifCategory();
		frmLngth = getSelectedFromLength();
		toLength = getSelectedToLength();
		
		SpcstngmPK id = new SpcstngmPK();
		id.setConnectionType(Long.parseLong(type));
		id.setPhase(Long.parseLong(phase));
		id.setTariffCatCode(tariff);
		id.setDeptId(getCostCenter());
		id.setFromLength(Long.parseLong(frmLngth));
		id.setToLength(Long.parseLong(toLength));
		return id;
	}
	
}
