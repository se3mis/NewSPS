var newStatus="N";
var modifiedStatus="M";
var confirmedStatus="C";

function test(){
	alert("hello");
}

function disableChequeNo(){
	//alert("jhfgf");
	//alert(document.getElementsByName("txfdReferenceNo").value);
	
	if (document.getElementById('RadioPaymentModeQ').checked){
		//document.getElementById("txfdChequeBankCode").readOnly = false;
		document.getElementById("txfdChequeBankCode").disabled = false;
		document.getElementById("chequeBranchCode").disabled = false;
		document.getElementById("txfdChequeNo").readOnly = false;
		document.getElementById("txfdAllocatedChecAmount").readOnly = false;
		document.getElementById("txfdPayDate").disabled = true;
		//alert("jhfgfeee");
	}else {
		//document.getElementById("txfdChequeBankCode").readOnly = true;
		document.getElementById("txfdChequeBankCode").disabled = true;
		document.getElementById("chequeBranchCode").disabled = true;
		document.getElementById("txfdChequeNo").readOnly = true;
		document.getElementById("txfdAllocatedChecAmount").readOnly = false;
		document.getElementById("txfdPayDate").disabled = false;
		
	}
}

function validate_required(fieldValue,alerttxt){

	  if (fieldValue==null||fieldValue=="")
	    {
	    alert(alerttxt);
	    return false;
	  }else{
	    return true;//true
	    }
	  
	}

	function validate_form(){
		if(validate_required(document.getElementById("txfdPivNo").value,"Piv No must be filled out!")==false){
			document.getElementById("txfdPivNo").select();
			return false;
		}else if(validate_required(document.getElementById("txfdReferenceNo").value,"Reference No must be filled out!")==false){
			document.getElementById("txfdReferenceNo").select();
			return false;
		}else if (validate_required(document.getElementById("txfdCostCenterNo").value,"Cost Center No must be filled out!")==false){
			document.getElementById("txfdCostCenterNo").select();
			return false;
		}else if (validate_required(document.getElementById("txfdIdNo").value,"Id No must be filled out!")==false){
			document.getElementById("txfdIdNo").select();
			return false;
		}else if(validate_required(document.getElementById("txfdFullName").value,"Full Name must be filled out!")==false){
			document.getElementById("txfdFullName").select();
			return false;
		}else if(validate_required(document.getElementById("txfdFullAddress").value,"Full Address must be filled out!")==false){
			document.getElementById("txfdFullAddress").select();
			return false;
		}else if (document.getElementById('RadioPaymentModeQ').checked){
			//validate_cheque_details();
			if(validate_required(document.getElementById("txfdChequeBankCode").value,"Cheque Bank Code must be filled out!")==false){
				//document.getElementById("txfdChequeBankCode").select();
				return false;
			}else if(validate_required(document.getElementById("txfdChequeBranchCode").value,"Cheque Branch Code must be filled out!")==false){	
				//document.getElementById("txfdChequeBranchCode").select();
				return false;
			}else if(validate_required(document.getElementById("txfdChequeNo").value,"Cheque No must be filled out!")==false){		
				//document.getElementById("txfdChequeNo").select();
				return false;
			}
		}
			
	 }
	
	function validate_formConfirm(){
		//alert(";kvjsdjfsdp");
		//alert(document.getElementsByTagName("payDate").value);
		//alert(";kvjsdjfsdp");
		if(validate_required(document.getElementById("txfdPivNo").value,"Piv No must be filled out!")==false){
			document.getElementById("txfdPivNo").select();
			return false;
		}else if(validate_required(document.getElementById("txfdPivReceiptNo").value,"Piv Receipt No must be filled out!")==false){
				document.getElementById("txfdPivNo").select();
				return false;	
		}else if(validate_required(document.getElementById("txfdReferenceNo").value,"Reference No must be filled out!")==false){
			document.getElementById("txfdReferenceNo").select();
			return false;
		}else if (validate_required(document.getElementById("txfdCostCenterNo").value,"Cost Center No must be filled out!")==false){
			document.getElementById("txfdCostCenterNo").select();
			return false;
		}else if (validate_required(document.getElementById("txfdIdNo").value,"Id No must be filled out!")==false){
			document.getElementById("txfdIdNo").select();
			return false;
		}else if(validate_required(document.getElementById("txfdFullName").value,"Full Name must be filled out!")==false){
			document.getElementById("txfdFullName").select();
			return false;
		}else if(validate_required(document.getElementById("txfdFullAddress").value,"Full Address must be filled out!")==false){
			document.getElementById("txfdFullAddress").select();
			return false;
		}else if (document.getElementById('RadioPaymentModeQ').checked){
			//validate_cheque_details();
			if(validate_required(document.getElementById("txfdChequeBankCode").value,"Cheque Bank Code must be filled out!")==false){
				//document.getElementById("txfdChequeBankCode").select();
				return false;
			}else if(validate_required(document.getElementById("txfdChequeBranchCode").value,"Cheque Branch Code must be filled out!")==false){	
				//document.getElementById("txfdChequeBranchCode").select();
				return false;
			}else if(validate_required(document.getElementById("txfdChequeNo").value,"Cheque No must be filled out!")==false){		
				//document.getElementById("txfdChequeNo").select();
				return false;
			}
		}
			
	 }
	
	function validate_formModify(){
		//alert(";kvjsdjfsdp");
		//alert(document.getElementsByTagName("payDate").value);
		//alert(";kvjsdjfsdp");
		if(validate_required(document.getElementById("txfdPivNo").value,"Piv No must be filled out!")==false){
			document.getElementById("txfdPivNo").select();
			return false;
		}else if(validate_required(document.getElementById("txfdPivReceiptNo").value,"Piv Receipt No must be filled out!")==false){
				document.getElementById("txfdPivNo").select();
				return false;	
		}else if(validate_required(document.getElementById("txfdReferenceNo").value,"Reference No must be filled out!")==false){
			document.getElementById("txfdReferenceNo").select();
			return false;
		}else if (validate_required(document.getElementById("txfdCostCenterNo").value,"Cost Center No must be filled out!")==false){
			document.getElementById("txfdCostCenterNo").select();
			return false;
		}else if (validate_required(document.getElementById("txfdIdNo").value,"Id No must be filled out!")==false){
			document.getElementById("txfdIdNo").select();
			return false;
		}else if(validate_required(document.getElementById("txfdFullName").value,"Full Name must be filled out!")==false){
			document.getElementById("txfdFullName").select();
			return false;
		}else if((dojo.widget.byId("txfdPayDate").getValue()==null)||(dojo.widget.byId("txfdPayDate").getValue()=="") ){
			alert("Please fille the Pay Date.");
			return false;	
		}else if (document.getElementById('RadioPaymentModeQ').checked){
			//validate_cheque_details();
			if(validate_required(document.getElementById("txfdChequeBankCode").value,"Cheque Bank Code must be filled out!")==false){
				//document.getElementById("txfdChequeBankCode").select();
				return false;
			}else if(validate_required(document.getElementById("txfdChequeBranchCode").value,"Cheque Branch Code must be filled out!")==false){	
				//document.getElementById("txfdChequeBranchCode").select();
				return false;
			}else if(validate_required(document.getElementById("txfdChequeNo").value,"Cheque No must be filled out!")==false){		
				//document.getElementById("txfdChequeNo").select();
				return false;
			}
		}
			
	 }	

