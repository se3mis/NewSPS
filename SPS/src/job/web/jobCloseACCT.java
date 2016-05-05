package job.web;

import inventory.service.InwrhmtmEjb;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.SessionAware;

import piv.model.PivDetail;
import piv.model.PivDetailPK;
import piv.model.TempTb;
import piv.service.PivDetailEjb;
import reports.web.report;
import security.service.SecurityEjb;

import util.common.ApplicationStatus;


import util.common.EstimateStatus;
import util.common.Format;
import util.common.Path;
import util.common.StandardEstimateStatus;
import application.model.Application;
import application.service.ApplicationEjb;
import application.service.WiringLandDetailEjb;
import estimate.dto.ApproveDetails;
import estimate.dto.DetailEstimateDTO;
import estimate.dto.EstimateDetails;
import estimate.dto.Norm;
import estimate.model.Approval;
import estimate.model.EstimateReference;
import estimate.model.Pcestdtt;
import estimate.model.PcestdttPK;
import estimate.model.Pcesthtt;
import estimate.model.PcesthttPK;
import estimate.model.Pegschdmt;
import estimate.model.SpPointdmt;
import estimate.model.Spstdestdmt;
import estimate.model.SpstdestdmtPK;
import estimate.model.Spstdesthmt;
import estimate.model.SpstdesthmtPK;
import estimate.service.EstimateEjb;
import estimate.service.EstimateReferenceEjb;
import estimate.service.PcesthttEjb;
import estimate.service.PegschdmtEjb;
import estimate.service.SpestedyConEjb;
import estimate.service.SpstdesthmtEjb;


import com.opensymphony.xwork2.ActionSupport;

import javax.persistence.PersistenceException;
import javax.persistence.RollbackException;
import javax.servlet.http.HttpServletRequest;

import job.model.Pcestdmt;
import job.model.PcestdmtPK;
import job.model.Pcesthmt;
import job.model.PcesthmtPK;
import job.service.JobEjb;
import job.service.PcesthmtEjb;
import util.common.Constants;;

@SuppressWarnings("serial")
public class jobCloseACCT  extends ActionSupport implements SessionAware, ParameterAware{
	private String userId;
	private String password;
	private String region;
	private String costCenterNo;
	private String costCenterName;
	private String estimateNo;
	private String fileRef;
	private String pivNo;
	private String wareHouseId;
	private String amount;
	private String pivDatePicker;
	private String estimateDate;

	private String fundsou;
	private String rebate;
	private String categoryId;
	private String fundid;
	private String description;
	private String totalCost;
	private String lblError=null;
	private String lblSuccess=null;
	private static final String MSG_ERROR="ERROR";
	private static final String MSG_INFO="INFO";
	private static final String MSG_DONE="DONE";
	private String errorMessage;
	private static final String MSG_NONE="NONE";
	private Map<String, Object> session;
	private String messageType=MSG_NONE;
	
	private List<String> selectedPegItemList=null;
	
	Map<String,Pegschdmt> selectedPegschuleItems=null;
	Map<String,DetailEstimateDTO> selectedAdditionalMaterials=null;
	Map<String,DetailEstimateDTO> addedItemsFromPegSchedule=null;
	Map<String,DetailEstimateDTO> allSelectedAdditionalResoMap=null;
	
	Map<String,DetailEstimateDTO> resourceMap =null;
	
	
	
	Map<String,DetailEstimateDTO> updatedDetailsMap =null;
	Map<String,DetailEstimateDTO> deletedDetailsMap =null;
	Map<String,DetailEstimateDTO> alreadyAddedDetails = null;
	EstimateDetails dto = null;
	List<String> errorMessages = new ArrayList<String>();
	NumberFormat nf = NumberFormat.getInstance();
	private List<Pcesthmt> JobList;
	private String hiddenJobNo;
	private String projectNo;
	

	private String loggedInUserId;
	private String loggedInUserLevel;
	private String applicationType;
	
	private String reviseReason;
	private String rejectReason;
	/*availableOtherMaterialsMap = (Map<String,DetailEstimateDTO> )request.getSession().getAttribute("availableOtherMaterialsMap");
	selectedOtherMaterials =  (Map<String, DetailEstimateDTO>) request.getSession().getAttribute("selectedOtherMaterials");
	
	availableNPLMaterialsMap = (Map<String,DetailEstimateDTO> )request.getSession().getAttribute("availableOtherMaterialsMap");
	selectedNPLMaterials =  (Map<String, DetailEstimateDTO>) request.getSession().getAttribute("selectedNPLMaterials");

	
	availableOtherResourcesMap = (Map<String,DetailEstimateDTO> )request.getSession().getAttribute("availableOtherResourcesMap");
	selectedOtherResources =  (Map<String, DetailEstimateDTO>) request.getSession().getAttribute("selectedOtherResources");

	pegIdList = (List<String>) request.getSession().getAttribute("selectedpegIdList");
	resourceMap = (Map<String,SpPointdmt>) request.getSession().getAttribute("detailEstimationDetails");*/
	
	private String applicationId;
	//print variables
	private  String selectCategoryString = "----Select Category-----";
	private String selectedReport;
	private static final String viewPath="Reports>Select Report";
	private String path;
	private InputStream fileInputStream;
	private InputStream fileInputStream1;
	private String divSec;
	private String area;
	private String eCSC;
	private String district;
	private String esname;

