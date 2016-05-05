<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@ page language="java" %>
<html>
<head>
<sx:head />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>

<s:if test="%{isViewOnly == 'true'}">
VIEW ESTIMATION
</s:if>
<s:elseif test="%{isModify=='true'}">
MODIFY ESTIMATION
</s:elseif>
<s:elseif test="%{isUndoReject=='true'}">
UNDO REJECTED ESTIMATION
</s:elseif>
<s:elseif test="%{isApprove=='true'}">
APPROVE/REJECT ESTIMATION
</s:elseif>
<s:elseif test="%{isPrint=='true'}">
PRINT ESTIMATION
</s:elseif>
<s:else>NEW ESTIMATION</s:else>
</title>
<link rel="stylesheet" type="text/css" href="../../style/style.css" />

<script type="text/javascript" src="../../script/estimate.js"> </script>
<script type="text/javascript" src="../../script/javascript.js"> </script>
<script type="text/javascript" src="../../script/jquery/jquery.js"> </script>
<script type="text/javascript" src="../../script/jquery/jquery.min.js"> </script>

<link rel="stylesheet" type="text/css" href="../../script/ext-3.3.1/css/setup.css" media="all" />
<link rel="stylesheet" type="text/css" href="../../script/ext-3.3.1/resources/css/ext-all.css" />
<link rel="stylesheet" type="text/css" href="../../script/ext-3.3.1/resources/css/xtheme-gray.css" />
 <script type="text/javascript" src="../../script/ext-3.3.1/adapter/ext/ext-base.js"></script>
        <script type="text/javascript" src="../../script/ext-3.3.1/ext-all-debug.js"></script>
<link rel="stylesheet" type="text/css" href="../../style/datePicker.css" />		
<script src="../../script/jquery/jquery.datePicker-min.js" type="text/javascript"></script>
<script type="text/javascript">
var estmatedCost = '';
var nodeId='';
var nodeText='';
var selectedpegItem='';

var itemCodeList = new Array();
var itemDescriptionList = new Array();
var itemUOMList = new Array();
var itemUnitPriceList = new Array();
var itemQuantityList = new Array();
var itemCostList = new Array();
var allSelected ="0";

Ext.onReady(function() {
clearPeggingScheduleForm();
 $('#estimateDatePicker').datePicker({clickInput:true});
 $('#pivDatePicker').datePicker({clickInput:true});

	loadStdEstimationRefs();
});

function printingEstimate()
{

	
	
	//getData();
	//alert(itemCodeList.length);
	//for (var j = 0; j < itemCodeList.length; j++) {
	//	allSelected = allSelected+itemCodeList[j];
	//	alert(allSelected);
	//} 

	
	document.PIVApplet.print(
			$('#estimateNo').val(),$('#fundsou').val(),$('#description').val(),$('#categoryId').val(),$('#estimateDatePicker').val(),$('#listRescd').val(),
			$('#listResName').val(),$('#listUom').val(),$('#listUnitPrice').val(),$('#listQuantity').val(),$('#listEstimateCost').val(),$('#totPages').val(),$('#esname').val());
			
	
}
function printingEstimateSummary()
{
	
	
	
	//getData();
	//alert(itemCodeList.length);
	//for (var j = 0; j < itemCodeList.length; j++) {
	//	allSelected = allSelected+itemCodeList[j];
	//	alert(allSelected);
	//} 

			 
	document.PIVApplet.printDetailsEstimate(
			$('#estimateNo').val(),$('#fundsou').val(),$('#description').val(),$('#categoryId').val(),$('#estimateDatePicker').val()
			,$('#pivNo').val(),$('#amount').val(),$('#pivDatePicker').val(),$('#district').val(),$('#divSec').val(),$('#area').val()
			,$('#eCSC').val(),"0",$('#materialCost').val(),$('#wayleaves').val(),$('#transport').val(),$('#labour').val()
			,$('#subsistance').val(),$('#contingencies').val(),$('#overhead').val(),$('#otherCost').val(),$('#totalConstructionCost').val(),
			$('#rebateCost').val(),$('#totalcapitalCost').val(),$('#otherCostType').val());
			
	
}

function clearPeggingScheduleForm(){
	$.getJSON("smc/clearPeggingScheduleForm.ajax", {
			ajax_command: "clearPeggingScheduleForm",ajax_ieCacheFix:new Date().getTime() },
			function(json){ 
	
	});
	$("#estimateDetails tr:gt(0)").remove();
}
function getData(){

var rows =$('#estimateDetails tr');
		var rowcount = rows.length;


var value;
	$('#estimateDetails').find('input[name$=resourceCode]').each(function(i){
		//alert($(this).attr('name'));
		//alert($(this).val());
		value = $(this).val()+"###";
		itemCodeList.push(value);

});
	$('#estimateDetails').find('input[name$=resourceName]').each(function(i){
		//alert($(this).attr('name'));
		value = $(this).val()+"###";
		itemDescriptionList.push($(this).val());

		
});
	$('#estimateDetails').find('input[name$=uom]').each(function(i){
		//alert($(this).attr('name'));
		value = $(this).val()+"###";
		itemUOMList.push($(this).val());

		
});
	$('#estimateDetails').find('input[name$=unitPrice]').each(function(i){
		//alert($(this).attr('name'));
		value = $(this).val()+"###";
		itemUnitPriceList.push($(this).val());

		
		
});
	$('#estimateDetails').find('input[name$=estimateQuantity]').each(function(i){
		//alert($(this).attr('name'));
		value = $(this).val()+"###";
		itemQuantityList.push($(this).val());

		
});
$('#estimateDetails').find('input[name$=estimateCost]').each(function(i){
		//alert($(this).attr('name'));
		value = $(this).val()+"###";
		itemCostList.push($(this).val());

		
});
	
}

