package fa.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.math.BigDecimal;


/**
 * The persistent class for the FAHEADER database table.
 * 
 */
@Entity
public class Faheader implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private FaheaderPK id;

	@Column(name="APPROVE_DATE")
	private Timestamp approveDate;

	@Column(name="APPROVE_USER")
	private String approveUser;

	@Column(name="DOC_DATE")
	private Timestamp docDate;

	@Column(name="DOC_NO")
	private String docNo;

	@Column(name="DOC_PF")
	private String docPf;

	@Column(name="ENTER_DATE")
	private Timestamp enterDate;

	@Column(name="ENTER_USER")
	private String enterUser;

	private String status;

	@Column(name="TOTAL_VALUE")
	private BigDecimal totalValue;

	@Column(name="VALIDATE_DATE")
	private Timestamp validateDate;

	@Column(name="VALIDATE_USER")
	private String validateUser;

    public Faheader() {
    }

	public FaheaderPK getId() {
		return this.id;
	}

	public void setId(FaheaderPK id) {
		this.id = id;
	}
	
	public Timestamp getApproveDate() {
		return this.approveDate;
	}

	public void setApproveDate(Timestamp approveDate) {
		this.approveDate = approveDate;
	}

	public String getApproveUser() {
		return this.approveUser;
	}

	public void setApproveUser(String approveUser) {
		this.approveUser = approveUser;
	}

	public Timestamp getDocDate() {
		return this.docDate;
	}

	public void setDocDate(Timestamp docDate) {
		this.docDate = docDate;
	}

	public String getDocNo() {
		return this.docNo;
	}

	public void setDocNo(String docNo) {
		this.docNo = docNo;
	}

	public String getDocPf() {
		return this.docPf;
	}

	public void setDocPf(String docPf) {
		this.docPf = docPf;
	}

	public Timestamp getEnterDate() {
		return this.enterDate;
	}

	public void setEnterDate(Timestamp enterDate) {
		this.enterDate = enterDate;
	}

	public String getEnterUser() {
		return this.enterUser;
	}

	public void setEnterUser(String enterUser) {
		this.enterUser = enterUser;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BigDecimal getTotalValue() {
		return this.totalValue;
	}

	public void setTotalValue(BigDecimal totalValue) {
		this.totalValue = totalValue;
	}

	public Timestamp getValidateDate() {
		return this.validateDate;
	}

	public void setValidateDate(Timestamp validateDate) {
		this.validateDate = validateDate;
	}

	public String getValidateUser() {
		return this.validateUser;
	}

	public void setValidateUser(String validateUser) {
		this.validateUser = validateUser;
	}

	@Override
	public String toString() {
		return "Faheader [id=" + id + ", approveDate=" + approveDate
				+ ", approveUser=" + approveUser + ", docDate=" + docDate
				+ ", docNo=" + docNo + ", docPf=" + docPf + ", enterDate="
				+ enterDate + ", enterUser=" + enterUser + ", status=" + status
				+ ", totalValue=" + totalValue + ", validateDate="
				+ validateDate + ", validateUser=" + validateUser + "]";
	}

}