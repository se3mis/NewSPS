package estimate.web;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
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
import util.common.Format;
import util.common.Path;
import util.common.StandardEstimateStatus;


import com.opensymphony.xwork2.ActionSupport;


import estimate.dto.ApproveDetails;
import estimate.model.Approval;
import estimate.model.EstimateReference;
import estimate.model.Pcesthtt;
import estimate.model.PcesthttPK;
import estimate.model.Splaborm;
import estimate.model.Spstdesthmt;
import estimate.model.SpstdesthmtPK;
import estimate.service.EstimateEjb;
import estimate.service.EstimateReferenceEjb;
import estimate.service.PcesthttEjb;
import estimate.service.SpestedyConEjb;
import estimate.service.SpestedyEjb;
import estimate.service.SplabormEjb;
import estimate.service.SpstdesthmtEjb;
 

public class SendWorkEsForValidationBS extends ActionSupport implements SessionAware, ParameterAware  {
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
	private List<Pcesthtt> EstimateList;
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
	private String applicationId;
	private String estimateNo;
	private String rejectReason;
	private String costCenterName;
	private String stdtotalCost;
	private String rebateCost;
	private String variance;
	private List<String> listuserName=new ArrayList<String>();
	private String postUserName;
	NumberFormat nf = NumberFormat.getInstance();
	private  String selectCategoryString = "----Select Category-----";
	private String selectedReport;
	private static final String viewPath="Reports>Select Report";
	
