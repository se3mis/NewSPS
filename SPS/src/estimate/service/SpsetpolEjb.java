package estimate.service;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.PersistenceException;

import estimate.ejb.SpsetpolDaoRemote;
import estimate.model.Spsetpol;
import estimate.model.SpsetpolPK;

public class SpsetpolEjb implements SpsetpolEjbI{
	private Context context;
	private SpsetpolDaoRemote dao; 
	private String region=null;
	
	public SpsetpolEjb(String region) {
		super();
		this.region=region;
		this.dao=lookupDao();
		
	}
	
	private SpsetpolDaoRemote lookupDao() {
		try
		{
			 context = new InitialContext();
			 SpsetpolDaoRemote dao = (SpsetpolDaoRemote) context.lookup("SpsetpolDao/remote");
			 return dao; 
		} catch (NamingException e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}

	

	@Override
	public void createSpsetpol(Spsetpol Spsetpol) {
		dao.createSpsetpol(Spsetpol, region);
		
	}

	@Override
	public List<Spsetpol> getAll(String deptId) {
		return dao.getAll(deptId, region);
	}

	@Override
	public void updateSpsetpol(Spsetpol Spsetpol) {
		dao.updateSpsetpol(Spsetpol, region);
		
	}

	@Override
	public void removeSpsetpol(Spsetpol Spsetpol) {
		dao.removeSpsetpol(Spsetpol, region);
		
	}

	@Override
	public void removeAll() {
		dao.removeAll(region);
		
	}

	@Override
	public Spsetpol findById(SpsetpolPK id) throws PersistenceException {
		return dao.findById(id, region);
	}

	@Override
	public List<Spsetpol> getPoleList(String applicationNo, String deptId)
			throws PersistenceException {
		return dao.getPoleList(applicationNo, deptId, region);
	}

	@Override
	public void createSpsetpolList(List<Spsetpol> list) {
		dao.createSpsetpolList(list, region);
		
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpsetpolEjb ejb=new SpsetpolEjb("region");
		System.err.println(ejb);

	}

}
