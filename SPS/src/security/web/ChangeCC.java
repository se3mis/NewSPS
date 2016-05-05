package security.web;

import java.util.Map;

import masters.service.ProvinceDetailsMasterEjb;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.SessionAware;

import util.common.Constants;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;


@SuppressWarnings("serial")
public class ChangeCC extends ActionSupport implements SessionAware, ParameterAware{
	private Map<String, Object> session;
	private Map <String, String[]> parameters;
	private static final Log log = LogFactory.getLog(ChangeCC.class);
	private String smcType;
	private String costCenterNo;
	private String loggedInUserId;
	private String branchType;
	private String estimateType;
		
	@Override
    public String execute(){
      //setSession(ActionContext.getContext().getSession());
		log.info("BEFORE $$$$$$$$$$$$$$$$$$$"+getSession());
		setCostCenterNo(getSessionKey("costCenterNo"));
		setSmcType(getSessionKey("smcType"));
		setLoggedInUserId(getSessionKey("userName"));
		
		ProvinceDetailsMasterEjb promasEjb = new ProvinceDetailsMasterEjb();
		   String branchType = promasEjb.getBranchType(getCostCenterNo(), getSessionKey("region"));
		   if(branchType != null){
			  setBranchType(branchType);
		   }else{
			   setBranchType(Constants.DEFAULT_ESTIMATE_TYPE);
		   }
		   putSessionKey("branchType",getBranchType());
		   
		   String estimateType = promasEjb.getEstimateTypeByDeptId(getCostCenterNo(), getSessionKey("region"));
		   if(estimateType != null){
			  setEstimateType(estimateType);
		   }else{
			  setEstimateType(Constants.DEFAULT_ESTIMATE_TYPE);
		   }
		   putSessionKey("estimateType",getEstimateType());
   
		//removeSessionKey("userName");
      //removeSessionKey("costCenterNo");
      //removeSessionKey("costCenterName");
	  //removeSessionKey("costCenterName");	
      log.info("AFTER $$$$$$$$$$$$$$$$$$$"+getSession());
      return SUCCESS;
    }
	
	private void putSessionKey(String key, String value ){
		   getSession().put(key, value);
	   
	   }
	/**
     * @return the session
     */
    public Map<String, Object> getSession() {
        return session;
    }

    /**
     * @param session the session to set
     */
    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    public Map<String, String[]> getParameters() {
		return parameters;
	}
	
	@Override
	public void setParameters(Map<String, String[]> parameters) {
		this.parameters=parameters;
	}
	
	public void removeSessionKey(String key) {
        getSession().remove(key);
    }
	
	public String getSmcType() {
		return smcType;
	}
	public void setSmcType(String smcType) {
		this.smcType = smcType;
	}
	public String getCostCenterNo() {
		return costCenterNo;
	}
	public void setCostCenterNo(String costCenterNo) {
		this.costCenterNo = costCenterNo;
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
	
	public String getEstimateType() {
		return estimateType;
	}



	public void setEstimateType(String estimateType) {
		this.estimateType = estimateType;
	}
	
	public String getBranchType() {
		return branchType;
	}



	public void setBranchType(String branchType) {
		this.branchType = branchType;
	}


}
