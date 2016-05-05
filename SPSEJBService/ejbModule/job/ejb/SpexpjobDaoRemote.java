package job.ejb;
import java.util.List;

import javax.ejb.Remote;
import javax.persistence.PersistenceException;

import export.model.BillUpdateData;

import job.model.Spexpjob;
import job.model.SpexpjobPK;

@Remote
public interface SpexpjobDaoRemote {
	void createSpexpjob(Spexpjob spexpjob , String webAppName);
	void updateSpexpjob(Spexpjob spexpjob , String webAppName)  ;
	void removeSpexpjob(Spexpjob spexpjob , String webAppName)  ;
	void removeAll(String webAppName);
	List<Spexpjob> getAll(String deptId, String webAppName) ; 
	Spexpjob findById(SpexpjobPK id, String webAppName) throws PersistenceException;
	void updateExportedJob(List<BillUpdateData> list, String webAppName);
	List<String> getExportedJobWithoutAccNo(String deptId, String webAppName);
	List<BillUpdateData> getAccNoInfo(String deptId, String webAppName);
	Spexpjob findByJobNo(String jobNo, String webAppName)
			throws PersistenceException;
	Spexpjob findByJobNo(String jobNo, String deptId, String webAppName)
			throws PersistenceException;


}
