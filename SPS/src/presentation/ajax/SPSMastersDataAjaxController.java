package presentation.ajax;

import inventory.model.Inmatm;
import inventory.service.InmatmEjb;
import inventory.service.InwrhmtmEjb;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import job.model.JobInfo;
import job.model.Pcestdmt;
import job.model.Pcesthmt;
import job.model.Pcrstypm;
import job.model.Spestcnd;
import job.model.Spestcnt;
import job.service.PcesthmtEjb;
import job.service.SpestcndEjb;
import job.service.SpestcntEjb;

import masters.model.BankBranch;
import masters.service.BankEjb;

import org.apache.struts2.ServletActionContext;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import util.common.ApplicationStatus;
import util.common.AppointmentStatus;
import util.common.Constants;
import util.common.Format;
import util.common.PivPrefixType;


import application.model.Applicant;
import application.model.Application;
import application.model.WiringLandDetail;
import application.service.ApplicantEjb;
import application.service.ApplicationEjb;
import application.service.WiringLandDetailEjb;

import costcenter.model.Gldeptm;
import costcenter.service.GldeptmEjb;

import estimate.dto.DetailEstimateDTO;
import estimate.dto.EstimateDetails;
import estimate.dto.Norm;
import estimate.dto.StandardEstimateDetail;
import estimate.model.EstimateReference;
import estimate.model.FundSource;
import estimate.model.Pcfunddm;
import estimate.model.Pcrsgrpm;
import estimate.model.Pegschdmt;
import estimate.model.SpPegInfo;
import estimate.model.SpPegInfoPK;
import estimate.model.SpPointdmt;
import estimate.model.SpPointdmtPK;
import estimate.model.SpestedyCons;
import estimate.model.Spnorms;
import estimate.model.Spstdestdmt;
import estimate.model.SpstdestdmtPK;
import estimate.model.Spstdesthmt;
import estimate.model.SpstdesthmtPK;
import estimate.service.EstimateEjb;
import estimate.service.EstimateReferenceEjb;
import estimate.service.SpPegInfoEjb;
import estimate.service.SpPointDmtEjb;
import estimate.service.SpestedyConEjb;
import estimate.service.SpestedyEjb;

public class SPSMastersDataAjaxController {
	
	Map<String,Norm> norms = null;
	private Map<String,Norm> normsMap1 = new HashMap<String,Norm>();
	
	Map<String, Norm> normsMap = new TreeMap<String, Norm>();

	
	private Format format;
	
	Map<String,SpPointdmt> resourceMap = new HashMap<String,SpPointdmt>();
	Map<String, DetailEstimateDTO> availableOtherMaterialsMap = new TreeMap<String, DetailEstimateDTO>();
	Map<String, DetailEstimateDTO> availableOtherResourcesMap = new TreeMap<String, DetailEstimateDTO>();
	Map<String, DetailEstimateDTO> availableNPLMaterialsMap = new TreeMap<String, DetailEstimateDTO>();
	
	StandardEstimateDetail standardEstimateDetail = new StandardEstimateDetail();
	
	Map<String, DetailEstimateDTO> availableOtherMaterialsMap1 = new TreeMap<String, DetailEstimateDTO>();
	Map<String, DetailEstimateDTO> availableOtherMaterialsMap2 = new TreeMap<String, DetailEstimateDTO>();
	Map<String, DetailEstimateDTO> availableOtherMaterialsMap3 = new TreeMap<String, DetailEstimateDTO>();
	Map<String, DetailEstimateDTO> availableOtherMaterialsMap4 = new TreeMap<String, DetailEstimateDTO>();
	Map<String, DetailEstimateDTO> availableOtherMaterialsMap5 = new TreeMap<String, DetailEstimateDTO>();
	Map<String, DetailEstimateDTO> availableOtherMaterialsMap6 = new TreeMap<String, DetailEstimateDTO>();
	Map<String, DetailEstimateDTO> availableOtherMaterialsMap7 = new TreeMap<String, DetailEstimateDTO>();
	Map<String, DetailEstimateDTO> availableOtherMaterialsMap8 = new TreeMap<String, DetailEstimateDTO>();
	NumberFormat nf = NumberFormat.getInstance();
	public void clearForm(HttpServletRequest request){
		resourceMap = (Map<String, SpPointdmt>) request.getSession().getAttribute("MeterialsList");
		
		availableOtherMaterialsMap = (Map<String,DetailEstimateDTO> )request.getSession().getAttribute("availableOtherMaterialsMap");
		
		
		availableNPLMaterialsMap = (Map<String,DetailEstimateDTO> )request.getSession().getAttribute("availableOtherMaterialsMap");
		
	
		
		availableOtherResourcesMap = (Map<String,DetailEstimateDTO> )request.getSession().getAttribute("availableOtherResourcesMap");
		availableOtherMaterialsMap1 = (Map<String,DetailEstimateDTO> )request.getSession().getAttribute("availableOtherMaterialsMap1");
		
		availableOtherMaterialsMap2 = (Map<String,DetailEstimateDTO> )request.getSession().getAttribute("availableOtherMaterialsMap2");
		
		availableOtherMaterialsMap3 = (Map<String,DetailEstimateDTO> )request.getSession().getAttribute("availableOtherMaterialsMap3");
		
		availableOtherMaterialsMap4 = (Map<String,DetailEstimateDTO> )request.getSession().getAttribute("availableOtherMaterialsMap4");
		
		availableOtherMaterialsMap5 = (Map<String,DetailEstimateDTO> )request.getSession().getAttribute("availableOtherMaterialsMap5");
		
		availableOtherMaterialsMap6 = (Map<String,DetailEstimateDTO> )request.getSession().getAttribute("availableOtherMaterialsMap6");
		
		availableOtherMaterialsMap7 = (Map<String,DetailEstimateDTO> )request.getSession().getAttribute("availableOtherMaterialsMap7");
		
		availableOtherMaterialsMap8 = (Map<String,DetailEstimateDTO> )request.getSession().getAttribute("availableOtherMaterialsMap8");
		
		
		if(availableOtherMaterialsMap != null){
			
			availableOtherMaterialsMap.clear();
		}

		if(availableOtherMaterialsMap1 != null){
			
			availableOtherMaterialsMap1.clear();
		}

		if(availableOtherMaterialsMap2 != null){
			
			availableOtherMaterialsMap3.clear();
		}

		if(availableOtherMaterialsMap3 != null){
			
			availableOtherMaterialsMap3.clear();
		}

		if(availableOtherMaterialsMap4 != null){
			
			availableOtherMaterialsMap4.clear();
		}

		if(availableOtherMaterialsMap5 != null){
			
			availableOtherMaterialsMap5.clear();
		}

		if(availableOtherMaterialsMap6 != null){
			
			availableOtherMaterialsMap6.clear();
		}
		if(availableOtherMaterialsMap7 != null){
					
			availableOtherMaterialsMap7.clear();
		}
		if(availableOtherMaterialsMap8 != null){
			
			availableOtherMaterialsMap8.clear();
		}
				/*selectedNorms =  (Map<String, Norm>) request.getSession().getAttribute("selectedNorms");
		deletedNorms =  (Map<String, Norm>) request.getSession().getAttribute("deletedNorms");
		alreadyAddedDetails =  (Map<String, Norm>) request.getSession().getAttribute("alreadyAddedDetails");
		newlyAddedDetails =  (Map<String, Norm>) request.getSession().getAttribute("newlyAddedDetails");
		updatedDetails =  (Map<String, Norm>) request.getSession().getAttribute("updatedDetails");
		standardEstimateDetail = (StandardEstimateDetail) request.getSession().getAttribute("standardEstimateDetail");
		if(normsMap != null){
			normsMap.clear();
		}
		if(selectedNorms != null){
			selectedNorms.clear();
		}
		if(deletedNorms != null){
			deletedNorms.clear();
		}
		if(alreadyAddedDetails != null){
			alreadyAddedDetails.clear();
		}
		if(newlyAddedDetails != null){
			newlyAddedDetails.clear();
		}
		if(updatedDetails != null){
			updatedDetails.clear();
		}*/
		if(resourceMap != null){
			resourceMap = null;
		}
		if(availableOtherMaterialsMap != null){
					
			availableOtherMaterialsMap.clear();
		}
		
		if(availableNPLMaterialsMap != null){ 
			
			availableNPLMaterialsMap.clear();
		}
		
		if(availableOtherResourcesMap != null){
			
			availableOtherResourcesMap.clear();
			
		}

		request.getSession().setAttribute("MeterialsList",resourceMap);
		request.getSession().setAttribute("detailEstimateDetailsMap",availableOtherMaterialsMap);
	
		request.getSession().setAttribute("availableNPLMaterialsMap",availableNPLMaterialsMap);
	
		request.getSession().setAttribute("availableOtherResourcesMap",availableOtherResourcesMap);
		request.getSession().setAttribute("detailEstimateDetailsMap1",availableOtherMaterialsMap1);
		
		request.getSession().setAttribute("detailEstimateDetailsMap2",availableOtherMaterialsMap2);
		request.getSession().setAttribute("detailEstimateDetailsMap3",availableOtherMaterialsMap3);
		
		request.getSession().setAttribute("detailEstimateDetailsMap4",availableOtherMaterialsMap4);
		request.getSession().setAttribute("detailEstimateDetailsMap5",availableOtherMaterialsMap5);
		request.getSession().setAttribute("detailEstimateDetailsMap6",availableOtherMaterialsMap6);
		request.getSession().setAttribute("detailEstimateDetailsMap7",availableOtherMaterialsMap7);
		request.getSession().setAttribute("detailEstimateDetailsMap8",availableOtherMaterialsMap8);
		//request.getSession().setAttribute("detailEstimateDetailsMap9",availableOtherMaterialsMap9);
		

		/*request.getSession().setAttribute("selectedNorms",selectedNorms);
		request.getSession().setAttribute("deletedNorms",deletedNorms);
		request.getSession().setAttribute("alreadyAddedDetails",alreadyAddedDetails);
		request.getSession().setAttribute("newlyAddedDetails",newlyAddedDetails);
		request.getSession().setAttribute("updatedDetails",updatedDetails);*/
		//request.getSession().setAttribute("standardEstimateDetail",standardEstimateDetail);
	}
	public void clearItems(HttpServletRequest request){
		resourceMap = (Map<String, SpPointdmt>) request.getSession().getAttribute("MeterialsList");

		if(resourceMap != null){
			resourceMap.clear();
		}
		

		request.getSession().setAttribute("MeterialsList",resourceMap);
		
	}
	public Format getFormat() {
		return format;
	}

