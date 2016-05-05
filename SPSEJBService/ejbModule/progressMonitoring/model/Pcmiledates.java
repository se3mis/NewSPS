package progressMonitoring.model;


import java.io.Serializable;



import javax.persistence.EmbeddedId;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;







@Entity
@Table(name="PCMILEDATES")
public class Pcmiledates implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private PcmiledatesPK id;
	
	@Column (name="CPERCENTAGE")
	private Float cpercentage;
	
	@Column (name="REMARKS")
	private String remarks;
	
	@Column (name="MILE_NM")
	private String mileName;
	

	public PcmiledatesPK getId() {
		return id;
	}

	public void setId(PcmiledatesPK id) {
		this.id = id;
	}

	public Float getCpercentage() {
		return cpercentage;
	}

	public void setCpercentage(Float cpercentage) {
		this.cpercentage = cpercentage;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getMileName() {
		return mileName;
	}

	public void setMileName(String mileName) {
		this.mileName = mileName;
	}
	
	
	
	
	
}
