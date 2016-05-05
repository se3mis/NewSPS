package estimate.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import util.emSelect.EmSelector;
import estimate.model.Spcrconv;
import estimate.model.SpcrconvPK;

/**
 * Session Bean implementation class SpcrconvDao
 */
@Stateless
public class SpcrconvDao extends EmSelector implements SpcrconvDaoRemote, SpcrconvDaoLocal {
	
	
    /**
     * Default constructor. 
     */
    public SpcrconvDao() {
        // TODO Auto-generated constructor stub
    }
    
    

	@SuppressWarnings("unchecked")
	@Override
	public Spcrconv findById(SpcrconvPK id, String webAppName) {
		//getEntityManager(webAppName);
		String qryStr = "select s from Spcrconv s where s.id.fromPhase=:fromPhase AND s.id.toPhase=:toPhase AND s.id.fromConnectionType=:fromConnectionType AND s.id.toConnectionType=:toConnectionType";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("fromPhase", id.getFromPhase());
		query.setParameter("toPhase", id.getToPhase());
		query.setParameter("fromConnectionType", id.getFromConnectionType());
		query.setParameter("toConnectionType", id.getToConnectionType());
		List<Spcrconv> list = query.getResultList();
		if (list.isEmpty())
			return null;
        else if (list.size() == 1)
        	return list.get(0);
        throw new NonUniqueResultException();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Spcrconv> getAll(String webAppName) {
		//getEntityManager(webAppName);
		String qryStr = "select s from Spcrconv s ";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		List<Spcrconv> list = query.getResultList();
		return list;
	}

}
