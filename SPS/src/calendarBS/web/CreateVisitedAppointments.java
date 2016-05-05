package calendarBS.web;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.SessionAware;

import security.service.SecurityEjb;
import util.common.AppointmentStatus;
import util.common.Format;
import application.model.Application;
import application.service.ApplicationEjb;
import com.opensymphony.xwork2.ActionSupport;

import estimate.model.Spestedy;
import estimate.model.SpestedyPK;
import estimate.service.EstimateEjb;
import estimate.service.SpestedyEjb;


public class CreateVisitedAppointments extends ActionSupport implements SessionAware, ParameterAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String path;
	private static final Log log = LogFactory.getLog(CreateVisitedAppointments.class);
	private String loggedInUserId;
	private String costCenterNo;
	private Map<String, Object> session;
	private Map <String, String[]> parameters;
	private String csc;
	private List<Application> referenceNumberList;
	private ApplicationNumberList appNumList;
	private List<ApplicationRecord> list;
	private String statusMsg;
	private String[] applicationNumberList;
	private List<String> listuserName;	
	private String appointedUserName;
	
	public List<String> getListuserName() {
		return listuserName;
	}
	public void setListuserName(List<String> listuserName) {
		this.listuserName = listuserName;
	}
	public String getAppointedUserName() {
		return appointedUserName;
	}
	public void setAppointedUserName(String appointedUserName) {
		this.appointedUserName = appointedUserName;
	}
	public String[] getApplicationNumberList() {
		return applicationNumberList;
	}
	public void setApplicationNumberList(String[] applicationNumberList) {
		this.applicationNumberList = applicationNumberList;
	}

	private String applicationNo;
	private Date applicationDate;
	
	public String getApplicationNo() {
		return applicationNo;
	}
	public void setApplicationNo(String applicationNo) {
		this.applicationNo = applicationNo;
	}
	public Date getApplicationDate() {
		return applicationDate;
	}
	public void setApplicationDate(Date applicationDate) {
		this.applicationDate = applicationDate;
	}
	public String getStatusMsg() {
		return statusMsg;
	}
	public void setStatusMsg(String statusMsg) {
		this.statusMsg = statusMsg;
	}

	private static final String newPath="Calendar>Create Appointments";

	public List<Application> getReferenceNumberList() {
		return referenceNumberList;
	}
	public void setReferenceNumberList(List<Application> referenceNumberList) {
		this.referenceNumberList = referenceNumberList;
	}
	public String getCsc() {
		return csc;
	}
	public void setCsc(String csc) {
		this.csc = csc;
	}
	public ApplicationNumberList getAppNumList() {
		return appNumList;
	}
	public void setAppNumList(ApplicationNumberList appNumList) {
		this.appNumList = appNumList;
	}
	public List<ApplicationRecord> getList() {
		return list;
	}
	public void setList(List<ApplicationRecord> list) {
		this.list = list;
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
	
	public String execute(){		
		try{
			setLoggedData();
		 	setPath(newPath);
			String costCentre = getSessionKey("costCenterNo");
			ApplicationEjb appEjb=new ApplicationEjb(getSessionKey("region"));
			referenceNumberList= appEjb.getApplicationList(costCentre, getSessionKey("smcType"),"C");			
	    	Iterator<Application> it = referenceNumberList.iterator();
	    	appNumList= new ApplicationNumberList();
	    	list = new ArrayList<ApplicationRecord>(); 
	    	listuserName  = new ArrayList<String>(); 		
					
			SecurityEjb secejb=new SecurityEjb(getSessionKey("region"));
			listuserName = secejb.getUserList(costCentre,"ES");	    	
	    	int estCount = 0 ;
	        while (it.hasNext()) {    
	        	Application app=it.next();
	        	ApplicationRecord record=new ApplicationRecord(app.getApplicationNo(), app.getSubmitDate());
	        	appNumList.addList(list,record);  
	        	estCount++;     	
	          } 
	        
	        if(estCount==0)
	        	statusMsg = "No Applications";
			
		}catch(Exception ex){
			
		}
		return "success";
	}
	
	public String close()throws Exception{
		setLoggedData();
		return "closed";
	}
	
	
	public String save()throws Exception{
		
		try{
			
			SpestedyEjb spestTedyejb=new SpestedyEjb(getSessionKey("region"));
			StringBuilder sb=new StringBuilder();
			List<String> estimatenumList = new ArrayList<String>();
			List<Spestedy> appointmentList = new ArrayList<Spestedy>();			
			Format format=new Format();
			for(int i=0;i<applicationNumberList.length;i++){									
				if(i==0)
					sb.append(applicationNumberList[i].trim());
				else
					sb.append(","+applicationNumberList[i].trim());
				
				estimatenumList.add(applicationNumberList[i].trim());	
				Spestedy spestedy = new Spestedy();
				SpestedyPK id= new SpestedyPK();
				id.setDeptId(getSessionKey("costCenterNo").trim());
				spestedy.setId(id);	
				Calendar calandar= Calendar.getInstance();
				spestedy.setAppointmentDate(new Date());
				spestedy.setDescription("Batch Created");
				spestedy.setTimeSession("Morning");
				spestedy.setAllocatedTo(appointedUserName.trim());	
				spestedy.setAllocatedBy(getSessionKey("userName"));
				spestedy.setAllocatedDate(calandar.getTime());
				spestedy.setAllocatedTime(format.FormatTime());
				spestedy.setAppoinmentType(AppointmentStatus.listappointmentType);
				spestedy.setStatus(AppointmentStatus.getAptStsVal(AppointmentStatus.ACTIVE));
				spestedy.setReferenceNo(applicationNumberList[i].trim());
				appointmentList.add(spestedy);
				}
			spestTedyejb.makeAnAppointment(appointmentList);
			statusMsg = "The Applications :"+ sb.toString()+" created successfully";
		
		}
		catch(Exception ex){
			System.out.println("The error is.........:"+ex);
			return "success";	
		}
		
			execute();
		return "success";	
	}
	
 	public void setLoggedData() {       
        log.info(getSession());
        setLoggedInUserId(getSessionKey("userName"));
        setCostCenterNo(getSessionKey("costCenterNo"));
        setCsc(getSessionKey("costCenterName"));       
    }
}
