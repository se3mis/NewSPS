<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
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
loadMasterDataToForm();
 $('#estimateDatePicker').datePicker({clickInput:true});
 $('#pivDatePicker').datePicker({clickInput:true});
	
	var contextPath='sps/loadPegInfoMaster.ajax';

	var pegTreeLoader = new Ext.tree.TreeLoader({
		url: contextPath + '?ajax_command=loadPegInfoMaster' // equivalent to dataUrl
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
                   // alert(node.id);
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
	load_ResourceTypes();
});
function clearPeggingScheduleForm(){
	$.getJSON("smc/clearMaster.ajax", {
			ajax_command: "clearMaster",ajax_ieCacheFix:new Date().getTime() },
			function(json){ 
	
	});
	$("#allMasterDetails tr:gt(0)").remove();
	$("#viewMasterDetails tr:gt(0)").remove();
	
}
function clearItemsMaster(){
	$.getJSON("smc/clearItemListMaster.ajax", {
			ajax_command: "clearItemListMaster",ajax_ieCacheFix:new Date().getTime() },
			function(json){ 
	
	});
	
	$("#allMasterDetails tr:gt(0)").remove();
	$("#viewMasterDetails tr:gt(0)").remove();
	alert("Succefully added");
}
function loadMasterDataToForm(){
	$.getJSON("smc/loadAvailableOtherMaterialsForMaster.ajax", {
			ajax_command: "loadAvailableOtherMaterialsForMaster",ajax_ieCacheFix:new Date().getTime() },
			function(json){ 
	
	});
	
}
function show_OtherMaterials() {

	var wareHouseId=escape($("#wareHouseId").val());
	var applicationId=$("#applicationId").val();	
	
	if(applicationId==-1){
		alert("Please select Reference Number");
		return;
	}
	document.getElementById('otherMatdiv').style.display = '';
	document.getElementById('otherResourcesdiv').style.display = 'none';
	document.getElementById('nplMatdiv').style.display = 'none';

	$.getJSON("smc/addOtherMaterials.ajax?warehouseId="+wareHouseId, {ajax_command: "loadAvailableOtherMaterials",ajax_ieCacheFix:new Date().getTime() },
		function(json){ 
				//alert(json.otherMaterials.length);
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
function show_OtherMaterials1() {

	var wareHouseId=escape($("#wareHouseId").val());
	var applicationId=$("#applicationId").val();	
	
	
	document.getElementById('otherMatdiv1').style.display = '';
	document.getElementById('otherResourcesdiv').style.display = 'none';
	document.getElementById('nplMatdiv').style.display = 'none';
	document.getElementById('otherMatdiv2').style.display = 'none';
	document.getElementById('otherMatdiv3').style.display = 'none';
	document.getElementById('otherMatdiv4').style.display = 'none';
	document.getElementById('otherMatdiv5').style.display = 'none';
	document.getElementById('otherMatdiv6').style.display = 'none';
	document.getElementById('otherMatdiv7').style.display = 'none';
	document.getElementById('otherMatdiv8').style.display = 'none';
	document.getElementById('otherMatdiv9').style.display = 'none';


	$.getJSON("smc/addOtherMaterials.ajax?warehouseId="+wareHouseId, {ajax_command: "showOtherResourcesLink1",ajax_ieCacheFix:new Date().getTime() },
		function(json){ 
				//alert(json.otherMaterials.length);
				$("#otherMattable1 tr:gt(0)").remove();
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
							
					$('#otherMattable1').append(otherMattable);
			
				});
		}
	);

}
function show_OtherMaterials2() {

	var wareHouseId=escape($("#wareHouseId").val());
	var applicationId=$("#applicationId").val();	
	
	
	document.getElementById('otherMatdiv2').style.display = '';
	document.getElementById('otherResourcesdiv').style.display = 'none';
	document.getElementById('nplMatdiv').style.display = 'none';

	document.getElementById('nplMatdiv').style.display = 'none';
	document.getElementById('otherMatdiv1').style.display = 'none';
	document.getElementById('otherMatdiv3').style.display = 'none';
	document.getElementById('otherMatdiv4').style.display = 'none';
	document.getElementById('otherMatdiv5').style.display = 'none';
	document.getElementById('otherMatdiv6').style.display = 'none';
	document.getElementById('otherMatdiv7').style.display = 'none';
	document.getElementById('otherMatdiv8').style.display = 'none';
	document.getElementById('otherMatdiv9').style.display = 'none';
	

	$.getJSON("smc/addOtherMaterials.ajax?warehouseId="+wareHouseId, {ajax_command: "showOtherResourcesLink2",ajax_ieCacheFix:new Date().getTime() },
		function(json){ 
				//alert(json.otherMaterials.length);
				$("#otherMattable2 tr:gt(0)").remove();
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
							
					$('#otherMattable2').append(otherMattable);
			
				});
		}
	);

}
function show_OtherMaterials3() {

	var wareHouseId=escape($("#wareHouseId").val());
	var applicationId=$("#applicationId").val();	
	
	
	document.getElementById('otherMatdiv3').style.display = '';
	document.getElementById('otherResourcesdiv').style.display = 'none';
	document.getElementById('nplMatdiv').style.display = 'none';
	document.getElementById('otherMatdiv1').style.display = 'none';
	document.getElementById('otherMatdiv2').style.display = 'none';
	document.getElementById('otherMatdiv4').style.display = 'none';
	document.getElementById('otherMatdiv5').style.display = 'none';
	document.getElementById('otherMatdiv6').style.display = 'none';
	document.getElementById('otherMatdiv7').style.display = 'none';
	document.getElementById('otherMatdiv8').style.display = 'none';
	document.getElementById('otherMatdiv9').style.display = 'none';

	$.getJSON("smc/addOtherMaterials.ajax?warehouseId="+wareHouseId, {ajax_command: "showOtherResourcesLink3",ajax_ieCacheFix:new Date().getTime() },
		function(json){ 
				//alert(json.otherMaterials.length);
				$("#otherMattable3 tr:gt(0)").remove();
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
							
					$('#otherMattable3').append(otherMattable);
			
				});
		}
	);

}
function show_OtherMaterials4() {

	var wareHouseId=escape($("#wareHouseId").val());
	var applicationId=$("#applicationId").val();	
	
	
	document.getElementById('otherMatdiv4').style.display = '';
	document.getElementById('otherResourcesdiv').style.display = 'none';
	document.getElementById('nplMatdiv').style.display = 'none';
	document.getElementById('otherMatdiv1').style.display = 'none';
	document.getElementById('otherMatdiv2').style.display = 'none';
	document.getElementById('otherMatdiv3').style.display = 'none';
	document.getElementById('otherMatdiv5').style.display = 'none';
	document.getElementById('otherMatdiv6').style.display = 'none';
	document.getElementById('otherMatdiv7').style.display = 'none';
	document.getElementById('otherMatdiv8').style.display = 'none';
	document.getElementById('otherMatdiv9').style.display = 'none';
	

	$.getJSON("smc/addOtherMaterials.ajax?warehouseId="+wareHouseId, {ajax_command: "showOtherResourcesLink4",ajax_ieCacheFix:new Date().getTime() },
		function(json){ 
				//alert(json.otherMaterials.length);
				$("#otherMattable4 tr:gt(0)").remove();
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
							
					$('#otherMattable4').append(otherMattable);
			
				});
		}
	);

}
function show_OtherMaterials5() {

	var wareHouseId=escape($("#wareHouseId").val());
	var applicationId=$("#applicationId").val();	
	
	
	document.getElementById('otherMatdiv5').style.display = '';
	document.getElementById('otherResourcesdiv').style.display = 'none';
	document.getElementById('nplMatdiv').style.display = 'none';
	document.getElementById('otherMatdiv1').style.display = 'none';
	document.getElementById('otherMatdiv2').style.display = 'none';
	document.getElementById('otherMatdiv3').style.display = 'none';
	document.getElementById('otherMatdiv4').style.display = 'none';
	document.getElementById('otherMatdiv6').style.display = 'none';
	document.getElementById('otherMatdiv7').style.display = 'none';
	document.getElementById('otherMatdiv8').style.display = 'none';
	document.getElementById('otherMatdiv9').style.display = 'none';

	$.getJSON("smc/addOtherMaterials.ajax?warehouseId="+wareHouseId, {ajax_command: "showOtherResourcesLink5",ajax_ieCacheFix:new Date().getTime() },
		function(json){ 
				//alert(json.otherMaterials.length);
				$("#otherMattable5 tr:gt(0)").remove();
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
							
					$('#otherMattable5').append(otherMattable);
			
				});
		}
	);

}
function show_OtherMaterials6() {

	var wareHouseId=escape($("#wareHouseId").val());
	var applicationId=$("#applicationId").val();	
	
	
	document.getElementById('otherMatdiv6').style.display = '';
	document.getElementById('otherResourcesdiv').style.display = 'none';
	document.getElementById('nplMatdiv').style.display = 'none';
	document.getElementById('otherMatdiv1').style.display = 'none';
	document.getElementById('otherMatdiv2').style.display = 'none';
	document.getElementById('otherMatdiv3').style.display = 'none';
	document.getElementById('otherMatdiv4').style.display = 'none';
	document.getElementById('otherMatdiv5').style.display = 'none';
	document.getElementById('otherMatdiv7').style.display = 'none';
	document.getElementById('otherMatdiv8').style.display = 'none';
	document.getElementById('otherMatdiv9').style.display = 'none';

	$.getJSON("smc/addOtherMaterials.ajax?warehouseId="+wareHouseId, {ajax_command: "showOtherResourcesLink6",ajax_ieCacheFix:new Date().getTime() },
		function(json){ 
				//alert(json.otherMaterials.length);
				$("#otherMattable6 tr:gt(0)").remove();
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
							
					$('#otherMattable6').append(otherMattable);
			
				});
		}
	);

}

