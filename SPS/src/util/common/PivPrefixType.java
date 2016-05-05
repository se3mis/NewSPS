package util.common;

public final class PivPrefixType {
	//
	public static final String APP_NEW_CONN = "ANC";
	public static final String APP_RE_INSP = "RIP";
	public static final String APP_TEMP_CONN = "ATC";
	public static final String APP_CR_JOB = "ACR";
	public static final String APP_MT_JOB = "AMT";
	public static final String APP_SA_JOB = "ASA";
	public static final String APP_PS_JOB = "APS";
	public static final String APP_BD_JOB = "ABD";
	
	public static final String APP_NEW_BCONN = "ABS";
	public static final String APP_NEW_AC = "AAC";
	public static final String APP_NEW_RDCONN = "ARD";
	//
	//public static final String APP_PHASE_CHANGE = "ACP";
	//public static final String APP_RE_CONNECTION = "ACR";
	//public static final String APP_SEQUENCE_CHANGE = "ACS";
	//public static final String APP_TOTAL_CHANGE = "ACT";
	//public static final String APP_NAME_CHANGE = "ACW";
	//public static final String APP_CONVERSION_30_60 = "ACC";
	//
	public static final String EST_NEW_CONN = "ENC";
	public static final String EST_TEMP_CONN = "ETC";
	public static final String EST_CR_JOBS = "ECR";
	public static final String EST_MT_JOBS = "EMT";
	public static final String EST_SA_JOBS = "ESA";
	public static final String EST_PS_JOBS = "EPS";
	public static final String EST_BD_JOBS = "EBD";
	//
	//public static final String EST_PHASE_CHANGE = "ECP";
	//public static final String EST_RE_CONNECTION = "ECR";
	//public static final String EST_SEQUENCE_CHANGE = "ECS";
	//public static final String EST_TOTAL_CHANGE = "ECT";
	//public static final String EST_NAME_CHANGE = "ECW";
	//public static final String EST_CONVERSION_30_60 = "ECC";
	//
	public static final String JOB_NEW_CONN = "JNC";
	public static final String JOB_TEMP_CONN = "JTC";
	public static final String JOB_CR_JOB = "JCR";
	public static final String JOB_MT_JOB = "JMT";
	public static final String JOB_SA_JOB = "JSA";
	public static final String JOB_PS_JOB = "JPS";
	public static final String JOB_BD_JOB = "JBD";
	//
	//public static final String JOB_PHASE_CHANGE = "JCP";
	//public static final String JOB_RE_CONNECTION = "JCR";
	//public static final String JOB_SEQUENCE_CHANGE = "JCS";
	//public static final String JOB_TOTAL_CHANGE = "JCT";
	//public static final String JOB_NAME_CHANGE = "JCW";
	//public static final String JOB_CONVERSION_30_60 = "JCC";
	public static final String APP_BS = "ABS";
	public static final String APP_AC = "AAC";
	public static final String APP_RD = "ARD";
	public static final String APP_RE = "ARE";
	public static final String APP_LN = "ALN";
	public static final String APP_PL = "APL";
	public static final String APP_CP = "ACP";
	public static final String APP_MU = "AMU";
	public static final String APP_EM = "AEM";
	public static final String APP_CN = "ACN";
	public static final String APP_DM = "ADM";
	public static final String APP_AU = "AAU";
	
	//Gayani
	public static final String APP_DCB = "DCB";
	public static final String EST_DCB = "DCB";
	
	public static final String APP_PCF = "PCF";
	public static final String EST_PCF = "PCF";
	
	public static final String APP_R8E = "RE8";
	public static final String EST_R8E = "RE8";
	
	public static final String APP_R8S = "R8S";
	public static final String EST_R8S = "R8S";
	
	
	public static final String EST_BS = "BS";
	public static final String EST_RD = "RD";
	public static final String EST_RE = "RE";
	public static final String EST_REI = "RI";
	public static final String EST_LN = "LN";
	public static final String EST_PL = "PL";
	public static final String EST_CP = "CP";
	public static final String EST_MU = "MU";
	public static final String EST_EM = "EM";
	public static final String EST_DE = "DE";
	public static final String EST_DM = "DM";
	public static final String EST_CN = "CNT";
	public static final String EST_AU = "AU";
	public static final String EST_AC = "AC";
	public static final String BS = "BULK";
	public static final String RE = "RE";
	public static final String LN = "LAND";
	public static final String PL = "PL";
	
