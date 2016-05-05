package offDoc.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the LETTER_REASONS database table.
 * 
 */
@Entity
@Table(name="LETTER_REASONS")
public class LetterReason implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private LetterReasonPK id;

	private String reason;

    public LetterReason() {
    }

	public LetterReasonPK getId() {
		return this.id;
	}

	public void setId(LetterReasonPK id) {
		this.id = id;
	}
	
	public String getReason() {
		return this.reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	@Override
	public String toString() {
		return "LetterReason [id=" + id + ", reason=" + reason + "]";
	}
	
	

}