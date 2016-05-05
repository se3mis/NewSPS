function getValues(ctrl){
	
	var Ctrlid = ctrl.id;	
	 var estNoRowID = 'tdestNo_'+Ctrlid;
	 
	 var costCenterRowID = 'rejcostCenNo_'+Ctrlid;
	 var subTypeRowID = 'rejsubTypeNo_'+Ctrlid;
	 
	 var elementestNo =document.getElementById(estNoRowID).innerHTML;
	 var costCenterNo =document.getElementById(costCenterRowID).innerHTML;	 
	 var subType =document.getElementById(subTypeRowID).innerHTML;	
	 
	 var navigateURL;
	 if(subType=="C1"||subType=="C2"||subType=="C3"||subType=="PM")
		 navigateURL ="/SMC/estimate/web/estimate!undoReject";
	 else if(subType=="CP")
		 navigateURL ="/SMC/estimateCR/web/estphasechange!undoReject";
	 else if(subType=="CC")
		 navigateURL ="/SMC/estimateCR/web/estphasechange!undoRejectConversion";
	 else if(subType=="CR")
		 navigateURL ="/SMC/estimateCR/web/estreconn!undoReject";
	 else if(subType=="T1")
		 navigateURL ="/SMC/estimatetc1/web/estimatefestival!undoReject";
	 else if(subType=="T2")
		 navigateURL ="/SMC/estimatetc2/web/estimatecarnival!undoReject";
 
	 var actionurl = navigateURL+"?estNo="+elementestNo+"&costCen="+costCenterNo;
	
	 document.forms[0].action=actionurl;
	 document.forms[0].submit();
	 
}

function getValuesForRejJobs(ctrl){
	
	 var Ctrlid = ctrl.id;	
	 var prjNoRowID = 'tdPrjtNo_'+Ctrlid;	
	 var costCenterRowID = 'rejRevCostCenNo_'+Ctrlid;
	 var projectNo =document.getElementById(prjNoRowID).innerHTML;	
	 var costCenterNo =document.getElementById(costCenterRowID).innerHTML;		 
	 document.forms[0].action="/SMC/job/web/ReviseJob?prjNo="+projectNo+"&costCen="+costCenterNo;
	 document.forms[0].submit();
}

function getValuesForEstApprove(ctrl){
	
	 var Ctrlid = ctrl.id;	
	 var estNoRowID = 'tdApprestNo_'+Ctrlid;
	 var costCenterRowID = 'estAprCostCenNo_'+Ctrlid;
	 var subTypeRowID = 'rejsubTypeNo_'+Ctrlid;
	 
	 var estimateNo =document.getElementById(estNoRowID).innerHTML;
	 var costCenterNo =document.getElementById(costCenterRowID).innerHTML;
	 var subType =document.getElementById(subTypeRowID).innerHTML;	
		 
	 var navigateURL;
	 if(subType=="C1"||subType=="C2"||subType=="C3"||subType=="PM")
		 navigateURL ="/SMC/estimate/web/estimate!approve";
	 else if(subType=="CP")
		 navigateURL ="/SMC/estimateCR/web/estphasechange!approve";
	 else if(subType=="CC")
		 navigateURL ="/SMC/estimateCR/web/estphasechange!approveConversion";
	 else if(subType=="CR")
		 navigateURL ="/SMC/estreconn/web/estreconn!approve";
	 else if(subType=="T1")
		 navigateURL ="/SMC/estimateTC/web/estimatecarnival!approve";
	 else if(subType=="T2")
		 navigateURL ="/SMC/estimateTC/web/estimatefestival!approve";
 	
	 var actionurl = navigateURL+"?estNo="+estimateNo+"&costCen="+costCenterNo;
		 
	 document.forms[0].action= actionurl;
	 document.forms[0].submit();
}

function getValuesForAprRevised(ctrl){
	
	 var Ctrlid = ctrl.id;	
	 var prjNoRowID = 'tdAprRevisedPrjNo_'+Ctrlid;	
	 var costCenterRowID = 'estRevAprcostCenNo_'+Ctrlid;
	 var projectNo =document.getElementById(prjNoRowID).innerHTML;
	 var costCenterNo =document.getElementById(costCenterRowID).innerHTML;
	 document.forms[0].action="/SMC/job/web/approveRevisedJob?projectNo="+projectNo+"&costCen="+costCenterNo;
	 document.forms[0].submit();
}

function getValuesForunAttendedAppoinments(ctrl){
	 var Ctrlid = ctrl.id;	
	 var estNoRowID = 'unAttApptRefNo_'+Ctrlid;	
	 var aptDateRowID = 'unAttApptAptDate_'+Ctrlid;
	 var allocToRowID = 'unAttApptAllTo_'+Ctrlid;
	 var estNo =document.getElementById(estNoRowID).innerHTML;
	 var aptdate =document.getElementById(aptDateRowID).innerHTML;
	 var allocatedUser =document.getElementById(allocToRowID).innerHTML;	 
	 document.forms[0].action="/SMC/calendar/web/schedule?selDateFromMenu="+aptdate+"&allocateUser="+allocatedUser+"&estNo="+estNo;
	 document.forms[0].submit();
	
}

function getValuesForRevisedJobs(ctrl){
	 var Ctrlid = ctrl.id;	
	 var prjNoRowID = 'tdRevPrjtNo_'+Ctrlid;	
	 var costCenterRowID = 'tdRevCostCenNo_'+Ctrlid;
	 var projectNo =document.getElementById(prjNoRowID).innerHTML;
	 var costCenterNo =document.getElementById(costCenterRowID).innerHTML;
	 document.forms[0].action="/SMC/job/web/ReviseJob?projectNo="+projectNo+"&costCenter="+costCenterNo;
	 document.forms[0].submit();	 
	 
}
