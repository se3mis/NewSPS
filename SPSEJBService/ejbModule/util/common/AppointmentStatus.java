package util.common;

public final class AppointmentStatus {
	public static final String ACTIVE="A";
	public static final String CANCEL="C";
	public static final String VISITED="V";
	public static final String SERVICE_EST_CREATED="S";
	public static final String ESTIMATED="E";
	public static final String FAILED="F";
	public static final String DISCARDED="D";
	public static final String DETAIL_EST_CREATED="E";
	public static final String ACTIVE_APPOINMENT_STATUS = "A";
	public static final String listappointmentType="ES.VISIT";
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(AppointmentStatus.ACTIVE);
		System.out.println(AppointmentStatus.CANCEL);
		System.out.println(AppointmentStatus.VISITED);
		System.out.println(AppointmentStatus.FAILED);
		System.out.println(AppointmentStatus.ESTIMATED);
		System.out.println(AppointmentStatus.DISCARDED);
		

	}
public static String getAptStsVal(String Status){
		
		return Status.substring(0,1);
		
	}
}
