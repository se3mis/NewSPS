package application.service;

import java.util.Date;
import java.util.List;

import javax.ejb.EJBException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.PersistenceException;

import estimate.model.ApplicationDisplay;

import util.common.AppStatus;

import application.ejb.ApplicationDaoRemote;
import application.model.Application;
import application.model.ApplicationPK;


public class ApplicationEjb implements ApplicationDaoRemote{
	private Context context;
	private ApplicationDaoRemote dao; 
		
	public ApplicationEjb() {
		super();
		this.dao=lookupDao();
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
	public  Application findByAppId(ApplicationPK id, String webAppName) {
		return dao.findByAppId(id,webAppName);
	}
	@Override
	public void updateApplication(Application application, String webAppName) {
		dao.updateApplication(application,webAppName);
	}
	
	@Override
	public List<Application> getAll( String webAppName) {
		return dao.getAll(webAppName);
	}

	@Override
	public void createApplication(Application application, String webAppName) {
		dao.createApplication(application,webAppName);
		
	}

	//@Override
	//public void updateApplication(Application application) {
	//	dao.updateApplication(application);
		
	//}

	@Override
	public Application findByApplicationNo(String applicationNo, String webAppName)	throws PersistenceException {
		return dao.findByApplicationNo(applicationNo,webAppName);
	}

	@Override
	public void removeApplication(Application application, String webAppName) {
		dao.removeApplication(application,webAppName);
		
	}

	@Override
	public void removeAll( String webAppName) {
		dao.removeAll(webAppName);
		
	}
	
	@Override
	public List<Application> getApplicationList(String deptId, String status, String webAppName) {
		return dao.getApplicationList(deptId, status, webAppName);
	}

	@Override
	public List<Application> getAll(String deptId,String webAppName) {
		return dao.getAll(deptId,webAppName);
	}
	
	@Override
	public int changeStatusApplication(String applicationNo, String deptId,String status, String webAppName) {
		return dao.changeStatusApplication(applicationNo, deptId, status, webAppName);
		
	}
	
	@Override
	public String getNextAppId(String appIdPrefix,String webAppName) {
		return dao.getNextAppId(appIdPrefix, webAppName);
	}
	
	@Override
	public String createApplicationAutoId(Application application, String appIdPrefix, String webAppName) {
		return dao.createApplicationAutoId(application, appIdPrefix, webAppName);
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try{
		ApplicationEjb ejb=new ApplicationEjb();
		Date date=new Date();
		//date.getTime();
		ApplicationPK id=new ApplicationPK();
		id.setApplicationId("514.20/ANC/2011/0056");
		id.setDeptId("514.20");
		//514.20/ENC/2011/1000
		//                               ejb.getSearchResults(deptId, idNo, appplicationId, applicationNo, firstName, lastName, suburb, city, fromDate, toDate, jobNo, webAppName)
		
		System.out.println("List : "+ejb.getNextAppId("430.00/DCB/14","R2"));
		
		
		/**System.out.println("sdlkfnsdkn "+ejb.getSearchResults(null, null, null, null, null, null, null, null, null, null, "423.50/SMC/11/1230", "R1").size());
		System.out.println("sdlkfnsdkn "+ejb.getSearchResults(null, null, null, null, null, null, null, null, null, null, "423.50/SMC/11/1230", "R1"));
		//System.out.println(ejb.getSearchResults(null, "81200", appplicationId, applicationNo, firstName, lastName, suburb, city, fromDate, toDate));
		//System.out.println("hflsdfjsd "+ejb.getNextJAppId("518.10/ANC/2011/", "R3"));
		System.out.println("hflsdfjsd "+ejb.getNextAppId("443.50/ANC/2011/", "R2"));
		System.out.println("hflsdfjsd "+ejb.getNextAppId("423.50/ANC/2011/", "R1"));
		System.out.println("hflsdfjsd "+ejb.getApplicationList("514.20", "C", "NC", "sd;kmf"));
		System.out.println("hflsdfjsd "+ejb.getNextAppId("422.10/ANC/2011/", "R1"));
		System.out.println("hflsdfjsd "+ejb.findByAppId(id, "asdsd"));
		System.out.println("hflsdfjsd "+ejb.findByApplicationNo("514.20/ENC/2011/0038", "514.20", "g;mfg"));
		System.out.println("hflsdfjsd "+ejb.getApplicationNosByStatus("423.10", "DEO42310", "NC", "A", "R1"));
		System.out.println("hflsdfjsd "+ejb.getApplicationNosByStatus("423.10", "DEO42310", "NC", "A", "R1").size());
		System.out.println("hflsdfjsd "+ejb.getAppStatusByAppNo("514.20/ENC/2011/0005", "514.20", "SMCTesting"));
		System.out.println("hflsdfjsd "+ejb.getApplicationIdList("514.20", "NC","SMCTesting").size());
		System.out.println(ejb.findByAppId(id, "SMCTesting"));
		System.out.println(ejb.getApplicationIdList("510.20", "NC","SMCTesting").size());
		System.out.println(ejb.getApplicationList("510.20", "A", "SMCTesting"));
		
		//System.out.println("sdlkfnsdkn "+ejb.getSearchResults("510.20", "23", null, null, null, null, null, null, null, null));
		//System.out.println("sdlkfnsdkn "+ejb.getSearchResults(null, "81200", null, null, null, null, null, null, null, null, "SMCTesting"));
		//System.out.println(ejb.getSearchResults(null, "81200", appplicationId, applicationNo, firstName, lastName, suburb, city, fromDate, toDate));
		System.out.println(ejb.findByApplicationNo("441.40/ENC/2011/0001", "441.40", "SMCTesting"));
		System.out.println(ejb.findByApplicationNo("441.40/ENC/2011/0001", "SMCTesting"));
		System.out.println(ejb.getNextAppId("510.20/APP/2010/", "SMCTesting")); 
		System.out.println(ejb.findByApplicationNo("510.20/SNL/2010/0008", "SMCTesting")); 
		//ejb.changeStatusApplication("510.20/SNL/2010/0008", "510.20", AppStatus.ESTIMATED, "SMCTesting");
		//ApplicationPK id=new ApplicationPK("R8", "510.20");
		//Application application=ejb.findByAppId(id);
		//System.out.println(application);
		//application.setApplicationType("CR");
		//ejb.updateApplication(application);
		//Application application2=ejb.findByAppId(id);
		//System.out.println(application2);
		//application2.setApplicationType("NL");
		//ejb.updateApplication(application2);
		//System.out.println(application2);
		//System.out.println(daoTest.getAll());
		//ApplicationPK id4=new ApplicationPK("R37", "510.20");
		//System.out.println(ejb.findByAppId(id4));
		//ejb.removeApplication(ejb.findByAppId(id4));
		System.out.println("okbndfljkgndflgndflgnldfknglkdfngkldfnkgdfnkgndf");
		System.out.println("jhfksjdhf"+ejb.findByApplicationNo("SMC/2009/003", "SMCTesting"));*/
		
		}catch (EJBException e) {
			//e.printStackTrace();
			Throwable lastCause = e;
			while (lastCause.getCause() != null)
			      lastCause = lastCause.getCause();
			      if (lastCause.getMessage().startsWith("Index: 0, Size: 0"))
			        //throw new CustomerException("The Customer Id already exists.");
			      System.out.println("ERROR MESSEGE" + "can not find the number");
			System.out.print(e.getCause());
		}
		
		
		

	}

	//@Override
	//public List<Application> getSearchResults(String deptId,
	//		String appplicationId, String applicationNo, String firstName,
	//		String lastName, String fromDate, String toDate) {
	//	// TODO Auto-generated method stub
	//	return null;
	//}

	@Override
	public List<Application> getSearchResultSet(String deptId, String idNo,
			String appplicationId, String applicationNo, String firstName,
			String lastName, String suburb, String city, Date fromDate,
			Date toDate,String fileref, String webAppName) {
		
		return dao.getSearchResults(deptId, idNo, appplicationId, applicationNo, firstName, lastName, suburb, city, fromDate, toDate,fileref, webAppName);
	}

	@Override
	public Application findByApplicationNo(String applicationNo, String deptId, String webAppName)
			throws PersistenceException {
		return dao.findByApplicationNo(applicationNo, deptId, webAppName);
	}

	@Override
	public List<String> getApplicationIdList(String deptId, String applicationType,String webAppName) {
		return dao.getApplicationIdList(deptId, applicationType,webAppName);
	}

	@Override
	public void changeStatusById(String applicationId, String deptId,
			String status, String webAppName) {
		dao.changeStatusById(applicationId, deptId, status,webAppName);
		
	}

	@Override
	public String getAppStatusByAppNo(String applicationNo, String deptId,
			String webAppName) {
		return dao.getAppStatusByAppNo(applicationNo, deptId, webAppName);
	}

	@Override
	public List<String> getApplicationNosByStatus(String deptId,
			String allocatedTo, String applicationType, String status,
			String webAppName) {
		return dao.getApplicationNosByStatus(deptId, allocatedTo, applicationType, status, webAppName);
	}

	@Override
	public List<String> getApplicationNosByStatus(String deptId,
			String allocatedTo, String applicationType,
			String applicationSubType, String status, String webAppName) {
		return dao.getApplicationNosByStatus(deptId, allocatedTo, applicationType, applicationSubType, status, webAppName);
	}

	@Override
	public Application findByApplicationId(String applicationId,
			String webAppName) {
		return dao.findByApplicationId(applicationId, webAppName);
	}

	@Override
	public List<Application> getApplicationList(String deptId,
			 String status, String applicationType,String webAppName) {
		return dao.getApplicationList(deptId, status, applicationType, webAppName);
	}

	@Override
	public Application findByApplicationNo(String applicationNo, String deptId,
			String applicationType, String webAppName)
			throws PersistenceException {
		return dao.findByApplicationNo(applicationNo, deptId, applicationType, webAppName);
	}

	@Override
	public List<Application> getSearchResults(String deptId, String idNo,
			String appplicationId, String applicationNo, String firstName,
			String lastName, String suburb, String city, Date fromDate,
			Date toDate, String jobNo, String webAppName) {
		return dao.getSearchResults(deptId, idNo, appplicationId, applicationNo, firstName, lastName, suburb, city, fromDate, toDate, jobNo, webAppName);
	}

	@Override
	public List<ApplicationDisplay> getToBeAllocatedApplicationList(String deptId,
			String status, String applicationType, String webAppName)
			throws PersistenceException {
		// TODO Auto-generated method stub
		return dao.getToBeAllocatedApplicationList(deptId,status,applicationType,webAppName);
	}

	

	

	

}
