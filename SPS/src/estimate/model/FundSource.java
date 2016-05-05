package estimate.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class FundSource implements Serializable{
	private String fundSource;
	private String fundId;
	private String name;
	
	
	public FundSource() {
		super();
	}
	public FundSource(String fundSource, String fundId, String name) {
		super();
		this.fundSource = fundSource;
		this.fundId = fundId;
		this.name = name;
	}
	public String getFundSource() {
		return fundSource;
	}
	public void setFundSource(String fundSource) {
		this.fundSource = fundSource;
	}
	public String getFundId() {
		return fundId;
	}
	public void setFundId(String fundId) {
		this.fundId = fundId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "FundSource [fundSource=" + fundSource + ", fundId=" + fundId
				+ ", name=" + name + "]";
	}
	
	

}
