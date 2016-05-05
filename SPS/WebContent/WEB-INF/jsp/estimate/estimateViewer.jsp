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
<title>Estimate Viewer</title>
<link rel="stylesheet" type="text/css" href="../../style/style.css" />
<script type="text/javascript" src="../../script/generateJobNo.js"> </script>
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
									<td><s:form name="estimateViewer" action="estimateViewer" method="POST"
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
																<td class="tdHeadings">Estimate Viewer</td>
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
													<td class="tdHeadings" colspan="4">Estimate Detail</td>
												</tr>
												<tr>
													<td>
													<table width="100%" class="tldFromContent" border="0" >
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
																<td><s:label value="Cost Center No"></s:label></td>
																<td><s:textfield name="costCenterNo" id="txfdCostCenterNo" size="6" maxlength="6" cssClass="inputArea" ></s:textfield></td>
																<td><s:label value="CSC No"></s:label></td>
																<td><s:textfield name="cSCNo" id="txfdcSCNo" size="6" maxlength="6" cssClass="inputArea" ></s:textfield></td>
																<td></td>
															</tr>
															<!--  <tr>
																<td></td>
																<td><s:label value="CSC No"></s:label></td>
																<td><s:textfield name="cSCNo" id="txfdcSCNo" size="6" maxlength="6" cssClass="inputArea" ></s:textfield></td>
																<td></td>
																<td></td>
																<td></td>
															</tr>-->
															<tr>
																<td></td>
																<td><s:label value="Estimate No"></s:label></td>
																<td><s:select  name="estimateNo" id="txfdEstimateNo" headerKey="1" headerValue="-- Please Select --" list="estimateNoList" onchange="form.submit()" /></td>
																<!--<td><s:textfield name="estimateNo" id="txfdEstimateNo" size="20" maxlength="20" cssClass="inputArea" ></s:textfield></td>-->
																<td></td>
																<td></td>
																<td></td>
															</tr>
															<tr>
																<td></td>
																<td><s:label value="Applicant's  Name"></s:label></td>
																<td><s:textarea name="fullName" id="txfdFullName" cols="30" rows="1"></s:textarea></td>
																<td><s:label value="Application Date"></s:label></td>
																<td><s:textfield  name="applicationDate" id="txfdApplicationDate" size="10" maxlength="10" /></td>
																<td></td>
															</tr>
															<tr>
																<td></td>
																<td><s:label value="Address"></s:label></td>
																<td><s:textarea name="fullAddress"  id="txfdFullAddress" cols="30" rows="2"></s:textarea></td>
																<td><s:label value="Remarks "></s:label></td>
																<td><s:textarea  name="semarks" id="txfdRemarks" cols="30" rows="2" /></td>
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
																<td class="tdHeadings" colspan="2"><s:label value="Connection Details"></s:label></td>
																<td></td>
																<td></td>
																<td></td>
																<td></td>
															</tr>
															<tr>
																<td></td>
																<td colspan="2">
																<table width="100%" class="tldFromContent" border="0" >
																	<thead>
																		<tr>
																			<th width="37.5%"></th>
																			<th width="*"></th>
																		</tr>
																	</thead>
																	<tbody>
																	
																		<tr>
																		 	<td><s:label value="Phase"></s:label></td>
																			<td><s:textfield name="phase" id="txfdPhase"  size="2" maxlength="1" /></td>
																		 	
																		 	
																		</tr>
																		<tr>
																		 	<td><s:label value="Connection Type"></s:label></td>
																			<td><s:textfield name="connectionType" id="txfdConnectionType" size="3" maxlength="2" /></td> 
																		 	
																		</tr>
																		<tr>
																		 	<td><s:label value="Tariff Category Code"></s:label></td>
																			<td><s:textfield name="tariffCategoryCode" id="txfdTariffCategoryCode" size="3" maxlength="2"/></td>
																		 	
																		</tr>
																		<tr>
																		 	<td><s:label value="Tariff Code"></s:label></td>
																			<td><s:textfield name="tariffCode" id="txfdTariffCode" size="3" maxlength="2"/></td>
																		 	
																		</tr>
																	</tbody>
																</table>
																</td>
																<td colspan="2" valign="top"">
																<table width="100%" class="tldFromContent" border="0" >
																	<thead>
																		<tr >
																			<th width="42%"></th>
																			<th width="*"></th>
																			
																		</tr>
																	</thead>
																	<tbody>
																	
																		<tr>
																		 	<td><s:label value="Job Description : "></s:label></td>
																			<td><s:textarea name="jobDescription" id="txfdJobDescription" cols="30" rows="3"></s:textarea></td>
																		 	
																		 	
																		</tr>
																		
																	</tbody>
																</table>
																</td>
																<td></td>
															</tr>
															<!--  <tr>
																<td></td>
																<td><s:label value="Phase"></s:label></td>
																<td><s:textfield name="phase" id="txfdPhase"  size="2" maxlength="1" /></td>
																<td><s:label value="Connection Type"></s:label></td>
																<td><s:textfield name="connectionType" id="txfdConnectionType" size="3" maxlength="2" /></td> 
																<td></td>
															</tr>
															<tr>
																<td></td>
																<td><s:label value="Tariff Category Code"></s:label></td>
																<td><s:textfield name="tariffCategoryCode" id="txfdTariffCategoryCode" size="3" maxlength="2"/></td>
																<td><s:label value="Tariff Code"></s:label></td>
																<td><s:textfield name="tariffCode" id="txfdTariffCode" size="3" maxlength="2"/></td>
																<td></td>
															</tr>-->
															<tr>
																<td></td>
																<td>.</td>
																<td></td>
																<td>.</td>
																<td></td>
																<td></td>
															</tr>
															<tr>
																<td></td>
																<td><s:label value="Category Code"></s:label></td>
																<td><s:textfield name="categoryCode" id="txfdCategoryCode" size="3" maxlength="2"/></td>
																<td></td>
																<td></td>
																<td></td>
															</tr>
															<tr>
																<td></td>
																<td><s:label value="Total Line Length(m)"></s:label></td>
																<td><s:textfield name="totalLineLength" id="txfdTotalLineLength" size="3" maxlength="3"/></td>
																<td class="tdHeadings" colspan="2"><s:label value="Standard Estimate"></s:label></td>
																<td></td>
															</tr>
															<tr>
																<td></td>
																<td></td>
																<td></td>
																<!--  <td><s:label value="Conductor Type"></s:label></td>
																<td><s:textfield name="conductorType" id="txfdConductorType" size="3" maxlength="3"/></td>
																-->
																<td><s:label value="Fixed Cost"></s:label></td>
																<td><s:textfield name="fixedCost" id="txfdFixedCost" size="10" maxlength="10"/></td>
																<td></td>
															</tr>
															<tr>
																<td></td>
																<td></td>
																<td></td>
																<!-- <td><s:label value="Conductor Length(m)"></s:label></td>
																<td><s:textfield name="conductorLength" id="txfdConductorLength" size="3" maxlength="3"/></td>
																-->
																<td><s:label value="Variable Cost"></s:label></td>
																<td><s:textfield name="variableCost" id="txfdVariableCost" size="10" maxlength="10"/></td>
																<td></td>
															</tr>
															<tr>
																<td></td>
																<td><s:label value="Service Type"></s:label></td>
																<td><s:textfield name="serviceType" id="txfdServiceType" size="3" maxlength="3"/></td>
																<td><s:label value="Sub Total"></s:label></td>
																<td><s:textfield name="subTotal" id="txfdSubTotal" size="10" maxlength="10"/></td>
																<td></td>
															</tr>
															<tr>
																<td></td>
																<td><s:label value="Service Length(m)"></s:label></td>
																<td><s:textfield name="serviceLength" id="txfdServiceLength" size="3" maxlength="3"/></td>
																<td><s:label value="OtherCost (Material)"></s:label></td>
																<td><s:textfield name="otherCostM" id="txfdOtherCostM" size="10" maxlength="10"/></td>
																<td></td>
															</tr>
															<tr>
																<td></td>
																<td><s:label value="Wiring Type"></s:label></td>
																<td><s:textfield name="wiringType" id="txfdWiringType" size="3" maxlength="3"/></td>
																<td><s:label value="OtherCost (Labour)"></s:label></td>
																<td><s:textfield name="otherCostL" id="txfdOtherCostL" size="10" maxlength="10"/></td>
																<td></td>
															</tr>
															<tr>
																<td></td>
																<td><s:label value="Is loop service?" ></s:label></td>
																<td><s:textfield name="isLoopService" id="txfdIsLoopService" size="3" maxlength="3"/></td>
																<td><s:label value="Capital Cost" ></s:label></td>
																<td><s:textfield name="capitalCost" id="txfdCapitalCost" size="10" maxlength="10"/></td>
																<td></td>
															</tr>
															<tr>
																<td></td>
																<td><s:label value="Cable Type"></s:label></td>
																<td><s:textfield name="cableType" id="txfdCableType" size="10" maxlength="10"/></td>
																<td><s:label value="Tax Amount" ></s:label></td>
																<td><s:textfield name="taxAmount" id="txfdTaxAmount" size="10" maxlength="10"/></td>
																<td></td>
															</tr>
															<tr>
																<td></td>
																<td><s:label value="Spans" ></s:label></td>
																<td><s:textfield name="spans" id="txfdSpans" size="3" maxlength="3"/></td>
																<td><s:label value="Security Deposit" ></s:label></td>
																<td><s:textfield name="securityDeposit" id="txfdSecurityDeposit" size="10" maxlength="10"/></td>
																<td></td>
															</tr>
															<tr>
																<td></td>
																<td><s:label value="No of Poles"></s:label></td>
																<td><s:textfield name="noOfPoles" id="txfdNoOfPoles" size="2" maxlength="2"/></td>
																<td class="tdHeadings"><s:label value="EstimatedTotalCost"></s:label></td>
																<td><s:textfield name="estimatedTotalCost" id="txfdEstimatedTotalCost" size="10" maxlength="10"/></td>
																<td></td>
															</tr>
															<tr>
																<td></td>
																<td><s:label value="No of Stays"></s:label></td>
																<td><s:textfield name="noOfStays" id="txfdNoOfStays" size="2" maxlength="2"/></td>
																<td></td>
																<td></td>
																<td></td>
															</tr>
															<tr>
																<td></td>
																<td><s:label value="No of Struts"></s:label></td>
																<td><s:textfield name="noOfStruts" id="txfdNoOfStruts" size="2" maxlength="2"/></td>
																<td></td>
																<td></td>
																<td></td>
															</tr>
															<tr>
																<td></td>
																<td><s:label value="SIN No"></s:label></td>
																<td><s:textfield name="sINNo" id="txfdSINNo" size="10" maxlength="10"/></td>
																<td></td>
																<td></td>
																<td></td>
															</tr>
															<tr>
																<td></td>
																<td><s:label value="Phase Colour"></s:label></td>
																<td><s:textfield name="phaseColour" id="txfdPhaseColour" size="2" maxlength="2"/></td>
																<td></td>
																<td></td>
																<td></td>
															</tr>
															<tr>
																<td></td>
																<td><s:label value="Distance to Service Place(Km)"></s:label></td>
																<td><s:textfield name="distanceToServicePlace" id="txfdDistanceToServicePlace" size="3" maxlength="3"/></td>
																<td></td>
																<td></td>
																<td></td>
															</tr>
															<tr>
																<td></td>
																<td><s:label value="Fund Source"></s:label></td>
																<td><s:textfield name="fundSource" id="txfdFundSource" size="10" maxlength="10"/></td>
																<td></td>
																<td></td>
																<td></td>
															</tr>
															<tr>
																<td></td>
																<td colspan="4">
																<div>
																	<s:if test="materialGrid.size > 0">
				                                                    	<table id="list_table" width="95%" align="center" border="1" style="border-color:#000033;border-collapse:collapse"   >
				                                                        	<tr class="listColumnHeadingStyle">
				                                                            	<td align="center">id</td>
				                                                                <td align="center">Res. Code</td>
				                                                                <td align="center">Res. Type</td>
				                                                                <td align="center">Res. Category</td>
				                                                                <td align="center">Tolerance</td>
				                                                                <td align="center">uom</td>
				                                                                <td align="center">Est. Quantity</td>
				                                                                <td align="center">Unit Price</td>
				                                                                <td align="center">Estimated Cost</td>
				                                                                <td align="center">Customer Qty</td>
				                                                                <td align="center">Customer Cost</td>
				                                                             </tr>
				                                                             <%int i=0; %>
				                                                             <s:iterator value="materialGrid"  id="listColumnStyle">
				                                                             	<tr id="row_<s:property value="resourceCode"/>" class="listRowStyle">
				                                                                	<td align="center"><s:property value="*" /></td>
				                                                                	<td align="center"><s:property value="resCd" /></td>
				                                                                	<td align="center"><s:property value="resType" /></td>
				                                                                	<td align="center"><s:property value="resCat" /></td>
				                                                                	<td align="center"><s:property value="tolerance"/></td>
				                                                                	<td align="right"><s:property value="uom" /></td>
				                                                                	<td align="right"><s:property value="estimateQty"/></td>
				                                                                	<td align="right"><s:property value="unitPrice"/></td>
				                                                                	<td align="right"><s:property value="estimateCost"/></td>
				                                                                	<td align="right"><s:property value="customerQty" /></td>
				                                                                	<td align="right"><s:property value="customerCost"/></td>
				                                                                 </tr>
				                                                              <%i++; %>
				                                                            </s:iterator>
				                                                        </table>
				                                                    </s:if>
				                                                </div>    
																</td>
																<td></td>
															</tr>
															<tr>
																<td></td>
																<td colspan="4">
																<div>
																	<s:if test="labourGrid.size > 0">
				                                                    	<table id="list_table" width="95%" align="center" border="1" style="border-color:#000033;border-collapse:collapse"   >
				                                                        	<tr class="listColumnHeadingStyle">
				                                                            	<td align="center">id</td>
				                                                                <td align="center">Labour Code</td>
				                                                                <td align="center">Description</td>
				                                                                <td align="center">Unit Price</td>
				                                                                <td align="center">Item Qty</td>
				                                                                <td align="center">Labour Cost</td>
				                                                            </tr>
				                                                            <%int i=0; %>
				                                                        	<s:iterator value="labourGrid"  id="listColumnStyle">
				                                                            <tr id="row_<s:property value="labourCode"/>" class="listRowStyle">
				                                                            	<td align="center"><s:property value="*" /></td>
				                                                            	<td align="center"><s:property value="labourCode" /></td>
				                                                            	<td align="center"><s:property value="description" /></td>
				                                                            	<td align="right"><s:property value="unitPrice"  /></td >
				                                                            	<td align="right"><s:property value="itemQty" /></td>
				                                                            	<td align="right"><s:property value="labourCost" /></td>
				                                                            </tr>
				                                                            <%i++; %>
				                                                            </s:iterator>
				                                                        </table> 
				                                                    </s:if>
				                                                </div>    
																</td>
																<td></td>
															</tr>
															
															
															<!-- <tr>
																<td></td>
																<td><s:submit theme="simple" value="Generate Job No" method="generateJobNo" id="btnGenerateJobNo" onclick=""></s:submit></td>
																<td><s:textfield name="jobNo" id="txfdJobNo" size="20" readonly="true"></s:textfield></td>
																<td></td>
																<td></td>
																<td></td>
															</tr>
															-->
															
																
																				
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
													<s:submit theme="simple" align="right" value="Exit" method="close" id="btnClose" ></s:submit>
													<s:if test="%{state=='REVERSE_APPROVED_EST'}">
													<s:submit theme="simple" align="right" value="Cancel Approval" method="cancelApproval" id="btnCancel" ></s:submit>
													</s:if>
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