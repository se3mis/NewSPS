package calendarBS.web;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import job.model.Spestcnt;
import job.model.SpestcntPK;
import job.service.SpestcntEjb;

import masters.service.ProvinceDetailsMasterEjb;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.SessionAware;

import reports.web.report;
import security.service.SecurityEjb;
import util.common.AppStatus;
import util.common.AppointmentStatus;
import util.common.Constants;
import util.common.Format;
import util.common.Path;
import util.common.StandardEstimateStatus;
import application.model.Application;
import application.service.ApplicationEjb;
import application.service.WiringLandDetailConEjb;

import com.opensymphony.xwork2.ActionSupport;

import estimate.model.AllocationSummary;
import estimate.model.AllocationSummaryDisplay;
import estimate.model.Spestedy;
import estimate.model.SpestedyCons;
import estimate.model.SpestedyConsPK;
import estimate.model.SpestedyPK;
import estimate.model.Spstdesthmt;
import estimate.model.SpstdesthmtPK;
import estimate.service.AllocationSummaryEjb;
import estimate.service.EstimateEjb;
import estimate.service.SpestedyConEjb;
import estimate.service.SpestedyEjb;
import estimate.service.SpstdesthmtEjb;

public class DiaryEntryBS extends ActionSupport implements SessionAware, ParameterAware{

	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(DiaryEntryBS.class);
	private Map<String, Object> session;
	private Map <String, String[]> parameters;
	private String path;
	private String loggedInUserId;
	private String costCenterNo;
	private String applicationNumber;
	private String aprDate;
	private String appointedUserName;
	private String sessionName;
	private Date appointmentDate;
	private String statusMsg;
	private String selCostCenter;
	private String appointmentType;
	private String csc;
	private List<String> listsessionName;
	private List<String> listuserName;	
	private List<String> listapplicationNumber;
	private List<String> listappointmentType;
	private List<String> listAlluserName;	
	//private List<String> appointedUserName.
	private String elecSupName;
	private String contractor;
	private String construcRef;
	private String selectedConstructionEstNumber;
	private InputStream fileInputStream;
	private String electorate;
	private String proposerName;
	private String schemaName;
	private String schemaExtention;
	private String representative;
	private String repContact;
	private List<SpestedyCons> ativeAppoinmentsListConMain;
	private List<AllocationSummaryDisplay> allocationSummary;
	private static final String viewPath="";
	
	private String lblError = null;
	private String lblSuccess = null;
	Map<String, List<AllocationSummary>>  allocationSummaryList = new HashMap<String, List<AllocationSummary>>();
	Map<String, List<AllocationSummaryDisplay>>  allocationSummaryListByCommRef = new HashMap<String, List<AllocationSummaryDisplay>>();
	
	private String branchType;
	private String smcType;
	
