package costcenter.service;

import java.util.List;

import javax.persistence.PersistenceException;

import costcenter.model.Gldeptin;

public interface GldeptinEjbI {
	void createGldeptin(Gldeptin gldeptin);
	List<Gldeptin> getAll() ;
	void updateGldeptin(Gldeptin gldeptin)  ;
	void removeGldeptin(Gldeptin gldeptin)  ;
	void removeAll() ;
	Gldeptin findById(String deptId) throws PersistenceException;
	Gldeptin getFindByDeptId(String deptId);
	
}
