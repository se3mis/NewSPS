package job.web;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import job.model.Spestcnd;
import job.model.SpestcndPK;
import job.model.Spjobtcd;
import job.model.SpjobtcdPK;
import job.model.TempConnInfo;
import job.service.JobEjb;
import job.service.SpestcndEjb;
import job.service.SpjobtcdEjb;
import offDoc.web.PrintTemporaryJobEnagize;
import offDoc.web.TemporaryJobEnagize;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.SessionAware;
import util.common.ApplicationType;
import util.common.JobProcessStatus;
import util.common.PhaseList;
import com.opensymphony.xwork2.ActionSupport;

public class TCEnergize extends ActionSupport implements SessionAware, ParameterAware{
	private static final long serialVersionUID = 1L;
	private Map<String, Object> session;
	private Map <String, String[]> parameters;
	private static final Log log = LogFactory.getLog(TCEnergize.class);
	private static final String newPath="Temp Conn>Manage Jobs>Temporary Job Energize";
	private String path;
	private String loggedInUserId;
	private String costCenterNo;
	private String csc;
	private List<String> listjobNo;	
	private String jobNo;
	private Date applicationDate;
	private String serviceAddress;
	private Date applicationsendDate;
	private Date applicationreceivedDate;
	private Date servicerequiredFromDate;
	private Date servicerequiredToDate;
	private String estimateAmt;
	private String secdepositAmt;
	private String adddepositAmt;
	private String payinVouchrNo;
	private Date payinVouchrDate;
	private Date signatureESDate;	
	private Date servicegivenDate;
	private String givenlinemanName;
	private String matNoticeNo;
	private Date matNoticeDate;
	private Date servicediscntDate;
	private String discntlinemanName;	
	private String unitsUsed;
	private String chargedAmt;
	private Date applicationsendtoREDate;
	private String phase;
	private String meterNumber1;
	private String meterNumber2;
	private String meterNumber3;
	private String initialReading1;
	private String initialReading2;
	private String initialReading3;
	private String finalmeterReading1;
	private String finalmeterReading2;
	private String finalmeterReading3;
	private String hid_isFind;
	private String hid_contractorId;
	private String damageAmt;
	
	public String getDamageAmt() {
		return damageAmt;
	}
	public void setDamageAmt(String damageAmt) {
		this.damageAmt = damageAmt;
	}
	public String getHid_contractorId() {
		return hid_contractorId;
	}
	public void setHid_contractorId(String hid_contractorId) {
		this.hid_contractorId = hid_contractorId;
	}
	public String getHid_isFind() {
		return hid_isFind;
	}
	public void setHid_isFind(String hid_isFind) {
		this.hid_isFind = hid_isFind;
	}
	public String getMeterNumber1() {
		return meterNumber1;
	}
	public void setMeterNumber1(String meterNumber1) {
		this.meterNumber1 = meterNumber1;
	}
	public String getMeterNumber2() {
		return meterNumber2;
	}
	public void setMeterNumber2(String meterNumber2) {
		this.meterNumber2 = meterNumber2;
	}
	public String getMeterNumber3() {
		return meterNumber3;
	}
	public void setMeterNumber3(String meterNumber3) {
		this.meterNumber3 = meterNumber3;
	}
	public String getInitialReading1() {
		return initialReading1;
	}
	public void setInitialReading1(String initialReading1) {
		this.initialReading1 = initialReading1;
	}
	public String getInitialReading2() {
		return initialReading2;
	}
	public void setInitialReading2(String initialReading2) {
		this.initialReading2 = initialReading2;
	}
	public String getInitialReading3() {
		return initialReading3;
	}
	public void setInitialReading3(String initialReading3) {
		this.initialReading3 = initialReading3;
	}
	public String getFinalmeterReading1() {
		return finalmeterReading1;
	}
	public void setFinalmeterReading1(String finalmeterReading1) {
		this.finalmeterReading1 = finalmeterReading1;
	}
	public String getFinalmeterReading2() {
		return finalmeterReading2;
	}
	public void setFinalmeterReading2(String finalmeterReading2) {
		this.finalmeterReading2 = finalmeterReading2;
	}
	public String getFinalmeterReading3() {
		return finalmeterReading3;
	}
	public void setFinalmeterReading3(String finalmeterReading3) {
		this.finalmeterReading3 = finalmeterReading3;
	}
	
