package estimate.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the SPESTMTM database table.
 * 
 */
@Entity
public class Spestmtm implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SpestmtmPK id;

	@Column(name="MAT_QTY")
	private BigDecimal matQty;

    @Temporal( TemporalType.DATE)
	@Column(name="UPD_DATE")
	private Date updDate;

	@Column(name="UPD_USER")
	private String updUser;

    public Spestmtm() {
    }

	public SpestmtmPK getId() {
		return this.id;
	}

	public void setId(SpestmtmPK id) {
		this.id = id;
	}
	
	public BigDecimal getMatQty() {
		return this.matQty;
	}

	public void setMatQty(BigDecimal matQty) {
		this.matQty = matQty;
	}

	public Date getUpdDate() {
		return this.updDate;
	}

	public void setUpdDate(Date updDate) {
		this.updDate = updDate;
	}

	public String getUpdUser() {
		return this.updUser;
	}

	public void setUpdUser(String updUser) {
		this.updUser = updUser;
	}

}