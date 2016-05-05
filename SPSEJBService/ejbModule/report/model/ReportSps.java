package report.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the REPORT_SMC database table.
 * 
 */
@Entity
@Table(name="REPORT_SPS")
public class ReportSps implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="REPORT_ID")
	private String reportId;

	@Column(name="CATEGORY_ID")
	private String categoryId;

	@Column(name="IS_ACTIVE")
	private String isActive;

	@Column(name="ORDER_SQ")
	private String orderSq;

	private String permission;

	@Column(name="REPORT_NAME")
	private String reportName;

	public ReportSps() {
	}

	public String getReportId() {
		return this.reportId;
	}

	public void setReportId(String reportId) {
		this.reportId = reportId;
	}

	public String getCategoryId() {
		return this.categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getIsActive() {
		return this.isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public String getOrderSq() {
		return this.orderSq;
	}

	public void setOrderSq(String orderSq) {
		this.orderSq = orderSq;
	}

	public String getPermission() {
		return this.permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public String getReportName() {
		return this.reportName;
	}

	public void setReportName(String reportName) {
		this.reportName = reportName;
	}

	@Override
	public String toString() {
		return "ReportSps [reportId=" + reportId + ", categoryId=" + categoryId
				+ ", isActive=" + isActive + ", orderSq=" + orderSq
				+ ", permission=" + permission + ", reportName=" + reportName
				+ "]";
	}

}