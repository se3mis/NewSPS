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
																<td width="25%"><strong>Estimate Number</strong></td>
																<td><strong>Cost Center</strong></td>
																<td><strong>Estimate Date</strong></td>
																<td><strong>Standard Cost</strong></td>
																<td><strong>Entered By</strong></td>
																<td><strong>Approved By</strong></td>
																<td><strong>Approved Date</strong></td>
																
															<%int i=0; %>
															<s:iterator value="toBeAllocatedConMainList">
																<tr bgcolor="#CCCCCC">
																	
		                                                             <s:url id="page_url" namespace="/calendarBS/web" action="diaryEntryCMBS!directFromMain">
		                                                             <s:param name="estNo" value="%{id.stdNo}" />
		                                                             <s:param name="costCen" value="%{id.deptId}" />
		                                                             </s:url>
		                                                          	
																	<td align="left" id="tdApprestNo_<%= i %>"><s:a href="%{page_url}"><s:property value="id.stdNo" /></s:a></td>
		
																	<td align="left" id="estAprCostCenNo_<%= i %>"><s:property value="postDeptId" /></td>
																	<td align="left"><s:text name="format.date"><s:param value="entryDate" /></s:text></td>
																	<td align="left"><s:text name="format.currency"><s:param value="totalCost" /></s:text></td>
																	<td align="left"><s:property value="entryBy" /></td>
																	<td align="left"><s:property value="approvedBy" /></td>
																	<td align="left"><s:property value="approvedDate" /></td>
																	
																</tr>
																<%i++; %>
															</s:iterator>
														</table>
		
																

</body>
</html>