	public String getPhase() {
		return phase;
	}
	public void setPhase(String phase) {
		this.phase = phase;
	}
	public Date getServicegivenDate() {
		return servicegivenDate;
	}
	public void setServicegivenDate(Date servicegivenDate) {
		this.servicegivenDate = servicegivenDate;
	}
	public String getGivenlinemanName() {
		return givenlinemanName;
	}
	public void setGivenlinemanName(String givenlinemanName) {
		this.givenlinemanName = givenlinemanName;
	}
	public String getMatNoticeNo() {
		return matNoticeNo;
	}
	public void setMatNoticeNo(String matNoticeNo) {
		this.matNoticeNo = matNoticeNo;
	}
	public Date getMatNoticeDate() {
		return matNoticeDate;
	}
	public void setMatNoticeDate(Date matNoticeDate) {
		this.matNoticeDate = matNoticeDate;
	}

	public Date getServicediscntDate() {
		return servicediscntDate;
	}
	public void setServicediscntDate(Date servicediscntDate) {
		this.servicediscntDate = servicediscntDate;
	}
	public String getDiscntlinemanName() {
		return discntlinemanName;
	}
	public void setDiscntlinemanName(String discntlinemanName) {
		this.discntlinemanName = discntlinemanName;
	}
	public String getUnitsUsed() {
		return unitsUsed;
	}
	public void setUnitsUsed(String unitsUsed) {
		this.unitsUsed = unitsUsed;
	}
	public String getChargedAmt() {
		return chargedAmt;
	}
	public void setChargedAmt(String chargedAmt) {
		this.chargedAmt = chargedAmt;
	}
	public Date getApplicationsendtoREDate() {
		return applicationsendtoREDate;
	}
	public void setApplicationsendtoREDate(Date applicationsendtoREDate) {
		this.applicationsendtoREDate = applicationsendtoREDate;
	}
	
	public Date getSignatureESDate() {
		return signatureESDate;
	}
	public void setSignatureESDate(Date signatureESDate) {
		this.signatureESDate = signatureESDate;
	}
	public Date getApplicationDate() {
		return applicationDate;
	}
	public void setApplicationDate(Date applicationDate) {
		this.applicationDate = applicationDate;
	}
	public String getServiceAddress() {
		return serviceAddress;
	}
	public void setServiceAddress(String serviceAddress) {
		this.serviceAddress = serviceAddress;
	}
	public Date getApplicationsendDate() {
		return applicationsendDate;
	}
	public void setApplicationsendDate(Date applicationsendDate) {
		this.applicationsendDate = applicationsendDate;
	}
	public Date getApplicationreceivedDate() {
		return applicationreceivedDate;
	}
	public void setApplicationreceivedDate(Date applicationreceivedDate) {
		this.applicationreceivedDate = applicationreceivedDate;
	}
	public Date getServicerequiredFromDate() {
		return servicerequiredFromDate;
	}
	public void setServicerequiredFromDate(Date servicerequiredFromDate) {
		this.servicerequiredFromDate = servicerequiredFromDate;
	}
	public Date getServicerequiredToDate() {
		return servicerequiredToDate;
	}
	public void setServicerequiredToDate(Date servicerequiredToDate) {
		this.servicerequiredToDate = servicerequiredToDate;
	}
	public String getEstimateAmt() {
		return estimateAmt;
	}
	public void setEstimateAmt(String estimateAmt) {
		this.estimateAmt = estimateAmt;
	}
	public String getSecdepositAmt() {
		return secdepositAmt;
	}
	public void setSecdepositAmt(String secdepositAmt) {
		this.secdepositAmt = secdepositAmt;
	}

