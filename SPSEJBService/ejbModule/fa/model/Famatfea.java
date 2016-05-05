package fa.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the FAMATFEA database table.
 * 
 */
@Entity
public class Famatfea implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private FamatfeaPK id;

	@Column(name="FEATURE_VALUE")
	private String featureValue;

    public Famatfea() {
    }

	public FamatfeaPK getId() {
		return this.id;
	}

	public void setId(FamatfeaPK id) {
		this.id = id;
	}
	
	public String getFeatureValue() {
		return this.featureValue;
	}

	public void setFeatureValue(String featureValue) {
		this.featureValue = featureValue;
	}

	@Override
	public String toString() {
		return "Famatfea [id=" + id + ", featureValue=" + featureValue + "]";
	}

}