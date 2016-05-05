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

var allSelected = new Array();
Ext.onReady(function() {
clearPeggingScheduleForm();
 $('#estimateDatePicker').datePicker({clickInput:true});
 $('#pivDatePicker').datePicker({clickInput:true});
	
	var contextPath='sps/loadPegInfo.ajax';

	var pegTreeLoader = new Ext.tree.TreeLoader({
		url: contextPath + '?ajax_command=loadPegInfo' // equivalent to dataUrl
	});
	
	var pegTreePanel = new Ext.tree.TreePanel({
		title: 'Peg Info',
		collapsible: false,
		animCollapse: false,
		border: true,
		id: 'pegTree',
		el: 'pegTree', // html element id
		autoScroll: true,
		animate: false,
		enableDD: true,
		containerScroll: true,
		height: 400,
		width: 300,
		loader: pegTreeLoader,
		rootVisible: false,
		listeners: {
            click :function (node,checked) {
			
                if(node){
                	nodeId = node.id;
    				nodeText = node.text;
					selectedpegItem = node.id;
                    if(node.hasChildNodes()){
                        node.expand();
						
                        node.eachChild(function(n) {
                           // n.getUI().toggleCheck(checked);
							//alert(node.id);
							
                        });
                    }
                }
            }
        }// set for not displaying 'Global root';
	});

	// set the root node
	var pegTreeRoot = new Ext.tree.AsyncTreeNode({
		text: 'Global root',
		draggable: false,
		//id: '0' // id of the root node
		 id:'source'
	});

	
	var hiddenPkgs = [];
    var markCount = [];

   
	// render the tree
	pegTreePanel.setRootNode(pegTreeRoot);
	pegTreePanel.render();
	loadStdEstimationRefs();
});
function clearPeggingScheduleForm(){
	$.getJSON("smc/clearPeggingScheduleForm.ajax", {
			ajax_command: "clearPeggingScheduleForm",ajax_ieCacheFix:new Date().getTime() },
			function(json){ 
	
	});
	$("#estimateDetails tr:gt(0)").remove();
}

function loadJobTypes(val){
	//alert(userId.value);
	document.getElementById("description").value=val.value;
}
function generateWorkEstNo(){

	var applicationId=$("#applicationId").val();	
	if(applicationId==-1){
		alert("Please select Reference Number");
		return;
	}
	var fundsou=$("#fundsou").val();
	 
	 if(fundsou==-1)
	 {
		 alert('Please enter Found Source!!');
		 //document.getElementById("fundsou").focus();
		 return false;
	 }
	 $.getJSON("smc/generateWorkEstNo.ajax?applicationId="+applicationId+"&fundsou="+fundsou, {
			ajax_command: "generateWorkEstNo",ajax_ieCacheFix:new Date().getTime() },
			function(json){ 
				
				 $('#estimateNo').val(json.workEstmateNo);
	});
	
}
/**function show_OtherMaterials() {

    var checkSelected = false;
	var selectedItemsList='';
	var selected = new Array();
    $("input:checkbox:checked").each(function() {
		
		selected.push($(this).attr('id'));
		$(this).attr('checked', false);
    });
	//document.getElementById('addedPegInfo').style.display = '';
	for (var j = 0; j < selected.length; j++) {
		selectedItemsList = selectedItemsList+selected[j]+'|';


		//$.getJSON("smc/loadAdditionalResourceDetails.ajax?selectedItems="+escape(selectedItemsList)+"&func=add"+"&resourceType="+escape(resourType), {
	}

	var wareHouseId=escape($("#wareHouseId").val());
	var applicationId=$("#applicationId").val();	
	
	//if(applicationId==-1){
	//	alert("Please select Reference Number");
	//	return;
	//}
	document.getElementById('otherMatdiv').style.display = '';
	document.getElementById('otherResourcesdiv').style.display = 'none';
	document.getElementById('nplMatdiv').style.display = 'none';

	$.getJSON("smc/addOtherMaterials.ajax?selectedItems="+escape(selectedItemsList)+"&warehouseId="+wareHouseId, {ajax_command: "loadAvailableOtherMaterials",ajax_ieCacheFix:new Date().getTime() },
		function(json){ 
		
				$("#otherMattable tr:gt(0)").remove();
				if(json.otherMaterials.length == 0){
					alert("No master data found for this cost center!!!!" );
				}				
				$.each(json.otherMaterials,function(i,otherMaterialsjson){
									
					var otherMattable = '<tr  id="otherMaterials'+otherMaterialsjson.resourceCode+'">'+
								'<td align="right"><input type="checkbox" class="required" style="text-align: left" name="chkMatCode" id="'+otherMaterialsjson.resourceCode+'"  value=""  /></td>'+	
								'<td id="resourceCode'+otherMaterialsjson.resourceCode+'" align="left" style="text-align: left">'+otherMaterialsjson.resourceCode+'</td>'+					
						
								'<td id="description'+otherMaterialsjson.resourceCode+'" align="left" style="text-align: left">'+otherMaterialsjson.resourceName+'</td>'+
								'<td id="uom'+otherMaterialsjson.resourceCode+'" align="left" style="text-align: left">'+otherMaterialsjson.uom+'</td>'+
								'<td id="s2d'+otherMaterialsjson.resourceCode+'" align="left" style="text-align: left" >'+otherMaterialsjson.unitPrice+'</td>'+
								'</tr></table></td>'+
								'</tr>';
							
					$('#otherMattable').append(otherMattable);
			
				});
		}
	);

}*/

	function show_OtherMaterials() {

		/**var checkSelected = false;
			var selectedItemsList='';
			var selected = new Array();
		    $("input:checkbox:checked").each(function() {
				
				selected.push($(this).attr('id'));
				$(this).attr('checked', false);
		    });
			//document.getElementById('addedPegInfo').style.display = '';
			for (var j = 0; j < selected.length; j++) {
				selectedItemsList = selectedItemsList+selected[j]+'|';


				$.getJSON("smc/loadAdditionalResourceDetails.ajax?selectedItems="+escape(selectedItemsList)+"&func=add"+"&resourceType="+escape(resourType), {
			} */



			
			var wareHouseId=escape($("#wareHouseId").val());
			var applicationId=$("#applicationId").val();	
			
			//if(applicationId==-1){
			//	alert("Please select Reference Number");
			//	return;
			//}
			document.getElementById('otherMatdiv').style.display = '';
			document.getElementById('otherResourcesdiv').style.display = 'none';
			document.getElementById('nplMatdiv').style.display = 'none';

			$.getJSON("smc/addOtherMaterials.ajax?warehouseId="+wareHouseId, {ajax_command: "loadAvailableOtherMaterials",ajax_ieCacheFix:new Date().getTime() },
				function(json){ 
				
						$("#otherMattable tr:gt(0)").remove();
						if(json.otherMaterials.length == 0){
							alert("No master data found for this cost center!!!!" );
						}				
						$.each(json.otherMaterials,function(i,otherMaterialsjson){
											
							var otherMattable = '<tr  id="otherMaterials'+otherMaterialsjson.resourceCode+'">'+
										'<td align="right"><input type="checkbox" class="required" style="text-align: left" name="chkMatCode" id="'+otherMaterialsjson.resourceCode+'"  value=""  /></td>'+	
										'<td id="resourceCode'+otherMaterialsjson.resourceCode+'" align="left" style="text-align: left">'+otherMaterialsjson.resourceCode+'</td>'+					
								
										'<td id="description'+otherMaterialsjson.resourceCode+'" align="left" style="text-align: left">'+otherMaterialsjson.resourceName+'</td>'+
										'<td id="uom'+otherMaterialsjson.resourceCode+'" align="left" style="text-align: left">'+otherMaterialsjson.uom+'</td>'+
										'<td id="s2d'+otherMaterialsjson.resourceCode+'" align="left" style="text-align: left" >'+otherMaterialsjson.unitPrice+'</td>'+
										'</tr></table></td>'+
										'</tr>';
									
							$('#otherMattable').append(otherMattable);
					
						});
				}
			);
 
		}
function show_OtherResources() {

	//var wareHouseId=escape($("#wareHouseId").val());
	var applicationId=$("#applicationId").val();	
	//alert(applicationId);
	//if(applicationId==-1){
	//	alert("Please select Reference Number");
	//	return;
	//}
	document.getElementById('otherResourcesdiv').style.display = '';
	document.getElementById('otherMatdiv').style.display = 'none';
	document.getElementById('nplMatdiv').style.display = 'none';
	
	$.getJSON("smc/showOtherResources.ajax", {ajax_command: "showOtherResources",ajax_ieCacheFix:new Date().getTime() },
		function(json){ 
		
				$("#otherResourceTable tr:gt(0)").remove();	
				if(json.otherResources.length == 0){
					alert("No master data found for this cost center!!!!" );
				}				
				$.each(json.otherResources,function(i,otherResourcejson){
									
					var otherResourceTable = '<tr  id="otherResource'+otherResourcejson.resourceCode+'">'+
								'<td align="right"><input type="checkbox" class="required" style="text-align: left" name="chkMatCode" id="'+otherResourcejson.resourceCode+'"  value=""  /></td>'+	
								'<td id="resourceCode'+otherResourcejson.resourceCode+'" align="left" style="text-align: left">'+otherResourcejson.resourceCode+'</td>'+
								'<td id="resourceType'+otherResourcejson.resourceCode+'" align="left" style="text-align: left">'+otherResourcejson.resourceType+'</td>'+					
								'<td id="resourceName'+otherResourcejson.resourceCode+'" align="left" style="text-align: left">'+otherResourcejson.resourceName+'</td>'+
								'<td id="uom'+otherResourcejson.resourceCode+'" align="left" style="text-align: left">'+otherResourcejson.uom+'</td>'+					
								'<td id="unitPrice'+otherResourcejson.resourceCode+'" align="left" style="text-align: left">'+otherResourcejson.unitPrice+'</td>'+
								//'<td align="right"><input type="text" class="required" style="text-align: left" name="estimateCost" id="estimateCost"  value=""  /></td>'+	
								//'<td id="resourceCode1'+otherResourcejson.resourceCode+'" align="middle" style="text-align: left">'+otherResourcejson.resourceCat+'</td>'+
								
								'</tr></table></td>'+
								'</tr>';
							
					$('#otherResourceTable').append(otherResourceTable);
			
				});
		}
	);

}

