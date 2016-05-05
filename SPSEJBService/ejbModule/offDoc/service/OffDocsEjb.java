package offDoc.service;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.PersistenceException;

import offDoc.ejb.OffDocsBeanRemote;
import offDoc.model.LetterReason;
import offDoc.model.LetterReasonPK;

public class OffDocsEjb implements OffDocsBeanRemote{

	private Context context;
	private OffDocsBeanRemote bean; 
	
	public OffDocsEjb() {
		super();
		bean=lookupGldeptmDao();
		
	}

	
	private OffDocsBeanRemote lookupGldeptmDao() {
		try
		{
			 context = new InitialContext();
			 OffDocsBeanRemote bean = (OffDocsBeanRemote) context.lookup("OffDocsBean/remote");
			 return bean; 
			 
		} catch (NamingException e){
			e.printStackTrace();
			
			throw new RuntimeException(e);
		}
		
	}
	


	@Override
	public void createLetterReason(LetterReason letterReason, String webAppName) {
		bean.createLetterReason(letterReason, webAppName);
		
	}

	@Override
	public List<LetterReason> getAll(String deptId, String webAppName) {
		return bean.getAll(deptId, webAppName);
	}

	@Override
	public void updateLetterReason(LetterReason letterReason, String webAppName) {
		bean.updateLetterReason(letterReason, webAppName);
		
	}

	@Override
	public void removeLetterReason(LetterReason letterReason, String webAppName) {
		bean.removeLetterReason(letterReason, webAppName);
		
	}

	@Override
	public void removeAll(String webAppName) {
		bean.removeAll(webAppName);
		
	}

	@Override
	public LetterReason findById(LetterReasonPK id, String webAppName) throws PersistenceException {
		return bean.findById(id, webAppName);
	}
	

	@Override
	public List<LetterReason> getReasons(String deptId, String letterType, String webAppName) {
		return bean.getReasons(deptId, letterType, webAppName);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		OffDocsEjb ejb=new OffDocsEjb();
		System.out.println(ejb);

	}



}
