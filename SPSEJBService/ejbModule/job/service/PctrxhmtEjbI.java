package job.service;

import java.util.List;

import javax.persistence.PersistenceException;

import job.model.Pctrxhmt;
import job.model.PctrxhmtPK;

import estimate.model.Pcjbtypm;
import estimate.model.PcjbtypmPK;

public interface PctrxhmtEjbI {

	void createPctrxhmt(Pctrxhmt pctrxhmt);
	List<Pctrxhmt> getAll() ;
	List<Pctrxhmt> getAll(String deptId);
	void updatePctrxhmt(Pctrxhmt pctrxhmt);
	void removePctrxhmt(Pctrxhmt pctrxhmt);
	void removeAll();
	Pctrxhmt findById(PctrxhmtPK id) throws PersistenceException ;
	
}
