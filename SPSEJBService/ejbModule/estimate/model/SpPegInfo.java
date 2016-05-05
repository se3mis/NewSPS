package estimate.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Author: Dinusha Nirmalie
 * Created: March 01, 2012 11:23:42 AM
 */

/**
 * The persistent class for the SpPegInfo database table.
 * 
 */
@Entity
@Table(name="SPPEGINFORMATION")
public class SpPegInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SpPegInfoPK id;

	@Column(name="NAME")
	private String name;

	
	
	
	
	@Column(name="DESCRIPTION")
	private String description;

    public SpPegInfo() {
    }

    

	

	public SpPegInfoPK getId() {
		return id;
	}





	public void setId(SpPegInfoPK id) {
		this.id = id;
	}





	public String getName() {
		return name;
	}





	public void setName(String name) {
		this.name = name;
	}






	public void setDescription(String description) {
		this.description = description;
	}





	@Override
	public String toString() {
		return "SpPegInfo [id=" + id + ", description=" + description
				+ "]";
	}

}