function onRebateChange(quan,standCost,resourceCode){
	//alert("hi"+quan.value);
		  // if(!(quan.value > 0) || quan.value <= 0){
		     if(!(quan.value >= 0)){
		       alert("Please enter valid Quantity");
		       quan.value = '';
		       return;
		    } else {
		        
				estmatedCost = quan.value*standCost;
				//$("#totalcost"+lineid).val(estmatedCost);
				$("#rebateCost"+resourceCode).val(estmatedCost);
				
				$.getJSON("smc/getEstimatedUpdateDetails.ajax?lineid="+resourceCode+"&rebquantity="+quan.value+"&rebatecost="+estmatedCost+"&unitcost="+standCost, {
					ajax_command: "getEstimatedUpdateDetailsForRebate",ajax_ieCacheFix:new Date().getTime() },
					function(json){ 
						
				});
				
				
				var rows =$('#estimateDetails tr');
				var rowcount = rows.length;
				var calculatedCost=0;	
				var lineCost =0;
			
			  $("#estimateDetails tbody tr input[name$='rebateCost']").each(function() {
				  lineCost = $(this).val();
			
				  calculatedCost=parseFloat(isNaN(calculatedCost) ? 0 : calculatedCost) + parseFloat(isNaN(lineCost) ? 0 : lineCost);
					 
					 if(!isNaN(calculatedCost))
					 {
						 $("#rebateTotalCost").text(calculatedCost);
						 $("#rebateTotalCost").val(calculatedCost);
						 $("#rebateTotalCostDisplay").val(intToFormat(calculatedCost));	
					 }
			   });

			}
}

function onReusableChange(quan,standCost,resourceCode){
	//alert("hi"+quan.value);
		   if(!(quan.value > 0) || quan.value <= 0){
		       
		       alert("Please enter valid Quantity");
		       quan.value = '';
		       return;
		    } else {
		        
				//estmatedCost = quan.value*standCost;
				//$("#totalcost"+lineid).val(estmatedCost);
				//$("#rebateCost"+resourceCode).val(estmatedCost);
				
				$.getJSON("smc/getEstimatedUpdateDetails.ajax?lineid="+resourceCode+"&reuquantity="+quan.value, {
					ajax_command: "getEstimatedUpdateDetailsForReusable",ajax_ieCacheFix:new Date().getTime() },
					function(json){ 
						
						
						
				});
				
		

			}
}

function onOffchargeChange(quan,standCost,resourceCode){
	//alert("hi"+quan.value);
		   if(!(quan.value > 0) || quan.value <= 0){
		       
		       alert("Please enter valid Quantity");
		       quan.value = '';
		       return;
		    } else {
		        
				//estmatedCost = quan.value*standCost;
				//$("#totalcost"+lineid).val(estmatedCost);
				//$("#rebateCost"+resourceCode).val(estmatedCost);
				
				$.getJSON("smc/getEstimatedUpdateDetails.ajax?lineid="+resourceCode+"&offquantity="+quan.value, {
					ajax_command: "getEstimatedUpdateDetailsForOffCharge",ajax_ieCacheFix:new Date().getTime() },
					function(json){ 
						
						
						
				});
		

			}
}




function show_NPLMaterials() {

	//var wareHouseId=escape($("#wareHouseId").val());
	var applicationId=$("#applicationId").val();	
	//alert(applicationId);
	//if(applicationId==-1){
	//	alert("Please select Reference Number");
	//	return;
	//}
	//alert("hi");
	document.getElementById('nplMatdiv').style.display = '';
	document.getElementById('otherResourcesdiv').style.display = 'none';
	document.getElementById('otherMatdiv').style.display = 'none';

	
	$.getJSON("smc/showNPLMaterials.ajax", {ajax_command: "showNPLMaterials",ajax_ieCacheFix:new Date().getTime() },
		function(json){ 
		
				$("#NPLMaterialsTable tr:gt(0)").remove();
				if(json.otherNPLMaterials.length == 0){
					alert("No master data found for this cost center!!!!" );
				}		
				
				$.each(json.otherNPLMaterials,function(i,otherNPLjson){
						//alert(otherNPLjson.unitPrice);			
					var NPLMaterialsTable = '<tr  id="nplMaterials'+otherNPLjson.resourceCode+'">'+
						'<td align="right"><input type="checkbox" class="required" style="text-align: left" name="chkMatCode" id="'+otherNPLjson.resourceCode+'"  value=""  /></td>'+	
					'<td id="resourceCode'+otherNPLjson.resourceCode+'" align="left" style="text-align: left">'+otherNPLjson.resourceCode+'</td>'+					
			
					'<td id="description'+otherNPLjson.resourceCode+'" align="left" style="text-align: left">'+otherNPLjson.resourceName+'</td>'+
					'<td id="uom'+otherNPLjson.resourceCode+'" align="left" style="text-align: left">'+otherNPLjson.uom+'</td>'+
					'<td id="s2d'+otherNPLjson.resourceCode+'" align="left" style="text-align: left" >'+otherNPLjson.unitPrice+'</td>'+
					'</tr></table></td>'+
					'</tr>';
							//alert(NPLMaterialsTable);
					$('#NPLMaterialsTable').append(NPLMaterialsTable);
			
				});
		}
	);

}
function add_materials(resourType){

	addSelectedItems(resourType);


}

function hide_OtherMaterials()
{
	document.getElementById('otherMatdiv').style.display = 'none';
	
}
function hide_OtherResources()
{
	document.getElementById('otherResourcesdiv').style.display = 'none';
	
}
function hide_NPLResources()
{
	document.getElementById('nplMatdiv').style.display = 'none';
	
}
function onQuantityChange(quan,standCost,resourceCode){
	//alert("hi");
		var quantity = $("#estimateQuantity1"+resourceCode).val();
		var unitPrice = $("#unitPrice1"+resourceCode).val();
		
		   if(!(quantity > 0) || quantity < 0){
		      //if(!( quantity < 0)){
		       alert("Please enter valid Quantity");
		       quantity.value = '';
		       return;
		    } else {
		        
				estmatedCost = quantity*unitPrice;
				//$("#totalcost"+lineid).val(estmatedCost);
				$("#estimateCost"+resourceCode).val(estmatedCost);
				
				$.getJSON("smc/getEstimatedUpdateDetails.ajax?lineid="+resourceCode+"&quantity="+quan.value+"&linecost="+estmatedCost+"&unitcost="+unitPrice, {
					ajax_command: "getEstimatedUpdateDetailsForPeg",ajax_ieCacheFix:new Date().getTime() },
					function(json){ 
						var pkgTable1 = '<tr  id="pegid'+json.selectedPegId+'">'+	
						'<td id="radio'+json.selectedPegId+'" align="left" ><input type="radio" class="required" style="text-align: right" name="pegradio" id="'+json.selectedPegId+'"  value="'+json.selectedPegId+'"  onchange="onNumChangePegId(this)"  /></td>'+																							
						'<td id="description'+json.selectedPegId+'" align="left" >'+json.description+'</td>'+
						'<td id="quantity'+json.selectedPegId+'" align="left" ><input type="text" class="required" style="text-align: right" name="quantity" id="'+json.selectedPegId+'"  value="'+json.quantity+'"   /></td>'+
						//'<td align="right"><input type="text" class="required" style="text-align: right" name="'+json.quantity+'" id="'+json.quantity+'"  maxlength="10" size="10" value="" onchange="onNumChange1(this)" /></td>'+
						//'<td width=\'25px\' align=\'center\'><image onclick="deletePegDetailLine(\'' + json.selectedPegId + '\')" src="../../image/X.png" /></td>'+
						'</tr>';
					
						$('#pkgtable').append(pkgTable1);
				});
				
				var rows =$('#estimateDetails tr');
				var rowcount = rows.length;
				var calculatedCost=0;	
				var lineCost =0;
			
			  $("#estimateDetails tbody tr input[name$='estimateCost']").each(function() {
				  lineCost = $(this).val();
			
				  calculatedCost=parseFloat(isNaN(calculatedCost) ? 0 : calculatedCost) + parseFloat(isNaN(lineCost) ? 0 : lineCost);
					 
					 if(!isNaN(calculatedCost))
					 {
						 $("#totalCost").text(calculatedCost);
						 $("#totalCost").val(calculatedCost);
						 $("#totalCostDisplay").val(intToFormat(calculatedCost));	
					 }
			   });

			}
		}


