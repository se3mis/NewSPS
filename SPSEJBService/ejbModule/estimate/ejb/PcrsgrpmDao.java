package estimate.ejb;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import util.emSelect.EmSelector;

import estimate.model.Pcestdtt;
import estimate.model.PcestdttPK;
import estimate.model.Pcrsgrpm;

/**
 * Session Bean implementation class PcestdttDao
 */
@Stateless
public class PcrsgrpmDao extends EmSelector implements PcrsgrpmDaoRemote, PcrsgrpmDaoLocal {

	@Override
	public Pcrsgrpm getResourceDetails(String resourType, String code,
			String webAppName) throws PersistenceException {
			try{
				//String qryStr = "SELECT g FROM Spestmtm g WHERE g.id.wiringType = :wiringType AND g.id.phase = :phase AND g.id.connectionType = :connectionType AND g.id.fromLength = :fromLength AND g.id.toLength = :toLength";
				StringBuffer qryStr = new StringBuffer();
				qryStr.append("select pc from Pcrsgrpm pc where pc.resLvl = 1");
				if(code != null){
					qryStr.append(" and pc.resCd =:resCode");
				}
				if(resourType != null){
					qryStr.append(" and pc.resType = :resType ");
				}
				
			        
				Query query = getEntityManager(webAppName).createQuery(qryStr.toString());
				if(resourType != null){
					query.setParameter("resType", resourType);
				}
				if(code != null){
					query.setParameter("resCode", code);
				}
				List<Pcrsgrpm>  list = query.getResultList();	
				
				if(list != null && list.size() > 0){
					return list.get(0);
				}
				
			}catch(Exception e){
				e.printStackTrace();
			}
			return null;
	}
	

	
}
