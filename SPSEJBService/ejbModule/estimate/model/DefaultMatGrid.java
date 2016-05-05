package estimate.model;

import java.io.Serializable;
import java.math.BigDecimal;

@SuppressWarnings("serial")
public class DefaultMatGrid implements Serializable {
	private String matCd;
	private String matNm;
	private String uom;
	private BigDecimal matQty;
	private BigDecimal unitPrice;
	private BigDecimal cost;
	
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
	public String getUom() {
		return uom;
	}
	public void setUom(String uom) {
		this.uom = uom;
	}
	public BigDecimal getMatQty() {
		return matQty;
	}
	public void setMatQty(BigDecimal matQty) {
		this.matQty = matQty;
	}
	public BigDecimal getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}
	public BigDecimal getCost() {
		return cost;
	}
	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}
	@Override
	public String toString() {
		return "DefaultMatList [matCd=" + matCd + ", matNm=" + matNm + ", uom="
				+ uom + ", matQty=" + matQty + ", unitPrice=" + unitPrice
				+ ", cost=" + cost + "]";
	}
	


}
