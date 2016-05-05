package fa.service;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.PersistenceException;

import fa.ejb.FaplnteqDaoRemote;
import fa.model.Faplnteq;
import fa.model.FaplnteqPK;

public class FaplnteqEjb implements FaplnteqEjbI{

	private Context context;
	private FaplnteqDaoRemote dao; 
	private String region=null;
	
	public FaplnteqEjb(String region) {
		super();
		this.region=region;
		this.dao=lookupDao();
		
	}

	private FaplnteqDaoRemote lookupDao() {
		try
		{
			 context = new InitialContext();
			 FaplnteqDaoRemote dao = (FaplnteqDaoRemote) context.lookup("FaplnteqDao/remote");
			 return dao; 
		} catch (NamingException e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}

	@Override
	public void createFaplnteq(Faplnteq faplnteq) {
		dao.createFaplnteq(faplnteq, region);
		
	}

	@Override
	public void updateFaplnteq(Faplnteq faplnteq) {
		dao.updateFaplnteq(faplnteq, region);
		
	}

	@Override
	public void removeFaplnteq(Faplnteq faplnteq) {
		dao.removeFaplnteq(faplnteq, region);
		
	}

	@Override
	public List<Faplnteq> getAll() {
		return dao.getAll(region);
	}

	@Override
	public void removeAll() {
		dao.removeAll(region);
		
	}

	@Override
	public Faplnteq findById(FaplnteqPK id)
			throws PersistenceException {
		return dao.findById(id, region);
	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
