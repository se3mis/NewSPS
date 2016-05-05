package estimate.service;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.PersistenceException;

import estimate.ejb.SpsetstuDaoRemote;
import estimate.model.Spsetstu;
import estimate.model.SpsetstuPK;

public class SpsetstuEjb implements SpsetstuEjbI{
	private Context context;
	private SpsetstuDaoRemote dao; 
	private String region=null;
	
	public SpsetstuEjb(String region) {
		super();
		this.region=region;
		this.dao=lookupDao();
		
	}
	
	private SpsetstuDaoRemote lookupDao() {
		try
		{
			 context = new InitialContext();
			 SpsetstuDaoRemote dao = (SpsetstuDaoRemote) context.lookup("SpsetstuDao/remote");
			 return dao; 
		} catch (NamingException e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}

	

	@Override
	public void createSpsetstu(Spsetstu Spsetstu) {
		dao.createSpsetstu(Spsetstu, region);
		
	}

	@Override
	public List<Spsetstu> getAll(String deptId) {
		return dao.getAll(deptId, region);
	}

	@Override
	public void updateSpsetstu(Spsetstu Spsetstu) {
		dao.updateSpsetstu(Spsetstu, region);
		
	}

	@Override
	public void removeSpsetstu(Spsetstu Spsetstu) {
		dao.removeSpsetstu(Spsetstu, region);
		
	}

	@Override
	public void removeAll() {
		dao.removeAll(region);
		
	}

	@Override
	public Spsetstu findById(SpsetstuPK id) throws PersistenceException {
		return dao.findById(id, region);
	}

	@Override
	public List<Spsetstu> getStrutList(String applicationNo, String deptId)
			throws PersistenceException {
		return dao.getStrutsList(applicationNo, deptId, region);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpsetstuEjb ejb=new SpsetstuEjb("region");
		System.err.println(ejb);

	}

	


}
