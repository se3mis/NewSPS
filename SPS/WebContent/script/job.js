function checkJobNoNotNull(){
	if((document.getElementById("txtJobNo").value==null)||(document.getElementById("txtJobNo").value=="") ){
		alert("Please type a Job Number.");
		document.getElementById("txtJobNo").select();
		return false;
	}
	else return true;
}

function checkJvNoNotNull(){
	if((document.getElementById("jvNo").value==null)||(document.getElementById("jvNo").value=="") ){
		alert("Please type a JV Number.");
		document.getElementById("jvNo").select();
		return false;
	}
	else return true;
}

function formValidation(){
	if(document.getElementById("kva").value=="" && document.getElementById("kva").value.length==0){
	 	alert("Please fill the KVA details");
	 	return false;
	}else if (document.getElementById("sinNo").value=="" && document.getElementById("sinNo").value.length==0){
	 	alert("Please fill the SIN number");
	 	return false;
	}
	return true;
}

function formValidationNCjobFinish(){
	//alert(document.getElementById("neighboursAccNo").value);
	
	if (document.getElementById("sinNo").value=="" && document.getElementById("sinNo").value.length==0){
		 	alert("Please fill the SIN number");
		 	return false;
	}else if(document.getElementById("kva").value=="" && document.getElementById("kva").value.length==0){
	 	alert("Please fill the KVA details");
	 	return false;
	}else if((dojo.widget.byId("conDate").getValue()==null)||(dojo.widget.byId("conDate").getValue()=="") ){
		alert("Please fille the Connected date.");
		return false;	
	}else if(document.getElementById("noOfMeters").value!=1 && document.getElementById("noOfMeters").value!=3){
	 	alert("Please Select the no Of Meters");
	 	return false; 	
	}else if (document.getElementById("meterNo1").value=="" && document.getElementById("meterNo1").value.length==0){
	 	alert("Please fill the meter No 1");
	 	return false;
	}else if (document.getElementById("reading1").value=="" && document.getElementById("reading1").value.length==0){
	 	alert("Please fill the reading 1");
	 	return false;
	}else if (document.getElementById("meterType1").value=="" && document.getElementById("meterType1").value.length==0){
	 	alert("Please fill the meter Type 1");
	 	return false;
	}else if (document.getElementById("noDigit1").value=="" && document.getElementById("noDigit1").value.length==0){
	 	alert("Please fill the no Digit 1");
	 	return false;
	}else if (document.getElementById("SealNumber1").value=="" && document.getElementById("SealNumber1").value.length==0){
	 	alert("Please fill the Seal Number 1");
	 	return false;
	}else if (document.getElementById("SealNumber2").value=="" && document.getElementById("SealNumber2").value.length==0){
	 	alert("Please fill the Seal Number 2");
	 	return false;
	}else if (document.getElementById("SealNumber3").value=="" && document.getElementById("SealNumber3").value.length==0){
	 	alert("Please fill the Seal Number 3");
	 	return false;
	}else if (document.getElementById("neighboursAccNo").value=="" && document.getElementById("neighboursAccNo").value.length==0){
	 	alert("Please fill the neighboursAccNo");
	 	return false;
	}
	return true;
}


