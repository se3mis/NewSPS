package estimate.ejb;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import util.emSelect.EmSelector;

import estimate.model.Approval;
import estimate.model.SpPegInfo;
import estimate.model.SpPegInfoPK;
import estimate.model.SpPointdmt;
import estimate.model.SpPointdmtPK;
import estimate.model.Spnorms;
import estimate.model.SpnormsPK;

/**
 * Author: Dinusha Nirmalie
 * Created: April 18, 2012 11:23:42 AM
 */

@Stateless
public class SpPointdmtDao extends EmSelector implements SpPointdmtDaoRemote, SpPointdmtDaoLocal {


	@Override
	public List<SpPointdmt> getAll(String webAppName) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void createSpPointdmt(SpPointdmt spPointdmt, String webAppName)throws PersistenceException {
		// TODO Auto-generated method stub
		getEntityManager(webAppName).persist(spPointdmt);
	}
	@Override
	public void updateSpPointdmt(SpPointdmt spPointdmt, String webAppName) throws PersistenceException{
		getEntityManager(webAppName).merge(spPointdmt);
		
	}

	@Override
	public void removeSpPointdmt(SpPointdmt spPointdmt, String webAppName) throws PersistenceException{
		getEntityManager(webAppName).remove(spPointdmt);
		
	}
	@Override
	public void updateSpPointdmt(String lineTypeId, String resCode, BigDecimal quantity, String masterId, String webAppName) throws PersistenceException{
		
			//String qryStr = "SELECT g FROM Spestmtm g WHERE g.id.wiringType = :wiringType AND g.id.phase = :phase AND g.id.connectionType = :connectionType AND g.id.fromLength = :fromLength AND g.id.toLength = :toLength";
			StringBuffer qryStr = new StringBuffer();
			qryStr.append("UPDATE SpPointdmt peg  set peg.estimatedQuantity=:quantity  WHERE TRIM(peg.id.lineSectionTypeId) = :lineTypeId AND TRIM(peg.id.resCd)=:resCode AND TRIM(peg.id.masterId)=:masterId");
			
			Query query = getEntityManager(webAppName).createQuery(qryStr.toString());
			
			query.setParameter("lineTypeId", lineTypeId);
			query.setParameter("resCode", resCode);
			query.setParameter("quantity", quantity);
			query.setParameter("masterId", masterId);
			query.executeUpdate();
			
		
		
		
	}

	@Override
	public void removeSpPointdmt(String lineTypeId, String resCode, String masterId, String webAppName)throws PersistenceException {
		
			StringBuffer qryStr = new StringBuffer();
			qryStr.append("DELETE SpPointdmt peg WHERE TRIM(peg.id.lineSectionTypeId) = :lineTypeId AND TRIM(peg.id.resCd)=:resCode AND TRIM(peg.id.masterId)=:masterId");
			
			Query query = getEntityManager(webAppName).createQuery(qryStr.toString());
			
			query.setParameter("lineTypeId", lineTypeId.trim());
			query.setParameter("resCode", resCode.trim());
			query.setParameter("masterId", masterId.trim());
			query.executeUpdate();

		
	}
	@Override
	public List<SpPointdmt> getPegResourceById(String id,String masterId, String webAppName)
			throws PersistenceException {
		try{
			//String qryStr = "SELECT g FROM Spestmtm g WHERE g.id.wiringType = :wiringType AND g.id.phase = :phase AND g.id.connectionType = :connectionType AND g.id.fromLength = :fromLength AND g.id.toLength = :toLength";
			StringBuffer qryStr = new StringBuffer();
			qryStr.append("SELECT peg FROM SpPointdmt peg WHERE LINESECTIONTYPEID = :lineSectionTypeId AND TRIM(peg.id.masterId)=:masterId");
			
			Query query = getEntityManager(webAppName).createQuery(qryStr.toString());
			if(id != null){
				query.setParameter("lineSectionTypeId", id);
			}
			query.setParameter("masterId", masterId);
			List<SpPointdmt>  list = query.getResultList();	
			
		
			return list;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public SpPointdmt findById(SpPointdmtPK id, String webAppName)
			throws PersistenceException {
		return getEntityManager(webAppName).find(SpPointdmt.class, id);
	}
	

	/*@Override
	public List<String> loadResourceCodes(String resourType,String webAppName)
			throws PersistenceException {
		try{
			//String qryStr = "SELECT g FROM Spestmtm g WHERE g.id.wiringType = :wiringType AND g.id.phase = :phase AND g.id.connectionType = :connectionType AND g.id.fromLength = :fromLength AND g.id.toLength = :toLength";
			StringBuffer qryStr = new StringBuffer();
			qryStr.append("SELECT distinct peg.id.resCd FROM SpPointdmt peg WHERE peg.resType = :resourType");
			
			Query query = getEntityManager(webAppName).createQuery(qryStr.toString());
			if(resourType != null){
				query.setParameter("resourType", resourType);
			}
			List<String>  list = query.getResultList();	
			
		
			return list;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}*/
	/*@Override
	public SpPointdmt loadResourceDetails(String resourType,String code,String webAppName)
			throws PersistenceException {
		try{
			//String qryStr = "SELECT g FROM Spestmtm g WHERE g.id.wiringType = :wiringType AND g.id.phase = :phase AND g.id.connectionType = :connectionType AND g.id.fromLength = :fromLength AND g.id.toLength = :toLength";
			StringBuffer qryStr = new StringBuffer();
			qryStr.append("SELECT peg FROM SpPointdmt peg WHERE peg.id.resCd = :code and peg.resType = :resourType");
			
			Query query = getEntityManager(webAppName).createQuery(qryStr.toString());
			if(resourType != null){
				query.setParameter("resourType", resourType);
				query.setParameter("code", code);
			}
			List<SpPointdmt>  list = query.getResultList();	
			if(list != null && list.size() > 0){
				return list.get(0);
			}
		
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}*/
}
