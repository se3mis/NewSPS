package estimate.ejb;
import java.util.List;

import javax.ejb.Remote;
import javax.persistence.PersistenceException;

import estimate.model.MaterialGrid;
import estimate.model.Spcndmat;
import estimate.model.SpcndmatPK;

@Remote
public interface SpcndmatDaoRemote {
	void createSpcndmat(Spcndmat spcndmat, String webAppName);
	List<Spcndmat> getAll(String webAppName) ;
	void updateSpcndmat(Spcndmat spcndmat, String webAppName)  ;
	void removeSpcndmat(Spcndmat spcndmat, String webAppName)  ;
	void removeAll(String webAppName);
	Spcndmat findById(SpcndmatPK id, String webAppName) throws PersistenceException ;
	List<MaterialGrid> getConductorMaterialGrid(String deptId, long phase,
			long connectionType, String wiringType, String conductorType,
			Double conductorlength, String webAppName) throws PersistenceException;
		

}
