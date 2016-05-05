package job.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import util.emSelect.EmSelector;

import job.model.Pcrstypm;

/**
 * Session Bean implementation class PcrstypmDao
 */
@Stateless
public class PcrstypmDao extends EmSelector implements PcrstypmDaoRemote, PcrstypmDaoLocal {
	
    /**
     * Default constructor. 
     */
    public PcrstypmDao() {
        // TODO Auto-generated constructor stub
    }

    
    
	@Override
	public void createPcrstypm(Pcrstypm Pcrstypm, String webAppName) {
		//getEntityManager(webAppName);
		getEntityManager(webAppName).persist(Pcrstypm);
		
	}
	
	@Override
	public List<String> loadResourceTypes(String webAppName)
			throws PersistenceException {
		try{
			//String qryStr = "SELECT g FROM Spestmtm g WHERE g.id.wiringType = :wiringType AND g.id.phase = :phase AND g.id.connectionType = :connectionType AND g.id.fromLength = :fromLength AND g.id.toLength = :toLength";
			StringBuffer qryStr = new StringBuffer();
			qryStr.append("select resType from Pcrstypm where status = 2 and TRIM(resType) not like '%MAT-COST-OTHER%'");
			
			Query query = getEntityManager(webAppName).createQuery(qryStr.toString());
			//if(id != null){
				//query.setParameter("lineSectionTypeId", id);
			//}
			List<String>  list = query.getResultList();	
			
		
			return list;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public List<Pcrstypm> isResourceTypeExist(String resourType,String webAppName)
			throws PersistenceException {
		try{
			//String qryStr = "SELECT g FROM Spestmtm g WHERE g.id.wiringType = :wiringType AND g.id.phase = :phase AND g.id.connectionType = :connectionType AND g.id.fromLength = :fromLength AND g.id.toLength = :toLength";
			StringBuffer qryStr = new StringBuffer();
			qryStr.append("select pcr from Pcrstypm pcr where pcr.status = 2");
			if(resourType != null){
				qryStr.append(" and pcr.resType = :resourType");
			} 
		        
			Query query = getEntityManager(webAppName).createQuery(qryStr.toString());
			if(resourType != null){
				query.setParameter("resourType", resourType);
			}
			List<Pcrstypm>  list = query.getResultList();	
			
		
			return list;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	/*@Override
	public List<String> loadResourceCodes(String resourType,String webAppName)
			throws PersistenceException {
		try{
			//String qryStr = "SELECT g FROM Spestmtm g WHERE g.id.wiringType = :wiringType AND g.id.phase = :phase AND g.id.connectionType = :connectionType AND g.id.fromLength = :fromLength AND g.id.toLength = :toLength";
			StringBuffer qryStr = new StringBuffer();
			qryStr.append("SELECT distinct peg.id.resCd FROM Pcrstypm peg WHERE peg.resType = :resourType");
			 
			SELECT mat_cd FROM inwrhmtm where dept_id='"+drpDwnWarehouse1.getSelected() + "' and mat_cd like 'NPL%' 
			String command1 = "select * from pcrsgrpm where res_type = " + "'" + resVal  + "'" + " and res_lvl = 1" ;
			command = command1 + "and res_cd =" +"'" + txtResCode + "'" ;
             
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
}
