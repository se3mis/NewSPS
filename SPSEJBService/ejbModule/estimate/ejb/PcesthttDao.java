package estimate.ejb;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
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
import javax.persistence.EntityManager;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import piv.ejb.PivDetailDaoRemote;

import security.ejb.SecurityBeanRemote;
import util.common.Constants;
import util.common.EstimateStatus;
import util.common.Format;
import util.common.StandardEstimateStatus;
import util.emSelect.EmSelector;
import job.model.JobRecord;
import com.model.App;
import costcenter.model.TestObject;
import estimate.model.Appestlim;
import estimate.model.Approval;
import estimate.model.EstimateReference;
import estimate.model.EstimateReferencePK;
import estimate.model.Pcesthtt;
import estimate.model.PcesthttPK;
import estimate.model.ReferenceDeleteInfo;
import estimate.model.SpeststdPK; 
import estimate.model.Spstdesthmt;
import estimate.model.SpstdesthmtPK;


/**
 * Session Bean implementation class PcesthttDao
 */
@TransactionManagement(TransactionManagementType.CONTAINER)
@Stateless
public class PcesthttDao extends EmSelector implements PcesthttDaoRemote, PcesthttDaoLocal {
	@Resource
	private SessionContext context;
	
	@EJB
	ApprovalDaoRemote approvalDaoRemote;
	@EJB
	SpeststdDaoRemote speststdDaoRemote;
	@EJB
	SecurityBeanRemote securityBeanRemote;
	@EJB
	PivDetailDaoRemote pivDetailDaoRemote;
	@EJB
	EstimateReferenceDaoRemote estimateReferenceDaoRemote;
	
	@EJB
	SpstdesthmtDaoRemote spstdesthmtDaoRemote;
    /**
     * Default constructor. 
     */
    public PcesthttDao() {
        // TODO Auto-generated constructor stub
    }
    
