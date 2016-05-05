package calendarBS.web;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class TestDownload extends ActionSupport implements SessionAware, ParameterAware{

	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(DiaryEntry.class);
	private Map<String, Object> session;
	private Map <String, String[]> parameters;
	
	public Map<String, Object> getSession() {
		return session;
	}
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	public Map<String, String[]> getParameters() {
		return parameters;
	}
	public void setParameters(Map<String, String[]> parameters) {
		this.parameters = parameters;
	}
	
	public String execute(){		
		try{
			
		}catch(Exception ex){
			
		}
		
		return "success";
	}
}