	public String execute(){		
		try{
			setLoggedData();
			setPath(newPath);
			listsessionName = new ArrayList<String>(); 
			listsessionName.add("Morning");
			listsessionName.add("Afternoon");
			
			listuserName  = new ArrayList<String>(); 	
			listAlluserName  = new ArrayList<String>();
			costCenterNo = getSessionKey("costCenterNo");	
			branchType = getSessionKey("branchType");	
			smcType = getSessionKey("smcType");	
			//DW
			if (costCenterNo.equals("541.00") || costCenterNo.equals("542.00") || costCenterNo.equals("547.00") || costCenterNo.equals("548.00")) {
				if (getSession().containsKey("ccn")){
					costCenterNo = getSessionKey("ccn");	
					
				}else{
					costCenterNo = getSessionKey("costCenterNo");	
				}	
			}else{
				costCenterNo= getSessionKey("costCenterNo");
				
			}
			//DW
			
			SecurityEjb secejb=new SecurityEjb(getSessionKey("region"));
			SpestedyConEjb spestedyConEjb=new SpestedyConEjb(getSessionKey("region"));
			//DW
			List<String> usersList = new ArrayList<String>();
			usersList.add("ES");
			usersList.add("EE");
			List<String> userList = secejb.getUserList(costCenterNo,usersList);

			//DW
			
			Iterator<String> usit = userList.iterator();
			
			 while (usit.hasNext()) {        	 
		        	String esUser=usit.next();
		        	listuserName.add(esUser);		       	        	        	
		          } 
			
			setSelCostCenter(costCenterNo);
			listappointmentType = new ArrayList<String>();
			listappointmentType.add(AppointmentStatus.listappointmentType);
			/*ApplicationEjb appEjb=new ApplicationEjb(getSessionKey("region"));
			//DW
			List<Application> applicationLis= appEjb.getApplicationList(costCenterNo, "C");
			//DW
			*/
			SpstdesthmtEjb spstdesthmtEjb = new SpstdesthmtEjb(getSessionKey("region"));
			List<Long> status = new ArrayList<Long>();
			status.add(StandardEstimateStatus.SEND_FOR_CONSTRUCTION_MAINTENANCE.getKey());
			listapplicationNumber = spstdesthmtEjb.loadConsMaintenanceEstimaNos(costCenterNo,status,getSessionKey("region"));
			if(listapplicationNumber == null){
				listapplicationNumber = new ArrayList<String>();
			}
			//listapplicationNumber = spstdesthmtEjb.loadConsMaintenanceEstimaNos(costCenterNo,status,getSessionKey("region")); 
				
			
			/*listapplicationNumber = new ArrayList<String>(); 
			Iterator<Application> it = applicationLis.iterator();		    	   	
	        while (it.hasNext()) {        	 
	        	Application applc=it.next();
	        	listapplicationNumber.add(applc.getApplicationNo()); 		       	        	        	
	        } 	*/        
			
	        appointedUserName = getSessionKey("userName");
	        appointmentType = AppointmentStatus.listappointmentType;
	        sessionName = "Morning";
	        
	       /* ProvinceDetailsMasterEjb promasEjb = new ProvinceDetailsMasterEjb();
			   String branchType = promasEjb.getBranchType(getCostCenterNo(), getSessionKey("region"));
			   if(branchType != null){
				  setBranchType(branchType);
			   }else{
				   setBranchType(Constants.DEFAULT_BRANCHCODE);
			   }
			   
	       
	        */
			HttpServletRequest request = ServletActionContext.getRequest();
			String selectedDt=request.getParameter("selDate");
			if(selectedDt!=null && selectedDt.length()>0){
				String formatteStr[] = selectedDt.split("/");
				if(formatteStr.length>2){
					Format format=new Format();
					String dtStr = formatteStr[2].trim()+"-"+formatteStr[1].trim()+"-"+formatteStr[0].trim();
					appointmentDate = format.StrToDate(dtStr);					
				}
						
			}else			
				appointmentDate = new Date();
			//DW
			if (costCenterNo.equals("541.00") || costCenterNo.equals("542.00") || costCenterNo.equals("547.00") || costCenterNo.equals("548.00")) {
				if (getSession().containsKey("applicationNo")){
					setApplicationNumber(getSessionKey("applicationNo"));
					getSession().remove("applicationNo");
				}
			}
			ativeAppoinmentsListConMain =  spestedyConEjb.getAtiveAppoinmentsforDept(costCenterNo,getSessionKey("region") );
			EstimateEjb estimateEjb=new EstimateEjb(getSessionKey("region"));
			List<AllocationSummaryDisplay>  list = estimateEjb.findEstimateSummary("510.00/EBS/12/0005","510.00");
			//DW
		}catch(Exception ex){
			ex.printStackTrace();
			return "error";
		}
		return "success";
	}
		
		public String getPath() {
		return path;
	}
		public String directFromMain(){
			costCenterNo = getSessionKey("costCenterNo");
			setSelCostCenter(costCenterNo);
			SpestedyConEjb spestedyConEjb=new SpestedyConEjb(getSessionKey("region"));
			SecurityEjb secejb=new SecurityEjb(getSessionKey("region"));
			List<String> usersList = new ArrayList<String>();
			usersList.add("ES");
			usersList.add("EE");
			List<String> userList = secejb.getUserList(costCenterNo,usersList);
			//DW
			
			
			Iterator<String> usit = userList.iterator();
			
			listuserName  = new ArrayList<String>(); 	
			listAlluserName  = new ArrayList<String>();
			while (usit.hasNext()) {        	 
		        	String esUser=usit.next();
		        	listuserName.add(esUser);		       	        	        	
			} 
			listsessionName = new ArrayList<String>(); 
			listsessionName.add("Morning");
			listsessionName.add("Afternoon");
			 sessionName = "Morning";
			listappointmentType = new ArrayList<String>();
			listappointmentType.add(AppointmentStatus.listappointmentType);
			ApplicationEjb appEjb=new ApplicationEjb(getSessionKey("region"));
			
			listapplicationNumber = new ArrayList<String>(); 
			
	        
	        HttpServletRequest request = ServletActionContext.getRequest();
			//String sessionKey= (String) request.getSession().getAttribute("region");
			String estimateNo=request.getParameter("estNo");
			//String costCen=request.getParameter("costCen");
			
			
			if(estimateNo != null){
				listapplicationNumber.add(estimateNo);
				
				//request.getSession().setAttribute("estimateNos",wEstimationRefNos);
			}
			
			ProvinceDetailsMasterEjb promasEjb = new ProvinceDetailsMasterEjb();
			   String branchType = promasEjb.getBranchType(getCostCenterNo(), getSessionKey("region"));
			   if(branchType != null){
				  setBranchType(branchType);
			   }else{
				   setBranchType(Constants.DEFAULT_BRANCHCODE);
			   }
			   
			   
			ativeAppoinmentsListConMain =  spestedyConEjb.getAtiveAppoinmentsforDept(costCenterNo,getSessionKey("region") );
			EstimateEjb estimateEjb=new EstimateEjb(getSessionKey("region"));
			List<AllocationSummaryDisplay>  list = estimateEjb.findEstimateSummary("510.00/EBS/12/0005","510.20");
			allocationSummary = list;
	        return "success";
	}
	public void setPath(String path) {
		this.path = path;
	}

