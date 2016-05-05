package estimate.ejb;
import java.util.List;

import javax.ejb.Remote;
import javax.persistence.PersistenceException;

import estimate.model.MaterialGrid;
import estimate.model.Spsvcwrm;
import estimate.model.SpsvcwrmPK;

@Remote
public interface SpsvcwrmDaoRemote {
	void createSpsvcwrm(Spsvcwrm spsvcwrm, String webAppName);
	List<Spsvcwrm> getAll(String webAppName) ;
	void updateSpsvcwrm(Spsvcwrm spsvcwrm, String webAppName)  ;
	void removeSpsvcwrm(Spsvcwrm spsvcwrm, String webAppName)  ;
	void removeAll(String webAppName);
	Spsvcwrm findById(SpsvcwrmPK id, String webAppName) throws PersistenceException ;
	List<MaterialGrid> getServiceWireMaterialGrid( String deptId,long phase, long connectionType, String wiringType, Double serviceLength, String webAppName);
	List<Spsvcwrm> getAll(String deptId, String webAppName);

}
