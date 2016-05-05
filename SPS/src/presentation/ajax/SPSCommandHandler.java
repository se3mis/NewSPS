package presentation.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;



public class SPSCommandHandler implements AjaxCommandHandler {
		 private static final String COMMAND_ADD_NORMS = "addNorms";
		 private static final String COMMAND_CALCULATE_COST = "calculateCost";
		 private static final String COMMAND_LOAD_APPLICATIONREFS = "loadApplicationRefs";
		 private static final String COMMAND_LOAD_ALLNORMS = "loadNorms";
		
		 private static final String COMMAND_LOAD_APPLICATION_DETAILS = "loadApplicationDetails";
		 private static final String COMMAND_CLEAR_FORM_DETAILS = "clearFormData";
		 private static final String COMMAND_GET_ESTIMATED_COST_DETAILS = "getEstimatedCostDetails";
		 private static final String COMMAND_DELETE_LINEDETAILS = "deleteLineDetails";
		 private static final String COMMAND_UPDATE_LINEDETAILS = "updateLineDetails";
		 private static final String COMMAND_LOAD_ESTIMATIONREFS = "loadStdEstimationRefs";
		 private static final String COMMAND_LOAD_SUBSTATION_COST = "setAddSubstationCost";
		 private static final String COMMAND_SAVE_DESCRIPTION = "getDescriptionDetails";
		 private static final String COMMAND_LOAD_BRANCH_CODES = "loadbranchCodes";
		 private static final String COMMAND_LOAD_STANDARD_ESTIMATE_SCHEME_DETAILS = "loadSchemaDetails";
		 private static final String COMMAND_LOAD_NORMS_TREE = "loadNormsTree";
		 private static final String COMMAND_LOAD_LINE_SUMMARY_DETAILS = "loadLineSummaryDetails";
		 
		 private static final String COMMAND_UPDATE_ALLOCATED_LENGTH = "getAllocatedLengthDetails";
		 private static final String COMMAND_LOAD_LINE_SUMMARY_DETAILS_IN_CACHE = "loadLineSummaryDetailsInCache";
		 private static final String COMMAND_LOAD_USERNAME = "getUserName";
		 private static final String COMMAND_LOAD_STANDARD_ESTIMATES_FOR_VIEW ="loadStdEstimationRefsforValidation";
		 private static final String COMMAND_LOAD_CODENAME = "getCodeName";
		 
	    public String[] getValidCommands() {
	        return new String[]{
	        		COMMAND_ADD_NORMS,
	        		COMMAND_LOAD_APPLICATIONREFS,
	        		COMMAND_LOAD_ALLNORMS,
	        		
	        		COMMAND_LOAD_APPLICATION_DETAILS,
	        		COMMAND_CLEAR_FORM_DETAILS,
	        		COMMAND_GET_ESTIMATED_COST_DETAILS,
	        		COMMAND_DELETE_LINEDETAILS,
	        		COMMAND_UPDATE_LINEDETAILS,
	        		COMMAND_LOAD_ESTIMATIONREFS,
	        		COMMAND_LOAD_SUBSTATION_COST,
	        		COMMAND_SAVE_DESCRIPTION,
	        		COMMAND_LOAD_BRANCH_CODES,
	        		COMMAND_LOAD_STANDARD_ESTIMATE_SCHEME_DETAILS,
	        		COMMAND_LOAD_NORMS_TREE,
	        		COMMAND_LOAD_LINE_SUMMARY_DETAILS,
	        		COMMAND_UPDATE_ALLOCATED_LENGTH,
	        		COMMAND_LOAD_LINE_SUMMARY_DETAILS_IN_CACHE,
	        		COMMAND_LOAD_USERNAME,
	        		COMMAND_LOAD_STANDARD_ESTIMATES_FOR_VIEW,
	        		COMMAND_LOAD_CODENAME
	        		
	        };
	    }

