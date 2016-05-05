package fa.service;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.PersistenceException;

import fa.ejb.FaplnteqDaoRemote;
import fa.model.Faplnteq;
import fa.model.FaplnteqPK;

public class FaplnteqEjb implements FaplnteqDaoRemote{
	private Context context;
	private FaplnteqDaoRemote dao; 
	public FaplnteqEjb() {
		super();
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
	public void createFaplnteq(Faplnteq faplnteq, String webAppName) {
		dao.createFaplnteq(faplnteq, webAppName);
		
	}

	@Override
	public void updateFaplnteq(Faplnteq faplnteq, String webAppName) {
		dao.updateFaplnteq(faplnteq, webAppName);
		
	}

	@Override
	public void removeFaplnteq(Faplnteq faplnteq, String webAppName) {
		dao.removeFaplnteq(faplnteq, webAppName);
		
	}

	@Override
	public List<Faplnteq> getAll(String webAppName) {
		return dao.getAll(webAppName);
	}

	@Override
	public void removeAll(String webAppName) {
		dao.removeAll(webAppName);
		
	}

	@Override
	public Faplnteq findById(FaplnteqPK id, String webAppName)
			throws PersistenceException {
		return dao.findById(id, webAppName);
	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
