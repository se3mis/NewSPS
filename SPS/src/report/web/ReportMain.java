package report.web;



import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.SessionAware;

import report.model.TreeNode;



import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class ReportMain extends ActionSupport implements SessionAware, ParameterAware{
	private static final Log log = LogFactory.getLog(ReportMain.class);
	private Map<String, Object> session;
	private Map <String, String[]> parameters;
	private TreeNode rootNode;
	private String selectedNode;
	private String loggedInUserId;
	
	


	public String execute(){
		setLoggedData();
		return SUCCESS;
	}
	
	public void setLoggedData() {
        log.info(getSession());
		setLoggedInUserId(getSessionKey("userName"));
        //setCostCenterNo(getSessionKey("costCenterNo"));
        //setCebBranch(getSessionKey("costCenterName"));
        //setCsc(getCebBranch());
        
    }
	
	public String getSessionKey(String key) {
        return getSession().get(key).toString();
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
	
	public TreeNode getRootNode() {
		return rootNode;
	}


	public void setRootNode(TreeNode rootNode) {
		this.rootNode = rootNode;
	}


	public String getSelectedNode() {
		return selectedNode;
	}


	public void setSelectedNode(String selectedNode) {
		this.selectedNode = selectedNode;
	}
	
	public String getLoggedInUserId() {
		return loggedInUserId;
	}


	public void setLoggedInUserId(String loggedInUserId) {
		this.loggedInUserId = loggedInUserId;
	}

}
