package progressMonitoring.service;

import java.util.List;

import javax.persistence.PersistenceException;

import job.model.Pcesthmt;

import estimate.model.Spstdestdmt;

import progressMonitoring.model.Pcinitialdata;
import progressMonitoring.model.PcinitialdataPK;
import progressMonitoring.model.Pcmilem;



public interface PcmilemEjbI {
	
	void createPcmilem(Pcmilem Pcmilem,String webAppName);
	List<Pcmilem> getAll(String webAppName);
	
	
	List<Pcmilem> getMilestoneList(String deptId,
			String webAppName);
	void updatePcmilem(Pcmilem pcmilem,String webAppName);
}
