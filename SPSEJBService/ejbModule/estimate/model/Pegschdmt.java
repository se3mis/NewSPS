package estimate.model;

import java.io.Serializable;
import javax.persistence.*;

import java.math.BigDecimal;



/**
 * The persistent class for the PCESTHTT database table.
 * 
 */
@Entity
@Table(name="Pegschdmt")
public class Pegschdmt implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PegschdmtPK id;

	

	@Column(name="NOOFITEM")
	private BigDecimal noOfItem;

	

	@Column(name="NODEDES")
	private String nodeDes;

    
	
	public PegschdmtPK getId() {
		return id;
	}


	public void setId(PegschdmtPK id) {
		this.id = id;
	}




	public BigDecimal getNoOfItem() {
		return noOfItem;
	}


	public void setNoOfItem(BigDecimal noOfItem) {
		this.noOfItem = noOfItem;
	}


	public String getNodeDes() {
		return nodeDes;
	}


	public void setNodeDes(String nodeDes) {
		this.nodeDes = nodeDes;
	}




	@Override
	public String toString() {
		return "Pegschdmt [id="  + id
				+ ", noOfItem=" + noOfItem + ", nodeDes=" + nodeDes
				+ "]";
	}
	
	

}