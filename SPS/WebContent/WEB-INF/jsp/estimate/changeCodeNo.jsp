<%@ include file="/WEB-INF/jsp/include.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Update Contractor</title>
<link rel="stylesheet" type="text/css" href="../../style/style.css" />
<!-- <script type="text/javascript" src="../../script/estimate.js">
	
</script>
<script type="text/javascript" src="../../script/javascript.js">
	
</script> -->
<script type="text/javascript">
	function submitForm() {
		document.forms["UpdateConstructorForm"].submit();

	}
	function EnableTextfield(cbxID, txtSEN,txtWEN,txtDEPTID,txtCodeNo,txtFileRef) {
		 
		if (document.getElementById(cbxID).checked)
		{
			 
			document.getElementById(txtSEN).disabled = false;
			document.getElementById(txtWEN).disabled = false;
			document.getElementById(txtDEPTID).disabled = false;
			document.getElementById(txtCodeNo).disabled = false;
			document.getElementById(txtFileRef).disabled = false;

		}else
		{
			 
			document.getElementById(txtSEN).disabled = true;
			document.getElementById(txtWEN).disabled = true;
			document.getElementById(txtDEPTID).disabled = true;
			document.getElementById(txtCodeNo).disabled = true;
			document.getElementById(txtFileRef).disabled = true;
			
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
						<div id="" style="overflow-x:scroll; height:400px;">
						<table class="tblContent" border="1" cellpadding="0"
							cellspacing="0">
							<thead>
								<tr>
									<th></th>
								</tr>
							</thead>
						
  
							
							<tbody>
								<tr>
									<td><s:form action="change!CodeNo" method="POST"
										validate="true" theme="simple" >
										
										<table width="100%" class="tldFromContent" border="0">
											<tr>
												<td class="listHeader" colspan="3">
												<table width="100%" border="0">
													<tr>
														<td width="30%" class="listHeader">Estimate</td>
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
												<td colspan="3">
												
												<table class="tldFromContent" border="1" >

													<tr style="hight:5px;">
														<td><strong>Select</strong></td>
														<td><strong>Stranded Estimate No</strong></td>
														<td><strong>Work Estimate No</strong></td>
														<td><strong>DEPT ID</strong></td>
														<td><strong>Status</strong></td>
														<td><strong>Code No</strong></td>
														<td></td>
														

													</tr>
													<s:iterator value="estimationRefList">
														<tr>
															<td><s:checkbox
																fieldValue="%{id.workEstimateNo}#*%{id.deptId}#*%{id.standardEstimateNo}"
																name="EstimateReferenceID" id="X1%{id.workEstimateNo}" onclick="EnableTextfield('X1%{id.workEstimateNo}','X2%{id.workEstimateNo}','X3%{id.workEstimateNo}','X4%{id.workEstimateNo}','X5%{id.workEstimateNo}','X6%{id.workEstimateNo}')"   ></s:checkbox>
															</td>
															<td><s:textfield name="id.standardEstimateNo" disabled="true" id="X2%{id.workEstimateNo}" /></td>
															<td><s:textfield name="id.workEstimateNo"  disabled="true" id="X3%{id.workEstimateNo}"  /></td>
															<td><s:textfield name="id.deptId" id="X4%{id.workEstimateNo}"  disabled="true" /></td>
															<td><s:textfield name="status" id="X5%{id.workEstimateNo}" disabled="true"  /></td>
															<td><s:textfield name="fileReference" id="X6%{id.workEstimateNo}" disabled="true"  /></td>
															<td> <s:submit theme="simple" align="right"	value="Update" type="submit" method="Update"></s:submit> </td>
															
														</tr>
													</s:iterator>
												</table>
												
												</td>
											</tr>
											
											<tr>
												<td colspan="3"><%-- <s:submit theme="simple" align="right"
													value="Update" type="submit" method="Update"></s:submit> --%> <s:submit
													theme="simple" align="right" value="Exit" type="submit"
													method="Exit" id="btnClose"></s:submit></td>
											</tr>

										</table>
										


									</s:form></td>
								</tr>

							</tbody>
						
						</table>
					</div>
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
