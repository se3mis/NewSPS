package estimate.model;

import java.io.Serializable;
import java.math.BigDecimal;

@SuppressWarnings("serial")
public class MaterialPriceInfo implements Serializable {
	private String resType;
	private BigDecimal resCat;
	private String matCd;
	private String matNm;
	private String uom;
	private BigDecimal unitPrice;
	private BigDecimal tolerance;
	public String getResType() {
		return resType;
	}
	public void setResType(String resType) {
		this.resType = resType;
	}
	
	public BigDecimal getResCat() {
		return resCat;
	}
	public void setResCat(BigDecimal resCat) {
		this.resCat = resCat;
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
	public String getUom() {
		return uom;
	}
	public void setUom(String uom) {
		this.uom = uom;
	}
	public BigDecimal getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}
	public BigDecimal getTolerance() {
		return tolerance;
	}
	public void setTolerance(BigDecimal tolerance) {
		this.tolerance = tolerance;
	}
	@Override
	public String toString() {
		return "MaterialPriceInfo [resType=" + resType + ", resCat=" + resCat
				+ ", matCd=" + matCd + ", matNm=" + matNm + ", uom=" + uom
				+ ", unitPrice=" + unitPrice + ", tolerance=" + tolerance + "]";
	}
	
	
	


}