function addSelectedItems(resourType){

	var checkSelected = false;
	var selectedItemsList='';
	var selected = new Array();
    $("input:checkbox:checked").each(function() {
		
		selected.push($(this).attr('id'));
		$(this).attr('checked', false);
    });
	//document.getElementById('addedPegInfo').style.display = '';
	for (var j = 0; j < selected.length; j++) {
		selectedItemsList = selectedItemsList+selected[j]+'|';
	} 
	//alert(escape(resourType))
	    if(selectedItemsList !=''){
		$.getJSON("smc/loadAdditionalResourceDetails.ajax?selectedItems="+escape(selectedItemsList)+"&func=add"+"&resourceType="+escape(resourType), {
		ajax_command: "loadAdditionalResourceDetails",ajax_ieCacheFix:new Date().getTime() },
		function(json){ 
			
				if(json.invalid){	
				    invalidStatus = "true";
					alert(json.errorMessage);
					return;
				}
				$.each(json.addedMaterials,function(i,estimateDetails){
					
					//$('#totalcostRow').remove();
								//alert(json.addedMaterials.length);						
					var estimateDetails1 = '<tr  id="lineseciid'+estimateDetails.selectedPegId+'">'+	
					//'<td id="radio'+estimateDetails.selectedPegId+'" align="middle" ><input type="radio" class="required" style="text-align: right" name="pegradio" id="'+estimateDetails.selectedPegId+'"  value="'+estimateDetails.selectedPegId+'"  /></td>'+																							
					//'<td id="resourceType'+estimateDetails.selectedPegId+'" align="middle" >'+estimateDetails.resourceType+'</td>'+
					'<td id="resourceCode'+estimateDetails.selectedPegId+'" align="left" >'+estimateDetails.resourceCode+'</td>'+
					'<td id="resourceName'+estimateDetails.selectedPegId+'" align="left" >'+estimateDetails.resourceName+'</td>'+
					'<td id="uom'+estimateDetails.selectedPegId+'" align="left" >'+estimateDetails.uom+'</td>';
					//'<td id="unitPrice'+estimateDetails.selectedPegId+'" align="middle" >'+estimateDetails.unitPrice+'</td>';
					
					var estimateDetailsOtherResource ='<td id="unitPrice'+estimateDetails.selectedPegId+'" align="left" ><input type="text" class="required" style="text-align: right;color: green;" name="'+estimateDetails.unitPrice+'" id="'+estimateDetails.unitPrice+'"  maxlength="10" size="10" value="'+estimateDetails.unitPrice+'" onchange="onNumChange(this,'+estimateDetails.unitPrice+',\'' + estimateDetails.selectedPegId + '\')"/></td>'+
					'<td id="estimateQuantity'+estimateDetails.selectedPegId+'" align="left" ><input type="text" class="required" style="text-align: right;color: green;" name="'+estimateDetails.estimateQuantity+'" id="'+estimateDetails.estimateQuantity+'"  maxlength="10" size="10" value="1"  onchange="onNumChange(this,'+estimateDetails.unitPrice+',\'' + estimateDetails.selectedPegId + '\')" /></td>'+
						'<td align="left" ><input type="text" class="required" style="text-align: right;color: green;" name="estimateCost"  readOnly="true" id="estimateCost'+estimateDetails.selectedPegId+'"  maxlength="20" size="20" value="" onchange="onNumChange(this,'+estimateDetails.unitPrice+',\'' + estimateDetails.selectedPegId + '\')" /></td>'+
						'<td width=\'25px\' align=\'center\'><image onclick="deleteDetailLine(\'' + estimateDetails.isFromPegSchedule + '\'' +',\'' + estimateDetails.selectedPegId + '\'' +',\'' + resourType+'\')" src="../../image/X.png" /></td>'+
					
						'</tr>';
				    var estimateDetailsNPL ='<td id="unitPrice'+estimateDetails.selectedPegId+'" align="left" ><input type="text" class="required" style="text-align: right;color: green;" name="'+estimateDetails.unitPrice+'" id="unitPrice1'+estimateDetails.selectedPegId+'"  maxlength="20" size="20" value="'+estimateDetails.unitPrice+'" onchange="onQuantityChange1(this,'+estimateDetails.unitPrice+',\'' + estimateDetails.selectedPegId + '\')"/></td>'+
						'<td id="estimateQuantity'+estimateDetails.selectedPegId+'" align="left" ><input type="text" class="required" style="text-align: right;color: green;" name="'+estimateDetails.estimateQuantity+'" id="estimateQuantity1'+estimateDetails.selectedPegId+'"   maxlength="20" size="20" value="1"  onchange="onQuantityChange(this,'+estimateDetails.unitPrice+',\'' + estimateDetails.selectedPegId + '\')" /></td>'+
							'<td align="left" ><input type="text" class="required" style="text-align: right;color: green;" name="estimateCost" readOnly="true"  id="estimateCost'+estimateDetails.selectedPegId+'"  maxlength="20" size="20" value="" onchange="onNumChange(this,'+estimateDetails.unitPrice+',\'' + estimateDetails.selectedPegId + '\')" /></td>'+
							'<td width=\'25px\' align=\'center\'><image onclick="deleteDetailLine(\'' + estimateDetails.isFromPegSchedule + '\'' +',\'' + estimateDetails.selectedPegId + '\'' +',\'' + resourType+'\')" src="../../image/X.png" /></td>'+
						
							'</tr>';
					//var estimateDetailsOther ='<td id="unitPrice'+estimateDetails.selectedPegId+'" align="left" >'+estimateDetails.unitPrice+'</td>'+
					//'<td id="estimateQuantity'+estimateDetails.selectedPegId+'" align="left" ><input type="text" class="required" style="text-align: right;color: green;" name="'+estimateDetails.estimateQuantity+'" id="'+estimateDetails.estimateQuantity+'"  maxlength="10" size="10" value="" onchange="onNumChange(this,'+estimateDetails.unitPrice+',\'' + estimateDetails.selectedPegId + '\')" /></td>'+
						//'<td align="left" ><input type="text" class="required" style="text-align: right;color: green;" readOnly="true" name="estimateCost" readOnly="true" id="estimateCost'+estimateDetails.selectedPegId+'"  maxlength="20" size="20" value=""  onchange="onNumChange(this,'+estimateDetails.unitPrice+',\'' + estimateDetails.selectedPegId + '\')"/></td>'+
						//'<td width=\'25px\' align=\'center\'><image onclick="deleteDetailLine(\'' + estimateDetails.isFromPegSchedule + '\'' +',\'' + estimateDetails.selectedPegId + '\'' +',\'' + resourType+'\')" src="../../image/X.png" /></td>'+
					
						//'</tr>';
						
					var estimateDetailsOther ='<td id="unitPrice'+estimateDetails.selectedPegId+'" align="left" >'+estimateDetails.unitPrice+'</td>'+
					'<td id="estimateQuantity'+estimateDetails.selectedPegId+'" align="left" ><input type="text" class="required" style="text-align: right;color: green;" name="'+estimateDetails.estimateQuantity+'" id="'+estimateDetails.estimateQuantity+'"  maxlength="20" size="20" value="" onchange="onNumChange(this,'+estimateDetails.unitPrice+',\'' + estimateDetails.selectedPegId + '\')" /></td>'+
						'<td align="left" ><input type="text" class="required" style="text-align: right;color: green;" readOnly="true" name="estimateCost"  id="estimateCost'+estimateDetails.selectedPegId+'"  maxlength="20" size="20" value=""  readOnly="true" onchange="onNumChange(this,'+estimateDetails.unitPrice+',\'' + estimateDetails.selectedPegId + '\')"/></td>'+
						'<td id="rebateQuantity'+estimateDetails.selectedPegId+'" align="left" ><input type="text" class="required" style="text-align: right;color: green;" name="'+estimateDetails.rebateQuantity+'" id="'+estimateDetails.rebateQuantity+'"  maxlength="20" size="20" value="" onchange="onRebateChange(this,'+estimateDetails.unitPrice+',\'' + estimateDetails.selectedPegId + '\')" /></td>'+
						'<td align="left" ><input type="text" class="required" style="text-align: right;color: green;" readOnly="true" name="rebateCost"  id="rebateCost'+estimateDetails.selectedPegId+'"  maxlength="20" size="20" value=""  readOnly="true" onchange="onRebateChange(this,'+estimateDetails.unitPrice+',\'' + estimateDetails.selectedPegId + '\')"/></td>'+
						'<td id="reusableQuantity'+estimateDetails.selectedPegId+'" align="left" ><input type="text" class="required" style="text-align: right;color: green;" name="'+estimateDetails.reusableQuantity+'" id="'+estimateDetails.reusableQuantity+'"  maxlength="20" size="20" value=""  onchange="onReusableChange(this,'+estimateDetails.unitPrice+',\'' + estimateDetails.selectedPegId + '\')"/></td>'+
						'<td id="offChargeQuantity'+estimateDetails.selectedPegId+'" align="left" ><input type="text" class="required" style="text-align: right;color: green;" name="'+estimateDetails.offChargeQuantity+'" id="'+estimateDetails.offChargeQuantity+'"  maxlength="20" size="20" value="" onchange="onOffchargeChange(this,'+estimateDetails.unitPrice+',\'' + estimateDetails.selectedPegId + '\')"/></td>'+
						
						'<td width=\'25px\' align=\'center\'><image onclick="deleteDetailLine(\'' + estimateDetails.isFromPegSchedule + '\'' +',\'' + estimateDetails.selectedPegId + '\'' +',\'' + resourType+'\')" src="../../image/X.png" /></td>'+
					
						'</tr>';
					
					if(resourType=='otherMat'){
						$('#estimateDetails').append(estimateDetails1+estimateDetailsOther);
						
					}else if(resourType=='NPL'){
						$('#estimateDetails').append(estimateDetails1+estimateDetailsNPL);
					}else{
					
						$('#estimateDetails').append(estimateDetails1+estimateDetailsOtherResource);
					}
					
				
					//$('#estimateDetails').append(estimateDetails1);
				
					
				});
				
				
			}
		);
 }else{
	alert("Please Select Items!!!...");
 }
hide_OtherMaterials();	
	
}

function onNumChangePegId(pegId){


selectedpegItem = pegId.value;
}
function onNumChange(quan,standCost,resourceCode){
//alert("hi");
	   if(!(quan.value > 0) || quan.value < 0){
	       
	       alert("Please enter valid Quantity");
	       quan.value = '';
	       return;
	    } else {
	        
			estmatedCost = quan.value*standCost;
			//$("#totalcost"+lineid).val(estmatedCost);
			$("#estimateCost"+resourceCode).val(estmatedCost);
			
			$.getJSON("smc/getEstimatedUpdateDetails.ajax?lineid="+resourceCode+"&quantity="+quan.value+"&linecost="+estmatedCost+"&unitcost="+standCost, {
				ajax_command: "getEstimatedUpdateDetailsForPeg",ajax_ieCacheFix:new Date().getTime() },
				function(json){ 
					var pkgTable1 = '<tr  id="pegid'+json.selectedPegId+'">'+	
					'<td id="radio'+json.selectedPegId+'" align="left" ><input type="radio" class="required" style="text-align: right" name="pegradio" id="'+json.selectedPegId+'"  value="'+json.selectedPegId+'"  onchange="onNumChangePegId(this)"  /></td>'+																							
					'<td id="description'+json.selectedPegId+'" align="left" >'+json.description+'</td>'+
					'<td id="quantity'+json.selectedPegId+'" align="left" ><input type="text" class="required" style="text-align: right" name="quantity" id="'+json.selectedPegId+'"  value="'+json.quantity+'"   /></td>'+
					//'<td align="right"><input type="text" class="required" style="text-align: right" name="'+json.quantity+'" id="'+json.quantity+'"  maxlength="10" size="10" value="" onchange="onNumChange1(this)" /></td>'+
					//'<td width=\'25px\' align=\'center\'><image onclick="deletePegDetailLine(\'' + json.selectedPegId + '\')" src="../../image/X.png" /></td>'+
					'</tr>';
				
					$('#pkgtable').append(pkgTable1);
			});
			
			var rows =$('#estimateDetails tr');
			var rowcount = rows.length;
			var calculatedCost=0;	
			var lineCost =0;
		
		  $("#estimateDetails tbody tr input[name$='estimateCost']").each(function() {
			  lineCost = $(this).val();
		
			  calculatedCost=parseFloat(isNaN(calculatedCost) ? 0 : calculatedCost) + parseFloat(isNaN(lineCost) ? 0 : lineCost);
				 
				 if(!isNaN(calculatedCost))
				 {
					 $("#totalCost").text(calculatedCost);
					 $("#totalCost").val(calculatedCost);
					 $("#totalCostDisplay").val(intToFormat(calculatedCost));	
				 }
		   });

		}
}