	public String getAdddepositAmt() {
		return adddepositAmt;
	}
	public void setAdddepositAmt(String adddepositAmt) {
		this.adddepositAmt = adddepositAmt;
	}
	public String getPayinVouchrNo() {
		return payinVouchrNo;
	}
	public void setPayinVouchrNo(String payinVouchrNo) {
		this.payinVouchrNo = payinVouchrNo;
	}
	public Date getPayinVouchrDate() {
		return payinVouchrDate;
	}
	public void setPayinVouchrDate(Date payinVouchrDate) {
		this.payinVouchrDate = payinVouchrDate;
	}	
	public String getJobNo() {
		return jobNo;
	}
	public void setJobNo(String jobNo) {
		this.jobNo = jobNo;
	}
	public List<String> getListjobNo() {
		return listjobNo;
	}
	public void setListjobNo(List<String> listjobNo) {
		this.listjobNo = listjobNo;
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
	public String getCostCenterNo() {
		return costCenterNo;
	}
	public void setCostCenterNo(String costCenterNo) {
		this.costCenterNo = costCenterNo;
	}
	public String getCsc() {
		return csc;
	}
	public void setCsc(String csc) {
		this.csc = csc;
	}
	public Map<String, Object> getSession() {
		return session;
	}
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	public Map<String, String[]> getParameters() {
		return parameters;
	}
	public void setParameters(Map<String, String[]> parameters) {
		this.parameters = parameters;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public static String getNewpath() {
		return newPath;
	}
	
	public String execute(){
		try{
			setLoggedData();
			listjobNo = new ArrayList<String>();			
			SpestcndEjb spejb=new SpestcndEjb(getSessionKey("region"));
			setPath(newPath);
			JobEjb jobejb=new JobEjb(getSessionKey("region"));
			hid_isFind="";
			listjobNo=spejb.getJobNoListByType(costCenterNo,ApplicationType.TEMP_CONN);		 		
			if(jobNo!=null && !jobNo.trim().equals("-1") && jobNo.length()>0){
				TempConnInfo tempConnInfo= jobejb.getTempConnInfos(jobNo.trim(),costCenterNo);
							              
				if(tempConnInfo!=null){
					if(tempConnInfo.getPhase()!=null){
						int intPhase=tempConnInfo.getPhase().intValue();
						if(intPhase==PhaseList.SINGLEPHASE_VAL)
							phase = PhaseList.SINGLEPHASE;
						else if(intPhase==PhaseList.TWOPHASE_VAL)
							phase = PhaseList.TWOPHASE;
						else if(intPhase==PhaseList.THREEPHASE_VAL)
							phase = PhaseList.THREEPHASE;
					}
					phase = PhaseList.THREEPHASE;					
					applicationDate = new Date();
					serviceAddress = tempConnInfo.getServiceStreetAddress()+","+tempConnInfo.getServiceSuburb()+","+tempConnInfo.getServiceCity();
					applicationsendDate = tempConnInfo.getSubmitDate();
					applicationreceivedDate= tempConnInfo.getPrjAssDt();
					servicerequiredFromDate = tempConnInfo.getFromDate();
					hid_contractorId = tempConnInfo.getContractorId();
					Calendar cal = Calendar.getInstance();
					cal.setTime(servicerequiredFromDate);
					if(tempConnInfo.getDurationInDays()!=null)
						cal.add(Calendar.DATE, tempConnInfo.getDurationInDays().intValue());
					
					
					servicerequiredToDate = cal.getTime();
					if(tempConnInfo.getTotalCost()!=null)
						estimateAmt = tempConnInfo.getTotalCost().toString();
					if(tempConnInfo.getSecurityDeposit()!=null)
						secdepositAmt=tempConnInfo.getSecurityDeposit().toString();
					if(tempConnInfo.getAddlDeposit()!=null)
						adddepositAmt=tempConnInfo.getAddlDeposit().toString();
					else
						adddepositAmt = "0";
					
					if(tempConnInfo.getDamageCost()!=null)
						damageAmt=tempConnInfo.getDamageCost().toString();
					else
						damageAmt = "0";
					
					payinVouchrNo = tempConnInfo.getPivNo();
					payinVouchrDate = tempConnInfo.getPivDate();
					hid_contractorId = tempConnInfo.getContractorId();
					
					SpjobtcdEjb spjobtcdEjb=new SpjobtcdEjb(getSessionKey("region"));
					Spjobtcd spjobtcd = spjobtcdEjb.findById(jobNo.trim(),costCenterNo);
					if(spjobtcd!=null){
						hid_isFind ="Found";
						servicegivenDate=spjobtcd.getServiceConnectedDate();
						givenlinemanName = spjobtcd.getServiceConnectedLinesman();
						matNoticeNo = spjobtcd.getMaterialNoticeNumber();
						matNoticeDate = spjobtcd.getMaterialNoticeDate();
						meterNumber1 = spjobtcd.getMeterNumber1();
						meterNumber2= spjobtcd.getMeterNumber2();
						meterNumber3= spjobtcd.getMeterNumber3();
						if(spjobtcd.getInitialReading1()!=null)
							initialReading1= spjobtcd.getInitialReading1().toString();
						if(spjobtcd.getInitialReading2()!=null)
							initialReading2= spjobtcd.getInitialReading2().toString();
						if(spjobtcd.getInitialReading3()!=null)
							initialReading3= spjobtcd.getInitialReading3().toString();
						signatureESDate = new Date();
						if(spjobtcd.getUnitsUsed()!=null)
							unitsUsed = spjobtcd.getUnitsUsed().toString();
						
						
					}else{
						unitsUsed = "0";
					}
					
				}				
			}else{
				clearForm();;
			}
			
		}catch(Exception ex){
			System.out.println("Error......................."+ex);
			
		}
		return "success";
	}

	public String close(){
		setLoggedData();
		clearForm();
		return "closed";
	}
	
	public String print(){
		TemporaryJobEnagize temporaryJobEnagize = new TemporaryJobEnagize();
        PrintTemporaryJobEnagize pe = new PrintTemporaryJobEnagize(temporaryJobEnagize);

        temporaryJobEnagize.setJobNumber(jobNo);
        if(applicationDate!=null)
        	temporaryJobEnagize.setJobDate(applicationDate.toString());
        temporaryJobEnagize.setStreetAddress("89/3, Meemanagoda Para");
        temporaryJobEnagize.setSuburb("Kalalgoda");
        temporaryJobEnagize.setCity("Pannipitiya");
        temporaryJobEnagize.setPostalCode("00024");
        if(applicationsendDate!=null)
        	temporaryJobEnagize.setApplicationSendDate(applicationsendDate.toString());
        if(applicationreceivedDate!=null)
        	temporaryJobEnagize.setApplicationRecivedDateFromREEOffice(applicationreceivedDate.toString());
        if(servicerequiredFromDate!=null)
        	temporaryJobEnagize.setRequiredServiceDurationFromDate(servicerequiredFromDate.toString());
        if(servicerequiredToDate!=null)
        	temporaryJobEnagize.setRequiredServiceDurationToDate(servicerequiredToDate.toString());
        temporaryJobEnagize.setEstimateAmount(estimateAmt);
        temporaryJobEnagize.setSecurityDeposite(secdepositAmt);
        temporaryJobEnagize.setAdditionalDeposite(adddepositAmt);
        temporaryJobEnagize.setPivNumber(payinVouchrNo);
        if(payinVouchrDate!=null)
        	temporaryJobEnagize.setPivDate(payinVouchrDate.toString());
        if(servicegivenDate!=null)
        	temporaryJobEnagize.setServiceConnectedDate(servicegivenDate.toString());
        temporaryJobEnagize.setServiceConnectedlinesManName(givenlinemanName);
        temporaryJobEnagize.setMaterialNoticeNumber(matNoticeNo);
        if(matNoticeDate!=null)
        	temporaryJobEnagize.setMaterialNoticeDate(matNoticeDate.toString());
        temporaryJobEnagize.setPhase(phase);
        temporaryJobEnagize.setMeterNumber1(meterNumber1);
        temporaryJobEnagize.setMeterNumber1InitialReading(initialReading1);
        temporaryJobEnagize.setMeterNumber1FinalReading(finalmeterReading1);
        temporaryJobEnagize.setMeterNumber2(meterNumber2);
        temporaryJobEnagize.setMeterNumber2InitialReading(initialReading2);
        temporaryJobEnagize.setMeterNumber2FinalReading(finalmeterReading2);
        temporaryJobEnagize.setMeterNumber3(meterNumber3);
        temporaryJobEnagize.setMeterNumber3InitialReading(initialReading2);
        temporaryJobEnagize.setMeterNumber3FinalReading(finalmeterReading3);
        if(servicediscntDate!=null)
        	temporaryJobEnagize.setServiceDisconnectedDate(servicediscntDate.toString());
        temporaryJobEnagize.setServiceDisconnectedlinesManName(discntlinemanName);
        temporaryJobEnagize.setUnitsUsed(unitsUsed);
        temporaryJobEnagize.setChargedAmount(chargedAmt);
        pe.Print(true);

        execute();
		setLoggedData();
		return "success";
	}
	
	public void setLoggedData() {	       
        log.info(getSession());
        setLoggedInUserId(getSessionKey("userName"));
        setCostCenterNo(getSessionKey("costCenterNo"));
        setCsc(getSessionKey("costCenterName"));       
    }
	 
	public String getSessionKey(String key) {
        return getSession().get(key).toString();
    }

	public String energizeTCJob(){
		try
		{
			SpjobtcdEjb spjobtcdEjb=new SpjobtcdEjb(getSessionKey("region"));
			if(hid_isFind!=null && (hid_isFind=="" || hid_isFind.length()==0)){
				SpjobtcdPK spjobtcdPK=new SpjobtcdPK();
				spjobtcdPK.setDeptId(getSessionKey("costCenterNo"));
				spjobtcdPK.setProjectNo(jobNo.trim());
				
				Spjobtcd spjobtcd=new Spjobtcd();
				spjobtcd.setId(spjobtcdPK);
				spjobtcd.setInitialReading1(new BigDecimal(initialReading1));
				spjobtcd.setInitialReading2(new BigDecimal(initialReading2));
				spjobtcd.setInitialReading3(new BigDecimal(initialReading3));
				spjobtcd.setMaterialNoticeDate(matNoticeDate);
				spjobtcd.setMaterialNoticeNumber(matNoticeNo);
				spjobtcd.setMeterNumber1(meterNumber1);
				spjobtcd.setMeterNumber2(meterNumber2);
				spjobtcd.setMeterNumber3(meterNumber3);
				spjobtcd.setUnitsUsed(new BigDecimal(unitsUsed));
				spjobtcd.setServiceConnectedDate(servicegivenDate);
				spjobtcd.setServiceConnectedLinesman(givenlinemanName);
				spjobtcdEjb.createSpjobtcd(spjobtcd);	
				
				SpestcndEjb spestcndejb =new SpestcndEjb(getSessionKey("region"));
				SpestcndPK spestcndpk = new SpestcndPK();
				spestcndpk.setContractorId(hid_contractorId);
				spestcndpk.setDeptId(getSessionKey("costCenterNo"));
				spestcndpk.setProjectNo(jobNo.trim());					 
				Spestcnd spestcnd=spestcndejb.findById(spestcndpk);
				
				if(spestcnd!=null){					
					spestcnd.setStatus(JobProcessStatus.TEMPORARY_ENERGIZED);	
					spestcndejb.updateSpestcnd(spestcnd);					
				}
				
			}else{
				SpjobtcdEjb spjobtcdEjb1=new SpjobtcdEjb(getSessionKey("region"));
				Spjobtcd spjobtcd1 = spjobtcdEjb1.findById(jobNo.trim(),getSessionKey("costCenterNo"));
				if(spjobtcd1!=null){
					System.out.println("It is found....................");
					spjobtcd1.setFinalReading1(new BigDecimal(finalmeterReading1));
					spjobtcd1.setFinalReading2(new BigDecimal(finalmeterReading2));
					spjobtcd1.setFinalReading3(new BigDecimal(finalmeterReading3));
					
					spjobtcd1.setServiceDisconnectedDate(servicediscntDate);
					spjobtcd1.setServiceConnectedLinesman(discntlinemanName);
					spjobtcd1.setUnitsUsed(new BigDecimal(unitsUsed));
					if(chargedAmt!=null)
						spjobtcd1.setChargedAmount(new BigDecimal(chargedAmt));
					
					spjobtcd1.setSubmitDate(new Date());
					spjobtcdEjb1.updateSpjobtcd(spjobtcd1);
					
					System.out.println("Done....................");
					
					SpestcndEjb spestcndejb =new SpestcndEjb(getSessionKey("region"));
					SpestcndPK spestcndpk = new SpestcndPK();
					spestcndpk.setContractorId(hid_contractorId);
					spestcndpk.setDeptId(getSessionKey("costCenterNo"));
					spestcndpk.setProjectNo(jobNo.trim());					 
					Spestcnd spestcnd=spestcndejb.findById(spestcndpk);
					
					if(spestcnd!=null){
						spestcnd.setStatus(JobProcessStatus.FINISHED);	
						spestcnd.setFinishedDate(servicediscntDate);
						spestcnd.setFinishedUser(getSessionKey("userName"));
						spestcndejb.updateSpestcnd(spestcnd);
					}
				}				
			}
			
		}catch(Exception ex){
			System.out.println("The error is................................:"+ex);
		}
		clearForm();
		setLoggedData();
		execute();
		return "success";
	}

	private void clearForm(){
		
		 applicationDate=null;
		 serviceAddress="";
		 applicationsendDate=null;
		 applicationreceivedDate=null;
		 servicerequiredFromDate=null;
		 servicerequiredToDate=null;
		 estimateAmt="";
		 secdepositAmt="";
		 adddepositAmt="";
		 payinVouchrNo="";
		 payinVouchrDate=null;
		 signatureESDate=null;	
		 servicegivenDate=null;
		 givenlinemanName="";
		 matNoticeNo="";
		 matNoticeDate=null;
		 servicediscntDate=null;
		 discntlinemanName="";	
		 unitsUsed="";
		 chargedAmt="";
		 applicationsendtoREDate=null;
		 phase="";
		 meterNumber1="";
		 meterNumber2="";
		 meterNumber3="";
		 initialReading1="";
		 initialReading2="";
		 initialReading3="";
		 finalmeterReading1="";
		 finalmeterReading2="";
		 finalmeterReading3="";
		 hid_isFind="";
		 jobNo="";
	}

}
