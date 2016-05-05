package contractor.web;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import job.model.BillDetail;
import job.service.SpestcndEjb;

 


import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.SessionAware;

import reports.web.report;

import util.common.Path;


import com.opensymphony.xwork2.ActionSupport;

public class ContractorBillManage extends ActionSupport implements SessionAware, ParameterAware  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map<String, Object> session;
	private Map <String, String[]> parameters;
	private String loggedInUserId;
	private static final String viewPath="Manage Jobs>Bill Contractor Manage";	
	private String path;
	private String lblError = null;
	private String lblSuccess = null;

	private String costCenter;
	private List<String> BillList;
	private String selectedBillID; 

	private List<BillDetail> billedJobs;


	private Date startDate;
	private Date endDate; 


	private InputStream fileInputStream;



	public String Close(){
		return "close";
	}




	public String execute(){
		setLblError(null);
		setCostCenter(getSessionKey("costCenterNo"));
		setPath(viewPath);
		if (getStartDate() == null)
			startDate = new Date();
		if (getEndDate() == null)
			endDate = new Date();

		setBillList();
		setBilledJobs();
		 
		 
 		return SUCCESS;
	}

	public String setBillList()
	{
		setCostCenter(getSessionKey("costCenterNo"));
		SpestcndEjb ejb = new SpestcndEjb(getSessionKey("region"));
		this.BillList = ejb.getBillNoList(costCenter,getStartDate() , getEndDate())   ;
		if (this.BillList.size() > 0)
		{
			if (this.selectedBillID == null)
				this.selectedBillID = this.BillList.get(0);
		}
		setBilledJobs();
		return SUCCESS;
	}



	public void setBilledJobs()
	{
		SpestcndEjb spejb=new SpestcndEjb(getSessionKey("region"));		
		billedJobs =spejb.getBillDetailByBillNo(selectedBillID)  ;
	}








	public void setLblError(String lblError) {
		this.lblError = lblError;
	}





	public String Exit(){
		return "close";
	} 


	public String getSessionKey(String key) {
		return getSession().get(key).toString();
	}


	public String  deleteBill()	
	{
		setCostCenter(getSessionKey("costCenterNo"));
		SpestcndEjb spejb=new SpestcndEjb(getSessionKey("region"));		
		spejb.removeBillDetailListByBillNo(selectedBillID, costCenter);
		execute();
		setLblSuccess("Bill deleted!");
		return  SUCCESS;
	}
	public String  printBill()	
	{


		return DownloadBill(selectedBillID);



		
		
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





























	public List<String> getBillList() {
		return BillList;
	}




	public void setBillList(List<String> billList) {
		BillList = billList;
	}




	public String getSelectedBillID() {
		return selectedBillID;
	}




	public void setSelectedBillID(String selectedBillID) {
		this.selectedBillID = selectedBillID;
	}




	public List<BillDetail> getBilledJobs() {
		return billedJobs;
	}




	public void setBilledJobs(List<BillDetail> billedJobs) {
		this.billedJobs = billedJobs;
	}




	public InputStream getFileInputStream() {
		return fileInputStream;
	}




	public void setFileInputStream(InputStream fileInputStream) {
		this.fileInputStream = fileInputStream;
	}


}




