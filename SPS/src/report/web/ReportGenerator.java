package report.web;
import java.util.Map;

import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.SessionAware;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class ReportGenerator extends ActionSupport implements SessionAware, ParameterAware{
	private Map<String, Object> session;
	private Map <String, String[]> parameters;
	private String path;
	private String visibleCostCenter;

	

	public String execute(){
		//setPath("Report Name");
		if (getPath().equals("Report1")){//Name of the Report{
			System.out.println("visibleCostCenter "+visibleCostCenter);
			return methodReport1();
		}else {
			return ERROR;
		}
			
		
	}
	
	public String methodReport1(){//Call the report
		setPath("Call the Report Here");
		return "methodReport1";
	}
	
	public void view(){//Call the report
		setPath("Call the Report Here");
		System.out.println("view ");
		//return "methodReport1";
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
	
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	public String getVisibleCostCenter() {
		return visibleCostCenter;
	}

	public void setVisibleCostCenter(String visibleCostCenter) {
		this.visibleCostCenter = visibleCostCenter;
	}
	
	
}
