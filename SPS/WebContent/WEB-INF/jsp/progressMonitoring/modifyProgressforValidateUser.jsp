<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<html>
<head>
<style>
div.main {
	position: relative;
	margin: 0;
	padding: 0;
	float: left;
}

div.sub1 {
	position: relative;
	margin: 0;
	padding: 10px;
	float: left;
}

div.sub2 {
	position: relative;
	margin: 0;
	padding: 40px;
	float: left;
}

</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>New Add</title>
<link rel="stylesheet" type="text/css" href="../../style/style.css" />

<link rel="stylesheet" type="text/css" href="../../style/datePicker.css" />
<script src="../../script/jquery/jquery.datePicker-min.js"
	type="text/javascript"></script>

<script type="text/javascript" src="../../script/javascript.js"></script>
<link rel="stylesheet" type="text/css" href="../../style/datePicker.css" />

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
Ext.onReady(function() {
	$('#dateOfprogress').datePicker({clickInput:true});
	$('#dateOfprogress1').datePicker({clickInput:true});
	
	loadMilestoneList();
});

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
function loadEstimationData(){
var estimateNo = $("#estimateNo").val();

	
		if(estimateNo==""){
			
		alert("Please enter Construction Reference No");
			return;	
		}else{
			//document.getElementById('normsdiv').style.display = 'none';
		  	$("#update").removeAttr("disabled");
		  	//$("#bulkAddSub").removeAttr("disabled");
		  	//$("#landAddSub").removeAttr("disabled");
		   	//$("#update").removeAttr("disabled");
		}
	
		$.getJSON("smc/loadEstimationDetails.ajax?estimateNo="+estimateNo, {
		ajax_command: "searchProgressDetails",ajax_ieCacheFix:new Date().getTime() },
		function(json){ 
			if(json.invalid){
			   // invalidStatus = "true";
				alert(json.errorMessage);
				return;
			}	
			
			
			$('#fundsou').val(json.fundsou);	
			 
			$('#applicationId').val(json.stdEstimateNo);	
			$('#description').val(json.description);	
			$('#categoryId').val(json.categoryId);	
			
			$('#projectNo').val(json.projectNo);
			
			 $('#costCenterNo').val(json.costCenter);
//alert(json.estimateDetails.length);			
			
			$("#progressDetails tr:gt(0)").remove();
	
						$.each(json.addedMilestones,function(i,addedMilestone){
					
					//$('#totalcostRow').remove();
								//alert(json.addedMaterials.length);						
							var progressDetails = '<tr  id="pro'+addedMilestone.milesToneId+'">'+	
							//'<td id="radio'+estimateDetail.selectedPegId+'" align="middle" ><input type="radio" class="required" style="text-align: right" name="pegradio" id="'+estimateDetail.selectedPegId+'"  value="'+estimateDetail.selectedPegId+'"  /></td>'+																							
							//'<td id="resourceType'+estimateDetail.selectedPegId+estimateDetail.resourceCode+'" align="middle" >'+estimateDetail.resourceType+'</td>'+
							'<td id="milesToneId'+addedMilestone.milesToneId+'" align="left" >'+addedMilestone.milesToneId+'</td>'+
							'<td id="percentage'+addedMilestone.percentage+'" align="left" ><input type="text" class="required" style="text-align: right;color: green;" name="percentage'+addedMilestone.percentage+'" id="percentage'+addedMilestone.percentage+'"  maxlength="10" size="10" value="'+addedMilestone.percentage+'" onchange="onNumChange(this,'+'\''+addedMilestone.percentage+'\''+',\'' + addedMilestone.percentage + '\')" /></td>'+
							//'<td id="updatedDate'+addedMilestone.updatedDate+'" align="left" >'+addedMilestone.updatedDate+'</td>'+
							//'<td id="remarks'+addedMilestone.remarks+'" align="left" >'+addedMilestone.remarks+'</td>'+
						
							'<td id="updatedDate'+addedMilestone.updatedDate+'" align="left" ><input type="text" class="required" style="text-align: right;color: green;" name="dateOfprogress'+addedMilestone.milesToneId+'" id="dateOfprogress'+addedMilestone.milesToneId+'"  maxlength="10" size="10" value="'+addedMilestone.updatedDate+'" onchange="onNumChange(this,'+'\''+addedMilestone.updatedDate+'\''+',\'' + addedMilestone.updatedDate + '\')" /></td>'+
							//<input type="text" class="required" style="text-align: right;color: green;" name="'+estimateDetails.estimateQuantity+'" id="'+estimateDetails.estimateQuantity+'"  maxlength="10" size="10" value="" onchange="onNumChange(this,'+'\''+estimateDetails.selectedPegId+'\''+',\'' + estimateDetails.resourceCode + '\')" />
							'<td id="remarks'+addedMilestone.remarks+'" align="left" ><input type="text" class="required" style="text-align: right;color: green;" name="'+addedMilestone.remarks+'" id="'+addedMilestone.remarks+'"  maxlength="50" size="50" value="'+addedMilestone.remarks+'" onchange="onNumChange(this,'+'\''+addedMilestone.milesToneId+'\''+',\'' + addedMilestone.milesToneId + '\')" /></td>'+
							//'<td width=\'25px\' align=\'center\'><image onclick="deleteDetailLine(\'' + estimateDetail.isFromPegSchedule + '\'' +',\'' + estimateDetail.resourceCode + '\'' +',\'' + null+'\')" src="../../image/X.png" /></td>'+

							'</tr>';
						
						$('#progressDetails').append(progressDetails);
				
				
					
				});

			
			});

		
	}
