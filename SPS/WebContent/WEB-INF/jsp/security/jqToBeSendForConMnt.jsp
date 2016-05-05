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
																
												
									<table class="tldFromContent" border="1">

													<tr class="tblMenuColor" height="25px">
														<td><strong>Estimate Number</strong></td>
														<td><strong>Cost Center</strong></td>
														<td><strong>Estimate Date</strong></td>
														<td><strong>Standard Cost</strong></td>
														<td><strong>Entered By</strong></td>
														<td><strong>Approved By</strong></td>
														<td><strong>Approved Date</strong></td>
														
													<%int i=0; %>
													<s:iterator value="sEsendForConMainList">
														<tr bgcolor="#CCCCCC">
															
                                                             <s:url id="page_url" namespace="/estimate/web" action="sendForConsMainFMainMenu">
                                                             <s:param name="estNo" value="%{id.stdNo}" />
                                                             <s:param name="costCen" value="%{id.deptId}" />
                                                             </s:url>
                                                          	
															<td align="right" id="tdApprestNo_<%= i %>"><s:a href="%{page_url}"><s:property value="id.stdNo" /></s:a></td>

															<td align="right" id="estAprCostCenNo_<%= i %>"><s:property value="id.deptId" /></td>
															<td align="right"><s:text name="format.date"><s:param value="entryDate" /></s:text></td>
															<td align="right"><s:text name="format.currency"><s:param value="totalCost" /></s:text></td>
															<td align="right"><s:property value="entryBy" /></td>
															<td align="right"><s:property value="approvedBy" /></td>
															<td align="right"><s:property value="approvedDate" /></td>
															
														</tr>
														<%i++; %>
													</s:iterator>
												</table>

																

</body>
</html>