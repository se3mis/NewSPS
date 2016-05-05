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
		var fc = document.getElementById("fixedCost").value;
		var vc = document.getElementById("variableCost").value;
		var oc = document.getElementById("otherCost").value;
		var cc = document.getElementById("convCost").value;
		var ta = document.getElementById("taxAmount").value;
		var sd = document.getElementById("secDeposit").value;
	
		var st = parseFloat(fc.replace(",",""))+parseFloat(vc.replace(",",""));
		var tc = parseFloat(fc.replace(",",""))+parseFloat(vc.replace(",",""))+parseFloat(oc.replace(",",""))+parseFloat(ta.replace(",",""))+parseFloat(sd.replace(",",""))+parseFloat(cc.replace(",",""));
	
		document.getElementById("subTotal").value = formatCurrency(st);
		document.getElementById("totalCost").value = formatCurrency(tc);
		
	}
		
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
		//var cc = document.getElementById("convCost").value;
		var ta = document.getElementById("taxAmount").value;
		var sd = document.getElementById("secDeposit").value;
		var lc = document.getElementById("labourCost").value;
		var tc = document.getElementById("transportCost").value;
		var oh = document.getElementById("overheadCost").value;
		var ad = document.getElementById("addlDeposit").value;
	
		var st = parseFloat(fc.replace(",",""))+parseFloat(vc.replace(",",""));
		var tc = parseFloat(fc.replace(",",""))+parseFloat(vc.replace(",",""))+parseFloat(oc.replace(",",""))
				+parseFloat(ta.replace(",",""))+parseFloat(sd.replace(",",""))
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
	var cc = document.getElementById("convCost").value;
	var ta = document.getElementById("taxAmount").value;
	var sd = document.getElementById("secDeposit").value;

	var st = parseFloat(fc.replace(",",""))+parseFloat(vc);
	var tc = parseFloat(fc.replace(",",""))+parseFloat(vc)+parseFloat(oc.replace(",",""))+parseFloat(ta.replace(",",""))+parseFloat(sd.replace(",",""))+parseFloat(cc.replace(",",""));

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
		//var cc = document.getElementById("convCost").value;
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
	
	var crType = document.getElementById("crType").value;
	
	if (crType=="CR")
	{
		if(matCode=="TRANSPORT")
		{
			document.getElementById("transportCost").value=formatCurrency(newMatCost);
		}
		else
		{
			
			var transCost = document.getElementById("transportCost").value;
			var labourCost = document.getElementById("labourCost").value;
			var ohCost = document.getElementById("overheadCost").value;
			var tMat = newTotCost-parseFloat(transCost.replace(",",""))-parseFloat(labourCost.replace(",",""))-parseFloat(ohCost.replace(",",""));
			var percent = parseFloat(document.getElementById("matPercent").value);
			
			tMat = tMat * percent;
			document.getElementById("otherCost").value=formatCurrency(tMat);
		}
		
	}
	else if (crType=="OT")
	{
		if(matCode=="TRANSPORT")
		{
			document.getElementById("transportCost").value=formatCurrency(newMatCost);
		}
		else
		{
			//if(newMatCost>0)
			//{
				var transCost = document.getElementById("transportCost").value;
				var labourCost = document.getElementById("labourCost").value;
				var ohCost = document.getElementById("overheadCost").value;
				var tMat = newTotCost-parseFloat(transCost.replace(",",""))-parseFloat(labourCost.replace(",",""))-parseFloat(ohCost.replace(",",""));
				var mntCost = document.getElementById("totalMntCost").value;
				if(mntCost=='')
					mntCost = 0;
				tMat = tMat - mntCost;
				document.getElementById("otherCost").value=formatCurrency(tMat);
			//}
		}
	}
	document.getElementById("txt"+matType+"MatCost"+matCode).value=formatCurrency(newMatCost);
	document.getElementById("totalDetailCost").value=formatCurrency(newTotCost);
	
	calculatingTotalCost();
	
}

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
/*	else if(document.getElementById("variableCost").value.length==0)
	{
		alert('Please key in Variable Cost.');
		document.getElementById("variableCost").focus();
		return false;
	}*/
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
	/*else if(!document.frmEstimate.wiringType[0].checked && !document.frmEstimate.wiringType[1].checked)
	{
		alert('Please select Wiring Type.');
		return false;
	}*/
	else
	{
		if(!isQtyFilled())
			return false;
		else
			return true;
		
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


function deleteOtherCR()
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
				var removeCost = 0;
				if(matcost.length>0)
					removeCost = parseFloat(matcost.replace(",",""));
				
				var mntcost = document.getElementById("txtSelMntCost"+matCode).value;
				var removeMntCost = 0;
				if(mntcost.length>0)
					removeMntCost = parseFloat(mntcost.replace(",",""));
				
				var oldOtherCost = document.getElementById("otherCost").value;
				var newOtherCost = parseFloat(oldOtherCost.replace(",",""))-parseFloat(removeCost)+parseFloat(removeMntCost);
				document.getElementById("otherCost").value=formatCurrency(newOtherCost);
				
				
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
	}*/		

}

