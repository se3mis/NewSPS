package costcenter.service;

import java.util.List;

import javax.persistence.PersistenceException;

import costcenter.model.Area;
import costcenter.model.Gldeptin;
import costcenter.model.Gldeptm;
import costcenter.model.Province;

public interface CostCenterEjbI {
	List<Area> getAllAreas();
	List<Province> getAllProvince();
	void createGldeptin(Gldeptin gldeptin);
	List<Gldeptin> getAllGldeptin() ;
	void updateGldeptin(Gldeptin gldeptin)  ;
	void removeGldeptin(Gldeptin gldeptin)  ;
	void removeAll() ;
	Gldeptin findById(String deptId) throws PersistenceException;
	Gldeptin getFindByDeptId(String deptId);
	String findDeptName(String deptId);
	Gldeptm findGldeptm(Gldeptm gldeptm);
	Gldeptm findGldeptm(String deptId);
	List<String> findAreaDeptIdList(String deptId);
	List<String> findDgmDeptIdList(String deptId);
	List<Gldeptm> getAllGldeptm();
	List<String> findAgmDeptIdList(String deptId);

}