function intToFormat(nStr)
{
 nStr += '';
 x = nStr.split('.');
 x1 = x[0];
 x2 = x.length > 1 ? '.' + x[1] : '';
 var rgx = /(\d+)(\d{3})/;
 var z = 0;
 var len = String(x1).length;
 var num = parseInt((len/2)-1);

  while (rgx.test(x1))
  {
    if(z > 0)
    {
      x1 = x1.replace(rgx, '$1' + ',' + '$2');
    }
    else
    {
      x1 = x1.replace(rgx, '$1' + ',' + '$2');
      rgx = /(\d+)(\d{2})/;
    }
    z++;
    num--;
    if(num == 0)
    {
      break;
    }
  }
 return x1 + x2;
}

function loadStdEstimationRefs(){
	clearPeggingScheduleForm();
	
	 $.getJSON("sps/loadEstmationNumbers.ajax?selectedCustomer=" + 1, {ajax_command:"loadEstmationNumbers",ajax_ieCacheFix:new Date().getTime()}, function(json) {
//alert("hi");
	 //alert(json.jsonarrayapplicationrefs.length);
	 //  alert(json.jsonarrayEstimationRefNumbers.length);
	  // alert("hi");
      if (json.jsonarrayapplicationrefs != null && json.jsonarrayapplicationrefs.length > 0) {
          for (x = 0; x < json.jsonarrayapplicationrefs.length; x++) {
           
              $("#applicationRefs1 select").append($('<option></option>').val(json.jsonarrayapplicationrefs[x].id).html(json.jsonarrayapplicationrefs[x].name));
          }
      }
	 
	
	       if (json.jsonarrayEstimationRefNumbers != null && json.jsonarrayEstimationRefNumbers.length > 0) {
	           for (x = 0; x < json.jsonarrayEstimationRefNumbers.length; x++) {
	            
	               $("#estimateNos select").append($('<option></option>').val(json.jsonarrayEstimationRefNumbers[x].id).html(json.jsonarrayEstimationRefNumbers[x].name));
	           }
	       }
		  
	       $('#costCenterNo').val(json.costCenter);
		 });
	
}



function loadDefaultDropDownsValues(){
clearPeggingScheduleForm();
	  if( $('#applicationId').val() != '-1' || $('#estimateNo').val() != '-1' ){
	  //alert("hi");
	  $("#wareHouse select option").remove();
	  $("#fundsource select option").remove();
	  $("#category select option").remove();
	  $("#funid select option").remove();
	  
	  
	 $.getJSON("sps/loadDefaultDropDownsValues.ajax?selectedCustomer=" + 1, {ajax_command:"loadDropDownsValues",ajax_ieCacheFix:new Date().getTime()}, function(json) {

				   if (json.jsonWarehouses != null && json.jsonWarehouses.length > 0) {
					   for (x = 0; x < json.jsonWarehouses.length; x++) {
						
						   $("#wareHouse select").append($('<option></option>').val(json.jsonWarehouses[x].id).html(json.jsonWarehouses[x].name));
					   }
				   }
				 $('#costCenterNo').val(json.costCenter);
				 $('#wareHouseId').val(json.warehouse);

				 if (json.jsonFundsources != null && json.jsonFundsources.length > 0) {
					 for (x = 0; x < json.jsonFundsources.length; x++) {
					  
						 $("#fundsource select").append($('<option></option>').val(json.jsonFundsources[x].id).html(json.jsonFundsources[x].name));
					 }
				 }
				  $('#fundsou').val(json.fundsource);
				 
				   if (json.jsonCategoryCodes != null && json.jsonCategoryCodes.length > 0) {
					   for (x = 0; x < json.jsonCategoryCodes.length; x++) {
						
						   $("#category select").append($('<option></option>').val(json.jsonCategoryCodes[x].id).html(json.jsonCategoryCodes[x].name));
					   }
				   }
				
				 $('#categoryId').val(json.categoryCode);
				 
				  if (json.jsonfundIds != null && json.jsonfundIds.length > 0) {
					   for (x = 0; x < json.jsonfundIds.length; x++) {
						
						   $("#funid select").append($('<option></option>').val(json.jsonfundIds[x].id).html(json.jsonfundIds[x].name));
					   }
				   }
				
				 $('#fundid').val(json.fundid);
			
	 });
	  }
	
	
}

