<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>New Add</title>
<link rel="stylesheet" type="text/css" href="../../style/style.css" />

<link rel="stylesheet" type="text/css" href="../../style/datePicker.css" />
<script src="../../script/jquery/jquery.datePicker-min.js"
	type="text/javascript"></script>

<script type="text/javascript" src="../../script/javascript.js"></script>
<link rel="stylesheet" type="text/css" href="../../style/datePicker.css" />

<script type="text/javascript" src="../../script/estimate.js"> </script>
<script type="text/javascript" src="../../script/javascript.js"> </script>
<script type="text/javascript" src="../../script/jquery/jquery.js"> </script>
<script type="text/javascript" src="../../script/jquery/jquery.min.js"> </script>

<link rel="stylesheet" type="text/css" href="../../script/ext-3.3.1/css/setup.css" media="all" />
<link rel="stylesheet" type="text/css" href="../../script/ext-3.3.1/resources/css/ext-all.css" />
<link rel="stylesheet" type="text/css" href="../../script/ext-3.3.1/resources/css/xtheme-gray.css" />
 <script type="text/javascript" src="../../script/ext-3.3.1/adapter/ext/ext-base.js"></script>
        <script type="text/javascript" src="../../script/ext-3.3.1/ext-all-debug.js"></script>
<link rel="stylesheet" type="text/css" href="../../style/datePicker.css" />		
<script src="../../script/jquery/jquery.datePicker-min.js" type="text/javascript"></script>

   

<script type="text/javascript">

	Ext.onReady(function() {
		 $('#targetDate').datePicker({clickInput:true});
		 $('#jobnoReceDate').datePicker({clickInput:true});
		 $('#projectLetterReceivedOnDate').datePicker({clickInput:true});
		 $('#proLtrToEsOnDate').datePicker({clickInput:true});
		 $('#estReceivedOnDate').datePicker({clickInput:true});
		 $('#estimateSntToDGMOnDate').datePicker({clickInput:true});
		 $('#estimatedDate').datePicker({clickInput:true});
		 $('#esAllocatedDate').datePicker({clickInput:true});
		 $('#jobAssDate').datePicker({clickInput:true});
		 $('#jobAlloDate').datePicker({clickInput:true});
		 
		 
	});


	
	function loadContractorName(userId){
		//alert(userId.value);
		 $.getJSON("sps/getContractorname.ajax?userId="+userId.value, {ajax_command:"getContractorname",ajax_ieCacheFix:new Date().getTime()}, function(json) {

			 $('#conName').val(json.contractorName);
				
	 	// $('#applicationRefs1').val(json.categoryCode);

	 
		 });
	}

	function answers()
	{
		var selectedanswer=document.getElementById("listConref").value;
		document.getElementById("searchtxtField").value = selectedanswer;
	}

	function answersJob()
	{
		var selectedanswer=document.getElementById("listJoblist").value;
		document.getElementById("searchtxtField").value = selectedanswer;
	}
</script>
<sx:head />

</head>

