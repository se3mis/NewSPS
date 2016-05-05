package report.web;

import report.service.ReportEjb;
import report.model.ReportSps;
import report.model.ReportSpsPara;
import security.service.SecurityEjb;
import reports.web.report;





import java.text.ParseException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.HashMap;
import java.util.Map;


import job.model.Spestcnt;
import job.service.SpestcntEjb;
import masters.service.ProvinceDetailsMasterEjb;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRRuntimeException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;

import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.SessionAware;

import util.common.ApplicationType;
import util.common.EstimateStatus;
import util.common.Format;
import util.common.JobProcessStatus;
import util.common.Path;
import util.common.Phase;

//import antlr.collections.List;




import application.service.ApplicationTransactionEjb;

import com.opensymphony.xwork2.ActionSupport;

import estimate.model.EstimateReference;

@SuppressWarnings("serial")
public class Para extends ActionSupport implements SessionAware, ParameterAware {
	private Map<String, Object> session;
	private Map<String, String[]> parameters;
	static String idd;
	
	
	

	// JSP Fields
	private String path;
	private String reportName;
	private String nodeId;
	private String txt;
	private String costCenterNo;	
	private String region;	
	private String lblError;
	private String selectCategoryString;
	private String lblSuccess;
	private InputStream fileInputStream;
	
	
	
	//parameters
	private String costctr;
	private String dateFrom;
	private String dateTo;
	private String fundId;	
	private String es;
	private String electorate;
	private String csc;
	private String area;
	private String fromNo;	
	private String toNo;
	private String district;
	private String contractor;
	
	
	//lists
	private List<String> fundSourceList;
	private List<String> districtCodeList;
	private List<String> areaCodeList;
	private List<String> electorateList;
	private List<String> esUserList;
	private List<String> CSCList;
	private List<String> listContractor;

	
	// -----------------------------new variables for display parameters------------------
	
	private String costCenterVisible = "false";
	private String dateFromVisible = "false";
	private String dateToVisible = "false";
	private String fundIdVisible = "false";
	private String contractorVisible = "false";
	private String eSVisible = "false";
	private String districtVisible = "false";
	private String cSCVisible = "false";
	private String electroateVisible = "false";
	private String areaVisible = "false";
	private String fromNoVisible = "false";
	private String toNoVisible = "false";
	
	
	public String execute() {
		this.setCostctr(getSessionKey("costCenterNo"));
		
		reportsMethodName();
		

		System.err.println("execute()");
		
		return SUCCESS;
	}

	public String report1MethodName() {
		setPath("Report1");// EnterName Of the report
		/*setVisibleCostCenter("true");
		setVisibleApplicationType("true");*/
		System.err.println("reportName " + parameters.get(nodeId));
		
		return SUCCESS;
	}
	
	
	
	
	
	
	
	//********************************************************************************************
	//----------------------------Method for create para page.jsp according 2 selected node

	public String reportsMethodName() {
		
		
		idd = idd;
		reportName=idd;
		System.out.println("id is " + idd);
		
		setLblError(null);
		this.setCostctr(this.getSessionKey("costCenterNo"));
		System.out.println(this.costctr+"111111111111111111111111111");
		System.out.println("--------------------1------------------------");
		
		
		
		

		ReportEjb reprotEjb = new ReportEjb("region");
		System.out.println("--------------------2------------------------");
		ReportSps report = new ReportSps();
		report = reprotEjb.getReport(idd);
		setPath(report.getReportName());
		
		List<ReportSpsPara> paraId = new ArrayList<ReportSpsPara>();
		paraId = reprotEjb.getReportParaList(idd);
		//System.out.println(paraId+"000000000000000000000000000000000000000000000000");


		// ----------------Display parameters---------------------------
		System.out.println("--------------------3------------------------");
			for (int i = 0; i < paraId.size(); i++) {

				if (paraId.get(i).getId().getParaId().equals("1"))//Display costcenter
				{
					this.setCostCenterVisible("true");
				}
				if (paraId.get(i).getId().getParaId().equals("2"))//Display start day
				{
					this.setDateFromVisible("true");
				}
				if (paraId.get(i).getId().getParaId().equals("3"))//Display end day
				{
					this.setDateToVisible("true");
				}
				if (paraId.get(i).getId().getParaId().equals("4"))//Display FundId
				{
					this.setFundIdVisible("true");
					setFundSourceList();
					System.out.println("--------------------2------------------------");
				}			
				if (paraId.get(i).getId().getParaId().equals("5"))//Display Contractor
				{
					this.setContractorVisible("true");

					setContractorsList();
					System.out.println("--------------------7------------------------");
				}
				if(paraId.get(i).getId().getParaId().equals("6"))//Display ES
				{
					this.seteSVisible("true");
					setESList();
					System.out.println("--------------------4------------------------");
				}			 
				if(paraId.get(i).getId().getParaId().equals("7"))//Display District
				{
					this.setDistrictVisible("true");
					setDistrictCodeList();
					System.out.println("--------------------1------------------------");
				}
				if (paraId.get(i).getId().getParaId().equals("8"))//Display CSC
				{
					this.setcSCVisible("true");
					setCSCList();
					System.out.println("--------------------5------------------------");
				}
				if(paraId.get(i).getId().getParaId().equals("9"))//Display Electorate
				{
					this.setElectroateVisible("true");
					setElectorateList();
					System.out.println("--------------------3------------------------");
				}			 
				if (paraId.get(i).getId().getParaId().equals("10"))//Display Area
				{
					this.setAreaVisible("true");
					setAreaCodeList();		
					System.out.println("--------------------6------------------------");
				}			
				if (paraId.get(i).getId().getParaId().equals("11"))//Display FromNo
				{
					this.setFromNoVisible("true");
				}			
				if (paraId.get(i).getId().getParaId().equals("12"))//Display ToNo
				{
					this.setToNoVisible("true");
				}
				

			}
		

		System.err.println("reportName " + parameters.get(nodeId));
		System.err.println("reportName det " );
		return SUCCESS;
	}
	
	
	
	

	

	
	// ---------------------------Function for view selected report--------------------------------------
	
