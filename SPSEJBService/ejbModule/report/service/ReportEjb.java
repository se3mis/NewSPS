package report.service;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import report.dao.ReportBeanRemote;
import report.model.ReportSps;
import report.model.ReportSpsPara;


public class ReportEjb implements ReportBeanRemote{
	private Context context;
	private ReportBeanRemote bean; 
	public ReportEjb() {
		super();
		this.bean=lookupDao();
		
	}

	private ReportBeanRemote lookupDao() {
		try
		{
			 context = new InitialContext();
			 ReportBeanRemote dao = (ReportBeanRemote) context.lookup("ReportBean/remote");
			 return dao; 
		} catch (NamingException e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ReportEjb reportEjb=new ReportEjb();
		
		List<ReportSps> reportList3=reportEjb.getReportList("1", "555");
		System.out.println(reportList3+"///////////////////");
		System.out.println(reportEjb.getCategoryList("sss"));
				System.out.println(reportEjb.getReportList("sss"));
				
				System.out.println(reportEjb.getReportList("Piv","sss"));
				System.out.println(reportEjb.getReportList("Piv","sss"));
				
				System.out.println(reportEjb.getReportList("Piv","1","sss"));
				System.out.println(reportEjb.getCategoryList("sss","1"));
				System.out.println(reportEjb.getReportParaList("1", "dd"));
				
	}

	@Override
	public List<String> getCategoryList(String webAppName) {
		return bean.getCategoryList(webAppName);
	}

	@Override
	public List<ReportSps> getReportList(String webAppName) {
		return bean.getReportList(webAppName);
	}

	@Override
	public List<ReportSps> getReportList(String categoryId, String webAppName) {
		return bean.getReportList(categoryId, webAppName);
	}

	@Override
	public ReportSps getReport(String reportId, String webAppName) {
		return bean.getReport(reportId, webAppName) ;
	}

	@Override
	public List<ReportSpsPara> getReportParaList(String reportId,
			String webAppName) {
		return bean.getReportParaList(reportId, webAppName);
	}

	@Override
	public List<String> getCategoryList(String isActive, String webAppName) {
		return bean.getCategoryList(isActive, webAppName);
	}

	@Override
	public List<ReportSps> getReportList(String categoryName, String isActive,
			String webAppName) {
		return bean.getReportList(categoryName, isActive, webAppName);
	}

	@Override
	public ReportSpsPara getReportPara(String reportId, String paraId,
			String webAppName) {
		return bean.getReportPara(reportId, paraId, webAppName);
	}

	

}
