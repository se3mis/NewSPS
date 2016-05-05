package masters.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the BANK_BRANCH database table.
 * 
 */
@Entity
@Table(name="BANK_BRANCH")
public class BankBranch implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private BankBranchPK id;

	@Column(name="BRANCH_ADDRESS")
	private String branchAddress;

	@Column(name="BRANCH_NAME")
	private String branchName;

    public BankBranch() {
    }

	public BankBranchPK getId() {
		return this.id;
	}

	public void setId(BankBranchPK id) {
		this.id = id;
	}
	
	public String getBranchAddress() {
		return this.branchAddress;
	}

	public void setBranchAddress(String branchAddress) {
		this.branchAddress = branchAddress;
	}

	public String getBranchName() {
		return this.branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	@Override
	public String toString() {
		return "BankBranch [id=" + id + ", branchAddress=" + branchAddress
				+ ", branchName=" + branchName + "]";
	}

}