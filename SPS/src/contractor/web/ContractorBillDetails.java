package contractor.web;

public class ContractorBillDetails {

	private String serviceCode;
	private String description;
	private String unitPrice;
	private String quantity;
	private String amount;
	
		
	public String getServiceCode() {
		return serviceCode;
	}


	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getUnitPrice() {
		return unitPrice;
	}


	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
	}


	public String getQuantity() {
		return quantity;
	}


	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}


	public String getAmount() {
		return amount;
	}


	public void setAmount(String amount) {
		this.amount = amount;
	}


	public ContractorBillDetails(String serviceCode, String description , String unitPrice,String quantity, String amount	) {
		super();
		this.serviceCode = serviceCode;
		this.description = description;
		this.unitPrice = unitPrice;
		this.quantity = quantity;
		this.amount = amount;

}
	
}
