<%--
    Document   : Application Form
    Created on : May 16, 2010, 11:21:45 AM
    Author     : Dileepa Waduge
--%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<html>
<head>
<sx:head />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Paying In Voucher - Estimation</title>
<link rel="stylesheet" type="text/css" href="../../style/style.css" />
<script type="text/javascript" src="../../script/estimatePivForm.js"> </script>
<script type="text/javascript" src="../../script/javascript.js"> </script>
<script type="text/javascript">
function display_branches() 
{
	document.getElementById("txfdChequeBranchCode").disabled = false;
	dojo.event.topic.publish("display_branch");
}
</script>
<s:head />
</head>
<% HttpSession session = request.getSession(); %>
<body onload="formView()">
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
						<table class="tblContent" border="0" cellpadding="0"
							cellspacing="0">
							<thead>
								<tr>
									<th></th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td><s:form action="EstimatePIV" method="POST"
										validate="true" theme="simple" name="frmPiv" id="frmPiv">
										<s:hidden name="isConfirm" id="isConfirm"></s:hidden>
										<s:hidden name="isCancel" id="isConfirm"></s:hidden>
										<s:hidden name="isPivView" id="isPivView"></s:hidden>
										<s:hidden name="isGenerate" id="isGenerate"></s:hidden>
										<s:hidden name="isGenerateSuccess" id="isGenerateSuccess"></s:hidden>
										<s:hidden name="isRevise" id="isRevise"></s:hidden>
										<s:hidden name="isPrint" id="isPrint"></s:hidden>
										<s:hidden name="isTempConn" id="isTempConn"></s:hidden>
										<s:hidden name="isNewService" id="isNewService"></s:hidden>
										<s:hidden name="isReConn" id="isReConn"></s:hidden>
										<s:hidden name="isPhaseChange" id="isPhaseChange"></s:hidden>
										<s:hidden name="isConversion" id="isConversion"></s:hidden>
										<s:hidden name="isModify" id="isModify"></s:hidden>
										<s:hidden name="isCostRec" id="isCostRec"></s:hidden>
										<s:hidden name="isLoan" id="isLoan"></s:hidden>
										<s:hidden name="isMeterChange" id="isMeterChange"></s:hidden>
										<s:hidden name="isOtherCR" id="isOtherCR"></s:hidden>
										<s:hidden name="hasAppLoan" id="hasAppLoan"></s:hidden>
										<s:hidden name="referenceType" id="referenceType"></s:hidden>
										<s:hidden name="region" id="region"></s:hidden>
										<table width="100%" class="tldFromContent" border="0" >
											<thead>
												<tr>
													<th></th>

												</tr>
											</thead>
											<tbody>
												<tr>
													<td>
													<table width="100%" border="0"  >
													
														<tr>
															<td class="tdHeadings">
																<s:if test="%{isRevise == 'true'}">
																	PAYING IN VOUCHER - JOB REVISE
																</s:if>
																<s:else>
																	PAYING IN VOUCHER - ESTIMATION
																</s:else>
															</td>
														</tr>
														
														
														<tr>
															<td >&nbsp;</td>
														</tr>
														<tr>
															<td class="tdMsgStyle"><s:property  value="errorMessage"></s:property></td>
														</tr>
														<tr>
															<td class="tdMsgStyleDone"><s:property  value="successMessage" ></s:property></td>
														</tr>
														

													</table>
													</td>
												</tr>
												<tr>
													<td>
													<table width="75%" class="tldFromContent" border="0" >
														<tr height="20">
															<s:if test="%{isGenerate == 'true'  || isPrint == 'true'|| isModify == 'true'}">
																<td><s:if test="%{isRevise == 'true'}">Job No</s:if><s:else>Estimation No</s:else></td>
																<td><s:textfield name="estimateNo" id="estimateNo"  maxlength="21" ></s:textfield><s:submit theme="simple" value="Find" method="viewSMCInfo" id="btnFind" onclick="return checkEstimateNoNotNull()" onblur="javascript:{this.value = this.value.toUpperCase(); }"></s:submit></td>
																
															</s:if>
															<s:else>
																<td>PIV No</td>
																<td><s:textfield name="pivNo" id="pivNo"  maxlength="24" size="25" ></s:textfield><s:submit theme="simple" value="Find" method="viewPIVDetails" id="btnFind" onclick="return checkPIVNoNotNull()" onblur="javascript:{this.value = this.value.toUpperCase(); }"></s:submit></td>
															</s:else>
															<td>C.E.B. Branch : <s:property value="cebBranch"/><s:hidden name="cebBranch" id="cebBranch"></s:hidden></td>
															<td>Cost Center No : <s:property value="costCenterNo"/><s:hidden name="costCenterNo" id="costCenterNo"></s:hidden></td>
															<td>PIV Date : <s:property value="date"/><s:hidden name="date" id="date"></s:hidden></td>
														</tr>
														<s:if test="%{isPrint == 'true'|| isModify == 'true'}">
														<tr>
															<td></td>
															<td colspan="4"><s:radio name="pivType" id="pivType" list="#{'EST':'Normal PIV','ELN':'Loan PIV'}"   /></td>
														</tr>
														</s:if>
													</table>
													
													</td>
												</tr>
												<tr>
													<td>
														<table width="100%" class="tldFromContent" border="0" >
															<tbody>
																<tr valign="top">
																	<td>
																		<table  width="100%" class="tldFromContent" border="0">
																			<tbody>
																				<tr height="20">
																					<s:if test="%{isGenerate != 'true' && isPrint != 'true' && isModify != 'true'}">
																						<td><s:label value="Estimate No"></s:label></td>
																						<td><s:property value="estimateNo"/><s:hidden name="estimateNo"></s:hidden></td>
																					</s:if>
																					<s:elseif test="%{isGenerate != 'true'}">
																						<td><s:label value="PIV No"></s:label></td>
																						<td><s:property value="pivNo"/><s:hidden id="pivNo" name="pivNo"></s:hidden></td>
																					</s:elseif>
																					<s:else>
																						<s:if test="%{isGenerateSuccess == 'true'}">
																						<td><s:label value="PIV No"></s:label></td>
																						<td><s:property value="pivNo"/><s:hidden id="pivNo" name="pivNo"></s:hidden></td>
																						</s:if>
																					</s:else>
																				</tr>
																				<tr height="20">
																					<td><s:label value="Id No"></s:label></td>
																					<td><s:hidden name="idNo" id="idNo"/><s:property value="idNo"/></td>
																				</tr>
																				<tr height="20">
																					<td><s:label value="Depositor's Name"></s:label></td>
																					<td><s:hidden name="fullName" id="fullName"></s:hidden><s:property value="fullName"/></td>
																				</tr>
																				<tr height="20">
																					<td><s:label value="Address"></s:label></td>
																					<td><s:property value="fullAddress"/>
																					<s:hidden name="street" id="street"/>
																					<s:hidden name="city" id="city"/>
																					<s:hidden name="subUrb" id="subUrb"/>
																					<s:hidden name="postalCode" id="postalCode"/>
																					</td>
																				</tr>
																				<tr>
																					<td><s:label value="PIV Receipt No"></s:label><s:if test="%{isConfirm == 'true'}"><font color="red">*</font></s:if></td>
																					<td><s:textfield name="pivReceiptNo" id="pivReceiptNo" size="20" maxlength="10"></s:textfield></td>
																				</tr>
																				<tr>
																					<td>Paying Bank/Branch <font color="red">*</font></td>
																					<td><s:textfield name="payingBankCode" id="txtpayingBankCode" size="4"  maxlength="4" readonly="true" ></s:textfield><s:textfield name="payingBranchCode" id="txtPayingBranchCode" size="3"  maxlength="3" ></s:textfield></td>
																					<s:hidden id="payingBankName" name="payingBankName"/>
																					<s:hidden id="payingBranchName" name="payingBranchName"/>
																				</tr>
																				<tr>
																					<td><s:label value="Date Paid (dd/mm/yyyy)"></s:label></td>
																					<td>
																						<sx:datetimepicker name="paidDate" id="txtPaidDate" displayFormat="dd/MM/yyyy"  required="true" />
																					</td>
																				</tr>
																				
																				<tr>
																					<td><s:label value="Payment Mode"></s:label></td>
																					<td><s:radio name="paymentMode" id="paymentMode" list="#{'C':'Cash','Q':'Cheque'}" onchange="disableChequeNo()"/><s:hidden id="hiddenPaymentMode" name="hiddenPaymentMode"></s:hidden></td>
																					
																				</tr>
																		
																				<tr>
																					<td><s:label value="Bank Code"></s:label></td>
																					<td>
																						<s:if test="%{isCancel != 'true' }">
																							<s:select name="chequeBankCode" id="txfdChequeBankCode" headerKey=""  headerValue="Select Bank" list="bankList"  listKey="bankCode" listValue="%{bankCode+' - '+bankName}" onchange="javascript:display_branches()" />
																							<s:url id="bank_url" value="EstimatePIV!displayBankBranches" />
																						</s:if>
																						<s:else>
																							<s:select name="chequeBankCode" id="txfdChequeBankCode" headerKey=""  headerValue="Select Bank" list="bankList"  disabled="true" />
																						</s:else>
																						
																						
																					</td>
																				</tr>
																				<tr>
																					<td><s:label value="Branch Code"></s:label></td>
																					<td>
																						<s:if test="%{isCancel != 'true'}">
																							<sx:div id="branch" href="%{#bank_url}" listenTopics="display_branch" formId="frmPiv"></sx:div>
																							
																						</s:if>
																						<s:else>
																							<s:select name="chequeBranchCode" id="txfdChequeBranchCode" headerKey=""  headerValue="Select Bank Branch" list="bankBranchList"  disabled="true" />
																						</s:else>
																					</td>
																				</tr>
																		
																				<tr>
																					<td><s:label value="Cheque No"></s:label></td>
																					<td><s:textfield name="chequeNo" id="txfdChequeNo" size="10" readonly="true" maxlength="8" ></s:textfield></td>
																				</tr>
																				<tr>
																					<td><s:label value="Cheque Date (dd/mm/yyyy)"></s:label></td>
																					<td><sx:datetimepicker name="chequeDate" id="txtChequeDate" displayFormat="dd/MM/yyyy" required="true"  />
																					</td>
																				</tr>
																				
																				<tr>
																					<td>&nbsp;</td>
																					<td>&nbsp;</td>
																				</tr>
																				<tr>
																					<td colspan="2"> If Bank loan is granted</td>
																				</tr>
																				<tr>
																					<td>Loan Reference</td>
																					<td><s:textfield name="loanReference" id="txtLoanReference" size="10" ></s:textfield> </td>
																				</tr>
																				<tr>
																					<td>Loan Amount</td>
																					<td><s:textfield name="loanAmount" id="txtLoanAmount" size="10" ></s:textfield> </td>
																				</tr>
																				<tr>
																					<td>&nbsp;</td>
																					<td>&nbsp;</td>
																				</tr>
																				<tr>
																					<td>&nbsp;</td>
																					<td>&nbsp;</td>
																				</tr>
																				<s:if test="%{isConfirm != 'true' && isCancel != 'true' && isGenerate != 'true'}">
																					<tr height="20">
																						<td>PIV Status : <b><s:property value="statusDesc"/></b>
																						</td>
																						<td> </td>
																					</tr>
																				</s:if>
																			</tbody>
																		</table>
																	</td>
																	<td align="center">
																		<table width="85%" class="tldFromContent" border="0" >
																			<tbody>
																				<tr>
																					<td>
																						<table width="100%" border="0" >
																							<tr>
																								<td valign="top" width="100" nowrap="nowrap">Job Description :</td>
																								<td align="left"><s:textarea name="description" id="txtDescription"  cols="60" rows="1"></s:textarea></td>
																							</tr>
																						</table>
																					</td>
																				</tr>
																				<tr><td>&nbsp;</td></tr>
																				<tr>
																					<td>
																						<table width="100%" class="tldFromContent" border="1" >
																							<thead>
																								<tr  height="20">
																									<th width="20%" ><s:label value="Code No"></s:label></th>
																									<th width="50%" ><s:label value="Description"></s:label></th>
																									<th width="30%" ><s:label value="Amount"></s:label></th>
																								</tr>
																							</thead>
																							<tbody>
																								<tr height="20">
																									<td align="center"><s:label value="1300"></s:label></td>
																									<td><s:label value="Miscellaneous Income"></s:label></td>
																									<td align="right"><s:hidden name="miscellaneousIncome" id="miscellaneousIncome"></s:hidden><s:property value="miscellaneousIncome"/></td>
																									
																								</tr>
																								<tr height="20">
																									<td align="center"><s:label value="3600"></s:label></td>
																									<td><s:label value="Electricity Debtors"></s:label></td>
																									<td align="right"><s:hidden name="electricityDebtors" id="electricityDebtors"></s:hidden><s:property value="electricityDebtors"/></td>
																								</tr>
																								<tr height="20">
																									<td align="center"><s:label value="5600"></s:label></td>
																									<td><s:label value="Security Deposit"></s:label></td>
																									<td align="right"><s:hidden name="securityDeposit" id="securityDeposit"></s:hidden><s:property value="securityDeposit"/></td>
																									
																								</tr>
																								<tr height="20">
																									<td align="center"><s:label value="5610"></s:label></td>
																									<td><s:label value="Service Connection/Electricity Schemes"></s:label></td>
																									<td align="right"><s:hidden name="serConnOrElecSch" id="serConnOrElecSch"></s:hidden><s:property value="serConnOrElecSch"/></td>
																									
																								</tr>
																								<tr height="20">
																									<td align="center"><s:label value="5640"></s:label></td>
																									<td><s:label value="Tender Deposit"></s:label></td>
																									<td align="right"><s:hidden name="tenderDeposit" id="tenderDeposit"></s:hidden><s:property value="tenderDeposit"/></td>
																									
																								</tr>
																								<tr height="20">
																									<td align="center"><s:label value="5660"></s:label></td>
																									<td><s:label value="Miscellaneous Deposit"></s:label></td>
																									<td align="right"><s:hidden name="miscellaneousDeposit" id="miscellaneousDeposit"></s:hidden><s:property value="miscellaneousDeposit"/></td>
																									
																								</tr>
																								<tr height="20">
																									<td align="center"><s:label value="5800"></s:label></td>
																									<td><s:label value="Cash in Transit"></s:label></td>
																									<td align="right"><s:hidden name="cashInTransit" id="cashInTransit"></s:hidden><s:property value="cashInTransit"/></td>
																									
																								</tr>
																								<tr height="20">
																									<td align="center"><s:label value="5910"></s:label></td>
																									<td><s:label value="For Dishonoured Cheque"></s:label></td>
																									<td align="right"><s:hidden name="forDishonouredCheque" id="forDishonouredCheque"></s:hidden><s:property value="forDishonouredCheque"/></td>
																									
																								</tr>
																								<tr  height="20">
																									<td>&nbsp;</td>
																									<td>&nbsp;</td>
																									<td>&nbsp;</td>
																								</tr>
																								<tr height="20">
																									<td><s:label value=""></s:label></td>
																									<td><b>Sub Total</b></td>
																									<td align="right"><b><s:hidden name="subTotal" id="subTotal"></s:hidden><s:property value="subTotal"/></b></td>
																									
																								</tr>
																								<tr height="20">
																									<td align="center"><s:label value="5222"></s:label></td>
																									<td><s:label value="V.A.T."></s:label></td>
																									<td align="right"><s:hidden name="vat" id="vat"></s:hidden><s:property value="vat"/></td>
																									
																								</tr>
																								<tr  height="20">
																									<td>&nbsp;</td>
																									<td>&nbsp;</td>
																									<td>&nbsp;</td>
																								</tr>
																								<tr height="20">
																									<td>&nbsp;</td>
																									<td><b>Grand Total</b></td>
																									<td align="right"><b><s:hidden name="grandTotal" id="grandTotal"></s:hidden><s:property value="grandTotal"/></b></td>
																									
																								</tr>
																							</tbody>
																						</table>
																					</td>
																				</tr>
																				<tr><td>&nbsp;</td></tr>
																				<tr>
																					<td>Amount in Words: <s:property value="amountInWords"/><s:hidden name="amountInWords" id="amountInWords"></s:hidden></td>
																				</tr>
																			</tbody>
																		</table>
																	</td>
																</tr>
																<tr>
																	<td colspan="2">&nbsp;</td>
																</tr>
																<s:if test="%{preparedTime != null}">
																	<tr height="20">
																		<td colspan="2"><s:label value="Prepared By : "></s:label><b><s:property value="preparedBy"/><s:hidden id="preparedBy" name="preparedBy"></s:hidden> </b> on <b><s:property value="preparedTime"/></b></td>
																	</tr>
																</s:if>
																<s:if test="%{confirmedBy != null}">
																	<tr height="20">
																		<td colspan="2"><s:label value="Confirmed By : "></s:label><b><s:property value="confirmedBy"/></b> on <b><s:property value="confirmedTime"/></b></td>
																	</tr>
																</s:if>
																<tr>
																	<td colspan="2">&nbsp;</td>
																</tr>
															</tbody>
														</table>
													</td>
												</tr>
												<tr>
													<s:hidden name="hdnChequeBranchCode" id="hdnChequeBranchCode"></s:hidden>
													<td id="tdBotton" colspan="3">
														
														
														
														<s:if test="%{isConfirm == 'true'}">
															<s:if test="%{isPivView == 'true'}">
																<s:submit theme="simple" value="Confirm" method="confirmPiv" id="btnSave" onclick="return pivConfirmValidate()"></s:submit>
															</s:if>
															<s:else>
																<s:submit theme="simple" value="Confirm" method="confirmPiv" id="btnSave"  disabled="true"></s:submit>
															</s:else>
														</s:if>
														<s:elseif test="%{isCancel == 'true'}">
															<s:if test="%{isPivView == 'true'}">
																	<s:submit theme="simple" value="Cancel" method="cancelPiv" id="btnSave" ></s:submit>
															</s:if>
															<s:else>
																<s:submit theme="simple" value="Cancel" method="cancelPiv" id="btnSave"  disabled="true"></s:submit>
															</s:else>
														</s:elseif>
														<s:elseif test="%{isGenerate == 'true'}">
															<s:if test="%{isPivView == 'true'}">
																<s:submit theme="simple" value="Save & Print" method="saveOnly" id="btnSaveOnly" onclick="return estimatePIVFormValidate()"></s:submit>
																<!-- <s:submit theme="simple" value="Save & Print" method="save" id="btnSave" onclick="return estimatePIVFormValidate()"></s:submit> -->
															</s:if>
															<s:else>
																
																<s:submit theme="simple" value="Save & Print" id="btnSaveOnly"  disabled="true"></s:submit>
															
															</s:else>
														</s:elseif>
														<s:hidden></s:hidden>
														<s:elseif test="%{isPrint == 'true'}">
															<s:if test="%{isPivView == 'true'}">
																<input type="button" value="Print" name="btnPrint" onclick="javascript:printing()" />
																
																
															</s:if>
															<s:else>
																<s:submit theme="simple" value="Print" id="btnSave"  disabled="true"></s:submit>
															</s:else>
														</s:elseif> 
														<s:elseif test="%{isModify == 'true'}">
															<s:if test="%{isPivView == 'true'}">
																<s:submit theme="simple" value="Change Receipt No" method="modifyPiv" id="btnSave" ></s:submit>
															</s:if>
															<s:else>
																<s:submit theme="simple" value="Change Receipt No" id="btnSave"  disabled="true"></s:submit>
															</s:else>
														</s:elseif> 
														<s:submit theme="simple" value="Clear" method="clear" id="btnClear"></s:submit>
														<s:submit theme="simple" value="Exit" method="close" id="btnClose"></s:submit>
														<s:hidden id="state" name="state"></s:hidden>
														<s:hidden id="hiddenSecondState" name="hiddenSecondState"></s:hidden>
														<s:hidden id="path" name="path"></s:hidden>
														<s:hidden id="status" name="status"></s:hidden>	
														<s:hidden id="errorMessage" name="errorMessage"></s:hidden>		
														<applet codebase="../../PrintLib" code="printPiv.PrintPayingInVoucher" archive="PrintJar.jar" name="pivApplet" width="1" height="1"  ></applet>								
													</tr>
												</tbody>
											</table>
											
										</s:form>
									</td>
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
						<table class="tblFooter" border="0" cellpadding="0"
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