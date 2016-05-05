package estimate.service;

import java.util.List;

import javax.persistence.PersistenceException;
import estimate.model.Spsetpol;
import estimate.model.SpsetpolPK;

public interface SpsetpolEjbI {
	void createSpsetpol(Spsetpol Spsetpol);
	void createSpsetpolList(List<Spsetpol> list);
	List<Spsetpol> getAll(String deptId);
	void updateSpsetpol(Spsetpol Spsetpol);
	void removeSpsetpol(Spsetpol Spsetpol);
	void removeAll();
	Spsetpol findById(SpsetpolPK id) throws PersistenceException;
	List<Spsetpol> getPoleList(String applicationNo, String deptId)
	throws PersistenceException;

}
