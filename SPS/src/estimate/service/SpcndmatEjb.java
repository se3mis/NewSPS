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

public class SpcndmatEjb implements SpcndmatEjbI {
	private Context context;
	private SpcndmatDaoRemote dao; 
	private String region=null;
	
	public SpcndmatEjb(String region) {
		super();
		this.region=region;
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
	

	@Override
	public void createSpcndmat(Spcndmat spcndmat) {
		dao.createSpcndmat(spcndmat, region);
		
	}

	@Override
	public List<Spcndmat> getAll() {
		return dao.getAll(region);
	}

	@Override
	public void updateSpcndmat(Spcndmat spcndmat) {
		dao.updateSpcndmat(spcndmat, region);
		
	}

	@Override
	public void removeSpcndmat(Spcndmat spcndmat) {
		dao.removeSpcndmat(spcndmat, region);
		
	}

	@Override
	public void removeAll() {
		dao.removeAll(region);
		
	}

	@Override
	public Spcndmat findById(SpcndmatPK id) throws PersistenceException {
		return dao.findById(id, region);
	}

	@Override
	public List<MaterialGrid> getConductorMaterialGrid(String deptId,
			long phase, long connectionType, String wiringType,
			String conductorType, Double conductorLength)
			throws PersistenceException {
		return dao.getConductorMaterialGrid(deptId, phase, connectionType, wiringType, conductorType, conductorLength, region);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpcndmatEjb ejb=new SpcndmatEjb("region");
		System.err.println(ejb);

	}

}
