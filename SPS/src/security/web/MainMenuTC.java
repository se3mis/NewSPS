package security.web;

import java.util.Map;

import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class MainMenuTC extends ActionSupport implements SessionAware, ParameterAware{
	private Map<String, Object> session;
	private Map <String, String[]> parameters;
	private String loggedInUserId;
	private String loggedInCostCenterNo;
	private String loggedInUserLevel;
	private String costCenterNo;
	
	public String execute(){
		return SUCCESS;
		
	}
	
	public Map<String, String[]> getParameters() {
		return parameters;
	}
	
	@Override
	public void setParameters(Map<String, String[]> parameters) {
		this.parameters=parameters;
	}
	
	public Map<String, Object> getSession() {
        return session;

    }

	@Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }
	
	public String getLoggedInUserId() {
		return loggedInUserId;
	}

	public void setLoggedInUserId(String loggedInUserId) {
		this.loggedInUserId = loggedInUserId;
	}

	public String getLoggedInCostCenterNo() {
		return loggedInCostCenterNo;
	}

	public void setLoggedInCostCenterNo(String loggedInCostCenterNo) {
		this.loggedInCostCenterNo = loggedInCostCenterNo;
	}

	public String getLoggedInUserLevel() {
		return loggedInUserLevel;
	}

	public void setLoggedInUserLevel(String loggedInUserLevel) {
		this.loggedInUserLevel = loggedInUserLevel;
	}

	public String getCostCenterNo() {
		return costCenterNo;
	}

	public void setCostCenterNo(String costCenterNo) {
		this.costCenterNo = costCenterNo;
	}

}
