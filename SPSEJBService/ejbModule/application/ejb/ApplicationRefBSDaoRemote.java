package application.ejb;
import java.util.List;

import javax.ejb.Remote;
import javax.persistence.PersistenceException;

import application.model.ApplicationRefBS;
import application.model.ApplicationRefBSPK;
import application.model.ApplicationReference;
import application.model.ApplicationReferencePK;

@Remote
public interface ApplicationRefBSDaoRemote {
	ApplicationRefBS findByAppId(ApplicationRefBSPK id, String webAppName);
	List<ApplicationRefBS> getAll(String webAppName); 
	void createApplicationReference(ApplicationRefBS applicationReference, String webAppName); 
	void updateApplicationReference(ApplicationRefBS applicationReference, String webAppName) ;
	public ApplicationRefBS findByApplicationNo(String applicationNo,String deptid, String webAppName) throws PersistenceException;
	void removeApplicationReference(ApplicationRefBS applicationReference, String webAppName);
	void removeAll(String webAppName);
	String getNextApplicationNo(String applicationNoPrefix, String webAppName);
	ApplicationRefBS findByJobNo(String jobNo, String webAppName) throws PersistenceException;
	String getNextJobNo(String jobNoPrefix, String webAppName);
	int updateEstimationNo(String applicationNo, String webAppName) throws PersistenceException;

}
