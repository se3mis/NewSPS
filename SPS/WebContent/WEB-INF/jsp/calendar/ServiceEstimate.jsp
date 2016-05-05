<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/jsp/include.jsp"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Service Estimation</title>
<link rel="stylesheet" type="text/css" href="../../style/style.css" />
<script type="text/javascript" src="../../script/serviceEstimate.js"> </script>
<script type="text/javascript" src="../../script/javascript.js"> </script>

<s:head /> 
</head>
<body onload="on_Load()">
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
                                                    <td width="100%" height="70px"><img src="../../image/NewSPS2.gif" width="100%"  alt="NewSPS2"/>
                                                    </td>
                                                    <td width="*" bgcolor="#000033"></td>
                                                    <td width="*" bgcolor="#000033"></td>
                                     </tr>
								
							</tbody>
						</table>
						</td>
					</tr>					
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
									<td class="tdLogOut"> <a href="<s:url action="logout"/>">Logout</a></td>
								</tr>
							</tbody>
						</table>
						</td>
					</tr>					
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
									<td><s:form action="serviceEstimate" method="POST"
										validate="true" theme="simple">
										<table width="100%" class="tblContent" border="0">
											<thead>
												<tr>
													<th></th>

												</tr>
											</thead>
											<tbody>
												<tr>
													<td class="tdHeadings" colspan="4">CEYLON ELECTRICITY
													BOARD - Service Estimation</td>
												</tr>
												<tr>
													<td></td>
												</tr>
												<tr>
													<td></td>
												</tr>
															<tr>
																
																<td align="center"><s:label name="statusMsg"
																	id="statusMsg" cssClass="STATUSMSG"></s:label></td>
															</tr>
															<tr>
																
																<td align="center"><s:label name="statusMsgErr"
																	id="statusMsgErr" cssClass="STATUSMSGERR"></s:label></td>

															</tr>

												<tr>
													<td>
													<table width="100%" class="tblContent" border="0">
														<thead>
															<tr>
																<th width="2%"></th>
																<th width="10%"></th>
																<th width="5%"></th>
																<th width="10%"></th>
																<th width="10%"></th>
																<th width="5%"></th>
															</tr>
														</thead>
														<tbody>
															<tr>
																<td>&nbsp;</td>
															</tr>
															<tr>
																<td class="tdHeadings" colspan="4">Service
																Estimation Form</td>

																<td></td>
															</tr>
															
															<tr>
																<td>&nbsp;</td>
															</tr>
															<tr>
																<td></td>
																<td><s:label value="Name (with initials)"></s:label></td>
																<td><s:textfield name="appName" id="txfdappName"
																	size="20" readonly="true"></s:textfield></td>
																<td><s:label value="Area"></s:label></td>
																<td><s:textfield name="appArea" id="txfdappArea"
																	size="20" readonly="true"></s:textfield></td>
																<td></td>
															</tr>
															<tr>
																<td>&nbsp;</td>
															</tr>
															<tr>
																<td></td>
																<td><s:label value="Address"></s:label></td>
																<td><s:textarea name="appAddress"
																	cssClass="serviceEst" id="txfdappAddress" rows="2"
																	cols="20" readonly="true"></s:textarea></td>
																<td><s:label value="Cost Center"></s:label></td>
																<td><s:textfield name="appCSC" id="txfdappCSC"
																	size="10" readonly="true"></s:textfield></td>
																<td></td>
															</tr>
															<tr>
																<td>&nbsp;</td>
															</tr>
															<tr>
																<td></td>
																<td><s:label value="Telephone No"></s:label></td>
																<td><s:textfield name="appTphone"
																	id="txfdappTphone" size="20" readonly="true"></s:textfield></td>
																<td><s:label value="App No"></s:label></td>
																<td><s:textfield name="appNo" id="txfdappNo"
																	size="20"></s:textfield></td>
																<td><s:submit theme="simple" align="right"
																	value="Find" method="viewApplicantDetails"
																	id="btnFindApplicationNo"
																	onclick="return checkAppNoNotNull()"></s:submit></td>

															</tr>
															<tr>
																<td>&nbsp;</td>
															</tr>
															<tr>
																<td></td>
																<td><s:label value="Service Type"></s:label></td>
																<td><s:textfield name="appSerType"
																	id="txfdappSerType" size="10" readonly="true"></s:textfield></td>															
																<td><s:label value="Neighbors Account"></s:label></td>
																<td><s:textfield name="neighborsAcct"
																	id="txfdneighborsAcct" size="20" readonly="true"></s:textfield></td>
																<td></td>
																<td></td>
															</tr>
															<tr>
																<td>&nbsp;</td>
															</tr>
															<tr>
																<td></td>
																<td><s:label value="Tariff Category Code"></s:label></td>
																<td><s:textfield name="appTariff"
																	id="txfdappTariff" size="10" readonly="true"></s:textfield>
																</td>
																<td><s:label value="Tarrif Code"></s:label></td>
																<td><s:textfield name="tarrifCode"
																	id="txfdTarrifCode" size="20" readonly="true"></s:textfield></td>
																<td></td>
															</tr>
															<tr>
																<td>&nbsp;</td>
															</tr>
															<tr>
																<td></td>
																<td><s:label value="Connection Type"></s:label></td>
																<td><s:textfield name="connectionType"
																	id="txfdconnectionType" size="10" readonly="true"></s:textfield>
																</td>
																
															</tr>
															<tr>
																<td>&nbsp;</td>
															</tr>
															<tr>

																<td class="tdHeadings" colspan="4">Sketch</td>

															</tr>
														</tbody>
													</table>
													</td>
												</tr>
												<tr>
													<td class="tdHeadings" colspan="4">Poles</td>
												</tr>
												<tr>
													<td>

													<table width="60%" class="tldFromContent" border="1">
														<tbody>
															<tr>
																<td style="font-weight: bold;"><s:label
																	value="Category"></s:label></td>
																<td style="font-weight: bold;"><s:label
																	value="Item"></s:label></td>
																<td style="font-weight: bold;"><s:label
																	value="PoleType"></s:label></td>
																<td style="font-weight: bold;"><s:label
																	value="FromCon"></s:label></td>
																<td style="font-weight: bold;"><s:label
																	value="ToCon"></s:label></td>
																<td style="font-weight: bold;"><s:label
																	value="Pointer"></s:label></td>
																<td style="font-weight: bold;"><s:label
																	value="Quantity"></s:label></td>
																<td style="font-weight: bold;"><s:label value="Add"></s:label></td>
															</tr>


															<tr>
																<td><s:label id="lblExcavation" value="Poles"></s:label></td>

																<td><s:select headerKey="-1"
																	headerValue="Select an option"
																	list="excavationDetailsList" id="excavationDetailsList"
																	name="excavationType" listValue="%{value}"
																	listKey="key" emptyOption="false" disabled="true" />
																</td>
																<td><s:select headerKey="-1"
																	headerValue="Select an option" list="poleTypeList"
																	id="poleTypeList" name="poleType" emptyOption="false"
																	disabled="true" />
																</td>
																<td><s:select headerKey="-1"
																	headerValue="Select an option" list="conductorList"
																	id="fromConductorList" name="fromConductor"
																	emptyOption="false" disabled="true" /></td>
																<td><s:select headerKey="-1"
																	headerValue="Select an option" list="conductorList"
																	id="toConductorList" name="toConductor"
																	emptyOption="false" disabled="true" />
																</td>
																<td><s:select headerKey="-1"
																	headerValue="Select an option" list="polePointList"
																	id="polePointList" name="polePointer"
																	emptyOption="false" disabled="true" />
																</td>
																<td><s:textfield name="nofPoles" id="nofPoles"
																	size="8" onkeypress="return onlyNumbers(event)"
																	readonly="true"></s:textfield></td>
																<td><s:submit value="Add" id="excavationAmountAdd"
																	disabled="true"
																	onclick="return setPoleDetailsToTable('excavationAmountAdd')"></s:submit></td>

															</tr>


														</tbody>
													</table>
													</td>
												</tr>
												<tr>
													<td></td>
												</tr>
												<tr>
													<td class="tdHeadings" colspan="4">Other Materials and Service Distance</td>
												</tr>
												<tr>
													<td>
													<table width="60%" class="tldFromContent" border="1">
														<tbody>
															<tr>
																<td style="font-weight: bold;"><s:label
																	value="Category"></s:label></td>
																<td style="font-weight: bold;"><s:label
																	value="Item"></s:label></td>
																<td style="font-weight: bold;"><s:label
																	value="Type"></s:label></td>
																<td style="font-weight: bold;"><s:label
																	value="Quantity"></s:label></td>
																<td style="font-weight: bold;"><s:label
																	value="Description"></s:label></td>
																<td style="font-weight: bold;"><s:label value="Add"></s:label></td>
															</tr>



															<tr>
																<td><s:label id="lblErection" value="Struts"></s:label></td>
																<td><s:select headerKey="-1"
																	headerValue="Select an option"
																	list="erectionDetailsList" id="erectionDetailsList"
																	name="erectionType" listValue="%{value}" listKey="key"
																	emptyOption="false" disabled="true" />
																</td>
																<td></td>
																<td><s:textfield name="noferectionPoles"
																	id="noferectionPoles" size="8"
																	onkeypress="return onlyNumbers(event)" readonly="true"></s:textfield></td>
																<td><s:textfield name="erectionPolesnDesc"
																	id="erectionPolesDesc" size="25" readonly="true"></s:textfield></td>
																<td><s:submit value="Add" id="erectionAmountAdd"
																	disabled="true"
																	onclick="return setDetailsToTable('Excavation...of Struts','erectionDetailsList','noferectionPoles','erectionPolesDesc','struts','erectionAmountAdd')"></s:submit></td>

																<td></td>
															</tr>

															<tr>
																<td><s:label id="lblStaysDetails" value="Stays"></s:label></td>
																<td><s:select headerKey="-1"
																	headerValue="Select an option"
																	list="insStaysDetailsList" id="insStaysDetailsList"
																	name="insStaysType" listValue="%{value}" listKey="key"
																	emptyOption="false" disabled="true" />
																</td>
																<td><s:select headerKey="-1"
																	headerValue="Select an option"
																	list="insStaysTypeList" id="insStaysTypeList"	name="stayControlType"																
																	emptyOption="false" disabled="true" />
																</td>
																<td><s:textfield name="nofExacErec"
																	id="nofExacErec" size="8"
																	onkeypress="return onlyNumbers(event)" readonly="true"></s:textfield></td>
																<td><s:textfield name="ExacErecDesc"
																	id="ExacErecDesc" size="25" readonly="true"></s:textfield></td>
																<td><s:submit value="Add" id="insStaysAmountAdd"
																	disabled="true"
																	onclick="return setStayDetailsToTable('insStaysDetailsList','nofExacErec','ExacErecDesc','stay','insStaysAmountAdd','insStaysTypeList')"></s:submit></td>


																<td></td>
															</tr>
															<tr>
																<td><s:label id="lblStrAlmPVC" value="Service Wire"></s:label></td>
																<td><s:select list="wireDetailsList"
																	id="wireDetailsList" name="wireType"
																	listValue="%{value}" listKey="key"
																	onchange="showText('wireDetailsList','lblnofAddWireSpan','nofAddWireSpan')"
																	emptyOption="false" disabled="true" />
																</td>
																<td></td>
																<td><s:textfield name="nofAddWireSpan"
																	id="nofAddWireSpan" size="8" readonly="true"></s:textfield></td>
																<td><s:textfield name="nofAddWireSpanDesc"
																	id="nofAddWireSpanDesc" size="25" readonly="true"></s:textfield></td>
																<td><s:submit value="Add" disabled="true"
																	id="wireAmountAdd"
																	onclick="return setServiceLengthDetailsToTable('wireDetailsList','nofAddWireSpan','nofAddWireSpanDesc','wire type','wireAmountAdd')"></s:submit></td>

																<td></td>
															</tr>
															<tr>
																<td><s:label id="lblStrAlFly"
																	value="Bare Conductor"></s:label></td>
																<td><s:select headerKey="-1"
																	headerValue="Select an option"
																	list="conductorDetailsList" id="conductorDetailsList"
																	name="conductorType" listValue="%{value}" listKey="key"
																	emptyOption="false" disabled="true" />
																</td>
																<td></td>
																<td><s:textfield name="noOfconductor"
																	id="noOfconductor" size="8" readonly="true"></s:textfield></td>
																<td><s:textfield name="conductorAmountDesc"
																	id="conductorAmountDesc" size="25" readonly="true"></s:textfield></td>
																<td><s:submit disabled="true" value="Add"
																	id="conductorAmountAdd"
																	onclick="return setServiceLengthDetailsToTable('conductorDetailsList','noOfconductor','conductorAmountDesc','wire type','conductorAmountAdd')"></s:submit></td>

																<td></td>
															</tr>
															<tr>
																<td><s:label id="lblnoOfSpans" value="No of Spans"></s:label></td>
																<td><s:textfield name="noOfSpans" id="noOfSpans" maxlength="3"
																	size="8" readonly="true" onkeypress="return onlyNumbers(event)"></s:textfield></td>
																<td></td>
																<td></td>
																<td></td>
																<td></td>
															</tr>
															<tr>
																<td><s:label id="lblDistancetoServicePlace" value="Distance to service place (km)"></s:label></td>
																<td><s:textfield name="distancetoServicePlace" id="txtdistancetoServicePlace"
																	size="8" readonly="true" maxlength="5" onkeypress="return restrictToTwoDecimalPossition(this,event);" onblur="return setDecimalPoints(this);"></s:textfield></td>
																<td></td>
																<td></td>
																<td></td>
																<td></td>
															</tr>

														</tbody>
													</table>
													</td>
												</tr>

												<tr>
													<td>
													<table width="100%" class="tldFromContent" border="0">
														<thead>
															<tr>
																<th width="2%"></th>
																<th width="15%"></th>
																<th width="15%"></th>
																<th width="10%"></th>
																<th width="10%"></th>
																<th width="5%"></th>
															</tr>
														</thead>
														<tbody>
															<tr>
																<td class="tdHeadings" colspan="4">Technical
																Details</td>
															</tr>
															<tr>
																<td></td>
															</tr>
															<tr>
																<td></td>
																<td><s:label value="Substation"></s:label></td>
																<td><s:textfield name="substation"
																	id="txfdsubstation" size="20" readonly="true"></s:textfield></td>
																<td><s:label value="SIN"></s:label></td>
																<td><s:textfield name="sin" id="txfdsin" size="20" maxlength="4"
																	readonly="true"></s:textfield></td>
																<td></td>
															</tr>
															<tr>
																<td></td>
															</tr>
															<tr>
																<td></td>
																<td><s:label value="Distance from S/S"></s:label></td>
																<td><s:textfield name="distanceFrmSS"
																	id="txfddistanceFrmSS" size="20" readonly="true" onkeypress="return restrictToTwoDecimalPossition(this,event);"></s:textfield></td>
																<td><s:label value="Phase"></s:label></td>
																<td><s:textfield name="phase" id="txfdphase"
																	size="20" readonly="true"></s:textfield></td>
																<td></td>
															</tr>
															<tr>
																<td></td>
															</tr>
															<tr>
																<td></td>
																<td><s:label value="Transformer Capacity (KVA)"></s:label></td>
																<td><s:textfield name="transformerCapacity"
																	id="txfdtransformerCapacity" size="20" readonly="true" onkeypress="return onlyNumbers(event)"></s:textfield></td>
																<td><s:label value="Pole No"></s:label></td>
																<td><s:textfield name="poleNo" id="txfdpoleNo"
																	size="20" readonly="true" onkeypress="return onlyNumbers(event)"></s:textfield></td>
																<td></td>
															</tr>
															<tr>
																<td></td>
															</tr>
															<tr>
																<td></td>
																<td><s:label value="Transformer Load (KVA)*"></s:label></td>
																<td><s:textfield name="transformerLoad"
																	id="txfdtransformerLoad" onkeypress="return onlyNumbers(event)" size="20" readonly="true"></s:textfield></td>
																<td></td>
																<td></td>
																<td></td>
															</tr>
															<tr>
																<td></td>
															</tr>
															<tr>
																<td></td>
																<td><s:label value="Transformer Peak Load (KVA)*"></s:label></td>
																<td><s:textfield name="transformerPeakLoad"
																	id="txfdtransformerPeakLoad" size="20" readonly="true" onkeypress="return onlyNumbers(event)"></s:textfield></td>
																<td><s:label value="Total Length"></s:label></td>
																<td><s:textfield name="totalLength"
																	id="txfdttotalLength" size="20"
																	onkeypress="return onlyNumbers(event)" readonly="true"></s:textfield></td>

																<td></td>
															</tr>
															<tr>
																<td></td>
															</tr>
															<tr>
																<td></td>
																<td><s:label value="Feeder Control Type"></s:label></td>
																<td><s:textfield name="feederControltype"
																	id="txfdfeederControltype" size="20" readonly="true"></s:textfield></td>
																<td></td>
																<td></td>
																<td></td>
															</tr>
																<tr>
																<td></td>
															</tr>
															<tr>
																<td></td>
																<td><s:label value="Is Loop"></s:label></td>
																<td><s:radio name="isLoopType" id="RadioisLoopType"
																	list="#{'Y':'Yes','N':'No'}" onchange="setCableType()" /></td>
																<td></td>
															</tr>
															<tr>
																<td></td>
															</tr>
															<tr>
																<td></td>
																<td><s:label value="Wire Type"></s:label></td>
																<td><s:radio name="wiringType" id="RadiowireType"
																	list="#{'OH':'Overheard','UG':'Underground'}" onchange="setCableType()"/></td>
																<td style="visibility: hidden;" id="tdLableCableType"><s:label value="Cable Type"></s:label></td>
																<td style="visibility: hidden;" id="tdListCableType"><s:select headerKey="-1"
																	headerValue="Select an option"
																	list="cableTypeList" id="cableTypeList"
																	name="cableType" listValue="%{value}" listKey="key"
																	emptyOption="false" />
																</td>
															</tr>
															<s:if test="%{costCenterNo == '514.20'}">
															<tr>
																<td></td>
																<td><s:label value="Is Service Conversion"></s:label></td>
																<td><s:radio name="isServiceConversion" id="RadioIsServiceConversion" list="#{'0P_00A':'NO','3P-30A':'3P-30A','3P-60A':'3P-60A'}" onchange=""/></td>
																<td></td>
																<td></td>
															</tr>
															</s:if>
															<tr>
																<td></td>
																<td id="tdLableIsConversion"><s:label value="Is Conversion"></s:label></td>
																<td id="tdRadioIsConversion"><s:radio name="isConversion" id="RadiowireisConversion"
																	list="#{'Y':'Yes','N':'No'}" onchange="setConversionLength()"  value="N"/></td>
																<td style="visibility: hidden;" id="tdLableConversionLength"><s:label value="Line Conversion Length"></s:label></td>
																<td style="visibility: hidden;" id="tdtxtConversionlength"><s:textfield name="conversionLength"
																	id="txtConversionlength" size="20" onkeypress="return restrictToTwoDecimalPossition(this,event);"  readonly="true"></s:textfield>
																</td>
															</tr>
														</tbody>
													</table>
													</td>
												</tr>
												<tr>
													<td class="tdHeadings" colspan="4">Pole Details</td>
												</tr>
												<tr>
													<td>
													<table class="tblList" border="1" cellpadding="0"
														cellspacing="0">
														<thead>
															<tr>
																<th></th>
															</tr>
														</thead>
														<tbody>
															<tr>
																<td>


																<table id="list_table1" width="95%" align="center"
																	border="1"
																	style="border-color: #000033; border-collapse: collapse">
																	<tr class="listColumnHeadingStyle">
																		<td align="center" width="200px">CategoryId</td>
																		<td align="center" width="200px">CategoryName</td>
																		<td align="center" width="150px">PoleType</td>
																		<td align="center" width="50px">FromCon</td>
																		<td align="center" width="50px">ToCon</td>
																		<td align="center" width="50px">Pointer Type</td>
																		<td align="center" width="50px">Quantity</td>
																		<td align="center" width="50px">Remove</td>
																	</tr>
																	<%int j=1; %>
																	<s:iterator value="list1" id="listColumn">
																		<tr id="rowIDPole_<%= j %>" class="listRowStyle">
																			<td align="left" id="tdPoleCategoryId_<%= j %>">
																			<s:property value="CategoryId" /></td>
																			<td align="left" id="tdPoleCategoryName_<%= j %>">
																			<s:property value="CategoryName" /></td>
																			<td align="left" id="tdPoleType_<%= j %>"><s:property
																				value="poleType" /></td>
																			<td align="left" id="tdPolefromConductor_<%= j %>">
																			<s:property value="fromConductor" /></td>
																			<td align="left" id="tdPoletoConductor_<%= j %>">
																			<s:property value="toConductor" /></td>
																			<td align="left" id="tdPolePointerType_<%= j %>">
																			<s:property value="polePointerType" /></td>
																			<td align="left" id="tdPoleQuantity_<%= j %>"><s:property
																				value="Quantity" /></td>
																			<td align="left">
																			<button type="button" id="<%= j %>_btnRmv"
																				onclick="removePoleItem(this)">Remove</button>
																			</td>
																		</tr>
																		<%j++; %>
																	</s:iterator>
																</table>

																</td>
															</tr>
														</tbody>
													</table>


													</td>
												</tr>
												<tr>
													<td></td>
												</tr>
												<tr>
													<td class="tdHeadings" colspan="4">List of Struts</td>
												</tr>
												<tr>
													<td></td>
												</tr>
												<tr>
													<td>
													<table class="tblList" border="1" cellpadding="0"
														cellspacing="0">
														<thead>
															<tr>
																<th></th>
															</tr>
														</thead>
														<tbody>
															<tr>
																<td>


																<table id="list_table2" width="95%" align="center"
																	border="1"
																	style="border-color: #000033; border-collapse: collapse">
																	<tr class="listColumnHeadingStyle">
																		<td align="center" width="200px">CategoryId</td>
																		<td align="center" width="150px">Category</td>
																		<td align="center" width="50px">Quantity</td>
																		<td align="center" width="50px">Description</td>
																		<td align="center" width="50px">Remove</td>
																	</tr>
																	<%int i=1; %>
																	<s:iterator value="list2" id="listColumn">
																		<tr id="rowID_<%= i %>" class="listRowStyle">
																			<td align="left" id="tdStrutsCategoryId_<%= i %>">
																			<s:property value="CategoryId" /></td>
																			<td align="left"><s:property value="Item" /></td>
																			<td align="left"><s:property value="Quantity" />
																			</td>
																			<td align="left"><s:property value="Description" />
																			</td>
																			<td align="left">
																			<button type="button" id="<%= i %>_btnRmv"
																				onclick="removeItem(this)">Remove</button>
																			</td>
																		</tr>
																		<%i++; %>
																	</s:iterator>
																</table>

																</td>
															</tr>
														</tbody>
													</table>


													</td>
												</tr>
												<tr>
													<td></td>
												</tr>
												<tr>
													<td class="tdHeadings" colspan="4">List of Stays</td>
												</tr>
												<tr>
													<td></td>
												</tr>
												<tr>
													<td>
													<table class="tblList" border="1" cellpadding="0"
														cellspacing="0">
														<thead>
															<tr>
																<th></th>
															</tr>
														</thead>
														<tbody>
															<tr>
																<td>


																<table id="list_table3" width="95%" align="center"
																	border="1"
																	style="border-color: #000033; border-collapse: collapse">
																	<tr class="listColumnHeadingStyle">
																		<td align="center" width="200px">CategoryId</td>
																		<td align="center" width="150px">Category</td>
																		<td align="center" width="150px">Stay Type</td>
																		<td align="center" width="50px">Quantity</td>
																		<td align="center" width="50px">Description</td>
																		<td align="center" width="50px">Remove</td>
																	</tr>
																	<%int k=1; %>
																	<s:iterator value="list3" id="listColumn">
																		<tr id="rowIDStay_<%= k %>" class="listRowStyle">
																			<td align="left" id="tdStayCategoryId_<%= k %>">
																			<s:property value="CategoryId" /></td>
																			<td align="left" ><s:property value="Item" /></td>
																			<td align="left" id="tdStayCtrlCategoryId_<%= k %>"><s:property value="StayType" /></td>
																			<td align="left"><s:property value="Quantity" />
																			</td>
																			<td align="left"><s:property value="Description" />
																			</td>

																			<td align="left">
																			<button type="button" id="<%= k %>_btnRmv"
																				onclick="removeStayItem(this)">Remove</button>
																			</td>
																		</tr>
																		<%k++; %>
																	</s:iterator>
																</table>

																</td>
															</tr>
														</tbody>
													</table>


													</td>
												</tr>

												<tr>
													<td></td>
												</tr>
												<tr>
													<td class="tdHeadings" colspan="4">Service Lengths</td>
												</tr>
												<tr>
													<td></td>
												</tr>
												<tr>
													<td>
													<table class="tblList" border="1" cellpadding="0"
														cellspacing="0">
														<thead>
															<tr>
																<th></th>
															</tr>
														</thead>
														<tbody>
															<tr>
																<td>


																<table id="list_table4" width="95%" align="center"
																	border="1"
																	style="border-color: #000033; border-collapse: collapse">
																	<tr class="listColumnHeadingStyle">
																		<td align="center" width="200px">CategoryId</td>
																		<td align="center" width="150px">Category</td>
																		<td align="center" width="50px">Quantity</td>
																		<td align="center" width="50px">Description</td>
																		<td align="center" width="50px">Remove</td>
																	</tr>
																	<%int l=1; %>
																	<s:iterator value="list4" id="listColumn">
																		<tr id="rowIDSer_<%= l %>" class="listRowStyle">
																			<td align="left" id="tdSerLengthCategoryId_<%= l %>">
																			<s:property value="CategoryId" /></td>
																			<td align="left" id="tdSerLengthItemId_<%= l %>">
																			<s:property value="Item" /></td>
																			<td align="left" id="tdSerLengthQuantityId_<%= l %>">
																			<s:property value="Quantity" /></td>
																			<td align="left"><s:property value="Description" />
																			</td>
																			<td align="left">
																			<button type="button" id="<%= l %>_btnRmv"
																				onclick="removeServiceLengthItem(this)">Remove</button>
																			</td>
																		</tr>
																		<%l++; %>
																	</s:iterator>
																</table>

																</td>
															</tr>
														</tbody>
													</table>


													</td>
												</tr>
												<tr>
													<td id="tdBotton" colspan="3"><s:submit theme="simple"
														value="Save" method="save" id="btnSave"
														onclick="return saveValues()" disabled="true"></s:submit> 
														<input type="button"  value="Print"  id="btnPrint" onclick="callPrintEstimate()"></input>
														<applet codebase="../../PrintLib" code="serviceEstimate.ServiceEstimateApp" name="serviceEstimateApplet"
															archive="PrintJar.jar" width="1" height="1">														
														</applet> 
													<s:submit theme="simple" value="Clear" method="clear"
														id="btnClear" onclick="clearFields()"></s:submit>
													<s:submit theme="simple" value="Exit" method="close"
														id="btnClose"></s:submit> <s:hidden
														id="hid_isValueFilled" name="hid_isValueFilled"></s:hidden>
													<s:hidden id="hid_selectedStringPoles"
														name="hid_selectedStringPoles"></s:hidden> <s:hidden
														id="hid_selectedStringStruts"
														name="hid_selectedStringStruts"></s:hidden> <s:hidden
														id="hid_selectedStringStays"
														name="hid_selectedStringStays"></s:hidden> <s:hidden
														id="hid_selectedStringSerLengths"
														name="hid_selectedStringSerLengths"></s:hidden> <s:hidden
														id="hid_RemovePoles" name="hid_RemovePoles"></s:hidden> <s:hidden
														id="hid_RemoveStruts" name="hid_RemoveStruts"></s:hidden>
													<s:hidden id="hid_RemoveStays" name="hid_RemoveStays"></s:hidden>
													<s:hidden id="hid_RemoveSerLengths"
														name="hid_RemoveSerLengths"></s:hidden> <s:hidden
														id="hid_AddedPoleList" name="hid_AddedPoleList"></s:hidden>
													<s:hidden id="hid_AddedStayList" name="hid_AddedStayList"></s:hidden>
													<s:hidden id="hid_AddedStrutList" name="hid_AddedStrutList"></s:hidden>
													<s:hidden id="hid_AddedSerLengthsList"
														name="hid_AddedSerLengthsList"></s:hidden>
													<s:hidden id="hid_CableType"
														name="hid_CableType"></s:hidden>
													<s:hidden id="hid_SavedWireLengths"
														name="hid_SavedWireLengths"></s:hidden>
														<s:hidden id="hid_ConversionLength"
														name="hid_ConversionLength"></s:hidden>
												</tr>
											</tbody>
										</table>

									</s:form>
													
									</td>
								</tr>
								<tr>
									<td></td>
								</tr>
								<tr>
									<td></td>
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