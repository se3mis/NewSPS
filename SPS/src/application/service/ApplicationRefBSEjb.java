package application.service;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.PersistenceException;

import application.ejb.ApplicationRefBSDaoRemote;
import application.ejb.ApplicationReferenceDaoRemote;
import application.model.ApplicationRefBS;
import application.model.ApplicationRefBSPK;
import application.model.ApplicationReference;
import application.model.ApplicationReferencePK;

public class ApplicationRefBSEjb implements ApplicationRefBSEjbI{
	private Context context;
	private ApplicationRefBSDaoRemote dao; 
	private String region=null;
	
	
	
	public ApplicationRefBSEjb(String region) {
		super();
		this.region=region;
		this.dao=lookupDao();
	}

	private ApplicationRefBSDaoRemote lookupDao() {
		try
		{
			 context = new InitialContext();
			 ApplicationRefBSDaoRemote dao = (ApplicationRefBSDaoRemote) context.lookup("ApplicationRefBSDao/remote");
			 return dao; 
		} catch (NamingException e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}

	
	
	@Override
	public ApplicationRefBS findByAppId(ApplicationRefBSPK id) {
		return dao.findByAppId(id, region);
	}

	@Override
	public List<ApplicationRefBS> getAll() {
		return dao.getAll(region);
	}

	@Override
	public void createApplicationReference(ApplicationRefBS applicationReference) {
		dao.createApplicationReference(applicationReference, region);
		
	}

	@Override
	public void updateApplicationReference(ApplicationRefBS applicationReference) {
		dao.updateApplicationReference(applicationReference, region);
		
	}

	@Override
	public ApplicationRefBS findByApplicationNo(String applicationNo,String deptId) throws PersistenceException {
		return dao.findByApplicationNo(applicationNo,deptId, region);
	}

	@Override
	public void removeApplicationReference(ApplicationRefBS applicationReference) {
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
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ApplicationRefBSEjb ejb =new ApplicationRefBSEjb("SMCTesting");
		System.out.println(ejb.getAll());
		ApplicationReferencePK id=new ApplicationReferencePK();
		id.setApplicationId("R133");
		id.setDeptId("510.20");
		//System.out.println(ejb.findByAppId(id));
		System.out.println(ejb.getNextApplicationNo("510.20/SNL/2010/"));

	}

	

}
