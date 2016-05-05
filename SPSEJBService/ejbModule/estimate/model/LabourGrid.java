package estimate.model;

import java.io.Serializable;
import java.math.BigDecimal;

@SuppressWarnings("serial")
public class LabourGrid implements Serializable{
	private String labourCode;
	private String labourName;
	private String activityCode;
	private String activityName;
	private String matCd;
	private BigDecimal phase;
	private String spanTypeOrConduttorType;
	private String description;
	private BigDecimal unitPrice;
	private BigDecimal itemQty;
	private BigDecimal labourCost;
	private BigDecimal customerQty;
	private BigDecimal customerCost;
	private BigDecimal labourHours;
	private BigDecimal unitLabourHrs;
	private BigDecimal cebUnitPrice;
	private BigDecimal cebLabourCost;
	
	public LabourGrid() {
		super();
	}
	
	//
	public LabourGrid(String labourCode, String activityDescription, BigDecimal itemQty, BigDecimal labourCost, BigDecimal labourHours, BigDecimal unitLabourHrs, BigDecimal unitPrice){
		this.labourCode=labourCode;
		this.description=activityDescription;
		this.itemQty=itemQty;
		this.unitPrice=unitPrice;
		this.labourCost= labourCost;
		this.labourHours=labourHours;
		this.unitLabourHrs=unitLabourHrs;
	}
	//New 
	public LabourGrid(String labourCode, String activityDescription, BigDecimal itemQty, BigDecimal labourCost, BigDecimal labourHours, BigDecimal unitLabourHrs, BigDecimal unitPrice, BigDecimal cebUnitPrice, BigDecimal cebLabourCost){
		this.labourCode=labourCode;
		this.description=activityDescription;
		this.itemQty=itemQty;
		this.unitPrice=unitPrice;
		this.labourCost= labourCost;
		this.labourHours=labourHours;
		this.unitLabourHrs=unitLabourHrs;
		this.cebUnitPrice=cebUnitPrice;
		this.cebLabourCost=cebLabourCost;
		
	}
	
	public LabourGrid(String labourCode, String activityCode,String activityName, String matCd, String description,
			BigDecimal unitPrice) {
		super();
		this.labourCode = labourCode;
		this.activityCode=activityCode;
		this.activityName = activityName;
		this.matCd = matCd;
		this.description= description;
		this.unitPrice = unitPrice;
	}
	//New
	public LabourGrid(String labourCode, String activityCode,String activityName, String matCd, String description,
			BigDecimal unitPrice, BigDecimal unitLabourHrs) {
		super();
		this.labourCode = labourCode;
		this.activityCode=activityCode;
		this.activityName = activityName;
		this.matCd = matCd;
		this.description= description;
		this.unitPrice = unitPrice;
		this.unitLabourHrs = unitLabourHrs;
	}
	
	public LabourGrid(String labourCode, String activityCode,String activityName, BigDecimal phase, String spanTypeOrConduttorType,
			BigDecimal unitPrice) {
		super();
		String strPhase="";
		this.labourCode = labourCode;
		this.activityCode=activityCode;
		this.activityName = activityName;
		this.phase=phase;
		if(phase.toString().equals("1"))
			strPhase="Single Phase";
		else strPhase="Three Phase";
		this.spanTypeOrConduttorType=spanTypeOrConduttorType;
		if(spanTypeOrConduttorType.toString().equals("LAST"))
			spanTypeOrConduttorType="Last Span";
		else if(spanTypeOrConduttorType.toString().equals("OTHER"))
			spanTypeOrConduttorType="Additional Span";
		//else spanTypeOrConduttorType=spanTypeOrConduttorType;
		this.description= activityName +" ("+strPhase+ "- "+ spanTypeOrConduttorType+" )";
		this.unitPrice = unitPrice;
	}
	//New
	public LabourGrid(String labourCode, String activityCode,String activityName, BigDecimal phase, String spanTypeOrConduttorType,
			BigDecimal unitPrice, BigDecimal unitLabourHrs) {
		super();
		String strPhase="";
		this.labourCode = labourCode;
		this.activityCode=activityCode;
		this.activityName = activityName;
		this.phase=phase;
		if(phase.toString().equals("1"))
			strPhase="Single Phase";
		else strPhase="Three Phase";
		this.spanTypeOrConduttorType=spanTypeOrConduttorType;
		if(spanTypeOrConduttorType.toString().equals("LAST"))
			spanTypeOrConduttorType="Last Span";
		else if(spanTypeOrConduttorType.toString().equals("OTHER"))
			spanTypeOrConduttorType="Additional Span";
		//else spanTypeOrConduttorType=spanTypeOrConduttorType;
		this.description= activityName +" ("+strPhase+ "- "+ spanTypeOrConduttorType+" )";
		this.unitPrice = unitPrice;
		this.unitLabourHrs = unitLabourHrs;
	}



