package report.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the REPORT_SMC_CATEGORY database table.
 * 
 */
@Entity
@Table(name="REPORT_SPS_CATEGORY")
public class ReportSpsCategory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="CATEGORY_ID")
	private String categoryId;

	@Column(name="CATEGORY_NAME")
	private String categoryName;

	@Column(name="IS_ACTIVE")
	private String isActive;

	@Column(name="ORDER_SQ")
	private String orderSq;

	private String permission;

	public ReportSpsCategory() {
	}

	public String getCategoryId() {
		return this.categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return this.categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
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

	@Override
	public String toString() {
		return "ReportSpsCategory [categoryId=" + categoryId
				+ ", categoryName=" + categoryName + ", isActive=" + isActive
				+ ", orderSq=" + orderSq + ", permission=" + permission + "]";
	}

}