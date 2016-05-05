/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cashbook.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Dileepa
 */
@Entity
//@Table(name = "cbchqrgh", catalog = "mispre", schema = "atula")
@NamedQueries({@NamedQuery(name = "Cbchqrgh.findAll", query = "SELECT c FROM Cbchqrgh c"), @NamedQuery(name = "Cbchqrgh.findByChqNo", query = "SELECT c FROM Cbchqrgh c WHERE c.cbchqrghPK.chqNo = :chqNo"), @NamedQuery(name = "Cbchqrgh.findByBankAc", query = "SELECT c FROM Cbchqrgh c WHERE c.cbchqrghPK.bankAc = :bankAc"), @NamedQuery(name = "Cbchqrgh.findByBankCd", query = "SELECT c FROM Cbchqrgh c WHERE c.cbchqrghPK.bankCd = :bankCd"), @NamedQuery(name = "Cbchqrgh.findByBnkDept", query = "SELECT c FROM Cbchqrgh c WHERE c.cbchqrghPK.bnkDept = :bnkDept"), @NamedQuery(name = "Cbchqrgh.findByChqBkId", query = "SELECT c FROM Cbchqrgh c WHERE c.chqBkId = :chqBkId"), @NamedQuery(name = "Cbchqrgh.findByInOut", query = "SELECT c FROM Cbchqrgh c WHERE c.inOut = :inOut"), @NamedQuery(name = "Cbchqrgh.findByStatus", query = "SELECT c FROM Cbchqrgh c WHERE c.status = :status"), @NamedQuery(name = "Cbchqrgh.findByRecStatus", query = "SELECT c FROM Cbchqrgh c WHERE c.recStatus = :recStatus"), @NamedQuery(name = "Cbchqrgh.findByChqDt", query = "SELECT c FROM Cbchqrgh c WHERE c.chqDt = :chqDt"), @NamedQuery(name = "Cbchqrgh.findByChqRun", query = "SELECT c FROM Cbchqrgh c WHERE c.chqRun = :chqRun"), @NamedQuery(name = "Cbchqrgh.findByChqAmt", query = "SELECT c FROM Cbchqrgh c WHERE c.chqAmt = :chqAmt"), @NamedQuery(name = "Cbchqrgh.findByLogId", query = "SELECT c FROM Cbchqrgh c WHERE c.logId = :logId"), @NamedQuery(name = "Cbchqrgh.findByAmtWord", query = "SELECT c FROM Cbchqrgh c WHERE c.amtWord = :amtWord")})
public class Cbchqrgh implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CbchqrghPK cbchqrghPK;
    @Column(name = "chq_bk_id")
    private BigDecimal chqBkId;
    @Column(name = "in_out")
    private Character inOut;
    @Column(name = "status")
    private BigDecimal status;
    @Column(name = "rec_status")
    private BigDecimal recStatus;
    @Basic(optional = false)
    @Column(name = "chq_dt")
    @Temporal(TemporalType.DATE)
    private Date chqDt;
    @Column(name = "chq_run")
    private String chqRun;
    @Column(name = "chq_amt")
    private Double chqAmt;
    @Column(name = "log_id")
    private BigDecimal logId;
    @Column(name = "amt_word")
    private String amtWord;

    public Cbchqrgh() {
    }

    public Cbchqrgh(CbchqrghPK cbchqrghPK) {
        this.cbchqrghPK = cbchqrghPK;
    }

    public Cbchqrgh(CbchqrghPK cbchqrghPK, Date chqDt) {
        this.cbchqrghPK = cbchqrghPK;
        this.chqDt = chqDt;
    }

    public Cbchqrgh(BigDecimal chqNo, BigDecimal bankAc, String bankCd, String bnkDept) {
        this.cbchqrghPK = new CbchqrghPK(chqNo, bankAc, bankCd, bnkDept);
    }

    public CbchqrghPK getCbchqrghPK() {
        return cbchqrghPK;
    }

    public void setCbchqrghPK(CbchqrghPK cbchqrghPK) {
        this.cbchqrghPK = cbchqrghPK;
    }

    public BigDecimal getChqBkId() {
        return chqBkId;
    }

    public void setChqBkId(BigDecimal chqBkId) {
        this.chqBkId = chqBkId;
    }

    public Character getInOut() {
        return inOut;
    }

    public void setInOut(Character inOut) {
        this.inOut = inOut;
    }

    public BigDecimal getStatus() {
        return status;
    }

    public void setStatus(BigDecimal status) {
        this.status = status;
    }

    public BigDecimal getRecStatus() {
        return recStatus;
    }

    public void setRecStatus(BigDecimal recStatus) {
        this.recStatus = recStatus;
    }

    public Date getChqDt() {
        return chqDt;
    }

    public void setChqDt(Date chqDt) {
        this.chqDt = chqDt;
    }

    public String getChqRun() {
        return chqRun;
    }

    public void setChqRun(String chqRun) {
        this.chqRun = chqRun;
    }

    public Double getChqAmt() {
        return chqAmt;
    }

    public void setChqAmt(Double chqAmt) {
        this.chqAmt = chqAmt;
    }

    public BigDecimal getLogId() {
        return logId;
    }

    public void setLogId(BigDecimal logId) {
        this.logId = logId;
    }

    public String getAmtWord() {
        return amtWord;
    }

    public void setAmtWord(String amtWord) {
        this.amtWord = amtWord;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cbchqrghPK != null ? cbchqrghPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cbchqrgh)) {
            return false;
        }
        Cbchqrgh other = (Cbchqrgh) object;
        if ((this.cbchqrghPK == null && other.cbchqrghPK != null) || (this.cbchqrghPK != null && !this.cbchqrghPK.equals(other.cbchqrghPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "estimate.domain.Cbchqrgh[cbchqrghPK=" + cbchqrghPK + "]";
    }

}
