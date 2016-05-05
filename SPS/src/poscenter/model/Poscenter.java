package poscenter.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the POSCENTER database table.
 * 
 */
@Entity
public class Poscenter implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="POS_CENTER_NO")
	private long posCenterNo;

	@Column(name="POS_CENTER_ADDRESS")
	private String posCenterAddress;

	@Column(name="POS_CENTER_NAME")
	private String posCenterName;

    public Poscenter() {
    }

	public long getPosCenterNo() {
		return this.posCenterNo;
	}

	public void setPosCenterNo(long posCenterNo) {
		this.posCenterNo = posCenterNo;
	}

	public String getPosCenterAddress() {
		return this.posCenterAddress;
	}

	public void setPosCenterAddress(String posCenterAddress) {
		this.posCenterAddress = posCenterAddress;
	}

	public String getPosCenterName() {
		return this.posCenterName;
	}

	public void setPosCenterName(String posCenterName) {
		this.posCenterName = posCenterName;
	}

}