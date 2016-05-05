package fa.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the FAPLNTEQ database table.
 * 
 */
@Entity
public class Faplnteq implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private FaplnteqPK id;

	@Column(name="APPROVE_DATE")
	private Timestamp approveDate;

	@Column(name="APPROVE_USER")
	private String approveUser;

	@Column(name="ENTER_DATE")
	private Timestamp enterDate;

	@Column(name="ENTER_USER")
	private String enterUser;

	@Column(name="FEATURE_VALUE")
	private String featureValue;

	private String status;

	@Column(name="VALIDATE_DATE")
	private Timestamp validateDate;

	@Column(name="VALIDATE_USER")
	private String validateUser;

    public Faplnteq() {
    }

	public FaplnteqPK getId() {
		return this.id;
	}

	public void setId(FaplnteqPK id) {
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

	public String getFeatureValue() {
		return this.featureValue;
	}

	public void setFeatureValue(String featureValue) {
		this.featureValue = featureValue;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
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
		return "Faplnteq [id=" + id + ", approveDate=" + approveDate
				+ ", approveUser=" + approveUser + ", enterDate=" + enterDate
				+ ", enterUser=" + enterUser + ", featureValue=" + featureValue
				+ ", status=" + status + ", validateDate=" + validateDate
				+ ", validateUser=" + validateUser + "]";
	}

}