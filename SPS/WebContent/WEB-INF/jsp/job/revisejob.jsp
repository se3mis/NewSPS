<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<html>
<head>
<sx:head />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>
	<s:if test="%{state == 'print'}">
		PRINT JOBS
	</s:if>
	<s:elseif test="%{isUndoReject == 'true'}">
		UNDO REJECTED JOBS
	</s:elseif>
	<s:elseif test="%{isApprove == 'true'}">
		APPROVE/REJECT JOBS
	</s:elseif>
	<s:else>
		JOB REVISION
	</s:else>
</title>
<link rel="stylesheet" type="text/css" href="../../style/style.css" />

<script type="text/javascript" src="../../script/javascript.js"> </script>
<script type="text/javascript" src="../../script/job.js"> </script>
<script type="text/javascript">

function hide_list()
{
	document.getElementById('otherMat').style.display = 'none';
	document.getElementById('others').style.display = 'none';
	document.getElementById('npl').style.display = 'none';
}

function show_materials() 
{
	document.getElementById('otherMat').style.display = '';
	document.getElementById('others').style.display = 'none';
	document.getElementById('npl').style.display = 'none';
	document.getElementById('isMatClicked').value = "true";
	dojo.event.topic.publish("display_mat");
	document.getElementById('isMatClicked').value = "";
}

function other_costs() 
{
	document.getElementById('others').style.display = '';
	document.getElementById('otherMat').style.display = 'none';
	document.getElementById('npl').style.display = 'none';
	document.getElementById('isOthClicked').value = "true";
	dojo.event.topic.publish("display_oth_cost");
	document.getElementById('isOthClicked').value = "";
}

function npl_costs() 
{
	document.getElementById('others').style.display = 'none';
	document.getElementById('otherMat').style.display = 'none';
	document.getElementById('npl').style.display = '';
	document.getElementById('isNplClicked').value = "true";
	dojo.event.topic.publish("display_npl_cost");
	document.getElementById('isNplClicked').value = "";
}

function add_materials(isClicked) 
{
	var len = document.getElementById('lineLength').value;
	
	document.getElementById('otherMat').style.display = 'none';
	if(isClicked)
		document.getElementById('isAddMatClicked').value = "true";
	dojo.event.topic.publish("add_mat");
}

