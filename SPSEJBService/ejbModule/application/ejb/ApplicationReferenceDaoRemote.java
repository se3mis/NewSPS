package application.ejb;
import java.util.List;

import javax.ejb.Remote;
import javax.persistence.PersistenceException;

import application.model.ApplicationReference;
import application.model.ApplicationReferencePK;

@Remote
public interface ApplicationReferenceDaoRemote {
	ApplicationReference findByAppId(ApplicationReferencePK id, String webAppName);
	List<ApplicationReference> getAll(String webAppName); 
	void createApplicationReference(ApplicationReference applicationReference, String webAppName); 
	void updateApplicationReference(ApplicationReference applicationReference, String webAppName) ;
	ApplicationReference findByApplicationNo(String applicationNo, String webAppName) throws PersistenceException;
	void removeApplicationReference(ApplicationReference applicationReference, String webAppName);
	void removeAll(String webAppName);
	String getNextApplicationNo(String applicationNoPrefix, String webAppName);
	ApplicationReference findByJobNo(String jobNo,String deptId, String webAppName) throws PersistenceException;
	String getNextJobNo(String jobNoPrefix, String webAppName);
	//ApplicationReference findByApplicationId(String applicationId, String webAppName) throws PersistenceException;
	//public ApplicationReference findByApplicationId(String applicationId, String deptId,String webAppName) throws PersistenceException;


}