function validate_cheque_details() {
	if(validate_required(document.getElementById("txfdChequeBankCode").value,"Cheque Bank Code must be filled out!")==false){
		document.getElementById("txfdChequeBankCode").select();
		return false;
	}else if(validate_required(document.getElementById("txfdChequeBranchCode").value,"Cheque Branch Code must be filled out!")==false){	
		document.getElementById("txfdChequeBranchCode").select();
		return false;
	}else if(validate_required(document.getElementById("txfdChequeNo").value,"Cheque No must be filled out!")==false){		
		document.getElementById("txfdChequeBankCode").readOnly = true;
		return false;
	}
		
	}
	

function buttonLoader() {
	//alert("hi");
		//alert(document.getElementById("serviceCity").value);
		//alert(document.getElementById("state").value);
		//alert(document.getElementById("hiddenSecondState").value);
		if (document.getElementById("state").value == "fromApp") {
			//alert("fromApp");
			//For cheque
			document.getElementById('RadioPaymentModeC').checked=true;
			//document.getElementById('RadioPaymentModeQ').disabled=true;
			document.getElementById("txfdPayDate").childNodes[1].disabled = true;
			disableChequeNo();
			//common
			
			commonDisableTags();
			document.getElementById("txfdAmountInWords").value = "Two Thousand Rupees Only";
			document.getElementById("txfdMiscellaneousIncome").value = "0.00";
			document.getElementById("txfdApplicationFee").value = "2000.00";
			document.getElementById("txfdSubTotal").value = "2000.00";
			document.getElementById("txfdGrandTotal").value = "2000.00";
			//Buttons
			document.getElementById("btnFindPivNo").disabled = true;
			//document.getElementById("btnSave").disabled = true;
			document.getElementById("btnConfirm").disabled = true;
			
			//	if (document.getElementById("hiddenSecondState").value == "findPivNo") {
			//		alert("findPiv");
			//		document.getElementById("btnSave").disabled = false;
			//	}
			
			//document.getElementById('RadioApplicationTypeCR').checked =true;
			//document.getElementById("chvJobCategory").options[1].selected=true;
			//alert("ffff");
		}else if (document.getElementById("state").value == "confirm") {
			//alert("confirm");
			//For cheque
			document.getElementById('RadioPaymentModeC').checked=true;
			//document.getElementById('RadioPaymentModeQ').disabled=true;
			disableChequeNo();
			//common
			commonDisableTags();
			//alert("confirm1");
			//Buttons
			document.getElementById("btnSave").disabled = true;
			document.getElementById("btnConfirm").disabled = true;
			if (document.getElementById("hiddenSecondState").value == "findPivNo") {
				//alert("findPiv1");
				//alert(document.getElementById("errorMessage").value.length);
				if(document.getElementById("errorMessage").value.length==0){
					//alert("findPiv2");
					var status=document.getElementById("status").value;
					//alert(status);
					if (status==newStatus||status==modifiedStatus ){
						checkRedioButtons();
						disableChequeNo();
						document.getElementById("btnConfirm").disabled = false;
					}else if (status==confirmedStatus ){
						checkRedioButtons();
						disableChequeNo();
						alert("Already Confirmed.");
						document.getElementById("btnConfirm").disabled = true;
					}
				}else{
					document.getElementById("btnConfirm").disabled = false;
				}
			}else{}
			
		}else if (document.getElementById("state").value == "modifyDirect" || document.getElementById("state").value == "modify") {
			//alert("confirm");
			//For cheque
			document.getElementById('RadioPaymentModeC').checked=true;
			//document.getElementById('RadioPaymentModeQ').disabled=true;
			disableChequeNo();
			//common
			commonDisableTags();
			
			//Buttons
			document.getElementById("btnSave").disabled = true;
			document.getElementById("btnConfirm").disabled = true;
			if (document.getElementById("hiddenSecondState").value == "findPivNo") {
				//alert("findPiv1");
				//alert(document.getElementById("errorMessage").value.length);
				if(document.getElementById("errorMessage").value.length==0){
					//alert("findPiv2");
					var status=document.getElementById("status").value;
					//alert(status);
					if (status==newStatus||status==modifiedStatus ){
						checkRedioButtons();
						disableChequeNo();
						//document.getElementById("btnConfirm").disabled = false;
					}else if (status==confirmedStatus ){
						checkRedioButtons();
						disableChequeNo();
						//alert("Already Confirmed.");
						//document.getElementById("btnConfirm").disabled = true;
					}
				}else{
					//document.getElementById("btnConfirm").disabled = false;
				}
			}else{}			
		} else if (document.getElementById("state").value == "view") {
			//alert("view");
			//document.getElementById("txfdCostCenterNo").readOnly = true;
			//For cheque
			document.getElementById('RadioPaymentModeC').checked=true;
			//document.getElementById('RadioPaymentModeQ').disabled=true;
			disableChequeNo();
			//alert("1");
			//common
			commonDisableTags();
			//alert("2");
			//Buttons
			document.getElementById("btnSave").disabled = true;
			document.getElementById("btnConfirm").disabled = true;
			if (document.getElementById("hiddenSecondState").value == "findPivNo") {
				if(document.getElementById("errorMessage").value.length==0){
					//alert("findPiv");
					checkRedioButtons();
					disableChequeNo();
					document.getElementById("btnConfirm").disabled = true;
				}else {
					//
				}
			}else{
				//
			}
			
		} else if (document.getElementById("state").value == "printPIV") {
			//alert("view");
			//document.getElementById("txfdCostCenterNo").readOnly = true;
			//For cheque
			document.getElementById('RadioPaymentModeC').checked=true;
			//document.getElementById('RadioPaymentModeQ').disabled=true;
			disableChequeNo();
			//alert("1");
			//common
			commonDisableTags();
			//alert("2");
			//Buttons
			document.getElementById("btnSave").disabled = true;
			document.getElementById("btnConfirm").disabled = true;
			if (document.getElementById("hiddenSecondState").value == "findPivNo") {
				if(document.getElementById("errorMessage").value.length==0){
					//alert("findPiv");
					checkRedioButtons();
					disableChequeNo();
					document.getElementById("btnConfirm").disabled = true;
				}else {
					//
				}
			}else{
				//
			}	
		} else if (document.getElementById("state").value == "new") {
			//alert("new");
			//document.getElementById("txfdCostCenterNo").readOnly = true;
			//For cheque
			document.getElementById('RadioPaymentModeC').checked=true;
			//document.getElementById('RadioPaymentModeQ').disabled=true;
			disableChequeNo();
			//alert("1");
			//common
			commonDisableTags();
			document.getElementById("txfdPivNo").readOnly = true;
			document.getElementById("txfdPayDate").childNodes[1].disabled = true;
			//alert("2");
			//Buttons
			document.getElementById("btnSave").disabled = true;
			document.getElementById("btnConfirm").disabled = true;
			if (document.getElementById("hiddenSecondState").value == "findByReferenceNo") {
				if(document.getElementById("errorMessage").value.length==0){
					//alert("findPiv");
					//checkRedioButtons();
					//alert("3");
					disableChequeNo();
					//alert("4");
					document.getElementById("btnConfirm").disabled = true;
					//alert("5");
					document.getElementById("btnSave").disabled = false;
					//alert("6");
				}else {
					//
				}
			}else{
				//
			}	
		} else if (document.getElementById("state").value == "issueReInsp") {
			//alert("new");
			//document.getElementById("txfdCostCenterNo").readOnly = true;
			//For cheque
			document.getElementById('RadioPaymentModeC').checked=true;
			//document.getElementById('RadioPaymentModeQ').disabled=true;
			disableChequeNo();
			//alert("1");
			//common
			commonDisableTags();
			document.getElementById("txfdPivNo").readOnly = true;
			document.getElementById("txfdPayDate").childNodes[1].disabled = true;
			//alert("2");
			//Buttons
			document.getElementById("btnSave").disabled = true;
			document.getElementById("btnConfirm").disabled = true;
			if (document.getElementById("hiddenSecondState").value == "findByReferenceNo") {
				if(document.getElementById("errorMessage").value.length==0){
					//alert("findPiv");
					//checkRedioButtons();
					//alert("3");
					disableChequeNo();
					//alert("4");
					document.getElementById("btnConfirm").disabled = true;
					//alert("5");
					document.getElementById("btnSave").disabled = false;
					//alert("6");
				}else {
					//
				}
			}else{
				//
			}
		} else if (document.getElementById("state").value == "afterestimated" || document.getElementById("state").value == "afterestimatedDone") {
			//alert("new");
			//document.getElementById("txfdCostCenterNo").readOnly = true;
			//For cheque
			document.getElementById('RadioPaymentModeC').checked=true;
			document.getElementById('RadioPaymentModeQ').disabled=true;
			disableChequeNo();
			//alert("1");
			//common
			commonDisableTags();
			document.getElementById("txfdReferenceNo").readOnly = false;
			document.getElementById("txfdPivNo").readOnly = true;
			document.getElementById("txfdPayDate").childNodes[1].disabled = true;
			//alert("2");
			//Buttons
			document.getElementById("btnSave").disabled = true;
			document.getElementById("btnConfirm").disabled = true;
			if (document.getElementById("hiddenSecondState").value == "findByReferenceNo") {
				if(document.getElementById("errorMessage").value.length==0){
					//alert("findPiv");
					//checkRedioButtons();
					//alert("3");
					disableChequeNo();
					//alert("4");
					document.getElementById("btnConfirm").disabled = true;
					//alert("5");
					//document.getElementById("btnSave").disabled = false;
					//alert("6");
				}else {
					//
				}
			}else{
				//
			}	
				
		}else if (document.getElementById("state").value == "confirmReInsp") {
			//alert("confirm");
			//For cheque
			document.getElementById('RadioPaymentModeC').checked=true;
			//document.getElementById('RadioPaymentModeQ').disabled=true;
			disableChequeNo();
			//common
			commonDisableTags();
			
			//Buttons
			document.getElementById("btnSave").disabled = true;
			document.getElementById("btnConfirm").disabled = true;
			if (document.getElementById("hiddenSecondState").value == "findPivNo") {
				//alert("findPiv1");
				//alert(document.getElementById("errorMessage").value.length);
				if(document.getElementById("errorMessage").value.length==0){
					//alert("findPiv2");
					var status=document.getElementById("status").value;
					//alert(status);
					if (status==newStatus||status==modifiedStatus ){
						checkRedioButtons();
						disableChequeNo();
						document.getElementById("btnConfirm").disabled = false;
					}else if (status==confirmedStatus ){
						checkRedioButtons();
						disableChequeNo();
						alert("Already Confirmed.");
						document.getElementById("btnConfirm").disabled = true;
					}
				}else{
					document.getElementById("btnConfirm").disabled = false;
				}
			}else{}			
		}else if (document.getElementById("state").value == "issuePiv") {
			//alert("issuePiv");
			//For cheque
			document.getElementById('RadioPaymentModeC').checked=true;
			//document.getElementById('RadioPaymentModeQ').disabled=true;
			disableChequeNo();
			//common
			commonDisableTags();
			//document.getElementById("txfdAmountInWords").value = "Two Hundred and Five Rupees Only";
			//document.getElementById("txfdMiscellaneousIncome").value = "250.00";
			//document.getElementById("txfdSubTotal").value = "250.00";
			//document.getElementById("txfdGrandTotal").value = "250.00";
			//Buttons
			document.getElementById("btnFindPivNo").disabled = true;
			//document.getElementById("btnSave").disabled = true;
			document.getElementById("btnConfirm").disabled = true;
			
			//	if (document.getElementById("hiddenSecondState").value == "findPivNo") {
			//		alert("findPiv");
			//		document.getElementById("btnSave").disabled = false;
			//	}
			
			//document.getElementById('RadioApplicationTypeCR').checked =true;
			//document.getElementById("chvJobCategory").options[1].selected=true;
			//alert("ffff");
			
		}else if (document.getElementById("state").value == "save") {
			//alert("save");
			//For cheque
			document.getElementById('RadioPaymentModeC').checked=true;
			//document.getElementById('RadioPaymentModeQ').disabled=true;
			disableChequeNo();
			//common
			commonDisableTags();
			//document.getElementById("txfdAmountInWords").value = "Two Hundred and Five Rupees Only";
			//document.getElementById("txfdMiscellaneousIncome").value = "250.00";
			//document.getElementById("txfdSubTotal").value = "250.00";
			//document.getElementById("txfdGrandTotal").value = "250.00";
			//Buttons
			document.getElementById("btnFindPivNo").disabled = true;
			document.getElementById("btnSave").disabled = true;
			document.getElementById("btnConfirm").disabled = true;
			callPrintEstimate();
			//alert("save end");
			//	if (document.getElementById("hiddenSecondState").value == "findPivNo") {
			//		alert("findPiv");
			//		document.getElementById("btnSave").disabled = false;
			//	}
			
			//document.getElementById('RadioApplicationTypeCR').checked =true;
			//document.getElementById("chvJobCategory").options[1].selected=true;
			//alert("ffff");	
			
		} else {
			alert("Different Path");
			//For cheque
			//document.getElementById('RadioPaymentModeC').checked=true;
			//document.getElementById('RadioPaymentModeQ').disabled=true;
			disableChequeNo();
			//common
			commonDisableTags();

		}
		
	}

