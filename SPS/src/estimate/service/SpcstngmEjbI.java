package estimate.service;

import java.util.List;

import javax.persistence.PersistenceException;

import estimate.model.Spcstngm;
import estimate.model.SpcstngmPK;

//import estimate.model.Spcstngm;
//import estimate.model.SpcstngmPK;

public interface SpcstngmEjbI {
	void createSpcstngm(Spcstngm spcstngm);
	List<Spcstngm> getAll() ;
	List<Spcstngm> getAll(String deptId) ;
	void updateSpcstngm(Spcstngm spcstngm)  ;
	void removeSpcstngm(Spcstngm spcstngm)  ;
	void removeAll() ;
	Spcstngm findById(SpcstngmPK id) throws PersistenceException ;
	Spcstngm getSpcstngm(Long phase, Long connectionType,String  tariffCatCode,Long  length, String deptId) throws PersistenceException ;


}
