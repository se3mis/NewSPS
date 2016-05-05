package application.service;

import java.util.Date;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.PersistenceException;

import estimate.model.ApplicationDisplay;

import application.ejb.ApplicationDaoRemote;
import application.model.Application;
import application.model.ApplicationPK;
//import application.model.Application;
//import application.model.ApplicationPK;



public class ApplicationEjb implements ApplicationEjbI {
	private Context context;
	private ApplicationDaoRemote dao; 
	private String region=null;
	
	public ApplicationEjb(String region) {
		super();
		this.region=region;
		dao=lookupDao();
	}
	private ApplicationDaoRemote lookupDao() {
		try
		{
			 context = new InitialContext();
			 ApplicationDaoRemote dao = (ApplicationDaoRemote) context.lookup("ApplicationDao/remote");
			 //System.out.println(dao);
			 return dao; 
		} catch (NamingException e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}
	
	@Override
	public Application findByAppId(ApplicationPK id) {
		return dao.findByAppId(id, region);
	}
	
	@Override
	public void createApplication(Application application) {
		dao.createApplication(application, region);
		
	}
	
	@Override
	public void createApplicationAutoId(Application application, String appIdPrefix) {
		dao.createApplicationAutoId(application, appIdPrefix, region);
		
	}
	
	@Override
	public List<Application> getAll() {
		return dao.getAll(region);
	}
	
	@Override
	public Application findByApplicationNo(String applicationNo)
			throws PersistenceException {
		return dao.findByApplicationNo(applicationNo, region);
	}
	
	@Override
	public void updateApplication(Application application) {
		dao.updateApplication(application, region);
		
	}
	@Override
	public void removeApplication(Application application) {
		dao.removeApplication(application, region);
		
	}
	@Override
	public void removeAll() {
		dao.removeAll(region);
		
	}
	
	@Override
	public List<Application> getApplicationList(String deptId, String status) {
		return dao.getApplicationList(deptId, status, region);
	}
	@Override
	public List<Application> getAll(String deptId) {
		return dao.getAll(deptId, region);
	}
	
	@Override
	public List<Application> getSearchResultsSet(String deptId, String idNo,
			String appplicationId, String applicationNo, String firstName,
			String lastName, String suburb, String city, Date fromDate,
			Date toDate,String fileref) {
		return dao.getSearchResultSet(deptId, idNo, appplicationId, applicationNo, firstName, lastName, suburb, city, fromDate, toDate,fileref, region);
	}
	@Override
	public Application findByApplicationNo(String applicationNo, String deptId)
			throws PersistenceException {
		return dao.findByApplicationNo(applicationNo, deptId, region);
	}
	@Override
	public List<String> getApplicationIdList(String deptId, String applicationType) {
		return dao.getApplicationIdList(deptId, applicationType,region);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ApplicationEjb ejb=new ApplicationEjb("SMCTesting");
		//ApplicationPK id=new ApplicationPK("R8", "510.20");
		//System.out.println(ejb.findByAppId(id));
		System.out.println(ejb.findByApplicationNo("514.20/ENC/2011/1236","514.20","BS"));

	}
	@Override
	public List<String> getApplicationNosByStatus(String deptId,
			String allocatedTo, String applicationType, String status) {
		return dao.getApplicationNosByStatus(deptId, allocatedTo, applicationType, status, region);
	}
	@Override
	public List<String> getApplicationNosByStatus(String deptId,
			String allocatedTo, String applicationType,
			String applicationSubType, String status) {
		return dao.getApplicationNosByStatus(deptId, allocatedTo, applicationType, applicationSubType, status, region);
	}
	@Override
	public Application findByApplicationId(String applicationId) {
		return dao.findByApplicationId(applicationId, region);
	}
	@Override
	public List<Application> getApplicationList(String deptId,
			String applicationType, String status) {
		return dao.getApplicationList(deptId, status, applicationType, region);
	}
	@Override
	public Application findByApplicationNo(String applicationNo, String deptId,
			String applicationType) throws PersistenceException {
		return dao.findByApplicationNo(applicationNo, deptId, applicationType, region);
	}
	@Override
	public List<Application> getSearchResults(String deptId, String idNo,
			String appplicationId, String applicationNo, String firstName,
			String lastName, String suburb, String city, Date fromDate,
			Date toDate, String jobNo) {
		return dao.getSearchResults(deptId, idNo, appplicationId, applicationNo, firstName, lastName, suburb, city, fromDate, toDate, jobNo, region);
	}
	
	
	@Override
	public List<ApplicationDisplay> getToBeAllocatedApplicationList(String deptId,
			String status, String applicationType)
			throws PersistenceException {
		// TODO Auto-generated method stub
		return dao.getToBeAllocatedApplicationList(deptId,status,applicationType,region);
	}

	@Override
	public int changeStatusApplication(String applicationNo, String deptId, String status, String webAppName) {
		return dao.changeStatusApplication(applicationNo, deptId, status, webAppName);
		
	}

}
