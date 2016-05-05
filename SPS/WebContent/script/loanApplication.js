function disableSamurdhiDetails(){
	if (document.getElementById('RadioMemberOfSamurdhiN').checked){
		document.getElementById("txfdSamurdhiId").readOnly = true;
		document.getElementById("txfdSharePrice").value = document.getElementById("hid_SharePrice").value;
		document.getElementById("txfdNoOfShares").value = document.getElementById("hid_NoShares").value;
		
	}else if (document.getElementById('RadioMemberOfSamurdhiY').checked){
		document.getElementById("txfdSamurdhiId").readOnly = false;
		document.getElementById("txfdSharePrice").value = "";
		document.getElementById("txfdNoOfShares").value = "";

	}
		
}

function ctrlLoader(){
	
	if(document.getElementById('hid_isNew') && document.getElementById('hid_isNew').value!="" && document.getElementById('hid_isNew').value.length>0)
	{		
		document.getElementById('btnFind').disabled = true;
		document.getElementById('btnSave').value = "Save";
	}
	else{
		document.getElementById('btnFind').disabled = false;
		document.getElementById('btnSave').value = "Update";
		
		if(document.getElementById('hid_isFound') && document.getElementById('hid_isFound').value!="" && document.getElementById('hid_isFound').value.length>0)
			document.getElementById('btnSave').disabled = false;
		else
			document.getElementById('btnSave').disabled = true;
	}		
		
	
	if(document.getElementById('hid_isFound') && document.getElementById('hid_isFound').value!="" && document.getElementById('hid_isFound').value.length>0){
		
	}else{
		if(document.getElementById('RadioMemberOfSamurdhiN'))
			document.getElementById('RadioMemberOfSamurdhiN').checked = true;
		
		if(document.getElementById('RadioisApprovedSDON'))
			document.getElementById('RadioisApprovedSDON').checked = true;

		if(document.getElementById('RadioisApprovedBMN'))
			document.getElementById('RadioisApprovedBMN').checked = true;

		if(document.getElementById('RadioisApprovedDSN'))
			document.getElementById('RadioisApprovedDSN').checked = true;
		
		if(document.getElementById('RadioiisApplyForShareLoanN'))
			document.getElementById('RadioiisApplyForShareLoanN').checked = true;
	
		
	}
	disableSamurdhiDetails();

}

function validate_form(){	

	
	if(validate_required(document.getElementById("txfdapplicationNo").value,"The Application Number must be filled out!")==false){
		document.getElementById("txfdapplicationNo").select();
		return false;
	}
	
	if(document.getElementById('RadioMemberOfSamurdhiY').checked){
		if(validate_required(document.getElementById("txfdSamurdhiId").value,"Samurdhi Id must be filled out!")==false){
			document.getElementById("txfdSamurdhiId").select();
			return false;
		} 		
	}else{
		if (validate_required(document.getElementById("txfdSharePrice").value,"Share Price must be filled out!")==false){
			document.getElementById("txfdSharePrice").select();
			return false;
		}else if (validate_required(document.getElementById("txfdNoOfShares").value,"Number of Shares must be filled out!")==false){
			document.getElementById("txfdNoOfShares").select();
			return false;
		}
	}
	
	 var answer = confirm ("Are you sure you want to confirm the loan application?");
		if (!answer)
			return false;
	
	return true;
}

function validate_required(fieldValue,alerttxt){

	  if (fieldValue==null||fieldValue=="" ||fieldValue.length==0)
	    {
	    alert(alerttxt);
	    return false;
	  }else{
	    return true;
	   }
	  
	}

function setDefaultCheck(){
	
	if(document.getElementById('RadioMemberOfSamurdhiN'))
		document.getElementById('RadioMemberOfSamurdhiN').checked = true;
	
	if(document.getElementById('RadioisApprovedSDON'))
		document.getElementById('RadioisApprovedSDON').checked = true;

	if(document.getElementById('RadioisApprovedBMN'))
		document.getElementById('RadioisApprovedBMN').checked = true;

	if(document.getElementById('RadioisApprovedDSN'))
		document.getElementById('RadioisApprovedDSN').checked = true;	

	
	document.getElementById("txfdapplicationNo").value="";	
	document.getElementById("txfdSamurdhiId").value="";
	document.getElementById("txfdSharePrice").value="";
	document.getElementById("txfdNoOfShares").value="";
	document.getElementById("txfdaapprovedBy").value="";
	document.getElementById("hid_isFound").value="";
	document.getElementById("statusMsg").value="";
	document.getElementById("statusMsgErr").value="";
	document.getElementById("txfdidNo").value="";
	
	document.getElementById("txfdNoOfShares").readOnly=true;
	document.getElementById("txfdSamurdhiId").readOnly=true;
	document.getElementById("txfdSharePrice").readOnly=true;
	
	return false;
}

function setLoanDetails(){
	
	var loanamtChk= document.getElementById("txfdLoanAmount").value;
	
	if(loanamtChk!=null && loanamtChk!="" && loanamtChk.length>0){
		var loanAmt = parseFloat(document.getElementById("txfdLoanAmount").value);		
		var maxLoanAmt = parseFloat(document.getElementById("hid_MaxLoanAmt").value);
		var maxSamurdhiLoanAmt = parseFloat(document.getElementById("hid_MaxSamurdhiLoanAmt").value);		
		
			var noOfInstallments = parseFloat(document.getElementById("txfdnofInstallments").value);
			var monthlyIns = loanAmt/(noOfInstallments);
			var interestrate = parseFloat(document.getElementById("txfdinterestrate").value);
			var tempterest = (loanAmt/24)*(interestrate/100);
			var monthlyInterest = (tempterest*((noOfInstallments)+1))/(noOfInstallments);
			
			var totPayment = (monthlyIns + monthlyInterest)*(noOfInstallments)-loanAmt;
			
			document.getElementById("txfdinstallments").value = monthlyIns + monthlyInterest;
			document.getElementById("txfdtotInterest").value = totPayment;
		
	}		
}

function validate_AppNo(){
	if(validate_required(document.getElementById("txfdapplicationNo").value,"The Application Number must be filled out!")==false)
	{
		document.getElementById("txfdapplicationNo").select();
		return false;
	} else
		return true;
	
}
