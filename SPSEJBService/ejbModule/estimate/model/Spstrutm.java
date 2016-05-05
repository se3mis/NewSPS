package estimate.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the SPSTRUTM database table.
 * 
 */
@Entity
public class Spstrutm implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SpstrutmPK id;

	@Column(name="MAT_QTY")
	private BigDecimal matQty;

    public Spstrutm() {
    }

	public SpstrutmPK getId() {
		return this.id;
	}

	public void setId(SpstrutmPK id) {
		this.id = id;
	}
	
	public BigDecimal getMatQty() {
		return this.matQty;
	}

	public void setMatQty(BigDecimal matQty) {
		this.matQty = matQty;
	}

	@Override
	public String toString() {
		return "Spstrutm [id=" + id + ", matQty=" + matQty + "]";
	}

}