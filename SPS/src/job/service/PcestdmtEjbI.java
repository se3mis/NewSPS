package job.service;

import java.util.List;
import java.util.Map;

import javax.persistence.PersistenceException;

import job.model.Pcestdmt;
import job.model.PcestdmtPK;

//import job.model.Pcestdmt;
//import job.model.PcestdmtPK;

public interface PcestdmtEjbI {
	void createPcestdmt(Pcestdmt pcestdmt );
	void updatePcestdmt(Pcestdmt pcestdmt )  ;
	void removePcestdmt(Pcestdmt pcestdmt )  ;
	void removeAll();
	List<Pcestdmt> getAll() ;
	Pcestdmt findById(PcestdmtPK id) throws PersistenceException;
	List<Pcestdmt> findByEstimationNo(String estimateNo,String deptId);
	Pcestdmt findBy3PK(String estimateNo, String deptId, String resCd);
	List<Pcestdmt> findByEstimationNo(String estimateNo);
	Pcestdmt findByJobNo(String jobNo, String deptId);
	List<Map> getSUMByResType(String estimateNo, String deptId);
}
