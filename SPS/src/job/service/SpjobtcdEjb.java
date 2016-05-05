package job.service;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.PersistenceException;

import job.ejb.SpjobtcdDaoRemote;
import job.model.Spjobtcd;



public class SpjobtcdEjb implements SpjobtcdEjbI {

	private Context context;
	private SpjobtcdDaoRemote dao;
	private String region=null;
	
	public SpjobtcdEjb(String region) {
		super();
		this.region=region;
		this.dao=lookupDao();
		
	}

	private SpjobtcdDaoRemote lookupDao() {
		try
		{
			 context = new InitialContext();
			 SpjobtcdDaoRemote dao = (SpjobtcdDaoRemote) context.lookup("SpjobtcdDao/remote");
			 return dao; 
		} catch (NamingException e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}

	

	@Override
	public void createSpjobtcd(Spjobtcd spjobtcd) {
		dao.createSpjobtcd(spjobtcd, region);
		
	}

	@Override
	public List<Spjobtcd> getAll(String deptId) {
		return dao.getAll(deptId, region);
	}

	@Override
	public void updateSpjobtcd(Spjobtcd spjobtcd) {
		dao.updateSpjobtcd(spjobtcd, region);
		
	}

	@Override
	public void removeSpjobtcd(Spjobtcd spjobtcd) {
		dao.removeSpjobtcd(spjobtcd, region);
		
	}

	@Override
	public void removeAll() {
		dao.removeAll(region);
		
	}

	@Override
	public Spjobtcd findById(String jobNo, String deptId) throws PersistenceException {
		return dao.findById(jobNo, deptId, region);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpjobtcdEjb ejb=new SpjobtcdEjb("region");
		System.out.println(ejb);
		System.out.println(ejb.getAll("510.20"));
		System.out.println(ejb.findById("dd", "deptId"));


	}

}
