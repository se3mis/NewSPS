package presentation.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;



public class SPSMasterDataCommandHandler implements AjaxCommandHandler {
		 private static final String COMMAND_LOAD_NORMS = "loadNorms1";
		 private static final String COMMAND_ADD_NEW_PEGITEM= "addNewPegItem";
		 private static final String COMMAND_MODIFY_PEGITEM=  "modifyPegItem";
		 private static final String COMMAND_DELETE_PEGITEM=  "deletePegItem";
		 private static final String COMMAND_RESOURCE_TYPES = "loadResourceTypes";
		 private static final String COMMAND_RESOURCE_CODES = "loadResourceCodes";
		 private static final String COMMAND_RESOURCE_CODE_DETAILS = "loadResourceDetails";
		 private static final String COMMAND_ADD_MATERIALS = "addMaterials";
		 private static final String COMMAND_VIEW_PEG_MASTER_DETAILS = "viewPegMasterDetails";
		 private static final String COMMAND_CLEAR_MASTER_DETAILS = "clearMaster";
		 private static final String COMMAND_CLEAR_MASTER_ITEMDETAILS = "clearItemListMaster";
		 private static final String COMMAND_GET_ESTIMATE_UPDATE_DETAILS = "getMasterUpdateDetails";
		 private static final String COMMAND_DELETE_PEG_INFO_DETAILS = "deleteMasterPegDetailLine";
		 private static final String COMMAND_ADD_BULK_MASTER_DETAILS = "addMasters";
		 private static final String COMMAND_LOAD_NO_CONTRACT_LETTER = "loadEstimateNumForContractorLetter";
		 private static final String COMMAND_LOAD_CONTRACTORS_CONTRACT_LETTER = "loadContractorForContractorLetter";
		 private static final String COMMAND_LOAD_CONSTRUCTION_ESTIMATE_FOT_JOB_ALLOCATION_LETTER = "loadConstructionEstimForJobAllocLetter";
		 private static final String COMMAND_LOAD_NO_JOB_ALLOCATION_LETTER = "loadEstimateNumForJobAllocationLetter";
		// private static final String COMMAND_LOAD_NO_JOB_ALLOCATION_LETTER = "loadSEstimateNumForJobAllocationLetter";
		 private static final String COMMAND_LOAD_CONSTRUCTION_REFERENS_FOR_CONTRACTOR_LETTER = "loadConstructonRefreForContractorLetter";
		 private static final String COMMAND_LOAD_ESTIMATE_DETAILS_FOR_CONTRACTOR_LETTER = "loadEstimateDetailsForContractorLetter";
		 private static final String COMMAND_LOAD_JOB_WORKSCOPE_CONTRACTOR_LETTER = "loadWorkScopeForJob";
		 private static final String COMMAND_LOAD_PEGINFO_MASTER = "loadPegInfoMaster";
		 
	    public String[] getValidCommands() {
	        return new String[]{
	        		COMMAND_LOAD_NORMS,
	        		COMMAND_ADD_NEW_PEGITEM,
	        		COMMAND_MODIFY_PEGITEM,
	        		COMMAND_DELETE_PEGITEM,
	        		COMMAND_RESOURCE_CODE_DETAILS,
	        		COMMAND_RESOURCE_TYPES,
	        		COMMAND_RESOURCE_CODES,
	        		COMMAND_ADD_MATERIALS,
	        		COMMAND_VIEW_PEG_MASTER_DETAILS,
	        		COMMAND_CLEAR_MASTER_DETAILS,
	        		COMMAND_GET_ESTIMATE_UPDATE_DETAILS,
	        		COMMAND_DELETE_PEG_INFO_DETAILS,
	        		COMMAND_ADD_BULK_MASTER_DETAILS,
	        		COMMAND_LOAD_NO_CONTRACT_LETTER,
	        		COMMAND_LOAD_CONTRACTORS_CONTRACT_LETTER,
	        		COMMAND_LOAD_CONSTRUCTION_ESTIMATE_FOT_JOB_ALLOCATION_LETTER,
	        		COMMAND_LOAD_NO_JOB_ALLOCATION_LETTER,
	        		COMMAND_LOAD_CONSTRUCTION_REFERENS_FOR_CONTRACTOR_LETTER,
	        		COMMAND_LOAD_ESTIMATE_DETAILS_FOR_CONTRACTOR_LETTER,
	        		COMMAND_LOAD_JOB_WORKSCOPE_CONTRACTOR_LETTER,
	        		COMMAND_LOAD_PEGINFO_MASTER,
	        		COMMAND_CLEAR_MASTER_ITEMDETAILS
	        		//COMMAND_LOAD_APPLICATIONREFS
	        		
	        		
	        };
	    }

