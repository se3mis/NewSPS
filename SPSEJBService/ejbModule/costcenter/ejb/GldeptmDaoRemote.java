package costcenter.ejb;
import java.util.List;

import javax.ejb.Remote;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import costcenter.model.Gldeptm;

@Remote
public interface GldeptmDaoRemote {
	String findDeptName(String deptId, String webAppName);
	Gldeptm findGldeptm(Gldeptm gldeptm, String webAppName);
	Gldeptm findGldeptm(String deptId, String webAppName);
	List<String> findAreaDeptIdList(String deptId, String webAppName);
	List<String> findDgmDeptIdList(String deptId, String webAppName);
	List<Gldeptm> getAll(String webAppName);
	List<String> findAgmDeptIdList(String deptId, String webAppName);
	List<String> findAreaCodes(String deptId, String webAppName) throws PersistenceException;
	List<String> getPostDepartmentIds(String deptId, String webAppName)
	throws PersistenceException;
	List<String> findAreaCodeNames(String deptId, String webAppName);
	List<String> findAreaCodesForPost(String deptId, String webAppName);
}
