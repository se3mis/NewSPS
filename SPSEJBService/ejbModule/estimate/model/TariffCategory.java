package estimate.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the TARIFF_CATEGORY database table.
 * 
 */
@Entity
@Table(name="TARIFF_CATEGORY")
public class TariffCategory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="TARIFF_CAT_CODE")
	private String tariffCatCode;

	@Column(name="TARIFF_CAT_NAME")
	private String tariffCatName;

    public TariffCategory() {
    }

	public String getTariffCatCode() {
		return this.tariffCatCode;
	}

	public void setTariffCatCode(String tariffCatCode) {
		this.tariffCatCode = tariffCatCode;
	}

	public String getTariffCatName() {
		return this.tariffCatName;
	}

	public void setTariffCatName(String tariffCatName) {
		this.tariffCatName = tariffCatName;
	}

	@Override
	public String toString() {
		return "TariffCategory [tariffCatCode=" + tariffCatCode
				+ ", tariffCatName=" + tariffCatName + "]";
	}
	

}