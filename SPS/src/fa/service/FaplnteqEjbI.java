package fa.service;

import java.util.List;

import javax.persistence.PersistenceException;

import fa.model.Faplnteq;
import fa.model.FaplnteqPK;

public interface FaplnteqEjbI {
	void createFaplnteq(Faplnteq faplnteq);

	void updateFaplnteq(Faplnteq faplnteq);

	void removeFaplnteq(Faplnteq faplnteq);

	List<Faplnteq> getAll();

	void removeAll();

	Faplnteq findById(FaplnteqPK id) throws PersistenceException;

}
