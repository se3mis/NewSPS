package application.service;

import java.util.List;

import javax.persistence.PersistenceException;

import application.model.ApplicationReference;
import application.model.ApplicationReferencePK;

public interface ApplicationReferenceEjbI {
	ApplicationReference findByAppId(ApplicationReferencePK id);
	List<ApplicationReference> getAll(); 
	void createApplicationReference(ApplicationReference applicationReference); 
	void updateApplicationReference(ApplicationReference applicationReference) ;
	ApplicationReference findByApplicationNo(String applicationNo) throws PersistenceException;
	void removeApplicationReference(ApplicationReference applicationReference);
	void removeAll();
	String getNextApplicationNo(String applicationNoPrefix);  
	public ApplicationReference findByJobNo(String jobNo,String deptId) ;
}
