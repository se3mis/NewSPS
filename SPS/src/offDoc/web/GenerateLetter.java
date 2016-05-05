package offDoc.web;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import job.model.JobInfo;
import job.model.Spestcnt;
import job.model.SpestcntPK;
import job.service.SpestcntEjb;

import masters.service.ProvinceDetailsMasterEjb;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;
import offDoc.model.LetterReason;
import offDoc.model.LetterReasonPK;
import offDoc.service.OffDocsEjb;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.SessionAware;

import reports.web.report;
import security.service.SecurityEjb;

 

import util.common.AppointmentStatus;
import util.common.Constants;
import util.common.Format;
import util.common.Path;
import util.common.Phase;

import application.model.Applicant;
import application.model.Application;
import application.model.WiringLandDetail;
import application.model.WiringLandDetailPK;
import application.service.ApplicantEjb;
import application.service.ApplicationEjb;
import application.service.WiringLandDetailEjb;

import com.opensymphony.xwork2.ActionSupport;

import costcenter.model.Gldeptin;
import costcenter.model.Gldeptm;
import costcenter.service.GldeptinEjb;
import costcenter.service.GldeptmEjb;

import estimate.model.EstimateReference;
import estimate.model.Pcesthtt;
import estimate.service.PcesthttEjb;
import estimate.service.SpestedyConEjb;

