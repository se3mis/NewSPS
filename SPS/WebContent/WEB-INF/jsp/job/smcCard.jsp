<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<html>
<head>
<sx:head />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>

<s:if test="%{isViewOnly == 'true'}">
VIEW ESTIMATION
</s:if>
<s:elseif test="%{isModify=='true'}">
MODIFY ESTIMATION
</s:elseif>
<s:elseif test="%{isUndoReject=='true'}">
UNDO REJECTED ESTIMATION
</s:elseif>
<s:else>NEW ESTIMATION</s:else>
</title>
<link rel="stylesheet" type="text/css" href="../../style/style.css" />

<script type="text/javascript" src="../../script/job.js"> </script>
<script type="text/javascript" src="../../script/javascript.js"> </script>
<script type="text/javascript">
function abc(divid,imgId)
{
	if(document.getElementById(divid).style.display == 'none'){
		 document.getElementById(imgId).src ="../../image/down.jpg";
      document.getElementById(divid).style.display = 'block';
    }else{
      document.getElementById(divid).style.display = 'none';
	  document.getElementById(imgId).src ="../../image/up.jpg";
    }
}


function hide_list()
{
	document.getElementById('otherMat').style.display = 'none';
	document.getElementById('others').style.display = 'none';
}


function show_materials() 
{
	document.style='cursor:hand';
	document.getElementById('otherMat').style.display = '';
	document.getElementById('others').style.display = 'none';
	dojo.event.topic.publish("display_mat");
}


function other_costs() 
{
	document.getElementById('others').style.display = '';
	document.getElementById('otherMat').style.display = 'none';
	dojo.event.topic.publish("display_oth_cost");
}


function add_materials() 
{
	var len = document.getElementById('lineLength').value;
	
	document.getElementById('otherMat').style.display = 'none';
	dojo.event.topic.publish("add_mat");
}


function remove_materials() 
{
	if(deleteConfirm())
	{
		document.getElementById('otherMat').style.display = 'none';
		document.getElementById('others').style.display = 'none';
		dojo.event.topic.publish("remove_mat");
	}
}


function add_other_costs() 
{
	document.getElementById('otherMat').style.display = 'none';
	document.getElementById('others').style.display = 'none';
	dojo.event.topic.publish("add_oth_cost");
}



