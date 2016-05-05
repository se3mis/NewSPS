package estimate.service;

import java.util.List;

import javax.persistence.PersistenceException;

import estimate.model.MaterialGrid;
import estimate.model.Spsvcwrm;
import estimate.model.SpsvcwrmPK;

public interface SpsvcwrmEjbI {
	void createSpsvcwrm(Spsvcwrm spsvcwrm);
	List<Spsvcwrm> getAll() ;
	void updateSpsvcwrm(Spsvcwrm spsvcwrm)  ;
	void removeSpsvcwrm(Spsvcwrm spsvcwrm)  ;
	void removeAll();
	Spsvcwrm findById(SpsvcwrmPK id) throws PersistenceException ;
	List<MaterialGrid> getServiceWireMaterialGrid(long phase,
			long connectionType, String wiringType, Double serviceLength,String deptId);
	

}
