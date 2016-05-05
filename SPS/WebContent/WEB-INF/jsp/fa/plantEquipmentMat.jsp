<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<html>
<head>
<sx:head />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>
PLANT AND EQUIPMENT
</title>
<link rel="stylesheet" type="text/css" href="../../style/style.css" />

<script type="text/javascript" src="../../script/fa.js"> </script>
<script type="text/javascript" src="../../script/javascript.js"> </script>
<script type="text/javascript">
function hide_list()
{
	document.getElementById('otherMat').style.display = 'none';
	document.getElementById('others').style.display = 'none';
	document.getElementById('npl').style.display = 'none';
	document.getElementById('poleMatParam').style.display = 'none';
	document.getElementById('availLabour').style.display = 'none';
	
}


function show_materials() 
{
	document.style='cursor:hand';
	document.getElementById('otherMat').style.display = '';
	document.getElementById('others').style.display = 'none';
	document.getElementById('poleMatParam').style.display = 'none';
	document.getElementById('npl').style.display = 'none';
	document.getElementById('isMatClicked').value = "true";
	dojo.event.topic.publish("display_mat");
	document.getElementById('isMatClicked').value = "";
}

function show_pole_mat_params() 
{
	document.style='cursor:hand';
	document.getElementById('otherMat').style.display = 'none';
	document.getElementById('poleMatParam').style.display = '';
	document.getElementById('others').style.display = 'none';
	document.getElementById('npl').style.display = 'none';
	document.getElementById('isPoleMatParamClicked').value = "true";
	dojo.event.topic.publish("display_pole_mat_param");
	document.getElementById('isPoleMatParamClicked').value = "";
}


function other_costs() 
{
	document.getElementById('others').style.display = '';
	document.getElementById('otherMat').style.display = 'none';
	document.getElementById('poleMatParam').style.display = 'none';
	document.getElementById('npl').style.display = 'none';
	document.getElementById('isOthClicked').value = "true";
	dojo.event.topic.publish("display_oth_cost");
	document.getElementById('isOthClicked').value = "";
}

function npl_costs() 
{
	document.getElementById('others').style.display = 'none';
	document.getElementById('otherMat').style.display = 'none';
	document.getElementById('poleMatParam').style.display = 'none';
	document.getElementById('npl').style.display = '';
	document.getElementById('isNplClicked').value = "true";
	dojo.event.topic.publish("display_npl_cost");
	document.getElementById('isNplClicked').value = "";
}

function add_materials(isAddMatClicked) 
{
	var len = document.getElementById('lineLength').value;
	
	document.getElementById('otherMat').style.display = 'none';
	if(isAddMatClicked)
		document.getElementById('isAddMatClicked').value = "true";
	dojo.event.topic.publish("add_mat");
}

function display_pole_materials()
{
	document.getElementById('isPoleMatClicked').value = "true";
	dojo.event.topic.publish("display_pole_mat");
	document.getElementById('isPoleMatClicked').value = "";
}


function remove_materials() 
{
	if(deleteConfirm())
	{
		document.getElementById('otherMat').style.display = 'none';
		document.getElementById('poleMatParam').style.display = 'none';
		document.getElementById('others').style.display = 'none';
		document.getElementById('npl').style.display = 'none';
		dojo.event.topic.publish("remove_mat");
	}
}


function remove_labour(val) 
{
	if(val=='remove')
	{
		
		if(labourDeleteConfirm())
		{
			document.getElementById('isRemoveLabClicked').value = "true";
			document.frmEstimate.submit();
			
		}
	}
	
}

function displayLabour()
{
	dojo.event.topic.publish("display_sel_labour");
}


function refreshSelMat()
{
	dojo.event.topic.publish("refresh_sel_mat");
}

function add_other_costs() 
{
	document.getElementById('otherMat').style.display = 'none';
	document.getElementById('others').style.display = 'none';
	document.getElementById('npl').style.display = 'none';
	dojo.event.topic.publish("add_oth_cost");
}

