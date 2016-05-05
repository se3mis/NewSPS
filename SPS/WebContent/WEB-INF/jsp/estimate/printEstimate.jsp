<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<html>
<head>
<sx:head />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>PRINT ESTIMATE</title>
<link rel="stylesheet" type="text/css" href="../../style/style.css" />

<script type="text/javascript" src="../../script/estimate.js"> </script>
<script type="text/javascript" src="../../script/javascript.js"> </script>
<script>
function printing() 
{
	dojo.event.topic.publish("print_est");
}
</script>
</head>

<body onload="javascript:formElementsDisplay()">
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
									<td width="736px" height="70px"><img
										src="../../image/Heading_Left.PNG" width="736" height="70"
										alt="Heading_Left" /></td>
									<td width="*" bgcolor="#226B9C"></td>
									<td width="198px" height="70px"><img
										src="../../image/Heading_Right.PNG" width="198" height="70"
										alt="Heading_Right" /></td>
								</tr>
								<tr>
									<td height="15px" bgcolor="#000033"></td>
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
						<table class="tblContent" border="0" cellpadding="0"
							cellspacing="0">
							<thead>
								<tr>
									<th></th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td><s:form id="frmEstimate" name="frmEstimate" action="print" method="POST"
										validate="true" theme="simple">
										<table width="75%" class="tldFromContent" border="0">
											<s:hidden id="isRemoveMat" name="isRemoveMat"></s:hidden>
											<thead>
												<tr>
													<th></th>

												</tr>
											</thead>
											<tbody>
												<tr>
													<td class="listHeader" colspan="4">STANDARD ESTIMATION PRINTING
													</td>
												</tr>
												<tr>
													<td class="tdMsgStyle"><s:property  value="errorMessage" ></s:property></td>
												</tr>
												<tr>
													<td></td>
												</tr>
												<tr>
															
															<td colspan="2">Estimation Number : <s:textfield name="applicationNo"
																id="txtApplicationNo" size="23" maxlength="21"
																onblur="javascript:{this.value = this.value.toUpperCase(); }"></s:textfield>
															<s:submit theme="simple" align="right" value="Find"
																method="viewEstimateDetails" id="btnFindApplicationNo"
																onclick="return checkAppNoNotNull()"></s:submit>
																Cost Center : <s:property value="costCenterNo" />
																<s:hidden id="isViewApplicant" name="isViewApplicant"></s:hidden>
																</td>
																
														</tr>
												<tr>
													<td></td>
												</tr>
												<tr>
													<td class="tdHeadings" colspan="4"><img
														src="../../image/down.jpg" name="imgUpDown" align="middle"
														id="imgUpDown" onclick="abc('myDivx','imgUpDown')" />
													Applicant Details</td>
												</tr>
												<tr>
													
													<td colspan="2">
													<div id="myDivx">
													<table width="100%" border="0">
														<tr>
															<td width="5"></td>
															<td>
															<table width="100%" border="0">
														
														<tr>
															<td width="20%" nowrap="nowrap">Applicant Name</td>
															<td width="2">:</td>
															<td width="30%"><s:property  value="applicantName"  /></td>
															<td width="20%">Application Date</td>
															<td width="2">:</td>
															<td width="30%"><s:property value="applicantDate" /></td>
														</tr>
														<tr>
															<td>Address</td>
															<td>:</td>
															<td><s:property  value="address"  />	 </td>
															</td>
																<td>Remarks</td>
																<td>:</td>
																<td><s:if test="description!=NULL">
																<s:property value="description" default="" />
															</s:if> </td>
														</tr>
														<tr>
															<td>Assesment Number</td>
															<td>:</td>
															<td> <s:if test="assessmentNo!=NULL">
																<s:property value="assessmentNo" />	</s:if> </td>
															</td>
																<td>&nbsp;</td>
																<td>&nbsp;</td>
																<td>&nbsp;</td>
														</tr>
														
														
														<tr>
															<td>Neighbours Acc. No</td>
															<td>:</td>
															<td><s:if test="neighboursAccNo!=NULL">
																<s:property value="neighboursAccNo" />
															</s:if> 
															 </td>
															 <td>&nbsp;</td>
																<td>&nbsp;</td>
																<td>&nbsp;</td>
															 
														</tr>
														
													</table>
															</td>
														</tr>
													</table>
													
													</div>
													</td>
												</tr>
												<tr>
													<td></td>
												</tr>
												<tr>
													<td class="tdHeadings" colspan="4">&nbsp;</td>
												</tr>
												
												<tr>
												<td class="tdHeadings">Details of Wiring</td>
												<td class="tdHeadings">Cost Details</td>
												</tr>
												

												<tr>
													<td>
													<div id="myDivx2">
													<table width="100%" border="0">
														<tr>
															<td width="5"></td>
															<td>
															<table width="100%" border="0">
													
														<tr height="20">
															<td width="150">Phase</td>
															<td>:</td>
															<td><s:property value="phase" default="" /> 
															<s:hidden id="phaseDb" name="phaseDb"/>
															</td>
														</tr>
														<tr height="20">
															<td>Connection Type</td>
															<td>:</td>
															<td>
															<s:property value="connectionType" default="" /> 
															<s:hidden id="connectionTypeDb" name="connectionTypeDb"/>
															</td>
														</tr>
														
														
														<tr height="20">
															<td>Tariff Category</td>
															<td>:</td>
															<td><s:property value="tariffCategory" default="" /></td>
														</tr>
														<tr height="20">
															<td>Tariff</td>
															<td>:</td>
															<td><s:property value="tariffCode" default="" /></td>
														</tr>
														<tr height="20">
															<td>Is loop service?</td>
															<td>:</td>
															<td><s:property value="isLoopService" default="" /></td>
														</tr>
														<tr height="20">
															<td colspan="3">&nbsp</td>
															
														</tr>
														<tr height="20">
															<td>Category Code</td>
															<td>:</td>
															<td><s:property value="categoryCode" /></td>
														</tr>
														<tr height="20">
															<td>Line Length(m)</td>
															<td>:</td>
															<td>
																<s:property value="lineLength" />
															</td>
														</tr>
														<tr height="20">
															<td>Loop Length(m)</td>
															<td>:</td>
															<td>
																<s:property value="loopLength" />
																
															</td>
														</tr>
														<tr height="20">
															<td>Lb(m)</td>
															<td>:</td>
															<td>
																<s:property value="lb" />
																</td>
														</tr>
														<tr height="20">
															<td>Spans</td>
															<td>:</td>
															<td>
																<s:property value="spans" /></td>
														</tr>
														<tr height="20">
															<td>No. Poles</td>
															<td>:</td>
															<td>
																<s:property value="poleNo" /></td>
														</tr>
														<tr height="20">
															<td>No. Stays</td>
															<td>:</td>
															<td>
																<s:property value="stayNo" /></td>
														</tr>
														<tr height="20">
															<td>No. Struts</td>
															<td>:</td>
															<td>
																<s:property value="strutsNo" /></td>
														</tr>
														<tr height="20">
															<td>SIN Number</td>
															<td>:</td>
															<td>
																<s:property value="sinNo" /></td>
														</tr>
														<tr height="20">
															<td>Phase</td>
															<td>:</td>
															<td>
																<s:property value="transColor" />
																<!-- 
																<s:select name="transColor" headerKey="0"  headerValue="Select Phase" list="#{'R':'Red','Y':'Yellow','B':'Blue'}"  />
																 -->
																</td>
														</tr>
														
														
														
														<tr height="20">
															<td>Distance to Service Place(Km)</td>
															<td>:</td>
															<td>
																<s:property value="distanceToServicePlace" /></td>
														</tr>
														
														<tr height="20">
															<td>Fund Source & ID</td>
															<td>:</td>
															<td>
																<s:property value="fundSourceID" />
																</td>
														</tr>
														<tr height="20">
															<td>Wiring Type</td>
															<td>:</td>
															<td>
																<s:property value="wiringType" />
																</td>
														</tr>
														
														
													</table>
															</td>
														</tr>
														<tr><td colspan="6">&nbsp;</td></tr>
														
													</table>
													
													</div>
													</td>
													<td valign="top">
														<table border="0">
															<tr>
																<td width="5">&nbsp;
																</td>
																<td>
																<table>
															<s:hidden id="wireMeterPrice" name="wireMeterPrice"></s:hidden>
															<s:hidden id="isMatListPopUp" name="isMatListPopUp"></s:hidden>
															<tr height="20">
																<td>Fixed Cost</td>
																<td>:</td>
																<td align="right"><s:property value="fixedCost" /></td>
															</tr>
															<tr height="20">
																<td>Variable Cost</td>
																<td>:</td>
																<td align="right"><s:property value="variableCost" /></td>
															</tr>
															<tr height="20">
																<td>Sub Total</td>
																<td>:</td>
																<td align="right"><s:property value="subTotal" /></td>
															</tr>
															<tr height="20">
																<td>Other Cost</td>
																<td>:</td>
																<td align="right"><s:property value="otherCost" /></td>
															</tr>
															<tr height="20">
																<td>Conversion Cost</td>
																<td>:</td>
																<td align="right"><s:property value="convCost" /></td>
															</tr>
															<tr height="20">
																<td>Tax Amount</td>
																<td>:</td>
																<td align="right"><s:property value="taxAmount" /></td>
															</tr>
															<tr height="20">
																<td>Security Deposit</td>
																<td>:</td>
																<td align="right"><s:property value="secDeposit" /></td>
															</tr>
															<tr height="20">
																<td>Total Cost</td>
																<td>:</td>
																<td align="right"><s:property value="totalCost" /></td>
															</tr>
															<tr height="20">
																<td colspan="3">
																&nbsp;
																</td>
															</tr>
															<tr height="20">
																<td colspan="3"  class="tdHeadings">
																Job Description
																</td>
															</tr>
															<tr height="20">
																<td colspan="3"><s:property value="jobDesc" />
																
																</td>
															</tr>
															<tr height="20">
																<td colspan="3">
																&nbsp;
																</td>
															</tr>
															<tr height="20">
																<td colspan="3"  class="tdHeadings">
																Permissions
																</td>
															</tr>
															<tr height="20">
																<td colspan="3"  >
																<s:property value="permission" />
																
																</td>
															</tr>
															
														</table>
																</td>
															</tr>
															
																
														</table>
														
													</td>
												</tr>
												<tr>
													<td colspan="2" class="tdHeadings">Resource List</td>
												</tr>
												<tr>
													<td colspan="2">
														<table  width="100%">
															
															<tr>
																<td width="5"></td>
																<td>
																<!-- Materials display -->
																<s:if test="isViewApplicant!=null">
																	<s:url id="add_url" value="MaterialAction!addMaterials" />
																	<s:url id="display_url" value="MaterialAction!displayMaterials" />
																	<table width="100%">
																		
																		 
																		
																		
																		<tr>
																			<td>
																				<s:if test="selectMatList!=null">
