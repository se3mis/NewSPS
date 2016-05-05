package estimate.service;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.PersistenceException;

import estimate.ejb.SpcndctmDaoRemote;
import estimate.model.Spcndctm;
import estimate.model.SpcndctmPK;

public class SpcndctmEjb implements SpcndctmEjbI {
	private Context context;
	private SpcndctmDaoRemote dao; 
	private String region=null;
	
	
	public SpcndctmEjb(String region) {
		super();
		this.region=region;
		this.dao=lookupDao();
		
	}
	
	private SpcndctmDaoRemote lookupDao() {
		try
		{
			 context = new InitialContext();
			 SpcndctmDaoRemote dao = (SpcndctmDaoRemote) context.lookup("SpcndctmDao/remote");
			 return dao; 
		} catch (NamingException e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}
	

	@Override
	public void createSpcndctm(Spcndctm spcndctm) {
		dao.createSpcndctm(spcndctm, region);
		
	}

	@Override
	public List<Spcndctm> getAll() {
		return dao.getAll(region);
	}

	@Override
	public void updateSpcndctm(Spcndctm spcndctm) {
		dao.updateSpcndctm(spcndctm, region);
		
	}

	@Override
	public void removeSpcndctm(Spcndctm spcndctm) {
		dao.removeSpcndctm(spcndctm, region);
		
	}

	@Override
	public void removeAll() {
		dao.removeAll(
				region);
		
	}

	@Override
	public Spcndctm findById(SpcndctmPK id) throws PersistenceException {
		return dao.findById(id, region);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpcndctmEjb ejb= new SpcndctmEjb("region");
		System.out.println(ejb);

	}

}
