var newStatus="N";
var modifiedStatus="M";
var paidStatus="P";
var confirmedStatus="C";
var allocatedStatus="A";
var serviceEstimateCreatedStatus="S";
var estimatedStatus="E";
//var status=document.getElementById("status").value;

function buttonLoader() {
	//document.getElementById("errorMessage").style.visibility="hidden";
	//document.getElementById("errorMessage").value="ovkjsdfsdf";
	//alert("state "+document.getElementById("state").value);
	//alert("hiddenSecondState "+document.getElementById("hiddenSecondState").value);
	//alert("hi");
	
	if (document.getElementById("state").value == "view") {
		//alert("view");
		makeReadOnlyAll();
		disableAllButtons();
		//alert("af");f
		if (document.getElementById("hiddenSecondState").value == "findAppId"){
			//alert("findAppId");
			if(document.getElementById("errorMessage").value.length==0){
				document.getElementById("btnSave").disabled = true;
				document.getElementById("btnModify").disabled = true;
				document.getElementById("btnIssuePiv").disabled = true;
				checkRedioButtons();
			}else{
				//
			}
			
		}else{
			//alert("else");
			//document.getElementById("btnFillTheSame").disabled = true;
		}
				
			
	} else if (document.getElementById("state").value == "newApplication") {
		//alert("newApplication");
		
		document.getElementById("btnFindAppReferenceNo").disabled = true;
		document.getElementById("btnFindAppId").disabled = true;
		document.getElementById("btnModify").disabled = true;
		//document.getElementById("btnNext").disabled = true;
		document.getElementById("btnSave").disabled = true;
		document.getElementById("btnFillTheSame").disabled = true;
		document.getElementById("btnConfirm").disabled = true;
		document.getElementById("btnIssuePiv").disabled = true;
		//document.getElementById("btnIssuePiv").style.visibility="hidden";
		document.getElementById("btnAllocate").disabled = true;
		//document.getElementById("btnAllocate").style.visibility="hidden";
		document.getElementById("RadioConfirmPivY").disabled = true;
		document.getElementById("RadioConfirmPivN").disabled = true;
		
		document.getElementById("txfdCsc").readOnly = true;
		document.getElementById("txfdDate").readOnly = true;
		//document.getElementById("txfdAllocatedTo").readOnly = true;
		document.getElementById("txfdAppReferenceNo").readOnly = true;
		document.getElementById("txfdApplicationId").readOnly = true;
		if (document.getElementById("txfdCostCenterNo").value== "541.00" || document.getElementById("txfdCostCenterNo").value== "542.00" || document.getElementById("txfdCostCenterNo").value== "547.00" || document.getElementById("txfdCostCenterNo").value== "548.00" ){
			document.getElementById("txfdCostCenterNo").readOnly = false;	
		}else{
			document.getElementById("txfdCostCenterNo").readOnly = true;	
		}
		
		document.getElementById("txfdConfirmedBy").readOnly = true;
		//Duration
		disableDurationPM();
		//document.getElementById("txfdFromDate").childNodes[1].disabled = true;
		//document.getElementById("txfdToDate").childNodes[1].disabled = true;
		//document.getElementById("txfdDurationType").disabled = true;
		//document.getElementById("txfdDuration").disabled = true;
		//Duration
		if (document.getElementById("hiddenSecondState").value == "findId") {
			//alert(document.getElementById("txfdFromDate").childNodes[1].value);
			disableDuration();
			//alert(document.getElementById("errorMessage").value.length);
			if(document.getElementById("errorMessage").value.length==0){
				//document.getElementById("btnNext").disabled = false;
				document.getElementById("btnSave").disabled = false;
				document.getElementById("btnFillTheSame").disabled = false;
				}else{
					//
				}
		}
	} else if (document.getElementById("state").value == "fromCustomer") {
		//alert("newApplication");
		
		document.getElementById("btnFindAppReferenceNo").disabled = true;
		//document.getElementById("btnFindAppId").disabled = true;
		document.getElementById("btnModify").disabled = true;
		//document.getElementById("btnNext").disabled = true;
		//document.getElementById("btnSave").disabled = true;
		//document.getElementById("btnFillTheSame").disabled = true;
		document.getElementById("btnConfirm").disabled = true;
		document.getElementById("btnIssuePiv").disabled = true;
		//document.getElementById("btnIssuePiv").style.visibility="hidden";
		document.getElementById("btnAllocate").disabled = true;
		//document.getElementById("btnAllocate").style.visibility="hidden";
		document.getElementById("RadioConfirmPivY").disabled = true;
		document.getElementById("RadioConfirmPivN").disabled = true;
		
		document.getElementById("txfdCsc").readOnly = true;
		document.getElementById("txfdDate").readOnly = true;
		//document.getElementById("txfdAllocatedTo").readOnly = true;
		document.getElementById("txfdAppReferenceNo").readOnly = true;
		document.getElementById("txfdApplicationId").readOnly = true;
		if (document.getElementById("txfdCostCenterNo").value== "541.00" || document.getElementById("txfdCostCenterNo").value== "542.00" || document.getElementById("txfdCostCenterNo").value== "547.00" || document.getElementById("txfdCostCenterNo").value== "548.00" ){
			document.getElementById("txfdCostCenterNo").readOnly = false;	
		}else{
			document.getElementById("txfdCostCenterNo").readOnly = true;	
		}
		document.getElementById("txfdConfirmedBy").readOnly = true;
		//Duration
		disableDurationPM();
		//document.getElementById("txfdFromDate").childNodes[1].disabled = true;
		//document.getElementById("txfdToDate").childNodes[1].disabled = true;
		//document.getElementById("txfdDurationType").disabled = true;
		//document.getElementById("txfdDuration").disabled = true;
		//Duration
		if (document.getElementById("hiddenSecondState").value == "findId") {
			//alert(document.getElementById("txfdFromDate").childNodes[1].value);
			disableDuration();
			//alert(document.getElementById("errorMessage").value.length);
			if(document.getElementById("errorMessage").value.length==0){
				//document.getElementById("btnNext").disabled = false;
				document.getElementById("btnSave").disabled = false;
				document.getElementById("btnFillTheSame").disabled = false;
				}else{
					//
				}
		}	
	} else if (document.getElementById("state").value == "newCustomer") {
		//alert("newCustomer");
		
		document.getElementById("btnFindAppReferenceNo").disabled = true;
		document.getElementById("txfdAppReferenceNo").readOnly = true;
		document.getElementById("btnFindAppId").disabled = true;
		document.getElementById("btnFindId").disabled = true;
		document.getElementById("btnModify").disabled = true;
		document.getElementById("btnIssuePiv").disabled = true;
		//document.getElementById("btnIssuePiv").style.visibility="hidden";
		document.getElementById("btnAllocate").disabled = true;
		//document.getElementById("btnAllocate").style.visibility="hidden";
		document.getElementById("btnConfirm").disabled = true;
		document.getElementById("txfdCsc").readOnly = true;
		document.getElementById("txfdDate").readOnly = true;
		//document.getElementById("txfdAllocatedTo").readOnly = true;
		if (document.getElementById("txfdCostCenterNo").value== "541.00" || document.getElementById("txfdCostCenterNo").value== "542.00" || document.getElementById("txfdCostCenterNo").value== "547.00" || document.getElementById("txfdCostCenterNo").value== "548.00" ){
			document.getElementById("txfdCostCenterNo").readOnly = false;	
		}else{
			document.getElementById("txfdCostCenterNo").readOnly = true;	
		}
		document.getElementById("txfdConfirmedBy").readOnly = true;
	
	} else if (document.getElementById("state").value == "modifyApplication") {
			//alert("modifyApplication");
			//disableAll();
			document.getElementById("btnFindAppReferenceNo").disabled = true;
			document.getElementById("txfdAppReferenceNo").readOnly = true;
			document.getElementById("btnFindId").disabled = true;
			//alert("1");
			//document.getElementById("btnNext").disabled = true;
			document.getElementById("btnSave").disabled = true;
			document.getElementById("btnFillTheSame").disabled = true;
			document.getElementById("btnConfirm").disabled = true;
			//alert("2");
			document.getElementById("btnModify").disabled = true;
			document.getElementById("btnIssuePiv").disabled = true;
			//document.getElementById("btnIssuePiv").style.visibility="hidden";
			document.getElementById("btnAllocate").disabled = true;
			//document.getElementById("btnAllocate").style.visibility="hidden";
			document.getElementById("RadioConfirmPivY").disabled = true;
			document.getElementById("RadioConfirmPivN").disabled = true;
			//alert("3");
			document.getElementById("txfdCsc").readOnly = true;
			document.getElementById("txfdDate").readOnly = true;
			//document.getElementById("txfdAllocatedTo").readOnly = true;
			if (document.getElementById("txfdCostCenterNo").value== "541.00" || document.getElementById("txfdCostCenterNo").value== "542.00" || document.getElementById("txfdCostCenterNo").value== "547.00" || document.getElementById("txfdCostCenterNo").value== "548.00" ){
				document.getElementById("txfdCostCenterNo").readOnly = false;	
			}else{
				document.getElementById("txfdCostCenterNo").readOnly = true;	
			}
			document.getElementById("txfdConfirmedBy").readOnly = true;
			//alert("4");
			checkRedioButtons();
			//alert("5");
			disableDuration();
			//alert("af");
			
			if (document.getElementById("hiddenSecondState").value == "findAppId"){
				//alert(document.getElementById("errorMessage").value.length);
				if(document.getElementById("errorMessage").value.length==0){
					var status=document.getElementById("status").value;
					//alert(status);
					if (status==newStatus||status==modifiedStatus || status==paidStatus||status==confirmedStatus || status==allocatedStatus || status==serviceEstimateCreatedStatus){
						document.getElementById("btnModify").disabled = false;
						document.getElementById("txfdConfirmedBy").readOnly = true;
						//checkRedioButtons();
					}else if (status==estimatedStatus ){
						alert("Can not Modify this Application. To modify you have to delete Estimate First.");
						document.getElementById("btnModify").disabled = true;
						//checkRedioButtons();
					}
				}else{
					//
				}
				
			}else{
				//alert("else");
				//document.getElementById("btnFillTheSame").disabled = true;
			}
	} else if (document.getElementById("state").value == "changeServiceAdd") {
		//alert("modifyApplication");
		//disableAll();
		makeReadOnlyAll();
		disableAllButtons();
		document.getElementById("txfdServiceStreetAddress").readOnly = false;
		document.getElementById("txfdServiceSuburb").readOnly = false;
		document.getElementById("txfdServiceCity").readOnly = false;
		document.getElementById("txfdServicePostalCode").readOnly = false;
		document.getElementById("txfdNeighboursAccNo").readOnly = false;
		document.getElementById("txfdTariffCode").disabled = false;
		//document.getElementById("btnFindAppReferenceNo").disabled = false;
		document.getElementById("txfdAppReferenceNo").readOnly = false;
		//document.getElementById("btnFindAppReferenceNo").disabled = true;
		//document.getElementById("txfdAppReferenceNo").readOnly = true;
		//document.getElementById("btnFindId").disabled = true;
		//alert("1");
		//document.getElementById("btnNext").disabled = true;
		//document.getElementById("btnSave").disabled = true;
		//document.getElementById("btnFillTheSame").disabled = true;
		//document.getElementById("btnConfirm").disabled = true;
		//alert("2");
		//document.getElementById("btnModify").disabled = true;
		//document.getElementById("btnIssuePiv").disabled = true;
		//document.getElementById("btnIssuePiv").style.visibility="hidden";
		//document.getElementById("btnAllocate").disabled = true;
		//document.getElementById("btnAllocate").style.visibility="hidden";
		//document.getElementById("RadioConfirmPivY").disabled = true;
		//document.getElementById("RadioConfirmPivN").disabled = true;
		//alert("3");
		//document.getElementById("txfdCsc").readOnly = true;
		//document.getElementById("txfdDate").readOnly = true;
		//document.getElementById("txfdAllocatedTo").readOnly = true;
		//if (document.getElementById("txfdCostCenterNo").value== "541.00" || document.getElementById("txfdCostCenterNo").value== "542.00" || document.getElementById("txfdCostCenterNo").value== "547.00" || document.getElementById("txfdCostCenterNo").value== "548.00" ){
		//	document.getElementById("txfdCostCenterNo").readOnly = false;	
		//}else{
		//	document.getElementById("txfdCostCenterNo").readOnly = true;	
		//}
		//document.getElementById("txfdConfirmedBy").readOnly = true;
		//alert("4");
		//checkRedioButtons();
		//alert("5");
		//disableDuration();
		//alert("af");
		
		if (document.getElementById("hiddenSecondState").value == "findAppId"){
			//alert(document.getElementById("errorMessage").value.length);
			if(document.getElementById("errorMessage").value.length==0){
				var status=document.getElementById("status").value;
				//alert(status);
				if (status==newStatus||status==modifiedStatus || status==paidStatus||status==confirmedStatus || status==allocatedStatus || status==serviceEstimateCreatedStatus){
					document.getElementById("btnModify").disabled = false;
					document.getElementById("txfdConfirmedBy").readOnly = true;
					//checkRedioButtons();
				}else if (status==estimatedStatus ){
					//alert("you can change only few fields");
					document.getElementById("btnModify").disabled = true;
					//checkRedioButtons();
				}
			}else{
				//
			}
			
		}else{
			//alert("else");
			//document.getElementById("btnFillTheSame").disabled = true;
		}		
	} else if (document.getElementById("state").value == "alterLoanApp") {
		//alert("modifyApplication");
		//disableAll();
		makeReadOnlyAll();
		disableAllButtons();
		//document.getElementById("btnFindAppReferenceNo").disabled = false;
		document.getElementById("txfdAppReferenceNo").readOnly = false;

		//document.getElementById("btnFindAppReferenceNo").disabled = true;
		//document.getElementById("txfdAppReferenceNo").readOnly = true;
		//document.getElementById("btnFindId").disabled = true;
		//alert("1");
		//document.getElementById("btnNext").disabled = true;
		//document.getElementById("btnSave").disabled = true;
		//document.getElementById("btnFillTheSame").disabled = true;
		//document.getElementById("btnConfirm").disabled = true;
		//alert("2");
		//document.getElementById("btnModify").disabled = true;
		//document.getElementById("btnIssuePiv").disabled = true;
		//document.getElementById("btnIssuePiv").style.visibility="hidden";
		//document.getElementById("btnAllocate").disabled = true;
		//document.getElementById("btnAllocate").style.visibility="hidden";
		//document.getElementById("RadioConfirmPivY").disabled = true;
		//document.getElementById("RadioConfirmPivN").disabled = true;
		//alert("3");
		//document.getElementById("txfdCsc").readOnly = true;
		//document.getElementById("txfdDate").readOnly = true;
		//document.getElementById("txfdAllocatedTo").readOnly = true;
		//if (document.getElementById("txfdCostCenterNo").value== "541.00" || document.getElementById("txfdCostCenterNo").value== "542.00" || document.getElementById("txfdCostCenterNo").value== "547.00" || document.getElementById("txfdCostCenterNo").value== "548.00" ){
		//	document.getElementById("txfdCostCenterNo").readOnly = false;	
		//}else{
		//	document.getElementById("txfdCostCenterNo").readOnly = true;	
		//}
		//document.getElementById("txfdConfirmedBy").readOnly = true;
		//alert("4");
		//checkRedioButtons();
		//alert("5");
		//disableDuration();
		//alert("af");
		
		if (document.getElementById("hiddenSecondState").value == "findAppId"){
			//alert(document.getElementById("errorMessage").value.length);
			if(document.getElementById("errorMessage").value.length==0){
				var status=document.getElementById("status").value;
				//alert(status);
				if (status==newStatus||status==modifiedStatus || status==paidStatus||status==confirmedStatus || status==allocatedStatus || status==serviceEstimateCreatedStatus){
					document.getElementById("btnModify").disabled = false;
					document.getElementById("txfdConfirmedBy").readOnly = true;
					//checkRedioButtons();
				}else if (status==estimatedStatus ){
					//alert("you can change only few fields");
					document.getElementById("btnModify").disabled = true;
					//checkRedioButtons();
				}
			}else{
				//
			}
			
		}else{
			//alert("else");
			//document.getElementById("btnFillTheSame").disabled = true;
		}				
	
	} else if (document.getElementById("state").value == "findId") {
		//alert("findId");
		//alert(document.getElementById("errorMessage").value.length);
		if(document.getElementById("errorMessage").value.length!=0){
			document.getElementById("btnFindAppReferenceNo").disabled = true;
			document.getElementById("txfdAppReferenceNo").readOnly = true;
			document.getElementById("btnFindAppId").disabled = true;
			document.getElementById("btnModify").disabled = true;
			document.getElementById("btnConfirm").disabled = true;
			document.getElementById("btnIssuePiv").disabled = true;
			//document.getElementById("btnIssuePiv").style.visibility="hidden";
			document.getElementById("btnIssuePiv").disabled = true;
			document.getElementById("btnAllocate").disabled = true;
		
			document.getElementById("txfdConfirmedBy").readOnly = true;
		//	document.getElementById("txfdAllocatedTo").readOnly = true;
			document.getElementById("txfdApplicationId").readOnly = true;
			if (document.getElementById("txfdCostCenterNo").value== "541.00" || document.getElementById("txfdCostCenterNo").value== "542.00" || document.getElementById("txfdCostCenterNo").value== "547.00" || document.getElementById("txfdCostCenterNo").value== "548.00" ){
				document.getElementById("txfdCostCenterNo").readOnly = false;	
			}else{
				document.getElementById("txfdCostCenterNo").readOnly = true;	
			}
		}
		
	} else if (document.getElementById("state").value == "findAppId") {
		//alert("findAppId");
		document.getElementById("btnFindAppReferenceNo").disabled = true;
		document.getElementById("txfdAppReferenceNo").readOnly = true;
		document.getElementById("btnFindId").disabled = true;
		document.getElementById("btnSave").disabled = true;
		document.getElementById("btnIssuePiv").disabled = true;
		//document.getElementById("btnIssuePiv").style.visibility="hidden";
		document.getElementById("txfdConfirmedBy").readOnly = true;
		checkRedioButtons();
	} else if (document.getElementById("state").value == "fillTheSame") {
		//alert("fillTheSame");
		document.getElementById("btnFindAppReferenceNo").disabled = true;
		document.getElementById("txfdAppReferenceNo").readOnly = true;
		document.getElementById("btnFindAppId").disabled = true;
		document.getElementById("btnModify").disabled = true;
		document.getElementById("btnConfirm").disabled = true;
		document.getElementById("btnIssuePiv").disabled = true;
		document.getElementById("btnAllocate").disabled = true;
		document.getElementById("RadioConfirmPivY").disabled = true;
		document.getElementById("RadioConfirmPivN").disabled = true;
		document.getElementById("txfdApplicationId").readOnly = true;
		//document.getElementById("txfdAllocatedTo").readOnly = true;
		if (document.getElementById("txfdCostCenterNo").value== "541.00" || document.getElementById("txfdCostCenterNo").value== "542.00" || document.getElementById("txfdCostCenterNo").value== "547.00" || document.getElementById("txfdCostCenterNo").value== "548.00" ){
			document.getElementById("txfdCostCenterNo").readOnly = false;	
		}else{
			document.getElementById("txfdCostCenterNo").readOnly = true;	
		}
		document.getElementById("txfdConfirmedBy").readOnly = true;
		//document.getElementById("txfdServicePostalCode").select();
		//document.getElementById("txfdServicePostalCode").facus();
		disableDuration();
		costcenterChange(document.getElementById('txfdServiceCity').value);
		//alert("2");
	} else if (document.getElementById("state").value == "confirmApp") {
		//alert("confirmApp");
		makeReadOnlyAll();
		disableAllButtons();
		disableDurationPM();
		checkRedioButtons();
		if (document.getElementById("hiddenSecondState").value == "findAppId"){
			disableDuration();
			if(document.getElementById("errorMessage").value.length==0){
				var status=document.getElementById("status").value;
				if (status==confirmedStatus || status==allocatedStatus || status==estimatedStatus ){
					alert("already Confirmed. App No has been issued.");
					document.getElementById("btnConfirm").disabled = true;	
				}else if(status==newStatus||status==modifiedStatus ){
					alert("Confirm the PIV first");
					document.getElementById("RadioConfirmPivY").disabled = false;
					document.getElementById("RadioConfirmPivN").disabled = false;
					document.getElementById("btnConfirm").disabled = true;
				}else if(status==paidStatus ){
					document.getElementById("btnConfirm").disabled = false;
					
				}
			}else{
				//
				}
		}else {
			//
		}
	}else if (document.getElementById("state").value == "allocateApplication") {
			//alert("allocateApplication");
			makeReadOnlyAll();
			disableAllButtons();
			if (document.getElementById("hiddenSecondState").value == "findAppId"){
				if(document.getElementById("errorMessage").value.length==0){
					checkRedioButtons();
					//alert(document.getElementById("status").value=="A");
					var status=document.getElementById("status").value;
					if (status==allocatedStatus ){
						alert("already Allocated");
						document.getElementById("btnAllocate").disabled = true;	
						//document.getElementById("txfdAllocatedTo").readOnly = true;
					}else if(status=="C"){
						document.getElementById("btnAllocate").disabled = false;
						document.getElementById("txfdAllocatedTo").readOnly = false;	
					}else if(status==paidStatus){
						alert("Confirm the Application first");
						document.getElementById("btnAllocate").disabled = true;
						//document.getElementById("txfdAllocatedTo").readOnly = true;
					}else if(status=="N"||status=="M" ){
						alert("PIV must Confirm first");
						document.getElementById("btnAllocate").disabled = true;
						//document.getElementById("txfdAllocatedTo").readOnly = true;
					}
				}else {
					//
				}
			}else{
				//alert("else");
				//document.getElementById("btnFillTheSame").disabled = true;
			}

	} else {
		//alert("Different Path");
		makeReadOnlyAll();
		disableAllButtons();

	}
	
}

