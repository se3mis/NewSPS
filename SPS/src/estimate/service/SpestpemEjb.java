package estimate.service;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.PersistenceException;

import estimate.ejb.SpestpemDaoRemote;
import estimate.model.Spestpem;
import estimate.model.SpestpemPK;

public class SpestpemEjb implements SpestpemEjbI{

	private Context context;
	private SpestpemDaoRemote dao;
	private String region=null;
	
	public SpestpemEjb(String region) {
		super();
		this.region=region;
		this.dao=lookupDao();
		
	}
	
	private SpestpemDaoRemote lookupDao() {
		try
		{
			 context = new InitialContext();
			 SpestpemDaoRemote dao = (SpestpemDaoRemote) context.lookup("SpestpemDao/remote");
			 return dao; 
		} catch (NamingException e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}
	

	@Override
	public void createSpestpem(Spestpem spestpem) {
		dao.createSpestpem(spestpem, region);
		
	}

	@Override
	public void createSpestpem(List<Spestpem> list) {
		dao.createSpestpem(list, region);
		
	}

	@Override
	public List<Spestpem> getAll() {
		return dao.getAll(region);
	}

	@Override
	public void updateSpestpem(Spestpem spestpem) {
		dao.updateSpestpem(spestpem, region);
		
	}

	@Override
	public void removeSpestpem(Spestpem spestpem) {
		dao.removeSpestpem(spestpem, region);
		
	}

	@Override
	public void removeAll() {
		dao.removeAll(region);
		
	}

	@Override
	public List<Spestpem> findById(SpestpemPK id) throws PersistenceException {
		return dao.findById(id, region);
	}

	@Override
	public void removeSpestpem(List<SpestpemPK> list) {
		dao.removeSpestpem(list, region);
		
	}

	@Override
	public List<Spestpem> getPermissions(String applicationNo, String deptId) throws PersistenceException {
		return dao.getPermissions(applicationNo, deptId, region);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpestpemEjb ejb=new SpestpemEjb("region");
		System.out.println(ejb);
		System.out.println(ejb.getAll());
		SpestpemPK id=new SpestpemPK();
		id.setApplicationNo("macsa");
		id.setDeptId("510.20");
		System.out.println(ejb.findById(id));

	}

}
