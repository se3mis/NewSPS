package util.common;

public class EstimateApproval {
	
	public static boolean getApprovalStatus(double cost,String userRole){
		boolean isApprovable = false;
		if(userRole.equalsIgnoreCase("ES")){
			if( cost < Constants.ES_APPROVE_LIMIT_MAX.doubleValue() ){
				//status= estimateEjb.updateEstimateStatus(estimateNo, costCenterNo, StandardEstimateStatus.APPROVED_ESTIMATES.getKey(),userId,new Date(),null,null,null,null,null,null,null,null,null,null,null, region);
				isApprovable = true;
				
			}
			
		}else if(userRole.equalsIgnoreCase("EE")){
			if( cost < Constants.EE_APPROVE_LIMIT_MAX.doubleValue() ){
				//status= estimateEjb.updateEstimateStatus(estimateNo, costCenterNo, StandardEstimateStatus.APPROVED_ESTIMATES.getKey(),userId,new Date(),null,null,null,null,null,null,null,null,null,null,null, region);
				isApprovable = true;
				
			}
			
		}else if(userRole.equalsIgnoreCase("CE")){
			if( cost < Constants.CE_APPROVE_LIMIT_MAX.doubleValue()){
				//status= estimateEjb.updateEstimateStatus(estimateNo, costCenterNo, StandardEstimateStatus.APPROVED_ESTIMATES.getKey(),userId,new Date(),null,null,null,null,null,null,null,null,null,null,null, region);
				isApprovable = true;
				
			}
			
		}else if(userRole.equalsIgnoreCase("DGM")){
			//if( cost < Constants.DGM_APPROVE_LIMIT_MAX.doubleValue()){
			isApprovable = true;
			//}
			
		}else if(userRole.equalsIgnoreCase("AGM")){
			isApprovable = true;
			
		}
		
		
		
		return isApprovable;
		
	}

}
