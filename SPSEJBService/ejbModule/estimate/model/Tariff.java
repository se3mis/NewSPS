package estimate.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the TARIFF database table.
 * 
 */
@Entity
public class Tariff implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="TARIFF_CODE")
	private String tariffCode;

	@Column(name="TARIFF_CAT_CODE")
	private String tariffCatCode;

	@Column(name="TARIFF_NAME")
	private String tariffName;

    public Tariff() {
    }

	public String getTariffCode() {
		return this.tariffCode;
	}

	public void setTariffCode(String tariffCode) {
		this.tariffCode = tariffCode;
	}

	public String getTariffCatCode() {
		return this.tariffCatCode;
	}

	public void setTariffCatCode(String tariffCatCode) {
		this.tariffCatCode = tariffCatCode;
	}

	public String getTariffName() {
		return this.tariffName;
	}

	public void setTariffName(String tariffName) {
		this.tariffName = tariffName;
	}

	@Override
	public String toString() {
		return "Tariff [tariffCode=" + tariffCode + ", tariffCatCode="
				+ tariffCatCode + ", tariffName=" + tariffName + "]";
	}
	

}