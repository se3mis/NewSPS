package estimate.service;

import java.util.List;

import javax.persistence.PersistenceException;

import estimate.model.Pcjbtypm;
import estimate.model.PcjbtypmPK;

public interface PcjbtypmEjbI {
	void createPcjbtypm(Pcjbtypm pcjbtypm);
	List<Pcjbtypm> getAll() ;
	List<Pcjbtypm> getAll(String deptId);
	List<String> getCatCode(String deptId);
	void updatePcjbtypm(Pcjbtypm pcjbtypm);
	void removePcjbtypm(Pcjbtypm pcjbtypm);
	void removeAll();
	Pcjbtypm findById(PcjbtypmPK id) throws PersistenceException ;

}
