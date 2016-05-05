<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Diary Entry</title>
<link rel="stylesheet" type="text/css" href="../../style/style.css" />
<script type="text/javascript" src="../../script/editEntry.js"></script>
<s:head  />
<sx:head />
</head>
<body onload="on_Load()">
				<table class="tblContent" border="1" cellpadding="0"
							cellspacing="0">
							
							<tbody>
								<tr>
									<td><s:form action="editEntryBS" method="POST" theme="simple">
										<table width="100%" class="tldFromContent" border="0">
											<tbody>	
															<tr>
																<td>	
																	<div class="listDivStyleBig" >
																			<table width="100%" border="0" align="left" bgcolor="#FBFFFF" border="1" style="border-color:#000033;border-collapse:collapse" class="tldFromContent" >
																			<thead>
																				<tr>
																					<th width="15%"></th>
																					<th width="20%"></th>
																					<th width="10%"></th>
																					<th width="5%"></th>
																					<th width="5%"></th>
																									
																				</tr>
																			</thead>
																			        <tr >
																					 <td align="left" class="listHeader">Edit</td>
																					 <td align="left"><s:label name="statusMsg" id="statusMsg" cssClass="STATUSMSG" ></s:label></td>
																					</tr>
																						<tr><td>&nbsp;</td></tr>
																					<tr>																						
																						<td align="left"><s:label value="To" ></s:label></td>
																						<td align="left"> 
																							<s:select name="appointedUserName" 
																									list="listuserName" 
																									id="listuserName"																			
																									headerKey="-1"												
																									headerValue="-- Please Select --">																			
						    																</s:select>
																						</td>		
																					</tr>
																						
																					<tr>
																						<td align="left"><s:label value="Appt Type" ></s:label></td>	
																						<td align="left"> 
																							<s:select name="appointmentType" 
																									list="listappointmentType" 
																									id="listappointmentType"																			
																									headerKey="-1"												
																									headerValue="-- Please Select --">																			
						    																</s:select>
																						</td>
																					</tr>
																					
																					<tr>																						
																						<td align="left"><s:label value="Date" ></s:label></td>
																						<td> 
																							<sx:datetimepicker name="appointmentDate" id="appointmentDate" displayFormat="dd/MM/yyyy"  required="true"   />
																						</td>		
																					</tr>
																					<tr>
																						<td></td>																						
																						<td align="left"><s:label value="dd/mm/yyyy" ></s:label></td>
																						<td align="left"><s:hidden id="hid_AptDate" name="aprDate"></s:hidden></td>
																						<td align="left"><s:hidden id="hid_passedCostCen" name="passedCostCen"></s:hidden></td>
																					</tr>
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
																					</tr>																																										
																					<tr>																						
																						<td align="left"><s:label value="Description" ></s:label></td>
																						<td align="left"> 
																							<s:textarea name="description" id="txtDescription" cols="35" rows="2" ></s:textarea>
																						</td>		
																					</tr>																				
																					<tr>
																						<td>&nbsp;</td>
																					</tr>
																					<tr>
																						<td id="tdBotton" colspan="3">
																							<s:submit theme="simple"  value="Update" method="editAppointment"  onclick="return addAppointment()" id="btnEdit" disabled="false"></s:submit>
																							<s:submit theme="simple" align="right" value="Exit" id="btnClose" onclick="return closeForm()" ></s:submit>
																							<s:hidden id="hid_appontId" name="appontId"></s:hidden>																							
																						</td>
																					</tr>
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
</body>
</html>