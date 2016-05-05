package inventory.service;

import java.util.List;

import javax.persistence.PersistenceException;

import inventory.model.Inmatm;

public interface InmatmEjbI {
	void createInmatm(Inmatm inmatm);
	List<Inmatm> getAll1() ;
	void updateInmatm(Inmatm inmatm)  ;
	void removeInmatm(Inmatm inmatm)  ;
	void removeAll() ;
	Inmatm findById(String matCd) throws Exception ;
	String findName(String matCd) throws Exception ;
	public Inmatm findMatItem(String matCd)
	throws Exception ;
}
