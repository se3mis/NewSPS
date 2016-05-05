package application.service;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import application.ejb.WiringLandDetailDaoRemote;
//import application.model.WiringLandDetail;
//import application.model.WiringLandDetailPK;
import application.model.WiringLandDetail;
import application.model.WiringLandDetailPK;

public class WiringLandDetailEjb implements WiringLandDetailEjbI {
	private Context context;
	private WiringLandDetailDaoRemote dao; 
	private String region=null;
	
	public WiringLandDetailEjb(String region) {
		super();
		this.region=region;
		dao=lookupDao();
		
	}
	
	
	
	
	private WiringLandDetailDaoRemote lookupDao() {
		try
		{
			 context = new InitialContext();
			 WiringLandDetailDaoRemote dao = (WiringLandDetailDaoRemote) context.lookup("WiringLandDetailDao/remote");
			 return dao; 
		} catch (NamingException e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}
	
	@Override
	public List<WiringLandDetail> getAll() {
		return dao.getAll(region);
	}

	@Override
	public void createWiringLandDetail(WiringLandDetail wiringLandDetail) {
		dao.createWiringLandDetail(wiringLandDetail, region);
		
	}

	@Override
	public WiringLandDetail findByAppId(WiringLandDetailPK id) {
		return dao.findByAppId(id, region);
	}

	@Override
	public void updateWiringLandDetail(WiringLandDetail wiringLandDetail) {
		dao.updateWiringLandDetail(wiringLandDetail, region);
		
	}

	@Override
	public void removeWiringLandDetail(WiringLandDetail wiringLandDetail) {
		dao.removeWiringLandDetail(wiringLandDetail, region);
		
	}

	@Override
	public void removeAll() {
		dao.removeAll(region);
		
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		WiringLandDetailEjb ejb=new WiringLandDetailEjb("Testing");
		WiringLandDetailPK id=new WiringLandDetailPK();
		id.setApplicationId("R8");
		id.setDeptId("510.20");
		System.out.println(ejb.findByAppId(id));
		System.out.println(ejb.getAll());

	}




	@Override
	public String getServiceCity(String estimateNo, String deptId) {
		return dao.getServiceCity(estimateNo, deptId, region);
	}




	@Override
	public WiringLandDetail findByApplicationNo(String applicationNo) {
		return dao.findByApplicationNo(applicationNo, region);
	}


	@Override
	public int updateDemand(String applicationId,Long demand,
			String webAppName) {
		return dao.updateDemand(applicationId,demand, region);
	}
	

	

}
