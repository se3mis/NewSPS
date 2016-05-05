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

public class ApplicationRefBSEjb implements ApplicationRefBSDaoRemote{
	private Context context;
	private ApplicationRefBSDaoRemote dao; 
	
	
	
	public ApplicationRefBSEjb() {
		super();
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
	public ApplicationRefBS findByAppId(ApplicationRefBSPK id, String webAppName) {
		return dao.findByAppId(id, webAppName);
	}

	@Override
	public List<ApplicationRefBS> getAll( String webAppName) {
		return dao.getAll(webAppName);
	}

	@Override
	public void createApplicationReference(	ApplicationRefBS applicationReference, String webAppName) {
		dao.createApplicationReference(applicationReference, webAppName);
		
	}

	@Override
	public void updateApplicationReference(ApplicationRefBS applicationReference, String webAppName) {
		dao.updateApplicationReference(applicationReference, webAppName);
		
	}

	@Override
	public ApplicationRefBS findByApplicationNo(String applicationNo,String deptId, String webAppName)throws PersistenceException {
		return dao.findByApplicationNo(applicationNo,deptId, webAppName);
	}

	@Override
	public void removeApplicationReference( ApplicationRefBS applicationReference, String webAppName) {
		dao.removeApplicationReference(applicationReference, webAppName);
		
	}
	
	@Override
	public String getNextApplicationNo(String applicationNoPrefix, String webAppName) {
		return dao.getNextApplicationNo(applicationNoPrefix, webAppName);
	}

	@Override
	public void removeAll( String webAppName) {
		dao.removeAll(webAppName);
		
	}
	/*
	 * @P
	 */
			
	public static void main(String[] args) {
		ApplicationRefBSEjb ejb =new ApplicationRefBSEjb();
		//System.out.println(ejb.getAll("SMCTesting"));
		System.out.println(ejb.getNextJobNo("547.10/EMT/12/", "R1"));
		System.out.println(ejb.getNextJobNo("422.20/SMC/11/", "R1"));
		System.out.println(ejb.getNextJobNo("2010/SMC/11/", "Ri1"));
		System.out.println(ejb.getNextJobNo("547.00/SMC/11/", "Ri1"));
		System.out.println(ejb.getNextJobNo("542.10/SMC/11/", "R1"));
		System.out.println(ejb.getNextJobNo("542.20/SMC/11/", "R1"));
		System.out.println(ejb.getNextApplicationNo("443.50/ENC/2011/", "R2"));
		System.out.println(ejb.getNextJobNo("443.50/SMC/11/", "R2"));
		System.out.println(ejb.getNextJobNo("548.20/SMC/11/", "R1"));
		System.out.println(ejb.getNextApplicationNo("548./ENC/2011/", "R1dsdf"));
		
		System.out.println(ejb.getNextApplicationNo("541.00/ECR/2011/", "R1"));
		System.out.println(ejb.getNextApplicationNo("547.10/ENC/2011/", "R1"));
		System.out.println(ejb.getNextApplicationNo("547.00/ENC/2011/", "R1"));
		System.out.println(ejb.getNextApplicationNo("547.10/ENC/2011/", "R1"));
		System.out.println(ejb.getNextApplicationNo("547.20/ENC/2011/", "R1"));
		System.out.println(ejb.getNextJobNo("547.20/SMC/11/", "R1"));
		
		System.out.println(ejb.getNextJobNo("547.10/SMC/11/", "R1"));
		System.out.println(ejb.getNextJobNo("547.20/SMC/11/", "R1"));
		
		System.out.println(ejb.getNextJobNo("548.10/SMC/11/", "R1"));
		System.out.println(ejb.getNextJobNo("548.20/SMC/11/", "R1"));
		
		System.out.println(ejb.getNextJobNo("541.10/SMC/11/", "R1"));
		System.out.println(ejb.getNextJobNo("541.20/SMC/11/", "R1"));
		
		System.out.println(ejb.getNextJobNo("542.10/SMC/11/", "R1"));
		System.out.println(ejb.getNextJobNo("542.20/SMC/11/", "R1"));
		System.out.println(ejb.getNextJobNo("511.20/SMC/11/", "R4"));
		System.out.println(ejb.getNextJobNo("424.10/CRJ/11/", "R1"));
		System.out.println(ejb.getNextJobNo("424.10/CRJ/11/", "R1"));
		System.out.println(ejb.getNextApplicationNo("444.40/ENC/2011/", "R2"));
		System.out.println(ejb.findByJobNo("424.10/CRJ/11/", "R1"));		
	}

	@Override
	public ApplicationRefBS findByJobNo(String jobNo, String webAppName) throws PersistenceException {
		return dao.findByJobNo(jobNo, webAppName);
	}

	@Override
	public String getNextJobNo(String jobNoPrefix, String webAppName) {
		return dao.getNextJobNo(jobNoPrefix, webAppName);
	}

	@Override
	public int updateEstimationNo(String applicationNo, String webAppName)
			throws PersistenceException {
		// TODO Auto-generated method stub
		return 0;
	}

	
	
	

}