function checkRedioButtons(){
	//alert(document.getElementById("hiddenOwnership").value);
	//var i=document.getElementById("hiddenOwnership").value;
	//if (document.getElementById("hiddenOwnership").value == "O"){
	//	document.getElementById('RadioOwnershipO').checked =true;
	//}else
	//	document.getElementById('RadioOwnershipR').checked =true;
	var status=document.getElementById("status").value;
	//alert("6");
	//document.getElementById('RadioOwnership'+document.getElementById("hiddenOwnership").value).checked =true;
	//alert("66");
	//document.getElementById('RadioOccupy_OwnerCertified'+document.getElementById("hiddenOccupy_OwnerCertified").value).checked =true;
	//alert("666");
	//document.getElementById('RadioIsGovernmentPlace'+document.getElementById("hiddenIsGovernmentPlace").value).checked =true;
	//alert("7");
	//document.getElementById('RadioLandDispute'+document.getElementById("hiddenLandDispute").value).checked =true;
	//document.getElementById('RadioPhase'+document.getElementById("hiddenPhase").value).checked =true;
	//document.getElementById('RadioConnectionType'+document.getElementById("hiddenConnectionType").value).checked =true;
	//alert("8");
	//document.getElementById('RadioIsLoopService'+document.getElementById("hiddenIsLoopService").value).checked =true;
	//alert(status);
	if (status==paidStatus||status==confirmedStatus || status==allocatedStatus || status==estimatedStatus ){
	document.getElementById('RadioConfirmPivY').checked =true;
	}else{
		document.getElementById('RadioConfirmPivN').checked =true;
	}
	//alert("endend");
	
}

