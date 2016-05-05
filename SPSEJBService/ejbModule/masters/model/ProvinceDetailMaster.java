package masters.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the BANK database table.
 * 
 */
@Entity
@Table(name="PROVINCE_DETAIL_MASTER")
public class ProvinceDetailMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	
	private ProvinceDetailMasterPK districtpk;

	@Column(name="CODE_NAME")
	private String codeName;
	
	

    public ProvinceDetailMaster() {
    }

	

	public ProvinceDetailMasterPK getDistrictpk() {
		return districtpk;
	}



	public void setDistrictpk(ProvinceDetailMasterPK districtpk) {
		this.districtpk = districtpk;
	}



	public String getCodeName() {
		return codeName;
	}



	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}



	@Override
	public String toString() {
		return "Bank [districtpk=" + districtpk + ", codeName=" + codeName + "]";
	}

}