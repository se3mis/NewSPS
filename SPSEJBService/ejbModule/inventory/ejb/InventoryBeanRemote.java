package inventory.ejb;


import java.util.List;

import javax.ejb.Remote;

import estimate.model.MaterialGrid;
import estimate.model.MaterialPriceInfo;

@Remote
public interface InventoryBeanRemote {
	List<MaterialPriceInfo> getMaterialPriceInfo(String deptId, String notIn, String webAppName);
	List<MaterialGrid> getMatListByCategory(String deptId, String like, String webAppName);
	

}
