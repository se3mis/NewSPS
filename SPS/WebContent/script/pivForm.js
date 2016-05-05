var newStatus="N";
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



