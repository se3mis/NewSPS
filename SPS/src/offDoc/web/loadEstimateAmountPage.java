package offDoc.web;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import offDoc.model.LetterReason;
import offDoc.model.LetterReasonPK;
import offDoc.service.OffDocsEjb;

import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.SessionAware;

import piv.service.PivDetailEjb;

 

import util.common.EstimateStatus;
import util.common.Format;
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

import estimate.model.Pcesthtt;
import estimate.model.Speststd;
import estimate.service.PcesthttEjb;
import estimate.service.SpeststdEjb;

@SuppressWarnings("serial")
public class loadEstimateAmountPage extends ActionSupport implements SessionAware, ParameterAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map<String, Object> session;
	private Map <String, String[]> parameters;
	private String loggedInUserId;
	private static final String viewPath="Letters>Estimate Quotation";
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
	private String totalCost;
	private String customerName;
	private String customerAddress;
	private String streetAddress;
	private String suberb;
	private String city;
	private String postalCode;
	private String phase;
	private String appDate;
	private String securityDeposit;
	 
 


	 
	private InputStream fileInputStream;
	
	public String execute(){
		setLblError(null);

		setLoggedInUserId(getSession().get("userName").toString());
		setPath(viewPath);
		 
		
		setCostCenterDetials();
		
		setEstimateList();
		setEstimateDetails();
		if (  (this.selectedAppNumber ==null || this.selectedAppNumber.equals("Select Estimate")))
		{
			 
			this.customerName = "";
			this.customerAddress = "";
			this.phase = "";
			this.appDate = "";
		} 
			 
		 
		
			 
		 
		
		setLoggedInUserLevel( getSessionKey("userRole"));
		
		return SUCCESS;
	}

	private void setCostCenterDetials()
	{
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
	private void setEstimateList()
	{
		this.applicationNumberList = new  ArrayList<String>();

		 PcesthttEjb ejbX = new PcesthttEjb(getSessionKey("region"));	
		List<Pcesthtt> lst  = ejbX.getEstimateList(getCostCenter(), new Long(EstimateStatus.EST_APPROVED));
		
		 
		this.applicationNumberList.add("Select Estimate");
		for (int i=0; i<=lst.size()-1;i++) {

			this.applicationNumberList.add(lst.get(i).getId().getEstimateNo().trim());
		} 
	}
	
	 
	 
	public String Exit(){
		return "closed";
	}
	
	
	
	public String GenerateLetter() throws FileNotFoundException
	{
	 
		 
		PDFEstimateAmount print = new PDFEstimateAmount(this.costCenterName,this.costCenterArea,this.costCenterAddress ,
										this.selectedAppNumber,this.customerName,this.streetAddress,this.suberb,this.city,this.postalCode,
										this.phase,this.appDate,this.totalCost,this.securityDeposit );
		
		String fileName = print.PrintPDF();	
		fileInputStream = new FileInputStream(fileName);
		return "successprint";
		 
	}
	
	

	private void setEstimateDetails()
	{


		if (this.selectedAppNumber ==null || this.selectedAppNumber.equals("Select Estimate"))
		{
			setLblError("Select Estimate");
			return;
		}else
		{
			SpeststdEjb stdEjb = new SpeststdEjb(getSessionKey("region"));
			Speststd speststd = null;
			 
			try
			{
				speststd = stdEjb.findByEstimateNo(this.selectedAppNumber);
			}catch(Exception e1)
			{
				setLblError("Estimate details not found");
				//setLblError(e1.toString());
				return;
			}		
			Format ft = new Format();
			
			String tCost = "Rs. " +  ft.fromatCurrency(speststd.getTotalCost());
			setTotalCost(tCost);
			String secDep = "Rs. " +  ft.fromatCurrency(speststd.getSecurityDeposit()); 
			setSecurityDeposit(secDep);
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
			
			 
			try
			{
				 String tempCusAddress = "";
				setStreetAddress(wlDetail.getServiceStreetAddress() );
				tempCusAddress += wlDetail.getServiceStreetAddress();
				setSuberb(wlDetail.getServiceSuburb());
				tempCusAddress += ", " + wlDetail.getServiceSuburb();
				if (wlDetail.getServiceCity() != null)
				{
					setCity( wlDetail.getServiceCity());
					tempCusAddress += ", " + wlDetail.getServiceCity();
				}
				else
					setCity("");
				 
				if (wlDetail.getServicePostalCode() != null)
				{
					setPostalCode( wlDetail.getServicePostalCode().toString());
					tempCusAddress += ", " +  wlDetail.getServicePostalCode().toString();
				}
				else
					setPostalCode( "");
				
				setCustomerAddress(tempCusAddress  );
				 
			}catch(Exception e4)
			{
				setLblError("Error in applicant address");
			}

			
			 
			setAppDate(ft.formatDate(application.getSubmitDate()));
			
			 
			 
		}

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



	public List<String> getApplicationNumberList() {
		return applicationNumberList;
	}

	public void setApplicationNumberList(List<String> applicationNumberList) {
		this.applicationNumberList = applicationNumberList;
	}

	public String getLoggedInUserLevel() {
		return loggedInUserLevel;
	}

	public void setLoggedInUserLevel(String loggedInUserLevel) {
		this.loggedInUserLevel = loggedInUserLevel;
	}

	public String getSelectedAppNumber() {
		return selectedAppNumber;
	}

	public void setSelectedAppNumber(String selectedAppNumber) {
		this.selectedAppNumber = selectedAppNumber;
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

	public String getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(String totalCost) {
		this.totalCost = totalCost;
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

	public String getSecurityDeposit() {
		return securityDeposit;
	}

	public void setSecurityDeposit(String securityDeposit) {
		this.securityDeposit = securityDeposit;
	}


}