		@Override
		public void setSession(Map<String, Object> session) {
			this.session=session;
			
		}

		public Map<String, String[]> getParameters() {
			return parameters;
		}
		
		public List<AllocationSummaryDisplay> getAllocationSummary() {
			return allocationSummary;
		}

		public void setAllocationSummary(
				List<AllocationSummaryDisplay> allocationSummary) {
			this.allocationSummary = allocationSummary;
		}

		@Override
		public void setParameters(Map<String, String[]> parameters) {
			this.parameters=parameters;
			
		}
		
		public String getSessionKey(String key) {
	        return getSession().get(key).toString();
	    }
		
		public Map<String, Object> getSession() {
			return session;	
		}
		
	 	public void setLoggedData() {       
	        log.info(getSession());
	        setLoggedInUserId(getSessionKey("userName"));
	        setCostCenterNo(getSessionKey("costCenterNo"));
	        setCsc(getSessionKey("costCenterName"));       
	    }
	 	
	 	public String close(){
	 		setLoggedData() ; 
			return "closed";
	 	}
	 	
	 	public String addAppointment(){
	 		HttpServletRequest request = ServletActionContext.getRequest();
	 		try{
	 			Format format=new Format();
	 			setLoggedData() ; 	
				SpestedyConEjb ejb=new SpestedyConEjb(getSessionKey("region"));
				SpestedyCons spestedy = new SpestedyCons();
				SpestedyConsPK id= new SpestedyConsPK();
				//DW
				if (costCenterNo.equals("541.00") || costCenterNo.equals("542.00") || costCenterNo.equals("547.00") || costCenterNo.equals("548.00")) {
					if (getSession().containsKey("ccn")){
						id.setDeptId(getSessionKey("ccn"));	
						getSession().remove("ccn");
					}else{
						id.setDeptId(costCenterNo);	
					}	
				}else{
					id.setDeptId(costCenterNo);
					
				}
				
				spestedy.setId(id);	
				Calendar calandar= Calendar.getInstance();
				spestedy.setAppointmentDate(format.StrToDate(getAprDate().substring(0, 10)));
				String des = getDescription();
				System.out.println("Application Number " + des);
				if(des != null || !des.equalsIgnoreCase("")){
					System.out.println("Application Number2 " + des);
					des = des.replace('\n', ' ');
					des = des.replace('\r', ' ');
				}
				System.out.println("Application Number1 " + des);
				
				spestedy.setDescription(des);
				spestedy.setTimeSession(sessionName);
				spestedy.setAllocatedTo(appointedUserName);	
				spestedy.setAllocatedBy(getSessionKey("userName"));
				spestedy.setAllocatedDate(calandar.getTime());
				spestedy.setAllocatedTime(format.FormatTime());
				spestedy.setAppoinmentType(appointmentType);
				spestedy.setStatus(AppointmentStatus.getAptStsVal(AppointmentStatus.ACTIVE));
				spestedy.setReferenceNo(applicationNumber);
				if(construcRef != null && !construcRef.equals("")){
					spestedy.setWestimateNo(construcRef);
					//ejb.makeAnAppointment(spestedy);
				}else if(selectedConstructionEstNumber != null){
					
					spestedy.setWestimateNo(selectedConstructionEstNumber);
					//ejb.makeAnAppointment(spestedy);
				}else{
					spestedy.setWestimateNo(applicationNumber);
					//ejb.addAppointment(spestedy);
				}
				ejb.makeAnAppointment(spestedy);
				/*if(!costCenterNo.equalsIgnoreCase("530.20") && !costCenterNo.equalsIgnoreCase("550.20") && !costCenterNo.equalsIgnoreCase("550.20")){
					finishAllocation();
				}*/
				
				Map<String, AllocationSummaryDisplay> allocationSummaryDisplayList =  (Map<String, AllocationSummaryDisplay>) request.getSession().getAttribute("allocationSummaryDisplayList");
				allocationSummaryList =  (Map<String, List<AllocationSummary>>) request.getSession().getAttribute("allocationSummaryList");
				allocationSummaryListByCommRef =  (Map<String, List<AllocationSummaryDisplay>>) request.getSession().getAttribute("allocationSummaryListByCommRef");
				if(allocationSummaryList == null){
					allocationSummaryList = new HashMap<String, List<AllocationSummary>>();
				}
				if(allocationSummaryListByCommRef == null){
					allocationSummaryListByCommRef = new HashMap<String, List<AllocationSummaryDisplay>>();
				}
				if(allocationSummaryDisplayList != null){
					
						List<AllocationSummaryDisplay> listAllocation = new ArrayList<AllocationSummaryDisplay>(allocationSummaryDisplayList.values());
						List<AllocationSummary>  allocationSummList = new ArrayList<AllocationSummary>();
						List<AllocationSummaryDisplay>  allocationSummListByCommRef = new ArrayList<AllocationSummaryDisplay>();
						for(AllocationSummaryDisplay dis : listAllocation){
							if(dis.getAllocatedLineLength() != null){
								AllocationSummary summ = new AllocationSummary();
								populateAllocationSummary(dis,summ);
								allocationSummList.add(summ);
								allocationSummListByCommRef.add(dis);
								AllocationSummaryEjb ejbSumm = new AllocationSummaryEjb(getSessionKey("region"));
								ejbSumm.createAutoIdAllocationSummary(summ, getSessionKey("region"));
							}
							allocationSummaryList.put(dis.getConstructionRef(), allocationSummList);
							allocationSummaryListByCommRef.put(applicationNumber, allocationSummListByCommRef);
						}
						
						request.getSession().setAttribute("allocationSummaryDisplayList",allocationSummaryDisplayList);
						request.getSession().setAttribute("allocationSummaryList",allocationSummaryList);
						request.getSession().setAttribute("allocationSummaryListByCommRef",allocationSummaryListByCommRef);
				}
				//update spstdesthmt as job allocated
				//SpstdesthmtEjb ejbhmt = new SpstdesthmtEjb(getSessionKey("region"));
				//ejbhmt.updateAllocatedEstimateStatus(applicationNumber, costCenterNo, StandardEstimateStatus.JOB_ALLOCATED.getKey(),  getSessionKey("region"));
				
				
				ClearForm();
				execute();
				
				
				statusMsg = "Appointment added successfully";
				return "success";
				
	 		}catch(Exception ex){
	 			System.out.println("The eror is...................:"+ex.getMessage());
	 			return "error";
	 			
	 		}
	 		
	 	}
	 	private AllocationSummary populateAllocationSummary(AllocationSummaryDisplay dis,AllocationSummary summary){
	 		
	 		summary.setEstimateNo(dis.getEstimateNo());
	 		summary.setConstructionReference(dis.getConstructionRef());
	 		summary.setAllocatedLineLength(dis.getAllocatedLineLength());
	 		summary.setLineId(dis.getLineId());
	 		summary.setLineSummaryId(dis.getLineSummaryId());
	 		summary.setTotalLineLength(dis.getTotalLineLength());
	 		summary.setDeptId(costCenterNo);
	 		
	 		return summary;
	 		
	 	}
	 	public String finishAllocation(){
	 		System.out.println("EE : 1");
	 		try{
	 			Format format=new Format();
	 			System.out.println("EE : 2");
	 			setLoggedData() ; 	
	 			System.out.println("EE : 3");
	 			SpstdesthmtEjb ejbhmt = new SpstdesthmtEjb(getSessionKey("region"));
	 			System.out.println("EE : 4");
	 			ProvinceDetailsMasterEjb promasEjb = new ProvinceDetailsMasterEjb();
	 			System.out.println("EE : 5");
				String commercialId = promasEjb.getCommercialId(getCostCenterNo(),  getSessionKey("region"));
				System.out.println("EE : 6");
				  
				//update spstdesthmt as job allocated
			   if(commercialId != null){
				   System.out.println("EE : 7");
				   	SpstdesthmtPK spstdesthmtPK = new SpstdesthmtPK();
					spstdesthmtPK.setDeptId(commercialId);
					spstdesthmtPK.setStdNo(applicationNumber);
					spstdesthmtPK.setApplicationNo(applicationNumber);
					System.out.println("EE : 7" +applicationNumber );
					Spstdesthmt hmt = ejbhmt.findById(spstdesthmtPK, getSessionKey("region"));
			  
					System.out.println("EE : 7" +hmt );
					if(hmt != null){
						
						System.out.println("EE : "+applicationNumber + "  ");
						ejbhmt.updateAllocatedEstimateStatus(applicationNumber, costCenterNo, StandardEstimateStatus.JOB_ALLOCATED.getKey(),  getSessionKey("region"));
					}else{
						ApplicationEjb ejb = new ApplicationEjb(getSessionKey("region"));
						
						   if(getSessionKey("branchType").equalsIgnoreCase(Constants.BRANCHTYPE_ENERGY_MANAGEMENT) 
								   || getSessionKey("branchType").equalsIgnoreCase(Constants.BRANCHTYPE_AREA_UNIT) 
								   || getSessionKey("branchType").equalsIgnoreCase(Constants.BRANCHTYPE_DISTRIBUTION_AND_MAINTENANCE)
								   || getSessionKey("branchType").equalsIgnoreCase(Constants.BRANCHTYPE_AREA_MAINTENANCE)
								   || getSessionKey("branchType").equalsIgnoreCase(Constants.BRANCHTYPE_CONSTRUCTION)){
							  
							   ejb.changeStatusApplication(applicationNumber, costCenterNo,AppStatus.ALLOCATED, getSessionKey("region"));
						   }else{
							   ejb.changeStatusApplication(applicationNumber, commercialId,AppStatus.ALLOCATED, getSessionKey("region"));
							   
						    }
						   }
				
			   }
	 			
				ClearForm();
				execute();
				statusMsg = "Finished allocaton ";
				return "success";
				
	 		}catch(Exception ex){
	 			System.out.println("The eror is...................:"+ex.getMessage());
	 			return "error";
	 			
	 	}
	 		
	 	}
	 	private void ClearForm(){
	 		
	 		aprDate = "";
	 		appointmentDate = null;
	 		appointedUserName = "-- Please Select --";
	 		sessionName = "-- Please Select --";
	 		applicationNumber = "-- Please Select --";
	 		appointmentType = "-- Please Select --";
	 		description ="";
	 		
	 	}
	 	
	 	
	 	public String getApplicationNumber() {
			return applicationNumber;
		}

