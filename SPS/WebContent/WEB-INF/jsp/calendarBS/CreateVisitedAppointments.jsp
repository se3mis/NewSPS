<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create Visited Appointments</title>
<link rel="stylesheet" type="text/css" href="../../style/style.css" />
<script type="text/javascript" src="../../script/createVisitedAppointments.js"></script>
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
					<!--tr>
						<td></td>
					</tr-->
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
									<td><s:form action="createVisitedAppointments" method="POST" theme="simple">
										<table width="100%" class="tblContent" border="0">
											
											<tbody>		
													<tr>																						
														<td align="left" class="listColumnHeadingStyle"><s:label  value="Allocated To" ></s:label>
															
																<s:select name="appointedUserName" 
																	list="listuserName" 
																	id="listuserName"																			
																	headerKey="-1"												
																	headerValue="-- Please Select --">																			
						    									</s:select>
														</td>		
													</tr>	
													<tr><td></td></tr>
													  <tr align="center">
				                                            <td ><s:label name="statusMsg" id="statusMsg" cssClass="STATUSMSG" ></s:label></td>
				                                      </tr>										
													<tr>
													<td>
													<table width="100%" height="90%" class="tldFromContent" border="0"  >
														
														<tbody>
														
															<tr>
																<td>
																
																<div class="listDivStyleBig" >
																<table width="100%" border="0" align="center"  cellpadding="0" cellspacing="0" style="border-collapse:collapse"  >
																      <tr>
				                                                                        <td align="center" class="listHeader">Application Details
				                                                                        <s:hidden name="list" id="list"></s:hidden>
				                                                                            <s:if test="list.size > 0">
				                                                                                <table id="list_table" width="95%" align="center" border="1" style="border-color:#000033;border-collapse:collapse"   >
				                                                                                    <tr class="listColumnHeadingStyle">
				                                                                                    	<td align="center">Id</td>
				                                                                                        <td align="center">Application No</td>
				                                                                                        <td align="center">Application Date</td>				                                                                                        		
				                                                                                    </tr>
				                                                                                    <%int i=0; %>
				                                                                                    <s:iterator value="list"  id="listColumn">
				                                                                                        <tr id="row_<s:property value="applicationNo"/>" class="listRowStyle">
				                                                                                            <td align="center">
				                                                                                                <s:checkbox fieldValue="%{applicationNo}" name="applicationNumberList" id="X1%{applicationNo}" onclick="setSelectedList(this)" ></s:checkbox>
				                                                                                            </td>
				                                                                                            <td align="left">
				                                                                                                <s:property value="applicationNo" />
				                                                                                            </td>
				                                                                                            <td align="left">
				                                                                                               <s:property value="applicationDate" />
				                                                                                            </td>				                                                                                                                                                                    
				                                                                                        </tr>
				                                                                                        <%i++; %>
				                                                                                    </s:iterator>
				                                                                                </table>
				                                                                            </s:if>
				                                                                        </td>
				                                                                    </tr>	
				                                                                    <tr><td>&nbsp;</td></tr>
				                                                            									
																		</table>
																	</div>
																</td>																
															</tr>
										   			<tr>
														<td id="tdBotton" colspan="3">
														<s:submit theme="simple" value="Create" method="save" id="btnSave" onclick="return validate_form()"></s:submit>														
														<s:reset theme="simple" align="right" value="Clear" onclick="resetCheck()"/>
														<s:submit theme="simple" align="right" value="Exit" method="close" id="btnClose"></s:submit>
														<s:hidden id="state" name="state"></s:hidden>
														<s:hidden id="hiddenSecondState" name="hiddenSecondState"></s:hidden>
														<s:hidden id="path" name="path"></s:hidden>
														<s:hidden id="status" name="status"></s:hidden>
														<s:hidden id="errorMessage" name="errorMessage"></s:hidden>
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
</body>
</html>