function add_npl_costs() 
{
	document.getElementById('otherMat').style.display = 'none';
	document.getElementById('others').style.display = 'none';
	document.getElementById('npl').style.display = 'none';
	dojo.event.topic.publish("add_npl_cost");
}

function add_pole_materials() 
{
	if(poleMatParamValidate())
	{
		document.getElementById('otherMat').style.display = 'none';
		document.getElementById('others').style.display = 'none';
		document.getElementById('npl').style.display = 'none';
		document.getElementById('poleMatParam').style.display = 'none';
		dojo.event.topic.publish("add_pole_mats");
		
	}
}

function show_estcost() 
{
	document.getElementById('isLineLenEntered').value = "true";
	dojo.event.topic.publish("display_estcost");
	document.getElementById('isLineLenEntered').value = "";
}

function show_labour_section()
{
	document.getElementById('labourSec').style.display = '';
	document.getElementById('matSec').style.display = 'none';
}

function show_material_section()
{
	document.getElementById('labourSec').style.display = 'none';
	document.getElementById('matSec').style.display = '';
}

function add_labourCodes(val) 
{
	//document.getElementById('otherMat').style.display = 'none';
	document.getElementById('availLabour').style.display = 'none';
	if(val=='add')
		document.getElementById('isAddLabClicked').value = "true";
	
	dojo.event.topic.publish("display_sel_labour");
	
}

function show_avail_labour()
{
	document.getElementById('availLabour').style.display = '';
	document.getElementById('isShowLabLinkClicked').value = "true";
	dojo.event.topic.publish("display_avail_labour");
	document.getElementById('isShowLabLinkClicked').value = "";
}

function cal_labour_cost() 
{
	dojo.event.topic.publish("cal_labour_cost");
}

</script>

</head>

