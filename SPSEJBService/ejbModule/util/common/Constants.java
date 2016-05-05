package util.common;

import java.math.BigDecimal;

public class Constants {
	public static final String BULK_SUPPLY ="BULK";
	public static final String BULK_SUP ="BULK-SUP";
	public static final String BS ="BS";
	
	public static final String MAT_RESOUR_TYPE ="MAT";
	public static final String MAT_RESOUR_CODE ="MAT";
	public static final String MAT_UOM ="NO";
	public static final Long REV_NO =2L;
	public static final String NORM_DEFAULT ="F";
	public static final String RES_TYPE_MAT_COST= "MAT-COST";
	
	public static final String RES_TYPE_MAT_COST_OTHER ="MAT-COST-OTHER";
	
	public static final String RES_TYPE_CONTINGENCIES_COST ="CONTINGENCIES-COST";
	
	public static final String RES_CODE_CONTINGENCIES_COST ="CONTINGENCIES";
	
	public static final Long RES_CAT_CONTIGENCIES =2L;
	
	public static final Long RES_CAT_DEFAULT =2L;
	
	public static final String GEN_RES ="F";
	
	public static final String CONTIGENCIES_UOM ="NO";
	
	public static final BigDecimal UNIT_PRICE_CONTIGENCIES =new BigDecimal(1.0);
	
	public static final BigDecimal TOLLERANCE_CONTIGENCIES =new BigDecimal(10);
	
	
	public static final BigDecimal UNIT_PRICE_DEFAULT =new BigDecimal(1.0);
	
	public static final BigDecimal TOLLERANCE__DEFAULT=new BigDecimal(10);

	public static final BigDecimal MINIMUM_ESTIMATE_COST =new BigDecimal(3500);
	
	public static final String RES_TYPE_OTHER_MATERIALS ="OTHER-MATERIAL";
	
	public static final String RES_CODE_OTHER_MATERIALS ="OTHER-MATERIAL";
	
	public static final Long RES_CAT_OTHER_MATERIALS =2L;
	
	public static final String OTHER_MATERIALS_UOM ="NO";
	
	public static final BigDecimal UNIT_PRICE_OTHER_MATERIALS =new BigDecimal(1.0);
	
	public static final BigDecimal TOLLERANCE_OTHER_MATERIALS =new BigDecimal(0);
	
	public static final BigDecimal ESTIMATE_QUANTITY_OTHER_MATERIALS =new BigDecimal(0);
	
	public static final Long ES_APPROVE_LIMIT_MAX =25000L;
	
	public static final Double EE_APPROVE_LIMIT_MIN =25000D;
	
	public static final Double EE_APPROVE_LIMIT_MAX =250000D;
	
	public static final Double CE_APPROVE_LIMIT_MAX =1000000D;
	
	public static final Double CE_APPROVE_LIMIT_MIN =250000D;
	
	public static final Double DGM_APPROVE_LIMIT_MIN =1000000D;
	
	public static final String PLANNING = "PL";
	
	
	public static final String DCB_PROJECT = "DB";
	public static final String PCF_PROJECT = "PF";
	public static final String PE8_EXTENSION_PROJECT = "R8";
	public static final String PE8_SCHEME_PROJECT = "RS";
	
	
	public static final String RUTAL_ELECTRIFICATION_I = "RI";
	public static final String RUTAL_ELECTRIFICATION = "RE";
	public static final String LAND = "LN";
	
	/* R1 Colombo city Cost calculation  */
	
	public static final String RES_CD_MAINTENANCE= "MAINTENANCE 3%";
	
	public static final double RES_CD_MAINTENANCE_PERCENTAGE=0.03;
	
	public static final String RES_CD_INTEREST= "INTEREST 7%";
	
	public static final double RES_CD_INTEREST_PERCENTAGE=0.07;
	
	public static final String RES_CD_DEPRECIATION= "DEPRECIATION 5%";
	
	public static final double RES_CD_DEPRECIATION_PERCENTAGE=0.05;
	
	public static final String RES_CD_DAMAGES_BREAKAGES ="DAM-BREAKAGES 10%";
	
	public static final double RES_CD_DAMAGES_BREAKAGES_PERCENTAGE=0.1;
	
	
	public static final String RES_CD_AD = "5% A-D";
	
	public static final double RES_CD_AD_PERCENTAGE=0.05;
	
	public static final String RES_CD_VAT = "12% VAT";
	
	public static final double RES_CD_VAT_PERCENTAGE=0.12;
	
	public static final String RES_CD_AG ="20% A-G";
	
	public static final double RES_CD_AG_PERCENTAGE=0.2;
	
	public static final String RES_CD_50_LABOUR="50% LABOUR";
	
	public static final double RES_CD_50_LABOUR_PERCENTAGE=0.5;
	
    public static final String RES_CD_30_AG ="30% A-G";
	
	public static final double RES_CD__30_AG_PERCENTAGE=0.3;
	
	public static final int DEFAULT_COMM_REF_LENGTH=17;
	
	public static final String SPS_ESTIMATE_CODE= "SPS";
	public static final String SPS_JOB_NUMBER_CODE= "VSL";

	
	
}
