<%@ include file="/WEB-INF/jsp/include.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Service Provisioning Estimate Materials Master Detials</title>
<link rel="stylesheet" type="text/css" href="../../style/style.css" />
 
<script type="text/javascript" src="../../script/javascript.js">
	
</script>
<script type="text/javascript">
	function submitForm() {
		document.forms["SPCostForm"].submit();

	}

	function EnableTextfield(cbxID, txtMatQty) {
		 
		if (document.getElementById(cbxID).checked)
		{
			 
		 
			document.getElementById(txtMatQty).disabled = false;
		}else
		{
			 
			 
			document.getElementById(txtMatQty).disabled = true;
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
									<td><s:form action="loadSPESTMTMage" method="POST"
										validate="true" theme="simple" name="SPCostForm">
										<table width="100%" class="tldFromContent" border="0">
											<tr>
												<td class="listHeader" colspan="9">
												<table width="118%" border="0">
													<tr>
														<td width="30%" class="listHeader" >Materials Master Detials</td>
														<td width="70%"> <s:label name="lblSuccess" id="lblSuccess" cssClass="STATUSMSG"></s:label>
														<s:label name="lblError" id="lblError" cssClass="STATUSMSGERR"></s:label></td>
													</tr>
												</table>
												</td>

											</tr>

											<tr>
												<td>&nbsp;</td>
												<td>&nbsp;</td>
												<td>&nbsp;</td>
												<td>&nbsp;</td>
												<td>&nbsp;</td>
												<td>&nbsp;</td>
												<td>&nbsp;</td>
												<td>&nbsp;</td>
												<td>&nbsp;</td>
											</tr>
											<tr>
												<td width="20%">Phase</td>
												<td width="1%">:</td>
												<td width="20%"><s:select onchange="submitForm()" list="phaseList"
													headerKey="-1" listValue="%{phaseName}"
													listKey="phaseValue" emptyOption="false"
													name="selectedPhase"></s:select></td>
												<td width="20%">Connection Type</td>
												<td width="1%">:</td>
												<td width="20%"><s:select onchange="submitForm()" list="conectionTypesList"
													name="selectedConectionType"></s:select></td>
												<td width="20%"></td>
												<td width="1%"></td>
												<td width="20%"> </td>
											</tr>
											<tr>
												<td>Material Code</td>
												<td>:</td>
												<td><s:textfield name="mtralCode" size="15"></s:textfield></td>
												<td>Material Quantity</td>
												<td>:</td>
												<td><s:textfield name="mtralQty" size="15" onblur="numericValidate(this,event)"  onkeyup="numericValidate(this,event)" ></s:textfield></td>
												<td width="20%">Cost Center</td>
												<td width="1%">:</td>
												<td width="20%"> <s:property value="costCenter" /></td>
											</tr>
											<tr>
											  <td>Wiring Type</td>
											  <td>:</td>
											  <td><s:select onchange="submitForm()" list="wiringTypeList"
													name="selectedwiringType"></s:select></td>
											  <td>&nbsp;</td>
											  <td>&nbsp;</td>
											  <td>&nbsp;</td>
											  <td>&nbsp;</td>
											  <td>&nbsp;</td>
											  <td>&nbsp;</td>
										  </tr>
											<tr>
												<td colspan="9"><s:submit theme="simple" type="submit"
													value="Add New" method="AddNewMaterial" id="btnSave">
													
													<s:submit theme="simple" align="right" value="Remove"
														type="submit" method="Remove"></s:submit>
														
														<s:submit theme="simple" align="right" value="Update"
														type="submit" method="Update"></s:submit>
														

												</s:submit> <s:submit theme="simple" align="right" value="Exit"
													type="submit" method="Close" id="btnClose"></s:submit></td>
											</tr>

											<tr>
												<td colspan="9">
												<table class="tldFromContent" border="1">

													<tr>
													<td><strong>Select</strong></td>
														<td><strong>Phase</strong></td>
														<td><strong>Connection Type</strong></td>
														 
														<td><strong>Material Code</strong></td>
														<td><strong>Material Quantity</strong></td>
														<td><strong>Wiring Type</strong></td>
													</tr>
													<s:iterator value="estmateMaterialsList">
														<tr>
														<td>  <s:checkbox fieldValue="%{id.phase}#*%{id.connectionType}#*%{id.matCd}#*%{matQty}#*%{id.wiringType}"  name="spestmtmID" 
														id="X1%{id.matCd}" onclick="EnableTextfield('X1%{id.matCd}','X3%{id.matCd}')"  ></s:checkbox> </td>
															<td>
															<s:property  value="id.phase" /></td>
															<td><s:property value="id.connectionType" /></td>
															 
															 <td>  <s:property value="id.matCd" />    </td>
															 <td> <s:textfield  name="matQty" disabled="true" id="X3%{id.matCd}" />  </td>
															  <td>  <s:property value="id.wiringType" />    </td>
															 
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
