package inventory.ejb;
import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Remote;

@Remote
public interface InwrhmapDaoRemote {
	public String mapWarehouse(String deptId, String webAppName);
	public List<String> loadWarehouses(String deptId, String webAppName);
	BigDecimal getConRateByDeptId(String deptId,String webAppName) throws Exception;
}