<body onload="javascript:formElementsDisplay();">
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
									<td><img src="../../image/NewSPS2.gif" width="100%"
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
									<td><s:form id="frmEstimate" name="frmEstimate"
										action="PlantEquip" method="POST" validate="true" theme="simple">
										<s:hidden id="isViewApplicant" name="isViewApplicant"></s:hidden>
										<s:hidden id="isViewOnly" name="isViewOnly"></s:hidden>
										<s:hidden id="estimateNoDB" name="estimateNoDB"></s:hidden>
										<s:hidden id="isModify" name="isModify"></s:hidden>
										<s:hidden id="isUndoReject" name="isUndoReject"></s:hidden>
										<s:hidden id="isApprove" name="isApprove"></s:hidden>
										<s:hidden id="estimateExist" name="estimateExist"></s:hidden>
										<s:hidden id="isPrint" name="isPrint"></s:hidden>
										<s:hidden id="estimateStatus" name="estimateStatus"></s:hidden>
										<s:hidden id="estimateStatusDesc" name="estimateStatusDesc"></s:hidden>
										<s:hidden id="fixedCost_pr" name="fixedCost_pr"></s:hidden>
										<s:hidden id="isStandardVC" name="isStandardVC"></s:hidden>

										<s:hidden id="labourRate" name="labourRate"></s:hidden>
										<s:hidden id="transportRate" name="transportRate"></s:hidden>
										<s:hidden id="overheadRate" name="overheadRate"></s:hidden>
										<s:hidden id="region" name="region"></s:hidden>

										<table width="100%" class="tldFromContent" border="0">
											<s:hidden id="isRemoveMat" name="isRemoveMat"></s:hidden>
											<thead>
												<tr>
													<th></th>

												</tr>
											</thead>
											<tbody>
												<tr>

													<td class="listHeader" colspan="4">
													TRANSFER TO PLANT AND EQUIPMENT
													</td>
													<td><s:submit cssClass="buttonExit" theme="simple"
														align="right" value="X" method="close" id="btnClose"></s:submit>
													</td>
												</tr>
												<tr>
													<td class="tdMsgStyle"><s:property
														value="errorMessage"></s:property></td>
												</tr>
												<tr>
													<td class="tdMsgStyleDone"><s:property
														value="successMessage"></s:property></td>
												</tr>
												<tr>
													<td class="tdMsgStyleInfo"><s:property
														value="infoMessage"></s:property></td>
												</tr>
												<tr>
													<td></td>
												</tr>
												<tr height="20">
													<td colspan="2">Estimation Number : <s:if
														test="%{isViewOnly == 'true'|| isPrint == 'true'}">
														<s:textfield name="applicationNo" id="txtApplicationNo"
															size="23" maxlength="21"
															onblur="javascript:{this.value = this.value.toUpperCase(); }"></s:textfield>
														<s:submit theme="simple" align="right" value="Find"
															method="viewApplicantDetails" id="btnFindApplicationNo"
															onclick="return checkAppNoNotNull()"></s:submit>
													</s:if> <s:else>
														<s:select name="applicationNo" id="applicationNo"
															headerKey="" headerValue="Select Estimate No."
															list="estNoList" onchange="form.submit()" />
													</s:else> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Temp Id : <s:property
														value="applicationId" /><s:hidden name="applicationId"
														id="applicationId"></s:hidden></td>
												</tr>
												<tr height="20">
													<td colspan="2">Cost Center : <s:property
														value="costCenterNo" /><s:hidden name="costCenterNo"
														id="costCenterNo"></s:hidden>
													&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Estimated Date : <s:property
														value="estimatedDate" /><s:hidden name="estimatedDate"
														id="estimatedDate"></s:hidden></td>
												</tr>

												<s:if test="%{duration != null}">
													<tr height="20">
														<s:hidden name="appSubTypeDB" id="appSubTypeDB"></s:hidden>
														<s:hidden name="durationDB" id="durationDB"></s:hidden>
														<s:hidden name="firstYear" id="firstYear"></s:hidden>
														<s:hidden name="nextYear" id="nextYear"></s:hidden>

														<td colspan="3"><font color="blue">Temporary
														Connection : <b><s:property value="appSubType"
															default="" /></b> for <b><s:property value="duration"
															default="" /></b> </font></td>

													</tr>
												</s:if>
												<s:else>
													<tr height="20">
														<s:hidden name="appSubTypeDB" id="appSubTypeDB"></s:hidden>
														<s:hidden name="durationDB" id="durationDB"></s:hidden>
														<s:hidden name="firstYear" id="firstYear"></s:hidden>
														<s:hidden name="nextYear" id="nextYear"></s:hidden>

														<td colspan="3"></td>

													</tr>
												</s:else>
												<tr>
													<td>&nbsp;</td>
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

																<tr height="20">
																	<td width="20%" nowrap="nowrap">Applicant Name</td>
																	<td width="2">:</td>
																	<td width="30%"><s:if test="companyName!=NULL">
																		<s:property value="companyName" default="" /> - 
															</s:if> <s:property value="applicantName" /><s:hidden
																		id="applicantName" name="applicantName" /> <s:hidden
																		id="companyName" name="companyName" /></td>
																	<td width="20%">Application Date</td>
																	<td width="2">:</td>
																	<td width="30%"><s:property value="applicantDate" /><s:hidden
																		id="applicantDate" name="applicantDate" /></td>
																</tr>
																<tr height="20">
																	<td>NIC/Passport No</td>
																	<td>:</td>
																	<td><s:property value="idNo" /><s:hidden
																		id="idNo" name="idNo" /></td>
																	<td>Neighbours Acc. No</td>
																	<td>:</td>
																	<td><s:if test="neighboursAccNo!=NULL">
																		<s:property value="neighboursAccNo" />
																	</s:if> <s:hidden id="neighboursAccNo" name="neighboursAccNo" />
																	</td>
																</tr>
																<tr height="20">
																	<td>Address</td>
																	<td>:</td>
																	<td><s:property value="address" /><s:hidden
																		id="address" name="address" /></td>
																	<td>Remarks</td>
																	<td>:</td>
																	<td><s:if test="description!=NULL">
																		<s:property value="description" default="" />
																	</s:if> <s:hidden id="description" name="description" /></td>
																</tr>
																<tr height="20">
																	<td>Assesment Number</td>
																	<td>:</td>
																	<td><s:if test="assessmentNo!=NULL">
																		<s:property value="assessmentNo" />
																	</s:if><s:hidden id="assessmentNo" name="assessmentNo" /></td>

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
													<td>&nbsp;</td>
												</tr>
												<s:if
													test="%{isViewOnly!='true' && isModify!='true' && isUndoReject!='true' && estimateExist != 'true' && isApprove!='true' && isPrint!='true'}">
													<tr>
														<td>Copy estimate details from: <s:textfield
															name="copyEstimateNo" id="txtCopyEstimateNo" size="23"
															maxlength="21"></s:textfield> <s:submit theme="simple"
															align="right" value="Fill" method="copyEstimateDetails"
															onclick="return checkCopy()" id="btnFill"></s:submit></td>
													</tr>
												</s:if>
												<tr>
													<td colspan="4" style="font-style: italic;">(<font
														color="red">*</font>) Must be entered</td>
												</tr>
												<tr>
													<td class="tdHeadings">Connection Details</td>
													<td class="tdHeadings">&nbsp;</td>
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
																	<td>Phase</td>
																	<td>:</td>
																	<td><s:property value="phase" default="" /> <s:hidden
																		id="phase" name="phase" /> <s:hidden id="phaseDb"
																		name="phaseDb" /></td>
																</tr>
																<tr height="20">
																	<td>Connection Type</td>
																	<td>:</td>
																	<td><s:property value="connectionType" default="" />
																	<s:hidden id="connectionTypeDb" name="connectionTypeDb" />
																	<s:hidden id="connectionType" name="connectionType" />
																	</td>
																</tr>
																<tr height="20">
																	<td>Tariff Category</td>
																	<td>:</td>
																	<td><s:property value="tariffCategory" default="" /><s:hidden
																		id="tariffCategory" name="tariffCategory" /></td>
																</tr>
																<tr height="20">
																	<td>Tariff</td>
																	<td>:</td>
																	<td><s:property value="tariffCode" default="" /><s:hidden
																		id="tariffCode" name="tariffCode" /></td>
																</tr>

																<tr>
																	<td colspan="3">&nbsp</td>

																</tr>
																<tr>
																	<td>Category Code <font color="red">*</font></td>
																	<td>:</td>
																	<td><s:select name="categoryCode"
																		id="cmbCategoryCode" headerKey=""
																		headerValue="Select Category Code"
																		list="categoryCodeList" /></td>
																</tr>

																<tr>
																	<td>Total Line Length(m) <font color="red">*</font></td>
																	<td>:</td>
																	<td><s:textfield name="lineLength" id="lineLength"
																		onkeyup="show_estcost();numericValidate(this,event);"
																		onblur="show_estcost();" readonly="true" /></td>
																</tr>
																<tr>
																	<td>Conductor Type</td>
																	<td>:</td>
																	<td><!-- <s:radio name="conductorType" id="conductorType" list="#{'ABC':'ABC','FLY':'FLY'}" disabled="true" />-->
																	<s:textfield name="conductorType" id="conductorType"
																		readonly="true" /></td>
																</tr>
																<tr>
																	<td>Conductor Length(m)</td>
																	<td>:</td>
																	<td><s:if test="%{estimateExist == 'true'}">
																		<s:textfield name="conductorLength"
																			id="conductorLength"
																			onkeyup="numericValidate(this,event);"
																			readonly="true" />
																	</s:if> <s:else>
																		<s:textfield name="conductorLength"
																			id="conductorLength"
																			onkeyup="numericValidate(this,event);"
																			onblur="add_materials(false)" readonly="true" />
																	</s:else></td>
																</tr>
																<tr>
																	<td>Service Length(m)</td>
																	<td>:</td>
																	<td><s:if test="%{estimateExist == 'true'}">
																		<s:textfield name="serviceLength" id="serviceLength"
																			onkeyup="numericValidate(this,event);"
																			readonly="true" />
																	</s:if> <s:else>
																		<s:textfield name="serviceLength" id="serviceLength"
																			onkeyup="numericValidate(this,event);"
																			onblur="add_materials(false)" readonly="true" />
																	</s:else></td>
																</tr>
																<tr>
																	<td>1P-3P Line Conversion Length(m)</td>
																	<td>:</td>
																	<td><s:textfield name="conversionLength"
																		id="conversionLength"
																		onkeyup="numericValidate(this,event);" readonly="true" />
																	</td>
																</tr>
																<tr>
																	<td>2P-3P Line Conversion Length(m)</td>
																	<td>:</td>
																	<td><s:textfield name="conversionLength2P"
																		id="conversionLength2P"
																		onkeyup="numericValidate(this,event);" readonly="true" />
																	</td>
																</tr>
																<tr>
																	<td>Second Circuit Length(m)</td>
																	<td>:</td>
																	<td><s:textfield name="circuitLength"
																		id="circuitLength"
																		onkeyup="numericValidate(this,event);" readonly="true" />
																	</td>
																</tr>
																<tr>
																	<td>Wiring Type <font color="red">*</font></td>
																	<td>:</td>
																	<td><!--  
																<s:if test="%{estimateExist == 'true'}">
																	<s:radio name="wiringType" id="wiringType" list="#{'UG':'Under Ground','OH':'Over Head'}" disabled="true" />
																</s:if>
																<s:else>
																	<s:radio name="wiringType" id="wiringType" list="#{'UG':'Under Ground','OH':'Over Head'}" onchange="add_materials(false);show_estcost();displayCableType();"  disabled="true"/>
																</s:else>
																--> <s:textfield name="wiringType" id="wiringType"
																		readonly="true" /></td>
																</tr>
																<tr height="20">
																	<td>Is loop service?</td>
																	<td>:</td>
																	<td><!-- <s:radio name="isLoopService" id="isLoopService" list="#{'Y':'Yes','N':'No'}" onchange="show_estcost();displayCableType();"  disabled="true" /> -->
																	<s:textfield name="isLoopService" id="isLoopService"
																		readonly="true" /></td>
																</tr>
																<!-- 
														<tr>
															<td>Loop Length(m)</td>
															<td>:</td>
															<td>
																<s:if test="isLoopService='Y'">
																	<s:textfield name="loopLength" id="loopLength" onkeyup="numericValidate(this,event);" />
																</s:if>
																<s:else>
																	<s:textfield name="loopLength" id="loopLength" value="0" readonly="true" />
																</s:else>
															</td>
														</tr>
														 -->
																<tr>
																	<td>Cable Type</td>
																	<td>:</td>
																	<td><s:hidden name="cableType"></s:hidden> <s:select
																		name="cableTypeDis" id="cmbCableType" headerKey=""
																		headerValue="Select Cable Type" list="cableTypeList"
																		onchange="show_estcost();" listValue="%{value}"
																		listKey="key" disabled="true" /> <!-- 
																<s:if test="isLoopService='Y'">
																	<s:select name="cableType" id="cmbCableType" headerKey=""  headerValue="Select Cable Type"  list="cableTypeList" onchange="show_estcost();" listValue="%{value}" listKey="key" disabled="true"/>
																</s:if>
																<s:elseif test="wiringType='UG'">
																	<s:select name="cableType" id="cmbCableType" headerKey=""  headerValue="Select Cable Type"  list="cableTypeList" onchange="show_estcost();" listValue="%{value}" listKey="key"/>
																	
																</s:elseif>
																<s:else>
																	<s:select name="cableType" id="cmbCableType" headerKey=""  headerValue="Select Cable Type"  list="cableTypeList" onchange="show_estcost();" listValue="%{value}" listKey="key" disabled="true"/>
																</s:else>
															 --></td>
																</tr>
																<tr>
																	<td>Spans</td>
																	<td>:</td>
																	<td><s:textfield name="spans" id="spans"
																		onkeyup="numericValidate(this,event);" readonly="true" /></td>
																</tr>
																<tr>
																	<td>No. Poles</td>
																	<td>:</td>
																	<td><s:textfield name="poleNo" id="poleNo"
																		onkeyup="numericValidate(this,event);" readonly="true" /></td>
																</tr>
																<tr>
																	<td>No. Stays</td>
																	<td>:</td>
																	<td><s:textfield name="stayNo" id="stayNo"
																		onkeyup="numericValidate(this,event);" readonly="true" /></td>
																</tr>
																<tr>
																	<td>No. Struts</td>
																	<td>:</td>
																	<td><s:textfield name="strutsNo" id="strutsNo"
																		onkeyup="numericValidate(this,event);" readonly="true" /></td>
																</tr>
																<tr>
																	<td>SIN Number</td>
																	<td>:</td>
																	<td><s:textfield name="sinNo" id="sinNo" size="10"
																		maxlength="4"></s:textfield></td>
																</tr>
																<tr>
																	<td>Phase</td>
																	<td>:</td>
																	<td><s:select name="transColor" id="transColor"
																		headerKey="0" headerValue="Select Phase"
																		list="#{'R':'Red','Y':'Yellow','B':'Blue'}" /></td>
																</tr>
																<tr>
																	<td>Distance to Service Place(Km) <font
																		color="red">*</font></td>
																	<td>:</td>
																	<td><s:textfield name="distanceToServicePlace"
																		id="distanceToServicePlace"
																		onkeyup="numericValidate(this,event);" readonly="true" /></td>
																</tr>

																<tr>
																	<td>Fund Source & ID <font color="red">*</font></td>
																	<td>:</td>
																	<td><s:select name="fundSourceID"
																		id="fundSourceID" headerKey=""
																		headerValue="Select Fund Source & ID"
																		list="fundSourceList"
																		listKey="%{fundSource+'###'+fundId}"
																		listValue="%{name+' - '+fundId}" /></td>
																</tr>


															</table>
															</td>
														</tr>
														<tr>
															<td colspan="6">&nbsp;</td>
														</tr>

													</table>

													</div>
													</td>
													<s:hidden id="isNplClicked" name="isNplClicked"></s:hidden>
													<s:hidden id="isMatClicked" name="isMatClicked"></s:hidden>
													<s:hidden id="isPoleMatClicked" name="isPoleMatClicked"></s:hidden>
													<s:hidden id="isPoleMatParamClicked"
														name="isPoleMatParamClicked"></s:hidden>
													<s:hidden id="isAddMatClicked" name="isAddMatClicked"></s:hidden>
													<s:hidden id="isOthClicked" name="isOthClicked"></s:hidden>
													<s:hidden id="isLineLenEntered" name="isLineLenEntered"></s:hidden>
													<s:hidden id="city" name="city"></s:hidden>
													<s:hidden id="suburb" name="suburb"></s:hidden>
													<s:hidden id="streetAddress" name="streetAddress"></s:hidden>
													<s:hidden id="postalCode" name="postalCode"></s:hidden>
													<s:hidden id="isAddLabClicked" name="isAddLabClicked"></s:hidden>
													<s:hidden id="isRemoveLabClicked" name="isRemoveLabClicked"></s:hidden>
													<s:hidden id="isShowLabLinkClicked"
														name="isShowLabLinkClicked"></s:hidden>
													<td valign="top">
													<table border="0">
														<tr>
															<td colspan="2">Job Description :</td>
														</tr>
														<tr>
															<td width="5">&nbsp;</td>
															<td><s:textarea name="jobDesc" id="jobDesc"
																cols="45" rows="4"></s:textarea></td>
														</tr>
														<s:if
															test="%{isApprove == 'true' || isUndoReject == 'true'}">
															<tr>
																<td colspan="2">&nbsp;</td>
															</tr>
															<tr>
																<td colspan="2">Reason to reject <font
																	color="red">*</font></td>
															</tr>
															<tr>
																<td width="5">&nbsp;</td>
																<td colspan="2"><s:textarea name="rejectReason"
																	id="rejectReason" cols="45" rows="4"></s:textarea></td>
															</tr>
														</s:if>
														<tr>
															<td colspan="2" class="tdHeadings">&nbsp;</td>
														</tr>
														

													</table>

													</td>
												</tr>
												<tr>
													<td colspan="2" class="tdHeadings">Materials</td>
												</tr>
												<tr>
													<td colspan="2">&nbsp;</td>
												</tr>
												
												<tr>
													<td colspan="2">
													<div id="matSec">
													<table width="100%">

														<tr>
															<td width="5"></td>
															<td>
																<!-- Materials display --> 
																<s:if test="isViewApplicant!=null">
																	<s:url id="mat_url" value="PlantEquip!displayMaterialList" />
																	<table width="100%">
																		<tr>
																			<td>
																				<sx:div id="selMat" theme="simple"
																				href="%{mat_url}" listenTopics="add_mat"
																				formId="frmEstimate"></sx:div> 
																			</td>
																		</tr>
																	</table>
																</s:if> <s:else>
																<table class="tldFromContent" border="1" width="100%">
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
																		<th>Customer Qty.</th>
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
																		<td>&nbsp;</td>
																	</tr>
																</table>
															</s:else></td>
														</tr>

													</table>
													</div>

													<div id="labourSec">


													<table width="100%">
														<tr>
															<td width="5"></td>
															<td><!-- Materials display --> <s:if
																test="%{isViewApplicant == 'true'}">
																<table width="100%">
																	<tr>
																		<td><s:url id="sel_labour_url"
																			value="LabourAction!displaySelectLabour" /> <sx:div
																			id="selLabour" href="%{#sel_labour_url}"
																			listenTopics="display_sel_labour"
																			formId="frmEstimate" /></td>
																	</tr>
																	<s:if
																		test="%{isViewOnly!='true' && isUndoReject!='true' && isApprove!='true' && isPrint!='true'}">
																		<tr>
																			<td><s:url id="remove_url_labour"
																				action="estimate!removeLabour" /> <s:a
																				id="removeLinkLabour"
																				href="javascript:remove_labour('remove')">Remove Checked Resources</s:a>
																			<sx:bind targets="selLabour"
																				sources="removeLinkLabour" events="href"
																				href="%{#remove_url_labour}"
																				listenTopics="remove_labour_items"
																				formId="frmEstimate" /></td>
																		</tr>

																		<tr>
																			<td>&nbsp;</td>
																		</tr>

																		<tr>
																			<td><s:a id="showAvailLabourLink"
																				href="javascript:show_avail_labour()">Add Other Labour Activities</s:a>


																			</td>
																		</tr>

																		<tr>
																			<td align="center"><s:url id="avail_labour_url"
																				value="LabourAction!displayAvailLabour" /> <sx:div
																				id="availLabour" href="%{#avail_labour_url}"
																				listenTopics="display_avail_labour"
																				formId="frmEstimate" /></td>
																		</tr>

																	</s:if>
																</table>
															</s:if> <s:else>
																<table class="tldFromContent" border="1" width="100%">
																	<tr>
																		<th>&nbsp;</th>
																		<th>Labour Code</th>
																		<th>Description</th>
																		<th>Unit Price</th>
																		<th>Qty.</th>
																		<th>Labour Cost</th>
																	</tr>

																	<tr>
																		<td>&nbsp;</td>
																		<td>&nbsp;</td>
																		<td>&nbsp;</td>
																		<td>&nbsp;</td>
																		<td>&nbsp;</td>
																		<td>&nbsp;</td>

																	</tr>
																</table>
															</s:else></td>
														</tr>

													</table>



													</div>
													</td>
												</tr>
												<tr>
													<td colspan="2">
													<table>
														<tr>
															<td width="5"></td>
															<td><s:if test="%{isViewOnly!='true'}">
																<s:if test="%{isApprove == 'true'}">
																	<s:if test="isViewApplicant!=null">

																		<s:submit theme="simple" value="Approve"
																			method="approveEstimate" id="btnApprove"></s:submit>
																		<!--<s:if test="%{costCenterNo=='514.20' || costCenterNo=='513.00' || costCenterNo=='513.10' || costCenterNo=='513.30' || costCenterNo=='513.40'}">-->
																		<s:submit theme="simple" value="Recommend"
																			method="approveEstimate" id="btnApprove"></s:submit>
																		<s:submit theme="simple" value="Check"
																			method="approveEstimate" id="btnApprove"></s:submit>
																		<!--</s:if>-->
																		<s:submit theme="simple" value="Reject"
																			method="rejectEstimate" id="btnReject"
																			onclick="return rejectConfirm()"></s:submit>

																	</s:if>
																	<s:else>
																		<s:submit theme="simple" value="Approve"
																			id="btnApprove" disabled="true"></s:submit>
																		<s:submit theme="simple" value="Reject" id="btnReject"
																			disabled="true"></s:submit>
																	</s:else>
																</s:if>
																<s:elseif test="%{isPrint == 'true'}">
																</s:elseif>
																<s:else>
																	<s:if test="isViewApplicant!=null">
																		<s:if test="%{isUndoReject == 'true'}">
																			<s:submit theme="simple" value="Undo Reject"
																				method="save" id="btnSave"
																				onclick="return formValidation()"></s:submit>
																		</s:if>
																		<s:else>
																			<s:submit theme="simple" value="Transfer to P&E" method="save"
																				id="btnSave" onclick="return formValidation()"></s:submit>
																		</s:else>
																	</s:if>
																	<s:else>
																		<s:if test="%{isUndoReject == 'true'}">
																			<s:submit theme="simple" value="Undo Reject"
																				method="save" id="btnSave" disabled="true"></s:submit>
																		</s:if>
																		<s:else>
																			<s:submit theme="simple" value="Save" method="save"
																				id="btnSave" disabled="true"></s:submit>
																		</s:else>
																	</s:else>
																	<s:if test="%{estimateExist == 'true'}">
																		<s:submit theme="simple" value="Send for Approval"
																			method="sendToapproval" id="btnApprove"
																			onclick="return approveConfirm()"></s:submit>
																	</s:if>
																	<s:else>
																		<s:submit theme="simple" value="Send for Approval"
																			id="btnApprove" disabled="true"></s:submit>
																	</s:else>
																</s:else>
															</s:if> <s:if test="%{isPrint != 'true'}">
																<s:submit theme="simple" value="Clear" method="clear"
																	id="btnClear"></s:submit>
																<s:submit theme="simple" value="Exit" method="close"
																	id="btnClose"></s:submit>

															</s:if> <s:submit theme="simple" value="Download Estimate"
																id="btnDownloadEstimate" method="DownlaodEstimate">
															</s:submit></td>
														</tr>
													</table>
													</td>
												</tr>

											</tbody>
										</table>


									</s:form></td>
								</tr>
								<tr>
									<td><s:if test="%{isPrint == 'true'}">
										<table>
											<tr>
												<td><s:if test="isViewApplicant!=null">
													<applet codebase="../../PrintLib"
														code="standardEstimate.StandardEstimateApp"
														archive="PrintJar.jar" name="PIVApplet" width="1"
														height="1"> </applet>
													<s:submit theme="simple" value="Print" id="btnPrint"
														onclick="javascript:printing()" type="button"></s:submit>
												</s:if> <s:else>
													<s:submit theme="simple" value="Print" id="btnApprove"
														disabled="true"></s:submit>
												</s:else></td>
												<td><s:form action="estimate">
													<s:submit theme="simple" value="Clear" method="clear"
														id="btnClear" onclick="frmEstimate.submit()"></s:submit>
												</s:form></td>
												<td><s:form action="estimate">
													<s:submit theme="simple" value="Close" method="close"
														id="btnClose"></s:submit>
												</s:form></td>
											</tr>
										</table>
									</s:if></td>
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
									<td id="tdFooter">
									<table class="tblFooter" border="1" cellpadding="0"
										cellspacing="0">

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