/**function onNumChange(quan,standCost,resourceCode,reQuntity,reUsableQuantity,OffCharge){
	//alert("hi");
		   if(!(quan.value > 0) || quan.value < 0){
		       
		       alert("Please enter valid Quantity");
		       quan.value = '';
		       return;
		    } else {
		        
				estmatedCost = quan.value*standCost;
				rebateCost = reQuntity.value*standCost;
				//$("#totalcost"+lineid).val(estmatedCost);
				$("#estimateCost"+resourceCode).val(estmatedCost);
				
				$.getJSON("smc/getEstimatedUpdateDetails.ajax?lineid="+resourceCode+"&quantity="+quan.value+"&linecost="+estmatedCost+"&unitcost="+standCost+"&reuquantity="+reQuntity, {
					ajax_command: "getEstimatedUpdateDetailsForPeg",ajax_ieCacheFix:new Date().getTime() },
					function(json){ 
						var pkgTable1 = '<tr  id="pegid'+json.selectedPegId+'">'+	
						'<td id="radio'+json.selectedPegId+'" align="left" ><input type="radio" class="required" style="text-align: right" name="pegradio" id="'+json.selectedPegId+'"  value="'+json.selectedPegId+'"  onchange="onNumChangePegId(this)"  /></td>'+																							
						'<td id="description'+json.selectedPegId+'" align="left" >'+json.description+'</td>'+
						'<td id="quantity'+json.selectedPegId+'" align="left" ><input type="text" class="required" style="text-align: right" name="quantity" id="'+json.selectedPegId+'"  value="'+json.quantity+'"   /></td>'+
						//'<td align="right"><input type="text" class="required" style="text-align: right" name="'+json.quantity+'" id="'+json.quantity+'"  maxlength="10" size="10" value="" onchange="onNumChange1(this)" /></td>'+
						//'<td width=\'25px\' align=\'center\'><image onclick="deletePegDetailLine(\'' + json.selectedPegId + '\')" src="../../image/X.png" /></td>'+
						'</tr>';
					
						$('#pkgtable').append(pkgTable1);
				});
				
				var rows =$('#estimateDetails tr');
				var rowcount = rows.length;
				var calculatedCost=0;	
				var lineCost =0;
			
			  $("#estimateDetails tbody tr input[name$='estimateCost']").each(function() {
				  lineCost = $(this).val();
			
				  calculatedCost=parseFloat(isNaN(calculatedCost) ? 0 : calculatedCost) + parseFloat(isNaN(lineCost) ? 0 : lineCost);
					 
					 if(!isNaN(calculatedCost))
					 {
						 $("#totalCost").text(calculatedCost);
						 $("#totalCost").val(calculatedCost);
						 $("#totalCostDisplay").val(intToFormat(calculatedCost));	
					 }
			   });

			}
		}*/
function onQuantityChange1(quan,standCost,resourceCode){
	//alert("hi");
		var quantity = $("#estimateQuantity1"+resourceCode).val();
		var unitPrice = $("#unitPrice1"+resourceCode).val();
		
		   if(!(quantity > 0) || quantity < 0){
		       
		       alert("Please enter valid Quantity");
		       quantity.value = '';
		       return;
		    } else {
		        
				estmatedCost = quantity*unitPrice;
				//$("#totalcost"+lineid).val(estmatedCost);
				$("#estimateCost"+resourceCode).val(estmatedCost);
				
				$.getJSON("smc/getEstimatedUpdateDetails.ajax?lineid="+resourceCode+"&quantity="+quan.value+"&linecost="+estmatedCost+"&unitcost="+unitPrice, {
					ajax_command: "getEstimatedUpdateDetails",ajax_ieCacheFix:new Date().getTime() },
					function(json){ 
						
				});
				
				var rows =$('#estimateDetails tr');
				var rowcount = rows.length;
				var calculatedCost=0;	
				var lineCost =0;
			
			  $("#estimateDetails tbody tr input[name$='estimateCost']").each(function() {
				  lineCost = $(this).val();
			
				  calculatedCost=parseFloat(isNaN(calculatedCost) ? 0 : calculatedCost) + parseFloat(isNaN(lineCost) ? 0 : lineCost);
					 
					 if(!isNaN(calculatedCost))
					 {
						 $("#totalCost").text(calculatedCost);
						 $("#totalCost").val(calculatedCost);
						 $("#totalCostDisplay").val(intToFormat(calculatedCost));	
					 }
			   });

			}
		}
function onNumChange1(quantity){

var selectedID=nodeId;


document.getElementById('addedPegInfo').style.display = '';

	    if(selectedID !=''){
			$.getJSON("smc/prepareEstimateDetails.ajax?selectedPegId="+selectedID+"&quantity="+quantity.value, {
			ajax_command: "prepareEstimateDetails",ajax_ieCacheFix:new Date().getTime() },
			function(json){ 
				var myCell = $('#estimateDetails');
							//alert(json.description);
					$.each(json.estimateDetails,function(i,estimateDetail){
					//$('#lineseciid'+estimateDetail.selectedPegId).remove();
					
				//alert(json.scanStatus);
					//var edd=normjson.standardcost;			
				//$('#description').text(json.scanStatus);
															
				var estimateDetails = '<tr  id="lineseciid'+estimateDetail.selectedPegId+'">'+	
								//'<td id="radio'+estimateDetail.selectedPegId+'" align="middle" ><input type="radio" class="required" style="text-align: right" name="pegradio" id="'+estimateDetail.selectedPegId+'"  value="'+estimateDetail.selectedPegId+'"  /></td>'+																							
								'<td id="resourceType'+estimateDetail.selectedPegId+'" align="left" >'+estimateDetail.resourceType+'</td>'+
								'<td id="resourceCode'+estimateDetail.selectedPegId+'" align="left" >'+estimateDetail.resourceCode+'</td>'+
								'<td id="resourceCategory'+estimateDetail.selectedPegId+'" align="left" >'+estimateDetail.resourceCategory+'</td>'+
								'<td id="uom'+estimateDetail.selectedPegId+'" align="left" >'+estimateDetail.uom+'</td>'+
								'<td id="unitPrice'+estimateDetail.selectedPegId+'" align="left" >'+estimateDetail.unitPrice+'</td>'+
								//'<td id="estimateQuantity'+estimateDetail.selectedPegId+'" align="middle" >'+estimateDetail.estimateQuantity+'</td>'+
								//'<td id="estimateQuantity'+estimateDetail.selectedPegId+'" align="middle" ><input type="text" class="required" style="text-align: right" name="'+estimateDetail.estimateQuantity+'" id="'+estimateDetail.estimateQuantity+'"  maxlength="10" size="10" value="'+estimateDetail.estimateQuantity+'"  /></td>'+
								
								'<td id="estimateCost'+estimateDetail.selectedPegId+'" align="left" >'+estimateDetail.estimateCost+'</td>'+
								//'<td id="tolerance'+estimateDetail.selectedPegId+'" align="middle" >'+estimateDetail.tolerance+'</td>'+
								//'<td align="right"><input type="text" class="required" style="text-align: right" name="'+json.quantity+'" id="'+json.quantity+'"  maxlength="10" size="10" value=""  onchange="onNumChange(this)"  /></td>'+
								
								'</tr>';
							
$('#estimateDetails').append(estimateDetails);

					});
					
					//show_materials();
					//show_materials();
					//dojo.event.topic.publish("display_norms");
					//document.getElementById('otherMat').style.display = '';
								//$('#pkgtable').append(pkgTable1+pkgTable2);
				}
			);
	 }else{
		alert("Please Select Items!!!...");
	 }

}



function validateSelection(val){
var isSelected = false;
if(allSelected.length > 0){
for(var i =0; i<allSelected.length;i++){
                    //alert(val == selected[i]);
               if(val == allSelected[i]){
			//alert("hi");
					 alert("Already have selected that item!!!!");
					  isSelected = true;
                   }else{
				    isSelected = false;
				   }                            
            }
	}
	
	return isSelected;
}	
function onPegItemChange(quan,pegId){
	var status=false;
	var quantity = $("#quantity"+pegId).val();
	var wareHouseId=escape($("#wareHouseId").val());	
	   if(quantity < 0){
	       
	       alert("Please enter valid Quantity");
	       quantity.value = '';
	       return;
	    } else {
	        status = true;
			
			$.getJSON("smc/addAdditinalPegInfo.ajax?pegId="+pegId+"&quantity="+quan.value+"&warehouseId="+wareHouseId, {
				ajax_command: "addAdditinalPegInfo",ajax_ieCacheFix:new Date().getTime() },
				function(json){ 
								$.each(json.estimateDetails,function(i,estimateDetail){
					//alert(json.needToRemovePreRows);
				if(estimateDetail.needToRemovePreRows != null){
					$('#lineseciid'+estimateDetail.needToRemovePreRows).remove();
					//alert($('#lineseciid'+estimateDetail.needToRemovePreRows));
				}					
				var estimateDetails = '<tr  id="lineseciid'+estimateDetail.resourceCode+'">'+	
								//'<td id="radio'+estimateDetail.selectedPegId+'" align="middle" ><input type="radio" class="required" style="text-align: right" name="pegradio" id="'+estimateDetail.selectedPegId+'"  value="'+estimateDetail.selectedPegId+'"  /></td>'+																							
								//'<td id="resourceType'+estimateDetail.selectedPegId+estimateDetail.resourceCode+'" align="middle" >'+estimateDetail.resourceType+'</td>'+
								'<td id="resourceCode'+estimateDetail.resourceCode+'" align="left" >'+estimateDetail.resourceCode+'</td>'+
								'<td id="resourceName'+estimateDetail.resourceCode+'" align="left" >'+estimateDetail.resourceName+'</td>'+
								'<td id="uom'+estimateDetail.resourceCode+'" align="left" >'+estimateDetail.uom+'</td>'+
								'<td id="unitPrice'+estimateDetail.resourceCode+'" align="left" >'+estimateDetail.unitPrice+'</td>'+
								'<td id="estimateQuantity'+estimateDetail.resourceCode+'" align="left" ><input type="text" class="required" style="text-align: right;color: green;" name="'+estimateDetail.estimateQuantity+'" id="'+estimateDetail.estimateQuantity+'"  maxlength="20" size="20"  value="'+estimateDetail.estimateQuantity+'" onchange="onNumChange(this,'+estimateDetail.unitPrice+',\'' + estimateDetail.resourceCode + '\')"  /></td>'+
								'<td align="left" ><input type="text" class="required" style="text-align: right;color: green;"  name="estimateCost"  id="estimateCost'+estimateDetail.resourceCode+'"  maxlength="20" size="20" value="'+estimateDetail.estimateCost+'"  /></td>'+

								'<td width=\'25px\' align=\'center\'><image onclick="deleteDetailLine(\'' + estimateDetail.isFromPegSchedule + '\'' +',\'' + estimateDetail.resourceCode + '\'' +',\'' + null+'\')" src="../../image/X.png" /></td>'+

								'</tr>';
							
							$('#estimateDetails').append(estimateDetails);
							$("#totalCost").text(json.totalCost);
							 $("#totalCost").val(json.totalCost);
							 $("#totalCostDisplay").val(intToFormat(json.totalCost));
				}
				
				);
			});
			
		}
		
		
	}
