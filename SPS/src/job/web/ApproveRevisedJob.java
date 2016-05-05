package job.web;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import job.model.Pcesthmt;
import job.service.PcesthmtEjb;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.SessionAware;

import application.model.Applicant;
import application.model.Application;
import application.service.ApplicantEjb;
import application.service.ApplicationEjb;

import com.opensymphony.xwork2.ActionSupport;


import estimate.model.Pcesthtt;
import estimate.model.PcesthttPK;
import estimate.model.Speststd;
import estimate.model.SpeststdPK;
import estimate.service.PcesthttEjb;

import estimate.service.SpeststdEjb;
import util.common.*;

@SuppressWarnings("serial")
public class ApproveRevisedJob extends ActionSupport implements SessionAware, ParameterAware {

	private String costCenterNo;
	private String costCenterName;
	private Map<String, Object> session;
	private Map <String, String[]> parameters;
	private List<String> estimatesList;
	private String estList;


	// estimate details

	private String estimateDate;
	private String applicantName;
	private String applicantAddress;
	private String projectNumber;
	private String fundSource;
	private String standardCost;
	private String fixedCost;
	private String variableCost;
	private String conversionCost;
	private String estimateNumber;
	
	


	private String securityAmount;
	private String taxAmount;
	private String subTotal;
	private String otherCost;
	private String totalCost;
	private String description;
	private String enterBy;
	private String enterDate;

	// estimate cost details;

	private String lblError = null;
	private String lblSuccess = null;
	private Boolean hasError = false;
	private String revReason;
	private String loggedInUserLevel;
	
	// Status
//	private String approval="55";
	//private String approved="1";
	//private String rejected="41";

	private String loggedInUserId;
	private static final String viewPath="Manage Jobs>Approve Revised Jobs";
	private String path;
	
	private String select= "Select Project Number";
	
	
	public String getEstimateNumber() {
		return estimateNumber;
	}



	public void setEstimateNumber(String estimateNumber) {
		this.estimateNumber = estimateNumber;
	}



	public String getPath() {
		return path;
	}



	public void setPath(String path) {
		this.path = path;
	}
	

	public String getLoggedInUserId() {
		return loggedInUserId;
	}



	public void setLoggedInUserId(String loggedInUserId) {
		this.loggedInUserId = loggedInUserId;
	}



	 



	public String getRevReason() {
		return revReason;
	}



	public void setRevReason(String revReason) {
		this.revReason = revReason;
	}



	public String getLoggedInUserLevel() {
		return loggedInUserLevel;
	}



	public void setLoggedInUserLevel(String loggedInUserLevel) {
		this.loggedInUserLevel = loggedInUserLevel;
	}



	 

	public Boolean getHasError() {
		return hasError;
	}

	public void setHasError(Boolean hasError) {
		this.hasError = hasError;
	}

	public String getLblSuccess() {
		return lblSuccess;
	}

	public void setLblSuccess(String lblSuccess) {
		this.lblSuccess = lblSuccess;
	}

	public String getConversionCost() {
		return conversionCost;
	}

	public void setConversionCost(String conversionCost) {
		this.conversionCost = conversionCost;
	}


	public String getLblError() {
		return lblError;
	}

	public void setLblError(String lblError) {

		if (lblError == null )
		{
			setHasError(false);
			this.lblError = lblError;
		}
		else
		{
			if (lblError == select)
				this.lblError = null;
			else
				this.lblError = lblError;			  

			setHasError(true);
		}
	}

	public String getSecurityAmount() {
		return securityAmount;
	}

	public void setSecurityAmount(String securityAmount) {
		this.securityAmount = securityAmount;
	}

	public String getTaxAmount() {
		return taxAmount;
	}

	public void setTaxAmount(String taxAmount) {
		this.taxAmount = taxAmount;
	}

	public String getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(String subTotal) {
		this.subTotal = subTotal;
	}

	public String getOtherCost() {
		return otherCost;
	}

	public void setOtherCost(String otherCost) {
		this.otherCost = otherCost;
	}

