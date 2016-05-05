package estimate.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the SPSETSTU database table.
 * 
 */
@Entity
public class Spsetstu implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SpsetstuPK id;

	@Column(name="MAT_QTY")
	private BigDecimal matQty;

    public Spsetstu() {
    }

	public SpsetstuPK getId() {
		return this.id;
	}

	public void setId(SpsetstuPK id) {
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
		return "Spsetstu [id=" + id + ", matQty=" + matQty + "]";
	}

}