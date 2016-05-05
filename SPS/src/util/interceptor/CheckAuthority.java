package util.interceptor;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
//import com.opensymphony.xwork2.ActionProxy;
//import com.opensymphony.xwork2.config.entities.ResultConfig;
import com.opensymphony.xwork2.interceptor.Interceptor;
//import com.opensymphony.xwork2.util.location.Location;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@SuppressWarnings("serial")
public class CheckAuthority implements Interceptor{
	private static final Log log = LogFactory.getLog(CheckAuthority.class);
	private String  errorMessage;
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		Map session = ActionContext.getContext().getSession();
		log.info("checking intercepors....");
		if (session.get("userRole") != null) {
            log.info("userRole not equal to null");
            if (session.get("userRole").equals("AE")){
            	return invocation.invoke();
                //return "success";
            }else {
            	log.info("Not Authorize User");
                setErrorMessage("Not Authorize User");
            	return "notAutorized";
            }
            
        } else {
            /* Here we are passing the result to an
             * invalid page by specifying its location
             */
            
            //ResultConfig rc = new ResultConfig("name", "clazz");
            //rc.setLocation(Location.UNKNOWN);


            //results.put("invalid", rc);
            log.info("Not Authorize User");
            setErrorMessage("Not Authorize User");
            return "invalid";
        }
		
	}
	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}


}
