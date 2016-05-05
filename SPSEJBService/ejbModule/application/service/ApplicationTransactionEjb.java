package application.service;

import java.math.BigDecimal;
import java.util.Calendar;
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
import application.model.ApplicationPK;
import application.model.WiringLandDetailCon;
//import application.model.ApplicationReference;
import application.model.WiringLandDetail;
import application.model.WiringLandDetailPK;
import application.service.ApplicationEjb;
public class ApplicationTransactionEjb implements ApplicationTransactionBeanRemote{
	private Context context;
	private ApplicationTransactionBeanRemote bean; 

	public ApplicationTransactionEjb() {
		super();
		this.bean=lookupBean();
	}

	private ApplicationTransactionBeanRemote lookupBean() {
		try
		{
			 context = new InitialContext();
			 ApplicationTransactionBeanRemote bean = (ApplicationTransactionBeanRemote) context.lookup("ApplicationTransactionBean/remote");
			 //System.out.println(dao);
			 return bean; 
		} catch (NamingException e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}
	@Override
	public void save(Application application,WiringLandDetail wiringLandDetail, String webAppName ) {
		bean.save(application,wiringLandDetail, webAppName);
		
	}
	
	@Override
	public void update(Application application,	WiringLandDetail wiringLandDetail, String webAppName) {
		bean.update(application, wiringLandDetail, webAppName);
		
	}
	
	@Override
	public String update(Application application,String applicationIdPrefix, String webAppName) {
		return bean.update(application,applicationIdPrefix, webAppName);
		
	}
	
	@Override
	public String save(Application application, WiringLandDetail wiringLandDetail, String appIdPrefix, String webAppName) {
		return bean.save(application, wiringLandDetail, appIdPrefix, webAppName);
		
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ApplicationTransactionEjb ejb=new ApplicationTransactionEjb();
	
		//daoTest.updateApplication(application);
		WiringLandDetailEjb wiringLandDetailEjb=new WiringLandDetailEjb();
		WiringLandDetailPK id2=new WiringLandDetailPK();
		id2.setApplicationId("R8");
		id2.setDeptId("510.20");
		WiringLandDetail wiringLandDetail=wiringLandDetailEjb.findByAppId(id2, "SMCTesting");
		System.out.println(wiringLandDetail);
		wiringLandDetail.setServiceStreetAddress("ddddddd");
		//daoTest2.updateWiringLandDetail(wiringLandDetail);
		ApplicationEjb applicationEjb=new ApplicationEjb();
		//ApplicationPK id=new ApplicationPK("R8", "510.20");
		//Application application=applicationEjb.findByAppId(id);
		//application.setApplicationType("NL");
		//ejb.save(wiringLandDetail, application);
		System.out.println(wiringLandDetailEjb.findByAppId(id2, "SMCTesting"));
		//System.out.println(applicationEjb.findByAppId(id));
		//ApplicationPK id3=new ApplicationPK("R35", "510.20");
		ApplicationPK id3=new ApplicationPK();
		id3.setApplicationId("R67");
		id3.setDeptId("510.20");
		Calendar calendar = Calendar.getInstance();
		//Application application3 = new Application(id3, null, "NL", calendar.getTime(), "123",
		//		"d", null,null,null, null,null,null,null, "N",
		//		"d", calendar.getTime(), null,
		//		null, null, null, null);
		
		
		//setSubmitDate(calendar.getTime());
		
		//setPreparedBy(getLoggedInUserId());
		//setAddUser(getLoggedInUserId());
		//setAddDate(calendar.getTime());
		//setAddTime(getFormat().FormatTime());
		
		/*Application application = new Application(applicationPK, null, getApplicationType(), getSubmitDate(), getIdNo(),
				getPreparedBy(), getConfirmedBy(),getConfirmDate(),getConfirmTime(), getAllocatedBy(),getAllocatedTo(),getAllocatedDate(),getAllocatedTime(), getStatus(),
				getAddUser(), getAddDate(), getAddTime(),
				getUpdUser(), getUpdDate(), getUpdTime(), getDescription());*/
		
		//WiringLandDetailPK id4=new WiringLandDetailPK("R35", "510.20");
		WiringLandDetailPK id4=new WiringLandDetailPK();
		id4.setApplicationId("R67");
		id4.setDeptId("510.20");
		
		WiringLandDetail wiringLandDetail5=new WiringLandDetail();
		wiringLandDetail5.setId(id4);
		wiringLandDetail5.setServiceStreetAddress("dfd");
		wiringLandDetail5.setServiceSuburb("fsdf");
		wiringLandDetail5.setServiceCity("sdv"); 
		wiringLandDetail5.setServicePostalCode(new BigDecimal("123"));
		wiringLandDetail5.setOwnership("O");
		wiringLandDetail5.setOccupyOwnerCertified("Y");
		wiringLandDetail5.setIsGovernmentPlace("Y");
		//wiringLandDetail5.setLandDispute("N");
		wiringLandDetail5.setAssessmentNo("11");
		wiringLandDetail5.setNeighboursAccNo("11");
		wiringLandDetail5.setNoOfBulbs(new BigDecimal("1"));
		wiringLandDetail5.setNoOfFans(new BigDecimal("1"));
		wiringLandDetail5.setNoOfPlugs15a(new BigDecimal("1"));
		wiringLandDetail5.setNoOfPlugs15a(new BigDecimal("1"));
		wiringLandDetail5.setMotorTotal(new BigDecimal("1"));
		wiringLandDetail5.setWeldingPlant(new BigDecimal("1"));
		wiringLandDetail5.setMetalCrusher(new BigDecimal("1"));
		wiringLandDetail5.setSawMills(new BigDecimal("1"));
		wiringLandDetail5.setPhase(new BigDecimal("1"));
		wiringLandDetail5.setTariffCode("11");
		wiringLandDetail5.setConnectionType(new BigDecimal("15"));
		wiringLandDetail5.setCustomerCategory("ddd");
		//wiringLandDetail5.setIsLoopService("y");
		
		
		//ejb.save(application3,wiringLandDetail5);
		System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
		Application application2=null;
		WiringLandDetail wiringLandDetail2=null;
		//System.out.println(application2=applicationEjb.findByAppId(id));
		System.out.println(wiringLandDetail2=wiringLandDetailEjb.findByAppId(id2, "SMCTesting"));
		//application2.setConfirmedBy("Z");
		//wiringLandDetail2.setAssessmentNo("555");
		//ejb.update(application2, wiringLandDetail2);
		System.out.println(ejb.update(application2,"510.20/SNL/2010/", "SMCTesting"));
	}

	@Override
	public String saveANDConfirm(Application application, WiringLandDetail wiringLandDetail, String appIdPrefix,  String appNoPrefix, String webAppName) {
		return bean.saveANDConfirm(application, wiringLandDetail, appIdPrefix, appNoPrefix, webAppName);
	}

	@Override
	public String saveANDConfirm(Application application,
			WiringLandDetail wiringLandDetail, String appIdPrefix,
			String appNoPrefix, Spestedy spestedy, Spserest Spserest,
			String webAppName) {
		return bean.saveANDConfirm(application, wiringLandDetail, appIdPrefix, appNoPrefix, spestedy, Spserest, webAppName);
	}

	@Override
	public List<String> findAreaCodes(String deptId, String webAppName) {
		// TODO Auto-generated method stub
		return bean.findAreaCodes(deptId, webAppName);
	}

	@Override
	public List<String> getApplicationTypes(String deptId, String webAppName) {
		// TODO Auto-generated method stub
		return bean.getApplicationTypes(deptId, webAppName);
	}

	@Override
	public void update(Application application,
			WiringLandDetail wiringLandDetail,
			WiringLandDetailCon wiringLandDetailcon, String webAppName) {
		bean.update(application,wiringLandDetail,wiringLandDetailcon, webAppName);
		
	}

	@Override
	public String save(Application application,
			WiringLandDetail wiringLandDetail,
			WiringLandDetailCon wiringLandDetailcon, String appIdPrefix,
			String webAppName) {
		return bean.save(application,wiringLandDetail,wiringLandDetailcon,appIdPrefix, webAppName);
	}

	@Override
	public List<String> findAreaCodeNames(String deptId, String webAppName) {
		return bean.findAreaCodeNames(deptId,webAppName);
	}

	@Override
	public int updateApplicationSubType(String deptId,
			String applicationSubType, String applicationNo, String webAppName) {
		// TODO Auto-generated method stub
		return bean.updateApplicationSubType(deptId,applicationSubType,applicationNo,webAppName);
	}

	@Override
	public String findAreaCodeIDs(String deptId, String compNm,
			String webAppName) {
		return bean.findAreaCodeIDs(deptId,compNm,webAppName);
	}

	@Override
	public List<String> getAreasByDeptId(String deptId, String webAppName) {
		return bean.getAreasByDeptId(deptId,webAppName);
	}
	

	

	
	
	

}
