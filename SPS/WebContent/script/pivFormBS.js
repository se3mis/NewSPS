var newStatus="N";
var modifiedStatus="M";
var confirmedStatus="C";


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
	Number (document.getElementById("txfdApplicationFee").value)+
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




