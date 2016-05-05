package estimate.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.NonUniqueResultException;

import util.emSelect.EmSelector;
import estimate.model.Spreconn;

/**
 * Session Bean implementation class SPRECONNDao
 */
@Stateless
public class SpreconnDao extends EmSelector implements SpreconnDaoRemote, SpreconnDaoLocal {
	/**
     * Default constructor. 
     */
    public SpreconnDao() {
        // TODO Auto-generated constructor stub
    }
    
    @SuppressWarnings("unchecked")
	@Override
	public Spreconn findById(long disconnectDuration, String webAppName) {
		//getEntityManager(webAppName);
		String qryStr = "select s from Spreconn s where s.id.disconnectDuration=:disconnectDuration";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("disconnectDuration", disconnectDuration);
		List<Spreconn> list = query.getResultList();
		if (list.isEmpty())
			return null;
        else if (list.size() == 1)
        	return list.get(0);
        throw new NonUniqueResultException();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Spreconn> getAll(String webAppName) {
		//getEntityManager(webAppName);
		String qryStr = "select s from Spreconn s ";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		List<Spreconn> list = query.getResultList();
		return list;
	}

}
