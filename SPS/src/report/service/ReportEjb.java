package report.service;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import report.dao.ReportBeanRemote;
import report.model.ReportSps;
import report.model.ReportSpsPara;


public class ReportEjb implements ReportEjbI{
	private Context context;
	private ReportBeanRemote bean; 
	private String region=null;
	
	public ReportEjb(String region) {
		super();
		this.region=region;
		this.bean=lookupDao();
		
	}

	private ReportBeanRemote lookupDao() {
		try
		{
			 context = new InitialContext();
			 ReportBeanRemote bean = (ReportBeanRemote) context.lookup("ReportBean/remote");
			 return bean; 
		} catch (NamingException e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ReportEjb reportEjb=new ReportEjb("ddd");
		System.out.println(reportEjb.getCategoryList()+"222222222222");
		System.out.println(reportEjb.getReportParaList("R0002"));
		System.out.println(reportEjb.getReport("R0001"));
		

	}

	@Override
	public List<String> getCategoryList() {
		return bean.getCategoryList(region);
	}

	@Override
	public List<ReportSps> getReportList() {
		return bean.getReportList(region);
	}

	@Override
	public List<ReportSps> getReportList(String categoryId) {
		return bean.getReportList(categoryId, region);
	}

	@Override
	public ReportSps getReport(String reportId) {
		return bean.getReport(reportId, region);
	}

	@Override
	public List<ReportSpsPara> getReportParaList(String reportId) {
		return bean.getReportParaList(reportId, region);
	}

	@Override
	public List<String> getCategoryList(String isActive) {
		return bean.getCategoryList(isActive, region);
	}

	@Override
	public List<ReportSps> getReportList(String categoryName, String isActive) {
		return bean.getReportList(categoryName, isActive, region);
	}

	@Override
	public ReportSpsPara getReportPara(String reportId, String paraId) {
		return bean.getReportPara(reportId, paraId, region);
	}

}
