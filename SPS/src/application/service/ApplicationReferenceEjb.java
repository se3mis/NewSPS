package application.service;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.PersistenceException;

import application.ejb.ApplicationReferenceDaoRemote;
import application.model.ApplicationReference;
import application.model.ApplicationReferencePK;

public class ApplicationReferenceEjb implements ApplicationReferenceEjbI{
	private Context context;
	private ApplicationReferenceDaoRemote dao; 
	private String region=null;
	
	
	
	public ApplicationReferenceEjb(String region) {
		super();
		this.region=region;
		this.dao=lookupDao();
	}

	private ApplicationReferenceDaoRemote lookupDao() {
		try
		{
			 context = new InitialContext();
			 ApplicationReferenceDaoRemote dao = (ApplicationReferenceDaoRemote) context.lookup("ApplicationReferenceDao/remote");
			 return dao; 
		} catch (NamingException e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}

	
	
	@Override
	public ApplicationReference findByAppId(ApplicationReferencePK id) {
		return dao.findByAppId(id, region);
	}

	@Override
	public List<ApplicationReference> getAll() {
		return dao.getAll(region);
	}

	@Override
	public void createApplicationReference(ApplicationReference applicationReference) {
		dao.createApplicationReference(applicationReference, region);
		
	}

	@Override
	public void updateApplicationReference(ApplicationReference applicationReference) {
		dao.updateApplicationReference(applicationReference, region);
		
	}

	@Override
	public ApplicationReference findByApplicationNo(String applicationNo) throws PersistenceException {
		return dao.findByApplicationNo(applicationNo, region);
	}

	@Override
	public void removeApplicationReference(ApplicationReference applicationReference) {
		dao.removeApplicationReference(applicationReference, region);
		
	}

	@Override
	public void removeAll() {
		dao.removeAll(region);
		
	}

	@Override
	public String getNextApplicationNo(String applicationNoPrefix) {
		return dao.getNextApplicationNo(applicationNoPrefix, region);
	}
	
	@Override
	public ApplicationReference findByJobNo(String jobNo,String deptId)  {
		return dao.findByJobNo(jobNo,deptId, region);
	}
	
	/**of
	 * @param args
	 */
	public static void main(String[] args) {
		ApplicationReferenceEjb ejb =new ApplicationReferenceEjb("SMCTesting");
		System.out.println(ejb.getAll());
		ApplicationReferencePK id=new ApplicationReferencePK();
		id.setApplicationId("R133");
		id.setDeptId("510.20");
		System.out.println(ejb.findByAppId(id));
		System.out.println(ejb.getNextApplicationNo("510.20/SNL/2010/"));

	}

	

}
