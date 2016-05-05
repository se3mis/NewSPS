package fa.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the FASCFEAT database table.
 * 
 */
@Entity
public class Fascfeat implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private FascfeatPK id;

	private String description;

	private String status;

    public Fascfeat() {
    }

	public FascfeatPK getId() {
		return this.id;
	}

	public void setId(FascfeatPK id) {
		this.id = id;
	}
	
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Fascfeat [id=" + id + ", description=" + description
				+ ", status=" + status + "]";
	}

}