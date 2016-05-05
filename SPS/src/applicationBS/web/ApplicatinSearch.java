package applicationBS.web;

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

import masters.service.ProvinceDetailsMasterEjb;

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
import applicationBS.service.ApplicationTransactionEjb;
import applicationPrint.Format;

import com.opensymphony.xwork2.ActionSupport;

import estimate.model.EstimateReference;
import estimate.model.EstimateReferencePK;
import estimate.model.MaterialGrid;
import estimate.model.Pcesthtt;
import estimate.model.PcesthttPK;
import estimate.model.Spcndctm;
import estimate.model.SpcndctmPK;
import estimate.model.Spestmtm;
import estimate.service.EstimateReferenceEjb;
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
	private String fileRef;

	public String execute(){
		setLblError(null);

		setLoggedInUserId(getSession().get("userName").toString());
		setPath(viewPath);

		setCostCenter(getSessionKey("costCenterNo"));
		Date dt = new Date();
		Calendar now = Calendar.getInstance();
		 now.add(Calendar.YEAR , -2);
		
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
			 if (tempSMCType.equals("BS"))
				 appName = appName + "BS/" + format.getYear().substring(2, 4)  + "/";
			 if (tempSMCType.equals("RE"))
				 appName = appName + "RE/" +  format.getYear().substring(2, 4)+ "/";
			 if (tempSMCType.equals("RI"))
				 appName = appName + "RE/" +  format.getYear().substring(2, 4)+ "/";
			 if (tempSMCType.equals("LN"))
				 appName = appName + "LN/" +  format.getYear().substring(2, 4) + "/";
			 if (tempSMCType.equals("PL"))
				 appName = appName + "PL/" +  format.getYear().substring(2, 4) + "/";
			 if (tempSMCType.equals("EM"))
				 appName = appName + "EM/" +  format.getYear().substring(2, 4) + "/";
			 if (tempSMCType.equals("DM"))
				 appName = appName + "DM/" +  format.getYear().substring(2, 4) + "/";
			 if (tempSMCType.equals("MU"))
				 appName = appName + "MU/" +  format.getYear().substring(2, 4) + "/";
//			 if (tempSMCType.equals("NC"))
//				 appName = appName + "ENC/" +  Calendar.YEAR + "/";
		}
		
		
		if (jobNumber == null || jobNumber.isEmpty())
		{
			 Format format = new Format();
			 //541.10/SMC/11/1501  
			 
			 jobNumber = getCostCenter() + "/" ;
			 String tempSMCType = getSessionKey("smcType");
			 if (tempSMCType.equals("BS"))
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
			 if (tempSMCType.equals("BS"))
				 appID = appID + "ABS/" + format.getYear().substring(2, 4)   + "/";
			 if (tempSMCType.equals("RD"))
				 appID = appID + "ARD/" + format.getYear().substring(2, 4)   + "/";
			 if (tempSMCType.equals("RE"))
				 appID = appID + "ARE/" +  format.getYear().substring(2, 4) + "/";
			 if (tempSMCType.equals("PL"))
				 appID = appID + "APL/" +  format.getYear().substring(2, 4)  + "/";
			 if (tempSMCType.equals("LN"))
				 appID = appID + "ALN/" +  format.getYear().substring(2, 4)  + "/";
 
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
		idNo =getNic().trim();
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
	
		if (firstName.equals(""))
			firstName = null;
		if (lastName .equals(""))
			lastName = null;
		if (suburb .equals(""))
			suburb = null;
		if (city .equals(""))
			city = null;

		if (jobNumber != null && jobNumber.equals(""))		
			jobNumber = null;
		if (jobNumber != null && !jobNumber.equals("") && jobNumber.length() == 7)		
			jobNumber = null;
		
		if (appID.equals(""))		
			appID = null;
		if (appID.length()!= 20 )		
			appID = null;
		List<Application> aList = null;
		ApplicationSearchResult appSearchRslt  = null;
		applicationList = new ArrayList<ApplicationSearchResult>();
		if(jobNumber != null){
			
			EstimateReferenceEjb estiRefEjb = new EstimateReferenceEjb();
			EstimateReference estiRef = estiRefEjb.findByWorkEstimateNo(jobNumber, deptId, getSessionKey("region"));
			if(estiRef != null){
				ApplicationReferenceEjb appRefEjb = new ApplicationReferenceEjb(getSessionKey("region"));
				ApplicationReference appref = appRefEjb.findByApplicationNo(estiRef.getId().getStandardEstimateNo());
				String costCenter = deptId.substring(0, 3);
				String commCost = costCenter.concat(".00");
				System.out.println("test :5" + deptId);
				System.out.println("test :5" + fileRef);
				System.out.println("test :5" + fromDate);
				System.out.println("test :5" + toDate);
				System.out.println("test :5" + jobNumber);
				if(appref != null){
					
					aList = ejb.getSearchResults(deptId, idNo, appref.getId().getApplicationId(), appref.getApplicationNo(), firstName, lastName, suburb, city, fromDate, toDate ,jobNumber);
				}else {
					aList = ejb.getSearchResults(deptId, idNo, appref.getId().getApplicationId(), appref.getApplicationNo(), firstName, lastName, suburb, city, fromDate, toDate,jobNumber );
				}
			}
		}else{
			/*String costCenterLastdigit = deptId.substring(4, 6);
			String costCenterFirstDigit = deptId.substring(4, 6);*/
			System.out.println("test :2" + deptId);
			System.out.println("test :3" + fileRef);
			System.out.println("test :3" + fromDate);
			System.out.println("test :3" + toDate);
			String costCenterNumber = deptId;
	// 	List<Application> aList = ejb.getApplicationList(deptId,"M");
	
		/*	if(applicationNo != null && (deptId.contains(".20") || deptId.contains(".30"))){
				costCenterNumber = deptId.substring(0, 3).concat(".00");
				if(appplicationId != null && appplicationId.equalsIgnoreCase(deptId+"/")){
					appplicationId = costCenterNumber;
				}
			}*/
			ProvinceDetailsMasterEjb promasEjb = new ProvinceDetailsMasterEjb();
			String costCenterNoCommercial = null;
			try {
				costCenterNoCommercial = promasEjb.getCommercialId(deptId,  getSessionKey("region"));
			} catch (Exception e) {
				return ERROR;
			}
			if(costCenterNoCommercial != null){
				System.out.println("test :costCenterNoCommercial" + costCenterNoCommercial);
				aList = ejb.getSearchResultsSet(costCenterNoCommercial, idNo, appplicationId, applicationNo, firstName, lastName, suburb, city, fromDate, toDate,fileRef );
				
			}else{
				System.out.println("test :deptId" + deptId);
				
			    aList = ejb.getSearchResultsSet(deptId, idNo, appplicationId, applicationNo, firstName, lastName, suburb, city, fromDate, toDate,fileRef );
			}
		
			System.out.println("test :5" + aList);
		}
		if(aList != null){
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
	
				/*EstimateReferencePK estRefPK = new EstimateReferencePK();
				
				EstimateReferenceEjb estRefejb = new EstimateReferenceEjb();
				List<EstimateReference> refer = estRefejb.findByStdEstimateNo(appRef.getApplicationNo(), getSessionKey("region"));
				if (!(refer == null))
				{
					for(EstimateReference estimate : refer){
						if (estimate.getWorkEstimateNo()!= null)
						{
							// go to pcesthtt
							PcesthttEjb pcesthttEjb = new PcesthttEjb(getSessionKey("region"));
							PcesthttPK pcesthttPK = new PcesthttPK();
							pcesthttPK.setDeptId(application.getApplicationNo());
							pcesthttPK.setEstimateNo(estimate.getWorkEstimateNo());
							pcesthttPK.setRevNo(2);
		
							
							Pcesthtt pcesthtt = null;
							long revNo = 1L;
							do{
								pcesthttPK.setRevNo(revNo++);
		
								pcesthtt = pcesthttEjb.findById(pcesthttPK) ;
								
							}while(pcesthtt == null);
							
							if (!(pcesthtt == null))
							{
								EstimateStatus estStatus = new EstimateStatus();
								appSearchRslt.setEstimateStatus(estStatus.searchStatus( Integer.parseInt(pcesthtt.getStatus().toString())));
							}
							
						}else
						{
							
							
							PcesthmtEjb pcesthmtEjb = new PcesthmtEjb(getSessionKey("region"));
		
							PcesthmtPK pcesthmtPK = new PcesthmtPK();
							pcesthmtPK.setDeptId(application.getId().getDeptId());
							pcesthmtPK.setEstimateNo(application.getApplicationNo());
							pcesthmtPK.setRevNo(2);
							long revNo = 1L;
							Pcesthmt pcesthmt = null;
							do{
								pcesthmtPK.setRevNo(revNo++);
		
								pcesthmt = pcesthmtEjb.findById(pcesthmtPK) ;
								
							}while(pcesthmt == null);
							
							if (!(pcesthmt == null))
							{
								EstimateStatus estStatus = new EstimateStatus();
								appSearchRslt.setEstimateStatus(estStatus.searchStatus( Integer.parseInt(pcesthmt.getStatus().toString())));
							}
						}
						
					}
				}*/
	
	
				applicationList.add(appSearchRslt);
			}
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
		return nic;
	}

	public void setNic(String nic) {
		this.nic = nic;
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
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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
		return suberb;
	}

	public void setSuberb(String suberb) {
		this.suberb = suberb;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}


	public String getJobNumber() {
		return jobNumber;
	}


	public void setJobNumber(String jobNumber) {
		this.jobNumber = jobNumber;
	}


	public String getFileRef() {
		return fileRef;
	}


	public void setFileRef(String fileRef) {
		this.fileRef = fileRef;
	}

	 



}
