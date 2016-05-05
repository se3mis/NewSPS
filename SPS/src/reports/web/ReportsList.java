package reports.web;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import job.model.Pcesthmt;
import job.model.Spestcnd;
import job.model.Spestcnt;
import job.service.JobEjb;
import job.service.PcesthmtEjb;
import job.service.SpestcndEjb;
import job.service.SpestcntEjb;

import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.SessionAware;

import security.service.SecurityEjb;
import util.common.ApplicationType;
import util.common.EstimateStatus;
import util.common.JobProcessStatus;
import util.common.Path;
import util.common.Phase;

import com.opensymphony.xwork2.ActionSupport;

import estimate.model.Appestlim;



public class ReportsList extends ActionSupport implements SessionAware, ParameterAware {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map<String, Object> session;
	private Map <String, String[]> parameters;
	private String loggedInUserId;
	private static final String viewPath="Reports>Select Report";
	private String path;
	private String lblError = null;
	private String lblSuccess = null;
	private List<ReportName> reportList;
	private String selectedReport;
	private List<ReportCategory> reportCategoryList;
	private String selectedReportCategory;
	private String costCenter;


	private List<EstimateStatusType> estimateStatusList;
	private String selectedEstimateStatus;
	private List<PIVReportType> pivReportList;
	private String selectedPIVReport;
	
	private List<Spestcnt> ContractorList;
	private String selectedContractorID; 

	 

	public List<Spestcnt> getContractorList() {
		return ContractorList;
	}
	public void setContractorList(List<Spestcnt> contractorList) {
		ContractorList = contractorList;
	}
	public String getSelectedContractorID() {
		return selectedContractorID;
	}
	public void setSelectedContractorID(String selectedContractorID) {
		this.selectedContractorID = selectedContractorID;
	}

	private String selectedNode;
	private TreeNode rootNode;
	private  String selectCategoryString = "----Select Category-----";
	//private String report_url;

	private Date startDate;
	private Date endDate;
//	private String applicationType;
	private String applicatinNumber;

	private Boolean showCostCenter;
	private Boolean showStartDate;
	private Boolean showEndDate;
	private Boolean showApplicationType;
	private Boolean showApplicationNumber;
	private Boolean showPIVTypes;
	private Boolean showEstimateStatusList;
	private Boolean showJobNumberList;
	private Boolean showAllocatedJobsList;
	private Boolean showContractorIDList;
	private Boolean showjobAllocatedOrFinishedList;
	private List<Pcesthmt> projectNumberList;
	private List<Spestcnd> allocatedJobs;
	private String[] pcesthmtID;// selected finished jobs here
	private String[] selectedAllocatedJobs;// selected finished jobs here
	private List<Phase> applicationTypeList; // to storre application type and code i use Phase class....	
	private String selectedApplicationType;
	private List<Phase> jobAllocatedOrFinishedList; // to store application type and code i use Phase class....
	private String selectedAllocatedOrFinishedJob;
	private InputStream fileInputStream;

	public InputStream getFileInputStream() {
		return fileInputStream;
	}







	public void setFileInputStream(InputStream fileInputStream) {
		this.fileInputStream = fileInputStream;
	}


	public String Close(){
		return "close";
	}




	public String execute(){
		setLblError(null);
		setCostCenter(getSessionKey("costCenterNo"));
		setPath(viewPath);
		setLoggedInUserId(getSessionKey("userName"));
		System.out.println(getStartDate());
		if (getStartDate() == null)
			startDate = new Date();
		if (getEndDate() == null)
			endDate = new Date();
	//	setReportCategory();
		setApplicationTypesList();
		
		setContractorsList();
		setAllocatedOrFinshedList();
		setReports();
		
		SecurityEjb secEjb = new SecurityEjb(getSessionKey("region"));
		String uName = getSessionKey("userName");
		List<String> authCsc = secEjb.getAuthorizedCostCenters(uName);

		
		
		return SUCCESS;
	}







	public void setReportCategory()
	{
		this.reportCategoryList = new ArrayList<ReportCategory>();
		ReportCategory rptCate0 = new ReportCategory(selectCategoryString,"0");
		ReportCategory rptCate4 = new ReportCategory("SMC","4");

		this.reportCategoryList.add(rptCate0);
		this.reportCategoryList.add(rptCate4);


	}

