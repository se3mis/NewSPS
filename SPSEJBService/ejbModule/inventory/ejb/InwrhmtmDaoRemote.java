package inventory.ejb;


import java.math.BigDecimal;
import java.util.List;

import inventory.model.Inwrhmtm;


import javax.ejb.Remote;

import estimate.model.MaterialGrid;

@Remote
public interface InwrhmtmDaoRemote {
	Inwrhmtm findByMatCd(String matCd, String warehouse, String webAppName);

	List<MaterialGrid> getAll(String warehouse,String webAppName);
	BigDecimal findUnitPriceByMatCd(String matCd, String webAppName);
	//List<MatInfo> getMatListByCategory(String deptId, String like);
	public List<String> findNPLMatCds(String deptId, String webAppName);
	public List<String> findMatCds(String deptId, String webAppName);
	public BigDecimal findUPByMatCd(String matCd, String warehouse, String webAppName);
}
