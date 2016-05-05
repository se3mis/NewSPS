package fa.service;

import java.util.List;

import javax.persistence.PersistenceException;

import fa.model.Famatcat;
import fa.model.FamatcatPK;

public interface FamatcatEjbI {
	void createFamatcat(Famatcat famatcat);

	void updateFamatcat(Famatcat famatcat);

	void removeFamatcat(Famatcat famatcat);

	List<Famatcat> getAll();

	void removeAll();

	Famatcat findById(FamatcatPK id) throws PersistenceException;

}
