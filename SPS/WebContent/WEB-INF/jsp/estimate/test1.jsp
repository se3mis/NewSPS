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
<script type="text/javascript">
var estmatedCost = '';
$(document).ready(function()
{
	load_applicationNumbers();
});
function add_materials() 
{
	addSelectedItems();
	
}

function hide_list()
{
	document.getElementById('otherMat').style.display = 'none';
	
}

function showNorms()
{
	document.getElementById('otherMat').style.display = 'none';
	
}

function onNumChange(obj,obj1,obj2){
//alert(obj2);
//$('#pkgtable tr');
//$(function(){$("#pkgtable tr").focus(function(e) {
//alert("hi");
//	var attrId = $(this).attr('id');
//alert($("#quantity0").val())
//alert()
//});});

	
   if(!(obj.value > 0) || obj.value < 0){
       
       alert("Please enter valid Quantity");
        obj.value = '';
        return;
    }else{
	estmatedCost = obj.value*obj1;
	$("#totalcost"+obj2).val(estmatedCost)
	
		var rows =$('#pkgtable tr');
		var rowcount = rows.length;
		var numVal1=0;	
		$.each(rows, function(i,row) {
		//alert($("#totalcost"+i).val() );
		//if(i<=obj2){
		//alert(document.getElementById("totalcost"+i).value);
		//alert(document.getElementById("totalcost"+i).value);
		if(!document.getElementById("totalcost"+i).value==''){
			numVal1= numVal1+ parseInt(document.getElementById("totalcost"+i).value);
		}
		//var numVal2=parseInt(document.getElementById("one").value);
			//value = value+ $("#totalcost"+i).val();
			//
			
			$("#totalestimatedcost").text(numVal1);
			//}
		});
		//$('#description').text(json.scanStatus);
	
	
		//alert("hi");
				//$.getJSON("smc/calculateCost.ajax?quantity="+obj.value, {
				//ajax_command: "calculateCost",ajax_ieCacheFix:new Date().getTime() },
				//function(json){ 
				//	var myCell = $('#pkgtable');
						
					//alert(json.scanStatus);
									
					//$('#description').text(json.scanStatus);
																
				//	
				//	}
			//	);
		 
	
	}
}



function calculateTotalCost(obj){
alert(obj);
//$("#txtEmail").val()
//alert(obj.value);


  /*$("input:checkbox:checked").each(function() {
		    	selected.push($(this).attr('id'));		  
	    });
   if(!(obj.value > 0) || obj.value < 0){
       
       alert("Please enter valid Quantity");
        obj.value = '';
        return;
    }*/
}

   /* function getSelectedItems(){


		var selected = new Array();
		var selectedItemsList='';
	    $("input:checkbox:checked").each(function() {
		    //if($(this).attr('name') == "selectAllRowsTab1" || $(this).attr('name') == "selectAllRowsCheckbox"){
			    
		    //}else{
		    	selected.push($(this).attr('id'));
		   // }

	        
        	
	    });

	    for (var j = 0; j < selected.length; j++) {
        	selectedItemsList = selectedItemsList+selected[j]+'|';
        } 
		alert(selectedItemsList);
    	
    	}*/
		
function addSelectedItems(){
	//$("#pkgtable").attr("visibility", "visible");
	//$("#pkgtable").removeAttr("display");	
		var selected = new Array();
		var selectedItemsList='';
	    $("input:checkbox:checked").each(function() {
		    	selected.push($(this).attr('id'));		  
	    });

	    for (var j = 0; j < selected.length; j++) {
        	selectedItemsList = selectedItemsList+selected[j]+'|';
        } 
		//alert(selectedItemsList);
    	    if(selectedItemsList !=''){
				$.getJSON("smc/addNorms.ajax?selectedItems="+selectedItemsList, {
				ajax_command: "addNorms",ajax_ieCacheFix:new Date().getTime() },
				function(json){ 
					var myCell = $('#pkgtable');
						var pkgTable2 = '<tr  id="totalcostRow">'+		
									'<td align="middle" colspan="5" ><b>TOTAL COST<b></td><td id="totalestimatedcost"><input type="text" class="required" style="text-align: right" name="estimatedcost" id="estimatedcost"  maxlength="10" size="10" value="" readonly="true"/></td></tr>';
									
						$.each(json.addednorms,function(i,normjson){
						$('#linesecid'+i).remove();
						$('#totalcostRow').remove();
					//alert(json.scanStatus);
						//var edd=normjson.standardcost;			
					//$('#description').text(json.scanStatus);
																
					var pkgTable1 = '<tr  id="linesecid'+i+'">'+		
									'<td id="linesecid'+normjson.linesectionid+'" align="middle" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>'+					
									'<td id="uom'+normjson.linesectionid+'" align="middle" >'+normjson.uom+'</td>'+
									'<td id="description'+normjson.linesectionid+'" align="middle" >'+normjson.description+'</td>'+
									'<td align="right"><input type="text" class="required" style="text-align: right" name="quantity'+i+'" id="quantity'+i+'"  maxlength="10" size="10" value=""  onchange="onNumChange(this,'+normjson.standardcost+','+i+')"  /></td>'+
									'<td id="s2d'+normjson.linesectionid+'" align="middle" >'+normjson.standardcost+'</td>'+
									'<td align="right"><input type="text" class="required" style="text-align: right" name="totalcost'+i+'" id="totalcost'+i+'"  maxlength="10" size="10" value="" readonly="true"/></td>'+
									'<td width=\'25px\' align=\'center\'><image onclick="deletePkg('+i+')" src="../../image/X.png" /></td>'+
												'</tr></table></td>'+
									'</tr>';
								
$('#pkgtable').append(pkgTable1);

						});
						$('#pkgtable').append(pkgTable2);
						
									//$('#pkgtable').append(pkgTable1+pkgTable2);
					}
				);
		 }else{
			alert("Please Select Items!!!...");
		 }
	
}