function checkIdNoNotNull(){
	if((document.getElementById("txfdIdNo").value==null)||(document.getElementById("txfdIdNo").value=="") ){
		alert("Id No must be filled out!");
		document.getElementById("txfdIdNo").select();
		return false;
	}
	else return true;
}

function checkAppIdNoNotNull(){
	if((document.getElementById("txfdApplicationId").value==null)||(document.getElementById("txfdApplicationId").value=="") ){
		alert("Application Id No must be filled out!");
		document.getElementById("txfdApplicationId").select();
		return false;
	}
	else return true;
}

function checkAllocatedToNotNull(){
	if((document.getElementById("txfdAllocatedTo").value==null)||(document.getElementById("txfdAllocatedTo").value=="") ){
		alert("Allocated To must be filled out!");
		document.getElementById("txfdAllocatedTo").select();
		return false;
	}
	else return true;
}

function validateNeighboursAccount(){
		
	if(document.getElementById("txfdNeighboursAccNo")){
		neighbourAcct = document.getElementById("txfdNeighboursAccNo").value;
		if(neighbourAcct.length !=10){
			alert("Neighbours account number should have 10 Characters");
			return false;
		}
	}
		
	return true;
}

function validate_form(){
	
	if(validate_required(document.getElementById("txfdApplicationId").value,"ApplicationId must be filled out!. Use PIV No")==false){
		document.getElementById("txfdApplicationId").select();
		return false;
	}else if (validate_required(document.getElementById("txfdCostCenterNo").value,"Cost Center No must be filled out!")==false){
		document.getElementById("txfdCostCenterNo").select();
		return false;
	}else if (validate_required(document.getElementById("txfdServiceStreetAddress").value,"Street Address must be filled out!")==false){
		document.getElementById("txfdServiceStreetAddress").select();
		return false;
	}else if(validate_required(document.getElementById("txfdServiceSuburb").value,"Suburb must be filled out!")==false){
		document.getElementById("txfdServiceSuburb").select();
		return false;
	}else if(validate_required(document.getElementById("txfdServiceCity").value,"City must be filled out!")==false){
		document.getElementById("txfdServiceCity").select();
		return false;
	}else if(validate_required(document.getElementById("txfdNeighboursAccNo").value,"Neighbours Acc No must be filled out!")==false){
		document.getElementById("txfdNeighboursAccNo").select();
		return false;	
	}else if(!validateNeighboursAccount()){
		document.getElementById("txfdNeighboursAccNo").select();
		return false;	
	}else if(validate_required(document.getElementById("txfdTariffCode").value,"TariffCode must be filled out!")==false){
		document.getElementById("txfdTariffCode").select();
		return false;
	}else if(document.getElementById('txfdApplicationSubType').value!="PM"){
		 if(validate_required(document.getElementById("txfdDuration").value,"Duration must be filled out!")==false){
			 document.getElementById("txfdDuration").select(); 
			 return false;	
		 }else if(validate_required(document.getElementById("txfdDuration").value,"Duration must be filled out!")==false){
			 document.getElementById("txfdDuration").select(); 
			 return false;
		 }else if(validate_required(document.getElementById("txfdFromDate").childNodes[1].value,"From Date must be filled out!")==false){
			 //document.getElementById("txfdDuration").select(); 
			 return false;
		 }else if(validate_required(document.getElementById("txfdToDate").childNodes[1].value,"To Date must be filled out!")==false){
			 //document.getElementById("txfdDuration").select(); 
			 return false;
		 }
		
	//}else if(validate_required(document.getElementById("txfdCustomerCategory").value,"CustomerCategory must be filled out!")==false){
	//	document.getElementById("txfdCustomerCategory").select();
	//	return false;	
	}else if (document.getElementById("txfdAppReferenceNo").readOnly == false){
		if(validate_required(document.getElementById("txfdAppReferenceNo").value,"App Reference No must be filled out!")==false){
		document.getElementById("txfdAppReferenceNo").select();
		return false;
		}
	}
	
		
 }
