package costcenter.ejb;
import java.util.List;

import javax.ejb.Remote;
import javax.persistence.PersistenceException;
import costcenter.model.Gldeptin;

@Remote
public interface GldeptinDaoRemote {
	void createGldeptin(Gldeptin gldeptin, String webAppName);
	List<Gldeptin> getAll( String webAppName) ;
	void updateGldeptin(Gldeptin gldeptin, String webAppName)  ;
	void removeGldeptin(Gldeptin gldeptin, String webAppName)  ;
	void removeAll( String webAppName) ;
	Gldeptin findById(String deptId, String webAppName) throws PersistenceException;
	Gldeptin getFindByDeptId(String deptId, String webAppName);
	

}
