package estimate.web;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.SessionAware;
//import org.apache.tomcat.jni.Status;

import presentation.ajax.WorkEstimateAjaxController;

import reports.web.report;
import security.service.SecurityEjb;

import util.common.AppStatus;
import util.common.ApplicationStatus;
import util.common.Constants;
import util.common.Path;
import util.common.StandardEstimateStatus;


import application.service.ApplicationEjb;
import application.service.ApplicationTransactionEjb;

import com.opensymphony.xwork2.ActionSupport;

import costcenter.model.Glcompm;
import costcenter.service.GldeptinEjb;
import costcenter.service.GldeptmEjb;


import estimate.dto.ApproveDetails;
import estimate.model.ApplicationDisplay;
import estimate.model.Splaborm;
import estimate.model.Spstdesthmt;
import estimate.model.SpstdesthmtPK;
import estimate.service.EstimateEjb;
import estimate.service.EstimateReferenceEjb;
import estimate.service.SplabormEjb;
import estimate.service.SpstdesthmtEjb;
 

public class SendForConstructionMaintenanceBS extends ActionSupport implements SessionAware, ParameterAware  {
	private Map<String, Object> session;
	private Map <String, String[]> parameters;
	private String loggedInUserId;
	
	private String path;
	private String lblError = null;
	private String lblSuccess = null;

	private String hiddenEstimateNo;
	private String  hiddendeptId;
	private List<Spstdesthmt> EstimateList;
	private String[] DetailsList;

	private static final String MSG_ERROR="ERROR";
	private static final String MSG_INFO="INFO";
	private static final String MSG_DONE="DONE";
	private static final String MSG_NONE="NONE";
	private String errorMessage;
	private String messageType=MSG_NONE;
	private String applicationNo;
	private String rejectedReason;
	private String totalCost;
	private List<String> postIdList =new ArrayList<String>();
	private String postId;
	private String costCenter;
	private String fileRef;
	//print variables
	private  String selectCategoryString = "----Select Category-----";
	private String selectedReport;
	private static final String viewPath="Reports>Select Report";
	
	private InputStream fileInputStream;
	private InputStream fileInputStream1;

	
	
	public String execute(){
		setLblError(null);
		setCostCenter(getSession().get("costCenterNo").toString());
		setLoggedInUserId(getSession().get("userName").toString());
		loadPostIds();
		fillPIV2ConfirmedEstimateList();
		return SUCCESS;
	}
	public String newDirect(){
		setLblError(null);
		loadPostIds();
		setCostCenter(getSession().get("costCenterNo").toString());
		setLoggedInUserId(getSession().get("userName").toString());
		fillPIV2ConfirmedEstimateList();
		loadEstimateNos();
		return SUCCESS;
	}
	
	
	public String newBatchVise(){
		setLblError(null);
		loadPostIds();
		setCostCenter(getSession().get("costCenterNo").toString());
		setLoggedInUserId(getSession().get("userName").toString());
		fillPIV2ConfirmedEstimateList();
		loadEstimateNos();
		return SUCCESS;
	}
	public String newDirectFromMainMenu(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String sessionKey= (String) request.getSession().getAttribute("region");
		String estimateNo=request.getParameter("estNo");
		String costCen=request.getParameter("costCen");
		//String costCenterNo=(String) request.getSession().getAttribute("costCenterNo");
		SpstdesthmtEjb spstdesthmtEjb = new SpstdesthmtEjb(sessionKey); 
		if(estimateNo!=null && estimateNo.length()>0 && costCen!=null && costCen.length()>0){
			setApplicationNo(estimateNo);
			setCostCenter(costCen);
			//viewApplicantDetails();
			setLblError(null);
			loadPostIds();
			setLoggedInUserId(getSession().get("userName").toString());
			
			SpstdesthmtPK spstdesthmtPK = new SpstdesthmtPK();
			spstdesthmtPK.setDeptId(costCen);
			spstdesthmtPK.setStdNo(estimateNo);
			spstdesthmtPK.setApplicationNo(estimateNo);
			Spstdesthmt hmt = spstdesthmtEjb.findById(spstdesthmtPK, sessionKey);
			this.EstimateList = new ArrayList<Spstdesthmt>();
			if(hmt != null){
				
				this.EstimateList.add(hmt);
			}else{
				populateEstimateDBoard(spstdesthmtPK,null);
				
			}
		}
		
		
		//loadEstimateNos();
		return SUCCESS;
	}
	
