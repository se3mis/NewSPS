package estimate.ejb;
import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Remote;
import javax.persistence.PersistenceException;

import job.model.Pcestdmt;

import estimate.model.LabourGrid;
import estimate.model.Pcestdtt;
import estimate.model.Spestlab;
import estimate.model.SpestlabPK;

@Remote
public interface SpestlabDaoRemote {
	void createSpestlab(Spestlab spestlab, String webAppName);
	List<Spestlab> getAll(String deptId, String webAppName) ;
	List<Spestlab> getAll(String estimateNo, String deptId, String webAppName);
	void updateSpestlab(Spestlab spestlab, String webAppName);
	void removeSpestlab(Spestlab spestlab, String webAppName);
	void removeAll(String webAppName);
	Spestlab findById(SpestlabPK id, String webAppName) throws PersistenceException;
	List<LabourGrid> getSpestlabList(String estimateNo, String deptId,
			String webAppName);
	Spestlab findByEstimateNo(String estimateNo, String labourCode,
			String webAppName);
	void updateContractorLabourCost(String deptId, String webAppName);
	List<String> getAllByType(String deptId, String like, String webAppName);
	BigDecimal getSpestlabSUM(String estimateNo, String deptId,
			String webAppName);
	void updateDmtHmtForLabour(String deptId, String webAppName);
	void insertTransport(Pcestdmt pcestdmt,String estimateNo, String deptId, String resCd,
			String webAppName);
	void updateContractorLabourCostByEstNo(String estimateNo, String deptId,
			String webAppName);
	void updateDmtHmtForLabour(String estimateNo, String deptId,
			String webAppName);
	void updateDmtHmtForLabour(String deptId, String webAppName, int from,
			int to);
	void insertTransport(String estimateNo, String deptId, String resCd,
			String webAppName);
	void insertTransportOnDMT(String estimateNo, String deptId,
			String webAppName);
	void insertTransportOnDTT(String estimateNo, String deptId,
			String webAppName);
	void insertTransport(Pcestdtt pcestdtt, String estimateNo, String deptId,
			String resCd, String webAppName);
	void updateLabourCode(String labourCode, String webAppName);
	


}
