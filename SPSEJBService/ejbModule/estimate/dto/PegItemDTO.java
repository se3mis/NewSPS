package estimate.dto;

import java.math.BigDecimal;

import javax.persistence.Column;


public class PegItemDTO {
	
	
	private String nodeId;
	
	private BigDecimal noOfItem;
	
	private String nodeDescription;
	
	private String estimateNo;
	
	private String deptId;
	
	private String newNoOfItem;

	public String getNodeId() {
		return nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	
	public BigDecimal getNoOfItem() {
		return noOfItem;
	}

	public void setNoOfItem(BigDecimal noOfItem) {
		this.noOfItem = noOfItem;
	}

	public String getNodeDescription() {
		return nodeDescription;
	}

	public void setNodeDescription(String nodeDescription) {
		this.nodeDescription = nodeDescription;
	}

	public String getNewNoOfItem() {
		return newNoOfItem;
	}

	public void setNewNoOfItem(String newNoOfItem) {
		this.newNoOfItem = newNoOfItem;
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
	
	
	
	

}
