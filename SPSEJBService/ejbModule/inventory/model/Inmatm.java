package inventory.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the INMATM database table.
 * 
 */
@Entity
public class Inmatm implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="MAT_CD")
	private String matCd;

	@Column(name="ABC_CAT")
	private String abcCat;

	@Column(name="CLS_CD")
	private String clsCd;

	@Column(name="COMP_ID")
	private String compId;

	private String controlled;

	@Column(name="CONV_FACT")
	private BigDecimal convFact;

	@Column(name="DEPT_ID")
	private String deptId;

	@Column(name="ENT_BY")
	private String entBy;

    @Temporal( TemporalType.DATE)
	@Column(name="ENT_DT")
	private Date entDt;

	@Column(name="LOG_ID")
	private BigDecimal logId;

	@Column(name="MAJ_UOM")
	private String majUom;

	@Column(name="MAT_IMP")
	private String matImp;

	@Column(name="MAT_NM")
	private String matNm;

	@Column(name="MIN_UOM")
	private String minUom;

	@Column(name="MODI_BY")
	private String modiBy;

    @Temporal( TemporalType.DATE)
	@Column(name="MODI_DT")
	private Date modiDt;

	private String movement;

	@Column(name="ORD_LT_UOM")
	private BigDecimal ordLtUom;

	@Column(name="PRIC_MTHD")
	private BigDecimal pricMthd;

	private BigDecimal status;

	@Column(name="UNIT_PRICE")
	private BigDecimal unitPrice;

	private BigDecimal weight;

    public Inmatm() {
    }

	public String getMatCd() {
		return this.matCd;
	}

	public void setMatCd(String matCd) {
		this.matCd = matCd;
	}

	public String getAbcCat() {
		return this.abcCat;
	}

	public void setAbcCat(String abcCat) {
		this.abcCat = abcCat;
	}

	public String getClsCd() {
		return this.clsCd;
	}

	public void setClsCd(String clsCd) {
		this.clsCd = clsCd;
	}

	public String getCompId() {
		return this.compId;
	}

	public void setCompId(String compId) {
		this.compId = compId;
	}

	public String getControlled() {
		return this.controlled;
	}

	public void setControlled(String controlled) {
		this.controlled = controlled;
	}

	public BigDecimal getConvFact() {
		return this.convFact;
	}

	public void setConvFact(BigDecimal convFact) {
		this.convFact = convFact;
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

	public BigDecimal getLogId() {
		return this.logId;
	}

	public void setLogId(BigDecimal logId) {
		this.logId = logId;
	}

	public String getMajUom() {
		return this.majUom;
	}

	public void setMajUom(String majUom) {
		this.majUom = majUom;
	}

	public String getMatImp() {
		return this.matImp;
	}

	public void setMatImp(String matImp) {
		this.matImp = matImp;
	}

	public String getMatNm() {
		return this.matNm;
	}

	public void setMatNm(String matNm) {
		this.matNm = matNm;
	}

	public String getMinUom() {
		return this.minUom;
	}

	public void setMinUom(String minUom) {
		this.minUom = minUom;
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

	public String getMovement() {
		return this.movement;
	}

	public void setMovement(String movement) {
		this.movement = movement;
	}

	public BigDecimal getOrdLtUom() {
		return this.ordLtUom;
	}

	public void setOrdLtUom(BigDecimal ordLtUom) {
		this.ordLtUom = ordLtUom;
	}

	public BigDecimal getPricMthd() {
		return this.pricMthd;
	}

	public void setPricMthd(BigDecimal pricMthd) {
		this.pricMthd = pricMthd;
	}

	public BigDecimal getStatus() {
		return this.status;
	}

	public void setStatus(BigDecimal status) {
		this.status = status;
	}

	public BigDecimal getUnitPrice() {
		return this.unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public BigDecimal getWeight() {
		return this.weight;
	}

	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}

	@Override
	public String toString() {
		return "Inmatm [matCd=" + matCd + ", abcCat=" + abcCat + ", clsCd="
				+ clsCd + ", compId=" + compId + ", controlled=" + controlled
				+ ", convFact=" + convFact + ", deptId=" + deptId + ", entBy="
				+ entBy + ", entDt=" + entDt + ", logId=" + logId + ", majUom="
				+ majUom + ", matImp=" + matImp + ", matNm=" + matNm
				+ ", minUom=" + minUom + ", modiBy=" + modiBy + ", modiDt="
				+ modiDt + ", movement=" + movement + ", ordLtUom=" + ordLtUom
				+ ", pricMthd=" + pricMthd + ", status=" + status
				+ ", unitPrice=" + unitPrice + ", weight=" + weight + "]";
	}

}