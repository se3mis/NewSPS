package util.common;



	public final class JobProcessStatus {
		public static final String ALLOCATED="A";
		public static final String FINISHED ="F";
		public static final String TEMPORARY_ENERGIZED="E";
		public static final String BILLED="B";
		

		/**
		 * @param args
		 */
		public static void main(String[] args) {
			System.out.println(JobProcessStatus.ALLOCATED);
			System.out.println(JobProcessStatus.BILLED);
			System.out.println(JobProcessStatus.FINISHED);
			

		}
}
