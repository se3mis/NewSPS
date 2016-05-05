package presentation.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;



public class SendForValidationSPSCommandHandler implements AjaxCommandHandler {
		
		 private static final String COMMAND_LOAD_ESTIMATIONREFS_FOR_VALIDATION = "loadStdEstimationRefsforValidation";
		
		
		 
	    public String[] getValidCommands() {
	        return new String[]{
	        		COMMAND_LOAD_ESTIMATIONREFS_FOR_VALIDATION
	        		
	        };
	    }

	    public AjaxResponse executeCommand(HttpServletRequest request, String commandName, Map parameters) {
	        try {
	            if (commandName.equals(COMMAND_LOAD_ESTIMATIONREFS_FOR_VALIDATION)) {
	            	SendForValidationAjaxController controller = new SendForValidationAjaxController();
	                JSONObject response = controller.loadEstmationRefNumbersForValidation(request);
	               
	                return new AjaxResponse(AjaxResponse.TYPE_JSON, response);
	            }
	        } catch (Exception e) {

	            return null;
	        }

	        return null;
	    }

	/*public void doPost(HttpServletRequest request, HttpServletResponse response){
		 ObjectMapper mapper = new ObjectMapper();
	    
		String action = request.getParameter("action");
	    if(action.equals("sendmessage")){
	    	PrintWriter out = null;
	    	HashMap<String, Object> untyped = new HashMap<String, Object>();
	    	try {
	    	SmsNotificationController controller = new SmsNotificationController();
	    	//JSONObject object  = controller.sendSmsNotification();
	    	 response.setContentType("application/json; charset=utf-8");
	         //PrintWriter out = response.getWriter();

	         
	    	//response.setContentType("text/json");
	    	//response.setCharacterEncoding("utf-8"); 
	    	
	    	// Get the printwriter object from response to write the required json object to the output stream      
	    	
				out = response.getWriter();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	    	// Assuming your json object is **jsonObject**, perform the following, it will return your json object  
	    	//out.print((new JSONObject().put("status","success")));
	    	//out.print(new JSONObject().put("status","success"));
	    	
	    	untyped.put("status", "success");
            
            mapper.writeValue(out, untyped);
            out.flush();

	    } 
	}*/

}
