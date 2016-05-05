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
//@Table(name = "cbchqhmt", catalog = "mispre", schema = "atula")
@NamedQueries({@NamedQuery(name = "Cbchqhmt.findAll", query = "SELECT c FROM Cbchqhmt c"), @NamedQuery(name = "Cbchqhmt.findByChqRun", query = "SELECT c FROM Cbchqhmt c WHERE c.cbchqhmtPK.chqRun = :chqRun"), @NamedQuery(name = "Cbchqhmt.findByDeptId", query = "SELECT c FROM Cbchqhmt c WHERE c.cbchqhmtPK.deptId = :deptId"), @NamedQuery(name = "Cbchqhmt.findByBankAc", query = "SELECT c FROM Cbchqhmt c WHERE c.cbchqhmtPK.bankAc = :bankAc"), @NamedQuery(name = "Cbchqhmt.findByBankCd", query = "SELECT c FROM Cbchqhmt c WHERE c.cbchqhmtPK.bankCd = :bankCd"), @NamedQuery(name = "Cbchqhmt.findByBnkDept", query = "SELECT c FROM Cbchqhmt c WHERE c.cbchqhmtPK.bnkDept = :bnkDept"), @NamedQuery(name = "Cbchqhmt.findByDocPf", query = "SELECT c FROM Cbchqhmt c WHERE c.docPf = :docPf"), @NamedQuery(name = "Cbchqhmt.findByGrpCrt", query = "SELECT c FROM Cbchqhmt c WHERE c.grpCrt = :grpCrt"), @NamedQuery(name = "Cbchqhmt.findByStatus", query = "SELECT c FROM Cbchqhmt c WHERE c.status = :status"), @NamedQuery(name = "Cbchqhmt.findByTotAmt", query = "SELECT c FROM Cbchqhmt c WHERE c.totAmt = :totAmt"), @NamedQuery(name = "Cbchqhmt.findByRunBy", query = "SELECT c FROM Cbchqhmt c WHERE c.runBy = :runBy"), @NamedQuery(name = "Cbchqhmt.findByRunDt", query = "SELECT c FROM Cbchqhmt c WHERE c.runDt = :runDt"), @NamedQuery(name = "Cbchqhmt.findByModiBy", query = "SELECT c FROM Cbchqhmt c WHERE c.modiBy = :modiBy"), @NamedQuery(name = "Cbchqhmt.findByModiDt", query = "SELECT c FROM Cbchqhmt c WHERE c.modiDt = :modiDt"), @NamedQuery(name = "Cbchqhmt.findByConfBy", query = "SELECT c FROM Cbchqhmt c WHERE c.confBy = :confBy"), @NamedQuery(name = "Cbchqhmt.findByConfDt", query = "SELECT c FROM Cbchqhmt c WHERE c.confDt = :confDt"), @NamedQuery(name = "Cbchqhmt.findByApprvUid1", query = "SELECT c FROM Cbchqhmt c WHERE c.apprvUid1 = :apprvUid1"), @NamedQuery(name = "Cbchqhmt.findByApprvUid2", query = "SELECT c FROM Cbchqhmt c WHERE c.apprvUid2 = :apprvUid2"), @NamedQuery(name = "Cbchqhmt.findByApprBy1", query = "SELECT c FROM Cbchqhmt c WHERE c.apprBy1 = :apprBy1"), @NamedQuery(name = "Cbchqhmt.findByApprBy2", query = "SELECT c FROM Cbchqhmt c WHERE c.apprBy2 = :apprBy2"), @NamedQuery(name = "Cbchqhmt.findByRejctBy", query = "SELECT c FROM Cbchqhmt c WHERE c.rejctBy = :rejctBy"), @NamedQuery(name = "Cbchqhmt.findByRejctDt", query = "SELECT c FROM Cbchqhmt c WHERE c.rejctDt = :rejctDt"), @NamedQuery(name = "Cbchqhmt.findBySentDt1", query = "SELECT c FROM Cbchqhmt c WHERE c.sentDt1 = :sentDt1"), @NamedQuery(name = "Cbchqhmt.findBySentDt2", query = "SELECT c FROM Cbchqhmt c WHERE c.sentDt2 = :sentDt2"), @NamedQuery(name = "Cbchqhmt.findByApprDt1", query = "SELECT c FROM Cbchqhmt c WHERE c.apprDt1 = :apprDt1"), @NamedQuery(name = "Cbchqhmt.findByApprDt2", query = "SELECT c FROM Cbchqhmt c WHERE c.apprDt2 = :apprDt2"), @NamedQuery(name = "Cbchqhmt.findByPrtStatus", query = "SELECT c FROM Cbchqhmt c WHERE c.prtStatus = :prtStatus"), @NamedQuery(name = "Cbchqhmt.findByRemarks", query = "SELECT c FROM Cbchqhmt c WHERE c.remarks = :remarks")})
public class Cbchqhmt implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CbchqhmtPK cbchqhmtPK;
    @Column(name = "doc_pf")
    private String docPf;
    @Column(name = "grp_crt")
    private String grpCrt;
    @Column(name = "status")
    private BigDecimal status;
    @Column(name = "tot_amt")
    private Double totAmt;
    @Column(name = "run_by")
    private String runBy;
    @Basic(optional = false)
    @Column(name = "run_dt")
    @Temporal(TemporalType.DATE)
    private Date runDt;
    @Column(name = "modi_by")
    private String modiBy;
    @Basic(optional = false)
    @Column(name = "modi_dt")
    @Temporal(TemporalType.DATE)
    private Date modiDt;
    @Column(name = "conf_by")
    private String confBy;
    @Basic(optional = false)
    @Column(name = "conf_dt")
    @Temporal(TemporalType.DATE)
    private Date confDt;
    @Column(name = "apprv_uid1")
    private String apprvUid1;
    @Column(name = "apprv_uid2")
    private String apprvUid2;
    @Column(name = "appr_by1")
    private String apprBy1;
    @Column(name = "appr_by2")
    private String apprBy2;
    @Column(name = "rejct_by")
    private String rejctBy;
    @Column(name = "rejct_dt")
    @Temporal(TemporalType.DATE)
    private Date rejctDt;
    @Column(name = "sent_dt1")
    @Temporal(TemporalType.DATE)
    private Date sentDt1;
    @Column(name = "sent_dt2")
    @Temporal(TemporalType.DATE)
    private Date sentDt2;
    @Column(name = "appr_dt1")
    @Temporal(TemporalType.DATE)
    private Date apprDt1;
    @Column(name = "appr_dt2")
    @Temporal(TemporalType.DATE)
    private Date apprDt2;
    @Column(name = "prt_status")
    private Character prtStatus;
    @Column(name = "remarks")
    private String remarks;

    public Cbchqhmt() {
    }

    public Cbchqhmt(CbchqhmtPK cbchqhmtPK) {
        this.cbchqhmtPK = cbchqhmtPK;
    }

    public Cbchqhmt(CbchqhmtPK cbchqhmtPK, Date runDt, Date modiDt, Date confDt) {
        this.cbchqhmtPK = cbchqhmtPK;
        this.runDt = runDt;
        this.modiDt = modiDt;
        this.confDt = confDt;
    }

    public Cbchqhmt(String chqRun, String deptId, BigDecimal bankAc, String bankCd, String bnkDept) {
        this.cbchqhmtPK = new CbchqhmtPK(chqRun, deptId, bankAc, bankCd, bnkDept);
    }

    public CbchqhmtPK getCbchqhmtPK() {
        return cbchqhmtPK;
    }

    public void setCbchqhmtPK(CbchqhmtPK cbchqhmtPK) {
        this.cbchqhmtPK = cbchqhmtPK;
    }

    public String getDocPf() {
        return docPf;
    }

    public void setDocPf(String docPf) {
        this.docPf = docPf;
    }

    public String getGrpCrt() {
        return grpCrt;
    }

    public void setGrpCrt(String grpCrt) {
        this.grpCrt = grpCrt;
    }

    public BigDecimal getStatus() {
        return status;
    }

    public void setStatus(BigDecimal status) {
        this.status = status;
    }

    public Double getTotAmt() {
        return totAmt;
    }

    public void setTotAmt(Double totAmt) {
        this.totAmt = totAmt;
    }

    public String getRunBy() {
        return runBy;
    }

    public void setRunBy(String runBy) {
        this.runBy = runBy;
    }

    public Date getRunDt() {
        return runDt;
    }

    public void setRunDt(Date runDt) {
        this.runDt = runDt;
    }

    public String getModiBy() {
        return modiBy;
    }

    public void setModiBy(String modiBy) {
        this.modiBy = modiBy;
    }

    public Date getModiDt() {
        return modiDt;
    }

    public void setModiDt(Date modiDt) {
        this.modiDt = modiDt;
    }

    public String getConfBy() {
        return confBy;
    }

    public void setConfBy(String confBy) {
        this.confBy = confBy;
    }

    public Date getConfDt() {
        return confDt;
    }

    public void setConfDt(Date confDt) {
        this.confDt = confDt;
    }

    public String getApprvUid1() {
        return apprvUid1;
    }

    public void setApprvUid1(String apprvUid1) {
        this.apprvUid1 = apprvUid1;
    }

    public String getApprvUid2() {
        return apprvUid2;
    }

    public void setApprvUid2(String apprvUid2) {
        this.apprvUid2 = apprvUid2;
    }

    public String getApprBy1() {
        return apprBy1;
    }

    public void setApprBy1(String apprBy1) {
        this.apprBy1 = apprBy1;
    }

    public String getApprBy2() {
        return apprBy2;
    }

    public void setApprBy2(String apprBy2) {
        this.apprBy2 = apprBy2;
    }

    public String getRejctBy() {
        return rejctBy;
    }

    public void setRejctBy(String rejctBy) {
        this.rejctBy = rejctBy;
    }

    public Date getRejctDt() {
        return rejctDt;
    }

    public void setRejctDt(Date rejctDt) {
        this.rejctDt = rejctDt;
    }

    public Date getSentDt1() {
        return sentDt1;
    }

    public void setSentDt1(Date sentDt1) {
        this.sentDt1 = sentDt1;
    }

    public Date getSentDt2() {
        return sentDt2;
    }

    public void setSentDt2(Date sentDt2) {
        this.sentDt2 = sentDt2;
    }

    public Date getApprDt1() {
        return apprDt1;
    }

    public void setApprDt1(Date apprDt1) {
        this.apprDt1 = apprDt1;
    }

    public Date getApprDt2() {
        return apprDt2;
    }

    public void setApprDt2(Date apprDt2) {
        this.apprDt2 = apprDt2;
    }

    public Character getPrtStatus() {
        return prtStatus;
    }

    public void setPrtStatus(Character prtStatus) {
        this.prtStatus = prtStatus;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cbchqhmtPK != null ? cbchqhmtPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cbchqhmt)) {
            return false;
        }
        Cbchqhmt other = (Cbchqhmt) object;
        if ((this.cbchqhmtPK == null && other.cbchqhmtPK != null) || (this.cbchqhmtPK != null && !this.cbchqhmtPK.equals(other.cbchqhmtPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "estimate.domain.Cbchqhmt[cbchqhmtPK=" + cbchqhmtPK + "]";
    }

}
