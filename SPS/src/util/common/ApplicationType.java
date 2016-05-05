package util.common;



public class ApplicationType {
	
		public static final String NEW_CONN = "NC";
		public static final String TEMP_CONN = "TC";
		public static final String COST_RECOVERY = "CR";
		public static final String MAINTENANCE = "MT";
		public static final String SYS_AUGMENTATION = "SA";
		public static final String POLE_SHIFTING = "PS";
		public static final String BREAK_DOWN = "BD";
		
		public static final String NEW_LINE_DESC = "New";
		public static final String TEMP_CONN_DESC = "Temporary Connection";
		public static final String COST_RECOVERY_DESC = "Customer Request";
		public static final String MAINTENANCE_DESC = "Maintenance";
		public static final String SYS_AUGMENTATION_DESC = "System Augmentation";
		public static final String POLE_SHIFTING_DESC = "Pole Shifting";
		public static final String BREAK_DOWN_DESC = "Break Down";
		
		
		public static final String BULK_SUPPLY = "BS";
		public static final String READ_SUPPLY = "RD";
		public static final String RURAL_ELECTRIFICATION = "RE";
		public static final String LAND = "LN";
		public static final String PLANNING = "PL";

		public static final String BULK_SUPPLY_DESC = "Bulk Supply";
		public static final String READ_SUPPLY_DESC = "Ratama Eliyai Adura Diralai";
		public static final String RURAL_ELECTRIFICATION_DESC = "Rural Electrification";
		public static final String LAND_DESC = "Land";
		public static final String PLANNING_DESC = "Planning";
		
		public static final String RURAL_ELECTRIFICATION_PROJECT = "RI";
		public static final String RURAL_ELECTRIFICATION_PROJECT_DESC = "Rural Electrification Project";
		
		
		public static final String DCB_PROJECT = "DB";
		public static final String DCB_PROJECT_DESC = "DCB Project";
		
		public static final String PCF_PROJECT = "PF";
		public static final String PCF_PROJECT_DESC = "DCB Project";
		
		//public static final String R8_PROJECT = "R8";
		//public static final String R8_ESTENSION_PROJECT_DESC = "RE8 Extension Jobs";
		
		public static final String R8_ESTENSION_PROJECT = "R8";
		public static final String R8_ESTENSION_PROJECT_DESC = "RE8 Extension Jobs";
		
		public static final String R8_SCHEMES_PROJECT = "RS";
		public static final String R8_SCHEMES_PROJECT_DESC = "RE8 Schemes Jobs";
		
		
		public static final String CROSS_PAID_JOBS = "CP";
		public static final String CROSS_PAID_JOBS_DESC = "Cross Paid Jobs";
		
		public static final String ENERGY_MANAGEMENT = "EM";
		public static final String ENERGY_MANAGEMENT_DESC = "Energy Management";
		
		
		public static final String DISTRIBUTION_AND_MAINTENANACE = "DM";
		public static final String DISTRIBUTION_AND_MAINTENANACE_DESC = "Distribution and Maintenance";
		
		
		public static final String CONSTRUCTION = "CN";
		public static final String CONSTRUCTION_DESC  = "Construction";
		
		
		public static final String AREA_MAINTENANCE = "MU";
		public static final String AREA_MAINTENANCE_DESC  = "Area Maintenance";
		
		public static final String AREA_UNIT = "AU";
		public static final String AREA_UNIT_DESC  = "Area Unit";
		
		public static final String AC_UNIT = "AC";
		public static final String AC_UNIT_DESC  = "AC Unit";
		
		public static String getApplicationTypeDesc(String type)
		{
			String desc = "";
			if(type.equals(NEW_CONN))
				desc = NEW_LINE_DESC;
			else if(type.equals(TEMP_CONN))
				desc = TEMP_CONN_DESC;
			else if(type.equals(COST_RECOVERY))
				desc = COST_RECOVERY_DESC;
			else if(type.equals(MAINTENANCE))
				desc = MAINTENANCE_DESC;
			else if(type.equals(SYS_AUGMENTATION))
				desc = SYS_AUGMENTATION_DESC;
			else if(type.equals(POLE_SHIFTING))
				desc = POLE_SHIFTING_DESC;
			else if(type.equals(BREAK_DOWN))
				desc = BREAK_DOWN_DESC;
			else if(type.equals(BULK_SUPPLY))
				desc = BULK_SUPPLY_DESC;
			else if(type.equals(LAND))
				desc = LAND_DESC;
			else if(type.equals(BULK_SUPPLY))
				desc = BULK_SUPPLY_DESC;
			else if(type.equals(RURAL_ELECTRIFICATION))
				desc = RURAL_ELECTRIFICATION_DESC;
			else if(type.equals(RURAL_ELECTRIFICATION_PROJECT))
				desc = RURAL_ELECTRIFICATION_PROJECT_DESC;
			else if(type.equals(LAND))
				desc = LAND_DESC;
			else if(type.equals(PLANNING))
				desc = PLANNING_DESC;
			return desc;
		}
		
		public static String getAppTypeForBilling(String type)
		{
			String billingType = " ";
			if(type.equals(NEW_CONN))
				billingType = "N";
			
			return billingType;
		}
}
