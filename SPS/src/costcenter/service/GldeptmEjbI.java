package costcenter.service;

import java.util.List;

import javax.persistence.PersistenceException;

import costcenter.model.Gldeptm;

public interface GldeptmEjbI {
	String findDeptName(String DeptId );
	Gldeptm findGldeptm(Gldeptm gldeptm);
	Gldeptm findGldeptm(String deptId);
	List<String> getPostDepartmentIds(String deptId, String webAppName)
	throws Exception;
	List<String> findAreaCodeNames(String deptId, String webAppName)
	throws Exception;
	List<String> findAreaCodesForPost(String deptId);
	
	//List<Gldeptm> findAreaCodes(String DeptId );
}

