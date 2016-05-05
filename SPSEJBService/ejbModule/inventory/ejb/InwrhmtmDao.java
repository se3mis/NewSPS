package inventory.ejb;

import java.math.BigDecimal;
import java.util.List;

import inventory.model.Inwrhmtm;
import javax.ejb.Stateless;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;

import estimate.model.MaterialGrid;
import util.emSelect.EmSelector;

/**
 * Session Bean implementation class InwrhmtmDao
 */
@Stateless
public class InwrhmtmDao extends EmSelector implements InwrhmtmDaoRemote, InwrhmtmDaoLocal {
	
    /**
     * Default constructor. 
     */
    public InwrhmtmDao() {
        // TODO Auto-generated constructor stub
    }
    
    @SuppressWarnings("unchecked")
	@Override
	public List<MaterialGrid> getAll(String warehouse,String webAppName) {
		//getEntityManager(webAppName);
		String qryStr = "select new estimate.model.MaterialGrid(TRIM(wm.id.matCd) , TRIM(m.matNm), wm.unitPrice, wm.uomCd ) from Inwrhmtm wm ,Inmatm m where wm.id.deptId=:warehouse and wm.id.matCd = m.matCd ";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("warehouse", warehouse);
		List<MaterialGrid> list = query.getResultList();
        return list;

	}
    
   
    
	@SuppressWarnings("unchecked")
	@Override
	public Inwrhmtm findByMatCd(String matCd, String warehouse, String webAppName) {
		//getEntityManager(webAppName);
		String qryStr = "select a from Inwrhmtm a WHERE TRIM(a.id.matCd) =:matCd AND a.id.deptId=:warehouse";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		//Query query = getEntityManager(webAppName).createQuery("select a from Inwrhmtm a WHERE a.id.deptId = :warehouse");
		//Query query = getEntityManager(webAppName).createQuery("select a from Inwrhmtm a");
        query.setParameter("matCd", matCd);
        query.setParameter("warehouse", warehouse);
		List<Inwrhmtm> list = query.getResultList();
		if (list.isEmpty())
			return null;
        else if (list.size() == 1)
        	return list.get(0);
        throw new NonUniqueResultException();
	}
	@SuppressWarnings("unchecked")
	@Override
	public BigDecimal findUPByMatCd(String matCd, String warehouse, String webAppName) {
		//getEntityManager(webAppName);
		String qryStr = "select a.unitPrice  from Inwrhmtm a WHERE TRIM(a.id.matCd) =:matCd AND trim(a.id.deptId)=:warehouse and status IN ('2','7')";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		//Query query = getEntityManager(webAppName).createQuery("select a from Inwrhmtm a WHERE a.id.deptId = :warehouse");
		//Query query = getEntityManager(webAppName).createQuery("select a from Inwrhmtm a");
        query.setParameter("matCd", matCd.trim());
        query.setParameter("warehouse", warehouse.trim());
		List<BigDecimal> list = query.getResultList();
		if (list.isEmpty())
			return null;
        else if (list.size() == 1)
        	return list.get(0);
        else{
        	return list.get(0);
        }
       // throw new NonUniqueResultException();
	}
	@SuppressWarnings("unchecked")
	@Override
	public BigDecimal findUnitPriceByMatCd(String matCd, String webAppName) {
		//getEntityManager(webAppName);
		String qryStr = "select a.unitPrice from Inwrhmtm a WHERE TRIM(a.id.matCd) =:matCd";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		//Query query = getEntityManager(webAppName).createQuery("select a from Inwrhmtm a WHERE a.id.deptId = :warehouse");
		//Query query = getEntityManager(webAppName).createQuery("select a from Inwrhmtm a");
        query.setParameter("matCd", matCd);
       
		List<BigDecimal> list = query.getResultList();
		if (list.isEmpty()){
			return null;
		}
        else {
        	return list.get(0);
        }
        //throw new NonUniqueResultException();
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<String> findNPLMatCds(String deptId, String webAppName) {
		//getEntityManager(webAppName);
		String qryStr = "select TRIM(a.id.matCd) from Inwrhmtm a WHERE a.id.deptId= :deptId AND  TRIM(a.id.matCd) like 'NPL%' ";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		//Query query = getEntityManager(webAppName).createQuery("select a from Inwrhmtm a WHERE a.id.deptId = :warehouse");
		//Query query = getEntityManager(webAppName).createQuery("select a from Inwrhmtm a");
		query.setParameter("deptId", deptId);
	       
		List<String> list = query.getResultList();
		if (list.isEmpty()){
			return null;
		}
        else {
        	return list;
        }
        //throw new NonUniqueResultException();
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<String> findMatCds(String deptId, String webAppName) {
		//getEntityManager(webAppName);
		String qryStr = "select TRIM(a.id.matCd) from Inwrhmtm a WHERE a.id.deptId= :deptId ";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		//Query query = getEntityManager(webAppName).createQuery("select a from Inwrhmtm a WHERE a.id.deptId = :warehouse");
		//Query query = getEntityManager(webAppName).createQuery("select a from Inwrhmtm a");
		query.setParameter("deptId", deptId);
       
		List<String> list = query.getResultList();
		if (list.isEmpty()){
			return null;
		}
        else {
        	return list;
        }
        //throw new NonUniqueResultException();
	}
	/*@SuppressWarnings("unchecked")
	@Override
	public List<MatInfo> getMatListByCategory(String deptId, String like) {
		//String qryStr = "select TRIM(a.resType), a.resCat, TRIM(a.id.matCd), TRIM(a.matNm), TRIM(a.uom), a.unitPrice, a.tolerance   from Inmatm a WHERE TRIM(a.matCd) like :like";		Query query = getEntityManager(webAppName).createQuery(qryStr);
		String qryStr = "select new inventory.model.MatInfo (TRIM(a.id.matCd), TRIM(b.matNm)) from Inwrhmtm a, Inmatm b WHERE a.id.matCd=b.id.matCd AND a.id.deptId=:deptId AND TRIM(a.id.matCd) like :like ORDER BY a.id.matCd ASC";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("deptId", deptId);
		query.setParameter("like", like+"%");
		List<MatInfo> list = query.getResultList();
		return list;
	}*/

}
