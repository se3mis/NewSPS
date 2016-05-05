package presentation.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;



public class WorkEstimateCommandHandler implements AjaxCommandHandler {
		
		 private static final String COMMAND_LOAD_SHOW_PEGINFO = "showPegInfo";
		 private static final String COMMAND_LOAD_PEGINFO = "loadPegInfo";
		 private static final String COMMAND_LOAD_MASTER_DETAILS = "viewMasterDetails";
		 private static final String COMMAND_PREPARE_ESTIMATE_DETAILS = "prepareEstimateDetails";
		
		 private static final String COMMAND_RESOURCE_DETAILS = "loadAdditionalResourceDetails";
		 private static final String COMMAND_LOAD_PEG_SCHEDULE_ESTIMATIONREFS = "loadPegScheduEstimationRefs";
		 private static final String COMMAND_LOAD_DEFAULD_DROP_DOWN_VALUES = "loadDefaultDropDownsValues";
		 private static final String COMMAND_LOAD_CATEGORY_CODES = "loadCategoryCodes";
		 private static final String COMMAND_LOAD_FOUND_IDS= "loadFundIds";
		 private static final String COMMAND_LOAD_OTHER_MATERIALS= "loadAvailableOtherMaterials";
		 private static final String COMMAND_LOAD_OTHER_MATERIALS_OTHER_ITEM= "loadAvailableOtherMaterialsWithoutAddedItem";
		 private static final String COMMAND_LOAD_OTHER_RESOURCES= "showOtherResources";
		 private static final String COMMAND_LOAD_NPL_MATERIALS= "showNPLMaterials";
		 private static final String COMMAND_CLEAR_PEGGING_SCHEDULE_FORM_DETAILS = "clearPeggingScheduleForm";
		 private static final String COMMAND_DELETE_PEGGING_INFO_DETAIL_LINE = "deletePegIngoDetailLine";
		 private static final String COMMAND_UPDATE_ESTIMATE_DETAILS ="getEstimatedUpdateDetails";
		 private static final String COMMAND_ESTIMATE_NO_DETAILS ="loadEstmationNumbers";
		 private static final String COMMAND_LOAD_ESTIMATE_DETAILS ="loadEstimationDetails";
		 private static final String COMMAND_GENERATE_WORK_ESTIMATE_NO = "generateWorkEstNo";
		 private static final String COMMAND_LOAD_JOB_NO = "loadJobNumbers";
		 private static final String COMMAND_LOAD_REVISE_DETAILS = "loadReviseDetails";
		 private static final String COMMAND_DELETE_REVISE_DETAILS = "deleteReviseDetails";
		 private static final String COMMAND_LOAD_DROP_DOWN_VALUES = "loadDropDownsValues";
		 private static final String COMMAND_LOAD_MATERIALS_FOR_MASTER ="loadAvailableOtherMaterialsForMaster";
		 private static final String COMMAND_LOAD_OTHER_RESOURCES1= "showOtherResourcesLink1";
		 private static final String COMMAND_LOAD_OTHER_RESOURCES2= "showOtherResourcesLink2";
		 private static final String COMMAND_LOAD_OTHER_RESOURCES3= "showOtherResourcesLink3";
		 private static final String COMMAND_LOAD_OTHER_RESOURCES4= "showOtherResourcesLink4";
		 private static final String COMMAND_LOAD_OTHER_RESOURCES5= "showOtherResourcesLink5";
		 private static final String COMMAND_LOAD_OTHER_RESOURCES6= "showOtherResourcesLink6";
		 private static final String COMMAND_LOAD_OTHER_RESOURCES7= "showOtherResourcesLink7";
		 private static final String COMMAND_LOAD_OTHER_RESOURCES8= "showOtherResourcesLink8";
		 private static final String COMMAND_LOAD_OTHER_RESOURCES9= "showOtherResourcesLink9";
		 private static final String COMMAND_LOAD_CONSTRUCTION_ESTIMATIONREFS = "loadConstructionEstimationRefs";
		 private static final String COMMAND_CHECK_ESTIMATE_AVAILABLE ="checkAvailableEstimateNo";
		 private static final String COMMAND_LOAD_FIRST_FIFTY_JOB_NUMBERS="loadFFJobNumbers";
		 private static final String COMMAND_GENERATE_FIRST_FIFTY_ESTIMATE_NUMBER="generateEstimateNoForFirstFifty";
		 private static final String COMMAND_FIND_STD_ESTIMATE_NO = "FindCommercialReference";
		 private static final String COMMAND_UPDATE_JOB_DETAILS = "getJobUpdateDetails";
		 private static final String COMMAND_LOAD_ESTIMATE_DETAILS_PRINT ="loadEstimationDetailsForPrint";
		 private static final String COMMAND_LOAD_PIV_DETAILS ="loadPIVDetails";
		 private static final String COMMAND_SEARCH_ESTIMATE_DETAILS ="searchEstimate";
		 private static final String COMMAND_SEARCH_JOB_DETAILS ="searchJobs";
		 private static final String COMMAND_UPDATE_ESTIMATE_DETAILS_FOR_PEG ="getEstimatedUpdateDetailsForPeg";
		 private static final String COMMAND_UPDATE_ESTIMATE_DETAILS_FOR_REBATE ="getEstimatedUpdateDetailsForRebate";
		 private static final String COMMAND_UPDATE_ESTIMATE_DETAILS_FOR_REUSABLE ="getEstimatedUpdateDetailsForReusable";
		 private static final String COMMAND_UPDATE_ESTIMATE_DETAILS_FOR_OFFCHARGE ="getEstimatedUpdateDetailsForOffCharge";
		 
