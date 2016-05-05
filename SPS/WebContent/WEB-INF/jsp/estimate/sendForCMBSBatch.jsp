<%--
    Document   : Application Form
    Created on : May 16, 2010, 11:21:45 AM
    Author     : Dileepa Waduge
--%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@ page language="java"%>
<html> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Estimate Viewer</title>
<link rel="stylesheet" type="text/css" href="../../style/style.css" />
<script type="text/javascript" src="../../script/generateJobNo.js"> </script>
<script type="text/javascript" src="../../script/javascript.js"> </script>
<script type="text/javascript" src="../../script/jquery/jquery.js"> </script>
<script type="text/javascript" src="../../script/jquery/jquery.min.js"> </script>

<link rel="stylesheet" type="text/css" href="../../script/ext-3.3.1/css/setup.css" media="all" />
<link rel="stylesheet" type="text/css" href="../../script/ext-3.3.1/resources/css/ext-all.css" />
<link rel="stylesheet" type="text/css" href="../../script/ext-3.3.1/resources/css/xtheme-gray.css" />
 <script type="text/javascript" src="../../script/ext-3.3.1/adapter/ext/ext-base.js"></script>
        <script type="text/javascript" src="../../script/ext-3.3.1/ext-all-debug.js"></script>
<script type="text/javascript">

function validatePostDeptID(){
	var postdeptid = document.getElementById('postId').value;
	alert("Do you really want to send this estimate to " + postdeptid);
}


function generateNextFileRefNo(){

	var applicationId=document.getElementById('hiddenEstimateNo').value;
	
	
	
	if(applicationId==-1){
		alert("Please select Reference Number");
		return;
	}
	
	$.getJSON("smc/generateNextFileRefNo.ajax?applicationId="+applicationId, {
		ajax_command: "generateNextFileRefNo",ajax_ieCacheFix:new Date().getTime() },
		function(json){ 
			
			 $('#fileRef').val(json.fileRef);
	});
	//loadDefaultDropDownsValues();
}



</script>

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
					<!-- tr>
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
						<table class="tblContent" border="1" cellpadding="0"
							cellspacing="0">
							<thead>
								<tr>
									<th></th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td><s:form name="estimateViewer" action="estimateBS!sendBatchVise" method="POST" 
										validate="true" theme="simple">
										<table width="100%" class="tldFromContent" border="0">
											<thead>
												<tr>
													<th></th>

												</tr>
											</thead>
											<tbody>
												<tr>
													<td>
													<table width="100%" border="0">
														<thead>
															<tr>
																<th width="40%"></th>
																<th width="10%"></th>
																<th width="*"></th>

															</tr>
														</thead>
														<tbody>
															<tr>
																<td class="tdHeadings">Estimate Details</td>
																<td></td>
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
												<tr>
													<td class="tdHeadings" colspan="4"></td>
												</tr>
												<tr>
													<td>
													<table width="80%" class="tldFromContent" border="0" >
														<thead>
															<tr>
																	<th width="2%" align="left"></th>
																<th width="20%" align="left">Application No</th>
																<th width="20%" align="left">Total Cost</th>
																<th width="20%" align="left">Dept ID</th>
																<th width="20%" align="left">Entry By</th>
																<th width="18%" align="left">Entry Date</th>
																
															</tr>
														</thead>
														<tbody>
													
													<s:iterator status="estimate" value="EstimateList">
														<tr bgcolor="#9DCBE6">
														<s:hidden name="hiddenEstimateNo" id="hiddenEstimateNo" />
														<s:hidden name="hiddendeptId" id="hiddendeptId" />  
														<!-- <td width="2%"><input type="radio" onClick="document.getElementById('hiddenEstimateNo').value = '<s:property value="id.applicationNo" />';document.getElementById('hiddendeptId').value = '<s:property value="deptId" />';" name='applicationId' value='<s:property value="id.applicationNo" />' /><br></td>												<td width="20%"> <s:property  value="id.applicationNo" /></td>-->	
		                                                <td width="2%"><s:checkbox	name="DetailsList" fieldValue="%{id.applicationNo}" /></td>
		                                                <td width="20%"> <s:property  value="id.applicationNo" /></td>	
														<td width="20%"> <s:property  value="totalCost" /></td>														 
														<td width="20%">  <s:property  value="id.deptId" /></td>
														<td width="20%"> <s:property  value="entryBy" /></td>
														<td width="18%"> <s:property  value="entryDate" /></td>
														
														</tr>
													</s:iterator>
													
														
													</tbody>
													</table>
												</td>
												</tr>
												
												<tr>                             
													<td id="tdBotton" colspan="3">
													Post Dept ID : <s:select name="postId" id="postId" list="postIdList"></s:select>
														<!-- s:if test="%{costCenter=='430.00' || costCenter=='430.20' || costCenter=='430.25' || costCenter=='430.10' || costCenter=='430.30' || costCenter=='430.35'}"-->
														<!-- 	<input type="text" name="fileRef"  id="fileRef" />
															<input type="button" name="generateNo1" id="generateNo1" value="generateCodeNo" onclick="generateNextFileRefNo();" />-->
															
														<!-- /s:if-->
														<!-- s:if test="%{costCenter=='430.00' || costCenter=='430.20' || costCenter=='430.25' || costCenter=='430.10' || costCenter=='430.30' || costCenter=='430.35'}"-->
															
														<!--	<s:submit theme="simple" align="right" value="Job Sheet" method="generateJobSheet" id="generateNo2" ></s:submit>-->
													
														<!-- /s:if-->
													<s:submit otheme="simple" align="right" value="Send CONS & DM" method="sendForCMBatch" id="sendForCM" onclick="return confirm('Are You sure?')" ></s:submit>
													
													<!--<s:submit theme="simple" value="Print Dispatch of PIV" method="PrintDispatchOFPIV" id="btnprint" ></s:submit>-->
													<s:submit theme="simple" align="right" value="Exit" method="close" id="btnClose" ></s:submit>
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


			</td>
		</tr>
	</tbody>
</table>


</body>
</html>