function remove_materials() 
{
	if(deleteConfirm())
	{
		document.getElementById('otherMat').style.display = 'none';
		document.getElementById('others').style.display = 'none';
		document.getElementById('npl').style.display = 'none';
		dojo.event.topic.publish("remove_mat");
	}
	
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

function show_avail_labour()
{
	document.getElementById('availLabour').style.display = '';
	document.getElementById('isShowLabLinkClicked').value = "true";
	dojo.event.topic.publish("display_avail_labour");
	document.getElementById('isShowLabLinkClicked').value = "";
}

function add_labourCodes(val) 
{
	//document.getElementById('otherMat').style.display = 'none';
	document.getElementById('availLabour').style.display = 'none';
	if(val=='add')
		document.getElementById('isAddLabClicked').value = "true";
	
	dojo.event.topic.publish("display_sel_labour");
	
}

function remove_labour(val) 
{
	if(val=='remove')
	{
		
		if(labourDeleteConfirm())
		{
			document.getElementById('isRemoveLabClicked').value = "true";
			document.frmEstimate.submit();
			/* dojo.event.topic.publish("display_sel_labour");
			var i=0;
			while(i<200000)
			{
				i=i+1;
			}*/
			//alert('Successfully removed');	
			//refreshSelMat();
		}
	}
	
}

function cal_labour_cost() 
{
	dojo.event.topic.publish("cal_labour_cost");
}
</script>
</head>

<body onload="formElementsDisplay()">
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
						<table class="tblContent" border="0" cellpadding="0"
							cellspacing="0">
							<thead>
								<tr>
									<th></th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td><s:form id="frmEstimate" name="frmEstimate" action="ReviseJob" method="POST"
										validate="true" theme="simple">
										<table width="75%" class="tldFromContent" border="0">
											<s:hidden id="isRemoveMat" name="isRemoveMat"></s:hidden>
											<s:hidden id="isUndoReject" name="isUndoReject"></s:hidden>
											<s:hidden id="isApprove" name="isApprove"></s:hidden>
											<s:hidden id="applicationNo" name="applicationNo"></s:hidden>
											<s:hidden id="revNo" name="revNo"></s:hidden>
											<s:hidden id="isLineLenEntered" name="isLineLenEntered"></s:hidden>
											<s:hidden id="isAddLabClicked" name="isAddLabClicked"></s:hidden>
											<s:hidden id="isRemoveLabClicked" name="isRemoveLabClicked"></s:hidden>
											<s:hidden id="isShowLabLinkClicked" name="isShowLabLinkClicked"></s:hidden>
													
											<thead>
												<tr>
													<th></th>

												</tr>
											</thead>
											<tbody>
												<tr>
													<td class="listHeader" colspan="4">
													<s:if test="%{state == 'print'}">
														PRINT JOBS
													</s:if>
													<s:else>
													<s:if test="%{isUndoReject == 'true'}">
														UNDO REJECTED JOBS
													</s:if>
													<s:elseif test="%{isApprove == 'true'}">
														APPROVE/REJECT JOBS
													</s:elseif>
													<s:else>
														JOB REVISION
													</s:else>
													</s:else>
													</td>
												</tr>
												<tr>
													<td class="tdMsgStyle"><s:property  value="errorMessage" ></s:property></td>
												</tr>
												<tr>
													<td class="tdMsgStyleDone"><s:property  value="successMessage" ></s:property></td>
												</tr>
												<tr>
													<td class="tdMsgStyleInfo"><s:property  value="infoMessage" ></s:property></td>
												</tr>
												<tr>
													<td></td>
												</tr>
												<s:if test="%{state == 'print'}">
														<tr>
														<td colspan="2">Job Number(view) : 
																<s:textfield name="jobNo" id="jobNo" ></s:textfield><s:submit theme="simple" align="right" value="Find" method="findJobNo" id="btnFindAppId" onclick="return checkAppIdNoNotNull()"></s:submit>
																Cost Center : <s:property value="costCenterNo" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Revision No : <s:property value="revNo"/>
																<s:hidden id="isViewApplicant" name="isViewApplicant"></s:hidden>
																<s:hidden id="costCenterNo" name="costCenterNo"></s:hidden>
																</td>
																
														</tr>
												</s:if>
												<s:else>
												<tr>
															
														<td colspan="2">Job Number : 
																<s:select name="jobNo" id="jobNo" headerKey=""  headerValue="Select Job No." list="jobNoList" onchange="form.submit()"  />
																Cost Center : <s:property value="costCenterNo" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Revision No : <s:property value="revNo"/>
																<s:hidden id="isViewApplicant" name="isViewApplicant"></s:hidden>
																<s:hidden id="costCenterNo" name="costCenterNo"></s:hidden>
																</td>
																
														</tr>
														</s:else>
														
														<s:if test="%{duration != null}">
															<tr height="20">
																<s:hidden name="appSubTypeDB" id="appSubTypeDB"></s:hidden>
																<s:hidden name="durationDB" id="durationDB"></s:hidden>
																<s:hidden name="firstYear" id="firstYear"></s:hidden>
																<s:hidden name="nextYear" id="nextYear"></s:hidden>
																
																<td colspan="3"><font color="blue">Temporary Connection : <b><s:property value="appSubType" default="" /></b> for <b><s:property value="duration" default="" /></b> </font></td>
																
															</tr>
														</s:if>
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
														
														<tr height="20">
															<td width="20%" nowrap="nowrap">Applicant Name</td>
															<td width="2">:</td>
															<td width="30%"><s:textfield  name="applicantName" id="applicantName" readonly="true" /></td>
															<td width="20%">Application Date</td>
															<td width="2">:</td>
															<td width="30%"><s:textfield name="applicantDate" id="applicantDate" readonly="true" /></td>
														</tr>
														<tr height="20">
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
														<tr height="20">
															<td>Assesment Number</td>
															<td>:</td>
															<td> <s:if test="assessmentNo!=NULL">
																<s:property value="assessmentNo" />	</s:if> </td>
															</td>
																<td>&nbsp;</td>
																<td>&nbsp;</td>
																<td>&nbsp;</td>
														</tr>
														
														
														<tr height="20">
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
													<td colspan="4" style="font-style:italic;">(<font color="red">*</font>) Must be entered</td>
												</tr>
												<tr>
												<td class="tdHeadings">Connection Details</td>
												<td class="tdHeadings"></td>
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
															<td><s:textfield name="phase" id="phase" size="10" readonly="true" />
															<s:hidden name="phaseDB"></s:hidden>
															</td>
														</tr>
														<tr height="20">
															<td>Connection Type</td>
															<td>:</td>
															<td><s:textfield name="connectionType"  id="connectionType" size="5" readonly="true" /> <s:hidden name="connectionTypeDb"></s:hidden></td>
														</tr>
														
														
														<tr height="20">
															<td>Tariff Category</td>
															<td>:</td>
															<td><s:property value="tariffCategory" default="" /><s:hidden id="tariffCategory" name="tariffCategory"/></td>
														</tr>
														<tr height="20">
															<td>Tariff</td>
															<td>:</td>
															<td><s:property value="tariffCode" default="" /></td>
														</tr>
														<tr>
															<td colspan="3">&nbsp</td>
															
														</tr>
														<tr>
															<td>Category Code <font color="red">*</font></td>
															<td>:</td>
															<td><s:select name="categoryCode" id="cmbCategoryCode" headerKey=""  headerValue="Select Category Code" list="categoryCodeList"  /></td>
														</tr>
														<tr>
															<td>Total Line Length(m) <font color="red">*</font></td>
															<td>:</td>
															<td><s:textfield name="lineLength" id="lineLength" onkeyup="numericValidate(this,event);show_estcost();" onblur="show_estcost();add_materials(false)" /></td>
														</tr>
														<tr>
															<td>Conductor Type</td>
															<td>:</td>
															<td><s:radio name="conductorType" id="conductorType" list="#{'ABC':'ABC','FLY':'FLY'}" /></td>
														</tr>
														
														<tr>
															<td>Conductor Length(m)</td>
															<td>:</td>
															<td><s:textfield name="lb"
																id="lb" value="5" readonly="true"/></td>
														</tr>
														<tr>
															<td>Service Length(m)</td>
															<td>:</td>
															<td><s:textfield name="serviceLength"	id="serviceLength" onkeyup="numericValidate(this,event);" /></td>
														</tr>
														<tr>
															<td>Line Conversion Length(m)</td>
															<td>:</td>
															<td>
																<s:textfield name="conversionLength"	id="conversionLength" onkeyup="numericValidate(this,event);"  readonly="true"/>
															</td>
														</tr>
														<tr>
															<td>Wiring Type <font color="red">*</font></td>
															<td>:</td>
															<td><s:radio name="wiringType" id="wiringType" list="#{'UG':'Under Ground','OH':'Over Head'}" /></td>
														</tr>
														<tr height="20">
															<td>Is loop service?</td>
															<td>:</td>
															<td><s:radio name="isLoopService" id="isLoopService" list="#{'Y':'Yes','N':'No'}" /></td>
														</tr>
														<tr>
															<td>Loop Length(m)</td>
															<td>:</td>
															<td>
																<s:if test="isLoopService='N'">
																	<s:textfield name="loopLength" id="loopLength" value="0" readonly="true" />
																</s:if>
																<s:else>
																	<s:textfield name="loopLength" id="loopLength" onkeyup="numericValidate(this,event);" />
																</s:else>
															</td>
														</tr>
														
														<tr>
															<td>Spans</td>
															<td>:</td>
															<td><s:textfield name="spans"
																id="spans"  onkeyup="numericValidate(this,event);"/></td>
														</tr>
														<tr>
															<td>No. Poles</td>
															<td>:</td>
															<td><s:textfield name="poleNo"
																id="poleNo" onkeyup="numericValidate(this,event);" /></td>
														</tr>
														<tr>
															<td>No. Stays</td>
															<td>:</td>
															<td><s:textfield name="stayNo"
																id="stayNo" onkeyup="numericValidate(this,event);" /></td>
														</tr>
														<tr>
															<td>No. Struts</td>
															<td>:</td>
															<td><s:textfield name="strutsNo"
																id="strutsNo" onkeyup="numericValidate(this,event);" /></td>
														</tr>
														<tr>
															<td>SIN Number </td>
															<td>:</td>
															<td><s:textfield name="sinNo" id="sinNo" size="10"></s:textfield></td>
														</tr>
														<tr>
															<td>Phase</td>
															<td>:</td>
															<td><s:select name="transColor" id="transColor" headerKey="0"  headerValue="Select Phase" list="#{'R':'Red','Y':'Yellow','B':'Blue'}"  /></td>
														</tr>
														
														
														
														<tr>
															<td>Distance to Service Place(Km)</td>
															<td>:</td>
															<td><s:textfield name="distanceToServicePlace"
																id="distanceToServicePlace" onkeyup="numericValidate(this,event);" /></td>
														</tr>
														
														<tr>
															<td>Fund Source & ID</td>
															<td>:</td>
															<td><s:select name="fundSourceID" id="fundSourceID" headerKey=""  headerValue="Select Fund Source & ID" list="fundSourceList"  listKey="%{fundSource+'###'+fundId}" listValue="%{name+' - '+fundId}" /></td>
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
																<td colspan="2">
																Job Description 
																</td>
															</tr>
															<tr>
																<td width="5">&nbsp;</td>
																<td>
																<s:textarea name="jobDesc" id="jobDesc" cols="45" rows="2"></s:textarea>
																</td>
															</tr>
															<tr>
																<td colspan="2">Revise Reason <font color="red">*</font></td>
															</tr>
															<tr>
																<td width="5">&nbsp;</td>
																<td>
																<s:textarea name="revReason" id="revReason" cols="45" rows="2"></s:textarea>
																</td>
															</tr>
															<s:if test="%{isApprove == 'true' || isUndoReject == 'true'}">
																<tr>
																	<td colspan="2" >Reason to reject</td>
																</tr>
																<tr>
																	<td width="5">&nbsp;</td>
																	<td>
																	<s:textarea name="rejectReason" id="rejectReason" cols="45" rows="2"></s:textarea>
																	</td>
																</tr>
															</s:if>
															<tr>
																<td colspan="2"  class="tdHeadings">&nbsp;</td>
															</tr>
															<tr>
																<td colspan="2"  class="tdHeadings">Standard Estimate</td>
															</tr>
															<tr>
																<td width="5">&nbsp;</td>
																<td>
																	<s:url id="estcost_url" value="ReviseJob!calEstCostToLineLen" />
																	<sx:div theme="simple"  href="%{#estcost_url}" listenTopics="display_estcost" formId="frmEstimate" />
																</td>
															</tr>
														</table>
													</td>
												</tr>
												<s:hidden id="wireMeterPrice" name="wireMeterPrice"></s:hidden>
												<s:hidden id="isNplClicked" name="isNplClicked"></s:hidden>
												<s:hidden id="isMatClicked" name="isMatClicked"></s:hidden>
												<s:hidden id="isOthClicked" name="isOthClicked"></s:hidden>
												<s:hidden id="isAddMatClicked" name="isAddMatClicked"></s:hidden>
												<tr>
													<td colspan="2" class="tdHeadings">Work(Detailed) Estimate <font color="red">*</font></td>
												</tr>
												<tr>
													<td colspan="2" >&nbsp;</td>
												</tr>
												<tr>
													<td colspan="2" >&nbsp;&nbsp;&nbsp;&nbsp;<s:a href="javascript:show_material_section()">Material Costs</s:a>
													&nbsp;&nbsp;&nbsp;&nbsp;<s:a href="javascript:show_labour_section()">Labour Costs</s:a></td>
												</tr>
												<tr>
													<td colspan="2" >&nbsp;</td>
												</tr>
												<tr>
													<td colspan="2">
														<div id="matSec">
														<table  width="100%">
															
															<tr>
																<td width="5"></td>
																<td>
																<!-- Materials display -->
																<s:if test="%{isViewApplicant == 'true'}">
																	<s:url id="add_url" value="MaterialAction!addMaterials" />
																	<s:url id="display_url" value="MaterialAction!displayMaterials" />
																	<table width="100%">
																		<tr>
																			<td>
																				<sx:div id="selMat" theme="simple" href="%{add_url}" listenTopics="add_mat" formId="frmEstimate" ></sx:div>
																				
																				<s:url id="add_cost_url" value="MaterialAction!addOtherCost" />
																				<s:url id="add_nplcost_url" value="MaterialAction!addNPLMatCost" />
																				<s:url id="lab_cost_url" value="MaterialAction!calLabourCost" />
																				<sx:bind targets="selMat" sources="addCostLink" events="href" href="%{#add_cost_url}" listenTopics="add_oth_cost" formId="frmEstimate" />
																				<sx:bind targets="selMat" sources="addNPLLink" events="href" href="%{#add_nplcost_url}" listenTopics="add_npl_cost" formId="frmEstimate" />
																				<sx:bind targets="selMat" sources="txtSelLabQty" events="onkeyup" href="%{#lab_cost_url}" listenTopics="cal_labour_cost" formId="frmEstimate" />
																			</td>
																		</tr>
																		 <s:if test="isUndoReject!='true' && isApprove!='true'">
																		<tr>
																			<td>
																				<s:url id="remove_url" value="MaterialAction!removeMaterials" />
																				<s:if test="%{state != 'print'}">
																					<s:a id="removeLink" href="javascript:remove_materials()">Remove Checked Resources</s:a>
																				</s:if>
																				
																				<sx:bind targets="selMat" sources="removeLink" events="href" href="%{#remove_url}" listenTopics="remove_mat" formId="frmEstimate" />
																			</td>
																		</tr>
																		
																		<tr>
																			<td>
																				&nbsp;
																			</td>
																		</tr>
																		<tr>
																			<td>
																				<s:a id="showLink" href="javascript:show_materials()">Add Materials</s:a>
																				&nbsp;&nbsp;&nbsp;
																				<s:a href="javascript:other_costs()">Add Other Costs</s:a>
																				&nbsp;&nbsp;&nbsp;
																				<!-- <s:a href="javascript:npl_costs()">Add NPL Materials</s:a> -->
																			</td>
																		</tr>
																		<tr>
																			<td align="center" >
																				<sx:div id="otherMat" href="%{#display_url}" listenTopics="display_mat" formId="frmEstimate" /> 
																				
																			</td>
																		</tr>
																		
																		
																		<tr>
																			<td align="center">
																				<s:url id="oth_cost_url" value="MaterialAction!displayOtherCost" />
																				<sx:div id="others" href="%{#oth_cost_url}" listenTopics="display_oth_cost" formId="frmEstimate" />
																			</td>
																		</tr>
																		<tr>
																			<td align="center">
																				<s:url id="npl_cost_url" value="MaterialAction!displayNPLMatCost" />
																				<sx:div id="npl" href="%{#npl_cost_url}" listenTopics="display_npl_cost" formId="frmEstimate" />
																			</td>
																		</tr>
																		</s:if>
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
														</div>
														<div id="labourSec">
														<table  width="100%" >
														<tr>
															<td width="5"></td>
															<td>
															<!-- Labour rates display -->
															<s:if test="%{isViewApplicant == 'true'}">
																<table width="100%">
																	<tr>
																		<td>
																			<s:url id="sel_labour_url" value="LabourAction!displaySelectLabour" />
																			<sx:div id="selLabour" href="%{#sel_labour_url}" listenTopics="display_sel_labour" formId="frmEstimate" />
																		</td>
																	</tr>
																	<s:if test="%{isViewOnly!='true' && isUndoReject!='true' && isApprove!='true' && isPrint!='true'}">
																	<tr>
																		<td>
																			<s:url id="remove_url_labour" action="estimate!removeLabour" />
																			<s:a id="removeLinkLabour" href="javascript:remove_labour('remove')">Remove Checked Resources</s:a>
																			<sx:bind targets="selLabour" sources="removeLinkLabour" events="href" href="%{#remove_url_labour}" listenTopics="remove_labour_items" formId="frmEstimate" />
																			
																		</td>
																	</tr>
																	
																	<tr>
																		<td>
																			&nbsp;
																		</td>
																	</tr>
																	
																	<tr>
																		<td>
																			<s:a id="showAvailLabourLink" href="javascript:show_avail_labour()">Add Other Labour Activities</s:a>
																			
																			
																		</td>
																	</tr>
																	
																		<tr>
																			<td align="center" >
																				<s:url id="avail_labour_url" value="LabourAction!displayAvailLabour" />
																				<sx:div  id="availLabour" href="%{#avail_labour_url}" listenTopics="display_avail_labour" formId="frmEstimate" /> 
																			</td>
																		</tr>
																		
																	</s:if>
																</table>
															</s:if>
															<s:else>
																<table class="tldFromContent"  border="1" width="100%">
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
															</s:else>
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
																	<s:if test="isUndoReject=='true'">
																		<s:if test="%{isViewApplicant == 'true'}">
																			<s:submit theme="simple" value="Undo" method="undo" id="btnSave"></s:submit>
																		</s:if>
																		<s:else>
																			<s:submit theme="simple" value="Undo" id="btnSave" disabled="true"></s:submit>
																		</s:else>
																	</s:if>
																	<s:elseif test="%{isApprove == 'true'}">
																		<s:if test="%{isViewApplicant == 'true'}">
																			<s:submit theme="simple" value="Approve" method="approveJob" id="btnSave"></s:submit>
																			<s:submit theme="simple" value="Reject" method="rejectJob" id="btnReject" onclick="return rejectValidate()"></s:submit>
																		</s:if>
																		<s:else>
																			<s:submit theme="simple" value="Approve" id="btnSave" disabled="true"></s:submit>
																			<s:submit theme="simple" value="Reject" id="btnReject"  disabled="true"></s:submit>
																		</s:else>
																	</s:elseif>
																	<s:else>
																		<s:if test="%{state != 'print'}">
																		<s:if test="%{isViewApplicant == 'true'}">
																			<s:submit theme="simple" value="Save" method="save" id="btnSave" onclick="return reviseFormValidation()"></s:submit>
																			<s:submit theme="simple" value="Send for Approval" method="sendToApproval" id="btnApprove" onclick="return approveConfirm()"></s:submit>
																		</s:if>
																		</s:if>
																		<s:else>
																			
																				<s:submit theme="simple" value="Save" method="save" id="btnSave" disabled="true" ></s:submit>
																				<s:submit theme="simple" value="Send for Approval" id="btnApprove" disabled="true" ></s:submit>
																			
																		</s:else>
																	</s:else>
																	
																		<applet codebase="../../PrintLib" code="standardEstimate.StandardEstimateApp" archive="PrintJar.jar" name="PIVApplet" width="1" height="1"  >
																		</applet>
																	
																	<s:if test="%{state == 'print'}">
																	<s:submit theme="simple" value="Print" id="btnPrint" onclick="javascript:printing()" type="button" ></s:submit>
																	</s:if>
																	<s:submit theme="simple" value="Clear" method="clear" id="btnClear" ></s:submit>
																	<s:submit theme="simple" value="Exit" method="close" id="btnClose" ></s:submit>
																	<s:hidden id="region" name="region"></s:hidden>
																	<s:hidden id="estimateStatus" name="estimateStatus"></s:hidden>
																	<s:hidden id="estimateStatusDesc" name="estimateStatusDesc"></s:hidden>
																	<s:hidden id="city" name="city"></s:hidden>
																	<s:hidden id="suburb" name="suburb"></s:hidden>
																	<s:hidden id="streetAddress" name="streetAddress"></s:hidden>
																	<s:hidden id="postalCode" name="postalCode"></s:hidden>
																	<s:hidden id="state" name="state"></s:hidden>		
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