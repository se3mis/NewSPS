function validate_required(fieldValue,alerttxt){

  if (fieldValue==null||fieldValue=="")
    {
    alert(alerttxt);
    return false;
  }else{
    return true;
    }
  
}

function validate_form(){
	if(validate_required(document.getElementById("txfdApplicationId").value,"ApplicationId must be filled out!")==false){
		document.getElementById("txfdApplicationId").select();
		return false;
	}
	else if (validate_required(document.getElementById("txfdCostCenterNo").value,"Cost Center No must be filled out!")==false){
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
	}else if(validate_required(document.getElementById("txfdAssessmentNo").value,"AssessmentNo must be filled out!")==false){
		document.getElementById("txfdAssessmentNo").select();
		return false;	
	}else if(validate_required(document.getElementById("txfdTariffCode").value,"TariffCode must be filled out!")==false){
		document.getElementById("txfdTariffCode").select();
		return false;
	}else if(validate_required(document.getElementById("txfdCustomerCategory").value,"CustomerCategory must be filled out!")==false){
		document.getElementById("txfdCustomerCategory").select();
		return false;	
	}
	
		
 }

function buttonLoader() {
	//alert(document.getElementById("state").value);
	if (document.getElementById("state").value == "fromApp") {
		alert("fromApp");
		//document.getElementById("btnFindId").disabled = true;
		//document.getElementById("btnNext").disabled = true;
		//document.getElementById("btnSave").disabled = true;
		//document.getElementById("btnModify").disabled = true;
		//document.getElementById("btnFillTheSame").disabled = true;
		document.getElementById("txtApprovedBy").readOnly = true;
		//document.getElementById('RadioApplicationTypeCR').checked =true;
		//document.getElementById("chvJobCategory").options[1].selected=true;
		//alert("ffff");
		
			
	} else if (document.getElementById("state").value == "newApplication") {
		alert("newApplication");
		document.getElementById("btnFindAppId").disabled = true;
		document.getElementById("btnModify").disabled = true;
		document.getElementById("btnNext").disabled = true;
		document.getElementById("btnSave").disabled = true;
		document.getElementById("btnFillTheSame").disabled = true;
		document.getElementById("txtApprovedBy").readOnly = true;
		
	} else if (document.getElementById("state").value == "newCustomer") {
		alert("newCustomer");
		document.getElementById("btnFindAppId").disabled = true;
		document.getElementById("btnFindId").disabled = true;
		document.getElementById("btnModify").disabled = true;
		document.getElementById("txtApprovedBy").readOnly = true;
		
	} else if (document.getElementById("state").value == "findId") {
		alert("findId");
		document.getElementById("btnFindAppId").disabled = true;
		document.getElementById("btnModify").disabled = true;
		document.getElementById("txtApprovedBy").readOnly = true;
		
	} else if (document.getElementById("state").value == "findAppId") {
		alert("findAppId");
		document.getElementById("btnFindId").disabled = true;
		document.getElementById("btnSave").disabled = true;
		document.getElementById("txtApprovedBy").readOnly = true;
		checkRedioButtons();
	} else if (document.getElementById("state").value == "fillTheSame") {
		alert("fillTheSame");
		document.getElementById("btnFindAppId").disabled = true;
		document.getElementById("btnModify").disabled = true;
		document.getElementById("txtApprovedBy").readOnly = true;
		checkRedioButtons();	

	} else {
		alert("Different Path");

	}

	
}