function validate_required(fieldValue,alerttxt){

  if (fieldValue==null||fieldValue=="")
    {
    alert(alerttxt);
    return false;
  }else{
    return true;
    }
  
}

function makeReadOnlyAll(){
	document.getElementById("txfdAppReferenceNo").readOnly = true;
	//document.getElementById("txfdAllocatedTo").readOnly = true;
	document.getElementById("txfdDate").readOnly = true;
	if (document.getElementById("txfdCostCenterNo").value== "541.00" || document.getElementById("txfdCostCenterNo").value== "542.00" || document.getElementById("txfdCostCenterNo").value== "547.00" || document.getElementById("txfdCostCenterNo").value== "548.00" ){
		document.getElementById("txfdCostCenterNo").readOnly = false;	
	}else{
		document.getElementById("txfdCostCenterNo").readOnly = true;	
	}
	document.getElementById("txfdCsc").readOnly = true;
	document.getElementById("txfdDescription").readOnly = true;
	document.getElementById("txfdIdNo").readOnly = true;
	document.getElementById("txfdServiceStreetAddress").readOnly = true;
	document.getElementById("txfdServiceSuburb").readOnly = true;
	document.getElementById("txfdServiceCity").readOnly = true;
	document.getElementById("txfdServicePostalCode").readOnly = true;
	document.getElementById("txfdAssessmentNo").readOnly = true;
	document.getElementById("txfdNeighboursAccNo").readOnly = true;
	document.getElementById("txfdNoOfBulbs").readOnly = true;
	document.getElementById("txfdNoOfFans").readOnly = true;
	document.getElementById("txfdNoOfPlughs_5A").readOnly = true;
	document.getElementById("txfdNoOfPlughs_15A").readOnly = true;
	document.getElementById("txfdMotorsTotal").readOnly = true;
	document.getElementById("txfdWeldingPlant").readOnly = true;
	document.getElementById("txfdMetalCrusher").readOnly = true;
	document.getElementById("txfdSawMills").readOnly = true;
	//document.getElementById("txfdTariffCode").readOnly = true;
	//document.getElementById("txfdTariffCategoryCode").readOnly = true;
	//alert("1111");
	document.getElementById("txfdCustomerCategory").disabled = true;
	document.getElementById("txfdCustomerType").disabled = true;
	document.getElementById("txfdConfirmedBy").readOnly = true;
	//Select List
	document.getElementById("txfdTariffCategoryCode").disabled = true;
	document.getElementById("txfdTariffCode").disabled = true;
	//Duration
	//document.getElementById("txfdDurationType").readOnly = true;
	document.getElementById("txfdDuration").readOnly = true;
	document.getElementById("txfdFromDate").childNodes[1].readOnly = true;
	document.getElementById("txfdToDate").childNodes[1].readOnly = true;
}

