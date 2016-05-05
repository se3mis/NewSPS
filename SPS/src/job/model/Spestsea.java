package job.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the SPESTSEA database table.
 * 
 */
@Entity
public class Spestsea implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SpestseaPK id;

	@Column(name="SEAL_NO1")
	private String sealNo1;

	@Column(name="SEAL_NO2")
	private String sealNo2;

	@Column(name="SEAL_NO3")
	private String sealNo3;

	@Column(name="SEAL_NO4")
	private String sealNo4;

	@Column(name="SEAL_NO5")
	private String sealNo5;

	@Column(name="SEAL_NO6")
	private String sealNo6;

	@Column(name="SEAL_NO7")
	private String sealNo7;

	@Column(name="SEAL_NO8")
	private String sealNo8;

	@Column(name="SEAL_NO9")
	private String sealNo9;

    public Spestsea() {
    }

	public SpestseaPK getId() {
		return this.id;
	}

	public void setId(SpestseaPK id) {
		this.id = id;
	}
	
	public String getSealNo1() {
		return this.sealNo1;
	}

	public void setSealNo1(String sealNo1) {
		this.sealNo1 = sealNo1;
	}

	public String getSealNo2() {
		return this.sealNo2;
	}

	public void setSealNo2(String sealNo2) {
		this.sealNo2 = sealNo2;
	}

	public String getSealNo3() {
		return this.sealNo3;
	}

	public void setSealNo3(String sealNo3) {
		this.sealNo3 = sealNo3;
	}

	public String getSealNo4() {
		return this.sealNo4;
	}

	public void setSealNo4(String sealNo4) {
		this.sealNo4 = sealNo4;
	}

	public String getSealNo5() {
		return this.sealNo5;
	}

	public void setSealNo5(String sealNo5) {
		this.sealNo5 = sealNo5;
	}

	public String getSealNo6() {
		return this.sealNo6;
	}

	public void setSealNo6(String sealNo6) {
		this.sealNo6 = sealNo6;
	}

	public String getSealNo7() {
		return this.sealNo7;
	}

	public void setSealNo7(String sealNo7) {
		this.sealNo7 = sealNo7;
	}

	public String getSealNo8() {
		return this.sealNo8;
	}

	public void setSealNo8(String sealNo8) {
		this.sealNo8 = sealNo8;
	}

	public String getSealNo9() {
		return this.sealNo9;
	}

	public void setSealNo9(String sealNo9) {
		this.sealNo9 = sealNo9;
	}

}