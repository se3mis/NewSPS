package fa.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.MathContext;

@SuppressWarnings("serial")
public class FAMaterialGrid  implements Serializable{
	private String resType;
	private String resCd;
	private BigDecimal resCat;
	private String matNm;
	private String uom;
	private BigDecimal tolerance;
	private BigDecimal unitPrice;
	private BigDecimal realUnitPrice;
	private BigDecimal measurementFactor;
	private BigDecimal extraQty;
	private BigDecimal estimateQty;
	private BigDecimal estimateCost;
	private BigDecimal customerQty;
	private BigDecimal customerCost;
	private BigDecimal damageQty;
	private BigDecimal damageCost;
	private BigDecimal mntQty;
	private BigDecimal mntCost;
	private BigDecimal commitedQty;
	private BigDecimal commitedCost;
	private String faManCatCode;
	private String faManCatDesc;
	private String faSubCatCode;
	private String faSubCatDesc;
	
	
	//MathContext mc = new MathContext(2);
	
	public FAMaterialGrid() {
		super();
	}
	//Full Conscrutor
	public FAMaterialGrid(String resType, String resCd, BigDecimal resCat,
			String matNm, String uom, BigDecimal tolerance,
			BigDecimal unitPrice, BigDecimal estimateQty,
			BigDecimal estimateCost, BigDecimal customerQty,
			BigDecimal damageQty, BigDecimal mntQty, BigDecimal commitedQty,
			String faManCatCode, String faManCatDesc,
			String faSubCatCode, String faSubCatDesc) {
		super();
		this.resType = resType;
		this.resCd = resCd;
		this.resCat = resCat;
		this.matNm = matNm;
		this.uom = uom;
		this.tolerance = tolerance;
		this.unitPrice = unitPrice;
		this.estimateQty = estimateQty;
		this.estimateCost = estimateCost;
		this.customerQty = customerQty;
		
		if (customerQty!=null){	
			this.customerCost =new BigDecimal(Double.parseDouble(customerQty.toString())* Double.parseDouble(unitPrice.toString()));
		}else
			this.customerCost=new BigDecimal(0);
		this.damageQty = damageQty;
		if (damageQty!=null){	
			this.damageCost =new BigDecimal(Double.parseDouble(damageQty.toString())* Double.parseDouble(unitPrice.toString()));
		}else {
			this.damageCost=new BigDecimal(0);
		}
		this.mntQty = mntQty;
		if (mntQty!=null){	
			this.mntCost =new BigDecimal(Double.parseDouble(mntQty.toString())* Double.parseDouble(unitPrice.toString()));
		}else {
			this.mntCost=new BigDecimal(0);
		}
		this.commitedQty = commitedQty;
		if (commitedQty!=null){	
			this.commitedCost =new BigDecimal(Double.parseDouble(commitedQty.toString())* Double.parseDouble(unitPrice.toString()));
		}else {
			this.commitedCost=new BigDecimal(0);
		}
		this.faManCatCode = faManCatCode;
		this.faManCatDesc = faManCatDesc;
		this.faSubCatCode = faSubCatCode;
		this.faSubCatDesc = faSubCatDesc;
		//this.damageCost =new BigDecimal(Double.parseDouble(damageCost.toString())* Double.parseDouble(unitPrice.toString()));
		//setData(customerQty, damageQty);
	}
	
	public FAMaterialGrid(String resType, String resCd, BigDecimal resCat,
			String matNm, String uom, BigDecimal tolerance,
			BigDecimal unitPrice, BigDecimal estimateQty,
			BigDecimal estimateCost, BigDecimal customerQty,
			BigDecimal damageQty) {
		super();
		this.resType = resType;
		this.resCd = resCd;
		this.resCat = resCat;
		this.matNm = matNm;
		this.uom = uom;
		this.tolerance = tolerance;
		this.unitPrice = unitPrice;
		this.estimateQty = estimateQty;
		this.estimateCost = estimateCost;
		this.customerQty = customerQty;
		if (customerQty!=null){	
			this.customerCost =new BigDecimal(Double.parseDouble(customerQty.toString())* Double.parseDouble(unitPrice.toString()));
		}else
			this.customerCost=new BigDecimal(0);
		this.damageQty = damageQty;
		if (damageQty!=null){	
			this.damageCost =new BigDecimal(Double.parseDouble(damageQty.toString())* Double.parseDouble(unitPrice.toString()));
		}else {
			this.damageCost=new BigDecimal(0);
		}
		
		
		//this.damageCost =new BigDecimal(Double.parseDouble(damageCost.toString())* Double.parseDouble(unitPrice.toString()));
		//setData(customerQty, damageQty);
	}
	
	
	
