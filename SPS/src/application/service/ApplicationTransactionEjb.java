package application.service;

import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import costcenter.model.Glcompm;
import costcenter.model.Gldeptm;

import estimate.model.Spestedy;
import estimate.model.Spserest;

import application.ejb.ApplicationTransactionBeanRemote;
import application.model.Application;
import application.model.WiringLandDetailCon;
//import application.model.Application;
//import application.model.ApplicationReference;
//import application.model.WiringLandDetail;
import application.model.WiringLandDetail;

public class ApplicationTransactionEjb implements ApplicationTransactionEjbI{
	private Context context;
	private ApplicationTransactionBeanRemote bean; 
	private String region=null;
	
	public ApplicationTransactionEjb(String region) {
		super();
		this.region=region;
		this.bean=lookupDao();
	}

	private ApplicationTransactionBeanRemote lookupDao() {
		try
		{
			 context = new InitialContext();
			 ApplicationTransactionBeanRemote bean = (ApplicationTransactionBeanRemote) context.lookup("ApplicationTransactionBean/remote");
			 return bean; 
		} catch (NamingException e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}
	

	@Override
	public void save( Application application,WiringLandDetail wiringLandDetail) {
		bean.save( application,wiringLandDetail, region);
		
	}
	
	@Override
	public String save(Application application, WiringLandDetail wiringLandDetail, String appIdPrefix) {
		return bean.save(application, wiringLandDetail, appIdPrefix, region);
		
	}
	
	@Override
	public void update(Application application,	WiringLandDetail wiringLandDetail) {
		bean.update(application, wiringLandDetail, region);
		
	}
	
	@Override
	public String update(Application application, String applicationIdPrefix) {
		return bean.update(application, applicationIdPrefix, region);
		
	}
	
	/**
	 * @param args
	 */
	

	@Override
	public String saveANDConfirm(Application application, WiringLandDetail wiringLandDetail, String appIdPrefix, String appNoPrefix) {
		return bean.saveANDConfirm(application, wiringLandDetail, appIdPrefix, appNoPrefix, region);
	}
	
	public static void main(String[] args) {
		ApplicationTransactionEjb ejb=new ApplicationTransactionEjb("SMCTesting");

	}

	@Override
	public String saveANDConfirm(Application application, WiringLandDetail wiringLandDetail, String appIdPrefix, String appNoPrefix, Spestedy spestedy, Spserest Spserest) {
		return bean.saveANDConfirm(application, wiringLandDetail, appIdPrefix, appNoPrefix, spestedy, Spserest, region);
	}

	@Override
	public List<String> findAreaCodes(String deptId, String webAppName) {
		return bean.findAreaCodes(deptId,region);
	}

	@Override
	public List<String> getApplicationTypes(String deptId, String webAppName) {
		return bean.getApplicationTypes(deptId,region);
	}

	@Override
	public String save(Application application,
			WiringLandDetail wiringLandDetail,
			WiringLandDetailCon wiringLandDetailCon, String appIdPrefix) {
		return bean.save(application,wiringLandDetail,wiringLandDetailCon,appIdPrefix,region);
	}

	@Override
	public void update(Application application,
			WiringLandDetail wiringLandDetail,
			WiringLandDetailCon wiringLandDetailCon) {
		bean.update(application,wiringLandDetail,wiringLandDetailCon,region);
		
	}

	@Override
	public List<String> findAreaCodeNames(String deptId, String webAppName) {
		return bean.findAreaCodeNames(deptId,region);
	}

	
	public int updateApplicationSubType(String deptId,String applicationSubType,String applicationNo){
		return bean.updateApplicationSubType(deptId,applicationSubType,applicationNo,region);
	}
	
	@Override
	public String findAreaCodeIDs(String deptId, String compNm) {
		return bean.findAreaCodeIDs(deptId,compNm,region);
	}
	
	@Override
	public List<String> getAreasByDeptId(String deptId, String webAppName) {
		return bean.getAreasByDeptId(deptId,region);
	}

}
