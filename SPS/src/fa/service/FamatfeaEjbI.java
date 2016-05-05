package fa.service;

import java.util.List;

import javax.persistence.PersistenceException;

import fa.model.Famatfea;
import fa.model.FamatfeaPK;

public interface FamatfeaEjbI {
	void createFamatfea(Famatfea famatfea);

	void updateFamatfea(Famatfea famatfea);

	void removeFamatfea(Famatfea famatfea);

	List<Famatfea> getAll();

	void removeAll();

	Famatfea findById(FamatfeaPK id) throws PersistenceException;

}
