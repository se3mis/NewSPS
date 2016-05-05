package estimate.web;

import java.math.BigDecimal;

public class EstimateRecord {
	private String resourceType;
	private String resourceCategory;
	private String resourceCode;
	private String resourceName;
	private BigDecimal tolerance;
	private String uom;
	private BigDecimal estimatedQuantity;
	private BigDecimal unitPrice;
	private BigDecimal estimatedCost;
	
	public EstimateRecord(){
		
	}
	
	public EstimateRecord(String resourceType, String resourceCategory,
			String resourceCode, String resourceName, BigDecimal tolerance,
			String uom, BigDecimal estimatedQuantity, BigDecimal unitPrice,
			BigDecimal estimatedCost) {
		super();
		this.resourceType = resourceType;
		this.resourceCategory = resourceCategory;
		this.resourceCode = resourceCode;
		this.resourceName = resourceName;
		this.tolerance = tolerance;
		this.uom = uom;
		this.estimatedQuantity = estimatedQuantity;
		this.unitPrice = unitPrice;
		this.estimatedCost = estimatedCost;
	}
	public EstimateRecord(String resourceCode) {
		super();
		this.resourceCode = resourceCode;
		
	}
	
	public String getResourceType() {
		return resourceType;
	}
	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}
	public String getResourceCode() {
		return resourceCode;
	}
	public void setResourceCode(String resourceCode) {
		this.resourceCode = resourceCode;
	}
	public String getResourceCategory() {
		return resourceCategory;
	}
	public void setResourceCategory(String resourceCategory) {
		this.resourceCategory = resourceCategory;
	}
	public String getResourceName() {
		return resourceName;
	}
	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}
	public BigDecimal getTolerance() {
		return tolerance;
	}
	public void setTolerance(BigDecimal tolerance) {
		this.tolerance = tolerance;
	}
	public BigDecimal getEstimatedQuantity() {
		return estimatedQuantity;
	}
	public void setEstimatedQuantity(BigDecimal estimatedQuantity) {
		this.estimatedQuantity = estimatedQuantity;
	}
	public BigDecimal getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}
	public BigDecimal getEstimatedCost() {
		return estimatedCost;
	}
	public void setEstimatedCost(BigDecimal estimatedCost) {
		this.estimatedCost = estimatedCost;
	}
	public String getUom() {
		return uom;
	}
	public void setUom(String uom) {
		this.uom = uom;
	}

	@Override
	public String toString() {
		return "EstimateRecord [resourceType=" + resourceType
				+ ", resourceCategory=" + resourceCategory 
				+ ", resourceCode=" + resourceCode
				+ ", resourceName=" + resourceName
				+ ", tolerance=" + tolerance
				+ ", uom=" + uom 
				+ ", unitPrice=" + unitPrice
				+ ", estimatedQuantity=" + estimatedQuantity
				+ ", estimatedCost=" + estimatedCost
				+ "]\n";
	}
	

}
