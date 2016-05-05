package offDoc.web;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.SessionAware;

import util.common.Format;

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

public class estimageRequestForBulkSupply extends ActionSupport implements SessionAware, ParameterAware {

	private Map<String, Object> session;
	private Map <String, String[]> parameters;
	private String loggedInUserId;
	private static final String viewPath="Reference Letters>Request for bulk electricity supply";
	private String path;
	private String lblError = null;
	private String lblSuccess = null;

	private String costCenter;
	private String costCenterName;
	private String costCenterAddress;
	private String costCenterArea;
	private List<String> applicationNumberList;
	private String selectedAppNumber;
	private String loggedInUserLevel;
	private String customerName;
	private String customerAddress;
	private String streetAddress;
	private String suberb;
	private String city;
	private String postalCode;
	private String phase;
	private String appDate;
	private Date visitedDate;
	private String appForwardTo;
	private String appForwardLocation;
	private InputStream fileInputStream;
	
	public String execute(){
		setLblError(null);

		setLoggedInUserId(getSession().get("userName").toString());
		setPath(viewPath);
		if (this.visitedDate == null)
			this.visitedDate = new Date();
		
		setCostCenterDetials();
		
		setApplicationList();
		setApplicationDetails();
		if (! (this.selectedAppNumber ==null || this.selectedAppNumber.equals("Select Estimate")))
		{
			setBulkSupplyDetials();
		}else
		{
			
			this.customerName = "";
			this.customerAddress = "";
			this.phase = "";
			this.appDate = "";
			this.visitedDate = null;
		}
		
		setLoggedInUserLevel( getSessionKey("userRole"));
		return SUCCESS;
	}
	
	private void setBulkSupplyDetials()
	{
		// get the relavant bulk supply details for the loged cost center
		GldeptinEjb ejb = new GldeptinEjb(getSessionKey("region"));
		Gldeptin gldeptin =  ejb.findById(getSessionKey("costCenterNo"));
		this.appForwardTo = gldeptin.getBulkSupplierName();//"Electrical Engineer (Bulk Supply), Colombio City";
		this.appForwardLocation = gldeptin.getBulkSupplierAdd();//"4th floor, No 340 R A De Mel Mawatha, Colombo 03";
	}
	
	private void setCostCenterDetials()
	{
//		GldeptmEjb ejb = new GldeptmEjb(getSessionKey("region"));
//		Gldeptm gldeptm =  ejb.findGldeptm( getSessionKey("costCenterNo"));
		
		GldeptinEjb ejb = new GldeptinEjb(getSessionKey("region"));
		Gldeptin gldeptin =  ejb.findById(getSessionKey("costCenterNo"));
		
		setCostCenter(getSessionKey("costCenterNo"));
		setCostCenterName(gldeptin.getDeptFullName());
		setCostCenterAddress(gldeptin.getDeptAdd()  ); //   gldeptm.getAddress()
		setCostCenterArea(gldeptin.getDeptArea());
	}
	public String getSessionKey(String key) {
		return getSession().get(key).toString();
	}
	private void setApplicationList()
	{
		this.applicationNumberList = new  ArrayList<String>();

		ApplicationEjb ejb=new ApplicationEjb(getSessionKey("region"));		 
		List<Application> lst = ejb.getApplicationList(getCostCenter(), "A"); // .ge .getEstApprovalList(getCostCenter(), status:=A );
		this.applicationNumberList.add("Select Estimate");
		for (int i=0; i<=lst.size()-1;i++) {

			this.applicationNumberList.add(lst.get(i).getApplicationNo().trim());
		} 
	}
	private void setApplicationDetails()
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

	}
	
	public String Exit(){
		return "closed";
	}
	
	public String ViewPDF()
	{
	 
		
		PDFRequestForBulkSupply print = new PDFRequestForBulkSupply(this.costCenterName,this.costCenterArea,this.costCenterAddress ,
										this.selectedAppNumber,this.customerName,this.streetAddress,this.suberb,this.city,this.postalCode,
										this.phase,this.appDate,this.visitedDate,
										this.appForwardTo,this.appForwardLocation);
		 
		String fileName = print.PrintPDF();		
		 
		try {
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

	public String getCostCenterArea() {
		return costCenterArea;
	}

	public void setCostCenterArea(String costCenterArea) {
		this.costCenterArea = costCenterArea;
	}

	public List<String> getApplicationNumberList() {
		return applicationNumberList;
	}

	public void setApplicationNumberList(List<String> applicationNumberList) {
		this.applicationNumberList = applicationNumberList;
	}

	public String getSelectedAppNumber() {
		return selectedAppNumber;
	}

	public void setSelectedAppNumber(String selectedAppNumber) {
		this.selectedAppNumber = selectedAppNumber;
	}

	public String getLoggedInUserLevel() {
		return loggedInUserLevel;
	}

	public void setLoggedInUserLevel(String loggedInUserLevel) {
		this.loggedInUserLevel = loggedInUserLevel;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
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

	public Map<String, Object> getSession() {
		return session;
	}

	public Map<String, String[]> getParameters() {
		return parameters;
	}
	public String getAppForwardTo() {
		return appForwardTo;
	}
	public void setAppForwardTo(String appForwardTo) {
		this.appForwardTo = appForwardTo;
	}
	public String getAppForwardLocation() {
		return appForwardLocation;
	}
	public void setAppForwardLocation(String appForwardLocation) {
		this.appForwardLocation = appForwardLocation;
	}

	public InputStream getFileInputStream() {
		return fileInputStream;
	}

	public void setFileInputStream(InputStream fileInputStream) {
		this.fileInputStream = fileInputStream;
	}

	
}
