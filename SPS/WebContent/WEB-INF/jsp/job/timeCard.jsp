<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Time Card</title>
<link rel="stylesheet" type="text/css" href="../../style/style.css" />
<script type="text/javascript" src="../../script/timeCard.js"></script>
<script type="text/javascript" src="../../script/javascript.js"></script>
<s:head  />
<sx:head />
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
				                                         <td ><img src="../../image/NewSPS2.gif" width="100%"  alt="NewSPS2"/>
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
						<table class="tblWelcome" border="0" cellpadding="0" cellspacing="0">
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
									<td class="tdWelcomeUser"><s:property value="loggedInUserId"></s:property></td>
									<td class="tdLogOut"><a href="<s:url namespace="/estimate/web" action="logout"/>">Logout</a></td>
								</tr>
							</tbody>
						</table>
						</td>
					</tr>
					<tr>
						<td id="tdContent"><s:form action="timeCard"  method="post"   theme="simple">
						<table class="tblContent" border="1" cellpadding="0" cellspacing="0">
							<thead>
								<tr>
									<th></th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>
													<table width="100%" class="tldFromContent" border="0">
										
														<thead>
															<tr>
																<th width="3%"></th>
																<th width="20%"></th>
																<th width="15%"></th>
																<th width="3%"></th>
																<th width="10%"></th>
																<th width="15%"></th>
																<th width="10%"></th>
																<th width="*"></th>														
															</tr>
														</thead>
														<tbody>
															<tr>
															<td>&nbsp;</td>
															</tr>
															<tr>
																<td></td>
																<td class="tdHeadings">Employee Details</td>
																<td></td>																
															</tr>
															
															<tr>
																<td></td>
																<td><s:label value="Cost Center" ></s:label></td>
																<td> 
																	<s:textfield id="txtdcostCenterNo" name="costCenterNo" readonly="true"  size="20" ></s:textfield>
																</td>
																<td></td>
																<td><s:label value="Job No" ></s:label></td>
																<td><s:textfield id="txtdjobNo" name="jobNo"   size="20" ></s:textfield>
    															</td>
																
																<td></td>																
															</tr>
															<tr>
																<td>&nbsp;</td>															
															</tr>
															<tr>
																<td></td>	
																<td><s:label value="Work Type" ></s:label></td>																			
																<td><s:select name="workType" 
																			list="listworkType" 
																			id="listworkType" 
																			headerKey="-1"
																			onchange=""
																			headerValue="-- Please Select --">
    																</s:select>
    															</td>	
    															<td></td>
    															<td><s:label value="Employee No" ></s:label></td>
    															<td><s:textfield id="txtdemployeeNo" name="employeeNo"   size="20" ></s:textfield>															
    																															
															</tr>
															<tr>
																<td>&nbsp;</td>															
															</tr>
															<tr>
																<td></td>
																<td><s:label value="Date Type" ></s:label></td>
																<td><s:select name="dateType" 
																			list="listdateType" 
																			id="listdateType" 
																			headerKey="-1"
																			onchange=""
																			headerValue="-- Please Select --">
    																</s:select>
    															</td>
    															<td></td>
    															<td><s:label value="Date" ></s:label></td>
																<td><sx:datetimepicker name="timeCardDate" id="datepicker" displayFormat="dd/MM/yyyy"  required="true"  /></td>													
																															
															</tr>
														
															<tr>
																<td>&nbsp;</td>															
															</tr>
															<tr>
																<td></td>
																<td><s:label value="Name" ></s:label></td>
																<td> 
																	<s:textfield id="txtdname" name="Name"   size="20" ></s:textfield>
																</td>
																<td></td>
																<td><s:label value="Designation" ></s:label></td>
																<td><s:textfield id="txtddesignation" name="designation"   size="20" ></s:textfield>
    															</td>
																
																<td></td>																
															</tr>

															<tr>
																<td>&nbsp;</td>															
															</tr>
															<tr>
																<td></td>																
																<td class="tdHeadings">WORKING DETAILS</td>
															</tr>
															<tr>
																<td>&nbsp;</td>															
															</tr>
													
															<tr>
																<td></td>
																<td><s:label value="Actual Working Hours/Day" ></s:label></td>
																<td> 
																	<s:textfield id="txtdActualWorkingHours" name="ActualWorkingHours"   size="20" ></s:textfield>
																</td>
																<td></td>
																<td></td>
																<td></td>
																
																<td></td>																
															</tr>
															<tr>
																<td>&nbsp;</td>															
															</tr>
															<tr>
																<td></td>
																<td><s:label value="Normal H/Pay" ></s:label></td>
																<td><s:textfield id="txtdnormalHpay" name="normalHpay"   size="20" ></s:textfield></td>
																<td></td>
																<td><s:label value="Normal OT" ></s:label></td>
																<td><s:textfield id="txtdnormalOT" name="normalOT"   size="20" ></s:textfield>
    															</td>																
																<td></td>																
															</tr>
															<tr>
																<td>&nbsp;</td>															
															</tr>
															<tr>
																<td></td>
																<td><s:label value="Sunday H/Pay" ></s:label></td>
																<td><s:textfield id="txtdsundayHpay" name="sundayHpay"   size="20" ></s:textfield></td>
																<td></td>
																<td><s:label value="Sunday OT" ></s:label></td>
																<td><s:textfield id="txtdsundayOT" name="sundayOT"   size="20" ></s:textfield>
    															</td>																
																<td></td>																
															</tr>
															<tr>
																<td>&nbsp;</td>															
															</tr>
															<tr>
																<td></td>
																<td><s:label value="Declared H/Pay" ></s:label></td>
																<td><s:textfield id="txtddeclaredHpay" name="declaredHpay"   size="20" ></s:textfield></td>
																<td></td>
																<td><s:label value="Declared OT" ></s:label></td>
																<td><s:textfield id="txtddeclaredOT" name="declaredOT"   size="20" ></s:textfield>
    															</td>																
																<td></td>																
															</tr>
															<tr>
																<td>&nbsp;</td>															
															</tr>
															<tr>
																<td></td>
																<td><s:label value="Un allocated Time" ></s:label></td>
																<td><s:textfield id="txtdunalloctdTime" name="unalloctdTime"   size="20" ></s:textfield></td>
																<td></td>
																<td></td>
																<td></td>																
																<td></td>																
															</tr>
															<tr>
																<td>&nbsp;</td>															
															</tr>
															<tr>
																<td></td>
																<td><s:label value="Remarks" ></s:label></td>
																<td><s:textarea id="txtdremarks" name="reMarks"  cols="20" rows="2" ></s:textarea></td>
																<td></td>
																<td></td>
																<td></td>																
																<td></td>																
															</tr>
															<tr>
																<td>&nbsp;</td>															
															</tr>
															<tr>	
																<td></td>
																<td></td>															
																<td><s:label name="statusMsg" id="statusMsg" cssClass="STATUSMSG"></s:label></td>
															</tr>
															<tr>	
																<td></td>
																<td></td>
																<td><s:label name="statusMsgErr" id="statusMsgErr" cssClass="STATUSMSGERR"></s:label></td>		
															</tr>
																											
														</tbody>

													</table>
													</td>
												</tr>
												<tr>
												<td>
												<table width="100%" class="tldFromContent" border="0"  >
														
														<tbody>
															<tr>
																<td>
																<table width="100%" border="0" align="center" bgcolor="#FBFFFF" cellpadding="0" cellspacing="0" style="border-collapse:collapse"  >
																	<tr>
																																
																		<td class="tdHeadings" style="color: green ">EXISTING DETAILS</td>
																		</tr>
																     <tr>		
																     <td>		                                                                        
				                                                                        <s:hidden name="list" id="list"></s:hidden>
				                                                                            <s:if test="list.size > 0">
				                                                                                <table id="list_table" width="95%" align="center" border="1" style="border-color:#000033;border-collapse:collapse"   >
				                                                                                    <tr class="listColumnHeadingStyle">
				                                                                                    	<td align="center">Job No</td>
				                                                                                        <td align="left">Job Category</td>
				                                                                                        <td align="left">Job Description</td>
				                                                                                        <td align="left">Date Type</td>
				                                                                                        <td align="left">Time-From</td>	
				                                                                                        <td align="left">Time-To</td>				
				                                                                                    </tr>
				                                                                                    <%int i=0; %>
				                                                                                    <s:iterator value="list"  id="listColumn">
				                                                                                        <tr id="row_<s:property value="jobNo"/>" class="listRowStyle">
				                                                                                            <td align="center">
				                                                                                                 <s:property value="jobNo" />
				                                                                                            </td>
				                                                                                            <td align="left">
				                                                                                                <s:property value="jobCategory" />
				                                                                                            </td>
				                                                                                            <td align="left">
				                                                                                               <s:property value="jobDescription" />
				                                                                                            </td>
				                                                                                            <td align="left">
				                                                                                               <s:property value="dateType" />
				                                                                                            </td >  
				                                                                                             <td align="left">
				                                                                                               <s:property value="timeFrom" />
				                                                                                            </td >  
				                                                                                             <td align="left">
				                                                                                               <s:property value="timeTo" />
				                                                                                            </td >                                                                         
				                                                                                        </tr>
				                                                                                        <%i++; %>
				                                                                                    </s:iterator>
				                                                                                </table>
				                                                                            </s:if>
				                                                                        </td>
				                                                                    </tr>	
				                                                               <tr><td>&nbsp;</td></tr>
				                                                              <tr align="center">
				                                                                 <td ><s:label name="timecardTableMsg" id="timecardTableMsg" cssClass="STATUSMSG" ></s:label></td>
				                                                              </tr>					                                                               									
																		</table>																
																</td>																
															</tr>
														</tbody>
													</table>	
													</td>
												</tr>	
															
												<tr>
													<td id="tdBotton" colspan="3">
														
														<s:submit theme="simple" value="Save" method="save"  id="btnsave" onclick="return validate_form()"></s:submit>
														<s:reset theme="simple" align="right" value="Clear" id="btnReset" />																							
														<s:submit theme="simple" value="Exit" method="close"  id="btnClose" ></s:submit>																											
													</td>
									
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
</body>
</html>