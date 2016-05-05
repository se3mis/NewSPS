package application.ejb;
import java.util.List;
import java.util.Map;

import javax.ejb.Remote;

import costcenter.model.Glcompm;
import costcenter.model.Gldeptm;

import estimate.model.Spestedy;
import estimate.model.Spserest;

import application.model.Application;
import application.model.WiringLandDetail;
import application.model.WiringLandDetailCon;

@Remote
public interface ApplicationTransactionBeanRemote {
	void save(Application application,WiringLandDetail wiringLandDetail, String webAppName);
	void update(Application application,WiringLandDetail wiringLandDetail, String webAppName);
	String update(Application application,String applicationIdPrefix, String webAppName);
	String save(Application application, WiringLandDetail wiringLandDetail, String appIdPrefix, String webAppName);
	String saveANDConfirm(Application application, WiringLandDetail wiringLandDetail, String appIdPrefix, String appNoPrefix, String webAppName);
	String saveANDConfirm(Application application,
			WiringLandDetail wiringLandDetail, String appIdPrefix,
			String appNoPrefix, Spestedy spestedy, Spserest Spserest,
			String webAppName);
	List<String> findAreaCodes(String deptId, String webAppName);
	List<String> getApplicationTypes(String deptId, String webAppName);
	void update(Application application,WiringLandDetail wiringLandDetail,WiringLandDetailCon wiringLandDetailcon, String webAppName);
	String save(Application application, WiringLandDetail wiringLandDetail,WiringLandDetailCon wiringLandDetailcon, String appIdPrefix, String webAppName);
	List<String> findAreaCodeNames(String deptId, String webAppName);
	public int updateApplicationSubType(String deptId,String applicationSubType,String applicationNo,String webAppName);
	String findAreaCodeIDs(String deptId,String compNm,String webAppName);
	List<String> getAreasByDeptId(String deptId, String webAppName);
}
