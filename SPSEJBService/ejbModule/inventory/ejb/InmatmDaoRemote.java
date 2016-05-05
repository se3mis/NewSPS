package inventory.ejb;

import java.util.List;
import inventory.model.Inmatm;
import javax.ejb.Remote;
import javax.persistence.PersistenceException;


@Remote
public interface InmatmDaoRemote {
	void createInmatm(Inmatm inmatm, String webAppName);
	List<Inmatm> getAll1(String webAppName) ;
	void updateInmatm(Inmatm inmatm, String webAppName)  ;
	void removeInmatm(Inmatm inmatm, String webAppName)  ;
	void removeAll(String webAppName);
	Inmatm findById(String matCd, String webAppName) ;
	String findName(String matCd, String webAppName);
	public Inmatm findMatItem(String matCd, String webAppName);
	//List<MatInfo> getMatListByCategory(String deptId, String like) ;


}
