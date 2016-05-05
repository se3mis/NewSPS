function validate_required(fieldValue,alerttxt){

  if (fieldValue==null||fieldValue=="")
    {
    alert(alerttxt);
    return false;
  }else{
    return true;
    }
  
}

function checkIdNoNotNull(){
	if((document.getElementById("txfdIdNo").value==null)||(document.getElementById("txfdIdNo").value=="") ){
		alert("Id Number must be filled out!");
		document.getElementById("txfdIdNo").select();
		return false;
	}
	else return true;
}

function validate_form(){	
	//alert("hi");
	if(validate_required(document.getElementById("txfdIdNo").value,"Id Number must be filled out!")==false){
		document.getElementById("txfdIdNo").select();
		return false;
	}else if(document.getElementById("idRadioTypeNIC").checked){
		idNo = document.getElementById("txfdIdNo").value;
		
			
		 if(idNo.length !=10  && idNo.length !=12){

             alert("Id number should have 10 Characters or Id number should have 12 Characters");

             return false;


		}else{

            if (idNo.length ==10) {

                  var lastItem = idNo.substr(9,1);

                  var firstItem = idNo.substr(0,1);

                  if(lastItem!="v" && lastItem!="V" && lastItem!="x" && lastItem!="X" && firstItem!="d"){

                        alert("Id number should end with either v or x");

                        return false;

            }

            

      }



      }


		/**}else{
			//var lastItem = idNo.substr(9,1);
			//var firstItem = idNo.substr(0,1);
			//if(lastItem!="v" && lastItem!="V" && lastItem!="x" && lastItem!="X" && firstItem!="d"){
				//alert("Id number should end with either v or x");
				//return false;
			}*/
		

		//}
		
		
	}else if(document.getElementById("idRadioTypePAS").checked){
		passportNo = document.getElementById("txfdIdNo").value;
		if(passportNo.length !=8){
			alert("Passport number should have 8 Characters");
			return false;
		}else{
			var firstItem = passportNo.substr(0,1);
			if(firstItem!="N" && firstItem!="n" && firstItem!="M" && firstItem!="m"){
				alert("Passport number should start with N Or M");
				return false;
			}
		}
	}
	
	if (validate_required(document.getElementById("txfdFirstName").value,"First Name must be filled out!")==false){
		document.getElementById("txfdFirstName").select();
		return false;
	}else if (validate_required(document.getElementById("txfdLastName").value,"Last Name must be filled out!")==false){
		document.getElementById("txfdLastName").select();
		return false;
	}else if(validate_required(document.getElementById("txfdStreetAddress").value,"Street Address must be filled out!")==false){
		document.getElementById("txfdStreetAddress").select();
		return false;
	}else if(validate_required(document.getElementById("txfdSuburb").value,"Suburb must be filled out!")==false){
		document.getElementById("txfdSuburb").select();
		return false;
	}else if(document.getElementById("isColomboRadioTypeN") && document.getElementById("isColomboRadioTypeN").checked){
		if(validate_required(document.getElementById("txfdOtherCity").value,"City must be filled out!")==false){
			document.getElementById("txfdOtherCity").select();
			return false;	
		}
	}else if(document.getElementById("isColomboRadioTypeN") && document.getElementById("isColomboRadioTypeN").checked){			
		if(validate_required(document.getElementById("txfdCity").value,"City must be filled out!")==false){
		document.getElementById("txfdCity").select();
		return false;
		}
	}
			
	var answer = confirm ("Are you sure you want to save?");
	if (!answer)
		return false;	
 }