</script>
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
						<table class="tblContent" border="0" cellpadding="0" cellspacing="0">
							<tbody>
								<tr>
									<td>
										<s:form id="frmEstimate" name="frmEstimate" action="smcCard" method="POST"	validate="true" theme="simple">
											<s:hidden id="isViewJob" name="isViewJob"></s:hidden>
											<s:hidden id="street" name="street"></s:hidden>
											<s:hidden id="suburb" name="suburb"></s:hidden>
											<s:hidden id="city" name="city"></s:hidden>
											<s:hidden id="postalCode" name="postalCode"></s:hidden>
											<table width="75%" class="tldFromContent" border="0">
												<tbody>
													<tr>
														<td class="listHeader" colspan="4">SERVICE MAIN CARD</td>
													</tr>
													<tr>
														<td align="center"class="STATUSMSG" ><s:property  value="successMessage" ></s:property></td>									
													</tr>	
													<tr>
														<td align="center" class="STATUSMSGERR" ><s:property  value="errorMessage" ></s:property></td>																					
													</tr>
													<tr>
														<td colspan="2">
														<div id="myDivx">
															<table width="900" border="0">
																<tr>
																	<td width="5"></td>
																	<td>
																		<table width="100%" border="0">
																				
																			<s:if test="%{state!='nCjobFinishDirect' && state!='cRjobFinishDirect' }">
																			<tr>
																				<td colspan="8">Job Number :<s:textfield name="jobNo" id="txtJobNo" size="18" maxlength="18" onblur="javascript:{this.value = this.value.toUpperCase(); }"></s:textfield>
																				<s:submit theme="simple" align="right" value="Find" method="viewOrderCardDetails" id="btnFindApplicationNo" onclick="return checkJobNoNotNull()"></s:submit>Cost Center : <s:property value="costCenterNo"  />
																				</td>
																			</tr>
																			</s:if>
																			<s:if test="%{state=='nCjobFinishDirect'}">
																			<tr>
																			<td >Job Numbers :</td>
																			<td colspan="3"><s:select name="jobNo" list="listjobNo" id="listjobNo" headerKey="-1" headerValue="-- Please Select --" onchange="form.submit()" > </s:select></td>
    																		<td colspan="4"> Cost Center : <s:property value="costCenterNo"  /></td>
    																		</tr>
    																		</s:if>
    																		<s:if test="%{state=='cRjobFinishDirect'}">
																			<tr>
																			<td >Job Numbers :</td>
																			<td colspan="3"><s:select name="jobNo" list="listjobNo" id="listjobNo" headerKey="-1" headerValue="-- Please Select --" onchange="form.submit()" > </s:select></td>
    																		<td colspan="4"> Cost Center : <s:property value="costCenterNo"  /></td>
    																		</tr>
    																		</s:if>
    																		
																			<tr height="20"><td colspan="8">&nbsp;</td></tr>
																			
																			<tr height="20">
																				<td nowrap="nowrap">SMC Type</td>
																				<td><s:textfield name="smcType" id="smcType" readonly="true"  size="10"/></td>
																				<td>SIN No</td>
																				<td><s:textfield name="sinNo" id="sinNo" size="10"/></td>
																				<td colspan="4">&nbsp;</td>
																			</tr>
																			<tr height="20">
																				<td>Cost Code</td>
																				<td><s:textfield name="costCode" id="costCode"  readonly="true" size="10" maxlength="10"/>	 </td>
																				<td>ECSC</td>
																				<td><s:textfield name="ecsc" id="ecsc"  readonly="true" size="10"/></td>
																				<td>Year</td>
																				<td><s:textfield name="year" id="year"  readonly="true" size="10"/></td>
																				<td>Serial Job No.</td>
																				<td><s:textfield name="serialJobNo" id="serialJobNo"  readonly="true" size="20"/></td>
																			</tr>
																			<tr height="20">
																				<td>No. of Meters</td>
																				<td>
																				<s:select name="noOfMeters" id="noOfMeters" headerKey=""  headerValue="Select" list="#{'1':'1','3':'3'}" onchange="meterDisplay()"  />
																				</td>
																				<td>Connected Date (dd/mm/yyyy)</td>
																				<td><sx:datetimepicker name="conDate" id="conDate" displayFormat="dd/MM/yyyy"  required="true"   />
																				</td>
																				<td>Average Consumption</td>
																				<td><s:textfield name="avgConsump" id="avgConsump" maxlength="5"  size="10" onkeyup="numericValidate(this,event);"/></td>
																				<td colspan="2">&nbsp;</td>
																			</tr>
																			<tr height="20">
																				<td>Meter Number 1</td>
																				<td><s:textfield name="meterNo1" id="meterNo1" maxlength="8"  size="10"/>	 </td>
																				<td>Reading 1</td>
																				<td><s:textfield name="reading1" id="reading1" maxlength="10"  size="10" onkeyup="numericValidate(this,event);"/></td>
																				<td>Meter Type 1</td>
																				<td><s:textfield name="meterType1" id="meterType1"  maxlength="1" size="10"/></td>
																				<td>No. of Digits 1</td>
																				<td><s:textfield name="noDigit1" id="noDigit1"  maxlength="2" size="10" onkeyup="numericValidate(this,event);"/></td>
																			</tr>
																			<tr height="20">
																				<td>Seal Numbers (1-3)</td>
																				<td colspan="2"><s:textfield name="SealNumber1" id="SealNumber1"  maxlength="7" size="7" onkeyup="numericValidate(this,event);"/><s:textfield name="SealNumber2" id="SealNumber2" maxlength="7"  size="7" onkeyup="numericValidate(this,event);"/><s:textfield name="SealNumber3" id="SealNumber3"  maxlength="7" size="7" onkeyup="numericValidate(this,event);"/>	 </td>
																				<td></td>
																				<td></td>
																				<td></td>
																				<td></td>
																				<td></td>
																			</tr>
																			<tr height="20">
																				<td>Meter Number 2</td>
																				<td><s:textfield name="meterNo2" id="meterNo2"  maxlength="8" size="10"/>	 </td>
																				<td>Reading 2</td>
																				<td><s:textfield name="reading2" id="reading2" maxlength="10"  size="10" onkeyup="numericValidate(this,event);"/></td>
																				<td>Meter Type 2</td>
																				<td><s:textfield name="meterType2" id="meterType2"  maxlength="1" size="10" /></td>
																				<td>No. of Digits 2</td>
																				<td><s:textfield name="noDigit2" id="noDigit2"  maxlength="2" size="10" onkeyup="numericValidate(this,event);"/></td>
																			</tr>
																			<tr height="20">
																				<td>Seal Numbers (4-6)</td>
																				<td colspan="2"><s:textfield name="SealNumber4" id="SealNumber4"  maxlength="7" size="7" onkeyup="numericValidate(this,event);"/><s:textfield name="SealNumber5" id="SealNumber5" maxlength="7"  size="7" onkeyup="numericValidate(this,event);"/><s:textfield name="SealNumber6" id="SealNumber6"  maxlength="7" size="7" onkeyup="numericValidate(this,event);"/>	 </td>
																				<td></td>
																				<td></td>
																				<td></td>
																				<td></td>
																				<td></td>
																			</tr>
																			<tr height="20">
																				<td>Meter Number 3</td>
																				<td><s:textfield name="meterNo3" id="meterNo3"  maxlength="8" size="10" />	 </td>
																				<td>Reading 3</td>
																				<td><s:textfield name="reading3" id="reading3" maxlength="10"  size="10" onkeyup="numericValidate(this,event);"/></td>
																				<td>Meter Type 3</td>
																				<td><s:textfield name="meterType3" id="meterType3"  maxlength="1" size="10" /></td>
																				<td>No. of Digits 3</td>
																				<td><s:textfield name="noDigit3" id="noDigit3"  maxlength="2" size="10" onkeyup="numericValidate(this,event);"/></td>
																			</tr>
																			<tr height="20">
																				<td>Seal Numbers (7-9)</td>
																				<td colspan="2"><s:textfield name="SealNumber7" id="SealNumber7"  maxlength="7" size="7" onkeyup="numericValidate(this,event);"/><s:textfield name="SealNumber8" id="SealNumber8" maxlength="7"  size="7" onkeyup="numericValidate(this,event);"/><s:textfield name="SealNumber9" id="SealNumber9"  maxlength="7" size="7" onkeyup="numericValidate(this,event);"/>	 </td>
																				<td></td>
																				<td></td>
																				<td></td>
																				<td></td>
																				<td></td>
																			</tr>
																			<tr height="20">
																				<td>Payment Mode</td>
																				<td><s:textfield name="payMode" id="payMode"  readonly="true" size="10"/>	 </td>
																				<td>Payment Date (dd/mm/yyyy)</td>
																				<td><s:textfield name="payDate" id="payDate"  readonly="true" size="10"/></td>
																				<td colspan="4">&nbsp;</td>
																			</tr>
																			<tr height="20">
																				<td>Security Depost</td>
																				<td><s:textfield name="secDeposit" id="secDeposit"  readonly="true" size="10"/></td>
																				<td>Service Main Charge</td>
																				<td><s:textfield name="smCharge" id="smCharge"  readonly="true" size="10"/></td>
																				<td>Tax</td>
																				<td><s:textfield name="taxAmount" id="taxAmount"  readonly="true" size="10"/>	 </td>
																				<td>Total</td>
																				<td><s:textfield name="totalCost" id="totalCost"  readonly="true" size="10"/></td>
																			</tr>
																			<tr height="20">
																				<td>Tariff</td>
																				<td><s:textfield name="tariffCode" id="tariffCode"  readonly="true" size="10"/></td>
																				<td>KVA</td>
																				<td><s:textfield name="kva" id="kva"  size="10"/></td>
																				<td>Conn. Type</td>
																				<td><s:textfield name="connectionType" id="connectionType"  readonly="true" size="10"/></td>
																				<td>No. of Phs</td>
																				<td><s:textfield name="phase" id="phase"  readonly="true" size="10"/></td>
																			</tr>
																			<tr height="20">
																				<td>Bank Reference</td>
																				<td><s:textfield name="bankRef" id="bankRef"  readonly="true" size="25"/>	 </td>
																				<td>Neighbour's A/C No.</td>
																				<td><s:textfield name="neighboursAccNo" id="neighboursAccNo"  readonly="true" size="10"/></td>
																				<td>Bank/Branch Code</td>
																				<td><s:textfield name="bankBranch" id="bankBranch"  readonly="true" size="10"/></td>
																				<td colspan="2">&nbsp;</td>
																			</tr>
																			<tr height="20">
																				<td>Last Name</td>
																				<td colspan="3"><s:textfield name="lastName" id="lastName"  readonly="true" size="40"/>	 </td>
																				<td>First Name</td>
																				<td colspan="3"><s:textfield name="firstName" id="firstName"  readonly="true" size="10"/></td>
																				
																			</tr>
																			<tr height="20">
																				<td>Address</td>
																				<td colspan="7"><s:textfield name="address" id="address"  readonly="true" size="70"/>	 </td>
																			</tr>
																			<tr height="20">
																				<td>PIV Number</td>
																				<td><s:textfield name="pivNo" id="pivNo"  readonly="true" size="10"/>	 </td>
																				<td>PIV Date</td>
																				<td><s:textfield name="pivDate" id="pivDate"  readonly="true" size="10"/></td>
																				<td>Customer Type</td>
																				<td><s:textfield name="cusType" id="cusType"  readonly="true" size="10"/></td>
																				<td colspan="2">&nbsp;</td>
																			</tr>
																			<tr height="20">
																				<td>Reader Code</td>
																				<td><s:textfield name="readerCode" id="readerCode" maxlength="2" size="10" />	 </td>
																				<td>Pack No.</td>
																				<td><s:textfield name="packNo" id="packNo" maxlength="2" size="10" /></td>
																				<td>Walk Sequence</td>
																				<td><s:textfield name="walkSeq" id="walkSeq" maxlength="5"  size="10"/></td>
																				<td>Old Acct. Number</td>
																				<td><s:textfield name="oldAcct" id="oldAcct"  maxlength="10" size="10"/></td>
																			</tr>
																			<s:if test="%{jobType=='SMC' || jobType=='CRJ'}">
																			<tr height="20">
																				<td>loanCode</td>
																				<td><s:textfield name="hid_loanCode" id="hid_loanCode" maxlength="2"  readonly="true" size="10" />	 </td>
																				<td>loanDate</td>
																				<td><s:textfield name="hid_loanDate" id="hid_loanDate" maxlength="2" readonly="true" size="10" /></td>
																				<td>noOfInstalment</td>
																				<td><s:textfield name="hid_noOfInstalment" id="hid_noOfInstalment" maxlength="5"  readonly="true" size="10"/></td>
																				<td>loanAmount</td>
																				<td><s:textfield name="hid_loanAmount" id="hid_loanAmount"  maxlength="10" readonly="true" size="10"/></td>
																				<td>instalmantAmount</td>
																				<td><s:textfield name="hid_instalmantAmount" id="hid_instalmantAmount"  maxlength="10" readonly="true" size="10"/></td>
																				
																			</tr>
																			</s:if>
															
																		</table>
																	</td>
																</tr>
															</table>
															</div>
														</td>
													</tr>
													<tr>
													<td colspan="2">
														<table>
															<tr>
																<td width="5"></td>
																<td>
																	<s:if test="isViewJob!=null">
																		
																		<s:if test="%{state=='nCjobFinishDirect'}">
																		<s:submit theme="simple" value="job Finish" method="nCjobFinish" id="btnnCjobFinish" onclick="return formValidationNCjobFinish()"></s:submit>
																		</s:if>
																		<s:if test="%{state=='cRjobFinishDirect'}">
																		<s:submit theme="simple" value="job Finish" method="nCjobFinish" id="btnnCjobFinish" onclick="return formValidationCRjobFinish()"></s:submit>
																		</s:if>
																		<s:if test="%{state!='nCjobFinishDirect' || state!='cRjobFinishDirect'}">
																		<s:submit theme="simple" value="Save" method="save" id="btnSave" onclick="return formValidation()"></s:submit>
																		</s:if>
																		<input type="button"  value="Print"  id="btnSavePrint" onclick="Print()"></input>
																		
																	</s:if>
																	<s:else>
																		<s:submit theme="simple" value="Save" id="btnSave" disabled="true" ></s:submit>
																		<input type="button"  value="Print"  id="btnSavePrint" disabled="disabled" onclick="saveAndPrint()"></input>
																	</s:else>
																	<s:submit theme="simple" value="Clear" method="clear" id="btnClear" ></s:submit>
																	
																	<s:submit theme="simple" value="Exit" method="close" id="btnClose" ></s:submit>
																	<s:submit theme="simple" value="Back to Billing" method="back" id="btnBack" ></s:submit>
																</td>
															  </tr>
															<tr>
															<td><s:hidden id="hid_CostCenter" name="hid_CostCenter"></s:hidden></td>
															<td><s:hidden id="hid_EstimateNo" name="hid_EstimateNo"></s:hidden></td>
															<s:hidden id="state" name="state"></s:hidden>
															<s:hidden id="region" name="region"></s:hidden>
															<!--  <td><s:hidden id="hid_loanCode" name="hid_loanCode"></s:hidden></td>
															<td><s:hidden id="hid_loanDate" name="hid_loanDate"></s:hidden></td>
															<td><s:hidden id="hid_noOfInstalment" name="hid_noOfInstalment"></s:hidden></td>
															<td><s:hidden id="hid_loanAmount" name="hid_loanAmount"></s:hidden></td>
															<td><s:hidden id="hid_instalmantAmount" name="hid_instalmantAmount"></s:hidden></td>-->
														  </tr>
														</table>
													</td>
												</tr>
												<tr>
													<td class="tdHeadings" colspan="4">&nbsp;</td>
												</tr>
												<tr>
												<td>
												<applet codebase="../../PrintLib" code="serviceMainCard.ServiceMainCardApp" name="serviceMainCardApplet"
															archive="PrintJar.jar" width="1" height="1">
												</applet> 
												</td>
												</tr>
												<tr>
													<td id="tdBotton2" colspan="3">&nbsp;</td>
												</tr>

												
												
												<tr>
													<td id="tdBotton" colspan="3">
														
													</td>
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
								<tr align="Center">
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