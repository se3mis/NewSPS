package estimate.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the SPSETWIR database table.
 * 
 */
@Entity
public class Spsetwir implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SpsetwirPK id;

	@Column(name="WIRE_LENGTH")
	private BigDecimal wireLength;

	@Column(name="WIRE_MODE")
	private String wireMode;

	@Column(name="WIRE_TYPE")
	private String wireType;

    public Spsetwir() {
    }

	public SpsetwirPK getId() {
		return this.id;
	}

	public void setId(SpsetwirPK id) {
		this.id = id;
	}
	
	public BigDecimal getWireLength() {
		return this.wireLength;
	}

	public void setWireLength(BigDecimal wireLength) {
		this.wireLength = wireLength;
	}

	public String getWireMode() {
		return this.wireMode;
	}

	public void setWireMode(String wireMode) {
		this.wireMode = wireMode;
	}

	public String getWireType() {
		return this.wireType;
	}

	public void setWireType(String wireType) {
		this.wireType = wireType;
	}

}