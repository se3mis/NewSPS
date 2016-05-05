package fa.service;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.PersistenceException;

import fa.ejb.FascfeatDaoRemote;
import fa.model.Fascfeat;
import fa.model.FascfeatPK;

public class FascfeatEjb implements FascfeatEjbI{

	private Context context;
	private FascfeatDaoRemote dao; 
	private String region=null;
	
	public FascfeatEjb(String region) {
		super();
		this.region=region;
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
	public void createFascfeat(Fascfeat fascfeat) {
		dao.createFascfeat(fascfeat, region);
		
	}

	@Override
	public void updateFascfeat(Fascfeat fascfeat) {
		dao.updateFascfeat(fascfeat, region);
		
	}

	@Override
	public void removeFascfeat(Fascfeat fascfeat) {
		dao.removeFascfeat(fascfeat, region);
		
	}

	@Override
	public List<Fascfeat> getAll() {
		return dao.getAll(region);
	}

	@Override
	public void removeAll() {
		dao.removeAll(region);
		
	}

	@Override
	public Fascfeat findById(FascfeatPK id)
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