		public void setApplicationNumber(String applicationNumber) {
			this.applicationNumber = applicationNumber;
		}


		public List<String> getListappointmentType() {
			return listappointmentType;
		}

		public void setListappointmentType(List<String> listappointmentType) {
			this.listappointmentType = listappointmentType;
		}

		public List<String> getListapplicationNumber() {
			return listapplicationNumber;
		}

		public void setListapplicationNumber(List<String> listapplicationNumber) {
			this.listapplicationNumber = listapplicationNumber;
		}


		public List<String> getListAlluserName() {
			return listAlluserName;
		}

		public void setListAlluserName(List<String> listAlluserName) {
			this.listAlluserName = listAlluserName;
		}

		public String getAppointmentType() {
			return appointmentType;
		}

		public void setAppointmentType(String appointmentType) {
			this.appointmentType = appointmentType;
		}

		public String getSelCostCenter() {
			return selCostCenter;
		}

		public void setSelCostCenter(String selCostCenter) {
			this.selCostCenter = selCostCenter;
		}

		public String getStatusMsg() {
			return statusMsg;
		}

		public void setStatusMsg(String statusMsg) {
			this.statusMsg = statusMsg;
		}
		

		public Date getAppointmentDate() {
			return appointmentDate;
		}

		public void setAppointmentDate(Date appointmentDate) {
			this.appointmentDate = appointmentDate;
		}

