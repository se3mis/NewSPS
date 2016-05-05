package progressMonitoring.model;


import java.io.Serializable;



import javax.persistence.EmbeddedId;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;




@Entity
@Table(name="PCMILEM")
public class Pcmilem implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private PcmilemPK id;
	
	@Column (name="MILE_NM")
	private String mile_nm;
	
	@Column (name="PERCENTAGE")
	private Float percentage;

	

	public PcmilemPK getId() {
		return id;
	}

	public void setId(PcmilemPK id) {
		this.id = id;
	}

	public String getMile_nm() {
		return mile_nm;
	}

	public void setMile_nm(String mile_nm) {
		this.mile_nm = mile_nm;
	}

	public Float getPercentage() {
		return percentage;
	}

	public void setPercentage(Float percentage) {
		this.percentage = percentage;
	}
	
	
	


	

}