function formValidationCRjobFinish(){
	//alert(document.getElementById("neighboursAccNo").value);
	
	if (document.getElementById("sinNo").value=="" && document.getElementById("sinNo").value.length==0){
		 	alert("Please fill the SIN number");
		 	return false;
	}else if(document.getElementById("kva").value=="" && document.getElementById("kva").value.length==0){
	 	alert("Please fill the KVA details");
	 	return false;
	}else if((dojo.widget.byId("conDate").getValue()==null)||(dojo.widget.byId("conDate").getValue()=="") ){
		alert("Please fille the Connected date.");
		return false;	
	}else if(document.getElementById("noOfMeters").value!=1 && document.getElementById("noOfMeters").value!=3){
	 	alert("Please Select the no Of Meters");
	 	return false; 	
	}else if (document.getElementById("meterNo1").value=="" && document.getElementById("meterNo1").value.length==0){
	 	alert("Please fill the meter No 1");
	 	return false;
	}else if (document.getElementById("reading1").value=="" && document.getElementById("reading1").value.length==0){
	 	alert("Please fill the reading 1");
	 	return false;
	}else if (document.getElementById("meterType1").value=="" && document.getElementById("meterType1").value.length==0){
	 	alert("Please fill the meter Type 1");
	 	return false;
	}else if (document.getElementById("noDigit1").value=="" && document.getElementById("noDigit1").value.length==0){
	 	alert("Please fill the no Digit 1");
	 	return false;
	}else if (document.getElementById("SealNumber1").value=="" && document.getElementById("SealNumber1").value.length==0){
	 	alert("Please fill the Seal Number 1");
	 	return false;
	}else if (document.getElementById("SealNumber2").value=="" && document.getElementById("SealNumber2").value.length==0){
	 	alert("Please fill the Seal Number 2");
	 	return false;
	}else if (document.getElementById("SealNumber3").value=="" && document.getElementById("SealNumber3").value.length==0){
	 	alert("Please fill the Seal Number 3");
	 	return false;
	}else if (document.getElementById("oldAcct").value=="" && document.getElementById("oldAcct").value.length==0){
	 	alert("Please fill the oldAcct");
	 	return false;
	}
	return true;
}



function Print(){
	var p_region =document.getElementById("region").value;
	var  SMCType = document.getElementById("smcType").value;
    var  SIN = document.getElementById("sinNo").value;
    //alert(document.getElementById("hid_CostCenter").value);
    var  costCode = document.getElementById("hid_CostCenter").value;
    var  ECSC = document.getElementById("ecsc").value;
    var  year  = document.getElementById("year").value;
    var  serialJobNo = document.getElementById("serialJobNo").value;
    var  noOfMeters = document.getElementById("noOfMeters").value;      
    var  meterNumber1 = document.getElementById("meterNo1").value;
    var  meterNumber2 = document.getElementById("meterNo2").value;
    var  meterNumber3 = document.getElementById("meterNo3").value;
    var  meterReading1 = document.getElementById("reading1").value;
    var  meterReading2 = document.getElementById("reading2").value;
    var  meterReading3 = document.getElementById("reading3").value;
    var  meterType1 = document.getElementById("meterType1").value;
    var  meterType2 = document.getElementById("meterType2").value;
    var  meterType3 = document.getElementById("meterType3").value;
    var  noOfDigits1 = document.getElementById("noDigit1").value;
    var  noOfDigits2 = document.getElementById("noDigit2").value;
    var  noOfDigits3 = document.getElementById("noDigit3").value;
    var  connectedDate = getDateString("conDate");
    var  averageConsumption = document.getElementById("avgConsump").value;
    var  paymentMode = document.getElementById("payMode").value;
    var  paymentDate = document.getElementById("payDate").value;
    var  securityDeposits = document.getElementById("secDeposit").value;
    var  serviceMainCharge = document.getElementById("smCharge").value;
    var  tax = document.getElementById("taxAmount").value;
    var  total = document.getElementById("totalCost").value;
    var  tariff = document.getElementById("tariffCode").value;
    var  KVA = document.getElementById("kva").value;
    var  connType = document.getElementById("connectionType").value;
    var  noOfPhase = document.getElementById("phase").value;
    var  bankReference_PIV2 = document.getElementById("bankRef").value;
    var  neighboursACNo = document.getElementById("neighboursAccNo").value;
    var  bankBranchCode = document.getElementById("bankBranch").value;
    var  lastName = document.getElementById("lastName").value;
    var  firstName = document.getElementById("firstName").value;
 	var address = document.getElementById("address").value;
 	
	 	
    var streetAddress;
    var suburb;
    var city;
    var addArray = address.split(";");
    
    if(addArray.length>2){
    	streetAddress =   addArray[0];
    	suburb =  addArray[1];
    	city =  addArray[2];
    	  
    }
    
  
    var  postalCode = "";
    var  PIVNumber = document.getElementById("pivNo").value;
    var  PIVDate = document.getElementById("pivDate").value;
    var  customerType = document.getElementById("cusType").value;
    var  readerCode = document.getElementById("readerCode").value;
    var  packNo = document.getElementById("packNo").value;
    var  walkSequence = document.getElementById("walkSeq").value;
    var  oldAcctNumber= document.getElementById("oldAcct").value;
    var  loanCode = document.getElementById("hid_loanCode").value;
    var  loanDate = document.getElementById("hid_loanDate").value;
    var  noOfInstalment = document.getElementById("hid_noOfInstalment").value;
    var  loanAmount = document.getElementById("hid_loanAmount").value;
    var  instalmantAmount = document.getElementById("hid_instalmantAmount").value;
	
    document.serviceMainCardApplet.print(p_region,SMCType,SIN,costCode,ECSC,year,serialJobNo,noOfMeters, meterNumber1,meterNumber2,meterNumber3,meterReading1,
    meterReading2,meterReading3,meterType1,meterType2,meterType3,noOfDigits1,noOfDigits2, noOfDigits3,connectedDate,averageConsumption,paymentMode,
    paymentDate,securityDeposits,serviceMainCharge,tax,total,tariff,KVA,connType,noOfPhase,bankReference_PIV2,neighboursACNo,bankBranchCode,lastName,firstName,streetAddress,suburb,city,postalCode,PIVNumber,PIVDate,customerType,readerCode, packNo, walkSequence,
    oldAcctNumber,loanCode,loanDate,noOfInstalment,loanAmount,instalmantAmount);

}

