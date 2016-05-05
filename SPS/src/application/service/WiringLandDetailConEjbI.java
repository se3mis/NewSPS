package application.service;

import java.util.List;

import application.model.WiringLandDetail;
import application.model.WiringLandDetailCon;
import application.model.WiringLandDetailConPK;
import application.model.WiringLandDetailPK;

//import application.model.WiringLandDetail;
//import application.model.WiringLandDetailPK;

public interface WiringLandDetailConEjbI {
	List<WiringLandDetailCon> getAll() ; 
	void createWiringLandDetailCon(WiringLandDetailCon wiringLandDetail);
	WiringLandDetailCon findByAppId(WiringLandDetailConPK id);
	void updateWiringLandDetailCon(WiringLandDetailCon wiringLandDetail); 
	void removeWiringLandDetailCon(WiringLandDetailCon wiringLandDetail)  ;
	void removeAll() ;
	
	WiringLandDetailCon findByApplicationNo(String applicationNo);
}
