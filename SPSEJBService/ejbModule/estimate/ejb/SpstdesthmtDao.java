package estimate.ejb;


import java.util.Date;
import java.util.List;


import javax.ejb.Stateless;

import javax.persistence.PersistenceException;
import javax.persistence.Query;

import util.common.ApplicationStatus;
import util.emSelect.EmSelector;
import estimate.model.Spstdestdmt;
import estimate.model.Spstdesthmt;
import estimate.model.SpstdesthmtPK;

/**
 * Session Bean implementation class SpstaymtDao
 */
@Stateless
public class SpstdesthmtDao extends EmSelector implements SpstdesthmtDaoRemote, SpstdesthmtDaoLocal {

	  public SpstdesthmtDao() {
	        // TODO Auto-generated constructor stub
	  }
	@Override
	public void createSpstdesthmt(Spstdesthmt spstdesthmt, String webAppName) {
		// TODO Auto-generated method stub
		getEntityManager(webAppName).persist(spstdesthmt);
	}

	@Override
	public List<Spstdesthmt> getAll(String webAppName) {
		String qryStr ="select a from Spstdesthmt a";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
        List<Spstdesthmt> list = query.getResultList();
        return list;
	}

	@Override
	public void updateSpstdesthmt(Spstdesthmt spstdesthmt, String webAppName) {
		getEntityManager(webAppName).merge(spstdesthmt);
		
	}

	@Override
	public void removeSpstdesthmt(Spstdesthmt spstdesthmt, String webAppName) {
		getEntityManager(webAppName).remove(spstdesthmt);
		
	}

