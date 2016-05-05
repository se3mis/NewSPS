package estimate.ejb;

import inventory.ejb.InwrhmapDaoRemote;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import util.emSelect.EmSelector;
import estimate.model.MaterialGrid;
import estimate.model.Spcndmat;
import estimate.model.SpcndmatPK;

/**
 * Session Bean implementation class SpcndmatDao
 */
@Stateless
public class SpcndmatDao extends EmSelector implements SpcndmatDaoRemote, SpcndmatDaoLocal {
		
	@EJB
	InwrhmapDaoRemote inwrhmapDaoRemote;
    /**
     * Default constructor. 
     */
    public SpcndmatDao() {
        // TODO Auto-generated constructor stub
    }
    
    

	@Override
	public void createSpcndmat(Spcndmat spcndmat, String webAppName) {
		//getEntityManager(webAppName);
		getEntityManager(webAppName).persist(spcndmat);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Spcndmat> getAll(String webAppName) {
		//getEntityManager(webAppName);
		String qryStr = "select a from Spcndmat a ";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		List<Spcndmat> list = query.getResultList();
		return list;
	}

	@Override
	public void updateSpcndmat(Spcndmat spcndmat, String webAppName) {
		//getEntityManager(webAppName);
		getEntityManager(webAppName).merge(spcndmat);
		
	}

	@Override
	public void removeSpcndmat(Spcndmat spcndmat, String webAppName) {
		//getEntityManager(webAppName);
		getEntityManager(webAppName).remove(spcndmat);
		
	}

	@Override
	public void removeAll(String webAppName) {
		//getEntityManager(webAppName);
		throw new UnsupportedOperationException("Not supported yet.");
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public Spcndmat findById(SpcndmatPK id, String webAppName) throws PersistenceException {
		//getEntityManager(webAppName);
		String qryStr = "select a from Spcndmat a where a.id.deptId = :deptId AND a.id.phase = :phase AND  a.id.connectionType = :connectionType AND  a.id.wiringType = :wiringType AND  a.id.conductorType = :conductorType AND  TRIM(a.id.matCd) = :matCd ";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("deptId", id.getDeptId());
		query.setParameter("phase", id.getPhase());
		query.setParameter("connectionType", id.getConnectionType());
		query.setParameter("wiringType", id.getWiringType());
		query.setParameter("conductorType", id.getConductorType());
		query.setParameter("matCd", id.getMatCd());
		List<Spcndmat> list = query.getResultList();
		if (list.isEmpty())
			return null;
		else if (list.size() == 1)
			return list.get(0);
		throw new NonUniqueResultException();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<MaterialGrid> getConductorMaterialGrid(String deptId,long phase, long connectionType, String wiringType, String conductorType, Double conductorLength, String webAppName) throws PersistenceException {
		//getEntityManager(webAppName);
		String warehouse=inwrhmapDaoRemote.mapWarehouse(deptId, webAppName);
		String qryStr = "select new estimate.model.MaterialGrid (TRIM(b.id.matCd), TRIM(c.matNm), b.unitPrice, b.uomCd ,a.measurementFactor) " +
				"from Spcndmat a, Inwrhmtm b, Inmatm c " +
				"where a.id.matCd=b.id.matCd " +
				"AND a.id.matCd=c.id.matCd " +
				"AND b.id.deptId= :deptId " +
				"AND a.id.phase = :phase " +
				"AND  a.id.connectionType = :connectionType " +
				"AND  a.id.wiringType = :wiringType " +
				"AND  a.id.conductorType = :conductorType";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("deptId", warehouse);
		query.setParameter("phase", phase);
		query.setParameter("connectionType", connectionType);
		query.setParameter("wiringType", wiringType);
		query.setParameter("conductorType", conductorType);
		List<MaterialGrid> list = query.getResultList();
		//return list;
		for(int i=0; i<=list.size()-1; i++){
			list.get(i).setEstimateQty(new BigDecimal (Double.parseDouble(list.get(i).getMeasurementFactor().toString())*conductorLength));
			list.get(i).setEstimateCost(new BigDecimal (Double.parseDouble(list.get(i).getEstimateQty().toString())*Double.parseDouble(list.get(i).getUnitPrice().toString())));
		}
				
		return list;
		
	}
	

}
