package util.common;

public final class EstimateNumberGenerator {
	private Format format =new Format();

	
	 public String getConstructionReferenceNumber(String fundSource,String dept_id,String smcType){
		
		
		String format =null;
		if(smcType != null){
			format =  dept_id+"/"+Constants.ESTIMATE_CONSTRUCTION+"/"+getFormat().getYear()+"/";   // This is for further improvement,
																		  //According to the SMC Type, able to generate construction reference number in future,
																		//SMC type can be loaded through Province_BRANCH_JOB_TYPE table, according to the requirement of each cost center
		}else{
			format =  dept_id+"/"+Constants.ESTIMATE_CONSTRUCTION+"/"+getFormat().getYear()+"/";  //This is common format introduced by the system itself
		}
		return format;
		
	}
	 public String getConstructionReferenceViaCommerRefNumber(String dept_id,String commRef){
			
		
		String commerPrefix = commRef.substring(7, Constants.DEFAULT_COMM_REF_LENGTH); //This is used to generate Construction or maintenance 
																					  //reference relevant to Commercial or Planing reference
		String format =null;
		
			format =  dept_id+"/"+commerPrefix+"/";
		
		return format;
		
	}
	 public String getMaintenanceReferenceNumber(String fundSource,String dept_id,String smcType){
		
		//String costCenter = dept_id.substring(0, 3);
		String format =null;
		//if(dept_id.equalsIgnoreCase("520.30")){
			
			if(smcType != null){
				format =  dept_id+"/"+smcType+"/"+getFormat().getYear()+"/"; // This is for further improvement,
				  															//According to the SMC Type, able to generate Dist & Main reference number in future,
																			//SMC type can be loaded through Province_BRANCH_JOB_TYPE table, according to the requirement of each cost center
			}else{
				format =  dept_id+"/"+Constants.ESTIMATE_DISTRIBUTION_MAINTENANCE+"/"+getFormat().getYear()+"/"; //This is common format introduced by the system itself
			}
		return format;
		
	}
	 public String getEManagementReferenceNumber(String fundSource,String dept_id,String smcType){
			
			//String costCenter = dept_id.substring(0, 3);
			String format =null;
		
				if(smcType != null ){
					format =  dept_id+"/"+smcType+"/"+getFormat().getYear()+"/";  // This is for further improvement,
																					//According to the SMC Type, able to generate Energy Management reference number in future,
																					//SMC type can be loaded through Province_BRANCH_JOB_TYPE table, according to the requirement of each cost center
				}else{
					format =  dept_id+"/"+Constants.ESTIMATE_DISTRIBUTION_ENERGY_MANAGEMENT+"/"+getFormat().getYear()+"/"; //This is common format introduced by the system itself
				}
			return format;
			
		}
	 public String getReferenceForFirstFiftyEstimate(String applicationId){
			
			
			String format =null;
			if(applicationId.contains(Constants.SMC_JOB_NUMBER_CODE)){
				format =  applicationId.replace(Constants.SMC_JOB_NUMBER_CODE, Constants.SPS_ESTIMATE_CODE); // According to the existing SPS system, This is the way for generating the SPS reference for First Fifty Jobs
			}else{
				format = applicationId;
			}
			return format;
			
		}
	public Format getFormat() {
		return format;
	}

	public void setFormat(Format format) {
		this.format = format;
	}

}
