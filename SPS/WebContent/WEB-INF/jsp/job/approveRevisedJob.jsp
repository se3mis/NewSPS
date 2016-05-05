<%@ include file="/WEB-INF/jsp/include.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Approve Revised Job</title>
<link rel="stylesheet" type="text/css" href="../../style/style.css" />
<script type="text/javascript" src="../../script/estimate.js"> </script>
<script type="text/javascript" src="../../script/javascript.js"> </script>
<script type="text/javascript">
function abc(divid,imgId)
{
	 if(document.getElementById(divid).style.display == 'none'){
		 document.getElementById(imgId).src ="../../image/down.jpg"
      document.getElementById(divid).style.display = 'block';
    }else{
      document.getElementById(divid).style.display = 'none';
	  document.getElementById(imgId).src ="../../image/up.jpg"
    }
}

function submitForm()
{
	document.forms["approveEstimateForm"].submit();
 
}

function formValidation()
{
	if(document.getElementById("revReason").value.length==0)
	{
		document.getElementById("revReason").focus();
		alert('Please enter a reason to reject');
		return false;
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
									<td><s:form action="approveRevisedJob" method="POST"
										validate="true" theme="simple" name="approveEstimateForm">
										<table width="100%" border="0">
											<tr>
													<td class="listHeader" colspan="6">
                                                    <table width="100%" border="0">
												      <tr>
												      <td class="listHeader" colspan="6">Approve Revised Job</td>													       
													      <td width="75%">
													        <span class="STATUSMSG">
												            <s:property value="lblSuccess" /> </span>
													        <span class="STATUSMSGERR">
												            <s:property value="lblError" /> </span>
												           </td>
												      </tr>
											        </table>
                                                    </td>
                                                    
											</tr>
											<tr>
												<td width="14%">Project Number</td>
												<td width="1%">:</td>
												<td width="27%"><s:select list="estimatesList"
													name="estList" onchange="submitForm();"></s:select></td>
												<td width="14%">Standard Cost</td>
												<td width="0%">:</td>
												<td width="44%"><s:property value="standardCost" /></td>
											</tr>
											<tr>
												<td>Estimate Date</td>
												<td>:</td>
												<td><s:property value="estimateDate" /></td>
												<td>Fixed Cost</td>
												<td>:</td>
												<td><s:property value="fixedCost" /></td>
											</tr>
											<tr>
												<td>Applicant Name</td>
												<td>:</td>
												<td><s:property value="applicantName" /></td>
												<td>Variable Cost</td>
												<td>:</td>
												<td><s:property value="variableCost" /></td>
											</tr>
											<tr>
												<td>Address</td>
												<td>:</td>
												<td><s:property value="applicantAddress" /></td>
												<td>Conversion Cost</td>
												<td>:</td>
												<td><s:property value="conversionCost" /></td>
											</tr>
											<tr>
												<td>Cost Center</td>
												<td>:</td>
												<td><s:property value="costCenterNo" /></td>
												<td>Security Deposite</td>
												<td>:</td>
												<td><s:property value="securityAmount" /></td>
											</tr>
											<tr>
												<td>Estimate Number</td>
												<td>:</td>
												<td><s:property value="estimateNumber" /></td>
												<td>Tax Amount</td>
												<td>:</td>
												<td>&nbsp;</td>
											</tr>
											<tr>
												<td>Fund Source</td>
												<td>:</td>
												<td><s:property value="fundSource" /></td>
												<td>Other Cost</td>
												<td>:</td>
												<td><s:property value="otherCost" /></td>
											</tr>
											<tr>
												<td>&nbsp;</td>
												<td>&nbsp;</td>
												<td>&nbsp;</td>
												<td>Enter By</td>
												<td>:</td>
												<td><s:property value="enterBy" /></td>
											</tr>
											<tr>
												<td>&nbsp;</td>
												<td>&nbsp;</td>
												<td>&nbsp;</td>
												<td>Enter Date</td>
												<td>:</td>
												<td><s:property value="enterDate" /></td>
											</tr>
											<tr>
												<td>Description  <font color="red">*</font></td>
												<td>:</td>
												<td colspan="4"><s:property value="description" /></td>
											</tr>
											<tr>
												<td>Rejected Reason</td>
												<td>:</td>
												<td colspan="4"><s:textarea id="revReason" name="revReason" cols="50"
													rows="5" /></td>
											</tr>
											<tr>
												<td>&nbsp;</td>
												<td>&nbsp;</td>
												<td>&nbsp;</td>
												<td>&nbsp;</td>
												<td>&nbsp;</td>
												<td>&nbsp;</td>
											</tr>
											<tr>
												<td id="tdBotton" colspan="3"><s:if
													test="hasError == true">
													<s:submit theme="simple" type="submit" value="Approve"
														method="ApproveEst" id="btnSave" disabled="true">
													</s:submit>
													<s:submit type="submit" theme="simple" align="right"
														value="Reject" method="RejectEst" id="btnNext"
														disabled="true"></s:submit>


												</s:if> <s:else>
													<s:submit theme="simple" type="submit" value="Approve"
														method="ApproveEst" id="btnSave">
													</s:submit>
													<s:submit type="submit" theme="simple" align="right"
														value="Reject" method="RejectEst" id="btnNext" onclick="return formValidation()"></s:submit>

												</s:else> <s:submit theme="simple" align="right" value="Exit"
													type="submit" method="CloseEst" id="btnClose"></s:submit>
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
