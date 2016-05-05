package application.service;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import application.ejb.WiringLandDetailConDaoRemote;
import application.ejb.WiringLandDetailDaoRemote;
//import application.model.WiringLandDetail;
//import application.model.WiringLandDetailPK;
import application.model.WiringLandDetail;
import application.model.WiringLandDetailCon;
import application.model.WiringLandDetailConPK;
import application.model.WiringLandDetailPK;

public class WiringLandDetailConEjb implements WiringLandDetailConEjbI {
	private Context context;
	private WiringLandDetailConDaoRemote dao; 
	private String region=null;
	
	public WiringLandDetailConEjb(String region) {
		super();
		this.region=region;
		dao=lookupDao();
		
	}
	
	
	
	
	private WiringLandDetailConDaoRemote lookupDao() {
		try
		{
			 context = new InitialContext();
			 WiringLandDetailConDaoRemote dao = (WiringLandDetailConDaoRemote) context.lookup("WiringLandDetailConDao/remote");
			 return dao; 
		} catch (NamingException e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}
	
	@Override
	public List<WiringLandDetailCon> getAll() {
		return dao.getAll(region);
	}

	@Override
	public void createWiringLandDetailCon(WiringLandDetailCon wiringLandDetail) {
		dao.createWiringLandDetailCon(wiringLandDetail, region);
		
	}

	@Override
	public WiringLandDetailCon findByAppId(WiringLandDetailConPK id) {
		return dao.findByAppId(id, region);
	}

	@Override
	public void updateWiringLandDetailCon(WiringLandDetailCon wiringLandDetail) {
		dao.updateWiringLandDetailCon(wiringLandDetail, region);
		
	}

	@Override
	public void removeWiringLandDetailCon(WiringLandDetailCon wiringLandDetail) {
		dao.removeWiringLandDetailCon(wiringLandDetail, region);
		
	}

	@Override
	public void removeAll() {
		dao.removeAll(region);
		
	}
	



	


	@Override
	public WiringLandDetailCon findByApplicationNo(String applicationNo) {
		return dao.findByApplicationNo(applicationNo, region);
	}




	
	

}
