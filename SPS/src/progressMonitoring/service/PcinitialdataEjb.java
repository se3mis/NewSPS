package progressMonitoring.service;

import java.util.List;

import javax.naming.InitialContext;
import javax.persistence.PersistenceException;

import job.model.Pcesthmt;

import estimate.model.Spstdestdmt;

import progressMonitoring.ejb.PcinitialDaoRemote;
import progressMonitoring.model.Pcinitialdata;
import progressMonitoring.model.PcinitialdataPK;

public class PcinitialdataEjb implements PcinitialdataEjbI {

	
	public PcinitialDaoRemote dao;
	public InitialContext Context;
	//public String webAppName;
	public String region = null;
	private List<Pcinitialdata> pcinitialdataList1;
	


	public PcinitialdataEjb(String region){
		super();
	this.dao= (PcinitialDaoRemote) lookupDao();
	this.region=region;
		
	}

	private PcinitialDaoRemote lookupDao() {
		try {
			Context = new InitialContext();
			PcinitialDaoRemote dao = (PcinitialDaoRemote)Context.lookup("PcinitialDao/remote");
			return dao;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}
	@Override
	public void addNewProgress(Pcinitialdata pcinitialdata) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Pcinitialdata> getAll() {
		return dao.getAll(region);
	}

	@Override
	public List<Pcinitialdata> getAll(String deptId) {
		// TODO Auto-generated method stub
		return null;
	}

	

	@Override
	public void createPcinitialdata(Pcinitialdata pcinitialdata) {
	
		 dao.createPcinitialdata(pcinitialdata, region);
		 System.out.println("fasdfasdf");
		}

	@Override
	public List<Pcinitialdata> getDataForEstNumber(String value, String Type) {
		pcinitialdataList1=dao.getDataForEstNumber(region, value, Type);
		return pcinitialdataList1;
	}

	public List<Pcinitialdata> getPcinitialdataList1() {
		return pcinitialdataList1;
	}

	public void setPcinitialdataList1(List<Pcinitialdata> pcinitialdataList1) {
		this.pcinitialdataList1 = pcinitialdataList1;
	}
	
	
	@Override
	public void updatePcinitialData(Pcinitialdata pcin) {
		// TODO Auto-generated method stub
		dao.updatePcinitialData(pcin, region);
		
	}
	
	@Override
	public List<String> getListOfJobNumbers(String deptid)
	{
		
		return dao.getListOfJobNumbers(region,deptid);
	}

	@Override
	public Pcinitialdata findById(PcinitialdataPK id) throws PersistenceException {
		// TODO Auto-generated method stub
		return dao.findById(id,region);
	}
	
	

	@Override
	public List<Pcinitialdata> getDataForDescr(String value, String Type) {
		// TODO Auto-generated method stub
		pcinitialdataList1=dao.getDataForEstNumber(region, value, Type);
		return pcinitialdataList1;
	}

}
