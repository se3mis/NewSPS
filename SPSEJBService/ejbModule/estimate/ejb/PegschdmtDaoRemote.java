package estimate.ejb;
import java.util.List;

import javax.ejb.Remote;
import javax.persistence.PersistenceException;


import estimate.model.Pegschdmt;

@Remote
public interface PegschdmtDaoRemote {
	public void createPegschdmt(Pegschdmt pegschdmt, String webAppName) throws PersistenceException;
	public List<Pegschdmt> findByEstimationNo(String estNo,String deptId, String webAppName)throws PersistenceException;
	public void updatePegschdmt(Pegschdmt pegschdmt, String webAppName)throws PersistenceException;
	void deletePegschdmt(Pegschdmt pegschdmt, String webAppName);
}
