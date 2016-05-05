<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Diary Entry</title>
<link rel="stylesheet" type="text/css" href="../../style/style.css" />
<script type="text/javascript" src="../../script/diaryEntry.js"></script>
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
									<td><s:form action="diaryEntry" method="POST" theme="simple">
										<table width="100%" class="tldFromContent" border="0">
											
											<tbody>												
												<tr>
													<td>
													<table width="100%" height="90%" class="tblContent" border="0"  >
														
														<tbody>
															<tr>
																<td>	
																	<div class="listDivStyleBig" >
													<table width="100%" border="0" align="left"  border="1" style="border-color:#000033;border-collapse:collapse"  class="tldFromContent">
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
																						
																					<tr>	
																						<td align="left"><s:label value="Cost Centre" ></s:label></td>
																							<td align="left"><s:textfield name="selCostCenter" disabled="true"></s:textfield></td>																						
																						<td align="left"><s:label value="Allocated To" ></s:label></td>
																						<td align="left"> 
																							<s:select name="appointedUserName" 
																									list="listuserName" 
																									id="listuserName"																			
																									headerKey="-1"												
																									headerValue="-- Please Select --">																			
						    																</s:select>
																						</td>		
																					</tr>
																					<tr><td>&nbsp;</td></tr>	
																					<tr>																						
																						<td align="left"><s:label value="Application Number" ></s:label></td>
																						<td align="left"> 
																							<s:select name="applicationNumber" 
																									list="listapplicationNumber" 
																									id="listapplicationNumber"																			
																									headerKey="-1"												
																									headerValue="-- Please Select --">																			
						    																</s:select>
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
																						<td align="left"><s:label value="Session" ></s:label></td>
																						<td align="left"> 
																							<s:select name="sessionName" 
																									list="listsessionName" 
																									id="listsessionName"																			
																									headerKey="-1"												
																									headerValue="-- Please Select --">																			
						    																</s:select>
																						</td>	
																						<td align="left"><s:label value="Date" ></s:label></td>
																						<td> 
																							<sx:datetimepicker name="appointmentDate" id="appointmentDate" displayFormat="dd/MM/yyyy"  required="true"   />
																						</td>
																						<td><input value="Find" width="20px" height="5px" id="btnFindDate" type="button" onclick="return findAppointmentForDate()" ></td >   	
																					</tr>																					
																					<tr>
																						<td>&nbsp;</td>
																					</tr>	
																					<tr>
																						<td>&nbsp;</td>	
																						<td>&nbsp;</td>		
																						<td>&nbsp;</td>																			
																						<td align="left"><s:label value="dd/mm/yyyy" ></s:label></td>
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
																							<s:reset theme="simple" align="right" value="Clear" id="btnReset" onclick="return clearForm()" />																							
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