function checkPivNoNCostcenterNotNull() {
	if((document.getElementById("txfdPivNo").value==null)||(document.getElementById("txfdPivNo").value=="") ){
		alert("PivNo To must be filled out!");
		document.getElementById("txfdPivNo").select();
		return false;
	}else if((document.getElementById("txfdCostCenterNo").value==null)||(document.getElementById("txfdCostCenterNo").value=="") ){
		alert("Cost Center No To must be filled out!");
		document.getElementById("txfdCostCenterNo").select();
		return false;
	}
		
	else return true;
	
}	
	
function textNull(value) {
		alert(document.getElementById("txfdChequeBankCode").readOnly == true);
		alert(document.getElementById("txfdCostCenterNo").readOnly == true);
		//alert("svknknv");
		
		
	}
function setSubTotal() {
	//alert(Number (document.getElementById("txfdMiscellaneousIncome").value)+Number (document.getElementById("txfdElectricityDebtors").value));
	var subTotal;
	var grandTotal;
	subTotal=Number (document.getElementById("txfdMiscellaneousIncome").value)+
	Number (document.getElementById("txfdElectricityDebtors").value)+
	Number (document.getElementById("txfdSecurityDeposit").value)+
	Number (document.getElementById("txfdSerConnOrElecSch").value)+
	Number (document.getElementById("txfdTenderDeposit").value)+
	Number (document.getElementById("txfdMiscellaneousDeposit").value)+
	Number (document.getElementById("txfdCashInTransit").value)+
	Number (document.getElementById("txfdForDishonouredCheque").value);
	//alert(subTotal);
	document.getElementById("txfdSubTotal").value=subTotal;
	//
	grandTotal=Number(document.getElementById("txfdSubTotal").value)+
	Number(document.getElementById("txfdVat").value);
	document.getElementById("txfdGrandTotal").value=grandTotal;
	
	
	
}
function setGrandTotal() {
	var grandTotal;
	grandTotal=Number(document.getElementById("txfdSubTotal").value)+
			Number(document.getElementById("txfdVat").value);
	document.getElementById("txfdGrandTotal").value=grandTotal;
	
}

