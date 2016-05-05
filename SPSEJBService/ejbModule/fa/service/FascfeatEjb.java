package fa.service;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.PersistenceException;

import fa.ejb.FascfeatDaoRemote;
import fa.model.Fascfeat;
import fa.model.FascfeatPK;

public class FascfeatEjb implements FascfeatDaoRemote{
	private Context context;
	private FascfeatDaoRemote dao; 
	public FascfeatEjb() {
		super();
		this.dao=lookupDao();
		
	}

	private FascfeatDaoRemote lookupDao() {
		try
		{
			 context = new InitialContext();
			 FascfeatDaoRemote dao = (FascfeatDaoRemote) context.lookup("FascfeatDao/remote");
			 return dao; 
		} catch (NamingException e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}

	

	@Override
	public void createFascfeat(Fascfeat fascfeat, String webAppName) {
		dao.createFascfeat(fascfeat, webAppName);
		
	}

	@Override
	public void updateFascfeat(Fascfeat fascfeat, String webAppName) {
		dao.updateFascfeat(fascfeat, webAppName);
		
	}

	@Override
	public void removeFascfeat(Fascfeat fascfeat, String webAppName) {
		dao.removeFascfeat(fascfeat, webAppName);
		
	}

	@Override
	public List<Fascfeat> getAll(String webAppName) {
		return dao.getAll(webAppName);
	}

	@Override
	public void removeAll(String webAppName) {
		dao.removeAll(webAppName);
		
	}

	@Override
	public Fascfeat findById(FascfeatPK id, String webAppName)
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
