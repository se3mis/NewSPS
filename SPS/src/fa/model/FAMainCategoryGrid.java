package fa.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.MathContext;

@SuppressWarnings("serial")
public class FAMainCategoryGrid  implements Serializable
{
	private String faManCatCode;
	private String faManCatDesc;
	private String featureCode;
	private String featureDesc;
	private String featureValue;
	
	
	
	public FAMainCategoryGrid() 
	{
		super();
	}
	
	//Full Conscrutor
	public FAMainCategoryGrid(String faManCatCode, String faManCatDesc,
			String featureCode,String featureDesc, String featureValue)
	{
		super();
		this.faManCatCode = faManCatCode;
		this.faManCatDesc = faManCatDesc;
		this.featureCode = featureCode;
		this.featureValue = featureValue;
		this.featureDesc = featureDesc;
	}
	
	@Override
	public String toString() {
		return "FAMaterialGrid [faManCatCode=" + faManCatCode
								+ ", faManCatDesc=" + faManCatDesc
								+ ", featureCode=" + featureCode
								+ ", featureDesc=" + featureDesc
								+ ", featureValue=" + featureValue
								+ "]";
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
	public String getFeatureCode() {
		return featureCode;
	}
	public void setFeatureCode(String featureCode) {
		this.featureCode = featureCode;
	}
	public String getFeatureValue() {
		return featureValue;
	}
	public void setFeatureValue(String featureValue) {
		this.featureValue = featureValue;
	}

	public String getFeatureDesc() {
		return featureDesc;
	}

	public void setFeatureDesc(String featureDesc) {
		this.featureDesc = featureDesc;
	}
	
	
	
	
}