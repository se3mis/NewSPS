package estimate.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the SPSECDEP database table.
 * 
 */
@Entity
public class Spsecdep implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SpsecdepPK id;

	@Column(name="SECURITY_DEPOSIT")
	private BigDecimal securityDeposit;

	@Column(name="UPDATED_BY")
	private String updatedBy;

    @Temporal( TemporalType.DATE)
	@Column(name="UPDATED_DATE")
	private Date updatedDate;

    public Spsecdep() {
    }

	public SpsecdepPK getId() {
		return this.id;
	}

	public void setId(SpsecdepPK id) {
		this.id = id;
	}
	
	public BigDecimal getSecurityDeposit() {
		return this.securityDeposit;
	}

	public void setSecurityDeposit(BigDecimal securityDeposit) {
		this.securityDeposit = securityDeposit;
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