function loadCategoryCodes(){
	$("#category select option").remove();
	 $.getJSON("sps/loadCategoryCodes.ajax?warehouse=" +$('#wareHouseId').val(), {ajax_command:"loadCategoryCodes",ajax_ieCacheFix:new Date().getTime()}, function(json) {

       if (json.categoryCodes != null && json.categoryCodes.length > 0) {
           for (x = 0; x < json.categoryCodes.length; x++) {
            
               $("#category select").append($('<option></option>').val(json.categoryCodes[x].id).html(json.categoryCodes[x].name));
           }
       }
      
  	 $('#categoryId').val(json.categoryCode);

  
	 });
	
	
}

function loadFundIds(){
//alert( $('#fundsou').val());
	$("#funid select option").remove();
	 $.getJSON("sps/loadFundIds.ajax?foundsource=" + $('#fundsou').val()+"&deptId="+$('#costCenterNo').val(), {ajax_command:"loadFundIds",ajax_ieCacheFix:new Date().getTime()}, function(json) {

       if (json.jsonfundIds != null && json.jsonfundIds.length > 0) {
					   for (x = 0; x < json.jsonfundIds.length; x++) {
						
						   $("#funid select").append($('<option></option>').val(json.jsonfundIds[x].id).html(json.jsonfundIds[x].name));
					   }
				   }
				
				 $('#fundid').val(json.fundid);

  
	 });
	
	
}

