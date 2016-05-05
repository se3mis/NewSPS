package estimate.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the SPSTAYMT database table.
 * 
 */
@Entity
public class Spstaymt implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SpstaymtPK id;

	@Column(name="MAT_QTY")
	private BigDecimal matQty;

    public Spstaymt() {
    }

	public SpstaymtPK getId() {
		return this.id;
	}

	public void setId(SpstaymtPK id) {
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
		return "Spstaymt [id=" + id + ", matQty=" + matQty + "]";
	}

}