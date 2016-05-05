package job.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the PCWIPH database table.
 * 
 */
@Embeddable
public class PcwiphPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="FUND_ID")
	private String fundId;

	@Column(name="PROJECT_NO")
	private String projectNo;

    public PcwiphPK() {
    }
	public String getFundId() {
		return this.fundId;
	}
	public void setFundId(String fundId) {
		this.fundId = fundId;
	}
	public String getProjectNo() {
		return this.projectNo;
	}
	public void setProjectNo(String projectNo) {
		this.projectNo = projectNo;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PcwiphPK)) {
			return false;
		}
		PcwiphPK castOther = (PcwiphPK)other;
		return 
			this.fundId.equals(castOther.fundId)
			&& this.projectNo.equals(castOther.projectNo);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.fundId.hashCode();
		hash = hash * prime + this.projectNo.hashCode();
		
		return hash;
    }
}