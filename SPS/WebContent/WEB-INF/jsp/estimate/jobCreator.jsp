<%--
    Document   : Application Form
    Created on : May 16, 2010, 11:21:45 AM
    Author     : Dileepa Waduge
--%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/jsp/include.jsp"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Job Creator</title>
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
Ext.onReady(function() {
	$("#btnGenerateJobNo").removeAttr("disabled");
 <% HttpSession session = request.getSession(); %>
 <% String estNo=(String)session.getAttribute("estNo"); %>
 var estiamteNo ='<%=estNo%>';
 <% String cSCNo=(String)session.getAttribute("cSCNo"); %>
 var cSCNo ='<%=cSCNo%>';
 $('#txfdEstimateNo').val(estiamteNo);

 $('#txfdcSCNo').val(cSCNo);
loadPivDetails($('#txfdEstimateNo').val());
});
function loadPivDetails(estimateNo){
var estimateNo='';
	
	estimateNo = $('#txfdEstimateNo').val();
		//alert(estimateNo);
		 $.getJSON("sps/loadPIVDetails.ajax?estimateNo="+estimateNo, {ajax_command:"loadPIVDetails",ajax_ieCacheFix:new Date().getTime()}, function(json) {
	
			 	$('#pivNo').val(json.PivIINo);
				$('#pivAmount').val(json.pivAmount);
				$('#payDate').val(json.payDate);
				$('#paymentMode').val(json.paymentMode);
				$('#branchCode').val(json.branchCode);
				$('#bankCode').val(json.bankCode);
				$('#receiptNo').val(json.recieptNo);
				$('#checkNo').val(json.checkNo);
				$('#commerRef').val(json.applicationRef);
				$('#secDeposit').val(json.secDeposit);
				$('#serviceConnectionAmount').val(json.serviceConnectionAmount);
				$('#txfdJobNo').val(json.txfdJobNo);
	 	// $('#applicationRefs1').val(json.categoryCode);

	 
		 });
	
}
function loadPivDetailsOnChange(estimateNo){
var estimateNo='';
	$("#btnGenerateJobNo").removeAttr("disabled");
	
		//alert(estimateNo);
		 $.getJSON("sps/loadPIVDetails.ajax?estimateNo="+estimateNo.value, {ajax_command:"loadPIVDetails",ajax_ieCacheFix:new Date().getTime()}, function(json) {
	
			 	$('#pivNo').val(json.PivIINo);
				$('#pivAmount').val(json.pivAmount);
				$('#payDate').val(json.payDate);
				$('#paymentMode').val(json.paymentMode);
				$('#branchCode').val(json.branchCode);
				$('#bankCode').val(json.bankCode);
				$('#receiptNo').val(json.recieptNo);
				$('#checkNo').val(json.checkNo);
				$('#commerRef').val(json.applicationRef);
				$('#secDeposit').val(json.secDeposit);
				$('#serviceConnectionAmount').val(json.serviceConnectionAmount);
				$('#txfdJobNo').val(json.txfdJobNo);
	 	// $('#applicationRefs1').val(json.categoryCode);

	 
		 });
	
}

