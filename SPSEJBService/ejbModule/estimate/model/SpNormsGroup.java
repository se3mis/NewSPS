package estimate.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the SPCRCONV database table.
 * 
 */
@Entity
public class SpNormsGroup implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="SECTIONTYPEID")
	private String sectionTypeId;

	

	@Column(name="LINEPARENTID")
	private String parentId;
	
	@Column(name="DESCRIPTION")
	private String description;
	

	@Column(name="LINESUMMARYID")
	private String lineSummaryId;

    public SpNormsGroup() {
    }

    

	public String getSectionTypeId() {
		return sectionTypeId;
	}



	public void setSectionTypeId(String sectionTypeId) {
		this.sectionTypeId = sectionTypeId;
	}



	public String getLineSummaryId() {
		return lineSummaryId;
	}



	public void setLineSummaryId(String lineSummaryId) {
		this.lineSummaryId = lineSummaryId;
	}



	public String getParentId() {
		return parentId;
	}



	public void setParentId(String parentId) {
		this.parentId = parentId;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	@Override
	public String toString() {
		return "Norms [id=" + sectionTypeId + ", parentId=" + parentId
				+ ", description=" + description + "]";
	}

}