package job.ejb;
import java.util.List;

import javax.ejb.Remote;
import javax.persistence.PersistenceException;

import job.model.Spexphst;

@Remote
public interface SpexphstDaoRemote {
	void createSpexphst(Spexphst spexphst , String webAppName);
	void updateSpexphst(Spexphst spexphst , String webAppName)  ;
	void removeSpexphst(Spexphst spexphst , String webAppName)  ;
	void removeAll(String webAppName);
	List<Spexphst> getAll(String deptId, String webAppName) ; 
	Spexphst findById(String referenceNo, String webAppName) throws PersistenceException;
	String getNextAppointmentId(String deptId, String webAppName);
	String createSpexphstAutoId(Spexphst spexphst, String webAppName);

}
