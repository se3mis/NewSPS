package fa.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the FAMANCAT database table.
 * 
 */
@Entity
public class Famancat implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="CAT_CODE")
	private String catCode;

	private String description;

	private String status;

    public Famancat() {
    }

	public String getCatCode() {
		return this.catCode;
	}

	public void setCatCode(String catCode) {
		this.catCode = catCode;
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
		return "Famancat [catCode=" + catCode + ", description=" + description
				+ ", status=" + status + "]";
	}

}