function disableRadioButtons(){
	
}

function disableAllButtons(){
	document.getElementById("btnFindAppReferenceNo").disabled = true;
	document.getElementById("btnFindId").disabled = true;
	//document.getElementById("btnNext").disabled = true;
	document.getElementById("btnSave").disabled = true;
	document.getElementById("btnModify").disabled = true;
	document.getElementById("btnFillTheSame").disabled = true;
	document.getElementById("txfdConfirmedBy").readOnly = true;
	document.getElementById("btnConfirm").disabled = true;
	document.getElementById("btnIssuePiv").disabled = true;
	document.getElementById("btnAllocate").disabled = true;
	//
	document.getElementById("RadioConfirmPivY").disabled = true;
	document.getElementById("RadioConfirmPivN").disabled = true;
	//alert("RadioConfirmPivN");
}



function disableAll(){
	disableAllBtn(true);
	
	
	//alert("done");
	//readOnlyTxfd("txfdAllocatedTo",true);
	readOnlyTxfd("txfdAppReferenceNo",true);
	readOnlyTxfd("txfdDate",true);
	if (document.getElementById("txfdCostCenterNo").value== "541.00" || document.getElementById("txfdCostCenterNo").value== "542.00" || document.getElementById("txfdCostCenterNo").value== "547.00" || document.getElementById("txfdCostCenterNo").value== "548.00" ){
		readOnlyTxfd("txfdCostCenterNo",false);
	}else{
		readOnlyTxfd("txfdCostCenterNo",true);
	}
	
	readOnlyTxfd("txfdCsc",true);
	readOnlyTxfd("txfdDescription",true);
	readOnlyTxfd("txfdIdNo",true);
	readOnlyTxfd("txfdServiceStreetAddress",true);
	readOnlyTxfd("txfdServiceSuburb",true);
	readOnlyTxfd("txfdServiceCity",true);
	readOnlyTxfd("txfdServicePostalCode",true);
	readOnlyTxfd("txfdAssessmentNo",true);
	readOnlyTxfd("txfdNeighboursAccNo",true);
	readOnlyTxfd("txfdNoOfBulbs",true);
	readOnlyTxfd("txfdNoOfFans",true);
	readOnlyTxfd("txfdNoOfPlughs_5A",true);
	readOnlyTxfd("txfdNoOfPlughs_15A",true);
	readOnlyTxfd("txfdMotorsTotal",true);
	readOnlyTxfd("txfdWeldingPlant",true);
	readOnlyTxfd("txfdMetalCrusher",true);
	readOnlyTxfd("txfdSawMills",true);
	readOnlyTxfd("txfdTariffCode",true);
	//readOnlyTxfd("txfdCustomerCategory",true);
	readOnlyTxfd("txfdConfirmedBy",true);
	
	
	
}

