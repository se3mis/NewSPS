package piv.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import estimate.model.EstimateReference;
import estimate.model.Pcesthtt;
import estimate.model.PcesthttPK;
import piv.model.PivDetail;
import piv.model.PivDetailPK;
import piv.model.TempTb;
import util.common.PIVStatus;
import util.emSelect.EmSelector;

/**
 * Session Bean implementation class TempTbDao
 */
@Stateless
public class TempTbDao extends EmSelector implements TempTbDaoRemote, TempTbDaoLocal {
	
    /**
     * Default constructor. 
     */
    public TempTbDao() {
        // TODO Auto-generated constructor stub
    }
    
   

	@Override
	public void createTempTb(TempTb tempTb, String webAppName) {
		//getEntityManager(webAppName);
		getEntityManager(webAppName).persist(tempTb);
		
	}
	@Override
	public void updateTempTb(TempTb tempTb, String webAppName) {
		String qryStr = "UPDATE TempTb g SET g.pivDate = :pivDate,g.pivAmount=:pivAmt,g.pivNo=:pivNo WHERE TRIM(g.estimateNo) = :estimateNo AND TRIM(g.deptId) = :deptId";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("estimateNo", tempTb.getEstimateNo().trim());
		query.setParameter("pivDate", tempTb.getPivDate());
		query.setParameter("pivAmt", tempTb.getPivAmount());
		query.setParameter("pivNo", tempTb.getPivNo());
		query.setParameter("deptId", tempTb.getDeptId().trim());
	
		query.executeUpdate();		
		
		
		
	}
	@Override
	public int removTempTb(TempTb tempTb,
			String webAppName) {
		String qryStr = "DELETE  FROM TempTb g WHERE TRIM(g.estimateNo) = :estimateNo AND g.deptId = :deptId";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("estimateNo", tempTb.getEstimateNo().trim());
		query.setParameter("deptId", tempTb.getDeptId().trim());
	
		int status = query.executeUpdate();		
		
		return status;
		
	}
	@SuppressWarnings("unchecked")
	@Override
	public TempTb findById(String estimationNo,String deptId, String webAppName) throws PersistenceException {
		//getEntityManager(webAppName);
		//return getEntityManager(webAppName).find(Pcesthtt.class, id);
		String qryStr = "SELECT g FROM TempTb g WHERE TRIM(g.estimateNo) = :estimateNo AND g.deptId = :deptId";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("estimateNo", estimationNo.trim());
		query.setParameter("deptId", deptId);
	
		List<TempTb> list = query.getResultList();		
		if (list.isEmpty())
			return null;
        else if (list.size() == 1){
        	return list.get(0);
        }
        else{
        	return list.get(0);
        }
		
	}

	
	

}
