package job.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Map;

@SuppressWarnings("serial")
public class JobInfo implements Serializable {
	private String projectNo;
	private String fullName;
	private BigDecimal Amount;
	
	public JobInfo() {
		super();
	}
	public JobInfo(String projectNo, String fullName, BigDecimal amount) {
		super();
		this.projectNo = projectNo;
		this.fullName = fullName;
		this.Amount = amount;
	}
	
	@SuppressWarnings("rawtypes")
	public JobInfo(Map map) {
		super();
		this.projectNo = map.values().toArray()[3].toString();
		this.Amount = new BigDecimal(map.values().toArray()[2].toString());
		this.fullName = map.values().toArray()[1].toString()+" " + map.values().toArray()[0].toString();
		
		
	}
	public String getProjectNo() {
		return projectNo;
	}
	public void setProjectNo(String projectNo) {
		this.projectNo = projectNo;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public BigDecimal getAmount() {
		return Amount;
	}
	public void setAmount(BigDecimal amount) {
		Amount = amount;
	}
	@Override
	public String toString() {
		return "JobInfo [projectNo=" + projectNo + ", fullName=" + fullName
				+ ", Amount=" + Amount + "]";
	}
	
	

}
