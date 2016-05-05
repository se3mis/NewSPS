package job.ejb;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import application.ejb.ApplicationDaoRemote;
import application.ejb.ApplicationReferenceDaoRemote;
import application.model.Application;
import application.model.ApplicationReference;

import estimate.ejb.ApprovalDaoRemote;
import estimate.ejb.EstimateReferenceDaoRemote;
import estimate.ejb.PcesthttDaoRemote;
import estimate.ejb.SpeststdDaoRemote;
import estimate.model.Appestlim;
import estimate.model.Approval;
import estimate.model.EstimateReference;
import estimate.model.Pcesthtt;
import estimate.model.SpeststdPK;
import estimate.model.Spstdesthmt;
import estimate.model.SpstdesthmtPK;

import security.ejb.SecurityBeanRemote;
import util.common.EstimateStatus;
import util.common.Format;
import util.emSelect.EmSelector;

import job.model.Pcesthmt;
import job.model.PcesthmtPK;


/**
 * Session Bean implementation class PcesthmtDao
 */
@TransactionManagement(TransactionManagementType.CONTAINER)
@Stateless
public class PcesthmtDao extends EmSelector implements PcesthmtDaoRemote, PcesthmtDaoLocal {
	@Resource
	private SessionContext context;
	@EJB
	PcesthttDaoRemote pcesthttDaoRemote;
	@EJB
	ApprovalDaoRemote approvalDaoRemote;
	@EJB
	SpeststdDaoRemote speststdDaoRemote;
	@EJB
	SecurityBeanRemote securityBeanRemote;
	@EJB
	EstimateReferenceDaoRemote estimateReferenceDaoRemote;
	
	@EJB
	ApplicationReferenceDaoRemote applicationReferenceDaoRemote;
	
	@EJB
	ApplicationDaoRemote applicationDaoRemote;
    /**
     * Default constructor. 
     */
    public PcesthmtDao() { 
        // TODO Auto-generated constructor stub
    }

    
    
	@Override
	public void createPcesthmt(Pcesthmt pcesthmt, String webAppName) {
		//getEntityManager(webAppName);
		getEntityManager(webAppName).persist(pcesthmt);
		
	}

	@Override
	public void updatePcesthmt(Pcesthmt pcesthmt, String webAppName) {
		//getEntityManager(webAppName);
		getEntityManager(webAppName).merge(pcesthmt);
		
	}
	
	@Override
	public void updateRevNo(Pcesthmt pcesthmt, Long newRevNo, String webAppName) {
		//getEntityManager(webAppName);
		String qryString="UPDATE Pcesthmt a  SET a.id.revNo =:newRevNo WHERE TRIM(a.id.estimateNo)=:estimateNo AND a.id.deptId =:deptId AND a.id.revNo =:revNo";
		Query query = getEntityManager(webAppName).createQuery(qryString);
		query.setParameter("newRevNo", newRevNo);
		query.setParameter("estimateNo", pcesthmt.getId().getEstimateNo().trim());
		query.setParameter("deptId", pcesthmt.getId().getDeptId());
		query.setParameter("revNo", pcesthmt.getId().getRevNo());
        query.executeUpdate();
				
	}
	
	//@Override
	public void updatePcesthmt(Pcesthmt pcestdmt, BigDecimal status, String jobNo, String deptId, String webAppName) {
		//getEntityManager(webAppName);
		String qryStr = "UPDATE Pcesthmt hm SET hm.status =:status WHERE TRIM(hm.projectNo) =:jobNo AND hm.id.deptId =:deptId";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("jobNo", jobNo.trim());
		query.setParameter("deptId", deptId);
		query.executeUpdate();
				
	}

	@Override
	public void removePcesthmt(Pcesthmt pcesthmt, String webAppName) {
		//getEntityManager(webAppName);
		pcesthmt=getEntityManager(webAppName).merge(pcesthmt);
		getEntityManager(webAppName).remove(pcesthmt);
		
	}

