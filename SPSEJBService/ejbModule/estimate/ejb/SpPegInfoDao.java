package estimate.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import util.emSelect.EmSelector;

import estimate.model.Approval;
import estimate.model.SpPegInfo;
import estimate.model.SpPegInfoPK;
import estimate.model.Spnorms;
import estimate.model.SpnormsPK;
import estimate.model.Spstdesthmt;
import estimate.model.SpstdesthmtPK;

/**
 * Author: Dinusha Nirmalie
 * Created: March 01, 2012 11:23:42 AM
 */

@Stateless
public class SpPegInfoDao extends EmSelector implements SpPegInfoDaoRemote, SpPegInfoDaoLocal {

	 public SpPegInfoDao() {
	        // TODO Auto-generated constructor stub
	  }
	@Override
	public void createSpPegInfo(SpPegInfo spPegInfo, String webAppName) throws PersistenceException{
		// TODO Auto-generated method stub
		getEntityManager(webAppName).persist(spPegInfo);
	}

	@Override
	public List<SpPegInfo> getAll(String webAppName) {
		String qryStr ="select a from SpPegInfo a";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
     List<SpPegInfo> list = query.getResultList();
     return list;
	}

	@Override
	public void updateSpPegInfo(SpPegInfo spPegInfo, String webAppName) {
		getEntityManager(webAppName).merge(spPegInfo);
		
	}

	@Override
	public void removeSpPegInfo(SpPegInfo spPegInfo, String webAppName) {
		getEntityManager(webAppName).remove(spPegInfo);
		
	}

	@Override
	public void removeAll(String webAppName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public SpPegInfo findById(SpPegInfoPK id, String webAppName)
			throws PersistenceException {
		return getEntityManager(webAppName).find(SpPegInfo.class, id);
	}
	
	@Override
	public List<SpPegInfo> getPegChildrensByParentId(String parentId,String masterId,
			String webAppName) throws PersistenceException {
		try{
			//String qryStr = "SELECT g FROM Spestmtm g WHERE g.id.wiringType = :wiringType AND g.id.phase = :phase AND g.id.connectionType = :connectionType AND g.id.fromLength = :fromLength AND g.id.toLength = :toLength";
			StringBuffer qryStr = new StringBuffer();
			qryStr.append("SELECT peg FROM SpPegInfo peg WHERE 1 = 1 AND TRIM(peg.id.masterId)=:masterId ");
			if(parentId != null){
				qryStr.append(" AND peg.id.parent_id = :parent_id");
			}else{
				qryStr.append(" AND peg.id.parent_id is null");
			}
			Query query = getEntityManager(webAppName).createQuery(qryStr.toString());
			if(parentId != null){
				query.setParameter("parent_id", parentId);
			}
			query.setParameter("masterId", masterId);
			List<SpPegInfo>  list = query.getResultList();	
			
		
			return list;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public void updateDescription(String id,String name,String masterId,
			String webAppName) throws PersistenceException {
		try{
			//String qryStr = "SELECT g FROM Spestmtm g WHERE g.id.wiringType = :wiringType AND g.id.phase = :phase AND g.id.connectionType = :connectionType AND g.id.fromLength = :fromLength AND g.id.toLength = :toLength";
			StringBuffer qryStr = new StringBuffer();
			qryStr.append("update SpPegInfo peg set peg.name = :name WHERE  TRIM(peg.id.pegItemId) = :id AND TRIM(peg.id.masterId)=:masterId ");
			
			Query query = getEntityManager(webAppName).createQuery(qryStr.toString());
			
			query.setParameter("id", id.trim());
			query.setParameter("name", name);
			query.setParameter("masterId", masterId);
			query.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

	@Override
	public void deletePegItem(String id,String masterId,String webAppName) throws PersistenceException {
		try{
			//String qryStr = "SELECT g FROM Spestmtm g WHERE g.id.wiringType = :wiringType AND g.id.phase = :phase AND g.id.connectionType = :connectionType AND g.id.fromLength = :fromLength AND g.id.toLength = :toLength";
			StringBuffer qryStr = new StringBuffer();
			qryStr.append("delete from SpPegInfo peg WHERE   TRIM(peg.id.pegItemId) = :id AND TRIM(peg.id.masterId)=:masterId ");
			
			Query query = getEntityManager(webAppName).createQuery(qryStr.toString());
			
			query.setParameter("id", id.trim());
			query.setParameter("masterId", masterId.trim());
			query.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

   
}
