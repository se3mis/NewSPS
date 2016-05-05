

function checkAppNoNotNull(){
	if((document.getElementById("txtApplicationNo").value==null)||(document.getElementById("txtApplicationNo").value=="") ){
		alert("Please key in Application No.!");
		document.getElementById("txtApplicationNo").select();
		return false;
	}
	else return true;
}

function checkCopy(){
	if((document.getElementById("txtCopyEstimateNo").value==null)||(document.getElementById("txtCopyEstimateNo").value=="") ){
		alert("Please key in Estimation No.to be copied.");
		document.getElementById("txtCopyEstimateNo").select();
		return false;
	}
	if((document.getElementById("applicationNo").value==null)||(document.getElementById("applicationNo").value=="") ){
		alert("Please select application no.");
		document.getElementById("applicationNo").select();
		return false;
	}
	else return true;
}



function calculateTotalCost(obj,event)
{
	//alert("1");
	var objVal = obj.value;
	var isNumeric = IsNumeric(objVal);
	if(isNumeric)
	{
		calculatingTotalCost();
		
	}
		
}

function calculatingTotalCost(){
	//alert("2");
	var fc = document.getElementById("fixedCost").value;
	var vc = document.getElementById("variableCost").value;
	var oc = document.getElementById("otherCost").value;
	var olc = document.getElementById("otherLabourCost").value;
	var cc = document.getElementById("capitalCost").value;
	var ta = document.getElementById("taxAmount").value;
	var sd = document.getElementById("secDeposit").value;
	var coc = document.getElementById("convCost").value;

	var st = parseFloat(fc.replace(",",""))+parseFloat(vc.replace(",",""));
	var tc = parseFloat(fc.replace(",",""))+parseFloat(vc.replace(",",""))+parseFloat(oc.replace(",",""))+parseFloat(cc.replace(",",""))+parseFloat(ta.replace(",",""))+parseFloat(sd.replace(",",""))+parseFloat(olc.replace(",",""))+parseFloat(coc.replace(",",""));

	var costCenter = document.getElementById("costCenterNo").value;
	var appSubType = document.getElementById("appSubTypeDB").value;
	if(costCenter=='541.00' || costCenter=='542.00' ||  costCenter=='547.00' || costCenter=='548.00' || appSubType=='C1' || appSubType=='C2' || appSubType=='C3')
	{
			var addlDep = document.getElementById("addlDeposit").value;
			//alert(addlDep);
			if(addlDep=='')
				tc = tc;
			else
				tc = tc+parseFloat(addlDep.replace(",",""));
		
	}
	document.getElementById("subTotal").value = formatCurrency(st);
	document.getElementById("totalCost").value = formatCurrency(tc);
}


function calculateTotalCostTC1(obj,event)
{
	var objVal = obj.value;
	var isNumeric = IsNumeric(objVal);
	if(isNumeric)
	{
		var fc = document.getElementById("fixedCost").value;
		var vc = document.getElementById("variableCost").value;
		var oc = document.getElementById("otherCost").value;
		var cc = document.getElementById("capitalCost").value;
		var ta = document.getElementById("taxAmount").value;
		var sd = document.getElementById("secDeposit").value;
		var lc = document.getElementById("labourCost").value;
		var tc = document.getElementById("transportCost").value;
		var oh = document.getElementById("overheadCost").value;
		var ad = document.getElementById("addlDeposit").value;
	
		var st = parseFloat(fc.replace(",",""))+parseFloat(vc.replace(",",""));
		var tc = parseFloat(fc.replace(",",""))+parseFloat(vc.replace(",",""))+parseFloat(oc.replace(",",""))
				+parseFloat(cc.replace(",",""))+parseFloat(ta.replace(",",""))+parseFloat(sd.replace(",",""))
				+parseFloat(lc.replace(",",""))+parseFloat(tc.replace(",",""))+parseFloat(oh.replace(",",""))
				+parseFloat(ad.replace(",",""));
	
		document.getElementById("subTotal").value = formatCurrency(st);
		document.getElementById("totalCost").value = formatCurrency(tc);
		
	}
		
}