function loadEstimationData(val){
		loadDefaultDropDownsValues();
	   		//alert(val.value);
		if(val.value=="-1"){
			
		$('#totalCost').val("");	
		$('#rebate').val("");
		$('#estimateDatePicker').val("");	
		$('#fundsou').val("");	
		$('#wareHouseId').val(-1);	
		$('#fundid').val("");	
		$('#amount').val("");	
		$('#fileRef').val("");	
		$('#description').val("");	
		$('#categoryId').val("");	
		$('#pivNo').val("");
		$('#pivDatePicker').val("");
		
		//$("#amount").attr("disabled", "disabled");
		$("#update").attr("disabled", "disabled");
		$("#estimateNo").val("");
		$("#applicationId").val(-1);
			//$("#update").attr("disabled", "disabled");
			//$("#save").attr("disabled", "disabled");
			//$("#bulkAddSub").attr("disabled", "disabled");
			//$("#landAddSub").attr("disabled", "disabled");
			return;	
		}else{
			//document.getElementById('normsdiv').style.display = 'none';
		  	$("#update").removeAttr("disabled");
		  	//$("#bulkAddSub").removeAttr("disabled");
		  	//$("#landAddSub").removeAttr("disabled");
		   	//$("#update").removeAttr("disabled");
		}
	
		$.getJSON("smc/loadEstimationDetails.ajax?estimateNo="+val.value, {
		ajax_command: "loadEstimationDetailsForPrint",ajax_ieCacheFix:new Date().getTime() },
		function(json){ 
			
			$('#totalCost').val(json.totalCost);	
			$("#totalCostDisplay").val(intToFormat(json.totalCost));
			$('#rebate').val(json.rebate);
			$('#estimateDatePicker').val(json.estimateDatePicker);	
			$('#fundsou').val(json.fundsou);	
			$('#fundid').val(json.fundid);
			$('#fileRef').val(json.fileRef);
			$('#description').val(json.description);	
			$('#categoryId').val(json.categoryId);	
			$('#pivNo').val(json.pivNo);	
			$('#amount').val(json.amount);
			$('#applicationId').val(json.stdEstimateNo);
			$('#costCenterNo').val(json.costCenter);
//alert(json.estimateDetails.length);			
			$('#pivDatePicker').val(json.pivDatePicker);	
			$('#listRescd').val(json.listRescd);	
			$('#listResName').val(json.listResName);	
			$('#listUnitPrice').val(json.listUnitPrice);	
			$('#listUom').val(json.listUom);	
			$('#listQuantity').val(json.listQuantity);	
			$('#listEstimateCost').val(json.listEstimateCost);	
			$('#materialCost').val(json.materialCost);	
			$('#wayleaves').val(json.wayleaves);	
			$('#transport').val(json.transport);	
			$('#labour').val(json.labour);	
			$('#subsistance').val(json.subsistance);	
			$('#contingencies').val(json.contingencies);	
			$('#overhead').val(json.overhead);	
			$('#totalConstructionCost').val(json.totalConstructionCost);	
			$('#totalcapitalCost').val(json.totalcapitalCost);
			$('#rebateCost').val(json.rebateCost);
			$('#totPages').val(json.totPages);
			$('#otherCost').val(json.otherCost);
			$('#divSec').val(json.divSec);
			$('#area').val(json.area);
			$('#eCSC').val(json.eCSC);
			$('#district').val(json.district);
			$('#esname').val(json.esname);
			$('#otherCostType').val(json.otherCostType);
			
			
				$.each(json.estimateDetails,function(i,estimateDetail){
							
																
						var estimateDetails = '<tr  id="lineseciid'+estimateDetail.resourceCode+'">'+	
										//'<td id="radio'+estimateDetail.selectedPegId+'" align="middle" ><input type="radio" class="required" style="text-align: right" name="pegradio" id="'+estimateDetail.selectedPegId+'"  value="'+estimateDetail.selectedPegId+'"  /></td>'+																							
										//'<td id="resourceType'+estimateDetail.selectedPegId+estimateDetail.resourceCode+'" align="middle" >'+estimateDetail.resourceType+'</td>'+
										'<td id="resourceCode'+i+'" align="left" ><input type="text" class="required" style="text-align: right" name="resourceCode" id="resourceCode'+estimateDetail.resourceCode+'"  value="'+estimateDetail.resourceCode+'"  readOnly="true"/></td>'+
										'<td id="resourceName'+i+'" align="left" ><input type="text" class="required" style="text-align: right" name="resourceName" id="resourceName'+estimateDetail.resourceName+'"  value="'+estimateDetail.resourceName+'"  readOnly="true"/></td>'+
										'<td id="uom'+i+'" align="left" ><input type="text" class="required" style="text-align: right" name="uom'+i+'" id="uom"  value="'+estimateDetail.uom+'"  readOnly="true"/></td>'+
										'<td id="unitPrice'+i+'" align="left" ><input type="text" class="required" style="text-align: right" name="unitPrice" id="unitPrice'+estimateDetail.unitPrice+'"  value="'+estimateDetail.unitPrice+'"  readOnly="true"/></td>'+
										'<td id="estimateQuantity'+i+'" align="left" ><input type="text" class="required" style="text-align: right" name="estimateQuantity" id="estimateQuantity'+estimateDetail.estimateQuantity+'"  value="'+estimateDetail.estimateQuantity+'"  readOnly="true"/></td>'+
										'<td id="estimateCost'+i+'" align="left" ><input type="text" class="required" style="text-align: right" name="estimateCost" id="estimateCost'+estimateDetail.estimateCost+'"  value="'+estimateDetail.estimateCost+'"  readOnly="true"/></td>'+
										'<td id="rebateQuantity'+i+'" align="left" ><input type="text" class="required" style="text-align: right" name="rebateQuantity" id="rebateQuantity'+estimateDetail.rebateQuantity+'"  value="'+estimateDetail.rebateQuantity+'"  readOnly="true"/></td>'+
										'<td id="rebateCost'+i+'" align="left" ><input type="text" class="required" style="text-align: right" name="rebateCost" id="rebateCost'+estimateDetail.rebateCost+'"  value="'+estimateDetail.rebateCost+'"  readOnly="true"/></td>'+
										'<td id="reusableQuantity'+i+'" align="left" ><input type="text" class="required" style="text-align: right" name="reusableQuantity" id="reusableQuantity'+estimateDetail.reusableQuantity+'"  value="'+estimateDetail.reusableQuantity+'"  readOnly="true"/></td>'+
										'<td id="offChargeQuantity'+i+'" align="left" ><input type="text" class="required" style="text-align: right" name="offChargeQuantity" id="offChargeQuantity'+estimateDetail.offChargeQuantity+'"  value="'+estimateDetail.offChargeQuantity+'"  readOnly="true"/></td>'+
										



										//'<td width=\'25px\' align=\'center\'><image onclick="deleteDetailLine(\'' + estimateDetail.isFromPegSchedule + '\'' +',\'' + estimateDetail.resourceCode + '\'' +',\'' + null+'\')" src="../../image/X.png" /></td>'+
	
										'</tr>';
									
									$('#estimateDetails').append(estimateDetails);
							
				}
				);

				$.each(json.pegDetails,function(i,pegDetail){
				
	
						var pkgTable1 = '<tr  id="pegid'+pegDetail.selectedPegId+'">'+	
						'<td id="radio'+pegDetail.selectedPegId+'" align="left" ><input type="radio" class="required" style="text-align: right" name="pegradio" id="'+pegDetail.selectedPegId+'"  value="'+pegDetail.selectedPegId+'"  onchange="onNumChangePegId(this)"  /></td>'+																							
						'<td id="description'+pegDetail.selectedPegId+'" align="left" >'+pegDetail.description+'</td>'+
						'<td id="quantity'+pegDetail.selectedPegId+'" align="left" >'+pegDetail.quantity+'</td>'+
						//'<td align="right"><input type="text" class="required" style="text-align: right" name="'+json.quantity+'" id="'+json.quantity+'"  maxlength="10" size="10" value="" onchange="onNumChange1(this)" /></td>'+
						//'<td width=\'25px\' align=\'center\'><image onclick="deletePegDetailLine(\'' + json.selectedPegId + '\')" src="../../image/X.png" /></td>'+
						'</tr>';
					
						$('#pkgtable').append(pkgTable1);
	
				}
				);
			});

		
	}
</script>

</head>

