<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Permission and D-notice</title>
<link rel="stylesheet" type="text/css" href="../../style/style.css" />
<script type="text/javascript" src="../../script/permissionDenotice.js"></script>
<script type="text/javascript" src="../../script/javascript.js"></script>
<s:head  />
<sx:head />
</head>
<body onload="setFormCtrls()">
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
						<td id="tdContent"><s:form action="permissionDenoticeNew"  method="post"   theme="simple">
						<table class="tblContent" border="1" cellpadding="0" cellspacing="0">
							<thead>
								<tr>								
									
								</tr>
							</thead>
							<tbody>
								    <tr align="center">															
										<td><s:label name="statusMsg" id="statusMsg" cssClass="STATUSMSG"></s:label></td>
									</tr>
									<tr align="center">
										<td><s:label name="statusMsgErr" id="statusMsgErr" cssClass="STATUSMSGERR"></s:label></td>		
									</tr>
							</tbody>
							<tbody>
								<tr>
									<td>
											<table width="100%" class="tldFromContent" border="0">
														
														<thead>
															<tr>
																<th width="3%"></th>																
																<th width="15%"></th>
																<th width="3%"></th>	
																<th width="15%"></th>																
																<th width="10%"></th>
																<th width="3%"></th>
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
																<td><s:label value="Estimate No" ></s:label></td>
																<td></td>
																<td><s:textfield id="txtdestimateNo" name="estimateNo"   size="20" ></s:textfield></td>	
																<td><s:submit theme="simple" align="right" value="Find" disabled="true" method="viewDeNoticePermissionDetails" id="btnFindApplicationNo" onclick="return checkAppNoNotNull()"></s:submit></td>															
																<td></td>																
															</tr>
															<tr>
																<td>&nbsp;</td>															
															</tr>	
															<tr>
																<td></td>																
																<td class="tdHeadings" >D-Notice</td>																
															</tr>
															<tr>
																<td>&nbsp;</td>															
															</tr>
															<tr>
																<td></td>																
																<td><s:label value="D-Notice" ></s:label></td>
																<td><s:checkbox  name="chkdeNotice" id="chkdeNotice" onclick="setIssueClearDates(this)" ></s:checkbox></td>			
															</tr>
															<tr>
																<td>&nbsp;</td>															
															</tr>
															<tr>
																<td></td>
																<td></td>
																<td></td>
																<td><s:label id="lbldDenoticeIssuedDate" value="Issued Date" ></s:label></td>
																<td> 
																	<sx:datetimepicker name="denoticeIssuedDate" id="txtdDenoticeIssuedDate" displayFormat="dd/MM/yyyy"  required="true"  />																	
																</td>	
																<td></td>															
																<td><s:label id="lbldDenoticeClearedDate"  value="Cleared Date" ></s:label></td>
																<td>
																	<sx:datetimepicker name="denoticeClearedDate" id="txtDenoticeClearedDate" displayFormat="dd/MM/yyyy"  required="true"  />																
																</td>																
															</tr>
															<tr>
																<td>&nbsp;</td>															
															</tr>	
															<tr>
																<td></td>																
																<td class="tdHeadings" >Permission</td>																
															</tr>
															<tr>
																<td>&nbsp;</td>															
															</tr>
															<tr>
																<td></td>																
																<td><s:label value="RDA" ></s:label></td>			
																<td><s:checkbox  name="chkRDA" id="chkdRDA" onclick="setIssueClearDates(this)" ></s:checkbox></td>
																<td><s:label id="lbldRDAIssuedDate" value="Issued Date" ></s:label></td>
																<td>
																	<sx:datetimepicker name="issuedDateRDA" id="txtdIssuedDateRDA" displayFormat="dd/MM/yyyy"  required="true"  />
																	
																</td>	
																<td></td>															
																<td><s:label id="lbldRDAClearedDate" value="Cleared Date" ></s:label></td>
																<td>
																	<sx:datetimepicker name="clearedDateRDA" id="txtClearedDateRDA" displayFormat="dd/MM/yyyy"  required="true"  />	
																</td>																											
															</tr>
															<tr>
																<td></td>																
																<td><s:label value="PSD" ></s:label></td>	
																<td><s:checkbox  name="chkPSD" id="chkPSD" onclick="setIssueClearDates(this)" ></s:checkbox></td>
																<td><s:label id="lbldPSDIssuedDate" value="Issued Date" ></s:label></td>
																<td> 
																	<sx:datetimepicker name="issuedDatePSD" id="txtdIssuedDatePSD" displayFormat="dd/MM/yyyy"  required="true"  />	
																</td>	
																<td></td>															
																<td><s:label id="lbldPSDClearedDate" value="Cleared Date" ></s:label></td>
																<td>
																	<sx:datetimepicker name="clearedDatePSD" id="txtClearedDatePSD" displayFormat="dd/MM/yyyy"  required="true"  />
																</td>																											
																		
															</tr>
															<tr>
																<td></td>																
																<td><s:label value="Municipal" ></s:label></td>
																<td><s:checkbox  name="chkMuncipal" id="chkMuncipal" onclick="setIssueClearDates(this)" ></s:checkbox></td>
																<td><s:label id="lbldMuncipalIssuedDate" value="Issued Date" ></s:label></td>
																<td> 
																	<sx:datetimepicker name="issuedDateMuncipal" id="txtdIssuedDateMuncipal" displayFormat="dd/MM/yyyy"  required="true"  />
																</td>	
																<td></td>															
																<td><s:label id="lbldMuncipalClearedDate" value="Cleared Date" ></s:label></td>
																<td>
																	<sx:datetimepicker name="clearedDateMuncipal" id="txtClearedDateMuncipal" displayFormat="dd/MM/yyyy"  required="true"  />
																</td>																											
																
															</tr>
															<tr>
																<td></td>																
																<td><s:label value="Police" ></s:label></td>
																<td><s:checkbox  name="chkPolice" id="chkPolice" onclick="setIssueClearDates(this)" ></s:checkbox></td>
																<td><s:label id="lbldPoliceIssuedDate" value="Issued Date" ></s:label></td>
																<td> 
																	<sx:datetimepicker name="issuedDatePolice" id="txtdIssuedDatePolice" displayFormat="dd/MM/yyyy"  required="true"  />
																</td>	
																<td></td>															
																<td><s:label id="lbldPoliceClearedDate" value="Cleared Date" ></s:label></td>
																<td>
																	<sx:datetimepicker name="clearedDatePolice" id="txtClearedDatePolice" displayFormat="dd/MM/yyyy"  required="true"  />																
																</td>																										
															</tr>															
																																										
														</tbody>
													</table>
													</td>
												</tr>		
												<tr>
													<td id="tdBotton" colspan="3">														
														<s:submit theme="simple" value="Save" method="savePermission"  id="btnsave" onclick="return validate_form()"></s:submit>														
														<s:submit theme="simple" align="right" value="Clear" id="btnReset" onclick="return reSetForm()"></s:submit>	
														<s:submit theme="simple" value="Exit" method="close"  id="btnClose" ></s:submit>																											
													</td>
									
												</tr> 
												<tr>
													<td>	
														<s:hidden id="hid_isFind" name="hid_isFind"></s:hidden>														
														<s:hidden id="hid_DnoticeChk" name="hid_DnoticeChk"></s:hidden>	
														<s:hidden id="hid_RDAChk" name="hid_RDAChk"></s:hidden>
														<s:hidden id="hid_PSDChk" name="hid_PSDChk"></s:hidden>
														<s:hidden id="hid_MuncipalChk" name="hid_MuncipalChk"></s:hidden>
														<s:hidden id="hid_PoliceChk" name="hid_PoliceChk"></s:hidden>
														
														<s:hidden id="found_hid_DnoticeChk" name="found_hid_DnoticeChk"></s:hidden>	
														<s:hidden id="found_hid_RDAChk" name="found_hid_RDAChk"></s:hidden>
														<s:hidden id="found_hid_PSDChk" name="found_hid_PSDChk"></s:hidden>
														<s:hidden id="found_hid_MuncipalChk" name="found_hid_MuncipalChk"></s:hidden>
														<s:hidden id="found_hid_PoliceChk" name="found_hid_PoliceChk"></s:hidden>	
														<s:hidden id="hid_isNew" name="hid_isNew"></s:hidden>	
																							
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