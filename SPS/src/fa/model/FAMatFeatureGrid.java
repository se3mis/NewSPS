package fa.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.MathContext;

@SuppressWarnings("serial")
public class FAMatFeatureGrid  implements Serializable
{
	private String matCode;
	private String matName;
	private String faSubCatCode;
	private String faSubCatDesc;
	private String featureCode;
	private String featureDesc;
	private String featureValue;
	
	
	
	public FAMatFeatureGrid() 
	{
		super();
	}
	
	//Full Conscrutor
	public FAMatFeatureGrid(String matCode, String matName,String faSubCatCode, String faSubCatDesc,
			String featureCode,String featureDesc, String featureValue)
	{
		super();
		this.matCode = matCode;
		this.matName = matName;
		this.faSubCatCode = faSubCatCode;
		this.faSubCatDesc = faSubCatDesc;
		this.featureCode = featureCode;
		this.featureValue = featureValue;
		this.featureDesc = featureDesc;
	}
	
	@Override
	public String toString() {
		return "FAMaterialGrid [matCode=" + matCode
								+ ", matName=" + matName
								+ ", faSubCatCode=" + faSubCatCode
								+ ", faSubCatDesc=" + faSubCatDesc
								+ ", featureCode=" + featureCode
								+ ", featureDesc=" + featureDesc
								+ ", featureValue=" + featureValue
								+ "]";
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

	public String getMatCode() {
		return matCode;
	}

	public void setMatCode(String matCode) {
		this.matCode = matCode;
	}

	public String getMatName() {
		return matName;
	}

	public void setMatName(String matName) {
		this.matName = matName;
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