	public String getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(String totalCost) {
		this.totalCost = totalCost;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEnterBy() {
		return enterBy;
	}

	public void setEnterBy(String enterBy) {
		this.enterBy = enterBy;
	}

	public String getEnterDate() {
		return enterDate;
	}

	public void setEnterDate(String enterDate) {
		this.enterDate = enterDate;
	}




	public String getEstimateDate() {
		return estimateDate;
	}

	public void setEstimateDate(String estimateDate) {
		this.estimateDate = estimateDate;
	}

	public String getApplicantName() {
		return applicantName;
	}

	public void setApplicantName(String applicantName) {
		this.applicantName = applicantName;
	}

	public String getApplicantAddress() {
		return applicantAddress;
	}

	public void setApplicantAddress(String applicantAddress) {
		this.applicantAddress = applicantAddress;
	}

	public String getProjectNumber() {
		return projectNumber;
	}

	public void setProjectNumber(String projectNumber) {
		this.projectNumber = projectNumber;
	}

	public String getFundSource() {
		return fundSource;
	}

	public void setFundSource(String fundSource) {
		this.fundSource = fundSource;
	}

	public String getStandardCost() {
		return standardCost;
	}

	public void setStandardCost(String standardCost) {
		this.standardCost = standardCost;
	}

	public String getFixedCost() {
		return fixedCost;
	}

	public void setFixedCost(String fixedCost) {
		this.fixedCost = fixedCost;
	}

	public String getVariableCost() {
		return variableCost;
	}

	public void setVariableCost(String variableCost) {
		this.variableCost = variableCost;
	}



	public String getEstList() {
		return estList;
	}

	public void setEstList(String estList) {
		this.estList = estList;
	}

	public List<String> getEstimatesList() {
		return estimatesList;
	}

	public void setEstimatesList(List<String> estimatesList) {
		this.estimatesList = estimatesList;
	}

	public Map<String, String[]> getParameters() {
		return parameters;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session=session;

	}

	@Override
	public void setParameters(Map<String, String[]> parameters) {
		this.parameters=parameters;

	}

	public String getCostCenterName() {
		return costCenterName;
	}

	public void setCostCenterName(String costCenterName) {
		this.costCenterName = costCenterName;
	}

	public String getCostCenterNo() {
		return costCenterNo;
	}

	public void setCostCenterNo(String costCenterNo) {
		this.costCenterNo = costCenterNo;
	}

	public String execute(){
		setLblError(null);
		System.out.println("aaaaaaaaaaaaa");
		System.out.println("Selected List Value:" + this.estList) ;
		setCostCenterNo(getSessionKey("costCenterNo"));
		setCostCenterName(getSessionKey("costCenterName"));
		setLoggedInUserLevel( getSessionKey("userRole"));
		setLoggedData();
		setPath(viewPath);
		//Danusha
		HttpServletRequest request = ServletActionContext.getRequest();
		String revProjectNo=request.getParameter("projectNo");
		String costCen=request.getParameter("costCen");
		if(revProjectNo!=null && revProjectNo.length()>0 && costCen!=null && costCen.length()>0){
			setCostCenterNo(costCen);
			setEstList(revProjectNo);
		}
		//Danusha

		setEstimateList();
		setEstimateDetials();


		return SUCCESS;
	}

	public void setLoggedData() {

		setLoggedInUserId(getSession().get("userName").toString());

	}

	public String getSessionKey(String key) {
		return getSession().get(key).toString();
	}

	public void setEstimateList()
	{
		this.estimatesList = new   ArrayList<String>();
		//PcesthttEjb ejb=new PcesthttEjb(getSessionKey("region"));
		PcesthmtEjb ejb = new PcesthmtEjb(getSessionKey("region"));
		System.out.println("xxxo  getLoggedInUserLevel().toString() :" +  getLoggedInUserLevel() + "......" );
		List<Pcesthmt> lst = ejb.getJobApprovalListNew (getCostCenterNo(), getLoggedInUserLevel() );
		this.estimatesList.add(select);
		if (lst!=null){
			for (int i=0; i<=lst.size()-1;i++) {
				//System.out.println(lst.get(i).getId().getEstimateNo());
				//this.estimatesList.add(lst.get(i).getId().getEstimateNo().trim());
				this.estimatesList.add(lst.get(i).getProjectNo().trim()); // December 7 2010, display project number list
			}
		}

	}

	private void setEstimateDetials()
	{

		try {
			if (this.estList==null || this.estList.equals(select))
			{
				setLblError(select);

				return;
			}else
			{
				// get the estimate detials 

				PcesthmtEjb ejb=new PcesthmtEjb(getSessionKey("region"));			 
				//Pcesthmt estimate =  ejb.findByEstimationNo(this.estList, getCostCenterNo());
				// get estimate detials by job number , December 7, 2010
				Pcesthmt estimate =  ejb.findByJobNo(this.estList, getCostCenterNo());

				if(estimate!=null)	
				{				 
					Format ft = new Format();
					if (estimate.getEtimateDt() != null)
						setEstimateDate(ft.formatDate(estimate.getEtimateDt()) );

					if (estimate.getProjectNo() != null)
						setProjectNumber(estimate.getProjectNo().toString());

					if (estimate.getFundSource()!= null)
						setFundSource (estimate.getFundSource().toString());

					if (estimate.getStdCost() != null)
						setStandardCost (ft.fromatCurrency(estimate.getStdCost() ) );

					if (estimate.getDescr()!= null)					
						setDescription(estimate.getDescr());

					if (estimate.getEntBy() != null)
						setEnterBy(estimate.getEntBy());

					if (estimate.getEntDt() != null)
						setEnterDate( ft.formatDate(estimate.getEntDt()) );
					
					if (estimate.getId().getEstimateNo() != null)
						setEstimateNumber(estimate.getId().getEstimateNo());

					setPersonelDetials();
					setCoastlDetials();

				}else
				{
					setLblError("Estimate details not found!");
					// no estimate details
				}

			}
		} catch (Exception e) {
			setLblError("Error in estimate detials :" + e.getMessage());
		}
	}
	private void setPersonelDetials()
	{
		ApplicationEjb applEjb = new ApplicationEjb(getSessionKey("region"));
		Application application = null ;
		try
		{
			application = applEjb.findByApplicationNo(this.estimateNumber);
		}catch(Exception e1)
		{
			setLblError("Application detials not found");
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
			setLblError("Applicant detials not found");
			return;
		}

		try
		{
			String tempName = applicant.getFirstName() +  ", " + applicant.getLastName();
			setApplicantName(tempName);
		}catch(Exception e3)
		{
			setLblError("Error in applicant name");
			return;
		}

		try
		{
			String tempAddress = applicant.getStreetAddress() + ", " + applicant.getSuburb() + 
			", " + applicant.getCity() +  ", "  + applicant.getPostalCode();  
			setApplicantAddress(tempAddress);
		}catch(Exception e4)
		{
			setLblError("Error in applicant address");
		}


	} 


	private void setCoastlDetials()
	{
		try {
			SpeststdEjb speststdEjb = new SpeststdEjb(getSessionKey("region"));
			SpeststdPK id = new SpeststdPK();
			id.setDeptId(getCostCenterNo());
			id.setEstimateNo( this.estimateNumber);
			Speststd est = null;
			try
			{
				est =  speststdEjb.findById(id);
			}catch(Exception e1)
			{
				setLblError("Cost details not found");
				return;
			}
			Format ft = new Format();


			setEnterBy(est.getUpdUser() );
			setFixedCost(ft.fromatCurrency(est.getFixedCost() ) );
			setVariableCost(ft.fromatCurrency(est.getVariableCost() ) );
			setConversionCost (ft.fromatCurrency(est.getConversionCost() ) );
			setSecurityAmount(ft.fromatCurrency(est.getSecurityDeposit() ) );
			//setTaxAmount(ft.fromatCurrency(est.get ) );
			//setSubTotal(ft.fromatCurrency(est.gets) );
			setOtherCost(ft.fromatCurrency(est.getOtherCost()) );
			//setTotalCost(ft.fromatCurrency(est ) );
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			setLblError("Error in cost detials");
		}
	}

	public String ApproveEst(){
		return rejectOrApproveEstimate(EstimateStatus.JOB_APPROVED);


	}

	private String rejectOrApproveEstimate(String status) {
		System.out.println("approe or reject estimate");

		try {
			if (!(this.estList==null || this.estList.equals(select)))
			{

				setLblError(null);

				PcesthmtEjb ejb=new PcesthmtEjb(getSessionKey("region"));	
				Pcesthmt estimate = null;
				try
				{
					setCostCenterNo(getSessionKey("costCenterNo"));					 
					estimate =  ejb.findByJobNo(this.estList, getCostCenterNo());
				}catch(Exception e2)
				{
					setLblError(this.estList + ", Can not find the estimate");
					return SUCCESS;
				}
				if(estimate!=null)	
				{	
					Long bigD = new Long(status);
					estimate.setStatus(bigD);
					estimate.setRejctUid(getSessionKey("userName"));
					estimate.setRejctDt(new Date());

					String noteForRejectOrAccept = getRevReason();
					estimate.setRejectReason(noteForRejectOrAccept);
					System.out.println("noteForRejectOrAccept:" + noteForRejectOrAccept);

					try
					{

						ejb.updatePcesthmt(estimate);	
						setLblSuccess("Revised job, " + this.estList + " updated successfully");
						this.estList = null;


						execute();
						clearData();	

					}catch(Exception e3)
					{
						setLblError("Revised job, " + this.estList + " update failed");
						return SUCCESS;
					}
				}
			} else
			{
				setLblError("Please select a revised job");
			}

		}catch(Exception e1)
		{
			setLblError("Error in approve revised job");

		}
		return SUCCESS;
	}

	private void clearData()
	{
		setEstimateDate("" );		 
		setProjectNumber("");		 
		setFundSource ("");		 
		setStandardCost ("" );		 
		setDescription("");		 
		setEnterBy("");		 
		setEnterDate("");
		setApplicantName("");
		setApplicantAddress("");
		setEnterBy("" );
		setFixedCost("");
		setVariableCost("" );
		setConversionCost ("" );
		setSecurityAmount("");
		//setTaxAmount(ft.fromatCurrency(est.get ) );
		//setSubTotal(ft.fromatCurrency(est.gets) );
		setOtherCost("" );
		//setTotalCost(ft.fromatCurrency(est ) );
		setRevReason("");
	}

	public String RejectEst(){
		return rejectOrApproveEstimate(EstimateStatus.JOB_REJECTED);
	}
	public String CloseEst(){
		return "close";
	}
}
