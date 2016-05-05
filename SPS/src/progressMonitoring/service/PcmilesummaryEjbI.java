package progressMonitoring.service;

import java.util.List;

import javax.persistence.PersistenceException;

import job.model.Pcesthmt;

import estimate.model.Spstdestdmt;

import progressMonitoring.model.Pcinitialdata;
import progressMonitoring.model.PcinitialdataPK;
import progressMonitoring.model.Pcmilem;
import progressMonitoring.model.Pcmilesumary;



public interface PcmilesummaryEjbI {
	
	void createPcmilesummary(Pcmilesumary pcmilesummary,String webAppName);
	List<Pcmilesumary> getAll(String webAppName);
	
	void removePcmilesumary(Pcmilesumary pcmilesumary, String webAppName);
	Pcmilesumary getMilestoneList(String deptId,String projectNumber,
			String webAppName);
	void updatePcmilesummary(Pcmilesumary pcmilesummary,String webAppName);
	Pcmilesumary getMilestoneSummaryId(String deptId,
			String projectNumber,String milestoneId);
	List<Pcmilesumary> getSummaryList(String deptId,String projectNumber, String webAppName);
}
