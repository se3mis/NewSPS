package rebate.service;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.PersistenceException;

import rebate.model.Sprebate;
import rebate.model.SprebatePK;

public interface RebateEjbI {
	void createRebate(Sprebate sprebate , String webAppName);
	List<Sprebate> getAll(String webAppName) ;
	void updateRebate(Sprebate sprebate , String webAppName)  ;
	void removeRebate(Sprebate sprebate , String webAppName)  ;
	void removeAll(String webAppName) ;
	Sprebate findById(SprebatePK id, String webAppName) throws PersistenceException;
	List<Sprebate>  findByEstimationNo(String estimateNo,String deptId, String webAppName);
	List<String>  findByEstimationNoStr(String estimateNo,String deptId, String webAppName);
	
	Sprebate  findBy3PK(String estimateNo,String deptId, String resCd, String webAppName);
	List<Sprebate> findByEstimationNo(String estimateNo, String webAppName);
	BigDecimal getSUMRebate(String estimateNo, String webAppName);
	String getNextDocNo(String applicationNoPrefix, String webAppName);
}
