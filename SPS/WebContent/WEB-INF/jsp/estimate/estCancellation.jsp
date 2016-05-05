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

function viewStatus(){
	alert("Standard Estimate Status :\n 01. New Estimate : \n 02. Modified Estimate: \n 05. Rejected by : \n 08. To be validate by Electrical Superintendent \n 10. To be validate by Electrical Engineer \n 20. To be validate by Planning  Engineer \n 30. To be validate by Chief Engineer \n 35. To be validate by Planning Chief Engineer \n 40. To be approved by DGM \n 50. Approved Estimates \n 55. To be confirmed by ACCT_REV \n 56. To be confirmed by DEO \n 60. PIV II confirmed \n 70. Sent for ConstructionMaintenance \n 80. Work Estimate Job Allocated by Construction");
	
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
									<td><s:form name="estimateViewer" action="estimateCancellation" method="POST" 
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
																<th width="18%" align="left">Post DeptID</th>
																
															</tr>
														</thead>
														<tbody>
													
													<s:iterator status="estimate" value="EstimateList">
														<tr bgcolor="#9DCBE6">
														<s:hidden name="hiddenEstimateNo" id="hiddenEstimateNo" />
														<s:hidden name="hiddendeptId" id="hiddendeptId" />  
														<td width="2%"><input type="radio" onClick="document.getElementById('hiddenEstimateNo').value = '<s:property value="id.applicationNo" />';document.getElementById('hiddendeptId').value = '<s:property value="deptId" />';" name='applicationId' value='<s:property value="id.applicationNo" />' />
<br></td>												<td width="20%"> <s:property  value="id.applicationNo" /></td>	
		 
														<td width="20%"> <s:property  value="totalCost" /></td>														 
														<td width="20%">  <s:property  value="id.deptId" /></td>
														<td width="20%"> <s:property  value="entryBy" /></td>
														<td width="18%"> <s:property  value="entryDate" /></td>
														<td width="18%"> <s:property  value="postDeptId" /></td>
														
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
														Status : <s:select name="fileRef" id="fileRef" list="#{'2':'Change The Estimate Into Modify Status','5':'Change The Estimate Into Rejected Status''8':'Change The Estimate Into to be validate by Electrical Superintendent','10':'Change The Estimate Into to be validate by Electrical Engineer' ,'20':'Change The Estimate Into to be validate by Planning Engineer' ,'30':'Change The Estimate Into to be validate by Chief Engineer','35':'Change The Estimate Into to be validate by Planning Chief Engineer','40':'Change The Estimate Into to be Approved by DGM' ,'50':'Change The Estimate Approved Estimate' ,'55':'Change The PIV of Estimate Into To be confirmed by ACCT_REV' ,'56':'Change The  PIV of Estimate Into To be confirmed by DEO' ,'60':'Change The  Status of Estimate as PIV II confirmed(Estimate will whow in send construction Maintenance list )' ,'70':'Estimate Will Show in Con/Mnt section' ,'90':'Estimate Will Show in Con/Mnt section'}"></s:select>	
														
														<!-- <input type="text" name="fileRef"  id="fileRef" />-->
															<!-- input type="button" name="generateNo1" id="generateNo1" value="generateCodeNo" onclick="generateNextFileRefNo();" /-->
															
														<!-- /s:if-->
														<!-- s:if test="%{costCenter=='430.00' || costCenter=='430.20' || costCenter=='430.25' || costCenter=='430.10' || costCenter=='430.30' || costCenter=='430.35'}"-->
															
															<!-- s:submit theme="simple" align="right" value="Job Sheet" method="generateJobSheet" id="generateNo2" --><!-- /s:submit-->
													
														<!-- /s:if-->
													<s:submit theme="simple" align="right" value="Change Status & Post DeptID" method="sendForCM" id="sendForCM" ></s:submit>
													<s:submit theme="simple" align="right" value="View Status" id="vieStatus"  onclick="viewStatus();"></s:submit>
													
													<!-- s:submit theme="simple" value="Print Dispatch of PIV" method="PrintDispatchOFPIV" id="btnprint" --><!-- /s:submit-->
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