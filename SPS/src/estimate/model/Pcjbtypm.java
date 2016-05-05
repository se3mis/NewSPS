package estimate.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the PCJBTYPM database table.
 * 
 */
@Entity
public class Pcjbtypm implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PcjbtypmPK id;

	@Column(name="CHRG_GL_CD")
	private String chrgGlCd;

	@Column(name="CHRG_SUB_AC")
	private String chrgSubAc;

	@Column(name="DET_EST_APR")
	private BigDecimal detEstApr;

	@Column(name="ENT_BY")
	private String entBy;

    @Temporal( TemporalType.DATE)
	@Column(name="ENT_DT")
	private Date entDt;

	@Column(name="EST_FRMT_CD")
	private String estFrmtCd;

	@Column(name="EST_NO_APR")
	private BigDecimal estNoApr;

	@Column(name="EST_NO_GEN")
	private BigDecimal estNoGen;

	@Column(name="EST_SRS_CD")
	private BigDecimal estSrsCd;

	@Column(name="GL_CD")
	private String glCd;

	@Column(name="LOG_ID")
	private BigDecimal logId;

	@Column(name="MODI_BY")
	private String modiBy;

    @Temporal( TemporalType.DATE)
	@Column(name="MODI_DT")
	private Date modiDt;

	@Column(name="NORM_UNITS")
	private BigDecimal normUnits;

	@Column(name="PRENT_CAT")
	private String prentCat;

	@Column(name="PRJ_FRMT_CD")
	private String prjFrmtCd;

	@Column(name="PRJ_NO_GEN")
	private BigDecimal prjNoGen;

	@Column(name="PRJ_SRS_CD")
	private BigDecimal prjSrsCd;

	@Column(name="REV_NO_APR")
	private BigDecimal revNoApr;

	private BigDecimal status;

	@Column(name="SUB_AC")
	private String subAc;

	@Column(name="UOM_CD")
	private String uomCd;

    public Pcjbtypm() {
    }

	public PcjbtypmPK getId() {
		return this.id;
	}

	public void setId(PcjbtypmPK id) {
		this.id = id;
	}
	
	public String getChrgGlCd() {
		return this.chrgGlCd;
	}

	public void setChrgGlCd(String chrgGlCd) {
		this.chrgGlCd = chrgGlCd;
	}

	public String getChrgSubAc() {
		return this.chrgSubAc;
	}

	public void setChrgSubAc(String chrgSubAc) {
		this.chrgSubAc = chrgSubAc;
	}

	public BigDecimal getDetEstApr() {
		return this.detEstApr;
	}

	public void setDetEstApr(BigDecimal detEstApr) {
		this.detEstApr = detEstApr;
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

	public String getEstFrmtCd() {
		return this.estFrmtCd;
	}

	public void setEstFrmtCd(String estFrmtCd) {
		this.estFrmtCd = estFrmtCd;
	}

	public BigDecimal getEstNoApr() {
		return this.estNoApr;
	}

	public void setEstNoApr(BigDecimal estNoApr) {
		this.estNoApr = estNoApr;
	}

	public BigDecimal getEstNoGen() {
		return this.estNoGen;
	}

	public void setEstNoGen(BigDecimal estNoGen) {
		this.estNoGen = estNoGen;
	}

	public BigDecimal getEstSrsCd() {
		return this.estSrsCd;
	}

	public void setEstSrsCd(BigDecimal estSrsCd) {
		this.estSrsCd = estSrsCd;
	}

	public String getGlCd() {
		return this.glCd;
	}

	public void setGlCd(String glCd) {
		this.glCd = glCd;
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

	public BigDecimal getNormUnits() {
		return this.normUnits;
	}

	public void setNormUnits(BigDecimal normUnits) {
		this.normUnits = normUnits;
	}

	public String getPrentCat() {
		return this.prentCat;
	}

	public void setPrentCat(String prentCat) {
		this.prentCat = prentCat;
	}

	public String getPrjFrmtCd() {
		return this.prjFrmtCd;
	}

	public void setPrjFrmtCd(String prjFrmtCd) {
		this.prjFrmtCd = prjFrmtCd;
	}

	public BigDecimal getPrjNoGen() {
		return this.prjNoGen;
	}

	public void setPrjNoGen(BigDecimal prjNoGen) {
		this.prjNoGen = prjNoGen;
	}

	public BigDecimal getPrjSrsCd() {
		return this.prjSrsCd;
	}

	public void setPrjSrsCd(BigDecimal prjSrsCd) {
		this.prjSrsCd = prjSrsCd;
	}

	public BigDecimal getRevNoApr() {
		return this.revNoApr;
	}

	public void setRevNoApr(BigDecimal revNoApr) {
		this.revNoApr = revNoApr;
	}

	public BigDecimal getStatus() {
		return this.status;
	}

	public void setStatus(BigDecimal status) {
		this.status = status;
	}

	public String getSubAc() {
		return this.subAc;
	}

	public void setSubAc(String subAc) {
		this.subAc = subAc;
	}

	public String getUomCd() {
		return this.uomCd;
	}

	public void setUomCd(String uomCd) {
		this.uomCd = uomCd;
	}

	@Override
	public String toString() {
		return "Pcjbtypm [id=" + id + ", chrgGlCd=" + chrgGlCd + ", chrgSubAc="
				+ chrgSubAc + ", detEstApr=" + detEstApr + ", entBy=" + entBy
				+ ", entDt=" + entDt + ", estFrmtCd=" + estFrmtCd
				+ ", estNoApr=" + estNoApr + ", estNoGen=" + estNoGen
				+ ", estSrsCd=" + estSrsCd + ", glCd=" + glCd + ", logId="
				+ logId + ", modiBy=" + modiBy + ", modiDt=" + modiDt
				+ ", normUnits=" + normUnits + ", prentCat=" + prentCat
				+ ", prjFrmtCd=" + prjFrmtCd + ", prjNoGen=" + prjNoGen
				+ ", prjSrsCd=" + prjSrsCd + ", revNoApr=" + revNoApr
				+ ", status=" + status + ", subAc=" + subAc + ", uomCd="
				+ uomCd + "]";
	}
	

}