package estimate.service;

import java.util.List;

import javax.persistence.PersistenceException;

import estimate.model.Spestmtm;
import estimate.model.SpestmtmPK;

public interface SpestmtmEjbI {
	void createSpestmtm(Spestmtm spestmtm);
	List<Spestmtm> getAll() ;
	void updateSpestmtm(Spestmtm spestmtm)  ;
	void removeSpestmtm(Spestmtm spestmtm)  ;
	void removeAll() ;
	Spestmtm findById(SpestmtmPK id) throws PersistenceException ;
	List<Spestmtm> getAll(String deptId);
	List<Spestmtm> estimateMaterials(Long phase, Long connectionType, String wiringType, String deptId);


}
