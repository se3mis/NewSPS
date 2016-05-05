package estimate.dto;

import java.math.BigDecimal;


public class Norm {

	private String lineSectionTypeId;

	private String uom;

	
	private float standardCost;
	

	private String description;
	
	private BigDecimal estimatedCost;
	
	private BigDecimal quantity;

	private String parentId;
	

	public BigDecimal getEstimatedCost() {
		return estimatedCost;
	}


	public void setEstimatedCost(BigDecimal estimatedCost) {
		this.estimatedCost = estimatedCost;
	}


	public BigDecimal getQuantity() {
		return quantity;
	}


	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}


	public String getLineSectionTypeId() {
		return lineSectionTypeId;
	}


	public void setLineSectionTypeId(String lineSectionTypeId) {
		this.lineSectionTypeId = lineSectionTypeId;
	}


	public String getUom() {
		return uom;
	}


	public void setUom(String uom) {
		this.uom = uom;
	}


	public float getStandardCost() {
		return standardCost;
	}


	public void setStandardCost(float standardCost) {
		this.standardCost = standardCost;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getParentId() {
		return parentId;
	}


	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	
	

}
