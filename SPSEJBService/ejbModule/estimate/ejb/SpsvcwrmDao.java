package estimate.ejb;

import inventory.ejb.InwrhmapDaoRemote;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import util.emSelect.EmSelector;
import estimate.model.MaterialGrid;
import estimate.model.Spsvcwrm;
import estimate.model.SpsvcwrmPK;


/**
 * Session Bean implementation class SpsvcwrmDao
 */
@Stateless
public class SpsvcwrmDao extends EmSelector implements SpsvcwrmDaoRemote, SpsvcwrmDaoLocal {
	
	@EJB
	InwrhmapDaoRemote inwrhmapDaoRemote;
    /**
     * Default constructor. 
     */
    public SpsvcwrmDao() {
        // TODO Auto-generated constructor stub
    }

        
	@Override
	public void createSpsvcwrm(Spsvcwrm spsvcwrm, String webAppName) {
		//getEntityManager(webAppName);
		getEntityManager(webAppName).persist(spsvcwrm);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Spsvcwrm> getAll(String webAppName) {
		//getEntityManager(webAppName);
		String qryStr = "select a from Spsvcwrm a ";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
        List<Spsvcwrm> list = query.getResultList();
        return list;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Spsvcwrm> getAll(String deptId, String webAppName) {
		//getEntityManager(webAppName);
		String qryStr = "select a from Spsvcwrm a where a.id.deptId=:deptId";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("deptId", deptId);
        List<Spsvcwrm> list = query.getResultList();
        return list;
	}

	@Override
	public void updateSpsvcwrm(Spsvcwrm spsvcwrm, String webAppName) {
		//getEntityManager(webAppName);
		getEntityManager(webAppName).merge(spsvcwrm);
		
	}

	@Override
	public void removeSpsvcwrm(Spsvcwrm spsvcwrm, String webAppName) {
		//getEntityManager(webAppName);
		getEntityManager(webAppName).remove(spsvcwrm);
		
	}

	@Override
	public void removeAll(String webAppName) {
		//getEntityManager(webAppName);
		throw new UnsupportedOperationException("Not supported yet.");
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public Spsvcwrm findById(SpsvcwrmPK id, String webAppName) throws PersistenceException {
		//getEntityManager(webAppName);
		String qryStr = "select a from Spsvcwrm a where a.id.deptId = :deptId AND a.id.phase = :phase AND  a.id.connectionType = :connectionType AND  a.id.wiringType = :wiringType AND  TRIM(a.id.matCd) = :matCd ";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("deptId", id.getDeptId());
		query.setParameter("phase", id.getPhase());
		query.setParameter("connectionType", id.getConnectionType());
		query.setParameter("wiringType", id.getWiringType());
		query.setParameter("matCd", id.getMatCd());
		List<Spsvcwrm> list = query.getResultList();
		if (list.isEmpty())
			return null;
		else if (list.size() == 1)
			return list.get(0);
		throw new NonUniqueResultException();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MaterialGrid> getServiceWireMaterialGrid(String deptId, long phase, long connectionType, String wiringType, Double serviceLength, String webAppName) {
		//getEntityManager(webAppName);
		String warehouse=inwrhmapDaoRemote.mapWarehouse(deptId, webAppName);
		System.out.println("warehouse "+warehouse);
		String qryStr = "select distinct new estimate.model.MaterialGrid (TRIM(b.id.matCd), TRIM(c.matNm), b.unitPrice, b.uomCd, a.measurementFactor, a.extraQty) " +
				"from Spsvcwrm a, Inwrhmtm b, Inmatm c " +
				"where a.id.matCd=b.id.matCd " +
				"AND b.id.matCd=c.id.matCd " +
				"AND a.id.deptId= b.id.deptId " +
				"AND b.id.deptId= c.id.deptId " +
				"AND c.id.deptId= a.id.deptId " +
				"AND b.id.deptId= :warehouse " +
				"AND a.id.deptId= :deptId " +
				"AND a.id.phase = :phase " +
				"AND  a.id.connectionType = :connectionType " +
				"AND  a.id.wiringType = :wiringType ";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("phase", phase);
		query.setParameter("connectionType", connectionType);
		query.setParameter("wiringType", wiringType);
		query.setParameter("warehouse", warehouse);
		query.setParameter("deptId", warehouse);
		List<MaterialGrid> list = query.getResultList();
		for(int i=0; i<=list.size()-1; i++){
			list.get(i).setEstimateQty(new BigDecimal ((Double.parseDouble(list.get(i).getMeasurementFactor().toString())*serviceLength)+Double.parseDouble(list.get(i).getExtraQty().toString())));
		}
		
		return list;
		
	}

	

}