function getDateString(dtstring) {
	
	  var picker = dojo.widget.byId(dtstring);
	  var stringValue = picker.getValue();
	  var splitIndex = stringValue.indexOf("T");
	  var dateLength = stringValue.length;
	  var formattedString;	 
	  
	  if(splitIndex<dateLength){
		  var dateStr = stringValue.substring(0, splitIndex);	
		  
		  return dateStr;
	  } 	  	
}

function reviseFormValidation()
{
	if(document.getElementById("cmbCategoryCode").value.length==0)
	{
		document.getElementById("cmbCategoryCode").focus();
		alert('Please select Category Code.');
		return false;
	}
	else if(document.getElementById("lineLength").value.length==0)
	{
		document.getElementById("lineLength").focus();
		alert('Please key in Line Length.');
		return false;
	}
	else if(document.getElementById("fixedCost").value.length==0)
	{
		alert('Please key in Fixed Cost.');
		document.getElementById("fixedCost").focus();
		return false;
	}
	else if(document.getElementById("variableCost").value.length==0)
	{
		alert('Please key in Variable Cost.');
		document.getElementById("variableCost").focus();
		return false;
	}

	else if(document.getElementById("revReason").value.length==0)
	{
		document.getElementById("revReason").focus();
		alert('Please key in Revise Reason');
		return false;
	}
	else if(document.getElementById("totalMatCost")==null)
	{
		alert('Please insert resource list.');
		return false;
	}
	else if(!document.frmEstimate.wiringType[0].checked && !document.frmEstimate.wiringType[1].checked)
	{
		alert('Please select Wiring Type.');
		return false;
	}
	else
	{
		if(!isQtyFilled())
			return false;
		else
			return true;
		
	}
}

function numericValidate(obj,event)
{
	var key = event.keyCode;
	
	if((key>=48 && key<=57)|| key==190)	{}
	else
	{
		var val = obj.value.toUpperCase();
		var keyVal = String.fromCharCode ( event.keyCode )
		var index = val.indexOf(keyVal);
		if(index!=-1)
		{
			obj.value = val.substring(0,index)+val.substring(index+1);
		}
	}

}

function calculateMatCost(matType,matCode,unitPrice)
{
	var qty = document.getElementById("txt"+matType+"Qty"+matCode).value;
	if(qty=='')
		qty = 0;
	var newMatCost = parseFloat(qty)*parseFloat(unitPrice);
	var preMatCost = document.getElementById("txt"+matType+"MatCost"+matCode).value; 
	if(preMatCost=='')
		preMatCost = 0;
	else
		preMatCost = parseFloat(preMatCost.replace(",",""));
	var preTotCost = document.getElementById("totalMatCost").value; 
	if(preTotCost=='')
		preTotCost = 0;
	else
		preTotCost = parseFloat(preTotCost.replace(",",""));
	var newTotCost = preTotCost-preMatCost+newMatCost;
	document.getElementById("txt"+matType+"MatCost"+matCode).value=formatCurrency(newMatCost);
	document.getElementById("totalMatCost").value=formatCurrency(newTotCost);
}

