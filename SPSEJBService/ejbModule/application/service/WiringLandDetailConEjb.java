package application.service;

import java.math.BigDecimal;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.PersistenceException;

import application.ejb.WiringLandDetailConDaoRemote;
import application.ejb.WiringLandDetailDaoRemote;
import application.model.WiringInfo;
import application.model.WiringLandDetail;
import application.model.WiringLandDetailCon;
import application.model.WiringLandDetailConPK;
import application.model.WiringLandDetailPK;

public class WiringLandDetailConEjb implements WiringLandDetailConDaoRemote{
	private Context context;
	private WiringLandDetailConDaoRemote dao; 
	 public WiringLandDetailConEjb() {
			super();
			this.dao=lookupDao();
		}

		private WiringLandDetailConDaoRemote lookupDao() {
			try
			{
				 context = new InitialContext();
				 WiringLandDetailConDaoRemote dao = (WiringLandDetailConDaoRemote) context.lookup("WiringLandDetailConDao/remote");
				 //System.out.println(dao);
				 return dao; 
			} catch (NamingException e){
				e.printStackTrace();
				throw new RuntimeException(e);
			}
			
		}
		


		public void createWiringLandDetailCon(WiringLandDetailCon wiringLandDetail, String webAppName) {
			dao.createWiringLandDetailCon(wiringLandDetail, webAppName);
				
			}

		public WiringLandDetailCon findByAppId(WiringLandDetailConPK id, String webAppName) {
			return dao.findByAppId(id, webAppName);
			}

		public void updateWiringLandDetailCon(WiringLandDetailCon wiringLandDetail, String webAppName) {
			dao.updateWiringLandDetailCon(wiringLandDetail, webAppName);
				
			}
		
		@Override
		public List<WiringLandDetailCon> getAll( String webAppName) {
			return dao.getAll( webAppName);
		}

		@Override
		public void removeWiringLandDetailCon(WiringLandDetailCon wiringLandDetail, String webAppName) {
			dao.removeWiringLandDetailCon(wiringLandDetail, webAppName);
			
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

		
		@Override
		public WiringLandDetailCon findByApplicationNo(String applicationId,
				String webAppName) {
			return dao.findByApplicationNo(applicationId, webAppName);
		}

	


}
