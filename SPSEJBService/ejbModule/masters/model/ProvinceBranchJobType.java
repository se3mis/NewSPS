package masters.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the BANK database table.
 * 
 */
@Entity
@Table(name="PROVINCE_BRANCH_JOBTYPE")
public class ProvinceBranchJobType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	
	private ProvinceBranchJobTypePK id;

	@Column(name="JOBTYPE")
	private String jobType;
	
	@Column(name="ESTIMATE_TYPE")
	private String estimateType;
	
    public ProvinceBranchJobType() {
    }

	


	public ProvinceBranchJobTypePK getId() {
		return id;
	}




	public void setId(ProvinceBranchJobTypePK id) {
		this.id = id;
	}




	public String getJobType() {
		return jobType;
	}




	public void setJobType(String jobType) {
		this.jobType = jobType;
	}




	public String getEstimateType() {
		return estimateType;
	}




	public void setEstimateType(String estimateType) {
		this.estimateType = estimateType;
	}




	@Override
	public String toString() {
		return "Bank [id=" + id + ", jobType=" + jobType + "]";
	}

}