package estimate.ejb;
import java.util.List;

import javax.ejb.Remote;
import javax.persistence.PersistenceException;
import estimate.model.Pcjbtypm;
import estimate.model.PcjbtypmPK;

@Remote
public interface PcjbtypmDaoRemote {
	void createPcjbtypm(Pcjbtypm pcjbtypm, String webAppName);
	List<Pcjbtypm> getAll(String webAppName) ;
	List<Pcjbtypm> getAll(String deptId, String webAppName);
	List<String> getCatCode(String deptId, String webAppName);
	void updatePcjbtypm(Pcjbtypm pcjbtypm, String webAppName)  ;
	void removePcjbtypm(Pcjbtypm pcjbtypm, String webAppName)  ;
	void removeAll(String webAppName) ;
	Pcjbtypm findById(PcjbtypmPK id, String webAppName) throws PersistenceException ;
	

}
