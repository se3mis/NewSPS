package fa.service;

import java.util.List;

import javax.persistence.PersistenceException;

import fa.model.Fadetail;
import fa.model.FadetailPK;

public interface FadetailEjbI {
	void createFadetail(Fadetail fadetail);

	void updateFadetail(Fadetail fadetail);

	void removeFadetail(Fadetail fadetail);

	List<Fadetail> getAll();

	void removeAll();

	Fadetail findById(FadetailPK id) throws PersistenceException;

}
