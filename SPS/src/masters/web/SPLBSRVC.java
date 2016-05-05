package masters.web;

import inventory.model.Inmatm;
import inventory.service.InmatmEjb;

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
import estimate.service.SplabormEjb;
 
import estimate.service.SplbsrvcEjb;

public class SPLBSRVC extends ActionSupport implements SessionAware, ParameterAware {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map<String, Object> session;
	private Map <String, String[]> parameters;
	private String loggedInUserId;
	private static final String viewPath="Masters>Stringing Service Wire";
	private String path;
	private String lblError = null;
	private String lblSuccess = null;
	
	private String labourCode;
	private String costCenter;
	private  List<Splaborm> activityCode; 
	private String selectedActivityCode;
	private  List<Phase> phase; 
	private String selectedPhase;
	private  List<Phase> spanType; 
	private String selectedSpanType;
	private List<Phase> applicationTypeList; // to storre application type and code i use Phase class....
	private String selectedApplicationType;
	private BigDecimal unitPrice;
	private BigDecimal labourHours;
	
	public String execute(){

		setLblError(null);
		
		setLoggedInUserId(getSession().get("userName").toString());
		setPath(viewPath);
		setCostCenter(getSessionKey("costCenterNo"));
		setActivityCodeList();
		setPhaseList();
		setApplicationTypesList();
		setSpanTypeList();
		return SUCCESS;
	}

	
	private void setPhaseList()
	{
		this.phase = new   ArrayList<Phase>();
		Phase single = new Phase("Single Phase", "1");		 
		Phase three = new Phase("3 Phase","3");
		this.phase.add(single);		 
		this.phase.add(three);

	}

	
	
	 public String UpdateMaster()
	 {
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
			id.setLabourCode(getLabourCode());
			
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
				splbsrvc = new Splbsrvc();
				splbsrvc.setId(id);
				splbsrvc.setActivityCode(getSelectedActivityCode());
				splbsrvc.setPhase(new BigDecimal(getSelectedPhase()));
				splbsrvc.setSpanType(getSelectedSpanType());
				splbsrvc.setApplicationType(getSelectedApplicationType());
				splbsrvc.setUnitPrice(getUnitPrice());
				splbsrvc.setLabourHours(getLabourHours());
				 
				ejb.createSplbsrvc(splbsrvc);
			}else
			{
	 
				 
				execute();
				setLblError("Labour code already exists for this cost centre");
				return SUCCESS;
			}
			
		 
			 execute();
			 setLblSuccess("Labour activity stringing service wire successfully added.");
			return SUCCESS;
		 
	 }
	
	public String Close(){
		return "close";
	}
	
	 
	
	private void setActivityCodeList()
	{
		this.activityCode = new ArrayList<Splaborm>();

		SplabormEjb ejb = new SplabormEjb(getSessionKey("region"));
		this.activityCode =  ejb.getAll();
	}
	
	private void setSpanTypeList()
	{
		this.spanType = new   ArrayList<Phase>();
		Phase single = new Phase("Last", "LAST");		 
		Phase three = new Phase("Additional","OTHER");
		this.spanType.add(single);		 
		this.spanType.add(three);
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

	public String getLabourCode() {
		return labourCode;
	}

	public void setLabourCode(String labourCode) {
		this.labourCode = labourCode;
	}

	public String getCostCenter() {
		return costCenter;
	}

	public void setCostCenter(String costCenter) {
		this.costCenter = costCenter;
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
	
	
	
}

