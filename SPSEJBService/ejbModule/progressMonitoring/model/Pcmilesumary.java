package progressMonitoring.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;




@Entity
@Table(name="PCMILESUMARY")
public class Pcmilesumary implements Serializable {
	
	private static final long serialVersionUID = 1L;

	
	@EmbeddedId
	private PcmilesumaryPK id;
	
	@Column (name="CPERCENTAGE")
	private Float cpercentage;
	
	@Column (name="STATUS")
	private BigDecimal status;
	
	@Temporal(TemporalType.DATE)
	@Column (name="DATE_LAST")
	private Date dateLast;

	
	
	public PcmilesumaryPK getId() {
		return id;
	}

	public void setId(PcmilesumaryPK id) {
		this.id = id;
	}

	public Float getCpercentage() {
		return cpercentage;
	}

	public void setCpercentage(Float cpercentage) {
		this.cpercentage = cpercentage;
	}

	public BigDecimal getStatus() {
		return status;
	}

	public void setStatus(BigDecimal status) {
		this.status = status;
	}

	public Date getDateLast() {
		return dateLast;
	}

	public void setDateLast(Date dateLast) {
		this.dateLast = dateLast;
	}
	
	

}
