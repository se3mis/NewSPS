<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Seal Number</title>
<link rel="stylesheet" type="text/css" href="../../style/style.css" />
<script type="text/javascript" src="../../script/sealNumber.js"></script>
<s:head  />
<sx:head />
</head>
<body onload="on_Load()">
				<table class="tblContent" border="1" cellpadding="0"
							cellspacing="0">
							<thead>
								<tr>
									<th></th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td><s:form action="enterSealed" method="POST" theme="simple">
										<table width="100%" class="tldFromContent" border="0">
											
											<tbody>												
												<tr>
													<td>
													<table width="100%" height="50%" class="tldFromContent" border="0"  >
														
														<tbody>
															<tr>
																<td>	
																	<div class="listDivStyleBig" >
																		<table width="100%" border="0" align="left" bgcolor="#FBFFFF" border="1" style="border-color:#000033;border-collapse:collapse"  >
																			<thead>
																				<tr>
																					<th width="10%"></th>
																					<th width="15%"></th>
																					<th width="10%"></th>
																					<th width="5%"></th>
																					<th width="5%"></th>
																									
																				</tr>
																			</thead>
																			     	 <tr>
																					 <td align="left" class="listHeader">Seal Number</td>
																					 <td align="left"><s:label name="statusMsg" id="statusMsg" cssClass="STATUSMSG" ></s:label></td>
																					</tr>
																						<tr><td>&nbsp;</td></tr>																					
																					<tr>																						
																						<td align="left"><s:label value="Seal Number" ></s:label></td>
																						<td align="left"> 
																						<s:textfield name="sealNum" id="sealNum"  ></s:textfield>
																						</td>		
																					</tr>	
																					<tr><td>&nbsp;</td></tr>																						
																					
																					<tr>
																						<td>&nbsp;</td>
																					</tr>
																					<tr>
																						<td id="tdBotton" colspan="3">
																							<s:submit theme="simple"  value="OK"   onclick="return setSealNumber()" id="btnOk" ></s:submit>
																							<s:submit theme="simple"  value="Cancel"  onclick="return closeWindow()" id="btnCancel" ></s:submit>
																						</td>
																					</tr>
																		</table>
																		
																	</div>														
																
																</td>																
															</tr>
														
										   	
														</tbody>
													</table>														
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
</body>
</html>