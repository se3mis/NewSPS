package estimate.ejb;
import java.util.List;

import javax.ejb.Remote;
import javax.persistence.PersistenceException;

import estimate.model.Spcstngm;
import estimate.model.SpcstngmPK;

@Remote
public interface SpcstngmDaoRemote {
	void createSpcstngm(Spcstngm spcstngm, String webAppName);
	List<Spcstngm> getAll(String webAppName) ;
	void updateSpcstngm(Spcstngm spcstngm, String webAppName)  ;
	void removeSpcstngm(Spcstngm spcstngm, String webAppName)  ;
	void removeAll(String webAppName) ;
	Spcstngm findById(SpcstngmPK id, String webAppName) throws PersistenceException ;
	List<Spcstngm> getAll(String deptId, String webAppName);
	Spcstngm getSpcstngm(Long phase, Long connectionType,String  tariffCatCode,Long  length, String deptId, String webAppName);
}