		 private static final String COMMAND_ADD_ADDITIONAL_DETAILS_FOR_PEG ="addAdditinalPegInfo";
		 private static final String COMMAND_LOAD_REVISE_DETAILS_PRINT ="loadReviseDetailsForPrint";
		 private static final String COMMAND_GENERATE_NEXT_FILEREF_NO = "generateNextFileRefNo";
		 private static final String COMMAND_LOAD_MILESTONELIST= "generateNextFileRefNo";
		 private static final String COMMAND_LOAD_CONTRACTORNAME = "getContractorname";
		 
		 private static final String COMMAND_LOAD_CONTRACTORS = "loadContractors";
		 
		 private static final String COMMAND_LOAD_MILESTONES = "loadMilestones";
		 private static final String COMMAND_SAVE_PROGRESS = "addProgress";
		 private static final String COMMAND_DELETE_PROGRESS = "deleteLine";
		 
		 private static final String COMMAND_LOAD_PROGRESS_DETAILS = "searchProgressDetails";
		 private static final String COMMAND_UPDATE_PROGRESS = "updateProgress";
		 private static final String COMMAND_LOAD_PROGRESS_DETAILS_BY_PROJECT_NO = "searchProgressDetailsJobNumber";
		 
	    public String[] getValidCommands() {
	        return new String[]{
	        		
	        		COMMAND_LOAD_SHOW_PEGINFO,
	        		COMMAND_LOAD_PEGINFO,
	        		COMMAND_LOAD_MASTER_DETAILS,
	        		
	        		COMMAND_PREPARE_ESTIMATE_DETAILS,
	        		
	        		COMMAND_RESOURCE_DETAILS,
	        		COMMAND_LOAD_PEG_SCHEDULE_ESTIMATIONREFS,
	        		COMMAND_LOAD_CATEGORY_CODES,
	        		COMMAND_LOAD_FOUND_IDS,
	        		COMMAND_LOAD_DEFAULD_DROP_DOWN_VALUES,
	        		COMMAND_LOAD_OTHER_MATERIALS,
	        		COMMAND_LOAD_OTHER_RESOURCES,
	        		COMMAND_LOAD_NPL_MATERIALS,
	        		COMMAND_CLEAR_PEGGING_SCHEDULE_FORM_DETAILS,
	        		COMMAND_DELETE_PEGGING_INFO_DETAIL_LINE,
	        		COMMAND_UPDATE_ESTIMATE_DETAILS,
	        		COMMAND_ESTIMATE_NO_DETAILS,
	        		COMMAND_LOAD_ESTIMATE_DETAILS,
	        		COMMAND_GENERATE_WORK_ESTIMATE_NO,
	        		COMMAND_LOAD_JOB_NO,
	        		COMMAND_LOAD_REVISE_DETAILS,
	        		COMMAND_DELETE_REVISE_DETAILS,
	        		COMMAND_LOAD_DROP_DOWN_VALUES,
	        		COMMAND_LOAD_MATERIALS_FOR_MASTER,
	        		COMMAND_LOAD_OTHER_RESOURCES1,
	        		COMMAND_LOAD_OTHER_RESOURCES2,
	        		COMMAND_LOAD_OTHER_RESOURCES3,
	        		COMMAND_LOAD_OTHER_RESOURCES4,
	        		COMMAND_LOAD_OTHER_RESOURCES5,
	        		COMMAND_LOAD_OTHER_RESOURCES6,
	        		COMMAND_LOAD_OTHER_RESOURCES7,
	        		COMMAND_LOAD_OTHER_RESOURCES8,
	        		COMMAND_LOAD_OTHER_RESOURCES9,
	        		COMMAND_LOAD_CONSTRUCTION_ESTIMATIONREFS,
	        		COMMAND_CHECK_ESTIMATE_AVAILABLE,
	        		COMMAND_LOAD_FIRST_FIFTY_JOB_NUMBERS,
	        		COMMAND_GENERATE_FIRST_FIFTY_ESTIMATE_NUMBER,
	        		COMMAND_FIND_STD_ESTIMATE_NO,
	        		COMMAND_UPDATE_JOB_DETAILS,
	        		COMMAND_LOAD_ESTIMATE_DETAILS_PRINT,
	        		COMMAND_LOAD_PIV_DETAILS,
	        		COMMAND_SEARCH_ESTIMATE_DETAILS,
	        		COMMAND_SEARCH_JOB_DETAILS,
	        		COMMAND_UPDATE_ESTIMATE_DETAILS_FOR_PEG,
	        		COMMAND_UPDATE_ESTIMATE_DETAILS_FOR_REBATE,
	        		COMMAND_UPDATE_ESTIMATE_DETAILS_FOR_REUSABLE,
	        		COMMAND_UPDATE_ESTIMATE_DETAILS_FOR_OFFCHARGE,
	        		COMMAND_ADD_ADDITIONAL_DETAILS_FOR_PEG,
	        		COMMAND_LOAD_REVISE_DETAILS_PRINT,
	        		COMMAND_GENERATE_NEXT_FILEREF_NO,
	        		COMMAND_LOAD_CONTRACTORNAME,
	        		COMMAND_LOAD_CONTRACTORS,
	        		COMMAND_LOAD_MILESTONES,
	        		COMMAND_SAVE_PROGRESS,
	        		COMMAND_DELETE_PROGRESS,
	        		COMMAND_LOAD_PROGRESS_DETAILS,
	        		COMMAND_UPDATE_PROGRESS,
	        		COMMAND_LOAD_PROGRESS_DETAILS_BY_PROJECT_NO,
	        		COMMAND_LOAD_OTHER_MATERIALS_OTHER_ITEM
	        };
	    }