	public String view() throws ParseException, JRException {
		if( idd.equals(null))
		{
			execute();
			setLblError(this.selectCategoryString);
			return SUCCESS;
		}
		
		String reportId = idd;

		reportsMethodName();

		Path path = new Path();		
		report rept = new report();
		String REPORT_DIRECTORY = "" ;
		String EXPORT_REPORT_DIRECTORY = "";
		if (System.getProperty("os.name").equals("Windows XP") || System.getProperty("os.name").equals("Windows 7")|| System.getProperty("os.name").equals("Windows 8.1 Enterprice")){
			REPORT_DIRECTORY = path.getReportPath("REPORT_DIRECTORY");
			EXPORT_REPORT_DIRECTORY = path.getReportPath("EXPORT_REPORT_DIRECTORY");	
			System.out.println("--------------------A------------------------");
		}else{
			REPORT_DIRECTORY = path.getReportPath("REPORT_DIRECTORY_LINUX");
			EXPORT_REPORT_DIRECTORY = path.getReportPath("EXPORT_REPORT_DIRECTORY_LINUX");		
			System.out.println("--------------------B------------------------");
		}		
		HashMap<String, Object> param = new HashMap<String, Object>();
		if(this.getCostCenterVisible().equals("true"))
			{
			param.put("@costctr", "'"+this.getCostctr()+"'");
			System.out.println("'"+this.getCostctr()+"'");
			}
		if(this.getFundIdVisible().equals("true"))
			{
			param.put("@fundId", "'"+this.getFundId()+"'");
			System.out.println("'"+this.getFundId()+"'");
			}
		 
		if(this.getDateFromVisible().equals("true"))
			{
			System.out.println(this.dateFromVisible+"9999999999999999999999999999");
			
				param.put("@fromDate", "'"+this.getDateFrom()+"'");
				System.out.println("'"+this.getDateFrom()+"'");
			}
		if(this.getDateToVisible().equals("true"))
			{
			System.out.println(this.dateToVisible+"9999999999999999999999999999");
			
				param.put("@toDate", "'"+this.getDateTo()+"'");
				
				System.out.println("'"+this.getDateTo()+"'");
			}
		if(this.getContractorVisible().equals("true"))
			{
			param.put("@contractor", "'"+this.getContractor()+"'");
			System.out.println("'"+this.getContractor()+"'");
			}
		if(this.geteSVisible().equals("true"));
			{
			param.put("@es", "'"+this.getEs()+"'");
			System.out.println("'"+this.getEs()+"'");
			}
		if(this.getDistrictVisible().equals("true"))
			{
			param.put("@district", "'"+this.getDistrict()+"'");
			System.out.println("'"+this.getDistrict()+"'");
			}
		if(this.getcSCVisible().equals("true"))
			{
			param.put("@csc", "'"+this.getCsc()+"'");
			System.out.println("'"+this.getCsc()+"'");
			}
		if(this.getElectroateVisible().equals("true"))
			{
			param.put("@electorate", "'"+this.getElectorate()+"'");
			System.out.println("'"+this.getElectorate()+"'");
			}
		if(this.getAreaVisible().equals("true"))
			{
			param.put("@compId", "'"+this.getArea()+"'");
			System.out.println("'"+this.getArea()+"'");
			}
		if(this.getFromNoVisible().equals("true"))
			{
			param.put("@fromNo", "'"+this.getFromNo()+"'");
			System.out.println("'"+this.getFromNo()+"'");
			}
		if(this.getToNoVisible().equals("true"))
			{
			param.put("@toNo", "'"+this.getToNo()+"'");
			System.out.println("'"+this.getToNo()+"'");
			}
		String reportName=idd.toString();
		System.out.println(reportName+"/////////////////////////////////");
		String fileName = rept.generateReport(reportName,param,getSession(),REPORT_DIRECTORY,EXPORT_REPORT_DIRECTORY);	
/*	System.out.println(reportName+"reportName /////////////////////////////////");
		System.out.println(param+"param /////////////////////////////////");
		System.out.println(getSession()+"getSession() /////////////////////////////////");
		System.out.println(REPORT_DIRECTORY+"REPORT_DIRECTORY /////////////////////////////////");
		System.out.println(EXPORT_REPORT_DIRECTORY+"EXPORT_REPORT_DIRECTORY /////////////////////////////////");*/
		
		
		try {
			if (fileName == "")
			{
				setLblError("Error occured while generating the report");				
				return SUCCESS;
			}
			fileInputStream = new FileInputStream(fileName);
		} catch (FileNotFoundException e) {			
			e.printStackTrace();
			setLblError(e.getMessage().toString());
			return SUCCESS;
			
		}
		this.setLblSuccess("Successfully Reporte Generated");
		return "successprint";
		
		
	}
	///*****************************************************************************************************************
		
