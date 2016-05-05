package masters.web;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.SessionAware;

import util.common.ApplicationType;
import util.common.Phase;

import com.opensymphony.xwork2.ActionSupport;

import estimate.model.Splaborm;
import estimate.model.Splbsrvc;
import estimate.model.SplbsrvcPK;
import estimate.service.SplbsrvcEjb;

 

 

public class SPLBSRVCUpdate  extends ActionSupport implements SessionAware, ParameterAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map<String, Object> session;
	private Map <String, String[]> parameters;
	private String loggedInUserId;
	private static final String viewPath="Masters>Update Labour Activity Rates";
	private String path;
	private String lblError = null;
	private String lblSuccess = null;
	private List<Splbsrvc> labourCodeList; // 
private String selectedLabourCode;

	private List<Phase> applicationTypeList; 
	private String selectedApplicationType;
	private  List<Splaborm> activityCode; 
	private String selectedActivityCode;
	private String costCenter;
	 

	private  List<Phase> phase; 
	private String selectedPhase;
	private  List<Phase> spanType; 
	private String selectedSpanType;
	 
	private BigDecimal unitPrice;
	private BigDecimal labourHours;
	
	
	public List<Phase> getPhase() {
		return phase;
	}

	public void setPhase(List<Phase> phase) {
		this.phase = phase;
	}

	public String getSelectedPhase() {
		return selectedPhase;
	}

	public void setSelectedPhase(String selectedPhase) {
		this.selectedPhase = selectedPhase;
	}

	public List<Phase> getSpanType() {
		return spanType;
	}

	public void setSpanType(List<Phase> spanType) {
		this.spanType = spanType;
	}

	public String getSelectedSpanType() {
		return selectedSpanType;
	}

	public void setSelectedSpanType(String selectedSpanType) {
		this.selectedSpanType = selectedSpanType;
	}

	public void setLabourCodeList(List<Splbsrvc> labourCodeList) {
		this.labourCodeList = labourCodeList;
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
		setLblSuccess(null);
		setLoggedInUserId(getSession().get("userName").toString());
		setPath(viewPath);
		setCostCenter(getSessionKey("costCenterNo"));

		setLabourCodeList();
		getLabourActivityRates();
		setApplicationTypesList();
		setSpanTypeList();
		setPhaseList();

		return SUCCESS;
	}



	private void setApplicationTypesList()
	{
		this.applicationTypeList = new   ArrayList<Phase>();
		Phase nc = new Phase(ApplicationType.NEW_LINE_DESC, ApplicationType.NEW_CONN);
		Phase tc = new Phase(ApplicationType.TEMP_CONN_DESC , ApplicationType.TEMP_CONN);
		Phase cr = new Phase(ApplicationType.COST_RECOVERY_DESC,ApplicationType.COST_RECOVERY);
		this.applicationTypeList.add(nc);
		this.applicationTypeList.add(tc);
		this.applicationTypeList.add(cr);

	}

	private void setSpanTypeList()
	{
		this.spanType = new   ArrayList<Phase>();
		Phase single = new Phase("Last", "LAST");		 
		Phase three = new Phase("Additional","OTHER");
		this.spanType.add(single);		 
		this.spanType.add(three);
	}
	 
	private void setPhaseList()
	{
		this.phase = new   ArrayList<Phase>();
		Phase single = new Phase("Single Phase", "1");		 
		Phase three = new Phase("3 Phase","3");
		this.phase.add(single);		 
		this.phase.add(three);

	}
	private void setLabourCodeList()
	{


		this.labourCodeList = new   ArrayList<Splbsrvc>();			 
		SplbsrvcEjb ejb = new SplbsrvcEjb(getSessionKey("region"));
		this.labourCodeList = ejb.getAll(getCostCenter());
		if (getSelectedLabourCode() == null)
		{
			if (this.labourCodeList != null || this.labourCodeList.size() > 0)
				this.selectedLabourCode = this.labourCodeList.get(0).getId().getLabourCode();
		}
	}

	private void getLabourActivityRates()
	{
		String labourCde = getSelectedLabourCode();

		SplbsrvcPK id = new SplbsrvcPK();
		id.setDeptId(getCostCenter());
		id.setLabourCode(labourCde);
		SplbsrvcEjb ejb = new SplbsrvcEjb(getSessionKey("region"));
		Splbsrvc splbsrvc = null;

		try
		{

			splbsrvc = ejb.findById(id);

		}catch(Exception e1)
		{		
			setLblError("Labour Activity Rates not found !");
			return;
		}
		if (splbsrvc == null)
		{


		}else
		{

			setSelectedActivityCode(splbsrvc.getActivityCode());
			setSelectedApplicationType(splbsrvc.getApplicationType());
			setSelectedPhase(splbsrvc.getPhase().toString());
			 setSelectedSpanType(splbsrvc.getSpanType());
			setUnitPrice(splbsrvc.getUnitPrice());
			setLabourHours(splbsrvc.getLabourHours());
			this.selectedApplicationType = splbsrvc.getApplicationType();
		}



	}


	public String AddLabourActivityRate(){
		setCostCenter(getSessionKey("costCenterNo"));
		BigDecimal uPrice,lbrHours;
		uPrice = getUnitPrice();
		lbrHours = getLabourHours();

		if (uPrice == null)
			uPrice = new BigDecimal(0);
		if (lbrHours == null)
			lbrHours = new BigDecimal(0);

		SplbsrvcPK id = new SplbsrvcPK();
		id.setDeptId(getCostCenter());
			id.setLabourCode(getSelectedLabourCode());
			Splbsrvc splbsrvc = null;
			SplbsrvcEjb ejb = new SplbsrvcEjb(getSessionKey("region"));

		try
		{			  
			splbsrvc = ejb.findById(id);

		}catch(Exception e1)
		{
			return SUCCESS;
		}
		if (splbsrvc == null)
		{

		}else
		{
			splbsrvc.setActivityCode(getSelectedActivityCode());
			splbsrvc.setApplicationType(getSelectedApplicationType());
			splbsrvc.setPhase(new BigDecimal(getSelectedPhase()));
			splbsrvc.setSpanType(getSelectedSpanType());
			splbsrvc.setUnitPrice(getUnitPrice());
			splbsrvc.setLabourHours(getLabourHours());
			ejb.updateSplbsrvc(splbsrvc);
			
		}

		clearValues (true);
		execute();
		setLblSuccess("Labour activity rates successfully updated.");
		return SUCCESS;
	}


	public void clearValues (boolean clearlbCode)
	{




		costCenter = "";
		 
		unitPrice = null;
		labourHours = null;
	}
	public String Close(){
		return "close";
	}


	public List<Phase> getApplicationTypeList() {
		return applicationTypeList;
	}

	public void setApplicationTypeList(List<Phase> applicationTypeList) {
		this.applicationTypeList = applicationTypeList;
	}

	public String getSelectedApplicationType() {
		return selectedApplicationType;
	}

	public void setSelectedApplicationType(String selectedApplicationType) {
		this.selectedApplicationType = selectedApplicationType;
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



 


	public List<Splbsrvc> getLabourCodeList() {
		return labourCodeList;
	}

	public String getCostCenter() {
		return costCenter;
	}

	public void setCostCenter(String costCenter) {
		this.costCenter = costCenter;
	}

	 

	public String getSelectedLabourCode() {
		return selectedLabourCode;
	}

	public void setSelectedLabourCode(String selectedLabourCode) {
		this.selectedLabourCode = selectedLabourCode;
	}

	 

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public BigDecimal getLabourHours() {
		return labourHours;
	}

	public void setLabourHours(BigDecimal labourHours) {
		this.labourHours = labourHours;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public Map<String, String[]> getParameters() {
		return parameters;
	}

	public List<Splaborm> getActivityCode() {
		return activityCode;
	}

	public void setActivityCode(List<Splaborm> activityCode) {
		this.activityCode = activityCode;
	}

	public String getSelectedActivityCode() {
		return selectedActivityCode;
	}

	public void setSelectedActivityCode(String selectedActivityCode) {
		this.selectedActivityCode = selectedActivityCode;
	}









}
