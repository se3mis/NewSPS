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
	var objVal = obj.value;
	var isNumeric = IsNumeric(objVal);
	if(isNumeric)
	{
		calculateTotal();
		
	}
		
}

function calculateTotal()
{
	var fc = document.getElementById("fixedCost").value;
	var vc = document.getElementById("variableCost").value;
	var oc = document.getElementById("otherCost").value;
	var ta = document.getElementById("taxAmount").value;
	var sd = document.getElementById("secDeposit").value;
	var lc = document.getElementById("labourCost").value;
	var tc = document.getElementById("transportCost").value;
	var oh = document.getElementById("overheadCost").value;
	var ad = document.getElementById("addlDeposit").value;
	var dc = 0;
	if(document.getElementById("damageCost").value!=null)
		dc = document.getElementById("damageCost").value;

	var st = parseFloat(fc.replace(",",""))+parseFloat(vc.replace(",",""));
	var tc = parseFloat(fc.replace(",",""))+parseFloat(vc.replace(",",""))+parseFloat(oc.replace(",",""))
			+parseFloat(ta.replace(",",""))+parseFloat(sd.replace(",",""))
			+parseFloat(lc.replace(",",""))+parseFloat(tc.replace(",",""))+parseFloat(oh.replace(",",""))
			+parseFloat(ad.replace(",",""))+parseFloat(dc.replace(",",""));

	document.getElementById("subTotal").value = formatCurrency(st);
	document.getElementById("totalCost").value = formatCurrency(tc);
		
	
		
}


function calculateVariableCost(obj,event)
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
		var ta = document.getElementById("taxAmount").value;
		var sd = document.getElementById("secDeposit").value;
	
		var st = parseFloat(fc.replace(",",""))+parseFloat(vc);
		var tc = parseFloat(fc.replace(",",""))+parseFloat(vc)+parseFloat(oc.replace(",",""))+parseFloat(ta.replace(",",""))+parseFloat(sd.replace(",",""));
	
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
	var qty1 = document.getElementById("txtDamageQty"+matCode).value;
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
	var newTotCost = preTotCost-preMatCost+newMatCost;
	
	//var matPercent = document.getElementById("matPercent").value;
	//var fixedCost = parseFloat(matPercent)*parseFloat(newTotCost);
	//document.getElementById("fixedCost").value=formatCurrency(fixedCost);
	document.getElementById("txt"+matType+"MatCost"+matCode).value=formatCurrency(newMatCost);
	document.getElementById("totalDetailCost").value=formatCurrency(newTotCost);
	
	if(matCode=="TRANSPORT")
	{
		document.getElementById("transportCost").value=formatCurrency(newMatCost);
	}
	calculatingTotalCost();
}

function calculateMatCostCarnival(matType,matCode,unitPrice)
{
	
	var qty = document.getElementById("txt"+matType+"Qty"+matCode).value;
	var qty1 = document.getElementById("txtDamageQty"+matCode).value;
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
	var newTotCost = preTotCost-preMatCost+newMatCost;
	
	if(matCode=="TRANSPORT")
	{
		document.getElementById("transportCost").value=formatCurrency(newMatCost);
	}
	
	var transCost = document.getElementById("transportCost").value;
	var labourCost = document.getElementById("labourCost").value;
	var ohCost = document.getElementById("overheadCost").value;
	
		
	var matPercent = document.getElementById("matPercent").value;
	var tMat = newTotCost-parseFloat(transCost.replace(",",""))-parseFloat(labourCost.replace(",",""))-parseFloat(ohCost.replace(",",""));
	var fixedCost = parseFloat(matPercent)*parseFloat(tMat);
	document.getElementById("otherCost").value=formatCurrency(fixedCost);
	document.getElementById("txt"+matType+"MatCost"+matCode).value=formatCurrency(newMatCost);
	document.getElementById("totalDetailCost").value=formatCurrency(newTotCost);
	
	calculatingTotalCost();
}

