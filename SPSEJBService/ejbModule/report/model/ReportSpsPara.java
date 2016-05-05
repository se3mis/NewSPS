package report.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the REPORT_SMC_PARA database table.
 * 
 */
@Entity
@Table(name="REPORT_SPS_PARA")
public class ReportSpsPara implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ReportSpsParaPK id;

	@Column(name="SELECTED_LIST")
	private String selectedList;

	public ReportSpsPara() {
	}

	public ReportSpsParaPK getId() {
		return this.id;
	}

	public void setId(ReportSpsParaPK id) {
		this.id = id;
	}

	public String getSelectedList() {
		return this.selectedList;
	}

	public void setSelectedList(String selectedList) {
		this.selectedList = selectedList;
	}

	@Override
	public String toString() {
		return "ReportSpsPara [id=" + id + ", selectedList=" + selectedList
				+ "]";
	}

}