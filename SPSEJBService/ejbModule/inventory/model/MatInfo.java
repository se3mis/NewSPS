package inventory.model;

import java.io.Serializable;
import java.math.BigDecimal;

@SuppressWarnings("serial")
public class MatInfo  implements Serializable{
	private String matCd;
	private String matNm;
	private BigDecimal unitPrice;
	private String both;
	
	
	public MatInfo() {
		super();
	}
	
	
	public MatInfo(String matCd, String matNm, BigDecimal unitPrice) {
		super();
		this.matCd = matCd;
		this.matNm = matNm;
		this.both=matCd+"-"+matNm;
		this.unitPrice = unitPrice;
	}


	public MatInfo(String matCd, String matNm) {
		super();
		this.matCd = matCd;
		this.matNm = matNm;
		this.both=matCd+"-"+matNm;
	}
	public String getMatCd() {
		return matCd;
	}
	public void setMatCd(String matCd) {
		this.matCd = matCd;
	}
	public String getMatNm() {
		return matNm;
	}
	public void setMatNm(String matNm) {
		this.matNm = matNm;
	}
	public String getBoth() {
		return both;
	}
	public void setBoth(String both) {
		this.both = both;
	}
	
	public BigDecimal getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}
	@Override
	public String toString() {
		return "MatInfo [matCd=" + matCd + ", matNm=" + matNm + ", unitPrice="
				+ unitPrice + ", both=" + both + "]";
	}
	
	

}
