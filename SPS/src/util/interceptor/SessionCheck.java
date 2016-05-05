
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util.interceptor;

/**
 *
 * @author Dileepa Waduge
 */
//import java.util.Collections;
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
public class SessionCheck implements Interceptor {
    private static final Log log = LogFactory.getLog(SessionCheck.class);


    public void init() {
    }

    public void destroy() {
    }

    @SuppressWarnings("unchecked")
	public String intercept(ActionInvocation invocation) throws Exception {
        //ActionProxy proxy;
        //proxy = invocation.getProxy();
        Map session = ActionContext.getContext().getSession();
        //Map results = proxy.getConfig().getResults();
        /*
         * We are checking that the user
         * that is set at login time is null or not
         */
        log.info("checking intercepors....");
        if (session.get("userName") != null) {
            log.info("userName not equal to null");
            return invocation.invoke();
            //return "success";
        } else {
            /* Here we are passing the result to an
             * invalid page by specifying its location
             */
            
            //ResultConfig rc = new ResultConfig("name", "clazz");
            //rc.setLocation(Location.UNKNOWN);


            //results.put("invalid", rc);
            log.info("userName equal to null");
            return "invalid";
        }

    }
}
