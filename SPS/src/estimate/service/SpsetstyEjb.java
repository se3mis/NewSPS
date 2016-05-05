package estimate.service;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.PersistenceException;

import estimate.ejb.SpsetstyDaoRemote;
import estimate.model.Spsetsty;
import estimate.model.SpsetstyPK;

public class SpsetstyEjb implements SpsetstyEjbI{
	private Context context;
	private SpsetstyDaoRemote dao; 
	private String region=null;
	
	public SpsetstyEjb(String region) {
		super();
		this.region=region;
		this.dao=lookupDao();
		
	}
	
	private SpsetstyDaoRemote lookupDao() {
		try
		{
			 context = new InitialContext();
			 SpsetstyDaoRemote dao = (SpsetstyDaoRemote) context.lookup("SpsetstyDao/remote");
			 return dao; 
		} catch (NamingException e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}

	

	@Override
	public void createSpsetsty(Spsetsty Spsetsty) {
		dao.createSpsetsty(Spsetsty, region);
		
	}

	@Override
	public List<Spsetsty> getAll(String deptId) {
		return dao.getAll(deptId, region);
	}

	@Override
	public void updateSpsetsty(Spsetsty Spsetsty) {
		dao.updateSpsetsty(Spsetsty, region);
		
	}

	@Override
	public void removeSpsetsty(Spsetsty Spsetsty) {
		dao.removeSpsetsty(Spsetsty, region);
		
	}

	@Override
	public void removeAll() {
		dao.removeAll(region);
		
	}

	@Override
	public Spsetsty findById(SpsetstyPK id) throws PersistenceException {
		return dao.findById(id, region);
	}

	@Override
	public List<Spsetsty> getStayList(String applicationNo, String deptId) {
		return dao.getStayList(applicationNo, deptId, region);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpsetstyEjb ejb=new SpsetstyEjb("region");
		System.err.println(ejb);

	}

}
