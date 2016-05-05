package job.ejb;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.ejb.Remote;
import javax.persistence.PersistenceException;
import job.model.Pcestdmt;
import job.model.PcestdmtPK;


@Remote
public interface PcestdmtDaoRemote {
	void createPcestdmt(Pcestdmt pcestdmt , String webAppName);
	void updatePcestdmt(Pcestdmt pcestdmt , String webAppName)  ;
	void removePcestdmt(Pcestdmt pcestdmt , String webAppName)  ;
	void removeAll(String webAppName);
	List<Pcestdmt> getAll(String webAppName) ; 
	Pcestdmt findById(PcestdmtPK id, String webAppName) throws PersistenceException;
	List<Pcestdmt> findByEstimationNo(String estimateNo,String deptId, String webAppName);
	Pcestdmt findByJobNo(String jobNo,String deptId, String webAppName);
	List<String>  findJobNoList(String deptId, BigDecimal status, String webAppName);
	Pcestdmt findBy3PK(String estimateNo, String deptId, String resCd, String webAppName);
	List<Pcestdmt> getAll(String deptId, String webAppName);
	void updateRevNo(Pcestdmt pcestdmt, Long newRevNo, String webAppName);
	List<Pcestdmt> findByEstimationNo(String estimateNo, String webAppName);
	BigDecimal getSUM(String estimateNo, String webAppName);
	void changeUnitPtrice(String estimateNo, String resCd, String webAppName); 
	List<Map> getSUMByResType(String estimateNo,String resType, String webAppName)throws PersistenceException;
	
	BigDecimal getSUMOfMATandMAT_OTHER(String estimateNo, String webAppName)throws PersistenceException;
	public Long getRawCountOtherMat(String estimateNo,String deptId,String resCode, String webAppName)throws PersistenceException;
	public int updatePcestdmtOtherMaterialSum(String estimateNo,String deptId,String resCode,BigDecimal estimateCost, String webAppName)throws PersistenceException;
	int updatePcestdmtPercentageCost(String estimateNo,String deptId,String resCode,BigDecimal estimateCost, BigDecimal estimateQuantity, String webAppName);
	Pcestdmt getdmtByResCD(String estimateNo,String resCD, String webAppName);
}