	//Narmal Constructor
	public FAMaterialGrid(String resCd, String matNm, BigDecimal unitPrice, String uom) {
		super();
		this.resCd = resCd;
		this.matNm = matNm;
		this.unitPrice = unitPrice;
		this.uom = uom;
		this.resType="MAT-COST";
		this.resCat=new BigDecimal("1");
		this.tolerance=new BigDecimal("10");
	}
	
	//Narmal Constructor
	public FAMaterialGrid(String resCd, String matNm, BigDecimal unitPrice, BigDecimal matQty, String uom) {
		super();
		this.resCd = resCd;
		this.matNm = matNm;
		this.unitPrice = unitPrice;
		this.estimateQty=matQty;
		this.uom = uom;
		this.resType="MAT-COST";
		this.resCat=new BigDecimal("1");
		this.tolerance=new BigDecimal("10");
	}
	//SPCNDMAT
	public FAMaterialGrid(String resCd, String matNm, BigDecimal unitPrice, String uom, BigDecimal measurementFactor) {
		super();
		this.resCd = resCd;
		this.matNm = matNm;
		this.unitPrice = unitPrice;
		this.uom = uom;
		this.measurementFactor = measurementFactor;
		//this.estimateQty=new BigDecimal(measurementFactor.doubleValue()*conductorLength);
		this.resType="MAT-COST";
		this.resCat=new BigDecimal("1");
		this.tolerance=new BigDecimal("10");
		
	}
	
	//Spsvcwrm
	public FAMaterialGrid(String resCd, String matNm, BigDecimal unitPrice, String uom, BigDecimal measurementFactor, BigDecimal extraQty) {
		super();
		this.resCd = resCd;
		this.matNm = matNm;
		this.unitPrice = unitPrice;
		this.uom = uom;
		this.measurementFactor = measurementFactor;
		this.extraQty=extraQty;
		this.resType="MAT-COST";
		this.resCat=new BigDecimal("1");
		this.tolerance=new BigDecimal("10");
		
	}
	
	

	public BigDecimal getDamageQty() {
		return damageQty;
	}
	public void setDamageQty(BigDecimal damageQty) {
		this.damageQty = damageQty;
		if(this.damageQty!=null)
		{
			this.damageQty = this.damageQty.setScale(2, BigDecimal.ROUND_HALF_UP);
		}
	}
	public BigDecimal getCustomerQty() {
		return customerQty;
	}
	public void setCustomerQty(BigDecimal customerQty) {
		this.customerQty = customerQty;
		if(this.customerQty!=null)
		{
			this.customerQty = this.customerQty.setScale(2, BigDecimal.ROUND_HALF_UP);
		}
	}
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
	public BigDecimal getEstimateCost() {
		return estimateCost;
	}
	public void setEstimateCost(BigDecimal estimateCost) {
		this.estimateCost = estimateCost;
		if(this.estimateCost!=null)
		{
			this.estimateCost = this.estimateCost.setScale(2, BigDecimal.ROUND_HALF_UP);
		}
	}
	
	public String getResCd() {
		return resCd;
	}
	public void setResCd(String resCd) {
		this.resCd = resCd;
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
		if(this.unitPrice!=null)
		{
			this.unitPrice = this.unitPrice.setScale(2, BigDecimal.ROUND_HALF_UP);
		}
	}
	public BigDecimal getEstimateQty() {
		return estimateQty;
	}
	public void setEstimateQty(BigDecimal estimateQty) {
		this.estimateQty = estimateQty;
		if(this.estimateQty!=null)
		{
			this.estimateQty = this.estimateQty.setScale(2, BigDecimal.ROUND_HALF_UP);
		}
	}
	public BigDecimal getTolerance() {
		return tolerance;
	}
	public void setTolerance(BigDecimal tolerance) {
		this.tolerance = tolerance;
		if(this.tolerance!=null)
		{
			this.tolerance = this.tolerance.setScale(2, BigDecimal.ROUND_HALF_UP);
		}
	}
	public BigDecimal getCustomerCost() {
		return customerCost;
	}
	public void setCustomerCost(BigDecimal customerCost) {
		this.customerCost = customerCost;
		if(this.customerCost!=null)
		{
			this.customerCost = this.customerCost.setScale(2, BigDecimal.ROUND_HALF_UP);
		}
	}
	public BigDecimal getDamageCost() {
		return damageCost;
	}
	public void setDamageCost(BigDecimal damageCost) {
		this.damageCost = damageCost;
		if(this.damageCost!=null)
		{
			this.damageCost = this.damageCost.setScale(2, BigDecimal.ROUND_HALF_UP);
		}
	}
	
