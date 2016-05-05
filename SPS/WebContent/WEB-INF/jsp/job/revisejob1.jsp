<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Estimate</title>
<link rel="stylesheet" type="text/css" href="../../style/style.css" />
<script type="text/javascript" src="../../script/job.js"> </script>
<script type="text/javascript" src="../../script/javascript.js"> </script>
<script type="text/javascript">
function abc(divid,imgId)
{
	 if(document.getElementById(divid).style.display == 'none'){
		 document.getElementById(imgId).src ="../../image/down.jpg"
      document.getElementById(divid).style.display = 'block';
    }else{
      document.getElementById(divid).style.display = 'none';
	  document.getElementById(imgId).src ="../../image/up.jpg"
    }
}
</script>
<s:head />
</head>
<body onload="buttonLoader()">
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
									<td class="tdLogOut"><a href=" ">Logout</a></td>
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
									<td><s:form action="ReviseJob" method="POST"
										validate="true" theme="simple">
										<table width="100%" class="tldFromContent" border="0">
											<thead>
												<tr>
													<th></th>

												</tr>
											</thead>
											<tbody>
												<tr>
													<td class="listHeader" colspan="4">Consumer
													Registration</td>
												</tr>
												<tr>
													<td></td>
												</tr>
												<tr>
													<td></td>
												</tr>
												<tr>
													<td class="tdHeadings" colspan="4"><img
														src="../../image/down.jpg" name="imgUpDown" align="middle"
														id="imgUpDown" onclick="abc('myDivx','imgUpDown')" />
													Application Detail</td>
												</tr>
												<tr>
													<td>
													<div id="myDivx">
													<table width="100%" border="0">
														<tr>
															<td width="14%">Job Number</td>
															<td width="1%">:</td>
															<td><s:textfield name="projectNo"
																id="txtProjectNo" size="23" maxlength="21"
																onblur="javascript:{this.value = this.value.toUpperCase(); }"></s:textfield>
															<s:submit theme="simple" align="right" value="View"
																method="viewJob" id="btnViewJob"
																onclick="return checkJobNoNotNull()"></s:submit></td>
														</tr>
														<tr>
															<td>Applicatin Date</td>
															<td>:</td>
															<td><s:property value="date" /></td>
														</tr>
														<tr>
															<td>Applicant Name</td>
															<td>:</td>
															<td>Kumara Thilakasiri Kankanamge :</td>
														</tr>
														<tr>
															<td>Address</td>
															<td>:</td>
															<td>234,A Rajamalwaththa Mawatha, Malsiripura
															waththa, Polonaruwa</td>
														</tr>
														<tr>
															<td>Cost Center</td>
															<td>:</td>
															<td>
															<s:if test="costCenterNo!=NULL">
																<s:property value="costCenterNo" />
															</s:if> 
															<s:property value="costCenterNo" />
															 </td>
														</tr>
														<tr>
															<td>Assesment Number</td>
															<td>:</td>
															<td><s:if test="assessmentNo!=NULL">
																<s:property value="assessmentNo" />
															</s:if> 
														 
														
														</tr>
														<tr>
															<td>Neighbours Acc. No</td>
															<td>:</td>
															<td><s:if test="neighboursAccNo!=NULL">
																<s:property value="neighboursAccNo" />
															</s:if> 
															 </td>
														</tr>
														<tr>
															<td>Description</td>
															<td>:</td>
															<td><s:if test="description!=NULL">
																<s:property value="description" default="" />
															</s:if> 
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
													<td class="tdHeadings" colspan="4"><img
														src="../../image/down.jpg" alt="" name="imgUpDown2"
														align="middle" id="imgUpDown2"
														onclick="abc('myDivx2','imgUpDown2')" />Details of Wiring</td>
												</tr>

												<tr>
													<td>
													<div id="myDivx2">
													<table width="100%" border="0">
														<tr>
															<td>Phase Type</td>
															<td>:</td>
															<td><s:property value="phase" default="" /> </td>
														</tr>
														<tr>
															<td>Connection Type</td>
															<td>:</td>
															<td><s:property value="connectionType" default="" /> </td>
														</tr>
														
														<tr>
															<td>Customer Category</td>
															<td>:</td>
															<td><s:property value="customerCategory" default="" /></td>
														</tr>
														
														<tr>
															<td>Tariff Category</td>
															<td>:</td>
															<td><s:property value="tariffCode" default="" /></td>
														</tr>
														<tr>
															<td>Tariff Code</td>
															<td>:</td>
															<td>11</td>
														</tr>
														<tr>
															<td width="14%">SIN Number</td>
															<td width="1%">:</td>
															<td width="85%"><s:textfield name="sinNo" id="txfdSinNo" size="10"></s:textfield></td>
														</tr>
														<tr>
															<td>Phase</td>
															<td>:</td>
															<td>1,2,3</td>
														</tr>
														<tr>
															<td>Length</td>
															<td>:</td>
															<td><input type="text" name="textfield2"
																id="textfield2" /></td>
														</tr>
														<tr>
															<td>Is Loop Service</td>
															<td>:</td>
															<td><s:property value="isLoopService" default="" /></td>
														</tr>
														<tr>
															<td>Loop Length(m)</td>
															<td>:</td>
															<td><input type="text" name="textfield3"
																id="textfield3" /></td>
														</tr>
														<tr>
															<td>Distance to Service Place(Km)</td>
															<td>:</td>
															<td><input type="text" name="textfield6"
																id="textfield6" /></td>
														</tr>
														<tr>
															<td>Spans</td>
															<td>:</td>
															<td><input type="text" name="textfield4"
																id="textfield4" /></td>
														</tr>
														<tr>
															<td>Estimation Type</td>
															<td>:</td>
															<td><select name="select" id="select">
																<option>Normal Service</option>
																<option>Service with Poles</option>
																<option>Other</option>
															</select></td>
														</tr>
														<tr>
															<td>Bare  Conductor  length(Lb) in meters</td>
															<td>:</td>
															<td><input type="text" name="textfield5"
																id="textfield5" /></td>
														</tr>
														<tr>
															<td>Land Dispute</td>
															<td>:</td>
															<td>No</td>
														</tr>
														<tr>
															<td>D_Notice Issued</td>
															<td>:</td>
															<td>Yes</td>
														</tr>
														<tr>
															<td>D_Notice Date</td>
															<td>:</td>
															<td><label for="select"> 2010 August 14</label></td>
														</tr>
														<tr>
															<td>Item Quntities</td>
															<td>:</td>
															<td><input type="text" name="textfield7"
																id="textfield7" /></td>
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
													<td class="tdHeadings" colspan="4">Cost Details</td>
												</tr>

												<tr>
													<td>
													<table width="100%" border="0">
														<tr>
															<td width="14%">Fixed Cost</td>
															<td width="1%">:</td>
															<td width="15%">
															<div align="right">1200.00</div>
															</td>
															<td width="2%">&nbsp;</td>
															<td width="14%">Variable Cost</td>
															<td width="1%">:</td>
															<td width="15%">
															<div align="right">45345.00</div>
															</td>
															<td width="38%">&nbsp;</td>
														</tr>
														<tr>
															<td>Sub Total</td>
															<td>:</td>
															<td>
															<div align="right">3244.00</div>
															</td>
															<td>&nbsp;</td>
															<td>Conversion Cost</td>
															<td>:</td>
															<td>
															<div align="right">4546.00</div>
															</td>
															<td>&nbsp;</td>
														</tr>
														<tr>
															<td>Tax Amount</td>
															<td>:</td>
															<td>
															<div align="right">4545.00</div>
															</td>
															<td>&nbsp;</td>
															<td>Security Amount</td>
															<td>:</td>
															<td>
															<div align="right">455.00</div>
															</td>
															<td>&nbsp;</td>
														</tr>
														<tr>
															<td>Other Cost</td>
															<td>:</td>
															<td>
															<div align="right">66545.00</div>
															</td>
															<td>&nbsp;</td>
															<td>&nbsp;</td>
															<td>&nbsp;</td>
															<td>
															<div align="right"></div>
															</td>
															<td>&nbsp;</td>
														</tr>
													</table>
													</td>
												</tr>


												<tr>
													<td></td>
												</tr>


												<tr>
													<td id="tdBotton2" colspan="3">&nbsp;</td>
												</tr>

												<tr>
													<td class="tdHeadings" colspan="4">Estimate Details</td>
												</tr>
												<tr>
													<td><label for="textarea"></label> <textarea
														name="textarea" id="textarea" cols="45" rows="5"></textarea></td>
												</tr>
												<tr>
													<td id="tdBotton" colspan="3"><input theme="simple"
														type="submit" value="Save" method="save" id="btnSave"
														onclick="return validate_form()" /> <input type="submit"
														theme="simple" align="right" value="Next" method="next"
														id="btnNext" onclick="return validate_form()"></input> <input
														theme="simple" align="right" value="Close" type="submit"
														method="close" id="btnClose"></input> <input
														theme="simple" type="submit" align="right" value="Clear" /></td>
												</tr>
											</tbody>
										</table>

									</s:form></td>
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