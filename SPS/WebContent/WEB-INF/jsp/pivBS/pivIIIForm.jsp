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
<title>Paying In Voucher</title>
<link rel="stylesheet" type="text/css" href="../../style/style.css" />
<script type="text/javascript" src="../../script/pivForm.js"> </script>
<script type="text/javascript" src="../../script/javascript.js"> </script>
<script type="text/javascript" src="../../script/estimate.js"> </script>
<script type="text/javascript" src="../../script/javascript.js"> </script>
<script type="text/javascript" src="../../script/jquery/jquery.js"> </script>
<script type="text/javascript" src="../../script/jquery/jquery.min.js"> </script>
<script type="text/javascript">

function display_branches(val){
	
	 $.getJSON("sps/loadbranchCodes.ajax?bankCode=" + val.value, {ajax_command:"loadbranchCodes",ajax_ieCacheFix:new Date().getTime()}, function(json) {

      if (json.branchCodes != null && json.branchCodes.length > 0) {
          for (x = 0; x < json.branchCodes.length; x++) {
           
              $("#branchCode select").append($('<option></option>').val(json.branchCodes[x].id).html(json.branchCodes[x].name));
          }
      }

	 });

}
</script>

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
									<td><s:form action="pivFormIIBS" method="POST"
										validate="true" theme="simple" name="frmPiv" id="frmPiv">
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
														<thead>
															<tr>
																<th width="40%"></th>
																<th width="10%"></th>
																<th width="*"></th>
																
															</tr>
														</thead>
														<tbody>
															<tr>
																<td class="tdHeadings">PAYING IN VOUCHER CEYLON ELECTRICITY BOARD</td>
																<td></td>
																<td class="tdMsgStyle"><s:property  value="errorMessage"></s:property></td>
															</tr>
														</tbody>

													</table>
													</td>
												</tr>
												<tr>
													<td>
													<table width="100%" class="tldFromContent" border="0" >
														<thead>
															<tr>
																
																<th width="14%"></th>
																<th width="11%"></th>
																<th width="12%"></th>
																<th width="13%"></th>
																<th width="12%"></th>
																<th width="13%"></th>
																<th width="16%"></th>
																<th width="*%"></th>
															</tr>
														</thead>
														<tbody>
														<tr>
																<td>PIV No</td>
																<s:if test="%{state=='new'}">
																<td><s:textfield name="pivNo" id="txfdPivNo" size="24" maxlength="24" onblur="javascript:{this.value = this.value.toUpperCase(); }"></s:textfield></td>
																<td></td>
																</s:if>
																<s:elseif test="%{state=='issueReInsp'}">
																<td><s:textfield name="pivNo" id="txfdPivNo" size="24" maxlength="24" onblur="javascript:{this.value = this.value.toUpperCase(); }"></s:textfield></td>
																<td></td>
																</s:elseif>
																<s:elseif test="%{state=='afterestimated'}">
																<td><s:textfield name="pivNo" id="txfdPivNo" size="24" maxlength="24" onblur="javascript:{this.value = this.value.toUpperCase(); }"></s:textfield></td>
																<td></td>
																</s:elseif>
																<s:else>
																<td><s:textfield name="pivNo" id="txfdPivNo" size="24" maxlength="24" onblur="javascript:{this.value = this.value.toUpperCase(); }"></s:textfield></td>
																<td><s:submit theme="simple" value="Find PIV No" method="findPivNo" id="btnFindPivNo" onclick="return checkPivNoNCostcenterNotNull()" onblur="javascript:{this.value = this.value.toUpperCase(); }"></s:submit></td>
																</s:else>
																<td></td>
																<td></td>
																<td></td>
																<td>PIV Receipt No</td>
																<td><s:textfield name="pivReceiptNo" id="txfdPivReceiptNo" size="10" maxlength="10" onblur="javascript:{this.value = this.value.toUpperCase(); }"></s:textfield></td>
																
															</tr>
															<tr>
																<td>People's Bank Branch</td>
																<td><s:textfield name="payingBankName" id="txfdPayingBankName" size="15"  maxlength="15"></s:textfield><s:textfield name="payingBranchName" id="txfdPayingBranchName" size="15" ></s:textfield><s:hidden name="payingBankCode" id="txfdPayingBankCode" ></s:hidden><s:hidden name="payingBranchCode" id="txfdPayingBranchCode" ></s:hidden></td>
																<td>C.E.B. Branch</td>
																<td><s:textfield name="cebBranch" id="txfdCebBranch" size="25" ></s:textfield></td>
																<td>Cost Center No</td>
																<td><s:textfield name="costCenterNo" id="txfdCostCenterNo" size="6" maxlength="6"></s:textfield></td>
																<td>Date(DD/MM/YYYY))</td>
																<td><s:textfield name="date"  id="txfdDate" size="10" ></s:textfield></td>
															</tr>
															
														</tbody>
													</table>
													
													</td>
												</tr>
												<tr>
													<td>
													<table width="100%" class="tldFromContent" border="0" >
														<thead>
															<tr>
																
																<th width="40%"></th>
																<th width="60%"></th>
															</tr>
														</thead>
														<tbody>
															<tr valign="top">
																<td>
																<s:hidden name="hdnChequeBranchCode" id="hdnChequeBranchCode"></s:hidden>
																<table  width="100%" class="tldFromContent" border="0">
																	<thead>
																		<tr>
																			
																			<th width="40%"></th>
																			<th width="60%"></th>
																			
																		</tr>
																	</thead>
																	<tbody>
																		<tr>
																			<s:if test="%{state=='new'}">
																			<td><s:label value="Reference No"></s:label></td>
																			<td><s:select  name="referenceNo" id="txfdReferenceNo" headerKey="1" headerValue="-- Please Select --" list="applicationIdList" onchange="form.submit()" /> </td>
																			</s:if>
																			<s:elseif test="%{state=='issueReInsp'}">
																			<td><s:label value="Reference No"></s:label></td>
																			<td><s:select  name="referenceNo" id="txfdReferenceNo" headerKey="1" headerValue="-- Please Select --" list="applicationNoList" onchange="form.submit()" /> </td>
																			</s:elseif>
																			<s:elseif test="%{state=='afterestimated'}">
																			<td><s:label value="Reference No"></s:label></td>
																			<td><s:textfield  name="referenceNo" id="txfdReferenceNo" size="24" maxlength="24" /><s:submit theme="simple" value="Find EST No" method="findEstNo" id="btnFindEstNo" onclick="return checkPivNoNCostcenterNotNull()" onblur="javascript:{this.value = this.value.toUpperCase(); }"></s:submit></td>
																			</s:elseif>
																			<s:else> 
																			<td><s:label value="Reference No"></s:label></td>
																			<td><s:textfield name="referenceNo" id="txfdReferenceNo" size="24" maxlength="24"></s:textfield></td>
																			</s:else>
																		</tr>
																		<tr>
																			<td><s:label value="Id No"></s:label></td>
																			<td><s:textfield name="idNo" id="txfdIdNo" size="10" ></s:textfield></td>
																		</tr>
																		<tr>
																			<td><s:label value="Depositor's Name"></s:label></td>
																			<td><s:textarea name="fullName" id="txfdFullName" cols="30" rows="1"></s:textarea></td>
																		</tr>
																		<tr>
																			<td><s:label value="Address"></s:label></td>
																			<td><s:textarea name="fullAddress"  id="txfdFullAddress" cols="30" rows="2"></s:textarea></td>
																		</tr>
																		<tr>
																			<td><s:label value="Payment Mode"></s:label></td>
																			<td><s:radio name="paymentMode" id="RadioPaymentMode" list="#{'C':'Cash','Q':'Cheque'}" onchange="disableChequeNo()"/><s:hidden id="hiddenPaymentMode" name="hiddenPaymentMode"></s:hidden></td>
																			<!--td><s:textfield name="paymentMode" size="10" ></s:textfield></td-->
																		</tr>
																		<tr>
																			<td><s:label value="Pay Date"></s:label></td>
																			<!--<td><s:textfield name="payDate" id="txfdPayDate" /></td>-->
																			<td><sx:datetimepicker  name="payDate" id="txfdPayDate" displayFormat="yyyy-MM-dd"  required="true" /></td>
																		</tr>
																		<tr>
																			<td><s:label value="Bank Code"></s:label></td>
																			<!-- <td><s:textfield name="chequeBankCode" id="txfdChequeBankCode" size="10" ></s:textfield></td>-->
																			<td><s:select name="chequeBankCode" id="txfdChequeBankCode" headerKey=""  headerValue="Select Bank" list="bankList"  listKey="bankCode" listValue="%{bankCode+' - '+bankName}" onchange="javascript:display_branches(this)" /></td>
																			
																		</tr>
																		<tr>
																			<td><s:label value="Branch Code"></s:label></td>
																			
																			<td class="">
																			<div id="branchCode"><select name="chequeBranchCode" id="chequeBranchCode" ><option value="0">--Select Branch Code--</option></select></div>
																			
																			</td>	
																		</tr>
																		<tr>
																			<td><s:label value="Cheque Date"></s:label></td>
																			<!-- <td><s:textfield name="chequeDate" id="txfdChequeDate" /></td>-->
																			<td><sx:datetimepicker name="chequeDate" id="txfdChequeDate" displayFormat="yyyy-MM-dd"  required="true"  disabled="true"/></td>
																		</tr>
																		
																		<tr>
																			<td><s:label value="Cheque No"></s:label></td>
																			<td><s:textfield name="chequeNo" id="txfdChequeNo" size="8" maxlength="8"></s:textfield></td>
																		</tr>
																		<tr>
																			<td><s:label value="Amount Allocated"></s:label></td>
																			<td><s:textfield name="loanAmount" id="txfdAllocatedChecAmount" size="20" ></s:textfield></td>
																		</tr>
																		<tr>
																			<td><s:label value="Amount in Words"></s:label></td>
																			<td><s:textarea name="amountInWords"  id="txfdAmountInWords" cols="30" rows="2"></s:textarea></td>
																		</tr>
																		<tr>
																			<td></td>
																			<td></td>
																		</tr>
																		<tr>
																			<td><s:label value="Prepared By"></s:label></td>
																			<td><s:textfield name="preparedBy" id="txfdPreparedBy" size="10" ></s:textfield></td>
																		</tr>
																		<tr>
																			<td><s:label value="Certified By"></s:label></td>
																			<td><s:textfield name="approvedBy"  id="txfdApprovedBy" size="10" ></s:textfield></td>
																		</tr>
																		<tr>
																			<td colspan="2">Depositor's Signature</td>
																		</tr>
																		<tr>
																			<td colspan="2">Bank Officer's Signature</td>
																			
																		</tr>
																	</tbody>
																</table>
																</td>
																<td>
																<table width="100%" class="tldFromContent" border="0" >
																	<thead>
																		<tr>
																			<th width="100%"></th>
																		</tr>
																	</thead>
																	<tbody>
																		<tr>
																			<td>
																			<table width="100%" class="tldFromContent" border="0" >
																				<thead>
																					<tr>
																						<th width="18%"></th>
																						<th width="*"></th>
																					</tr>
																				</thead>
																				<tbody>
																					<tr>
																						<td><s:label value="Job Description"></s:label></td>
																						<td><s:textfield name="description" id="txfdDescription" size="75" ></s:textfield></td>
																					</tr>
																				</tbody>
																			</table>
																			</td>
																		</tr>
																		<tr>
																			<td>
																			<table width="100%" class="tldFromContent" border="0" >
																				<thead>
																					<tr>
																						
																						<th width="14%"></th>
																						<th width="55%"></th>
																						<th width="25%"></th>
																						<th width="*%"></th>
																						
																					</tr>
																				</thead>
																				<tbody>
																					<tr>
																						<td><s:label value="Code No"></s:label></td>
																						<td><s:label value="Description"></s:label></td>
																						<td align="right"><s:label value="Amount"></s:label></td>
																						<td><s:label value="Cents"></s:label></td>
																					</tr>
																					<tr>
																						<td><s:label value="1300"></s:label></td>
																						<td><s:label value="Miscellaneous Income"></s:label></td>
																						<td align="right"><s:textfield name="miscellaneousIncome" id="txfdMiscellaneousIncome" size="16" onkeypress="return restrictToTwoDecimalPossition(this,event)" onchange="return setSubTotal()" ></s:textfield></td>
																						<td></td>
																					</tr>
																					<tr>
																						<td><s:label value="3600"></s:label></td>
																						<td><s:label value="Electricity Debtors"></s:label></td>
																						<td align="right"><s:textfield  name="electricityDebtors" id="txfdElectricityDebtors"   size="16" onchange="return setSubTotal()"></s:textfield></td>
																						<td></td>
																					</tr>
																					<tr>
																						<td><s:label value="5600"></s:label></td>
																						<td><s:label value="Security Deposit"></s:label></td>
																						<td align="right"><s:textfield name="securityDeposit" id="txfdSecurityDeposit"  size="20" onchange="return setSubTotal()"></s:textfield></td>
																						<td></td>
																					</tr>
																					<tr>
																						<td><s:label value="5610"></s:label></td>
																						<td><s:label value="Service Connection/Electricity Schemes"></s:label></td>
																						<td align="right"><s:textfield name="serConnOrElecSch" id="txfdSerConnOrElecSch"  size="16" onchange="return setSubTotal()"></s:textfield></td>
																						<td></td>
																					</tr>
																					<tr>
																						<td><s:label value="5640"></s:label></td>
																						<td><s:label value="Tender Deposit"></s:label></td>
																						<td align="right"><s:textfield name="tenderDeposit" id="txfdTenderDeposit"  size="16" onchange="return setSubTotal()"></s:textfield></td>
																						<td></td>
																					</tr>
																					<tr>
																						<td><s:label value="5660"></s:label></td>
																						<td><s:label value="Miscellaneous Deposit"></s:label></td>
																						<td align="right"><s:textfield name="miscellaneousDeposit" id="txfdMiscellaneousDeposit"  size="16" onchange="return setSubTotal()"></s:textfield></td>
																						<td></td>
																					</tr>
																					<tr>
																						<td><s:label value="5800"></s:label></td>
																						<td><s:label value="Cash in Transit"></s:label></td>
																						<td align="right"><s:textfield name="cashInTransit" id="txfdCashInTransit"  size="16" onchange="return setSubTotal()"></s:textfield></td>
																						<td></td>
																					</tr>
																					<tr>
																						<td><s:label value="5910"></s:label></td>
																						<td><s:label value="For Dishonoured Cheque"></s:label></td>
																						<td align="right"><s:textfield name="forDishonouredCheque" id="txfdForDishonouredCheque"  size="16" onchange="return setSubTotal()"></s:textfield></td>
																						<td></td>
																					</tr>
																					<tr>
																						<td><s:label value="1380"></s:label></td>
																						<td><s:label value="Application Fee"></s:label></td>
																						<td align="right"><s:textfield name="applicationFee" id="txfdApplicationFee" size="16"  onkeypress="return restrictToTwoDecimalPossition(this,event)" onchange="return setSubTotal()"></s:textfield></td>
																						<td></td>
																					</tr>
																					<tr>
																						<td height="10"></td>
																						<td></td>
																						<td></td>
																						<td></td>
																					</tr>
																					<tr>
																						<td><s:label value=""></s:label></td>
																						<td><s:label value="Sub Total"></s:label></td>
																						<td align="right"><s:textfield name="subTotal" id="txfdSubTotal" readonly="true" size="20" ></s:textfield></td>
																						<td></td>
																					</tr>
																					<tr>
																						<td><s:label value="5222"></s:label></td>
																						<td><s:label value="V.A.T."></s:label></td>
																						<td align="right"><s:textfield name="vat" id="txfdVat"  size="20" onchange="return  setGrandTotal()()"></s:textfield></td>
																						<td></td>
																					</tr>
																					<tr>
																						<td height="10"></td>
																						<td></td>
																						<td></td>
																						<td></td>
																					</tr>
																					<tr>
																						<td><s:label value="1300"></s:label></td>
																						<td><s:label value="Grand Total"></s:label></td>
																						<td align="right"><s:textfield name="grandTotal" id="txfdGrandTotal" readonly="true" size="20" ></s:textfield></td>
																						<td></td>
																					</tr>
																			</tbody>
																			</table>
																			</td>
																		</tr>
																		<tr>
																			<td>
																			<table width="100%" class="tldFromContent" border="0" >
																				<thead>
																					<tr>
																						<th width="30%"></th>
																						<th width="*"></th>
																					</tr>
																				</thead>
																				<tbody>
																					<!--  <tr>
																						<td colspan="2"> If Bank loan is granted</td>
																					</tr>
																					<tr>
																						<td>Loan Reference</td>
																						<td><s:textfield name="loanReference" id="txfdLoanReference" size="10" ></s:textfield> </td>
																					</tr>
																					<tr>
																						<td>Loan Amount</td>
																						<td><s:textfield name="loanAmount" id="txfdLoanAmount" size="10" ></s:textfield> </td>
																					</tr>-->
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
													<td id="tdBotton" colspan="3">
													<s:submit theme="simple" value="Save" method="save" id="btnSave" onclick="return validate_form()"></s:submit>
													<!--  <input type="button"  value="Print"  id="btnPrint" onclick="callPrintEstimate()"></input>-->
													<s:if test="%{state=='afterestimatedDone'}"> 
													<s:submit theme="simple" value="Save" method="saveAfter" id="btnSave" onclick="return validate_form()"></s:submit>
													</s:if>
													<s:if test="%{state=='confirmReInsp'}">
													<s:submit theme="simple" value="Confirm" method="confirmReInsp" id="btnConfirm" onclick="return validate_formConfirm()"></s:submit>
													</s:if>
													<s:else>
													<s:submit theme="simple" value="Confirm" method="confirm" id="btnConfirm" onclick="return validate_formConfirm()"></s:submit>
													</s:else>
													<s:if test="%{state=='printPIV'}">
													<input type="button"  value="Print"  id="btnPrint" onclick="callPrintEstimate()"></input>
													<!--<s:submit theme="simple" value="Print" id="btnPrint" onclick="javascript:callPrintEstimate()" type="button" ></s:submit>-->
													</s:if>
													<s:if test="%{state=='modify'}">
													<s:submit theme="simple" value="Modify" method="modify" id="btnModify" onclick="return validate_formModify()" ></s:submit>
													</s:if>
													<s:submit theme="simple" value="Exit" method="close" id="btnClose"></s:submit>
													<s:reset theme="simple" align="right" value="Clear" onclick="disableChequeNo()"/>
													<applet codebase="../../PrintLib" code="printPiv.PrintPayingInVoucher" name="pivApplet" archive="PrintJar.jar" width="1" height="1"></applet>
													<s:hidden id="state" name="state"></s:hidden>
													<s:hidden id="serviceStreetAddress" name="serviceStreetAddress"></s:hidden>
													<s:hidden id="serviceSuburb" name="serviceSuburb"></s:hidden>
													<s:hidden id="serviceCity" name="serviceCity"></s:hidden>
													<s:hidden id="servicePostalCode" name="servicePostalCode"></s:hidden>
													<s:hidden id="hiddenSecondState" name="hiddenSecondState"></s:hidden>
													<s:hidden id="path" name="path"></s:hidden>
													<s:hidden id="status" name="status"></s:hidden>	
													<s:hidden id="errorMessage" name="errorMessage"></s:hidden>
													<s:hidden id="region" name="region"></s:hidden>
													
																							
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