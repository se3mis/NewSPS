package estimate.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;



/**
 * The persistent class for the Spstdestdmt database table.
 * 
 */
@Entity
public class Spstdestdmt implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SpstdestdmtPK id;

	@Column(name="LENGTH")
	private BigDecimal length;

	@Column(name="LINE_COST")
	private float lineCost;

	@Column(name="EST_COST")
	private BigDecimal estmateCost;

	@Column(name="DEPT_ID")
	private String deptId;
	
	@Column(name="UOM")
	private String uom;
	
	@Column(name="LINEDES")
	private String lineDescription;
	
	
    public Spstdestdmt() {
    }

	
    public SpstdestdmtPK getId() {
		return id;
	}


	public void setId(SpstdestdmtPK id) {
		this.id = id;
	}


	public BigDecimal getLength() {
		return length;
	}


	public void setLength(BigDecimal length) {
		//this.length = length;
		this.length = length.setScale(4, BigDecimal.ROUND_HALF_UP);
	}


	


	public float getLineCost() {
		return lineCost;
	}


	public void setLineCost(float lineCost) {
		this.lineCost = lineCost;
		
	}


	public BigDecimal getEstmateCost() {
		return estmateCost;
	}


	public void setEstmateCost(BigDecimal estmateCost) {
		//this.estmateCost = estmateCost;
		this.estmateCost = estmateCost.setScale(2, BigDecimal.ROUND_HALF_UP);
	}


	public String getDeptId() {
		return deptId;
	}


	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}


	public String getUom() {
		return uom;
	}


	public void setUom(String uom) {
		this.uom = uom;
	}


	public String getLineDescription() {
		return lineDescription;
	}


	public void setLineDescription(String lineDescription) {
		this.lineDescription = lineDescription;
	}


	@Override
	public String toString() {
		return "Spstdestdmt [id=" + id + ", length=" + length + ", lineCost="+lineCost+", estmateCost="+estmateCost+", deptId="+deptId+", uom="+uom+", lineDescription="+lineDescription+"]";
	}
	
	

}