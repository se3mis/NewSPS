package estimate.service;

import java.util.List;

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

public interface SpserestEjbI {
	void createSpserest(Spserest Spserest);
	List<Spserest> getAll(String deptId) ;
	void updateSpserest(Spserest Spserest)  ;
	void removeSpserest(Spserest Spserest)  ;
	void removeAll();
	Spserest findById(SpserestPK id) throws PersistenceException ;
	void saveServiceEstimate(Spserest spserest, List<Spsetpol> poleList, List<Spsetstu> strutList, List<Spsetsty> stayList, List<Spsetwir> wireList,List<SpsetpolPK> delPoleList, List<SpsetstuPK> delStrutList, List<SpsetstyPK> delStayList, List<SpsetwirPK> delWireList);
	Spserest findByApplicationNo(String applicationNo);

}
