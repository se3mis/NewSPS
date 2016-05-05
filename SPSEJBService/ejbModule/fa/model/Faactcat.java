package fa.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the FAACTCAT database table.
 * 
 */
@Entity
public class Faactcat implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="FA_CAT_CODE")
	private String faCatCode;

	@Column(name="ACCT_CODE")
	private String acctCode;

	@Column(name="CAT_CODE")
	private String catCode;

	private String description;

	private String status;

	@Column(name="SUB_CAT_CODE")
	private String subCatCode;

    public Faactcat() {
    }

	public String getFaCatCode() {
		return this.faCatCode;
	}

	public void setFaCatCode(String faCatCode) {
		this.faCatCode = faCatCode;
	}

	public String getAcctCode() {
		return this.acctCode;
	}

	public void setAcctCode(String acctCode) {
		this.acctCode = acctCode;
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

	public String getSubCatCode() {
		return this.subCatCode;
	}

	public void setSubCatCode(String subCatCode) {
		this.subCatCode = subCatCode;
	}

	@Override
	public String toString() {
		return "Faactcat [faCatCode=" + faCatCode + ", acctCode=" + acctCode
				+ ", catCode=" + catCode + ", description=" + description
				+ ", status=" + status + ", subCatCode=" + subCatCode + "]";
	}

}