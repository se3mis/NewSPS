package reports.web;

public class ReportCategory {
	private String reportCategory;
	private String reportCategoryID;
	
	public ReportCategory(String category,String id)
	{
		this.reportCategoryID = id;
		this.reportCategory = category;
	}

	public String getReportCategory() {
		return reportCategory;
	}

	public void setReportCategory(String reportCategory) {
		this.reportCategory = reportCategory;
	}

	public String getReportCategoryID() {
		return reportCategoryID;
	}

	public void setReportCategoryID(String reportCategoryID) {
		this.reportCategoryID = reportCategoryID;
	}
	 
	
	
}
