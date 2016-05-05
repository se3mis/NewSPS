package progressMonitoring.service;

import java.util.List;
import java.util.Date;

import javax.naming.InitialContext;
import javax.persistence.PersistenceException;

import job.model.Pcesthmt;

import estimate.model.Spstdestdmt;

import piv.model.PivDetailPK;
import piv.service.PivDetailEjb;
import progressMonitoring.ejb.PcinitialDaoRemote;
import progressMonitoring.ejb.PcmiledatesDao;
import progressMonitoring.ejb.PcmiledatesDaoRemote;
import progressMonitoring.ejb.PcmilemDao;
import progressMonitoring.ejb.PcmilemDaoRemote;
import progressMonitoring.model.Pcinitialdata;
import progressMonitoring.model.PcinitialdataPK;
import progressMonitoring.model.Pcmiledates;
import progressMonitoring.model.Pcmilem;

public class PcmiledatesEjb implements PcmiledatesEjbI {

	
	public PcmiledatesDaoRemote dao;
	public InitialContext Context;
	//public String webAppName;
	public String region = null;
	


	public PcmiledatesEjb(String region){
		super();
	this.dao= (PcmiledatesDaoRemote) lookupDao();
	this.region=region;
		
	}

	private PcmiledatesDaoRemote lookupDao() {
		try {
			Context = new InitialContext();
			PcmiledatesDaoRemote dao = (PcmiledatesDaoRemote)Context.lookup("PcmiledatesDao/remote");
			return dao;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}

	
	@Override
	public void createPcmiledates(Pcmiledates pcmiledates, String webAppName) {
		dao.createpcmiledates(pcmiledates, region);
		
	}



	@Override
	public void updatePcmiledates(Pcmiledates pcmiledates, String webAppName) {
		dao.updatepcmiledates(pcmiledates, region);
		
	}

	@Override
	public List<Pcmiledates> getAll(String webAppName) {
		return dao.getAll(region);
	}

	@Override
	public List<Pcmiledates> getMilestoneList(String deptId,String projectNumber,String webAppName) {
		return dao.getMilestoneList(deptId,projectNumber,region);
	}

	@Override
	public Pcmiledates getMilestoneById(String deptId,String projectNumber,String miletoneId,String webAppName) {
		return dao.getMilestoneById(deptId,projectNumber,miletoneId,region);
	}

	@Override
	public void delete(String deptId, String projectNumber, String webAppName) {
		// TODO Auto-generated method stub
		dao.delete(deptId,projectNumber,region);
	}
	
	public void removePcmiledates(Pcmiledates pcmiledates, String webAppName) {
		//getEntityManager(webAppName);
		dao.removePcmiledates(pcmiledates, region);
		
	}

	@Override
	public Pcmiledates getMilestoneByDate(String deptId, String projectNumber,
			String miletoneId, Date date, String webAppName) {
		return dao.getMilestoneByDate(deptId,projectNumber,miletoneId,date,region);
		
	}
	
	
	
	
}
