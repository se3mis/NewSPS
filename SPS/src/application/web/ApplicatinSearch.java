package application.web;

import java.security.Timestamp;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import job.model.Pcesthmt;
import job.model.PcesthmtPK;
import job.service.PcesthmtEjb;

import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.SessionAware;

import util.common.EstimateStatus;

import application.model.Applicant;
import application.model.Application;
import application.model.ApplicationPK;
import application.model.ApplicationReference;
import application.model.ApplicationReferencePK;
import application.service.ApplicantEjb;
import application.service.ApplicationEjb;
import application.service.ApplicationReferenceEjb;
import applicationPrint.Format;

import com.opensymphony.xwork2.ActionSupport;

import estimate.model.MaterialGrid;
import estimate.model.Pcesthtt;
import estimate.model.PcesthttPK;
import estimate.model.Spcndctm;
import estimate.model.SpcndctmPK;
import estimate.model.Spestmtm;
import estimate.service.PcesthttEjb;
import estimate.service.SpcndctmEjb;

public class ApplicatinSearch extends ActionSupport implements SessionAware, ParameterAware  {
	private static final long serialVersionUID = 1L;
	private Map<String, Object> session;
	private Map <String, String[]> parameters;
	private String loggedInUserId;
	private static final String viewPath="Application>Search";
	private String path;
	private String lblError = null;
	private String lblSuccess = null;

	private String nic;


	private String appID;
	private String appName;
	private Date fromDate;
	private Date toDate;
	private String firstName;
	private String lastName;
	private String suberb;
	private String city;
	private String costCenter;
	private List<ApplicationSearchResult> applicationList;	
	private String jobNumber;
	 

	public String execute(){
		setLblError(null);

		setLoggedInUserId(getSession().get("userName").toString());
		setPath(viewPath);

		setCostCenter(getSessionKey("costCenterNo"));
		Date dt = new Date();
		Calendar now = Calendar.getInstance();
		 now.add(Calendar.YEAR , -5);
		
		setFromDate (now.getTime());
		setToDate(dt);
		setAppName();

		return SUCCESS;
	}

	
	private void setAppName()
	{
		if (appName == null || appName.isEmpty())
		{
			 Format format = new Format();
			 
			 
			appName = getCostCenter() + "/" ;
			 String tempSMCType = getSessionKey("smcType");
			 if (tempSMCType.equals("NC"))
				 appName = appName + "ENC/" + format.getYear()  + "/";
			 if (tempSMCType.equals("TC"))
				 appName = appName + "ETC/" +  format.getYear()+ "/";
			 if (tempSMCType.equals("CR"))
				 appName = appName + "ECR/" +  format.getYear() + "/";
//			 if (tempSMCType.equals("NC"))
//				 appName = appName + "ENC/" +  Calendar.YEAR + "/";
		}
		
		
		if (jobNumber == null || jobNumber.isEmpty())
		{
			 Format format = new Format();
			 //541.10/SMC/11/1501  
			 
			 jobNumber = getCostCenter() + "/" ;
			 String tempSMCType = getSessionKey("smcType");
			 if (tempSMCType.equals("NC"))
				 jobNumber = jobNumber + "SMC/" + format.getYear().substring(2, 4)  + "/";
			 if (tempSMCType.equals("TC"))
				 jobNumber = jobNumber + "CRJ/" +  format.getYear().substring(2, 4)+ "/";
			 if (tempSMCType.equals("CR"))
				 jobNumber = jobNumber + "TMP/" +  format.getYear().substring(2, 4) + "/";
//			 if (tempSMCType.equals("NC"))
//				 appName = appName + "ENC/" +  Calendar.YEAR + "/";
		}
		
		if (appID == null || appID.isEmpty())
		{
			 Format format = new Format();
			 // 423.50/ANC/2011/0700
			 
			 appID = getCostCenter() + "/" ;
			 String tempSMCType = getSessionKey("smcType");
			 if (tempSMCType.equals("NC"))
				 appID = appID + "ANC/" + format.getYear()   + "/";
			 if (tempSMCType.equals("TC"))
				 appID = appID + "ATC/" +  format.getYear() + "/";
			 if (tempSMCType.equals("CR"))
				 appID = appID + "ACR/" +  format.getYear()  + "/";
 
		}
	}
	public String Close(){
		return "close";
	}


