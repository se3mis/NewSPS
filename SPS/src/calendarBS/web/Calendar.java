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
import util.common.AppointmentStatus;
import util.common.Format;
import util.common.UserRole;
import com.opensymphony.xwork2.ActionSupport;
import estimate.model.Spestedy;
import estimate.model.SpestedyPK;
import estimate.service.SpestedyEjb;




public class Calendar extends ActionSupport implements SessionAware, ParameterAware{

	private static final long serialVersionUID = 1L;
	private Map<String, Object> session;
	private Map <String, String[]> parameters;
	private String path;
	private List<Schedule> list;
	private ScheduleList scheduleList;
	private String scheduleNo;
	private String description;
	private String time;
	private String aprDate;
	private String selectedDate;
	private String appointUserName;
	private String applicationNo;
	private String appointmentType;
	private String selectedcostCentre;
	private String appointmentStatus;
	private String hid_UserRole;
	
	public String getHid_UserRole() {
		return hid_UserRole;
	}
	public void setHid_UserRole(String hid_UserRole) {
		this.hid_UserRole = hid_UserRole;
	}
	public boolean isDisable1() {
		return disable1;
	}
	public void setDisable1(boolean disable1) {
		this.disable1 = disable1;
	}

	private String hid_FrmFind;
	boolean disable1 = false;

	public String getHid_FrmFind() {
		return hid_FrmFind;
	}
	public void setHid_FrmFind(String hid_FrmFind) {
		this.hid_FrmFind = hid_FrmFind;
	}
	public String getAppointmentStatus() {
		return appointmentStatus;
	}
	public void setAppointmentStatus(String appointmentStatus) {
		this.appointmentStatus = appointmentStatus;
	}
	public String getAppointmentType() {
		return appointmentType;
	}
	public void setAppointmentType(String appointmentType) {
		this.appointmentType = appointmentType;
	}
	public String getSelectedcostCentre() {
		return selectedcostCentre;
	}
	public void setSelectedcostCentre(String selectedcostCentre) {
		this.selectedcostCentre = selectedcostCentre;
	}
	public String getApplicationNo() {
		return applicationNo;
	}
	public void setApplicationNo(String applicationNo) {
		this.applicationNo = applicationNo;
	}
	public String getAppointUserName() {
		return appointUserName;
	}
	public void setAppointUserName(String appointUserName) {
		this.appointUserName = appointUserName;
	}

	private String[] appointmentNumberList;

	public String[] getAppointmentNumberList() {
		return appointmentNumberList;
	}
	public void setAppointmentNumberList(String[] appointmentNumberList) {
		this.appointmentNumberList = appointmentNumberList;
	}
	public String getSelectedDate() {
		return selectedDate;
	}
	public void setSelectedDate(String selectedDate) {
		this.selectedDate = selectedDate;
	}
	public String getAprDate() {
		return aprDate;
	}
	public void setAprDate(String aprDate) {
		this.aprDate = aprDate;
	}
	public ScheduleList getScheduleList() {
		return scheduleList;
	}
	public void setScheduleList(ScheduleList scheduleList) {
		this.scheduleList = scheduleList;
	}
	
	public List<Schedule> getList() {
		return list;
	}
	public void setList(List<Schedule> list) {
		this.list = list;
	}
	public String getScheduleNo() {
		return scheduleNo;
	}
	public void setScheduleNo(String scheduleNo) {
		this.scheduleNo = scheduleNo;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}

