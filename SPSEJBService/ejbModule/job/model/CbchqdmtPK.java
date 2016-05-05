package job.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the CBCHQDMT database table.
 * 
 */
@Embeddable
public class CbchqdmtPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="GRP_ID")
	private long grpId;

	@Column(name="CHQ_RUN")
	private String chqRun;

	@Column(name="DEPT_ID")
	private String deptId;

	@Column(name="BANK_AC")
	private long bankAc;

	@Column(name="BANK_CD")
	private String bankCd;

	@Column(name="BNK_DEPT")
	private String bnkDept;

	@Column(name="PYMT_DOCNO")
	private String pymtDocno;

	@Column(name="PYMT_DOCPF")
	private String pymtDocpf;

	@Column(name="PYMT_DEPT")
	private String pymtDept;

    public CbchqdmtPK() {
    }
	public long getGrpId() {
		return this.grpId;
	}
	public void setGrpId(long grpId) {
		this.grpId = grpId;
	}
	public String getChqRun() {
		return this.chqRun;
	}
	public void setChqRun(String chqRun) {
		this.chqRun = chqRun;
	}
	public String getDeptId() {
		return this.deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public long getBankAc() {
		return this.bankAc;
	}
	public void setBankAc(long bankAc) {
		this.bankAc = bankAc;
	}
	public String getBankCd() {
		return this.bankCd;
	}
	public void setBankCd(String bankCd) {
		this.bankCd = bankCd;
	}
	public String getBnkDept() {
		return this.bnkDept;
	}
	public void setBnkDept(String bnkDept) {
		this.bnkDept = bnkDept;
	}
	public String getPymtDocno() {
		return this.pymtDocno;
	}
	public void setPymtDocno(String pymtDocno) {
		this.pymtDocno = pymtDocno;
	}
	public String getPymtDocpf() {
		return this.pymtDocpf;
	}
	public void setPymtDocpf(String pymtDocpf) {
		this.pymtDocpf = pymtDocpf;
	}
	public String getPymtDept() {
		return this.pymtDept;
	}
	public void setPymtDept(String pymtDept) {
		this.pymtDept = pymtDept;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof CbchqdmtPK)) {
			return false;
		}
		CbchqdmtPK castOther = (CbchqdmtPK)other;
		return 
			(this.grpId == castOther.grpId)
			&& this.chqRun.equals(castOther.chqRun)
			&& this.deptId.equals(castOther.deptId)
			&& (this.bankAc == castOther.bankAc)
			&& this.bankCd.equals(castOther.bankCd)
			&& this.bnkDept.equals(castOther.bnkDept)
			&& this.pymtDocno.equals(castOther.pymtDocno)
			&& this.pymtDocpf.equals(castOther.pymtDocpf)
			&& this.pymtDept.equals(castOther.pymtDept);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.grpId ^ (this.grpId >>> 32)));
		hash = hash * prime + this.chqRun.hashCode();
		hash = hash * prime + this.deptId.hashCode();
		hash = hash * prime + ((int) (this.bankAc ^ (this.bankAc >>> 32)));
		hash = hash * prime + this.bankCd.hashCode();
		hash = hash * prime + this.bnkDept.hashCode();
		hash = hash * prime + this.pymtDocno.hashCode();
		hash = hash * prime + this.pymtDocpf.hashCode();
		hash = hash * prime + this.pymtDept.hashCode();
		
		return hash;
    }
}