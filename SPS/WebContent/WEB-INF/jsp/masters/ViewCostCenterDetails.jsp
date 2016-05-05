<%@ include file="/WEB-INF/jsp/include.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Cost Centre Details</title>
<link rel="stylesheet" type="text/css" href="../../style/style.css" />
 
<script type="text/javascript" src="../../script/javascript.js"> </script>
 <script type="text/javascript" src="../../script/addCostCenterDetials.js"> </script>
 
<script type="text/javascript">
 

function submitForm()
{
	document.forms["ViewCostCenterDetails"].submit();
 
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
									<td><s:form action="ViewCostCenterDetails" method="POST"
										validate="true" theme="simple" name="ViewCostCenterDetails">
										<table width="100%" class="tldFromContent" border="0">
											<tr>
												<td class="listHeader" colspan="9">
												<table width="118%" border="0">
													<tr>
														<td width="26%" class="listHeader" >Cost Centre
														Details</td>
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
												<td width="18%">Cost Center Full Name</td>
												<td width="1%">:</td>
												<td width="81%"> <s:property  value="depFullName"  /> </td>
											</tr>
											<tr>
												<td>Cost Center Address</td>
												<td>:</td>
												<td><s:property   value="depAddress"   /> </td>
											</tr>
											<tr>
											  <td>Cost Center Telephone</td>
											  <td>:</td>
											  <td><s:property   value="depTelephone"   />  </td>
										  </tr>
											<tr>
												<td>Region</td>
												<td>:</td>
												<td> <s:property   value="selectedRegion"/>   </td>
											</tr>
											<tr>
												<td>Province</td>
												<td>:</td>
												<td> <s:property   value="selectedProvince"  />    </td>
											</tr>
											<tr>
												<td>Area</td>
												<td>:</td>
												<td> <s:property value="selectedArea"  />    </td>
											</tr>
											<tr>
												<td>Cost Center Type</td>
												<td>:</td>
												<td>  <s:property  value="selectedDeptType"  />    </td>
											</tr>
											<tr>
												<td>Cost Center ID</td>
												<td>:</td>
												<td>  <s:property value="costCenter" />  </td>
											</tr>
											<tr>
												<td>Bulk Supplier Name</td>
												<td>:</td>
												<td><s:property   value="bulkName"     />  </td>
											</tr>
											
											<tr>
												<td>Bulk Supplier Address</td>
												<td>:</td>
												<td> <s:property  value="bulkAddress"       />   </td>
											</tr>
											
											<tr>
												<td>Bulk Supplier Telephone</td>
												<td>:</td>
												<td> <s:property   value="bulkTelephone"    /> </td>
											</tr>
											<tr>
												<td>Branch Code</td>
												<td>:</td>
												<td> <s:property   value="branchCode"   />  </td>
											</tr>
											<tr>
												<td>Bank Code</td>
												<td>:</td>
												<td>  <s:property  value="bankCode"   /> </td>
											</tr>
											<tr>
												<td>PosA</td>
												<td>:</td>
												<td>  <s:property  value="posA"   /> </td>
											</tr>
											<tr>
												<td>Pos Cenre</td>
												<td>:</td>
												<td>  <s:property   value="posCentre"    /> </td>
											</tr>
											
											 
											<tr>
											  <td colspan="3">&nbsp;</td>
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
