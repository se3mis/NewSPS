<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Delete Estimate</title>
<link rel="stylesheet" type="text/css" href="../../style/style.css" />
<script type="text/javascript" src="../../script/deleteJob.js"></script>
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
									<td><s:form action="" method="POST"   theme="simple">
													<table width="100%" class="tldFromContent" border="0">
											<thead>
												<tr>
													<th></th>

												</tr>
											</thead>
											<tbody>
												<tr>
													<td class="tdHeadings" colspan="4">Estimate Delete</td>
												</tr>
												<tr>
													<td></td>
												</tr>
												<tr>
													<td></td>
												</tr>
												
												<tr>
													<td>
													<table width="100%" class="tldFromContent" border="0" >
														<thead>
															<tr>
																<th width="5%"></th>
																<th width="15%"></th>
																<th width="10%"></th>
																<th width="35%"></th>
																<th width="*"></th>
																<th width="5%"></th>
															</tr>
														</thead>
														<tbody>
															<tr>
															<td>&nbsp;</td>
															</tr>
															<tr>
																<td></td>
																<td><s:label value="Reference No" ></s:label></td>
																<td> 
																	<s:select name="referenceNo" 
																			list="listreferenceNo" 
																			id="listreferenceNo" 
																			headerKey="-1"
																			onchange="setDetails()"
																			headerValue="-- Please Select --">
    																</s:select>
																</td>
																<td Style="visibility: hidden;"> 
																<s:select name="selectedReference" 
																			list="listSelectedReference" 
																			id="listReference" 
																			headerKey="-1"
																			onchange=""
																			headerValue="-- Please Select --">
    																</s:select>
																</td>
																<td></td>																
															</tr>
																<tr><td>&nbsp;</td></tr>
															<tr>
																<td></td>
																<td><s:label value="Cost Center" ></s:label></td>
																<td><s:textfield name="costCentre" id="txfdcostCentre" size="20" disabled="true" ></s:textfield></td>																
																<td></td>																
															</tr>
																<tr><td>&nbsp;</td></tr>
															<tr>
																<td></td>
																<td><s:label value="Estimate Date" ></s:label></td>
																<td><s:textfield name="estimateDate" id="txfdestimateDate" size="20" disabled="true"></s:textfield></td>	
																<td></td>																
															</tr>
																<tr><td>&nbsp;</td></tr>
															<tr>
																<td></td>
																<td><s:label value="Estimate Category" ></s:label></td>
																<td><s:textfield name="estimateCategory" id="txfdestimateCategory" size="20" disabled="true"></s:textfield></td>	
																<td></td>																
															</tr>	
															<tr><td>&nbsp;</td></tr>
				                                             <tr align="center">
				                                             	<td></td>
				                                             	<td></td>
				                                             	<td></td>
				                                                <td><s:label name="statusMsg" id="statusMsg" cssClass="STATUSMSG" ></s:label></td>
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
													<td id="tdBotton" colspan="3">
													 	<s:submit theme="simple"  value="Delete" method="delete"  onclick="return validate_form()" ></s:submit>
														<s:reset theme="simple" align="right" value="Clear" id="btnReset" />
														<s:submit theme="simple" align="right" value="Exit" method="Close" id="btnClose" ></s:submit>
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