package estimate.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the SPCSTNGM database table.
 * 
 */
@Entity
public class Spcstngm implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SpcstngmPK id;

	@Column(name="FIXED_COST")
	private BigDecimal fixedCost;

	@Column(name="SECURITY_DEPOSIT")
	private BigDecimal securityDeposit;

	@Column(name="TEMPORARY_DEPOSIT")
	private BigDecimal temporaryDeposit;

    @Temporal( TemporalType.DATE)
	@Column(name="UPD_DATE")
	private Date updDate;

	@Column(name="UPD_TIME")
	private String updTime;

	@Column(name="UPD_USER")
	private String updUser;

	@Column(name="WIRE_METER_PRICE")
	private BigDecimal wireMeterPrice;

    public Spcstngm() {
    }

	public SpcstngmPK getId() {
		return this.id;
	}

	public void setId(SpcstngmPK id) {
		this.id = id;
	}
	
	public BigDecimal getFixedCost() {
		return this.fixedCost;
	}

	public void setFixedCost(BigDecimal fixedCost) {
		this.fixedCost = fixedCost;
	}

	public BigDecimal getSecurityDeposit() {
		return this.securityDeposit;
	}

	public void setSecurityDeposit(BigDecimal securityDeposit) {
		this.securityDeposit = securityDeposit;
	}

	public BigDecimal getTemporaryDeposit() {
		return this.temporaryDeposit;
	}

	public void setTemporaryDeposit(BigDecimal temporaryDeposit) {
		this.temporaryDeposit = temporaryDeposit;
	}

	public Date getUpdDate() {
		return this.updDate;
	}

	public void setUpdDate(Date updDate) {
		this.updDate = updDate;
	}

	public String getUpdTime() {
		return this.updTime;
	}

	public void setUpdTime(String updTime) {
		this.updTime = updTime;
	}

	public String getUpdUser() {
		return this.updUser;
	}

	public void setUpdUser(String updUser) {
		this.updUser = updUser;
	}

	public BigDecimal getWireMeterPrice() {
		return this.wireMeterPrice;
	}

	public void setWireMeterPrice(BigDecimal wireMeterPrice) {
		this.wireMeterPrice = wireMeterPrice;
	}

	@Override
	public String toString() {
		return "Spcstngm [id=" + id + ", fixedCost=" + fixedCost
				+ ", securityDeposit=" + securityDeposit
				+ ", temporaryDeposit=" + temporaryDeposit + ", updDate="
				+ updDate + ", updTime=" + updTime + ", updUser=" + updUser
				+ ", wireMeterPrice=" + wireMeterPrice + "]";
	}

}