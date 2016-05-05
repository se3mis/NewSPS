package estimate.ejb;
import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Remote;
import javax.persistence.PersistenceException;

import estimate.model.AllocationSummaryDisplay;
import estimate.model.Spstdestdmt;


@Remote
public interface SpstdestdmtDaoRemote {
	void createSpstdestdmt(Spstdestdmt spstdestdmt , String webAppName);
	List<Spstdestdmt> getAll(String webAppName) ;
	void updateSpstdestdmt(Spstdestdmt spstdestdmt , String webAppName)  ;
	void removeSpstdestdmt(Spstdestdmt spstdestdmt , String webAppName)  ;
	void removeAll(String webAppName) ;
	Spstdestdmt findById(Spstdestdmt id, String webAppName) throws PersistenceException;
	
	public List<Spstdestdmt> findByApplicationNo(String appId, String webAppName)
	throws PersistenceException;
	List<AllocationSummaryDisplay> findEstimateSummary(String applicationNo,String deptId, String webAppName)
	throws PersistenceException;
}
