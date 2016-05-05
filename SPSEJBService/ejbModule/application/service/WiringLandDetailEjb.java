package application.service;

import java.math.BigDecimal;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.PersistenceException;

import application.ejb.WiringLandDetailDaoRemote;
import application.model.WiringInfo;
import application.model.WiringLandDetail;
import application.model.WiringLandDetailPK;

public class WiringLandDetailEjb implements WiringLandDetailDaoRemote{
	private Context context;
	private WiringLandDetailDaoRemote dao; 
	 public WiringLandDetailEjb() {
			super();
			this.dao=lookupDao();
		}

		private WiringLandDetailDaoRemote lookupDao() {
			try
			{
				 context = new InitialContext();
				 WiringLandDetailDaoRemote dao = (WiringLandDetailDaoRemote) context.lookup("WiringLandDetailDao/remote");
				 //System.out.println(dao);
				 return dao; 
			} catch (NamingException e){
				e.printStackTrace();
				throw new RuntimeException(e);
			}
			
		}
		


		public void createWiringLandDetail(WiringLandDetail wiringLandDetail, String webAppName) {
			dao.createWiringLandDetail(wiringLandDetail, webAppName);
				
			}

		public WiringLandDetail findByAppId(WiringLandDetailPK id, String webAppName) {
			return dao.findByAppId(id, webAppName);
			}

		public void updateWiringLandDetail(WiringLandDetail wiringLandDetail, String webAppName) {
			dao.updateWiringLandDetail(wiringLandDetail, webAppName);
				
			}
		
		@Override
		public List<WiringLandDetail> getAll( String webAppName) {
			return dao.getAll( webAppName);
		}

		@Override
		public void removeWiringLandDetail(WiringLandDetail wiringLandDetail, String webAppName) {
			dao.removeWiringLandDetail(wiringLandDetail, webAppName);
			
		}

		@Override
		public void removeAll( String webAppName) {
			dao.removeAll( webAppName);
			
		}
		
		@Override
		public WiringInfo getWiringInfo(String ApplicationId, String deptId, String webAppName) {
			//System.out.println(ApplicationId+deptId);
			return dao.getWiringInfo(ApplicationId, deptId, webAppName);
		}

	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		WiringLandDetailEjb ejb=new WiringLandDetailEjb();
		WiringLandDetailPK id=new WiringLandDetailPK();
		System.out.println(ejb.findByApplicationNo("547.00/ENC/2011/0001", "SMCTesting"));
		System.out.println(ejb.getServiceCity("547.00/ENC/2011/0001", "547.00", "SMCTesting"));
		System.out.println(ejb.getWiringInfo("R133", "510.20", "SMCTesting"));
		id.setApplicationId("R8");
		id.setDeptId("510.20");
		System.out.println(ejb.findByAppId(id, "SMCTesting"));
		//System.out.println(ejb.getAll("SMCTesting"));
		
		WiringLandDetailPK id4=new WiringLandDetailPK();
		id4.setApplicationId("R35");
		id4.setDeptId("510.20");
		//WiringLandDetail wiringLandDetail4 = new WiringLandDetail(id4, "nvn",
		//		"nvn", "nvn", new BigDecimal("123"), "O", "Y", "Y", "N", "123",
		//		"123", new BigDecimal("1"), new BigDecimal("1"), new BigDecimal("1"),
		//		new BigDecimal("1"), new BigDecimal("1"), new BigDecimal("1"),
		//		new BigDecimal("1"), new BigDecimal("1"),new BigDecimal("1"),
		//		new BigDecimal("11"), new BigDecimal("15"), "vnkvnksd",
		//		"y");
		//ejb.createWiringLandDetail(wiringLandDetail4);

	}

	@Override
	public String getServiceCity(String estimateNo, String deptId,
			String webAppName) {
		return dao.getServiceCity(estimateNo, deptId, webAppName);
	}

	@Override
	public WiringLandDetail findByApplicationNo(String applicationNo,
			String webAppName) {
		return dao.findByApplicationNo(applicationNo, webAppName);
	}

	@Override
	public int updateDemand(String applicationId,Long demand,
			String webAppName){
		try{
		return dao.updateDemand(applicationId,demand, webAppName);
		}catch(Exception e){
			e.printStackTrace();
		}
		return 0;
	}
	

}
