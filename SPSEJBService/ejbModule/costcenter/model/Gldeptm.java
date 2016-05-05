package costcenter.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the GLDEPTM database table.
 * 
 */
@Entity
public class Gldeptm implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="DEPT_ID")
	private String deptId;

	@Column(name="COMP_ID")
	private String compId;

	@Column(name="CS_PS")
	private String csPs;

	@Column(name="DEPT_NM")
	private String deptNm;

	@Column(name="ENT_BY")
	private String entBy;

    @Temporal( TemporalType.DATE)
	@Column(name="ENT_DT")
	private Date entDt;

    @Lob()
	private String filter;

	@Column(name="LOG_ID")
	private BigDecimal logId;

	@Column(name="MODI_BY")
	private String modiBy;

    @Temporal( TemporalType.DATE)
	@Column(name="MODI_DT")
	private Date modiDt;

	private BigDecimal status;

    public Gldeptm() {
    }
    
    
	public Gldeptm(String deptId, String compId, String csPs, String deptNm,
			String entBy, Date entDt, String filter, BigDecimal logId,
			String modiBy, Date modiDt, BigDecimal status) {
		super();
		this.deptId = deptId;
		this.compId = compId;
		this.csPs = csPs;
		this.deptNm = deptNm;
		this.entBy = entBy;
		this.entDt = entDt;
		this.filter = filter;
		this.logId = logId;
		this.modiBy = modiBy;
		this.modiDt = modiDt;
		this.status = status;
	}


	public String getDeptId() {
		return this.deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getCompId() {
		return this.compId;
	}

	public void setCompId(String compId) {
		this.compId = compId;
	}

	public String getCsPs() {
		return this.csPs;
	}

	public void setCsPs(String csPs) {
		this.csPs = csPs;
	}

	public String getDeptNm() {
		return this.deptNm;
	}

	public void setDeptNm(String deptNm) {
		this.deptNm = deptNm;
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

	public String getFilter() {
		return this.filter;
	}

	public void setFilter(String filter) {
		this.filter = filter;
	}

	public BigDecimal getLogId() {
		return this.logId;
	}

	public void setLogId(BigDecimal logId) {
		this.logId = logId;
	}

	public String getModiBy() {
		return this.modiBy;
	}

	public void setModiBy(String modiBy) {
		this.modiBy = modiBy;
	}

	public Date getModiDt() {
		return this.modiDt;
	}

	public void setModiDt(Date modiDt) {
		this.modiDt = modiDt;
	}

	public BigDecimal getStatus() {
		return this.status;
	}

	public void setStatus(BigDecimal status) {
		this.status = status;
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((deptId == null) ? 0 : deptId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Gldeptm other = (Gldeptm) obj;
		if (deptId == null) {
			if (other.deptId != null)
				return false;
		} else if (!deptId.equals(other.deptId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Gldeptm [deptId=" + deptId + ", compId=" + compId + ", csPs="
				+ csPs + ", deptNm=" + deptNm + ", entBy=" + entBy + ", entDt="
				+ entDt + ", filter=" + filter + ", logId=" + logId
				+ ", modiBy=" + modiBy + ", modiDt=" + modiDt + ", status="
				+ status + "]";
	}
	

}