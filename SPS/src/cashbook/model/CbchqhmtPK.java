/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cashbook.model;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Dileepa
 */
@Embeddable
public class CbchqhmtPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "chq_run")
    private String chqRun;
    @Basic(optional = false)
    @Column(name = "dept_id")
    private String deptId;
    @Basic(optional = false)
    @Column(name = "bank_ac")
    private BigDecimal bankAc;
    @Basic(optional = false)
    @Column(name = "bank_cd")
    private String bankCd;
    @Basic(optional = false)
    @Column(name = "bnk_dept")
    private String bnkDept;

    public CbchqhmtPK() {
    }

    public CbchqhmtPK(String chqRun, String deptId, BigDecimal bankAc, String bankCd, String bnkDept) {
        this.chqRun = chqRun;
        this.deptId = deptId;
        this.bankAc = bankAc;
        this.bankCd = bankCd;
        this.bnkDept = bnkDept;
    }

    public String getChqRun() {
        return chqRun;
    }

    public void setChqRun(String chqRun) {
        this.chqRun = chqRun;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public BigDecimal getBankAc() {
        return bankAc;
    }

    public void setBankAc(BigDecimal bankAc) {
        this.bankAc = bankAc;
    }

    public String getBankCd() {
        return bankCd;
    }

    public void setBankCd(String bankCd) {
        this.bankCd = bankCd;
    }

    public String getBnkDept() {
        return bnkDept;
    }

    public void setBnkDept(String bnkDept) {
        this.bnkDept = bnkDept;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (chqRun != null ? chqRun.hashCode() : 0);
        hash += (deptId != null ? deptId.hashCode() : 0);
        hash += (bankAc != null ? bankAc.hashCode() : 0);
        hash += (bankCd != null ? bankCd.hashCode() : 0);
        hash += (bnkDept != null ? bnkDept.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CbchqhmtPK)) {
            return false;
        }
        CbchqhmtPK other = (CbchqhmtPK) object;
        if ((this.chqRun == null && other.chqRun != null) || (this.chqRun != null && !this.chqRun.equals(other.chqRun))) {
            return false;
        }
        if ((this.deptId == null && other.deptId != null) || (this.deptId != null && !this.deptId.equals(other.deptId))) {
            return false;
        }
        if ((this.bankAc == null && other.bankAc != null) || (this.bankAc != null && !this.bankAc.equals(other.bankAc))) {
            return false;
        }
        if ((this.bankCd == null && other.bankCd != null) || (this.bankCd != null && !this.bankCd.equals(other.bankCd))) {
            return false;
        }
        if ((this.bnkDept == null && other.bnkDept != null) || (this.bnkDept != null && !this.bnkDept.equals(other.bnkDept))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "estimate.domain.CbchqhmtPK[chqRun=" + chqRun + ", deptId=" + deptId + ", bankAc=" + bankAc + ", bankCd=" + bankCd + ", bnkDept=" + bnkDept + "]";
    }

}
