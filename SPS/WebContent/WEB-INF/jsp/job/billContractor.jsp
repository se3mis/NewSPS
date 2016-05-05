<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Bill Contractor</title>
<link rel="stylesheet" type="text/css" href="../../style/style.css" />
<script type="text/javascript" src="../../script/billContractor.js"></script>
<script type="text/javascript" src="../../script/javascript.js"> </script>
<s:head  />
<sx:head />
</head>
<body onload="on_load()">
<table id="tblOuter" class="tblOuter">
	<tbody>
		<tr>
			<td><s:form action="billContractor"  method="post"   theme="simple">
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
									<td class="tdLogOut"><a href="<s:url action="logout"/>">Logout</a></td>
								</tr>
							</tbody>
						</table>
						</td>
					</tr>
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
									<td><table width="100%" class="tldFromContent" border="0">
											<thead>
												<tr>
													<th></th>

												</tr>
											</thead>
											<tbody>
												<tr>
													<td class="tdHeadings" colspan="4">Bill Contractor</td>
												</tr>
												<tr>											
													<td align="left"><s:label name="statusMsg" id="statusMsg" cssClass="STATUSMSG"></s:label></td>	
												</tr>															
												<tr>																													
													<td align="center"><s:label name="statusMsgErr" id="statusMsgErr" cssClass="STATUSMSGERR"></s:label></td>
												</tr>
												
												<tr>
													<td>
													<table width="100%" class="tldFromContent" border="0" >
														<thead>
															<tr>
																<th width="3%"></th>
																<th width="20%"></th>
																<th width="10%"></th>
																<th width="7%"></th>
																<th width="5%"></th>
																<th width="7%"></th>
															</tr>
														</thead>
														<tbody>
															<tr>
															<td>&nbsp;</td>
															</tr>	
															<tr>
																<td class="tdHeadings" colspan="4">Job Details</td>
															</tr>
																<tr>
																<td></td>
																<td><s:label value="Job Number" ></s:label></td>
																<td> 
																	<s:select name="jobNumber" 
																			list="listjobNumber" 
																			id="listjobNumber"																			
																			onchange="setJobDetails()"
																			headerKey="-1"												
																			headerValue="-- Please Select --">																			
    																</s:select>
																</td>
																														
																<td></td>																
															</tr>														
															<tr>
																<td></td>
																<td><s:label value="Bill Date (DD/MM/YY)" ></s:label></td>
																<td><sx:datetimepicker name="billDate" id="datepicker"  displayFormat="dd/MM/yyyy" valueNotifyTopics="/value" disabled="true"  /></td>	
																<td></td>																
															</tr>
															<tr>
																<td></td>
																<td><s:label value="Consumer Name" ></s:label></td>
																<td><s:textfield name="consumerName" id="name" size="20" readonly="true"></s:textfield></td>	
																<td></td>																
															</tr>
															<tr>	
																<td></td>															
																<td><s:label value="Contractor" ></s:label></td>
																<td> 
																	<s:select 
																			name="contractor" 
																			list="listContractor" 
																			id="listContractor" 
																			headerKey="-1"	
																			onchange="setContractorDetails()"																		
																			headerValue="-- Please Select --">
    																</s:select>
																</td>	
																<td></td>
																<td > 
																<s:select style="visibility: hidden;"
																			name="contractorID" 
																			list="listContractorID" 
																			id="listContractorID" 
																			headerKey="-1"												
																			headerValue="-- Please Select --">
    															</s:select>
															</td>														
															</tr>
															<tr>
																<td></td>
																<td><s:label value="Bill Reference No" ></s:label></td>
																<td><s:textfield name="billReferenceNo" id="billReferenceNo" size="20" ></s:textfield></td>	
																<td></td>																
															</tr>														
															<tr>
																<td></td>
																<td><s:label value="Estimated by" ></s:label></td>
																<td><s:textfield name="estimatedBy" id="estimatedBy"  size="20" readonly="true"></s:textfield></td>	
																<td></td>																
															</tr>									
														</tbody>

													</table>
													</td>													
												</tr>
												<tr>
												<td>
												<table width="100%" id="tblBillDetails" class="tldFromContent" border="0" >
													
														<tbody>	
																<tr>
																	<td>&nbsp;</td>																																	
																</tr>	
																	
											<tr>
											<td>
											<table width="100%" border="0">
											<tr>
												<td class="tdHeadings">Bill Details - Labour Activities</td>
											</tr>
											<tr>
												<td>&nbsp;</td>
											</tr>
											<tr>
												<td>
												<table class="tldFromContent" border="1" width="95%">

													<tr bgcolor="#93C9FF">
														<td><strong>Activity Description</strong></td>
														<td><strong>Unit Price</strong></td>
														<td><strong>Quantity</strong></td>
														<td><strong>Labor Cost</strong></td>														
													</tr>
													<s:iterator value="billDetails">
														<tr bgcolor="#CCCCCC">
															<td align="left"><s:property value="activityDescription" /></td>
															<td align="left"><s:property value="unitPrice" /></td>
															<td align="left"><s:property value="itemQty" /></td>
															<td align="left"><s:property value="labourCost" /></td> 
														</tr>
													</s:iterator>
												</table>
												</td>
												</tr>
												
												<tr>
													<td>&nbsp;</td>
												</tr>
												</table>
													</td>
											</tr>
												<tr>
												<td><s:label value="Grand Total" ></s:label>&nbsp;&nbsp;&nbsp;<s:textfield name="grandTotal" id="grandTotal" readonly="true" /></td>
												<td></td>
												</tr>
												<tr>
													<td id="tdBotton" colspan="3">
													<s:submit theme="simple"  value="Bill" method="bill"  onclick="return saveBillValues()" id="btnBill" disabled="true"></s:submit>
<!--													<s:reset theme="simple" align="right" value="Clear" id="btnReset" onclick="clearTable()"/>																							-->
													<s:submit theme="simple" align="right" value="Exit" method="close" id="btnClose" ></s:submit>
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
					<tr>
					<td>
			</td>
		</tr>
		</tbody>
		</table>
		</s:form></td>
	</tr>
	</tbody>
</table>

</body>
</html>