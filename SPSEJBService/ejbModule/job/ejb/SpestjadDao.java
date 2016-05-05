package job.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;
import util.emSelect.EmSelector;
import job.model.Spestjad;
import job.model.SpestjadPK;

/**
 * Session Bean implementation class SpestjadDao
 */
@Stateless
public class SpestjadDao extends EmSelector implements SpestjadDaoRemote, SpestjadDaoLocal {

	
    /**
     * Default constructor. 
     */
    public SpestjadDao() {
        // TODO Auto-generated constructor stub
    }
    
    
	@SuppressWarnings("unchecked")
	@Override
	public List<Spestjad> getAll(String deptId, String webAppName) {
		//getEntityManager(webAppName);
		String qryStr = "select jad from Spestjad jad WHERE jad.id.deptId =:deptId";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("deptId", deptId);
		List<Spestjad> list = query.getResultList();
        return list;
	}

	@Override
	public Spestjad findById(SpestjadPK id, String webAppName) {
		//getEntityManager(webAppName);
		return getEntityManager(webAppName).find(Spestjad.class, id);
	}

}