	public static final String JOB_BS = "JBS";
	public static final String JOB_LN = "JLN";
	public static final String JOB_RE = "JRE";
	public static final String JOB_PL = "JPL";
	
	
	
	
	/**
	 * @param args
	 */
	public static String getAPP_XXX_Type(String smcType){
		
		if (smcType.equals(ApplicationType.NEW_CONN)){
			return PivPrefixType.APP_NEW_CONN;
		} else if (smcType.equals(ApplicationType.COST_RECOVERY)){
			return PivPrefixType.APP_CR_JOB;
		}else if (smcType.equals(ApplicationType.TEMP_CONN)){
			return PivPrefixType.APP_TEMP_CONN;
		}else if (smcType.equals(ApplicationType.MAINTENANCE)){
			return PivPrefixType.APP_MT_JOB;
		}else if (smcType.equals(ApplicationType.SYS_AUGMENTATION)){
			return PivPrefixType.APP_SA_JOB;
		}else if (smcType.equals(ApplicationType.POLE_SHIFTING)){
			return PivPrefixType.APP_PS_JOB;
		}else if (smcType.equals(ApplicationType.BREAK_DOWN)){
			return PivPrefixType.APP_BD_JOB;
		}else if (smcType.equals(ApplicationType.BULK_SUPPLY)){
			return PivPrefixType.APP_BS;
		}else if (smcType.equals(ApplicationType.AC_UNIT)){
				return PivPrefixType.APP_AC;
		
		}else if (smcType.equals(ApplicationType.READ_SUPPLY)){
				return PivPrefixType.APP_RD;
		}else if (smcType.equals(ApplicationType.RURAL_ELECTRIFICATION)){
			return PivPrefixType.APP_RE;
		}else if (smcType.equals(ApplicationType.LAND)){
				return PivPrefixType.APP_LN;
							
		}else if (smcType.equals(ApplicationType.PLANNING)){
			return PivPrefixType.APP_PL;
		}else if (smcType.equals(ApplicationType.RURAL_ELECTRIFICATION_PROJECT)){
			return PivPrefixType.APP_RE;
		
		}else if (smcType.equals(ApplicationType.CROSS_PAID_JOBS)){
			return PivPrefixType.APP_CP;	
		}else if (smcType.equals(ApplicationType.ENERGY_MANAGEMENT)){
			return PivPrefixType.APP_EM;	
		}else if (smcType.equals(ApplicationType.AREA_MAINTENANCE)){
			return PivPrefixType.APP_MU;	
		}else if (smcType.equals(ApplicationType.DISTRIBUTION_AND_MAINTENANACE)){
			return PivPrefixType.APP_DM;	
		}else if (smcType.equals(ApplicationType.CONSTRUCTION)){
			return PivPrefixType.APP_CN;
		}else if (smcType.equals(ApplicationType.DCB_PROJECT)){
				return PivPrefixType.APP_DCB;
		}else if (smcType.equals(ApplicationType.PCF_PROJECT)){
				return PivPrefixType.APP_PCF;
		}else if (smcType.equals(ApplicationType.R8_ESTENSION_PROJECT)){
				return PivPrefixType.APP_R8E;
		}else if (smcType.equals(ApplicationType.R8_SCHEMES_PROJECT)){
				return PivPrefixType.APP_R8S;
		}
		else{
			return "XXX";
		}
	}
	
