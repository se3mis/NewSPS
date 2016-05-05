package presentation.ajax;

import inventory.service.InwrhmtmEjb;

import java.math.BigDecimal;
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

import job.model.Pcrstypm;

import masters.model.BankBranch;
import masters.service.BankEjb;
import masters.service.ProvinceDetailsMasterEjb;

import org.apache.struts2.ServletActionContext;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.sun.pdfview.font.ttf.HmtxTable;

import security.service.SecurityEjb;
import util.common.ApplicationStatus;
import util.common.Constants;
import util.common.EstimateApproval;
import util.common.Format;
import util.common.PivPrefixType;
import util.common.STDEstimateStatus;


import application.model.Applicant;
import application.model.Application;
import application.model.WiringLandDetail;
import application.model.WiringLandDetailCon;
import application.service.ApplicantEjb;
import application.service.ApplicationEjb;
import application.service.WiringLandDetailConEjb;
import application.service.WiringLandDetailEjb;

import costcenter.model.Gldeptm;
import costcenter.service.GldeptmEjb;

import estimate.dto.DetailEstimateDTO;
import estimate.dto.EstimateDetails;
import estimate.dto.Norm;
import estimate.dto.StandardEstimateDetail;
import estimate.model.AllocationSummary;
import estimate.model.AllocationSummaryDisplay;
import estimate.model.Approval;
import estimate.model.FundSource;
import estimate.model.Pcfunddm;
import estimate.model.Pcrsgrpm;
import estimate.model.SpNormsGroup;
import estimate.model.SpPegInfo;
import estimate.model.SpPointdmt;
import estimate.model.Spnorms;
import estimate.model.SpnormsPK;
import estimate.model.Spstdestdmt;
import estimate.model.SpstdestdmtPK;
import estimate.model.Spstdesthmt;
import estimate.model.SpstdesthmtPK;
import estimate.service.ApprovalEjb;
import estimate.service.EstimateEjb;
import estimate.service.SpNormEjb;
import estimate.service.SpestedyEjb;
import estimate.service.SpstdesthmtEjb;

public class SPSAjaxController {
	
	Map<String,Norm> norms = null;
	private Map<String,Norm> normsMap1 = new HashMap<String,Norm>();
	
	Map<String, Norm> normsMap = new TreeMap<String, Norm>();
	private Map<String,Norm> normsMapParent = new HashMap<String,Norm>();
	
	Map<String, Norm> normsMapParent1 = new TreeMap<String, Norm>();


	private  Map<String,Norm> selectedNorms = new HashMap<String,Norm>();
	private  Map<String,Norm> deletedNorms = new HashMap<String,Norm>();
	
	Map<String,Norm> alreadyAddedDetails = new HashMap<String,Norm>();
	Map<String,Norm> newlyAddedDetails = new HashMap<String,Norm>();
	Map<String,Norm> updatedDetails = new HashMap<String,Norm>();
	private Format format;
	StandardEstimateDetail standardEstimateDetail = new StandardEstimateDetail();
	
	private  Map<String,AllocationSummaryDisplay> allocationSummaryDisplayList = new HashMap<String,AllocationSummaryDisplay>();
	private  Map<String,AllocationSummary> allocationSummaryList = new HashMap<String,AllocationSummary>();
	private  Map<String,List<AllocationSummaryDisplay>> allocationSummaryListByCommRef = new HashMap<String,List<AllocationSummaryDisplay>>();
	
