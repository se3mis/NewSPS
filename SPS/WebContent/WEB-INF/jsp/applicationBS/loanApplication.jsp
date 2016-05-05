<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/jsp/include.jsp"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Loan Application</title>
<link rel="stylesheet" type="text/css" href="../../style/style.css" />
<script type="text/javascript" src="../../script/loanApplication.js"> </script>
<script type="text/javascript" src="../../script/javascript.js"> </script>

<s:head />
</head>
<body onload="ctrlLoader()">
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
									<td><s:form action="loanApp" method="POST"
										validate="true" theme="simple">
										<table width="100%" class="tldFromContent" border="0">
											<thead>
												<tr>
													<th></th>

												</tr>
											</thead>
											<tbody>
												<tr>
													<td class="tdHeadings" colspan="4">Loan Application - CEYLON ELECTRICITY BOARD</td>
												</tr>
												<tr>
													<td></td>
												</tr>
												<tr>															
													<td align="center"><s:label name="statusMsg" id="statusMsg" cssClass="STATUSMSG"></s:label></td>	
												</tr>															
												<tr>																												
													<td align="center"><s:label name="statusMsgErr" id="statusMsgErr" cssClass="STATUSMSGERR"></s:label></td>
												</tr>
												
												<tr>
													<td>
													<table width="100%" class="tldFromContent" border="0" >
														<thead>
															<tr>
																<th width="2%"></th>
																<th width="20%"></th>
																<th width="10%"></th>
																<th width="8%"></th>
																<th width="15%"></th>
																<th width="10%"></th>
																<th width="2%"></th>
																<th width="10%"></th>
																<th width="10%"></th>
															</tr>
														</thead>
														<tbody>													
															<tr>
																<td>&nbsp;</td>																
															</tr>
															<tr>
																<td class="tdHeadings" colspan="4">Loan Details</td>
																														
																<td></td>
															</tr>
											
															<tr>
																<td>&nbsp;</td>																
															</tr>
															<tr>
																<td></td>
																<td><s:label value="Application No"></s:label></td>
																<td><s:textfield name="applicationNo" id="txfdapplicationNo" size="20" readonly="false"></s:textfield></td>
																<td><s:submit theme="simple" value="Find" method="find" id="btnFind" onclick="return validate_AppNo()"></s:submit></td>
																								
																
																<td></td>
															</tr>
															<tr>
																<td>&nbsp;</td>																
															</tr>	
															<tr>
																<td></td>
																<td><s:label value="Loan Amount"></s:label></td>
																<td><s:textfield name="loanAmount" id="txfdLoanAmount" size="10" maxlength="5" readonly="true"></s:textfield></td>
																<td></td>																
																<td></td>
															</tr>
																													
															<tr>
																<td>&nbsp;</td>																
															</tr>
															<tr>
																<td></td>
																<td><s:label value="Member Of Samurdhi"></s:label></td>
																<td><s:radio name="memberOfSamurdhi" id="RadioMemberOfSamurdhi" list="#{'Y':'Yes','N':'No'}" onchange="disableSamurdhiDetails()"/></td>
																<td></td>
																<td><s:label value="Samurdhi Id"></s:label></td>
																<td><s:textfield name="samurdhiId" id="txfdSamurdhiId" size="10" maxlength="15" readonly="true"  ></s:textfield></td> <!-- onkeyup="setLoanDetails()" --> 
																<td></td>
															</tr>
															<tr>
																<td>&nbsp;</td>																
															</tr>
															<tr>
																<td></td>
																<td><s:label value="Share Price"></s:label></td>
																<td><s:textfield name="sharePrice" id="txfdSharePrice" size="10" readonly="true"></s:textfield></td>
																<td></td>
																<td><s:label value="No Of Shares"></s:label></td>
																<td><s:textfield name="noOfShares" id="txfdNoOfShares" size="10" readonly="true"></s:textfield></td>
																<td></td>
															</tr>
															<tr>
																<td>&nbsp;</td>																
															</tr>
															<tr>
																<td></td>
																<td><s:label value="No Of Installments"></s:label></td>
																<td><s:textfield name="nofInstallments" id="txfdnofInstallments" size="10" readonly="true" maxlength="3" onkeyup="numericValidate(this,event);"></s:textfield></td>
																<td></td>
																<td><s:label value="Installments"></s:label></td>
																<td><s:textfield name="installment" id="txfdinstallments" size="10" maxlength="10" readonly="true" onkeyup="numericValidate(this,event);"></s:textfield></td>
																<td></td>
															</tr>
															<tr>
																<td>&nbsp;</td>																
															</tr>		
															<tr>
																<td></td>
																<td><s:label value="Interest Rate (%)"></s:label></td>
																<td><s:textfield name="interestrate" id="txfdinterestrate" size="10" readonly="true"></s:textfield></td>
																<td></td>	
																<td><s:label value="Total Interest"></s:label></td>
																<td><s:textfield name="totInterest" id="txfdtotInterest" size="10" maxlength="10" readonly="true"  onkeyup="numericValidate(this,event);"></s:textfield></td>
																<td></td>
															</tr>
															<tr>
																<td>&nbsp;</td>																
															</tr>	
															<tr>
																<td></td>																
																<td><s:label value="Is Apply For Share Loan"></s:label></td>
																<td><s:radio name="isApplyForShareLoan" id="RadioiisApplyForShareLoan" list="#{'Y':'Yes','N':'No'}" /></td>
																<td></td>																
															</tr>
															<tr>
																<td>&nbsp;</td>																
															</tr>	
															<tr>
																<td></td>																
																<td><s:label value="Approved By"></s:label></td>
																<td><s:textfield name="approvedBy" id="txfdaapprovedBy" size="20" ></s:textfield></td>																
																<td></td>																
															</tr>
															<tr>
																<td>&nbsp;</td>																
															</tr>
															<tr>
																<td></td>
																<td><s:label value="Approval Bank"></s:label></td>
																<td><s:radio name="isApprovedBM" id="RadioisApprovedBM" list="#{'Y':'Yes','N':'No'}" /></td>
																<td></td>
																<td><s:label value="Approval DS"></s:label></td>
																<td><s:radio name="isApprovedDS" id="RadioisApprovedDS" list="#{'Y':'Yes','N':'No'}" /></td>
																<td></td>																
																<td><s:label value="Approval SO"></s:label></td>
																<td><s:radio name="isApprovedSDO" id="RadioisApprovedSDO" list="#{'Y':'Yes','N':'No'}" /></td>
																
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
													<s:submit theme="simple" value="Exit" method="close" id="btnClose" ></s:submit>													
<!--													<s:reset theme="simple" align="right" value="Clear" onclick="return setDefaultCheck()" />-->
													<s:hidden id="hid_MaxLoanAmt" name="hid_MaxLoanAmt"></s:hidden>
													<s:hidden id="hid_MaxSamurdhiLoanAmt" name="hid_MaxSamurdhiLoanAmt"></s:hidden>	
													<s:hidden id="hid_isFound" name="hid_isFound"></s:hidden>
													<s:hidden id="hid_NoShares" name="hid_NoShares"></s:hidden>
													<s:hidden id="hid_SharePrice" name="hid_SharePrice"></s:hidden>
													<s:hidden id="hid_isNew" name="hid_isNew"></s:hidden>												
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