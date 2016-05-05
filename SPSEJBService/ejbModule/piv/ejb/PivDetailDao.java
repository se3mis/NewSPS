package piv.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;
import piv.model.PivDetail;
import piv.model.PivDetailPK;
import util.common.PIVStatus;
import util.emSelect.EmSelector;

/**
 * Session Bean implementation class pivDetailDao
 */
@Stateless
public class PivDetailDao extends EmSelector implements PivDetailDaoRemote, PivDetailDaoLocal {
	
    /**
     * Default constructor. 
     */
    public PivDetailDao() {
        // TODO Auto-generated constructor stub
    }
    
   

	@Override
	public void createPiv(PivDetail pivDetail, String webAppName) {
		//getEntityManager(webAppName);
		getEntityManager(webAppName).persist(pivDetail);
		
	}
	
	@Override
	public String createPivAutoId(PivDetail pivDetail, String pivNoPrefix, String webAppName) {
		//getEntityManager(webAppName);
		PivDetail pivDetail2=getPivDetailByStatusNRefType(pivDetail.getId().getDeptId(), pivDetail.getReferenceNo(), pivDetail.getReferenceType(), "N", webAppName);
		if (pivDetail2==null){
			System.out.println("######### "+pivNoPrefix);
			String nextPivNo=pivNoPrefix+getNextPivNo(pivNoPrefix, webAppName);
			PivDetailPK id=new PivDetailPK();
			id.setPivNo(nextPivNo);
			id.setDeptId(pivDetail.getId().getDeptId());
			pivDetail.setId(id);
			getEntityManager(webAppName).persist(pivDetail);
			return nextPivNo; 
		}else {
			return null; 
		}
		
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public PivDetail findById(PivDetailPK id, String webAppName) {
		//getEntityManager(webAppName);
		try{
			if (id.getDeptId().equals("541.00") || id.getDeptId().equals("542.00") || id.getDeptId().equals("547.00") || id.getDeptId().equals("548.00")) {
				String qryStr = "SELECT p FROM PivDetail p WHERE p.id.pivNo = :pivNo ";
	            Query query = getEntityManager(webAppName).createQuery(qryStr);
	            query.setParameter("pivNo", id.getPivNo());
	            List<PivDetail> list = query.getResultList();
	            if (list.isEmpty())
	    			return null;
	            else if (list.size() == 1)
	            	return list.get(0);
	            throw new NonUniqueResultException();
			}else{
				return getEntityManager(webAppName).find(PivDetail.class, id);
			}
		}catch(Exception ex){
            ex.printStackTrace();
            return null;
      }
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public PivDetail findByPivNo(String pivNo, String webAppName) {
		//getEntityManager(webAppName);
		try{
			String qryStr = "SELECT p FROM PivDetail p WHERE p.id.pivNo = :pivNo ";
            Query query = getEntityManager(webAppName).createQuery(qryStr);
            query.setParameter("pivNo", pivNo);
            List<PivDetail> list = query.getResultList();
            if (list.isEmpty())
    			return null;
            else if (list.size() == 1)
            	return list.get(0);
            throw new NonUniqueResultException();
        }catch(Exception ex){
            ex.printStackTrace();
            return null;
          }
         
    }

	@Override
	public void updatePivDetail(PivDetail pivDetail, String webAppName) {
		//getEntityManager(webAppName);
		getEntityManager(webAppName).merge(pivDetail);
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<PivDetail> getAll(String webAppName) {
		//getEntityManager(webAppName);
		String qryStr = "select a from PivDetail a";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
        List<PivDetail> list = query.getResultList();
        return list;

	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<PivDetail> getAll(String deptId, String webAppName) {
		//getEntityManager(webAppName);
		String qryStr = "select a from PivDetail a WHERE a.id.deptId=:deptId";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("deptId", deptId);
        List<PivDetail> list = query.getResultList();
        return list;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<PivDetail> getPivDetail(String deptId, String status, String webAppName) {
		//getEntityManager(webAppName);
		String qryStr = "select a from PivDetail a WHERE a.id.deptId=:deptId AND a.status=:status";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("deptId", deptId);
		query.setParameter("status", status);
        List<PivDetail> list = query.getResultList();
        return list;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<PivDetail> getPivDetail(String deptId, String referenceType, String status , String webAppName) {
		//getEntityManager(webAppName);
		String qryStr = "select a from PivDetail a WHERE a.id.deptId=:deptId AND a.referenceType=:referenceType AND a.status=:status";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("deptId", deptId);
		query.setParameter("referenceType", referenceType);
		query.setParameter("status", status);
        List<PivDetail> list = query.getResultList();
        return list;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<PivDetail> getPivDetailByRefNo(String deptId, String referenceNo, String referenceType , String webAppName) {
		//getEntityManager(webAppName);
		String qryStr = "select a from PivDetail a WHERE a.id.deptId=:deptId AND a.referenceType=:referenceType AND a.referenceNo=:referenceNo";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("deptId", deptId);
		query.setParameter("referenceNo", referenceNo);
		query.setParameter("referenceType", referenceType);
		List<PivDetail> list = query.getResultList();
        return list;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<PivDetail> getPivDetailByRefNoExcludingDestroy(String deptId, String referenceNo, String referenceType , String webAppName) {
		//getEntityManager(webAppName);
		String qryStr = "select a from PivDetail a WHERE a.id.deptId=:deptId AND a.referenceType=:referenceType AND a.referenceNo=:referenceNo AND a.status <> :status ";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("deptId", deptId);
		query.setParameter("referenceNo", referenceNo);
		query.setParameter("referenceType", referenceType);
		query.setParameter("status", PIVStatus.DESTROYED);
		List<PivDetail> list = query.getResultList();
        return list;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<PivDetail> getPivDetailByRefNo(String deptId, String referenceNo , String webAppName) {
		//getEntityManager(webAppName);
		String qryStr = "select a from PivDetail a WHERE a.id.deptId=:deptId AND a.referenceNo=:referenceNo AND a.status <> 'S' AND a.status <> 'D' ";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("deptId", deptId);
		query.setParameter("referenceNo", referenceNo); 
		List<PivDetail> list = query.getResultList();
        return list;
	}
	
	@SuppressWarnings("unchecked") 
	@Override
	public List<PivDetail> getPivDetailByRefNoExcludingDestroy(String deptId, String referenceNo , String webAppName) {
		//getEntityManager(webAppName);
		String qryStr = "select a from PivDetail a WHERE a.id.deptId=:deptId AND a.referenceNo=:referenceNo AND a.status<> :status ";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("deptId", deptId);
		query.setParameter("referenceNo", referenceNo); 
		query.setParameter("status", PIVStatus.DESTROYED);
		List<PivDetail> list = query.getResultList();
        return list;
	}
	

	@Override
	public void removePivDetail(PivDetail pivDetail, String webAppName) {
		//getEntityManager(webAppName);
		getEntityManager(webAppName).remove(pivDetail);
		
	}

	@Override
	public void removeAll(String webAppName) {
		//getEntityManager(webAppName);
		throw new UnsupportedOperationException("Not supported yet. Ask Dileepa If u want to use it");
		
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public boolean canGeneratePiv(String deptId, String estimateNo, String referenceType, String webAppName) {
		//getEntityManager(webAppName);  
		boolean can = true;
	      try
	      {
	            String qryStr = " select p.id.pivNo "+
	                                    " from PivDetail p "+
	                                    " where p.id.deptId = :deptId " +
	                                    " and p.referenceNo = :referenceNo "+
	                                    " and p.referenceType = :referenceType "+
	                                    " and (status = 'N' or status='P') ";
	                                    
	            
	            Query query = getEntityManager(webAppName).createQuery(qryStr);
	            query.setParameter("deptId", deptId);
	            query.setParameter("referenceNo", estimateNo);
	            query.setParameter("referenceType", referenceType);
	              
	              List list = query.getResultList();
	              if(!list.isEmpty())
	                  {
	                  can = false;
	                  }
	      }
	      catch(Exception e){
	            can = false;
	            e.printStackTrace();
	      }
	      return can;
	    }
	
	
	@SuppressWarnings("unchecked")
	@Override
	public PivDetail findByReferenceNo(String deptId, String referenceNo, String referenceType, String webAppName) {
		//getEntityManager(webAppName);
		//PivDetail piv = null;
        try{
        	String qryStr = "SELECT p FROM PivDetail p WHERE p.referenceNo = :referenceNo "+
        								" AND p.id.deptId = :deptId "+
                                        " AND p.referenceType = :referenceType AND p.status <> 'S' AND p.status <> 'D'";
            Query query = getEntityManager(webAppName).createQuery(qryStr);
            query.setParameter("referenceNo", referenceNo);
            query.setParameter("deptId", deptId);
            query.setParameter("referenceType", referenceType);
            List<PivDetail> list = query.getResultList();
            if (list.isEmpty())
    			return null;
            else if (list.size() == 1)
            	return list.get(0);
            throw new NonUniqueResultException();
        }catch(Exception ex){ 
        	ex.printStackTrace();
        	return null;
        }
          
    }
	
	@SuppressWarnings("unchecked")
	@Override
	public PivDetail findByReferenceNoIncludingSD(String deptId, String referenceNo, String referenceType, String webAppName) {
		//getEntityManager(webAppName);
		//PivDetail piv = null;
        try{
        	String qryStr = "SELECT p FROM PivDetail p WHERE p.referenceNo = :referenceNo "+
        								" AND p.id.deptId = :deptId "+
                                        " AND p.referenceType = :referenceType ";
            Query query = getEntityManager(webAppName).createQuery(qryStr);
            query.setParameter("referenceNo", referenceNo);
            query.setParameter("deptId", deptId);
            query.setParameter("referenceType", referenceType);
            List<PivDetail> list = query.getResultList();
            if (list.isEmpty())
    			return null;
            else if (list.size() == 1)
            	return list.get(0);
            throw new NonUniqueResultException();
        }catch(Exception ex){ 
        	ex.printStackTrace();
        	return null;
        }
          
    }
    
	
	@SuppressWarnings("unchecked")
	@Override
	public PivDetail findByReferenceNo(String referenceNo, String referenceType, String webAppName) {
		//getEntityManager(webAppName);
		//PivDetail piv = null;
        try{
        	String qryStr = "SELECT p FROM PivDetail p WHERE p.referenceNo = :referenceNo "+
        								" AND p.referenceType = :referenceType AND p.status <> 'S' AND p.status <> 'D' ";
            Query query = getEntityManager(webAppName).createQuery(qryStr);
            query.setParameter("referenceNo", referenceNo);
            query.setParameter("referenceType", referenceType);
            List<PivDetail> list = query.getResultList();
            if (list.isEmpty())
    			return null;
            else if (list.size() == 1)
            	return list.get(0);
            throw new NonUniqueResultException();
        }catch(Exception ex){
                ex.printStackTrace();
                return null;
        }
        
    }
	
	
	@SuppressWarnings("unchecked")
	@Override
	public PivDetail findByReferenceNoIncludingSD(String referenceNo, String referenceType, String webAppName) {
		//getEntityManager(webAppName);
		//PivDetail piv = null;
        try{
        	String qryStr = "SELECT p FROM PivDetail p WHERE p.referenceNo = :referenceNo "+
        								" AND p.referenceType = :referenceType ";
            Query query = getEntityManager(webAppName).createQuery(qryStr);
            query.setParameter("referenceNo", referenceNo);
            query.setParameter("referenceType", referenceType);
            List<PivDetail> list = query.getResultList();
            if (list.isEmpty())
    			return null;
            else if (list.size() == 1)
            	return list.get(0);
            throw new NonUniqueResultException();
        }catch(Exception ex){
                ex.printStackTrace();
                return null;
        }
        
    }
	
	
	@SuppressWarnings("rawtypes")
	@Override
	public int getMaxPivSeqNo(String deptId, String referenceNo,String referenceType, String webAppName) {
		//getEntityManager(webAppName);  
		int maxNo = 0;
	      try
	      {
	            String qryStr = " select max(p.pivSeqNo) from pivDetail p "+
	                                    " where p.id.deptId = :deptId " +
	                                    " and p.referenceNo = :referenceNo "+
	                                    " and p.referenceType = :referenceType "+
	                                    " and p.status = 'P'" ;
	                                                      
	            Query query = getEntityManager(webAppName).createQuery(qryStr);
	            query.setParameter("deptId", deptId);
	            query.setParameter("referenceNo", referenceNo);
	            query.setParameter("referenceType", referenceType);
	              
	              List list = query.getResultList();
	              if(!list.isEmpty())
	                  {
	                  maxNo = (Integer)list.get(0);
	                  }
	      }
	      catch(Exception e){
	            e.printStackTrace();
	      }
	      return maxNo;
	    }
	
	@SuppressWarnings("unchecked")
	@Override
	public String getNextPivNo(String pivNoPrefix, String webAppName) {
		//getEntityManager(webAppName);
		String sequence=null;
    	String like=pivNoPrefix+"%";
    	String strQuery="select p.id.pivNo from PivDetail p where p.id.pivNo like :like ORDER BY 1 DESC";
    	Query query=getEntityManager(webAppName).createQuery(strQuery);//SELECT MIS.TEST4_SEQ.NEXTVAL() FROM DUAL
    	query.setParameter("like", like);
    	List<String> list=query.getResultList();
    	if (list.size()!=0){
    		sequence=query.getResultList().get(0).toString().trim();
    		System.out.println(sequence);
    		//sequence=sequence.substring(20);//total 24 chars
    		sequence=sequence.substring(18);//total 22 chars
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
	public boolean hasActivePiv(String deptId, String referenceNo, String referenceType, String webAppName) {
		//getEntityManager(webAppName);  
		boolean can = false;
	      try
	      {
	            String qryStr = " select p.id.pivNo "+
	                                    " from PivDetail p "+
	                                    " where p.id.deptId = :deptId " +
	                                    " and p.referenceNo = :referenceNo "+
	                                    " and p.referenceType = :referenceType "+
	                                    " and (status = 'N' or status='P' or status='C') ";
	                                    
	            
	            Query query = getEntityManager(webAppName).createQuery(qryStr);
	            query.setParameter("deptId", deptId);
	            query.setParameter("referenceNo", referenceNo);
	            query.setParameter("referenceType", referenceType);
	              
	              List list = query.getResultList();
	              if(!list.isEmpty())
	                  {
	                  can = true;
	                  }
	      }
	      catch(Exception e){
	            can = false;
	            e.printStackTrace();
	      }
	      return can;
	    }
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<String> getNewPivNos(String deptId, String referenceType, String status, String webAppName) {
		//getEntityManager(webAppName);
		//String qryStr = "select a from PivDetail a WHERE a.id.deptId=:deptId AND a.status=:status";
		String qryStr = "select a.referenceNo from PivDetail a where a.id.deptId=:deptId and a.status=:status and a.referenceType=:referenceType order by trim(a.referenceType) ";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("deptId", deptId);
		query.setParameter("referenceType", referenceType);
		query.setParameter("status", status);
        List<String> list = query.getResultList();
        return list;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public PivDetail getPivDetailByStatus(String deptId, String referenceNo, String status, String webAppName) {
		//getEntityManager(webAppName);
		//String qryStr = "select a from PivDetail a WHERE a.id.deptId=:deptId AND a.status=:status";
		String qryStr = "select a from PivDetail a WHERE a.id.deptId=:deptId AND a.referenceNo=:referenceNo AND a.status =:status ";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("deptId", deptId);
		query.setParameter("referenceNo", referenceNo);
		query.setParameter("status", status);
        List<PivDetail> list = query.getResultList();
        if (list.isEmpty())
			return null;
        else if (list.size() == 1)
        	return list.get(0);
        throw new NonUniqueResultException();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public PivDetail getPivDetailByStatusNRefType(String deptId, String referenceNo, String referenceType, String status, String webAppName) {
		//getEntityManager(webAppName);
		//String qryStr = "select a from PivDetail a WHERE a.id.deptId=:deptId AND a.status=:status";
		String qryStr = "select a from PivDetail a WHERE a.id.deptId=:deptId AND a.referenceNo=:referenceNo and a.referenceType=:referenceType AND a.status =:status ";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("deptId", deptId);
		query.setParameter("referenceNo", referenceNo);
		query.setParameter("referenceType", referenceType);
		query.setParameter("status", status);
        List<PivDetail> list = query.getResultList();
        if (list.isEmpty())
			return null;
        else if (list.size() == 1)
        	return list.get(0);
        throw new NonUniqueResultException();
	}

	


	

}
