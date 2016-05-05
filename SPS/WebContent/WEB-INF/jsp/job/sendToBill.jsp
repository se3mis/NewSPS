<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<html>
<head>
<sx:head />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SEND TO BILLING SYSTEM</title>
<link rel="stylesheet" type="text/css" href="../../style/style.css" />
<script type="text/javascript" src="../../script/job.js"> </script>
<script type="text/javascript" src="../../script/javascript.js"> </script>
<script type="text/javascript" src="../../script/sendToBilling.js"> </script>
</head>

<body onload="javascript:formElementsDisplay()" >
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
				                                         <td ><img src="../../image/NewSPS2.gif" width="100%"  alt="NewSPS2"/>
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
					<tr>
						<td id="tdContent">
						<table class="tblContent" border="0" cellpadding="0"
							cellspacing="0">
							<thead>
								<tr>
									<th></th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td><s:form id="frmEstimate" name="frmEstimate" action="SendToBill" method="POST"
										validate="true" theme="simple">
										<table width="75%" class="tldFromContent" border="0">
											<thead>
												<tr>
													<th></th>

												</tr>
											</thead>
											<tbody>												
												<tr>
													<td align="center">
				                                                                                               
													<!--<td class="tdMsgStyle"><s:property value="errorMessage" ></s:property></td> -->
													<s:if test="%{messageType=='ERROR'}">
													<td class="tdMsgStyle"><s:property value="errorMessage"></s:property></td>
													</s:if>
													<s:elseif test="%{messageType=='INFO'}">
													<td class="tdMsgStyleInfo"><s:property value="errorMessage"></s:property></td>
													</s:elseif>
													<s:elseif test="%{messageType=='DONE'}">
													<td class="tdMsgStyleDone"><s:property value="errorMessage"></s:property></td>
													</s:elseif>
													<s:else>
													<td class="tdMsgStyleInfo"><s:property value="errorMessage"></s:property></td>	
													</s:else>
												</tr>
																	
												
												

												
												<tr>
													<td colspan="2" class="tdHeadings"></td>
												</tr>
												<tr>
												<s:hidden name="costCenterNo"></s:hidden>
													<td colspan="2">
														<table  width="100%">
															<tr>
															<td width="5">&nbsp;</td>
															<td class="tdHeadings">Jobs to be sent to Billing System. Please select job nos.(5 at a time)</td>
														</tr>
															<tr>
																<td width="5">&nbsp;</td>
																<td>
																<!-- Jobs display -->
																
																	<table width="100%">
																		<tr>
																			<td>
																				<s:if test="jobList!=null">
																					<table class="tldFromContent"  border="1" width="100%">
																						<tr height="20">
																							<!--  <th>Check.</th>-->
																							<th>Job No.</th>
																							<th>SMC Type</th>
																							<th>Customer Name</th>
																							<th>Address</th>
																							<th>NIC No.</th>
																							<th>Application Date</th>
																							<th>Application No.</th>
																							<th>Temp ID(ANC).</th>
																						</tr>
																						<%int i=0; %> 
																						<s:iterator value="jobList" status="jobStatus">
																							<tr height="20">
																								<s:hidden name="jobNoList" value="%{projectNo}"></s:hidden>
																								<td align="center">
				                                                                                     <s:checkbox fieldValue="%{projectNo}" name="selectedProjectNoList" id="X1%{projectNo}" onclick="setSelectedList(this)" ></s:checkbox>
				                                                                                </td>
																								<s:url id="page_url" namespace="/job/web" action="smcCard">
																								<s:param name="jobNo" value="%{projectNo}" />
																								<s:param name="state" value="%{'billingClerk'}" />
                                                                								<!--<s:param name="costCenter" value="%{id.DeptId}" />-->
																								</s:url>
																								<td align="right" id="tdprojectNo_<%= i %>"><s:a href="%{page_url}"><s:property value="projectNo" /></s:a></td>
																								<!-- <td><s:property value="projectNo" /></td> -->
																								<td><s:property value="applicationType" /></td>
																								<td><s:property value="firstName" /> <s:property value="lastName" /></td>
																								<td><s:property value="streetAddress" /> <s:property value="suburb" /> <s:property value="city" /></td>
																								<td><s:property value="idNo" /></td>
																								<td><s:date name="submitDate" format="dd/MM/yyyy" /></td>
																								<td><s:property value="estimateNo" /></td>
																								<s:url id="page_url" namespace="/application/web" action="changeServiceAdd"> 
																								<s:param name="applicationId" value="%{applicationId}" />
																								<s:param name="state" value="%{'billingClerk'}" />
																								</s:url>
																								<td align="right" id="tdapplicationId_<%= i %>"><s:a href="%{page_url}"><s:property value="applicationId" /></s:a></td>
																								<!-- <td><s:property value="applicationId" /></td>-->
																							</tr>
																							<%i++; %>
																						</s:iterator>
	
																						
	
	
																					</table>
																				</s:if>
																				<s:else>
																					No jobs to be sent to Billing System currently.
																				</s:else>
																			</td>
																		</tr>
																	</table>
																</td>
															</tr>
														</table>
													</td>
												</tr>
												<tr>
												<td colspan="2" class="tdHeadings">
													&nbsp;
												</td>
												</tr>
												<tr>
													<td colspan="2">
														<table>
															<tr>
																<td width="5"></td>
																<td>
																	<s:if test="jobList!=null">
																		<s:if test="%{loggedInUserLevel!='DEO' && loggedInUserLevel!='ES'}"> 
																		 <s:submit theme="simple" value="Send To Bill" method="export" id="btnSave" onclick="return validate_form()"></s:submit>
																		</s:if>
																	</s:if>
																	<s:else>
																		
																		<s:submit theme="simple" value="Export" method="export" id="btnSave" disabled="true"></s:submit>
																		
																	</s:else>
																	
																	<s:submit theme="simple" value="Exit" method="close" id="btnClose" ></s:submit>
																	<s:hidden id="state" name="state"></s:hidden>
																</td>
															</tr>
														</table>
													</td>
												</tr>
												<tr>
												<td colspan="2">
													
												</td>
												</tr>
												
												<tr>
													<td class="tdHeadings" colspan="4">&nbsp;</td>
												</tr>
												

												


												


												<tr>
													<td id="tdBotton2" colspan="3">&nbsp;</td>
												</tr>

												
												
												<tr>
													<td id="tdBotton" colspan="3">
														
													</td>
											</tbody>
										</table>

									</s:form></td>
								</tr>
								
								
							</tbody>
						</table>

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
</html>