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
														<td><strong>Application Type</strong></td>
														<td><strong>Service Depot Name</strong></td>
														<td><strong>Proposer</strong></td>
														<td><strong>Schema Name</strong></td>
													
														
													</tr>
													<%int i=0; %> 
													<s:iterator value="toBeAllocatedList">
														<tr bgcolor="#CCCCCC">
															<s:url id="page_url" namespace="/calendarBS/web" action="diaryEntryCMBS!directFromMain">
																
                                                                <s:param name="estNo" value="%{estimateNo}" />
															</s:url>
															<td align="left" id="unAttApptRefNo_<%= i %>"><s:a href="%{page_url}"><s:property value="estimateNo" /></s:a></td>
														
															
															
															<td align="left"><s:property value="costCenter" /></td>
															<td align="left"><s:property value="applicationType" /></td>
															
															<td align="left"><s:property value="area" /></td>
															
															<td align="left"><s:property value="proposer" /></td>
															
															<td align="left"><s:property value="schemeName" /><s:property value="schemeNo" /></td>
															
																														 
														</tr>
														<%i++; %>

													</s:iterator>
														</table>
		
																

</body>
</html>