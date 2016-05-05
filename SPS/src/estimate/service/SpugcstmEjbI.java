package estimate.service;

import java.util.List;

import javax.persistence.PersistenceException;

import estimate.model.Spugcstm;
import estimate.model.SpugcstmPK;

public interface SpugcstmEjbI {
	void createSpugcstm(Spugcstm spugcstm);
	List<Spugcstm> getAll() ;
	void updateSpugcstm(Spugcstm spugcstm)  ;
	void removeSpugcstm(Spugcstm spugcstm)  ;
	void removeAll();
	Spugcstm findById(SpugcstmPK id) throws PersistenceException ;

}
