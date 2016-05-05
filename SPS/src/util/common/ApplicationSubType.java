package util.common;



public class ApplicationSubType {
	
		//CR
		public static final String METER_CHANGE ="CM";
		public static final String PHASE_CHANGE ="CP";
		public static final String RE_CONNECTION = "CR";
		public static final String SEQUENCE_CHANGE = "CS";
		public static final String TOTAL_CHANGE = "CT";
		public static final String NAME_CHANGE ="CW";
		public static final String CONVERSION_30_60 = "CC";
		public static final String OTHER = "OT";
		
		public static final String PHASE_CHANGE_DESC ="Phase Change";
		public static final String RE_CONNECTION_DESC = "Re-connection";
		public static final String SEQUENCE_CHANGE_DESC = "Sequence Change";
		public static final String TOTAL_CHANGE_DESC = "Total Change";
		public static final String NAME_CHANGE_DESC ="Name Change";
		public static final String CONVERSION_30_60_DESC = "30A-60A Conversion";
		public static final String OTHER_DESC = "Other";
		
		//Temporary Connections 
		public static final String FESTIVAL = "T1";	
		public static final String CARNIVAL = "T2";	
		
		//New Line sub types
		public static final String PERMENANT = "PM";	
		public static final String CONS_2_YRS = "C1";	
		public static final String CONS_LV = "C2";	
		public static final String CONS_HV = "C3";	
		
		public static final String PERMENANT_DESC ="New Connection";
		public static final String CONS_2_YRS_DESC = "Construction 2";
		public static final String CONS_LV_DESC = "Construction LV";
		public static final String CONS_HV_DESC = "Construction HV";
		
		
		public static String getApplicationTypeDesc(String type)
		{
			String desc = "";
			if(type.equals(PHASE_CHANGE))
				desc = PHASE_CHANGE_DESC;
			else if(type.equals(RE_CONNECTION))
				desc = RE_CONNECTION_DESC;
			else if(type.equals(SEQUENCE_CHANGE))
				desc = SEQUENCE_CHANGE_DESC;
			else if(type.equals(TOTAL_CHANGE))
				desc = TOTAL_CHANGE_DESC;
			else if(type.equals(NAME_CHANGE))
				desc = NAME_CHANGE_DESC;
			else if(type.equals(CONVERSION_30_60))
				desc = CONVERSION_30_60_DESC;
			else if(type.equals(OTHER))
				desc = OTHER_DESC;
			else if(type.equals(OTHER))
				desc = PERMENANT_DESC;
			else if(type.equals(OTHER))
				desc = CONS_2_YRS_DESC;
			else if(type.equals(OTHER))
				desc = CONS_LV_DESC;
			else if(type.equals(OTHER))
				desc = CONS_HV_DESC;
			return desc;
		}
		
		
		public static String getAppTypeForBilling(String type){
			String billingType = " ";
			if(type.equals(PHASE_CHANGE))
				billingType = "P";
			else if(type.equals(RE_CONNECTION))
				billingType = "R";
			else if(type.equals(SEQUENCE_CHANGE))
				billingType = "S";
			else if(type.equals(TOTAL_CHANGE))
				billingType = "T";
			else if(type.equals(NAME_CHANGE))
				billingType = "W";
			else if(type.equals(CONVERSION_30_60))
				billingType = "C";
			else if(type.equals(PERMENANT))
				billingType = "N";
			else if(type.equals(CONS_2_YRS))
				billingType = "N";
			else if(type.equals(CONS_LV))
				billingType = "N";
			else if(type.equals(CONS_HV))
				billingType = "N";
			else if(type.equals(METER_CHANGE))
				billingType = "M";
			
			return billingType;
		}
		
		public static void main(String[] args){
			System.out.println(getAppTypeForBilling("PM"));
			System.out.println(getAppTypeForBilling("C1"));
			System.out.println(getAppTypeForBilling("C2"));
			System.out.println(getAppTypeForBilling("C2"));
			System.out.println(getAppTypeForBilling("CM"));
			System.out.println(getAppTypeForBilling("CP"));
			System.out.println(getAppTypeForBilling("CR"));
			System.out.println(getAppTypeForBilling("CC"));
			
		}
}