function calculateComCost(matType,matCode,unitPrice)
{
	var qty = document.getElementById("txt"+matType+"Qty"+matCode).value;
	if(qty=='')
		qty = 0;
	var newMatCost = parseFloat(qty)*parseFloat(unitPrice);
	var preMatCost = document.getElementById("txt"+"Sel"+"ComCost"+matCode).value; 
	if(preMatCost=='')
		preMatCost = 0;
	else
		preMatCost = parseFloat(preMatCost.replace(",",""));
	var preTotCost = document.getElementById("totalMatCost").value; 
	if(preTotCost=='')
		preTotCost = 0;
	else
		preTotCost = parseFloat(preTotCost.replace(",",""));
	var newTotCost = preTotCost-preMatCost+newMatCost;
	document.getElementById("txt"+"Sel"+"ComCost"+matCode).value=formatCurrency(newMatCost);
	document.getElementById("totalMatCost").value=formatCurrency(newTotCost);
}


function formatValue(obj)
{
	var objVal = obj.value;
	obj.value = formatCurrency(objVal);
}

function isQtyFilled()
{
	var el = document.frmEstimate.elements;
    var total = 0;
    var isFilled = true;
	for(var i = 0 ; i < el.length ; ++i) 
	{
		if(el[i].type == "text" &&  el[i].name == "txtSelQty") 
		{
			if(el[i].value.length==0) 
			{
				alert('You have not entered Estimated Quantity for some resources!');
				el[i].focus();
				isFilled = false;
				break;
			}
			
		}
	}
	return isFilled;
}

function deleteConfirm()
{
    var el = document.frmEstimate.elements;
    var total = 0;

	for(var i = 0 ; i < el.length ; ++i) 
	{
		if(el[i].type == "checkbox" &&  el[i].name == "chkSelMatCode") 
		{
			if(el[i].checked) 
			{
				total += 1;
				break;
			}
			
		}
	}
	if (total == 0) 
	{
		alert("Please select resource(s) to be removed!");
		return false;
	}
	else
	{
		return cfm = confirm("Do you really want to remove the selected resource(s)?");
	}		

}


function calculateTotalCost(obj,event)
{
	var objVal = obj.value;
	var isNumeric = IsNumeric(objVal);
	if(isNumeric)
	{
		calculatingTotalCost();
		
	}
		
}

function calculatingTotalCost()
{
	var fc = document.getElementById("fixedCost").value;
	var vc = document.getElementById("variableCost").value;
	var oc = document.getElementById("otherCost").value;
	var olc = document.getElementById("otherLabourCost").value;
	var cc = document.getElementById("convCost").value;
	var ta = document.getElementById("taxAmount").value;
	var sd = document.getElementById("secDeposit").value;

	var st = parseFloat(fc.replace(",",""))+parseFloat(vc.replace(",",""));
	var tc = parseFloat(fc.replace(",",""))+parseFloat(vc.replace(",",""))+parseFloat(oc.replace(",",""))+parseFloat(cc.replace(",",""))+parseFloat(ta.replace(",",""))+parseFloat(sd.replace(",",""))+parseFloat(olc.replace(",",""));

	document.getElementById("subTotal").value = formatCurrency(st);
	document.getElementById("totalCost").value = formatCurrency(tc);
}



function calculateVariableCost(obj,event)
{
	
	var wmp = document.getElementById("wireMeterPrice").value;
	var len = document.getElementById("lineLength").value;
	var isNumeric = IsNumeric(len);	
	///if(isNumeric)
	//{
		var fc = document.getElementById("fixedCost").value;
		var vc = parseFloat(wmp)*parseFloat(len);
		var oc = document.getElementById("otherCost").value;
		var cc = document.getElementById("convCost").value;
		var ta = document.getElementById("taxAmount").value;
		var sd = document.getElementById("secDeposit").value;
	
		var st = parseFloat(fc.replace(",",""))+parseFloat(vc);
		var tc = parseFloat(fc.replace(",",""))+parseFloat(vc)+parseFloat(oc.replace(",",""))+parseFloat(cc.replace(",",""))+parseFloat(ta.replace(",",""))+parseFloat(sd.replace(",",""));
	
		document.getElementById("subTotal").value = formatCurrency(st);
		document.getElementById("totalCost").value = formatCurrency(tc);
		document.getElementById("variableCost").value = formatCurrency(vc);
	/*}
	else
	{
		obj.value = '0';
	}*/
	
}


