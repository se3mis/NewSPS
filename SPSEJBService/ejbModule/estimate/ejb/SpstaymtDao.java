package estimate.ejb;

import inventory.ejb.InwrhmapDaoRemote;


import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import util.emSelect.EmSelector;
import estimate.model.Spstaymt;
import estimate.model.SpstaymtPK;

/**
 * Session Bean implementation class SpstaymtDao
 */
@Stateless
public class SpstaymtDao extends EmSelector implements SpstaymtDaoRemote, SpstaymtDaoLocal {
	
	@EJB
	InwrhmapDaoRemote inwrhmapDaoRemote;
    /**
     * Default constructor. 
     */
    public SpstaymtDao() {
        // TODO Auto-generated constructor stub
    }
    
    @Override
	public void createSpstaymt(Spstaymt spstaymt, String webAppName) {
		//getEntityManager(webAppName);
		getEntityManager(webAppName).persist(spstaymt);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Spstaymt> getAll(String webAppName) {
		//getEntityManager(webAppName);
		String qryStr = "select a from Spstaymt a ";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		List<Spstaymt> list = query.getResultList();
		return list;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Spstaymt> getAll(String deptId,String webAppName) {
		//getEntityManager(webAppName);
		String qryStr = "select a from Spstaymt a where a.id.deptId=:deptId";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("deptId", deptId);
		List<Spstaymt> list = query.getResultList();
		return list;
	}

	@Override
	public void updateSpstaymt(Spstaymt spstaymt, String webAppName) {
		//getEntityManager(webAppName);
		getEntityManager(webAppName).merge(spstaymt);
		
	}

	@Override
	public void removeSpstaymt(Spstaymt spstaymt, String webAppName) {
		//getEntityManager(webAppName);
		getEntityManager(webAppName).remove(spstaymt);
		
	}

	@Override
	public void removeAll(String webAppName) {
		//getEntityManager(webAppName);
		throw new UnsupportedOperationException("Not supported yet.");
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public Spstaymt findById(SpstaymtPK id, String webAppName) throws PersistenceException {
		//getEntityManager(webAppName);
		String qryStr = "select a from Spstaymt a where a.id.deptId=:deptId and trim(a.id.matCd) = :matCd ";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("deptId", id.getDeptId());
		query.setParameter("matCd", id.getMatCd());
		List<Spstaymt> list = query.getResultList();
		if (list.isEmpty())
			return null;
		else if (list.size() == 1)
			return list.get(0);
		throw new NonUniqueResultException();
	}

	/*@SuppressWarnings("unchecked")
	@Override
	public List<MaterialGrid> getStayMaterialGrid(String deptId, int noOfStays, String webAppName) {
		String warehouse=inwrhmapDaoRemote.mapWarehouse(deptId);
		System.out.println(warehouse);
		
		String qryStr = "select new estimate.model.MaterialGrid (TRIM(b.id.matCd), TRIM(c.matNm), b.unitPrice, b.uomCd ) " +
		"from Spstaymt a, Inwrhmtm b, Inmatm c " +
		"where a.id.matCd=b.id.matCd " +
		"AND b.id.matCd=c.id.matCd " +
		"AND b.id.deptId= :warehouse "+
		"AND a.id.deptId= :deptId ";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("warehouse", warehouse);
		query.setParameter("deptId", deptId);
		List<MaterialGrid> list = query.getResultList();
		for(int i=0; i<=list.size()-1; i++){
			list.get(i).setEstimateQty(new BigDecimal (Double.parseDouble(list.get(i).getEstimateQty().toString())*Double.parseDouble(String.valueOf(noOfStays))));
			list.get(i).setEstimateCost(new BigDecimal (Double.parseDouble(list.get(i).getEstimateQty().toString())*Double.parseDouble(list.get(i).getUnitPrice().toString())));
		}
		return list;
	}*/

}
