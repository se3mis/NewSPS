package job.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import job.model.Spestcnd;
import job.service.SpestcndEjb;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.SessionAware;
import util.common.ApplicationType;
import util.common.JobProcessStatus;
import util.common.PhaseList;

import com.opensymphony.xwork2.ActionSupport;

public class JobEnergize extends ActionSupport implements SessionAware, ParameterAware
{

	private static final long serialVersionUID = 1L;
	private Map<String, Object> session;
	private Map <String, String[]> parameters;
	private static final Log log = LogFactory.getLog(JobEnergize.class);
	private static final String newPath="New Conn>Manage Jobs>Job Energize";
	private String path;
	private String loggedInUserId;
	private String costCenterNo;
	private Date energizeDate;
		
	
	private List<String>listPhase;
	private List<String>listcontractorId;
	
	

	private String csc;
	private String statusMsgErr;
	private List<String> listjobNo;	

	
	public String execute(){
		try{
			setLoggedData();
		 	setPath(newPath);
		 	listjobNo = new ArrayList<String>();
		 	listcontractorId = new ArrayList<String>();
		 	costCenterNo = getSessionKey("costCenterNo");	
		 	SpestcndEjb spejb=new SpestcndEjb(getSessionKey("region"));
		 	//List<Spestcnd> jobList=spejb.getJobListByType(costCenterNo,JobProcessStatus.ALLOCATED,ApplicationType.NEW_CONN);
		 	List<Spestcnd> jobList=spejb.getJobListByType(costCenterNo,JobProcessStatus.ALLOCATED,getSessionKey("smcType"));
		 	Iterator<Spestcnd> it1 = jobList.iterator();
		 	
		 	listPhase = new ArrayList<String>();
		 	listPhase.add(PhaseList.SINGLEPHASE);
		 	listPhase.add(PhaseList.TWOPHASE);
		 	listPhase.add(PhaseList.THREEPHASE);	
		 		 
			
			 while (it1.hasNext()) {  
				 Spestcnd spestcnd=it1.next();
				 listjobNo.add(spestcnd.getId().getProjectNo().trim());
				 listcontractorId.add(spestcnd.getId().getContractorId());				 			 				        	        	
		       }  		
			
		 	energizeDate = new Date();
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return "success";
	}
	
	public String  close(){
		setLoggedData();
		return "closed";
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
	
	public List<String> getListcontractorId() {
		return listcontractorId;
	}
	public void setListcontractorId(List<String> listcontractorId) {
		this.listcontractorId = listcontractorId;
	}
	public List<String> getListPhase() {
		return listPhase;
	}
	public void setListPhase(List<String> listPhase) {
		this.listPhase = listPhase;
	}

	public Date getEnergizeDate() {
		return energizeDate;
	}
	public void setEnergizeDate(Date energizeDate) {
		this.energizeDate = energizeDate;
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
	public String getStatusMsgErr() {
		return statusMsgErr;
	}
	public void setStatusMsgErr(String statusMsgErr) {
		this.statusMsgErr = statusMsgErr;
	}
	public static String getNewpath() {
		return newPath;
	}	
	
	public List<String> getListjobNo() {
		return listjobNo;
	}
	public void setListjobNo(List<String> listjobNo) {
		this.listjobNo = listjobNo;
	}

}
