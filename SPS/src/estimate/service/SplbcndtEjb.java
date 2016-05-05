package estimate.service;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.PersistenceException;

import estimate.ejb.SplbcndtDaoRemote;
import estimate.model.Splbcndt;
import estimate.model.SplbcndtPK;

public class SplbcndtEjb implements SplbcndtEjbI{
	private Context context;
	private SplbcndtDaoRemote dao;
	private String region=null;
	
	public SplbcndtEjb(String region) {
		super();
		this.region=region;
		this.dao=lookupDao();
		
	}
	
	private SplbcndtDaoRemote lookupDao() {
		try
		{
			 context = new InitialContext();
			 SplbcndtDaoRemote dao = (SplbcndtDaoRemote) context.lookup("SplbcndtDao/remote");
			 return dao; 
		} catch (NamingException e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}
	

	@Override
	public void createSplbcndt(Splbcndt splbcndt) {
		dao.createSplbcndt(splbcndt, region);
		
	}

	@Override
	public List<Splbcndt> getAll(String deptId) {
		return dao.getAll(deptId, region);
	}

	@Override
	public void updateSplbcndt(Splbcndt splbcndt) {
		dao.updateSplbcndt(splbcndt, region);
		
	}

	@Override
	public void removeSplbcndt(Splbcndt splbcndt) {
		dao.removeSplbcndt(splbcndt, region);
		
	}

	@Override
	public void removeAll() {
		dao.removeAll(region);
		
	}

	@Override
	public Splbcndt findById(SplbcndtPK id) throws PersistenceException {
		return dao.findById(id, region);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SplbcndtEjb ejb=new SplbcndtEjb("region");

	}

}