	//public LabourGrid(String labourCode, String activityName, String matCd, String description,
	//		BigDecimal unitPrice, BigDecimal itemQty, BigDecimal labourCost
	//		) {
	//	super();
	//	this.labourCode = labourCode;
	//	this.activityName = activityName;
	//	this.matCd = matCd;
	//	this.description = description;
	//	this.unitPrice = unitPrice;
	//	this.itemQty = itemQty;
	//	this.labourCost=labourCost;
		
	//}



	public LabourGrid(String labourCode, String labourName,
			String activityName, String matCd, String description,
			BigDecimal unitPrice, BigDecimal estimateQty,
			BigDecimal estimateCost, BigDecimal customerQty,
			BigDecimal customerCost) {
		super();
		this.labourCode = labourCode;
		this.labourName = labourName;
		this.activityName = activityName;
		this.matCd = matCd;
		this.description = description;
		this.unitPrice = unitPrice;
		this.customerQty = customerQty;
		this.customerCost = customerCost;
	}



	public LabourGrid(String labourCode, String labourName, String description,
			BigDecimal unitPrice) {
		super();
		this.labourCode = labourCode;
		this.labourName = labourName;
		this.description = description;
		this.unitPrice = unitPrice;
	}
	//New
	public LabourGrid(String labourCode, String labourName, String description,
			BigDecimal unitPrice, BigDecimal unitLabourHrs) {
		super();
		this.labourCode = labourCode;
		this.labourName = labourName;
		this.description = description;
		this.unitPrice = unitPrice;
		this.unitLabourHrs = unitLabourHrs;
	}



	/*public LabourGrid(String labourCode, String description,
			BigDecimal unitPrice) {
		super();
		this.labourCode = labourCode;
		this.description = description;
		this.unitPrice = unitPrice;
	}*/
	public String getLabourCode() {
		return labourCode;
	}
	public void setLabourCode(String labourCode) {
		this.labourCode = labourCode;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public BigDecimal getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}
	
	public String getLabourName() {
		return labourName;
	}

	public void setLabourName(String labourName) {
		this.labourName = labourName;
	}
	
	

	public BigDecimal getCustomerQty() {
		return customerQty;
	}

	public void setCustomerQty(BigDecimal customerQty) {
		this.customerQty = customerQty;
	}

	public BigDecimal getCustomerCost() {
		return customerCost;
	}

	public void setCustomerCost(BigDecimal customerCost) {
		this.customerCost = customerCost;
	}
	
	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public String getMatCd() {
		return matCd;
	}

	public void setMatCd(String matCd) {
		this.matCd = matCd;
	}

	
	
	public BigDecimal getItemQty() {
		return itemQty;
	}

	public void setItemQty(BigDecimal itemQty) {
		this.itemQty = itemQty;
	}
	
	public BigDecimal getLabourCost() {
		return labourCost;
	}

	public void setLabourCost(BigDecimal labourCost) {
		this.labourCost = labourCost;
	}
	public String getActivityCode() {
		return activityCode;
	}
	public void setActivityCode(String activityCode) {
		this.activityCode = activityCode;
	}
	
	public BigDecimal getPhase() {
		return phase;
	}

	public void setPhase(BigDecimal phase) {
		this.phase = phase;
	}

	public String getSpanType() {
		return spanTypeOrConduttorType;
	}

	public void setSpanType(String spanType) {
		this.spanTypeOrConduttorType = spanType;
	}
	
	public BigDecimal getLabourHours() {
		return labourHours;
	}


	public void setLabourHours(BigDecimal labourHours) {
		this.labourHours = labourHours;
	}


	public BigDecimal getUnitLabourHrs() {
		return unitLabourHrs;
	}


	public void setUnitLabourHrs(BigDecimal unitLabourHrs) {
		this.unitLabourHrs = unitLabourHrs;
	}
	
	public BigDecimal getCebUnitPrice() {
		return cebUnitPrice;
	}


	public void setCebUnitPrice(BigDecimal cebUnitPrice) {
		this.cebUnitPrice = cebUnitPrice;
	}


	public BigDecimal getCebLabourCost() {
		return cebLabourCost;
	}


	public void setCebLabourCost(BigDecimal cebLabourCost) {
		this.cebLabourCost = cebLabourCost;
	}
	
	@Override
	public String toString() {
		return "LabourGrid [labourCode=" + labourCode + ", labourName="
				+ labourName + ", activityCode=" + activityCode
				+ ", activityName=" + activityName + ", matCd=" + matCd
				+ ", phase=" + phase + ", spanTypeOrConduttorType="
				+ spanTypeOrConduttorType + ", description=" + description
				+ ", unitPrice=" + unitPrice + ", itemQty=" + itemQty
				+ ", labourCost=" + labourCost + ", customerQty=" + customerQty
				+ ", customerCost=" + customerCost + ", labourHours="
				+ labourHours + ", unitLabourHrs=" + unitLabourHrs
				+ ", cebUnitPrice=" + cebUnitPrice + ", cebLabourCost="
				+ cebLabourCost + "]";
	}


	
	


}