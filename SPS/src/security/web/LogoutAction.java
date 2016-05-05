package security.web;

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
public class LogoutAction extends ActionSupport{
	
    @Override
    public String execute(){
      
      return SUCCESS;
    }

   

}
