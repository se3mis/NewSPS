package estimate.ejb;
import java.util.List;

import javax.ejb.Remote;
import javax.persistence.PersistenceException;

import estimate.model.Spestmtm;
import estimate.model.SpestmtmPK;

@Remote
public interface SpestmtmDaoRemote {
	void createSpestmtm(Spestmtm spestmtm, String webAppName);
	List<Spestmtm> getAll(String webAppName) ;
	void updateSpestmtm(Spestmtm spestmtm, String webAppName)  ;
	void removeSpestmtm(Spestmtm spestmtm, String webAppName)  ;
	void removeAll(String webAppName) ;
	Spestmtm findById(SpestmtmPK id, String webAppName) throws PersistenceException ;
	List<Spestmtm> getAll(String deptId, String webAppName);
	List<Spestmtm> estimateMaterials(Long phase, Long connectionType, String wiringType, String deptId, String webAppName);
	


}