function readOnlyTxfd(txfdName,bool){
	document.getElementById(txfdName).readOnly = bool;
}

function disableBtn(btnName,bool){
	document.getElementById(btnName).disabled = bool;
}

function readOnlyAllApplicantTxfd(){
	readOnlyTxfd("txfdIdNo",true);
}

function readOnlyAllApplicationTxfd(){
//	readOnlyTxfd("txfdAllocatedTo",true);
	readOnlyTxfd("txfdAppReferenceNo",true);
	readOnlyTxfd("txfdDate",true);
	if (document.getElementById("txfdCostCenterNo").value== "541.00" || document.getElementById("txfdCostCenterNo").value== "542.00" || document.getElementById("txfdCostCenterNo").value== "547.00" || document.getElementById("txfdCostCenterNo").value== "548.00" ){
		readOnlyTxfd("txfdCostCenterNo",false);
	}else{
		readOnlyTxfd("txfdCostCenterNo",true);
	}
	readOnlyTxfd("txfdCsc",true);
	readOnlyTxfd("txfdDescription",true);
}

function readOnlyAllLandTxfd(){
	readOnlyTxfd("txfdServiceStreetAddress",true);
	readOnlyTxfd("txfdServiceSuburb",true);
	readOnlyTxfd("txfdServiceCity",true);
	readOnlyTxfd("txfdServicePostalCode",true);
	readOnlyTxfd("txfdAssessmentNo",true);
	readOnlyTxfd("txfdNeighboursAccNo",true);
}

function readOnlyAllWiringTxfd(){
	readOnlyTxfd("txfdNoOfBulbs",true);
	readOnlyTxfd("txfdNoOfFans",true);
	readOnlyTxfd("txfdNoOfPlughs_5A",true);
	readOnlyTxfd("txfdNoOfPlughs_15A",true);
	readOnlyTxfd("txfdMotorsTotal",true);
	readOnlyTxfd("txfdWeldingPlant",true);
	readOnlyTxfd("txfdMetalCrusher",true);
	readOnlyTxfd("txfdSawMills",true);
	readOnlyTxfd("txfdTariffCode",true);
	//readOnlyTxfd("txfdCustomerCategory",true);
	readOnlyTxfd("txfdConfirmedBy",true);
}

function disableAllBtn(bool){
	disableBtn("btnFindAppReferenceNo",bool);
	disableBtn("btnFindId",bool);
	//disableBtn("btnNext",bool);
	disableBtn("btnSave",bool);
	disableBtn("btnModify",bool);
	disableBtn("btnFillTheSame",bool);
	disableBtn("txfdConfirmedBy",bool);
	disableBtn("btnConfirm",bool);
	disableBtn("btnIssuePiv",bool);
	disableBtn("btnAllocate",bool);
}

function disableDuration(){
	if(document.getElementById('txfdApplicationSubType').value=="PM"){
		disableDurationPM();
		//document.getElementById("txfdFromDate").childNodes[1].disabled = true;
		//document.getElementById("txfdToDate").childNodes[1].disabled = true;
		//document.getElementById("txfdDurationType").disabled = true;
		//document.getElementById("txfdDuration").disabled = true;
		//document.getElementById("txfdFromDate").childNodes[1].value = "";
	}else{
		document.getElementById("txfdFromDate").childNodes[1].disabled = false;
		document.getElementById("txfdToDate").childNodes[1].disabled = false;
		document.getElementById("txfdDurationType").disabled = false;
		document.getElementById("txfdDuration").disabled = false;
	}
		
	
}
function disableDurationPM(){
	document.getElementById("txfdFromDate").childNodes[1].disabled = true;
	document.getElementById("txfdToDate").childNodes[1].disabled = true;
	document.getElementById("txfdDurationType").disabled = true;
	document.getElementById("txfdDuration").disabled = true;
}