function approveConfirm()
{
	return cfm = confirm("Please 'Save' the info, before sending for approval.\nIf already saved, Click 'OK' to send for approval?");
}

function formElementsDisplay()
{
	document.getElementById('labourSec').style.display = 'none';
	if((document.getElementById("isUndoReject").value!=null)&&(document.getElementById("isUndoReject").value=="true") )
	{
		disableAll();
		
	}
	else if((document.getElementById("isApprove").value!=null)&&(document.getElementById("isApprove").value=="true") )
	{
		disableAll();
		document.getElementById("rejectReason").readOnly = false;
	}
	
	
	
}

function disableAll()
{
	document.getElementById("cmbCategoryCode").disabled = true;
	document.getElementById("transColor").disabled = true;
	document.getElementById("lineLength").readOnly = true;
	document.getElementById("loopLength").readOnly = true;
	document.getElementById("lb").readOnly = true;
	document.getElementById("spans").readOnly = true;
	document.getElementById("poleNo").readOnly = true;
	document.getElementById("stayNo").readOnly = true;
	document.getElementById("strutsNo").readOnly = true;
	document.getElementById("sinNo").readOnly = true;
	document.getElementById("distanceToServicePlace").readOnly = true;
	document.getElementById("fundSourceID").disabled = true;
	document.getElementById("wiringTypeUG").disabled = true;
	document.getElementById("wiringTypeOH").disabled = true;
	document.getElementById("fixedCost").readOnly = true;
	document.getElementById("variableCost").readOnly = true;
	document.getElementById("otherCost").readOnly = true;
	document.getElementById("convCost").readOnly = true;
	document.getElementById("taxAmount").readOnly = true;
	document.getElementById("secDeposit").readOnly = true;
	document.getElementById("jobDesc").readOnly = true;
	/*document.getElementById("rda-1").disabled = true;
	document.getElementById("mc-1").disabled = true;
	document.getElementById("psd-1").disabled = true;
	document.getElementById("police-1").disabled = true;*/
	document.getElementById("revReason").readOnly = true;
	document.getElementById("rejectReason").readOnly = true;

}
function meterDisplay(){
	var meterNo = document.getElementById("noOfMeters").value;
	
	if(meterNo=="1"){
		document.getElementById("meterNo1").disabled = false;
		document.getElementById("reading1").disabled = false;
		document.getElementById("meterType1").disabled = false;
		document.getElementById("noDigit1").disabled = false;
		document.getElementById("meterNo2").disabled = true;
		document.getElementById("reading2").disabled = true;
		document.getElementById("meterType2").disabled = true;
		document.getElementById("noDigit2").disabled = true;
		document.getElementById("meterNo3").disabled = true;
		document.getElementById("reading3").disabled = true;
		document.getElementById("meterType3").disabled = true;
		document.getElementById("noDigit3").disabled = true;
		//
		document.getElementById("SealNumber1").readOnly = false;
		document.getElementById("SealNumber2").readOnly = false;
		document.getElementById("SealNumber3").readOnly = false;
		document.getElementById("SealNumber4").readOnly = true;
		document.getElementById("SealNumber5").readOnly = true;
		document.getElementById("SealNumber6").readOnly = true;
		document.getElementById("SealNumber7").readOnly = true;
		document.getElementById("SealNumber8").readOnly = true;
		document.getElementById("SealNumber9").readOnly = true;
	}else if(meterNo=="3"){
		document.getElementById("meterNo1").disabled = false;
		document.getElementById("reading1").disabled = false;
		document.getElementById("meterType1").disabled = false;
		document.getElementById("noDigit1").disabled = false;
		document.getElementById("meterNo2").disabled = false;
		document.getElementById("reading2").disabled = false;
		document.getElementById("meterType2").disabled = false;
		document.getElementById("noDigit2").disabled = false;
		document.getElementById("meterNo3").disabled = false;
		document.getElementById("reading3").disabled = false;
		document.getElementById("meterType3").disabled = false;
		document.getElementById("noDigit3").disabled = false;
		//
		document.getElementById("SealNumber1").readOnly = false;
		document.getElementById("SealNumber2").readOnly = false;
		document.getElementById("SealNumber3").readOnly = false;
		document.getElementById("SealNumber4").readOnly = false;
		document.getElementById("SealNumber5").readOnly = false;
		document.getElementById("SealNumber6").readOnly = false;
		document.getElementById("SealNumber7").readOnly = false;
		document.getElementById("SealNumber8").readOnly = false;
		document.getElementById("SealNumber9").readOnly = false;
	}else {
		document.getElementById("meterNo1").disabled = true;
		document.getElementById("reading1").disabled = true;
		document.getElementById("meterType1").disabled = true;
		document.getElementById("noDigit1").disabled = true;
		document.getElementById("meterNo2").disabled = true;
		document.getElementById("reading2").disabled = true;
		document.getElementById("meterType2").disabled = true;
		document.getElementById("noDigit2").disabled = true;
		document.getElementById("meterNo3").disabled = true;
		document.getElementById("reading3").disabled = true;
		document.getElementById("meterType3").disabled = true;
		document.getElementById("noDigit3").disabled = true;
		//
		document.getElementById("SealNumber1").readOnly = false;
		document.getElementById("SealNumber2").readOnly = false;
		document.getElementById("SealNumber3").readOnly = false;
		document.getElementById("SealNumber4").readOnly = false;
		document.getElementById("SealNumber5").readOnly = false;
		document.getElementById("SealNumber6").readOnly = false;
		document.getElementById("SealNumber7").readOnly = false;
		document.getElementById("SealNumber8").readOnly = false;
		document.getElementById("SealNumber9").readOnly = false;
	}
}

