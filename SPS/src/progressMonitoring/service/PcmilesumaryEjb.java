package progressMonitoring.service;

import java.util.List;

import javax.naming.InitialContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import job.model.Pcesthmt;

import estimate.model.Spstdestdmt;

import progressMonitoring.ejb.PcinitialDaoRemote;
import progressMonitoring.ejb.PcmilemDao;
import progressMonitoring.ejb.PcmilemDaoRemote;
import progressMonitoring.ejb.PcmilesumaryDao;
import progressMonitoring.ejb.PcmilesumaryDaoRemote;
import progressMonitoring.model.Pcinitialdata;
import progressMonitoring.model.PcinitialdataPK;
import progressMonitoring.model.Pcmilem;
import progressMonitoring.model.Pcmilesumary;

public class PcmilesumaryEjb implements PcmilesummaryEjbI {

	
	public PcmilesumaryDaoRemote dao;
	public InitialContext Context;
	//public String webAppName;
	public String region = null;
	


	public PcmilesumaryEjb(String region){
		super();
	this.dao= (PcmilesumaryDaoRemote) lookupDao();
	this.region=region;
		
	}

	private PcmilesumaryDaoRemote lookupDao() {
		try {
			Context = new InitialContext();
			PcmilesumaryDaoRemote dao = (PcmilesumaryDaoRemote)Context.lookup("PcmilesumaryDao/remote");
			return dao;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}

	@Override
	public void createPcmilesummary(Pcmilesumary pcmilesummary,
			String webAppName) {
		dao.createPcmilesumary(pcmilesummary, webAppName);
		
	}

	@Override
	public List<Pcmilesumary> getAll(String webAppName) {
		return dao.getAll(webAppName);
	}

	@Override
	public Pcmilesumary getMilestoneList(String deptId,String projectNo, String webAppName) {
		return dao.getMilestoneList(deptId,projectNo,webAppName);
	}

	@Override
	public void updatePcmilesummary(Pcmilesumary pcmilesummary,
			String webAppName) {
		dao.updatePcmilesumary(pcmilesummary, webAppName);
		
	}

	@Override
	public Pcmilesumary getMilestoneSummaryId(String deptId,
			String projectNumber,String milestoneId) {
		return dao.getMilestoneSummaryId(deptId,projectNumber,milestoneId,region);
	}
	
	@Override
	public void removePcmilesumary(Pcmilesumary pcmilesumary, String webAppName) {
		//getEntityManager(webAppName);
		dao.removePcmilesumary(pcmilesumary, region);
		
	}
	
	@Override
	public List<Pcmilesumary> getSummaryList(String deptId,String projectNumber, String webAppName) {
		return dao.getSummaryList(deptId, projectNumber, region);
	}
	
}