</script>
<s:head />
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
									<td class="tdPath"><a href="<s:url action="logintest"/>">Login Test</a></td>
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
									<td><s:form name="jobCreator" action="jobCreator" method="POST"
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
																<td class="tdHeadings">Create Job</td>
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
													<td class="tdHeadings" colspan="4">Estimate Detail</td>
												</tr>
												<tr>
													<td>
													<table width="100%" class="tldFromContent" border="0" >
														<thead>
															<tr>
																<th width="5%"></th>
																<th width="15%"></th>
																<th width="10%"></th>
																<th width="15%"></th>
																<th width="*"></th>
																<th width="5%"></th>
															</tr>
														</thead>
														<tbody>
														
															<tr>
																<td></td>
																<td><s:label value="Cost Center No"></s:label></td>
																<td><s:textfield name="costCenterNo" id="txfdCostCenterNo" size="6" maxlength="6" cssClass="inputArea" ></s:textfield></td>
																<td></td>
																<td></td>
																<td></td>
															</tr>
															<tr>
																<td></td>
																<td><s:label value="CSC No"></s:label></td>
																<td><s:textfield name="cSCNo" id="txfdcSCNo" size="6" maxlength="6" cssClass="inputArea" ></s:textfield></td>
																<td></td>
																<td></td>
																<td></td>
															</tr>
															<tr>
																<td></td>
																<td><s:label value="Estimate No"></s:label></td>
																<td><s:select  name="estimateNo" id="txfdEstimateNo" headerKey="1" headerValue="-- Please Select --" list="estimateNoList" onchange="loadPivDetails()" /></td>
																<!--<td><s:textfield name="estimateNo" id="txfdEstimateNo" size="20" maxlength="20" cssClass="inputArea" ></s:textfield></td>-->
																<td></td>
																<td></td>
																<td></td>
															</tr>
															<tr>
																<td></td>
																<td><s:label value="Commercial Reference No"></s:label></td>
																<td><s:textfield  name="commerRef" id="commerRef" size="20" maxlength="20" readonly="true"></s:textfield></td>
																<!--<td><s:textfield name="estimateNo" id="txfdEstimateNo" size="20" maxlength="20" cssClass="inputArea" ></s:textfield></td>-->
																<td><s:label value="Piv No"></s:label></td>
																<td><s:textfield name="pivNo" id="pivNo" size="20" maxlength="20" readonly="true" cssClass="inputArea" ></s:textfield></td>
																<td></td>
															</tr>
															
															<tr>
																<td></td>
																<td><s:label value="Amount for Service Connection(PIV II)"></s:label></td>
																<td><s:textfield name="serviceConnectionAmount" id="serviceConnectionAmount" size="20" maxlength="20" readonly="true" cssClass="inputArea" ></s:textfield></td>
																<td><s:label value="Amount for Security Deposit(PIV II)"></s:label></td>
																<td><s:textfield name="secDeposit" id="secDeposit" size="20" maxlength="20" readonly="true" cssClass="inputArea" ></s:textfield></td>
																<td></td>
															</tr>
															<tr>
																<td></td>
																<td></td>
																<td></td>
																<td></td>
																<td></td>
																<td></td>
															</tr>
															<tr>
																<td></td>
																<td><s:label value="Total PIV Amount"></s:label></td>
																<td><s:textfield name="pivAmount" id="pivAmount" size="20" maxlength="20" readonly="true" cssClass="inputArea" ></s:textfield></td>
																<td><s:label value="Reciept No"></s:label></td>
																<td><s:textfield name="receiptNo" id="checNo" size="20" maxlength="20" readonly="true" cssClass="inputArea" ></s:textfield></td>
																<td></td>
															</tr>
															<tr>
																<td></td>
																<td><s:label value="Pay Date"></s:label></td>
																<td><s:textfield name="payDate" id="payDate" size="20" maxlength="20" readonly="true" cssClass="inputArea" ></s:textfield></td>
																<td><s:label value="Paymemt Mode"></s:label></td>
																<td><s:textfield name="paymentMode" id="paymentMode" size="20" maxlength="20" readonly="true" cssClass="inputArea" ></s:textfield></td>
																<td></td>
															</tr>
															
															<tr>
																<td></td>
																<td><s:label value="Bank Code"></s:label></td>
																<td><s:textfield name="bankCode" id="bankCode" size="20" maxlength="20" readonly="true" cssClass="inputArea" ></s:textfield></td>
																<td><s:label value="Branch Code"></s:label></td>
																<td><s:textfield name="branchCode" id="branchCode" size="20" maxlength="20" readonly="true" cssClass="inputArea" ></s:textfield></td>
																<td></td>
															</tr>
															
															<tr>
																<td></td>
																<td></td>
																<td></td>
																<td><s:label value="Cheque No"></s:label></td>
																<td><s:textfield name="checkNo" id="checkNo" size="20" maxlength="20" readonly="true" cssClass="inputArea" ></s:textfield></td>
																<td></td>
															</tr>
															<tr>
																<td></td>
																<td><s:submit theme="simple" value="Generate Job No" method="generateJobNo" id="btnGenerateJobNo" onclick="" ></s:submit>
																</td>
																<td><s:textfield name="jobNo" id="txfdJobNo" size="20" maxlength="20" readonly="true"></s:textfield></td>
																<td></td>
																<td></td>
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
												
												<tr>
													<td id="tdBotton" colspan="3">
													<s:submit theme="simple" align="right" value="Exit" method="close" id="btnClose" ></s:submit>
													<s:reset theme="simple" align="right" value="Clear" />	
													<s:hidden id="state" name="state"></s:hidden>
													<s:hidden id="hiddenSecondState" name="hiddenSecondState"></s:hidden>
													<s:hidden id="path" name="path"></s:hidden> 
													<s:hidden id="status" name="status"></s:hidden> 
													<s:hidden id="errorMessage" name="errorMessage"></s:hidden></td>
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