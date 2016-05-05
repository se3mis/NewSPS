package estimate.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the SPPOLEMT database table.
 * 
 */
@Entity
public class Sppolemt implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SppolemtPK id;

	@Column(name="MAT_QTY")
	private BigDecimal matQty;

    public Sppolemt() {
    }

	public SppolemtPK getId() {
		return this.id;
	}

	public void setId(SppolemtPK id) {
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
		return "Sppolemt [id=" + id + ", matQty=" + matQty + "]";
	}

}