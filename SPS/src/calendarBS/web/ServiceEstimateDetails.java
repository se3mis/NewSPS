package calendarBS.web;

public class ServiceEstimateDetails {
	
	public String getCategory() {
		return Category;
	}
	public void setCategory(String category) {
		Category = category;
	}
	public String getItem() {
		return Item;
	}
	public void setItem(String item) {
		Item = item;
	}
	
	public String getStayType() {
		return StayType;
	}
	public void setStayType(String stayType) {
		StayType = stayType;
	}
	
	public ServiceEstimateDetails(String category, String item,
			String quantity, String description, String categoryId,
			String stayType) {
		super();
		Category = category;
		Item = item;
		Quantity = quantity;
		Description = description;
		CategoryId = categoryId;
		StayType = stayType;
	}
	public ServiceEstimateDetails(String category, String item,
			String quantity, String description, String categoryId) {
		super();
		Category = category;
		Item = item;
		Quantity = quantity;
		Description = description;
		CategoryId = categoryId;
	}
	public String getQuantity() {
		return Quantity;
	}
	public void setQuantity(String quantity) {
		Quantity = quantity;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public String getCategoryId() {
		return CategoryId;
	}
	public void setCategoryId(String categoryId) {
		CategoryId = categoryId;
	}
	private String Category;
	private String Item;		                                                                              
	private String Quantity;
	private String Description;
	private String CategoryId;
	private String StayType;
	
}
