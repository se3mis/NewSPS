package progressMonitoring.service;

import java.util.List;

import javax.naming.InitialContext;
import javax.persistence.PersistenceException;

import job.model.Pcesthmt;

import estimate.model.Spstdestdmt;

import progressMonitoring.ejb.PcinitialDaoRemote;
import progressMonitoring.ejb.PcmilemDao;
import progressMonitoring.ejb.PcmilemDaoRemote;
import progressMonitoring.model.Pcinitialdata;
import progressMonitoring.model.PcinitialdataPK;
import progressMonitoring.model.Pcmilem;

public class PcmilemEjb implements PcmilemEjbI {

	
	public PcmilemDaoRemote dao;
	public InitialContext Context;
	//public String webAppName;
	public String region = null;
	


	public PcmilemEjb(String region){
		super();
	this.dao= (PcmilemDaoRemote) lookupDao();
	this.region=region;
		
	}

	private PcmilemDaoRemote lookupDao() {
		try {
			Context = new InitialContext();
			PcmilemDaoRemote dao = (PcmilemDaoRemote)Context.lookup("PcmilemDao/remote");
			return dao;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}

	@Override
	public void createPcmilem(Pcmilem Pcmilem, String webAppName) {
		dao.createPcmilem(Pcmilem, webAppName);
		
	}

	@Override
	public List<Pcmilem> getAll(String webAppName) {
		return dao.getAll( webAppName);
	}

	@Override
	public List<Pcmilem> getMilestoneList(String deptId, String webAppName) {
		return dao.getMilestoneList(deptId, webAppName);
	}

	@Override
	public void updatePcmilem(Pcmilem pcmilem, String webAppName) {
		dao.updatePcmilem(pcmilem, webAppName);
		
	}
	
	
}
