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
														
														<td width="15%"><strong>Construction Reference Number</strong></td>
														<td width="15%"><strong>Reference Number</strong></td>
														<td><strong>Allocated By </strong></td>
														<td><strong>Allocated To</strong></td>
														<td><strong>Appointment Date</strong></td>
														<td><strong>Time Session</strong></td>
														<td><strong>Description</strong></td>												 


													</tr>
													<%int i=0; %>
													<s:iterator value="tobeEnteredDetailEstimates">
														<tr bgcolor="#CCCCCC">
														
														<s:if test="%{branchType=='MU'}">
															
																 <s:url id="page_url" namespace="/estimate/web" action="pegSchedule!directItembyitemWithOutCR">
		                                                             <s:param name="estNo" value="%{westimateNo}" />
		                                                             <s:param name="costCen" value="%{id.deptId}" />
                       	                                      	</s:url>
															</s:if>
															<s:else>
																<s:url id="page_url" namespace="/estimate/web" action="pegSchedule!newDirectEstimateEntry">
																<s:param name="estNo" value="%{westimateNo}" />
																<s:param name="costCen" value="%{id.deptId}" />
																</s:url>
															</s:else>
															
															
															
														<td align="left" id="tdRevPrjtNo_<%= i %>"><s:a href="%{page_url}"><s:property value="westimateNo" /></s:a></td>
														
															<td align="left"><s:property value="referenceNo" /></td>
															<td align="left"><s:property value="allocatedBy" /></td>

															<td align="left"><s:property value="allocatedTo" /></td>

															<td align="left"><s:text name="format.date">
																<s:param value="appointmentDate" />
															</s:text></td>
															<td align="left"><s:property value="timeSession" /></td>
															 
															<td align="left"><s:property value="description" /></td>
															 
														</tr>
													</s:iterator>
													<%i++; %>
												</table>
																

</body>
</html>