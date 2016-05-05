package util.common;

public class Tariff {
	private String tariffCode;
	private String tariffName;
	
	
	 
	
	public String getTariffCode() {
		return tariffCode;
	}

	public void setTariffCode(String tariffCode) {
		this.tariffCode = tariffCode;
	}

	public String getTariffName() {
		return tariffName;
	}

	public void setTariffName(String tariffName) {
		this.tariffName = tariffName;
	}

	public Tariff(String tarriffName, String tarriffCode)
	{
		this.tariffCode = tarriffCode;
		this.tariffName = tarriffName;
	}
}
