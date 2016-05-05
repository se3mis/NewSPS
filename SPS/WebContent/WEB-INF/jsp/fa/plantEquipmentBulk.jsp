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

function move_mats(resCd)
{
	document.getElementById('moveMatCode').value = resCd;
	var val1 = 'faManCatCode'+resCd;
	var val2 = 'faMoveQty'+resCd;
	document.getElementById('moveManCatCode').value = document.getElementById(val1).value;
	document.getElementById('moveQty').value = document.getElementById(val2).value;
	dojo.event.topic.publish("mat_move");
	
}

function remove_mats(resCd,faManCat)
{
	document.getElementById('removeMatCode').value = resCd;
	document.getElementById('removeManCatCode').value = faManCat;
	dojo.event.topic.publish("mat_remove");
	
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
										action="PlantEquipBulk" method="POST" validate="true" theme="simple">
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
										
										<s:hidden id="moveMatCode" name="moveMatCode"></s:hidden>
										<s:hidden id="moveManCatCode" name="moveManCatCode"></s:hidden>
										<s:hidden id="moveQty" name="moveQty"></s:hidden>
										<s:hidden id="removeMatCode" name="removeMatCode"></s:hidden>
										<s:hidden id="removeManCatCode" name="removeManCatCode"></s:hidden>
										<s:hidden id="isTransfer" name="isTransfer"></s:hidden>
										<s:hidden id="isVerify" name="isVerify"></s:hidden>
										

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
													PLANT AND EQUIPMENT
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
																	<td colspan="3">&nbsp;</td>

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
													<td colspan="2">
														<table border="0" width="100%">
															<tr>
																<td width="5">
																</td>
																<td>
																	<table width="100%">
																		<tr>
																			<td width="35%">
																				<table>
																					<s:set name="tmpManCat" value="@"/>
																					<s:iterator value="faManCatFeatList" status="matStatus">
																						<s:if test="#tmpManCat!=faManCatCode">
																							<tr>
																								<s:set name="tmpManCat" value="%{faManCatCode}"/>
																								<td colspan="7" >
																									<b><s:property value="faManCatDesc" /></b>
																								</td>
																							</tr>
																						</s:if>
																						<tr>
																							<td>
																								<s:property value="featureDesc" />
																							</td>
																							<td><s:textfield name="manCatFeatValue" value="%{featureValue}" ></s:textfield>
																							</td>
																						</tr>
																					</s:iterator>
																				</table>
																			</td>
																			<td valign="top" width="65%">
																				<table border="0">
																					<s:set name="tmpMatCode" value="@"/>
																					<s:set name="tmpSubCatCode" value="@"/>
																					<s:iterator value="faMatFeatureList" status="matStatus">
																						<s:if test="#tmpSubCatCode!=faSubCatCode">
																							<tr>
																								<s:set name="tmpSubCatCode" value="%{faSubCatCode}"/>
																								<td colspan="4" >
																									<b><s:property value="faSubCatDesc" /></b>
																								</td>
																							</tr>
																						</s:if>
																						<s:if test="#tmpMatCode!=matCode">
																							<tr>
																								<td width="10"></td>
																								<td colspan="3">
																									<s:set name="tmpMatCode" value="%{matCode}"/>
																									<s:property value="matCode" /> - <s:property value="matName" />
																								</td>
																							</tr>
																						</s:if>
																						<tr>
																							<td width="10"></td>
																							<td width="10"></td>
																							<td>
																								<s:property value="featureDesc" />
																							</td>
																							<td>
																								<s:textfield name="matFeatValue" value="%{featureValue}" ></s:textfield>
																							</td>
																						</tr>
																					</s:iterator>
																				</table>
																			</td>
																		</tr>
																	</table>
																</td>
															</tr>	
														</table>
													</td>
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
																	<s:url id="mat_url" value="PlantEquipBulk!displayMaterialList" />
																	<s:url id="mat_move_url" value="PlantEquipBulk!moveMaterials" />
																	<s:url id="mat_remove_url" value="PlantEquipBulk!removeMaterials" />
																	<table width="100%">
																		<tr>
																			<td>
																				<sx:div id="selMat" theme="simple"
																				href="%{mat_url}" listenTopics="add_mat"
																				formId="frmEstimate"></sx:div> 
																				<sx:bind targets="selMat" sources="matMoveLink"
																				events="href" href="%{#mat_move_url}"
																				listenTopics="mat_move" formId="frmEstimate" />
																				<sx:bind targets="selMat" sources="matRemoveLink"
																				events="href" href="%{#mat_remove_url}"
																				listenTopics="mat_remove" formId="frmEstimate" />
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
														<tr>
															<td width="5"></td>
															<td>
																
															</td>
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
															<td>
																<s:if test="isViewApplicant!=null">
																		<s:if test="%{isVerify == 'true'}">
																			<s:submit theme="simple" value="Verify"
																				method="save" id="btnSave"
																				onclick="return formValidation()"></s:submit>
																		</s:if>
																		<s:elseif test="%{isTransfer == 'true'}">
																			<s:submit theme="simple" value="Transfer to P&E"
																				method="save" id="btnSave"
																				onclick="return formValidation()"></s:submit>
																		</s:elseif>
																		<s:else>
																			<s:submit theme="simple" value="Save" method="save"
																				id="btnSave" onclick="return formValidation()"></s:submit>
																		</s:else>
																	</s:if>
																	<s:else>
																		<s:if test="%{isVerify == 'true'}">
																			<s:submit theme="simple" value="Verify"
																				method="save" id="btnSave"
																				onclick="return formValidation()" disabled="true"></s:submit>
																		</s:if>
																		<s:elseif test="%{isTransfer == 'true'}">
																			<s:submit theme="simple" value="Transfer to P&E"
																				method="save" id="btnSave"
																				onclick="return formValidation()" disabled="true"></s:submit>
																		</s:elseif>
																		<s:else>
																			<s:submit theme="simple" value="Save" method="save"
																				id="btnSave" onclick="return formValidation()" disabled="true"></s:submit>
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
																
															 		<s:submit theme="simple" value="Clear" method="clear"
																	id="btnClear"></s:submit>
																	<s:submit theme="simple" value="Exit" method="close"
																	id="btnClose"></s:submit>
																
																
															 </td>
														</tr>
													</table>
													</td>
												</tr>

											</tbody>
										</table>


									</s:form></td>
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