function show_OtherMaterials7() {

	var wareHouseId=escape($("#wareHouseId").val());
	var applicationId=$("#applicationId").val();	
	
	
	document.getElementById('otherMatdiv7').style.display = '';
	document.getElementById('otherResourcesdiv').style.display = 'none';
	document.getElementById('nplMatdiv').style.display = 'none';
	document.getElementById('otherMatdiv1').style.display = 'none';
	document.getElementById('otherMatdiv2').style.display = 'none';
	document.getElementById('otherMatdiv3').style.display = 'none';
	document.getElementById('otherMatdiv4').style.display = 'none';
	document.getElementById('otherMatdiv5').style.display = 'none';
	document.getElementById('otherMatdiv6').style.display = 'none';
	document.getElementById('otherMatdiv8').style.display = 'none';
	document.getElementById('otherMatdiv9').style.display = 'none';

	$.getJSON("smc/addOtherMaterials.ajax?warehouseId="+wareHouseId, {ajax_command: "showOtherResourcesLink7",ajax_ieCacheFix:new Date().getTime() },
		function(json){ 
				//alert(json.otherMaterials.length);
				$("#otherMattable7 tr:gt(0)").remove();
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
							
					$('#otherMattable7').append(otherMattable);
			
				});
		}
	);

}

function show_OtherMaterials8() {

	var wareHouseId=escape($("#wareHouseId").val());
	var applicationId=$("#applicationId").val();	
	
	
	document.getElementById('otherMatdiv8').style.display = '';
	document.getElementById('otherResourcesdiv').style.display = 'none';
	document.getElementById('nplMatdiv').style.display = 'none';
	document.getElementById('otherMatdiv1').style.display = 'none';
	document.getElementById('otherMatdiv2').style.display = 'none';
	document.getElementById('otherMatdiv3').style.display = 'none';
	document.getElementById('otherMatdiv4').style.display = 'none';
	document.getElementById('otherMatdiv5').style.display = 'none';
	document.getElementById('otherMatdiv6').style.display = 'none';
	document.getElementById('otherMatdiv7').style.display = 'none';
	
	document.getElementById('otherMatdiv9').style.display = 'none';
	

	$.getJSON("smc/addOtherMaterials.ajax?warehouseId="+wareHouseId, {ajax_command: "showOtherResourcesLink8",ajax_ieCacheFix:new Date().getTime() },
		function(json){ 
				//alert(json.otherMaterials.length);
				$("#otherMattable8 tr:gt(0)").remove();
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
							
					$('#otherMattable8').append(otherMattable);
			
				});
		}
	);

}
function show_OtherMaterials9() {

	var wareHouseId=escape($("#wareHouseId").val());
	var applicationId=$("#applicationId").val();	
	
	
	document.getElementById('otherMatdiv9').style.display = '';
	document.getElementById('otherResourcesdiv').style.display = 'none';
	document.getElementById('nplMatdiv').style.display = 'none';
	document.getElementById('otherMatdiv1').style.display = 'none';
	document.getElementById('otherMatdiv2').style.display = 'none';
	document.getElementById('otherMatdiv3').style.display = 'none';
	document.getElementById('otherMatdiv4').style.display = 'none';
	document.getElementById('otherMatdiv5').style.display = 'none';
	document.getElementById('otherMatdiv6').style.display = 'none';
	document.getElementById('otherMatdiv7').style.display = 'none';
	document.getElementById('otherMatdiv8').style.display = 'none';
	

	$.getJSON("smc/addOtherMaterials.ajax?warehouseId="+wareHouseId, {ajax_command: "showOtherResourcesLink9",ajax_ieCacheFix:new Date().getTime() },
		function(json){ 
				//alert(json.otherMaterials.length);
				$("#otherMattable8 tr:gt(0)").remove();
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
							
					$('#otherMattable9').append(otherMattable);
			
				});
		}
	);

}

