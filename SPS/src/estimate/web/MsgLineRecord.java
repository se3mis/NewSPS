package estimate.web;

public class MsgLineRecord {
	private String code;
	private String quntity;
	
	private String cost;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getQuntity() {
		return quntity;
	}
	public void setQuntity(String quntity) {
		this.quntity = quntity;
	}
	public String getCost() {
		return cost;
	}
	public void setCost(String cost) {
		this.cost = cost;
	}
	@Override
	public String toString() {
		return "MsgLineRecord [Code=" + code + ", Quntity=" + quntity + ", Cost="
				+ cost + "]";
	}
	

}
