package estimate.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import util.emSelect.EmSelector;
import estimate.model.Pcestdtt;
import estimate.model.SpNormsGroup;
import estimate.model.SpPegInfo;
import estimate.model.Spnorms;
import estimate.model.SpnormsPK;


/**
 * Session Bean implementation class SpugcstmDao
 */
@Stateless
public class SpnormsDao extends EmSelector implements SpnormsDaoLocal, SpnormsDaoRemote {
	
    /**
     * Default constructor. 
     */
    public SpnormsDao() {
        // TODO Auto-generated constructor stub
    }

   

	@SuppressWarnings("unchecked")
	@Override
	public List<Spnorms> getAll(String webAppName) {
		//getEntityManager(webAppName);
		String qryStr = "select a from Spnorms a order by a.id.lineSectionTypeId";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
        List<Spnorms> list = query.getResultList();
        return list;
	}
	
	
	@Override
	public void removeSpnorms(Spnorms spnorms, String webAppName) {
		//getEntityManager(webAppName);
		getEntityManager(webAppName).remove(spnorms);
		
	}

	@Override
	public void removeAll(String webAppName) {
		//getEntityManager(webAppName);
		throw new UnsupportedOperationException("Not supported yet.");
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public Spnorms findById(SpnormsPK id, String webAppName) throws PersistenceException {
		//getEntityManager(webAppName);
		return getEntityManager(webAppName).find(Spnorms.class, id);
	}



	@Override
	public void createSpnorms(Spnorms spnorms, String webAppName) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void updateSpnorms(Spnorms spnorms, String webAppName) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public List<Spnorms> getChildrensByParentId(String parentId,
			String webAppName) throws PersistenceException {
		try{
			//String qryStr = "SELECT g FROM Spestmtm g WHERE g.id.wiringType = :wiringType AND g.id.phase = :phase AND g.id.connectionType = :connectionType AND g.id.fromLength = :fromLength AND g.id.toLength = :toLength";
			StringBuffer qryStr = new StringBuffer();
			qryStr.append("SELECT norm FROM Spnorms norm WHERE 1 = 1");
			if(parentId != null){
				qryStr.append(" AND norm.parentId = :parent_id");
			}else{
				qryStr.append(" AND norm.parentId is null");
			}
			Query query = getEntityManager(webAppName).createQuery(qryStr.toString());
			if(parentId != null){
				query.setParameter("parent_id", parentId);
			}
			List<Spnorms>  list = query.getResultList();	
			
		
			return list;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}


}