function setOtherCity(cityVal){
	costCenterNo = document.getElementById("txfdCostCenterNo").value;
	if(costCenterNo=='541.00' || costCenterNo=='541.10' || costCenterNo=='541.20' || costCenterNo=='542.00' || costCenterNo=='542.10' || costCenterNo=='542.20' || costCenterNo=='547.00' || costCenterNo=='547.10' || costCenterNo=='547.20' || costCenterNo=='548.00' || costCenterNo=='548.10' || costCenterNo=='548.20'){
		if(cityVal=='Colombo_1'||cityVal=='Colombo_2'||cityVal=='Colombo_3'||cityVal=='Colombo_4'||cityVal=='Colombo_5'||cityVal=='Colombo_6'||cityVal=='Colombo_7'||cityVal=='Colombo_8'||cityVal=='Colombo_9'||cityVal=='Colombo_10'||cityVal=='Colombo_11'||cityVal=='Colombo_12'||cityVal=='Colombo_13'||cityVal=='Colombo_14'||cityVal=='Colombo_15'){
			
			document.getElementById("isColomboRadioTypeY").checked = true;		
			document.getElementById("tdlblServiceCity").style.visibility = "hidden";
			document.getElementById("tdtxtServiceCity").style.visibility = "hidden";
			document.getElementById("txfdOtherCity").value="";
			document.getElementById("txfdCity").disabled = false;
			}else
			{
				document.getElementById("isColomboRadioTypeN").checked =true;
				document.getElementById("tdlblServiceCity").style.visibility = "visible";
				document.getElementById("tdtxtServiceCity").style.visibility = "visible";
				document.getElementById("txfdOtherCity").value = cityVal;
				document.getElementById("txfdCity").disabled = true;
				
			}
	}	

}		


