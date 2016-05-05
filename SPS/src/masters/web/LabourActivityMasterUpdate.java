package masters.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import estimate.model.Splaborm;
 
 
import estimate.service.SplabormEjb;
 
 

public class LabourActivityMasterUpdate extends ActionSupport implements SessionAware, ParameterAware {
	
	private static final long serialVersionUID = 1L;
	private Map<String, Object> session;
	private Map <String, String[]> parameters;
	private String loggedInUserId;
	private static final String viewPath="Masters>Labour Activity";
	private String path;
	private String lblError = null;
	private String lblSuccess = null;
	
	private List<Splaborm> activityCodeList;
	private String selectedActivityCode;
	private String activityName;
	private String activityDescription;
	
	
	public String execute(){
		setLblError(null);
		
		setLoggedInUserId(getSession().get("userName").toString());
		setPath(viewPath);
		
		
		setActivityCodeList();
		getLabourActivityMasters();
		return SUCCESS;
	}
	
	
	private void setActivityCodeList()
	{


		this.activityCodeList = new   ArrayList<Splaborm>();			 
		SplabormEjb ejb = new SplabormEjb(getSessionKey("region"));
		this.activityCodeList = ejb.getAll();
		if (getSelectedActivityCode() == null)
		{
			if (this.activityCodeList != null || this.activityCodeList.size() > 0)
				this.selectedActivityCode = this.activityCodeList.get(0).getActivityCode();
		}
	}
	
	
	public String Close(){
		return "close";
	}
	
	public String  UpdateLabourActivity()
	{
		 
		
		Splaborm splaborm = null;
		SplabormEjb ejb = new SplabormEjb(getSessionKey("region"));
		 String actvCode = getSelectedActivityCode();
		 if (actvCode == null || actvCode.trim().equals(""))
		 {
			 execute();
				setLblError("Please select the activity code.");
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
			
		}else
		{
 
			splaborm = new Splaborm();
			splaborm.setActivityCode(actvCode.trim());
			splaborm.setActivityName(getActivityName());
			splaborm.setDescription(getActivityDescription());
			 
			 
			ejb.updateSplaborm(splaborm);
		}
		
		 
		 execute();
		 setLblSuccess("Labour activity  successfully updated.");
		return SUCCESS;
	}
	
	
	private void getLabourActivityMasters()
	{
		String actCode = getSelectedActivityCode();

		 
		SplabormEjb ejb = new SplabormEjb(getSessionKey("region"));
		Splaborm splaborm = null;

		try
		{

			splaborm = ejb.findById(actCode);

		}catch(Exception e1)
		{		
			setLblError("Labour activity  not found !");
			return;
		}
		if (splaborm == null)
		{


		}else
		{

			setActivityName(splaborm.getActivityName());
			setActivityDescription(splaborm.getDescription());
			 
			 
		}



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


	public List<Splaborm> getActivityCodeList() {
		return activityCodeList;
	}


	public void setActivityCodeList(List<Splaborm> activityCodeList) {
		this.activityCodeList = activityCodeList;
	}


	public String getSelectedActivityCode() {
		return selectedActivityCode;
	}


	public void setSelectedActivityCode(String selectedActivityCode) {
		this.selectedActivityCode = selectedActivityCode;
	}


	 

}