function validateDuration(){
	if(document.getElementById('txfdApplicationSubType').value!="PM"){
		document.getElementById("txfdFromDate").childNodes[1].disabled = true;
		document.getElementById("txfdToDate").childNodes[1].disabled = true;
		document.getElementById("txfdDurationType").disabled = true;
		document.getElementById("txfdDuration").disabled = true;
	}
}

function costcenterChange(serviceCity){
	//alert(serviceCity);
	var costCenterNo=document.getElementById('txfdCostCenterNo').value;
	//alert(costCenterNo);
	if (costCenterNo=="541.00" || costCenterNo=="542.00" || costCenterNo=="547.00" || costCenterNo=="548.00") {
		if (serviceCity=="Colombo_10"){
			//return costCenterNo="541.00";
			//alert("541.00");
			document.getElementById('txfdCostCenterNo').value="541.00";
			document.getElementById('txfdCsc').value="EE - COLOMBO EAST";
		}else if(serviceCity=="Colombo_8" || serviceCity=="Colombo_9"){
			//return costCenterNo="541.00";
			//alert("541.00");
			document.getElementById('txfdCostCenterNo').value="541.00";
			document.getElementById('txfdCsc').value="EE - COLOMBO EAST";
		}else if(serviceCity=="Colombo_4" || serviceCity=="Colombo_6"){
			//return costCenterNo="542.00";
			//alert("542.00");
			document.getElementById('txfdCostCenterNo').value="542.00";
			document.getElementById('txfdCsc').value="EE - COLOMBO SOUTH";
		}else if(serviceCity=="Colombo_5" || serviceCity=="Colombo_7"){
			//return costCenterNo="542.00";
			//alert("542.00");
			document.getElementById('txfdCostCenterNo').value="542.00";
			document.getElementById('txfdCsc').value="EE - COLOMBO SOUTH";
		}else if(serviceCity=="Colombo_2" || serviceCity=="Colombo_3"){
			//return costCenterNo="547.00";
			//alert("547.00");
			document.getElementById('txfdCostCenterNo').value="547.00";	
			document.getElementById('txfdCsc').value="EE - COLOMBO WEST";
		}else if(serviceCity=="Colombo_1" || serviceCity=="Colombo_11" || serviceCity=="Colombo_12"){
			//return costCenterNo="547.00";
			//alert("547.00");
			document.getElementById('txfdCostCenterNo').value="547.00";
			document.getElementById('txfdCsc').value="EE - COLOMBO WEST";
		}else if(serviceCity=="Colombo_13"){
			//return costCenterNo="548.00";
			//alert("548.00");
			document.getElementById('txfdCostCenterNo').value="548.00";
			document.getElementById('txfdCsc').value="EE - COLOMBO NORTH";
		}else if(serviceCity=="Colombo_14" || serviceCity=="Colombo_15"){
			//return costCenterNo="548.00";
			//alert("548.00");
			document.getElementById('txfdCostCenterNo').value="548.00";
			document.getElementById('txfdCsc').value="EE - COLOMBO NORTH";
		}else{
			//return costCenterNo;
			//alert(serviceCity);
		}
	}else {
		//return costCenterNo;
		//alert(serviceCity);
	}
}