<body>
<table id="tblOuter" class="tblOuter">
	<tbody>
		<tr>
			<td>
			<table id="tblInner" class="tblInner" >
				<tbody>
					<tr>
						<td width="303" id="tdHeader">
						<table id="tblHeader" class="tblHeader" cellpadding="0"
							cellspacing="0">
							<tbody>
								<tr>
				                                         <td ><img src="../../image/NewSPS2.gif" width="100%"  alt="NewSPS2"/>
				                                         </td>
				                                         <td width="*" bgcolor="#000033"></td>
				                                         <td width="*" bgcolor="#000033"></td>
			                  </tr>	
							</tbody>
						</table>
					  </td>
					</tr>
					<!--tr>
						<td></td>
					</tr-->
					<tr>
						<td id="tdWelcome">
						<table class="tblWelcome" border="0" cellpadding="0"
							cellspacing="0">
							<thead>
								<tr>
									<th width="70%"></th>
									<th width="10%"></th>
									<th width="10%"></th>
									<th width="*"></th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td class="tdPath"><s:property value="path"></s:property></td>
									<td class="tdWelcomeLabel">Welcome</td>
									<td class="tdWelcomeUser"><s:property
										value="loggedInUserId"></s:property></td>
									<td class="tdLogOut"><a href="<s:url action="logout"/>">Logout</a></td>
								</tr>
							</tbody>
						</table>
						</td>
					</tr>
					<tr  class="tblInner">
						<td>
						<s:form id="frmEstimate" name="frmEstimate" action="pegSchedule!updateDetailEstimationItembyitemPrint" method="POST"
										validate="true" theme="simple">
						  		<table align="center" class="tldFromContent1" width="100%">
						  			<tr>
									<td width="25%">Estimate No</td>
									<td width="25%">
										<div id="estimateNos"><select name="estimateNo" id="estimateNo" onchange="loadEstimationData(this)"><option value="-1">--Select--</option></select></div>
									</td>
									<td width="25%">Reference No</td>
									<td width="25%">
									<div id="applicationRefs1"><select name="applicationId" id="applicationId" onchange="loadDefaultDropDownsValues()"><option value="-1">--Select--</option></select></div>
								
									</td>
									<td  width="25%">File Ref</td>
									<td  width="25%"><input type="text" name="fileRef"  id="fileRef" " disabled="disabled"/></td>
									
									</tr>
									<tr>
									
									<td  width="25%">Cost Center</td>
									<td  width="25%"><input type="text" name="costCenterNo"  id="costCenterNo"  disabled="disabled"/>
									</td>
									<td  width="25%">&nbsp;</td>
									<td  width="25%">&nbsp;</td>
									<td  width="25%">PIV Number</td>
									<td  width="25%"><input type="text" name="pivNo"  id="pivNo" disabled="disabled"/></td>
									</tr>
									<tr>
									<td  width="25%">Ware House</td>
									<td  width="25%"><div id="wareHouse"><select name="wareHouseId" id="wareHouseId" onchange="loadCategoryCodes()" disabled="disabled"></select></div></td>
									<td  width="25%">&nbsp;</td>
									<td  width="25%">&nbsp;</td>
									<td  width="25%">Amount</td>
									<td  width="25%"><input type="text" name="amount"  id="amount" onChange="validateQuantity(this)" disabled="disabled"/></td>
									
									</tr>
									<tr>
									<td  width="25%">Estimate Date</td>
									<td  width="25%"><input type="text"  name="it" id="estimateDatePicker" readOnly="true" disabled="disabled"/></td>
									<td  width="25%">Fund Source</td>
									<td  width="25%"><div id="fundsource"><select name="fundsou" id="fundsou" onchange="loadFundIds()" disabled="disabled"></select></div></td>
									<td  width="25%">Rebate</td>
									<td  width="25%"><input type="text" name="rebate"  id="rebate" onChange="validateQuantity(this)" disabled="disabled"/></td>
									</tr>
									<tr>
									<td  width="25%">Estimate categoty</td>
									<td  width="25%"><div id="category"><select name="categoryId" id="categoryId" disabled="disabled"></select></div></td>
									<td  width="25%">Fund ID</td>
									<td  width="25%"><div id="funid"><select name="fundid" id="fundid" disabled="disabled"></select></div></td>
									<td  width="25%">Description</td>
									<td  width="25%"><textarea name="description" id="description" rows="5" cols="60" disabled="disabled"></textarea></td>
									
									</tr>
									<tr>
									<td  width="25%">Total Cost</td>
									<td  width="25%"><input type="hidden" name="totalCost"  id="totalCost" readOnly="true" disabled="disabled"/>
									<input type="text" name="totalCostDisplay"  id="totalCostDisplay" readOnly="true" disabled="disabled"/></td>
									<td  width="25%">PIV Date</td>
									<td  width="25%"><input type="text"  name="pivDate" id="pivDatePicker" readOnly="true" disabled="disabled"/></td>
									<td  width="25%">&nbsp;</td>
									<td  width="25%">&nbsp;</td>
									</tr>
									<tr>
									<td  width="25%">&nbsp;</td>
									<td  width="25%">&nbsp;</td>
									<td  width="25%">&nbsp;</td>
									<td  width="25%">&nbsp;</td>
									<td  width="25%">&nbsp;</td>
									<td  width="25%">&nbsp;</td>
									
									</tr>
									<tr>
									<td  width="25%">Div Sec</td>
									<td  width="25%"><input type="text" name="divSec"  id="divSec" value="" size="30"/></td>
									<td  width="25%">Area</td>
									<td  width="25%"><input type="text" name="area"   id="area" value="" size="30"/></td>
									<td  width="25%">E CSC</td>
									<td  width="25%"><input type="text" name="eCSC" id="eCSC"  value=""  size="30"/></td>
									
									</tr>
									<tr>
									<td  width="25%">&nbsp;</td>
									<td  width="25%">&nbsp;</td>
									<td  width="25%">&nbsp;</td>
									<td  width="25%">&nbsp;</td>
									<td  width="25%">&nbsp;</td>
									<td  width="25%">&nbsp;</td>
									
									</tr>
									<tr>
									<td  width="25%">District</td>
									<td  width="25%"><input type="text" name="district"   id="district" value="" size="30"/></td>
									<td  width="25%">Es Name</td>
									<td  width="25%"><input type="text" name="esname"   id="esname" value="" size="30"/></td>
									<td  width="25%">&nbsp;</td>
									<td  width="25%">&nbsp;</td>
									
									</tr>
									
									
									
									<tr>
									<td  width="25%">&nbsp;&nbsp;&nbsp;&nbsp;</td>
									<td  width="25%">&nbsp;&nbsp;&nbsp;&nbsp;</td>
									<td  width="25%">&nbsp;&nbsp;&nbsp;&nbsp;</td>
									<td  width="25%">&nbsp;&nbsp;&nbsp;&nbsp;</td>
									<td  width="25%">&nbsp;&nbsp;&nbsp;&nbsp;</td>
								
									<td  width="25%">
									
									<input type="hidden" name="totalCost"  id="totalCost" readOnly="true" disabled="disabled"/>
									
									
									<input type="hidden" name="listRescd"  id="listRescd" />
									<input type="hidden" name="listResName"  id="listResName" />
									<input type="hidden" name="listUnitPrice"  id="listUnitPrice" />
									<input type="hidden" name="listUom"  id="listUom" />
									<input type="hidden" name="listQuantity"  id="listQuantity" />
									<input type="hidden" name="listEstimateCost"  id="listEstimateCost" />
									<input type="hidden" name="materialCost"  id="materialCost" />
									<input type="hidden" name="wayleaves"  id="wayleaves" />
									
									<input type="hidden" name="transport"  id="transport" />
									<input type="hidden" name="labour"  id="labour" />
									<input type="hidden" name="subsistance"  id="subsistance" />
									<input type="hidden" name="contingencies"  id="contingencies" />
									<input type="hidden" name="overhead"  id="overhead" />
									<input type="hidden" name="otherCost"  id="otherCost" />
									<input type="hidden" name="totalConstructionCost"  id="totalConstructionCost" />
									<input type="hidden" name="rebateCost"  id="rebateCost" />
									<input type="hidden" name="totalcapitalCost"  id="totalcapitalCost" />
									<input type="hidden" name="totPages"  id="totPages" />
									
									<input type="hidden" name="otherCostType"  id="otherCostType" />
									
									<s:if test="%{costCenterNo=='520.20' || costCenterNo=='520.30' || costCenterNo=='521.00'  || costCenterNo=='521.20'  || costCenterNo=='521.40'  || costCenterNo=='521.10' || costCenterNo=='514.20' || costCenterNo=='521.10' || costCenterNo=='521.20' ||
																									costCenterNo=='521.30' || costCenterNo=='521.40' || costCenterNo=='521.50' || costCenterNo=='522.00'  ||
																									costCenterNo=='522.10' || costCenterNo=='522.20' ||
																									costCenterNo=='522.40' || costCenterNo=='522.50' || costCenterNo=='522.60' ||
																									costCenterNo=='523.10' || costCenterNo=='523.40' || costCenterNo=='523.00'  ||
																									costCenterNo=='523.60' || costCenterNo=='523.70' ||
																									costCenterNo=='524.10' || costCenterNo=='524.20' || costCenterNo=='524.00' ||
																									costCenterNo=='524.30' || costCenterNo=='524.40' || costCenterNo=='524.50' ||
																									costCenterNo=='525.10' || costCenterNo=='525.20' || costCenterNo=='525.30' || costCenterNo=='525.00'  ||
																									costCenterNo=='526.10' || costCenterNo=='526.20' || costCenterNo=='526.30' || costCenterNo=='526.40' || costCenterNo=='526.00'}">	
										<s:submit theme="simple" value="Print(A4)" method="PrintEstimateA4" id="btnprint1" ></s:submit>
									</s:if>
									
																	
									<s:else>
									<s:submit theme="simple" value="Print Rebate(A4)" method="PrintEstimateRebate" id="btnprint2" ></s:submit>
											<s:submit theme="simple" value="Print(A4)" method="PrintEstimateA4" id="btnprint2" ></s:submit>	
											<s:submit theme="simple" value="View Sketch" method="ViewSketch" id="btnprint" ></s:submit>
									</s:else>
									<applet codebase="../../PrintLib" code="standardEstimate.DetailEstimateApp" archive="PrintJar.jar" name="PIVApplet" width="1" height="1"  >
													</applet>
									<input type="button" value="Print Estimate(A3)" id="btnPrint" onclick="javascript:printingEstimate()" />
									<input type="button"" value="Print Summary Estimate(A3)" id="btnPrint" onclick="javascript:printingEstimateSummary()" />
									
									
									
									<s:submit theme="simple" value="Exit" method="close" id="btnClose" >
									</s:submit><input type="button"  value="Clear" onclick="clearFormDetails();"/></td>
									<td>
	<s:label name="lblSuccess" id="lblSuccess" cssClass="STATUSMSG"/>
	<s:label name="lblError" id="lblError" cssClass="STATUSMSGERR"/>
