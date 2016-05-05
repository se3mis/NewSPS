<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Job Energize</title>
<link rel="stylesheet" type="text/css" href="../../style/style.css" />
<script type="text/javascript" src="../../script/jobEnergize.js"></script>
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
						<td id="tdContent"><s:form action="OrderCard"  method="post"   theme="simple">
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
																<th width="3%"></th>
																<th width="10%"></th>
																<th width="15%"></th>
																<th width="10%"></th>
																<th width="15%"></th>
																<th width="10%"></th>
																<th width="*"></th>														
															</tr>
														</thead>
														<tbody>
															<tr>
															<td>&nbsp;</td>
															</tr>
															
															<tr>
																<td></td>
																<td><s:label value="Cost Center" ></s:label></td>
																<td> 
																	<s:textfield name="costCenterNo" readonly="true"  size="20" ></s:textfield>
																</td>
																
																<td></td>																
															</tr>
															<tr>
																<td>&nbsp;</td>															
															</tr>
															<tr>
																<td></td>
																<td><s:label value="Job No" ></s:label></td>
																<td><s:select name="jobNo" 
																			list="listjobNo" 
																			id="listjobNo" 
																			onchange="setContractorId()"
																			headerKey="-1"
																			headerValue="-- Please Select --">
    																</s:select>
    															</td>
    															<td><s:label value="Energize Date" ></s:label></td>
																<td><sx:datetimepicker name="energizeDate" id="datepicker" displayFormat="dd/MM/yyyy"  required="true"  /></td>													
																															
															</tr>
															<tr>	
																<td></td>
																<td></td>	
																<td></td>
																<td></td>														
																<td><s:label value="MM/DD/YY"  cssClass="tlddateIndicator"></s:label></td>		
															</tr>
															<tr>
																<td>&nbsp;</td>															
															</tr>
															<tr>
																<td></td>
																<td><s:label value="Phase" ></s:label></td>
																<td><s:select name="phase" 
																			list="listPhase" 
																			id="listphase" 
																			headerKey="-1"
																			disabled="true"
																			onchange="setSealNos()"
																			headerValue="-- Please Select --">
    																</s:select>
    															</td>
    															<td style="visibility: hidden;"><s:select name="contractorId" 
																			list="listcontractorId" 
																			id="listcontractorId" 
																			headerKey="-1"
																			headerValue="-- Please Select --">
    																</s:select>    															
    															</td>
																<td></td>													
																															
															</tr>	
															<tr>
																<td>&nbsp;</td>															
															</tr>
															<tr>
																<td></td>
																<td></td>
																<td class="tdHeadings">SEAL NUMBER DETAILS</td>
															</tr>
															<tr>
																<td>&nbsp;</td>															
															</tr>
															<tr id="trPhase1" style="visibility:hidden;">
																<td></td>
																<td><s:label value="Seal Number1" ></s:label></td>
																<td><s:textfield id="txtdSealNumber1" name="SealNumber1" ></s:textfield></td>
																<td><s:label value="Seal Number2" ></s:label></td>
																<td><s:textfield id="txtdSealNumber2" name="SealNumber2" ></s:textfield></td>
																<td><s:label value="Seal Number3" ></s:label></td>
																<td><s:textfield id="txtdSealNumber3" name="SealNumber3" ></s:textfield></td>
																<td></td>																
															</tr>
															<tr>
																<td>&nbsp;</td>															
															</tr>
															<tr id="trPhase2" style="visibility:hidden;">
																<td></td>
																<td><s:label value="Seal Number4" ></s:label></td>
																<td><s:textfield id="txtdSealNumber4" name="SealNumber4" ></s:textfield></td>
																<td><s:label value="Seal Number5" ></s:label></td>
																<td><s:textfield id="txtdSealNumber5" name="SealNumber5" ></s:textfield></td>
																<td><s:label value="Seal Number6" ></s:label></td>
																<td><s:textfield id="txtdSealNumber6" name="SealNumber6" ></s:textfield></td>
																<td></td>																
															</tr>
															<tr>
																<td>&nbsp;</td>															
															</tr>
															<tr id="trPhase3" style="visibility:hidden;">
																<td></td>
																<td><s:label value="Seal Number7" ></s:label></td>
																<td><s:textfield id="txtdSealNumber7" name="SealNumber7" ></s:textfield></td>
																<td><s:label value="Seal Number8" ></s:label></td>
																<td><s:textfield id="txtdSealNumber8" name="SealNumber8" ></s:textfield></td>
																<td><s:label value="Seal Number9" ></s:label></td>
																<td><s:textfield id="txtdSealNumber9" name="SealNumber9" ></s:textfield></td>
																<td></td>																
															</tr>
															<tr>
																<td>&nbsp;</td>															
															</tr>
														
															<tr>	
																<td></td>
																<td></td>															
																<td><s:label name="statusMsg" id="statusMsg" cssClass="STATUSMSG"></s:label></td>
															</tr>
															<tr>	
																<td></td>
																<td></td>
																<td><s:label name="statusMsgErr" id="statusMsgErr" cssClass="STATUSMSGERR"></s:label></td>		
															</tr>
															<tr style="visibility:hidden;">
																<td></td>
																<td ><s:label value="Description" ></s:label></td>
																	<td><s:textarea name="estimateCategory"  id="txfdestimateCategory" rows="4" cols="30" disabled="true"  ></s:textarea></td>
																<td></td>																
															</tr>														
														</tbody>

													</table>
													</td>
												</tr>	
															
												<tr>
													<td id="tdBotton" colspan="3">
														
														<s:submit theme="simple" value="Energize" method="energize"  id="btnenergize" onclick="return validate_form()"></s:submit>
														<s:reset theme="simple" align="right" value="Clear" id="btnReset" onclick="clearForm()"/>																							
														<s:submit theme="simple" value="Exit" method="close"  id="btnClose" ></s:submit>																											
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
			</td>
		</tr>
	</tbody>
</table>
</body>
</html>