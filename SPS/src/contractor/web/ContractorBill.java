package contractor.web;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import job.model.BillDetail;
import job.model.Pcesthmt;
import job.model.Spestcnd;
import job.model.Spestcnt;
import job.service.JobEjb;
import job.service.SpestcndEjb;
import job.service.SpestcntEjb;

import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.SessionAware;

import reports.web.ReportCategory;
import reports.web.ReportName;
import reports.web.report;
import security.service.SecurityEjb;
import util.common.JobProcessStatus;
import util.common.Path;

import applicationPrint.Format;

import com.opensymphony.xwork2.ActionSupport;

public class ContractorBill  extends ActionSupport implements SessionAware, ParameterAware  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map<String, Object> session;
	private Map <String, String[]> parameters;
	private String loggedInUserId;
	private static final String viewPath="Manage Jobs>Bill Contractor";	
	private String path;
	private String lblError = null;
	private String lblSuccess = null;

	private String costCenter;
	private List<Spestcnt> ContractorList;
	private String selectedContractorID; 
	private List<Spestcnd> finshedJobs;
	private String[] selectedFinishedJobs;


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


	private InputStream fileInputStream;

	public InputStream getFileInputStream() {
		return fileInputStream;
	}







	public List<Spestcnd> getFinshedJobs() {
		return finshedJobs;
	}
	public void setFinshedJobs(List<Spestcnd> finshedJobs) {
		this.finshedJobs = finshedJobs;
	}
	public String[] getSelectedFinishedJobs() {
		return selectedFinishedJobs;
	}
	public void setSelectedFinishedJobs(String[] selectedFinishedJobs) {
		this.selectedFinishedJobs = selectedFinishedJobs;
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
		setContractorsList();
		setReports();
		return SUCCESS;
	}





	public void setReports()
	{
		System.out.println("Hiii : " + selectedContractorID + " cs : "+ costCenter);
		SpestcndEjb spejb=new SpestcndEjb(getSessionKey("region"));
		finshedJobs =spejb.getJobList(selectedContractorID,costCenter,JobProcessStatus.ALLOCATED);
		//finshedJobs =spejb.getJobList(selectedContractorID,costCenter);
		System.out.println("Hiii : "+ finshedJobs );

	}








	public void setLblError(String lblError) {
		this.lblError = lblError;
	}
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




	public String Exit(){
		return "close";
	} 


	public String getSessionKey(String key) {
		return getSession().get(key).toString();
	}

	public String  createBill()	
	{
		if (getSelectedFinishedJobs()!= null)
		{
			Format format = new Format();
			setCostCenter(getSessionKey("costCenterNo"));
			SpestcndEjb ejb = new SpestcndEjb(getSessionKey("region"));
			String billNumber = ejb.createBillDetailAutoId(getSelectedFinishedJobs(), getCostCenter(),getSession().get("userName").toString(), 	"BIL" + getCostCenter() + "" +   format.getYear() + "",selectedContractorID );
			setLblSuccess("Bill has successfully created. Bill number is :" + billNumber);
			
			return DownloadBill(billNumber);
			
		} 

		execute();
		return  SUCCESS;
	}
	public String DownloadBill(String billNumber ){


		HashMap<String, Object> param = new HashMap<String, Object>();

		report rept =  new report();
		setCostCenter(getSessionKey("costCenterNo"));
		param.put("costCenter", getCostCenter());
		param.put("billNumber", "'" + billNumber + "'");


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

		String fileName = rept.generateReport("R0022",param,getSession(),REPORT_DIRECTORY,EXPORT_REPORT_DIRECTORY);
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
		
		execute();
		return "successprint";


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
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getLblError() {
		return lblError;
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







}
