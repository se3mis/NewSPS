package util.common;

public final class AppointmentStatus {
	public static final String ACTIVE="A";
	public static final String CANCEL="CANCEL";
	public static final String VISITED="VISITED";
	public static final String SERVICE_EST_CREATED="SERVICE_EST_CREATED";
	public static final String FAILED="FAILED";
	public static final String DISCARDED="DISCARDED";
	public static final String listappointmentType="ES.VISIT";
	public static final String DETAIL_EST_CREATED="E";
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(AppointmentStatus.ACTIVE);
		System.out.println(AppointmentStatus.CANCEL);
		System.out.println(AppointmentStatus.VISITED);
		System.out.println(AppointmentStatus.FAILED);
		System.out.println(AppointmentStatus.DISCARDED);
	}
	
	public static String getAptStsVal(String Status){
		
		return Status.substring(0,1);
		
	}

}