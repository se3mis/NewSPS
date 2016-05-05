package job.service;

import java.util.List;

import javax.persistence.PersistenceException;

import job.model.Spodrcrd;
import job.model.SpodrcrdPK;

public interface SpodrcrdEjbI {
	void createSpodrcrd(Spodrcrd spodrcrd );
	void updateSpodrcrd(Spodrcrd spodrcrd )  ;
	void removeSpodrcrd(Spodrcrd spodrcrd )  ;
	void removeAll();
	List<Spodrcrd> getAll(String deptId) ; 
	Spodrcrd findById(SpodrcrdPK id) throws PersistenceException;
	Spodrcrd findByJobNo(String jobNo);

}