	private void setToFalse()
	{
		this.showApplicationType = false;
		this.showCostCenter = false;
		this.showEndDate = false;
		this.showStartDate = false;
		this.showApplicationNumber = false;
		this.showPIVTypes = false;
		this.showEstimateStatusList = false;
		this.showJobNumberList = false;
		this.showContractorIDList = false;
		this.showjobAllocatedOrFinishedList = false;
		this.showAllocatedJobsList = false;
	}
	
	private void setApplicationTypesList()
	{
		this.applicationTypeList = new   ArrayList<Phase>();
		Phase nc = new Phase(ApplicationType.NEW_LINE_DESC, ApplicationType.NEW_CONN);
		Phase tc = new Phase(ApplicationType.TEMP_CONN_DESC , ApplicationType.TEMP_CONN);
		Phase cr = new Phase(ApplicationType.COST_RECOVERY_DESC,ApplicationType.COST_RECOVERY);
		this.applicationTypeList.add(nc);
		 this.applicationTypeList.add(tc);
		this.applicationTypeList.add(cr);

	}
	
	private void setAllocatedOrFinshedList()
	{
		this.jobAllocatedOrFinishedList = new   ArrayList<Phase>();
		Phase nc = new Phase("Allocated Jobs", "ALLO");
		Phase tc = new Phase("Finished Jobs" , "FINI");
		 
		this.jobAllocatedOrFinishedList.add(nc);
		 this.jobAllocatedOrFinishedList.add(tc);
		 

	}
	
//	SELECT *
//	  FROM R1PROD.PCESTHMT JOIN R1PROD.SPESTCND ON R1PROD.PCESTHMT.PROJECT_NO = R1PROD.SPESTCND.PROJECT_NO
//	    AND R1PROD.PCESTHMT.DEPT_ID = R1PROD.SPESTCND.DEPT_ID
//	    and R1PROD.SPESTCND.STATUS = 'A' AND  R1PROD.PCESTHMT.DEPT_ID = '423.50'

	
	private void setContractorsList()
	{
		
		SpestcntEjb ejb = new SpestcntEjb(getSessionKey("region"));
		this.ContractorList = ejb.getAll(getSessionKey("costCenterNo"));
		 if (this.ContractorList.size() > 0)
		 {
			 if (this.selectedContractorID == null)
				 this.selectedContractorID = this.ContractorList.get(0).getId().getContractorId();
		 }

	}

