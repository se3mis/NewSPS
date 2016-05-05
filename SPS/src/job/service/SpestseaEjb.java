package job.service;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.PersistenceException;

import job.ejb.SpestseaDaoRemote;
import job.model.Spestsea;
import job.model.SpestseaPK;

public class SpestseaEjb implements SpestseaEjbI {
	private Context context;
	private SpestseaDaoRemote dao;
	private String region=null;
	
	public SpestseaEjb(String region) {
		super();
		this.region=region;
		this.dao=lookupDao();
		
	}

	private SpestseaDaoRemote lookupDao() {
		try
		{
			 context = new InitialContext();
			 SpestseaDaoRemote dao = (SpestseaDaoRemote) context.lookup("SpestseaDao/remote");
			 return dao; 
		} catch (NamingException e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}

	

	@Override
	public void createSpestsea(Spestsea spestsea) {
		dao.createSpestsea(spestsea, region);
		
	}

	@Override
	public List<Spestsea> getAll(String deptId) {
		return dao.getAll(deptId, region);
	}

	@Override
	public void updateSpestsea(Spestsea spestsea) {
		dao.updateSpestsea(spestsea, region);
		
	}

	@Override
	public void removeSpestsea(Spestsea spestsea) {
		dao.removeSpestsea(spestsea, region);
		
	}

	@Override
	public void removeAll() {
		dao.removeAll(region);
		
	}

	@Override
	public Spestsea findById(SpestseaPK id) throws PersistenceException {
		return dao.findById(id, region);
	}

	@Override
	public List<Spestsea> getSealNoList(String jobNo, String deptId) throws PersistenceException {
		return dao.getSealNoList(jobNo, deptId, region);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpestseaEjb ejb=new SpestseaEjb("region");
		ejb.getAll("510.20");

	}

	@Override
	public Spestsea findByJobNo(String jobNo) {
		return dao.findByJobNo(jobNo, region);
	}

}