function deleteReConn()
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
				var removeCost = 0;
				if(matcost.length>0)
					removeCost = parseFloat(matcost.replace(",",""));
				var percent = parseFloat(document.getElementById("matPercent").value);
				removeCost = removeCost * percent;
				
				var oldOtherCost = document.getElementById("otherCost").value;
				var newOtherCost = parseFloat(oldOtherCost.replace(",",""))-parseFloat(removeCost);
				document.getElementById("otherCost").value=formatCurrency(newOtherCost);
				
				
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
	document.getElementById("convCost").readOnly = true;
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
	
	var crType = document.getElementById("crType").value;
	if (crType=="CR" || crType=="OT")
	{
		document.getElementById("labourCost").value=formatCurrency(newTotCost);
		document.getElementById("overheadCost").value=formatCurrency(newOHCost);
	}
	
	calculatingTotalCost();
	
}

function calculatingTotalCost()
{
	var fc = document.getElementById("fixedCost").value;
	var vc = document.getElementById("variableCost").value;
	var oc = document.getElementById("otherCost").value;
	var ta = document.getElementById("taxAmount").value;
	var sd = document.getElementById("secDeposit").value;
	var cc = document.getElementById("convCost").value;
	var lc = 0;
	var trc = 0;
	var ohc = 0;
	
	var st = 0;
	var tc = 0;
	var crType = document.getElementById("crType").value;
	if (crType=="CR" || crType=="OT")
	{
		lc = document.getElementById("labourCost").value;
		trc = document.getElementById("transportCost").value;
		ohc = document.getElementById("overheadCost").value;
		
		tc = parseFloat(fc.replace(",",""))+parseFloat(vc.replace(",",""))
		+parseFloat(oc.replace(",",""))	+parseFloat(ta.replace(",",""))
		+parseFloat(sd.replace(",",""))+parseFloat(trc.replace(",",""))
		+parseFloat(lc.replace(",",""))+parseFloat(ohc.replace(",",""))
		+parseFloat(cc.replace(",",""));
	}
	else
	{
		tc = parseFloat(fc.replace(",",""))+parseFloat(vc.replace(",",""))
		+parseFloat(oc.replace(",",""))	+parseFloat(ta.replace(",",""))
		+parseFloat(sd.replace(",",""))	+parseFloat(cc.replace(",",""));
	
	}
	

	var st = parseFloat(fc.replace(",",""))+parseFloat(vc.replace(",",""));
				
	var crType = document.getElementById("crType").value;
	if(crType=='OT')
	{
		var contPercent = document.getElementById("contingencyPercent").value;
		if(contPercent=='')
			contPercent = 0;
		contPercent = parseFloat(contPercent)/100;
		var contC = tc*contPercent;
		
		var bcPercent = document.getElementById("boardChargePercent").value;
		if(bcPercent=='')
			bcPercent = 0;
		bcPercent = parseFloat(bcPercent)/100;
		var boardC = tc*bcPercent;
		
		document.getElementById("contingencyCost").value = formatCurrency(contC);
		document.getElementById("boardCharge").value = formatCurrency(boardC);
		tc = tc+contC+boardC;
	}
	document.getElementById("subTotal").value = formatCurrency(st);
	document.getElementById("totalCost").value = formatCurrency(tc);
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
 

function printingPhaseChange()
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
	var naration = "CR/Phase Change";
	var crType = document.getElementById("crType").value;
	if(crType=="CP")
		naration = naration+" - (1,30)-->(3,30)";
	else if(crType=="CC")
		naration = naration+" - (1,30)-->(3,60)";
	
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
	
	document.PIVApplet.printCR(
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
			"",//document.getElementById("capitalCost").value,
			warning,//warnning
			"",//damageCost
			"",//contingency cost
			
			labourCodeList,
			labourDescList,
			labourUomList,
			labourPriceList,
			labourQtyList,
			labourCostList,
			totalLabCost,
			naration,
			document.getElementById("companyName").value,
			document.getElementById("totalDetailCost").value,
			document.getElementById("nonMatCodeList").value,
			document.getElementById("nonMatCostList").value
	); 
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
	var crType = document.getElementById("crType").value;
	var naration = "CR/Re-Connection";
	if(crType=="OT")
		naration = "CR/Other - "+document.getElementById("description").value;
	
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
	
	document.PIVApplet.printCR(
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
			document.getElementById("transportCost").value,//transportCost
			document.getElementById("overheadCost").value,//overHeadCost
			document.getElementById("convCost").value,
			"",//document.getElementById("capitalCost").value,
			warning,//warnning
			"",//damageCost
			document.getElementById("contingencyCost").value,//contingency cost
			
			labourCodeList,
			labourDescList,
			labourUomList,
			labourPriceList,
			labourQtyList,
			labourCostList,
			totalLabCost,
			naration,
			document.getElementById("companyName").value,
			document.getElementById("totalDetailCost").value,
			document.getElementById("nonMatCodeList").value,
			document.getElementById("nonMatCostList").value
	); 
}