	@Override
	public void removeAll(String webAppName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Spstdesthmt findById(SpstdesthmtPK id, String webAppName)
			throws PersistenceException {
		return getEntityManager(webAppName).find(Spstdesthmt.class, id);
	}
	
	@Override
	public List<String> loadStandEstmatenos(String costCenter,List<Long> status,String userName,String webAppName) throws PersistenceException{
		//String qryStr = "select a.id.stdNo from Spstdesthmt a WHERE a.id.deptId=:deptId AND a.status in (:status) AND a.assignTo=:assignTo ";
		StringBuffer buff = new StringBuffer();
		buff.append("select a.id.stdNo from Spstdesthmt a WHERE a.id.deptId=:deptId AND a.status in (:status) order by a.id.stdNo ");
		
		if(userName != null){
			buff.append(" AND a.assignTo=:assignTo ");
		}
		
		Query query = getEntityManager(webAppName).createQuery(buff.toString());
		query.setParameter("deptId", costCenter);
		query.setParameter("status",status );

		if(userName != null){
			query.setParameter("assignTo",userName );
		}
		//query.setParameter("userId", userId);
		List<String> list = query.getResultList();
		if (list.isEmpty())
			return null;
        else {
        	return list;
        }
		
	}
	
	/**
	
	@Override
	public List<String> loadStandEst(String costCenter,List<Long> status,String userName,String webAppName) throws PersistenceException{
		//String qryStr = "select a.id.stdNo from Spstdesthmt a WHERE a.id.deptId=:deptId AND a.status in (:status) AND a.assignTo=:assignTo ";
		StringBuffer buff = new StringBuffer();
		buff.append("select a.id.stdNo from Spstdesthmt a WHERE a.id.deptId=:deptId AND a.status in (:status) order by a.id.stdNo ");
		
		if(userName != null){
			buff.append(" AND a.assignTo=:assignTo ");
		}
		
		Query query = getEntityManager(webAppName).createQuery(buff.toString());
		query.setParameter("deptId", costCenter);
		query.setParameter("status",status );

		if(userName != null){
			query.setParameter("assignTo",userName );
		}
		//query.setParameter("userId", userId);
		List<String> list = query.getResultList();
		if (list.isEmpty())
			return null;
        else {
        	return list;
        }
		
	}
	
	*/
	
	
	
	
	@Override
	public List<Spstdesthmt> loadStandEstmateDetails(String costCenter,List<Long> status,String userName,String webAppName) throws PersistenceException{
		/*String qryStr = "select a from Spstdesthmt a WHERE a.id.deptId=:deptId AND a.status in (:status)";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("deptId", costCenter);
		query.setParameter("status",status );
		//query.setParameter("userId", userId);
		List<Spstdesthmt> list = query.getResultList();
		if (list.isEmpty())
			return null;
        else {
        	return list;
        }*/
		
		StringBuffer buff = new StringBuffer();
		buff.append("select a from Spstdesthmt a WHERE a.id.deptId=:deptId AND a.status in (:status) ");
		
		
		if(userName != null){
			buff.append(" AND a.assignTo=:assignTo ");
		}
		
		Query query = getEntityManager(webAppName).createQuery(buff.toString());
		query.setParameter("deptId", costCenter);
		query.setParameter("status",status );

		if(userName != null){
			query.setParameter("assignTo",userName );
		}
		//query.setParameter("userId", userId);
		
		List<Spstdesthmt> list = query.getResultList();
		if (list.isEmpty())
			return null;
        else {
        	return list;
        }
		
	}
	
	@Override
	public List<Spstdesthmt> loadConMainSentList(String costCenter,String postId,List<Long> status,String webAppName) throws PersistenceException{
		String qryStr = "select a from Spstdesthmt a WHERE a.id.deptId=:deptId AND a.status in (:status) AND a.postDeptId=:postId";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("deptId", costCenter);
		query.setParameter("postId", postId);
		query.setParameter("status",status );
		//query.setParameter("userId", userId);
		List<Spstdesthmt> list = query.getResultList();
		if (list.isEmpty())
			return null;
        else {
        	return list;
        }
		
	}
	
	
	
	@Override
	public boolean updateEstimateStatus(String estimateNumber,String costCenter,Long status,String approvedBy,Date approvedDate ,String rejectedBy,Date rejectedDate,String planningByPE,Date planningDatePE,String valiadteByEE,Date valiadteDateEE,String valiadteByCE,Date valiadteDateCE,String rejectedReasonCE,String rejectedReasonPE,
			String rejectedReasonEE,String webAppName) throws PersistenceException{
		StringBuffer qryStr = new StringBuffer();
		qryStr.append("update Spstdesthmt a set a.status=:status ");
			
			if(approvedBy != null){
				qryStr.append(",a.approvedBy=:approvedBy ");
			}
			if(approvedDate != null){
				qryStr.append(",a.approvedDate=:approvedDate ");
			}
			if(rejectedBy != null){
				qryStr.append(",a.rejectedBy=:rejectedBy ");
			}
			if(rejectedDate != null){
				qryStr.append(",a.rejectedDate=:rejectedDate ");
			}
			if(rejectedReasonCE != null){
				qryStr.append(",a.rejReasonCE=:rejReasonCE ");
			}
			if(rejectedReasonEE != null){
				qryStr.append(",a.rejReasonEE=:rejReasonEE ");
			}
			if(rejectedReasonPE != null){
				qryStr.append(",a.rejReasonPE=:rejReasonPE ");
			}
			if(valiadteByCE  != null){
				qryStr.append(",a.validateByCE=:validateByCE ");
			}
			if(valiadteDateCE!= null){
				qryStr.append(",a.validateDateCE=:validateDateCE ");
			}
			if(valiadteByEE != null){
				qryStr.append(",a.validateByEE=:validateByEE ");
			}
			if(valiadteDateEE != null){
				qryStr.append(",a.validateDateEE=:validateDateEE ");
			}
			if(planningByPE != null){
				qryStr.append(",a.planningBy=:planningBy ");
			}
			if(planningDatePE!= null){
				qryStr.append(",a.planningDate=:planningDate ");
			}
		
		qryStr.append("where a.id.deptId=:deptId AND a.id.applicationNo=:estimateNumber");
		Query query = getEntityManager(webAppName).createQuery(qryStr.toString());
		query.setParameter("deptId", costCenter);
		query.setParameter("status",status );
		query.setParameter("estimateNumber", estimateNumber);
		
			if(approvedBy != null){
				query.setParameter("approvedBy", approvedBy);
				
			}
			if(approvedDate != null){
				query.setParameter("approvedDate", approvedDate);
			
			}
			if(rejectedBy != null){
				query.setParameter("rejectedBy", rejectedBy );
				
			}
			if(rejectedDate != null){
				query.setParameter("rejectedDate", rejectedDate );
				
			}
			if(rejectedReasonCE!= null){
				
				query.setParameter("rejReasonCE", rejectedReasonCE);
			}
			if(rejectedReasonEE != null){
				
				query.setParameter("rejReasonEE", rejectedReasonEE);
			}
			if(rejectedReasonPE != null){
				
				query.setParameter("rejReasonPE", rejectedReasonPE);
			}
			if(valiadteByCE  != null){
				
				query.setParameter("validateByCE", valiadteByCE);
			}
			if(valiadteDateCE != null){
			
				query.setParameter("validateDateCE",valiadteDateCE);
			}
			if(valiadteByEE != null){
				
				query.setParameter("validateByEE", valiadteByEE);
			}
			if(valiadteDateEE != null){
				
				query.setParameter("validateDateEE", valiadteDateEE);
			}
			if(planningByPE  != null){
				
				query.setParameter("planningBy", planningByPE);
			}
			if(planningDatePE != null){
				
				query.setParameter("planningDate", planningDatePE);
			}
		
		int updateStatus = query.executeUpdate();
		if (updateStatus ==1 )
			return true;
        else {
        	return false;
        }
		
	}
	@Override
	public int updateSendForCMStatus(String estimateNo,String deptId,Long status,String postDeptId,String webAppName) throws PersistenceException{
		String qryStr = "update Spstdesthmt a set a.status=:status,a.postDeptId=:postDeptId WHERE  a.id.stdNo = :estimateNo AND a.id.deptId=:deptId";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("deptId", deptId);
		query.setParameter("postDeptId",postDeptId);
		query.setParameter("status",status );
		query.setParameter("estimateNo",estimateNo );
		//query.setParameter("userId", userId);
		int statusUpdate = query.executeUpdate();
		return statusUpdate;
		
	}

	@Override
	public List<String> loadConsMaintenanceEstimaNos(String costCenter,List<Long> status,String webAppName) throws PersistenceException{
		String qryStr = "select a.id.stdNo from Spstdesthmt a WHERE a.postDeptId=:deptId AND a.status in (:status) order by a.id.stdNo";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("deptId", costCenter);
		query.setParameter("status",status );
		//query.setParameter("userId", userId);
		List<String> list = query.getResultList();
		if (list.isEmpty())
			return null;
        else {
        	return list;
        }
		
	}
	@Override
	public int updateAllocatedEstimateStatus(String estimateNumber,
			String postIdCenter, Long status, String webAppName)
			throws PersistenceException {
		String qryStr = "update Spstdesthmt a set a.status=:status WHERE  a.id.stdNo = :estimateNo AND a.postDeptId=:postDeptId";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
	
		query.setParameter("postDeptId",postIdCenter);
		query.setParameter("status",status );
		query.setParameter("estimateNo",estimateNumber );
		//query.setParameter("userId", userId);
		int statusUpdate = query.executeUpdate();
		return statusUpdate;
		
	}
	@Override
	public int updateEstimateStatus(String estimateNumber,
			String deptId, Long status, String webAppName)
			throws PersistenceException {
		String qryStr = "update Spstdesthmt a set a.status=:status WHERE  a.id.stdNo = :estimateNo AND a.id.deptId=:deptId";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
	
		query.setParameter("deptId",deptId);
		query.setParameter("status",status );
		query.setParameter("estimateNo",estimateNumber );
		//query.setParameter("userId", userId);
		int statusUpdate = query.executeUpdate();
		return statusUpdate;
		
	}
	@Override
	public List<Spstdesthmt> loadStandEstmateDetails(String costCenter,List<Long> status,String userName,String applicationType,String webAppName) throws PersistenceException{
		/*String qryStr = "select a from Spstdesthmt a WHERE a.id.deptId=:deptId AND a.status in (:status)";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("deptId", costCenter);
		query.setParameter("status",status );
		//query.setParameter("userId", userId);
		List<Spstdesthmt> list = query.getResultList();
		if (list.isEmpty())
			return null;
        else {
        	return list;
        }*/
		//System.out.println("Hiii 4");
		StringBuffer buff = new StringBuffer();
		buff.append("select a from Spstdesthmt a ,Application b WHERE a.id.applicationNo = b.applicationNo AND b.applicationType =:applicationType AND a.id.deptId=:deptId AND a.status in (:status) order by a.id.applicationNo ");
		
		//System.out.println("Hiii 5");
		if(userName != null){
			buff.append(" AND a.assignTo=:assignTo ");
		}
		//System.out.println("Hiii 6");
		Query query = getEntityManager(webAppName).createQuery(buff.toString());
		query.setParameter("deptId", costCenter);
		query.setParameter("status",status );
		query.setParameter("applicationType",applicationType);
		//System.out.println("Hiii 7");
		if(userName != null){
			query.setParameter("assignTo",userName );
		}
		//query.setParameter("userId", userId);
		List<Spstdesthmt> list = query.getResultList();
		if (list.isEmpty())
			return null;
        else {
        	return list;
        }
		
	}
	
	
	@Override
	public List<String> loadStandEstmateDetailsType(String costCenter,List<Long> status,String userName,String applicationType,String webAppName) throws PersistenceException{
		/*String qryStr = "select a from Spstdesthmt a WHERE a.id.deptId=:deptId AND a.status in (:status)";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("deptId", costCenter);
		query.setParameter("status",status );
		//query.setParameter("userId", userId);
		List<Spstdesthmt> list = query.getResultList();
		if (list.isEmpty())
			return null;
        else {
        	return list;
        }*/
		//System.out.println("Hiii 4");
		StringBuffer buff = new StringBuffer();
		//19022016
		buff.append("select a.id.applicationNo from Spstdesthmt a ,Application b WHERE a.id.applicationNo = b.applicationNo AND b.applicationType =:applicationType AND a.id.deptId=:deptId AND a.status in (:status) order by a.id.applicationNo");
		
		//System.out.println("Hiii 5");
		if(userName != null){
			buff.append(" AND a.assignTo=:assignTo ");
		}
		//System.out.println("Hiii 6");
		Query query = getEntityManager(webAppName).createQuery(buff.toString());
		query.setParameter("deptId", costCenter);
		query.setParameter("status",status );
		query.setParameter("applicationType",applicationType);
		//System.out.println("Hiii 7");
		if(userName != null){
			query.setParameter("assignTo",userName );
		}
		//query.setParameter("userId", userId);
		List<String> list = query.getResultList();
		if (list.isEmpty())
			return null;
        else {
        	return list;
        }
		
	}
	

}
