package estimate.web;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
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

import reports.web.report;
import security.service.SecurityEjb;

import util.common.ApplicationStatus;
import util.common.Constants;
import util.common.EstimateStatus;
import util.common.Path;
import util.common.StandardEstimateStatus;


import com.opensymphony.xwork2.ActionSupport;


import estimate.dto.ApproveDetails;
import estimate.model.Pcesthtt;
import estimate.model.Splaborm;
import estimate.model.Spstdesthmt;
import estimate.service.EstimateEjb;
import estimate.service.PcesthttEjb;
import estimate.service.SplabormEjb;
 

public class SendApprovalCancelationBS extends ActionSupport implements SessionAware, ParameterAware  {
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

	private String hiddenEstimateNo;
	private List<Spstdesthmt> EstimateList;
	private List<Pcesthtt> EstimateListConMain;
	private static final String MSG_ERROR="ERROR";
	private static final String MSG_INFO="INFO";
	private static final String MSG_DONE="DONE";
	private static final String MSG_NONE="NONE";
	private String errorMessage;
	private String messageType=MSG_NONE;
	private String applicationNo;
	private String rejectedReason;
	private String totalCost;
	private String hiddenEntryBy;
	//print variables
	private  String selectCategoryString = "----Select Category-----";
	private String selectedReport;
	private static final String viewPath="Reports>Select Report";
	
