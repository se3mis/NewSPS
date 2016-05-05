package estimate.ejb;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.ejb.Remote;
import javax.persistence.PersistenceException;

import job.model.Pcestdmt;

import estimate.model.Pcestdtt;
import estimate.model.PcestdttPK;

@Remote
public interface PcestdttDaoRemote {
	void createPcestdtt(Pcestdtt pcestdtt , String webAppName);
	List<Pcestdtt> getAll(String webAppName) ;
	void updatePcestdtt(Pcestdtt pcestdtt , String webAppName)  ;
	void removePcestdtt(Pcestdtt pcestdtt , String webAppName)  ;
	void removeAll(String webAppName) ;
	Pcestdtt findById(PcestdttPK id, String webAppName) throws PersistenceException;
	List<Pcestdtt>  findByEstimationNo(String estimateNo,String deptId, String webAppName);
	Pcestdtt  findBy3PK(String estimateNo,String deptId, String resCd, String webAppName);
	void changeCostCenterNoPcestdtt(String estimateNo, String areaDeptId,
			String depotDeptId, String webAppName);
	List<Pcestdtt> findByEstimationNo(String estimateNo, String webAppName);
	BigDecimal getSUM(String estimateNo, String webAppName);
	void changeUnitPtrice(String estimateNo, String resCd, String webAppName);
	
	public List<Map> getSUMByResType(String estimateNo,String deptId, String webAppName);
	public BigDecimal getSUMOfMATandMAT_OTHER(String estimateNo, String webAppName) ;
	public Long getRawCountOtherMat(String estimateNo,String deptId,String resCode, String webAppName);
	public int updatePcestdttOtherMaterialSum(String estimateNo,String deptId,String resCode,BigDecimal estimateCost, String webAppName);
	public List<Pcestdtt> findByEstimationNo(String estimateNo, String deptId,Long revNo, String webAppName);
	public Pcestdtt findBy3PK(String estimateNo, String deptId, String resCd,Long revNo, String webAppName);
	Pcestdtt getdttByResCD(String estimateNo,String resCD, String webAppName);
	int updatePcestdttPercentageCost(String estimateNo, String deptId,
			String resCode, BigDecimal estimateCost,BigDecimal estimateQuantity, String webAppName);
}
