package calendarBS.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import estimate.web.JobRecord;
import estimate.web.PostJobList;

public class TestTable extends ActionSupport implements SessionAware, ParameterAware{

	private static final long serialVersionUID = 1L;
	private Map<String, Object> session;
	private Map <String, String[]> parameters;
	private String path;
	private List<JobRecord> list;
	private PostJobList postJobList;	
	private String estimateNo;
	private Date estimateDate;
	private String jobDescription;
	private String refList;
	
	public String getRefList() {
		return refList;
	}

	public void setRefList(String refList) {
		this.refList = refList;
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

	public PostJobList getPostJobList() {
		return postJobList;
	}

	public void setPostJobList(PostJobList postJobList) {
		this.postJobList = postJobList;
	}

	public List<JobRecord> getList() {
		return list;
	}

	public void setList(List<JobRecord> list) {
		this.list = list;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Map<String, Object> getSession() {
		return session;
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
	
	public String execute(){
		JobRecord record1=new JobRecord("1", new Date(), "a");
		JobRecord record2=new JobRecord("2", new Date(), "b");
		JobRecord record3=new JobRecord("3", new Date(), "c");
		postJobList= new PostJobList();
		list = new ArrayList<JobRecord>();   
    	postJobList.addList(list,record1);  
    	postJobList.addList(list,record2);
    	postJobList.addList(list,record3);
    	
    	System.out.println("The value is...................:"+refList);
		
		return "success";
	}
	
	public String close(){
 		return "closed";
 	}
}
