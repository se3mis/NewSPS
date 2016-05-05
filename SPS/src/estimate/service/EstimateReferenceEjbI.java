package estimate.service;

import java.util.List;

import javax.persistence.PersistenceException;

import estimate.model.EstimateReference;
import estimate.model.EstimateReferencePK;
import estimate.model.Pcestdtt;
import estimate.model.PcestdttPK;

//import estimate.model.Pcestdtt;
//import estimate.model.PcestdttPK;

public interface EstimateReferenceEjbI {
	EstimateReference findByAppId(EstimateReferencePK id, String webAppName);
	List<EstimateReference> getAll(String webAppName); 
	void createEstimateReference(EstimateReference estimateReference, String webAppName); 
	void updateEstimateReference(EstimateReference estimateReference, String webAppName) ;
	public List<EstimateReference> findByStdEstimateNo(String stdestimateNo,String deptId, String webAppName) throws Exception;
	public List<EstimateReference> findByStdEstimateNoCom(String deptId, String webAppName) throws Exception;
	
	void removEstimateReference(EstimateReference estimateReference, String webAppName);
	void removeAll(String webAppName);
	String getNextEstimateNo(String estimateNoPrefix, String webAppName);
	//EstimateReference findByJobNo(String jobNo,String deptId, String webAppName) throws Exception;
	String getNextJobNo(String jobNoPrefix, String webAppName);
	public EstimateReference findByWorkEstimateNo(String wrEstimateNo,String deptId,String webAppName) throws Exception;
	public List<EstimateReference> getWorkEstimatesBySEstimateNo(
			String sEstimateNo, String deptId, String webAppName)
			throws Exception;
	public boolean checkEstimateNoExist(String stdestimateNo,
			String wtdestimateNo, String deptId, String webAppName)
			throws Exception;
	List<EstimateReference> getActiveConstructionEstimates(
			String deptId, String webAppName) throws Exception;
	public EstimateReference findByJobNo(String jobNo, String deptId, String webAppName);
	public String getNextFileRefNo(String fileRefPrefix, String deptId,
			String webAppName);
}