function calculateVariableCost(obj,event)
{
	var lineLenLimit = document.getElementById("lowMaxLineLen").value;
	var wmp = document.getElementById("wireMeterPrice").value;
	var len = document.getElementById("lineLength").value;
	
	var isNumeric = IsNumeric(len);	
	
	var fc = document.getElementById("fixedCost").value;
	var vc1 = parseFloat(wmp)*parseFloat(len);
	alert('vc1 '+vc1);
	alert('vc1 '+vc1);
	var addlVc = 0;
	if(parseFloat(len)>parseFloat(lineLenLimit))
	{
		var conductorPrice = 0;
		if(document.getElementById("conductorTypeABC").checked)
			conductorPrice = parseFloat(document.getElementById("ABC").value);
		else if (document.getElementById("conductorTypeFLY").checked)
			conductorPrice = parseFloat(document.getElementById("FLY").value);
		var diff = parseFloat(len)-parseFloat(lineLenLimit);
		addlVc = diff*conductorPrice;
		vc = vc + newvc;
		
	}
	var vc = vc1 + addlVc;
	alert('vc '+vc);
	if(document.getElementById("appSubTypeDB")!=null)
	{
		var fYr = document.getElementById("firstYear").value;
		var nYr = document.getElementById("nextYear").value;
		var dur = document.getElementById("durationDB").value;
		var appSub = document.getElementById("appSubTypeDB").value;
		if(appSub!=null && appSub=='C3')
		{
			if(parseFloat(dur)==1)
			{
				vc = vc*parseFloat(fYr);
			}
			else
			{
				vc = vc*(parseFloat(fYr)+(parseFloat(nYr)*(dur-1)));
				
			}
		
		}
	}
		
	var oc = document.getElementById("otherCost").value;
	var cc = document.getElementById("capitalCost").value;
	var ta = document.getElementById("taxAmount").value;
	var sd = document.getElementById("secDeposit").value;

	var st = parseFloat(fc.replace(",",""))+parseFloat(vc);
	var tc = parseFloat(fc.replace(",",""))+parseFloat(vc)+parseFloat(oc.replace(",",""))+parseFloat(cc.replace(",",""))+parseFloat(ta.replace(",",""))+parseFloat(sd.replace(",",""));

	document.getElementById("subTotal").value = formatCurrency(st);
	document.getElementById("totalCost").value = formatCurrency(tc);
	document.getElementById("variableCost").value = formatCurrency(vc);

}

function calculateVariableCostTC1(obj,event)
{
	
	var wmp = document.getElementById("wireMeterPrice").value;
	var varPer = document.getElementById("variablePercent").value;
	
	var len = document.getElementById("lineLength").value;
	var isNumeric = IsNumeric(len);	
	///if(isNumeric)
	//{
		var fc = document.getElementById("fixedCost").value;
		var vc = parseFloat(wmp)*parseFloat(len)*parseFloat(varPer);
		var oc = document.getElementById("otherCost").value;
		var cc = document.getElementById("capitalCost").value;
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
function keyFreeze(obj,event)
{
	var key = event.keyCode;
	var val = obj.value.toUpperCase();
	var keyVal = String.fromCharCode ( event.keyCode )
	var index = val.indexOf(keyVal);
	if(index!=-1)
	{
		obj.value = val.substring(0,index)+val.substring(index+1);
	}
	

}

function popupSizeWindow(url,width,height,name)
{
	var ht = screen.height;
	var wdth = screen.width;
	var newWidth = width;
	var newHeight = height;
	var lft = (wdth / 2) - (newWidth / 2);
	var tp = (ht / 2) - (newHeight / 2);
	var w = window.open (url, name, "location=no,screen x=200,screen y=200,toolbar=no,directories=no,status=no, menubar=no,scrollbars=yes,resizable=yes,copyhistory=no,left="+ lft + ",top="+ tp +",width="+ newWidth  +",height="+ newHeight);
}


function calculateMatCost(matType,matCode,unitPrice)
{
	var qty = document.getElementById("txt"+matType+"Qty"+matCode).value;
	if(qty=='')
		qty = 0;
	else if(qty=='.')
		qty = 0;
	var newMatCost = parseFloat(qty)*parseFloat(unitPrice);
	var preMatCost = document.getElementById("txt"+matType+"MatCost"+matCode).value; 
	if(preMatCost=='')
		preMatCost = 0;
	else
		preMatCost = parseFloat(preMatCost.replace(",",""));
	var preTotCost = document.getElementById("totalDetailCost").value; 
	if(preTotCost=='')
		preTotCost = 0;
	else
		preTotCost = parseFloat(preTotCost.replace(",",""));
	
	//var newTotCost = preTotCost;
	//if(newMatCost>0)
	var	newTotCost = preTotCost-preMatCost+newMatCost;
	document.getElementById("txt"+matType+"MatCost"+matCode).value=formatCurrency(newMatCost);
	document.getElementById("totalDetailCost").value=formatCurrency(newTotCost);
}

function calculateOtherCost(obj,event,matType,matCode,unitPrice)
{
	var qty = document.getElementById("txtCusQty"+matCode).value;
	var estQty = document.getElementById("txtSelQty"+matCode).value;
	if(estQty=='')
		estQty = 0;
	var otLabCost = document.getElementById("otherLabourCost").value;
	if(otLabCost=='')
		otLabCost = 0;
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
			document.getElementById("otherCost").value=formatCurrency(newTotalCusCost-parseFloat(otLabCost.replace(",","")));
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



function formValidation()
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
	else if(document.getElementById("distanceToServicePlace").value.length==0)
	{
		document.getElementById("distanceToServicePlace").focus();
		alert('Please key in Distance To Service Place.');
		return false;
	}
	else if(document.getElementById("fundSourceID").value.length==0)
	{
		document.getElementById("fundSourceID").focus();
		alert('Please select Fund Source.');
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
	/*else if(document.getElementById("jobDesc").value.length==0)
	{
		document.getElementById("jobDesc").focus();
		alert('Please key in Job Description');
		return false;
	}*/
	else if(document.getElementById("totalDetailCost")==null)
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
		{
			copyOtherCost();
			return true;
		}
		
	}
}


function rejectConfirm()
{
	if(document.getElementById("rejectReason").value.length==0)
	{
		document.getElementById("rejectReason").focus();
		alert('Please key in reason to reject.');
		return false;
	}
	return true;
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
		document.getElementById("rejectReason").disabled = false;
		
	}
	if((document.getElementById("applicationNo").value==null)||(document.getElementById("applicationNo").value.length==0) ){
		document.getElementById("txtCopyEstimateNo").disabled = true;
		document.getElementById("btnFill").disabled = true;
		//document.getElementById("txtApplicationNo").focus();
		
	}
	else
		
	{
		document.getElementById("txtCopyEstimateNo").disabled = false;
		document.getElementById("btnFill").disabled = false;
	}
	
}

