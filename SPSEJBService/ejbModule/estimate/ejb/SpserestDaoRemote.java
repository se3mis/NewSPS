package estimate.ejb;
import java.util.List;

import javax.ejb.Remote;
import javax.persistence.PersistenceException;

import estimate.model.Spserest;
import estimate.model.SpserestPK;
import estimate.model.Spsetpol;
import estimate.model.SpsetpolPK;
import estimate.model.Spsetstu;
import estimate.model.SpsetstuPK;
import estimate.model.Spsetsty;
import estimate.model.SpsetstyPK;
import estimate.model.Spsetwir;
import estimate.model.SpsetwirPK;

@Remote
public interface SpserestDaoRemote {
	void createSpserest(Spserest spserest, String webAppName);
	List<Spserest> getAll(String deptId, String webAppName) ;
	void updateSpserest(Spserest spserest, String webAppName)  ;
	void removeSpserest(Spserest spserest, String webAppName)  ;
	void removeAll(String webAppName);
	Spserest findById(SpserestPK id, String webAppName) throws PersistenceException ;
	void saveServiceEstimate(Spserest spserest, List<Spsetpol> poleList, List<Spsetstu> strutList, List<Spsetsty> stayList, List<Spsetwir> wireList,List<SpsetpolPK> delPoleList, List<SpsetstuPK> delStrutList, List<SpsetstyPK> delStayList, List<SpsetwirPK> delWireList, String webAppName);
	Spserest findByApplicationNo(String applicationNo, String webAppName);

}
