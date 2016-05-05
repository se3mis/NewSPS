package job.service;

import java.util.List;

import javax.persistence.PersistenceException;

import job.model.Spjobtcd;

public interface SpjobtcdEjbI {
	void createSpjobtcd(Spjobtcd  spjobtcd);
	List<Spjobtcd> getAll(String deptId);
	void updateSpjobtcd(Spjobtcd spjobtcd)  ;
	void removeSpjobtcd(Spjobtcd spjobtcd)  ;
	void removeAll() ;
	Spjobtcd findById(String jobNo, String deptId) throws PersistenceException ;

}