function calculateDamageCost(matCode,unitPrice)
{
	var qty = document.getElementById("txtDamageQty"+matCode).value;
	var estQty = document.getElementById("txtSelQty"+matCode).value;
	if(estQty=='')
		estQty = 0;
	if(qty=='')
		qty = 0;
	if(parseFloat(qty)<=parseFloat(estQty))
	{
		var newMatCost = parseFloat(qty)*parseFloat(unitPrice);
		var preMatCost = document.getElementById("txtDamageCost"+matCode).value; 
		if(preMatCost=='')
			preMatCost = 0;
		else
			preMatCost = parseFloat(preMatCost.replace(",",""));
		var preTotCost = document.getElementById("totalDamageCost").value; 
		if(preTotCost=='')
			preTotCost = 0;
		else
			preTotCost = parseFloat(preTotCost.replace(",",""));
		var newTotCost = preTotCost-preMatCost+newMatCost;
		document.getElementById("txtDamageCost"+matCode).value=formatCurrency(newMatCost);
		document.getElementById("totalDamageCost").value=formatCurrency(newTotCost);
		document.getElementById("damageCost").value=formatCurrency(newTotCost);
		calculateTotal();
	}
	else
	{
		alert('Damage quanity cannot be greater than estimated quantity.');
		document.getElementById("txtDamageQty"+matCode).value = "";
	}
}


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
		if(parseFloat(qty)<=parseFloat(estQty))
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
	else
	{
		if(!isQtyFilled())
			return false;
		else
			return true;
		
	}
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

function deleteCarnival()
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
				var chkVal = el[i].value;
				var strArr = new Array();
				strArr = chkVal.split("###");
				var matCode = strArr[1];
				
				var matcost = document.getElementById("txtSelMatCost"+matCode).value;
				var matPercent = document.getElementById("matPercent").value;
				var removeCost = 0;
				if(matcost.length>0)
					removeCost = parseFloat(matPercent)*parseFloat(matcost.replace(",",""));
								
				var dmgcost = document.getElementById("txtDamageCost"+matCode).value;
				if(dmgcost=='')
					dmgcost = 0;
				else
					dmgcost = parseFloat(dmgcost.replace(",",""))
				var oldFixedCost = document.getElementById("otherCost").value;
				var newFixedCost = parseFloat(oldFixedCost.replace(",",""))-parseFloat(removeCost);
				document.getElementById("otherCost").value=formatCurrency(newFixedCost);
				//document.getElementById("subTotal").value=formatCurrency(newFixedCost);
				
				var oldDmgCost = document.getElementById("damageCost").value;
				var newDmgCost = parseFloat(oldDmgCost.replace(",",""))- dmgcost;
				document.getElementById("damageCost").value=formatCurrency(newDmgCost);
				
			}
			
		}
		calculatingTotalCost();
	}
	/*if (total == 0) 
	{
		alert("Please select resource(s) to be removed!");
		return false;
	}
	else
	{
		return cfm = confirm("Do you really want to remove the selected resource(s)?");
	}	*/	

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
	else if((document.getElementById("isPrint").value!=null)&&(document.getElementById("isPrint").value=="true") )
	{
		disableAll();
	}
	if((document.getElementById("applicationNo").value==null)||(document.getElementById("applicationNo").value.length==0) ){
		document.getElementById("txtCopyEstimateNo").disabled = true;
		document.getElementById("btnFill").disabled = true;
		document.getElementById("txtApplicationNo").focus();
		
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
	//document.getElementById("loopLength").readOnly = true;
	document.getElementById("spans").readOnly = true;
	document.getElementById("poleNo").readOnly = true;
	document.getElementById("stayNo").readOnly = true;
	document.getElementById("strutsNo").readOnly = true;
	document.getElementById("sinNo").readOnly = true;
	document.getElementById("distanceToServicePlace").readOnly = true;
	document.getElementById("fundSourceID").disabled = true;
	//document.getElementById("wiringTypeUG").disabled = true;
	//document.getElementById("wiringTypeOH").disabled = true;
	//document.getElementById("fixedCost").readOnly = true;
	document.getElementById("labourCost").readOnly = true;
	document.getElementById("transportCost").readOnly = true;
	document.getElementById("overheadCost").readOnly = true;
	document.getElementById("addlDeposit").readOnly = true;
	document.getElementById("variableCost").readOnly = true;
	document.getElementById("otherCost").readOnly = true;
	document.getElementById("taxAmount").readOnly = true;
	document.getElementById("secDeposit").readOnly = true;
	document.getElementById("jobDesc").readOnly = true;
	document.getElementById("rejectReason").readOnly = true;
	/*
	document.getElementById("rda-1").disabled = true;
	document.getElementById("mc-1").disabled = true;
	document.getElementById("psd-1").disabled = true;
	document.getElementById("police-1").disabled = true;*/
}

