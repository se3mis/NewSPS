<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Temporary Job Energize</title>
<link rel="stylesheet" type="text/css" href="../../style/style.css" />
<script type="text/javascript" src="../../script/tCEnergize.js"></script>
<script type="text/javascript" src="../../script/javascript.js"></script>
<s:head  />
<sx:head />
</head>
<body onload="on_Load()">
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
									<td class="tdLogOut"><a href="<s:url namespace="/estimate/web" action="logout"/>">Logout</a></td>
								</tr>
							</tbody>
						</table>
						</td>
					</tr>
					<tr>
						<td id="tdContent"><s:form action="tCEnergize"  method="post"   theme="simple">
						<table class="tblContent" border="1" cellpadding="0" cellspacing="0">
							<thead>
								<tr>
									<th></th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>
													<table width="100%" class="tldFromContent" border="0">
														
														<thead>
															<tr>
																<th width="2%"></th>																
																<th width="13%"></th>																																<th width="15%"></th>																
																<th width="10%"></th>
																<th width="1%"></th>
																<th width="13%"></th>
																<th width="10%"></th>
																<th width="1%"></th>
																<th width="15%"></th>
																<th width="10%"></th>													
															</tr>
														</thead>
														<tbody>
															<tr>
																<td>&nbsp;</td>
															</tr>												
															<tr>
																<td></td>																
																<td><s:label value="Job No" ></s:label></td>
																<td>
																	<s:select name="jobNo" 
																			list="listjobNo" 
																			id="listjobNo" 																			
																			headerKey="-1"
																			onchange="setDetails()"
																			headerValue="-- Please Select --">
    																</s:select>
    															</td>
																<td></td>	
																<td><s:label value="Date" ></s:label></td>															
																<td><sx:datetimepicker disabled="true" name="applicationDate" id="txtdapplicationDate" displayFormat="dd/MM/yyyy"  required="true"  /></td>													
															</tr>
															<tr>
																<td>&nbsp;</td>															
															</tr>	
															<tr>
																<td></td>																
																<td><s:label value="Address of service required" ></s:label></td>
																<td><s:textarea id="txtdserviceAddress" name="serviceAddress"   rows="2" cols="20" readonly="true"></s:textarea></td>
																<td></td>																															
															</tr>
															<tr>
																<td>&nbsp;</td>															
															</tr>
															<tr>
																<td></td>																
																<td><s:label value="Application send date" ></s:label></td>
																<td><sx:datetimepicker  disabled="true" name="applicationsendDate" id="txtdapplicationsendDate" displayFormat="dd/MM/yyyy"  required="true"  /></td>
																<td></td>																															
															</tr>	
															<tr>
																<td>&nbsp;</td>															
															</tr>	
															<tr>
																<td></td>																
																<td><s:label value="Application received date from R.E.E Office" ></s:label></td>
																<td><sx:datetimepicker disabled="true" name="applicationreceivedDate" id="txtdreceivedDate" displayFormat="dd/MM/yyyy"  required="true"  /></td>
																<td></td>																															
															</tr>														
															<tr>
																<td>&nbsp;</td>															
															</tr>	
															<tr>
																<td></td>																
																<td><s:label value="Required service duration" ></s:label></td>
																<td><sx:datetimepicker disabled="true" name="servicerequiredFromDate" id="txtdservicerequiredFromDate" displayFormat="dd/MM/yyyy"  required="true"  /></td>
																<td></td>	
																<td><sx:datetimepicker  disabled="true" name="servicerequiredToDate" id="txtdservicerequiredToDate" displayFormat="dd/MM/yyyy"  required="true"  /></td>																														
															</tr>
																<tr>
																<td></td>																
																<td></td>
																<td><s:label value="From" ></s:label></td>
																<td></td>	
																<td><s:label value="To" ></s:label></td>																														
															</tr>
															<tr>
																<td>&nbsp;</td>															
															</tr>	
															<tr>
																<td></td>																
																<td><s:label value="Estimate amount" ></s:label></td>
																<td><s:textfield id="txtdestimateAmt" readonly="true" name="estimateAmt"   size="20" ></s:textfield></td>
																<td></td>
																<td><s:label value="Damage amount" ></s:label></td>
																<td><s:textfield id="txtddamageAmt" readonly="true" name="damageAmt"   size="20" ></s:textfield></td>
																																	
															</tr>
																<tr>
																<td>&nbsp;</td>															
															</tr>	
															<tr>
																<td></td>																
																<td><s:label value="Security Deposit" ></s:label></td>															
																<td><s:textfield readonly="true"  id="txtdsecdepositAmt" name="secdepositAmt"   size="20" ></s:textfield></td>																
																<td></td>	
																<td><s:label value="Additional Deposit" ></s:label></td>															
																<td><s:textfield readonly="true" id="txtdadddepositAmt" name="adddepositAmt"   size="20" ></s:textfield></td>																
															</tr>
															<tr>
																<td>&nbsp;</td>															
															</tr>
															<tr>
																<td></td>																
																<td><s:label value="PIV No" ></s:label></td>
																<td><s:textfield readonly="true" id="txtdpayinVouchrNo" name="payinVouchrNo"   size="20" ></s:textfield></td>
																<td></td>	
																<td><s:label value="PIV Date" ></s:label></td>	
																<td><sx:datetimepicker disabled="true"  name="payinVouchrDate" id="txtpayinVouchrDate" displayFormat="dd/MM/yyyy"  required="true"  /></td>																														
															</tr>	
															<tr>
																<td>&nbsp;</td>															
															</tr>	
															<tr>
																<td></td>																
																<td><s:label value="Service Connected Date" ></s:label></td>
																<td><sx:datetimepicker disabled="true"  name="servicegivenDate" id="txtdservicegivenDate" displayFormat="dd/MM/yyyy"  required="true"  /></td>
																<td></td>	
																<td><s:label value="Line's man Name" ></s:label></td>	
																<td><s:textfield readonly="true" id="txtdgivenlinemanName" name="givenlinemanName"   size="20" ></s:textfield></td>																														
															</tr>	
															<tr>
																<td>&nbsp;</td>															
															</tr>	
															<tr>
																<td></td>																
																<td><s:label value="Material Notice No" ></s:label></td>
																<td><s:textfield readonly="true" id="txtdmatNoticeNo" name="matNoticeNo"   size="20" ></s:textfield></td>
																<td></td>	
																<td><s:label value="Date" ></s:label></td>	
																<td><sx:datetimepicker disabled="true"  name="matNoticeDate" id="txtdmatNoticeDate" displayFormat="dd/MM/yyyy"  required="true"  /></td>
																<td></td>																
																<td><s:label value="Phase" ></s:label></td>
																<td><s:textfield readonly="true" id="txtdPhase" name="phase"  size="20" ></s:textfield></td>
																																														
															</tr>	
															<tr>
																<td>&nbsp;</td>															
															</tr>	
															<tr>
																<td></td>																
																<td><s:label id ="lblmeterNumber1" value="Meter Number1" ></s:label></td>
																<td><s:textfield readonly="true" onkeyup="numericValidate(this,event)" id="txtdmeterNumber1" name="meterNumber1"   size="20" ></s:textfield></td>
																<td></td>																
																<td><s:label style="visibility:hidden;" id ="lblmeterNumber2" value="Number2" ></s:label></td>
																<td><s:textfield style="visibility:hidden;" readonly="true" onkeyup="numericValidate(this,event)" id="txtdmeterNumber2" name="meterNumber2"   size="20" ></s:textfield></td>
																<td></td>																
																<td><s:label style="visibility:hidden;" id ="lblmeterNumber3" value="Number3" ></s:label></td>
																<td><s:textfield style="visibility:hidden;" readonly="true" onkeyup="numericValidate(this,event)" id="txtdmeterNumber3" name="meterNumber3"   size="20" ></s:textfield></td>
																							</tr>
															<tr>
																<td>&nbsp;</td>															
															</tr>
															<tr>
																<td></td>	
																<td><s:label id ="lblinitialReading1" value="Initial Reading1" ></s:label></td>															
																<td><s:textfield onkeyup="numericValidate(this,event)" readonly="true" id="txtdinitialReading1" name="initialReading1"   size="20" ></s:textfield></td>
																<td></td>	
																<td><s:label style="visibility:hidden;" id ="lblinitialReading2" value="Reading2" ></s:label></td>															
																<td><s:textfield style="visibility:hidden;" readonly="true" onkeyup="numericValidate(this,event)"  id="txtdinitialReading2" name="initialReading2"   size="20" ></s:textfield></td>
																<td></td>	
																<td><s:label style="visibility:hidden;" id ="lblinitialReading3" value="Reading3" ></s:label></td>															
																<td><s:textfield style="visibility:hidden;" readonly="true" onkeyup="numericValidate(this,event)"  id="txtdinitialReading3" name="initialReading3"   size="20" ></s:textfield></td>												
																						
															</tr>	
																<tr>
																<td>&nbsp;</td>															
															</tr>
															<tr>
																<td></td>																
																<td><s:label id ="lblfinalReading1" value="Final Reading1" ></s:label></td>
																<td><s:textfield onkeyup="numericValidate(this,event)" readonly="true" id="txtdfinalmeterReading1"  name="finalmeterReading1"  size="20" onblur="setUnits(this)"></s:textfield></td>
																<td></td>																
																<td><s:label style="visibility:hidden;"  id ="lblfinalReading2" value="Reading2" ></s:label></td>
																<td><s:textfield style="visibility:hidden;"  onkeyup="numericValidate(this,event)" id="txtdfinalmeterReading2" name="finalmeterReading2" size="20" onblur="setUnits(this)"></s:textfield></td>
																<td></td>																
																<td><s:label style="visibility:hidden;" id ="lblfinalReading3" value="Reading3" ></s:label></td>
																<td><s:textfield style="visibility:hidden;"  onkeyup="numericValidate(this,event)"  id="txtdfinalmeterReading3" name="finalmeterReading3" size="20" onblur="setUnits(this)"></s:textfield></td>																																			
															</tr>
															<tr>
																<td>&nbsp;</td>															
															</tr>
															<tr>
																<td></td>																
																<td><s:label value="Service Disconnected Date" ></s:label></td>
																<td><sx:datetimepicker disabled="true" name="servicediscntDate" id="txtdservicediscntDate" displayFormat="dd/MM/yyyy"  required="true"  /></td>
																<td></td>	
																<td><s:label value="Line's man Name" ></s:label></td>	
																<td><s:textfield readonly="true" id="txtddiscntlinemanName" name="discntlinemanName"   size="20" ></s:textfield></td>																														
															</tr>	
															<tr>
																<td>&nbsp;</td>															
															</tr>	
															<tr>
																<td></td>	
																<td><s:label value="Units Used" ></s:label></td>															
																<td><s:textfield readonly="true" id="txtdunitsUsed" name="unitsUsed"   size="20" ></s:textfield></td>																
															</tr>	
															<tr>
																<td>&nbsp;</td>															
															</tr>	
															<tr>
																<td></td>																
																<td><s:label value="Charged amount" ></s:label></td>
																<td><s:textfield readonly="true" id="txtdchargedAmt" name="chargedAmt"   size="20"  onkeyup="numericValidate(this,event)" onblur="formatValue(this);setBalanceAmt(this)" ></s:textfield></td>
																<td></td>	
																<td><s:label value="Balance amount" ></s:label></td>
																<td><s:textfield readonly="true" id="txtdbalanceAmt" name="balanceAmt"   size="20" ></s:textfield></td>
																																											
															</tr>	
															<tr>
																<td>&nbsp;</td>															
															</tr>
															<tr>
																<td></td>																
																<td><s:label value="Date send to R.E.E Office" ></s:label></td>
																<td><sx:datetimepicker disabled="true" name="applicationsendtoREDate" id="txtdapplicationsendtoREDate" displayFormat="dd/MM/yyyy"  required="true"  /></td>
																<td></td>	
															</tr>
															<tr>
																<td>&nbsp;</td>															
															</tr>	
															<tr>
																<td></td>																
																<td><s:label value="Signature ES" ></s:label></td>
																<td><s:textfield readonly="true" id="txtdsignatureES" name="signatureES"   size="20" ></s:textfield></td>
																<td></td>	
																<td><s:label value="Date" ></s:label></td>	
																<td><sx:datetimepicker disabled="true" name="signatureESDate" id="txtdsignatureESDate" displayFormat="dd/MM/yyyy"  required="true"  /></td>																														
															</tr>															
															<tr>	
																<td></td>
																<td></td>
																<td></td>															
																<td><s:label name="statusMsg" id="statusMsg" cssClass="STATUSMSG"></s:label></td>
															</tr>
															<tr>	
																<td></td>
																<td></td>
																<td></td>
																<td><s:label name="statusMsgErr" id="statusMsgErr" cssClass="STATUSMSGERR"></s:label></td>		
															</tr>																											
														</tbody>
													</table>
													</td>
												</tr>		
												<tr>
													<td id="tdBotton" colspan="3">														
														<s:submit theme="simple" value="Energize" method="energizeTCJob"  id="btnEnergize" onclick="return validate_form()"></s:submit>
<!--														<s:reset theme="simple" align="right" value="Clear" id="btnReset" />	-->
														<s:submit theme="simple" value="Print" method="print"  id="btnPrint" ></s:submit>																						
														<s:submit theme="simple" value="Exit" method="close"  id="btnClose" ></s:submit>																											
													</td>
									
												</tr> 	
												<tr>
													<td>	
														<s:hidden id="hid_isFind" name="hid_isFind"></s:hidden>		
														<s:hidden id="hid_contractorId" name="hid_contractorId"></s:hidden>													
																										
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
</body>
</html>