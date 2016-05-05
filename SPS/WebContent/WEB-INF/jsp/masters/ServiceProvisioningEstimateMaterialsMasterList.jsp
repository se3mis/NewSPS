<%@ include file="/WEB-INF/jsp/include.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Service Provisioning Estimate Materials Master Details</title>
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
									<td><s:form action="loadSPESTMTMageList" method="POST"
										validate="true" theme="simple" name="SPCostForm">
										<table width="100%" class="tldFromContent" border="0">
											<tr>
												<td class="listHeader" colspan="9">
												<table width="118%" border="0">
													<tr>
														<td width="30%" class="listHeader" >Materials Master Details List</td>
														<td width="70%"> <s:label name="lblSuccess" id="lblSuccess" cssClass="STATUSMSG"></s:label>
														<s:label name="lblError" id="lblError" cssClass="STATUSMSGERR"></s:label></td>
													</tr>
												</table>
												</td>

											</tr>

										
											<tr>
											<td colspan="3"> &nbsp;</td>												 
											</tr>
											<tr>
											<td colspan="3"> 
												 &nbsp;</td>
												 
											</tr>
											
											<tr>
												<td> <b>Single Phase, 30 A, Over Head </b> </td>
												<td> <b>Three Phase, 30 A, Over Head </b> </td>
												<td> <b>Three Phase, 60 A, Over Head </b> </td>
											</tr>
											
											<tr>
												<td> 
												 <table class="tldFromContent" border="1">

													<tr bgcolor="#93C9FF">
													
													 												 
														<td><strong>Material Code</strong></td>
														<td><strong>Material Qty</strong></td>
														 
														 
													</tr>
													<s:iterator value="single30OH">
														<tr bgcolor="#CCCCCC">
													 
														<td> <s:property  value="id.matCd" /></td>
														<td> <s:property  value="matQty" /></td>
														
															 
															 
														</tr>
													</s:iterator>
												</table>
												</td>
												<td> 
												 <table class="tldFromContent" border="1">

													<tr bgcolor="#93C9FF">
													
													<td><strong>Material Code</strong></td>
														<td><strong>Material Qty</strong></td>
														 
													</tr>
													<s:iterator value="three30OH">
														<tr tr bgcolor="#CCCCCC">
														 
														<td> <s:property  value="id.matCd" /></td>
														<td> <s:property  value="matQty" /></td>
														
															 
															 
														</tr>
													</s:iterator>
												</table>
												</td>
												<td> 
												 <table class="tldFromContent" border="1">

													<tr bgcolor="#93C9FF">
													
												 												 
														<td><strong>Mat Code</strong></td>
														<td><strong>Mat Qty</strong></td>
														 
														 
													</tr>
													<s:iterator value="three60OH">
														<tr  bgcolor="#CCCCCC">
														 
														<td> <s:property  value="id.matCd" /></td>
														<td> <s:property  value="matQty" /></td>
														
															 
															 
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
												<td> <b> Single Phase, 30 A, Under Ground </b></td>
												<td><b> Three Phase, 30 A, Under Ground </b> </td>
												<td><b> Three Phase, 60 A, Under Ground </b> </td>
											</tr>
											
											<tr>
												<td> 
												 <table class="tldFromContent" border="1">

													<tr bgcolor="#93C9FF">
													
													<td><strong>Material Code</strong></td>
														<td><strong>Material Qty</strong></td>
														 
														 
													</tr>
													<s:iterator value="single30UG">
														<tr  bgcolor="#CCCCCC">
														 
														<td> <s:property  value="id.matCd" /></td>
														<td> <s:property  value="matQty" /></td>
														
															 
															 
														</tr>
													</s:iterator>
												</table>
												</td>
												<td> 
												 <table class="tldFromContent" border="1">

													<tr bgcolor="#93C9FF">
													
													<td><strong>Material Code</strong></td>
														<td><strong>Material Qty</strong></td>
														 
													</tr>
													<s:iterator value="three30UG">
														<tr  bgcolor="#CCCCCC">
														 
														<td> <s:property  value="id.matCd" /></td>
														<td> <s:property  value="matQty" /></td>
														
															 
															 
														</tr>
													</s:iterator>
												</table>
												</td>
												<td> 
												 <table class="tldFromContent" border="1">

													<tr bgcolor="#93C9FF">
													
													<td><strong>Material Code</strong></td>
														<td><strong>Material Qty</strong></td>
														 
													</tr>
													<s:iterator value="three60UG">
														<tr  bgcolor="#CCCCCC">
														 
														<td> <s:property  value="id.matCd" /></td>
														<td> <s:property  value="matQty" /></td>
														
															 
															 
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
												<td><b>Single Phase, 15 A, Under Ground </b> </td>
												<td>&nbsp;</td>
												<td>&nbsp;</td>
											</tr>
											
											<tr>
												<td> 
												 <table class="tldFromContent" border="1">

													<tr bgcolor="#93C9FF">
													
												<td><strong>Material Code</strong></td>
														<td><strong>Material Qty</strong></td>
														 
													</tr>
													<s:iterator value="single15UG">
														<tr  bgcolor="#CCCCCC">
														<td> <s:property  value="id.matCd" /></td>
														<td> <s:property  value="matQty" /></td>
														
															 
															 
														</tr>
													</s:iterator>
												</table>
												</td>
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

								<tr>
									<td>&nbsp;</td>
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
