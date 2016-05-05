package estimate.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the SPLOOPMT database table.
 * 
 */
@Entity
public class Sploopmt implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SploopmtPK id;

	@Column(name="MAT_QTY")
	private BigDecimal matQty;

	@Column(name="UPDATED_BY")
	private String updatedBy;

    @Temporal( TemporalType.DATE)
	@Column(name="UPDATED_DATE")
	private Date updatedDate;

    public Sploopmt() {
    }

	public SploopmtPK getId() {
		return this.id;
	}

	public void setId(SploopmtPK id) {
		this.id = id;
	}
	
	public BigDecimal getMatQty() {
		return this.matQty;
	}

	public void setMatQty(BigDecimal matQty) {
		this.matQty = matQty;
	}

	public String getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedDate() {
		return this.updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

}