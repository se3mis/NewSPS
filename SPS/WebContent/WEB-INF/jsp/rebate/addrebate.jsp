<%@ include file="/WEB-INF/jsp/include.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add new contractor</title>
<link rel="stylesheet" type="text/css" href="../../style/style.css" />
<script type="text/javascript" src="../../script/javascript.js"> </script>
<script type="text/javascript" src="../../script/addNewContractor.js"> </script>
<style>
.sam {
	
	
	box-shadow: inset 0 1px 0 #fff;
	font-size: 12px;
	line-height: 24px;
	margin: 30px auto;
	text-align: left;
	width: 800px;
	border: 2px ;   
    background: #dddddd;
    width: 300px;
    border-collapse: collapse;
	
	
}


</style>
<script type="text/javascript">
 

function submitForm()
{
	document.forms["AddRebateForm"].submit();
 
}

function onPriceChange(price)
{
var pri=price.value;
var qty=document.getElementById("rebateQty").value;
if(qty !== null)
	{
		var cost=pri*qty;
		document.getElementById("rebateCost").value = cost;
	}
}

function onQuantityChange(quantity)
{
var qty=quantity.value;
var pri=document.getElementById("unitPrice").value;
var cost=qty*pri;
document.getElementById("rebateCost").value = cost;
alert("Value = "+st+"num two ="+tt+"Multiply = "+cost);
}

function getUnitPrice()
	{
		document.forms[0].action = "addrebate";  

	    document.forms[0].submit(); 	 
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
									<table class="tblWelcome" >
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
									<table class="tblContent" border="1" >
										<thead>
											<tr>
												<th></th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td>
												<s:form action="addrebate" method="POST" validate="true" theme="simple" name="AddRebateForm">
														<table  class="tldFromContent" border="0">
															<tr>
																<td class="listHeader" colspan="9">
																	<table border="0">
																		<tr>
																			<td width="26%" class="listHeader">Rebate
																				Detials</td>
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
																<td width="5%">Estimate No</td>
																<td><s:select list="estimationNoList"
																		name="estimateNo" onchange="getUnitPrice()"></s:select>
																	<%-- <s:textfield name="estimateNo" id="estimateNo"  /> --%></td>
																<td><s:property value="errestimateNo" /></td>
															</tr>
															<%-- <tr>
												<td width="4%"></td>
												<td width="10%">Rev No</td>
												<td><s:textfield name="revNo" id="revNo" value="2" /></td>
												<td><s:property value="errresCd"/></td>	
											</tr> --%>
															<tr>
																<td width="4%"></td>
																<td width="5%">Cost Center</td>
																<td><s:textfield name="deptId" id="deptId" /></td>

															</tr>
															<tr>
																<td width="4%"></td>
																<td width="5%">Res Cd</td>
																<td><s:select list="resocrceCodeList" name="resCd" id="resCd" onchange="getUnitPrice()" method="setunitPrice"></s:select></td>


															</tr>
															<tr>
																<td width="4%"></td>
																<td width="5%">Unit Price</td>
																<td><s:textfield name="unitPrice" id="unitPrice" onChange="onPriceChange(this)"/></td>
																
															</tr>
															<tr>
																<td width="4%"></td>
																<td width="5%">Rebate Quantity</td>
																<td><s:textfield name="rebateQty" id="rebateQty" onChange="onQuantityChange(this)"/></td>
															</tr>
															<tr>
																<td width="4%"></td>
																<td width="5%">Rebate Cost</td>
																<td><s:textfield name="rebateCost" id="rebateCost" /></td>
															</tr>
															<tr>
																<td width="4%"></td>
																<td width="5%">Offcharge Quantity</td>
																<td><s:textfield name="offchargeQty" id="" /></td>
															</tr>
															<tr>
																<td width="4%"></td>
																<td width="5%">Reusable Quantity</td>
																<td><s:textfield name="reusableQty" id="" /></td>
															</tr>

															<tr>
																<td colspan="3">&nbsp;</td>
															</tr>
															<tr>
																<td colspan="3"><s:submit
																		onclick="return addNewContractorValidation()"
																		theme="simple" type="submit" value=" Add Rebate "
																		method="addRebate">
																	</s:submit> <s:submit theme="simple" align="right" value="Exit"
																		type="submit" method="close" id="btnClose"></s:submit>



																</td>


															</tr>
															<!-- ---------------------------------Table------------------------------------->
														<tr>
																<td colspan="3">
																	<table class="sam" border="1" >

																		<tr>
																			
																			<td><strong>Estimate No</strong></td>
																			<td><strong>Cost Center</strong></td>
																			<td><strong>Resource Code</strong></td>
																			<td><strong>Unit Price</strong></td>
																			<td><strong>Offcharge Qty</strong></td>
																			<td><strong>Rebate Cost</strong></td>
																			<td><strong>Rebate Qty</strong></td>
																			<td><strong>Reusable Qty</strong></td>

																		</tr>
																		<s:iterator value="rebateList">
																			<tr>
																				
																				<td><s:property value="id.estimateNo"/></td>
																				<td><s:property value="id.deptId" /></td>
																				<td><s:property value="id.resCd"/></td>
																				<td><s:property value="unitPrice"/></td>
																				<td><s:property value="offchargeQty"/></td>
																				<td><s:property value="rebateCost"/></td>
																				<td><s:property value="rebateQty"/></td>
																				<td><s:property value="reusableQty"/></td>

																			</tr>
																		</s:iterator>
																	</table>

																</td>
															</tr>

																													</table>

														</s:form>
								

														</table>
													
												</td>
											</tr>

									</tbody>
									</table>
								</td>
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