	public static String getJOB_XXX_Type(String smcType){
		
		 if (smcType.equals(ApplicationType.BULK_SUPPLY)){
			return PivPrefixType.JOB_BS;	
		}else if (smcType.equals(ApplicationType.RURAL_ELECTRIFICATION)){
			return PivPrefixType.JOB_RE;	
		}else if (smcType.equals(ApplicationType.LAND)){
			return PivPrefixType.JOB_LN;	
		}else if (smcType.equals(ApplicationType.PLANNING)){
			return PivPrefixType.JOB_PL;	
		}else if (smcType.equals(ApplicationType.RURAL_ELECTRIFICATION_PROJECT)){
			return PivPrefixType.JOB_RE;	
		}else{
			return "XXX";
		}
	}
	public static String getEST_XXX_Type(String smcType){
		
		if (smcType.equals(ApplicationType.NEW_CONN)){
			return PivPrefixType.EST_NEW_CONN;
		} else if (smcType.equals(ApplicationType.COST_RECOVERY)){
			return PivPrefixType.EST_CR_JOBS;
		}else if (smcType.equals(ApplicationType.TEMP_CONN)){
			return PivPrefixType.EST_TEMP_CONN;
		}else if (smcType.equals(ApplicationType.MAINTENANCE)){
			return PivPrefixType.EST_MT_JOBS;	
		}else if (smcType.equals(ApplicationType.SYS_AUGMENTATION)){
			return PivPrefixType.EST_SA_JOBS;	
		}else if (smcType.equals(ApplicationType.POLE_SHIFTING)){
			return PivPrefixType.EST_PS_JOBS;	
		}else if (smcType.equals(ApplicationType.BREAK_DOWN)){
			return PivPrefixType.EST_BD_JOBS;	
		}else if (smcType.equals(ApplicationType.BULK_SUPPLY)){
			return PivPrefixType.EST_BS;
		}else if (smcType.equals(ApplicationType.READ_SUPPLY)){
				return PivPrefixType.EST_RD;	
			
		}else if (smcType.equals(ApplicationType.RURAL_ELECTRIFICATION)){
			return PivPrefixType.EST_RE;	
		}else if (smcType.equals(ApplicationType.LAND)){
			return PivPrefixType.EST_LN;	
		}else if (smcType.equals(ApplicationType.PLANNING)){
			return PivPrefixType.EST_PL;	
		}else if (smcType.equals(ApplicationType.RURAL_ELECTRIFICATION_PROJECT)){
			return PivPrefixType.EST_RE;	
		}else if (smcType.equals(ApplicationType.CROSS_PAID_JOBS)){
			return PivPrefixType.EST_CP;	
		}else if (smcType.equals(ApplicationType.CONSTRUCTION)){
			return PivPrefixType.EST_CN;	
		}else if (smcType.equals(ApplicationType.AREA_MAINTENANCE)){
			return PivPrefixType.EST_MU;	
		}else if (smcType.equals(ApplicationType.AREA_UNIT)){
			return PivPrefixType.EST_AU;	
		}else if (smcType.equals(ApplicationType.ENERGY_MANAGEMENT)){
			return PivPrefixType.EST_EM;	
		}else if (smcType.equals(ApplicationType.DISTRIBUTION_AND_MAINTENANACE)){
			return PivPrefixType.EST_DM;
		}else if (smcType.equals(ApplicationType.DCB_PROJECT)){
				return PivPrefixType.EST_DCB;	
		}else if (smcType.equals(ApplicationType.PCF_PROJECT)){
				return PivPrefixType.EST_PCF;	
		}else if (smcType.equals(ApplicationType.R8_ESTENSION_PROJECT)){
				return PivPrefixType.EST_R8E;	
		}else if (smcType.equals(ApplicationType.R8_SCHEMES_PROJECT)){
				return PivPrefixType.EST_R8S;	
		}
		else{
			return "XXX";
		}
	}public static String getEST_Type(String smcType){
		
		if (smcType.equals(ApplicationType.NEW_CONN)){
			return PivPrefixType.EST_NEW_CONN;
		} else if (smcType.equals(ApplicationType.COST_RECOVERY)){
			return PivPrefixType.EST_CR_JOBS;
		}else if (smcType.equals(ApplicationType.TEMP_CONN)){
			return PivPrefixType.EST_TEMP_CONN;
		}else if (smcType.equals(ApplicationType.MAINTENANCE)){
			return PivPrefixType.EST_MT_JOBS;	
		}else if (smcType.equals(ApplicationType.SYS_AUGMENTATION)){
			return PivPrefixType.EST_SA_JOBS;	
		}else if (smcType.equals(ApplicationType.POLE_SHIFTING)){
			return PivPrefixType.EST_PS_JOBS;	
		}else if (smcType.equals(ApplicationType.BREAK_DOWN)){
			return PivPrefixType.EST_BD_JOBS;	
		}else if (smcType.equals(ApplicationType.BULK_SUPPLY)){
			return PivPrefixType.EST_BS;
		}else if (smcType.equals(ApplicationType.READ_SUPPLY)){
				return PivPrefixType.EST_RD;
		}else if (smcType.equals(ApplicationType.RURAL_ELECTRIFICATION)){
			return PivPrefixType.EST_RE;	
		}else if (smcType.equals(ApplicationType.LAND)){
			return PivPrefixType.EST_LN;	
		}else if (smcType.equals(ApplicationType.PLANNING)){
			return PivPrefixType.EST_PL;	
		}else if (smcType.equals(ApplicationType.RURAL_ELECTRIFICATION_PROJECT)){
			return PivPrefixType.EST_RE;	
		}else if (smcType.equals(ApplicationType.CROSS_PAID_JOBS)){
			return PivPrefixType.EST_CP;	
		}else if (smcType.equals(ApplicationType.CONSTRUCTION)){
			return PivPrefixType.EST_CN;	
		}else if (smcType.equals(ApplicationType.AREA_MAINTENANCE)){
			return PivPrefixType.EST_MU;	
		}else if (smcType.equals(ApplicationType.AREA_UNIT)){
			return PivPrefixType.EST_AU;	
		}else if (smcType.equals(ApplicationType.ENERGY_MANAGEMENT)){
			return PivPrefixType.EST_EM;	
		}else if (smcType.equals(ApplicationType.DISTRIBUTION_AND_MAINTENANACE)){
			return PivPrefixType.EST_DM;	
		}else{
			return "XXX";
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
