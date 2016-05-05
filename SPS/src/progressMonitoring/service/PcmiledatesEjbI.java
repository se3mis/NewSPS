package progressMonitoring.service;

import java.util.List;

import java.util.Date;

import javax.persistence.PersistenceException;

import job.model.Pcesthmt;

import estimate.model.Spstdestdmt;

import progressMonitoring.model.Pcinitialdata;
import progressMonitoring.model.PcinitialdataPK;
import progressMonitoring.model.Pcmiledates;
import progressMonitoring.model.Pcmilem;



public interface PcmiledatesEjbI {
	
	void createPcmiledates(Pcmiledates pcmiledates,String webAppName);
	List<Pcmiledates> getAll(String webAppName);
	void removePcmiledates(Pcmiledates pcmiledates, String webAppName);
	void delete(String deptId,String projectNumber,String webAppName);
	List<Pcmiledates> getMilestoneList(String deptId,String projectNumber,String webAppName);
	void updatePcmiledates(Pcmiledates pcmiledates,String webAppName);
	Pcmiledates getMilestoneById(String deptId,String projectNumber,String miletoneId,String webAppName);
	Pcmiledates getMilestoneByDate(String deptId,String projectNumber,String miletoneId,Date date,String webAppName);
}