function buttonLoader() {	
	
	if (document.getElementById("state").value == "newApplicant") {
		document.getElementById("btnFindIdNo").disabled = true;		
		document.getElementById("btnModify").disabled = true;		
		if(document.getElementById("errorMessage").value.length==0){
			//document.getElementById("btnConfirm").disabled = true;
		}else 
		{
			
		}		
		
	} else if (document.getElementById("state").value == "modifyApplicant") {
		
		document.getElementById("btnSave").disabled = true;
		document.getElementById("btnModify").disabled = true;		
		if (document.getElementById("hiddenSecondState").value == "findIdNo"){
			document.getElementById("btnSave").disabled = true;
			document.getElementById("btnModify").disabled = false;
		
			var cityValue = document.getElementById("hid_city").value;
			
			if(cityValue!=null && cityValue.length>0)
				setOtherCity(cityValue);
			
		}else {
			
		}
	} else if (document.getElementById("state").value == "fromDummyId") {
		//alert("1");
		document.getElementById("btnFindIdNo").disabled = true;
		document.getElementById("txfdIdNo").readOnly = true;
		//alert("2");
		document.getElementById("btnModify").disabled = true;
		//alert("3");
		//document.getElementById("RadioEntitled4LoanN").checked =true;
		//alert("4");
		//document.getElementById("RadioEntitled4LoanY").disabled =true;//later
		if(document.getElementById("errorMessage").value.length==0){
			//alert("no error");
			document.getElementById("btnConfirm").disabled = true;
		}else {
			//alert("error");
		}
		//disableLoanDetails();
		
		
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
		document.getElementById("txfdAllocatedTo").readOnly = true;
		document.getElementById("txfdCostCenterNo").readOnly = true;
		document.getElementById("txfdConfirmedBy").readOnly = true;
	
	} else if (document.getElementById("state").value == "modifyApplication") {
			//alert("modifyApplication");
			//disableAll();
			document.getElementById("btnFindAppReferenceNo").disabled = true;
			document.getElementById("txfdAppReferenceNo").readOnly = true;
			document.getElementById("btnFindId").disabled = true;
			//document.getElementById("btnNext").disabled = true;
			document.getElementById("btnSave").disabled = true;
			document.getElementById("btnFillTheSame").disabled = true;
			document.getElementById("btnConfirm").disabled = true;
			document.getElementById("btnModify").disabled = true;
			document.getElementById("btnIssuePiv").disabled = true;
			//document.getElementById("btnIssuePiv").style.visibility="hidden";
			document.getElementById("btnAllocate").disabled = true;
			//document.getElementById("btnAllocate").style.visibility="hidden";
			document.getElementById("RadioConfirmPivY").disabled = true;
			document.getElementById("RadioConfirmPivN").disabled = true;
			
			document.getElementById("txfdCsc").readOnly = true;
			document.getElementById("txfdDate").readOnly = true;
			document.getElementById("txfdAllocatedTo").readOnly = true;
			document.getElementById("txfdCostCenterNo").readOnly = true;
			document.getElementById("txfdConfirmedBy").readOnly = true;
			checkRedioButtons();
			//alert("af");
			
			if (document.getElementById("hiddenSecondState").value == "findAppId"){
				//alert(document.getElementById("errorMessage").value.length);
				if(document.getElementById("errorMessage").value.length==0){
					var status=document.getElementById("status").value;
					//alert(status);
					if (status==newStatus||status==modifiedStatus ){
						document.getElementById("btnModify").disabled = false;
						document.getElementById("txfdConfirmedBy").readOnly = true;
						//checkRedioButtons();
					}else if (status==paidStatus||status==confirmedStatus||status==allocatedStatus){
						alert("Can not Modify contact MIS IT Supportive staff");
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
			document.getElementById("txfdAllocatedTo").readOnly = true;
			document.getElementById("txfdApplicationId").readOnly = true;
			document.getElementById("txfdCostCenterNo").readOnly = true;
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
		document.getElementById("txfdAllocatedTo").readOnly = true;
		document.getElementById("txfdCostCenterNo").readOnly = true;
		document.getElementById("txfdConfirmedBy").readOnly = true;
		document.getElementById("txfdServicePostalCode").select();
		document.getElementById("txfdServicePostalCode").facus();
		//alert("2");
	} else if (document.getElementById("state").value == "confirmApp") {
		//alert("confirmApp");
		makeReadOnlyAll();
		disableAllButtons();
		checkRedioButtons();
		if (document.getElementById("hiddenSecondState").value == "findAppId"){
			if(document.getElementById("errorMessage").value.length==0){
				var status=document.getElementById("status").value;
				if (status==confirmedStatus || status==allocatedStatus ){
					alert("already Confirmed");
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
						document.getElementById("txfdAllocatedTo").readOnly = true;
					}else if(status=="C"){
						document.getElementById("btnAllocate").disabled = false;
						document.getElementById("txfdAllocatedTo").readOnly = false;	
					}else if(status==paidStatus){
						alert("Confirm the Application first");
						document.getElementById("btnAllocate").disabled = true;
						document.getElementById("txfdAllocatedTo").readOnly = true;
					}else if(status=="N"||status=="M" ){
						alert("PIV must Confirm first");
						document.getElementById("btnAllocate").disabled = true;
						document.getElementById("txfdAllocatedTo").readOnly = true;
					}
				}else {
					//
				}
			}else{
				//alert("else");
				//document.getElementById("btnFillTheSame").disabled = true;
			}

	} else {
		alert("Different Path");
		makeReadOnlyAll();
		disableAllButtons();

	}
}
	

	
	function checkRedioButtons(){
		var status=document.getElementById("status").value;
		//document.getElementById('RadioMemberOfSamurdhi'+document.getElementById("hiddenOwnership").value).checked =true;
		document.getElementById('RadioOccupy_OwnerCertified'+document.getElementById("hiddenOccupy_OwnerCertified").value).checked =true;
		document.getElementById('RadioIsGovernmentPlace'+document.getElementById("hiddenIsGovernmentPlace").value).checked =true;
		//document.getElementById('RadioLandDispute'+document.getElementById("hiddenLandDispute").value).checked =true;
		document.getElementById('RadioPhase'+document.getElementById("hiddenPhase").value).checked =true;
		document.getElementById('RadioConnectionType'+document.getElementById("hiddenConnectionType").value).checked =true;
		document.getElementById('RadioIsLoopService'+document.getElementById("hiddenIsLoopService").value).checked =true;
		//alert(status);
		if (status==paidStatus||status==confirmedStatus){
		document.getElementById('RadioConfirmPivY').checked =true;
		}else{
			document.getElementById('RadioConfirmPivN').checked =true;
		}
		//alert("endend");
		
	}
	
function setCityCtrl(){
	if(document.getElementById("isColomboRadioTypeN").checked){
		document.getElementById("tdlblServiceCity").style.visibility = "visible";
		document.getElementById("tdtxtServiceCity").style.visibility = "visible";
		document.getElementById("txfdCity").disabled = true;
	}else{
		document.getElementById("tdlblServiceCity").style.visibility = "hidden";
		document.getElementById("tdtxtServiceCity").style.visibility = "hidden";
		document.getElementById("txfdOtherCity").value="";
		document.getElementById("txfdCity").disabled = false;
	}
	
}
function displayCompany(){
	if (document.getElementById("txfdPersonalCorporate").value=="CM"){
		//alert("dscfsdf");
		document.getElementById("txfdNoOfDmgMeters").style.display='';
		document.getElementById("txfdIsVisitingNeeded").style.display='';
		
		//document.getElementById("txfdNoOfDmgMeters").disabled = false;
	}
}

