<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<html>
<head>
<title>New Job Allocation</title>
<link rel="stylesheet" type="text/css" href="../../style/style.css" />
<script type="text/javascript" src="../../script/newJobAllocation.js"> </script>
</head>
<body onload="on_load()">
<table id="tblOuter" class="tblOuter">
	<tbody>
		<tr>
		 <td><s:form action="newJobAllocationBS"  method="post"   theme="simple">
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
																<td class="tdHeadings">NEW JOB ALLOCATION - CEYLON ELECTRICITY BOARD</td>
																<td></td>
																<td class="tdMsgStyle"><s:property  value="errorMessage" ></s:property></td>
															</tr>
								<tr>
									<td>
													<table width="100%" class="tldFromContent" border="0">
														<thead>
															<tr>
																<th width="10%"></th>
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
														
														</tr>
														
														<tr>																
																<td><s:label value="Contractor" ></s:label></td>
																<td> 
																	<s:select 
																			name="contractor" 
																			list="listContractor" 
																			id="listContractor" 
																			headerKey="-1"	
																			onchange="setContractorDetails()"																		
																			headerValue="-- Please Select --">
    																</s:select>
																</td>																
															
														
															<td></td>
															<td><s:label  value="Job Allocation Date" id="labelJobAllocationDate"></s:label></td>
															<td><s:textfield name="jobAllocationDate" disabled="true" id="datepicker" size="20" ></s:textfield></td>
															<td><s:textfield name="contractDetails" disabled="true" id="contractDetails" size="20"  style="visibility:hidden"></s:textfield></td>
    														<td > 
																<s:select style="visibility: hidden;"
																			name="contractorID" 
																			list="listContractorID" 
																			id="listContractorID" 
																			headerKey="-1"												
																			headerValue="-- Please Select --">
    															</s:select>
															</td>
														</tr>
														<tr>
															
															<td><s:label value="Jobs in Hand" id="labelJobInHand"></s:label></td>
															<td><s:textfield name="jobInHand" disabled="true" id="txfdjobInHand" size="23" maxlength="21" ></s:textfield></td>
														
															<td></td>
															<td><s:label value="Total Amount" id="labelTotalAmount"></s:label></td>
															<td><s:textfield name="totalAmount" disabled="true" id="txfdtotalAmount" size="23" maxlength="21" ></s:textfield></td>
															
														</tr>															
													
														<tr>
														<td><s:hidden name="refList" id="refList"></s:hidden></td>
														</tr>
			
									
																											
														</tbody>
										</table>

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
								<tr>
									<td></td>
								</tr>
							</tbody>
						</table>
						</td>
					</tr>
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
				                                                                                <table id="list_table" width="70%" align="left" border="1" style="border-color:#000033;"   >
				                                                                                    <tr class="listColumnHeadingStyle">
				                                                                                    	<td align="center" width="5px" >Select</td>
				                                                                                    	<td align="center" width="30px" >Job No</td>
				                                                                              
				                                                                                        <td align="center" width="50px" >Consumer Name</td>				                                                                                        
				                                                                                        <td align="center" width="50px" >Amount (Rs)</td>
				                                                                                        			                                                                                        			                                                                                       
				                                                                                    </tr>	
				                                                                                     <%int i=0; %>
				                                                                                    <s:iterator value="list"  id="listColumn">
				                                                                                        <tr id="row_<s:property value="jobNo"/>" class="listRowStyle" >				                                                                                        	
				                                                                                         	<td align="center" class="FORMDEFAULT">
				                                                                                               <input id="tdchkrow_<%= i %>" type="checkbox" >
				                                                                                            </td>
				                                                                                            <td align="left" id="tdjobrow_<%= i %>" class="FORMDEFAULT" >
				                                                                                               <s:property  value="jobNo" />
				                                                                                            </td>
				                                                                                            <td align="left" id="tdnamerow_<%= i %>" class="FORMDEFAULT">
				                                                                                                <s:property value="consumerName" />
				                                                                                            </td>
				                                                                                            <td align="left" id="tdamtrow_<%= i %>" class="FORMDEFAULT">
																												<s:property value="jobAmount" />
				                                                                                            </td>
				                                                                                           		                                                                                                                                                                  
				                                                                                        </tr>
				                                                                                        <%i++; %>
				                                                                                    </s:iterator>			
				                                                                                </table>				                                                                           
				                                                                        </td>
				                                                                    </tr>
				                                                                    	<tr>
																							<td id="tdBotton" colspan="3" align="left">															
																								<s:submit theme="simple" value="Allocate" method="Save"  disabled="true" id="btnAllocate" onclick="return saveValues()" ></s:submit>
<!--																								<s:submit theme="simple" value="Print" method=""  id="btnPrint" ></s:submit>																								-->
																								<s:submit theme="simple" value="Exit" method="Close"  id="btnClose" ></s:submit>										
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
										
				</tbody>
			</table>
			</s:form></td>
		</tr>
			<tr>
						<td id="tdFooter">
						<table class="tblFooter" border="1" cellpadding="0"
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
</body>
</html>