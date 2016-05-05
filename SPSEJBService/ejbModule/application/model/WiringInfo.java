package application.model;

import java.io.Serializable;
import java.math.BigDecimal;

@SuppressWarnings("serial")
public class WiringInfo implements Serializable{
	private BigDecimal connectionType;
	private BigDecimal phase;
	private String customerType;
	private String customerCategory;
	private String tariffCatCode;
	private String tariffCode;
	
	
	public WiringInfo() {
		super();
	}


	public WiringInfo(BigDecimal connectionType, BigDecimal phase,
			String customerType, String customerCategory, String tariffCatCode,
			String tariffCode) {
		super();
		this.connectionType = connectionType;
		this.phase = phase;
		this.customerType = customerType;
		this.customerCategory = customerCategory;
		this.tariffCatCode = tariffCatCode;
		this.tariffCode = tariffCode;
	}
	
	public WiringInfo(BigDecimal connectionType, BigDecimal phase,
			String customerCategory, String tariffCatCode,
			String tariffCode) {
		super();
		this.connectionType = connectionType;
		this.phase = phase;
		this.customerCategory = customerCategory;
		this.tariffCatCode = tariffCatCode;
		this.tariffCode = tariffCode;
	}


	public BigDecimal getConnectionType() {
		return connectionType;
	}


	public void setConnectionType(BigDecimal connectionType) {
		this.connectionType = connectionType;
	}


	public BigDecimal getPhase() {
		return phase;
	}


	public void setPhase(BigDecimal phase) {
		this.phase = phase;
	}


	public String getCustomerType() {
		return customerType;
	}


	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}


	public String getCustomerCategory() {
		return customerCategory;
	}


	public void setCustomerCategory(String customerCategory) {
		this.customerCategory = customerCategory;
	}


	public String getTariffCatCode() {
		return tariffCatCode;
	}


	public void setTariffCatCode(String tariffCatCode) {
		this.tariffCatCode = tariffCatCode;
	}


	public String getTariffCode() {
		return tariffCode;
	}


	public void setTariffCode(String tariffCode) {
		this.tariffCode = tariffCode;
	}


	@Override
	public String toString() {
		return "WiringInfo [connectionType=" + connectionType + ", phase="
				+ phase + ", customerType=" + customerType
				+ ", customerCategory=" + customerCategory + ", tariffCatCode="
				+ tariffCatCode + ", tariffCode=" + tariffCode + "]";
	}
	
	
	

}
