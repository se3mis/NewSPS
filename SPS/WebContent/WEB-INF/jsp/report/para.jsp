<html>
<head>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<title>SPS Reports</title>
<link rel="stylesheet" type="text/css" href="../../style/style.css" />


<s:head />

<sx:head/>
<script>
function myFunction() {
    var fDate = document.getElementById("fDate").value;  
    document.getElementById("fromDate").value = fDate;
   
}
function myFunction1() {   
    var tDate = document.getElementById("tDate").value;   
    document.getElementById("toDate").value = tDate;
}
</script>
</head>
<body>

	<table id="tblOuter" class="tblOuter">
		<tbody>
			<tr>
				<td>
					<table id="tblInner" class="tblInner">
						<tbody>
							<tr>
								<td id="tdHeader"></td>
							</tr>
							<tr>
								<td id="tdWelcome">
									<table class="tblWelcome" border="0" cellpadding="0"
										cellspacing="0">
										<thead>
											<tr>
												<td width="2%"></td>
												<th width="65%"></th>
												<th width="10%"></th>
												<th width="10%"></th>
												<th width="*"></th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td></td>
												<td class="tdPath"><s:property value="path"></s:property></td>
												<td></td>
												<td></td>
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
								<td id="tdContent">
									<table class="tblContent" border="" cellpadding=""
										cellspacing="0">
										<thead>
											<tr>
												<th></th>
											</tr>
										</thead>
										<tbody>


											<s:form action="para" method="POST" validate="true"
													theme="simple" name="reportsForm">
													<table width="100%" class="tldFromContent">
														<thead>
															<tr>
																<th width="10%"></th>
																<th width="15%"></th>
																<th width="10"></th>
																<th width="*"></th>

															</tr>
														</thead>
														
														<tbody>

															<tr>

																<td height="80%"></td>
																<td><span style="background-color: lime;"> <s:property
																			value="lblSuccess" />
																</span> <span style="background-color: red;"> <s:property
																			value="lblError" />
																</span></td>
															</tr>
														
															<tr class="tblHeader">														
																<s:if test="%{costCenterVisible=='true'}">
																	<tr>
																		<td>Cost Center:&nbsp;</td>
																		<td><s:textfield name="costctr" id="costctr" label=""/></td>
																	</tr>
																</s:if>
																<s:if test="%{dateFromVisible=='true'}">
																	<tr>
																		<td>Date From :&nbsp;</td>																		
																		<%-- <td><sx:datetimepicker name="fromDate" id="fromDate" displayFormat="yyyy/MM/dd"></sx:datetimepicker></td> --%>
																		<td><input type="Date" name="fDate" id="fDate" onChange="myFunction()"/></td>
																		<s:hidden name="dateFrom" id="fromDate" ></s:hidden>
																	</tr>
																</s:if>
																<s:if test="%{dateToVisible=='true'}">
																	<tr>
																		<td>Date To :&nbsp;</td>																																		
																		<%--<td><sx:datetimepicker name="toDate" id="toDate" displayFormat="yyyy/MM/dd"></sx:datetimepicker></td> --%>
																		<td><input type="Date" name="tDate" id="tDate" onChange="myFunction1()"/></td>
																		<s:hidden name="dateTo" id="toDate"></s:hidden>
																	</tr>
																</s:if>
																<s:if test="%{fundIdVisible=='true'}">
																	<tr>
																		<td>Fund ID :&nbsp;</td>
																		<td><s:select  name="fundId" id="fundId"  headerKey="" list="fundSourceList"  headerValue="-- Select --"></s:select></td>
																	</tr>
																</s:if>
																<s:if test="%{contractorVisible=='true'}">
																	<tr>
																		<td>Contractor :&nbsp;</td>
																		<td><s:select list="listContractor" name="contractor" headerKey="" id="contractor" headerValue="-- Select --"></s:select></td>
																	</tr>
																</s:if>
																<s:if test="%{eSVisible=='true'}">
																	<tr>
																		<td>ES :&nbsp;</td>
																		<td><s:select list="esUserList" name="es" headerKey="" id="es"	headerValue="-- Select --"></s:select></td>	
																	</tr>
																</s:if>
																<s:if test="%{districtVisible=='true'}">
																	<tr>
																		<td>District :&nbsp;</td>
																		<td><s:select list="districtCodeList" name="district" headerKey="" id="district"	headerValue="-- Select --"></s:select></td>
																	</tr>
																</s:if>
																<s:if test="%{cSCVisible=='true'}">
																	<tr>
																		<td>CSC :&nbsp;</td>
																		<td><s:select  name="csc" id="CSCList" headerKey=""  list="CSCList"	headerValue="-- Select --"></s:select></td>	
																	</tr>
																</s:if>
																<s:if test="%{electroateVisible=='true'}">
																	<tr>														
																		<td>Electorate :&nbsp;</td>
																		<td><s:select  name="electorate" id="electorate" headerKey=""  list="electorateList"	headerValue="-- Select --"></s:select></td>
																	</tr>
																</s:if>
																<s:if test="%{areaVisible=='true'}">
																	<tr>
																		<td>Area :&nbsp;</td>
																		<td><s:select  name="area" id="area"  headerKey="" list="areaCodeList"  headerValue="-- Select --"></s:select></td>
																	</tr>
																</s:if>
																<s:if test="%{fromNoVisible=='true'}">
																	<tr>
																		<td>From No :&nbsp;</td>
																		<td><s:textfield name="fromNo" id="fromNo"/></td>
																	</tr>	
																</s:if>
																<s:if test="%{toNoVisible=='true'}">
																	<tr>
																		<td>To No :&nbsp;</td>
																		<td><s:textfield name="toNo" id="toNo"/></td>
																	</tr>	
																</s:if>
															</tr> 
															<s:hidden theme="simple" id="path" name="path" />
															
															<tr>
																<td colspan="3"><s:submit theme="simple"
																		value=" Generate Report " method="view"></s:submit> 	
																		<s:reset value=" Reset " theme="simple"></s:reset>
																		
																	 <s:hidden theme="simple" id="path" name="path" /></td>
															</tr>

														</tbody>
													</table>

												</s:form>
										
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
									<table id="tblFooter" class="tblFooter" border="0"
										cellpadding="0" cellspacing="0">
										<tbody>
											<tr>
												<td></td>
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
