package job.ejb;
import java.util.List;

import javax.ejb.Remote;
import javax.persistence.PersistenceException;

import export.model.BillUpdateData;

import job.model.Sparemap;
import job.model.Spexphst;
import job.model.Spexpjob;
import job.model.SpexpjobPK;

@Remote
public interface ExportBeanRemote {
	void createSpexphst(Spexphst spexphst , String webAppName);
	void updateSpexphst(Spexphst spexphst , String webAppName)  ;
	void removeSpexphst(Spexphst spexphst , String webAppName)  ;
	void removeAllSpexphst(String webAppName);
	List<Spexphst> getAllSpexphst(String deptId, String webAppName) ; 
	Spexphst findById(String referenceNo, String webAppName) throws PersistenceException;
	void createSpexpjob(Spexpjob spexpjob , String webAppName);
	void updateSpexpjob(Spexpjob spexpjob , String webAppName)  ;
	void removeSpexpjob(Spexpjob spexpjob , String webAppName)  ;
	void removeAllSpexpjob(String webAppName);
	List<Spexpjob> getAllSpexpjob(String deptId, String webAppName) ; 
	Spexpjob findById(SpexpjobPK id, String webAppName) throws PersistenceException;
	void insertExportData(Spexphst Spexphst, List<Spexpjob> list, String webAppName);
	Sparemap findBySmcAreaCode(String smcAreaCode, String webAppName) throws PersistenceException;
	void updateExportedJob(List<BillUpdateData> list, String webAppName);
	List<String> getExportedJobWithoutAccNo(String deptId, String webAppName);
	List<String> updateErrorData(String deptId, List<BillUpdateData> list,
			String webAppName);
	List<BillUpdateData> getAccNoInfo(String deptId, String webAppName);
	Spexpjob findByJobNo(String jobNo, String webAppName)
	throws PersistenceException;
	Spexpjob findByJobNo(String jobNo, String deptId, String webAppName)
	throws PersistenceException;
}
