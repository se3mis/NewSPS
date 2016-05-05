package estimate.web;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.NumberFormat;

import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.SessionAware;
//import org.apache.tomcat.jni.Status;

import reports.web.report;
import security.service.SecurityEjb;

import util.common.ApplicationStatus;
import util.common.Constants;
import util.common.EstimateStatus;
import util.common.Format;
import util.common.Path;
import util.common.StandardEstimateStatus;


import application.model.Application;
import application.service.ApplicationEjb;

import com.opensymphony.xwork2.ActionSupport;

import costcenter.service.GldeptmEjb;


import estimate.dto.ApproveDetails;
import estimate.model.Approval;
import estimate.model.Pcesthtt;
import estimate.model.Splaborm;
import estimate.model.Spstdesthmt;
import estimate.model.SpstdesthmtPK;
import estimate.service.ApprovalEjb;
import estimate.service.EstimateEjb;
import estimate.service.PcesthttEjb;
import estimate.service.SpestedyEjb;
import estimate.service.SplabormEjb;
import estimate.service.SpstdesthmtEjb;
 

public class SendForValidationBS extends ActionSupport implements SessionAware, ParameterAware  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map<String, Object> session;
	private Map <String, String[]> parameters;
	private String loggedInUserId;
	private String loggedInUserLevel;
	private String userId;
	private String password;
	private String region;
	private String costCenterNo;
	private String applicationType;
	private String path;
	private String lblError = null;
	private String lblSuccess = null;
	private String applicationId;
	private String[] DetailsList;
	private String recomReason;
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
	
	public String getSinNo() {
		return sinNo;
	}
	public void setSinNo(String sinNo) {
		this.sinNo = sinNo;
	}
	public String getRecomReason() {
		return recomReason;
	}
	public void setRecomReason(String recomReason) {
		this.recomReason = recomReason;
	}

	public String[] getDetailsList() {
		return DetailsList;
	}
	public void setDetailsList(String[] detailsList) {
		DetailsList = detailsList;
	}
	public String getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}

	private String hiddenEstimateNo;
	private List<Spstdesthmt> EstimateList;

	private static final String MSG_ERROR="ERROR";
	private static final String MSG_INFO="INFO";
	private static final String MSG_DONE="DONE";
	private static final String MSG_NONE="NONE";
	private String errorMessage;
	private String messageType=MSG_NONE;
	private String applicationNo;
	private String rejectedReason;
	private String totalCost;

	//print variables
	private  String selectCategoryString = "----Select Category-----";
	private String selectedReport;
	private static final String viewPath="Reports>Select Report";
	NumberFormat nf = NumberFormat.getInstance();
	private InputStream fileInputStream;
	private String entryBy;
	private String entryDate;
	private List<String> listuserName=new ArrayList<String>();
	private String postUserName;
	
	private String fileRef ;
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
	public String execute(){
		setLblError(null);
		
		setLoggedData();
		fillEstimateList();
		loaduserIds();
		return SUCCESS;
	}
	public String newDirect(){
		setLblError(null);
		System.out.println("HIIII");
		setLoggedData();
		loadEstimateNos();
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
		}if(getSessionKey("userRole").equalsIgnoreCase("CE")){
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
	        	listuserName.add(esUser);		       	        	        	
	          } 
	}
	
	private void fillEstimateList()
	{
		setLoggedData();
		HttpServletRequest request = ServletActionContext.getRequest();
		String region= (String) request.getSession().getAttribute("region");
		String costCenterNo=(String) request.getSession().getAttribute("costCenterNo");
		String userId= (String) request.getSession().getAttribute("userName");
		EstimateEjb estimateEjb = new EstimateEjb(region); 
		List<Long> status = new ArrayList<Long>();
		status.add(StandardEstimateStatus.NEW_APPLICATION.getKey());
		status.add(StandardEstimateStatus.APPLICATION_MODIFIED.getKey()); 
		status.add(StandardEstimateStatus.REJECTED_BY.getKey());
		try {
			if(getSessionKey("userRole").equalsIgnoreCase("ES")){
				status.add(StandardEstimateStatus.TO_BE_CHECKED_BY_ELECTRICAL_SUPIRINTENDENT.getKey());
			}
			if(getSessionKey("userRole").equalsIgnoreCase("EE") || getSessionKey("userRole").equalsIgnoreCase("PE")){
				status.add(StandardEstimateStatus.TO_BE_VALIDATED_BY_ELECTRICAL_ENGINEER.getKey()); 
				
				status.add(StandardEstimateStatus.TO_BE_VALIDATED_BY_PLANNING_ENGINEER.getKey());
				this.EstimateList = estimateEjb.loadStandEstmateDetailsType(costCenterNo, status,null,getSessionKey("smcType"), region);
			}
			
			/**if(getSessionKey("userRole").equalsIgnoreCase("PE")){
				//status.add(StandardEstimateStatus.TO_BE_VALIDATED_BY_ELECTRICAL_ENGINEER.getKey()); 
				
				status.add(StandardEstimateStatus.TO_BE_VALIDATED_BY_PLANNING_ENGINEER.getKey());
				this.EstimateList = estimateEjb.loadStandEstmateDetailsType(costCenterNo, status,null,getSessionKey("smcType"), region);
			}
			
			if(getSessionKey("userRole").equalsIgnoreCase("EE")){
				status.add(StandardEstimateStatus.TO_BE_VALIDATED_BY_ELECTRICAL_ENGINEER.getKey()); 
				
				//status.add(StandardEstimateStatus.TO_BE_VALIDATED_BY_PLANNING_ENGINEER.getKey());
				this.EstimateList = estimateEjb.loadStandEstmateDetailsType(costCenterNo, status,null,getSessionKey("smcType"), region);
			}*/
			
			if(getSessionKey("userRole").equalsIgnoreCase("CE") ){
				status.add(StandardEstimateStatus.TO_BE_VALIDATED_BY_ELECTRICAL_ENGINEER.getKey()); 
				status.add(StandardEstimateStatus.TO_BE_CHECKED_BY_ELECTRICAL_SUPIRINTENDENT.getKey());
				status.add(StandardEstimateStatus.TO_BE_VALIDATED_BY_PLANNING_ENGINEER.getKey());
				this.EstimateList = estimateEjb.loadStandEstmateDetailsType(costCenterNo, status,null,getSessionKey("smcType"), region);
			}
			
			
			else{
				this.EstimateList = estimateEjb.loadStandEstmateDetailsType(costCenterNo, status,null,getSessionKey("smcType"), region);
			}
			//request.getSession().setAttribute("estimateList", this.EstimateList);
			//RequestDispatcher dispatcher = request.getRequestDispatcher("/sendForValidation.jsp");
			//dispatcher.forward( request, ServletActionContext.getResponse()); 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*public String post() throws Exception
	{
		try{
	
		setLoggedData();
		HttpServletRequest request = ServletActionContext.getRequest();
		String region= (String) request.getSession().getAttribute("region");
		String costCenterNo=(String) request.getSession().getAttribute("costCenterNo");
		EstimateEjb estimateEjb = new EstimateEjb(region);
		ApprovalEjb appejb = new ApprovalEjb(region);
		EstimateList=new ArrayList<Spstdesthmt>();
		//*SpstdesthmtEjb ejb =  new SpstdesthmtEjb(getSessionKey("region"));
		SpstdesthmtEjb spstdesthmtEjb =  new SpstdesthmtEjb(getSessionKey("region"));
		//String[] estimateNo=getDetailList();
		// estimateNo= getHiddenEstimateNo().split(",");
		//System.out.println("AAAA"+getDetails());
		System.out.println("yyyy"+getHiddenEstimateNo().split(","));
		//System.out.println("BBB"+estimateNo);
		String usernamePostedTo = getPostUserName();
		System.out.println("CCC"+usernamePostedTo);
		//String totalCost = getTotalCost();
		//BigDecimal estimatedTotalCost = new BigDecimal(totalCost);
		Approval approval=new Approval();
		//boolean status = false;
		
		if(  usernamePostedTo != null && estimateNo != null){
			System.out.println("tttttttttttt");
			System.out.println("EstimateList");
			
			//for( int i=0;i<estimateNo.length;i++){
									
				
				System.out.println("QQQQQ"+getApplicationId());
				System.out.println("PPPP"+applicationId);
				
					SecurityEjb secejb=new SecurityEjb(getSessionKey("region"));
					String userLevelPostedTo = secejb.getAuthorizedLevel(usernamePostedTo);
					String postinguserLevel = secejb.getAuthorizedLevel(userId);
					//ApprovalEjb appejb = new ApprovalEjb(region);
					for( int i=0;i<estimateNo.length;){
					
						// estimateNo= getHiddenEstimateNo().split(",");
						 System.out.println("BBB"+estimateNo[i]);
					Calendar calendar = Calendar.getInstance();
					Format format=new Format();
					SpstdesthmtPK pk = new SpstdesthmtPK();
					pk.setApplicationNo(estimateNo[i]);
					System.out.println("Application Number"+estimateNo[i]);
					
					pk.setStdNo(estimateNo[i]);
					System.out.println("Estimate Number"+estimateNo[i]);
					pk.setDeptId(costCenterNo);
					//SpstdesthmtEjb ejb =  new SpstdesthmtEjb(getSessionKey("region"));
					//*Spstdesthmt hmt = ejb.findById(pk,getSessionKey("region"));
					Spstdesthmt spstdesthmt=new Spstdesthmt();
					spstdesthmt=spstdesthmtEjb.findById(pk,getSessionKey("region"));
					System.out.println("KKKKKKK"+spstdesthmt);
					
				if(spstdesthmt != null){
						
						approval.setApprovalId(null);
						approval.setReferenceNo(estimateNo[i].trim());
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
					
						i++;
						}
					
				
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
			
		
		fillEstimateList();
		loaduserIds();
		return SUCCESS;
	}
		*/
	public String post()
	{
		setLoggedData();
		HttpServletRequest request = ServletActionContext.getRequest();
		String region= (String) request.getSession().getAttribute("region");
		String costCenterNo=(String) request.getSession().getAttribute("costCenterNo");
		String logingUserLevel=(String) request.getSession().getAttribute("userRole");
		EstimateEjb estimateEjb = new EstimateEjb(region);
		System.out.println("AAAAAAAAAAAAAAAAAA" + logingUserLevel);
		//String[] estimateNo= getHiddenEstimateNo().split(",");
		String usernamePostedTo = getPostUserName();
		//String totalCost = getTotalCost();
		//BigDecimal estimatedTotalCost = new BigDecimal(totalCost);
		
		boolean status = false;
		//if(estimateNo[i] != null && !estimateNo[i].equalsIgnoreCase(Constants.DEFAULT_STRING) && usernamePostedTo != null){
			if(DetailsList!=null){
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
						if(logingUserLevel != null){
							if(logingUserLevel.trim().equalsIgnoreCase("ES")){
								hmt.setCom_es(getRecomReason());
							}else if(logingUserLevel.trim().equalsIgnoreCase("EE")){
								hmt.setCom_ee(getRecomReason());
							}
						}
						
						
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
				setLoggedData();
				fillEstimateList();
				loaduserIds();
			}catch(Exception e){
				return ERROR;
			}
		//i++;
		}else{
			setMessageType(MSG_INFO);
			setErrorMessage("Select required Details");
		}
		
		
				
		
		
		//if(status){
			
		//}
		fillEstimateList();
		loaduserIds();
		return SUCCESS;
	}
	public String postCE()
	{
		setLoggedData();
		HttpServletRequest request = ServletActionContext.getRequest();
		String region= (String) request.getSession().getAttribute("region");
		String costCenterNo=(String) request.getSession().getAttribute("costCenterNo");
		EstimateEjb estimateEjb = new EstimateEjb(region);
		
		String[] estimateNo= getHiddenEstimateNo().split(",");
		boolean status = false;
		Approval approval=new Approval();
		try{
			ApprovalEjb appejb = new ApprovalEjb(region);

		
			if(estimateNo != null){
				Calendar calendar = Calendar.getInstance();
				Format format=new Format();
				
				approval.setApprovalId(null);
				approval.setReferenceNo(estimateNo[0].trim());
				approval.setDeptId(costCenterNo);
				
				approval.setApprovedLevel(getSessionKey("userRole"));
				approval.setApprovedBy(userId);
				approval.setApprovedDate(calendar.getTime());
				approval.setApprovedTime(format.FormatTime());
				//approval.setStandardCost(new BigDecimal(totalCost));
				
				status= estimateEjb.updateEstimateStatus(estimateNo[0], costCenterNo, StandardEstimateStatus.TO_BE_VALIDATED_BY_CE.getKey(),null,null,null,null,null,null,null,null,null,null,null,null,null, region);
				approval.setFromStatus(StandardEstimateStatus.APPLICATION_MODIFIED.getKey().toString());
				approval.setToStatus(StandardEstimateStatus.TO_BE_VALIDATED_BY_CE.getKey().toString());
				approval.setApprovalType(getSessionKey("userRole")+"_VLDT");
			
			}
			appejb.createAutoIdSEstmateApprovals(approval);
			
		}catch(Exception e){
			return ERROR;
		}
		if(status){
			setMessageType(MSG_DONE);
			setErrorMessage("DONE:- Successfully Posted for CE");
		}
		fillEstimateList();
		return SUCCESS;
	}
	public String postPE()
	{
		setLoggedData();
		HttpServletRequest request = ServletActionContext.getRequest();
		String region= (String) request.getSession().getAttribute("region");
		String costCenterNo=(String) request.getSession().getAttribute("costCenterNo");
		EstimateEjb estimateEjb = new EstimateEjb(region);
		
		String[] estimateNo= getHiddenEstimateNo().split(",");
		boolean status = false;
		Approval approval=new Approval();
		try{
			ApprovalEjb appejb = new ApprovalEjb(region);

		
			if(estimateNo[0] != null){
			
				Calendar calendar = Calendar.getInstance();
				Format format=new Format();
				
				approval.setApprovalId(null);
				approval.setReferenceNo(estimateNo[0].trim());
				approval.setDeptId(costCenterNo);
				
				approval.setApprovedLevel(getSessionKey("userRole"));
				approval.setApprovedBy(userId);
				approval.setApprovedDate(calendar.getTime());
				approval.setApprovedTime(format.FormatTime());
				//approval.setStandardCost(new BigDecimal(totalCost));
				
				status= estimateEjb.updateEstimateStatus(estimateNo[0], costCenterNo, StandardEstimateStatus.TO_BE_VALIDATED_BY_PLANNING_ENGINEER.getKey(),null,null,null,null,null,null,null,null,null,null,null,null,null, region);
			
				approval.setFromStatus(StandardEstimateStatus.APPLICATION_MODIFIED.getKey().toString());
				approval.setToStatus(StandardEstimateStatus.TO_BE_VALIDATED_BY_PLANNING_ENGINEER.getKey().toString());
				approval.setApprovalType(getSessionKey("userRole")+"_VLDT");
			}
		
			appejb.createAutoIdSEstmateApprovals(approval);
			
		}catch(Exception e){
			return ERROR;
		}
		if(status){
			setMessageType(MSG_DONE);
			setErrorMessage("DONE:- Successfully Posted for PE");
		}
		fillEstimateList();
		return SUCCESS;
	}
	public String postDGM()
	{
		setLoggedData();
		HttpServletRequest request = ServletActionContext.getRequest();
		String region= (String) request.getSession().getAttribute("region");
		String costCenterNo=(String) request.getSession().getAttribute("costCenterNo");
		EstimateEjb estimateEjb = new EstimateEjb(region);
		String estimateNumber = null;
		String[] estimateNo = null;
		if(getHiddenEstimateNo() != null){
			estimateNo= getHiddenEstimateNo().split(",");
		}
		if(estimateNo != null && estimateNo[0].trim() != null){
			estimateNumber = estimateNo[0].trim();
		}else{
			estimateNumber = getApplicationNo().trim();
		}
		
		boolean status = false;
		Approval approval=new Approval();
		try{
			ApprovalEjb appejb = new ApprovalEjb(region);

		
			if(estimateNumber != null){
				Calendar calendar = Calendar.getInstance();
				Format format=new Format();
				
				approval.setApprovalId(null);
				approval.setReferenceNo(estimateNumber);
				approval.setDeptId(costCenterNo);
				
				approval.setApprovedLevel(getSessionKey("userRole"));
				approval.setApprovedBy(userId);
				approval.setApprovedDate(calendar.getTime());
				approval.setApprovedTime(format.FormatTime());
				//approval.setStandardCost(new BigDecimal(totalCost));
				
				status= estimateEjb.updateEstimateStatus(estimateNumber, costCenterNo, StandardEstimateStatus.TO_BE_APPROVED_BY_DGM.getKey(),null,null,null,null,null,null,null,null,null,null,null,null,null, region);
	
				approval.setFromStatus(StandardEstimateStatus.APPLICATION_MODIFIED.getKey().toString());
				approval.setToStatus(StandardEstimateStatus.TO_BE_APPROVED_BY_DGM.getKey().toString());
				approval.setApprovalType(getSessionKey("userRole")+"_VLDT");
			}
			appejb.createAutoIdSEstmateApprovals(approval);
			
		}catch(Exception e){
			return ERROR;
		}
		
		if(status){
			setMessageType(MSG_DONE);
			setErrorMessage("DONE:- Successfully Posted for DGM");
		}
		fillEstimateList();
		return SUCCESS;
	}
	public String recommendEstimate()
	{
		setLoggedData();
		HttpServletRequest request = ServletActionContext.getRequest();
		String smcType= (String) request.getSession().getAttribute("smcType");
		EstimateEjb estimateEjb = new EstimateEjb(region);
		String userId= (String) request.getSession().getAttribute("userName");
		boolean status = false;
		String usernamePostedTo = getPostUserName();
		String comment = "";
		
		if(getRecomReason() == null){
			comment ="";
		}else{
			comment = getRecomReason();
		}
		try{
				
		String estimateNo= getApplicationNo();
		String sin ="";
		if(getSinNo() !=null){
			sin = getSinNo();
		}
		System.out.println("sin : "+getSinNo());
		if(estimateNo != null){
			
			if(getSessionKey("userRole").equalsIgnoreCase("PE")){
				ApplicationEjb applicationEjb=new ApplicationEjb(getSessionKey("region"));
				Application obj = applicationEjb.findByApplicationNo(estimateNo, costCenterNo);
				
				applicationType = obj.getApplicationType();
			}
			String msg = estimateEjb.recommendStandardEstimate(estimateNo,costCenterNo,loggedInUserLevel,userId,comment,usernamePostedTo,applicationType,sin);
			
			setMessageType(MSG_DONE);
			setErrorMessage(msg);
		}
		
		}catch(Exception e){
			setMessageType(MSG_ERROR);
			setErrorMessage("Error Ocurred");
			return ERROR;
		}
		
		
		loadEstimateNos();
		return SUCCESS;
	}
	public String close() {
		
		return "close";
	}
	
	private void loadEstimateNos(){
		setLoggedData();
		List<String> estimationRefNos = null;
		HttpServletRequest request = ServletActionContext.getRequest();
		
		EstimateEjb estimateEjb = new EstimateEjb(region);
		SpstdesthmtEjb  spstdEjb = new SpstdesthmtEjb(region);
		List<Long> status = new ArrayList<Long>();
		if(getSessionKey("userRole").equalsIgnoreCase("ES")){
			status.add(StandardEstimateStatus.TO_BE_CHECKED_BY_ELECTRICAL_SUPIRINTENDENT.getKey());
			//status.add(StandardEstimateStatus.TO_BE_CHECKED_BY_ELECTRICAL_SUPIRINTENDENT2.getKey());
			//estimationRefNos = estimateEjb.loadStandEstmatenos(costCenterNo,status,null,region);
			estimationRefNos = spstdEjb.loadStandEstmateDetailsType(costCenterNo,status,null,getSessionKey("smcType"),region);
			
		}else if(getSessionKey("userRole").equalsIgnoreCase("ES")){
			status.add(StandardEstimateStatus.TO_BE_VALIDATED_BY_ELECTRICAL_ENGINEER.getKey());
			//estimationRefNos = estimateEjb.loadStandEstmatenos(costCenterNo,status,null,region); 
			estimationRefNos = spstdEjb.loadStandEstmateDetailsType(costCenterNo,status,null,getSessionKey("smcType"),region);
			
			
		}else if(getSessionKey("userRole").equalsIgnoreCase("EE")){
			status.add(StandardEstimateStatus.TO_BE_VALIDATED_BY_ELECTRICAL_ENGINEER.getKey());
			//estimationRefNos = estimateEjb.loadStandEstmatenos(costCenterNo,status,null,region); 
			estimationRefNos = spstdEjb.loadStandEstmateDetailsType(costCenterNo,status,null,getSessionKey("smcType"),region);
			
			
		}else if(getSessionKey("userRole").equalsIgnoreCase("CE")){
			status.add(StandardEstimateStatus.TO_BE_VALIDATED_BY_CE.getKey());
			//estimationRefNos = estimateEjb.loadStandEstmatenos(costCenterNo,status,null,region);
			estimationRefNos = spstdEjb.loadStandEstmateDetailsType(costCenterNo,status,null,getSessionKey("smcType"),region);
			
		}else if(getSessionKey("userRole").equalsIgnoreCase("PE")){
			status.add(StandardEstimateStatus.TO_BE_VALIDATED_BY_PLANNING_ENGINEER.getKey());
			//estimationRefNos = estimateEjb.loadStandEstmatenos(costCenterNo,status,null,region); 
			estimationRefNos = spstdEjb.loadStandEstmateDetailsType(costCenterNo,status,null,getSessionKey("smcType"),region);
			
		}else if(getSessionKey("userRole").equalsIgnoreCase("PCE")){
			status.add(StandardEstimateStatus.TO_BE_VALIDATED_BY_PLANNING_CE.getKey());
			//estimationRefNos = estimateEjb.loadStandEstmatenos(costCenterNo,status,null,region); 
			estimationRefNos = spstdEjb.loadStandEstmateDetailsType(costCenterNo,status,null,getSessionKey("smcType"),region);
			
		}else if(getSessionKey("userRole").equalsIgnoreCase("DGM")){
			status.add(StandardEstimateStatus.TO_BE_APPROVED_BY_DGM.getKey());
			//estimationRefNos = estimateEjb.loadStandEstmatenos(costCenterNo,status,null,region); 
			estimationRefNos = spstdEjb.loadStandEstmateDetailsType(costCenterNo,status,null,getSessionKey("smcType"),region);
			
		}
		
		
		request.getSession().setAttribute("estimationRefNos",estimationRefNos);	
	}
	
	public String approve()
	{
		setLoggedData();
		HttpServletRequest request = ServletActionContext.getRequest();
		String smcType= (String) request.getSession().getAttribute("smcType");
		EstimateEjb estimateEjb = new EstimateEjb(region);
		
		String userId= (String) request.getSession().getAttribute("userName");
		boolean status = false;
		String usernamePostedTo = getPostUserName();
		try{
				
		String estimateNo= getApplicationNo();
		if(estimateNo != null){
			String msg = estimateEjb.approveStandardEstimate(estimateNo,costCenterNo,loggedInUserLevel,userId,usernamePostedTo,applicationType);
			
			setMessageType(MSG_DONE);
			setErrorMessage(msg);
		}
		
		}catch(Exception e){
			setMessageType(MSG_ERROR);
			setErrorMessage("Error Ocurred");
			return ERROR;
		}
		
		
		loadEstimateNos();
		return SUCCESS;
	}
	public String reject()
	{
		setLoggedData();
		HttpServletRequest request = ServletActionContext.getRequest();
		

		SpstdesthmtEjb spstdesthmtEjb = new SpstdesthmtEjb(region);
		ApproveDetails detail = new ApproveDetails();
		
		
		String totalC= getTotalCost();
		String totalCost = null;
		if(totalC != null && !totalC.equalsIgnoreCase("")){
			totalCost = totalC;
		}
		nf.setGroupingUsed(true);
		nf.setMaximumFractionDigits(2);
		nf.setMinimumFractionDigits(2);
		
		
		String estimateNo= getApplicationNo();
		
		SpstdesthmtPK pk = new SpstdesthmtPK();
		pk.setApplicationNo(estimateNo);
		pk.setDeptId(costCenterNo);
		pk.setStdNo(estimateNo);
		Spstdesthmt spstdesthmt = spstdesthmtEjb.findById(pk, region);
		
		
		boolean status = false;
		Calendar calendar = Calendar.getInstance();
		Format format=new Format();
		Approval approval=new Approval();
		approval.setApprovalId(null);
		approval.setReferenceNo(estimateNo);
		approval.setDeptId(costCenterNo);
		approval.setApprovalType("SES_RJCT");
		approval.setApprovedLevel(getSessionKey("userRole"));
		approval.setApprovedBy(getSessionKey("userName"));
		approval.setApprovedDate(calendar.getTime());
		approval.setApprovedTime(format.FormatTime());
		approval.setStandardCost(new BigDecimal(totalCost));
		approval.setFromStatus(spstdesthmt.getStatus().toString());
		approval.setToStatus(StandardEstimateStatus.REJECTED_BY.getKey().toString());
		approval.setReason(getRejectedReason());
		try{
			EstimateEjb estimateEjb = new EstimateEjb(region);
			if(estimateNo != null ){
				String msg =estimateEjb.rejectStandardEstimate(estimateNo.trim(),approval);
				setMessageType(MSG_DONE);
				setErrorMessage(msg);
			}
			
		}catch(Exception e){
			setMessageType(MSG_ERROR);
			
			return ERROR;
		}
		
		loadEstimateNos();
		return SUCCESS;
	}
	
	public String getApplicationNo() {
		return applicationNo;
	}
	public void setApplicationNo(String applicationNo) {
		this.applicationNo = applicationNo;
	}
	@Override
	public void setParameters(Map<String, String[]> parameters) {
		// TODO Auto-generated method stub
		this.parameters=parameters;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		// TODO Auto-generated method stub
		this.session=session;
	}
	public String getSessionKey(String key) {
		return getSession().get(key).toString();
	}

	public String getLoggedInUserId() {
		return loggedInUserId;
	}

	
	public String getPostUserName() {
		return postUserName;
	}
	public void setPostUserName(String postUserName) {
		this.postUserName = postUserName;
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

	public List<String> getListuserName() {
		return listuserName;
	}
	public void setListuserName(List<String> listuserName) {
		this.listuserName = listuserName;
	}
	public String getApplicationType() {
		return applicationType;
	}
	public void setApplicationType(String applicationType) {
		this.applicationType = applicationType;
	}
	public String getLblError() {
		return lblError;
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

	 

	public Map<String, Object> getSession() {
		return session;
	}

	public Map<String, String[]> getParameters() {
		return parameters;
	}

	public List<Spstdesthmt> getEstimateList() {
		return EstimateList;
	}

	public void setEstimateList(List<Spstdesthmt> estimateList) {
		EstimateList = estimateList;
	}

	

	public String getHiddenEstimateNo() {
		return hiddenEstimateNo;
	}

	public void setHiddenEstimateNo(String hiddenEstimateNo) {
		this.hiddenEstimateNo = hiddenEstimateNo;
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
	public String getRejectedReason() {
		return rejectedReason;
	}
	public void setRejectedReason(String rejectedReason) {
		this.rejectedReason = rejectedReason;
	}
	public String getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(String totalCost) {
		this.totalCost = totalCost;
	}
	public String getLoggedInUserLevel() {
		return loggedInUserLevel;
	}
	public void setLoggedInUserLevel(String loggedInUserLevel) {
		this.loggedInUserLevel = loggedInUserLevel;
	}

	/*private ApproveDetails populateApproveDetails(HttpServletRequest request){
		String userId= (String) request.getSession().getAttribute("userName");
		ApproveDetails detail = new ApproveDetails();
		detail.setApprovedBy(userId);
		detail.setApprovedDate(new Date());
		detail.setApprovedBy(userId);.set.setApprovedDate(new Date());
		detail.setApprovedBy(userId);
		detail.setApprovedDate(new Date());
		detail.setApprovedBy(userId);
		detail.setApprovedDate(new Date());
		detail.setApprovedBy(userId);
		detail.setApprovedDate(new Date());
		detail.setApprovedBy(userId);
		detail.setApprovedDate(new Date());
		detail.setApprovedBy(userId);
		detail.setApprovedDate(new Date());
		detail.setApprovedBy(userId);
		detail.setApprovedDate(new Date());
		
		return detail;
		
	}*/
	
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
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getCostCenterNo() {
		return costCenterNo;
	}
	public void setCostCenterNo(String costCenterNo) {
		this.costCenterNo = costCenterNo;
	}
	
	public String viewEstimate(){
		String[] estimateNo= getHiddenEstimateNo().split(",");
		setApplicationNo(getHiddenEstimateNo());
		String yyy = getApplicationNo();
		String xxx = "";
		if(DetailsList!=null){
			System.out.println("Hii DDDDDD");
			for(int i=0;i<DetailsList.length;i++){
				xxx =DetailsList[i];
				if(xxx != null){
					xxx = xxx.trim();
				}
			}
			
		}
		System.out.println("Hii xxx" +yyy + estimateNo[0]);
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
		System.out.println("Hii DDDDDD" +getApplicationNo());
		
		HashMap<String, Object> param = new HashMap<String, Object>();
	 
		param.put("@appNo", "'"+xxx+"'");
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
	public String Print(){
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
		System.out.println("inside 5........");

		SecurityEjb ejb=new SecurityEjb(region);
		System.out.println("inside 6........");
		String authCostCenters = "";
		List<String> authCost = ejb.getAuthorizedCostCenters(userId);
		System.out.println("inside 7........");
		String authCostList[] = authCost.toArray(new String[0]);
		System.out.println("inside 8........");
		/**if (authCostList != null)
		{
			for (String key : authCostList) {

				key = "'" +  key.trim() + "'";			
				if (key != "''")
				{
					authCostCenters = authCostCenters + "," + key;
				}
			}
			authCostCenters = authCostCenters.substring(1,authCostCenters.length());	
		}*/





		HashMap<String, Object> param = new HashMap<String, Object>();
	 

		System.out.println("Application Number :  " + getApplicationNo());
		System.out.println("CC Number :  " + costCenterNo);
		param.put("@appNo", "'"+getApplicationNo()+"'");
		param.put("@costctr","'"+costCenterNo+"'");
		 
		String fileName = null;
		if(smcType.equalsIgnoreCase(Constants.RUTAL_ELECTRIFICATION_I)){
			fileName = rept.generateReport("job_QuotationRE",param,getSession(),REPORT_DIRECTORY,EXPORT_REPORT_DIRECTORY);
		}else if(smcType.equalsIgnoreCase(Constants.PLANNING)){
			fileName = rept.generateReport("STD_Estimate_Planing",param,getSession(),REPORT_DIRECTORY,EXPORT_REPORT_DIRECTORY);
		}else if(smcType.equalsIgnoreCase(Constants.RUTAL_ELECTRIFICATION)){
			fileName = rept.generateReport("STD_Estimate_PCB",param,getSession(),REPORT_DIRECTORY,EXPORT_REPORT_DIRECTORY);
		}
		else{
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
	public String PrintAward(){
		HttpServletRequest request = ServletActionContext.getRequest();
		setLoggedData();
		
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
		System.out.println("inside 5........");

		SecurityEjb ejb=new SecurityEjb(region);
		System.out.println("inside 6........");
		String authCostCenters = "";
		List<String> authCost = ejb.getAuthorizedCostCenters(userId);
		System.out.println("inside 7........");
		String authCostList[] = authCost.toArray(new String[0]);
		System.out.println("inside 8........");
		/**if (authCostList != null)
		{
			for (String key : authCostList) {

				key = "'" +  key.trim() + "'";			
				if (key != "''")
				{
					authCostCenters = authCostCenters + "," + key;
				}
			}
			authCostCenters = authCostCenters.substring(1,authCostCenters.length());	
		}*/





		HashMap<String, Object> param = new HashMap<String, Object>();
	 

		System.out.println("Application Number :  " + getApplicationNo());
		System.out.println("CC Number :  " + costCenterNo);
		param.put("@appNo", "'"+getApplicationNo()+"'");
		param.put("@costctr","'"+costCenterNo+"'");
		System.out.println("Hi print award");
		String fileName = null;
		if(getApplicationType().equalsIgnoreCase(Constants.BS) && (costCenterNo.equalsIgnoreCase("550.00")|| costCenterNo.equalsIgnoreCase("501.00")) ){
			fileName = rept.generateReport("AwardletterBULKR3",param,getSession(),REPORT_DIRECTORY,EXPORT_REPORT_DIRECTORY);
		}else if (getApplicationType().equalsIgnoreCase(Constants.LAND) && (costCenterNo.equalsIgnoreCase("550.00")|| costCenterNo.equalsIgnoreCase("501.00")) ){
			fileName = rept.generateReport("AwardletterLandR3",param,getSession(),REPORT_DIRECTORY,EXPORT_REPORT_DIRECTORY);
		}else if(getApplicationType().equalsIgnoreCase(Constants.BS)){
			fileName = rept.generateReport("AwardletterBULK",param,getSession(),REPORT_DIRECTORY,EXPORT_REPORT_DIRECTORY);
		}else if(getApplicationType().equalsIgnoreCase(Constants.LAND)){
			fileName = rept.generateReport("AwardletterLand",param,getSession(),REPORT_DIRECTORY,EXPORT_REPORT_DIRECTORY);
		}else if(getApplicationType().equalsIgnoreCase(Constants.RUTAL_ELECTRIFICATION) || getApplicationType().equalsIgnoreCase(Constants.RUTAL_ELECTRIFICATION_I)){
			fileName = rept.generateReport("AwardletterRE",param,getSession(),REPORT_DIRECTORY,EXPORT_REPORT_DIRECTORY);
		}else{
			fileName = rept.generateReport("Awardletter",param,getSession(),REPORT_DIRECTORY,EXPORT_REPORT_DIRECTORY);
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
	public String getEntryBy() {
		return entryBy;
	}
	public void setEntryBy(String entryBy) {
		this.entryBy = entryBy;
	}
	public String getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
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
			param.put("@fileRef","'"+""+"'");
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
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("loggedInUserLevel : " + loggedInUserLevel);
			setLoggedData();
			System.out.println("loggedInUserLevel after: " + loggedInUserLevel);
			setLblError("Sketch Is Not Attached.");
			return SUCCESS;
		}
		
		
		return "successprint";



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

public String PrintAwardNewWithHeader(){
	//setInfoFromMainMenu();
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
	//setInfoFromMainMenu();
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
    
	
}
