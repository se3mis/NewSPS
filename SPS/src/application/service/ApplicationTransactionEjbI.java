package application.service;

import java.util.List;
import java.util.Map;

import costcenter.model.Glcompm;
import costcenter.model.Gldeptm;
import estimate.model.Spestedy;
import estimate.model.Spserest;
import application.model.Application;
import application.model.WiringLandDetail;
import application.model.WiringLandDetailCon;
//import application.model.ApplicationReference;
//import application.model.WiringLandDetail;

public interface ApplicationTransactionEjbI {
	void save(Application application, WiringLandDetail wiringLandDetail);
	void update(Application application, WiringLandDetail wiringLandDetail);
	String update(Application application,String applicationIdPrefix);
	String save(Application application, WiringLandDetail wiringLandDetail, String appIdPrefix);
	String saveANDConfirm(Application application, WiringLandDetail wiringLandDetail, String appIdPrefix, String appNoPrefix);
	String saveANDConfirm(Application application,
			WiringLandDetail wiringLandDetail, String appIdPrefix,
			String appNoPrefix, Spestedy spestedy, Spserest Spserest);
	public List<String> findAreaCodes(String deptId, String webAppName);
	public List<String> getApplicationTypes(String deptId, String webAppName);
	String save(Application application, WiringLandDetail wiringLandDetail,WiringLandDetailCon wiringLandDetailCon, String appIdPrefix);
	void update(Application application, WiringLandDetail wiringLandDetail,WiringLandDetailCon wiringLandDetailCon);
	List<String> findAreaCodeNames(String deptId, String webAppName);
	public int updateApplicationSubType(String deptId,String applicationSubType,String applicationNo);
	String findAreaCodeIDs(String deptId, String compNm);
	List<String> getAreasByDeptId(String deptId, String webAppName);
}
