package util.common;

public final class EstimateStatus1 {
	public static final String NEW="75";
	public static final String SAVED="23";
	public static final String MODIFIED="75";
	public static final String EST_APPROVAL="44";
	public static final String EST_APPROVAL_ES="44";
	public static final String EST_APPROVAL_EA="45";
	public static final String EST_TOBE_APPROVAL_ES= "80";  //"4
	public static final String EST_TOBE_APPROVAL_EE= "85";  //"46";
	public static final String EST_TOBE_APPROVAL_CE= "90";   //"47";
	public static final String EST_TOBE_APPROVAL_DGM= "95";  //"48";
	public static final String EST_APPROVED="22";
	
	public static final String EST_APPROVAL_AGM="49";
	public static final String EST_REJECTED="31";
	
	public static final String EST_POSTING="33";
	public static final String EST_POSTED="22";
	public static final String JOB_POSTED="22";
	public static final String JOB_ONGOING="1";
	public static final String JOB_RIVISION="5";
	public static final String JOB_RIVISED="6";
	public static final String JOB_APPROVAL="55";
	public static final String JOB_APPROVAL_ES="55";
	public static final String JOB_APPROVAL_EA="56";
	public static final String JOB_APPROVAL_EE="57";
	public static final String JOB_APPROVAL_CE="61";

	public static final String JOB_APPROVAL_DGM="58";
	public static final String JOB_APPROVAL_AGM="59";
	public static final String JOB_REJECTED="41";
	public static final String JOB_APPROVED="60";
	public static final String JOB_SOFT_CLOSED="4";
	public static final String JOB_EXPORTED="6";
	public static final String JOB_HARD_CLOSED="3";
	
	// Job revised status
	
	public static final String POSTED_UNPOSTED_CANCELLED="5";
	public static final String PRINTED_CONFIRMED="4";
	
	
	
	public static final String REVISED_JOB_TO_BE_APPROVAL_CE="61";
	public static final String REVISED_JOB_APPROVED="1";
	public static final String REVISED_JOB="5";
	public static final String UPDATED_REVISED_JOB="25";
	public static final String REVISED_JOB_REJECTED="81";
	public String reportName(Integer key)
	{
		switch (key) {
		case 75:
			return "New Estimates";			 
				 
				 
		case 31:
			return "Rejected Estimates";		 
		case 22:
			return "Approved Estimates";			 
		case 33:
			return "Posting Estimates";			 
				 
		case 1:
			return "Ongoing Jobs";
		case 5:
			return "Revision Jobs";
		
		
		case 85:
			return "To be Approved by EE";
		case 90:
			return "To be Approved by(CE)";	
		case 95:
			return "To be Approved by(DGM)";
		case 61:
			return "Job revised. Revised Estimate to be Approved by CE";	
	
		case 81:
			return "Job revised. Revised Estimate has rejected";
		case 41:
			return "Rejected Jobs";
		case 4:
			return "Soft Closed Jobs";
		case 3:
			return "Hard Closed Jobs";
		case 6:
			return "Exported Jobs";

		default:
			return "";
			 
		}
	}
	
	public String searchStatus(Integer key)
	{
		switch (key) {
		case 75:
			return "Modified Estimate";			 
		
		case 85:
			return "To be Approved by EE";
		case 90:
			return "To be Approved by CE";	
		case 95:
			return "To be Approved by DGM";	
		
		case 31:
			return "This estimate has rejected";		 
				 
		case 33:
			return "Posting Estimate  ";			 
		case 22:
			return "Job transferred to MITFIN";			 
		case 1:
			return "Ongoing Job";
		case 5:
			return "This job is to be revised";
		
		case 61:
			return "Job revised. Revised Estimate to be Approved by CE";	
	
		case 81:
			return "Job revised. Revised Estimate has rejected";
		case 60:
			return "This job has approved";
		case 41:
			return "This job has rejected";
		case 4:
			return "Soft Closed Job";
		case 3:
			return "Hard Closed Job";
		case 6:
			return "Exported Job";

		default:
			return "";
			 
		}
	}
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(EstimateStatus.NEW);
		System.out.println(EstimateStatus.SAVED);
		System.out.println(EstimateStatus.MODIFIED);
		//System.out.println(EstimateStatus.EST_APPROVAL);
		System.out.println(EstimateStatus.EST_REJECTED);
		//System.out.println(EstimateStatus.EST_APPROVED);
		System.out.println(EstimateStatus.EST_POSTING);
		System.out.println(EstimateStatus.EST_POSTED);
		System.out.println(EstimateStatus.JOB_ONGOING);
		System.out.println(EstimateStatus.JOB_RIVISION);
		//System.out.println(EstimateStatus.JOB_APPROVAL);
		System.out.println(EstimateStatus.JOB_REJECTED);
		System.out.println(EstimateStatus.JOB_APPROVED);
		System.out.println(EstimateStatus.JOB_SOFT_CLOSED);
		System.out.println(EstimateStatus.JOB_HARD_CLOSED);

	}

}