	public void clearForm(HttpServletRequest request){
		normsMap = (Map<String,Norm> )request.getSession().getAttribute("normsMap");
		normsMapParent = (Map<String,Norm> )request.getSession().getAttribute("normsMapParent");
		selectedNorms =  (Map<String, Norm>) request.getSession().getAttribute("selectedNorms");
		deletedNorms =  (Map<String, Norm>) request.getSession().getAttribute("deletedNorms");
		alreadyAddedDetails =  (Map<String, Norm>) request.getSession().getAttribute("alreadyAddedDetails");
		newlyAddedDetails =  (Map<String, Norm>) request.getSession().getAttribute("newlyAddedDetails");
		updatedDetails =  (Map<String, Norm>) request.getSession().getAttribute("updatedDetails");
		standardEstimateDetail = (StandardEstimateDetail) request.getSession().getAttribute("standardEstimateDetail");
		
		allocationSummaryDisplayList =  (Map<String, AllocationSummaryDisplay>) request.getSession().getAttribute("allocationSummaryDisplayList");
		allocationSummaryList = (Map<String, AllocationSummary>) request.getSession().getAttribute("allocationSummaryList");
		
		
		if(normsMap != null){
			normsMap.clear();
		}
		if(normsMapParent != null){
			normsMapParent.clear();
		}
		
		if(allocationSummaryDisplayList != null){
			allocationSummaryDisplayList.clear();
		}
		if(allocationSummaryList != null){
			allocationSummaryList.clear();
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
		}
		if(standardEstimateDetail != null){
			standardEstimateDetail = null;
		}
		request.getSession().setAttribute("normsMap",normsMap);
		request.getSession().setAttribute("normsMapParent",normsMapParent);
		request.getSession().setAttribute("selectedNorms",selectedNorms);
		request.getSession().setAttribute("deletedNorms",deletedNorms);
		request.getSession().setAttribute("alreadyAddedDetails",alreadyAddedDetails);
		request.getSession().setAttribute("newlyAddedDetails",newlyAddedDetails);
		request.getSession().setAttribute("updatedDetails",updatedDetails);
		request.getSession().setAttribute("standardEstimateDetail",standardEstimateDetail);
		
		request.getSession().setAttribute("allocationSummaryList",allocationSummaryList);
		
		request.getSession().setAttribute("allocationSummaryDisplayList",allocationSummaryDisplayList);
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
	public void loadNorms(HttpServletRequest request){
		JSONObject packet =new JSONObject();
		JSONArray normsArray = new JSONArray();
		try {
			String sessionKey= (String) request.getSession().getAttribute("region");
			
			EstimateEjb estimateEjb = new EstimateEjb(sessionKey);
			
			List<Spnorms> list = null;
		
			list = estimateEjb.getAvailableNorms();
			normsMap.clear();
			normsMap1.clear();
			for(int i=0; i<=list.size()-1; i++){
	
				 Norm norm = new Norm();
				 norm.setLineSectionTypeId(list.get(i).getId().getLineSectionTypeId());
				 norm.setUom(list.get(i).getUom());
				 norm.setStandardCost(list.get(i).getStandardCost());
				 norm.setDescription(list.get(i).getDescription());
				 
				 normsMap1.put(list.get(i).getId().getLineSectionTypeId(), norm);
				
	             
			}
			normsMap = new TreeMap<String, Norm>(normsMap1);
			selectedNorms =  (Map<String, Norm>) request.getSession().getAttribute("selectedNorms");
			if(selectedNorms != null){
				List<Norm> selectedNormList = new ArrayList<Norm>(selectedNorms.values());
				if(selectedNormList != null){
					for(Norm norm : selectedNormList){
						normsMap.remove(norm.getLineSectionTypeId());
					}
				}
			}

			request.getSession().setAttribute("normsMap",normsMap);
			request.getSession().setAttribute("selectedNorms",selectedNorms);
			
		}catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void loadChildNorms(HttpServletRequest request){
		JSONObject packet =new JSONObject();
		JSONArray normsArray = new JSONArray();
		try {
			String sessionKey= (String) request.getSession().getAttribute("region");
			String parentid= request.getParameter("parentid");
			EstimateEjb estimateEjb = new EstimateEjb(sessionKey);
			
			List<Spnorms> list = null;
		
			list = estimateEjb.getNormsChildrensByParentId(parentid);
			
			normsMapParent.clear();
			normsMapParent1.clear();
			
			normsMap.clear();
			normsMap1.clear();
			if(list != null){
				for(int i=0; i<=list.size()-1; i++){
		
					 Norm norm = new Norm();
					 norm.setLineSectionTypeId(list.get(i).getId().getLineSectionTypeId());
					 norm.setUom(list.get(i).getUom());
					 norm.setStandardCost(list.get(i).getStandardCost());
					 norm.setDescription(list.get(i).getDescription());
					 
					 normsMapParent1.put(list.get(i).getId().getLineSectionTypeId(), norm);
					 normsMap1.put(list.get(i).getId().getLineSectionTypeId(), norm);
					
		             
				}
				normsMapParent = new TreeMap<String, Norm>(normsMapParent1);
				normsMap = new TreeMap<String, Norm>(normsMap1);
				/*selectedNorms =  (Map<String, Norm>) request.getSession().getAttribute("selectedNorms");
				if(selectedNorms != null){
					List<Norm> selectedNormList = new ArrayList<Norm>(selectedNorms.values());
					if(selectedNormList != null){
						for(Norm norm : selectedNormList){
							normsMap.remove(norm.getLineSectionTypeId());
						}
					}
				}
	*/
				request.getSession().setAttribute("normsMap",normsMap);
				request.getSession().setAttribute("normsMapParent",normsMapParent);
			}
			//request.getSession().setAttribute("selectedNorms",selectedNorms);
			
		}catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public JSONObject  showNorms(HttpServletRequest request) throws JSONException {
		NumberFormat nf = NumberFormat.getInstance();
		nf.setGroupingUsed(true);
		nf.setMaximumFractionDigits(2);
		nf.setMinimumFractionDigits(2);
		
		JSONObject packet =new JSONObject();
		JSONArray normsArray = new JSONArray();
		try {
			loadNorms(request);
			//loadParentNorms(request);
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
	
	public JSONObject  showChildNorms(HttpServletRequest request) throws JSONException {
		NumberFormat nf = NumberFormat.getInstance();
		nf.setGroupingUsed(true);
		nf.setMaximumFractionDigits(2);
		nf.setMinimumFractionDigits(2);
		
		JSONObject packet =new JSONObject();
		JSONArray normsArray = new JSONArray();
		try {
			//loadNorms(request);
			loadChildNorms(request);
			normsMapParent = (Map<String,Norm> )request.getSession().getAttribute("normsMapParent");
			if(normsMapParent != null){
				List<Norm> normList = new ArrayList<Norm>(normsMapParent.values());
				
				for(Norm norm :normList ){
	
		            
		           JSONObject normDataRow =new JSONObject();
					 
					normDataRow.put("linesectionid", norm.getLineSectionTypeId());
					normDataRow.put("uom", norm.getUom());
					normDataRow.put("standardcost",nf.format(norm.getStandardCost()));
					normDataRow.put("description", norm.getDescription());
						
					normsArray.put(normDataRow);
		             
				}
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
				if(func.equalsIgnoreCase("add")){
					if(selectedNorms != null && selectedNorms.containsKey(id.trim())){
						packet.put("invalid",true);
						packet.put("errorMessage", "Item "+ id.trim() +" already selected");
						return packet;
					}
				}
				
				normDataRow.put("linesectionid", norm.getLineSectionTypeId());
				normDataRow.put("uom", norm.getUom());
				normDataRow.put("standardcost", norm.getStandardCost());
				normDataRow.put("description", norm.getDescription());
				if(norm.getLineSectionTypeId().contains("OTH") || norm.getLineSectionTypeId().contains("RB")){
					normDataRow.put("OtherCode", true );
				}
				
				//normDataRow.put("OtherCode", norm.getLineSectionTypeId().contains("OTH") );
				
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
			double allTotalCost=populateSum(selectedNorms);
			
			request.getSession().setAttribute("selectedNorms",selectedNorms);
			request.getSession().setAttribute("deletedNorms",deletedNorms);
			request.getSession().setAttribute("newlyAddedDetails",newlyAddedDetails);
			packet.put("totalCost", allTotalCost);
			packet.put("addednorms", normsArray);
			
		} catch (JSONException e) {
			packet.put("error", "Error Occurred");
			e.printStackTrace();
		}

		return packet;

	}
	private double populateSum(Map<String, Norm> normList){
		List<Norm> normDTOList = new ArrayList<Norm>(normList.values());
		//new ArrayList<AllocationSummaryDisplay>(allocationSummaryDisplayList.values());
		double estimateCost = 0;
		if(normDTOList != null){
			
		
			for(Norm dto : normDTOList ){
				if(dto.getEstimatedCost() != null){
					estimateCost = estimateCost + dto.getEstimatedCost().doubleValue();
				}
			}
		}
		return estimateCost;
		
		
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
	
	String node = request.getParameter("node");
	String masterId = request.getParameter("masterId");
	masterId="1";
	if(node.equals("source")){
		node = null;
	}
	return new AjaxResponse(AjaxResponse.TYPE_JSON, setHierarchyLinks(node,masterId,request));

}
public AjaxResponse  loadNormsTree(HttpServletRequest request) throws JSONException{
	
	String node = request.getParameter("node");
	if(node.equals("source")){
		node = null;
	}
	return new AjaxResponse(AjaxResponse.TYPE_JSON, setHierarchyLinksinNorms(node,request));

}
public JSONObject  loadApplicationDetails(HttpServletRequest request) throws JSONException{
	JSONObject packet =new JSONObject();
	NumberFormat nf = NumberFormat.getInstance();
	nf.setGroupingUsed(true);
	nf.setMaximumFractionDigits(2);
	nf.setMinimumFractionDigits(2);
	
	selectedNorms = (Map<String, Norm>) request.getSession().getAttribute("selectedNorms");
	StandardEstimateDetail stdetails = new StandardEstimateDetail();
	JSONArray normsArray = new JSONArray();
	try{
		
		String sessionKey= (String) request.getSession().getAttribute("region");
		String applicationNo = request.getParameter("applicationNo");
		String costCenterNo=(String) request.getSession().getAttribute("costCenterNo");
		String smcType= (String) request.getSession().getAttribute("smcType");
		//String smcType= (String) request.getSession().getAttribute("smcType");
		ApplicationEjb applicationEjb = new ApplicationEjb(sessionKey);
		
		ApplicantEjb applicantEjb = new ApplicantEjb(sessionKey);
		
		WiringLandDetailEjb wiringLandDetailEjb= new WiringLandDetailEjb(sessionKey);
		WiringLandDetailConEjb wiringLandDetailConEjb= new WiringLandDetailConEjb(sessionKey);
		Application application = applicationEjb.findByApplicationNo(applicationNo);
		
		if (application!=null){
			Applicant  applicant=applicantEjb.findById(application.getIdNo());
			if (applicant!=null){
				System.out.println("applicationID" + application.getId().getApplicationId());
				WiringLandDetail  wiringLandDetail=wiringLandDetailEjb.findByApplicationNo(applicationNo);
				WiringLandDetailCon  wiringLandDetailcon=wiringLandDetailConEjb.findByApplicationNo(applicationNo);
				if (wiringLandDetail!=null){
					
					if(smcType.equalsIgnoreCase(Constants.RUTAL_ELECTRIFICATION) || smcType.equalsIgnoreCase(Constants.RUTAL_ELECTRIFICATION_I) ){
						packet.put("powerToSupply", (wiringLandDetailcon.getSchemeName() == null ? wiringLandDetailcon.getSchemeNo()+"" : wiringLandDetailcon.getSchemeName()+""));
					}else{
						packet.put("powerToSupply", wiringLandDetail.getServiceStreetAddress()+" "+wiringLandDetail.getServiceCity());
					}
					packet.put("nameAndAddress", applicant.getFullName() +" "+applicant.getStreetAddress());
					packet.put("demand", wiringLandDetail.getDemand());
					packet.put("applicationId", application.getId().getApplicationId());
					packet.put("foundsource", wiringLandDetailcon.getFundSource());
					if(application.getApplicationType().equalsIgnoreCase(Constants.BS) && wiringLandDetail.getDemand() != null && wiringLandDetail.getDemand().doubleValue() == Constants.BULK_SUPPLY_DEMAND.doubleValue()){
						
						packet.put("disable",Constants.LAND);
						
					}
					else if(application.getApplicationType().equalsIgnoreCase(Constants.LAND) && wiringLandDetail.getDemand() != null && wiringLandDetail.getDemand().doubleValue()  >= Constants.LAND_DEMAND_LOWER_LIMIT.doubleValue() && wiringLandDetail.getDemand().doubleValue()  <= Constants.LAND_DEMAND_UPPER_LIMIT.doubleValue()){
						packet.put("disable", Constants.BULK);
					}
					if(wiringLandDetail.getDemand() != null){
						packet.put("secDeposit", wiringLandDetail.getDemand()*1250.00);
					}else{
						packet.put("secDeposit", "0");
					}
				}
			}
		}
		
		EstimateEjb estimateEjb = new EstimateEjb(sessionKey); 
		
		Spstdesthmt appliDetails = estimateEjb.loadEstimateByApplicationNo(costCenterNo,applicationNo, sessionKey);
		stdetails.setHmt(appliDetails);
		
		if(appliDetails != null){
			packet.put("secDeposit", appliDetails.getSecDeposit());
			packet.put("contigency", appliDetails.getContigency());
			packet.put("sinNo", appliDetails.getSinNo());
			packet.put("sinNo1", appliDetails.getSinNo1());
			packet.put("sinNo2", appliDetails.getSinNo2());
			packet.put("sinNo3", appliDetails.getSinNo3());
			packet.put("sinNo4", appliDetails.getSinNo4());
			if(appliDetails.getVatcost() == null){
				packet.put("vatcost", new Double(0));
			}
			else if(appliDetails.getVatcost().equals("")){
				packet.put("vatcost", new Double(0));
			}else{
				packet.put("vatcost", appliDetails.getVatcost());
			}
			
			
			if(appliDetails.getNbtcost() == null){
				packet.put("nbtcost", new Double(0));
			}
			else if(appliDetails.getNbtcost().equals("")){
				packet.put("nbtcost", new Double(0));
			}else{
				packet.put("nbtcost", appliDetails.getNbtcost());
			}
			//packet.put("vatcost", appliDetails.getVatcost());
			//packet.put("nbtcost", appliDetails.getNbtcost());
			if(appliDetails.getLine_length() == null){
				packet.put("lineLength", new Double(0));
			}
			else if(appliDetails.getLine_length().equals("")){
				packet.put("lineLength", new Double(0));
			}else{
				packet.put("lineLength", appliDetails.getLine_length());
			}
			
			packet.put("totalCost", appliDetails.getTotalCost());
			packet.put("jobDescription", appliDetails.getDescription());
			packet.put("rejectedReason", getRejectedReason(appliDetails,sessionKey));
			packet.put("entryBy", appliDetails.getEntryBy()); 
			packet.put("postDeptId", appliDetails.getPostDeptId());
			packet.put("entryDate", appliDetails.getEntryDate());
			//comment detail
			packet.put("rebateCost", appliDetails.getRebateCost());
			
			packet.put("dgmcomment", appliDetails.getCom_dgm());
			packet.put("cecomcomment", appliDetails.getCom_ce());
			packet.put("cepdcomment", appliDetails.getCom_pce());
			packet.put("eecomcomment", appliDetails.getCom_ee());
			packet.put("eepdcomment", appliDetails.getCom_pe());
			packet.put("escomcomment", appliDetails.getCom_es());
			System.out.println("hihi : " + appliDetails.getCom_es());
			if(appliDetails.getJobName() != null && appliDetails.getJobName().length() > 0){
				packet.put("powerToSupply", appliDetails.getJobName());
			}
			double toconpay = 0;
			if(appliDetails.getTotalCost() != null && appliDetails.getRebateCost() != null){
				toconpay = appliDetails.getTotalCost().doubleValue()-  appliDetails.getRebateCost().doubleValue();
			}
			
			if(appliDetails.getCebCost() != null && appliDetails.getTotalCost() != null){
				toconpay = toconpay -  appliDetails.getCebCost().doubleValue();
			}
			
			double nbtcost = 0;
			if(appliDetails.getNbtcost()!= null && appliDetails.getNbtcost() != null){
				nbtcost = appliDetails.getNbtcost().doubleValue();
			}
			
			double vatcost = 0;
			if(appliDetails.getVatcost()!= null && appliDetails.getVatcost() != null){
				vatcost = appliDetails.getVatcost().doubleValue();
			}
			
			packet.put("totalCostDisplay", "Total Cost :"+nf.format(appliDetails.getTotalCost() != null ? appliDetails.getTotalCost() : new BigDecimal("0")) +" \nCEB Cost :"+nf.format(appliDetails.getCebCost() != null ? appliDetails.getCebCost() :  new BigDecimal("0"))+" \nRebate Cost :"+nf.format(appliDetails.getRebateCost() != null ? appliDetails.getRebateCost() :  new BigDecimal("0"))+" \nNBT COST :"+nf.format(new BigDecimal(nbtcost) != null ? new BigDecimal(nbtcost) :  new BigDecimal("0"))+" \nVAT COST :"+nf.format(new BigDecimal(vatcost) != null ? new BigDecimal(vatcost) :  new BigDecimal("0")) +" \nTotal Consumer Payable Cost :"+nf.format(new BigDecimal(toconpay) != null ? new BigDecimal(toconpay) :  new BigDecimal("0"))   );
			
			STDEstimateStatus status = new STDEstimateStatus();
			;
			packet.put("status", status.setStatus(appliDetails.getStatus()));
			String userRole= (String) request.getSession().getAttribute("userRole");
			if(appliDetails.getTotalCost() != null ){
				packet.put("isApprovable", EstimateApproval.getApprovalStatus(appliDetails.getTotalCost().doubleValue(), userRole));
			}
			
			
			
			
		}
		
		
		
		
		List<Spstdestdmt> normsList = estimateEjb.loadEstimateDetailsByApplicaNo(costCenterNo,applicationNo, sessionKey);
		stdetails.setDmts(normsList);
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


				if(selectedNorms == null){
					selectedNorms = new HashMap<String, Norm>();
				}
				if(alreadyAddedDetails == null){
					alreadyAddedDetails = new HashMap<String, Norm>();
				}
				
				alreadyAddedDetails.put(spstdestdmt.getId().getLineType(), populateNorm(spstdestdmt));
				selectedNorms.put(spstdestdmt.getId().getLineType().trim(), populateNorm(spstdestdmt));

			}
			
		}else{
			packet.put("disableSave", false);
		}
		request.getSession().setAttribute("alreadyAddedDetails",alreadyAddedDetails);
		request.getSession().setAttribute("selectedNorms",selectedNorms);
		request.getSession().setAttribute("standardEstimateDetail",stdetails);
		
		packet.put("addednorms", normsArray);

	}catch(Exception e){
		
	}
	return packet;

}
private  String getRejectedReason(Spstdesthmt hmt,String sessionKey){
	StringBuffer buff = new StringBuffer();
	String rejeReason = null;
	ApprovalEjb eppejb = new ApprovalEjb(sessionKey);
	List<Approval> apprList = eppejb.findByReferenceNo(hmt.getId().getStdNo());
	if(apprList != null ){
		for(Approval app : apprList){
			buff.append(app.getReason() != null ? app.getApprovedLevel()+": " + app.getReason() : "");
			
		
		}
		rejeReason = buff.toString();
	}
	
	
	return rejeReason;
	
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
	
}
public Norm populateSpstdestdmt(Norm norm){

	
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
private JSONArray setHierarchyLinksinNorms(String nodeId,HttpServletRequest request) throws JSONException {
	   
	String sessionKey= (String) request.getSession().getAttribute("region");
	
	EstimateEjb estimateEjb = new EstimateEjb(sessionKey);
	
	List<SpNormsGroup> list = null;

	JSONArray spNormsGroupArray = new JSONArray();
	try {
		if (nodeId == null) {
			list = estimateEjb.getChildrensByParentId(null);
		}else{
			list = estimateEjb.getChildrensByParentId(nodeId);
		}
		if(list != null && list.size() > 0){
			for(SpNormsGroup spNormsGroup :  list){
				
				Node node = new Node(spNormsGroup.getSectionTypeId(),spNormsGroup.getDescription());
				
				List<SpNormsGroup> listChilderns = estimateEjb.getChildrensByParentId(spNormsGroup.getSectionTypeId());
				
				if(listChilderns != null && listChilderns.size() > 0){
					node.setLeaf(false);
				}else{
					node.setLeaf(true);
				}
				
				spNormsGroupArray.put(node.toJson());

			}
		}
	} catch (Exception e) {
		
		e.printStackTrace();
	}
	
    return spNormsGroupArray;

}  
public void getEstimatedCostDetails(HttpServletRequest request){
	
	String quantity = request.getParameter("quantity");
	
	String lineId = request.getParameter("lineid");
	String stdCost = request.getParameter("linecost");
	
	String unitcost = request.getParameter("unitcost");
	selectedNorms =  (Map<String, Norm>) request.getSession().getAttribute("selectedNorms");
	alreadyAddedDetails =  (Map<String, Norm>) request.getSession().getAttribute("alreadyAddedDetails");
	updatedDetails =  (Map<String, Norm>) request.getSession().getAttribute("updatedDetails");
	if(updatedDetails == null){
		updatedDetails = new HashMap<String, Norm>();
	}
	
	Norm addedNorm = selectedNorms.get(lineId);
	if(addedNorm != null){
		double estimateCost = 0.0;
		if(lineId.contains("OTH") || lineId.contains("RB")){
			//20140714gayani
			if(new BigDecimal(stdCost).doubleValue() != 0){
				estimateCost = (new BigDecimal(stdCost).doubleValue()) * new BigDecimal(quantity).doubleValue();
				addedNorm.setStandardCost(new Float(stdCost));
			}else{
				estimateCost = (new BigDecimal(addedNorm.getStandardCost()).doubleValue()) * new BigDecimal(quantity).doubleValue();
				addedNorm.setStandardCost(new Float(addedNorm.getStandardCost()));
			}
			
			
			//estimateCost = (new BigDecimal(addedNorm.getStandardCost()).doubleValue()) * new BigDecimal(quantity).doubleValue();
			//System.out.println("unit cost : " + unitcost + " getstdCost:"+ stdCost + "  quantity : " + quantity  + "  estimateCost : " +estimateCost );
			//System.out.println("addedNorm.getStandardCost() : " + addedNorm.getStandardCost() );
			
			
			//addedNorm.setStandardCost(new Float(addedNorm.getStandardCost()));
		}else{
			estimateCost = new BigDecimal(addedNorm.getStandardCost()).doubleValue() * new BigDecimal(quantity).doubleValue();
			addedNorm.setStandardCost(new Float(addedNorm.getStandardCost()));
		}
				
		addedNorm.setEstimatedCost(new BigDecimal(estimateCost).setScale(2, BigDecimal.ROUND_HALF_UP));
		//addedNorm.setStandardCost(new Float(addedNorm.getStandardCost()));
		addedNorm.setQuantity(new BigDecimal(quantity));
		
		if(alreadyAddedDetails != null && alreadyAddedDetails.containsKey(lineId)){
			
			if(lineId.contains("OTH") || lineId.contains("RB")){
				//20140714gayani
				//System.out.println("lineId " +lineId );
				if(new BigDecimal(stdCost).doubleValue() != 0){
					estimateCost = (new BigDecimal(stdCost).doubleValue())/new BigDecimal(quantity).doubleValue() * new BigDecimal(quantity).doubleValue();
					addedNorm.setStandardCost(new Float(stdCost));
				}else{
					estimateCost = (new BigDecimal(addedNorm.getStandardCost()).doubleValue())/new BigDecimal(quantity).doubleValue() * new BigDecimal(quantity).doubleValue();
					addedNorm.setStandardCost(new Float(addedNorm.getStandardCost()));
				}
				
				
				//estimateCost = (new BigDecimal(addedNorm.getStandardCost()).doubleValue()) * new BigDecimal(quantity).doubleValue();
				//System.out.println("unit cost : " + unitcost + " getstdCost:"+ stdCost + "  quantity : " + quantity  + "  estimateCost : " +estimateCost );
				//System.out.println("addedNorm.getStandardCost() : " + addedNorm.getStandardCost() );
				addedNorm.setEstimatedCost(new BigDecimal(estimateCost).setScale(2, BigDecimal.ROUND_HALF_UP));
				//addedNorm.setStandardCost(new Float(addedNorm.getStandardCost()));
				addedNorm.setQuantity(new BigDecimal(quantity));
				
				//addedNorm.setStandardCost(new Float(addedNorm.getStandardCost()));
			}
			
			
			updatedDetails.put(lineId, addedNorm);
			System.out.println("updatedDetails addedNorm.getStandardCost : " + addedNorm.getStandardCost() + "updatedDetails addedNorm.getEstimatedCost: " + addedNorm.getEstimatedCost() + " "   );
		}
	}
	

	request.getSession().setAttribute("selectedNorms",selectedNorms);
	request.getSession().setAttribute("updatedDetails",updatedDetails);
}

public void getDescriptionDetails(HttpServletRequest request){
	

	String lineId = request.getParameter("lineid");
	String description = request.getParameter("description");
	
	selectedNorms =  (Map<String, Norm>) request.getSession().getAttribute("selectedNorms");
	alreadyAddedDetails =  (Map<String, Norm>) request.getSession().getAttribute("alreadyAddedDetails");
	updatedDetails =  (Map<String, Norm>) request.getSession().getAttribute("updatedDetails");
	if(updatedDetails == null){
		updatedDetails = new HashMap<String, Norm>();
	}
	
	Norm addedNorm = selectedNorms.get(lineId);
	if(addedNorm != null){
		addedNorm.setDescription(description);
		
		if(alreadyAddedDetails != null && alreadyAddedDetails.containsKey(lineId)){
			updatedDetails.put(lineId, addedNorm);
		}
	}
	

	request.getSession().setAttribute("selectedNorms",selectedNorms);
	//request.getSession().setAttribute("updatedDetails",updatedDetails);
}
public JSONObject loadBankBranches(HttpServletRequest request){
	

	String bankCode = request.getParameter("bankCode");
	String sessionKey= (String) request.getSession().getAttribute("region");
	if(bankCode != null){
		
	}
	
	BankEjb bankEjb = new BankEjb(sessionKey);
	List <BankBranch> branchList = bankEjb.getBranchList(bankCode);
	
	JSONArray branchCodes = new JSONArray();

	JSONObject objectlist = new JSONObject();

	
	try {
		if(branchList != null){
		  for (BankBranch branch : branchList) {
			  	JSONObject branchCodesJSON = new JSONObject();
				
			  	branchCodesJSON.put("id", branch.getId().getBranchCode());
			  	branchCodesJSON.put("name", branch.getId().getBranchCode()+"-"+branch.getBranchName());
				
			  	branchCodes.put(branchCodesJSON);
	       }
		  	
	       objectlist.put("branchCodes", branchCodes);
		}
	} catch (JSONException e) {
		
		e.printStackTrace();
	}

	return objectlist;
}
public JSONObject loadSchemaDetails(HttpServletRequest request){
	
	WiringLandDetailCon schemaDetails = null;
	Application application = null;
	Applicant applicant = null;
	String estimateNumber = request.getParameter("estimateNumber");
	String sessionKey= (String) request.getSession().getAttribute("region");
	String costCenterNo=(String) request.getSession().getAttribute("costCenterNo");
	JSONObject objectlist = new JSONObject();
	SpstdesthmtEjb ejb = new SpstdesthmtEjb(sessionKey);
	
	if(estimateNumber.equalsIgnoreCase("-1")|| estimateNumber != null){
		
	
	

	WiringLandDetailConEjb wiringLandDetailConEjb = new WiringLandDetailConEjb(sessionKey);
	schemaDetails = wiringLandDetailConEjb.findByApplicationNo(estimateNumber);
	
	ApplicationEjb applicantionEjbejb = new ApplicationEjb(sessionKey);
	ApplicantEjb applicantEjbejb = new ApplicantEjb(sessionKey);
	application = applicantionEjbejb.findByApplicationNo(estimateNumber);
	SpstdesthmtPK hmt = new SpstdesthmtPK();
	
	String commercialDeptid=null;
	String commercialId= costCenterNo.substring(0, 3);
	if(commercialId != null){
		commercialDeptid = commercialId.concat(".00");

	}
	hmt.setApplicationNo(estimateNumber);
	hmt.setStdNo(estimateNumber);
	hmt.setDeptId(commercialDeptid);
	Spstdesthmt hmtstd = ejb.findById(hmt, sessionKey);
		if(application != null){
			applicant = applicantEjbejb.findById(application.getIdNo());
			try {
				if(application != null && application.getDescription() != null ){
					objectlist.put("description", application.getDescription());
				}else if (hmtstd != null && hmtstd.getDescription() != null){
					objectlist.put("description",  hmtstd.getDescription());
				}
				
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	try {
		if(applicant!= null ){
		
	
		  	objectlist.put("proposerName", applicant.getFullName() != null ? applicant.getFullName() : Constants.DEFAULT_STRING);

		}
		if(schemaDetails != null){
			objectlist.put("electorate", schemaDetails.getElectorate() != null ?  schemaDetails.getElectorate() : Constants.DEFAULT_STRING);
			objectlist.put("schemaExtention", schemaDetails.getSchemeNo() !=null ? schemaDetails.getSchemeNo() : Constants.DEFAULT_STRING);
		  	objectlist.put("schemaName", schemaDetails.getSchemeName() != null ? schemaDetails.getSchemeName() : Constants.DEFAULT_STRING);
		  	objectlist.put("area", schemaDetails.getAreaCode() != null ?  schemaDetails.getAreaCode() : Constants.DEFAULT_STRING);
			objectlist.put("servicedeponame", schemaDetails.getServiceDepoName() != null ? schemaDetails.getServiceDepoName() : Constants.DEFAULT_STRING);
		  	objectlist.put("representative", schemaDetails.getRepresentative() != null ? schemaDetails.getRepresentative() :Constants.DEFAULT_STRING );
		  	objectlist.put("repContact", schemaDetails.getRepContact1() != null ? schemaDetails.getRepContact1() :  Constants.DEFAULT_STRING );
		}
		
		
	} catch (JSONException e) {
		
		e.printStackTrace();
	}

	return objectlist;
}
public JSONObject loadLineSummaryDetails(HttpServletRequest request){
	
	String estimateNumber = request.getParameter("estimateNumber");
	String sessionKey= (String) request.getSession().getAttribute("region");
	String costCenterNo=(String) request.getSession().getAttribute("costCenterNo");
	JSONArray normsArray = new JSONArray();
	JSONObject packet =new JSONObject();
	
	allocationSummaryDisplayList =  (Map<String, AllocationSummaryDisplay>) request.getSession().getAttribute("allocationSummaryDisplayList");
	allocationSummaryList = (Map<String, AllocationSummary>) request.getSession().getAttribute("allocationSummaryList");
	allocationSummaryListByCommRef =  (Map<String, List<AllocationSummaryDisplay>>) request.getSession().getAttribute("allocationSummaryListByCommRef");
	
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
		List<AllocationSummaryDisplay>  allosummary =null;
		EstimateEjb estimateEjb = new EstimateEjb(sessionKey);
		if(allocationSummaryListByCommRef !=null && allocationSummaryListByCommRef.size() > 0 && allocationSummaryListByCommRef.get(estimateNumber.trim()) != null){
			allosummary = allocationSummaryListByCommRef.get(estimateNumber.trim());
		}else{
			String costCenter = costCenterNo.substring(0, 3);
			String commCost = costCenter.concat(".00");
		    allosummary = estimateEjb.findEstimateSummary(estimateNumber,commCost);
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
					normDataRow.put("description", display.getDescription() );
					normDataRow.put("alloactedLength", display.getAllocatedLineLength() );
					normDataRow.put("allocatedCost", display.getAllocatedEstimatedCost());
					allocationSummaryDisplayList.put(display.getLineId(), display);
					normsArray.put(normDataRow);
				}
				packet.put("summary", normsArray);
				request.getSession().setAttribute("allocationSummaryDisplayList",allocationSummaryDisplayList);
			}
			
		
	}catch (Exception e) {
		// TODO: handle exception
	}
	
	return packet;
}
/*public JSONObject loadLineSummaryDetailsInCache(HttpServletRequest request){
	
	String estimateNumber = request.getParameter("estimateNumber");
	String sessionKey= (String) request.getSession().getAttribute("region");
	String costCenterNo=(String) request.getSession().getAttribute("costCenterNo");
	JSONArray normsArray = new JSONArray();
	JSONObject packet =new JSONObject();
	
	allocationSummaryDisplayList =  (Map<String, AllocationSummaryDisplay>) request.getSession().getAttribute("allocationSummaryDisplayList");
	allocationSummaryList = (Map<String, AllocationSummary>) request.getSession().getAttribute("allocationSummaryList");
	
	
	if(allocationSummaryDisplayList == null){
		allocationSummaryDisplayList = new HashMap<String, AllocationSummaryDisplay>();
	}
	if(allocationSummaryList == null){
		allocationSummaryList = new HashMap<String, AllocationSummary>();
	}
	try{
		EstimateEjb estimateEjb = new EstimateEjb(sessionKey);
		//List<AllocationSummaryDisplay>  allosummary = allocationSummaryDisplayList.g
		List<AllocationSummaryDisplay> allosummary = new ArrayList<AllocationSummaryDisplay>(allocationSummaryDisplayList.values());
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
					normDataRow.put("remainLength",display.getRemainingLineLength());
					normDataRow.put("totalestiamtecost", display.getTotalEstimatedCost());
					normDataRow.put("estiamtedate", display.getEstimateDate() );
					normDataRow.put("description", display.getDescription() );
					normDataRow.put("alloactedLength", display.getAllocatedLineLength() );
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
}*/
public void getAllocatedLengthDetails(HttpServletRequest request){
	
	String quantity = request.getParameter("quantity");
	
	String lineId = request.getParameter("lineid");
	
	
	allocationSummaryDisplayList =  (Map<String, AllocationSummaryDisplay>) request.getSession().getAttribute("allocationSummaryDisplayList");
	if(quantity != null && !quantity.equalsIgnoreCase(Constants.DEFAULT_STRING)){
		
	
		if(allocationSummaryDisplayList != null){
			
		
			AllocationSummaryDisplay allocationSummaryDisplay = allocationSummaryDisplayList.get(lineId);
			if(allocationSummaryDisplay != null){
				
				allocationSummaryDisplay.setAllocatedLineLength(new BigDecimal(quantity));
				allocationSummaryDisplay.setRemainingLineLength(new BigDecimal(allocationSummaryDisplay.getTotalLineLength().doubleValue() - new BigDecimal(quantity).doubleValue()));
				
				
				
			}
		}
	}
	

	request.getSession().setAttribute("allocationSummaryDisplayList",allocationSummaryDisplayList);
	//request.getSession().setAttribute("updatedDetails",updatedDetails);
}
public JSONObject deleteLineDetails(HttpServletRequest request){
	JSONObject packet =new JSONObject();
	String quantity = request.getParameter("quantity");
	String applicationRefNo = request.getParameter("applicationRefNo");
	String costCenterNo = (String) request.getSession().getAttribute("costCenterNo");
	String lineId = request.getParameter("lineid");
	String estimatedCost = request.getParameter("linecost");
	
	//deletedNorms =  (Map<String, Norm>) request.getSession().getAttribute("deletedNorms");
	selectedNorms =  (Map<String, Norm>) request.getSession().getAttribute("selectedNorms");
	
	alreadyAddedDetails =  (Map<String, Norm>) request.getSession().getAttribute("alreadyAddedDetails");
	updatedDetails =  (Map<String, Norm>) request.getSession().getAttribute("updatedDetails");
	if(updatedDetails == null){
		updatedDetails = new HashMap<String, Norm>();
	}
	
	String sessionKey= (String) request.getSession().getAttribute("region");
	EstimateEjb estimateEjb = new EstimateEjb(sessionKey); 
	System.out.println("###########1" + lineId);
	Norm deletedNorm = selectedNorms.get(lineId);
	
	if(deletedNorm != null){
		System.out.println("#############################################7" + lineId);
		Spstdestdmt spstdestdmt = new Spstdestdmt();
		SpstdestdmtPK id = new SpstdestdmtPK();
		id.setApplicationNo(applicationRefNo);
		id.setLineType(lineId);
		id.setStdNo(applicationRefNo);
		spstdestdmt.setId(id);
		estimateEjb.removeLineDetails(spstdestdmt, sessionKey);
		System.out.println("costCenterNo" + costCenterNo);
		System.out.println("applicationRefNo" + applicationRefNo);
		try{
			estimateEjb.updateStandardEstimateCost(applicationRefNo, costCenterNo, sessionKey);
		}catch(Exception e){
			System.out.println("Error" + e);
		}
		selectedNorms.remove(lineId);
		if(alreadyAddedDetails != null && alreadyAddedDetails.containsKey(lineId)){
			updatedDetails.remove(lineId);
			alreadyAddedDetails.remove(lineId);
		}
	}else{
		Norm deletedNorm2 = alreadyAddedDetails.get(lineId);
		if(deletedNorm2 != null){
			System.out.println("##alreadyAddedDetails####: DDD" + deletedNorm);
			System.out.println("########alreadyAddedDetails" + lineId);
			Spstdestdmt spstdestdmt = new Spstdestdmt();
			SpstdestdmtPK id = new SpstdestdmtPK();
			id.setApplicationNo(applicationRefNo);
			id.setLineType(lineId);
			id.setStdNo(applicationRefNo);
			spstdestdmt.setId(id);
			estimateEjb.removeLineDetails(spstdestdmt, sessionKey);
			System.out.println("costCenterNo" + costCenterNo);
			System.out.println("applicationRefNo" + applicationRefNo);
			try{
				estimateEjb.updateStandardEstimateCost(applicationRefNo, costCenterNo, sessionKey);
			}catch(Exception e){
				System.out.println("Error" + e);
			}
			//selectedNorms.remove(lineId);
			if(alreadyAddedDetails != null && alreadyAddedDetails.containsKey(lineId)){
				updatedDetails.remove(lineId);
				alreadyAddedDetails.remove(lineId);
			}
		}
		
	}
	//Norm deletedNorm = deletedNorms.get(lineId);
	//if(deletedNorm != null){
		
	//}
		
		
		

		request.getSession().setAttribute("selectedNorms",selectedNorms);
		request.getSession().setAttribute("updatedDetails",updatedDetails);
		request.getSession().setAttribute("alreadyAddedDetails",alreadyAddedDetails);
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
@SuppressWarnings("null")
public JSONObject setAddSubstationCost(HttpServletRequest request){
	System.out.println("hi hi hi hi hi ");
	JSONObject packet =new JSONObject();
	String demand = request.getParameter("demand");
	
	String applicationNo = request.getParameter("applicationNo");
	String lineLegth = request.getParameter("lineLength");
	
	String sessionKey= (String) request.getSession().getAttribute("region");
	String costCenterNo=(String) request.getSession().getAttribute("costCenterNo");
	String isUnable = request.getParameter("isUnable");
	String func = request.getParameter("func");
	selectedNorms =  (Map<String, Norm>) request.getSession().getAttribute("selectedNorms");
	newlyAddedDetails =  (Map<String, Norm>) request.getSession().getAttribute("newlyAddedDetails");
	if(selectedNorms == null){
		selectedNorms = new HashMap<String, Norm>();
	}
	if(newlyAddedDetails == null){
		newlyAddedDetails = new HashMap<String, Norm>();
	}
	
	//String isAddSub = request.getParameter("isAddSub");
	try {
		ApplicationEjb applicationEjb = new ApplicationEjb(sessionKey);
		if(applicationNo != null){
			System.out.println("hiiix : "+isUnable + "linel : "+lineLegth);
			Application application = applicationEjb.findByApplicationNo(applicationNo);
			Double demandValue = Double.parseDouble(demand);
			Double lineLengthValue = Double.parseDouble(lineLegth);
			System.out.println("hiiifunc : "+func);
			System.out.println("hiii : "+isUnable + "line : " + lineLengthValue);
			if(isUnable != null && isUnable.equalsIgnoreCase("true")){
				if(application.getApplicationType().equalsIgnoreCase(Constants.BS) && demandValue.doubleValue() <= Constants.PH95_TYPE.doubleValue()){
					
					packet.put("disable",Constants.LAND);
					
				}
				else if(application.getApplicationType().equalsIgnoreCase(Constants.LAND) && demandValue.doubleValue()  >= Constants.LAND_DEMAND_LOWER_LIMIT.doubleValue() && demandValue.doubleValue()  <= Constants.LAND_DEMAND_UPPER_LIMIT.doubleValue()){
					packet.put("disable", Constants.BULK);
				}
			}
			
			if(func != null && func.equalsIgnoreCase("add")){
				boolean status= false;
				JSONArray normsArray = new JSONArray();
				if(application.getApplicationType().equalsIgnoreCase(Constants.BS) && demandValue.doubleValue() <= Constants.BULK_SUPPLY_DEMAND.doubleValue() && lineLengthValue <= 200){
					System.out.println("hiii kkkkk");
					if(lineLengthValue <= 50){
						//Only Fixed Cost
						Norm normFixedCost = populateFixedCost( sessionKey, costCenterNo, application.getApplicationType(), demandValue);
						if(normFixedCost != null && normFixedCost.getEstimatedCost() != null && normFixedCost.getEstimatedCost().doubleValue() != 0.0){
							if(selectedNorms.get(normFixedCost.getLineSectionTypeId()) != null && selectedNorms.containsKey(normFixedCost.getLineSectionTypeId())){
								
							}else{
								selectedNorms.put(normFixedCost.getLineSectionTypeId(), normFixedCost);
								newlyAddedDetails.put(Constants.CEB_COST_CODE+normFixedCost.getLineSectionTypeId(), normFixedCost);
							
							JSONObject normDataRow =new JSONObject();
							
							normDataRow.put("linesectionid", normFixedCost.getLineSectionTypeId());
							normDataRow.put("uom", normFixedCost.getUom());
							normDataRow.put("standardcost", normFixedCost.getEstimatedCost());
							normDataRow.put("estimatecost", normFixedCost.getEstimatedCost());
							
							normDataRow.put("description", normFixedCost.getDescription());
							normsArray.put(normDataRow);
						
						}
						}
						
					}else{
						double preLegnth = lineLengthValue - 50;
						
						Norm normFixedCost = populateFixedCost( sessionKey, costCenterNo, application.getApplicationType(), demandValue);
						if(normFixedCost != null && normFixedCost.getEstimatedCost() != null && normFixedCost.getEstimatedCost().doubleValue() != 0.0){
							if(selectedNorms.get(normFixedCost.getLineSectionTypeId()) != null && selectedNorms.containsKey(normFixedCost.getLineSectionTypeId())){
								
							}else{
								selectedNorms.put(normFixedCost.getLineSectionTypeId(), normFixedCost);
								newlyAddedDetails.put(Constants.CEB_COST_CODE+normFixedCost.getLineSectionTypeId(), normFixedCost);
							
							JSONObject normDataRow =new JSONObject();
							
							normDataRow.put("linesectionid", normFixedCost.getLineSectionTypeId());
							normDataRow.put("uom", normFixedCost.getUom());
							normDataRow.put("standardcost", normFixedCost.getEstimatedCost());
							normDataRow.put("estimatecost", normFixedCost.getEstimatedCost());
							
							normDataRow.put("description", normFixedCost.getDescription());
							normsArray.put(normDataRow);
						
						}
						}
						
						Norm normVCost = populateVCOST( sessionKey, costCenterNo, application.getApplicationType(), preLegnth);
						if(normVCost != null && normVCost.getEstimatedCost() != null && normVCost.getEstimatedCost().doubleValue() != 0.0){
							if(selectedNorms.get(normVCost.getLineSectionTypeId()) != null && selectedNorms.containsKey(normVCost.getLineSectionTypeId())){
								
							}else{
								selectedNorms.put(normVCost.getLineSectionTypeId(), normVCost);
								newlyAddedDetails.put(Constants.CEB_COST_CODE+normVCost.getLineSectionTypeId(), normVCost);
							
							JSONObject normDataRow =new JSONObject();
							
							normDataRow.put("linesectionid", normVCost.getLineSectionTypeId());
							normDataRow.put("uom", normVCost.getUom());
							normDataRow.put("standardcost", normVCost.getStandardCost());
							normDataRow.put("estimatecost", normVCost.getEstimatedCost());
							normDataRow.put("quantity", normVCost.getQuantity());
							normDataRow.put("description", normVCost.getDescription());
							normsArray.put(normDataRow);
						
						}
						}
						
					}
					
					
					
				}else if(application.getApplicationType().equalsIgnoreCase(Constants.BS) && demandValue.doubleValue() > Constants.BULK_SUPPLY_DEMAND.doubleValue() && demandValue.doubleValue() <= 75 && lineLengthValue <= 100){
					
					if(lineLengthValue <= 50){
						//Only Fixed Cost
						Norm normFixedCost = populateFixedCost75( sessionKey, costCenterNo, application.getApplicationType(), demandValue);
						if(normFixedCost != null && normFixedCost.getEstimatedCost() != null && normFixedCost.getEstimatedCost().doubleValue() != 0.0){
							if(selectedNorms.get(normFixedCost.getLineSectionTypeId()) != null && selectedNorms.containsKey(normFixedCost.getLineSectionTypeId())){
								
							}else{
								selectedNorms.put(normFixedCost.getLineSectionTypeId(), normFixedCost);
								newlyAddedDetails.put(Constants.CEB_COST_CODE+normFixedCost.getLineSectionTypeId(), normFixedCost);
							
							JSONObject normDataRow =new JSONObject();
							
							normDataRow.put("linesectionid", normFixedCost.getLineSectionTypeId());
							normDataRow.put("uom", normFixedCost.getUom());
							normDataRow.put("standardcost", normFixedCost.getEstimatedCost());
							normDataRow.put("estimatecost", normFixedCost.getEstimatedCost());
							
							normDataRow.put("description", normFixedCost.getDescription());
							normsArray.put(normDataRow);
						
						}
						}
						
					}else{
						double preLegnth = lineLengthValue - 50;
						
						Norm normFixedCost = populateFixedCost75( sessionKey, costCenterNo, application.getApplicationType(), demandValue);
						if(normFixedCost != null && normFixedCost.getEstimatedCost() != null && normFixedCost.getEstimatedCost().doubleValue() != 0.0){
							if(selectedNorms.get(normFixedCost.getLineSectionTypeId()) != null && selectedNorms.containsKey(normFixedCost.getLineSectionTypeId())){
								
							}else{
								selectedNorms.put(normFixedCost.getLineSectionTypeId(), normFixedCost);
								newlyAddedDetails.put(Constants.CEB_COST_CODE+normFixedCost.getLineSectionTypeId(), normFixedCost);
							
							JSONObject normDataRow =new JSONObject();
							
							normDataRow.put("linesectionid", normFixedCost.getLineSectionTypeId());
							normDataRow.put("uom", normFixedCost.getUom());
							normDataRow.put("standardcost", normFixedCost.getEstimatedCost());
							normDataRow.put("estimatecost", normFixedCost.getEstimatedCost());
							
							normDataRow.put("description", normFixedCost.getDescription());
							normsArray.put(normDataRow);
						
						}
						}
						
						Norm normVCost = populateVCOST( sessionKey, costCenterNo, application.getApplicationType(), preLegnth);
						if(normVCost != null && normVCost.getEstimatedCost() != null && normVCost.getEstimatedCost().doubleValue() != 0.0){
							if(selectedNorms.get(normVCost.getLineSectionTypeId()) != null && selectedNorms.containsKey(normVCost.getLineSectionTypeId())){
								
							}else{
								selectedNorms.put(normVCost.getLineSectionTypeId(), normVCost);
								newlyAddedDetails.put(Constants.CEB_COST_CODE+normVCost.getLineSectionTypeId(), normVCost);
							
							JSONObject normDataRow =new JSONObject();
							
							normDataRow.put("linesectionid", normVCost.getLineSectionTypeId());
							normDataRow.put("uom", normVCost.getUom());
							normDataRow.put("standardcost", normVCost.getStandardCost());
							normDataRow.put("estimatecost", normVCost.getEstimatedCost());
							normDataRow.put("quantity", normVCost.getQuantity());
							normDataRow.put("description", normVCost.getDescription());
							normsArray.put(normDataRow);
						
						}
						}
						
					}
				
				
				}else if(application.getApplicationType().equalsIgnoreCase(Constants.BS) && demandValue.doubleValue() > 75 && demandValue.doubleValue() <= 95 && lineLengthValue <= 100){
					if(lineLengthValue <= 50){
						//Only Fixed Cost
						Norm normFixedCost = populateFixedCost95( sessionKey, costCenterNo, application.getApplicationType(), demandValue);
						if(normFixedCost != null && normFixedCost.getEstimatedCost() != null && normFixedCost.getEstimatedCost().doubleValue() != 0.0){
							if(selectedNorms.get(normFixedCost.getLineSectionTypeId()) != null && selectedNorms.containsKey(normFixedCost.getLineSectionTypeId())){
								
							}else{
								selectedNorms.put(normFixedCost.getLineSectionTypeId(), normFixedCost);
								newlyAddedDetails.put(Constants.CEB_COST_CODE+normFixedCost.getLineSectionTypeId(), normFixedCost);
							
							JSONObject normDataRow =new JSONObject();
							
							normDataRow.put("linesectionid", normFixedCost.getLineSectionTypeId());
							normDataRow.put("uom", normFixedCost.getUom());
							normDataRow.put("standardcost", normFixedCost.getEstimatedCost());
							normDataRow.put("estimatecost", normFixedCost.getEstimatedCost());
							
							normDataRow.put("description", normFixedCost.getDescription());
							normsArray.put(normDataRow);
						
						}
						}
						
					}else{
						double preLegnth = lineLengthValue - 50;
						
						Norm normFixedCost = populateFixedCost95( sessionKey, costCenterNo, application.getApplicationType(), demandValue);
						if(normFixedCost != null && normFixedCost.getEstimatedCost() != null && normFixedCost.getEstimatedCost().doubleValue() != 0.0){
							if(selectedNorms.get(normFixedCost.getLineSectionTypeId()) != null && selectedNorms.containsKey(normFixedCost.getLineSectionTypeId())){
								
							}else{
								selectedNorms.put(normFixedCost.getLineSectionTypeId(), normFixedCost);
								newlyAddedDetails.put(Constants.CEB_COST_CODE+normFixedCost.getLineSectionTypeId(), normFixedCost);
							
							JSONObject normDataRow =new JSONObject();
							
							normDataRow.put("linesectionid", normFixedCost.getLineSectionTypeId());
							normDataRow.put("uom", normFixedCost.getUom());
							normDataRow.put("standardcost", normFixedCost.getStandardCost());
							normDataRow.put("estimatecost", normFixedCost.getEstimatedCost());
							
							normDataRow.put("description", normFixedCost.getDescription());
							normsArray.put(normDataRow);
						
						}
						}
						
						Norm normVCost = populateVCOST( sessionKey, costCenterNo, application.getApplicationType(), preLegnth);
						if(normVCost != null && normVCost.getEstimatedCost() != null && normVCost.getEstimatedCost().doubleValue() != 0.0){
							if(selectedNorms.get(normVCost.getLineSectionTypeId()) != null && selectedNorms.containsKey(normVCost.getLineSectionTypeId())){
								
							}else{
								selectedNorms.put(normVCost.getLineSectionTypeId(), normVCost);
								newlyAddedDetails.put(Constants.CEB_COST_CODE+normVCost.getLineSectionTypeId(), normVCost);
							
							JSONObject normDataRow =new JSONObject();
							
							normDataRow.put("linesectionid", normVCost.getLineSectionTypeId());
							normDataRow.put("uom", normVCost.getUom());
							normDataRow.put("standardcost", normVCost.getStandardCost());
							normDataRow.put("estimatecost", normVCost.getEstimatedCost());
							normDataRow.put("quantity", normVCost.getQuantity());
							normDataRow.put("description", normVCost.getDescription());
							normsArray.put(normDataRow);
							}
						
						}
						
					}
				
				
				}
				
				else{
					
					System.out.println("hiiielse");
					/*if(selectedNorms != null && selectedNorms.containsKey(Constants.CEB_COST_CODE_FOR_SUBSTATION)){
						packet.put("invalid",true);
						packet.put("errorMessage",  Constants.CEB_COST_CODE_FOR_SUBSTATION +" - "+Constants.CEB_COST_FOR_SUBSTATION+" entry already added");
						status = true;
					}
					if(selectedNorms != null && selectedNorms.containsKey(Constants.CONSUMER_COST_FOR_SUBSTATION)){
						packet.put("invalid",true);
						packet.put("errorMessage",  Constants.CONSUMER_COST_CODE_FOR_SUBSTATION +" - "+Constants.CEB_COST_FOR_SUBSTATION+" entry already added");
						status = true;
						//return packet;
					}if(selectedNorms != null && selectedNorms.containsKey(Constants.CEB_COST_FOR_LINE)){
						packet.put("invalid",true);
						packet.put("errorMessage",  Constants.CEB_COST_FOR_LINE +" entry already added");
						status = true;
						//return packet;
					}
					if(status){
						return packet;
					}*/
					
					Norm normCEB = populateCEBCost( sessionKey, costCenterNo, application.getApplicationType(), demandValue);
					System.out.println("hiiielse2: "+normCEB + " 1 : "+normCEB.getEstimatedCost() + "  2 : " + normCEB.getLineSectionTypeId());
					
					
					if(normCEB != null && normCEB.getEstimatedCost() != null && normCEB.getEstimatedCost().doubleValue() != 0.0){
						if(selectedNorms.get(normCEB.getLineSectionTypeId()) != null && selectedNorms.containsKey(normCEB.getLineSectionTypeId())){
							
						}else{
							System.out.println("hiiielse1");
							selectedNorms.put(Constants.CEB_COST_CODE, normCEB);
							newlyAddedDetails.put(Constants.CEB_COST_CODE, normCEB);
							JSONObject normDataRow =new JSONObject();
							
							normDataRow.put("linesectionid", normCEB.getLineSectionTypeId());
							normDataRow.put("uom", normCEB.getUom());
							normDataRow.put("standardcost", normCEB.getEstimatedCost());
							normDataRow.put("estimatecost", normCEB.getEstimatedCost());
							normDataRow.put("description", normCEB.getDescription());
							normsArray.put(normDataRow);
						}
						
					}
					
					System.out.println("hiiielse2");
					Norm normConumerCost = populateConsumerCost( sessionKey, costCenterNo, application.getApplicationType(), demandValue);
					
					System.out.println("hiiielse2: "+normConumerCost + " 1 : "+normConumerCost.getEstimatedCost() + "  2 : " + normConumerCost.getLineSectionTypeId());
					if(normConumerCost != null && normConumerCost.getEstimatedCost() != null && normConumerCost.getEstimatedCost().doubleValue() != 0.0){
						if(selectedNorms.get(normConumerCost.getLineSectionTypeId()) != null && selectedNorms.containsKey(normConumerCost.getLineSectionTypeId())){
							
						}else{
							System.out.println("hiiielse3");
							selectedNorms.put(Constants.CONSUMER_COST_CODE_FOR_SUBSTATION, normConumerCost);
							newlyAddedDetails.put(Constants.CONSUMER_COST_CODE_FOR_SUBSTATION, normConumerCost);
							selectedNorms.remove(Constants.LINE_NORM_ID_33KV_63kVA_SUBST);
							newlyAddedDetails.remove(Constants.LINE_NORM_ID_33KV_63kVA_SUBST);
							JSONObject normDataRow =new JSONObject();
							
							normDataRow.put("linesectionid", normConumerCost.getLineSectionTypeId());
							normDataRow.put("uom", normConumerCost.getUom());
							normDataRow.put("standardcost", normConumerCost.getEstimatedCost());
							normDataRow.put("estimatecost", normConumerCost.getEstimatedCost());
							
							normDataRow.put("description", normConumerCost.getDescription());
							normsArray.put(normDataRow);
						}
						
					}
					
					
					
					System.out.println("hiiielse4");
					
					Norm normLineCEBCost = populateLineCostCEBANDConsumer(  costCenterNo, application.getApplicationType(), demandValue,selectedNorms,newlyAddedDetails, request);
					if(normLineCEBCost != null && normLineCEBCost.getEstimatedCost() != null && normLineCEBCost.getEstimatedCost().doubleValue() != 0.0){
						if(selectedNorms.get(normLineCEBCost.getLineSectionTypeId()) != null && selectedNorms.containsKey(normLineCEBCost.getLineSectionTypeId())){
							
						}else{
							System.out.println("hiiielse5");
							selectedNorms.put(normLineCEBCost.getLineSectionTypeId(), normLineCEBCost);
							newlyAddedDetails.put(Constants.CEB_COST_CODE+normLineCEBCost.getLineSectionTypeId(), normLineCEBCost);
							
							JSONObject normDataRow =new JSONObject();
							
							normDataRow.put("linesectionid", normLineCEBCost.getLineSectionTypeId());
							normDataRow.put("uom", normLineCEBCost.getUom());
							normDataRow.put("standardcost", normLineCEBCost.getEstimatedCost());
							normDataRow.put("estimatecost", normLineCEBCost.getEstimatedCost());
							
							normDataRow.put("description", normLineCEBCost.getDescription());
							normsArray.put(normDataRow);
						}
						
					}
					packet.put("addednorms", normsArray);
					//packet.put("disable", "all");
					request.getSession().setAttribute("selectedNorms",selectedNorms);
					//request.getSession().setAttribute("deletedNorms",deletedNorms);
					request.getSession().setAttribute("newlyAddedDetails",newlyAddedDetails);
					
			}
				packet.put("addednorms", normsArray);
				//packet.put("disable", "all");
				request.getSession().setAttribute("selectedNorms",selectedNorms);
				//request.getSession().setAttribute("deletedNorms",deletedNorms);
				request.getSession().setAttribute("newlyAddedDetails",newlyAddedDetails);
	
			}/*else if(func.equalsIgnoreCase("delete")){
					newlyAddedDetails.remove(id);
					selectedNorms.remove(id);
					deletedNorms.put(id, norm);
			}*/
		}
		
	
	} catch (JSONException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return packet;
}
private Norm populateCEBCost(String sessionKey,String costCenterNo,String applicationType,Double demandValue){
	SpNormEjb ejb = new SpNormEjb();
	SpnormsPK pk100KVA = new SpnormsPK();
	pk100KVA.setLineSectionTypeId(Constants.LINE_NORM_ID_33KV_100kVA_SUBST);
	
	Spnorms norm100KVA = ejb.findById(pk100KVA, sessionKey);

	SpnormsPK pk63KVA = new SpnormsPK();
	pk63KVA.setLineSectionTypeId(Constants.LINE_NORM_ID_33KV_63kVA_SUBST);
	
	Spnorms norm63KVA = ejb.findById(pk63KVA, sessionKey);
	
	double cebCost = 0.0;
	//BigDecimal consumerCost = new BigDecimal("0");
	if(costCenterNo.equalsIgnoreCase("000.00")){
		if(norm100KVA != null && norm100KVA.getId().getLineSectionTypeId().equalsIgnoreCase(Constants.LINE_NORM_ID_33KV_100kVA_SUBST)){
			if((applicationType.equalsIgnoreCase(PivPrefixType.getEST_XXX_Type(Constants.ESTIMATE_TYPE_EBS))|| applicationType.equalsIgnoreCase(Constants.BS)) && demandValue.longValue() == Constants.BULK_SUPPLY_DEMAND.longValue()){
				//consumerCost = new BigDecimal((demandValue/100 )*norm100KVA.getStandardCost()); // 33 KV sub 100KVA norms
				cebCost = ((100-demandValue)/100)*norm100KVA.getStandardCost();
			}
			if((applicationType.equalsIgnoreCase(PivPrefixType.getEST_XXX_Type(Constants.ESTIMATE_TYPE_ELN))|| applicationType.equalsIgnoreCase(Constants.LAND)) && demandValue.longValue()  >= Constants.LAND_DEMAND_LOWER_LIMIT.longValue() && demandValue.longValue()  <= Constants.LAND_DEMAND_UPPER_LIMIT.longValue()){
				//consumerCost = new BigDecimal((demandValue/100 )*norm100KVA.getStandardCost());
				System.out.println("ffffff " + demandValue);
				cebCost = (((100-demandValue)/100 )*norm100KVA.getStandardCost());
				System.out.println("ffffffcebCost " + cebCost);
			}
			
		}
	}
	else if(costCenterNo.equalsIgnoreCase("460.00") || costCenterNo.equalsIgnoreCase("550.00") || costCenterNo.equalsIgnoreCase("501.00") || costCenterNo.equalsIgnoreCase("510.00")){
		
		System.out.println("hiiielsesssgayani " + norm63KVA);
		System.out.println("newgayani " + norm63KVA);
		System.out.println("ffffff1 " + demandValue);
		//System.out.println("hiiielsesssgayani " + norm100KVA.getId().getLineSectionTypeId() + " fff : " + norm63KVA.getId().getLineSectionTypeId() );
		
		//if(norm100KVA != null && norm100KVA.getId().getLineSectionTypeId().equalsIgnoreCase(Constants.LINE_NORM_ID_33KV_100kVA_SUBST) && norm63KVA != null && norm63KVA.getId().getLineSectionTypeId().equalsIgnoreCase(Constants.LINE_NORM_ID_33KV_63kVA_SUBST)){
		if(norm100KVA != null && norm100KVA.getId().getLineSectionTypeId().equalsIgnoreCase(Constants.LINE_NORM_ID_33KV_100kVA_SUBST)){
				
			System.out.println("hiiielsesssgayani1 ");
			/**if((applicationType.equalsIgnoreCase(PivPrefixType.getEST_XXX_Type(Constants.ESTIMATE_TYPE_EBS))|| applicationType.equalsIgnoreCase(Constants.BS)) && demandValue.longValue() == Constants.BULK_SUPPLY_DEMAND.longValue()){
				//consumerCost = new BigDecimal(norm63KVA.getStandardCost()); // 33 KV sub 100KVA norms
				cebCost = (norm100KVA.getStandardCost()-norm63KVA.getStandardCost());
			}*/
			System.out.println("gayani");
			System.out.println((applicationType.equalsIgnoreCase(PivPrefixType.getEST_XXX_Type(Constants.ESTIMATE_TYPE_ELN))|| applicationType.equalsIgnoreCase(Constants.LAND)) && demandValue.longValue()  >= Constants.LAND_DEMAND_LOWER_LIMIT.longValue() && demandValue.longValue()  <= Constants.LAND_DEMAND_UPPER_LIMIT.longValue());
			if((applicationType.equalsIgnoreCase(PivPrefixType.getEST_XXX_Type(Constants.ESTIMATE_TYPE_ELN))|| applicationType.equalsIgnoreCase(Constants.LAND)) && demandValue.longValue()  >= Constants.LAND_DEMAND_LOWER_LIMIT.longValue() && demandValue.longValue()  <= Constants.LAND_DEMAND_UPPER_LIMIT.longValue()){
				//consumerCost= new BigDecimal((demandValue/100 )*norm100KVA.getStandardCost());
				
				System.out.println("ffffff1 " + demandValue);
				cebCost = (((100-demandValue)/100)*norm100KVA.getStandardCost());
				System.out.println("ffffffcebCost1 " + cebCost);
			}
		}
		
	}
	
	Norm norm = new Norm();
	
	norm.setUom(Constants.DEFAULT_UOM);
	norm.setQuantity(new BigDecimal(Constants.DEFAULT_QUANTITY));
	norm.setLineSectionTypeId(Constants.CEB_COST_CODE);
	norm.setEstimatedCost(new BigDecimal(cebCost));
	norm.setDescription(Constants.CEB_COST_FOR_SUBSTATION);
	norm.setStandardCost(new Float(cebCost));
	
	return norm;
	
}
private Norm populateLineCostCEBANDConsumer(String costCenterNo,String applicationType,Double demandValue,Map<String, Norm> selectedNorms,Map<String, Norm> newaddedDetails,HttpServletRequest request){
	
	Set<String> lineIds = selectedNorms.keySet();
	double cebCost = 0.0;
	double ceblength = 0.0;
	String lineTypeId ="";
	Norm norm = new Norm();
	//BigDecimal consumerCost = new BigDecimal("0");
	if(costCenterNo.equalsIgnoreCase("460.00") || costCenterNo.equalsIgnoreCase("550.00") || costCenterNo.equalsIgnoreCase("501.00") || costCenterNo.equalsIgnoreCase("510.00")){
	
			if((applicationType.equalsIgnoreCase(PivPrefixType.getEST_XXX_Type(Constants.ESTIMATE_TYPE_EBS))|| applicationType.equalsIgnoreCase(Constants.BS)) && demandValue.longValue() == Constants.BULK_SUPPLY_DEMAND.longValue()){
				for(String lineId : lineIds ){
					if(lineId != null && lineId.equalsIgnoreCase("3311L") || lineId.equalsIgnoreCase("3310E") || lineId.equalsIgnoreCase("3310L") || lineId.equalsIgnoreCase("3310R")
							|| lineId.equalsIgnoreCase("3311E") || lineId.equalsIgnoreCase("3311R") || lineId.equalsIgnoreCase("3313E") || lineId.equalsIgnoreCase("3313ED")
								|| lineId.equalsIgnoreCase("3313L") || lineId.equalsIgnoreCase("3313LD") ||
								lineId.equalsIgnoreCase("3313R") || lineId.equalsIgnoreCase("3313RD")){
									if(selectedNorms.get(Constants.CEB_COST_CODE+lineId) != null && selectedNorms.containsKey(Constants.CEB_COST_CODE+lineId)){
										break;
									}else{
										lineTypeId =lineId;
										if(selectedNorms.get(lineId).getEstimatedCost() != null){
											cebCost = selectedNorms.get(lineId).getEstimatedCost().doubleValue()/2;
											ceblength = selectedNorms.get(lineId).getQuantity().doubleValue()/2;
											selectedNorms.get(lineId).setEstimatedCost(new BigDecimal(cebCost));
											if(newaddedDetails != null && newaddedDetails.size() > 0 && newaddedDetails.get(lineId) != null){
												newaddedDetails.get(lineId).setEstimatedCost(new BigDecimal(cebCost));
												
												newaddedDetails.get(lineId).setQuantity(new BigDecimal(ceblength));
											}
											selectedNorms.get(lineId).setQuantity(new BigDecimal(ceblength));
											norm.setUom(Constants.DEFAULT_UOM);
											norm.setQuantity(new BigDecimal(ceblength));
											norm.setLineSectionTypeId(Constants.CEB_COST_CODE+lineTypeId);
											norm.setEstimatedCost(new BigDecimal(cebCost));
											norm.setDescription(Constants.CEB_COST_FOR_LINE);
											norm.setStandardCost(selectedNorms.get(lineId).getStandardCost());
										}
									}
									
					}
				}
			
			}
			
		}
		
	
	
	
	
	request.getSession().setAttribute("selectedNorms",selectedNorms);
	//request.getSession().setAttribute("deletedNorms",deletedNorms);
	request.getSession().setAttribute("newlyAddedDetails",newlyAddedDetails);
	return norm;
	
}
private Norm populateConsumerCost(String sessionKey,String costCenterNo,String applicationType,Double demandValue){
	
	SpNormEjb ejb = new SpNormEjb();
	SpnormsPK pk100KVA = new SpnormsPK();
	pk100KVA.setLineSectionTypeId(Constants.LINE_NORM_ID_33KV_100kVA_SUBST);
	
	Spnorms norm100KVA = ejb.findById(pk100KVA, sessionKey);
    
	SpnormsPK pk63KVA = new SpnormsPK();
	pk63KVA.setLineSectionTypeId(Constants.LINE_NORM_ID_33KV_63kVA_SUBST);
	
	Spnorms norm63KVA = ejb.findById(pk63KVA, sessionKey);
	
	//BigDecimal cebCost = new BigDecimal("0");
	double consumerCost = 0.0;
	if(costCenterNo.equalsIgnoreCase("000.00")){
		if(norm100KVA != null && norm100KVA.getId().getLineSectionTypeId().equalsIgnoreCase(Constants.LINE_NORM_ID_33KV_100kVA_SUBST)){
			if((applicationType.equalsIgnoreCase(PivPrefixType.getEST_XXX_Type(Constants.ESTIMATE_TYPE_EBS))|| applicationType.equalsIgnoreCase(Constants.BS)) && demandValue.longValue() == Constants.BULK_SUPPLY_DEMAND.longValue()){
				consumerCost =((demandValue/100 )*norm100KVA.getStandardCost()); // 33 KV sub 100KVA norms
				//cebCost = new BigDecimal(((100-demandValue)/100 )*norm100KVA.getStandardCost());
			}
			if((applicationType.equalsIgnoreCase(PivPrefixType.getEST_XXX_Type(Constants.ESTIMATE_TYPE_ELN))|| applicationType.equalsIgnoreCase(Constants.LAND)) && demandValue.longValue()  >= Constants.LAND_DEMAND_LOWER_LIMIT.longValue() && demandValue.longValue()  <= Constants.LAND_DEMAND_UPPER_LIMIT.longValue()){
				consumerCost = ((demandValue/100 )*norm100KVA.getStandardCost());
				//cebCost = new BigDecimal(((100-demandValue)/100 )*norm100KVA.getStandardCost());
			}
		}
	}else if(costCenterNo.equalsIgnoreCase("460.00") || costCenterNo.equalsIgnoreCase("550.00") || costCenterNo.equalsIgnoreCase("501.00") || costCenterNo.equalsIgnoreCase("510.00")){
		
		//if(norm100KVA != null && norm100KVA.getId().getLineSectionTypeId().equalsIgnoreCase(Constants.LINE_NORM_ID_33KV_100kVA_SUBST) && norm63KVA != null && norm63KVA.getId().getLineSectionTypeId().equalsIgnoreCase(Constants.LINE_NORM_ID_33KV_63kVA_SUBST)){
		if(norm100KVA != null && norm100KVA.getId().getLineSectionTypeId().equalsIgnoreCase(Constants.LINE_NORM_ID_33KV_100kVA_SUBST)){
				
		/**if((applicationType.equalsIgnoreCase(PivPrefixType.getEST_XXX_Type(Constants.ESTIMATE_TYPE_EBS))|| applicationType.equalsIgnoreCase(Constants.BS)) && demandValue.longValue() == Constants.BULK_SUPPLY_DEMAND.longValue()){
				consumerCost = (norm63KVA.getStandardCost()); // 33 KV sub 100KVA norms
				//cebCost = new BigDecimal(norm100KVA.getStandardCost()-norm63KVA.getStandardCost());
			}*/
			if((applicationType.equalsIgnoreCase(PivPrefixType.getEST_XXX_Type(Constants.ESTIMATE_TYPE_ELN))|| applicationType.equalsIgnoreCase(Constants.LAND)) && demandValue.longValue()  >= Constants.LAND_DEMAND_LOWER_LIMIT.longValue() && demandValue.longValue()  <= Constants.LAND_DEMAND_UPPER_LIMIT.longValue()){
				consumerCost= ((demandValue/100 )*norm100KVA.getStandardCost());
				//cebCost = new BigDecimal(((100-demandValue)/100 )*norm100KVA.getStandardCost());
			}
		}
		
	}
	Norm norm = new Norm();
	
	norm.setUom(Constants.DEFAULT_UOM);
	norm.setQuantity(new BigDecimal(Constants.DEFAULT_QUANTITY));
	norm.setLineSectionTypeId(Constants.CONSUMER_COST_CODE_FOR_SUBSTATION);
	norm.setEstimatedCost(new BigDecimal(consumerCost));
	norm.setDescription(Constants.CONSUMER_COST_FOR_SUBSTATION);
	norm.setStandardCost(new Float(consumerCost));
	
	return norm;
	
}

private Norm populateFixedCost(String sessionKey,String costCenterNo,String applicationType,Double demandValue){
	
	long denamd = demandValue.longValue();
	Norm norm = new Norm();
	if(denamd  <= Constants.BULK_SUPPLY_DEMAND.longValue()){
		norm.setUom(Constants.DEFAULT_UOM);
		norm.setQuantity(new BigDecimal(Constants.DEFAULT_QUANTITY));
		norm.setLineSectionTypeId(Constants.FIXED_CODE_FOR_63);
		norm.setEstimatedCost(new BigDecimal(Constants.FIXED_COST_FOR_63.doubleValue()));
		norm.setDescription(Constants.FIXED_CODE_FOR_63_DES);
		norm.setStandardCost(new Float(Constants.FIXED_COST_FOR_63.doubleValue()));
		
	}

	return norm;
	
}

private Norm populateFixedCost75(String sessionKey,String costCenterNo,String applicationType,Double demandValue){
	
	long denamd = demandValue.longValue();
	Norm norm = new Norm();
	
		norm.setUom(Constants.DEFAULT_UOM);
		norm.setQuantity(new BigDecimal(Constants.DEFAULT_QUANTITY));
		norm.setLineSectionTypeId(Constants.FIXED_CODE_FOR_75);
		norm.setEstimatedCost(new BigDecimal(Constants.FIXED_COST_FOR_75.doubleValue()));
		norm.setDescription(Constants.FIXED_CODE_FOR_75_DES);
		norm.setStandardCost(new Float(Constants.FIXED_COST_FOR_75.doubleValue()));
		
	

	return norm;
	
}

private Norm populateFixedCost95(String sessionKey,String costCenterNo,String applicationType,Double demandValue){
	
	long denamd = demandValue.longValue();
	Norm norm = new Norm();
	
		norm.setUom(Constants.DEFAULT_UOM);
		norm.setQuantity(new BigDecimal(Constants.DEFAULT_QUANTITY));
		norm.setLineSectionTypeId(Constants.FIXED_CODE_FOR_95);
		norm.setEstimatedCost(new BigDecimal(Constants.FIXED_COST_FOR_95.doubleValue()));
		norm.setDescription(Constants.FIXED_CODE_FOR_95_DES);
		norm.setStandardCost(new Float(Constants.FIXED_COST_FOR_95.doubleValue()));
		
	

	return norm;
	
}


private Norm populateVCOST(String sessionKey,String costCenterNo,String applicationType,Double lineLength){
	
	long line = lineLength.longValue();
	Norm norm = new Norm();
	//double cost = line * 2000;
	//2016 rates
	double cost = line * 1785;
		norm.setUom("m");
		norm.setQuantity(new BigDecimal(line));
		norm.setLineSectionTypeId(Constants.VARIABLE_COST_CODE);
		norm.setEstimatedCost(new BigDecimal(cost));
		norm.setDescription("Variable Cost");
		norm.setStandardCost(new Float(1785));
		//norm.setStandardCost(new Float(2000));
		
	

	return norm;
	
}
private JSONObject validateNorms(HttpServletRequest request){
	
	selectedNorms = (Map<String, Norm>) request.getSession().getAttribute("selectedNorms");
	
	return null;
	
	
}

///////////////////////////////////////

	
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
	}
public JSONObject  loadResourceDetails(HttpServletRequest request) {
	
	JSONObject resourceTypeJson = new JSONObject();
	JSONArray resourceCodeJson = new JSONArray();

	JSONObject objectlist = new JSONObject();
	String sessionKey= (String) request.getSession().getAttribute("region");
	String costCenterNo=(String) request.getSession().getAttribute("costCenterNo");
	BigDecimal resCatgory = null;
	String resourceType = request.getParameter("resourceTypeId");
	//String resourceCode = request.getParameter("resourceCode");
	try {
		EstimateEjb estimateEjb = new EstimateEjb(sessionKey); 
		if(resourceType == null){
			return resourceTypeJson.put("error", "error occured");
		}
		List<Pcrstypm> list  = estimateEjb.isResourceTypeExist(resourceType,sessionKey);
		
		if(list != null && list.size() > 0){
			resCatgory = list.get(0).getResCat();
		}
		
		InwrhmtmEjb ejb = new InwrhmtmEjb(sessionKey);
		if(resourceType.equalsIgnoreCase(Constants.MAT_COST_OTHER)){
			List <String> matCds = ejb.findNPLMatCds(costCenterNo, sessionKey);
		}else if(resourceType.equalsIgnoreCase(Constants.MAT_COST)){
			List <String> matCds = ejb.findMatCds(costCenterNo, sessionKey);
		}else{
			Pcrsgrpm resourceDetail = estimateEjb.loadResourceDetails(resourceType,null,sessionKey);
			Pcrsgrpm resourceDetail1 = estimateEjb.loadResourceDetails(resourceType,resourceDetail.getResCd(),sessionKey);
			
			if(resourceDetail1 != null){
				//JSONObject resourceTypeJson = new JSONObject();
				resourceTypeJson.put("lineSectypeId", resourceDetail1.getResCd());
				
				resourceTypeJson.put("resourceType", resourceDetail1.getResType());
				resourceTypeJson.put("resourceCategory", resourceDetail1.getResCat());
				resourceTypeJson.put("resourceCode", resourceDetail1.getResCd());
			  	resourceTypeJson.put("resourceName", resourceDetail1.getResNm());
			  	//resourceTypeJson.put("tolerance", resourceDetail.g);
			  	//resourceTypeJson.put("estimateQuantity", resourceDetail.getEstimatedQuantity());
			  	resourceTypeJson.put("unitPrice", resourceDetail1.getUnitPrice());
			  	resourceTypeJson.put("uom", resourceDetail1.getResUom());
			  	//resourceTypeJson.put("totalCost", resourceDetail.getUnitPrice()*resourceDetail.getEstimatedQuantity());
		
		}
		}
		
		/*if(resCatgory == Constants.RESOURCE_CATEGORY){
			
		}else{
			Pcrsgrpm resourceDetail = estimateEjb.loadResourceDetails(resourceType,null,sessionKey);
			if(resourceDetail != null){
				//JSONObject resourceTypeJson = new JSONObject();
				//resourceTypeJson.put("lineSectypeId", resourceDetail.getL.getLineSectionTypeId());
				
				
				JSONObject resourceCodesJson = new JSONObject();
				
				resourceCodesJson.put("id", resourceDetail.getResCd());
				resourceCodesJson.put("name", resourceDetail.getResCd());
				
				resourceCodeJson.put(resourceCodesJson);
	      // }
				resourceTypeJson.put("jsonarrayResourceCodes", resourceCodeJson);
			}
			if(resourceDetail != null){
				Pcrsgrpm resourceDetail1 = estimateEjb.loadResourceDetails(resourceType,resourceDetail.getResCd(),sessionKey);
				if(resourceDetail1 != null){
					//JSONObject resourceTypeJson = new JSONObject();
					resourceTypeJson.put("lineSectypeId", resourceDetail1.getResCd());
					
					resourceTypeJson.put("resourceType", resourceDetail1.getResType());
					resourceTypeJson.put("resourceCategory", resourceDetail1.getResCat());
					resourceTypeJson.put("resourceCode", resourceDetail1.getResCd());
				  	resourceTypeJson.put("resourceName", resourceDetail1.getResNm());
				  	//resourceTypeJson.put("tolerance", resourceDetail.g);
				  	//resourceTypeJson.put("estimateQuantity", resourceDetail.getEstimatedQuantity());
				  	resourceTypeJson.put("unitPrice", resourceDetail1.getUnitPrice());
				  	resourceTypeJson.put("uom", resourceDetail1.getResUom());
				  	//resourceTypeJson.put("totalCost", resourceDetail.getUnitPrice()*resourceDetail.getEstimatedQuantity());
			
				
				 //for 
				}
			}
			
		}*/
		/*SpPointdmt resourceDetail  = estimateEjb.loadResourceDetails(resourceType,resourceCode,sessionKey);
		
		
		
		if(resourceDetail != null){
			//JSONObject resourceTypeJson = new JSONObject();
			resourceTypeJson.put("lineSectypeId", resourceDetail.getId().getLineSectionTypeId());
			resourceTypeJson.put("resourceType", resourceDetail.getResType());
			resourceTypeJson.put("resourceCategory", resourceDetail.getResCat());
			resourceTypeJson.put("resourceCode", resourceDetail.getId().getResCd());
		  	resourceTypeJson.put("resourceName", resourceDetail.getResName());
		  	resourceTypeJson.put("tolerance", resourceDetail.getTolerance());
		  	resourceTypeJson.put("estimateQuantity", resourceDetail.getEstimatedQuantity());
		  	resourceTypeJson.put("unitPrice", resourceDetail.getUnitPrice());
		  	resourceTypeJson.put("uom", resourceDetail.getUom());
		  	resourceTypeJson.put("totalCost", resourceDetail.getUnitPrice()*resourceDetail.getEstimatedQuantity());
	
		}*/
	  //for (SpPointdmt resourceDetail : resourceDetails) {
		  	
		  	//resourceTypeJson.put("totalCost", resourceDetail.getUnitPrice());
		  	//resourceTypeJson.put(resourceTypeJson);
       //}
	  	
       //objectlist.put("resourceDetail", resourceDetailJson);

	} catch (Exception e) {
		
		e.printStackTrace();
	}

	return resourceTypeJson;
	}

public JSONObject  loadPegScheduleEstmationRefNumbers(HttpServletRequest request) {
	
	JSONArray applicationRefNumbers = new JSONArray();

	
	JSONObject objectlist = new JSONObject();

	List<String> applicationNos  = (List<String>) request.getSession().getAttribute("estimationRefNos");

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
       
       for (String warehouse : warehouses) {
		  	JSONObject warehouseJson = new JSONObject();
			
		  	warehouseJson.put("id", warehouse);
		  	warehouseJson.put("name", warehouse);
			
		  	warehousesArray.put(warehouseJson);
      }
       for (String fundsource : fundsources) {
		  	JSONObject fundsourceJson = new JSONObject();
			
		  	fundsourceJson.put("id", fundsource);
		  	fundsourceJson.put("name", fundsource);
			
		  	fundsourcesArray.put(fundsourceJson);
     }
      objectlist.put("jsonarrayapplicationrefs", applicationRefNumbers);
      objectlist.put("jsonWarehouses", warehousesArray);
      objectlist.put("jsonFundsources", fundsourcesArray);
      
       objectlist.put("costCenter", costCenterNo);
       objectlist.put("warehouse", warehouses.get(0));
       objectlist.put("fundsource", fundsources.get(0));
       EstimateEjb estimateEjb = new EstimateEjb(sessionKey); 
	
       List<String> codes = estimateEjb.getCatCode(warehouses.get(0),sessionKey);
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
		      
		       objectlist.put("categoryCode", codes.get(0));
		       
		    
				List<String> fundids = estimateEjb.getFundIds(costCenterNo,fundsources.get(0),sessionKey);
				if(fundids != null){
					for(String fundid: fundids){
			
					
					  	JSONObject fundidJson = new JSONObject();
						
					  	fundidJson.put("id", fundid);
					  	fundidJson.put("name", fundid);
						
					  	fundArray.put(fundidJson);
					}
					
			
				}
				
				 objectlist.put("jsonfundIds", fundArray);
			      
			       objectlist.put("fundid", fundids.get(0));
			       
				
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

public JSONObject getUsername(HttpServletRequest request){
	JSONObject userIdObejct = new JSONObject();
	String userId = request.getParameter("userId");
	String deptId = request.getParameter("deptId");
	String sessionKey= (String) request.getSession().getAttribute("region");
	
	try {
		SecurityEjb securityEjb = new SecurityEjb(sessionKey); 
		String userName = securityEjb.getUserName(userId, sessionKey);
		if(userName != null){
			
			userIdObejct.put("userName", userName);
	
		}


	} catch (JSONException e) {
		
		e.printStackTrace();
	}

	return userIdObejct;
}
/*public JSONObject  generateWorkEstimateNo(HttpServletRequest request) {
	
	JSONObject estimateNo = new JSONObject();
	
	String sessionKey= (String) request.getSession().getAttribute("region");
	String smcType=(String) request.getSession().getAttribute("smcType");
	String costCenterNo=(String) request.getSession().getAttribute("costCenterNo");
	String applicationId = request.getParameter("applicationId");
	setFormat(new Format());
	String estimatePrefix = costCenterNo+"/"+getFormat().getYear()+"/";
	String workEstmateNo = null;
	try {
		EstimateEjb estimateEjb = new EstimateEjb(sessionKey); 
		
		try {
			workEstmateNo = estimateEjb.getWorkEstimateNo(estimatePrefix, applicationId, costCenterNo, sessionKey);
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
	}*/

public JSONObject getCodeName(HttpServletRequest request){
	JSONObject userIdObejct = new JSONObject();
	String code = request.getParameter("codeId");

	String codeType = request.getParameter("codeType");
	String sessionKey= (String) request.getSession().getAttribute("region");
	String costCenterNo=(String) request.getSession().getAttribute("costCenterNo");
	
	try {
		ProvinceDetailsMasterEjb provinceDetailsMasterEjb = new ProvinceDetailsMasterEjb(); 
		String codeName = provinceDetailsMasterEjb.findCodeName(code, costCenterNo, codeType, sessionKey);
		if(codeName != null){
			
			userIdObejct.put("codeName", codeName);
	
		}


	} catch (JSONException e) {
		
		e.printStackTrace();
	}

	return userIdObejct;
}
}
