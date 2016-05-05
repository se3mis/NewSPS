package masters.web;

import java.math.BigDecimal;
import java.util.Map;

import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import estimate.model.Splaborm;
import estimate.service.SplabormEjb;

 

public class LabourActivityMaster extends ActionSupport implements SessionAware, ParameterAware {
	
	private static final long serialVersionUID = 1L;
	private Map<String, Object> session;
	private Map <String, String[]> parameters;
	private String loggedInUserId;
	private static final String viewPath="Masters>Labour Activity";
	private String path;
	private String lblError = null;
	private String lblSuccess = null;
	
	private String activityCode;
	private String activityName;
	private String activityDescription;
	
	public String execute(){
		setLblError(null);
		
		setLoggedInUserId(getSession().get("userName").toString());
		setPath(viewPath);
		return SUCCESS;
	}
	
	public String Close(){
		return "close";
	}
	
	public String  AddLabourActivity()
	{
		 
		
		Splaborm splaborm = null;
		SplabormEjb ejb = new SplabormEjb(getSessionKey("region"));
		 String actvCode = getActivityCode();
		 if (actvCode == null || actvCode.trim().equals(""))
		 {
			 execute();
				setLblError("Please enter the activity code.");
				return SUCCESS;
		 }
	 
		
		try
		{			  
			splaborm = ejb.findById(actvCode.trim());
			  
		}catch(Exception e1)
		{
			return SUCCESS;
		}
		if (splaborm == null)
		{
			splaborm = new Splaborm();
			splaborm.setActivityCode(actvCode.trim());
			splaborm.setActivityName(getActivityName());
			splaborm.setDescription(getActivityDescription());
			 
			 
			ejb.createSplaborm(splaborm);
		}else
		{
 
			 
			execute();
			setLblError("Labour activity already exists. !!!");
			return SUCCESS;
		}
		
		 
		 execute();
		 setLblSuccess("Labour activity  successfully added.");
		return SUCCESS;
	}
	
	 
	@Override
	public void setParameters(Map<String, String[]> parameters){
		// TODO Auto-generated method stub
		this.parameters=parameters;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		// TODO Auto-generated method stub
		this.session=session;
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

	public String getActivityCode() {
		return activityCode;
	}

	public void setActivityCode(String activityCode) {
		this.activityCode = activityCode;
	}

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public String getActivityDescription() {
		return activityDescription;
	}

	public void setActivityDescription(String activityDescription) {
		this.activityDescription = activityDescription;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public Map<String, String[]> getParameters() {
		return parameters;
	}
	public String getSessionKey(String key) {
		return getSession().get(key).toString();
	}

}
