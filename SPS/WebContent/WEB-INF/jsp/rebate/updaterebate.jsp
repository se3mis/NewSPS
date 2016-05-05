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
	document.forms["AddRebateForm"].submit();
 
}

function onPriceChange(price)
{
	
	var quantity=$("#rebateQty").val();
	
	var cost=(price.value*quantity);
	
	document.getElementById("rebateCost").value = cost;

}

function onQuantityChange(quantity)
{
	
	var price=$("#unitPrice").val();
	
		var cost=(price.value*quantity.value);
		
		document.getElementById("rebateCost").value = cost;
		
}
function EnableTextfield(cbxID, txtEstNo,txtDEPTID,txtReCode,txtUniPrice,txtOffQty,txtReCost,txtReQty,txtReusableQty) {
	 
	if (document.getElementById(cbxID).checked)
	{
		 
		document.getElementById(txtEstNo).disabled = false;		
		document.getElementById(txtDEPTID).disabled = false;
		document.getElementById(txtReCode).disabled = false;
		document.getElementById(txtUniPrice).disabled = false;
		document.getElementById(txtOffQty).disabled = false;
		document.getElementById(txtReCost).disabled = false;
		document.getElementById(txtReQty).disabled = false;
		document.getElementById(txtReusableQty).disabled = false;

	}else
	{
		 
		document.getElementById(txtEstNo).disabled = true;		
		document.getElementById(txtDEPTID).disabled = true;
		document.getElementById(txtReCode).disabled = true;
		document.getElementById(txtUniPrice).disabled = true;
		document.getElementById(txtOffQty).disabled = true;
		document.getElementById(txtReCost).disabled = true;
		document.getElementById(txtReQty).disabled = true;
		document.getElementById(txtReusableQty).disabled = true;
		
	}

	 
}

function getUnitPrice()
{
	document.forms[0].action = "updaterebate";  

    document.forms[0].submit(); 	 
}


</script>
<s:head />
<sx:head />
</head>
<body onLoad="buttonLoader()">
	<table id="tblOuter" class="tblHeader">
		<tbody>
			<tr>
				<td>
					<table id="tblInner" class="tblHeader">
						<tbody>
							
							<tr>
								<td id="tdHeader">
									<table id="tblHeader" class="tblHeader" >
										<tbody>
											<tr>
												<td><img src="../../image/spsn.png" width="100%"
													alt="NewSPS2" /></td>
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
												<td><s:form action="updaterebate" method="POST"
														validate="true" theme="simple" >
														<table width="100%" class="tldFromContent" border="0">
															<tr>
																<td class="listHeader" colspan="9">
																	<table width="100%" border="0">
																		<tr>																			
																			<td width="74%"><s:label name="lblSuccess"
																					id="lblSuccess" cssClass="STATUSMSG"></s:label> <s:label
																					name="lblError" id="lblError"
																					cssClass="STATUSMSGERR"></s:label></td>
																		</tr>
																	</table>
																</td>

															</tr>
																<tr>
																<td width="4%"></td>
																<td >Estimate No :&nbsp;
																<s:select list="estimationNoList"
																		name="estimateNo" onchange="getUnitPrice()"></s:select>
																	<%-- <s:textfield name="estimateNo" id="estimateNo"  /> --%></td>
																<td></td>
																<td></td>
																<td></td>
																<td></td>
															</tr>

																				<!-- ---------------------------------Table------------------------------------->

															<tr>
																<td colspan="3">
																	<table class="tldFromContent" border="1" width="800px">

																		<tr>
																			<td><strong>Select</strong></td>
																			<td width="10px"><strong>Estimate No</strong></td>
																			<td width="10px"><strong>Cost Center</strong></td>
																			<td width="10px"><strong>Resource Code</strong></td>
																			<td width="10px"><strong>Unit Price</strong></td>
																			<td width="10px"><strong>Offcharge Qty</strong></td>
																			<td width="10px"><strong>Rebate Cost</strong></td>
																			<td width="10px"><strong>Rebate Qty</strong></td>
																			<td width="10px"><strong>Reusable Qty</strong></td>

																		</tr>
																		<s:iterator value="rebateList">
																			<tr>
																				<td><s:checkbox
																						fieldValue="%{id.estimateNo}#*%{id.deptId}#*%{id.resCd}"
																						name="SprebateID" id="X1%{id.resCd}"
																						onclick="EnableTextfield('X1%{id.resCd}','X2%{id.resCd}','X3%{id.resCd}','X4%{id.resCd}','X5%{id.resCd}','X6%{id.resCd}','X7%{id.resCd}','X8%{id.resCd}','X9%{id.resCd}','X10%{id.resCd}')"></s:checkbox>
																				</td>
																				<td><s:textfield name="id.estimateNo"  id="X2%{id.resCd}" disabled="true" /></td>
																				<td><s:textfield name="id.deptId"  id="X3%{id.resCd}" disabled="true" /></td>
																				<td><s:textfield name="id.resCd"  id="X4%{id.resCd}" disabled="true" /></td>
																				<td><s:textfield name="unitPrice"  id="X5%{id.resCd}" disabled="true" /></td>
																				<td><s:textfield name="offchargeQty"  id="X6%{id.resCd}" disabled="true" /></td>
																				<td><s:textfield name="rebateCost"  id="X7%{id.resCd}" disabled="true" /></td>
																				<td><s:textfield name="rebateQty"  id="X8%{id.resCd}" disabled="true" /></td>
																				<td><s:textfield name="reusableQty"  id="X9%{id.resCd}" disabled="true" /></td>

																			</tr>
																		</s:iterator>
																	</table>

																</td>
															</tr>

															<tr>
																<td colspan="3"><s:submit theme="simple"
																		align="right" value="Update" method="Updaterebate"></s:submit>
																	<s:submit theme="simple" align="right" value=" Exit "
																		method="close" id="btnClose"></s:submit></td>
															</tr>
														</table>


														<!-- ---------------------------------Table------------------------------------->


													</s:form></td>
											</tr>
											<tr>
								<td id="tdFooter">
									<table class="tblFooter" border="1" >

										<tbody>
											<tr align="Center">
												<td id="tdFooter">
													<table class="tblFooter" border="1">

														<tbody>
															<tr>
																<td class="footerNotice" align="center">@ Ceylon
																	Electricity Board</td>
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
