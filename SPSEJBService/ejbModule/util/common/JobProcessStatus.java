package util.common;

public final class JobProcessStatus {
	public static final String ALLOCATED="A";
	public static final String FINISHED_ENAGIZED ="F";
	public static final String FINISHED_CM_OT ="O";
	public static final String BILLED="B";
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(JobProcessStatus.ALLOCATED);
		System.out.println(JobProcessStatus.BILLED);
		System.out.println(JobProcessStatus.FINISHED_ENAGIZED);
		System.out.println(JobProcessStatus.FINISHED_CM_OT);

	}

}
