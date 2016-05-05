package fa.service;

import java.util.List;

import javax.persistence.PersistenceException;

import fa.model.Faheader;
import fa.model.FaheaderPK;

public interface FaheaderEjbI {
	void createFaheader(Faheader faheader);

	void updateFaheader(Faheader faheader);

	void removeFaheader(Faheader faheader);

	List<Faheader> getAll();

	void removeAll();

	Faheader findById(FaheaderPK id) throws PersistenceException;

}
