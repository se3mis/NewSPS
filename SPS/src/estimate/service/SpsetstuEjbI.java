package estimate.service;

import java.util.List;

import javax.persistence.PersistenceException;

import estimate.model.Spsetstu;
import estimate.model.SpsetstuPK;

public interface SpsetstuEjbI {
	void createSpsetstu(Spsetstu Spsetstu);
	List<Spsetstu> getAll(String deptId);
	void updateSpsetstu(Spsetstu Spsetstu);
	void removeSpsetstu(Spsetstu Spsetstu);
	void removeAll();
	Spsetstu findById(SpsetstuPK id) throws PersistenceException;
	List<Spsetstu> getStrutList(String applicationNo, String deptId)
			throws PersistenceException;

}