	    public AjaxResponse executeCommand(HttpServletRequest request, String commandName, Map parameters) {
	        try {
	            if (commandName.equals(COMMAND_LOAD_NORMS)) {
	            	SPSMastersDataAjaxController controller = new SPSMastersDataAjaxController();
	                JSONObject response = controller.loadNorms(request,parameters);
	               
	                return new AjaxResponse(AjaxResponse.TYPE_JSON, response);
	            } else if (commandName.equals(COMMAND_ADD_NEW_PEGITEM)) {
	            	SPSMastersDataAjaxController controller = new SPSMastersDataAjaxController();
	                JSONObject response = controller.addPegItem(request,parameters);
	               
	                return new AjaxResponse(AjaxResponse.TYPE_JSON, response);
	            } else if (commandName.equals(COMMAND_MODIFY_PEGITEM)) {
	            	SPSMastersDataAjaxController controller = new SPSMastersDataAjaxController();
	                JSONObject response = controller.modifyPegItem(request,parameters);
	               
	                return new AjaxResponse(AjaxResponse.TYPE_JSON, response);
	            }  else if (commandName.equals(COMMAND_DELETE_PEGITEM)) {
	            	SPSMastersDataAjaxController controller = new SPSMastersDataAjaxController();
	                JSONObject response = controller.deletePegItem(request,parameters);
	               
	                return new AjaxResponse(AjaxResponse.TYPE_JSON, response);
	            }else if (commandName.equals(COMMAND_RESOURCE_TYPES)) {
	            	SPSMastersDataAjaxController controller = new SPSMastersDataAjaxController();
	                JSONObject response = controller.loadResourceTypes(request);
	               
	                return new AjaxResponse(AjaxResponse.TYPE_JSON, response);
	            } else if (commandName.equals(COMMAND_RESOURCE_CODES)) {
	            	SPSMastersDataAjaxController controller = new SPSMastersDataAjaxController();
	                JSONObject response = controller.loadResourceCodes(request);
	               
	                return new AjaxResponse(AjaxResponse.TYPE_JSON, response);
	            }else if (commandName.equals(COMMAND_RESOURCE_CODE_DETAILS)) {
	            	SPSMastersDataAjaxController controller = new SPSMastersDataAjaxController();
	                JSONObject response = controller.loadResourceDetails(request);
	               
	                return new AjaxResponse(AjaxResponse.TYPE_JSON, response);
	            } else if (commandName.equals(COMMAND_ADD_MATERIALS)) {
	            	SPSMastersDataAjaxController controller = new SPSMastersDataAjaxController();
	                JSONObject response = controller.addMaterials(request);
	               
	                return new AjaxResponse(AjaxResponse.TYPE_JSON, response);
	            }else if (commandName.equals(COMMAND_LOAD_PEGINFO_MASTER)) {
	            	SPSMastersDataAjaxController controller = new SPSMastersDataAjaxController();
	            	AjaxResponse response = controller.loadPegInfoMaster(request);
	               
	                return response;
	            }  else if (commandName.equals(COMMAND_VIEW_PEG_MASTER_DETAILS)) {
	            	SPSMastersDataAjaxController controller = new SPSMastersDataAjaxController();
	                JSONObject response = controller.viewPegMasterDetails(request);
	               
	                return new AjaxResponse(AjaxResponse.TYPE_JSON, response);
	            } else if (commandName.equals(COMMAND_GET_ESTIMATE_UPDATE_DETAILS)) {
	            	SPSMastersDataAjaxController controller = new SPSMastersDataAjaxController();
	                JSONObject response = controller.getMasterUpdateDetails(request);
	               
	                return new AjaxResponse(AjaxResponse.TYPE_JSON, response);
	            } else if (commandName.equals(COMMAND_DELETE_PEG_INFO_DETAILS)) {
	            	SPSMastersDataAjaxController controller = new SPSMastersDataAjaxController();
	                JSONObject response = controller.deleteMasterPegDetailLine(request);
	               
	                return new AjaxResponse(AjaxResponse.TYPE_JSON, response);
	            }  else if (commandName.equals(COMMAND_ADD_BULK_MASTER_DETAILS)) {
	            	SPSMastersDataAjaxController controller = new SPSMastersDataAjaxController();
	                JSONObject response = controller.addMasters(request);
	               
	                return new AjaxResponse(AjaxResponse.TYPE_JSON, response);
	            } else if (commandName.equals(COMMAND_CLEAR_MASTER_DETAILS)) {
	            	SPSMastersDataAjaxController controller = new SPSMastersDataAjaxController();
	                controller.clearForm(request);
	               
	                
	            }else if (commandName.equals(COMMAND_CLEAR_MASTER_ITEMDETAILS)) {
	            	SPSMastersDataAjaxController controller = new SPSMastersDataAjaxController();
	                controller.clearItems(request);
	               
	                
	            }else if (commandName.equals(COMMAND_LOAD_NO_CONTRACT_LETTER)) {
	            	SPSMastersDataAjaxController controller = new SPSMastersDataAjaxController();
	                
	             
	                JSONObject response = controller.loadEstimateNumForContractorLetter(request);
		               
	                return new AjaxResponse(AjaxResponse.TYPE_JSON, response);
	            }else if (commandName.equals(COMMAND_LOAD_CONTRACTORS_CONTRACT_LETTER)) {
	            	SPSMastersDataAjaxController controller = new SPSMastersDataAjaxController();
	                
	             
	                JSONObject response = controller.loadContractorsForContractorLetter(request);
		               
	                return new AjaxResponse(AjaxResponse.TYPE_JSON, response);
	            }else if (commandName.equals(COMMAND_LOAD_CONSTRUCTION_ESTIMATE_FOT_JOB_ALLOCATION_LETTER)) {
	            	SPSMastersDataAjaxController controller = new SPSMastersDataAjaxController();
	                
	             
	                JSONObject response = controller.loadConstructionEstimForJobAllocLetter(request);
		               
	                return new AjaxResponse(AjaxResponse.TYPE_JSON, response);
	            }else if (commandName.equals(COMMAND_LOAD_NO_JOB_ALLOCATION_LETTER)) {
	            	SPSMastersDataAjaxController controller = new SPSMastersDataAjaxController();
	                
	             
	                JSONObject response = controller.loadSEstimateNumbersForJobAllocLetter(request);
		               
	                return new AjaxResponse(AjaxResponse.TYPE_JSON, response);
	            }else if (commandName.equals(COMMAND_LOAD_CONSTRUCTION_REFERENS_FOR_CONTRACTOR_LETTER)) {
	            	SPSMastersDataAjaxController controller = new SPSMastersDataAjaxController();
	                
	             
	                JSONObject response = controller.loadConstructonRefreForContractorLetter(request);
		               
	                return new AjaxResponse(AjaxResponse.TYPE_JSON, response);
	            }else if (commandName.equals(COMMAND_LOAD_ESTIMATE_DETAILS_FOR_CONTRACTOR_LETTER)) {
	            	SPSMastersDataAjaxController controller = new SPSMastersDataAjaxController();
	                
	             
	                JSONObject response = controller.loadEstimateDetailsForContractorLetter(request);
		               
	                return new AjaxResponse(AjaxResponse.TYPE_JSON, response);
	            }else if (commandName.equals(COMMAND_LOAD_JOB_WORKSCOPE_CONTRACTOR_LETTER)) {
	            	SPSMastersDataAjaxController controller = new SPSMastersDataAjaxController();
	                
	             
	                JSONObject response = controller.loadWorkScopeForJob(request);
		               
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
