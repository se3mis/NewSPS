<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Estimate</title>
<link rel="stylesheet" type="text/css" href="../../style/style.css" />
<script type="text/javascript" src="../../script/estimate.js"> </script>
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
						<table class="tblContent" border="1" cellpadding="0"
							cellspacing="0">
							<thead>
								<tr>
									<th></th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td><s:form action="estimate" method="POST"   validate="true" theme="simple">
										<table width="100%" class="tldFromContent" border="0">
											<thead>
												<tr>
													<th></th>

												</tr>
											</thead>
											<tbody>
												<tr>
													<td>
													<table width="100%" border="0"  >
														<thead>
															<tr>
																<th width="40%"></th>
																<th width="10%"></th>
																<th width="*"></th>
																
															</tr>
														</thead>
														<tbody>
															<tr>
																<td class="tdHeadings">NEW STANDARD ESTIMATE_CEYLON ELECTRICITY BOARD</td>
																<td></td>
																<td class="tdMsgStyle"><s:property  value="errorMessage" ></s:property></td>
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
													<table width="100%" class="tldFromContent" border="0"  >
														<thead>
															<tr>
																<th width="79%"></th>
																<th width="*"></th>
															</tr>
														</thead>
														<tbody>
															<tr>
																<td>
																<table width="100%" class="tldFromContent" border="0"  >
																	<thead>
																		<tr>
																			<th width="2%"></th>
																			<th width="13%"></th>
																			<th width="30%"></th>
																			<th width="14%"></th>
																			<th width="8%"></th>
																			<th width="15%"></th>
																			<th width="*"></th>
																			<th width="1%"></th>
																		</tr>
																	</thead>
																	<tbody>
																		<tr>
																			<td></td>
																			<td><s:label value="Application No" id="labelApplicationNo"></s:label></td>
																			<td><s:textfield name="applicationNo" id="txfdApplicationNo" size="23" maxlength="21" onblur="javascript:{this.value = this.value.toUpperCase(); }"></s:textfield><s:submit theme="simple" align="right" value="Find" method="findApplicationNo" id="btnFindApplicationNo" onclick="return checkAppNoNotNull()"></s:submit></td>
																			<td><s:label value="Date(D/M/Y)"></s:label></td>
																			<td><s:textfield name="date" id="txfdDate" size="10"></s:textfield></td>
																			<td><s:label value="Cost Center No"></s:label></td>
																			<td><s:textfield name="costCenterNo" id="txfdCostCenterNo" size="6" maxlength="6"></s:textfield></td>
																			<td></td>
																		</tr>
																		<tr>
																			<td></td>
																			<td><s:label value="Id No"></s:label></td>
																			<td><s:textfield name="idNo" size="10" id="txfdIdNo"></s:textfield><s:submit theme="simple" align="right" value="Find Id" method="findId" id="btnFindId" onclick="return checkIdNoNotNull()"></s:submit></td>
																			<td><s:label value="Assessment No"></s:label></td>
																			<td><s:textfield name="assessmentNo" id="txfdAssessmentNo" size="10"></s:textfield></td>
																			<td><s:label value="Neighbours Acc. No"></s:label></td>
																			<td><s:textfield name="neighboursAccNo" id="txfdNeighboursAccNo" size="10"></s:textfield></td>
																			
																			<td></td>
																		</tr>
																		
																	</tbody>

																</table>
																</td>
																<td valign="top">
																<table width="100%" class="tldFromContent" border="0"  >
																	<thead>
																		<tr>
																			<th width="30%"></th>
																			<th width="60%"></th>
																			<th width="*"></th>
																		</tr>
																	</thead>
																	<tbody>
																		<tr>
																			<td><s:label value="Description"></s:label></td>
																			<td><s:textarea name="description" id="txfdDescription" cols="20" rows="2"></s:textarea></td>
																			<td></td>
																			
																		</tr>
																	</tbody>

																</table>
																
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
													<table width="100%" class="tldFromContent" border="0"  >
														<thead>
															<tr>
																<th width="60%"></th>
																<th width="*"></th>
															</tr>
														</thead>
														<tbody>
															<tr>
																<td>
																<table width="100%" class="tldFromContent" border="0" >
														<thead>
															<tr>
																<th width="2%"></th>
																<th width="25%"></th>
																<th width="25%"></th>
																<th width="21%"></th>
																<th width="20"></th>
																<th width="*%"></th>
															</tr>
														</thead>
														<tbody>
															<tr>
																<td></td>
																<td><s:label value="SIN No"></s:label></td>
																<td><s:textfield name="sinNo" id="txfdSinNo" size="10"></s:textfield></td>
																<td><s:label value="Phase"></s:label></td>
																<td><s:textfield name="phase" id="txfdPhase" size="10"></s:textfield></td>
																<td></td>
															</tr>
															<tr>
																<td></td>
																<td><s:label value="Phase"></s:label></td>
																<td><s:radio name="phase" id="RadioPhase" list="#{'1':'1 ph','3':'3 ph'}" value="'1'" /><s:hidden id="hiddenPhase" name="hiddenPhase"></s:hidden></td>
																<td><s:label value="Tariff Category"></s:label></td>
																<td><s:textfield name="tariffCode" id="txfdTariffCode" size="2" maxlength="2" onkeypress="return onlyNumbers(event)"></s:textfield></td>
																<td></td>
															</tr>
															<tr>
																<td></td>
																<td><s:label value="Connection Type"></s:label></td>
																<td><s:radio name="connectionType" id="RadioConnectionType" list="#{'15':'15','30':'30','60':'60'}" value="'15'" /><s:hidden id="hiddenConnectionType" name="hiddenConnectionType"></s:hidden></td>
																<td><s:label value="Customer Category"></s:label></td>
																<td><s:textfield name="customerCategory" id="txfdCustomerCategory" size="4" maxlength="4" onkeypress="return onlyLettersWithoutSpaceBar(event)"></s:textfield></td>
																<td></td>
															</tr>
															<tr>
																<td></td>
																<td><s:label value="Length"></s:label></td>
																<td><s:textfield name="length" id="txfdLength" size="10"></s:textfield></td>
																<td><s:label value="Is Loop Service"></s:label></td>
																<td><s:radio name="isLoopService" id="RadioIsLoopService" list="#{'Y':'Yes','N':'No'}" value="'N'" /><s:hidden id="hiddenIsLoopService" name="hiddenIsLoopService"></s:hidden></td>
																<td></td>
															</tr>
															<tr>
																<td></td>
																<td><s:label value="Loop Length(m)"></s:label></td>
																<td><s:textfield name="loopLength" id="txfdLoopLength" size="10"></s:textfield></td>
																<td><s:label value="Lb(m)"></s:label></td>
																<td><s:textfield name="lb" id="txfdLb" size="10"></s:textfield></td>
																<td></td>
															</tr>
															<tr>
																<td></td>
																<td><s:label value="Spans"></s:label></td>
																<td><s:textfield name="spans" id="txfdSpans" size="10"></s:textfield></td>
																<td><s:label value="Estimation Type"></s:label></td>
																<td><s:textfield name="estimationType" id="txfdEstimationType" size="10"></s:textfield></td>
																<td></td>
															</tr>
															<tr>
																<td></td>
																<td><s:label value="Distance to Service Place(Km)"></s:label></td>
																<td><s:textfield name="distanceToServicePlace" id="txfdDistanceToServicePlace" size="10"></s:textfield></td>
																<td><s:label value="Item Quntities"></s:label></td>
																<td><s:textfield name="itemQuntities" id="txfdItemQuntities" size="10"></s:textfield></td>
																<td></td>
															</tr>
															<tr>
																<td></td>
																<td><s:label value="Land Dispute"></s:label></td>
																<td><s:radio name="landDispute" id="RadioLandDispute" list="#{'Y':'Yes','N':'No'}" value="'N'" /><s:hidden id="hiddenLandDispute" name="hiddenLandDispute"></s:hidden></td>
																<td><s:label value="D_Notice Issued"></s:label></td>
																<td><s:textfield name="dNoticeIssued" id="txfdDNoticeIssued" size="10"></s:textfield></td>
																<td></td>
															</tr>
															<tr>
																<td></td>
																<td><s:label value="D_Notice Date"></s:label></td>
																<td><s:textfield name="dNoticeDate" id="txfdDNoticeDate" size="10"></s:textfield></td>
																<td><s:label value="Electricity Needed"></s:label></td>
																<td><s:textfield name="electricityNeeded" id="txfdElectricityNeeded" size="10"></s:textfield></td>
																<td></td>
															</tr>
														</tbody>

													</table>
													</td>
													<td valign="top">
													<table width="100%" class="tldFromContent" border="0"  >
														<thead>
															<tr>
																<th width="60%"></th>
																<th width="*"></th>
															</tr>
														</thead>
														<tbody>
															<tr>
																<td><s:label value="Fixed Cost"></s:label></td>
																<td><s:textfield name="fixedCost" id="txfdFixedCost" size="10"></s:textfield></td>
															</tr>
															<tr>
																<td><s:label value="Variable Cost"></s:label></td>
																<td><s:textfield name="variableCost" id="txfdVariableCost" size="10"></s:textfield></td>
															</tr>
															<tr>
																<td><s:label value="Sub Total"></s:label></td>
																<td><s:textfield name="subTotal" id="txfdSubTotal" size="10"></s:textfield></td>
															</tr>
															<tr>
																<td><s:label value="Other Total"></s:label></td>
																<td><s:textfield name="OtherTotal" id="txfdOtherTotal" size="10"></s:textfield></td>
															</tr>
															<tr>
																<td><s:label value="Conversion Cost"></s:label></td>
																<td><s:textfield name="ConversionCost" id="txfdConversionCost" size="10"></s:textfield></td>
															</tr>
															<tr>
																<td><s:label value="Tax Amount"></s:label></td>
																<td><s:textfield name="taxAmount" id="txfdTaxAmount" size="10"></s:textfield></td>
															</tr>
															<tr>
																<td><s:label value="Security Amount"></s:label></td>
																<td><s:textfield name="securityAmount" id="txfdSecurityAmount" size="10"></s:textfield></td>
															</tr>
															<tr>
																<td><s:label value="Total Cost"></s:label></td>
																<td><s:textfield name="totalCost" id="txfdTotalCost" size="10"></s:textfield></td>
															</tr>
															
														</tbody>
													</table>		
													</td>
													</tr>
														</tbody>
													</table>		
													
													</td>
												</tr>
												<!-- tr>
													<td class="tdHeadings" colspan="4">Land Detail</td>
												</tr-->
												<!-- tr>
													<td>
													<table width="100%" class="tldFromContent" border="0" >
														<thead>
															<tr>
																<th width="2%"></th>
																<th width="15%"></th>
																<th width="20%"></th>
																<th width="15%"></th>
																<th width="*"></th>
																<th width="5%"></th>
															</tr>
														</thead>
														<tbody>
														
														</tbody>

													</table>
													</td>
												</tr-->
												<!--tr>
													<td class="tdHeadings" colspan="4">Details of Wiring</td>
												</tr-->
												<!--tr>
													<td>
													<table width="100%" class="tldFromContent" border="0" >
														<thead>
															<tr>
																<th width="2%"></th>
																<th width="15%"></th>
																<th width="20%"></th>
																<th width="15%"></th>
																<th width="*"></th>
																<th width="5%"></th>
															</tr>
														</thead>
														<tbody>
															<tr>
																<td></td>
																<td><s:label value="Motors Total(hp/Kw)"></s:label></td>
																<td><s:textfield name="motorsTotal" id="txfdMotorsTotal" size="10"></s:textfield></td>
																<td><s:label value="Welding Plant(KVA)"></s:label></td>
																<td><s:textfield name="weldingPlant" id="txfdWeldingPlant" size="10"></s:textfield></td>
																<td></td>
															</tr>
															<tr>
																<td></td>
																<td><s:label value="Metal Crusher(hp/Kw)"></s:label></td>
																<td><s:textfield name="metalCrusher" id="txfdMetalCrusher" size="10"></s:textfield></td>
																<td><s:label value="Saw Mills(hp/Kw)"></s:label></td>
																<td><s:textfield name="sawMills" id="txfdSawMills" size="10"></s:textfield></td>
																<td></td>
															</tr>
															
														</tbody>

													</table>
													</td>
												</tr-->
												<tr>
													<td class="tdHeadings" colspan="4">Other Details</td>
												</tr>
												<tr>
													<td>
													<table width="100%" class="tldFromContent" border="0" >
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
																<td><s:textfield name="preparedBy" id="txfdPreparedBy" readonly="true" size="10"></s:textfield></td>
																<td><s:label value="Confirmed By"></s:label></td>
																<td><s:textfield name="confirmedBy" id="txfdConfirmedBy" size="10"></s:textfield></td>
																<td></td>
															</tr>
															
														</tbody>

													</table>
													</td>
												</tr>
												<tr>
													<td id="tdBotton" colspan="3">
													<s:submit theme="simple" value="Save" method="save" id="btnSave" onclick="return validate_form()"></s:submit>
													<s:submit theme="simple" align="right" value="Modify" method="modify" id="btnModify" onclick="return validate_form()"></s:submit>
													<s:submit theme="simple" align="right" value="Next" method="next" id="btnNext" onclick="return validate_form()"></s:submit>
													<s:submit theme="simple" align="right" value="Confirm" method="confirm" id="btnConfirm" onclick="return validate_form()"></s:submit>
													<s:submit theme="simple" align="right" value="Issue PIV" method="issuePiv" id="btnIssuePiv"></s:submit>
													<s:submit theme="simple" align="right" value="Allocate" method="allocate" id="btnAllocate" onclick="return checkAllocatedToNotNull()"></s:submit>
													<s:submit theme="simple" align="right" value="Close" method="close" id="btnClose"></s:submit>
													<s:submit theme="simple" align="right" value="List" method="list" id="btnList"></s:submit>
													<s:reset theme="simple" align="right" value="Clear" />
													<s:hidden id="state" name="state"></s:hidden>
													<s:hidden id="hiddenSecondState" name="hiddenSecondState"></s:hidden>
													<s:hidden id="path" name="path"></s:hidden>
													<s:hidden id="status" name="status"></s:hidden>
													<s:hidden id="errorMessage" name="errorMessage"></s:hidden>
													</td>
													
												</tr>
												<tr>
													<td>
													    <table class="tblList"  border="1" cellpadding="0" cellspacing="0" >
				                                            <thead>
				                                                <tr>
				                                                    <th></th>
				                                                </tr>
				                                            </thead>
				                                            <tbody>
				                                                <tr>
				                                                    <td>
				                                                        <div class="listDivStyle" >
				                                                            <table id="tdList" width="100%" border="0" align="center" bgcolor="#FBFFFF" cellpadding="0" cellspacing="0" style="border-collapse:collapse">
				                                                                <tbody>
				                                                                    <tr>
				                                                                        <td align="center"class="listHeader">Estimate Details
				                                                                            <s:hidden name="list" id="list"></s:hidden>
				                                                                            <s:if test="list.size > 0">
				                                                                                <table id="list_table" width="95%" align="center" border="1" style="border-color:#000033;border-collapse:collapse"   >
				                                                                                    <tr class="listColumnHeadingStyle">
				                                                                                        <td align="center">id<s:hidden name="resourceCode" id="resourceCode"></s:hidden></td>
				                                                                                        <td align="center">resourceCode</td>
				                                                                                        <td align="center">resourceType</td>
				                                                                                        <td align="center">resourceCategory</td>
				                                                                                        <td align="center">tolerance</td>
				                                                                                        <td align="center">uom</td>
				                                                                                        <td align="center">estimatedQuantity</td>
				                                                                                        <td align="center">unitPrice</td>
				                                                                                        <td align="center">estimatedCost</td>
				                                                                                        <td align="center">..</td>
				                                                                                        
				                                                                                        
				
				                                                                                    </tr>
				                                                                                    <s:iterator value="list"  id="listColumnStyle">
				                                                                                        <tr id="row_<s:property value="resourceCode"/>" class="listRowStyle">
				                                                                                            <td>
				                                                                                                <s:checkbox name="v" ></s:checkbox>
				                                                                                            </td>
				                                                                                            <td>
				                                                                                                <s:property value="resourceCode" />
				                                                                                            </td>
				                                                                                            <td>
				                                                                                               <s:property value="resourceType" />
				                                                                                            </td>
				                                                                                            <td>
				                                                                                               <s:property value="resourceCategory" />
				                                                                                            </td >
				                                                                                            <td>
				                                                                                               <s:property value="tolerance" />
				                                                                                            </td>
				                                                                                            <td>
				                                                                                               <s:property value="uom" />
				                                                                                            </td >
				                                                                                            <td>
				                                                                                                <s:property value="estimatedQuantity" />
				                                                                                            </td>
				                                                                                            <td>
				                                                                                               <s:property value="unitPrice" />
				                                                                                            </td >
				                                                                                            <td>
				                                                                                                <s:property value="estimatedCost" />
				                                                                                            </td>
				                                                                                            <td>
				                                                                                                <s:submit name="v" value=".." onclick="function1(this);"></s:submit>
				                                                                                                <s:submit name="v" value="A"  onclick = "alert(getRow(this));"></s:submit>
				                                                                                                
				                                                                                            </td>
				
				                                                                                            <!--td--><!--/td-->
				                                                                                        </tr>
				                                                                                    </s:iterator>
				                                                                                </table>
				                                                                            </s:if>
				                                                                        </td>
				                                                                    </tr>
				                                                                </tbody>
				                                                            </table>
				                                                        </div>
				                                                    </td>
				                                                </tr>
				                                                <tr>
				                                                    <td></td>
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