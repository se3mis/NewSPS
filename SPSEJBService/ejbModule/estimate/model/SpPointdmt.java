package estimate.model;

import java.io.Serializable;
import java.math.BigDecimal;

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

@Table(name="SPPEGGINGDMT")
public class SpPointdmt implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SpPointdmtPK id;

	@Column(name="POLETYPEID")
	private String poleTypeId;

	@Column(name="RES_TYPE")
	private String resType;
	
	@Column(name="RES_CAT")
	private long resCat;
	
	@Column(name="RES_NAME")
	private String resName;

	@Column(name="UOM")
	private String uom;
	
	@Column(name="ESTIMATE_QTY")
	private BigDecimal estimatedQuantity;
	
	@Column(name="TOLERANCE")
	private String tolerance;
	
	@Column(name="UNIT_PRICE")
	private float unitPrice;
	
	

	
    public SpPointdmt() {
    }

    

	public long getResCat() {
		return resCat;
	}



	public void setResCat(long resCat) {
		this.resCat = resCat;
	}


	public BigDecimal getEstimatedQuantity() {
		return estimatedQuantity;
	}



	public void setEstimatedQuantity(BigDecimal estimatedQuantity) {
		//this.estimatedQuantity = estimatedQuantity;
		this.estimatedQuantity = estimatedQuantity;
	}



	public float getUnitPrice() {
		return unitPrice;
	}



	public void setUnitPrice(float unitPrice) {
		this.unitPrice = unitPrice;
	}


	public SpPointdmtPK getId() {
		return id;
	}



	public void setId(SpPointdmtPK id) {
		this.id = id;
	}



	public String getPoleTypeId() {
		return poleTypeId;
	}



	public void setPoleTypeId(String poleTypeId) {
		this.poleTypeId = poleTypeId;
	}



	public String getResType() {
		return resType;
	}



	public void setResType(String resType) {
		this.resType = resType;
	}



	


	public String getResName() {
		return resName;
	}



	public void setResName(String resName) {
		this.resName = resName;
	}



	public String getUom() {
		return uom;
	}



	public void setUom(String uom) {
		this.uom = uom;
	}



	


	public String getTolerance() {
		return tolerance;
	}



	public void setTolerance(String tolerance) {
		this.tolerance = tolerance;
	}





	@Override
	public String toString() {
		return "SpPointdmt [id=" + id + ", resType=" + resType
				+ ", resName=" + resName + ", estimatedQuantity=" + estimatedQuantity + ", resCat=" + resCat + ", unitPrice=" + unitPrice + "]";
	}

}