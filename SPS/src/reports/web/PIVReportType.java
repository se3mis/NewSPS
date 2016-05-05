package reports.web;

public class PIVReportType {
	private String reportName;
	private String reportID;
	
	
	public PIVReportType(String reportName,String prefix)
	{
		this.reportName = reportName;
		this.reportID = prefix;
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
