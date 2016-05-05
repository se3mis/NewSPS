<%@ include file="/WEB-INF/jsp/include.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add new contractor</title>
<link rel="stylesheet" type="text/css" href="../../style/style.css" />

<script type="text/javascript" src="../../script/javascript.js"> </script>
<script type="text/javascript" src="../../script/addNewContractor.js"> </script>

<script type="text/javascript">
 

function submitForm()
{
	document.forms["ContractorList"].submit();
 
}

 
</script>
<s:head />
<sx:head />
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
				                                         <td ><img src="../../image/NewSPS2.gif" width="100%"  alt="NewSPS2"/>
				                                         </td>
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
									<td><s:form action="ContractorList" method="POST"
										validate="true" theme="simple" name="ContractorList">
										<table width="100%" class="tldFromContent" border="0">
											<tr>
												<td class="listHeader" colspan="9">
												<table width="118%" border="0">
													<tr>
														<td width="26%" class="listHeader">Contractor
														Detials</td>
														<td width="74%"><s:label name="lblSuccess"
															id="lblSuccess" cssClass="STATUSMSG"></s:label> <s:label
															name="lblError" id="lblError" cssClass="STATUSMSGERR"></s:label></td>
													</tr>
												</table>
												</td>

											</tr>





											<tr>
												<td colspan="3">
												<table class="tldFromContent" border="1">

													<tr bgcolor="#93C9FF">
														<td><strong>Name</strong></td>
														<td><strong>Address</strong></td>
														<td><strong>Jobs In Hand</strong></td>
														<td><strong>Total Amount</strong></td>
														<td><strong>Bond Number</strong></td>
														<td><strong>Start Date</strong></td>
														<td><strong>Perfomance Amount</strong></td>
														<td><strong>VAT</strong></td>
														<td><strong>NBT</strong></td>



													</tr>
													<s:iterator value="ContractorList">
														<tr bgcolor="#CCCCCC">
															<td><s:property value="contractorName" /></td>
															<td><s:property value="contractorAddress" /></td>
															<td><s:property value="jobInHand" /></td>
															<td><s:property value="totalAmount" /></td>
															<td><s:property value="bondNo" /></td>
															<td><s:property value="startDate" /></td>
															<td><s:property value="performanceAmount" /></td>
															<td><s:property value="vat" /></td>
															<td><s:property value="nbt" /></td>



														</tr>
													</s:iterator>
												</table>

												</td>
											</tr>
											<tr>
												<td colspan="3"><s:submit theme="simple" align="right"
													value="Exit" type="submit" method="Close" id="btnClose"></s:submit>



												</td>


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
