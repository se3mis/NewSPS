package estimate.service;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.PersistenceException;

import estimate.ejb.SpcndctmDaoRemote;
import estimate.model.Spcndctm;
import estimate.model.SpcndctmPK;

public class SpcndctmEjb implements SpcndctmDaoRemote {
	private Context context;
	private SpcndctmDaoRemote dao; 
	public SpcndctmEjb() {
		super();
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
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpcndctmEjb ejb=new SpcndctmEjb();
		SpcndctmPK id=new SpcndctmPK();
		id.setPhase(1);
		id.setConductorType("ABC");
		System.out.println(ejb.findById(id,"SMCTesting"));

	}

	@Override
	public void createSpcndctm(Spcndctm spcndctm, String webAppName) {
		dao.createSpcndctm(spcndctm, webAppName);
		
	}

	@Override
	public List<Spcndctm> getAll(String webAppName) {
		return dao.getAll(webAppName);
	}

	@Override
	public void updateSpcndctm(Spcndctm spcndctm, String webAppName) {
		dao.updateSpcndctm(spcndctm, webAppName);
		
	}

	@Override
	public void removeSpcndctm(Spcndctm spcndctm, String webAppName) {
		dao.removeSpcndctm(spcndctm, webAppName);
		
	}

	@Override
	public void removeAll(String webAppName) {
		dao.removeAll(webAppName);
		
	}

	@Override
	public Spcndctm findById(SpcndctmPK id, String webAppName) throws PersistenceException {
		return dao.findById(id, webAppName);
	}

	

}