function checkRedioButtons(){
	//alert(document.getElementById("hiddenPaymentMode").value);
	document.getElementById('RadioPaymentMode'+document.getElementById("hiddenPaymentMode").value).checked =true;
	//alert("endend");
	
}

function commonDisableTags(){
	//alert("1");
	document.getElementById("txfdCostCenterNo").readOnly = true;
	document.getElementById("txfdCebBranch").readOnly = true;
	//document.getElementById("txfdCebBranch").readOnly = true;
	//alert("2");
	document.getElementById("txfdDate").readOnly = true;
	document.getElementById("txfdReferenceNo").readOnly = true;
	//alert("3");
	document.getElementById("txfdIdNo").readOnly = true;
	document.getElementById("txfdFullName").readOnly = true;
	document.getElementById("txfdFullAddress").readOnly = true;
	document.getElementById("txfdApprovedBy").readOnly = true;
	document.getElementById('RadioPaymentModeC').checked =true;
	//alert("4");
	document.getElementById("txfdChequeNo").readOnly = true;
	document.getElementById("txfdChequeBankCode").readOnly = true;
	document.getElementById("txfdChequeBranchCode").readOnly = true;
	document.getElementById("txfdAmountInWords").readOnly = true;
	document.getElementById("txfdDescription").readOnly = true;
	document.getElementById("txfdPreparedBy").readOnly = true;
	//
	//alert("5");
	document.getElementById("txfdMiscellaneousIncome").readOnly = true;
	document.getElementById("txfdElectricityDebtors").readOnly = true;
	document.getElementById("txfdSecurityDeposit").readOnly = true;
	document.getElementById("txfdSerConnOrElecSch").readOnly = true;
	document.getElementById("txfdTenderDeposit").readOnly = true;
	document.getElementById("txfdMiscellaneousDeposit").readOnly = true;
	//alert("7");
	document.getElementById("txfdCashInTransit").readOnly = true;
	document.getElementById("txfdForDishonouredCheque").readOnly = true;
	document.getElementById("txfdSubTotal").readOnly = true;
	document.getElementById("txfdVat").readOnly = true;
	document.getElementById("txfdGrandTotal").readOnly = true;
	//document.getElementById("txfdLoanReference").readOnly = true;
	//document.getElementById("txfdLoanAmount").readOnly = true;
	//alert("8");
}