<body>
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
														value="costCenter"></s:property></td>
												<td class="tdLogOut"><a href="<s:url action="logout"/>">Logout</a></td>
											</tr>
										</tbody>
									</table>
								</td>
							</tr>
						</tbody>
					</table>
			<tr>
				<td id="tdContent" width="100%">
					<table class="tblContent" border="0" cellpadding="0"
						cellspacing="0">
						<thead>
							<tr>
								<th></th>
							</tr>
						</thead>


						<!-- add table in here  -->




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
											<td><s:form name="fjhdjfh" validate="true"
													theme="simple">
													<table width="100%" class="tldFromContent" border="0">
														<thead>

														</thead>
														<tbody>
															<tr>
																<td>
																
																	<table width="100%" border="0">
																		<thead>
																			<tr>
																				<th width="3%"></th>
																				<th width="18%"></th>
																				<th width="30%"></th>
																				<th width="18%"></th>
																				<th width="25%"></th>
																				<th width="*%"></th>
																				<!--  <th width="40%"></th>
																<th width="10%"></th>
																<th width="*"></th>-->

																			</tr>
																		</thead>
																		<tbody>
																			
																			<tr>
																		
																				<td id="tdContent" width="100%">
																			
																					<table class="tblContent" border="0" align="center"
																						cellpadding="0" cellspacing="0">
																						<thead>
																							<tr>
																								<th></th>
																							</tr>
																						</thead>
																					
			 <!--   <tr>
             	
             	 <td>Find By    : <s:radio name="idSearchType" id="idJobRadioType"  list="#{'comRef':'Commercial Reference','constructRef':'Construction Reference','projJobNo':'Project/Job Number'}" /></td>
              </tr>
              -->
       	      <tr>
             
              	
				<td></td>
			
			  </tr>
              <tr>
           <td></td>
              <td>Construction Reference :<s:textfield name="txtSearchValue" id="searchtxtField" maxlength="35"></s:textfield><s:submit theme="simple" align="right" value="Search" id="idSearchJob" method="searchJob"></s:submit></td>
              <td>Construction Reference :</td>
              <td>:</td>
              
              <td><s:select name="ConRef" 
														list="listConref" 
												id="listConref" 		 																		
									headerKey="NOT ASSIGN"												
									headerValue="-- Please Select --" onchange="answers()"></s:select>
                                </td>
              <td>Job Reference :</td>
              <td>:</td>
              
              <td><s:select name="JobRef" 
														list="listJoblist" 
												id="listJoblist" 		 																		
									headerKey="NOT ASSIGN"												
									headerValue="-- Please Select --" onchange="answersJob()"></s:select>
                                </td>
              </tr>
             
			
			</table>
			<table>
			<tbody>
			<tr>
			<td>
			<div class="double-scroll">
				<table class="tldFromContent" border="1" width="800px">

																	<tr class="tblMenuColorMsg" height="25px">
																											<td><strong></strong></td>
																											<td><strong>Reference Number</strong></td>
																											<td><strong>File Number</strong></td>
																											<td><strong>Project Number</strong></td>
																											<td><strong>Fund Id</strong></td>
																											<td><strong>Area Code</strong></td>
																											<td><strong>Estimated By</strong></td>
																											<td><strong>Con By</strong></td>
																											<td><strong>Sup By</strong></td>
																											<td><strong>Description</strong></td>
																											<td><strong>Dev Sectrait</strong></td>
																											<td><strong>Detail Cost</strong></td>
																											<td><strong>Variance</strong></td>
																											<td><strong>Standard Cost</strong></td>
																				
													</tr>
																	<%
																		int j = 0;
																	%>
																	<s:iterator value="pcinitiladataList">
																		<tr >

																										
																											<s:hidden name="hiddentableRadioButtonValue" id="hiddentableRadioButtonValue" /> 
