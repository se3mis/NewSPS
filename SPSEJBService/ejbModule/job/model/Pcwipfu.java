package job.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the PCWIPFU database table.
 * 
 */
@Entity
public class Pcwipfu implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PcwipfuPK id;

	@Column(name="ADD_AMT")
	private BigDecimal addAmt;

	@Column(name="CL_BAL")
	private BigDecimal clBal;

	@Column(name="OP_BAL")
	private BigDecimal opBal;

	@Column(name="TRANS_AMT")
	private BigDecimal transAmt;

    public Pcwipfu() {
    }

	public PcwipfuPK getId() {
		return this.id;
	}

	public void setId(PcwipfuPK id) {
		this.id = id;
	}
	
	public BigDecimal getAddAmt() {
		return this.addAmt;
	}

	public void setAddAmt(BigDecimal addAmt) {
		this.addAmt = addAmt;
	}

	public BigDecimal getClBal() {
		return this.clBal;
	}

	public void setClBal(BigDecimal clBal) {
		this.clBal = clBal;
	}

	public BigDecimal getOpBal() {
		return this.opBal;
	}

	public void setOpBal(BigDecimal opBal) {
		this.opBal = opBal;
	}

	public BigDecimal getTransAmt() {
		return this.transAmt;
	}

	public void setTransAmt(BigDecimal transAmt) {
		this.transAmt = transAmt;
	}

}