package fa.service;

import java.util.List;

import javax.persistence.PersistenceException;

import fa.model.Famancat;

public interface FamancatEjbI {
	void createFamancat(Famancat famancat);

	void updateFamancat(Famancat famancat);

	void removeFamancat(Famancat famancat);

	List<Famancat> getAll();

	void removeAll();

	Famancat findById(String catCode) throws PersistenceException;

}
