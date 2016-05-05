package masters.web;

import java.util.Map;

import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class Main extends ActionSupport implements SessionAware, ParameterAware{
	private Map<String, Object> session;
	private Map <String, String[]> parameters;
	public String execute(){
		return SUCCESS; 
	}
	
	
	public Map<String, Object> getSession() {
		return session;

	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	
	public Map <String, String[]> getParameters() {
		return parameters;
	}
		

	@Override
	public void setParameters(Map<String, String[]> parameters) {
		this.parameters=parameters;
		
	}
}
