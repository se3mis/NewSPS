package calendarBS.web;


import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.SessionAware;

import security.service.SecurityEjb;
import util.common.AppointmentStatus;
import util.common.Format;
import com.opensymphony.xwork2.ActionSupport;
import estimate.model.Spestedy;
import estimate.model.SpestedyPK;
import estimate.service.SpestedyEjb;

public class EditAppt extends ActionSupport implements SessionAware, ParameterAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(EditAppt.class);
	private static final String newPath="Calendar>Diary";
	private Map<String, Object> session;
	private Map <String, String[]> parameters;
	private List<String> listsessionName;
	private List<String> listuserName;	
	
	private String appointedUserName;
	private String sessionName;
	private String description;
	private String aprDate;
	private String statusMsg;
	private String appointmentType;
	private Date appointmentDate;
	private String passedCostCen;
	public String getPassedCostCen() {
		return passedCostCen;
	}
	public void setPassedCostCen(String passedCostCen) {
		this.passedCostCen = passedCostCen;
	}
	public Date getAppointmentDate() {
		return appointmentDate;
	}
	public void setAppointmentDate(Date appointmentDate) {
		this.appointmentDate = appointmentDate;
	}
	public String getAppointmentType() {
		return appointmentType;
	}
	public void setAppointmentType(String appointmentType) {
		this.appointmentType = appointmentType;
	}

	private List<String> listappointmentType;
	
	public List<String> getListappointmentType() {
		return listappointmentType;
	}
	public void setListappointmentType(List<String> listappointmentType) {
		this.listappointmentType = listappointmentType;
	}
	public String getStatusMsg() {
		return statusMsg;
	}
	public void setStatusMsg(String statusMsg) {
		this.statusMsg = statusMsg;
	}
	public String getAprDate() {
		return aprDate;
	}
	public void setAprDate(String aprDate) {
		this.aprDate = aprDate;
	}

	String appontId;
	
	
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

	
	public String getAppointedUserName() {
		return appointedUserName;
	}
	public void setAppointedUserName(String appointedUserName) {
		this.appointedUserName = appointedUserName;
	}
	public List<String> getListsessionName() {
		return listsessionName;
	}
	public void setListsessionName(List<String> listsessionName) {
		this.listsessionName = listsessionName;
	}
	public List<String> getListuserName() {
		return listuserName;
	}
	public void setListuserName(List<String> listuserName) {
		this.listuserName = listuserName;
	}

	private String loggedInUserId;
	private String costCenterNo;
	private String csc;
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
	public static String getNewpath() {
		return newPath;
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
	
 public String execute()
 {
		try{
			
			listsessionName = new ArrayList<String>(); 
			listsessionName.add("Morning");
			listsessionName.add("Afternoon");
			
			listuserName  = new ArrayList<String>(); 
			String costCentre = getSessionKey("costCenterNo");			
			
			SecurityEjb secejb=new SecurityEjb(getSessionKey("region"));
			List<String> userList = secejb.getUserList(costCentre,"ES");
						
			Iterator<String> usit = userList.iterator();
			
			 while (usit.hasNext()) {        	 
		        	String esUser=usit.next();
		        	listuserName.add(esUser);		       	        	        	
		          } 
					
			listappointmentType = new ArrayList<String>(); 
			listappointmentType.add(AppointmentStatus.listappointmentType);
		
			HttpServletRequest request = ServletActionContext.getRequest();
			if(request.getParameter("apptId")!=null)
				appontId=request.getParameter("apptId").trim();
			if(request.getParameter("userNam")!=null)
				appointedUserName=request.getParameter("userNam").trim();
			if(request.getParameter("descr")!=null)
				description=request.getParameter("descr").trim();
			if(request.getParameter("timeval")!=null)
				sessionName=request.getParameter("timeval").trim();
			if(request.getParameter("apptType")!=null)
				appointmentType =request.getParameter("apptType").trim();
			if(request.getParameter("costCen")!=null)
				passedCostCen =request.getParameter("costCen").trim();
			String selectedDate= request.getParameter("seldate");
			
			if(selectedDate!=null && selectedDate.length()>0){
				String formatteStr[] = selectedDate.split("/");
				if(formatteStr.length>2){
					Format format=new Format();
					String dtStr = formatteStr[2].trim()+"-"+formatteStr[1].trim()+"-"+formatteStr[0].trim();
					appointmentDate = format.StrToDate(dtStr);					
				}
			}					
			
		}catch(Exception ex){
			return "error"; 
		}
		return "success"; 
	}
 
 public String  editAppointment(){
	 
	 try{			
			SpestedyEjb ejb=new SpestedyEjb(getSessionKey("region"));			
			SpestedyPK id = new SpestedyPK();
			id.setAppointmentId(appontId.trim());
			id.setDeptId(passedCostCen.trim());	
			Spestedy tedy = ejb.findById(id);
			if(tedy!=null){						
				tedy.setAppointmentDate(appointmentDate);
				tedy.setDescription(description.trim());
				tedy.setTimeSession(sessionName);
				tedy.setAllocatedTo(appointedUserName);	
				tedy.setAllocatedBy(getSessionKey("userName"));
				ejb.updateSpestedy(tedy);	
				statusMsg = "Appointment updated successfully";
				execute();
			}
		}catch(Exception ex){
			System.out.println("The error is.........:"+ex);
			return "error";
	}
	 return "success";
 }
public String getAppontId() {
	return appontId;
}
public void setAppontId(String appontId) {
	this.appontId = appontId;
}

}
