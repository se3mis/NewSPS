package estimate.service;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.PersistenceException;

import estimate.ejb.SpestmtmDaoRemote;
import estimate.model.Spestmtm;
import estimate.model.SpestmtmPK;

public class SpestmtmEjb implements SpestmtmEjbI{
	private Context context;
	private SpestmtmDaoRemote dao;
	private String region=null;
	
	public SpestmtmEjb(String region) {
		super();
		this.region=region;
		this.dao=lookupDao();
		
	}
	
	private SpestmtmDaoRemote lookupDao() {
		try
		{
			 context = new InitialContext();
			 SpestmtmDaoRemote dao = (SpestmtmDaoRemote) context.lookup("SpestmtmDao/remote");
			 return dao; 
		} catch (NamingException e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}

	
	

	@Override
	public void createSpestmtm(Spestmtm spestmtm) {
		dao.createSpestmtm(spestmtm, region);
		
	}

	@Override
	public List<Spestmtm> getAll() {
		return dao.getAll(region);
	}

	@Override
	public void updateSpestmtm(Spestmtm spestmtm) {
		dao.updateSpestmtm(spestmtm, region);
		
	}

	@Override
	public void removeSpestmtm(Spestmtm spestmtm) {
		dao.removeSpestmtm(spestmtm, region);
		
	}

	@Override
	public void removeAll() {
		dao.removeAll(region);
		
	}

	@Override
	public Spestmtm findById(SpestmtmPK id) throws PersistenceException {
		return dao.findById(id, region);
	}

	@Override
	public List<Spestmtm> estimateMaterials(Long phase, Long connectionType, String wiringType, String deptId) {
		return dao.estimateMaterials(phase, connectionType, wiringType, deptId, region);
	}
	
	@Override
	public List<Spestmtm> getAll(String deptId) {
		return dao.getAll(deptId, region);
	}

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpestmtmEjb ejb=new SpestmtmEjb("region");
		System.out.print(ejb.estimateMaterials(new Long("1"), new Long("15"), "UG","510.20"));
		

	}

	
}
