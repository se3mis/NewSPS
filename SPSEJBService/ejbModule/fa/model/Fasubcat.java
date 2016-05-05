package fa.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the FASUBCAT database table.
 * 
 */
@Entity
public class Fasubcat implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="SUB_CAT_CODE")
	private String subCatCode;

	private String description;

	private String status;

    public Fasubcat() {
    }

	public String getSubCatCode() {
		return this.subCatCode;
	}

	public void setSubCatCode(String subCatCode) {
		this.subCatCode = subCatCode;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Fasubcat [subCatCode=" + subCatCode + ", description="
				+ description + ", status=" + status + "]";
	}

}