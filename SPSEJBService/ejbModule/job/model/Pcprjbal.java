package job.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the PCPRJBAL database table.
 * 
 */
@Entity
public class Pcprjbal implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PcprjbalPK id;

	private BigDecimal addition;

	@Column(name="CAT_CD")
	private String catCd;

	@Column(name="CL_BAL")
	private BigDecimal clBal;

	private BigDecimal deduction;

	@Column(name="OP_BAL")
	private BigDecimal opBal;

	private BigDecimal payment;

	@Column(name="PROJECT_NO")
	private String projectNo;

	@Column(name="RES_CAT")
	private BigDecimal resCat;

	@Column(name="REV_NO")
	private BigDecimal revNo;

    public Pcprjbal() {
    }

	public PcprjbalPK getId() {
		return this.id;
	}

	public void setId(PcprjbalPK id) {
		this.id = id;
	}
	
	public BigDecimal getAddition() {
		return this.addition;
	}

	public void setAddition(BigDecimal addition) {
		this.addition = addition;
	}

	public String getCatCd() {
		return this.catCd;
	}

	public void setCatCd(String catCd) {
		this.catCd = catCd;
	}

	public BigDecimal getClBal() {
		return this.clBal;
	}

	public void setClBal(BigDecimal clBal) {
		this.clBal = clBal;
	}

	public BigDecimal getDeduction() {
		return this.deduction;
	}

	public void setDeduction(BigDecimal deduction) {
		this.deduction = deduction;
	}

	public BigDecimal getOpBal() {
		return this.opBal;
	}

	public void setOpBal(BigDecimal opBal) {
		this.opBal = opBal;
	}

	public BigDecimal getPayment() {
		return this.payment;
	}

	public void setPayment(BigDecimal payment) {
		this.payment = payment;
	}

	public String getProjectNo() {
		return this.projectNo;
	}

	public void setProjectNo(String projectNo) {
		this.projectNo = projectNo;
	}

	public BigDecimal getResCat() {
		return this.resCat;
	}

	public void setResCat(BigDecimal resCat) {
		this.resCat = resCat;
	}

	public BigDecimal getRevNo() {
		return this.revNo;
	}

	public void setRevNo(BigDecimal revNo) {
		this.revNo = revNo;
	}

	@Override
	public String toString() {
		return "Pcprjbal [id=" + id + ", addition=" + addition + ", catCd="
				+ catCd + ", clBal=" + clBal + ", deduction=" + deduction
				+ ", opBal=" + opBal + ", payment=" + payment + ", projectNo="
				+ projectNo + ", resCat=" + resCat + ", revNo=" + revNo + "]";
	}
	
	

}