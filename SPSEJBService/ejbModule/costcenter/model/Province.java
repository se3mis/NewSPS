package costcenter.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Province implements Serializable{
	private String ProvinceCode;
	private String ProvinceName;
	
	public Province() {
		super();
	}

	public Province(String provinceCode, String provinceName) {
		super();
		ProvinceCode = provinceCode;
		ProvinceName = provinceName;
	}

	public String getProvinceCode() {
		return ProvinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		ProvinceCode = provinceCode;
	}

	public String getProvinceName() {
		return ProvinceName;
	}

	public void setProvinceName(String provinceName) {
		ProvinceName = provinceName;
	}

	@Override
	public String toString() {
		return "Province [ProvinceCode=" + ProvinceCode + ", ProvinceName="
				+ ProvinceName + "]";
	}

}
