<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Activate Failed</title>
<link rel="stylesheet" type="text/css" href="../../style/style.css" />
<script type="text/javascript" src="../../script/calendar.js"></script>
<s:head  />
<sx:head />
<script type="text/javascript">
dojo.event.topic.subscribe("/value", function(textEntered, date, widget){

	if(document.getElementById("hid_AptDate"))
		document.getElementById("hid_AptDate").value=textEntered;

	document.forms[0].submit(); 

});
</script>

</head>
<body onunload="unLoad()" onload="on_Load()">
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
									<td><s:form action="activeFailed" method="POST" theme="simple">
										<table width="100%" class="tldFromContent" border="0">
											
											<tbody>												
												<tr>
													<td>
													<table width="100%" height="50%" class="tblContent" border="0"  >
														
														<tbody>
															<tr>
																<td>
																<div class="listDivStyleBig" >
																<table width="100%" border="0" align="center"  cellpadding="0" cellspacing="0" style="border-collapse:collapse" class="tldFromContent" >
																	<tr>
																		<td class="divTex"> 
																			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<s:textfield name="selectedDate" id="selectedDate" size="20" disabled="true" ></s:textfield>	
																		</td>		
																	</tr>
																	<tr class="divTex">																																									
																			<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<s:label value="Selected Date" ></s:label></td>
																			
																								
																	</tr>
																	<tr>
																		<td> 
																			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<sx:datetimepicker name="appointmentDate" id="appointmentDate"  displayFormat="dd/MM/yyyy" valueNotifyTopics="/value" />
																		</td>		
																	</tr>
																	<tr>																																									
																			<td align="left" class="divTex">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<s:label value="dd/mm/yyyy" ></s:label></td>
																			<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<s:hidden id="hid_AptDate" name="aprDate"></s:hidden></td>
																			<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<s:hidden id="hid_FrmFind" name="hid_FrmFind"></s:hidden></td>
																			<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<s:hidden id="hid_UserRole" name="hid_UserRole"></s:hidden></td>																																														
																								
																	</tr>
																      <tr>
				                                                                        <td align="center"class="listHeader">Diary Schedules
				                                                                        
				                                                                                <table id="list_table" width="95%" align="center" border="1" style="border-color:#000033;border-collapse:collapse"   >
				                                                                                    <tr class="listColumnHeadingStyle">
				                                                                                    	<td align="center">Check</td>
				                                                                                    	<td align="center">Id</td>
				                                                                                    	<td align="center">Application No</td>
				                                                                                    	<td align="center">Name</td>
				                                                                                        <td align="center">Description</td>
				                                                                                        <td align="center">Time</td>
				                                                                                        <td align="center">Cost Center</td>
				                                                                                        <td align="center">Appt Type</td>
				                                                                                        <td align="center">Status</td>
				                                                                                    </tr>
				                                                                                    <%int i=0; %>
				                                                                                    <s:iterator value="list"  id="listColumn">
				                                                                                        <tr  class="listRowStyle">	
				                                                                                        	<td align="center">
				                                                                                                <s:checkbox fieldValue="%{scheduleNo}" name="appointmentNumberList" id="%{scheduleNo}" onclick="setSelectedList(this)" ></s:checkbox>
				                                                                                            </td>			                                                                                          
				                                                                                            <td  align="left">
				                                                                                                <s:property value="scheduleNo" />
				                                                                                            </td>
				                                                                                            <td id="rowAppNo_<s:property value="scheduleNo"/>" align="left">
				                                                                                               <s:property value="applicationNo" />
				                                                                                            </td>
				                                                                                            <td id="rowName_<s:property value="scheduleNo"/>" align="left">
				                                                                                               <s:property value="appointUserName" />
				                                                                                            </td>
				                                                                                            <td id="rowDes_<s:property value="scheduleNo"/>" align="left">
				                                                                                               <s:property value="description" />
				                                                                                            </td>
				                                                                                            <td id="rowTim_<s:property value="scheduleNo"/>" align="left">
				                                                                                               <s:property value="time" />
				                                                                                            </td >  
				                                                                                             <td id="rowCosCen_<s:property value="scheduleNo"/>" align="left">
				                                                                                               <s:property value="selectedcostCentre" />
				                                                                                            </td >
				                                                                                            <td id="rowAptType_<s:property value="scheduleNo"/>" align="left">
				                                                                                               <s:property value="appointmentType" />
				                                                                                            </td > 
				                                                                                             <td id="rowStatus_<s:property value="scheduleNo"/>" align="center">
				                                                                                               <s:property value="appointmentStatus" />
				                                                                                            </td >                                                                       
				                                                                                        </tr>
				                                                                                        <%i++; %>
				                                                                                    </s:iterator>
				                                                                                </table>				                                                                          
				                                                                        </td>
				                                                                    </tr>											
																		</table>
																	</div>
																</td>																
															</tr>
															<tr>
																<td id="tdBotton" colspan="3">															
<!--																	<s:submit theme="simple" align="right" value="Remove" method="remove" id="btnRemove" onclick="return validate_Remove()"></s:submit>-->
																	<s:submit theme="simple" align="right" value="Edit" id="btnEdit"  onclick="return validate_Edit()"  disabled="#{Calendar.disable1}"></s:submit>
																	<s:submit theme="simple" align="right" value="Change Status" id="btnChangeStatus" onclick="return ChangeStatus_Edit()"></s:submit>
																	<input value="Add new" width="20px" height="5px" id="btnAddNew" type="button" onclick="return addNewAppointment()" >
																	<s:reset theme="simple" align="right" value="Clear" onclick="resetCheck()"/>
																	<s:submit theme="simple" align="right" value="Exit" method="close" id="btnClose" ></s:submit>
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
									<td class="footerNotice" >@ Ceylon Electricity Board</td>
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