package estimate.model;

import java.io.Serializable;
import java.util.Date;

public class ReferenceDeleteInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private String estimateNo;
	private String deptId;
	private long revNo;
	private Date etimateDt;
	
	private String estType;
	
	
	public ReferenceDeleteInfo() {
		super();
	}
	
	public String getEstimateNo() {
		return estimateNo;
	}
	
	public void setEstimateNo(String estimateNo) {
		this.estimateNo = estimateNo;
	}
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public long getRevNo() {
		return revNo;
	}
	public void setRevNo(long revNo) {
		this.revNo = revNo;
	}
	public Date getEtimateDt() {
		return etimateDt;
	}
	public void setEtimateDt(Date etimateDt) {
		this.etimateDt = etimateDt;
	}
	public String getEstType() {
		return estType;
	}
	public void setEstType(String estType) {
		this.estType = estType;
	}

	@Override
	public String toString() {
		return "ReferenceDeleteInfo [estimateNo=" + estimateNo + ", deptId="
				+ deptId + ", RevNo=" + revNo + ", EtimateDt=" + etimateDt
				+ ", EstType=" + estType + "]";
	}
	
	
	
	
	
	
}
