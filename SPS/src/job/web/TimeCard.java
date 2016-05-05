package job.web;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.SessionAware;
import util.common.TimeCardData;
import com.opensymphony.xwork2.ActionSupport;




public class TimeCard extends ActionSupport implements SessionAware, ParameterAware{

	private static final long serialVersionUID = 1L;
	private Map<String, Object> session;
	private Map <String, String[]> parameters;
	private static final Log log = LogFactory.getLog(TimeCard.class);
	private static final String newPath="Manage Jobs>Time Card";
	private String path;
	private String loggedInUserId;
	private String costCenterNo;
	private List<String> listdateType;
	private String csc;
	private List<String>listworkType;
	private TimeCardList timeCardList;
	private String timecardTableMsg;
		
	
	public String getTimecardTableMsg() {
		return timecardTableMsg;
	}

	public void setTimecardTableMsg(String timecardTableMsg) {
		this.timecardTableMsg = timecardTableMsg;
	}

	public TimeCardList getTimeCardList() {
		return timeCardList;
	}

	public void setTimeCardList(TimeCardList timeCardList) {
		this.timeCardList = timeCardList;
	}

	public List<TimeCardDetails> getList() {
		return list;
	}

	public void setList(List<TimeCardDetails> list) {
		this.list = list;
	}

	private List<TimeCardDetails> list;
	
	public List<String> getListdateType() {
		return listdateType;
	}

	public void setListdateType(List<String> listdateType) {
		this.listdateType = listdateType;
	}

	public List<String> getListworkType() {
		return listworkType;
	}

	public void setListworkType(List<String> listworkType) {
		this.listworkType = listworkType;
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

	public void setLoggedData() {	       
        log.info(getSession());
        setLoggedInUserId(getSessionKey("userName"));
        setCostCenterNo(getSessionKey("costCenterNo"));
        setCsc(getSessionKey("costCenterName"));
       
    }

	public String getSessionKey(String key) {
        return getSession().get(key).toString();
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
	
	public String execute(){
		try{
			setLoggedData() ;
			setPath(newPath);
			costCenterNo = getSessionKey("costCenterNo");
			list = new ArrayList<TimeCardDetails>(); 
			timeCardList= new TimeCardList();
			listworkType = new ArrayList<String>();
			listworkType.add(TimeCardData.WORKTYPE_NORMAL);
			listworkType.add(TimeCardData.WORKTYPE_SHIFT);
			
			listdateType = new ArrayList<String>();
			listdateType.add(TimeCardData.DATETYPE_WORKING);
			listdateType.add(TimeCardData.DATETYPE_SATURDAY);
			listdateType.add(TimeCardData.DATETYPE_SUNDAY);
			listdateType.add(TimeCardData.DATETYPE_SPECIAL);
			listdateType.add(TimeCardData.DATETYPE_OTHER);
			
			int recordCount=0;
			 while (recordCount<3) {    
		        	
				 	TimeCardDetails record=new TimeCardDetails("Job1", "Repair", "Meter Change","Sunday OT","10.00 ","15.00");
		        	timeCardList.addList(list,record);  
		        	recordCount++;     	
		          } 
			if(recordCount==0)
				timecardTableMsg = "No previous data available";
			else
				timecardTableMsg = "";
			
		}catch(Exception ex){
			
		}
		return "success";
	}
	
	public String close(){		
		setLoggedData() ;
		return "closed";
	}
}
