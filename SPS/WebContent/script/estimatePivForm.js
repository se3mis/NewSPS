var newStatus="N";
var modifiedStatus="M";
var confirmedStatus="C";

function test(){
	alert("hello");
}

function disableChequeNo(){
	//alert("jhfgf");
	//alert(document.getElementsByName("txfdReferenceNo").value);
	//alert("3");
	if (document.getElementById('paymentModeQ').checked){
		//document.getElementById("txfdChequeBankCode").readOnly = false;
		document.getElementById("txfdChequeBankCode").disabled = false;
		document.getElementById("txfdChequeBranchCode").readOnly = false;
		document.getElementById("txfdChequeNo").readOnly = false;
		dojo.widget.byId("txtChequeDate").disabled = false;
		//alert("jhfgfeee");
	}else {
		//document.getElementById("txfdChequeBankCode").readOnly = true;
		document.getElementById("txfdChequeBankCode").disabled = true;
		document.getElementById("txfdChequeBranchCode").readOnly = true;
		document.getElementById("txfdChequeNo").readOnly = true;
		dojo.widget.byId("txtChequeDate").disabled = true;
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
		}else if (document.getElementById('paymentModeQ').checked){
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
		}else if(validate_required(document.getElementById("txfdPayDate").value,"Pay Date must be filled out!")==false){
			//document.getElementById("txfdPayDate").select();
			return false;
		}else if (document.getElementById('paymentModeQ').checked){
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
		//alert(document.getElementById("state").value);
		//alert(document.getElementById("hiddenSecondState").value);
		if (document.getElementById("state").value == "fromApp") {
			//alert("fromApp");
			//For cheque
			document.getElementById('paymentModeC').checked=true;
			document.getElementById('paymentModeQ').disabled=true;
			disableChequeNo();
			//common
			commonDisableTags();
			document.getElementById("txfdAmountInWords").value = "Two Hundred and Five Rupees Only";
			document.getElementById("txfdMiscellaneousIncome").value = "250.00";
			document.getElementById("txfdSubTotal").value = "250.00";
			document.getElementById("txfdGrandTotal").value = "250.00";
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
			document.getElementById('paymentModeC').checked=true;
			document.getElementById('paymentModeQ').disabled=true;
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
		} else if (document.getElementById("state").value == "view") {
			//alert("view");
			//document.getElementById("txfdCostCenterNo").readOnly = true;
			//For cheque
			document.getElementById('paymentModeC').checked=true;
			document.getElementById('paymentModeQ').disabled=true;
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
		}else if (document.getElementById("state").value == "issuePiv") {
			//alert("issuePiv");
			//For cheque
			document.getElementById('paymentModeC').checked=true;
			document.getElementById('paymentModeQ').disabled=true;
			disableChequeNo();
			//common
			commonDisableTags();
			document.getElementById("txfdAmountInWords").value = "Two Hundred and Five Rupees Only";
			document.getElementById("txfdMiscellaneousIncome").value = "250.00";
			document.getElementById("txfdSubTotal").value = "250.00";
			document.getElementById("txfdGrandTotal").value = "250.00";
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
			
		} else {
			alert("Different Path");
			//For cheque
			document.getElementById('paymentModeC').checked=true;
			document.getElementById('paymentModeQ').disabled=true;
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
		alert("svknknv");
		
		
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
	document.getElementById("txfdCostCenterNo").readOnly = true;
	document.getElementById("txfdCebBranch").readOnly = true;
	//document.getElementById("txfdCebBranch").readOnly = true;
	document.getElementById("txfdDate").readOnly = true;
	document.getElementById("txfdReferenceNo").readOnly = true;
	document.getElementById("txfdIdNo").readOnly = true;
	document.getElementById("txfdFullName").readOnly = true;
	document.getElementById("txfdFullAddress").readOnly = true;
	document.getElementById("txfdApprovedBy").readOnly = true;
	document.getElementById('paymentModeC').checked =true;
	document.getElementById("txfdChequeNo").readOnly = true;
	document.getElementById("txfdChequeBankCode").readOnly = true;
	document.getElementById("txfdChequeBranchCode").readOnly = true;
	document.getElementById("txfdAmountInWords").readOnly = true;
	document.getElementById("txfdDescription").readOnly = true;
	document.getElementById("txfdPreparedBy").readOnly = true;
	//
	document.getElementById("txfdMiscellaneousIncome").readOnly = true;
	document.getElementById("txfdElectricityDebtors").readOnly = true;
	document.getElementById("txfdSecurityDeposit").readOnly = true;
	document.getElementById("txfdSerConnOrElecSch").readOnly = true;
	document.getElementById("txfdTenderDeposit").readOnly = true;
	document.getElementById("txfdMiscellaneousDeposit").readOnly = true;
	document.getElementById("txfdCashInTransit").readOnly = true;
	document.getElementById("txfdForDishonouredCheque").readOnly = true;
	document.getElementById("txfdSubTotal").readOnly = true;
	document.getElementById("txfdVat").readOnly = true;
	document.getElementById("txfdGrandTotal").readOnly = true;
	document.getElementById("txfdLoanReference").readOnly = true;
	document.getElementById("txfdLoanAmount").readOnly = true;
	
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
	if(document.getElementById("referenceType").value=="EST")
	{
		if((document.getElementById("pivReceiptNo").value==null)||(document.getElementById("pivReceiptNo").value=="") ){
			alert("Please key in PIV Receipt Number.");
			document.getElementById("pivReceiptNo").select();
			return false;
		}
		else if((dojo.widget.byId("txtPaidDate").getValue()==null)||(dojo.widget.byId("txtPaidDate").getValue()=="") ){
			alert("Please key in date paid.");
			retVal = false;
		}else if(document.getElementById('paymentModeQ').checked==false && document.getElementById('paymentModeC').checked==false){
			alert("Please mention the payment mode.");
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
	}
	else
	{
		if((document.getElementById("pivReceiptNo").value==null)||(document.getElementById("pivReceiptNo").value=="") ){
			alert("Please key in PIV Receipt Number.");
			document.getElementById("pivReceiptNo").select();
			return false;
		}
	}
	return retVal;
}

function formView()
{
	if((document.getElementById("isConfirm").value!=null)&&(document.getElementById("isConfirm").value=="true") )
	{
		document.getElementById("txtLoanReference").readOnly = true;
		document.getElementById("txtLoanAmount").readOnly = true;
		dojo.widget.byId("txtPaidDate").disabled = false;
	}
	else if((document.getElementById("isGenerate").value!=null)&&(document.getElementById("isGenerate").value=="true") )
	{
		document.frmPiv.paymentMode[0].disabled = true;
		document.frmPiv.paymentMode[1].disabled = true;
		dojo.widget.byId("txtPaidDate").disabled = true;
	}
	else if((document.getElementById("isPrint").value!=null)&&(document.getElementById("isPrint").value=="true") )
	{
		document.frmPiv.paymentMode[0].disabled = true;
		document.frmPiv.paymentMode[1].disabled = true;
		dojo.widget.byId("txtPaidDate").disabled = true;
		document.getElementById("pivReceiptNo").readOnly = true;
		document.getElementById("txtDescription").readOnly = true;
	}
	else
	{
		document.getElementById("txtPayingBranchCode").readOnly = true;
		document.getElementById("txtLoanReference").readOnly = true;
		document.getElementById("txtLoanAmount").disabled = true;
		document.frmPiv.paymentMode[0].disabled = true;
		document.frmPiv.paymentMode[1].disabled = true;
		document.getElementById("txtDescription").readOnly = true;
		dojo.widget.byId("txtPaidDate").disabled = true;
	}
	document.getElementById("txfdChequeBankCode").disabled = true;
	document.getElementById("txtPaidDate").childNodes[1].disabled = true;
	document.getElementById("txtChequeDate").childNodes[1].disabled = true;
	dojo.widget.byId("txtChequeDate").disabled = true;
	
	if((document.getElementById("isGenerateSuccess").value!=null)&&(document.getElementById("isGenerateSuccess").value=="true") )
	{
		document.getElementById("btnSaveOnly").disabled = true;
		
		
		//alert('print 1');
		printing();
		//document.getElementById("btnFind").disabled = true;
		//document.getElementById("btnSaveOnly").disabled = true;
		
	}

}

function estimatePIVFormValidate()
{
	if((document.getElementById("txtPayingBranchCode").value==null)||(document.getElementById("txtPayingBranchCode").value=="") ){
		alert("Please key in People's Bank Branch Code.");
		document.getElementById("txtPayingBranchCode").select();
		return false;
	}
	
	else return true;
}

function setAppletValues()
{
       document.getElementById("p_bank").value  = document.getElementById("payingBankName").value;
       document.getElementById("p_branchName").value  = document.getElementById("payingBranchName").value;
       document.getElementById("p_cebBranch").value  = document.getElementById("cebBranch").value;
       document.getElementById("p_costCentre").value  = document.getElementById("costCenterNo").value;
       document.getElementById("p_date").value  = document.getElementById("date").value;
       document.getElementById("p_referenceNumber").value  = document.getElementById("estimateNo").value;
       document.getElementById("p_PIVNumber").value  = document.getElementById("pivNo").value;
       document.getElementById("p_jobDescription").value  = document.getElementById("txtDescription").value;
       document.getElementById("p_depositorID").value  = document.getElementById("idNo").value;
       document.getElementById("p_depositerName").value  = document.getElementById("fullName").value;
       document.getElementById("p_streetAddress").value  = document.getElementById("street").value;
       document.getElementById("p_suburb").value  = document.getElementById("subUrb").value;
       document.getElementById("p_postalCode").value  = document.getElementById("postalCode").value;
       document.getElementById("p_city").value  = document.getElementById("city").value;
       document.getElementById("p_bankCode").value  = document.getElementById("txtpayingBankCode").value;
       document.getElementById("p_branchCode").value  = document.getElementById("txtPayingBranchCode").value;
       document.getElementById("p_cehqueNo").value  = document.getElementById("txfdChequeNo").value;
       //document.getElementById("p_chequeDate").value  = document.getElementById("txtChequeDate").value;
       //document.getElementById("p_chequeDate").value  = dojo.widget.byId("txtChequeDate").getValue();
       document.getElementById("p_amountInWords").value  = document.getElementById("amountInWords").value;
       //document.getElementById("p_amountInFigures").value  = document.getElementById("amountInWords").value;
       document.getElementById("p_preparedBy").value  = document.getElementById("preparedBy").value;
       document.getElementById("p_miscellaneousIncome").value  = document.getElementById("miscellaneousIncome").value;
       document.getElementById("p_electricityDebtors").value  = document.getElementById("electricityDebtors").value;
       document.getElementById("p_securityDeposit").value  = document.getElementById("securityDeposit").value;
       document.getElementById("p_serviceConnOrElecSchemes").value  = document.getElementById("serConnOrElecSch").value;
       document.getElementById("p_tenderDeposit").value  = document.getElementById("tenderDeposit").value;
       document.getElementById("p_miscellaneousDeposit").value  = document.getElementById("miscellaneousDeposit").value;
       document.getElementById("p_cashInTransit").value  = document.getElementById("cashInTransit").value;
       document.getElementById("p_forDishonouredCheques").value  = document.getElementById("forDishonouredCheque").value;
       document.getElementById("p_subTotal").value  = document.getElementById("subTotal").value;
       document.getElementById("p_vat").value  = document.getElementById("vat").value;
       document.getElementById("p_grandTot").value  = document.getElementById("grandTotal").value;
       document.getElementById("p_loanAmnt").value  = document.getElementById("txtLoanAmount").value;
       document.getElementById("p_loanRef").value  = document.getElementById("txtLoanReference").value;
       if(dojo.widget.byId("txtChequeDate") == undefined)
    	   document.getElementById("p_chequeDate").value  = "";
       else
    	   document.getElementById("p_chequeDate").value  = dojo.widget.byId("txtChequeDate").getValue();
       
      
       
}

function printing()  { 
	var p_bank =document.getElementById("payingBankName").value;
    var p_branchName =document.getElementById("payingBranchName").value;
	var p_cebBranch = document.getElementById("cebBranch").value;
    var p_costCentre = document.getElementById("costCenterNo").value;
    var p_date = document.getElementById("date").value;
    var p_referenceNumber = document.getElementById("estimateNo").value;
    var p_PIVNumber = document.getElementById("pivNo").value;
    var p_jobDescription = document.getElementById("txtDescription").value;
    var p_depositorID = document.getElementById("idNo").value;
    var p_depositerName = document.getElementById("fullName").value;
    var p_streetAddress = document.getElementById("street").value;
    var p_suburb = document.getElementById("subUrb").value;
    var p_postalCode = document.getElementById("postalCode").value;
    var p_city = document.getElementById("city").value;
    var p_bankCode = document.getElementById("txtpayingBankCode").value;
    var p_branchCode = document.getElementById("txtPayingBranchCode").value;
    var p_cehqueNo = document.getElementById("txfdChequeNo").value;
    var p_amountInWords = document.getElementById("amountInWords").value;
    var p_amountInFigures = " ";//document.getElementById("amountInWords").value;
    var p_preparedBy = document.getElementById("preparedBy").value;
    var p_miscellaneousIncome = document.getElementById("miscellaneousIncome").value;
    var p_electricityDebtors = document.getElementById("electricityDebtors").value;
    var p_securityDeposit = document.getElementById("securityDeposit").value;
    var p_serviceConnOrElecSchemes = document.getElementById("serConnOrElecSch").value;
    var p_tenderDeposit = document.getElementById("tenderDeposit").value;
    var p_miscellaneousDeposit = document.getElementById("miscellaneousDeposit").value;
    var p_cashInTransit = document.getElementById("cashInTransit").value;
    var p_forDishonouredCheques = document.getElementById("forDishonouredCheque").value;
    var p_subTotal = document.getElementById("subTotal").value;
    var p_vat = document.getElementById("vat").value;
    var p_grandTot = document.getElementById("grandTotal").value;
    var p_loanAmnt = document.getElementById("txtLoanReference").value;
    var p_loanRef = document.getElementById("txtLoanAmount").value;
    if(dojo.widget.byId("txtChequeDate") == undefined)
    	p_chequeDate  = "";
    else
    	p_chequeDate  = dojo.widget.byId("txtChequeDate").getValue();
    var region =document.getElementById("region").value;
    
    document.pivApplet.print(region, p_bank, p_branchName, p_cebBranch, p_costCentre, p_date, p_referenceNumber,  p_PIVNumber, p_jobDescription,  
    		p_depositorID, p_depositerName, p_streetAddress, p_suburb, p_postalCode, p_city, p_bankCode,
    		p_branchCode, p_cehqueNo, p_chequeDate, p_amountInWords, p_amountInFigures, p_preparedBy,
    		p_miscellaneousIncome, p_electricityDebtors, p_securityDeposit, p_serviceConnOrElecSchemes, 
    		p_tenderDeposit, p_miscellaneousDeposit, p_cashInTransit, p_forDishonouredCheques, p_subTotal, 
    		p_vat, p_grandTot, p_loanAmnt, p_loanRef,""); 
    
    
   
}



