package estimate.web;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Iterator;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.SessionAware;
import util.common.EstimateStatus;
import com.opensymphony.xwork2.ActionSupport;
import estimate.model.Pcesthtt;
import estimate.service.EstimateEjb;
import estimate.service.PcesthttEjb;
public class PostJob extends ActionSupport implements SessionAware, ParameterAware{

	
	private static final long serialVersionUID = 1L;
	private Map<String, Object> session;
	private Map <String, String[]> parameters;
	
	private static final Log log = LogFactory.getLog(PostJob.class);
	private static final String newPath="Estimation>Estimate>Post";
	
	private String estimateNo;
	private Date estimateDate;
	private String jobDescription;
	private PostJobList postJobList;
	private String path;
	private String loggedInUserId;
	private String costCenterNo;
	private String csc;
	private String statusMsg;
	
	public String getStatusMsg() {
		return statusMsg;
	}

	public void setStatusMsg(String statusMsg) {
		this.statusMsg = statusMsg;
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

	public static String getNewpath() {
		return newPath;
	}

	private String[] estimationNumberList;
	
	
	public String[] getEstimationNumberList() {
		return estimationNumberList;
	}

	public void setEstimationNumberList(String[] estimationNumberList) {
		this.estimationNumberList = estimationNumberList;
	}

	public String getEstimateNo() {
		return estimateNo;
	}

	public void setEstimateNo(String estimateNo) {
		this.estimateNo = estimateNo;
	}

	public Date getEstimateDate() {
		return estimateDate;
	}

	public void setEstimateDate(Date estimateDate) {
		this.estimateDate = estimateDate;
	}

	public String getJobDescription() {
		return jobDescription;
	}

	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}

	public String close()throws Exception{
		setLoggedData();
		return "closed";
	}
	
	public String save()throws Exception{
		
		try{
			EstimateEjb estEjb=new EstimateEjb(getSessionKey("region"));
			StringBuilder sb=new StringBuilder();
			List<String> estimatenumList = new ArrayList<String>();
			String costCentre = getSessionKey("costCenterNo");
			for(int i=0;i<estimationNumberList.length;i++){	
				
			
				
				if(i==0)
					sb.append(estimationNumberList[i].trim());
				else
					sb.append(","+estimationNumberList[i].trim());
				
				estimatenumList.add(estimationNumberList[i].trim());
				}
			estEjb.estimatePost(estimatenumList, costCentre);
			statusMsg = "The Estimates :"+ sb.toString()+" posted successfully";
		}
		catch(Exception ex){
			return "error";
		}
			setLoggedData();
			execute();
			return "success";	
	}
	
	public PostJobList getPostJobList() {
		return postJobList;
	}

	public void setPostJobList(PostJobList postJobList) {
		this.postJobList = postJobList;
	}

	public List<String> getListRadio() {
		return listRadio;
	}
	public void setListRadio(List<String> listRadio) {
		this.listRadio = listRadio;
	}

	private List<JobRecord> list;
	private List<String> listRadio;
	
	public Map<String, Object> getSession() {
		return session;	
	}
	public String execute(){		
	
		try{			
			setLoggedData();
		 	setPath(newPath);
			PcesthttEjb ejb=new PcesthttEjb(getSessionKey("region"));
			String costCentre = getSessionKey("costCenterNo");		
			//List<Pcesthtt> resultList= ejb.getEstimateList(costCentre,new BigDecimal(EstimateStatus.EST_POSTING));
			List<Pcesthtt> resultList= ejb.getEstimateList(costCentre, new Long(2), new Long(EstimateStatus.EST_POSTING));
			Iterator<Pcesthtt> it = resultList.iterator();
	    	postJobList= new PostJobList();
	    	list = new ArrayList<JobRecord>(); 
	    	int estCount = 0 ;
	        while (it.hasNext()) {    
	        	Pcesthtt pces=it.next();
	        	JobRecord record=new JobRecord(pces.getId().getEstimateNo(), pces.getEtimateDt(), pces.getDescr());
	        	postJobList.addList(list,record);  
	        	estCount++;     	
	          } 
	        
	        if(estCount==0)
	        	statusMsg = "No Estimates To Post";
	        
	        
        }  catch(Exception ex)	{
        		return "error";
        	}
		return "success";
	}
	
	public List<JobRecord> getList() {
		return list;
	}
	public void setList(List<JobRecord> list) {
		this.list = list;
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
		
}
