package progressMonitoring.service;

import java.util.List;

import javax.persistence.PersistenceException;

import job.model.Pcesthmt;

import estimate.model.Spstdestdmt;

import progressMonitoring.model.Pcinitialdata;
import progressMonitoring.model.PcinitialdataPK;



public interface PcinitialdataEjbI {
	
	void addNewProgress(Pcinitialdata pcinitialdata);
	List<Pcinitialdata> getAll();
	List<Pcinitialdata> getAll(String deptId);
	Pcinitialdata findById(PcinitialdataPK id) throws PersistenceException;
	void createPcinitialdata(Pcinitialdata pcinitialdata);
	List<Pcinitialdata> getDataForEstNumber(String value, String Type);
	List<Pcinitialdata> getDataForDescr(String value, String Type);
	void updatePcinitialData(Pcinitialdata pcin);
	List<String> getListOfJobNumbers(String deptid);
}
