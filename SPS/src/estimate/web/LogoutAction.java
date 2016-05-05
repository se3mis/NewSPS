package estimate.web;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author Dileepa Waduge
 */
@SuppressWarnings("serial")
public class LogoutAction extends ActionSupport implements SessionAware{
	private Map<String, Object> session;
	private String region;
    private static final Log log = LogFactory.getLog(LogoutAction.class);
    @Override
    public String execute(){
    	log.info("user logout");
      return SUCCESS;
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
    /**
     * @param session the session to set
     */
    public void removeSessionKey(String key) {
        getSession().remove(key);
    }
    
    public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}


}