	public String Search(){

		setCostCenter(getSessionKey("costCenterNo"));
		ApplicationEjb ejb = new ApplicationEjb(getSessionKey("region"));	

		// implement a new method to get search resluts...
		String deptId, idNo, appplicationId, applicationNo, firstName, lastName, suburb, city;
		Date fromDate, toDate;
		deptId = getCostCenter().trim();
		idNo =getNic().trim().toLowerCase();
		appplicationId =  getAppID().trim();
		applicationNo = getAppName().trim();
		firstName = getFirstName().trim();
		lastName = getLastName().trim();
		suburb = getSuberb().trim();
		city = getCity().trim();
		fromDate = getFromDate() ;
		toDate = getToDate();

		
		
		if (deptId.equals(""))
			deptId = null;
		if (idNo.equals(""))
			idNo = null;
		if (appplicationId.equals(""))
			appplicationId = null;
		if (applicationNo.equals(""))		
			applicationNo = null;
		if (applicationNo.length()!= 20 )		
			applicationNo = null;
		
		if (firstName.equals(""))
			firstName = null;
		if (lastName .equals(""))
			lastName = null;
		if (suburb .equals(""))
			suburb = null;
		if (city .equals(""))
			city = null;

		if (jobNumber.equals(""))		
			jobNumber = null;
		if (jobNumber.length()!= 18 )		
			jobNumber = null;
		
		if (appID.equals(""))		
			appID = null;
		if (appID.length()!= 20 )		
			appID = null;



	// 	List<Application> aList = ejb.getApplicationList(deptId,"M");
		List<Application> aList = ejb.getSearchResults(deptId, idNo, appplicationId, applicationNo, firstName, lastName, suburb, city, fromDate, toDate, city );
		ApplicationSearchResult appSearchRslt  = null;
		applicationList = new ArrayList<ApplicationSearchResult>();


		for (Application application : aList) {	
			appSearchRslt = null;
			appSearchRslt = new ApplicationSearchResult();
			appSearchRslt.setApplicationNumber(application.getApplicationNo());
			appSearchRslt.setApplicationID(application.getId().getApplicationId());
			appSearchRslt.setApplicationType(application.getApplicationType());
			appSearchRslt.setSubmitedDate(application.getSubmitDate());
			appSearchRslt.setApplicationStatus(application.getStatus());
			appSearchRslt.setNic (application.getIdNo());

			ApplicantEjb applicantEjb = new ApplicantEjb(getSessionKey("region"));

			Applicant applicant =  applicantEjb.findById(application.getIdNo());
			appSearchRslt.setName(applicant.getFirstName() + " " + applicant.getLastName());
			appSearchRslt.setAddress(applicant.getStreetAddress() + " " + applicant.getSuburb() + " " + applicant.getCity());

			ApplicationReferencePK appRefID = new ApplicationReferencePK();
			appRefID.setApplicationId(application.getId().getApplicationId());
			appRefID.setDeptId(application.getId().getDeptId());
			ApplicationReferenceEjb appRefEjb = new ApplicationReferenceEjb(getSessionKey("region"));
			ApplicationReference appRef = appRefEjb.findByAppId(appRefID);

			if (!(appRef == null))
			{
				if (appRef.getProjectno()!= null)
				{
					// go to pcesthmt
					PcesthmtEjb pcesthmtEjb = new PcesthmtEjb(getSessionKey("region"));

					PcesthmtPK pcesthmtPK = new PcesthmtPK();
					pcesthmtPK.setDeptId(application.getId().getDeptId());
					pcesthmtPK.setEstimateNo(application.getApplicationNo());
					pcesthmtPK.setRevNo(2);


					Pcesthmt pcesthmt = pcesthmtEjb.findById(pcesthmtPK) ;
					if (!(pcesthmt == null))
					{
						EstimateStatus estStatus = new EstimateStatus();
						appSearchRslt.setEstimateStatus(estStatus.searchStatus( Integer.parseInt(pcesthmt.getStatus().toString())));
					}
				}else
				{
					// go to pcesthtt
					PcesthttEjb pcesthttEjb = new PcesthttEjb(getSessionKey("region"));
					PcesthttPK pcesthttPK = new PcesthttPK();
					pcesthttPK.setDeptId(application.getId().getDeptId());
					pcesthttPK.setEstimateNo(application.getApplicationNo());
					pcesthttPK.setRevNo(2);

					Pcesthtt pcesthtt = pcesthttEjb.findById(application.getApplicationNo(), deptId,  (long) 2) ;
					if (!(pcesthtt == null))
					{
						EstimateStatus estStatus = new EstimateStatus();
						appSearchRslt.setEstimateStatus(estStatus.searchStatus( Integer.parseInt(pcesthtt.getStatus().toString())));
					}
				}
			}


			applicationList.add(appSearchRslt);
		}


		//applicationList = null; //ejb.getAll("510.20");
		execute();
		return SUCCESS;
	}

	public String getSessionKey(String key) {
		return getSession().get(key).toString();
	}
	@Override
	public void setParameters(Map<String, String[]> parameters){
		// TODO Auto-generated method stub
		this.parameters=parameters;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		// TODO Auto-generated method stub
		this.session=session;
	}

	public String getCostCenter() {
		return costCenter;
	}

	public void setCostCenter(String costCenter) {
		this.costCenter = costCenter;
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

	public String getNic() {
		return nic.toUpperCase();
	}

	public void setNic(String nic) {
		this.nic = nic.toUpperCase();
	}

	public String getAppID() {
		return appID.toUpperCase();
	}

	public void setAppID(String appID) {
		this.appID = appID.toUpperCase();
	}

	public String getAppName() {
		return appName.toUpperCase();
	}

	public void setAppName(String appName) {
		this.appName = appName.toUpperCase();
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public String getFirstName() {
		return firstName.toUpperCase();
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName.toUpperCase();
	}

	public String getLastName() {
		return lastName.toUpperCase();
	}

	public void setLastName(String lastName) {
		this.lastName = lastName.toUpperCase();
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public Map<String, String[]> getParameters() {
		return parameters;
	}

	public static String getViewpath() {
		return viewPath;
	}

	public List<ApplicationSearchResult> getApplicationList() {
		return applicationList;
	}

	public void setApplicationList(List<ApplicationSearchResult> applicationList) {
		this.applicationList = applicationList;
	}

	public String getSuberb() {
		return suberb.toUpperCase();
	}

	public void setSuberb(String suberb) {
		this.suberb = suberb.toUpperCase();
	}

	public String getCity() {
		return city.toUpperCase();
	}

	public void setCity(String city) {
		this.city = city.toUpperCase();
	}


	public String getJobNumber() {
		return jobNumber;
	}


	public void setJobNumber(String jobNumber) {
		this.jobNumber = jobNumber;
	}

	 



}