	    public AjaxResponse executeCommand(HttpServletRequest request, String commandName, Map parameters) {
	        try {
	            if (commandName.equals(COMMAND_ADD_NORMS)) {
	            	SPSAjaxController controller = new SPSAjaxController();
	                JSONObject response = controller.addNorms(request);
	               
	                return new AjaxResponse(AjaxResponse.TYPE_JSON, response);
	            } /*else if (commandName.equals(COMMAND_CALCULATE_COST)) {
	            	SPSAjaxController controller = new SPSAjaxController();
	                JSONObject response = controller.calculateCost(request);
	               
	                return new AjaxResponse(AjaxResponse.TYPE_JSON, response);
	            }*/ else if (commandName.equals(COMMAND_LOAD_APPLICATIONREFS)) {
	            	SPSAjaxController controller = new SPSAjaxController();
	                JSONObject response = controller.loadApplicationRefNumbers(request);
	               
	                return new AjaxResponse(AjaxResponse.TYPE_JSON, response);
	            } else if (commandName.equals(COMMAND_LOAD_ALLNORMS)) {
	            	SPSAjaxController controller = new SPSAjaxController();
	                //JSONObject response = controller.showNorms(request);
	                JSONObject response = controller.showChildNorms(request);
	                
	                return new AjaxResponse(AjaxResponse.TYPE_JSON, response);
	            }  else if (commandName.equals(COMMAND_LOAD_APPLICATION_DETAILS)) {
	            	SPSAjaxController controller = new SPSAjaxController();
	            	JSONObject response = controller.loadApplicationDetails(request);
	               
	            	return new AjaxResponse(AjaxResponse.TYPE_JSON, response);
	            } else if (commandName.equals(COMMAND_CLEAR_FORM_DETAILS)) {
	            	SPSAjaxController controller = new SPSAjaxController();
	            	controller.clearForm(request);
	               
	            } else if (commandName.equals(COMMAND_GET_ESTIMATED_COST_DETAILS)) {
	            	SPSAjaxController controller = new SPSAjaxController();
	            	controller.getEstimatedCostDetails(request);
	               
	            } else if (commandName.equals(COMMAND_DELETE_LINEDETAILS)) {
	            	SPSAjaxController controller = new SPSAjaxController();
	            	JSONObject response = controller.deleteLineDetails(request);
	            	return new AjaxResponse(AjaxResponse.TYPE_JSON, response);
	            } else if (commandName.equals(COMMAND_UPDATE_LINEDETAILS)) {
	            	SPSAjaxController controller = new SPSAjaxController();
	            	JSONObject response = controller.updateLineDetails(request);
	            	return new AjaxResponse(AjaxResponse.TYPE_JSON, response);
	            } else if (commandName.equals(COMMAND_LOAD_ESTIMATIONREFS)) {
	            	SPSAjaxController controller = new SPSAjaxController();
	            	JSONObject response = controller.loadEstmationRefNumbers(request);
	            	return new AjaxResponse(AjaxResponse.TYPE_JSON, response);
	            } else if (commandName.equals(COMMAND_LOAD_SUBSTATION_COST)) {
	            	SPSAjaxController controller = new SPSAjaxController();
	            	JSONObject response = controller.setAddSubstationCost(request);
	            	return new AjaxResponse(AjaxResponse.TYPE_JSON, response);
	            }  else if (commandName.equals(COMMAND_SAVE_DESCRIPTION)) {
	            	SPSAjaxController controller = new SPSAjaxController();
	            	controller.getDescriptionDetails(request);
	            	//return new AjaxResponse(AjaxResponse.TYPE_JSON, response);
	            } else if (commandName.equals(COMMAND_LOAD_BRANCH_CODES)) {
	            	SPSAjaxController controller = new SPSAjaxController();
	            	JSONObject response = controller.loadBankBranches(request);
	            	return new AjaxResponse(AjaxResponse.TYPE_JSON, response);
	            	
	            	//return new AjaxResponse(AjaxResponse.TYPE_JSON, response);
	            } else if (commandName.equals(COMMAND_LOAD_STANDARD_ESTIMATE_SCHEME_DETAILS)) {
	            	SPSAjaxController controller = new SPSAjaxController();
	            	JSONObject response = controller.loadSchemaDetails(request);
	            	return new AjaxResponse(AjaxResponse.TYPE_JSON, response);
	            	
	            	//return new AjaxResponse(AjaxResponse.TYPE_JSON, response);
	            } else if (commandName.equals(COMMAND_LOAD_NORMS_TREE)) {
	            	SPSAjaxController controller = new SPSAjaxController();
	            	AjaxResponse response = controller.loadNormsTree(request);
	            	return response;
	            	
	            	//return new AjaxResponse(AjaxResponse.TYPE_JSON, response);
	            }else if (commandName.equals(COMMAND_LOAD_LINE_SUMMARY_DETAILS)) {
	            	SPSAjaxController controller = new SPSAjaxController();
	            	JSONObject response = controller.loadLineSummaryDetails(request);
	            	return new AjaxResponse(AjaxResponse.TYPE_JSON, response);
	            	
	            	//return new AjaxResponse(AjaxResponse.TYPE_JSON, response);
	            }
	            else if (commandName.equals(COMMAND_UPDATE_ALLOCATED_LENGTH)) {
	            	SPSAjaxController controller = new SPSAjaxController();
	            	controller.getAllocatedLengthDetails(request);
	            
	            	
	            	//return new AjaxResponse(AjaxResponse.TYPE_JSON, response);
	            }/*else if (commandName.equals(COMMAND_LOAD_LINE_SUMMARY_DETAILS_IN_CACHE)) {
	            	SPSAjaxController controller = new SPSAjaxController();
	            
	            	JSONObject response = controller.loadLineSummaryDetailsInCache(request);
	            	return new AjaxResponse(AjaxResponse.TYPE_JSON, response);
	            	
	            	
	            	//return new AjaxResponse(AjaxResponse.TYPE_JSON, response);
	            }*/
	            else if (commandName.equals(COMMAND_LOAD_USERNAME)) {
	            	SPSAjaxController controller = new SPSAjaxController();
	            	JSONObject response = controller.getUsername(request);
	            	return new AjaxResponse(AjaxResponse.TYPE_JSON, response);
	            	
	            	//return new AjaxResponse(AjaxResponse.TYPE_JSON, response);
	            } else if (commandName.equals(COMMAND_LOAD_STANDARD_ESTIMATES_FOR_VIEW)) {
	            	SPSAjaxController controller = new SPSAjaxController();
	            	JSONObject response = controller.loadEstmationRefNumbers(request);
	            	return new AjaxResponse(AjaxResponse.TYPE_JSON, response);
	            	
	            	//return new AjaxResponse(AjaxResponse.TYPE_JSON, response);
	            }else if (commandName.equals(COMMAND_LOAD_CODENAME)) {
	            	SPSAjaxController controller = new SPSAjaxController();
	            	JSONObject response = controller.getCodeName(request);
	            	return new AjaxResponse(AjaxResponse.TYPE_JSON, response);
	            	
	            	//return new AjaxResponse(AjaxResponse.TYPE_JSON, response);
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
