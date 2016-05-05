package calendar.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.SessionAware;
import util.common.AppointmentStatus;
import com.opensymphony.xwork2.ActionSupport;

import contractor.web.ListObject;
import estimate.model.Spestedy;
import estimate.model.SpestedyPK;
import estimate.service.SpestedyEjb;

public class ChangeStatus extends ActionSupport implements SessionAware, ParameterAware{


	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(ChangeStatus.class);
	private static final String newPath="Calendar>Diary";
	private Map<String, Object> session;
	
	public String getAppontId() {
		return appontId;
	}
	public void setAppontId(String appontId) {
		this.appontId = appontId;
	}

	private Map <String, String[]> parameters;
	private String appontId;
	private String statusMsg;
	
	public String getStatusMsg() {
		return statusMsg;
	}
	public void setStatusMsg(String statusMsg) {
		this.statusMsg = statusMsg;
	}

	List <ListObject>  listappointmentStatus;	

	public List<ListObject> getListappointmentStatus() {
		return listappointmentStatus;
	}
	public void setListappointmentStatus(List<ListObject> listappointmentStatus) {
		this.listappointmentStatus = listappointmentStatus;
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

	private String loggedInUserId;
	private String costCenterNo;
	private String csc;
	private String description;
	private String appointmentStatus;
	private String passedCostCen;
	
	public String getPassedCostCen() {
		return passedCostCen;
	}
	public void setPassedCostCen(String passedCostCen) {
		this.passedCostCen = passedCostCen;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getAppointmentStatus() {
		return appointmentStatus;
	}
	public void setAppointmentStatus(String appointmentStatus) {
		this.appointmentStatus = appointmentStatus;
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
	public static String getNewpath() {
		return newPath;
	}
	
	 public String execute()
	 {
			try{
				listappointmentStatus = new ArrayList<ListObject>();
				
				ListObject li1 = new ListObject(AppointmentStatus.getAptStsVal(AppointmentStatus.ACTIVE),AppointmentStatus.ACTIVE);
				listappointmentStatus.add(li1);
				
				ListObject li2 = new ListObject(AppointmentStatus.getAptStsVal(AppointmentStatus.FAILED),AppointmentStatus.FAILED);
				listappointmentStatus.add(li2);
				
						
					
				
				HttpServletRequest request = ServletActionContext.getRequest();
				
				if(request.getParameter("desc")!=null)
					description=request.getParameter("desc").trim();
				if(request.getParameter("status")!=null)
					appointmentStatus= request.getParameter("status").trim();
				if(request.getParameter("apptId")!=null)
					appontId=request.getParameter("apptId").trim();
				if(request.getParameter("costCen")!=null)
					passedCostCen =request.getParameter("costCen").trim();
				
				return "success";
			}catch(Exception ex){
				return "error";
			}
	 }
	 
	 public String  changeStatus(){
		 
		 try{	
			 	setLoggedData();	
				SpestedyEjb ejb=new SpestedyEjb(getSessionKey("region"));			
				SpestedyPK id = new SpestedyPK();
				id.setAppointmentId(appontId);
				id.setDeptId(passedCostCen);	
				Spestedy tedy = ejb.findById(id);
				if(tedy!=null){	
					tedy.setDescription(description);
					tedy.setStatus(appointmentStatus);
					ejb.updateSpestedy(tedy);	
					statusMsg = "Status updated successfully";
					reSetForm();
					execute();
				}
			}catch(Exception ex){
				return "error";
		}
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
		

		private void reSetForm(){
			description="";				
			appointmentStatus= "";	
			appontId="";	
			passedCostCen ="";	
		
		}
}