function show_materials() 
{
	document.style='cursor:hand';
	document.getElementById('otherMat').style.display = '';
	//document.getElementById('others').style.display = 'none';
	//document.getElementById('poleMatParam').style.display = 'none';
	//document.getElementById('npl').style.display = 'none';
	//document.getElementById('isMatClicked').value = "true";
	//dojo.event.topic.publish("display_norms");
	//document.getElementById('isMatClicked').value = "";
}

function deletePkg(i){
	
						//$('#linesecid'+i).remove();
						//$('#totalcostRow').remove();
	var deleteItemValue=$("#totalcost"+i).val();	
	//$("#totalestimatedcost").text(numVal1);
	var totalEstimatedCost=$("#totalestimatedcost").text();
	
	//alert(document.getElementById("totalcost"+i).value);
	var numVal1 = 0;
	if(!document.getElementById("totalcost"+i).value==''){
			numVal1= totalEstimatedCost - parseInt(deleteItemValue);
		}
		
		$("#totalestimatedcost").text(numVal1);
		$('#linesecid'+i).remove();
	/*var rows =$('#pkgtable tr');
		var rowcount = rows.length;
		alert();
		var numVal1=0;	
		$.each(rows, function(j,row) {
		//alert($("#totalcost"+i).val() );
		//if(i<=obj2){
		//alert(document.getElementById("totalcost"+i).value);
		//alert(document.getElementById("totalcost"+i).value);
		if(!document.getElementById("totalcost"+i).value==''){
			numVal1= numVal1+ parseInt(document.getElementById("totalcost"+j).value);
		}
		//var numVal2=parseInt(document.getElementById("one").value);
			//value = value+ $("#totalcost"+i).val();
			//
			
			$("#totalestimatedcost").text(numVal1);
			if(numVal1="0"){
				$('#totalcostRow').remove();
			}
			//}
		});
			*/				
			/*			var rows =$('#pkgtable tr');
		var rowcount = rows.length;
		var numVal1=0;	
		$.each(rows, function(i,row) {
		//alert($("#totalcost"+i).val() );
		//if(i<=obj2){
		//alert(document.getElementById("totalcost"+i).value);
		numVal1= numVal1+ parseInt(document.getElementById("totalcost"+i).value);
		//var numVal2=parseInt(document.getElementById("one").value);
			//value = value+ $("#totalcost"+i).val();
			//
			if(numVal1 == "NaN"){
				$("#totalestimatedcost").val("");
			}
		
			$("#totalestimatedcost").text(numVal1);
			//}
		});*/

}


function load_applicationNumbers()
{
	//$("#applicationRefs select option:gt(0)").remove();
	//$("#subcribers select option:gt(0)").remove();
	 $.getJSON("sps/leadApplicationNumbers.ajax?selectedCustomer=" + 1, {ajax_command:"loadApplicationRefs",ajax_ieCacheFix:new Date().getTime()}, function(json) {

        if (json.JSON_ARRAY_APPLICATION_REFS != null && json.JSON_ARRAY_APPLICATION_REFS.length > 0) {
            for (x = 0; x < json.JSON_ARRAY_APPLICATION_REFS.length; x++) {
             
                $("#applicationRefs1 select").append($('<option></option>').val(json.JSON_ARRAY_APPLICATION_REFS[x].id).html(json.JSON_ARRAY_APPLICATION_REFS[x].name));
            }
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
			<table id="tblInner" class="tblInner">
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
						  		<table align="center" class="tldFromContent1" width="80%">
						  			<tr>
									<td width="25%">Application Ref.No</td>
									<td width="25%">
									<div id="applicationRefs1"><select><option>Select Application Number</option></select></div>
								
									</td>
									
									<td width="25%">Power To Supply</td>
									<td width="25%">wqw</td>
									</tr>
									<tr>
									<td  width="25%">Sec Deposit</td>
									<td  width="25%">wew</td>
									<td  width="25%">Name and Address</td>
									<td  width="25%">wew</td>
									</tr>
									<tr>
									<td  width="25%">SIN No</td>
									<td  width="25%">wew</td>
									<td  width="25%">Total Cost</td>
									<td  width="25%">wew</td>
									</tr>
									<tr>
									<td  width="25%">Sec Deposit</td>
									<td  width="25%">wew</td>
									<td  width="25%">Name and Address</td>
									<td  width="25%">wew</td>
									</tr>
									<tr>
									<td>&nbsp; </td>
									<td>&nbsp;</td>
									<td>Demand</td>
									<td>demand</td>
									</tr>
								</table>
						
						</td>
					</tr>
					 <tr  class="tblInner">
						<td>
					  		<table  class="tldFromContent1" id="pkgtable" align="center">
					  			<tr>
									<th></th> <th>UOM</th> <th>Description</th> <th>Quantity</th> <th>Standard Cost</th> <th>Cost</th></tr>
					 			<tr id="linesecid"><td></td><td id="uom"></td><td id="description"></td><td id="quantity"></td><td id="standardcost"></td>
					  				<td id="totalcost"></td></tr>
					  
							</table>
					
						</td>
					</tr>
					<tr>
						<td id="tdContent">
							<s:form id="frmEstimate" name="frmEstimate" action="test1" method="POST"
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
								
							</tbody>
						</table>
					
										</s:form>
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

