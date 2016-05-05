<%@ include file="/WEB-INF/jsp/include.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Letters</title>
<link rel="stylesheet" type="text/css" href="../../style/style.css" />
<script type="text/javascript" src="../../script/estimate.js"> </script>
<script type="text/javascript" src="../../script/javascript.js"> </script>


<script type="text/javascript">
 

function submitForm()
{
	document.forms["EstimateAmount"].submit();
 
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
									<td><s:form action="loadEstimateAmountPage"
										method="POST" validate="true" theme="simple" name="EstimateAmount">
										<table width="100%" class="tldFromContent" border="0">
											<tr>
												<td class="listHeader" colspan="9">
												<table width="118%" border="0">
													<tr>
														<td width="33%" class="listHeader">Estimate Quotation</td>
														<td width="67%"><span style="background-color: lime;">
														<s:property value="lblSuccess" /> </span> <span
															style="background-color: red;"> <s:property
															value="lblError" /> </span></td>
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
												<td width="18%">Cost Center Name</td>
												<td width="1%">:</td>
												<td width="81%"><s:property value="costCenterName" />
												<s:hidden name="costCenterName" value="%{costCenterName}" />
												</td>
											</tr>
											<tr>
												<td width="18%">Cost Center Area</td>
												<td width="1%">:</td>
												<td width="81%"><s:property value="costCenterArea" />
												<s:hidden name="costCenterArea" value="%{costCenterArea}" />
												</td>
											</tr>
											<tr>
												<td>Cost Center Address</td>
												<td>:</td>
												<td><s:textfield name="costCenterAddress" size="50"></s:textfield>
												</td>
											</tr>
											<tr>
												<td>Estimate Number</td>
												<td>:</td>
												<td><s:select list="applicationNumberList"
													name="selectedAppNumber" onchange="submitForm();"></s:select>
												</td>
											</tr>
											<tr>
												<td>Customer Name</td>
												<td>:</td>
												<td><s:property value="customerName" /> <s:hidden
													name="customerName" value="%{customerName}" /></td>
											</tr>
											<tr>
												<td>Customer Address</td>
												<td>:</td>
												<td><s:property value="customerAddress" /> 
												<s:hidden name="streetAddress" value="%{streetAddress}" />
												<s:hidden name="suberb" value="%{suberb}" />
												<s:hidden name="city" value="%{city}" />
												<s:hidden name="postalCode" value="%{postalCode}" />
												 </td>
											</tr>
											<tr>
												<td>Phase</td>
												<td>:</td>
												<td><s:property value="phase" /> <s:hidden
													name="phase" value="%{phase}" /></td>
											</tr>
											<tr>
												<td>Application Date</td>
												<td>:</td>
												<td><s:property value="appDate" /> <s:hidden
													name="appDate" value="%{appDate}" /></td>
											</tr>
											 

											<tr>
												<td>Total Cost</td>
												<td>:</td>
												<td><s:property value="totalCost" /> 
												<s:hidden name="totalCost" value="%{totalCost}" />
												</td>
											</tr>
											<tr>
												<td>Security Deposit</td>
												<td>:</td>
												<td><s:property value="securityDeposit" /> 
												<s:hidden name="securityDeposit" value="%{securityDeposit}" />
												</td>
											</tr>


										 
											<tr>
												<td colspan="3"><s:submit theme="simple" type="submit"
													value="Download Letter" method="GenerateLetter">
												</s:submit> <s:submit theme="simple" align="right" value="Exit"
													type="submit" method="Exit" id="btnClose"></s:submit></td>
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
