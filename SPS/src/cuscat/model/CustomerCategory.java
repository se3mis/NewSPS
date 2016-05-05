package cuscat.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the CUSTOMER_CATEGORY database table.
 * 
 */
@Entity
@Table(name="CUSTOMER_CATEGORY")
public class CustomerCategory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="CATEGORY_CODE")
	private long categoryCode;

	@Column(name="CATEGORY_NAME")
	private String categoryName;

	private String description;

    public CustomerCategory() {
    }

	public long getCategoryCode() {
		return this.categoryCode;
	}

	public void setCategoryCode(long categoryCode) {
		this.categoryCode = categoryCode;
	}

	public String getCategoryName() {
		return this.categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}