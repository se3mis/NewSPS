package fa.service;

import java.util.List;

import javax.persistence.PersistenceException;

import fa.model.Fasubcat;

public interface FasubcatEjbI {
	void createFasubcat(Fasubcat fasubcat);

	void updateFasubcat(Fasubcat fasubcat);

	void removeFasubcat(Fasubcat fasubcat);

	List<Fasubcat> getAll();

	void removeAll();

	Fasubcat findById(String subCatCode) throws PersistenceException;

}
