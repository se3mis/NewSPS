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
	document.forms["AddCostCenterDetailsForm"].submit();
 
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
									<td><s:form action="CostCenterDetails" method="POST"
										validate="true" theme="simple" name="AddCostCenterDetailsForm">
										<table width="100%" class="tldFromContent" border="0">
											<tr>
												<td class="listHeader" colspan="9">
												<table width="118%" border="0">
													<tr>
														<td width="26%" class="listHeader" >Cost Center
														Detials</td>
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
												<td width="18%">Cost Center Full Name<font color="red">*</font></td>
												<td width="1%">:</td>
												<td width="81%"> <s:textfield id="depFullName" name="depFullName" size="50" ></s:textfield> </td>
											</tr>
											<tr>
												<td>Cost Center Address<font color="red">*</font></td>
												<td>:</td>
												<td><s:textarea id="depAddress" name="depAddress" rows="5" cols="50"   ></s:textarea> </td>
											</tr>
											<tr>
											  <td>Cost Center Telephone<font color="red">*</font></td>
											  <td>:</td>
											  <td><s:textfield id="depTelephone" name="depTelephone" maxLength="10" onblur="numericValidate(this,event)"  onkeyup="numericValidate(this,event)"  ></s:textfield> </td>
										  </tr>
											<tr>
												<td>Region<font color="red">*</font></td>
												<td>:</td>
												<td> <s:select list="regionList" name="selectedRegion" ></s:select>    </td>
											</tr>
											<tr>
												<td>Province<font color="red">*</font></td>
												<td>:</td>
												<td> <s:select list="provinceList" name="selectedProvince" headerKey="-1" listValue="%{ProvinceName}"
													listKey="ProvinceCode" emptyOption="false" ></s:select>    </td>
											</tr>
											<tr>
												<td>Area<font color="red">*</font></td>
												<td>:</td>
												<td>
												
												<s:textfield id="selectedArea" name="selectedArea"   size="50"   ></s:textfield> 
												    </td>
											</tr>
											<tr>
												<td>Cost Center Type<font color="red">*</font></td>
												<td>:</td>
												<td>  <s:select list="deptTypeList" name="selectedDeptType" ></s:select>    </td>
											</tr>
											<tr>
												<td>Cost Center ID</td>
												<td>:</td>
												<td>  <s:property value="costCenter" />  </td>
											</tr>
											<tr>
												<td>Bulk Supplier Name<font color="red">*</font></td>
												<td>:</td>
												<td><s:textfield id="bulkName" name="bulkName"   size="50"   ></s:textfield>  </td>
											</tr>
											
											<tr>
												<td>Bulk Supplier Address<font color="red">*</font></td>
												<td>:</td>
												<td> <s:textarea id="bulkAddress" name="bulkAddress"  rows="5" cols="50"     ></s:textarea>   </td>
											</tr>
											
											<tr>
												<td>Bulk Supplier Telephone<font color="red">*</font></td>
												<td>:</td>
												<td> <s:textfield id="bulkTelephone" name="bulkTelephone" maxLength="10" onblur="numericValidate(this,event)"  onkeyup="numericValidate(this,event)"   ></s:textfield> </td>
											</tr>
											<tr>
												<td>Branch Code<font color="red">*</font></td>
												<td>:</td>
												<td> <s:textfield id="branchCode" name="branchCode" size="50"  ></s:textfield>  </td>
											</tr>
											<tr>
												<td>Bank Code<font color="red">*</font></td>
												<td>:</td>
												<td>  <s:textfield id="bankCode" name="bankCode" size="50"  ></s:textfield> </td>
											</tr>
											<tr>
												<td>PosA<font color="red">*</font></td>
												<td>:</td>
												<td>  <s:textfield id="posA" name="posA" size="50"  ></s:textfield> </td>
											</tr>
											<tr>
												<td>Pos Cenre<font color="red">*</font></td>
												<td>:</td>
												<td>  <s:textfield id="posCentre" name="posCentre" size="50"  ></s:textfield> </td>
											</tr>
											
											 
											<tr>
											  <td colspan="3">&nbsp;</td>
										  </tr>
											<tr>
												<td colspan="3"><s:submit onclick="return addCostCenterDetials()" theme="simple" type="submit"
													value="Add/Update Cost Center" method="AddCostCenterDetials"  >
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
