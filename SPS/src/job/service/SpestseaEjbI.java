package job.service;

import java.util.List;

import javax.persistence.PersistenceException;

import job.model.Spestsea;
import job.model.SpestseaPK;

public interface SpestseaEjbI {
	void createSpestsea(Spestsea  spestsea);
	List<Spestsea> getAll(String deptId);
	void updateSpestsea(Spestsea spestsea)  ;
	void removeSpestsea(Spestsea spestsea)  ;
	void removeAll();
	Spestsea findById(SpestseaPK id) throws PersistenceException ;
	List<Spestsea> getSealNoList(String jobNo, String deptId) throws PersistenceException;
	Spestsea findByJobNo(String jobNo);

}
