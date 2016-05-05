package progressMonitoring.ejb;

import java.util.List;

import javax.ejb.Remote;
import javax.persistence.PersistenceException;

import job.model.Pcesthmt;

import estimate.model.Spstdestdmt;

import progressMonitoring.model.Pcinitialdata;
import progressMonitoring.model.PcinitialdataPK;

@Remote
public interface PcinitialDaoRemote {

	void createPcinitialdata(Pcinitialdata pcinitialdata,String webAppName);
	List<Pcinitialdata> getAll(String webAppName);
	
	List<Pcinitialdata> getDataForDescr(String webAppName, String value, String Type);
	List<Pcinitialdata> getDataForEstNumber(String webAppName, String value, String Type);
	void updatePcinitialData(Pcinitialdata pcinitialdata,String webAppName);
	List<String> getListOfJobNumbers(String webAppName,String deptId);
	Pcinitialdata findById(PcinitialdataPK id,String webAppName) throws PersistenceException;
}
