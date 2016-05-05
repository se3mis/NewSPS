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
<script type="text/javascript"><!--
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

function printing()
{
	
	getData();
	alert(itemCodeList.length);
	for (var j = 0; j < itemCodeList.length; j++) {
		selectedItemsList = selectedItemsList+itemCodeList[j];
		alert(selectedItemsList);
	} 
	document.PIVApplet.print(
			itemCodeList,itemDescriptionList,itemUOMList,itemUnitPriceList,itemQuantityList,itemCostList
			
			
	);  
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

function loadReviseDetails(){
	var projectNo = $("#projectNo").val();
	var estimateNo = $("#estimateNo").val();
	var costCenterNo = $("#costCenterNo").val();
	loadDefaultDropDownsValues();
   		//alert(val.value);
	if(projectNo=="" && estimateNo==""){
		
		alert("Please enter Project/Estimate no!");
		return;	
	
	}

		$.getJSON("smc/loadReviseDetails.ajax?projectNo="+projectNo+"&estimateNo="+estimateNo+"&requestCostCenter="+costCenterNo, {
		ajax_command: "searchJobs",ajax_ieCacheFix:new Date().getTime() },
		function(json){ 
			if(json.invalid){
				   // invalidStatus = "true";
					alert(json.errorMessage);
					return;
				}	
			$('#totalCost').val(json.totalCost);	
			$('#rebate').val(json.rebate);
			$('#estimateNo').val(json.estimateNo);
			$('#estimateDatePicker').val(json.estimateDatePicker);	
			$('#fundsou').val(json.fundsou);	
			$('#fundid').val(json.fundid);
			$('#projectNo').val(json.projectNo);
			$('#description').val(json.description);
			$('#reviseReason').val(json.reviseReason);
			$('#rejectReason').val(json.rejectedReason);
			$('#categoryId').val(json.categoryId);	
			$('#pivNo').val(json.pivNo);	
			$('#amount').val(json.amount);
			//$('#applicationId').val(json.stdEstimateNo);
			 $('#costCenterNo').val(json.costCenter);
	//alert(json.estimateDetails.length);			
			$('#pivDatePicker').val(json.pivDatePicker);	
			$('#status').val(json.status);
	
	
					$.each(json.estimateDetails,function(i,estimateDetail){
							
							var estimateDetails = '<tr  id="lineseciid'+estimateDetail.resourceCode+'">'+	
											//'<td id="radio'+estimateDetail.selectedPegId+'" align="middle" ><input type="radio" class="required" style="text-align: right" name="pegradio" id="'+estimateDetail.selectedPegId+'"  value="'+estimateDetail.selectedPegId+'"  /></td>'+																							
											//'<td id="resourceType'+estimateDetail.selectedPegId+estimateDetail.resourceCode+'" align="middle" >'+estimateDetail.resourceType+'</td>'+
											'<td id="resourceCode'+estimateDetail.resourceCode+'" align="left" >'+estimateDetail.resourceCode+'</td>'+
											'<td id="resourceName'+estimateDetail.resourceCode+'" align="left" >'+estimateDetail.resourceName+'</td>'+
											'<td id="uom'+estimateDetail.resourceCode+'" align="left" >'+estimateDetail.uom+'</td>'+
											'<td id="unitPrice'+estimateDetail.resourceCode+'" align="left" >'+estimateDetail.unitPrice+'</td>'+
											'<td id="estimateQuantity'+estimateDetail.resourceCode+'" align="left" >'+estimateDetail.estimateQuantity+'</td>'+
											'<td align="left" >'+estimateDetail.estimateCost+'</td>'+
				
											//'<td width=\'25px\' align=\'center\'><image onclick="deleteDetailLine(\'' + estimateDetail.isFromPegSchedule + '\'' +',\'' + estimateDetail.resourceCode + '\'' +',\'' + null+'\')" src="../../image/X.png" /></td>'+
				
											'</tr>';
										
										$('#estimateDetails').append(estimateDetails);
								
					});
	
					$.each(json.approvals,function(i,approvalHistory){
					
							var approvalTable = '<tr  id="approvalHistory'+approvalHistory.approvedId+'">'+	
							'<td id="approvedLevel'+approvalHistory.approvedId+'" align="left" >'+approvalHistory.approvedLevel+'</td>'+																							
							'<td id="approvedtype'+approvalHistory.approvedId+'" align="left" >'+approvalHistory.approvedtype+'</td>'+
							'<td id="approvedby'+approvalHistory.approvedId+'" align="left" >'+approvalHistory.approvedby+'</td>'+
							'<td id="approveddate'+approvalHistory.approvedId+'" align="left" >'+approvalHistory.approveddate+'</td>'+
							'<td id="approvedtime'+approvalHistory.approvedId+'" align="left" >'+approvalHistory.approvedtime+'</td>'+
							
							'</tr>';
						
							$('#approvalTable').append(approvalTable);
					
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
						<s:form id="frmEstimate" name="frmEstimate" action="pegSchedule!updateDetailEstimationItembyitemView" method="POST"
										validate="true" theme="simple">
						  		<table align="center" class="tldFromContent1" width="100%">
						  			<tr>
									<td width="25%">Project No</td>
									<td width="30%">
										
									
									<input type="text" name="projectNo"  id="projectNo"  size="30"/>
									</td>
									<td  width="25%">Estimate no</td>
									<td  width="30%"><input type="text" name="estimateNo"  id="estimateNo"  size="30"   /></td>
								
									<td width="25%">&nbsp;</td>
									<td width="15%">&nbsp;</td>
									</tr>
									<tr>
									
									<td  width="25%">Cost Center</td>
									<td  width="25%"><input type="text" name="costCenterNo"  id="costCenterNo" />
									</td>
									<td  width="25%">File Ref</td>
									<td  width="25%"><input type="text" name="fileRef"  id="fileRef" " readOnly="true" /></td>
									
									
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
									<td  width="25%">Status</td>
									<td  width="25%"><input type="text" name="status"  id="status" readOnly="true" size="50" style="background-color:#AFEEEE;"/></td>
									<td  width="25%">&nbsp;&nbsp;&nbsp;&nbsp;</td>
									<td  width="25%">&nbsp;&nbsp;&nbsp;&nbsp;</td>
									<td  width="25%">&nbsp;&nbsp;&nbsp;&nbsp;</td>
								
									<td  width="25%">
									
									<input type="button"  value="Search" onclick="loadReviseDetails();"/>			
									<s:submit theme="simple" value="Exit" method="close" id="btnClose" >
									</s:submit><input type="button"  value="Clear" onclick="clearFormDetails();"/></td>
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
				
				<tr><td></td></tr>
				
							<tr >
								<td colspan="3">
									<!--  <input type="button" name="new" id="new" value="Add New Estimate Entry" onclick="showAddJobInfo();"/>-->
									<table  class="tldFromContent1" id="estimateDetails"  style="display:block;" border="0"> 
							  			<tr id="header1">
											 <th><b>Resource CD&nbsp;&nbsp;</b></th><th><b>Resource Name&nbsp;&nbsp;</b></th><th><b>UOM&nbsp;&nbsp;</b></th><th><b>Unit Price&nbsp;&nbsp;</b></th><th><b>Estimated Quantity&nbsp;&nbsp;</b></th><th><b>Estimated Cost&nbsp;&nbsp;</b></th><th></th></tr>
							 			<tr id="linesecid"><td id="resCD"></td><td id="resName"></td><td id="estimatedQuantity"></td></tr>		
									</table>
								</td>
							</tr>
							 <tr>
								<td>
						
								</td>
							</tr>	
							<tr>
							<td>
							<table  class="tldFromContent1" id="approvalTable" align="left"  style="display:block;" border="0">
					  			<tr id="header1">
									<th><b>&nbsp;&nbsp;&nbsp;&nbsp;Approval Level&nbsp;&nbsp;&nbsp;&nbsp;</b></th> <th><b>&nbsp;&nbsp;&nbsp;&nbsp;Approved Type&nbsp;&nbsp;&nbsp;&nbsp;</b></th><th><b>&nbsp;&nbsp;&nbsp;&nbsp;Approved By&nbsp;&nbsp;&nbsp;&nbsp;</b></th><th><b>&nbsp;&nbsp;&nbsp;&nbsp;Approved Date&nbsp;&nbsp;&nbsp;&nbsp;</b></th><th><b>&nbsp;&nbsp;&nbsp;&nbsp;Approved Time&nbsp;&nbsp;&nbsp;&nbsp;</b></th></tr>
					 			<tr id="approvedLevel"><td></td><td id="approvedtype"></td><td id="approvedby"></td><td id="approveddate"></td><td id="approvedtime"></td></tr>
					  
							</table>
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

 