function checkEstimateNoNotNull(){
	if((document.getElementById("estimateNo").value==null)||(document.getElementById("estimateNo").value=="") ){
		alert("Please type an Estimate Number.");
		document.getElementById("estimateNo").select();
		return false;
	}
	else return true;
}

function pivConfirmValidate(){
	var retVal = true;
	if((dojo.widget.byId("txtPaidDate").getValue()==null)||(dojo.widget.byId("txtPaidDate").getValue()=="") ){
		alert("Please key in date paid.");
		//document.getElementById("txtPaidDate").select();
		retVal = false;
	}
	if(retVal)
	{
		if (document.getElementById('paymentModeQ').checked)
		{
			if((document.getElementById("txfdChequeBankCode").value==null)||(document.getElementById("txfdChequeBankCode").value=="") ){
				alert("Please select Bank.");
				document.getElementById("txfdChequeBankCode").focus();
				retVal = false;
			}
			else if((document.getElementById("txfdChequeBranchCode")==null || document.getElementById("txfdChequeBranchCode").value==null)||(document.getElementById("txfdChequeBranchCode").value=="") ){
				alert("Please select Bank Branch.");
				document.getElementById("txfdChequeBranchCode").focus();
				retVal = false;
			}
			else if((document.getElementById("txfdChequeNo").value==null)||(document.getElementById("txfdChequeNo").value=="") ){
				alert("Please key in cheque no.");
				document.getElementById("txfdChequeNo").select();
				retVal = false;
			}
			else if((dojo.widget.byId("txtChequeDate").getValue()==null)||(dojo.widget.byId("txtChequeDate").getValue()=="") ){
				alert("Please key in cheque date.");
				retVal = false;
			}
		}
	}
	return retVal;
}

