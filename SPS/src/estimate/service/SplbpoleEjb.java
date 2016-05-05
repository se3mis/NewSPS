package estimate.service;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.PersistenceException;

import estimate.ejb.SplbpoleDaoRemote;
import estimate.model.Splbpole;
import estimate.model.SplbpolePK;

public class SplbpoleEjb implements SplbpoleEjbI{
	private Context context;
	private SplbpoleDaoRemote dao; 
	private String region=null;
	
	public SplbpoleEjb(String region) {
		super();
		this.region=region;
		this.dao=lookupDao();
		
	}
	
	private SplbpoleDaoRemote lookupDao() {
		try
		{
			 context = new InitialContext();
			 SplbpoleDaoRemote dao = (SplbpoleDaoRemote) context.lookup("SplbpoleDao/remote");
			 return dao; 
		} catch (NamingException e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}
	

	@Override
	public void createSplbpole(Splbpole splbpole) {
		dao.createSplbpole(splbpole, region);
		
	}

	@Override
	public List<Splbpole> getAll(String deptId) {
		return dao.getAll(deptId, region);
	}

	@Override
	public void updateSplbpole(Splbpole splbpole) {
		dao.updateSplbpole(splbpole, region);
		
	}

	@Override
	public void removeSplbpole(Splbpole splbpole) {
		dao.removeSplbpole(splbpole, region);
		
	}

	@Override
	public void removeAll() {
		dao.removeAll(region);
		
	}

	@Override
	public Splbpole findById(SplbpolePK id) throws PersistenceException {
		return dao.findById(id, region);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SplbpoleEjb ejb=new SplbpoleEjb("region");

	}

}
