package application.ejb;
import java.util.List;

import javax.ejb.Remote;
import javax.persistence.PersistenceException;



import application.model.WiringInfo;
import application.model.WiringLandDetail;
import application.model.WiringLandDetailPK;

@Remote
public interface WiringLandDetailDaoRemote {
	List<WiringLandDetail> getAll(String webAppName) ; 
	void createWiringLandDetail(WiringLandDetail wiringLandDetail, String webAppName);
	WiringLandDetail findByAppId(WiringLandDetailPK id, String webAppName);
	void updateWiringLandDetail(WiringLandDetail wiringLandDetail, String webAppName); 
	void removeWiringLandDetail(WiringLandDetail wiringLandDetail, String webAppName)  ;
	void removeAll(String webAppName) ;
	WiringInfo getWiringInfo(String applicationId, String deptId, String webAppName);
	String getServiceCity(String estimateNo, String deptId, String webAppName);
	WiringLandDetail findByApplicationNo(String applicationId, String webAppName);
	int updateDemand(String applicationId,Long demand,String webAppName) throws PersistenceException;
}