	private Map <String, String[]> parameters;
	public void setLoggedData() 
	{
		
		
		/*setLoggedInUserLevel(getSession().get("userRole").toString());
		
		setLoggedInUserId(getSession().get("userName").toString());*/
		HttpServletRequest request = ServletActionContext.getRequest();
		String region= (String) request.getSession().getAttribute("region");
	
		String costCenterNo=(String) request.getSession().getAttribute("costCenterNo");
		String userId= (String) request.getSession().getAttribute("userName");
		setLoggedInUserLevel(getSession().get("userRole").toString());
		String smcType= (String) request.getSession().getAttribute("smcType");
		setCostCenterNo(costCenterNo);
		setUserId(userId);
		setRegion(region);
		setApplicationType(smcType);
	}
	public String sendToApprovalBS(){
		setLblError(null);
		setLoggedData();
		//setLoggedInUserId(getSession().get("userName").toString());
		
		fillJobList();
		return SUCCESS;
	}
	private void fillJobList()
	{
		setLoggedData();
		HttpServletRequest request = ServletActionContext.getRequest();
		
		PcesthmtEjb pcesthmtEjb = new PcesthmtEjb(region); 
		List<Long> status = new ArrayList<Long>();
		
		//status.add(EstimateStatus.APPLICATION_MODIFIED.getKey()); 
		try {
			this.JobList = pcesthmtEjb.getJobDetailList(costCenterNo,  new Long(EstimateStatus.UPDATED_REVISED_JOB));
			//request.getSession().setAttribute("estimateList", this.EstimateList);
			//RequestDispatcher dispatcher = request.getRequestDispatcher("/sendForValidation.jsp");
			//dispatcher.forward( request, ServletActionContext.getResponse()); 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String post()
	{
		try
		{
			setErrorMessage(null);
			
			setLoggedData();
			setPath(viewPath);
			
			nf.setGroupingUsed(true);
			nf.setMaximumFractionDigits(2);
			nf.setMinimumFractionDigits(2);
			
			PcesthmtEjb pcesthmtEjb = new PcesthmtEjb(getSessionKey("region"));
			//pcesthmtEjb.changeStatusPcesthmt(jobNo, costCenterNo, new BigDecimal(EstimateStatus.JOB_APPROVAL_ES));
			
			String[] jobNo= getHiddenJobNo().split(",");
			boolean status = false;
			if(jobNo[0].trim() != null){
				projectNo = jobNo[0].trim();
			}
			Calendar calendar = Calendar.getInstance();
			Format format=new Format();
			Approval approval=new Approval();
			approval.setApprovalId(null);
			approval.setReferenceNo(projectNo);
			approval.setDeptId(costCenterNo);
			approval.setApprovalType("JOB_REVD");
			approval.setApprovedLevel(getSessionKey("userRole"));
			approval.setApprovedBy(getSessionKey("userName"));
			approval.setApprovedDate(calendar.getTime());
			approval.setApprovedTime(format.FormatTime());
			approval.setFromStatus(new BigDecimal(EstimateStatus.JOB_RIVISION).toString());
			approval.setToStatus(new BigDecimal(EstimateStatus.JOB_APPROVAL_CE).toString());
			
			//approval.setStandardCost(new BigDecimal(nf.parse(totalCost).doubleValue()));
			//approval.setDetailedCost(new BigDecimal(nf.parse(totalMatCost).doubleValue()));

			//approval.setStandardCost(new BigDecimal(Double.toString(nf.parse(getStandardCost(projectNo,costCenterNo).toString()).doubleValue())));
			
			String detailCost = null;
			if(pcesthmtEjb.findByJobNo(projectNo, costCenterNo) != null && pcesthmtEjb.findByJobNo(projectNo, costCenterNo).getStdCost() != null  ){
				detailCost = pcesthmtEjb.findByJobNo(projectNo, costCenterNo).getStdCost().toString();
			}
			approval.setDetailedCost(new BigDecimal(Double.toString(nf.parse(detailCost).doubleValue())));
			approval.setStandardCost(new BigDecimal(Double.toString(nf.parse(detailCost).doubleValue())));
			
			//Pcesthmt pcesthmt = pcesthmtEjb.findByJobNo(jobNo, costCenterNo);
			Pcesthmt pcesthmt =null;
		
			pcesthmt = pcesthmtEjb.findByJobNo(projectNo, costCenterNo);
			
			
			pcesthmt.setStatus(new Long(EstimateStatus.JOB_APPROVAL_CE));
			pcesthmtEjb.validateJob(pcesthmt, approval);
			
			setMessageType(MSG_DONE);
			setErrorMessage("DONE:- Job sent to CE for approval!");
			
			
		}
		catch(Exception e)
		{
			return ERROR;
		}
		
		fillJobList();
		
		return SUCCESS;
	}
	
	private BigDecimal getStandardCost(String estimateNo,String deptId){
		
		String sEstimateNo= null;
		String commercialDeptid=null;
		String commercialId= deptId.substring(0, 3);
		if(commercialId != null){
			commercialDeptid = commercialId.concat(".00");

		}
		EstimateReferenceEjb estEjb = new EstimateReferenceEjb();
		setLoggedData();
		EstimateReference est = estEjb.findByWorkEstimateNo(estimateNo.trim(), deptId, region);
		
		if(est != null){
			SpstdesthmtEjb ejb = new SpstdesthmtEjb(region);
			sEstimateNo = est.getId().getStandardEstimateNo();
			SpstdesthmtPK id=new SpstdesthmtPK();
			id.setDeptId(commercialDeptid);
			id.setStdNo(sEstimateNo.trim());
			id.setApplicationNo(sEstimateNo.trim());
			Spstdesthmt hmt = ejb.findById(id, region);
			if(hmt != null){
				return hmt.getTotalCost();
			}else{
				return new BigDecimal("0");
			}
		}
		
		return null;
	}

	private void loadJobNos(){
		setLoggedData();
		List<String> jobNos = null;
		HttpServletRequest request = ServletActionContext.getRequest();
		
		PcesthmtEjb pcesthmtEjb = new PcesthmtEjb(region);
		//List<Long> status = new ArrayList<Long>();
		
		
		
		if(getSessionKey("userRole").equalsIgnoreCase("EE")){
			//status.add(StandardEstimateStatus.TO_BE_VALIDATED_BY_ELECTRICAL_ENGINEER.getKey());
			jobNos = pcesthmtEjb.findJobNoList(costCenterNo,new Long(EstimateStatus.JOB_APPROVAL_EE)); 
			
		}else if(getSessionKey("userRole").equalsIgnoreCase("CE")){
			jobNos = pcesthmtEjb.findJobNoList(costCenterNo,new Long(EstimateStatus.JOB_APPROVAL_CE)); 
			
		}/*else if(getSessionKey("userRole").equalsIgnoreCase("PE")){
			jobNos = pcesthmtEjb.findJobNoList(costCenterNo,new Long(EstimateStatus.JOB_APPROVAL_DGM)); 
		}*/else if(getSessionKey("userRole").equalsIgnoreCase("DGM")){
			jobNos = pcesthmtEjb.findJobNoList(costCenterNo,new Long(EstimateStatus.JOB_APPROVAL_DGM)); 
		}
		
		//request.getSession().setAttribute("estimationRefNos",estimationRefNos);
		request.getSession().setAttribute("revisejobNos",jobNos);	
	}
	
	
	public String app_rejected()
	{
		
		try{
			setLoggedData();
			HttpServletRequest request = ServletActionContext.getRequest();
			String projectNo= getProjectNo();
			String totalC= getTotalCost();
			
			PcesthmtEjb pcesthmtEjb = new PcesthmtEjb(region);
			
			
			setErrorMessage(null);
			String msg = "";
			msg = pcesthmtEjb.approveJob(projectNo, costCenterNo, getSession().get("userRole").toString(),getSession().get("userName").toString(),getSession().get("estimateType").toString());
			//msg = "Rejected";
			//setFormData();
			//clearFields();
			int errorIndex = msg.indexOf('@');
			int successIndex = msg.indexOf('#');
			msg =msg.substring(1);
			
			if(errorIndex!=-1){
				setMessageType(ERROR);
				setErrorMessage(msg);
			}
			else if(successIndex!=-1){
				setMessageType(MSG_DONE);
			
				setErrorMessage("Job Rejected Successfully");
			}
			
			
		}catch(Exception e){
			return ERROR;
		}
		loadJobNos();
		return SUCCESS;
	
	}
	public String approve()
	{
		
		try{
			setLoggedData();
			HttpServletRequest request = ServletActionContext.getRequest();
			String projectNo= getProjectNo();
			String totalC= getTotalCost();
			
			PcesthmtEjb pcesthmtEjb = new PcesthmtEjb(region);
			
			
			setErrorMessage(null);
			String msg = "";
			msg = pcesthmtEjb.approveJob(projectNo, costCenterNo, getSession().get("userRole").toString(),getSession().get("userName").toString(),getSession().get("estimateType").toString());
			//setFormData();
			//clearFields();
			int errorIndex = msg.indexOf('@');
			int successIndex = msg.indexOf('#');
			msg =msg.substring(1);
			
			if(errorIndex!=-1){
				setMessageType(ERROR);
				setErrorMessage(msg);
			}
			else if(successIndex!=-1){
				setMessageType(MSG_DONE);
			
				setErrorMessage(msg);
			}
			
			
		}catch(Exception e){
			return ERROR;
		}
		loadJobNos();
		return SUCCESS;
		
		/*setLoggedData();
		HttpServletRequest request = ServletActionContext.getRequest();
		
		PcesthmtEjb pcesthmtEjb = new PcesthmtEjb(region);
		ApproveDetails detail = new ApproveDetails();
		
		boolean status = false;
		
		
		String projectNo= getProjectNo();
		String totalC= getTotalCost();
		Long totalCost = null;
		if(totalC != null && !totalC.equalsIgnoreCase("")){
			totalCost = Long.parseLong(totalC);
		}
		if(estimateNo != null && getSessionKey("userRole").equalsIgnoreCase("ES")){
			if( (totalCost < Constants.ES_APPROVE_LIMIT_MAX )){
				
				status= estimateEjb.updateEstimateStatus(estimateNo, costCenterNo, StandardEstimateStatus.APPROVED_ESTIMATES.getKey(),userId,new Date(),null,null,null,null,null,null,null,null,null,null,null, region);
				setErrorMessage("DONE:- Successfully Approved By ES");
				
			}else{
				status= estimateEjb.updateEstimateStatus(estimateNo, costCenterNo, StandardEstimateStatus.TO_BE_VALIDATED_BY_PLANNING_ENGINEER.getKey(),null,null,null,null,null,null,userId,new Date(),null,null,null,null,null, region);
				setErrorMessage("DONE:- Successfully Posted for EE");
			}
		}

		if(estimateNo != null && getSessionKey("userRole").equalsIgnoreCase("EE")){
			
				//pcesthttEjb.changeStatusPcesthtt(estimateNo,costCenterNo,new BigDecimal(EstimateStatus.EST_TOBE_APPROVAL_CE));
			pcesthmtEjb.updateEstimateDetails(projectNo,costCenterNo,new BigDecimal(EstimateStatus.EST_TOBE_APPROVAL_CE),null,null,userId,new Date(),null,null,null,null);
				setErrorMessage("DONE:- Successfully Posted for CE");
			
			
		}if(projectNo != null && getSessionKey("userRole").equalsIgnoreCase("EE")){
			
			
			//pcesthttEjb.changeStatusPcesthtt(estimateNo,costCenterNo,new BigDecimal(EstimateStatus.EST_TOBE_APPROVAL_DGM));
			pcesthmtEjb.updateReviseApproveDetails(projectNo,costCenterNo,new Long(EstimateStatus.REVISED_JOB_APPROVED),null,null,null,null,userId,new Date(),null,null,null);
				setErrorMessage("DONE:- Revised Job uccessfully Approved By EE");
			
			
		}else if(estimateNo != null && getSessionKey("userRole").equalsIgnoreCase("PE")){
			
			
			status= estimateEjb.updateEstimateStatus(estimateNo, costCenterNo, StandardEstimateStatus.TO_BE_VALIDATED_BY_CE.getKey(),null,null,null,null,userId,new Date(),null,null,null,null,null,null,null, region);
			setErrorMessage("DONE:- Successfully Posted for CE");
			
		}else if(estimateNo != null && getSessionKey("userRole").equalsIgnoreCase("CE")){
			
			pcesthmtEjb.updateReviseApproveDetails(projectNo,costCenterNo,new BigDecimal(EstimateStatus.REVISED_JOB_APPROVED),null,null,null,null,null,null,userId,new Date());
			//pcesthttEjb.changeStatusPcesthtt(estimateNo,costCenterNo,new BigDecimal(EstimateStatus.EST_APPROVED));
			setErrorMessage("DONE:- Revised Job uccessfully Approved By CE");
		}
		
		
		//if(status){
			setMessageType(MSG_DONE);
			
		//}
			loadJobNos();
		return SUCCESS;*/
	}
	
	
	
	public String reject()
	{
		setLoggedData();
		HttpServletRequest request = ServletActionContext.getRequest();
		
		PcesthmtEjb pcesthmtEjb = new PcesthmtEjb(region);
		setErrorMessage(null);
		String projectNo= getProjectNo();
		Pcesthmt pcesthmt =null;
	
		pcesthmt = pcesthmtEjb.findByJobNo(projectNo, costCenterNo);
		
		
		
		
		Long preStatus = pcesthmt.getStatus();
		pcesthmt.setStatus(new Long(EstimateStatus.JOB_REJECTED));
		pcesthmt.setRejctUid(getSessionKey("userName"));
		pcesthmt.setRejctDt(new Date());
		pcesthmt.setRejectReason(rejectReason);
		try
		{
			//pcesthmtEjb.updatePcesthmt(pcesthmt);	
			
			nf.setGroupingUsed(true);
			nf.setMaximumFractionDigits(2);
			nf.setMinimumFractionDigits(2);
			
			Calendar calendar = Calendar.getInstance();
			Format format=new Format();
			Approval approval=new Approval();
			approval.setApprovalId(null);
			approval.setReferenceNo(projectNo);
			approval.setDeptId(costCenterNo);
			approval.setApprovalType("JOB_RJCT");
			approval.setApprovedLevel(getSessionKey("userRole"));
			approval.setApprovedBy(getSessionKey("userName"));
			approval.setApprovedDate(calendar.getTime());
			approval.setApprovedTime(format.FormatTime());
			approval.setReason(rejectReason);
			approval.setFromStatus(preStatus.toString());
			approval.setToStatus(pcesthmt.getStatus().toString());
			//approval.setStandardCost(new BigDecimal(Double.toString(nf.parse(getStandardCost(estimateNo,costCenterNo).toString()).doubleValue())));
			
			String detailCost = pcesthmtEjb.findByJobNo(projectNo, costCenterNo).getStdCost().toString();
			approval.setDetailedCost(new BigDecimal(Double.toString(nf.parse(detailCost).doubleValue())));
			approval.setStandardCost(new BigDecimal(Double.toString(nf.parse(detailCost).doubleValue())));
			
			pcesthmtEjb.rejectJob(pcesthmt, approval);
		}
		catch(Exception e3){
			
			loadJobNos();
		}
		loadJobNos();
		setMessageType(MSG_DONE);
		
		setErrorMessage("Job Sent To DEO For Modification Successfully.");
		
		return SUCCESS;
	}
	
	
	public String softCloseEE()
	{   System.out.println("Hii softCloseEE ");
		setLoggedData();
		HttpServletRequest request = ServletActionContext.getRequest();
		
		PcesthmtEjb pcesthmtEjb = new PcesthmtEjb(region);
		setErrorMessage(null);
		String projectNo= getProjectNo();
		Pcesthmt pcesthmt =null;
	
		pcesthmt = pcesthmtEjb.findByJobNo(projectNo, costCenterNo);
		
		Long preStatus = pcesthmt.getStatus();
		pcesthmt.setStatus(new Long(EstimateStatus.JOB_SOFT_CLOSED));
		pcesthmt.setAprUid2(getSessionKey("userName"));
		pcesthmt.setAprDt2(new Date());
		pcesthmt.setRejectReason(rejectReason);
		try
		{
			//pcesthmtEjb.updatePcesthmt(pcesthmt);	
			
			nf.setGroupingUsed(true);
			nf.setMaximumFractionDigits(2);
			nf.setMinimumFractionDigits(2);
			
			Calendar calendar = Calendar.getInstance();
			Format format=new Format();
			Approval approval=new Approval();
			approval.setApprovalId(null);
			approval.setReferenceNo(projectNo);
			approval.setDeptId(costCenterNo);
			approval.setApprovalType("JOB_SCEE");
			approval.setApprovedLevel(getSessionKey("userRole"));
			approval.setApprovedBy(getSessionKey("userName"));
			approval.setApprovedDate(calendar.getTime());
			approval.setApprovedTime(format.FormatTime());
			approval.setReason(rejectReason);
			approval.setFromStatus(preStatus.toString());
			approval.setToStatus(pcesthmt.getStatus().toString());
			//approval.setStandardCost(new BigDecimal(Double.toString(nf.parse(getStandardCost(estimateNo,costCenterNo).toString()).doubleValue())));
			
			String detailCost = pcesthmtEjb.findByJobNo(projectNo, costCenterNo).getStdCost().toString();
			approval.setDetailedCost(new BigDecimal(Double.toString(nf.parse(detailCost).doubleValue())));
			approval.setStandardCost(new BigDecimal(Double.toString(nf.parse(detailCost).doubleValue())));
			
			pcesthmtEjb.rejectJob(pcesthmt, approval);
		}
		catch(Exception e3){
			
			loadJobNos();
		}
		loadJobNumbers();
		setMessageType(MSG_DONE);
		setErrorMessage("Job Sent To PE For Approval Successfully.");
		
		return SUCCESS;
	}
	
	
	public String init()
	{
		errorMessages.clear();
		setLoggedData();
		loadJobNumbers();
		loadWareHouse();
		loadFundSources();
		return SUCCESS;
	}
	
	public String initFromMain()
	{
		errorMessages.clear();
		setLoggedData();
		loadJobNumbers();
		loadWareHouse();
		loadFundSources();
		return SUCCESS;
	}
	
	
	
	/*public void loadApplicationRefNumbers(){
		try{
			List<String> applicationRefNos = null;
			EstimateEjb estimateEjb = new EstimateEjb(getRegion());
			applicationRefNos = estimateEjb.loadApplicationRefnos(getUserId(),getCostCenterNo()); 
			HttpServletRequest request = ServletActionContext.getRequest();
			request.getSession().setAttribute("applicationRefNos",applicationRefNos);
		}catch(Exception e){
			
		}
		
		
		
	}*/
	
	/*public void loadEstimationRefNumbers(){
		try{
			setLoggedData();
			List<String> estimationRefNos = null;
			SpestedyConEjb estimateEjb = new SpestedyConEjb(getRegion());
			estimationRefNos = estimateEjb.loadApplicationRefnos(getUserId(),getCostCenterNo()); 
			HttpServletRequest request = ServletActionContext.getRequest();
			request.getSession().setAttribute("estimationRefNos",estimationRefNos);
		}catch(Exception e){
			
		}
		
		
		
	}*/
	public void loadJobNumbers(){
		try{
			setLoggedData();
			List<String> jobNos = null;
			PcesthmtEjb pcesthmtEjb = new PcesthmtEjb(getRegion());
			List<Long> statuses = new ArrayList<Long>();
			statuses.add(new Long(EstimateStatus.JOB_ONGOING));
			//statuses.add(new Long(EstimateStatus.JOB_REJECTED));
			
			jobNos = pcesthmtEjb.findJobNosList(getCostCenterNo(),statuses); 
			/*List<String> jobNos1 = new ArrayList<String>();
			for(String jobNo:jobNos){
				if(jobNo != null && jobNo.contains("CNT")){
					jobNos1.add(jobNo);
				}
				
			}*/
			HttpServletRequest request = ServletActionContext.getRequest();
			request.getSession().setAttribute("revisejobNos",jobNos);
		}catch(Exception e){
			
		}
		
		
		
	}
	
	
	public void loadWorkEstimationRefNumbers(){
		try{
			setLoggedData();
			List<String> estimateNos = null;
			//EstimateEjb estimateEjb = new EstimateEjb(getRegion());
			PcesthttEjb ejb = new PcesthttEjb(getRegion());
			estimateNos = ejb.getEstimateNoList(getCostCenterNo(), Constants.REV_NO,new Long(EstimateStatus.MODIFIED));
			//estimationRefNos = estimateEjb.loadStandEstmatenos(getCostCenterNo(),ApplicationStatus.NEW_APPLICATION.getKey(),getRegion()); //SEND_FOR_CONSTRUCTION_MAINTENANCE
			HttpServletRequest request = ServletActionContext.getRequest();
			request.getSession().setAttribute("estimateNos",estimateNos);
		}catch(Exception e){
			
		}
		
		
		
	}
	public void loadWareHouse(){
		try{
			setLoggedData();
			List<String> warehouses = null;
			EstimateEjb estimateEjb = new EstimateEjb(getRegion());
			warehouses = estimateEjb.loadWarehouses(getCostCenterNo(),getRegion()); //SEND_FOR_CONSTRUCTION_MAINTENANCE
			HttpServletRequest request = ServletActionContext.getRequest();
			request.getSession().setAttribute("warehouses",warehouses);
		}catch(Exception e){
			
		}
		
		
		
	}
	
	public void loadFundSources(){
		try{
			setLoggedData();
			List<String> fundSources = null;
			EstimateEjb estimateEjb = new EstimateEjb(getRegion());
			fundSources = estimateEjb.getFundSources(getCostCenterNo(),getRegion()); //SEND_FOR_CONSTRUCTION_MAINTENANCE
			HttpServletRequest request = ServletActionContext.getRequest();
			request.getSession().setAttribute("fundsources",fundSources);
		}catch(Exception e){
			
		}
		
		
		
	}
	public void loadJobNumbersReviseModify(){
		try{
			setLoggedData();
			List<String> jobNos = null;
			PcesthmtEjb pcesthmtEjb = new PcesthmtEjb(getRegion());
			jobNos = pcesthmtEjb.findJobNoList(getCostCenterNo(),new Long(EstimateStatus.UPDATED_REVISED_JOB));
			//statuses.add(new Long(EstimateStatus.JOB_ONGOING));
			HttpServletRequest request = ServletActionContext.getRequest();
			request.getSession().setAttribute("revisejobNos",jobNos);
		}catch(Exception e){
			
		}
		
		
		
	}
	public String initUpdate()
	{
		errorMessages.clear();
		setLoggedData();
		loadJobNumbersReviseModify();
		loadWareHouse();
		loadFundSources();
		return SUCCESS;
	}
	/*public void loadFundIds(){
		try{
			List<String> fundIds = null;
			EstimateEjb estimateEjb = new EstimateEjb(getRegion());
			fundIds = estimateEjb.getFundIds(getCostCenterNo(),getRegion()); //SEND_FOR_CONSTRUCTION_MAINTENANCE
			HttpServletRequest request = ServletActionContext.getRequest();
			request.getSession().setAttribute("fundIds",fundIds);
		}catch(Exception e){
			
		}
		
		
		
	}*/
	public String close() {
		
		return "close";
	}
	/*public String saveDetailEstimation(){
		try{
			List<Pcestdtt> delist = null;
			List<DetailEstimateDTO> detailEstimateDTOlist = null;
			List<DetailEstimateDTO> pointDmtList = null;
			List<DetailEstimateDTO> allList = new ArrayList<DetailEstimateDTO>();
			HttpServletRequest request = ServletActionContext.getRequest();
			String region= (String) request.getSession().getAttribute("region");
			String costCenterNo=(String) request.getSession().getAttribute("costCenterNo");
			String userId= (String) request.getSession().getAttribute("userName");
			EstimateEjb estimateEjb = new EstimateEjb(region); 
			WiringLandDetailEjb wiringLandDetailEjb= new WiringLandDetailEjb(region);
			ApplicationEjb applicationEjb = new ApplicationEjb(region);
			PivDetailEjb pivejb =new PivDetailEjb(region);
			//if(validateForm()){
				selectedPegItemList = (List<String>) request.getSession().getAttribute("selectedpegIdList");
				

				selectedPegschuleItems = (Map<String, Pegschdmt>) request.getSession().getAttribute("pegItemDetails");
				
				
				if(selectedPegschuleItems != null && selectedPegschuleItems.size() > 0){
					PegschdmtEjb pegschdmtEjbejb = new PegschdmtEjb(region);
					
					List<Pegschdmt> list = new ArrayList<Pegschdmt>(selectedPegschuleItems.values());

					
					for(Pegschdmt peg : list ){
						pegschdmtEjbejb.insert(peg, region);
					}
					//pegschdmtEjbejb.insert(peg, region);
				}
				
				allSelectedAdditionalResoMap = (Map<String, DetailEstimateDTO>) request.getSession().getAttribute("allSelectedAdditionalResources");
				//addedItemsFromPegSchedule = (Map<String,DetailEstimateDTO>) request.getSession().getAttribute("detailEstimationDetails");
				
	
				
				//if((selectedAdditionalMaterials != null && selectedAdditionalMaterials.size() > 0))
				//	detailEstimateDTOlist = new ArrayList<DetailEstimateDTO>(selectedAdditionalMaterials.values());
				//}
				if(allSelectedAdditionalResoMap != null && allSelectedAdditionalResoMap.size() > 0){
					pointDmtList = new ArrayList<DetailEstimateDTO>(allSelectedAdditionalResoMap.values());
				}
					
					//if(detailEstimateDTOlist != null){
					//	allList.addAll(detailEstimateDTOlist);
					//}
				if(pointDmtList != null){
					delist = populatePcestdtts(costCenterNo,pointDmtList);
				}
			
					
					
				
				
		        
		            pcestdttItemByItemDataProvider.setValue("res_type",selectedRowKey, drpDwnResType.getSelected());
		            pcestdttItemByItemDataProvider.setValue("res_cd",selectedRowKey, drpDwnResCode.getSelected());
		            pcestdttItemByItemDataProvider.setValue("res_cat",selectedRowKey,txtFldResCat.getText());
		            Number num = sps.util.Util.getNumberFromFormattedDoubleStr(""+txtFldEstimatedQty.getText());
		            Object estQty = new Double(num.doubleValue());
		            
		            pcestdttItemByItemDataProvider.setValue("estimate_qty",selectedRowKey, estQty);
		            pcestdttItemByItemDataProvider.setValue("tolerance",selectedRowKey, txtFldTollerance.getText());
		            pcestdttItemByItemDataProvider.setValue("unit_price", selectedRowKey,txtFldPrice.getText());
		            Number num1 = sps.util.Util.getNumberFromFormattedDoubleStr(""+txtFldEstimatedCost.getText());
		            Object estiCost = new Double(num1.doubleValue());
		            
		            pcestdttItemByItemDataProvider.setValue("estimate_cost", selectedRowKey,estiCost);
		            pcestdttItemByItemDataProvider.setValue("estimate_no",selectedRowKey,  txtFldEstNo.getText());
		            pcestdttItemByItemDataProvider.setValue("rev_no",selectedRowKey ,rev_no);
		            pcestdttItemByItemDataProvider.setValue("dept_id",selectedRowKey,  getSessionBean1().getLoggedInCostCenter());
		            pcestdttItemByItemDataProvider.setValue("gen_res",selectedRowKey,  gen_res);
		            pcestdttItemByItemDataProvider.setValue("uom", selectedRowKey,hdnFldUOM.getText());
		            pcestdttItemByItemDataProvider.setValue("norm_default", selectedRowKey, norm_default);
		            
		       
				
				
				Format format=new Format();
				PcesthttPK pcesthttPK = new PcesthttPK();
				pcesthttPK.setEstimateNo(getEstimateNo());
				pcesthttPK.setDeptId(costCenterNo);
				pcesthttPK.setRevNo(2);//for new methods-
				
				Pcesthtt pcesthtt = new Pcesthtt();
				pcesthtt.setId(pcesthttPK);
				
				pcesthtt.setPartialPmt("F");
				pcesthtt.setNormDefault(new BigDecimal(1));
				pcesthtt.setSubCont("T");
				pcesthtt.setControlled("T");
				pcesthtt.setPriority(new BigDecimal(0));
				pcesthtt.setAllocPaid(new BigDecimal(0));
				pcesthtt.setEstType("2");
				pcesthtt.setAllocSettle(new BigDecimal(0)); 
				if(getRebate() == null){
					pcesthtt.setPartialAmt(new BigDecimal(0));
					setRebate(new BigDecimal("0"));
				}else{
					pcesthtt.setPartialAmt(getRebate());
				}
				pcesthtt.setEtimateDt(new java.util.Date());
				pcesthtt.setStatus(new BigDecimal(EstimateStatus.NEW));
				pcesthtt.setEntBy(userId);
				pcesthtt.setEntDt(new java.util.Date());
				pcesthtt.setCatCd(getCategoryId());
				
				//pcesthtt.set
				pcesthtt.setConfDt(format.getDefaultDate());
				pcesthtt.setAprDt1(format.getDefaultDate());
				pcesthtt.setAprDt2(format.getDefaultDate());
				pcesthtt.setAprDt3(format.getDefaultDate());
				pcesthtt.setAprDt4(format.getDefaultDate());
				pcesthtt.setAprDt5(format.getDefaultDate());
				pcesthtt.setRejctDt(format.getDefaultDate());
				pcesthtt.setReviseDt(format.getDefaultDate());
				pcesthtt.setDescr(getDescription());
				//pcesthtt.setStdCost(new BigDecimal(Double.toString(nf.parse(getTotalCost()).doubleValue())));
				if(getTotalCost() == null){
					setTotalCost(new BigDecimal("0"));
				}
				pcesthtt.setStdCost(getTotalCost());
				
				if(getFundid()!=null && getFundsou().length()>0)
				{
					
					pcesthtt.setFundSource(getFundsou());
					pcesthtt.setFundId(getFundid());
				}
				TempTb tb = new TempTb();
				tb.setDeptId(getCostCenterNo());
				tb.setEstimateNo(getEstimateNo());
				if(getAmount().equals("")){
					setAmount("0");
				}
				tb.setPivAmount(new BigDecimal(getAmount()));
				tb.setPivDate(format.getDefaultDate());
				tb.setPivNo(getPivNo());
				//tb.setRowId(get);
				tb.setStatus("U");
				
				estimateEjb.insertDetailEst(pcesthtt,delist,tb,region);
				estimateEjb.updateDetailEstimateCost(getEstimateNo(), costCenterNo, region);
				//estimateEjb.addAdditionalCost(getEstimateNo(),costCenterNo,getTotalCost(),region);
				clearForm();
				
			//}else{
				StringBuffer bf = new StringBuffer();
				bf.append("Enter required details");
				for(String errorMessage :errorMessages){
					bf.append(errorMessage +", ");
					
				}
				setLblError(bf.toString());
				return SUCCESS;
			//}
			
		}catch(Exception e){
			e.printStackTrace();
			setMessageType(MSG_ERROR);
			setErrorMessage("Error Occured in Saving Estimation Details");
			//setLblError("Error Occured in Saving Estimation Details");
			return SUCCESS;
		}
		setMessageType(MSG_DONE);
		setErrorMessage("Successfully saved Detail Estimation Details");
		//setLblError("Successfully saved Detail Estimation Details");
		return SUCCESS;
		
	}*/
	
	private boolean validateForm(){
		if(getApplicationId()== null){
			setLblError("Please enter Application Id");
			errorMessages.add("Application Id");
			return false;
		}
		if(getEstimateNo() == null){
			setLblError("Please enter PIV No");
			errorMessages.add("PIV No");
			return false;
		}
		
		
		
		return true;
		
	}
	private List<Pcestdmt> populatePcestdmts(Pcesthmt pchmt,String costCenter,List<DetailEstimateDTO> detaList){
		
	 	//Long revNo = 2l;
        String genRes = "F";
        String normDefault = "F";
	       
		List<Pcestdmt> lst = new ArrayList<Pcestdmt>();
		if(detaList != null){
			for(DetailEstimateDTO dto :detaList){
				Pcestdmt pcestdmt = new Pcestdmt();
				
				PcestdmtPK pcestdmtPk = new PcestdmtPK();
				pcestdmtPk.setDeptId(costCenter);
				pcestdmtPk.setEstimateNo(pchmt.getId().getEstimateNo().trim());
				pcestdmtPk.setResCd(dto.getResourceCode());
				pcestdmtPk.setRevNo(pchmt.getId().getRevNo());
				
				pcestdmt.setId(pcestdmtPk);
				if(dto.getResCategory() != null){
					pcestdmt.setResCat(new BigDecimal(dto.getResCategory()));
				}else{
					pcestdmt.setResCat(null);
				}
				//pcestdtt.setResCat(new BigDecimal(dto.getResCategory()));
				pcestdmt.setEstimateCost(dto.getEstimateCost());
				pcestdmt.setEstimateQty(dto.getEstimatedQuantity());
				pcestdmt.setResType(dto.getResourceType());
				pcestdmt.setUom(dto.getUom());
				pcestdmt.setUnitPrice(dto.getUnitPrice());
				pcestdmt.setNormDefault(normDefault);
				pcestdmt.setGenRes(genRes);
				lst.add(pcestdmt);
			}
		}
		
		
		
		return lst;
		
	}
	private Pcestdmt setUpdatedDtts(Pcestdmt dmt,DetailEstimateDTO dto){
		
	 	
		List<Pcestdmt> lst = new ArrayList<Pcestdmt>();
		if(dmt != null){
			if(dto != null){
				
				dmt.setEstimateCost(dto.getEstimateCost());
				dmt.setEstimateQty(dto.getEstimatedQuantity());
		
			}
		}
	
		return dmt;
		
	}
	public String revise(){
		setLoggedData();
		List<Pcestdmt> delist = null;
		List<DetailEstimateDTO> detailEstimateDTOlist = null;
		List<DetailEstimateDTO> pointDmtList = null;
		List<Pcestdmt> addedList = null;
		List<Pcestdmt> updatedList = new ArrayList<Pcestdmt>();
		List<Pcestdmt> deletedList = new ArrayList<Pcestdmt>();
		List<DetailEstimateDTO> allList = new ArrayList<DetailEstimateDTO>();
		HttpServletRequest request = ServletActionContext.getRequest();
		try {
		JobEjb jobEjb = new JobEjb(region); 
		WiringLandDetailEjb wiringLandDetailEjb= new WiringLandDetailEjb(region);
		ApplicationEjb applicationEjb = new ApplicationEjb(region);
		PivDetailEjb pivejb =new PivDetailEjb(region);
		//if(validateForm()){
			//selectedPegItemList = (List<String>) request.getSession().getAttribute("selectedpegIdList");
		String smcType= (String) request.getSession().getAttribute("smcType");
		String estimateType= (String) request.getSession().getAttribute("estimateType");
		String branchType= (String) request.getSession().getAttribute("branchType");
	

			selectedPegschuleItems = (Map<String, Pegschdmt>) request.getSession().getAttribute("pegItemDetails");
			
			
			if(selectedPegschuleItems != null && selectedPegschuleItems.size() > 0){
				PegschdmtEjb pegschdmtEjbejb = new PegschdmtEjb(region);
				
				List<Pegschdmt> list = new ArrayList<Pegschdmt>(selectedPegschuleItems.values());

				
				for(Pegschdmt peg : list ){
					//pegschdmtEjbejb.insert(peg, region);
				}
				//pegschdmtEjbejb.insert(peg, region);
			}
			
			resourceMap = (Map<String, DetailEstimateDTO>) request.getSession().getAttribute("detailEstimationDetails");
			//addedItemsFromPegSchedule = (Map<String,DetailEstimateDTO>) request.getSession().getAttribute("detailEstimationDetails");
			
			updatedDetailsMap = (Map<String, DetailEstimateDTO>) request.getSession().getAttribute("updatedDetails");
			
			deletedDetailsMap = (Map<String, DetailEstimateDTO>) request.getSession().getAttribute("deletedDetails");
			
			alreadyAddedDetails = (Map<String,DetailEstimateDTO>) request.getSession().getAttribute("alreadyAddedDetails");
			
			
			
		
			Format format=new Format();
			EstimateDetails dto = (EstimateDetails) request.getSession().getAttribute("estimateDetailobject");
			if(dto != null){
				Pcesthmt pchmt = dto.getHmt();
				long newRevNo = pchmt.getId().getRevNo();
				if(pchmt != null){
					//pchmt.getId().setRevNo(newRevNo+1);
					if(getRebate().equalsIgnoreCase(Constants.DEFAULT_STRING) || getRebate().equalsIgnoreCase("undefined")){
						setRebate("0");
					}
					pchmt.setPartialAmt(new BigDecimal(getRebate()));
					pchmt.setEtimateDt(new java.util.Date());
					pchmt.setStatus(new Long(EstimateStatus.UPDATED_REVISED_JOB));
					pchmt.setEntBy(userId);
					pchmt.setEntDt(new java.util.Date());
					pchmt.setCatCd(getCategoryId());
					pchmt.setRevReason(getReviseReason());
					//pcesthtt.set
					pchmt.setConfDt(format.getDefaultDate());
					pchmt.setAprDt1(format.getDefaultDate());
					pchmt.setAprDt2(format.getDefaultDate());
					pchmt.setAprDt3(format.getDefaultDate());
					pchmt.setAprDt4(format.getDefaultDate());
					pchmt.setAprDt5(format.getDefaultDate());
					pchmt.setRejctDt(format.getDefaultDate());
					pchmt.setReviseDt(format.getDefaultDate());
					pchmt.setDescr(getDescription());
					pchmt.setRevReason(getReviseReason());
					
					//pchmt.setRevReason(getReviseReason());
					//pcesthtt.setStdCost(new BigDecimal(Double.toString(nf.parse(getTotalCost()).doubleValue())));
					if(getTotalCost().equalsIgnoreCase(Constants.DEFAULT_STRING) || getTotalCost().equalsIgnoreCase("undefined")){
						setTotalCost("0");
					}
					pchmt.setStdCost(new BigDecimal(getTotalCost()));
					
					if(getFundid()!=null && getFundsou().length()>0)
					{
						
						pchmt.setFundSource(getFundsou().trim());
						pchmt.setFundId(getFundid().trim());
					}
				}
				
				
				/*TempTb tb = dto.getTb();
				if(tb != null){
					
					tb.setPivAmount(new BigDecimal(getAmount()));
					tb.setPivDate(format.getDefaultDate());
					tb.setPivNo(getPivNo());
					
				}*/
				if(resourceMap != null && resourceMap.size() > 0){
					addedList = populatePcestdmts(pchmt,costCenterNo,new ArrayList<DetailEstimateDTO>(resourceMap.values()));
				}
				
				List<Pcestdmt> list = dto.getDmts();
				if(list != null && list.size() > 0){
					
					for(Pcestdmt dmt : list){
						if(updatedDetailsMap != null && updatedDetailsMap.containsKey(dmt.getId().getResCd().trim())){
							setUpdatedDtts(dmt,updatedDetailsMap.get(dmt.getId().getResCd().trim()));
							updatedList.add(dmt);
						}
						if(deletedDetailsMap != null && deletedDetailsMap.containsKey(dmt.getId().getResCd().trim())){
							deletedList.add(dmt);
						}
					}
					
					
				}
				/*double totalCost = detailEstimateUpdatedTotal(getTotalCost().doubleValue(),dto.getDtts(),addedList,updatedDetailsMap,deletedDetailsMap);
				if(totalCost != 0.0){
					pchmt.setStdCost(new BigDecimal(totalCost));
				}*/
				
				
					jobEjb.updateReviseDetailsWithRevNo(pchmt, addedList, updatedList,deletedList);
					if(branchType != null && branchType.equalsIgnoreCase(Constants.BRANCHTYPE_GOVERNMENT_INSTALLATION)){
						estimateType = smcType;
					}
					if(estimateType == null){
						estimateType = Constants.DEFAULT_ESTIMATE_TYPE;
					}
					//jobEjb.updateAdditionalCostForRevisejob(pchmt, costCenterNo,pchmt.getStdCost());
					jobEjb.updateRevisedEstimateCost(pchmt,smcType, costCenterNo);
					loadJobNumbers();
					clearForm();
					setMessageType(MSG_DONE);
					setErrorMessage("Successfully saved Revised Job Details");
					//setLblError("Successfully saved Detail Estimation Details");
					return SUCCESS;
			}
		}catch (RollbackException e) {
			loadJobNumbers();
			e.printStackTrace();
			System.out.println("ERROR MESSEGE" + e.getMessage());
			setErrorMessage(e.getMessage());
			return ERROR;
		} catch (PersistenceException e) {
			loadJobNumbers();
			e.printStackTrace();
			System.out.println("ERROR MESSEGE" + e.getMessage());
			setErrorMessage(e.getMessage());
			return ERROR;
		} catch (Exception e) {
			loadJobNumbers();
			e.printStackTrace();
			System.out.println("ERROR MESSEGE" + e.getMessage());
			setErrorMessage(e.getMessage());
			return ERROR;
		} finally{
			//getEm().close();
			//em=null;
			//getEmf().close();
			//emf=null;
			//format=null;
		} 
			//estimateEjb.addAdditionalCost(getEstimateNo(),costCenterNo,getTotalCost(),region);
			
			return SUCCESS;

		
	}
	
	
	public String updateRevise(){
		setLoggedData();
		List<Pcestdmt> delist = null;
		List<DetailEstimateDTO> detailEstimateDTOlist = null;
		List<DetailEstimateDTO> pointDmtList = null;
		List<Pcestdmt> addedList = null;
		List<Pcestdmt> updatedList = new ArrayList<Pcestdmt>();
		List<Pcestdmt> deletedList = new ArrayList<Pcestdmt>();
		List<DetailEstimateDTO> allList = new ArrayList<DetailEstimateDTO>();
		HttpServletRequest request = ServletActionContext.getRequest();
		try {
		JobEjb jobEjb = new JobEjb(region); 
		WiringLandDetailEjb wiringLandDetailEjb= new WiringLandDetailEjb(region);
		ApplicationEjb applicationEjb = new ApplicationEjb(region);
		PivDetailEjb pivejb =new PivDetailEjb(region);
		//if(validateForm()){
			//selectedPegItemList = (List<String>) request.getSession().getAttribute("selectedpegIdList");
		String smcType= (String) request.getSession().getAttribute("smcType");
		String estimateType= (String) request.getSession().getAttribute("estimateType");
		String branchType= (String) request.getSession().getAttribute("branchType");
	

			selectedPegschuleItems = (Map<String, Pegschdmt>) request.getSession().getAttribute("pegItemDetails");
			
			
			if(selectedPegschuleItems != null && selectedPegschuleItems.size() > 0){
				PegschdmtEjb pegschdmtEjbejb = new PegschdmtEjb(region);
				
				List<Pegschdmt> list = new ArrayList<Pegschdmt>(selectedPegschuleItems.values());

				
				for(Pegschdmt peg : list ){
					//pegschdmtEjbejb.insert(peg, region);
				}
				//pegschdmtEjbejb.insert(peg, region);
			}
			
			resourceMap = (Map<String, DetailEstimateDTO>) request.getSession().getAttribute("detailEstimationDetails");
			//addedItemsFromPegSchedule = (Map<String,DetailEstimateDTO>) request.getSession().getAttribute("detailEstimationDetails");
			
			updatedDetailsMap = (Map<String, DetailEstimateDTO>) request.getSession().getAttribute("updatedDetails");
			
			deletedDetailsMap = (Map<String, DetailEstimateDTO>) request.getSession().getAttribute("deletedDetails");
			
			alreadyAddedDetails = (Map<String,DetailEstimateDTO>) request.getSession().getAttribute("alreadyAddedDetails");
			
			
			
		
			Format format=new Format();
			EstimateDetails dto = (EstimateDetails) request.getSession().getAttribute("estimateDetailobject");
			if(dto != null){
				Pcesthmt pchmt = dto.getHmt();
				long newRevNo = pchmt.getId().getRevNo();
				if(pchmt != null){
					//pchmt.getId().setRevNo(newRevNo+1);
				
					pchmt.setPartialAmt(new BigDecimal(getRebate()));
					pchmt.setEtimateDt(new java.util.Date());
					pchmt.setStatus(new Long(EstimateStatus.UPDATED_REVISED_JOB));
					pchmt.setEntBy(userId);
					pchmt.setEntDt(new java.util.Date());
					pchmt.setCatCd(getCategoryId());
					pchmt.setRevReason(getReviseReason());
					//pcesthtt.set
					pchmt.setConfDt(format.getDefaultDate());
					pchmt.setAprDt1(format.getDefaultDate());
					pchmt.setAprDt2(format.getDefaultDate());
					pchmt.setAprDt3(format.getDefaultDate());
					pchmt.setAprDt4(format.getDefaultDate());
					pchmt.setAprDt5(format.getDefaultDate());
					pchmt.setRejctDt(format.getDefaultDate());
					pchmt.setReviseDt(new java.util.Date());
					pchmt.setDescr(getDescription());
					pchmt.setRevReason(getReviseReason());
					
					//pchmt.setRevReason(getReviseReason());
					//pcesthtt.setStdCost(new BigDecimal(Double.toString(nf.parse(getTotalCost()).doubleValue())));
					if(getTotalCost().equalsIgnoreCase(Constants.DEFAULT_STRING) || getTotalCost().equalsIgnoreCase("undefined")){
						setTotalCost("0");
					}
					pchmt.setStdCost(new BigDecimal(getTotalCost()));
					
					if(getFundid()!=null && getFundsou().length()>0)
					{
						
						pchmt.setFundSource(getFundsou().trim());
						pchmt.setFundId(getFundid().trim());
					}
				}
				
				
				/*TempTb tb = dto.getTb();
				if(tb != null){
					
					tb.setPivAmount(new BigDecimal(getAmount()));
					tb.setPivDate(format.getDefaultDate());
					tb.setPivNo(getPivNo());
					
				}*/
				if(resourceMap != null && resourceMap.size() > 0){
					addedList = populatePcestdmts(pchmt,costCenterNo,new ArrayList<DetailEstimateDTO>(resourceMap.values()));
				}
				
				List<Pcestdmt> list = dto.getDmts();
				if(list != null && list.size() > 0){
					
					for(Pcestdmt dmt : list){
						if(updatedDetailsMap != null && updatedDetailsMap.containsKey(dmt.getId().getResCd().trim())){
							setUpdatedDtts(dmt,updatedDetailsMap.get(dmt.getId().getResCd().trim()));
							updatedList.add(dmt);
						}
						if(deletedDetailsMap != null && deletedDetailsMap.containsKey(dmt.getId().getResCd().trim())){
							deletedList.add(dmt);
						}
					}
					
					
				}
				/*double totalCost = detailEstimateUpdatedTotal(getTotalCost().doubleValue(),dto.getDtts(),addedList,updatedDetailsMap,deletedDetailsMap);
				if(totalCost != 0.0){
					pchmt.setStdCost(new BigDecimal(totalCost));
				}*/
				
				
					jobEjb.updateReviseDetails(pchmt, addedList, updatedList,deletedList);
					if(branchType != null && branchType.equalsIgnoreCase(Constants.BRANCHTYPE_GOVERNMENT_INSTALLATION)){
						estimateType = smcType;
					}
					if(estimateType == null){
						estimateType = Constants.DEFAULT_ESTIMATE_TYPE;
					}
					
					//jobEjb.updateAdditionalCostForRevisejob(pchmt, costCenterNo,pchmt.getStdCost());
					jobEjb.updateRevisedEstimateCost(pchmt,estimateType, costCenterNo);
					loadJobNumbersReviseModify();
					clearForm();
					setMessageType(MSG_DONE);
					setErrorMessage("Successfully saved Revised Job Details");
					//setLblError("Successfully saved Detail Estimation Details");
					
				
			}
		}catch (RollbackException e) {
			loadJobNumbersReviseModify();
			e.printStackTrace();
			System.out.println("ERROR MESSEGE" + e.getMessage());
			setErrorMessage(e.getMessage());
			return ERROR;
		} catch (PersistenceException e) {
			loadJobNumbersReviseModify();
			e.printStackTrace();
			System.out.println("ERROR MESSEGE" + e.getMessage());
			setErrorMessage(e.getMessage());
			return ERROR;
		} catch (Exception e) {
			loadJobNumbersReviseModify();
			e.printStackTrace();
			System.out.println("ERROR MESSEGE" + e.getMessage());
			setErrorMessage(e.getMessage());
			return ERROR;
		} finally{
			//getEm().close();
			//em=null;
			//getEmf().close();
			//emf=null;
			//format=null;
		} 
			return SUCCESS;
			
			
			
			//estimateEjb.addAdditionalCost(getEstimateNo(),costCenterNo,getTotalCost(),region);
			
			

		
	}
	public String RevisePrice(){
		setLoggedData();
		List<Pcestdmt> delist = null;
		List<DetailEstimateDTO> detailEstimateDTOlist = null;
		List<DetailEstimateDTO> pointDmtList = null;
		List<Pcestdmt> addedList = null;
		List<Pcestdmt> updatedList = new ArrayList<Pcestdmt>();
		List<Pcestdmt> deletedList = new ArrayList<Pcestdmt>();
		List<DetailEstimateDTO> allList = new ArrayList<DetailEstimateDTO>();
		HttpServletRequest request = ServletActionContext.getRequest();
		try {
		JobEjb jobEjb = new JobEjb(region); 
		WiringLandDetailEjb wiringLandDetailEjb= new WiringLandDetailEjb(region);
		ApplicationEjb applicationEjb = new ApplicationEjb(region);
		PivDetailEjb pivejb =new PivDetailEjb(region);
		//if(validateForm()){
			//selectedPegItemList = (List<String>) request.getSession().getAttribute("selectedpegIdList");
		String smcType= (String) request.getSession().getAttribute("smcType");
		
	

		
			Format format=new Format();
			EstimateDetails dto = (EstimateDetails) request.getSession().getAttribute("estimateDetailobject");
			if(dto != null){
				Pcesthmt pchmt = dto.getHmt();
				long newRevNo = pchmt.getId().getRevNo();
				if(pchmt != null){
					//pchmt.getId().setRevNo(newRevNo+1);
				
					pchmt.setPartialAmt(new BigDecimal(getRebate()));
					pchmt.setEtimateDt(new java.util.Date());
					pchmt.setStatus(pchmt.getStatus());
					pchmt.setEntBy(userId);
					pchmt.setEntDt(new java.util.Date());
					pchmt.setCatCd(getCategoryId());
					pchmt.setRevReason(getReviseReason());
					//pcesthtt.set
					pchmt.setConfDt(format.getDefaultDate());
					pchmt.setAprDt1(format.getDefaultDate());
					pchmt.setAprDt2(format.getDefaultDate());
					pchmt.setAprDt3(format.getDefaultDate());
					pchmt.setAprDt4(format.getDefaultDate());
					pchmt.setAprDt5(format.getDefaultDate());
					pchmt.setRejctDt(format.getDefaultDate());
					pchmt.setReviseDt(new java.util.Date());
					pchmt.setDescr(getDescription());
					pchmt.setRevReason(getReviseReason());
					
					//pchmt.setRevReason(getReviseReason());
					//pcesthtt.setStdCost(new BigDecimal(Double.toString(nf.parse(getTotalCost()).doubleValue())));
					if(getTotalCost().equalsIgnoreCase(Constants.DEFAULT_STRING) || getTotalCost().equalsIgnoreCase("undefined")){
						setTotalCost("0");
					}
					pchmt.setStdCost(new BigDecimal(getTotalCost()));
					
					if(getFundid()!=null && getFundsou().length()>0)
					{
						
						pchmt.setFundSource(getFundsou().trim());
						pchmt.setFundId(getFundid().trim());
					}
				}
				
				
				/*TempTb tb = dto.getTb();
				if(tb != null){
					
					tb.setPivAmount(new BigDecimal(getAmount()));
					tb.setPivDate(format.getDefaultDate());
					tb.setPivNo(getPivNo());
					
				}*/
				if(resourceMap != null && resourceMap.size() > 0){
					addedList = populatePcestdmts(pchmt,costCenterNo,new ArrayList<DetailEstimateDTO>(resourceMap.values()));
				}
				
				List<Pcestdmt> list = dto.getDmts();
				if(list != null && list.size() > 0){
					
					for(Pcestdmt dmt : list){
						if(dmt != null){
							if(dmt != null && (dmt.getResType().trim().equalsIgnoreCase("MAT-COST") || dmt.getResType().trim().equalsIgnoreCase("MAT-COST-OTHER)"))){
								setPrice(dmt);
								updatedList.add(dmt);
							}
						}
					}
					
					
				}
				/*double totalCost = detailEstimateUpdatedTotal(getTotalCost().doubleValue(),dto.getDtts(),addedList,updatedDetailsMap,deletedDetailsMap);
				if(totalCost != 0.0){
					pchmt.setStdCost(new BigDecimal(totalCost));
				}*/
				
				
					jobEjb.updateReviseDetails(pchmt, null, updatedList,null);
					
					//jobEjb.updateAdditionalCostForRevisejob(pchmt, costCenterNo,pchmt.getStdCost());
					jobEjb.updateRevisedEstimateCost(pchmt,smcType, costCenterNo);
					loadJobNumbersReviseModify();
					clearForm();
					setMessageType(MSG_DONE);
					setErrorMessage("Successfully saved Revised Job Details");
					//setLblError("Successfully saved Detail Estimation Details");
					
				
			}
		}catch (RollbackException e) {
			loadJobNumbersReviseModify();
			e.printStackTrace();
			System.out.println("ERROR MESSEGE" + e.getMessage());
			setErrorMessage(e.getMessage());
			return ERROR;
		} catch (PersistenceException e) {
			loadJobNumbersReviseModify();
			e.printStackTrace();
			System.out.println("ERROR MESSEGE" + e.getMessage());
			setErrorMessage(e.getMessage());
			return ERROR;
		} catch (Exception e) {
			loadJobNumbersReviseModify();
			e.printStackTrace();
			System.out.println("ERROR MESSEGE" + e.getMessage());
			setErrorMessage(e.getMessage());
			return ERROR;
		} finally{
			//getEm().close();
			//em=null;
			//getEmf().close();
			//emf=null;
			//format=null;
		} 
			return SUCCESS;
			
			
			
			//estimateEjb.addAdditionalCost(getEstimateNo(),costCenterNo,getTotalCost(),region);
			
			

		
	}
	private Pcestdmt setPrice(Pcestdmt dtt){
		String warehouseId = null;
		InwrhmtmEjb ejb=new InwrhmtmEjb("R3");
	 	if(dtt.getId().getDeptId().equalsIgnoreCase("550.20")){
	 		warehouseId = "550.11";
	 	}else if(dtt.getId().getDeptId().equalsIgnoreCase("510.20")){
	 		warehouseId = "510.11";
	 	}
	 	BigDecimal price = new BigDecimal("0");
		List<Pcestdmt> lst = new ArrayList<Pcestdmt>();
		if(dtt != null){
			
				price = ejb.findUPByMatCd(dtt.getId().getResCd().trim(), warehouseId);
				if(price == null){
					price = ejb.findUPByMatCd(dtt.getId().getResCd().trim(), "550.12");
				}
				dtt.setUnitPrice(price);
				dtt.setEstimateCost(new BigDecimal(dtt.getUnitPrice().doubleValue()*dtt.getEstimateQty().doubleValue()));
				
				
		
		}
	
		return dtt;
		
	}
	public String getProjectNo() {
		return projectNo;
	}
	public void setProjectNo(String projectNo) {
		this.projectNo = projectNo;
	}
	public String getEstimateNo() {
		return estimateNo;
	}

	public void setEstimateNo(String estimateNo) {
		this.estimateNo = estimateNo;
	}

	public String getFileRef() {
		return fileRef;
	}

	public void setFileRef(String fileRef) {
		this.fileRef = fileRef;
	}

	public String getPivNo() {
		return pivNo;
	}

	public void setPivNo(String pivNo) {
		this.pivNo = pivNo;
	}

	public String getWareHouseId() {
		return wareHouseId;
	}

	public void setWareHouseId(String wareHouseId) {
		this.wareHouseId = wareHouseId;
	}

	

	public List<Pcesthmt> getJobList() {
		return JobList;
	}
	public void setJobList(List<Pcesthmt> jobList) {
		JobList = jobList;
	}
	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	

	public String getPivDatePicker() {
		return pivDatePicker;
	}

	public void setPivDatePicker(String pivDatePicker) {
		this.pivDatePicker = pivDatePicker;
	}

	public String getEstimateDate() {
		return estimateDate;
	}

	public void setEstimateDate(String estimateDate) {
		this.estimateDate = estimateDate;
	}

	public String getFundsou() {
		return fundsou;
	}

	public void setFundsou(String fundsou) {
		this.fundsou = fundsou;
	}

	public String getRebate() {
		return rebate;
	}

	public void setRebate(String rebate) {
		this.rebate = rebate;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getFundid() {
		return fundid;
	}

	public void setFundid(String fundid) {
		this.fundid = fundid;
	}

	public NumberFormat getNf() {
		return nf;
	}
	public void setNf(NumberFormat nf) {
		this.nf = nf;
	}
	public String getSelectCategoryString() {
		return selectCategoryString;
	}
	public void setSelectCategoryString(String selectCategoryString) {
		this.selectCategoryString = selectCategoryString;
	}
	public String getSelectedReport() {
		return selectedReport;
	}
	public void setSelectedReport(String selectedReport) {
		this.selectedReport = selectedReport;
	}
	public InputStream getFileInputStream() {
		return fileInputStream;
	}
	public void setFileInputStream(InputStream fileInputStream) {
		this.fileInputStream = fileInputStream;
	}
	public static String getViewpath() {
		return viewPath;
	}
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<String> getSelectedPegItemList() {
		return selectedPegItemList;
	}

	public void setSelectedPegItemList(List<String> selectedPegItemList) {
		this.selectedPegItemList = selectedPegItemList;
	}

	public Map<String, DetailEstimateDTO> getSelectedAdditionalMaterials() {
		return selectedAdditionalMaterials;
	}

	public void setSelectedAdditionalMaterials(
			Map<String, DetailEstimateDTO> selectedAdditionalMaterials) {
		this.selectedAdditionalMaterials = selectedAdditionalMaterials;
	}

	public Map<String, DetailEstimateDTO> getAddedItemsFromPegSchedule() {
		return addedItemsFromPegSchedule;
	}

	public void setAddedItemsFromPegSchedule(
			Map<String, DetailEstimateDTO> addedItemsFromPegSchedule) {
		this.addedItemsFromPegSchedule = addedItemsFromPegSchedule;
	}

	public String getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}

	public String getLoggedInUserLevel() {
		return loggedInUserLevel;
	}
	public void setLoggedInUserLevel(String loggedInUserLevel) {
		this.loggedInUserLevel = loggedInUserLevel;
	}
	public String getApplicationType() {
		return applicationType;
	}
	public void setApplicationType(String applicationType) {
		this.applicationType = applicationType;
	}
	public Map<String, String[]> getParameters() {
		return parameters;
	}
	/*public String updateEstimation(){
		try{
			//EstimateEjb estimateEjb = new EstimateEjb(getRegion());
			//applicationRefNos = estimateEjb.loadApplicationRefnos(getUserId(),getCostCenterNo()); 
			//HttpServletRequest request = ServletActionContext.getRequest();
			//request.getSession().setAttribute("applicationRefNos",applicationRefNos);
			
			
			//System.out.println(getSecDeposit());
			//System.out.println(getSinNo());
			Spstdesthmt spstdesthmt = new Spstdesthmt();
			List<Spstdestdmt> lineTypeList = new ArrayList<Spstdestdmt>();
			SpstdesthmtPK id = new SpstdesthmtPK();
			id.setApplicationNo(getApplicationNo());
			id.setStdNo(getApplicationNo());
			spstdesthmt.setRejReason(getRejectedReason());
			spstdesthmt.setSecDeposit(getSecDeposit());
			spstdesthmt.setId(id);
			spstdesthmt.setDescription(getJobDescription());
			spstdesthmt.setTotalCost(getTotalCost());
			//spstdesthmt.setSinNo(getSinNo());
			HttpServletRequest request = ServletActionContext.getRequest();
			String region= (String) request.getSession().getAttribute("region");
			String costCenterNo=(String) request.getSession().getAttribute("costCenterNo");
			
			EstimateEjb estimateEjb = new EstimateEjb(region);
			WiringLandDetailEjb wiringLandDetailEjb= new WiringLandDetailEjb(region);
			ApplicationEjb applicationEjb = new ApplicationEjb(region);
			//PcestdttEjb pcestdttEjb = new PcestdttEjb();

			estimateEjb.updateStdEstimateDetails(getApplicationNo(),getSecDeposit(),getTotalCost(),region);
			
			Application application = applicationEjb.findByApplicationNo(applicationNo);
			
			wiringLandDetailEjb.updateDemand(application.getId().getApplicationId(),Long.parseLong(getDemand()),region);
			//loadEstimationRefNumbers();
			setLblSuccess("Application Details successfully updated");
			
		}catch(Exception e){
			//execute();
			setLblError("Error Occured in Updating Estimation Details");
			return SUCCESS;
		}
		
		return SUCCESS;
		
	}
		
	*/
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getCostCenterNo() {
		return costCenterNo;
	}
	public void setLblError(String lblError) {
		this.lblError = lblError;
	}

	public String getLblSuccess() {
		return lblSuccess;
	}
	public void setLblSuccess(String lblSuccess) {
		this.lblSuccess = lblSuccess;
	}
	public String getLblError() {
		return lblError;
	}
	public void setCostCenterNo(String costCenterNo) {
		this.costCenterNo = costCenterNo;
	}


	public String getCostCenterName() {
		return costCenterName;
	}


	public void setCostCenterName(String costCenterName) {
		this.costCenterName = costCenterName;
	}

	

	public String getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(String totalCost) {
		this.totalCost = totalCost;
	}


 
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
 
	public void setPassword(String password) {
		this.password = password;
	}

	private void clearForm(){
		HttpServletRequest request = ServletActionContext.getRequest();
		resourceMap = (Map<String, DetailEstimateDTO>) request.getSession().getAttribute("detailEstimationDetails");
		//addedItemsFromPegSchedule = (Map<String,DetailEstimateDTO>) request.getSession().getAttribute("detailEstimationDetails");
		if(resourceMap != null){
			resourceMap.clear();
		}
		request.getSession().setAttribute("detailEstimationDetails",resourceMap);
		
		updatedDetailsMap = (Map<String, DetailEstimateDTO>) request.getSession().getAttribute("updatedDetails");
		
		if(updatedDetailsMap != null){
			updatedDetailsMap.clear();
		}
		request.getSession().setAttribute("updatedDetails",updatedDetailsMap);
		
		deletedDetailsMap = (Map<String, DetailEstimateDTO>) request.getSession().getAttribute("deletedDetails");
		
		if(deletedDetailsMap != null){
			deletedDetailsMap.clear();
		}
		request.getSession().setAttribute("deletedDetailsMap",deletedDetailsMap);
		
		alreadyAddedDetails = (Map<String,DetailEstimateDTO>) request.getSession().getAttribute("alreadyAddedDetails");
		

		if(alreadyAddedDetails != null){
			alreadyAddedDetails.clear();
		}
		request.getSession().setAttribute("alreadyAddedDetails",alreadyAddedDetails);
		
	
		dto = (EstimateDetails) request.getSession().getAttribute("estimateDetailobject");
		if(dto != null){
			dto = null;
		}
		request.getSession().setAttribute("estimateDetailobject",dto);
		
		selectedPegItemList = (List<String>) request.getSession().getAttribute("selectedpegIdList");
		if(dto != null){
			dto = null;
		}
		request.getSession().setAttribute("estimateDetailobject",dto);
		

		selectedPegschuleItems = (Map<String, Pegschdmt>) request.getSession().getAttribute("pegItemDetails");
		if(selectedPegschuleItems != null){
			selectedPegschuleItems.clear();
		}
		request.getSession().setAttribute("pegItemDetails",selectedPegschuleItems);
	
		allSelectedAdditionalResoMap = (Map<String, DetailEstimateDTO>) request.getSession().getAttribute("allSelectedAdditionalResources");

		if(allSelectedAdditionalResoMap != null){
			allSelectedAdditionalResoMap.clear();
		}
		request.getSession().setAttribute("allSelectedAdditionalResources",allSelectedAdditionalResoMap);
		
		request.getSession().setAttribute("selectedpegIdList",null);
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public String getMessageType() {
		return messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

	public List<String> getErrorMessages() {
		return errorMessages;
	}

	public void setErrorMessages(List<String> errorMessages) {
		this.errorMessages = errorMessages;
	}
	
	public String getHiddenJobNo() {
		return hiddenJobNo;
	}
	public void setHiddenJobNo(String hiddenJobNo) {
		this.hiddenJobNo = hiddenJobNo;
	}
	@Override
	public void setParameters(Map<String, String[]> parameters) {
		// TODO Auto-generated method stub
		this.parameters=parameters;
	}


	public String getSessionKey(String key) {
		return getSession().get(key).toString();
	}

	public String getLoggedInUserId() {
		return loggedInUserId;
	}

	public void setLoggedInUserId(String loggedInUserId) {
		this.loggedInUserId = loggedInUserId;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	public String getReviseReason() {
		return reviseReason;
	}
	public void setReviseReason(String reviseReason) {
		this.reviseReason = reviseReason;
	}
	
	public String getRejectReason() {
		return rejectReason;
	}
	public void setRejectReason(String rejectReason) {
		this.rejectReason = rejectReason;
	}
	
	public String getDivSec() {
		return divSec;
	}
	public void setDivSec(String divSec) {
		this.divSec = divSec;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String geteCSC() {
		return eCSC;
	}
	public void seteCSC(String eCSC) {
		this.eCSC = eCSC;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getEsname() {
		return esname;
	}
	public void setEsname(String esname) {
		this.esname = esname;
	}
	public String newDirect()
	{
		clearForm();
		setErrorMessage(null);
		errorMessages.clear();
		setLoggedData();
		//loadEstimationRefNumbers();//to load Application status to be changed
		//loadWorkEstimationRefNumbers();
		loadWareHouse();
		loadFundSources();
		setNewEntryFromdashboard();
		return SUCCESS;
	}
	public String LoadfromMainmenu()
	{
		clearForm();
		setErrorMessage(null);
		errorMessages.clear();
		setLoggedData();
		//loadEstimationRefNumbers();//to load Application status to be changed
		//loadWorkEstimationRefNumbers();
		loadWareHouse();
		loadFundSources();
		setNewEntryFromMainMenu();
		return SUCCESS;
	}
	public void setNewEntryFromMainMenu(){
		setLoggedData();
		HttpServletRequest request = ServletActionContext.getRequest();
		List<String> jobNos = null;
		String sessionKey= (String) request.getSession().getAttribute("region");
		PcesthmtEjb pcesthmtEjb = new PcesthmtEjb(getRegion());
		List<Long> status = new ArrayList<Long>();
		status.add(new Long(EstimateStatus.JOB_RIVISION));
		status.add(new Long(EstimateStatus.JOB_ONGOING));
		status.add(new Long(EstimateStatus.JOB_REJECTED));
		status.add(new Long(EstimateStatus.JOB_APPROVAL_CE));
		status.add(new Long(EstimateStatus.JOB_APPROVAL_EE));
		status.add(new Long(EstimateStatus.JOB_APPROVAL_DGM));
		jobNos = pcesthmtEjb.findJobNosList(getCostCenterNo(),status); 
		request.getSession().setAttribute("revisejobNos",jobNos);
		setUserId(userId);
		setRegion(sessionKey);
		
		
	}
	public void setNewEntryFromdashboard(){
		setLoggedData();
		HttpServletRequest request = ServletActionContext.getRequest();
		String sessionKey= (String) request.getSession().getAttribute("region");
		String projectNo=request.getParameter("projectNo");
		String costCen=request.getParameter("costCenter");
		if(projectNo!=null && projectNo.length()>0 && costCen!=null && costCen.length()>0){
			setProjectNo(projectNo);
			setCostCenterNo(costCen);
			//viewApplicantDetails();
			
		}
		List<String> jobNos = new ArrayList<String>();
		
		if(projectNo != null){
			jobNos.add(projectNo);
			request.getSession().setAttribute("revisejobNos",jobNos);
			
		}
		
		setUserId(userId);
		setRegion(sessionKey);
		
		
	}
	public String undoDirect()
	{
		clearForm();

	
		
		setLoggedData();
		//setRejectedJobsFromMainMenu();
		loadJobNumbers();
		loadWareHouse();
		loadFundSources();
		return SUCCESS;
	}
	/*public void setRejectedJobsFromMainMenu(){
		setLoggedData();
		HttpServletRequest request = ServletActionContext.getRequest();
		String sessionKey= (String) request.getSession().getAttribute("region");
		String jobNo=request.getParameter("estNo");
		String costCen=request.getParameter("costCen");
		if(estimateNo!=null && estimateNo.length()>0 && costCen!=null && costCen.length()>0){
			setEstimateNo(estimateNo);
			setCostCenterNo(costCen);
			//viewApplicantDetails();
			
		}
		List<String> jobNos = new ArrayList<String>();
		
		if(jobNo != null){
			jobNos.add(jobNo);
			
			
			request.getSession().setAttribute("revisejobNos",jobNos);
		}
		
		String userId= (String) request.getSession().getAttribute("userName");
		
		setUserId(userId);
		setRegion(sessionKey);
		
		
	}*/
	public String PrintEstimateA4(){
		HttpServletRequest request = ServletActionContext.getRequest();		
		String costCenterNo=(String) request.getSession().getAttribute("costCenterNo");
		String userId= (String) request.getSession().getAttribute("userName");		
		String region= (String) request.getSession().getAttribute("region");
		String smcType= (String) request.getSession().getAttribute("smcType");
		String projectNo=request.getParameter("projectNo");
		Path path = new Path();
		System.out.println("successprint 1");
		report rept = new report();
		String REPORT_DIRECTORY = "" ;
		String EXPORT_REPORT_DIRECTORY = "";
		if (System.getProperty("os.name").equals("Windows XP") || System.getProperty("os.name").equals("Windows 7") ){
			REPORT_DIRECTORY = path.getReportPath("REPORT_DIRECTORY");
			EXPORT_REPORT_DIRECTORY = path.getReportPath("EXPORT_REPORT_DIRECTORY");

		}else{
			REPORT_DIRECTORY = path.getReportPath("REPORT_DIRECTORY_LINUX");
			EXPORT_REPORT_DIRECTORY = path.getReportPath("EXPORT_REPORT_DIRECTORY_LINUX");
		}	
		System.out.println("successprint 2");
		SecurityEjb ejb=new SecurityEjb(region);
		String authCostCenters = "";
		List<String> authCost = ejb.getAuthorizedCostCenters(userId);
		
		String authCostList[] = authCost.toArray(new String[0]);		
		HashMap<String, Object> param = new HashMap<String, Object>();
		//param.put("@project_no", "'"+getEstimateNo()+"'");
		param.put("@project_no", "'"+projectNo+"'");
		param.put("@costctr","'"+costCenterNo+"'");
		System.out.println("successprint 3"+ projectNo );
		String fileName = null;
		if(costCenterNo.equalsIgnoreCase("521.10") || costCenterNo.equalsIgnoreCase("521.20") ||
				costCenterNo.equalsIgnoreCase("521.30") || costCenterNo.equalsIgnoreCase("521.40") || 
				costCenterNo.equalsIgnoreCase("521.50") || costCenterNo.equalsIgnoreCase("522.10") || 
				costCenterNo.equalsIgnoreCase("522.20") || costCenterNo.equalsIgnoreCase("522.40") || 
				costCenterNo.equalsIgnoreCase("522.50") || costCenterNo.equalsIgnoreCase("522.60") ||
				costCenterNo.equalsIgnoreCase("523.10") || costCenterNo.equalsIgnoreCase("523.40") ||
				costCenterNo.equalsIgnoreCase("523.60") || costCenterNo.equalsIgnoreCase("523.70") || 
				costCenterNo.equalsIgnoreCase("514.20") || costCenterNo.equalsIgnoreCase("524.10") || 
				costCenterNo.equalsIgnoreCase("524.20") || costCenterNo.equalsIgnoreCase("524.30") || 
				costCenterNo.equalsIgnoreCase("524.40") || costCenterNo.equalsIgnoreCase("524.50") ||
				costCenterNo.equalsIgnoreCase("525.10") || costCenterNo.equalsIgnoreCase("525.20") || 
				costCenterNo.equalsIgnoreCase("525.30") || costCenterNo.equalsIgnoreCase("526.10") || 
				costCenterNo.equalsIgnoreCase("526.20") || costCenterNo.equalsIgnoreCase("526.30") || 
				costCenterNo.equalsIgnoreCase("526.40")){
			param.put("@devsec", "'"+getDivSec()+"'");
			param.put("@csc","'"+geteCSC()+"'");
			param.put("@area", "'"+getArea()+"'");
			param.put("@district","'"+getDistrict()+"'");
			param.put("@esname","'"+getEsname()+"'");
			fileName = rept.generateReport("ReviseEstimateGalle",param,getSession(),REPORT_DIRECTORY,EXPORT_REPORT_DIRECTORY);
		}else{
			param.put("@devsec", "'"+getDivSec()+"'");
			param.put("@csc","'"+geteCSC()+"'");
			param.put("@area", "'"+getArea()+"'");
			param.put("@district","'"+getDistrict()+"'");
			param.put("@esname","'"+getEsname()+"'");
			fileName = rept.generateReport("ReviseEstimateGalle",param,getSession(),REPORT_DIRECTORY,EXPORT_REPORT_DIRECTORY);
			System.out.println("successprint 4 : " + fileName);
		}
		try {
			if (fileName == "")
			{
				setLblError("Error occured while generating the report");
				return SUCCESS;
			}
			fileInputStream = new FileInputStream(fileName);
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
			System.out.println("successprint : " +e);
		}
		System.out.println("successprint");
		return "successprint";

	}
	
	
	
	public String PrintReviseApproval(){
		HttpServletRequest request = ServletActionContext.getRequest();		
		String costCenterNo=(String) request.getSession().getAttribute("costCenterNo");
		String userId= (String) request.getSession().getAttribute("userName");		
		String region= (String) request.getSession().getAttribute("region");
		String smcType= (String) request.getSession().getAttribute("smcType");
		String projectNo=request.getParameter("projectNo");
		Path path = new Path();
		System.out.println("successprint 1");
		report rept = new report();
		String REPORT_DIRECTORY = "" ;
		String EXPORT_REPORT_DIRECTORY = "";
		if (System.getProperty("os.name").equals("Windows XP") || System.getProperty("os.name").equals("Windows 7") ){
			REPORT_DIRECTORY = path.getReportPath("REPORT_DIRECTORY");
			EXPORT_REPORT_DIRECTORY = path.getReportPath("EXPORT_REPORT_DIRECTORY");

		}else{
			REPORT_DIRECTORY = path.getReportPath("REPORT_DIRECTORY_LINUX");
			EXPORT_REPORT_DIRECTORY = path.getReportPath("EXPORT_REPORT_DIRECTORY_LINUX");
		}	
		System.out.println("successprint 2");
		SecurityEjb ejb=new SecurityEjb(region);
		String authCostCenters = "";
		List<String> authCost = ejb.getAuthorizedCostCenters(userId);
		
		String authCostList[] = authCost.toArray(new String[0]);		
		HashMap<String, Object> param = new HashMap<String, Object>();
		if(projectNo != null){
			projectNo = projectNo.trim();
		}
		//param.put("@project_no", "'"+getEstimateNo()+"'");
		param.put("@project_no", "'"+projectNo+"'");
		param.put("@costctr","'"+costCenterNo+"'");
		System.out.println("successprint 3 : "+ projectNo  + "nnn : " + costCenterNo + "dddd : " + param + "session : " +getSession());
		
		String fileName = null;
		try{
			fileName = rept.generateReport("Approval_Revised_job",param,getSession(),REPORT_DIRECTORY,EXPORT_REPORT_DIRECTORY);
		}catch (Exception e) {
			
			e.printStackTrace();
			System.out.println("errorprint 5 " + e); 
		}
		System.out.println("successprint 4 :"+fileName);
		
		try {
			if (fileName == "")
			{
				setLblError("Error occured while generating the report");
				return SUCCESS;
			}
			fileInputStream = new FileInputStream(fileName);
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
			System.out.println("errorprint 4 " + e); 
		}
		System.out.println("successprint");
		return "successprint";

	}
	
	public String PrintCompleteReport(){
		HttpServletRequest request = ServletActionContext.getRequest();		
		String costCenterNo=(String) request.getSession().getAttribute("costCenterNo");
		String userId= (String) request.getSession().getAttribute("userName");		
		String region= (String) request.getSession().getAttribute("region");
		String smcType= (String) request.getSession().getAttribute("smcType");
		String projectNo=request.getParameter("projectNo");
		Path path = new Path();
		System.out.println("successprint 1");
		report rept = new report();
		String REPORT_DIRECTORY = "" ;
		String EXPORT_REPORT_DIRECTORY = "";
		if (System.getProperty("os.name").equals("Windows XP") || System.getProperty("os.name").equals("Windows 7") ){
			REPORT_DIRECTORY = path.getReportPath("REPORT_DIRECTORY");
			EXPORT_REPORT_DIRECTORY = path.getReportPath("EXPORT_REPORT_DIRECTORY");

		}else{
			REPORT_DIRECTORY = path.getReportPath("REPORT_DIRECTORY_LINUX");
			EXPORT_REPORT_DIRECTORY = path.getReportPath("EXPORT_REPORT_DIRECTORY_LINUX");
		}	
		System.out.println("successprint 2");
		SecurityEjb ejb=new SecurityEjb(region);
		String authCostCenters = "";
		List<String> authCost = ejb.getAuthorizedCostCenters(userId);
		
		String authCostList[] = authCost.toArray(new String[0]);		
		HashMap<String, Object> param = new HashMap<String, Object>();
		if(projectNo != null){
			projectNo = projectNo.trim();
		}
		//param.put("@project_no", "'"+getEstimateNo()+"'");
		param.put("@appNo", "'"+ getEstimateNo() +"'");
		param.put("@costctr","'"+costCenterNo+"'");
		System.out.println("successprint 3 : "+ getEstimateNo()  + "nnn : " + costCenterNo);
		String fileName = null;
		try{
			fileName = rept.generateReport("Energize",param,getSession(),REPORT_DIRECTORY,EXPORT_REPORT_DIRECTORY);
		}catch (Exception e) {
			
			e.printStackTrace();
			System.out.println("errorprint 5 " + e); 
		}
		System.out.println("successprint 4 :"+fileName);
		
		try {
			if (fileName == "")
			{
				setLblError("Error occured while generating the report");
				return SUCCESS;
			}
			fileInputStream = new FileInputStream(fileName);
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
			System.out.println("errorprint 4 " + e); 
		}
		System.out.println("successprint");
		return "successprint";

	}
	
	
	public String PrintReviseEstimateReport(){
		HttpServletRequest request = ServletActionContext.getRequest();
	
		String costCenterNo=(String) request.getSession().getAttribute("costCenterNo");
		String userId= (String) request.getSession().getAttribute("userName");
		
		String region= (String) request.getSession().getAttribute("region");
		String smcType= (String) request.getSession().getAttribute("smcType");
		
		
		Path path = new Path();
		
		report rept = new report();
		String REPORT_DIRECTORY = "" ;
		String EXPORT_REPORT_DIRECTORY = "";
		if (System.getProperty("os.name").equals("Windows XP") || System.getProperty("os.name").equals("Windows 7") ){
			REPORT_DIRECTORY = path.getReportPath("REPORT_DIRECTORY");
			EXPORT_REPORT_DIRECTORY = path.getReportPath("EXPORT_REPORT_DIRECTORY");

		}else{
			REPORT_DIRECTORY = path.getReportPath("REPORT_DIRECTORY_LINUX");
			EXPORT_REPORT_DIRECTORY = path.getReportPath("EXPORT_REPORT_DIRECTORY_LINUX");
		}
	
		SecurityEjb ejb=new SecurityEjb(region);
		
		String authCostCenters = "";
		List<String> authCost = ejb.getAuthorizedCostCenters(userId);
		
		String authCostList[] = authCost.toArray(new String[0]);
		
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("@project_no", "'"+getProjectNo()+"'");
		param.put("@costctr","'"+costCenterNo+"'");

		String fileName=null;
		if(costCenterNo.equals("543.10") || costCenterNo.equals("543.20") || costCenterNo.equals("543.40")){
			if(smcType.equalsIgnoreCase("MNT")){
				fileName = rept.generateReport("Job_MN",param,getSession(),REPORT_DIRECTORY,EXPORT_REPORT_DIRECTORY);
			}else if(smcType.equalsIgnoreCase("TCI")){
				fileName = rept.generateReport("Job_GI",param,getSession(),REPORT_DIRECTORY,EXPORT_REPORT_DIRECTORY);
			}else{
				fileName = rept.generateReport("Job_GI_TPV_TPJ_TPC_TPL",param,getSession(),REPORT_DIRECTORY,EXPORT_REPORT_DIRECTORY);
			}
		}else{
			fileName = rept.generateReport("EstimateRevise",param,getSession(),REPORT_DIRECTORY,EXPORT_REPORT_DIRECTORY);
		}
		
		System.out.println("inside ViewGayani");
		System.out.println("xxxxxxxxx"+ fileName);
		
		try {
			if (fileName == "")
			{
				setLblError("Error occured while generating the report");
				return SUCCESS;
			}
			fileInputStream = new FileInputStream(fileName);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "successprint";


	}
	
	public void updateSoftCloseEEDetails(){
		
	}

}//End of class
