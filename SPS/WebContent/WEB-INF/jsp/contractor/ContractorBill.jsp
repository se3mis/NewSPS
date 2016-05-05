<%@ include file="/WEB-INF/jsp/include.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Reports</title>
<link rel="stylesheet" type="text/css" href="../../style/style.css" />

<script type="text/javascript" src="../../script/javascript.js">
	
</script>
<script type="text/javascript" src="../../script/addNewContractor.js">
	
</script>

<s:head />
<sx:head />
<script type="text/javascript">
	function submitForm() {
		document.forms["billContractorForm"].submit();

	}
	function checkAll(field) {
		for (i = 0; i < field.length; i++)
			field[i].checked = true;
	}

	function uncheckAll(field) {
		for (i = 0; i < field.length; i++)
			field[i].checked = false;
	}
</script>

</head>
<body onLoad="buttonLoader()">
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
									<td><img src="../../image/NewSPS2.gif" width="100%"
										alt="NewSPS2" /></td>
									<td width="*" bgcolor="#000033"></td>
									<td width="*" bgcolor="#000033"></td>
								</tr>
							</tbody>
						</table>
						</td>
					</tr>
					<!--tr>
						<td></td>
					</tr-->
					<tr>
						<td id="tdWelcome">
						<table class="tblWelcome" border="0" cellpadding="0"
							cellspacing="0">
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
									<td class="tdWelcomeUser"><s:property
										value="loggedInUserId"></s:property></td>
									<td><a href="<s:url action="logout"/>">Logout</a></td>
								</tr>
							</tbody>
						</table>
						</td>
					</tr>
					<!-- tr>
						<td></td>
					</tr-->
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
									<td><s:form action="billContractor" method="POST"
										validate="true" theme="simple" name="billContractorForm">
										<table width="100%" class="tldFromContent" border="0">
											<tr>
												<td class="listHeader" colspan="3">
												<table width="118%" border="0">
													<tr>
														<td width="26%" class="listHeader">Select Report</td>
														<td width="74%"><span style="background-color: lime;">
														<s:property value="lblSuccess" /> </span> <span
															style="background-color: red;"> <s:property
															value="lblError" /> </span></td>
													</tr>
												</table>
												</td>

											</tr>


											<tr>
												<td width="13%"></td>
												<td width="1%">&nbsp;</td>
												<td>&nbsp;</td>
											</tr>

											<tr>
												<td colspan="3"></td>
											</tr>




											<tr>
												<td>Cost Center</td>
												<td>:</td>
												<td><s:property value="costCenter" /></td>
											</tr>




											<tr>
												<td width="18%">Contractor List</td>
												<td width="1%">:</td>
												<td width="81%"><s:select list="ContractorList"
													headerKey="-1" listValue="%{contractorName}"
													listKey="id.contractorId" emptyOption="false"
													name="selectedContractorID" onchange="submitForm()"></s:select></td>
											</tr>







											<tr>
												
												<td><input type="button" name="CheckAll"
													value="Check All" onClick="checkAll(document.billContractorForm.selectedFinishedJobs)"> 
												 <input type="button" name="UnCheckAll"
													value="Uncheck All"
													onClick="uncheckAll(document.billContractorForm.selectedFinishedJobs)"></td>
													<td></td>
													<td></td>
											</tr>






											<tr  >
												<td colspan="3">
												<table class="tldFromContent" border="1" >

													<tr>
														<td><strong>Select</strong></td>
														<td><strong>Finished Job Number</strong></td>

													</tr>
													<s:iterator value="finshedJobs">
														<tr>
															<td><s:checkbox fieldValue="%{id.projectNo}" 
																name="selectedFinishedJobs" id="%{id.projectNo}"></s:checkbox></td>
															<td><s:property value="id.projectNo" /></td>

														</tr>
													</s:iterator>

												</table>

												</td>
											</tr>








											<tr>
												<td colspan="3">
											</tr>

											<tr>
												<td colspan="3"> 
												
												
												<s:submit theme="simple" align="right"
													value="Create Bill" type="submit" method="createBill">
												</s:submit>
												
												 <s:submit theme="simple" align="right" value="Exit"
													type="submit" method="Exit" id="btnClose">
												</s:submit></td>


											</tr>
										</table>

									</s:form></td>
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
									<td>@ Ceylon Electricity Board</td>
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
