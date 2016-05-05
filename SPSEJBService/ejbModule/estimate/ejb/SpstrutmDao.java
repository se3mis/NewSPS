package estimate.ejb;

import inventory.ejb.InwrhmapDaoRemote;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import util.emSelect.EmSelector;
import estimate.model.Spstrutm;
import estimate.model.SpstrutmPK;

/**
 * Session Bean implementation class SpstrutmDao
 */
@Stateless
public class SpstrutmDao extends EmSelector implements SpstrutmDaoRemote, SpstrutmDaoLocal {
	@EJB
	InwrhmapDaoRemote inwrhmapDaoRemote;
    /**
     * Default constructor. 
     */
    public SpstrutmDao() {
        // TODO Auto-generated constructor stub
    }

    @Override
	public void createSpstrutm(Spstrutm spstrutm, String webAppName) {
		//getEntityManager(webAppName);
		getEntityManager(webAppName).persist(spstrutm);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Spstrutm> getAll(String webAppName) {
		//getEntityManager(webAppName);
		String qryStr = "select a from Spstrutm a ";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		List<Spstrutm> list = query.getResultList();
		return list;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Spstrutm> getAll(String deptId,String webAppName) {
		//getEntityManager(webAppName);
		String qryStr = "select a from Spstrutm a where a.id.deptId=:deptId";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("deptId", deptId);
		List<Spstrutm> list = query.getResultList();
		return list;
	}

	@Override
	public void updateSpstrutm(Spstrutm spstrutm, String webAppName) {
		//getEntityManager(webAppName);
		getEntityManager(webAppName).merge(spstrutm);
		
	}

	@Override
	public void removeSpstrutm(Spstrutm spstrutm, String webAppName) {
		//getEntityManager(webAppName);
		getEntityManager(webAppName).remove(spstrutm);
		
	}

	@Override
	public void removeAll(String webAppName) {
		//getEntityManager(webAppName);
		throw new UnsupportedOperationException("Not supported yet.");
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public Spstrutm findById(SpstrutmPK id, String webAppName) throws PersistenceException {
		//getEntityManager(webAppName);
		String qryStr = "select a from Spstrutm a where a.id.deptId=:deptId and TRIM(a.id.matCd) = :matCd ";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("deptId", id.getDeptId());
		query.setParameter("matCd", id.getMatCd());
		List<Spstrutm> list = query.getResultList();
		if (list.isEmpty())
			return null;
		else if (list.size() == 1)
			return list.get(0);
		throw new NonUniqueResultException();

	}

	

	
	/*@SuppressWarnings({ "unchecked" })
	@Override
	public List<MaterialGrid> getStrutMaterialGrid(String deptId, int noOfStruts, String webAppName) {
		String warehouse=inwrhmapDaoRemote.mapWarehouse(deptId);
		System.out.println(warehouse);
		
		String qryStr = "select new estimate.model.MaterialGrid (TRIM(b.id.matCd), TRIM(c.matNm), b.unitPrice, b.uomCd ) " +
		"from Spstrutm a, Inwrhmtm b, Inmatm c " +
		"where a.id.matCd=b.id.matCd " +
		"AND a.id.matCd=c.id.matCd " +
		"AND b.id.deptId= :warehouse "+
		"AND a.id.deptId= :deptId ";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("warehouse", warehouse);
		query.setParameter("deptId", deptId);
		List<MaterialGrid> list = query.getResultList();
		for(int i=0; i<=list.size()-1; i++){
			list.get(i).setEstimateQty(new BigDecimal (Double.parseDouble(list.get(i).getEstimateQty().toString())*Double.parseDouble(String.valueOf(noOfStruts))));
			list.get(i).setEstimateCost(new BigDecimal (Double.parseDouble(list.get(i).getEstimateQty().toString())*Double.parseDouble(list.get(i).getUnitPrice().toString())));
		}
		return list;
	}*/

}
