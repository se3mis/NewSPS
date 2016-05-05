package masters.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the BANK database table.
 * 
 */
@Entity
public class Bank implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="BANK_CODE")
	private String bankCode;

	@Column(name="BANK_NAME")
	private String bankName;

    public Bank() {
    }

	public String getBankCode() {
		return this.bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getBankName() {
		return this.bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

}