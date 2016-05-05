package estimate.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.SessionAware;
import org.json.JSONObject;

import presentation.ajax.SPSAjaxController;
import reports.web.report;
import security.service.SecurityEjb;

import util.common.AppStatus;
import util.common.ApplicationStatus;
import util.common.Format;
import util.common.Path;
import util.common.PivPrefixType;
import util.common.StandardEstimateStatus;
import application.model.Applicant;
import application.model.Application;
import application.model.WiringLandDetail;
import application.service.ApplicantEjb;
import application.service.ApplicationEjb;
import application.service.WiringLandDetailEjb;
import application.web.PathStore;
import estimate.dto.DetailEstimateDTO;
import estimate.dto.Norm;
import estimate.dto.StandardEstimateDetail;
import estimate.model.Approval;
import estimate.model.EstimateDisplay;
import estimate.model.Pcestdtt;
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

import com.opensymphony.xwork2.ActionSupport;

import costcenter.service.GldeptmEjb;

import javax.persistence.Column;
import javax.persistence.PersistenceException;
import javax.persistence.RollbackException;
import javax.servlet.http.HttpServletRequest;
import util.common.Constants;;

@SuppressWarnings("serial")
public class StandardEstimateBS  extends ActionSupport implements SessionAware, ParameterAware{
	
	private String[] DetailsList;
	private String postUserName;
	private List<String> listuserName=new ArrayList<String>();
	private String contigency;
	private String lineLength;
	public String getLineLength() {
		return lineLength;
	}
	public void setLineLength(String lineLength) {
		this.lineLength = lineLength;
	}


	private String userId;
	private String password;
	private String region;
	private String costCenterNo;
	private String costCenterName;
	private String powerTosupply;
	private String secDeposit;
	private String nameAddress;
	private String sinNo;
	private String sinNo1;
	
	public String getSinNo1() {
		return sinNo1;
	}
	public void setSinNo1(String sinNo1) {
		this.sinNo1 = sinNo1;
	}


	private String sinNo2;
	public String getSinNo2() {
		return sinNo2;
	}
	public void setSinNo2(String sinNo2) {
		this.sinNo2 = sinNo2;
	}


	private String sinNo3;
	public String getSinNo3() {
		return sinNo3;
	}
	public void setSinNo3(String sinNo3) {
		this.sinNo3 = sinNo3;
	}


	private String sinNo4;
	public String getSinNo4() {
		return sinNo4;
	}
	public void setSinNo4(String sinNo4) {
		this.sinNo4 = sinNo4;
	}


	private String totalCost;
	private String vatcost;
	public String getVatcost() {
		return vatcost;
	}
	public void setVatcost(String vatcost) {
		this.vatcost = vatcost;
	}
	private String nbtcost;
	public String getNbtcost() {
		return nbtcost;
	}
	public void setNbtcost(String nbtcost) {
		this.nbtcost = nbtcost;
	}
	private String jobDescription;
	private String rejectedReason;
	private String demand;
	private String secDepositDisplay;
	private String applicationId;
	private String lblError=null;
	private String lblSuccess=null;
	private static final String MSG_ERROR="ERROR";
	private static final String MSG_INFO="INFO";
	private static final String MSG_DONE="DONE";
	private String errorMessage;
	private static final String MSG_NONE="NONE";
	private Map<String, Object> session;
	private String messageType=MSG_NONE;
	//private String messageType=MSG_NONE;
	private List<Norm> selectedNormsList;
	private String fileRef;
	private Map<String,Norm> updatedDetails;
	
	private Map<String,Norm> newlyAddedDetails;
	
	private String applicationNo;
	private String loggedInUserLevel;
	private String applicationType;
	//print variables
	private  String selectCategoryString = "----Select Category-----";
	private String selectedReport;
	private static final String viewPath="Reports>Select Report";
	private String path;
	private InputStream fileInputStream;
	  
	private Map <String, String[]> parameters;
	
	public String status;
	private List<String> postIdList;
	private String postId;
	private Long chgStatus;
	
	
	private File fileUpload;
	private String fileUploadContentType;
	private String fileUploadFileName;
	
	private String dgmcomment;
	private String cecomcomment;
	private String cepdcomment;
	private String eecomcomment;
	private String eepdcomment;
	private String escomcomment;
	
	
	public String getEscomcomment() {
		return escomcomment;
	}
	public void setEscomcomment(String escomcomment) {
		this.escomcomment = escomcomment;
	}
	public String getDgmcomment() {
		return dgmcomment;
	}
    public void setDgmcomment(String dgmcomment) {
		this.dgmcomment = dgmcomment;
	}
	
	public String getCecomcomment() {
		return cecomcomment;
	}


	public void setCecomcomment(String cecomcomment) {
		this.cecomcomment = cecomcomment;
	}
	
	public String getCepdcomment() {
		return cepdcomment;
	}


	public void setCepdcomment(String cepdcomment) {
		this.cepdcomment = cepdcomment;
	}
	
	public String getEecomcomment() {
		return eecomcomment;
	}


	public void setEecomcomment(String eecomcomment) {
		this.eecomcomment = eecomcomment;
	}
	
	
	public String getEepdcomment() {
		return eepdcomment;
	}


	public void setEepdcomment(String eepdcomment) {
		this.eepdcomment = eepdcomment;
	}


	public String execute() {
		System.out.println("##########execute");
		setInfoFromMainMenu();
		loadEstimationRefNumbers();
		loaduserIds();
		return SUCCESS;
	}
	
	
	public String init()
	{
		
		setInfoFromMainMenu();
		loadApplicationRefNumbers();
		return SUCCESS;
	}
	
	public String initUpdate()
	{
		
		setInfoFromMainMenu();
		loadEstimationRefNumbers();
		loaduserIds();
		return SUCCESS;
	}
	public String initUpdateDirect()
	{
		
		//setInfoFromMainMenu();
		setUndoRejectFromMainMenu();
		return SUCCESS;
	}
	public void setInfoFromMainMenu(){

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
		System.out.println("####################khgjhgh"+applicationId);
		
		
		
	}
	public void loadApplicationRefNumbers(){
		try{
			List<String> applicationRefNos = null;

			setInfoFromMainMenu();
			EstimateEjb estimateEjb = new EstimateEjb(getRegion());
			applicationRefNos = estimateEjb.loadApplicationRefnos(getUserId(),getCostCenterNo()); 
			HttpServletRequest request = ServletActionContext.getRequest();
			request.getSession().setAttribute("applicationRefNos",applicationRefNos);
		}catch(Exception e){
			
		}
		
		
		
	}
	@SuppressWarnings("null")
	public String loadSTDEstimatesForView(){
		try{
			List<String> estimationRefNos = null;
			List<Spstdesthmt> EstimateList = null;
			setInfoFromMainMenu();
			EstimateEjb estimateEjb = new EstimateEjb(getRegion());
			SpstdesthmtEjb xxx = new SpstdesthmtEjb(getRegion());
			List<Long> status = new ArrayList<Long>();
			status.add(StandardEstimateStatus.TO_BE_VALIDATED_BY_ELECTRICAL_ENGINEER.getKey());
			status.add(StandardEstimateStatus.TO_BE_CHECKED_BY_ELECTRICAL_SUPIRINTENDENT.getKey());
			status.add(StandardEstimateStatus.TO_BE_VALIDATED_BY_CE.getKey());
			status.add(StandardEstimateStatus.TO_BE_APPROVED_BY_DGM.getKey());
			status.add(StandardEstimateStatus.TO_BE_VALIDATED_BY_PLANNING_CE.getKey());
			status.add(StandardEstimateStatus.TO_BE_VALIDATED_BY_PLANNING_ENGINEER.getKey());
			status.add(StandardEstimateStatus.TO_BE_VALIDATED_BY_PLANNING_ENGINEER.getKey());
			status.add(StandardEstimateStatus.PIVII_CONFIRMATION.getKey());
			status.add(StandardEstimateStatus.SEND_FOR_CONSTRUCTION_MAINTENANCE.getKey());
			status.add(StandardEstimateStatus.REJECTED_BY.getKey());
			status.add(StandardEstimateStatus.JOB_ALLOCATED.getKey());
			status.add(StandardEstimateStatus.JOB_CANCELLATION.getKey());
			
			//estimationRefNos = estimateEjb.loadStandEstmatenos(getCostCenterNo(),status,getRegion()); 
			estimationRefNos = xxx.loadStandEstmateDetailsType(getCostCenterNo(),status,null,getSessionKey("smcType"), getRegion());
			
			
			/**EstimateList = estimateEjb.loadStandEstmateDetailsType(costCenterNo, status,null,getSessionKey("smcType"), region);
			if(EstimateList != null){
				Iterator<Spstdesthmt> usit = EstimateList.iterator();
				
				 while (usit.hasNext()) {        	 
			        	
					 Spstdesthmt objStd=usit.next();
					 String name = objStd.getId().getApplicationNo();
					 estimationRefNos.add(name);		       	        	        	
			    } 
			}*/
			HttpServletRequest request = ServletActionContext.getRequest();
			request.getSession().setAttribute("estimationRefNos",estimationRefNos);
			return SUCCESS;
		}catch(Exception e){
			
		}
		
		return SUCCESS;
		
	}
	
	
	public String loadSTDEstimatesForCancel(){
		setInfoFromMainMenu();
		//loadPostIds();
		try{
			List<String> estimationRefNos = null;
			SpstdesthmtEjb xxx = new SpstdesthmtEjb(getRegion());
			setInfoFromMainMenu();
			EstimateEjb estimateEjb = new EstimateEjb(getRegion());
			List<Long> status = new ArrayList<Long>();
			status.add(StandardEstimateStatus.NEW_APPLICATION.getKey());
			status.add(StandardEstimateStatus.APPLICATION_MODIFIED.getKey());
			status.add(StandardEstimateStatus.REJECTED_BY.getKey());
			status.add(StandardEstimateStatus.REJECTED_BY_CE.getKey());
			status.add(StandardEstimateStatus.REJECTED_BY_DGM.getKey());
			status.add(StandardEstimateStatus.PIVII_TO_BE_CONFIRMED_BY_ACCOUNT_REV.getKey());
			status.add(StandardEstimateStatus.PIVII_TO_BE_CONFIRMED_BY_DEO.getKey());
			status.add(StandardEstimateStatus.PIVII_TO_BE_CONFIRMED_BY_DEO.getKey());
			status.add(StandardEstimateStatus.TO_BE_VALIDATED_BY_ELECTRICAL_ENGINEER.getKey());
			status.add(StandardEstimateStatus.TO_BE_CHECKED_BY_ELECTRICAL_SUPIRINTENDENT.getKey());
			status.add(StandardEstimateStatus.TO_BE_VALIDATED_BY_CE.getKey());
			status.add(StandardEstimateStatus.TO_BE_APPROVED_BY_DGM.getKey());
			status.add(StandardEstimateStatus.TO_BE_VALIDATED_BY_PLANNING_CE.getKey());
			status.add(StandardEstimateStatus.TO_BE_VALIDATED_BY_PLANNING_ENGINEER.getKey());
			status.add(StandardEstimateStatus.TO_BE_VALIDATED_BY_PLANNING_ENGINEER.getKey());
			status.add(StandardEstimateStatus.PIVII_CONFIRMATION.getKey());
			status.add(StandardEstimateStatus.VALIDATED_BY_CE.getKey());
			status.add(StandardEstimateStatus.VALIDATED_BY_DGM.getKey());
			status.add(StandardEstimateStatus.VALIDATED_BY_ELECTRICAL_ENG.getKey());
			status.add(StandardEstimateStatus.VALIDATED_BY_PLANNING_ENG.getKey());
			status.add(StandardEstimateStatus.SEND_FOR_CONSTRUCTION_MAINTENANCE.getKey());
			status.add(StandardEstimateStatus.REJECTED_BY.getKey());
			status.add(StandardEstimateStatus.JOB_ALLOCATED.getKey());
			
			estimationRefNos = xxx.loadStandEstmateDetailsType(getCostCenterNo(),status,null,getSessionKey("smcType"), getRegion());
			
			//estimationRefNos = estimateEjb.loadStandEstmatenos(getCostCenterNo(),status,getRegion()); 
			//loadStandEstmateDetailsType (costCenterNo, status,null,getSmcType(), getSessionKey("region")
			//estimationRefNos = estimateEjb.loadStandEstmateDetailsType(getCostCenterNo(),status,null,getApplicationType(),getRegion());
			
			
			
			HttpServletRequest request = ServletActionContext.getRequest();
			request.getSession().setAttribute("estimationRefNos",estimationRefNos);
			return SUCCESS;
		}catch(Exception e){
			
		}
		
		return SUCCESS;
		
	}
	
	public String loadSTDEstimatesForCPS(){
		setInfoFromMainMenu();
		loadPostIds();
		try{
			List<String> estimationRefNos = null;
			SpstdesthmtEjb xxx = new SpstdesthmtEjb(getRegion());
			setInfoFromMainMenu();
			EstimateEjb estimateEjb = new EstimateEjb(getRegion());
			List<Long> status = new ArrayList<Long>();
			status.add(StandardEstimateStatus.NEW_APPLICATION.getKey());
			status.add(StandardEstimateStatus.APPLICATION_MODIFIED.getKey());
			status.add(StandardEstimateStatus.REJECTED_BY.getKey());
			status.add(StandardEstimateStatus.REJECTED_BY_CE.getKey());
			status.add(StandardEstimateStatus.REJECTED_BY_DGM.getKey());
			status.add(StandardEstimateStatus.PIVII_TO_BE_CONFIRMED_BY_ACCOUNT_REV.getKey());
			status.add(StandardEstimateStatus.PIVII_TO_BE_CONFIRMED_BY_DEO.getKey());
			status.add(StandardEstimateStatus.PIVII_TO_BE_CONFIRMED_BY_DEO.getKey());
			status.add(StandardEstimateStatus.TO_BE_VALIDATED_BY_ELECTRICAL_ENGINEER.getKey());
			status.add(StandardEstimateStatus.TO_BE_CHECKED_BY_ELECTRICAL_SUPIRINTENDENT.getKey());
			status.add(StandardEstimateStatus.TO_BE_VALIDATED_BY_CE.getKey());
			status.add(StandardEstimateStatus.TO_BE_APPROVED_BY_DGM.getKey());
			status.add(StandardEstimateStatus.TO_BE_VALIDATED_BY_PLANNING_CE.getKey());
			status.add(StandardEstimateStatus.TO_BE_VALIDATED_BY_PLANNING_ENGINEER.getKey());
			status.add(StandardEstimateStatus.TO_BE_VALIDATED_BY_PLANNING_ENGINEER.getKey());
			status.add(StandardEstimateStatus.PIVII_CONFIRMATION.getKey());
			status.add(StandardEstimateStatus.VALIDATED_BY_CE.getKey());
			status.add(StandardEstimateStatus.VALIDATED_BY_DGM.getKey());
			status.add(StandardEstimateStatus.VALIDATED_BY_ELECTRICAL_ENG.getKey());
			status.add(StandardEstimateStatus.VALIDATED_BY_PLANNING_ENG.getKey());
			status.add(StandardEstimateStatus.SEND_FOR_CONSTRUCTION_MAINTENANCE.getKey());
			status.add(StandardEstimateStatus.JOB_ALLOCATED.getKey());
			
			status.add(StandardEstimateStatus.REJECTED_BY.getKey());
			status.add(StandardEstimateStatus.JOB_ALLOCATED.getKey());
			status.add(StandardEstimateStatus.APPROVED_ESTIMATES.getKey());
			estimationRefNos = estimateEjb.loadStandEstmatenos(getCostCenterNo(),status,getRegion()); 
			//loadStandEstmateDetailsType (costCenterNo, status,null,getSmcType(), getSessionKey("region")
			//estimationRefNos = estimateEjb.loadStandEstmateDetailsType(getCostCenterNo(),status,null,getApplicationType(),getRegion());
			
			
			estimationRefNos = xxx.loadStandEstmateDetailsType(getCostCenterNo(),status,null,getSessionKey("smcType"), getRegion());
			
			HttpServletRequest request = ServletActionContext.getRequest();
			request.getSession().setAttribute("estimationRefNos",estimationRefNos);
			return SUCCESS;
		}catch(Exception e){
			
		}
		
		return SUCCESS;
		
	}
	
	
	public void loadEstimationRefNumbers(){
		try{
			setInfoFromMainMenu();
			List<String> estimationRefNos = new ArrayList<String>();
			List<Spstdesthmt> EstimateList = null;
			EstimateEjb estimateEjb = new EstimateEjb(getRegion());
			List<Long> status = new ArrayList<Long>();
			status.add(StandardEstimateStatus.NEW_APPLICATION.getKey());
			status.add(StandardEstimateStatus.APPLICATION_MODIFIED.getKey());
			status.add(StandardEstimateStatus.REJECTED_BY.getKey());
			if(getSessionKey("userRole").equalsIgnoreCase("EE")){
				status.add(StandardEstimateStatus.TO_BE_VALIDATED_BY_ELECTRICAL_ENGINEER.getKey());
			}else if(getSessionKey("userRole").equalsIgnoreCase("PE")){
				status.add(StandardEstimateStatus.TO_BE_VALIDATED_BY_PLANNING_ENGINEER.getKey());
			}
			//estimationRefNos = estimateEjb.loadStandEstmatenos(getCostCenterNo(),status,getRegion());
			EstimateList = estimateEjb.loadStandEstmateDetailsType(costCenterNo, status,null,getSessionKey("smcType"), region);
			if(EstimateList != null){
				Iterator<Spstdesthmt> usit = EstimateList.iterator();
				
				 while (usit.hasNext()) {        	 
			        	
					 Spstdesthmt objStd=usit.next();
					 String name = objStd.getId().getApplicationNo();
					 estimationRefNos.add(name);		       	        	        	
			    } 
			}
			
			HttpServletRequest request = ServletActionContext.getRequest();
			request.getSession().setAttribute("estimationRefNos",estimationRefNos);
		}catch(Exception e){
			
		}
		
		
		
	}
	
