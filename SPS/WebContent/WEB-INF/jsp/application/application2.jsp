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
									<td width="736px" height="70px"><img src="../../image/Heading_Left.PNG" width="736" height="70" alt="Heading_Left" /></td>
									<td width="*" bgcolor="#226B9C"></td>
									<td width="198px" height="70px"><img src="../../image/Heading_Right.PNG" width="198" height="70" alt="Heading_Right" /></td>
								</tr>
								<tr>
									<td height="15px" bgcolor="#000033"></td>
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
									<td><a href="<s:url action="logout"/>">Logout</a></td>
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
						<table class="tblContent" border="1" cellpadding="0" cellspacing="0">
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
																<td class="tdHeadings">APPLICATION FORM _CEYLON ELECTRICITY BOARD</td>
																<td></td>
																<td class="tdMsgStyle"><s:property value="errorMessage"></s:property></td>
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
																			<th width="3%"></th>
																			<th width="21%"></th>
																			<th width="38%"></th>
																			<th width="8%"></th>
																			<th width="25"></th>
																			<th width="*%"></th>
																		</tr>
																	</thead>
																	<tbody>
																		<tr>
																			<td></td>
																			<td><s:label value="Application Id(PIV No)"></s:label></td>
																			<td><s:textfield name="applicationId" id="txfdApplicationId" size="25" maxlength="21" onblur="javascript:{this.value = this.value.toUpperCase(); }"></s:textfield><s:submit theme="simple" align="right" value="Find" method="findAppId" id="btnFindAppId" onclick="return checkAppIdNoNotNull()"></s:submit></td>
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
																		<tr>
																			<td></td>
																			<td><s:label value="Description"></s:label></td>
																			<td><s:textarea name="description" id="txfdDescription" cols="20" rows="1"></s:textarea></td>
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
													<td></td>
												</tr>
												<tr>
													<td></td>
												</tr>
												<tr>
													<td></td>
												</tr>
												<tr>
													<td></td>
												</tr>
												<tr>
													<td></td>
												</tr>
												<tr>
													<td></td>
												</tr>
												<tr>
													<td id="tdBotton" colspan="3"><s:submit theme="simple" value="Save" method="save" id="btnSave" 	onclick="return validate_form()"></s:submit> 
													<s:submit theme="simple" align="right" value="Modify" method="modify" id="btnModify" onclick="return validate_form()"></s:submit> 
													<s:submit theme="simple" align="right" value="Next" method="next" id="btnNext" onclick="return validate_form()"></s:submit>
													<s:submit theme="simple" align="right" value="Confirm" method="confirm" id="btnConfirm" onclick="return validate_form()"></s:submit> 
													<s:submit theme="simple" align="right" value="Issue PIV" method="issuePiv" id="btnIssuePiv"></s:submit> 
													<s:submit theme="simple" align="right" value="Allocate" method="allocate" id="btnAllocate" onclick="return checkAllocatedToNotNull()"></s:submit> 
													<s:submit theme="simple" align="right" value="Close" method="close" id="btnClose"></s:submit> 
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
									<td>This is footer</td>
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