	public String getSessionKey(String key) {
		return getSession().get(key).toString();
	}

	public Map<String, Object> getSession() {
		return session;

	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public Map<String, String[]> getParameters() {
		return parameters;
	}

	@Override
	public void setParameters(Map<String, String[]> parameters) {
		this.parameters = parameters;

	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getReportName() {
		return reportName;
	}

	public void setReportName(String reportName) {
		this.reportName = reportName;
	}

	public String getTxt() {
		return txt;
	}

	public void setTxt(String txt) {
		this.txt = txt;
	}

	public String getCostCenterNo() {
		return costCenterNo;
	}

	public void setCostCenterNo(String costCenterNo) {
		this.costCenterNo = costCenterNo;
	}

	public String getNodeId() {
		return nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	

	

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}



	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	
	public InputStream getFileInputStream() {
		return fileInputStream;
	}

	public void setFileInputStream(InputStream fileInputStream) {
		this.fileInputStream = fileInputStream;
	}

	public void setIdd(String idd) {
		this.idd = idd;

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

	

	

	public String getCostCenterVisible() {
		return costCenterVisible;
	}

	public void setCostCenterVisible(String costCenterVisible) {
		this.costCenterVisible = costCenterVisible;
	}

	public String getDateFromVisible() {
		return dateFromVisible;
	}

	public void setDateFromVisible(String dateFromVisible) {
		this.dateFromVisible = dateFromVisible;
	}

	public String getDateToVisible() {
		return dateToVisible;
	}

	public void setDateToVisible(String dateToVisible) {
		this.dateToVisible = dateToVisible;
	}

	public String getFundIdVisible() {
		return fundIdVisible;
	}

	public void setFundIdVisible(String fundIdVisible) {
		this.fundIdVisible = fundIdVisible;
	}

	public String getContractorVisible() {
		return contractorVisible;
	}

	public void setContractorVisible(String contractorVisible) {
		this.contractorVisible = contractorVisible;
	}

	public String geteSVisible() {
		return eSVisible;
	}

	public void seteSVisible(String eSVisible) {
		this.eSVisible = eSVisible;
	}

	public String getDistrictVisible() {
		return districtVisible;
	}

	public void setDistrictVisible(String districtVisible) {
		this.districtVisible = districtVisible;
	}

	public String getcSCVisible() {
		return cSCVisible;
	}

	public void setcSCVisible(String cSCVisible) {
		this.cSCVisible = cSCVisible;
	}

	public String getElectroateVisible() {
		return electroateVisible;
	}

	public void setElectroateVisible(String electroateVisible) {
		this.electroateVisible = electroateVisible;
	}

	public String getAreaVisible() {
		return areaVisible;
	}

	public void setAreaVisible(String areaVisible) {
		this.areaVisible = areaVisible;
	}

	public String getFromNoVisible() {
		return fromNoVisible;
	}

	public void setFromNoVisible(String fromNoVisible) {
		this.fromNoVisible = fromNoVisible;
	}

	public String getToNoVisible() {
		return toNoVisible;
	}

	public void setToNoVisible(String toNoVisible) {
		this.toNoVisible = toNoVisible;
	}

	public String getCostctr() {
		return costctr;
	}

	public void setCostctr(String costctr) {
		this.costctr = costctr;
	}

	

	public String getFundId() {
		return fundId;
	}

	public void setFundId(String fundId) {
		this.fundId = fundId;
	}

	public String getEs() {
		return es;
	}

	public void setEs(String es) {
		this.es = es;
	}

	public String getElectorate() {
		return electorate;
	}

	public void setElectorate(String electorate) {
		this.electorate = electorate;
	}

	public String getCsc() {
		return csc;
	}

	public void setCsc(String csc) {
		this.csc = csc;
	}

	public String getFromNo() {
		return fromNo;
	}

	public void setFromNo(String fromNo) {
		this.fromNo = fromNo;
	}

	public String getToNo() {
		return toNo;
	}

	public void setToNo(String toNo) {
		this.toNo = toNo;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getContractor() {
		return contractor;
	}

	public void setContractor(String contractor) {
		this.contractor = contractor;
	}

	private void setESList()
	{
		SecurityEjb secejb=new SecurityEjb(getSessionKey("region"));		
		List<String> usersList = new ArrayList<String>();
		usersList.add("ES");		
		setEsUserList(secejb.getUserList(getCostctr(),usersList));	
	}
	
	private void setCSCList()
	{
		ProvinceDetailsMasterEjb provinceDetailsMasterEjb=new ProvinceDetailsMasterEjb();		     
		CSCList = provinceDetailsMasterEjb.getAllCodes("CSC",getCostctr(), getSessionKey("region"));
	   
	}	
	
	private void setElectorateList()
	{		
		ProvinceDetailsMasterEjb provinceDetailsMasterEjb=new ProvinceDetailsMasterEjb();		     
		electorateList = provinceDetailsMasterEjb.getAllCodes("ELECTORATE", getCostctr(), getSessionKey("region"));	    
	}
	
	private void setContractorsList()
	{		
		SpestcntEjb ejb=new SpestcntEjb(getSessionKey("region"));	
		listContractor= new  ArrayList<String>();		
		List<Spestcnt> spestcntList=new ArrayList<Spestcnt>();		
		String costCenter = getSessionKey("costCenterNo");
		spestcntList=ejb.getContractorByStatus(costCenter, "1");
		
		Iterator<Spestcnt> it1 = spestcntList.iterator();
		
		 while (it1.hasNext()) {  
			 Spestcnt spestcnt1=it1.next();
			 listContractor.add(spestcnt1.getContractorName());	
			
			 				        	        	
	       }  
	}
	
	private void setDistrictCodeList()
	{		
		    ProvinceDetailsMasterEjb provinceDetailsMasterEjb=new ProvinceDetailsMasterEjb();		     
		    districtCodeList = provinceDetailsMasterEjb.getAllCodes("DISTRICT", getCostctr(), getSessionKey("region"));		   
	}
	
	private void setFundSourceList()
	{		
		ApplicationTransactionEjb transactionEjb=new ApplicationTransactionEjb(getSessionKey("region"));
		fundSourceList = transactionEjb.getApplicationTypes(getCostctr(), getSessionKey("region"));	   
	}
	
	private void setAreaCodeList()
	{		
			ApplicationTransactionEjb transactionEjb=new ApplicationTransactionEjb(getSessionKey("region"));
			areaCodeList = transactionEjb.findAreaCodeNames(this.getCostctr(), getSessionKey("region"));
			System.out.println("areaCodeList : "+areaCodeList);
	}

	public static void main(String[]args)
	{
		try{
		Para para=new Para();
		para.setContractorsList();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public List<String> getFundSourceList() {
		return fundSourceList;
	}

	public void setFundSourceList(List<String> fundSourceList) {
		this.fundSourceList = fundSourceList;
	}

	public List<String> getDistrictCodeList() {
		return districtCodeList;
	}

	public void setDistrictCodeList(List<String> districtCodeList) {
		this.districtCodeList = districtCodeList;
	}

	public List<String> getAreaCodeList() {
		return areaCodeList;
	}

	public void setAreaCodeList(List<String> areaCodeList) {
		this.areaCodeList = areaCodeList;
	}

	public List<String> getElectorateList() {
		return electorateList;
	}

	public void setElectorateList(List<String> electorateList) {
		this.electorateList = electorateList;
	}

	public List<String> getEsUserList() {
		return esUserList;
	}

	public void setEsUserList(List<String> esUserList) {
		this.esUserList = esUserList;
	}

	public List<String> getCSCList() {
		return CSCList;
	}

	public void setCSCList(List<String> cSCList) {
		CSCList = cSCList;
	}

	public List<String> getListContractor() {
		return listContractor;
	}

	public void setListContractor(List<String> listContractor) {
		this.listContractor = listContractor;
	}

	public String getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(String dateFrom) {
		this.dateFrom = dateFrom;
	}

	public String getDateTo() {
		return dateTo;
	}

	public void setDateTo(String dateTo) {
		this.dateTo = dateTo;
	}

	

	

}
