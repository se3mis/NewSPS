package estimate.service;

import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.PersistenceException;

import estimate.ejb.SpestpemDaoRemote;
import estimate.model.Spestpem;
import estimate.model.SpestpemPK;

public class SpestpemEjb implements SpestpemDaoRemote {
	private Context context;
	private SpestpemDaoRemote dao; 
	public SpestpemEjb() {
		super();
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
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpestpemEjb ejb=new SpestpemEjb();
		System.out.println(ejb);
		System.out.println(ejb.getAll("SMCTesting"));
		SpestpemPK id=new SpestpemPK();
		id.setApplicationNo("510.20/SNL/2010/0005");
		id.setDeptId("510.20");
		id.setPermissionType("PSD");
		System.out.println(ejb.findById(id, "SMCTesting"));
		List<SpestpemPK> list=new ArrayList<SpestpemPK>();
		list.add(id);
		System.out.println(ejb.getPermissions("510.20/SNL/2010/0005", "510.20", "SMCTesting"));
		//ejb.removeSpestpem(list);

	}

	@Override
	public void createSpestpem(Spestpem spestpem, String webAppName) {
		dao.createSpestpem(spestpem, webAppName);
		
	}

	@Override
	public void createSpestpem(List<Spestpem> list, String webAppName) {
		dao.createSpestpem(list, webAppName);
		
	}

	@Override
	public List<Spestpem> getAll(String webAppName) {
		return dao.getAll(webAppName);
	}

	@Override
	public void updateSpestpem(Spestpem spestpem, String webAppName) {
		dao.updateSpestpem(spestpem, webAppName);
		
	}

	@Override
	public void removeSpestpem(Spestpem spestpem, String webAppName) {
		dao.removeSpestpem(spestpem, webAppName);
		
	}

	@Override
	public void removeAll(String webAppName) {
		dao.removeAll(webAppName);
		
	}

	@Override
	public List<Spestpem> findById(SpestpemPK id, String webAppName) throws PersistenceException {
		return dao.findById(id, webAppName);
	}

	@Override
	public void removeSpestpem(List<SpestpemPK> list, String webAppName) {
		dao.removeSpestpem(list, webAppName);
		
	}

	@Override
	public List<Spestpem> getPermissions(String applicationNo, String deptId, String webAppName) throws PersistenceException {
		return dao.getPermissions(applicationNo, deptId, webAppName);
	}

}