/*
function calculateOtherCost(obj,event,matType,matCode,unitPrice)
{
	var qty = document.getElementById("txtCusQty"+matCode).value;
	var estQty = document.getElementById("txtSelQty"+matCode).value;
	if(estQty=='')
		estQty = 0;
	if(estQty>0)
	{
		if(qty=='')
			qty = 0;
		if(qty<=estQty)
		{
			var preQty = document.getElementById("savedCusQty"+matCode).value;
			if(preQty=='')
				preQty = 0;
			
			var newMatCost = parseFloat(qty)*parseFloat(unitPrice);
			var preMatCost = parseFloat(preQty)*parseFloat(unitPrice);
			
			var preTotCost = document.getElementById("otherCost").value; 
			if(preTotCost=='')
				preTotCost = 0;
			else
				preTotCost = parseFloat(preTotCost.replace(",",""));
			
			var newTotCost = preTotCost-preMatCost+newMatCost;
			document.getElementById("otherCost").value=formatCurrency(newTotCost);
			document.getElementById("savedCusQty"+matCode).value=qty;
		}
		else
		{
			alert('Customer quanity cannot be greater than estimated quantity.');
			document.getElementById("txtCusQty"+matCode).value = "";
		}
	}
	else
	{
		alert('Please key in the estimated quantity first.');
		document.getElementById("txtCusQty"+matCode).value = "";
	}
}
*/

