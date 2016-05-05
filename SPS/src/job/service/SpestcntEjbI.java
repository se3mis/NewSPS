package job.service;

import java.util.List;

import javax.persistence.PersistenceException;

import job.model.Spestcnt;
import job.model.SpestcntPK;

//import job.model.Spestcnt;



public interface SpestcntEjbI {
	void createSpestcnt(Spestcnt  spestcnt);
	List<Spestcnt> getAll(String deptId) ;
	void updateSpestcnt(Spestcnt spestcnt)  ;
	void removeSpestcnt(Spestcnt spestcnt)  ;
	void removeAll() ;
	Spestcnt findByContractorId(String contractorId,String deptId) throws PersistenceException ;
	Spestcnt findById(SpestcntPK id) throws PersistenceException ;
	void createSpestcntAutoId(Spestcnt spestcnt);
	List<Spestcnt> getContractorByStatus(String deptId, String status);

}
