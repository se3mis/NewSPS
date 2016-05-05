package masters.web;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import estimate.model.Spugcstm;
import estimate.service.SpugcstmEjb;

 

public class SPUGCSTMMasterList extends ActionSupport implements SessionAware, ParameterAware  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map<String, Object> session;
	private Map <String, String[]> parameters;
	private String loggedInUserId;
	private static final String viewPath="Masters>Service Type Material Master Detials";
	private String path;
	private String lblError = null;
	private String lblSuccess = null;
	private String costCenter;
	private List<Spugcstm> MaterialList;
	
	public String execute(){
		setLblError(null);
		
		setLoggedInUserId(getSession().get("userName").toString());
		setPath(viewPath);
		setCostCenter(getSessionKey("costCenterNo"));
		
		 fillCostingList();
		return SUCCESS;
	}
	
	public String Close(){
		return "close";
	}
	
	
	private void fillCostingList()
	{
		SpugcstmEjb ejb = new SpugcstmEjb(getSessionKey("region"));
		this.MaterialList = ejb.getAll();
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


	public String getCostCenter() {
		return costCenter;
	}


	public void setCostCenter(String costCenter) {
		this.costCenter = costCenter;
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

	public List<Spugcstm> getMaterialList() {
		return MaterialList;
	}

	public void setMaterialList(List<Spugcstm> materialList) {
		MaterialList = materialList;
	}

 
	
	
}
