package estimate.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the PCRSGRPM database table.
 * 
 */
@Entity
public class Pcrsgrpm implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="RES_CD")
	private String resCd;

	@Column(name="DEPT_ID")
	private String deptId;

	@Column(name="ENT_BY")
	private String entBy;

    @Temporal( TemporalType.DATE)
	@Column(name="ENT_DT")
	private Date entDt;

	@Column(name="GEN_RES")
	private String genRes;

	@Column(name="LOG_ID")
	private BigDecimal logId;

	@Column(name="MOD_BY")
	private String modBy;

    @Temporal( TemporalType.DATE)
	@Column(name="MOD_DT")
	private Date modDt;

	@Column(name="PRENT_DEPT")
	private String prentDept;

	@Column(name="PRENT_LVL")
	private BigDecimal prentLvl;

	@Column(name="PRENT_RES")
	private String prentRes;

	@Column(name="RES_CAT")
	private BigDecimal resCat;

	@Column(name="RES_LVL")
	private BigDecimal resLvl;

	@Column(name="RES_NM")
	private String resNm;

	@Column(name="RES_TYPE")
	private String resType;

	@Column(name="RES_UOM")
	private String resUom;

	private BigDecimal status;

	@Column(name="SUB_CONT")
	private String subCont;

	@Column(name="UNIT_PRICE")
	private BigDecimal unitPrice;

    public Pcrsgrpm() {
    }

	public String getResCd() {
		return this.resCd;
	}

	public void setResCd(String resCd) {
		this.resCd = resCd;
	}

	public String getDeptId() {
		return this.deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
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

	public String getGenRes() {
		return this.genRes;
	}

	public void setGenRes(String genRes) {
		this.genRes = genRes;
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

	public String getPrentDept() {
		return this.prentDept;
	}

	public void setPrentDept(String prentDept) {
		this.prentDept = prentDept;
	}

	public BigDecimal getPrentLvl() {
		return this.prentLvl;
	}

	public void setPrentLvl(BigDecimal prentLvl) {
		this.prentLvl = prentLvl;
	}

	public String getPrentRes() {
		return this.prentRes;
	}

	public void setPrentRes(String prentRes) {
		this.prentRes = prentRes;
	}

	public BigDecimal getResCat() {
		return this.resCat;
	}

	public void setResCat(BigDecimal resCat) {
		this.resCat = resCat;
	}

	public BigDecimal getResLvl() {
		return this.resLvl;
	}

	public void setResLvl(BigDecimal resLvl) {
		this.resLvl = resLvl;
	}

	public String getResNm() {
		return this.resNm;
	}

	public void setResNm(String resNm) {
		this.resNm = resNm;
	}

	public String getResType() {
		return this.resType;
	}

	public void setResType(String resType) {
		this.resType = resType;
	}

	public String getResUom() {
		return this.resUom;
	}

	public void setResUom(String resUom) {
		this.resUom = resUom;
	}

	public BigDecimal getStatus() {
		return this.status;
	}

	public void setStatus(BigDecimal status) {
		this.status = status;
	}

	public String getSubCont() {
		return this.subCont;
	}

	public void setSubCont(String subCont) {
		this.subCont = subCont;
	}

	public BigDecimal getUnitPrice() {
		return this.unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	@Override
	public String toString() {
		return "Pcrsgrpm [resCd=" + resCd + ", deptId=" + deptId + ", entBy="
				+ entBy + ", entDt=" + entDt + ", genRes=" + genRes
				+ ", logId=" + logId + ", modBy=" + modBy + ", modDt=" + modDt
				+ ", prentDept=" + prentDept + ", prentLvl=" + prentLvl
				+ ", prentRes=" + prentRes + ", resCat=" + resCat + ", resLvl="
				+ resLvl + ", resNm=" + resNm + ", resType=" + resType
				+ ", resUom=" + resUom + ", status=" + status + ", subCont="
				+ subCont + ", unitPrice=" + unitPrice + "]";
	}
	
	

}