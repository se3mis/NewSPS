package util.common;

import java.math.BigDecimal;

public class Constants {
	public static final String BULK ="BULK";
	public static final String BULK_SUP ="BULK-SUP";
	public static final String BS ="BS";
	
	
	public static final Long BULK_SUPPLY_DEMAND_COST =1250L;
	public static final Double BULK_SUPPLY_DEMAND =63D;
	
	//public static final Double BULK_SUPPLY_DEMAND_63 =63D;
	public static final String LAND ="LN";


	public static final Double LAND_DEMAND_LOWER_LIMIT =10D;
	public static final Double LAND_DEMAND_UPPER_LIMIT =100D;
	
	public static final String SUSTATION_TYPE_LAND ="LAND";
	public static final String SUSTATION_TYPE_BULK ="BULK";
	
	public static final Long REV_NO =2L;
	public static final BigDecimal RESOURCE_CATEGORY =new BigDecimal(1);
	public static final String DEFAULT_STRING ="";
	public static final String DEFAULT_STRING1 ="  ";
	public static final String DEFAULT_UNDEFINED_STRING="undefined";
	public static final int DEFAULT_COMM_REF_LENGTH=17;
	public static final String DEFAULT_OWNERSHIP ="N";
	public static final String DEFAULT_OWNER_CERTIFIED ="N";
	public static final String DEFAULT_IS_GOV_PLACE ="N";
	public static final String DEFAULT_TARIFF_CODE ="31";
	public static final String MACHINERY_COST="MACHINERY" ;
	public static final String OTHER_COST="OTHER_COST" ;
	
	
	public static final String PIV_SUB_TYPE_SEC_DEPOSIT ="secDeposit";
	public static final String PIV_SUB_TYPE_TOTAL ="total";
	public static final String PIV_SUB_TYPE_STESTMATE ="STEstimate";
	
	public static final String DEFAULT_STRING_SELECT ="-Select-";
	public static final String RUTAL_ELECTRIFICATION = "RE";
	public static final String DCB_PROJECT = "DB";
	public static final String PLANNING = "PL";
	public static final String RUTAL_ELECTRIFICATION_I = "RI";
	public static final String CROSS_PAID= "CP";
	
	public static final String MAT_COST_OTHER = "MAT-COST-OTHER";
	public static final String MAT_COST = "MAT-COST";
	public static final String MAT_RESOUR_NAME= "MAT";
	
	public static final String DEFAULT_CONSTRUCTION_ESTIMATE_TYPE= "DES";
	
	public static final String PAYMENT_MODE_CHEQUE= "Q";
	
	public static final String ESTIMATE_TYPE_EBS= "BS";
	
	public static final String ESTIMATE_TYPE_ELN= "LN";
	
	public static final String ESTIMATE_DISTRIBUTION_MAINTENANCE= "DM";
	public static final String ESTIMATE_DISTRIBUTION_ENERGY_MANAGEMENT= "EM";
	//public static final String ESTIMATE_CONSTRUCTION= "CNT";
	public static final String ESTIMATE_CONSTRUCTION= "CN";
	public static final String LINE_NORM_ID_33KV_100kVA_SUBST= "33OD100B";
	public static final String LINE_NORM_ID_33KV_63kVA_SUBST= "33OD063B";
	
	public static final String SMC_APPLICATION_SUB_TYPE= "PM";
	public static final String SMC_JOB_NUMBER_CODE= "SMC";
	public static final String SPS_ESTIMATE_CODE= "SPS";
	public static final String SPS_JOB_NUMBER_CODE= "VSL";
	public static final String GI_TCI= "TCI";
	
	
	/////////////////////CEB_COST-CONSUMER COST//////////////////////
	public static final String DEFAULT_UOM= "NO";
	public static final String DEFAULT_QUANTITY= "1";
	public static final String CEB_COST_CODE= "CEB";
	public static final String CEB_COST_CODE_FOR_SUBSTATION= "CEB01";
	public static final String CONSUMER_COST_CODE_FOR_SUBSTATION= "OTH200";
	public static final String CONSUMER_COST_FOR_SUBSTATION= "Consumer Cost For Substation";
	public static final String CEB_COST_FOR_SUBSTATION= "CEB Cost For Substation";
	
