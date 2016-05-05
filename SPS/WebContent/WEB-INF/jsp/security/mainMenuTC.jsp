<%--
    Document   : mainMenu CR
    Created on : JAN 06, 2011, 11:21:45 AM
    Author     : Dileepa Waduge
--%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Main Menu TC</title>
        <!--link rel="stylesheet" type="text/css" href="../resources/style.css" /-->
        <link rel="stylesheet" type="text/css" href="../../style/style.css" />
        <script type="text/javascript" src="../../script/mainMenu.js"></script>
        <!-- <link rel="stylesheet" type="text/css" href="WEB-INF/style/style.css" />
         <link rel="stylesheet" type="text/css" href="/WEB-INF/style/style.css" />
         <link rel="stylesheet" type="text/css" href="../WEB-INF/style/style.css" />
         <link rel="stylesheet" type="text/css" href="../../WEB-INF/style/style.css" />
         <link rel="stylesheet" type="text/css" href="../../../WEB-INF/style/style.css" />
         <link rel="stylesheet" type="text/css" href="/../WEB-INF/style/style.css" />
         <link rel="stylesheet" type="text/css" href="/../../WEB-INF/style/style.css" />
         <link rel="stylesheet" type="text/css" href="/../../../WEB-INF/style/style.css" />
         <link rel="stylesheet" type="text/css" href="../style/style.css" />
         <link rel="stylesheet" type="text/css" href="style/style.css" />
         <link rel="stylesheet" type="text/css" href="../../style/style.css" />

        -->
    </head>
    <body >
        <table id="tblOuter"   width="100%" border="0" bgcolor="#4B8EC0">
            <tbody>
                <tr>
                    <td>
                        <table id="inner" width="97%" border="0" align="center">
                            <tbody>
                                <tr>
                                    <td id="tdHeader" >
                                        <table id="tblHeader" width="100%" border="0" align="center" cellpadding="0" cellspacing="0" style="border-collapse:collapse">
                                            <tbody>
                                                <tr>
                                                    <td width="100%" height="70px"><img src="../../image/NewSPS2.gif" width="100%"  alt="NewSPS2"/>
                                                    </td>
                                                    <td width="*" bgcolor="#000033"></td>
                                                    <td width="*" bgcolor="#000033"></td>
                                                </tr>  
                                            </tbody>
                                        </table>
                                    </td>
                                </tr>
                                <!-- tr>
                                    <td>
                                    </td>
                                </tr-->
                                <tr>

                                    <td >
                                        <table width="100*" class="tblWelcome" border="0" cellpadding="0" cellspacing="0">
                                            <thead>
                                                <tr>
                                                    <th width="70%"></th>
                                                    <th width="9%"></th>
                                                    <th width="9%"></th>
                                                    <th width="7%"></th>
                                                    <th width="*"></th>

                                                </tr>
                                            </thead>
                                            <tbody>
                                                <tr>
                                                    <td valign="top"><s:form  action="MainMenu" method="POST">
                                                            <div  align="left"   class="nav-container-outer" >
                                                                <ul class="nav-container" id="nav-container">
                                                                    <li>
                                                                        <a class="item-primary" onclick="return false"> Security </a>
                                                                        <ul style="width:150px;">
                                                                            <li>
                                                                                <a href="Administration" onclick="return false">Administration</a>
                                                                                <ul style="padding-top :0px;  ">
                                                                                    <li>
                                                                                        <!--<a href="User Manager">User</a>-->
                                                                                        <a href="<s:url action="userAction"/>">User Manager</a>
                                                                                    </li>
                                                                                    <li>
                                                                                        <a href="User Role Manager">User Role Manager</a>
                                                                                    </li>
                                                                                    <li>
                                                                                        <a href="Role Manager">Role Manager</a>
                                                                                    </li>
                                                                                    <li>
                                                                                        <a href="Function Manager">Function Manager</a>
                                                                                    </li>
                                                                                </ul>
                                                                            </li>
                                                                            <li>
                                                                                <a href="Change Password">Change Password</a>
                                                                            </li>
                                                                        </ul>
                                                                    </li>
                                                                    <li>
                                                                        <a class="item-primary" href="#" onclick="return false"> Masters</a>
                                                                        <ul style="width:150px;">
                                                                        	<li>
                                                                                <a href="<s:url namespace="/masters/web" action="loadSPCSTPage"/>">Costing</a>
                                                                            </li>
                                                                            <li>
                                                                                <a href="<s:url namespace="/masters/web" action="loadSPESTMTMage"/>">Estimate Materials</a>
                                                                            </li>
                                                                            <li>
                                                                            	<a href="<s:url namespace="/masters/web" action="AddNewContractor"/>">Add New Contractor</a>
                                                                            </li>
																			<li>
																			 	<a href="<s:url  namespace="/masters/web" action="UpdateContractor"/>">Update Contractor</a>
																			</li>
																			<li>
																			 	<a href="<s:url  namespace="/masters/web" action="CostCenterDetails"/>">Cost Center Detials</a>
																			</li>
                                                                            
                                                                        </ul>
                                                                    </li>
                                                                    <li>
                                                                        <!-- a href="<s:url namespace="/application/web" action="application"/>">Application</a-->
                                                                        <a class="item-primary" href="#" onclick="return false">Application</a>
                                                                        <ul style="width:150px;">
                                                                        	<li>
                                                                                <a href="Applicant" onclick="return false">Customer</a>
                                                                                <ul style="padding-top :0px; ">
                                                                                    <li>
		                                                                                <a href="<s:url namespace="/application/web" action="customerRegistration"/>">New Customer</a>
		                                                                            </li>
		                                                                            <li>
		                                                                                <a href="<s:url namespace="/application/web" action="modifyCustomer"/>">Modify Customer</a>
		                                                                            </li>
		                                                                        </ul><!-- 3rd -->
                                                                            </li>
                                                                            <li>
                                                                                <a href="Application" onclick="return false">Application</a>
                                                                                <ul style="padding-top :0px; ">
                                                                                     <li>
		                                                                                <a href="<s:url namespace="/application/web" action="newApplication"/>">New Application</a>
		                                                                            </li>
		                                                                            <li>
		                                                                                <a href="<s:url namespace="/application/web" action="modifyApplication"/>">Modify Application</a>
		                                                                            </li>
		                                                                            <li>
		                                                                                <a href="<s:url namespace="/application/web" action="applicationForm"/>">View Application</a>
		                                                                            </li>
		                                                                            <li>
		                                                                                <a href="<s:url namespace="/application/web" action="confirmApplication"/>">Generate App No</a>
		                                                                            </li>
		                                                                            <li>
		                                                                                <a href="<s:url namespace="/application/web" action="allocateApplication"/>">Allocate Application</a>
		                                                                            </li>
		                                                                        </ul><!-- 3rd -->
                                                                            </li>
                                                                            <li>
                                                                                <a href="PIV" onclick="return false">PIV</a>
                                                                                <ul style="padding-top :0px; ">
                                                                                    <li>
		                                                                                <a href="<s:url namespace="/application/web" action="confirmPiv"/>">Confirm PIV</a>
		                                                                            </li>
		                                                                            <li>
		                                                                                <a href="<s:url namespace="/application/web" action="viewPiv"/>">View PIV</a>
		                                                                            </li>
		                                                                        </ul><!-- 3rd -->
                                                                            </li>
                                                                        </ul><!-- 2nd -->
                                                                    </li>
                                                                    <li>                                                                        
                                                                        <a class="item-primary">Calendar</a>
                                                                        <ul style="width:150px;">
                                                                            <li>
                                                                            	<a  href="<s:url namespace="/calendar/web" action="diaryEntry"/>">New Appointmnet</a>
                                                                            </li>
                                                                            <li>
                                                                            	<a  href="<s:url namespace="/calendar/web" action="schedule"/>">Edit/Change Status</a>
                                                                            </li>
                                                                            
                                                                        </ul>         
                                                                    </li>
                                                                    <li>
                                                                        <a class="item-primary" href="#">Estimation</a>
                                                                        <ul style="width:150px;">
                                                                        	<li>
                                                                                <a href="New Estimate" onclick="return false">Estimate</a>
                                                                                <ul style="padding-top :0px; ">
                                                                                    <li>
                                                                            			<a href="<s:url namespace="/estimate/web" action="estimate"/>">New</a>
                                                                            		</li>
                                                                            		<li>
                                                                            			<a href="<s:url namespace="/estimate/web" action="estimate!modify"/>">Modify</a>
                                                                            		</li>
                                                                            		<li>
                                                                            			<a href="<s:url namespace="/estimate/web" action="estimate!viewOnly"/>">View</a>
                                                                            		</li>
		                                                                            <li>
		                                                                            	<a href="<s:url namespace="/estimate/web" action="print"/>">Print</a>
		                                                                            </li>
		                                                                            <li>
		                                                                            	<a href="<s:url namespace="/estimate/web" action="approveEstimate"/>">Approve Estimate</a>
		                                                                            </li>
		                                                                            <li>
		                                                                            	<a href="<s:url namespace="/estimate/web" action="estimate!undoReject"/>">Undo Rejects</a>
		                                                                            </li>
		                                                                            <li>
		                                                                                  <a href="<s:url namespace="/estimate/web" action="postJob"/>">Post Estimate</a>
		                                                                            </li>
		                                                                            <li> 
		                                                                            	<a href="<s:url namespace="/estimate/web" action="deleteJob"/>">Delete Estimate</a>
		                                                                            </li>
                                                                            	</ul><!-- 3rd -->
                                                                            </li>
                                                                           	<li>
                                                                                <a href="PIV" onclick="return false">PIV</a>
                                                                                <ul style="padding-top :0px; ">
                                                                                    <li>
                                                                                		<a href="<s:url namespace="/piv/web" action="EstimatePIV!generate"/>">Generate PIV</a>
                                                                            		</li>
                                                                            		<li>
                                                                                		<a href="<s:url namespace="/piv/web" action="EstimatePIV!confirm"/>">Confirm PIV</a>
		                                                                            </li>
		                                                                            <li>
		                                                                                <a href="<s:url namespace="/piv/web" action="EstimatePIV!cancel"/>">Cancel PIV</a>
		                                                                            </li>
		                                                                            <li>
		                                                                                <a href="<s:url namespace="/piv/web" action="EstimatePIV"/>">View PIV</a>
		                                                                            </li>
                                                                            	</ul><!-- 3rd -->
                                                                            </li>
                                                                            
                                                                            <li>
                                                                                <a href="<s:url namespace="/estimate/web" action="standardCostDetail"/>">Standard Cost Detail</a>
                                                                            </li>
                                                                        </ul>
                                                                    </li>
                                                                    <li>
                                                                        <a class="item-primary" href="#">Manage Jobs</a>
                                                                        <ul style="width:150px;"><!-- 2nd -->
                                                                            <li>
                                                                                <a href="<s:url namespace="/job/web" action="ReviseJob"/>">Revise Jobs</a>
                                                                            </li>
                                                                            <li>
                                                                            	<a href="<s:url namespace="/job/web" action="approveRevisedJob"/>">Approve Revised Job</a>
                                                                           	</li>
                                                                           	<li>
                                                                            	<a href="<s:url namespace="/job/web" action="ReviseJob!undoReject"/>">Undo Rejected Jobs</a>
                                                                           	</li>
                                                                           	<li>
                                                                                <a href="Job Contractor" onclick="return false">Job Contractor</a>
                                                                                <ul style="padding-top :0px; ">
                                                                                    <li>
                                                                                		<a href="<s:url namespace="/job/web" action="newJobAllocation"/>">Job Allocation</a>
                                                                            		</li>
                                                                            		<li>
                                                                                		<a href="<s:url namespace="/job/web" action="newJobDeAllocation"/>">Job De Allocation</a>
                                                                            		</li>
                                                                           			<li>
                                                                            			<a href="<s:url namespace="/job/web" action="billContractor"/>">Bill Contractor</a>
                                                                            		</li>
                                                                            	</ul><!-- 3rd -->
                                                                            </li>
                                                                           	<li>
                                                                                <a href="Job Closer" onclick="return false">Job Closer</a>
                                                                                <ul style="padding-top :0px; ">
                                                                                    <li>
                                                                            			<a href="<s:url namespace="/job/web" action="JobClose"/>">Soft Close Jobs</a>
                                                                            		</li>                                                                            
                                                                                    <li>
                                                                            			<a href="<s:url namespace="/job/web" action="HardJobClose"/>">Job Closer</a>
                                                                            		</li>
                                                                            		<li>
                                                                            			<a href="<s:url namespace="/job/web" action="jobCloseGroup"/>">Close Jobs Group</a>
                                                                            		</li>
                                                                            		<li>
                                                                            			<a href="<s:url namespace="/job/web" action="forceJobClose"/>">Forced Job Close</a>
                                                                            		</li>
                                                                            	</ul><!-- 3rd -->
                                                                            </li>
                                                                            <li>
                                                                                <a href="<s:url namespace="/job/web" action="OrderCard"/>">SMC Order Card</a>
                                                                            </li>
                                                                            
                                                                        </ul><!-- 2nd -->
                                                                    </li>
                                                                    <li>
                                                                    	<a class="item-primary" href="#" onclick="return false"> Reference Letters</a>
																		<ul style="width: 150px;">
																			<li>
																				<a href="<s:url namespace="/officeDocs/web" action="loadestimateReferencePage"/>">Estimate Reference</a></li>
																			<li>
																				<a href="<s:url namespace="/officeDocs/web" action="loadRequestForBulkSupplyPage"/>">Request for bulk supply</a></li>
																		</ul><!-- 2nd -->
																	</li>
                                                                    
                                                                    <li>
                                                                        <a class="item-primary" href="#">Reports</a>
                                                                        <!--ul style="width:150px;">
                                                                            <li>
                                                                                <h:outputLink onclick="return false" rendered="true">
                                                                                    <h:outputText value="Error and Exception"/>
                                                                                </h:outputLink>
                                                                            </li>
                                                                            <li>
                                                                                <h:outputLink onclick="return false" rendered="true">
                                                                                    <h:outputText value="Progress"/>
                                                                                </h:outputLink>
                                                                            </li>
                                                                            <li>
                                                                                <h:outputLink onclick="return false" rendered="true">
                                                                                    <h:outputText value="Managemnet Information"/>
                                                                                </h:outputLink>
                                                                            </li>
                                                                        </ul-->
                                                                    </li>
                                                                    <li>
                                                                        <a class="item-primary" href="#">About</a>
                                                                    </li>
                                                                    
                                                                </ul>
                                                            </div>
                                                    </s:form></td>
                                                    <td class="tdWelcomeLabel">Welcome</td>
                                                    <td class="tdWelcomeUser"><s:property value="loggedInUserId"></s:property></td>
                                                    <td><a href="<s:url action="changeCC"/>">Change CC</a> </td>
                                                    <td class="tdLogOut"><a href="<s:url action="logout"/>">Logout</a> </td>
                                                </tr>
                                                <tr>
                                                    <td></td>
                                                    <td></td>
                                                    <td></td>
                                                    <td></td>
                                                </tr>
                                            </tbody>
                                        </table>


                                    </td>

                                </tr>
                                		<tr>
						<td >
						<table width="100%" border="0" align="center"
							bgcolor="#D4E9F8" cellpadding="0" cellspacing="0"
							style="border-collapse: collapse;">
							<tbody>
								<tr>
									<td>
									<s:if test="rejectedList != NULL">
									<table width="100%" border="0">
										<tr>
											<td class="tblMenuHeading">Rejected Estimates</td>
										</tr>
										<tr>
											<td>
											<table class="tldFromContent" border="1">

												<tr class="tblMenuColor" height="25px">
													<td><strong>Estimate Number</strong></td>
													<td><strong>Cost Center</strong></td>
													<td><strong>Project</strong></td>
													<td><strong>Estimate Date</strong></td>
													<td><strong>Confirm Date</strong></td>
													<td><strong>Confirm By</strong></td>
													<td><strong>Standard Cost</strong></td>
													<td><strong>Discription</strong></td>
													<td><strong>Redirect</strong></td>



												</tr>
												<%int i=0; %>
												<s:iterator value="rejectedList">
													<tr bgcolor="#CCCCCC">
														<td id="tdestNo_<%= i %>"><s:property value="id.estimateNo" /></td>
														<td id="rejcostCenNo_<%= i %>" align="right"><s:property value="id.DeptId" /></td>
														<td align="right"><s:property value="projectNo" /></td>
														<td align="right"><s:text name="format.date" ><s:param value="etimateDt"/></s:text> </td>																												
														<td align="right"><s:text name="format.date" ><s:param value="confDt"/></s:text> </td>
														<td><s:property value="confBy" /></td>
														<td align="right"><s:text name="format.currency" ><s:param value="stdCost"/></s:text></td>
														<td><s:property value="descr.substring(0,40)" /></td>
														<td><input value="Direct" width="20px" height="5px" id="<%= i %>" type="button" onclick="return getValues(this)" ></td >   

													</tr>
													<%i++; %>
												</s:iterator>
											</table>
											</td>
										</tr>
										<tr>
											<td>&nbsp;</td>
										</tr>
									</table>
									</s:if>
									</td>
								</tr>
								
								<tr>
									<td>
									<s:if test="rejectedRevisedJobsList != NULL">
									<table width="100%" border="0">
										<tr>
											<td class="tblMenuHeading">Rejected Jobs</td>
										</tr>
										<tr>
											<td>
											<table class="tldFromContent" border="1">

												<tr class="tblMenuColor" height="25px">
													<td><strong>Project</strong></td>
													<td><strong>Cost Center</strong></td>
													<td><strong>Estimate Number</strong></td>													
													<td><strong>Estimate Date</strong></td>
													<td><strong>Confirm Date</strong></td>
													<td><strong>Confirm By</strong></td>
													<td><strong>Standard Cost</strong></td>
													<td><strong>Discription</strong></td>
													<td><strong>Direct</strong></td>



												</tr>
												<%int i=0; %>
												<s:iterator value="rejectedRevisedJobsList">
													<tr bgcolor="#CCCCCC">
														<td align="right" id="tdPrjtNo_<%= i %>"><s:property value="projectNo" /></td>
														<td id="rejRevCostCenNo_<%= i %>" align="right"><s:property value="id.DeptId" /></td>
														<td><s:property value="id.estimateNo" /></td>
														<td align="right"><s:text name="format.date"><s:param value="etimateDt"/></s:text> </td>	
														<td align="right"><s:text name="format.date"><s:param value="confDt"/></s:text></td>	
														<td><s:property value="confBy" /></td>
														<td align="right"><s:text name="format.currency"><s:param value="stdCost"/></s:text></td>
														<td><s:property value="descr.substring(0,40)" /></td>
														<td><input value="Direct" width="20px" height="5px" id="<%= i %>" type="button" onclick="return getValuesForRejJobs(this)" ></td >   
													</tr>
													<%i++; %>
												</s:iterator>
											</table>
											</td>
										</tr>
										<tr>
											<td>&nbsp;</td>
										</tr>
										
									</table>
									</s:if>
									</td>
								</tr>
								<tr>
									<td><s:if test="approveList != NULL">
										<table width="100%" border="0">
											<tr>
												<td class="tblMenuHeading">Estimate Approval</td>
											</tr>
											<tr>
												<td>
												<table class="tldFromContent" border="1">

													<tr class="tblMenuColor" height="25px">
														<td><strong>Estimate Number</strong></td>
														<td><strong>Cost Center</strong></td>
														<td><strong>Estimate Date</strong></td>
														<td><strong>Confirm Date</strong></td>
														<td><strong>Confirm By</strong></td>
														<td><strong>Standard Cost</strong></td>
														<td><strong>Discription</strong></td>
														<td><strong>Direct</strong></td>



													</tr>
													<%int i=0; %>
													<s:iterator value="approveList">
														<tr bgcolor="#CCCCCC">
															<td id="tdApprestNo_<%= i %>"><s:property value="id.estimateNo" /></td>
															<td id="estAprCostCenNo_<%= i %>" align="right"><s:property value="id.DeptId" /></td>
															<td align="right"><s:text name="format.date"><s:param value="etimateDt" /></s:text></td>
															<td align="right"><s:text name="format.date"><s:param value="confDt" /></s:text></td>
															<td><s:property value="confBy" /></td>
															<td align="right"><s:text name="format.currency"><s:param value="stdCost" /></s:text></td>
															<td><s:property value="descr.substring(0,40)" /></td>
															<td><input value="Direct" width="20px" height="5px" id="<%= i %>" type="button" onclick="return getValuesForEstApprove(this)" ></td >   
														</tr>
														<%i++; %>
													</s:iterator>
												</table>
												</td>
											</tr>
											<tr>
												<td>&nbsp;</td>
											</tr>
											
										</table>
									</s:if></td>
								</tr>
								
								
								
								<tr>
									<td><s:if test="approveRevisedJobsList != NULL">
										<table width="100%" border="0">
											<tr>
												<td class="tblMenuHeading">Job Approval</td>
											</tr>
											<tr>
												<td>
												<table class="tldFromContent" border="1">

													<tr class="tblMenuColor" height="25px">
														<td><strong>Project</strong></td>
														<td><strong>Cost Center</strong></td>
														<td><strong>Estimate Number</strong></td>														
														<td><strong>Estimate Date</strong></td>
														<td><strong>Confirm Date</strong></td>
														<td><strong>Confirm By</strong></td>
														<td><strong>Standard Cost</strong></td>
														<td><strong>Discription</strong></td>
														<td><strong>Direct</strong></td>
													</tr>
													<%int i=0; %> 
													<s:iterator value="approveRevisedJobsList">
														<tr bgcolor="#CCCCCC">
															<td align="right" id="tdAprRevisedPrjNo_<%= i %>" ><s:property value="projectNo" /></td>
															<td id="estRevAprcostCenNo_<%= i %>" align="right"><s:property value="id.DeptId" /></td>
															<td><s:property value="id.estimateNo" /></td>
															<td align="right"><s:text name="format.date"><s:param value="etimateDt" /></s:text></td>
															<td align="right"><s:text name="format.date"><s:param value="confDt" /></s:text></td>
															<td><s:property value="confBy" /></td>
															<td align="right"><s:text name="format.currency"><s:param value="stdCost" /></s:text></td>
															<td><s:property value="descr.substring(0,40)" /></td>
															<td><input value="Direct" width="20px" height="5px" id="<%= i %>" type="button" onclick="return getValuesForAprRevised(this)" ></td >   
														</tr>
														<%i++; %>

													</s:iterator>
												</table>
												</td>
											</tr>
											<tr>
												<td>&nbsp;</td>
											</tr>
										</table>
									</s:if></td>
								</tr>
								<tr>
									<td><s:if test="ativeAppoinmentsList != NULL">
										<table width="100%" border="0">
											<tr>
												<td class="tblMenuHeading">Active Appointments</td>
											</tr>
											<tr>
												<td>
												<table class="tldFromContent" border="1">

													<tr class="tblMenuColor" height="25px">
														<td><strong>Reference Number</strong></td>
														<td><strong>Allocated By </strong></td>
														<td><strong>Alocated To</strong></td>
														<td><strong>Appointment Date</strong></td>
														<td><strong>Time Session</strong></td>
														<td><strong>Description</strong></td>												 


													</tr>
													<s:iterator value="ativeAppoinmentsList">
														<tr bgcolor="#CCCCCC">
															<td><s:property value="referenceNo" /></td>
															<td><s:property value="allocatedBy" /></td>

															<td><s:property value="allocatedTo" /></td>

															<td ><s:text name="format.date">
																<s:param value="appointmentDate" />
															</s:text></td>
															<td><s:property value="timeSession" /></td>
															 
															<td><s:property value="description" /></td>
															 
														</tr>
													</s:iterator>
												</table>
												</td>
											</tr>
											<tr>
												<td>&nbsp;</td>
											</tr>
										
										</table>
									</s:if></td>
								</tr>
								
								<tr>
									<td><s:if test="unAttendedAppoinments != NULL">
										<table width="100%" border="0">
											<tr>
												<td class="tblMenuColor" height="25px">Un Attended Appointments</td>
											</tr>
											<tr>
												<td>
												<table class="tldFromContent" border="1">

													<tr class="tblMenuColor" height="25px">
														<td><strong>Reference Number</strong></td>
														<td><strong>Allocated By </strong></td>
														<td><strong>Alocated To</strong></td>
														<td><strong>Appointment Date</strong></td>
														<td><strong>Time Session</strong></td>
														<td><strong>Description</strong></td>												 
														<td><strong>Direct</strong></td>
											 

													
													</tr>
													<%int i=0; %> 
													<s:iterator value="unAttendedAppoinments">
														<tr bgcolor="#CCCCCC">
															<td id="unAttApptRefNo_<%= i %>"><s:property value="referenceNo" /></td>
															<td><s:property value="allocatedBy" /></td>

															<td id="unAttApptAllTo_<%= i %>"><s:property value="allocatedTo" /></td>

															<td id="unAttApptAptDate_<%= i %>"><s:text name="format.date">
															 <s:param value="appointmentDate" />
															</s:text></td>
															<td><s:property value="timeSession" /></td>
															 
															<td><s:property value="description" /></td>
															<td><input value="Direct" width="20px" height="5px" id="<%= i %>" type="button" onclick="return getValuesForunAttendedAppoinments(this)" ></td >   
															 
														</tr>
														<%i++; %>

													</s:iterator>
												</table>
												</td>
											</tr>
											<tr>
												<td>&nbsp;</td>
											</tr>
										 
										</table>
									</s:if></td>
								</tr>
								
								<tr>
									<td><s:if test="failedAppoinments != NULL">
										<table width="100%" border="0">
											<tr>
												<td class="tblMenuHeading">Failed Appointments</td>
											</tr>
											<tr>
												<td>
												<table class="tldFromContent" border="1">

													<tr class="tblMenuColor" height="25px">
														<td><strong>Reference Number</strong></td>
														<td><strong>Allocated By </strong></td>
														<td><strong>Alocated To</strong></td>
														<td><strong>Appointment Date</strong></td>
														<td><strong>Time Session</strong></td>
														<td><strong>Description</strong></td>												 


													</tr>
													<s:iterator value="failedAppoinments">
														<tr bgcolor="#CCCCCC">
															<td><s:property value="referenceNo" /></td>
															<td><s:property value="allocatedBy" /></td>

															<td><s:property value="allocatedTo" /></td>

															<td ><s:text name="format.date">
																<s:param value="appointmentDate" />
															</s:text></td>
															<td><s:property value="timeSession" /></td>
															 
															<td><s:property value="description" /></td>
															 
														</tr>
													</s:iterator>
												</table>
												</td>
											</tr>
											<tr>
												<td>&nbsp;</td>
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
