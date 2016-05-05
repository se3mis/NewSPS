package report.service;

import java.util.List;

import report.model.ReportSps;
import report.model.ReportSpsPara;

public interface ReportEjbI {
	List<String> getCategoryList();
	List<ReportSps> getReportList();
	List<ReportSps> getReportList(String categoryId);
	ReportSps getReport(String reportId);
	
	List<ReportSpsPara> getReportParaList(String reportId);
	List<String> getCategoryList(String isActive);
	List<ReportSps> getReportList(String categoryName, String isActive);
	ReportSpsPara getReportPara (String reportId,String paraId);

}
