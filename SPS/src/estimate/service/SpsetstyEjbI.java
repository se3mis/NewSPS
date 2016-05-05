package estimate.service;

import java.util.List;

import javax.persistence.PersistenceException;

import estimate.model.Spsetsty;
import estimate.model.SpsetstyPK;

public interface SpsetstyEjbI {
	void createSpsetsty(Spsetsty Spsetsty);
	List<Spsetsty> getAll(String deptId);
	void updateSpsetsty(Spsetsty Spsetsty);
	void removeSpsetsty(Spsetsty Spsetsty);
	void removeAll();
	Spsetsty findById(SpsetstyPK id) throws PersistenceException;
	List<Spsetsty> getStayList(String applicationNo, String deptId);

}
