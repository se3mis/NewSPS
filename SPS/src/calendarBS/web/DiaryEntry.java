package calendarBS.web;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.SessionAware;

import util.common.AppStatus;
import security.service.SecurityEjb;
import util.common.AppointmentStatus;
import util.common.Constants;
import util.common.Format;
import application.model.Application;
import application.model.ApplicationPK;
import application.service.ApplicationEjb;
import com.opensymphony.xwork2.ActionSupport;
import estimate.model.Spestedy;
import estimate.model.SpestedyCons;
import estimate.model.SpestedyPK;
import estimate.service.SpestedyConEjb;
import estimate.service.SpestedyEjb;

public class DiaryEntry extends ActionSupport implements SessionAware, ParameterAware{

	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(DiaryEntry.class);
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
	private List<Spestedy> ativeAppoinmentsList;
	private String status;


	//private String webAppName;
	
	public String directFromMain(){
		costCenterNo = getSessionKey("costCenterNo");
		String smcType = getSessionKey("smcType");
		setSelCostCenter(costCenterNo);
		SecurityEjb secejb=new SecurityEjb(getSessionKey("region"));
		SpestedyEjb spestedyEjb=new SpestedyEjb(getSessionKey("region"));
		List<String> usersList = new ArrayList<String>();
		usersList.add("ES");
		usersList.add("EE");
		if(smcType.equalsIgnoreCase(Constants.PLANNING) || smcType.equalsIgnoreCase(Constants.RUTAL_ELECTRIFICATION)){
			usersList.add("PE");
		}
		
		List<String> userList = secejb.getUserList(costCenterNo,usersList);
		//DW
		
		
		Iterator<String> usit = userList.iterator();
		
		listuserName = new ArrayList<String>();
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
		//DW
		List<Application> applicationList= appEjb.getApplicationList(costCenterNo, "C");
		//DW
		
		listapplicationNumber = new ArrayList<String>(); 
		
        
        HttpServletRequest request = ServletActionContext.getRequest();
		//String sessionKey= (String) request.getSession().getAttribute("region");
		String estimateNo=request.getParameter("estNo");
		//String costCen=request.getParameter("costCen");
		
		
		if(estimateNo != null){
			listapplicationNumber.add(estimateNo);
			
			//request.getSession().setAttribute("estimateNos",wEstimationRefNos);
		}
		ativeAppoinmentsList =  spestedyEjb.getAtiveAppoinmentsforDept(costCenterNo);
        return "success";
	}

	

	public String execute(){		
		try{
			setLoggedData();
			setPath(newPath);
			listsessionName = new ArrayList<String>(); 
			listsessionName.add("Morning");
			listsessionName.add("Afternoon");
			String smcType = getSessionKey("smcType");
			
			listuserName  = new ArrayList<String>(); 			
			costCenterNo = getSessionKey("costCenterNo");	
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
			SpestedyEjb spestedyEjb=new SpestedyEjb(getSessionKey("region"));
			//DW
			List<String> usersList = new ArrayList<String>();
			usersList.add("ES");
			usersList.add("EE");
			if(smcType.equalsIgnoreCase(Constants.PLANNING) || smcType.equalsIgnoreCase(Constants.RUTAL_ELECTRIFICATION)){
				usersList.add("PE");
			}
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
			ApplicationEjb appEjb=new ApplicationEjb(getSessionKey("region"));
			//DW
			List<Application> applicationLis= appEjb.getApplicationList(costCenterNo, "C");
			//DW
			
			listapplicationNumber = new ArrayList<String>(); 
			Iterator<Application> it = applicationLis.iterator();		    	   	
	        while (it.hasNext()) {        	 
	        	Application applc=it.next();
	        	listapplicationNumber.add(applc.getApplicationNo()); 		       	        	        	
	          } 	        
			
	        appointedUserName = getSessionKey("userName");
	        appointmentType = AppointmentStatus.listappointmentType;
	        sessionName = "Morning";
	        
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
			
			ativeAppoinmentsList =  spestedyEjb.getAtiveAppoinmentsforDept(costCenterNo);
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
	 		
	 		try{
	 			Format format=new Format();
	 			setLoggedData() ; 	
				SpestedyEjb ejb=new SpestedyEjb(getSessionKey("region"));
				Spestedy spestedy = new Spestedy();
				SpestedyPK id= new SpestedyPK();
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
				//
				spestedy.setId(id);	
				Calendar calandar= Calendar.getInstance();
				spestedy.setAppointmentDate(format.StrToDate(getAprDate().substring(0, 10)));
				spestedy.setDescription(description);
				spestedy.setTimeSession(sessionName);
				spestedy.setAllocatedTo(appointedUserName);	
				spestedy.setAllocatedBy(getSessionKey("userName"));
				spestedy.setAllocatedDate(calandar.getTime());
				spestedy.setAllocatedTime(format.FormatTime());
				spestedy.setAppoinmentType(appointmentType);
				spestedy.setStatus(AppointmentStatus.getAptStsVal(AppointmentStatus.ACTIVE));
				spestedy.setReferenceNo(applicationNumber);
				ejb.makeAnAppointment(spestedy);
				ClearForm();
				execute();
				statusMsg = "Appointment added successfully";
				return "success";
				
	 		}catch(Exception ex){
	 			System.out.println("The eror is...................:"+ex.getMessage());
	 			return "error";
	 			
	 		}
	 		
	 	}
	 	
public String removeApplication() throws ParseException {
	 		
	try{
			Format format=new Format();
			setLoggedData() ; 	
		SpestedyEjb ejb=new SpestedyEjb(getSessionKey("region"));
		Spestedy spestedy = new Spestedy();
		SpestedyPK id= new SpestedyPK();
		int status=0;
		
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
		//
		spestedy.setId(id);	
	
		spestedy.setDescription(description);
		spestedy.setTimeSession(sessionName);
		spestedy.setAllocatedTo(appointedUserName);	
		
		spestedy.setAppoinmentType(appointmentType);
		spestedy.setStatus(AppointmentStatus.getAptStsVal(AppointmentStatus.CANCEL));
		spestedy.setReferenceNo(applicationNumber);
		
		System.out.println("WWWWWWWWW");
		ApplicationEjb appejb = new ApplicationEjb(getSessionKey("region"));
		Application app=new Application();
		ApplicationPK apppk=new ApplicationPK();
		
		apppk.setApplicationId(applicationNumber);
		apppk.setDeptId(costCenterNo);
		app.setId(apppk);
		System.out.println(applicationNumber);
		System.out.println(costCenterNo);
		System.out.println("XCXCXCXCXCXC");
		status= appejb.changeStatusApplication(applicationNumber, costCenterNo,"D",getSessionKey("region"));
		System.out.println("TTTTTTTT");
		if(status==1){
			System.out.println("MMMMMMMMMMMMM");
			statusMsg = "Application Discard Successfully" ;
			execute();
			
		}
		return "success";
		
		}catch(Exception ex){
			System.out.println("The eror is...................:"+ex.getMessage());
			System.out.println("The stack trace...................:");
			ex.printStackTrace();
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

		public List<Spestedy> getAtiveAppoinmentsList() {
			return ativeAppoinmentsList;
		}

		public void setAtiveAppoinmentsList(List<Spestedy> ativeAppoinmentsList) {
			this.ativeAppoinmentsList = ativeAppoinmentsList;
		}
		
		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}
	
}


