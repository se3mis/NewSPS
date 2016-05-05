package offDoc.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import offDoc.model.LetterReason;
import offDoc.model.LetterReasonPK;
import util.emSelect.EmSelector;

/**
 * Session Bean implementation class OffDocsBean
 */
@Stateless
public class OffDocsBean extends EmSelector implements OffDocsBeanRemote, OffDocsBeanLocal {
	
    /**
     * Default constructor. 
     */
    public OffDocsBean() {
        // TODO Auto-generated constructor stub
    }
    
    

	@Override
	public void createLetterReason(LetterReason letterReason, String webAppName) {
		//getEntityManager(webAppName);
		getEntityManager(webAppName).persist(letterReason);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LetterReason> getAll(String deptId, String webAppName) {
		//getEntityManager(webAppName);
		Query query = getEntityManager(webAppName).createQuery("select l from LetterReason l WHERE l.id.deptId=:deptId");
		query.setParameter("deptId", deptId);
		List<LetterReason> list = query.getResultList();
        return list;

	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<LetterReason> getReasons(String deptId, String letterType, String webAppName) {
		//getEntityManager(webAppName);
		Query query = getEntityManager(webAppName).createQuery("select l from LetterReason l WHERE l.id.deptId=:deptId AND l.id.letterType=:letterType");
		query.setParameter("deptId", deptId);
		query.setParameter("letterType", letterType);
		List<LetterReason> list = query.getResultList();
        return list;

	}

	@Override
	public void updateLetterReason(LetterReason letterReason, String webAppName) {
		//getEntityManager(webAppName);
		getEntityManager(webAppName).merge(letterReason);
		
	}

	@Override
	public void removeLetterReason(LetterReason letterReason, String webAppName) {
		//getEntityManager(webAppName);
		letterReason=getEntityManager(webAppName).merge(letterReason);
		getEntityManager(webAppName).remove(letterReason);
		
	}

	@Override
	public void removeAll(String webAppName) {
		//getEntityManager(webAppName);
		throw new UnsupportedOperationException("Not supported yet.");
		
	}

	@Override
	public LetterReason findById(LetterReasonPK id, String webAppName) throws PersistenceException {
		//getEntityManager(webAppName);
		return getEntityManager(webAppName).find(LetterReason.class, id);
	}

}
