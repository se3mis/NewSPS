package application.ejb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import costcenter.ejb.GlcompmDaoRemote;
import costcenter.ejb.GldeptmDaoRemote;
import costcenter.model.Glcompm;
import costcenter.model.Gldeptm;

import util.common.AppStatus;
import util.emSelect.EmSelector;

import estimate.ejb.PcfunddmDaoRemote;
import estimate.ejb.SpestedyDaoRemote;
import estimate.ejb.SpserestDaoRemote;
import estimate.model.Spestedy;
import estimate.model.Spserest;
import estimate.model.SpserestPK;


import application.model.Application;
import application.model.ApplicationReference;
import application.model.ApplicationReferencePK;
import application.model.WiringLandDetail;
import application.model.WiringLandDetailCon;
import application.model.WiringLandDetailConPK;
import application.model.WiringLandDetailPK;

/**
 * Session Bean implementation class SaveAppDetailDao
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class ApplicationTransactionBean extends EmSelector implements ApplicationTransactionBeanRemote, ApplicationTransactionBeanLocal  {
	
	private Log LOGGER = LogFactory.getLog(ApplicationTransactionBean.class);
	 @Resource
	 private SessionContext context;
	
	@EJB
	ApplicationDaoRemote applicationDaoRemote;
	@EJB 
	WiringLandDetailDaoRemote wiringLandDetailDaoRemote;
	@EJB
	WiringLandDetailConDaoRemote wiringLandDetailConDaoRemote;
	@EJB
	ApplicationReferenceDaoRemote applicationReferenceDaoRemote;
	@EJB 
	SpestedyDaoRemote spestedyDaoRemote;
	@EJB 
	SpserestDaoRemote  spserestDaoRemote;
	@EJB
	GldeptmDaoRemote gldeptmDaoRemote;
	@EJB
	PcfunddmDaoRemote pcfunddmDaoRemote;
	
	@EJB
	GlcompmDaoRemote glcompmDaoRemote;
	/**
     * Default constructor. 
     */
    public ApplicationTransactionBean() {
        // TODO Auto-generated constructor stub
    }
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void save(Application application,WiringLandDetail wiringLandDetail, String webAppName) {
		try{
			
			applicationDaoRemote.createApplication(application,webAppName);
			wiringLandDetailDaoRemote.createWiringLandDetail(wiringLandDetail,webAppName);
			System.out.println("9999999999999999999999999999999999999999999");
		}catch (Exception e) {
			//e.printStackTrace();
			System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%% "+e.getMessage());
			context.setRollbackOnly();
		}
		
	}
	
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public String save(Application application,WiringLandDetail wiringLandDetail, String appIdPrefix, String webAppName) {
		try{
			String nextAppId=applicationDaoRemote.createApplicationAutoId(application, appIdPrefix,webAppName);
			WiringLandDetailPK id=new WiringLandDetailPK();
			id.setApplicationId(nextAppId);
			id.setDeptId(application.getId().getDeptId());
			wiringLandDetail.setId(id);
			wiringLandDetailDaoRemote.createWiringLandDetail(wiringLandDetail,webAppName);
			System.out.println("9999999999999999999999999999999999999999999");
			return nextAppId;
		}catch (Exception e) {
			//e.printStackTrace();
			System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%% "+e.getMessage());
			context.setRollbackOnly();
			return null;
		}
		
	}
	
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void update(Application application,WiringLandDetail wiringLandDetail, String webAppName) {
		try{
			applicationDaoRemote.updateApplication(application,webAppName);
			wiringLandDetailDaoRemote.updateWiringLandDetail(wiringLandDetail,webAppName);
		}catch (Exception e) {
			//e.printStackTrace();
			System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%% "+e.getMessage());
			context.setRollbackOnly();
		}
		
	}
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public String save(Application application,WiringLandDetail wiringLandDetail, WiringLandDetailCon wiringLandDetailcon,String appIdPrefix, String webAppName) {
		try{
			String nextAppId=applicationDaoRemote.createApplicationAutoId(application, appIdPrefix,webAppName);
			
			System.out.println("Hiinnnnnn" + nextAppId);
			WiringLandDetailPK id=new WiringLandDetailPK();
			id.setApplicationId(nextAppId);
			id.setDeptId(application.getId().getDeptId());
			wiringLandDetail.setId(id);
			System.out.println("Hiinnnnnn" + nextAppId);
			WiringLandDetailConPK idc=new WiringLandDetailConPK();
			idc.setApplicationId(nextAppId);
			idc.setDeptId(application.getId().getDeptId());
			wiringLandDetailcon.setId(idc);
			System.out.println("Hiinnnnnn" + nextAppId);
			wiringLandDetailDaoRemote.createWiringLandDetail(wiringLandDetail,webAppName);
			wiringLandDetailConDaoRemote.createWiringLandDetailCon(wiringLandDetailcon,webAppName);
			System.out.println("Hiinnnnnntext" + nextAppId);
			return nextAppId;
		}catch (Exception e) {
			//e.printStackTrace();
			System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%% "+e.getMessage());
			context.setRollbackOnly();
			return null;
		}
		
	}
	
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void update(Application application,WiringLandDetail wiringLandDetail,WiringLandDetailCon wiringLandDetailcon, String webAppName) {
		try{
			applicationDaoRemote.updateApplication(application,webAppName);
			wiringLandDetailDaoRemote.updateWiringLandDetail(wiringLandDetail,webAppName);
			wiringLandDetailConDaoRemote.updateWiringLandDetailCon(wiringLandDetailcon,webAppName);
		}catch (Exception e) {
			//e.printStackTrace();
			System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%% "+e.getMessage());
			context.setRollbackOnly();
		}
		
	}
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public String update(Application application,String applicationIdPrefix, String webAppName) {
		try{	
			String nextsquence=applicationReferenceDaoRemote.getNextApplicationNo(applicationIdPrefix, webAppName);
			String nextApplicationNo=applicationIdPrefix+nextsquence;
			application.setApplicationNo(nextApplicationNo);
			System.out.println("nextApplicationNo %%%%%%%%%%%%%%%%%%%%%%%%%%% "+nextApplicationNo);
			applicationDaoRemote.updateApplication(application,webAppName);
			ApplicationReference appReference=new ApplicationReference();
			ApplicationReferencePK id=new ApplicationReferencePK();
			id.setApplicationId(application.getId().getApplicationId());
			id.setDeptId(application.getId().getDeptId());
			appReference.setId(id);
			appReference.setApplicationNo(nextApplicationNo);
			appReference.setIdNo(application.getIdNo());//Identity Card No
			System.out.println("appReference %%%%%%%%%%%%%%%%%%%%%%%%%%% "+appReference);
			applicationReferenceDaoRemote.createApplicationReference(appReference,webAppName);
			return nextApplicationNo;
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%% "+e.getMessage());
			context.setRollbackOnly();
			return null;
		}	
	}
	@Override
	public String saveANDConfirm(Application application, WiringLandDetail wiringLandDetail, String appIdPrefix, String appNoPrefix, String webAppName) {
		try{
			//CityMap cityMap=new CityMap();
			//setCostCenterNo(cityMap.mapCity(application., serviceCity));
			//App No
			System.out.println(appIdPrefix+" "+appNoPrefix);
			String nextsquence=applicationReferenceDaoRemote.getNextApplicationNo(appNoPrefix, webAppName);
			String nextAppNo=appNoPrefix+nextsquence;
			System.out.println(nextAppNo);
			application.setApplicationNo(nextAppNo);
			//App No
			
			
			String nextAppId=applicationDaoRemote.createApplicationAutoId(application, appIdPrefix,webAppName);
			WiringLandDetailPK id=new WiringLandDetailPK();
			id.setApplicationId(nextAppId);
			id.setDeptId(application.getId().getDeptId());
			wiringLandDetail.setId(id);
			wiringLandDetailDaoRemote.createWiringLandDetail(wiringLandDetail,webAppName);
			//App ref
			ApplicationReference appReference=new ApplicationReference();
			ApplicationReferencePK id2=new ApplicationReferencePK();
			id2.setApplicationId(nextAppId);
			id2.setDeptId(application.getId().getDeptId());
			appReference.setId(id2);
			appReference.setApplicationNo(nextAppNo);
			appReference.setIdNo(application.getIdNo());
			applicationReferenceDaoRemote.createApplicationReference(appReference, webAppName);
			//App ref
			System.out.println("9999999999999999999999999999999999999999999");
			return nextAppId;
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%% "+e.getMessage());
			context.setRollbackOnly();
			return null;
		}
	}
	
	
	@Override
	public String saveANDConfirm(Application application, WiringLandDetail wiringLandDetail, String appIdPrefix, String appNoPrefix,Spestedy spestedy, Spserest  spserest,String webAppName) {
		try{
			//CityMap cityMap=new CityMap();
			//setCostCenterNo(cityMap.mapCity(application., serviceCity));
			//App No
			String nextsquence=applicationReferenceDaoRemote.getNextApplicationNo(appNoPrefix, webAppName);
			String nextAppNo=appNoPrefix+nextsquence;
			System.out.println(nextAppNo);
			application.setApplicationNo(nextAppNo);
			//App No
			
			application.setStatus(AppStatus.ALLOCATED);
			String nextAppId=applicationDaoRemote.createApplicationAutoId(application, appIdPrefix,webAppName);
			WiringLandDetailPK id=new WiringLandDetailPK();
			id.setApplicationId(nextAppId);
			id.setDeptId(application.getId().getDeptId());
			wiringLandDetail.setId(id);
			wiringLandDetailDaoRemote.createWiringLandDetail(wiringLandDetail,webAppName);
			//App ref
			ApplicationReference appReference=new ApplicationReference();
			ApplicationReferencePK id2=new ApplicationReferencePK();
			id2.setApplicationId(nextAppId);
			id2.setDeptId(application.getId().getDeptId());
			appReference.setId(id2);
			appReference.setApplicationNo(nextAppNo);
			appReference.setIdNo(application.getIdNo());
			applicationReferenceDaoRemote.createApplicationReference(appReference, webAppName);
			//App ref
			System.out.println("9999999999999999999999999999999999999999999");
			//
			//applicationDaoRemote.changeStatusApplication(nextAppNo, application.getId().getDeptId(), AppStatus.ALLOCATED, webAppName);
			spestedy.setReferenceNo(nextAppNo);
			SpserestPK spserestPK= spserest.getId();;
			spserestPK.setApplicationNo(nextAppNo);
			spserestPK.setDeptId(application.getId().getDeptId());
			System.out.println(spserestPK+"   "+spestedy);
			spserest.setId(spserestPK);			
			System.out.println("##########");
			spestedyDaoRemote.createSpestedyAutoId(spestedy, webAppName);
			System.out.println("@@@@@@@@@");
			spserestDaoRemote.createSpserest(spserest, webAppName);
			System.out.println("$$$$$$$$$$");
			return nextAppId;
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%% "+e.getMessage());
			context.setRollbackOnly();
			return null;
		}
	}
	@Override
	public List<String> findAreaCodes(String deptId, String webAppName){
		try{
			List<String>  list = new ArrayList<String>();
			list = gldeptmDaoRemote.findAreaCodes(deptId, webAppName);
			
			return list;
		}catch(Exception e){
			LOGGER.info("Exception Occured in findAreaCodes" + e);
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<String> getApplicationTypes(String deptId, String webAppName) {
		try{
			List<String> list =pcfunddmDaoRemote.getApplicationTypes(deptId, webAppName);
			return list;
		}catch(Exception e){
			LOGGER.info("Exception Occured in getApplicationTypes" + e);
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public List<String> findAreaCodeNames(String deptId, String webAppName){
		try{
			List<String>  list = glcompmDaoRemote.getAreasForProvince(deptId,webAppName);

			return list;
		}catch(Exception e){
			LOGGER.info("Exception Occured in findAreaCodes" + e);
			e.printStackTrace();
		}
		return null;
	}
	
	public int updateApplicationSubType(String deptId,String applicationSubType,String applicationNo,String webAppName){
		
		int status = 0;
			//getEntityManager(webAppName);
		String qryStr = "UPDATE Application a set a.applicationSubType=:applicationSubType WHERE TRIM(a.applicationNo)= :applicationNo AND a.id.deptId = :deptId";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("applicationSubType", applicationSubType);
		query.setParameter("applicationNo", applicationNo.trim());
		query.setParameter("deptId", deptId);
		status = query.executeUpdate();
		
		return status;
		
	}
	@Override
	public String  findAreaCodeIDs(String deptId,String compNm,String webAppName){
		try{
			
			String  areacode = glcompmDaoRemote.getAreaCodesByName(deptId,compNm,webAppName);

			return areacode;
		}catch(Exception e){
			LOGGER.info("Exception Occured in findAreaCodes" + e);
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public List<String> getAreasByDeptId(String deptId, String webAppName) {
		try{
			List<String>  list = glcompmDaoRemote.getAreasByDeptId(deptId,webAppName);

			return list;
		}catch(Exception e){
			LOGGER.info("Exception Occured in findAreaCodes" + e);
			e.printStackTrace();
		}
		return null;
	}
    

}
