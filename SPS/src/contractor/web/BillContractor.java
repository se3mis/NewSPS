package contractor.web;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import job.model.Pcesthmt;
import job.model.Spestbip;
import job.model.SpestbipPK;
import job.model.Spestcnd;
import job.model.Spestcnt;
import job.model.Spestjad;
import job.model.StdDetail;
import job.service.JobEjb;
import job.service.PcesthmtEjb;
import job.service.SpestbipEjb;
import job.service.SpestcndEjb;
import job.service.SpestcntEjb;
import job.service.SpestjadEjb;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.SessionAware;

import util.common.Format;
import util.common.JobProcessStatus;

import com.opensymphony.xwork2.ActionSupport;
public class BillContractor extends ActionSupport implements SessionAware, ParameterAware{

	private static final long serialVersionUID = 1L;
	private Map<String, Object> session;
	private Map <String, String[]> parameters;	
	private static final Log log = LogFactory.getLog(BillContractor.class);
	private static final String newPath="Manage Jobs>Bill Contractor";	
	private String path;
	private String loggedInUserId;
	private String costCenterNo;
	private String csc;
	private String contractor;	
	private String contractorID;
	private String estimatedBy;
	private String billDate;
	private String billReferenceNo;
	private String grandTotal1;
	private String transportnofKm;

	public String getTransportnofKm() {
		return transportnofKm;
	}

	public void setTransportnofKm(String transportnofKm) {
		this.transportnofKm = transportnofKm;
	}

	public String getGrandTotal1() {
		return grandTotal1;
	}

	public void setGrandTotal1(String grandTotal1) {
		this.grandTotal1 = grandTotal1;
	}

	public String getBillReferenceNo() {
		return billReferenceNo;
	}

	public void setBillReferenceNo(String billReferenceNo) {
		this.billReferenceNo = billReferenceNo;
	}

	private String serviceCode;
	private String description;
	private String unitPrice;
	private String quantity;
	private String amount;
	private String name;	
	private String distanceToPlace;
	private String serviceLength;
	
	

	private String nofStays;
	private String nofStructs;
	private String nofSpans;
	private String jobDescription;
	
	private String abAmount;
	

	public String getAbAmount() {
		return abAmount;
	}

	public void setAbAmount(String abAmount) {
		this.abAmount = abAmount;
	}

	private List<String> listContractor;
	private String jobNumber;
	private List<String> listjobNumber;
	private List<String> listContractorID;
	
	private List<ListObject> transportationDetailsList;
	private List<ListObject> excavationDetailsList;
	private List<ListObject>  erectionDetailsList;
	private List<ListObject> insStaysDetailsList;
	private List<ListObject> wireDetailsList;
	private List<ListObject> conductorDetailsList;
	private List<ListObject> reapirExistingDetailsList;
	private List<ListObject> changeExistingDetailsList;
	private List<ListObject> removalDetailsList;
	private List<ListObject> removalPoleDetailsList;
	
	public List<ListObject> getRemovalPoleDetailsList() {
		return removalPoleDetailsList;
	}

	public void setRemovalPoleDetailsList(List<ListObject> removalPoleDetailsList) {
		this.removalPoleDetailsList = removalPoleDetailsList;
	}

	public List<ListObject> getRemovalDetailsList() {
		return removalDetailsList;
	}

	public void setRemovalDetailsList(List<ListObject> removalDetailsList) {
		this.removalDetailsList = removalDetailsList;
	}

	public List<ListObject> getChangeExistingDetailsList() {
		return changeExistingDetailsList;
	}

	public void setChangeExistingDetailsList(
			List<ListObject> changeExistingDetailsList) {
		this.changeExistingDetailsList = changeExistingDetailsList;
	}

	public List<ListObject> getConductorDetailsList() {
		return conductorDetailsList;
	}

	public void setConductorDetailsList(List<ListObject> conductorDetailsList) {
		this.conductorDetailsList = conductorDetailsList;
	}

	public List<ListObject> getWireDetailsList() {
		return wireDetailsList;
	}

	public void setWireDetailsList(List<ListObject> wireDetailsList) {
		this.wireDetailsList = wireDetailsList;
	}

	public List<ListObject> getInsStaysDetailsList() {
		return insStaysDetailsList;
	}

	public void setInsStaysDetailsList(List<ListObject> insStaysDetailsList) {
		this.insStaysDetailsList = insStaysDetailsList;
	}

	public List<ListObject> getErectionDetailsList() {
		return erectionDetailsList;
	}