function formView()
{
	if((document.getElementById("isConfirm").value!=null)&&(document.getElementById("isConfirm").value=="true") )
	{
		//document.getElementById("txtLoanReference").readOnly = true;
		//document.getElementById("txtLoanAmount").readOnly = true;
		dojo.widget.byId("txtPaidDate").disabled = false;
	}
	else if((document.getElementById("isGenerate").value!=null)&&(document.getElementById("isGenerate").value=="true") )
	{
		document.frmPiv.paymentMode[0].disabled = true;
		document.frmPiv.paymentMode[1].disabled = true;
		dojo.widget.byId("txtPaidDate").disabled = true;
	}
	else
	{
		document.getElementById("txtPayingBranchCode").readOnly = true;
		//document.getElementById("txtLoanReference").readOnly = true;
		//document.getElementById("txtLoanAmount").disabled = true;
		document.frmPiv.paymentMode[0].disabled = true;
		document.frmPiv.paymentMode[1].disabled = true;
		document.getElementById("txtDescription").readOnly = true;
		dojo.widget.byId("txtPaidDate").disabled = true;
	}
	document.getElementById("txfdChequeBankCode").disabled = true;
	document.getElementById("txtPaidDate").childNodes[1].disabled = true;
	document.getElementById("txtChequeDate").childNodes[1].disabled = true;
	dojo.widget.byId("txtChequeDate").disabled = true;
	
	
}

