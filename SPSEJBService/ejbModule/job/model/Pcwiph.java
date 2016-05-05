package job.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the PCWIPH database table.
 * 
 */
@Entity
public class Pcwiph implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PcwiphPK id;

	private String flag;

	@Column(name="WIP_MTH")
	private BigDecimal wipMth;

	@Column(name="WIP_YR")
	private BigDecimal wipYr;

    public Pcwiph() {
    }

	public PcwiphPK getId() {
		return this.id;
	}

	public void setId(PcwiphPK id) {
		this.id = id;
	}
	
	public String getFlag() {
		return this.flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public BigDecimal getWipMth() {
		return this.wipMth;
	}

	public void setWipMth(BigDecimal wipMth) {
		this.wipMth = wipMth;
	}

	public BigDecimal getWipYr() {
		return this.wipYr;
	}

	public void setWipYr(BigDecimal wipYr) {
		this.wipYr = wipYr;
	}

}