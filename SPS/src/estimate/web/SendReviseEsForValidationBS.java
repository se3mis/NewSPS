package estimate.web;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.SessionAware;
//import org.apache.tomcat.jni.Status;

import util.common.ApplicationStatus;
import util.common.Constants;
import util.common.EstimateStatus;
import util.common.StandardEstimateStatus;


import com.opensymphony.xwork2.ActionSupport;


import estimate.dto.ApproveDetails;
import estimate.model.EstimateReference;
import estimate.model.Pcesthtt;
import estimate.model.Pcfunddm;
import estimate.model.Splaborm;
import estimate.model.Spstdesthmt;
import estimate.service.EstimateEjb;
import estimate.service.EstimateReferenceEjb;
import estimate.service.PcesthttEjb;
import estimate.service.SpestedyConEjb;
import estimate.service.SplabormEjb;
 

public class SendReviseEsForValidationBS extends ActionSupport implements SessionAware, ParameterAware  {
	private Map<String, Object> session;
	private Map <String, String[]> parameters;
	private String loggedInUserId;
	private String loggedInUserLevel;
	private String path;
	private String lblError = null;
	private String lblSuccess = null;

	private String hiddenEstimateNo;
	private List<Pcesthtt> EstimateList;

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
	private String userId;
	private String password;
	private String region;
	private String costCenterNo;
	private String costCenterName;
	
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
	public String execute(){
		setLblError(null);
		
		setLoggedInUserId(getSession().get("userName").toString());
		
		fillEstimateList();
		return SUCCESS;
	}
	public String newDirect(){
		setLblError(null);
		
		setLoggedInUserId(getSession().get("userName").toString());
		setInfoFromMainMenu();
		//loadEstimateNos();
		loadEstimationRefNumbers();//to load Application status to be changed
		//loadWorkEstimationRefNumbers();
	
		loadWareHouse();
		loadFundSources();
		return SUCCESS;
	}
	private void fillEstimateList()
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		String region= (String) request.getSession().getAttribute("region");
		String costCenterNo=(String) request.getSession().getAttribute("costCenterNo");
		PcesthttEjb pcesthttEjb = new PcesthttEjb(region); 
		List<Long> status = new ArrayList<Long>();
		
		//status.add(EstimateStatus.APPLICATION_MODIFIED.getKey()); 
		try {
			this.EstimateList = pcesthttEjb.getEstimateList(costCenterNo, Constants.REV_NO, new Long(EstimateStatus.NEW));
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
		HttpServletRequest request = ServletActionContext.getRequest();
		String region= (String) request.getSession().getAttribute("region");
		String costCenterNo=(String) request.getSession().getAttribute("costCenterNo");
		PcesthttEjb pcesthttEjb = new PcesthttEjb(region);
		
		String[] estimateNo= getHiddenEstimateNo().split(",");
		boolean status = false;
		if(estimateNo[0].trim() != null){
		
			//pcesthttEjb.changeStatusPcesthtt(estimateNo[0].trim(),costCenterNo,new Long(EstimateStatus.EST_TOBE_APPROVAL_EE));
			
			
		}
		
		
		//if(status){
			setMessageType(MSG_DONE);
			setErrorMessage("DONE:- Successfully Posted for EE");
		//}
		fillEstimateList();
		return SUCCESS;
	}
	public void setInfoFromMainMenu(){

		HttpServletRequest request = ServletActionContext.getRequest();
		String region= (String) request.getSession().getAttribute("region");
	
		String costCenterNo=(String) request.getSession().getAttribute("costCenterNo");
		String userId= (String) request.getSession().getAttribute("userName");
		setCostCenterNo(costCenterNo);
		setUserId(userId);
		setRegion(region);
		
	}
	public void loadEstimationRefNumbers(){
		try{
			List<String> estimationRefNos = null;
			SpestedyConEjb estimateEjb = new SpestedyConEjb(getRegion());
			estimationRefNos = estimateEjb.loadApplicationRefnos(getUserId(),getCostCenterNo(),null); 
			HttpServletRequest request = ServletActionContext.getRequest();
			request.getSession().setAttribute("estimationRefNos",estimationRefNos);
		}catch(Exception e){
			
		}
		
		
		
	}
	
