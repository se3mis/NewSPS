package inventory.model;


import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the INPODMT database table.
 * 
 */
@Entity
public class Inpodmt implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private InpodmtPK id;

	@Temporal(TemporalType.DATE)
	@Column(name="BATCH_DT")
	private Date batchDt;

	private BigDecimal qty;

	public Inpodmt() {
	}

	public InpodmtPK getId() {
		return this.id;
	}

	public void setId(InpodmtPK id) {
		this.id = id;
	}

	public Date getBatchDt() {
		return this.batchDt;
	}

	public void setBatchDt(Date batchDt) {
		this.batchDt = batchDt;
	}

	public BigDecimal getQty() {
		return this.qty;
	}

	public void setQty(BigDecimal qty) {
		this.qty = qty;
	}

}