	public void loadSTDEstimatesForView1(){
		try{
			setInfoFromMainMenu();
			List<String> estimationRefNos = new ArrayList<String>();
			List<Spstdesthmt> EstimateList = null;
			EstimateEjb estimateEjb = new EstimateEjb(getRegion());
			List<Long> status = new ArrayList<Long>();
			status.add(StandardEstimateStatus.TO_BE_VALIDATED_BY_ELECTRICAL_ENGINEER.getKey());
			status.add(StandardEstimateStatus.TO_BE_CHECKED_BY_ELECTRICAL_SUPIRINTENDENT.getKey());
			status.add(StandardEstimateStatus.TO_BE_VALIDATED_BY_CE.getKey());
			status.add(StandardEstimateStatus.TO_BE_APPROVED_BY_DGM.getKey());
			status.add(StandardEstimateStatus.TO_BE_VALIDATED_BY_PLANNING_CE.getKey());
			status.add(StandardEstimateStatus.TO_BE_VALIDATED_BY_PLANNING_ENGINEER.getKey());
			status.add(StandardEstimateStatus.TO_BE_VALIDATED_BY_PLANNING_ENGINEER.getKey());
			status.add(StandardEstimateStatus.PIVII_CONFIRMATION.getKey());
			status.add(StandardEstimateStatus.SEND_FOR_CONSTRUCTION_MAINTENANCE.getKey());
			status.add(StandardEstimateStatus.REJECTED_BY.getKey());
			status.add(StandardEstimateStatus.JOB_ALLOCATED.getKey());
			status.add(StandardEstimateStatus.JOB_CANCELLATION.getKey());
			//estimationRefNos = estimateEjb.loadStandEstmatenos(getCostCenterNo(),status,getRegion());
			EstimateList = estimateEjb.loadStandEstmateDetailsType(costCenterNo, status,null,getSessionKey("smcType"), region);
			if(EstimateList != null){
				Iterator<Spstdesthmt> usit = EstimateList.iterator();
				
				 while (usit.hasNext()) {        	 
			        	
					 Spstdesthmt objStd=usit.next();
					 String name = objStd.getId().getApplicationNo();
					 estimationRefNos.add(name);		       	        	        	
			    } 
			}
			
			HttpServletRequest request = ServletActionContext.getRequest();
			request.getSession().setAttribute("estimationRefNos",estimationRefNos);
		}catch(Exception e){
			
		}
		
		
		
	}
	
	public void loadEstimationRefNumbersForCancel(){
		try{
			//setInfoFromMainMenu();
			List<String> estimationRefNos = new ArrayList<String>();
			List<Spstdesthmt> EstimateList = null;
			SpstdesthmtEjb xxx = new SpstdesthmtEjb(getRegion());
			EstimateEjb estimateEjb = new EstimateEjb(getRegion());
			List<Long> status = new ArrayList<Long>();
			status.add(StandardEstimateStatus.NEW_APPLICATION.getKey());
			status.add(StandardEstimateStatus.APPLICATION_MODIFIED.getKey());
			status.add(StandardEstimateStatus.REJECTED_BY.getKey());
			status.add(StandardEstimateStatus.REJECTED_BY_CE.getKey());
			status.add(StandardEstimateStatus.REJECTED_BY_DGM.getKey());
			status.add(StandardEstimateStatus.PIVII_TO_BE_CONFIRMED_BY_ACCOUNT_REV.getKey());
			status.add(StandardEstimateStatus.PIVII_TO_BE_CONFIRMED_BY_DEO.getKey());
			status.add(StandardEstimateStatus.PIVII_TO_BE_CONFIRMED_BY_DEO.getKey());
			status.add(StandardEstimateStatus.TO_BE_VALIDATED_BY_ELECTRICAL_ENGINEER.getKey());
			status.add(StandardEstimateStatus.TO_BE_CHECKED_BY_ELECTRICAL_SUPIRINTENDENT.getKey());
			status.add(StandardEstimateStatus.TO_BE_VALIDATED_BY_CE.getKey());
			status.add(StandardEstimateStatus.TO_BE_APPROVED_BY_DGM.getKey());
			status.add(StandardEstimateStatus.TO_BE_VALIDATED_BY_PLANNING_CE.getKey());
			status.add(StandardEstimateStatus.TO_BE_VALIDATED_BY_PLANNING_ENGINEER.getKey());
			status.add(StandardEstimateStatus.TO_BE_VALIDATED_BY_PLANNING_ENGINEER.getKey());
			status.add(StandardEstimateStatus.PIVII_CONFIRMATION.getKey());
			status.add(StandardEstimateStatus.SEND_FOR_CONSTRUCTION_MAINTENANCE.getKey());
			status.add(StandardEstimateStatus.REJECTED_BY.getKey());
			//estimationRefNos = estimateEjb.loadStandEstmatenos(getCostCenterNo(),status,getRegion());
			//EstimateList = estimateEjb.loadStandEstmateDetailsType(costCenterNo, status,null,"BS", region);
			estimationRefNos = xxx.loadStandEstmateDetailsType(getCostCenterNo(),status,null,getSessionKey("smcType"), getRegion());
			
			/**if(EstimateList != null){
				Iterator<Spstdesthmt> usit = EstimateList.iterator();
				
				 while (usit.hasNext()) {        	 
			        	
					 Spstdesthmt objStd=usit.next();
					 String name = objStd.getId().getApplicationNo();
					 estimationRefNos.add(name);		       	        	        	
			    } 
			}*/
			
			HttpServletRequest request = ServletActionContext.getRequest();
			request.getSession().setAttribute("estimationRefNos",estimationRefNos);
		}catch(Exception e){
			
		}
		
		
		
	}
	