</td>
									</tr>
							
									<tr>
											<td class="tdHeadings"></td>
											<s:if test="%{messageType=='ERROR'}">
											<td class="tdMsgStyle"><s:property value="errorMessage"></s:property></td>
											</s:if>
											<s:elseif test="%{messageType=='INFO'}">
											<td class="tdMsgStyleInfo"><s:property value="errorMessage"></s:property></td>
											</s:elseif>
											<s:elseif test="%{messageType=='DONE'}">
											<td class="tdMsgStyleDone"><s:property value="errorMessage"></s:property></td>
											</s:elseif>
											<td></td>
											<td></td>
											
									</tr>
									
								</table>
						</s:form>
						</td>
					</tr>
					 <tr>
						<td>
						
						</td>
					</tr>
					<tr class="tldFromContent1">
						<td>
						<!-- <div id="pegTree" style="width:25%;float: left"> -->
						</div>
						<div id="addedPegInfo" style="display:block;width:75%">
							<!--  <table>
								<tr>
									<td>
										Quantity : <input type="text" name="itemquantity" id="itemquantity" value="" onchange="validateQuantity(this)"/>
									</td>
									<td>
									<input type="button" name="add" id="add" value="Add Items" onclick="addPegItems();"/>
									</td>
									<td>
									<input type="button" name="add" id="add" value="View Masters" onclick="viewMasterDetails();"/>
									</td>
								</tr>
							</table>-->
							<br>
							<br><br><br>
							<!-- <div style="width:50%;float: left">
					  		<table  class="tldFromContent1" id="pkgtable" align="left"  style="display:block;" border="1">
					  			<tr id="header1">
									<th></th><th><b>&nbsp;&nbsp;&nbsp;&nbsp;Description&nbsp;&nbsp;&nbsp;&nbsp;</b></th> <th><b>&nbsp;&nbsp;&nbsp;&nbsp;No of Items&nbsp;&nbsp;&nbsp;&nbsp;</b></th></tr>
					 			<tr id="linesecid"><td></td><td id="description"></td><td id="quantity"></td></tr>
					  
							</table>
							
							</div>
							<br><br><br><br><br><br>
							<div style="width:50%;float: left">

							<table  class="tldFromContent1" id="masterDetails" align="left"  style="display:block;" border="1">
					  			<tr id="header1">
									<th><b>&nbsp;&nbsp;Res Type&nbsp;&nbsp;</b></th> <th><b>&nbsp;&nbsp;Res CD&nbsp;&nbsp;</b></th><th><b>&nbsp;&nbsp;Res Name&nbsp;&nbsp;</b></th><th><b>&nbsp;&nbsp;Estimated Quantity&nbsp;&nbsp;</b></th></tr>
					 			<tr id="linesecid"><td></td><td id="resType"></td><td id="resCD"></td><td id="resName"></td><td id="estimatedQuantity"></td></tr>
					  
							</table>
							<div> -->
							<br>
							<br><br><br>
							
						</div>
						
						</td>
					</tr>
					<tr>
					<td></td>
				</tr>
				<tr><td></td></tr>
				<tr>
					 <tr class="tldFromContent1">
						<td>
						<table>
						<tr>
								<td>
									<!--<a id="showLink1" href="javascript:show_OtherMaterials()">Add Materials</a>  -->
								</td>
								
								<td>
									<!--<a id="showLink3" href="javascript:show_OtherResources()">Add Other Resources</a>  -->
								</td>
								<td>
									<!--<a id="showLink2" href="javascript:show_NPLMaterials()">ADD NPL Materials</a>  -->
								</td>
							</tr>
							<tr >
								<td colspan="3">
									<!--  <input type="button" name="new" id="new" value="Add New Estimate Entry" onclick="showAddJobInfo();"/>-->
									<table  class="tldFromContent1" id="estimateDetails"  style="display:block;" border="1"> 
							  			<tr id="header1">
											 <th><b>Resource CD&nbsp;&nbsp;</b></th><th><b>Resource Name&nbsp;&nbsp;</b></th><th><b>UOM&nbsp;&nbsp;</b></th><th><b>Unit Price&nbsp;&nbsp;</b></th><th><b>Estimated Quantity&nbsp;&nbsp;</b></th><th><b>Estimated Cost&nbsp;&nbsp;</b></th><th><b>Rebate Quantity&nbsp;&nbsp;</b></th><th><b>Rebate Cost&nbsp;&nbsp;</b></th><th><b>Reusable Quantity&nbsp;&nbsp;</b></th><th><b>Off Charge Quantity&nbsp;&nbsp;</b></th><th></th></tr>
							 			<tr id="linesecid"><td id="resCD"></td><td id="resName"></td><td id="estimatedQuantity"></td></tr>		
									</table>
								</td>
							</tr>	
							<tr>
								<td colspan="3">
								
								
									<div id="otherMatdiv" style="display:none">
										<table class="tldFromContent1" width="100%">							
											<tr>
												<td>
												<s:a id="addLink" href="javascript:add_materials('otherMat')">Add to Resource List</s:a>&nbsp;&nbsp;&nbsp;<a href="javascript:hide_OtherMaterials()">Hide List</a>
											  		<table id="otherMattable" align="center" border="1">
											  			<tr id="header2"><th></th> 
															<th>Resource Code</th><th>Resource Name</th> <th>UOM</th> <th>Unit Price</th></tr>
											 			<tr id="resourceid"><td></td><td id="resourceName"></td><td id="uom"></td><td id="unitPrice"></td><td id="estimatedCost"></td>
											  				<td id="totalcost"></td></tr>
											  
													</table>
												</td>
											</tr>
										</table>
							
									<s:a id="addLink" href="javascript:add_materials('otherMat')">Add to Resource List</s:a>&nbsp;&nbsp;&nbsp;<a href="javascript:hide_OtherMaterials()">Hide List</a>
									</div>
									<div id="nplMatdiv" style="display:none">
										<table class="tldFromContent1" width="100%">							
											<tr>
												<td>
												<s:a id="addLink" href="javascript:add_materials('NPL')">Add to Resource List</s:a>&nbsp;&nbsp;&nbsp;<a href="javascript:hide_NPLResources()">Hide List</a>
											  		<table id="NPLMaterialsTable" align="center" border="1">
											  			<tr id="header3"><th></th> 
															<th><b>Resource Code<b></th><th><b>Resource Name<b></th><th>UOM</th> <th>Unit Price</th></tr>
											 			<tr id="resourceCode1"><td></td><td id="resourceName1"></td><td id="UOM1"></td><td id="unitPrice1"></td>
											  				</tr>
											  
													</table>
												</td>
											</tr>
										</table>
							
									<s:a id="addLink" href="javascript:add_materials('NPL')">Add to Resource List</s:a>&nbsp;&nbsp;&nbsp;<a href="javascript:hide_NPLResources()">Hide List</a>
									</div>
					
								<div id="otherResourcesdiv" style="display:none">
										<table class="tldFromContent1" width="100%">							
											<tr>
												<td>
												<s:a id="addLink" href="javascript:add_materials('otherResource')">Add to Resource List</s:a>&nbsp;&nbsp;&nbsp;<a href="javascript:hide_OtherResources()">Hide List</a>
											  		<table id=otherResourceTable align="center" border="1">
											  			<tr id="header2"><th></th>
															<th><b>Resource Code<b></th><th><b>Resource Type<b></th><th><b>Resource Name<b></th><th>UOM</th> <th>Unit Price</th></tr>
											 			<tr id="resourceCode"><td></td><td id="resourceType"></td><td id="resourceName"></td><td id="UOM"></td><td id="unitPrice"></td>
											  				</tr>
											  
													</table>
												</td>
											</tr>
										</table>
							
									<s:a id="addLink" href="javascript:add_materials('otherResource')">Add to Resource List</s:a>&nbsp;&nbsp;&nbsp;<a href="javascript:hide_OtherResources()">Hide List</a>
									</div>
					
					
								</td>
								
							</tr>
							
							
						</table>
						
						</td>
					</tr>
					<tr>
						<td id="tdContent">
						
						</td>
					</tr>
					<tr>
						<td id="tdFooter">
						<table class="tblFooter" border="0" cellpadding="0"
							cellspacing="0">

							<tbody>
								<tr align="Center">
									<td id="tdFooter">
                                        <table class="tblFooter" border="1" cellpadding="0" cellspacing="0">

                                            <tbody>
                                                <tr>
                                                    <td class="footerNotice" align="center">@ Ceylon Electricity Board</td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </td>
								</tr>
							</tbody>
						</table>
						</td>
					</tr>
				</tbody>
			</table>


			</td>
		</tr>
	</tbody>
</table>


</body>

 