<%--
    Document   : Application Form
    Created on : May 16, 2010, 11:21:45 AM
    Author     : Dileepa Waduge
--%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Application Form</title>
<link rel="stylesheet" type="text/css" href="../../style/style.css" />
<script type="text/javascript" src="../../script/applicationForm.js"></script>
<script type="text/javascript" src="../../script/javascript.js"></script>

<s:head />
<sx:head />
</head>
<body onload="buttonLoader()">
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
				                    <td ><img src="../../image/NewSPS2.gif" width="100%"  alt="NewSPS2"/></td>
				                    <td width="*" bgcolor="#000033"></td>
				                    <td width="*" bgcolor="#000033"></td>
				                 </tr>	
							</tbody>
						</table>
						</td>
					</tr>
					<!-- tr>
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
									<td><s:form action="applicationForm" method="POST" validate="true" theme="simple">
										<table width="100%" class="tldFromContent" border="0">
											<thead>
												<tr>
													<th></th>

												</tr>
											</thead>
											<tbody>
												<tr>
													<td>
													<table width="100%" border="0">
														<thead>
															<tr>
																<th width="40%"></th>
																<th width="10%"></th>
																<th width="*"></th>

															</tr>
														</thead>
														<tbody>
															<tr>
																<td class="tdHeadings">APPLICATION FORM CEYLON ELECTRICITY BOARD</td>
																<td></td>
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
														</tbody>

													</table>
													</td>
												</tr>
												<tr>
													<td></td>
												</tr>
												<tr>
													<td></td>
												</tr>
												<tr>
													<td class="tdHeadings" colspan="4">Application Detail</td>
												</tr>
												<tr>
													<td>
													<table width="100%" class="tldFromContent" border="0">
														<thead>
															<tr>
																<th width="90%"></th>
																<th width="*"></th>
															</tr>
														</thead>
														<tbody>
															<tr>
																<td  valign="top">
																<table width="100%" class="tldFromContent" border="0">
																	<thead>
																		<tr>
																			<th width="3%"></th>
																			<th width="18%"></th>
																			<th width="30%"></th>
																			<th width="18%"></th>
																			<th width="25%"></th>
																			<th width="*%"></th>
																		</tr>
																	</thead>
																	<tbody>
																		<tr>
																			<td></td>
																			<td><s:label value="Temp Id"></s:label></td>
																			<td><s:textfield name="applicationId" id="txfdApplicationId" size="25" maxlength="20" onblur="javascript:{this.value = this.value.toUpperCase(); }"></s:textfield><s:submit theme="simple" align="right" value="Find" method="findAppId" id="btnFindAppId" onclick="return checkAppIdNoNotNull()"></s:submit></td>
																			<td><s:label value="Date"></s:label></td>
																			<td><s:textfield name="date" id="txfdDate" size="10"></s:textfield></td>
																			<td></td>
																		</tr>
																	<!-- 	<tr>
																			<td></td>
																			<td><s:label value="Application Type"></s:label></td>
																			<td colspan="3"><s:radio name="applicationType" id="RadioApplicationType" list="#{'NL':'New Line','CR':'Consumer Req','MB':'Mobile Tower'}" value="'NL'" /><s:hidden id="hiddenApplicationType" name="hiddenApplicationType"></s:hidden></td>
																			<td><s:select id="chvJobCategory" name="chvJobCategory" list="#{'NEW':'NEW','AUGMENTATION':'AUGMENTATION','REHABILITATION':'REHABILITATION'}"/></td>
																			<td></td>

																		</tr>
																	-->
																		<tr>
																			<td></td>
																			<td><s:label value="Cost Center No"></s:label></td>
																			<td><s:textfield name="costCenterNo" id="txfdCostCenterNo" size="6" maxlength="6"></s:textfield></td>
																			<td><s:label value="CSC"></s:label></td>
																			<td><s:textfield name="csc" id="txfdCsc" size="25" onkeypress="return onlyLetters(event)"></s:textfield></td>
																			<td></td>
																		</tr>
																		
																		
																		<!-- vdfgdfgfg -->
																		<tr>
																			<td></td>
																			<td><s:label value="Application Type"></s:label></td>
																			<td ><s:radio name="applicationType" id="RadioApplicationType" list="#{'NC':'New Conn'}" value="'NC'" /></td>
																			<td><s:label value="Application SubType"></s:label></td>
																			<td><s:select  name="applicationSubType" id="txfdApplicationSubType"  headerKey="1" list="#{'PM':'Permanent','C1':'Temp Cons1','C2':'Temp Cons2','C3':'Temp Cons3'}" onchange="disableDuration()"/>
																			<s:hidden id="hiddenApplicationType" name="hiddenApplicationType"></s:hidden>
																			</td>
																			
																			
																			<td></td>
																		</tr>
																		<tr>
																			<td></td>
																			<td><s:label value="Duration" id="labelDuration"></s:label></td>
																			<td>
																			<s:select  name="durationType" id="txfdDurationType"  headerKey="1" list="#{'M':'Months','Y':'Years'}"/>
																			<s:textfield name="duration" id="txfdDuration" size="2" maxlength="2"></s:textfield>
																			
																			</td>
																			<td></td>
																			<td></td>
																			<td></td>
																			
																		</tr>
																		<tr>
																			<td></td>
																			<td><s:label value="From Date" id="labelFromDate"></s:label></td>
																			<td><sx:datetimepicker  name="fromDate" id="txfdFromDate" displayFormat="yyyy-MM-dd"  required="true" /></td>
																			<td><s:label value="To Date" id="labelToDate"></s:label></td>
																			<td><sx:datetimepicker  name="toDate" id="txfdToDate" displayFormat="yyyy-MM-dd"  required="true" /></td>
																			<td></td>
																			
																		</tr>
																		<tr>
																			<td></td>
																			<td><s:label value="Application No" id="labelAppReferenceNo"></s:label></td>
																			<td><s:textfield name="appReferenceNo" id="txfdAppReferenceNo" size="25"></s:textfield><s:submit theme="simple" align="right" value="Find No" method="findAppReferenceNo" id="btnFindAppReferenceNo" onclick="return checkAppIdNoNotNull()"></s:submit></td>
																			<td><s:label value="Confirm PIV" id="labelConfirmPIV"></s:label></td>
																			<td><s:radio name="confirmPiv" id="RadioConfirmPiv" list="#{'Y':'Yes','N':'No'}"  /><s:hidden id="hiddenConfirmPiv" name="hiddenConfirmPiv"></s:hidden></td>
																			<td></td>
																		</tr>
																		<tr>
																			<td></td>
																			<td><s:label value="Allocated To" id="labelAllocatedTo"></s:label></td>
																			<td><s:textfield name="allocatedTo" id="txfdAllocatedTo" size="10"></s:textfield><s:textfield name="appointmentDate" id="txfdAppointmentDate" size="10"></s:textfield><s:textfield name="appointmentSession" id="txfdAppointmentSession" size="10"></s:textfield></td>
																			<td></td>
																			<td></td>
																			<td></td>
																		</tr>
																		<tr>
																			<td></td>
																			<td><s:label value="Description"></s:label></td>
																			<td><s:textarea name="description" id="txfdDescription" cols="20" rows="1"></s:textarea></td>
																			<td></td>
																			<td></td>
																			<td></td>
																		</tr>
																		
																	<!-- vdfgdfgfg -->	
																	</tbody>

																</table>
																</td>
																<td valign="top">
																 <!-- <table width="100%" class="tldFromContent" border="0">
																	<thead>
																		<tr>
																			<th width="30%"></th>
																			<th width="*"></th>
																		</tr>
																	</thead>
																	<tbody>
																		<tr>
																			<td><s:label value="Application Type"></s:label></td>
																			<td ><s:radio name="applicationType" id="RadioApplicationType" list="#{'NC':'New Conn'}" value="'NC'" />
																			<s:select  name="applicationSubType" id="txfdApplicationSubType"  headerKey="1" list="#{'PM':'Permanent','C1':'Temp Cons1','C2':'Temp Cons2','C3':'Temp Cons3'}" onchange="disableDuration()"/>
																			<s:hidden id="hiddenApplicationType" name="hiddenApplicationType"></s:hidden>
																			
																			</td>
																		</tr>
																		<tr>
																			<td><s:label value="Duration" id="labelDuration"></s:label></td>
																			<td>
																			<s:select  name="durationType" id="txfdDurationType"  headerKey="1" list="#{'M':'Months','Y':'Years'}"/>
																			<s:textfield name="duration" id="txfdDuration" size="2" maxlength="2"></s:textfield>
																			
																			</td>
																			
																		</tr>
																		<tr>
																			<td><s:label value="From_To" id="labelFromDate"></s:label></td>
																			<td>
																			<sx:datetimepicker  name="fromDate" id="txfdFromDate" displayFormat="yyyy-MM-dd"  required="true" />
																			<sx:datetimepicker  name="toDate" id="txfdToDate" displayFormat="yyyy-MM-dd"  required="true" />
																			</td>
																			
																		</tr>
																		<tr>
																			<td><s:label value="Application No" id="labelAppReferenceNo"></s:label></td>
																			<td><s:textfield name="appReferenceNo" id="txfdAppReferenceNo" size="25"></s:textfield><s:submit theme="simple" align="right" value="Find No" method="findAppReferenceNo" id="btnFindAppReferenceNo" onclick="return checkAppIdNoNotNull()"></s:submit></td>
																		</tr>
																		<tr>
																			<td><s:label value="Allocated To" id="labelAllocatedTo"></s:label></td>
																			<td><s:textfield name="allocatedTo" id="txfdAllocatedTo" size="10"></s:textfield></td>
																		</tr>
																		<tr>
																			<td><s:label value="Confirm PIV" id="labelConfirmPIV"></s:label></td>
																			<td><s:radio name="confirmPiv" id="RadioConfirmPiv" list="#{'Y':'Yes','N':'No'}"  /><s:hidden id="hiddenConfirmPiv" name="hiddenConfirmPiv"></s:hidden></td>
																		</tr>
																		
																	</tbody>

																</table> -->
																
																</td>
															</tr>
															<tr>
																<td></td>
																<td></td>
															</tr>
														</tbody>

													</table>
												
													</td>
												</tr>
												<tr>
													<td class="tdHeadings" colspan="4">Personal Detail</td>
												</tr>
												<tr>
													<td>
													<table width="100%" class="tldFromContent" border="0">
														<thead>
															<tr>
																<th width="90%"></th>
																<th width="*"></th>
															</tr>
														</thead>
														<tbody>
															<tr>
																<td>
																<table width="100%" class="tldFromContent" border="0">
																	<thead>
																		<tr>
																			<th width="3%"></th>
																			<th width="18%"></th>
																			<th width="30%"></th>
																			<th width="18%"></th>
																			<th width="25%"></th>
																			<th width="*%"></th>
																		</tr>
																	</thead>
																	<tbody>
																		<tr>
																			<td></td>
																			<td><s:label value="Id Type"></s:label></td>
																			<td><s:textfield name="idType" readonly="true" size="10" id="idType"></s:textfield></td>
																			<td><s:label value="Id No"></s:label></td>
																			<td><s:textfield name="idNo" size="10" id="txfdIdNo"  onblur="javascript:{this.value = this.value.toLowerCase(); }"></s:textfield><s:submit theme="simple" align="right" value="Find" method="findId" id="btnFindId" onclick="return checkIdNoNotNull()"></s:submit></td>
																			<td></td>
																		</tr>
																		<tr>
																			<td></td>
																			<td><s:label value="First Name (Initials)"></s:label></td>
																			<td><s:textfield name="firstName" id="txfdFirstName" readonly="true" size="25"></s:textfield></td>
																			<td><s:label value="Last Name"></s:label></td>
																			<td><s:textfield name="lastName" id="txfdLastName" readonly="true" size="25"></s:textfield></td>
																			<td></td>
																		</tr>
																		<tr>
																			<td></td>
																			<td><s:label value="Street Address"></s:label></td>
																			<td><s:textarea name="streetAddress" id="txfdStreetAddress" readonly="true" cols="30" rows="1"></s:textarea></td>
																			<td><s:label value="Suburb"></s:label></td>
																			<td><s:textfield name="suburb" id="txfdSuburb" readonly="true" size="20"></s:textfield></td>
																			<td></td>
																		</tr>
																		<tr>
																			<td></td>
																			<td><s:label value="City"></s:label></td>
																			<td><s:textfield name="city" id="txfdCity" readonly="true" size="20"></s:textfield></td>
																			<td><s:label value="Postal Code"></s:label></td>
																			<td><s:textfield name="postalCode" id="txfdPostalCode" readonly="true" size="5" maxlength="5"></s:textfield></td>
																			<td></td>
																		</tr>
																		<tr>
																			<td></td>
																			<td><s:label value="Telephone No"></s:label></td>
																			<td><s:textfield name="telephoneNo" id="txfdTelephoneNo" readonly="true" size="10"></s:textfield></td>
																			<td><s:label value="Mobile No"></s:label></td>
																			<td><s:textfield name="mobileNo" id="txfdMobileNo" readonly="true" size="10"></s:textfield></td>
																			<td></td>
																		</tr>
																		<tr>
																			<td></td>
																			<td><s:label value="Email"></s:label></td>
																			<td><s:textfield name="email" id="txfdEmail" readonly="true" size="30"></s:textfield></td>
																			<td><s:label value="Preferred Language"></s:label></td>
																			<td><s:textfield name="preferefLanguage" id="txfdPreferefLanguage" readonly="true" size="10"></s:textfield></td>
																			<td></td>
																		</tr>
																		<tr>
																			<td></td>
																			<td><s:label value="CEB Employee"></s:label></td>
																			<td><s:textfield name="cebEmployee" id="txfdCebEmployee" readonly="true" size="2"></s:textfield></td>
																			<td><s:label value="Is Applying For a Loan"></s:label><font color="red">***</font></td>
																			<td><s:radio name="isLoanApp" id="RadioIsLoanApp" list="#{'Y':'YES','N':'NO'}" /></td>
																			<td></td>
																		</tr>
																	</tbody>
			
																</table>
																</td>
																<td valign="top">
																<!--<table width="100%" class="tldFromContent" border="0">
																	<thead>
																		<tr>
																			<th width="30%" ></th>
																			<th width="*"></th>
																		</tr>
																	</thead>
																	<tbody>
																		<tr>
																			<td><s:label value="Street Address"></s:label></td>
																			<td><s:textarea name="streetAddress" readonly="true" cols="30" rows="1"></s:textarea></td>
																		</tr>
																		<tr>
																			<td><s:label value="Suburb"></s:label></td>
																			<td><s:textfield name="suburb" readonly="true" size="20"></s:textfield></td>
																		</tr>
																		
																		<tr>
																			<td><s:label value="City"></s:label></td>
																			<td><s:textfield name="city" readonly="true" size="20"></s:textfield></td>
																			
																		</tr>
																		<tr>
																			<td><s:label value="Postal Code"></s:label></td>
																			<td><s:textfield name="postalCode" readonly="true" size="5" maxlength="5"></s:textfield></td>
																		</tr>
																		
																	</tbody>

																</table>-->
																</td>
															</tr>
														</tbody>
													</table>			
																									
													</td>
												</tr>
												<tr>
													<td class="tdHeadings" colspan="4">Land Detail</td>
												</tr>
												<tr>
													<td >
													<table width="100%" class="tldFromContent" border="0">
														<thead>
															<tr>
																<th width="90%"></th>
																<th width="*"></th>
															</tr>
														</thead>
														<tbody>
															<tr>
																<td valign="top">
																<table width="100%" class="tldFromContent" border="0">
																	<thead>
																		<tr>
																			<th width="3%"></th>
																			<th width="18%"></th>
																			<th width="30%"></th>
																			<th width="18%"></th>
																			<th width="25%"></th>
																			<th width="*%"></th>
																		</tr>
																	</thead>
																	<tbody>
																		<tr>
																			<td></td>
																			<td><s:label value="Street Address"></s:label><font color="red">(line 1)</font></td> 
																			<td><s:textarea name="serviceStreetAddress" id="txfdServiceStreetAddress" cols="25" rows="1" onblur="javascript:{this.value = this.value.toUpperCase(); }"></s:textarea></td>
																			<td><s:label value="Suburb"></s:label><font color="red">(line 2)</font></td>
																			<td><s:textfield name="serviceSuburb" id="txfdServiceSuburb" size="20" onblur="javascript:{this.value = this.value.toUpperCase(); }"></s:textfield><s:submit theme="simple" value="Fill" method="fillTheSame" id="btnFillTheSame"></s:submit></td>
																			<td></td>
																		</tr>
																		<s:if test="%{costCenterNo=='541.00' || costCenterNo=='541.10' || costCenterNo=='541.20' || costCenterNo=='542.00' || costCenterNo=='542.10' || costCenterNo=='542.20' || costCenterNo=='547.00' || costCenterNo=='547.10' || costCenterNo=='547.20' || costCenterNo=='548.00' || costCenterNo=='548.10' || costCenterNo=='548.20'}">
																		<tr>
																			<td></td>
																			<td><s:label value="City"></s:label><font color="red">(line 3)</font></td>
																			<td><s:select  name="serviceCity" id="txfdServiceCity"  headerKey="1" list="#{'Colombo_1':'Colombo_1','Colombo_2':'Colombo_2','Colombo_3':'Colombo_3','Colombo_4':'Colombo_4', 'Colombo_5':'Colombo_5','Colombo_6':'Colombo_6','Colombo_7':'Colombo_7','Colombo_8':'Colombo_8', 'Colombo_9':'Colombo_9','Colombo_10':'Colombo_10','Colombo_11':'Colombo_11','Colombo_12':'Colombo_12','Colombo_13':'Colombo_13','Colombo_14':'Colombo_14', 'Colombo_15':'Colombo_15'}" onchange="costcenterChange(this.value)"/></td>
																			<td><s:label value="Postal Code"></s:label></td>
																			<td><s:textfield name="servicePostalCode" id="txfdServicePostalCode" size="5" maxlength="5"  onkeypress="return onlyNumbersWithoutSpaceBar(event)"></s:textfield></td>
																			<td></td>
																		</tr>
																		</s:if >
																		<s:else>
    																	<tr>
																			<td></td>
																			<td><s:label value="City"></s:label><font color="red">(line 3)</font></td> 
																			<td><s:textfield name="serviceCity" id="txfdServiceCity" size="20"    onblur="javascript:{this.value = this.value.toUpperCase(); }"></s:textfield></td>
																			<td><s:label value="Postal Code"></s:label></td>
																			<td><s:textfield name="servicePostalCode" id="txfdServicePostalCode" size="5" maxlength="5" onkeypress="return onlyNumbersWithoutSpaceBar(event)"></s:textfield></td>
																			<td></td>
																		</tr>
																		</s:else>
																		<tr>
																			<td></td>
																			<td></td>
																			<td></td>
																			<td></td>
																			<td></td>
																			<td></td>
																		</tr>
																		
																		<tr>
																			<td></td>
																			<td><s:label value="Assessment No"></s:label></td>
																			<td><s:textfield name="assessmentNo" id="txfdAssessmentNo" size="6" maxlength="6"></s:textfield></td>
																			<td><s:label value="Neighbours Acc. No"></s:label></td>
																			<td><s:textfield name="neighboursAccNo" id="txfdNeighboursAccNo" size="10" maxlength="10" onkeyup="numericValidate(this,event);"></s:textfield></td>
																			<td></td>
																		</tr>
																		<tr>
																			<td></td>
																			<td><s:label value="Ownership"></s:label></td>
																			<td><s:radio name="ownership" id="RadioOwnership" list="#{'O':'Ocupy','R':'Rent'}" /><s:hidden id="hiddenOwnership" name="hiddenOwnership"></s:hidden></td>
																			<td><s:label value="Occupy / Owner Certified"></s:label></td>
																			<td><s:radio name="occupy_OwnerCertified" id="RadioOccupy_OwnerCertified" list="#{'Y':'Yes','N':'No'}" /><s:hidden id="hiddenOccupy_OwnerCertified" name="hiddenOccupy_OwnerCertified"></s:hidden></td>
																			<td></td>
																		</tr>
																		
																		<tr>
																			<td></td>
																			<td><s:label value="Is Governmenent Place"></s:label></td>
																			<td><s:radio name="isGovernmentPlace" id="RadioIsGovernmentPlace" list="#{'Y':'Yes','N':'No'}" /><s:hidden id="hiddenIsGovernmentPlace" name="hiddenIsGovernmentPlace"></s:hidden></td>
																			<td></td>
																			<td></td>
																			<td></td>
																			
																		</tr>
																	</tbody>

																</table>
																</td>
																<td valign="top">
																<!--<table width="100%" class="tldFromContent" border="0">
																	<thead>
																		<tr>
																			<th width="55%"></th>
																			<th width="*"></th>
																		</tr>
																	</thead>
																	<tbody>
																		<tr><td><s:label value="Ownership"></s:label></td>
																			<td><s:radio name="ownership" id="RadioOwnership" list="#{'O':'Ocupy','R':'Rent'}" /><s:hidden id="hiddenOwnership" name="hiddenOwnership"></s:hidden></td>
																			
																		</tr>
																		<tr>
																			<td><s:label value="Occupy / Owner Certified"></s:label></td>
																			<td><s:radio name="occupy_OwnerCertified" id="RadioOccupy_OwnerCertified" list="#{'Y':'Yes','N':'No'}" /><s:hidden id="hiddenOccupy_OwnerCertified" name="hiddenOccupy_OwnerCertified"></s:hidden></td>
																			
																		</tr>
																		<tr>
																			<td><s:label value="Is Governmenent Place"></s:label></td>
																			<td><s:radio name="isGovernmentPlace" id="RadioIsGovernmentPlace" list="#{'Y':'Yes','N':'No'}" /><s:hidden id="hiddenIsGovernmentPlace" name="hiddenIsGovernmentPlace"></s:hidden></td>
																			
																		</tr>
																		<tr>
																			<td></td>
																			<td></td>
																		</tr>
																	</tbody>
																</table>-->		
																</td>
															</tr>	
														</tbody>
													</table> 	
																										
													</td>
												</tr>
												<tr>
													<td class="tdHeadings" colspan="4">Details of Wiring</td>
												</tr>
												<tr>
													<td>
													<table width="100%" class="tldFromContent" border="0">
														<thead>
															<tr>
																<th width="90%"></th>
																<th width="*"></th>
															</tr>
														</thead>
														<tbody>
															<tr>
																<td valign="top">
																<table width="100%" class="tldFromContent" border="0">
																	<thead>
																		<tr>
																			<th width="3%"></th>
																			<th width="18%"></th>
																			<th width="30%"></th>
																			<th width="18%"></th>
																			<th width="25%"></th>
																			<th width="*%"></th>
																		</tr>
																	</thead>
																	<tbody>
																		<tr>
																			<td></td>
																			<td><s:label value="Number of Bullbs"></s:label></td>
																			<td><s:textfield name="noOfBulbs" id="txfdNoOfBulbs" size="3" maxlength="3"  onkeypress="return onlyNumbersWithoutSpaceBar(event)"></s:textfield></td>
																			<td><s:label value="Number of Fans"></s:label></td>
																			<td><s:textfield name="noOfFans" id="txfdNoOfFans" size="3" maxlength="3"  onkeypress="return onlyNumbersWithoutSpaceBar(event)"></s:textfield></td>
																			<td></td>
																		</tr>
																		<tr>
																			<td></td>
																			<td><s:label value="Number of Plughs(5A)"></s:label></td>
																			<td><s:textfield name="noOfPlughs_5A" id="txfdNoOfPlughs_5A" size="3" maxlength="3"  onkeypress="return onlyNumbersWithoutSpaceBar(event)"></s:textfield></td>
																			<td><s:label value="Number of Plughs(15A)"></s:label></td>
																			<td><s:textfield name="noOfPlughs_15A" id="txfdNoOfPlughs_15A" size="3" maxlength="3"  onkeypress="return onlyNumbersWithoutSpaceBar(event)"></s:textfield></td>
																			<td></td>
																		</tr>
																		<tr>
																			<td></td>
																			<td><s:label value="Motors Total(hp/Kw)"></s:label></td>
																			<td><s:textfield name="motorsTotal" id="txfdMotorsTotal" size="3" maxlength="3"  onkeypress="return onlyNumbersWithoutSpaceBar(event)"></s:textfield></td>
																			<td><s:label value="Welding Plant(KVA)"></s:label></td>
																			<td><s:textfield name="weldingPlant" id="txfdWeldingPlant" size="3" maxlength="3"  onkeypress="return onlyNumbersWithoutSpaceBar(event)"></s:textfield></td>
																			<td></td>
																		</tr>
																		<tr>
																			<td></td>
																			<td><s:label value="Metal Crusher(hp/Kw)"></s:label></td>
																			<td><s:textfield name="metalCrusher" id="txfdMetalCrusher" size="3" maxlength="3"  onkeypress="return onlyNumbersWithoutSpaceBar(event)"></s:textfield></td>
																			<td><s:label value="Saw Mills(hp/Kw)"></s:label></td>
																			<td><s:textfield name="sawMills" id="txfdSawMills" size="3" maxlength="3"  onkeypress="return onlyNumbersWithoutSpaceBar(event)"></s:textfield></td>
																			<td></td>
																		</tr>
																		<tr>
																			<td></td>
																			<td></td>
																			<td></td>
																			<td></td>
																			<td></td>
																			<td></td>
																		</tr>
																		<tr>
																			<td></td>
																			<td><s:label value="Phase"></s:label></td>
																			<td><s:radio name="phase" id="RadioPhase" list="#{'1':'1 ph','3':'3 ph'}" /><s:hidden id="hiddenPhase" name="hiddenPhase"></s:hidden></td>
																			<td><s:label value="Connection Type"></s:label></td>
																			<td><s:radio name="connectionType" id="RadioConnectionType" list="#{'15':'15','30':'30','60':'60'}" /><s:hidden id="hiddenConnectionType" name="hiddenConnectionType"></s:hidden></td> 
																			<td></td>
																		</tr>
																		<tr>
																			<td></td>
																			<td><s:label value="Customer Category"></s:label></td>
																			<td><s:select  name="customerCategory" id="txfdCustomerCategory"  headerKey="1" list="#{'PRIV':'Private','GOVE':'Government','SEGO':'Semi_Government','FORE':'Foreign','RELI':'Religious'}"/></td>
																			<td><s:label value="Customer Type"></s:label></td>
																			<td><s:select  name="customerType" id="txfdCustomerType" headerKey="1" list="#{'DOME':'Domestic','CONS':'Construction','SHOP':'Shop','INDT':'Industrial','HOTE':'Hotel','GARM':'Garment','FORC':'Forces','TEMP':'Temple','CHUR':'Church'}" onchange="tariffChooser()"/></td>
																			<td></td>
																		</tr>
																		<tr>
																			<td></td>
																			<td><s:label value="Tariff Category Code"></s:label></td>
																			<td ><s:select name="tariffCategoryCode" id="txfdTariffCategoryCode" list="listTariffCatCode" onchange="tariffChooser2()"></s:select></td>
																			<td><s:label value="Tariff Code"></s:label></td>
																			<td ><s:select name="tariffCode" id="txfdTariffCode" list="listTariffCode"></s:select></td>
																			<td></td>
																		</tr>
																		
																		
																		
																	</tbody>

																</table>
																</td>
																<td valign="top">
																<!-- <table width="100%" class="tldFromContent" border="0">
																	<thead>
																		<tr>
																			<th width="55%"></th>
																			<th width="*"></th>
																			<th width="5"></th>
																			
																		</tr>
																	</thead>
																	<tbody>
																		<tr>
																			<td><s:label value="Phase"></s:label></td>
																			<td><s:radio name="phase" id="RadioPhase" list="#{'1':'1 ph','3':'3 ph'}" /><s:hidden id="hiddenPhase" name="hiddenPhase"></s:hidden></td>
																			<td></td>
																		</tr>
																		<tr>
																			<td><s:label value="Connection Type"></s:label></td>
																			<td><s:radio name="connectionType" id="RadioConnectionType" list="#{'15':'15','30':'30','60':'60'}" /><s:hidden id="hiddenConnectionType" name="hiddenConnectionType"></s:hidden></td> 
																			<td></td>
																		</tr>
																		<tr>
																			<td colspan="3">
																			<table width="100%" class="tldFromContent" border="0">
																				<thead>
																					<tr>
																						<th width="30%"></th>
																						<th width="10%"></th>
																						<th width="35%"></th>
																						<th width="15%"></th>
																						<th width="*"></th>
																					</tr>
																				</thead>
																				<tbody>
																					<tr>
																						<td><s:label value="Tariff Category Code"></s:label></td>
																						<td align="right"><s:select name="tariffCategoryCode" id="txfdTariffCategoryCode" list="listTariffCatCode"></s:select></td>
																						<td><s:label value="Tariff Code"></s:label></td>
																						<td align="right"><s:select name="tariffCode" id="txfdTariffCode" list="listTariffCode"></s:select></td>
																						<td></td>
																					</tr>
																					<tr>
																						<td><s:label value="Customer Category"></s:label></td>
																						<td><s:select  name="customerCategory" id="txfdCustomerCategory"  headerKey="1" list="#{'PRIV':'Private','GOVE':'Government','SEGO':'Semi_Government','FORE':'Foreign','RELI':'Religious'}"/></td>
																						<td><s:label value="Customer Type"></s:label></td>
																						<td><s:select  name="customerType" id="txfdCustomerType" headerKey="1" list="#{'DOME':'Domestic','SHOP':'Shop','HOTE':'Hotel','GARM':'Garment','FORC':'Forces','TEMP':'Temple','CHUR':'Church'}"/></td>
																						<td></td>
																					</tr>
																				</tbody>
																			</table>		
																			</td>
																			
																		</tr>
																																																					
																	</tbody>

																</table>-->
																
																</td>
															</tr>
														</tbody>
													</table>		
													
													</td>
												</tr>
												<tr>
													<td class="tdHeadings" colspan="4">Other Details</td>
												</tr>
												<tr>
													<td>
													<table width="100%" class="tldFromContent" border="0">
														<thead>
															<tr>
																<th width="5%"></th>
																<th width="15%"></th>
																<th width="10%"></th>
																<th width="15%"></th>
																<th width="*"></th>
																<th width="5%"></th>
															</tr>
														</thead>
														<tbody>
															<tr>
																<td></td>
																<td><s:label value="Prepared By"></s:label></td>
																<td><s:textfield name="preparedBy" id="txfdPreparedBy" readonly="true"
																	size="10"></s:textfield></td>
																<td><s:label value="Confirmed By"></s:label></td>
																<td><s:textfield name="confirmedBy"
																	id="txfdConfirmedBy" size="10"></s:textfield></td>
																<td></td>
															</tr>

														</tbody>

													</table>
													</td>
												</tr>
												<tr>
													<td id="tdBotton" colspan="3"><s:submit theme="simple" value="Save" method="save" id="btnSave" 	onclick="return validate_form()"></s:submit> 
													<s:submit theme="simple" align="right" value="Modify" method="modify" id="btnModify" onclick="return validate_form()"></s:submit> 
													<!--<s:submit theme="simple" align="right" value="Next" method="next" id="btnNext" onclick="return validate_form()"></s:submit>-->
													<s:submit theme="simple" align="right" value="Issue PIV" method="issuePiv" id="btnIssuePiv"></s:submit> 
													<s:if test="%{costCenterNo=='541.00' || costCenterNo=='542.00' || costCenterNo=='547.00' || costCenterNo=='548.00'}">
														<s:submit theme="simple" align="right" value="Generate" method="confirmCC" id="btnConfirm" onclick="return validate_form()"></s:submit>
													</s:if>
													<s:else>
														<s:submit theme="simple" align="right" value="Generate" method="confirm" id="btnConfirm" onclick="return validate_form()"></s:submit>  
													</s:else>
													<s:submit theme="simple" align="right" value="Allocate" method="allocate" id="btnAllocate" onclick="return checkAllocatedToNotNull()"></s:submit> 
													<applet codebase="../../PrintLib" code="applicationPrint.ApplicationFormApp" archive="PrintJar.jar" name="ApplicationApplet" width="1" height="1"  >
													</applet>
													<s:if test="%{state=='print'}">
													<s:submit theme="simple" align="right" value="Print" id="btnPrint" onclick="printApplication()" type="button"></s:submit> 
													</s:if>
													<s:if test="%{state=='changeServiceAdd'}">
													<s:submit theme="simple" align="right" value="Change Service Add " id="btnChangeServiceAdd" method="changeServiceAdd"></s:submit> 
													</s:if>
													<s:if test="%{state=='alterLoanApp'}">
													<s:submit theme="simple" align="right" value="Alter Loan App " id="btnAlterLoanApp" method="alterLoanApp"></s:submit> 
													</s:if>
													<s:submit theme="simple" align="right" value="Exit" method="close" id="btnClose"></s:submit> 
													<s:reset theme="simple" align="right" value="Clear" /> 
													<s:hidden id="state" name="state"></s:hidden>
													<s:hidden id="hiddenSecondState" name="hiddenSecondState"></s:hidden>
													<s:hidden id="path" name="path"></s:hidden> 
													<s:hidden id="status" name="status"></s:hidden> 
													<s:hidden id="errorMessage" name="errorMessage"></s:hidden></td>
													

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


			</td>
		</tr>
	</tbody>
</table>


</body>
</html>