function validateQuantity(quan){

   if(!(quan.value > 0) || quan.value < 0){
       
       alert("Please enter valid Quantity");
       quan.value = '';
       return;
    }
}	
function addPegItems(){
	var applicationId=$("#applicationId").val();
	var wareHouseId=escape($("#wareHouseId").val());	
	//alert(applicationId);
	//if(applicationId==-1){
	//	alert("Please select Reference Number");
	//	return;
	//}
	var selectedID=nodeId;
	var selectedIDValue = nodeText;
	
	document.getElementById('addedPegInfo').style.display = '';
	  if(selectedID ==''){
		alert("Please Select Items!!!...");
		
       return;
	  }
	  if( document.getElementById('itemquantity').value ==''){
		alert("Please Enter Item Quantity!!!...");
		
       return;
	  }
	 nodeId='';
	 nodeText='';
	 var invalidStatus = "false";
	//alert(document.getElementById('itemquantity').value);
	
    	   
			$.getJSON("smc/showPegInfo.ajax?selectedPegId="+selectedID+"&selectedPegText="+selectedIDValue+"&quantity="+document.getElementById('itemquantity').value+"&applicationId="+applicationId+"&warehouseId="+wareHouseId, {
				ajax_command: "showPegInfo",ajax_ieCacheFix:new Date().getTime() },
				function(json){ 
					var myCell = $('#pkgtable');
								
					if(json.invalid){
					    invalidStatus = "true";
						alert(json.errorMessage);
						return;
					}	
 					
					var pkgTable1 = '<tr  id="pegid1'+json.selectedPegId+'">'+	
					'<td id="radio1'+json.selectedPegId+'" align="left" ><input type="radio" class="required" style="text-align: right" name="pegradio" id="pegid'+json.selectedPegId+'"  value="'+json.selectedPegId+'"  onchange="onNumChangePegId(this)"  /></td>'+																							
					'<td id="description1'+json.selectedPegId+'" align="left" >'+json.description+'</td>'+
					'<td id="quantity1'+json.selectedPegId+'" align="left" ><input type="text" class="required" style="text-align: right" name="quantity" id="quantity'+json.selectedPegId+'"  value="'+json.quantity+'"  onchange="onPegItemChange(this,'+'\''+json.selectedPegId + '\')"/></td>'+
					//'<td align="right"><input type="text" class="required" style="text-align: right" name="'+json.quantity+'" id="'+json.quantity+'"  maxlength="10" size="10" value="" onchange="onNumChange1(this)" /></td>'+
					//'<td width=\'25px\' align=\'center\'><image onclick="deletePegDetailLine(\'' + json.selectedPegId + '\')" src="../../image/X.png" /></td>'+
					'</tr>';
								
					$('#pkgtable').append(pkgTable1);
					$.each(json.estimateDetails,function(i,estimateDetail){
						
					if(estimateDetail.needToRemovePreRows != null){
						$('#lineseciid'+estimateDetail.needToRemovePreRows).remove();
						//alert($('#lineseciid'+estimateDetail.needToRemovePreRows));
					}											
					var estimateDetails = '<tr  id="lineseciid'+estimateDetail.resourceCode+'">'+	
									//'<td id="radio'+estimateDetail.selectedPegId+'" align="middle" ><input type="radio" class="required" style="text-align: right" name="pegradio" id="'+estimateDetail.selectedPegId+'"  value="'+estimateDetail.selectedPegId+'"  /></td>'+																							
									//'<td id="resourceType'+estimateDetail.selectedPegId+estimateDetail.resourceCode+'" align="middle" >'+estimateDetail.resourceType+'</td>'+
									'<td id="resourceCode'+estimateDetail.resourceCode+'" align="left" >'+estimateDetail.resourceCode+'</td>'+
									'<td id="resourceName'+estimateDetail.resourceCode+'" align="left" >'+estimateDetail.resourceName+'</td>'+
									'<td id="uom'+estimateDetail.resourceCode+'" align="left" >'+estimateDetail.uom+'</td>'+
									'<td id="unitPrice'+estimateDetail.resourceCode+'" align="left" >'+estimateDetail.unitPrice+'</td>'+
									'<td id="estimateQuantity'+estimateDetail.resourceCode+'" align="left" ><input type="text" class="required" style="text-align: right;color: green;" name="'+estimateDetail.estimateQuantity+'" id="'+estimateDetail.estimateQuantity+'"  maxlength="10" size="10"  value="'+estimateDetail.estimateQuantity+'" onchange="onNumChange(this,'+estimateDetail.unitPrice+',\'' + estimateDetail.resourceCode + '\')" /></td>'+
									'<td align="left" ><input type="text" class="required" style="text-align: right;color: green;"  name="estimateCost"  id="estimateCost'+estimateDetail.resourceCode+'"  maxlength="10" size="10" value="'+estimateDetail.estimateCost+'"  /></td>'+

									'<td id="rebateQuantity'+estimateDetail.resourceCode+'" align="left" ><input type="text" class="required" style="text-align: right;color: green;" name="'+estimateDetail.rebateQuantity+'" id="'+estimateDetail.rebateQuantity+'"  maxlength="20" size="20" value="" onchange="onRebateChange(this,'+estimateDetail.unitPrice+',\'' + estimateDetail.resourceCode + '\')" /></td>'+
									'<td align="left" ><input type="text" class="required" style="text-align: right;color: green;" readOnly="true" name="rebateCost"  id="rebateCost'+estimateDetail.resourceCode+'"  maxlength="20" size="20" value=""  readOnly="true" onchange="onRebateChange(this,'+estimateDetail.unitPrice+',\'' + estimateDetail.resourceCode + '\')"/></td>'+
									'<td id="reusableQuantity'+estimateDetail.resourceCode+'" align="left" ><input type="text" class="required" style="text-align: right;color: green;" name="'+estimateDetail.reusableQuantity+'" id="'+estimateDetail.reusableQuantity+'"  maxlength="20" size="20" value="" onchange="onReusableChange(this,'+estimateDetail.unitPrice+',\'' + estimateDetail.resourceCode + '\')"/></td>'+

									'<td id="offChargeQuantity'+estimateDetail.resourceCode+'" align="left" ><input type="text" class="required" style="text-align: right;color: green;" name="'+estimateDetail.offChargeQuantity+'" id="'+estimateDetail.offChargeQuantity+'"  maxlength="20" size="20" value=""  onchange="onOffchargeChange(this,'+estimateDetail.unitPrice+',\'' + estimateDetail.resourceCode + '\')"/></td>'+
									
									'<td width=\'25px\' align=\'center\'><image onclick="deleteDetailLine(\'' + estimateDetail.isFromPegSchedule + '\'' +',\'' + estimateDetail.resourceCode + '\'' +',\'' + null+'\')" src="../../image/X.png" /></td>'+

									'</tr>';
								
								$('#estimateDetails').append(estimateDetails);
						
					}
					
				);
			}
		);
		catculateTotalCost();
				
	

}

function catculateTotalCost(){
	
		var rows =$('#estimateDetails tr');
		var rowcount = rows.length;
		var calculatedCost=0;	
		var lineCost =0;
		//alert(rows);
	  $("#estimateDetails tbody tr input[name$='estimateCost']").each(function() {
		  lineCost = $(this).val();
	
		  calculatedCost=parseFloat(isNaN(calculatedCost) ? 0 : calculatedCost) + parseFloat(isNaN(lineCost) ? 0 : lineCost);
			 
			 
	   });
	   if(!isNaN(calculatedCost)){
	  
				// $("#totalCost").text(calculatedCost);
				 $("#totalCost").val(calculatedCost);
				 $("#totalCostDisplay").val(intToFormat(calculatedCost));	
				 
			 }
			  //alert(calculatedCost);
}