	public void loadWorkEstimationRefNumbers(){
		try{
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
			List<Pcfunddm> fundSources = null;
			EstimateEjb estimateEjb = new EstimateEjb(getRegion());
			fundSources = estimateEjb.getFundSourcesList(getCostCenterNo(),getRegion()); //SEND_FOR_CONSTRUCTION_MAINTENANCE
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
	
	/*private void loadEstimateNos(){
		List<String> estimationRefNos = null;
		HttpServletRequest request = ServletActionContext.getRequest();
		String region= (String) request.getSession().getAttribute("region");
		String costCenterNo=(String) request.getSession().getAttribute("costCenterNo");
		String userId= (String) request.getSession().getAttribute("userName");
		PcesthttEjb pcesthttEjb = new PcesthttEjb(region);
		//List<Long> status = new ArrayList<Long>();
		
		
		
		if(getSessionKey("userRole").equalsIgnoreCase("EE")){
			//status.add(StandardEstimateStatus.TO_BE_VALIDATED_BY_ELECTRICAL_ENGINEER.getKey());
			estimationRefNos = pcesthttEjb.getEstimateNoList(costCenterNo,new Long(EstimateStatus.EST_TOBE_APPROVAL_EE)); 
			
		}else if(getSessionKey("userRole").equalsIgnoreCase("CE")){
			estimationRefNos = pcesthttEjb.getEstimateNoList(costCenterNo,new Long(EstimateStatus.EST_TOBE_APPROVAL_CE)); 
			
		}else if(getSessionKey("userRole").equalsIgnoreCase("PE")){
			estimationRefNos = pcesthttEjb.getEstimateNoList(costCenterNo,new BigDecimal(EstimateStatus.EST_APPROVAL_DGM)); 
		}else if(getSessionKey("userRole").equalsIgnoreCase("DGM")){
			estimationRefNos = pcesthttEjb.getEstimateNoList(costCenterNo,new Long(EstimateStatus.EST_TOBE_APPROVAL_DGM)); 
		}
		
		//request.getSession().setAttribute("estimationRefNos",estimationRefNos);
		request.getSession().setAttribute("estimateNos",estimationRefNos);	
	}
	public String approve()
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		String region= (String) request.getSession().getAttribute("region");
		String costCenterNo=(String) request.getSession().getAttribute("costCenterNo");
		String userId= (String) request.getSession().getAttribute("userName");
		PcesthttEjb pcesthttEjb = new PcesthttEjb(region);
		ApproveDetails detail = new ApproveDetails();
		
		boolean status = false;
		
		
		String estimateNo= getEstimateNo();
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
				pcesthttEjb.updateEstimateDetails(estimateNo,costCenterNo,new Long(EstimateStatus.EST_TOBE_APPROVAL_CE),null,null,userId,new Date(),null,null,null,null,null);
				setErrorMessage("DONE:- Successfully Posted for CE");
			
			
		}else if(estimateNo != null && getSessionKey("userRole").equalsIgnoreCase("CE")){
			
			
			//pcesthttEjb.changeStatusPcesthtt(estimateNo,costCenterNo,new BigDecimal(EstimateStatus.EST_TOBE_APPROVAL_DGM));
			pcesthttEjb.updateEstimateDetails(estimateNo,costCenterNo,new Long(EstimateStatus.EST_TOBE_APPROVAL_DGM),null,null,null,null,userId,new Date(),null,null,null);
				setErrorMessage("DONE:- Successfully Posted for DGM");
			
			
		}else if(estimateNo != null && getSessionKey("userRole").equalsIgnoreCase("PE")){
			
			
			status= estimateEjb.updateEstimateStatus(estimateNo, costCenterNo, StandardEstimateStatus.TO_BE_VALIDATED_BY_CE.getKey(),null,null,null,null,userId,new Date(),null,null,null,null,null,null,null, region);
			setErrorMessage("DONE:- Successfully Posted for CE");
			
		}else if(estimateNo != null && getSessionKey("userRole").equalsIgnoreCase("DGM")){
			
			pcesthttEjb.updateEstimateDetails(estimateNo,costCenterNo,new Long(EstimateStatus.EST_APPROVED),null,null,null,null,null,null,userId,new Date(),null);
			//pcesthttEjb.changeStatusPcesthtt(estimateNo,costCenterNo,new BigDecimal(EstimateStatus.EST_APPROVED));
			setErrorMessage("DONE:- Successfully Approved By DGM");
		}
		
		
		//if(status){
			setMessageType(MSG_DONE);
			
		//}
		loadEstimateNos();
		return SUCCESS;
	}
	public String reject()
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		String region= (String) request.getSession().getAttribute("region");
		String costCenterNo=(String) request.getSession().getAttribute("costCenterNo");
		String userId= (String) request.getSession().getAttribute("userName");
		PcesthttEjb pcesthttEjb = new PcesthttEjb(region);
		ApproveDetails detail = new ApproveDetails();
		String estimateNo= getEstimateNo();
		boolean status = false;
		if(estimateNo != null && getSessionKey("userRole").equalsIgnoreCase("EE")){
		
			
			//pcesthttEjb.changeStatusPcesthtt(estimateNo,costCenterNo,new BigDecimal(EstimateStatus.EST_REJECTED));
			pcesthttEjb.updateEstimateDetails(estimateNo,costCenterNo,new Long(EstimateStatus.EST_REJECTED),userId,new Date(),null,null,null,null,null,null,null);
			setErrorMessage("DONE:- Rejected By EE");
		}else if(estimateNo != null && getSessionKey("userRole").equalsIgnoreCase("CE")){
		
			//pcesthttEjb.changeStatusPcesthtt(estimateNo,costCenterNo,new BigDecimal(EstimateStatus.EST_REJECTED));
			pcesthttEjb.updateEstimateDetails(estimateNo,costCenterNo,new Long(EstimateStatus.EST_REJECTED),userId,new Date(),null,null,null,null,null,null,null);
			setErrorMessage("DONE:- Rejected By CE");
		}else if(estimateNo != null && getSessionKey("userRole").equalsIgnoreCase("PE")){
		
			pcesthttEjb.changeStatusPcesthtt(estimateNo,costCenterNo,new BigDecimal(EstimateStatus.EST_REJECTED));
			
			setErrorMessage("DONE:- Rejected By PE");
		}else if(estimateNo != null && getSessionKey("userRole").equalsIgnoreCase("DGM")){
			
			//pcesthttEjb.changeStatusPcesthtt(estimateNo,costCenterNo,new BigDecimal(EstimateStatus.EST_REJECTED));
			pcesthttEjb.updateEstimateDetails(estimateNo,costCenterNo,new Long(EstimateStatus.EST_REJECTED),userId,new Date(),null,null,null,null,null,null,null);
			setErrorMessage("DONE:- Rejected By DGM");
		}
		
		
		//if(status){
			setMessageType(MSG_DONE);
			
		//}
		loadEstimateNos();
		return SUCCESS;
	}
	*/
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
	public void setNewEntryFromMainMenu(){
		
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
	
}
