package estimate.service;

import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.PersistenceException;

import estimate.ejb.SplabratDaoRemote;
import estimate.model.LabourGrid;
import estimate.model.Splabrat;
import estimate.model.SplabratPK;

public class SplabratEjb implements SplabratDaoRemote{
	private Context context;
	private SplabratDaoRemote dao; 
	public SplabratEjb() {
		super();
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
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SplabratEjb ejb=new SplabratEjb();
		List<String> list=new ArrayList<String>();
		//list=null;
		list.add("LC1");
		//list.
		//System.out.println(ejb.getLabourGrid("514.20", "NC", "bdfdfg").size());
		//System.out.println(ejb.getLabourGrid("514.20", "NC",list, "bdfdfg").size());
		ejb.insertLabour("517.10", "551.10", "R3");
	}

	@Override
	public void createSplabrat(Splabrat splabrat, String webAppName) {
		dao.createSplabrat(splabrat, webAppName);
		
	}

	@Override
	public List<Splabrat> getAll(String deptId, String webAppName) {
		return dao.getAll(deptId, webAppName);
	}

	@Override
	public void updateSplabrat(Splabrat splabrat, String webAppName) {
		dao.updateSplabrat(splabrat, webAppName);
		
	}

	@Override
	public void removeSplabrat(Splabrat splabrat, String webAppName) {
		dao.removeSplabrat(splabrat, webAppName);
		
	}

	@Override
	public void removeAll(String webAppName) {
		dao.removeAll(webAppName);
		
	}

	@Override
	public Splabrat findById(SplabratPK id, String webAppName) throws PersistenceException {
		return dao.findById(id, webAppName);
	}

	@Override
	public List<LabourGrid> getLabourGrid(String deptId, String applicationType, String webAppName) {
		return dao.getLabourGrid(deptId, applicationType, webAppName);
	}

	@Override
	public List<LabourGrid> getLabourGrid(String deptId,
			String applicationType, List<String> labourCodeList, String webAppName) {
		return dao.getLabourGrid(deptId, applicationType, labourCodeList, webAppName);
	}

	@Override
	public void insertLabour(String fromDeptId, String todeptId,
			String webAppName) {
		dao.insertLabour(fromDeptId, todeptId, webAppName);
		
	}

}
