package job.service;

import java.util.List;

import javax.persistence.PersistenceException;

import estimate.model.SpestedyCons;

import job.model.Spestbip;
import job.model.SpestbipPK;

public interface SpestbipEjbI {
	void createSpestbip(Spestbip  spestbip);
	List<Spestbip> getAll(String deptId);
	void updateSpestbip(Spestbip spestbip)  ;
	void removeSpestbip(Spestbip spestbip)  ;
	void removeAll() ;
	Spestbip findById(SpestbipPK id) throws PersistenceException ;
	


}
