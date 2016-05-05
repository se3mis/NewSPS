package report.dao;

import java.util.List;

import javax.ejb.Remote;

import report.model.ReportSps;
import report.model.ReportSpsPara;

@Remote
public interface ReportBeanRemote {
	List<String> getCategoryList(String webAppName);
	List<ReportSps> getReportList(String webAppName);
	List<ReportSps> getReportList(String categoryId, String webAppName);
	ReportSps getReport(String reportId, String webAppName);
	List<ReportSpsPara> getReportParaList(String reportId, String webAppName);
	List<String> getCategoryList(String isActive, String webAppName);
	List<ReportSps> getReportList(String categoryName, String isActive,
			String webAppName);
	ReportSpsPara getReportPara(String reportId, String paraId,
			String webAppName);
	/*ReportSps getReportIdByName(String reportName, String webAppName);*/
}
