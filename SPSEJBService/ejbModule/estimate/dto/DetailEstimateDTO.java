package estimate.dto;

import java.math.BigDecimal;


public class DetailEstimateDTO {
	
	
	private String resourceType;
	
	private String resourceCode;
	
	private String resourceName;
	
	private String resCategory;
	
	private String uom;
	
	private BigDecimal unitPrice;
	
	private BigDecimal estimateCost;
	
	private BigDecimal rebateCost;
	
	private BigDecimal rebateQuantity;
	
	private BigDecimal reusableQuantity;
	
	private BigDecimal offChargeQuantity;
	
	public BigDecimal getRebateCost() {
		return rebateCost;
	}

	public void setRebateCost(BigDecimal rebateCost) {
		this.rebateCost = rebateCost;
	}
	
	public BigDecimal getRebateQuantity() {
		return rebateQuantity;
	}

	public void setRebateQuantity(BigDecimal rebateQuantity) {
		this.rebateQuantity = rebateQuantity;
	}
	
	public BigDecimal getReusableQuantity() {
		return reusableQuantity;
	}

	public void setReusableQuantity(BigDecimal reusableQuantity) {
		this.reusableQuantity = reusableQuantity;
	}
	
	public BigDecimal getOffChargeQuantity() {
		return offChargeQuantity;
	}

	public void setOffChargeQuantity(BigDecimal offChargeQuantity) {
		this.offChargeQuantity = offChargeQuantity;
	}
	
	public BigDecimal getEstimateCost() {
		return estimateCost;
	}

	public void setEstimateCost(BigDecimal estimateCost) {
		this.estimateCost = estimateCost;
	}

	public String getResCategory() {
		return resCategory;
	}

	public void setResCategory(String resCategory) {
		this.resCategory = resCategory;
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

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public String getUom() {
		return uom;
	}

	public void setUom(String uom) {
		this.uom = uom;
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

	public BigDecimal getEstimatedQuantity() {
		return estimatedQuantity;
	}

	public void setEstimatedQuantity(BigDecimal estimatedQuantity) {
		this.estimatedQuantity = estimatedQuantity;
	}

	private BigDecimal estimatedCost;
	
	private BigDecimal estimatedQuantity;

	private BigDecimal estimatedQuantityBefore;
	
	private BigDecimal estQuanDif;
	

	public BigDecimal getEstQuanDif() {
		return estQuanDif;
	}

	public void setEstQuanDif(BigDecimal estQuanDif) {
		this.estQuanDif = estQuanDif;
	}

	public BigDecimal getEstimatedQuantityBefore() {
		return estimatedQuantityBefore;
	}

	public void setEstimatedQuantityBefore(BigDecimal estimatedQuantityBefore) {
		this.estimatedQuantityBefore = estimatedQuantityBefore;
	}

	

}
