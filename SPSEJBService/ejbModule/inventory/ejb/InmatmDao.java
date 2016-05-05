package inventory.ejb;

import inventory.model.Inmatm;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import util.emSelect.EmSelector;



/**
 * Session Bean implementation class InmatmDao
 */
@Stateless
public class InmatmDao extends EmSelector implements InmatmDaoRemote, InmatmDaoLocal {
	
	
	/**
     * Default constructor. 
     */
    public InmatmDao() {
        // TODO Auto-generated constructor stub
    }
    
    @Override
	public void createInmatm(Inmatm inmatm, String webAppName) {
		//getEntityManager(webAppName);
		throw new UnsupportedOperationException("Not supported yet.");
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Inmatm> getAll1(String webAppName) {
		//getEntityManager(webAppName);
		String qryStr = "select a from Inmatm a";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
        List<Inmatm> list = query.getResultList();
        return list;

	}

	@Override
	public void updateInmatm(Inmatm inmatm, String webAppName) {
		//getEntityManager(webAppName);
		throw new UnsupportedOperationException("Not supported yet.");
		
	}

	@Override
	public void removeInmatm(Inmatm inmatm, String webAppName) {
		//getEntityManager(webAppName);
		throw new UnsupportedOperationException("Not supported yet.");
		
	}

	@Override
	public void removeAll(String webAppName) {
		//getEntityManager(webAppName);
		throw new UnsupportedOperationException("Not supported yet.");
		
	}

	@Override
	public Inmatm findById(String matCd, String webAppName) throws PersistenceException {
		//getEntityManager(webAppName);
		return getEntityManager(webAppName).find(Inmatm.class, matCd);
	}

	@SuppressWarnings("unchecked")
	@Override
	public String findName(String matCd, String webAppName) throws PersistenceException {
		//getEntityManager(webAppName);
		String qryStr = "select TRIM(a.matNm) from Inmatm a WHERE TRIM(a.matCd) = :matCd";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("matCd", matCd);
		List<String> list = query.getResultList();
		if (list.isEmpty())
			return null;
        else if (list.size() == 1)
        	return list.get(0);
        throw new NonUniqueResultException();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Inmatm findMatItem(String matCd, String webAppName)  {
		//getEntityManager(webAppName);
		String qryStr = "select a from Inmatm a WHERE TRIM(a.matCd) = :matCd";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("matCd", matCd);
		List<Inmatm> list = query.getResultList();
		if (list.isEmpty())
			return null;
        else if (list.size() == 1)
        	return list.get(0);
        throw new NonUniqueResultException();
	}
	
	/*//String resType, BigDecimal resCat, String matCd,
	//String matNm, String uom, BigDecimal unitPrice, BigDecimal tolerance
	@SuppressWarnings("unchecked")
	@Override
	public List<MatInfo> getMatListByCategory(String deptId, String like, String webAppName) {
		//String qryStr = "select TRIM(a.resType), a.resCat, TRIM(a.id.matCd), TRIM(a.matNm), TRIM(a.uom), a.unitPrice, a.tolerance   from Inmatm a WHERE TRIM(a.matCd) like :like";		Query query = getEntityManager(webAppName).createQuery(qryStr);
		String qryStr = "select new inventory.model.MatInfo(TRIM(a.id.matCd), TRIM(a.matNm)) from Inmatm a WHERE TRIM(a.matCd) like :like";		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("like", like+"%");
		List<MatInfo> list = query.getResultList();
		return list;
	*/

}
