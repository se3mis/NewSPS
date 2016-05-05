package job.ejb;
import java.util.List;

import javax.ejb.Remote;
import javax.persistence.PersistenceException;

import job.model.Spestcnt;
import job.model.SpestcntPK;

@Remote
public interface SpestcntDaoRemote {
	void createSpestcnt(Spestcnt  spestcnt, String webAppName);
	List<Spestcnt> getAll(String deptId, String webAppName) ;
	void updateSpestcnt(Spestcnt spestcnt, String webAppName)  ;
	void removeSpestcnt(Spestcnt spestcnt, String webAppName)  ;
	void removeAll(String webAppName) ;
	Spestcnt findByContractorId(String contractorId,String deptId, String webAppName) throws PersistenceException ;
	Spestcnt findById(SpestcntPK id, String webAppName) throws PersistenceException ;
	String getNextContractorId(String deptId, String webAppName);
	void createSpestcntAutoId(Spestcnt spestcnt, String webAppName);
	List<Spestcnt> getContractorByStatus(String deptId, String status, String webAppName);

}
