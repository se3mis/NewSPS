package job.service;

import javax.persistence.PersistenceException;


import job.model.Pctrxhmt;
import job.model.PctrxhmtPK;

public interface PctrxhmtEjbI {
	
	    void createPctrxhmt(Pctrxhmt pctrxhmt);
		void updatePctrxhmt(Pctrxhmt pctrxhmt);
		void removePctrxhmt(Pctrxhmt pctrxhmt);
		void removeAll(String webAppName);
		Pctrxhmt findById(PctrxhmtPK id) throws PersistenceException;
		Pctrxhmt findBy3PK(String deptId,String docPf,String docNo) throws PersistenceException;
		
}