function loadMilestoneList(){
	//clearPeggingScheduleForm();
	
	 $.getJSON("sps/loadMilestones.ajax?selectedCustomer=" + 1, {ajax_command:"loadMilestones",ajax_ieCacheFix:new Date().getTime()}, function(json) {
//alert("hi");
	 //alert(json.jsonarrayapplicationrefs.length);
	 //  alert(json.jsonarrayEstimationRefNumbers.length);
	  // alert("hi");
      
	       if (json.jsonmilestoneAray != null && json.jsonmilestoneAray.length > 0) {
	           for (x = 0; x < json.jsonmilestoneAray.length; x++) {
	            
	               $("#milestones select").append($('<option></option>').val(json.jsonmilestoneAray[x].id).html(json.jsonmilestoneAray[x].name));
	           }
	       }
		  
	       $('#costCenterNo').val(json.costCenter);
		 });
	
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
function saveProgress(){
	
	var milestoneId = $("#milestoneId").val();
	var remark = $("#remark").val();
	var projectNo = $("#projectNo").val();
	var estimateNo = $("#estimateNo").val();
	var dateOfprogress = $("#dateOfprogress").val();
	
	
	if(projectNo ==''){
		alert("Please Select Items!!!...");
		
       return;
	  }

	
	//alert(escape(resourType))
	  ///  if(selectedItemsList !=''){
		$.getJSON("smc/addProgress.ajax?milestoneId="+milestoneId+"&remark="+remark+"&estimateNo="+estimateNo+"&projectNo="+projectNo+"&dateOfprogress="+dateOfprogress, {
		ajax_command: "addProgress",ajax_ieCacheFix:new Date().getTime() },
		function(json){ 
			
					if(json.invalid){	
					    invalidStatus = "true";
						alert(json.errorMessage);
						return;
					}	

					$("#progressDetails tr:gt(0)").remove();
				$.each(json.addedMilestones,function(i,addedMilestone){
					
					//$('#totalcostRow').remove();
								//alert(json.addedMaterials.length);						
					var progressDetails = '<tr  id="pro'+addedMilestone.milesToneId+'">'+	
					//'<td id="radio'+estimateDetail.selectedPegId+'" align="middle" ><input type="radio" class="required" style="text-align: right" name="pegradio" id="'+estimateDetail.selectedPegId+'"  value="'+estimateDetail.selectedPegId+'"  /></td>'+																							
					//'<td id="resourceType'+estimateDetail.selectedPegId+estimateDetail.resourceCode+'" align="middle" >'+estimateDetail.resourceType+'</td>'+
					'<td id="milesToneId'+addedMilestone.milesToneId+'" align="left" >'+addedMilestone.milesToneId+'</td>'+
					'<td id="percentage'+addedMilestone.percentage+'" align="left" ><input type="text" class="required" style="text-align: right;color: green;" name="percentage'+addedMilestone.percentage+'" id="percentage'+addedMilestone.percentage+'"  maxlength="10" size="10" value="'+addedMilestone.percentage+'" onchange="onNumChange(this,'+'\''+addedMilestone.percentage+'\''+',\'' + addedMilestone.percentage + '\')" /></td>'+
					//'<td id="updatedDate'+addedMilestone.updatedDate+'" align="left" >'+addedMilestone.updatedDate+'</td>'+
					//'<td id="remarks'+addedMilestone.remarks+'" align="left" >'+addedMilestone.remarks+'</td>'+
				
					'<td id="updatedDate'+addedMilestone.updatedDate+'" align="left" ><input type="text" class="required" style="text-align: right;color: green;" name="dateOfprogress'+addedMilestone.milesToneId+'" id="dateOfprogress'+addedMilestone.milesToneId+'"  maxlength="10" size="10" value="'+addedMilestone.updatedDate+'" onchange="onNumChange(this,'+'\''+addedMilestone.updatedDate+'\''+',\'' + addedMilestone.updatedDate + '\')" /></td>'+
					//<input type="text" class="required" style="text-align: right;color: green;" name="'+estimateDetails.estimateQuantity+'" id="'+estimateDetails.estimateQuantity+'"  maxlength="10" size="10" value="" onchange="onNumChange(this,'+'\''+estimateDetails.selectedPegId+'\''+',\'' + estimateDetails.resourceCode + '\')" />
					'<td id="remarks'+addedMilestone.remarks+'" align="left" ><input type="text" class="required" style="text-align: right;color: green;" name="'+addedMilestone.remarks+'" id="'+addedMilestone.remarks+'"  maxlength="50" size="50" value="'+addedMilestone.remarks+'" onchange="onNumChange(this,'+'\''+addedMilestone.milesToneId+'\''+',\'' + addedMilestone.milesToneId + '\')" /></td>'+
					//'<td width=\'25px\' align=\'center\'><image onclick="deleteDetailLine(\'' + estimateDetail.isFromPegSchedule + '\'' +',\'' + estimateDetail.resourceCode + '\'' +',\'' + null+'\')" src="../../image/X.png" /></td>'+

					'</tr>';
				
					$('#progressDetails').append(progressDetails);
				
					
				});
				
			}
		);
/// }else{
///	alert("Please Select Items!!!...");
//}
	   
}
</script>
</head>
<body onLoad="buttonLoader()">
	<table id="tblOuter" class="tblOuter">
		<tbody>
			<tr>
				<td>
					<table id="tblInner" class="tblInner">
						<tbody>
							<tr>
								<td id="tdHeader">
									<table id="tblHeader" class="tblHeader" cellpadding="0"
										cellspacing="0">
										<tbody>
											<tr>
												<td><img src="../../image/NewSPS2.gif" width="100%"
													alt="NewSPS2" /></td>
												<td width="*" bgcolor="#000033"></td>
												<td width="*" bgcolor="#000033"></td>
											</tr>
										</tbody>
									</table>
								</td>
							</tr>

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

						</tbody>
					</table>
			<tr>
				<td id="tdContent" width="100%">
					<table class="tblContent" border="0" cellpadding="0"
						cellspacing="0">
						<thead>
							<tr>
								<th></th>
							</tr>
						</thead>

						<tbody>
							<tr>
								<td><s:form name="fjhdjfh" validate="true" theme="simple">
										<table width="100%" class="tldFromContent" border="0">
											<thead>

											</thead>
											<tbody>
												<tr>
													<td>
														<div border="1">
															<table width="100%" border="0">
																<thead>
																	<tr>
																		<th width="3%"></th>
																		<th width="18%"></th>
																		<th width="30%"></th>
																		<th width="18%"></th>
																		<th width="25%"></th>
																		<th width="*%"></th>
																		<!--  <th width="40%"></th>
																<th width="10%"></th>
																<th width="*"></th>-->

																	</tr>
																</thead>
																<tbody>

																	<tr>

																		<td id="tdContent" width="100%">

																			<table class="tblContent" border="0" cellpadding="0"
																				cellspacing="0">
																				<thead>
																					<tr>
																						<th></th>
																					</tr>
																				</thead>



																				<tbody>
																					<tr>
																						<td>
																							<div class="main">
																							
																									<table>
																										<tr>
																											<td>Cost center</td>
																											<td></td>
																										</tr>
																									<s:if test="%{costCenterNo!='510.20'}">
																										<tr>
																											<td width="25%">Estimate No</td>
																											<td width="25%">
																											<input type="text" name="estimateNo"  id="estimateNo" " size="20" maxlength="20" /><input type="button"  value="Search" onclick="loadEstimationData();"/>
																												<!-- <div id="estimateNos"><select name="estimateNo" id="estimateNo" onchange="loadEstimationData(this)"><option value="-1">--Select--</option></select></div> -->
																											</td>
																										</tr>
																										<tr>
																											<td  width="25%">Commercial Reference</td>
																											<td  width="30%"><input type="text" name="applicationId"  id="applicationId" " disabled="disabled"/></td>
																										</tr>
																										</s:if>
																										<tr>
																										
																										<td width="25%">Job No</td>
																										<td width="25%">
																										<s:select name="projectNo" 
																										list="jobNumberList" 
																										id="jobNumberList" 		 																		
																										headerKey="-1"												
																										headerValue="-- Please Select --"></s:select>
																										
																										
																										<input type="text" name="projectNo"  id="projectNo" " disabled="disabled"/>
																									
																											<!-- <div id="projectNos"><select name="projectNo" id="projectNo" onchange="loadReviseDetails(this)"><option value="-1">--Select--</option></select></div> -->
																										</td>
																										</tr>
																										<tr>
																											<td>Description</td>
																											<td  width="25%"><textarea name="description" id="description" rows="5" cols="60" disabled="disabled"></textarea></td>
																										</tr>
																											
																										<tr>
																											<td width="25%">Progress Milestone</td>
																											<td><!--<s:select name="milestoneId" 
																										list="milestoneList" 
																										id="milestoneList" 		 																		
																										headerKey="-1"												
																										headerValue="-- Please Select --"></s:select>-->
																										<div id="milestones"><select name="milestoneId" id="milestoneId"><option value="-1">--Select--</option></select></div> 
																										
																										</td>
																										</tr>
																										
																										<tr>
																											<td>Date</td>
																										
																											<td><input type="text"  name="dateOfprogress" id="dateOfprogress" />
																											</td>
																										</tr>
																										<tr>
																											<td  width="25%">Estimate categoty</td>
																											<td  width="25%"><input type="text" name="categoryId"  id="categoryId" disabled="disabled"/></td>
																										</tr>
																										<tr>
																											<td  width="25%">Fund Source</td>
																											<td  width="25%"><input type="text" name="fundsou"  id="fundsou" disabled="disabled"/></td>
																										</tr>
																										<tr>
																											<td>Remark</td>
																											<td  width="25%"><textarea name="remark" id="remark" rows="5" cols="60" ></textarea></td>
																										</tr>
																										
																										<tr>
																											<td></td>
																											<td><input type="button" name="update" id="update" value="Update" onclick="saveProgress()"/>
																											<s:submit
																										theme="simple" align="right" value="Exit"
																										method="close" id="btnClose"></s:submit></td>
																											
																										</tr>

																									</table>
																								</div>
																								
																								<div>
																									<table>
												
																										<tr >
																											<td colspan="3">
																												<!--  <input type="button" name="new" id="new" value="Add New Estimate Entry" onclick="showAddJobInfo();"/>-->
																												<table  class="tldFromContent1" id="progressDetails"  style="display:block;" border="1"> 
																										  			<tr id="header1">
																														 <th>MilesTone  Id</th><th><b>Progress Percentage&nbsp;&nbsp;</b></th><th><b>Updated Date&nbsp;&nbsp;</b></th><th><b>Remarks&nbsp;&nbsp;</b></th><th></th></tr>
																										 			<tr id="milesToneId"><td id="percentage"></td><td id="updatedDate"></td><td id="remarks"></td></tr>		
																												</table>
																											</td>
																										</tr>
																										
																								     </table>
																								</div>
																								
																								
																						
																					</tr>
																				</tbody>
																			</table>
																		</td>
																	</tr>
																</tbody>

															</table>
														</div>
													</td>
												</tr>
											</tbody>
										</table>
									</s:form></td>
							</tr>
						</tbody>





					</table>
			</tr>


			<tr>
				<td id="tdFooter">
					<table class="tblFooter" border="1" cellpadding="0" cellspacing="0">

						<tbody>
							<tr>
								<td id="tdFooter">
									<table class="tblFooter" border="1" cellpadding="0"
										cellspacing="0">

										<tbody>



											<tr>
												<td class="footerNotice" align="center">@ Ceylon
													Electricity Board</td>
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
</html>