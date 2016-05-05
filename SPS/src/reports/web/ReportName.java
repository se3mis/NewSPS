package reports.web;

public class ReportName {
	private String reportName;
	private String reportID;
	
	public ReportName(String rptName,String rptID)
	{
		this.reportName = rptName;
		this.reportID = rptID;
	}
	
	
	
	public String getReportName() {
		return reportName;
	}
	
	
	public void setReportName(String reportName) {
		this.reportName = reportName;
	}
	public String getReportID() {
		return reportID;
	}
	public void setReportID(String reportID) {
		this.reportID = reportID;
	}
	
}
