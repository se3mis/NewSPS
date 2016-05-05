package inventory.service;

import java.util.List;

import estimate.model.MaterialGrid;
import estimate.model.MaterialPriceInfo;

public interface InventoryEjbI {
	List<MaterialPriceInfo> getMaterialPriceInfo(String deptId, String notIn);
	List<MaterialGrid> getMatListByCategory(String deptId, String like);
}
