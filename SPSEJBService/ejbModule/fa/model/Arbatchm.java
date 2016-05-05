package fa.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the ARBATCHM database table.
 * 
 */
@Entity
public class Arbatchm implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ArbatchmPK id;

	@Column(name="CONF_BY")
	private String confBy;

    @Temporal( TemporalType.DATE)
	@Column(name="CONF_DT")
	private Date confDt;

	@Column(name="DOC_VALUE")
	private BigDecimal docValue;

	@Column(name="ENT_BY")
	private String entBy;

	@Column(name="ENT_DOC_VAL")
	private BigDecimal entDocVal;

    @Temporal( TemporalType.DATE)
	@Column(name="ENT_DT")
	private Date entDt;

	@Column(name="ENT_NOF_DOC")
	private BigDecimal entNofDoc;

	@Column(name="MODI_BY")
	private String modiBy;

    @Temporal( TemporalType.DATE)
	@Column(name="MODI_DT")
	private Date modiDt;

	private String modify;

	@Column(name="MTH_IND")
	private BigDecimal mthInd;

	@Column(name="NOF_DOCS")
	private BigDecimal nofDocs;

	@Column(name="POST_BY")
	private String postBy;

    @Temporal( TemporalType.DATE)
	@Column(name="POST_DT")
	private Date postDt;

	private BigDecimal status;

	@Column(name="TRF_BY")
	private String trfBy;

    @Temporal( TemporalType.DATE)
	@Column(name="TRF_DT")
	private Date trfDt;

	@Column(name="TRX_TYPE")
	private String trxType;

	@Column(name="YR_IND")
	private BigDecimal yrInd;

    public Arbatchm() {
    }

	public ArbatchmPK getId() {
		return this.id;
	}

	public void setId(ArbatchmPK id) {
		this.id = id;
	}
	
	public String getConfBy() {
		return this.confBy;
	}

	public void setConfBy(String confBy) {
		this.confBy = confBy;
	}

	public Date getConfDt() {
		return this.confDt;
	}

	public void setConfDt(Date confDt) {
		this.confDt = confDt;
	}

	public BigDecimal getDocValue() {
		return this.docValue;
	}

	public void setDocValue(BigDecimal docValue) {
		this.docValue = docValue;
	}

	public String getEntBy() {
		return this.entBy;
	}

	public void setEntBy(String entBy) {
		this.entBy = entBy;
	}

	public BigDecimal getEntDocVal() {
		return this.entDocVal;
	}

	public void setEntDocVal(BigDecimal entDocVal) {
		this.entDocVal = entDocVal;
	}

	public Date getEntDt() {
		return this.entDt;
	}

	public void setEntDt(Date entDt) {
		this.entDt = entDt;
	}

	public BigDecimal getEntNofDoc() {
		return this.entNofDoc;
	}

	public void setEntNofDoc(BigDecimal entNofDoc) {
		this.entNofDoc = entNofDoc;
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

	public String getModify() {
		return this.modify;
	}

	public void setModify(String modify) {
		this.modify = modify;
	}

	public BigDecimal getMthInd() {
		return this.mthInd;
	}

	public void setMthInd(BigDecimal mthInd) {
		this.mthInd = mthInd;
	}

	public BigDecimal getNofDocs() {
		return this.nofDocs;
	}

	public void setNofDocs(BigDecimal nofDocs) {
		this.nofDocs = nofDocs;
	}

	public String getPostBy() {
		return this.postBy;
	}

	public void setPostBy(String postBy) {
		this.postBy = postBy;
	}

	public Date getPostDt() {
		return this.postDt;
	}

	public void setPostDt(Date postDt) {
		this.postDt = postDt;
	}

	public BigDecimal getStatus() {
		return this.status;
	}

	public void setStatus(BigDecimal status) {
		this.status = status;
	}

	public String getTrfBy() {
		return this.trfBy;
	}

	public void setTrfBy(String trfBy) {
		this.trfBy = trfBy;
	}

	public Date getTrfDt() {
		return this.trfDt;
	}

	public void setTrfDt(Date trfDt) {
		this.trfDt = trfDt;
	}

	public String getTrxType() {
		return this.trxType;
	}

	public void setTrxType(String trxType) {
		this.trxType = trxType;
	}

	public BigDecimal getYrInd() {
		return this.yrInd;
	}

	public void setYrInd(BigDecimal yrInd) {
		this.yrInd = yrInd;
	}

	@Override
	public String toString() {
		return "Arbatchm [id=" + id + ", confBy=" + confBy + ", confDt="
				+ confDt + ", docValue=" + docValue + ", entBy=" + entBy
				+ ", entDocVal=" + entDocVal + ", entDt=" + entDt
				+ ", entNofDoc=" + entNofDoc + ", modiBy=" + modiBy
				+ ", modiDt=" + modiDt + ", modify=" + modify + ", mthInd="
				+ mthInd + ", nofDocs=" + nofDocs + ", postBy=" + postBy
				+ ", postDt=" + postDt + ", status=" + status + ", trfBy="
				+ trfBy + ", trfDt=" + trfDt + ", trxType=" + trxType
				+ ", yrInd=" + yrInd + "]";
	}

}