<td width="2%"><input type="radio" onClick="document.getElementById('hiddentableRadioButtonValue').value = '<s:property value="id.fileNo" />';" name='applicationId' value='<s:property value="id.fileNo" />' />	
																												
																		  <td align="right"><s:property
																														value="refNo" /></td>
																												<td align="right"><s:property
																														value="fileNo" /></td>
																												<td align="right"><s:property
																														value="projectNo" /></td>
																												<td align="right"><s:property
																														value="fundId" /></td>
																												
																												<td align="right"><s:property
																														value="areaCode" /></td>
																												<td align="right"><s:property
																														value="estBy" /></td>
																												<td align="right"><s:property
																														value="conBy" /></td>
																												<td align="right"><s:property
																														value="supBy" /></td>
																												
																												
																												
																												
																												<td align="right"><s:property
																														value="decrp" /></td>
																												<td align="right"><s:property
																														value="devSec" /></td>
																										
																												
																												<td align="right"><s:property
																														value="detailCost" /></td>
																												<td align="right"><s:property
																														value="varianceNew" /></td>
																												<td align="right"><s:property
																														value="stdCost" /></td>
																												
																												
																												
																												
																												

																	    </tr>
																		<%j++;%>
																	</s:iterator>
																
												  </table> 
												</div>
															
			</td>
			</tr>
				<tr>
																	<td>
																	<s:submit
																										theme="simple" align="right" value="Load"
																										method="loadValuesForPage" id="btnLoad"
																										onclick="return validate_form()"></s:submit>
																	</td>
					
                    						</tr>
			</tbody>		<tr></tr>
			</table>
			<table width="100%" border="0" class="tldFromContent">
              
              <tbody>
              <tr>																						<td></td>
              <td></td>
																										<td class="tdHeadings"></td>
																										<s:if test="%{messageType=='ERROR'}">
																										<td class="tdMsgStyle"><s:property value="errorMessage"></s:property></td>
																										</s:if>
																										<s:elseif test="%{messageType=='INFO'}">
																										<td class="tdMsgStyleInfo"><s:property value="errorMessage"></s:property></td>
																										</s:elseif>
																										<s:elseif test="%{messageType=='DONE'}">
																										<td class="tdMsgStyleDone"><s:property value="errorMessage"></s:property></td>
																										</s:elseif>
																										
																										
																
																							</tr>
                <tr>
                  <td colspan="2"><div id="myDivx">
                      <table width="100%" border="0">
                        <tr>
                          <td width="5"></td>
                          <td><table width="100%" border="0">
                              <tr height="20">
                                <td class="tdHeadings"  width="20%" >Initail Data</td>
                                <td width="2">&nbsp;</td>
                                <td width="30%"></td>
                                <td width="20%">&nbsp;</td>
                                <td width="2"></td>
                                <td width="30%"></td>
                              </tr>
                              <tr height="20">
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td></td>
                                <td>&nbsp;</td>
                                <td></td>
                                <td>
                                </td>
                              </tr>
                              <tr height="20">
                                <td>Construction Ref No</td>
                                <td>:</td>
                                <td><s:textfield name="fileNo"
													id="fileNo" size="20" maxlength="20" readonly="false"
															cssClass="inputArea"></s:textfield></td>
                                <td>Commercial Ref No</td>
                                <td>:</td>
                                <td><s:textfield name="refNo"
									id="refNo" size="20" maxlength="20" readonly="false"
									cssClass="inputArea"></s:textfield>
                                </td>
                              </tr>
                              <tr height="20">
                                <td>Project No</td>
                                <td>:</td>
                                <td><s:textfield
									name="projectNo" id="projectNo" size="20" maxlength="20"></s:textfield></td>
                                <td>Description</td>
                                <td>:</td>
                                <td><s:textarea name="decrp"
								id="decrp" cols="20" rows="2"> </s:textarea>
                                </td>
                              </tr>
                              <tr height="20">
                                <td>District</td>
                                <td>:</td>
                                <td><s:textfield
									name="district" id="district" size="20" maxlength="20"></s:textfield></td>
                                <td>Dev Sectrait</td>
                                <td>:</td>
                                <td><s:textfield
								name="devSec" id="devSec"  size="20" maxlength="20"></s:textfield>
                                </td>
                              </tr>
                              <tr height="20">
                                <td>Electorate</td>
                                <td>:</td>
                                <td><s:textfield name="electorate"
										id="electorate"size="20" maxlength="20"></s:textfield></td>
                                <td>CSC</td>
                                <td>:</td>
                                <td><s:textfield
							name="csc" id="csc" size="20" maxlength="20"></s:textfield>
                                </td>
                              </tr>
                              <tr height="20">
                                <td>Fund Id</td>
                                <td>:</td>
                                <td><s:textfield name="fundId"
								id="fundId" size="20" maxlength="20"></s:textfield></td>
                                <td>Area Code</td>
                                <td>:</td>
                                <td><s:textfield
								name="areaCode" id="areaCode" size="20" maxlength="20"></s:textfield>
                                </td>
                              </tr>
                              <tr height="20">
                                <td>Target Date</td>
                                <td>:</td>
                                <td><s:textfield  name="targetDate" id="targetDate" ></s:textfield></td>
                                <td>ES Allocated Date</td>
                                <td>:</td>
                                <td><s:textfield  name="esAllocatedDate" id="esAllocatedDate" ></s:textfield>
                                </td>
                              </tr>
                              <tr height="20">
                                <td>Estimated Date</td>
                                <td>:</td>
                                <td><s:textfield  name="estimatedDate" id="estimatedDate"></s:textfield></td>
                                <td>Estimate Recieved On</td>
                                <td>:</td>
                                <td><s:textfield  name="estReceivedOnDate" id="estReceivedOnDate" ></s:textfield>
                                </td>
                              </tr>
                              <tr height="20">
                                <td>Estimate Sent to DGM On</td>
                                <td>:</td>
                                <td><s:textfield name="estimateSntToDGMOnDate" id="estimateSntToDGMOnDate" ></s:textfield></td>
                                <td>Project Letter To ES On</td>
                                <td>:</td>
                                <td><s:textfield  name="proLtrToEsOnDate" id="proLtrToEsOnDate" ></s:textfield>
                                </td>
                              </tr><tr height="20">
                                <td>Job Assigened Date</td>
                                <td>:</td>
                                <td><s:textfield  name="jobAssDate" id="jobAssDate" ></s:textfield></td>
                                <td>Job No Recieved On</td>
                                <td>:</td>
                                <td><s:textfield  name="jobnoReceDate" id="jobnoReceDate"></s:textfield>
                                </td>
                              </tr>
                              <tr height="20">
                                <td>Project Letter Recieved On</td>
                                <td>:</td>
                                <td><s:textfield  name="projectLetterReceivedOnDate" id="projectLetterReceivedOnDate"></s:textfield>
                                </td>

                                <td>Detail Cost</td>
                                <td>:</td>
                                <td><s:textfield
										name="detailCost" id="detailCost"
										maxlength="8"></s:textfield>
                                </td>
                              </tr>
                              <tr height="20">
                                <td>Standard Cost</td>
                                <td>:</td>
                                <td><s:textfield name="stdCost"
										id="costId" maxlength="7"></s:textfield></td>
                                <td>Estimation By</td>
                                <td>:</td>
                                <td><s:select name="esttBy" 
														list="listuserName" 
												id="listuserName" 		 																		
									headerKey="NOT ASSIGN"												
									headerValue="-- Please Select --"></s:select>
                                </td>
                              </tr>
                              <tr height="20">
                                <td>Variation</td>
                                <td>:</td>
                                <td><s:textfield
											name="varianceNew" id="varinceNew"
											maxlength="8"></s:textfield></td>
                                <td>Contractor Name</td>
                                <td>:</td>
                                <td><s:textfield name="conName"
									id="conName" maxlength="6"></s:textfield>
                                </td>
                              </tr>
                              <tr height="20">
                                <td>Construction By</td>
                                <td>:</td>
                                <td><s:select name="conBy" 
													list="listContractorsList" 
												id="listContractorsList" 		 																		
												headerKey="NOT ASSIGN"												
												headerValue="-- Please Select --" onchange="loadContractorName()">																			
    														</s:select></td>
                                <td>Supervision By</td>
                                <td>:</td>
                                <td><s:select name="supBy" 
												list="listuserName" 
									id="listuserName" 		 																		
									headerKey="NOT ASSIGN"												
										headerValue="-- Please Select --">																			
							    </s:select>
                                </td>
                              </tr>
                              <tr height="20">
                                <td>Project Funding Authority</td>
                                <td>:</td>
                                <td><s:textfield
											name="profundaut" id="profundaut"	maxlength="20"></s:textfield></td>
                                <td>Job Allocation Date</td>
                                <td>:</td>
                                <td><s:textfield  name="jobAlloDate" id="jobAlloDate"></s:textfield>
                                </td>
                                
                                </tr>
                              <tr height="20">
                                <td>Feerder Name</td>
                                <td>:</td>
                                <td><s:textfield
											name="feedername" id="feedername"	maxlength="20"></s:textfield></td>
                                
                                <td>Code Number</td>
                                <td>:</td>
                                <td><s:textfield
											name="codeNumber" id="codeNumber"	maxlength="20"></s:textfield></td>
                              
                                </tr>
                              
                              
                              <tr height="20">
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td></td>
                                <td>&nbsp;</td>
                                <td></td>
                                <td>
                                </td>
                              </tr>
                              <tr height="20">
                                
																								<td class="tdHeadings" colspan="4">Line Detail</td>
																							</tr>


																							<tr>
																								<td>HT33</td><td>:</td>
																								<td><s:textfield id="ht33" name="ht33" ></s:textfield></td>


																								<td>LT3Phase</td><td>:</td>
																								<td><s:textfield id="lt3Phase"
																										name="lt3Phase"></s:textfield></td>


																								<td>ABC5W</td><td>:</td>
																								<td><s:textfield id="abc5w" name="abc5w"></s:textfield></td>
																								<td>Aug Sub</td><td>:</td>
																									<td><s:checkbox name="chkdeNotice" value=""
																									id="chkdeNotice"
																									onclick="setIssueClearDates(this)"></s:checkbox></td>
																																														


                              																		</tr>
                              																							<tr>

																								<td>LT11</td> <td>:</td>
																								<td><s:textfield id="lt11" name="lt11"></s:textfield></td>



																								<td>LTSPhase</td><td>:</td>
																								<td><s:textfield id="ltsPhase"
																										name="ltsPhase"></s:textfield></td>


																								<td>ABC4W</td><td>:</td>
																								<td><s:textfield id="abc4w" name="abc4w"></s:textfield></td>
																								

																							</tr>
                                                                                            <tr>

																								<td>SUB33</td><td>:</td>
																								<td><s:textfield id="sub33" name="sub33" ></s:textfield></td>


																								<td>LT1->3</td><td>:</td>
																								<td><s:textfield id="lt13" name="lt13"></s:textfield></td>


																								<td>ABCSECCC</td><td>:</td>
																																										
																							<td><s:textfield id="abcsecct"
																										name="abcsecct"></s:textfield></td>
																								
																								

																							</tr>

																							<tr>
																								<td>SUB11</td><td>:</td>
																								<td><s:textfield id="sub11" name="sub11"></s:textfield></td>
																								<td>LT2->3</td><td>:</td>
																								<td><s:textfield id="lt23" name="lt23"></s:textfield></td>
																								<td>No of HT Poles To be Shifted</td><td>:</td>
																								<td><s:textfield id="noPoleTobeShifted" name="noPoleTobeShifted"></s:textfield></td>
																								
																								
																							</tr>

																							<tr>
																								<td>SUBCAP</td><td>:</td>
																								<td><s:textfield id="subCapacity"
																										name="subCapacity"></s:textfield></td>
																								<td>Combine Run</td><td>:</td>
																								<td><s:textfield id="combineRun" name="combineRun"></s:textfield></td>
																								<td>No of LT Poles To be Shifted</td><td>:</td>
																								<td><s:textfield id="noPoleShifted" name="noPoleShifted"></s:textfield></td>
																								
																								<td></td>
																							</tr>
																							<tr></tr>
																							<tr></tr>
																							<tr>
																								<td>Gang Days Required</td><td>:</td>
																								<td><s:textfield  id="ganDaysRequired"
																										name="ganDaysRequired" ></s:textfield></td>
																								<td>Pre Capacity</td><td>:</td>
																								<td><s:textfield  id="perCapacity"
																										name="perCapacity" ></s:textfield></td>
																								<td>No of HT Poles Shifted</td><td>:</td>
																								<td><s:textfield id="no_ht_pole" name="no_ht_pole"></s:textfield></td>
																								
																								
																								
																							</tr>
																							<tr><td></td>
																								<td></td>
																								<td></td>
																								<td>New Capacity</td><td>:</td>
																								<td><s:textfield id="NewCapacity"
																										name="newCapacity" ></s:textfield></td>
																										<td>No of LT Poles Shifted</td><td>:</td>
																								<td><s:textfield id="no_lt_pole" name="no_lt_pole"></s:textfield></td>
																								
																							</tr>
																							 <tr>
																								<td id="tdBotton" colspan="3"><s:if test="%{state1=='save'}"><s:submit
																										theme="simple" align="right" value="Save"
																										method="save" id="btnSave"
																										onclick="return validate_form()"></s:submit></s:if>
																										 <s:else> <s:submit theme="simple" align="right" value="Save" disabled="true" />
																													</s:else>
																										<s:if test="%{state=='found'}"><s:submit
																										theme="simple" align="right" value="Update"
																										method="update" id="btnupdate"
																										onclick="return validate_form()"></s:submit></s:if>
																										 <s:else> <s:submit theme="simple" align="right" value="Update" disabled="true" />
																													</s:else>
																										 <s:reset
																										theme="simple" align="right" value="Clear" />
																										<s:submit
																										theme="simple" align="right" value="Exit"
																										method="close" id="btnClose"></s:submit>
																							</tr>
																							<s:hidden id="state" name="state"></s:hidden>
																							<s:hidden id="state1" name="state1"></s:hidden>
																							<!--  <tr>
																										<td class="tdHeadings"></td>
																										<s:if test="%{messageType=='ERROR'}">
																										<td class="tdMsgStyle"><s:property value="errorMessage"></s:property></td>
																										</s:if>
																										<s:elseif test="%{messageType=='INFO'}">
																										<td class="tdMsgStyleInfo"><s:property value="errorMessage"></s:property></td>
																										</s:elseif>
																										<s:elseif test="%{messageType=='DONE'}">
																										<td class="tdMsgStyleDone"><s:property value="errorMessage"></s:property></td>
																										</s:elseif>
																										<td></td>
																										<td></td>
																
																							</tr>-->



                          </table></td>
                        </tr>
                      </table>
                  </div></td>
                </tr>
               
              </tbody>
            </table>
																			  </table>
														
																</td>
															</tr>
															<tr>
																<td id="tdFooter">
																	<table class="tblFooter" border="1" cellpadding="0"
																		cellspacing="0">

																		<tbody>
																			<tr>
																				<td id="tdFooter">
																					<table class="tblFooter" border="1" cellpadding="0"
																						cellspacing="0">

																						<tbody>
																							<tr>
																								<td class="footerNotice" align="center">@
																									Ceylon Electricity Board</td>
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
					</table>
				</td>

			</tr>
		</tbody>
	</table>
	
</body>
</html>