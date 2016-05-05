package fa.service;

import java.util.List;

import javax.persistence.PersistenceException;

import fa.model.Faactcat;

public interface FaactcatEjbI {
	void createFaactcat(Faactcat faactcat);

	void updateFaactcat(Faactcat faactcat);

	void removeFaactcat(Faactcat faactcat);

	List<Faactcat> getAll();

	void removeAll();

	Faactcat findById(String faCatCode) throws PersistenceException;
	

}