	private InputStream fileInputStream;

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
		fillStandardEstimateList();
		return SUCCESS;
	}
	public String initEstimates(){
		setLblError(null);
		
		setLoggedData();
		fillConMainEstimateList();
		return SUCCESS;
	}
	private void fillStandardEstimateList()
	{
		setLoggedData();
		HttpServletRequest request = ServletActionContext.getRequest();
		String region= (String) request.getSession().getAttribute("region");
		String costCenterNo=(String) request.getSession().getAttribute("costCenterNo");
		EstimateEjb estimateEjb = new EstimateEjb(region); 
		List<Long> status = new ArrayList<Long>();
		//status.add(StandardEstimateStatus.NEW_APPLICATION.getKey());
		//status.add(StandardEstimateStatus.APPLICATION_MODIFIED.getKey()); 
		if(getSessionKey("userRole").equalsIgnoreCase("EE")){
			status.add(StandardEstimateStatus.TO_BE_VALIDATED_BY_CE.getKey());
			//estimationRefNos = estimateEjb.loadStandEstmatenos(costCenterNo,status,region); 
			
		}else if(getSessionKey("userRole").equalsIgnoreCase("CE")){
			status.add(StandardEstimateStatus.TO_BE_APPROVED_BY_DGM.getKey());
			//estimationRefNos = estimateEjb.loadStandEstmatenos(costCenterNo,status,region); 
		}else if(getSessionKey("userRole").equalsIgnoreCase("PE")){
			status.add(StandardEstimateStatus.TO_BE_VALIDATED_BY_CE.getKey());
			//estimationRefNos = estimateEjb.loadStandEstmatenos(costCenterNo,status,region); 
		}else if(getSessionKey("userRole").equalsIgnoreCase("DGM")){
			status.add(StandardEstimateStatus.APPROVED_ESTIMATES.getKey());
			status.add(StandardEstimateStatus.PIVII_CONFIRMATION.getKey());
			status.add(StandardEstimateStatus.PIVII_TO_BE_CONFIRMED_BY_ACCOUNT_REV.getKey());
			status.add(StandardEstimateStatus.PIVII_TO_BE_CONFIRMED_BY_DEO.getKey());
			status.add(StandardEstimateStatus.SEND_FOR_CONSTRUCTION_MAINTENANCE.getKey());
			//estimationRefNos = estimateEjb.loadStandEstmatenos(costCenterNo,status,region); 
		}
		try {
			this.EstimateList = estimateEjb.loadStandEstmateDetails(costCenterNo, status,null, region);
			//request.getSession().setAttribute("estimateList", this.EstimateList);
			//RequestDispatcher dispatcher = request.getRequestDispatcher("/sendForValidation.jsp");
			//dispatcher.forward( request, ServletActionContext.getResponse()); 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void fillConMainEstimateList()
	{
		setLoggedData();
		HttpServletRequest request = ServletActionContext.getRequest();
		String region= (String) request.getSession().getAttribute("region");
		String costCenterNo=(String) request.getSession().getAttribute("costCenterNo");
		EstimateEjb estimateEjb = new EstimateEjb(region); 
		Long status = null;
		List<Long> statuses = new ArrayList<Long>();
		if(getSessionKey("userRole").equalsIgnoreCase("EE")){
			status = new Long(EstimateStatus.EST_APPROVAL_CE);
			statuses.add(new Long(EstimateStatus.EST_APPROVAL_CE));
		}else if(getSessionKey("userRole").equalsIgnoreCase("CE")){
			//status = new Long(EstimateStatus.EST_APPROVAL_DGM);
			//status = new Long(EstimateStatus.EST_POSTED);
			statuses.add(new Long(EstimateStatus.EST_APPROVAL_DGM));
			statuses.add(new Long(EstimateStatus.EST_POSTED));
		}else if(getSessionKey("userRole").equalsIgnoreCase("DGM")){
			//status = new Long(EstimateStatus.EST_POSTING);
			//status = new Long(EstimateStatus.EST_APPROVED);
			//statuses.add(new Long(EstimateStatus.EST_POSTING));
			statuses.add(new Long(EstimateStatus.EST_POSTED));
		}else{
			statuses.add(new Long(EstimateStatus.EST_APPROVAL_CE));
		}
		
		
		
		
		try {
			this.EstimateListConMain   = estimateEjb.getDetailEstimatesList(getCostCenterNo(), statuses,null,null,getSessionKey("region"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String approveCancellationCommercial()
	{
		setLoggedData();
		HttpServletRequest request = ServletActionContext.getRequest();
		String region= (String) request.getSession().getAttribute("region");
		String costCenterNo=(String) request.getSession().getAttribute("costCenterNo");
		EstimateEjb estimateEjb = new EstimateEjb(region);
		
		String[] estimateNo= getHiddenEstimateNo().split(",");
		String entryBy= getHiddenEntryBy();
		boolean status = false;
		if(estimateNo[0] != null){
			if(estimateNo != null && getSessionKey("userRole").equalsIgnoreCase("EE")){
				
				status= estimateEjb.updateEstimateStatus(estimateNo[0], costCenterNo, StandardEstimateStatus.APPLICATION_MODIFIED.getKey(),null,null,null,null,null,null,null,null,null,null,null,null,null, region);
			}else if(estimateNo != null && getSessionKey("userRole").equalsIgnoreCase("CE")){
				
				status= estimateEjb.updateEstimateStatus(estimateNo[0], costCenterNo,StandardEstimateStatus.APPLICATION_MODIFIED.getKey(),null,null,null,null,null,null,null,null,null,null,null,null,null, region);
			}else if(estimateNo != null && getSessionKey("userRole").equalsIgnoreCase("DGM")){
				
				status= estimateEjb.updateEstimateStatus(estimateNo[0], costCenterNo, StandardEstimateStatus.APPLICATION_MODIFIED.getKey(),null,null,null,null,null,null,null,null,null,null,null,null,null, region);
			}
		}
		
		
		if(status){
			setMessageType(MSG_DONE);
			setErrorMessage("DONE:- Successfully Posted for Entry User");
		}
		fillStandardEstimateList();
		return SUCCESS;
	}
	public String approveCancellationConMain()
	{
		setLoggedData();
		HttpServletRequest request = ServletActionContext.getRequest();
		String region= (String) request.getSession().getAttribute("region");
		String costCenterNo=(String) request.getSession().getAttribute("costCenterNo");
		PcesthttEjb pcesthttEjb = new PcesthttEjb(region);
		
		String[] estimateNo= getHiddenEstimateNo().split(",");
		String entryBy= getHiddenEntryBy();
		boolean status = false;
		if(estimateNo[0] != null){
			if(estimateNo != null && getSessionKey("userRole").equalsIgnoreCase("EE")){
				
				pcesthttEjb.changeStatusPcesthtt(estimateNo[0], costCenterNo, Long.parseLong(EstimateStatus.MODIFIED));
			}else if(estimateNo != null && getSessionKey("userRole").equalsIgnoreCase("CE")){
				
				pcesthttEjb.changeStatusPcesthtt(estimateNo[0], costCenterNo, Long.parseLong(EstimateStatus.MODIFIED));
			}else if(estimateNo != null && getSessionKey("userRole").equalsIgnoreCase("DGM")){
				
				pcesthttEjb.changeStatusPcesthtt(estimateNo[0], costCenterNo, Long.parseLong(EstimateStatus.MODIFIED));
			}
		}
		
		
		
		setMessageType(MSG_DONE);
		setErrorMessage("DONE:- Successfully Posted for Entry User");
		
		fillConMainEstimateList();
		return SUCCESS;
	}
	
	public String close() {
		
		return "close";
	}
	
	
	public String getHiddenEntryBy() {
		return hiddenEntryBy;
	}
	public void setHiddenEntryBy(String hiddenEntryBy) {
		this.hiddenEntryBy = hiddenEntryBy;
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

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
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
		 
		String fileName = null;
		if(getApplicationType().equalsIgnoreCase(Constants.BS) && (costCenterNo.equalsIgnoreCase("550.00")|| costCenterNo.equalsIgnoreCase("501.00")) ){
			fileName = rept.generateReport("AwardletterBULKR3",param,getSession(),REPORT_DIRECTORY,EXPORT_REPORT_DIRECTORY);
		}else if (getApplicationType().equalsIgnoreCase(Constants.LAND) && (costCenterNo.equalsIgnoreCase("550.00")|| costCenterNo.equalsIgnoreCase("501.00")) ){
			fileName = rept.generateReport("AwardletterLandR3",param,getSession(),REPORT_DIRECTORY,EXPORT_REPORT_DIRECTORY);
		}else if(getApplicationType().equalsIgnoreCase(Constants.BS)){
			fileName = rept.generateReport("AwardletterBULK",param,getSession(),REPORT_DIRECTORY,EXPORT_REPORT_DIRECTORY);
		}else if(getApplicationType().equalsIgnoreCase(Constants.LAND)){
			fileName = rept.generateReport("AwardletterLand",param,getSession(),REPORT_DIRECTORY,EXPORT_REPORT_DIRECTORY);
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
	public List<Pcesthtt> getEstimateListConMain() {
		return EstimateListConMain;
	}
	public void setEstimateListConMain(List<Pcesthtt> estimateListConMain) {
		EstimateListConMain = estimateListConMain;
	}
	
}
