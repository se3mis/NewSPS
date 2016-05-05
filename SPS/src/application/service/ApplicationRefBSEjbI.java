package application.service;

import java.util.List;

import javax.persistence.PersistenceException;

import application.model.ApplicationRefBS;
import application.model.ApplicationRefBSPK;
import application.model.ApplicationReference;
import application.model.ApplicationReferencePK;

public interface ApplicationRefBSEjbI {
	ApplicationRefBS findByAppId(ApplicationRefBSPK id);
	List<ApplicationRefBS> getAll(); 
	void createApplicationReference(ApplicationRefBS applicationReference); 
	void updateApplicationReference(ApplicationRefBS applicationReference) ;
	ApplicationRefBS findByApplicationNo(String applicationNo,String deptId) throws PersistenceException;
	void removeApplicationReference(ApplicationRefBS applicationReference);
	void removeAll();
	String getNextApplicationNo(String applicationNoPrefix);  

}
