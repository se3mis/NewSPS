/**
 * 
 */
package security.web;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.interceptor.SessionAware;
import com.opensymphony.xwork2.ActionSupport;



/**
 * @author Dileepa Waduge
 *
 */
public class Index extends ActionSupport implements SessionAware{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String region;
	private Map<String, Object> session;
	private static final Log log = LogFactory.getLog(Index.class);
	
	public String execute() {
		//only when logout 
		if (getSession().get("region")!=null) 
			setRegion(getSession().get("region").toString());
		return "success";
	       
	   }
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
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
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

}