	private static final Log log = LogFactory.getLog(Calendar.class);
	private static final String newPath="Calendar>Edit/Change Status";

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
	public Map<String, Object> getSession() {
		return session;	
	}
	public String execute(){		
		try{			
			setLoggedData();
			setPath(newPath);
			
			String allocatedUserId;
			String loggedUserLevel = getSessionKey("userRole");
			
			if(loggedUserLevel.equals(UserRole.ENGINEERINGSUPIRINTED)){
				disable1 = true;
				hid_UserRole = UserRole.ENGINEERINGSUPIRINTED;
					
			}else{
				disable1 = false;
				hid_UserRole = "NONES";
			}
					
			
			scheduleList= new ScheduleList();
	    	list = new ArrayList<Schedule>(); 	    	
			String costCentre = getSessionKey("costCenterNo");	
			Date dt;
			hid_FrmFind = "";
			HttpServletRequest request = ServletActionContext.getRequest();
			String selectedDt=request.getParameter("selDate");
			String selectedEstNo=request.getParameter("estNo");
			
			String selectedUser=request.getParameter("allocateUser");
			Format format=new Format();
							
			String seldateFromMenu = request.getParameter("selDateFromMenu");			

			if(selectedDt!=null && selectedDt.length()>0 &&  selectedUser!=null && selectedUser.length()>0 ){
				dt = format.StrToDate(selectedDt);
				allocatedUserId = selectedUser;
				selectedDate  =format.FormatStringDate(dt);
							
			}else if(seldateFromMenu!=null && seldateFromMenu.length()>0 &&  selectedUser!=null && selectedUser.length()>0 && selectedEstNo!=null && selectedEstNo.length()>0){
				dt = format.StrToDate(seldateFromMenu);
				allocatedUserId = selectedUser;
				selectedDate  =format.FormatStringDate(dt);
								
			}else if(aprDate==null || aprDate.length()==0){
				dt = new Date();
				selectedDate = format.FormatDate("dd/MM/yyyy");				
				allocatedUserId = getSessionKey("userName");
			}
			else{
				String dateArray[]=aprDate.split("/");
				selectedDate = aprDate;
				allocatedUserId = getSessionKey("userName");
				if(dateArray.length>2){
					String dtStr =dateArray[1]+"/"+dateArray[0]+"/"+dateArray[2];					
					dt = format.StringToDateSlash(dtStr);					
				}else
					dt = new Date();
			}				
			List<Spestedy> apptList ;
			SpestedyEjb ejb=new SpestedyEjb(getSessionKey("region"));
			if(selectedEstNo!=null && selectedEstNo.length()>0)
				apptList = ejb.getAll(costCentre, dt,allocatedUserId,AppointmentStatus.getAptStsVal(AppointmentStatus.ACTIVE),selectedEstNo);
			else
				apptList = ejb.getAll(costCentre, dt);
			
			Iterator<Spestedy> it = apptList.iterator();
		
			while (it.hasNext()) {    
				Spestedy tedy=it.next();
				Schedule record=new Schedule(tedy.getId().getAppointmentId(),tedy.getDescription(),tedy.getTimeSession(),tedy.getAllocatedBy(),tedy.getAllocatedTo(),tedy.getReferenceNo(),null,tedy.getAppoinmentType(),tedy.getId().getDeptId(),tedy.getStatus());
		    	scheduleList.addList(list,record); 
	          } 
			
	    	
        }  catch(Exception ex)	{
        		System.out.println("The error is:"+ex);
        		return "error";
        	}
		return "success";
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
	
 	public void setLoggedData() {       
        log.info(getSession());
        setLoggedInUserId(getSessionKey("userName"));
        setCostCenterNo(getSessionKey("costCenterNo"));
        setCsc(getSessionKey("costCenterName"));       
    }
 	
 	public String close(){
 		setLoggedData();
		return "closed";
 	}
 	
 	public String edit(){
 		setLoggedData();
 		return "closed";
 	}
 	
	public String remove(){
		try{
			setLoggedData();
			SpestedyEjb ejb=new SpestedyEjb(getSessionKey("region"));
			for(int i=0;i<appointmentNumberList.length;i++){
				SpestedyPK id = new SpestedyPK();
				String costCentre = getSessionKey("costCenterNo");
				id.setAppointmentId(appointmentNumberList[0]);
				id.setDeptId(costCentre);				
				Spestedy tedy = ejb.findById(id);
				
				if(tedy!=null)
					ejb.removeSpestedy(tedy);
			}
			execute();
	 		return "success";
		}catch(Exception ex){
			return "error";
		}
 		
 	}
 	
}
