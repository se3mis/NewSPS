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
<script type="text/javascript">

var estmatedCost = '';

var allSelected = new Array();
Ext.onReady(function() {
	clearForm();
	loadStdEstimationRefs();
	//document.getElementById('otherMat').style.display = 'none'; // to do remove

  	//$("#update").attr("disabled", "disabled");
	$("#update").attr("disabled", "disabled");

});



function add_materials() 
{
	addSelectedItems();
	
}

function hide_list()
{
	document.getElementById('normsdiv').style.display = 'none';
	
}
function onDemandChange(demand){

	   if(!(demand.value > 0) || demand.value < 0){
	       
	       alert("Please enter valid value");
	       demand.value = '';
	       return;
	    } else {
	        
			var secDeposit = demand.value*1250;
			$("#secDeposit").val(secDeposit);
			$("#secDepositDisplay").val(secDeposit);
			
	    }
}
function clearFormDetails(){
	
	$('#powerTosupply').val("");
	
	$('#nameAndAddress').val("");
	$('#secDeposit').val("");	
	$('#sinNo').val("");	
	$('#totalCost').val("");	
	$('#jobDescription').val("");	
	$('#rejectedReason').val("");	
	$('#status').val("");	
	$('#demand').val("");	
	$("#update").attr("disabled", "disabled");
	$("#save").attr("disabled", "disabled");
	$("#applicationNo").val(0);
}
function onNumChange(quan,standCost,lineid){

   if(!(quan.value > 0) || quan.value < 0){
       
       alert("Please enter valid Quantity");
       quan.value = '';
       return;
    } else {
        
		estmatedCost = quan.value*standCost;
		$("#totalcost"+lineid).val(estmatedCost);
		
		$.getJSON("smc/getEstimatedCost.ajax?lineid="+lineid+"&quantity="+quan.value+"&linecost="+estmatedCost, {
			ajax_command: "getEstimatedCostDetails",ajax_ieCacheFix:new Date().getTime() },
			function(json){ 
	
		});
		
		var rows =$('#pkgtable tr');
		var rowcount = rows.length;
		var calculatedCost=0;	
		var lineCost =0;
	
	  $("#pkgtable tbody tr input[name$='totalcost']").each(function() {
		  lineCost = $(this).val();
	
		  calculatedCost=parseFloat(isNaN(calculatedCost) ? 0 : calculatedCost) + parseFloat(isNaN(lineCost) ? 0 : lineCost);
			 
			 if(!isNaN(calculatedCost))
			 {
				 $("#totalestimatedcost").text(calculatedCost);
				 $("#totalCost").val(calculatedCost);
			 }
	   });

	}
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
function clearForm(){
	$.getJSON("smc/clearFormData.ajax", {
			ajax_command: "clearFormData",ajax_ieCacheFix:new Date().getTime() },
			function(json){ 
	
	});
	$("#pkgtable tr:gt(0)").remove();
}
function loadApplicationData(val){
	
		clearForm();
		$.getJSON("smc/clearFormData.ajax", {
			ajax_command: "clearFormData",ajax_ieCacheFix:new Date().getTime() },
			function(json){ 
	
		});
				
		if(val.value=="0"){
			
			$('#powerTosupply').val("");	
			$('#nameAndAddress').val("");
			$('#secDeposit').val("");	
			$('#sinNo').val("");	
			$('#totalCost').val("");	
			$('#jobDescription').val("");	
			$('#rejectedReason').val("");	
			$('#demand').val("");	
			//$("#update").attr("disabled", "disabled");
			$("#update").attr("disabled", "disabled");
			return;	
		}else{
			document.getElementById('normsdiv').style.display = 'none';
		  	$("#update").removeAttr("disabled");
		   	//$("#update").removeAttr("disabled");
		}
	
		$.getJSON("smc/showApplicationDetails.ajax?applicationNo="+val.value, {
		ajax_command: "loadApplicationDetails",ajax_ieCacheFix:new Date().getTime() },
		function(json){ 
					
			$('#powerTosupply').val(json.powerToSupply);	
			$('#nameAndAddress').val(json.nameAndAddress);
			$('#secDeposit').val(json.secDeposit);	
			$("#secDepositDisplay").val(intToFormat(json.secDeposit));
			$('#sinNo').val(json.sinNo);	
			$('#totalCost').val(json.totalCost);
			$("#totalCostDisplay").val(intToFormat(json.totalCost));	
			$('#jobDescription').val(json.jobDescription);	
			$('#rejectedReason').val("");	
			$('#demand').val(json.demand);		
			$('#status').val(json.status);	
			$('#fundSource').val(json.foundsource);
			$('#postId').val(json.postId);		
				
			//if(json.disableSave){
			//	$("#save").attr("disabled", "disabled");
			//	 $("#update").removeAttr("disabled");
			//}else{
			//	$("#update").attr("disabled", "disabled");
			//	 $("#save").removeAttr("disabled");
			//}
			document.getElementById('addednormsdiv').style.display = '';
				var newEntry ="new";
			var pkgTable2 = '<tr  id="totalcostRow">'+		
						'<td align="middle" colspan="5" ><b>TOTAL COST<b></td><td id="totalestimatedcost"><input type="text" class="required" style="text-align: right" name="estimatedcost" id="estimatedcost"  maxlength="10" size="10" value="" readonly="true"/></td></tr>';
					
			$("#pkgtable tr:gt(0)").remove();
		
			$.each(json.addednorms,function(i,normjson){
						
				var pkgTable1 = '<tr  id="linesecid'+normjson.linesectionid+'">'+		
							'<td id="linesecid'+normjson.linesectionid+'" align="left" >'+normjson.linesectionid+'</td>'+					
							'<td id="uom'+normjson.linesectionid+'" align="left" >'+normjson.uom+'</td>'+
							'<td id="description'+normjson.linesectionid+'" align="left" >'+normjson.description+'</td>'+
							'<td align="left"><input type="text" class="required" style="text-align: right" name="quantity'+i+'" id="quantity'+normjson.linesectionid+'"  maxlength="10" size="10" value="'+normjson.quantity+'"  style="text-align: right;color: green;" onchange="onNumChange(this,'+normjson.standardcost+',\'' + normjson.linesectionid + '\')"  disabled="disabled"/></td>'+
							'<td id="s2d'+normjson.linesectionid+'" align="left" >'+normjson.standardcost+'</td>'+
							'<td align="right"><input type="text" class="required" style="text-align: right" name="totalcost" id="totalcost'+normjson.linesectionid+'"  maxlength="10" size="10" value="'+normjson.totalcost+'" readonly="true" style="text-align: right;color: green;" disabled="disabled"/></td>'+
							//'<td width=\'25px\' align=\'center\'><image onclick="saveNorm(\''+ 'update'+'\'' +',\'' + normjson.linesectionid + '\')" src="../../image/saveButton.png" /></td>'+
							//'<td width=\'25px\' align=\'center\'><image onclick="deleteNorm(\'' + normjson.linesectionid + '\')" src="../../image/X.png" /></td>'+
										//'</tr></table></td>'+
							'</tr>';
						
				$('#pkgtable').append(pkgTable1);
	
			});
			if(json.addednorms.length != 0){
				$('#pkgtable').append(pkgTable2);
			}
			$("#totalestimatedcost").text(json.totalCost);

			});

	}

function validateSelection(val){
	var isSelected = false;
	if(allSelected.length > 0){
		for(var i =0; i<allSelected.length;i++){                 
				if(val == allSelected[i]){
				             	   
					alert("Already have selected that item!!!!");
					isSelected = true;
				
				}else{                
					isSelected = false;
				}                            
		}
	}
	
	return isSelected;
}	
 function updateDetails(){

	 
	 document.frmEstimate.action="estimateBS!updateEstimation";
	 document.frmEstimate.method="POST";
	 document.frmEstimate.submit();
 } 

function saveDetails(){

	 
	 document.frmEstimate.action="estimateBS!saveEstimation";
	 document.frmEstimate.method="POST";
	 document.frmEstimate.submit();
 } 
	
function addSelectedItems(){
	
		document.getElementById('addednormsdiv').style.display = '';
		
		var checkSelected = false;
		var selectedItemsList='';
		var selected = new Array();
	    $("input:checkbox:checked").each(function() {
			
			selected.push($(this).attr('id'));
			  
	    });
		var newEntry ="new";
		for (var j = 0; j < selected.length; j++) {
			selectedItemsList = selectedItemsList+selected[j]+'|';
		} 
		
   	    if(selectedItemsList !=''){
			$.getJSON("smc/addNorms.ajax?selectedItems="+selectedItemsList+"&func=add", {
			ajax_command: "addNorms",ajax_ieCacheFix:new Date().getTime() },
			function(json){ 
				
					var pkgTable2 = '<tr  id="totalcostRow">'+		
								'<td align="middle" colspan="5" ><b>TOTAL COST<b></td><td id="totalestimatedcost"><input type="text" class="required" style="text-align: right" name="estimatedcost" id="estimatedcost"  maxlength="10" size="10" value="" readonly="true"/></td></tr>';
								
					$.each(json.addednorms,function(i,normjson){
					
						$('#totalcostRow').remove();
															
						var pkgTable1 = '<tr  id="linesecid'+normjson.linesectionid+'">'+		
									'<td id="linesecid'+normjson.linesectionid+'" align="left" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>'+					
									'<td id="uom'+normjson.linesectionid+'" align="left" >'+normjson.uom+'</td>'+
									'<td id="description'+normjson.linesectionid+'" align="left" >'+normjson.description+'</td>'+
									'<td align="right"><input type="text" class="required" style="text-align: right" name="quantity'+i+'" id="quantity'+normjson.linesectionid+'"  maxlength="10" size="10" value=""  style="text-align: right;color: green;" onchange="onNumChange(this,'+normjson.standardcost+',\'' + normjson.linesectionid + '\')"  disabled="disabled"/></td>'+
									'<td id="standardcost'+normjson.linesectionid+'" align="left" >'+normjson.standardcost+'</td>'+
									'<td align="right"><input type="text" class="required" style="text-align: right" name="totalcost" id="totalcost'+normjson.linesectionid+'"  maxlength="10" size="10" value="" readonly="true" style="text-align: right;color: green;" disabled="disabled"/></td>'+
									//'<td width=\'25px\' align=\'center\'><image onclick="saveNorm(\''+ 'save'+'\'' +',\'' + normjson.linesectionid + '\')" src="../../image/saveButton.png" /></td>'+
									'<td width=\'25px\' align=\'center\'><image onclick="deleteNorm(\'' + normjson.linesectionid + '\')" src="../../image/X.png" /></td>'+
												'</tr></table></td>'+
									'</tr>';
								
						$('#pkgtable').append(pkgTable1);
						$('#lineseciid'+normjson.linesectionid).remove();
					});
					$('#pkgtable').append(pkgTable2);
					
				}
			);
	 }else{
		alert("Please Select Items!!!...");
	 }
	
}

function show_materials() {
	var appRefNoValue=$("#applicationNo").val();	
	if(appRefNoValue==0){
		alert("Please select Application Ref Number");
		return;
	}
	document.getElementById('normsdiv').style.display = '';

	$.getJSON("smc/addNorms.ajax", {ajax_command: "loadNorms",ajax_ieCacheFix:new Date().getTime() },
		function(json){ 
		
				$("#pkgtable1 tr:gt(0)").remove();		
				$.each(json.shownorms,function(i,normjson){
									
					var pkgTable1 = '<tr  id="lineseciid'+normjson.linesectionid+'">'+
								'<td align="left"><input type="checkbox" class="required" style="text-align: right" name="chkMatCode" id="'+normjson.linesectionid+'"  value=""  /></td>'+	
								'<td id="lineseciid'+normjson.linesectionid+'" align="left" style="text-align: right">'+normjson.linesectionid+'</td>'+					
								
								'<td id="uom'+normjson.linesectionid+'" align="left" style="text-align: right">'+normjson.uom+'</td>'+
								'<td id="description'+normjson.linesectionid+'" align="left" style="text-align: right">'+normjson.description+'</td>'+
							
								'<td id="s2d'+normjson.linesectionid+'" align="left" style="text-align: right" >'+normjson.standardcost+'</td>'+
								'</tr></table></td>'+
								'</tr>';
							
					$('#pkgtable1').append(pkgTable1);
			
				});
		}
	);

}

function checkValidity(status,postdeptid){
	if(status = -1){
		alert('You have not done any status changes');
		
	}
}

function deleteNorm(lineid){
	var status= confirm("Do you really want to remove this ??");
	 if (status== true){			
			var deleteItemValue=$("#totalcost"+lineid).val();	
			
			var totalEstimatedCost=$("#totalestimatedcost").text();
		
			var newCost = 0;
			if(!document.getElementById("totalcost"+lineid).value==''){
				newCost= totalEstimatedCost - parseFloat(deleteItemValue);
			}
				
			$("#totalestimatedcost").text(newCost);
			$("#totalCost").val(newCost);
			$('#linesecid'+lineid).remove();
			var appRefNoValue=$("#applicationNo").val();	
			$.getJSON("smc/deleteLineDetails.ajax?lineid="+lineid+"&applicationRefNo="+appRefNoValue, {
					ajax_command: "deleteLineDetails",ajax_ieCacheFix:new Date().getTime() },
					function(json){ 
			
				});
			$.getJSON("smc/addNorms.ajax?selectedItems="+lineid+"&func=delete", {
			ajax_command: "addNorms",ajax_ieCacheFix:new Date().getTime() },
				function(json){ 
			
						$.each(json.addednorms,function(i,normjson){
						
							var pkgTable1 = '<tr  id="lineseciid'+normjson.linesectionid+'">'+
											'<td align="right"><input type="checkbox" class="required" style="text-align: right" name="chkMatCode" id="'+normjson.linesectionid+'"  value=""  /></td>'+	
											'<td id="lineseciid'+normjson.linesectionid+'" align="left" >'+normjson.linesectionid+'</td>'+					
											
											'<td id="uom'+normjson.linesectionid+'" align="left" >'+normjson.uom+'</td>'+
											'<td id="description'+normjson.linesectionid+'" align="left" >'+normjson.description+'</td>'+
										
											'<td id="s2d'+normjson.linesectionid+'" align="left" >'+normjson.standardcost+'</td>'+
											'</tr></table></td>'+
											'</tr>';
										
							$('#pkgtable1').append(pkgTable1);
			
						});
						
				}
			);
			var appRefNoValue=$("#applicationNo").val();	
			$.getJSON("smc/deleteLineDetails.ajax?lineid="+lineid+"&applicationRefNo="+appRefNoValue, {
					ajax_command: "deleteLineDetails",ajax_ieCacheFix:new Date().getTime() },
					function(json){ 
			
				});

	 }
	 else{
	  	return;
	 }
	  	
}


function loadStdEstimationRefs(){
	
	 $.getJSON("sps/loadStdEstimationRefsforValidation.ajax?selectedCustomer=" + 1, {ajax_command:"loadStdEstimationRefsforValidation",ajax_ieCacheFix:new Date().getTime()}, function(json) {

       if (json.jsonarrayapplicationrefs != null && json.jsonarrayapplicationrefs.length > 0) {
           for (x = 0; x < json.jsonarrayapplicationrefs.length; x++) {
            
               $("#applicationRefs1 select").append($('<option></option>').val(json.jsonarrayapplicationrefs[x].id).html(json.jsonarrayapplicationrefs[x].name));
           }
       }

	 });

}
function saveNorm(func,lineid){
	
	var estimatedCost=$("#totalcost"+lineid).val();	
	var quantity=$("#quantity"+lineid).val();	
	var appRefNoValue=$("#applicationNo").val();	
	$.getJSON("smc/updateLineDetails.ajax?lineid="+lineid+"&applicationRefNo="+appRefNoValue+"&estimatedCost="+estimatedCost+"&quantity="+quantity+"&func="+func, {
			ajax_command: "updateLineDetails",ajax_ieCacheFix:new Date().getTime() },
			function(json){ 
				if(json.success != null){
					alert(json.success);
				}
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
					<tr>
						<td>
						<s:form id="frmEstimate" name="frmEstimate" action="estimateBS!ChangePS" method="POST" validate="true" theme="simple">
						  		<table width="100%" border="0" class="tldFromContent1">
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
														</tr>
														<tr>
															<td width="5"></td>
															
															<td>
															<table width="100%" border="0">
														
														<tr height="20">
															<td>Application Ref.No</td>
															
															<td class="">
															<div id="applicationRefs1"><select name="applicationNo" id="applicationNo" onchange="loadApplicationData(this)"><option value="0">--Select Application No--</option></select></div>
															
															</td>
															<td >Power To Supply</td>
															
															<td><input type="text" name="powerTosupply" id="powerTosupply" readOnly="true" size="60" disabled="disabled"/></td>
															<td width="25%">&nbsp;</td>
															
															<td>&nbsp;</td>
														</tr>
														<tr height="20">
															<td>Sec Deposit</td>
															
															<td><input type="hidden" name="secDeposit" id="secDeposit" readOnly="true" disabled="disabled"/>
															<input type="text" name="secDepositDisplay" id="secDepositDisplay" readOnly="true" disabled="disabled"/></td>
															<td>Name and Address</td>
															
															<td><input type="text" name="nameAndAddress" id="nameAndAddress" readOnly="true" size="60" disabled="disabled"/></td>
															<td width="25%">&nbsp;</td>
															
															<td>&nbsp;</td>
														</tr>
														<tr height="20">
															<td>Fund Source</td>
															
															<td><input type="text" name="fundSource" id="fundSource" /></td>
															<td>Total Cost</td>
															
															<td><input type="hidden" name="totalCost" id="totalCost" readOnly="true" disabled="disabled"/>
															<input type="text" name="totalCostDisplay" id="totalCostDisplay" readOnly="true" />
															</td>
															<td width="25%">&nbsp;</td>
															
															<td>&nbsp;</td>
														</tr>
														<tr height="20">
															<td>Job Description</td>
															
															<td><textarea type="text" name="jobDescription" id="jobDescription" disabled="disabled"></textarea></td>
															
																<td>Reason For Change Post DeptID or Status</td>
																
																<td><textarea type="text"  name="rejectedReason" id="rejectedReason" ></textarea></td>
																<td width="25%">&nbsp;</td>
															
															<td>&nbsp;</td>
														</tr>
														<tr height="20">
															<td  width="25%">Existing Status</td>
															<td  width="25%"><input type="text" name="status"  id="status" readOnly="true" size="50" style="background-color:#AFEEEE;"/></td>
															
																<td>Demand</td>
																
																<td><input type="text" name="demand" id="demand" onchange="onDemandChange(this)" disabled="disabled"/></td>
																<td width="25%">&nbsp;</td>
															
															<td>&nbsp;</td>
														</tr>
														<tr>
														<td  width="25%">Post Dept ID</td>
															<td  width="25%"><s:select name="postId" id="postId" list="postIdList"></s:select></td>
															
														<td  width="25%">Status To Be Change</td>
															<td  width="25%"><s:select name="chgStatus" id="chgStatus" list="#{'-1':'--Select--','2':'Change The Estimate Into Modify Status','5':'Change The Estimate Into Rejected Status','8':'Change The Estimate Into to be validate by Electrical Superintendent','10':'Change The Estimate Into to be validate by Electrical Engineer','20':'Change The Estimate Into to be validate by Planning Engineer','30':'Change The Estimate Into to be validate by Chief Engineer','35':'Change The Estimate Into to be validate by Planning Chief Engineer','40':'Change The Estimate Into to be Approved by DGM','50':'Change The Estimate into Approved Estimate','55':'Change The Estimate Into To be confirmed by ACCT_REV(PIV)','56':'Change The of Estimate Into To be confirmed by DEO(PIV)','60':'Change The Status of Estimate as PIV II confirmed(Estimate will show in send construction Maintenance list )' ,'70':'Change the Estimate into Show in Con/Mnt section'}"></s:select></td>
															
															
														
													
														</tr>
														
														<tr height="20">
															<td>&nbsp;</td>
															
																<td colspan="5"><s:submit theme="simple" value="Change Status And Post DeptID" method="changePostStatus" id="cancel" disabled="false" ></s:submit>
																<s:submit theme="simple" value="View Detail Report" method="Print" id="btnprint" ></s:submit>
																<s:submit theme="simple" value="View Award Letter" method="PrintAward" id="btnprint2" ></s:submit>
																
																<!--<s:submit theme="simple" value="Reject" method="reject" id="Reject" disabled="true"></s:submit>
																<s:submit theme="simple" value="Print Detail Report" method="Print" id="btnprint" ></s:submit>
																<s:submit theme="simple" value="Print Award Letter" method="PrintAward" id="btnprint2" ></s:submit>
																<s:submit theme="simple" value="Print Dispatch of PIV" method="PrintDispatchOFPIV" id="btnprint3" ></s:submit>
																<input type="button"  value="Clear" onclick="clearFormDetails();" disabled="true"/>
											
																<s:submit theme="simple" value="Print Dispatch of PIV NEW" method="PrintDispatchOFPIVNew" id="btnprint4" ></s:submit>-->
																<s:submit theme="simple" value="Exit" method="close" id="btnClose" ></s:submit>
																					
																</td>
														</tr>
														
														<tr height="20">
															<td colspan="4"><s:label name="lblSuccess" id="lblSuccess" cssClass="STATUSMSG"></s:label>
														<s:label name="lblError" id="lblError" cssClass="STATUSMSGERR"></s:label></td>
															<td>&nbsp;</td>
															<td>&nbsp;</td>
															
																<td>&nbsp;</td>
																<td>&nbsp;</td>
																<td>&nbsp;</td>
														</tr>
														<tr>
															<td>
															<!--  <a id="showLink" href="javascript:show_materials()">Show Norms</a> -->
															</td>
														</tr>
													</table>
													</s:form>
															</td>
														</tr>
													</table>
						
						</td>
					</tr>
					 <tr>
						<td>
						<div id="addednormsdiv" style="display:none">
					  		<!-- <table  class="tldFromContent1" id="pkgtable" align="center"  style="display:block">
					  			<tr id="header1">
									<th></th> <th>UOM</th> <th>Description</th> <th>Quantity</th> <th>Standard Cost</th> <th>Cost</th></tr>
					 			<tr id="linesecid"><td></td><td id="uom"></td><td id="description"></td><td id="quantity"></td><td id="standardcost"></td>
					  				<td id="totalcost"></td></tr>
					  
							</table>-->
							
							<table class="tldFromContent1" id="pkgtable" align="center" style="display:block">
					  			<tr id="header1">
									<th><b>Line ID</b></th> <th><b>UOM</b>&nbsp;&nbsp;&nbsp;&nbsp;</th> <th>&nbsp;&nbsp;&nbsp;&nbsp;<b>Description</b>&nbsp;&nbsp;&nbsp;&nbsp;</th> <th>&nbsp;&nbsp;&nbsp;&nbsp;<b>Quantity/Length</b>&nbsp;&nbsp;&nbsp;&nbsp;</th> <th>&nbsp;&nbsp;&nbsp;&nbsp;<b>Standard Cost</b>&nbsp;&nbsp;&nbsp;&nbsp;</th> <th>&nbsp;&nbsp;&nbsp;&nbsp;<b>Cost</b>&nbsp;&nbsp;&nbsp;&nbsp;</th></tr>
					 			
					  
					  <!--  <s:iterator value="selectedNormsList" id="norm">--> 
					   <%   
					String lineId = "linesecid"+"33ME";
    				String uomId =  "uom"+"33ME";
    				String desId = "description"+"33ME";
    				String quanpId = "quantity"+"33ME";
					   String stcostpId = "standardcost"+"33ME";
					   String tocostpId = "totalcost"+"33ME";
					   %>
					
                              <tr id="linesecid">
								  <td id="linesecid">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
								  <td id="uom"></td>
								  <td id="description"></td>
								  <td id="quantity"></td>
								  <td id="standardcost"></td>
								  <td id="totalcost"></td>
							  </tr>
                       <!--  </s:iterator>--> 
						 
							</table>
						</div>
						</td>
					</tr>
					<!-- <tr>
						<td>
						<div id="guitarTree">
						</div>
						</td>
					</tr> -->
					<tr>
					<td></td>
				</tr>
				<tr><td></td></tr>
				<tr>
					 <tr>
						<td>
						<div id="normsdiv" style="display:none">
						<table class="tldFromContent1" width="100%">							
							<tr>
								<td>
								<s:a id="addLink" href="javascript:add_materials(true)">Add to Resource List</s:a>&nbsp;&nbsp;&nbsp;<a href="javascript:hide_list()">Hide List</a>
					  		<table id="pkgtable1" align="center" border="1">
					  			<tr id="header2"><th></th> 
									<th>
								Line Id
								</th> <th>UOM</th> <th>Description</th><th>Standard Cost</th></tr>
					 			<tr id="linesecid"><td></td><td id="uom"></td><td id="description"></td><td id="quantity"></td><td id="standardcost"></td>
					  				<td id="totalcost"></td></tr>
					  
							</table>
								</td>
							</tr>
						</table>
						
							<s:a id="addLink" href="javascript:add_materials(true)">Add to Resource List</s:a>&nbsp;&nbsp;&nbsp;<a href="javascript:hide_list()">Hide List</a>
					</div>
						</td>
					</tr>
					<tr>
						<td id="tdContent">
							<!--<s:form id="frmEstimate" name="frmEstimate" action="test1" method="POST"
										validate="true" theme="simple">
										<s:hidden id="isMatClicked" name="isMatClicked"></s:hidden>
										
										
    
						<table class="tblContent" border="0" cellpadding="0"
							cellspacing="0">
							<thead>
								<tr>
									<th></th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td align="center" >
										<s:url id="display_url" value="TestAction!displayNorms" />
									</td>
									<td align="center" >
									
										
									</td>
								</tr>
								<tr>
									<td align="center" >
										<sx:div  id="otherMat" href="%{#display_url}" listenTopics="display_norms" formId="frmEstimate"  /> 
									</td>
								</tr>
								<tr>
									<td>
								<s:a id="showLink" href="javascript:show_materials()">Show Norms</s:a> 
									</td>
								</tr>
								<tr>
									<td align="right">
										
									</td>
								</tr>
								
							</tbody>
						</table> 
					
										</s:form>-->
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

