package report.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the REPORT_SMC_PARA database table.
 * 
 */
@Embeddable
public class ReportSpsParaPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="PARA_ID")
	private String paraId;

	@Column(name="REPORT_ID")
	private String reportId;

	public ReportSpsParaPK() {
	}
	public String getParaId() {
		return this.paraId;
	}
	public void setParaId(String paraId) {
		this.paraId = paraId;
	}
	public String getReportId() {
		return this.reportId;
	}
	public void setReportId(String reportId) {
		this.reportId = reportId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ReportSpsParaPK)) {
			return false;
		}
		ReportSpsParaPK castOther = (ReportSpsParaPK)other;
		return 
			this.paraId.equals(castOther.paraId)
			&& this.reportId.equals(castOther.reportId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.paraId.hashCode();
		hash = hash * prime + this.reportId.hashCode();
		
		return hash;
	}
	@Override
	public String toString() {
		return "ReportSpsParaPK [paraId=" + paraId + ", reportId=" + reportId
				+ "]";
	}
}