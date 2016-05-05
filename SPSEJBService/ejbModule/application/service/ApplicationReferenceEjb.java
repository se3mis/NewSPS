package application.service;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.PersistenceException;


import application.ejb.ApplicationReferenceDaoRemote;
import application.model.ApplicationReference;
import application.model.ApplicationReferencePK;

public class ApplicationReferenceEjb implements ApplicationReferenceDaoRemote{
	private Context context;
	private ApplicationReferenceDaoRemote dao; 
	
	
	
	public ApplicationReferenceEjb() {
		super();
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
	public ApplicationReference findByAppId(ApplicationReferencePK id, String webAppName) {
		return dao.findByAppId(id, webAppName);
	}

	@Override
	public List<ApplicationReference> getAll( String webAppName) {
		return dao.getAll(webAppName);
	}

	@Override
	public void createApplicationReference(	ApplicationReference applicationReference, String webAppName) {
		dao.createApplicationReference(applicationReference, webAppName);
		
	}

	@Override
	public void updateApplicationReference(ApplicationReference applicationReference, String webAppName) {
		dao.updateApplicationReference(applicationReference, webAppName);
		
	}

	@Override
	public ApplicationReference findByApplicationNo(String applicationNo, String webAppName)throws PersistenceException {
		return dao.findByApplicationNo(applicationNo, webAppName);
	}

	@Override
	public void removeApplicationReference( ApplicationReference applicationReference, String webAppName) {
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
		ApplicationReferenceEjb ejb =new ApplicationReferenceEjb();
		//System.out.println(ejb.getAll("SMCTesting"));
		//System.out.println(ejb.findByStdEstimateNoCom("530.00", "R3"));
		/**System.out.println(ejb.getNextJobNo("547.10/EMT/12/", "R1"));
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
		//System.out.println(ejb.findByJobNo("424.10/CRJ/11/", "R1"));	*/	
	}

	@Override
	public ApplicationReference findByJobNo(String jobNo,String deptId, String webAppName) throws PersistenceException {
		return dao.findByJobNo(jobNo,deptId, webAppName);
	}

	@Override
	public String getNextJobNo(String jobNoPrefix, String webAppName) {
		return dao.getNextJobNo(jobNoPrefix, webAppName);
	}

	
	
	
	

}
