package estimate.service;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.PersistenceException;

import estimate.ejb.PcjbtypmDaoRemote;
import estimate.model.Pcjbtypm;
//import estimate.model.Pcjbtypm;
//import estimate.model.PcjbtypmPK;
import estimate.model.PcjbtypmPK;

public class PcjbtypmEjb implements PcjbtypmEjbI{
	private Context context;
	private PcjbtypmDaoRemote dao; 
	private String region=null;
	
	public PcjbtypmEjb(String region) {
		super();
		this.region=region;
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
	public void createPcjbtypm(Pcjbtypm pcjbtypm) {
		dao.createPcjbtypm(pcjbtypm, region);
		
	}

	@Override
	public List<Pcjbtypm> getAll() {
		return dao.getAll(region);
	}

	@Override
	public List<Pcjbtypm> getAll(String deptId) {
		return dao.getAll(deptId, region);
	}

	@Override
	public List<String> getCatCode(String deptId) {
		return dao.getCatCode(deptId, region);
	}

	@Override
	public void updatePcjbtypm(Pcjbtypm pcjbtypm) {
		dao.updatePcjbtypm(pcjbtypm, region);
		
	}

	@Override
	public void removePcjbtypm(Pcjbtypm pcjbtypm) {
		dao.removePcjbtypm(pcjbtypm, region);
		
	}

	@Override
	public void removeAll() {
		dao.removeAll(region);
		
	}

	@Override
	public Pcjbtypm findById(PcjbtypmPK id) throws PersistenceException {
		return dao.findById(id, region);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PcjbtypmEjb ejb=new PcjbtypmEjb("region");
		System.out.println(ejb.getAll("440.20"));
		System.out.println(ejb.getCatCode("440.20"));

	}

}
