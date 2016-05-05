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
public class CbchqrghPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "chq_no")
    private BigDecimal chqNo;
    @Basic(optional = false)
    @Column(name = "bank_ac")
    private BigDecimal bankAc;
    @Basic(optional = false)
    @Column(name = "bank_cd")
    private String bankCd;
    @Basic(optional = false)
    @Column(name = "bnk_dept")
    private String bnkDept;

    public CbchqrghPK() {
    }

    public CbchqrghPK(BigDecimal chqNo, BigDecimal bankAc, String bankCd, String bnkDept) {
        this.chqNo = chqNo;
        this.bankAc = bankAc;
        this.bankCd = bankCd;
        this.bnkDept = bnkDept;
    }

    public BigDecimal getChqNo() {
        return chqNo;
    }

    public void setChqNo(BigDecimal chqNo) {
        this.chqNo = chqNo;
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
        hash += (chqNo != null ? chqNo.hashCode() : 0);
        hash += (bankAc != null ? bankAc.hashCode() : 0);
        hash += (bankCd != null ? bankCd.hashCode() : 0);
        hash += (bnkDept != null ? bnkDept.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CbchqrghPK)) {
            return false;
        }
        CbchqrghPK other = (CbchqrghPK) object;
        if ((this.chqNo == null && other.chqNo != null) || (this.chqNo != null && !this.chqNo.equals(other.chqNo))) {
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
        return "estimate.domain.CbchqrghPK[chqNo=" + chqNo + ", bankAc=" + bankAc + ", bankCd=" + bankCd + ", bnkDept=" + bnkDept + "]";
    }

}
