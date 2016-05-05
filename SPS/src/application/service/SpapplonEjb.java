package application.service;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import application.ejb.SpapplonDaoRemote;
import application.model.Spapplon;
import application.model.SpapplonPK;

public class SpapplonEjb implements SpapplonEjbI{
	private Context context;
	private SpapplonDaoRemote dao; 
	private String region=null;
	
	
	public SpapplonEjb(String region) {
		super();
		this.region=region;
		this.dao=lookupDao();
	}

	private SpapplonDaoRemote lookupDao() {
		try
		{
			 context = new InitialContext();
			 SpapplonDaoRemote dao = (SpapplonDaoRemote) context.lookup("SpapplonDao/remote");
			 //System.out.println(dao);
			 return dao; 
		} catch (NamingException e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}
	

	@Override
	public void createSpapplon(Spapplon spapplon) {
		dao.createSpapplon(spapplon, region);
		
	}

	@Override
	public void updateSpapplon(Spapplon spapplon) {
		dao.updateSpapplon(spapplon, region);
		
	}

	@Override
	public void removeSpapplon(Spapplon spapplon) {
		dao.removeSpapplon(spapplon, region);
		
	}

	@Override
	public void removeAll() {
		dao.removeAll(region);
		
	}

	@Override
	public List<Spapplon> getAll() {
		return dao.getAll(region);
	}

	@Override
	public List<Spapplon> getAll(String deptId) {
		return dao.getAll(deptId, region);
	}

	@Override
	public Spapplon findById(SpapplonPK id)  {
		return dao.findById(id, region);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpapplonEjb ejb=new SpapplonEjb("Testing");
		SpapplonPK id=new SpapplonPK();
		id.setDeptId("510.20");
		id.setApplicationNo("ANC/510.20/2011/0001");
		System.out.println(ejb.findById(id));
	}

	@Override
	public Spapplon findByApplicationNo(String applicationNo) {
		return dao.findByApplicationNo(applicationNo, region);
	}
}