<table class="tldFromContent"  border="1" width="100%">
	<tr>
		<th>&nbsp;</th>
		<th>Resource Code</th>
		<th>Resource Type</th>
		<th>Resource Category</th>
		<th>Resource Name</th>
		<th>UOM</th>
		<th>Unit Price</th>
		<th>Est. Qty.</th>
		<th>Est. Cost</th>
	</tr>
	<s:set name="calcTotalAmt" value="0"/>
	<s:iterator value="selectMatList" status="matStatus">
		<tr>
			<td><s:checkbox name="chkSelMatCode" id="chkSelMatCode%{resCd}" fieldValue="%{#matStatus.index}###%{resCd}###%{uom}###%{unitPrice}###%{matNm}" theme="simple"></s:checkbox></td>
			<td><s:property value="resCd" /></td>
			<td><s:property value="resType" /></td>
			<td><s:property value="resCat" /></td>
			<td><s:property value="matNm" /></td>
			<td><s:property value="uom" /></td>
			<td align="right"><s:text name="format.currency"><s:param value="unitPrice"/></s:text></td>
			<td align="right"><s:property  value="estimateQty"/></td>
			<td align="right"><s:text name="format.currency"><s:param value="estimateCost"/></s:text>
				
			</td>
			<s:if test="estimateCost!=NULL">
				<s:set name="calcTotalAmt" value="#calcTotalAmt+estimateCost"/>
			</s:if>
		</tr>
	</s:iterator>
	
	<tr>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>Total Cost</td>
		<td align="right"><s:text name="format.currency"><s:param value="calcTotalAmt"/></s:text></td>
	</tr>
	
	
