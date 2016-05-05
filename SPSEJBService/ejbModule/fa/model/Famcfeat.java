package fa.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the FAMCFEAT database table.
 * 
 */
@Entity
public class Famcfeat implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private FamcfeatPK id;

	private String description;

	private String status;

    public Famcfeat() {
    }

	public FamcfeatPK getId() {
		return this.id;
	}

	public void setId(FamcfeatPK id) {
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
		return "Famcfeat [id=" + id + ", description=" + description
				+ ", status=" + status + "]";
	}

}