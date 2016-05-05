package estimate.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the Spstdestdmt database table.
 * 
 */
@Embeddable
public class SpstdestdmtPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="APP_NO")
	private String applicationNo;

	@Column(name="STD_NO")
	private String stdNo;
	
	@Column(name="LINE_TYPE")
	private String lineType;
	
    public SpstdestdmtPK() {
    }
	

	public String getApplicationNo() {
		return applicationNo;
	}


	public void setApplicationNo(String applicationNo) {
		this.applicationNo = applicationNo;
	}


	public String getStdNo() {
		return stdNo;
	}


	public void setStdNo(String stdNo) {
		this.stdNo = stdNo;
	}


	public String getLineType() {
		return lineType;
	}


	public void setLineType(String lineType) {
		this.lineType = lineType;
	}


	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof SpstdestdmtPK)) {
			return false;
		}
		SpstdestdmtPK castOther = (SpstdestdmtPK)other;
		return 
			this.applicationNo.equals(castOther.applicationNo)
			&& this.stdNo.equals(castOther.stdNo) && this.lineType.equals(castOther.lineType) ;

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.applicationNo.hashCode();
		hash = hash * prime + this.stdNo.hashCode();
		hash = hash * prime + this.lineType.hashCode();
		return hash;
    }
	@Override
	public String toString() {
		return "SpstdestdmtPK [applicationNo=" + applicationNo + ", stdNo=" + stdNo + ", lineType="+lineType+"]";
	}
}