	public String close() {
		
		return "close";
	}
	public String saveEstimation(){
		try{
			setInfoFromMainMenu();
			HttpServletRequest request = ServletActionContext.getRequest();
			/*String region= (String) request.getSession().getAttribute("region");
			String costCenterNo=(String) request.getSession().getAttribute("costCenterNo");
			String userId= (String) request.getSession().getAttribute("userName");*/
			EstimateEjb estimateEjb = new EstimateEjb(getRegion()); 
			WiringLandDetailEjb wiringLandDetailEjb= new WiringLandDetailEjb(getRegion());
			ApplicationEjb applicationEjb = new ApplicationEjb(getRegion());
			String costCenterNo=(String) request.getSession().getAttribute("costCenterNo");
			String smcType= (String) request.getSession().getAttribute("smcType");
			if(getApplicationNo() != null){
				
				Spstdesthmt spstdesthmt = new Spstdesthmt();
				List<Spstdestdmt> lineTypeList = new ArrayList<Spstdestdmt>();
				SpstdesthmtPK id = new SpstdesthmtPK();
				id.setApplicationNo(getApplicationNo());
				id.setStdNo(getApplicationNo());
				id.setDeptId(getCostCenterNo());
				//spstdesthmt.setRejReasonEE(getRejectedReason());
				if(getSecDeposit() == null || getSecDeposit().equalsIgnoreCase(Constants.DEFAULT_STRING)){
					setSecDeposit("0");
					
				}
				spstdesthmt.setSecDeposit(new BigDecimal(getSecDeposit()));
				spstdesthmt.setId(id);
				spstdesthmt.setDescription(getJobDescription());
				if(getTotalCost() == null || getTotalCost().equalsIgnoreCase(Constants.DEFAULT_STRING)){
					setTotalCost("0");
					
				}
				System.out.println("ss:  " + getTotalCost());
				
				if(getTotalCost()!= null && getTotalCost().matches("[0-9]+")){
					System.out.println("Hiii x :");
					spstdesthmt.setTotalCost(new BigDecimal(getTotalCost()));
				}
				//spstdesthmt.setTotalCost(new BigDecimal(getTotalCost()));
				System.out.println("ss1:  " + "kkk" );
				spstdesthmt.setSinNo(getSinNo());
				spstdesthmt.setSinNo1(getSinNo1());
				spstdesthmt.setSinNo2(getSinNo2());
				spstdesthmt.setSinNo3(getSinNo3());
				spstdesthmt.setSinNo4(getSinNo4());
				
				if(getLineLength().equalsIgnoreCase(Constants.DEFAULT_STRING)){
					System.out.println("ss1:  " +Constants.DEFAULT_STRING + "lll");
					
					setLineLength("0");
					
				}
				System.out.println("ss1:  " + Constants.DEFAULT_STRING + "kkk" );
				System.out.println("ss1:  " + getLineLength() + "kkk" );
				System.out.println("ddd" );
				if(getLineLength() != null){
					System.out.println("ddd1" + getLineLength() +"k");
					spstdesthmt.setLine_length(new BigDecimal(getLineLength()));
					System.out.println("ddd2" );
				}
				System.out.println("con:  " + getContigency() + "SIN " + getSinNo());
				spstdesthmt.setContigency(getContigency());
				spstdesthmt.setStatus(StandardEstimateStatus.NEW_APPLICATION.getKey());
				spstdesthmt.setEntryDate(new Date());
				spstdesthmt.setEntryBy(userId);
				spstdesthmt.setJobName(getPowerTosupply());
				//spstdesthmt.setDeptId(costCenterNo);
				
				
				//PcestdttEjb pcestdttEjb = new PcestdttEjb();
				
				Application application = applicationEjb.findByApplicationNo(applicationNo);
				if(getDemand().equalsIgnoreCase(Constants.DEFAULT_STRING)){
					setDemand("0");
					
				}
				
				System.out.println("ss2:  " );
				
				double demand = Long.parseLong(getDemand());
				System.out.println("ss3:  " );
				
				if(application.getApplicationType().equalsIgnoreCase(Constants.BULK)
						|| application.getApplicationType().equalsIgnoreCase(Constants.BULK_SUP)
							||application.getApplicationType().equalsIgnoreCase(Constants.BS)){
	                   
	                   spstdesthmt.setSecDeposit(new BigDecimal(Long.parseLong(getDemand())*Constants.BULK_SUPPLY_DEMAND_COST));
	                   
	            }
				System.out.println("ss4:  " );
				if(costCenterNo != null && (costCenterNo.equalsIgnoreCase("501.00") || costCenterNo.equalsIgnoreCase("550.00") || costCenterNo.equalsIgnoreCase("510.00"))){
						/*if((((smcType.equalsIgnoreCase(PivPrefixType.getEST_XXX_Type(Constants.ESTIMATE_TYPE_EBS))||
								application.getApplicationType().equalsIgnoreCase(Constants.BS)) && demand == Constants.BULK_SUPPLY_DEMAND)) || 
									((smcType.equalsIgnoreCase(PivPrefixType.getEST_XXX_Type(Constants.ESTIMATE_TYPE_ELN))
										|| application.getApplicationType().equalsIgnoreCase(Constants.LAND)) 
											&& demand  >= Constants.LAND_DEMAND_LOWER_LIMIT && demand  <= Constants.LAND_DEMAND_UPPER_LIMIT)){
						
											SpNormEjb ejb = new SpNormEjb();
											SpnormsPK pk100KVA = new SpnormsPK();
											pk100KVA.setLineSectionTypeId(Constants.LINE_NORM_ID_33KV_100kVA_SUBST);
											
											Spnorms norm100KVA = ejb.findById(pk100KVA, getRegion());
										
											SpnormsPK pk63KVA = new SpnormsPK();
											pk63KVA.setLineSectionTypeId(Constants.LINE_NORM_ID_33KV_63kVA_SUBST);
											
											Spnorms norm63KVA = ejb.findById(pk63KVA, getRegion());
											
											
												if(costCenterNo.equalsIgnoreCase("501.00")  ){
													if(norm100KVA != null && norm100KVA.getId().getLineSectionTypeId().equalsIgnoreCase(Constants.LINE_NORM_ID_33KV_100kVA_SUBST)){
														if((smcType.equalsIgnoreCase(PivPrefixType.getEST_XXX_Type(Constants.ESTIMATE_TYPE_EBS))|| application.getApplicationType().equalsIgnoreCase(Constants.BS)) && demand == Constants.BULK_SUPPLY_DEMAND){
															spstdesthmt.setConCost(new BigDecimal((demand/100 )*norm100KVA.getStandardCost())); // 33 KV sub 100KVA norms
															spstdesthmt.setCebCost(new BigDecimal(((100-demand)/100 )*norm100KVA.getStandardCost()));
														}
														if((smcType.equalsIgnoreCase(PivPrefixType.getEST_XXX_Type(Constants.ESTIMATE_TYPE_ELN))|| application.getApplicationType().equalsIgnoreCase(Constants.LAND)) && demand  >= Constants.LAND_DEMAND_LOWER_LIMIT && demand  <= Constants.LAND_DEMAND_UPPER_LIMIT){
															spstdesthmt.setConCost(new BigDecimal((demand/100 )*norm100KVA.getStandardCost()));
															spstdesthmt.setCebCost(new BigDecimal(((100-demand)/100 )*norm100KVA.getStandardCost()));
														}
													}
												}else if(costCenterNo.equalsIgnoreCase("550.00") || costCenterNo.equalsIgnoreCase("510.00")){
													if(norm100KVA != null && norm100KVA.getId().getLineSectionTypeId().equalsIgnoreCase(Constants.LINE_NORM_ID_33KV_100kVA_SUBST) && norm63KVA != null && norm63KVA.getId().getLineSectionTypeId().equalsIgnoreCase(Constants.LINE_NORM_ID_33KV_63kVA_SUBST)){
														if((smcType.equalsIgnoreCase(PivPrefixType.getEST_XXX_Type(Constants.ESTIMATE_TYPE_EBS))|| application.getApplicationType().equalsIgnoreCase(Constants.BS)) && demand == Constants.BULK_SUPPLY_DEMAND){
															spstdesthmt.setConCost(new BigDecimal(norm63KVA.getStandardCost())); // 33 KV sub 100KVA norms
															spstdesthmt.setCebCost(new BigDecimal(norm100KVA.getStandardCost()-norm63KVA.getStandardCost()));
														}
														if((smcType.equalsIgnoreCase(PivPrefixType.getEST_XXX_Type(Constants.ESTIMATE_TYPE_ELN))|| application.getApplicationType().equalsIgnoreCase(Constants.LAND)) && demand  >= Constants.LAND_DEMAND_LOWER_LIMIT && demand  <= Constants.LAND_DEMAND_UPPER_LIMIT){
															spstdesthmt.setConCost(new BigDecimal((demand/100 )*norm100KVA.getStandardCost()));
															spstdesthmt.setCebCost(new BigDecimal(((100-demand)/100 )*norm100KVA.getStandardCost()));
														}
													}
													
												}
						
					}else{
						
						spstdesthmt.setConCost(new BigDecimal("0"));
						spstdesthmt.setCebCost(new BigDecimal("0"));
						
					}*/
				}
		
				Map<String, Norm> selectedNorms = (Map<String, Norm>) request.getSession().getAttribute("selectedNorms");
				Map<String,Norm> normsMap = (Map<String, Norm>) request.getSession().getAttribute("normsMap");
				
				if(selectedNorms != null){
					selectedNormsList = new ArrayList<Norm>(selectedNorms.values());
					
					
				}
				selectedNormsList = selectedNormsList;
				if(selectedNormsList != null && selectedNormsList.size() > 0){
					//List<Norm> selectedNormsList = new ArrayList<Norm>(selectedNorms.values());
					double totalCebCost = 0;
					for(Norm norm: selectedNormsList){
					/*	if(costCenterNo != null && (costCenterNo.equalsIgnoreCase("501.00") || costCenterNo.equalsIgnoreCase("550.00") || costCenterNo.equalsIgnoreCase("510.00"))){
							
							if(norm.getLineSectionTypeId().contains(Constants.CEB_COST_CODE)){
								totalCebCost = totalCebCost + norm.getEstimatedCost().doubleValue();
								spstdesthmt.setCebCost(new BigDecimal(totalCebCost));
							}
							if(norm.getLineSectionTypeId().equalsIgnoreCase(Constants.CONSUMER_COST_CODE_FOR_SUBSTATION)){
								spstdesthmt.setConCost(norm.getEstimatedCost());
							}
						}*/
						
						//Norm norm = (Norm)normsMap.get(lineId);
						Spstdestdmt spstdestdmt = new Spstdestdmt();
						SpstdestdmtPK sp= new SpstdestdmtPK();
						sp.setApplicationNo(getApplicationNo());
						sp.setLineType(norm.getLineSectionTypeId().trim());
						sp.setStdNo(getApplicationNo());
						
						
						spstdestdmt.setId(sp);
						System.out.println("ss4:  " + norm.getQuantity() );
						if(norm.getQuantity() != null){
							spstdestdmt.setLength(norm.getQuantity());
						}else{
							spstdestdmt.setLength(new BigDecimal(0));
						}
						
						spstdestdmt.setDeptId(getCostCenterNo());
						spstdestdmt.setUom(norm.getUom());
						System.out.println("ss5:  " + norm.getStandardCost() );
						spstdestdmt.setLineCost(norm.getStandardCost());
						
						
						spstdestdmt.setLineDescription(norm.getDescription());
						if(norm.getEstimatedCost()  != null){
							System.out.println("ss5:  " + norm.getEstimatedCost() );
							spstdestdmt.setEstmateCost(norm.getEstimatedCost());
						}else{
							spstdestdmt.setEstmateCost(new BigDecimal(0));
						}
						
						
						lineTypeList.add(spstdestdmt);
						
					}
					estimateEjb.insert(spstdesthmt, lineTypeList,getRegion());
					//clearForm();
					
					estimateEjb.updateStandardEstimateCost(getApplicationNo(), getCostCenterNo(), region);
					wiringLandDetailEjb.updateDemand(application.getId().getApplicationId(),Long.parseLong(getDemand()),region);
					//setLblSuccess("Application Details successfully saved");
					setMessageType(MSG_DONE);
					setErrorMessage("Standard Estimate Details successfully saved");
					loadApplicationRefNumbers();
				}
				else{
					setMessageType(MSG_INFO);
					setErrorMessage("Please select norms");
					//setLblError("Please select norms");
					setSinNo(costCenterNo);
					setSecDeposit(getSecDeposit());
					setRegion(region);
					return SUCCESS;
				}
			
			}else{
				//setLblError("Enter estimation Details");
				setMessageType(MSG_INFO);
				setErrorMessage("Enter estimation Details");
			}
			
			
		}catch (RollbackException e) {
			
			e.printStackTrace();
			System.out.println("ERROR MESSEGE" + e.getMessage());
			setErrorMessage(e.getMessage());
			return ERROR;
		} catch (PersistenceException e) {
			
			e.printStackTrace();
			System.out.println("ERROR MESSEGE" + e.getMessage());
			setErrorMessage(e.getMessage());
			return ERROR;
		} catch (Exception e) {
			
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
		
	}
	
	
	
	public String updateEstimation(){
		try{
			//EstimateEjb estimateEjb = new EstimateEjb(getRegion());
			//applicationRefNos = estimateEjb.loadApplicationRefnos(getUserId(),getCostCenterNo()); 
			setInfoFromMainMenu();
			HttpServletRequest request = ServletActionContext.getRequest();
			String region= (String) request.getSession().getAttribute("region");
			String costCenterNo=(String) request.getSession().getAttribute("costCenterNo");
			String smcType= (String) request.getSession().getAttribute("smcType");
			//request.getSession().setAttribute("applicationRefNos",applicationRefNos);
			List<Spstdestdmt> addedList = null;
			List<Spstdestdmt> updatedList = new ArrayList<Spstdestdmt>();
			String userId= (String) request.getSession().getAttribute("userName");
			String userRole= (String) request.getSession().getAttribute("userRole");
			updatedDetails = (Map<String, Norm>) request.getSession().getAttribute("updatedDetails");
			
			newlyAddedDetails = (Map<String,Norm>) request.getSession().getAttribute("newlyAddedDetails");
			
			StandardEstimateDetail standardEstimateDetail = (StandardEstimateDetail) request.getSession().getAttribute("standardEstimateDetail");
			
			//System.out.println(getSecDeposit());
			//System.out.println(getSinNo());
			Spstdesthmt spstdesthmt = standardEstimateDetail.getHmt();
			//System.out.println("con:  " + getContingency() + "SIN " + getSinNo());
			if(userRole.equalsIgnoreCase("EE")){
				spstdesthmt.setRejReasonEE(getRejectedReason());
			}else if(userRole.equalsIgnoreCase("CE")){
				spstdesthmt.setRejReasonCE(getRejectedReason());
			}else if(userRole.equalsIgnoreCase("PE")){
				spstdesthmt.setRejReasonCE(getRejectedReason());
			}
			System.out.println("ssS:  " +getSecDepositDisplay());
			
			System.out.println("ss:  " + getSecDeposit());
			System.out.println("ss:  " + getSecDeposit());
			if(getSecDeposit()!=null){
				String secDeposit = getSecDeposit().replace(",","");
				spstdesthmt.setSecDeposit(new BigDecimal(secDeposit));
			}
			
			System.out.println("Hiii 1 :");
			spstdesthmt.setDescription(getJobDescription());
			spstdesthmt.setSinNo(getSinNo());
			spstdesthmt.setSinNo1(getSinNo1());
			spstdesthmt.setSinNo2(getSinNo2());
			spstdesthmt.setSinNo3(getSinNo3());
			spstdesthmt.setSinNo4(getSinNo4());
			
			spstdesthmt.setLine_length(new BigDecimal(getLineLength()));
			if(getVatcost() != null){
				spstdesthmt.setVatcost(new BigDecimal(getVatcost()));
			}
			if(getNbtcost() != null){
			spstdesthmt.setNbtcost(new BigDecimal(getNbtcost()));
			}
			System.out.println("con:  " + getContigency() + "SIN " + getSinNo());
			spstdesthmt.setContigency(getContigency());
			//System.out.println("Hiii x :" + getTotalCost().contains("[a-zA-Z]+"));
			
			if(getTotalCost()!= null && getTotalCost().matches("[0-9]+")){
				System.out.println("Hiii x :");
				spstdesthmt.setTotalCost(new BigDecimal(getTotalCost()));
			}
			System.out.println("Hiii y :");
			spstdesthmt.setEntryBy(userId);
			System.out.println("Hiii z :");
			spstdesthmt.setEntryDate(new Date());
			System.out.println("Hiii r :");
			spstdesthmt.setStatus(StandardEstimateStatus.APPLICATION_MODIFIED.getKey());
			System.out.println("Hiii w :");
			spstdesthmt.setJobName(getPowerTosupply());
			System.out.println("Hiii 2 :");
			double demand = Long.parseLong(getDemand());
			System.out.println("Hiii 3 :");
			
			ApplicationEjb applicationEjb = new ApplicationEjb(region);
			Application application = applicationEjb.findByApplicationNo(applicationNo);
			if(application.getApplicationType().equalsIgnoreCase(Constants.BULK)
					|| application.getApplicationType().equalsIgnoreCase(Constants.BULK_SUP)
						||application.getApplicationType().equalsIgnoreCase(Constants.BS)){
				System.out.println("Hiii 4 :");
                   spstdesthmt.setSecDeposit(new BigDecimal(Long.parseLong(getDemand())*Constants.BULK_SUPPLY_DEMAND_COST));
                   System.out.println("Hiii 5 :"); 
            }
			
			if(getSecDepositDisplay() != null){
				System.out.println("ssSS:  " +getSecDepositDisplay());
				String secDepositDisplay = getSecDepositDisplay().replace(",","");
				System.out.println("ssSS:  " +secDepositDisplay);
				spstdesthmt.setSecDeposit(new BigDecimal(secDepositDisplay));
				System.out.println("ssSSfffff:  " );
			}
			
			
			if(costCenterNo != null && (costCenterNo.equalsIgnoreCase("501.00") || costCenterNo.equalsIgnoreCase("550.00") || costCenterNo.equalsIgnoreCase("510.00"))){
				/*if((((smcType.equalsIgnoreCase(PivPrefixType.getEST_XXX_Type(Constants.ESTIMATE_TYPE_EBS))|| application.getApplicationType().equalsIgnoreCase(Constants.BS)) && demand == Constants.BULK_SUPPLY_DEMAND)) || 
						((smcType.equalsIgnoreCase(PivPrefixType.getEST_XXX_Type(Constants.ESTIMATE_TYPE_ELN))|| application.getApplicationType().equalsIgnoreCase(Constants.LAND)) && demand  >= Constants.LAND_DEMAND_LOWER_LIMIT && demand  <= Constants.LAND_DEMAND_UPPER_LIMIT)){
					SpNormEjb ejb = new SpNormEjb();
					SpnormsPK pk100KVA = new SpnormsPK();
					pk100KVA.setLineSectionTypeId(Constants.LINE_NORM_ID_33KV_100kVA_SUBST);
					
					Spnorms norm100KVA = ejb.findById(pk100KVA, getRegion());
				
					SpnormsPK pk63KVA = new SpnormsPK();
					pk63KVA.setLineSectionTypeId(Constants.LINE_NORM_ID_33KV_63kVA_SUBST);
					
					Spnorms norm63KVA = ejb.findById(pk63KVA, getRegion());
					
					
						if(costCenterNo.equalsIgnoreCase("501.00") || costCenterNo.equalsIgnoreCase("510.00")){
							if(norm100KVA != null && norm100KVA.getId().getLineSectionTypeId().equalsIgnoreCase(Constants.LINE_NORM_ID_33KV_100kVA_SUBST)){
								if((smcType.equalsIgnoreCase(PivPrefixType.getEST_XXX_Type(Constants.ESTIMATE_TYPE_EBS))|| application.getApplicationType().equalsIgnoreCase(Constants.BS)) && demand == Constants.BULK_SUPPLY_DEMAND){
									spstdesthmt.setConCost(new BigDecimal((demand/100 )*norm100KVA.getStandardCost())); // 33 KV sub 100KVA norms
									spstdesthmt.setCebCost(new BigDecimal(((100-demand)/100 )*norm100KVA.getStandardCost()));
								}
								if((smcType.equalsIgnoreCase(PivPrefixType.getEST_XXX_Type(Constants.ESTIMATE_TYPE_ELN))|| application.getApplicationType().equalsIgnoreCase(Constants.LAND)) && demand  >= Constants.LAND_DEMAND_LOWER_LIMIT && demand  <= Constants.LAND_DEMAND_UPPER_LIMIT){
									spstdesthmt.setConCost(new BigDecimal((demand/100 )*norm100KVA.getStandardCost()));
									spstdesthmt.setCebCost(new BigDecimal(((100-demand)/100 )*norm100KVA.getStandardCost()));
								}
							}
						}else if(costCenterNo.equalsIgnoreCase("550.00")){
							if(norm100KVA != null && norm100KVA.getId().getLineSectionTypeId().equalsIgnoreCase(Constants.LINE_NORM_ID_33KV_100kVA_SUBST) && norm63KVA != null && norm63KVA.getId().getLineSectionTypeId().equalsIgnoreCase(Constants.LINE_NORM_ID_33KV_63kVA_SUBST)){
								if((smcType.equalsIgnoreCase(PivPrefixType.getEST_XXX_Type(Constants.ESTIMATE_TYPE_EBS))|| application.getApplicationType().equalsIgnoreCase(Constants.BS)) && demand == Constants.BULK_SUPPLY_DEMAND){
									spstdesthmt.setConCost(new BigDecimal(norm63KVA.getStandardCost())); // 33 KV sub 100KVA norms
									spstdesthmt.setCebCost(new BigDecimal(norm100KVA.getStandardCost()-norm63KVA.getStandardCost()));
								}
								if((smcType.equalsIgnoreCase(PivPrefixType.getEST_XXX_Type(Constants.ESTIMATE_TYPE_ELN))|| application.getApplicationType().equalsIgnoreCase(Constants.LAND)) && demand  >= Constants.LAND_DEMAND_LOWER_LIMIT && demand  <= Constants.LAND_DEMAND_UPPER_LIMIT){
									spstdesthmt.setConCost(new BigDecimal((demand/100 )*norm100KVA.getStandardCost()));
									spstdesthmt.setCebCost(new BigDecimal(((100-demand)/100 )*norm100KVA.getStandardCost()));
								}
							}
							
						}
					
				}else{
				
						
					spstdesthmt.setConCost(new BigDecimal("0"));
					spstdesthmt.setCebCost(new BigDecimal("0"));
					
					
				}*/
			}
			boolean isExistCeb = false;
			boolean isExistConsumer = false;
			Spstdestdmt cebCostEntries = null;
			Spstdestdmt consumerCostEntries = null;
			 
			
			List<Spstdestdmt> list = standardEstimateDetail.getDmts();
			
			if(list != null && list.size() > 0){
				
				for(Spstdestdmt dmt : list){
					if(costCenterNo != null && (costCenterNo.equalsIgnoreCase("460.00") || costCenterNo.equalsIgnoreCase("501.00") || costCenterNo.equalsIgnoreCase("550.00") || costCenterNo.equalsIgnoreCase("510.00"))){
						if(dmt.getId().getLineType().trim().equalsIgnoreCase(Constants.CEB_COST_CODE_FOR_SUBSTATION)){
							isExistCeb = true;
							cebCostEntries = dmt;
						}
						if(dmt.getId().getLineType().trim().equalsIgnoreCase(Constants.CONSUMER_COST_CODE_FOR_SUBSTATION)){
							
							isExistConsumer = true;
							consumerCostEntries = dmt;
						}
					}
					if(updatedDetails != null && updatedDetails.containsKey(dmt.getId().getLineType().trim())){
						
						System.out.println("setUpdatedDmts :"+ dmt.getId().getLineType().trim());
						System.out.println("setUpdatedDmts :"+ updatedDetails.get(dmt.getId().getLineType().trim()).getEstimatedCost());
						setUpdatedDmts(dmt,updatedDetails.get(dmt.getId().getLineType().trim()));
						
						updatedList.add(dmt);
						
						
					}
					
				/*	if(costCenterNo != null && (costCenterNo.equalsIgnoreCase("501.00") || costCenterNo.equalsIgnoreCase("550.00") || costCenterNo.equalsIgnoreCase("510.00"))){
						if(isExistCeb && spstdesthmt.getCebCost() != null && cebCostEntries.doubleValue() != spstdesthmt.getCebCost().doubleValue()){
							updatedList.add(setUpdatedDmts(dmt,populateCEBCost(spstdesthmt)));
						}
						if(isExistConsumer && consumerCostEntries.doubleValue() != spstdesthmt.getConCost().doubleValue()){
							updatedList.add(setUpdatedDmts(dmt,populateConsumerCost(spstdesthmt)));
						}
					}*/
					
				}
				
				
			}
			double cebCost =0.0;
			String lineTypeId ="";
			if(newlyAddedDetails != null && newlyAddedDetails.size() > 0){
				 if(isExistCeb && cebCostEntries != null && newlyAddedDetails.containsKey(Constants.CEB_COST_CODE)){
					 Set<String> lineIds = newlyAddedDetails.keySet();
					 
					 for(String lineId : lineIds ){
							if(lineId != null && lineId.equalsIgnoreCase("3311L") || lineId.equalsIgnoreCase("3310E") || lineId.equalsIgnoreCase("3310L") || lineId.equalsIgnoreCase("3310R")
									|| lineId.equalsIgnoreCase("3311E") || lineId.equalsIgnoreCase("3311R") || lineId.equalsIgnoreCase("3313E") || lineId.equalsIgnoreCase("3313ED")
										|| lineId.equalsIgnoreCase("3313L") || lineId.equalsIgnoreCase("3313LD") ||
										lineId.equalsIgnoreCase("3313R") || lineId.equalsIgnoreCase("3313RD") || lineId.equalsIgnoreCase("CEB01")){
											lineTypeId =lineId;
											
											cebCost = cebCost + newlyAddedDetails.get(lineTypeId).getEstimatedCost().doubleValue();
											setUpdatedDmts(cebCostEntries,newlyAddedDetails.get(lineTypeId));
											
											updatedList.add(cebCostEntries);
											newlyAddedDetails.remove(lineTypeId);
							
							}
						}
					
					
					
					// cebCost = cebCost + newlyAddedDetails.get(Constants.CEB_COST_CODE).getEstimatedCost().doubleValue();
					 spstdesthmt.setCebCost(new BigDecimal(cebCost));
					
					 //updatedDetails.put(Constants.CEB_COST_CODE, newlyAddedDetails.get(Constants.CEB_COST_CODE));
					 
				 }
				 if(isExistConsumer && consumerCostEntries != null && newlyAddedDetails.containsKey(Constants.CONSUMER_COST_CODE_FOR_SUBSTATION)){
					 
					 
					 spstdesthmt.setConCost(newlyAddedDetails.get(Constants.CONSUMER_COST_CODE_FOR_SUBSTATION).getEstimatedCost());
					 setUpdatedDmts(consumerCostEntries,newlyAddedDetails.get(Constants.CONSUMER_COST_CODE_FOR_SUBSTATION));
						
					 updatedList.add(consumerCostEntries);
					 newlyAddedDetails.remove(Constants.CONSUMER_COST_CODE_FOR_SUBSTATION);
					 
				 }
				 
				 
				addedList = populateSpstdestdmt(spstdesthmt, new ArrayList<Norm>(newlyAddedDetails.values()));
				
				
				
			 }
			
			//comment update page Can't newly add consumer cost and CEb cost automatically
	/*		 if(!isExistCeb && spstdesthmt.getCebCost().doubleValue() != 0.0){
				 if(newlyAddedDetails == null){
					 newlyAddedDetails = new HashMap<String, Norm>();
				 }
				 newlyAddedDetails.put(Constants.CEB_COST_CODE,populateCEBCost(spstdesthmt));
			 }
			 if(!isExistConsumer && spstdesthmt.getConCost().doubleValue()!= 0.0){
				 if(newlyAddedDetails == null){
					 newlyAddedDetails = new HashMap<String, Norm>();
				 }
				 newlyAddedDetails.put(Constants.CONSUMER_COST_CODE,populateConsumerCost(spstdesthmt));
				}*/
			//spstdesthmt.setSinNo(getSinNo());

			
			
			EstimateEjb estimateEjb = new EstimateEjb(region);
			WiringLandDetailEjb wiringLandDetailEjb= new WiringLandDetailEjb(region);
			//ApplicationEjb applicationEjb = new ApplicationEjb(region);
			//PcestdttEjb pcestdttEjb = new PcestdttEjb();

			estimateEjb.updateEstimateDetails(spstdesthmt,addedList,updatedList,region);
			
			//Application application = applicationEjb.findByApplicationNo(applicationNo);
			
			wiringLandDetailEjb.updateDemand(application.getId().getApplicationId(),Long.parseLong(getDemand()),region);
			estimateEjb.updateStandardEstimateCost(getApplicationNo(), costCenterNo, region);
			//loadEstimationRefNumbers();
			//setLblSuccess("Application Details successfully updated");
			setMessageType(MSG_DONE);
			setErrorMessage("Standard Estimate Details successfully updated");
			clearForm();
		}catch (RollbackException e) {
			
			e.printStackTrace();
			System.out.println("ERROR MESSEGE" + e.getMessage());
			setErrorMessage(e.getMessage());
			return ERROR;
		} catch (PersistenceException e) {
			
			e.printStackTrace();
			System.out.println("ERROR MESSEGE" + e.getMessage());
			setErrorMessage(e.getMessage());
			return ERROR;
		} catch (Exception e) {
			
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
		loaduserIds();
		return SUCCESS;
		
	}
	
	private void delete(){
		
	}
	public List<Spstdestdmt> populateSpstdestdmt(Spstdesthmt spstdesthmt,List<Norm> norms){
		List<Spstdestdmt> spdmtList = new ArrayList<Spstdestdmt>();
		for(Norm norm : norms){
			Spstdestdmt spstdestdmt = new Spstdestdmt();
			SpstdestdmtPK id = new SpstdestdmtPK();
			id.setApplicationNo(spstdesthmt.getId().getApplicationNo());
			id.setLineType(norm.getLineSectionTypeId());
			id.setStdNo(spstdesthmt.getId().getStdNo());
			System.out.println("Hiii a:");
			spstdestdmt.setId(id);
			System.out.println("Hiii b:" + norm.getQuantity());
			if(norm.getQuantity()!= null){
				spstdestdmt.setLength(norm.getQuantity());
			}else{
				spstdestdmt.setLength(new BigDecimal(0));
			}
			System.out.println("Hiii c:");
			spstdestdmt.setDeptId(spstdesthmt.getId().getDeptId());
			System.out.println("Hiii d:");
			spstdestdmt.setUom(norm.getUom());
			System.out.println("Hiii e:");
			spstdestdmt.setLineCost(norm.getStandardCost());
			System.out.println("Hiii f:");
			spstdestdmt.setLineDescription(norm.getDescription());
			if(norm.getEstimatedCost()!= null){
			System.out.println("Hiii g:" + norm.getEstimatedCost());
			spstdestdmt.setEstmateCost(norm.getEstimatedCost());
			}else{
				spstdestdmt.setEstmateCost(new BigDecimal(0));
			}
			
			System.out.println("Hiii h:");
			spdmtList.add(spstdestdmt);
		}
		return spdmtList;
		
	}
    private Spstdestdmt setUpdatedDmts(Spstdestdmt dmt,Norm norm){
		
	 	
		List<Spstdestdmt> lst = new ArrayList<Spstdestdmt>();
		if(dmt != null){
			if(norm != null){
				System.out.println("getQuantity:"+ norm.getQuantity());
				dmt.setLength(norm.getQuantity());
				System.out.println("getEstimatedCost:"+ norm.getEstimatedCost());
				dmt.setEstmateCost(norm.getEstimatedCost());
				
			}
		}
	
		return dmt;
		
	}
private Norm populateCEBCost(Spstdesthmt hmt){
	
	
	Norm norm = new Norm();
	
	norm.setUom(Constants.DEFAULT_UOM);
	norm.setQuantity(new BigDecimal(Constants.DEFAULT_QUANTITY));
	norm.setLineSectionTypeId(Constants.CEB_COST_CODE_FOR_SUBSTATION);
	norm.setEstimatedCost(hmt.getCebCost());
	norm.setDescription(Constants.CEB_COST_FOR_SUBSTATION);
	norm.setStandardCost(Constants.DEFAULT_STD_COST);
	
	return norm;
	
}
private Norm populateConsumerCost(Spstdesthmt hmt){
	
	
	Norm norm = new Norm();
	
	norm.setUom(Constants.DEFAULT_UOM);
	norm.setQuantity(new BigDecimal(Constants.DEFAULT_QUANTITY));
	norm.setLineSectionTypeId(Constants.CONSUMER_COST_CODE_FOR_SUBSTATION);
	norm.setEstimatedCost(hmt.getConCost());
	norm.setDescription(Constants.CONSUMER_COST_FOR_SUBSTATION);
	norm.setStandardCost(Constants.DEFAULT_STD_COST);
	
	return norm;
	
}
/*public Spstdestdmt populateSpstdestdmt(Spstdesthmt spstdesthmt,Norm norm){
		//List<Spstdestdmt> spdmtList = new ArrayList<Spstdestdmt>();
	
		Spstdestdmt spstdestdmt = new Spstdestdmt();
		SpstdestdmtPK id = new SpstdestdmtPK();
		id.setApplicationNo(spstdesthmt.getId().getApplicationNo());
		id.setLineType(norm.getLineSectionTypeId());
		id.setStdNo(spstdesthmt.getId().getStdNo());
		
		spstdestdmt.setId(id);
		spstdestdmt.setLength(norm.getQuantity());
		
		spstdestdmt.setUom(norm.getUom());
		spstdestdmt.setLineCost(norm.getStandardCost());
		spstdestdmt.setLineDescription(norm.getDescription());
		spstdestdmt.setEstmateCost(norm.getEstimatedCost());
		//spdmtList.add(spstdestdmt);
	
	return spstdestdmt;
	
}
*/
	public List<Norm> getSelectedNormsList() {
		return selectedNormsList;
	}
	public void setSelectedNormsList(List<Norm> selectedNormsList) {
		this.selectedNormsList = selectedNormsList;
	}
	public String getApplicationNo() {
		return applicationNo;
	}
	public void setApplicationNo(String applicationNo) {
		this.applicationNo = applicationNo;
	}
	
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


	
	public String getPowerTosupply() {
		return powerTosupply;
	}

	public void setPowerTosupply(String powerTosupply) {
		this.powerTosupply = powerTosupply;
	}

	public String getSecDeposit() {
		return secDeposit;
	}

	public void setSecDeposit(String secDeposit) {
		this.secDeposit = secDeposit;
	}
	
	public String getSecDepositDisplay() {
		return secDepositDisplay;
	}

	public void setSecDepositDisplay(String secDepositDe) {
		this.secDepositDisplay = secDepositDe;
	}


	public String getNameAddress() {
		return nameAddress;
	}


	public void setNameAddress(String nameAddress) {
		this.nameAddress = nameAddress;
	}


	public String getSinNo() {
		return sinNo;
	}


	public void setSinNo(String sinNo) {
		this.sinNo = sinNo;
	}


	public String getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(String totalCost) {
		this.totalCost = totalCost;
	}

	public String getJobDescription() {
		return jobDescription;
	}


	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}


	public String getRejectedReason() {
		return rejectedReason;
	}


	public void setRejectedReason(String rejectedReason) {
		this.rejectedReason = rejectedReason;
	}


	public String getDemand() {
		return demand;
	}


	public void setDemand(String demand) {
		this.demand = demand;
	}


 
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getStatus() {
		return status;
	}
 
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPassword() {
		return password;
	}
 
	public void setPassword(String password) {
		this.password = password;
	}

	public static String getMsgError() {
		return MSG_ERROR;
	}

	public static String getMsgInfo() {
		return MSG_INFO;
	}

	public static String getMsgDone() {
		return MSG_DONE;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getMessageType() {
		return messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}
	public String getSessionKey(String key) {
		return getSession().get(key).toString();
	}


	public String getApplicationType() {
		return applicationType;
	}

	public void setApplicationType(String applicationType) {
		this.applicationType = applicationType;
	}

	private void clearForm(){
		HttpServletRequest request = ServletActionContext.getRequest();
		
		newlyAddedDetails =  (Map<String, Norm>) request.getSession().getAttribute("newlyAddedDetails");
		updatedDetails =  (Map<String, Norm>) request.getSession().getAttribute("updatedDetails");
		StandardEstimateDetail standardEstimateDetail = (StandardEstimateDetail) request.getSession().getAttribute("standardEstimateDetail");
		
		if(newlyAddedDetails != null){
			newlyAddedDetails.clear();
		}
		if(updatedDetails != null){
			updatedDetails.clear();
		}
		if(standardEstimateDetail != null){
			standardEstimateDetail = null;
		}
		
		request.getSession().setAttribute("newlyAddedDetails",newlyAddedDetails);
		request.getSession().setAttribute("updatedDetails",updatedDetails);
		request.getSession().setAttribute("standardEstimateDetail",standardEstimateDetail);
		
	}
	public String newDirect()
	{
		clearForm();
		setErrorMessage(null);
		/*setSuccessMessage(null);
		setInfoMessage(null);
		setLoggedData();
		this.isViewOnly = null;
		this.isModify = null;
		this.isUndoReject = "true";
		this.isApprove = null;
		this.isPrint = null;
		this.isRoughEstimate = null;
		setPath(viewPath);*/
		setNewEntryFromMainMenu();
		//setInfoFromMainMenu();
		//loadUndoEstimationRefNumbers();
		
		//==setFormData();
		return SUCCESS;
	}

	public String undoReject()
	{
		clearForm();
		setErrorMessage(null);
		/*setSuccessMessage(null);
		setInfoMessage(null);
		setLoggedData();
		this.isViewOnly = null;
		this.isModify = null;
		this.isUndoReject = "true";
		this.isApprove = null;
		this.isPrint = null;
		this.isRoughEstimate = null;
		setPath(viewPath);*/
		setUndoRejectFromMainMenu();
		//setInfoFromMainMenu();
		//loadUndoEstimationRefNumbers();
		
		//==setFormData();
		return SUCCESS;
	}
	public void loadUndoEstimationRefNumbers(){
		try{
			setInfoFromMainMenu();
			List<String> estimationRefNos = null;
			List<EstimateDisplay> tesmpList = null;
			HttpServletRequest request = ServletActionContext.getRequest();
			EstimateEjb estimateEjb = new EstimateEjb(getRegion());
			String region= (String) request.getSession().getAttribute("region");
			String smcType= (String) request.getSession().getAttribute("smcType");
			String userRole= (String) request.getSession().getAttribute("userRole");
			//estimationRefNos = estimateEjb.loadStandEstmatenos(getCostCenterNo(),ApplicationStatus.NEW_APPLICATION.getKey(),getRegion()); 
			//estimationRefNos = estimateEjb.getRejectedEstimateList(costCenterNo,StandardEstimateStatus.REJECTED_BY.getKey(),region); 
			tesmpList   = estimateEjb.getRejectedEstimateList(getCostCenterNo(), StandardEstimateStatus.REJECTED_BY.getKey(), smcType,getUserId(),userRole,region);
			if(tesmpList != null){
				estimationRefNos = getRejectedList(tesmpList);
				
				request.getSession().setAttribute("estimationRefNos",estimationRefNos);
			}
			
		}catch(Exception e){
			
		}
		
		
		
	}
	private List<String> getRejectedList(List<EstimateDisplay> tesmpList){
		List<String> estimationRefNos = new ArrayList<String>();
		for(EstimateDisplay dis : tesmpList){
			estimationRefNos.add(dis.getEstimateNo());
		}
		return estimationRefNos;
		
	}
	public void setUndoRejectFromMainMenu(){
		setInfoFromMainMenu();
		HttpServletRequest request = ServletActionContext.getRequest();
		String sessionKey= (String) request.getSession().getAttribute("region");
		String estimateNo=request.getParameter("estNo");
		String costCen=request.getParameter("costCen");
		if(estimateNo!=null && estimateNo.length()>0 && costCen!=null && costCen.length()>0){
			setApplicationNo(estimateNo);
			setCostCenterNo(costCen);
			//viewApplicantDetails();
			
		}
		List<String> estimationRefNos = new ArrayList<String>();
		
		if(estimateNo != null){
			estimationRefNos.add(estimateNo);
			
			request.getSession().setAttribute("estimationRefNos",estimationRefNos);
		}
	
		String userId= (String) request.getSession().getAttribute("userName");
		
		setUserId(userId);
		setRegion(sessionKey);
		
		
	}
	public void setNewEntryFromMainMenu(){
		setInfoFromMainMenu();
		HttpServletRequest request = ServletActionContext.getRequest();
		String sessionKey= (String) request.getSession().getAttribute("region");
		String estimateNo=request.getParameter("estNo");
		String costCen=request.getParameter("costCen");
		if(estimateNo!=null && estimateNo.length()>0 && costCen!=null && costCen.length()>0){
			setApplicationNo(estimateNo);
			setCostCenterNo(costCen);
			//viewApplicantDetails();
			
		}
		List<String> estimationRefNos = new ArrayList<String>();
		
		if(estimateNo != null){
			estimationRefNos.add(estimateNo);
			
			request.getSession().setAttribute("applicationRefNos",estimationRefNos);
		}
	
		String userId= (String) request.getSession().getAttribute("userName");
		
		setUserId(userId);
		setRegion(sessionKey);
		
		
	}

	
	
	/*private void viewApplicationDetails(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String sessionKey= (String) request.getSession().getAttribute("region");
		String applicationNo = request.getParameter("applicationNo");
		String costCenterNo=(String) request.getSession().getAttribute("costCenterNo");
		ApplicationEjb applicationEjb = new ApplicationEjb(sessionKey);
		
		ApplicantEjb applicantEjb = new ApplicantEjb(sessionKey);
		
		WiringLandDetailEjb wiringLandDetailEjb= new WiringLandDetailEjb(sessionKey);
		
		Application application = applicationEjb.findByApplicationNo(getApplicationNo());
		
		if (application!=null){
			Applicant  applicant=applicantEjb.findById(application.getIdNo());
			if (applicant!=null){
				WiringLandDetail  wiringLandDetail=wiringLandDetailEjb.findByApplicationNo(applicationNo);
				if (wiringLandDetail!=null){
					setPowerToSupply(wiringLandDetail.getServiceStreetAddress());
					setNameAddress(applicant.getStreetAddress());
					setDemand(String.valueOf(wiringLandDetail.getDemand()));
					//setSecDeposit(new BigDecimal(wiringLandDetail.getDemand()*1250.00));
					
				}
			}
		}
		
		EstimateEjb estimateEjb = new EstimateEjb(sessionKey); 
		
		Spstdesthmt appliDetails = estimateEjb.loadEstimateByApplicationNo(getCostCenterNo(),getApplicationNo(), sessionKey);
		if(appliDetails != null){
			setSecDeposit(appliDetails.getSecDeposit());
			setSinNo(appliDetails.getSinNo());
			setTotalCost(appliDetails.getTotalCost());
			setJobDescription( appliDetails.getDescription());
			setRejectedReason(appliDetails.getRejReasonCE());
			
			//packet.put("demand", "500.00");
			if(application.getApplicationType().equalsIgnoreCase(Constants.BULK)){
				//to do disable enable button
			}else if(application.getApplicationType().equalsIgnoreCase(Constants.LAND)){
				//to do disable enable button
			}
		}
		
		
		List<Spstdestdmt> normsList = estimateEjb.loadEstimateDetailsByApplicaNo(getCostCenterNo(),getApplicationNo(), sessionKey);
		
		if(normsList != null && normsList.size() != 0){
			
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
				selectedNorms.put(spstdestdmt.getId().getLineType(), populateNorm(spstdestdmt));

			}
		}else{
			packet.put("disableSave", false);
		}
		request.getSession().setAttribute("alreadyAddedDetails",alreadyAddedDetails);
		request.getSession().setAttribute("selectedNorms",selectedNorms);
		request.getSession().setAttribute("standardEstimateDetail",stdetails);
		
		packet.put("addednorms", normsArray);
	}*/
	
	public String Print(){
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
	 
		param.put("@appNo", "'"+getApplicationNo()+"'");
		param.put("@costctr","'"+costCenterNo+"'");
		 
		

		String fileName = null;
		if(smcType.equalsIgnoreCase(Constants.RUTAL_ELECTRIFICATION_I) || smcType.equalsIgnoreCase(Constants.RUTAL_ELECTRIFICATION)){
			fileName = rept.generateReport("job_QuotationRE",param,getSession(),REPORT_DIRECTORY,EXPORT_REPORT_DIRECTORY);
		}else if(smcType.equalsIgnoreCase(Constants.PLANNING)){
			fileName = rept.generateReport("STD_Estimate_Planing",param,getSession(),REPORT_DIRECTORY,EXPORT_REPORT_DIRECTORY);
		}/*else if(smcType.equalsIgnoreCase(Constants.RUTAL_ELECTRIFICATION)){
			fileName = rept.generateReport("STD_Estimate_PCB",param,getSession(),REPORT_DIRECTORY,EXPORT_REPORT_DIRECTORY);
		}*/
		else{
			System.out.println("Hii DDDDDD");
			fileName = rept.generateReport("job_Quotation",param,getSession(),REPORT_DIRECTORY,EXPORT_REPORT_DIRECTORY);
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
	
	
	public String Print95(){
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
	 
		param.put("@appNo", "'"+getApplicationNo()+"'");
		param.put("@costctr","'"+costCenterNo+"'");
		 
		

		String fileName = null;
		if(smcType.equalsIgnoreCase(Constants.RUTAL_ELECTRIFICATION_I) || smcType.equalsIgnoreCase(Constants.RUTAL_ELECTRIFICATION)){
			fileName = rept.generateReport("job_QuotationNew42",param,getSession(),REPORT_DIRECTORY,EXPORT_REPORT_DIRECTORY);
		}else if(smcType.equalsIgnoreCase(Constants.PLANNING)){
			fileName = rept.generateReport("job_QuotationNew42",param,getSession(),REPORT_DIRECTORY,EXPORT_REPORT_DIRECTORY);
		}/*else if(smcType.equalsIgnoreCase(Constants.RUTAL_ELECTRIFICATION)){
			fileName = rept.generateReport("STD_Estimate_PCB",param,getSession(),REPORT_DIRECTORY,EXPORT_REPORT_DIRECTORY);
		}*/
		else{
			System.out.println("Hii DDDDDD");
			fileName = rept.generateReport("job_QuotationNew42",param,getSession(),REPORT_DIRECTORY,EXPORT_REPORT_DIRECTORY);
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
	public String PrintAward(){
		setInfoFromMainMenu();
		HttpServletRequest request = ServletActionContext.getRequest();
		System.out.println("inside ViewGayani........");
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
	 

		
		param.put("@appNo", "'"+getApplicationNo()+"'");
		param.put("@costctr","'"+costCenterNo+"'");
		 
		String fileName = null;
		if(smcType.equalsIgnoreCase(Constants.BS)){
			fileName = rept.generateReport("AwardletterBULKR3",param,getSession(),REPORT_DIRECTORY,EXPORT_REPORT_DIRECTORY);
		}else if (smcType.equalsIgnoreCase(Constants.LAND) ){
			fileName = rept.generateReport("AwardletterLandR3",param,getSession(),REPORT_DIRECTORY,EXPORT_REPORT_DIRECTORY);
		}else if(smcType.equalsIgnoreCase(Constants.BS)){
			fileName = rept.generateReport("AwardletterBULK",param,getSession(),REPORT_DIRECTORY,EXPORT_REPORT_DIRECTORY);
		}else if(smcType.equalsIgnoreCase(Constants.LAND)){
			fileName = rept.generateReport("AwardletterLand",param,getSession(),REPORT_DIRECTORY,EXPORT_REPORT_DIRECTORY);
		}else if (smcType.equalsIgnoreCase(Constants.DCB_PROJECT) || smcType.equalsIgnoreCase(Constants.RUTAL_ELECTRIFICATION) || smcType.equalsIgnoreCase(Constants.RUTAL_ELECTRIFICATION_I)){
			fileName = rept.generateReport("AwardletterRE",param,getSession(),REPORT_DIRECTORY,EXPORT_REPORT_DIRECTORY);
		}else{
			fileName = rept.generateReport("Awardletter",param,getSession(),REPORT_DIRECTORY,EXPORT_REPORT_DIRECTORY);
		}
		
		
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
	
	
	public String PrintAwardNewWithHeader(){
		setInfoFromMainMenu();
		HttpServletRequest request = ServletActionContext.getRequest();
		System.out.println("inside ViewGayani........");
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
	 

		
		param.put("@appNo", "'"+getApplicationNo()+"'");
		param.put("@costctr","'"+costCenterNo+"'");
		 
		String fileName = null;
		if(smcType.equalsIgnoreCase(Constants.BS)){
			fileName = rept.generateReport("AwardletterBULKNewF1_D",param,getSession(),REPORT_DIRECTORY,EXPORT_REPORT_DIRECTORY);
		}else if (smcType.equalsIgnoreCase(Constants.LAND) ){
			fileName = rept.generateReport("AwardletterLandNewF1_D",param,getSession(),REPORT_DIRECTORY,EXPORT_REPORT_DIRECTORY);
		}else if (smcType.equalsIgnoreCase(Constants.DCB_PROJECT) || smcType.equalsIgnoreCase(Constants.RUTAL_ELECTRIFICATION) || smcType.equalsIgnoreCase(Constants.RUTAL_ELECTRIFICATION_I)){
			fileName = rept.generateReport("AwardletteRENewF1_D",param,getSession(),REPORT_DIRECTORY,EXPORT_REPORT_DIRECTORY);
		}else{
			fileName = rept.generateReport("AwardletterBULKNewF1_D",param,getSession(),REPORT_DIRECTORY,EXPORT_REPORT_DIRECTORY);
		}
		
		
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
	
	public String PrintAwardNewWithOutHeader(){
		setInfoFromMainMenu();
		HttpServletRequest request = ServletActionContext.getRequest();
		System.out.println("inside ViewGayani........");
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
	 

		
		param.put("@appNo", "'"+getApplicationNo()+"'");
		param.put("@costctr","'"+costCenterNo+"'");
		 
		String fileName = null;
		if(smcType.equalsIgnoreCase(Constants.BS)){
			fileName = rept.generateReport("AwardletterBULKNewF2_D",param,getSession(),REPORT_DIRECTORY,EXPORT_REPORT_DIRECTORY);
		}else if (smcType.equalsIgnoreCase(Constants.LAND) ){
			fileName = rept.generateReport("AwardletterLandNewF2_D",param,getSession(),REPORT_DIRECTORY,EXPORT_REPORT_DIRECTORY);
		}else if (smcType.equalsIgnoreCase(Constants.DCB_PROJECT) || smcType.equalsIgnoreCase(Constants.RUTAL_ELECTRIFICATION) || smcType.equalsIgnoreCase(Constants.RUTAL_ELECTRIFICATION_I)){
			fileName = rept.generateReport("AwardletteRENewF2_D",param,getSession(),REPORT_DIRECTORY,EXPORT_REPORT_DIRECTORY);
		}else{
			fileName = rept.generateReport("AwardletterBULKNewF2_D",param,getSession(),REPORT_DIRECTORY,EXPORT_REPORT_DIRECTORY);
		}
		
		
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
	/*public String executePrint(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String region= (String) request.getSession().getAttribute("region");
		String costCenterNo=(String) request.getSession().getAttribute("costCenterNo");
		String userId= (String) request.getSession().getAttribute("userName");
		System.out.println("inside execute........");
		setLblError(null);
		//setCostCenterNo(getSessionKey("costCenterNo"));
		System.out.println("inside costCenterNo........");
		setPath(viewPath);
		//setL(getSessionKey("userName"));
		System.out.println("inside 1........");
		//System.out.println(getStartDate());
		//if (getStartDate() == null)
			//startDate = new Date();
		//if (getEndDate() == null)
			//endDate = new Date();
	    //	setReportCategory();
		//setApplicationTypesList();
		
		//setContractorsList();
		//setAllocatedOrFinshedList();
		//setReports();
		
		SecurityEjb secEjb = new SecurityEjb(region);
		//String uName = getSessionKey("userName");
		List<String> authCsc = secEjb.getAuthorizedCostCenters(userId);
		System.out.println("inside 2........");
		
		
		return SUCCESS;
	}*/
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

	public Map<String, Norm> getNewlyAddedDetails() {
		return newlyAddedDetails;
	}

	public void setNewlyAddedDetails(Map<String, Norm> newlyAddedDetails) {
		this.newlyAddedDetails = newlyAddedDetails;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
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


	public Map <String, String[]> getParameters() {
		return parameters;
	}
		

	@Override
	public void setParameters(Map<String, String[]> parameters) {
		this.parameters=parameters;
		
	}

	

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public String getLoggedInUserLevel() {
		return loggedInUserLevel;
	}

	public void setLoggedInUserLevel(String loggedInUserLevel) {
		this.loggedInUserLevel = loggedInUserLevel;
	}
	
	public String getFileRef() {
		return fileRef;
	}

	public void setFileRef(String fileRef) {
		this.fileRef = fileRef;
	}

	public String PrintDispatchOFPIV(){
		//setInfoFromMainMenu();
		HttpServletRequest request = ServletActionContext.getRequest();
		System.out.println("inside ViewGayani........");
		String costCenterNo=(String) request.getSession().getAttribute("costCenterNo");
		String userId= (String) request.getSession().getAttribute("userName");
		
		String region= (String) request.getSession().getAttribute("region");
		String smcType= (String) request.getSession().getAttribute("smcType");
		
		//if (this.selectedReport == null)
		//{
			//execute();
			//setLblError(this.selectCategoryString);
			//return SUCCESS;
		//}
		//String rpt = getSelectedReport();
		
		//executePrint();
		System.out.println("inside 3........");
		Path path = new Path();
		System.out.println("inside 4........");
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
		

		
		String authCostCenters = "";
		

		HashMap<String, Object> param = new HashMap<String, Object>();
	 

		System.out.println("Application Number :  " + getApplicationNo());
		System.out.println("CC Number :  " + costCenterNo);
		param.put("@appNo", "'"+getApplicationNo()+"'");
		param.put("@costctr","'"+costCenterNo+"'");
		 
		
		if(getFileRef() != null && costCenterNo.equalsIgnoreCase("430.00")){
			
			param.put("@fileRef","'"+getFileRef()+"'");
		}else{
			param.put("@fileRef","'"+getFileRef()+"'");
		}
	
		
		String fileName = null;
		/*if( (smcType.equalsIgnoreCase(Constants.RUTAL_ELECTRIFICATION_I) || smcType.equalsIgnoreCase(Constants.RUTAL_ELECTRIFICATION)) ){
			fileName = rept.generateReport("DispatchofPIVRE",param,getSession(),REPORT_DIRECTORY,EXPORT_REPORT_DIRECTORY);
		}else{
			fileName = rept.generateReport("DispatchofPIV",param,getSession(),REPORT_DIRECTORY,EXPORT_REPORT_DIRECTORY);
		}*/
		
		if( (smcType.equalsIgnoreCase(Constants.RUTAL_ELECTRIFICATION_I) || smcType.equalsIgnoreCase(Constants.RUTAL_ELECTRIFICATION)) ){
			fileName = rept.generateReport("DispatchofPIVRE",param,getSession(),REPORT_DIRECTORY,EXPORT_REPORT_DIRECTORY);
		}else if((smcType.equalsIgnoreCase(Constants.CROSS_PAID))){
			fileName = rept.generateReport("DispatchofPIVCP",param,getSession(),REPORT_DIRECTORY,EXPORT_REPORT_DIRECTORY);
		}else{
			fileName = rept.generateReport("DispatchofPIV",param,getSession(),REPORT_DIRECTORY,EXPORT_REPORT_DIRECTORY);
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
	
	public String PrintDispatchOFPIVNew(){
		//setInfoFromMainMenu();
		HttpServletRequest request = ServletActionContext.getRequest();
		System.out.println("inside ViewGayani........");
		String costCenterNo=(String) request.getSession().getAttribute("costCenterNo");
		String userId= (String) request.getSession().getAttribute("userName");
		
		String region= (String) request.getSession().getAttribute("region");
		String smcType= (String) request.getSession().getAttribute("smcType");
		
		//if (this.selectedReport == null)
		//{
			//execute();
			//setLblError(this.selectCategoryString);
			//return SUCCESS;
		//}
		//String rpt = getSelectedReport();
		
		//executePrint();
		System.out.println("inside 3........");
		Path path = new Path();
		System.out.println("inside 4........");
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
		

		
		String authCostCenters = "";
		

		HashMap<String, Object> param = new HashMap<String, Object>();
	 

		System.out.println("Application Number :  " + getApplicationNo());
		System.out.println("CC Number :  " + costCenterNo);
		param.put("@appNo", "'"+getApplicationNo()+"'");
		param.put("@costctr","'"+costCenterNo+"'");
		 
		
		if(getFileRef() != null && costCenterNo.equalsIgnoreCase("430.00")){
			
			param.put("@fileRef","'"+getFileRef()+"'");
		}else{
			param.put("@fileRef","'"+getFileRef()+"'");
		}
	
		
		String fileName = null;
		/*if( (smcType.equalsIgnoreCase(Constants.RUTAL_ELECTRIFICATION_I) || smcType.equalsIgnoreCase(Constants.RUTAL_ELECTRIFICATION)) ){
			fileName = rept.generateReport("DispatchofPIVRE",param,getSession(),REPORT_DIRECTORY,EXPORT_REPORT_DIRECTORY);
		}else{
			fileName = rept.generateReport("DispatchofPIV",param,getSession(),REPORT_DIRECTORY,EXPORT_REPORT_DIRECTORY);
		}*/
		
		
		fileName = rept.generateReport("DispatchofPIV",param,getSession(),REPORT_DIRECTORY,EXPORT_REPORT_DIRECTORY);
		
		
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
	
	
	
	public String generateInvestigationReport(){
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
		System.out.println("Application Number :  " + getApplicationNo());
		param.put("@appNo", "'"+getApplicationNo()+"'");
		param.put("@costctr","'"+costCenterNo+"'");
		System.out.println("successprint 3 : "+ projectNo  + "nnn : " + costCenterNo);
		String fileName = null;
		try{
			fileName = rept.generateReport("allocation_Of_job_Investigation",param,getSession(),REPORT_DIRECTORY,EXPORT_REPORT_DIRECTORY);
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

	public void setContigency(String contigency) {
		this.contigency = contigency;
	}

	public String getContigency() {
		return contigency;
	}
	
	
	/**public String post()
	{
		
		HttpServletRequest request = ServletActionContext.getRequest();
		String region= (String) request.getSession().getAttribute("region");
		String costCenterNo=(String) request.getSession().getAttribute("costCenterNo");
		EstimateEjb estimateEjb = new EstimateEjb(region);
		System.out.println("AAAAAAAAAAAAAAAAAA");
		//String[] estimateNo= getHiddenEstimateNo().split(",");
		String usernamePostedTo = getPostUserName();
		//String totalCost = getTotalCost();
		//BigDecimal estimatedTotalCost = new BigDecimal(totalCost);
		
		boolean status = false;
		//if(estimateNo[i] != null && !estimateNo[i].equalsIgnoreCase(Constants.DEFAULT_STRING) && usernamePostedTo != null){
			//if(DetailsList!=null){
				System.out.println("BBBBBBBBBBBBBBBBBBB");
			Approval approval=new Approval();
			try{
					SecurityEjb secejb=new SecurityEjb(getSessionKey("region"));
					String userLevelPostedTo = secejb.getAuthorizedLevel(usernamePostedTo);
					String postinguserLevel = secejb.getAuthorizedLevel(userId);
					ApprovalEjb appejb = new ApprovalEjb(region);
					System.out.println("CCCCCCCCCCCCCCCCCCCCC");

					for(int i=0;i<DetailsList.length;i++){
						System.out.println("DDDDDDDDDDDDDDDDD");
					
					Calendar calendar = Calendar.getInstance();
					Format format=new Format();
					SpstdesthmtPK pk = new SpstdesthmtPK();
					pk.setApplicationNo(DetailsList[i]);
					System.out.println("Appno:"+DetailsList[i]);
					pk.setStdNo(DetailsList[i]);
					System.out.println("Values:"+DetailsList[i]);
				
					pk.setDeptId(costCenterNo);
					SpstdesthmtEjb ejb =  new SpstdesthmtEjb(getSessionKey("region"));
					Spstdesthmt hmt = ejb.findById(pk,getSessionKey("region"));
					if(hmt != null){
						System.out.println("HMT:"+hmt);
						approval.setApprovalId(null);
						approval.setReferenceNo(DetailsList[i].trim());
						approval.setDeptId(costCenterNo);
						approval.setApprovedDate(calendar.getTime());
						approval.setApprovedTime(format.FormatTime());
						approval.setStandardCost(hmt.getTotalCost());
					
						
						Long userStatus =null;
						if(userLevelPostedTo != null){
							if(userLevelPostedTo.trim().equalsIgnoreCase("ES")){
								//if(postinguserLevel.equalsIgnoreCase("DEO")){
									userStatus = StandardEstimateStatus.TO_BE_CHECKED_BY_ELECTRICAL_SUPIRINTENDENT.getKey();
									approval.setApprovedLevel(getSessionKey("userRole"));
									approval.setApprovedBy(getSessionKey("userRole"));
									approval.setApprovalType("EST_ENTY");
									
								//}else if(postinguserLevel.equalsIgnoreCase("ES")){
									//userStatus = StandardEstimateStatus.TO_BE_CHECKED_BY_ELECTRICAL_SUPIRINTENDENT.getKey();
									//approval.setApprovedLevel(getSessionKey("userRole"));
									//approval.setApprovedBy(getSessionKey("userRole"));
									//approval.setApprovalType("ES_CHEKD");
								//}
								
								
							}else if(userLevelPostedTo.trim().equalsIgnoreCase("EE")){
								userStatus = StandardEstimateStatus.TO_BE_VALIDATED_BY_ELECTRICAL_ENGINEER.getKey();
								approval.setApprovedLevel(getSessionKey("userRole"));
								approval.setApprovedBy(getSessionKey("userRole"));
								approval.setApprovalType("EST_ENTY");
								
							}else if(userLevelPostedTo.trim().equalsIgnoreCase("CE")){
								userStatus = StandardEstimateStatus.TO_BE_VALIDATED_BY_CE.getKey();
								approval.setApprovedLevel(getSessionKey("userRole"));
								approval.setApprovedBy(getSessionKey("userRole"));
								approval.setApprovalType("EST_ENTY");
								
							}else if(userLevelPostedTo.trim().equalsIgnoreCase("PE")){
								userStatus = StandardEstimateStatus.TO_BE_VALIDATED_BY_PLANNING_ENGINEER.getKey();
								approval.setApprovedLevel(getSessionKey("userRole"));
								approval.setApprovedBy(getSessionKey("userRole"));
								approval.setApprovalType("EST_ENTY");
								
							}else if(userLevelPostedTo.trim().equalsIgnoreCase("PCE")){
								userStatus = StandardEstimateStatus.TO_BE_VALIDATED_BY_PLANNING_CE.getKey();
								approval.setApprovedLevel(getSessionKey("userRole"));
								approval.setApprovedBy(getSessionKey("userRole"));
								approval.setApprovalType("EST_ENTY");
								
							}else if(userLevelPostedTo.trim().equalsIgnoreCase("DGM")){
								userStatus = StandardEstimateStatus.TO_BE_APPROVED_BY_DGM.getKey();
								approval.setApprovedLevel(getSessionKey("userRole"));
								approval.setApprovedBy(getSessionKey("userRole"));
								approval.setApprovalType("EST_ENTY");
								
							}
						}
						
						//status= estimateEjb.updateEstimateStatus(estimateNo[0], costCenterNo, userStatus,null,null,null,null,null,null,null,null,null,null,null,null,null, region);
						hmt.setStatus(userStatus);
						hmt.setAssignTo(usernamePostedTo);
						ejb.updateSpstdesthmt(hmt, getSessionKey("region"));
						approval.setFromStatus(StandardEstimateStatus.APPLICATION_MODIFIED.getKey().toString());
						
						approval.setToStatus(userStatus.toString());
					}
					
				}
				appejb.createAutoIdSEstmateApprovals(approval);
				setMessageType(MSG_DONE);
				setErrorMessage("DONE:- Successfully Posted for " + usernamePostedTo);
			}catch(Exception e){
				return ERROR;
			}
		//i++;
		//}else{
			//setMessageType(MSG_INFO);
			//setErrorMessage("Select required Details");
		//}
		
		
				
		
		
		//if(status){
			
		//}
		//fillEstimateList();
		loaduserIds();
		return SUCCESS;
	}*/
	
	public String post() throws Exception
	{
		try{
	
		
		HttpServletRequest request = ServletActionContext.getRequest();
		String region= (String) request.getSession().getAttribute("region");
		String costCenterNo=(String) request.getSession().getAttribute("costCenterNo");
		String userId= (String) request.getSession().getAttribute("userName");
		EstimateEjb estimateEjb = new EstimateEjb(region);
		ApprovalEjb appejb = new ApprovalEjb(region);
		
		//*SpstdesthmtEjb ejb =  new SpstdesthmtEjb(getSessionKey("region"));
		SpstdesthmtEjb spstdesthmtEjb =  new SpstdesthmtEjb(getSessionKey("region"));
		//String[] estimateNo=getDetailList();
		// estimateNo= getHiddenEstimateNo().split(",");
		//System.out.println("AAAA"+getDetails());
		//System.out.println("yyyy"+getHiddenEstimateNo().split(","));
		//System.out.println("BBB"+estimateNo);
		String usernamePostedTo = getPostUserName();
		System.out.println("CCC"+usernamePostedTo);
		//String totalCost = getTotalCost();
		//BigDecimal estimatedTotalCost = new BigDecimal(totalCost);
		Approval approval=new Approval();
		//boolean status = false;
		
		String estimateNo = applicationNo;
		if(  usernamePostedTo != null && estimateNo != null){
			System.out.println("tttttttttttt");
			System.out.println("EstimateList");
			
			//for( int i=0;i<estimateNo.length;i++){
									
				
				//System.out.println("QQQQQ"+getApplicationId());
				//System.out.println("PPPP"+applicationId);
				
					SecurityEjb secejb=new SecurityEjb(getSessionKey("region"));
					String userLevelPostedTo = secejb.getAuthorizedLevel(usernamePostedTo);
					String postinguserLevel = secejb.getAuthorizedLevel(userId);
					//ApprovalEjb appejb = new ApprovalEjb(region);
					//for( int i=0;i<estimateNo.length;){
					
						// estimateNo= getHiddenEstimateNo().split(",");
						 System.out.println("BBB"+estimateNo);
					Calendar calendar = Calendar.getInstance();
					Format format=new Format();
					SpstdesthmtPK pk = new SpstdesthmtPK();
					pk.setApplicationNo(estimateNo);
					System.out.println("Application Number"+estimateNo);
					
					pk.setStdNo(estimateNo);
					System.out.println("Estimate Number"+estimateNo);
					pk.setDeptId(costCenterNo);
					//SpstdesthmtEjb ejb =  new SpstdesthmtEjb(getSessionKey("region"));
					//*Spstdesthmt hmt = ejb.findById(pk,getSessionKey("region"));
					Spstdesthmt spstdesthmt=new Spstdesthmt();
					spstdesthmt=spstdesthmtEjb.findById(pk,getSessionKey("region"));
					System.out.println("KKKKKKK"+spstdesthmt);
					
				if(spstdesthmt != null){
						
						approval.setApprovalId(null);
						approval.setReferenceNo(estimateNo.trim());
						approval.setDeptId(costCenterNo);
						approval.setApprovedDate(calendar.getTime());
						approval.setApprovedTime(format.FormatTime());
						approval.setStandardCost(spstdesthmt.getTotalCost());
					
						
						Long userStatus =null;
						if(userLevelPostedTo != null){
							if(userLevelPostedTo.trim().equalsIgnoreCase("ES")){
								//if(postinguserLevel.equalsIgnoreCase("DEO")){
									userStatus = StandardEstimateStatus.TO_BE_CHECKED_BY_ELECTRICAL_SUPIRINTENDENT.getKey();
									approval.setApprovedLevel(getSessionKey("userRole"));
									approval.setApprovedBy(getSessionKey("userRole"));
									approval.setApprovalType("EST_ENTY");
									
								//}else if(postinguserLevel.equalsIgnoreCase("ES")){
									//userStatus = StandardEstimateStatus.TO_BE_CHECKED_BY_ELECTRICAL_SUPIRINTENDENT.getKey();
									//approval.setApprovedLevel(getSessionKey("userRole"));
									//approval.setApprovedBy(getSessionKey("userRole"));
									//approval.setApprovalType("ES_CHEKD");
								//}
								
								
							}else if(userLevelPostedTo.trim().equalsIgnoreCase("EE")){
								userStatus = StandardEstimateStatus.TO_BE_VALIDATED_BY_ELECTRICAL_ENGINEER.getKey();
								approval.setApprovedLevel(getSessionKey("userRole"));
								approval.setApprovedBy(getSessionKey("userRole"));
								approval.setApprovalType("EST_ENTY");
								
							}else if(userLevelPostedTo.trim().equalsIgnoreCase("CE")){
								userStatus = StandardEstimateStatus.TO_BE_VALIDATED_BY_CE.getKey();
								approval.setApprovedLevel(getSessionKey("userRole"));
								approval.setApprovedBy(getSessionKey("userRole"));
								approval.setApprovalType("EST_ENTY");
								
							}else if(userLevelPostedTo.trim().equalsIgnoreCase("PE")){
								userStatus = StandardEstimateStatus.TO_BE_VALIDATED_BY_PLANNING_ENGINEER.getKey();
								approval.setApprovedLevel(getSessionKey("userRole"));
								approval.setApprovedBy(getSessionKey("userRole"));
								approval.setApprovalType("EST_ENTY");
								
							}else if(userLevelPostedTo.trim().equalsIgnoreCase("PCE")){
								userStatus = StandardEstimateStatus.TO_BE_VALIDATED_BY_PLANNING_CE.getKey();
								approval.setApprovedLevel(getSessionKey("userRole"));
								approval.setApprovedBy(getSessionKey("userRole"));
								approval.setApprovalType("EST_ENTY");
								
							}else if(userLevelPostedTo.trim().equalsIgnoreCase("DGM")){
								userStatus = StandardEstimateStatus.TO_BE_APPROVED_BY_DGM.getKey();
								approval.setApprovedLevel(getSessionKey("userRole"));
								approval.setApprovedBy(getSessionKey("userRole"));
								approval.setApprovalType("EST_ENTY");
								
							}
						}
					
						//status= estimateEjb.updateEstimateStatus(estimateNo[0], costCenterNo, userStatus,null,null,null,null,null,null,null,null,null,null,null,null,null, region);
						spstdesthmt.setStatus(userStatus);
						spstdesthmt.setAssignTo(usernamePostedTo);
						spstdesthmtEjb.updateSpstdesthmt(spstdesthmt, getSessionKey("region"));
						approval.setFromStatus(StandardEstimateStatus.APPLICATION_MODIFIED.getKey().toString());
						
						approval.setToStatus(userStatus.toString());
					
						//i++;
						//}
					
				
				//}
				
				appejb.createAutoIdSEstmateApprovals(approval);
				setMessageType(MSG_DONE);
				setErrorMessage("DONE:- **Successfully Posted for " + usernamePostedTo);
			
					}
					
					}
		
			else{
				setMessageType(MSG_INFO);
				setErrorMessage("Select required Details");
			}
		}catch(Exception e){
				
				return ERROR;
			}
			
			
		
		
				
		
			//if(status){
			
		
		//fillEstimateList();
		loadEstimationRefNumbers();
		loaduserIds();
		return SUCCESS;
	}

	
	public String changePostStatus() throws Exception
	{
		try{
	
		
		HttpServletRequest request = ServletActionContext.getRequest();
		String region= (String) request.getSession().getAttribute("region");
		String costCenterNo=(String) request.getSession().getAttribute("costCenterNo");
		String userId= (String) request.getSession().getAttribute("userName");
		setLoggedInUserLevel(getSession().get("userRole").toString());
		EstimateEjb estimateEjb = new EstimateEjb(region);
		ApprovalEjb appejb = new ApprovalEjb(region);
		SpstdesthmtEjb spstdesthmtEjb =  new SpstdesthmtEjb(getSessionKey("region"));
		String logingUserRole = getLoggedInUserLevel();
		
		System.out.println("CCC"+ logingUserRole+" getStatus(); "+ getStatus() +"getPostDeptID(); "+ getPostId());
		
		Approval approval=new Approval();
		
		String estimateNo = applicationNo;
		if( logingUserRole != null && estimateNo != null){
			SecurityEjb secejb=new SecurityEjb(getSessionKey("region"));
			System.out.println("BBB"+estimateNo);
			Calendar calendar = Calendar.getInstance();
			Format format=new Format();
			SpstdesthmtPK pk = new SpstdesthmtPK();
			pk.setApplicationNo(estimateNo);
			System.out.println("Application Number"+estimateNo);
			pk.setStdNo(estimateNo);
			System.out.println("Estimate Number"+estimateNo);
			pk.setDeptId(costCenterNo);
			Spstdesthmt spstdesthmt=new Spstdesthmt();
			spstdesthmt=spstdesthmtEjb.findById(pk,getSessionKey("region"));
			System.out.println("KKKKKKK"+spstdesthmt);
					
				if(spstdesthmt != null){
						
					    
						approval.setApprovalId(null);
						approval.setReferenceNo(estimateNo.trim());
						approval.setDeptId(costCenterNo);
						approval.setApprovedDate(calendar.getTime());
						approval.setApprovedTime(format.FormatTime());
						approval.setStandardCost(spstdesthmt.getTotalCost());
					
						
						Long userStatus =null;
						if(getLoggedInUserLevel() != null){
							if(getLoggedInUserLevel().trim().equalsIgnoreCase("EE")){
								//userStatus = StandardEstimateStatus.TO_BE_VALIDATED_BY_ELECTRICAL_ENGINEER.getKey();
								//userStatus = StandardEstimateStatus.TO_BE_VALIDATED_BY_ELECTRICAL_ENGINEER.getKey();
								userStatus = 90L;
								approval.setApprovedLevel(getSessionKey("userRole"));
								approval.setApprovedBy(getSessionKey("userName"));
								approval.setApprovalType("EST_CPS");
								
							}else if(getLoggedInUserLevel().trim().equalsIgnoreCase("TECH")){
								//userStatus = StandardEstimateStatus.TO_BE_VALIDATED_BY_CE.getKey();
								userStatus = 90L;
								approval.setApprovedLevel(getSessionKey("userRole"));
								approval.setApprovedBy(getSessionKey("userName"));
								approval.setApprovalType("EST_CPS");
								
							}else if(getLoggedInUserLevel().trim().equalsIgnoreCase("CE")){
								//userStatus = StandardEstimateStatus.TO_BE_VALIDATED_BY_CE.getKey();
								userStatus = 90L;
								approval.setApprovedLevel(getSessionKey("userRole"));
								approval.setApprovedBy(getSessionKey("userName"));
								approval.setApprovalType("EST_CPS");
								
							}else if(getLoggedInUserLevel().trim().equalsIgnoreCase("PE")){
								//userStatus = StandardEstimateStatus.TO_BE_VALIDATED_BY_PLANNING_ENGINEER.getKey();
								userStatus = 90L;
								approval.setApprovedLevel(getSessionKey("userRole"));
								approval.setApprovedBy(getSessionKey("userName"));
								approval.setApprovalType("EST_CPS");
								
							}else if(getLoggedInUserLevel().trim().equalsIgnoreCase("PCE")){
								//userStatus = StandardEstimateStatus.TO_BE_VALIDATED_BY_PLANNING_CE.getKey();
								userStatus = 90L;
								approval.setApprovedLevel(getSessionKey("userRole"));
								approval.setApprovedBy(getSessionKey("userName"));
								approval.setApprovalType("EST_CPS");
								
							}else if(getLoggedInUserLevel().trim().equalsIgnoreCase("DGM")){
								//userStatus = StandardEstimateStatus.TO_BE_APPROVED_BY_DGM.getKey();
								userStatus = 90L;
								approval.setApprovedLevel(getSessionKey("userRole"));
								approval.setApprovedBy(getSessionKey("userName"));
								approval.setApprovalType("EST_CPS");
								
							}
							Long status = spstdesthmt.getStatus();
						    Long cStatus = getChgStatus();
						    
						    System.out.println("CCC"+ logingUserRole+"curStatus(" + status +" changeStatus(); "+ cStatus);
							
						if(cStatus == -1){
							//spstdesthmt.setStatus(status);
							System.out.println("hi");
							approval.setFromStatus(status.toString());
							approval.setToStatus(status.toString());
						}else{
							System.out.println("hii");
							spstdesthmt.setStatus(cStatus);
							approval.setFromStatus(status.toString());
							approval.setToStatus(cStatus.toString());
						}
						if(getPostId()=="-Select-"){
							
						}else{
							
						}
						
						
						System.out.println("getPostId() "+ getPostId() + "sysID : " + spstdesthmt.getPostDeptId()); 
						
						if(cStatus == -1 && getPostId()=="-Select-"){
							setMessageType(MSG_DONE);
							setErrorMessage("You have not done any changes for " + estimateNo);
							System.out.println("hiii");
						}
						else if (cStatus == -1 && getPostId()!="-Select-" && getPostId().equalsIgnoreCase(spstdesthmt.getPostDeptId())){
							setMessageType(MSG_DONE);
							setErrorMessage("You have not done any changes for " + estimateNo);
							System.out.println("hiiii");
							//spstdesthmtEjb.updateSpstdesthmt(spstdesthmt, getSessionKey("region"));
							//approval.setReason(getRejectedReason());
							//appejb.createAutoIdSEstmateApprovals(approval);
							//setMessageType(MSG_DONE);
							//setErrorMessage("DONE:- **Successfully Changed Post DeptID for : " + estimateNo + "AS : " + getPostId());
				
						}else if (cStatus != -1 && getPostId()=="-Select-" ){
							spstdesthmt.setStatus(cStatus);
							approval.setFromStatus(status.toString());
							approval.setToStatus(cStatus.toString());
							spstdesthmtEjb.updateSpstdesthmt(spstdesthmt, getSessionKey("region"));
							approval.setReason(getRejectedReason());
							appejb.createAutoIdSEstmateApprovals(approval);
							setMessageType(MSG_DONE);
							setErrorMessage("HDONE:- **Successfully Changed Status for : " + estimateNo );
							System.out.println("hiiiii");
						}else if (cStatus != -1 && getPostId().equalsIgnoreCase(spstdesthmt.getPostDeptId())){
							spstdesthmt.setStatus(cStatus);
							approval.setFromStatus(status.toString());
							approval.setToStatus(cStatus.toString());
							//spstdesthmt.setPostDeptId(getPostId());
							spstdesthmtEjb.updateSpstdesthmt(spstdesthmt, getSessionKey("region"));
							approval.setReason(getRejectedReason());
							appejb.createAutoIdSEstmateApprovals(approval);
							setMessageType(MSG_DONE);
							setErrorMessage("DONE:- **Successfully Changed Status for : : " + estimateNo );
							System.out.println("hiiiiiii");
						}else if (cStatus == -1 && !getPostId().equalsIgnoreCase(spstdesthmt.getPostDeptId())){
							//spstdesthmt.setStatus(cStatus);
							approval.setFromStatus(status.toString());
							approval.setToStatus(status.toString());
							spstdesthmt.setPostDeptId(getPostId());
							spstdesthmtEjb.updateSpstdesthmt(spstdesthmt, getSessionKey("region"));
							approval.setReason(getRejectedReason());
							appejb.createAutoIdSEstmateApprovals(approval);
							setMessageType(MSG_DONE);
							setErrorMessage("DONE:- **Successfully Changed Post DeptID for : : " + estimateNo );
							System.out.println("hiie");
						}else if (cStatus != -1 && !getPostId().equalsIgnoreCase(spstdesthmt.getPostDeptId())){
							spstdesthmt.setStatus(cStatus);
							approval.setFromStatus(status.toString());
							approval.setToStatus(cStatus.toString());
							spstdesthmt.setPostDeptId(getPostId());
							spstdesthmtEjb.updateSpstdesthmt(spstdesthmt, getSessionKey("region"));
							approval.setReason(getRejectedReason());
							appejb.createAutoIdSEstmateApprovals(approval);
							setMessageType(MSG_DONE);
							setErrorMessage("DONE:- **Successfully Changed Status and Post DeptID for : : " + estimateNo );
							System.out.println("hiiee");
						}
						
						}
						
					}
					
				
					}
		
		
			else{
				setMessageType(MSG_INFO);
				setErrorMessage("Select required Details");
			}
		}catch(Exception e){
			System.out.println("Exception"+e);
				return ERROR;
		}
		
		loadSTDEstimatesForCPS();
		loaduserIds();
		loadPostIds();
		return SUCCESS;
	}
	
	public String jobcancel() throws Exception
	{
		try{
	
		
		HttpServletRequest request = ServletActionContext.getRequest();
		String region= (String) request.getSession().getAttribute("region");
		String costCenterNo=(String) request.getSession().getAttribute("costCenterNo");
		String userId= (String) request.getSession().getAttribute("userName");
		setLoggedInUserLevel(getSession().get("userRole").toString());
		EstimateEjb estimateEjb = new EstimateEjb(region);
		ApprovalEjb appejb = new ApprovalEjb(region);
		SpstdesthmtEjb spstdesthmtEjb =  new SpstdesthmtEjb(getSessionKey("region"));
		String logingUserRole = getLoggedInUserLevel();
		
		System.out.println("CCC"+ logingUserRole+" getStatus(); "+ getStatus());
		
		Approval approval=new Approval();
		
		String estimateNo = applicationNo;
		if( logingUserRole != null && estimateNo != null){
			SecurityEjb secejb=new SecurityEjb(getSessionKey("region"));
			System.out.println("BBB"+estimateNo);
			Calendar calendar = Calendar.getInstance();
			Format format=new Format();
			SpstdesthmtPK pk = new SpstdesthmtPK();
			pk.setApplicationNo(estimateNo);
			System.out.println("Application Number"+estimateNo);
			pk.setStdNo(estimateNo);
			System.out.println("Estimate Number"+estimateNo);
			pk.setDeptId(costCenterNo);
			Spstdesthmt spstdesthmt=new Spstdesthmt();
			spstdesthmt=spstdesthmtEjb.findById(pk,getSessionKey("region"));
			System.out.println("KKKKKKK"+spstdesthmt);
					
				if(spstdesthmt != null){
						
					    Long status = spstdesthmt.getStatus();
					    System.out.println("CCC"+ logingUserRole+" getStatus(); "+ status);
						
						approval.setApprovalId(null);
						approval.setReferenceNo(estimateNo.trim());
						approval.setDeptId(costCenterNo);
						approval.setApprovedDate(calendar.getTime());
						approval.setApprovedTime(format.FormatTime());
						approval.setStandardCost(spstdesthmt.getTotalCost());
					
						
						Long userStatus =null;
						if(getLoggedInUserLevel() != null){
							if(getLoggedInUserLevel().trim().equalsIgnoreCase("EE")){
								//userStatus = StandardEstimateStatus.TO_BE_VALIDATED_BY_ELECTRICAL_ENGINEER.getKey();
								//userStatus = StandardEstimateStatus.TO_BE_VALIDATED_BY_ELECTRICAL_ENGINEER.getKey();
								userStatus = 90L;
								approval.setApprovedLevel(getSessionKey("userRole"));
								approval.setApprovedBy(getSessionKey("userName"));
								approval.setApprovalType("EST_CNL");
								
							}else if(getLoggedInUserLevel().trim().equalsIgnoreCase("TECH")){
								//userStatus = StandardEstimateStatus.TO_BE_VALIDATED_BY_CE.getKey();
								userStatus = 90L;
								approval.setApprovedLevel(getSessionKey("userRole"));
								approval.setApprovedBy(getSessionKey("userName"));
								approval.setApprovalType("EST_CNL");
								
							}
							else if(getLoggedInUserLevel().trim().equalsIgnoreCase("CE")){
								//userStatus = StandardEstimateStatus.TO_BE_VALIDATED_BY_CE.getKey();
								userStatus = 90L;
								approval.setApprovedLevel(getSessionKey("userRole"));
								approval.setApprovedBy(getSessionKey("userName"));
								approval.setApprovalType("EST_CNL");
								
							}else if(getLoggedInUserLevel().trim().equalsIgnoreCase("PE")){
								//userStatus = StandardEstimateStatus.TO_BE_VALIDATED_BY_PLANNING_ENGINEER.getKey();
								userStatus = 90L;
								approval.setApprovedLevel(getSessionKey("userRole"));
								approval.setApprovedBy(getSessionKey("userName"));
								approval.setApprovalType("EST_CNL");
								
							}else if(getLoggedInUserLevel().trim().equalsIgnoreCase("PCE")){
								//userStatus = StandardEstimateStatus.TO_BE_VALIDATED_BY_PLANNING_CE.getKey();
								userStatus = 90L;
								approval.setApprovedLevel(getSessionKey("userRole"));
								approval.setApprovedBy(getSessionKey("userName"));
								approval.setApprovalType("EST_CNL");
								
							}else if(getLoggedInUserLevel().trim().equalsIgnoreCase("DGM")){
								//userStatus = StandardEstimateStatus.TO_BE_APPROVED_BY_DGM.getKey();
								userStatus = 90L;
								approval.setApprovedLevel(getSessionKey("userRole"));
								approval.setApprovedBy(getSessionKey("userName"));
								approval.setApprovalType("EST_CNL");
								
							}
						
						spstdesthmt.setStatus(userStatus);
						spstdesthmtEjb.updateSpstdesthmt(spstdesthmt, getSessionKey("region"));
						approval.setFromStatus(status.toString());
						approval.setToStatus(userStatus.toString());
						approval.setReason(getRejectedReason());
						appejb.createAutoIdSEstmateApprovals(approval);
						}
				setMessageType(MSG_DONE);
				setErrorMessage("DONE:- **Successfully Cancelled Estimate No : " + estimateNo);
			
					}
					
					}
		
			else{
				setMessageType(MSG_INFO);
				setErrorMessage("Select required Details");
			}
		}catch(Exception e){
			System.out.println("Exception"+e);
				return ERROR;
		}
		
		loadSTDEstimatesForCancel();
		loaduserIds();
		return SUCCESS;
	}
	
	
	private void loaduserIds(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String region= (String) request.getSession().getAttribute("region");
		String smcType= (String) request.getSession().getAttribute("smcType");
		String costCenterNo=(String) request.getSession().getAttribute("costCenterNo");
		SecurityEjb secejb=new SecurityEjb(getSessionKey("region"));
		SpestedyEjb spestedyEjb=new SpestedyEjb(getSessionKey("region"));
		//DW
		List<String> usersList = new ArrayList<String>();
		
		if(getSessionKey("userRole").equalsIgnoreCase("EE")){
			usersList.add("PE");
			usersList.add("CE");
			usersList.add("DGM");
		}else{
			usersList.add("ES");
			usersList.add("EE");
			usersList.add("PE");
			
		}
		//usersList.add("DGM");
		
		if(smcType.equalsIgnoreCase(Constants.RUTAL_ELECTRIFICATION) || smcType.equalsIgnoreCase(Constants.BS) || smcType.equalsIgnoreCase(Constants.LAND) || smcType.equalsIgnoreCase(Constants.RUTAL_ELECTRIFICATION_I)){
			usersList.add("PE");
			
		}
		if(smcType.equalsIgnoreCase(Constants.PLANNING)){
			usersList.add("PE");
			usersList.add("PCE");
			usersList.add("DGM");
		}
		List<String> userList = secejb.getUserList(costCenterNo,usersList);
		//DW
		
		Iterator<String> usit = userList.iterator();
		
		 while (usit.hasNext()) {        	 
	        	String esUser=usit.next();
	        	getListuserName().add(esUser);		       	        	        	
	          } 
	}
	
	public String getPostUserName() {
		return postUserName;
	}
	public void setPostUserName(String postUserName) {
		this.postUserName = postUserName;
	}

	public void setListuserName(List<String> listuserName) {
		this.listuserName = listuserName;
	}

	public List<String> getListuserName() {
		return listuserName;
	}
	
	public String sendForCM()
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		String region= (String) request.getSession().getAttribute("region");
		String costCenterNo=(String) request.getSession().getAttribute("costCenterNo");
		SpstdesthmtEjb spstdesthmtEjb = new SpstdesthmtEjb(region);
		
		//String[] estimateNo= getHiddenEstimateNo().split(",");
		//System.out.println("test : " + estimateNo.length);
		//System.out.println("test : " + estimateNo[0] + " " + estimateNo[1]);
		String postDeptId= null;
		//if(!getPostId().equalsIgnoreCase(Constants.DEFAULT_STRING_SELECT)){
			//postDeptId= getPostId();
		//}
		
		int status = 0;
		
		//for(int i= 0 ; i < estimateNo.length ; i++){
			//System.out.println("test : " + estimateNo[i]);
			//System.out.println("test : " + i);
		//}
		/**if(estimateNo[0] != null && postDeptId != null){
			SpstdesthmtPK spstdesthmtPK = new SpstdesthmtPK();
			spstdesthmtPK.setDeptId(costCenterNo);
			spstdesthmtPK.setStdNo(estimateNo[0].trim());
			spstdesthmtPK.setApplicationNo(estimateNo[0].trim());
			Spstdesthmt hmt = spstdesthmtEjb.findById(spstdesthmtPK, region);
			
			if(hmt != null){
				
				status= spstdesthmtEjb.updateSendForCMStatus(estimateNo[0], costCenterNo, StandardEstimateStatus.SEND_FOR_CONSTRUCTION_MAINTENANCE.getKey(),postDeptId, region);
			}else{
				ApplicationEjb ejb = new ApplicationEjb(region);
			
				status = ejb.changeStatusApplication(estimateNo[0], costCenterNo,AppStatus.REQUEST_FOR_ESTIMATION, region);
			}
			
		}else{
			setMessageType(MSG_INFO);
			setErrorMessage("Select required Details");
		}*/
		
		
		if(status==1){
			setMessageType(MSG_DONE);
			setErrorMessage("DONE:- Successfully Posted for "+postDeptId);
		}
		loadPostIds();
		
		return SUCCESS;

	}
	
	private void loadPostIds(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String region= (String) request.getSession().getAttribute("region");
		String costCenterNo=(String) request.getSession().getAttribute("costCenterNo");
		
		GldeptmEjb ejb = new GldeptmEjb(region);
		String costcenNo = costCenterNo.substring(0, 2);
		if(costcenNo != null){
			try {
				postIdList = new ArrayList<String>();
				postIdList.add(Constants.DEFAULT_STRING_SELECT);
				if(ejb.findAreaCodesForPost(costCenterNo) != null){
					postIdList.addAll(ejb.findAreaCodesForPost(costCenterNo));
			    }
				
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public List<String> getPostIdList() {
		return postIdList;
	}
	public void setPostIdList(List<String> postIdList) {
		this.postIdList = postIdList;
	}
	
	
	public String getPostId() {
		return postId;
	}
	public void setPostId(String postId) {
		this.postId = postId;
	}
	
	public Long getChgStatus() {
		return chgStatus;
	}
	public void setChgStatus(Long chgStatus) {
		this.chgStatus = chgStatus;
	}
	public String getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}
	
	public String getFileUploadFileName() {
		return fileUploadFileName;
	}

	public void setFileUploadFileName(String fileUploadFileName) {
		this.fileUploadFileName = fileUploadFileName;
	}
	
	public File getFileUpload() {
		return fileUpload;
	}

	public void setFileUpload(File fileUpload) {
		this.fileUpload = fileUpload;
	}
	
	public String getFileUploadContentType() {
		return fileUploadContentType;
	}

	public void setFileUploadContentType(String fileUploadContentType) {
		this.fileUploadContentType = fileUploadContentType;
	}
	
	public String ViewSketch(){
		
		/**HttpServletRequest request = ServletActionContext.getRequest();
			
		String appno=this.getApplicationNo();
    	String appNo=appno.replaceAll("/", ".");
			Path path = new Path();
			String pdfPath = "";
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
			
			

				String fileName = REPORT_DIRECTORY + appNo + ".pdf";

			try {
				if (fileName == "")
				{
					setLblError("Error occured while generating the report");
					return SUCCESS;
				}
				fileInputStream = new FileInputStream(fileName);
			} catch (FileNotFoundException e) {
				
				e.printStackTrace();
			}
			return "successprint";*/
		
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
		
        String fileName = null;
		String appno=this.getApplicationNo();
    	String appNo=appno.replaceAll("/", ".");
			System.out.println("Hii DDDDDD");
			fileName = REPORT_DIRECTORY + appNo + ".pdf";
		
		
		System.out.println("inside ViewGayani");
		System.out.println("xxxxxxxxx"+ fileName);
		
		
		
		try {
			if (fileName == "")
			{
				setLblError("Error occured while generating the report");
				return SUCCESS;
			}
			fileInputStream = new FileInputStream(fileName);
			
			if(fileInputStream == null){
				setLblError("Sketch Is Not Attached");
				return SUCCESS;
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		
			setLblError("Sketch Is Not Attached");
			return SUCCESS;
		}
		return "successprint";



		}
    public String doUpload() {
		
		/**SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
    	String date = sdf.format(new Date());
    	String appno=this.getApplicationNo();
    	String appNo=appno.replaceAll("/", "");
    	
       // File saveFilePath = new File("E:/Upload/" +date+" "+appNo+" "+fileUploadFileName);
        Path path = new Path();
        String REPORT_DIRECTORY = "" ;
		String EXPORT_REPORT_DIRECTORY = "";
		if (System.getProperty("os.name").equals("Windows XP") || System.getProperty("os.name").equals("Windows 7") ){
			REPORT_DIRECTORY = path.getReportPath("REPORT_DIRECTORY");
			EXPORT_REPORT_DIRECTORY = path.getReportPath("EXPORT_REPORT_DIRECTORY");

		}else{
			REPORT_DIRECTORY = path.getReportPath("REPORT_DIRECTORY_LINUX");
			EXPORT_REPORT_DIRECTORY = path.getReportPath("EXPORT_REPORT_DIRECTORY_LINUX");
		}
		
		File saveFilePath = new File(REPORT_DIRECTORY +appNo);
        System.out.println(fileUpload+"222222222222222222 :"+saveFilePath);
        try {
            FileUtils.copyFile(fileUpload, saveFilePath);
            System.out.println(fileUpload+"2222222222222222222222222222222222222222222");
        } catch (IOException ex) {
            System.out.println("Couldn't save file: " + ex.getMessage());
        }
        return SUCCESS;*/
    	SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
    	
    	String appno=this.getApplicationNo();
    	String appNo=appno.replaceAll("/", ".");
    	
    	String ext = FilenameUtils.getExtension(fileUploadFileName);
    	//Check for PDF files
    	if(ext.equals("pdf"))
    	{
    		
    		Path path = new Path();
    		 String REPORT_DIRECTORY = "" ;
    			String EXPORT_REPORT_DIRECTORY = "";
    			if (System.getProperty("os.name").equals("Windows XP") || System.getProperty("os.name").equals("Windows 7") ){
    				REPORT_DIRECTORY = path.getReportPath("REPORT_DIRECTORY");
    				EXPORT_REPORT_DIRECTORY = path.getReportPath("EXPORT_REPORT_DIRECTORY");

    			}else{
    				REPORT_DIRECTORY = path.getReportPath("REPORT_DIRECTORY_LINUX");
    				EXPORT_REPORT_DIRECTORY = path.getReportPath("EXPORT_REPORT_DIRECTORY_LINUX");
    			}
    		File folder = new File(REPORT_DIRECTORY);
    		
    		
    		File[] listOfFiles = folder.listFiles();
    	
    	    for (int i = 0; i < listOfFiles.length; i++) 
    	    {
    	    	//Check For Existing files
    	    	if (listOfFiles[i].isFile()) 
    	    	{
    	    		String filename=listOfFiles[i].getName();
    	    		if(filename.equals(appNo+"."+ext))
    	    		{
    	    		  listOfFiles[i].delete();
    	    		}
    	        
    	    	} 
    	    	else if (listOfFiles[i].isDirectory()) 
    	    	{
    	        	System.out.println("Directory " + listOfFiles[i].getName());
    	      	}
    	    }
    	        	    
    	       	File saveFilePath = new File(REPORT_DIRECTORY+appNo+"."+ext);
    	       	try 
    	       	{      
    	       		 FileUtils.copyFile(fileUpload, saveFilePath);    
    	       		 this.setLblSuccess("Successfully Uploaded your file "+appNo+"."+ext);
    	       	} 
    	       	catch (IOException ex) 
    	       	{
                 ex.printStackTrace();
    	       	}
    	}    	
    	else
    	{
    		this.setLblError("Can't Upload "+ext+" fomat files Select a PDF");
    		
    	}
    	initUpdate();
        return SUCCESS;
    }
        
        
public String sinInfo(){
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
		System.out.println("Application Number :  " + getApplicationNo());
		param.put("@sinNo1", "'"+getSinNo1()+"'");
		param.put("@sinNo2", "'"+getSinNo2()+"'");
		param.put("@sinNo3", "'"+getSinNo3()+"'");
		param.put("@sinNo4", "'"+getSinNo4()+"'");
		param.put("@costctr","'"+costCenterNo+"'");
		System.out.println("successprint 3 : "+ getSinNo()  + "nnn : " + costCenterNo);
		String fileName = null;
		try{
			fileName = rept.generateReport("SinInfo",param,getSession(),REPORT_DIRECTORY,EXPORT_REPORT_DIRECTORY);
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
        
        
        
        
   // }
}//End of class
