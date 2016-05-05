package estimate.service;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.PersistenceException;

import estimate.ejb.PcjbtypmDaoRemote;
import estimate.model.Pcjbtypm;
import estimate.model.PcjbtypmPK;

public class PcjbtypmEjb implements PcjbtypmDaoRemote {
	private Context context;
	private PcjbtypmDaoRemote dao; 
	public PcjbtypmEjb() {
		super();
		this.dao=lookupDao();
		
	}
	
	private PcjbtypmDaoRemote lookupDao() {
		try
		{
			 context = new InitialContext();
			 PcjbtypmDaoRemote dao = (PcjbtypmDaoRemote) context.lookup("PcjbtypmDao/remote");
			 return dao; 
		} catch (NamingException e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}
	
	@Override
	public void createPcjbtypm(Pcjbtypm pcjbtypm, String webAppName) {
		dao.createPcjbtypm(pcjbtypm, webAppName);
		
	}

	@Override
	public List<Pcjbtypm> getAll(String webAppName) {
		return dao.getAll(webAppName);
	}

	@Override
	public List<Pcjbtypm> getAll(String deptId, String webAppName) {
		return dao.getAll(deptId, webAppName);
	}

	@Override
	public List<String> getCatCode(String deptId, String webAppName) {
		return dao.getCatCode(deptId, webAppName);
	}

	@Override
	public void updatePcjbtypm(Pcjbtypm pcjbtypm, String webAppName) {
		dao.updatePcjbtypm(pcjbtypm, webAppName);
		
	}

	@Override
	public void removePcjbtypm(Pcjbtypm pcjbtypm, String webAppName) {
		dao.removePcjbtypm(pcjbtypm, webAppName);
		
	}

	@Override
	public void removeAll(String webAppName) {
		dao.removeAll(webAppName);
		
	}

	@Override
	public Pcjbtypm findById(PcjbtypmPK id, String webAppName) throws PersistenceException {
		return dao.findById(id, webAppName);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PcjbtypmEjb ejb=new PcjbtypmEjb();
		System.out.println(ejb.getCatCode("440.20", "SMCTesting" ));
	}
	

}
