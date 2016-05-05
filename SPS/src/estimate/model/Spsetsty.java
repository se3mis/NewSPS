package estimate.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the SPSETSTY database table.
 * 
 */
@Entity
public class Spsetsty implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SpsetstyPK id;

	@Column(name="MAT_QTY")
	private BigDecimal matQty;

    public Spsetsty() {
    }

	public SpsetstyPK getId() {
		return this.id;
	}

	public void setId(SpsetstyPK id) {
		this.id = id;
	}
	
	public BigDecimal getMatQty() {
		return this.matQty;
	}

	public void setMatQty(BigDecimal matQty) {
		this.matQty = matQty;
	}

}