function deleteDetailLine(isFromPegSchedule,lineid,resourceType){
	//if(lineid.match("%")){
	//	alert("Cannot Remove !!!! This is system generated Entry");
	//	return;
	//}
//alert(resourceType);
	var status= confirm("Do you really want to remove this ?");
	 if (status== true){
		 var deleteItemValue=$("#estimateCost"+lineid).val();	
			
			var totalEstimatedCost=$("#totalCost").val();
			
			var newCost = 0;
			if(!document.getElementById("estimateCost"+lineid).value==''){
				newCost= totalEstimatedCost - parseFloat(deleteItemValue);
			}
			
				
			$("#totalCost").val(newCost);
			$("#totalCostDisplay").val(intToFormat(newCost));	
		$('#lineseciid'+lineid).remove();
			
			//alert(isFromPegSchedule);
			if(isFromPegSchedule=="false"){
				$.getJSON("smc/loadAdditionalResourceDetails.ajax?selectedItems="+lineid+"&func=delete"+"&resourceType="+resourceType, {
				ajax_command: "loadAdditionalResourceDetails",ajax_ieCacheFix:new Date().getTime() },
					function(json){ 
				
							$.each(json.addedMaterials,function(i,estimateDetails){
				
								//$('#totalcostRow').remove();
									//alert(json.addedMaterials.length);	
							var otherMattable = '<tr  id="otherMaterials'+estimateDetails.resourceCode+'">'+
									'<td align="right"><input type="checkbox" class="required" style="text-align: left" name="chkMatCode" id="'+estimateDetails.resourceCode+'"  value=""  /></td>'+	
									'<td id="resourceCode'+estimateDetails.resourceCode+'" align="middle" style="text-align: left">'+estimateDetails.resourceCode+'</td>'+					
							'<td id="resourceType'+estimateDetails.resourceCode+'" align="middle" style="text-align: left">'+estimateDetails.resourceType+'</td>'+					
							
									'<td id="description'+estimateDetails.resourceCode+'" align="middle" style="text-align: left">'+estimateDetails.resourceName+'</td>'+
									'<td id="uom'+estimateDetails.resourceCode+'" align="middle" style="text-align: left">'+estimateDetails.uom+'</td>'+
									'<td id="s2d'+estimateDetails.resourceCode+'" align="middle" style="text-align: left" >'+estimateDetails.unitPrice+'</td>'+
									'</tr></table></td>'+
									'</tr>';
						
						
						if(resourceType=='otherMat'){
							$('#otherMattable').append(otherMattable);
							//$('#otherMaterials'+estimateDetails.selectedPegId).remove();
						}else if(resourceType=='otherResource'){
							$('#otherResourceTable').append(otherMattable);
						}else if(resourceType=='NPL'){
							$('#NPLMaterialsTable').append(otherMattable);
						}

						
		
							});
							
					}
				);
			}else{
			//alert(lineid);
				$.getJSON("smc/deletePegIngoDetailLine.ajax?selectedItems="+lineid, {
				ajax_command: "deletePegIngoDetailLine",ajax_ieCacheFix:new Date().getTime() },
					function(json){ 
				
							
					}
				);
			
			}
	 }
	 else{
	  	return;
	 }
	  			
	
	
}
function viewMasterDetails(){
	
	var selectedID=selectedpegItem;
	
	document.getElementById('addedPegInfo').style.display = '';
	//alert(selectedID);
    	    if(selectedID !=''){
				$.getJSON("smc/viewMasterDetails.ajax?selectedPegId="+selectedID, {
				ajax_command: "viewMasterDetails",ajax_ieCacheFix:new Date().getTime() },
				function(json){ 
					var myCell = $('#masterDetails');
								//alert(json.description);
								$("#masterDetails tr:gt(0)").remove();
						$.each(json.masterDetails,function(i,masterDetail){
						//$('#linesecid'+i).remove();
							
					//alert(json.scanStatus);
						//var edd=normjson.standardcost;			
					//$('#description').text(json.scanStatus);
																
					var masterDetails = '<tr  id="linesecid'+masterDetail.selectedPegId+'">'+	
									'<td id="resourceType'+masterDetail.selectedPegId+'" align="left" >'+masterDetail.resourceType+'</td>'+
									'<td id="resourceCode'+masterDetail.selectedPegId+'" align="left" >'+masterDetail.resourceCode+'</td>'+
									'<td id="resourceName'+masterDetail.selectedPegId+'" align="left" >'+masterDetail.resourceName+'</td>'+
									'<td id="estimateQuantity'+masterDetail.selectedPegId+'" align="left" >'+masterDetail.estimateQuantity+'</td>'+
									'<td id="uom'+masterDetail.selectedPegId+'" align="left" >'+masterDetail.uom+'</td>'+
									'</tr>';
								
$('#masterDetails').append(masterDetails);

						});
						
						
					}
				);
		 }else{
			alert("Please Select Items!!!...");
		 }
	
}

function load_ResourceTypes()
{


	//$("#applicationRefs select option:gt(0)").remove();
	//$("#subcribers select option:gt(0)").remove();
	 $.getJSON("sps/loadResourceTypes.ajax", {ajax_command:"loadResourceTypes",ajax_ieCacheFix:new Date().getTime()}, function(json) {

        if (json.jsonarrayResourceTypes != null && json.jsonarrayResourceTypes.length > 0) {
            for (x = 0; x < json.jsonarrayResourceTypes.length; x++) {
             
                $("#resourceTypes select").append($('<option></option>').val(json.jsonarrayResourceTypes[x].id).html(json.jsonarrayResourceTypes[x].name));
            }
        }
       
	 });
	

	
}
function load_ResourceCodes(resourceTypeId)
{

//if(resourceTypeId=="0"){
			$('#resourceName').val("");	
			$('#unitPrice').val("");
			$('#tollerance').val("");	
			$('#estiQuantity').val("");	
			$('#totalCost').val("");
			
			//return;	
			//}
	//$("#applicationRefs select option:gt(0)").remove();
	$("#resourceCodes select option:gt(0)").remove();
	//alert(resourceTypeId.value);
	 $.getJSON("sps/loadResourceCodes.ajax?resourceTypeId="+resourceTypeId.value, {ajax_command:"loadResourceCodes",ajax_ieCacheFix:new Date().getTime()}, function(json) {

        if (json.jsonarrayResourceCodes != null && json.jsonarrayResourceCodes.length > 0) {
            for (x = 0; x < json.jsonarrayResourceCodes.length; x++) {
             
                $("#resourceCodes select").append($('<option></option>').val(json.jsonarrayResourceCodes[x].id).html(json.jsonarrayResourceCodes[x].name));
            }
        }
       
	 });

	
}
function showAddJobInfo()
{
	document.getElementById('newEstimaleDetails').style.display = '';
	load_ResourceTypes();
}
function cancel(){

	document.getElementById('newEstimaleDetails').style.display = 'none';
}

function loadEstimateData(){

		$('#resourceName').val("");	
		$('#unitPrice').val("");
		$('#tollerance').val("");	
		$('#estiQuantity').val("");	
		$('#totalCost').val("");
			
		$("#resourceCodes select option:gt(0)").remove();
	//alert(resourceType.value);
		//var resourceTypeId=resourceType.value;	
		var resourceTypeId=escape($("#resourceTypeId").val());	
		$.getJSON("smc/loadResourceDetails.ajax?resourceTypeId="+resourceTypeId, {
		ajax_command: "loadResourceDetails",ajax_ieCacheFix:new Date().getTime() },
		function(json){ 
				
			$('#lineSectypeId').val(json.lineSectypeId);				
			$('#resourceName').val(json.resourceName);	
			$('#unitPrice').val(json.unitPrice);
			$('#tollerance').val(json.tolerance);	
			$('#estiQuantity').val(json.estimateQuantity);	
	
			$('#totalCost').val(json.totalCost);
				 
			if (json.jsonarrayResourceCodes != null && json.jsonarrayResourceCodes.length > 0) {
				for (x = 0; x < json.jsonarrayResourceCodes.length; x++) {
				 
					$("#resourceCodes select").append($('<option></option>').val(json.jsonarrayResourceCodes[x].id).html(json.jsonarrayResourceCodes[x].name));
				}
				$('#resourceCodeId').val(json.resourceCode);
			}

			});

	}
function calculateTotal(quan,unitPrice,resourceCode){


	
//if(!($('#estiQuantity').val() > 0) || $('#estiQuantity').val() < 0){
     if(!(quan.value > 0) || quan.value < 0){   
       alert("Please enter valid Quantity");
      // $('#estiQuantity').val('');
	   quan.value = '';
       return;
      
    }
	//alert("quan "+quan  ",unitPrice"+unitPrice "resourceCode"+resourceCode);
	document.getElementById('totalCost').style.display = '';
	
	//$("#totalCost").val($('#unitPrice').val()*quan.value);


	var estmatedCost = quan.value*unitPrice;
	//alert();
	//alert($("#estimateCost"+resourceCode));
		$("#estimateCost"+resourceCode).val(estmatedCost);
		
		
		
		var rows =$('#estimateDetails tr');
		var rowcount = rows.length;
		var calculatedCost=0;	
		var lineCost =0;
	
	  $("#estimateDetails tbody tr input[name$='estimateCost']").each(function() {
		  lineCost = $(this).val();
	
		  calculatedCost=parseFloat(isNaN(calculatedCost) ? 0 : calculatedCost) + parseFloat(isNaN(lineCost) ? 0 : lineCost);
			 
			 if(!isNaN(calculatedCost))
			 {
				 $("#totalCost").text(calculatedCost);
				 $("#totalCost").val(calculatedCost);
				 $("#totalCostDisplay").val(intToFormat(calculatedCost));
			 }
	   });

	   
 /*if(!(quan.value > 0) || quan.value < 0){
       
       alert("Please enter valid Quantity");
       quan.value = '';
       return;
    }	*/
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
				// $('#wareHouseId').val(json.warehouse);

				 if (json.jsonFundsources != null && json.jsonFundsources.length > 0) {
					 for (x = 0; x < json.jsonFundsources.length; x++) {
					  
						 $("#fundsource select").append($('<option></option>').val(json.jsonFundsources[x].id).html(json.jsonFundsources[x].name));
					 }
				 }
				  //$('#fundsou').val(json.fundsource);
				 
				   if (json.jsonCategoryCodes != null && json.jsonCategoryCodes.length > 0) {
					   for (x = 0; x < json.jsonCategoryCodes.length; x++) {
						
						   $("#category select").append($('<option></option>').val(json.jsonCategoryCodes[x].id).html(json.jsonCategoryCodes[x].name));
					   }
				   }
				
				 //$('#categoryId').val(json.categoryCode);
				 
				  if (json.jsonfundIds != null && json.jsonfundIds.length > 0) {
					   for (x = 0; x < json.jsonfundIds.length; x++) {
						
						   $("#funid select").append($('<option></option>').val(json.jsonfundIds[x].id).html(json.jsonfundIds[x].name));
					   }
				   }
				
				 //$('#fundid').val(json.fundid);
			
	 });
	  }
	
	
}

function loadCategoryCodes(){
	$("#category select option:gt(0)").remove();
	 $.getJSON("sps/loadCategoryCodes.ajax?warehouse=" +$('#wareHouseId').val(), {ajax_command:"loadCategoryCodes",ajax_ieCacheFix:new Date().getTime()}, function(json) {

       if (json.categoryCodes != null && json.categoryCodes.length > 0) {
           for (x = 0; x < json.categoryCodes.length; x++) {
            
               $("#category select").append($('<option></option>').val(json.categoryCodes[x].id).html(json.categoryCodes[x].name));
           }
       }
      
  	 //$('#categoryId').val(json.categoryCode);

  
	 });
	
	
}

