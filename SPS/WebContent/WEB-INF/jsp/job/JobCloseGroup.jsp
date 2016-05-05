<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Job Close</title>
<link rel="stylesheet" type="text/css" href="../../style/style.css" />
<script type="text/javascript" src="../../script/JobCloseGroup.js"></script>
<s:head  />
<sx:head />

</head>
<body onload="on_load()">
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
									<td><s:form action="jobCloseGroupBS"  method="post"   theme="simple">
													<table width="100%" class="tldFromContent" border="0">
														<s:textfield name="selectedNo" id="txtdselectedNo"  size="20" style="display:none" ></s:textfield>
														<s:textfield name="closingDate" id="txtdclosingDate"  size="20" style="display:none" ></s:textfield>
														<thead>
															<tr>
																<th width="5%"></th>
																<th width="10%"></th>
																<th width="20%"></th>
																<th width="5%"></th>																
															</tr>
														</thead>
														<tbody>
															<tr>
															<td>&nbsp;</td>
															</tr>															
															<tr>
																<td></td>
																<td><s:label value="Cost Center" ></s:label></td>
																<td>
																	<s:textfield name="costCenter" readonly="true"  size="20" ></s:textfield>
																</td>																
																<td></td>																
															</tr>															
															<tr>
																<td></td>
																<td><s:label value="Closing Date" ></s:label></td>
																<td>			
																	<sx:datetimepicker name="appointmentDate" id="datepicker" displayFormat="dd/MM/yyyy"  required="true"  />													
																
																</td>
																<td></td>																
															</tr>	
															<tr>
																<td>&nbsp;</td>	
															</tr>																											
															<tr>
															<td></td>
																<td><s:label value="Job Numbers" ></s:label></td>
																<td>
																
																 <div style="overflow:scroll; height:200px;" >
											 -					
																
																<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" style="border-collapse:collapse"  >
																      <tr>
				                                                                        <td align="center" class="listHeader" width="150px">Job Details
				                                                                        <s:hidden name="list" id="list"></s:hidden>
				                                                                            <s:if test="list.size > 0">
				                                                                                <table id="stdLayoutSwap0" width="100%" align="center" border="1" style="border-color:#000033;border-collapse:collapse"   >
				                                                                                    <tr class="listColumnHeadingStyle" >
				                                                                                    	<td align="center" width="150px">Job No</td>
				                                                                                    </tr>				                                           
																
																	 <%int i=0; %>
				                                                        <s:iterator value="list"  id="listColumn">
				                                                             <tr  onclick="prepareSwap(this, 'FORMSWAP')" style="cursor:pointer;cursor:hand;" id="row_<s:property value="jobNo"/>" class="listRowStyle">					                                                             		                                                                                            
				                                                             <td align="center" class="FORMDEFAULT" >
				                                                             <s:property value="jobNo" />
				                                                             </td>				                                                                                                                                                                   
				                                                             </tr>
				                                                       <%i++; %>
				                                                     </s:iterator>
				                                                   </table>
				                                                  </s:if>
				                                                 </td>
				                                               </tr>				                                            										
														</table>
													</div>
																</td>
																<td>
																<s:select list="listreferenceNo"   multiple="multiple" id="stdLayoutSwapHS0" style="display:none"></s:select>
																
																</td>
																<td align="center">
																<s:submit theme="simple" value=">>"  id="btnSelect" onclick="return swap('stdLayoutSwap0', 'stdLayoutSwapHS0', 'stdLayoutSwap1', 'stdLayoutSwapHS1')"></s:submit>
																<s:submit theme="simple" value="<<"  id="btnDeSelect" onclick="return swap('stdLayoutSwap1', 'stdLayoutSwapHS1', 'stdLayoutSwap0', 'stdLayoutSwapHS0')"></s:submit>
																</td>
																<td>
																<div  >
																<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" style="border-collapse:collapse"  >
																      <tr>
				                                                            <td align="center" class="listHeader">
				                                                                 <s:hidden name="list" id="list"></s:hidden>
				                                                                 <s:if test="list.size > 0">
				                                                                 <table id="stdLayoutSwap1"  align="center" border="1" style="border-color:#000033;border-collapse:collapse"   >
				                                                                 <tr class="listColumnHeadingStyle">
				                                                             <td align="center">Selected No</td>
				                                                              
				                                                      </tr>	
				                                                      			                                           
																
				                                                   			</table>
				                                                 		 </s:if>
				                                                 		</td>
				                                               			</tr>											
																	</table>
																</div>
																
																</td>
																<td>
																<select  multiple="multiple" id="stdLayoutSwapHS1" style="display:none"></select>
																</td>	
																<td></td>												
															</tr>	
															<tr>															
																<td colspan="5"><s:label name="statusMsg" id="statusMsg" cssClass="STATUSMSG"></s:label></td>
															</tr>
																<tr><td>&nbsp;</td></tr>
															<tr>
																<td colspan="5"><s:label name="statusMsgNotClosed" id="statusMsgNotClosed" cssClass="STATUSMSGERR"></s:label></td>		
															</tr>
																<tr><td>&nbsp;</td></tr>
															<tr>
																<td colspan="5"><s:label name="statusMsgErr" id="statusMsgErr" cssClass="STATUSMSGERR"></s:label></td>		
															</tr>
																<tr><td>&nbsp;</td></tr>
															  <tr>
																	<td id="tdBotton" colspan="3">
																		<s:submit theme="simple" value="Job Close" method="jobclose"  id="btnjobClose" onclick="return jobClose()"></s:submit>
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
			</td>
		</tr>
	</tbody>
</table>
</body>
</html>