	public BigDecimal getMeasurementFactor() {
		return measurementFactor;
	}
	public void setMeasurementFactor(BigDecimal measurementFactor) {
		this.measurementFactor = measurementFactor;
		if(this.measurementFactor!=null)
		{
			this.measurementFactor = this.measurementFactor.setScale(2, BigDecimal.ROUND_HALF_UP);
		}
	}
	
	public BigDecimal getExtraQty() {
		return extraQty;
	}
	public void setExtraQty(BigDecimal extraQty) {
		this.extraQty = extraQty;
		if(this.extraQty!=null)
		{
			this.extraQty = this.extraQty.setScale(2, BigDecimal.ROUND_HALF_UP);
		}
	}
	
	public BigDecimal getMntQty() {
		return mntQty;
	}
	public void setMntQty(BigDecimal mntQty) {
		this.mntQty = mntQty;
		if(this.mntQty!=null)
		{
			this.mntQty = this.mntQty.setScale(2, BigDecimal.ROUND_HALF_UP);
		}
	}
	public BigDecimal getMntCost() {
		return mntCost;
	}
	public void setMntCost(BigDecimal mntCost) {
		this.mntCost = mntCost;
		if(this.mntCost!=null)
		{
			this.mntCost = this.mntCost.setScale(2, BigDecimal.ROUND_HALF_UP);
		}
	}
	
	@Override
	public String toString() {
		return "FAMaterialGrid [resType=" + resType + ", resCd=" + resCd
				+ ", resCat=" + resCat + ", matNm=" + matNm + ", uom=" + uom
				+ ", tolerance=" + tolerance + ", unitPrice=" + unitPrice
				+ ", realUnitPrice=" + realUnitPrice 
				+ ", measurementFactor="+ measurementFactor + ", extraQty=" + extraQty
				+ ", estimateQty=" + estimateQty + ", estimateCost="+ estimateCost 
				+ ", customerQty=" + customerQty+ ", customerCost=" + customerCost 
				+ ", damageQty=" + damageQty+ ", damageCost=" + damageCost 
				+ ", mntQty=" + mntQty+ ", mntCost=" + mntCost 
				+ ", commitedQty=" + commitedQty+ ", commitedCost=" + commitedCost
				+ ", faManCatCode=" + faManCatCode+ ", faManCatDesc=" + faManCatDesc
				+ ", faSubCatCode=" + faSubCatCode+ ", faSubCatDesc=" + faSubCatDesc
				+ "]";
	}
	
	public BigDecimal getRealUnitPrice() {
		return realUnitPrice;
	}
	public void setRealUnitPrice(BigDecimal realUnitPrice) {
		this.realUnitPrice = realUnitPrice;
	}
	public BigDecimal getCommitedQty() {
		return commitedQty;
	}
	public void setCommitedQty(BigDecimal commitedQty) {
		this.commitedQty = commitedQty;
	}
	public BigDecimal getCommitedCost() {
		return commitedCost;
	}
	public void setCommitedCost(BigDecimal commitedCost) {
		this.commitedCost = commitedCost;
	}
	public String getFaManCatCode() {
		return faManCatCode;
	}
	public void setFaManCatCode(String faManCatCode) {
		this.faManCatCode = faManCatCode;
	}
	public String getFaManCatDesc() {
		return faManCatDesc;
	}
	public void setFaManCatDesc(String faManCatDesc) {
		this.faManCatDesc = faManCatDesc;
	}
	public String getFaSubCatCode() {
		return faSubCatCode;
	}
	public void setFaSubCatCode(String faSubCatCode) {
		this.faSubCatCode = faSubCatCode;
	}
	public String getFaSubCatDesc() {
		return faSubCatDesc;
	}
	public void setFaSubCatDesc(String faSubCatDesc) {
		this.faSubCatDesc = faSubCatDesc;
	}
	
	
	
	
}