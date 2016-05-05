<%@ include file="/WEB-INF/jsp/include.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Update Contractor</title>
<link rel="stylesheet" type="text/css" href="../../style/style.css" />
<script type="text/javascript" src="../../script/estimate.js">
	
</script>
<script type="text/javascript" src="../../script/javascript.js">
	
</script>
<script type="text/javascript">
	function submitForm() {
		document.forms["UpdateConstructorForm"].submit();

	}

	function EnableTextfield(cbxID, txtName,txtAddress,txtVAT,txtNBT,txtStatus,txtCode) {
		 
		if (document.getElementById(cbxID).checked)
		{
			 
			document.getElementById(txtName).disabled = false;
			document.getElementById(txtAddress).disabled = false;
			document.getElementById(txtVAT).disabled = false;
			document.getElementById(txtNBT).disabled = false;
			document.getElementById(txtStatus).disabled = false;
			document.getElementById(txtCode).disabled = false;
		}else
		{
			 
			document.getElementById(txtName).disabled = true;
			document.getElementById(txtAddress).disabled = true;
			document.getElementById(txtVAT).disabled = true;
			document.getElementById(txtNBT).disabled = true;
			document.getElementById(txtStatus).disabled = true;
			document.getElementById(txtCode).disabled = true;
		}
		 
	}
</script>
<s:head />
</head>
<body>
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
									<td class="tdLogOut"><a href="<s:url action="logout"/>">Logout</a></td>
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
									<td><s:form action="UpdateContractor" method="POST"
										validate="true" theme="simple" name="UpdateConstructorForm">
										<table width="100%" class="tldFromContent" border="0">
											<tr>
												<td class="listHeader" colspan="3">
												<table width="118%" border="0">
													<tr>
														<td width="30%" class="listHeader">Constructor
														Detials</td>
														<td width="70%"><s:label name="lblSuccess" id="lblSuccess" cssClass="STATUSMSG"></s:label>
														<s:label name="lblError" id="lblError" cssClass="STATUSMSGERR"></s:label></td>
													</tr>
												</table>
												</td>

											</tr>

											<tr>
												<td>&nbsp;</td>
												<td>&nbsp;</td>
												<td>&nbsp;</td>
											</tr>

											<tr>
												<td width="20%">Department ID</td>
												<td width="1%">:</td>
												<td><s:property value="costCenterNo" /></td>
											</tr>

											
											<tr>
												<td>&nbsp;</td>
												<td>&nbsp;</td>
												<td>&nbsp;</td>
											</tr>
											<tr>
												<td colspan="3">
												<table class="tldFromContent" border="1">

													<tr>
														<td><strong>Select</strong></td>
														<td><strong>Name</strong></td>
														<td><strong>Address</strong></td>
														<td><strong>Jobs In Hand</strong></td>
														<td><strong>Total Amount</strong></td>
														<td><strong>VAT</strong></td>
														<td><strong>NBT</strong></td>
														<td><strong>Status</strong></td>
														<td><strong>Code</strong></td>

													</tr>
													<s:iterator value="constructorList">
														<tr>
															<td><s:checkbox
																fieldValue="%{id.contractorId}#*%{id.deptId}"
																name="spestcntID" id="X1%{id.contractorId}" onclick="EnableTextfield('X1%{id.contractorId}','X2%{id.contractorId}','X3%{id.contractorId}','X4%{id.contractorId}','X5%{id.contractorId}','X6%{id.contractorId}','X7%{id.contractorId}')"   ></s:checkbox>
															</td>
															<td><s:textarea name="contractorName" cols="40" rows="1"  disabled="true" id="X2%{id.contractorId}" /></td>
															<td><s:textarea name="contractorAddress" cols="40" rows="1"  disabled="true" id="X3%{id.contractorId}"  /></td>
															
															<td><s:property value="jobInHand" />  </td>
															<td><s:property value="totalAmount" /></td>
															<td><s:textfield name="vat" id="X4%{id.contractorId}"  disabled="true" /></td>
															<td><s:textfield name="nbt" id="X5%{id.contractorId}" disabled="true"  /></td>
															<td><s:textfield name="status" id="X6%{id.contractorId}" disabled="true"  /></td>
															<td><s:textfield name="code" size="4" disabled="true" id="X7%{id.contractorId}"  /></td>
															
														</tr>
													</s:iterator>
												</table>

												</td>
											</tr>
											
											<tr>
												<td colspan="3"><s:submit theme="simple" align="right"
													value="Update" type="submit" method="Update"></s:submit> <s:submit
													theme="simple" align="right" value="Exit" type="submit"
													method="Exit" id="btnClose"></s:submit></td>
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


			</td>
		</tr>
	</tbody>
</table>


</body>
</html>
