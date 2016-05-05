package fa.service;

import java.util.List;

import javax.persistence.PersistenceException;

import fa.model.Famcfeat;
import fa.model.FamcfeatPK;

public interface FamcfeatEjbI {
	void createFamcfeat(Famcfeat famcfeat);

	void updateFamcfeat(Famcfeat famcfeat);

	void removeFamcfeat(Famcfeat famcfeat);

	List<Famcfeat> getAll();

	void removeAll();

	Famcfeat findById(FamcfeatPK id) throws PersistenceException;

}