function estimatePIVFormValidate()
{
	if((document.getElementById("pivNo").value==null)||(document.getElementById("pivNo").value=="") ){
		alert("Please key in PIV Number.");
		document.getElementById("pivNo").select();
		return false;
	}
	else if((document.getElementById("txtPayingBranchCode").value==null)||(document.getElementById("txtPayingBranchCode").value=="") ){
		alert("Please key in People's Bank Branch Code.");
		document.getElementById("txtPayingBranchCode").select();
		return false;
	}
	
	else return true;
}




function setAppletValues()
{
       //var P_bank = document.getElementById("txfdPayingBankName").value;
       //var P_branchName = document.getElementById("txfdPayingBranchName").value;
       var P_cebBranch = document.getElementById("txfdCebBranch").value;
       var P_costCentre = document.getElementById("txfdCostCenterNo").value;
      var P_date = document.getElementById("txfdDate").value;
       var P_referenceNumber = document.getElementById("txfdReferenceNo").value;
       var P_PIVNumber = document.getElementById("txfdPivNo").value;
       var P_jobDescription = document.getElementById("txfdDescription").value;
       var P_depositorID = document.getElementById("txfdIdNo").value;
       var P_depositerName = document.getElementById("txfdFullName").value;
       var P_streetAddress = document.getElementById("txfdFullAddress").value;
       //var P_suburb = document.getElementById("subUrb").value;
       //var P_postalCode = var PostalCode").value;
       //var P_city = document.getElementById("city").value;
       var P_bankCode = document.getElementById("txfdPayingBankCode").value;
       var P_branchCode = document.getElementById("txfdPayingBranchCode").value;
       var P_cehqueNo = document.getElementById("txfdChequeNo").value;
       //var P_chequeDate = document.getElementById("txtChequeDate").value;
       //var P_chequeDate = dojo.widget.byId("txtChequeDate").getValue();
       var P_amountInWords = document.getElementById("txfdAmountInWords").value;
       //var P_amountInFigures = document.getElementById("amountInWords").value;
       var P_preparedBy = document.getElementById("txfdPreparedBy").value;
       var P_miscellaneousIncome = document.getElementById("txfdMiscellaneousIncome").value;
       var P_electricityDebtors = document.getElementById("txfdElectricityDebtors").value;
       var P_securityDeposit = document.getElementById("txfdSecurityDeposit").value;
       var P_serviceConnOrElecSchemes = document.getElementById("txfdSerConnOrElecSch").value;
       var P_tenderDeposit = document.getElementById("txfdTenderDeposit").value;
       var P_miscellaneousDeposit = document.getElementById("txfdMiscellaneousDeposit").value;
       var P_cashInTransit = document.getElementById("txfdCashInTransit").value;
       var P_forDishonouredCheques = document.getElementById("txfdForDishonouredCheque").value;
       var P_subTotal = document.getElementById("txfdSubTotal").value;
       var P_vat = document.getElementById("txfdVat").value;
       var P_grandTot = document.getElementById("txfdGrandTotal").value;
       //var P_loanAmnt = document.getElementById("txfdLoanReference").value;
       //var P_loanRef = document.getElementById("txfdLoanAmount").value;
       if(dojo.widget.byId("txfdChequeDate") == undefined)
    	   var P_chequeDate = "";
       else
    	   var P_chequeDate = dojo.widget.byId("txfdChequeDate").getValue();
       
      
       
}

