<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Diary Entry</title>
<link rel="stylesheet" type="text/css" href="../../style/style.css" />
<script type="text/javascript" src="../../script/diaryEntry.js"></script>
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
//loadEstimateNumForJobAllocationLetter();
loadContractorForContractorLetter();

});
function loadEstimateNumForJobAllocationLetter(){
	$("#applicationRefs1 select option:gt(0)").remove();
	 $.getJSON("sps/loadEstimateNumForJobAllocationLetter.ajax", {ajax_command:"loadEstimateNumForJobAllocationLetter",ajax_ieCacheFix:new Date().getTime()}, function(json) {

       if (json.estimateNumbers != null && json.estimateNumbers.length > 0) {
           for (x = 0; x < json.estimateNumbers.length; x++) {
            
               $("#applicationRefs1 select").append($('<option></option>').val(json.estimateNumbers[x].id).html(json.estimateNumbers[x].name));
           }
       }
      
  	// $('#applicationRefs1').val(json.categoryCode);

  
	 });
	
	
}
function loadConstructionEstimForJobAllocLetter(estimateNo){
	$("#constructionEstimate select option:gt(0)").remove();
	$("#allocatedIDs select option:gt(0)").remove();
	 $.getJSON("sps/loadConstructionEstimForJobAllocLetter.ajax?ApplicationNumber="+estimateNo.value, {ajax_command:"loadConstructionEstimForJobAllocLetter",ajax_ieCacheFix:new Date().getTime()}, function(json) {

       if (json.estimateNumbers != null && json.estimateNumbers.length > 0) {
           for (x = 0; x < json.estimateNumbers.length; x++) {
            
               $("#constructionEstimate select").append($('<option></option>').val(json.estimateNumbers[x].id).html(json.estimateNumbers[x].name));
           }
           //$('#generateNo').attr("disabled", true);
       }
       if (json.allocatedIds != null && json.allocatedIds.length > 0) {
           for (x = 0; x < json.allocatedIds.length; x++) {
            
               $("#allocatedIDs select").append($('<option></option>').val(json.allocatedIds[x].id).html(json.allocatedIds[x].name));
           }
       }
  	// $('#applicationRefs1').val(json.categoryCode);

  
	 });

	 $.getJSON("sps/loadSchemaDetails.ajax?estimateNumber="+estimateNo.value, {ajax_command:"loadSchemaDetails",ajax_ieCacheFix:new Date().getTime()}, function(json) {

			$('#electorate').val(json.electorate);
			$('#schemaExtention').val(json.schemaExtention);
			$('#schemaName').val(json.schemaName);
			$('#representative').val(json.representative);
			$('#repContact').val(json.repContact);
			$('#proposerName').val(json.proposerName);
			$('#area').val(json.area);
			$('#servicedeponame').val(json.servicedeponame);
			$('#txtDescription').val(json.description);
			
	// $('#applicationRefs1').val(json.categoryCode);


	 });

	 $.getJSON("smc/loadLineSummaryDetails.ajax?estimateNumber="+estimateNo.value, {ajax_command: "loadLineSummaryDetails",ajax_ieCacheFix:new Date().getTime() },
				function(json){ 
				
						$("#pkgtable1 tr:gt(0)").remove();		
						$.each(json.summary,function(i,normjson){
											
							var pkgTable1 = '<tr  id="lineseciid'+normjson.lineid+'">'+
										
										'<td id="estimateno'+normjson.lineid+'" align="left" style="text-align: left">'+normjson.estimateno+'</td>'+					
										'<td id="constructionRef'+normjson.lineid+'" align="left" style="text-align: left">'+normjson.constructionRef+'</td>'+
										'<td id="linesummaryid'+normjson.lineid+'" align="left" style="text-align: left">'+normjson.linesummaryid+'</td>'+
										'<td id="lineid'+normjson.lineid+'" align="left" style="text-align: left">'+normjson.lineid+'</td>'+
										
										'<td id="toatllength1'+normjson.lineid+'" align="right"><input type="text" class="required" style="text-align: right" name="toatllength'+i+'" id="toatllength'+normjson.lineid+'"  readOnly="true" maxlength="10" size="10"  style="text-align: right;color: green;" value="'+normjson.toatllength+'"   /></td>'+
										'<td id="remainLength1'+normjson.lineid+'" align="right"><input type="text" class="required" style="text-align: right" name="remainLength'+i+'" id="remainLength'+normjson.lineid+'"  maxlength="10" size="10"  style="text-align: right;color: green;" value="'+normjson.remainLength+'" onchange="onQuantityChange(this,'+normjson.remainLength+',\'' + normjson.lineid + '\')"  /></td>'+
										
										'<td id="alloactedLength1'+normjson.lineid+'" align="right"><input type="text" class="required" style="text-align: right" name="alloactedLength'+i+'" id="alloactedLength'+normjson.lineid+'"  maxlength="10" size="10"  style="text-align: right;color: green;" value=""  onchange="onQuantityChange(this,'+normjson.remainLength+',\'' + normjson.lineid + '\')"  /></td>'+
										
										'<td id="description'+normjson.lineid+'" align="right"><input type="text" class="required" style="text-align: right" name="description'+i+'" id="description'+normjson.lineid+'"  maxlength="40" size="40"  style="text-align: right;color: green;" value=""  onchange="onQuantityChange(this,'+normjson.standardcost+',\'' + normjson.lineid + '\')"  /></td>'+
										
										'</tr></table></td>'+
										'</tr>';
									
							$('#pkgtable1').append(pkgTable1);
					
						});
				}
			);
	 

	
}
function onQuantityChange(quan,totalLength,lineid){

var remainlength=0;
   if(quan.value < 0 || totalLength < quan.value){
       
       alert("Please enter valid Quantity");
       quan.value = '';
       return;
    } else {
        
		remainlength =totalLength - quan.value;
		
		$("#remainLength"+lineid).val(remainlength);
		
		$.getJSON("smc/getAllocatedLengthDetails.ajax?lineid="+lineid+"&quantity="+quan.value, {
			ajax_command: "getAllocatedLengthDetails",ajax_ieCacheFix:new Date().getTime() },
			function(json){ 
	
		});
	
	}
}
function generateWorkEstNo(){

	var applicationId=$("#listapplicationNumber").val();	
	if(applicationId==-1){
		alert("Please select Reference Number");
		return;
	}
	$.getJSON("smc/generateWorkEstNo.ajax?applicationId="+applicationId, {
			ajax_command: "generateWorkEstNo",ajax_ieCacheFix:new Date().getTime() },
			function(json){ 
				
				 $('#construcRef').val(json.workEstmateNo);
				 $("#pkgtable1 tr:gt(0)").remove();		
					$.each(json.summary,function(i,normjson){
										
						var pkgTable1 = '<tr  id="lineseciid'+normjson.lineid+'">'+
									
									'<td id="estimateno'+normjson.lineid+'" align="left" style="text-align: left">'+normjson.estimateno+'</td>'+					
									'<td id="constructionRef'+normjson.lineid+'" align="left" style="text-align: left">'+normjson.constructionRef+'</td>'+
									'<td id="linesummaryid'+normjson.lineid+'" align="left" style="text-align: left">'+normjson.linesummaryid+'</td>'+
									'<td id="lineid'+normjson.lineid+'" align="left" style="text-align: left">'+normjson.lineid+'</td>'+
									
									'<td id="toatllength1'+normjson.lineid+'" align="right"><input type="text" class="required" style="text-align: right" name="toatllength'+i+'" id="toatllength'+normjson.lineid+'"  readOnly="true" maxlength="10" size="10"  style="text-align: right;color: green;" value="'+normjson.toatllength+'"   /></td>'+
									'<td id="remainLength1'+normjson.lineid+'" align="right"><input type="text" class="required" style="text-align: right" name="remainLength'+i+'" id="remainLength'+normjson.lineid+'"  maxlength="10" size="10"  style="text-align: right;color: green;" value="'+normjson.remainLength+'" onchange="onQuantityChange(this,'+normjson.remainLength+',\'' + normjson.lineid + '\')"  /></td>'+
									
									'<td id="alloactedLength1'+normjson.lineid+'" align="right"><input type="text" class="required" style="text-align: right" name="alloactedLength'+i+'" id="alloactedLength'+normjson.lineid+'"  maxlength="10" size="10"  style="text-align: right;color: green;" value="'+normjson.alloactedLength+'"  onchange="onQuantityChange(this,'+normjson.remainLength+',\'' + normjson.lineid + '\')"  /></td>'+
									
									'<td id="description'+normjson.lineid+'" align="right"><input type="text" class="required" style="text-align: right" name="description'+i+'" id="description'+normjson.lineid+'"  maxlength="40" size="40"  style="text-align: right;color: green;" value="'+normjson.description+'"  /></td>'+
									
									'</tr></table></td>'+
									'</tr>';
								
						$('#pkgtable1').append(pkgTable1);
				
					});
	});
	/*$.getJSON("smc/loadLineSummaryDetails.ajax?estimateNumber="+estimateNo.value, {ajax_command: "loadLineSummaryDetailsInCache",ajax_ieCacheFix:new Date().getTime() },
			function(json){ 
			
					$("#pkgtable1 tr:gt(0)").remove();		
					$.each(json.summary,function(i,normjson){
										
						var pkgTable1 = '<tr  id="lineseciid'+normjson.lineid+'">'+
									
									'<td id="estimateno'+normjson.lineid+'" align="left" style="text-align: left">'+normjson.estimateno+'</td>'+					
									'<td id="constructionRef'+normjson.lineid+'" align="left" style="text-align: left">'+normjson.constructionRef+'</td>'+
									'<td id="linesummaryid'+normjson.lineid+'" align="left" style="text-align: left">'+normjson.linesummaryid+'</td>'+
									'<td id="lineid'+normjson.lineid+'" align="left" style="text-align: left">'+normjson.lineid+'</td>'+
									
									'<td id="toatllength1'+normjson.lineid+'" align="right"><input type="text" class="required" style="text-align: right" name="toatllength'+i+'" id="toatllength'+normjson.lineid+'"  readOnly="true" maxlength="10" size="10"  style="text-align: right;color: green;" value="'+normjson.toatllength+'"   /></td>'+
									'<td id="remainLength1'+normjson.lineid+'" align="right"><input type="text" class="required" style="text-align: right" name="remainLength'+i+'" id="remainLength'+normjson.lineid+'"  maxlength="10" size="10"  style="text-align: right;color: green;" value="'+normjson.remainLength+'" onchange="onQuantityChange(this,'+normjson.remainLength+',\'' + normjson.lineid + '\')"  /></td>'+
									
									'<td id="alloactedLength1'+normjson.lineid+'" align="right"><input type="text" class="required" style="text-align: right" name="alloactedLength'+i+'" id="alloactedLength'+normjson.lineid+'"  maxlength="10" size="10"  style="text-align: right;color: green;" value="'+normjson.alloactedLength+'"  onchange="onQuantityChange(this,'+normjson.remainLength+',\'' + normjson.lineid + '\')"  /></td>'+
									
									'<td id="description'+normjson.lineid+'" align="right"><input type="text" class="required" style="text-align: right" name="description'+i+'" id="description'+normjson.lineid+'"  maxlength="40" size="40"  style="text-align: right;color: green;" value="'+normjson.description+'"  onchange="onQuantityChange(this,'+normjson.standardcost+',\'' + normjson.lineid + '\')"  /></td>'+
									
									'</tr></table></td>'+
									'</tr>';
								
						$('#pkgtable1').append(pkgTable1);
				
					});
			}
		);*/
	//loadDefaultDropDownsValues();
}
function loadContractorForContractorLetter(){
	$("#ContractorList select option:gt(0)").remove();
	 $.getJSON("sps/loadContractorForContractorLetter.ajax", {ajax_command:"loadContractorForContractorLetter",ajax_ieCacheFix:new Date().getTime()}, function(json) {

       if (json.contractors != null && json.contractors.length > 0) {
           for (x = 0; x < json.contractors.length; x++) {
            
               $("#ContractorList select").append($('<option></option>').val(json.contractors[x].id).html(json.contractors[x].name));
           }
       }
      
  	// $('#applicationRefs1').val(json.categoryCode);

  
	 });
	
	
}