function rejectValidate()
{
	if(document.getElementById("rejectReason").value.length==0)
	{
		document.getElementById("rejectReason").focus();
		alert('Please key in reason to reject.');
		return false;
	}
	return true;
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
	var ohUnitPrice = document.getElementById("overheadRate").value;
	
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
	var newOHCost = parseFloat(newTotHrs)*parseFloat(ohUnitPrice);
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
	document.getElementById("labourCost").value=formatCurrency(newTotCost);
	document.getElementById("overheadCost").value=formatCurrency(newOHCost);
	
	calculatingTotalCost();
}

function calculatingTotalCost()
{
	var fc = document.getElementById("fixedCost").value;
	var vc = document.getElementById("variableCost").value;
	var dc = document.getElementById("damageCost").value;
	var oc = document.getElementById("otherCost").value;
	var lc = document.getElementById("labourCost").value;
	var tc = document.getElementById("transportCost").value;
	var ohc = document.getElementById("overheadCost").value;
	var ta = document.getElementById("taxAmount").value;
	var sd = document.getElementById("secDeposit").value;
	var ad = document.getElementById("addlDeposit").value;
	
	
	

	var st = parseFloat(fc.replace(",",""))+parseFloat(vc.replace(",",""));
	var tc = parseFloat(fc.replace(",",""))+parseFloat(vc.replace(",",""))
			+parseFloat(oc.replace(",",""))	+parseFloat(ta.replace(",",""))
			+parseFloat(sd.replace(",",""))+parseFloat(tc.replace(",",""))
			+parseFloat(lc.replace(",",""))+parseFloat(ohc.replace(",",""))
			+parseFloat(dc.replace(",",""))+parseFloat(ad.replace(",",""));

	document.getElementById("subTotal").value = formatCurrency(st);
	document.getElementById("totalCost").value = formatCurrency(tc);
}

function printing()
{
	var isLoop = document.getElementById("isLoopService").value;
	if(isLoop=="Y")
		isLoop = "LOOP";
	else
		isLoop = "";
		
	var es = document.getElementById("estimateStatus").value;
	var warning = "";
	if(es=="75" || es=="31")
		warning = "** This is not for APPROVAL **";
	
	var labourCodeList = "";
	if(document.getElementById("labourCodeList")!=null)
		labourCodeList = document.getElementById("labourCodeList").value;
	var labourDescList = "";
	if(document.getElementById("labourDescList")!=null)
		labourDescList = document.getElementById("labourDescList").value;
	var labourUomList = "";
	if(document.getElementById("labourUomList")!=null)
		labourUomList = document.getElementById("labourUomList").value;
	var labourPriceList = "";
	if(document.getElementById("labourPriceList")!=null)
		labourPriceList = document.getElementById("labourPriceList").value;
	var labourQtyList = "";
	if(document.getElementById("labourQtyList")!=null)
		labourQtyList = document.getElementById("labourQtyList").value;
	var labourCostList = "";
	if(document.getElementById("labourCostList")!=null)
		labourCostList = document.getElementById("labourCostList").value;
	var totalLabCost = "";
	if(document.getElementById("totalLabCost")!=null)
		totalLabCost = document.getElementById("totalLabCost").value;
	
	document.PIVApplet.printTC(
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
			document.getElementById("otherCost").value,
			document.getElementById("secDeposit").value,
			document.getElementById("taxAmount").value,
			document.getElementById("labourCost").value,//labour cost
			document.getElementById("addlDeposit").value,//addl deposit
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
			document.getElementById("transportCost").value,//transportCost
			document.getElementById("overheadCost").value,//overHeadCost
			"",//document.getElementById("convCost").value,
			"",//document.getElementById("capitalCost").value,
			warning,//warnning
			document.getElementById("damageCost").value,
			"",//contingency cost
			labourCodeList,
			labourDescList,
			labourUomList,
			labourPriceList,
			labourQtyList,
			labourCostList,
			totalLabCost,
			
			"Temporary Connection",
			"",//company name,
			document.getElementById("totalDetailCost").value,
			document.getElementById("nonMatCodeList").value,
			document.getElementById("nonMatCostList").value
	); 
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
