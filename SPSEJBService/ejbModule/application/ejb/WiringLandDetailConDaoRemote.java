package application.ejb;
import java.util.List;

import javax.ejb.Remote;
import javax.persistence.PersistenceException;



import application.model.WiringInfo;
import application.model.WiringLandDetail;
import application.model.WiringLandDetailCon;
import application.model.WiringLandDetailConPK;
import application.model.WiringLandDetailPK;

@Remote
public interface WiringLandDetailConDaoRemote {
	List<WiringLandDetailCon> getAll(String webAppName) ; 
	void createWiringLandDetailCon(WiringLandDetailCon wiringLandDetail, String webAppName);
	WiringLandDetailCon findByAppId(WiringLandDetailConPK id, String webAppName);
	void updateWiringLandDetailCon(WiringLandDetailCon wiringLandDetail, String webAppName); 
	void removeWiringLandDetailCon(WiringLandDetailCon wiringLandDetail, String webAppName)  ;
	void removeAll(String webAppName) ;
	WiringInfo getWiringInfo(String applicationId, String deptId, String webAppName);
	
	WiringLandDetailCon findByApplicationNo(String applicationId, String webAppName);
	
}
