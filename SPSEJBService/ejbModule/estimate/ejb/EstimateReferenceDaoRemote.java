package estimate.ejb;
import java.util.List;

import javax.ejb.Remote;
import javax.persistence.PersistenceException;

import estimate.model.EstimateReference;
import estimate.model.EstimateReferencePK;


@Remote
public interface EstimateReferenceDaoRemote {
	EstimateReference findByAppId(EstimateReferencePK id, String webAppName);
	List<EstimateReference> getAll(String webAppName); 
	void createEstimateReference(EstimateReference estimateReference, String webAppName); 
	void updateEstimateReference(EstimateReference estimateReference, String webAppName) ;
	public List<EstimateReference> findByStdEstimateNo(String stdestimateNo, String deptId,String webAppName) throws PersistenceException;
	public List<EstimateReference> findByStdEstimateNoCom(String deptId,String webAppName) throws PersistenceException;
	
	public int removEstimateReference(EstimateReference estimateReference,
			String webAppName)throws PersistenceException;
	void removeAll(String webAppName);
	String getNextEstimateNo(String estimateNoPrefix, String webAppName);
	EstimateReference findByJobNo(String jobNo, String deptId,String webAppName) throws PersistenceException;
	String getNextJobNo(String jobNoPrefix, String webAppName);
	public EstimateReference findByWorkEstimateNo(String wrEstimateNo,String deptId, String webAppName) throws PersistenceException;
	public List<EstimateReference> getWorkEstimatesBySEstimateNo(String sEstimateNo,String deptId,String webAppName)throws PersistenceException;
	public boolean checkEstimateNoExist(String stdestimateNo,String wtdestimateNo,String deptId,String webAppName) throws PersistenceException;
	List<EstimateReference> getActiveConstructionEstimates(String deptId,String webAppName) throws PersistenceException;
	String getNextEstimateNoViaCommReference(String applicationNoPrefix, String webAppName)throws PersistenceException;
	public String getNextFileRefNo(String fileRefPrefix,String deptId, String webAppName);
	//EstimateReference findByJobNo(String jobNo, String deptId,String webAppName) throws PersistenceException;
}