	public static final String FIXED_CODE_FOR_63= "63kVA";
	public static final String FIXED_CODE_FOR_75= "75kVA";
	public static final String FIXED_CODE_FOR_95= "95kVA";
	public static final String VARIABLE_COST_CODE= "VCOST";
	
	public static final Double PH63_TYPE= 63D;
	public static final Double PH75_TYPE= 75D;
	public static final Double PH95_TYPE= 95D;
	
	public static final String FIXED_CODE_FOR_63_DES= "Fixed Cost For The 63kVA (with Bulk Supply Metering)";
	public static final String FIXED_CODE_FOR_75_DES= "Fixed Cost For The 75kVA (with Bulk Supply Metering)";
	public static final String FIXED_CODE_FOR_95_DES= "Fixed Cost For The 95kVA (with Bulk Supply Metering)";
	public static final String VARIABLE_COST_DES= "Variable Cost ";
	
	//public static final Double FIXED_COST_FOR_63 =440000D;
	//public static final Double FIXED_COST_FOR_75 =514000D;
	//public static final Double FIXED_COST_FOR_95 =594000D;
	
	public static final Double FIXED_COST_FOR_63 =434000D;
	public static final Double FIXED_COST_FOR_75 =494000D;
	public static final Double FIXED_COST_FOR_95 =574000D;
	
	//public static final Double VARIABLE_COST =2000D;
	public static final Double VARIABLE_COST =1785D;
	
	public static final String CEB_COST_FOR_LINE= "CEB Cost For Line";
	public static final float DEFAULT_STD_COST= 1.0f;
	
	public static final String DEFAULT_STATUS_22= "Job Number Generated";
	
	public static final Long ES_APPROVE_LIMIT_MAX =25000L;
	
	public static final Double EE_APPROVE_LIMIT_MIN =25000D;
	
	public static final Double EE_APPROVE_LIMIT_MAX =250000D;
	
	public static final Double CE_APPROVE_LIMIT_MAX =1000000D;
	
	public static final Double DGM_APPROVE_LIMIT_MAX =5000000D;
	
	public static final Double CE_APPROVE_LIMIT_MIN =250000D;
	
	public static final Double DGM_APPROVE_LIMIT_MIN =1000000D;
	
	public static final String DISTRICT_CODE_KEY ="DISTRICT";
	public static final String ELECTORATE_CODE_KEY ="ELECTORATE";
	public static final String CSC_CODE_KEY ="CSC";
	
	
	
	//Menu Migration details
	
	//Branch codes
	public static final String DEFAULT_BRANCHCODE ="DE";
	public static final String BRANCHTYPE_COMMERCIAL ="COMMERCIAL";
	public static final String BRANCHTYPE_CONSTRUCTION ="CONSTRUCTION";
	public static final String BRANCHTYPE_DISTRIBUTION_AND_MAINTENANCE ="DM";
	public static final String BRANCHTYPE_PLANNING ="PLANNING";
	public static final String BRANCHTYPE_ENERGY_MANAGEMENT ="EM";
	public static final String BRANCHTYPE_AREA_MAINTENANCE ="AMU";
	public static final String BRANCHTYPE_AREA_UNIT ="AU";
	public static final String BRANCHTYPE_AC_UNIT ="AC";
	public static final String BRANCHTYPE_GOVERNMENT_INSTALLATION ="GI";
	public static final String BRANCHTYPE_PHM ="PH";
	//job Types
	public static final String DEFAULT_ESTIMATE_TYPE ="DE";
	public static final String JOBTYPE_COMMERCIAL_CP ="CP";
	public static final String JOBTYPE_CONSTRUCTION ="CN";
	public static final String JOBTYPE_PHM ="PH";
	public static final String JOBTYPE_DISTRIBUTION_AND_MAINTENANCE ="DM";
	public static final String JOBTYPE_ENERGY_MANAGEMENT ="EM";
	
}
