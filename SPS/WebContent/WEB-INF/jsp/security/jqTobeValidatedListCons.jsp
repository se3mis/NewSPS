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
                                                	<td width="15%"><strong>Estimate Number</strong></td>
                                                	<td><strong>Fund Source</strong></td>
													<td><strong>Cost Center</strong></td>													
													<td width="10%"><strong>Estimate Date</strong></td>													
													<td><strong>Work Estimate Cost</strong></td>
													<td><strong>Entry By</strong></td>
													<td width="10%"><strong>Entry Date</strong></td>
													<td><strong>Description</strong></td>	
																							
												</tr>
												<%int i=0; %>
												
												<s:iterator value="tobeValidateWorkEsList">
												<tr bgcolor="#CCCCCC">
												
             									</tr>			
													<tr bgcolor="#CCCCCC">		
														<s:url id="page_url" namespace="/estimate/web" action="pegSchedule!workEstiValidate">
														<s:param name="estNo" value="%{id.estimateNo}" />
														
														<s:param name="costCen" value="%{id.deptId}" />
														</s:url>
													    
														<td align="left" id="tdApprestNo_<%= i %>"><s:a href="%{page_url}"><s:property value="id.estimateNo" /></s:a></td>
														<td align="left"><s:property value="fundSource" /></td>	
														<td align="left" id="rejcostCenNo_<%= i %>"><s:property value="id.deptId" /></td>														
														<td align="left"><s:text name="format.date" ><s:param value="etimateDt"/></s:text> </td>
														<td align="left"><s:text name="format.currency" ><s:param value="stdCost"/></s:text></td>
														<td align="left"><s:property value="entBy" /></td>
														<td align="left"><s:text name="format.date" ><s:param value="entDt"/></s:text> </td>
														<td align="left"><s:property value="descr" /></td>	
																										
													</tr>
													
													<%i++;%>
												</s:iterator>
											</table>
</body>
</html>