	private void populateEstimateDBoard(SpstdesthmtPK spstdesthmtPK ,List<ApplicationDisplay> tesmpList ){
		System.out.println("hiiii");
		Spstdesthmt hmt1 = new Spstdesthmt();
		if(spstdesthmtPK != null) {
			
			hmt1.setId(spstdesthmtPK);
			
			hmt1.setEntryBy("");
			hmt1.setTotalCost(new BigDecimal("0"));
			hmt1.setApprovedBy("");
			
			
		}else{
			if(tesmpList != null){
				for(ApplicationDisplay dis : tesmpList){
					SpstdesthmtPK spstdesthmtpk = new SpstdesthmtPK();
					spstdesthmtpk.setDeptId(dis.getCostCenter());
					spstdesthmtpk.setStdNo(dis.getEstimateNo());
					spstdesthmtpk.setApplicationNo(dis.getEstimateNo());
					hmt1.setId(spstdesthmtpk);
				}
			}
		}
		this.EstimateList.add(hmt1);
	}
	private void fillPIV2ConfirmedEstimateList()
	{
		System.out.println("hiiii");
		HttpServletRequest request = ServletActionContext.getRequest();
		String region= (String) request.getSession().getAttribute("region");
		String costCenterNo=(String) request.getSession().getAttribute("costCenterNo");
		EstimateEjb estimateEjb = new EstimateEjb(region); 
		List<Long> status = new ArrayList<Long>();
		status.add(StandardEstimateStatus.PIVII_CONFIRMATION.getKey());
		
		
		try {
			
			if(getSessionKey("smcType") != null && getSessionKey("smcType").equalsIgnoreCase(Constants.JOBTYPE_COMMERCIAL_CP)){
				ApplicationEjb appEjb=new ApplicationEjb(getSessionKey("region"));
				List<ApplicationDisplay> tesmpList = appEjb.getToBeAllocatedApplicationList(costCenterNo, "C",Constants.JOBTYPE_COMMERCIAL_CP);
				populateEstimateDBoard(null,tesmpList);
			}else{
				//this.EstimateList = estimateEjb.loadStandEstmateDetails(costCenterNo, status,null, region);
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
	
	
	private void cancellationEstList()
	{
		System.out.println("hiiii");
		HttpServletRequest request = ServletActionContext.getRequest();
		String region= (String) request.getSession().getAttribute("region");
		String costCenterNo=(String) request.getSession().getAttribute("costCenterNo");
		EstimateEjb estimateEjb = new EstimateEjb(region); 
		List<Long> status = new ArrayList<Long>();
		status.add(StandardEstimateStatus.APPLICATION_MODIFIED.getKey());
		status.add(StandardEstimateStatus.TO_BE_CHECKED_BY_ELECTRICAL_SUPIRINTENDENT.getKey());
		status.add(StandardEstimateStatus.TO_BE_VALIDATED_BY_ELECTRICAL_ENGINEER.getKey());
		status.add(StandardEstimateStatus.TO_BE_VALIDATED_BY_PLANNING_CE.getKey());
		status.add(StandardEstimateStatus.TO_BE_VALIDATED_BY_PLANNING_ENGINEER.getKey());
		status.add(StandardEstimateStatus.TO_BE_VALIDATED_BY_CE.getKey());
		status.add(StandardEstimateStatus.TO_BE_APPROVED_BY_DGM.getKey());
		status.add(StandardEstimateStatus.VALIDATED_BY_DGM.getKey());
		status.add(StandardEstimateStatus.VALIDATED_BY_CE.getKey());
		status.add(StandardEstimateStatus.VALIDATED_BY_CE.getKey());
		status.add(StandardEstimateStatus.PIVII_CONFIRMATION.getKey());
		status.add(StandardEstimateStatus.APPROVED_ESTIMATES.getKey());
		status.add(StandardEstimateStatus.PIVII_TO_BE_CONFIRMED_BY_DEO.getKey());
		status.add(StandardEstimateStatus.PIVII_TO_BE_CONFIRMED_BY_ACCOUNT_REV.getKey());
		status.add(StandardEstimateStatus.PIVII_FOR_SECDEPOSIT.getKey());
		
		status.add(StandardEstimateStatus.SEND_FOR_CONSTRUCTION_MAINTENANCE.getKey());
		status.add(StandardEstimateStatus.JOB_ALLOCATED.getKey());
		
		try {
			
			if(getSessionKey("smcType") != null && getSessionKey("smcType").equalsIgnoreCase(Constants.JOBTYPE_COMMERCIAL_CP)){
				ApplicationEjb appEjb=new ApplicationEjb(getSessionKey("region"));
				List<ApplicationDisplay> tesmpList = appEjb.getToBeAllocatedApplicationList(costCenterNo, "C",Constants.JOBTYPE_COMMERCIAL_CP);
				populateEstimateDBoard(null,tesmpList);
			}else{
				//this.EstimateList = estimateEjb.loadStandEstmateDetails(costCenterNo, status,null, region);
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
	private void loadPostIds(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String region= (String) request.getSession().getAttribute("region");
		String costCenterNo=(String) request.getSession().getAttribute("costCenterNo");
		
		GldeptmEjb ejb = new GldeptmEjb(region);
		
		//String costcenNo = costCenterNo.substring(0,costCenterNo.indexOf("."));
		String costcenNo = costCenterNo.substring(0, 2);
		if(costcenNo != null){
			try {
				postIdList = new ArrayList<String>();
				postIdList.add(Constants.DEFAULT_STRING_SELECT);
				//if(ejb.getPostDepartmentIds(costcenNo, region) != null){
					//postIdList.addAll(ejb.getPostDepartmentIds(costcenNo, region));
				//}
				//System.out.println(""+ costCenterNo);
				//System.out.println(ejb.findAreaCodesForPost(costcenNo));
				//16072014gayani
				if(ejb.findAreaCodesForPost(costCenterNo) != null){
					postIdList.addAll(ejb.findAreaCodesForPost(costCenterNo));
			    }
				
				/*ApplicationTransactionEjb transactionEjb=new ApplicationTransactionEjb(getSessionKey("region"));
				List<String> areasList = transactionEjb.getAreasByDeptId(costCenterNo, getSessionKey("region"));
				if(areasList != null){
					postIdList.addAll(areasList);
				}*/
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**public String sendForCM()
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		String region= (String) request.getSession().getAttribute("region");
		String costCenterNo=(String) request.getSession().getAttribute("costCenterNo");
		SpstdesthmtEjb spstdesthmtEjb = new SpstdesthmtEjb(region);
		
		String[] estimateNo= getHiddenEstimateNo().split(",");
		//System.out.println("test : " + estimateNo.length);
		//System.out.println("test : " + estimateNo[0] + " " + estimateNo[1]);
		String postDeptId= null;
		if(!getPostId().equalsIgnoreCase(Constants.DEFAULT_STRING_SELECT)){
			postDeptId= getPostId();
		}
		
		int status = 0;
		
		//for(int i= 0 ; i < estimateNo.length ; i++){
			//System.out.println("test : " + estimateNo[i]);
			//System.out.println("test : " + i);
		//}
		if(estimateNo[0] != null && postDeptId != null){
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
		}
		
		
		if(status==1){
			setMessageType(MSG_DONE);
			setErrorMessage("DONE:- Successfully Posted for "+postDeptId);
		}
		loadPostIds();
		fillPIV2ConfirmedEstimateList();
		return SUCCESS;
	}*/
	
	
	public String sendForCMBatch()
	{   System.out.println("test :");
		HttpServletRequest request = ServletActionContext.getRequest();
		String region= (String) request.getSession().getAttribute("region");
		String costCenterNo=(String) request.getSession().getAttribute("costCenterNo");
		SpstdesthmtEjb spstdesthmtEjb = new SpstdesthmtEjb(region);
		System.out.println("test 1:");
		String postDeptId= null;
		if(!getPostId().equalsIgnoreCase(Constants.DEFAULT_STRING_SELECT)){
			postDeptId= getPostId();
		}
		System.out.println("test :2" + postDeptId);
		int status = 0;
		if(DetailsList!=null){
			for(int i=0;i<DetailsList.length;i++){
				if(DetailsList[i] != null && postDeptId != null){
					SpstdesthmtPK spstdesthmtPK = new SpstdesthmtPK();
					spstdesthmtPK.setDeptId(costCenterNo);
					spstdesthmtPK.setStdNo(DetailsList[i].trim());
					spstdesthmtPK.setApplicationNo(DetailsList[i].trim());
					Spstdesthmt hmt = spstdesthmtEjb.findById(spstdesthmtPK, region);
					System.out.println("test :3" + DetailsList[i].trim());
					if(hmt != null){
						
						status= spstdesthmtEjb.updateSendForCMStatus(DetailsList[i], costCenterNo, StandardEstimateStatus.SEND_FOR_CONSTRUCTION_MAINTENANCE.getKey(),postDeptId, region);
					}else{
						ApplicationEjb ejb = new ApplicationEjb(region);
					
						status = ejb.changeStatusApplication(DetailsList[i], costCenterNo,AppStatus.REQUEST_FOR_ESTIMATION, region);
					}
				
			}
			
		}
		}
		else{
			setMessageType(MSG_INFO);
			setErrorMessage("Select required Details");
		}
		
		
		if(status==1){
			setMessageType(MSG_DONE);
			setErrorMessage("DONE:- Successfully Posted for "+postDeptId);
		}
		loadPostIds();
		fillPIV2ConfirmedEstimateList();
		return SUCCESS;

	}
	public String sendForCM()
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		String region= (String) request.getSession().getAttribute("region");
		String costCenterNo=(String) request.getSession().getAttribute("costCenterNo");
		SpstdesthmtEjb spstdesthmtEjb = new SpstdesthmtEjb(region);
		
		String[] estimateNo= getHiddenEstimateNo().split(",");
		//System.out.println("test : " + estimateNo.length);
		//System.out.println("test : " + estimateNo[0] + " " + estimateNo[1]);
		String postDeptId= null;
		if(!getPostId().equalsIgnoreCase(Constants.DEFAULT_STRING_SELECT)){
			postDeptId= getPostId();
		}
		
		int status = 0;
		
		//for(int i= 0 ; i < estimateNo.length ; i++){
			//System.out.println("test : " + estimateNo[i]);
			//System.out.println("test : " + i);
		//}
		if(estimateNo[0] != null && postDeptId != null){
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
		}
		
		
		if(status==1){
			setMessageType(MSG_DONE);
			setErrorMessage("DONE:- Successfully Posted for "+postDeptId);
		}
		loadPostIds();
		fillPIV2ConfirmedEstimateList();
		return SUCCESS;

	}
	public String postCE()
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		String region= (String) request.getSession().getAttribute("region");
		String costCenterNo=(String) request.getSession().getAttribute("costCenterNo");
		EstimateEjb estimateEjb = new EstimateEjb(region);
		
		String[] estimateNo= getHiddenEstimateNo().split(",");
		boolean status = false;
		if(estimateNo != null){
			status= estimateEjb.updateEstimateStatus(estimateNo[0], costCenterNo, StandardEstimateStatus.TO_BE_VALIDATED_BY_CE.getKey(),null,null,null,null,null,null,null,null,null,null,null,null,null, region);
		}
		
		
		if(status){
			setMessageType(MSG_DONE);
			setErrorMessage("DONE:- Successfully Posted for CE");
		}
		fillPIV2ConfirmedEstimateList();
		return SUCCESS;
	}
	
	
	public String postPE()
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		String region= (String) request.getSession().getAttribute("region");
		String costCenterNo=(String) request.getSession().getAttribute("costCenterNo");
		EstimateEjb estimateEjb = new EstimateEjb(region);
		
		String[] estimateNo= getHiddenEstimateNo().split(",");
		boolean status = false;
		if(estimateNo[0] != null){
			status= estimateEjb.updateEstimateStatus(estimateNo[0], costCenterNo, StandardEstimateStatus.TO_BE_VALIDATED_BY_PLANNING_ENGINEER.getKey(),null,null,null,null,null,null,null,null,null,null,null,null,null, region);
		}
		
		
		if(status){
			setMessageType(MSG_DONE);
			setErrorMessage("DONE:- Successfully Posted for PE");
		}
		fillPIV2ConfirmedEstimateList();
		return SUCCESS;
	}
	
	
	public String close() {
		
		return "close";
	}
	
	private void loadEstimateNos(){
		List<String> estimationRefNos = null;
		HttpServletRequest request = ServletActionContext.getRequest();
		String region= (String) request.getSession().getAttribute("region");
		String costCenterNo=(String) request.getSession().getAttribute("costCenterNo");
		String userId= (String) request.getSession().getAttribute("userName");
		EstimateEjb estimateEjb = new EstimateEjb(region);
		List<Long> status = new ArrayList<Long>();
		if(getSessionKey("userRole").equalsIgnoreCase("EE")){
			status.add(StandardEstimateStatus.TO_BE_VALIDATED_BY_ELECTRICAL_ENGINEER.getKey());
			estimationRefNos = estimateEjb.loadStandEstmatenos(costCenterNo,status,region); 
			
		}else if(getSessionKey("userRole").equalsIgnoreCase("CE")){
			status.add(StandardEstimateStatus.TO_BE_VALIDATED_BY_CE.getKey());
			estimationRefNos = estimateEjb.loadStandEstmatenos(costCenterNo,status,region); 
		}else if(getSessionKey("userRole").equalsIgnoreCase("PE")){
			status.add(StandardEstimateStatus.TO_BE_VALIDATED_BY_PLANNING_ENGINEER.getKey());
			estimationRefNos = estimateEjb.loadStandEstmatenos(costCenterNo,status,region); 
		}else if(getSessionKey("userRole").equalsIgnoreCase("DGM")){
			status.add(StandardEstimateStatus.TO_BE_APPROVED_BY_DGM.getKey());
			estimationRefNos = estimateEjb.loadStandEstmatenos(costCenterNo,status,region); 
		}
		
		
		request.getSession().setAttribute("estimationRefNos",estimationRefNos);	
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

	public void setLoggedInUserId(String loggedInUserId) {
		this.loggedInUserId = loggedInUserId;
	}

	public String getFileRef() {
		return fileRef;
	}
	public void setFileRef(String fileRef) {
		this.fileRef = fileRef;
	}
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
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
	public String getHiddendeptId() {
		return hiddendeptId;
	}
	public void setHiddendeptId(String hiddendeptId) {
		this.hiddendeptId = hiddendeptId;
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
	public String getCostCenter() {
		return costCenter;
	}
	public void setCostCenter(String costCenter) {
		this.costCenter = costCenter;
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
	
	public String[] getDetailsList() {
		return DetailsList;
	}
	public void setDetailsList(String[] detailsList) {
		DetailsList = detailsList;
	}
public String ViewSketch(){
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
	//String appno=this.getApplicationNo();
    String[] estimateNo= getHiddenEstimateNo().split(",");
    String appno= estimateNo[0];
	String appNo=appno.replaceAll("/", ".");
		System.out.println("Hii DDDDDD" + appno);
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


		String[] estimateNo= getHiddenEstimateNo().split(",");

        String appNo = "";
		HashMap<String, Object> param = new HashMap<String, Object>();
	 
		/**if(DetailsList!=null){
			System.out.println("BBBBBBBBBBBBBBBBB");
			for(int i=0;i<DetailsList.length;i++){
				System.out.println("CCCCCCCCCCCCCCCCCC");
				if(DetailsList[i] != null){
					appNo = DetailsList[i].trim();
				}
			    System.out.println("selected numbers:"+appNo);
			}
		}*/
		System.out.println("Application Number :  " + estimateNo[0]);
		System.out.println("CC Number :  " + costCenterNo);
		param.put("@appNo", "'"+estimateNo[0]+"'");
		param.put("@costctr","'"+costCenterNo+"'");
		
		if(getFileRef() != null && costCenterNo.equalsIgnoreCase("430.00")){
			
			param.put("@fileRef","'"+getFileRef()+"'");
		}else{
			param.put("@fileRef","'"+""+"'");
		}
	
		
		String fileName = null;
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
				loadPostIds();
				return SUCCESS;
			}
			fileInputStream = new FileInputStream(fileName);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		loadPostIds();
		return "successprint";


	}
	
	public String cancellation(){
		setLblError(null);
		loadPostIds();
		setCostCenter(getSession().get("costCenterNo").toString());
		setLoggedInUserId(getSession().get("userName").toString());
		cancellationEstList();
		//loadEstimateNos();
		return SUCCESS;
	}
	
	public String generateJobSheet(){
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


		String[] estimateNo= getHiddenEstimateNo().split(",");


		HashMap<String, Object> param = new HashMap<String, Object>();
		
		/**String appNo ="";
		if(DetailsList!=null){
			System.out.println("BBBBBBBBBBBBBBBBB");
			for(int i=0;i<DetailsList.length;i++){
				System.out.println("CCCCCCCCCCCCCCCCCC");
				if(DetailsList[i] != null){
					appNo = DetailsList[i].trim();
				}
			    System.out.println("selected numbers:"+appNo);
			}
		}*/

		System.out.println("Application Number :  " + estimateNo[0]);
		System.out.println("CC Number :  " + costCenterNo);
		param.put("@appNo", "'"+ estimateNo[0]+"'");
		param.put("@costctr","'"+costCenterNo+"'");
		
		
		
		String fileName = null;
		if( (smcType.equalsIgnoreCase(Constants.PLANNING) || smcType.equalsIgnoreCase(Constants.PLANNING)) ){
			fileName = rept.generateReport("Job_Sheet",param,getSession(),REPORT_DIRECTORY,EXPORT_REPORT_DIRECTORY);
		}
		
		System.out.println("inside ViewGayani");
		System.out.println("xxxxxxxxx"+ fileName);
		
		try {
			if (fileName == "")
			{
				setLblError("Error occured while generating the report");
				loadPostIds();
				return SUCCESS;
			}
			fileInputStream = new FileInputStream(fileName);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		loadPostIds();
		return "successprint";


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
	
	
}
