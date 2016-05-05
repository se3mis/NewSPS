<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Hard Job Close</title>
<link rel="stylesheet" type="text/css" href="../../style/style.css" />
<script type="text/javascript" src="../../script/HardJobClose.js"></script>
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
						<td id="tdContent"><s:form action="HardJobCloseBS"  method="post"   theme="simple">
						<table class="tblContent" border="1" cellpadding="0"
							cellspacing="0">
							<s:textfield name="closingDate" id="txtdclosingDate"  size="20" style="display:none" ></s:textfield>
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
																<th width="5%"></th>
																<th width="20%"></th>
																<th width="10%"></th>	
																<th width="10%"></th>
																<th width="10%"></th>
																<th width="10%"></th>																
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
																	<s:textfield name="costCenter" readonly="true"  size="20" ></s:textfield>
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
																			onchange="setJobNos()"
																			headerKey="-1"
																			headerValue="-- Please Select --">
    																</s:select>
    															</td>    																												
																<td><s:label value="Closing Date" ></s:label></td>
																<td><sx:datetimepicker name="appointmentDate" id="datepicker" displayFormat="dd/MM/yyyy"  required="true"  />
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
																<td></td>															
																<td><s:label name="statusMsg" id="statusMsg" cssClass="STATUSMSG"></s:label></td>
															</tr>
															<tr>	
																<td></td>
																<td></td>
																<td><s:label name="statusMsgErr" id="statusMsgErr" cssClass="STATUSMSGERR"></s:label></td>		
															</tr>
															<tr>
																<td></td>
																<td></td>
																<td class="tdHeadings">JOB CARD DETAILS</td>
															</tr>
															<tr>
																<td>&nbsp;</td>															
															</tr>
															<tr>
																<td></td>
																<td><s:label value="Estimated" ></s:label></td>
																<td><s:textfield id="txtdEstimated" name="estimatedCost" readonly="true"></s:textfield></td>
																<td><s:label value="Fund" ></s:label></td>
																<td><s:textfield id="txtdFund" name="fund" readonly="true"></s:textfield></td>
																<td></td>																
															</tr>
															<tr>
																<td>&nbsp;</td>															
															</tr>
															<tr>
																<td></td>
																<td><s:label value="Committed" ></s:label></td>
																<td><s:textfield id="txtdCommitted" name="committedCost" readonly="true"></s:textfield></td>
																<td><s:label value="Job Status" ></s:label></td>
																<td><s:textfield id="txtdJobStatus" name="jobStatus" readonly="true"></s:textfield></td>
																<td></td>																
															</tr>
															<tr>
																<td>&nbsp;</td>															
															</tr>
															<tr>
																<td></td>
																<td><s:label value="Variance in" ></s:label></td>
																<td><s:textfield id="txtdVarianceIn" name="varianceIn" readonly="true"></s:textfield></td>
																<td><s:label value="Cat Code" ></s:label></td>
																<td><s:textfield id="txtdCatCode" name="catCode" readonly="true"></s:textfield></td>
																<td></td>																
															</tr>
															<tr>
																<td>&nbsp;</td>															
															</tr>
															<tr>
																<td></td>
																<td><s:label value="Variance in %" ></s:label></td>
																<td><s:textfield id="txtdVarianceInPercent" name="varianceInPercent" readonly="true"></s:textfield></td>
																<td></td>
																<td></td>																
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
														<s:submit theme="simple" value="Hard Job Close" method="hardJobclose"  id="btnhardjobclose" onclick="return validate_form()"></s:submit>
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
	
</body>
</html>