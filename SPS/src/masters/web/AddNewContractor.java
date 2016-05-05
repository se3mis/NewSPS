package masters.web;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Date;
import java.util.Map;

import job.model.Spestcnt;
import job.model.SpestcntPK;
import job.service.SpestcntEjb;

import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.SessionAware;

import print.piv.PrintPiv;


 

import com.opensymphony.xwork2.ActionSupport;

public class AddNewContractor extends ActionSupport implements SessionAware, ParameterAware {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map<String, Object> session;
	private Map <String, String[]> parameters;
	private String loggedInUserId;
	private static final String viewPath="Masters>Add Contractor Detials";
	private String path;
	private String lblError = null;
	private String lblSuccess = null;
	
	private String costCenter;
	private String contractorName;
	private String contractorAddress;
	private Date startDate;
	private Date endDate;
	private String bondNumber;
	private String performanceAmount;
	private String contractorCode;
	private String bondAmount;
	private String tenderAmount;
	private String singAddSpan;
	private String threeAddSpan;
	NumberFormat nf = NumberFormat.getInstance();
	private String VAT="0.00";
	private String NBT="0.00";
	
	
	public String getVAT() {
		return VAT;
	}

	public void setVAT(String vAT) {
		VAT = vAT;
	}

	public String getNBT() {
		return NBT;
	}

	public void setNBT(String nBT) {
		NBT = nBT;
	}

	public String getContractorCode() {
		return contractorCode;
	}

	public void setContractorCode(String contractorCode) {
		this.contractorCode = contractorCode;
	}

	public String getBondAmount() {
		return bondAmount;
	}

	public void setBondAmount(String bondAmount) {
		this.bondAmount = bondAmount;
	}

	public String getTenderAmount() {
		return tenderAmount;
	}

	public void setTenderAmount(String tenderAmount) {
		this.tenderAmount = tenderAmount;
	}

	public String getSingAddSpan() {
		return singAddSpan;
	}

	public void setSingAddSpan(String singAddSpan) {
		this.singAddSpan = singAddSpan;
	}

	public String getThreeAddSpan() {
		return threeAddSpan;
	}

	public void setThreeAddSpan(String threeAddSpan) {
		this.threeAddSpan = threeAddSpan;
	}

	public String getBondNumber() {
		return bondNumber;
	}

	public void setBondNumber(String bondNumber) {
		this.bondNumber = bondNumber;
	}

	public String getPerformanceAmount() {
		return performanceAmount;
	}

	public void setPerformanceAmount(String performanceAmount) {
		this.performanceAmount = performanceAmount;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getContractorName() {
		return contractorName;
	}

	public void setContractorName(String contractorName) {
		this.contractorName = contractorName;
	}

	public String getContractorAddress() {
		return contractorAddress;
	}

	public void setContractorAddress(String contractorAddress) {
		this.contractorAddress = contractorAddress;
	}

	public String getCostCenter() {
		return costCenter;
	}

	public void setCostCenter(String costCenter) {
		this.costCenter = costCenter;
	}

	public static String getViewpath() {
		return viewPath;
	}

	public Map<String, Object> getSession() {
		return session;
	}
	
	public Map<String, String[]> getParameters() {
		return parameters;
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
	
	public String execute(){
		setLblError(null);
		
		setLoggedInUserId(getSession().get("userName").toString());
		setPath(viewPath);
		
		setCostCenter(getSessionKey("costCenterNo"));
		Date dt = new Date();
		setStartDate(dt);
		setEndDate(dt);
		
		
		return SUCCESS;
	}
	public String Close(){
		return "close";
	}
	public String AddContractor()throws Exception
	{
		String name = getContractorName();
		String address = getContractorAddress();
		String bondNumber = getBondNumber();
		String  perfAmount = getPerformanceAmount();
		Date stDt = getStartDate();
		Date endDt = getEndDate();
		String code = getContractorCode();
		String bondAmount = getBondAmount();
		String tenderAmount = getTenderAmount();
		String singAddSpan = getSingAddSpan();
		String threeAddSpan = getThreeAddSpan();
		String vat = getVAT();
		String nbt = getNBT();
		setCostCenter(getSessionKey("costCenterNo"));
		
		if (  name == null )
		{
			setLblError("Please enter the contractor name");			
			return SUCCESS;
		}
		if (  address == null )
		{
			setLblError("Please enter the address of the contractor");
			return SUCCESS;
		}
		name = name.trim();
		if (  name.isEmpty() )
		{
			setLblError("Please enter the contractor name");			
			return SUCCESS;
		}
		address = address.trim();
		if (  address.isEmpty() )
		{
			setLblError("Please enter the address of the contractor");
			return SUCCESS;
		}
		 	
		Date dt = getStartDate();
		System.out.println(" XXXXXXXXXXXDddddddddddddddddddddddddd " + dt.toString());
		SpestcntEjb ejb = new SpestcntEjb(getSessionKey("region"));
		
		SpestcntPK tempSpestcntPK = new SpestcntPK();
		tempSpestcntPK.setContractorId(null);
		tempSpestcntPK.setDeptId(getCostCenter());
		
		Spestcnt tempSpestcnt = new Spestcnt();
		tempSpestcnt.setContractorAddress(address);
		tempSpestcnt.setContractorName(name);
		tempSpestcnt.setId(tempSpestcntPK);
		BigDecimal bigD = new BigDecimal("0");
		tempSpestcnt.setJobInHand(bigD);
		tempSpestcnt.setTotalAmount(bigD);
		
		tempSpestcnt.setCode(code);
		if(bondAmount.length() != 0){
			tempSpestcnt.setBondAmount(new BigDecimal(nf.parse(bondAmount).doubleValue()));
		}
		if(tenderAmount.length() != 0){
			tempSpestcnt.setTenderAmount(new BigDecimal(nf.parse(tenderAmount).doubleValue()));
		}
		//tempSpestcnt.setSpAddSpan(new BigDecimal(nf.parse(singAddSpan).doubleValue()));
		//tempSpestcnt.setTpAddSpan(new BigDecimal(nf.parse(threeAddSpan).doubleValue()));
		tempSpestcnt.setBondNo(bondNumber);
		if(perfAmount.length() != 0){
			tempSpestcnt.setPerformanceAmount(new BigDecimal(nf.parse(perfAmount).doubleValue()));
		}
		tempSpestcnt.setStartDate(stDt);
		tempSpestcnt.setEndDate(endDt);
		tempSpestcnt.setNbt(nbt);
		tempSpestcnt.setVat(vat);
		

		Spestcnt spestcnt = ejb.findById(tempSpestcntPK);
		if (spestcnt == null)
		{
			ejb.createSpestcntAutoId(tempSpestcnt);
			setLblSuccess("Contractor added successfully");
		}else
		{
			execute();
			setLblError("Contractor already exist.");
			return SUCCESS;
		}
		
		execute();
		return SUCCESS;
	}
	
	public String getSessionKey(String key) {
		return getSession().get(key).toString();
	}
	
}
