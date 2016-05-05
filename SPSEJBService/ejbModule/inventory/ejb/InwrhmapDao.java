package inventory.ejb;



import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;
import util.emSelect.EmSelector;

/**
 * Session Bean implementation class InwrhmapDao
 */
@Stateless
public class InwrhmapDao extends EmSelector implements InwrhmapDaoRemote, InwrhmapDaoLocal {
	
    /**
     * Default constructor. 
     */
    public InwrhmapDao() {
        // TODO Auto-generated constructor stub
    }
    


	@SuppressWarnings("unchecked")
	@Override
	public String mapWarehouse(String deptId, String webAppName) {
		//getEntityManager(webAppName);
		String qryStr = "select a.id.wrhDept from Inwrhmap a WHERE TRIM(a.id.deptId) =:deptId";	
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("deptId", deptId);
		List<String> list = query.getResultList();
		if (list.isEmpty())
			return null;
        else if (list.size() == 1)
        	return list.get(0);
        throw new NonUniqueResultException();
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<String> loadWarehouses(String deptId, String webAppName) {
		//getEntityManager(webAppName);
		String qryStr = "select a.id.wrhDept from Inwrhmap a WHERE TRIM(a.id.deptId) =:deptId";	
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("deptId", deptId);
		List<String> list = query.getResultList();
		
		return list;
        
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public BigDecimal getConRateByDeptId(String deptId, String webAppName) {
	
		String qryStr = "select a.conRat from Inwrhmap a WHERE TRIM(a.id.deptId) =:deptId";	
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("deptId", deptId);
		Double stdCost = (Double) query.getSingleResult();
		if(stdCost == null || stdCost.doubleValue() == 0.0){
			//stdCost = null;
			return null;
		}
		return new BigDecimal(stdCost);
        
	}
}
