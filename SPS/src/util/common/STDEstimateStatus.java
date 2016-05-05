package util.common;

import estimate.model.Spstdesthmt;

public class STDEstimateStatus {
public static String setStatus(Long status){
		
		String statusStandardES="";
		if(status == 1){
			statusStandardES = StandardEstimateStatus.NEW_APPLICATION.getName();
		}else if(status == 2){
			statusStandardES = StandardEstimateStatus.APPLICATION_MODIFIED.getName();
		}else if(status == 8){
			statusStandardES = StandardEstimateStatus.TO_BE_CHECKED_BY_ELECTRICAL_SUPIRINTENDENT.getName();
		}else if(status == 10){
			statusStandardES = StandardEstimateStatus.TO_BE_VALIDATED_BY_ELECTRICAL_ENGINEER.getName();
		}else if(status == 5){
			statusStandardES = StandardEstimateStatus.REJECTED_BY.getName();
		}else if(status == 20){
			statusStandardES = StandardEstimateStatus.TO_BE_VALIDATED_BY_PLANNING_ENGINEER.getName();
		}else if(status == 35){
			statusStandardES = StandardEstimateStatus.TO_BE_VALIDATED_BY_PLANNING_CE.getName();
		}else if(status == 30){
			statusStandardES = StandardEstimateStatus.TO_BE_VALIDATED_BY_CE.getName();
		}else if(status == 40){
			statusStandardES = StandardEstimateStatus.TO_BE_APPROVED_BY_DGM.getName();
		}else if(status == 50){
			statusStandardES = StandardEstimateStatus.APPROVED_ESTIMATES.getName();
		}else if(status == 55){
			statusStandardES = StandardEstimateStatus.PIVII_TO_BE_CONFIRMED_BY_ACCOUNT_REV.getName();
		}else if(status == 56){
			statusStandardES = StandardEstimateStatus.PIVII_TO_BE_CONFIRMED_BY_DEO.getName();
		}else if(status == 60){
			statusStandardES = StandardEstimateStatus.PIVII_CONFIRMATION.getName();
		}else if(status == 61){
			statusStandardES = StandardEstimateStatus.PIVII_FOR_SECDEPOSIT.getName();
		}else if(status == 70){
			statusStandardES = StandardEstimateStatus.SEND_FOR_CONSTRUCTION_MAINTENANCE.getName();
		}else if(status == 80){
			statusStandardES = StandardEstimateStatus.JOB_ALLOCATED.getName();
		}else if(status == 90){
			//statusStandardES = StandardEstimateStatus..getName();
		}else{
			statusStandardES = "";
		}
		
		return statusStandardES;
		
	}
}
