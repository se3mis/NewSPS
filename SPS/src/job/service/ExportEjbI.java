package job.service;

import java.util.List;

import javax.persistence.PersistenceException;

import export.model.BillUpdateData;

import job.model.Sparemap;
import job.model.Spexphst;
import job.model.Spexpjob;
import job.model.SpexpjobPK;



public interface ExportEjbI {
	void createSpexphst(Spexphst spexphst );
	void updateSpexphst(Spexphst spexphst )  ;
	void removeSpexphst(Spexphst spexphst )  ;
	void removeAllSpexphst();
	List<Spexphst> getAllSpexphst(String deptId) ; 
	Spexphst findById(String referenceNo) throws PersistenceException;
	void createSpexpjob(Spexpjob spexpjob );
	void updateSpexpjob(Spexpjob spexpjob )  ;
	void removeSpexpjob(Spexpjob spexpjob )  ;
	void removeAllSpexpjob();
	List<Spexpjob> getAllSpexpjob(String deptId) ; 
	Spexpjob findById(SpexpjobPK id) throws PersistenceException;
	void insertExportData(Spexphst Spexphst, List<Spexpjob> list);
	Sparemap findBySmcAreaCode(String smcAreaCode) throws PersistenceException;
	void updateExportedJob(List<BillUpdateData> list);
	List<String> getExportedJobWithoutAccNo(String deptId);
	List<String> updateErrorData(String deptId, List<BillUpdateData> list);
	List<BillUpdateData> getAccNoInfo(String deptId);
	Spexpjob findByJobNo(String jobNo)
	throws PersistenceException;
	Spexpjob findByJobNo(String jobNo, String deptId)
	throws PersistenceException;


}
