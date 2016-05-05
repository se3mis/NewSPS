<%@ include file="/WEB-INF/jsp/include.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Update Labour Activity</title>
<link rel="stylesheet" type="text/css" href="../../style/style.css" />
 
<script type="text/javascript" src="../../script/javascript.js"> </script>
 <script type="text/javascript" src="../../script/addNewContractor.js"> </script>
 
<script type="text/javascript">
 

function submitForm()
{
	document.forms["UpdateLabourActivity"].submit();
 
}

 
</script>
<s:head  />
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
									<td><s:form action="UpdateLabourActivity" method="POST"
										validate="true" theme="simple" name="UpdateLabourActivity">
										<table width="100%" class="tldFromContent" border="0">
											<tr>
												<td class="listHeader" colspan="9">
												<table width="118%" border="0">
													<tr>
														<td width="26%" class="listHeader" >Labour Activity Rates</td>
														<td width="74%"><s:label name="lblSuccess" id="lblSuccess" cssClass="STATUSMSG"></s:label>
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
												<td >Labour Code</td>
												<td >:</td>
												<td ><s:select list="labourCodeList"
													headerKey="-1" listValue="%{id.labourCode}"
													listKey="id.labourCode" emptyOption="false"
													name="selectedLabourCode" onchange="submitForm();"></s:select></td>
											</tr>
											<tr>
												<td >Application Type</td>
												<td >:</td>
												<td ><s:select list="applicationTypeList"
													headerKey="-1" listValue="%{phaseName}"
													listKey="phaseValue" emptyOption="false"
													name="selectedApplicationType" ></s:select></td>
											</tr>
											<tr>
												<td>Department ID</td>
												<td>:</td>
												<td><s:property value="costCenter" /></td>
											</tr>
											 
											 
											
											<tr>
												<td>Labour Name<font color="red">*</font></td>
												<td>:</td>
												<td> <s:textfield  id="labourName" name="labourName" size="50" ></s:textfield>   </td>
											</tr>
											
											
											<tr>
												<td>Description<font color="red">*</font></td>
												<td>:</td>
												<td> <s:textarea id="description" name="description"  cols="50" rows="5" ></s:textarea>   </td>
											</tr>
											<tr>
												<td>Unit Price<font color="red">*</font></td>
												<td>:</td>
												<td> <s:textfield id="unitPrice" name="unitPrice" size="50" onblur="numericValidate(this,event);formatValue(this)"  onkeyup="numericValidate(this,event)" ></s:textfield>   </td>
											</tr>
											
											
											<tr>
												<td>Labour Hours<font color="red">*</font></td>
												<td>:</td>
												<td> <s:textfield id="labourHours" name="labourHours" size="50" onblur="numericValidate(this,event);formatValue(this)"  onkeyup="numericValidate(this,event)" ></s:textfield>  </td>
											</tr>
											 
											<tr>
											  <td colspan="3">&nbsp;</td>
										  </tr>
											<tr>
												<td colspan="3"><s:submit  theme="simple" type="submit"
													value="Update Labour Activity Rates" method="AddLabourActivityRate"  >
												</s:submit>											
												 
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
