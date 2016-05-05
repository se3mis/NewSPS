package masters.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the BANK database table.
 * 
 */
@Entity
@Table(name="PROVINCE_BRANCH")
public class ProvinceBranch implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	
	private ProvinceBranchPK id;

	@Column(name="BRANCH_NAME")
	private String branchName;
	
	
	@Column(name="PARENT_ID")
	private String parentId;

	@Column(name="COMMERCIAL_ID")
	private String commercialId;
	
	

    public ProvinceBranch() {
    }

	

	





	public ProvinceBranchPK getId() {
		return id;
	}









	public void setId(ProvinceBranchPK id) {
		this.id = id;
	}









	public String getCommercialId() {
		return commercialId;
	}









	public void setCommercialId(String commercialId) {
		this.commercialId = commercialId;
	}









	public String getBranchName() {
		return branchName;
	}



	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}



	public String getParentId() {
		return parentId;
	}



	public void setParentId(String parentId) {
		this.parentId = parentId;
	}



	@Override
	public String toString() {
		return "Bank [id=" + id + ", branchName=" + branchName + "]";
	}

}