function loadSchemeDetails(estimateNo){
	//alert(estimateNo);
	 $.getJSON("sps/loadSchemaDetails.ajax?estimateNumber="+estimateNo.value, {ajax_command:"loadSchemaDetails",ajax_ieCacheFix:new Date().getTime()}, function(json) {

		 $('#electorate').val(json.electorate);
			$('#schemaExtention').val(json.schemaExtention);
			$('#schemaName').val(json.schemaName);
			$('#representative').val(json.representative);
			$('#repContact').val(json.repContact);
			$('#proposerName').val(json.proposerName);
			$('#area').val(json.area);
			$('#servicedeponame').val(json.servicedeponame);
			$('#txtDescription').val(json.description);
 	// $('#applicationRefs1').val(json.categoryCode);

 
	 });
}
</script>
<script type="text/javascript">
function moveToRightOrLeft(side){
	var listLeft=document.getElementById('listAlluserName');
	var listRight=document.getElementById('appointedUserName');
	
	if(side==1){
		if(listLeft.options.length==0){
			alert('You have already moved all countries to Right');
			return false;
		}else{
			var selectedCountry=listLeft.options.selectedIndex;
		
			move(listRight,listLeft.options[selectedCountry].value,listLeft.options[selectedCountry].text);
			listLeft.remove(selectedCountry);
		
			if(listLeft.options.length>0){
				listLeft.options[0].selected=true;
			}
		}
	}else if(side==2){
		if(listRight.options.length==0){
			alert('You have already moved all countries to Left');
			return false;
		}else{
			var selectedCountry=listRight.options.selectedIndex;
			
			move(listLeft,listRight.options[selectedCountry].value,listRight.options[selectedCountry].text);
			listRight.remove(selectedCountry);
			
			if(listRight.options.length>0){
				listRight.options[0].selected=true;
			}
		}
	}
}

