package progressMonitoring.service;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.PersistenceException;

import piv.ejb.PivDetailDaoRemote;
import piv.model.PivDetailPK;
import piv.service.PivDetailEjb;
import progressMonitoring.ejb.PcinitialDaoRemote;
import progressMonitoring.model.Pcinitialdata;
import progressMonitoring.model.PcinitialdataPK;
import progressMonitoring.model.Pcmiledates;

public class PcinitialDataEjb implements PcinitialDaoRemote {
	
	
	private Context context;
	private PcinitialDaoRemote dao; 
	
	public PcinitialDataEjb() {
		super();
		dao=lookupPcinitialDao();
		
	}

	
	private PcinitialDaoRemote lookupPcinitialDao() {
		try
		{
			 context = new InitialContext();
			 PcinitialDaoRemote dao = (PcinitialDaoRemote) context.lookup("PcinitialDao/remote");
			 return dao; 
			 
		} catch (NamingException e){
			e.printStackTrace();
			
			throw new RuntimeException(e);
		}
		
	}


	@Override
	public void createPcinitialdata(Pcinitialdata pcinitialdata,
			String webAppName) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public List<Pcinitialdata> getAll(String webAppName) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<Pcinitialdata> getDataForDescr(String webAppName, String value,
			String Type) {
		dao.getDataForDescr(webAppName, value, Type);
		return null;
	}


	@Override
	public List<Pcinitialdata> getDataForEstNumber(String webAppName,
			String value, String Type) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void updatePcinitialData(Pcinitialdata pcinitialdata,
			String webAppName) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public List<String> getListOfJobNumbers(String webAppName, String deptId) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Pcinitialdata findById(PcinitialdataPK id, String webAppName)
			throws PersistenceException {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	public static void main(String[] args) {
		//PcmiledatesEjb ejb=new PcmiledatesEjb();
		
		//Pcinitialdata  list = ejb.get("R2", "ELECTRICITY", "constructRef");
		//System.out.println("hiii : " + list);
	
	}

}
