package estimate.service;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.PersistenceException;


import estimate.ejb.SplabratDaoRemote;
import estimate.model.LabourGrid;
import estimate.model.Splabrat;
import estimate.model.SplabratPK;

public class SplabratEjb implements SplabratEjbI {
	private Context context;
	private SplabratDaoRemote dao; 
	private String region=null;
	
	public SplabratEjb(String region) {
		super();
		this.region=region;
		this.dao=lookupDao();
		
	}
	
	private SplabratDaoRemote lookupDao() {
		try
		{
			 context = new InitialContext();
			 SplabratDaoRemote dao = (SplabratDaoRemote) context.lookup("SplabratDao/remote");
			 return dao; 
		} catch (NamingException e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}
	

	@Override
	public void createSplabrat(Splabrat splabrat) {
		dao.createSplabrat(splabrat, region);
		
	}

	@Override
	public List<Splabrat> getAll(String deptId) {
		return dao.getAll(deptId, region);
	}

	@Override
	public void updateSplabrat(Splabrat splabrat) {
		dao.updateSplabrat(splabrat, region);
		
	}

	@Override
	public void removeSplabrat(Splabrat splabrat) {
		dao.removeSplabrat(splabrat, region);
		
	}

	@Override
	public void removeAll() {
		dao.removeAll(region);
		
	}

	@Override
	public Splabrat findById(SplabratPK id) throws PersistenceException {
		return dao.findById(id, region);
	}

	@Override
	public List<LabourGrid> getLabourGrid(String deptId, String applicationType) {
		return dao.getLabourGrid(deptId,applicationType, region);
	}

	@Override
	public List<LabourGrid> getLabourGrid(String deptId,
			String applicationType, List<String> labourCodeLst) {
		return dao.getLabourGrid(deptId, applicationType, labourCodeLst, region);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SplabratEjb ejb=new SplabratEjb("slfnsdjk");

	}

}