function printingReConn()
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
	var crType = document.getElementById("crType").value;
	var naration = "CR/Re-Connection";
	if(crType=="OT")
		naration = "CR/Other - "+document.getElementById("description").value;
	
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
	
	document.PIVApplet.printCR(
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
			document.getElementById("transportCost").value,//transportCost
			document.getElementById("overheadCost").value,//overHeadCost
			document.getElementById("convCost").value,
			"",//document.getElementById("capitalCost").value,
			warning,//warnning
			"",//damageCost
			"",//contingency cost
			
			labourCodeList,
			labourDescList,
			labourUomList,
			labourPriceList,
			labourQtyList,
			labourCostList,
			totalLabCost,
			naration,
			document.getElementById("companyName").value,
			document.getElementById("totalDetailCost").value,
			document.getElementById("nonMatCodeList").value,
			document.getElementById("nonMatCostList").value
	); 
}

function printingMeterChange()
{
	
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
	
	document.PIVApplet.printCR(
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
			"0.00",
			"0.00",
			document.getElementById("estimateStatusDesc").value,
			"0.00",//transportCost
			"0.00",//overHeadCost
			"0.00",//document.getElementById("convCost").value,
			"0.00",//document.getElementById("capitalCost").value,
			warning,//warnning
			"0.00",//damage cost
			"",//contingency cost
			
			labourCodeList,
			labourDescList,
			labourUomList,
			labourPriceList,
			labourQtyList,
			labourCostList,
			totalLabCost,
			
			"Meter Damage",
			document.getElementById("companyName").value,
			document.getElementById("totalDetailCost").value,
			document.getElementById("nonMatCodeList").value,
			document.getElementById("nonMatCostList").value
	); 
}

function calculateOtherCRMaintenanceCost(obj,event,matType,matCode,unitPrice)
{
	var qty = document.getElementById("txtMntQty"+matCode).value;
	var estQty = document.getElementById("txtSelQty"+matCode).value;
	if(estQty=='')
		estQty = 0;
	/*var otLabCost = document.getElementById("otherLabourCost").value;
	if(otLabCost=='')
		otLabCost = 0;*/
	if(parseFloat(estQty)>0)
	{
		if(qty=='')
			qty = 0;
		if(parseFloat(qty)<=parseFloat(estQty))
		{
			var preQty = document.getElementById("savedMntQty"+matCode).value;
			if(preQty=='')
				preQty = 0;
			
			var newMatCost = parseFloat(qty)*parseFloat(unitPrice);
			
			var preMatCost = parseFloat(preQty)*parseFloat(unitPrice);
			
			var preTotalCusCost = document.getElementById("totalMntCost").value; 
			if(preTotalCusCost=='')
				preTotalCusCost = 0;
			else
				preTotalCusCost = parseFloat(preTotalCusCost.replace(",",""));
			
			var newTotalCusCost = preTotalCusCost-preMatCost+newMatCost;
			//document.getElementById("otherCost").value=formatCurrency(newTotalCusCost);
			document.getElementById("savedMntQty"+matCode).value=qty;
			
			document.getElementById("txtSelMntCost"+matCode).value=formatCurrency(newMatCost);
			document.getElementById("totalMntCost").value=formatCurrency(newTotalCusCost);
			
			
			var labCost = document.getElementById("txtSelMatCostLABOUR").value;
			var ohCost = document.getElementById("txtSelMatCostOVERHEAD").value;
			var transCost = document.getElementById("txtSelMatCostTRANSPORT").value;
			var detailCost = document.getElementById("totalDetailCost").value;
			
			if(labCost=='')
				labCost = 0;
			else
				labCost = parseFloat(labCost.replace(",",""));
			if(ohCost=='')
				ohCost = 0;
			else
				ohCost = parseFloat(ohCost.replace(",",""));
			if(transCost=='')
				transCost = 0;
			else
				transCost = parseFloat(transCost.replace(",",""));
			if(detailCost=='')
				detailCost = 0;
			else
				detailCost = parseFloat(detailCost.replace(",",""));
			
			var matCost = detailCost - transCost - ohCost - labCost -newTotalCusCost;
			
			document.getElementById("otherCost").value=formatCurrency(matCost);
			
			calculatingTotalCost();
		}
		else
		{
			alert('Maintenance quanity cannot be greater than estimated quantity.');
			document.getElementById("txtMntQty"+matCode).value = "";
		}
	}
	else
	{
		alert('Please key in the estimated quantity first.');
		document.getElementById("txtMntQty"+matCode).value = "";
	}
}
