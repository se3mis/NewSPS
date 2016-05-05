<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Soft Job Close</title>
<link rel="stylesheet" type="text/css" href="../../style/style.css" />
<script type="text/javascript" src="../../script/SoftJobCloseGroup.js"></script>
<s:head  />
<sx:head />

</head>
<body >
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
									<td><s:form action="softjobCloseGroupBS"  method="post"   theme="simple">
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
																	<sx:datetimepicker name="closingDate" id="datepicker" displayFormat="dd/MM/yyyy"  required="true"  />													
																
																</td>
																<td></td>																
															</tr>
															
															<tr>
																<td>&nbsp;</td>	
															</tr>																											
															<tr>
															<td></td>																
																
																											
														</tbody>
										</table>
										<tr>
												<td>
											<table class="tblList"  border="1" cellpadding="0" cellspacing="0" >
				                                            <thead>
				                                                <tr>
				                                                    <th></th>
				                                                </tr>
				                                            </thead>
				                                            <tbody>
				                                                <tr>
				                                                    <td>			                                                      
				                                                            <table id="tdList" width="100%"  border="0" align="center" bgcolor="#FBFFFF" cellpadding="0" cellspacing="0" >
				                                                                <tbody>
				                                                                    <tr>
				                                                                        <td >
				                                                                               <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" style="border-collapse:collapse"  >
																							      <tr>
																							     		<s:hidden name="list" id="list"></s:hidden>
											                                                            <s:if test="list.size > 0">
											                                                          	<td align="center" class="listHeader" width="150px">Job Details				                                                            
											                                                            <table id="stdLayoutSwap0" width="100%" align="center" border="1" style="border-color:#000033;border-collapse:collapse"   >
											                                                            	<tr class="listColumnHeadingStyle" >
											                                                            		<td align="center" width="5px">Id</td>
											                                                            		<td align="center" width="50px">PaySlip No</td>
											                                                              		<td align="center" width="50px">Job No</td>				                                                              		
											                                                                    <td align="center" width="50px">Slip Amt</td>
											                                                                    <td align="center" width="50px">Chq Date</td>	
											                                                       			</tr>		  
																											   <%int i=0; %>
														                                                        <s:iterator value="list"  id="listColumn">
														                                                             <tr id="row_<s:property value="jobNo"/>" class="listRowStyle">
														                                                             	<td align="center"><s:checkbox fieldValue="%{jobNo}" name="jobNumberList" id="X1%{jobNo}"  onclick="setSelectedList(this)"></s:checkbox></td>
														                                                             	<td align="center" class="FORMDEFAULT" ><s:property value="stringdocNo" /></td>
														                                                             	<td align="center" class="FORMDEFAULT" ><s:property value="jobNo" /></td>				                                                             	
														                                                             	<td align="center" class="FORMDEFAULT" ><s:property value="stringpaySlipAmount" /></td>	
														                                                             	<td align="center" class="FORMDEFAULT" ><s:property value="chequeDate" /></td>				                                                                                                                                                                   
														                                                             </tr>
														                                                       <%i++; %>
																                                                     </s:iterator>
																                                                   </table>
																                                                  </s:if>
																                                                 </tr>				                                            										
																										</table>				                                                                           
				                                                                        </td>				                                                                        
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
				                                                    </td>
				                                                </tr>
				                                                <tr>
				                                                    <td></td>
				                                                </tr>
				                 </tbody>
				        </table>
					
					</td>
										</tr>

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