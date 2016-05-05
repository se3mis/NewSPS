package job.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import util.emSelect.EmSelector;
import job.model.Spestbip;
import job.model.SpestbipPK;

/**
 * Session Bean implementation class SpestbipDao
 */
@Stateless
public class SpestbipDao extends EmSelector implements SpestbipDaoRemote, SpestbipDaoLocal {
	
    /**
     * Default constructor. 
     */
    public SpestbipDao() {
        // TODO Auto-generated constructor stub
    }
    
   

	@Override
	public void createSpestbip(Spestbip spestbip, String webAppName) {
		//getEntityManager(webAppName);
		getEntityManager(webAppName).persist(spestbip);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Spestbip> getAll(String deptId, String webAppName) {
		//getEntityManager(webAppName);
		String qryStr = "select a from Spestbip a WHERE a.id.deptId = :deptId";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		//Query query = getEntityManager(webAppName).createQuery("select a from Spestcnd a");
		query.setParameter("deptId", deptId);
		List<Spestbip> list = query.getResultList();
	    return list;
	}

	@Override
	public void updateSpestbip(Spestbip spestbip, String webAppName) {
		//getEntityManager(webAppName);
		getEntityManager(webAppName).merge(spestbip);
		
	}

	@Override
	public void removeSpestbip(Spestbip spestbip, String webAppName) {
		//getEntityManager(webAppName);
		spestbip=getEntityManager(webAppName).merge(spestbip);
		getEntityManager(webAppName).remove(spestbip);
		
	}

	@Override
	public void removeAll(String webAppName) {
		//getEntityManager(webAppName);
		throw new UnsupportedOperationException("Not supported yet. Ask Dileepa");
		
	}

	@Override
	public Spestbip findById(SpestbipPK id, String webAppName) throws PersistenceException {
		//getEntityManager(webAppName);
		return getEntityManager(webAppName).find(Spestbip.class, id);
	}

}
