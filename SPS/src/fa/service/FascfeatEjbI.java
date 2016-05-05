package fa.service;

import java.util.List;

import javax.persistence.PersistenceException;

import fa.model.Fascfeat;
import fa.model.FascfeatPK;

public interface FascfeatEjbI {
	void createFascfeat(Fascfeat fascfeat);

	void updateFascfeat(Fascfeat fascfeat);

	void removeFascfeat(Fascfeat fascfeat);

	List<Fascfeat> getAll();

	void removeAll();

	Fascfeat findById(FascfeatPK id) throws PersistenceException;

}
