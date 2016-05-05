package util.common;



public class PIVStatus {
	
		public static final String NEW = "N";
		public static final String CONFIRMED ="C";
		public static final String PAID = "P";
		public static final String DESTROYED = "D";
		
		public static final String NEW_DESC = "NEW";
		public static final String CONFIRMED_DESC ="CONFIRMED";
		public static final String PAID_DESC = "PAID";
		public static final String DESTROYED_DESC = "CANCELLED";
		
		
		public static String getPIVStatusDesc(String status)
		{
			String desc = "";
			if(status.equals(NEW))
				desc = NEW_DESC;
			else if(status.equals(CONFIRMED))
				desc = CONFIRMED_DESC;
			else if(status.equals(PAID))
				desc = PAID_DESC;
			else if(status.equals(DESTROYED))
				desc = DESTROYED_DESC;
			return desc;
		}
}