function disableAll()
{
	document.getElementById("cmbCategoryCode").disabled = true;
	document.getElementById("transColor").disabled = true;
	document.getElementById("transColor").disabled = true;
	document.getElementById("lineLength").readOnly = true;
	document.getElementById("loopLength").readOnly = true;
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
	document.getElementById("capitalCost").readOnly = true;
	document.getElementById("taxAmount").readOnly = true;
	document.getElementById("secDeposit").readOnly = true;
	document.getElementById("jobDesc").readOnly = true;
	document.getElementById("rejectReason").readOnly = true;
	
	
}


function enablePoles()
{
	if(document.getElementById("isWithPoleY").checked)
	{
		document.getElementById("poleCode").disabled = false;
		
	}
	else if(document.getElementById("isWithPoleN").checked)
	{
		document.getElementById("poleCode").disabled = true;
	}
}

function enableConductors()
{
	if(document.getElementById("poleType").value=="TAPPING")
	{
		document.getElementById("toConductor").disabled = false;
	}
	else
	{
		document.getElementById("toConductor").disabled = true;
	}
}

function poleMatParamValidate()
{
	 if(document.getElementById("poleType").value.length==0)
	 {
		 alert('Please select a pole type.');
		 document.getElementById("poleType").focus();
		 return false;
	 }
	 else if(document.getElementById("fromConductor").value.length==0)
	 {
		 alert('Please select conductor type of the pole.');
		 document.getElementById("fromConductor").focus();
		 return false;
	 }
	 else if( document.getElementById("poleType").value=="TAPPING" && document.getElementById("toConductor").value.length==0)
	 {
		 alert('Please select connecting conductor type.');
		 document.getElementById("toConductor").focus();
		 return false;
	 }
	 else if(!document.getElementById("isWithPoleY").checked && !document.getElementById("isWithPoleN").checked)
	 {
		 alert('Please state whether service is given with a pole or not.');
		 return false;
	 }
	 else if(document.getElementById("isWithPoleY").checked && document.getElementById("poleCode").value.length==0)
	 {
		 alert('Please select the pole to be used.');
		 document.getElementById("poleCode").focus();
		 return false;
	 }
	 else if(document.getElementById("noOfPole").value.length==0)
	 {
		 alert('Please key in no of poles.');
		 document.getElementById("noOfPole").focus();
		 return false;
	 }
	 else
	 {
		 return true;
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


function calculateLabourCostNew(labCode)
{
	var qty = document.getElementById("txtSelLabQty"+labCode).value;
	if(qty=='')
		qty = 0;
	var hrs = document.getElementById("txtSelUnitLabHr"+labCode).value;
	if(hrs=='')
		hrs = 0;
	var unitPrice = document.getElementById("labourRate").value;
	
	//calc labour hours
	var newLabHrs = parseFloat(qty)*parseFloat(hrs);
	var preLabHrs = document.getElementById("txtSelLabHrs"+labCode).value; 
	if(preLabHrs=='')
		preLabHrs = 0;
	else
		preLabHrs = parseFloat(preLabHrs.replace(",",""));
	var preTotHrs = document.getElementById("totalLabHrs").value; 
	if(preTotHrs=='')
		preTotHrs = 0;
	else
		preTotHrs = parseFloat(preTotHrs.replace(",",""));
	var newTotHrs = preTotHrs-preLabHrs+newLabHrs;
	
	document.getElementById("txtSelLabHrs"+labCode).value=formatCurrency(newLabHrs);
	document.getElementById("totalLabHrs").value=formatCurrency(newTotHrs);
	
	
	//calc labour cost
	var newLabCost = parseFloat(qty)*parseFloat(unitPrice)*parseFloat(hrs);
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
/*
function setAppletValues()
{
	
	document.getElementById("p_itemCodes").value  = document.getElementById("payingBankName").value;                  
	document.getElementById("p_uoms").value  = document.getElementById("payingBankName").value;
	document.getElementById("p_price").value  = document.getElementById("payingBankName").value;
	document.getElementById("p_qtys").value  = document.getElementById("payingBankName").value;
	document.getElementById("p_csts").value  = document.getElementById("payingBankName").value;
	
    //document.getElementById("p_vat").value  = document.getElementById("taxAmount").value;
    //document.getElementById("p_total").value  = document.getElementById("totalCost").value;
    document.getElementById("p_estimateDate").value  = document.getElementById("estimatedDate").value;
	document.getElementById("p_labourCost").value  = "";
	document.getElementById("p_additionalDeposite").value  = "";
	
	
	
}
*/
function printing()
{
	//DW
	var appSubType = document.getElementById("appSubTypeDB").value;
	var costCenter=document.getElementById("costCenterNo").value;
	var riskDeposit=null;
	var secDeposit=document.getElementById("secDeposit").value;
	
	if(costCenter=='541.00' || costCenter=='542.00' ||  costCenter=='547.00' || costCenter=='548.00' || appSubType=='C1' || appSubType=='C2' || appSubType=='C3')
	{
			var addlDep = document.getElementById("addlDeposit").value;
			//alert(addlDep);
			if(addlDep=='')
				secDeposit = secDeposit;
			else
				secDeposit = parseFloat(secDeposit.replace(",",""))+parseFloat(addlDep.replace(",",""));
		
	}
	//alert(secDeposit);
	//
	var othMatCost = document.getElementById("otherCost").value;
	var othLabCost = document.getElementById("otherLabourCost").value;
	var isLoop = document.getElementById("isLoopService").value;
	if(isLoop=="Y")
		isLoop = "LOOP";
	else
		isLoop = "";
	var totOthCost = parseFloat(othMatCost.replace(",",""))+parseFloat(othLabCost.replace(",",""));
	
	var es = document.getElementById("estimateStatus").value;
	var warning = "";
	if(es=="75" || es=="31")
		warning = "** This is not for APPROVAL **";
	
	
	
	
	document.PIVApplet.print(
			document.getElementById("region").value,
			document.getElementById("txtApplicationNo").value,
			document.getElementById("applicantName").value,
			document.getElementById("streetAddress").value,
			document.getElementById("suburb").value,
			document.getElementById("city").value,
			document.getElementById("postalCode").value,
			document.getElementById("estimatedDate").value,
			document.getElementById("lineLength").value,
			
			document.getElementById("fixedCost").value,
			document.getElementById("variableCost").value,
			document.getElementById("subTotal").value,
			formatCurrency(totOthCost),
			formatCurrency(secDeposit),//document.getElementById("secDeposit").value,
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
			document.getElementById("wiringType").value,
			isLoop,
			document.getElementById("estimateStatusDesc").value,
			"",//transportCost
			"",//overHeadCost
			document.getElementById("convCost").value,
			document.getElementById("capitalCost").value,
			warning,//warnning
			"",//damageCost
			"",//contingency cost
			document.getElementById("companyName").value,
			
			document.getElementById("totalDetailCost").value,
			document.getElementById("nonMatCodeList").value,
			document.getElementById("nonMatCostList").value
			
			
	); 
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

function calculateOtherLabourCost(obj,event,matType,matCode,unitPrice)
{
	var qty = document.getElementById("txtCusQty"+matCode).value;
	var estQty = document.getElementById("txtSelQty"+matCode).value;
	var labRate = document.getElementById("labourRate").value;
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
			var otLabCost = parseFloat(obj.value)*parseFloat(labRate)
			document.getElementById("otherLabourCost").value=formatCurrency(otLabCost);
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

function displayCableType()
{
	if(document.getElementById("isLoopServiceY").checked)
	//if(document.getElementById("isLoopService").value!=null && document.getElementById("isLoopService").value=="Y")
	{
		document.getElementById("cmbCableType").disabled = true;
	}
	else if(document.getElementById("wiringTypeUG").checked)
	{
		document.getElementById("cmbCableType").disabled = false;
	}
	else
	{
		document.getElementById("cmbCableType").disabled = true;
	}
	
	

}
