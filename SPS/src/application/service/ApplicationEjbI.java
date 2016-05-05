package application.service;

import java.util.Date;
import java.util.List;

import javax.persistence.PersistenceException;

import estimate.model.ApplicationDisplay;

import application.model.Application;
import application.model.ApplicationPK;

//import application.model.Application;
//import application.model.ApplicationPK;

public interface ApplicationEjbI {
	Application findByAppId(ApplicationPK id);
	void createApplication(Application application); 
	void createApplicationAutoId(Application application, String appIdPrefix);
	List<Application> getAll(); 
	Application findByApplicationNo(String applicationNo) throws PersistenceException;
	Application findByApplicationNo(String applicationNo, String deptId) throws PersistenceException;
	void updateApplication(Application application);  
	void removeApplication(Application application);  
	void removeAll();
	List<Application> getApplicationList(String deptId, String status);
	List<Application> getAll(String deptId);
	List<Application> getSearchResultsSet(String deptId, String idNo, 	String appplicationId, String applicationNo, String firstName, String lastName, String suburb, String city, Date fromDate, Date toDate,String fileref);
	List<String> getApplicationIdList(String deptId, String applicationType);
	List<String> getApplicationNosByStatus(String deptId, String allocatedTo,
			String applicationType, String status);
	List<String> getApplicationNosByStatus(String deptId, String allocatedTo,
			String applicationType, String applicationSubType, String status); 
	Application findByApplicationId(String applicationId);
	List<Application> getApplicationList(String deptId
			, String status, String appllicationType); 
	Application findByApplicationNo(String applicationNo, String deptId,
			String applicationType)
			throws PersistenceException; 
	List<Application> getSearchResults(String deptId, String idNo, 	String appplicationId, String applicationNo, String firstName, String lastName, String suburb, String city, Date fromDate, Date toDate, String jobNo);
	List<ApplicationDisplay> getToBeAllocatedApplicationList(String deptId,
			String status, String applicationType)
			throws PersistenceException;
	public int changeStatusApplication(String applicationNo, String deptId, String status, String webAppName);
}
