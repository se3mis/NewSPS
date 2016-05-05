package job.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the PCRSTYPM database table.
 * 
 */
@Entity
public class Pcrstypm implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="RES_TYPE")
	private String resType;

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

	@Column(name="RES_CAT")
	private BigDecimal resCat;

	private BigDecimal status;

	@Column(name="TYPE_NM")
	private String typeNm;

    public Pcrstypm() {
    }

	public String getResType() {
		return this.resType;
	}

	public void setResType(String resType) {
		this.resType = resType;
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

	public BigDecimal getResCat() {
		return this.resCat;
	}

	public void setResCat(BigDecimal resCat) {
		this.resCat = resCat;
	}

	public BigDecimal getStatus() {
		return this.status;
	}

	public void setStatus(BigDecimal status) {
		this.status = status;
	}

	public String getTypeNm() {
		return this.typeNm;
	}

	public void setTypeNm(String typeNm) {
		this.typeNm = typeNm;
	}

	@Override
	public String toString() {
		return "Pcrstypm [resType=" + resType + ", entBy=" + entBy + ", entDt="
				+ entDt + ", logId=" + logId + ", modBy=" + modBy + ", modDt="
				+ modDt + ", resCat=" + resCat + ", status=" + status
				+ ", typeNm=" + typeNm + "]";
	}
	
	

}