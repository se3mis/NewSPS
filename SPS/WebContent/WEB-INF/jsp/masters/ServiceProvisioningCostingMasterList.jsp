<%@ include file="/WEB-INF/jsp/include.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Service Provisioning Costing Master Detials</title>
<link rel="stylesheet" type="text/css" href="../../style/style.css" />
 
<script type="text/javascript" src="../../script/javascript.js"> </script>
<script type="text/javascript">
 

function submitForm()
{
	document.forms["SPCostForm"].submit();
 
}

 
</script>
<s:head />
</head>
<body onload="buttonLoader()">
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
									<td><s:form action="loadSPCSTListPage" method="POST"
										validate="true" theme="simple" name="SPCostForm">
										<table width="100%" class="tldFromContent" border="0">
											<tr>
												<td class="listHeader" colspan="9">
												<table width="118%" border="0">
													<tr>
														<td width="26%" class="listHeader" >Costing Master
														Detials List</td>
														<td width="74%">
														<s:label name="lblSuccess" id="lblSuccess" cssClass="STATUSMSG"></s:label>
														<s:label name="lblError" id="lblError" cssClass="STATUSMSGERR"></s:label>
														 </td>
													</tr>
												</table>
												</td>

											</tr>


											<tr>
											<td colspan="3"> 
												 <table class="tldFromContent" border="1">

													<tr bgcolor="#93C9FF">
													<td><strong>Phase</strong></td>
														<td><strong>Connection Type</strong></td>
														<td><strong>From</strong></td>														 
														<td><strong>To</strong></td>
														<td><strong>Tariff Category</strong></td>
														<td><strong>Fixed Cost</strong></td>
														<td><strong>Sec. Deposit</strong></td>
														<td><strong>Temporary Deposit</strong></td>
														 
													</tr>
													<s:iterator value="CostingList">
														<tr bgcolor="#CCCCCC">
														 <td> <s:property  value="id.phase" /></td>
														<td> <s:property  value="id.connectionType" /></td>														 
														<td> <s:property  value="id.fromLength" /></td>
														<td> <s:property  value="id.toLength" /></td>
														<td> <s:property  value="id.tariffCatCode" /></td>
														<td> <s:property  value="fixedCost" /></td>
														<td> <s:property  value="securityDeposit" /></td>
														<td> <s:property  value="temporaryDeposit" /></td>
														
															 
															 
														</tr>
													</s:iterator>
												</table>
												</td>
												 
											</tr>
											
											<tr>
												<td>&nbsp;</td>
												<td>&nbsp;</td>
												<td>&nbsp;</td>
											</tr>
											<tr>
												<td colspan="3"> 
												 
												 <s:submit theme="simple" align="right" value="Exit"
													type="submit" method="Close" id="btnClose"></s:submit>
													
													
													
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
