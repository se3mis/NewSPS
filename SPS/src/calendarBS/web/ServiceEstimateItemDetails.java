package calendarBS.web;

public class ServiceEstimateItemDetails {

	private String CategoryId;
	private String CategoryName;
	public String getCategoryName() {
		return CategoryName;
	}
	public void setCategoryName(String categoryName) {
		CategoryName = categoryName;
	}
	private String poleType;
	private String fromConductor;
	private String toConductor;
	public String getPolePointerType() {
		return polePointerType;
	}
	public void setPolePointerType(String polePointerType) {
		this.polePointerType = polePointerType;
	}
	private String Quantity;
	private String polePointerType;
	

	
	public ServiceEstimateItemDetails(String categoryId, String categoryName,
			String poleType, String fromConductor, String toConductor,
			String quantity, String polePointerType) {
		super();
		CategoryId = categoryId;
		CategoryName = categoryName;
		this.poleType = poleType;
		this.fromConductor = fromConductor;
		this.toConductor = toConductor;
		Quantity = quantity;
		this.polePointerType = polePointerType;
	}
	
	public String getCategoryId() {
		return CategoryId;
	}
	public void setCategoryId(String categoryId) {
		CategoryId = categoryId;
	}
	public String getPoleType() {
		return poleType;
	}
	public void setPoleType(String poleType) {
		this.poleType = poleType;
	}
	public String getFromConductor() {
		return fromConductor;
	}
	public void setFromConductor(String fromConductor) {
		this.fromConductor = fromConductor;
	}
	public String getToConductor() {
		return toConductor;
	}
	public void setToConductor(String toConductor) {
		this.toConductor = toConductor;
	}
	public String getQuantity() {
		return Quantity;
	}
	public void setQuantity(String quantity) {
		Quantity = quantity;
	}
	
	
}