	public void setFormat(Format format) {
		this.format = format;
	}
	public String getYear() {
		String DATE_FORMAT = "yy";
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
		Calendar calendar = Calendar.getInstance(); // today
		//calendar.getTime().
		return sdf.format(calendar.getTime());

	}

	public JSONObject loadNorms(HttpServletRequest request, Map parameters){
		JSONObject packet =new JSONObject();
		JSONArray normsArray = new JSONArray();
		try {
			String sessionKey= (String) request.getSession().getAttribute("region");
			
			EstimateEjb estimateEjb = new EstimateEjb(sessionKey);
			
			List<Spnorms> list = null;
		
			list = estimateEjb.getAvailableNorms();
			normsMap.clear();
			if(list != null && list.size() > 0){
				for(int i=0; i<=list.size()-1; i++){
					
					packet.put("lineSecTypeId", list.get(i).getId().getLineSectionTypeId());
					packet.put("uom", list.get(i).getUom());
					packet.put("name", list.get(i).getDescription());
					packet.put("standardCost", list.get(i).getStandardCost());
					
					normsArray.put(packet);
					  
				}
			}
			 packet.put("norms", normsArray);

		    return packet;
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return packet;

	}
	
	public JSONObject addPegItem(HttpServletRequest request, Map parameters){
		JSONObject packet =new JSONObject();
		JSONArray normsArray = new JSONArray();
		String costCenterNo=(String) request.getSession().getAttribute("costCenterNo");
		
		try {
			
			String selectedPegId = request.getParameter("selectedPegId");
			String masterId = null;
			if(costCenterNo != null && (costCenterNo.equalsIgnoreCase("430.20") || costCenterNo.equalsIgnoreCase("430.25") || costCenterNo.equalsIgnoreCase("430.30") || costCenterNo.equalsIgnoreCase("430.35") || costCenterNo.equalsIgnoreCase("430.10"))){
				masterId = "4";
			}else if(costCenterNo != null &&(costCenterNo.equalsIgnoreCase("510.20") || costCenterNo.equalsIgnoreCase("510.30") || costCenterNo.equalsIgnoreCase("550.20") || costCenterNo.equalsIgnoreCase("550.30"))){
				masterId = "2";
			}else if(costCenterNo != null &&  (costCenterNo.equalsIgnoreCase("530.80") || costCenterNo.equalsIgnoreCase("530.20") || costCenterNo.equalsIgnoreCase("530.30"))){
				masterId = "1";
			}
			
			String selectedPegText = request.getParameter("selectedPegText");
			String itemName = request.getParameter("itemName");
			
			String sessionKey= (String) request.getSession().getAttribute("region");
			
			EstimateEjb estimateEjb = new EstimateEjb(sessionKey);
			
			String id = estimateEjb.getNextPegItemId(selectedPegId,masterId, sessionKey);
			SpPegInfoEjb spPegInfoEjb = new SpPegInfoEjb();
			SpPegInfo info = new SpPegInfo();
			SpPegInfoPK pk = new SpPegInfoPK();
			pk.setParent_id(selectedPegId.trim());
			pk.setPegItemId(id);
			pk.setMasterId(masterId);
			info.setId(pk);
			System.out.println("peggg : " + selectedPegId + " " + masterId + "  " + id);
			info.setDescription(itemName);
			info.setName(itemName);
			
			spPegInfoEjb.createSpPegInfo(info, sessionKey);
			try {
				packet.put("message",true);
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				packet.put("statusMessage", "Successfully Added");
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			/*String sessionKey= (String) request.getSession().getAttribute("region");
			
			EstimateEjb estimateEjb = new EstimateEjb(sessionKey);
			
			List<Spnorms> list = null;
		
			list = estimateEjb.getAvailableNorms();
			normsMap.clear();
		
			for(int i=0; i<=list.size()-1; i++){
				
				packet.put("lineSecTypeId", list.get(i).getId().getLineSectionTypeId());
				packet.put("uom", list.get(i).getUom());
				packet.put("name", list.get(i).getDescription());
				packet.put("standardCost", list.get(i).getStandardCost());
				
				normsArray.put(packet);
				  
			}
			 packet.put("norms", normsArray);

		    return packet;
			*/
		}catch (Exception e) {
			try {
				packet.put("message",true);
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				packet.put("statusMessage", "Error occured");
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		return packet;

	}
	public JSONObject modifyPegItem(HttpServletRequest request, Map parameters){
		JSONObject packet =new JSONObject();
		JSONArray normsArray = new JSONArray();
		try {
			String selectedPegId = request.getParameter("selectedPegId");
			String selectedPegText = request.getParameter("selectedPegText");
			String itemName = request.getParameter("itemName");
			String costCenterNo=(String) request.getSession().getAttribute("costCenterNo");
			String masterId = null;
			if(costCenterNo != null && (costCenterNo.equalsIgnoreCase("430.20") || costCenterNo.equalsIgnoreCase("430.25") || costCenterNo.equalsIgnoreCase("430.30") || costCenterNo.equalsIgnoreCase("430.35") || costCenterNo.equalsIgnoreCase("430.10"))){
				masterId = "4";
			}else if(costCenterNo != null &&(costCenterNo.equalsIgnoreCase("510.20") || costCenterNo.equalsIgnoreCase("510.30") || costCenterNo.equalsIgnoreCase("550.20") || costCenterNo.equalsIgnoreCase("550.30"))){
				masterId = "2";
			}else if(costCenterNo != null && ( costCenterNo.equalsIgnoreCase("530.80") || costCenterNo.equalsIgnoreCase("530.20") || costCenterNo.equalsIgnoreCase("530.30"))){
				masterId = "1";
			}
			
			String sessionKey= (String) request.getSession().getAttribute("region");
			
			
			EstimateEjb estimateEjb = new EstimateEjb(sessionKey);
			List<SpPegInfo> listChilderns = estimateEjb.getPegChildrensByParentId(selectedPegId,masterId);
			
			//if(listChilderns != null && listChilderns.size() > 0){
			//	packet.put("invalid",true);
			//	packet.put("errorMessage", "Please select valid node");
			//	return packet;
			//}
			
			
			
		
			SpPegInfoEjb spPegInfoEjb = new SpPegInfoEjb();
			
			spPegInfoEjb.updateDescription(selectedPegId, itemName,masterId,sessionKey);
			try {
				packet.put("message",true);
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				packet.put("statusMessage", "Successfully Updated the description");
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			/*String sessionKey= (String) request.getSession().getAttribute("region");
			
			EstimateEjb estimateEjb = new EstimateEjb(sessionKey);
			
			List<Spnorms> list = null;
		
			list = estimateEjb.getAvailableNorms();
			normsMap.clear();
		
			for(int i=0; i<=list.size()-1; i++){
				
				packet.put("lineSecTypeId", list.get(i).getId().getLineSectionTypeId());
				packet.put("uom", list.get(i).getUom());
				packet.put("name", list.get(i).getDescription());
				packet.put("standardCost", list.get(i).getStandardCost());
				
				normsArray.put(packet);
				  
			}
			 packet.put("norms", normsArray);

		    return packet;
			*/
		}catch (Exception e) {
			try {
				packet.put("message",true);
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				packet.put("statusMessage", "Error occured");
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		return packet;


	}
	public JSONObject deletePegItem(HttpServletRequest request, Map parameters){
		JSONObject packet =new JSONObject();
		JSONArray normsArray = new JSONArray();
		String costCenterNo=(String) request.getSession().getAttribute("costCenterNo");
		try {
			String selectedPegId = request.getParameter("selectedPegId");
			String masterId = null;
			if(costCenterNo != null && (costCenterNo.equalsIgnoreCase("430.20") || costCenterNo.equalsIgnoreCase("430.25") || costCenterNo.equalsIgnoreCase("430.30") || costCenterNo.equalsIgnoreCase("430.35") || costCenterNo.equalsIgnoreCase("430.10"))){
				masterId = "4";
			}else if(costCenterNo != null &&(costCenterNo.equalsIgnoreCase("510.20") || costCenterNo.equalsIgnoreCase("510.30") || costCenterNo.equalsIgnoreCase("550.20") || costCenterNo.equalsIgnoreCase("550.30"))){
				masterId = "2";
			}else if(costCenterNo != null && (costCenterNo.equalsIgnoreCase("530.80") || costCenterNo.equalsIgnoreCase("530.20") || costCenterNo.equalsIgnoreCase("530.30"))){
				masterId = "1";
			}
			
			
			String selectedPegText = request.getParameter("selectedPegText");
			String itemName = request.getParameter("itemName");
			
			String sessionKey= (String) request.getSession().getAttribute("region");
			
			
			EstimateEjb estimateEjb = new EstimateEjb(sessionKey);
			List<SpPegInfo> listChilderns = estimateEjb.getPegChildrensByParentId(selectedPegId,masterId);
			
			if(listChilderns != null && listChilderns.size() > 0){
				packet.put("message",true);
				packet.put("statusMessage", "Please select valid node");
				return packet;
			}
			
			
			
		
			SpPegInfoEjb spPegInfoEjb = new SpPegInfoEjb();
			
			spPegInfoEjb.deletePegItem(selectedPegId,masterId,sessionKey);
			try {
				packet.put("message",true);
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				packet.put("statusMessage", "Successfully deleted the Node "+selectedPegText);
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			/*String sessionKey= (String) request.getSession().getAttribute("region");
			
			EstimateEjb estimateEjb = new EstimateEjb(sessionKey);
			
			List<Spnorms> list = null;
		
			list = estimateEjb.getAvailableNorms();
			normsMap.clear();
		
			for(int i=0; i<=list.size()-1; i++){
				
				packet.put("lineSecTypeId", list.get(i).getId().getLineSectionTypeId());
				packet.put("uom", list.get(i).getUom());
				packet.put("name", list.get(i).getDescription());
				packet.put("standardCost", list.get(i).getStandardCost());
				
				normsArray.put(packet);
				  
			}
			 packet.put("norms", normsArray);

		    return packet;
			*/
		}catch (Exception e) {
			try {
				packet.put("message",true);
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				packet.put("statusMessage", "Error occured");
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		return packet;


	}
public JSONObject  loadResourceTypes(HttpServletRequest request) {
		
		JSONArray resourceTypesJson = new JSONArray();

		JSONObject objectlist = new JSONObject();
		String sessionKey= (String) request.getSession().getAttribute("region");
		
		
		
		try {
			EstimateEjb estimateEjb = new EstimateEjb(sessionKey); 
			List<String> resourceTypes  = estimateEjb.loadResourceTypes(sessionKey); 
		  for (String resourceType : resourceTypes) {
			  	JSONObject resourceTypeJson = new JSONObject();
				
			  	resourceTypeJson.put("id", resourceType);
			  	resourceTypeJson.put("name", resourceType);
				
			  	resourceTypesJson.put(resourceTypeJson);
	       }
		  	
	       objectlist.put("jsonarrayResourceTypes", resourceTypesJson);

		} catch (JSONException e) {
			
			e.printStackTrace();
		}

		return objectlist;

	}
	public JSONObject  loadResourceCodes(HttpServletRequest request) {
		
		JSONObject resourceTypeJson = new JSONObject();
		
		JSONArray resourceCodesJson = new JSONArray();

		
		String sessionKey= (String) request.getSession().getAttribute("region");
		String costCenterNo=(String) request.getSession().getAttribute("costCenterNo");
		
		String resourceType = request.getParameter("resourceTypeId");
		String resourceCodeId = request.getParameter("resourceCodeId");
		try {
			EstimateEjb estimateEjb = new EstimateEjb(sessionKey); 
			List <String> matCds = null;
			Inmatm mtm = null;
			InmatmEjb ejbmtm = new InmatmEjb(sessionKey);
			InwrhmtmEjb ejb = new InwrhmtmEjb(sessionKey);
			/*if(resourceType.trim().equalsIgnoreCase(Constants.MAT_COST_OTHER)){
				matCds = ejb.findNPLMatCds(costCenterNo, sessionKey);
				

			}else */
			if(resourceType.trim().equalsIgnoreCase(Constants.MAT_COST)){
				matCds = ejb.findMatCds(costCenterNo, sessionKey);
			
			}else{
				Pcrsgrpm resourceDetail = estimateEjb.loadResourceDetails(resourceType,null,sessionKey);
				Pcrsgrpm resourceDetail1 = null;
				if(resourceDetail != null){
					resourceDetail1 = estimateEjb.loadResourceDetails(resourceType,resourceDetail.getResCd(),sessionKey);
				}
				if(resourceDetail1 != null){
					
					JSONObject resourceCodeJson = new JSONObject();
					
				  	resourceCodeJson.put("id", resourceDetail1.getResCd());
				  	resourceCodeJson.put("name", resourceDetail1.getResCd());
					
				  	resourceCodesJson.put(resourceCodeJson);
				  	resourceTypeJson.put("jsonarrayResourceCodes", resourceCodesJson);
				  	resourceTypeJson.put("resourceName", resourceDetail1.getResNm());
				  	resourceTypeJson.put("categoryCode", "2");
				  	resourceTypeJson.put("unitPrice", resourceDetail1.getUnitPrice());
				  	resourceTypeJson.put("uom", resourceDetail1.getResUom());
				  	
				}
				
			}
			if(matCds !=null && matCds.size() > 0){
				for (String resourceCode : matCds) {
					JSONObject resourceCodeJson = new JSONObject();
						
					resourceCodeJson.put("id", resourceCode);
					resourceCodeJson.put("name", resourceCode);
						
					resourceCodesJson.put(resourceCodeJson);
				}
				
				 resourceTypeJson.put("jsonarrayResourceCodes", resourceCodesJson);
				 
				if(resourceCodeId.equalsIgnoreCase("null")){
					
					
					mtm = ejbmtm.findMatItem(matCds.get(0));
				}else{
					mtm = ejbmtm.findMatItem(resourceCodeId.trim());
					
						
				}
				if(mtm != null){
					resourceTypeJson.put("resourceName", mtm.getMatNm());			  	
					resourceTypeJson.put("unitPrice", mtm.getUnitPrice());
					resourceTypeJson.put("uom", mtm.getMajUom());
					resourceTypeJson.put("categoryCode", "1");
				}
			
			}
			

		} catch (Exception e) {
			
			e.printStackTrace();
		}

		return resourceTypeJson;
		}
public JSONObject  loadResourceDetails(HttpServletRequest request) {
		
		JSONObject resourceTypeJson = new JSONObject();
		
		JSONArray resourceCodesJson = new JSONArray();

		
		String sessionKey= (String) request.getSession().getAttribute("region");
		String costCenterNo=(String) request.getSession().getAttribute("costCenterNo");
		
		String resourceType = request.getParameter("resourceTypeId");
		String resourceCodeId = request.getParameter("resourceCodeId");
		try {
			EstimateEjb estimateEjb = new EstimateEjb(sessionKey); 
			List <String> matCds = null;
			Inmatm mtm = null;
			InmatmEjb ejbmtm = new InmatmEjb(sessionKey);
			InwrhmtmEjb ejb = new InwrhmtmEjb(sessionKey);
			/*if(resourceType.trim().equalsIgnoreCase(Constants.MAT_COST_OTHER)){
				matCds = ejb.findNPLMatCds(costCenterNo, sessionKey);
				

			}else */
			if(resourceType.trim().equalsIgnoreCase(Constants.MAT_COST)){
				matCds = ejb.findMatCds(costCenterNo, sessionKey);
			
			}else{
				Pcrsgrpm resourceDetail = estimateEjb.loadResourceDetails(resourceType,null,sessionKey);
				Pcrsgrpm resourceDetail1 = estimateEjb.loadResourceDetails(resourceType,resourceDetail.getResCd(),sessionKey);
				
				if(resourceDetail1 != null){
					
					JSONObject resourceCodeJson = new JSONObject();
					
				  	resourceCodeJson.put("id", resourceDetail1.getResCd());
				  	resourceCodeJson.put("name", resourceDetail1.getResCd());
					
				  	resourceCodesJson.put(resourceCodeJson);
				  	resourceTypeJson.put("jsonarrayResourceCodes", resourceCodesJson);
				  	resourceTypeJson.put("resourceName", resourceDetail1.getResNm());
					resourceTypeJson.put("categoryCode", "2");
				  	resourceTypeJson.put("unitPrice", resourceDetail1.getUnitPrice());
				  	resourceTypeJson.put("uom", resourceDetail1.getResUom());
				  	
				}
				
			}
			if(matCds !=null && matCds.size() > 0){
				
				if(resourceCodeId.equalsIgnoreCase("null")){
					
					
					mtm = ejbmtm.findMatItem(matCds.get(0));
				}else{
					mtm = ejbmtm.findMatItem(resourceCodeId.trim());
					
						
				}
				if(mtm != null){
					resourceTypeJson.put("resourceName", mtm.getMatNm());			  	
					resourceTypeJson.put("unitPrice", mtm.getUnitPrice());
					resourceTypeJson.put("uom", mtm.getMajUom());
					resourceTypeJson.put("categoryCode", "1");
				}
			
			}
			

		} catch (Exception e) {
			
			e.printStackTrace();
		}

		return resourceTypeJson;
		}
public JSONObject  addMaterials(HttpServletRequest request) {
	
	//JSONObject resourceTypeJson = new JSONObject();
	
	JSONObject packet =new JSONObject();
	JSONArray pegArray = new JSONArray();
	
	String sessionKey= (String) request.getSession().getAttribute("region");
	String costCenterNo=(String) request.getSession().getAttribute("costCenterNo");
	
	String selectedPegId = request.getParameter("selectedPegId");
	String selectedPegText = request.getParameter("selectedPegText");
	String resourceTypeId = request.getParameter("resourceTypeId");
	String resourceCodeId = request.getParameter("resourceCodeId");
	String resourceName = request.getParameter("resourceName");
	String categoryCode = request.getParameter("categoryCode");
	String unitPrice = request.getParameter("unitPrice");
	String estiQuantity = request.getParameter("quantity");
	String uom=request.getParameter("uom");
	String masterId = null;
	if(costCenterNo != null && (costCenterNo.equalsIgnoreCase("430.20") || costCenterNo.equalsIgnoreCase("430.25") || costCenterNo.equalsIgnoreCase("430.30") || costCenterNo.equalsIgnoreCase("430.35") || costCenterNo.equalsIgnoreCase("430.10"))){
		masterId = "4";
	}else if(costCenterNo != null &&(costCenterNo.equalsIgnoreCase("510.20") || costCenterNo.equalsIgnoreCase("510.30") || costCenterNo.equalsIgnoreCase("550.20") || costCenterNo.equalsIgnoreCase("550.30"))){
		masterId = "2";
	}else if(costCenterNo != null && (costCenterNo.equalsIgnoreCase("530.80") || costCenterNo.equalsIgnoreCase("530.20") || costCenterNo.equalsIgnoreCase("530.30"))){
		masterId = "1";
	}
	
	try {
		EstimateEjb estimateEjb = new EstimateEjb(sessionKey); 
		resourceMap = (Map<String, SpPointdmt>) request.getSession().getAttribute("MeterialsList");
		if(resourceMap == null ){
			resourceMap = new HashMap<String, SpPointdmt>();
		}
		if(resourceMap.containsKey(resourceCodeId.trim())){
			packet.put("invalid",true);
			packet.put("errorMessage", "Already exist");
			return packet;
		}
		
		List<SpPointdmt> list = estimateEjb.getPegResourceById(selectedPegId,masterId, sessionKey);
		SpPointDmtEjb pintEjb = new SpPointDmtEjb(); 
		
		SpPointdmt spPointdmt= new SpPointdmt();
		SpPointdmtPK pksp = new SpPointdmtPK();
		pksp.setLineSectionTypeId(selectedPegId.trim());
		pksp.setResCd(resourceCodeId.trim());
		pksp.setMasterId(masterId);
		spPointdmt.setId(pksp);
		spPointdmt.setUom(uom);
		spPointdmt.setUnitPrice(new Float(unitPrice));
		spPointdmt.setResType(resourceTypeId);	
		spPointdmt.setResName(resourceName);
		//spPointdmt.getId().getM("1");
		spPointdmt.setResCat((categoryCode.equalsIgnoreCase(Constants.DEFAULT_STRING) ? 1L : Long.parseLong(categoryCode)));
		spPointdmt.setUom(uom);		
		spPointdmt.setPoleTypeId(selectedPegText);
		spPointdmt.setEstimatedQuantity(new BigDecimal(estiQuantity));
		spPointdmt.setTolerance("10");
		
		pintEjb.createSpPointdmt(spPointdmt, sessionKey);
		
		//for(SpPointdmt dmt : list){
			JSONObject masterDetaRow =new JSONObject();
			masterDetaRow.put("selectedPegId", selectedPegId);
			masterDetaRow.put("resourceType", spPointdmt.getResType());
			masterDetaRow.put("resourceCode",spPointdmt.getId().getResCd().trim());
			masterDetaRow.put("resourceName", spPointdmt.getResName());
			masterDetaRow.put("estimateQuantity", spPointdmt.getEstimatedQuantity());
			masterDetaRow.put("uom", spPointdmt.getUom());
			masterDetaRow.put("unitPrice", spPointdmt.getUnitPrice());
			
			pegArray.put(masterDetaRow);
			resourceMap.put(spPointdmt.getId().getResCd().trim(), spPointdmt);
			request.getSession().setAttribute("MeterialsList",resourceMap);
		//}resourceMap
		packet.put("allMasterDetails", pegArray);
		

	} catch (Exception e) {
		
		e.printStackTrace();
	}

	return packet;
	}

public JSONObject  viewPegMasterDetails(HttpServletRequest request) throws JSONException {
	//clearForm(request);
	nf.setGroupingUsed(true);
	nf.setMaximumFractionDigits(2);
	nf.setMinimumFractionDigits(2);
	JSONObject packet =new JSONObject();
	JSONArray pegArray = new JSONArray();
	String costCenterNo=(String) request.getSession().getAttribute("costCenterNo");
	try {
		String selectedPegId = request.getParameter("selectedPegId");
		//String masterId = request.getParameter("masterId");
		String masterId = null;
		if(costCenterNo != null && (costCenterNo.equalsIgnoreCase("430.20") || costCenterNo.equalsIgnoreCase("430.25") || costCenterNo.equalsIgnoreCase("430.30") || costCenterNo.equalsIgnoreCase("430.35") || costCenterNo.equalsIgnoreCase("430.10"))){
			masterId = "4";
		}else if(costCenterNo != null &&(costCenterNo.equalsIgnoreCase("510.20") || costCenterNo.equalsIgnoreCase("510.30") || costCenterNo.equalsIgnoreCase("550.20") || costCenterNo.equalsIgnoreCase("550.30"))){
			masterId = "2";
		}else if(costCenterNo != null && (costCenterNo.equalsIgnoreCase("530.80") ||  costCenterNo.equalsIgnoreCase("530.20") || costCenterNo.equalsIgnoreCase("530.30"))){
			masterId = "1";
		}
		//String selectedPegText = request.getParameter("selectedPegText");
		
		String sessionKey= (String) request.getSession().getAttribute("region");
		resourceMap = (Map<String,SpPointdmt >) request.getSession().getAttribute("MeterialsList");
		if(resourceMap == null){
			resourceMap = new HashMap<String, SpPointdmt>();
		}
		
		EstimateEjb estimateEjb = new EstimateEjb(sessionKey); 
		List<SpPointdmt> list = estimateEjb.getPegResourceById(selectedPegId,masterId, sessionKey);
		
		for(SpPointdmt dmt : list){
			JSONObject masterDetaRow =new JSONObject();
			masterDetaRow.put("selectedPegId", selectedPegId);
			masterDetaRow.put("resourceType", dmt.getResType());
			masterDetaRow.put("resourceCode",dmt.getId().getResCd().trim());
			masterDetaRow.put("resourceName", dmt.getResName());
			masterDetaRow.put("estimateQuantity", nf.format(dmt.getEstimatedQuantity()));
			masterDetaRow.put("unitPrice", nf.format(dmt.getUnitPrice()));
			masterDetaRow.put("uom", dmt.getUom());
			pegArray.put(masterDetaRow);
			resourceMap.put(dmt.getId().getResCd().trim(), dmt);
		}
		packet.put("masterDetails", pegArray);
		request.getSession().setAttribute("MeterialsList",resourceMap);
		
	} catch (JSONException e) {
		
		packet.put("error", "Error in Load Norms");
		
	}catch (Exception e) {
		packet.put("error", "Error Occurred");
		e.printStackTrace();
	}

	return packet;

}

public JSONObject  deleteMasterPegDetailLine(HttpServletRequest request) {
	
	
	String sessionKey= (String) request.getSession().getAttribute("region");
	String costCenterNo=(String) request.getSession().getAttribute("costCenterNo");
	
	String lineSecId = request.getParameter("selectedItems");
	//String resourceType = request.getParameter("resourceType");
	String pegItemId = request.getParameter("pegItemId");
	
	JSONObject packet = new JSONObject();

	resourceMap = (Map<String,SpPointdmt >) request.getSession().getAttribute("MeterialsList");
	
	SpPointDmtEjb spejb = new SpPointDmtEjb();
	String masterId = null;
	if(costCenterNo != null && (costCenterNo.equalsIgnoreCase("430.20") || costCenterNo.equalsIgnoreCase("430.25") || costCenterNo.equalsIgnoreCase("430.30") || costCenterNo.equalsIgnoreCase("430.35") || costCenterNo.equalsIgnoreCase("430.10"))){
		masterId = "4";
	}else if(costCenterNo != null &&(costCenterNo.equalsIgnoreCase("510.20") || costCenterNo.equalsIgnoreCase("510.30") || costCenterNo.equalsIgnoreCase("550.20") || costCenterNo.equalsIgnoreCase("550.30"))){
		masterId = "2";
	}else if(costCenterNo != null && ( costCenterNo.equalsIgnoreCase("530.80") || costCenterNo.equalsIgnoreCase("530.20") || costCenterNo.equalsIgnoreCase("530.30"))){
		masterId = "1";
	}
		
		
		try {
			//dmt = spejb.findById(pkPoint, sessionKey);
			//if(dmt != null){
				spejb.removeSpPointdmt(pegItemId,lineSecId,masterId, sessionKey);
				try {
					if(lineSecId != null && resourceMap != null && resourceMap.size() > 0){
						resourceMap.remove(lineSecId.trim());
						
							packet.put("message", true);
						
							packet.put("statusMessage", "Successfully deleted Material code "+lineSecId);
						}
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			//}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.getSession().setAttribute("MeterialsList",resourceMap);
		
		
		return packet;
		
	
	
	
}

public JSONObject getMasterUpdateDetails(HttpServletRequest request){
	NumberFormat nf = NumberFormat.getInstance();
	nf.setGroupingUsed(true);
	nf.setMaximumFractionDigits(2);
	nf.setMinimumFractionDigits(2);
	
	DecimalFormat df = new DecimalFormat("#.##");
	

	
	JSONObject packet = new JSONObject();

	String sessionKey= (String) request.getSession().getAttribute("region");
	String costCenterNo=(String) request.getSession().getAttribute("costCenterNo");
	
	String quantity = request.getParameter("quantity");
	
	
	String lineId = request.getParameter("lineid");
	String pegItemId = request.getParameter("pegItemId");
	String masterId = null;
	if(costCenterNo != null && (costCenterNo.equalsIgnoreCase("430.20") || costCenterNo.equalsIgnoreCase("430.25") || costCenterNo.equalsIgnoreCase("430.30") || costCenterNo.equalsIgnoreCase("430.35") || costCenterNo.equalsIgnoreCase("430.10"))){
		masterId = "4";
	}else if(costCenterNo != null &&(costCenterNo.equalsIgnoreCase("510.20") || costCenterNo.equalsIgnoreCase("510.30") || costCenterNo.equalsIgnoreCase("550.20") || costCenterNo.equalsIgnoreCase("550.30"))){
		masterId = "2";
	}else if(costCenterNo != null && (costCenterNo.equalsIgnoreCase("530.80") ||  costCenterNo.equalsIgnoreCase("530.20") || costCenterNo.equalsIgnoreCase("530.30"))){
		masterId = "1";
	}
	
	resourceMap = (Map<String,SpPointdmt >) request.getSession().getAttribute("MeterialsList");
	if(resourceMap == null){
		resourceMap = new HashMap<String, SpPointdmt>();
	}
	SpPointDmtEjb spejb = new SpPointDmtEjb();
	
	if(lineId != null && resourceMap != null && resourceMap.size() > 0){
		SpPointdmt dmt = resourceMap.get(lineId.trim());
		if(dmt != null && quantity != null){
			
		
			//SpPointdmt dmtsp = null;
			try {
				//dmtsp = spejb.findById(pkPoint, sessionKey);
				//if(dmt != null){
					BigDecimal quantityValue = new BigDecimal(quantity);
					dmt.setEstimatedQuantity(quantityValue.setScale(2, BigDecimal.ROUND_HALF_UP));
					//this.damageQty = this.damageQty.setScale(2, BigDecimal.ROUND_HALF_UP);
					
					spejb.updateSpPointdmt(pegItemId,lineId,quantityValue.setScale(2, BigDecimal.ROUND_HALF_UP),masterId, sessionKey);

					resourceMap.put(lineId.trim(),dmt);
					try {
						packet.put("message", true);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					try {
						packet.put("statusMessage", "Successfully updated material quantity of "+lineId);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				//}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
	}

	request.getSession().setAttribute("MeterialsList",resourceMap);
	return packet;
}
public JSONObject addMasters(HttpServletRequest request) {
	//clearForm(request);
	String sessionKey= (String) request.getSession().getAttribute("region");
	String costCenterNo=(String) request.getSession().getAttribute("costCenterNo");
	
	JSONObject packet = new JSONObject();
	JSONArray resourceDetailsArray = new JSONArray();

	String lineSecIds = request.getParameter("selectedItems");
	String resourceType = request.getParameter("resourceType");
	
	String selectedPegId = request.getParameter("selectedPegId");
	String selectedPegText = request.getParameter("selectedPegText");
	
	String categoryCode = request.getParameter("categoryCode");
	
	String[] lineSectionIds = lineSecIds.split("\\|");
	resourceMap = (Map<String,SpPointdmt >) request.getSession().getAttribute("MeterialsList");
	if(resourceMap == null){
		resourceMap = new HashMap<String, SpPointdmt>();
	}
	String masterId = null;
	if(costCenterNo != null && (costCenterNo.equalsIgnoreCase("430.20") || costCenterNo.equalsIgnoreCase("430.25") || costCenterNo.equalsIgnoreCase("430.30") || costCenterNo.equalsIgnoreCase("430.35") || costCenterNo.equalsIgnoreCase("430.10"))){
		masterId = "4";
	}else if(costCenterNo != null &&(costCenterNo.equalsIgnoreCase("510.20") || costCenterNo.equalsIgnoreCase("510.30") || costCenterNo.equalsIgnoreCase("550.20") || costCenterNo.equalsIgnoreCase("550.30"))){
		masterId = "2";
	}else if(costCenterNo != null && ( costCenterNo.equalsIgnoreCase("530.80") || costCenterNo.equalsIgnoreCase("530.20") || costCenterNo.equalsIgnoreCase("530.30"))){
		masterId = "1";
	}
	try {
		EstimateEjb estimateEjb = new EstimateEjb(sessionKey); 
		for (String id : lineSectionIds) {

			if(resourceMap.containsKey(id.trim())){
				packet.put("invalid",true);
				packet.put("errorMessage", "Already exist");
				return packet;
			}
			DetailEstimateDTO detailEstimateDTO = null;
			if(resourceType.equalsIgnoreCase("otherMat")){
				
				availableOtherMaterialsMap = (Map<String, DetailEstimateDTO>) request.getSession().getAttribute("availableOtherMaterialsMap");
				
		
				detailEstimateDTO = (DetailEstimateDTO) availableOtherMaterialsMap.get(id);
				
			}else if(resourceType.equalsIgnoreCase("otherResource")){
				
				availableOtherResourcesMap = (Map<String, DetailEstimateDTO>) request.getSession().getAttribute("availableOtherResourcesMap");
				
		
				detailEstimateDTO = (DetailEstimateDTO) availableOtherResourcesMap.get(id);
				
			}else if(resourceType.equalsIgnoreCase("NPL")){
				availableNPLMaterialsMap = (Map<String, DetailEstimateDTO>) request.getSession().getAttribute("availableNPLMaterialsMap");
				
				
				detailEstimateDTO = (DetailEstimateDTO) availableNPLMaterialsMap.get(id);
				
			}
			if(detailEstimateDTO != null){
			//List<SpPointdmt> list = estimateEjb.getPegResourceById(id, sessionKey);
				SpPointDmtEjb pintEjb = new SpPointDmtEjb(); 
				
				SpPointdmt spPointdmt= new SpPointdmt();
				SpPointdmtPK pksp = new SpPointdmtPK();
				pksp.setLineSectionTypeId(selectedPegId.trim());
				pksp.setResCd(id.trim());
				pksp.setMasterId(masterId);
				spPointdmt.setId(pksp);
				spPointdmt.setUom(detailEstimateDTO.getUom());
				
				spPointdmt.setUnitPrice(new Float(detailEstimateDTO.getUnitPrice().doubleValue()));
				
				if(resourceType.equalsIgnoreCase("otherMat")){
					
					spPointdmt.setResType("MAT-COST");	
				}else{
					spPointdmt.setResType(detailEstimateDTO.getResourceType());	
				}
				
				
				spPointdmt.setResName(detailEstimateDTO.getResourceName());
				spPointdmt.setResCat(1L);
				//spPointdmt.setUom(uom);		
				spPointdmt.setPoleTypeId(selectedPegText);
				//spPointdmt.setEstimatedQuantity(new Float(estiQuantity));
				spPointdmt.setTolerance("10");
				try {
					pintEjb.createSpPointdmt(spPointdmt, sessionKey);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				JSONObject masterDetaRow =new JSONObject();
				masterDetaRow.put("selectedPegId", selectedPegId);
				masterDetaRow.put("resourceType", spPointdmt.getResType());
				masterDetaRow.put("resourceCode",spPointdmt.getId().getResCd().trim());
				masterDetaRow.put("resourceName", spPointdmt.getResName());
				masterDetaRow.put("estimateQuantity", spPointdmt.getEstimatedQuantity());
				masterDetaRow.put("uom", spPointdmt.getUom());
				masterDetaRow.put("unitPrice", spPointdmt.getUnitPrice());
				
				resourceDetailsArray.put(masterDetaRow);
				resourceMap.put(spPointdmt.getId().getResCd().trim(), spPointdmt);
				request.getSession().setAttribute("MeterialsList",resourceMap);
				packet.put("addedMaterials", resourceDetailsArray);
			}
			
			
			//for(SpPointdmt dmt : list){
				
			//}resourceMap
			//packet.put("allMasterDetails", pegArray);
			
		
		}
	} catch (JSONException e) {
		try {
			packet.put("error", "Error Occurred");
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		e.printStackTrace();
	}

	return packet;

}

public JSONObject  loadEstimateNumForContractorLetter(HttpServletRequest request) {
	
	JSONArray estimationRefNoJsonArr = new JSONArray();

	JSONObject objectlist = new JSONObject();
	String sessionKey= (String) request.getSession().getAttribute("region");
	String costCenterNo= (String) request.getSession().getAttribute("costCenterNo");
	
	
	
	
		//SpestedyConEjb estimateEjb = new SpestedyConEjb(sessionKey);
		//EstimateEjb estimateEjb = new EstimateEjb(sessionKey); 
		List<EstimateReference> estimationRefs = null;
		//estimationRefNos = estimateEjb.loadApplicationRefnos(null,costCenterNo,AppointmentStatus.ACTIVE);
		EstimateReferenceEjb estEjb = new EstimateReferenceEjb();
		try {
			estimationRefs = estEjb.getActiveConstructionEstimates(costCenterNo, sessionKey);
			if(estimationRefs != null){
				for (EstimateReference estimationRefNo : estimationRefs) {
				  	
					
				  	JSONObject estimationRefNoJson = new JSONObject();
					
				  	estimationRefNoJson.put("id", estimationRefNo.getId().getStandardEstimateNo());
				  	estimationRefNoJson.put("name", estimationRefNo.getId().getStandardEstimateNo());
					
				  	estimationRefNoJsonArr.put(estimationRefNoJson);
		       }
				 objectlist.put("estimateNumbers", estimationRefNoJsonArr);
			}
		  	
	      
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		


	return objectlist;

}
public AjaxResponse  loadPegInfoMaster(HttpServletRequest request) throws JSONException{
	
	String node = request.getParameter("node");
	//String masterId = request.getParameter("masterId");
	String costCenterNo=(String) request.getSession().getAttribute("costCenterNo");
	String masterId = null;
	if(costCenterNo != null && (costCenterNo.equalsIgnoreCase("430.20") || costCenterNo.equalsIgnoreCase("430.25") || costCenterNo.equalsIgnoreCase("430.30") || costCenterNo.equalsIgnoreCase("430.35") || costCenterNo.equalsIgnoreCase("430.10"))){
		masterId = "4";
	}else if(costCenterNo != null &&(costCenterNo.equalsIgnoreCase("510.20") || costCenterNo.equalsIgnoreCase("510.30") || costCenterNo.equalsIgnoreCase("550.20") || costCenterNo.equalsIgnoreCase("550.30"))){
		masterId = "2";
	}else if(costCenterNo != null && (costCenterNo.equalsIgnoreCase("530.80") || costCenterNo.equalsIgnoreCase("530.20") || costCenterNo.equalsIgnoreCase("530.30"))){
		masterId = "1";
	}
	if(node.equals("source")){
		node = null;
	}
	return new AjaxResponse(AjaxResponse.TYPE_JSON, setHierarchyLinks(node,masterId,request));

}
private JSONArray setHierarchyLinks(String nodeId,String masterId,HttpServletRequest request) throws JSONException {
	   
	String sessionKey= (String) request.getSession().getAttribute("region");
	
	EstimateEjb estimateEjb = new EstimateEjb(sessionKey);
	
	List<SpPegInfo> list = null;

	JSONArray sppegArray = new JSONArray();
	try {
		if (nodeId == null) {
			list = estimateEjb.getPegChildrensByParentId("1",masterId);
		}else{
			list = estimateEjb.getPegChildrensByParentId(nodeId,masterId);
		}
		if(list != null && list.size() > 0){
			for(SpPegInfo sppeg :  list){
				
				Node node = new Node(sppeg.getId().getPegItemId(),sppeg.getName());
				
				List<SpPegInfo> listChilderns = estimateEjb.getPegChildrensByParentId(sppeg.getId().getPegItemId(),masterId);
				
				if(listChilderns != null && listChilderns.size() > 0){
					node.setLeaf(false);
				}else{
					node.setLeaf(true);
				}
				
				sppegArray.put(node.toJson());

			}
		}
	} catch (Exception e) {
		
		e.printStackTrace();
	}
	
    return sppegArray;

}  
public JSONObject  loadConstructionEstimForJobAllocLetter(HttpServletRequest request) {
	
	JSONArray constructionNumberJsonJsonArr = new JSONArray();
	JSONArray alloactedIdsJsonJsonArr = new JSONArray();

	JSONObject objectlist = new JSONObject();
	String sessionKey= (String) request.getSession().getAttribute("region");
	String costCenterNo= (String) request.getSession().getAttribute("costCenterNo");
	String sEstimateNo= (String) request.getParameter("ApplicationNumber");
	List<EstimateReference> constructionNumbers = null;
	List<SpestedyCons> alloactedIDS = null;
	
	try {
		if(sEstimateNo != null){
			 EstimateReferenceEjb estEjb = new EstimateReferenceEjb();
			     if(costCenterNo.equals("530.00")){
			    	 constructionNumbers = estEjb.findByStdEstimateNoCom("530", sessionKey);
				     
			     }else{
			    	 constructionNumbers = estEjb.findByStdEstimateNo(sEstimateNo,costCenterNo, sessionKey);
			     }
				if(constructionNumbers != null){
				  for (EstimateReference estimateReference : constructionNumbers) {
					  	JSONObject constructionNumberJson = new JSONObject();
						
					  	constructionNumberJson.put("id", estimateReference.getId().getWorkEstimateNo());
					  	constructionNumberJson.put("name", estimateReference.getId().getWorkEstimateNo());
						
					  	constructionNumberJsonJsonArr.put(constructionNumberJson);
			       }
				  	
			       objectlist.put("estimateNumbers", constructionNumberJsonJsonArr);
				}  
			 	  
					 SpestedyConEjb spestedyConEjb = new SpestedyConEjb(sessionKey);
					 alloactedIDS = spestedyConEjb.getAppointmentForReference(costCenterNo, sEstimateNo,sessionKey);
					 if(alloactedIDS != null){
						Map<String,String> alloactedIDSMap = new HashMap<String, String>();
							for (SpestedyCons alloactedID : alloactedIDS) {
								alloactedIDSMap.put(alloactedID.getAllocatedTo(), alloactedID.getAllocatedTo());
							}
							List<String> list = new ArrayList<String>(alloactedIDSMap.keySet());
							for (String alloactedID : list) {
						 
							 
								  	JSONObject alloactedJson = new JSONObject();
									
								  	alloactedJson.put("id", alloactedID);
								  	alloactedJson.put("name", alloactedID);
								
								  	alloactedIdsJsonJsonArr.put(alloactedJson);
						   }
						  	
						   objectlist.put("allocatedIds", alloactedIdsJsonJsonArr);
						}
			      /* for (EstimateReference estimateReference : constructionNumbers) {
					  	JSONObject alloactedJson = new JSONObject();
						
					  	alloactedJson.put("id", estimateReference.getEntryBy());
					  	alloactedJson.put("name", estimateReference.getEntryBy());
						
					  	alloactedIdsJsonJsonArr.put(alloactedJson);
			       }
			       objectlist.put("allocatedIds", alloactedIdsJsonJsonArr);*/
				
			  	
		       
		}
	} catch (JSONException e) {
		
		e.printStackTrace();
	}

	return objectlist;

}
public JSONObject  loadConstructonRefreForContractorLetter(HttpServletRequest request) {
	
	JSONArray constructionNumberJsonJsonArr = new JSONArray();
	JSONArray alloactedIdsJsonJsonArr = new JSONArray();

	JSONObject objectlist = new JSONObject();
	String sessionKey= (String) request.getSession().getAttribute("region");
	String costCenterNo= (String) request.getSession().getAttribute("costCenterNo");
	String sEstimateNo= (String) request.getParameter("ApplicationNumber");
	List<EstimateReference> constructionNumbers = null;
	
	List<SpestedyCons> alloactedIDS = null;
	
	try {
		if(sEstimateNo != null){
			 EstimateReferenceEjb estEjb = new EstimateReferenceEjb();
			 constructionNumbers = estEjb.findByStdEstimateNo(sEstimateNo,costCenterNo, sessionKey);
				if(constructionNumbers != null){
					for (EstimateReference estimateReference : constructionNumbers) {
					  	JSONObject constructionNumberJson = new JSONObject();
						
					  	constructionNumberJson.put("id", estimateReference.getId().getWorkEstimateNo());
					  	constructionNumberJson.put("name", estimateReference.getId().getWorkEstimateNo());
						
					  	constructionNumberJsonJsonArr.put(constructionNumberJson);
			       }
				  	
			       objectlist.put("estimateNumbers", constructionNumberJsonJsonArr);	
				}
		/*	  
				 SpestedyConEjb spestedyConEjb = new SpestedyConEjb(sessionKey);
				 alloactedIDS = spestedyConEjb.getAppointmentForReference(costCenterNo, sEstimateNo,sessionKey);
				 if(alloactedIDS != null){
					Map<String,String> alloactedIDSMap = new HashMap<String, String>();
						for (SpestedyCons alloactedID : alloactedIDS) {
							alloactedIDSMap.put(alloactedID.getAllocatedTo(), alloactedID.getAllocatedTo());
						}
						List<String> list = new ArrayList<String>(alloactedIDSMap.keySet());
						for (String alloactedID : list) {
					 
						 
							  	JSONObject alloactedJson = new JSONObject();
								
							  	alloactedJson.put("id", alloactedID);
							  	alloactedJson.put("name", alloactedID);
							
							  	alloactedIdsJsonJsonArr.put(alloactedJson);
					   }
					  	
					   objectlist.put("allocatedIds", alloactedIdsJsonJsonArr);
					} */ 
				  }
		   
	} catch (JSONException e) {
		
		e.printStackTrace();
	}

	return objectlist;

}
public JSONObject  loadEstimateDetailsForContractorLetter(HttpServletRequest request) {
	
	JSONArray constructionNumberJsonJsonArr = new JSONArray();
	JSONArray alloactedIdsJsonJsonArr = new JSONArray();

	JSONObject objectlist = new JSONObject();
	String sessionKey= (String) request.getSession().getAttribute("region");
	String costCenterNo= (String) request.getSession().getAttribute("costCenterNo");
	String wrEstimateNo= (String) request.getParameter("constructionId");
	EstimateReference estimateReference = null;
	
	List<SpestedyCons> alloactedIDS = null;
	
	try {
		if(wrEstimateNo != null){
			 EstimateReferenceEjb estEjb = new EstimateReferenceEjb();
			 System.out.println("west : "+wrEstimateNo);
			 estimateReference = estEjb.findByWorkEstimateNo(wrEstimateNo,costCenterNo, sessionKey);
			 PcesthmtEjb ejb = new PcesthmtEjb(sessionKey);
			 Pcesthmt hmt = ejb.findByEstimationNo(wrEstimateNo, costCenterNo);
				if(estimateReference != null && hmt != null){
				   System.out.println("projectNo : "+hmt.getProjectNo());
			       objectlist.put("entryBy", estimateReference.getEntryBy());	
			       objectlist.put("projectNo", hmt.getProjectNo());
			       if(estimateReference.getProjectno() != null){
			    	   SpestcndEjb spestcnd = new SpestcndEjb(sessionKey);
				       Spestcnd cnd = spestcnd.findByJobNo(estimateReference.getProjectno().trim());
				       if(cnd != null){
				    	   SpestcntEjb spestcnt = new SpestcntEjb(sessionKey);
					       Spestcnt spestcntDetails = spestcnt.findByContractorId(cnd.getId().getContractorId(),costCenterNo);
					       objectlist.put("contractorId", spestcntDetails.getContractorName());
					       
				       }else{
				    	   objectlist.put("status", "Contractor is not assigned for this job");	
				       }
				       
			       }
			      
				}
			  
				// SpestedyConEjb spestedyConEjb = new SpestedyConEjb(sessionKey);
				// alloactedIDS = spestedyConEjb.getAppointmentForReference(costCenterNo, sEstimateNo,sessionKey);
				/* if(alloactedIDS != null){
					Map<String,String> alloactedIDSMap = new HashMap<String, String>();
						for (SpestedyCons alloactedID : alloactedIDS) {
							alloactedIDSMap.put(alloactedID.getAllocatedTo(), alloactedID.getAllocatedTo());
						}
						List<String> list = new ArrayList<String>(alloactedIDSMap.keySet());
						for (String alloactedID : list) {
					 
						 
							  	JSONObject alloactedJson = new JSONObject();
								
							  	alloactedJson.put("id", alloactedID);
							  	alloactedJson.put("name", alloactedID);
							
							  	alloactedIdsJsonJsonArr.put(alloactedJson);
					   }
					  	
					   objectlist.put("allocatedIds", alloactedIdsJsonJsonArr);
					}*/  
				  }
		   
	} catch (JSONException e) {
		
		e.printStackTrace();
	}

	return objectlist;

}
/*public JSONObject  loadAllocatedESListForContractorLetter(HttpServletRequest request) {
	
	JSONArray constructionNumberJsonJsonArr = new JSONArray();
	JSONArray alloactedIdsJsonJsonArr = new JSONArray();

	JSONObject objectlist = new JSONObject();
	String sessionKey= (String) request.getSession().getAttribute("region");
	String costCenterNo= (String) request.getSession().getAttribute("costCenterNo");
	String sEstimateNo= (String) request.getParameter("ApplicationNumber");
	List<SpestedyCons> alloactedIDS = null;
	
	
	try {
		if(sEstimateNo != null){
			 SpestedyConEjb spestedyConEjb = new SpestedyConEjb(sessionKey);
			 alloactedIDS = spestedyConEjb.getAppointmentForReference(costCenterNo, sEstimateNo,sessionKey);
				
			  
		       for (SpestedyCons alloactedID : alloactedIDS) {
				  	JSONObject alloactedJson = new JSONObject();
					
				  	alloactedJson.put("id", alloactedID.getAllocatedTo());
				  	alloactedJson.put("name", alloactedID.getAllocatedTo());
					
				  	alloactedIdsJsonJsonArr.put(alloactedJson);
		       }
			  	
		       objectlist.put("allocatedIds", alloactedIdsJsonJsonArr);
		}
	} catch (JSONException e) {
		
		e.printStackTrace();
	}

	return objectlist;

}*/
public JSONObject  loadSEstimateNumbersForJobAllocLetter(HttpServletRequest request) {
	
	JSONArray estimationRefNoJsonArr = new JSONArray();

	JSONObject objectlist = new JSONObject();
	String sessionKey= (String) request.getSession().getAttribute("region");
	String costCenterNo= (String) request.getSession().getAttribute("costCenterNo");
	
	
	
	try {
		SpestedyConEjb estimateEjb = new SpestedyConEjb(sessionKey);
		//EstimateEjb estimateEjb = new EstimateEjb(sessionKey); 
		List<String> estimationRefNos = null;
		//if(costCenterNo.equalsIgnoreCase("530.20") || costCenterNo.equalsIgnoreCase("530.30")){
			estimationRefNos = estimateEjb.loadApplicationRefnos(null,costCenterNo,null); 
			Map<String,String> estimNos = new HashMap<String, String>();
			for (String estimationRefNo : estimationRefNos) {
				estimNos.put(estimationRefNo, estimationRefNo);
			}
			List<String> list = new ArrayList<String>(estimNos.keySet());
		  for (String estimationRefNo : list) {
			  	
			  	JSONObject estimationRefNoJson = new JSONObject();
				
			  	estimationRefNoJson.put("id", estimNos.get(estimationRefNo));
			  	estimationRefNoJson.put("name", estimNos.get(estimationRefNo));
				
			  	estimationRefNoJsonArr.put(estimationRefNoJson);
	       }
		  objectlist.put("estimateNumbers", estimationRefNoJsonArr);
		//}else{
			/*EstimateReferenceEjb estEjb = new EstimateReferenceEjb();
			List<EstimateReference> estimationRefs = null;
			try {
				estimationRefs = estEjb.getActiveConstructionEstimates(costCenterNo, sessionKey);
				for (EstimateReference estimationRefNo : estimationRefs) {
				  	
					
				  	JSONObject estimationRefNoJson = new JSONObject();
					
				  	estimationRefNoJson.put("id", estimationRefNo.getId().getStandardEstimateNo());
				  	estimationRefNoJson.put("name", estimationRefNo.getId().getStandardEstimateNo());
					
				  	estimationRefNoJsonArr.put(estimationRefNoJson);
		       }
			  	
		       objectlist.put("estimateNumbers", estimationRefNoJsonArr);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}*/
		
		
	  	
       

	} catch (JSONException e) {
		
		e.printStackTrace();
	}

	return objectlist;

}
public JSONObject  loadContractorsForContractorLetter(HttpServletRequest request) {
	
	JSONArray contractorsJsonArr = new JSONArray();

	JSONObject objectlist = new JSONObject();
	String sessionKey= (String) request.getSession().getAttribute("region");
	String costCenterNo= (String) request.getSession().getAttribute("costCenterNo");
	
	
	
	try {
		List<Spestcnt> spestcntList=new ArrayList<Spestcnt>();	
		
		SpestcntEjb ejb=new SpestcntEjb(sessionKey);	
		spestcntList=ejb.getContractorByStatus(costCenterNo, "1");
		
	  for (Spestcnt spestcn : spestcntList) {
		  	JSONObject contractorJson = new JSONObject();
			
		  	contractorJson.put("id", spestcn.getId().getContractorId());
		  	contractorJson.put("name", spestcn.getCode());
			
		  	contractorsJsonArr.put(contractorJson);
       }
	  	
       objectlist.put("contractors", contractorsJsonArr);

	} catch (JSONException e) {
		
		e.printStackTrace();
	}

	return objectlist;

}

public JSONObject  loadWorkScopeForJob(HttpServletRequest request) {
	
	JSONArray constructionNumberJsonJsonArr = new JSONArray();
	JSONArray alloactedIdsJsonJsonArr = new JSONArray();

	JSONObject objectlist = new JSONObject();
	String sessionKey= (String) request.getSession().getAttribute("region");
	String costCenterNo= (String) request.getSession().getAttribute("costCenterNo");
	String sEstimateNo= (String) request.getParameter("ApplicationNumber");
	String allocatedId= (String) request.getParameter("allocatedId");
	
	try {
		if(sEstimateNo != null){
			 SpestedyConEjb ejbspCon = new SpestedyConEjb(sessionKey);
			 List<SpestedyCons> spestedyCons = ejbspCon.getAppointmentByUser(sEstimateNo,null,costCenterNo,allocatedId);
				if(spestedyCons != null){
					
				  	
			       objectlist.put("workScope", spestedyCons.get(0).getDescription());	
				}
		/*	  
				 SpestedyConEjb spestedyConEjb = new SpestedyConEjb(sessionKey);
				 alloactedIDS = spestedyConEjb.getAppointmentForReference(costCenterNo, sEstimateNo,sessionKey);
				 if(alloactedIDS != null){
					Map<String,String> alloactedIDSMap = new HashMap<String, String>();
						for (SpestedyCons alloactedID : alloactedIDS) {
							alloactedIDSMap.put(alloactedID.getAllocatedTo(), alloactedID.getAllocatedTo());
						}
						List<String> list = new ArrayList<String>(alloactedIDSMap.keySet());
						for (String alloactedID : list) {
					 
						 
							  	JSONObject alloactedJson = new JSONObject();
								
							  	alloactedJson.put("id", alloactedID);
							  	alloactedJson.put("name", alloactedID);
							
							  	alloactedIdsJsonJsonArr.put(alloactedJson);
					   }
					  	
					   objectlist.put("allocatedIds", alloactedIdsJsonJsonArr);
					} */ 
				  }
		   
	} catch (JSONException e) {
		
		e.printStackTrace();
	}

	return objectlist;

}
	/*public JSONObject  showNorms(HttpServletRequest request) throws JSONException {
		NumberFormat nf = NumberFormat.getInstance();
		nf.setGroupingUsed(true);
		nf.setMaximumFractionDigits(2);
		nf.setMinimumFractionDigits(2);
		
		JSONObject packet =new JSONObject();
		JSONArray normsArray = new JSONArray();
		try {
			loadNorms(request);
			normsMap = (Map<String,Norm> )request.getSession().getAttribute("normsMap");
			
			List<Norm> normList = new ArrayList<Norm>(normsMap.values());
			
			for(Norm norm :normList ){

	            
	           JSONObject normDataRow =new JSONObject();
				 
				normDataRow.put("linesectionid", norm.getLineSectionTypeId());
				normDataRow.put("uom", norm.getUom());
				normDataRow.put("standardcost",nf.format(norm.getStandardCost()));
				normDataRow.put("description", norm.getDescription());
					
				normsArray.put(normDataRow);
	             
			}
			
			packet.put("shownorms", normsArray);
			
			System.out.println("hi");
		} catch (JSONException e) {
			
			packet.put("error", "Error in Load Norms");
			
		}catch (Exception e) {
			packet.put("error", "Error Occurred");
			e.printStackTrace();
		}

		return packet;

	}
	
	
	public JSONObject  addNorms(HttpServletRequest request) throws JSONException {
		
		JSONObject packet =new JSONObject();
		JSONArray normsArray = new JSONArray();
		
		norms = (Map<String, Norm>) request.getSession().getAttribute("normsMap");
		selectedNorms =  (Map<String, Norm>) request.getSession().getAttribute("selectedNorms");
		newlyAddedDetails =  (Map<String, Norm>) request.getSession().getAttribute("newlyAddedDetails");
		
		String lineSecIds = request.getParameter("selectedItems");
		String func = request.getParameter("func");
	
		String[] lineSectionIds = lineSecIds.split("\\|");
	
		try {

			for(String id : lineSectionIds){
				
				JSONObject normDataRow =new JSONObject();
				Norm norm = (Norm) norms.get(id);
				if(norm == null){
					norm = (Norm) selectedNorms.get(id);
				}
				
				
				normDataRow.put("linesectionid", norm.getLineSectionTypeId());
				normDataRow.put("uom", norm.getUom());
				normDataRow.put("standardcost", norm.getStandardCost());
				normDataRow.put("description", norm.getDescription());
				normDataRow.put("OtherCode", norm.getLineSectionTypeId().contains("OTH"));
				normsArray.put(normDataRow);
				
				
				if(selectedNorms == null){
					selectedNorms = new HashMap<String, Norm>();
				}
				if(newlyAddedDetails == null){
					newlyAddedDetails = new HashMap<String, Norm>();
				}
				
				
				if(func.equalsIgnoreCase("add")){
					selectedNorms.put(id, norm);
					newlyAddedDetails.put(id, norm);
				}else if(func.equalsIgnoreCase("delete")){
					newlyAddedDetails.remove(id);
					selectedNorms.remove(id);
					deletedNorms.put(id, norm);
				}

			}
			request.getSession().setAttribute("selectedNorms",selectedNorms);
			request.getSession().setAttribute("deletedNorms",deletedNorms);
			request.getSession().setAttribute("newlyAddedDetails",newlyAddedDetails);
			packet.put("addednorms", normsArray);
			
		} catch (JSONException e) {
			packet.put("error", "Error Occurred");
			e.printStackTrace();
		}

		return packet;

	}
	*/

	
}
