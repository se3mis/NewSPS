package masters.web;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.SessionAware;

import util.common.ApplicationType;
import util.common.Format;
import util.common.Phase;

import com.opensymphony.xwork2.ActionSupport;

 
import estimate.model.Splabrat;
import estimate.model.SplabratPK;
import estimate.service.SplabratEjb;

public class LabourActivityRates extends ActionSupport implements SessionAware, ParameterAware {

	private Map<String, Object> session;
	private Map <String, String[]> parameters;
	private String loggedInUserId;
	private static final String viewPath="Masters>Labour Activity Rates";
	private String path;
	private String lblError = null;
	private String lblSuccess = null;
	private List<Phase> applicationTypeList; // to storre application type and code i use Phase class....
	
	 
	private String labourCode;
	private String costCenter;
	private String labourName;
	private String selectedApplicationType;
	
	private String description;
	private BigDecimal unitPrice;
	private BigDecimal labourHours;
	
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
		
		setApplicationTypesList();
		 
		
		 
		
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
	
	private void getLabourActivityRates(String labourCde)
	{
		SplabratPK id = new SplabratPK();
		id.setDeptId(getCostCenter());
		id.setLabourCode(labourCde);
		SplabratEjb ejb = new SplabratEjb(getSessionKey("region"));
		Splabrat splabrat = null;
	
		try
		{
			  
			splabrat = ejb.findById(id);
			  
		}catch(Exception e1)
		{		
			 setLblError("Labour Activity Rates not found !");
			 return;
		}
		if (splabrat == null)
		{
			BigDecimal bigD = new BigDecimal("0");
			 setUnitPrice(bigD) ;
			 setLabourHours(bigD);
			  
		}else
		{
			setLabourCode(labourCde);
		   setLabourName(splabrat.getLabourName());
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
		
		SplabratPK id = new SplabratPK();
		id.setDeptId(getCostCenter());
		id.setLabourCode(getLabourCode());
		Splabrat splabrat = null;
		SplabratEjb ejb = new SplabratEjb(getSessionKey("region"));
		
		try
		{			  
			splabrat = ejb.findById(id);
			  
		}catch(Exception e1)
		{
			return SUCCESS;
		}
		if (splabrat == null)
		{
			splabrat = new Splabrat();
			splabrat.setId(id);
			splabrat.setLabourName(getLabourName());
			splabrat.setApplicationType(getSelectedApplicationType());
			splabrat.setDescription(getDescription());
			splabrat.setUnitPrice(getUnitPrice());
			splabrat.setLabourHours(getLabourHours());
			 
			ejb.createSplabrat(splabrat);
		}else
		{
 
			clearValues (false);
			execute();
			setLblError("Labour code already exists for this cost centre");
			return SUCCESS;
		}
		
		clearValues (true);
		 execute();
		 setLblSuccess("Labour activity rates successfully added.");
		return SUCCESS;
	}
	
	
	public void clearValues (boolean clearlbCode)
	{
		if (clearlbCode)
			 labourCode = "";
	 
			
		  
		 costCenter = "";
		 labourName = "";
		 selectedApplicationType = "";
		
		 description = "";
		 unitPrice = null;
		 labourHours = null;
	}
	public String Close(){
		return "close";
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

	 

	public Map<String, Object> getSession() {
		return session;
	}

	public Map<String, String[]> getParameters() {
		return parameters;
	}

	public List<Phase> getApplicationTypeList() {
		return applicationTypeList;
	}

	public void setApplicationTypeList(List<Phase> applicationTypeList) {
		this.applicationTypeList = applicationTypeList;
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

	public String getLabourName() {
		return labourName;
	}

	public void setLabourName(String labourName) {
		this.labourName = labourName;
	}

	public String getSelectedApplicationType() {
		return selectedApplicationType;
	}

	public void setSelectedApplicationType(String selectedApplicationType) {
		this.selectedApplicationType = selectedApplicationType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
	
	
	
	

}
