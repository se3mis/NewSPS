package estimate.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the SPCRCONV database table.
 * 
 */
@Entity
@Table(name="Spnorms")
public class Spnorms implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SpnormsPK id;

	@Column(name="UOM")
	private String uom;

	@Column(name="STANDARDCOST")
	private float standardCost;
	
	@Column(name="DESCRIPTION")
	private String description;
	
	@Column(name="LINEPARENTID")
	private String parentId;

    public Spnorms() {
    }

    

	public SpnormsPK getId() {
		return id;
	}



	public void setId(SpnormsPK id) {
		this.id = id;
	}



	public String getParentId() {
		return parentId;
	}



	public void setParentId(String parentId) {
		this.parentId = parentId;
	}



	public String getUom() {
		return uom;
	}



	public void setUom(String uom) {
		this.uom = uom;
	}



	public float getStandardCost() {
		return standardCost;
	}



	public void setStandardCost(float standardCost) {
		this.standardCost = standardCost;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	@Override
	public String toString() {
		return "Norms [id=" + id + ", standardCost=" + standardCost
				+ ", description=" + description + "]";
	}

}