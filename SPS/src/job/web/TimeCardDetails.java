package job.web;

public class TimeCardDetails {
	
	private String jobNo;
	private String jobCategory;
	private String jobDescription;
	private String dateType;
	private String timeFrom;
	private String timeTo;
	
	public String getJobNo() {
		return jobNo;
	}

	public void setJobNo(String jobNo) {
		this.jobNo = jobNo;
	}

	public String getJobCategory() {
		return jobCategory;
	}

	public void setJobCategory(String jobCategory) {
		this.jobCategory = jobCategory;
	}

	public String getJobDescription() {
		return jobDescription;
	}

	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}

	public String getDateType() {
		return dateType;
	}

	public void setDateType(String dateType) {
		this.dateType = dateType;
	}

	public String getTimeFrom() {
		return timeFrom;
	}

	public void setTimeFrom(String timeFrom) {
		this.timeFrom = timeFrom;
	}

	public String getTimeTo() {
		return timeTo;
	}

	public void setTimeTo(String timeTo) {
		this.timeTo = timeTo;
	}

	public TimeCardDetails(String jobNo, String jobCategory,
			String jobDescription, String dateType, String timeFrom,
			String timeTo) {
		super();
		this.jobNo = jobNo;
		this.jobCategory = jobCategory;
		this.jobDescription = jobDescription;
		this.dateType = dateType;
		this.timeFrom = timeFrom;
		this.timeTo = timeTo;
	}
	
	

}
