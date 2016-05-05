package fa.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the FAMATCAT database table.
 * 
 */
@Entity
public class Famatcat implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private FamatcatPK id;

    public Famatcat() {
    }

	public FamatcatPK getId() {
		return this.id;
	}

	public void setId(FamatcatPK id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Famatcat [id=" + id + "]";
	}
	
}