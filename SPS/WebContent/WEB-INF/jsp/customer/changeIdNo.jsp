<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/jsp/include.jsp"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Change ID Number</title>
<link rel="stylesheet" type="text/css" href="../../style/style.css" />
<script type="text/javascript" src="../../script/changeIdNumber.js"> </script>
<script type="text/javascript" src="../../script/javascript.js"> </script>

<s:head />
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
						<table class="tblContent" border="1" cellpadding="0"
							cellspacing="0">
							<thead>
								<tr>
									<th></th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td><s:form name="changeIdNo" action="changeIdNoBS" method="POST"
										validate="true" theme="simple">
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
																<td class="tdHeadings">CHANGE APPLICATION CUSTOMER ID NO</td>
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
													<td class="tdHeadings" colspan="4">Consumer Detail</td>
												</tr>
												<tr>
													<td>
													<table width="100%" class="tldFromContent" border="0" >
														<thead>
															<tr>
																<th width="3%"></th>
																<th width="40%"></th>
																<th width="40%"></th>
																<th width="*%"></th>
															</tr>
														</thead>
														<tbody>
															<tr>
																<td></td>
																<td><s:label value="Cost Center No"></s:label>
																	<s:textfield name="costCenterNo" id="txfdCostCenterNo" size="6" maxlength="6" readonly="true" cssClass="inputArea" ></s:textfield></td>
																<td></td>
																<td></td>
															</tr>
															<tr>
																<td></td>
																<td><s:label value="Application Id"></s:label>
																	<s:textfield name="applicationId" id="txfdApplicationId" size="20" maxlength="20" onblur="javascript:{this.value = this.value.toUpperCase(); }"></s:textfield><s:submit theme="simple" align="right" value="Find" method="findAppId" id="btnFindAppId" onclick="return checkAppIdNoNotNull()"></s:submit></td>
																<td></td>
																<td></td>
															</tr>
														   <tr>
																<td></td>
																<td><table width="100%" class="tldFromContent" border="0" >
																	<thead>
																		<tr>
																			<th width="3%"></th>
																			<th width="40%"></th>
																			<th width="40%"></th>
																			<th width="*%"></th>
																		</tr>
																	</thead>
																	<tbody>
																		<tr>
																			<td></td>
																			<td colspan="2" class="tdHeadings">Old Customer Details</td>
																			<td></td>
																			<td></td>
																		</tr>
																		<tr>
																			<td></td>
																			<td><s:label value="Id No"></s:label></td>
																			<td><s:textfield name="idNo" readonly="true" size="25"></s:textfield></td>
																			<td></td>
																		</tr>
																		<tr>
																			<td></td>
																			<td><s:label value="First Name (Initials)"></s:label></td>
																			<td><s:textfield name="firstName" readonly="true" size="25"></s:textfield></td>
																			<td></td>
																		</tr>
																		<tr>
																			<td></td>
																			<td><s:label value="Last Name"></s:label></td>
																			<td><s:textfield name="lastName" readonly="true" size="25"></s:textfield></td>
																			<td></td>
																		</tr>
																		<tr>
																			<td></td>
																			<td><s:label value="Street Address"></s:label></td>
																			<td><s:textarea name="streetAddress" readonly="true" cols="30" rows="1"></s:textarea></td>
																			<td></td>
																		</tr>
																		<tr>
																			<td></td>
																			<td><s:label value="Suburb"></s:label></td>
																			<td><s:textfield name="suburb" readonly="true" size="20"></s:textfield></td>
																			<td></td>
																		</tr>
																		<tr>
																			<td></td>
																			<td><s:label value="City"></s:label></td>
																			<td><s:textfield name="city" readonly="true" size="20"></s:textfield></td>
																			<td></td>
																		</tr>
																		<tr>
																			<td></td>
																			<td><s:label value="Postal Code"></s:label></td>
																			<td><s:textfield name="postalCode" readonly="true" size="5" maxlength="5"></s:textfield></td>
																			<td></td>
																		</tr>
																		<tr>
																			<td></td>
																			<td><s:label value="Telephone No"></s:label></td>
																			<td><s:textfield name="telephoneNo" readonly="true" size="10"></s:textfield></td>
																			<td></td>
																		</tr>
																		<tr>
																			<td></td>
																			<td><s:label value="Mobile No"></s:label></td>
																			<td><s:textfield name="mobileNo" readonly="true" size="10"></s:textfield></td>
																			<td></td>
																		</tr>
																		<tr>
																			<td></td>
																			<td><s:label value="Email"></s:label></td>
																			<td><s:textfield name="email" readonly="true" size="30"></s:textfield></td>
																			<td></td>
																		</tr>
																		<tr>
																			<td></td>
																			<td><s:label value="Preferef Language"></s:label></td>
																			<td><s:textfield name="preferefLanguage" readonly="true" size="10"></s:textfield></td>
																			<td></td>
																		</tr>
																		<tr>
																			<td></td>
																			<td><s:label value="CEB Employee"></s:label></td>
																			<td><s:textfield name="cebEmployee" readonly="true" size="2"></s:textfield></td>
																			<td></td>
																		</tr>
																		<tr>
																			<td></td>
																			<td><s:label value="Is Applying For a Loan"></s:label></td>
																			<td><s:radio name="isLoanApp" id="RadioIsLoanApp" list="#{'Y':'YES','N':'NO'}" /></td>
																			<td></td>
																		</tr>
																						
																	</tbody>
			
																</table></td>
																<td><table width="100%" class="tldFromContent" border="0" >
																	<thead>
																		<tr>
																			<th width="3%"></th>
																			<th width="40%"></th>
																			<th width="40%"></th>
																			<th width="*%"></th>
																		</tr>
																	</thead>
																	<tbody>
																	<tr>
																			<td></td>
																			<td colspan="2" class="tdHeadings">New Customer Details</td>
																			<td></td>
																			<td></td>
																			
																	</tr>
																	
																	<tr>
																			<td></td>
																			<td><s:label value="ID No"></s:label></td>
																			<td><s:textfield name="idNoNew" id="txfdIdNoNew" size="10" maxlength="10" cssClass="inputArea" onblur="javascript:{this.value = this.value.toLowerCase(); }"></s:textfield><s:submit theme="simple" align="right" value="Find" method="findIdNo" id="btnFindIdNo" onclick="return checkIdNoNotNull()"></s:submit></td>
																			<td></td>
																		</tr>
																	<tr>
																			<td></td>
																			<td><s:label value="First Name (Initials)"></s:label></td>
																			<td><s:textfield name="firstNameNew" readonly="true" size="25"></s:textfield></td>
																			<td></td>
																		</tr>
																		<tr>
																			<td></td>
																			<td><s:label value="Last Name"></s:label></td>
																			<td><s:textfield name="lastNameNew" readonly="true" size="25"></s:textfield></td>
																			<td></td>
																		</tr>
																		<tr>
																			<td></td>
																			<td><s:label value="Street Address"></s:label></td>
																			<td><s:textarea name="streetAddressNew" readonly="true" cols="30" rows="1"></s:textarea></td>
																			<td></td>
																		</tr>
																		<tr>
																			<td></td>
																			<td><s:label value="Suburb"></s:label></td>
																			<td><s:textfield name="suburbNew" readonly="true" size="20"></s:textfield></td>
																			<td></td>
																		</tr>
																		<tr>
																			<td></td>
																			<td><s:label value="City"></s:label></td>
																			<td><s:textfield name="cityNew" readonly="true" size="20"></s:textfield></td>
																			<td></td>
																		</tr>
																		<tr>
																			<td></td>
																			<td><s:label value="Postal Code"></s:label></td>
																			<td><s:textfield name="postalCodeNew" readonly="true" size="5" maxlength="5"></s:textfield></td>
																			<td></td>
																		</tr>
																		<tr>
																			<td></td>
																			<td><s:label value="Telephone No"></s:label></td>
																			<td><s:textfield name="telephoneNoNew" readonly="true" size="10"></s:textfield></td>
																			<td></td>
																		</tr>
																		<tr>
																			<td></td>
																			<td><s:label value="Mobile No"></s:label></td>
																			<td><s:textfield name="mobileNoNew" readonly="true" size="10"></s:textfield></td>
																			<td></td>
																		</tr>
																		<tr>
																			<td></td>
																			<td><s:label value="Email"></s:label></td>
																			<td><s:textfield name="emailNew" readonly="true" size="30"></s:textfield></td>
																			<td></td>
																		</tr>
																		<tr>
																			<td></td>
																			<td><s:label value="Preferef Language"></s:label></td>
																			<td><s:textfield name="preferefLanguageNew" readonly="true" size="10"></s:textfield></td>
																			<td></td>
																		</tr>
																		<tr>
																			<td></td>
																			<td><s:label value="CEB Employee"></s:label></td>
																			<td><s:textfield name="cebEmployeeNew" readonly="true" size="2"></s:textfield></td>
																			<td></td>
																		</tr>
																		<tr>
																			<td></td>
																			<td><s:label value="Is Applying For a Loan"></s:label></td>
																			<td><s:radio name="isLoanAppNew" id="RadioIsLoanAppNew" list="#{'Y':'YES','N':'NO'}" /></td>
																			<td></td>
																		</tr>
																							
																	</tbody>
			
																</table></td>
																<td></td>
																
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
													<td id="tdBotton" colspan="3">
													<s:submit theme="simple" value="Change" method="change" id="btnChange" onclick="return validate_form()"></s:submit>													
													<s:submit theme="simple" align="right" value="Exit" method="close" id="btnClose" ></s:submit>
													<s:reset theme="simple" align="right" value="Clear" />	
													
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