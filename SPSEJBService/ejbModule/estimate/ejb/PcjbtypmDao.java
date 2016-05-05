package estimate.ejb;

import inventory.ejb.InwrhmapDaoRemote;
import inventory.ejb.InwrhmtmDaoRemote;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import util.emSelect.EmSelector;
import estimate.model.Pcjbtypm;
import estimate.model.PcjbtypmPK;

/**
 * Session Bean implementation class PcjbtypmDao
 */
@Stateless
public class PcjbtypmDao extends EmSelector implements PcjbtypmDaoRemote, PcjbtypmDaoLocal {
	@Resource
	private SessionContext context;
	@EJB
	InwrhmapDaoRemote inwrhmapDaoRemote;

	
    /**
     * Default constructor. 
     */
    public PcjbtypmDao() {
        // TODO Auto-generated constructor stub
    }
    
   

	@Override
	public void createPcjbtypm(Pcjbtypm pcjbtypm, String webAppName) {
		//getEntityManager(webAppName);
		getEntityManager(webAppName).persist(pcjbtypm);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Pcjbtypm> getAll(String webAppName) {
		//getEntityManager(webAppName);
		String qryStr = "select a from Pcjbtypm a";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
        List<Pcjbtypm> list = query.getResultList();
        return list;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Pcjbtypm> getAll(String deptId, String webAppName) {
		//getEntityManager(webAppName);
		String qryStr = "SELECT g FROM Pcjbtypm g WHERE g.id.deptId = :deptId";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("deptId", deptId);
		List<Pcjbtypm> list = query.getResultList();		
		return list;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<String> getCatCode(String warehouse, String webAppName) {
		//getEntityManager(webAppName);
		//String warehouse=inwrhmapDaoRemote.mapWarehouse(deptId, webAppName);
		String qryStr = "SELECT TRIM(g.id.catCd) FROM Pcjbtypm g WHERE g.id.deptId = :deptId";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("deptId", warehouse);
		List<String> list = query.getResultList();		
		return list;
	}
	

	@Override
	public void updatePcjbtypm(Pcjbtypm pcjbtypm, String webAppName) {
		//getEntityManager(webAppName);
		getEntityManager(webAppName).merge(pcjbtypm);
		
	}

	@Override
	public void removePcjbtypm(Pcjbtypm pcjbtypm, String webAppName) {
		//getEntityManager(webAppName);
		//getEntityManager(webAppName).remove(pcjbtypm);
		throw new UnsupportedOperationException("Not supported yet.");
		
	}

	@Override
	public void removeAll(String webAppName) {
		//getEntityManager(webAppName);
		throw new UnsupportedOperationException("Not supported yet.");
		
	}

	@Override
	public Pcjbtypm findById(PcjbtypmPK id, String webAppName) throws PersistenceException {
		//getEntityManager(webAppName);
		return getEntityManager(webAppName).find(Pcjbtypm.class, id);
	}

}
