package fa.service;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.PersistenceException;

import fa.ejb.FamcfeatDaoRemote;
import fa.model.Famcfeat;
import fa.model.FamcfeatPK;

public class FamcfeatEjb implements FamcfeatDaoRemote{
	private Context context;
	private FamcfeatDaoRemote dao; 
	public FamcfeatEjb() {
		super();
		this.dao=lookupDao();
		
	}

	private FamcfeatDaoRemote lookupDao() {
		try
		{
			 context = new InitialContext();
			 FamcfeatDaoRemote dao = (FamcfeatDaoRemote) context.lookup("FamcfeatDao/remote");
			 return dao; 
		} catch (NamingException e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}

	

	@Override
	public void createFamcfeat(Famcfeat famcfeat, String webAppName) {
		dao.createFamcfeat(famcfeat, webAppName);
		
	}

	@Override
	public void updateFamcfeat(Famcfeat famcfeat, String webAppName) {
		dao.updateFamcfeat(famcfeat, webAppName);
		
	}

	@Override
	public void removeFamcfeat(Famcfeat famcfeat, String webAppName) {
		dao.removeFamcfeat(famcfeat, webAppName);
		
	}

	@Override
	public List<Famcfeat> getAll(String webAppName) {
		return dao.getAll(webAppName);
	}

	@Override
	public void removeAll(String webAppName) {
		dao.removeAll(webAppName);
		
	}

	@Override
	public Famcfeat findById(FamcfeatPK id, String webAppName)
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
