package job.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the SPAREMAP database table.
 * 
 */
@Entity
public class Sparemap implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="SMC_AREA_CODE")
	private String smcAreaCode;

	@Column(name="AREA_NAME")
	private String areaName;

	@Column(name="BILL_AREA_CODE")
	private String billAreaCode;

	@Column(name="PROVINCE_CODE")
	private String provinceCode;

	@Column(name="REGION_CODE")
	private String regionCode;

    public Sparemap() {
    }

	public String getSmcAreaCode() {
		return this.smcAreaCode;
	}

	public void setSmcAreaCode(String smcAreaCode) {
		this.smcAreaCode = smcAreaCode;
	}

	public String getAreaName() {
		return this.areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getBillAreaCode() {
		return this.billAreaCode;
	}

	public void setBillAreaCode(String billAreaCode) {
		this.billAreaCode = billAreaCode;
	}

	public String getProvinceCode() {
		return this.provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	public String getRegionCode() {
		return this.regionCode;
	}

	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}

	@Override
	public String toString() {
		return "Sparemap [smcAreaCode=" + smcAreaCode + ", areaName="
				+ areaName + ", billAreaCode=" + billAreaCode
				+ ", provinceCode=" + provinceCode + ", regionCode="
				+ regionCode + "]";
	}
	
	

}