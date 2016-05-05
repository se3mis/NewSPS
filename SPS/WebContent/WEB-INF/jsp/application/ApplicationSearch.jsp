<%@ include file="/WEB-INF/jsp/include.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Application Search</title>
<link rel="stylesheet" type="text/css" href="../../style/style.css" />

<script type="text/javascript" src="../../script/javascript.js"> </script>
<script type="text/javascript">
 

function submitForm()
{
	document.forms["AppSearchForm"].submit();
 
}

 
</script>
<s:head />
<sx:head />
        <style type="text/css">
            .style1
            {
                color: #FFFF00;
            }
            .style3
            {
                color: #0000FF;
            }
        </style>
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
									<td><s:form action="loadAppSearchPage" method="POST"
										validate="true" theme="simple" name="AppSearchForm">
										<table width="100%" class="tldFromContent" border="0">
											<tr>
												<td class="listHeader" colspan="9">
												<table width="118%" border="0">
													<tr>
														<td width="26%" class="listHeader">Application Search</td>
														<td width="74%" style="text-align: right; width: 0%;"><span style="background-color: lime;">
														<s:property value="lblSuccess" /> </span> <span
															style="background-color: red;"> <s:property
															value="lblError" /> </span></td>
														<td width="74%" style="text-align: right; width: 37%;"><s:submit theme="simple" align="right" value="X"
													type="submit" method="Close" id="btnClose"></s:submit></td>
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
												<td>Department ID</td>
												<td>:</td>
												<td><s:property value="costCenter" /></td>
											</tr>
											<tr>
												<td width="18%">NIC</td>
												<td width="1%">:</td>
												<td width="81%"><s:textfield id="nic" name="nic"
													size="10" maxlength="10" ></s:textfield></td>
											</tr>
											<tr>
												<td>Temp ID (<span class="style1">ANC/ACR/ATC Number</span>)</td>
												<td>:</td>
												<td><s:textfield id="appID" name="appID" size="20" maxlength="20" ></s:textfield>
												</td>
											</tr>
											<tr>
												<td>Application Number<br />
                                                    (<span class="style1">ENC/ECR/ETC Number</span>)</td>
												<td>:</td>
												<td><s:textfield id="appName" name="appName" size="20" maxlength="20" ></s:textfield>
												</td>
											</tr>
											<tr>
													<td>Job Number<br />
                                                    (<span class="style1">SMC/CRJ/TMP Number</span>)</td>
												<td>:</td>
												<td><s:textfield id="jobNumber" name="jobNumber" size="18" maxlength="18" ></s:textfield>
												</td>
											</tr>
											<tr>
												<td>First Name</td>
												<td>:</td>
												<td><s:textfield id="firstName" name="firstName"
													size="50"></s:textfield></td>
											</tr>
											<tr>
												<td>Last Name</td>
												<td>:</td>
												<td><s:textfield id="lastName" name="lastName"
													size="50"></s:textfield></td>
											</tr>
											<tr>
												<td>Suburb</td>
												<td>:</td>
												<td><s:textfield id="suberb" name="suberb"
													size="50"></s:textfield></td>
											</tr>
											<tr>
												<td>City</td>
												<td>:</td>
												<td><s:textfield id="city" name="city"
													size="50"></s:textfield></td>
											</tr>
											<tr>
												<td>From Date</td>
												<td>:</td>
												<td><sx:datetimepicker id="fromDate" name="fromDate"
													displayFormat="yyyy-MMM-dd" /></td>
											</tr>
											<tr>
												<td>To Date</td>
												<td>:</td>
												<td><sx:datetimepicker id="toDate" name="toDate"
													displayFormat="yyyy-MMM-dd" /></td>
											</tr>
											<tr>
												<td>&nbsp;</td>
												<td>&nbsp;</td>
												<td>&nbsp;</td>
											</tr>
											<tr>
												<td colspan="3"><s:submit theme="simple" type="submit"
													value="Search" method="Search" id="btnSearch">
												</s:submit> <s:submit theme="simple" align="right" value="Exit"
													type="submit" method="Close" id="btnClose"></s:submit></td>


											</tr>

											<tr>
												<td colspan="9">
												<table class="tldFromContent" border="1">

													<tr>
														<td><strong>NIC</strong></td>
														<td><strong>Name</strong></td>
														<td><strong>Address</strong></td>
														<td class="style3"><strong>App ID</strong></td>
														<td><strong>App Number</strong></td>
														<td><strong>App Type</strong></td>
														<td><strong>Submitted Date</strong></td>
														<td><strong>Application Status</strong></td>
														<td><strong> Status</strong></td>
													</tr>
													<s:iterator value="applicationList">
													<tr>
														<s:url id="page_url" namespace="/application/web" action="loadAppStatusPage">
																<s:param name="applicationId" value="%{applicationID}" />                                                               
														</s:url>
														<td><s:property value="nic" /></td>
														<td><s:property value="name" /></td>
														<td><s:property value="address" /> </td>
														<td class="style3"><s:a href="%{page_url}"><s:property value="applicationID" /></s:a></td>
<!--														<td><s:property value="applicationID" /></td>-->
														<td><s:property value="applicationNumber" /></td>
														<td><s:property value="applicationType" /> </td>
														<td><s:property value="submitedDate" /></td>
														<td><s:property value="applicationStatus" /></td>
														<td><s:property value="estimateStatus" /></td>
														 </tr>
													</s:iterator>
												</table>

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
									<td>@ Ceylon Electricity Board					</tbody>
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
