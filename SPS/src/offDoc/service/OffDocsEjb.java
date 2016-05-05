package offDoc.service;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.PersistenceException;

import offDoc.ejb.OffDocsBeanRemote;
import offDoc.model.LetterReason;
import offDoc.model.LetterReasonPK;

public class OffDocsEjb implements OffDocsEjbI{
	private Context context;
	private OffDocsBeanRemote bean; 
	private String region=null;
	
	
	public OffDocsEjb(String region) {
		super();
		this.region=region;
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
	public void createLetterReason(LetterReason letterReason) {
		bean.createLetterReason(letterReason, region);
		
	}

	@Override
	public List<LetterReason> getAll(String deptId) {
		return bean.getAll(deptId, region);
	}

	@Override
	public void updateLetterReason(LetterReason letterReason) {
		bean.updateLetterReason(letterReason, region);
		
	}

	@Override
	public void removeLetterReason(LetterReason letterReason) {
		bean.removeLetterReason(letterReason, region);
		
	}

	@Override
	public void removeAll() {
		bean.removeAll(region);
		
	}

	@Override
	public LetterReason findById(LetterReasonPK id) throws PersistenceException {
		return bean.findById(id, region);
	}
	
	
	@Override
	public List<LetterReason> getReasons(String deptId, String letterType) {
		return bean.getReasons(deptId, letterType, region);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		OffDocsEjb ejb=new OffDocsEjb("region");
		System.out.println(ejb);

	}

}