function loadFundIds(){
//alert( $('#fundsou').val());
	//$("#funid select option:gt(0)").remove();
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
		$('#rejectReason').val("");	
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
		ajax_command: "loadEstimationDetails",ajax_ieCacheFix:new Date().getTime() },
		function(json){ 
			
			//$('#totalCost').val(json.totalCost);
			$("#totalCostDisplay").val(intToFormat(json.totalCost));	
			$('#rebate').val(json.rebate);
			$('#estimateDatePicker').val(json.estimateDatePicker);	
			$('#fundsou').val(json.fundsou);	
			$('#fundid').val(json.fundid);
			
			$('#rejectReason').val(json.rejectedReason);	
			$('#description').val(json.description);	
			$('#categoryId').val(json.categoryId);	
			$('#pivNo').val(json.pivNo);	
			$('#amount').val(json.amount);
			$('#applicationId').val(json.stdEstimateNo);
//alert(json.estimateDetails.length);			
			$('#pivDatePicker').val(json.pivDatePicker);	
			$('#fileRef').val(json.fileRef);
			$('#divSec').val(json.divSec);
			$('#area').val(json.area);
			$('#eCSC').val(json.eCSC);
			$('#district').val(json.district);
			$('#esname').val(json.esname);
			
					
						//$.each(json.estimateDetails,function(i,estimateDetail){
							
																
					$.each(json.estimateDetails,function(i,estimateDetail){
						
						var resCd =estimateDetail.resourceCode;
						var resType =estimateDetail.resourceType;
						//alert(resType);								
						var estimateDetails = '<tr  id="lineseciid'+estimateDetail.resourceCode+'">'+	
						//'<td id="radio'+estimateDetail.selectedPegId+'" align="middle" ><input type="radio" class="required" style="text-align: right" name="pegradio" id="'+estimateDetail.selectedPegId+'"  value="'+estimateDetail.selectedPegId+'"  /></td>'+																							
						//'<td id="resourceType'+estimateDetail.selectedPegId+estimateDetail.resourceCode+'" align="middle" >'+estimateDetail.resourceType+'</td>'+
						'<td id="resourceCode'+estimateDetail.resourceCode+'" align="left" >'+estimateDetail.resourceCode+'</td>'+
						'<td id="resourceName'+estimateDetail.resourceCode+'" align="left"" >'+estimateDetail.resourceName+'</td>'+
						'<td id="uom'+estimateDetail.resourceCode+'" align="left" >'+estimateDetail.uom+'</td>'+
						'<td id="unitPrice'+estimateDetail.resourceCode+'" align="left" >'+estimateDetail.unitPrice+'</td>';
						
						
						var unModified =	'<td id="estimateQuantity'+estimateDetail.resourceCode+'" align="left" ><input type="text" class="required" style="text-align: right;color: green;" name="'+estimateDetail.estimateQuantity+'" id="'+estimateDetail.estimateQuantity+'"   maxlength="20" size="20"  value="1" readonly="true"  /></td>'+
							'<td align="left" ><input type="text" class="required" style="text-align: right;color: green;"  name="estimateCost" readonly="true" id="estimateCost'+estimateDetail.resourceCode+'"  maxlength="20" size="20" value="'+estimateDetail.estimateCost+'"  /></td>';

						
						var modified='<td id="estimateQuantity'+estimateDetail.resourceCode+'" align="left" ><input type="text" class="required" style="text-align: right;color: green;" name="'+estimateDetail.estimateQuantity+'" id="'+estimateDetail.estimateQuantity+'"  maxlength="20" size="20"  value="'+estimateDetail.estimateQuantity+'" onchange="onNumChange(this,'+estimateDetail.unitPrice+',\'' + estimateDetail.resourceCode + '\')"  /></td>'+
								'<td align="left" ><input type="text" class="required" style="text-align: right;color: green;"  name="estimateCost"  id="estimateCost'+estimateDetail.resourceCode+'"  readonly="true" maxlength="20" size="20" value="'+estimateDetail.estimateCost+'"  /></td>';
						

						//var reUnModified = '<td id="rebateQuantity'+estimateDetail.resourceCode+'" align="left" ><input type="text" class="required" style="text-align: right;color: green;" name="'+estimateDetail.rebateQuantity+'" id="'+estimateDetail.rebateQuantity+'"  maxlength="20" size="20" value="" onchange="onRebateChange(this,'+estimateDetail.unitPrice+',\'' + estimateDetail.resourceCode + '\')" /></td>'+
							//	'<td align="left" ><input type="text" class="required" style="text-align: right;color: green;" readOnly="true" name="rebateCost"  id="rebateCost'+estimateDetail.resourceCode+'"  maxlength="20" size="20" value=""  readOnly="true" onchange="onRebateChange(this,'+estimateDetail.unitPrice+',\'' + estimateDetail.resourceCode + '\')"/></td>'+
							//	'<td id="reusableQuantity'+estimateDetail.resourceCode+'" align="left" ><input type="text" class="required" style="text-align: right;color: green;" name="'+estimateDetail.reusableQuantity+'" id="'+estimateDetail.reusableQuantity+'"  maxlength="20" size="20" value=""  /></td>'+
							//	'<td id="offChargeQuantity'+estimateDetail.resourceCode+'" align="left" ><input type="text" class="required" style="text-align: right;color: green;" name="'+estimateDetail.offChargeQuantity+'" id="'+estimateDetail.offChargeQuantity+'"  maxlength="20" size="20" value=""  /></td>';
								
								//alert("hii :"+estimateDetail.rebateQuantity);
						
						if(resType.match("MAT-COST")){
							var reUnModified = '<td id="rebateQuantity'+estimateDetail.resourceCode+'" align="left" ><input type="text" class="required" style="text-align: right;color: green;" name="'+estimateDetail.rebateQuantity+'" id="'+estimateDetail.rebateQuantity+'"  maxlength="20" size="20" value="'+estimateDetail.rebateQuantity+'" onchange="onRebateChange(this,'+estimateDetail.unitPrice+',\'' + estimateDetail.resourceCode + '\')" /></td>'+
							'<td align="left" ><input type="text" class="required" style="text-align: right;color: green;" readOnly="true" name="rebateCost"  id="rebateCost'+estimateDetail.resourceCode+'"  maxlength="20" size="20" readOnly="true" value="'+estimateDetail.rebateCost+'"  /></td>'+
							'<td id="reusableQuantity'+estimateDetail.resourceCode+'" align="left" ><input type="text" class="required" style="text-align: right;color: green;" name="'+estimateDetail.reusableQuantity+'" id="'+estimateDetail.reusableQuantity+'"  maxlength="20" size="20" value="'+estimateDetail.reusableQuantity+'" onchange="onReusableChange(this,'+estimateDetail.unitPrice+',\'' + estimateDetail.resourceCode + '\')" /></td>'+
							'<td id="offChargeQuantity'+estimateDetail.resourceCode+'" align="left" ><input type="text" class="required" style="text-align: right;color: green;" name="'+estimateDetail.offChargeQuantity+'" id="'+estimateDetail.offChargeQuantity+'"  maxlength="20" size="20" value="'+estimateDetail.offChargeQuantity+'" onchange="onOffchargeChange(this,'+estimateDetail.unitPrice+',\'' + estimateDetail.resourceCode + '\')"  /></td>';
							
						}else{
							var reUnModified = '<td id="rebateQuantity'+estimateDetail.resourceCode+'" align="left" ><input type="text" class="required" style="text-align: right;color: green;" name="'+estimateDetail.rebateQuantity+'" id="'+estimateDetail.rebateQuantity+'"  maxlength="20" size="20" readOnly="true" value="'+estimateDetail.rebateQuantity+'" onchange="onRebateChange(this,'+estimateDetail.unitPrice+',\'' + estimateDetail.resourceCode + '\')" /></td>'+
							'<td align="left" ><input type="text" class="required" style="text-align: right;color: green;" readOnly="true" name="rebateCost"  id="rebateCost'+estimateDetail.resourceCode+'"  maxlength="20" size="20" readOnly="true" value="'+estimateDetail.rebateCost+'"  /></td>'+
							'<td id="reusableQuantity'+estimateDetail.resourceCode+'" align="left" ><input type="text" class="required" style="text-align: right;color: green;" name="'+estimateDetail.reusableQuantity+'" id="'+estimateDetail.reusableQuantity+'"  maxlength="20" size="20" readOnly="true" value="'+estimateDetail.reusableQuantity+'" onchange="onReusableChange(this,'+estimateDetail.unitPrice+',\'' + estimateDetail.resourceCode + '\')" /></td>'+
							'<td id="offChargeQuantity'+estimateDetail.resourceCode+'" align="left" ><input type="text" class="required" style="text-align: right;color: green;" name="'+estimateDetail.offChargeQuantity+'" id="'+estimateDetail.offChargeQuantity+'"  maxlength="20" size="20" readOnly="true" value="'+estimateDetail.offChargeQuantity+'" onchange="onOffchargeChange(this,'+estimateDetail.unitPrice+',\'' + estimateDetail.resourceCode + '\')"  /></td>';
							
						}
						
                     	//'<td id="estimateQuantity'+estimateDetail.resourceCode+'" align="left" ><input type="text" class="required" style="text-align: right;color: green;" name="'+estimateDetail.estimateQuantity+'" id="'+estimateDetail.estimateQuantity+'"  maxlength="20" size="20"  value="'+estimateDetail.estimateQuantity+'" onchange="onNumChange(this,'+estimateDetail.unitPrice+',\'' + estimateDetail.resourceCode + '\')"  /></td>'+
						//'<td align="left" ><input type="text" class="required" style="text-align: right;color: green;"  name="estimateCost"  id="estimateCost'+estimateDetail.resourceCode+'"  maxlength="20" size="20" value="'+estimateDetail.estimateCost+'"  /></td>'+

						var deleteLine ='<td width=\'25px\' align=\'center\'><image onclick="deleteDetailLine(\'' + estimateDetail.isFromPegSchedule + '\'' +',\'' + estimateDetail.resourceCode + '\'' +',\'' + null+'\')" src="../../image/X.png" /></td>'+

						'</tr>';
					
						if(resCd.match("%")){
						
						$('#estimateDetails').append(estimateDetails+unModified+reUnModified+deleteLine);

						}else{
							$('#estimateDetails').append(estimateDetails+modified+reUnModified+deleteLine);
						}
			
								
					}
							
				//}
				);
					$("#pkgtable tr:gt(0)").remove();
				$.each(json.pegDetails,function(i,pegDetail){
				
	
						var pkgTable1 = '<tr  id="pegid'+pegDetail.selectedPegId+'">'+	
						'<td id="radio'+pegDetail.selectedPegId+'" align="left" ><input type="radio" class="required" style="text-align: right" name="pegradio" id="'+pegDetail.selectedPegId+'"  value="'+pegDetail.selectedPegId+'"  onchange="onNumChangePegId(this)"  /></td>'+																							
						'<td id="description'+pegDetail.selectedPegId+'" align="left" >'+pegDetail.description+'</td>'+
						//'<td id="quantity'+pegDetail.selectedPegId+'" align="left" >'+pegDetail.quantity+'</td>'+
						'<td id="quantity1'+pegDetail.selectedPegId+'" align="left" ><input type="text" class="required" style="text-align: right" name="quantity" id="quantity'+pegDetail.selectedPegId+'"   value="'+pegDetail.quantity+'" onchange="onPegItemChange(this,'+'\''+pegDetail.selectedPegId + '\')"/></td>'+
						//'<td align="right"><input type="text" class="required" style="text-align: right" name="'+json.quantity+'" id="'+json.quantity+'"  maxlength="10" size="10" value="" onchange="onNumChange1(this)" /></td>'+
						//'<td width=\'25px\' align=\'center\'><image onclick="deletePegDetailLine(\'' + json.selectedPegId + '\')" src="../../image/X.png" /></td>'+
						'</tr>';
					
						$('#pkgtable').append(pkgTable1);
	
				}
				);
			});

		
	}