	private InputStream fileInputStream;
	private InputStream fileInputStream1;


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
	public String getCostCenterName() {
		return costCenterName;
	}
	public void setCostCenterName(String costCenterName) {
		this.costCenterName = costCenterName;
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
	public List<String> getListuserName() {
		return listuserName;
	}
	public void setListuserName(List<String> listuserName) {
		this.listuserName = listuserName;
	}
	
	public String getPostUserName() {
		return postUserName;
	}
	public void setPostUserName(String postUserName) {
		this.postUserName = postUserName;
	}
	public String getRejectReason() {
		return rejectReason;
	}
	public void setRejectReason(String rejectReason) {
		this.rejectReason = rejectReason;
	}
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
	/*public String newDirect(){
		setLblError(null);
		
		
		setLoggedData();
		loadEstimateNos();
		loadEstimationRefNumbers();//to load Application status to be changed
		//loadWorkEstimationRefNumbers();
	
		loadWareHouse();
		loadFundSources();
		return SUCCESS;
	}*/
	private void fillEstimateList()
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		setLoggedData();
		PcesthttEjb pcesthttEjb = new PcesthttEjb(region); 
		List<Long> status = new ArrayList<Long>();
		
		status.add(new Long(EstimateStatus.NEW)); 
		status.add(new Long(EstimateStatus.EST_REJECTED));
		if(getSession().get("userRole").toString().equalsIgnoreCase("ES")){
			status.add(new Long(EstimateStatus.EST_APPROVAL_ES));
		}
		try {
			this.EstimateList = pcesthttEjb.getEstimateList(costCenterNo, Constants.REV_NO, status,null);
			
			//request.getSession().setAttribute("estimateList", this.EstimateList);
			//RequestDispatcher dispatcher = request.getRequestDispatcher("/sendForValidation.jsp");
			//dispatcher.forward( request, ServletActionContext.getResponse()); 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
//	public String postES()
//	{
//		HttpServletRequest request = ServletActionContext.getRequest();
//		setLoggedData();
//		PcesthttEjb pcesthttEjb = new PcesthttEjb(region);
//		
//		String[] estimateNo= getHiddenEstimateNo().split(",");
//		boolean status = false;
//		if(estimateNo[0].trim() != null){
//		
//			pcesthttEjb.changeStatusPcesthtt(estimateNo[0].trim(),costCenterNo,new Long(EstimateStatus.EST_TOBE_APPROVAL_ES));
//			
//			
//		}
//		
//		
//		//if(status){
//			setMessageType(MSG_DONE);
//			setErrorMessage("DONE:- Successfully Posted for EE");
//		//}
//		fillEstimateList();
//		return SUCCESS;
//	}
	
	private void loaduserIds(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String region= (String) request.getSession().getAttribute("region");
		String smcType= (String) request.getSession().getAttribute("smcType");
		String costCenterNo=(String) request.getSession().getAttribute("costCenterNo");
		String branchType= (String) request.getSession().getAttribute("branchType");
		SecurityEjb secejb=new SecurityEjb(getSessionKey("region"));
		SpestedyEjb spestedyEjb=new SpestedyEjb(getSessionKey("region"));
		//DW
		List<String> userlevelList = new ArrayList<String>();
		List<String> userList =null;
		
		
		
		if(getSessionKey("userRole").equalsIgnoreCase("EE")){
			userlevelList.add("PE");
			userlevelList.add("CE");
			userlevelList.add("DGM");
		}if(getSessionKey("userRole").equalsIgnoreCase("DEO") && costCenterNo.equals("530.80")){//this done only for the UVA project 530.80
			userlevelList.add("EE");
			userlevelList.add("ES");
			costCenterNo = "530.20";
		}
		else{
			userlevelList.add("CE");
			userlevelList.add("EE");
			userlevelList.add("ES");
			userlevelList.add("DGM");
		}
		
		userList = secejb.getUserList(costCenterNo,userlevelList);
		
		if(costCenterNo.equals("450.50")||costCenterNo.equals("452.40")|| costCenterNo.equals("452.50")|| costCenterNo.equals("452.80")||costCenterNo.equals("452.90")||costCenterNo.equals("453.00")||costCenterNo.equals("453.10")||costCenterNo.equals("453.20")||costCenterNo.equals("453.30")||costCenterNo.equals("453.40")|| costCenterNo.equals("454.00")||costCenterNo.equals("454.10")||costCenterNo.equals("454.20")||costCenterNo.equals("454.50")||costCenterNo.equals("455.00")||costCenterNo.equals("455.10")||costCenterNo.equals("455.20")||costCenterNo.equals("455.30")||costCenterNo.equals("455.40")|| costCenterNo.equals("455.50")||costCenterNo.equals("456.00")||costCenterNo.equals("456.10")|| costCenterNo.equals("456.20")||costCenterNo.equals("456.30")||costCenterNo.equals("457.00")||costCenterNo.equals("457.10")||costCenterNo.equals("457.20")|| costCenterNo.equals("457.30")||costCenterNo.equals("450.00")|| costCenterNo.equals("450.80")||costCenterNo.equals("451.00")||costCenterNo.equals("451.10")||costCenterNo.equals("451.20")||costCenterNo.equals("451.30")||costCenterNo.equals("451.40")||costCenterNo.equals("451.50")||costCenterNo.equals("451.90") || costCenterNo.equals("452.00") || costCenterNo.equals("452.10")||costCenterNo.equals("452.20")||costCenterNo.equals("452.30")){
			costCenterNo = costCenterNo.substring(0, 3).concat(".00");
			
			userList = secejb.getUserList(costCenterNo,userlevelList);
			
		}
		
		if(branchType != null && (branchType.equalsIgnoreCase(Constants.BRANCHTYPE_AREA_UNIT) 
				|| branchType.equalsIgnoreCase(Constants.BRANCHTYPE_GOVERNMENT_INSTALLATION))){
				userlevelList.add("ES");
				userlevelList.add("EE");
				userlevelList.add("CE");
				userlevelList.add("DGM");
				
				userList = secejb.getAllUserList(costCenterNo,userlevelList);
		}
		if((getSessionKey("userRole").equalsIgnoreCase("EE") || getSessionKey("userRole").equalsIgnoreCase("CE") ) && costCenterNo.equalsIgnoreCase("420.90")){
			
			userlevelList.add("DGM");
			userList = secejb.getAllUserList(costCenterNo,userlevelList);
		}
		
		Iterator<String> usit = userList.iterator();
		
		 while (usit.hasNext()) {        	 
	        	String esUser=usit.next();
	        	
	        	listuserName.add(esUser);		       	        	        	
	    } 
	}
	
	/**public String post() throws ParseException  
	{
		try{
			HttpServletRequest request = ServletActionContext.getRequest();
			setLoggedData();
			nf.setGroupingUsed(true);
			nf.setMaximumFractionDigits(2);
			nf.setMinimumFractionDigits(2);
			
			PcesthttEjb pcesthttEjb = new PcesthttEjb(region);
			SecurityEjb secejb=new SecurityEjb(getSessionKey("region"));
			String usernamePostedTo = getPostUserName();
			String userLevelPostedTo = secejb.getAuthorizedLevel(usernamePostedTo);
			String postinguserLevel = secejb.getAuthorizedLevel(userId);
			//String totalCost = getTotalCost();
			BigDecimal detailCost = new BigDecimal("0");
			String[] estimateNo= getHiddenEstimateNo().split(",");
			//boolean status = false;
			if(estimateNo[0].trim() != null){
				applicationNo = estimateNo[0].trim();
			}	
			Calendar calendar = Calendar.getInstance();
			Format format=new Format();
			Approval approval=new Approval();
			approval.setApprovalId(null);
			approval.setReferenceNo(applicationNo);
			approval.setDeptId(costCenterNo);
			approval.setApprovalType("EE_VLDT");
			approval.setApprovedLevel(getSessionKey("userRole"));
			approval.setApprovedBy(getSessionKey("userName"));
			Long userStatus =null;
			if(userLevelPostedTo != null){
				if(userLevelPostedTo.trim().equalsIgnoreCase("ES")){
					//if(postinguserLevel.equalsIgnoreCase("DEO")){
						userStatus = Long.parseLong(EstimateStatus.EST_APPROVAL_ES);
						approval.setApprovedLevel(getSessionKey("userRole"));
						approval.setApprovedBy(getSessionKey("userRole"));
						approval.setApprovalType("EST_ENTY");
						
					//}else if(postinguserLevel.equalsIgnoreCase("ES")){
						//userStatus = StandardEstimateStatus.TO_BE_CHECKED_BY_ELECTRICAL_SUPIRINTENDENT2.getKey();
						//approval.setApprovedLevel(getSessionKey("userRole"));
						//approval.setApprovedBy(getSessionKey("userRole"));
						//approval.setApprovalType("EST_ENTY");
					//}
					
					
				}else if(userLevelPostedTo.trim().equalsIgnoreCase("EE")){
					userStatus = Long.parseLong(EstimateStatus.EST_APPROVAL_EE);
					approval.setApprovedLevel(getSessionKey("userRole"));
					approval.setApprovedBy(getSessionKey("userRole"));
					approval.setApprovalType("EST_ENTY");
					
				}else if(userLevelPostedTo.trim().equalsIgnoreCase("CE")){
					userStatus = Long.parseLong(EstimateStatus.EST_APPROVAL_CE);
					approval.setApprovedLevel(getSessionKey("userRole"));
					approval.setApprovedBy(getSessionKey("userRole"));
					approval.setApprovalType("EST_ENTY");
					
				}else if(userLevelPostedTo.trim().equalsIgnoreCase("DGM")){
					userStatus = Long.parseLong(EstimateStatus.EST_APPROVAL_DGM);
					approval.setApprovedLevel(getSessionKey("userRole"));
					approval.setApprovedBy(getSessionKey("userRole"));
					approval.setApprovalType("EST_ENTY");
					
				}
			}
			
			approval.setApprovedDate(calendar.getTime());
			approval.setApprovedTime(format.FormatTime());
	
			approval.setFromStatus(new BigDecimal(EstimateStatus.NEW
					).toString());
			approval.setToStatus(new BigDecimal(userStatus).toString());
			//************* comment for SAN_TEST ****************
			//approval.setStandardCost(new BigDecimal(Double.toString(nf.parse(getStandardCost(applicationNo,costCenterNo).toString()).doubleValue())));
			approval.setStandardCost(new BigDecimal("0.0"));
			PcesthttPK phtpk = new PcesthttPK();
			phtpk.setDeptId(costCenterNo);
			phtpk.setEstimateNo(applicationNo.trim());
			phtpk.setRevNo(Constants.REV_NO);
			
			Pcesthtt pcesthtt = pcesthttEjb.findById(phtpk);
			
			if(pcesthtt != null){
				detailCost = pcesthtt.getStdCost();
			}
			 
			if(detailCost != null ){
				approval.setDetailedCost(detailCost);
			}
			pcesthtt.setStatus(userStatus);
			pcesthtt.setAprUid5(usernamePostedTo);
			pcesthttEjb.validateEstimate(pcesthtt, approval);
			//clearFields();
			setMessageType(MSG_DONE);
			setErrorMessage("DONE:- Estimate sent for approval successfully!");
			//setSuccessMessage("Estimate sent for approval successfully!");
			/*String[] estimateNo= getHiddenEstimateNo().split(",");
			boolean status = false;
			if(estimateNo[0].trim() != null){
			
				pcesthttEjb.changeStatusPcesthtt(estimateNo[0].trim(),costCenterNo,new Long(EstimateStatus.EST_APPROVAL_EE));
				
				
			}
			
			
			//if(status){
				setMessageType(MSG_DONE);
				setErrorMessage("DONE:- Successfully Posted for EE");
			//}
	*/		
	/**	}catch(Exception e){
			return ERROR;
		}
		fillEstimateList();
		loaduserIds();
		return SUCCESS;
	}*/
	
	public String post() throws ParseException  
	{
		try{
			HttpServletRequest request = ServletActionContext.getRequest();
			setLoggedData();
			nf.setGroupingUsed(true);
			nf.setMaximumFractionDigits(2);
			nf.setMinimumFractionDigits(2);
			
			PcesthttEjb pcesthttEjb = new PcesthttEjb(region);
			SecurityEjb secejb=new SecurityEjb(getSessionKey("region"));
			String usernamePostedTo = getPostUserName();
			String userLevelPostedTo = secejb.getAuthorizedLevel(usernamePostedTo);
			String postinguserLevel = secejb.getAuthorizedLevel(userId);
			//String totalCost = getTotalCost();
			BigDecimal detailCost = new BigDecimal("0");
			//String[] estimateNo= getHiddenEstimateNo().split(",");
			//boolean status = false;
			if(DetailsList!=null){
				for(int i=0;i<DetailsList.length;i++){
					applicationNo = DetailsList[i].trim();
					estimateNo=applicationNo;
				
			Calendar calendar = Calendar.getInstance();
			Format format=new Format();
			Approval approval=new Approval();
			approval.setApprovalId(null);
			approval.setReferenceNo(applicationNo);
			approval.setDeptId(costCenterNo);
			approval.setApprovalType("EE_VLDT");
			approval.setApprovedLevel(getSessionKey("userRole"));
			approval.setApprovedBy(getSessionKey("userName"));
			Long userStatus =null;
			if(userLevelPostedTo != null){
				if(userLevelPostedTo.trim().equalsIgnoreCase("ES")){
					//if(postinguserLevel.equalsIgnoreCase("DEO")){
						userStatus = Long.parseLong(EstimateStatus.EST_APPROVAL_ES);
						approval.setApprovedLevel(getSessionKey("userRole"));
						approval.setApprovedBy(getSessionKey("userRole"));
						approval.setApprovalType("EST_ENTY");
						
					//}else if(postinguserLevel.equalsIgnoreCase("ES")){
						//userStatus = StandardEstimateStatus.TO_BE_CHECKED_BY_ELECTRICAL_SUPIRINTENDENT2.getKey();
						//approval.setApprovedLevel(getSessionKey("userRole"));
						//approval.setApprovedBy(getSessionKey("userRole"));
						//approval.setApprovalType("EST_ENTY");
					//}
					
					
				}else if(userLevelPostedTo.trim().equalsIgnoreCase("EE")){
					userStatus = Long.parseLong(EstimateStatus.EST_APPROVAL_EE);
					approval.setApprovedLevel(getSessionKey("userRole"));
					approval.setApprovedBy(getSessionKey("userRole"));
					approval.setApprovalType("EST_ENTY");
					
				}else if(userLevelPostedTo.trim().equalsIgnoreCase("CE")){
					userStatus = Long.parseLong(EstimateStatus.EST_APPROVAL_CE);
					approval.setApprovedLevel(getSessionKey("userRole"));
					approval.setApprovedBy(getSessionKey("userRole"));
					approval.setApprovalType("EST_ENTY");
					
				}else if(userLevelPostedTo.trim().equalsIgnoreCase("DGM")){
					userStatus = Long.parseLong(EstimateStatus.EST_APPROVAL_DGM);
					approval.setApprovedLevel(getSessionKey("userRole"));
					approval.setApprovedBy(getSessionKey("userRole"));
					approval.setApprovalType("EST_ENTY");
					
				}
			}
			
			approval.setApprovedDate(calendar.getTime());
			approval.setApprovedTime(format.FormatTime());
	
			approval.setFromStatus(new BigDecimal(EstimateStatus.NEW
					).toString());
			approval.setToStatus(new BigDecimal(userStatus).toString());
			//************* comment for SAN_TEST ****************
			//approval.setStandardCost(new BigDecimal(Double.toString(nf.parse(getStandardCost(applicationNo,costCenterNo).toString()).doubleValue())));
			approval.setStandardCost(new BigDecimal("0.0"));
			PcesthttPK phtpk = new PcesthttPK();
			phtpk.setDeptId(costCenterNo);
			phtpk.setEstimateNo(applicationNo.trim());
			phtpk.setRevNo(Constants.REV_NO);
			
			Pcesthtt pcesthtt = pcesthttEjb.findById(phtpk);
			
			if(pcesthtt != null){
				detailCost = pcesthtt.getStdCost();
			}
			 
			if(detailCost != null ){
				approval.setDetailedCost(detailCost);
			}
			pcesthtt.setStatus(userStatus);
			pcesthtt.setAprUid5(usernamePostedTo);
			pcesthttEjb.validateEstimate(pcesthtt, approval);
			//clearFields();
			setMessageType(MSG_DONE);
			setErrorMessage("DONE:- Estimate sent for approval successfully!");
			//setSuccessMessage("Estimate sent for approval successfully!");
			/*String[] estimateNo= getHiddenEstimateNo().split(",");
			boolean status = false;
			if(estimateNo[0].trim() != null){
			
				pcesthttEjb.changeStatusPcesthtt(estimateNo[0].trim(),costCenterNo,new Long(EstimateStatus.EST_APPROVAL_EE));
				
				
			}
			
			
			//if(status){
			}	setMessageType(MSG_DONE);
				setErrorMessage("DONE:- Successfully Posted for EE");*/
				}}
			
		}catch(Exception e){
			return ERROR;
		}
		fillEstimateList();
		loaduserIds();
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
	/*public String postCE()
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
		fillEstimateList();
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
		fillEstimateList();
		return SUCCESS;
	}*/
	public String close() {
		
		return "close";
	}
	
	private void loadEstimateNos(){
		setLoggedData();
		List<String> estimationRefNos = null;
		HttpServletRequest request = ServletActionContext.getRequest();
		
		PcesthttEjb pcesthttEjb = new PcesthttEjb(region);
		List<Long> status = new ArrayList<Long>();
		
		
		if(getSessionKey("userRole").equalsIgnoreCase("ES")){
			status.add(new Long(EstimateStatus.EST_APPROVAL_ES));
			//status.add(new Long(EstimateStatus.EST_APPROVAL_ES2));
			estimationRefNos = pcesthttEjb.getEstimateNoList(costCenterNo,status,null); 
			
		}
		else if(getSessionKey("userRole").equalsIgnoreCase("EE")){
			status.add(new Long(EstimateStatus.EST_APPROVAL_EE));
			estimationRefNos = pcesthttEjb.getEstimateNoList(costCenterNo,status,null); 
			
		}else if(getSessionKey("userRole").equalsIgnoreCase("CE")){
			estimationRefNos = pcesthttEjb.getEstimateNoList(costCenterNo,new Long(EstimateStatus.EST_APPROVAL_CE)); 
			
		}/*else if(getSessionKey("userRole").equalsIgnoreCase("PE")){
			estimationRefNos = pcesthttEjb.getEstimateNoList(costCenterNo,new BigDecimal(EstimateStatus.EST_APPROVAL_DGM)); 
		}*/else if(getSessionKey("userRole").equalsIgnoreCase("DGM")){
			estimationRefNos = pcesthttEjb.getEstimateNoList(costCenterNo,new Long(EstimateStatus.EST_APPROVAL_DGM)); 
		}
		
		//request.getSession().setAttribute("estimationRefNos",estimationRefNos);
		request.getSession().setAttribute("estimateNos",estimationRefNos);	
	}
	/*public String approve()
	{
		try{
			setLoggedData();
			HttpServletRequest request = ServletActionContext.getRequest();
			
			PcesthttEjb pcesthttEjb = new PcesthttEjb(region);
			
			String estimateNo= getEstimateNo();
			setErrorMessage(null);
			String usernamePostedTo = getPostUserName();
			String msg = pcesthttEjb.approveEstimate(estimateNo.trim(), getCostCenterNo(), getSession().get("userRole").toString(),getSessionKey("userName"),usernamePostedTo,getSession().get("smcType").toString());
			int errorIndex = msg.indexOf('@');
			int successIndex = msg.indexOf('#');
			msg =msg.substring(1);
			//earFields();
			//tFormData();
			if(errorIndex!=-1){
				setMessageType(ERROR);
				setErrorMessage(msg);
				
			}else if(successIndex!=-1){
				setMessageType(MSG_DONE);
			
				setErrorMessage(msg);
			}
		}catch(Exception e){
			return ERROR;
		}
		loadEstimateNos();
		return SUCCESS;
	}
	public String recommendEstimate()
	{
		try{
			setLoggedData();
			HttpServletRequest request = ServletActionContext.getRequest();
			
			PcesthttEjb pcesthttEjb = new PcesthttEjb(region);
			
			String estimateNo= getEstimateNo();
			setErrorMessage(null);
			String usernamePostedTo = getPostUserName();
			String msg = pcesthttEjb.recommendEstimate(estimateNo.trim(), getCostCenterNo(), getSession().get("userRole").toString(),getSessionKey("userName"),usernamePostedTo,getSession().get("smcType").toString());
			int errorIndex = msg.indexOf('@');
			int successIndex = msg.indexOf('#');
			msg =msg.substring(1);
			//earFields();
			//tFormData();
			if(errorIndex!=-1){
				setMessageType(ERROR);
				setErrorMessage(msg);
				
			}else if(successIndex!=-1){
				setMessageType(MSG_DONE);
			
				setErrorMessage(msg);
			}
		}catch(Exception e){
			return ERROR;
		}
		loadEstimateNos();
		return SUCCESS;
	}
	public String reject()
	{
		try{
		setLoggedData();
		HttpServletRequest request = ServletActionContext.getRequest();
	
		PcesthttEjb pcesthttEjb = new PcesthttEjb(region);
		ApproveDetails detail = new ApproveDetails();
		String estimateNo= getEstimateNo();
		
		//PcesthttEjb pcesthttEjb = new PcesthttEjb(getSessionKey("region"));
		
		Pcesthtt pcesthtt = pcesthttEjb.findByEstimationNo(estimateNo, costCenterNo,Constants.REV_NO);
		
		Long preStatus = pcesthtt.getStatus();
		pcesthtt.setRejectReason(rejectReason);
		pcesthtt.setRejctUid(getSessionKey("userName"));
		pcesthtt.setRejctDt(new Date());
		pcesthtt.setStatus(new Long(EstimateStatus.EST_REJECTED));
		
		nf.setGroupingUsed(true);
		nf.setMaximumFractionDigits(2);
		nf.setMinimumFractionDigits(2);
		
		//pcesthttEjb.updatePcesthtt(pcesthtt);
		Calendar calendar = Calendar.getInstance();
		Format format=new Format();
		Approval approval=new Approval();
		approval.setApprovalId(null);
		approval.setReferenceNo(estimateNo.trim());
		approval.setDeptId(costCenterNo);
		approval.setApprovalType("DES_RJCT");
		approval.setApprovedLevel(getSessionKey("userRole"));
		approval.setApprovedBy(getSessionKey("userName"));
		approval.setApprovedDate(calendar.getTime());
		approval.setApprovedTime(format.FormatTime());
		approval.setReason(getRejectReason());
		approval.setFromStatus(preStatus.toString());
		approval.setToStatus(pcesthtt.getStatus().toString());
		//************** comment for SAN_TEST ******************
		//approval.setStandardCost(new BigDecimal(Double.toString(nf.parse(getStandardCost(estimateNo,costCenterNo).toString()).doubleValue())));
		approval.setStandardCost(new BigDecimal("0.0"));
		
		BigDecimal cost = pcesthttEjb.findById(estimateNo, costCenterNo, Constants.REV_NO).getStdCost();
		String detailCost = null;
		if(cost != null){
			detailCost = cost.toString();
			approval.setDetailedCost(new BigDecimal(Double.toString(nf.parse(detailCost).doubleValue())));
			
		}
		
		pcesthttEjb.rejectEstimate(pcesthtt, approval);
		setMessageType(MSG_DONE);
		setErrorMessage("Estimate rejected successfully.");
		
		}
		catch(Exception e){}
		
		boolean status = false;
		if(estimateNo != null && getSessionKey("userRole").equalsIgnoreCase("EE")){
		
			
			//pcesthttEjb.changeStatusPcesthtt(estimateNo,costCenterNo,new BigDecimal(EstimateStatus.EST_REJECTED));
			pcesthttEjb.updateEstimateDetails(estimateNo,costCenterNo,new Long(EstimateStatus.EST_REJECTED),userId,new Date(),null,null,null,null,null,null,getRejectReason());
			setErrorMessage("DONE:- Rejected By EE");
		}else if(estimateNo != null && getSessionKey("userRole").equalsIgnoreCase("ES")){
		
			//pcesthttEjb.changeStatusPcesthtt(estimateNo,costCenterNo,new BigDecimal(EstimateStatus.EST_REJECTED));
			pcesthttEjb.updateEstimateDetails(estimateNo,costCenterNo,new Long(EstimateStatus.EST_REJECTED),userId,new Date(),null,null,null,null,null,null,getRejectReason());
			setErrorMessage("DONE:- Rejected By ES");
		}else if(estimateNo != null && getSessionKey("userRole").equalsIgnoreCase("CE")){
		
			//pcesthttEjb.changeStatusPcesthtt(estimateNo,costCenterNo,new BigDecimal(EstimateStatus.EST_REJECTED));
			pcesthttEjb.updateEstimateDetails(estimateNo,costCenterNo,new Long(EstimateStatus.EST_REJECTED),userId,new Date(),null,null,null,null,null,null,getRejectReason());
			setErrorMessage("DONE:- Rejected By CE");
		}else if(estimateNo != null && getSessionKey("userRole").equalsIgnoreCase("PE")){
		
			pcesthttEjb.changeStatusPcesthtt(estimateNo,costCenterNo,new BigDecimal(EstimateStatus.EST_REJECTED));
			
			setErrorMessage("DONE:- Rejected By PE");
		}else if(estimateNo != null && getSessionKey("userRole").equalsIgnoreCase("DGM")){
			
			//pcesthttEjb.changeStatusPcesthtt(estimateNo,costCenterNo,new BigDecimal(EstimateStatus.EST_REJECTED));
			pcesthttEjb.updateEstimateDetails(estimateNo,costCenterNo,new Long(EstimateStatus.EST_REJECTED),userId,new Date(),null,null,null,null,null,null,getRejectReason());
			setErrorMessage("DONE:- Rejected By DGM");
		}
		
		
		//if(status){
			setMessageType(MSG_DONE);
			
		//}
		loadEstimateNos();
		return SUCCESS;
	}*/
	
	public String PrintEstimateA4(){
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
		param.put("@project_no", "'"+getEstimateNo()+"'");
		param.put("@costctr","'"+costCenterNo+"'");

		/*System.out.println("Application Number :  " + getApplicationNo());
		System.out.println("CC Number :  " + costCenterNo);
		param.put("@appNo", "'"+getApplicationNo()+"'");
		param.put("@costctr","'"+costCenterNo+"'");*/
		String fileName = null;
		
		fileName = rept.generateReport("EstimateA4",param,getSession(),REPORT_DIRECTORY,EXPORT_REPORT_DIRECTORY);
		
	
		
		
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
	public String getEstimateNo() {
		return estimateNo;
	}
	public void setEstimateNo(String estimateNo) {
		this.estimateNo = estimateNo;
	}
	public String getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
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

	 

	public String getApplicationType() {
		return applicationType;
	}
	public void setApplicationType(String applicationType) {
		this.applicationType = applicationType;
	}
	public Map<String, Object> getSession() {
		return session;
	}

	public Map<String, String[]> getParameters() {
		return parameters;
	}

	public List<Pcesthtt> getEstimateList() {
		return EstimateList;
	}

	public void setEstimateList(List<Pcesthtt> estimateList) {
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

	public String getStdtotalCost() {
		return stdtotalCost;
	}
	public void setStdtotalCost(String stdtotalCost) {
		this.stdtotalCost = stdtotalCost;
	}
	public String getRebateCost() {
		return rebateCost;
	}
	public void setRebateCost(String rebateCost) {
		this.rebateCost = rebateCost;
	}
	public String getVariance() {
		return variance;
	}
	public void setVariance(String variance) {
		this.variance = variance;
	}
	
	
	public String[] getDetailsList() {
		return DetailsList;
	}
	public void setDetailsList(String[] detailsList) {
		DetailsList = detailsList;
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
	public String newDirectFroValidate()
	{
		
		setNewEntryFromMainMenu();
		return SUCCESS;
	}
	public String newDirect(){
		setLblError(null);
		
		setLoggedData();
		loadEstimateNos();
		return SUCCESS;
	}
	
	public void setNewEntryFromMainMenu(){
		setLoggedData();
		HttpServletRequest request = ServletActionContext.getRequest();
		String sessionKey= (String) request.getSession().getAttribute("region");
		String estimateNo=request.getParameter("estNo");
		String costCen=request.getParameter("costCen");
		if(estimateNo!=null && estimateNo.length()>0 && costCen!=null && costCen.length()>0){
			setEstimateNo(estimateNo);
			
			//viewApplicantDetails();
			
		}
		List<String> wEstimationRefNos = new ArrayList<String>();
		List<String> sEstimationRefNos = new ArrayList<String>();
		if(estimateNo != null){
			wEstimationRefNos.add(estimateNo);
			
			request.getSession().setAttribute("estimateNos",wEstimationRefNos);
		}
		EstimateReferenceEjb estejb = new EstimateReferenceEjb();
		EstimateReference refe = estejb.findByWorkEstimateNo(estimateNo,costCen, sessionKey);
		if(refe != null){
			
			sEstimationRefNos.add(refe.getId().getStandardEstimateNo());
			request.getSession().setAttribute("estimationRefNos",sEstimationRefNos);
			//loadEstmationNumbers(request);
		}
		
		String userId= (String) request.getSession().getAttribute("userName");
		
		//setUserId(userId);
		//setRegion(sessionKey);
		
		
	}
	
	
	public String fillEstimateListForSendPLCOM()
	
	
	{
		
		System.out.println("inside fillEstimateListForSendPLCOM");
		HttpServletRequest request = ServletActionContext.getRequest();
		setLoggedData();
		PcesthttEjb pcesthttEjb = new PcesthttEjb(region); 
		List<Long> status = new ArrayList<Long>();
		
		status.add(new Long(EstimateStatus.EST_APPROVAL_DGM)); 
		//status.add(new Long(EstimateStatus.EST_REJECTED));
		//if(getSession().get("userRole").toString().equalsIgnoreCase("ES")){
			//status.add(new Long(EstimateStatus.EST_APPROVAL_ES));
		//}
		try {
			this.EstimateList = pcesthttEjb.getEstimateList(costCenterNo, Constants.REV_NO, status,null);
			
			//request.getSession().setAttribute("estimateList", this.EstimateList);
			//RequestDispatcher dispatcher = request.getRequestDispatcher("/sendForValidation.jsp");
			//dispatcher.forward( request, ServletActionContext.getResponse()); 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        setLblError(null);
		
		setLoggedData();
		
		
		//loaduserIdForPLCOM();
		return SUCCESS;
	}
	
	public String postPL() throws ParseException  
	{
		try{
			
			System.out.println("inside postPL");
			HttpServletRequest request = ServletActionContext.getRequest();
			setLoggedData();
			nf.setGroupingUsed(true);
			nf.setMaximumFractionDigits(2);
			nf.setMinimumFractionDigits(2);
			
			PcesthttEjb pcesthttEjb = new PcesthttEjb(region);
			
			SecurityEjb secejb=new SecurityEjb(getSessionKey("region"));
			
			//String usernamePostedTo = getPostUserName();
			//String userLevelPostedTo = secejb.getAuthorizedLevel(usernamePostedTo);
			//String postinguserLevel = secejb.getAuthorizedLevel(userId);
			//String totalCost = getTotalCost();
			BigDecimal detailCost = new BigDecimal("0");
			//String[] estimateNo= getHiddenEstimateNo().split(",");
			//boolean status = false;
			//fillEstimateListForSendPLCOM();
			//getDetailsList();
			if(DetailsList!=null){
				for(int i=0;i<DetailsList.length;i++){
					applicationNo = DetailsList[i].trim();
					estimateNo=applicationNo;
				
			Calendar calendar = Calendar.getInstance();
			Format format=new Format();
			Approval approval=new Approval();
			approval.setApprovalId(null);
			approval.setReferenceNo(applicationNo);
			approval.setDeptId(costCenterNo);
			approval.setApprovalType("EE_VLDT");
			approval.setApprovedLevel(getSessionKey("userRole"));
			approval.setApprovedBy(getSessionKey("userName"));
			Long userStatus =null;
			
			
					userStatus = Long.parseLong(EstimateStatus.EST_APPROVAL_PLCE);
					approval.setApprovedLevel(getSessionKey("userRole"));
					approval.setApprovedBy(getSessionKey("userRole"));
					approval.setApprovalType("EST_APRO");
					
				
			
			
			approval.setApprovedDate(calendar.getTime());
			approval.setApprovedTime(format.FormatTime());
	
			approval.setFromStatus(new BigDecimal(EstimateStatus.NEW
					).toString());
			approval.setToStatus(new BigDecimal(userStatus).toString());
			//************* comment for SAN_TEST ****************
			//approval.setStandardCost(new BigDecimal(Double.toString(nf.parse(getStandardCost(applicationNo,costCenterNo).toString()).doubleValue())));
			approval.setStandardCost(new BigDecimal("0.0"));
			PcesthttPK phtpk = new PcesthttPK();
			phtpk.setDeptId(costCenterNo);
			phtpk.setEstimateNo(applicationNo.trim());
			phtpk.setRevNo(Constants.REV_NO);
			
			Pcesthtt pcesthtt = pcesthttEjb.findById(phtpk);
			
			if(pcesthtt != null){
				detailCost = pcesthtt.getStdCost();
			}
			 
			if(detailCost != null ){
				approval.setDetailedCost(detailCost);
			}
			pcesthtt.setStatus(userStatus);
			//pcesthtt.setAprUid5(usernamePostedTo);
			pcesthttEjb.validateEstimate(pcesthtt, approval);
			//clearFields();
			setMessageType(MSG_DONE);
			setErrorMessage("DONE:- Estimate sent for approval successfully!");
			//setSuccessMessage("Estimate sent for approval successfully!");
			/*String[] estimateNo= getHiddenEstimateNo().split(",");
			boolean status = false;
			if(estimateNo[0].trim() != null){
			
				pcesthttEjb.changeStatusPcesthtt(estimateNo[0].trim(),costCenterNo,new Long(EstimateStatus.EST_APPROVAL_EE));
				
				
			}
			
			
			//if(status){
			}	setMessageType(MSG_DONE);
				setErrorMessage("DONE:- Successfully Posted for EE");*/
				}}
			
			fillEstimateListForSendPLCOM();
		}catch(Exception e){
			System.out.println("ddd : "+ e);
			return ERROR;
		}
		//
		//fillEstimateList();
		loaduserIds();
		return SUCCESS;
	}

	public String postCOM() throws ParseException  
	{
		try{
			
			System.out.println("inside postCOM");
			HttpServletRequest request = ServletActionContext.getRequest();
			setLoggedData();
			nf.setGroupingUsed(true);
			nf.setMaximumFractionDigits(2);
			nf.setMinimumFractionDigits(2);
			System.out.println("inside postCOM 1" + DetailsList);
			PcesthttEjb pcesthttEjb = new PcesthttEjb(region);
			SecurityEjb secejb=new SecurityEjb(getSessionKey("region"));
			//String usernamePostedTo = getPostUserName();
			//String userLevelPostedTo = secejb.getAuthorizedLevel(usernamePostedTo);
			//String postinguserLevel = secejb.getAuthorizedLevel(userId);
			//String totalCost = getTotalCost();
			BigDecimal detailCost = new BigDecimal("0");
			//String[] estimateNo= getHiddenEstimateNo().split(",");
			//boolean status = false;
			//fillEstimateListForSendPLCOM();
			if(DetailsList!=null){
				for(int i=0;i<DetailsList.length;i++){
					applicationNo = DetailsList[i].trim();
					estimateNo=applicationNo;
					System.out.println("inside postCOM 2");
			Calendar calendar = Calendar.getInstance();
			Format format=new Format();
			Approval approval=new Approval();
			approval.setApprovalId(null);
			approval.setReferenceNo(applicationNo);
			approval.setDeptId(costCenterNo);
			approval.setApprovalType("EE_VLDT");
			approval.setApprovedLevel(getSessionKey("userRole"));
			approval.setApprovedBy(getSessionKey("userName"));
			Long userStatus =null;
			//if(userLevelPostedTo != null){
					userStatus = Long.parseLong(EstimateStatus.EST_APPROVAL_COMCE);
					approval.setApprovedLevel(getSessionKey("userRole"));
					approval.setApprovedBy(getSessionKey("userRole"));
					approval.setApprovalType("EST_APRO");
			//}
					System.out.println("inside postCOM 3");
			approval.setApprovedDate(calendar.getTime());
			approval.setApprovedTime(format.FormatTime());
	
			approval.setFromStatus(new BigDecimal(EstimateStatus.EST_APPROVAL_CE).toString());
			approval.setToStatus(new BigDecimal(userStatus).toString());
			//************* comment for SAN_TEST ****************
			//approval.setStandardCost(new BigDecimal(Double.toString(nf.parse(getStandardCost(applicationNo,costCenterNo).toString()).doubleValue())));
			approval.setStandardCost(new BigDecimal("0.0"));
			PcesthttPK phtpk = new PcesthttPK();
			phtpk.setDeptId(costCenterNo);
			phtpk.setEstimateNo(applicationNo.trim());
			phtpk.setRevNo(Constants.REV_NO);
			
			Pcesthtt pcesthtt = pcesthttEjb.findById(phtpk);
			
			if(pcesthtt != null){
				detailCost = pcesthtt.getStdCost();
			}
			 
			if(detailCost != null ){
				approval.setDetailedCost(detailCost);
			}
			System.out.println("inside postCOM 4");
			pcesthtt.setStatus(userStatus);
			//pcesthtt.setAprUid5(usernamePostedTo);
			pcesthttEjb.validateEstimate(pcesthtt, approval);
			//clearFields();
			setMessageType(MSG_DONE);
			setErrorMessage("DONE:- Estimate sent for approval successfully!");
			//setSuccessMessage("Estimate sent for approval successfully!");
			/*String[] estimateNo= getHiddenEstimateNo().split(",");
			boolean status = false;
			if(estimateNo[0].trim() != null){
			
				pcesthttEjb.changeStatusPcesthtt(estimateNo[0].trim(),costCenterNo,new Long(EstimateStatus.EST_APPROVAL_EE));
				
				
			}
			
			
			//if(status){
			}	setMessageType(MSG_DONE);
				setErrorMessage("DONE:- Successfully Posted for EE");*/
				}}
			System.out.println("inside postCOM 5");
			fillEstimateListForSendPLCOM();
		}catch(Exception e){
			return ERROR;
		}
		//fillEstimateList();
		loaduserIds();
		return SUCCESS;
	}



	
}
