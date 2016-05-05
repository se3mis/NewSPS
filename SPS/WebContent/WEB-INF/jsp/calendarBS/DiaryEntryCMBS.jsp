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
function loadUserName(userId){
	//alert(userId.value);
	 $.getJSON("sps/getUsername.ajax?userId="+userId.value, {ajax_command:"getUserName",ajax_ieCacheFix:new Date().getTime()}, function(json) {

		 $('#elecSupName').val(json.userName);
			
 	// $('#applicationRefs1').val(json.categoryCode);

 
	 });
}
function loadEstimateNo(val){
	//alert(userId.value);
	document.getElementById("construcRef").value=val.value;
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
	});
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
									<td><s:form action="diaryEntryCMBS" method="POST" theme="simple">
										<table width="100%" class="tldFromContent" border="0">
											
											<tbody>												
												<tr>
													<td>
													<table width="100%" height="90%" class="tblContent" border="0"  >
														
														<tbody>
															<tr>
																<td>	
																	<div  >
													<table width="100%" border="0" align="left"  border="1" style="border-color:#000033;border-collapse:collapse"  class="tldFromContent">
																			<thead>
																				<tr>
																					<th width="10%"></th>
																					<th width="15%"></th>
																					<th width="15%"></th>
																					<th width="25%"></th>
																					<th width="5%"></th>
																									
																				</tr>
																			</thead>
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
																					<tr><td colspan="5"><div style="float:left;width:50%">
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
																		</div>	</td></tr>	
																				
																				
																					<tr>																						
																						<td align="left">&nbsp;</td>
																						
																						<td align="left"> 
																							&nbsp;
																						</td>
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
																										headerValue="-- Please Select --" onchange="loadUserName(this)">																			
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
																						<td>&nbsp;</td>	
																						<td>&nbsp;</td>	
																						<td>Electrical Suprintendent Name</td>
																						
																						<td><s:textfield name="elecSupName" id="elecSupName" size="50"></s:textfield></td>
																					</tr>	
																					<tr>
																						<td>&nbsp;</td>	
																						<td>&nbsp;</td>	
																						<td>Standard Estimate Cost</td>
																						
																						<td><s:textfield name="stdEstCost" id="stdEstCost" size="50"></s:textfield></td>
																					</tr>																					
																					<tr>
																						<td>&nbsp;</td>
																					</tr>	
																					<tr>
																						<td align="left"><s:label value="Description" ></s:label></td>
																						<td align="left"> 
																							<s:textarea name="description" id="txtDescription" cols="35" rows="2" ></s:textarea>
																						</td>
																						
																						<td>&nbsp;</td>																			
																						<td align="left">&nbsp;</td>
																						<td align="left"><s:hidden id="hid_AptDate" name="aprDate"></s:hidden></td>
																						<s:hidden id="branchType" name="branchType"></s:hidden>
																					</tr>																					
																					<tr>																						
																						<td>&nbsp;</td>	
																						<td>&nbsp;</td>		
																						<s:if test="%{costCenterNo=='530.80' ||costCenterNo=='530.20' || costCenterNo=='530.30' || costCenterNo=='550.20' || costCenterNo=='550.30' || costCenterNo=='430.20' || costCenterNo=='430.25'  || costCenterNo=='430.30' || costCenterNo=='430.35' || costCenterNo=='430.10'}">
																						<td>Contractor</td>	
																						<td><div id="ContractorList">
																								<select name="contractor" id="contractor" ><option value="-1">--Select--</option>
																								</select>
																							</div>
																						</td>
																						</s:if>
																					</tr>
																					
																					<tr>
																					
																						<td>&nbsp;</td>
																						<td>&nbsp;</td>
																						<s:if test="%{branchType != 'MU'}">
																							<td>Estimate Reference</td>
																							<td>
																							
																									<div id="constructionEstimate" >
																										<select name="selectedConstructionEstNumber" id="selectedConstructionEstNumber" onChange="loadEstimateNo(this)"><option value="-1">--Select--</option>
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
																							<s:submit theme="simple" type="submit" value="Download Letter" method="GenerateAllocationOfJobLetter"></s:submit>
																							
																							<s:submit theme="simple" align="right" value="Finish Allocation" method="finishAllocation" id="btnAllocate" ></s:submit>	
																																													
																							<s:submit theme="simple" align="right" value="Exit" method="close" id="btnClose" ></s:submit>
																						</td>
																					</tr>
																		</table>
																		
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