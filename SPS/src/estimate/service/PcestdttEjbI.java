package estimate.service;

import java.util.List;
import java.util.Map;

import javax.persistence.PersistenceException;

import estimate.model.Pcestdtt;
import estimate.model.PcestdttPK;

//import estimate.model.Pcestdtt;
//import estimate.model.PcestdttPK;

public interface PcestdttEjbI {
	void createPcestdtt(Pcestdtt pcestdtt );
	List<Pcestdtt> getAll() ;
	void updatePcestdtt(Pcestdtt pcestdtt )  ;
	void removePcestdtt(Pcestdtt pcestdtt )  ;
	void removeAll() ;
	Pcestdtt findById(PcestdttPK id) throws PersistenceException;
	List<Pcestdtt>  findByEstimationNo(String estimateNo,String deptId);
	Pcestdtt  findBy3PK(String estimateNo,String deptId, String resCd);
	List<Pcestdtt> findByEstimationNo(String estimateNo);
	public List<Pcestdtt> findByEstimationNo(String estimateNo, String deptId,Long revNo);
	public Pcestdtt findBy3PK(String estimateNo, String deptId, String resCd,
			Long revNo, String webAppName);
	public List<Map> getSUMByResType(String estimateNo, String deptId) ;
}