function printApplication(){
	//alert("slndfkmng");
	var applicationNo= document.getElementById("txfdAppReferenceNo").value;
	var applicationId= document.getElementById("txfdApplicationId").value;
	var applicationType= "NC";//document.getElementById("RadioApplicationType").value;
	var applicationSubType= document.getElementById("txfdApplicationSubType").value;
	var duration= document.getElementById("txfdDuration").value;
	var fromDate= document.getElementById("txfdFromDate").childNodes[1].value;
	var toDate= document.getElementById("txfdToDate").childNodes[1].value;
	var date= document.getElementById("txfdDate").value;
	var description= document.getElementById("txfdDescription").value;
	var costCentreNo= document.getElementById("txfdCostCenterNo").value;
	
	var costCentreName= document.getElementById("txfdCsc").value;
	var idNo= document.getElementById("txfdIdNo").value;
	var idType= document.getElementById("idType").value;
	var firstName= document.getElementById("txfdFirstName").value;
	var lastName= document.getElementById("txfdLastName").value;
	var streetAddress= document.getElementById("txfdStreetAddress").value;
	var suburb= document.getElementById("txfdSuburb").value;
	var city= document.getElementById("txfdCity").value;
	var postalCode= document.getElementById("txfdPostalCode").value;
	var telephoneNo= document.getElementById("txfdTelephoneNo").value;
	
	var mobileNo= document.getElementById("txfdMobileNo").value;
	var email= document.getElementById("txfdEmail").value;
	var preferefLanguage= document.getElementById("txfdPreferefLanguage").value;
	var cebEmployee= document.getElementById("txfdCebEmployee").value;
	//isLoanApp="Y";//document.getElementById("RadioIsLoanApp").value;
	var isLoanApp;
	if (document.getElementById("RadioIsLoanAppY").checked){
		
	
		isLoanApp="Y";
	}else {
		isLoanApp="N";
	}
	var serviceStreetAddress= document.getElementById("txfdServiceStreetAddress").value;
	var serviceSuburb= document.getElementById("txfdServiceSuburb").value;
	var serviceCity= document.getElementById("txfdServiceCity").value;
	var servicePostalCode= document.getElementById("txfdServicePostalCode").value;
	//var ownership= "O";//document.getElementById("RadioOwnership").value;
	var ownership;
	if (document.getElementById("RadioOwnershipO").checked){
		ownership="O";
	}else{
		ownership="R";
	}
	//var occupy_OwnerCertified= "Y";// document.getElementById("RadioOccupy_OwnerCertified").value;
	var occupy_OwnerCertified;
	if (document.getElementById("RadioOccupy_OwnerCertifiedY").checked){
		occupy_OwnerCertified="Y";
	}else{
		occupy_OwnerCertified="N";
	}
		
	
	//var isGovernmentPlace= "Y";// document.getElementById("RadioIsGovernmentPlace").value;
	var isGovernmentPlace;
	if (document.getElementById("RadioIsGovernmentPlaceY").checked){
		isGovernmentPlace="Y";
	}else{
		isGovernmentPlace="N";
	}
	
	var assessmentNo= document.getElementById("txfdAssessmentNo").value;
	var neighboursAccNo= document.getElementById("txfdNeighboursAccNo").value;
	var noOfBulbs= document.getElementById("txfdNoOfBulbs").value;
	var noOfFans= document.getElementById("txfdNoOfFans").value;
	var noOfPlughs_5A= document.getElementById("txfdNoOfPlughs_5A").value;
	var noOfPlughs_15A= document.getElementById("txfdNoOfPlughs_15A").value;
	var motorsTotal= document.getElementById("txfdMotorsTotal").value;
	var weldingPlant= document.getElementById("txfdWeldingPlant").value;
	
	var metalCrusher= document.getElementById("txfdMetalCrusher").value;
	var sawMills= document.getElementById("txfdSawMills").value;
		
	//var phase= "1";//document.getElementById("RadioPhase").value;
	var phase;
	if (document.getElementById("RadioPhase1").checked){
		phase="1";
	}else{
		phase="3";
	}
	
	//var connectionType= "30"; //document.getElementById("RadioConnectionType").value;
	var connectionType;
	if (document.getElementById("RadioConnectionType15").checked){
		connectionType="15";
	}else if (document.getElementById("RadioConnectionType30").checked){
		connectionType="30";
	}else{
		connectionType="60";
	}
	
	
	var tariffCategoryCode= document.getElementById("txfdTariffCategoryCode").value;
	var tariffCode= document.getElementById("txfdTariffCode").value;
	//document.getElementById("txfdTariffCode").value;
	//document.getElementById("txfdTariffCategoryCode").value;
	//alert("1111");
	var customerCategory= document.getElementById("txfdCustomerCategory").value;
	var customerType= document.getElementById("txfdCustomerType").value;
	var preparedBy= document.getElementById("txfdPreparedBy").value;
	var confirmedBy= document.getElementById("txfdConfirmedBy").value;
	var  visitedDate=document.getElementById("txfdAppointmentDate").value;
	var  session=document.getElementById("txfdAppointmentSession").value;
	//var costCenterArea=document.getElementById("txfdCostCenterArea").value;
	var costCenterArea="";
	//Select List
	
	//Duration
	//document.getElementById("txfdDurationType").value;
	
	
	document.ApplicationApplet.print(applicationNo, applicationId, applicationType, applicationSubType, duration, 
			fromDate, toDate, date, description, costCentreNo, 
			costCentreName, idType, idNo, firstName, lastName, 
			streetAddress, suburb, city, postalCode, telephoneNo, 
			mobileNo, email, preferefLanguage, cebEmployee, isLoanApp,             
            serviceStreetAddress, serviceSuburb, serviceCity, servicePostalCode, ownership, 
            occupy_OwnerCertified, isGovernmentPlace, assessmentNo, neighboursAccNo, noOfBulbs, 
            noOfFans, noOfPlughs_5A, noOfPlughs_15A, motorsTotal, weldingPlant, 
            metalCrusher, sawMills, phase, connectionType, tariffCode, 
            tariffCategoryCode, customerCategory, customerType, preparedBy, confirmedBy,
            visitedDate, session, costCenterArea );


	
}


function tariffChooser(){
	if(document.getElementById('txfdCustomerType').value=="CONS"){
		document.getElementById("txfdTariffCategoryCode").value = "GP";
		document.getElementById("txfdTariffCode").value= "31";
	}else if(document.getElementById('txfdCustomerType').value=="SHOP"){
		document.getElementById("txfdTariffCategoryCode").value = "GP";
		document.getElementById("txfdTariffCode").value= "31";
	}else if(document.getElementById('txfdCustomerType').value=="DOME"){
		document.getElementById("txfdTariffCategoryCode").value = "DP";
		document.getElementById("txfdTariffCode").value= "11";
	}else if(document.getElementById('txfdCustomerType').value=="TEMP"){
		document.getElementById("txfdTariffCategoryCode").value = "RP";
		document.getElementById("txfdTariffCode").value= "51";
	}else if(document.getElementById('txfdCustomerType').value=="CHUR"){
		document.getElementById("txfdTariffCategoryCode").value = "RP";
		document.getElementById("txfdTariffCode").value= "51";	
	}else if(document.getElementById('txfdCustomerType').value=="HOTE"){
		document.getElementById("txfdTariffCategoryCode").value = "HP";
		document.getElementById("txfdTariffCode").value= "41";	
	}else if(document.getElementById('txfdCustomerType').value=="INDT"){
		document.getElementById("txfdTariffCategoryCode").value = "IP";
		document.getElementById("txfdTariffCode").value= "21";			
	}
}

function tariffChooser2(){
	if(document.getElementById('txfdTariffCategoryCode').value=="GP"){
		//document.getElementById("txfdTariffCategoryCode").value = "GP";
		document.getElementById("txfdTariffCode").value= "31";
	}else if(document.getElementById('txfdTariffCategoryCode').value=="DP"){
		document.getElementById("txfdCustomerCategory").value = "PRIV";
		document.getElementById("txfdCustomerType").value = "DOME";
		document.getElementById("txfdTariffCode").value= "11";
	}else if(document.getElementById('txfdTariffCategoryCode').value=="RP"){
		document.getElementById("txfdCustomerCategory").value = "RELI";
		document.getElementById("txfdCustomerType").value = "TEMP";
		document.getElementById("txfdTariffCode").value= "51";
	}else if(document.getElementById('txfdTariffCategoryCode').value=="HP"){
		document.getElementById("txfdCustomerCategory").value = "PRIV";
		document.getElementById("txfdCustomerType").value = "HOTE";
		document.getElementById("txfdTariffCode").value= "41";	
	}else if(document.getElementById('txfdTariffCategoryCode').value=="IP"){
		//document.getElementById("txfdTariffCategoryCode").value = "IP";
		document.getElementById("txfdTariffCode").value= "21";			
	}
}





	

