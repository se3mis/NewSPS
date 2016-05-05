package inventory.ejb;

import inventory.model.Intrhmt;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;
import util.emSelect.EmSelector;

/**
 * Session Bean implementation class IntrhmtDao
 */
@Stateless
public class IntrhmtDao extends EmSelector implements IntrhmtDaoRemote, IntrhmtDaoLocal {
	
    /**
     * Default constructor. 
     */
    public IntrhmtDao() { 
        // TODO Auto-generated constructor stub
    }
    
	
    @SuppressWarnings("unchecked")
	@Override
	public List<Intrhmt> getAll1(String webAppName) {
    	//getEntityManager(webAppName);
    	String qryStr = "SELECT a FROM Intrhmt a";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
        List<Intrhmt> list = query.getResultList();
        return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Boolean isMaterialIssued(String jobNo, String deptId, BigDecimal issueTo, BigDecimal status, String webAppName) {
		//getEntityManager(webAppName);
		String qryStr = "SELECT TRIM(a.id.docNo) FROM Intrhmt a WHERE TRIM(a.isRef)= :isRef AND a.id.deptId =:deptId AND a.issueTo= :issueTo AND status =:status";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("isRef", jobNo);
		query.setParameter("deptId", deptId);
		query.setParameter("issueTo", issueTo);
		query.setParameter("status", status);
		List<String> list= query.getResultList();
		if (list.isEmpty())
			return false;
        else 
        	return true;
        
        //return list;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Boolean isMaterialIssued(String jobNo, String deptId, String webAppName) {
		//getEntityManager(webAppName);
		String qryStr = "SELECT TRIM(a.id.docNo) FROM Intrhmt a WHERE TRIM(a.isRef)= :isRef AND a.id.deptId =:deptId";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("isRef", jobNo);
		query.setParameter("deptId", deptId);
		List<String> list= query.getResultList();
		System.out.println(list);
		if (list.isEmpty())
			return false;
        else 
        	return true;
        
        //return list;
	}

}
