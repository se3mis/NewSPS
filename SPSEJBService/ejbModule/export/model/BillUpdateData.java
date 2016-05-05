package export.model;
import java.io.Serializable;
import java.util.Date;




public class BillUpdateData implements Serializable {
	private static final long serialVersionUID = 1L;
	private String projectNo;
	private String deptId;
	
	private Date accCreatedDate;
	private String accountNo;
	private String errorDesc;	
	
	

	public BillUpdateData(String projectNo, String deptId, Date accCreatedDate,
			String accountNo) {
		super();
		this.projectNo = projectNo;
		this.deptId = deptId;
		this.accCreatedDate = accCreatedDate;
		this.accountNo = accountNo;
	}
	
	public BillUpdateData(String projectNo, String deptId, Date accCreatedDate,
			String accountNo, String errorDesc) {
		super();
		this.projectNo = projectNo;
		this.deptId = deptId;
		this.accCreatedDate = accCreatedDate;
		this.accountNo = accountNo;
		this.errorDesc = errorDesc;
	}
	public String getProjectNo() {
		return projectNo;
	}
	
	public void setProjectNo(String projectNo) {
		this.projectNo = projectNo;
	}
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public Date getAccCreatedDate() {
		return accCreatedDate;
	}
	public void setAccCreatedDate(Date accCreatedDate) {
		this.accCreatedDate = accCreatedDate;
	}
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	@Override
	public String toString() {
		return "BillUpdateData [projectNo=" + projectNo + ", deptId=" + deptId
				+ ", accCreatedDate=" + accCreatedDate + ", accountNo="
				+ accountNo + ", errorDesc=" + errorDesc + "]";
	}
	

	public String getErrorDesc() {
		return errorDesc;
	}

	public void setErrorDesc(String errorDesc) {
		this.errorDesc = errorDesc;
	}

}