		public String getAprDate() {
			return aprDate;
		}

		public void setAprDate(String aprDate) {
			this.aprDate = aprDate;
		}

		public String getAppointedUserName() {
			return appointedUserName;
		}

		public void setAppointedUserName(String appointedUserName) {
			this.appointedUserName = appointedUserName;
		}

		public String getSessionName() {
			return sessionName;
		}

		public void setSessionName(String sessionName) {
			this.sessionName = sessionName;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		private String description;
		
		public List<String> getListuserName() {
			return listuserName;
		}

		public void setListuserName(List<String> listuserName) {
			this.listuserName = listuserName;
		}

		private static final String newPath="Calendar>New Appointment";
		
		public static String getNewpath() {
			return newPath;
		}

		public List<String> getListsessionName() {
			return listsessionName;
		}

		public void setListsessionName(List<String> listsessionName) {
			this.listsessionName = listsessionName;
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
		
		public String GenerateAllocationOfJobLetter(){
			HttpServletRequest request = ServletActionContext.getRequest();
			
			String costCenterNo=(String) request.getSession().getAttribute("costCenterNo");
			String userId= (String) request.getSession().getAttribute("userName");
			
			String region= (String) request.getSession().getAttribute("region");
			String smcType= (String) request.getSession().getAttribute("smcType");
			
		
			Path path = new Path();
			
			report rept = new report();
			String REPORT_DIRECTORY = "" ;
			String EXPORT_REPORT_DIRECTORY = "";
			if (System.getProperty("os.name").equals("Windows XP") || System.getProperty("os.name").equals("Windows 7") ){
				REPORT_DIRECTORY = path.getReportPath("REPORT_DIRECTORY");
				EXPORT_REPORT_DIRECTORY = path.getReportPath("EXPORT_REPORT_DIRECTORY");

			}else{
				REPORT_DIRECTORY = path.getReportPath("REPORT_DIRECTORY_LINUX");
				EXPORT_REPORT_DIRECTORY = path.getReportPath("EXPORT_REPORT_DIRECTORY_LINUX");
			}
			

			SecurityEjb ejb=new SecurityEjb(region);
			
			String authCostCenters = "";
			List<String> authCost = ejb.getAuthorizedCostCenters(userId);
			
			String authCostList[] = authCost.toArray(new String[0]);
			

			ProvinceDetailsMasterEjb promasEjb = new ProvinceDetailsMasterEjb();
			String costCenterNoCommercial = null;
			try {
				costCenterNoCommercial = promasEjb.getCommercialId(costCenterNo,  getSessionKey("region"));
			} catch (Exception e) {
				return ERROR;
			}
		
			if(costCenterNoCommercial == null){
				String deptid= costCenterNo.substring(0, 3);
				costCenterNoCommercial = deptid.concat(".00");

			}
			
			HashMap<String, Object> param = new HashMap<String, Object>();
		 

			System.out.println("Application Number :  " + getApplicationNumber());
			System.out.println("CC Number :  " + costCenterNoCommercial);
			param.put("@appNo", "'"+getApplicationNumber()+"'");
			param.put("@costctr","'"+costCenterNoCommercial+"'");
			param.put("@coordinating", "'"+getElecSupName()+"'");
		
		
			if(getContractor() != null){
				SpestcntEjb ejbCnt=new SpestcntEjb(getSessionKey("region"));	
				SpestcntPK spestcntpk = new SpestcntPK();
				spestcntpk.setContractorId(getContractor());
				spestcntpk.setDeptId(costCenterNo);
				
				 
				Spestcnt spestcnt=ejbCnt.findById(spestcntpk);
				if(spestcnt != null){
					param.put("@conname","'"+spestcnt.getContractorName()+"'");
				}else{
					param.put("@conname","'"+getContractor()+"'");
				}
			}
			
			
			
			param.put("@esname","'"+getAppointedUserName()+"'");
			if(getConstrucRef() != null && getSelectedConstructionEstNumber().equalsIgnoreCase("-1")){
				param.put("@construRef","'"+getConstrucRef()+"'");
			}else if(!getSelectedConstructionEstNumber().equalsIgnoreCase("-1")){
				param.put("@construRef","'"+getSelectedConstructionEstNumber()+"'");
			}
			param.put("@workScope","'"+getDescription()+"'");

			String fileName = null;
			
			fileName = rept.generateReport("allocation_Of_constructionJob_Uva",param,getSession(),REPORT_DIRECTORY,EXPORT_REPORT_DIRECTORY);
			
			
		
			System.out.println("xxxxxxxxx"+ fileName);
			
			try {
				if (fileName == "")
				{
					execute();
					setLblError("Error occured while generating the report");
					return SUCCESS;
				}
				fileInputStream = new FileInputStream(fileName);
			} catch (FileNotFoundException e) {
				
				e.printStackTrace();
			}
			
			return "successprint";


		}
		
		public String GenerateAllocationOfMNTJobLetter(){
			HttpServletRequest request = ServletActionContext.getRequest();
			
			String costCenterNo=(String) request.getSession().getAttribute("costCenterNo");
			String userId= (String) request.getSession().getAttribute("userName");
			
			String region= (String) request.getSession().getAttribute("region");
			String smcType= (String) request.getSession().getAttribute("smcType");
			
		
			Path path = new Path();
			
			report rept = new report();
			String REPORT_DIRECTORY = "" ;
			String EXPORT_REPORT_DIRECTORY = "";
			if (System.getProperty("os.name").equals("Windows XP") || System.getProperty("os.name").equals("Windows 7") ){
				REPORT_DIRECTORY = path.getReportPath("REPORT_DIRECTORY");
				EXPORT_REPORT_DIRECTORY = path.getReportPath("EXPORT_REPORT_DIRECTORY");

			}else{
				REPORT_DIRECTORY = path.getReportPath("REPORT_DIRECTORY_LINUX");
				EXPORT_REPORT_DIRECTORY = path.getReportPath("EXPORT_REPORT_DIRECTORY_LINUX");
			}
			

			SecurityEjb ejb=new SecurityEjb(region);
			
			String authCostCenters = "";
			List<String> authCost = ejb.getAuthorizedCostCenters(userId);
			
			String authCostList[] = authCost.toArray(new String[0]);
			

			ProvinceDetailsMasterEjb promasEjb = new ProvinceDetailsMasterEjb();
			String costCenterNoCommercial = null;
			try {
				costCenterNoCommercial = promasEjb.getCommercialId(costCenterNo,  getSessionKey("region"));
			} catch (Exception e) {
				return ERROR;
			}
		
			if(costCenterNoCommercial == null){
				String deptid= costCenterNo.substring(0, 3);
				costCenterNoCommercial = deptid.concat(".00");

			}
			
			HashMap<String, Object> param = new HashMap<String, Object>();
		 

			System.out.println("Application Number :  " + getApplicationNumber());
			System.out.println("CC Number :  " + costCenterNoCommercial);
			param.put("@appNo", "'"+getApplicationNumber()+"'");
			param.put("@costctr","'"+costCenterNoCommercial+"'");
			param.put("@coordinating", "'"+getElecSupName()+"'");
		
		
			if(getContractor() != null){
				SpestcntEjb ejbCnt=new SpestcntEjb(getSessionKey("region"));	
				SpestcntPK spestcntpk = new SpestcntPK();
				spestcntpk.setContractorId(getContractor());
				spestcntpk.setDeptId(costCenterNo);
				
				 
				Spestcnt spestcnt=ejbCnt.findById(spestcntpk);
				if(spestcnt != null){
					param.put("@conname","'"+spestcnt.getContractorName()+"'");
				}else{
					param.put("@conname","'"+getContractor()+"'");
				}
			}
			
			
			
			param.put("@esname","'"+getAppointedUserName()+"'");
			if(getConstrucRef() != null && getSelectedConstructionEstNumber().equalsIgnoreCase("-1")){
				param.put("@construRef","'"+getConstrucRef()+"'");
			}else if(!getSelectedConstructionEstNumber().equalsIgnoreCase("-1")){
				param.put("@construRef","'"+getSelectedConstructionEstNumber()+"'");
			}
			param.put("@workScope","'"+getDescription()+"'");

			String fileName = null;
			
			fileName = rept.generateReport("allocation_Of_MNTJob_Uva",param,getSession(),REPORT_DIRECTORY,EXPORT_REPORT_DIRECTORY);
			
			
		
			System.out.println("xxxxxxxxx"+ fileName);
			
			try {
				if (fileName == "")
				{
					execute();
					setLblError("Error occured while generating the report");
					return SUCCESS;
				}
				fileInputStream = new FileInputStream(fileName);
			} catch (FileNotFoundException e) {
				
				e.printStackTrace();
			}
			
			return "successprint";


		}
		
		
		
		public String GenerateAllocationOfJobLetterCon(){
			HttpServletRequest request = ServletActionContext.getRequest();
			
			String costCenterNo=(String) request.getSession().getAttribute("costCenterNo");
			String userId= (String) request.getSession().getAttribute("userName");
			
			String region= (String) request.getSession().getAttribute("region");
			String smcType= (String) request.getSession().getAttribute("smcType");
			
		
			Path path = new Path();
			
			report rept = new report();
			String REPORT_DIRECTORY = "" ;
			String EXPORT_REPORT_DIRECTORY = "";
			if (System.getProperty("os.name").equals("Windows XP") || System.getProperty("os.name").equals("Windows 7") ){
				REPORT_DIRECTORY = path.getReportPath("REPORT_DIRECTORY");
				EXPORT_REPORT_DIRECTORY = path.getReportPath("EXPORT_REPORT_DIRECTORY");

			}else{
				REPORT_DIRECTORY = path.getReportPath("REPORT_DIRECTORY_LINUX");
				EXPORT_REPORT_DIRECTORY = path.getReportPath("EXPORT_REPORT_DIRECTORY_LINUX");
			}
			

			SecurityEjb ejb=new SecurityEjb(region);
			
			String authCostCenters = "";
			List<String> authCost = ejb.getAuthorizedCostCenters(userId);
			
			String authCostList[] = authCost.toArray(new String[0]);
			

			ProvinceDetailsMasterEjb promasEjb = new ProvinceDetailsMasterEjb();
			String costCenterNoCommercial = null;
			try {
				costCenterNoCommercial = promasEjb.getCommercialId(costCenterNo,  getSessionKey("region"));
			} catch (Exception e) {
				return ERROR;
			}
		
			if(costCenterNoCommercial == null){
				String deptid= costCenterNo.substring(0, 3);
				costCenterNoCommercial = deptid.concat(".00");

			}
			
			HashMap<String, Object> param = new HashMap<String, Object>();
		 

			System.out.println("Application Number :  " + getApplicationNumber());
			System.out.println("CC Number :  " + costCenterNoCommercial);
			param.put("@appNo", "'"+getApplicationNumber()+"'");
			param.put("@costctr","'"+costCenterNo+"'");
			param.put("@coordinating", "'"+getElecSupName()+"'");
		
		
			if(getContractor() != null){
				SpestcntEjb ejbCnt=new SpestcntEjb(getSessionKey("region"));	
				SpestcntPK spestcntpk = new SpestcntPK();
				spestcntpk.setContractorId(getContractor());
				spestcntpk.setDeptId(costCenterNo);
				
				 
				Spestcnt spestcnt=ejbCnt.findById(spestcntpk);
				if(spestcnt != null){
					param.put("@conname","'"+spestcnt.getContractorName()+"'");
				}else{
					param.put("@conname","'"+getContractor()+"'");
				}
			}
			
			
			
			param.put("@esname","'"+getAppointedUserName()+"'");
			if(getConstrucRef() != null && getSelectedConstructionEstNumber().equalsIgnoreCase("-1")){
				param.put("@construRef","'"+getConstrucRef()+"'");
			}else if(!getSelectedConstructionEstNumber().equalsIgnoreCase("-1")){
				param.put("@construRef","'"+getSelectedConstructionEstNumber()+"'");
			}
			param.put("@workScope","'"+getDescription()+"'");

			String fileName = null;
			
			fileName = rept.generateReport("allocation_Of_constructionJob_Uva",param,getSession(),REPORT_DIRECTORY,EXPORT_REPORT_DIRECTORY);
			
			
		
			System.out.println("xxxxxxxxx"+ fileName);
			
			try {
				if (fileName == "")
				{
					execute();
					setLblError("Error occured while generating the report");
					return SUCCESS;
				}
				fileInputStream = new FileInputStream(fileName);
			} catch (FileNotFoundException e) {
				
				e.printStackTrace();
			}
			
			return "successprint";


		}

		public String getElecSupName() {
			return elecSupName;
		}

		public void setElecSupName(String elecSupName) {
			this.elecSupName = elecSupName;
		}

		public String getContractor() {
			return contractor;
		}

		public void setContractor(String contractor) {
			this.contractor = contractor;
		}

		public String getConstrucRef() {
			return construcRef;
		}

		public void setConstrucRef(String construcRef) {
			this.construcRef = construcRef;
		}

		public String getSelectedConstructionEstNumber() {
			return selectedConstructionEstNumber;
		}

		public void setSelectedConstructionEstNumber(
				String selectedConstructionEstNumber) {
			this.selectedConstructionEstNumber = selectedConstructionEstNumber;
		}

		public InputStream getFileInputStream() {
			return fileInputStream;
		}

		public void setFileInputStream(InputStream fileInputStream) {
			this.fileInputStream = fileInputStream;
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

		public static String getViewpath() {
			return viewPath;
		}

		public String getElectorate() {
			return electorate;
		}

		public void setElectorate(String electorate) {
			this.electorate = electorate;
		}

		public String getProposerName() {
			return proposerName;
		}

		public void setProposerName(String proposerName) {
			this.proposerName = proposerName;
		}

		public String getSchemaName() {
			return schemaName;
		}

		public void setSchemaName(String schemaName) {
			this.schemaName = schemaName;
		}

		public List<SpestedyCons> getAtiveAppoinmentsListConMain() {
			return ativeAppoinmentsListConMain;
		}

		public void setAtiveAppoinmentsListConMain(
				List<SpestedyCons> ativeAppoinmentsListConMain) {
			this.ativeAppoinmentsListConMain = ativeAppoinmentsListConMain;
		}

		public String getSchemaExtention() {
			return schemaExtention;
		}

		public void setSchemaExtention(String schemaExtention) {
			this.schemaExtention = schemaExtention;
		}

		public String getRepresentative() {
			return representative;
		}

		public void setRepresentative(String representative) {
			this.representative = representative;
		}

		public String getRepContact() {
			return repContact;
		}

		public void setRepContact(String repContact) {
			this.repContact = repContact;
		}

		public String getBranchType() {
			return branchType;
		}

		public void setBranchType(String branchType) {
			this.branchType = branchType;
		}

		public String getSmcType() {
			return smcType;
		}

		public void setSmcType(String smcType) {
			this.smcType = smcType;
		}
		
		
}

