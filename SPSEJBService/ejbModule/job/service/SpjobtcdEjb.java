package job.service;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.PersistenceException;


import job.ejb.SpjobtcdDaoRemote;
import job.model.Spjobtcd;

public class SpjobtcdEjb  implements SpjobtcdDaoRemote{
	private Context context;
	private SpjobtcdDaoRemote dao; 
	public SpjobtcdEjb() {
		super();
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

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpjobtcdEjb ejb=new SpjobtcdEjb();
		System.out.println(ejb);
		System.out.println(ejb.getAll("510.20", "SMCTesting"));
		System.out.println(ejb.findById("dd", "deptId", "SMCTesting"));

	}

	

	

	@Override
	public void removeAll(String webAppName) {
		dao.removeAll(webAppName);
		
	}

	

	@Override
	public void createSpjobtcd(Spjobtcd spjobtcd, String webAppName) {
		dao.createSpjobtcd(spjobtcd, webAppName);
		
	}

	@Override
	public void updateSpjobtcd(Spjobtcd spjobtcd, String webAppName) {
		dao.updateSpjobtcd(spjobtcd, webAppName);
		
	}

	@Override
	public void removeSpjobtcd(Spjobtcd spjobtcd, String webAppName) {
		dao.removeSpjobtcd(spjobtcd, webAppName);
		
	}

	@Override
	public List<Spjobtcd> getAll(String deptId, String webAppName) {
		return dao.getAll(deptId, webAppName);
	}

	@Override
	public Spjobtcd findById(String jobNo, String deptId, String webAppName) throws PersistenceException {
		return dao.findById(jobNo, deptId, webAppName);
	}

}