function show_OtherResources() {

	//var wareHouseId=escape($("#wareHouseId").val());
	var applicationId=$("#applicationId").val();	
	//alert(applicationId);
	if(applicationId==-1){
		alert("Please select Reference Number");
		return;
	}
	document.getElementById('otherResourcesdiv').style.display = '';
	//document.getElementById('otherMatdiv').style.display = 'none';
	//document.getElementById('nplMatdiv').style.display = 'none';
	
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

function show_NPLMaterials() {

	//var wareHouseId=escape($("#wareHouseId").val());
	var applicationId=$("#applicationId").val();	
	//alert(applicationId);
	if(applicationId==-1){
		alert("Please select Reference Number");
		return;
	}
	//alert("hi");
	document.getElementById('nplMatdiv').style.display = '';
	//document.getElementById('otherResourcesdiv').style.display = 'none';
	//document.getElementById('otherMatdiv').style.display = 'none';

	
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
					$('#NPLMaterialsTable1').append(NPLMaterialsTable);
			
				});
		}
	);

}
function add_materials(resourType){

	addSelectedItems(resourType);


}

function hide_OtherMaterials1()
{
	document.getElementById('otherMatdiv1').style.display = 'none';
	
}
function hide_OtherMaterials2()
{
	document.getElementById('otherMatdiv2').style.display = 'none';
	
}
function hide_OtherMaterials3()
{
	document.getElementById('otherMatdiv3').style.display = 'none';
	
}
function hide_OtherMaterials4()
{
	document.getElementById('otherMatdiv4').style.display = 'none';
	
}
function hide_OtherMaterials5()
{
	document.getElementById('otherMatdiv5').style.display = 'none';
	
}
function hide_OtherMaterials6()
{
	document.getElementById('otherMatdiv6').style.display = 'none';
	
}
function hide_OtherMaterials7()
{
	document.getElementById('otherMatdiv7').style.display = 'none';
	
}

function hide_OtherMaterials8()
{
	document.getElementById('otherMatdiv8').style.display = 'none';
	
}


function hide_NPLResources()
{
	document.getElementById('nplMatdiv').style.display = 'none';
	
}


function onNumChangePegId(pegId){


selectedpegItem = pegId.value;
}

function clearFormDetails(){
	
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
	$("#save").attr("disabled", "disabled");
	$("#estimateNo").val("");
	$("#applicationId").val(-1);
	$("#estimateDetails tr:gt(0)").remove();
}