function validateForm()
{
	
	
	 var fundid=$("#fundid").val();
	
	 if(fundid==-1)
	 {
		 alert('Please enter FundId!!');
		// document.getElementById("fundid").focus();
		 return false;
	 }
	 var fundsou=$("#fundsou").val();
	 
	 if(fundsou==-1)
	 {
		 alert('Please enter Found Source!!');
		 //document.getElementById("fundsou").focus();
		 return false;
	 }
	 var categoryId=$("#categoryId").val();
	 if(categoryId==-1)
	 {
		 alert('Please enter category Code!!');
		 //document.getElementById("reviseReason").focus();
		 return false;
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
						<s:form id="frmEstimate" name="frmEstimate" action="pegSchedule!updateDetailEstimationRebate"  enctype="multipart/form-data" method="POST"
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
									<td  width="25%"><input type="text" name="fileRef"  id="fileRef" /></td>
									
									</tr>
									<tr>
									
									
									<td  width="25%">Cost Center</td>
									<td  width="25%"><input type="text" name="costCenterNo"  id="costCenterNo"  disabled="disabled"/>
									</td>
									<!-- <td  width="25%">Assigned To</td>
									<td  width="25%"><s:select name="appointedUserName" 
																										list="listuserName" 
																										id="listuserName" 		 																		
																										headerKey="-1"												
																										headerValue="-- Please Select --">																			
							    														</s:select></td> -->
							    	<td></td>
							    	<td></td>
									<td  width="25%">PIV Number</td>
									<td  width="25%"><input type="text" name="pivNo"  id="pivNo"/></td>
									</tr>
									<tr>
									<td  width="25%">Ware House</td>
									<td  width="25%"><div id="wareHouse"><select name="wareHouseId" id="wareHouseId" onchange="loadCategoryCodes()"><option value="-1">--Select--</option></select></div></td>
									<td  width="25%">&nbsp;</td>
									<td  width="25%">&nbsp;</td>
									<td  width="25%">Amount</td>
									<td  width="25%"><input type="text" name="amount"  id="amount" onChange="validateQuantity(this)"/></td>
									
									</tr>
									<tr>
									<td  width="25%">Estimate Date</td>
									<td  width="25%"><input type="text"  name="it" id="estimateDatePicker" readOnly="true"/></td>
									<td  width="25%">Fund Source</td>
									<td  width="25%"><div id="fundsource"><select name="fundsou" id="fundsou" onchange="loadFundIds()"></select></div></td>
									<td  width="25%">Rebate</td>
									<td  width="25%"><input type="text" name="rebate"  id="rebate" onChange="validateQuantity(this)"/></td>
									</tr>
									<tr>
									<td  width="25%">Estimate categoty</td>
									<td  width="25%"><div id="category"><select name="categoryId" id="categoryId"></select></div></td>
									<td  width="25%">Fund ID</td>
									<td  width="25%"><div id="funid"><select name="fundid" id="fundid"></select></div></td>
									<td  width="25%">Description</td>
									<td  width="25%"><textarea name="description" id="description" rows="5" cols="60"></textarea></td>
									
									</tr>
									<tr>
									<td  width="25%">Total Cost</td>
									<td  width="25%"><input type="hidden" name="totalCost"  id="totalCost" readOnly="true"/>
									<input type="text" name="totalCostDisplay"  id="totalCostDisplay" readOnly="true"/></td>
									<td  width="25%">PIV Date</td>
									<td  width="25%"><input type="text"  name="pivDate" id="pivDatePicker" readOnly="true"/></td>
									<td  width="25%">Reject Reason</td>
									<td  width="25%"><textarea name="rejectReason" id="rejectReason" rows="5" cols="60" ></textarea></td>
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
									<s:if test="%{branchType == 'EM'}">
										<td  width="25%">Job Types</td>
										<td  width="25%"><s:select name="jobTypes" id="jobTypes" list="#{'0':'--Select--','Meter Shifting':'Meter Shifting','Meter Replacement':'Meter Replacement','Installation of KWH Meters':'Installation of KWH Meters','Installation of Capacitors':'Installation of Capacitors','Phase Balancing':'Phase Balancing','Refubishment of 60A,3 Phase Service':'Refubishment of 60A,3 Phase Service','Refubishment of 30A,1 Phase Service':'Refubishment of 30A,1 Phase Service','Loss Reduction works in RE schemes':'Loss Reduction works in RE schemes','Other':'Other'}"  onchange="loadJobTypes(this)"></s:select></td>
									</s:if>
									<td  width="25%">&nbsp;&nbsp;&nbsp;&nbsp;</td>
									<td  width="25%">&nbsp;&nbsp;&nbsp;&nbsp;</td>
									<td  width="25%">&nbsp;&nbsp;&nbsp;&nbsp;</td>
									<td  width="25%">&nbsp;&nbsp;&nbsp;&nbsp;</td>
								
									<td  width="25%"><input type="submit" value="Update"  id="update" onclick="return validateForm()"/>
									<!--<s:if test="%{costCenterNo=='550.20' || costCenterNo=='510.20'}">
										<s:submit theme="simple" value="Update1" method="updatePriceEstimation" id="btnprint1" ></s:submit>
									</s:if>
									<s:if test="%{costCenterNo=='550.20' || costCenterNo=='550.30' || costCenterNo=='510.20' || costCenterNo=='510.30' || costCenterNo=='520.20' || costCenterNo=='520.30'}">
										<s:submit theme="simple" value="Print Estimate-A3" method="PrintEstimateA3" id="btnprint2" ></s:submit>
										
										<s:submit theme="simple" value="Print Summary Report-A3" method="PrintSummaryEstimateA3" id="btnprint3" ></s:submit>
									</s:if>
									<s:else>
										<s:submit theme="simple" value="Print Estimate" method="PrintEstimate" id="btnprint" ></s:submit>
										<s:submit theme="simple" value="Print Summary Report" method="PrintSummaryReport" id="btnprint1" ></s:submit>
									</s:else>-->
									
									<s:submit theme="simple" value="Print Estimate" method="PrintEstimate" id="btnprint" ></s:submit>
									<s:submit theme="simple" value="Print Rebate(A4)" method="PrintEstimateRebate" id="btnprint2" ></s:submit>
									<s:submit theme="simple" value="Print(A4)" method="PrintEstimateA4" id="btnprint2" ></s:submit>	
									<s:submit theme="simple" value="View Sketch" method="ViewSketch" id="btnprint" ></s:submit>
																
									<s:submit theme="simple" value="Exit" method="close" id="btnClose" >
									</s:submit><input type="button"  value="Clear" onclick="clearFormDetails();"/>
										Send to : <s:select name="postUserNameNew" id="postUserNameNew" list="listuserNameNew"></s:select>
													<s:submit theme="simple" align="right" value="Send For Validation" method="post" id="post" ></s:submit>
													<s:file name="fileUpload" id="fileUpload" label="Select a file to upload" size="30" />
         														<s:submit value="Upload Sketch" method="doUpload"/>
														
									</td>
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
						<div id="pegTree" style="width:25%;float: left">
						</div>
						<div id="addedPegInfo" style="display:block;width:75%">
							<table>
								<tr>
									<td>
										Quantity/Length: <input type="text" name="itemquantity" id="itemquantity" value="" onchange="validateQuantity(this)"/>
									</td>
									<td>
									<input type="button" name="add" id="add" value="Add Items" onclick="addPegItems();"/>
									</td>
									<td>
									<input type="button" name="add" id="add" value="View Masters" onclick="viewMasterDetails();"/>
									</td>
								</tr>
							</table>
							<br>
							<br><br><br>
							<div style="width:50%;float: left">
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
									<th><b>&nbsp;&nbsp;Res Type&nbsp;&nbsp;</b></th> <th><b>&nbsp;&nbsp;Res CD&nbsp;&nbsp;</b></th><th><b>&nbsp;&nbsp;Res Name&nbsp;&nbsp;</b></th><th><b>&nbsp;&nbsp;Estimated Quantity&nbsp;&nbsp;</b></th><th><b>&nbsp;&nbsp;UOM&nbsp;&nbsp;</b></th></tr>
					 			<tr id="linesecid"><td></td><td id="resType"></td><td id="resCD"></td><td id="resName"></td><td id="estimatedQuantity"></td><td id="uom"></td></tr>
					  
							</table>
							<div>
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
									<a id="showLink1" href="javascript:show_OtherMaterials()">Add Materials</a> 
								</td>
								
								<td>
									<a id="showLink3" href="javascript:show_OtherResources()">Add Other Resources</a> 
								</td>
								<td>
									<a id="showLink2" href="javascript:show_NPLMaterials()">ADD NPL Materials</a> 
								</td>
							</tr>
							<tr >
								<td colspan="3">
									<!--  <input type="button" name="new" id="new" value="Add New Estimate Entry" onclick="showAddJobInfo();"/>-->
									<table  class="tldFromContent1" id="estimateDetails"  style="display:block;" border="1"> 
							  			<tr id="header1">
											 <th><b>Resource CD&nbsp;&nbsp;</b></th><th><b>Resource Name&nbsp;&nbsp;</b></th><th><b>UOM&nbsp;&nbsp;</b></th><th><b>Unit Price&nbsp;&nbsp;</b></th><th><b>Estimated Quantity&nbsp;&nbsp;</b></th><th><b>Estimated Cost&nbsp;&nbsp;</b></th><th><b>Rebate Quantity&nbsp;&nbsp;</b></th><th><b>Rebate Cost&nbsp;&nbsp;</b></th><th><b>Reusable Quantity&nbsp;&nbsp;</b></th><th><b>Off Charge Quantity&nbsp;&nbsp;</b></th><th></th></tr>
							 			<tr id="linesecid"><td id="resCD"></td><td id="resName"></td><td id="estimatedQuantity"></tr>		
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

 