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
<title>Customer Registration</title>
<link rel="stylesheet" type="text/css" href="../../style/style.css" />
<script type="text/javascript" src="../../script/customerRegistration.js"> </script>
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
									<td><s:form name="customerRegistrationForm" action="customerRegistrationBS" method="POST"
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
																<td class="tdHeadings">CUSTOMER REGISTRATION FORM CEYLON ELECTRICITY BOARD</td>
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
																<th width="5%"></th>
																<th width="20%"></th>
																<th width="10%"></th>
																<th width="15%"></th>
																<th width="*"></th>
																<th width="5%"></th>
															</tr>
														</thead>
														<tbody>
															<tr>
																<td></td>
																<td><s:label value="Cost Center No"></s:label></td>
																<td><s:textfield name="costCenterNo" id="txfdCostCenterNo" size="6" maxlength="6" readonly="true" cssClass="inputArea" ></s:textfield></td>
																<td></td>
																<td></td>
																<td></td>
															</tr>
															
															<tr>
																<td></td>
																<td><s:label value="Personal/Corporate"></s:label></td>
																<td><s:select  name="personalCorporate" id="txfdPersonalCorporate"  headerKey="1" list="#{'PER':'Personal','COR':'Corporate'}"/></td>
																<td></td>
																<td></td>
																<td></td>
															</tr>
															
															
															<tr>
																<td></td>
																<td><s:label value="ID Type"></s:label></td>
																<td><s:radio name="idType" id="idRadioType"  list="#{'NIC':'NIC','PAS':'PASSPORT','BRN':'BUS REG NO'}" /></td>
																<td><s:label value="ID No"></s:label></td>
																<td><s:textfield name="idNo" id="txfdIdNo" size="12" maxlength="12" cssClass="inputArea" onblur="javascript:{this.value = this.value.toLowerCase(); }"></s:textfield><s:submit theme="simple" align="right" value="Find" method="findIdNo" id="btnFindIdNo" onclick="return checkIdNoNotNull()"></s:submit></td>
																<td></td>
															</tr>
															<s:if test="%{costCenterNo=='000.20'}">
															<tr>
																<td></td>
																<td><s:label value="Company Name" ></s:label></td>
																<td><s:textarea name="companyName" id="txfdCompanyName" cols="30" rows="3" onblur="javascript:{this.value = this.value.toUpperCase(); }"></s:textarea></td>
																<td></td>
																<td></td>
																<td></td>
															</tr>
															</s:if>
															<tr>
																<td></td>
																
																<td><s:label value="Full Name/Requested By" ></s:label></td>
																<td><s:textarea name="fullName" id="txfdFullName" cols="30" rows="3" onblur="javascript:{this.value = this.value.toUpperCase(); }"></s:textarea></td>
																
																<td></td>
																<td></td>
																<td></td>
															</tr>
															<tr>
																<td></td>
																<td><s:label value="First Name (Initials)/ Company Name/Requested By" ></s:label></td>
																<td><s:textarea name="firstName" id="txfdFirstName" cols="30" rows="1" onblur="javascript:{this.value = this.value.toUpperCase(); }" ></s:textarea></td>
																<td><s:label value="Last Name/Company Type"></s:label></td>
																<td><s:textarea name="lastName" id="txfdLastName" cols="30" rows="1" onblur="javascript:{this.value = this.value.toUpperCase(); }"></s:textarea></td>
																<td></td>
															</tr>
															<s:if test="%{costCenterNo=='000.00'}">
															<tr>
																<td></td>
																<td><s:label value=" Company Name" ></s:label></td>
																<td><s:textarea name="firstName" id="txfdFirstName" cols="30" rows="1" onblur="javascript:{this.value = this.value.toUpperCase(); }" ></s:textarea></td>
																<td><s:label value="Company Type"></s:label></td>
																<td><s:textarea name="lastName" id="txfdLastName" cols="30" rows="1" onblur="javascript:{this.value = this.value.toUpperCase(); }"></s:textarea></td>
																<td></td>
															</tr>
															</s:if>
															
															<tr>
																<td></td>
																<td><s:label value="Street Address"></s:label><font color="red">(line 1)</font></td>
																<td><s:textarea name="streetAddress" id="txfdStreetAddress" cols="30" rows="1" onblur="javascript:{this.value = this.value.toUpperCase(); }"></s:textarea></td>
																<td><s:label value="Suburb"></s:label><font color="red">(line 2)</font></td>
																<td><s:textfield name="suburb" id="txfdSuburb" size="20" onblur="javascript:{this.value = this.value.toUpperCase(); }"></s:textfield></td>
																<td></td>
															</tr>
															
															
															
															<s:if test="%{costCenterNo=='541.00' || costCenterNo=='541.10' || costCenterNo=='541.20' || costCenterNo=='542.00' || costCenterNo=='542.10' || costCenterNo=='542.20' || costCenterNo=='547.00' || costCenterNo=='547.10' || costCenterNo=='547.20' || costCenterNo=='548.00' || costCenterNo=='548.10' || costCenterNo=='548.20'}">
															<tr>
																<td></td>
																<td><s:label value="Colombo Resident"></s:label></td>
																<td><s:radio name="isColombo" id="isColomboRadioType"  list="#{'Y':'YES','N':'NO'}" value="'Y'" onchange="setCityCtrl()" /></td>																
																<td><s:label value="City"></s:label><font color="red">(line 3)</font></td>
																<td><s:select  name="city" id="txfdCity"  headerKey="1" list="#{'Colombo_1':'Colombo_1','Colombo_2':'Colombo_2','Colombo_3':'Colombo_3','Colombo_4':'Colombo_4', 'Colombo_5':'Colombo_5','Colombo_6':'Colombo_6','Colombo_7':'Colombo_7','Colombo_8':'Colombo_8', 'Colombo_9':'Colombo_9','Colombo_10':'Colombo_10','Colombo_11':'Colombo_11','Colombo_12':'Colombo_12','Colombo_13':'Colombo_13','Colombo_14':'Colombo_14', 'Colombo_15':'Colombo_15'}"/></td>

																<td id="tdlblServiceCity" style="visibility: hidden;"><s:label value="City"></s:label></td>
																<td id="tdtxtServiceCity" style="visibility: hidden;"><s:textfield name="othercity" id="txfdOtherCity" size="20" onblur="javascript:{this.value = this.value.toUpperCase(); }"></s:textfield></td>															
																
															</tr>
															</s:if >
															<s:else>
															<tr>
																<td></td>
																<td><s:label value="City"></s:label><font color="red">(line3)</font></td>
																<td><s:textfield name="city" id="txfdCity" size="20" onblur="javascript:{this.value = this.value.toUpperCase(); }"></s:textfield></td>															
															</tr>
															</s:else>	
															<tr>
																<td></td>
																<td><s:label value="Postal Code"></s:label></td>
																<td><s:textfield name="postalCode" size="5" maxlength="5" onkeypress="return onlyNumbersWithoutSpaceBar(event)"></s:textfield></td>
															</tr>												
															<tr>
																<td></td>
																<td><s:label value="Telephone No"></s:label></td>
																<td><s:textfield name="telephoneNo" size="10" maxlength="10" onkeypress="return onlyNumbersWithoutSpaceBar(event)"></s:textfield></td>
																<td><s:label value="Mobile No"></s:label></td>
																<td><s:textfield name="mobileNo" size="10" maxlength="10" onkeypress="return onlyNumbersWithoutSpaceBar(event)"></s:textfield></td>
																<td></td>
															</tr>
															<tr>
															</tr>
															<tr>
																<td></td>
																<td><s:label value="Email"></s:label></td>
																<td><s:textfield name="email" size="30"></s:textfield></td>
																<td><s:label value="Preferred Language"></s:label></td>
																<td><s:radio name="preferefLanguage"  list="#{'SI':'Sinhala','EN':'English'}" value="'SI'" /></td>
																<td></td>
															</tr>
															<tr>
																<td></td>
																<td><s:label value="CEB Employee"></s:label></td>
																<td><s:radio name="cebEmployee"  list="#{'Y':'Yes','N':'No'}"  /></td>
																<td></td>
																<td></td>
																<td></td>
															</tr>
															<tr>
																<td>.</td>
																<td></td>
																<td></td>
																<td></td>
																<td></td>
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
													<s:submit theme="simple" value="Save" method="save" id="btnSave" onclick="return validate_form()"></s:submit>
													<s:submit theme="simple" value="Modify" method="modify" id="btnModify" onclick="return validate_form()"></s:submit>
													<s:if test="%{smcType=='NC'}">
													<!--<s:submit theme="simple" align="right" value="Next" method="next" id="btnNext" onclick="return validate_form()"></s:submit>-->
													</s:if>
													<s:else>
													<!--<s:submit theme="simple" align="right" value="Next" method="next" id="btnNext" onclick="return validate_form()"></s:submit>-->
													</s:else>
													<s:submit theme="simple" align="right" value="Exit" method="close" id="btnClose" ></s:submit>
													<s:reset theme="simple" align="right" value="Clear" />
													<s:hidden id="state" name="state"></s:hidden>
													<s:hidden id="hiddenSecondState" name="hiddenSecondState"></s:hidden>
													<s:hidden id="path" name="path"></s:hidden> 
													<s:hidden id="status" name="status"></s:hidden> 
													<s:hidden id="webAppName" name="webAppName"></s:hidden> 
													<s:hidden id="hid_city" name="hid_city"></s:hidden>
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