	public void setReports()
	{
		this.reportList = new ArrayList<ReportName>();
		this.pivReportList = new ArrayList<PIVReportType>();
		this.estimateStatusList = new ArrayList<EstimateStatusType>();

		

			ReportName rptName1 = new ReportName("SMC List", "R0001") ;
			ReportName rptName2 = new ReportName("Estimates (To be Approved)", "R0023") ;
			ReportName rptName3 = new ReportName("PIV Paid Report", "R0003") ;
			ReportName rptName4 = new ReportName("Application Register", "R0004") ;
			ReportName rptName5 = new ReportName("Jobs", "R0005") ;
			//ReportName rptName6 = new ReportName("Consumer Details", "R0006") ;
			ReportName rptName7 = new ReportName("Work In Progress - Age Analysis(Breakdowns)", "R0007") ;
			ReportName rptName8 = new ReportName("Work In Progress - Age Analysis(Summary)", "R0008") ;
			ReportName rptName9 = new ReportName("Finished Jobs - Material Code", "R0009") ;
			ReportName rptName10 = new ReportName("Finished Jobs - Job Number", "R0010") ;
//			ReportName rptName11 = new ReportName("Job Register", "R0011") ;
			//ReportName rptName13 = new ReportName("Loan PIV Report", "R0013") ;
			ReportName rptName14 = new ReportName("Connections Summary", "R0014") ;
			ReportName rptName15 = new ReportName("Job Register", "R0015") ;
			ReportName rptName16 = new ReportName("Contractor Jobs Summary", "R0016") ;
			ReportName rptName19 = new ReportName("Completed Jobs", "R0019") ;
			ReportName rptName21 = new ReportName("Allocated Jobs - Material Code", "R0021") ;
			ReportName rptName25 = new ReportName("Labour/Material Rates", "R0025") ;


			this.reportList.add(rptName1 );
			this.reportList.add( rptName2);
			this.reportList.add(rptName3);
			this.reportList.add( rptName4);
			this.reportList.add( rptName5);
			//this.reportList.add( rptName6);
 			this.reportList.add( rptName7);
 			this.reportList.add( rptName8);
			this.reportList.add( rptName9);
			this.reportList.add( rptName10);
			this.reportList.add( rptName21);
//			this.reportList.add( rptName11);
		//	this.reportList.add( rptName13);
			this.reportList.add( rptName14);
			this.reportList.add( rptName15);
			this.reportList.add( rptName16);
			this.reportList.add( rptName19);
			this.reportList.add( rptName25);
			

			if (this.selectedReport == null)
			{
				this.selectedReport = "R0001";
			}

			setToFalse();

			if (this.selectedReport != null)
			{

				if (this.selectedReport.equals("R0001"))
				{
					this.showCostCenter = true;
					this.showApplicationType = true;
				}
				if (this.selectedReport.equals("R0023")) // estimates
				{

					//					EstimateStatusType estStatus = new EstimateStatusType("NEW",EstimateStatus.NEW);
					//					this.estimateStatusList.add(estStatus);

					EstimateStatusType estStatus = new EstimateStatusType("SAVED",EstimateStatus.SAVED);
				//	this.estimateStatusList.add(estStatus);

//					estStatus = new EstimateStatusType("NEW",EstimateStatus.NEW);
//					this.estimateStatusList.add(estStatus);


					estStatus = new EstimateStatusType("By ES ",EstimateStatus.EST_APPROVAL_ES);
					this.estimateStatusList.add(estStatus);	

					estStatus = new EstimateStatusType("By EA",EstimateStatus.EST_APPROVAL_EA);
					this.estimateStatusList.add(estStatus);	

					estStatus = new EstimateStatusType("By EE",EstimateStatus.EST_APPROVAL_EE);
					this.estimateStatusList.add(estStatus);	
					
					estStatus = new EstimateStatusType("By CE",EstimateStatus.EST_APPROVAL_CE);
					this.estimateStatusList.add(estStatus);	


					estStatus = new EstimateStatusType("By DGM",EstimateStatus.EST_APPROVAL_DGM);
					this.estimateStatusList.add(estStatus);	

					estStatus = new EstimateStatusType("By AGM",EstimateStatus.EST_APPROVAL_AGM);
					this.estimateStatusList.add(estStatus);	

//					estStatus = new EstimateStatusType("REJECTED",EstimateStatus.EST_REJECTED);
//					this.estimateStatusList.add(estStatus);
//
//					estStatus = new EstimateStatusType("APPROVED",EstimateStatus.EST_APPROVED);
//					this.estimateStatusList.add(estStatus);

//					estStatus = new EstimateStatusType("POSTING",EstimateStatus.EST_POSTING);
//					this.estimateStatusList.add(estStatus);

//					estStatus = new EstimateStatusType("POSTED",EstimateStatus.EST_POSTED);
//					this.estimateStatusList.add(estStatus);



					this.showCostCenter = true;
					//	this.showStartDate = true;
					//	this.showEndDate = true;
					this.showEstimateStatusList = true;
				}
				if (this.selectedReport.equals("R0003")) // PIV Paid reports
				{
					PIVReportType reportType = new PIVReportType("Application-New Connection","ANC");
					this.pivReportList.add(reportType );	
					
					reportType = new PIVReportType("Estimate-New Connection","ENC");
					this.pivReportList.add(reportType );	
					
					reportType = new PIVReportType("Loan PIV","LOAN");
					this.pivReportList.add(reportType );	
					
					reportType = new PIVReportType("Reinspction PIV","RIP");
					this.pivReportList.add(reportType );	
					
					reportType = new PIVReportType("Cost Recovery Jobs","ECR");
					this.pivReportList.add(reportType );	
					
					reportType = new PIVReportType("Temporary Connection","ETC");
					this.pivReportList.add(reportType );	
					
					reportType = new PIVReportType("Unpaid PIV","UNP");
					this.pivReportList.add(reportType );	



					reportType = new PIVReportType("All PIV","ALLPIV");
					this.pivReportList.add(reportType );


					this.showCostCenter = true;
					this.showStartDate = true;
					this.showEndDate = true;
					this.showPIVTypes = true;
				}
				if (this.selectedReport.equals("R0004"))
				{
					this.showCostCenter = true;
					this.showStartDate = true;
					this.showEndDate = true;
				}
				if (this.selectedReport.equals("R0005"))
				{
					EstimateStatusType estStatus = new EstimateStatusType("ONGOING",EstimateStatus.JOB_ONGOING);
					this.estimateStatusList.add(estStatus);

					estStatus = new EstimateStatusType("RIVISION",EstimateStatus.JOB_RIVISION);
					this.estimateStatusList.add(estStatus);

					estStatus = new EstimateStatusType("APPROVAL (ES)",EstimateStatus.JOB_APPROVAL_ES);
					this.estimateStatusList.add(estStatus);	

					estStatus = new EstimateStatusType("APPROVAL (EA)",EstimateStatus.JOB_APPROVAL_EA);
					this.estimateStatusList.add(estStatus);	

					estStatus = new EstimateStatusType("APPROVAL (AE)",EstimateStatus.JOB_APPROVAL_EE);
					this.estimateStatusList.add(estStatus);	

					estStatus = new EstimateStatusType("APPROVAL (DGM)",EstimateStatus.JOB_APPROVAL_DGM);
					this.estimateStatusList.add(estStatus);	

					estStatus = new EstimateStatusType("APPROVAL (AGM)",EstimateStatus.JOB_APPROVAL_AGM);
					this.estimateStatusList.add(estStatus);	

					estStatus = new EstimateStatusType("REJECTED",EstimateStatus.JOB_REJECTED);
					this.estimateStatusList.add(estStatus);

					estStatus = new EstimateStatusType("APPROVED",EstimateStatus.JOB_APPROVED);
					this.estimateStatusList.add(estStatus);

					estStatus = new EstimateStatusType("SOFT CLOSED",EstimateStatus.JOB_SOFT_CLOSED);
					this.estimateStatusList.add(estStatus);

					estStatus = new EstimateStatusType("JOB HARD CLOSED",EstimateStatus.JOB_HARD_CLOSED);
					this.estimateStatusList.add(estStatus);

					estStatus = new EstimateStatusType("JOB EXPORTED",EstimateStatus.JOB_EXPORTED);
					this.estimateStatusList.add(estStatus);



					this.showCostCenter = true;
					//	this.showStartDate = true;
					//	this.showEndDate = true;
					this.showEstimateStatusList = true;
				}

				if (this.selectedReport.equals("R0006"))
				{

					this.showApplicationNumber = true;
				}

				if (this.selectedReport.equals("R0007"))
				{

					this.showCostCenter = true;
					this.showStartDate = true;
					this.showEndDate = true;
				}
				if (this.selectedReport.equals("R0008"))
				{

					this.showCostCenter = true;
					this.showStartDate = true;
					this.showEndDate = true;
				}
				if (this.selectedReport.equals("R0009") || this.selectedReport.equals("R0010") )
				{
					// this.projectNumberList =  new ArrayList<Pcesthmt>();
					this.showCostCenter = true;
					this.showStartDate = true;
					this.showEndDate = true;
					this.showJobNumberList = true;
				//	this.showContractorIDList = true;
				//	this.showjobAllocatedOrFinishedList = true;
					JobEjb jobEjb = new JobEjb(getSessionKey("region"));
					this.projectNumberList = jobEjb.getJobDetailListByDateRange(startDate, endDate, costCenter) ;
				}
				if (this.selectedReport.equals("R0011"))
				{

					this.showCostCenter = true;
					this.showStartDate = true;
					this.showEndDate = true;
				}
				if (this.selectedReport.equals("R0013"))
				{

					this.showCostCenter = true;
					this.showStartDate = true;
					this.showEndDate = true;
				}
				if (this.selectedReport.equals("R0014"))
				{

					this.showCostCenter = true;
					this.showStartDate = true;
					this.showEndDate = true;
				}
				
				if (this.selectedReport.equals("R0015"))
				{

					this.showCostCenter = true;
					this.showStartDate = true;
					this.showEndDate = true;
				}
				
				if (this.selectedReport.equals("R0016"))
				{

					this.showCostCenter = true;
					this.showStartDate = true;
					this.showEndDate = true;
					this.showContractorIDList = true;
				}
				if (this.selectedReport.equals("R0019"))
				{

					this.showCostCenter = true;
					this.showStartDate = true;
					this.showEndDate = true;
					//this.showContractorIDList = true;
				}
				if (this.selectedReport.equals("R0021"))
				{

					this.showCostCenter = true;
				//	this.showStartDate = true;
				//	this.showEndDate = true;
					this.showContractorIDList = true;
					this.showAllocatedJobsList = true;
					
					SpestcndEjb spejb=new SpestcndEjb(getSessionKey("region"));
					allocatedJobs =spejb.getJobList(selectedContractorID,costCenter,JobProcessStatus.ALLOCATED);
				}
				
				
				if (this.selectedReport.equals("R0025"))
				{

					this.showCostCenter = true;
				 
//					this.showContractorIDList = true;
//					this.showAllocatedJobsList = true;
					
//					SpestcndEjb spejb=new SpestcndEjb(getSessionKey("region"));
//					allocatedJobs =spejb.getJobList(selectedContractorID,costCenter,JobProcessStatus.ALLOCATED);
				}
			}


		 


	}



