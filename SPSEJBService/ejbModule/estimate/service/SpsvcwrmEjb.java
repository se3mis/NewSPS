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

public class SpsvcwrmEjb implements SpsvcwrmDaoRemote{
	private Context context;
	private SpsvcwrmDaoRemote dao; 
	public SpsvcwrmEjb() {
		super();
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
	public void createSpsvcwrm(Spsvcwrm spsvcwrm, String webAppName) {
		dao.createSpsvcwrm(spsvcwrm, webAppName);
		
	}

	@Override
	public List<Spsvcwrm> getAll( String webAppName) {
		return dao.getAll(webAppName);
	}

	@Override
	public void updateSpsvcwrm(Spsvcwrm spsvcwrm, String webAppName) {
		dao.updateSpsvcwrm(spsvcwrm, webAppName);
		
	}

	@Override
	public void removeSpsvcwrm(Spsvcwrm spsvcwrm, String webAppName) {
		dao.removeSpsvcwrm(spsvcwrm, webAppName);
		
	}

	@Override
	public void removeAll(String webAppName) {
		dao.removeAll(webAppName);
		
	}

	@Override
	public Spsvcwrm findById(SpsvcwrmPK id, String webAppName) throws PersistenceException {
		return dao.findById(id, webAppName);
	}

	@Override
	public List<MaterialGrid> getServiceWireMaterialGrid(String deptId,long phase,
			long connectionType, String wiringType, Double serviceLength, String webAppName) {
		return dao.getServiceWireMaterialGrid(deptId,phase, connectionType, wiringType, serviceLength, webAppName);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpsvcwrmEjb ejb=new SpsvcwrmEjb();
		System.out.println(ejb.getAll("SMCTesting"));
		System.out.println(ejb.getAll("SMCTesting"));
		System.out.println(ejb.getServiceWireMaterialGrid( "510.20",1, 30, "OH", new Double(6.00),"SMCTesting"));

	}

	@Override
	public List<Spsvcwrm> getAll(String deptId, String webAppName) {
		return dao.getAll(deptId, webAppName);
	}

}
