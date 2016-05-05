package estimate.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * Author: Dinusha Nirmalie
 * Created: March 01, 2012 11:23:42 AM
 */

/**
 * The persistent class for the SpPegInfo database table.
 * 
 */


@Entity
@Table(name="SPS_COST_CALCULATION_MASTER")
public class CostCalculationMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private CostCalculationMasterPK id;

	@Column(name="COMBINATION_ENTRIES")
	private String combinationEntry;

	@Column(name="PRIORITY")
	private String priority;
	
	
	
	@Column(name="ENTRY_TYPE")
	private String entryType;
	
	@Column(name="FUNDID")
	private String fundId;
	
	@Column(name="PERCENTAGE")
	private double percentage;

	
	@Column(name="EFFECTIVE_FROM")
	private Date effectiveFrom;

	@Column(name="EFFECTIVE_TO")
	private Date effectiveTo;
	
    public CostCalculationMaster() {
    }

    

	





	public CostCalculationMasterPK getId() {
		return id;
	}









	public void setId(CostCalculationMasterPK id) {
		this.id = id;
	}









	public String getCombinationEntry() {
		return combinationEntry;
	}









	public void setCombinationEntry(String combinationEntry) {
		this.combinationEntry = combinationEntry;
	}









	public String getPriority() {
		return priority;
	}









	public void setPriority(String priority) {
		this.priority = priority;
	}









	public String getEntryType() {
		return entryType;
	}









	public void setEntryType(String entryType) {
		this.entryType = entryType;
	}









	public double getPercentage() {
		return percentage;
	}









	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}
















	public Date getEffectiveFrom() {
		return effectiveFrom;
	}









	public void setEffectiveFrom(Date effectiveFrom) {
		this.effectiveFrom = effectiveFrom;
	}









	public Date getEffectiveTo() {
		return effectiveTo;
	}









	public void setEffectiveTo(Date effectiveTo) {
		this.effectiveTo = effectiveTo;
	}














	public String getFundId() {
		return fundId;
	}









	public void setFundId(String fundId) {
		this.fundId = fundId;
	}









	@Override
	public String toString() {
		return "CostCalculationMaster [id=" + id + ", combinationEntry=" + combinationEntry
				+ "]";
	}

}