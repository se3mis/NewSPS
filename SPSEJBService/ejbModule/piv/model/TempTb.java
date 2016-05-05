package piv.model;

import java.io.Serializable;
import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the PIV_DETAIL database table.
 * 
 */
@Entity
@Table(name="TempTb")
public class TempTb implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ESTIMATE_NO")
	private String estimateNo;

	@Column(name="STATUS")
	private String status;
	
    @Temporal( TemporalType.DATE)
	@Column(name="PIV_DATE")
	private Date pivDate;

	@Column(name="PIV_AMT")
	private BigDecimal pivAmount;

	@Column(name="PIV_NO")
	private String pivNo;

	@Column(name="DEPT_ID")
	private String deptId;



    public TempTb() {
    }

	
	public String getEstimateNo() {
		return estimateNo;
	}


	public void setEstimateNo(String estimateNo) {
		this.estimateNo = estimateNo;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	

	public Date getPivDate() {
		return pivDate;
	}


	public void setPivDate(Date pivDate) {
		this.pivDate = pivDate;
	}


	public BigDecimal getPivAmount() {
		return pivAmount;
	}


	public void setPivAmount(BigDecimal pivAmount) {
		this.pivAmount = pivAmount;
	}


	public String getPivNo() {
		return pivNo;
	}


	public void setPivNo(String pivNo) {
		this.pivNo = pivNo;
	}


	public String getDeptId() {
		return deptId;
	}


	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}




	@Override
	public String toString() {
		return "tempDb [estimateNo=" + estimateNo + ", status=" + status + ", pivDate="
				+ pivDate + ", pivAmount=" + pivAmount + ", pivNo="
				+ pivNo + "]";
	}

}