	public String Exit(){
		return "close";
	}

	
//	public String loadAllocatedJobsList()
//	{
//		execute();
//		SpestcndEjb spejb=new SpestcndEjb(getSessionKey("region"));
//		this.allocatedJobs = spejb.getJobList(selectedContractorID,costCenter,JobProcessStatus.ALLOCATED);		
//		return SUCCESS;
//	}
	public String loadJobNumberList()
	{
		execute();		
		JobEjb jobEjb = new JobEjb(getSessionKey("region"));		
		this.projectNumberList = jobEjb.getJobDetailListByDateRange(startDate, endDate, costCenter) ;		
		return SUCCESS;
	}
	public String getSessionKey(String key) {
		return getSession().get(key).toString();
	}

	public String View(){

		if (this.selectedReport == null)
		{
			execute();
			setLblError(this.selectCategoryString);
			return SUCCESS;
		}
		String rpt = getSelectedReport();
		report rept =  new report();
		execute();

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
		
		
		HashMap<String, Object> param = new HashMap<String, Object>();
		if (this.selectedReport.equals("R0001"))
		{
			param.put("costCenter", getCostCenter());
			//param.put("startDate", getStartDate());
			//param.put("endDate", getEndDate());
			param.put("applicationType", getSelectedApplicationType());
		}
		if (this.selectedReport.equals("R0002") || this.selectedReport.equals("R0005") ) // estimates
		{
			param.put("costCenter", getCostCenter() );

			System.out.println("............................xx:" + Integer.parseInt(getSelectedEstimateStatus()));
			param.put("estStatus", Integer.parseInt(getSelectedEstimateStatus()));
			EstimateStatus estStatus = new EstimateStatus();			
			/*param.put("rptName", estStatus.reportName(Integer.parseInt(getSelectedEstimateStatus())));
			System.out.println("............................xx:" + estStatus.reportName(Integer.parseInt(getSelectedEstimateStatus())) );*/
		}

		if (this.selectedReport.equals("R0003"))
		{
			param.put("@costctr", getCostCenter());
			param.put("@fromDate", getStartDate());
			param.put("@toDate", getEndDate());
			
			
			 


			String reportName = "";
			if ( getSelectedPIVReport().equals("ANC"))
			{
				rpt = "R0003";
			}

			if ( getSelectedPIVReport() .equals("ENC"))
			{
				rpt = "R0017";
				param.put("@PIVType", "%ENC%");
				param.put("rptName", "Estimate-New Connection");
				 
			}
			
			if ( getSelectedPIVReport() .equals("RIP"))
			{
				rpt = "R0017";
				param.put("@PIVType", "%RIP%");
				param.put("rptName", "Reinspection");
				 
			}
			
			if ( getSelectedPIVReport() .equals("ECR"))
			{
				rpt = "R0017";
				param.put("@PIVType", "%ECR%");
				param.put("rptName", "Cost Recovery");
				 
			}
			
			if ( getSelectedPIVReport() .equals("LOAN"))
			{
				rpt = "R0013";						 
			}

			
			if ( getSelectedPIVReport().equals( "UNP"))
			{
				rpt = "R0024";		
				 
			}
			
			
			if ( getSelectedPIVReport().equals( "ALLPIV"))
			{
				rpt = "R0018";		
				 
			}


			
			//param.put("applicationType", getApplicationType());
		}

		if (this.selectedReport.equals("R0004"))
		{
			param.put("@costctr", getCostCenter());
			param.put("@fromDate", getStartDate());
			param.put("@toDate", getEndDate());
			//param.put("applicationType", getApplicationType());
		}

		if (this.selectedReport.equals("R0006"))
		{			 
			param.put("@appno", getApplicatinNumber());

		}

		if (this.selectedReport.equals("R0007"))
		{			 
			param.put("costCenter", getCostCenter());
			param.put("fromDate", getStartDate());
			param.put("toDate", getEndDate());

		}

		if (this.selectedReport.equals("R0008"))
		{			 
			param.put("costCenter", getCostCenter());
			param.put("fromDate", getStartDate());
			param.put("toDate", getEndDate());

		}

		if (this.selectedReport.equals("R0009") ||this.selectedReport.equals("R0010") )
		{			

			String selectedJobNumberList = ""; // '1031/2008D','004/2006K ','005/2006K','007/2006K','1002/2008K','1009/2008K','1030/2008D'
			if (getPcesthmtID()!= null)
			{
				for (String key : getPcesthmtID()) {
					//	System.out.println("Key:" + key + "..");
					key = "'" +  key.trim() + "'";			
					if (key != "''")
					{
						selectedJobNumberList = selectedJobNumberList + "," + key;
					}
				}

				//System.out.println("xxxxxxxxxxxxxxx4444xxxxxxxxxxxxxxxxxx:" + selectedJobNumberList);
				selectedJobNumberList = selectedJobNumberList.substring(1,selectedJobNumberList.length());
				//	selectedJobNumberList = "'1031/2008D','004/2006K ','005/2006K','007/2006K','1002/2008K','1009/2008K','1030/2008D'";
				//	System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx:" + selectedJobNumberList); 
				param.put("costCenter", getCostCenter());
				param.put("projectList", selectedJobNumberList);
			}


		}

		if (this.selectedReport.equals("R0011"))
		{			 
			param.put("costCenter", getCostCenter());
			param.put("fromDate", getStartDate());
			param.put("toDate", getEndDate());

		}
		if (this.selectedReport.equals("R0013"))
		{			 
			param.put("@costctr", getCostCenter());
			param.put("@fromDate", getStartDate());
			param.put("@toDate", getEndDate());

		}
		
		if (this.selectedReport.equals("R0014"))
		{			 
			param.put("@costctr", getCostCenter());
			param.put("@fromDate", getStartDate());
			param.put("@toDate", getEndDate());
		}
		
		if (this.selectedReport.equals("R0015"))
		{			 
			param.put("costCenter", getCostCenter());
			param.put("fromDate", getStartDate());
			param.put("toDate", getEndDate());
		}
		
		if (this.selectedReport.equals("R0016"))
		{			 
			param.put("costCenter", getCostCenter());
			param.put("fromDate", getStartDate());
			param.put("toDate", getEndDate());
			param.put("contractorID", getSelectedContractorID());
			
		}
		
		if (this.selectedReport.equals("R0019"))
		{			 
			param.put("costCenter", getCostCenter());
			param.put("fromDate", getStartDate());
			param.put("toDate", getEndDate());
			param.put("contractorId", "");
			
		}
		
		if (this.selectedReport.equals("R0021"))
		{			 
			String selectedJobNumberList = "";  
			if (getSelectedAllocatedJobs()!= null)
			{
				for (String key : getSelectedAllocatedJobs()) {
					 
					key = "'" +  key.trim() + "'";			
					if (key != "''")
					{
						selectedJobNumberList = selectedJobNumberList + "," + key;
					}
				}
				selectedJobNumberList = selectedJobNumberList.substring(1,selectedJobNumberList.length());				 
				param.put("costCenter", getCostCenter());
				param.put("projectList", selectedJobNumberList);
			}
		}
		
		
		if (this.selectedReport.equals("R0023")  ) // estimates to be approved
		{
			SecurityEjb ejb=new SecurityEjb(getSessionKey("region"));
			 
			
			  String authCostCenters = "";
				 List<String> authCost = ejb.getAuthorizedCostCenters(loggedInUserId);
				 String authCostList[] = authCost.toArray(new String[0]);
					if (authCostList != null)
					{
						for (String key : authCostList) {
							 
							key = "'" +  key.trim() + "'";			
							if (key != "''")
							{
								authCostCenters = authCostCenters + "," + key;
							}
						}
						authCostCenters = authCostCenters.substring(1,authCostCenters.length());	
					}
			  
			  
			  param.put("costCenter", authCostCenters );
			 
			param.put("estStatus", Integer.parseInt(getSelectedEstimateStatus()));
			EstimateStatus estStatus = new EstimateStatus();			
			/*param.put("rptName", estStatus.reportName(Integer.parseInt(getSelectedEstimateStatus())));
			  param.put("SUBREPORT_DIR", REPORT_DIRECTORY );*/
			 
		}
		
		if (this.selectedReport.equals("R0025"))
		{			 
			param.put("costCenter", getCostCenter());
			  param.put("SUBREPORT_DIR", REPORT_DIRECTORY );
			
		}
		
		
		String fileName = rept.generateReport(rpt,param,getSession(),REPORT_DIRECTORY,EXPORT_REPORT_DIRECTORY);
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
	@Override
	public void setParameters(Map<String, String[]> parameters){
		// TODO Auto-generated method stub
		this.parameters=parameters;
	}



	public TreeNode getRootNode() {
		return rootNode;
	}
	public void setRootNode(TreeNode rootNode) {
		this.rootNode = rootNode;
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
			if (lblError == this.selectCategoryString)
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

	public List<ReportName> getReportList() {
		return reportList;
	}
	public void setReportList(List<ReportName> reportList) {
		this.reportList = reportList;
	}
	public String getSelectedReport() {
		return selectedReport;
	}
	public void setSelectedReport(String selectedReport) {
		this.selectedReport = selectedReport;
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

	public String getSelectedNode() {
		return selectedNode;
	}
	public void setSelectedNode(String selectedNode) {
		this.selectedNode = selectedNode;
	}
	public List<ReportCategory> getReportCategoryList() {
		return reportCategoryList;
	}
	public void setReportCategoryList(List<ReportCategory> reportCategoryList) {
		this.reportCategoryList = reportCategoryList;
	}
	public String getSelectedReportCategory() {
		return selectedReportCategory;
	}
	public void setSelectedReportCategory(String selectedReportCategory) {
		this.selectedReportCategory = selectedReportCategory;
	}



	public Date getStartDate() {
		return startDate;
	}



	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}



	public Date getEndDate() {
		return endDate;
	}



	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}



	 



	public Boolean getShowCostCenter() {
		return showCostCenter;
	}



	public void setShowCostCenter(Boolean showCostCenter) {
		this.showCostCenter = showCostCenter;
	}



	public Boolean getShowStartDate() {
		return showStartDate;
	}



	public void setShowStartDate(Boolean showStartDate) {
		this.showStartDate = showStartDate;
	}



	public Boolean getShowEndDate() {
		return showEndDate;
	}



	public void setShowEndDate(Boolean showEndDate) {
		this.showEndDate = showEndDate;
	}



	public Boolean getShowApplicationType() {
		return showApplicationType;
	}



	public void setShowApplicationType(Boolean showApplicationType) {
		this.showApplicationType = showApplicationType;
	}



	public String getApplicatinNumber() {
		return applicatinNumber;
	}



	public void setApplicatinNumber(String applicatinNumber) {
		this.applicatinNumber = applicatinNumber;
	}



	public Boolean getShowApplicationNumber() {
		return showApplicationNumber;
	}



	public void setShowApplicationNumber(Boolean showApplicationNumber) {
		this.showApplicationNumber = showApplicationNumber;
	}



	public Boolean getShowPIVTypes() {
		return showPIVTypes;
	}



	public void setShowPIVTypes(Boolean showPIVTypes) {
		this.showPIVTypes = showPIVTypes;
	}



	public List<PIVReportType> getPivReportList() {
		return pivReportList;
	}



	public void setPivReportList(List<PIVReportType> pivReportList) {
		this.pivReportList = pivReportList;
	}



	public String getSelectedPIVReport() {
		return selectedPIVReport;
	}



	public void setSelectedPIVReport(String selectedPIVReport) {
		this.selectedPIVReport = selectedPIVReport;
	}







	public String getSelectedEstimateStatus() {
		return selectedEstimateStatus;
	}



	public void setSelectedEstimateStatus(String selectedEstimateStatus) {
		this.selectedEstimateStatus = selectedEstimateStatus;
	}



	public List<EstimateStatusType> getEstimateStatusList() {
		return estimateStatusList;
	}



	public void setEstimateStatusList(List<EstimateStatusType> estimateStatusList) {
		this.estimateStatusList = estimateStatusList;
	}



	public Boolean getShowEstimateStatusList() {
		return showEstimateStatusList;
	}



	public void setShowEstimateStatusList(Boolean showEstimateStatusList) {
		this.showEstimateStatusList = showEstimateStatusList;
	}



	public String getSelectCategoryString() {
		return selectCategoryString;
	}



	public void setSelectCategoryString(String selectCategoryString) {
		this.selectCategoryString = selectCategoryString;
	}



	public Boolean getShowJobNumberList() {
		return showJobNumberList;
	}



	public void setShowJobNumberList(Boolean showJobNumberList) {
		this.showJobNumberList = showJobNumberList;
	}



	public List<Pcesthmt> getProjectNumberList() {
		return projectNumberList;
	}



	public void setProjectNumberList(List<Pcesthmt> projectNumberList) {
		this.projectNumberList = projectNumberList;
	}







	public String[] getPcesthmtID() {
		return pcesthmtID;
	}







	public void setPcesthmtID(String[] pcesthmtID) {
		this.pcesthmtID = pcesthmtID;
	}







	public List<Phase> getApplicationTypeList() {
		return applicationTypeList;
	}







	public void setApplicationTypeList(List<Phase> applicationTypeList) {
		this.applicationTypeList = applicationTypeList;
	}







	public String getSelectedApplicationType() {
		return selectedApplicationType;
	}







	public void setSelectedApplicationType(String selectedApplicationType) {
		this.selectedApplicationType = selectedApplicationType;
	}







	public Boolean getShowContractorIDList() {
		return showContractorIDList;
	}







	public void setShowContractorIDList(Boolean showContractorIDList) {
		this.showContractorIDList = showContractorIDList;
	}
	public Boolean getShowjobAllocatedOrFinishedList() {
		return showjobAllocatedOrFinishedList;
	}
	public void setShowjobAllocatedOrFinishedList(
			Boolean showjobAllocatedOrFinishedList) {
		this.showjobAllocatedOrFinishedList = showjobAllocatedOrFinishedList;
	}
	public List<Phase> getJobAllocatedOrFinishedList() {
		return jobAllocatedOrFinishedList;
	}
	public void setJobAllocatedOrFinishedList(List<Phase> jobAllocatedOrFinishedList) {
		this.jobAllocatedOrFinishedList = jobAllocatedOrFinishedList;
	}
	public String getSelectedAllocatedOrFinishedJob() {
		return selectedAllocatedOrFinishedJob;
	}
	public void setSelectedAllocatedOrFinishedJob(
			String selectedAllocatedOrFinishedJob) {
		this.selectedAllocatedOrFinishedJob = selectedAllocatedOrFinishedJob;
	}
	public List<Spestcnd> getAllocatedJobs() {
		return allocatedJobs;
	}
	public void setAllocatedJobs(List<Spestcnd> allocatedJobs) {
		this.allocatedJobs = allocatedJobs;
	}
	public Boolean getShowAllocatedJobsList() {
		return showAllocatedJobsList;
	}
	public void setShowAllocatedJobsList(Boolean showAllocatedJobsList) {
		this.showAllocatedJobsList = showAllocatedJobsList;
	}
	public String[] getSelectedAllocatedJobs() {
		return selectedAllocatedJobs;
	}
	public void setSelectedAllocatedJobs(String[] selectedAllocatedJobs) {
		this.selectedAllocatedJobs = selectedAllocatedJobs;
	}














	//	public String getReport_url() {
	//		return report_url;
	//	}
	//
	//	public void setReport_url(String report_url) {
	//		this.report_url = report_url;
	//	}


}
