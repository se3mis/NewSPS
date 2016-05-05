package costcenter.ejb;
import java.util.List;


import javax.ejb.Remote;
import javax.persistence.PersistenceException;

import costcenter.model.Area;
import costcenter.model.Gldeptin;
import costcenter.model.Gldeptm;
import costcenter.model.Province;

@Remote
public interface CostCenterBeanRemote {
	List<Area> getAllAreas(String webAppName);
	List<Province> getAllProvince(String webAppName);
	void createGldeptin(Gldeptin gldeptin, String webAppName);
	List<Gldeptin> getAllGldeptin(String webAppName) ;
	void updateGldeptin(Gldeptin gldeptin, String webAppName)  ;
	void removeGldeptin(Gldeptin gldeptin, String webAppName)  ;
	void removeAll(String webAppName) ;
	Gldeptin findById(String deptId, String webAppName) throws PersistenceException;
	Gldeptin getFindByDeptId(String deptId, String webAppName);
	String findDeptName(String deptId, String webAppName);
	Gldeptm findGldeptm(Gldeptm gldeptm, String webAppName);
	Gldeptm findGldeptm(String deptId, String webAppName);
	List<String> findAreaDeptIdList(String deptId, String webAppName);
	List<String> findDgmDeptIdList(String deptId, String webAppName);
	List<Gldeptm> getAllGldeptm(String webAppName);
	List<String> findAgmDeptIdList(String deptId, String webAppName);


}