</table>
</s:if>
<s:else>
<table class="tldFromContent"  border="1" width="100%">
	<tr>
		<th>&nbsp;</th>
		<th>Resource Code</th>
		<th>Resource Type</th>
		<th>Resource Category</th>
		<th>Resource Name</th>
		<th>UOM</th>
		<th>Unit Price</th>
		<th>Est. Qty.</th>
		<th>Est. Cost</th>
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
</table>
</s:else>
																			</td>
																		</tr>
																		
																		
																		
																		
																		
																	</table>
																</s:if>
																<s:else>
																	<table class="tldFromContent"  border="1" width="100%">
																		<tr>
																			<th>&nbsp;</th>
																			<th>Resource Code</th>
																			<th>Resource Type</th>
																			<th>Resource Category</th>
																			<th>Resource Name</th>
																			<th>UOM</th>
																			<th>Unit Price</th>
																			<th>Est. Qty.</th>
																			<th>Est. Cost</th>
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
																	</table>
																</s:else>
																</td>
															</tr>
															
														</table>
													</td>
												</tr>
												<tr>
													<td colspan="2">
														<table>
															<tr>
																<td width="5"></td>
																<td>
																	
																	
																	
																</td>
															</tr>
														</table>
													</td>
												</tr>
												<tr>
													<td class="tdHeadings" colspan="4">&nbsp;</td>
												</tr>
												

												


												


												<tr>
													<td id="tdBotton2" colspan="3">&nbsp;</td>
												</tr>

												
												
												<tr>
													<td id="tdBotton" colspan="3">
														
													</td>
											</tbody>
										</table>

									</s:form></td>
								</tr>
								<tr>
									<td>
									<table border="0">
										<tr>
										<td>
											<s:if test="isViewApplicant!=null">
												<s:submit theme="simple" value="Print" id="btnPrint" onclick="javascript:printing()" type="button" ></s:submit>
											</s:if>
											<s:else>
												<s:submit theme="simple" value="Print" method="print" id="btnPrint" disabled="true" type="button"></s:submit>
											</s:else>
											<s:url id="print_url" value="PrintEstimateAction!print" />
											<s:a id="printLink" href="javascript:printing()"> </s:a>
											<sx:bind loadingText="Printing in progress...."  targets="msg" sources="printLink" events="href" href="%{#print_url}" listenTopics="print_est" formId="frmEstimate" />
											<sx:bind sources="btnPrint" events="onclick" href="%{#print_url}" listenTopics="print_est" formId="frmEstimate" />
																	
										</td>
										<td>
										<s:form action="print">
										<s:submit theme="simple" value="Clear" method="clear" id="btnClear" onclick="frmEstimate.submit()"></s:submit>
										</s:form>
										</td>
										<td>
										<s:form action="print">
																	<s:submit theme="simple" value="Close" method="close" id="btnClose" ></s:submit>
																	</s:form>
										</td>
										<td><div id="msg"></div></td>
										</tr>
									</table>
									
																	
																	
									</td>
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
						<table class="tblFooter" border="0" cellpadding="0"
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