package fa.service;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.PersistenceException;

import fa.ejb.FamcfeatDaoRemote;
import fa.model.Famcfeat;
import fa.model.FamcfeatPK;

public class FamcfeatEjb implements FamcfeatEjbI{

	private Context context;
	private FamcfeatDaoRemote dao; 
	private String region=null;
	
	public FamcfeatEjb(String region) {
		super();
		this.region=region;
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
	public void createFamcfeat(Famcfeat famcfeat) {
		dao.createFamcfeat(famcfeat, region);
		
	}

	@Override
	public void updateFamcfeat(Famcfeat famcfeat) {
		dao.updateFamcfeat(famcfeat, region);
		
	}

	@Override
	public void removeFamcfeat(Famcfeat famcfeat) {
		dao.removeFamcfeat(famcfeat, region);
		
	}

	@Override
	public List<Famcfeat> getAll() {
		return dao.getAll(region);
	}

	@Override
	public void removeAll() {
		dao.removeAll(region);
		
	}

	@Override
	public Famcfeat findById(FamcfeatPK id)
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