function move(listBoxTo,optionValue,optionDisplayText){
	var newOption = document.createElement("option");
	newOption.value = optionValue;
	newOption.text = optionDisplayText;
	listBoxTo.add(newOption, null);
	return true;
}
</script>
<s:head  />
<sx:head />

</head>
<body onunload="unLoad()">
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
                                                    <td width="100%" height="70px"><img src="../../image/NewSPS2.gif" width="100%"  alt="NewSPS2"/>
                                                    </td>
                                                    <td width="*" bgcolor="#000033"></td>
                                                    <td width="*" bgcolor="#000033"></td>
                                     </tr>
								
							</tbody>
						</table>
						</td>
					</tr>
					<tr>
						<td id="tdWelcome">
						<table class="tblWelcome" border="0" cellpadding="0" cellspacing="0">
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
									<td class="tdWelcomeUser"><s:property value="loggedInUserId"></s:property></td>
									<td class="tdLogOut"><a href="<s:url action="logout"/>">Logout</a></td>
								</tr>
							</tbody>
						</table>
						</td>
					</tr>
					<!-- tr>
						<td></td>
					</tr-->
					<tr>
						<td id="tdContent">
						<table class="tblContent" border="1" cellpadding="0"
							cellspacing="0">
							<thead>
								<tr>
									<th></th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td><s:form action="diaryEntryCMRE" method="POST" theme="simple">
										<table width="100%" class="tldFromContent" border="0">
											
											<tbody>												
												<tr>
													<td>
													<table width="100%" class="tblContent" border="0"  >
														
														<tbody>
															<tr>
																<td>	
																	<div class="listDivStyleBig1" >
																<table width="100%" border="0"  border="1"  class="tldFromContent">
																			<thead>
																				<tr>
																					<th width="10%"></th>
																					<th width="15%"></th>
																					<th width="15%"></th>
																					<th width="25%"></th>
																					<th width="5%"></th>
																									
																				</tr>
																			</thead>
																			</table>
																			<div style="float:left;width:50%">
																			<table border="0" a border="1"  class="tldFromContent">
																			        <tr>
																					 <td align="left" class="listHeader">Schedule</td>
																					 <td align="left"><s:label name="statusMsg" id="statusMsg" cssClass="STATUSMSG"></s:label></td>
																					</tr>
																						
																					<tr>	
																						<td align="left"><s:label value="Cost Centre" ></s:label></td>
																						<td align="left"><s:textfield name="selCostCenter" disabled="true"></s:textfield></td>																						
																						<td align="left"><s:label value="Estimate Number" ></s:label></td>
																						<td align="left"> 
																							<s:select name="applicationNumber" 
																									list="listapplicationNumber" 
																									id="listapplicationNumber"																			
																									headerKey="-1"												
																									headerValue="-- Please Select --" onchange="loadConstructionEstimForJobAllocLetter(this)">																			
						    																</s:select>
																						</td>
																									
																					</tr>
																					<tr><td>&nbsp;</td></tr>	
																				</table>
																			</div>	
																			<div style="float:left;width:50%">
																				<table  border="0"   class="tldFromContent">
																					<tr><td>
																						<table  border="0">
																							<tr>
																							
																								<td colspan="2" class="tblMenuHeading"><b>Application Details</b></td>
																							</tr>
																							<tr class="tblMenuColor">
																								<td > <s:label value="Area" ></s:label></td>
																								<td width="50px"><s:textfield name="area" id="area" readonly="true" size="40" maxlength="40"></s:textfield></td>
																							</tr>
																							<tr class="tblMenuColor">
																								<td ><s:label value="Service Depot Name" ></s:label></td>
																								<td width="50px"><s:textfield name="servicedeponame" id="servicedeponame" readonly="true" size="40" maxlength="40"></s:textfield></td>
																							</tr>
																							<tr class="tblMenuColor">
																								<td ><s:label value="Proposer" ></s:label></td>
																								<td width="50px"><s:textfield name="proposerName" id="proposerName" readonly="true" size="40" maxlength="40"></s:textfield></td>
																							</tr>
																							<tr class="tblMenuColor">
																								<td ><s:label value="Electorate" ></s:label></td>
																								<td width="50px"><s:textfield name="electorate" id="electorate" readonly="true" size="40" maxlength="40"></s:textfield></td>
																							</tr>
																							<tr class="tblMenuColor">
																								<td ><s:label value="Representative and Contact" ></s:label></td>
																								<td width="50px"><s:textfield name="representative" id="representative" readonly="true" size="40" maxlength="40"></s:textfield><s:textfield name="repContact" id="repContact" readonly="true" size="40" maxlength="40" ></s:textfield></td>
																							</tr>
																							<tr class="tblMenuColor">
																								<td ><s:label value="Scheme / Extension name" ></s:label></td>
																								<td width="50px"><s:textfield name="schemaName" id="schemaName" readonly="true" size="40" maxlength="40"></s:textfield><s:textfield name="schemaExtention" id="schemaExtention" readonly="true" size="40" maxlength="40" ></s:textfield></td>
																							</tr>
																						</table>
																					</td>
																				</tr>
																			</table>
																		</div>	
																		<table id="pkgtable1" align="center" border="1">
																			<tr><td colspan="2">Estimate Number</td><td colspan="3" id="estimateNo"></td></tr>
																 			<tr id="header2">
																			<th>Construction Ref</th><th>Line Summary</th><th>Line Id</th> <th>Total Length</th><th>Remaining Length</th><th>Allocated Length</th><th>Remarks</th></tr>
																			<tr><td id="constructionRef"></td><td id="lineSummaryid"></td><td id="lineId"></td><td id="totalLength"></td><td id="remainLength"></td><td id="allocatedLength"></td><td id="remarks"></td>
																 				</tr>
																 
																		</table>
																			<div style="float:left;width:100%">
																			<table border="0"   border="1"  class="tldFromContent">
																						<tr>
																							<td class="tblMenuHeading">Active Appointments</td>
																						</tr>
																						<tr>
																							<td>
																								<table class="tldFromContent" border="1">
												
																									<tr class="tblMenuColor" height="20px">
																										<td><strong>Commercial Reference</strong></td>
																										<td><strong>Construction Reference</strong></td>
																										<td><strong>Allocated By </strong></td>
																										<td><strong>Allocated To</strong></td>
																										<td><strong>Appointment Date</strong></td>
																										
																										<td><strong>Work Scope</strong></td>												 
												
												
																									</tr>
																									<%int i=0; %>
																									<s:iterator value="ativeAppoinmentsListConMain">
																										<tr bgcolor="#CCCCCC">
																											
																											<td width="150px"><s:property value="referenceNo" /></td>
																											<td width="150px"><s:property value="westimateNo" /></td>
																										
																											<td width="5px" align="right"><s:property value="allocatedBy" /></td>
												
																											<td  width="5px" align="right"><s:property value="allocatedTo" /></td>
												
																											<td width="5px" align="right"><s:property value="appointmentDate" /></td>
																											
																											 
																											<td width="5px" align="right"><s:property value="description" /></td>
																											 
																										</tr>
																									</s:iterator>
																									<%i++; %>
																								</table>
																								</td>
																							</tr>
																							<tr>
																								<td>&nbsp;</td>
																							</tr>
																						
																						
																					
																					</table>
																					</div>
																					<div style="float:left;width:100%">
																					<table width="100%" border="0"   border="1"  class="tldFromContent">
																					<tr>																						
																						<!--  <td align="left"><s:label value="Session" ></s:label></td>
																						
																						<td align="left"> 
																							<s:select name="sessionName" 
																									list="listsessionName" 
																									id="listsessionName"																			
																									headerKey="-1"												
																									headerValue="-- Please Select --">																			
						    																</s:select>
																						</td>-->
																						<td align="left"><s:label value="Appointment Type" ></s:label></td>	
																						<td align="left"> 
																							<s:select name="appointmentType" 
																									list="listappointmentType" 
																									id="listappointmentType"																			
																									headerKey="-1"												
																									headerValue="-- Please Select --">																			
						    																</s:select>
																						</td>		
																					</tr>
																					<tr><td>&nbsp;</td></tr>	
																					<tr>																						
																						<td align="left"><s:label value="Allocated To" ></s:label></td>
																						<td align="left"> 
																								<s:select name="appointedUserName" 
																										list="listuserName" 
																										id="listuserName" 		 																		
																										headerKey="-1"												
																										headerValue="-- Please Select --">																			
							    														</s:select>
																							
																						</td>
																						<td align="left"><s:label value="Date" ></s:label></td>
																						<td> 
																							<sx:datetimepicker name="appointmentDate" id="appointmentDate" displayFormat="dd/MM/yyyy"  required="true"   />
																						</td>
																						  	
																					</tr>																					
																					<tr>
																						<td>&nbsp;</td>
																					</tr>	
																					<tr>
																						<td align="left"><s:label value="Description" ></s:label></td>
																						<td align="left"> 
																							<s:textarea name="description" id="txtDescription" cols="20" rows="5" ></s:textarea>
																						</td>
																						
																						<td>&nbsp;</td>																			
																						<td align="left">&nbsp;</td>
																						<td align="left"><s:hidden id="hid_AptDate" name="aprDate"></s:hidden></td>
																						
																								
																					</tr>
																					<s:if test="%{costCenterNo=='530.80' ||costCenterNo=='510.20' || costCenterNo=='530.80' || costCenterNo=='530.20' || costCenterNo=='530.30'}">																					
																					<tr>																						
																						<td>&nbsp;</td>	
																						<td>&nbsp;</td>		
																						
																						<td>Contractor</td>	
																						<td><div id="ContractorList">
																								<select name="contractor" id="contractor" ><option value="-1">--Select--</option>
																								</select>
																							</div>
																						</td>
																						
																						
																					</tr>	
																					<tr>
																						<td>&nbsp;</td>	
																						<td>&nbsp;</td>	
																						<td>Electrical Suprintendent Name</td>
																						
																						<td><s:textfield name="elecSupName" size="50"></s:textfield></td>
																					</tr>
																					</s:if>
																					<tr>
																						<s:if test="%{costCenterNo=='510.20' || costCenterNo=='530.80' || costCenterNo=='530.20' || costCenterNo=='530.30'}">
																							<td>&nbsp;</td>
																							<td>&nbsp;</td>
																							<td>Estimate Reference</td>
																							<td>
																						
																							
																									<div id="constructionEstimate" >
																										<select name="selectedConstructionEstNumber" id="selectedConstructionEstNumber" ><option value="-1">--Select--</option>
																										</select>
																									</div>
															
																								
																									<s:textfield name="construcRef" id="construcRef" size="30"></s:textfield>
																									<input type="button" name="generateNo" id="generateNo" value="generateNo" onclick="generateWorkEstNo();"/>
																								
																							</td>
																					</s:if>
																					</tr>																						
																					
																					
																					<tr>
																						<td>&nbsp;</td>
																					</tr>
																					<tr>
																						<td id="tdBotton" colspan="3">
																							<s:submit theme="simple"  value="Add" method="addAppointment"  onclick="return addAppointment()" id="btnBill" disabled="false"></s:submit>
																							<s:reset theme="simple" align="right" value="Clear" id="btnReset" onclick="return clearForm()" />
																							<s:if test="%{costCenterNo=='510.20' ||  costCenterNo=='530.80' || costCenterNo=='530.20' || costCenterNo=='530.30'}">
																								<s:submit theme="simple" type="submit" value="Download Letter" method="GenerateAllocationOfJobLetter"></s:submit>
																							</s:if>
																							<s:submit theme="simple" align="right" value="Finish Allocation" method="finishAllocation" id="btnAllocate" ></s:submit>																							
																							<s:submit theme="simple" align="right" value="Exit" method="close" id="btnClose" ></s:submit>
																						</td>
																					</tr>
																		</table>
																		
																		</div>
																	</div>														
																
																</td>																
															</tr>
														
										   	
														</tbody>
													</table>														
													</td>
												</tr>		
											</tbody>
										</table>

									</s:form></td>
								</tr>
								<tr>
									<td></td>
								</tr>
								<tr>
									<td></td>
								</tr>
							</tbody>
						</table>

						</td>
					</tr>
					<tr>
						<td id="tdFooter">
						<table class="tblFooter" border="1" cellpadding="0"
							cellspacing="0">

							<tbody>
								<tr align="Center">
										<td class="footerNotice">@ Ceylon Electricity Board</td>
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