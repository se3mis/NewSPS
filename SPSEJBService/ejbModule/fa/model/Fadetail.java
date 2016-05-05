package fa.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the FADETAIL database table.
 * 
 */
@Entity
public class Fadetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private FadetailPK id;

	@Column(name="COMMITED_COST")
	private BigDecimal commitedCost;

	@Column(name="COMMITED_QTY")
	private BigDecimal commitedQty;

	private String status;

	private String uom;

    public Fadetail() {
    }

	public FadetailPK getId() {
		return this.id;
	}

	public void setId(FadetailPK id) {
		this.id = id;
	}
	
	public BigDecimal getCommitedCost() {
		return this.commitedCost;
	}

	public void setCommitedCost(BigDecimal commitedCost) {
		this.commitedCost = commitedCost;
	}

	public BigDecimal getCommitedQty() {
		return this.commitedQty;
	}

	public void setCommitedQty(BigDecimal commitedQty) {
		this.commitedQty = commitedQty;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUom() {
		return this.uom;
	}

	public void setUom(String uom) {
		this.uom = uom;
	}

	@Override
	public String toString() {
		return "Fadetail [id=" + id + ", commitedCost=" + commitedCost
				+ ", commitedQty=" + commitedQty + ", status=" + status
				+ ", uom=" + uom + "]";
	}

}