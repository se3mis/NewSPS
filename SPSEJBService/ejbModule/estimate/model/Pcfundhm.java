package estimate.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the PCFUNDHM database table.
 * 
 */
@Entity
public class Pcfundhm implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="FUND_SOURCE")
	private String fundSource;

	@Column(name="ENT_BY")
	private String entBy;

    @Temporal( TemporalType.DATE)
	@Column(name="ENT_DT")
	private Date entDt;

	@Column(name="LOG_ID")
	private BigDecimal logId;

	@Column(name="MOD_BY")
	private String modBy;

    @Temporal( TemporalType.DATE)
	@Column(name="MOD_DT")
	private Date modDt;

	private String name;

	private BigDecimal status;

    public Pcfundhm() {
    }

	public String getFundSource() {
		return this.fundSource;
	}

	public void setFundSource(String fundSource) {
		this.fundSource = fundSource;
	}

	public String getEntBy() {
		return this.entBy;
	}

	public void setEntBy(String entBy) {
		this.entBy = entBy;
	}

	public Date getEntDt() {
		return this.entDt;
	}

	public void setEntDt(Date entDt) {
		this.entDt = entDt;
	}

	public BigDecimal getLogId() {
		return this.logId;
	}

	public void setLogId(BigDecimal logId) {
		this.logId = logId;
	}

	public String getModBy() {
		return this.modBy;
	}

	public void setModBy(String modBy) {
		this.modBy = modBy;
	}

	public Date getModDt() {
		return this.modDt;
	}

	public void setModDt(Date modDt) {
		this.modDt = modDt;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getStatus() {
		return this.status;
	}

	public void setStatus(BigDecimal status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Pcfundhm [fundSource=" + fundSource + ", entBy=" + entBy
				+ ", entDt=" + entDt + ", logId=" + logId + ", modBy=" + modBy
				+ ", modDt=" + modDt + ", name=" + name + ", status=" + status
				+ "]";
	}
	
	

}