	@Override
	public void removeAll(String webAppName) {
		//getEntityManager(webAppName);
		throw new UnsupportedOperationException("Not supported yet. Ask Dileepa");
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Pcesthmt> getAll(String webAppName) {
		//getEntityManager(webAppName);
		String qryStr = "select a from Pcesthmt a"; 
		Query query = getEntityManager(webAppName).createQuery(qryStr);
        List<Pcesthmt> list = query.getResultList();
        return list;

	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Pcesthmt> getAll(String deptId, String webAppName) {
		//getEntityManager(webAppName);
		String qryStr = "select a from Pcesthmt a WHERE a.id.deptId =:deptId";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("deptId", deptId);
		List<Pcesthmt> list = query.getResultList();
        return list;

	}

	@SuppressWarnings("unchecked")
	@Override
	public Pcesthmt findById(PcesthmtPK id, String webAppName) throws PersistenceException {
		//getEntityManager(webAppName);
		//return getEntityManager(webAppName).find(Pcesthmt.class, id);
		String qryStr = "SELECT g FROM Pcesthmt g WHERE TRIM(g.id.estimateNo) = :estimateNo AND g.id.deptId = :deptId AND g.id.revNo=:revNo";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("estimateNo", id.getEstimateNo().trim());
		query.setParameter("deptId", id.getDeptId());
		query.setParameter("revNo", id.getRevNo());
		List<Pcesthmt> list = query.getResultList();		
		if (list.isEmpty())
			return null;
        else if (list.size() == 1)
        	return list.get(0);
        throw new NonUniqueResultException();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Pcesthmt findByEstimationNo(String estimateNo, String deptId, String webAppName) {
		//getEntityManager(webAppName);
		String qryStr = "SELECT g FROM Pcesthmt g WHERE TRIM(g.id.estimateNo) = :estimateNo AND g.id.deptId = :deptId";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("estimateNo", estimateNo.trim());
		query.setParameter("deptId", deptId);
		List<Pcesthmt> list = query.getResultList();		
		if (list.isEmpty())
			return null;
        else if (list.size() == 1)
        	return list.get(0);
        throw new NonUniqueResultException();

	}
	
	@SuppressWarnings("unchecked") 
	@Override
	public Pcesthmt findByEstimationNo(String estimateNo, String webAppName) {
		//getEntityManager(webAppName);
		String qryStr = "SELECT g FROM Pcesthmt g WHERE TRIM(g.id.estimateNo) = :estimateNo ";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("estimateNo", estimateNo.trim());
		List<Pcesthmt> list = query.getResultList();		
		if (list.isEmpty())
			return null;
        else if (list.size() == 1)
        	return list.get(0);
        throw new NonUniqueResultException();

	}

	@SuppressWarnings("unchecked")
	@Override
	public Pcesthmt findByJobNo(String jobNo, String deptId, String webAppName) {
		//getEntityManager(webAppName);
		String qryStr = "SELECT g FROM Pcesthmt g WHERE TRIM(g.projectNo) = :jobNo AND g.id.deptId = :deptId";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("jobNo", jobNo.trim());
		query.setParameter("deptId", deptId);
		List<Pcesthmt> list = query.getResultList();		
		if (list.isEmpty())
			return null;
        else if (list.size() == 1)
        	return list.get(0);
        throw new NonUniqueResultException();

	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Pcesthmt findByJobNo(String jobNo, String webAppName) {
		//getEntityManager(webAppName);
		String qryStr = "SELECT g FROM Pcesthmt g WHERE TRIM(projectNo) = :jobNo ";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("jobNo", jobNo.trim());
		List<Pcesthmt> list = query.getResultList();		
		if (list.isEmpty())
			return null;
        else if (list.size() == 1)
        	return list.get(0);
        throw new NonUniqueResultException();

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> findJobNoList(String deptId, Long status, String webAppName) {
		//getEntityManager(webAppName);
		String qryStr = "SELECT TRIM(projectNo) FROM Pcesthmt g WHERE g.id.deptId = :deptId AND status = :status";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("deptId", deptId);
		query.setParameter("status", status);
		List<String> list = query.getResultList();		
		return list;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<String> findJobNosList(String deptId, List<Long> status, String webAppName) {
		//getEntityManager(webAppName);
		String qryStr = "SELECT TRIM(projectNo) FROM Pcesthmt g WHERE g.id.deptId = :deptId AND status in (:status)";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("deptId", deptId);
		query.setParameter("status", status);
		List<String> list = query.getResultList();		
		return list;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<String> findEstNosList(String deptId, List<Long> status, String webAppName) {
		//getEntityManager(webAppName);
		String qryStr = "SELECT TRIM(g.id.estimateNo) FROM Pcesthmt g WHERE g.id.deptId = :deptId AND status in (:status) order by g.id.estimateNo";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("deptId", deptId);
		query.setParameter("status", status);
		List<String> list = query.getResultList();		
		return list;
	}
	
	


	@SuppressWarnings("unchecked")
		@Override
		public List<Pcesthmt> getJobApprovalList(String deptId, Long status,	String value, String webAppName) {
			//getEntityManager(webAppName);	
			Query query=null;
			if (value.equals("ES")){
				//query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthmt g WHERE trim(g.id.deptId)= :deptId AND g.status = :status  AND stdCost <= 25000  order by g.id.estimateNo");
				return null;
		    }else if (value.equals("EA")){
		    	//query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthmt g WHERE trim(g.id.deptId)= :deptId AND g.status = :status  AND g.stdCost > 25000 AND g.stdCost <= 50000 order by g.id.estimateNo");//BETWEEN 15 and 19
		    	query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthmt g WHERE trim(g.id.deptId)= :deptId AND g.status = :status  AND g.stdCost > 0 AND g.stdCost <= 50000 order by g.id.estimateNo");//BETWEEN 15 and 19
		    	//query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthmt g WHERE trim(g.id.deptId)= :deptId AND g.status = :status  AND stdCost BETWEEN 25000 and 50000");
		    }else if (value.equals("AE") ||value.equals("EE") ){
		    	query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthmt g WHERE trim(g.id.deptId)= :deptId AND g.status = :status  AND stdCost > 50000 AND stdCost <= 100000 order by g.id.estimateNo");
		    }else if (value.equals("DGM")){
		    	query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthmt g WHERE trim(g.id.deptId)= :deptId AND g.status = :status  AND stdCost > 100000 AND stdCost <= 200000 order by g.id.estimateNo");
		    }else if (value.equals("AGM")){
		    	query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthmt g WHERE trim(g.id.deptId)= :deptId AND g.status = :status  AND 200000 < stdCost order by g.id.estimateNo");
		    }
			query.setParameter("deptId", deptId);
			query.setParameter("status", status);
			List<Pcesthmt> list = query.getResultList();
			return list;
		}
	@SuppressWarnings("unchecked")
	@Override
	public List<String> getJobNoApprovalListNew(String deptId,	String authorityLevel, String webAppName) {
		//getEntityManager(webAppName);
		Query query=null;
		BigDecimal status = null;
		if (authorityLevel.equals("ES")){
			status=new BigDecimal(55); 
	    }else if (authorityLevel.equals("EA")){
	    	status=new BigDecimal(56); 
	    }else if (authorityLevel.equals("AE") ||authorityLevel.equals("EE") ){
	    	status=new BigDecimal(57); 
	    }else if (authorityLevel.equals("CE") ){
	    	status=new BigDecimal(61); 	
	    }else if (authorityLevel.equals("DGM")){
	    	status=new BigDecimal(58); 
	    }else if (authorityLevel.equals("AGM")){
	    	status=new BigDecimal(59); 
	    }
		if (authorityLevel.equals("ES")){
			//query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthtt g, Speststd s WHERE g.id.estimateNo=s.id.estimateNo AND g.id.deptId=s.id.deptId AND trim(g.id.deptId)= :deptId AND g.status = :status  AND s.totalCost <= 25000  order by g.id.estimateNo");
			query = getEntityManager(webAppName).createQuery("SELECT TRIM(g.projectNo) FROM Pcesthmt g WHERE trim(g.id.deptId)= :deptId AND g.status = :status  order by g.id.estimateNo");
		}else if (authorityLevel.equals("EA")){
	    	//query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthtt g, Speststd s WHERE g.id.estimateNo=s.id.estimateNo AND g.id.deptId=s.id.deptId AND trim(g.id.deptId)= :deptId AND g.status = :status  AND s.totalCost > 25000 AND s.totalCost <= 50000 order by g.id.estimateNo");//BETWEEN 15 and 19
			query = getEntityManager(webAppName).createQuery("SELECT TRIM(g.projectNo) FROM Pcesthmt g WHERE trim(g.id.deptId)= :deptId AND g.status = :status  order by g.id.estimateNo");
	    }else if (authorityLevel.equals("AE") ||authorityLevel.equals("EE") ){
	    	//query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthtt g, Speststd s WHERE g.id.estimateNo=s.id.estimateNo AND g.id.deptId=s.id.deptId AND trim(g.id.deptId)= :deptId AND g.status = :status  AND s.totalCost > 50000 AND s.totalCost <= 100000 order by g.id.estimateNo");
	    	query = getEntityManager(webAppName).createQuery("SELECT TRIM(g.projectNo) FROM Pcesthmt g WHERE trim(g.id.deptId)= :deptId AND g.status = :status  order by g.id.estimateNo");
	    }else if (authorityLevel.equals("CE") ){
	    	//query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthtt g, Speststd s WHERE g.id.estimateNo=s.id.estimateNo AND g.id.deptId=s.id.deptId AND trim(g.id.deptId)= :deptId AND g.status = :status  AND s.totalCost > 50000 AND s.totalCost <= 100000 order by g.id.estimateNo");
	    	query = getEntityManager(webAppName).createQuery("SELECT TRIM(g.projectNo) FROM Pcesthmt g WHERE trim(g.id.deptId)= :deptId AND g.status = :status  order by g.id.estimateNo");
	    }else if (authorityLevel.equals("DGM")){
	    	//query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthtt g, Speststd s WHERE g.id.estimateNo=s.id.estimateNo AND g.id.deptId=s.id.deptId AND trim(g.id.deptId)= :deptId AND g.status = :status  AND s.totalCost > 100000 AND s.totalCost <= 200000 order by g.id.estimateNo");
	    	query = getEntityManager(webAppName).createQuery("SELECT TRIM(g.projectNo) FROM Pcesthmt g WHERE trim(g.id.deptId)= :deptId AND g.status = :status  order by g.id.estimateNo");
	    }else if (authorityLevel.equals("AGM")){
	    	//query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthtt g, Speststd s WHERE g.id.estimateNo=s.id.estimateNo AND g.id.deptId=s.id.deptId AND trim(g.id.deptId)= :deptId AND g.status = :status  AND 200000 < s.totalCost order by g.id.estimateNo");
	    	query = getEntityManager(webAppName).createQuery("SELECT TRIM(g.projectNo) FROM Pcesthmt g WHERE trim(g.id.deptId)= :deptId AND g.status = :status  order by g.id.estimateNo");
	    }
		query.setParameter("deptId", deptId);
		query.setParameter("status", status);
		List<String> list = query.getResultList();
		return list;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<String> getJobNoApprovalListNewST(String deptId,	String authorityLevel, String applicationType, String applicationSubType, String webAppName) {
		//getEntityManager(webAppName);
		Query query=null;
		BigDecimal status = null;
		if (authorityLevel.equals("ES")){
			status=new BigDecimal(EstimateStatus.JOB_APPROVAL_ES); 
	    }else if (authorityLevel.equals("EA")){
	    	status=new BigDecimal(EstimateStatus.JOB_APPROVAL_EA); 
	    }else if (authorityLevel.equals("AE") ||authorityLevel.equals("EE") ){
	    	status=new BigDecimal(EstimateStatus.JOB_APPROVAL_EE); 
	    }else if (authorityLevel.equals("CE") ){
	    	status=new BigDecimal(EstimateStatus.JOB_APPROVAL_CE); 
	    }else if (authorityLevel.equals("DGM")){
	    	status=new BigDecimal(EstimateStatus.JOB_APPROVAL_DGM); 
	    }else if (authorityLevel.equals("AGM")){
	    	status=new BigDecimal(EstimateStatus.JOB_APPROVAL_AGM); 
	    }
		if (authorityLevel.equals("ES")){
			//query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthtt g, Speststd s WHERE g.id.estimateNo=s.id.estimateNo AND g.id.deptId=s.id.deptId AND trim(g.id.deptId)= :deptId AND g.status = :status  AND s.totalCost <= 25000  order by g.id.estimateNo");
			query = getEntityManager(webAppName).createQuery("SELECT TRIM(g.projectNo) FROM Pcesthmt g , Application a WHERE  trim(g.id.deptId)= :deptId AND g.status = :status AND g.id.estimateNo=a.applicationNo AND a.applicationType=:applicationType AND a.applicationSubType=:applicationSubType order by g.id.estimateNo");
		}else if (authorityLevel.equals("EA")){
	    	//query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthmt g, Speststd s WHERE g.id.estimateNo=s.id.estimateNo AND g.id.deptId=s.id.deptId AND trim(g.id.deptId)= :deptId AND g.status = :status  AND s.totalCost > 25000 AND s.totalCost <= 50000 order by g.id.estimateNo");//BETWEEN 15 and 19
			query = getEntityManager(webAppName).createQuery("SELECT TRIM(g.projectNo) FROM Pcesthmt g , Application a WHERE  trim(g.id.deptId)= :deptId AND g.status = :status AND g.id.estimateNo=a.applicationNo AND a.applicationType=:applicationType AND a.applicationSubType=:applicationSubType order by g.id.estimateNo");
	    }else if (authorityLevel.equals("AE") ||authorityLevel.equals("EE") ){
	    	//query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthmt g, Speststd s WHERE g.id.estimateNo=s.id.estimateNo AND g.id.deptId=s.id.deptId AND trim(g.id.deptId)= :deptId AND g.status = :status  AND s.totalCost > 50000 AND s.totalCost <= 100000 order by g.id.estimateNo");
	    	query = getEntityManager(webAppName).createQuery("SELECT TRIM(g.projectNo) FROM Pcesthmt g , Application a WHERE  trim(g.id.deptId)= :deptId AND g.status = :status AND g.id.estimateNo=a.applicationNo AND a.applicationType=:applicationType AND a.applicationSubType=:applicationSubType order by g.id.estimateNo");
	    }else if (authorityLevel.equals("CE") ){
	    	//query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthmt g, Speststd s WHERE g.id.estimateNo=s.id.estimateNo AND g.id.deptId=s.id.deptId AND trim(g.id.deptId)= :deptId AND g.status = :status  AND s.totalCost > 50000 AND s.totalCost <= 100000 order by g.id.estimateNo");
	    	query = getEntityManager(webAppName).createQuery("SELECT TRIM(g.projectNo) FROM Pcesthmt g , Application a WHERE  trim(g.id.deptId)= :deptId AND g.status = :status AND g.id.estimateNo=a.applicationNo AND a.applicationType=:applicationType AND a.applicationSubType=:applicationSubType order by g.id.estimateNo");
	    }else if (authorityLevel.equals("DGM")){
	    	//query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthmt g, Speststd s WHERE g.id.estimateNo=s.id.estimateNo AND g.id.deptId=s.id.deptId AND trim(g.id.deptId)= :deptId AND g.status = :status  AND s.totalCost > 100000 AND s.totalCost <= 200000 order by g.id.estimateNo");
	    	query = getEntityManager(webAppName).createQuery("SELECT TRIM(g.projectNo) FROM Pcesthmt g , Application a WHERE  trim(g.id.deptId)= :deptId AND g.status = :status AND g.id.estimateNo=a.applicationNo AND a.applicationType=:applicationType AND a.applicationSubType=:applicationSubType order by g.id.estimateNo");
	    }else if (authorityLevel.equals("AGM")){
	    	//query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthmt g, Speststd s WHERE g.id.estimateNo=s.id.estimateNo AND g.id.deptId=s.id.deptId AND trim(g.id.deptId)= :deptId AND g.status = :status  AND 200000 < s.totalCost order by g.id.estimateNo");
	    	query = getEntityManager(webAppName).createQuery("SELECT TRIM(g.projectNo) FROM Pcesthmt g , Application a WHERE  trim(g.id.deptId)= :deptId AND g.status = :status AND g.id.estimateNo=a.applicationNo AND a.applicationType=:applicationType AND a.applicationSubType=:applicationSubType order by g.id.estimateNo");
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
	public List<Pcesthmt> getJobApprovalListNew(String deptId,	String authorityLevel, String webAppName) {
		//getEntityManager(webAppName);
		Query query=null;
		BigDecimal status = null;
		if (authorityLevel.equals("ES")){
			status=new BigDecimal(55); 
	    }else if (authorityLevel.equals("EA")){
	    	status=new BigDecimal(56); 
	    }else if (authorityLevel.equals("AE") ||authorityLevel.equals("EE") ){
	    	status=new BigDecimal(57); 
	    }else if (authorityLevel.equals("DGM")){
	    	status=new BigDecimal(58); 
	    }else if (authorityLevel.equals("AGM")){
	    	status=new BigDecimal(59); 
	    }
		if (authorityLevel.equals("ES")){
			//query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthmt g, Speststd s WHERE g.id.estimateNo=s.id.estimateNo AND g.id.deptId=s.id.deptId AND trim(g.id.deptId)= :deptId AND g.status = :status  AND s.totalCost <= 25000  order by g.id.estimateNo");
			query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthmt g WHERE trim(g.id.deptId)= :deptId AND g.status = :status  order by g.id.estimateNo");
		}else if (authorityLevel.equals("EA")){
	    	//query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthmt g, Speststd s WHERE g.id.estimateNo=s.id.estimateNo AND g.id.deptId=s.id.deptId AND trim(g.id.deptId)= :deptId AND g.status = :status  AND s.totalCost > 25000 AND s.totalCost <= 50000 order by g.id.estimateNo");//BETWEEN 15 and 19
			query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthmt g WHERE trim(g.id.deptId)= :deptId AND g.status = :status  order by g.id.estimateNo");
	    }else if (authorityLevel.equals("AE") ||authorityLevel.equals("EE") ){
	    	//query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthmt g, Speststd s WHERE g.id.estimateNo=s.id.estimateNo AND g.id.deptId=s.id.deptId AND trim(g.id.deptId)= :deptId AND g.status = :status  AND s.totalCost > 50000 AND s.totalCost <= 100000 order by g.id.estimateNo");
	    	query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthmt g WHERE trim(g.id.deptId)= :deptId AND g.status = :status  order by g.id.estimateNo");
	    }else if (authorityLevel.equals("DGM")){
	    	//query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthmt g, Speststd s WHERE g.id.estimateNo=s.id.estimateNo AND g.id.deptId=s.id.deptId AND trim(g.id.deptId)= :deptId AND g.status = :status  AND s.totalCost > 100000 AND s.totalCost <= 200000 order by g.id.estimateNo");
	    	query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthmt g WHERE trim(g.id.deptId)= :deptId AND g.status = :status  order by g.id.estimateNo");
	    }else if (authorityLevel.equals("AGM")){
	    	//query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthmt g, Speststd s WHERE g.id.estimateNo=s.id.estimateNo AND g.id.deptId=s.id.deptId AND trim(g.id.deptId)= :deptId AND g.status = :status  AND 200000 < s.totalCost order by g.id.estimateNo");
	    	query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthmt g WHERE trim(g.id.deptId)= :deptId AND g.status = :status  order by g.id.estimateNo");
	    }
		query.setParameter("deptId", deptId);
		query.setParameter("status", status);
		List<Pcesthmt> list = query.getResultList();
		return list;
	}
	
	
	@Override
	@SuppressWarnings({ "unchecked", "rawtypes"})
	public String approveJob(String jobNo, String deptId,	String authorityLevel, String userName,String appType, String webAppName) {
		//getEntityManager(webAppName);
		//Limits
		
		
		try{
			
			String applicaType  = null;
			if(deptId.equals("543.10") || deptId.equals("543.20") || deptId.equals("543.40")){
				applicaType = "GI";
			}else{
				ApplicationReference appRef = applicationReferenceDaoRemote.findByJobNo(jobNo,deptId, webAppName);
				if(appRef != null && appRef.getId().getApplicationId() != null){
					Application applica = applicationDaoRemote.findByApplicationId(appRef.getId().getApplicationId(), webAppName);
					if(applica != null){
						applicaType = applica.getApplicationType();
					}
				}
			}
				
			
			System.out.println("approveJob ");
			Appestlim appestlim=pcesthttDaoRemote.findAppEstLimits(deptId, "JOB",applicaType, authorityLevel, webAppName);
			if (appestlim!=null){
			
				Double limitFrom=Double.parseDouble(String.valueOf(appestlim.getId().getLimitFrom()));
				Double limitTo=Double.parseDouble(String.valueOf(appestlim.getId().getLimitTo()));
				String statusFrom=String.valueOf(appestlim.getId().getStatusFrom());
				String statusTo=String.valueOf(appestlim.getId().getStatusTo());
				System.out.println("appestlim "+appestlim);
				
				Query query=null;
				BigDecimal totalCost = null;
				String status = null;
				Double dbTotalCost=0.0;
				//query = getEntityManager(webAppName).createQuery("SELECT new map(p.status, s.totalCost) FROM Pcesthmt p, Speststd s  WHERE TRIM(p.id.estimateNo)=s.id.estimateNo AND p.id.deptId=s.id.deptId AND TRIM(p.projectNo)=:projectNo  AND TRIM(p.id.deptId)= :deptId");
				
				query = getEntityManager(webAppName).createQuery("SELECT new map(p.status, p.stdCost) FROM Pcesthmt p  WHERE TRIM(p.projectNo)=:projectNo  AND TRIM(p.id.deptId)= :deptId");
					
								
					
				query.setParameter("deptId", deptId);
				query.setParameter("projectNo", jobNo.trim());
				//query.setParameter("deptId", deptId);
				List<Map> maplist = query.getResultList();
				System.out.println("maplist "+maplist);
				Pcesthmt pcesthmt= findByJobNo(jobNo, deptId, webAppName);
				
				
				//Approvals
				Calendar calendar = Calendar.getInstance();
				Format format=new Format();
				Approval approval=new Approval();
				approval.setApprovalId(null);
				approval.setReferenceNo(jobNo);
				approval.setDeptId(deptId);
				
				approval.setApprovedLevel(authorityLevel);
				approval.setApprovedBy(userName);
				approval.setApprovedDate(calendar.getTime());
				approval.setApprovedTime(format.FormatTime());
				//
				SpeststdPK id=new SpeststdPK();
				id.setDeptId(deptId);
				id.setEstimateNo(pcesthmt.getId().getEstimateNo().trim());
				/*//approval.setStandardCost(speststdDaoRemote.findById(id, webAppName).getTotalCost());
				if(deptId.equals("547.00")||deptId.equals("547.10")||deptId.equals("547.20")|| deptId.equals("542.00") || deptId.equals("542.10")|| deptId.equals("542.20") || deptId.equals("541.00")|| deptId.equals("541.10")|| deptId.equals("541.20") || deptId.equals("548.00")|| deptId.equals("548.10")|| deptId.equals("548.20") || deptId.equals("541.40")){
					approval.setStandardCost(speststdDaoRemote.findByEstimateNo(pcesthmt.getId().getEstimateNo(), webAppName).getTotalCost());
					
				}else{
					approval.setStandardCost(speststdDaoRemote.findById(id, webAppName).getTotalCost());
					
				}*/
				approval.setDetailedCost(findByEstimationNo(pcesthmt.getId().getEstimateNo(), deptId, webAppName).getStdCost());
				approval.setStandardCost(findByEstimationNo(pcesthmt.getId().getEstimateNo(), deptId, webAppName).getStdCost());
				System.out.println("maplist "+2);
				if (maplist.isEmpty()){
					return null;
				}else if (maplist.size() == 1){
					System.out.println("maplist "+3);
					status=maplist.get(0).values().toArray()[1].toString();
					
		        	totalCost= new BigDecimal(maplist.get(0).values().toArray()[0].toString());
		        	dbTotalCost=Double.parseDouble(totalCost.toString());
		        	//System.out.println(status+" "+dbTotalCost);
		        	
			        	/*if (status.equals(EstimateStatus.JOB_APPROVAL_ES) && authorityLevel.equals("ES")){
			        		if (dbTotalCost <= limitTo ){
			            		if(hasPiv){
			            			changeStatusPcesthmt(jobNo, deptId, new BigDecimal(EstimateStatus.JOB_APPROVED), webAppName);
			            			approval.setFromStatus(statusFrom);
				            		approval.setToStatus(EstimateStatus.JOB_APPROVED);
				            		approvalDaoRemote.createAutoIdApprovals(approval, webAppName);
			            		}else {
			            			changeStatusPcesthmt(jobNo, deptId, new BigDecimal(EstimateStatus.JOB_ONGOING), webAppName);	
			            			approval.setFromStatus(statusFrom);
				            		approval.setToStatus(EstimateStatus.JOB_ONGOING);
				            		approvalDaoRemote.createAutoIdApprovals(approval, webAppName);
			            		}
			            		return "#Approved";
			        		}else if (dbTotalCost > limitTo){
			        			changeStatusPcesthmt(jobNo, deptId, new BigDecimal(statusTo), webAppName);
			        			approval.setFromStatus(statusFrom);
			            		approval.setToStatus(statusTo);
			            		approvalDaoRemote.createAutoIdApprovals(approval, webAppName);
			            		return "#Sent to "+forewordTo(statusTo.toString())+  "for Approval";
			        		}else{
			        			return "Can not Approve this";
			        		}
			    	    }else if (status.equals(EstimateStatus.JOB_APPROVAL_EA) && authorityLevel.equals("EA")){
			    	    	if (dbTotalCost > limitFrom && dbTotalCost <= limitTo){
			    	    		if(hasPiv){
			            			changeStatusPcesthmt(jobNo, deptId, new BigDecimal(EstimateStatus.JOB_APPROVED), webAppName);
			            			approval.setFromStatus(statusFrom);
				            		approval.setToStatus(EstimateStatus.JOB_APPROVED);
				            		approvalDaoRemote.createAutoIdApprovals(approval, webAppName);
			    	    		}else{ 
			            			changeStatusPcesthmt(jobNo, deptId, new BigDecimal(EstimateStatus.JOB_ONGOING), webAppName);	
			            			approval.setFromStatus(statusFrom);
				            		approval.setToStatus(EstimateStatus.JOB_ONGOING);
				            		approvalDaoRemote.createAutoIdApprovals(approval, webAppName);
			    	    		}
			    	    		return "#Approved";
			    	    	}else if (dbTotalCost > limitTo){
			        			changeStatusPcesthmt(jobNo, deptId, new BigDecimal(statusTo), webAppName);
			        			approval.setFromStatus(statusFrom);
			            		approval.setToStatus(statusTo);
			            		approvalDaoRemote.createAutoIdApprovals(approval, webAppName);
			            		return "#Sent to "+forewordTo(statusTo.toString())+  "for Approval";
			        		}else{
			        			return "@Can not Approve this";
			        		}
			    	    }else */
			        	if (deptId.equals("543.10") && status.equals(EstimateStatus.JOB_APPROVAL_EE) && authorityLevel.equals("EE") ){
			    	    		
		            			changeStatusPcesthmt(jobNo, deptId, new Long(EstimateStatus.JOB_ONGOING), webAppName);	
		            			approval.setFromStatus(statusFrom);
			            		approval.setToStatus(EstimateStatus.JOB_ONGOING);
			            		approval.setApprovalType("JOB_APRV");
			            		approvalDaoRemote.createAutoIdApprovals(approval, webAppName);
			    	    		
			    	    		return "#Approved";
			    	    	
			    	    }else if (status.equals(EstimateStatus.JOB_APPROVAL_EE) && authorityLevel.equals("EE") ){
			    	    	if (dbTotalCost > limitFrom && dbTotalCost <= limitTo){
			    	    		
		            			changeStatusPcesthmt(jobNo, deptId, new Long(EstimateStatus.JOB_ONGOING), webAppName);	
		            			approval.setFromStatus(statusFrom);
			            		approval.setToStatus(EstimateStatus.JOB_ONGOING);
			            		approval.setApprovalType("JOB_APRV");
			            		approvalDaoRemote.createAutoIdApprovals(approval, webAppName);
			    	    		
			    	    		return "#Approved";
			    	    	}else if (dbTotalCost > limitTo) {
			        			changeStatusPcesthmt(jobNo, deptId, new Long(statusTo), webAppName);
			        			approval.setFromStatus(statusFrom);
			            		approval.setToStatus(statusTo);
			            		approval.setApprovalType("JOB_VLDT");
			            		approvalDaoRemote.createAutoIdApprovals(approval, webAppName);
			            		return "#Sent to "+forewordTo(statusTo.toString())+  "for Approval";
			        		}else {
			        			return "@Can not Approve this";
			        		}
			    	    }else if (status.equals(EstimateStatus.JOB_APPROVAL_CE) && authorityLevel.equals("CE") ){
			    	    	//if (dbTotalCost > limitFrom && dbTotalCost <= limitTo){
			    	    		
		            			changeStatusPcesthmt(jobNo, deptId, new Long(statusTo), webAppName);
		            			approval.setFromStatus(statusFrom);
			            		approval.setToStatus(statusTo);
			            		
			            		approval.setApprovalType("JOB_APRV");
			            		approvalDaoRemote.createAutoIdApprovals(approval, webAppName);
		    	    		
			            		
			    	    		return "#Approved";
			    	    	/*}else if (dbTotalCost > limitTo) {
			        			changeStatusPcesthmt(jobNo, deptId, new Long(statusTo), webAppName);
			        			approval.setFromStatus(statusFrom);
			            		approval.setToStatus(statusTo);
			            		approval.setApprovalType("JOB_VLDT");
			            		approvalDaoRemote.createAutoIdApprovals(approval, webAppName);
			            		return "#Sent to "+forewordTo(statusTo.toString())+  "for Approval";
			        		}else {
			        			return "@Can not Approve this";
			        		}*/	
			    	    }else if (status.equals(EstimateStatus.JOB_APPROVAL_DGM) && authorityLevel.equals("DGM")){
			    	    	if (dbTotalCost > limitFrom ){
			    	    		
		            			changeStatusPcesthmt(jobNo, deptId, new Long(EstimateStatus.JOB_ONGOING), webAppName);	
		            			approval.setFromStatus(statusFrom);
			            		approval.setToStatus(EstimateStatus.JOB_ONGOING);
			            		approval.setApprovalType("JOB_APRV");
			            		approvalDaoRemote.createAutoIdApprovals(approval, webAppName);
			    	    		
			            		
			    	    		return "#Approved";
			    	    	}/*else if (dbTotalCost > limitTo){
			        			changeStatusPcesthmt(jobNo, deptId, new Long(statusTo), webAppName);
			        			approval.setFromStatus(statusFrom);
			            		approval.setToStatus(statusTo);
			            		approvalDaoRemote.createAutoIdApprovals(approval, webAppName);
			            		return "#Sent to "+forewordTo(statusTo.toString())+  "for Approval";
			        		}*/
			    	    	else {
			        			return "@Can not Approve this";
			        		}
			    	    }else if (status.equals(EstimateStatus.JOB_APPROVAL_AGM) && authorityLevel.equals("AGM")){
			    	    	if (dbTotalCost > limitFrom){
			    	    		
		            			changeStatusPcesthmt(jobNo, deptId, new Long(EstimateStatus.JOB_ONGOING), webAppName);	
		            			approval.setFromStatus(statusFrom);
			            		approval.setToStatus(EstimateStatus.JOB_ONGOING);
			            		approval.setApprovalType("JOB_APRV");
			            		approvalDaoRemote.createAutoIdApprovals(approval, webAppName);
			            		
			            		return "#Approved";
			    	    	} else {
			    	    		return "@Can not Approve this";
			    	    	}
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

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getNonAllocatedJobNoList(String deptId,Long status, List<String> aLlist, String webAppName) {
		//getEntityManager(webAppName);
		List<String> blist=new ArrayList<String>();
		String e=null;
		for(int i=0; i<=aLlist.size()-1;i++){
			e="'"+aLlist.get(i)+"'";
			blist.add(e.trim());
		}
		String notIn=blist.toString().substring(1, blist.toString().length()-1);
		//System.out.println(notIn);
		String qryStr=null;
		if (aLlist==null || aLlist.size()==0  )
			qryStr="SELECT TRIM(g.projectNo) FROM Pcesthmt g WHERE g.id.deptId = :deptId AND status = :status AND g.projectNo IS NOT NULL ORDER BY g.projectNo";
		else
			qryStr="SELECT TRIM(g.projectNo) FROM Pcesthmt g WHERE g.id.deptId = :deptId AND status = :status AND g.projectNo IS NOT NULL AND g.projectNo NOT IN ("+notIn+") ORDER BY g.projectNo";
		//System.out.println(qryStr.charAt(158));
		//String qryStr = "SELECT TRIM(g.projectNo) FROM Pcesthmt g WHERE g.id.deptId = :deptId AND status = :status AND g.projectNo IS NOT NULL ORDER BY g.projectNo";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("deptId", deptId);
		query.setParameter("status", status);
		List<String> list = query.getResultList();	
		return list;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<String> getJobNoList(String deptId,Long status, String webAppName) {
		//getEntityManager(webAppName);
		String qryStr = "SELECT TRIM(g.projectNo) FROM Pcesthmt g WHERE g.id.deptId = :deptId AND status = :status AND g.projectNo IS NOT NULL ORDER BY g.projectNo";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("deptId", deptId);
		query.setParameter("status", status);
		List<String> list = query.getResultList();	
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Pcesthmt> getJobDetailList(String deptId,Long status, String webAppName) {
		//getEntityManager(webAppName);
		String qryStr = "SELECT g FROM Pcesthmt g WHERE g.id.deptId = :deptId AND status = :status AND g.projectNo IS NOT NULL  ORDER BY g.projectNo";
		//String qryStr = "SELECT g FROM Pcesthmt g WHERE g.id.deptId = :deptId";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("deptId", deptId);
		query.setParameter("status", status);
		//query.setParameter("catCd", "SMC");
		List<Pcesthmt> list = query.getResultList();	
		return list;
	}
	
	

	@Override
	public void changeStatusPcesthmt(String jobNo,String deptId, Long status, String webAppName) {
		//getEntityManager(webAppName);
		String qryStr = "UPDATE Pcesthmt g set g.status=:status WHERE TRIM(g.projectNo)= :jobNo AND g.id.deptId = :deptId";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("status", status);
		query.setParameter("jobNo", jobNo.trim());
		query.setParameter("deptId", deptId);
		query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BigDecimal> getNonAllocatedAmountList(String deptId, Long status, List<String> aList, String webAppName) {
		//getEntityManager(webAppName);
		List<String> blist=new ArrayList<String>();
		String e=null;
		for(int i=0; i<=aList.size()-1;i++){
			e="'"+aList.get(i)+"'";
			blist.add(e.trim());
		}
		String notIn=blist.toString().substring(1, blist.toString().length()-1);
		//System.out.println(notIn);
		String qryStr=null;
		if (aList==null || aList.size()==0  )
			qryStr="SELECT TRIM(g.stdCost) FROM Pcesthmt g WHERE g.id.deptId = :deptId AND status = :status AND g.projectNo IS NOT NULL ORDER BY g.projectNo";
		else
			qryStr="SELECT TRIM(g.stdCost) FROM Pcesthmt g WHERE g.id.deptId = :deptId AND status = :status AND g.projectNo IS NOT NULL AND g.projectNo NOT IN ("+notIn+") ORDER BY g.projectNo";
		System.out.println(qryStr.charAt(155));
		//String qryStr = "SELECT TRIM(g.projectNo) FROM Pcesthmt g WHERE g.id.deptId = :deptId AND status = :status AND g.projectNo IS NOT NULL ORDER BY g.projectNo";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("deptId", deptId);
		query.setParameter("status", status);
		List<BigDecimal> list = query.getResultList();	
		return list;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public String getNextJobNo(String jobNoPrefix, String webAppName) {
		//getEntityManager(webAppName);
    	String sequence=null;
    	String like=jobNoPrefix+"%";
    	String strQuery="select a.projectNo from Pcesthmt a " +
    			"where a.projectNo like :like ORDER BY 1 DESC";
    	Query query=getEntityManager(webAppName).createQuery(strQuery);//SELECT MIS.TEST4_SEQ.NEXTVAL() FROM DUAL
    	query.setParameter("like", like);
    	List<String> list=query.getResultList();
    	if (list.size()!=0){
    		sequence=query.getResultList().get(0).toString().trim();
    		System.out.println(sequence);
    		sequence=sequence.substring(jobNoPrefix.length());//total 18 chars
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
	
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void validateJob(Pcesthmt pcesthmt, Approval approval, String webAppName) {
		try{
			updatePcesthmt(pcesthmt, webAppName);
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
	public void rejectJob(Pcesthmt pcesthtt, Approval approval, String webAppName) {
		try{
			updatePcesthmt(pcesthtt, webAppName);
			approvalDaoRemote.createAutoIdApprovals(approval, webAppName);
			
			
		}catch (Exception e) {
			//e.printStackTrace();
			System.out.println(e.getMessage());
			context.setRollbackOnly();
			throw new RuntimeException(e);
		}
		
	}
	
	private String forewordTo(String status){
		if (status.equals(EstimateStatus.JOB_APPROVAL_ES)){
			return " Engineering Assistant ";
		}else if (status.equals(EstimateStatus.JOB_APPROVAL_EE)){
			return " Electrical Engineer ";
		}else if (status.equals(EstimateStatus.JOB_APPROVAL_CE)){
			return " Cheif Engineer";
		}else if (status.equals(EstimateStatus.JOB_APPROVAL_DGM)){
			return " DGM ";
		}else if (status.equals(EstimateStatus.JOB_APPROVAL_AGM)){
			return " AGM ";
		} else {
			return " ";
		}
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<String> getJobNoListST(String deptId, Long status, String applicationType, String applicationSubType, String webAppName) {
		//getEntityManager(webAppName);
		Query query = getEntityManager(webAppName).createQuery("SELECT TRIM(g.projectNo) FROM Pcesthmt g, Application a WHERE  g.id.estimateNo=a.applicationNo AND   trim(g.id.deptId)= :deptId AND g.status =:status AND a.applicationType=:applicationType AND a.applicationSubType=:applicationSubType  order by g.projectNo");
		query.setParameter("deptId", deptId);
		query.setParameter("status", status);
		query.setParameter("applicationType", applicationType);
		query.setParameter("applicationSubType", applicationSubType);
		List<String> list = query.getResultList();
		return list;
	}



	@Override
	public int updateReviseApproveDetails(String jobNo,String deptId,Long status, String rejectedBy ,Date rejectedDate,String approvedByEE ,Date approvedEEDate,String approvedByCE ,Date approvedCEDate,String approvedByDGM ,Date approvedDGMDate,String rejectedReason, String webAppName) {
		//getEntityManager(webAppName);
		//System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		//String qryStr = "UPDATE Pcesthtt g set g.status=:status WHERE TRIM(g.id.estimateNo)= :estimateNo AND g.id.deptId = :deptId";
		
		StringBuffer qryStr = new StringBuffer();
		qryStr.append("UPDATE Pcesthmt g set g.status=:status ");
			
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
			qryStr.append(" WHERE TRIM(projectNo)= :jobNo AND g.id.deptId = :deptId ");
			 
		Query query = getEntityManager(webAppName).createQuery(qryStr.toString());
		query.setParameter("deptId", deptId);
		query.setParameter("jobNo", jobNo.trim());
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
	
	@SuppressWarnings("unchecked")
	@Override
	
	public List<Pcesthmt> getRevisedJobDetailList(String deptId,List<Long> status, String userId, String webAppName) {
		//getEntityManager(webAppName);
		//String qryStr ="SELECT p FROM Pcesthtt p WHERE trim(p.id.deptId)= :deptId AND  p.status = :status AND p.entBy=:entryBy AND order by p.id.estimateNo";
		StringBuffer buff = new StringBuffer();
		buff.append("SELECT p FROM Pcesthmt p WHERE trim(p.id.deptId)= :deptId AND  p.status in (:status) ");
		if(userId != null){
			buff.append(" AND trim(p.entBy)=:entryBy ");
		}
		buff.append(" order by p.id.estimateNo");
		Query query = getEntityManager(webAppName).createQuery(buff.toString());
		query.setParameter("deptId", deptId);
		query.setParameter("status", status);
		//query.setParameter("revNo", "2");
		if(userId != null){
			query.setParameter("entryBy", userId.trim());
		}
		List<Pcesthmt> list = query.getResultList();
		if(list != null && list.size() > 0){
			return list;
		}
		return null;
	}
	

}