	    public AjaxResponse executeCommand(HttpServletRequest request, String commandName, Map parameters) {
	        try {
	            if (commandName.equals(COMMAND_LOAD_SHOW_PEGINFO)) {
	            	WorkEstimateAjaxController controller = new WorkEstimateAjaxController();
	            	JSONObject response = controller.showPegInfo(request);
	            	return new AjaxResponse(AjaxResponse.TYPE_JSON, response);
	            } else if (commandName.equals(COMMAND_LOAD_PEGINFO)) {
	            	WorkEstimateAjaxController controller = new WorkEstimateAjaxController();
	            	AjaxResponse response = controller.loadPegInfo(request);
	               
	                return response;
	            } else if (commandName.equals(COMMAND_LOAD_MASTER_DETAILS)) {
	            	WorkEstimateAjaxController controller = new WorkEstimateAjaxController();
	            	JSONObject response = controller.viewMasterDetails(request);
	            	return new AjaxResponse(AjaxResponse.TYPE_JSON, response);
	            }  else if (commandName.equals(COMMAND_PREPARE_ESTIMATE_DETAILS)) {
	            	WorkEstimateAjaxController controller = new WorkEstimateAjaxController();
	            	JSONObject response = controller.prepareEstimateDetails(request);
	            	return new AjaxResponse(AjaxResponse.TYPE_JSON, response);
	            }else if (commandName.equals(COMMAND_RESOURCE_DETAILS)) {
	            	WorkEstimateAjaxController controller = new WorkEstimateAjaxController();
	                JSONObject response = controller.loadAdditionalResourceDetails(request);
	               
	                return new AjaxResponse(AjaxResponse.TYPE_JSON, response);
	            }else if (commandName.equals(COMMAND_LOAD_PEG_SCHEDULE_ESTIMATIONREFS)) {
	            	WorkEstimateAjaxController controller = new WorkEstimateAjaxController();
	            	JSONObject response = controller.loadPegScheduleEstmationRefNumbers(request);
	            	return new AjaxResponse(AjaxResponse.TYPE_JSON, response);
	            }else if (commandName.equals(COMMAND_LOAD_DEFAULD_DROP_DOWN_VALUES)) {
	            	WorkEstimateAjaxController controller = new WorkEstimateAjaxController();
	            	JSONObject response = controller.loadDefaultDropDownsValues(request);
	            	return new AjaxResponse(AjaxResponse.TYPE_JSON, response);
	            }else if (commandName.equals(COMMAND_LOAD_CATEGORY_CODES)) {
	            	WorkEstimateAjaxController controller = new WorkEstimateAjaxController();
	            	JSONObject response = controller.loadCategoryCodes(request);
	            	return new AjaxResponse(AjaxResponse.TYPE_JSON, response);
	            }else if (commandName.equals(COMMAND_LOAD_FOUND_IDS)) {
	            	WorkEstimateAjaxController controller = new WorkEstimateAjaxController();
	            	JSONObject response = controller.loadFundIds(request);
	            	return new AjaxResponse(AjaxResponse.TYPE_JSON, response);
	            }else if (commandName.equals(COMMAND_LOAD_OTHER_MATERIALS)) {
	            	WorkEstimateAjaxController controller = new WorkEstimateAjaxController();
	            	JSONObject response = controller.showAvailableOtherMaterials(request);
	            	return new AjaxResponse(AjaxResponse.TYPE_JSON, response);
	            }else if (commandName.equals(COMMAND_LOAD_OTHER_RESOURCES)) {
	            	WorkEstimateAjaxController controller = new WorkEstimateAjaxController();
	            	JSONObject response = controller.showOtherResources(request);
	            	return new AjaxResponse(AjaxResponse.TYPE_JSON, response);
	            }
	            else if (commandName.equals(COMMAND_LOAD_NPL_MATERIALS)) {
	            	WorkEstimateAjaxController controller = new WorkEstimateAjaxController();
	            	JSONObject response = controller.showNPLMaterials(request);
	            	return new AjaxResponse(AjaxResponse.TYPE_JSON, response);
	            }
	            else if (commandName.equals(COMMAND_CLEAR_PEGGING_SCHEDULE_FORM_DETAILS)) {
	            	WorkEstimateAjaxController controller = new WorkEstimateAjaxController();
	            	controller.clearForm(request);
	            	
	            } else if (commandName.equals(COMMAND_DELETE_PEGGING_INFO_DETAIL_LINE)) {
	            	WorkEstimateAjaxController controller = new WorkEstimateAjaxController();
	            	controller.deletePegIngoDetailLine(request);
	            	
	            }else if (commandName.equals(COMMAND_UPDATE_ESTIMATE_DETAILS)) {
	            	WorkEstimateAjaxController controller = new WorkEstimateAjaxController();
	            	JSONObject response = controller.getEstimatedUpdateDetails(request);
	            	return new AjaxResponse(AjaxResponse.TYPE_JSON, response);
	            }else if (commandName.equals(COMMAND_ESTIMATE_NO_DETAILS)) {
	            	WorkEstimateAjaxController controller = new WorkEstimateAjaxController();
	            	JSONObject response = controller.loadEstmationNumbers(request);
	            	return new AjaxResponse(AjaxResponse.TYPE_JSON, response);
	            }else if (commandName.equals(COMMAND_LOAD_ESTIMATE_DETAILS)) {
	            	WorkEstimateAjaxController controller = new WorkEstimateAjaxController();
	            	JSONObject response = controller.loadEstimationDetails(request);
	            	return new AjaxResponse(AjaxResponse.TYPE_JSON, response);
	            }else if (commandName.equals(COMMAND_LOAD_ESTIMATE_DETAILS_PRINT)) {
	            	WorkEstimateAjaxController controller = new WorkEstimateAjaxController();
	            	JSONObject response = controller.loadEstimationDetailsForPrint(request);
	            	return new AjaxResponse(AjaxResponse.TYPE_JSON, response);
	            }else if (commandName.equals(COMMAND_GENERATE_WORK_ESTIMATE_NO)) {
	            	WorkEstimateAjaxController controller = new WorkEstimateAjaxController();
	            	JSONObject response = controller.generateWorkEstimateNo(request);
	            	return new AjaxResponse(AjaxResponse.TYPE_JSON, response);
	            }else if (commandName.equals(COMMAND_LOAD_JOB_NO)) {
	            	WorkEstimateAjaxController controller = new WorkEstimateAjaxController();
	            	JSONObject response = controller.loadJobNumbers(request);
	            	return new AjaxResponse(AjaxResponse.TYPE_JSON, response);
	            }else if (commandName.equals(COMMAND_LOAD_REVISE_DETAILS)) {
	            	WorkEstimateAjaxController controller = new WorkEstimateAjaxController();
	            	JSONObject response = controller.loadReviseDetails(request);
	            	return new AjaxResponse(AjaxResponse.TYPE_JSON, response);
	            }else if (commandName.equals(COMMAND_DELETE_REVISE_DETAILS)) {
	            	WorkEstimateAjaxController controller = new WorkEstimateAjaxController();
	            	JSONObject response = controller.deleteReviseDetailLine(request);
	            	return new AjaxResponse(AjaxResponse.TYPE_JSON, response);
	            }else if (commandName.equals(COMMAND_LOAD_DROP_DOWN_VALUES)) {
	            	WorkEstimateAjaxController controller = new WorkEstimateAjaxController();
	            	JSONObject response = controller.loadDropDownsValues(request);
	            	return new AjaxResponse(AjaxResponse.TYPE_JSON, response);
	            }else if (commandName.equals(COMMAND_LOAD_MATERIALS_FOR_MASTER)) {
	            	WorkEstimateAjaxController controller = new WorkEstimateAjaxController();
	            	controller.loadAvailableOtherMaterialsForMaster(request);
	            	//return new AjaxResponse(AjaxResponse.TYPE_JSON, response);
	            }else if (commandName.equals(COMMAND_LOAD_OTHER_MATERIALS_OTHER_ITEM)) {
	            	WorkEstimateAjaxController controller = new WorkEstimateAjaxController();
	            	controller.loadAvailableOtherMaterialsWithoutAddedItem(request);
	            	//return new AjaxResponse(AjaxResponse.TYPE_JSON, response);
	            }
	            
	            else if (commandName.equals(COMMAND_LOAD_MATERIALS_FOR_MASTER)) {
	            	WorkEstimateAjaxController controller = new WorkEstimateAjaxController();
	            	controller.loadAvailableOtherMaterialsForMaster(request);
	            	//return new AjaxResponse(AjaxResponse.TYPE_JSON, response);
	            }
	            else if (commandName.equals(COMMAND_LOAD_OTHER_RESOURCES1)) {
	            	WorkEstimateAjaxController controller = new WorkEstimateAjaxController();
	            	JSONObject response = controller.showAvailableOtherMaterialsLink1(request);
	            	return new AjaxResponse(AjaxResponse.TYPE_JSON, response);
	            }else if (commandName.equals(COMMAND_LOAD_OTHER_RESOURCES2)) {
	            	WorkEstimateAjaxController controller = new WorkEstimateAjaxController();
	            	JSONObject response = controller.showAvailableOtherMaterialsLink2(request);
	            	return new AjaxResponse(AjaxResponse.TYPE_JSON, response);
	            }
	            else if (commandName.equals(COMMAND_LOAD_OTHER_RESOURCES3)) {
	            	WorkEstimateAjaxController controller = new WorkEstimateAjaxController();
	            	JSONObject response = controller.showAvailableOtherMaterialsLink3(request);
	            	return new AjaxResponse(AjaxResponse.TYPE_JSON, response);
	            }
	            else if (commandName.equals(COMMAND_LOAD_OTHER_RESOURCES4)) {
	            	WorkEstimateAjaxController controller = new WorkEstimateAjaxController();
	            	JSONObject response = controller.showAvailableOtherMaterialsLink4(request);
	            	return new AjaxResponse(AjaxResponse.TYPE_JSON, response);
	            }
	            else if (commandName.equals(COMMAND_LOAD_OTHER_RESOURCES5)) {
	            	WorkEstimateAjaxController controller = new WorkEstimateAjaxController();
	            	JSONObject response = controller.showAvailableOtherMaterialsLink5(request);
	            	return new AjaxResponse(AjaxResponse.TYPE_JSON, response);
	            }
	            else if (commandName.equals(COMMAND_LOAD_OTHER_RESOURCES6)) {
	            	WorkEstimateAjaxController controller = new WorkEstimateAjaxController();
	            	JSONObject response = controller.showAvailableOtherMaterialsLink6(request);
	            	return new AjaxResponse(AjaxResponse.TYPE_JSON, response);
	            }else if (commandName.equals(COMMAND_LOAD_OTHER_RESOURCES7)) {
	            	WorkEstimateAjaxController controller = new WorkEstimateAjaxController();
	            	JSONObject response = controller.showAvailableOtherMaterialsLink7(request);
	            	return new AjaxResponse(AjaxResponse.TYPE_JSON, response);
	            }
	            else if (commandName.equals(COMMAND_LOAD_OTHER_RESOURCES8)) {
	            	WorkEstimateAjaxController controller = new WorkEstimateAjaxController();
	            	JSONObject response = controller.showAvailableOtherMaterialsLink8(request);
	            	return new AjaxResponse(AjaxResponse.TYPE_JSON, response);
	            }else if (commandName.equals(COMMAND_LOAD_OTHER_RESOURCES9)) {
	            	WorkEstimateAjaxController controller = new WorkEstimateAjaxController();
	            	JSONObject response = controller.showAvailableOtherMaterialsLink9(request);
	            	return new AjaxResponse(AjaxResponse.TYPE_JSON, response);
	            }
	            else if (commandName.equals(COMMAND_LOAD_CONSTRUCTION_ESTIMATIONREFS)) {
	            	WorkEstimateAjaxController controller = new WorkEstimateAjaxController();
	            	JSONObject response = controller.loadConstructionEstmationRefNumbers(request);
	            	return new AjaxResponse(AjaxResponse.TYPE_JSON, response);
	            }else if (commandName.equals(COMMAND_CHECK_ESTIMATE_AVAILABLE)) {
	            	WorkEstimateAjaxController controller = new WorkEstimateAjaxController();
	            	JSONObject response = controller.checkWorkEstimateNoExist(request);
	            	return new AjaxResponse(AjaxResponse.TYPE_JSON, response);
	            }else if (commandName.equals(COMMAND_LOAD_FIRST_FIFTY_JOB_NUMBERS)) {
	            	WorkEstimateAjaxController controller = new WorkEstimateAjaxController();
	            	JSONObject response = controller.loadFFJobNumbers(request);
	            	return new AjaxResponse(AjaxResponse.TYPE_JSON, response);
	            }else if (commandName.equals(COMMAND_LOAD_FIRST_FIFTY_JOB_NUMBERS)) {
	            	WorkEstimateAjaxController controller = new WorkEstimateAjaxController();
	            	JSONObject response = controller.loadFFJobNumbers(request);
	            	return new AjaxResponse(AjaxResponse.TYPE_JSON, response);
	            }else if (commandName.equals(COMMAND_GENERATE_FIRST_FIFTY_ESTIMATE_NUMBER)) {
	            	WorkEstimateAjaxController controller = new WorkEstimateAjaxController();
	            	JSONObject response = controller.generateEstimateNoForFirstFifty(request);
	            	return new AjaxResponse(AjaxResponse.TYPE_JSON, response);
	            }else if (commandName.equals(COMMAND_FIND_STD_ESTIMATE_NO)) {
	            	WorkEstimateAjaxController controller = new WorkEstimateAjaxController();
	            	JSONObject response = controller.FindCommercialRef(request);
	            	return new AjaxResponse(AjaxResponse.TYPE_JSON, response);
	            }else if (commandName.equals(COMMAND_UPDATE_JOB_DETAILS)) {
	            	WorkEstimateAjaxController controller = new WorkEstimateAjaxController();
	            	JSONObject response = controller.getJobUpdateDetails(request);
	            	return new AjaxResponse(AjaxResponse.TYPE_JSON, response);
	            }else if (commandName.equals(COMMAND_LOAD_PIV_DETAILS)) {
	            	WorkEstimateAjaxController controller = new WorkEstimateAjaxController();
	            	JSONObject response = controller.loadPIVDetails(request);
	            	return new AjaxResponse(AjaxResponse.TYPE_JSON, response);
	            }else if (commandName.equals(COMMAND_SEARCH_ESTIMATE_DETAILS)) {
	            	WorkEstimateAjaxController controller = new WorkEstimateAjaxController();
	            	JSONObject response = controller.searchEstimationDetails(request);
	            	return new AjaxResponse(AjaxResponse.TYPE_JSON, response);
	            }else if (commandName.equals(COMMAND_SEARCH_JOB_DETAILS)) {
	            	WorkEstimateAjaxController controller = new WorkEstimateAjaxController();
	            	JSONObject response = controller.searchJobDetails(request);
	            	return new AjaxResponse(AjaxResponse.TYPE_JSON, response);
	            }else if (commandName.equals(COMMAND_UPDATE_ESTIMATE_DETAILS_FOR_PEG)) {
	            	WorkEstimateAjaxController controller = new WorkEstimateAjaxController();
	            	JSONObject response = controller.getEstimatedUpdateDetailsForPeg(request);
	            	return new AjaxResponse(AjaxResponse.TYPE_JSON, response);
	            }else if (commandName.equals(COMMAND_ADD_ADDITIONAL_DETAILS_FOR_PEG)) {
	            	WorkEstimateAjaxController controller = new WorkEstimateAjaxController();
	            	JSONObject response = controller.addAdditinalPegInfo(request);
	            	return new AjaxResponse(AjaxResponse.TYPE_JSON, response);
	            }else if (commandName.equals(COMMAND_UPDATE_ESTIMATE_DETAILS_FOR_REUSABLE)) {
	            	WorkEstimateAjaxController controller = new WorkEstimateAjaxController();
	            	JSONObject response = controller.getEstimatedUpdateDetailsForReusable(request);
	            	return new AjaxResponse(AjaxResponse.TYPE_JSON, response);
	            }else if (commandName.equals(COMMAND_UPDATE_ESTIMATE_DETAILS_FOR_OFFCHARGE)) {
	            	WorkEstimateAjaxController controller = new WorkEstimateAjaxController();
	            	JSONObject response = controller.getEstimatedUpdateDetailsForOffCharge(request);
	            	return new AjaxResponse(AjaxResponse.TYPE_JSON, response);
	            }else if (commandName.equals(COMMAND_UPDATE_ESTIMATE_DETAILS_FOR_REBATE)) {
	            	WorkEstimateAjaxController controller = new WorkEstimateAjaxController();
	            	JSONObject response = controller.getEstimatedUpdateDetailsForRebate(request);
	            	return new AjaxResponse(AjaxResponse.TYPE_JSON, response);
	            }else if (commandName.equals(COMMAND_LOAD_REVISE_DETAILS_PRINT)) {
	            	WorkEstimateAjaxController controller = new WorkEstimateAjaxController();
	            	JSONObject response = controller.loadReviseDetailsForPrint(request);
	            	return new AjaxResponse(AjaxResponse.TYPE_JSON, response);
	            }else if (commandName.equals(COMMAND_GENERATE_NEXT_FILEREF_NO)) {
	            	WorkEstimateAjaxController controller = new WorkEstimateAjaxController();
	            	JSONObject response = controller.generateNextFileRefNo(request);
	            	return new AjaxResponse(AjaxResponse.TYPE_JSON, response);
	            }else if (commandName.equals(COMMAND_LOAD_CONTRACTORNAME)) {
	            	WorkEstimateAjaxController controller = new WorkEstimateAjaxController();
	            	JSONObject response = controller.getContractorname(request);
	            	return new AjaxResponse(AjaxResponse.TYPE_JSON, response);
	            }else if (commandName.equals(COMMAND_LOAD_CONTRACTORS)) {
	            	WorkEstimateAjaxController controller = new WorkEstimateAjaxController();
	            	JSONObject response = controller.loadContractors(request);
	            	return new AjaxResponse(AjaxResponse.TYPE_JSON, response);
	            }else if (commandName.equals(COMMAND_LOAD_MILESTONES)) {
	            	WorkEstimateAjaxController controller = new WorkEstimateAjaxController();
	            	JSONObject response = controller.loadMilestones(request);
	            	return new AjaxResponse(AjaxResponse.TYPE_JSON, response);
	            }else if (commandName.equals(COMMAND_SAVE_PROGRESS)) {
	            	WorkEstimateAjaxController controller = new WorkEstimateAjaxController();
	            	JSONObject response = controller.addProgress(request);
	            	return new AjaxResponse(AjaxResponse.TYPE_JSON, response);
	            }else if (commandName.equals(COMMAND_DELETE_PROGRESS)) {
	            	WorkEstimateAjaxController controller = new WorkEstimateAjaxController();
	            	JSONObject response = controller.deleteLine(request);
	            	return new AjaxResponse(AjaxResponse.TYPE_JSON, response);
	            }else if (commandName.equals(COMMAND_LOAD_PROGRESS_DETAILS)) {
	            	WorkEstimateAjaxController controller = new WorkEstimateAjaxController();
	            	JSONObject response = controller.searchProgressDetails(request);
	            	return new AjaxResponse(AjaxResponse.TYPE_JSON, response);
	            }else if (commandName.equals(COMMAND_UPDATE_PROGRESS)) {
	            	WorkEstimateAjaxController controller = new WorkEstimateAjaxController();
	            	JSONObject response = controller.updateProgress(request);
	            	return new AjaxResponse(AjaxResponse.TYPE_JSON, response);
	            }else if (commandName.equals(COMMAND_LOAD_PROGRESS_DETAILS_BY_PROJECT_NO)) {
	            	WorkEstimateAjaxController controller = new WorkEstimateAjaxController();
	            	JSONObject response = controller.searchProgressDetailsJobNumber(request);
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