function calculateOtherCost(obj,event,matType,matCode,unitPrice)
{
	var qty = document.getElementById("txtCusQty"+matCode).value;
	var estQty = document.getElementById("txtSelQty"+matCode).value;
	if(estQty=='')
		estQty = 0;
	if(parseFloat(estQty)>0)
	{
		if(qty=='')
			qty = 0;
		if(parseFloat(qty)<=parseFloat(estQty))
		{
			var preQty = document.getElementById("savedCusQty"+matCode).value;
			if(preQty=='')
				preQty = 0;
			
			var newMatCost = parseFloat(qty)*parseFloat(unitPrice);
			var preMatCost = parseFloat(preQty)*parseFloat(unitPrice);
			
			var preTotalCusCost = document.getElementById("totalCusCost").value; 
			if(preTotalCusCost=='')
				preTotalCusCost = 0;
			else
				preTotalCusCost = parseFloat(preTotalCusCost.replace(",",""));
			
			var newTotalCusCost = preTotalCusCost-preMatCost+newMatCost;
			document.getElementById("otherCost").value=formatCurrency(newTotalCusCost);
			document.getElementById("savedCusQty"+matCode).value=qty;
			
			/*
			var preTotCost = document.getElementById("totalCost").value;
			var newTotCost = parseFloat(preTotCost.replace(",",""))+newMatCost-preMatCost;
			document.getElementById("totalCost").value=formatCurrency(newTotCost);
			*/
			
			document.getElementById("txtSelCusCost"+matCode).value=formatCurrency(newMatCost);
			document.getElementById("totalCusCost").value=formatCurrency(newTotalCusCost);
			
			calculatingTotalCost();
		}
		else
		{
			alert('Customer quanity cannot be greater than estimated quantity.');
			document.getElementById("txtCusQty"+matCode).value = "";
		}
	}
	else
	{
		alert('Please key in the estimated quantity first.');
		document.getElementById("txtCusQty"+matCode).value = "";
	}
}

function rejectValidate()
{
	if(document.getElementById("rejectReason").value.length==0)
	{
		document.getElementById("rejectReason").focus();
		alert('Please key in reason to reject.');
		return false;
	}
}

function calculateLabourCost(labCode,unitPrice)
{
	var qty = document.getElementById("txtSelLabQty"+labCode).value;
	if(qty=='')
		qty = 0;
	var newLabCost = parseFloat(qty)*parseFloat(unitPrice);
	var preLabCost = document.getElementById("txtSelLabCost"+labCode).value; 
	if(preLabCost=='')
		preLabCost = 0;
	else
		preLabCost = parseFloat(preLabCost.replace(",",""));
	var preTotCost = document.getElementById("totalLabCost").value; 
	if(preTotCost=='')
		preTotCost = 0;
	else
		preTotCost = parseFloat(preTotCost.replace(",",""));
	var newTotCost = preTotCost-preLabCost+newLabCost;
	document.getElementById("txtSelLabCost"+labCode).value=formatCurrency(newLabCost);
	document.getElementById("totalLabCost").value=formatCurrency(newTotCost);
}

function labourDeleteConfirm()
{
    var el = document.frmEstimate.elements;
    var total = 0;

	for(var i = 0 ; i < el.length ; ++i) 
	{
		if(el[i].type == "checkbox" &&  el[i].name == "chkSelLabCode") 
		{
			if(el[i].checked) 
			{
				total += 1;
				break;
			}
			
		}
	}
	if (total == 0) 
	{
		alert("Please select labour item(s) to be removed!");
		return false;
	}
	else
	{
		return cfm = confirm("Do you really want to remove the selected labour item(s)?");
	}		

}

function calculateOtherLabourCost(obj,event,matType,matCode,unitPrice)
{
	var qty = document.getElementById("txtCusQty"+matCode).value;
	var estQty = document.getElementById("txtSelQty"+matCode).value;
	if(estQty=='')
		estQty = 0;
	if(parseFloat(estQty)>0)
	{
		if(qty=='')
			qty = 0;
		if(parseFloat(qty)<=parseFloat(estQty))
		{
			var preQty = document.getElementById("savedCusQty"+matCode).value;
			if(preQty=='')
				preQty = 0;
			
			var newMatCost = parseFloat(qty)*parseFloat(unitPrice);
			var preMatCost = parseFloat(preQty)*parseFloat(unitPrice);
			
			var preTotalCusCost = document.getElementById("totalCusCost").value; 
			if(preTotalCusCost=='')
				preTotalCusCost = 0;
			else
				preTotalCusCost = parseFloat(preTotalCusCost.replace(",",""));
			
			var newTotalCusCost = preTotalCusCost-preMatCost+newMatCost;
			document.getElementById("otherLabourCost").value=formatCurrency(obj.value);
			document.getElementById("savedCusQty"+matCode).value=qty;
			
			/*
			var preTotCost = document.getElementById("totalCost").value;
			var newTotCost = parseFloat(preTotCost.replace(",",""))+newMatCost-preMatCost;
			document.getElementById("totalCost").value=formatCurrency(newTotCost);
			*/
			
			document.getElementById("txtSelCusCost"+matCode).value=formatCurrency(newMatCost);
			document.getElementById("totalCusCost").value=formatCurrency(newTotalCusCost);
			
			calculatingTotalCost();
		}
		else
		{
			alert('Customer quanity cannot be greater than estimated quantity.');
			document.getElementById("txtCusQty"+matCode).value = "";
		}
	}
	else
	{
		alert('Please key in the estimated quantity first.');
		document.getElementById("txtCusQty"+matCode).value = "";
	}
}

