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


<s:head />
</head>
<body>
&nbsp;&nbsp;
																
												
									<table width="100%" border="0">
										<tr>
									<td>
									
									<s:if test="tobeValidateList != NULL">
										
									   
										<table width="100%" border="0">
											<tr>
											<s:if test="%{loggedInUserLevel=='ES' || loggedInUserLevel=='EE'  || loggedInUserLevel=='PE' || loggedInUserLevel=='ADMIN' || loggedInUserLevel=='TECH'}">
												<td class="tblMenuHeading">Standard Estimates to be Validated</td>
											</s:if>
											<s:if test="%{loggedInUserLevel=='CE'  || loggedInUserLevel=='DGM' || loggedInUserLevel=='ADMIN' || loggedInUserLevel=='TECH'}">
												<td class="tblMenuHeading">Standard Estimates to be Approved</td>
											</s:if>
											</tr>
											<tr>
												<td>
												<table class="tldFromContent" border="1">

													<tr class="tblMenuColor" height="25px">
														<td width="30%"><strong>Estimate Number</strong></td>
														<td><strong>Cost Center</strong></td>
														<td><strong>Proposer</strong></td>		
													
														<td><strong>Scheme Name</strong></td>
														<td><strong>Electorate</strong></td>
														<td><strong>Representative</strong></td>
														<td><strong>Representative Contact</strong></td>			
														<td><strong>Estimate Date</strong></td>
														<td><strong>Standard Cost</strong></td>
														<td><strong>Entered By</strong></td>
														<td><strong>Fund Id</strong></td>
														
																									
													</tr>
													<%int i=0; %>
													<s:iterator value="tobeValidateList">
														<tr bgcolor="#CCCCCC">
															<s:if test="%{loggedInUserLevel=='ES' || ((loggedInUserLevel=='EE' && smcType=='BS') || (loggedInUserLevel=='EE' && smcType=='LN'))  || (loggedInUserLevel=='PE' && smcType=='PL') || loggedInUserLevel=='ADMIN'}">
																 <s:url id="page_url" namespace="/estimate/web" action="estimateBS!tobeValidate">
																     <s:param name="estNo" value="%{estimateNo}" />
		                                                             <s:param name="costCen" value="%{costCenter}" />
                       	                                      	</s:url>
															</s:if>
															<s:else>
																<s:url id="page_url" namespace="/estimate/web" action="estimateBS!tobeValidate">
		                                                             <s:param name="estNo" value="%{estimateNo}" />
		                                                             <s:param name="costCen" value="%{costCenter}" />
	                                                             </s:url>
															</s:else>
                                                            
                                                          	
															<td align="left" id="tdApprestNo_<%= i %>"><s:a href="%{page_url}"><s:property value="estimateNo" /></s:a></td>

															<td align="left" id="estAprCostCenNo_<%= i %>"><s:property value="costCenter" /></td>
															<td align="left"><s:property value="proposer" /></td>
															
															<td align="left"><s:property value="schemeName" />&nbsp;<s:property value="schemeNo" /></td>												
															<td align="left"><s:property value="electorate" /></td>
															<td align="left"><s:property value="representative1" /></td>
															<td align="left"><s:property value="representativeContact1" /></td>	
															<td align="left"><s:text name="format.date"><s:param value="estimateDate" /></s:text></td>
															<td align="left"><s:text name="format.currency"><s:param value="standardCost" /></s:text></td>
															<td align="left"><s:property value="enterBy" /></td>
															<td align="left"><s:property value="fundId" /></td>
															
															
														</tr>
														<%i++; %>
													</s:iterator>
												</table>

												</td>
											</tr>
											<tr>
												<td>&nbsp;</td>
											</tr>
											
										</table>
									</s:if></td>
								</tr>
											
																			
									</table>
																

</body>
</html>