function callPrintEstimate()  { 
	//alert("callPrintEstimate()");
	//alert(document.getElementById("txfdFullAddress").value);
	var p_region =document.getElementById("region").value;
	var p_bank =document.getElementById("txfdPayingBankName").value;
    var p_branchName =document.getElementById("txfdPayingBranchName").value;
	var p_cebBranch = document.getElementById("txfdCebBranch").value;
    var p_costCentre = document.getElementById("txfdCostCenterNo").value;
    var p_date = document.getElementById("txfdDate").value;
    var p_referenceNumber = document.getElementById("txfdReferenceNo").value;
    var p_PIVNumber = document.getElementById("txfdPivNo").value;
    var p_jobDescription = document.getElementById("txfdDescription").value;
    var p_depositorID = document.getElementById("txfdIdNo").value;
    var p_depositerName = document.getElementById("txfdFullName").value;
    var p_FullAddress = document.getElementById("txfdFullAddress").value;
    
    //var address = document.getElementById("txfdappAddress").value;
  
    var p_streetAddress;
    var p_suburb;
    var p_city;
    
    var addArray = p_FullAddress.split(";");
    //alert(addArray);
    //alert(addArray.length);
    if(addArray.length>2){
    	p_streetAddress =   addArray[0];
    	p_suburb =  addArray[1];
    	p_city =  addArray[2];
    	  
    }else if(addArray.length>1){
    	p_streetAddress =   addArray[0];
    	p_suburb =  addArray[1];
    }else p_streetAddress =   addArray;
    
    
    //var p_suburb =" ";// document.getElementById("subUrb").value;
    var p_postalCode = " ";//var PostalCode").value;
    //var p_city = " ";//document.getElementById("city").value;
    var p_bankCode = document.getElementById("txfdPayingBankCode").value;
    var p_branchCode = document.getElementById("txfdPayingBranchCode").value;
    var p_cehqueNo = document.getElementById("txfdChequeNo").value;
    var p_chequeDate = " ";//document.getElementById("txtChequeDate").value;
    var p_chequeDate = " ";//dojo.widget.byId("txtChequeDate").getValue();
    var p_amountInWords = document.getElementById("txfdAmountInWords").value;
    var p_amountInFigures = " ";//document.getElementById("amountInWords").value;
    var p_preparedBy = document.getElementById("txfdPreparedBy").value;
    var p_miscellaneousIncome = document.getElementById("txfdMiscellaneousIncome").value;
    var p_electricityDebtors = document.getElementById("txfdElectricityDebtors").value;
    var p_securityDeposit = document.getElementById("txfdSecurityDeposit").value;
    var p_serviceConnOrElecSchemes = document.getElementById("txfdSerConnOrElecSch").value;
    var p_tenderDeposit = document.getElementById("txfdTenderDeposit").value;
    var p_miscellaneousDeposit = document.getElementById("txfdMiscellaneousDeposit").value;
    var p_cashInTransit = document.getElementById("txfdCashInTransit").value;
    var p_forDishonouredCheques = document.getElementById("txfdForDishonouredCheque").value;
    var p_subTotal = document.getElementById("txfdSubTotal").value;
    var p_vat = document.getElementById("txfdVat").value;
    var p_grandTot = document.getElementById("txfdGrandTotal").value;
    //var p_loanAmnt = document.getElementById("txfdLoanReference").value;
    //var p_loanRef = document.getElementById("txfdLoanAmount").value;
    var p_loanAmnt = "";
    var p_loanRef = "";
    //if(dojo.widget.byId("txfdChequeDate") == undefined)
 	var p_chequeDate = "";
 	var p_applicationFee=document.getElementById("txfdApplicationFee").value;
 	  //else
 	   //var p_chequeDate = dojo.widget.byId("txfdChequeDate").getValue();
    	//var p_chequeDate = "";
    
    //document.pivApplet.print(appName,area,appSCS,street,suburb,city,"",applicationNo,tPhoneNo,serviceType,
    //		tariff,subStation,distancesFromSS,transformerCapacity,transformerLoad,peakLoad,feederCtrlType,sin,phase,noOfPoles,neighborAcct
    //		); 
    document.pivApplet.print(p_region,p_bank, p_branchName, p_cebBranch, p_costCentre, p_date, p_referenceNumber,  p_PIVNumber, p_jobDescription,  
    		p_depositorID, p_depositerName, p_streetAddress, p_suburb, p_postalCode, p_city, p_bankCode,
    		p_branchCode, p_cehqueNo, p_chequeDate, p_amountInWords, p_amountInFigures, p_preparedBy,
    		p_miscellaneousIncome, p_electricityDebtors, p_securityDeposit, p_serviceConnOrElecSchemes, 
    		p_tenderDeposit, p_miscellaneousDeposit, p_cashInTransit, p_forDishonouredCheques, p_subTotal, 
    		p_vat, p_grandTot, p_loanAmnt, p_loanRef,p_applicationFee); 
   
    //(p_bank, p_branchName, p_cebBranch, p_costCentre, p_date, p_referenceNumber,  p_PIVNumber, p_jobDescription,  
    //		p_depositorID, p_depositerName, p_streetAddress, p_suburb, p_postalCode, p_city, p_bankCode,
    //		p_branchCode, p_cehqueNo, p_chequeDate, p_amountInWords, p_amountInFigures, p_preparedBy,
    //		p_miscellaneousIncome, p_electricityDebtors, p_securityDeposit, p_serviceConnOrElecSchemes, 
    //		p_tenderDeposit, p_miscellaneousDeposit, p_cashInTransit, p_forDishonouredCheques, p_subTotal, 
    //		p_vat, p_grandTot, p_loanAmnt, p_loanRef)
    // return false;
   
}



