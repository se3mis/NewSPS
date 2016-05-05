package estimate.service;

import java.util.List;

import javax.persistence.PersistenceException;

import estimate.model.Spestpem;
import estimate.model.SpestpemPK;

public interface SpestpemEjbI {
	void createSpestpem(Spestpem spestpem);
	void createSpestpem(List<Spestpem> list);
	List<Spestpem> getAll() ;
	void updateSpestpem(Spestpem spestpem)  ;
	void removeSpestpem(Spestpem spestpem)  ;
	void removeSpestpem(List<SpestpemPK> list)  ;
	void removeAll();
	List<Spestpem> findById(SpestpemPK id) throws PersistenceException ;
	List<Spestpem> getPermissions(String applicationNo, String deptId) throws PersistenceException;

}