    @Override
	public void createPcesthtt(Pcesthtt pcesthtt, String webAppName) {
		////getEntityManager(webAppName);
		getEntityManager(webAppName).persist(pcesthtt);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Pcesthtt> getAll(String webAppName) {
		//getEntityManager(webAppName);
		String qryStr = "select a from Pcesthtt a";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
        List<Pcesthtt> list = query.getResultList();
        return list;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Pcesthtt> getAll(String deptId, String webAppName) {
		//getEntityManager(webAppName);
		String qryStr = "select a from Pcesthtt a where g.id.deptId = :deptId";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
        List<Pcesthtt> list = query.getResultList();
        return list;
	}

	@Override
	public void updatePcesthtt(Pcesthtt pcesthtt, String webAppName) {
		//getEntityManager(webAppName);
		getEntityManager(webAppName).merge(pcesthtt);
		
	}

	@Override
	public void removePcesthtt(Pcesthtt pcesthtt, String webAppName) {
		//getEntityManager(webAppName);
		//System.out.println(pcesthtt);
		pcesthtt=getEntityManager(webAppName).merge(pcesthtt);
		getEntityManager(webAppName).remove(pcesthtt);
		
	}

	@Override
	public void removeAll(String webAppName) {
		//getEntityManager(webAppName);
		throw new UnsupportedOperationException("Not supported yet.");
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public Pcesthtt findById(PcesthttPK id, String webAppName) throws PersistenceException {
		//getEntityManager(webAppName);
		//return getEntityManager(webAppName).find(Pcesthtt.class, id);
		String qryStr = "SELECT g FROM Pcesthtt g WHERE TRIM(g.id.estimateNo) = :estimateNo AND g.id.deptId = :deptId AND g.id.revNo = :revNo";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("estimateNo", id.getEstimateNo().trim());
		query.setParameter("deptId", id.getDeptId());
		query.setParameter("revNo", id.getRevNo());
		List<Pcesthtt> list = query.getResultList();		
		if (list.isEmpty())
			return null;
        else if (list.size() == 1)
        	return list.get(0);
        throw new NonUniqueResultException();
		
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public Pcesthtt findById(String estimateNo,String deptId,Long revNo, String webAppName) throws PersistenceException {
		//getEntityManager(webAppName);
		String qryStr = "SELECT g FROM Pcesthtt g WHERE TRIM(g.id.estimateNo) = :estimateNo AND g.id.deptId = :deptId AND g.id.revNo = :revNo";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("estimateNo", estimateNo.trim());
		query.setParameter("deptId", deptId);
		query.setParameter("revNo", revNo);
		List<Pcesthtt> list = query.getResultList();		
		if (list.isEmpty())
			return null;
        else if (list.size() == 1)
        	return list.get(0);
        throw new NonUniqueResultException();
				
		
	}

	@Override
	public List<Pcesthtt> getJobinfo(Long status, String webAppName) {
		//getEntityManager(webAppName);
		throw new UnsupportedOperationException("Not supported yet.");
	}

	//@SuppressWarnings("unchecked")
	@SuppressWarnings("unchecked")
	@Override
	public Pcesthtt findByEstimationNo(String estimateNo,String deptId, String webAppName) {
			//getEntityManager(webAppName);
			//String qryStr = "SELECT g FROM Pcesthtt g WHERE TRIM(g.id.estimateNo) = :estimateNo AND g.id.deptId = :deptId";
			String qryStr = "SELECT g FROM Pcesthtt g WHERE TRIM(g.id.estimateNo) = :estimateNo AND g.id.deptId = :deptId AND g.id.revNo = :revNo";
			Query query = getEntityManager(webAppName).createQuery(qryStr);
			query.setParameter("estimateNo", estimateNo.trim());
			query.setParameter("deptId", deptId);
			query.setParameter("revNo", new Long(1));
			List<Pcesthtt> list = query.getResultList();
			if (list.isEmpty())
				return null;
	        else if (list.size() == 1)
	        	return list.get(0);
	        throw new NonUniqueResultException();

			
		
	}
	@SuppressWarnings("unchecked")
	@Override
	public Pcesthtt findByEstimationNo(String estimateNo,String deptId,Long revNo, String webAppName) {
			//getEntityManager(webAppName);
			//String qryStr = "SELECT g FROM Pcesthtt g WHERE TRIM(g.id.estimateNo) = :estimateNo AND g.id.deptId = :deptId";
			String qryStr = "SELECT g FROM Pcesthtt g WHERE TRIM(g.id.estimateNo) = :estimateNo AND g.id.deptId = :deptId AND g.id.revNo = :revNo";
			Query query = getEntityManager(webAppName).createQuery(qryStr);
			query.setParameter("estimateNo", estimateNo.trim());
			query.setParameter("deptId", deptId);
			query.setParameter("revNo", revNo);
			System.out.println("EE 433 : ");
			List<Pcesthtt> list = query.getResultList();
			if (list.isEmpty())
				return null;
	        else if (list.size() == 1)
	        	return list.get(0);
	        throw new NonUniqueResultException();

			
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Pcesthtt findByEstimationNo(String estimateNo, String webAppName) {
			//getEntityManager(webAppName);
			String qryStr = "SELECT g FROM Pcesthtt g WHERE TRIM(g.id.estimateNo) = :estimateNo AND g.id.revNo = :revNo";
			//String qryStr = "SELECT g FROM Pcesthtt g WHERE TRIM(g.id.estimateNo) = :estimateNo";
			Query query = getEntityManager(webAppName).createQuery(qryStr);
			query.setParameter("estimateNo", estimateNo.trim());
			query.setParameter("revNo", new Long(1));
			List<Pcesthtt> list = query.getResultList();
			if (list.isEmpty())
				return null;
	        else if (list.size() == 1)
	        	return list.get(0);
	        throw new NonUniqueResultException();

			
		
	}

	@Override
	public List<String> getEstimateNoList(String webAppName) {
		//getEntityManager(webAppName);
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@SuppressWarnings("unchecked")
	@Override
	public Pcesthtt getJobDetails1(Long status, String webAppName) {
		//getEntityManager(webAppName);
		String qryStr = "SELECT g FROM Pcesthtt g WHERE g.status = :status";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		//query.setParameter("deptId", deptId);
		query.setParameter("status", status);
		List<Pcesthtt> resultList = query.getResultList();
				
		return resultList.get(0);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Pcesthtt> getEstimateList(String deptId,Long status, String webAppName) {
		//getEntityManager(webAppName);
		Query query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthtt g WHERE trim(g.id.deptId)= :deptId AND  g.status = :status order by g.id.estimateNo");
		query.setParameter("deptId", deptId);
		query.setParameter("status", status);
		List<Pcesthtt> resultList = query.getResultList();
		return resultList;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Pcesthtt> getEstimateList(String deptId, Long revNo, Long status, String webAppName) {
		//getEntityManager(webAppName);
		Query query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthtt g WHERE trim(g.id.deptId)= :deptId AND g.id.revNo= :revNo AND g.status = :status order by g.id.estimateNo");
		query.setParameter("deptId", deptId);
		query.setParameter("revNo", revNo);
		query.setParameter("status", status);
		List<Pcesthtt> resultList = query.getResultList();
		return resultList;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<String> getEstimateNoList(String deptId, Long revNo, Long status, String webAppName) {
		//getEntityManager(webAppName);
		Query query = getEntityManager(webAppName).createQuery("SELECT TRIM(g.id.estimateNo) FROM Pcesthtt g WHERE trim(g.id.deptId)= :deptId AND g.id.revNo= :revNo AND g.status = :status order by g.id.estimateNo");
		query.setParameter("deptId", deptId);
		query.setParameter("revNo", revNo);
		query.setParameter("status", status);
		List<String> resultList = query.getResultList();
		return resultList;
	}
	
	@Override
	public List<App> getAppList(String webAppName) {
		//getEntityManager(webAppName);
		App app1=new App("one", "two", "three");
		App app2=new App("1", "2", "3");
		List<App> list=new ArrayList<App>();
		list.add(app1);
		list.add(app2);
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<String> getEstimateNoList(EntityManager emf, String webAppName) {
		//getEntityManager(webAppName);
		Query query = emf.createQuery("SELECT g FROM Pcesthtt g order by g.id.estimateNo order by g.id.estimateNo");
		List<Pcesthtt> noList = query.getResultList();
		List<String> estimateNumberList = new  ArrayList<String>();

		Iterator<Pcesthtt> it = noList.iterator();		
        while (it.hasNext()) 
        { 
        	estimateNumberList.add( it.next().getId().getEstimateNo());
        }
		return estimateNumberList;
	}
	
	
	
	@SuppressWarnings("unchecked")
	@Override
	// order by g.id.estimateNo
	public List<String> getEstimateNoList(String deptId, String webAppName) {
		//getEntityManager(webAppName);
		Query query = getEntityManager(webAppName).createQuery("SELECT TRIM(g.id.estimateNo) FROM Pcesthtt g WHERE trim(g.id.deptId)= :deptId order by g.id.estimateNo");
		query.setParameter("deptId", deptId);
		List<String> list = query.getResultList();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<String> getEstimateNoList(String deptId, Long status, String webAppName) {
		//getEntityManager(webAppName);
		Query query = getEntityManager(webAppName).createQuery("SELECT TRIM(g.id.estimateNo) FROM Pcesthtt g WHERE trim(g.id.deptId)= :deptId AND g.status =:status order by g.id.estimateNo");
		query.setParameter("deptId", deptId);
		query.setParameter("status", status);
		List<String> list = query.getResultList();
		return list;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<String> getEstimateNoList(String deptId, List<Long> status,String username, String webAppName) {
		//getEntityManager(webAppName);
		StringBuffer buff = new StringBuffer();
		buff.append("SELECT TRIM(g.id.estimateNo) FROM Pcesthtt g WHERE trim(g.id.deptId)= :deptId AND trim(g.id.revNo)= :revNo  AND g.status =:status order by g.id.estimateNo");
		
		if(username != null){
			buff.append(" AND g.aprUid5=:assignTo ");
		}
		
		Query query = getEntityManager(webAppName).createQuery(buff.toString());
		query.setParameter("deptId", deptId);
		query.setParameter("status", status);
		query.setParameter("revNo", 2L);
		if(username != null){
			query.setParameter("assignTo",username );
		}
		//query.setParameter("userId", userId);
		List<String> list = query.getResultList();
		if (list.isEmpty())
			return null;
        else {
        	return list;
        }
		
		
		/*Query query = getEntityManager(webAppName).createQuery("SELECT TRIM(g.id.estimateNo) FROM Pcesthtt g WHERE trim(g.id.deptId)= :deptId AND g.status =:status order by g.id.estimateNo");
		query.setParameter("deptId", deptId);
		query.setParameter("status", status);
		List<String> list = query.getResultList();*/
		//return list;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<String> getEstimateNoList(String deptId, Long revNo, List<Long> status,String userId, String webAppName) {
		//getEntityManager(webAppName);
		//Query query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthtt g WHERE trim(g.id.deptId)= :deptId AND g.id.revNo= :revNo AND g.status = :status AND g.entBy = :entBy order by g.id.estimateNo");
		StringBuffer qryStr = new StringBuffer();
		qryStr.append("SELECT g.id.estimateNo FROM Pcesthtt g WHERE trim(g.id.deptId)= :deptId AND g.id.revNo= :revNo AND g.status in (:status) ");
		if(userId != null){ 
			qryStr.append(" AND g.entBy = :entBy ");
			 
		}
		qryStr.append(" order by g.id.estimateNo ");
		Query query = getEntityManager(webAppName).createQuery(qryStr.toString());
		query.setParameter("deptId", deptId);
		query.setParameter("revNo", revNo);
		query.setParameter("status", status);
		
		if(userId != null){ 
			query.setParameter("entBy", userId);
		}
		List<String> resultList = query.getResultList();
		return resultList;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<String> getEstimateNoListT(String deptId, Long status, String applicationType, String webAppName) {
		//getEntityManager(webAppName);
		Query query = getEntityManager(webAppName).createQuery("SELECT TRIM(g.id.estimateNo) FROM Pcesthtt g, Application a WHERE  TRIM(g.id.estimateNo)=a.applicationNo AND  trim(g.id.deptId)= :deptId AND g.status =:status AND a.applicationType=:applicationType order by g.id.estimateNo");
		query.setParameter("applicationType", applicationType);
		query.setParameter("deptId", deptId);
		query.setParameter("status", status);
		List<String> list = query.getResultList();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<String> getEstimateNoListMT_SA(String deptId, Long status, String webAppName) {
		//getEntityManager(webAppName);
		Query query = getEntityManager(webAppName).createQuery("SELECT TRIM(g.id.estimateNo) FROM Pcesthtt g, Application a WHERE  TRIM(g.id.estimateNo)=a.applicationNo AND  trim(g.id.deptId)= :deptId AND g.status =:status AND (a.applicationType='MT' OR a.applicationType='LS' OR a.applicationType='PS' OR a.applicationType='SA' OR a.applicationType='BD' OR a.applicationType='CC'  OR a.applicationType='CS' OR a.applicationType= 'CO'  OR a.applicationType='TR'  OR a.applicationType='TP'  OR a.applicationType='MR' OR a.applicationType= 'PF' OR a.applicationType= 'MF' OR a.applicationType= 'DB' OR a.applicationType= 'RM')  order by g.id.estimateNo");
		//query.setParameter("applicationType", applicationType);
		query.setParameter("deptId", deptId);
		query.setParameter("status", status);
		List<String> list = query.getResultList();
		return list;
	}
	
	//@SuppressWarnings("unchecked")
	//@Override
	//public List<String> getEstimateNoList(String deptId, BigDecimal status, String applicationType, String applicationSubType, String webAppName) {
	//	Query query = getEntityManager(webAppName).createQuery("SELECT TRIM(g.id.estimateNo) FROM Pcesthtt g, Application a WHERE  TRIM(g.id.estimateNo)=a.applicationNo AND   trim(g.id.deptId)= :deptId AND g.status =:status AND a.applicationType=:applicationType AND a.applicationSubType=:applicationSubType  order by g.id.estimateNo");
	//	query.setParameter("applicationType", applicationType);
	//	query.setParameter("deptId", deptId);
	//	query.setParameter("status", status);
	//	query.setParameter("applicationSubType", applicationSubType);
	//	List<String> list = query.getResultList();
	//	return list;
	//}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<String> getEstimateNoListST(String deptId, Long status, String applicationType, String applicationSubType, String webAppName) {
		//getEntityManager(webAppName);
		Query query = getEntityManager(webAppName).createQuery("SELECT TRIM(g.id.estimateNo) FROM Pcesthtt g, Application a WHERE  TRIM(g.id.estimateNo)=a.applicationNo AND   trim(g.id.deptId)= :deptId AND g.status =:status AND a.applicationType=:applicationType AND a.applicationSubType=:applicationSubType  order by g.id.estimateNo");
		query.setParameter("deptId", deptId);
		query.setParameter("status", status);
		query.setParameter("applicationType", applicationType);
		query.setParameter("applicationSubType", applicationSubType);
		List<String> list = query.getResultList();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ReferenceDeleteInfo> getReferenceInfoList(String deptId, Long status, String webAppName) {
		//getEntityManager(webAppName);
		//List<ReferenceDeleteInfo> infoList2=new ArrayList<ReferenceDeleteInfo>();
		//Query query = getEntityManager(webAppName).createQuery("SELECT TRIM(g.id.estimateNo), TRIM(g.id.deptId),TRIM(g.id.revNo()),TRIM(etimateDt),TRIM(g.estType) FROM Pcesthtt g WHERE trim(g.id.deptId)= :deptId order by g.id.estimateNo");
		Query query = getEntityManager(webAppName).createQuery("SELECT new estimate.model.ReferenceDeleteInfo(TRIM(g.id.estimateNo), g.id.deptId, g.id.revNo, g.etimateDt, TRIM(g.estType))  FROM Pcesthtt g WHERE trim(g.id.deptId)= :deptId AND g.status =:status order by g.id.estimateNo  ");
		query.setParameter("deptId", deptId);
		query.setParameter("status", status);
		List<ReferenceDeleteInfo> list = query.getResultList();
		
		//Iterator<Pcesthtt> it = list.iterator();		
       // while (it.hasNext()) 
       // { 
       /// 	ReferenceDeleteInfo referenceDeleteInfo= new ReferenceDeleteInfo();
       // 	Pcesthtt pcesthtt = it.next();
       // 	referenceDeleteInfo.setEstimateNo(pcesthtt.getId().getEstimateNo().trim());
       // 	referenceDeleteInfo.setDeptId(pcesthtt.getId().getDeptId().trim());
       // 	referenceDeleteInfo.setRevNo(pcesthtt.getId().getRevNo());
       // 	referenceDeleteInfo.setEtimateDt(pcesthtt.getEtimateDt());
       // 	referenceDeleteInfo.setEstType(pcesthtt.getEstType().trim());
       // 	infoList2.add(referenceDeleteInfo);
      // }
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<JobRecord> getJobRecordInfoList(String deptId, Long status, String webAppName) {
		//getEntityManager(webAppName);
		List<JobRecord> infoList2=new ArrayList<JobRecord>();
		Query query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthtt g WHERE trim(g.id.deptId)= :deptId AND g.status = :status order by g.id.estimateNo");
		//							  SELECT g FROM Pcesthtt g WHERE trim(g.id.deptId)= :deptId AND g.status = :status"
		query.setParameter("deptId", deptId);
		query.setParameter("status", status);
		List<Pcesthtt> list = query.getResultList();
		Iterator<Pcesthtt> it = list.iterator();		
        while (it.hasNext()) 
        { 
        	JobRecord jobRecord= new JobRecord();
        	Pcesthtt pcesthtt = it.next();
        	jobRecord.setEstimateNo(pcesthtt.getId().getEstimateNo().trim());
        	jobRecord.setEstimateDate(pcesthtt.getEtimateDt());
        	jobRecord.setJobDescription(pcesthtt.getDescr());
        	infoList2.add(jobRecord);
        	
       }
		return infoList2;
	}

/*	
	@Override
	@SuppressWarnings({ "unchecked", "rawtypes"})
	public String approveEstimate(String estimateNo, String deptId,	String authorityLevel, String userName, String webAppName) {
		//SmsEjb smsEjb=new SmsEjb();
		//getEntityManager(webAppName);
		//Limits
		 
		//System.out.println(userName+ authorityLevel);
		System.out.println("FFFFFFFFFFFFFFFFFFFFFFFFFFFF    .uuuuuuuuuuuuuuuuuuuuuuuuu");
		
		try{
			
			//System.out.println(userName+securityBeanRemote.getCostCenter(userName, webAppName)+ authorityLevel);
			Appestlim appestlim=findAppEstLimits(securityBeanRemote.getCostCenter(userName, webAppName), "EST","NC", authorityLevel, webAppName);
			System.out.println("appestlim "+appestlim);
			if (appestlim!=null){
				Double limitFrom=Double.parseDouble(String.valueOf(appestlim.getId().getLimitFrom()));
				Double limitTo=Double.parseDouble(String.valueOf(appestlim.getId().getLimitTo()));
				String statusFrom=String.valueOf(appestlim.getId().getStatusFrom());
				String statusTo=String.valueOf(appestlim.getId().getStatusTo());
				System.out.println("appestlim "+appestlim);
				//		
				Query query=null;
				BigDecimal totalCost = null;
				String status = null;
				Double dbTotalCost=0.0;
				query = getEntityManager(webAppName).createQuery("SELECT new map(p.status, s.totalCost) FROM Pcesthtt p, Speststd s  WHERE TRIM(p.id.estimateNo)=s.id.estimateNo AND p.id.deptId=s.id.deptId AND TRIM(s.id.estimateNo)=:estimateNo  AND TRIM(s.id.deptId)= :deptId");
				query.setParameter("estimateNo", estimateNo.trim());
				query.setParameter("deptId", deptId);
				List<Map> maplist = query.getResultList();
				query = getEntityManager(webAppName).createQuery("SELECT w.phase FROM WiringLandDetail w, Application a WHERE  a.id.applicationId=w.id.applicationId AND a.applicationNo=:applicationNo AND w.id.deptId=:deptId");
				query.setParameter("applicationNo", estimateNo.trim());
				query.setParameter("deptId", deptId);
				BigDecimal phase = (BigDecimal) query.getSingleResult();
				//Approvals
				Calendar calendar = Calendar.getInstance();
				Format format=new Format();
				Approval approval=new Approval();
				approval.setApprovalId(null);
				approval.setReferenceNo(estimateNo.trim());
				approval.setDeptId(deptId);
				approval.setApprovalType("EST_APRV");
				approval.setApprovedLevel(authorityLevel);
				approval.setApprovedBy(userName);
				approval.setApprovedDate(calendar.getTime());
				approval.setApprovedTime(format.FormatTime());
				//
				SpeststdPK id=new SpeststdPK();
				id.setDeptId(deptId);
				id.setEstimateNo(estimateNo.trim());
				approval.setStandardCost(speststdDaoRemote.findById(id, webAppName).getTotalCost());
				approval.setDetailedCost(findByEstimationNo(estimateNo.trim(), deptId, webAppName).getStdCost());
				
				
				//if (phase.toString().equals("1")){
					
				//}
				//if (phaseList.isEmpty())
				//	return null;
		        //else if (list.size() == 1)
		        ///	return list.get(0);
		        //throw new NonUniqueResultException();
				if (maplist.isEmpty()){
					//return null;
				}else if (maplist.size() == 1){
					status=maplist.get(0).values().toArray()[1].toString();
		        	totalCost= new BigDecimal(maplist.get(0).values().toArray()[0].toString());
		        	dbTotalCost=Double.parseDouble(totalCost.toString());
		        	//System.out.println(status+" "+dbTotalCost);
		        	if (phase.toString().equals("1")){
			        	if (status.equals(EstimateStatus.EST_APPROVAL_ES1) && authorityLevel.equals("ES")){
			        		if (dbTotalCost <= limitTo ){
			            		changeStatusPcesthtt(estimateNo, deptId, new BigDecimal(EstimateStatus.EST_APPROVED), webAppName);
			            		approval.setFromStatus(statusFrom);
			            		approval.setToStatus(EstimateStatus.EST_APPROVED);
			            		approvalDaoRemote.createAutoIdApprovals(approval, webAppName);
			            		//System.out.println("SMS $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
			            		//smsEjb.writeSMS("0773877798", "Your esimate has been approved ", "5");
			            		return "#Approved";
			        		}else if (dbTotalCost > limitTo){
			        			changeStatusPcesthtt(estimateNo, deptId, new BigDecimal(statusTo), webAppName);
			        			approval.setFromStatus(statusFrom);
			            		approval.setToStatus(statusTo);
			            		approvalDaoRemote.createAutoIdApprovals(approval, webAppName);
			            		return "#Sent to "+forewordTo(statusTo.toString())+  "for Approval";
			        		}else{
			        			return "@Can not Approve this";
			        		}
			    	    }else if (status.equals(EstimateStatus.EST_APPROVAL_EA) && authorityLevel.equals("EA")){
			    	    	if (dbTotalCost > limitFrom && dbTotalCost <= limitTo){
			    	    	//if (dbTotalCost <= limitTo){	
			    	    		changeStatusPcesthtt(estimateNo, deptId, new BigDecimal(EstimateStatus.EST_APPROVED), webAppName);
			    	    		approval.setFromStatus(statusFrom);
			            		approval.setToStatus(EstimateStatus.EST_APPROVED);
			            		approvalDaoRemote.createAutoIdApprovals(approval, webAppName);
			            		return "#Approved";
			    	    	}else if (dbTotalCost > limitTo){
			        			changeStatusPcesthtt(estimateNo, deptId, new BigDecimal(statusTo), webAppName);
			        			approval.setFromStatus(statusFrom);
			            		approval.setToStatus(statusTo);
			            		approvalDaoRemote.createAutoIdApprovals(approval, webAppName);
			            		return "#Sent to "+forewordTo(statusTo.toString())+  "for Approval";
			        		}else{
			        			return "@Can not Approve this";
			        		}
			    	    }else if (status.equals(EstimateStatus.EST_APPROVAL_EE) && authorityLevel.equals("AE") ||authorityLevel.equals("EE") ){
			    	    	if (dbTotalCost > limitFrom && dbTotalCost <= limitTo){
			    	    		changeStatusPcesthtt(estimateNo, deptId, new BigDecimal(EstimateStatus.EST_APPROVED), webAppName);
			    	    		approval.setFromStatus(statusFrom);
			            		approval.setToStatus(EstimateStatus.EST_APPROVED);
			            		approvalDaoRemote.createAutoIdApprovals(approval, webAppName);
			            		return "#Approved";
			    	    	}else if (dbTotalCost > limitTo) {
			        			changeStatusPcesthtt(estimateNo, deptId, new BigDecimal(statusTo), webAppName);
			        			approval.setFromStatus(statusFrom);
			            		approval.setToStatus(statusTo);
			            		approvalDaoRemote.createAutoIdApprovals(approval, webAppName);
			            		return "#Sent to "+forewordTo(statusTo.toString())+  "for Approval";
			        		}else {
			        			return "@Can not Approve this";
			        		}
			    	    	
			    	    }else if (status.equals(EstimateStatus.EST_APPROVAL_CE) && authorityLevel.equals("CE") ){
			    	    	if (dbTotalCost > limitFrom && dbTotalCost <= limitTo){
			    	    		changeStatusPcesthtt(estimateNo, deptId, new BigDecimal(EstimateStatus.EST_APPROVED), webAppName);
			    	    		approval.setFromStatus(statusFrom);
			            		approval.setToStatus(EstimateStatus.EST_APPROVED);
			            		approvalDaoRemote.createAutoIdApprovals(approval, webAppName);
			            		return "#Approved";
			    	    	}else if (dbTotalCost > limitTo) {
			        			changeStatusPcesthtt(estimateNo, deptId, new BigDecimal(statusTo), webAppName);
			        			approval.setFromStatus(statusFrom);
			            		approval.setToStatus(statusTo);
			            		approvalDaoRemote.createAutoIdApprovals(approval, webAppName);
			            		return "#Sent to "+forewordTo(statusTo.toString())+  "for Approval";
			        		}else {
			        			return "@Can not Approve this";
			        		}	
			    	    }else if (status.equals(EstimateStatus.EST_APPROVAL_DGM) && authorityLevel.equals("DGM")){
			    	    	if (dbTotalCost > limitFrom && dbTotalCost <= limitTo){
			    	    		changeStatusPcesthtt(estimateNo, deptId, new BigDecimal(EstimateStatus.EST_APPROVED), webAppName);
			    	    		approval.setFromStatus(statusFrom);
			            		approval.setToStatus(EstimateStatus.EST_APPROVED);
			            		approvalDaoRemote.createAutoIdApprovals(approval, webAppName);
			            		return "#Approved";
			    	    	}else if (dbTotalCost > limitTo){
			        			changeStatusPcesthtt(estimateNo, deptId, new BigDecimal(statusTo), webAppName);
			        			approval.setFromStatus(statusFrom);
			            		approval.setToStatus(statusTo);
			            		approvalDaoRemote.createAutoIdApprovals(approval, webAppName);
			            		return "#Sent to"+forewordTo(statusTo.toString())+  "for Approval";
			        		}else {
			        			return "@Can not Approve this";
			        		}
			    	    }else if (status.equals(EstimateStatus.EST_APPROVAL_AGM) && authorityLevel.equals("AGM")){
			    	    	if (dbTotalCost > limitFrom){
			    	    		changeStatusPcesthtt(estimateNo, deptId, new BigDecimal(EstimateStatus.EST_APPROVED), webAppName);
			    	    		approval.setFromStatus(statusFrom);
			            		approval.setToStatus(statusTo);
			            		approvalDaoRemote.createAutoIdApprovals(approval, webAppName);
			            		return "#Approved";
			    	    	} else {
			    	    		return "@Can not Approve this";
			    	    	}
			    	    }else {
			    	    	return "@Can not Approve this";
			    	    }
		        	}else{//3phase
		        		if (status.equals(EstimateStatus.EST_APPROVAL_ES1) && authorityLevel.equals("ES")){
			        		if (dbTotalCost <= limitTo ){
			            		changeStatusPcesthtt(estimateNo, deptId, new BigDecimal(statusTo), webAppName);
			            		approval.setFromStatus(statusFrom);
			            		approval.setToStatus(statusTo);
			            		approvalDaoRemote.createAutoIdApprovals(approval, webAppName);
			            		return "#Sent to"+forewordTo(statusTo.toString())+  "for Approval";
			        		}else if (dbTotalCost > limitTo){
			        			changeStatusPcesthtt(estimateNo, deptId, new BigDecimal(statusTo), webAppName);
			        			approval.setFromStatus(statusFrom);
			            		approval.setToStatus(statusTo);
			            		approvalDaoRemote.createAutoIdApprovals(approval, webAppName);
			            		return "#Sent to"+forewordTo(statusTo.toString())+  "for Approval";
			        		}else{
			        			return "@Can not Approve this";
			        		}
			    	    }else if (status.equals(EstimateStatus.EST_APPROVAL_EA) && authorityLevel.equals("EA")){
			    	    	if (dbTotalCost <= limitTo){
			    	    		changeStatusPcesthtt(estimateNo, deptId, new BigDecimal(statusTo), webAppName);
			    	    		approval.setFromStatus(statusFrom);
			            		approval.setToStatus(statusTo);
			            		approvalDaoRemote.createAutoIdApprovals(approval, webAppName);
			            		return "#Sent to"+forewordTo(statusTo.toString())+  "for Approval";
			    	    	}else if (dbTotalCost > limitTo){
			        			changeStatusPcesthtt(estimateNo, deptId, new BigDecimal(statusTo), webAppName);
			        			approval.setFromStatus(statusFrom);
			            		approval.setToStatus(statusTo);
			            		approvalDaoRemote.createAutoIdApprovals(approval, webAppName);
			            		return "#Sent to"+forewordTo(statusTo.toString())+  "for Approval";
			        		}else{
			        			return "@Can not Approve this";
			        		}
			    	    }else if (status.equals(EstimateStatus.EST_APPROVAL_EA) && authorityLevel.equals("AE") ||authorityLevel.equals("EE") ){
			    	    	if (dbTotalCost <= limitTo){
			    	    		changeStatusPcesthtt(estimateNo, deptId, new BigDecimal(EstimateStatus.EST_APPROVED), webAppName);
			    	    		approval.setFromStatus(statusFrom);
			            		approval.setToStatus(EstimateStatus.EST_APPROVED);
			            		approvalDaoRemote.createAutoIdApprovals(approval, webAppName);
			            		return "#Approved";
			    	    	}else if (dbTotalCost > limitTo) {
			        			changeStatusPcesthtt(estimateNo, deptId, new BigDecimal(statusTo), webAppName);
			        			approval.setFromStatus(statusFrom);
			            		approval.setToStatus(statusTo);
			            		approvalDaoRemote.createAutoIdApprovals(approval, webAppName);
			            		return "#Sent to"+forewordTo(statusTo.toString())+  "for Approval";
			        		}else {
			        			return "@Can not Approve this";
			        		}
			    	    }else if (status.equals(EstimateStatus.EST_APPROVAL_CE) && authorityLevel.equals("CE") ){
			    	    	if (dbTotalCost <= limitTo){
			    	    		changeStatusPcesthtt(estimateNo, deptId, new BigDecimal(EstimateStatus.EST_APPROVED), webAppName);
			    	    		approval.setFromStatus(statusFrom);
			            		approval.setToStatus(EstimateStatus.EST_APPROVED);
			            		approvalDaoRemote.createAutoIdApprovals(approval, webAppName);
			            		return "#Approved";
			    	    	}else if (dbTotalCost > limitTo) {
			        			changeStatusPcesthtt(estimateNo, deptId, new BigDecimal(statusTo), webAppName);
			        			approval.setFromStatus(statusFrom);
			            		approval.setToStatus(statusTo);
			            		approvalDaoRemote.createAutoIdApprovals(approval, webAppName);
			            		return "#Sent to"+forewordTo(statusTo.toString())+  "for Approval";
			        		}else {
			        			return "@Can not Approve this";
			        		}	
			    	    }else if (status.equals(EstimateStatus.EST_APPROVAL_DGM) && authorityLevel.equals("DGM")){
			    	    	if (dbTotalCost > limitFrom && dbTotalCost <= limitTo){
			    	    		changeStatusPcesthtt(estimateNo, deptId, new BigDecimal(EstimateStatus.EST_APPROVED), webAppName);
			    	    		approval.setFromStatus(statusFrom);
			            		approval.setToStatus(EstimateStatus.EST_APPROVED);
			            		approvalDaoRemote.createAutoIdApprovals(approval, webAppName);
			            		return "#Approved";
			    	    	}else if (dbTotalCost > limitTo){
			        			changeStatusPcesthtt(estimateNo, deptId, new BigDecimal(statusTo), webAppName);
			        			approval.setFromStatus(statusFrom);
			            		approval.setToStatus(statusTo);
			            		approvalDaoRemote.createAutoIdApprovals(approval, webAppName);
			            		return "#Sent to"+forewordTo(statusTo.toString())+  "for Approval";
			        		}else {
			        			return "@Can not Approve this";
			        		}
			    	    }else if (status.equals(EstimateStatus.EST_APPROVAL_AGM) && authorityLevel.equals("AGM")){
			    	    	if (dbTotalCost > limitTo){
			    	    		changeStatusPcesthtt(estimateNo, deptId, new BigDecimal(EstimateStatus.EST_APPROVED), webAppName);
			    	    		approval.setFromStatus(statusFrom);
			            		approval.setToStatus(EstimateStatus.EST_APPROVED);
			            		approvalDaoRemote.createAutoIdApprovals(approval, webAppName);
			            		return "#Approved";
			    	    	} else {
			    	    		return "@Can not Approve this";
			    	    	}
			    	    }else {
			    	    	return "@Can not Approve this";
			    	    }
		        		
		        	}
		        	
				}throw new NonUniqueResultException();
				
			}else {
				return "@There is no approve limits for your user Level";
			}
		}catch (Exception e) {
			e.printStackTrace();
			return "@Please contact the MIS Staff.";
		}
		
		
	}*/
	
	@Override
	@SuppressWarnings({ "unchecked", "rawtypes"})
	public String approveEstimate(String estimateNo, String deptId,	String authorityLevel, String userName, String assignedUserName, String applicationType,String comment,String webAppName) {
		//SmsEjb smsEjb=new SmsEjb();
		//getEntityManager(webAppName);
		//Limits
		 
		//System.out.println(userName+ authorityLevel);
		System.out.println("FFFFFFFFFFFFFFFFFFFFFFFFFFFF    .uuuuuuuuuuuuuuuuuuuuuuuuu");
		
		try{
			if(deptId.equals("543.10") || deptId.equals("543.20") || deptId.equals("543.40")){
				applicationType = "GI";
			}
			//System.out.println(userName+securityBeanRemote.getCostCenter(userName, webAppName)+ authorityLevel);
			Appestlim appestlim=findAppEstLimits(deptId, "DES",applicationType, authorityLevel, webAppName);
			System.out.println("appestlim "+appestlim);
			if (appestlim!=null){
				Double limitFrom=Double.parseDouble(String.valueOf(appestlim.getId().getLimitFrom()));
				Double limitTo=Double.parseDouble(String.valueOf(appestlim.getId().getLimitTo()));
				String statusFrom=String.valueOf(appestlim.getId().getStatusFrom());
				String statusTo=String.valueOf(appestlim.getId().getStatusTo());
				System.out.println("appestlim "+appestlim);
				//	
				String sEstimateNo=null;
				String commercialDeptid=null;
				String commercialId= deptId.substring(0, 3);
				if(commercialId != null){
					commercialDeptid = commercialId.concat(".00");

				}
				
				Query query=null;
				BigDecimal totalCost = null;
				String status = null;
				Double dbTotalCost=0.0;
				/*query = getEntityManager(webAppName).createQuery("SELECT new map(p.status, p.stdCost) FROM Pcesthtt p  WHERE  TRIM(p.id.estimateNo)=:estimateNo  AND TRIM(p.id.deptId)= :deptId");
				query.setParameter("estimateNo", estimateNo.trim());
				query.setParameter("deptId", deptId);
				List<Map> maplist = query.getResultList();*/
				
				//Approvals
				Calendar calendar = Calendar.getInstance();
				Format format=new Format();
				Approval approval=new Approval();
				approval.setApprovalId(null);
				approval.setReferenceNo(estimateNo.trim());
				approval.setDeptId(deptId);
				approval.setReason(comment);
				approval.setApprovedLevel(authorityLevel);
				approval.setApprovedBy(userName);
				approval.setApprovedDate(calendar.getTime());
				approval.setApprovedTime(format.FormatTime());
				//
				
				EstimateReference est = estimateReferenceDaoRemote.findByWorkEstimateNo(estimateNo.trim(), deptId, webAppName);
				
				if(est != null){
					sEstimateNo = est.getId().getStandardEstimateNo();
					SpstdesthmtPK id=new SpstdesthmtPK();
					id.setDeptId(commercialDeptid);
					id.setStdNo(sEstimateNo.trim());
					id.setApplicationNo(sEstimateNo.trim());
					Spstdesthmt hmt = spstdesthmtDaoRemote.findById(id, webAppName);
					if(hmt != null){
						approval.setStandardCost(hmt.getTotalCost());
					}else{
						approval.setStandardCost(new BigDecimal("0"));
					}
				}
				Pcesthtt htt = findByEstimationNo(estimateNo.trim(), deptId,Constants.REV_NO, webAppName);
				//approval.setStandardCost(spstdesthmtDaoRemote.findById(id, webAppName).getTotalCost());
				approval.setDetailedCost(findByEstimationNo(estimateNo.trim(), deptId,Constants.REV_NO, webAppName).getStdCost());
				String postinguserLevel = null;
				if(assignedUserName != null){
					postinguserLevel = securityBeanRemote.getAuthorizedLevel(assignedUserName,webAppName);
				}
				if (htt != null){
					status=htt.getStatus().toString();
		        	totalCost= htt.getStdCost();
		        	if(totalCost != null ){
		        		dbTotalCost=Double.parseDouble(totalCost.toString());
		        	}
			        	if (postinguserLevel != null && status.equals(EstimateStatus.EST_APPROVAL_ES) && authorityLevel.equals("ES")){
			        		if(postinguserLevel != null && postinguserLevel.equalsIgnoreCase("EE")){
			        			
			        			//htt.setStatus(new Long(EstimateStatus.EST_APPROVAL_EE));
			        			//htt.setAprUid5(assignedUserName);
			        		
			        			//updatePcesthtt(htt, webAppName);
			        			
			        			changeStatusPcesthtt(estimateNo, deptId, new Long(statusTo), webAppName);
			        			approval.setFromStatus(status);
			            		approval.setToStatus(statusTo);
			            		approvalDaoRemote.createAutoIdApprovals(approval, webAppName);
			            		return "#Sent to "+forewordTo(statusTo.toString())+  " for Approval";
			        		}else if(postinguserLevel != null && postinguserLevel.equalsIgnoreCase("ES")){
			        			changeStatusPcesthtt(estimateNo, deptId, new Long(EstimateStatus.EST_APPROVAL_ES), webAppName);
			        			//htt.setStatus(new Long(EstimateStatus.EST_APPROVAL_ES));
			        			//htt.setAprUid5(assignedUserName);
			        		
			        			//updatePcesthtt(htt, webAppName);
			        			
			        			approval.setFromStatus(status);
			            		approval.setToStatus(EstimateStatus.EST_APPROVAL_ES);
			            		approvalDaoRemote.createAutoIdApprovals(approval, webAppName);
			            		return "#Sent to "+forewordTo(statusTo.toString())+  " for Checking";
			        		}else if(postinguserLevel != null && postinguserLevel.equalsIgnoreCase("EA")){
			        			changeStatusPcesthtt(estimateNo, deptId, new Long(EstimateStatus.EST_APPROVAL_EA), webAppName);
			        			//htt.setStatus(new Long(EstimateStatus.EST_APPROVAL_EA));
			        			//htt.setAprUid5(assignedUserName);
			        		
			        			//updatePcesthtt(htt, webAppName);
			        			
			        			
			        			approval.setFromStatus(status);
			            		approval.setToStatus(EstimateStatus.EST_APPROVAL_EA);
			            		approvalDaoRemote.createAutoIdApprovals(approval, webAppName);
			            		return "#Sent to "+forewordTo(statusTo.toString())+  " for Approval";
			        		}
			        			
			        	}else if (status.equals(EstimateStatus.EST_APPROVAL_ES) && authorityLevel.equals("ES") ){
		        			
			    	    	if (dbTotalCost <= limitTo){
			    	    		//************* comment for SAN_TEST ****************
			    	    		
			    	    		if(deptId.equalsIgnoreCase("543.10") || deptId.equalsIgnoreCase("543.20") || deptId.equalsIgnoreCase("543.40")){
			    	    			changeStatusPcesthtt(estimateNo, deptId, new Long(EstimateStatus.EST_POSTED), webAppName);
			    	    			approval.setToStatus(EstimateStatus.EST_POSTED);
			    	    		}else{
			    	    			changeStatusPcesthtt(estimateNo, deptId, new Long(EstimateStatus.EST_POSTED), webAppName);
			    	    			approval.setToStatus(EstimateStatus.EST_POSTED);
			    	    		}
			    	    		approval.setFromStatus(statusFrom);
			    	    		//************* comment for SAN_TEST ****************
			            		approval.setToStatus(EstimateStatus.EST_POSTING);
			            		//approval.setToStatus(EstimateStatus.EST_POSTED);
			            		approval.setApprovalType("DES_APRV");
			            		approvalDaoRemote.createAutoIdConstructionEstimateApprovals(approval, webAppName);
			            		return "#Approved";
			    	    	}else if (dbTotalCost > limitTo) {
			        			changeStatusPcesthtt(estimateNo, deptId, new Long(statusTo), webAppName);
			        			approval.setFromStatus(statusFrom);
			            		approval.setToStatus(statusTo);
			            		approval.setApprovalType("DES_VLDT");
			            		approvalDaoRemote.createAutoIdConstructionEstimateApprovals(approval, webAppName);
			            		return "#Sent to "+forewordTo(statusTo.toString())+  " for Approval";
			        		}else {
			        			return "@Can not Approve this";
			        		}
			    	    	
			    	    }else if (status.equals(EstimateStatus.EST_APPROVAL_EE) && authorityLevel.equals("EE") ){
		        			
			    	    	if (dbTotalCost <= limitTo){
			    	    		//************* comment for SAN_TEST ****************
			    	    		
			    	    		if(deptId.equalsIgnoreCase("543.10") || deptId.equalsIgnoreCase("543.20") || deptId.equalsIgnoreCase("543.40")){
			    	    			changeStatusPcesthtt(estimateNo, deptId, new Long(EstimateStatus.EST_POSTED), webAppName);
			    	    			approval.setToStatus(EstimateStatus.EST_POSTED);
			    	    		}else{
			    	    			changeStatusPcesthtt(estimateNo, deptId, new Long(EstimateStatus.EST_POSTED), webAppName);
			    	    			approval.setToStatus(EstimateStatus.EST_POSTED);
			    	    		}
			    	    		approval.setFromStatus(statusFrom);
			    	    		//************* comment for SAN_TEST ****************
			            		approval.setToStatus(EstimateStatus.EST_POSTING);
			            		//approval.setToStatus(EstimateStatus.EST_POSTED);
			            		approval.setApprovalType("DES_APRV");
			            		approvalDaoRemote.createAutoIdConstructionEstimateApprovals(approval, webAppName);
			            		return "#Approved";
			    	    	}else if (dbTotalCost > limitTo) {
			        			changeStatusPcesthtt(estimateNo, deptId, new Long(statusTo), webAppName);
			        			approval.setFromStatus(statusFrom);
			            		approval.setToStatus(statusTo);
			            		approval.setApprovalType("DES_VLDT");
			            		approvalDaoRemote.createAutoIdConstructionEstimateApprovals(approval, webAppName);
			            		return "#Sent to "+forewordTo(statusTo.toString())+  " for Approval";
			        		}else {
			        			return "@Can not Approve this";
			        		}
			    	    	
			    	    }else if (status.equals(EstimateStatus.EST_APPROVAL_CE) && authorityLevel.equals("CE") ){
			    	    	
			    	    	if (dbTotalCost <= limitTo){
			    	    		//************* comment for SAN_TEST ****************
			    	    		if(deptId.equalsIgnoreCase("543.10") || deptId.equalsIgnoreCase("543.20") || deptId.equalsIgnoreCase("543.40")){
			    	    			changeStatusPcesthtt(estimateNo, deptId, new Long(EstimateStatus.EST_POSTED), webAppName);
			    	    			approval.setToStatus(EstimateStatus.EST_POSTED);
			    	    		}else{
			    	    			changeStatusPcesthtt(estimateNo, deptId, new Long(EstimateStatus.EST_POSTED), webAppName);
			    	    			approval.setToStatus(EstimateStatus.EST_POSTED);
			    	    		}
			    	    		approval.setFromStatus(statusFrom);
			    	    		//********* comment for SAN_TEST **************
			            		approval.setToStatus(EstimateStatus.EST_POSTING);
			            		//approval.setToStatus(EstimateStatus.EST_POSTED);
			            		approval.setApprovalType("DES_APRV");
			            		approvalDaoRemote.createAutoIdConstructionEstimateApprovals(approval, webAppName);
			            		return "#Approved";
			    	    	}else if (dbTotalCost > limitTo) {
			        			changeStatusPcesthtt(estimateNo, deptId, new Long(statusTo), webAppName);
			        			approval.setFromStatus(statusFrom);
			            		approval.setToStatus(statusTo);
			            		approval.setApprovalType("DES_VLDT");
			            		approvalDaoRemote.createAutoIdConstructionEstimateApprovals(approval, webAppName);
			            		return "#Sent to "+forewordTo(statusTo.toString())+  " for Approval";
			        		}else {
			        			return "@Can not Approve this";
			        		}	
			    	    }else if (status.equals(EstimateStatus.EST_APPROVAL_DGM) && authorityLevel.equals("DGM")){
			    	    	System.out.println("DGM : " +dbTotalCost +" : " +  limitFrom +" : "+limitTo);
			    	    	if (dbTotalCost > limitFrom){
			    	    		//************* comment for SAN_TEST ****************
			    	    		if(deptId.equalsIgnoreCase("543.10") || deptId.equalsIgnoreCase("543.20") || deptId.equalsIgnoreCase("543.40")){
			    	    			changeStatusPcesthtt(estimateNo, deptId, new Long(EstimateStatus.EST_POSTED), webAppName);
			    	    			approval.setToStatus(EstimateStatus.EST_POSTED);
			    	    		}else{
			    	    			if (dbTotalCost < limitTo){
			    	    				changeStatusPcesthtt(estimateNo, deptId, new Long(new Long(EstimateStatus.EST_POSTED)), webAppName);
				    	    			
			    	    				approval.setToStatus(EstimateStatus.EST_POSTED);
			    	    			}else{
			    	    				changeStatusPcesthtt(estimateNo, deptId, new Long(statusTo), webAppName);
				    	    			
			    	    				approval.setToStatus(statusTo);
			    	    			}
			    	    		}
			    	    		approval.setFromStatus(statusFrom);
			    	    		//********** comment for SAN_TEST ************
			            		approval.setToStatus(EstimateStatus.EST_POSTING);
			            		
			            		approval.setApprovalType("DES_APRV");
			            		approvalDaoRemote.createAutoIdConstructionEstimateApprovals(approval, webAppName);
			            		return "#Approved";
			            		}else if (dbTotalCost > limitTo){
			            			System.out.println("DGM1 : " +dbTotalCost +" : " +  limitFrom +" : "+limitTo);
			        			changeStatusPcesthtt(estimateNo, deptId, new Long(statusTo), webAppName);
			        			approval.setFromStatus(statusFrom);
			            		approval.setToStatus(statusTo);
			            		approval.setApprovalType("DES_VLDT");
			            		//approvalDaoRemote.createAutoIdConstructionEstimateApprovals(approval, webAppName);
			            		approvalDaoRemote.createAutoIdApprovals(approval, webAppName);
			            		return "#Sent to"+forewordTo(statusTo.toString())+  "for Approval";
			            		
			            		
			        		}else if (dbTotalCost < limitTo){
			            			System.out.println("DGM2 : " +dbTotalCost +" : " +  limitFrom +" : "+limitTo);
			        			changeStatusPcesthtt(estimateNo, deptId, new Long(EstimateStatus.EST_POSTED), webAppName);
			        			approval.setFromStatus(statusFrom);
			            		approval.setToStatus(EstimateStatus.EST_POSTED);
			            		approval.setApprovalType("DES_APRV");
			            		//approvalDaoRemote.createAutoIdConstructionEstimateApprovals(approval, webAppName);
			            		approvalDaoRemote.createAutoIdApprovals(approval, webAppName);
			            		return "#Approved";
			            		
			            		
			        		}else {
			        			return "@Can not Approve this";
			        		}
			    	    }else if (status.equals(EstimateStatus.EST_APPROVAL_AGM) && authorityLevel.equals("AGM")){
			    	    	
			    	    	//if (dbTotalCost > limitFrom){
			    	    		//************* comment for SAN_TEST ****************
			    	    		if(deptId.equalsIgnoreCase("543.10") || deptId.equalsIgnoreCase("543.20") || deptId.equalsIgnoreCase("543.40")){
			    	    			changeStatusPcesthtt(estimateNo, deptId, new Long(EstimateStatus.EST_POSTED), webAppName);
			    	    		}else{
			    	    			changeStatusPcesthtt(estimateNo, deptId, new Long(EstimateStatus.EST_POSTED), webAppName);
			    	    		}
			    	    		approval.setFromStatus(statusFrom);
			            		approval.setToStatus(statusTo);
			            		approval.setApprovalType("DES_APRV");
			            		approvalDaoRemote.createAutoIdConstructionEstimateApprovals(approval, webAppName);
			            		return "#Approved";
			    	    	//} else {
			    	    	//	return "@Can not Approve this";
			    	    	//}
			    	    }else {
			    	    	return "@Can not Approve this";
			    	    }
		        		
		        	
		        	
				}throw new NonUniqueResultException();
				
			}else {
				return "@There is no approve limits for your user Level";
			}
		}catch (Exception e) {
			e.printStackTrace();
			return "@Please contact the MIS Staff.";
		}
		
		
	}
	
	@Override
	@SuppressWarnings({ "unchecked", "rawtypes"})
	public String recommendEstimate(String estimateNo, String deptId,	String authorityLevel, String userName, String assignedUserName, String applicationType,String comment,String webAppName) {
		//SmsEjb smsEjb=new SmsEjb();
		//getEntityManager(webAppName);
		//Limits
		 
		//System.out.println(userName+ authorityLevel);
		System.out.println("FFFFFFFFFFFFFFFFFFFFFFFFFFFF    .uuuuuuuuuuuuuuuuuuuuuuuuu");
		
		try{
			if(deptId.equals("543.10") || deptId.equals("543.20") || deptId.equals("543.40")){
				applicationType = "GI";
			}
			//System.out.println(userName+securityBeanRemote.getCostCenter(userName, webAppName)+ authorityLevel);
			Appestlim appestlim=findAppEstLimits(deptId, "DES",applicationType, authorityLevel, webAppName);
			System.out.println("appestlim "+appestlim);
			
			System.out.println("hiiii "+appestlim);
			if (appestlim!=null){
				Double limitFrom=Double.parseDouble(String.valueOf(appestlim.getId().getLimitFrom()));
				Double limitTo=Double.parseDouble(String.valueOf(appestlim.getId().getLimitTo()));
				String statusFrom=String.valueOf(appestlim.getId().getStatusFrom());
				String statusTo=String.valueOf(appestlim.getId().getStatusTo());
				System.out.println("appestlim "+appestlim);
				//	
				String sEstimateNo=null;
				String commercialDeptid=null;
				String commercialId= deptId.substring(0, 3);
				if(commercialId != null){
					commercialDeptid = commercialId.concat(".00");

				}
				
				Query query=null;
				BigDecimal totalCost = null;
				String status = null;
				Double dbTotalCost=0.0;
			/*	query = getEntityManager(webAppName).createQuery("SELECT new map(p.status, p.stdCost) FROM Pcesthtt p  WHERE  TRIM(p.id.estimateNo)=:estimateNo  AND TRIM(p.id.deptId)= :deptId");
				query.setParameter("estimateNo", estimateNo.trim());
				query.setParameter("deptId", deptId);
				List<Map> maplist = query.getResultList();*/
				
				//Approvals
				Calendar calendar = Calendar.getInstance();
				Format format=new Format();
				Approval approval=new Approval();
				approval.setApprovalId(null);
				approval.setReferenceNo(estimateNo.trim());
				approval.setDeptId(deptId);
				approval.setReason(comment);
				approval.setApprovedLevel(authorityLevel);
				approval.setApprovedBy(userName);
				approval.setApprovedDate(calendar.getTime());
				approval.setApprovedTime(format.FormatTime());
				//
				
				EstimateReference est = estimateReferenceDaoRemote.findByWorkEstimateNo(estimateNo.trim(), deptId, webAppName);
				
				if(est != null){
					sEstimateNo = est.getId().getStandardEstimateNo();
					SpstdesthmtPK id=new SpstdesthmtPK();
					id.setDeptId(commercialDeptid);
					id.setStdNo(sEstimateNo.trim());
					id.setApplicationNo(sEstimateNo.trim());
					Spstdesthmt hmt = spstdesthmtDaoRemote.findById(id, webAppName);
					if(hmt != null){
						approval.setStandardCost(hmt.getTotalCost());
					}else{
						approval.setStandardCost(new BigDecimal("0"));
					}
				}
				
				
				Pcesthtt htt = findByEstimationNo(estimateNo.trim(), deptId,Constants.REV_NO, webAppName);
				//approval.setStandardCost(spstdesthmtDaoRemote.findById(id, webAppName).getTotalCost());
				
				String postinguserLevel = null;
				if(assignedUserName != null){
					postinguserLevel = securityBeanRemote.getAuthorizedLevel(assignedUserName,webAppName);
				}
				 if (htt != null){
					approval.setDetailedCost(findByEstimationNo(estimateNo.trim(), deptId,Constants.REV_NO, webAppName).getStdCost());
						
					status=htt.getStatus().toString();
		        	totalCost= htt.getStdCost();
		        	if(totalCost != null ){
		        		dbTotalCost=Double.parseDouble(totalCost.toString());
		        	}
			        	/*if (status.equals(EstimateStatus.EST_APPROVAL_ES) && authorityLevel.equals("ES")){
			        		if(postinguserLevel.equalsIgnoreCase("EE")){
			        			changeStatusPcesthtt(estimateNo, deptId, new Long(statusTo), webAppName);
			        			approval.setFromStatus(status);
			            		approval.setToStatus(statusTo);
			            		approvalDaoRemote.createAutoIdApprovals(approval, webAppName);
			            		return "#Sent to "+forewordTo(statusTo.toString())+  " for Approval";
			        		}else if(postinguserLevel.equalsIgnoreCase("ES")){
			        			changeStatusPcesthtt(estimateNo, deptId, new Long(EstimateStatus.EST_APPROVAL_ES), webAppName);
			        			approval.setFromStatus(status);
			            		approval.setToStatus(EstimateStatus.EST_APPROVAL_ES);
			            		approvalDaoRemote.createAutoIdApprovals(approval, webAppName);
			            		return "#Sent to "+forewordTo(statusTo.toString())+  " for Checking";
			        		}else if(postinguserLevel.equalsIgnoreCase("EA")){
			        			changeStatusPcesthtt(estimateNo, deptId, new Long(EstimateStatus.EST_APPROVAL_EA), webAppName);
			        			approval.setFromStatus(status);
			            		approval.setToStatus(EstimateStatus.EST_APPROVAL_EA);
			            		approvalDaoRemote.createAutoIdApprovals(approval, webAppName);
			            		return "#Sent to "+forewordTo(statusTo.toString())+  " for Approval";
			        		}
			        			
			        	}*/if (status.equals(EstimateStatus.EST_APPROVAL_ES) && authorityLevel.equals("ES") ){
		        			
				        		//htt.setStatus(new Long(EstimateStatus.EST_APPROVAL_EE));
			        			//htt.setAprUid5(assignedUserName);
			        		
			        			//updatePcesthtt(htt, webAppName);
			        			changeStatusPcesthtt(estimateNo, deptId, new Long(statusTo), webAppName);
			        			approval.setFromStatus(statusFrom);
			            		approval.setToStatus(statusTo);
			            		approval.setApprovalType("DES_VLDT");
			            		approvalDaoRemote.createAutoIdConstructionEstimateApprovals(approval, webAppName);
			            		return "#Sent to "+forewordTo(statusTo.toString())+  " for Approval";
			        		
			    	    	
			    	    }else if (status.equals(EstimateStatus.EST_APPROVAL_EA) && authorityLevel.equals("EA")){
				    	    	//htt.setStatus(new Long(EstimateStatus.EST_APPROVAL_EE));
			        			//htt.setAprUid5(assignedUserName);
			        		
			        			//updatePcesthtt(htt, webAppName);
			        			changeStatusPcesthtt(estimateNo, deptId, new Long(statusTo), webAppName);
			        			approval.setFromStatus(statusFrom);
			            		approval.setToStatus(statusTo);
			            		approvalDaoRemote.createAutoIdConstructionEstimateApprovals(approval, webAppName);
			            		return "#Sent to "+forewordTo(statusTo.toString())+  "for Approval";
			        		
			    	    }else if (status.equals(EstimateStatus.EST_APPROVAL_EE) && authorityLevel.equals("EE") ){
		        			
			    	    	
			        			changeStatusPcesthtt(estimateNo, deptId, new Long(statusTo), webAppName);
			        			approval.setFromStatus(statusFrom);
			            		approval.setToStatus(statusTo);
			            		approval.setApprovalType("DES_VLDT");
			            		approvalDaoRemote.createAutoIdConstructionEstimateApprovals(approval, webAppName);
			            		return "#Sent to "+forewordTo(statusTo.toString())+  " for Approval";
			        		
			    	    	
			    	    }else if (status.equals(EstimateStatus.EST_APPROVAL_PLCE) && authorityLevel.equals("PCE") ){
			    	    	System.out.println("CEappestlim "+estimateNo);
			    	    	
		        			changeStatusPcesthtt(estimateNo, deptId, new Long(statusTo), webAppName);
		        			approval.setFromStatus(statusFrom);
		            		approval.setToStatus(statusTo);
		            		approval.setApprovalType("DES_RECM");
		            		approvalDaoRemote.createAutoIdConstructionEstimateApprovals(approval, webAppName);
		            		return "#Sent to "+forewordTo(statusTo.toString())+  " for Approval";
		        		
		    	    }else if (status.equals(EstimateStatus.EST_APPROVAL_COMCE) && authorityLevel.equals("CE") ){
		    	    	System.out.println("CEappestlim "+estimateNo);
		    	    	
	        			changeStatusPcesthtt(estimateNo, deptId, new Long(statusTo), webAppName);
	        			approval.setFromStatus(statusFrom);
	            		approval.setToStatus(statusTo);
	            		approval.setApprovalType("DES_RECM");
	            		approvalDaoRemote.createAutoIdConstructionEstimateApprovals(approval, webAppName);
	            		return "#Sent to "+forewordTo(statusTo.toString())+  " for Approval";
	        		
		    	    	}else if (status.equals(EstimateStatus.EST_APPROVAL_CE) && authorityLevel.equals("CE") ){
			    	    	System.out.println("CEappestlim "+estimateNo);
			    	    	
			        			changeStatusPcesthtt(estimateNo, deptId, new Long(statusTo), webAppName);
			        			approval.setFromStatus(statusFrom);
			            		approval.setToStatus(statusTo);
			            		approval.setApprovalType("DES_VLDT");
			            		approvalDaoRemote.createAutoIdConstructionEstimateApprovals(approval, webAppName);
			            		return "#Sent to "+forewordTo(statusTo.toString())+  " for Approval";
			        		
			    	    }else if (status.equals(EstimateStatus.EST_APPROVAL_DGM) && authorityLevel.equals("DGM")){
			    	    	
			    	    	
			    	    		//************* comment for SAN_TEST ****************
			    	    		if(deptId.equalsIgnoreCase("543.10") || deptId.equalsIgnoreCase("543.20") || deptId.equalsIgnoreCase("543.40")){
			    	    			changeStatusPcesthtt(estimateNo, deptId, new Long(EstimateStatus.EST_POSTED), webAppName);
			    	    			approval.setToStatus(EstimateStatus.EST_POSTED);
			    	    		}else{
			    	    			changeStatusPcesthtt(estimateNo, deptId, new Long(statusTo), webAppName);
			    	    			approval.setToStatus(statusTo);
			    	    		}
			    	    		approval.setFromStatus(statusFrom);
			    	    		//********** comment for SAN_TEST ************
			            		//approval.setToStatus(EstimateStatus.EST_POSTING);
			            		
			            		approval.setApprovalType("DES_APRV");
			            		approvalDaoRemote.createAutoIdConstructionEstimateApprovals(approval, webAppName);
			            		return "#Approved";
			    	    	/*else if (dbTotalCost > limitTo){
			        			changeStatusPcesthtt(estimateNo, deptId, new Long(statusTo), webAppName);
			        			approval.setFromStatus(statusFrom);
			            		approval.setToStatus(statusTo);
			            		approvalDaoRemote.createAutoIdApprovals(approval, webAppName);
			            		return "#Sent to"+forewordTo(statusTo.toString())+  "for Approval";
			        		}*/
			    	    }else if (status.equals(EstimateStatus.EST_APPROVAL_AGM) && authorityLevel.equals("AGM")){
			    	    	
			    	    
			    	    		//************* comment for SAN_TEST ****************
			    	    		if(deptId.equalsIgnoreCase("543.10") || deptId.equalsIgnoreCase("543.20") || deptId.equalsIgnoreCase("543.40")){
			    	    			changeStatusPcesthtt(estimateNo, deptId, new Long(EstimateStatus.EST_POSTED), webAppName);
			    	    		}else{
			    	    			changeStatusPcesthtt(estimateNo, deptId, new Long(EstimateStatus.EST_POSTED), webAppName);
			    	    		}
			    	    		approval.setFromStatus(statusFrom);
			            		approval.setToStatus(statusTo);
			            		approval.setApprovalType("DES_APRV");
			            		approvalDaoRemote.createAutoIdConstructionEstimateApprovals(approval, webAppName);
			            		return "#Approved";
			    	    	
			    	    }else {
			    	    	return "@Can not Approve this";
			    	    }
		        		
		        	
		        	
				}throw new NonUniqueResultException();
				
			}else {
				return "@There is no approve limits for your user Level";
			}
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("CEappestlim "+e);
			return "@Please contact the MIS Staff.";
		}
		
		
	}

	@Override
	@SuppressWarnings({ "unchecked", "rawtypes"})
	public String approveEstimateMA_SA(String estimateNo, String deptId,	String authorityLevel, String userName, String webAppName) {
		//getEntityManager(webAppName);
		//Limits
		 
		
		//System.out.println("FFFFFFFFFFFFFFFFFFFFFFFFFFFF    .uuuuuuuuuuuuuuuuuuuuuuuuu");
		try{
			
		
			Appestlim appestlim=findAppEstLimits(securityBeanRemote.getCostCenter(userName, webAppName), "EST","NC", authorityLevel, webAppName);
			System.out.println("appestlim "+appestlim);
			if (appestlim!=null){
				Double limitFrom=Double.parseDouble(String.valueOf(appestlim.getId().getLimitFrom()));
				Double limitTo=Double.parseDouble(String.valueOf(appestlim.getId().getLimitTo()));
				String statusFrom=String.valueOf(appestlim.getId().getStatusFrom());
				String statusTo=String.valueOf(appestlim.getId().getStatusTo());
				System.out.println("appestlim "+appestlim);
				//		
				Query query=null;
				BigDecimal detailCost = null;
				Double dbDetailCost=0.0;
				//BigDecimal totalCost = null;
				//Double dbTotalCost=0.0;
				String status = null;
				
				query = getEntityManager(webAppName).createQuery("SELECT new map(p.status, s.totalCost) FROM Pcesthtt p, Speststd s  WHERE TRIM(p.id.estimateNo)=s.id.estimateNo AND p.id.deptId=s.id.deptId AND TRIM(s.id.estimateNo)=:estimateNo  AND TRIM(s.id.deptId)= :deptId");
				query.setParameter("estimateNo", estimateNo.trim());
				query.setParameter("deptId", deptId);
				List<Map> maplist = query.getResultList();
				query = getEntityManager(webAppName).createQuery("SELECT w.phase FROM WiringLandDetail w, Application a WHERE  a.id.applicationId=w.id.applicationId AND a.applicationNo=:applicationNo AND w.id.deptId=:deptId");
				query.setParameter("applicationNo", estimateNo.trim());
				query.setParameter("deptId", deptId);
				BigDecimal phase = (BigDecimal) query.getSingleResult();
				//Approvals
				Calendar calendar = Calendar.getInstance();
				Format format=new Format();
				Approval approval=new Approval();
				approval.setApprovalId(null);
				approval.setReferenceNo(estimateNo.trim());
				approval.setDeptId(deptId);
				approval.setApprovalType("EST_APRV");
				approval.setApprovedLevel(authorityLevel);
				approval.setApprovedBy(userName);
				approval.setApprovedDate(calendar.getTime());
				approval.setApprovedTime(format.FormatTime());
				//
				SpeststdPK id=new SpeststdPK();
				id.setDeptId(deptId);
				id.setEstimateNo(estimateNo.trim());
				approval.setStandardCost(speststdDaoRemote.findById(id, webAppName).getTotalCost());
				detailCost=findByEstimationNo(estimateNo.trim(), deptId, webAppName).getStdCost();
				approval.setDetailedCost(detailCost);
				
				
				//if (phase.toString().equals("1")){
					
				//}
				//if (phaseList.isEmpty())
				//	return null;
		        //else if (list.size() == 1)
		        ///	return list.get(0);
		        //throw new NonUniqueResultException();
				if (maplist.isEmpty()){
					//return null;
				}else if (maplist.size() == 1){
					status=maplist.get(0).values().toArray()[1].toString();
					//detailCost= new BigDecimal(maplist.get(0).values().toArray()[0].toString());
					dbDetailCost=Double.parseDouble(detailCost.toString());
		        	//System.out.println(status+" "+dbTotalCost);
		        	if (phase.toString().equals("0")){
			        	if (status.equals(EstimateStatus.EST_APPROVAL_ES) && authorityLevel.equals("ES")){
			        		if (dbDetailCost <= limitTo ){
			            		changeStatusPcesthtt(estimateNo, deptId, new Long(EstimateStatus.EST_POSTING), webAppName);
			            		approval.setFromStatus(statusFrom);
			            		approval.setToStatus(EstimateStatus.EST_POSTING);
			            		approvalDaoRemote.createAutoIdApprovals(approval, webAppName);
			            		return "#Approved";
			        		}else if (dbDetailCost > limitTo){
			        			changeStatusPcesthtt(estimateNo, deptId, new Long(statusTo), webAppName);
			        			approval.setFromStatus(statusFrom);
			            		approval.setToStatus(statusTo);
			            		approvalDaoRemote.createAutoIdApprovals(approval, webAppName);
			            		return "#Sent to "+forewordTo(statusTo.toString())+  "for Approval";
			        		}else{
			        			return "@Can not Approve this";
			        		}
			    	    }else if (status.equals(EstimateStatus.EST_APPROVAL_EA) && authorityLevel.equals("EA")){
			    	    	if (dbDetailCost > limitFrom && dbDetailCost <= limitTo){
			    	    		changeStatusPcesthtt(estimateNo, deptId, new Long(EstimateStatus.EST_POSTING), webAppName);
			    	    		approval.setFromStatus(statusFrom);
			            		approval.setToStatus(EstimateStatus.EST_POSTING);
			            		approvalDaoRemote.createAutoIdApprovals(approval, webAppName);
			            		return "#Approved";
			    	    	}else if (dbDetailCost > limitTo){
			        			changeStatusPcesthtt(estimateNo, deptId, new Long(statusTo), webAppName);
			        			approval.setFromStatus(statusFrom);
			            		approval.setToStatus(statusTo);
			            		approvalDaoRemote.createAutoIdApprovals(approval, webAppName);
			            		return "#Sent to "+forewordTo(statusTo.toString())+  "for Approval";
			        		}else{
			        			return "@Can not Approve this";
			        		}
			    	    }else if (status.equals(EstimateStatus.EST_APPROVAL_EE) && authorityLevel.equals("AE") ||authorityLevel.equals("EE") ){
			    	    	if (dbDetailCost > limitFrom && dbDetailCost <= limitTo){
			    	    		changeStatusPcesthtt(estimateNo, deptId, new Long(EstimateStatus.EST_POSTING), webAppName);
			    	    		approval.setFromStatus(statusFrom);
			            		approval.setToStatus(EstimateStatus.EST_POSTING);
			            		approvalDaoRemote.createAutoIdApprovals(approval, webAppName);
			            		return "#Approved";
			    	    	}else if (dbDetailCost > limitTo) {
			        			changeStatusPcesthtt(estimateNo, deptId, new Long(statusTo), webAppName);
			        			approval.setFromStatus(statusFrom);
			            		approval.setToStatus(statusTo);
			            		approvalDaoRemote.createAutoIdApprovals(approval, webAppName);
			            		return "#Sent to "+forewordTo(statusTo.toString())+  "for Approval";
			        		}else {
			        			return "@Can not Approve this";
			        		}
			    	    	
			    	    }else if (status.equals(EstimateStatus.EST_APPROVAL_CE) && authorityLevel.equals("CE") ){
			    	    	if (dbDetailCost > limitFrom && dbDetailCost <= limitTo){
			    	    		changeStatusPcesthtt(estimateNo, deptId, new Long(EstimateStatus.EST_POSTING), webAppName);
			    	    		approval.setFromStatus(statusFrom);
			            		approval.setToStatus(EstimateStatus.EST_POSTING);
			            		approvalDaoRemote.createAutoIdApprovals(approval, webAppName);
			            		return "#Approved";
			    	    	}else if (dbDetailCost > limitTo) {
			        			changeStatusPcesthtt(estimateNo, deptId, new Long(statusTo), webAppName);
			        			approval.setFromStatus(statusFrom);
			            		approval.setToStatus(statusTo);
			            		approvalDaoRemote.createAutoIdApprovals(approval, webAppName);
			            		return "#Sent to "+forewordTo(statusTo.toString())+  "for Approval";
			        		}else {
			        			return "@Can not Approve this";
			        		}	
			    	    }else if (status.equals(EstimateStatus.EST_APPROVAL_DGM) && authorityLevel.equals("DGM")){
			    	    	if (dbDetailCost > limitFrom && dbDetailCost <= limitTo){
			    	    		changeStatusPcesthtt(estimateNo, deptId, new Long(EstimateStatus.EST_POSTING), webAppName);
			    	    		approval.setFromStatus(statusFrom);
			            		approval.setToStatus(EstimateStatus.EST_POSTING);
			            		approvalDaoRemote.createAutoIdApprovals(approval, webAppName);
			            		return "#Approved";
			    	    	}else if (dbDetailCost > limitTo){
			        			changeStatusPcesthtt(estimateNo, deptId, new Long(statusTo), webAppName);
			        			approval.setFromStatus(statusFrom);
			            		approval.setToStatus(statusTo);
			            		approvalDaoRemote.createAutoIdApprovals(approval, webAppName);
			            		return "#Sent to"+forewordTo(statusTo.toString())+  "for Approval";
			        		}else {
			        			return "@Can not Approve this";
			        		}
			    	    }else if (status.equals(EstimateStatus.EST_APPROVAL_AGM) && authorityLevel.equals("AGM")){
			    	    	if (dbDetailCost > limitFrom){
			    	    		changeStatusPcesthtt(estimateNo, deptId, new Long(EstimateStatus.EST_POSTING), webAppName);
			    	    		approval.setFromStatus(statusFrom);
			            		approval.setToStatus(statusTo);
			            		approvalDaoRemote.createAutoIdApprovals(approval, webAppName);
			            		return "#Approved";
			    	    	} else {
			    	    		return "@Can not Approve this";
			    	    	}
			    	    }else {
			    	    	return "@Can not Approve this";
			    	    }
		        	}else{//3phase
		        		if (status.equals(EstimateStatus.EST_APPROVAL_ES) && authorityLevel.equals("ES")){
			        		if (dbDetailCost <= limitTo ){
			            		changeStatusPcesthtt(estimateNo, deptId, new Long(statusTo), webAppName);
			            		approval.setFromStatus(statusFrom);
			            		approval.setToStatus(statusTo);
			            		approvalDaoRemote.createAutoIdApprovals(approval, webAppName);
			            		return "#Sent to"+forewordTo(statusTo.toString())+  "for Approval";
			        		}else if (dbDetailCost > limitTo){
			        			changeStatusPcesthtt(estimateNo, deptId, new Long(statusTo), webAppName);
			        			approval.setFromStatus(statusFrom);
			            		approval.setToStatus(statusTo);
			            		approvalDaoRemote.createAutoIdApprovals(approval, webAppName);
			            		return "#Sent to"+forewordTo(statusTo.toString())+  "for Approval";
			        		}else{
			        			return "@Can not Approve this";
			        		}
			    	    }else if (status.equals(EstimateStatus.EST_APPROVAL_EA) && authorityLevel.equals("EA")){
			    	    	if (dbDetailCost <= limitTo){
			    	    		changeStatusPcesthtt(estimateNo, deptId, new Long(statusTo), webAppName);
			    	    		approval.setFromStatus(statusFrom);
			            		approval.setToStatus(statusTo);
			            		approvalDaoRemote.createAutoIdApprovals(approval, webAppName);
			            		return "#Sent to"+forewordTo(statusTo.toString())+  "for Approval";
			    	    	}else if (dbDetailCost > limitTo){
			        			changeStatusPcesthtt(estimateNo, deptId, new Long(statusTo), webAppName);
			        			approval.setFromStatus(statusFrom);
			            		approval.setToStatus(statusTo);
			            		approvalDaoRemote.createAutoIdApprovals(approval, webAppName);
			            		return "#Sent to"+forewordTo(statusTo.toString())+  "for Approval";
			        		}else{
			        			return "@Can not Approve this";
			        		}
			    	    }else if (status.equals(EstimateStatus.EST_APPROVAL_EA) && authorityLevel.equals("AE") ||authorityLevel.equals("EE") ){
			    	    	if (dbDetailCost <= limitTo){
			    	    		changeStatusPcesthtt(estimateNo, deptId, new Long(EstimateStatus.EST_POSTING), webAppName);
			    	    		approval.setFromStatus(statusFrom);
			            		approval.setToStatus(EstimateStatus.EST_POSTING);
			            		approvalDaoRemote.createAutoIdApprovals(approval, webAppName);
			            		return "#Approved";
			    	    	}else if (dbDetailCost > limitTo) {
			        			changeStatusPcesthtt(estimateNo, deptId, new Long(statusTo), webAppName);
			        			approval.setFromStatus(statusFrom);
			            		approval.setToStatus(statusTo);
			            		approvalDaoRemote.createAutoIdApprovals(approval, webAppName);
			            		return "#Sent to"+forewordTo(statusTo.toString())+  "for Approval";
			        		}else {
			        			return "@Can not Approve this";
			        		}
			    	    }else if (status.equals(EstimateStatus.EST_APPROVAL_CE) && authorityLevel.equals("CE") ){
			    	    	if (dbDetailCost <= limitTo){
			    	    		changeStatusPcesthtt(estimateNo, deptId, new Long(EstimateStatus.EST_POSTING), webAppName);
			    	    		approval.setFromStatus(statusFrom);
			            		approval.setToStatus(EstimateStatus.EST_POSTING);
			            		approvalDaoRemote.createAutoIdApprovals(approval, webAppName);
			            		return "#Approved";
			    	    	}else if (dbDetailCost > limitTo) {
			        			changeStatusPcesthtt(estimateNo, deptId, new Long(statusTo), webAppName);
			        			approval.setFromStatus(statusFrom);
			            		approval.setToStatus(statusTo);
			            		approvalDaoRemote.createAutoIdApprovals(approval, webAppName);
			            		return "#Sent to"+forewordTo(statusTo.toString())+  "for Approval";
			        		}else {
			        			return "@Can not Approve this";
			        		}	
			    	    }else if (status.equals(EstimateStatus.EST_APPROVAL_DGM) && authorityLevel.equals("DGM")){
			    	    	if (dbDetailCost > limitFrom && dbDetailCost <= limitTo){
			    	    		changeStatusPcesthtt(estimateNo, deptId, new Long(EstimateStatus.EST_POSTING), webAppName);
			    	    		approval.setFromStatus(statusFrom);
			            		approval.setToStatus(EstimateStatus.EST_POSTING);
			            		approvalDaoRemote.createAutoIdApprovals(approval, webAppName);
			            		return "#Approved";
			    	    	}else if (dbDetailCost > limitTo){
			        			changeStatusPcesthtt(estimateNo, deptId, new Long(statusTo), webAppName);
			        			approval.setFromStatus(statusFrom);
			            		approval.setToStatus(statusTo);
			            		approvalDaoRemote.createAutoIdApprovals(approval, webAppName);
			            		return "#Sent to"+forewordTo(statusTo.toString())+  "for Approval";
			        		}else {
			        			return "@Can not Approve this";
			        		}
			    	    }else if (status.equals(EstimateStatus.EST_APPROVAL_AGM) && authorityLevel.equals("AGM")){
			    	    	if (dbDetailCost > limitTo){
			    	    		changeStatusPcesthtt(estimateNo, deptId, new Long(EstimateStatus.EST_POSTING), webAppName);
			    	    		approval.setFromStatus(statusFrom);
			            		approval.setToStatus(EstimateStatus.EST_POSTING);
			            		approvalDaoRemote.createAutoIdApprovals(approval, webAppName);
			            		return "#Approved";
			    	    	} else {
			    	    		return "@Can not Approve this";
			    	    	}
			    	    }else {
			    	    	return "@Can not Approve this";
			    	    }
		        		
		        	}
		        	
				}throw new NonUniqueResultException();
				
			}else {
				return "@There is no approve limits for your user Level";
			}
		}catch (Exception e) {
			e.printStackTrace();
			return "@Please contact the MIS Staff.";
		}
		
		
	}
	
	/*@SuppressWarnings("unchecked")
	@Override
	public List<Pcesthtt> getEstApprovalReport(String deptId, BigDecimal status, String value, String webAppName) {
		//getEntityManager(webAppName);
		//String AreaDeptIdList=;
		Query query=null;
		
		if (value.equals("ES")){
			Appestlim appestlim=findAppEstLimits(deptId, "NC", value, webAppName);
			Double limitFrom=Double.parseDouble(String.valueOf(appestlim.getId().getLimitFrom()));
			query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthtt g WHERE trim(g.id.deptId)= :deptId AND g.status = :status  AND stdCost <= :limitFrom  order by g.id.estimateNo");
			query.setParameter("limitFrom", new BigDecimal(limitFrom));
		}else if (value.equals("EA")){
			Appestlim appestlim=findAppEstLimits(deptId, "NC", value, webAppName);
			Double limitFrom=Double.parseDouble(String.valueOf(appestlim.getId().getLimitFrom()));
			Double limitTo=Double.parseDouble(String.valueOf(appestlim.getId().getLimitTo()));
			query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthtt g WHERE trim(g.id.deptId)= :deptId AND g.status = :status  AND g.stdCost > :limitFrom AND g.stdCost <= :limitTo order by g.id.estimateNo");//BETWEEN 15 and 19
	    	query.setParameter("limitTo", new BigDecimal(limitTo));
			query.setParameter("limitFrom", new BigDecimal(limitFrom));
	    	//query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthtt g WHERE trim(g.id.deptId)= :deptId AND g.status = :status  AND stdCost BETWEEN 25000 and 50000");
	    }else if (value.equals("AE") ||value.equals("EE") ){
	    	Appestlim appestlim=findAppEstLimits(deptId, "NC", value, webAppName);
			Double limitFrom=Double.parseDouble(String.valueOf(appestlim.getId().getLimitFrom()));
			Double limitTo=Double.parseDouble(String.valueOf(appestlim.getId().getLimitTo()));
	    	query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthtt g WHERE trim(g.id.deptId)= :deptId AND g.status = :status  AND stdCost > :limitFrom AND stdCost <= :limitTo order by g.id.estimateNo");
	    	query.setParameter("limitTo", new BigDecimal(limitTo));
			query.setParameter("limitFrom", new BigDecimal(limitFrom));
	    }else if (value.equals("CE") ){
	    	Appestlim appestlim=findAppEstLimits(deptId, "NC", value, webAppName);
			Double limitFrom=Double.parseDouble(String.valueOf(appestlim.getId().getLimitFrom()));
			Double limitTo=Double.parseDouble(String.valueOf(appestlim.getId().getLimitTo()));
	    	query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthtt g WHERE trim(g.id.deptId)= :deptId AND g.status = :status  AND stdCost > :limitFrom AND stdCost <= :limitTo order by g.id.estimateNo");
	    	query.setParameter("limitTo", new BigDecimal(limitTo));
			query.setParameter("limitFrom", new BigDecimal(limitFrom));
	    }else if (value.equals("DGM")){
	    	Appestlim appestlim=findAppEstLimits(deptId, "NC", value, webAppName);
			Double limitFrom=Double.parseDouble(String.valueOf(appestlim.getId().getLimitFrom()));
			Double limitTo=Double.parseDouble(String.valueOf(appestlim.getId().getLimitTo()));
	    	query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthtt g WHERE trim(g.id.deptId)= :deptId AND g.status = :status  AND stdCost > :limitFrom AND stdCost <= :limitTo order by g.id.estimateNo");
	    	query.setParameter("limitTo", new BigDecimal(limitTo));
			query.setParameter("limitFrom", new BigDecimal(limitFrom));
	    }else if (value.equals("AGM")){
	    	Appestlim appestlim=findAppEstLimits(deptId, "NC", value, webAppName);
			Double limitFrom=Double.parseDouble(String.valueOf(appestlim.getId().getLimitFrom()));
			Double limitTo=Double.parseDouble(String.valueOf(appestlim.getId().getLimitTo()));
	    	query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthtt g WHERE trim(g.id.deptId)= :deptId AND g.status = :status  AND :limitFrom < stdCost order by g.id.estimateNo");
	    	query.setParameter("limitTo", new BigDecimal(limitTo));
			query.setParameter("limitFrom", new BigDecimal(limitFrom));
	    }
		query.setParameter("deptId", deptId);
		query.setParameter("status", status);
		List<Pcesthtt> list = query.getResultList();
		return list;
	}*/

	@SuppressWarnings("unchecked")
	@Override
	public Pcesthtt findByApplicationNo(String applicationNo, String deptId, String webAppName) {
		//getEntityManager(webAppName);
		String qryStr = "SELECT g FROM Pcesthtt g WHERE TRIM(g.id.estimateNo) = :estimateNo AND g.id.deptId = :deptId";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("estimateNo", applicationNo.trim());
		query.setParameter("deptId", deptId);
		List<Pcesthtt> list = query.getResultList();		
		if (list.isEmpty())
			return null;
        else if (list.size() == 1)
        	return list.get(0);
        throw new NonUniqueResultException();
	}
	
	@SuppressWarnings({ "unchecked" })
	@Override
	// order by g.id.estimateNo
	public List<TestObject> getEstimateNoList1(String deptId, String webAppName) {
		//getEntityManager(webAppName);
		Query query = getEntityManager(webAppName).createQuery("SELECT new costcenter.model.TestObject (TRIM(g.id.estimateNo) AS a,trim(g.id.deptId) AS b) FROM Pcesthtt g WHERE trim(g.id.deptId)= :deptId order by g.id.estimateNo");
		query.setParameter("deptId", deptId);
		List<TestObject> list = query.getResultList();
		return list;
		
	}
	@SuppressWarnings("rawtypes")
	@Override
	public long getHttRevNo(String estimateNo,String deptId, String webAppName) {
		//getEntityManager(webAppName);  
		long revNo = -1;
	      try
	      {
	            String qryStr = " select max(p.id.revNo) from Pcesthtt p where p.id.deptId = :deptId " +
	                                    " and p.id.estimateNo = :estimateNo ";
	            
	            Query query = getEntityManager(webAppName).createQuery(qryStr);
	            query.setParameter("estimateNo", estimateNo.trim());
	            query.setParameter("deptId", deptId);
	              
	              List list = query.getResultList();
	              if(list.get(0)!=null )
	              {
	                  revNo = (Long)list.get(0);
	              }
	      }
	      catch(Exception e){
	            
	            e.printStackTrace();
	      }
	      return revNo;
	    }
	@SuppressWarnings("rawtypes")
	@Override
	public long getMinHttRevNo(String estimateNo,String deptId, String webAppName) {
		//getEntityManager(webAppName);  
		long revNo = -1;
	      try
	      {
	            String qryStr = " select min(p.id.revNo) from Pcesthtt p where trim(p.id.deptId) = :deptId " +
	                                    " and trim(p.id.estimateNo) = :estimateNo ";
	            
	            Query query = getEntityManager(webAppName).createQuery(qryStr);
	            query.setParameter("estimateNo", estimateNo.trim());
	            query.setParameter("deptId", deptId);
	              
	              List list = query.getResultList();
	              if(list.get(0)!=null )
	              {
	                  revNo = (Long)list.get(0);
	              }
	      }
	      catch(Exception e){
	            
	            e.printStackTrace();
	      }
	      return revNo;
	    }
	@Override
	public void changeStatusPcesthtt(String estimateNo,String deptId, Long status, String webAppName) {
		//getEntityManager(webAppName);
		//System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		String qryStr = "UPDATE Pcesthtt g set g.status=:status WHERE TRIM(g.id.estimateNo)= :estimateNo AND g.id.deptId = :deptId";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("status", status);
		query.setParameter("estimateNo", estimateNo.trim());
		query.setParameter("deptId", deptId);
		query.executeUpdate();
		
	}
	
	@Override
	public void changeCostCenterNoPcesthtt(String estimateNo,String areaDeptId, String depotDeptId , String webAppName) {
		//getEntityManager(webAppName);
		//System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		String qryStr = "UPDATE Pcesthtt g set g.id.deptId=:depotDeptId WHERE TRIM(g.id.estimateNo)= :estimateNo AND g.id.deptId = :areaDeptId";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("depotDeptId", depotDeptId);
		query.setParameter("estimateNo", estimateNo.trim());
		query.setParameter("areaDeptId", areaDeptId);
		query.executeUpdate();
		
	}
	
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void validateEstimate(Pcesthtt pcesthtt, Approval approval, String webAppName) {
		try{
			updatePcesthtt(pcesthtt, webAppName);
			approvalDaoRemote.createAutoIdApprovals(approval, webAppName);
			
			
		}catch (Exception e) {
			//e.printStackTrace();
			System.out.println(e.getMessage());
			context.setRollbackOnly();
			throw new RuntimeException(e);
		}
		
	}
	
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void rejectEstimate(Pcesthtt pcesthtt, Approval approval, String webAppName) {
		try{
			updatePcesthtt(pcesthtt, webAppName);
			approvalDaoRemote.createAutoIdConstructionEstimateApprovals(approval, webAppName);
			
			
		}catch (Exception e) {
			//e.printStackTrace();
			System.out.println(e.getMessage());
			context.setRollbackOnly();
			throw new RuntimeException(e);
		}
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Appestlim findAppEstLimits(String costCenterNo,String approvalType, String applicationType, String userLevel, String webAppName){
		//getEntityManager(webAppName);
		//return getEntityManager(webAppName).find(Pcesthtt.class, id);
		//String qryStr = "SELECT new estimate.model.AppestlimPK(deptId,applicationType, userLevel, limitFrom, limitTo, statusFrom, statusTo)  FROM Appestlim g where  g.id.deptId = :deptId AND g.id.applicationType = :applicationType AND g.id.userLevel = :userLevel";
		System.out.println("hiiiiihy");
		System.out.println(costCenterNo + "  approvalType : " + approvalType +"   userLevel  : "+userLevel + "  applicationType  : "+ applicationType);
		StringBuffer buff = new StringBuffer();
		System.out.println("hiiiiihy2");
		buff.append("SELECT g  FROM Appestlim g where  g.id.deptId = :deptId ");
		//String qryStr = "SELECT g  FROM Appestlim g where  g.id.deptId = :deptId AND g.id.applicationType = :applicationType AND g.id.approvalType = :approvalType AND g.id.userLevel = :userLevel";
		//if(applicationType != null || applicationType == "DM"){
			//applicationType = "DE";
		//}
		
		if(applicationType != null){
			buff.append("AND g.id.applicationType = :applicationType ");
		}
		System.out.println("hiiiiihy3");
		buff.append(" AND g.id.approvalType = :approvalType AND g.id.userLevel = :userLevel ");
		Query query = getEntityManager(webAppName).createQuery(buff.toString());
		query.setParameter("deptId", costCenterNo);
		if(applicationType != null){
			query.setParameter("applicationType", applicationType);
		}
		System.out.println("hiiiiihy4");
		query.setParameter("approvalType", approvalType);
		query.setParameter("userLevel", userLevel);
		
		List<Appestlim> list = query.getResultList();
		System.out.println("hiiiiihy4 : " +list );
	
			return list.get(0);
		
		
		
		
		/**System.out.println(buff.toString() + " dd 1 : " + list );
		
		
		if (list.isEmpty()){
			System.out.println(buff.toString() + " dd 3 : " + list );
			return null;
		}
        else if (list.size() == 1){
        	System.out.println(buff.toString() + " dd 2: " + list.get(0) );
        	return list.get(0);
        }
        throw new NonUniqueResultException();
        */
		
	}
	
	private String forewordTo(String status){
		if (status.equals(EstimateStatus.EST_APPROVAL_EA)){
			return " Engineering Assistant ";
		}else if (status.equals(EstimateStatus.EST_APPROVAL_ES)){
			return " Electrical Spirintdendant ";
		}else if (status.equals(EstimateStatus.EST_APPROVAL_ES)){
			return " Electrical Spirintdendant ";
		}else if (status.equals(EstimateStatus.EST_APPROVAL_EE)){
			return " Electrical Engineer ";
		}else if (status.equals(EstimateStatus.EST_APPROVAL_CE)){
			return " Cheif Engineer";
		}else if (status.equals(EstimateStatus.EST_APPROVAL_DGM)){
			return " DGM ";
		}else if (status.equals(EstimateStatus.EST_APPROVAL_AGM)){
			return " AGM ";
		} else {
			return " ";
		}
		
	}

	
	
	
	/*@SuppressWarnings("unchecked")
	@Override
	public List<ReferenceDeleteInfo> getReferenceDeleteInfoList(String deptId, String webAppName) {
		//Query query = getEntityManager(webAppName).createQuery("SELECT TRIM(g.id.estimateNo), TRIM(g.id.deptId),TRIM(g.id.revNo()),TRIM(etimateDt),TRIM(g.estType) FROM Pcesthtt g WHERE trim(g.id.deptId)= :deptId order by g.id.estimateNo");
		Query query = getEntityManager(webAppName).createQuery("SELECT TRIM(g.id.estimateNo), TRIM(g.id.deptId) FROM Pcesthtt g WHERE trim(g.id.deptId)= :deptId order by g.id.estimateNo");
		query.setParameter("deptId", deptId);
		List<ReferenceDeleteInfo> list = query.getResultList();
		return list;
	}*/
	
	 
	
	/*@SuppressWarnings("unchecked")
	private List<String> getEstimateNoList(EntityManager emf,String deptID, String webAppName) {
		Query query = emf.createQuery("SELECT g FROM Pcesthtt g order by g.id.estimateNo");
		List<Pcesthtt> noList = query.getResultList();
		List<String> estimateNumberList = new  ArrayList<String>();

		referenceDeleteList = new  ArrayList<String>();		
		Iterator<Pcesthtt> it = noList.iterator();		
        while (it.hasNext()) 
        { 
        	Pcesthtt pct = it.next();
        	estimateNumberList.add( pct.getId().getEstimateNo());
        	referenceDeleteList.add(pct.getId().getDeptId()+","+pct.getId().getRevNo()+","+pct.getEtimateDt()+","+pct.getEstType());
        }
		return estimateNumberList;
	}*/
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Pcesthtt> getEstApprovalList(String deptId, Long status,	String value, String webAppName) {
		//getEntityManager(webAppName);
		//Appestlim appestlim=findAppEstLimits(deptId, "NC", value, webAppName);
		//Double limitFrom=Double.parseDouble(String.valueOf(appestlim.getId().getLimitFrom()));
		//Double limitTo=Double.parseDouble(String.valueOf(appestlim.getId().getLimitTo()));
		Query query=null;
		if (value.equals("ES")){
			Appestlim appestlim=findAppEstLimits(deptId, "EST", "NC", value, webAppName);
			Double limitFrom=Double.parseDouble(String.valueOf(appestlim.getId().getLimitFrom()));
			query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthtt  g WHERE trim(g.id.deptId)= :deptId AND g.status = :status  AND stdCost <= :limitFrom  order by g.id.estimateNo");
			query.setParameter("limitFrom", new BigDecimal(limitFrom));
		}else if (value.equals("EA")){
			Appestlim appestlim=findAppEstLimits(deptId, "EST", "NC", value, webAppName);
			Double limitFrom=Double.parseDouble(String.valueOf(appestlim.getId().getLimitFrom()));
			Double limitTo=Double.parseDouble(String.valueOf(appestlim.getId().getLimitTo()));
			query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthtt g WHERE trim(g.id.deptId)= :deptId AND g.status = :status  AND g.stdCost > :limitFrom AND g.stdCost <= :limitTo order by g.id.estimateNo");//BETWEEN 15 and 19
			query.setParameter("limitTo", new BigDecimal(limitTo));
			query.setParameter("limitFrom", new BigDecimal(limitFrom));
			//query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthtt g WHERE trim(g.id.deptId)= :deptId AND g.status = :status  AND stdCost BETWEEN 25000 and 50000");
	    }else if (value.equals("AE") ||value.equals("EE") ){
	    	Appestlim appestlim=findAppEstLimits(deptId, "EST", "NC", value, webAppName);
			Double limitFrom=Double.parseDouble(String.valueOf(appestlim.getId().getLimitFrom()));
			Double limitTo=Double.parseDouble(String.valueOf(appestlim.getId().getLimitTo()));
	    	query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthtt g WHERE trim(g.id.deptId)= :deptId AND g.status = :status  AND stdCost > :limitFrom AND stdCost <= :limitTo order by g.id.estimateNo");
	    	query.setParameter("limitTo", new BigDecimal(limitTo));
			query.setParameter("limitFrom", new BigDecimal(limitFrom));
	    }else if (value.equals("CE") ){
	    	
	    	Appestlim appestlim=findAppEstLimits(deptId, "EST", "NC", value, webAppName);
	    	System.out.println(appestlim);
	    	Double limitFrom=Double.parseDouble(String.valueOf(appestlim.getId().getLimitFrom()));
			Double limitTo=Double.parseDouble(String.valueOf(appestlim.getId().getLimitTo()));
	    	query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthtt g WHERE trim(g.id.deptId)= :deptId AND g.status = :status  AND stdCost > :limitFrom AND stdCost <= :limitTo order by g.id.estimateNo");
	    	query.setParameter("limitTo", new BigDecimal(limitTo));
			query.setParameter("limitFrom", new BigDecimal(limitFrom));
	    }else if (value.equals("DGM")){
	    	Appestlim appestlim=findAppEstLimits(deptId, "EST", "NC", value, webAppName);
			System.out.println(appestlim);
	    	Double limitFrom=Double.parseDouble(String.valueOf(appestlim.getId().getLimitFrom()));
			Double limitTo=Double.parseDouble(String.valueOf(appestlim.getId().getLimitTo()));
	    	query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthtt g WHERE trim(g.id.deptId)= :deptId AND g.status = :status  AND stdCost > :limitFrom AND stdCost <= :limitTo order by g.id.estimateNo");
	    	query.setParameter("limitTo", new BigDecimal(limitTo));
			query.setParameter("limitFrom", new BigDecimal(limitFrom));
	    }else if (value.equals("AGM")){
	    	Appestlim appestlim=findAppEstLimits(deptId, "EST", "NC", value, webAppName);
			Double limitFrom=Double.parseDouble(String.valueOf(appestlim.getId().getLimitFrom()));
			query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthtt g WHERE trim(g.id.deptId)= :deptId AND g.status = :status  AND :limitFrom < stdCost order by g.id.estimateNo");
	    	query.setParameter("limitFrom", new BigDecimal(limitFrom));
	    }
		query.setParameter("deptId", deptId);
		query.setParameter("status", status);
		List<Pcesthtt> list = query.getResultList();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<String> getEstNoApprovalListNew(String deptId,	String authorityLevel, String webAppName) {
		//getEntityManager(webAppName);
		Query query=null;
		BigDecimal status = null;
		if (authorityLevel.equals("ES")){
			status=new BigDecimal(EstimateStatus.EST_APPROVAL_ES); 
	    }else if (authorityLevel.equals("EA")){
	    	status=new BigDecimal(EstimateStatus.EST_APPROVAL_EA); 
	    }else if (authorityLevel.equals("AE") ||authorityLevel.equals("EE") ){
	    	status=new BigDecimal(EstimateStatus.EST_APPROVAL_EE); 
	    }else if (authorityLevel.equals("CE") ){
	    	status=new BigDecimal(EstimateStatus.EST_APPROVAL_CE); 
	    }else if (authorityLevel.equals("DGM")){
	    	status=new BigDecimal(EstimateStatus.EST_APPROVAL_DGM); 
	    }else if (authorityLevel.equals("AGM")){
	    	status=new BigDecimal(EstimateStatus.EST_APPROVAL_AGM); 
	    }
		if (authorityLevel.equals("ES")){
			//query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthtt g, Speststd s WHERE TRIM(g.id.estimateNo)=s.id.estimateNo AND g.id.deptId=s.id.deptId AND trim(g.id.deptId)= :deptId AND g.status = :status  AND s.totalCost <= 25000  order by g.id.estimateNo");
			query = getEntityManager(webAppName).createQuery("SELECT TRIM(g.id.estimateNo) FROM Pcesthtt g WHERE trim(g.id.deptId)= :deptId AND g.status = :status  order by g.id.estimateNo");
		}else if (authorityLevel.equals("EA")){
	    	//query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthtt g, Speststd s WHERE TRIM(g.id.estimateNo)=s.id.estimateNo AND g.id.deptId=s.id.deptId AND trim(g.id.deptId)= :deptId AND g.status = :status  AND s.totalCost > 25000 AND s.totalCost <= 50000 order by g.id.estimateNo");//BETWEEN 15 and 19
			query = getEntityManager(webAppName).createQuery("SELECT TRIM(g.id.estimateNo) FROM Pcesthtt g WHERE trim(g.id.deptId)= :deptId AND g.status = :status  order by g.id.estimateNo");
	    }else if (authorityLevel.equals("AE") ||authorityLevel.equals("EE") ){
	    	//query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthtt g, Speststd s WHERE TRIM(g.id.estimateNo)=s.id.estimateNo AND g.id.deptId=s.id.deptId AND trim(g.id.deptId)= :deptId AND g.status = :status  AND s.totalCost > 50000 AND s.totalCost <= 100000 order by g.id.estimateNo");
	    	query = getEntityManager(webAppName).createQuery("SELECT TRIM(g.id.estimateNo) FROM Pcesthtt g WHERE trim(g.id.deptId)= :deptId AND g.status = :status  order by g.id.estimateNo");
	    }else if (authorityLevel.equals("CE")  ){
	    	//query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthtt g, Speststd s WHERE TRIM(g.id.estimateNo)=s.id.estimateNo AND g.id.deptId=s.id.deptId AND trim(g.id.deptId)= :deptId AND g.status = :status  AND s.totalCost > 50000 AND s.totalCost <= 100000 order by g.id.estimateNo");
	    	query = getEntityManager(webAppName).createQuery("SELECT TRIM(g.id.estimateNo) FROM Pcesthtt g WHERE trim(g.id.deptId)= :deptId AND g.status = :status  order by g.id.estimateNo");
	    }else if (authorityLevel.equals("DGM")){
	    	//query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthtt g, Speststd s WHERE TRIM(g.id.estimateNo)=s.id.estimateNo AND g.id.deptId=s.id.deptId AND trim(g.id.deptId)= :deptId AND g.status = :status  AND s.totalCost > 100000 AND s.totalCost <= 200000 order by g.id.estimateNo");
	    	query = getEntityManager(webAppName).createQuery("SELECT TRIM(g.id.estimateNo) FROM Pcesthtt g WHERE trim(g.id.deptId)= :deptId AND g.status = :status  order by g.id.estimateNo");
	    }else if (authorityLevel.equals("AGM")){
	    	//query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthtt g, Speststd s WHERE TRIM(g.id.estimateNo)=s.id.estimateNo AND g.id.deptId=s.id.deptId AND trim(g.id.deptId)= :deptId AND g.status = :status  AND 200000 < s.totalCost order by g.id.estimateNo");
	    	query = getEntityManager(webAppName).createQuery("SELECT TRIM(g.id.estimateNo) FROM Pcesthtt g WHERE trim(g.id.deptId)= :deptId AND g.status = :status  order by g.id.estimateNo");
	    }
		query.setParameter("deptId", deptId);
		query.setParameter("status", status);
		List<String> list = query.getResultList();
		return list;
	}
	
	/*@SuppressWarnings("unchecked")
	@Override
	public List<String> getEstNoApprovalListNew(String deptId,	String authorityLevel, String applicationType, String webAppName) {
		Query query=null;
		BigDecimal status = null;
		if (authorityLevel.equals("ES")){
			status=new BigDecimal(44); 
	    }else if (authorityLevel.equals("EA")){
	    	status=new BigDecimal(45); 
	    }else if (authorityLevel.equals("AE") ||authorityLevel.equals("EE") ){
	    	status=new BigDecimal(46); 
	    }else if (authorityLevel.equals("DGM")){
	    	status=new BigDecimal(47); 
	    }else if (authorityLevel.equals("AGM")){
	    	status=new BigDecimal(48); 
	    }
		if (authorityLevel.equals("ES")){
			//query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthtt g, Speststd s WHERE TRIM(g.id.estimateNo)=s.id.estimateNo AND g.id.deptId=s.id.deptId AND trim(g.id.deptId)= :deptId AND g.status = :status  AND s.totalCost <= 25000  order by g.id.estimateNo");
			query = getEntityManager(webAppName).createQuery("SELECT TRIM(g.id.estimateNo) FROM Pcesthtt g , Application a WHERE  TRIM(g.id.estimateNo)=a.applicationNo AND a.applicationType=:applicationType AND trim(g.id.deptId)= :deptId AND g.status = :status  order by g.id.estimateNo");
		}else if (authorityLevel.equals("EA")){
	    	//query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthtt g, Speststd s WHERE TRIM(g.id.estimateNo)=s.id.estimateNo AND g.id.deptId=s.id.deptId AND trim(g.id.deptId)= :deptId AND g.status = :status  AND s.totalCost > 25000 AND s.totalCost <= 50000 order by g.id.estimateNo");//BETWEEN 15 and 19
			query = getEntityManager(webAppName).createQuery("SELECT TRIM(g.id.estimateNo) FROM Pcesthtt g , Application a WHERE  TRIM(g.id.estimateNo)=a.applicationNo AND a.applicationType=:applicationType AND trim(g.id.deptId)= :deptId AND g.status = :status  order by g.id.estimateNo");
	    }else if (authorityLevel.equals("AE") ||authorityLevel.equals("EE") ){
	    	//query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthtt g, Speststd s WHERE TRIM(g.id.estimateNo)=s.id.estimateNo AND g.id.deptId=s.id.deptId AND trim(g.id.deptId)= :deptId AND g.status = :status  AND s.totalCost > 50000 AND s.totalCost <= 100000 order by g.id.estimateNo");
	    	query = getEntityManager(webAppName).createQuery("SELECT TRIM(g.id.estimateNo) FROM Pcesthtt g , Application a WHERE  TRIM(g.id.estimateNo)=a.applicationNo AND a.applicationType=:applicationType AND trim(g.id.deptId)= :deptId AND g.status = :status  order by g.id.estimateNo");
	    }else if (authorityLevel.equals("DGM")){
	    	//query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthtt g, Speststd s WHERE TRIM(g.id.estimateNo)=s.id.estimateNo AND g.id.deptId=s.id.deptId AND trim(g.id.deptId)= :deptId AND g.status = :status  AND s.totalCost > 100000 AND s.totalCost <= 200000 order by g.id.estimateNo");
	    	query = getEntityManager(webAppName).createQuery("SELECT TRIM(g.id.estimateNo) FROM Pcesthtt g , Application a WHERE  TRIM(g.id.estimateNo)=a.applicationNo AND a.applicationType=:applicationType AND trim(g.id.deptId)= :deptId AND g.status = :status  order by g.id.estimateNo");
	    }else if (authorityLevel.equals("AGM")){
	    	//query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthtt g, Speststd s WHERE TRIM(g.id.estimateNo)=s.id.estimateNo AND g.id.deptId=s.id.deptId AND trim(g.id.deptId)= :deptId AND g.status = :status  AND 200000 < s.totalCost order by g.id.estimateNo");
	    	query = getEntityManager(webAppName).createQuery("SELECT TRIM(g.id.estimateNo) FROM Pcesthtt g , Application a WHERE  TRIM(g.id.estimateNo)=a.applicationNo AND a.applicationType=:applicationType AND  trim(g.id.deptId)= :deptId AND g.status = :status  order by g.id.estimateNo");
	    }
		query.setParameter("applicationType", applicationType);
		query.setParameter("deptId", deptId);
		query.setParameter("status", status);
		List<String> list = query.getResultList();
		return list;
	}*/
	
	@SuppressWarnings("unchecked")
	@Override
	public List<String> getEstNoApprovalListNewT(String deptId,	String authorityLevel, String applicationType, String webAppName) {
		//getEntityManager(webAppName);
		Query query=null;
		BigDecimal status = null;
		if (authorityLevel.equals("ES")){
			status=new BigDecimal(EstimateStatus.EST_APPROVAL_ES); 
	    }else if (authorityLevel.equals("EA")){
	    	status=new BigDecimal(EstimateStatus.EST_APPROVAL_EA); 
	    }else if (authorityLevel.equals("AE") ||authorityLevel.equals("EE") ){
	    	status=new BigDecimal(EstimateStatus.EST_APPROVAL_EE); 
	    }else if (authorityLevel.equals("CE") ){
	    	status=new BigDecimal(EstimateStatus.EST_APPROVAL_CE); 
	    }else if (authorityLevel.equals("DGM")){
	    	status=new BigDecimal(EstimateStatus.EST_APPROVAL_DGM); 
	    }else if (authorityLevel.equals("AGM")){
	    	status=new BigDecimal(EstimateStatus.EST_APPROVAL_AGM); 
	    }
		if (authorityLevel.equals("ES")){
			//query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthtt g, Speststd s WHERE TRIM(g.id.estimateNo)=s.id.estimateNo AND g.id.deptId=s.id.deptId AND trim(g.id.deptId)= :deptId AND g.status = :status  AND s.totalCost <= 25000  order by g.id.estimateNo");
			query = getEntityManager(webAppName).createQuery("SELECT TRIM(g.id.estimateNo) FROM Pcesthtt g , Application a WHERE  trim(g.id.deptId)= :deptId AND g.status = :status AND TRIM(g.id.estimateNo)=a.applicationNo AND a.applicationType=:applicationType order by g.id.estimateNo");
		}else if (authorityLevel.equals("EA")){
	    	//query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthtt g, Speststd s WHERE TRIM(g.id.estimateNo)=s.id.estimateNo AND g.id.deptId=s.id.deptId AND trim(g.id.deptId)= :deptId AND g.status = :status  AND s.totalCost > 25000 AND s.totalCost <= 50000 order by g.id.estimateNo");//BETWEEN 15 and 19
			query = getEntityManager(webAppName).createQuery("SELECT TRIM(g.id.estimateNo) FROM Pcesthtt g , Application a WHERE  trim(g.id.deptId)= :deptId AND g.status = :status AND TRIM(g.id.estimateNo)=a.applicationNo AND a.applicationType=:applicationType order by g.id.estimateNo");
	    }else if (authorityLevel.equals("AE") ||authorityLevel.equals("EE") ){
	    	//query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthtt g, Speststd s WHERE TRIM(g.id.estimateNo)=s.id.estimateNo AND g.id.deptId=s.id.deptId AND trim(g.id.deptId)= :deptId AND g.status = :status  AND s.totalCost > 50000 AND s.totalCost <= 100000 order by g.id.estimateNo");
	    	query = getEntityManager(webAppName).createQuery("SELECT TRIM(g.id.estimateNo) FROM Pcesthtt g , Application a WHERE  trim(g.id.deptId)= :deptId AND g.status = :status AND TRIM(g.id.estimateNo)=a.applicationNo AND a.applicationType=:applicationType order by g.id.estimateNo");
	    }else if (authorityLevel.equals("CE") ){
	    	//query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthtt g, Speststd s WHERE TRIM(g.id.estimateNo)=s.id.estimateNo AND g.id.deptId=s.id.deptId AND trim(g.id.deptId)= :deptId AND g.status = :status  AND s.totalCost > 50000 AND s.totalCost <= 100000 order by g.id.estimateNo");
	    	query = getEntityManager(webAppName).createQuery("SELECT TRIM(g.id.estimateNo) FROM Pcesthtt g , Application a WHERE  trim(g.id.deptId)= :deptId AND g.status = :status AND TRIM(g.id.estimateNo)=a.applicationNo AND a.applicationType=:applicationType order by g.id.estimateNo");
	    }else if (authorityLevel.equals("DGM")){
	    	//query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthtt g, Speststd s WHERE TRIM(g.id.estimateNo)=s.id.estimateNo AND g.id.deptId=s.id.deptId AND trim(g.id.deptId)= :deptId AND g.status = :status  AND s.totalCost > 100000 AND s.totalCost <= 200000 order by g.id.estimateNo");
	    	query = getEntityManager(webAppName).createQuery("SELECT TRIM(g.id.estimateNo) FROM Pcesthtt g , Application a WHERE  trim(g.id.deptId)= :deptId AND g.status = :status AND TRIM(g.id.estimateNo)=a.applicationNo AND a.applicationType=:applicationType order by g.id.estimateNo");
	    }else if (authorityLevel.equals("AGM")){
	    	//query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthtt g, Speststd s WHERE TRIM(g.id.estimateNo)=s.id.estimateNo AND g.id.deptId=s.id.deptId AND trim(g.id.deptId)= :deptId AND g.status = :status  AND 200000 < s.totalCost order by g.id.estimateNo");
	    	query = getEntityManager(webAppName).createQuery("SELECT TRIM(g.id.estimateNo) FROM Pcesthtt g , Application a WHERE  trim(g.id.deptId)= :deptId AND g.status = :status AND TRIM(g.id.estimateNo)=a.applicationNo AND a.applicationType=:applicationType order by g.id.estimateNo");
	    }
		
		query.setParameter("deptId", deptId);
		query.setParameter("status", status);
		query.setParameter("applicationType", applicationType);
		List<String> list = query.getResultList();
		return list;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<String> getEstNoApprovalListNewMT_SA(String deptId,	String authorityLevel, String webAppName) {
		//getEntityManager(webAppName);
		Query query=null;
		BigDecimal status = null;
		if (authorityLevel.equals("ES")){
			status=new BigDecimal(EstimateStatus.EST_APPROVAL_ES); 
	    }else if (authorityLevel.equals("EA")){
	    	status=new BigDecimal(EstimateStatus.EST_APPROVAL_EA); 
	    }else if (authorityLevel.equals("AE") ||authorityLevel.equals("EE") ){
	    	status=new BigDecimal(EstimateStatus.EST_APPROVAL_EE); 
	    }else if (authorityLevel.equals("CE") ){
	    	status=new BigDecimal(EstimateStatus.EST_APPROVAL_CE); 
	    }else if (authorityLevel.equals("DGM")){
	    	status=new BigDecimal(EstimateStatus.EST_APPROVAL_DGM); 
	    }else if (authorityLevel.equals("AGM")){
	    	status=new BigDecimal(EstimateStatus.EST_APPROVAL_AGM); 
	    }
		if (authorityLevel.equals("ES")){
			//query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthtt g, Speststd s WHERE TRIM(g.id.estimateNo)=s.id.estimateNo AND g.id.deptId=s.id.deptId AND trim(g.id.deptId)= :deptId AND g.status = :status  AND s.totalCost <= 25000  order by g.id.estimateNo");
			query = getEntityManager(webAppName).createQuery("SELECT TRIM(g.id.estimateNo) FROM Pcesthtt g , Application a WHERE  trim(g.id.deptId)= :deptId AND g.status = :status AND TRIM(g.id.estimateNo)=a.applicationNo AND (a.applicationType='MT' OR a.applicationType='LS' OR a.applicationType='PS' OR a.applicationType='SA' OR a.applicationType='BD' OR a.applicationType='CC'  OR a.applicationType='CS' OR a.applicationType= 'CO'  OR a.applicationType='TR'  OR a.applicationType='TP'  OR a.applicationType='MR' OR a.applicationType= 'PF' OR a.applicationType= 'MF' OR a.applicationType= 'DB' OR a.applicationType= 'RM') order by g.id.estimateNo");
		}else if (authorityLevel.equals("EA")){
	    	//query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthtt g, Speststd s WHERE TRIM(g.id.estimateNo)=s.id.estimateNo AND g.id.deptId=s.id.deptId AND trim(g.id.deptId)= :deptId AND g.status = :status  AND s.totalCost > 25000 AND s.totalCost <= 50000 order by g.id.estimateNo");//BETWEEN 15 and 19
			query = getEntityManager(webAppName).createQuery("SELECT TRIM(g.id.estimateNo) FROM Pcesthtt g , Application a WHERE  trim(g.id.deptId)= :deptId AND g.status = :status AND TRIM(g.id.estimateNo)=a.applicationNo AND (a.applicationType='MT' OR a.applicationType='LS' OR a.applicationType='PS' OR a.applicationType='SA' OR a.applicationType='BD' OR a.applicationType='CC'  OR a.applicationType='CS' OR a.applicationType= 'CO'  OR a.applicationType='TR'  OR a.applicationType='TP'  OR a.applicationType='MR' OR a.applicationType= 'PF' OR a.applicationType= 'MF' OR a.applicationType= 'DB' OR a.applicationType= 'RM') order by g.id.estimateNo");
	    }else if (authorityLevel.equals("AE") ||authorityLevel.equals("EE") ){
	    	//query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthtt g, Speststd s WHERE TRIM(g.id.estimateNo)=s.id.estimateNo AND g.id.deptId=s.id.deptId AND trim(g.id.deptId)= :deptId AND g.status = :status  AND s.totalCost > 50000 AND s.totalCost <= 100000 order by g.id.estimateNo");
	    	query = getEntityManager(webAppName).createQuery("SELECT TRIM(g.id.estimateNo) FROM Pcesthtt g , Application a WHERE  trim(g.id.deptId)= :deptId AND g.status = :status AND TRIM(g.id.estimateNo)=a.applicationNo AND (a.applicationType='MT' OR a.applicationType='LS' OR a.applicationType='PS' OR a.applicationType='SA' OR a.applicationType='BD' OR a.applicationType='CC'  OR a.applicationType='CS' OR a.applicationType= 'CO'  OR a.applicationType='TR'  OR a.applicationType='TP'  OR a.applicationType='MR' OR a.applicationType= 'PF' OR a.applicationType= 'MF' OR a.applicationType= 'DB' OR a.applicationType= 'RM') order by g.id.estimateNo");
	    }else if (authorityLevel.equals("CE") ){
	    	//query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthtt g, Speststd s WHERE TRIM(g.id.estimateNo)=s.id.estimateNo AND g.id.deptId=s.id.deptId AND trim(g.id.deptId)= :deptId AND g.status = :status  AND s.totalCost > 50000 AND s.totalCost <= 100000 order by g.id.estimateNo");
	    	query = getEntityManager(webAppName).createQuery("SELECT TRIM(g.id.estimateNo) FROM Pcesthtt g , Application a WHERE  trim(g.id.deptId)= :deptId AND g.status = :status AND TRIM(g.id.estimateNo)=a.applicationNo AND (a.applicationType='MT' OR a.applicationType='LS' OR a.applicationType='PS' OR a.applicationType='SA' OR a.applicationType='BD' OR a.applicationType='CC'  OR a.applicationType='CS' OR a.applicationType= 'CO'  OR a.applicationType='TR'  OR a.applicationType='TP'  OR a.applicationType='MR' OR a.applicationType= 'PF' OR a.applicationType= 'MF' OR a.applicationType= 'DB' OR a.applicationType= 'RM') order by g.id.estimateNo");
	    }else if (authorityLevel.equals("DGM")){
	    	//query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthtt g, Speststd s WHERE TRIM(g.id.estimateNo)=s.id.estimateNo AND g.id.deptId=s.id.deptId AND trim(g.id.deptId)= :deptId AND g.status = :status  AND s.totalCost > 100000 AND s.totalCost <= 200000 order by g.id.estimateNo");
	    	query = getEntityManager(webAppName).createQuery("SELECT TRIM(g.id.estimateNo) FROM Pcesthtt g , Application a WHERE  trim(g.id.deptId)= :deptId AND g.status = :status AND TRIM(g.id.estimateNo)=a.applicationNo AND (a.applicationType='MT' OR a.applicationType='LS' OR a.applicationType='PS' OR a.applicationType='SA' OR a.applicationType='BD' OR a.applicationType='CC'  OR a.applicationType='CS' OR a.applicationType= 'CO'  OR a.applicationType='TR'  OR a.applicationType='TP'  OR a.applicationType='MR' OR a.applicationType= 'PF' OR a.applicationType= 'MF' OR a.applicationType= 'DB' OR a.applicationType= 'RM') order by g.id.estimateNo");
	    }else if (authorityLevel.equals("AGM")){
	    	//query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthtt g, Speststd s WHERE TRIM(g.id.estimateNo)=s.id.estimateNo AND g.id.deptId=s.id.deptId AND trim(g.id.deptId)= :deptId AND g.status = :status  AND 200000 < s.totalCost order by g.id.estimateNo");
	    	query = getEntityManager(webAppName).createQuery("SELECT TRIM(g.id.estimateNo) FROM Pcesthtt g , Application a WHERE  trim(g.id.deptId)= :deptId AND g.status = :status AND TRIM(g.id.estimateNo)=a.applicationNo AND (a.applicationType='MT' OR a.applicationType='LS' OR a.applicationType='PS' OR a.applicationType='SA' OR a.applicationType='BD' OR a.applicationType='CC'  OR a.applicationType='CS' OR a.applicationType= 'CO'  OR a.applicationType='TR'  OR a.applicationType='TP'  OR a.applicationType='MR' OR a.applicationType= 'PF' OR a.applicationType= 'MF' OR a.applicationType= 'DB' OR a.applicationType= 'RM') order by g.id.estimateNo");
	    }
		
		query.setParameter("deptId", deptId);
		query.setParameter("status", status);
		//query.setParameter("applicationType", applicationType);
		List<String> list = query.getResultList();
		return list;
	}
	
	
	/*@SuppressWarnings("unchecked")
	@Override
	public List<String> getEstNoApprovalListNew(String deptId,	String authorityLevel, String applicationType, String applicationSubType, String webAppName) {
		Query query=null;
		BigDecimal status = null;
		if (authorityLevel.equals("ES")){
			status=new BigDecimal(44); 
	    }else if (authorityLevel.equals("EA")){
	    	status=new BigDecimal(45); 
	    }else if (authorityLevel.equals("AE") ||authorityLevel.equals("EE") ){
	    	status=new BigDecimal(46); 
	    }else if (authorityLevel.equals("DGM")){
	    	status=new BigDecimal(47); 
	    }else if (authorityLevel.equals("AGM")){
	    	status=new BigDecimal(48); 
	    }
		if (authorityLevel.equals("ES")){
			//query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthtt g, Speststd s WHERE TRIM(g.id.estimateNo)=s.id.estimateNo AND g.id.deptId=s.id.deptId AND trim(g.id.deptId)= :deptId AND g.status = :status  AND s.totalCost <= 25000  order by g.id.estimateNo");
			query = getEntityManager(webAppName).createQuery("SELECT TRIM(g.id.estimateNo) FROM Pcesthtt g , Application a WHERE  TRIM(g.id.estimateNo)=a.applicationNo AND a.applicationType=:applicationType AND a.applicationSubType=:applicationSubType AND trim(g.id.deptId)= :deptId AND g.status = :status  order by g.id.estimateNo");
		}else if (authorityLevel.equals("EA")){
	    	//query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthtt g, Speststd s WHERE TRIM(g.id.estimateNo)=s.id.estimateNo AND g.id.deptId=s.id.deptId AND trim(g.id.deptId)= :deptId AND g.status = :status  AND s.totalCost > 25000 AND s.totalCost <= 50000 order by g.id.estimateNo");//BETWEEN 15 and 19
			query = getEntityManager(webAppName).createQuery("SELECT TRIM(g.id.estimateNo) FROM Pcesthtt g , Application a WHERE  TRIM(g.id.estimateNo)=a.applicationNo AND a.applicationType=:applicationType AND a.applicationSubType=:applicationSubType  AND trim(g.id.deptId)= :deptId AND g.status = :status  order by g.id.estimateNo");
	    }else if (authorityLevel.equals("AE") ||authorityLevel.equals("EE") ){
	    	//query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthtt g, Speststd s WHERE TRIM(g.id.estimateNo)=s.id.estimateNo AND g.id.deptId=s.id.deptId AND trim(g.id.deptId)= :deptId AND g.status = :status  AND s.totalCost > 50000 AND s.totalCost <= 100000 order by g.id.estimateNo");
	    	query = getEntityManager(webAppName).createQuery("SELECT TRIM(g.id.estimateNo) FROM Pcesthtt g , Application a WHERE  TRIM(g.id.estimateNo)=a.applicationNo AND a.applicationType=:applicationType AND a.applicationSubType=:applicationSubType  AND trim(g.id.deptId)= :deptId AND g.status = :status  order by g.id.estimateNo");
	    }else if (authorityLevel.equals("DGM")){
	    	//query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthtt g, Speststd s WHERE TRIM(g.id.estimateNo)=s.id.estimateNo AND g.id.deptId=s.id.deptId AND trim(g.id.deptId)= :deptId AND g.status = :status  AND s.totalCost > 100000 AND s.totalCost <= 200000 order by g.id.estimateNo");
	    	query = getEntityManager(webAppName).createQuery("SELECT TRIM(g.id.estimateNo) FROM Pcesthtt g , Application a WHERE  TRIM(g.id.estimateNo)=a.applicationNo AND a.applicationType=:applicationType AND a.applicationSubType=:applicationSubType  AND trim(g.id.deptId)= :deptId AND g.status = :status  order by g.id.estimateNo");
	    }else if (authorityLevel.equals("AGM")){
	    	//query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthtt g, Speststd s WHERE TRIM(g.id.estimateNo)=s.id.estimateNo AND g.id.deptId=s.id.deptId AND trim(g.id.deptId)= :deptId AND g.status = :status  AND 200000 < s.totalCost order by g.id.estimateNo");
	    	query = getEntityManager(webAppName).createQuery("SELECT TRIM(g.id.estimateNo) FROM Pcesthtt g , Application a WHERE  TRIM(g.id.estimateNo)=a.applicationNo AND a.applicationType=:applicationType AND a.applicationSubType=:applicationSubType  AND  trim(g.id.deptId)= :deptId AND g.status = :status  order by g.id.estimateNo");
	    }
		query.setParameter("applicationType", applicationType);
		query.setParameter("deptId", deptId);
		query.setParameter("status", status);
		query.setParameter("applicationSubType", applicationSubType);
		List<String> list = query.getResultList();
		return list;
	}*/
	
	@SuppressWarnings("unchecked")
	@Override
	public List<String> getEstNoApprovalListNewST(String deptId,	String authorityLevel, String applicationType, String applicationSubType, String webAppName) {
		//getEntityManager(webAppName);
		Query query=null;
		BigDecimal status = null;
		if (authorityLevel.equals("ES")){
			status=new BigDecimal(EstimateStatus.EST_APPROVAL_ES); 
	    }else if (authorityLevel.equals("EA")){
	    	status=new BigDecimal(EstimateStatus.EST_APPROVAL_EA); 
	    }else if (authorityLevel.equals("AE") ||authorityLevel.equals("EE") ){
	    	status=new BigDecimal(EstimateStatus.EST_APPROVAL_EE); 
	    }else if (authorityLevel.equals("CE") ){
	    	status=new BigDecimal(EstimateStatus.EST_APPROVAL_CE); 
	    }else if (authorityLevel.equals("DGM")){
	    	status=new BigDecimal(EstimateStatus.EST_APPROVAL_DGM); 
	    }else if (authorityLevel.equals("AGM")){
	    	status=new BigDecimal(EstimateStatus.EST_APPROVAL_AGM); 
	    }
		if (authorityLevel.equals("ES")){
			//query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthtt g, Speststd s WHERE TRIM(g.id.estimateNo)=s.id.estimateNo AND g.id.deptId=s.id.deptId AND trim(g.id.deptId)= :deptId AND g.status = :status  AND s.totalCost <= 25000  order by g.id.estimateNo");
			query = getEntityManager(webAppName).createQuery("SELECT TRIM(g.id.estimateNo) FROM Pcesthtt g , Application a WHERE  trim(g.id.deptId)= :deptId AND g.status = :status AND TRIM(g.id.estimateNo)=a.applicationNo AND a.applicationType=:applicationType AND a.applicationSubType=:applicationSubType order by g.id.estimateNo");
		}else if (authorityLevel.equals("EA")){
	    	//query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthtt g, Speststd s WHERE TRIM(g.id.estimateNo)=s.id.estimateNo AND g.id.deptId=s.id.deptId AND trim(g.id.deptId)= :deptId AND g.status = :status  AND s.totalCost > 25000 AND s.totalCost <= 50000 order by g.id.estimateNo");//BETWEEN 15 and 19
			query = getEntityManager(webAppName).createQuery("SELECT TRIM(g.id.estimateNo) FROM Pcesthtt g , Application a WHERE  trim(g.id.deptId)= :deptId AND g.status = :status AND TRIM(g.id.estimateNo)=a.applicationNo AND a.applicationType=:applicationType AND a.applicationSubType=:applicationSubType order by g.id.estimateNo");
	    }else if (authorityLevel.equals("AE") ||authorityLevel.equals("EE") ){
	    	//query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthtt g, Speststd s WHERE TRIM(g.id.estimateNo)=s.id.estimateNo AND g.id.deptId=s.id.deptId AND trim(g.id.deptId)= :deptId AND g.status = :status  AND s.totalCost > 50000 AND s.totalCost <= 100000 order by g.id.estimateNo");
	    	query = getEntityManager(webAppName).createQuery("SELECT TRIM(g.id.estimateNo) FROM Pcesthtt g , Application a WHERE  trim(g.id.deptId)= :deptId AND g.status = :status AND TRIM(g.id.estimateNo)=a.applicationNo AND a.applicationType=:applicationType AND a.applicationSubType=:applicationSubType order by g.id.estimateNo");
	    }else if (authorityLevel.equals("CE") ){
	    	//query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthtt g, Speststd s WHERE TRIM(g.id.estimateNo)=s.id.estimateNo AND g.id.deptId=s.id.deptId AND trim(g.id.deptId)= :deptId AND g.status = :status  AND s.totalCost > 50000 AND s.totalCost <= 100000 order by g.id.estimateNo");
	    	query = getEntityManager(webAppName).createQuery("SELECT TRIM(g.id.estimateNo) FROM Pcesthtt g , Application a WHERE  trim(g.id.deptId)= :deptId AND g.status = :status AND TRIM(g.id.estimateNo)=a.applicationNo AND a.applicationType=:applicationType AND a.applicationSubType=:applicationSubType order by g.id.estimateNo");
	    }else if (authorityLevel.equals("DGM")){
	    	//query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthtt g, Speststd s WHERE TRIM(g.id.estimateNo)=s.id.estimateNo AND g.id.deptId=s.id.deptId AND trim(g.id.deptId)= :deptId AND g.status = :status  AND s.totalCost > 100000 AND s.totalCost <= 200000 order by g.id.estimateNo");
	    	query = getEntityManager(webAppName).createQuery("SELECT TRIM(g.id.estimateNo) FROM Pcesthtt g , Application a WHERE  trim(g.id.deptId)= :deptId AND g.status = :status AND TRIM(g.id.estimateNo)=a.applicationNo AND a.applicationType=:applicationType AND a.applicationSubType=:applicationSubType order by g.id.estimateNo");
	    }else if (authorityLevel.equals("AGM")){
	    	//query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthtt g, Speststd s WHERE TRIM(g.id.estimateNo)=s.id.estimateNo AND g.id.deptId=s.id.deptId AND trim(g.id.deptId)= :deptId AND g.status = :status  AND 200000 < s.totalCost order by g.id.estimateNo");
	    	query = getEntityManager(webAppName).createQuery("SELECT TRIM(g.id.estimateNo) FROM Pcesthtt g , Application a WHERE  trim(g.id.deptId)= :deptId AND g.status = :status AND TRIM(g.id.estimateNo)=a.applicationNo AND a.applicationType=:applicationType AND a.applicationSubType=:applicationSubType order by g.id.estimateNo");
	    }
		
		query.setParameter("deptId", deptId);
		query.setParameter("status", status);
		query.setParameter("applicationType", applicationType);
		query.setParameter("applicationSubType", applicationSubType);
		List<String> list = query.getResultList();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Pcesthtt> getEstApprovalListNew(String deptId,	String authorityLevel, String webAppName) {
		//getEntityManager(webAppName);
		Query query=null;
		BigDecimal status = null;
		if (authorityLevel.equals("ES")){
			status=new BigDecimal(EstimateStatus.EST_APPROVAL_ES); 
	    }else if (authorityLevel.equals("EA")){
	    	status=new BigDecimal(EstimateStatus.EST_APPROVAL_EA); 
	    }else if (authorityLevel.equals("AE") ||authorityLevel.equals("EE") ){
	    	status=new BigDecimal(EstimateStatus.EST_APPROVAL_EE); 
	    }else if (authorityLevel.equals("CE") ){
	    	status=new BigDecimal(EstimateStatus.EST_APPROVAL_CE); 
	    }else if (authorityLevel.equals("DGM")){
	    	status=new BigDecimal(EstimateStatus.EST_APPROVAL_DGM); 
	    }else if (authorityLevel.equals("AGM")){
	    	status=new BigDecimal(EstimateStatus.EST_APPROVAL_AGM); 
	    }
		if (authorityLevel.equals("ES")){
			//query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthtt g, Speststd s WHERE TRIM(g.id.estimateNo)=s.id.estimateNo AND g.id.deptId=s.id.deptId AND trim(g.id.deptId)= :deptId AND g.status = :status  AND s.totalCost <= 25000  order by g.id.estimateNo");
			query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthtt g WHERE trim(g.id.deptId)= :deptId AND g.status = :status  order by g.id.estimateNo");
		}else if (authorityLevel.equals("EA")){
	    	//query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthtt g, Speststd s WHERE TRIM(g.id.estimateNo)=s.id.estimateNo AND g.id.deptId=s.id.deptId AND trim(g.id.deptId)= :deptId AND g.status = :status  AND s.totalCost > 25000 AND s.totalCost <= 50000 order by g.id.estimateNo");//BETWEEN 15 and 19
			query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthtt g WHERE trim(g.id.deptId)= :deptId AND g.status = :status  order by g.id.estimateNo");
	    }else if (authorityLevel.equals("AE") ||authorityLevel.equals("EE") ){
	    	//query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthtt g, Speststd s WHERE TRIM(g.id.estimateNo)=s.id.estimateNo AND g.id.deptId=s.id.deptId AND trim(g.id.deptId)= :deptId AND g.status = :status  AND s.totalCost > 50000 AND s.totalCost <= 100000 order by g.id.estimateNo");
	    	query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthtt g WHERE trim(g.id.deptId)= :deptId AND g.status = :status  order by g.id.estimateNo");
	    }else if (authorityLevel.equals("CE")){
	    	//query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthtt g, Speststd s WHERE TRIM(g.id.estimateNo)=s.id.estimateNo AND g.id.deptId=s.id.deptId AND trim(g.id.deptId)= :deptId AND g.status = :status  AND s.totalCost > 50000 AND s.totalCost <= 100000 order by g.id.estimateNo");
	    	query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthtt g WHERE trim(g.id.deptId)= :deptId AND g.status = :status  order by g.id.estimateNo");	
	    }else if (authorityLevel.equals("DGM")){
	    	//query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthtt g, Speststd s WHERE TRIM(g.id.estimateNo)=s.id.estimateNo AND g.id.deptId=s.id.deptId AND trim(g.id.deptId)= :deptId AND g.status = :status  AND s.totalCost > 100000 AND s.totalCost <= 200000 order by g.id.estimateNo");
	    	query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthtt g WHERE trim(g.id.deptId)= :deptId AND g.status = :status  order by g.id.estimateNo");
	    }else if (authorityLevel.equals("AGM")){
	    	//query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthtt g, Speststd s WHERE TRIM(g.id.estimateNo)=s.id.estimateNo AND g.id.deptId=s.id.deptId AND trim(g.id.deptId)= :deptId AND g.status = :status  AND 200000 < s.totalCost order by g.id.estimateNo");
	    	query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthtt g WHERE trim(g.id.deptId)= :deptId AND g.status = :status  order by g.id.estimateNo");
	    }
		query.setParameter("deptId", deptId);
		query.setParameter("status", status);
		List<Pcesthtt> list = query.getResultList();
		return list;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override 
	public List<String> getApprovedEst(String deptId, String applicationType, String approvedBy, String toStatus,String status,String webAppName){
		//getEntityManager(webAppName);
		//return getEntityManager(webAppName).find(Pcesthtt.class, id);
		List<String> listCSC=securityBeanRemote.getAuthorizedCostCenters(approvedBy, webAppName) ;
		String csc=securityBeanRemote.getCostCenter(approvedBy, webAppName);
		if (listCSC.contains(csc)){
			
		}else{
			listCSC.add(csc);
		}
		List<String> listNo=null;
		//List<String> 
		
		listNo= pivDetailDaoRemote.getNewPivNos(deptId, "EST", "N", webAppName);
		System.out.println("1 "+listNo);
		System.out.println("1 "+listNo.isEmpty());
		System.out.println("1 "+listNo==null);
		
		if (listNo.isEmpty()){
			System.out.println("33 "+listNo);
			if (!listCSC.isEmpty()){
				System.out.println("44 "+listNo);
				for(int i=0; i<=listCSC.size()-1; i++){
					//pcestdttDaoRemote.createPcestdtt(list.get(i), webAppName);
					List<String> listNo2= pivDetailDaoRemote.getNewPivNos(listCSC.get(i), "EST", "N", webAppName);
					System.out.println("22 "+listNo2);
					if (listNo2.size()!=0){
						listNo.addAll(listNo2);
					}
				}
			}
		}
			
		
		//String qryStr = "SELECT g FROM Pcesthtt g WHERE TRIM(g.id.estimateNo) = :estimateNo AND g.id.deptId = :deptId AND g.id.revNo = :revNo";
		System.out.println("2 "+listNo.size());
		if (listNo.size()!=0){
			String qryStr = "SELECT  DISTINCT b.id.estimateNo  FROM Approval a,  Pcesthtt b, Application c WHERE a.referenceNo=TRIM(b.id.estimateNo) AND c.applicationNo=TRIM(b.id.estimateNo) AND a.approvedBy=:approvedBy AND a.toStatus=:toStatus AND b.status=:status AND b.projectNo is null AND b.id.estimateNo Not in (:listNo) AND c.applicationType=:applicationType order by  b.id.estimateNo  ";
			Query query = getEntityManager(webAppName).createQuery(qryStr);
			query.setParameter("approvedBy", approvedBy);
			//query.setParameter("deptId", deptId);
			query.setParameter("toStatus", toStatus);
			query.setParameter("status", new BigDecimal(status));
			query.setParameter("listNo", listNo);
			query.setParameter("applicationType", applicationType);
			List<String> list = query.getResultList();		
			return list;
		}else{
			String qryStr = "SELECT  DISTINCT b.id.estimateNo  FROM Approval a,  Pcesthtt b, Application c WHERE a.referenceNo=TRIM(b.id.estimateNo) AND c.applicationNo=TRIM(b.id.estimateNo) AND a.approvedBy=:approvedBy AND a.toStatus=:toStatus AND b.status=:status AND b.projectNo is null AND c.applicationType=:applicationType order by  b.id.estimateNo  ";
			Query query = getEntityManager(webAppName).createQuery(qryStr);
			query.setParameter("approvedBy", approvedBy);
			//query.setParameter("deptId", deptId);
			query.setParameter("toStatus", toStatus);
			query.setParameter("status", new BigDecimal(status));
			//query.setParameter("listNo", listNo);
			query.setParameter("applicationType", applicationType);
			List<String> list = query.getResultList();		
			return list;
		}
        
		
	}
	
	
	@SuppressWarnings("unchecked")
	@Override 
	public List<String> getApprovedEstMA_SA(String deptId, String approvedBy, String toStatus,String status,String webAppName){
		//getEntityManager(webAppName);
		//return getEntityManager(webAppName).find(Pcesthtt.class, id);
		List<String> listCSC=securityBeanRemote.getAuthorizedCostCenters(approvedBy, webAppName) ;
		String csc=securityBeanRemote.getCostCenter(approvedBy, webAppName);
		if (listCSC.contains(csc)){
			
		}else{
			listCSC.add(csc);
		}
		List<String> listNo=null;
		//List<String> 
		
		listNo= pivDetailDaoRemote.getNewPivNos(deptId, "EST", "N", webAppName);
		System.out.println("1 "+listNo);
		System.out.println("1 "+listNo.isEmpty());
		System.out.println("1 "+listNo==null);
		
		if (listNo.isEmpty()){
			System.out.println("33 "+listNo);
			if (!listCSC.isEmpty()){
				System.out.println("44 "+listNo);
				for(int i=0; i<=listCSC.size()-1; i++){
					//pcestdttDaoRemote.createPcestdtt(list.get(i), webAppName);
					List<String> listNo2= pivDetailDaoRemote.getNewPivNos(listCSC.get(i), "EST", "N", webAppName);
					System.out.println("22 "+listNo2);
					if (listNo2.size()!=0){
						listNo.addAll(listNo2);
					}
				}
			}
		}
			
		
		//String qryStr = "SELECT g FROM Pcesthtt g WHERE TRIM(g.id.estimateNo) = :estimateNo AND g.id.deptId = :deptId AND g.id.revNo = :revNo";
		System.out.println("2 "+listNo.size());
		if (listNo.size()!=0){
			String qryStr = "SELECT  DISTINCT b.id.estimateNo  FROM Approval a,  Pcesthtt b, Application c WHERE a.referenceNo=TRIM(b.id.estimateNo) AND c.applicationNo=TRIM(b.id.estimateNo) AND a.approvedBy=:approvedBy AND a.toStatus=:toStatus AND b.status=:status AND b.projectNo is null AND b.id.estimateNo Not in (:listNo) AND (c.applicationType='MT' OR c.applicationType='LS' OR c.applicationType='PS' OR c.applicationType='SA' OR c.applicationType='BD' OR c.applicationType='CC'  OR c.applicationType='CS' OR c.applicationType= 'CO'  OR c.applicationType='TR'  OR c.applicationType='TP'  OR c.applicationType='MR' OR c.applicationType= 'PF' OR c.applicationType= 'MF' OR c.applicationType= 'DB' OR c.applicationType= 'RM') order by  b.id.estimateNo  ";
			Query query = getEntityManager(webAppName).createQuery(qryStr);
			query.setParameter("approvedBy", approvedBy);
			//query.setParameter("deptId", deptId);
			query.setParameter("toStatus", toStatus);
			query.setParameter("status", new BigDecimal(status));
			query.setParameter("listNo", listNo);
			//query.setParameter("applicationType", applicationType);
			List<String> list = query.getResultList();		
			return list;
		}else{
			String qryStr = "SELECT  DISTINCT b.id.estimateNo  FROM Approval a,  Pcesthtt b, Application c WHERE a.referenceNo=TRIM(b.id.estimateNo) AND c.applicationNo=TRIM(b.id.estimateNo) AND a.approvedBy=:approvedBy AND a.toStatus=:toStatus AND b.status=:status AND b.projectNo is null AND (c.applicationType='MT' OR c.applicationType='LS' OR c.applicationType='PS' OR c.applicationType='SA' OR c.applicationType='BD' OR c.applicationType='CC'  OR c.applicationType='CS' OR c.applicationType= 'CO'  OR c.applicationType='TR'  OR c.applicationType='TP'  OR c.applicationType='MR' OR c.applicationType= 'PF' OR c.applicationType= 'MF' OR c.applicationType= 'DB' OR c.applicationType= 'RM') order by  b.id.estimateNo  ";
			Query query = getEntityManager(webAppName).createQuery(qryStr);
			query.setParameter("approvedBy", approvedBy);
			//query.setParameter("deptId", deptId);
			query.setParameter("toStatus", toStatus);
			query.setParameter("status", new BigDecimal(status));
			//query.setParameter("listNo", listNo);
			//query.setParameter("applicationType", applicationType);
			List<String> list = query.getResultList();		
			return list;
		}
        
		
	}
	
	@Override
	public int updateEstimateDetails(String estimateNo,String deptId,Long status, String rejectedBy ,Date rejectedDate,String approvedByEE ,Date approvedEEDate,String approvedByCE ,Date approvedCEDate,String approvedByDGM ,Date approvedDGMDate,String rejectedReason, String webAppName) {
		//getEntityManager(webAppName);
		//System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		//String qryStr = "UPDATE Pcesthtt g set g.status=:status WHERE TRIM(g.id.estimateNo)= :estimateNo AND g.id.deptId = :deptId";
		
		StringBuffer qryStr = new StringBuffer();
		qryStr.append("UPDATE Pcesthtt g set g.status=:status ");
			
			if(rejectedBy != null){
				qryStr.append(",g.rejctUid=:rejectedBy ");
			}
			if(rejectedDate != null){
				qryStr.append(",g.rejctDt=:rejectedDate ");
			}
			if(rejectedReason != null){
				qryStr.append(",g.rejectReason=:rejectReason ");
			}
			
			if(approvedByEE != null){
				qryStr.append(",g.aprUid1=:approvedByEE ");
			}
			if(approvedEEDate != null){
				qryStr.append(",g.aprDt1=:approvedEEDate ");
			}
			if(approvedByCE != null){
				qryStr.append(",g.aprUid2=:approvedByCE ");
			}
			if(approvedCEDate != null){
				qryStr.append(",g.aprDt2=:approvedCEDate ");
			}
			if(approvedByDGM != null){
				qryStr.append(",g.aprUid3=:approvedByDGM ");
			}
			if(approvedDGMDate != null){
				qryStr.append(",g.aprDt3=:approvedDGMDate ");
			}
			qryStr.append(" WHERE TRIM(g.id.estimateNo)= :estimateNo AND g.id.deptId = :deptId ");
			 
		Query query = getEntityManager(webAppName).createQuery(qryStr.toString());
		query.setParameter("deptId", deptId);
		query.setParameter("estimateNo", estimateNo.trim());
		query.setParameter("status", status);
		
		if(rejectedBy != null){
			query.setParameter("rejectedBy", rejectedBy);
			
		}
		if(rejectedDate != null){
			query.setParameter("rejectedDate", rejectedDate);
		
		}
		if(rejectedReason != null){
			query.setParameter("rejectReason",rejectedBy +" : "+rejectedReason);
		}
		if(approvedByEE != null){
			query.setParameter("approvedByEE", approvedByEE);
		}
		if(approvedEEDate != null){
			query.setParameter("approvedEEDate", approvedEEDate);
		}
		if(approvedByCE != null){
			query.setParameter("approvedByCE", approvedByCE);
		}
		if(approvedCEDate != null){
			query.setParameter("approvedCEDate", approvedCEDate);
		}
		if(approvedByDGM != null){
			query.setParameter("approvedByDGM", approvedByDGM);
		}
		if(approvedDGMDate != null){
			query.setParameter("approvedDGMDate", approvedDGMDate);
		}
		
		int updateStatus = query.executeUpdate();
		return updateStatus;
	}
	
	@Override 
	public void cancelApproval(String estimateNo, String deptId, Approval approval, String webAppName ){
		changeStatusPcesthtt(estimateNo, deptId, new Long(EstimateStatus.MODIFIED), webAppName);
		approvalDaoRemote.createAutoIdApprovals(approval, webAppName);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Pcesthtt> getEstimateList(String deptId, Long revNo, List<Long> status,String userId, String webAppName) {
		//getEntityManager(webAppName);
		//Query query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthtt g WHERE trim(g.id.deptId)= :deptId AND g.id.revNo= :revNo AND g.status = :status AND g.entBy = :entBy order by g.id.estimateNo");
		StringBuffer qryStr = new StringBuffer();
		qryStr.append("SELECT g FROM Pcesthtt g WHERE trim(g.id.deptId)= :deptId AND g.id.revNo= :revNo AND g.status in (:status) ");
		if(userId != null){ 
			qryStr.append(" AND g.entBy = :entBy ");
			 
		}
		qryStr.append(" order by g.id.estimateNo ");
		Query query = getEntityManager(webAppName).createQuery(qryStr.toString());
		query.setParameter("deptId", deptId);
		query.setParameter("revNo", revNo);
		query.setParameter("status", status);
		
		if(userId != null){ 
			query.setParameter("entBy", userId);
		}
		List<Pcesthtt> resultList = query.getResultList();
		return resultList;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Pcesthtt> getEstimateListForVSLAuthorizedCC(List<String> deptIds, Long revNo, List<Long> status,String userId, String webAppName) {
		//getEntityManager(webAppName);
		//Query query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthtt g WHERE trim(g.id.deptId)= :deptId AND g.id.revNo= :revNo AND g.status = :status AND g.entBy = :entBy order by g.id.estimateNo");
		StringBuffer qryStr = new StringBuffer();
		qryStr.append("SELECT g FROM Pcesthtt g WHERE trim(g.id.deptId) in (:deptId) AND g.id.revNo= :revNo AND g.status in (:status) AND g.id.estimateNo like '%SPS%' ");
		//if(userId != null){ 
		//	qryStr.append(" AND g.entBy = :entBy ");
			 
		//}
		qryStr.append(" order by g.id.estimateNo ");
		Query query = getEntityManager(webAppName).createQuery(qryStr.toString());
		query.setParameter("deptId", deptIds);
		query.setParameter("revNo", revNo);
		query.setParameter("status", status);
		
		//if(userId != null){ 
		//	query.setParameter("entBy", userId);
		//}
		List<Pcesthtt> resultList = query.getResultList();
		return resultList;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Pcesthtt> getEstimateListForAuthorizedCC(List<String> deptIds, Long revNo, List<Long> status,String userId,String estimateLike ,String webAppName) {
		//getEntityManager(webAppName);
		//Query query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthtt g WHERE trim(g.id.deptId)= :deptId AND g.id.revNo= :revNo AND g.status = :status AND g.entBy = :entBy order by g.id.estimateNo");
		StringBuffer qryStr = new StringBuffer();
		qryStr.append("SELECT g FROM Pcesthtt g WHERE trim(g.id.deptId) in (:deptId) AND g.id.revNo= :revNo AND g.status in (:status) ");
		if(estimateLike != null){ 
			qryStr.append(" AND g.id.estimateNo like '%SPS%' ");
			 
		}else{
			qryStr.append(" AND g.id.estimateNo not like '%SPS%' ");
		}
		qryStr.append(" order by g.id.estimateNo ");
		Query query = getEntityManager(webAppName).createQuery(qryStr.toString());
		query.setParameter("deptId", deptIds);
		query.setParameter("revNo", revNo);
		query.setParameter("status", status);
		
		//if(userId != null){ 
		//	query.setParameter("entBy", userId);
		//}
		List<Pcesthtt> resultList = query.getResultList();
		return resultList;
	}
	@SuppressWarnings("unchecked")
	@Override
	public String getNextEstimateNo(String estimateNoPrefix,String deptId, String webAppName) {
		//getEntityManager(webAppName);
		String sequence=null;
    	String like=estimateNoPrefix+"%";
    	String strQuery="select trim(htt.id.estimateNo) from Pcesthtt htt where TRIM(htt.id.estimateNo) like :like and (htt.id.deptId)=:deptId  order by htt.id.estimateNo DESC";
    	Query query=getEntityManager(webAppName).createQuery(strQuery);//SELECT MIS.TEST4_SEQ.NEXTVAL() FROM DUAL
    	query.setParameter("deptId", deptId);
		query.setParameter("like", like);
    	List<String> list=query.getResultList();
    	if (list.size()!=0){
    		sequence=query.getResultList().get(0).toString().trim();
    		System.out.println(sequence);
    		//sequence=sequence.substring(16);//total 20
    		sequence=sequence.substring(sequence.length()-4);//total 18
    		Integer i=Integer.parseInt(sequence)+1;
    		sequence=i.toString();
    		System.out.println(sequence);
    	}else{
    		sequence="0001";
    		System.out.println(sequence);
    	}
    	if(sequence.length()==1)
    		return "000"+sequence;
    	else if (sequence.length()==2)
    		return "00"+sequence;
    	else if (sequence.length()==3)
    		return "0"+sequence;
    	else return sequence;
	}
	
	
	@SuppressWarnings("rawtypes")
	@Override
	public long getMaxHttRevNo(String estimateNo,String deptId, String webAppName) {
		//getEntityManager(webAppName);  
		long revNo = -1;
	      try
	      {
	            String qryStr = " select max(p.id.revNo) from Pcesthtt p where trim(p.id.deptId) = :deptId " +
	                                    " and trim(p.id.estimateNo) = :estimateNo ";
	            
	            Query query = getEntityManager(webAppName).createQuery(qryStr);
	            query.setParameter("estimateNo", estimateNo.trim());
	            query.setParameter("deptId", deptId);
	              
	              List list = query.getResultList();
	              if(list.get(0)!=null )
	              {
	                  revNo = (Long)list.get(0);
	              }
	      }
	      catch(Exception e){
	            
	            e.printStackTrace();
	      }
	      return revNo;
	    }

}