function calOtherLabourCost(obj)
{
	var newOth = obj.value;
	obj.value = formatCurrency(newOth);
	document.getElementById("otherLabourCost").value = formatCurrency(newOth);
	var preTotCost = document.getElementById("totalCusCost").value;
	var newTotCost = parseFloat(preTotCost.replace(",",""))+parseFloat(newOth.replace(",",""));
	document.getElementById("totalCusCost").value = formatCurrency(newTotCost);
	calculatingTotalCost();
}

function copyOtherCost()
{
	var totOthCost = document.getElementById("totalCusCost").value;
	var othLabCost = document.getElementById("otherLabourCost").value;
	var otheMatCost = parseFloat(totOthCost.replace(",",""))-parseFloat(othLabCost.replace(",",""));
	document.getElementById("otherCost").value = formatCurrency(otheMatCost);
	
	calculatingTotalCost();
	/*
	var preTotCost = document.getElementById("totalCost").value;
	var newTotCost = parseFloat(preTotCost.replace(",",""))+parseFloat(othCost.replace(",",""));
	document.getElementById("totalCost").value = formatCurrency(newTotCost);
	*/
}

function printing()
{
	var othMatCost = document.getElementById("otherCost").value;
	var othLabCost = document.getElementById("otherLabourCost").value;
	if (document.getElementById("isLoopServiceY").checked==true)
	
	//var isLoop = document.getElementById("isLoopServiceY").value;
	var	wiringType="";
	if(document.getElementById("wiringTypeOH").checked==true)
		wiringType="Over Head";
	else wiringType="Underground";
	
	var isLoop = "";
	if(document.getElementById("isLoopServiceY").checked==true)
		isLoop = "LOOP";
	else
		isLoop = "";
	var totOthCost = parseFloat(othMatCost.replace(",",""))+parseFloat(othLabCost.replace(",",""));
	
	var es = document.getElementById("estimateStatus").value;
	var warning = "";
	if(es=="5" || es=="41")
		warning = "** This is not for APPROVAL **";
	
	
	
	document.PIVApplet.print(
			document.getElementById("region").value,
			"",//document.getElementById("txtApplicationNo").value,
			document.getElementById("applicantName").value,
			document.getElementById("streetAddress").value,
			document.getElementById("suburb").value,
			document.getElementById("city").value,
			document.getElementById("postalCode").value,
			"",//document.getElementById("estimatedDate").value,
			document.getElementById("lineLength").value,
			
			document.getElementById("fixedCost").value,
			document.getElementById("variableCost").value,
			document.getElementById("subTotal").value,
			formatCurrency(totOthCost),
			document.getElementById("secDeposit").value,
			document.getElementById("taxAmount").value,
			"",//labour cost
			"",//addl deposit
			document.getElementById("totalCost").value,
			
			document.getElementById("itemCodeList").value,
			document.getElementById("itemDescList").value,
			document.getElementById("uomList").value,
			document.getElementById("priceList").value,
			document.getElementById("qtyList").value,
			document.getElementById("costList").value,
			document.getElementById("totalMatCost").value,
			
			document.getElementById("phase").value,
			document.getElementById("connectionType").value,
			wiringType,//document.getElementById("wiringType").value,
			isLoop,
			document.getElementById("estimateStatusDesc").value,
			"",//transportCost
			"",//overHeadCost
			document.getElementById("convCost").value,
			document.getElementById("capitalCost").value,
			warning,//warnning
			"",//damageCost
			"",//contingency cost
			"",//document.getElementById("companyName").value,
			
			document.getElementById("totalDetailCost").value,
			document.getElementById("nonMatCodeList").value,
			document.getElementById("nonMatCostList").value
			
			
	); 
}
