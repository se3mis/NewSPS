package estimate.ejb;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import application.ejb.ApplicationDaoRemote;

import util.common.AppStatus;
import util.common.AppointmentStatus;
import util.emSelect.EmSelector;
import estimate.model.Spserest;
import estimate.model.SpserestPK;
import estimate.model.Spsetpol;
import estimate.model.SpsetpolPK;
import estimate.model.Spsetstu;
import estimate.model.SpsetstuPK;
import estimate.model.Spsetsty;
import estimate.model.SpsetstyPK;
import estimate.model.Spsetwir;
import estimate.model.SpsetwirPK;

/**
 * Session Bean implementation class Spserest
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class SpserestDao extends EmSelector implements SpserestDaoRemote, SpserestDaoLocal {
	@Resource
	private SessionContext context;
	@EJB
	SpsetpolDaoRemote spsetpolDaoRemote;
	@EJB
	SpsetstuDaoRemote spsetstuDaoRemote;
	@EJB
	SpsetstyDaoRemote spsetstyDaoRemote;
	@EJB
	SpsetwirDaoRemote spsetwirDaoRemote;
	@EJB
	SpestedyDaoRemote spestedyDaoRemote;
	@EJB
	ApplicationDaoRemote applicationDaoRemote;
	/**
     * Default constructor. 
     */
    public SpserestDao() {
        // TODO Auto-generated constructor stub
    }
    
    @Override
	public void createSpserest(Spserest spserest, String webAppName) {
		//getEntityManager(webAppName);
		getEntityManager(webAppName).persist(spserest);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Spserest> getAll(String deptId, String webAppName) {
		//getEntityManager(webAppName);
		String qryStr = "select a from Spserest a where a.id.deptId = :deptId ";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("deptId", deptId);
		List<Spserest> list = query.getResultList();
        return list;
	}

	@Override
	public void updateSpserest(Spserest spserest, String webAppName) {
		//getEntityManager(webAppName);
		getEntityManager(webAppName).merge(spserest);
		
	}

	@Override
	public void removeSpserest(Spserest spserest, String webAppName) {/////////////////////////////////////////revies
		//getEntityManager(webAppName);
		//spserest=getEntityManager(webAppName).merge(spserest);
		getEntityManager(webAppName).remove(spserest);
		
	}

	@Override
	public void removeAll(String webAppName) {
		//getEntityManager(webAppName);
		throw new UnsupportedOperationException("Not supported yet.");
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public Spserest findById(SpserestPK id, String webAppName) throws PersistenceException {
		//getEntityManager(webAppName);
		String qryStr = "select a from Spserest a where a.id.applicationNo = :applicationNo AND  a.id.deptId = :deptId ";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("applicationNo", id.getApplicationNo().trim());
		query.setParameter("deptId", id.getDeptId());
		List<Spserest> list = query.getResultList();
        if (list.isEmpty())
			return null;
        else if (list.size() == 1)
        	return list.get(0);
        throw new NonUniqueResultException();
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Spserest findByApplicationNo(String applicationNo, String webAppName) {
		//getEntityManager(webAppName);
		String qryStr = "select a from Spserest a where a.id.applicationNo = :applicationNo ";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("applicationNo", applicationNo.trim());
		List<Spserest> list = query.getResultList();
        if (list.isEmpty())
			return null;
        else if (list.size() == 1)
        	return list.get(0);
        throw new NonUniqueResultException();
		
	}
	
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void updateServiceEstimate(Spserest spserest, List<Spsetpol> poleList, List<Spsetstu> strutList, List<Spsetsty> stayList, List<Spsetwir> wireList, String webAppName) {
		//getEntityManager(webAppName);
		try{
			if (spserest!=null){
				updateSpserest(spserest, webAppName);
			}
			
			if (poleList!=null){
				for(int i=0; i<=poleList.size()-1; i++){
					SpsetpolPK id=new SpsetpolPK();
					id.setApplicationNo(poleList.get(i).getId().getApplicationNo());
					id.setDeptId(poleList.get(i).getId().getDeptId());
					id.setFromConductor(poleList.get(i).getId().getFromConductor());
					id.setMatCd(poleList.get(i).getId().getMatCd());
					id.setPointType(poleList.get(i).getId().getPointType());
					id.setPoleType(poleList.get(i).getId().getPoleType());
					id.setToConductor(poleList.get(i).getId().getToConductor());
					Spsetpol spsetpol= spsetpolDaoRemote.findById(id, webAppName);
					if (spsetpol!=null){
						spsetpolDaoRemote.updateSpsetpol(spsetpol, webAppName);
					}else{
						spsetpolDaoRemote.createSpsetpol(poleList.get(i), webAppName);
					}
					//poleList.get(i).getId().getApplicationNo()
					//poleList.get(i).getId().get
					//Spsetpol spsetpol= spsetpolDaoRemote.findById(poleList.get(i));
					//spsetpolDaoRemote.updateSpsetpol(poleList.get(i));
				}
			}
			if (strutList!=null){
				for(int i=0; i<=strutList.size()-1; i++){
					SpsetstuPK id=new SpsetstuPK();
					id.setApplicationNo(strutList.get(i).getId().getApplicationNo());
					id.setDeptId(strutList.get(i).getId().getDeptId());
					id.setMatCd(strutList.get(i).getId().getMatCd());
					Spsetstu spsetstu= spsetstuDaoRemote.findById(id, webAppName);
					if (spsetstu!=null){
						spsetstuDaoRemote.updateSpsetstu(spsetstu, webAppName);
					}else{
						spsetstuDaoRemote.createSpsetstu(strutList.get(i), webAppName);
					}
				}
			}
			if (stayList!=null){
				for(int i=0; i<=stayList.size()-1; i++){
					SpsetstyPK id=new SpsetstyPK();
					id.setApplicationNo(strutList.get(i).getId().getApplicationNo());
					id.setDeptId(strutList.get(i).getId().getDeptId());
					id.setMatCd(strutList.get(i).getId().getMatCd());
					Spsetsty spsetsty= spsetstyDaoRemote.findById(id, webAppName);
					if (spsetsty!=null){
						spsetstyDaoRemote.updateSpsetsty(spsetsty, webAppName);
					}else{
						spsetstyDaoRemote.createSpsetsty(stayList.get(i), webAppName);
					}
					//spsetstyDaoRemote.updateSpsetsty(stayList.get(i));
				}
			}
			if (wireList!=null){
				for(int i=0; i<=wireList.size()-1; i++){
					spsetwirDaoRemote.updateSpsetwir(wireList.get(i), webAppName);
				}
			}
		}catch (Exception e) {
			//e.printStackTrace();
			System.out.println(e.getMessage());
			context.setRollbackOnly();
			//throw new RuntimeException(e);
		}
	}
	
	//@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void deleteServiceEstimate(List<SpsetpolPK> delPoleList, List<SpsetstuPK> delStrutList, List<SpsetstyPK> delStayList, List<SpsetwirPK> delWireList, String webAppName) {
		//getEntityManager(webAppName);
		try{
			if (delPoleList!=null){
				for(int i=0; i<=delPoleList.size()-1; i++){
					Spsetpol spsetpol= spsetpolDaoRemote.findById(delPoleList.get(i), webAppName);
					
					if (spsetpol!=null){ 
						spsetpolDaoRemote.removeSpsetpol(spsetpol, webAppName);
					}
				}
			}
			if (delStrutList!=null){
				for(int i=0; i<=delStrutList.size()-1; i++){
					Spsetstu spsetstu= spsetstuDaoRemote.findById(delStrutList.get(i), webAppName);
					if (spsetstu!=null){ 
						spsetstuDaoRemote.removeSpsetstu(spsetstu, webAppName);
					}
				}
			}
			if (delStayList!=null){
				for(int i=0; i<=delStayList.size()-1; i++){
					Spsetsty spsetsty= spsetstyDaoRemote.findById(delStayList.get(i), webAppName);
					if (spsetsty!=null){
						spsetstyDaoRemote.removeSpsetsty(spsetsty, webAppName);
					}
				}
			}
			if (delWireList!=null){
				for(int i=0; i<=delWireList.size()-1; i++){
					Spsetwir spsetwir= spsetwirDaoRemote.findById(delWireList.get(i), webAppName);
					if (spsetwir!=null){
						spsetwirDaoRemote.removeSpsetwir(spsetwir, webAppName);
					}
				}
			}
		}catch (Exception e) {
			//e.printStackTrace();
			System.out.println(e.getMessage());
			context.setRollbackOnly();
			//throw new RuntimeException(e);
		}
	}
 
	/*@Override
	//@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void saveServiceEstimate(Spserest spserest, List<Spsetpol> poleList, List<Spsetstu> strutList, List<Spsetsty> stayList, List<Spsetwir> wireList, List<SpsetpolPK> delPoleList, List<SpsetstuPK> delStrutList, List<SpsetstyPK> delStayList, List<SpsetwirPK> delWireList, String webAppName) {
		try{
			System.out.println("delPoleList "+ delPoleList);
			System.out.println("delStrutList "+ delStrutList);
			System.out.println("delStayList "+  delStayList);
			System.out.println("delWireList "+  delWireList);
			System.out.println("poleList "+ poleList);
			System.out.println("strutList "+ strutList);
			System.out.println("stayList "+  stayList);
			System.out.println("wireList "+  wireList);
			deleteServiceEstimate(delPoleList, delStrutList, delStayList, delWireList);
			updateServiceEstimate(spserest, poleList, strutList, stayList, wireList);
			
		}catch (Exception e, String webAppName) {
			//e.printStackTrace();
			System.out.println(e.getMessage());
			//context.setRollbackOnly();
			//throw new RuntimeException(e);
		}
		
	}*/
	
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void saveServiceEstimate(Spserest spserest, List<Spsetpol> poleList, List<Spsetstu> strutList, List<Spsetsty> stayList, List<Spsetwir> wireList, List<SpsetpolPK> delPoleList, List<SpsetstuPK> delStrutList, List<SpsetstyPK> delStayList, List<SpsetwirPK> delWireList, String webAppName) {
		//getEntityManager(webAppName);
		try{
			if (spserest!=null){
				updateSpserest(spserest, webAppName);
			}
			///////////////////////////////////////////////////
			//System.out.println("delPoleList "+ delPoleList);
			//System.out.println("delStrutList "+ delStrutList);
			//System.out.println("delStayList "+  delStayList);
			//System.out.println("delWireList "+  delWireList);
			//System.out.println("poleList "+ poleList);
			//System.out.println("strutList "+ strutList);
			//System.out.println("stayList "+  stayList);
			//System.out.println("wireList "+  wireList);
			
			if (delPoleList!=null){
				for(int i=0; i<=delPoleList.size()-1; i++){
					Spsetpol spsetpol= spsetpolDaoRemote.findById(delPoleList.get(i), webAppName);
					
					if (spsetpol!=null){ 
						spsetpolDaoRemote.removeSpsetpol(spsetpol, webAppName);
					}
				}
			}
			if (delStrutList!=null){
				for(int i=0; i<=delStrutList.size()-1; i++){
					Spsetstu spsetstu= spsetstuDaoRemote.findById(delStrutList.get(i), webAppName);
					if (spsetstu!=null){ 
						spsetstuDaoRemote.removeSpsetstu(spsetstu, webAppName);
					}
				}
			}
			if (delStayList!=null){
				for(int i=0; i<=delStayList.size()-1; i++){
					Spsetsty spsetsty= spsetstyDaoRemote.findById(delStayList.get(i), webAppName);
					if (spsetsty!=null){
						spsetstyDaoRemote.removeSpsetsty(spsetsty, webAppName);
					}
				}
			}
			if (delWireList!=null){
				for(int i=0; i<=delWireList.size()-1; i++){
					Spsetwir spsetwir= spsetwirDaoRemote.findById(delWireList.get(i), webAppName);
					if (spsetwir!=null){
						spsetwirDaoRemote.removeSpsetwir(spsetwir, webAppName);
					}
				}
			}
			
			//////////////////////////////////////////////////////////////
			if (spserest!=null){
			updateSpserest(spserest, webAppName);
			}
		
			if (poleList!=null){
				for(int i=0; i<=poleList.size()-1; i++){
					SpsetpolPK id=new SpsetpolPK();
					id.setApplicationNo(poleList.get(i).getId().getApplicationNo());
					id.setDeptId(poleList.get(i).getId().getDeptId());
					id.setFromConductor(poleList.get(i).getId().getFromConductor());
					id.setMatCd(poleList.get(i).getId().getMatCd());
					id.setPointType(poleList.get(i).getId().getPointType());
					id.setPoleType(poleList.get(i).getId().getPoleType());
					id.setToConductor(poleList.get(i).getId().getToConductor());
					Spsetpol spsetpol= spsetpolDaoRemote.findById(id, webAppName);
					if (spsetpol!=null){
						spsetpolDaoRemote.updateSpsetpol(spsetpol, webAppName);
					}else{
						spsetpolDaoRemote.createSpsetpol(poleList.get(i), webAppName);
					}
					
					
				}
			}
			if (strutList!=null){
				for(int i=0; i<=strutList.size()-1; i++){
					SpsetstuPK id=new SpsetstuPK();
					id.setApplicationNo(strutList.get(i).getId().getApplicationNo());
					id.setDeptId(strutList.get(i).getId().getDeptId());
					id.setMatCd(strutList.get(i).getId().getMatCd());
					Spsetstu spsetstu= spsetstuDaoRemote.findById(id, webAppName);
					if (spsetstu!=null){
						spsetstuDaoRemote.updateSpsetstu(spsetstu, webAppName);
					}else{
						spsetstuDaoRemote.createSpsetstu(strutList.get(i), webAppName);
					}
					
				}
			}
			if (stayList!=null){
				for(int i=0; i<=stayList.size()-1; i++){
					SpsetstyPK id=new SpsetstyPK();
					id.setApplicationNo(stayList.get(i).getId().getApplicationNo());
					id.setDeptId(stayList.get(i).getId().getDeptId());
					id.setMatCd(stayList.get(i).getId().getMatCd());
					Spsetsty spsetsty= spsetstyDaoRemote.findById(id, webAppName);
					if (spsetsty!=null){
						spsetstyDaoRemote.updateSpsetsty(spsetsty, webAppName);
					}else{
						spsetstyDaoRemote.createSpsetsty(stayList.get(i), webAppName);
					}
					
				}
			}
			if (wireList!=null){
				for(int i=0; i<=wireList.size()-1; i++){
					spsetwirDaoRemote.updateSpsetwir(wireList.get(i), webAppName);
				}
			}
			//For new method
			spestedyDaoRemote.changeStatusAppointments(spserest.getId().getApplicationNo(), spserest.getId().getDeptId(), AppointmentStatus.VISITED, webAppName);
			//applicationDaoRemote.changeStatusApplication(spserest.getId().getApplicationNo(), spserest.getId().getDeptId(), AppStatus.SERVICE_EST_CREATED, webAppName);
			///////////////////////////////////
			
			
		}catch (Exception e) {
			//e.printStackTrace();
			System.out.println(e.getMessage());
			context.setRollbackOnly();
			throw new RuntimeException(e);
		}
		
	}
	
	

}
