package job.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the BILL_DETAIL database table.
 * 
 */
@Entity
@Table(name="BILL_DETAIL")
public class BillDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private BillDetailPK id;

    @Temporal( TemporalType.DATE)
	@Column(name="ADD_DATE")
	private Date addDate;

	@Column(name="ADD_TIME")
	private String addTime;

	@Column(name="ADD_USER")
	private String addUser;

	@Column(name="BILL_NO")
	private String billNo;

	@Column(name="CONTRACTOR_ID")
	private String contractorId;

    @Temporal( TemporalType.DATE)
	@Column(name="UPD_DATE")
	private Date updDate;

	@Column(name="UPD_TIME")
	private String updTime;

	@Column(name="UPD_USER")
	private String updUser;

    public BillDetail() {
    }

	public BillDetailPK getId() {
		return this.id;
	}

	public void setId(BillDetailPK id) {
		this.id = id;
	}
	
	public Date getAddDate() {
		return this.addDate;
	}

	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}

	public String getAddTime() {
		return this.addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

	public String getAddUser() {
		return this.addUser;
	}

	public void setAddUser(String addUser) {
		this.addUser = addUser;
	}

	public String getBillNo() {
		return this.billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}

	public String getContractorId() {
		return this.contractorId;
	}

	public void setContractorId(String contractorId) {
		this.contractorId = contractorId;
	}

	public Date getUpdDate() {
		return this.updDate;
	}

	public void setUpdDate(Date updDate) {
		this.updDate = updDate;
	}

	public String getUpdTime() {
		return this.updTime;
	}

	public void setUpdTime(String updTime) {
		this.updTime = updTime;
	}

	public String getUpdUser() {
		return this.updUser;
	}

	public void setUpdUser(String updUser) {
		this.updUser = updUser;
	}

}