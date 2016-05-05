<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Estimate</title>
<link rel="stylesheet" type="text/css" href="../../style/style.css" />
<script type="text/javascript" src="../../script/javascript.js"> </script>

<s:head />
</head>
<body>
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
									<td><s:form action="" method="POST"   validate="true" theme="simple">
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
																<th width="60%"></th>
																<th width="10%"></th>
																<th width="*"></th>
																
															</tr>
														</thead>
														<tbody>
															<tr>
																<td class="tdHeadings">NEW STANDARD ESTIMATE - CEYLON ELECTRICITY BOARD</td>
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
													<td>
													<table width="100%" class="tldFromContent" border="0"  >
														<thead>
															<tr>
																<th width="90%"></th>
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
																			<th width="50%"></th>
																			<th width="30%"></th>
																			<th width="14%"></th>
																			<th width="8%"></th>
																			<th width="15%"></th>
																			<th width="15%"></th>
																			<th width="15%"></th>
																		</tr>
																	</thead>
																	<tbody>
																		<tr>
																			<td></td>
																			<td><s:label value="Application No" id="labelApplicationNo"></s:label></td>
																			<td><s:textfield name="applicationNo" id="txfdApplicationNo" size="23" maxlength="21" onblur="javascript:{this.value = this.value.toUpperCase(); }"></s:textfield></td>
																			<td><s:label value="Tariff Category"></s:label></td>
																			<td><s:select name="tariffVal" 
																			list="listTariff" 
																			id="listTariff" 
																			headerKey="1"
    																		headerValue="-- Please Select --">
    																		</s:select></td>
																			<td></td>
																		</tr>
																		<tr>
																			<td></td>
																			<td><s:label value="Date(DD/MM/YYYY)" id="labelDate"></s:label></td>
																			<td><s:textfield name="date" id="txfddate" size="23" maxlength="21" ></s:textfield></td>
																			<td><s:label value="Name"></s:label></td>
																			<td><s:textfield name="Name" id="txfdName" size="23" maxlength="21"></s:textfield></td>
																			<td></td>
																		</tr>
																		<tr>
																			<td></td>
																			<td><s:label value="Job Description" id="labelJobDescription"></s:label></td>
																			<td><s:textfield name="jobDescription" id="txfdjobDescription" size="23" maxlength="21" ></s:textfield></td>
																			<td><s:label value="Connection Type"></s:label></td>
																			<td><s:select name="ConnectionType" 
																			list="listConnectionType" 
																			id="listConnectionType" 
																			headerKey="1"
    																		headerValue="-- Please Select --">
    																		</s:select></td>
																			<td></td>
																		</tr>
																		<tr>
																			<td></td>
																			<td><s:label value="Neighbours Acc.  No " id="labelNeighboursAccNo"></s:label></td>
																			<td><s:textfield name="NeighboursAccNo" id="txfdNeighboursAccNo" size="23" maxlength="21" ></s:textfield></td>
																			<td><s:label value="Estimated By"></s:label></td>
																			<td><s:textfield name="EstimatedBy" id="txfdEstimatedBy" size="23" maxlength="21"></s:textfield></td>
																			<td></td>
																		</tr>
																		<tr>
																			<td></td>
																			<td><s:label value="No of Phases" id="labelNoOfPhases"></s:label></td>
																			<td><s:textfield name="NoOfPhases" id="txfdNoOfPhases" size="23" maxlength="21" ></s:textfield></td>
																			<td><s:label value="Sin No"></s:label></td>
																			<td><s:textfield name="SinNo" id="txfdSinNo" size="23" maxlength="21"></s:textfield></td>
																			<td><s:label value="Phase"></s:label></td>
																			<td><s:select name="Phase" 
																			list="listPhase" 
																			id="listPhase" 
																			headerKey="1"
    																		headerValue="-- Please Select --">
    																		</s:select></td>
																			<td></td>
																		</tr>
																		<tr>
																			<td></td>
																			<td><s:label value="Length" id="labelLength"></s:label></td>
																			<td><s:textfield name="Length" id="txfdLength" size="23" maxlength="21" ></s:textfield></td>
																			<td><s:label value="Is Loop Service"></s:label></td>
																			<td><s:checkbox name="IsLoopService" id="chkdIsLoopService"></s:checkbox></td>
																			<td></td>
																		</tr>
																		<tr>
																			<td></td>
																			<td><s:label value="Loop Length(m)" id="labelLoopLength"></s:label></td>
																			<td><s:textfield name="LoopLength" id="txfdLoopLength" size="23" maxlength="21" ></s:textfield></td>
																			<td><s:label value="Lb(m)"></s:label></td>
																			<td><s:textfield name="Lb" id="txfdLb" size="23" maxlength="21"></s:textfield></td>
																			<td></td>
																		</tr>
																		<tr>
																			<td></td>
																			<td><s:label value="Span" id="labelSpan"></s:label></td>
																			<td><s:textfield name="Span" id="txfdSpan" size="23" maxlength="21" ></s:textfield></td>
																			<td><s:label value="Estimation Type"></s:label></td>
																			<td><s:select name="EstimationType" 
																			list="listEstimationType" 
																			id="listEstimationType" 
																			headerKey="1"
    																		headerValue="-- Please Select --">
    																		</s:select></td>
																			<td></td>
																		</tr>
																		<tr>
																			<td></td>
																			<td><s:label value="Distance to service Place(km)" id="labelDistancetoservicePlace"></s:label></td>
																			<td><s:textfield name="DistancetoservicePlace" id="txfdDistancetoservicePlace" size="23" maxlength="21" ></s:textfield></td>
																			<td><s:label value="Item Quantities/New Poles"></s:label></td>
																			<td><s:textfield name="ItemQuantities" id="txfdItemQuantities" size="23" maxlength="21"></s:textfield></td>
																			<td></td>
																		</tr>
																		<tr>
																			<td></td>
																			<td><s:label value="No.Stays" id="labelNoStays"></s:label></td>
																			<td><s:textfield name="NoStays" id="txfdNoStays" size="23" maxlength="21" ></s:textfield></td>
																			<td><s:label value="No.Struts"></s:label></td>
																			<td><s:textfield name="NoStruts" id="txfdNoStruts" size="23" maxlength="21"></s:textfield></td>
																			<td></td>
																		</tr>
																		<tr>
																			<td></td>
																			<td><s:label value="Pole Type" id="labelPoleType"></s:label></td>
																			<td><s:select name="PoleType" 
																			list="listPoleType" 
																			id="listPoleType" 
																			headerKey="1"
    																		headerValue="-- Please Select --">
    																		</s:select></td>
																			<td><s:label value="Pole Size" id="labelPoleSize"></s:label></td>
																			<td><s:select name="PoleSize" 
																			list="listPoleSize" 
																			id="listPoleSize" 
																			headerKey="1"
    																		headerValue="-- Please Select --">
    																		</s:select></td>
																			<td></td>
																		</tr>
																		<tr>
																			<td></td>
																			<td><s:label value="Land Dispute" id="labelLandDispute"></s:label></td>
																			<td><s:select name="LandDispute" 
																			list="listLandDispute" 
																			id="listLandDispute" 
																			headerKey="1"
    																		headerValue="-- Please Select --">
    																		</s:select></td>
																			<td><s:label value="D-Notice Issued"></s:label></td>
																			<td><s:checkbox name="DNoticeIssued" id="chkdDNoticeIssued"></s:checkbox></td>
																			<td></td>
																		</tr>
																		<tr>
																			<td></td>
																			<td><s:label value="D-Notice Issued Date (DD/MM/YYYY)" id="labelDNoticeIssuedDate"></s:label></td>
																			<td><s:textfield name="DNoticeIssuedDate" id="txfdDNoticeIssuedDate" size="23" maxlength="21" ></s:textfield></td>
																			<td><s:label value="Ecectricity Needed"></s:label></td>
																			<td><s:select name="EcectricityNeeded" 
																			list="listEcectricityNeeded" 
																			id="listEcectricityNeeded" 
																			headerKey="1"
    																		headerValue="-- Please Select --">
    																		</s:select></td>
																			<td></td>
																		</tr>
																		<tr>
																			<td height="2px">&nbsp;</td>	
																		</tr>
																		<tr>
																			<td class="tdHeadings"  colspan="4">Estimation Details</td>
																		</tr>																																													
																		<tr>											
																			<td></td>
																			<td><s:label value="Fixed Cost" id="labelFixedCost"></s:label></td>
																			<td><s:textfield name="FixedCost" id="txfdFixedCost" size="23" maxlength="21" ></s:textfield></td>
																			<td><s:label value="Variable Cost"></s:label></td>
																			<td><s:textfield name="VariableCost" id="txfdVariableCost" size="23" maxlength="21"></s:textfield></td>
																			<td></td>																		
																		</tr>
																		<tr>											
																			<td></td>
																			<td><s:label value="Sub Cost" id="labelSubCost"></s:label></td>
																			<td><s:textfield name="SubCost" id="txfdSubCost" size="23" maxlength="21" ></s:textfield></td>
																			<td><s:label value="Other Cost"></s:label></td>
																			<td><s:textfield name="OtherCost" id="txfdOtherCost" size="23" maxlength="21"></s:textfield></td>
																			<td></td>																		
																		</tr>
																		<tr>											
																			<td></td>
																			<td><s:label value="Conversion Cost" id="labelConversionCost"></s:label></td>
																			<td><s:textfield name="ConversionCost" id="txfdConversionCost" size="23" maxlength="21" ></s:textfield></td>
																			<td><s:label value="Tax Amount"></s:label></td>
																			<td><s:textfield name="TaxAmount" id="txfdTaxAmount" size="23" maxlength="21"></s:textfield></td>
																			<td></td>																		
																		</tr>
																		<tr>											
																			<td></td>
																			<td><s:label value="Security Amount" id="labelSecurityAmount"></s:label></td>
																			<td><s:textfield name="SecurityAmount" id="txfdSecurityAmount" size="23" maxlength="21" ></s:textfield></td>
																			<td><s:label value="Total Amount"></s:label></td>
																			<td><s:textfield name="Total Amount" id="txfdTotalAmount" size="23" maxlength="21"></s:textfield></td>
																			<td></td>																		
																		</tr>
																		<tr>
																			<td></td>																			
																			<td></td>
																		</tr>	
																		<tr>
																			
																		</tr>																
				         
																		<tr>
																			<td></td>																			
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