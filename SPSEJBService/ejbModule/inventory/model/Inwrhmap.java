package inventory.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the INWRHMAP database table.
 * 
 */
@Entity
public class Inwrhmap implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private InwrhmapPK id;

	@Column(name="CON_RAT")
	private double conRat;

    public Inwrhmap() {
    }

	public InwrhmapPK getId() {
		return this.id;
	}

	public void setId(InwrhmapPK id) {
		this.id = id;
	}
	
	public double getConRat() {
		return this.conRat;
	}

	public void setConRat(double conRat) {
		this.conRat = conRat;
	}

	@Override
	public String toString() {
		return "Inwrhmap [id=" + id + ", conRat=" + conRat + "]";
	}
	
	

}