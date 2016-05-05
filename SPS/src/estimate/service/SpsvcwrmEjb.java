package estimate.service;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.PersistenceException;

import estimate.ejb.SpsvcwrmDaoRemote;
import estimate.model.MaterialGrid;
import estimate.model.Spsvcwrm;
import estimate.model.SpsvcwrmPK;

public class SpsvcwrmEjb implements SpsvcwrmEjbI {
	private Context context;
	private SpsvcwrmDaoRemote dao; private String region=null;
	
	public SpsvcwrmEjb(String region) {
		super();
		this.region=region;
		this.dao=lookupDao();
		
	}
	
	private SpsvcwrmDaoRemote lookupDao() {
		try
		{
			 context = new InitialContext();
			 SpsvcwrmDaoRemote dao = (SpsvcwrmDaoRemote) context.lookup("SpsvcwrmDao/remote");
			 return dao; 
		} catch (NamingException e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}
	

	@Override
	public void createSpsvcwrm(Spsvcwrm spsvcwrm) {
		dao.createSpsvcwrm(spsvcwrm, region);
		
	}

	@Override
	public List<Spsvcwrm> getAll() {
		return dao.getAll(region);
	}

	@Override
	public void updateSpsvcwrm(Spsvcwrm spsvcwrm) {
		dao.updateSpsvcwrm(spsvcwrm, region);
		
	}

	@Override
	public void removeSpsvcwrm(Spsvcwrm spsvcwrm) {
		dao.removeSpsvcwrm(spsvcwrm, region);
		
	}

	@Override
	public void removeAll() {
		dao.removeAll(region);
		
	}

	@Override
	public Spsvcwrm findById(SpsvcwrmPK id) throws PersistenceException {
		return dao.findById(id, region);
	}

	@Override
	public List<MaterialGrid> getServiceWireMaterialGrid(long phase,
			long connectionType, String wiringType, Double serviceLength,
			String deptId) {
		return dao.getServiceWireMaterialGrid(deptId, phase, connectionType, wiringType, serviceLength, region);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpsvcwrmEjb  ejb=new SpsvcwrmEjb("region");

	}

}