function onNumChange(quan,pegItemId,resourceCode){
//alert(quan.value);
	   if(!(quan.value > 0) || quan.value < 0){
	       
	       alert("Please enter valid Quantity");
	       quan.value = '';
	       return;
	    } else {
	     
			$.getJSON("smc/getMasterUpdateDetails.ajax?lineid="+resourceCode+"&quantity="+quan.value+"&pegItemId="+pegItemId, {
				ajax_command: "getMasterUpdateDetails",ajax_ieCacheFix:new Date().getTime() },
				function(json){ 
					//if(json.message){
					    //invalidStatus = "true";
					//	alert(json.statusMessage);
					//	return;
					//}	
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
								'<td id="uom'+estimateDetail.selectedPegId+'" align="left">'+estimateDetail.uom+'</td>'+
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

function validateQuantity(quan){

   if(!(quan.value > 0) || quan.value < 0){
       
       alert("Please enter valid Quantity");
       quan.value = '';
       return;
    }
}	
function validateName(name){

	   if(!(name.value.length == 0)){
	       
	       alert("Please enter valid item description");
	       name.value = '';
	       return;
	    }
	}
function addMasterDetails(){
	//clearPeggingScheduleForm();
	$("#allMasterDetails tr:gt(0)").remove();
	$("#viewMasterDetails tr:gt(0)").remove();
	var selectedID=nodeId;
	var selectedIDValue = nodeText;
	if(selectedID ==''){
		alert("Please Select Items!!!...");
		
       return;
	}
	//document.getElementById('addNewMaster').style.display = '';
	 
	 
	//alert(document.getElementById('itemquantity').value);
	
    	   
			$.getJSON("smc/viewPegMasterDetails.ajax?selectedPegId="+selectedID+"&selectedPegText="+selectedIDValue, {
				ajax_command: "viewPegMasterDetails",ajax_ieCacheFix:new Date().getTime() },
				function(json){ 
					
					$.each(json.masterDetails,function(i,masterDetails){
						//alert(masterDetails.resourceCode);
						//alert(json.needToRemovePreRows);
					//if(estimateDetail.needToRemovePreRows != null){
						//$('#lineseciid'+estimateDetail.needToRemovePreRows).remove();
						//alert($('#lineseciid'+estimateDetail.needToRemovePreRows));
					//}					
					var masterDetails = '<tr  id="lineseciid'+masterDetails.resourceCode+'">'+	
									//'<td id="radio'+estimateDetail.selectedPegId+'" align="middle" ><input type="radio" class="required" style="text-align: right" name="pegradio" id="'+estimateDetail.selectedPegId+'"  value="'+estimateDetail.selectedPegId+'"  /></td>'+																							
									//'<td id="resourceType'+estimateDetail.selectedPegId+estimateDetail.resourceCode+'" align="middle" >'+estimateDetail.resourceType+'</td>'+
									'<td id="resourceCode'+masterDetails.resourceCode+'" align="left" >'+masterDetails.resourceCode+'</td>'+
									'<td id="resourceName'+masterDetails.resourceCode+'" align="left" >'+masterDetails.resourceName+'</td>'+
									'<td id="uom'+masterDetails.resourceCode+'" align="left" >'+masterDetails.uom+'</td>'+
									'<td id="unitPrice'+masterDetails.resourceCode+'" align="left" >'+masterDetails.unitPrice+'</td>'+
									'<td id="estimateQuantity'+masterDetails.resourceCode+'" align="left" ><input type="text" class="required" style="text-align: right;color: green;" name="'+masterDetails.estimateQuantity+'" id="'+masterDetails.estimateQuantity+'"  maxlength="10" size="10"  value="'+masterDetails.estimateQuantity+'" onchange="onNumChange(this,'+' \''+masterDetails.selectedPegId+'\''+',\'' + masterDetails.resourceCode + '\')"  /></td>'+
									
									'<td width=\'25px\' align=\'center\'><image onclick="deleteDetailLine(\'' + masterDetails.resourceCode + '\'' +',\'' + masterDetails.selectedPegId+'\')" src="../../image/X.png" /></td>'+

									'</tr>';
								
								$('#allMasterDetails').append(masterDetails);
						
					}
					
				);
					
					 
			}
		);
		//catculateTotalCost();
				
	

}
function addPegMaterials(){
	
	clearItemsMaster();
	
	

}
function addPegItem(){
	var itemName=$("#itemName").val();	
	
	var selectedID=nodeId;
	var selectedIDValue = nodeText;
	
	document.getElementById('addedPegInfo').style.display = '';
	  if(selectedID ==''){
		alert("Please Select Items!!!...");
		
       return;
	  }
	  if( document.getElementById('itemName').value ==''){
		alert("Please Enter Item Descrioption!!!...");
		
       return;
	  }
	 nodeId='';
	 nodeText='';
	 var invalidStatus = "false";
	//alert(document.getElementById('itemquantity').value);
	
    	   
			$.getJSON("sps/addNewPegItem.ajax?selectedPegId="+selectedID+"&selectedPegText="+selectedIDValue+"&itemName="+document.getElementById('itemName').value, {
				ajax_command: "addNewPegItem",ajax_ieCacheFix:new Date().getTime() },
				function(json){ 
					setTimeout("location.reload(true);",1500);
					if(json.message){
					    invalidStatus = "true";
						alert(json.statusMessage);
						return;
					}	
				
					
			}
		);
		//catculateTotalCost();
				
	

}
function modifyPegItem(){
	var itemName=$("#itemName").val();	
	
	var selectedID=nodeId;
	var selectedIDValue = nodeText;
	
	document.getElementById('addedPegInfo').style.display = '';
	  if(selectedID ==''){
		alert("Please Select Items!!!...");
		
       return;
	  }
	  if( document.getElementById('itemName').value ==''){
		alert("Please Enter Item Descrioption!!!...");
		
       return;
	  }
	 nodeId='';
	 nodeText='';
	 var invalidStatus = "false";
	//alert(document.getElementById('itemquantity').value);
	var status= confirm("Do you really want to update description of this node?");
	 if (status== true){
    	   
			$.getJSON("sps/addNewPegItem.ajax?selectedPegId="+selectedID+"&selectedPegText="+selectedIDValue+"&itemName="+document.getElementById('itemName').value, {
				ajax_command: "modifyPegItem",ajax_ieCacheFix:new Date().getTime() },
				function(json){ 
					setTimeout("location.reload(true);",1500);
					if(json.message){
					    invalidStatus = "true";
						alert(json.statusMessage);
						return;
					}	
				
					
			}
		);
	 }
		//catculateTotalCost();
				
	

}
function deletePegItem(){
	var itemName=$("#itemName").val();	
	
	var selectedID=nodeId;
	var selectedIDValue = nodeText;
	
	document.getElementById('addedPegInfo').style.display = '';
	  if(selectedID ==''){
		alert("Please Select Items!!!...");
		
       return;
	  }
	  
	 nodeId='';
	 nodeText='';
	 var invalidStatus = "false";
	//alert(document.getElementById('itemquantity').value);
	
    	   var status= confirm("Do you really want to delete this node?");
	 		if (status== true){
					$.getJSON("sps/addNewPegItem.ajax?selectedPegId="+selectedID+"&selectedPegText="+selectedIDValue+"&itemName="+document.getElementById('itemName').value, {
						ajax_command: "deletePegItem",ajax_ieCacheFix:new Date().getTime() },
						function(json){ 
							setTimeout("location.reload(true);",1500);
							if(json.message){
							    invalidStatus = "true";
								alert(json.statusMessage);
								return;
							}	
						
							
					}
				);
	 		}
		//catculateTotalCost();
				
	

}
function catculateTotalCost(){
	
		var rows =$('#estimateDetails tr');
		var rowcount = rows.length;
		var calculatedCost=0;	
		var lineCost =0;
		alert(rows);
	  $("#estimateDetails tbody tr input[name$='estimateCost']").each(function() {
		  lineCost = $(this).val();
	
		  calculatedCost=parseFloat(isNaN(calculatedCost) ? 0 : calculatedCost) + parseFloat(isNaN(lineCost) ? 0 : lineCost);
			 
			 
	   });
	   if(!isNaN(calculatedCost)){
	  
				// $("#totalCost").text(calculatedCost);
				 $("#totalCost").val(calculatedCost);
				 
			 }
			  //alert(calculatedCost);
}


function deleteDetailLine(lineid,pegItemId){
//alert(resourceType);
	var status= confirm("Do you really want to remove this ?");
	 if (status== true){

		$('#lineseciid'+lineid).remove();
	
		$.getJSON("smc/deleteMasterPegDetailLine.ajax?selectedItems="+lineid+"&pegItemId="+pegItemId, {
		ajax_command: "deleteMasterPegDetailLine",ajax_ieCacheFix:new Date().getTime() },
			function(json){ 
		
				if(json.message){
				    //invalidStatus = "true";
					alert(json.statusMessage);
					return;
				}	
			}
		);	
			
	 }
	 else{
	  	return;
	 }
	  			
	
	
}
function viewMasterDetails(){
	
	var selectedID=selectedpegItem;
	
	document.getElementById('viewMaster').style.display = '';
	//alert(selectedID);
    	    if(selectedID !=''){
				$.getJSON("smc/viewMasterDetails.ajax?selectedPegId="+selectedID, {
				ajax_command: "viewPegMasterDetails",ajax_ieCacheFix:new Date().getTime() },
				function(json){ 
					var myCell = $('#viewMasterDetails');
								//alert(json.description);
								$("#viewMasterDetails tr:gt(0)").remove();
						$.each(json.masterDetails,function(i,masterDetail){
						//$('#linesecid'+i).remove();
							//alert(masterDetail.selectedPegId);
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
								
					$('#viewMasterDetails').append(masterDetails);

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
	$("#resourceCodes select option").remove();
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
		//$('#tollerance').val("");	
		$('#estiQuantity').val("");	
		$('#totalCost').val("");
		//$('#resourceCodeId').val("");
			
		$("#resourceCodes select option").remove();
	//alert(resourceType.value);
		//var resourceTypeId=resourceType.value;	
		var resourceTypeId=escape($("#resourceTypeId").val());	
		var resourceCodeId=escape($("#resourceCodeId").val());	
		$.getJSON("smc/loadResourceCodes.ajax?resourceTypeId="+resourceTypeId+"&resourceCodeId="+resourceCodeId, {
		ajax_command: "loadResourceCodes",ajax_ieCacheFix:new Date().getTime() },
		function(json){ 
				
			//$('#lineSectypeId').val(json.lineSectypeId);				
			$('#resourceName').val(json.resourceName);	
			$('#unitPrice').val(json.unitPrice);
			$('#categoryCode').val(json.categoryCode);
			
			$('#estiQuantity').val(json.estimateQuantity);	
			//$('#totalCost').val(json.totalCost);
			
			if (json.jsonarrayResourceCodes != null && json.jsonarrayResourceCodes.length > 0) {
				for (x = 0; x < json.jsonarrayResourceCodes.length; x++) {
				 
					$("#resourceCodes select").append($('<option></option>').val(json.jsonarrayResourceCodes[x].id).html(json.jsonarrayResourceCodes[x].name));
				}
				$('#resourceCodeId').val(json.resourceCode);
			}

			});

	}
function loadResourceData(){

	$('#resourceName').val("");	
	$('#unitPrice').val("");
	//$('#tollerance').val("");	
	$('#estiQuantity').val("");	
	$('#totalCost').val("");
	//$('#resourceCodeId').val("");
		
	//$("#resourceCodes select option").remove();
//alert(resourceType.value);
	//var resourceTypeId=resourceType.value;	
	var resourceTypeId=escape($("#resourceTypeId").val());	
	var resourceCodeId=escape($("#resourceCodeId").val());	
	$.getJSON("smc/loadResourceDetails.ajax?resourceTypeId="+resourceTypeId+"&resourceCodeId="+resourceCodeId, {
	ajax_command: "loadResourceDetails",ajax_ieCacheFix:new Date().getTime() },
	function(json){ 
			
		//$('#lineSectypeId').val(json.lineSectypeId);				
		$('#resourceName').val(json.resourceName);	
		$('#unitPrice').val(json.unitPrice);
		$('#categoryCode').val(json.categoryCode);
		
		//$('#tollerance').val(json.tolerance);	
		$('#estiQuantity').val(json.estimateQuantity);	
		//$('#totalCost').val(json.totalCost);
		
		if (json.jsonarrayResourceCodes != null && json.jsonarrayResourceCodes.length > 0) {
			for (x = 0; x < json.jsonarrayResourceCodes.length; x++) {
			 
				$("#resourceCodes select").append($('<option></option>').val(json.jsonarrayResourceCodes[x].id).html(json.jsonarrayResourceCodes[x].name));
			}
			$('#resourceCodeId').val(json.resourceCode);
		}

		});

}

function addSelectedItems(resourType){
	var selectedID=nodeId;
	var selectedIDValue = nodeText;
	if(selectedID ==''){
		alert("Please Select Items!!!...");
		
       return;
	  }
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
		$.getJSON("smc/addMasters.ajax?selectedItems="+escape(selectedItemsList)+"&func=add"+"&resourceType="+escape(resourType)+"&selectedPegId="+selectedID+"&selectedPegText="+selectedIDValue, {
		ajax_command: "addMasters",ajax_ieCacheFix:new Date().getTime() },
		function(json){ 
			
				//var pkgTable2 = '<tr  id="totalcostRow">'+		
					//		'<td align="middle" colspan="5" ><b>TOTAL COST<b></td><td id="totalestimatedcost"><input type="text" class="required" style="text-align: right" name="estimatedcost" id="estimatedcost"  maxlength="10" size="10" value="" readonly="true"/></td></tr>';
					var  cell=	 $('#estimateDetails');
					//alert(cell);
					
					if(json.invalid){	
					    invalidStatus = "true";
						alert(json.errorMessage);
						return;
					}	

					
				$.each(json.addedMaterials,function(i,estimateDetails){
					
					//$('#totalcostRow').remove();
								//alert(json.addedMaterials.length);						
					var estimateDetails1 = '<tr  id="lineseciid'+estimateDetails.resourceCode+'">'+	
					//'<td id="radio'+estimateDetails.selectedPegId+'" align="middle" ><input type="radio" class="required" style="text-align: right" name="pegradio" id="'+estimateDetails.selectedPegId+'"  value="'+estimateDetails.selectedPegId+'"  /></td>'+																							
					//'<td id="resourceType'+estimateDetails.selectedPegId+'" align="middle" >'+estimateDetails.resourceType+'</td>'+
					'<td id="resourceCode'+estimateDetails.selectedPegId+'" align="left" >'+estimateDetails.resourceCode+'</td>'+
					'<td id="resourceName'+estimateDetails.selectedPegId+'" align="left" >'+estimateDetails.resourceName+'</td>'+
					'<td id="uom'+estimateDetails.selectedPegId+'" align="left" >'+estimateDetails.uom+'</td>';
					//'<td id="unitPrice'+estimateDetails.selectedPegId+'" align="middle" >'+estimateDetails.unitPrice+'</td>';
					
					var estimateDetailsOtherResource ='<td id="unitPrice'+estimateDetails.selectedPegId+'" align="left" ><input type="text" class="required" style="text-align: right;color: green;" name="'+estimateDetails.unitPrice+'" id="'+estimateDetails.unitPrice+'"  maxlength="10" size="10" value="" onchange="onNumChange(this,'+'\''+estimateDetails.selectedPegId+'\''+',\'' + estimateDetails.resourceCode + '\')" /></td>'+
					'<td id="estimateQuantity'+estimateDetails.selectedPegId+'" align="left" ><input type="text" class="required" style="text-align: right;color: green;" name="'+estimateDetails.estimateQuantity+'" id="'+estimateDetails.estimateQuantity+'"  maxlength="10" size="10" value="1"  onchange="onNumChange(this,'+'\''+estimateDetails.selectedPegId+'\''+',\'' + estimateDetails.resourceCode + '\')" /></td>'+
						//'<td align="left" ><input type="text" class="required" style="text-align: right;color: green;" name="estimateCost"  id="estimateCost'+estimateDetails.selectedPegId+'"  maxlength="10" size="10" value="" onchange="onNumChange(this,'+estimateDetails.unitPrice+',\'' + estimateDetails.selectedPegId + '\')" /></td>'+
						'<td width=\'25px\' align=\'center\'><image onclick="deleteDetailLine(\'' + estimateDetails.resourceCode + '\'' +',\'' + estimateDetails.selectedPegId+'\')" src="../../image/X.png" /></td>'+

						'</tr>';
					
					var estimateDetailsOther ='<td id="unitPrice'+estimateDetails.selectedPegId+'" align="left" >'+estimateDetails.unitPrice+'</td>'+
					'<td id="estimateQuantity'+estimateDetails.selectedPegId+'" align="left" ><input type="text" class="required" style="text-align: right;color: green;" name="'+estimateDetails.estimateQuantity+'" id="'+estimateDetails.estimateQuantity+'"  maxlength="10" size="10" value="" onchange="onNumChange(this,'+'\''+estimateDetails.selectedPegId+'\''+',\'' + estimateDetails.resourceCode + '\')" /></td>'+
						//'<td align="left" ><input type="text" class="required" style="text-align: right;color: green;" readOnly="true" name="estimateCost"  id="estimateCost'+estimateDetails.selectedPegId+'"  maxlength="10" size="10" value=""  onchange="onNumChange(this,'+estimateDetails.unitPrice+',\'' + estimateDetails.selectedPegId + '\')"/></td>'+
						'<td width=\'25px\' align=\'center\'><image onclick="deleteDetailLine(\'' + estimateDetails.resourceCode + '\'' +',\'' + estimateDetails.selectedPegId+'\')" src="../../image/X.png" /></td>'+

						'</tr>';
					
					if(resourType=='otherMat'){
						$('#allMasterDetails').append(estimateDetails1+estimateDetailsOther);
						
					}else{
					
						$('#allMasterDetails').append(estimateDetails1+estimateDetailsOtherResource);
					}
					
				
					//$('#estimateDetails').append(estimateDetails1);
					/*if(resourType=='otherMat'){
					
						$('#otherMaterials'+escape(estimateDetails.selectedPegId)).remove();
					}else if(resourType=='otherResource'){
					//alert("hi");
						$('#otherResource'+escape(estimateDetails.selectedPegId)).remove();
					}else if(resourType=='NPL'){
						$('#nplMaterials'+escape(estimateDetails.selectedPegId)).remove();
					}*/
					
				});
				
				
			}
		);
 }else{
	alert("Please Select Items!!!...");
 }
	   
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
						<s:form id="frmEstimate" name="frmEstimate" action="masterLoadPeggingPage" method="POST"
										validate="true" theme="simple">
						  		<table align="center" class="tldFromContent1" width="100%">
						  			
								
									<tr>
									<td  width="25%">&nbsp;&nbsp;&nbsp;&nbsp;</td>
									<td  width="25%">&nbsp;&nbsp;&nbsp;&nbsp;</td>
									<td  width="25%">&nbsp;&nbsp;&nbsp;&nbsp;</td>
									<td  width="25%">&nbsp;&nbsp;&nbsp;&nbsp;</td>
									<td  width="25%">&nbsp;&nbsp;&nbsp;&nbsp;</td>
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
									</s:form>
								</table>
						
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
										Name : <input type="text" name="itemName" id="itemName" value="" />
									</td>
									<td>
									<input type="button" name="add" id="add" value="Add Item" onclick="addPegItem();"/>
									</td>
									<td>
									<input type="button" name="modify" id="modify" value="Update Item" onclick="modifyPegItem();"/>
									<input type="button" name="delete" id="delete" value="Delete Item" onclick="deletePegItem();"/>
									<s:submit theme="simple" value="Exit" method="close" id="btnClose" ></s:submit>
									</td>
								</tr>
							</table>
							<table>
								<tr>
									<td>
										
									</td>
									<td>
									<input type="button" name="addMaster" id="addMaster" value="Add New Master" onclick="addMasterDetails();"/>
									</td>
									<td>
									<input type="button" name="add" id="add" value="View Masters" onclick="viewMasterDetails();"/>
									</td>
									<td>
									<input type="button" name="Add" id="Add" value="Add" onclick="addPegMaterials()"/>
									</td>
								</tr>
							</table>
							<br>
							
							
								<div style="width:50%;float: left;display: none" id="viewMaster">

									<table  class="tldFromContent1" id="viewMasterDetails" align="left"  border="1">
									<tr id="header1">
									<th><b>&nbsp;&nbsp;Res Type&nbsp;&nbsp;</b></th> <th><b>&nbsp;&nbsp;Res CD&nbsp;&nbsp;</b></th><th><b>&nbsp;&nbsp;Res Name&nbsp;&nbsp;</b></th><th><b>&nbsp;&nbsp;Estimated Quantity&nbsp;&nbsp;</b></th><th><b>&nbsp;&nbsp;UOM&nbsp;&nbsp;</b></th></tr>
									<tr id="linesecid"><td></td><td id="resType"></td><td id="resCD"></td><td id="resName"></td><td id="estimatedQuantity"></td><td id="uom"></td></tr>
					  
									</table>
								</div>
										<br><br><br><br><br><br>
							
							<div id="addNewMaster" style="width:50%;float: left;display: none">
								<table>
									<tr>
										<td width="20%">Resource Types</td>
										<td width="25%">
										<div id="resourceTypes"><select name="resourceTypeId" id="resourceTypeId" onchange="loadEstimateData()"><option value="-1">--Select--</option></select></div>
									
										</td>
									</tr>
									<tr>
										<td width="20%">Resource Codes</td>
										<td width="25%">
										<div id="resourceCodes"><select name="resourceCodeId" id="resourceCodeId" onchange="loadResourceData()"><option value="-1">--Select--</option></select></div>
									
										</td>
									</tr>
									<tr>
										<td width="20%">Resource Name</td>
										<td width="25%">
											<input type="text" name="resourceName" id="resourceName" value="" readOnly="true"/>
										</td>
									</tr>
									<tr>
										<td width="20%">Resource Category Code</td>
										<td width="25%">
											<input type="text" name="categoryCode" id="categoryCode" value="" readOnly="true"/>
										</td>
									</tr>
									<tr>
										<td width="20%">Unit Price</td>
										<td width="25%">
											<input type="text" name="unitPrice" id="unitPrice" value="" />
										</td>
									</tr>
									<tr>
										<td width="20%">uom</td>
										<td><s:select name="uom" id="uom" list="#{'NO':'NO','MTR':'MTR','KG':'KG','SET':'SET'}" /></td>
									</tr>
									<tr>
										<td width="20%">Quantity</td>
										<td width="25%">
											<input type="text" name="estiQuantity" id="estiQuantity" value="" />
										</td>
									</tr>
									<tr>
										<td width="20%"></td>
										<td width="25%">
											
										</td>
									</tr>
								</table>
							
							</div>
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
									<a id="showLink1" href="javascript:show_OtherMaterials1()">Add Materials1</a> 
									
									<a id="showLink2" href="javascript:show_OtherMaterials2()">Add Materials2</a> 
								</td>
								
								<td>
									<a id="showLink3" href="javascript:show_OtherMaterials3()">Add Materials3</a>
									 
									<a id="showLink4" href="javascript:show_OtherMaterials4()">Add Materials4</a> 
								</td>
								<td>
									<a id="showLink5" href="javascript:show_OtherMaterials5()">Add Materials5</a>
									
									<a id="showLink6" href="javascript:show_OtherMaterials6()">Add Materials6</a>
									
									<a id="showLink7" href="javascript:show_OtherMaterials7()">Add Materials7</a>
									
									<a id="showLink8" href="javascript:show_OtherMaterials8()">Add Materials8</a>    
									
									<a id="showLink9" href="javascript:show_OtherResources()">Add Materials9</a>
									
									<a id="showLink10" href="javascript:show_NPLMaterials()">ADD NPL Materials</a> 
								</td>
								
							</tr>
							<tr >
								<td colspan="3">
									<!--  <input type="button" name="new" id="new" value="Add New Estimate Entry" onclick="showAddJobInfo();"/>-->
									<table  class="tldFromContent1" id="allMasterDetails"  style="display:block;" border="1"> 
							  			<tr id="header1">
											 <th><b>Resource CD&nbsp;&nbsp;</b></th><th><b>Resource Name&nbsp;&nbsp;</b></th><th><b>UOM&nbsp;&nbsp;</b></th><th><b>Unit Price&nbsp;&nbsp;</b></th><th><b>Estimated Quantity&nbsp;&nbsp;</b></th><th></th></tr>
							 			<tr id="linesecid"><td id="resCD"></td><td id="resName"></td><td id="estimatedQuantity"></td></tr>		
									</table>
								</td>
							</tr>	
							<tr>
								<td colspan="3">
								
								
									<div id="otherMatdiv1" style="display:none">
										<table class="tldFromContent1" width="100%">							
											<tr>
												<td>
												<s:a id="addLink" href="javascript:add_materials('otherMat')">Add to Resource List</s:a>&nbsp;&nbsp;&nbsp;<a href="javascript:hide_OtherMaterials1()">Hide List</a>
											  		<table id="otherMattable1" align="center" border="1">
											  			<tr id="header2"><th></th> 
															<th>Resource Code</th><th>Resource Name</th> <th>UOM</th> <th>Unit Price</th></tr>
											 			<tr id="resourceid"><td></td><td id="resourceName"></td><td id="uom"></td><td id="unitPrice"></td><td id="estimatedCost"></td>
											  				<td id="totalcost"></td></tr>
											  
													</table>
												</td>
											</tr>
										</table>
							
									<s:a id="addLink" href="javascript:add_materials('otherMat')">Add to Resource List</s:a>&nbsp;&nbsp;&nbsp;<a href="javascript:hide_OtherMaterials1()">Hide List</a>
									</div>
									
									<div id="otherMatdiv2" style="display:none">
										<table class="tldFromContent1" width="100%">							
											<tr>
												<td>
												<s:a id="addLink" href="javascript:add_materials('otherMat')">Add to Resource List</s:a>&nbsp;&nbsp;&nbsp;<a href="javascript:hide_OtherMaterials2()">Hide List</a>
											  		<table id="otherMattable2" align="center" border="1">
											  			<tr id="header2"><th></th> 
															<th>Resource Code</th><th>Resource Name</th> <th>UOM</th> <th>Unit Price</th></tr>
											 			<tr id="resourceid"><td></td><td id="resourceName"></td><td id="uom"></td><td id="unitPrice"></td><td id="estimatedCost"></td>
											  				<td id="totalcost"></td></tr>
											  
													</table>
												</td>
											</tr>
										</table>
							
									<s:a id="addLink" href="javascript:add_materials('otherMat')">Add to Resource List</s:a>&nbsp;&nbsp;&nbsp;<a href="javascript:hide_OtherMaterials2()">Hide List</a>
									</div>
									
									<div id="otherMatdiv3" style="display:none">
										<table class="tldFromContent1" width="100%">							
											<tr>
												<td>
												<s:a id="addLink" href="javascript:add_materials('otherMat')">Add to Resource List</s:a>&nbsp;&nbsp;&nbsp;<a href="javascript:hide_OtherMaterials3()">Hide List</a>
											  		<table id="otherMattable3" align="center" border="1">
											  			<tr id="header2"><th></th> 
															<th>Resource Code</th><th>Resource Name</th> <th>UOM</th> <th>Unit Price</th></tr>
											 			<tr id="resourceid"><td></td><td id="resourceName"></td><td id="uom"></td><td id="unitPrice"></td><td id="estimatedCost"></td>
											  				<td id="totalcost"></td></tr>
											  
													</table>
												</td>
											</tr>
										</table>
							
									<s:a id="addLink" href="javascript:add_materials('otherMat')">Add to Resource List</s:a>&nbsp;&nbsp;&nbsp;<a href="javascript:hide_OtherMaterials3()">Hide List</a>
									</div>
									
									<div id="otherMatdiv4" style="display:none">
										<table class="tldFromContent1" width="100%">							
											<tr>
												<td>
												<s:a id="addLink" href="javascript:add_materials('otherMat')">Add to Resource List</s:a>&nbsp;&nbsp;&nbsp;<a href="javascript:hide_OtherMaterials4()">Hide List</a>
											  		<table id="otherMattable4" align="center" border="1">
											  			<tr id="header2"><th></th> 
															<th>Resource Code</th><th>Resource Name</th> <th>UOM</th> <th>Unit Price</th></tr>
											 			<tr id="resourceid"><td></td><td id="resourceName"></td><td id="uom"></td><td id="unitPrice"></td><td id="estimatedCost"></td>
											  				<td id="totalcost"></td></tr>
											  
													</table>
												</td>
											</tr>
										</table>
							
									<s:a id="addLink" href="javascript:add_materials('otherMat')">Add to Resource List</s:a>&nbsp;&nbsp;&nbsp;<a href="javascript:hide_OtherMaterials4()">Hide List</a>
									</div>
									
									<div id="otherMatdiv5" style="display:none">
										<table class="tldFromContent1" width="100%">							
											<tr>
												<td>
												<s:a id="addLink" href="javascript:add_materials('otherMat')">Add to Resource List</s:a>&nbsp;&nbsp;&nbsp;<a href="javascript:hide_OtherMaterials5()">Hide List</a>
											  		<table id="otherMattable5" align="center" border="1">
											  			<tr id="header2"><th></th> 
															<th>Resource Code</th><th>Resource Name</th> <th>UOM</th> <th>Unit Price</th></tr>
											 			<tr id="resourceid"><td></td><td id="resourceName"></td><td id="uom"></td><td id="unitPrice"></td><td id="estimatedCost"></td>
											  				<td id="totalcost"></td></tr>
											  
													</table>
												</td>
											</tr>
										</table>
							
									<s:a id="addLink" href="javascript:add_materials('otherMat')">Add to Resource List</s:a>&nbsp;&nbsp;&nbsp;<a href="javascript:hide_OtherMaterials5()">Hide List</a>
									</div>
									
									<div id="otherMatdiv6" style="display:none">
										<table class="tldFromContent1" width="100%">							
											<tr>
												<td>
												<s:a id="addLink" href="javascript:add_materials('otherMat')">Add to Resource List</s:a>&nbsp;&nbsp;&nbsp;<a href="javascript:hide_OtherMaterials6()">Hide List</a>
											  		<table id="otherMattable6" align="center" border="1">
											  			<tr id="header2"><th></th> 
															<th>Resource Code</th><th>Resource Name</th> <th>UOM</th> <th>Unit Price</th></tr>
											 			<tr id="resourceid"><td></td><td id="resourceName"></td><td id="uom"></td><td id="unitPrice"></td><td id="estimatedCost"></td>
											  				<td id="totalcost"></td></tr>
											  
													</table>
												</td>
											</tr>
										</table>
							
									<s:a id="addLink" href="javascript:add_materials('otherMat')">Add to Resource List</s:a>&nbsp;&nbsp;&nbsp;<a href="javascript:hide_OtherMaterials7()">Hide List</a>
									</div>
									<div id="otherMatdiv7" style="display:none">
										<table class="tldFromContent1" width="100%">							
											<tr>
												<td>
												<s:a id="addLink" href="javascript:add_materials('otherMat')">Add to Resource List</s:a>&nbsp;&nbsp;&nbsp;<a href="javascript:hide_OtherMaterials7()">Hide List</a>
											  		<table id="otherMattable7" align="center" border="1">
											  			<tr id="header2"><th></th> 
															<th>Resource Code</th><th>Resource Name</th> <th>UOM</th> <th>Unit Price</th></tr>
											 			<tr id="resourceid"><td></td><td id="resourceName"></td><td id="uom"></td><td id="unitPrice"></td><td id="estimatedCost"></td>
											  				<td id="totalcost"></td></tr>
											  
													</table>
												</td>
											</tr>
										</table>
							
									<s:a id="addLink" href="javascript:add_materials('otherMat')">Add to Resource List</s:a>&nbsp;&nbsp;&nbsp;<a href="javascript:hide_OtherMaterials8()">Hide List</a>
									</div>
									<div id="otherMatdiv8" style="display:none">
										<table class="tldFromContent1" width="100%">							
											<tr>
												<td>
												<s:a id="addLink" href="javascript:add_materials('otherMat')">Add to Resource List</s:a>&nbsp;&nbsp;&nbsp;<a href="javascript:hide_OtherMaterials8()">Hide List</a>
											  		<table id="otherMattable8" align="center" border="1">
											  			<tr id="header2"><th></th> 
															<th>Resource Code</th><th>Resource Name</th> <th>UOM</th> <th>Unit Price</th></tr>
											 			<tr id="resourceid"><td></td><td id="resourceName"></td><td id="uom"></td><td id="unitPrice"></td><td id="estimatedCost"></td>
											  				<td id="totalcost"></td></tr>
											  
													</table>
												</td>
											</tr>
										</table>
							
									<s:a id="addLink" href="javascript:add_materials('otherMat')">Add to Resource List</s:a>&nbsp;&nbsp;&nbsp;<a href="javascript:hide_OtherMaterials9()">Hide List</a>
									</div>
									<div id="otherMatdiv9" style="display:none">
										<table class="tldFromContent1" width="100%">							
											<tr>
												<td>
												<s:a id="addLink" href="javascript:add_materials('otherMat')">Add to Resource List</s:a>&nbsp;&nbsp;&nbsp;<a href="javascript:hide_OtherMaterials9()">Hide List</a>
											  		<table id="otherMattable9" align="center" border="1">
											  			<tr id="header2"><th></th> 
															<th>Resource Code</th><th>Resource Name</th> <th>UOM</th> <th>Unit Price</th></tr>
											 			<tr id="resourceid"><td></td><td id="resourceName"></td><td id="uom"></td><td id="unitPrice"></td><td id="estimatedCost"></td>
											  				<td id="totalcost"></td></tr>
											  
													</table>
												</td>
											</tr>
										</table>
							
									<s:a id="addLink" href="javascript:add_materials('otherMat')">Add to Resource List</s:a>&nbsp;&nbsp;&nbsp;<a href="javascript:hide_OtherMaterials9()">Hide List</a>
									</div>
									<div id="otherMatdiv10" style="display:none">
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
						<div id="nplMatdiv" style="display:none">
										<table class="tldFromContent1" width="100%">							
											<tr>
												<td>
												<s:a id="addLink" href="javascript:add_materials('NPL')">Add to Resource List</s:a>&nbsp;&nbsp;&nbsp;<a href="javascript:hide_NPLResources()">Hide List</a>
											  		<table id="NPLMaterialsTable1" align="center" border="1">
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

 