@SuppressWarnings("serial")
public class GenerateLetter extends ActionSupport implements SessionAware, ParameterAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map<String, Object> session;
	private Map <String, String[]> parameters;
	private String loggedInUserId;
	private static final String viewPath="";
	private String path;
	private String lblError = null;
	private String lblSuccess = null;

	private String costCenter;
	private String costCenterName;
	private String costCenterAddress;
	private String costCenterArea;
	private List<String> esimateNumberList = new  ArrayList<String>();;
	private String selectedEstNumber;
	private String selectedConstructionEstNumber;
	private String loggedInUserLevel;
	private String customerName;
	private String customerAddress;
	private String elecSupName;
	private String contractor;
	private String suberb;
	private String city;
	private String postalCode;
	private String phase;
	private String appDate;
	private Date visitedDate;
	

	private String[] selectedReasons;
	private String[] selectedReasonsID;
	private InputStream fileInputStream;
	private InputStream fileInputStream1;
	private List<String> listContractor;
	private List<String> listContractorID;
	private List<EstimateReference> listApplicationNumber;
	private String district;
	private String jobNumber;
	private String fundSource;
	private String workScope;
	private String construcRef;
	private String allocatedId;
	private String reason;
	
	private String expectedDateofFinish;
	
	
	
	private String branchType;
	public String execute(){
		setLblError(null);

		setLoggedInUserId(getSession().get("userName").toString());
		setPath(viewPath);
		if (this.visitedDate == null)
			this.visitedDate = new Date();
		
		setCostCenterDetials();
		
		setApplicationList();
		//setApplicationDetails();
		
		
		setLoggedInUserLevel( getSessionKey("userRole"));
		
		SpestcntEjb ejb=new SpestcntEjb(getSessionKey("region"));	
		listContractor= new  ArrayList<String>();
		listContractorID = new  ArrayList<String>();
		listApplicationNumber = new  ArrayList<EstimateReference>();
		List<Spestcnt> spestcntList=new ArrayList<Spestcnt>();	
		List<JobInfo> jobInfoList=new ArrayList<JobInfo>();	
		
		String costCenter = getSessionKey("costCenterNo");
		spestcntList=ejb.getContractorByStatus(costCenter, "1");
		
		Iterator<Spestcnt> it1 = spestcntList.iterator();
		
		 while (it1.hasNext()) {  
			 Spestcnt spestcnt1=it1.next();
			 listContractor.add(spestcnt1.getContractorName());	
			 listContractorID.add(spestcnt1.getId().getContractorId());	
			 				        	        	
	       }  
		 
		 
		return SUCCESS;
	}

	private void setCostCenterDetials()
	{
		GldeptinEjb ejb = new GldeptinEjb(getSessionKey("region"));
		Gldeptin gldeptin =  ejb.findById(getSessionKey("costCenterNo"));
		if(gldeptin != null){
		
			setCostCenterName(gldeptin.getDeptFullName());
			setCostCenterAddress(gldeptin.getDeptAdd()  ); //   gldeptm.getAddress()
			setCostCenterArea(gldeptin.getDeptArea());
		}
		setCostCenter(getSessionKey("costCenterNo"));
		ProvinceDetailsMasterEjb promasEjb = new ProvinceDetailsMasterEjb();
		   String branchType = promasEjb.getBranchType(getSessionKey("costCenterNo"), getSessionKey("region"));
		   if(branchType != null){
			  setBranchType(branchType);
		   }
	}
	
	public String getSessionKey(String key) {
		return getSession().get(key).toString();
	}
	private void setApplicationList()
	{
		
		this.esimateNumberList = new  ArrayList<String>();
		List<String> estimationRefNos = null;
		SpestedyConEjb estimateEjb = new SpestedyConEjb(getSessionKey("region"));
		//estimationRefNos = estimateEjb.loadApplicationRefnos(null,getSessionKey("costCenterNo"),AppointmentStatus.ACTIVE); 
		estimationRefNos = estimateEjb.loadApplicationRefnos(null,getSessionKey("costCenterNo"),null); 
		
		//ApplicationEjb ejb=new ApplicationEjb(getSessionKey("region"));		 
		//List<Application> lst = ejb.getApplicationList(getCostCenter(), "A"); // .ge .getEstApprovalList(getCostCenter(), status:=A );
		//this.esimateNumberList.add("Select Estimate");
		if(estimationRefNos != null && estimationRefNos.size() > 0){
			//for (int i=0; i<=estimationRefNos.size()-1;i++) {
	
				this.esimateNumberList.addAll(estimationRefNos);
			//} 
		}
	}
	
	
	public String Exit(){
		return "closed";
	}
	
	/*public String Print()
	{
	 
		if (this.selectedReasonsID == null)
		{
			
			 execute();
			 setLblError("Please select at least one reason");
			return SUCCESS;
		}
		PDFEstimateReference print = new PDFEstimateReference(this.costCenterName,this.costCenterArea,this.costCenterAddress ,
										this.selectedAppNumber,this.customerName,this.streetAddress,this.suberb,this.city,this.postalCode,
										this.phase,this.appDate,this.visitedDate,this.selectedSession,this.selectedReasonsID);
		print.PrintPDF();
		 execute();
		return SUCCESS;
	}*/
	
	/*public String GenerateLetter() throws FileNotFoundException
	{
	 
		if (this.selectedReasonsID == null)
		{
			
			 execute();
			 setLblError("Please select at least one reason");
			return SUCCESS;
		}
		PDFEstimateReference print = new PDFEstimateReference(this.costCenterName,this.costCenterArea,this.costCenterAddress ,
										this.selectedAppNumber,this.customerName,this.streetAddress,this.suberb,this.city,this.postalCode,
										this.phase,this.appDate,this.visitedDate,this.selectedSession,this.selectedReasonsID);
		
		String fileName = print.PrintPDF();	
		fileInputStream = new FileInputStream(fileName);
		return "successprint";
		 
	}*/
	
	

	/*private void setApplicationDetails()
	{


		if (this.selectedAppNumber ==null || this.selectedAppNumber.equals("Select Estimate"))
		{
			setLblError("Select Estimate");
			return;
		}else
		{
			ApplicationEjb applEjb = new ApplicationEjb(getSessionKey("region"));
			Application application = null ;
			try
			{
				application = applEjb.findByApplicationNo(this.selectedAppNumber);
			}catch(Exception e1)
			{
				setLblError("Application details not found");
				//setLblError(e1.toString());
				return;
			}
			
			ApplicantEjb applicantEjb = new ApplicantEjb(getSessionKey("region"));
			Applicant applicant = null;
			try
			{
				applicant = applicantEjb.findById(application.getIdNo());
			}catch(Exception e2)
			{
				setLblError("Applicant details not found");
				return;
			}
			
			try
			{
				String tempName = applicant.getFirstName() +  " " + applicant.getLastName();
				setCustomerName(tempName);
			}catch(Exception e3)
			{
				setLblError("Error in applicant name");
				return;
			}

			try
			{
				 String tempCusAddress = "";
				setStreetAddress(applicant.getStreetAddress());
				tempCusAddress += applicant.getStreetAddress();
				setSuberb(applicant.getSuburb());
				tempCusAddress += ", " + applicant.getSuburb();
				if (applicant.getCity() != null)
				{
					setCity( applicant.getCity());
					tempCusAddress += ", " + applicant.getCity();
				}
				else
					setCity("");
				 
				if (applicant.getPostalCode() != null)
				{
					setPostalCode( applicant.getPostalCode().toString());
					tempCusAddress += ", " +  applicant.getPostalCode().toString();
				}
				else
					setPostalCode( "");
				
				setCustomerAddress(tempCusAddress  );
				 
			}catch(Exception e4)
			{
				setLblError("Error in applicant address");
			}
			
			WiringLandDetailEjb wlEjb = new WiringLandDetailEjb(getSessionKey("region"));
			WiringLandDetailPK id = new WiringLandDetailPK();
			id.setApplicationId(application.getId().getApplicationId());
			id.setDeptId(costCenter);
			WiringLandDetail wlDetail =  wlEjb.findByAppId(id);
			BigDecimal phse = wlDetail.getPhase();
			BigDecimal big1 = new  BigDecimal("1");
			BigDecimal big2 = new  BigDecimal("2");
			BigDecimal big3= new  BigDecimal("3");
			 
			String conType =  wlDetail.getConnectionType().toString();
			if (phse.equals(big1))
				setPhase("Single Phase " + conType + " (A)" );
			if (phse.equals(big2))
				setPhase("Dual Phase " + conType + " (A)");
			if (phse.equals(big3))
				setPhase("Three Phase " + conType + " (A)");
			
			 
			

			Format ft = new Format();
			setAppDate(ft.formatDate(application.getSubmitDate()));
			 
		}

	}*/








	public String getContractor() {
		return contractor;
	}

	public String getAllocatedId() {
		return allocatedId;
	}

	public void setAllocatedId(String allocatedId) {
		this.allocatedId = allocatedId;
	}

	public void setContractor(String contractor) {
		this.contractor = contractor;
	}

	public String getElecSupName() {
		return elecSupName;
	}

	public void setElecSupName(String elecSupName) {
		this.elecSupName = elecSupName;
	}

	public List<String> getListContractor() {
		return listContractor;
	}

	public void setListContractor(List<String> listContractor) {
		this.listContractor = listContractor;
	}

	public List<String> getListContractorID() {
		return listContractorID;
	}

	public void setListContractorID(List<String> listContractorID) {
		this.listContractorID = listContractorID;
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

	public String getLoggedInUserId() {
		return loggedInUserId;
	}

	public void setLoggedInUserId(String loggedInUserId) {
		this.loggedInUserId = loggedInUserId;
	}

	public String getWorkScope() {
		return workScope;
	}

	public void setWorkScope(String workScope) {
		this.workScope = workScope;
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
		if (lblError == null )
		{

			this.lblError = lblError;
		}
		else
		{
			if (lblError == "Select Estimate")
				this.lblError = null;
			else
				this.lblError = lblError;			  


		}
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

	public static String getViewpath() {
		return viewPath;
	}

	public String getCostCenter() {
		return costCenter;
	}

	public void setCostCenter(String costCenter) {
		this.costCenter = costCenter;
	}

	public String getCostCenterName() {
		return costCenterName;
	}

	public void setCostCenterName(String costCenterName) {
		this.costCenterName = costCenterName;
	}

	public String getCostCenterAddress() {
		return costCenterAddress;
	}

	public void setCostCenterAddress(String costCenterAddress) {
		this.costCenterAddress = costCenterAddress;
	}



	public String getLoggedInUserLevel() {
		return loggedInUserLevel;
	}

	public void setLoggedInUserLevel(String loggedInUserLevel) {
		this.loggedInUserLevel = loggedInUserLevel;
	}

	

	public List<String> getEsimateNumberList() {
		return esimateNumberList;
	}

	public void setEsimateNumberList(List<String> esimateNumberList) {
		this.esimateNumberList = esimateNumberList;
	}

	public String getSelectedEstNumber() {
		return selectedEstNumber;
	}

	public void setSelectedEstNumber(String selectedEstNumber) {
		this.selectedEstNumber = selectedEstNumber;
	}

	public String getConstrucRef() {
		return construcRef;
	}

	public void setConstrucRef(String construcRef) {
		this.construcRef = construcRef;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	 

	public String getPhase() {
		return phase;
	}

	public void setPhase(String phase) {
		this.phase = phase;
	}

	public String getAppDate() {
		return appDate;
	}

	public void setAppDate(String appDate) {
		this.appDate = appDate;
	}

	public Date getVisitedDate() {
		return visitedDate;
	}

	public void setVisitedDate(Date visitedDate) {
		this.visitedDate = visitedDate;
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

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

 

 

	public String getFundSource() {
		return fundSource;
	}

	public void setFundSource(String fundSource) {
		this.fundSource = fundSource;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	

	public String getJobNumber() {
		return jobNumber;
	}

	public void setJobNumber(String jobNumber) {
		this.jobNumber = jobNumber;
	}

	public String[] getSelectedReasons() {
		return selectedReasons;
	}

	public void setSelectedReasons(String[] selectedReasons) {
		this.selectedReasons = selectedReasons;
	}

	public String[] getSelectedReasonsID() {
		return selectedReasonsID;
	}

	public void setSelectedReasonsID(String[] selectedReasonsID) {
		this.selectedReasonsID = selectedReasonsID;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	

	public String getSelectedConstructionEstNumber() {
		return selectedConstructionEstNumber;
	}

	public void setSelectedConstructionEstNumber(
			String selectedConstructionEstNumber) {
		this.selectedConstructionEstNumber = selectedConstructionEstNumber;
	}

	public String getCostCenterArea() {
		return costCenterArea;
	}

	public void setCostCenterArea(String costCenterArea) {
		this.costCenterArea = costCenterArea;
	}

	public InputStream getFileInputStream() {
		return fileInputStream;
	}

	public void setFileInputStream(InputStream fileInputStream) {
		this.fileInputStream = fileInputStream;
	}

	public InputStream getFileInputStream1() {
		return fileInputStream1;
	}

	public void setFileInputStream1(InputStream fileInputStream1) {
		this.fileInputStream1 = fileInputStream1;
	}

	public String getExpectedDateofFinish() {
		return expectedDateofFinish;
	}

	public void setExpectedDateofFinish(String expectedDateofFinish) {
		this.expectedDateofFinish = expectedDateofFinish;
	}

	public String getBranchType() {
		return branchType;
	}

	public void setBranchType(String branchType) {
		this.branchType = branchType;
	}
	
	
	
	public String GenerateContracOfferLetterConitSelf(){
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
	 

		System.out.println("Application Number :  " + getSelectedConstructionEstNumber());
		System.out.println("CC Number :  " + costCenterNo);
		param.put("@appNo", "'"+getSelectedConstructionEstNumber()+"'");
		param.put("@costctr","'"+costCenterNo+"'");
		
		param.put("@finishedDate","'"+getExpectedDateofFinish()+"'");
		//param.put("@eslogin","'"+getAllocatedId()+"'");
		
		

		String fileName = null;
		
			fileName = rept.generateReport("Contractor_Offer_Of_A_Construction_JobUva",param,getSession(),REPORT_DIRECTORY,EXPORT_REPORT_DIRECTORY);
		
		
	
		System.out.println("xxxxxxxxx"+ fileName);
		
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
		return "successprint";


	}

	public String GenerateContracOfferLetter(){
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
	 

		System.out.println("Application Number :  " + getSelectedConstructionEstNumber());
		System.out.println("CC Number :  " + costCenterNo);
		param.put("@appNo", "'"+getSelectedConstructionEstNumber()+"'");
		param.put("@costctr","'"+costCenterNo+"'");
		
		param.put("@finishedDate","'"+getExpectedDateofFinish()+"'");
		//param.put("@eslogin","'"+getAllocatedId()+"'");
		
		

		String fileName = null;
		if (costCenterNo.equals("450.50")|| costCenterNo.equals("452.40")|| costCenterNo.equals("452.50")|| costCenterNo.equals("452.80")||costCenterNo.equals("452.90")||costCenterNo.equals("453.00")||costCenterNo.equals("453.10")||costCenterNo.equals("453.20")||costCenterNo.equals("453.30")||costCenterNo.equals("453.40")|| costCenterNo.equals("454.00")||costCenterNo.equals("454.10")||costCenterNo.equals("454.20")||costCenterNo.equals("454.50")||costCenterNo.equals("455.00")||costCenterNo.equals("455.10")||costCenterNo.equals("455.20")||costCenterNo.equals("455.30")||costCenterNo.equals("455.40")|| costCenterNo.equals("455.50")||costCenterNo.equals("456.00")||costCenterNo.equals("456.10")|| costCenterNo.equals("456.20")||costCenterNo.equals("456.30")||costCenterNo.equals("457.00")||costCenterNo.equals("457.10")||costCenterNo.equals("457.20")|| costCenterNo.equals("457.30")||costCenterNo.equals("450.00")|| costCenterNo.equals("450.80")||costCenterNo.equals("451.00")||costCenterNo.equals("451.10")||costCenterNo.equals("451.20")||costCenterNo.equals("451.30")||costCenterNo.equals("451.40")||costCenterNo.equals("451.50")||costCenterNo.equals("451.90") || costCenterNo.equals("452.00") || costCenterNo.equals("452.10")||costCenterNo.equals("452.20")||costCenterNo.equals("452.30")){
			fileName = rept.generateReport("Contractor_Offer_Of_A_Construction_JobDepo",param,getSession(),REPORT_DIRECTORY,EXPORT_REPORT_DIRECTORY);
			
		}else if(smcType.equalsIgnoreCase(Constants.RUTAL_ELECTRIFICATION) || smcType.equalsIgnoreCase(Constants.RUTAL_ELECTRIFICATION_I)){
			fileName = rept.generateReport("Contractor_Offer_Of_A_Construction_JobUva",param,getSession(),REPORT_DIRECTORY,EXPORT_REPORT_DIRECTORY);
		}else if(smcType.equalsIgnoreCase(Constants.JOBTYPE_CONSTRUCTION) || smcType.equalsIgnoreCase(Constants.JOBTYPE_DISTRIBUTION_AND_MAINTENANCE)){
			fileName = rept.generateReport("Contractor_Offer_Of_A_Construction_JobUvaCON",param,getSession(),REPORT_DIRECTORY,EXPORT_REPORT_DIRECTORY);
		}	
		else{
			fileName = rept.generateReport("Contractor_Offer_Of_A_Construction_JobUva",param,getSession(),REPORT_DIRECTORY,EXPORT_REPORT_DIRECTORY);
		}
		
	
		System.out.println("xxxxxxxxx"+ fileName);
		
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
		return "successprint";


	}
	
	public String GenerateContracOfferLetterWithHeader(){
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
	 

		System.out.println("Application Number :  " + getSelectedConstructionEstNumber());
		System.out.println("CC Number :  " + costCenterNo);
		param.put("@appNo", "'"+getSelectedConstructionEstNumber()+"'");
		param.put("@costctr","'"+costCenterNo+"'");
		
		param.put("@finishedDate","'"+getExpectedDateofFinish()+"'");
		//param.put("@eslogin","'"+getAllocatedId()+"'");
		
		

		String fileName = null;
		
			fileName = rept.generateReport("Contractor_Offer_Of_A_Construction_JobCONNew_F2_D",param,getSession(),REPORT_DIRECTORY,EXPORT_REPORT_DIRECTORY);
		
		
	
		System.out.println("xxxxxxxxx"+ fileName);
		
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
		return "successprint";


	}
	
	public String GenerateContracOfferLetterWithOutHeader(){
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
	 

		System.out.println("Application Number :  " + getSelectedConstructionEstNumber());
		System.out.println("CC Number :  " + costCenterNo);
		param.put("@appNo", "'"+getSelectedConstructionEstNumber()+"'");
		param.put("@costctr","'"+costCenterNo+"'");
		
		param.put("@finishedDate","'"+getExpectedDateofFinish()+"'");
		//param.put("@eslogin","'"+getAllocatedId()+"'");
		
		

		String fileName = null;
		
			fileName = rept.generateReport("Contractor_Offer_Of_A_Construction_JobCONNew_F1_D",param,getSession(),REPORT_DIRECTORY,EXPORT_REPORT_DIRECTORY);
		
		
	
		System.out.println("xxxxxxxxx"+ fileName);
		
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
		return "successprint";


	}
	
	
	public String GenerateHandingOverOfMaintenceCEBGAND(){
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
	 

		System.out.println("Application Number :  " + getSelectedConstructionEstNumber());
		System.out.println("CC Number :  " + costCenterNo);
		param.put("@appNo", "'"+getSelectedConstructionEstNumber()+"'");
		param.put("@costctr","'"+costCenterNo+"'");
		
		param.put("@finishedDate","'"+getExpectedDateofFinish()+"'");
		//param.put("@eslogin","'"+getAllocatedId()+"'");
		
		

		String fileName = null;
		if(smcType.equalsIgnoreCase(Constants.RUTAL_ELECTRIFICATION) || smcType.equalsIgnoreCase(Constants.RUTAL_ELECTRIFICATION_I)){
			fileName = rept.generateReport("Contractor_Offer_Of_A_Construction_JobUva",param,getSession(),REPORT_DIRECTORY,EXPORT_REPORT_DIRECTORY);
		}else if(smcType.equalsIgnoreCase(Constants.JOBTYPE_CONSTRUCTION) || smcType.equalsIgnoreCase(Constants.JOBTYPE_DISTRIBUTION_AND_MAINTENANCE)){
			fileName = rept.generateReport("Contractor_Offer_Of_A_Construction_JobUvaCON",param,getSession(),REPORT_DIRECTORY,EXPORT_REPORT_DIRECTORY);
		}
		else{
			fileName = rept.generateReport("Contractor_Offer_Of_A_Construction_JobUva",param,getSession(),REPORT_DIRECTORY,EXPORT_REPORT_DIRECTORY);
		}
		
	
		System.out.println("xxxxxxxxx"+ fileName);
		
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
		return "successprint";


	}
	
	public String GenerateAllocationOfJobLetter1(){
		HttpServletRequest request = ServletActionContext.getRequest();
		
		String costCenterNo=(String) request.getSession().getAttribute("costCenterNo");
		String userId= (String) request.getSession().getAttribute("userName");
		
		String region= (String) request.getSession().getAttribute("region");
		String smcType= (String) request.getSession().getAttribute("smcType");
		String branchType= (String) request.getSession().getAttribute("branchType");
		 
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
	 

		System.out.println("Application Number :  " + getSelectedEstNumber());
		System.out.println("CC Number :  " + costCenterNo);
		param.put("@appNo", "'"+getSelectedConstructionEstNumber()+"'");
		param.put("@costctr","'"+costCenterNo+"'");
		

		String fileName = rept.generateReport("allocation_Of_jobNumber_Maintenance",param,getSession(),REPORT_DIRECTORY,EXPORT_REPORT_DIRECTORY);
		
		
	
		System.out.println("xxxxxxxxx"+ fileName);
		
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
		return "successprint";


	}
	
	
	public String GenerateCompletionReport(){
		HttpServletRequest request = ServletActionContext.getRequest();
		
		String costCenterNo=(String) request.getSession().getAttribute("costCenterNo");
		String userId= (String) request.getSession().getAttribute("userName");
		
		String region= (String) request.getSession().getAttribute("region");
		String smcType= (String) request.getSession().getAttribute("smcType");
		String branchType= (String) request.getSession().getAttribute("branchType");
		 
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
	 

		System.out.println("Application Number :  " + getSelectedEstNumber());
		System.out.println("CC Number :  " + costCenterNo);
		param.put("@appNo", "'"+getSelectedConstructionEstNumber()+"'");
		param.put("@costctr","'"+costCenterNo+"'");
		

		String fileName = rept.generateReport("Energize",param,getSession(),REPORT_DIRECTORY,EXPORT_REPORT_DIRECTORY);
		
		
	
		System.out.println("xxxxxxxxx"+ fileName);
		
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
		return "successprint";


	}
	public String GenerateAllocationOfJobLetter(){
		HttpServletRequest request = ServletActionContext.getRequest();
		
		String costCenterNo=(String) request.getSession().getAttribute("costCenterNo");
		String userId= (String) request.getSession().getAttribute("userName");
		
		String region= (String) request.getSession().getAttribute("region");
		String smcType= (String) request.getSession().getAttribute("smcType");
		String branchType= (String) request.getSession().getAttribute("branchType");
		 
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
		

		String costCenterNoCommercial ="";
		HashMap<String, Object> param = new HashMap<String, Object>();
		/**if(branchType.equalsIgnoreCase(Constants.BRANCHTYPE_AREA_UNIT)){
			ProvinceDetailsMasterEjb promasEjb = new ProvinceDetailsMasterEjb();
			try {
				costCenterNoCommercial = promasEjb.getCommercialId(costCenterNo,  getSessionKey("region"));
			} catch (Exception e) {
				return ERROR;
			}
		}*/
		System.out.println("Application Number :  " + getSelectedEstNumber());
		System.out.println("CC Number :  " + costCenterNo);
		param.put("@appNo", "'"+getSelectedConstructionEstNumber()+"'");
		param.put("@costctr","'"+costCenterNo+"'");
		
		/**if(branchType.equalsIgnoreCase(Constants.BRANCHTYPE_AREA_UNIT)){
			param.put("@costctr","'"+costCenterNoCommercial+"'");
		}*/
		String fileName = null;
		
		
		
		if(costCenterNo.equals("450.50")|| costCenterNo.equals("452.40")|| costCenterNo.equals("452.50")|| costCenterNo.equals("452.80")||costCenterNo.equals("452.90")||costCenterNo.equals("453.00")||costCenterNo.equals("453.10")||costCenterNo.equals("453.20")||costCenterNo.equals("453.30")||costCenterNo.equals("453.40")|| costCenterNo.equals("454.00")||costCenterNo.equals("454.10")||costCenterNo.equals("454.20")||costCenterNo.equals("454.50")||costCenterNo.equals("455.00")||costCenterNo.equals("455.10")||costCenterNo.equals("455.20")||costCenterNo.equals("455.30")||costCenterNo.equals("455.40")|| costCenterNo.equals("455.50")||costCenterNo.equals("456.00")||costCenterNo.equals("456.10")|| costCenterNo.equals("456.20")||costCenterNo.equals("456.30")||costCenterNo.equals("457.00")||costCenterNo.equals("457.10")||costCenterNo.equals("457.20")|| costCenterNo.equals("457.30")||costCenterNo.equals("450.00")|| costCenterNo.equals("450.80")||costCenterNo.equals("451.00")||costCenterNo.equals("451.10")||costCenterNo.equals("451.20")||costCenterNo.equals("451.30")||costCenterNo.equals("451.40")||costCenterNo.equals("451.50")||costCenterNo.equals("451.90") || costCenterNo.equals("452.00") || costCenterNo.equals("452.10")||costCenterNo.equals("452.20")||costCenterNo.equals("452.30")){
			fileName = rept.generateReport("allocation_Of_jobNumber_Depo",param,getSession(),REPORT_DIRECTORY,EXPORT_REPORT_DIRECTORY);
			
		
		}
		else if(branchType != null && (branchType.equalsIgnoreCase(Constants.BRANCHTYPE_AREA_MAINTENANCE) 
				|| branchType.equalsIgnoreCase(Constants.BRANCHTYPE_AREA_UNIT) 
				|| branchType.equalsIgnoreCase(Constants.BRANCHTYPE_DISTRIBUTION_AND_MAINTENANCE) 
				|| branchType.equalsIgnoreCase(Constants.BRANCHTYPE_ENERGY_MANAGEMENT) 
				|| getSelectedConstructionEstNumber().contains(Constants.JOBTYPE_CONSTRUCTION))){
			fileName = rept.generateReport("allocation_Of_jobNumber_Maintenance",param,getSession(),REPORT_DIRECTORY,EXPORT_REPORT_DIRECTORY);
			
		}else{
			fileName = rept.generateReport("allocation_Of_jobNumber",param,getSession(),REPORT_DIRECTORY,EXPORT_REPORT_DIRECTORY);
			
		}
		
	
		System.out.println("xxxxxxxxx"+ fileName);
		
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
		return "successprint";


	}
	
	public String GenerateAllocationOfJobLetterAU(){
		HttpServletRequest request = ServletActionContext.getRequest();
		
		String costCenterNo=(String) request.getSession().getAttribute("costCenterNo");
		String userId= (String) request.getSession().getAttribute("userName");
		
		String region= (String) request.getSession().getAttribute("region");
		String smcType= (String) request.getSession().getAttribute("smcType");
		String branchType= (String) request.getSession().getAttribute("branchType");
		 
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
		

		String costCenterNoCommercial ="";
		HashMap<String, Object> param = new HashMap<String, Object>();
		if(branchType.equalsIgnoreCase(Constants.BRANCHTYPE_AREA_UNIT)){
			ProvinceDetailsMasterEjb promasEjb = new ProvinceDetailsMasterEjb();
			try {
				costCenterNoCommercial = promasEjb.getCommercialId(costCenterNo,  getSessionKey("region"));
			} catch (Exception e) {
				return ERROR;
			}
		}
		System.out.println("Application Number :  " + getSelectedEstNumber());
		System.out.println("CC Number :  " + costCenterNo);
		param.put("@appNo", "'"+getSelectedConstructionEstNumber()+"'");
		param.put("@costctr","'"+costCenterNoCommercial+"'");
		
		if(branchType.equalsIgnoreCase(Constants.BRANCHTYPE_AREA_UNIT)){
			param.put("@costctr","'"+costCenterNoCommercial+"'");
		}
		String fileName = null;
		
		
		
		
		fileName = rept.generateReport("allocation_Of_jobNumber_AU",param,getSession(),REPORT_DIRECTORY,EXPORT_REPORT_DIRECTORY);
		
	
		System.out.println("xxxxxxxxx"+ fileName);
		
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
		return "successprint";


	}
	
	
	public String GenerateAllocationOfJobLetterPlanning(){
		HttpServletRequest request = ServletActionContext.getRequest();
		
		String costCenterNo=(String) request.getSession().getAttribute("costCenterNo");
		String userId= (String) request.getSession().getAttribute("userName");
		
		String region= (String) request.getSession().getAttribute("region");
		String smcType= (String) request.getSession().getAttribute("smcType");
		String branchType= (String) request.getSession().getAttribute("branchType");
		 
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
	 

		System.out.println("Application Number :  " + getSelectedEstNumber());
		System.out.println("CC Number :  " + costCenterNo);
		param.put("@appNo", "'"+getSelectedConstructionEstNumber()+"'");
		param.put("@costctr","'"+costCenterNo+"'");
		
		String fileName = null;
		
		
		
		if(costCenterNo.equals("450.50")|| costCenterNo.equals("452.40")|| costCenterNo.equals("452.50")|| costCenterNo.equals("452.80")||costCenterNo.equals("452.90")||costCenterNo.equals("453.00")||costCenterNo.equals("453.10")||costCenterNo.equals("453.20")||costCenterNo.equals("453.30")||costCenterNo.equals("453.40")|| costCenterNo.equals("454.00")||costCenterNo.equals("454.10")||costCenterNo.equals("454.20")||costCenterNo.equals("454.50")||costCenterNo.equals("455.00")||costCenterNo.equals("455.10")||costCenterNo.equals("455.20")||costCenterNo.equals("455.30")||costCenterNo.equals("455.40")|| costCenterNo.equals("455.50")||costCenterNo.equals("456.00")||costCenterNo.equals("456.10")|| costCenterNo.equals("456.20")||costCenterNo.equals("456.30")||costCenterNo.equals("457.00")||costCenterNo.equals("457.10")||costCenterNo.equals("457.20")|| costCenterNo.equals("457.30")||costCenterNo.equals("450.00")|| costCenterNo.equals("450.80")||costCenterNo.equals("451.00")||costCenterNo.equals("451.10")||costCenterNo.equals("451.20")||costCenterNo.equals("451.30")||costCenterNo.equals("451.40")||costCenterNo.equals("451.50")||costCenterNo.equals("451.90") || costCenterNo.equals("452.00") || costCenterNo.equals("452.10")||costCenterNo.equals("452.20")||costCenterNo.equals("452.30")){
			fileName = rept.generateReport("allocation_Of_jobNumber_MaintenancePlanning",param,getSession(),REPORT_DIRECTORY,EXPORT_REPORT_DIRECTORY);
			
		
		}
		else if(branchType != null && (branchType.equalsIgnoreCase(Constants.BRANCHTYPE_AREA_MAINTENANCE) 
				|| branchType.equalsIgnoreCase(Constants.BRANCHTYPE_AREA_UNIT) 
				|| branchType.equalsIgnoreCase(Constants.BRANCHTYPE_DISTRIBUTION_AND_MAINTENANCE) 
				|| branchType.equalsIgnoreCase(Constants.BRANCHTYPE_ENERGY_MANAGEMENT) 
				|| getSelectedConstructionEstNumber().contains(Constants.JOBTYPE_CONSTRUCTION))){
			fileName = rept.generateReport("allocation_Of_jobNumber_MaintenancePlanning",param,getSession(),REPORT_DIRECTORY,EXPORT_REPORT_DIRECTORY);
			
		}else{
			fileName = rept.generateReport("allocation_Of_jobNumber_MaintenancePlanning",param,getSession(),REPORT_DIRECTORY,EXPORT_REPORT_DIRECTORY);
			
		}
		
	
		System.out.println("xxxxxxxxx"+ fileName);
		
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
		return "successprint";


	}
	
	
	public String GenerateAllocationOfJobLetterBelowOneMillion(){
		HttpServletRequest request = ServletActionContext.getRequest();
		
		String costCenterNo=(String) request.getSession().getAttribute("costCenterNo");
		String userId= (String) request.getSession().getAttribute("userName");
		
		String region= (String) request.getSession().getAttribute("region");
		String smcType= (String) request.getSession().getAttribute("smcType");
		String branchType= (String) request.getSession().getAttribute("branchType");
		 
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
	 

		System.out.println("Application Number :  " + getSelectedEstNumber());
		System.out.println("CC Number :  " + costCenterNo);
		param.put("@appNo", "'"+getSelectedConstructionEstNumber()+"'");
		param.put("@costctr","'"+costCenterNo+"'");
		
		String fileName = null;
		
		
		
		if(costCenterNo.equals("450.50")|| costCenterNo.equals("452.40")|| costCenterNo.equals("452.50")|| costCenterNo.equals("452.80")||costCenterNo.equals("452.90")||costCenterNo.equals("453.00")||costCenterNo.equals("453.10")||costCenterNo.equals("453.20")||costCenterNo.equals("453.30")||costCenterNo.equals("453.40")|| costCenterNo.equals("454.00")||costCenterNo.equals("454.10")||costCenterNo.equals("454.20")||costCenterNo.equals("454.50")||costCenterNo.equals("455.00")||costCenterNo.equals("455.10")||costCenterNo.equals("455.20")||costCenterNo.equals("455.30")||costCenterNo.equals("455.40")|| costCenterNo.equals("455.50")||costCenterNo.equals("456.00")||costCenterNo.equals("456.10")|| costCenterNo.equals("456.20")||costCenterNo.equals("456.30")||costCenterNo.equals("457.00")||costCenterNo.equals("457.10")||costCenterNo.equals("457.20")|| costCenterNo.equals("457.30")||costCenterNo.equals("450.00")|| costCenterNo.equals("450.80")||costCenterNo.equals("451.00")||costCenterNo.equals("451.10")||costCenterNo.equals("451.20")||costCenterNo.equals("451.30")||costCenterNo.equals("451.40")||costCenterNo.equals("451.50")||costCenterNo.equals("451.90") || costCenterNo.equals("452.00") || costCenterNo.equals("452.10")||costCenterNo.equals("452.20")||costCenterNo.equals("452.30")){
			fileName = rept.generateReport("allocation_Of_jobNumber_BelowOneMillion",param,getSession(),REPORT_DIRECTORY,EXPORT_REPORT_DIRECTORY);
			
		
		}
		else if(branchType != null && (branchType.equalsIgnoreCase(Constants.BRANCHTYPE_AREA_MAINTENANCE) 
				|| branchType.equalsIgnoreCase(Constants.BRANCHTYPE_AREA_UNIT) 
				|| branchType.equalsIgnoreCase(Constants.BRANCHTYPE_DISTRIBUTION_AND_MAINTENANCE) 
				|| branchType.equalsIgnoreCase(Constants.BRANCHTYPE_ENERGY_MANAGEMENT) 
				|| getSelectedConstructionEstNumber().contains(Constants.JOBTYPE_CONSTRUCTION))){
			fileName = rept.generateReport("allocation_Of_jobNumber_BelowOneMillion",param,getSession(),REPORT_DIRECTORY,EXPORT_REPORT_DIRECTORY);
			
		}else{
			fileName = rept.generateReport("allocation_Of_jobNumber_BelowOneMillion",param,getSession(),REPORT_DIRECTORY,EXPORT_REPORT_DIRECTORY);
			
		}
		
	
		System.out.println("xxxxxxxxx"+ fileName);
		
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
		return "successprint";


	}




public String GenerateAllocationOfJobLetterArea(){
		HttpServletRequest request = ServletActionContext.getRequest();
		
		String costCenterNo=(String) request.getSession().getAttribute("costCenterNo");
		String userId= (String) request.getSession().getAttribute("userName");
		
		String region= (String) request.getSession().getAttribute("region");
		String smcType= (String) request.getSession().getAttribute("smcType");
		String branchType= (String) request.getSession().getAttribute("branchType");
		 
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
	 

		System.out.println("Application Number :  " + getSelectedEstNumber());
		System.out.println("CC Number :  " + costCenterNo);
		param.put("@appNo", "'"+getSelectedConstructionEstNumber()+"'");
		param.put("@costctr","'"+costCenterNo+"'");
		
		String fileName = null;
		
		
		
		if(costCenterNo.equals("450.50")|| costCenterNo.equals("452.40")|| costCenterNo.equals("452.50")|| costCenterNo.equals("452.80")||costCenterNo.equals("452.90")||costCenterNo.equals("453.00")||costCenterNo.equals("453.10")||costCenterNo.equals("453.20")||costCenterNo.equals("453.30")||costCenterNo.equals("453.40")|| costCenterNo.equals("454.00")||costCenterNo.equals("454.10")||costCenterNo.equals("454.20")||costCenterNo.equals("454.50")||costCenterNo.equals("455.00")||costCenterNo.equals("455.10")||costCenterNo.equals("455.20")||costCenterNo.equals("455.30")||costCenterNo.equals("455.40")|| costCenterNo.equals("455.50")||costCenterNo.equals("456.00")||costCenterNo.equals("456.10")|| costCenterNo.equals("456.20")||costCenterNo.equals("456.30")||costCenterNo.equals("457.00")||costCenterNo.equals("457.10")||costCenterNo.equals("457.20")|| costCenterNo.equals("457.30")||costCenterNo.equals("450.00")|| costCenterNo.equals("450.80")||costCenterNo.equals("451.00")||costCenterNo.equals("451.10")||costCenterNo.equals("451.20")||costCenterNo.equals("451.30")||costCenterNo.equals("451.40")||costCenterNo.equals("451.50")||costCenterNo.equals("451.90") || costCenterNo.equals("452.00") || costCenterNo.equals("452.10")||costCenterNo.equals("452.20")||costCenterNo.equals("452.30")){
			fileName = rept.generateReport("allocation_Of_jobNumberArea",param,getSession(),REPORT_DIRECTORY,EXPORT_REPORT_DIRECTORY);
			
		
		}
		else if(branchType != null && (branchType.equalsIgnoreCase(Constants.BRANCHTYPE_AREA_MAINTENANCE) 
				|| branchType.equalsIgnoreCase(Constants.BRANCHTYPE_AREA_UNIT) 
				|| branchType.equalsIgnoreCase(Constants.BRANCHTYPE_DISTRIBUTION_AND_MAINTENANCE) 
				|| branchType.equalsIgnoreCase(Constants.BRANCHTYPE_ENERGY_MANAGEMENT) 
				|| getSelectedConstructionEstNumber().contains(Constants.JOBTYPE_CONSTRUCTION))){
			fileName = rept.generateReport("allocation_Of_jobNumberArea",param,getSession(),REPORT_DIRECTORY,EXPORT_REPORT_DIRECTORY);
			
		}else{
			fileName = rept.generateReport("allocation_Of_jobNumberArea",param,getSession(),REPORT_DIRECTORY,EXPORT_REPORT_DIRECTORY);
			
		}
		
	
		System.out.println("xxxxxxxxx"+ fileName);
		
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
		return "successprint";


	}

	
	public String GenerateAllocationOfMNTJobLetter(){
		HttpServletRequest request = ServletActionContext.getRequest();
		
		String costCenterNo=(String) request.getSession().getAttribute("costCenterNo");
		String userId= (String) request.getSession().getAttribute("userName");
		
		String region= (String) request.getSession().getAttribute("region");
		String smcType= (String) request.getSession().getAttribute("smcType");
		String branchType= (String) request.getSession().getAttribute("branchType");
		 
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
	 

		System.out.println("Application Number :  " + getSelectedEstNumber());
		System.out.println("CC Number :  " + costCenterNo);
		param.put("@appNo", "'"+getSelectedConstructionEstNumber()+"'");
		param.put("@costctr","'"+costCenterNo+"'");
		
		String fileName = null;
		
		
		
		if(costCenterNo.equals("450.50")|| costCenterNo.equals("452.40")|| costCenterNo.equals("452.50")|| costCenterNo.equals("452.80")||costCenterNo.equals("452.90")||costCenterNo.equals("453.00")||costCenterNo.equals("453.10")||costCenterNo.equals("453.20")||costCenterNo.equals("453.30")||costCenterNo.equals("453.40")|| costCenterNo.equals("454.00")||costCenterNo.equals("454.10")||costCenterNo.equals("454.20")||costCenterNo.equals("454.50")||costCenterNo.equals("455.00")||costCenterNo.equals("455.10")||costCenterNo.equals("455.20")||costCenterNo.equals("455.30")||costCenterNo.equals("455.40")|| costCenterNo.equals("455.50")||costCenterNo.equals("456.00")||costCenterNo.equals("456.10")|| costCenterNo.equals("456.20")||costCenterNo.equals("456.30")||costCenterNo.equals("457.00")||costCenterNo.equals("457.10")||costCenterNo.equals("457.20")|| costCenterNo.equals("457.30")||costCenterNo.equals("450.00")|| costCenterNo.equals("450.80")||costCenterNo.equals("451.00")||costCenterNo.equals("451.10")||costCenterNo.equals("451.20")||costCenterNo.equals("451.30")||costCenterNo.equals("451.40")||costCenterNo.equals("451.50")||costCenterNo.equals("451.90") || costCenterNo.equals("452.00") || costCenterNo.equals("452.10")||costCenterNo.equals("452.20")||costCenterNo.equals("452.30")){
			fileName = rept.generateReport("allocation_Of_jobNumber_Depo",param,getSession(),REPORT_DIRECTORY,EXPORT_REPORT_DIRECTORY);
			
		
		}
		else if(branchType != null && (branchType.equalsIgnoreCase(Constants.BRANCHTYPE_AREA_MAINTENANCE) 
				|| branchType.equalsIgnoreCase(Constants.BRANCHTYPE_AREA_UNIT) 
				|| branchType.equalsIgnoreCase(Constants.BRANCHTYPE_DISTRIBUTION_AND_MAINTENANCE) 
				|| branchType.equalsIgnoreCase(Constants.BRANCHTYPE_ENERGY_MANAGEMENT) 
				|| getSelectedConstructionEstNumber().contains(Constants.JOBTYPE_CONSTRUCTION))){
			fileName = rept.generateReport("allocation_Of_jobNumber_MaintenanceSAB",param,getSession(),REPORT_DIRECTORY,EXPORT_REPORT_DIRECTORY);
			
		}else{
			fileName = rept.generateReport("allocation_Of_jobNumber",param,getSession(),REPORT_DIRECTORY,EXPORT_REPORT_DIRECTORY);
			
		}
		
	
		System.out.println("xxxxxxxxx"+ fileName);
		
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
		return "successprint";


	}
	
	public String GenerateAllocationOfConstructionLetter(){
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
		
		
		String costCenterNoCommercial = null;

		//String deptid= costCenterNo.substring(0, 3);
		//if(deptid != null){
			//costCenterNoCommercial = deptid.concat(".00");

		//}
		
		ProvinceDetailsMasterEjb promasEjb = new ProvinceDetailsMasterEjb();
		try {
			costCenterNoCommercial = promasEjb.getCommercialId(costCenterNo,  getSessionKey("region"));
		} catch (Exception e) {
			return ERROR;
		}
		
		HashMap<String, Object> param = new HashMap<String, Object>();
	 

		
		param.put("@appNo", "'"+getSelectedEstNumber()+"'");
		//if(smcType.equalsIgnoreCase(Constants.JOBTYPE_CONSTRUCTION) || smcType.equalsIgnoreCase(Constants.JOBTYPE_DISTRIBUTION_AND_MAINTENANCE)){
			//param.put("@costctr","'"+costCenterNo+"'");
		//}else{
			param.put("@costctr","'"+costCenterNoCommercial+"'");
		//}
		
		
		
		param.put("@coordinating", "'"+getElecSupName()+"'");
	
		//param.put("@conname","'"+getContractor()+"'");
		if(getContractor() != null){
			SpestcntEjb ejbCnt=new SpestcntEjb(getSessionKey("region"));	
			SpestcntPK spestcntpk = new SpestcntPK();
			spestcntpk.setContractorId(getContractor());
			spestcntpk.setDeptId(costCenterNo);
			
			 
			Spestcnt spestcnt=ejbCnt.findById(spestcntpk);
			if(spestcnt != null){
				param.put("@conname","'"+spestcnt.getContractorName()+"'");
			}else{
				param.put("@conname","'"+getContractor()+"'");
			}
		}
		
		
		
		param.put("@esname","'"+getAllocatedId()+"'");
		if(getConstrucRef() != null && getSelectedConstructionEstNumber().equalsIgnoreCase("-1")){
			param.put("@construRef","'"+getConstrucRef()+"'");
		}else if(!getSelectedConstructionEstNumber().equalsIgnoreCase("-1")){
			param.put("@construRef","'"+getSelectedConstructionEstNumber()+"'");
		}
		

		String fileName = null;
		if(smcType.equalsIgnoreCase(Constants.RUTAL_ELECTRIFICATION) || smcType.equalsIgnoreCase(Constants.RUTAL_ELECTRIFICATION_I)){
			System.out.println("newhiiii allocation_Of_constructionJob"+ fileName);
			fileName = rept.generateReport("allocation_Of_constructionJob",param,getSession(),REPORT_DIRECTORY,EXPORT_REPORT_DIRECTORY);
		}else if(smcType.equalsIgnoreCase(Constants.BRANCHTYPE_AREA_UNIT) || smcType.equalsIgnoreCase(Constants.RUTAL_ELECTRIFICATION_I)|| smcType.equalsIgnoreCase(Constants.RUTAL_ELECTRIFICATION)){
			System.out.println("newhiiii allocation_Of_constructionJob"+ fileName);
			fileName = rept.generateReport("allocation_Of_constructionJob_Uva",param,getSession(),REPORT_DIRECTORY,EXPORT_REPORT_DIRECTORY);
		}else{
			System.out.println("newhiiii allocation_Of_constructionJob"+ fileName);
			fileName = rept.generateReport("allocation_Of_constructionJob",param,getSession(),REPORT_DIRECTORY,EXPORT_REPORT_DIRECTORY);
			System.out.println("Test SAB");
		}
		
	
		System.out.println("xxxxxxxxx"+ fileName);
		
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
		return "successprint";


	}
	
	public String GenerateAllocationOfMNTLetter(){
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
		
		
		String costCenterNoCommercial = null;

		//String deptid= costCenterNo.substring(0, 3);
		//if(deptid != null){
			//costCenterNoCommercial = deptid.concat(".00");

		//}
		
		ProvinceDetailsMasterEjb promasEjb = new ProvinceDetailsMasterEjb();
		costCenterNoCommercial = null;
		try {
			costCenterNoCommercial = promasEjb.getCommercialId(costCenterNo,  getSessionKey("region"));
		} catch (Exception e) {
			return ERROR;
		}
		
		
		HashMap<String, Object> param = new HashMap<String, Object>();
		if(costCenterNoCommercial.equalsIgnoreCase("550.00")){
			//getSelectedEstNumber().
			System.out.println("hi gayani");
			
			CharSequence cs1 = "PL";
			if(getSelectedEstNumber().contains(cs1)){
				System.out.println("hi gayani 1");
				costCenterNoCommercial = "550.14";
			}
			
		}
		
		
		
		

		
		param.put("@appNo", "'"+getSelectedEstNumber()+"'");
		//if(smcType.equalsIgnoreCase(Constants.JOBTYPE_CONSTRUCTION) || smcType.equalsIgnoreCase(Constants.JOBTYPE_DISTRIBUTION_AND_MAINTENANCE)){
			//param.put("@costctr","'"+costCenterNo+"'");
		//}else{
			param.put("@costctr","'"+costCenterNoCommercial+"'");
		//}
		
		
		
		param.put("@coordinating", "'"+getElecSupName()+"'");
	
		//param.put("@conname","'"+getContractor()+"'");
		if(getContractor() != null){
			SpestcntEjb ejbCnt=new SpestcntEjb(getSessionKey("region"));	
			SpestcntPK spestcntpk = new SpestcntPK();
			spestcntpk.setContractorId(getContractor());
			spestcntpk.setDeptId(costCenterNo);
			
			 
			Spestcnt spestcnt=ejbCnt.findById(spestcntpk);
			if(spestcnt != null){
				param.put("@conname","'"+spestcnt.getContractorName()+"'");
			}else{
				param.put("@conname","'"+getContractor()+"'");
			}
		}
		
		
		
		param.put("@esname","'"+getAllocatedId()+"'");
		if(getConstrucRef() != null && getSelectedConstructionEstNumber().equalsIgnoreCase("-1")){
			param.put("@construRef","'"+getConstrucRef()+"'");
		}else if(!getSelectedConstructionEstNumber().equalsIgnoreCase("-1")){
			param.put("@construRef","'"+getSelectedConstructionEstNumber()+"'");
		}
		

		String fileName = null;
		if(smcType.equalsIgnoreCase(Constants.RUTAL_ELECTRIFICATION) || smcType.equalsIgnoreCase(Constants.RUTAL_ELECTRIFICATION_I)){
			System.out.println("newhiiii allocation_Of_constructionJob"+ fileName);
			fileName = rept.generateReport("allocation_Of_MNTJob",param,getSession(),REPORT_DIRECTORY,EXPORT_REPORT_DIRECTORY);
		}else{
			System.out.println("newhiiii allocation_Of_constructionJob"+ fileName);
			fileName = rept.generateReport("allocation_Of_MNTJob",param,getSession(),REPORT_DIRECTORY,EXPORT_REPORT_DIRECTORY);
		}
		
	
		System.out.println("xxxxxxxxx"+ fileName);
		
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
		return "successprint";


	}
	
	
	
	public String GenerateAllocationOfConstructionLetterCon(){
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
		
		
		String costCenterNoCommercial = null;

		//String deptid= costCenterNo.substring(0, 3);
		//if(deptid != null){
			//costCenterNoCommercial = deptid.concat(".00");

		//}
		
		ProvinceDetailsMasterEjb promasEjb = new ProvinceDetailsMasterEjb();
		costCenterNoCommercial = null;
		try {
			costCenterNoCommercial = promasEjb.getCommercialId(costCenterNo,  getSessionKey("region"));
					
			
		} catch (Exception e) {
			return ERROR;
		}
		
		
		HashMap<String, Object> param = new HashMap<String, Object>();
	 

		
		param.put("@appNo", "'"+getSelectedEstNumber()+"'");
		if(smcType.equalsIgnoreCase(Constants.BRANCHTYPE_AREA_UNIT) ||smcType.equalsIgnoreCase(Constants.JOBTYPE_CONSTRUCTION) || smcType.equalsIgnoreCase(Constants.JOBTYPE_DISTRIBUTION_AND_MAINTENANCE)){
			param.put("@costctr","'"+costCenterNo+"'");
			System.out.println("hi gayani 3");
		}else{
			param.put("@costctr","'"+costCenterNoCommercial+"'");
			System.out.println("hi gayani 4");
		}
		
		
		
		param.put("@coordinating", "'"+getElecSupName()+"'");
	
		//param.put("@conname","'"+getContractor()+"'");
		if(getContractor() != null){
			SpestcntEjb ejbCnt=new SpestcntEjb(getSessionKey("region"));	
			SpestcntPK spestcntpk = new SpestcntPK();
			spestcntpk.setContractorId(getContractor());
			spestcntpk.setDeptId(costCenterNo);
			
			 
			Spestcnt spestcnt=ejbCnt.findById(spestcntpk);
			if(spestcnt != null){
				param.put("@conname","'"+spestcnt.getContractorName()+"'");
			}else{
				param.put("@conname","'"+getContractor()+"'");
			}
		}
		
		
		
		param.put("@esname","'"+getAllocatedId()+"'");
		if(getConstrucRef() != null && getSelectedConstructionEstNumber().equalsIgnoreCase("-1")){
			param.put("@construRef","'"+getConstrucRef()+"'");
		}else if(!getSelectedConstructionEstNumber().equalsIgnoreCase("-1")){
			param.put("@construRef","'"+getSelectedConstructionEstNumber()+"'");
		}
		

		String fileName = null;
		
		if(costCenterNo.equals("450.50")|| costCenterNo.equals("452.40")|| costCenterNo.equals("452.50")|| costCenterNo.equals("452.80")||costCenterNo.equals("452.90")||costCenterNo.equals("453.00")||costCenterNo.equals("453.10")||costCenterNo.equals("453.20")||costCenterNo.equals("453.30")||costCenterNo.equals("453.40")|| costCenterNo.equals("454.00")||costCenterNo.equals("454.10")||costCenterNo.equals("454.20")||costCenterNo.equals("454.50")||costCenterNo.equals("455.00")||costCenterNo.equals("455.10")||costCenterNo.equals("455.20")||costCenterNo.equals("455.30")||costCenterNo.equals("455.40")|| costCenterNo.equals("455.50")||costCenterNo.equals("456.00")||costCenterNo.equals("456.10")|| costCenterNo.equals("456.20")||costCenterNo.equals("456.30")||costCenterNo.equals("457.00")||costCenterNo.equals("457.10")||costCenterNo.equals("457.20")|| costCenterNo.equals("457.30")||costCenterNo.equals("450.00")|| costCenterNo.equals("450.80")||costCenterNo.equals("451.00")||costCenterNo.equals("451.10")||costCenterNo.equals("451.20")||costCenterNo.equals("451.30")||costCenterNo.equals("451.40")||costCenterNo.equals("451.50")||costCenterNo.equals("451.90") || costCenterNo.equals("452.00") || costCenterNo.equals("452.10")||costCenterNo.equals("452.20")||costCenterNo.equals("452.30")){
            System.out.println("hiiiiggggg allocation_Of_constructionJobDepo"+ fileName);
			
			fileName = rept.generateReport("allocation_Of_constructionJob_Depo",param,getSession(),REPORT_DIRECTORY,EXPORT_REPORT_DIRECTORY);
	
		}else if(smcType.equalsIgnoreCase(Constants.RUTAL_ELECTRIFICATION) || smcType.equalsIgnoreCase(Constants.RUTAL_ELECTRIFICATION_I)){
			System.out.println("hiiii allocation_Of_constructionJob"+ fileName);
			fileName = rept.generateReport("allocation_Of_constructionJob",param,getSession(),REPORT_DIRECTORY,EXPORT_REPORT_DIRECTORY);
		}else if(smcType.equalsIgnoreCase(Constants.BRANCHTYPE_AREA_UNIT) ||smcType.equalsIgnoreCase(Constants.JOBTYPE_CONSTRUCTION) || smcType.equalsIgnoreCase(Constants.JOBTYPE_DISTRIBUTION_AND_MAINTENANCE)){
			System.out.println("hiiiiggggg allocation_Of_constructionJob"+ fileName);
			
			fileName = rept.generateReport("allocation_Of_constructionJob_Uva",param,getSession(),REPORT_DIRECTORY,EXPORT_REPORT_DIRECTORY);
		}
		
		else{
			System.out.println("hiiii allocation_Of_constructionJob"+ fileName);
			fileName = rept.generateReport("allocation_Of_constructionJob",param,getSession(),REPORT_DIRECTORY,EXPORT_REPORT_DIRECTORY);
		}
		
	
		System.out.println("xxxxxxxxx"+ fileName);
		
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
		return "successprint";


	}
public String GenerateEstimateVariance(){
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
	 

	System.out.println("Application Number :  " + getSelectedConstructionEstNumber());
	System.out.println("CC Number :  " + costCenterNo);
	param.put("@project_no", "'"+getSelectedConstructionEstNumber()+"'");
	param.put("@costctr","'"+costCenterNo+"'");
	

	String fileName = null;
	//if(smcType.equalsIgnoreCase(Constants.RUTAL_ELECTRIFICATION) || smcType.equalsIgnoreCase(Constants.RUTAL_ELECTRIFICATION_I)){
		fileName = rept.generateReport("EstimatewithVariance",param,getSession(),REPORT_DIRECTORY,EXPORT_REPORT_DIRECTORY);
	//}else{
	//	fileName = rept.generateReport("EstimatewithVariance",param,getSession(),REPORT_DIRECTORY,EXPORT_REPORT_DIRECTORY);
	//}
	
	
	System.out.println("xxxxxxxxx"+ fileName);
	
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
	return "successprint";


}
public String GenerateEstimateVarianceReport(){
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
	 

	System.out.println("Application Number :  " + getSelectedConstructionEstNumber());
	System.out.println("CC Number :  " + costCenterNo);
	param.put("@project_no", "'"+getSelectedConstructionEstNumber()+"'");
	param.put("@costctr","'"+costCenterNo+"'");
	

	String fileName = null;
	//if(smcType.equalsIgnoreCase(Constants.RUTAL_ELECTRIFICATION) || smcType.equalsIgnoreCase(Constants.RUTAL_ELECTRIFICATION_I)){
		fileName = rept.generateReport("EstimatewithVarianceReport",param,getSession(),REPORT_DIRECTORY,EXPORT_REPORT_DIRECTORY);
	//}else{
	//	fileName = rept.generateReport("EstimatewithVariance",param,getSession(),REPORT_DIRECTORY,EXPORT_REPORT_DIRECTORY);
	//}
	
	
	System.out.println("xxxxxxxxx"+ fileName);
	
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
	return "successprint";


}
public String GenerateJobTransferToAsset(){
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
 

	System.out.println("Application Number :  " + getSelectedEstNumber());
	System.out.println("CC Number :  " + costCenterNo);
	param.put("@appNo", "'"+getSelectedConstructionEstNumber()+"'");
	param.put("@costctr","'"+costCenterNo+"'");
	

	String fileName = null;
	//if(smcType.equalsIgnoreCase(Constants.RUTAL_ELECTRIFICATION) || smcType.equalsIgnoreCase(Constants.RUTAL_ELECTRIFICATION_I)){
		fileName = rept.generateReport("Completed_Construction_Job_For_Transfering_To_Assets",param,getSession(),REPORT_DIRECTORY,EXPORT_REPORT_DIRECTORY);
	//}else{
	//	fileName = rept.generateReport("EstimatewithVariance",param,getSession(),REPORT_DIRECTORY,EXPORT_REPORT_DIRECTORY);
	//}
	
	
	System.out.println("xxxxxxxxx"+ fileName);
	
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
	return "successprint";


}

public String GenerateJHandingOverCompletedJob(){
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
 

	System.out.println("Application Number :  " + getSelectedEstNumber());
	System.out.println("CC Number :  " + costCenterNo);
	param.put("@appNo", "'"+getSelectedConstructionEstNumber()+"'");
	param.put("@costctr","'"+costCenterNo+"'");
	

	String fileName = null;
	if(costCenterNo.equals("450.50")||costCenterNo.equals("452.40")|| costCenterNo.equals("452.50")|| costCenterNo.equals("452.80")||costCenterNo.equals("452.90")||costCenterNo.equals("453.00")||costCenterNo.equals("453.10")||costCenterNo.equals("453.20")||costCenterNo.equals("453.30")||costCenterNo.equals("453.40")|| costCenterNo.equals("454.00")||costCenterNo.equals("454.10")||costCenterNo.equals("454.20")||costCenterNo.equals("454.50")||costCenterNo.equals("455.00")||costCenterNo.equals("455.10")||costCenterNo.equals("455.20")||costCenterNo.equals("455.30")||costCenterNo.equals("455.40")|| costCenterNo.equals("455.50")||costCenterNo.equals("456.00")||costCenterNo.equals("456.10")|| costCenterNo.equals("456.20")||costCenterNo.equals("456.30")||costCenterNo.equals("457.00")||costCenterNo.equals("457.10")||costCenterNo.equals("457.20")|| costCenterNo.equals("457.30")||costCenterNo.equals("450.00")||costCenterNo.equals("450.80")||costCenterNo.equals("451.00")||costCenterNo.equals("451.10")||costCenterNo.equals("451.20")||costCenterNo.equals("451.30")||costCenterNo.equals("451.40")||costCenterNo.equals("451.50")||costCenterNo.equals("451.90") || costCenterNo.equals("452.00") || costCenterNo.equals("452.10")||costCenterNo.equals("452.20")||costCenterNo.equals("452.30")){
		fileName = rept.generateReport("HandingOver_Of_A_completed_Construction_JobDepo",param,getSession(),REPORT_DIRECTORY,EXPORT_REPORT_DIRECTORY);
		
	}else{
		fileName = rept.generateReport("HandingOver_Of_A_completed_Construction_Job",param,getSession(),REPORT_DIRECTORY,EXPORT_REPORT_DIRECTORY);
	}
	//}else{
	//	fileName = rept.generateReport("EstimatewithVariance",param,getSession(),REPORT_DIRECTORY,EXPORT_REPORT_DIRECTORY);
	//}
	

	System.out.println("xxxxxxxxx"+ fileName);
	
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
	return "successprint";


}


public String GenerateHelpChangePostDeptIDandStatus(){
	
	HttpServletRequest request = ServletActionContext.getRequest();
		
		
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
		

			String fileName = REPORT_DIRECTORY + "HelpOfChange PostDeptID and Status for Commercial.pdf";

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
		return "successprint";


	}

public String GenerateHelpChangeCodeNumber(){
	
	HttpServletRequest request = ServletActionContext.getRequest();
		
		
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
		

			String fileName = REPORT_DIRECTORY + "Change Code Number.pdf";

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
		return "successprint";


	}


public String GenerateHelpUploadSketch(){
	
	HttpServletRequest request = ServletActionContext.getRequest();
		
		
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
		

			String fileName = REPORT_DIRECTORY + "Upload_Sketch.pdf";

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
		return "successprint";


	}



public String GenerateHelpAddRebateMaterial(){
	
	HttpServletRequest request = ServletActionContext.getRequest();
		
		
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
		

			String fileName = REPORT_DIRECTORY + "Add_Rebate_Material_to_Estimate.pdf";

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
		return "successprint";


	}

public String GenerateHelpEstimateCancellationNew(){
	
HttpServletRequest request = ServletActionContext.getRequest();
	
	
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
	

		String fileName = REPORT_DIRECTORY + "Estimate Cancellation for Commercial.pdf";

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
	return "successprint";


}


public String GenerateHelpCirculerNoCOM3(){
	
	HttpServletRequest request = ServletActionContext.getRequest();
		
		
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
		

			String fileName = REPORT_DIRECTORY + "EstimateHelpCirculerNoCOM3.pdf";

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
		return "successprint";


	}


public String GenerateSummaryofInProgressConstructionNew(){
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
 

	System.out.println("Application Number :  " + getSelectedEstNumber());
	System.out.println("CC Number :  " + costCenterNo);
	param.put("@appNo", "'"+getSelectedConstructionEstNumber()+"'");
	param.put("@costctr","'"+costCenterNo+"'");
	

	String fileName = null;
	//if(smcType.equalsIgnoreCase(Constants.RUTAL_ELECTRIFICATION) || smcType.equalsIgnoreCase(Constants.RUTAL_ELECTRIFICATION_I)){
		fileName = rept.generateReport("SummaryofInProgressConstructionNew",param,getSession(),REPORT_DIRECTORY,EXPORT_REPORT_DIRECTORY);
	//}else{
	//	fileName = rept.generateReport("EstimatewithVariance",param,getSession(),REPORT_DIRECTORY,EXPORT_REPORT_DIRECTORY);
	//}
	

	System.out.println("xxxxxxxxx"+ fileName);
	
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
	return "successprint";


}


public String GenerateStatusReport(){
	
	String fromDate ="";
	String toDate = "";
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
 

	System.out.println("Application Number :  " + getSelectedEstNumber());
	System.out.println("CC Number :  " + costCenterNo);
	param.put("@fromDate", "'" + fromDate +"'");
	param.put("@toDate", "'" +toDate+ "'");
	param.put("@costctr","'"+costCenterNo+"'");
	

	String fileName = null;
	//if(smcType.equalsIgnoreCase(Constants.RUTAL_ELECTRIFICATION) || smcType.equalsIgnoreCase(Constants.RUTAL_ELECTRIFICATION_I)){
		fileName = rept.generateReport("cc_documentInquirySPS _con",param,getSession(),REPORT_DIRECTORY,EXPORT_REPORT_DIRECTORY);
	//}else{
	//	fileName = rept.generateReport("EstimatewithVariance",param,getSession(),REPORT_DIRECTORY,EXPORT_REPORT_DIRECTORY);
	//}
	

	System.out.println("xxxxxxxxx"+ fileName);
	
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
	return "successprint";


}

public String GenerateStatusReportCom(){
	
	String fromDate ="";
	String toDate = "";
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
 

	System.out.println("Application Number :  " + getSelectedEstNumber());
	System.out.println("CC Number :  " + costCenterNo);
	param.put("@fromDate", "'" + fromDate +"'");
	param.put("@toDate", "'" +toDate+ "'");
	param.put("@costctr","'"+costCenterNo+"'");
	

	String fileName = null;
	//if(smcType.equalsIgnoreCase(Constants.RUTAL_ELECTRIFICATION) || smcType.equalsIgnoreCase(Constants.RUTAL_ELECTRIFICATION_I)){
		fileName = rept.generateReport("cc_documentInquirySPSNew",param,getSession(),REPORT_DIRECTORY,EXPORT_REPORT_DIRECTORY);
	//}else{
	//	fileName = rept.generateReport("EstimatewithVariance",param,getSession(),REPORT_DIRECTORY,EXPORT_REPORT_DIRECTORY);
	//}
	

	System.out.println("xxxxxxxxx"+ fileName);
	
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
	return "successprint";


}





public String GenerateCompletionOfConstructionJob(){
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
 

	System.out.println("Application Number :  " + getSelectedEstNumber());
	System.out.println("CC Number :  " + costCenterNo);
	param.put("@appNo", "'"+getSelectedConstructionEstNumber()+"'");
	param.put("@costctr","'"+costCenterNo+"'");
	param.put("@finishedDate","'"+getExpectedDateofFinish()+"'");
	

	String fileName = null;
	if(costCenterNo.equals("450.50")|| costCenterNo.equals("452.40")|| costCenterNo.equals("452.50")|| costCenterNo.equals("452.80")||costCenterNo.equals("452.90")||costCenterNo.equals("453.00")||costCenterNo.equals("453.10")||costCenterNo.equals("453.20")||costCenterNo.equals("453.30")||costCenterNo.equals("453.40")|| costCenterNo.equals("454.00")||costCenterNo.equals("454.10")||costCenterNo.equals("454.20")||costCenterNo.equals("454.50")||costCenterNo.equals("455.00")||costCenterNo.equals("455.10")||costCenterNo.equals("455.20")||costCenterNo.equals("455.30")||costCenterNo.equals("455.40")|| costCenterNo.equals("455.50")||costCenterNo.equals("456.00")||costCenterNo.equals("456.10")|| costCenterNo.equals("456.20")||costCenterNo.equals("456.30")||costCenterNo.equals("457.00")||costCenterNo.equals("457.10")||costCenterNo.equals("457.20")|| costCenterNo.equals("457.30")||costCenterNo.equals("450.00")|| costCenterNo.equals("450.80")||costCenterNo.equals("451.00")||costCenterNo.equals("451.10")||costCenterNo.equals("451.20")||costCenterNo.equals("451.30")||costCenterNo.equals("451.40")||costCenterNo.equals("451.50")||costCenterNo.equals("451.90") || costCenterNo.equals("452.00") || costCenterNo.equals("452.10")||costCenterNo.equals("452.20")||costCenterNo.equals("452.30")){
		fileName = rept.generateReport("Completion_Of_Construction_JobDepo",param,getSession(),REPORT_DIRECTORY,EXPORT_REPORT_DIRECTORY);
		
	
	}else{
		fileName = rept.generateReport("Completion_Of_Construction_Job",param,getSession(),REPORT_DIRECTORY,EXPORT_REPORT_DIRECTORY);
	}
	//}else{
	//	fileName = rept.generateReport("EstimatewithVariance",param,getSession(),REPORT_DIRECTORY,EXPORT_REPORT_DIRECTORY);
	//}
	

	System.out.println("xxxxxxxxx"+ fileName);
	
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
	return "successprint";


}

public String GenerateHTConditions(){
	HttpServletRequest request = ServletActionContext.getRequest();
	
	
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
	

		String fileName = REPORT_DIRECTORY + "HT_Conditions.pdf";

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
	return "successprintHT";


}
public String GenerateLTConditions(){
	HttpServletRequest request = ServletActionContext.getRequest();
	
	
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
	

		String fileName = REPORT_DIRECTORY + "LT_Conditions.pdf";

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
	return "successprintLT";


}

public String GenerateCebOfferOfMainJob(){
	HttpServletRequest request = ServletActionContext.getRequest();
	
	String costCenterNo=(String) request.getSession().getAttribute("costCenterNo");
	String userId= (String) request.getSession().getAttribute("userName");
	
	String region= (String) request.getSession().getAttribute("region");
	String smcType= (String) request.getSession().getAttribute("smcType");
	String branchType= (String) request.getSession().getAttribute("branchType");
	 
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
 

	System.out.println("Application Number :  " + getSelectedEstNumber());
	System.out.println("CC Number :  " + costCenterNo);
	param.put("@appNo", "'"+getSelectedConstructionEstNumber()+"'");
	param.put("@costctr","'"+costCenterNo+"'");
	
	String fileName = null;
	
	if(branchType != null && (branchType.equalsIgnoreCase(Constants.BRANCHTYPE_AREA_MAINTENANCE) 
			|| branchType.equalsIgnoreCase(Constants.BRANCHTYPE_AREA_UNIT) 
			|| branchType.equalsIgnoreCase(Constants.BRANCHTYPE_DISTRIBUTION_AND_MAINTENANCE) 
			|| branchType.equalsIgnoreCase(Constants.BRANCHTYPE_ENERGY_MANAGEMENT) 
			|| getSelectedConstructionEstNumber().contains(Constants.JOBTYPE_CONSTRUCTION))){
		fileName = rept.generateReport("CEB_Offer_Of_A_Main_Job",param,getSession(),REPORT_DIRECTORY,EXPORT_REPORT_DIRECTORY);
		
	}else{
		fileName = rept.generateReport("CEB_Offer_Of_A_Main_Job",param,getSession(),REPORT_DIRECTORY,EXPORT_REPORT_DIRECTORY);
		
	}
	

	System.out.println("FILE NAME:  "+ fileName);
	
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
	return "successprint";


}

public String GenerateJobCancellation(){
	HttpServletRequest request = ServletActionContext.getRequest();
	
	String costCenterNo=(String) request.getSession().getAttribute("costCenterNo");
	String userId= (String) request.getSession().getAttribute("userName");
	
	String region= (String) request.getSession().getAttribute("region");
	String smcType= (String) request.getSession().getAttribute("smcType");
	String branchType= (String) request.getSession().getAttribute("branchType");
	 
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
 
	System.out.println("reason :  " + getReason());
	System.out.println("Application Number :  " + getSelectedEstNumber());
	System.out.println("CC Number :  " + costCenterNo);
	
	param.put("@appNo", "'"+getSelectedConstructionEstNumber()+"'");
	param.put("@costctr","'"+costCenterNo+"'");
	param.put("@reason", "'"+getReason()+"'");
	
	String fileName = null;
	
	if(branchType != null && (branchType.equalsIgnoreCase(Constants.BRANCHTYPE_AREA_MAINTENANCE) 
			|| branchType.equalsIgnoreCase(Constants.BRANCHTYPE_AREA_UNIT) 
			|| branchType.equalsIgnoreCase(Constants.BRANCHTYPE_DISTRIBUTION_AND_MAINTENANCE) 
			|| branchType.equalsIgnoreCase(Constants.BRANCHTYPE_ENERGY_MANAGEMENT) 
			|| getSelectedConstructionEstNumber().contains(Constants.JOBTYPE_CONSTRUCTION))){
		fileName = rept.generateReport("jobcancellation",param,getSession(),REPORT_DIRECTORY,EXPORT_REPORT_DIRECTORY);
		
	}else{
		fileName = rept.generateReport("jobcancellation",param,getSession(),REPORT_DIRECTORY,EXPORT_REPORT_DIRECTORY);
		
	}
	

	System.out.println("FILE NAME %%%%%%%%%:  "+ fileName);
	
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
	return "successprint";


}

private void setReason(String reason) {
	this.reason = reason;
}

private String getReason() {
	return reason;
}


}