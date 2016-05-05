package job.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the PCWIPBAL database table.
 * 
 */
@Entity
public class Pcwipbal implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PcwipbalPK id;

	@Column(name="ADD_JBS")
	private BigDecimal addJbs;

	@Column(name="CL_JBS")
	private BigDecimal clJbs;

	@Column(name="OP_JBS")
	private BigDecimal opJbs;

	@Column(name="TRANS_JBS")
	private BigDecimal transJbs;

    public Pcwipbal() {
    }

	public PcwipbalPK getId() {
		return this.id;
	}

	public void setId(PcwipbalPK id) {
		this.id = id;
	}
	
	public BigDecimal getAddJbs() {
		return this.addJbs;
	}

	public void setAddJbs(BigDecimal addJbs) {
		this.addJbs = addJbs;
	}

	public BigDecimal getClJbs() {
		return this.clJbs;
	}

	public void setClJbs(BigDecimal clJbs) {
		this.clJbs = clJbs;
	}

	public BigDecimal getOpJbs() {
		return this.opJbs;
	}

	public void setOpJbs(BigDecimal opJbs) {
		this.opJbs = opJbs;
	}

	public BigDecimal getTransJbs() {
		return this.transJbs;
	}

	public void setTransJbs(BigDecimal transJbs) {
		this.transJbs = transJbs;
	}

}