	public void setErectionDetailsList(List<ListObject> erectionDetailsList) {
		this.erectionDetailsList = erectionDetailsList;
	}

	public List<ListObject> getExcavationDetailsList() {
		return excavationDetailsList;
	}

	public void setExcavationDetailsList(List<ListObject> excavationDetailsList) {
		this.excavationDetailsList = excavationDetailsList;
	}

	public List<ListObject> getTransportationDetailsList() {
		return transportationDetailsList;
	}

	public void setTransportationDetailsList(
			List<ListObject> transportationDetailsList) {
		this.transportationDetailsList = transportationDetailsList;
	}

	public String getJobDescription() {
		return jobDescription;
	}

	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}

	public String getDistanceToPlace() {
		return distanceToPlace;
	}

	public void setDistanceToPlace(String distanceToPlace) {
		this.distanceToPlace = distanceToPlace;
	}

	public String getServiceLength() {
		return serviceLength;
	}

	public void setServiceLength(String serviceLength) {
		this.serviceLength = serviceLength;
	}



	public String getNofStays() {
		return nofStays;
	}

	public void setNofStays(String nofStays) {
		this.nofStays = nofStays;
	}

	public String getNofStructs() {
		return nofStructs;
	}

	public void setNofStructs(String nofStructs) {
		this.nofStructs = nofStructs;
	}

	public String getNofSpans() {
		return nofSpans;
	}

	public void setNofSpans(String nofSpans) {
		this.nofSpans = nofSpans;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getServiceCode() {
		return serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getBillDate() {
		return billDate;
	}

	public void setBillDate(String billDate) {
		this.billDate = billDate;
	}

	public String getEstimatedBy() {
		return estimatedBy;
	}

	public void setEstimatedBy(String estimatedBy) {
		this.estimatedBy = estimatedBy;
	}

	public String getContractorID() {
		return contractorID;
	}

	public void setContractorID(String contractorID) {
		this.contractorID = contractorID;
	}

	public List<String> getListContractorID() {
		return listContractorID;
	}

	public void setListContractorID(List<String> listContractorID) {
		this.listContractorID = listContractorID;
	}



	public List<String> getListContractor() {
		return listContractor;
	}

	public void setListContractor(List<String> listContractor) {
		this.listContractor = listContractor;
	}


	public List<String> getListjobNumber() {
		return listjobNumber;
	}

	public void setListjobNumber(List<String> listjobNumber) {
		this.listjobNumber = listjobNumber;
	}

	public String getJobNumber() {
		return jobNumber;
	}

	public void setJobNumber(String jobNumber) {
		this.jobNumber = jobNumber;
	}

	public List<ContractorBillDetails> getList() {
		return list;
	}

	public void setList(List<ContractorBillDetails> list) {
		this.list = list;
	}

	public ContractorBillList getContractBillList() {
		return contractBillList;
	}

	public void setContractBillList(ContractorBillList contractBillList) {
		this.contractBillList = contractBillList;
	}

	private List<ContractorBillDetails> list;
	private ContractorBillList contractBillList;
	

	public String getContractor() {
		return contractor;
	}

	public void setContractor(String contractor) {
		this.contractor = contractor;
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
 	
 	public String getSessionKey(String key) {
        return getSession().get(key).toString();
    }
	
	@Override
	public void setSession(Map<String, Object> session) {
		this.session=session;
		
	}

	public Map<String, String[]> getParameters() {
		return parameters;
	}
	
	@Override
	public void setParameters(Map<String, String[]> parameters) {
		this.parameters=parameters;
		
	}
	
	public String execute()	 
	{
		try
		{
			setLoggedData();
			setPath(newPath);
			SpestcntEjb ejb=new SpestcntEjb(getSessionKey("region"));	
			contractBillList = new ContractorBillList();
			listjobNumber = new ArrayList<String>();
			listContractor  = new ArrayList<String>();			
						
			listContractorID = new ArrayList<String>(); 
			list = new ArrayList<ContractorBillDetails>();
			List<Spestcnd> spestcndList=new ArrayList<Spestcnd>();
			String costCentre = getSessionKey("costCenterNo");			
			List<Spestcnt> spestcntList=new ArrayList<Spestcnt>();
			
			// Adding values to the controls
			SpestjadEjb spJejb =new SpestjadEjb(getSessionKey("region"));
			List<Spestjad> jobAmtDetails = spJejb.getAll(costCentre);			
					
			transportationDetailsList = new ArrayList<ListObject>();
			excavationDetailsList = new ArrayList<ListObject>();
			erectionDetailsList = new ArrayList<ListObject>();
			insStaysDetailsList = new ArrayList<ListObject>();
			wireDetailsList = new ArrayList<ListObject>();
			conductorDetailsList= new ArrayList<ListObject>(); 	
			reapirExistingDetailsList = new ArrayList<ListObject>(); 
			changeExistingDetailsList = new ArrayList<ListObject>();
			removalDetailsList  = new ArrayList<ListObject>();	
			removalPoleDetailsList = new ArrayList<ListObject>();
			
			Iterator<Spestjad> itr = jobAmtDetails.iterator();
				
			while (itr.hasNext()) {  
				Spestjad spestjad=itr.next();
				
				String catogoryID = spestjad.getJobCategoryId();
				if(catogoryID.equals("1")){	
					if(spestjad.getUnitPrice()!=null){
						ListObject li = new ListObject(spestjad.getUnitPrice().toEngineeringString(),spestjad.getJobDescription());
						transportationDetailsList.add(li);
					}
				}
				
				if(catogoryID.equals("2")){	
					if(spestjad.getUnitPrice()!=null){
						ListObject li = new ListObject(spestjad.getUnitPrice().toEngineeringString(),spestjad.getJobDescription());
						excavationDetailsList.add(li);
					}
				}
				
				if(catogoryID.equals("3")){	
					if(spestjad.getUnitPrice()!=null){
						ListObject li = new ListObject(spestjad.getUnitPrice().toEngineeringString(),spestjad.getJobDescription());
						erectionDetailsList.add(li);
					}
				}
				
				if(catogoryID.equals("4")){	
					if(spestjad.getUnitPrice()!=null){
						ListObject li = new ListObject(spestjad.getUnitPrice().toEngineeringString(),spestjad.getJobDescription());
						insStaysDetailsList.add(li);
					}
				}
				
				if(catogoryID.equals("5")){	
					if(spestjad.getUnitPrice()!=null){
						ListObject li = new ListObject(spestjad.getUnitPrice().toEngineeringString(),spestjad.getJobDescription());
						wireDetailsList.add(li);
					}
				}
				
				if(catogoryID.equals("6")){	
					if(spestjad.getUnitPrice()!=null){
						ListObject li = new ListObject(spestjad.getUnitPrice().toEngineeringString(),spestjad.getJobDescription());
						conductorDetailsList.add(li);
					}
				}
				
				if(catogoryID.equals("7")){	
					if(spestjad.getUnitPrice()!=null){
						abAmount = spestjad.getUnitPrice().toEngineeringString();
					}
				}
				
				if(catogoryID.equals("8")){	
					if(spestjad.getUnitPrice()!=null){
						ListObject li = new ListObject(spestjad.getUnitPrice().toEngineeringString(),spestjad.getJobDescription());
						reapirExistingDetailsList.add(li);
					}
				}
				
				if(catogoryID.equals("9")){	
					if(spestjad.getUnitPrice()!=null){
						ListObject li = new ListObject(spestjad.getUnitPrice().toEngineeringString(),spestjad.getJobDescription());
						changeExistingDetailsList.add(li);
					}
				}
				
				if(catogoryID.equals("10")){	
					if(spestjad.getUnitPrice()!=null){
						ListObject li = new ListObject(spestjad.getUnitPrice().toEngineeringString(),spestjad.getJobDescription());
						removalDetailsList.add(li);
					}
				}
				
				if(catogoryID.equals("12")){	
					if(spestjad.getUnitPrice()!=null){
						ListObject li = new ListObject(spestjad.getUnitPrice().toEngineeringString(),spestjad.getJobDescription());
						removalPoleDetailsList.add(li);
					}
				}
			}			
			
			////////////////////////////////////////////////////////////////
				
			spestcntList=ejb.getAll(costCentre);
			Iterator<Spestcnt> it1 = spestcntList.iterator();
							
			String bondNo="";
			String laborRate="";
			String consumerName="";	
				
			while (it1.hasNext()) {  
				Spestcnt spestcnt1=it1.next();
				
				String scontractorID = spestcnt1.getId().getContractorId();
				listContractor.add(spestcnt1.getContractorName());	
				listContractorID.add(scontractorID);	
				
				 if(contractor!=null && !contractor.equals("-1") &&  contractorID!=null && !contractorID.equals("-1") )
				 {										 
					 if(contractorID.equals(scontractorID.trim()))
					 {
						  bondNo =spestcnt1.getBondNo();
						  laborRate=spestcnt1.getPerformanceAmount().toString();
					 }	
				 }
			   } 	
			
			 if(contractor!=null && !contractor.equals("-1") &&  contractorID!=null && !contractorID.equals("-1") )
			 {			
				SpestcndEjb spejb=new SpestcndEjb(getSessionKey("region"));
				spestcndList=spejb.getJobList(contractorID,costCentre,JobProcessStatus.FINISHED);
				Iterator<Spestcnd> it2 = spestcndList.iterator();
				while (it2.hasNext()) {  
					 Spestcnd spestcnd=it2.next();
					 String projectNo = spestcnd.getId().getProjectNo();
					 listjobNumber.add(projectNo);
					
					 if(jobNumber!=null && !jobNumber.equals("-1") && jobNumber.trim().equals(projectNo.trim()))
					 {
						consumerName = spestcnd.getConsumerName();
					 }
				} 				
				estimatedBy = getSessionKey("userName");				
				Format format=new Format();						
				billDate =format.FormatStringDate(new Date());
							
			
			 }else{
				 estimatedBy = "";					
				 billDate="";
				 resetPage();
			 }
			 
			 if(jobNumber!=null && !jobNumber.equals("-1")&& contractor!=null && !contractor.equals("-1") &&  contractorID!=null && !contractorID.equals("-1")){	
				
				 JobEjb jejb=new JobEjb(getSessionKey("region"));	
				 StdDetail stDetail = jejb.getStdDetail(jobNumber.trim(), costCentre);
				 		if(stDetail!=null){					
												
								if(stDetail.getServiceDistance()!=null)
									distanceToPlace = stDetail.getServiceDistance().toString();
								if(stDetail.getLineLength()!=null){
									serviceLength = stDetail.getLineLength().toString();
									
								}
								
								
						}
						
				
						
						PcesthmtEjb thmtejb=new PcesthmtEjb(getSessionKey("region"));
						Pcesthmt pst = thmtejb.findByJobNo(jobNumber.trim(), costCentre);
							if(pst!=null){
								String description = pst.getDescr();
								jobDescription = description;
							}
						
				name = consumerName;				 
			 }else{
				 resetPage();
			 }
 
					
		}catch(Exception ex){
			System.out.println("The error is........."+ex);
			return "error";
		}
		transportnofKm = "10";
		return "success";
	}
	
	public List<ListObject> getReapirExistingDetailsList() {
		return reapirExistingDetailsList;
	}

	public void setReapirExistingDetailsList(
			List<ListObject> reapirExistingDetailsList) {
		this.reapirExistingDetailsList = reapirExistingDetailsList;
	}

	private void resetPage(){
		
		distanceToPlace = "";
		serviceLength = "";		
		nofStays = "";
		nofStructs = "";
		nofSpans = "";
		jobDescription = "";
		name = "";
	}
	
 	public void setLoggedData() {
        
        log.info(getSession());
        setLoggedInUserId(getSessionKey("userName"));
        setCostCenterNo(getSessionKey("costCenterNo"));
        setCsc(getSessionKey("costCenterName"));
    }
 	
	public String close()
	{
		setLoggedData();
		return "closed";
	}
	
	public String bill()
	{
		setLoggedData();
		
		try{
			if(!jobNumber.trim().equals("")){
				SpestcndEjb ejb =new SpestcndEjb(getSessionKey("region"));
				String costCentre = getSessionKey("costCenterNo");
				List<Spestcnd> cndList = ejb.findByJobNo(jobNumber.trim(),costCentre);
				
				if(cndList!=null && cndList.size()>0){
					Spestcnd spestcndlistItem = cndList.get(0);
					if(spestcndlistItem!=null)
					{
						spestcndlistItem.setStatus(JobProcessStatus.BILLED);
						ejb.updateSpestcnd(spestcndlistItem);
					}					
				}
				SpestbipEjb bipEjb=new SpestbipEjb(getSessionKey("region"));
				SpestbipPK spestbipPK = new SpestbipPK();
				
				spestbipPK.setBillSettlementNo(billReferenceNo.trim());
				spestbipPK.setDeptId(costCentre);
				spestbipPK.setProjectNo(jobNumber.trim());
				
				Spestbip spestbip=new Spestbip();
				spestbip.setId(spestbipPK);
				spestbip.setBillDate(new Date());
				spestbip.setTotalBillAmt(new BigDecimal(grandTotal1));
							
				bipEjb.createSpestbip(spestbip);
			}
			
		}catch(Exception ex){
			return "error";
		}
		
		execute();
		return "success";
	}

}
