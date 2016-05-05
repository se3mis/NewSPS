package consumer.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;

import util.emSelect.EmSelector;

import application.model.Application;

import consumer.model.Consumer;

/**
 * Session Bean implementation class ConsumerDao
 */
@Stateless
public class ConsumerDao extends EmSelector implements ConsumerDaoRemote, ConsumerDaoLocal {

    /**
     * Default constructor. 
     */
    public ConsumerDao() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void update(String ccc) {
		// TODO Auto-generated method stub
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public Consumer findApplicationRefNo(String applicationRefNo, String deptId, String webAppName) {
		String qryStr = "SELECT a FROM Consumer a WHERE a.id.applicationRefNo = :applicationRefNo AND TRIM(a.id.deptId)=:deptId ";
        Query query = getEntityManager(webAppName).createQuery(qryStr);
        query.setParameter("applicationRefNo", applicationRefNo);
        query.setParameter("deptId", deptId);
        List<Consumer> list = query.getResultList();
        if (list.isEmpty())
			return null;
        else if (list.size() == 1)
        	return list.get(0);
        throw new NonUniqueResultException();
	}

}
