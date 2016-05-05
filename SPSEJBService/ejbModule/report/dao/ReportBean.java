package report.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;

import report.model.ReportSps;
import report.model.ReportSpsPara;

import util.emSelect.EmSelector;

/**
 * Session Bean implementation class ReportBean
 */
@Stateless
public class ReportBean extends EmSelector implements ReportBeanRemote, ReportBeanLocal {

    /**
     * Default constructor. 
     */
    public ReportBean() {
        // TODO Auto-generated constructor stub
    }

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getCategoryList(String webAppName) {
		String qryStr = "select categoryName from ReportSpsCategory a order by a.orderSq ";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		List<String> list = query.getResultList();
		return list;
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<String> getCategoryList(String isActive, String webAppName) {
		String qryStr = "select categoryName from ReportSpsCategory a where a.isActive=:isActive order by a.orderSq ";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("isActive", isActive);
		List<String> list = query.getResultList();
		return list;
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ReportSps> getReportList(String webAppName) {
		String qryStr = "select a from ReportSps a order by a.orderSq";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		List<ReportSps> list = query.getResultList();
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ReportSps> getReportList(String categoryName, String webAppName) {
		String qryStr = "select a from ReportSps a, ReportSpsCategory b where a.categoryId=b.categoryId and b.categoryName=:categoryName order by a.orderSq";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("categoryName", categoryName);
		List<ReportSps> list = query.getResultList();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ReportSps> getReportList(String categoryName, String isActive,String webAppName) {
		String qryStr = "select a from ReportSps a, ReportSpsCategory b where a.categoryId=b.categoryId and b.categoryName=:categoryName and a.isActive=:isActive order by a.orderSq";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("categoryName", categoryName);
		query.setParameter("isActive", isActive);
		List<ReportSps> list = query.getResultList();
		return list;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public ReportSps getReport(String reportId, String webAppName) {
		String qryStr = "select a from ReportSps a  where a.reportId=:reportId ";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("reportId", reportId);
		List<ReportSps> list = query.getResultList();
		if (list.isEmpty())
			return null;
        else if (list.size() == 1)
        	return list.get(0);
        throw new NonUniqueResultException();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ReportSpsPara> getReportParaList(String reportId, String webAppName) {
		String qryStr = "select a from ReportSpsPara a where a.id.reportId=:reportId  ";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("reportId", reportId);
		List<ReportSpsPara> list = query.getResultList();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public ReportSpsPara getReportPara(String reportId, String paraId, String webAppName) {
		String qryStr = "select a from ReportSpsPara a where a.id.reportId=:reportId and a.id.paraId=:paraId  ";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("reportId", reportId);
		query.setParameter("paraId", paraId);
		List<ReportSpsPara> list = query.getResultList();
		if (list.isEmpty())
			return null;
        else if (list.size() == 1)
        	return list.get(0);
        throw new NonUniqueResultException();
	}
	
	/*@SuppressWarnings("unchecked")
	@Override
	public ReportSps getReportIdByName(String reportName,String webAppName) {
		String qryStr = "select TRIM(reportId) from ReportSps a where a.reportName=:reportName  ";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("reportName", reportName);		
		List<ReportSps> list = query.getResultList();
		
        	return list.get(0);
       
      
	}*/

	
}
