<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Diary Entry</title>
<link rel="stylesheet" type="text/css" href="../../style/style.css" />
<script type="text/javascript" src="../../script/diaryEntry.js"></script>
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
function loadUserName(userId){
	//alert(estimateNo);
	 $.getJSON("sps/getUsername.ajax?userId="+userId.value, {ajax_command:"getUserName",ajax_ieCacheFix:new Date().getTime()}, function(json) {

		 $('#elecSupName').val(json.userName);
			
 	// $('#applicationRefs1').val(json.categoryCode);

 
	 });
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
									<td><s:form action="diaryEntryBS" method="POST" theme="simple">
										<table width="100%" class="tldFromContent" border="0">
											
											<tbody>												
												<tr>
													<td>
													<table width="100%" height="90%" class="tblContent" border="0"  >
														
														<tbody>
															<tr>
																<td>	
																	<div >
																	<table border="0" a border="1"  class="tldFromContent">
																			<thead>
																				<tr>
																					<th width="10%"></th>
																					<th width="15%"></th>
																					<th width="10%"></th>
																					<th width="5%"></th>
																					<th width="5%"></th>
																									
																				</tr>
																			</thead>
																			        
																					</table>
																			<table><tr><td>
																			
																			
																			</td><td>
																			<div style="float:left;width:100%">
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
																					<table border="0" a border="1"  class="tldFromContent">
																			<thead>
																				<tr>
																					<th width="10%"></th>
																					<th width="15%"></th>
																					<th width="10%"></th>
																					<th width="5%"></th>
																					<th width="5%"></th>
																									
																				</tr>
																			</thead>
																			        <tr>
																					 <td align="left" class="listHeader">Schedule</td>
																					 <td align="left"><s:label name="statusMsg" id="statusMsg" cssClass="STATUSMSG"></s:label></td>
																					</tr>
																					</table>
																			<table><tr><td>
																			
																			<table border="0" a border="1"  class="tldFromContent">																					
																					<tr>	
																						<td align="left"><s:label value="Cost Centre" ></s:label></td>
																							<td align="left"><s:textfield name="selCostCenter" disabled="true"></s:textfield></td>																						
																							
																					
																					
																					</tr>
																					<tr><td>&nbsp;</td></tr>	
																					<tr>	<td align="left"><s:label value="Application Number" ></s:label></td>
																							<td align="left"> 
																								<s:select name="applicationNumber" 
																										list="listapplicationNumber" 
																										id="listapplicationNumber"																			
																										headerKey="-1"												
																										headerValue="-- Please Select --" onchange="loadSchemeDetails(this)">																			
																								</s:select>
																							</td></tr>	
																					<tr>	
																					<tr><td>&nbsp;</td></tr>	
																						<td align="left"><s:label value="" ></s:label></td>																					
																						<td align="left"> 
																							
																						</td>
																								
																					</tr>
																					<tr>
																					<td align="left"><s:label value="Appointment Type" ></s:label></td>	
																						<td align="left"> 
																							<s:select name="appointmentType" 
																									list="listappointmentType" 
																									id="listappointmentType"																			
																									>																			
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
																						
																							
																					</tr>	
																					<tr><td>&nbsp;</td></tr>																						
																					<tr>
																						<td align="left"><s:label value="Date" ></s:label></td>
																						<td> 
																							<sx:datetimepicker name="appointmentDate" id="appointmentDate" displayFormat="dd/MM/yyyy"  required="true"   />
																						</td>
																					</tr>	
																					<tr>
																						<td>&nbsp;</td>	
																						<td>&nbsp;</td>		
																						<td>&nbsp;</td>																			
																						<td align="left"></td>
																						<td align="left"><s:hidden id="hid_AptDate" name="aprDate"></s:hidden></td>
																						
																								
																					</tr>																					
																					<tr>																						
																						<td align="left"><s:label value="Description" ></s:label></td>
																						<td align="left"> 
																							<s:textarea name="description" id="txtDescription" cols="35" rows="2" ></s:textarea>
																						</td>		
																					</tr>	
																					<tr><td>&nbsp;</td></tr>																						
																					
																					
																					<tr>
																						<td>&nbsp;</td>
																					</tr>
																					<tr>
																						<td id="tdBotton" colspan="3">
																							<s:submit theme="simple"  value="Add" method="addAppointment"  onclick="return addAppointment()" id="btnBill" disabled="false"></s:submit>
																							<s:submit theme="simple"  value="Remove Application" method="removeApplication"  onclick="return removeApplication()" id="btnBill" ></s:submit>
																							
																							
																							<s:reset theme="simple" align="right" value="Clear" id="btnReset" onclick="return clearForm()" />																							
																							
																							
																							<s:submit theme="simple" align="right" value="Exit" method="close" id="btnClose" ></s:submit>
																						</td>
																					</tr>
																				</table>																			
																			</div>	
																			
																			<div style="float:left;width:100%">
																			<table border="0"   border="1"  class="tldFromContent">
																						<tr>
																							<td class="tblMenuHeading">Active Appointments</td>
																						</tr>
																						<tr>
																							<td>
																								<table class="tldFromContent" border="1" width="100%">
												
																									<tr class="tblMenuColor" >
																										<td width="200px"><strong>Commercial Reference</strong></td>
																										
																										<td width="20px"><strong>Allocated By </strong></td>
																										<td width="20px"><strong>Allocated To</strong></td>
																										<td width="20px"><strong>Appointment Date</strong></td>
																										
																										<td width="200px"><strong>Work Scope</strong></td>												 
												
												
																									</tr>
																									<%int i=0; %>
																									<s:iterator value="ativeAppoinmentsList">
																										<tr bgcolor="#CCCCCC">
																											
																											<td><s:property value="referenceNo" /></td>
																										
																										
																											<td  align="right"><s:property value="allocatedBy" /></td>
												
																											<td align="right"><s:property value="allocatedTo" /></td>
												
																											<td  align="right"><s:property value="appointmentDate" /></td>
																										
																											 
																											<td  align="right"><s:property value="description" /></td>
																											 
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
																		</td></tr></table>																			
																					
																					</td></tr>
																				<tr><td>&nbsp;</td></tr>
																				
																		
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