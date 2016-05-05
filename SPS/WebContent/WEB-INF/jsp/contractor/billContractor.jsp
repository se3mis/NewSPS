<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Bill Contractor</title>
<link rel="stylesheet" type="text/css" href="../../style/style.css" />
<link rel="stylesheet" type="text/css" href="../../style/jquerystyle.css" />
<link rel="stylesheet" type="text/css" href="../../style/jquerystyle1.css" />
<script type="text/javascript" src="../../script/jquerycal1.js"></script>
<script type="text/javascript" src="../../script/jquerycal2.js"></script>
<script type="text/javascript" src="../../script/billContractor.js"></script>
<script type="text/javascript" src="../../script/javascript.js"> </script>
</head>
<body onload="on_load()">
<table id="tblOuter" class="tblOuter">
	<tbody>
		<tr>
			<td><s:form action="billContractor"  method="post"   theme="simple">
			<table id="tblInner" class="tblInner">
				<tbody>
					<tr>
						<td id="tdHeader">
						<table id="tblHeader" class="tblHeader" cellpadding="0"
							cellspacing="0">
							<tbody>
								<tr>
									<td width="736px" height="70px"><img src="../../image/Heading_Left.PNG" width="736" height="70" alt="Heading_Left" /></td>
									<td width="*" bgcolor="#226B9C"></td>
									<td width="198px" height="70px"><img src="../../image/Heading_Right.PNG" width="198" height="70" alt="Heading_Right" /></td>
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
									<td class="tdLogOut"><a href="<s:url action="logout"/>">Logout</a></td>
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
									<td><table width="100%" class="tldFromContent" border="0">
											<thead>
												<tr>
													<th></th>

												</tr>
											</thead>
											<tbody>
												<tr>
													<td class="tdHeadings" colspan="4">Bill Contractor</td>
												</tr>
												<tr>
													<td></td>
												</tr>
												<tr>
													<td></td>
												</tr>
												
												<tr>
													<td>
													<table width="100%" class="tldFromContent" border="0" >
														<thead>
															<tr>
																<th width="3%"></th>
																<th width="20%"></th>
																<th width="10%"></th>
																<th width="7%"></th>
																<th width="5%"></th>
																<th width="7%"></th>
															</tr>
														</thead>
														<tbody>
															<tr>
															<td>&nbsp;</td>
															</tr>	
															<tr>
																<td class="tdHeadings" colspan="4">Job Details</td>
															</tr>
																<tr>
																<td></td>
																<td><s:label value="Job Number" ></s:label></td>
																<td> 
																	<s:select name="jobNumber" 
																			list="listjobNumber" 
																			id="listjobNumber"																			
																			onchange="setJobDetails()"
																			headerKey="-1"												
																			headerValue="-- Please Select --">																			
    																</s:select>
																</td>
																														
																<td></td>																
															</tr>														
															<tr>
																<td></td>
																<td><s:label value="Bill Date (DD/MM/YY)" ></s:label></td>
																<td><s:textfield name="billDate" id="datepicker" size="20" ></s:textfield></td>	
																<td></td>																
															</tr>
															<tr>
																<td></td>
																<td><s:label value="Name" ></s:label></td>
																<td><s:textfield name="name" id="name" size="20" ></s:textfield></td>	
																<td></td>																
															</tr>
															<tr>	
																<td></td>															
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
																<td></td>
																<td><s:label value="Bill Reference No" ></s:label></td>
																<td><s:textfield name="billReferenceNo" id="billReferenceNo" size="20" ></s:textfield></td>	
																<td></td>																
															</tr>
															<tr>
																<td></td>
																<td><s:label value="Job Description" ></s:label></td>
																<td><s:textfield name="jobDescription" id="jobDescription" size="80" ></s:textfield></td>	
																<td></td>																
															</tr>
															<tr>
																<td></td>
																<td><s:label value="Estimated by" ></s:label></td>
																<td><s:textfield name="estimatedBy" id="estimatedBy"  size="20" readonly="true"></s:textfield></td>	
																<td></td>																
															</tr>	
															<tr>
																<td></td>
																<td><s:label value="Distance to Service place" ></s:label></td>
																<td><s:textfield name="distanceToPlace" id="distanceToPlace" size="20" ></s:textfield></td>	
																<td></td>																
															</tr>	
															<tr>
																<td></td>
																<td><s:label value="Service Length" ></s:label></td>
																<td><s:textfield name="serviceLength" id="serviceLength" size="20" ></s:textfield></td>	
																<td></td>																
															</tr>
															
																									
														</tbody>

													</table>
													</td>													
												</tr>
												<tr>
												<td>
												<table width="100%" id="tblBillDetails" class="tldFromContent" border="0" >
														<thead>
															<tr>
																<th width="3%"></th>
																<th width="25%"></th>
																<th width="5%"></th>
																<th width="5%"></th>
																<th width="5%"></th>
																<th width="5%"></th>
																<th width="7%"></th>
																<th width="7%"></th>
																<th width="7%"></th>
																<th width="7%"></th>
															</tr>
														</thead>
														<tbody>	
															<tr>																										
																<td class="tdHeadings" colspan="4">Bill Details</td>														
															</tr>
																<tr>
																<td></td>
																<td></td>
																<td></td>
																<td></td>
																<td></td>
																<td></td>
																<td></td>
																<td></td>																				
																
																<td><s:label value="Amount" style="font-size: 14px;font-weight: bold"></s:label></td>
																																													
																<td></td>																
																</tr>
															<tr>
																<td></td>
																<td><s:label id="lblTransPortPoles" value="Transportation of Poles(pole/km)" ></s:label></td>
																<td> 
																			<s:select headerKey="-1" 
																					headerValue="Select an option"  
																					list="transportationDetailsList" 
																					id = "transportationDetailsList" 	
																					name="transportationType"
																			    	listValue="%{value}" 
																			    	listKey="key"																			    			    	
																			    	emptyOption="false"
																			    	onchange="setAmount(this)"
																			    	
																			    	 />
																</td>
																<td><s:label id="lblnofPoles" value="No Poles" ></s:label></td>
																<td><s:textfield name="nofPoles" id="nofPoles" size="8" onkeypress="return onlyNumbers(event)" ></s:textfield></td>	
																
																<td><s:label id="lblnofKmAdd" value="No Km" ></s:label></td>
																<td><s:textfield name="nofKm" id="nofKm" size="8" ></s:textfield></td>	
																<td><s:submit  value="Add" id="nofKmAdd"  onclick="return setTransportationAmount('transportationDetailsList','nofKmAdd')"></s:submit></td>
																																
																<td><s:textfield name="transportAmount" id="transportAmount" size="8" disabled="true" value="0"></s:textfield></td>	
															
																														
																<td></td>																
															</tr>
															<tr>
															<td></td>
															</tr>
															<tr>
																<td></td>
																<td><s:label id="lblExcavation" value="Excavation,Erection and Dressing Pole" ></s:label></td>
																<td> 
																			<s:select headerKey="-1" 
																					headerValue="Select an option"  
																					list="excavationDetailsList" 
																					id = "excavationDetailsList" 	
																					name="excavationType"
																			    	listValue="%{value}" 
																			    	listKey="key"																			    	    	
																			    	emptyOption="false"
																			    	
																			    	 />
																</td>
																<td><s:label value="No Poles" id="lblnofexcavationPoles"></s:label></td>
																<td><s:textfield name="nofexcavationPoles" id="nofexcavationPoles" size="8" onkeypress="return onlyNumbers(event)" style="visibility: visible;"></s:textfield></td>	
																
																<td>&nbsp;</td>
																<td>&nbsp;</td>
																<td><s:submit value="Add" id="excavationAmountAdd" onclick="return setBillAmount('excavationDetailsList','nofexcavationPoles','excavationAmount','pole','excavationAmountAdd')"></s:submit></td>																																		
																<td><s:textfield name="excavationAmount" id="excavationAmount" size="8" disabled="true" value="0"></s:textfield></td>	
															
																														
																<td></td>																
															</tr>
															<tr>
																<td></td>
															</tr>
															<tr>
																<td></td>
																<td><s:label id="lblexandEre" value="Excavation and Erection of Struts" ></s:label></td>
																<td> 
																			<s:select headerKey="-1" 
																					headerValue="Select an option"  
																					list="erectionDetailsList" 
																					id = "erectionDetailsList" 	
																					name="erectionType"
																			    	listValue="%{value}" 
																			    	listKey="key"																			    	    	
																			    	emptyOption="false"
																			    	
																			    	 />
																</td>
																<td><s:label id="lblnoferectionPoles" value="No Struts" ></s:label></td>
																<td><s:textfield name="noferectionPoles" id="noferectionPoles" size="8" onkeypress="return onlyNumbers(event)" style="visibility: visible;" ></s:textfield></td>	
																<td>&nbsp;</td>
																<td>&nbsp;</td>
																<td><s:submit value="Add" id="erectionAmountAdd" onclick="return setBillAmount('erectionDetailsList','noferectionPoles','erectionAmount','strut','erectionAmountAdd')"></s:submit></td>																																		
															
																<td><s:textfield name="erectionAmount" id="erectionAmount" size="8" disabled="true" value="0"></s:textfield></td>	
															
																														
																<td></td>																
															</tr>
															<tr>
																<td></td>
															</tr>															
															<tr>
																<td></td>
																<td><s:label id="lblExacErec" value="Excavation,Erection and Installation of Stays" ></s:label></td>
																<td> 
																			<s:select headerKey="-1" 
																					headerValue="Select an option"  
																					list="insStaysDetailsList" 
																					id = "insStaysDetailsList" 	
																					name="insStaysType"
																			    	listValue="%{value}" 
																			    	listKey="key"																			    	    	
																			    	emptyOption="false"
																			    	
																			    	 />
																</td>
																<td></td>
																<td></td>
																<td></td>
																<td></td>
																<td><s:submit value="Add" id="insStaysAmountAdd" onclick="return setBillAmountWithOutQty('insStaysDetailsList','insStaysAmount','stay','insStaysAmountAdd')"></s:submit></td>																																	
																<td><s:textfield name="insStaysAmount" id="insStaysAmount" size="8" disabled="true" value="0"></s:textfield></td>	
																																													
																<td></td>																
															</tr>
															<tr>
																<td></td>
															</tr>
															<tr>
																<td></td>
																<td><s:label id="lblStrAlmPVC" value="Stringing or Aluminium PVC duplex service Wire" ></s:label></td>
																<td> 
																			<s:select headerKey="-1" 
																					headerValue="Select an option"  
																					list="wireDetailsList" 
																					id = "wireDetailsList" 	
																					name="wireType"
																			    	listValue="%{value}" 
																			    	listKey="key"
																			    	onchange="showText('wireDetailsList','lblnofAddWireSpan','nofAddWireSpan')"																			    	    	
																			    	emptyOption="false"
																			    	
																			    	 />
																</td>
																			
																<td><s:label value="No Spans" id="lblnofAddWireSpan" style="visibility: hidden;"></s:label></td>
																<td><s:textfield name="nofAddWireSpan" id="nofAddWireSpan" size="8" style="visibility: hidden;"></s:textfield></td>	
																<td></td>
																<td></td>
																<td><s:submit value="Add" id="wireAmountAdd" onclick="return setBillAmount('wireDetailsList','nofAddWireSpan','wireAmount','span','wireAmountAdd')"></s:submit></td>																		
																<td><s:textfield name="wireAmount" id="wireAmount" size="8" disabled="true" value="0"></s:textfield></td>	
																																													
																<td></td>																
																</tr>																
																<tr>
																<td></td>
																</tr>
																<tr>
																<td></td>
																<td><s:label id="lblStrAlFly" value="Stringing or Aluminium (Fly) conductor" ></s:label></td>
																<td> 
																			<s:select headerKey="-1" 
																					headerValue="Select an option"  
																					list="conductorDetailsList" 
																					id = "conductorDetailsList" 	
																					name="conductorType"
																			    	listValue="%{value}" 
																			    	listKey="key"																			    	    	
																			    	emptyOption="false"
																			    	
																			    	 />
																</td>
																<td></td>
																<td></td>
																<td></td>
																<td></td>
																<td><s:submit value="Add" id="conductorAmountAdd" onclick="return setBillAmountWithOutQty('conductorDetailsList','conductorAmount','phase','conductorAmountAdd')"></s:submit></td>																			
																<td><s:textfield name="conductorAmount" id="conductorAmount" size="8" disabled="true" value="0"></s:textfield></td>	
																																													
																<td></td>																
																</tr>
																<tr>
																<td></td>
																</tr>
																<tr>
																<td></td>
																<td><s:label id="lblStrAeCon" value="Stringing of Aerial Bundle conductors(per km)" ></s:label></td>
																<td><s:textfield name="abAmount" id="abAmount" size="8" disabled="true"></s:textfield></td>
																<td><s:label value="No Km" id="lblabnofKm"></s:label></td>
																<td><s:textfield name="abnofKm" id="abnofKm" size="8" ></s:textfield></td>	
																
																<td></td>
																<td></td>	
																<td><s:submit value="Add" id="abconductorAmountAdd" onclick="return setArielBundles('abconductorAmountAdd')"></s:submit></td>																																				
																<td><s:textfield name="abconductorAmount" id="abconductorAmount" size="8" disabled="true" value="0"></s:textfield></td>	
																																													
																<td></td>																
																</tr>
																<tr>
																<td></td>
																</tr>
																<tr>
																<td></td>
																<td><s:label id="lblRemRefix" value="Removal and re-fixing of an existing service" ></s:label></td>
																<td> 
																			<s:select headerKey="-1" 
																					headerValue="Select an option"  
																					list="reapirExistingDetailsList" 
																					id = "reapirExistingDetailsList" 	
																					name="reapirExistingType"
																			    	listValue="%{value}" 
																			    	listKey="key"																			    	    	
																			    	emptyOption="false"
																			    	
																			    	 />
																</td>
																<td></td>
																<td></td>
																<td></td>
																<td></td>
																<td><s:submit value="Add" id="reapirExistingAmountAdd" onclick="return setBillAmountWithOutQty('reapirExistingDetailsList','reapirExistingAmount','phase','reapirExistingAmountAdd')"></s:submit></td>																				
																<td><s:textfield name="reapirExistingAmount" id="reapirExistingAmount" size="8" disabled="true" value="0"></s:textfield></td>	
																																													
																<td></td>																
																</tr>
																<tr>
																<td></td>
																</tr>
																<tr>
																<td></td>
																<td><s:label id="lblChngExSer" value="Changing of an existing service (New wire)" ></s:label></td>
																<td> 
																			<s:select headerKey="-1" 
																					headerValue="Select an option"  
																					list="changeExistingDetailsList" 
																					id = "changeExistingDetailsList" 	
																					name="changeExistingType"
																			    	listValue="%{value}" 
																			    	listKey="key"	
																			    	onchange="showText('changeExistingDetailsList','lblnofAddChngExistgSpan','nofAddChngExistgSpan')"																			    	    	
																			    	emptyOption="false"
																			    	
																			    	 />
																</td>																
																				
																<td><s:label value="No Spans" id='lblnofAddChngExistgSpan' style="visibility: hidden;"></s:label></td>
																<td><s:textfield name="nofAddChngExistgSpan" id="nofAddChngExistgSpan" size="8" style="visibility: hidden;"></s:textfield></td>	
																<td></td>
																<td></td>
																<td><s:submit value="Add" id="changeExistingAmountAdd" onclick="return setBillAmount('changeExistingDetailsList','nofAddChngExistgSpan','changeExistingAmount','span','changeExistingAmountAdd')"></s:submit></td>																				
																<td><s:textfield name="changeExistingAmount" id="changeExistingAmount" size="8" disabled="true" value="0"></s:textfield></td>	
																																													
																<td></td>																
																</tr>
																<tr>
																<td></td>
																</tr>
																<tr>
																<td></td>
																<td><s:label value="Removal of service connection" ></s:label></td>
																<td> 
																			<s:select headerKey="-1" 
																					headerValue="Select an option"  
																					list="removalDetailsList" 
																					id = "removalDetailsList" 	
																					name="removalType"
																			    	listValue="%{value}" 
																			    	listKey="key"																			    	    	
																			    	emptyOption="false"
																			    	
																			    	 />
																</td>
																<td></td>
																<td></td>
																<td></td>
																<td></td>
																<td><s:submit value="Add" id="removalAmountAdd" onclick="return setBillAmountWithOutQty('removalDetailsList','removalAmount','phase','removalAmountAdd')"></s:submit></td>																				
																<td><s:textfield name="removalAmount" id="removalAmount" size="8" disabled="true" value="0"></s:textfield></td>	
																																													
																<td></td>																
																</tr>
																<tr>
																<td></td>
																</tr>
																<tr>
																<td></td>
																<td><s:label value="Removal of struts including accessories" ></s:label></td>
																<td><s:textfield name="remStrutsAccAmount" id="remStrutsAccAmount" size="8" onkeypress="return onlyNumbers(event)"></s:textfield></td>	
																<td></td>
																<td></td>
																<td></td>
																<td></td>
																<td><s:submit value="Add" id="remStrutsAccAmountAdd" onclick="return setRemovalStructs('remStrutsAccAmountAdd')"></s:submit></td>																																			
																<td><s:textfield name="remStrutsAccAmounttot" id="remStrutsAccAmounttot" size="8" disabled="true" value="0"></s:textfield></td>	
																																													
																<td></td>																
																</tr>
																<tr>
																<td></td>
																</tr>
																<tr>
																<td></td>
																<td><s:label value="Removal of Pole" ></s:label></td>
																<td> 
																			<s:select headerKey="-1" 
																					headerValue="Select an option"  
																					list="removalPoleDetailsList" 
																					id = "removalPoleDetailsList" 	
																					name="removalPoleType"
																			    	listValue="%{value}" 
																			    	listKey="key"																			    	    	
																			    	emptyOption="false"
																			    	
																			    	 />
																</td>
																<td><s:label value="No Poles" ></s:label></td>
																<td><s:textfield name="nofremovalPoleAmount" id="nofremovalPoleAmount" size="8" style="visibility: visible;"></s:textfield></td>	

																<td></td>
																<td></td>
																<td><s:submit value="Add" id="removalPoleAmountAdd" onclick="return setBillAmount('removalPoleDetailsList','nofremovalPoleAmount','removalPoleAmount','pole','removalPoleAmountAdd')"></s:submit></td>																				
																<td><s:textfield name="removalPoleAmount" value="0" id="removalPoleAmount" size="8" disabled="true"></s:textfield></td>	
																																													
																<td></td>																
																</tr>
																<tr>
																<td></td>
																<td></td>
																<td>			
																</td>
																<td></td>
																<td></td>
																<td></td>
																<td></td>																
																<td><s:label value="Total" style="font-size: 14px;font-weight: bold"></s:label></td>
																<td><s:textfield name="grandTotal1" id="grandTotal" size="8" value="0" readonly="true"  ></s:textfield></td>	
																<td></td>																
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
				                                                                                <table id="list_table" width="90%" align="left" border="1" style="border-color:#000033;"   >
				                                                                                    <tr class="listColumnHeadingStyle">
				                                                   				                       	<td align="center" width="150px" >Description</td>				                                                                              
				                                                                                        <td align="center" width="50px" >Unit Price</td>	
				                                                                                        <td align="center" width="50px" >Quantity</td>			                                                                                        
				                                                                                        <td align="center" width="50px" >Amount (Rs)</td>
				                                                                                        <td align="center" width="50px" >Remove</td>
				                                                                                        			                                                                                        			                                                                                       
				                                                                                    </tr>	
				                                                                                		
				                                                                                </table>				                                                                           
				                                                                        </td>
				                                                                    </tr>
				                                                              
				                                                                    <tr>
																						<td id="tdBotton" colspan="3">
																							<s:submit theme="simple"  value="Bill" method="bill"  onclick="return saveBillValues()" id="btnBill" disabled="true"></s:submit>
																							<s:reset theme="simple" align="right" value="Clear" id="btnReset" onclick="clearTable()"/>																							
																							<s:submit theme="simple" align="right" value="Close" method="close" id="btnClose" ></s:submit>
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
		</s:form></td>
	</tr>
	</tbody>
</table>

</body>
</html>