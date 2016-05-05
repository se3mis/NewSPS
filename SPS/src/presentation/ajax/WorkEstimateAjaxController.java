package presentation.ajax;


import inventory.service.InmatmEjb;
import inventory.service.InwrhmtmEjb;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import job.model.Pcestdmt;
import job.model.Pcesthmt;
import job.model.Spestcnt;
import job.service.JobEjb;
import job.service.PcestdmtEjb;
import job.service.PcesthmtEjb;
import job.service.SpestcntEjb;

import masters.service.ProvinceDetailsMasterEjb;

import org.apache.struts2.ServletActionContext;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import application.ejb.ApplicationReferenceDaoRemote;
import application.model.Application;
import application.model.ApplicationReference;
import application.model.WiringLandDetailCon;
import application.model.WiringLandDetailConPK;
import application.service.ApplicationEjb;
import application.service.ApplicationReferenceEjb;
import application.service.WiringLandDetailConEjb;

import piv.model.PivDetail;
import piv.model.TempTb;
import piv.service.PivDetailEjb;
import print.standardEstimate.DetailEstimatePrintDetails;
import progressMonitoring.model.Pcmiledates;
import progressMonitoring.model.PcmiledatesPK;
import progressMonitoring.model.Pcmilem;
import progressMonitoring.model.PcmilemPK;
import progressMonitoring.model.Pcmilesumary;
import progressMonitoring.model.PcmilesumaryPK;
import progressMonitoring.service.PcmiledatesEjb;
import progressMonitoring.service.PcmilemEjb;
import progressMonitoring.service.PcmilesumaryEjb;
import progressMonitoring.service.ProgressMonitoringEjb;
import rebate.model.Sprebate;
import rebate.model.SprebatePK;
import rebate.service.RebateEjb;
import security.service.SecurityEjb;
import util.common.ApplicationType;
import util.common.Constants;
import util.common.EstimateApproval;
import util.common.EstimateNumberGenerator;
import util.common.EstimateStatus;
import util.common.Format;
import util.common.PivPrefixType;
import util.common.ReferenceType;
import estimate.dto.DetailEstimateDTO;
import estimate.dto.EstimateDetails;
import estimate.dto.Norm;
import estimate.dto.PegItemDTO;
import estimate.model.AllocationSummary;
import estimate.model.AllocationSummaryDisplay;
import estimate.model.Approval;
import estimate.model.EstimateReference;
import estimate.model.Pcestdtt;
import estimate.model.PcestdttPK;
import estimate.model.Pcesthtt;
import estimate.model.PcesthttPK;
import estimate.model.Pegschdmt;
import estimate.model.PegschdmtPK;
import estimate.model.SpPegInfo;
import estimate.model.SpPointdmt;
import estimate.model.SpPointdmtPK;
import estimate.model.Spstdestdmt;
import estimate.model.Spstdesthmt;
import estimate.model.SpstdesthmtPK;
import estimate.service.ApprovalEjb;
import estimate.service.EstimateEjb;
import estimate.service.EstimateReferenceEjb;
import estimate.service.PcestdttEjb;
import estimate.service.PcesthttEjb;
import estimate.service.PegschdmtEjb;
import estimate.service.SpPointDmtEjb;
import estimate.service.SpstdesthmtEjb;


public class WorkEstimateAjaxController {
	private Format format;
	Map<String,DetailEstimateDTO> detailEstimate = null;
	//private Map<String,DetailEstimateDTO> availableOtherMaterialsMap = new HashMap<String,DetailEstimateDTO>();
	Map<String, DetailEstimateDTO> availableOtherMaterialsMap = new TreeMap<String, DetailEstimateDTO>();
	private  Map<String,DetailEstimateDTO> selectedOtherMaterials = new HashMap<String,DetailEstimateDTO>();
	private  Map<String,DetailEstimateDTO> deletedDetailEstimate = new HashMap<String,DetailEstimateDTO>();
	
	
	//Map<String,DetailEstimateDTO> detailEstimate = null;
	//private Map<String,DetailEstimateDTO> availableOtherResourcesMap = new HashMap<String,DetailEstimateDTO>();
	Map<String, DetailEstimateDTO> availableOtherResourcesMap = new TreeMap<String, DetailEstimateDTO>();
	private  Map<String,DetailEstimateDTO> selectedOtherResources = new HashMap<String,DetailEstimateDTO>();
	//private  Map<String,DetailEstimateDTO> deletedDetailEstimate = new HashMap<String,DetailEstimateDTO>();
	
	//private Map<String,DetailEstimateDTO> availableNPLMaterialsMap = new HashMap<String,DetailEstimateDTO>();
	Map<String, DetailEstimateDTO> availableNPLMaterialsMap = new TreeMap<String, DetailEstimateDTO>();
	private  Map<String,DetailEstimateDTO> selectedNPLMaterials = new HashMap<String,DetailEstimateDTO>();
	
	private List<String>  pegIdList= new ArrayList<String>();
	Map<String,DetailEstimateDTO> resourceMap = new HashMap<String,DetailEstimateDTO>();
	Map<String,PegItemDTO> pegItemMap = new TreeMap<String,PegItemDTO>();
	Map<String,DetailEstimateDTO> allSelectedAdditionalResoMap = new HashMap<String,DetailEstimateDTO>();
	
	Map<String,DetailEstimateDTO> alreadyAddedDetails = new HashMap<String,DetailEstimateDTO>();
	Map<String,Pcestdmt> alreadyAddedDmtDetails = new HashMap<String,Pcestdmt>();
	Map<String,DetailEstimateDTO> updatedDetailsMap = new HashMap<String,DetailEstimateDTO>();
	Map<String,DetailEstimateDTO> deletedDetailsMap = new HashMap<String,DetailEstimateDTO>();
	
	Map<String, DetailEstimateDTO> availableOtherMaterialsMap1 = new TreeMap<String, DetailEstimateDTO>();
	Map<String, DetailEstimateDTO> availableOtherMaterialsMap2 = new TreeMap<String, DetailEstimateDTO>();
	Map<String, DetailEstimateDTO> availableOtherMaterialsMap3 = new TreeMap<String, DetailEstimateDTO>();
	Map<String, DetailEstimateDTO> availableOtherMaterialsMap4 = new TreeMap<String, DetailEstimateDTO>();
	Map<String, DetailEstimateDTO> availableOtherMaterialsMap5 = new TreeMap<String, DetailEstimateDTO>();
	Map<String, DetailEstimateDTO> availableOtherMaterialsMap6 = new TreeMap<String, DetailEstimateDTO>();
	Map<String, DetailEstimateDTO> availableOtherMaterialsMap7 = new TreeMap<String, DetailEstimateDTO>();
	Map<String, DetailEstimateDTO> availableOtherMaterialsMap8 = new TreeMap<String, DetailEstimateDTO>();

	Map<String, DetailEstimateDTO> availableOtherMaterialsMap9 = new TreeMap<String, DetailEstimateDTO>();
	private  Map<String,AllocationSummaryDisplay> allocationSummaryDisplayList = new HashMap<String,AllocationSummaryDisplay>();
	
	private  Map<String,AllocationSummary> allocationSummaryList = new HashMap<String,AllocationSummary>();
	Map<String, List<AllocationSummaryDisplay>>  allocationSummaryListByCommRef = new HashMap<String, List<AllocationSummaryDisplay>>();
	
	Map<String,PegItemDTO> existingPegItemMap = new TreeMap<String,PegItemDTO>();
	Map<String,Pcesthtt> pcesthttListForPrint = new TreeMap<String,Pcesthtt>();
	
	NumberFormat nf = NumberFormat.getInstance();

	EstimateDetails dto = new EstimateDetails();
	float allTotalCost = 0L;
	Map<String,Pcmilem> milestoneList = new TreeMap<String,Pcmilem>();
	Map<String,Pcmiledates> addedMilestones = new HashMap<String,Pcmiledates>();
	public void clearForm(HttpServletRequest request){
		
		availableOtherMaterialsMap = (Map<String,DetailEstimateDTO> )request.getSession().getAttribute("availableOtherMaterialsMap");
		selectedOtherMaterials =  (Map<String, DetailEstimateDTO>) request.getSession().getAttribute("selectedOtherMaterials");
		
		availableNPLMaterialsMap = (Map<String,DetailEstimateDTO> )request.getSession().getAttribute("availableOtherMaterialsMap");
		selectedNPLMaterials =  (Map<String, DetailEstimateDTO>) request.getSession().getAttribute("selectedNPLMaterials");
	
		
		availableOtherResourcesMap = (Map<String,DetailEstimateDTO> )request.getSession().getAttribute("availableOtherResourcesMap");
		selectedOtherResources =  (Map<String, DetailEstimateDTO>) request.getSession().getAttribute("selectedOtherResources");

		pegIdList = (List<String>) request.getSession().getAttribute("selectedpegIdList");
		resourceMap = (Map<String,DetailEstimateDTO>) request.getSession().getAttribute("detailEstimationDetails");
		pegItemMap = (Map<String,PegItemDTO>) request.getSession().getAttribute("pegItemDetails");
		existingPegItemMap = (Map<String,PegItemDTO>) request.getSession().getAttribute("existingPegItemMap");
		
		allSelectedAdditionalResoMap = (Map<String,DetailEstimateDTO>) request.getSession().getAttribute("allSelectedAdditionalResources");
		
		updatedDetailsMap = (Map<String,DetailEstimateDTO>) request.getSession().getAttribute("updatedDetails");
		
		deletedDetailsMap = (Map<String,DetailEstimateDTO>) request.getSession().getAttribute("deletedDetails");
		alreadyAddedDetails =(Map<String,DetailEstimateDTO>) request.getSession().getAttribute("alreadyAddedDetails");
		
		alreadyAddedDmtDetails =(Map<String,Pcestdmt>) request.getSession().getAttribute("alreadyAddedDmtDetails");
		dto = (EstimateDetails) request.getSession().getAttribute("estimateDetailobject");
		
		availableOtherMaterialsMap1 = (Map<String,DetailEstimateDTO> )request.getSession().getAttribute("availableOtherMaterialsMap1");
		
		availableOtherMaterialsMap2 = (Map<String,DetailEstimateDTO> )request.getSession().getAttribute("availableOtherMaterialsMap2");
		
		availableOtherMaterialsMap3 = (Map<String,DetailEstimateDTO> )request.getSession().getAttribute("availableOtherMaterialsMap3");
		
		availableOtherMaterialsMap4 = (Map<String,DetailEstimateDTO> )request.getSession().getAttribute("availableOtherMaterialsMap4");
		
		availableOtherMaterialsMap5 = (Map<String,DetailEstimateDTO> )request.getSession().getAttribute("availableOtherMaterialsMap5");
		
		availableOtherMaterialsMap6 = (Map<String,DetailEstimateDTO> )request.getSession().getAttribute("availableOtherMaterialsMap6");
		
		availableOtherMaterialsMap7 = (Map<String,DetailEstimateDTO> )request.getSession().getAttribute("availableOtherMaterialsMap7");
		
		availableOtherMaterialsMap8 = (Map<String,DetailEstimateDTO> )request.getSession().getAttribute("availableOtherMaterialsMap8");
		
		availableOtherMaterialsMap9 = (Map<String,DetailEstimateDTO> )request.getSession().getAttribute("availableOtherMaterialsMap9");
		
		
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
		
		if(availableOtherMaterialsMap9 != null){
			
			availableOtherMaterialsMap9.clear();
		}
		if(selectedOtherMaterials != null){
			 
			selectedOtherMaterials.clear();
		}
		
		
		if(availableNPLMaterialsMap != null){ 
					
			availableNPLMaterialsMap.clear();
		}
		if(selectedNPLMaterials != null){
			
			selectedNPLMaterials.clear();
		}
		if(availableOtherResourcesMap != null){
			availableOtherResourcesMap.clear();
			
		}
		if(selectedOtherResources != null){
			selectedOtherResources.clear();
				
		}
		if(pegIdList != null){
			pegIdList.clear();
		}
			
		if(resourceMap != null){
			resourceMap.clear();
		}
		if(pegItemMap != null){
			pegItemMap.clear();
		}
		if(existingPegItemMap != null){
			existingPegItemMap.clear();
		}
		
		
		if(allSelectedAdditionalResoMap != null){
			allSelectedAdditionalResoMap.clear();
		}
		if(updatedDetailsMap != null){
			updatedDetailsMap.clear();
		}
		if(deletedDetailsMap != null){
			deletedDetailsMap.clear();
		}
		
		if(alreadyAddedDetails != null){
			alreadyAddedDetails.clear();
		}
		if(dto != null){
			dto = null;
		}
		if(alreadyAddedDmtDetails != null){
			alreadyAddedDmtDetails.clear();
		}
		
		
		request.getSession().setAttribute("detailEstimateDetailsMap1",availableOtherMaterialsMap1);
		
		request.getSession().setAttribute("detailEstimateDetailsMap2",availableOtherMaterialsMap2);
		request.getSession().setAttribute("detailEstimateDetailsMap3",availableOtherMaterialsMap3);
		
		request.getSession().setAttribute("detailEstimateDetailsMap4",availableOtherMaterialsMap4);
		request.getSession().setAttribute("detailEstimateDetailsMap5",availableOtherMaterialsMap5);
		request.getSession().setAttribute("detailEstimateDetailsMap6",availableOtherMaterialsMap6);
		request.getSession().setAttribute("detailEstimateDetailsMap7",availableOtherMaterialsMap7);
		request.getSession().setAttribute("detailEstimateDetailsMap8",availableOtherMaterialsMap8);
		request.getSession().setAttribute("detailEstimateDetailsMap9",availableOtherMaterialsMap9);
		
		request.getSession().setAttribute("selectedOtherMaterials",selectedOtherMaterials);
		
	
		request.getSession().setAttribute("availableNPLMaterialsMap",availableNPLMaterialsMap);
		request.getSession().setAttribute("selectedNPLMaterials",selectedNPLMaterials);
		
		
		request.getSession().setAttribute("availableOtherResourcesMap",availableOtherResourcesMap);
		request.getSession().setAttribute("selectedOtherResources",selectedOtherResources);

		
		
		request.getSession().setAttribute("detailEstimationDetails",resourceMap);
		request.getSession().setAttribute("selectedpegIdList",pegIdList);
		request.getSession().setAttribute("pegItemDetails",pegItemMap);
		request.getSession().setAttribute("allSelectedAdditionalResources",allSelectedAdditionalResoMap);
		request.getSession().setAttribute("updatedDetails",updatedDetailsMap);
		request.getSession().setAttribute("deletedDetails",deletedDetailsMap);
		request.getSession().setAttribute("alreadyAddedDetails",alreadyAddedDetails);
		request.getSession().setAttribute("estimateDetailobject",dto);
		request.getSession().setAttribute("alreadyAddedDmtDetails",alreadyAddedDmtDetails);
	}
	
	//public void loadAvailableOtherMaterialsWithoutAddedItem(HttpServletRequest request){
	@SuppressWarnings("unchecked")
	public void loadAvailableOtherMaterialsWithoutAddedItem(HttpServletRequest request){
		JSONObject packet =new JSONObject();
		JSONArray normsArray = new JSONArray();
		
		List<String> addedList = null;
		try {
			String sessionKey= (String) request.getSession().getAttribute("region");
			String costCenterNo=(String) request.getSession().getAttribute("costCenterNo");
			String warehouseId = request.getParameter("warehouseId");
						
			allSelectedAdditionalResoMap = (Map<String, DetailEstimateDTO>) request.getSession().getAttribute("allSelectedAdditionalResources");
			if(allSelectedAdditionalResoMap == null){
				allSelectedAdditionalResoMap = new HashMap<String, DetailEstimateDTO>();
			}
			addedList = populatePcestdtts(costCenterNo,new ArrayList<DetailEstimateDTO>(allSelectedAdditionalResoMap.values()));
			
			
			EstimateEjb estimateEjb = new EstimateEjb(sessionKey);
			
			if(warehouseId.equalsIgnoreCase("undefined") ||warehouseId == null || warehouseId.equalsIgnoreCase("-1") ){
				List<String> warehouses = estimateEjb.loadWarehouses(costCenterNo,sessionKey); //SEND_FOR_CONSTRUCTION_MAINTENANCE
				if(warehouses != null && warehouses.size() > 0){
					warehouseId = warehouses.get(0);
				}
				
			}
			
			
			List<DetailEstimateDTO> list = null;
		
			list = estimateEjb.getAvailableOtherMaterials(costCenterNo,warehouseId,addedList,sessionKey);
			System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% "+list);
			availableOtherMaterialsMap.clear();
			if(list != null && list.size() > 0){ 
				for(int i=0; i<=list.size()-1; i++){
		             
					 DetailEstimateDTO detailEstimateDTO = new DetailEstimateDTO();
					 detailEstimateDTO.setResourceCode(list.get(i).getResourceCode().trim());
					 detailEstimateDTO.setUom(list.get(i).getUom());
					 detailEstimateDTO.setResourceType(list.get(i).getResourceType());
					 detailEstimateDTO.setResourceName(list.get(i).getResourceName());
					 detailEstimateDTO.setUnitPrice(list.get(i).getUnitPrice());
					 detailEstimateDTO.setResCategory("1");
					 detailEstimateDTO.setResourceType("MAT-COST");
					 
					 availableOtherMaterialsMap.put(list.get(i).getResourceCode().trim(), detailEstimateDTO);
					     
					 
					 
		             
				}
			}
			selectedOtherMaterials =  (Map<String, DetailEstimateDTO>) request.getSession().getAttribute("selectedOtherMaterials");
			if(selectedOtherMaterials != null){
				List<DetailEstimateDTO> selectedEstimateDetaList = new ArrayList<DetailEstimateDTO>(selectedOtherMaterials.values());
				if(selectedEstimateDetaList != null){
					for(DetailEstimateDTO detailEstimateDTO : selectedEstimateDetaList){
						availableOtherMaterialsMap.remove(detailEstimateDTO.getResourceCode());
					}
				}
			}

			request.getSession().setAttribute("availableOtherMaterialsMap",availableOtherMaterialsMap);
			request.getSession().setAttribute("selectedOtherMaterials",selectedOtherMaterials);
			
		}catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
	
	public void loadAvailableOtherMaterials(HttpServletRequest request){
		JSONObject packet =new JSONObject();
		JSONArray normsArray = new JSONArray();
		List<String> addedList = null;
		
		try {
			String sessionKey= (String) request.getSession().getAttribute("region");
			String costCenterNo=(String) request.getSession().getAttribute("costCenterNo");
			String warehouseId = request.getParameter("warehouseId");
						
			EstimateEjb estimateEjb = new EstimateEjb(sessionKey);
			
			if(warehouseId.equalsIgnoreCase("undefined") ||warehouseId == null || warehouseId.equalsIgnoreCase("-1") ){
				List<String> warehouses = estimateEjb.loadWarehouses(costCenterNo,sessionKey); //SEND_FOR_CONSTRUCTION_MAINTENANCE
				if(warehouses != null && warehouses.size() > 0){
					warehouseId = warehouses.get(0);
				}
				
			}
			allSelectedAdditionalResoMap = (Map<String, DetailEstimateDTO>) request.getSession().getAttribute("allSelectedAdditionalResources");
			if(allSelectedAdditionalResoMap == null){
				allSelectedAdditionalResoMap = new HashMap<String, DetailEstimateDTO>();
			}
			addedList = populatePcestdtts(costCenterNo,new ArrayList<DetailEstimateDTO>(allSelectedAdditionalResoMap.values()));
			
			
			List<DetailEstimateDTO> list = null;
			list = estimateEjb.getAvailableOtherMaterials(costCenterNo,warehouseId,addedList,sessionKey);
			
			//list = estimateEjb.getAvailableOtherMaterials(costCenterNo,warehouseId,sessionKey);
			availableOtherMaterialsMap.clear();
			if(list != null && list.size() > 0){ 
				for(int i=0; i<=list.size()-1; i++){
		             
					 DetailEstimateDTO detailEstimateDTO = new DetailEstimateDTO();
					 detailEstimateDTO.setResourceCode(list.get(i).getResourceCode().trim());
					 detailEstimateDTO.setUom(list.get(i).getUom());
					 detailEstimateDTO.setResourceType(list.get(i).getResourceType());
					 detailEstimateDTO.setResourceName(list.get(i).getResourceName());
					 detailEstimateDTO.setUnitPrice(list.get(i).getUnitPrice());
					 detailEstimateDTO.setResCategory("1");
					 detailEstimateDTO.setResourceType("MAT-COST");
					 
					 availableOtherMaterialsMap.put(list.get(i).getResourceCode().trim(), detailEstimateDTO);
					     
					 
					 
		             
				}
			}
			selectedOtherMaterials =  (Map<String, DetailEstimateDTO>) request.getSession().getAttribute("selectedOtherMaterials");
			if(selectedOtherMaterials != null){
				List<DetailEstimateDTO> selectedEstimateDetaList = new ArrayList<DetailEstimateDTO>(selectedOtherMaterials.values());
				if(selectedEstimateDetaList != null){
					for(DetailEstimateDTO detailEstimateDTO : selectedEstimateDetaList){
						availableOtherMaterialsMap.remove(detailEstimateDTO.getResourceCode());
					}
				}
			}

			request.getSession().setAttribute("availableOtherMaterialsMap",availableOtherMaterialsMap);
			request.getSession().setAttribute("selectedOtherMaterials",selectedOtherMaterials);
			
		}catch (Exception e) {
			e.printStackTrace();
		}

	}
	
private List<String> populatePcestdtts(String costCenter,List<DetailEstimateDTO> detaList){
		
	 	
		List<String> lst = new ArrayList<String>();
		if(detaList != null){
			for(DetailEstimateDTO dto :detaList){
				String str = dto.getResourceCode();
				lst.add(str);
			}
		}
		
		
		
		return lst;
		
	}
	
	
	
	public JSONObject  showAvailableOtherMaterials(HttpServletRequest request) throws JSONException {
		
		JSONObject packet =new JSONObject();
		JSONArray otherMaterialsArray = new JSONArray();
		nf.setGroupingUsed(true);
		nf.setMaximumFractionDigits(2);
		nf.setMinimumFractionDigits(2);
		try {
			loadAvailableOtherMaterials(request);
			availableOtherMaterialsMap = (Map<String,DetailEstimateDTO> )request.getSession().getAttribute("availableOtherMaterialsMap");
			List<DetailEstimateDTO> detaList = null;
			if(availableOtherMaterialsMap != null){
				detaList = new ArrayList<DetailEstimateDTO>(availableOtherMaterialsMap.values());
			
				for(DetailEstimateDTO detailEstimateDTO :detaList ){
	
		            
		           JSONObject otherMaterialsRow =new JSONObject();
					 if(detailEstimateDTO != null){
						 
							 otherMaterialsRow.put("resourceCode", detailEstimateDTO.getResourceCode().trim());
					         otherMaterialsRow.put("uom", detailEstimateDTO.getUom());
					         otherMaterialsRow.put("resourceName",detailEstimateDTO.getResourceName());
					         otherMaterialsRow.put("unitPrice", nf.format(detailEstimateDTO.getUnitPrice())); 
					         otherMaterialsArray.put(otherMaterialsRow);
						
						
			 
				         
					 }
		          
		             
				}
				
				packet.put("otherMaterials", otherMaterialsArray);
			}else{
				packet.put("otherMaterials", otherMaterialsArray);
			}
			
		} catch (JSONException e) {
			
			packet.put("error", "Error in Load Norms");
			
		}catch (Exception e) {
			packet.put("error", "Error Occurred");
			e.printStackTrace();
		}

		return packet;

	}
	
	public void loadAvailableOtherMaterialsForMaster(HttpServletRequest request){ // this method is used to load all the materials in relevant warehouse in pegging master details page 
		JSONObject packet =new JSONObject();
		JSONArray normsArray = new JSONArray();
		try {
			String sessionKey= (String) request.getSession().getAttribute("region");
			String costCenterNo=(String) request.getSession().getAttribute("costCenterNo");
			String warehouseId = request.getParameter("warehouseId");
			

			EstimateEjb estimateEjb = new EstimateEjb(sessionKey);
			
			if((warehouseId != null && warehouseId.equalsIgnoreCase("undefined")) ||warehouseId == null || warehouseId.equalsIgnoreCase("-1") ){
				List<String> warehouses = estimateEjb.loadWarehouses(costCenterNo,sessionKey); 
				if(warehouses != null && warehouses.size() > 0){
					warehouseId = warehouses.get(0);
				}
				
			}
			
			
			List<DetailEstimateDTO> list = null;
		
			list = estimateEjb.getAvailableOtherMaterials(costCenterNo,warehouseId,sessionKey);
			availableOtherMaterialsMap.clear();
			if(list != null && list.size() > 0){ 
				for(int i=0; i<=list.size()-1; i++){
		
					 DetailEstimateDTO detailEstimateDTO = new DetailEstimateDTO();
					 detailEstimateDTO.setResourceCode(list.get(i).getResourceCode().trim());
					 detailEstimateDTO.setUom(list.get(i).getUom());
					 detailEstimateDTO.setResourceType(list.get(i).getResourceType());
					 detailEstimateDTO.setResourceName(list.get(i).getResourceName());
					 detailEstimateDTO.setUnitPrice(list.get(i).getUnitPrice());
					 availableOtherMaterialsMap.put(list.get(i).getResourceCode().trim(), detailEstimateDTO);
					 
				     if(i<=173){
				    	 availableOtherMaterialsMap1.put(list.get(i).getResourceCode().trim(), detailEstimateDTO);
						  
				     }
				     if(i > 176 && i <=300){
				    	 availableOtherMaterialsMap2.put(list.get(i).getResourceCode().trim(), detailEstimateDTO);
						  
				     }
				     if(i > 170 && i <=180){
				    	 availableOtherMaterialsMap3.put(list.get(i).getResourceCode().trim(), detailEstimateDTO);
						  
				     }
				     if(i > 300 && i <=400){
				    	 availableOtherMaterialsMap4.put(list.get(i).getResourceCode().trim(), detailEstimateDTO);
						  
				     }
				     if(i > 400 && i <=500){
				    	 availableOtherMaterialsMap5.put(list.get(i).getResourceCode().trim(), detailEstimateDTO);
						  
				     }
				     if(i > 500 && i <=600){
				    	 availableOtherMaterialsMap6.put(list.get(i).getResourceCode().trim(), detailEstimateDTO);
						  
				     }
				     if(i > 600 && i <=700){
				    	 availableOtherMaterialsMap7.put(list.get(i).getResourceCode().trim(), detailEstimateDTO);
						  
				     }
				     if(i > 700 && i <=800){
				    	 availableOtherMaterialsMap8.put(list.get(i).getResourceCode().trim(), detailEstimateDTO);
						  
				     }
				     if(i > 800 && i <=list.size()-1){
				    	 availableOtherMaterialsMap9.put(list.get(i).getResourceCode().trim(), detailEstimateDTO);
						  
				     }
		             
				}
			}
			
			request.getSession().setAttribute("availableOtherMaterialsMap",availableOtherMaterialsMap);
			request.getSession().setAttribute("availableOtherMaterialsMap1",availableOtherMaterialsMap1);
			request.getSession().setAttribute("availableOtherMaterialsMap2",availableOtherMaterialsMap2);
			request.getSession().setAttribute("availableOtherMaterialsMap3",availableOtherMaterialsMap3);
			request.getSession().setAttribute("availableOtherMaterialsMap4",availableOtherMaterialsMap4);
			request.getSession().setAttribute("availableOtherMaterialsMap5",availableOtherMaterialsMap5);
			request.getSession().setAttribute("availableOtherMaterialsMap6",availableOtherMaterialsMap6);
			request.getSession().setAttribute("availableOtherMaterialsMap7",availableOtherMaterialsMap7);
			request.getSession().setAttribute("availableOtherMaterialsMap8",availableOtherMaterialsMap8);
			request.getSession().setAttribute("availableOtherMaterialsMap9",availableOtherMaterialsMap9);
			//request.getSession().setAttribute("selectedOtherMaterials",selectedOtherMaterials);
			
		}catch (Exception e) {
			e.printStackTrace();
		}

	}
	 

public JSONObject  showAvailableOtherMaterialsLink1(HttpServletRequest request) throws JSONException {
	
		JSONObject packet =new JSONObject();
		JSONArray otherMaterialsArray = new JSONArray();
		nf.setGroupingUsed(true);
		nf.setMaximumFractionDigits(2);
		nf.setMinimumFractionDigits(2);
		try {
			//loadAvailableOtherMaterials(request);
			availableOtherMaterialsMap1 = (Map<String,DetailEstimateDTO> )request.getSession().getAttribute("availableOtherMaterialsMap1");
			if(availableOtherMaterialsMap1 != null){
				List<DetailEstimateDTO> detaList = new ArrayList<DetailEstimateDTO>(availableOtherMaterialsMap1.values());
				
				for(DetailEstimateDTO detailEstimateDTO :detaList ){

		            
		           JSONObject otherMaterialsRow =new JSONObject();
					 if(detailEstimateDTO != null){
						 //if(detailEstimateDTO.getResourceCode().equalsIgnoreCase("020201")){
							 otherMaterialsRow.put("resourceCode", detailEstimateDTO.getResourceCode().trim());
					         otherMaterialsRow.put("uom", detailEstimateDTO.getUom());
					         otherMaterialsRow.put("resourceName",detailEstimateDTO.getResourceName());
					         otherMaterialsRow.put("unitPrice", nf.format(detailEstimateDTO.getUnitPrice())); 
					         otherMaterialsArray.put(otherMaterialsRow);
						// }
						
			 
				         
					 }
		          
		             
				}
				
				packet.put("otherMaterials", otherMaterialsArray);
			}else{
				packet.put("otherMaterials", otherMaterialsArray);
			}
			
			
			//System.out.println("hi");
		} catch (JSONException e) {
			
			packet.put("error", "Error in Load Norms");
			
		}catch (Exception e) {
			packet.put("error", "Error Occurred");
			e.printStackTrace();
		}

		return packet;

	}
public JSONObject  showAvailableOtherMaterialsLink2(HttpServletRequest request) throws JSONException {
	
	JSONObject packet =new JSONObject();
	JSONArray otherMaterialsArray = new JSONArray();
	nf.setGroupingUsed(true);
	nf.setMaximumFractionDigits(2);
	nf.setMinimumFractionDigits(2);
	try {
		//loadAvailableOtherMaterials(request);
		availableOtherMaterialsMap2 = (Map<String,DetailEstimateDTO> )request.getSession().getAttribute("availableOtherMaterialsMap2");
		if(availableOtherMaterialsMap2 != null){
			List<DetailEstimateDTO> detaList = new ArrayList<DetailEstimateDTO>(availableOtherMaterialsMap2.values());
			
			for(DetailEstimateDTO detailEstimateDTO :detaList ){
	
	            
	           JSONObject otherMaterialsRow =new JSONObject();
				 if(detailEstimateDTO != null){
					 //if(detailEstimateDTO.getResourceCode().equalsIgnoreCase("020201")){
						 otherMaterialsRow.put("resourceCode", detailEstimateDTO.getResourceCode().trim());
				         otherMaterialsRow.put("uom", detailEstimateDTO.getUom());
				         otherMaterialsRow.put("resourceName",detailEstimateDTO.getResourceName());
				         otherMaterialsRow.put("unitPrice", nf.format(detailEstimateDTO.getUnitPrice())); 
				         otherMaterialsArray.put(otherMaterialsRow);
					// }
					
		 
			         
				 }
	          
	             
			}
			
			packet.put("otherMaterials", otherMaterialsArray);
		}else{
			packet.put("otherMaterials", otherMaterialsArray);
		}
		//System.out.println("hi");
	} catch (JSONException e) {
		
		packet.put("error", "Error in Load Norms");
		
	}catch (Exception e) {
		packet.put("error", "Error Occurred");
		e.printStackTrace();
	}

	return packet;

}
public JSONObject  showAvailableOtherMaterialsLink3(HttpServletRequest request) throws JSONException {
	
	JSONObject packet =new JSONObject();
	JSONArray otherMaterialsArray = new JSONArray();
	nf.setGroupingUsed(true);
	nf.setMaximumFractionDigits(2);
	nf.setMinimumFractionDigits(2);
	try {
		//loadAvailableOtherMaterials(request);
		availableOtherMaterialsMap3 = (Map<String,DetailEstimateDTO> )request.getSession().getAttribute("availableOtherMaterialsMap3");
		if(availableOtherMaterialsMap3 != null){
			List<DetailEstimateDTO> detaList = new ArrayList<DetailEstimateDTO>(availableOtherMaterialsMap3.values());
			
			for(DetailEstimateDTO detailEstimateDTO :detaList ){
	
	            
	           JSONObject otherMaterialsRow =new JSONObject();
				 if(detailEstimateDTO != null){
					 //if(detailEstimateDTO.getResourceCode().equalsIgnoreCase("020201")){
						 otherMaterialsRow.put("resourceCode", detailEstimateDTO.getResourceCode().trim());
				         otherMaterialsRow.put("uom", detailEstimateDTO.getUom());
				         otherMaterialsRow.put("resourceName",detailEstimateDTO.getResourceName());
				         otherMaterialsRow.put("unitPrice", nf.format(detailEstimateDTO.getUnitPrice())); 
				         otherMaterialsArray.put(otherMaterialsRow);
					// }
					
		 
			         
				 }
	          
	             
			}
			
			packet.put("otherMaterials", otherMaterialsArray);
		}else{
			packet.put("otherMaterials", otherMaterialsArray);
		}
		
		//System.out.println("hi");
	} catch (JSONException e) {
		
		packet.put("error", "Error in Load Norms");
		
	}catch (Exception e) {
		packet.put("error", "Error Occurred");
		e.printStackTrace();
	}

	return packet;

}
public JSONObject  showAvailableOtherMaterialsLink4(HttpServletRequest request) throws JSONException {
	
	JSONObject packet =new JSONObject();
	JSONArray otherMaterialsArray = new JSONArray();
	nf.setGroupingUsed(true);
	nf.setMaximumFractionDigits(2);
	nf.setMinimumFractionDigits(2);
	try {
		//loadAvailableOtherMaterials(request);
		availableOtherMaterialsMap4 = (Map<String,DetailEstimateDTO> )request.getSession().getAttribute("availableOtherMaterialsMap4");
		if(availableOtherMaterialsMap4 != null){
			List<DetailEstimateDTO> detaList = new ArrayList<DetailEstimateDTO>(availableOtherMaterialsMap4.values());
			
			for(DetailEstimateDTO detailEstimateDTO :detaList ){
	
	            
	           JSONObject otherMaterialsRow =new JSONObject();
				 if(detailEstimateDTO != null){
					 //if(detailEstimateDTO.getResourceCode().equalsIgnoreCase("020201")){
						 otherMaterialsRow.put("resourceCode", detailEstimateDTO.getResourceCode().trim());
				         otherMaterialsRow.put("uom", detailEstimateDTO.getUom());
				         otherMaterialsRow.put("resourceName",detailEstimateDTO.getResourceName());
				         otherMaterialsRow.put("unitPrice", nf.format(detailEstimateDTO.getUnitPrice())); 
				         otherMaterialsArray.put(otherMaterialsRow);
					// }
					
		 
			         
				 }
	          
	             
			}
			
			packet.put("otherMaterials", otherMaterialsArray);
		}else{
			packet.put("otherMaterials", otherMaterialsArray);
		}
		//System.out.println("hi");
	} catch (JSONException e) {
		
		packet.put("error", "Error in Load Norms");
		
	}catch (Exception e) {
		packet.put("error", "Error Occurred");
		e.printStackTrace();
	}

	return packet;

}
public JSONObject  showAvailableOtherMaterialsLink5(HttpServletRequest request) throws JSONException {
	
	JSONObject packet =new JSONObject();
	JSONArray otherMaterialsArray = new JSONArray();
	nf.setGroupingUsed(true);
	nf.setMaximumFractionDigits(2);
	nf.setMinimumFractionDigits(2);
	try {
		//loadAvailableOtherMaterials(request);
		availableOtherMaterialsMap5 = (Map<String,DetailEstimateDTO> )request.getSession().getAttribute("availableOtherMaterialsMap5");
		if(availableOtherMaterialsMap5 != null){
			List<DetailEstimateDTO> detaList = new ArrayList<DetailEstimateDTO>(availableOtherMaterialsMap5.values());
			
			for(DetailEstimateDTO detailEstimateDTO :detaList ){
	
	            
	           JSONObject otherMaterialsRow =new JSONObject();
				 if(detailEstimateDTO != null){
					 //if(detailEstimateDTO.getResourceCode().equalsIgnoreCase("020201")){
						 otherMaterialsRow.put("resourceCode", detailEstimateDTO.getResourceCode().trim());
				         otherMaterialsRow.put("uom", detailEstimateDTO.getUom());
				         otherMaterialsRow.put("resourceName",detailEstimateDTO.getResourceName());
				         otherMaterialsRow.put("unitPrice", nf.format(detailEstimateDTO.getUnitPrice())); 
				         otherMaterialsArray.put(otherMaterialsRow);
					// }
					
		 
			         
				 }
	          
	             
			}
			
			packet.put("otherMaterials", otherMaterialsArray);
		}else{
			packet.put("otherMaterials", otherMaterialsArray);
		}
		//System.out.println("hi");
	} catch (JSONException e) {
		
		packet.put("error", "Error in Load Norms");
		
	}catch (Exception e) {
		packet.put("error", "Error Occurred");
		e.printStackTrace();
	}

	return packet;

}
public JSONObject  showAvailableOtherMaterialsLink6(HttpServletRequest request) throws JSONException {
	
	JSONObject packet =new JSONObject();
	JSONArray otherMaterialsArray = new JSONArray();
	nf.setGroupingUsed(true);
	nf.setMaximumFractionDigits(2);
	nf.setMinimumFractionDigits(2);
	try {
		//loadAvailableOtherMaterials(request);
		availableOtherMaterialsMap6 = (Map<String,DetailEstimateDTO> )request.getSession().getAttribute("availableOtherMaterialsMap6");
		if(availableOtherMaterialsMap6 != null){
			List<DetailEstimateDTO> detaList = new ArrayList<DetailEstimateDTO>(availableOtherMaterialsMap6.values());
			
			for(DetailEstimateDTO detailEstimateDTO :detaList ){
	
	            
	           JSONObject otherMaterialsRow =new JSONObject();
				 if(detailEstimateDTO != null){
					 //if(detailEstimateDTO.getResourceCode().equalsIgnoreCase("020201")){
						 otherMaterialsRow.put("resourceCode", detailEstimateDTO.getResourceCode().trim());
				         otherMaterialsRow.put("uom", detailEstimateDTO.getUom());
				         otherMaterialsRow.put("resourceName",detailEstimateDTO.getResourceName());
				         otherMaterialsRow.put("unitPrice", nf.format(detailEstimateDTO.getUnitPrice())); 
				         otherMaterialsArray.put(otherMaterialsRow);
					// }
					
		 
			         
				 }
	          
	             
			}
			
			packet.put("otherMaterials", otherMaterialsArray);
		}else{
			packet.put("otherMaterials", otherMaterialsArray);
		}
		//System.out.println("hi");
	} catch (JSONException e) {
		
		packet.put("error", "Error in Load Norms");
		
	}catch (Exception e) {
		packet.put("error", "Error Occurred");
		e.printStackTrace();
	}

	return packet;

}
public JSONObject  showAvailableOtherMaterialsLink7(HttpServletRequest request) throws JSONException {
	
	JSONObject packet =new JSONObject();
	JSONArray otherMaterialsArray = new JSONArray();
	nf.setGroupingUsed(true);
	nf.setMaximumFractionDigits(2);
	nf.setMinimumFractionDigits(2);
	try {
		//loadAvailableOtherMaterials(request);
		availableOtherMaterialsMap7 = (Map<String,DetailEstimateDTO> )request.getSession().getAttribute("availableOtherMaterialsMap7");
		if(availableOtherMaterialsMap7 != null){
			List<DetailEstimateDTO> detaList = new ArrayList<DetailEstimateDTO>(availableOtherMaterialsMap7.values());
			
			for(DetailEstimateDTO detailEstimateDTO :detaList ){
	
	            
	           JSONObject otherMaterialsRow =new JSONObject();
				 if(detailEstimateDTO != null){
					 //if(detailEstimateDTO.getResourceCode().equalsIgnoreCase("020201")){
						 otherMaterialsRow.put("resourceCode", detailEstimateDTO.getResourceCode().trim());
				         otherMaterialsRow.put("uom", detailEstimateDTO.getUom());
				         otherMaterialsRow.put("resourceName",detailEstimateDTO.getResourceName());
				         otherMaterialsRow.put("unitPrice", nf.format(detailEstimateDTO.getUnitPrice())); 
				         otherMaterialsArray.put(otherMaterialsRow);
					// }
					
		 
			         
				 }
	          
	             
			}
			
			packet.put("otherMaterials", otherMaterialsArray);
		}else{
			packet.put("otherMaterials", otherMaterialsArray);
		}
		//System.out.println("hi");
	} catch (JSONException e) {
		
		packet.put("error", "Error in Load Norms");
		
	}catch (Exception e) {
		packet.put("error", "Error Occurred");
		e.printStackTrace();
	}

	return packet;

}
public JSONObject  showAvailableOtherMaterialsLink8(HttpServletRequest request) throws JSONException {
	
	JSONObject packet =new JSONObject();
	JSONArray otherMaterialsArray = new JSONArray();
	nf.setGroupingUsed(true);
	nf.setMaximumFractionDigits(2);
	nf.setMinimumFractionDigits(2);
	try {
		//loadAvailableOtherMaterials(request);
		availableOtherMaterialsMap8 = (Map<String,DetailEstimateDTO> )request.getSession().getAttribute("availableOtherMaterialsMap8");
		if(availableOtherMaterialsMap8 != null){
			List<DetailEstimateDTO> detaList = new ArrayList<DetailEstimateDTO>(availableOtherMaterialsMap8.values());
			
			for(DetailEstimateDTO detailEstimateDTO :detaList ){
	
	            
	           JSONObject otherMaterialsRow =new JSONObject();
				 if(detailEstimateDTO != null){
					 //if(detailEstimateDTO.getResourceCode().equalsIgnoreCase("020201")){
						 otherMaterialsRow.put("resourceCode", detailEstimateDTO.getResourceCode().trim());
				         otherMaterialsRow.put("uom", detailEstimateDTO.getUom());
				         otherMaterialsRow.put("resourceName",detailEstimateDTO.getResourceName());
				         otherMaterialsRow.put("unitPrice", nf.format(detailEstimateDTO.getUnitPrice())); 
				         otherMaterialsArray.put(otherMaterialsRow);
					// }
					
		 
			         
				 }
	          
	             
			}
			
			packet.put("otherMaterials", otherMaterialsArray);
		}else{
			packet.put("otherMaterials", otherMaterialsArray);
		}
		//System.out.println("hi");
	} catch (JSONException e) {
		
		packet.put("error", "Error in Load Norms");
		
	}catch (Exception e) {
		packet.put("error", "Error Occurred");
		e.printStackTrace();
	}

	return packet;

}public JSONObject  showAvailableOtherMaterialsLink9(HttpServletRequest request) throws JSONException {
	
	JSONObject packet =new JSONObject();
	JSONArray otherMaterialsArray = new JSONArray();
	nf.setGroupingUsed(true);
	nf.setMaximumFractionDigits(2);
	nf.setMinimumFractionDigits(2);
	try {
		//loadAvailableOtherMaterials(request);
		availableOtherMaterialsMap9 = (Map<String,DetailEstimateDTO> )request.getSession().getAttribute("availableOtherMaterialsMap9");
		if(availableOtherMaterialsMap9 != null){
			List<DetailEstimateDTO> detaList = new ArrayList<DetailEstimateDTO>(availableOtherMaterialsMap9.values());
			
			for(DetailEstimateDTO detailEstimateDTO :detaList ){
	
	            
	           JSONObject otherMaterialsRow =new JSONObject();
				 if(detailEstimateDTO != null){
					 //if(detailEstimateDTO.getResourceCode().equalsIgnoreCase("020201")){
						 otherMaterialsRow.put("resourceCode", detailEstimateDTO.getResourceCode().trim());
				         otherMaterialsRow.put("uom", detailEstimateDTO.getUom());
				         otherMaterialsRow.put("resourceName",detailEstimateDTO.getResourceName());
				         otherMaterialsRow.put("unitPrice", nf.format(detailEstimateDTO.getUnitPrice())); 
				         otherMaterialsArray.put(otherMaterialsRow);
					// }
					
		 
			         
				 }
	          
	             
			}
			
			packet.put("otherMaterials", otherMaterialsArray);
		}else{
			packet.put("otherMaterials", otherMaterialsArray);
		}
		//System.out.println("hi");
	} catch (JSONException e) {
		
		packet.put("error", "Error in Load Norms");
		
	}catch (Exception e) {
		packet.put("error", "Error Occurred");
		e.printStackTrace();
	}

	return packet;

}
	public void loadOtherResources(HttpServletRequest request){
		JSONObject packet =new JSONObject();
		JSONArray normsArray = new JSONArray();
		try {
			String sessionKey= (String) request.getSession().getAttribute("region");
			String costCenterNo=(String) request.getSession().getAttribute("costCenterNo");
			String warehouseId = request.getParameter("warehouseId");
			
			EstimateEjb estimateEjb = new EstimateEjb(sessionKey);
			if(warehouseId != null && warehouseId.equalsIgnoreCase("") ||warehouseId == null || warehouseId.equalsIgnoreCase("-1")){
				List<String> warehouses = estimateEjb.loadWarehouses(costCenterNo,sessionKey); //SEND_FOR_CONSTRUCTION_MAINTENANCE
				if(warehouses != null && warehouses.size() > 0){
					warehouseId = warehouses.get(0);
				}
				
			}
			
			List<DetailEstimateDTO> list = null;
		
			list = estimateEjb.getOtherResources(costCenterNo,sessionKey);
			availableOtherResourcesMap.clear();
			if(list != null && list.size() > 0){ 
				for(int i=0; i<=list.size()-1; i++){
		
					 DetailEstimateDTO detailEstimateDTO = new DetailEstimateDTO();
					 detailEstimateDTO.setResourceType(list.get(i).getResourceType());
					 detailEstimateDTO.setResourceName(list.get(i).getResourceName());
					 detailEstimateDTO.setResourceType(list.get(i).getResourceType());
					 detailEstimateDTO.setResourceCode(list.get(i).getResourceCode());
					 detailEstimateDTO.setUom(list.get(i).getUom());
					 detailEstimateDTO.setUnitPrice(list.get(i).getUnitPrice());
					 detailEstimateDTO.setResCategory("2");
					 availableOtherResourcesMap.put(list.get(i).getResourceCode().trim(), detailEstimateDTO);
	     
		             
				}
			}
			selectedOtherResources =  (Map<String, DetailEstimateDTO>) request.getSession().getAttribute("selectedOtherResources");
			if(selectedOtherResources != null){
				List<DetailEstimateDTO> selectedEstimateDetaList = new ArrayList<DetailEstimateDTO>(selectedOtherResources.values());
				if(selectedEstimateDetaList != null){
					for(DetailEstimateDTO detailEstimateDTO : selectedEstimateDetaList){
						availableOtherResourcesMap.remove(detailEstimateDTO.getResourceCode());
					}
				}
			}

			request.getSession().setAttribute("availableOtherResourcesMap",availableOtherResourcesMap);
			request.getSession().setAttribute("selectedOtherResources",selectedOtherResources);
			
		}catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
	
	public JSONObject  showOtherResources(HttpServletRequest request) throws JSONException {
		
		JSONObject packet =new JSONObject();
		JSONArray otherMaterialsArray = new JSONArray();
		nf.setGroupingUsed(true);
		nf.setMaximumFractionDigits(2);
		nf.setMinimumFractionDigits(2);
		try {
			loadOtherResources(request);
			availableOtherResourcesMap = (Map<String,DetailEstimateDTO> )request.getSession().getAttribute("availableOtherResourcesMap");
			if(availableOtherResourcesMap != null){
				List<DetailEstimateDTO> detaList = new ArrayList<DetailEstimateDTO>(availableOtherResourcesMap.values());
				
				for(DetailEstimateDTO detailEstimateDTO :detaList ){
	
		            
		           JSONObject otherResourcesRow =new JSONObject();
					 
		           otherResourcesRow.put("resourceCode", detailEstimateDTO.getResourceCode());
		           otherResourcesRow.put("resourceType", detailEstimateDTO.getResourceType());
		           otherResourcesRow.put("resourceName",detailEstimateDTO.getResourceName());
		           otherResourcesRow.put("unitPrice",nf.format(detailEstimateDTO.getUnitPrice()));
		           otherResourcesRow.put("uom",detailEstimateDTO.getUom());
		           otherResourcesRow.put("estimatedCost","");
		           otherMaterialsArray.put(otherResourcesRow);
		             
				}
				packet.put("otherResources", otherMaterialsArray);
			}else{
				packet.put("otherResources", otherMaterialsArray);
			}
			
			
			//System.out.println("hi");
		} catch (JSONException e) {
			
			packet.put("error", "Error in Load Norms");
			
		}catch (Exception e) {
			packet.put("error", "Error Occurred");
			e.printStackTrace();
		}

		return packet;

	}
	
	public void loadNPLMaterials(HttpServletRequest request){
		JSONObject packet =new JSONObject();
		JSONArray normsArray = new JSONArray();
		try {
			String sessionKey= (String) request.getSession().getAttribute("region");
			String costCenterNo=(String) request.getSession().getAttribute("costCenterNo");
			String warehouseId = request.getParameter("warehouseId");
			
			EstimateEjb estimateEjb = new EstimateEjb(sessionKey);
			
			List<DetailEstimateDTO> list = null;
			
			if((warehouseId != null && warehouseId.equalsIgnoreCase("undefined")) ||warehouseId == null || warehouseId.equalsIgnoreCase("-1") ){
				List<String> warehouses = estimateEjb.loadWarehouses(costCenterNo,sessionKey); //SEND_FOR_CONSTRUCTION_MAINTENANCE
				if(warehouses != null && warehouses.size() > 0){
					warehouseId = warehouses.get(0);
				}
				
			}
			
			list = estimateEjb.getNPLMaterials(warehouseId,sessionKey);
			availableNPLMaterialsMap.clear();
			if(list != null && list.size() > 0){ 
				for(int i=0; i<=list.size()-1; i++){
		
					 DetailEstimateDTO detailEstimateDTO = new DetailEstimateDTO();
					 detailEstimateDTO.setResourceCode(list.get(i).getResourceCode());
					 detailEstimateDTO.setUom(list.get(i).getUom());
					 detailEstimateDTO.setResourceType("MAT-COST-OTHER");
					 detailEstimateDTO.setResourceName(list.get(i).getResourceName());
					 detailEstimateDTO.setUnitPrice(list.get(i).getUnitPrice());
					 detailEstimateDTO.setResCategory(Constants.RESOURCE_CATEGORY.toString());
					 availableNPLMaterialsMap.put(list.get(i).getResourceCode().trim(), detailEstimateDTO);
	     
		             
				}
			}
			selectedNPLMaterials =  (Map<String, DetailEstimateDTO>) request.getSession().getAttribute("selectedNPLMaterials");
			if(selectedNPLMaterials != null){
				List<DetailEstimateDTO> selectedEstimateDetaList = new ArrayList<DetailEstimateDTO>(selectedNPLMaterials.values());
				if(selectedEstimateDetaList != null){
					for(DetailEstimateDTO detailEstimateDTO : selectedEstimateDetaList){
						availableNPLMaterialsMap.remove(detailEstimateDTO.getResourceCode());
					}
				}
			}

			request.getSession().setAttribute("availableNPLMaterialsMap",availableNPLMaterialsMap);
			request.getSession().setAttribute("selectedNPLMaterials",selectedNPLMaterials);
			
		}catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
	
	public JSONObject  showNPLMaterials(HttpServletRequest request) throws JSONException {
		
		JSONObject packet =new JSONObject();
		JSONArray otherNPLRowArray = new JSONArray();
		nf.setGroupingUsed(true);
		nf.setMaximumFractionDigits(2);
		nf.setMinimumFractionDigits(2);
		try {
			loadNPLMaterials(request);
			availableNPLMaterialsMap = (Map<String,DetailEstimateDTO> )request.getSession().getAttribute("availableNPLMaterialsMap");
			if(availableNPLMaterialsMap != null){
				List<DetailEstimateDTO> detaList = new ArrayList<DetailEstimateDTO>(availableNPLMaterialsMap.values());
				
				for(DetailEstimateDTO detailEstimateDTO :detaList ){
	
		            
		           JSONObject otherNPLRow =new JSONObject();
					 
		           otherNPLRow.put("resourceCode", detailEstimateDTO.getResourceCode());
		           otherNPLRow.put("uom", detailEstimateDTO.getUom());
		           otherNPLRow.put("resourceName",detailEstimateDTO.getResourceName());
		           otherNPLRow.put("unitPrice", nf.format(detailEstimateDTO.getUnitPrice()));
		           otherNPLRowArray.put(otherNPLRow);
		             
				}
				
				packet.put("otherNPLMaterials", otherNPLRowArray);
			}else{
				packet.put("otherNPLMaterials", otherNPLRowArray);
			}
			//System.out.println("hi");
		} catch (JSONException e) {
			
			packet.put("error", "Error in Load Norms");
			
		}catch (Exception e) {
			packet.put("error", "Error Occurred");
			e.printStackTrace();
		}

		return packet;

	}
	/*public JSONObject  addNorms(HttpServletRequest request) throws JSONException {
		
		JSONObject packet =new JSONObject();
		JSONArray normsArray = new JSONArray();
		
		norms = (Map<String, Norm>) request.getSession().getAttribute("normsMap");
		selectedNorms =  (Map<String, Norm>) request.getSession().getAttribute("selectedNorms");
		
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
					
				normsArray.put(normDataRow);
				
				
				if(selectedNorms == null){
					selectedNorms = new HashMap<String, Norm>();
				}
				
				
				if(func.equalsIgnoreCase("add")){
					selectedNorms.put(id, norm);
				}else if(func.equalsIgnoreCase("delete")){
					selectedNorms.remove(id);
					deletedNorms.put(id, norm);
				}

			}
			request.getSession().setAttribute("selectedNorms",selectedNorms);
			request.getSession().setAttribute("deletedNorms",deletedNorms);
			packet.put("addednorms", normsArray);
			
		} catch (JSONException e) {
			packet.put("error", "Error Occurred");
			e.printStackTrace();
		}

		return packet;

	}*/
	
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
	public JSONObject  loadApplicationRefNumbers(HttpServletRequest request) {
		
		JSONArray applicationRefNumbers = new JSONArray();

		JSONObject objectlist = new JSONObject();

		List<String> applicationNos  = (List<String>) request.getSession().getAttribute("applicationRefNos");
		try {
			
		  for (String applicationNo : applicationNos) {
			  	JSONObject appliNoJson = new JSONObject();
				
				appliNoJson.put("id", applicationNo);
				appliNoJson.put("name", applicationNo);
				
	        	applicationRefNumbers.put(appliNoJson);
	       }
		  	
	       objectlist.put("jsonarrayapplicationrefs", applicationRefNumbers);

		} catch (JSONException e) {
			
			e.printStackTrace();
		}

		return objectlist;

	}
public JSONObject  loadEstmationRefNumbers(HttpServletRequest request) {
		
		JSONArray applicationRefNumbers = new JSONArray();

		JSONObject objectlist = new JSONObject();

		List<String> applicationNos  = (List<String>) request.getSession().getAttribute("estimationRefNos");
		try {
			
		  for (String applicationNo : applicationNos) {
			  	JSONObject appliNoJson = new JSONObject();
				
				appliNoJson.put("id", applicationNo);
				appliNoJson.put("name", applicationNo);
				
	        	applicationRefNumbers.put(appliNoJson);
	       }
		  	
	       objectlist.put("jsonarrayapplicationrefs", applicationRefNumbers);

		} catch (JSONException e) {
			
			e.printStackTrace();
		}

		return objectlist;

	}
public AjaxResponse  loadPegInfo(HttpServletRequest request) throws JSONException{
	String costCenterNo=(String) request.getSession().getAttribute("costCenterNo");
	String node = request.getParameter("node");
	String masterId = request.getParameter("masterId");
	masterId="2";  //to load relevant pegging masters 
	if(costCenterNo != null && (costCenterNo.equalsIgnoreCase("530.20") || costCenterNo.equalsIgnoreCase("530.80"))){
		masterId="1";
	}
	if(costCenterNo != null && (costCenterNo.equalsIgnoreCase("430.20") || costCenterNo.equalsIgnoreCase("430.25") || costCenterNo.equalsIgnoreCase("430.30") || costCenterNo.equalsIgnoreCase("430.35"))){
		masterId="4";
	}
	if(node.equals("source")){
		node = null;
	}
	return new AjaxResponse(AjaxResponse.TYPE_JSON, setHierarchyLinks(node,masterId,request));

}
/*
public JSONObject  loadApplicationDetails(HttpServletRequest request) throws JSONException{
	JSONObject packet =new JSONObject();

	selectedOtherMaterials = (Map<String,DetailEstimateDTO >) request.getSession().getAttribute("selectedNorms");
	
	JSONArray normsArray = new JSONArray();
	try{
		
		String sessionKey= (String) request.getSession().getAttribute("region");
		String applicationNo = request.getParameter("applicationNo");
		
		ApplicationEjb applicationEjb = new ApplicationEjb(sessionKey);
		
		ApplicantEjb applicantEjb = new ApplicantEjb(sessionKey);
		
		WiringLandDetailEjb wiringLandDetailEjb= new WiringLandDetailEjb(sessionKey);
		
		Application application = applicationEjb.findByApplicationNo(applicationNo);
		
		if (application!=null){
			Applicant  applicant=applicantEjb.findById(application.getIdNo());
			if (applicant!=null){
				WiringLandDetail  wiringLandDetail=wiringLandDetailEjb.findByApplicationNo(applicationNo);
				if (wiringLandDetail!=null){
					packet.put("powerToSupply", wiringLandDetail.getServiceStreetAddress());
					packet.put("nameAndAddress", applicant.getStreetAddress());
					packet.put("demand", wiringLandDetail.getDemand());
				}
			}
		}
		
		EstimateEjb estimateEjb = new EstimateEjb(sessionKey); 
		
		Spstdesthmt appliDetails = estimateEjb.loadEstimateByApplicationNo(applicationNo, sessionKey);
		if(appliDetails != null){
			packet.put("secDeposit", appliDetails.getSecDeposit());
			packet.put("sinNo", appliDetails.getSinNo());
			packet.put("totalCost", appliDetails.getTotalCost());
			packet.put("jobDescription", appliDetails.getDescription());
			packet.put("rejectedReason", appliDetails.getRejReason());
			//packet.put("demand", "500.00");
			if(application.getApplicationType().equalsIgnoreCase(Constants.BULK)){
				//to do disable enable button
			}else if(application.getApplicationType().equalsIgnoreCase(Constants.LAND)){
				//to do disable enable button
			}
		}
		
		
		
		
		List<Spstdestdmt> normsList = estimateEjb.loadEstimateDetailsByApplicaNo(applicationNo, sessionKey);
		if(normsList != null && normsList.size() != 0){
			packet.put("disableSave", true);
			for(Spstdestdmt spstdestdmt : normsList){
				
				JSONObject normDataRow =new JSONObject();


				normDataRow.put("linesectionid", spstdestdmt.getId().getLineType());
				normDataRow.put("uom", spstdestdmt.getUom());
				normDataRow.put("standardcost", spstdestdmt.getLineCost());
				normDataRow.put("description", spstdestdmt.getLineDescription());
				normDataRow.put("quantity", spstdestdmt.getLength());
				normDataRow.put("totalcost", spstdestdmt.getEstmateCost());
				normsArray.put(normDataRow);


				if(	selectedOtherMaterials == null){
					selectedOtherMaterials = new HashMap<String, DetailEstimateDTO>();
				}
				
				selectedOtherMaterials.put(spstdestdmt.getId().getLineType(), populateNorm(spstdestdmt));

			}
		}else{
			packet.put("disableSave", false);
		}
		request.getSession().setAttribute("selectedNorms",	selectedOtherMaterials);
		
		packet.put("addednorms", normsArray);

	}catch(Exception e){
		
	}
	return packet;

}

private Norm populateNorm(Spstdestdmt spstdestdmt){
	
	Norm norm = new Norm();
	norm.setLineSectionTypeId(spstdestdmt.getId().getLineType());
	norm.setDescription(spstdestdmt.getLineDescription());
	
	norm.setEstimatedCost(spstdestdmt.getEstmateCost());
	norm.setQuantity(spstdestdmt.getLength());
	norm.setStandardCost(spstdestdmt.getLineCost());
	norm.setUom(spstdestdmt.getUom());
	return norm;
	
}*/
private Norm populateSpstdestdmt(Norm norm){

	
	Spstdestdmt spstdestdmt = new Spstdestdmt();

	spstdestdmt.setLength(norm.getQuantity());
	
	spstdestdmt.setUom(norm.getUom());
	spstdestdmt.setLineCost(norm.getStandardCost());
	spstdestdmt.setLineDescription(norm.getDescription());
	spstdestdmt.setEstmateCost(norm.getEstimatedCost());
	return norm;
	
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
/*
public void getEstimatedCostDetails(HttpServletRequest request){
	
	String quantity = request.getParameter("quantity");
	
	String lineId = request.getParameter("lineid");
	String estimatedCost = request.getParameter("linecost");
	
	selectedNorms =  (Map<String, Norm>) request.getSession().getAttribute("selectedNorms");
	
	Norm addedNorm = selectedNorms.get(lineId);
	if(addedNorm != null){
		addedNorm.setEstimatedCost(new BigDecimal(estimatedCost));
		addedNorm.setQuantity(new BigDecimal(quantity));
	}

	request.getSession().setAttribute("selectedNorms",selectedNorms);
}
public JSONObject deleteLineDetails(HttpServletRequest request){
	JSONObject packet =new JSONObject();
	String quantity = request.getParameter("quantity");
	String applicationRefNo = request.getParameter("applicationRefNo");
	String lineId = request.getParameter("lineid");
	String estimatedCost = request.getParameter("linecost");
	
	//deletedNorms =  (Map<String, Norm>) request.getSession().getAttribute("deletedNorms");
	
	String sessionKey= (String) request.getSession().getAttribute("region");
	EstimateEjb estimateEjb = new EstimateEjb(sessionKey); 
	
	
	//Norm deletedNorm = deletedNorms.get(lineId);
	//if(deletedNorm != null){
		Spstdestdmt spstdestdmt = new Spstdestdmt();
		SpstdestdmtPK id = new SpstdestdmtPK();
		id.setApplicationNo(applicationRefNo);
		id.setLineType(lineId);
		id.setStdNo(applicationRefNo);
		spstdestdmt.setId(id);
		estimateEjb.removeLineDetails(spstdestdmt, sessionKey);
	//}
		try {
			//if(status == 1){
				packet.put("success","Successfully Updated");
			//}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return packet;
	}
	
	//request.getSession().setAttribute("deletedNorms",deletedNorms);
public JSONObject insertLineDetails(HttpServletRequest request){
	JSONObject packet =new JSONObject();
	String quantity = request.getParameter("quantity");
	String applicationRefNo = request.getParameter("applicationRefNo");
	String lineId = request.getParameter("lineid");
	String estimatedCost = request.getParameter("linecost");
	
	//deletedNorms =  (Map<String, Norm>) request.getSession().getAttribute("deletedNorms");
	
	String sessionKey= (String) request.getSession().getAttribute("region");
	EstimateEjb estimateEjb = new EstimateEjb(sessionKey); 
	
	
	//Norm deletedNorm = deletedNorms.get(lineId);
	//if(deletedNorm != null){
		Spstdestdmt spstdestdmt = new Spstdestdmt();
		SpstdestdmtPK id = new SpstdestdmtPK();
		id.setApplicationNo(applicationRefNo);
		id.setLineType(lineId);
		id.setStdNo(applicationRefNo);
		spstdestdmt.setId(id);
		estimateEjb.removeLineDetails(spstdestdmt, sessionKey);
	//}
		try {
			//if(status == 1){
				packet.put("success","Successfully Updated");
			//}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return packet;
	}
public JSONObject updateLineDetails(HttpServletRequest request){
	JSONObject packet =new JSONObject();
	String quantity = request.getParameter("quantity");
	String func = request.getParameter("func");
	String applicationRefNo = request.getParameter("applicationRefNo");
	String lineId = request.getParameter("lineid");
	String estimatedCost = request.getParameter("estimatedCost");
	
	selectedNorms =  (Map<String, Norm>) request.getSession().getAttribute("selectedNorms");
	
	String sessionKey= (String) request.getSession().getAttribute("region");
	String costCenterNo=(String) request.getSession().getAttribute("costCenterNo");
	
	EstimateEjb estimateEjb = new EstimateEjb(sessionKey); 
	
	
	Norm updatedNorm = selectedNorms.get(lineId);
	if(updatedNorm != null){
		Spstdestdmt spstdestdmt = new Spstdestdmt();
		SpstdestdmtPK id = new SpstdestdmtPK();
		id.setApplicationNo(applicationRefNo);
		id.setLineType(lineId);
		id.setStdNo(applicationRefNo);
		spstdestdmt.setId(id);
		spstdestdmt.setDeptId(costCenterNo);
		spstdestdmt.setLength(new BigDecimal( quantity));
		spstdestdmt.setEstmateCost(new BigDecimal( estimatedCost));

		
		spstdestdmt.setUom(updatedNorm.getUom());
		spstdestdmt.setLineCost(updatedNorm.getStandardCost());
		spstdestdmt.setLineDescription(updatedNorm.getDescription());
		
		int status = 0;
		if(func != null && func.equalsIgnoreCase("update")){
			status = estimateEjb.updateLineDetails(spstdestdmt, sessionKey);
		}else if(func != null && func.equalsIgnoreCase("save")){
			estimateEjb.insertLineDetails(spstdestdmt, sessionKey);
		}
		
		try {
			//if(status == 1){
				packet.put("success","Successfully Updated");
			//}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	return packet;
	///request.getSession().setAttribute("deletedNorms",deletedNorms);
}
public JSONObject setAddSubstationCost(HttpServletRequest request){
	JSONObject packet =new JSONObject();
	String demand = request.getParameter("demand");
	
	String applicationNo = request.getParameter("applicationNo");
	
	String sessionKey= (String) request.getSession().getAttribute("region");
	String costCenterNo=(String) request.getSession().getAttribute("costCenterNo");
	try {
		ApplicationEjb applicationEjb = new ApplicationEjb(sessionKey);
		if(applicationNo != null){
	
			Application application = applicationEjb.findByApplicationNo(applicationNo);
			Long demandValue = Long.parseLong(demand);
			
			
			if(application.getApplicationType().equalsIgnoreCase(Constants.BULK) && demandValue == Constants.BULK_SUPPLY_DEMAND){
				
				packet.put("disable",Constants.LAND);
				
			}
			if(application.getApplicationType().equalsIgnoreCase(Constants.LAND) && demandValue  >= Constants.LAND_DEMAND_LOWER_LIMIT && demandValue  <= Constants.LAND_DEMAND_UPPER_LIMIT){
				packet.put("disable", Constants.BULK);
			}
		}
	
	} catch (JSONException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return packet;
}
private JSONObject validateNorms(HttpServletRequest request){
	
	selectedNorms = (Map<String, Norm>) request.getSession().getAttribute("selectedNorms");
	
	return null;
	
	
}
*/
///////////////////////////////////////

	public JSONObject  showPegInfo(HttpServletRequest request) throws JSONException {
	
		
    System.out.println("Hii showPegInfo");
	JSONObject packet =new JSONObject();
	JSONArray pegArray = new JSONArray();
	try {
		String costCenterNo=(String) request.getSession().getAttribute("costCenterNo");
		String selectedPegId = request.getParameter("selectedPegId");
		String selectedPegText = request.getParameter("selectedPegText");
		String quantity = request.getParameter("quantity");
		String applicationId = request.getParameter("applicationId");
		String estimateNo = request.getParameter("estimateNo");
		String masterId = request.getParameter("masterId");
		String warehouseId = request.getParameter("warehouseId");
		String sessionKey= (String) request.getSession().getAttribute("region");

		EstimateEjb estimateEjb = new EstimateEjb(sessionKey);
		
		
		System.out.println("Hii showPegInfo selectedPegId " + selectedPegId);
		if(warehouseId.equalsIgnoreCase("undefined") ||warehouseId == null || warehouseId.equalsIgnoreCase("-1") ){
			List<String> warehouses = estimateEjb.loadWarehouses(costCenterNo,sessionKey); //SEND_FOR_CONSTRUCTION_MAINTENANCE
			if(warehouses != null && warehouses.size() > 0){
				warehouseId = warehouses.get(0);
			}
			
		}
		
		masterId ="2";
		
		if(costCenterNo != null && (costCenterNo.equalsIgnoreCase("530.80")|| costCenterNo.equalsIgnoreCase("530.20")||costCenterNo.equalsIgnoreCase("530.20"))){
			masterId="1";
		}
		if(costCenterNo != null && (costCenterNo.equalsIgnoreCase("430.20") || costCenterNo.equalsIgnoreCase("430.25") || costCenterNo.equalsIgnoreCase("430.30") || costCenterNo.equalsIgnoreCase("430.35"))){
			masterId="4";
		}
		pegIdList =  (List<String>) request.getSession().getAttribute("selectedpegIdList");
		pegItemMap =(Map<String,PegItemDTO>) request.getSession().getAttribute("pegItemDetails");
		
		
		System.out.println(" masterId" + masterId);
		//String costCenterNo=(String) request.getSession().getAttribute("costCenterNo");
		
		//EstimateEjb estimateEjb = new EstimateEjb(sessionKey);
		
		List<SpPegInfo> listChilderns = estimateEjb.getPegChildrensByParentId(selectedPegId,masterId);
		
		if(listChilderns != null && listChilderns.size() > 0){
			packet.put("invalid",true);
			packet.put("errorMessage", "Please select valid node");
		}
		if(pegIdList == null){
			pegIdList = new ArrayList<String>();
		}
		
		
		if(pegIdList != null && pegIdList.contains(selectedPegId)){
			packet.put("invalid",true);
			packet.put("errorMessage", "Item already selected");
			return packet;

		}else{

			/*pegIdList =  (List<String>) request.getSession().getAttribute("selectedpegIdList");
			if(pegIdList != null){
				pegIdList.remove(id);
			}*/
			
			
			pegIdList.add(selectedPegId.trim());
			request.getSession().setAttribute("selectedpegIdList",pegIdList);
			
			PegItemDTO pegItemDTO = new PegItemDTO();
			pegItemDTO.setDeptId(costCenterNo);
			//pegItemDTO.setEstimateNo(getE.trim());
			pegItemDTO.setNodeDescription(selectedPegText);
			pegItemDTO.setNodeId(selectedPegId.trim());
			pegItemDTO.setNoOfItem(new BigDecimal(quantity));
			
			if(pegItemMap == null){
				pegItemMap = new TreeMap<String, PegItemDTO>();
			}
			pegItemMap.put(selectedPegId.trim(), pegItemDTO);
			
			request.getSession().setAttribute("pegItemDetails",pegItemMap);
			List<SpPointdmt> list = estimateEjb.getPegResourceById(selectedPegId,masterId, sessionKey);
			
			
			//resourceMap.put(selectedPegId, estiDeta);
			
			//float allQuantity = 0L;
			BigDecimal allQuantity = new BigDecimal(0);
			
			//if(allSelectedAdditionalResoMap == null){
				allSelectedAdditionalResoMap = (Map<String, DetailEstimateDTO>) request.getSession().getAttribute("allSelectedAdditionalResources");
				resourceMap = (Map<String, DetailEstimateDTO>) request.getSession().getAttribute("detailEstimationDetails");
				
			//}
		    System.out.println(" masterId" + masterId);
			InwrhmtmEjb ejb=new InwrhmtmEjb(sessionKey);
			
			
			if(allSelectedAdditionalResoMap == null){
				allSelectedAdditionalResoMap = new HashMap<String, DetailEstimateDTO>();
			}
			if(resourceMap == null){
				resourceMap = new HashMap<String, DetailEstimateDTO>();
			}
			alreadyAddedDetails = (Map<String,DetailEstimateDTO>) request.getSession().getAttribute("alreadyAddedDetails");
			
			if(alreadyAddedDetails == null){
				alreadyAddedDetails = new HashMap<String, DetailEstimateDTO>();
			}
			updatedDetailsMap = (Map<String,DetailEstimateDTO>) request.getSession().getAttribute("updatedDetails");
			
			if(updatedDetailsMap == null){
				updatedDetailsMap = new HashMap<String, DetailEstimateDTO>();
			}
			for(SpPointdmt dmt : list){
				JSONObject estimateDetailsRow =new JSONObject();
				System.out.println(" dmt.getId().getResCd().trim()" + dmt.getId().getResCd().trim());
				if(allSelectedAdditionalResoMap !=null && allSelectedAdditionalResoMap.containsKey(dmt.getId().getResCd().trim())){
					
					estimateDetailsRow.put("resourceType", dmt.getResType());
					estimateDetailsRow.put("resourceCategory",dmt.getResCat());
					estimateDetailsRow.put("resourceCode",dmt.getId().getResCd().trim());
					estimateDetailsRow.put("resourceName", dmt.getResName());
					estimateDetailsRow.put("needToRemovePreRows", dmt.getId().getResCd().trim());
					
					if(ejb.findUPByMatCd(dmt.getId().getResCd().trim(), warehouseId) != null){
						dmt.setUnitPrice(new Float(ejb.findUPByMatCd(dmt.getId().getResCd().trim(), warehouseId).toString()));
					}else {
						dmt.setUnitPrice(new Float(1));
					}
					
					
					estimateDetailsRow.put("unitPrice", dmt.getUnitPrice());
			
					estimateDetailsRow.put("uom", dmt.getUom());
					estimateDetailsRow.put("tolerance", dmt.getTolerance());
					
					if(allSelectedAdditionalResoMap.get(dmt.getId().getResCd().trim()).getEstimatedQuantity() != null){
						//dmt.setEstimatedQuantity(quantityValue.setScale(2, BigDecimal.ROUND_HALF_UP));
						allQuantity = new BigDecimal( dmt.getEstimatedQuantity().doubleValue() * new BigDecimal(quantity).doubleValue()+ Double.parseDouble(allSelectedAdditionalResoMap.get(dmt.getId().getResCd().trim()).getEstimatedQuantity().toString()));
						if(costCenterNo != null && (costCenterNo.trim().equalsIgnoreCase("710.00") || costCenterNo.trim().equalsIgnoreCase("550.20") || costCenterNo.trim().equalsIgnoreCase("510.20") || costCenterNo.equalsIgnoreCase("430.20") || costCenterNo.equalsIgnoreCase("430.25"))){
							if(dmt.getId().getResCd().trim().equalsIgnoreCase("L0825") || dmt.getId().getResCd().trim().equalsIgnoreCase("L0830")){
								allQuantity = new BigDecimal(allQuantity.doubleValue() +allQuantity.doubleValue()*.03);
							}
							if(dmt.getId().getResCd().trim().equalsIgnoreCase("D0605") || dmt.getId().getResCd().trim().equalsIgnoreCase("D0610")){
								double balance = allQuantity.doubleValue()/0.25;
								balance = Math.ceil(balance);
								
								if(balance <= 0){
									allQuantity = new BigDecimal(0.25);
								}else{
									allQuantity = new BigDecimal(balance * 0.25);
								}
							}else{
								allQuantity = new BigDecimal(Math.round(allQuantity.doubleValue()));
							}
						}
						//allTotalCost = allQuantity*dmt.getUnitPrice();
						DetailEstimateDTO detailEstimateDTO = allSelectedAdditionalResoMap.get(dmt.getId().getResCd().trim());
				
						detailEstimateDTO.setEstimatedQuantity(allQuantity.setScale(2, BigDecimal.ROUND_HALF_UP));
						detailEstimateDTO.setEstimateCost(new BigDecimal(dmt.getUnitPrice() * allQuantity.doubleValue()));
						allSelectedAdditionalResoMap.put(dmt.getId().getResCd().trim(), detailEstimateDTO);
						 
						if(alreadyAddedDetails.containsKey(dmt.getId().getResCd().trim())){
							updatedDetailsMap.put(dmt.getId().getResCd().trim(), detailEstimateDTO);
						}else{
							resourceMap.put(dmt.getId().getResCd().trim(), detailEstimateDTO);
						}
						
							
					}else{
						allQuantity = new BigDecimal(dmt.getEstimatedQuantity().doubleValue()* Double.parseDouble(quantity));
					}
					
					estimateDetailsRow.put("estimateQuantity",allQuantity );
					
					estimateDetailsRow.put("estimateCost", dmt.getUnitPrice() * allQuantity.doubleValue());
					
					//packet.put("needToRemovePreRows", dmt.getId().getResCd());
					
					pegArray.put(estimateDetailsRow);
					
					//DetailEstimateDTO detailEstimateDTO = populateDetailEstimateDTO(dmt);
					System.out.println(" hiiiiiiiii )" + dmt.getId().getResCd().trim());
					
					
				}else{
					
					
					estimateDetailsRow.put("selectedPegId", selectedPegId);
					estimateDetailsRow.put("resourceType", dmt.getResType());
					estimateDetailsRow.put("resourceCategory",dmt.getResCat());
					estimateDetailsRow.put("resourceCode",dmt.getId().getResCd().trim());
					estimateDetailsRow.put("resourceName", dmt.getResName());
					if(ejb.findUPByMatCd(dmt.getId().getResCd().trim(), warehouseId) != null){
						dmt.setUnitPrice(new Float(ejb.findUPByMatCd(dmt.getId().getResCd().trim(), warehouseId).toString()));
					}else {
						dmt.setUnitPrice(new Float(1));
					}
					allQuantity = new BigDecimal(dmt.getEstimatedQuantity().doubleValue() * Double.parseDouble(quantity));
					if(costCenterNo != null && (costCenterNo.equalsIgnoreCase("710.00") || costCenterNo.equalsIgnoreCase("550.20") || costCenterNo.equalsIgnoreCase("510.20") || costCenterNo.equalsIgnoreCase("430.20") || costCenterNo.equalsIgnoreCase("430.25"))){
						if(dmt.getId().getResCd().trim().equalsIgnoreCase("L0825") || dmt.getId().getResCd().trim().equalsIgnoreCase("L0830")){
							allQuantity = new BigDecimal(allQuantity.doubleValue() +allQuantity.doubleValue()*.03);
						}
						if(dmt.getId().getResCd().trim().equalsIgnoreCase("D0605") || dmt.getId().getResCd().trim().equalsIgnoreCase("D0610")){
							double balance = allQuantity.doubleValue()/0.25;
							balance = Math.ceil(balance);
							if(balance <= 0){
								allQuantity = new BigDecimal(0.25);
							}else{
								allQuantity = new BigDecimal(balance * 0.25);
							}
						}else{
							allQuantity = new BigDecimal(Math.round(allQuantity.doubleValue()));
						}
					}
					         
					estimateDetailsRow.put("unitPrice", dmt.getUnitPrice());
					estimateDetailsRow.put("uom", dmt.getUom());
					estimateDetailsRow.put("tolerance", dmt.getTolerance());
					estimateDetailsRow.put("estimateQuantity",allQuantity );
					
					//allTotalCost = allQuantity*dmt.getUnitPrice();
					estimateDetailsRow.put("estimateCost", new BigDecimal(dmt.getUnitPrice() * allQuantity.doubleValue()));
					estimateDetailsRow.put("isFromPegSchedule", true);
					
					pegArray.put(estimateDetailsRow);
					DetailEstimateDTO dto = new DetailEstimateDTO();
					dto.setEstimatedQuantity(allQuantity);
					dto.setEstimateCost(new BigDecimal(dmt.getUnitPrice() * allQuantity.doubleValue()));
					populateDetailEstimateDTO(dmt,dto);
					
					//allSelectedAdditionalResoMap.put(dmt.getId().getResCd(), detailEstimateDTO);
					
					allSelectedAdditionalResoMap.put(dmt.getId().getResCd().trim(), dto);
					resourceMap.put(dmt.getId().getResCd().trim(), dto);
					
				}
				allTotalCost = allTotalCost+ allQuantity.floatValue()*dmt.getUnitPrice();
				
			}
			//request.getSession().setAttribute("allSelectedAdditionalResources",
				//	allSelectedAdditionalResoMap);
			
			packet.put("estimateDetails", pegArray);
			packet.put("totalCost", allTotalCost);
			request.getSession().setAttribute("allSelectedAdditionalResources",allSelectedAdditionalResoMap);
			request.getSession().setAttribute("detailEstimationDetails",resourceMap);
			request.getSession().setAttribute("updatedDetails",updatedDetailsMap);
			/*for(SpPointdmt dmt : list){
				JSONObject estimateDetailsRow =new JSONObject();
				estimateDetailsRow.put("selectedPegId", selectedPegId);
				estimateDetailsRow.put("resourceType", dmt.getResType());
				estimateDetailsRow.put("resourceCategory",dmt.getResCat());
				estimateDetailsRow.put("resourceCode",dmt.getId().getResCd().trim());
				estimateDetailsRow.put("resourceName", dmt.getResName());
				
				if(ejb.findUnitPriceByMatCd(dmt.getId().getResCd().trim(), sessionKey) != null){
					dmt.setUnitPrice(new Float(ejb.findUnitPriceByMatCd(dmt.getId().getResCd().trim(), sessionKey).toString()));
				}else {
					dmt.setUnitPrice(new Float(1));
				}
				
				
				estimateDetailsRow.put("unitPrice", dmt.getUnitPrice());
				estimateDetailsRow.put("uom", dmt.getUom());
				estimateDetailsRow.put("tolerance", dmt.getTolerance());
				estimateDetailsRow.put("estimateQuantity",dmt.getEstimatedQuantity() * Long.parseLong(quantity) );
				
				
				estimateDetailsRow.put("estimateCost", dmt.getUnitPrice() * dmt.getEstimatedQuantity() * Long.parseLong(quantity));
				estimateDetailsRow.put("isFromPegSchedule", true);
				pegArray.put(estimateDetailsRow);
				
				DetailEstimateDTO dto = populateDetailEstimateDTO(dmt);
				resourceMap.put(selectedPegId+dmt.getId().getResCd(), dto);
				
			}
			packet.put("estimateDetails", pegArray);
			request.getSession().setAttribute("detailEstimationDetails",resourceMap);*/
		}
		
		JSONObject normDataRow =new JSONObject();
		
		packet.put("selectedPegId", selectedPegId);
		packet.put("quantity",quantity);
		packet.put("description", selectedPegText);
			
		//pegArray.put(normDataRow);

		//packet.put("showpegInfo", normDataRow);
		
	} catch (JSONException e) {
		
		packet.put("error", "Error in Load Norms");
		
	}catch (Exception e) {
		packet.put("error", "Error Occurred");
		e.printStackTrace();
	}

	return packet;

}
	
	public JSONObject  addAdditinalPegInfo(HttpServletRequest request) throws JSONException {
		
		JSONObject packet =new JSONObject();
		JSONArray pegArray = new JSONArray();
		try {
			String costCenterNo=(String) request.getSession().getAttribute("costCenterNo");
			String selectedPegId = request.getParameter("pegId");
			String warehouseId = request.getParameter("warehouseId");
			String quantity = request.getParameter("quantity");
			
			String masterId = request.getParameter("masterId");
			masterId ="2";
			
			if(costCenterNo != null && (costCenterNo.equalsIgnoreCase("530.20")||costCenterNo.equalsIgnoreCase("530.80"))){
				masterId="1";
			}
			if(costCenterNo != null && (costCenterNo.equalsIgnoreCase("430.20") || costCenterNo.equalsIgnoreCase("430.25") || costCenterNo.equalsIgnoreCase("430.30") || costCenterNo.equalsIgnoreCase("430.35"))){
				masterId="4";
			}
			deletedDetailsMap = (Map<String,DetailEstimateDTO>) request.getSession().getAttribute("deletedDetails");

			if(deletedDetailsMap == null){
				deletedDetailsMap = new HashMap<String, DetailEstimateDTO>();
			}
			pegIdList =  (List<String>) request.getSession().getAttribute("selectedpegIdList");
			pegItemMap =(Map<String,PegItemDTO>) request.getSession().getAttribute("pegItemDetails");
			existingPegItemMap =(Map<String,PegItemDTO>) request.getSession().getAttribute("existingPegItemMap");
			
			String sessionKey= (String) request.getSession().getAttribute("region");
			//String costCenterNo=(String) request.getSession().getAttribute("costCenterNo");
			
			EstimateEjb estimateEjb = new EstimateEjb(sessionKey);
			if(warehouseId.equalsIgnoreCase("undefined") ||warehouseId == null || warehouseId.equalsIgnoreCase("-1") ){
				List<String> warehouses = estimateEjb.loadWarehouses(costCenterNo,sessionKey); //SEND_FOR_CONSTRUCTION_MAINTENANCE
				if(warehouses != null && warehouses.size() > 0){
					warehouseId = warehouses.get(0);
				}
				
			}
			List<SpPegInfo> listChilderns = estimateEjb.getPegChildrensByParentId(selectedPegId,masterId);
			
			if(listChilderns != null && listChilderns.size() > 0){
				packet.put("invalid",true);
				packet.put("errorMessage", "Please select valid node");
			}
			if(pegIdList == null){
				pegIdList = new ArrayList<String>();
			}
			if(existingPegItemMap == null){
				existingPegItemMap = new HashMap<String,PegItemDTO>();
			}
			if(pegIdList != null && pegIdList.contains(selectedPegId)){
		
			
					List<SpPointdmt> list = estimateEjb.getPegResourceById(selectedPegId,masterId, sessionKey);
					
					
					//resourceMap.put(selectedPegId, estiDeta);
					
					//float allQuantity = 0L;
					BigDecimal allQuantity = new BigDecimal(0);
					
					//if(allSelectedAdditionalResoMap == null){
						allSelectedAdditionalResoMap = (Map<String, DetailEstimateDTO>) request.getSession().getAttribute("allSelectedAdditionalResources");
						resourceMap = (Map<String, DetailEstimateDTO>) request.getSession().getAttribute("detailEstimationDetails");
						//pegItemMap = (Map<String, PegItemDTO>) request.getSession().getAttribute("pegItemMap");
						
					//}
					
					InwrhmtmEjb ejb=new InwrhmtmEjb(sessionKey);
					
					if(allSelectedAdditionalResoMap == null){
						allSelectedAdditionalResoMap = new HashMap<String, DetailEstimateDTO>();
					}
					if(resourceMap == null){
						resourceMap = new HashMap<String, DetailEstimateDTO>();
					}
					alreadyAddedDetails = (Map<String,DetailEstimateDTO>) request.getSession().getAttribute("alreadyAddedDetails");
					
					if(alreadyAddedDetails == null){
						alreadyAddedDetails = new HashMap<String, DetailEstimateDTO>();
					}
					updatedDetailsMap = (Map<String,DetailEstimateDTO>) request.getSession().getAttribute("updatedDetails");
					
					if(updatedDetailsMap == null){
						updatedDetailsMap = new HashMap<String, DetailEstimateDTO>();
					}
					PegItemDTO pegItem = null;
					double oldQuantity = 0;
					double newQuantity = 0;
					double balance = 0;
					if(pegItemMap != null && selectedPegId != null){
						pegItem = pegItemMap.get(selectedPegId.trim());
						oldQuantity = pegItem.getNoOfItem().doubleValue();
						newQuantity = Double.parseDouble(quantity);
						balance = newQuantity - oldQuantity;
						pegItem.setNoOfItem(new BigDecimal(newQuantity));
						pegItemMap.put(selectedPegId.trim(), pegItem);
						
					}
					if(balance != 0){
						for(SpPointdmt dmt : list){
							JSONObject estimateDetailsRow =new JSONObject();
							
							if(allSelectedAdditionalResoMap !=null && allSelectedAdditionalResoMap.containsKey(dmt.getId().getResCd().trim())){
								
								estimateDetailsRow.put("resourceType", dmt.getResType());
								estimateDetailsRow.put("resourceCategory",dmt.getResCat());
								estimateDetailsRow.put("resourceCode",dmt.getId().getResCd().trim());
								estimateDetailsRow.put("resourceName", dmt.getResName());
								estimateDetailsRow.put("needToRemovePreRows", dmt.getId().getResCd().trim());
								
								if(ejb.findUPByMatCd(dmt.getId().getResCd().trim(), warehouseId) != null){
									dmt.setUnitPrice(new Float(ejb.findUPByMatCd(dmt.getId().getResCd().trim(), warehouseId).toString()));
								}else {
									dmt.setUnitPrice(new Float(1));
								}
								
								
								estimateDetailsRow.put("unitPrice", dmt.getUnitPrice());
						
								estimateDetailsRow.put("uom", dmt.getUom());
								estimateDetailsRow.put("tolerance", dmt.getTolerance());
								
								if(allSelectedAdditionalResoMap.get(dmt.getId().getResCd().trim()).getEstimatedQuantity() != null){
									//dmt.setEstimatedQuantity(quantityValue.setScale(2, BigDecimal.ROUND_HALF_UP));
									if(balance < 0){
										allQuantity = new BigDecimal(Double.parseDouble(allSelectedAdditionalResoMap.get(dmt.getId().getResCd().trim()).getEstimatedQuantity().toString()) +  dmt.getEstimatedQuantity().doubleValue() * new BigDecimal(balance).doubleValue());
									}else if (balance > 0){
										allQuantity = new BigDecimal( dmt.getEstimatedQuantity().doubleValue() * new BigDecimal(balance).doubleValue()+ Double.parseDouble(allSelectedAdditionalResoMap.get(dmt.getId().getResCd().trim()).getEstimatedQuantity().toString()));
									}
									
									if(costCenterNo != null && (costCenterNo.equalsIgnoreCase("710.00") || costCenterNo.equalsIgnoreCase("550.20") || costCenterNo.equalsIgnoreCase("510.20") || costCenterNo.equalsIgnoreCase("430.20") || costCenterNo.equalsIgnoreCase("430.25"))){
										if(dmt.getId().getResCd().trim().equalsIgnoreCase("L0825") || dmt.getId().getResCd().trim().equalsIgnoreCase("L0830")){
											double newEstimatedQuantity = dmt.getEstimatedQuantity().doubleValue()*newQuantity;
											allQuantity = new BigDecimal(newEstimatedQuantity +newEstimatedQuantity*.03);
										}
										if(dmt.getId().getResCd().trim().equalsIgnoreCase("D0605") || dmt.getId().getResCd().trim().equalsIgnoreCase("D0610")){
											double newEstimatedQuantity = dmt.getEstimatedQuantity().doubleValue()*newQuantity;
											double setOfWireBinding = newEstimatedQuantity/0.25;
											setOfWireBinding = Math.ceil(setOfWireBinding);
											if(setOfWireBinding <= 0){
												allQuantity = new BigDecimal(0.25);
											}else{
												allQuantity = new BigDecimal(setOfWireBinding * 0.25);
											}
										}else{
											allQuantity = new BigDecimal(Math.round(allQuantity.doubleValue()));
										}
									}
									
									
									//allTotalCost = allQuantity*dmt.getUnitPrice();
									DetailEstimateDTO detailEstimateDTO = allSelectedAdditionalResoMap.get(dmt.getId().getResCd().trim());
							
									detailEstimateDTO.setEstimatedQuantity(allQuantity.setScale(2, BigDecimal.ROUND_HALF_UP));
									detailEstimateDTO.setEstimateCost(new BigDecimal(dmt.getUnitPrice() * allQuantity.doubleValue()));
									allSelectedAdditionalResoMap.put(dmt.getId().getResCd().trim(), detailEstimateDTO);
									/*if(allQuantity.doubleValue() == 0){
										allSelectedAdditionalResoMap.remove(dmt.getId().getResCd().trim());
										resourceMap.remove(dmt.getId().getResCd().trim());
									}else */
									if(alreadyAddedDetails.containsKey(dmt.getId().getResCd().trim())){
										updatedDetailsMap.put(dmt.getId().getResCd().trim(), detailEstimateDTO);
										if(alreadyAddedDetails.containsKey(dmt.getId().getResCd().trim())){
											if(allQuantity.doubleValue() == 0){
												deletedDetailsMap.put(dmt.getId().getResCd().trim(), detailEstimateDTO);
											}else{
												updatedDetailsMap.put(dmt.getId().getResCd().trim(), detailEstimateDTO);
											}
										}else{
											if(allQuantity.doubleValue() == 0){
												allSelectedAdditionalResoMap.remove(dmt.getId().getResCd().trim());
												resourceMap.remove(dmt.getId().getResCd().trim());
											}
										}
										
									}else{
										if(allQuantity.doubleValue() == 0){
											allSelectedAdditionalResoMap.remove(dmt.getId().getResCd().trim());
											resourceMap.remove(dmt.getId().getResCd().trim());
										}else{
											resourceMap.put(dmt.getId().getResCd().trim(), detailEstimateDTO);
										}
									}
									
										
								}else{
									allQuantity = new BigDecimal(dmt.getEstimatedQuantity().doubleValue()* Double.parseDouble(quantity));
								}
								
								estimateDetailsRow.put("estimateQuantity",allQuantity );
								
								estimateDetailsRow.put("estimateCost", dmt.getUnitPrice() * allQuantity.doubleValue());
								
								//packet.put("needToRemovePreRows", dmt.getId().getResCd());
								
								pegArray.put(estimateDetailsRow);
								
								//DetailEstimateDTO detailEstimateDTO = populateDetailEstimateDTO(dmt);
								
								
								
							}else{
								
								
								estimateDetailsRow.put("selectedPegId", selectedPegId);
								estimateDetailsRow.put("resourceType", dmt.getResType());
								estimateDetailsRow.put("resourceCategory",dmt.getResCat());
								estimateDetailsRow.put("resourceCode",dmt.getId().getResCd().trim());
								estimateDetailsRow.put("resourceName", dmt.getResName());
								if(ejb.findUPByMatCd(dmt.getId().getResCd().trim(), warehouseId) != null){
									dmt.setUnitPrice(new Float(ejb.findUPByMatCd(dmt.getId().getResCd().trim(), warehouseId).toString()));
								}else {
									dmt.setUnitPrice(new Float(1));
								}
								estimateDetailsRow.put("unitPrice", dmt.getUnitPrice());
								estimateDetailsRow.put("uom", dmt.getUom());
								estimateDetailsRow.put("tolerance", dmt.getTolerance());
								estimateDetailsRow.put("estimateQuantity",dmt.getEstimatedQuantity().doubleValue() * Double.parseDouble(quantity) );
								allQuantity = new BigDecimal(dmt.getEstimatedQuantity().doubleValue() * Double.parseDouble(quantity));
								//allTotalCost = allQuantity*dmt.getUnitPrice();
								estimateDetailsRow.put("estimateCost", new BigDecimal(dmt.getUnitPrice() * allQuantity.doubleValue()));
								estimateDetailsRow.put("isFromPegSchedule", true);
								
								pegArray.put(estimateDetailsRow);
								DetailEstimateDTO dto = new DetailEstimateDTO();
								dto.setEstimatedQuantity(allQuantity);
								dto.setEstimateCost(new BigDecimal(dmt.getUnitPrice() * allQuantity.doubleValue()));
								populateDetailEstimateDTO(dmt,dto);
								
								//allSelectedAdditionalResoMap.put(dmt.getId().getResCd(), detailEstimateDTO);
								
								allSelectedAdditionalResoMap.put(dmt.getId().getResCd().trim(), dto);
								resourceMap.put(dmt.getId().getResCd().trim(), dto);
								
							}
							//allTotalCost = allTotalCost+ allQuantity.floatValue()*dmt.getUnitPrice();
							
						}
					}
				//request.getSession().setAttribute("allSelectedAdditionalResources",
					//	allSelectedAdditionalResoMap);
				double allTotalCost=populateSum(allSelectedAdditionalResoMap);
				packet.put("estimateDetails", pegArray);
				packet.put("totalCost", allTotalCost);
				request.getSession().setAttribute("allSelectedAdditionalResources",allSelectedAdditionalResoMap);
				request.getSession().setAttribute("detailEstimationDetails",resourceMap);
				request.getSession().setAttribute("updatedDetails",updatedDetailsMap);
				request.getSession().setAttribute("pegItemMap",pegItemMap);
				request.getSession().setAttribute("deletedDetails",deletedDetailsMap);
				request.getSession().setAttribute("existingPegItemMap",existingPegItemMap);
			}
			
			
				
			//pegArray.put(normDataRow);

			//packet.put("showpegInfo", normDataRow);
			
		} catch (JSONException e) {
			
			packet.put("error", "Error in Load Norms");
			
		}catch (Exception e) {
			packet.put("error", "Error Occurred");
			e.printStackTrace();
		}

		return packet;

	}
	private double populateSum(Map<String, DetailEstimateDTO> materialList){
		List<DetailEstimateDTO> detailEstimateDTOList = new ArrayList<DetailEstimateDTO>(materialList.values());
		//new ArrayList<AllocationSummaryDisplay>(allocationSummaryDisplayList.values());
		double estimateCost = 0;
		if(detailEstimateDTOList != null){
			
		
			for(DetailEstimateDTO dto : detailEstimateDTOList ){
				if(dto.getEstimateCost() != null){
					estimateCost = estimateCost + dto.getEstimateCost().doubleValue();
				}
			}
		}
		return estimateCost;
		
		
	}
	public JSONObject  viewMasterDetails(HttpServletRequest request) throws JSONException {
		nf.setGroupingUsed(true);
		nf.setMaximumFractionDigits(2);
		nf.setMinimumFractionDigits(2);
		JSONObject packet =new JSONObject();
		JSONArray pegArray = new JSONArray();
		try {
			String selectedPegId = request.getParameter("selectedPegId");
			String masterId = request.getParameter("masterId");
			String costCenterNo = (String) request.getSession().getAttribute("costCenterNo");
			masterId ="2";
			if(costCenterNo != null && (costCenterNo.equalsIgnoreCase("530.20") || costCenterNo.equalsIgnoreCase("530.80"))){
				masterId="1";
			}
			if(costCenterNo != null && (costCenterNo.equalsIgnoreCase("430.20") || costCenterNo.equalsIgnoreCase("430.25") || costCenterNo.equalsIgnoreCase("430.30") || costCenterNo.equalsIgnoreCase("430.35"))){
				masterId="4";
			}
			//String selectedPegText = request.getParameter("selectedPegText");
			
			String sessionKey= (String) request.getSession().getAttribute("region");
			
			
			EstimateEjb estimateEjb = new EstimateEjb(sessionKey); 
			List<SpPointdmt> list = estimateEjb.getPegResourceById(selectedPegId,masterId, sessionKey);
			
			for(SpPointdmt dmt : list){
				JSONObject masterDetaRow =new JSONObject();
				masterDetaRow.put("selectedPegId", selectedPegId);
				masterDetaRow.put("resourceType", dmt.getResType());
				masterDetaRow.put("resourceCode",dmt.getId().getResCd().trim());
				masterDetaRow.put("resourceName", dmt.getResName());
				masterDetaRow.put("estimateQuantity", nf.format(dmt.getEstimatedQuantity()));
				masterDetaRow.put("uom", dmt.getUom());
				pegArray.put(masterDetaRow);
			}
			packet.put("masterDetails", pegArray);
			
		} catch (JSONException e) {
			
			packet.put("error", "Error in Load Norms");
			
		}catch (Exception e) {
			packet.put("error", "Error Occurred");
			e.printStackTrace();
		}

		return packet;

	}
	
	public JSONObject  prepareEstimateDetails(HttpServletRequest request) throws JSONException {
		
		JSONObject packet =new JSONObject();
		JSONArray pegArray = new JSONArray();
		Map<String,EstimateDetails> resourceMap = new HashMap<String,EstimateDetails>();
		try {
			String selectedPegId = request.getParameter("selectedPegId");
			String masterId = request.getParameter("masterId");
			masterId ="1";
			String quantity = request.getParameter("quantity");
			BigDecimal allQuantity = new BigDecimal (0);
			String sessionKey= (String) request.getSession().getAttribute("region");
			allSelectedAdditionalResoMap = (Map<String, DetailEstimateDTO>) request.getSession().getAttribute("allSelectedAdditionalResources");
			
			EstimateEjb estimateEjb = new EstimateEjb(sessionKey); 
			List<SpPointdmt> list = estimateEjb.getPegResourceById(selectedPegId,masterId, sessionKey);
			
			
			
			request.getSession().setAttribute("detailEstimationDetails",resourceMap);
			if(allSelectedAdditionalResoMap == null){
				allSelectedAdditionalResoMap = new HashMap<String, DetailEstimateDTO>();
			}
			
			for(SpPointdmt dmt : list){
				JSONObject estimateDetailsRow =new JSONObject();
				
				if(allSelectedAdditionalResoMap !=null && allSelectedAdditionalResoMap.containsKey(dmt.getId().getResCd())){
					
					estimateDetailsRow.put("resourceType", dmt.getResType());
					estimateDetailsRow.put("resourceCategory",dmt.getResCat());
					estimateDetailsRow.put("resourceCode",dmt.getId().getResCd());
					estimateDetailsRow.put("resourceName", dmt.getResName());
					estimateDetailsRow.put("unitPrice", dmt.getUnitPrice());
					estimateDetailsRow.put("uom", dmt.getUom());
					estimateDetailsRow.put("tolerance", dmt.getTolerance());
					if(allSelectedAdditionalResoMap.get(dmt.getId().getResCd()).getEstimatedQuantity() != null){
						allQuantity =new BigDecimal(dmt.getEstimatedQuantity().doubleValue() * Double.parseDouble(quantity)+  Double.parseDouble(allSelectedAdditionalResoMap.get(dmt.getId().getResCd()).getEstimatedQuantity().toString()));
						DetailEstimateDTO detailEstimateDTO = allSelectedAdditionalResoMap.get(dmt.getId().getResCd());
						detailEstimateDTO.setEstimatedQuantity(allQuantity);
						detailEstimateDTO.setEstimateCost(new BigDecimal(dmt.getUnitPrice() * allQuantity.doubleValue()));
						 allSelectedAdditionalResoMap.put(dmt.getId().getResCd(), detailEstimateDTO);
					}else{
						allQuantity = new BigDecimal(dmt.getEstimatedQuantity().doubleValue() * Double.parseDouble(quantity));
					}
					
					estimateDetailsRow.put("estimateQuantity",allQuantity );
					
					estimateDetailsRow.put("estimateCost", dmt.getUnitPrice() * allQuantity.floatValue());
					
					packet.put("needToRemovePreRows", dmt.getId().getResCd());
					
					pegArray.put(estimateDetailsRow);
					
					
					
				}else{
					
					
					estimateDetailsRow.put("selectedPegId", selectedPegId);
					estimateDetailsRow.put("resourceType", dmt.getResType());
					estimateDetailsRow.put("resourceCategory",dmt.getResCat());
					estimateDetailsRow.put("resourceCode",dmt.getId().getResCd());
					estimateDetailsRow.put("resourceName", dmt.getResName());
					estimateDetailsRow.put("unitPrice", dmt.getUnitPrice());
					estimateDetailsRow.put("uom", dmt.getUom());
					estimateDetailsRow.put("tolerance", dmt.getTolerance());
					estimateDetailsRow.put("estimateQuantity",dmt.getEstimatedQuantity().doubleValue() * Double.parseDouble(quantity) );
					allQuantity = new BigDecimal(dmt.getEstimatedQuantity().doubleValue() * Double.parseDouble(quantity));
					estimateDetailsRow.put("estimateCost", dmt.getUnitPrice() * allQuantity.floatValue());
					
					
					pegArray.put(estimateDetailsRow);
					DetailEstimateDTO dto = new DetailEstimateDTO();
					populateDetailEstimateDTO(dmt,dto);
					
					allSelectedAdditionalResoMap.put(dmt.getId().getResCd(), dto);
				}
				 
			}
			request.getSession().setAttribute("allSelectedAdditionalResources",
					allSelectedAdditionalResoMap);
			
			packet.put("estimateDetails", pegArray);
			
		} catch (JSONException e) {
			
			packet.put("error", "Error in Load Norms");
			
		}catch (Exception e) {
			packet.put("error", "Error Occurred");
			e.printStackTrace();
		}

		return packet;
		
		
		
	}
	
	
/*public JSONObject  loadResourceCodes(HttpServletRequest request) {
		
	JSONArray resourceCodesJson = new JSONArray();

	JSONObject objectlist = new JSONObject();
	String sessionKey= (String) request.getSession().getAttribute("region");
	
	String resourceType = request.getParameter("resourceTypeId");
	
	try {
		EstimateEjb estimateEjb = new EstimateEjb(sessionKey); 
		List<String> resourceCodes  = estimateEjb.loadResourceCodes(resourceType,sessionKey); 
	  for (String resourceCode : resourceCodes) {
		  	JSONObject resourceTypeJson = new JSONObject();
			
		  	resourceTypeJson.put("id", resourceCode);
		  	resourceTypeJson.put("name", resourceCode);
			
		  	resourceCodesJson.put(resourceTypeJson);
       }
	  	
       objectlist.put("jsonarrayResourceCodes", resourceCodesJson);

	} catch (JSONException e) {
		
		e.printStackTrace();
	}

	return objectlist;
	}*/

	public JSONObject loadAdditionalResourceDetails(HttpServletRequest request) {
		JSONObject packet = new JSONObject();
		JSONArray resourceDetailsArray = new JSONArray();

		String lineSecIds = request.getParameter("selectedItems");
		String resourceType = request.getParameter("resourceType");
		
		String func = request.getParameter("func");

		String[] lineSectionIds = lineSecIds.split("\\|");
		allSelectedAdditionalResoMap = (Map<String, DetailEstimateDTO>) request.getSession().getAttribute("allSelectedAdditionalResources");
		if(allSelectedAdditionalResoMap == null){
			allSelectedAdditionalResoMap = new HashMap<String, DetailEstimateDTO>();
		}
		
		deletedDetailsMap = (Map<String,DetailEstimateDTO>) request.getSession().getAttribute("deletedDetails");
		if(deletedDetailsMap == null){
			deletedDetailsMap = new HashMap<String, DetailEstimateDTO>();
		}
		
		
		
		try {

			for (String id : lineSectionIds) {
				if(func.equalsIgnoreCase("add")){
					/*if(id.contains("VAT")){
						id=id.concat("%");
					}*/
					if(allSelectedAdditionalResoMap.containsKey(id.trim())){
						packet.put("invalid",true);
						packet.put("errorMessage", "Item "+ id.trim() +" already selected");
						return packet;
					}
				}
				JSONObject resourceTypeJson = new JSONObject();
				DetailEstimateDTO detailEstimateDTO = null;
				if(resourceType.equalsIgnoreCase("otherMat")){
					
					availableOtherMaterialsMap = (Map<String, DetailEstimateDTO>) request.getSession().getAttribute("availableOtherMaterialsMap");
					selectedOtherMaterials = (Map<String, DetailEstimateDTO>) request.getSession().getAttribute("selectedOtherMaterials");
			
					detailEstimateDTO = (DetailEstimateDTO) availableOtherMaterialsMap.get(id);
					if (detailEstimateDTO == null) {
						detailEstimateDTO = (DetailEstimateDTO) selectedOtherMaterials.get(id);
					}
				}else if(resourceType.equalsIgnoreCase("otherResource")){
					
					availableOtherResourcesMap = (Map<String, DetailEstimateDTO>) request.getSession().getAttribute("availableOtherResourcesMap");
					selectedOtherResources = (Map<String, DetailEstimateDTO>) request.getSession().getAttribute("selectedOtherResources");
			
					detailEstimateDTO = (DetailEstimateDTO) availableOtherResourcesMap.get(id);
					if (detailEstimateDTO == null) {
						detailEstimateDTO = (DetailEstimateDTO) selectedOtherResources.get(id);
					}
				}else if(resourceType.equalsIgnoreCase("NPL")){
					availableNPLMaterialsMap = (Map<String, DetailEstimateDTO>) request.getSession().getAttribute("availableNPLMaterialsMap");
					selectedNPLMaterials = (Map<String, DetailEstimateDTO>) request.getSession().getAttribute("selectedNPLMaterials");
					
					detailEstimateDTO = (DetailEstimateDTO) availableNPLMaterialsMap.get(id);
					if (detailEstimateDTO == null) {
						detailEstimateDTO = (DetailEstimateDTO) selectedNPLMaterials.get(id);
					}
				}
				

				resourceTypeJson.put("selectedPegId",
						detailEstimateDTO.getResourceCode());
				if(resourceType.equalsIgnoreCase("otherMat")){
					resourceTypeJson.put("resourceType",
					"MAT-COST");
					//detailEstimateDTO.setResourceType("MAT-COST");
					//detailEstimateDTO.setResCategory("1");
				}else{
					resourceTypeJson.put("resourceType",
							detailEstimateDTO.getResourceType());
				}
			
					resourceTypeJson.put("estimateQuantity",
							detailEstimateDTO.getEstimatedQuantity());
					resourceTypeJson.put("unitPrice",
							detailEstimateDTO.getUnitPrice());
					resourceTypeJson.put("uom", detailEstimateDTO.getUom());
					resourceTypeJson.put("resourceName",
							detailEstimateDTO.getResourceName());
					resourceTypeJson.put("resourceCode",
							detailEstimateDTO.getResourceCode());
					
				
				resourceTypeJson.put("totalCost","");
				resourceTypeJson.put("isFromPegSchedule", false);
				resourceTypeJson.put("resourceCodeType", resourceType);
				resourceDetailsArray.put(resourceTypeJson);

				if(resourceType.equalsIgnoreCase("otherMat")){
					if (selectedOtherMaterials == null) {
						selectedOtherMaterials = new HashMap<String, DetailEstimateDTO>();
					}
				}else if(resourceType.equalsIgnoreCase("otherResource")){
					
					if (selectedOtherResources == null) {
						selectedOtherResources = new HashMap<String, DetailEstimateDTO>();
					}
				
				}else if(resourceType.equalsIgnoreCase("NPL")){
					
					if (selectedNPLMaterials == null) {
						selectedNPLMaterials = new HashMap<String, DetailEstimateDTO>();
					}
				
				}
				
				if(allSelectedAdditionalResoMap == null){
					allSelectedAdditionalResoMap = new HashMap<String, DetailEstimateDTO>();
				}

				if (func.equalsIgnoreCase("add")) {

						if(resourceType.equalsIgnoreCase("otherMat")){
							selectedOtherMaterials.put(id, detailEstimateDTO);
						
						}else if(resourceType.equalsIgnoreCase("otherResource")){
							selectedOtherResources.put(id, detailEstimateDTO);
						
						
						}else if(resourceType.equalsIgnoreCase("NPL")){
							selectedNPLMaterials.put(id, detailEstimateDTO);
					
						}
						allSelectedAdditionalResoMap.put(id.trim(), detailEstimateDTO);
						resourceMap.put(id.trim(), detailEstimateDTO);
				} else if (func.equalsIgnoreCase("delete")) {
					if(resourceType.equalsIgnoreCase("otherMat")){
						selectedOtherMaterials.remove(id);
					}else if(resourceType.equalsIgnoreCase("otherResource")){
						selectedOtherResources.remove(id);
					
					}else if(resourceType.equalsIgnoreCase("NPL")){
						selectedNPLMaterials.remove(id);
					
					}
				
					allSelectedAdditionalResoMap.remove(id.trim());
					resourceMap.remove(id.trim());
					//deletedDetailsMap.put(id,detailEstimateDTO);
				}

			}
			
			if(resourceType.equalsIgnoreCase("otherMat")){
				request.getSession().setAttribute("selectedOtherMaterials",
						selectedOtherMaterials);
				
			}else if(resourceType.equalsIgnoreCase("otherResource")){
				request.getSession().setAttribute("selectedOtherResources",
						selectedOtherResources);
				
			
			}else if(resourceType.equalsIgnoreCase("NPL")){
				request.getSession().setAttribute("selectedNPLMaterials",
						selectedNPLMaterials);
				
			
			}
			
			request.getSession().setAttribute("allSelectedAdditionalResources",
					allSelectedAdditionalResoMap);
			request.getSession().setAttribute("detailEstimationDetails",resourceMap);
			request.getSession().setAttribute("deletedDetails",deletedDetailsMap);
			// request.getSession().setAttribute("deletedNorms",deletedNorms);
			packet.put("addedMaterials", resourceDetailsArray);

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
	
	public void  deletePegIngoDetailLine(HttpServletRequest request) {
		
		JSONArray applicationRefNumbers = new JSONArray();
		String lineSecId = request.getParameter("selectedItems");
		String resourceType = request.getParameter("resourceType");
		
		JSONObject objectlist = new JSONObject();

		
		allSelectedAdditionalResoMap = (Map<String,DetailEstimateDTO>) request.getSession().getAttribute("allSelectedAdditionalResources");
		deletedDetailsMap = (Map<String,DetailEstimateDTO>) request.getSession().getAttribute("deletedDetails");
		alreadyAddedDetails = (Map<String,DetailEstimateDTO>) request.getSession().getAttribute("alreadyAddedDetails");
		resourceMap = (Map<String, DetailEstimateDTO>) request.getSession().getAttribute("detailEstimationDetails");
		
		if(deletedDetailsMap == null){
			deletedDetailsMap = new HashMap<String, DetailEstimateDTO>();
		}
		if(alreadyAddedDmtDetails == null){
			alreadyAddedDmtDetails = new HashMap<String, Pcestdmt>();
		}
		
		
		if(lineSecId != null && allSelectedAdditionalResoMap != null && allSelectedAdditionalResoMap.size() > 0){
			DetailEstimateDTO sp = allSelectedAdditionalResoMap.remove(lineSecId);
			
			
		}
		if(alreadyAddedDetails != null && alreadyAddedDetails.size() > 0){
			DetailEstimateDTO existDto = alreadyAddedDetails.get(lineSecId);
			
			if(existDto != null){
				deletedDetailsMap.put(lineSecId, existDto);
			}
		}
		if(lineSecId != null && resourceMap != null && resourceMap.size() > 0){
			resourceMap.remove(lineSecId.trim());
		}
		request.getSession().setAttribute("allSelectedAdditionalResources",allSelectedAdditionalResoMap);
		request.getSession().setAttribute("deletedDetails",deletedDetailsMap);
		request.getSession().setAttribute("detailEstimationDetails",resourceMap);
	}
	
public JSONObject  deleteReviseDetailLine(HttpServletRequest request) {
		
		JSONArray applicationRefNumbers = new JSONArray();
		String lineSecId = request.getParameter("selectedItems");
		String resourceType = request.getParameter("resourceType");
		
		JSONObject objectlist = new JSONObject();
		JSONObject packet = new JSONObject();
		
		allSelectedAdditionalResoMap = (Map<String,DetailEstimateDTO>) request.getSession().getAttribute("allSelectedAdditionalResources");
		deletedDetailsMap = (Map<String,DetailEstimateDTO>) request.getSession().getAttribute("deletedDetails");
		alreadyAddedDetails = (Map<String,DetailEstimateDTO>) request.getSession().getAttribute("alreadyAddedDetails");
		resourceMap = (Map<String, DetailEstimateDTO>) request.getSession().getAttribute("detailEstimationDetails");
		alreadyAddedDmtDetails  =  (Map<String, Pcestdmt>) request.getSession().getAttribute("alreadyAddedDmtDetails");
		EstimateDetails estimateDetailobject=(EstimateDetails) request.getSession().getAttribute("estimateDetailobject");
		if(deletedDetailsMap == null){
			deletedDetailsMap = new HashMap<String, DetailEstimateDTO>();
		}
		if(alreadyAddedDmtDetails == null){
			alreadyAddedDmtDetails = new HashMap<String, Pcestdmt>();
		}
		
		
		if(lineSecId != null && allSelectedAdditionalResoMap != null && allSelectedAdditionalResoMap.size() > 0){
			DetailEstimateDTO sp = allSelectedAdditionalResoMap.remove(lineSecId);
			
			
		}
		if(alreadyAddedDetails != null && alreadyAddedDetails.size() > 0 &&  alreadyAddedDmtDetails != null && alreadyAddedDmtDetails.size() > 0){
			DetailEstimateDTO existDto = alreadyAddedDetails.get(lineSecId);
			if(alreadyAddedDmtDetails != null && alreadyAddedDmtDetails.size() > 0){
				Pcestdmt dmt = alreadyAddedDmtDetails.get(lineSecId);
				if(dmt != null && (dmt.getIssuedQty() != null || dmt.getIssuedQty() != new BigDecimal(0) || dmt.getIssuedCost() != null  || dmt.getIssuedCost() != new BigDecimal(0) || dmt.getApprovedQty() != null || dmt.getApprovedQty() != new BigDecimal(0) || dmt.getApprovedCost() != null || dmt.getApprovedCost() != new BigDecimal(0) || dmt.getCommitedQty() != null || dmt.getCommitedQty() != new BigDecimal(0) || dmt.getCommitedCost() != null ||dmt.getCommitedCost() != new BigDecimal(0))){
				//if(dmt != null && (  (dmt.getIssuedQty() != null && dmt.getIssuedQty() != new BigDecimal(0)) || (dmt.getIssuedCost() != null  && dmt.getIssuedCost() != new BigDecimal(0)) || (dmt.getApprovedQty() != null && dmt.getApprovedQty() != new BigDecimal(0)) || (dmt.getApprovedCost() != null && dmt.getApprovedCost() != new BigDecimal(0)) || (dmt.getCommitedQty() != null && dmt.getCommitedQty() != new BigDecimal(0)) || (dmt.getCommitedCost() != null && dmt.getCommitedCost() != new BigDecimal(0)))){
						
				//System.out.println("SSSSSSSS : " +dmt.getIssuedQty() + " : " + dmt.getIssuedCost() +" : "+ dmt.getApprovedQty() +" : "+ dmt.getApprovedCost() +"  ; "+ dmt.getCommitedCost()  +" : "+ dmt.getCommitedQty() );
					try {
						packet.put("invalid",true);
						packet.put("errorMessage", "Record can not be deleted");
					} catch (JSONException e) {
						try {
							packet.put("errorMessage", "Error Occured Deleting");
						} catch (JSONException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						e.printStackTrace();
					}
					
					
					return packet;
				}
				
			}
			if(existDto != null){
				deletedDetailsMap.put(lineSecId, existDto);
			}
		}
		if(lineSecId != null && resourceMap != null && resourceMap.size() > 0){
			resourceMap.remove(lineSecId.trim());
		}
		double allTotalCost=populateSum(allSelectedAdditionalResoMap);
		
		if(estimateDetailobject != null && estimateDetailobject.getHtt() != null){
			try {
				if(estimateDetailobject.getHtt().getStdCost() != null){
					packet.put("previousTotalCost", estimateDetailobject.getHtt().getStdCost());
				}
				
				packet.put("currentTotalCost", allTotalCost);
				
				if(estimateDetailobject.getHmt().getStdCost() != null){
					double deduction = allTotalCost-estimateDetailobject.getHtt().getStdCost().doubleValue();
					if(deduction != 0.0){
						packet.put("variance",(deduction/estimateDetailobject.getHtt().getStdCost().doubleValue())*100 +" %");
						
					}
				}
				//packet.put("estimateCost", new BigDecimal(estimatedCost).doubleValue()*new BigDecimal(quantity).doubleValue());
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		request.getSession().setAttribute("allSelectedAdditionalResources",allSelectedAdditionalResoMap);
		request.getSession().setAttribute("deletedDetails",deletedDetailsMap);
		request.getSession().setAttribute("detailEstimationDetails",resourceMap);
		return packet;
	}
public JSONObject  loadPegScheduleEstmationRefNumbers(HttpServletRequest request) {
	
	JSONArray applicationRefNumbers = new JSONArray();
	
	
	JSONObject objectlist = new JSONObject();

	List<String> applicationNos  = (List<String>) request.getSession().getAttribute("estimationRefNos");
	String costCenterNo=(String) request.getSession().getAttribute("costCenterNo");
	//List<String> estimationRefNos = null;
	//EstimateEjb estimateEjb = new EstimateEjb(sessionKey);
	//estimationRefNos = estimateEjb.loadStandEstmatenos(costCenterNo,ApplicationStatus.NEW_APPLICATION.getKey(),sessionKey); 
	try {
		
	  for (String applicationNo : applicationNos) {
		  	JSONObject appliNoJson = new JSONObject();
			
			appliNoJson.put("id", applicationNo);
			appliNoJson.put("name", applicationNo);
			
        	applicationRefNumbers.put(appliNoJson);
       }
	  	
       objectlist.put("jsonarrayapplicationrefs", applicationRefNumbers);
       objectlist.put("costCenter",costCenterNo);
      
       
} catch (JSONException e) {
		
		e.printStackTrace();
	}
	
	
	return objectlist;

}
public JSONObject  loadFFJobNumbers(HttpServletRequest request) {
	
	JSONArray applicationRefNumbers = new JSONArray();
	
	
	JSONObject objectlist = new JSONObject();
	String sessionKey= (String) request.getSession().getAttribute("region");
	List<String> applicationNos  = (List<String>) request.getSession().getAttribute("estimationRefNos");
	String costCenterNo=(String) request.getSession().getAttribute("costCenterNo");
	List<String> estimationRefNos = null;
	List<Long> status = new ArrayList<Long>();
	status.add(Long.parseLong(EstimateStatus.JOB_ONGOING));
	status.add(Long.parseLong(EstimateStatus.EST_POSTED));
	EstimateEjb estimateEjb = new EstimateEjb(sessionKey);
	estimationRefNos = estimateEjb.getFirstFiftyJobList(costCenterNo,status); 
	try {
	if(estimationRefNos != null){
	  for (String applicationNo : estimationRefNos) {
		  	JSONObject appliNoJson = new JSONObject();
			
			appliNoJson.put("id", applicationNo);
			appliNoJson.put("name", applicationNo);
			
        	applicationRefNumbers.put(appliNoJson);
       }
	}
	  	
     objectlist.put("jsonarrayapplicationrefs", applicationRefNumbers);
     objectlist.put("costCenter",costCenterNo);
      
       
} catch (JSONException e) {
		
		e.printStackTrace();
	}
	
	
	return objectlist;

}

public JSONObject  loadConstructionEstmationRefNumbers(HttpServletRequest request) {
	
	JSONArray applicationRefNumbers = new JSONArray();
	
	
	JSONObject objectlist = new JSONObject();

	List<String> applicationNos  = (List<String>) request.getSession().getAttribute("allEstimationRefNos");
	
	String costCenterNo=(String) request.getSession().getAttribute("costCenterNo");
	Map<String,String> estimNos = new HashMap<String, String>();
	for (String applicationNo : applicationNos) {
		estimNos.put(applicationNo, applicationNo);
	}
	List<String> list = new ArrayList<String>(estimNos.keySet());
	//List<String> estimationRefNos = null;
	//EstimateEjb estimateEjb = new EstimateEjb(sessionKey);
	//estimationRefNos = estimateEjb.loadStandEstmatenos(costCenterNo,ApplicationStatus.NEW_APPLICATION.getKey(),sessionKey); 
	try {
		
	  for (String applicationNo : list) {
		  	JSONObject appliNoJson = new JSONObject();
			
			appliNoJson.put("id", applicationNo);
			appliNoJson.put("name", applicationNo);
			
        	applicationRefNumbers.put(appliNoJson);
       }
	  	
       objectlist.put("jsonarrayapplicationrefs", applicationRefNumbers);
       objectlist.put("costCenter",costCenterNo);
      
       
} catch (JSONException e) {
		
		e.printStackTrace();
	}
	
	
	return objectlist;

}
public JSONObject  loadEstmationNumbers(HttpServletRequest request) {
	
	
	JSONArray estimationRefNumbers = new JSONArray();
	JSONArray applicationRefNumbers = new JSONArray();
	
	
	JSONObject objectlist = new JSONObject();

	List<String> applicationNos  = (List<String>) request.getSession().getAttribute("estimationRefNos");
	
	List<String> estimateNos  = (List<String>) request.getSession().getAttribute("estimateNos");
	
	//List<String> estimationRefNos = null;
	//EstimateEjb estimateEjb = new EstimateEjb(sessionKey);
	//estimationRefNos = estimateEjb.loadStandEstmatenos(costCenterNo,ApplicationStatus.NEW_APPLICATION.getKey(),sessionKey); 
	try {
			if (applicationNos != null) {

				for (String applicationNo : applicationNos) {
					JSONObject appliNoJson = new JSONObject();

					appliNoJson.put("id", applicationNo);
					appliNoJson.put("name", applicationNo);

					applicationRefNumbers.put(appliNoJson);
				}

				objectlist.put("jsonarrayapplicationrefs",applicationRefNumbers);
			}
			if (estimateNos != null) {
				for (String estimateNo : estimateNos) {
					JSONObject estimateNoJson = new JSONObject();

					estimateNoJson.put("id", estimateNo);
					estimateNoJson.put("name", estimateNo);

					estimationRefNumbers.put(estimateNoJson);
				}
			}

			objectlist.put("jsonarrayEstimationRefNumbers",estimationRefNumbers);
       
} catch (JSONException e) {
		
		e.printStackTrace();
	}
	
	
	return objectlist;

}
public JSONObject  loadJobNumbers(HttpServletRequest request) {
	
	
	JSONArray revisejobNoArray = new JSONArray();
	
	
	JSONObject objectlist = new JSONObject();

	List<String> revisejobNos  = (List<String>) request.getSession().getAttribute("revisejobNos");
	
	
	
	//List<String> estimationRefNos = null;
	//EstimateEjb estimateEjb = new EstimateEjb(sessionKey);
	//estimationRefNos = estimateEjb.loadStandEstmatenos(costCenterNo,ApplicationStatus.NEW_APPLICATION.getKey(),sessionKey); 
	try {
			if (revisejobNos != null) {

				for (String revisejobNo : revisejobNos) {
					JSONObject jobNoJson = new JSONObject();

					jobNoJson.put("id", revisejobNo);
					jobNoJson.put("name", revisejobNo);

					revisejobNoArray.put(jobNoJson);
				}

				objectlist.put("jsonarrayrevisejobNos",
						revisejobNoArray);
			}
			
       
} catch (JSONException e) {
		
		e.printStackTrace();
	}
	
	
	return objectlist;

}
public JSONObject  loadDefaultDropDownsValues(HttpServletRequest request) {
	
	JSONArray applicationRefNumbers = new JSONArray();
	JSONArray warehousesArray = new JSONArray();
	JSONArray fundsourcesArray = new JSONArray();
	JSONArray categoryArray = new JSONArray();
	
	//JSONObject fundIDSObejct = new JSONObject();
	JSONArray fundArray = new JSONArray();
	String sessionKey= (String) request.getSession().getAttribute("region");
	
	JSONObject objectlist = new JSONObject();
	List<String> warehouses= (List<String>) request.getSession().getAttribute("warehouses");
	String costCenterNo=(String) request.getSession().getAttribute("costCenterNo");
	List<String> applicationNos  = (List<String>) request.getSession().getAttribute("estimationRefNos");
	List<String> fundsources= (List<String>) request.getSession().getAttribute("fundsources");
	String applicationId=  request.getParameter("applicationId");
	
	//List<String> estimationRefNos = null;
	//EstimateEjb estimateEjb = new EstimateEjb(sessionKey);
	//estimationRefNos = estimateEjb.loadStandEstmatenos(costCenterNo,ApplicationStatus.NEW_APPLICATION.getKey(),sessionKey); 
	try {
		if(applicationNos != null){
		  for (String applicationNo : applicationNos) {
			  	JSONObject appliNoJson = new JSONObject();
				
				appliNoJson.put("id", applicationNo);
				appliNoJson.put("name", applicationNo);
				
	        	applicationRefNumbers.put(appliNoJson);
	       }
		  	
	       objectlist.put("jsonarrayapplicationrefs", applicationRefNumbers);
		}
		if(warehouses != null){
	       for (String warehouse : warehouses) {
			  	JSONObject warehouseJson = new JSONObject();
				
			  	warehouseJson.put("id", warehouse);
			  	warehouseJson.put("name", warehouse);
				
			  	warehousesArray.put(warehouseJson);
	      }
		}
		if(fundsources != null){
	       for (String fundsource : fundsources) {
			  	JSONObject fundsourceJson = new JSONObject();
				
			  	fundsourceJson.put("id", fundsource);
			  	fundsourceJson.put("name", fundsource);
				
			  	fundsourcesArray.put(fundsourceJson);
	       }
		}
      objectlist.put("jsonarrayapplicationrefs", applicationRefNumbers);
      objectlist.put("jsonWarehouses", warehousesArray);
      objectlist.put("jsonFundsources", fundsourcesArray);
      
       objectlist.put("costCenter", costCenterNo);
       if(warehouses != null && warehouses.size() != 0){
    	   objectlist.put("warehouse", warehouses.get(0));
       }
       if(fundsources != null && fundsources.size() != 0){
    	   objectlist.put("fundsource", fundsources.get(0));
       }
       EstimateEjb estimateEjb = new EstimateEjb(sessionKey); 
	
       List<String> codes = estimateEjb.getCatCode(costCenterNo,sessionKey);
		if(codes != null){
			for(String code: codes){
	
			
			  	JSONObject categoryJson = new JSONObject();
				
			  	categoryJson.put("id", code);
			  	categoryJson.put("name", code);
				
			  	categoryArray.put(categoryJson);
			}
			
	
		}
	  //for (SpPointdmt resourceDetail : resourceDetails) {
		  	
		  	//resourceTypeJson.put("totalCost", resourceDetail.getUnitPrice());
		  	//resourceTypeJson.put(resourceTypeJson);
      //}
	  	

			 objectlist.put("jsonCategoryCodes", categoryArray);
		      if(codes != null && codes.size() != 0){
		    	  objectlist.put("categoryCode", codes.get(0));
		      }
		       
		    
				List<String> fundids = estimateEjb.getFundIds(costCenterNo,null,sessionKey);
				if(fundids != null){
					for(String fundid: fundids){
			
					
					  	JSONObject fundidJson = new JSONObject();
						
					  	fundidJson.put("id", fundid);
					  	fundidJson.put("name", fundid);
						
					  	fundArray.put(fundidJson);
					}
					
			
				}
				
				 objectlist.put("jsonfundIds", fundArray);
			      if(fundids != null){
			    	 // objectlist.put("fundid", fundids.get(0));
			      } 
			if(applicationId != null ){
				
				try{
						ApplicationReferenceEjb ap = new ApplicationReferenceEjb(sessionKey);
						ApplicationReference refre = ap.findByApplicationNo(applicationId);
						if(refre != null){
							
							WiringLandDetailConEjb con = new WiringLandDetailConEjb(sessionKey);
							WiringLandDetailConPK pk = new WiringLandDetailConPK();
							pk.setDeptId(costCenterNo.substring(0, 3).concat(".00"));
							pk.setApplicationId(refre.getId().getApplicationId());
							WiringLandDetailCon appp =con.findByAppId(pk);
							if(appp != null){
								objectlist.put("fundsource", appp.getFundSource().trim());
							}
						}
				} catch (JSONException e) {
					
					e.printStackTrace();
				}
			}
	} catch (JSONException e) {
		
		e.printStackTrace();
	}
	
	return objectlist;

}
public JSONObject  loadDropDownsValues(HttpServletRequest request) {
	
	JSONArray applicationRefNumbers = new JSONArray();
	JSONArray warehousesArray = new JSONArray();
	JSONArray fundsourcesArray = new JSONArray();
	JSONArray categoryArray = new JSONArray();
	
	//JSONObject fundIDSObejct = new JSONObject();
	JSONArray fundArray = new JSONArray();
	String sessionKey= (String) request.getSession().getAttribute("region");
	
	JSONObject objectlist = new JSONObject();
	List<String> warehouses= (List<String>) request.getSession().getAttribute("warehouses");
	String costCenterNo=(String) request.getSession().getAttribute("costCenterNo");
	List<String> applicationNos  = (List<String>) request.getSession().getAttribute("estimationRefNos");
	List<String> fundsources= (List<String>) request.getSession().getAttribute("fundsources");
	//List<String> estimationRefNos = null;
	//EstimateEjb estimateEjb = new EstimateEjb(sessionKey);
	//estimationRefNos = estimateEjb.loadStandEstmatenos(costCenterNo,ApplicationStatus.NEW_APPLICATION.getKey(),sessionKey); 
	try {
		if(applicationNos != null){
		  for (String applicationNo : applicationNos) {
			  	JSONObject appliNoJson = new JSONObject();
				
				appliNoJson.put("id", applicationNo);
				appliNoJson.put("name", applicationNo);
				
	        	applicationRefNumbers.put(appliNoJson);
	       }
		  	
	       objectlist.put("jsonarrayapplicationrefs", applicationRefNumbers);
		}
		if(warehouses != null){
	       for (String warehouse : warehouses) {
			  	JSONObject warehouseJson = new JSONObject();
				
			  	warehouseJson.put("id", warehouse);
			  	warehouseJson.put("name", warehouse);
				
			  	warehousesArray.put(warehouseJson);
	      }
		}
		if(fundsources != null){
	       for (String fundsource : fundsources) {
			  	JSONObject fundsourceJson = new JSONObject();
				
			  	fundsourceJson.put("id", fundsource);
			  	fundsourceJson.put("name", fundsource);
				
			  	fundsourcesArray.put(fundsourceJson);
	       }
		}
      objectlist.put("jsonarrayapplicationrefs", applicationRefNumbers);
      objectlist.put("jsonWarehouses", warehousesArray);
      objectlist.put("jsonFundsources", fundsourcesArray);
      
       objectlist.put("costCenter", costCenterNo);
       if(warehouses != null && warehouses.size() != 0){
    	   objectlist.put("warehouse", warehouses.get(0));
       }
       if(fundsources != null && fundsources.size() != 0){
    	 //  objectlist.put("fundsource", fundsources.get(0));
       }
       EstimateEjb estimateEjb = new EstimateEjb(sessionKey); 
	
       List<String> codes = estimateEjb.getCatCode(costCenterNo,sessionKey);
		if(codes != null){
			for(String code: codes){
	
			
			  	JSONObject categoryJson = new JSONObject();
				
			  	categoryJson.put("id", code);
			  	categoryJson.put("name", code);
				
			  	categoryArray.put(categoryJson);
			}
			
	
		}
	  //for (SpPointdmt resourceDetail : resourceDetails) {
		  	
		  	//resourceTypeJson.put("totalCost", resourceDetail.getUnitPrice());
		  	//resourceTypeJson.put(resourceTypeJson);
      //}
	  	

			 objectlist.put("jsonCategoryCodes", categoryArray);
		      if(codes != null && codes.size() != 0){
		    	 // objectlist.put("categoryCode", codes.get(0));
		      }
		       
		    
				List<String> fundids = estimateEjb.getFundIds(costCenterNo,null,sessionKey);
				if(fundids != null){
					for(String fundid: fundids){
			
					
					  	JSONObject fundidJson = new JSONObject();
						
					  	fundidJson.put("id", fundid);
					  	fundidJson.put("name", fundid);
						
					  	fundArray.put(fundidJson);
					}
					
			
				}
				
				  objectlist.put("jsonfundIds", fundArray);
			      if(fundids != null){
			    	  //objectlist.put("fundid", fundids.get(0));
			      } 
				
	} catch (JSONException e) {
		
		e.printStackTrace();
	}
	
	return objectlist;

}
public JSONObject  loadCategoryCodes(HttpServletRequest request) {
	
	JSONObject categoryObejct = new JSONObject();
	JSONArray categoryArray = new JSONArray();
	
	//JSONObject objectlist = new JSONObject();
	String sessionKey= (String) request.getSession().getAttribute("region");
	
	String warehouse = request.getParameter("warehouse");
	//String resourceCode = request.getParameter("resourceCode");
	try {
		EstimateEjb estimateEjb = new EstimateEjb(sessionKey); 
		if(!warehouse.equals("-1")){
			List<String> codes = estimateEjb.getCatCode(warehouse,sessionKey);
			if(codes != null){
				for(String code: codes){
		
				
				  	JSONObject categoryJson = new JSONObject();
					
				  	categoryJson.put("id", code);
				  	categoryJson.put("name", code);
					
				  	categoryArray.put(categoryJson);
				}
				
		
			}
			
		  //for (SpPointdmt resourceDetail : resourceDetails) {
			  	
			  	//resourceTypeJson.put("totalCost", resourceDetail.getUnitPrice());
			  	//resourceTypeJson.put(resourceTypeJson);
	       //}
		  	
				categoryObejct.put("categoryCodes", categoryArray);
		}

	} catch (JSONException e) {
		
		e.printStackTrace();
	}

	return categoryObejct;
	}

	private DetailEstimateDTO populateDetailEstimateDTO(SpPointdmt dmt,DetailEstimateDTO dto){
		//DetailEstimateDTO dto = new DetailEstimateDTO();
		//dto.setEstimatedCost(new BigDecimal(dmt.getEstimatedQuantity() * dmt.getUnitPrice()));
		//dto.setEstimatedQuantity(new BigDecimal(dmt.getEstimatedQuantity()));
		dto.setResCategory(new Long(dmt.getResCat()).toString());
		dto.setResourceCode(dmt.getId().getResCd().trim());
		dto.setResourceName(dmt.getResName());
		dto.setResourceType(dmt.getResType());
		dto.setUom(dmt.getUom());
		dto.setUnitPrice(new BigDecimal(dmt.getUnitPrice()));
		
		return dto;
		
	}

public JSONObject  loadFundIds(HttpServletRequest request) {
	
	JSONObject fundIDSObejct = new JSONObject();
	JSONArray fundArray = new JSONArray();
	
	//JSONObject objectlist = new JSONObject();
	String sessionKey= (String) request.getSession().getAttribute("region");
	
	String foundsource = request.getParameter("foundsource");
	String deptId = request.getParameter("deptId");
	try {
		EstimateEjb estimateEjb = new EstimateEjb(sessionKey); 
		List<String> fundids = estimateEjb.getFundIds(deptId,foundsource,sessionKey);
		if(fundids != null){
			for(String fundid: fundids){
	
			
			  	JSONObject fundidJson = new JSONObject();
				
			  	fundidJson.put("id", fundid);
			  	fundidJson.put("name", fundid);
				
			  	fundArray.put(fundidJson);
			}
			
	
		}
	  //for (SpPointdmt resourceDetail : resourceDetails) {
		  	
		  	//resourceTypeJson.put("totalCost", resourceDetail.getUnitPrice());
		  	//resourceTypeJson.put(resourceTypeJson);
       //}
	  	
		fundIDSObejct.put("jsonfundIds", fundArray);

	} catch (JSONException e) {
		
		e.printStackTrace();
	}

	return fundIDSObejct;
	}
public JSONObject getEstimatedUpdateDetails(HttpServletRequest request){
	
	JSONObject packet =new JSONObject();
	String quantity = request.getParameter("quantity");
	
	String lineId = request.getParameter("lineid");
	String estimatedCost = request.getParameter("linecost");
	String unitcost = request.getParameter("unitcost");
	
	allSelectedAdditionalResoMap = (Map<String,DetailEstimateDTO>) request.getSession().getAttribute("allSelectedAdditionalResources");
	updatedDetailsMap = (Map<String,DetailEstimateDTO>) request.getSession().getAttribute("updatedDetails");
	alreadyAddedDetails = (Map<String,DetailEstimateDTO>) request.getSession().getAttribute("alreadyAddedDetails");
	resourceMap = (Map<String,DetailEstimateDTO>) request.getSession().getAttribute("detailEstimationDetails");
	
	if(updatedDetailsMap == null){
		updatedDetailsMap = new HashMap<String, DetailEstimateDTO>();
	}
//	if(lineId.contains("VAT")){
//		lineId=lineId.concat("%");
//	}
	DetailEstimateDTO dto = allSelectedAdditionalResoMap.get(lineId);
	
	if(dto != null){
		if(quantity != null && quantity.equalsIgnoreCase(Constants.DEFAULT_STRING)){
			dto.setEstimatedQuantity(new BigDecimal("0"));
			
		}else if(quantity != null){
			dto.setEstimatedQuantity(new BigDecimal(quantity));
			
		}else{
			dto.setEstimatedQuantity(new BigDecimal("0"));
		}
		if(estimatedCost != null && estimatedCost.equalsIgnoreCase(Constants.DEFAULT_STRING)){
			dto.setEstimateCost(new BigDecimal("0"));
		}else if(estimatedCost != null){
			dto.setEstimateCost(new BigDecimal(estimatedCost));
		}else{
			dto.setEstimateCost(new BigDecimal("0"));
		}
		if(unitcost != null && unitcost.equalsIgnoreCase(Constants.DEFAULT_STRING)){
			dto.setUnitPrice(new BigDecimal("0"));
		}else if(unitcost != null){
			dto.setUnitPrice(new BigDecimal(unitcost));
		}else{
			dto.setUnitPrice(new BigDecimal("0"));
		}
		
		
		//dto.setEstimateCost(new BigDecimal(estimatedCost));
		
		try {
			packet.put("estimateCost", new BigDecimal(estimatedCost).doubleValue()*new BigDecimal(quantity).doubleValue());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		if(alreadyAddedDetails != null && alreadyAddedDetails.size() > 0){
			if(alreadyAddedDetails.get(lineId) != null){
				updatedDetailsMap.put(lineId.trim(), dto);
				
			}else{
				if(resourceMap != null){
					resourceMap.put(lineId.trim(),dto);
					
					
				}
			}
		}
		
	}
	
	request.getSession().setAttribute("detailEstimationDetails",resourceMap);
	request.getSession().setAttribute("allSelectedAdditionalResources",allSelectedAdditionalResoMap);
	request.getSession().setAttribute("updatedDetails",updatedDetailsMap);
	return packet;
}

//Gayani

public JSONObject getEstimatedUpdateDetailsForRebate(HttpServletRequest request){
	
	System.out.println("getEstimatedUpdateDetailsForRebate");
	String costCenterNo=(String) request.getSession().getAttribute("costCenterNo");
	JSONObject packet =new JSONObject();
	String quantity = request.getParameter("quantity");
	String rebQuantity = request.getParameter("rebquantity");
	
	String rebatecost = request.getParameter("rebatecost");
	String reuquantity = request.getParameter("reuquantity");
	String offquantity = request.getParameter("offquantity");
	String lineId = request.getParameter("lineid");
	String estimatedCost = request.getParameter("linecost");
	String unitcost = request.getParameter("unitcost");
	PegItemDTO pegItemDTO = null;
	String descriSummary = null;
	System.out.println("Hirebate "+rebQuantity);
	System.out.println("Hirebate "+rebQuantity);
	double balance = 0;
	allSelectedAdditionalResoMap = (Map<String,DetailEstimateDTO>) request.getSession().getAttribute("allSelectedAdditionalResources");
	updatedDetailsMap = (Map<String,DetailEstimateDTO>) request.getSession().getAttribute("updatedDetails");
	alreadyAddedDetails = (Map<String,DetailEstimateDTO>) request.getSession().getAttribute("alreadyAddedDetails");
	resourceMap = (Map<String,DetailEstimateDTO>) request.getSession().getAttribute("detailEstimationDetails");
	pegItemMap =(Map<String,PegItemDTO>) request.getSession().getAttribute("pegItemDetails");
	if(updatedDetailsMap == null){
		updatedDetailsMap = new HashMap<String, DetailEstimateDTO>();
	}
	if(pegItemMap == null){
		pegItemMap = new TreeMap<String, PegItemDTO>();
	}
	if(alreadyAddedDetails == null){
		alreadyAddedDetails = new TreeMap<String, DetailEstimateDTO>();
	}
	DetailEstimateDTO dto = allSelectedAdditionalResoMap.get(lineId);
	double oldQuantity =0.0;
	double oldRebQuantity =0.0;
	if(dto != null && dto.getEstimatedQuantity() != null){
		
		System.out.println("Hiestima "+dto.getEstimatedQuantity());
		oldQuantity = dto.getEstimatedQuantity().doubleValue(); 
	}
	double newQuantity = 0;
	double newRebQuantity = 0;
	//BigDecimal itemFinalQuantity = new  BigDecimal("0");
	if(dto != null){
		System.out.println("Hiestimaquantity "+quantity);
		/**if(quantity != null && quantity.equalsIgnoreCase(Constants.DEFAULT_STRING)){
			dto.setEstimatedQuantity(new BigDecimal("0"));
			
		}else if(quantity != null){
			System.out.println("Hiestimaquantity "+quantity);
			dto.setEstimatedQuantity(new BigDecimal(quantity));
			newQuantity = new Double(quantity);
		}else{
			dto.setEstimatedQuantity(new BigDecimal("0"));
		}*/
		
		if(rebQuantity != null && rebQuantity.equalsIgnoreCase(Constants.DEFAULT_STRING)){
			dto.setRebateQuantity(new BigDecimal("0"));
			
		}else if(rebQuantity != null){
			dto.setRebateQuantity(new BigDecimal(rebQuantity));
			
		}else{
			dto.setRebateQuantity(new BigDecimal("0"));
		}
		
		if(rebatecost != null && rebatecost.equalsIgnoreCase(Constants.DEFAULT_STRING)){
			dto.setRebateCost(new BigDecimal("0"));
		}else if(rebatecost != null){
			dto.setRebateCost(new BigDecimal(rebatecost));
		}else{
			dto.setRebateCost(new BigDecimal("0"));
		}
		
		/**
		
		
		if(reuquantity != null && reuquantity.equalsIgnoreCase(Constants.DEFAULT_STRING)){
			dto.setReusableQuantity(new BigDecimal("0"));
			
		}else if(reuquantity != null){
			dto.setReusableQuantity(new BigDecimal(reuquantity));
			
		}else{
			dto.setReusableQuantity(new BigDecimal("0"));
		}
		
		
		if(offquantity != null && offquantity.equalsIgnoreCase(Constants.DEFAULT_STRING)){
			dto.setOffChargeQuantity(new BigDecimal("0"));
			
		}else if(offquantity != null){
			dto.setOffChargeQuantity(new BigDecimal(offquantity));
			
		}else{
			dto.setOffChargeQuantity(new BigDecimal("0"));
		}
		
		
		
		System.out.println("Hiestimaquantity "+estimatedCost);
		if(estimatedCost != null && estimatedCost.equalsIgnoreCase(Constants.DEFAULT_STRING)){
			dto.setEstimateCost(new BigDecimal("0"));
		}else if(estimatedCost != null){
			System.out.println("Hiestimaquantity "+estimatedCost);
			dto.setEstimateCost(new BigDecimal(estimatedCost));
		}else{
			dto.setEstimateCost(new BigDecimal("0"));
		}
		if(unitcost != null && unitcost.equalsIgnoreCase(Constants.DEFAULT_STRING)){
			dto.setUnitPrice(new BigDecimal("0"));
		}else if(unitcost != null){
			dto.setUnitPrice(new BigDecimal(unitcost));
		}else{
			dto.setUnitPrice(new BigDecimal("0"));
		}
		
		//dto.setEstimateCost(new BigDecimal(estimatedCost));
		/*if(lineId.trim().equalsIgnoreCase("L0825") || lineId.trim().equalsIgnoreCase("L0830")){
			itemFinalQuantity = new BigDecimal(new BigDecimal(quantity).doubleValue() +new BigDecimal(quantity).doubleValue()*.03);
		}else if(lineId.trim().equalsIgnoreCase("D0605") || lineId.trim().equalsIgnoreCase("D0610")){
			double setOfWireBinding = new BigDecimal(quantity).doubleValue()/0.25;
			if(setOfWireBinding <= 0){
				itemFinalQuantity = new BigDecimal(0.25);
			}else{
				itemFinalQuantity = new BigDecimal(setOfWireBinding * 0.25);
			}
		}else{
			itemFinalQuantity = new BigDecimal(quantity);
		}*/
		
		try {
			packet.put("estimateCost", new BigDecimal(estimatedCost).doubleValue()*new BigDecimal(quantity).doubleValue());
			//packet.put("estimateQuantity",itemFinalQuantity );
			//packet.put("lineId",lineId );
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		if(newQuantity == oldQuantity ){
			balance =0;
		}else 
			balance = newQuantity-oldQuantity;
		}
		if(alreadyAddedDetails != null){
			if(alreadyAddedDetails.get(lineId) != null){
				updatedDetailsMap.put(lineId.trim(), dto);
			
				pegItemDTO = populatePegItemDTO(costCenterNo,dto);
				
				if(balance != 0){
					pegItemDTO = populatePegItemDTO(costCenterNo,dto);
					descriSummary = pegItemDTO.getNodeDescription();
					pegItemDTO.setNodeDescription("MODIFY :" +descriSummary);
					pegItemDTO.setNoOfItem(new BigDecimal(balance));
					try {
						packet.put("selectedPegId", pegItemDTO.getNodeId());
					
						packet.put("quantity",balance);
						packet.put("description", "MODIFY :" +descriSummary);
						pegItemMap.put(pegItemDTO.getNodeId(), pegItemDTO);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}else if(resourceMap != null){
				resourceMap.put(lineId.trim(),dto);
				if(oldQuantity ==0 || lineId.trim().contains("NPL")){
					pegItemDTO = populatePegItemDTO(costCenterNo,dto);
					descriSummary = pegItemDTO.getNodeDescription();
					pegItemDTO.setNodeDescription("ADD :" +descriSummary);
					pegItemDTO.setNoOfItem(new BigDecimal(quantity));
					try{
						packet.put("selectedPegId", pegItemDTO.getNodeId());
						packet.put("quantity",quantity);
						packet.put("description", "ADD :" +descriSummary);
						pegItemMap.put(pegItemDTO.getNodeId(), pegItemDTO);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else{
					pegItemDTO = populatePegItemDTO(costCenterNo,dto);
					pegItemDTO.setNodeDescription(pegItemDTO.getNodeId());
					descriSummary = pegItemDTO.getNodeDescription();
					pegItemDTO.setNoOfItem(new BigDecimal(balance));
					pegItemDTO.setNodeDescription("MODIFY :" +descriSummary);
					try{
						packet.put("selectedPegId", pegItemDTO.getNodeId());
						packet.put("quantity",balance);
						packet.put("description", "MODIFY :" +descriSummary);
						pegItemMap.put(pegItemDTO.getNodeId(), pegItemDTO);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
		}
			
		
	


	
	request.getSession().setAttribute("pegItemDetails",pegItemMap);
	
	request.getSession().setAttribute("detailEstimationDetails",resourceMap);
	request.getSession().setAttribute("allSelectedAdditionalResources",allSelectedAdditionalResoMap);
	request.getSession().setAttribute("updatedDetails",updatedDetailsMap);
	return packet;
}

public JSONObject getEstimatedUpdateDetailsForReusable(HttpServletRequest request){
	
	System.out.println("getEstimatedUpdateDetailsForRebate");
	String costCenterNo=(String) request.getSession().getAttribute("costCenterNo");
	JSONObject packet =new JSONObject();
	String quantity = request.getParameter("quantity");
	String rebQuantity = request.getParameter("rebquantity");
	
	String rebatecost = request.getParameter("rebatecost");
	String reuquantity = request.getParameter("reuquantity");
	String offquantity = request.getParameter("offquantity");
	String lineId = request.getParameter("lineid");
	String estimatedCost = request.getParameter("linecost");
	String unitcost = request.getParameter("unitcost");
	PegItemDTO pegItemDTO = null;
	String descriSummary = null;
	System.out.println("Hirebate "+rebQuantity);
	System.out.println("Hirebate "+rebQuantity);
	double balance = 0;
	allSelectedAdditionalResoMap = (Map<String,DetailEstimateDTO>) request.getSession().getAttribute("allSelectedAdditionalResources");
	updatedDetailsMap = (Map<String,DetailEstimateDTO>) request.getSession().getAttribute("updatedDetails");
	alreadyAddedDetails = (Map<String,DetailEstimateDTO>) request.getSession().getAttribute("alreadyAddedDetails");
	resourceMap = (Map<String,DetailEstimateDTO>) request.getSession().getAttribute("detailEstimationDetails");
	pegItemMap =(Map<String,PegItemDTO>) request.getSession().getAttribute("pegItemDetails");
	if(updatedDetailsMap == null){
		updatedDetailsMap = new HashMap<String, DetailEstimateDTO>();
	}
	if(pegItemMap == null){
		pegItemMap = new TreeMap<String, PegItemDTO>();
	}
	if(alreadyAddedDetails == null){
		alreadyAddedDetails = new TreeMap<String, DetailEstimateDTO>();
	}
	DetailEstimateDTO dto = allSelectedAdditionalResoMap.get(lineId);
	double oldQuantity =0.0;
	double oldRebQuantity =0.0;
	if(dto != null && dto.getEstimatedQuantity() != null){
		
		System.out.println("Hiestima "+dto.getEstimatedQuantity());
		oldQuantity = dto.getEstimatedQuantity().doubleValue(); 
	}
	double newQuantity = 0;
	double newRebQuantity = 0;
	//BigDecimal itemFinalQuantity = new  BigDecimal("0");
	if(dto != null){
		System.out.println("Hiestimaquantity "+quantity);
		/**if(quantity != null && quantity.equalsIgnoreCase(Constants.DEFAULT_STRING)){
			dto.setEstimatedQuantity(new BigDecimal("0"));
			
		}else if(quantity != null){
			System.out.println("Hiestimaquantity "+quantity);
			dto.setEstimatedQuantity(new BigDecimal(quantity));
			newQuantity = new Double(quantity);
		}else{
			dto.setEstimatedQuantity(new BigDecimal("0"));
		}*/
		
		/**if(rebQuantity != null && rebQuantity.equalsIgnoreCase(Constants.DEFAULT_STRING)){
			dto.setRebateQuantity(new BigDecimal("0"));
			
		}else if(rebQuantity != null){
			dto.setRebateQuantity(new BigDecimal(rebQuantity));
			
		}else{
			dto.setRebateQuantity(new BigDecimal("0"));
		}
		
		if(rebatecost != null && rebatecost.equalsIgnoreCase(Constants.DEFAULT_STRING)){
			dto.setRebateCost(new BigDecimal("0"));
		}else if(rebatecost != null){
			dto.setRebateCost(new BigDecimal(rebatecost));
		}else{
			dto.setRebateCost(new BigDecimal("0"));
		}*/
		
		
		
		
		if(reuquantity != null && reuquantity.equalsIgnoreCase(Constants.DEFAULT_STRING)){
			dto.setReusableQuantity(new BigDecimal("0"));
			
		}else if(reuquantity != null){
			dto.setReusableQuantity(new BigDecimal(reuquantity));
			
		}else{
			dto.setReusableQuantity(new BigDecimal("0"));
		}
		
		/**
		if(offquantity != null && offquantity.equalsIgnoreCase(Constants.DEFAULT_STRING)){
			dto.setOffChargeQuantity(new BigDecimal("0"));
			
		}else if(offquantity != null){
			dto.setOffChargeQuantity(new BigDecimal(offquantity));
			
		}else{
			dto.setOffChargeQuantity(new BigDecimal("0"));
		}
		
		
		
		System.out.println("Hiestimaquantity "+estimatedCost);
		if(estimatedCost != null && estimatedCost.equalsIgnoreCase(Constants.DEFAULT_STRING)){
			dto.setEstimateCost(new BigDecimal("0"));
		}else if(estimatedCost != null){
			System.out.println("Hiestimaquantity "+estimatedCost);
			dto.setEstimateCost(new BigDecimal(estimatedCost));
		}else{
			dto.setEstimateCost(new BigDecimal("0"));
		}
		if(unitcost != null && unitcost.equalsIgnoreCase(Constants.DEFAULT_STRING)){
			dto.setUnitPrice(new BigDecimal("0"));
		}else if(unitcost != null){
			dto.setUnitPrice(new BigDecimal(unitcost));
		}else{
			dto.setUnitPrice(new BigDecimal("0"));
		}
		
		//dto.setEstimateCost(new BigDecimal(estimatedCost));
		/*if(lineId.trim().equalsIgnoreCase("L0825") || lineId.trim().equalsIgnoreCase("L0830")){
			itemFinalQuantity = new BigDecimal(new BigDecimal(quantity).doubleValue() +new BigDecimal(quantity).doubleValue()*.03);
		}else if(lineId.trim().equalsIgnoreCase("D0605") || lineId.trim().equalsIgnoreCase("D0610")){
			double setOfWireBinding = new BigDecimal(quantity).doubleValue()/0.25;
			if(setOfWireBinding <= 0){
				itemFinalQuantity = new BigDecimal(0.25);
			}else{
				itemFinalQuantity = new BigDecimal(setOfWireBinding * 0.25);
			}
		}else{
			itemFinalQuantity = new BigDecimal(quantity);
		}*/
		
		try {
			packet.put("estimateCost", new BigDecimal(estimatedCost).doubleValue()*new BigDecimal(quantity).doubleValue());
			//packet.put("estimateQuantity",itemFinalQuantity );
			//packet.put("lineId",lineId );
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		if(newQuantity == oldQuantity ){
			balance =0;
		}else 
			balance = newQuantity-oldQuantity;
		}
		if(alreadyAddedDetails != null){
			if(alreadyAddedDetails.get(lineId) != null){
				updatedDetailsMap.put(lineId.trim(), dto);
			
				pegItemDTO = populatePegItemDTO(costCenterNo,dto);
				
				if(balance != 0){
					pegItemDTO = populatePegItemDTO(costCenterNo,dto);
					descriSummary = pegItemDTO.getNodeDescription();
					pegItemDTO.setNodeDescription("MODIFY :" +descriSummary);
					pegItemDTO.setNoOfItem(new BigDecimal(balance));
					try {
						packet.put("selectedPegId", pegItemDTO.getNodeId());
					
						packet.put("quantity",balance);
						packet.put("description", "MODIFY :" +descriSummary);
						pegItemMap.put(pegItemDTO.getNodeId(), pegItemDTO);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}else if(resourceMap != null){
				resourceMap.put(lineId.trim(),dto);
				if(oldQuantity ==0 || lineId.trim().contains("NPL")){
					pegItemDTO = populatePegItemDTO(costCenterNo,dto);
					descriSummary = pegItemDTO.getNodeDescription();
					pegItemDTO.setNodeDescription("ADD :" +descriSummary);
					pegItemDTO.setNoOfItem(new BigDecimal(quantity));
					try{
						packet.put("selectedPegId", pegItemDTO.getNodeId());
						packet.put("quantity",quantity);
						packet.put("description", "ADD :" +descriSummary);
						pegItemMap.put(pegItemDTO.getNodeId(), pegItemDTO);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else{
					pegItemDTO = populatePegItemDTO(costCenterNo,dto);
					pegItemDTO.setNodeDescription(pegItemDTO.getNodeId());
					descriSummary = pegItemDTO.getNodeDescription();
					pegItemDTO.setNoOfItem(new BigDecimal(balance));
					pegItemDTO.setNodeDescription("MODIFY :" +descriSummary);
					try{
						packet.put("selectedPegId", pegItemDTO.getNodeId());
						packet.put("quantity",balance);
						packet.put("description", "MODIFY :" +descriSummary);
						pegItemMap.put(pegItemDTO.getNodeId(), pegItemDTO);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
		}
			
		
	


	
	request.getSession().setAttribute("pegItemDetails",pegItemMap);
	
	request.getSession().setAttribute("detailEstimationDetails",resourceMap);
	request.getSession().setAttribute("allSelectedAdditionalResources",allSelectedAdditionalResoMap);
	request.getSession().setAttribute("updatedDetails",updatedDetailsMap);
	return packet;
}


public JSONObject getEstimatedUpdateDetailsForOffCharge(HttpServletRequest request){
	
	System.out.println("getEstimatedUpdateDetailsForRebate");
	String costCenterNo=(String) request.getSession().getAttribute("costCenterNo");
	JSONObject packet =new JSONObject();
	String quantity = request.getParameter("quantity");
	String rebQuantity = request.getParameter("rebquantity");
	
	String rebatecost = request.getParameter("rebatecost");
	String reuquantity = request.getParameter("reuquantity");
	String offquantity = request.getParameter("offquantity");
	String lineId = request.getParameter("lineid");
	String estimatedCost = request.getParameter("linecost");
	String unitcost = request.getParameter("unitcost");
	PegItemDTO pegItemDTO = null;
	String descriSummary = null;
	System.out.println("Hirebate "+rebQuantity);
	System.out.println("Hirebate "+rebQuantity);
	double balance = 0;
	allSelectedAdditionalResoMap = (Map<String,DetailEstimateDTO>) request.getSession().getAttribute("allSelectedAdditionalResources");
	updatedDetailsMap = (Map<String,DetailEstimateDTO>) request.getSession().getAttribute("updatedDetails");
	alreadyAddedDetails = (Map<String,DetailEstimateDTO>) request.getSession().getAttribute("alreadyAddedDetails");
	resourceMap = (Map<String,DetailEstimateDTO>) request.getSession().getAttribute("detailEstimationDetails");
	pegItemMap =(Map<String,PegItemDTO>) request.getSession().getAttribute("pegItemDetails");
	if(updatedDetailsMap == null){
		updatedDetailsMap = new HashMap<String, DetailEstimateDTO>();
	}
	if(pegItemMap == null){
		pegItemMap = new TreeMap<String, PegItemDTO>();
	}
	if(alreadyAddedDetails == null){
		alreadyAddedDetails = new TreeMap<String, DetailEstimateDTO>();
	}
	DetailEstimateDTO dto = allSelectedAdditionalResoMap.get(lineId);
	double oldQuantity =0.0;
	double oldRebQuantity =0.0;
	if(dto != null && dto.getEstimatedQuantity() != null){
		
		System.out.println("Hiestima "+dto.getEstimatedQuantity());
		oldQuantity = dto.getEstimatedQuantity().doubleValue(); 
	}
	double newQuantity = 0;
	double newRebQuantity = 0;
	//BigDecimal itemFinalQuantity = new  BigDecimal("0");
	if(dto != null){
		System.out.println("Hiestimaquantity "+quantity);
		/**if(quantity != null && quantity.equalsIgnoreCase(Constants.DEFAULT_STRING)){
			dto.setEstimatedQuantity(new BigDecimal("0"));
			
		}else if(quantity != null){
			System.out.println("Hiestimaquantity "+quantity);
			dto.setEstimatedQuantity(new BigDecimal(quantity));
			newQuantity = new Double(quantity);
		}else{
			dto.setEstimatedQuantity(new BigDecimal("0"));
		}*/
		
		/**if(rebQuantity != null && rebQuantity.equalsIgnoreCase(Constants.DEFAULT_STRING)){
			dto.setRebateQuantity(new BigDecimal("0"));
			
		}else if(rebQuantity != null){
			dto.setRebateQuantity(new BigDecimal(rebQuantity));
			
		}else{
			dto.setRebateQuantity(new BigDecimal("0"));
		}
		
		if(rebatecost != null && rebatecost.equalsIgnoreCase(Constants.DEFAULT_STRING)){
			dto.setRebateCost(new BigDecimal("0"));
		}else if(rebatecost != null){
			dto.setRebateCost(new BigDecimal(rebatecost));
		}else{
			dto.setRebateCost(new BigDecimal("0"));
		}
		
		
		
		
		if(reuquantity != null && reuquantity.equalsIgnoreCase(Constants.DEFAULT_STRING)){
			dto.setReusableQuantity(new BigDecimal("0"));
			
		}else if(reuquantity != null){
			dto.setReusableQuantity(new BigDecimal(reuquantity));
			
		}else{
			dto.setReusableQuantity(new BigDecimal("0"));
		}*/
		
	
		if(offquantity != null && offquantity.equalsIgnoreCase(Constants.DEFAULT_STRING)){
			dto.setOffChargeQuantity(new BigDecimal("0"));
			
		}else if(offquantity != null){
			dto.setOffChargeQuantity(new BigDecimal(offquantity));
			
		}else{
			dto.setOffChargeQuantity(new BigDecimal("0"));
		}
		
		
		/**
		System.out.println("Hiestimaquantity "+estimatedCost);
		if(estimatedCost != null && estimatedCost.equalsIgnoreCase(Constants.DEFAULT_STRING)){
			dto.setEstimateCost(new BigDecimal("0"));
		}else if(estimatedCost != null){
			System.out.println("Hiestimaquantity "+estimatedCost);
			dto.setEstimateCost(new BigDecimal(estimatedCost));
		}else{
			dto.setEstimateCost(new BigDecimal("0"));
		}
		if(unitcost != null && unitcost.equalsIgnoreCase(Constants.DEFAULT_STRING)){
			dto.setUnitPrice(new BigDecimal("0"));
		}else if(unitcost != null){
			dto.setUnitPrice(new BigDecimal(unitcost));
		}else{
			dto.setUnitPrice(new BigDecimal("0"));
		}
		
		//dto.setEstimateCost(new BigDecimal(estimatedCost));
		/*if(lineId.trim().equalsIgnoreCase("L0825") || lineId.trim().equalsIgnoreCase("L0830")){
			itemFinalQuantity = new BigDecimal(new BigDecimal(quantity).doubleValue() +new BigDecimal(quantity).doubleValue()*.03);
		}else if(lineId.trim().equalsIgnoreCase("D0605") || lineId.trim().equalsIgnoreCase("D0610")){
			double setOfWireBinding = new BigDecimal(quantity).doubleValue()/0.25;
			if(setOfWireBinding <= 0){
				itemFinalQuantity = new BigDecimal(0.25);
			}else{
				itemFinalQuantity = new BigDecimal(setOfWireBinding * 0.25);
			}
		}else{
			itemFinalQuantity = new BigDecimal(quantity);
		}*/
		
		try {
			packet.put("estimateCost", new BigDecimal(estimatedCost).doubleValue()*new BigDecimal(quantity).doubleValue());
			//packet.put("estimateQuantity",itemFinalQuantity );
			//packet.put("lineId",lineId );
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		if(newQuantity == oldQuantity ){
			balance =0;
		}else 
			balance = newQuantity-oldQuantity;
		}
		if(alreadyAddedDetails != null){
			if(alreadyAddedDetails.get(lineId) != null){
				updatedDetailsMap.put(lineId.trim(), dto);
			
				pegItemDTO = populatePegItemDTO(costCenterNo,dto);
				
				if(balance != 0){
					pegItemDTO = populatePegItemDTO(costCenterNo,dto);
					descriSummary = pegItemDTO.getNodeDescription();
					pegItemDTO.setNodeDescription("MODIFY :" +descriSummary);
					pegItemDTO.setNoOfItem(new BigDecimal(balance));
					try {
						packet.put("selectedPegId", pegItemDTO.getNodeId());
					
						packet.put("quantity",balance);
						packet.put("description", "MODIFY :" +descriSummary);
						pegItemMap.put(pegItemDTO.getNodeId(), pegItemDTO);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}else if(resourceMap != null){
				resourceMap.put(lineId.trim(),dto);
				if(oldQuantity ==0 || lineId.trim().contains("NPL")){
					pegItemDTO = populatePegItemDTO(costCenterNo,dto);
					descriSummary = pegItemDTO.getNodeDescription();
					pegItemDTO.setNodeDescription("ADD :" +descriSummary);
					pegItemDTO.setNoOfItem(new BigDecimal(quantity));
					try{
						packet.put("selectedPegId", pegItemDTO.getNodeId());
						packet.put("quantity",quantity);
						packet.put("description", "ADD :" +descriSummary);
						pegItemMap.put(pegItemDTO.getNodeId(), pegItemDTO);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else{
					pegItemDTO = populatePegItemDTO(costCenterNo,dto);
					pegItemDTO.setNodeDescription(pegItemDTO.getNodeId());
					descriSummary = pegItemDTO.getNodeDescription();
					pegItemDTO.setNoOfItem(new BigDecimal(balance));
					pegItemDTO.setNodeDescription("MODIFY :" +descriSummary);
					try{
						packet.put("selectedPegId", pegItemDTO.getNodeId());
						packet.put("quantity",balance);
						packet.put("description", "MODIFY :" +descriSummary);
						pegItemMap.put(pegItemDTO.getNodeId(), pegItemDTO);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
		}
			
		
	


	
	request.getSession().setAttribute("pegItemDetails",pegItemMap);
	
	request.getSession().setAttribute("detailEstimationDetails",resourceMap);
	request.getSession().setAttribute("allSelectedAdditionalResources",allSelectedAdditionalResoMap);
	request.getSession().setAttribute("updatedDetails",updatedDetailsMap);
	return packet;
}


public JSONObject getEstimatedUpdateDetailsForPeg(HttpServletRequest request){
	String costCenterNo=(String) request.getSession().getAttribute("costCenterNo");
	JSONObject packet =new JSONObject();
	String quantity = request.getParameter("quantity");
	
	String lineId = request.getParameter("lineid");
	String estimatedCost = request.getParameter("linecost");
	String unitcost = request.getParameter("unitcost");
	PegItemDTO pegItemDTO = null;
	String descriSummary = null;
	double balance = 0;
	allSelectedAdditionalResoMap = (Map<String,DetailEstimateDTO>) request.getSession().getAttribute("allSelectedAdditionalResources");
	updatedDetailsMap = (Map<String,DetailEstimateDTO>) request.getSession().getAttribute("updatedDetails");
	alreadyAddedDetails = (Map<String,DetailEstimateDTO>) request.getSession().getAttribute("alreadyAddedDetails");
	resourceMap = (Map<String,DetailEstimateDTO>) request.getSession().getAttribute("detailEstimationDetails");
	pegItemMap =(Map<String,PegItemDTO>) request.getSession().getAttribute("pegItemDetails");
	if(updatedDetailsMap == null){
		updatedDetailsMap = new HashMap<String, DetailEstimateDTO>();
	}
	if(pegItemMap == null){
		pegItemMap = new TreeMap<String, PegItemDTO>();
	}
	if(alreadyAddedDetails == null){
		alreadyAddedDetails = new TreeMap<String, DetailEstimateDTO>();
	}
	DetailEstimateDTO dto = allSelectedAdditionalResoMap.get(lineId);
	double oldQuantity =0.0;
	if(dto != null && dto.getEstimatedQuantity() != null){
		oldQuantity = dto.getEstimatedQuantity().doubleValue(); 
	}
	double newQuantity = 0;
	//BigDecimal itemFinalQuantity = new  BigDecimal("0");
	if(dto != null){
		if(quantity != null && quantity.equalsIgnoreCase(Constants.DEFAULT_STRING)){
			dto.setEstimatedQuantity(new BigDecimal("0"));
			
		}else if(quantity != null){
			dto.setEstimatedQuantity(new BigDecimal(quantity));
			newQuantity = new Double(quantity);
		}else{
			dto.setEstimatedQuantity(new BigDecimal("0"));
		}
		if(estimatedCost != null && estimatedCost.equalsIgnoreCase(Constants.DEFAULT_STRING)){
			dto.setEstimateCost(new BigDecimal("0"));
		}else if(estimatedCost != null){
			dto.setEstimateCost(new BigDecimal(estimatedCost));
		}else{
			dto.setEstimateCost(new BigDecimal("0"));
		}
		if(unitcost != null && unitcost.equalsIgnoreCase(Constants.DEFAULT_STRING)){
			dto.setUnitPrice(new BigDecimal("0"));
		}else if(unitcost != null){
			dto.setUnitPrice(new BigDecimal(unitcost));
		}else{
			dto.setUnitPrice(new BigDecimal("0"));
		}
		
		//dto.setEstimateCost(new BigDecimal(estimatedCost));
		/*if(lineId.trim().equalsIgnoreCase("L0825") || lineId.trim().equalsIgnoreCase("L0830")){
			itemFinalQuantity = new BigDecimal(new BigDecimal(quantity).doubleValue() +new BigDecimal(quantity).doubleValue()*.03);
		}else if(lineId.trim().equalsIgnoreCase("D0605") || lineId.trim().equalsIgnoreCase("D0610")){
			double setOfWireBinding = new BigDecimal(quantity).doubleValue()/0.25;
			if(setOfWireBinding <= 0){
				itemFinalQuantity = new BigDecimal(0.25);
			}else{
				itemFinalQuantity = new BigDecimal(setOfWireBinding * 0.25);
			}
		}else{
			itemFinalQuantity = new BigDecimal(quantity);
		}*/
		
		try {
			packet.put("estimateCost", new BigDecimal(estimatedCost).doubleValue()*new BigDecimal(quantity).doubleValue());
			//packet.put("estimateQuantity",itemFinalQuantity );
			//packet.put("lineId",lineId );
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		if(newQuantity == oldQuantity ){
			balance =0;
		}else 
			balance = newQuantity-oldQuantity;
		}
		if(alreadyAddedDetails != null){
			if(alreadyAddedDetails.get(lineId) != null){
				updatedDetailsMap.put(lineId.trim(), dto);
			
				pegItemDTO = populatePegItemDTO(costCenterNo,dto);
				
				if(balance != 0){
					pegItemDTO = populatePegItemDTO(costCenterNo,dto);
					descriSummary = pegItemDTO.getNodeDescription();
					pegItemDTO.setNodeDescription("MODIFY :" +descriSummary);
					pegItemDTO.setNoOfItem(new BigDecimal(balance));
					try {
						packet.put("selectedPegId", pegItemDTO.getNodeId());
					
						packet.put("quantity",balance);
						packet.put("description", "MODIFY :" +descriSummary);
						pegItemMap.put(pegItemDTO.getNodeId(), pegItemDTO);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}else if(resourceMap != null){
				resourceMap.put(lineId.trim(),dto);
				if(oldQuantity ==0 || lineId.trim().contains("NPL")){
					pegItemDTO = populatePegItemDTO(costCenterNo,dto);
					descriSummary = pegItemDTO.getNodeDescription();
					pegItemDTO.setNodeDescription("ADD :" +descriSummary);
					pegItemDTO.setNoOfItem(new BigDecimal(quantity));
					try{
						packet.put("selectedPegId", pegItemDTO.getNodeId());
						packet.put("quantity",quantity);
						packet.put("description", "ADD :" +descriSummary);
						pegItemMap.put(pegItemDTO.getNodeId(), pegItemDTO);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else{
					pegItemDTO = populatePegItemDTO(costCenterNo,dto);
					pegItemDTO.setNodeDescription(pegItemDTO.getNodeId());
					descriSummary = pegItemDTO.getNodeDescription();
					pegItemDTO.setNoOfItem(new BigDecimal(balance));
					pegItemDTO.setNodeDescription("MODIFY :" +descriSummary);
					try{
						packet.put("selectedPegId", pegItemDTO.getNodeId());
						packet.put("quantity",balance);
						packet.put("description", "MODIFY :" +descriSummary);
						pegItemMap.put(pegItemDTO.getNodeId(), pegItemDTO);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
		}
			
		
	


	
	request.getSession().setAttribute("pegItemDetails",pegItemMap);
	
	request.getSession().setAttribute("detailEstimationDetails",resourceMap);
	request.getSession().setAttribute("allSelectedAdditionalResources",allSelectedAdditionalResoMap);
	request.getSession().setAttribute("updatedDetails",updatedDetailsMap);
	return packet;
}
private PegItemDTO populatePegItemDTO(String costCenter,DetailEstimateDTO detailEstimateDTO){
	
 	Long revNo = 2l;
    String genRes = "F";
    String normDefault = "F";
    PegItemDTO peg = new PegItemDTO();
	List<Pegschdmt> lst = new ArrayList<Pegschdmt>();
	if(detailEstimateDTO != null){
		//for(PegItemDTO dto :detaList){
			peg.setNodeDescription(detailEstimateDTO.getResourceName()+"/"+detailEstimateDTO.getResourceCode());
			peg.setNodeId(detailEstimateDTO.getResourceCode());
			peg.setNoOfItem(detailEstimateDTO.getEstimatedQuantity());
			
			//lst.add(pegschdmt);
		//}
	}
	
	
	
	return peg;
	
}
public JSONObject getJobUpdateDetails(HttpServletRequest request){
	JSONObject packet =new JSONObject();
	String quantity = request.getParameter("quantity");
	
	String lineId = request.getParameter("lineid");
	String estimatedCost = request.getParameter("linecost");
	String unitcost = request.getParameter("unitcost");
	
	allSelectedAdditionalResoMap = (Map<String,DetailEstimateDTO>) request.getSession().getAttribute("allSelectedAdditionalResources");
	updatedDetailsMap = (Map<String,DetailEstimateDTO>) request.getSession().getAttribute("updatedDetails");
	alreadyAddedDetails = (Map<String,DetailEstimateDTO>) request.getSession().getAttribute("alreadyAddedDetails");
	resourceMap = (Map<String,DetailEstimateDTO>) request.getSession().getAttribute("detailEstimationDetails");
	EstimateDetails estimateDetailobject=(EstimateDetails) request.getSession().getAttribute("estimateDetailobject");
	if(updatedDetailsMap == null){
		updatedDetailsMap = new HashMap<String, DetailEstimateDTO>();
	}
	DetailEstimateDTO dto = allSelectedAdditionalResoMap.get(lineId);
	if(dto != null){
		if(quantity != null && quantity.equalsIgnoreCase(Constants.DEFAULT_STRING)){
			dto.setEstimatedQuantity(new BigDecimal("0"));
		}else{
			dto.setEstimatedQuantity(new BigDecimal(quantity));
		}
		if(estimatedCost != null && estimatedCost.equalsIgnoreCase(Constants.DEFAULT_STRING)){
			dto.setEstimateCost(new BigDecimal("0"));
		}else{
			dto.setEstimateCost(new BigDecimal(estimatedCost));
		}
		if(unitcost != null && unitcost.equalsIgnoreCase(Constants.DEFAULT_STRING)){
			dto.setUnitPrice(new BigDecimal("0"));
		}else if(unitcost != null){
			dto.setUnitPrice(new BigDecimal(unitcost));
		}else{
			dto.setUnitPrice(new BigDecimal("0"));
		}
		//dto.setEstimateCost(new BigDecimal(estimatedCost));
		
		try {
			
			Pcestdmt dmt = alreadyAddedDmtDetails.get(lineId);
			if(dmt != null){
				if(dmt.getIssuedQty() != null || dmt.getIssuedCost() != null || dmt.getApprovedQty() != null || dmt.getApprovedCost() != null || dmt.getCommitedQty() != null || dmt.getCommitedCost() != null){
					
					try {
						packet.put("invalid",true);
						packet.put("errorMessage", "Record can not be updated");
					} catch (JSONException e) {
						try {
							packet.put("errorMessage", "Error Occured while updating");
						} catch (JSONException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						e.printStackTrace();
					}
					
					
					return packet;
				}
			}
			packet.put("estimateCost", new BigDecimal(estimatedCost).doubleValue()*new BigDecimal(quantity).doubleValue());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(alreadyAddedDetails != null && alreadyAddedDetails.size() > 0){
			if(alreadyAddedDetails.get(lineId) != null){
				updatedDetailsMap.put(lineId.trim(), dto);
			}else{
				if(resourceMap != null){
					resourceMap.put(lineId.trim(),dto);
				}
			}
			allSelectedAdditionalResoMap.put(lineId.trim(),dto);
		}
		double allTotalCost=populateSum(allSelectedAdditionalResoMap);
		
		if(estimateDetailobject != null && estimateDetailobject.getHtt() != null){
			try {
				if(estimateDetailobject.getHtt().getStdCost() != null){
					packet.put("previousTotalCost", estimateDetailobject.getHtt().getStdCost());
				}
				
				packet.put("currentTotalCost", allTotalCost);
				
				if(estimateDetailobject.getHtt().getStdCost() != null){
					double deduction = allTotalCost-estimateDetailobject.getHtt().getStdCost().doubleValue();
					if(deduction != 0.0){
						packet.put("variance",(deduction/estimateDetailobject.getHtt().getStdCost().doubleValue())*100 +" %");
					}
				}
				//packet.put("estimateCost", new BigDecimal(estimatedCost).doubleValue()*new BigDecimal(quantity).doubleValue());
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	request.getSession().setAttribute("detailEstimationDetails",resourceMap);
	request.getSession().setAttribute("allSelectedAdditionalResources",allSelectedAdditionalResoMap);
	request.getSession().setAttribute("updatedDetails",updatedDetailsMap);
	return packet;
}
/**public JSONObject  loadEstimationDetails(HttpServletRequest request) throws JSONException{
	JSONObject packet =new JSONObject();
	String region= (String) request.getSession().getAttribute("region");
	allSelectedAdditionalResoMap = (Map<String,DetailEstimateDTO>) request.getSession().getAttribute("allSelectedAdditionalResources");
	pegItemMap =(Map<String,PegItemDTO>) request.getSession().getAttribute("pegItemDetails");
	
	pegIdList =  (List<String>) request.getSession().getAttribute("selectedpegIdList");
	existingPegItemMap =(Map<String,PegItemDTO>) request.getSession().getAttribute("existingPegItemMap");
	
	if(allSelectedAdditionalResoMap == null){
		allSelectedAdditionalResoMap = new HashMap<String, DetailEstimateDTO>();
	}
	
	alreadyAddedDetails = (Map<String,DetailEstimateDTO>) request.getSession().getAttribute("alreadyAddedDetails");
	
	if(alreadyAddedDetails == null){
		alreadyAddedDetails = new HashMap<String, DetailEstimateDTO>();
	}
	if(existingPegItemMap == null){
		existingPegItemMap = new HashMap<String, PegItemDTO>();
	}
	if(pegItemMap == null){
		pegItemMap = new HashMap<String, PegItemDTO>();
	}
	if(pegIdList == null){
		pegIdList = new ArrayList<String>();
	}
	JSONArray pegArray = new JSONArray();
	JSONArray pegDetailsRowaray = new JSONArray();
	EstimateDetails detail = new EstimateDetails();
	try{
		
		String sessionKey= (String) request.getSession().getAttribute("region");
		String applicationNo = request.getParameter("applicationNo");
		String estimateNo = request.getParameter("estimateNo");
		String costCenterNo=(String) request.getSession().getAttribute("costCenterNo");
		String smcType = request.getParameter("smcType");
		String userRole= (String) request.getSession().getAttribute("userRole");
		PcesthttEjb pc = new PcesthttEjb(sessionKey);
		Pcesthtt pchtt =  pc.findByEstimationNo(estimateNo, costCenterNo,Constants.REV_NO);
		List<String> estimateNos = new ArrayList<String>();
		
		boolean isExistCommeRef = false;
		String applicationRefNo = null;
		EstimateReferenceEjb estejb = new EstimateReferenceEjb();
		EstimateReference refe = estejb.findByWorkEstimateNo(estimateNo.trim(),costCenterNo, sessionKey);
		
		
		
		InmatmEjb matejb = new InmatmEjb(sessionKey);
		EstimateEjb ejb = new EstimateEjb(sessionKey);
		if(refe != null){
			packet.put("stdEstimateNo", refe.getId().getStandardEstimateNo());
			packet.put("fileRef", refe.getFileReference());
			applicationRefNo = refe.getId().getStandardEstimateNo();
			estimateNos.add(refe.getId().getStandardEstimateNo());
			request.getSession().setAttribute("estimationRefNos",estimateNos);
			String commCost = costCenterNo.substring(0, 3);
			SpstdesthmtEjb spejb = new SpstdesthmtEjb(sessionKey);
			SpstdesthmtPK spstdesthmtPK = new SpstdesthmtPK();
			spstdesthmtPK.setApplicationNo(applicationNo);
			spstdesthmtPK.setDeptId(commCost+".00");
			spstdesthmtPK.setStdNo(refe.getId().getStandardEstimateNo());
			spstdesthmtPK.setApplicationNo(refe.getId().getStandardEstimateNo());
			Spstdesthmt hmt = spejb.findById(spstdesthmtPK, sessionKey);
			if(hmt != null){
				isExistCommeRef = true;
				NumberFormat nf = NumberFormat.getInstance();
				nf.setGroupingUsed(true);
				nf.setMaximumFractionDigits(2);
				nf.setMinimumFractionDigits(2);
				double deuction = 0.0;
				if(hmt.getRebateCost() != null){
					deuction = hmt.getRebateCost().doubleValue()+hmt.getTotalCost().doubleValue();
				}else{
					deuction = hmt.getTotalCost().doubleValue();
				}
				double workECost = 0.0;
				if(pchtt.getStdCost() != null){
					workECost = pchtt.getStdCost().doubleValue();
				}
				double variance = 0;
				if(hmt.getTotalCost() != null && hmt.getTotalCost().doubleValue()!= 0.0){
					variance = ((workECost - deuction)/hmt.getTotalCost().doubleValue())*100;
				}
				
				packet.put("stdtotalCost",hmt.getTotalCost() != null ? nf.format(hmt.getTotalCost().doubleValue()) : "0.00");
				packet.put("rebateCost", hmt.getRebateCost() != null ? nf.format(hmt.getRebateCost().doubleValue()) : "0.00");
				packet.put("variance",variance+"%");
				
				WiringLandDetailConEjb wiringLandDetailConEjb = new WiringLandDetailConEjb(sessionKey);
				WiringLandDetailCon schemaDetails = wiringLandDetailConEjb.findByApplicationNo(refe.getId().getStandardEstimateNo());
				if(schemaDetails != null){
					packet.put("schemeDetails", schemaDetails.getSchemeName() == null ? schemaDetails.getSchemeNo() : schemaDetails.getSchemeName());
					
				}
				
			}
			
			
			
		}
		if (pchtt!=null){
			
			if(pchtt.getStdCost() != null){
				packet.put("totalCost",nf.format(pchtt.getStdCost().doubleValue()));
			}
			
			if(pchtt.getStdCost() != null ){
				packet.put("isApprovable", EstimateApproval.getApprovalStatus(pchtt.getStdCost().doubleValue(), userRole));
			}
			
			packet.put("rebate", pchtt.getPartialAmt());
			packet.put("estimateDatePicker", pchtt.getEtimateDt());
			if(pchtt.getFundSource() != null){
				packet.put("fundsou", pchtt.getFundSource().trim());
			}
			if(pchtt.getFundId() != null){
				packet.put("fundid", pchtt.getFundId().trim());
			}
			packet.put("description", pchtt.getDescr());
			if(pchtt.getCatCd() != null){
				packet.put("categoryId", pchtt.getCatCd().trim());
			}
			packet.put("costCenter", costCenterNo);
			packet.put("rejectedReason", getRejectedReason(pchtt,userRole,sessionKey));
			
			detail.setHtt(pchtt);
		}
		
		EstimateEjb estimateEjb = new EstimateEjb(sessionKey); 
		if(isExistCommeRef && applicationRefNo != null){
			PivDetailEjb ejbPiv = new PivDetailEjb(sessionKey);
			PivDetail piv = ejbPiv.findByReferenceNo(applicationRefNo, ReferenceType.ESTIMATE);
			if(piv != null){
				packet.put("pivNo", piv.getId().getPivNo());
				packet.put("amount", nf.format(piv.getPivAmount()));
				packet.put("pivDatePicker", piv.getConfirmedDate());
			}
		}else{
			TempTb temptb = estimateEjb.findPIVDetails(estimateNo.trim(), costCenterNo, sessionKey);
			if(temptb != null){
				packet.put("pivNo", temptb.getPivNo());
				if(temptb.getPivAmount() != null){
					packet.put("amount", nf.format(temptb.getPivAmount()));
				}else{
					packet.put("amount","0.00");
				}
				packet.put("pivDatePicker", temptb.getPivDate() != null ? temptb.getPivDate() : "");
				detail.setTb(temptb);
			}
		}
		
		
		PcestdttEjb pd = new PcestdttEjb(sessionKey);
		List<Pcestdtt> pcdtts =  pd.findByEstimationNo(estimateNo, costCenterNo,Constants.REV_NO);

		
		if(pcdtts != null && pcdtts.size() != 0){
			
				detail.setDtts(pcdtts);
				for(Pcestdtt dtt : pcdtts){
					JSONObject estimateDetailsRow =new JSONObject();
					
					estimateDetailsRow.put("resourceType", dtt.getResType());
					estimateDetailsRow.put("resourceCategory",dtt.getResCat());
					estimateDetailsRow.put("resourceCode",dtt.getId().getResCd().trim());
					
					if(dtt.getId().getResCd().trim().equalsIgnoreCase("MAT")){
						estimateDetailsRow.put("resourceName", Constants.MAT_RESOUR_NAME);
					}else if(matejb.findName(dtt.getId().getResCd().trim()) == null){
						estimateDetailsRow.put("resourceName", dtt.getId().getResCd().trim());
					}else{
						estimateDetailsRow.put("resourceName", matejb.findName(dtt.getId().getResCd().trim()));
					}
					estimateDetailsRow.put("unitPrice", dtt.getUnitPrice());
					estimateDetailsRow.put("uom", dtt.getUom());
					
					estimateDetailsRow.put("estimateQuantity",dtt.getEstimateQty());
					estimateDetailsRow.put("estimateCost", dtt.getEstimateCost());
					pegArray.put(estimateDetailsRow);
					
					DetailEstimateDTO detailEstimateDTO = new DetailEstimateDTO();
					detailEstimateDTO.setResourceCode(dtt.getId().getResCd().trim());
					detailEstimateDTO.setUom(dtt.getUom());
					detailEstimateDTO.setEstimateCost(dtt.getEstimateCost());
					detailEstimateDTO.setEstimatedQuantity(dtt.getEstimateQty());
					detailEstimateDTO.setResourceType(dtt.getResType());
					detailEstimateDTO.setResourceName(dtt.getId().getResCd());
					detailEstimateDTO.setUnitPrice(dtt.getUnitPrice());
					
					allSelectedAdditionalResoMap.put(dtt.getId().getResCd().trim(), detailEstimateDTO);
					alreadyAddedDetails.put(dtt.getId().getResCd().trim(), detailEstimateDTO);
					
				}
				
				
				packet.put("estimateDetails", pegArray);
			
			}
		
		 String estStatus = pchtt.getStatus().toString() ;
         EstimateStatus estSt = new EstimateStatus();
         
         packet.put("status", estSt.searchStatus((Integer.parseInt(estStatus))));
         
         
		PegschdmtEjb pegEjb = new PegschdmtEjb(sessionKey);
		List<Pegschdmt> list = pegEjb.findByEstimationNo(estimateNo.trim(), costCenterNo, sessionKey);
		if(list != null && list.size() > 0){
			for(Pegschdmt pegschdmt : list){
				JSONObject pegDetailsRow =new JSONObject();
			
				
				pegDetailsRow.put("selectedPegId", pegschdmt.getId().getNodeId().trim());
				pegDetailsRow.put("quantity",pegschdmt.getNoOfItem());
				pegDetailsRow.put("description",pegschdmt.getNodeDes());
				
				pegDetailsRowaray.put(pegDetailsRow);
				
				PegItemDTO dto = new PegItemDTO();
				dto.setNodeId(pegschdmt.getId().getNodeId().trim());
				dto.setDeptId(costCenterNo);
				dto.setEstimateNo(estimateNo.trim());
				dto.setNodeDescription(pegschdmt.getNodeDes());
				dto.setNoOfItem(pegschdmt.getNoOfItem());
				dto.setEstimateNo(estimateNo.trim());
				pegItemMap.put(pegschdmt.getId().getNodeId().trim(), dto);
				existingPegItemMap.put(pegschdmt.getId().getNodeId().trim(), dto);
				pegIdList.add(pegschdmt.getId().getNodeId().trim());
				
			}
			packet.put("pegDetails", pegDetailsRowaray);
		}
		
		
		request.getSession().setAttribute("pegItemDetails",pegItemMap);
		request.getSession().setAttribute("selectedpegIdList",pegIdList);
		
		request.getSession().setAttribute("existingPegItemMap",existingPegItemMap);
		
		request.getSession().setAttribute("allSelectedAdditionalResources",allSelectedAdditionalResoMap);
		request.getSession().setAttribute("alreadyAddedDetails",alreadyAddedDetails);
		request.getSession().setAttribute("estimateDetailobject",detail);
	}catch(Exception e){
		
	}
	return packet;

}*/


public JSONObject  loadEstimationDetails(HttpServletRequest request) throws JSONException{
	JSONObject packet =new JSONObject();
	String region= (String) request.getSession().getAttribute("region");
	allSelectedAdditionalResoMap = (Map<String,DetailEstimateDTO>) request.getSession().getAttribute("allSelectedAdditionalResources");
	pegItemMap =(Map<String,PegItemDTO>) request.getSession().getAttribute("pegItemDetails");
	//pegIdList =  (List<String>) request.getSession().getAttribute("selectedpegIdList");
	
	pegIdList =  (List<String>) request.getSession().getAttribute("selectedpegIdList");
	existingPegItemMap =(Map<String,PegItemDTO>) request.getSession().getAttribute("existingPegItemMap");
	
	if(allSelectedAdditionalResoMap == null){
		allSelectedAdditionalResoMap = new HashMap<String, DetailEstimateDTO>();
	}
	
	alreadyAddedDetails = (Map<String,DetailEstimateDTO>) request.getSession().getAttribute("alreadyAddedDetails");
	
	if(alreadyAddedDetails == null){
		alreadyAddedDetails = new HashMap<String, DetailEstimateDTO>();
	}
	if(existingPegItemMap == null){
		existingPegItemMap = new HashMap<String, PegItemDTO>();
	}
	if(pegItemMap == null){
		pegItemMap = new HashMap<String, PegItemDTO>();
	}
	if(pegIdList == null){
		pegIdList = new ArrayList<String>();
	}
	JSONArray pegArray = new JSONArray();
	JSONArray pegDetailsRowaray = new JSONArray();
	EstimateDetails detail = new EstimateDetails();
	try{
		
		String sessionKey= (String) request.getSession().getAttribute("region");
		String applicationNo = request.getParameter("applicationNo");
		String estimateNo = request.getParameter("estimateNo");
		String costCenterNo=(String) request.getSession().getAttribute("costCenterNo");
		String smcType = request.getParameter("smcType");
		String userRole= (String) request.getSession().getAttribute("userRole");
		PcesthttEjb pc = new PcesthttEjb(sessionKey);
		Pcesthtt pchtt =  pc.findByEstimationNo(estimateNo, costCenterNo,Constants.REV_NO);
		List<String> estimateNos = new ArrayList<String>();
		
		boolean isExistCommeRef = false;
		String applicationRefNo = null;
		EstimateReferenceEjb estejb = new EstimateReferenceEjb();
		EstimateReference refe = estejb.findByWorkEstimateNo(estimateNo.trim(),costCenterNo, sessionKey);
		
		/**if(refe != null){
		ApplicationReferenceEjb appEjb = new ApplicationReferenceEjb(sessionKey);
		ApplicationReference appRef = appEjb.findByApplicationNo(refe.getId().getStandardEstimateNo());
		if(appRef != null){
			WiringLandDetailConEjb con = new WiringLandDetailConEjb(sessionKey);
			WiringLandDetailCon conDetail = con.findByApplicationNo(refe.getId().getStandardEstimateNo());
			System.out.println("EST NO: "+refe.getId().getStandardEstimateNo());
			
			packet.put("divSec", conDetail.getDevSec());
			System.out.println("divSec "+conDetail.getDevSec());
			packet.put("area", conDetail.getAreaCode());
			System.out.println("area "+conDetail.getAreaCode());
			packet.put("district", conDetail.getDistrict());
			System.out.println("district "+conDetail.getDistrict());
			packet.put("eCSC", conDetail.getServiceDepoName());
			packet.put("esname", "");
		
		}
		}*/
		
		InmatmEjb matejb = new InmatmEjb(sessionKey);
		EstimateEjb ejb = new EstimateEjb(sessionKey);
		if(refe != null){
			packet.put("stdEstimateNo", refe.getId().getStandardEstimateNo());
			packet.put("fileRef", refe.getFileReference());
			applicationRefNo = refe.getId().getStandardEstimateNo();
			estimateNos.add(refe.getId().getStandardEstimateNo());
			request.getSession().setAttribute("estimationRefNos",estimateNos);
			//loadEstmationNumbers(request);
			String commCost = costCenterNo.substring(0, 3);
			SpstdesthmtEjb spejb = new SpstdesthmtEjb(sessionKey);
			SpstdesthmtPK spstdesthmtPK = new SpstdesthmtPK();
			spstdesthmtPK.setApplicationNo(applicationNo);
			spstdesthmtPK.setDeptId(commCost+".00");
			spstdesthmtPK.setStdNo(refe.getId().getStandardEstimateNo());
			spstdesthmtPK.setApplicationNo(refe.getId().getStandardEstimateNo());
			Spstdesthmt hmt = spejb.findById(spstdesthmtPK, sessionKey);
			if(hmt != null){
				isExistCommeRef = true;
				NumberFormat nf = NumberFormat.getInstance();
				nf.setGroupingUsed(true);
				nf.setMaximumFractionDigits(2);
				nf.setMinimumFractionDigits(2);
				double deuction = 0.0;
				if(hmt.getRebateCost() != null){
					deuction = hmt.getRebateCost().doubleValue()+hmt.getTotalCost().doubleValue();
				}else{
					deuction = hmt.getTotalCost().doubleValue();
				}
				//double deuction = hmt.getRebateCost().doubleValue()+hmt.getTotalCost().doubleValue();
				double workECost = 0.0;
				if(pchtt.getStdCost() != null){
					workECost = pchtt.getStdCost().doubleValue();
				}
				double variance = 0;
				if(hmt.getTotalCost() != null && hmt.getTotalCost().doubleValue()!= 0.0){
					variance = ((workECost - deuction)/hmt.getTotalCost().doubleValue())*100;
				}
				
				packet.put("stdtotalCost",hmt.getTotalCost() != null ? nf.format(hmt.getTotalCost().doubleValue()) : "0.00");
				packet.put("rebateCost", hmt.getRebateCost() != null ? nf.format(hmt.getRebateCost().doubleValue()) : "0.00");
				packet.put("variance",variance+"%");
				
				WiringLandDetailConEjb wiringLandDetailConEjb = new WiringLandDetailConEjb(sessionKey);
				WiringLandDetailCon schemaDetails = wiringLandDetailConEjb.findByApplicationNo(refe.getId().getStandardEstimateNo());
				if(schemaDetails != null){
					packet.put("schemeDetails", schemaDetails.getSchemeName() == null ? schemaDetails.getSchemeNo() : schemaDetails.getSchemeName());
					//gayani
					//packet.put("divSec", schemaDetails.getDevSec());
					//packet.put("area", schemaDetails.getAreaCode());
					//packet.put("district", schemaDetails.getDistrict());
					//packet.put("eCSC", schemaDetails.getServiceDepoName());
					//packet.put("esname", "");
				}
				/*ApplicationReferenceEjb ejbAppli = new ApplicationReferenceEjb(region);
				ApplicationReference appli = ejbAppli.findByApplicationNo(refe.getId().getStandardEstimateNo());
				if(appli != null){
					
				}*/
			}
			
			//WiringLandDetailConEjb wiringLandDetailConEjb = new WiringLandDetailConEjb(sessionKey);
			//WiringLandDetailCon schemaDetails = wiringLandDetailConEjb.findByApplicationNo(refe.getId().getStandardEstimateNo());
			//if(schemaDetails != null){
				//packet.put("schemeDetails", schemaDetails.getSchemeName() == null ? schemaDetails.getSchemeNo() : schemaDetails.getSchemeName());
				//gayani
				//packet.put("divSec", schemaDetails.getDevSec());
				//packet.put("area", schemaDetails.getAreaCode());
				//packet.put("district", schemaDetails.getDistrict());
				//packet.put("eCSC", schemaDetails.getServiceDepoName());
				//packet.put("esname", "");
			//}
			
		}
		if (pchtt!=null){
			
			//packet.put("totalCostDisplay",nf.format(pchtt.getStdCost().doubleValue()));
			if(pchtt.getStdCost() != null){
				packet.put("totalCost",nf.format(pchtt.getStdCost().doubleValue()));
			}
			/*if(smcType != null && smcType.equalsIgnoreCase(Constants.GI_TCI)){
				BigDecimal cost = ejb.getTotalConsumerPayable(estimateNo, Constants.MAT_COST, costCenterNo);
				if(cost != null ){
					packet.put("totalCostPayable", nf.format(cost.doubleValue()));
				}
			}*/
			if(pchtt.getStdCost() != null ){
				packet.put("isApprovable", EstimateApproval.getApprovalStatus(pchtt.getStdCost().doubleValue(), userRole));
			}
			
			packet.put("rebate", pchtt.getPartialAmt());
			packet.put("estimateDatePicker", pchtt.getEtimateDt());
			if(pchtt.getFundSource() != null){
				packet.put("fundsou", pchtt.getFundSource().trim());
			}
			if(pchtt.getFundId() != null){
				packet.put("fundid", pchtt.getFundId().trim());
			}
			packet.put("description", pchtt.getDescr());
			if(pchtt.getCatCd() != null){
				packet.put("categoryId", pchtt.getCatCd().trim());
			}
			packet.put("costCenter", costCenterNo);
			packet.put("rejectedReason", getRejectedReason(pchtt,userRole,sessionKey));
			
			detail.setHtt(pchtt);
		}
		
		EstimateEjb estimateEjb = new EstimateEjb(sessionKey); 
		if(isExistCommeRef && applicationRefNo != null){
			PivDetailEjb ejbPiv = new PivDetailEjb(sessionKey);
			PivDetail piv = ejbPiv.findByReferenceNo(applicationRefNo, ReferenceType.ESTIMATE);
			if(piv != null){
				packet.put("pivNo", piv.getId().getPivNo());
				packet.put("amount", nf.format(piv.getPivAmount()));
				packet.put("pivDatePicker", piv.getConfirmedDate());
			}
		}else{
			TempTb temptb = estimateEjb.findPIVDetails(estimateNo.trim(), costCenterNo, sessionKey);
			if(temptb != null){
				packet.put("pivNo", temptb.getPivNo());
				if(temptb.getPivAmount() != null){
					packet.put("amount", nf.format(temptb.getPivAmount()));
				}else{
					packet.put("amount","0.00");
				}
				packet.put("pivDatePicker", temptb.getPivDate() != null ? temptb.getPivDate() : "");
				detail.setTb(temptb);
			}
		}
		
		
		PcestdttEjb pd = new PcestdttEjb(sessionKey);
		List<Pcestdtt> pcdtts =  pd.findByEstimationNo(estimateNo, costCenterNo,Constants.REV_NO);
        RebateEjb objRebate = new RebateEjb(sessionKey);
        //List<Sprebate> objReList = objRebate.findByEstimationNo(estimateNo, costCenterNo, sessionKey);
        //System.out.println("hii modified 10 : " + objReList);
		if(pcdtts != null && pcdtts.size() != 0){
			//packet.put("disableSave", true);
				detail.setDtts(pcdtts);
				for(Pcestdtt dtt : pcdtts){
					JSONObject estimateDetailsRow =new JSONObject();
					//estimateDetailsRow.put("selectedPegId", dtt);
					estimateDetailsRow.put("resourceType", dtt.getResType());
					estimateDetailsRow.put("resourceCategory",dtt.getResCat());
					estimateDetailsRow.put("resourceCode",dtt.getId().getResCd().trim());
					
					if(dtt.getId().getResCd().trim().equalsIgnoreCase("MAT")){
						estimateDetailsRow.put("resourceName", Constants.MAT_RESOUR_NAME);
					}else if(matejb.findName(dtt.getId().getResCd().trim()) == null){
						estimateDetailsRow.put("resourceName", dtt.getId().getResCd().trim());
					}else{
						estimateDetailsRow.put("resourceName", matejb.findName(dtt.getId().getResCd().trim()));
					}
					estimateDetailsRow.put("unitPrice", dtt.getUnitPrice());
					estimateDetailsRow.put("uom", dtt.getUom());
					//estimateDetailsRow.put("tolerance", dtt.getTolerance());
					estimateDetailsRow.put("estimateQuantity",dtt.getEstimateQty());
					estimateDetailsRow.put("estimateCost", dtt.getEstimateCost());
					//System.out.println("hii modified" + estimateNo +"  : "+ costCenterNo );
					SprebatePK objPK = new SprebatePK();
					objPK.setEstimateNo(estimateNo);
					objPK.setDeptId(costCenterNo);
					objPK.setResCd(dtt.getId().getResCd().trim());
					Sprebate objReb = objRebate.findBy3PK(estimateNo,costCenterNo,dtt.getId().getResCd().trim(), sessionKey);
					//System.out.println("hii modified"+ objReb);
					//System.out.println("hii modified xxx "+ dtt.getId().getResCd().trim());
					if(objReb!=null){
						
							//System.out.println("hii modified 11" + objReb.getRebateQty() );
							if(objReb.getRebateQty() != null){
								estimateDetailsRow.put("rebateQuantity", objReb.getRebateQty());
								
							}else{
								estimateDetailsRow.put("rebateQuantity", new BigDecimal("0"));
							}
							if(objReb.getRebateCost() != null){
								estimateDetailsRow.put("rebateCost", objReb.getRebateCost());
								
							}else{
								estimateDetailsRow.put("rebateCost", new BigDecimal("0"));
							}
							
							if(objReb.getReusableQty() != null){
								estimateDetailsRow.put("reusableQuantity", objReb.getReusableQty());
								
							}else{
								estimateDetailsRow.put("reusableQuantity", new BigDecimal("0"));
							}
							
							if(objReb.getOffchargeQty() != null){
								estimateDetailsRow.put("offChargeQuantity", objReb.getOffchargeQty());
								
							}else{
								estimateDetailsRow.put("offChargeQuantity", new BigDecimal("0"));
							}
							
							
							
							//estimateDetailsRow.put("rebateQuantity", objReb.getRebateQty());
							//estimateDetailsRow.put("rebateCost", objReb.getRebateCost());
							//estimateDetailsRow.put("reusableQuantity", objReb.getReusableQty());
							//estimateDetailsRow.put("offChargeQuantity", objReb.getOffchargeQty());
							//System.out.println("hii modified 12");
						
						
					}
					
					pegArray.put(estimateDetailsRow);
					
					DetailEstimateDTO detailEstimateDTO = new DetailEstimateDTO();
					detailEstimateDTO.setResourceCode(dtt.getId().getResCd().trim());
					detailEstimateDTO.setUom(dtt.getUom());
					detailEstimateDTO.setEstimateCost(dtt.getEstimateCost());
					detailEstimateDTO.setEstimatedQuantity(dtt.getEstimateQty());
					detailEstimateDTO.setResourceType(dtt.getResType());
					detailEstimateDTO.setResourceName(dtt.getId().getResCd());
					detailEstimateDTO.setUnitPrice(dtt.getUnitPrice());
					if(objReb!=null){
						
							//System.out.println("hii modified 13");
							detailEstimateDTO.setRebateQuantity(objReb.getRebateQty());
							detailEstimateDTO.setRebateCost(objReb.getRebateCost());
							detailEstimateDTO.setReusableQuantity(objReb.getReusableQty());
							detailEstimateDTO.setOffChargeQuantity(objReb.getOffchargeQty());
							//System.out.println("hii modified 42");
						
						
					}
					allSelectedAdditionalResoMap.put(dtt.getId().getResCd().trim(), detailEstimateDTO);
					alreadyAddedDetails.put(dtt.getId().getResCd().trim(), detailEstimateDTO);
					//allTotalCost = dtt.getEstimateQty().floatValue()*dtt.getEstimateCost().floatValue();
				}
				
				//packet.put("totalCost", allTotalCost);
				packet.put("estimateDetails", pegArray);
			
			}
		
		 String estStatus = pchtt.getStatus().toString() ;
         EstimateStatus estSt = new EstimateStatus();
         //estimateStatus =estSt.searchStatus((Integer.parseInt(estStatus)));
         packet.put("status", estSt.searchStatus((Integer.parseInt(estStatus))));
         
         
		PegschdmtEjb pegEjb = new PegschdmtEjb(sessionKey);
		List<Pegschdmt> list = pegEjb.findByEstimationNo(estimateNo.trim(), costCenterNo, sessionKey);
		if(list != null && list.size() > 0){
			for(Pegschdmt pegschdmt : list){
				JSONObject pegDetailsRow =new JSONObject();
			
				//estimateDetailsRow.put("selectedPegId", dtt);
				pegDetailsRow.put("selectedPegId", pegschdmt.getId().getNodeId().trim());
				pegDetailsRow.put("quantity",pegschdmt.getNoOfItem());
				pegDetailsRow.put("description",pegschdmt.getNodeDes());
				
				pegDetailsRowaray.put(pegDetailsRow);
				
				PegItemDTO dto = new PegItemDTO();
				dto.setNodeId(pegschdmt.getId().getNodeId().trim());
				dto.setDeptId(costCenterNo);
				dto.setEstimateNo(estimateNo.trim());
				dto.setNodeDescription(pegschdmt.getNodeDes());
				dto.setNoOfItem(pegschdmt.getNoOfItem());
				dto.setEstimateNo(estimateNo.trim());
				pegItemMap.put(pegschdmt.getId().getNodeId().trim(), dto);
				existingPegItemMap.put(pegschdmt.getId().getNodeId().trim(), dto);
				pegIdList.add(pegschdmt.getId().getNodeId().trim());
				//allSelectedAdditionalResoMap.put(dtt.getId().getResCd(), detailEstimateDTO);
			}
			packet.put("pegDetails", pegDetailsRowaray);
		}
		
		
		request.getSession().setAttribute("pegItemDetails",pegItemMap);
		request.getSession().setAttribute("selectedpegIdList",pegIdList);
		//pegIdList =  (List<String>) request.getSession().getAttribute("selectedpegIdList");
		request.getSession().setAttribute("existingPegItemMap",existingPegItemMap);
		//packet.put("addednorms", normsArray);
		request.getSession().setAttribute("allSelectedAdditionalResources",allSelectedAdditionalResoMap);
		request.getSession().setAttribute("alreadyAddedDetails",alreadyAddedDetails);
		request.getSession().setAttribute("estimateDetailobject",detail);
	}catch(Exception e){
		System.out.println("hii modified 16" + e.getMessage());
	}
	return packet;

}


private  String getRejectedReason(Pcesthtt htt,String userRole,String sessionKey){
	StringBuffer buff = new StringBuffer();
	String rejeReason = null;
	ApprovalEjb eppejb = new ApprovalEjb(sessionKey);
	List<Approval> apprList = eppejb.findByReferenceNo(htt.getId().getEstimateNo());
	String reason = null;
	if(apprList != null ){
		for(Approval app : apprList){
			buff.append(app.getReason() != null ? app.getApprovedLevel()+": " + app.getReason() : " " );
			reason = app.getReason() ;
		
		}
		/*if(htt.getRejectReason() != null && htt.getRejectReason().length() > 0 && htt.getRejectReason().length() != reason.length()){
			buff.append(htt.getRejectReason() != null ? htt.getEntBy()+": " + htt.getRejectReason() : " " +"\n");
		}*/
		//buff.append(htt.getRejectReason() != null ? userRole+": " + htt.getRejectReason() : "");
		
		rejeReason = buff.toString();
	}
	
	
	return rejeReason;
	
}
public JSONObject  loadEstimationDetailsForPrint(HttpServletRequest request) throws JSONException{
	JSONObject packet =new JSONObject();
	NumberFormat nf = NumberFormat.getInstance();
	nf.setGroupingUsed(true);
	nf.setMaximumFractionDigits(2);
	nf.setMinimumFractionDigits(2);
	
	JSONArray pegArray = new JSONArray();
	JSONArray pegDetailsRowaray = new JSONArray();
	EstimateDetails detail = new EstimateDetails();
	try{
		
		String sessionKey= (String) request.getSession().getAttribute("region");
		String applicationNo = request.getParameter("applicationNo");
		String estimateNo = request.getParameter("estimateNo");
		String costCenterNo=(String) request.getSession().getAttribute("costCenterNo");
		String smcType = request.getParameter("smcType");
		PcesthttEjb pc = new PcesthttEjb(sessionKey);
		Pcesthtt pchtt =  pc.findByEstimationNo(estimateNo, costCenterNo,Constants.REV_NO);
		List<String> estimateNos = new ArrayList<String>();
		
		
		
		InmatmEjb matejb = new InmatmEjb(sessionKey);
		EstimateEjb ejb = new EstimateEjb(sessionKey);
		boolean isExistCommeRef = false;
		String applicationRefNo = null;
		EstimateReferenceEjb estejb = new EstimateReferenceEjb();
		EstimateReference refe = estejb.findByWorkEstimateNo(estimateNo,costCenterNo, sessionKey);
		
		if(refe != null){
			packet.put("stdEstimateNo", refe.getId().getStandardEstimateNo());
			packet.put("fileRef", refe.getFileReference());
			applicationRefNo = refe.getId().getStandardEstimateNo();
			estimateNos.add(refe.getId().getStandardEstimateNo());
			request.getSession().setAttribute("estimationRefNos",estimateNos);
			//loadEstmationNumbers(request);
			String commCost = costCenterNo.substring(0, 3);
			SpstdesthmtEjb spejb = new SpstdesthmtEjb(sessionKey);
			SpstdesthmtPK spstdesthmtPK = new SpstdesthmtPK();
			spstdesthmtPK.setApplicationNo(applicationNo);
			spstdesthmtPK.setDeptId(commCost+".00");
			spstdesthmtPK.setStdNo(refe.getId().getStandardEstimateNo());
			spstdesthmtPK.setApplicationNo(refe.getId().getStandardEstimateNo());
			Spstdesthmt hmt = spejb.findById(spstdesthmtPK, sessionKey);
			if(hmt != null){
				isExistCommeRef = true;
				//NumberFormat nf = NumberFormat.getInstance();
				
			}
			
			ApplicationReferenceEjb appEjb = new ApplicationReferenceEjb(sessionKey);
			ApplicationReference appRef = appEjb.findByApplicationNo(refe.getId().getStandardEstimateNo());
			if(appRef != null){
				WiringLandDetailConEjb con = new WiringLandDetailConEjb(sessionKey);
				WiringLandDetailCon conDetail = con.findByApplicationNo(refe.getId().getStandardEstimateNo());
				packet.put("divSec", conDetail.getDevSec());
				packet.put("area", conDetail.getAreaCode());
				packet.put("district", conDetail.getDistrict());
				packet.put("eCSC", conDetail.getServiceDepoName());
				packet.put("esname", "");
			
			}
		}
		
		if (pchtt!=null){
			
			//packet.put("totalCostDisplay",nf.format(pchtt.getStdCost().doubleValue()));
			if(pchtt.getStdCost() != null){
				packet.put("totalCost",nf.format(pchtt.getStdCost().doubleValue()));
			}
			/*if(smcType != null && smcType.equalsIgnoreCase(Constants.GI_TCI)){
				BigDecimal cost = ejb.getTotalConsumerPayable(estimateNo, Constants.MAT_COST, costCenterNo);
				if(cost != null ){
					packet.put("totalCostPayable", nf.format(cost.doubleValue()));
				}
			}*/
			packet.put("rebate", pchtt.getPartialAmt());
			packet.put("estimateDatePicker", pchtt.getEtimateDt());
			if(pchtt.getFundSource() != null){
				packet.put("fundsou", pchtt.getFundSource().trim());
			}
			if(pchtt.getFundId() != null){
				packet.put("fundid", pchtt.getFundId().trim());
			}
			packet.put("description", pchtt.getDescr());
			if(pchtt.getCatCd() != null){
				packet.put("categoryId", pchtt.getCatCd().trim());
			}
			packet.put("costCenter", costCenterNo);
			packet.put("rejectedReason", pchtt.getRejectReason());
			packet.put("aprUid5", pchtt.getAprUid5());
			detail.setHtt(pchtt);
		}
		
	
		EstimateEjb estimateEjb = new EstimateEjb(sessionKey); 
		if(isExistCommeRef && applicationRefNo != null){
			PivDetailEjb ejbPiv = new PivDetailEjb(sessionKey);
			PivDetail piv = ejbPiv.findByReferenceNo(applicationRefNo, ReferenceType.ESTIMATE);
			if(piv != null){
				packet.put("pivNo", piv.getId().getPivNo());
				if(piv.getPivAmount() != null){
					packet.put("amount", nf.format(piv.getPivAmount()));
				}
				packet.put("pivDatePicker", piv.getConfirmedDate());
			}
		}else{
			TempTb temptb = estimateEjb.findPIVDetails(estimateNo, costCenterNo, sessionKey);
			if(temptb != null){
				packet.put("pivNo", temptb.getPivNo());
				//packet.put("amount", temptb.getPivAmount());
				if(temptb.getPivAmount() != null){
					packet.put("amount", nf.format(temptb.getPivAmount()));
				}
				packet.put("pivDatePicker", temptb.getPivDate() != null ? temptb.getPivDate() : "");
				detail.setTb(temptb);
			}
		}
		
		PcestdttEjb pd = new PcestdttEjb(sessionKey);
		List<Pcestdtt> pcdtts =  pd.findByEstimationNo(estimateNo, costCenterNo,Constants.REV_NO);
	
		
		if(pcdtts != null && pcdtts.size() != 0){
			//packet.put("disableSave", true);
				detail.setDtts(pcdtts);
				//DetailEstimatePrintDetails gPS= new DetailEstimatePrintDetails();
		       	StringBuffer listRescd = new StringBuffer();
		       	StringBuffer listResName = new StringBuffer();
		       	StringBuffer listQuantity = new StringBuffer();
		       	StringBuffer listUom = new StringBuffer();
		       	StringBuffer listUnitPrice = new StringBuffer();
		       	StringBuffer listEstimateCost = new StringBuffer();
				String resourcename="";
				int noofMaterials =0;
				for(Pcestdtt dtt : pcdtts){
					JSONObject estimateDetailsRow =new JSONObject();
					
					estimateDetailsRow.put("resourceType", dtt.getResType());
					estimateDetailsRow.put("resourceCategory",dtt.getResCat());
					estimateDetailsRow.put("resourceCode",dtt.getId().getResCd().trim());
					if(dtt.getId().getResCd().trim().equalsIgnoreCase("MAT")){
						estimateDetailsRow.put("resourceName", Constants.MAT_RESOUR_NAME);
					}else if(matejb.findName(dtt.getId().getResCd().trim()) == null){
						estimateDetailsRow.put("resourceName", dtt.getId().getResCd().trim());
						resourcename = dtt.getId().getResCd().trim();
						
					}else{
						resourcename =matejb.findName(dtt.getId().getResCd().trim());
						estimateDetailsRow.put("resourceName",resourcename );
						
					}
					if(dtt.getResType() != null && (dtt.getResType().trim().equalsIgnoreCase("MAT-COST") || dtt.getResType().trim().equalsIgnoreCase("MAT-COST-OTHER"))){
						listRescd.append(dtt.getId().getResCd().trim()).append("###");
						//listResName.append(dtt.getId().getResCd().trim()).append("###");
						listResName.append(resourcename).append("###");
						listUnitPrice.append(nf.format(dtt.getUnitPrice().doubleValue())).append("###");
						if (dtt.getUom() != null) {
							listUom.append(String.valueOf(dtt.getUom())).append("###"); 
						}else {
							listUom.append(String.valueOf("NO")).append("###");
						}
						if(dtt.getEstimateQty() != null){
							listQuantity.append(nf.format(dtt.getEstimateQty().doubleValue())).append("###");
						}else{
							listQuantity.append(nf.format(new Double("0.0"))).append("###");
						}
						listEstimateCost.append(nf.format(dtt.getEstimateCost().doubleValue())).append("###");
						noofMaterials =noofMaterials +1;
						
					}
					
					estimateDetailsRow.put("unitPrice", dtt.getUnitPrice());
					
					 
					
					estimateDetailsRow.put("uom", dtt.getUom());
					
					
					
					//estimateDetailsRow.put("tolerance", dtt.getTolerance());
					estimateDetailsRow.put("estimateQuantity",dtt.getEstimateQty());
					
					
					
					estimateDetailsRow.put("estimateCost", nf.format(dtt.getEstimateCost().doubleValue()));
					
					
					
					RebateEjb objRebate = new RebateEjb(sessionKey);
					SprebatePK objPK = new SprebatePK();
					objPK.setEstimateNo(estimateNo);
					objPK.setDeptId(costCenterNo);
					objPK.setResCd(dtt.getId().getResCd().trim());
					Sprebate objReb = objRebate.findBy3PK(estimateNo,costCenterNo,dtt.getId().getResCd().trim(), sessionKey);
					System.out.println("hii modified"+ objReb);
					System.out.println("hii modified xxx "+ dtt.getId().getResCd().trim());
					if(objReb!=null){
						
							System.out.println("hii modified 11" + objReb.getRebateQty() );
							if(objReb.getRebateQty() != null){
								estimateDetailsRow.put("rebateQuantity", objReb.getRebateQty());
								
							}else{
								estimateDetailsRow.put("rebateQuantity", new BigDecimal("0"));
							}
							if(objReb.getRebateCost() != null){
								estimateDetailsRow.put("rebateCost", objReb.getRebateCost());
								
							}else{
								estimateDetailsRow.put("rebateCost", new BigDecimal("0"));
							}
							
							if(objReb.getReusableQty() != null){
								estimateDetailsRow.put("reusableQuantity", objReb.getReusableQty());
								
							}else{
								estimateDetailsRow.put("reusableQuantity", new BigDecimal("0"));
							}
							
							if(objReb.getOffchargeQty() != null){
								estimateDetailsRow.put("offChargeQuantity", objReb.getOffchargeQty());
								
							}else{
								estimateDetailsRow.put("offChargeQuantity", new BigDecimal("0"));
							}
					}
					
					//for print details 
					
					pegArray.put(estimateDetailsRow);
				
					//allTotalCost = dtt.getEstimateQty().floatValue()*dtt.getEstimateCost().floatValue();
				}
				String resType="";
				double materialCostEntry=0.0;
				double realMaterialCostEntry=0.0;
				double labourCostEntry=0.0;
				double transportCostEntry=0.0;
				double subsistenceCostEntry=0.0;
				double overheadCostEntry=0.0;
				double contingenciesCostEntry=0.0;
				double wayleavesCostEntry=0.0;
				double materialcostEntry=0.0;
				double machineryCostEntry=0.0;
				double otherCostEntry=0.0;
				String otherCostEntryType="";
				double otherCostEntryVAT=0.0;
				
				List<Map>  mapCostList = pd.getSUMByResType(estimateNo, costCenterNo);
				for(int i=0 ; i<mapCostList.size() ; i++){
					resType = mapCostList.get(i).values().toArray()[0].toString();
					//new BigDecimal(mapCostList.get(0).values().toArray()[0].toString());
					
					if(resType != null && resType.trim().equalsIgnoreCase("MAT-COST")){
						materialcostEntry = new BigDecimal(mapCostList.get(i).values().toArray()[1].toString()).doubleValue();
						//packet.put("materialCost",new BigDecimal(mapCostList.get(0).values().toArray()[0].toString()));
					}
					if(resType != null && resType.trim().equalsIgnoreCase("MAT-COST-OTHER")){
						realMaterialCostEntry = new BigDecimal(mapCostList.get(i).values().toArray()[1].toString()).doubleValue();
						//packet.put("materialCost",new BigDecimal(mapCostList.get(0).values().toArray()[0].toString()));
					}
					
					if(resType != null && resType.trim().equalsIgnoreCase("LABOUR-COST")){
						labourCostEntry = new BigDecimal(mapCostList.get(i).values().toArray()[1].toString()).doubleValue();
					}
					
					if(resType != null && resType.trim().equalsIgnoreCase("TRANSPORT-COST")){
						transportCostEntry = new BigDecimal(mapCostList.get(i).values().toArray()[1].toString()).doubleValue();
					}
					
					if(resType != null && resType.trim().equalsIgnoreCase("SUBSISTANCE-COST")){
						subsistenceCostEntry = new BigDecimal(mapCostList.get(i).values().toArray()[1].toString()).doubleValue();
					}
					
					if(resType != null && resType.trim().equalsIgnoreCase("WAYLEAVES-COST")){
						wayleavesCostEntry = new BigDecimal(mapCostList.get(i).values().toArray()[1].toString()).doubleValue();
					}
					if(resType != null && resType.trim().equalsIgnoreCase("OVERHEAD-COST")){
						overheadCostEntry = new BigDecimal(mapCostList.get(i).values().toArray()[1].toString()).doubleValue();
					}
					
					if(resType != null && resType.trim().equalsIgnoreCase("CONTINGENCIES-COST")){
						contingenciesCostEntry = new BigDecimal(mapCostList.get(i).values().toArray()[1].toString()).doubleValue();
					}
					//if(costCenterNo != null && (costCenterNo.equalsIgnoreCase("520.20") || costCenterNo.equalsIgnoreCase("520.30"))){
						
						if(resType != null && resType.trim().equalsIgnoreCase("MACHINERY COST")){
							otherCostEntry = new BigDecimal(mapCostList.get(i).values().toArray()[1].toString()).doubleValue();
							otherCostEntryType = Constants.MACHINERY_COST;
						}
					//}else if(costCenterNo != null && (costCenterNo.equalsIgnoreCase("550.30"))){
						/*if(resType != null && resType.trim().equalsIgnoreCase("12% VAT")){
							otherCostEntryVAT = new BigDecimal(mapCostList.get(i).values().toArray()[1].toString()).doubleValue();
						}*/
					//}else{
						if(resType != null && resType.trim().equalsIgnoreCase("OTHER-COST")){
							otherCostEntry = new BigDecimal(mapCostList.get(i).values().toArray()[1].toString()).doubleValue();
							otherCostEntryType =Constants.OTHER_COST;
						}
					//}
					
					
					
	                
				}
					packet.put("materialCost",nf.format(materialcostEntry+realMaterialCostEntry));
			
					packet.put("wayleaves", nf.format(wayleavesCostEntry));
	                packet.put("transport", nf.format(transportCostEntry));
	                packet.put("labour", nf.format(labourCostEntry));
	                packet.put("subsistance",nf.format(subsistenceCostEntry));
	                packet.put("contingencies",nf.format(contingenciesCostEntry));
	                packet.put("overhead", nf.format(overheadCostEntry));
	                packet.put("otherCost", nf.format(otherCostEntry));
	                packet.put("otherCostType", otherCostEntryType);
	                String estStatus = pchtt.getStatus().toString() ;
	                EstimateStatus estSt = new EstimateStatus();
	                //estimateStatus =estSt.searchStatus((Integer.parseInt(estStatus)));
	                packet.put("status", estSt.searchStatus((Integer.parseInt(estStatus))));
	        	
				//gPS.setTotResCount(listRescd.size());
                //int totPages = (pcdtts.size() / 22);
                packet.put("listRescd", listRescd.toString());
                packet.put("listResName", listResName.toString());
                packet.put("listUnitPrice", listUnitPrice.toString());
                packet.put("listUom", listUom.toString());
                packet.put("listQuantity", listQuantity.toString());
                packet.put("listEstimateCost", listEstimateCost.toString());
                
             
                packet.put("totalConstructionCost",pchtt.getStdCost() != null ?  nf.format(pchtt.getStdCost()) : "0.00" );
                packet.put("rebateCost",  pchtt.getPartialAmt() != null ?  nf.format(pchtt.getPartialAmt()) : "0.00");
                packet.put("totalcapitalCost", nf.format(pchtt.getStdCost().doubleValue()- pchtt.getPartialAmt().doubleValue()));
                
                int totPages =0;
                if(noofMaterials != 0){
	                totPages = noofMaterials/22;
	                
	                if(noofMaterials % 22 != 0){
	                	totPages = totPages+1;
	                }
                }
                packet.put("totPages", totPages);
                
				packet.put("estimateDetails", pegArray);
			
			}
		
		
		
	}catch(Exception e){
		
	}
	return packet;

}
public JSONObject  loadReviseDetails(HttpServletRequest request) throws JSONException{
	
	
	JSONObject packet =new JSONObject();

	allSelectedAdditionalResoMap = (Map<String,DetailEstimateDTO>) request.getSession().getAttribute("allSelectedAdditionalResources");
	pegItemMap =(Map<String,PegItemDTO>) request.getSession().getAttribute("pegItemDetails");
	EstimateDetails detail = new EstimateDetails();
	if(allSelectedAdditionalResoMap == null){
		allSelectedAdditionalResoMap = new HashMap<String, DetailEstimateDTO>();
	}

	alreadyAddedDetails = (Map<String,DetailEstimateDTO>) request.getSession().getAttribute("alreadyAddedDetails");
	alreadyAddedDmtDetails = (Map<String,Pcestdmt>) request.getSession().getAttribute("alreadyAddedDmtDetails");
	
	if(alreadyAddedDmtDetails == null){
		alreadyAddedDmtDetails = new HashMap<String, Pcestdmt>();
	}
	if(alreadyAddedDetails == null){
		alreadyAddedDetails = new HashMap<String, DetailEstimateDTO>();
	}
	JSONArray pegArray = new JSONArray();
	JSONArray pegDetailsRowaray = new JSONArray();
	try{
		
		String sessionKey= (String) request.getSession().getAttribute("region");
		String applicationNo = request.getParameter("applicationNo");
		String projectNo = request.getParameter("projectNo");
		String costCenterNo=(String) request.getSession().getAttribute("costCenterNo");
		String smcType=(String) request.getSession().getAttribute("smcType");
		InmatmEjb matejb = new InmatmEjb(sessionKey);
		PcesthmtEjb pc = new PcesthmtEjb(sessionKey);
		JobEjb ejb = new JobEjb(sessionKey);
		Pcesthmt pchmt =  pc.findByJobNo(projectNo, costCenterNo);
		List<String> estimateNos = new ArrayList<String>();
		
		
		
		
		if (pchmt!=null){
			
			if(pchmt.getStdCost() != null){
				packet.put("totalCost",pchmt.getStdCost());
			}
			
			
			packet.put("rebate", pchmt.getPartialAmt());
			packet.put("estimateDatePicker", pchmt.getEtimateDt());
			if(pchmt.getFundSource() != null){
				packet.put("fundsou", pchmt.getFundSource().trim());
			}
			
			packet.put("fundid", pchmt.getFundId().trim());
			
			packet.put("description", pchmt.getDescr());
			
			
			packet.put("reviseReason", pchmt.getRevReason());
			if(pchmt.getCatCd() != null){
				packet.put("categoryId", pchmt.getCatCd().trim());
			}
			packet.put("costCenter", costCenterNo);
			packet.put("rejectedReason", pchmt.getRejectReason());
			String estStatus = pchmt.getStatus().toString() ;
			EstimateStatus estSt = new EstimateStatus();
			packet.put("status", estSt.searchStatus((Integer.parseInt(estStatus))));
			detail.setHmt(pchmt);
			
			
			
			try { //
				PcesthttEjb httEjb = new PcesthttEjb(sessionKey);
				long initRevNo = httEjb.getMinHttRevNo(pchmt.getId().getEstimateNo().trim(), costCenterNo);
				if(initRevNo != -1){
					Pcesthtt pcesthtt = httEjb.findByEstimationNo(pchmt.getId().getEstimateNo().trim(), costCenterNo, initRevNo);
					if(pcesthtt != null ){
						detail.setHtt(pcesthtt);
					
						if(pcesthtt.getStdCost() != null){
							packet.put("previousTotalCost", nf.format(pcesthtt.getStdCost()));
						}
					
						if(pchmt.getStdCost() != null){
							packet.put("currentTotalCost", nf.format(pchmt.getStdCost()));
						}
						if(pchmt.getStdCost() != null){
							double deduction = pchmt.getStdCost().doubleValue()-pcesthtt.getStdCost().doubleValue();
							if(deduction != 0.0){
								packet.put("variance",(deduction/pcesthtt.getStdCost().doubleValue())*100 +" %");
							}
						}
					}
				}
				
				
			} catch (JSONException e) {
				
				e.printStackTrace();
			}
			
		}
		
		
		EstimateEjb estimateEjb = new EstimateEjb(sessionKey); 
		
		TempTb temptb = estimateEjb.findPIVDetails(projectNo, costCenterNo, sessionKey);
		if(temptb != null){
			packet.put("pivNo", temptb.getPivNo());
			packet.put("amount", temptb.getPivAmount());
			packet.put("pivDatePicker", temptb.getPivDate());
			detail.setTb(temptb);
		}
		
		PcestdmtEjb pd = new PcestdmtEjb(sessionKey);
		PcesthttEjb httEjb = new PcesthttEjb(sessionKey);
		PcestdttEjb pdtt = new PcestdttEjb(sessionKey);
		List<Pcestdmt> pcdmts =  pd.findByEstimationNo(pchmt.getId().getEstimateNo(), costCenterNo);
        long maxRevNo = httEjb.getMaxHttRevNo(pchmt.getId().getEstimateNo().trim(), costCenterNo,sessionKey);
		
		List<Pcestdtt> pcdtts =  pdtt.findByEstimationNo(pchmt.getId().getEstimateNo(), costCenterNo,maxRevNo);
		System.out.println("Hiiiiii : " + pchmt.getId().getEstimateNo());
		if(pcdmts != null && pcdmts.size() != 0){
			
				detail.setDmts(pcdmts);
				for(Pcestdmt dmt : pcdmts){
					JSONObject estimateDetailsRow =new JSONObject();
					//estimateDetailsRow.put("selectedPegId", dtt);
					estimateDetailsRow.put("resourceType", dmt.getResType());
					estimateDetailsRow.put("resourceCategory",dmt.getResCat());
					estimateDetailsRow.put("resourceCode",dmt.getId().getResCd().trim());
					//estimateDetailsRow.put("resourceName", dmt.get);
					if(dmt.getId().getResCd().trim().equalsIgnoreCase("MAT")){
						estimateDetailsRow.put("resourceName", Constants.MAT_RESOUR_NAME);
					}else if(matejb.findName(dmt.getId().getResCd().trim()) == null){
						estimateDetailsRow.put("resourceName", dmt.getId().getResCd().trim());
					}else{
						estimateDetailsRow.put("resourceName", matejb.findName(dmt.getId().getResCd().trim()));
					}
					estimateDetailsRow.put("unitPrice", dmt.getUnitPrice());
					estimateDetailsRow.put("uom", dmt.getUom());
					//estimateDetailsRow.put("tolerance", dtt.getTolerance());
					estimateDetailsRow.put("estimateQuantity",dmt.getEstimateQty());
					BigDecimal diff = getEstQtyBeforeRevise(pcdtts,dmt.getId().getResCd().trim());
					System.out.println("Hiiiiii ffff: " + diff);
					if(diff == null){
						diff = new BigDecimal(0);
					}
					if(dmt.getEstimateQty() == null){
						dmt.setEstimateQty(new BigDecimal(0));
					}
					System.out.println("Hiiiiii ffff: " + dmt.getEstimateQty() +"dfdfdf : " + diff);
					BigDecimal diffRev = new BigDecimal(dmt.getEstimateQty().doubleValue() - diff.doubleValue());
					
					
					
					estimateDetailsRow.put("estimatedQuantityBefore",diff);
					estimateDetailsRow.put("estQuanDif",diffRev);

					
					estimateDetailsRow.put("estimateCost", dmt.getEstimateCost());
					pegArray.put(estimateDetailsRow);
					System.out.println("Hiiiiii : " + dmt.getEstimateCost());
					DetailEstimateDTO detailEstimateDTO = new DetailEstimateDTO();
					detailEstimateDTO.setResourceCode(dmt.getId().getResCd().trim());
					detailEstimateDTO.setUom(dmt.getUom());
					detailEstimateDTO.setEstimateCost(dmt.getEstimateCost());
					detailEstimateDTO.setEstimatedQuantity(dmt.getEstimateQty());
					detailEstimateDTO.setResourceType(dmt.getResType());
					detailEstimateDTO.setResourceName(dmt.getId().getResCd());
					detailEstimateDTO.setUnitPrice(dmt.getUnitPrice());
					detailEstimateDTO.setEstimatedQuantityBefore(diff);
					detailEstimateDTO.setEstQuanDif(diffRev);
					
					allSelectedAdditionalResoMap.put(dmt.getId().getResCd().trim(), detailEstimateDTO);
					alreadyAddedDetails.put(dmt.getId().getResCd().trim(), detailEstimateDTO);
					alreadyAddedDmtDetails.put(dmt.getId().getResCd().trim(), dmt);
					
					//allTotalCost = dtt.getEstimateQty().floatValue()*dtt.getEstimateCost().floatValue();
				}
				
				//packet.put("totalCost", allTotalCost);
				packet.put("estimateDetails", pegArray);
			
			}
		PegschdmtEjb pegEjb = new PegschdmtEjb(sessionKey);
		List<Pegschdmt> list = pegEjb.findByEstimationNo(projectNo.trim(), costCenterNo, sessionKey);
		/*if(list != null && list.size() > 0){
			for(Pegschdmt pegschdmt : list){
				JSONObject pegDetailsRow =new JSONObject();
				//estimateDetailsRow.put("selectedPegId", dtt);
				pegDetailsRow.put("selectedPegId", pegschdmt.getNodeId().trim());
				pegDetailsRow.put("quantity",pegschdmt.getNoOfItem());
				pegDetailsRow.put("description",pegschdmt.getNodeDes());
				
				pegDetailsRowaray.put(pegDetailsRow);
				pegItemMap.put(pegschdmt.getNodeId().trim(), pegschdmt);
				//allSelectedAdditionalResoMap.put(dtt.getId().getResCd(), detailEstimateDTO);
			}
			packet.put("pegDetails", pegDetailsRowaray);
		}*/
		

		request.getSession().setAttribute("pegItemDetails",pegItemMap);
		//packet.put("addednorms", normsArray);
		request.getSession().setAttribute("allSelectedAdditionalResources",allSelectedAdditionalResoMap);
		request.getSession().setAttribute("alreadyAddedDetails",alreadyAddedDetails);
		request.getSession().setAttribute("estimateDetailobject",detail);
		request.getSession().setAttribute("alreadyAddedDmtDetails",alreadyAddedDmtDetails);
		System.out.println("Hiiiiiigggg :");
	}catch(Exception e){
		
	}
	return packet;

}
public JSONObject  loadReviseDetailsForPrint(HttpServletRequest request) throws JSONException{
	JSONObject packet =new JSONObject();
	NumberFormat nf = NumberFormat.getInstance();
	nf.setGroupingUsed(true);
	nf.setMaximumFractionDigits(2);
	nf.setMinimumFractionDigits(2);
	
	JSONArray pegArray = new JSONArray();
	JSONArray pegDetailsRowaray = new JSONArray();
	EstimateDetails detail = new EstimateDetails();
	try{
		
		String sessionKey= (String) request.getSession().getAttribute("region");
		String applicationNo = request.getParameter("applicationNo");
		String projectNo = request.getParameter("projectNo");
		String costCenterNo=(String) request.getSession().getAttribute("costCenterNo");
		String smcType = request.getParameter("smcType");
		//PcesthttEjb pc = new PcesthttEjb(sessionKey);
		PcesthmtEjb pc = new PcesthmtEjb(sessionKey);
		Pcesthmt pchmt =  pc.findByJobNo(projectNo, costCenterNo);
		List<String> estimateNos = new ArrayList<String>();
		InmatmEjb matejb = new InmatmEjb(sessionKey);
		if(pchmt != null){
		
			
			EstimateEjb ejb = new EstimateEjb(sessionKey);
			boolean isExistCommeRef = false;
			String applicationRefNo = null;
			EstimateReferenceEjb estejb = new EstimateReferenceEjb();
			EstimateReference refe = estejb.findByWorkEstimateNo(pchmt.getId().getEstimateNo(),costCenterNo, sessionKey);
			
			if(refe != null){
				packet.put("stdEstimateNo", refe.getId().getStandardEstimateNo());
				applicationRefNo = refe.getId().getStandardEstimateNo();
				estimateNos.add(refe.getId().getStandardEstimateNo());
				request.getSession().setAttribute("estimationRefNos",estimateNos);
				//loadEstmationNumbers(request);
				String commCost = costCenterNo.substring(0, 3);
				SpstdesthmtEjb spejb = new SpstdesthmtEjb(sessionKey);
				SpstdesthmtPK spstdesthmtPK = new SpstdesthmtPK();
				spstdesthmtPK.setApplicationNo(applicationNo);
				spstdesthmtPK.setDeptId(commCost+".00");
				spstdesthmtPK.setStdNo(refe.getId().getStandardEstimateNo());
				spstdesthmtPK.setApplicationNo(refe.getId().getStandardEstimateNo());
				Spstdesthmt hmt = spejb.findById(spstdesthmtPK, sessionKey);
				if(hmt != null){
					isExistCommeRef = true;
					//NumberFormat nf = NumberFormat.getInstance();
					
				}
				
				ApplicationReferenceEjb appEjb = new ApplicationReferenceEjb(sessionKey);
				ApplicationReference appRef = appEjb.findByApplicationNo(refe.getId().getStandardEstimateNo());
				if(appRef != null){
					WiringLandDetailConEjb con = new WiringLandDetailConEjb(sessionKey);
					WiringLandDetailCon conDetail = con.findByApplicationNo(refe.getId().getStandardEstimateNo());
					packet.put("divSec", conDetail.getDevSec());
					packet.put("area", conDetail.getAreaCode());
					packet.put("district", conDetail.getDistrict());
					packet.put("eCSC", conDetail.getServiceDepoName());
					packet.put("esname", "");
				
				}
			}
		}
		if (pchmt!=null){
			
			//packet.put("totalCostDisplay",nf.format(pchtt.getStdCost().doubleValue()));
			if(pchmt.getStdCost() != null){
				packet.put("totalCost",nf.format(pchmt.getStdCost().doubleValue()));
			}
			/*if(smcType != null && smcType.equalsIgnoreCase(Constants.GI_TCI)){
				BigDecimal cost = ejb.getTotalConsumerPayable(estimateNo, Constants.MAT_COST, costCenterNo);
				if(cost != null ){
					packet.put("totalCostPayable", nf.format(cost.doubleValue()));
				}
			}*/
			
			packet.put("estimateNo", pchmt.getId().getEstimateNo());
			packet.put("rebate", pchmt.getPartialAmt());
			packet.put("estimateDatePicker", pchmt.getEtimateDt());
			packet.put("fundsou", pchmt.getFundSource().trim());
			packet.put("fundid", pchmt.getFundId().trim());
			packet.put("description", pchmt.getDescr());
			if(pchmt.getCatCd() != null){
				packet.put("categoryId", pchmt.getCatCd().trim());
			}
			packet.put("costCenter", costCenterNo);
			packet.put("rejectedReason", pchmt.getRejectReason());
			
			detail.setHmt(pchmt);
		}
		
	
		EstimateEjb estimateEjb = new EstimateEjb(sessionKey); 
	
		TempTb temptb = estimateEjb.findPIVDetails(pchmt.getId().getEstimateNo(), costCenterNo, sessionKey);
			if(temptb != null){
				packet.put("pivNo", temptb.getPivNo());
				//packet.put("amount", temptb.getPivAmount());
				if(temptb.getPivAmount() != null){
					packet.put("amount", nf.format(temptb.getPivAmount()));
				}
				packet.put("pivDatePicker", temptb.getPivDate() != null ? temptb.getPivDate() : "");
				detail.setTb(temptb);
			}
		
		
			PcestdmtEjb pd = new PcestdmtEjb(sessionKey);
			List<Pcestdmt> pcdmts =  pd.findByEstimationNo(pchmt.getId().getEstimateNo(), costCenterNo);

		
		if(pcdmts != null && pcdmts.size() != 0){
			//packet.put("disableSave", true);
				detail.setDmts(pcdmts);
				//DetailEstimatePrintDetails gPS= new DetailEstimatePrintDetails();
		       	StringBuffer listRescd = new StringBuffer();
		       	StringBuffer listResName = new StringBuffer();
		       	StringBuffer listQuantity = new StringBuffer();
		       	StringBuffer listUom = new StringBuffer();
		       	StringBuffer listUnitPrice = new StringBuffer();
		       	StringBuffer listEstimateCost = new StringBuffer();
				String resourcename="";
				int noofMaterials =0;
				for(Pcestdmt dtt : pcdmts){
					JSONObject estimateDetailsRow =new JSONObject();
					
					estimateDetailsRow.put("resourceType", dtt.getResType());
					estimateDetailsRow.put("resourceCategory",dtt.getResCat());
					estimateDetailsRow.put("resourceCode",dtt.getId().getResCd().trim());
					if(dtt.getId().getResCd().trim().equalsIgnoreCase("MAT")){
						estimateDetailsRow.put("resourceName", Constants.MAT_RESOUR_NAME);
					}else if(matejb.findName(dtt.getId().getResCd().trim()) == null){
						estimateDetailsRow.put("resourceName", dtt.getId().getResCd().trim());
						resourcename = dtt.getId().getResCd().trim();
						
					}else{
						resourcename =matejb.findName(dtt.getId().getResCd().trim());
						estimateDetailsRow.put("resourceName",resourcename );
						
					}
					if(dtt.getResType() != null && (dtt.getResType().trim().equalsIgnoreCase("MAT-COST") || dtt.getResType().trim().equalsIgnoreCase("MAT-COST-OTHER"))){
						listRescd.append(dtt.getId().getResCd().trim()).append("###");
						//listResName.append(dtt.getId().getResCd().trim()).append("###");
						listResName.append(resourcename).append("###");
						listUnitPrice.append(nf.format(dtt.getUnitPrice().doubleValue())).append("###");
						if (dtt.getUom() != null) {
							listUom.append(String.valueOf(dtt.getUom())).append("###"); 
						}else {
							listUom.append(String.valueOf("NO")).append("###");
						}
						if(dtt.getEstimateQty() != null){
							listQuantity.append(nf.format(dtt.getEstimateQty().doubleValue())).append("###");
						}else{
							listQuantity.append(nf.format(new Double("0.0"))).append("###");
						}
						listEstimateCost.append(nf.format(dtt.getEstimateCost().doubleValue())).append("###");
						noofMaterials =noofMaterials +1;
						
					}
					
					estimateDetailsRow.put("unitPrice", dtt.getUnitPrice());
					
					 
					
					estimateDetailsRow.put("uom", dtt.getUom());
					
					
					
					//estimateDetailsRow.put("tolerance", dtt.getTolerance());
					estimateDetailsRow.put("estimateQuantity",dtt.getEstimateQty());
					
					
					
					estimateDetailsRow.put("estimateCost", nf.format(dtt.getEstimateCost().doubleValue()));
					
					
					//for print details 
					
					pegArray.put(estimateDetailsRow);
				
					//allTotalCost = dtt.getEstimateQty().floatValue()*dtt.getEstimateCost().floatValue();
				}
				String resType="";
				double materialCostEntry=0.0;
				double realMaterialCostEntry=0.0;
				double labourCostEntry=0.0;
				double transportCostEntry=0.0;
				double subsistenceCostEntry=0.0;
				double overheadCostEntry=0.0;
				double contingenciesCostEntry=0.0;
				double wayleavesCostEntry=0.0;
				double materialcostEntry=0.0;
				double machineryCostEntry=0.0;
				double otherCostEntry=0.0;
				String otherCostEntryType="";
				double otherCostEntryVAT=0.0;
				
				List<Map>  mapCostList = pd.getSUMByResType(pchmt.getId().getEstimateNo(), costCenterNo);
				for(int i=0 ; i<mapCostList.size() ; i++){
					resType = mapCostList.get(i).values().toArray()[0].toString();
					//new BigDecimal(mapCostList.get(0).values().toArray()[0].toString());
					
					if(resType != null && resType.trim().equalsIgnoreCase("MAT-COST")){
						materialcostEntry = new BigDecimal(mapCostList.get(i).values().toArray()[1].toString()).doubleValue();
						//packet.put("materialCost",new BigDecimal(mapCostList.get(0).values().toArray()[0].toString()));
					}
					if(resType != null && resType.trim().equalsIgnoreCase("MAT-COST-OTHER")){
						realMaterialCostEntry = new BigDecimal(mapCostList.get(i).values().toArray()[1].toString()).doubleValue();
						//packet.put("materialCost",new BigDecimal(mapCostList.get(0).values().toArray()[0].toString()));
					}
					
					if(resType != null && resType.trim().equalsIgnoreCase("LABOUR-COST")){
						labourCostEntry = new BigDecimal(mapCostList.get(i).values().toArray()[1].toString()).doubleValue();
					}
					
					if(resType != null && resType.trim().equalsIgnoreCase("TRANSPORT-COST")){
						transportCostEntry = new BigDecimal(mapCostList.get(i).values().toArray()[1].toString()).doubleValue();
					}
					
					if(resType != null && resType.trim().equalsIgnoreCase("SUBSISTANCE-COST")){
						subsistenceCostEntry = new BigDecimal(mapCostList.get(i).values().toArray()[1].toString()).doubleValue();
					}
					
					if(resType != null && resType.trim().equalsIgnoreCase("WAYLEAVES-COST")){
						wayleavesCostEntry = new BigDecimal(mapCostList.get(i).values().toArray()[1].toString()).doubleValue();
					}
					if(resType != null && resType.trim().equalsIgnoreCase("OVERHEAD-COST")){
						overheadCostEntry = new BigDecimal(mapCostList.get(i).values().toArray()[1].toString()).doubleValue();
					}
					
					if(resType != null && resType.trim().equalsIgnoreCase("CONTINGENCIES-COST")){
						contingenciesCostEntry = new BigDecimal(mapCostList.get(i).values().toArray()[1].toString()).doubleValue();
					}
					//if(costCenterNo != null && (costCenterNo.equalsIgnoreCase("520.20") || costCenterNo.equalsIgnoreCase("520.30"))){
						
						if(resType != null && resType.trim().equalsIgnoreCase("MACHINERY COST")){
							otherCostEntry = new BigDecimal(mapCostList.get(i).values().toArray()[1].toString()).doubleValue();
							otherCostEntryType = Constants.MACHINERY_COST;
						}
					//}else if(costCenterNo != null && (costCenterNo.equalsIgnoreCase("550.30"))){
						/*if(resType != null && resType.trim().equalsIgnoreCase("12% VAT")){
							otherCostEntryVAT = new BigDecimal(mapCostList.get(i).values().toArray()[1].toString()).doubleValue();
						}*/
					//}else{
						if(resType != null && resType.trim().equalsIgnoreCase("OTHER-COST")){
							otherCostEntry = new BigDecimal(mapCostList.get(i).values().toArray()[1].toString()).doubleValue();
							otherCostEntryType =Constants.OTHER_COST;
						}
					//}
					
					
					
	                
				}
					packet.put("materialCost",nf.format(materialcostEntry+realMaterialCostEntry));
			
					packet.put("wayleaves", nf.format(wayleavesCostEntry));
	                packet.put("transport", nf.format(transportCostEntry));
	                packet.put("labour", nf.format(labourCostEntry));
	                packet.put("subsistance",nf.format(subsistenceCostEntry));
	                packet.put("contingencies",nf.format(contingenciesCostEntry));
	                packet.put("overhead", nf.format(overheadCostEntry));
	                packet.put("otherCost", nf.format(otherCostEntry));
	                packet.put("otherCostType", otherCostEntryType);
	                String estStatus = pchmt.getStatus().toString() ;
	                EstimateStatus estSt = new EstimateStatus();
	                //estimateStatus =estSt.searchStatus((Integer.parseInt(estStatus)));
	                packet.put("status", estSt.searchStatus((Integer.parseInt(estStatus))));
	        	
				//gPS.setTotResCount(listRescd.size());
                //int totPages = (pcdtts.size() / 22);
                packet.put("listRescd", listRescd.toString());
                packet.put("listResName", listResName.toString());
                packet.put("listUnitPrice", listUnitPrice.toString());
                packet.put("listUom", listUom.toString());
                packet.put("listQuantity", listQuantity.toString());
                packet.put("listEstimateCost", listEstimateCost.toString());
                
              /*  packet.put("materialCost","2424234.60");
                packet.put("wayleaves", "2424234.60");
                packet.put("transport", "2424234.60");
                packet.put("labour", "2424234.60");
                packet.put("subsistance", "2424234.60");
                packet.put("contingencies","2424234.60");
                packet.put("overhead", "2424234.60");*/
                packet.put("totalConstructionCost",pchmt.getStdCost() != null ?  nf.format(pchmt.getStdCost()) : "0.00" );
                packet.put("rebateCost",  pchmt.getPartialAmt() != null ?  nf.format(pchmt.getPartialAmt()) : "0.00");
                packet.put("totalcapitalCost", nf.format(pchmt.getStdCost().doubleValue()- pchmt.getPartialAmt().doubleValue()));
                
                int totPages =0;
                if(noofMaterials != 0){
	                totPages = noofMaterials/22;
	                
	                if(noofMaterials % 22 != 0){
	                	totPages = totPages+1;
	                }
                }
                packet.put("totPages", totPages);
                
                //gPS.setEstCat(pchtt.getCatCd()) ;
                //gPS.setFundSource(pchtt.getFundSource()) ;
               // gPS.setDescription(pchtt.getDescr()) ;
               // gPS.setEstimateDate(pchtt.getEtimateDt().toString());
                //Calendar cal = Calendar.getInstance();
        		//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                //gPS.setPrintDate( sdf.format(cal.getTime()));
                //gPS.setTotal(pchtt.getStdCost().doubleValue());
				//packet.put("totalCost", allTotalCost);
				packet.put("estimateDetails", pegArray);
			
			}
		
		
		
	}catch(Exception e){
		
	}
	return packet;

}
public JSONObject  generateWorkEstimateNo(HttpServletRequest request) {
	
	JSONObject packet = new JSONObject();
	
	String sessionKey= (String) request.getSession().getAttribute("region");
	String smcType=(String) request.getSession().getAttribute("smcType");
	String costCenterNo=(String) request.getSession().getAttribute("costCenterNo");
	String applicationId = request.getParameter("applicationId");
	String fundsou = request.getParameter("fundsou");
	String branchType =(String) request.getSession().getAttribute("branchType"); 
	String estimatePrefix = null; 
	EstimateNumberGenerator estimateNumberGenerator = new EstimateNumberGenerator();
	setFormat(new Format());
	//for relavent estimate number prefix
	if(applicationId != null && applicationId.length() == Constants.DEFAULT_COMM_REF_LENGTH ){//this is for construction reference generation relavant  to commecial reference
		estimatePrefix = estimateNumberGenerator.getConstructionReferenceViaCommerRefNumber(costCenterNo, applicationId);
	}else if(applicationId != null && applicationId.contains(PivPrefixType.getEST_Type(Constants.JOBTYPE_CONSTRUCTION))){ //this is for construction reference generation for the jobs initiating construction branch itself
		estimatePrefix = applicationId;
		System.out.println("hiiext1"+estimatePrefix);
	}else{
		
		if(branchType.equalsIgnoreCase(Constants.BRANCHTYPE_DISTRIBUTION_AND_MAINTENANCE)){ // jobs initiation by maintenance branch itself
			estimatePrefix = estimateNumberGenerator.getMaintenanceReferenceNumber(fundsou, costCenterNo,  smcType);
		}else if(branchType.equalsIgnoreCase(Constants.ESTIMATE_DISTRIBUTION_ENERGY_MANAGEMENT)){// jobs initiation by energy management branch itself
			estimatePrefix = estimateNumberGenerator.getEManagementReferenceNumber(fundsou, costCenterNo,  smcType);
		}else{
			estimatePrefix = estimateNumberGenerator.getConstructionReferenceNumber( fundsou,costCenterNo, smcType);
			System.out.println("hiiext2"+estimatePrefix);
		}
	}
	System.out.println("hiiext3"+estimatePrefix);
	
	String workEstmateNo = null;
	try {
		EstimateEjb estimateEjb = new EstimateEjb(sessionKey); 
		
		try {
			if(applicationId == null || (applicationId != null && applicationId.equalsIgnoreCase("undefined") )){
				workEstmateNo = estimateEjb.getWorkEstimateNo(estimatePrefix, null, costCenterNo, sessionKey);
			}else if(applicationId != null && applicationId.contains(PivPrefixType.getEST_Type(Constants.JOBTYPE_CONSTRUCTION))){
				workEstmateNo = applicationId;  // jobs initiation by construction branch itself - construction ref is same as the applicationId
			}else{
				workEstmateNo = estimateEjb.getWorkEstimateNo(estimatePrefix, applicationId, costCenterNo, sessionKey);
			}
			
			System.out.println("hiiext4"+workEstmateNo);
			//START -to do : This part added for UVA - Sceme allocation functionality
			allocationSummaryDisplayList =  (Map<String, AllocationSummaryDisplay>) request.getSession().getAttribute("allocationSummaryDisplayList");
			if(allocationSummaryDisplayList != null){
				List<AllocationSummaryDisplay> listAllocation = new ArrayList<AllocationSummaryDisplay>(allocationSummaryDisplayList.values());
				for(AllocationSummaryDisplay dis : listAllocation){
					dis.setConstructionRef(workEstmateNo);
					allocationSummaryDisplayList.put(dis.getLineId(), dis);
				}
				
				request.getSession().setAttribute("allocationSummaryDisplayList",allocationSummaryDisplayList);
				packet = loadLineSummaryDetailsInCache(request,workEstmateNo);
			}
			//END
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		if(workEstmateNo != null){
			
			packet.put("workEstmateNo", workEstmateNo);
		}
	 

	} catch (JSONException e) {
		
		e.printStackTrace();
	}

	return packet;
}
public JSONObject loadLineSummaryDetailsInCache(HttpServletRequest request,String estimateNumber){
	//HttpServletRequest request = ServletActionContext.getRequest();
	//String estimateNumber = request.getParameter("estimateNumber");
	String sessionKey= (String) request.getSession().getAttribute("region");
	String costCenterNo=(String) request.getSession().getAttribute("costCenterNo");
	JSONArray normsArray = new JSONArray();
	JSONObject packet =new JSONObject();
	
	allocationSummaryDisplayList =  (Map<String, AllocationSummaryDisplay>) request.getSession().getAttribute("allocationSummaryDisplayList");
	allocationSummaryList = (Map<String, AllocationSummary>) request.getSession().getAttribute("allocationSummaryList");
	allocationSummaryListByCommRef = (Map<String, List<AllocationSummaryDisplay>>) request.getSession().getAttribute("allocationSummaryListByCommRef");
	
	if(allocationSummaryDisplayList == null){
		allocationSummaryDisplayList = new HashMap<String, AllocationSummaryDisplay>();
	}
	if(allocationSummaryList == null){
		allocationSummaryList = new HashMap<String, AllocationSummary>();
	}
	
	if(allocationSummaryListByCommRef == null){
		allocationSummaryListByCommRef = new HashMap<String, List<AllocationSummaryDisplay>>();
	}
	try{
		EstimateEjb estimateEjb = new EstimateEjb(sessionKey);
		List<AllocationSummaryDisplay>  allosummary = null;
		List<AllocationSummaryDisplay> allosummary1 = new ArrayList<AllocationSummaryDisplay>(allocationSummaryDisplayList.values());
		List<AllocationSummaryDisplay> allosummary2 = allocationSummaryListByCommRef.get(estimateNumber);
		if(allosummary2 == null){
			allosummary = allosummary1;
		}else{
			allosummary = allosummary2;
		}
			if(allosummary != null){
				for(AllocationSummaryDisplay display: allosummary){
					JSONObject normDataRow =new JSONObject();
					
					
					normDataRow.put("estimateno", display.getEstimateNo());
					if(display.getConstructionRef() == null){
						display.setConstructionRef("Pending ....");
					}
					normDataRow.put("constructionRef", display.getConstructionRef());
					normDataRow.put("linesummaryid", display.getLineSummaryId());
					normDataRow.put("lineid", display.getLineId());
					normDataRow.put("toatllength",display.getTotalLineLength());
					normDataRow.put("remainLength",display.getRemainingLineLength() == null ? display.getTotalLineLength():display.getRemainingLineLength());
					normDataRow.put("totalestiamtecost", display.getTotalEstimatedCost());
					normDataRow.put("estiamtedate", display.getEstimateDate() );
					normDataRow.put("description", display.getDescription() == null ? "" : display.getDescription() );
					normDataRow.put("alloactedLength", display.getAllocatedLineLength() == null ? "0":display.getAllocatedLineLength() );
					normDataRow.put("remainlength",display.getRemainingLineLength());
					normDataRow.put("allocatedCost", display.getAllocatedEstimatedCost());
					allocationSummaryDisplayList.put(display.getLineId(), display);
					normsArray.put(normDataRow);
				}	
			}
			packet.put("summary", normsArray);
			request.getSession().setAttribute("allocationSummaryDisplayList",allocationSummaryDisplayList);
		
	}catch (Exception e) {
		// TODO: handle exception
	}
	
	return packet;
}
public JSONObject  generateEstimateNoForFirstFifty(HttpServletRequest request) {
	
	JSONObject estimateNo = new JSONObject();
	
	String sessionKey= (String) request.getSession().getAttribute("region");
	String smcType=(String) request.getSession().getAttribute("smcType");
	String costCenterNo=(String) request.getSession().getAttribute("costCenterNo");
	String applicationId = request.getParameter("applicationId");
	setFormat(new Format());
	//String estimatePrefix = costCenterNo+"/"+getFormat().getYear()+"/";
	String estimatePrefix = null;
	String workEstmateNo = null;
	try {
		EstimateEjb estimateEjb = new EstimateEjb(sessionKey); 
		EstimateNumberGenerator estimateNumberGenerator = new EstimateNumberGenerator();
		try {
			
			if(applicationId != null){
				//workEstmateNo = applicationId.replace("EBS", "SPS");
				workEstmateNo = estimateNumberGenerator.getReferenceForFirstFiftyEstimate(applicationId);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(workEstmateNo != null){
			
			estimateNo.put("workEstmateNo", workEstmateNo);
		}
	 

	} catch (JSONException e) {
		
		e.printStackTrace();
	}

	return estimateNo;
	}
public JSONObject  FindCommercialRef(HttpServletRequest request) {
	JSONObject packet =new JSONObject();
	JSONObject estimateNo = new JSONObject();
	
	String sessionKey= (String) request.getSession().getAttribute("region");
	String smcType=(String) request.getSession().getAttribute("smcType");
	String costCenterNo=(String) request.getSession().getAttribute("costCenterNo");
	String estimateNumber = request.getParameter("estimateNo");
	String fundsou = request.getParameter("fundsou");
	
	
	setFormat(new Format());
	String estimatePrefix = null; 
	
	EstimateReferenceEjb estejb = new EstimateReferenceEjb();
	EstimateReference refe = estejb.findByWorkEstimateNo(estimateNumber,costCenterNo, sessionKey);
	InmatmEjb matejb = new InmatmEjb(sessionKey);
	EstimateEjb ejb = new EstimateEjb(sessionKey);
	try{
		if(refe != null){
			packet.put("stdEstimateNo", refe.getId().getStandardEstimateNo());
			
			ApplicationReferenceEjb ap = new ApplicationReferenceEjb(sessionKey);
			ApplicationReference refre = ap.findByApplicationNo(refe.getId().getStandardEstimateNo());
			if(refre != null){
				
				WiringLandDetailConEjb con = new WiringLandDetailConEjb(sessionKey);
				WiringLandDetailConPK pk = new WiringLandDetailConPK();
				pk.setDeptId(costCenterNo.substring(0, 3).concat(".00"));
				pk.setApplicationId(refre.getId().getApplicationId());
				WiringLandDetailCon appp =con.findByAppId(pk);
				if(appp != null){
					packet.put("fundsource", appp.getFundSource().trim());
				}
				
				List<String> estimationRefNos = new ArrayList<String>();
				
				if(refe.getId().getStandardEstimateNo() != null){
					estimationRefNos.add(refe.getId().getStandardEstimateNo());
					
					request.getSession().setAttribute("estimationRefNos",estimationRefNos);
					EstimateEjb estimateEjb = new EstimateEjb(sessionKey); 
					//if(isExistCommeRef && applicationRefNo != null){
						PivDetailEjb ejbPiv = new PivDetailEjb(sessionKey);
						PivDetail piv = ejbPiv.findByReferenceNo(refe.getId().getStandardEstimateNo(), ReferenceType.ESTIMATE);
						if(piv != null){
							packet.put("pivNo", piv.getId().getPivNo());
							packet.put("amount", piv.getPivAmount());
							packet.put("pivDatePicker", piv.getConfirmedDate());
						}
				}
				
				/*}else{
					TempTb temptb = estimateEjb.findPIVDetails(estimateNo, costCenterNo, sessionKey);
					if(temptb != null){
						packet.put("pivNo", temptb.getPivNo());
						packet.put("amount", temptb.getPivAmount());
						packet.put("pivDatePicker", temptb.getPivDate());
						detail.setTb(temptb);
					}
				}*/
			
			}
			//estimateNos.add(refe.getId().getStandardEstimateNo());
		}else{
			packet.put("invalid", true);
			packet.put("errorMessage", "Not exist! ... Please enter Correct Reference");
			
		}
		
		
	
		} catch (JSONException e) {
			
			e.printStackTrace();
		}

	return packet;
	}


public JSONObject  checkWorkEstimateNoExist(HttpServletRequest request) {
	
	JSONObject message = new JSONObject();
	
	String sessionKey= (String) request.getSession().getAttribute("region");
	String smcType=(String) request.getSession().getAttribute("smcType");
	String costCenterNo=(String) request.getSession().getAttribute("costCenterNo");
	//String applicationId = request.getParameter("applicationId");
	String estimateNoRef = request.getParameter("estimateNo");
	try {
		if(estimateNoRef != null && estimateNoRef.length() != 0){
			setFormat(new Format());
			String estimatePrefix = estimateNoRef;
			String workEstmateNo = null;
			
				EstimateEjb estimateEjb = new EstimateEjb(sessionKey); 
				EstimateReferenceEjb estimateRefEjb = new EstimateReferenceEjb(); 
				boolean status = false;
				try {
					
						//status = estimateRefEjb.checkEstimateNoExist(estimateNoRef.trim(), estimatePrefix, costCenterNo, sessionKey);
						status = estimateEjb.checkEstimateNoExist(estimateNoRef.trim(), costCenterNo, sessionKey);
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(status){
					
					message.put("status", "Reference Already Exist!!!");
					message.put("isExist", true);
				}else{
					message.put("status", "You can continue with this Reference!!!");
					message.put("isExist", false);
				}
		}else{
			message.put("status", "Please enter Estimate number!!!");
		}
	 

	} catch (JSONException e) {
		
		e.printStackTrace();
	}

	return message;
	}
public JSONObject  loadPIVDetails(HttpServletRequest request) {
	
	JSONObject pivDetails = new JSONObject();
	nf.setGroupingUsed(true);
	nf.setMaximumFractionDigits(2);
	nf.setMinimumFractionDigits(2);
	String sessionKey= (String) request.getSession().getAttribute("region");
	String smcType=(String) request.getSession().getAttribute("smcType");
	String costCenterNo=(String) request.getSession().getAttribute("costCenterNo");
	//HttpServletRequest request = ServletActionContext.getRequest();
	String costCenter = (String) request.getSession().getAttribute("cSCNo");
	if(costCenter != null){
		costCenterNo = costCenter;
		//setCostCenterNo(costCenterNo);
	}
	
	String estimateNo = request.getParameter("estimateNo");
	setFormat(new Format());
	//String estimatePrefix = costCenterNo+"/"+getFormat().getYear()+"/";
	String estimatePrefix = null;
	String workEstmateNo = null;
	String costCenterCommercialNo = null;
	try {
		EstimateEjb estimateEjb = new EstimateEjb(sessionKey); 
		EstimateReferenceEjb ejb = new EstimateReferenceEjb();
		ApplicationReferenceEjb appejb = new ApplicationReferenceEjb(sessionKey);
		if(estimateNo != null){
			EstimateReference ref = ejb.findByWorkEstimateNo(estimateNo.trim(), costCenterNo, sessionKey);
			if(ref != null){
				PivDetailEjb ejb_PIV_Detail = new PivDetailEjb(sessionKey);	
				List<PivDetail> pivHistoryList =new   ArrayList<PivDetail>();
				costCenterCommercialNo = costCenterNo.substring(0, 3).concat(".00");
				PivDetail pivDetail1 = ejb_PIV_Detail.findByReferenceNo(costCenterCommercialNo, ref.getId().getStandardEstimateNo(),ReferenceType.ESTIMATE);
				if(pivDetail1 != null){
					pivDetails.put("PivIINo", pivDetail1.getId().getPivNo());
					pivDetails.put("applicationRef", pivDetail1.getAppReferenceNo());
					pivDetails.put("bankCode", pivDetail1.getChequeBankCode());
					pivDetails.put("branchCode", pivDetail1.getChequeBranchCode());
					pivDetails.put("paymentMode", (pivDetail1.getPaymentMode() != null && pivDetail1.getPaymentMode().equalsIgnoreCase("Q")) ? "Cheque" : "Cache");
					pivDetails.put("payDate", pivDetail1.getPivDate());
					pivDetails.put("recieptNo", pivDetail1.getPivReceiptNo());
					pivDetails.put("checkNo", pivDetail1.getChequeNo());
					pivDetails.put("pivAmount", nf.format(pivDetail1.getPivAmount()));
					pivDetails.put("secDeposit",  nf.format(pivDetail1.getSecurityDeposit()));
					pivDetails.put("serviceConnectionAmount",  nf.format(pivDetail1.getSerConnOrElecSch()));
					pivDetails.put("txfdJobNo",  ref.getProjectno() == null ? "" : ref.getProjectno());
				}
				/*ApplicationReference applicationReference = appejb.findByApplicationNo (ref.getId().getStandardEstimateNo());
				if(applicationReference != null){
					PivDetail pivDetail2 = ejb_PIV_Detail.findByReferenceNo(costCenterNo, ref.getId().getStandardEstimateNo(),ReferenceType.APPLICATION);
					pivHistoryList.add(pivDetail2);
				}*/
				
				
				
			}else{
				
				TempTb temptb = estimateEjb.findPIVDetails(estimateNo, costCenterNo, sessionKey);
				if(temptb != null){
					pivDetails.put("PivIINo", temptb.getPivNo() != null ? temptb.getPivNo() : "");
					pivDetails.put("pivAmount",  nf.format(temptb.getPivAmount()));
					pivDetails.put("payDate", temptb.getPivDate());
					//pivDetails.setTb(temptb);
				}
			}
		}
		

			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*if(pivDetails != null){
			
			estimateNo.put("workEstmateNo", workEstmateNo);
		}
	 */

	

	return pivDetails;
	}
public JSONObject  searchEstimationDetails(HttpServletRequest request) throws JSONException{
	JSONObject packet =new JSONObject();
	String region= (String) request.getSession().getAttribute("region");
	List<Approval> approvalHistory = null;
	JSONArray pegArray = new JSONArray();
	JSONArray approveHistoryArray = new JSONArray();
	JSONArray pegDetailsRowaray = new JSONArray();
	EstimateDetails detail = new EstimateDetails();
	List<String> estimateNos = new ArrayList<String>();
	try{
		
		String sessionKey= (String) request.getSession().getAttribute("region");
		String applicationNo = request.getParameter("applicationNo");
		String estimateNo = request.getParameter("estimateNo");
		String requestCostCenter = request.getParameter("requestCostCenter");
		String costCenterNo=(String) request.getSession().getAttribute("costCenterNo");
		String smcType = request.getParameter("smcType");
		String userRole= (String) request.getSession().getAttribute("userRole");
		
		String fileRef = request.getParameter("fileRef");
		PcesthttEjb pc = new PcesthttEjb(sessionKey);
		String projectNo=null;
		if(requestCostCenter != null && !requestCostCenter.equalsIgnoreCase(Constants.DEFAULT_STRING)){
			costCenterNo = requestCostCenter;
		}
		Pcesthtt pchtt =  pc.findByEstimationNo(estimateNo, costCenterNo,Constants.REV_NO);
		if(pchtt == null){
			packet.put("invalid", true);
			packet.put("errorMessage", "Please enter correct number!!");
			
			
		}
		PcesthmtEjb hmtejb = new PcesthmtEjb(sessionKey);
		Pcesthmt pchmt =  hmtejb.findByEstimationNo(estimateNo, costCenterNo);
		if(pchmt != null){
			packet.put("projectNo", pchmt.getProjectNo());
			//packet.put("projectNo", "002/2010");
			projectNo =  pchmt.getProjectNo();
		}
		boolean isExistCommeRef = false;
		String applicationRefNo = null;
		EstimateReferenceEjb estejb = new EstimateReferenceEjb();
		EstimateReference refe = estejb.findByWorkEstimateNo(estimateNo.trim(),costCenterNo, sessionKey);
		InmatmEjb matejb = new InmatmEjb(sessionKey);
		EstimateEjb ejb = new EstimateEjb(sessionKey);
		if(refe != null){
			packet.put("stdEstimateNo", refe.getId().getStandardEstimateNo());
			applicationRefNo = refe.getId().getStandardEstimateNo();
			estimateNos.add(refe.getId().getStandardEstimateNo());
			request.getSession().setAttribute("estimationRefNos",estimateNos);
			//loadEstmationNumbers(request);
			String commCost = costCenterNo.substring(0, 3);
			SpstdesthmtEjb spejb = new SpstdesthmtEjb(sessionKey);
			SpstdesthmtPK spstdesthmtPK = new SpstdesthmtPK();
			spstdesthmtPK.setApplicationNo(applicationNo);
			spstdesthmtPK.setDeptId(commCost+".00");
			spstdesthmtPK.setStdNo(refe.getId().getStandardEstimateNo());
			spstdesthmtPK.setApplicationNo(refe.getId().getStandardEstimateNo());
			Spstdesthmt hmt = spejb.findById(spstdesthmtPK, sessionKey);
			if(hmt != null){
				isExistCommeRef = true;
				NumberFormat nf = NumberFormat.getInstance();
				nf.setGroupingUsed(true);
				nf.setMaximumFractionDigits(2);
				nf.setMinimumFractionDigits(2);
				double deuction = 0.0;
				if(hmt.getRebateCost() != null){
					deuction = hmt.getRebateCost().doubleValue()+hmt.getTotalCost().doubleValue();
				}else{
					deuction = hmt.getTotalCost().doubleValue();
				}
			
				double workECost = 0.0;
				if(pchtt.getStdCost() != null){
					workECost = pchtt.getStdCost().doubleValue();
				}
				double variance = 0;
				if(hmt.getTotalCost() != null && hmt.getTotalCost().doubleValue()!= 0.0){
					variance = ((workECost - deuction)/hmt.getTotalCost().doubleValue())*100;
				}
				
				packet.put("stdtotalCost",hmt.getTotalCost() != null ? nf.format(hmt.getTotalCost().doubleValue()) : "0.00");
				packet.put("rebateCost", hmt.getRebateCost() != null ? nf.format(hmt.getRebateCost().doubleValue()) : "0.00");
				packet.put("variance",variance+"%");
				
				
			}
		}
		if (pchtt!=null){
			
			
			if(pchtt.getStdCost() != null){
				packet.put("totalCost",nf.format(pchtt.getStdCost().doubleValue()));
			}
			
			if(pchtt.getStdCost() != null ){
				packet.put("isApprovable", EstimateApproval.getApprovalStatus(pchtt.getStdCost().doubleValue(), userRole));
			}
			
			packet.put("rebate", pchtt.getPartialAmt());
			packet.put("estimateDatePicker", pchtt.getEtimateDt());
			packet.put("fundsou", pchtt.getFundSource().trim());
			packet.put("fundid", pchtt.getFundId().trim());
			packet.put("description", pchtt.getDescr());
			if(pchtt.getCatCd() != null){
				packet.put("categoryId", pchtt.getCatCd().trim());
			}
			packet.put("costCenter", costCenterNo);
			packet.put("rejectedReason", getRejectedReason(pchtt,userRole,sessionKey));
			
			detail.setHtt(pchtt);
		}
		
		EstimateEjb estimateEjb = new EstimateEjb(sessionKey); 
		if(isExistCommeRef && applicationRefNo != null){
			PivDetailEjb ejbPiv = new PivDetailEjb(sessionKey);
			PivDetail piv = ejbPiv.findByReferenceNo(applicationRefNo, ReferenceType.ESTIMATE);
			if(piv != null){
				packet.put("pivNo", piv.getId().getPivNo());
				packet.put("amount", nf.format(piv.getPivAmount()));
				packet.put("pivDatePicker", piv.getConfirmedDate());
			}
		}else{
			TempTb temptb = estimateEjb.findPIVDetails(estimateNo.trim(), costCenterNo, sessionKey);
			if(temptb != null){
				packet.put("pivNo", temptb.getPivNo());
				if(temptb.getPivAmount() != null){
					packet.put("amount", nf.format(temptb.getPivAmount()));
				}else{
					packet.put("amount","0.00");
				}
				packet.put("pivDatePicker", temptb.getPivDate() != null ? temptb.getPivDate() : "");
				detail.setTb(temptb);
			}
		}
		
		
		PcestdttEjb pd = new PcestdttEjb(sessionKey);
		List<Pcestdtt> pcdtts =  pd.findByEstimationNo(estimateNo, costCenterNo,Constants.REV_NO);

		
		if(pcdtts != null && pcdtts.size() != 0){
			//packet.put("disableSave", true);
				detail.setDtts(pcdtts);
				for(Pcestdtt dtt : pcdtts){
					JSONObject estimateDetailsRow =new JSONObject();
					//estimateDetailsRow.put("selectedPegId", dtt);
					estimateDetailsRow.put("resourceType", dtt.getResType());
					estimateDetailsRow.put("resourceCategory",dtt.getResCat());
					estimateDetailsRow.put("resourceCode",dtt.getId().getResCd().trim());
					
					if(dtt.getId().getResCd().trim().equalsIgnoreCase("MAT")){
						estimateDetailsRow.put("resourceName", Constants.MAT_RESOUR_NAME);
					}else if(matejb.findName(dtt.getId().getResCd().trim()) == null){
						estimateDetailsRow.put("resourceName", dtt.getId().getResCd().trim());
					}else{
						estimateDetailsRow.put("resourceName", matejb.findName(dtt.getId().getResCd().trim()));
					}
					estimateDetailsRow.put("unitPrice", dtt.getUnitPrice());
					estimateDetailsRow.put("uom", dtt.getUom());
					//estimateDetailsRow.put("tolerance", dtt.getTolerance());
					estimateDetailsRow.put("estimateQuantity",dtt.getEstimateQty());
					estimateDetailsRow.put("estimateCost", dtt.getEstimateCost());
					pegArray.put(estimateDetailsRow);
					
					DetailEstimateDTO detailEstimateDTO = new DetailEstimateDTO();
					detailEstimateDTO.setResourceCode(dtt.getId().getResCd().trim());
					detailEstimateDTO.setUom(dtt.getUom());
					detailEstimateDTO.setEstimateCost(dtt.getEstimateCost());
					detailEstimateDTO.setEstimatedQuantity(dtt.getEstimateQty());
					detailEstimateDTO.setResourceType(dtt.getResType());
					detailEstimateDTO.setResourceName(dtt.getId().getResCd());
					detailEstimateDTO.setUnitPrice(dtt.getUnitPrice());
					
					//allSelectedAdditionalResoMap.put(dtt.getId().getResCd().trim(), detailEstimateDTO);
					//alreadyAddedDetails.put(dtt.getId().getResCd().trim(), detailEstimateDTO);
					//allTotalCost = dtt.getEstimateQty().floatValue()*dtt.getEstimateCost().floatValue();
				}
				
				//packet.put("totalCost", allTotalCost);
				packet.put("estimateDetails", pegArray);
			
			}
		
		 String estStatus = pchtt.getStatus().toString() ;
         EstimateStatus estSt = new EstimateStatus();
         //estimateStatus =estSt.searchStatus((Integer.parseInt(estStatus)));
         if(Integer.parseInt(estStatus) == 22 && projectNo != null && projectNo.length() > 0){
        	 packet.put("status",Constants.DEFAULT_STATUS_22);
         }else{
        	 packet.put("status", estSt.searchStatus((Integer.parseInt(estStatus))));
         }
        
		ApprovalEjb ejb_approval = new ApprovalEjb(region);
		
		List<Approval> approval1 = ejb_approval.findByReferenceNo(estimateNo);
		if(approval1 != null && approval1.size() > 0){
			for(Approval approval : approval1){
				JSONObject approvalDetailsRow =new JSONObject();
				//estimateDetailsRow.put("selectedPegId", dtt);
				approvalDetailsRow.put("approvedId", approval.getApprovalId());
				approvalDetailsRow.put("approvedLevel", approval.getApprovedLevel());
				approvalDetailsRow.put("approvedtype",approval.getApprovalType());
				approvalDetailsRow.put("approvedby",approval.getApprovedBy());
				approvalDetailsRow.put("approveddate",approval.getApprovedDate());
				approvalDetailsRow.put("approvedtime",approval.getApprovedTime());
				approveHistoryArray.put(approvalDetailsRow);
				
			}
			packet.put("approvals", approveHistoryArray);
		}
			
			
	}catch(Exception e){
		
	}
	return packet;

}
public JSONObject  searchJobDetails(HttpServletRequest request) throws JSONException{
	JSONObject packet =new JSONObject();
	List<Approval> approvalHistory = null;
	JSONArray pegArray = new JSONArray();
	JSONArray approveHistoryArray = new JSONArray();
	JSONArray pegDetailsRowaray = new JSONArray();
	try{
		Pcesthmt pchmt = null;
		Pcesthtt pchtt = null;
		
		String sessionKey= (String) request.getSession().getAttribute("region");
		String applicationNo = request.getParameter("applicationNo");
		String projectNo = request.getParameter("projectNo");
		String estimateNo = request.getParameter("estimateNo");
		String requestCostCenter = request.getParameter("requestCostCenter");
		String costCenterNo=(String) request.getSession().getAttribute("costCenterNo");
		String smcType=(String) request.getSession().getAttribute("smcType");
		InmatmEjb matejb = new InmatmEjb(sessionKey);
		PcesthmtEjb pc = new PcesthmtEjb(sessionKey);
		JobEjb ejb = new JobEjb(sessionKey);
		if(requestCostCenter != null || !requestCostCenter.equalsIgnoreCase(Constants.DEFAULT_STRING)){
			costCenterNo = requestCostCenter;
		}
		
		if(projectNo != null && !projectNo.trim().equalsIgnoreCase(Constants.DEFAULT_STRING)){
			pchmt =  pc.findByJobNo(projectNo, costCenterNo);
		}else if(estimateNo != null && !estimateNo.trim().equalsIgnoreCase(Constants.DEFAULT_STRING)){
			pchmt =  pc.findByEstimationNo(estimateNo.trim(), costCenterNo);
			
		}
		//Pcesthmt pchmt =  pc.findByJobNo(projectNo, costCenterNo);
		List<String> estimateNos = new ArrayList<String>();
		if(pchmt == null){
			packet.put("invalid", true);
			packet.put("errorMessage", "Please enter correct number!!");
	
		}else{
			if(pchmt.getStdCost() != null){
				packet.put("totalCost",pchmt.getStdCost());
			}
	
			packet.put("rebate", pchmt.getPartialAmt());
			packet.put("estimateNo", pchmt.getId().getEstimateNo());
			packet.put("projectNo", pchmt.getProjectNo());
			packet.put("estimateDatePicker", pchmt.getEtimateDt());
			packet.put("fundsou", pchmt.getFundSource().trim());
			packet.put("fundid", pchmt.getFundId().trim());
			packet.put("description", pchmt.getDescr());
			packet.put("reviseReason", pchmt.getRevReason());
			packet.put("categoryId", pchmt.getCatCd().trim());
			packet.put("costCenter", costCenterNo);
			packet.put("rejectedReason", pchmt.getRejectReason());
			String estStatus = pchmt.getStatus().toString() ;
			EstimateStatus estSt = new EstimateStatus();
			packet.put("status", estSt.searchStatus((Integer.parseInt(estStatus))));

			EstimateEjb estimateEjb = new EstimateEjb(sessionKey); 
			
			TempTb temptb = estimateEjb.findPIVDetails(projectNo, costCenterNo, sessionKey);
			if(temptb != null){
				packet.put("pivNo", temptb.getPivNo());
				packet.put("amount", temptb.getPivAmount());
				packet.put("pivDatePicker", temptb.getPivDate());
				
			}
			
			PcestdmtEjb pd = new PcestdmtEjb(sessionKey);
			List<Pcestdmt> pcdmts =  pd.findByEstimationNo(pchmt.getId().getEstimateNo(), costCenterNo);

			if(pcdmts != null && pcdmts.size() != 0){
				//packet.put("disableSave", true);
					
					for(Pcestdmt dmt : pcdmts){
						JSONObject estimateDetailsRow =new JSONObject();
						//estimateDetailsRow.put("selectedPegId", dtt);
						estimateDetailsRow.put("resourceType", dmt.getResType());
						estimateDetailsRow.put("resourceCategory",dmt.getResCat());
						estimateDetailsRow.put("resourceCode",dmt.getId().getResCd().trim());
						//estimateDetailsRow.put("resourceName", dmt.get);
						if(dmt.getId().getResCd().trim().equalsIgnoreCase("MAT")){
							estimateDetailsRow.put("resourceName", Constants.MAT_RESOUR_NAME);
						}else if(matejb.findName(dmt.getId().getResCd().trim()) == null){
							estimateDetailsRow.put("resourceName", dmt.getId().getResCd().trim());
						}else{
							estimateDetailsRow.put("resourceName", matejb.findName(dmt.getId().getResCd().trim()));
						}
						estimateDetailsRow.put("unitPrice", dmt.getUnitPrice());
						estimateDetailsRow.put("uom", dmt.getUom());
						//estimateDetailsRow.put("tolerance", dtt.getTolerance());
						estimateDetailsRow.put("estimateQuantity",dmt.getEstimateQty());
						estimateDetailsRow.put("estimateCost", dmt.getEstimateCost());
						pegArray.put(estimateDetailsRow);
						
						
						
						//allTotalCost = dtt.getEstimateQty().floatValue()*dtt.getEstimateCost().floatValue();
					}
					
					//packet.put("totalCost", allTotalCost);
					packet.put("estimateDetails", pegArray);
				
				}
			
	         
			ApprovalEjb ejb_approval = new ApprovalEjb(sessionKey);
			
			List<Approval> approval1 = ejb_approval.findByReferenceNo(projectNo);
			if(approval1 != null && approval1.size() > 0){
				for(Approval approval : approval1){
					JSONObject approvalDetailsRow =new JSONObject();
					//estimateDetailsRow.put("selectedPegId", dtt);
					approvalDetailsRow.put("approvedId", approval.getApprovalId());
					approvalDetailsRow.put("approvedLevel", approval.getApprovedLevel());
					approvalDetailsRow.put("approvedtype",approval.getApprovalType());
					approvalDetailsRow.put("approvedby",approval.getApprovedBy());
					approvalDetailsRow.put("approveddate",approval.getApprovedDate());
					approvalDetailsRow.put("approvedtime",approval.getApprovedTime());
					approveHistoryArray.put(approvalDetailsRow);
					
				}
				packet.put("approvals", approveHistoryArray);
			}
		
		}
	}catch(Exception e){
		
	}
	return packet;

}
public JSONObject  generateNextFileRefNo(HttpServletRequest request) {
	
	JSONObject packet = new JSONObject();
	System.out.println("codeprefixnew ");
	String sessionKey= (String) request.getSession().getAttribute("region");
	String smcType=(String) request.getSession().getAttribute("smcType");
	String costCenterNo=(String) request.getSession().getAttribute("costCenterNo");
	String applicationId=request.getParameter("applicationId");
	
	System.out.println("codeprefix " +applicationId );
	String fileRef = null;
	try {
		EstimateReferenceEjb estimateEjb = new EstimateReferenceEjb(); 
		String codeprefix = populateCodeprefix(applicationId,costCenterNo,sessionKey);
		System.out.println("codeprefix :"+codeprefix);
		try {
			if(costCenterNo != null && codeprefix != null && codeprefix.length() > 0){
				fileRef = estimateEjb.getNextFileRefNo(codeprefix, costCenterNo, sessionKey);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(fileRef != null){
			
			packet.put("fileRef", codeprefix+fileRef);
		}
	 

	} catch (JSONException e) {
		
		e.printStackTrace();
	}

	return packet;
	}

	public String populateCodeprefix(String applicationNo,String costCenterNo,String sessionKey){
		System.out.println("codeprefix 1:");
		StringBuffer codeNumber = new StringBuffer();
		try{
			System.out.println("codeprefix 2:");
			WiringLandDetailConEjb con = new WiringLandDetailConEjb(sessionKey);
			WiringLandDetailCon wiringLandDetailCon = con.findByApplicationNo(applicationNo);
			System.out.println("codeprefix 5:" + wiringLandDetailCon);
			System.out.println("codeprefix 6:" + wiringLandDetailCon.getDistrict() );
			System.out.println("codeprefix 6:" + wiringLandDetailCon.getDistrict() );
			ProvinceDetailsMasterEjb province = new ProvinceDetailsMasterEjb();
			//StringBuffer codeNumber = new StringBuffer();
			if(wiringLandDetailCon != null && wiringLandDetailCon.getDistrict() != null && wiringLandDetailCon.getDistrict().length() > 0){
				codeNumber.append(province.findCode(wiringLandDetailCon.getDistrict(), costCenterNo, Constants.DISTRICT_CODE_KEY, sessionKey));
			}
			if(wiringLandDetailCon != null && wiringLandDetailCon.getElectorate() != null && wiringLandDetailCon.getElectorate().length() > 0){
				codeNumber.append(province.findCode(wiringLandDetailCon.getElectorate(), costCenterNo, Constants.ELECTORATE_CODE_KEY, sessionKey));
			}
			if(wiringLandDetailCon != null && wiringLandDetailCon.getServiceDepoName() != null && wiringLandDetailCon.getServiceDepoName().length() > 0){
				codeNumber.append(province.findCode(wiringLandDetailCon.getServiceDepoName(), costCenterNo, Constants.CSC_CODE_KEY, sessionKey));
			}
			
		}catch(Exception e){
			
		}
		System.out.println("codeprefix 4:");
		return codeNumber.toString();
	}
	
	public JSONObject getContractorname(HttpServletRequest request){
		JSONObject userIdObejct = new JSONObject();
		String contractorId = request.getParameter("codeId");
		System.out.println("contractorId : " + contractorId);
		String deptId = request.getParameter("deptId");
		String sessionKey= (String) request.getSession().getAttribute("region");
		
		try {
			
			SpestcntEjb spestcntEjb=new SpestcntEjb(sessionKey);
			Spestcnt spestcnt = spestcntEjb.findByContractorId(contractorId, deptId);
			
			System.out.println("Hiiii : getContractorname" + spestcnt);
			if(spestcnt != null){
				
				userIdObejct.put("contractorName", spestcnt.getContractorName());
				System.out.println("Hiiii : " + spestcnt.getContractorName());
		
			}


		} catch (JSONException e) {
			
			e.printStackTrace();
		}

		return userIdObejct;
	}
	
	public JSONObject  loadContractors(HttpServletRequest request) {
		
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
	public JSONObject  loadMilestones(HttpServletRequest request) {
		
		String sessionKey= (String) request.getSession().getAttribute("region");
		String costCenterNo= (String) request.getSession().getAttribute("costCenterNo");
		JSONArray milestoneJsonArray = new JSONArray();
		
		
		JSONObject objectlist = new JSONObject();
		milestoneList = (Map<String, Pcmilem>) request.getSession().getAttribute("milestoneList");
		
		if(milestoneList == null){
			milestoneList = new HashMap<String, Pcmilem>();
		}
		
		try {
			PcmilemEjb ejb = new PcmilemEjb(sessionKey);
			List <Pcmilem> milestonelist = ejb.getMilestoneList(costCenterNo, sessionKey);
			
				if (milestonelist != null) {

					for (Pcmilem milestone : milestonelist) {
						JSONObject milestoneJson = new JSONObject();

						milestoneJson.put("id", milestone.getId().getMileId());
						milestoneJson.put("name", milestone.getMile_nm() +" - "+milestone.getPercentage());
						milestoneList.put(milestone.getId().getMileId(), milestone);
						milestoneJsonArray.put(milestoneJson);
					}

					objectlist.put("jsonmilestoneAray",
							milestoneJsonArray);
					
					request.getSession().setAttribute("milestoneList",milestoneList);
				}
				
	       
	} catch (JSONException e) {
			
			e.printStackTrace();
		}
		
		
		return objectlist;

	}
	
	
	public JSONObject deleteLine(HttpServletRequest request){
		JSONObject packet =new JSONObject();
		JSONArray pegArray = new JSONArray();
		String lineID = request.getParameter("lineid");
		String estimateNo = request.getParameter("estimateNo");
		String dateupdate = request.getParameter("dateupdate");
		
		addedMilestones = (Map<String, Pcmiledates>) request.getSession().getAttribute("addedMilestones");
		System.out.println("start****addedMilestones" + addedMilestones);
		if(addedMilestones == null ){
			addedMilestones = new HashMap<String, Pcmiledates>();
		}
		
		String costCenterNo= (String) request.getSession().getAttribute("costCenterNo");
		String sessionKey= (String) request.getSession().getAttribute("region");
		EstimateEjb estimateEjb = new EstimateEjb(sessionKey); 
		Date objDate;
		Format sss = new Format();
		objDate = sss.StrToDate(dateupdate);
		if(addedMilestones.containsKey(lineID.trim())){
			System.out.println("start****1lineID" + lineID);
			addedMilestones.remove(lineID.trim());
		}
		
		PcmiledatesEjb obj = new PcmiledatesEjb(sessionKey);
		PcmilesumaryEjb objSu = new PcmilesumaryEjb(sessionKey);
		if(lineID != null && estimateNo != null && dateupdate != null){
			estimateNo = estimateNo.trim();
			System.out.println("start****1" + estimateNo + "fff : "+ costCenterNo + "line ID "+lineID + "cccc : "+objDate );
			Pcmiledates pcmiledates = obj.getMilestoneByDate(costCenterNo, estimateNo, lineID,objDate,sessionKey);
			
			System.out.println("start****1"+ pcmiledates);
	        obj.removePcmiledates(pcmiledates, sessionKey);
	        	
	        }
		try {
			request.getSession().setAttribute("addedMilestones",addedMilestones);
			//packet.put("addedMilestones", pegArray);
			
				//if(status == 1){
					packet.put("success","Successfully Deleted");
				//}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			return packet;
	}
	public JSONObject  addProgress(HttpServletRequest request) {
		Format format=new Format();
		JSONObject packet =new JSONObject();
		JSONArray pegArray = new JSONArray();
		
		String sessionKey= (String) request.getSession().getAttribute("region");
		String costCenterNo=(String) request.getSession().getAttribute("costCenterNo");
		
		String milestoneId = request.getParameter("milestoneId");
		String remark = request.getParameter("remark");
		String estimateNo = request.getParameter("estimateNo");
		String projectNo = request.getParameter("projectNo");
		if(projectNo != null){
			projectNo = projectNo.trim();
		}
		String dateOfprogress = request.getParameter("dateOfprogress");
		
		
		
		try {
			EstimateEjb estimateEjb = new EstimateEjb(sessionKey); 
			milestoneList = (Map<String, Pcmilem>) request.getSession().getAttribute("milestoneList");
			addedMilestones = (Map<String, Pcmiledates>) request.getSession().getAttribute("addedMilestones");
			if(addedMilestones == null ){
				addedMilestones = new HashMap<String, Pcmiledates>();
			}
			
			
			if(milestoneList == null ){
				PcmilemEjb ejb = new PcmilemEjb(sessionKey);
				List <Pcmilem> milestonelist = ejb.getMilestoneList(costCenterNo, sessionKey);
				
					if (milestonelist != null) {

						for (Pcmilem milestone : milestonelist) {
							JSONObject milestoneJson = new JSONObject();

							milestoneJson.put("id", milestone.getId().getMileId());
							milestoneJson.put("name", milestone.getMile_nm() +" - "+milestone.getPercentage());
							milestoneList.put(milestone.getId().getMileId(), milestone);
							
						}

					}
			}
			System.out.println("start**********************" +milestoneId.trim() );
			
			
			/**if(addedMilestones.containsKey(milestoneId.trim())){
				packet.put("invalid",true);
				packet.put("errorMessage", "Already exist");
				return packet;
			}*/
			
			
			ProgressMonitoringEjb progressMonitoringEjb = new ProgressMonitoringEjb(sessionKey); 
			
			Pcmiledates pcmiledate= new Pcmiledates();
			PcmiledatesPK pcmilemk = new PcmiledatesPK();
			pcmilemk.setDeptId(costCenterNo);
			pcmilemk.setMileId(milestoneId);
			
			if(dateOfprogress != null && !dateOfprogress.equalsIgnoreCase(Constants.DEFAULT_STRING)){
				pcmilemk.setEnterDate(format.StrToDate(dateOfprogress.substring(0, 10)));
			}
			
			if(costCenterNo != null && costCenterNo.equalsIgnoreCase("510.20")){
				pcmilemk.setProjectNumber(projectNo); //since WPS1 entering progress details via project no 
			}else{
				pcmilemk.setProjectNumber(estimateNo); //for other sites according to their requirement
			}
			pcmiledate.setId(pcmilemk);
			
			PcmilesumaryEjb summaryejb = new PcmilesumaryEjb(sessionKey);
	
			/**Pcmilesumary summaryObj= summaryejb.getMilestoneList(costCenterNo, pcmilemk.getProjectNumber(), sessionKey);
			if(summaryObj != null){
				summaryejb.removePcmilesumary(summaryObj, sessionKey);
			}*/
			
			Pcmilesumary summary= summaryejb.getMilestoneList(costCenterNo, pcmilemk.getProjectNumber(), sessionKey);
			
			
			if(summary == null){
				summary = new Pcmilesumary();
				PcmilesumaryPK pcmilesumaryPK = new PcmilesumaryPK();
				pcmilesumaryPK.setDeptId(costCenterNo);
				System.out.println("milestoneId : " + milestoneId);
				pcmilesumaryPK.setMileId(milestoneId);
				pcmilesumaryPK.setProjectNo(pcmilemk.getProjectNumber());
				if(milestoneId != null && milestoneList != null && milestoneList.size() > 0 && milestoneList.get(milestoneId.trim()) != null){
					summary.setCpercentage(milestoneList.get(milestoneId.trim()).getPercentage());
				}
				if(dateOfprogress != null && !dateOfprogress.equalsIgnoreCase(Constants.DEFAULT_STRING)){
					summary.setDateLast(format.StrToDate(dateOfprogress.substring(0, 10)));
				}
				summary.setStatus(new BigDecimal("0"));
				summary.setId(pcmilesumaryPK);
				//summary.setStatus(status);
				
			}else{
				
				if(milestoneId != null && milestoneList != null && milestoneList.size() > 0 && milestoneList.get(milestoneId.trim()) != null){
					summary.setCpercentage(milestoneList.get(milestoneId.trim()).getPercentage());
				}
				if(dateOfprogress != null && !dateOfprogress.equalsIgnoreCase(Constants.DEFAULT_STRING)){
					summary.setDateLast(format.StrToDate(dateOfprogress.substring(0, 10)));
				}
				
			}
			
			if(milestoneId != null && milestoneList != null && milestoneList.size() > 0 && milestoneList.get(milestoneId.trim()) != null){
				pcmiledate.setCpercentage(milestoneList.get(milestoneId.trim()).getPercentage());
				pcmiledate.setMileName(milestoneList.get(milestoneId.trim()).getMile_nm());
				
			}
		
			pcmiledate.setRemarks(remark);
			
			
			progressMonitoringEjb.updateProgress(pcmiledate, summary,sessionKey);
			
		/*	//for(SpPointdmt dmt : list){
				JSONObject masterDetaRow =new JSONObject();
				masterDetaRow.put("milesToneId", milestoneId);
				masterDetaRow.put("remarks", pcmiledate.getRemarks());
				masterDetaRow.put("percentage",pcmiledate.getCpercentage());
				//masterDetaRow.put("remarks", spPointdmt.getResName());
				masterDetaRow.put("updatedDate", pcmiledate.getId().getEnterDate());
				
				
				pegArray.put(masterDetaRow);
				addedMilestones.put(milestoneId.trim(), pcmiledate);
				request.getSession().setAttribute("addedMilestones",addedMilestones);
			//}resourceMap
			packet.put("addedMilestones", pegArray);*/
		
			
			PcmiledatesEjb miledates = new PcmiledatesEjb(sessionKey);
			List<Pcmiledates> pcmiledates =  miledates.getMilestoneList(costCenterNo, pcmilemk.getProjectNumber().trim(), sessionKey);

			
			if(pcmiledates != null && pcmiledates.size() != 0){
				
					for(Pcmiledates pcmileda : pcmiledates){
						JSONObject masterDetaRow =new JSONObject();
						masterDetaRow.put("milesToneId", pcmileda.getId().getMileId());
						masterDetaRow.put("remarks", pcmileda.getRemarks());
						masterDetaRow.put("percentage",pcmileda.getCpercentage());
						//masterDetaRow.put("remarks", spPointdmt.getResName());
						masterDetaRow.put("updatedDate",pcmileda.getId().getEnterDate());
						
						
						pegArray.put(masterDetaRow);
						addedMilestones.put(pcmileda.getId().getMileId().trim(), pcmileda);
						request.getSession().setAttribute("addedMilestones",addedMilestones);
					
						
					
					}
					
					packet.put("addedMilestones", pegArray);
				
				}
			

		} catch (Exception e) {
			
			e.printStackTrace();
		}

		return packet;
		}
	public JSONObject  updateProgress(HttpServletRequest request) {
		Format format=new Format();
		JSONObject packet =new JSONObject();
		JSONArray pegArray = new JSONArray();
		
		String sessionKey= (String) request.getSession().getAttribute("region");
		String costCenterNo=(String) request.getSession().getAttribute("costCenterNo");
		
		String milestoneId = request.getParameter("milestoneId");
		String remark = request.getParameter("remark");
		String estimateNo = request.getParameter("estimateNo");
		String projectNo = request.getParameter("projectNo");
		String dateOfprogress = request.getParameter("dateOfprogress");
		
		
		
		try {
			ProgressMonitoringEjb progressMonitoringEjb = new ProgressMonitoringEjb(sessionKey); 
			EstimateEjb estimateEjb = new EstimateEjb(sessionKey); 
			milestoneList = (Map<String, Pcmilem>) request.getSession().getAttribute("milestoneList");
			addedMilestones = (Map<String, Pcmiledates>) request.getSession().getAttribute("addedMilestones");
			if(addedMilestones == null ){
				addedMilestones = new HashMap<String, Pcmiledates>();
			}
			
			
			if(milestoneList == null ){
				PcmilemEjb ejb = new PcmilemEjb(sessionKey);
				List <Pcmilem> milestonelist = ejb.getMilestoneList(costCenterNo, sessionKey);
				
					if (milestonelist != null) {

						for (Pcmilem milestone : milestonelist) {
							JSONObject milestoneJson = new JSONObject();

							milestoneJson.put("id", milestone.getId().getMileId());
							milestoneJson.put("name", milestone.getMile_nm() +" - "+milestone.getPercentage());
							milestoneList.put(milestone.getId().getMileId(), milestone);
							
						}

					}
			}
			if(addedMilestones.containsKey(milestoneId.trim())){
				
				
				PcmiledatesEjb dateejb = new PcmiledatesEjb(sessionKey);
				Pcmiledates pcmiledate = dateejb.getMilestoneById(costCenterNo, estimateNo, milestoneId.trim(), sessionKey);
				if(pcmiledate == null  ){
					pcmiledate = dateejb.getMilestoneById(costCenterNo, projectNo, milestoneId.trim(), sessionKey);
				}
				if(pcmiledate != null){
					/*PcmilesumaryEjb summaryejb = new PcmilesumaryEjb(sessionKey);
					Pcmilesumary summary= summaryejb.getMilestoneSummaryId(costCenterNo, estimateNo,milestoneId.trim());
					if(summary != null){
						
						
						if(milestoneId != null && milestoneList != null && milestoneList.size() > 0 && milestoneList.get(milestoneId.trim()) != null){
							summary.setCpercentage(milestoneList.get(milestoneId.trim()).getPercentage());
						}
						if(dateOfprogress != null && !dateOfprogress.equalsIgnoreCase(Constants.DEFAULT_STRING)){
							summary.setDateLast(format.StrToDate(dateOfprogress.substring(0, 10)));
						}
						
					}
					*/
					if(milestoneId != null && milestoneList != null && milestoneList.size() > 0 && milestoneList.get(milestoneId.trim()) != null){
						pcmiledate.setCpercentage(milestoneList.get(milestoneId.trim()).getPercentage());
						pcmiledate.setMileName(milestoneList.get(milestoneId.trim()).getMile_nm());
					}
					if(dateOfprogress != null && !dateOfprogress.equalsIgnoreCase(Constants.DEFAULT_STRING)){
						pcmiledate.getId().setEnterDate(format.StrToDate(dateOfprogress.substring(0, 10)));
					}
					pcmiledate.setRemarks(remark);
					
					dateejb.updatePcmiledates(pcmiledate, sessionKey);
					//progressMonitoringEjb.updateProgress(pcmiledate, null,sessionKey);
				}
			}
			
			
			/*ProgressMonitoringEjb progressMonitoringEjb = new ProgressMonitoringEjb(sessionKey); 
			
			Pcmiledates pcmiledate= new Pcmiledates();
			PcmiledatesPK pcmilemk = new PcmiledatesPK();
			pcmilemk.setDeptId(costCenterNo);
			pcmilemk.setMileId(milestoneId);
			
			if(dateOfprogress != null && !dateOfprogress.equalsIgnoreCase(Constants.DEFAULT_STRING)){
				pcmilemk.setEnterDate(format.StrToDate(dateOfprogress.substring(0, 10)));
			}
			
			if(costCenterNo != null && costCenterNo.equalsIgnoreCase("510.20")){
				pcmilemk.setProjectNumber(projectNo);
			}else{
				pcmilemk.setProjectNumber(estimateNo);
			}
			pcmiledate.setId(pcmilemk);
			*/
			
			
		
		
			
			PcmiledatesEjb miledates = new PcmiledatesEjb(sessionKey);
			List<Pcmiledates> pcmiledates =  miledates.getMilestoneList(costCenterNo, estimateNo.trim(), sessionKey);
			if(pcmiledates == null || pcmiledates.size() ==0){
				pcmiledates =  miledates.getMilestoneList(costCenterNo, projectNo.trim(), sessionKey);
			}
			
			if(pcmiledates != null && pcmiledates.size() != 0){
				
					for(Pcmiledates pcmileda : pcmiledates){
						JSONObject masterDetaRow =new JSONObject();
						masterDetaRow.put("milesToneId", pcmileda.getId().getMileId());
						masterDetaRow.put("remarks", pcmileda.getRemarks());
						masterDetaRow.put("percentage",pcmileda.getCpercentage());
						//masterDetaRow.put("remarks", spPointdmt.getResName());
						masterDetaRow.put("updatedDate",pcmileda.getId().getEnterDate());
						
						
						pegArray.put(masterDetaRow);
						addedMilestones.put(pcmileda.getId().getMileId().trim(), pcmileda);
						request.getSession().setAttribute("addedMilestones",addedMilestones);
					
						
					
					}
					
					packet.put("addedMilestones", pegArray);
				
				}
			

		} catch (Exception e) {
			
			e.printStackTrace();
		}

		return packet;
		}
		
	private void populateProgressSummary(Pcmilesumary sumary){
		sumary = new Pcmilesumary();
		//sumary.setCpercentage(cpercentage);
		//sumary.setDateLast(dateLast);
		//sumary.setStatus(status);
	}
	public JSONObject  searchProgressDetails(HttpServletRequest request) throws JSONException{
		Format format=new Format();
		JSONObject packet =new JSONObject();
		String region= (String) request.getSession().getAttribute("region");
		List<Approval> approvalHistory = null;
		JSONArray pegArray = new JSONArray();
		JSONArray approveHistoryArray = new JSONArray();
		JSONArray pegDetailsRowaray = new JSONArray();
		EstimateDetails detail = new EstimateDetails();
		List<String> estimateNos = new ArrayList<String>();
		try{
			
			String sessionKey= (String) request.getSession().getAttribute("region");
			String applicationNo = request.getParameter("applicationNo");
			String estimateNo = request.getParameter("estimateNo");
			//String projectNo = request.getParameter("projectNo");
			
			String requestCostCenter = request.getParameter("requestCostCenter");
			String costCenterNo=(String) request.getSession().getAttribute("costCenterNo");
			String smcType = request.getParameter("smcType");
			String userRole= (String) request.getSession().getAttribute("userRole");
			
			String fileRef = request.getParameter("fileRef");
			PcesthttEjb pc = new PcesthttEjb(sessionKey);
			String projectNo=null;
			if(requestCostCenter != null && !requestCostCenter.equalsIgnoreCase(Constants.DEFAULT_STRING)){
				costCenterNo = requestCostCenter;
			}
			Pcesthtt pchtt =  pc.findByEstimationNo(estimateNo, costCenterNo,Constants.REV_NO);
			/*if(pchtt == null){
				packet.put("invalid", true);
				packet.put("errorMessage", "Please enter correct number!!");
				
				
			}*/
			PcesthmtEjb hmtejb = new PcesthmtEjb(sessionKey);
			Pcesthmt pchmt =  hmtejb.findByEstimationNo(estimateNo, costCenterNo);
			
			boolean isExistCommeRef = false;
			String applicationRefNo = null;
			EstimateReferenceEjb estejb = new EstimateReferenceEjb();
			EstimateReference refe = estejb.findByWorkEstimateNo(estimateNo.trim(),costCenterNo, sessionKey);
			InmatmEjb matejb = new InmatmEjb(sessionKey);
			EstimateEjb ejb = new EstimateEjb(sessionKey);
			if(refe != null){
				packet.put("stdEstimateNo", refe.getId().getStandardEstimateNo());
		
			}
			if (pchtt!=null){
				
		

				packet.put("fundsou", pchtt.getFundSource().trim());
				packet.put("fundid", pchtt.getFundId().trim());
				packet.put("description", pchtt.getDescr());
				if(pchtt.getCatCd() != null){
					packet.put("categoryId", pchtt.getCatCd().trim());
				}
			
			}
			if(pchmt != null){
				packet.put("projectNo", pchmt.getProjectNo());
				//packet.put("projectNo", "002/2010");
				projectNo =  pchmt.getProjectNo();
				packet.put("fundsou", pchmt.getFundSource().trim());
				packet.put("fundid", pchmt.getFundId().trim());
				packet.put("description", pchmt.getDescr());
				if(pchmt.getCatCd() != null){
					packet.put("categoryId", pchmt.getCatCd().trim());
				}
				packet.put("costCenter", costCenterNo);
	
				
			}
			packet.put("costCenter", costCenterNo);
			
			
			PcmiledatesEjb miledates = new PcmiledatesEjb(sessionKey);
			List<Pcmiledates> pcmiledates =  miledates.getMilestoneList(costCenterNo, estimateNo.trim(), sessionKey);

			
			if(pcmiledates != null && pcmiledates.size() != 0){
				
					for(Pcmiledates pcmiledate : pcmiledates){
						JSONObject masterDetaRow =new JSONObject();
						masterDetaRow.put("milesToneId", pcmiledate.getId().getMileId());
						masterDetaRow.put("remarks", pcmiledate.getRemarks());
						masterDetaRow.put("percentage",pcmiledate.getCpercentage());
						//masterDetaRow.put("remarks", spPointdmt.getResName());
						masterDetaRow.put("updatedDate",pcmiledate.getId().getEnterDate());
						
						
						pegArray.put(masterDetaRow);
						addedMilestones.put(pcmiledate.getId().getMileId().trim(), pcmiledate);
						request.getSession().setAttribute("addedMilestones",addedMilestones);
					
						
					
					}
					
					packet.put("addedMilestones", pegArray);
				
				}
			
			
				
				
		}catch(Exception e){
			
		}
		return packet;

	}
	public JSONObject  searchProgressDetailsJobNumber(HttpServletRequest request) throws JSONException{
		Format format=new Format();
		JSONObject packet =new JSONObject();
		String region= (String) request.getSession().getAttribute("region");
		List<Approval> approvalHistory = null;
		JSONArray pegArray = new JSONArray();
		JSONArray approveHistoryArray = new JSONArray();
		JSONArray pegDetailsRowaray = new JSONArray();
		EstimateDetails detail = new EstimateDetails();
		List<String> estimateNos = new ArrayList<String>();
		try{
			
			String sessionKey= (String) request.getSession().getAttribute("region");
			String applicationNo = request.getParameter("applicationNo");
			//String estimateNo = request.getParameter("estimateNo");
			String projectNo = request.getParameter("projectNo");
			
			String requestCostCenter = request.getParameter("requestCostCenter");
			String costCenterNo=(String) request.getSession().getAttribute("costCenterNo");
			String smcType = request.getParameter("smcType");
			String userRole= (String) request.getSession().getAttribute("userRole");
			
			String fileRef = request.getParameter("fileRef");
			PcesthttEjb pc = new PcesthttEjb(sessionKey);
			
			if(requestCostCenter != null && !requestCostCenter.equalsIgnoreCase(Constants.DEFAULT_STRING)){
				costCenterNo = requestCostCenter;
			}
			
			PcesthmtEjb hmtejb = new PcesthmtEjb(sessionKey);
			Pcesthmt pchmt =  hmtejb.findByJobNo(projectNo, costCenterNo);
			
			boolean isExistCommeRef = false;
			String applicationRefNo = null;
			
			/*if (pchtt!=null){
				
		

				packet.put("fundsou", pchtt.getFundSource().trim());
				packet.put("fundid", pchtt.getFundId().trim());
				packet.put("description", pchtt.getDescr());
				if(pchtt.getCatCd() != null){
					packet.put("categoryId", pchtt.getCatCd().trim());
				}
			
			}*/
			if(pchmt != null){
				EstimateReferenceEjb estejb = new EstimateReferenceEjb();
				EstimateReference refe = estejb.findByWorkEstimateNo(pchmt.getId().getEstimateNo().trim(),costCenterNo, sessionKey);
				InmatmEjb matejb = new InmatmEjb(sessionKey);
				EstimateEjb ejb = new EstimateEjb(sessionKey);
				if(refe != null){
					packet.put("stdEstimateNo", refe.getId().getStandardEstimateNo());
			
				}
				packet.put("projectNo", pchmt.getProjectNo());
				//packet.put("projectNo", "002/2010");
				projectNo =  pchmt.getProjectNo();
				packet.put("fundsou", pchmt.getFundSource().trim());
				packet.put("fundid", pchmt.getFundId().trim());
				packet.put("description", pchmt.getDescr());
				if(pchmt.getCatCd() != null){
					packet.put("categoryId", pchmt.getCatCd().trim());
				}
				packet.put("costCenter", costCenterNo);
	
				
			}
			packet.put("costCenter", costCenterNo);
			
			
			PcmiledatesEjb miledates = new PcmiledatesEjb(sessionKey);
			List<Pcmiledates> pcmiledates =  miledates.getMilestoneList(costCenterNo, projectNo.trim(), sessionKey);

			
			if(pcmiledates != null && pcmiledates.size() != 0){
				
					for(Pcmiledates pcmiledate : pcmiledates){
						JSONObject masterDetaRow =new JSONObject();
						masterDetaRow.put("milesToneId", pcmiledate.getId().getMileId());
						masterDetaRow.put("remarks", pcmiledate.getRemarks());
						masterDetaRow.put("percentage",pcmiledate.getCpercentage());
						//masterDetaRow.put("remarks", spPointdmt.getResName());
						masterDetaRow.put("updatedDate",pcmiledate.getId().getEnterDate());
						
						
						pegArray.put(masterDetaRow);
						addedMilestones.put(pcmiledate.getId().getMileId().trim(), pcmiledate);
						request.getSession().setAttribute("addedMilestones",addedMilestones);
					
						
					
					}
					
					packet.put("addedMilestones", pegArray);
				
				}
			
			
				
				
		}catch(Exception e){
			
		}
		return packet;

	}
	
private BigDecimal getEstQtyBeforeRevise(List<Pcestdtt> dttList,String resCode){
		
		//dttList.
		//System.out.println("hii 1 : ");
		BigDecimal result = new BigDecimal(0);
		//System.out.println("hii 2 : ");
		if(dttList != null && dttList.size() != 0){
			//System.out.println("hii 3 : ");
			for(Pcestdtt dtt : dttList){
				//System.out.println("hii 4 : ");
				//System.out.println("hii x : " + dtt.getId().getResCd().trim().equalsIgnoreCase(resCode.trim()));
				//System.out.println("hii y : " + dtt.getId().getResCd());
				//System.out.println("hii z : " + resCode);
				if(dtt.getId().getResCd().trim().equalsIgnoreCase(resCode.trim())){
					System.out.println("hii 5: " + dtt.getEstimateQty());
					return dtt.getEstimateQty();
					//System.out.println("hii 6: ");
				}else{
					//System.out.println("hii 7: ");
					result = new BigDecimal(0);
					
				}
		
			}
		}
		//System.out.println("hii 9: ");
		return result;
		
		
		
		
	}
			
	
	
}
