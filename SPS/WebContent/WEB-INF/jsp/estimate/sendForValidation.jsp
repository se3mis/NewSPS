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
<script type="text/javascript" src="../../script/jquery-1.9.1.js"></script>
<script type="text/javascript" src="../../script/jquery-ui-1.10.2.custom.js"></script>
<link href="../../style/jquery-ui-1.10.2.custom.css" rel="stylesheet">
        
<script type="text/javascript">


$(function() {
	
	$( "#accordion" ).accordion();
	$("#accordion").accordion({ header: "h3", collapsible: true, active: false });
	});
	function getSelectedOption(this) {
	alert("hi");
	//appender = appender +destinationContainer;
		//$.getJSON("/ebtransport/eod/ajax/loadSelectedShipments.ajax?destShipAppender="+destinationContainer, {ajax_command: "loadSelectedShipments",ajax_ieCacheFix:new Date().getTime() },
		//	function(json){ 
						
			//		}
	//);
	  
	}

	
	function getvalue(){
	alert("hi");
	//alert(document.getElementById('hiddenTilteProperty').value);
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
									<td><s:form name="estimateViewer" action="sendForValidationBS" method="POST" 
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
														<s:hidden name="totalCost" id="totalCost" />
														<td><s:checkbox	name="DetailsList" fieldValue="%{id.applicationNo}" /></td>
														<td width="20%"> <s:property  value="id.applicationNo" /></td>	
														<td width="30%"> <s:text name="format.currency" ><s:param value="totalCost" /></s:text></td>														 
														<td width="20%">  <s:property  value="id.deptId" /></td>
														<td width="20%"> <s:property  value="entryBy" /></td>
														<td width="18%"> <s:property  value="entryDate" /></td>
														<!-- <td width="18%"> <s:submit  value="View Estimate" method="viewEstimate" /></td>-->
												
														</tr>
													</s:iterator>
													
														
													</tbody>
													</table>
												<tr>
												
												<td  colspan="3" >
												<s:label>Comment : </s:label>
												<textarea type="text"  name="recomReason" id="recomReason"></textarea></td>
																
												</tr>
												<tr>                             
													<td id="tdBotton" colspan="3">
													Sent to : <s:select name="postUserName" id="postUserName" list="listuserName"></s:select>
													<s:submit theme="simple" align="right" value="Send For Approval" method="post" id="postEE" onclick="return confirm('please check the selected APPROVAL USER correct or not')"></s:submit>
													
												
												
													<!--<s:submit theme="simple" align="right" value="  CE  " method="postCE" id="postCE" ></s:submit>
													<s:submit theme="simple" align="right" value="  DGM  " method="postDGM" id="postDGM" ></s:submit>-->
													<s:submit theme="simple" align="right" value="  Exit  " method="close" id="btnClose" ></s:submit>
													
												</tr>
											</tbody>
										</table>

									</s:form>
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