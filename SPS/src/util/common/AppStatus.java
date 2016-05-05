package util.common;

public final class AppStatus {
	public static final String NEW="N";
	public static final String SAVED="S";
	public static final String MODIFIED="M";
	public static final String CONFIRMED="C";
	public static final String PAID="P";
	public static final String ALLOCATED="A";
	public static final String SERVICE_EST_CREATED="S";
	public static final String ESTIMATED="E";
	public static final String REQUEST_FOR_ESTIMATION="R";
	public static final String DISCARD_APPLICATION="D";
	
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(AppStatus.NEW);
		System.out.println(AppStatus.SAVED);
		System.out.println(AppStatus.MODIFIED);
		System.out.println(AppStatus.CONFIRMED);
		System.out.println(AppStatus.PAID);
		System.out.println(AppStatus.ALLOCATED);
		System.out.println(AppStatus.ESTIMATED);

	}

}
