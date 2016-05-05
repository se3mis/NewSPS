package estimate.ejb;


import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import application.model.Application;
import application.model.ApplicationReference;
import util.emSelect.EmSelector;
import estimate.model.AllocationSummary;
import estimate.model.AllocationSummaryDisplay;
import estimate.model.Pcestdtt;
import estimate.model.Spstaymt;
import estimate.model.SpstaymtPK;
import estimate.model.Spstdestdmt;
import estimate.model.Spstdesthmt;

/**
 * Session Bean implementation class SpstaymtDao
 */
@Stateless
public class SpstdestdmtDao extends EmSelector implements SpstdestdmtDaoRemote, SpstdestdmtDaoLocal {
	private Log LOGGER = LogFactory.getLog(SpstdestdmtDao.class);
	@Override
	public void createSpstdestdmt(Spstdestdmt spstdestdmt, String webAppName) {
		// TODO Auto-generated method stub
		getEntityManager(webAppName).persist(spstdestdmt);
	}

	@Override
	public List<Spstdestdmt> getAll(String webAppName) {
		String qryStr ="select a from Spstdestdmt a";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
        List<Spstdestdmt> list = query.getResultList();
        return list;
	}

	@Override
	public void updateSpstdestdmt(Spstdestdmt spstdestdmt, String webAppName) {
		getEntityManager(webAppName).merge(spstdestdmt);
		
	}

	@Override
	public void removeSpstdestdmt(Spstdestdmt spstdestdmt, String webAppName) {
		//getEntityManager(webAppName).removeSpstdestdmt(spstdestdmt, webAppName);
		//spstdestdmt=getEntityManager(webAppName).merge(spstdestdmt);
		getEntityManager(webAppName).remove(spstdestdmt);
	}

	@Override
	public void removeAll(String webAppName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Spstdestdmt findById(Spstdestdmt id, String webAppName)
			throws PersistenceException {
		return getEntityManager(webAppName).find(Spstdestdmt.class, id);
		
	}

	@Override
	public List<Spstdestdmt> findByApplicationNo(String applicationNo, String webAppName)
			throws PersistenceException {
				
		System.out.println("#############################################7");
		try{
				String qryStr = "SELECT spsdmt FROM Spstdestdmt spsdmt WHERE spsdmt.id.applicationNo = :applicationNo ";
	            Query query = getEntityManager(webAppName).createQuery(qryStr);
	            query.setParameter("applicationNo", applicationNo);
	            List<Spstdestdmt> list = query.getResultList();
	            if (list.isEmpty()){
	    			return null;
	            }
	            else{
	            	return list;
	            }
	          
			
			
		}catch (Exception e){
			LOGGER.info("Exception Occured in findByApplicationNo" + e);
			e.printStackTrace();
		}
		return null;
		
	
	}
	@Override
	public List<AllocationSummaryDisplay> findEstimateSummary(String applicationNo,String deptId, String webAppName)
			throws PersistenceException {
				
		System.out.println("#############################################7");
		try{
			StringBuffer buff = new StringBuffer();
			buff.append("SELECT new estimate.model.AllocationSummaryDisplay(TRIM(p.id.stdNo), p.id.lineType, ");
			buff.append("(select g.lineSummaryId from SpNormsGroup g,Spnorms sp where sp.parentId=g.sectionTypeId AND sp.id.lineSectionTypeId = p.id.lineType) as lineSummaryId, p.deptId,  s.entryDate,  con.fundSource,s.description, con.ServiceDepoName,p.length,p.estmateCost) ");
			buff.append("FROM Spstdestdmt p, Spstdesthmt s, ApplicationReference a, WiringLandDetailCon con ");
			buff.append("WHERE TRIM(con.id.applicationId)=a.id.applicationId AND  TRIM(p.id.stdNo)=TRIM(s.id.stdNo) AND ");
			
			buff.append("TRIM(p.id.stdNo)=a.applicationNo AND trim(p.deptId)= trim(a.id.deptId) AND trim(con.id.deptId)= trim(a.id.deptId) AND trim(p.deptId)= :deptId AND  p.id.stdNo = :stdNo");
			
			/*String qryStr = "SELECT new estimate.model.AllocationSummaryDisplay(TRIM(p.id.stdNo), p.id.lineType, 
					(select lineSummaryId from SpnormsGroup g,Spnorms sp where sp.parentId=g.sectionTypeId AND sp.id.lineSectionTypeId = p.id.lineType) as lineSummaryId, p.id.deptId,  s.entryDate,  con.fundSource,s.description, con.ServiceDepoName,p.estimateQty,p.estimateCost) 
			FROM Spstdestdmt p, Spstdesthmt s, ApplicationReference a, WiringLandDetailCon con 
					WHERE TRIM(con.id.applicationId)=a.id.applicationId AND  TRIM(p.id.stdNo)=TRIM(s.id.stdNo) AND
			TRIM(p.id.stdNo)=a.applicationNo AND trim(p.id.deptId)= trim(a.id.deptId) AND trim(con.id.deptId)= trim(a.id.deptId) AND trim(p.id.deptId)= :deptId AND  p.id.stdNo = :stdNo";
				*/	
			/*select 
			 p.std_no,(select LINESUMMARYID from SpnormsGroup g,Spnorms sp where sp.lineparentId=g.sectiontypeid and sp.linesectiontypeid = p.line_type  ) as lineSumamryId

			 from Spstdestdmt p, Application_Reference a, Wiring_Land_Detail_Con con ,Spstdesthmt s
			where a.application_no=p.std_no and a.application_id=con.application_id and a.dept_id=p.dept_id and a.dept_id=con.dept_id and s.std_no=p.std_no and 
			 p.std_no='510.00/EBS/12/0005' and p.dept_id='510.00'
*/
				
				//String qryStr = "SELECT spsdmt FROM Spstdestdmt spsdmt WHERE spsdmt.id.applicationNo = :applicationNo ";
	            Query query = getEntityManager(webAppName).createQuery(buff.toString());
	            query.setParameter("stdNo", applicationNo);
	            query.setParameter("deptId", deptId);
	            List<AllocationSummaryDisplay> list = query.getResultList();
	            if (list.isEmpty()){
	    			return null;
	            }
	            else{
	            	return list;
	            }
	          
			
			
		}catch (Exception e){
			LOGGER.info("Exception Occured in findByApplicationNo" + e);
			e.printStackTrace();
		}
		return null;
		
	
	}
	

}
