package application.ejb;
import java.util.Date;
import java.util.List;

import javax.ejb.Remote;
import javax.persistence.PersistenceException;

import estimate.model.ApplicationDisplay;

import application.model.Application;
import application.model.ApplicationPK;

@Remote
public interface ApplicationDaoRemote {
	Application findByAppId(ApplicationPK id, String webAppName);
	List<Application> getAll(String webAppName); 
	void createApplication(Application application, String webAppName); 
	void updateApplication(Application application, String webAppName);
	Application findByApplicationNo(String applicationNo, String webAppName) throws PersistenceException;
	Application findByApplicationNo(String applicationNo, String deptId, String webAppName) throws PersistenceException;
	void removeApplication(Application application, String webAppName);
	void removeAll(String webAppName);
	List<Application> getApplicationList(String deptId, String status, String webAppName);
	List<Application> getAll(String deptId, String webAppName);
	int changeStatusApplication(String applicationNo, String deptId, String status, String webAppName);
	String getNextAppId(String appIdPrefix, String webAppName);
	String createApplicationAutoId(Application application, String appIdPrefix, String webAppName);
	//List<Application> getSearchResults(String deptId,String appplicationId,String applicationNo,String firstName,String lastName,String fromDate,String toDate, String webAppName);
	List<Application> getSearchResults(String deptId, String idNo, 	String appplicationId, String applicationNo, String firstName, String lastName, String suburb, String city, Date fromDate, Date toDate, String jobNo,String webAppName);
	List<String> getApplicationIdList(String deptId, String applicationType,String webAppName);
	void changeStatusById(String applicationId, String deptId, String status, String webAppName);
	String getAppStatusByAppNo(String applicationNo, String deptId,
			String webAppName);
	List<String> getApplicationNosByStatus(String deptId, String allocatedTo,
			String applicationType, String status, String webAppName);
	List<String> getApplicationNosByStatus(String deptId, String allocatedTo,
			String applicationType, String applicationSubType, String status,
			String webAppName);
	Application findByApplicationId(String applicationId, String webAppName);
	List<Application> getApplicationList(String deptId,
			String appllicationType, String status, String webAppName);
	Application findByApplicationNo(String applicationNo, String deptId,
			String applicationType, String webAppName)
			throws PersistenceException;
	List<Application> getSearchResultSet(String deptId, String idNo,
			String applicationId, String applicationNo, String firstName,
			String lastName, String suburb, String city, Date fromDate,
			Date toDate,String fileRef,String webAppName); 
	
	List<ApplicationDisplay> getToBeAllocatedApplicationList(String deptId, String status, String applicationType, String webAppName)throws PersistenceException;
	        
      
	
}
