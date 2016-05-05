package application.service;

import java.util.List;

import application.model.WiringLandDetail;
import application.model.WiringLandDetailPK;

//import application.model.WiringLandDetail;
//import application.model.WiringLandDetailPK;

public interface WiringLandDetailEjbI {
	List<WiringLandDetail> getAll() ; 
	void createWiringLandDetail(WiringLandDetail wiringLandDetail);
	WiringLandDetail findByAppId(WiringLandDetailPK id);
	void updateWiringLandDetail(WiringLandDetail wiringLandDetail); 
	void removeWiringLandDetail(WiringLandDetail wiringLandDetail)  ;
	void removeAll() ;
	String getServiceCity(String estimateNo, String deptId);
	WiringLandDetail findByApplicationNo(String applicationNo);
	int updateDemand(String applicationId,Long demand,String webAppName);
}
