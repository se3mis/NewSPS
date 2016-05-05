package progressMonitoring.service;

import java.util.List;

import javax.naming.InitialContext;
import javax.persistence.PersistenceException;

import job.model.Pcesthmt;

import estimate.ejb.ProgressMonitoringBeanRemote;
import estimate.model.Spstdestdmt;

import progressMonitoring.ejb.PcinitialDaoRemote;
import progressMonitoring.model.Pcinitialdata;
import progressMonitoring.model.PcinitialdataPK;
import progressMonitoring.model.Pcmiledates;
import progressMonitoring.model.Pcmilesumary;

public class ProgressMonitoringEjb implements ProgressMonitoringEjbI {

	
	public ProgressMonitoringBeanRemote dao;
	public InitialContext Context;
	//public String webAppName;
	public String region = null;
	private List<Pcinitialdata> pcinitialdataList1;
	


	public ProgressMonitoringEjb(String region){
		super();
	this.dao= (ProgressMonitoringBeanRemote) lookupDao();
	this.region=region;
		
	}

	private ProgressMonitoringBeanRemote lookupDao() {
		try {
			Context = new InitialContext();
			ProgressMonitoringBeanRemote dao = (ProgressMonitoringBeanRemote)Context.lookup("ProgressMonitoringBean/remote");
			return dao;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}
	

	@Override
	public int updateProgress(Pcmiledates pcmiledate,
			Pcmilesumary pcmilesumary, String webAppName) {
		// TODO Auto-generated method stub
		return dao.updateProgress(pcmiledate, pcmilesumary, region);
	}

}
