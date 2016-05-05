package estimate.service;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.PersistenceException;

import estimate.ejb.SpcndmatDaoRemote;
import estimate.model.MaterialGrid;
import estimate.model.Spcndmat;
import estimate.model.SpcndmatPK;

public class SpcndmatEjb implements  SpcndmatDaoRemote{
	private Context context;
	private SpcndmatDaoRemote dao; 
	public SpcndmatEjb() {
		super();
		this.dao=lookupDao();
		
	}
	
	private SpcndmatDaoRemote lookupDao() {
		try
		{
			 context = new InitialContext();
			 SpcndmatDaoRemote dao = (SpcndmatDaoRemote) context.lookup("SpcndmatDao/remote");
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
		SpcndmatEjb ejb=new SpcndmatEjb();
		System.err.println(ejb.getAll("SMCTesing"));
		System.err.println(ejb.getConductorMaterialGrid("514.20", 1, 30, "OH", "FLY", 6.00,"SMCTesting"));

	}

	@Override
	public void createSpcndmat(Spcndmat spcndmat, String webAppName) {
		dao.createSpcndmat(spcndmat, webAppName);
		
	}

	@Override
	public List<Spcndmat> getAll(String webAppName) {
		return dao.getAll(webAppName);
	}

	@Override
	public void updateSpcndmat(Spcndmat spcndmat, String webAppName) {
		dao.updateSpcndmat(spcndmat, webAppName);
		
	}

	@Override
	public void removeSpcndmat(Spcndmat spcndmat, String webAppName) {
		dao.removeSpcndmat(spcndmat, webAppName);
		
	}

	@Override
	public void removeAll(String webAppName) {
		dao.removeAll(webAppName);
		
	}

	@Override
	public Spcndmat findById(SpcndmatPK id, String webAppName) throws PersistenceException {
		return dao.findById(id, webAppName);
	}

	@Override
	public List<MaterialGrid> getConductorMaterialGrid(String deptId, long phase,
			long connectionType, String wiringType, String conductorType,
			Double conductorlength, String webAppName) throws PersistenceException {
		return dao.getConductorMaterialGrid(deptId, phase, connectionType, wiringType, conductorType, conductorlength, webAppName);
	}

}
