<%--
    Document   : Application Form
    Created on : May 16, 2010, 11:21:45 AM
    Author     : Dileepa Waduge
--%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="org.apache.catalina.Session"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@ page language="java" session="false"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Application Form</title>
<link rel="stylesheet" type="text/css" href="../../style/style.css" />
<script type="text/javascript" src="../../script/applicationForm.js"></script>
<script type="text/javascript" src="../../script/javascript.js"></script>
<link rel="stylesheet" type="text/css" href="../../style/style.css" />
<script type="text/javascript" src="../../script/diaryEntry.js"></script>
<script type="text/javascript" src="../../script/jquery/jquery.js"> </script>
<script type="text/javascript" src="../../script/jquery/jquery.min.js"> </script>

<link rel="stylesheet" type="text/css" href="../../script/ext-3.3.1/css/setup.css" media="all" />
<link rel="stylesheet" type="text/css" href="../../script/ext-3.3.1/resources/css/ext-all.css" />
<link rel="stylesheet" type="text/css" href="../../script/ext-3.3.1/resources/css/xtheme-gray.css" />
 <script type="text/javascript" src="../../script/ext-3.3.1/adapter/ext/ext-base.js"></script>
        <script type="text/javascript" src="../../script/ext-3.3.1/ext-all-debug.js"></script>
        
<link rel="stylesheet" type="text/css" href="../../style/datePicker.css" />		
<script src="../../script/jquery/jquery.datePicker-min.js" type="text/javascript"></script>
<%
HttpSession session = request.getSession();
String type = session.getValue("smcType").toString();

%>

<script type="text/javascript">
Ext.onReady(function() {


});
</script>
<script type="text/javascript">

function loadCodeName(codeId,codeType){
	//alert(userId.value);
	 $.getJSON("sps/getCodeName.ajax?codeId="+codeId+"&codeType="+codeType, {ajax_command:"getCodeName",ajax_ieCacheFix:new Date().getTime()}, function(json) {
		 if(codeType=='DISTRICT'){
				$('#district').val(json.codeName);
			}
			if(codeType=='ELECTORATE'){
				$('#electrate').val(json.codeName);
			}
			if(codeType=='CSC'){
				$('#cscname').val(json.codeName);
			}
			
 	// $('#applicationRefs1').val(json.categoryCode);

 
	 });
}
var test='<%=type%>';

if(test== 'RE'){

	document.getElementById("premises").disabled=true;
	document.getElementById("foundSource").disabled=true;
	document.getElementById("txfdCity").disabled=true;
	document.getElementById("txfdFirstName").disabled=true;
	document.getElementById("txfdLastName").disabled=true;
	document.getElementById("txfdApplicationSubType").disabled=true;
	document.getElementById("txfdAppReferenceNo").disabled=true;
	document.getElementById("txfdDescription").disabled=true;
	document.getElementById("repres1").disabled=true;
	document.getElementById("repres2").disabled=true;
	document.getElementById("demand").disabled=true;
	document.getElementById("txfdCustomerCategory").disabled=true;
	document.getElementById("txfdTariffCategoryCode").disabled=true;
	document.getElementById("isAlreadyHave").disabled=true;
	document.getElementById("txfdCustomerType").disabled=true;
	//document.getElementById("txfdTariffCode").disabled=true;

	
}else{
	document.getElementById("premises").disabled=false;
	document.getElementById("foundSource").disabled=false;
	document.getElementById("txfdCity").disabled=false;
	document.getElementById("txfdFirstName").disabled=false;
	document.getElementById("txfdLastName").disabled=false;
	document.getElementById("txfdApplicationSubType").disabled=false;
	document.getElementById("txfdAppReferenceNo").disabled=false;
	document.getElementById("txfdDescription").disabled=false;
	document.getElementById("repres1").disabled=false;
	document.getElementById("repres2").disabled=false;
	document.getElementById("demand").disabled=false;
	document.getElementById("txfdCustomerCategory").disabled=false;
	document.getElementById("txfdTariffCategoryCode").disabled=false;
	document.getElementById("isAlreadyHave").disabled=false;
	document.getElementById("txfdCustomerType").disabled=false;
	//document.getElementById("txfdTariffCode").disabled=false;
}
function disableByApplicationTypes(val){
	
	if(val.value == 'RE'){
	
	//$("#txfdTariffCode").attr("disabled", "disabled");
	//$('foundSource').attr('disabled', 'disabled');
	document.getElementById("premises").disabled=true;
	document.getElementById("foundSource").disabled=true;
	document.getElementById("txfdCity").disabled=true;
	document.getElementById("txfdFirstName").disabled=true;
	document.getElementById("txfdLastName").disabled=true;
	document.getElementById("txfdApplicationSubType").disabled=true;
	document.getElementById("txfdAppReferenceNo").disabled=true;
	document.getElementById("txfdDescription").disabled=true;
	document.getElementById("repres1").disabled=true;
	document.getElementById("repres2").disabled=true;
	document.getElementById("demand").disabled=true;
	document.getElementById("txfdCustomerCategory").disabled=true;
	document.getElementById("txfdTariffCategoryCode").disabled=true;
	document.getElementById("isAlreadyHave").disabled=true;
	document.getElementById("txfdCustomerType").disabled=true;
	//document.getElementById("txfdTariffCode").disabled=true;



	}else{
	document.getElementById("premises").disabled=false;
	document.getElementById("foundSource").disabled=false;
	document.getElementById("txfdCity").disabled=false;
	document.getElementById("txfdFirstName").disabled=false;
	document.getElementById("txfdLastName").disabled=false;
	document.getElementById("txfdApplicationSubType").disabled=false;
	document.getElementById("txfdAppReferenceNo").disabled=false;
	document.getElementById("txfdDescription").disabled=false;
	document.getElementById("repres1").disabled=false;
	document.getElementById("repres2").disabled=false;
	document.getElementById("demand").disabled=false;
	document.getElementById("txfdCustomerCategory").disabled=false;
	document.getElementById("txfdTariffCategoryCode").disabled=false;
	document.getElementById("isAlreadyHave").disabled=false;
	document.getElementById("txfdCustomerType").disabled=false;
	//document.getElementById("txfdTariffCode").disabled=false;
	}
}	

function disableByElectricityHave(val){

	if(val.value=="Y"){
		document.getElementById("capacityOfServices").disabled=false;
		document.getElementById("accountNos").disabled=false;
		document.getElementById("meterPoints").disabled=false;
	}else{
		document.getElementById("capacityOfServices").disabled=true;
		document.getElementById("accountNos").disabled=true;
		document.getElementById("meterPoints").disabled=true;
	}
}	
</script>
<s:head />
<sx:head />
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
				                    <td ><img src="../../image/NewSPS2.gif" width="100%"  alt="NewSPS2"/></td>
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
									<td><s:form action="applicationFormREBS" method="POST" validate="true" theme="simple">
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
																<td class="tdHeadings">APPLICATION FORM CEYLON ELECTRICITY BOARD</td>
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
																<s:else>
																<td class="tdMsgStyleInfo"><s:property value="errorMessage"></s:property></td>	
																</s:else>
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
													<td class="tdHeadings" colspan="4">Proposer Detail</td>
												</tr>
												<tr>
													<td>
													<table width="100%" class="tldFromContent" border="0">
														<thead>
															<tr>
																<th width="90%"></th>
																<th width="*"></th>
															</tr>
														</thead>
														<tbody>
															<tr>
																<td>
																<table width="100%" class="tldFromContent" border="0">
																	<thead>
																		<tr>
																			<th width="3%"></th>
																			<th width="18%"></th>
																			<th width="30%"></th>
																			<th width="18%"></th>
																			<th width="25%"></th>
																			<th width="*%"></th>
																		</tr>
																	</thead>
																	<tbody>
																		<tr>
																			<td></td>
																			<td><s:label value="Id Type"></s:label></td>
																			<td><s:textfield name="idType" readonly="true" size="10" id="idType"></s:textfield></td>
																			<td><s:label value="Id No"></s:label></td>
																			<td><s:textfield name="idNo" size="10" id="txfdIdNo"  onblur="javascript:{this.value = this.value.toLowerCase(); }"></s:textfield><s:submit theme="simple" align="right" value="Find" method="findId" id="btnFindId" onclick="return checkIdNoNotNull()"></s:submit></td>
																			<td></td>
																		</tr>
																		<tr>
																			<td></td>
																			<td><s:label value="First Name (Initials)"></s:label></td>
																			<td><s:textfield name="firstName" id="txfdFirstName" readonly="true" size="25"></s:textfield></td>
																			<td><s:label value="Last Name"></s:label></td>
																			<td><s:textfield name="lastName" id="txfdLastName" readonly="true" size="25"></s:textfield></td>
																			<td></td>
																		</tr>
																		<tr>
																			<td></td>
																			<td><s:label value="Street Address"></s:label></td>
																			<td><s:textarea name="streetAddress" id="txfdStreetAddress" readonly="true" cols="30" rows="1"></s:textarea></td>
																			<td><s:label value="Suburb"></s:label></td>
																			<td><s:textfield name="suburb" id="txfdSuburb" readonly="true" size="20"></s:textfield></td>
																			<td></td>
																		</tr>
																		<tr>
																			<td></td>
																			<td><s:label value="City"></s:label></td>
																			<td><s:textfield name="city" id="txfdCity" readonly="true" size="20"></s:textfield></td>
																			<td><s:label value="Postal Code"></s:label></td>
																			<td><s:textfield name="postalCode" id="txfdPostalCode" readonly="true" size="5" maxlength="5"></s:textfield></td>
																			<td></td>
																		</tr>
																		<tr>
																			<td></td>
																			<td><s:label value="Telephone No"></s:label></td>
																			<td><s:textfield name="telephoneNo" id="txfdTelephoneNo" readonly="true" size="10"></s:textfield></td>
																			<td><s:label value="Mobile No"></s:label></td>
																			<td><s:textfield name="mobileNo" id="txfdMobileNo" readonly="true" size="10"></s:textfield></td>
																			<td></td>
																		</tr>
																		<tr>
																			<td></td>
																			<td><s:label value="Email"></s:label></td>
																			<td><s:textfield name="email" id="txfdEmail" readonly="true" size="30"></s:textfield></td>
																			<td></td>
																			<td></td>
																			<td></td>
																		</tr>
																		
																	</tbody>
			
																</table>
																</td>
																<td valign="top">
																<!--<table width="100%" class="tldFromContent" border="0">
																	<thead>
																		<tr>
																			<th width="30%" ></th>
																			<th width="*"></th>
																		</tr>
																	</thead>
																	<tbody>
																		<tr>
																			<td><s:label value="Street Address"></s:label></td>
																			<td><s:textarea name="streetAddress" readonly="true" cols="30" rows="1"></s:textarea></td>
																		</tr>
																		<tr>
																			<td><s:label value="Suburb"></s:label></td>
																			<td><s:textfield name="suburb" readonly="true" size="20"></s:textfield></td>
																		</tr>
																		
																		<tr>
																			<td><s:label value="City"></s:label></td>
																			<td><s:textfield name="city" readonly="true" size="20"></s:textfield></td>
																			
																		</tr>
																		<tr>
																			<td><s:label value="Postal Code"></s:label></td>
																			<td><s:textfield name="postalCode" readonly="true" size="5" maxlength="5"></s:textfield></td>
																		</tr>
																		
																	</tbody>

																</table>-->
																</td>
															</tr>
														</tbody>
													</table>			
																									
													</td>
												</tr>
												<tr>
													<td class="tdHeadings" colspan="4">Application Detail</td>
												</tr>
												<tr>
													<td>
													<table width="100%" class="tldFromContent" border="0">
														<thead>
															<tr>
																<th width="90%"></th>
																<th width="*"></th>
															</tr>
														</thead>
														<tbody>
															<tr>
																<td  valign="top">
																<table width="100%" class="tldFromContent" border="0">
																	<thead>
																		<tr>
																			<th width="3%"></th>
																			<th width="18%"></th>
																			<th width="30%"></th>
																			<th width="18%"></th>
																			<th width="25%"></th>
																			<th width="*%"></th>
																		</tr>
																	</thead>
																	<tbody>
																		<tr>
																			<td></td>
																			<td><s:label value="Temp Id"></s:label></td>
																			<td><s:textfield name="applicationId" id="txfdApplicationId" size="25" maxlength="20" onblur="javascript:{this.value = this.value.toUpperCase(); }"></s:textfield><s:submit theme="simple" align="right" value="Find" method="findAppId" id="btnFindAppId" onclick="return checkAppIdNoNotNull()"></s:submit></td>
																			<td><s:label value="Date"></s:label></td>
																			<td><s:textfield name="date" id="txfdDate" size="10"></s:textfield></td>
																			<td></td>
																		</tr>
																	<!-- 	<tr>
																			<td></td>
																			<td><s:label value="Application Type"></s:label></td>
																			<td colspan="3"><s:radio name="applicationType" id="RadioApplicationType" list="#{'NL':'New Line','CR':'Consumer Req','MB':'Mobile Tower'}" value="'NL'" /><s:hidden id="hiddenApplicationType" name="hiddenApplicationType"></s:hidden></td>
																			<td><s:select id="chvJobCategory" name="chvJobCategory" list="#{'NEW':'NEW','AUGMENTATION':'AUGMENTATION','REHABILITATION':'REHABILITATION'}"/></td>
																			<td></td>

																		</tr>
																	-->
																		<tr>
																			<td></td>
																			<td><s:label value="Area Code"></s:label></td>
	
																			<td ><s:select name="areaName" id="areaName" list="areaCodeList"></s:select></td>
																			<td><s:label value="CSC"></s:label></td>
																			<td><s:textfield name="csc" id="txfdCsc" size="25" onkeypress="return onlyLetters(event)"></s:textfield></td>
																			<td></td>
																		</tr>
																		
																		
																		<!-- vdfgdfgfg -->
																		<tr>
																			<td></td>
																			<td><s:label value="Application Type"></s:label></td>
																			<td ><s:select name="applicationType" id="applicationType" list="#{'BS':'BS','RD':'RD','RE':'RE','RI':'RE Projects','LN':'LN','CP':'Other','PL':'PLANNING','DM':'Maintenance','EM':'Energy Management','CN':'Construction'}"  onchange="disableByApplicationTypes(this)"></s:select></td>
																			<td><s:label value="Fund Source"></s:label></td>
																			<td><s:select  name="foundSource" id="foundSource"  headerKey="1" list="foundSourceList" />
																			<s:hidden id="hiddenApplicationType" name="hiddenApplicationType"></s:hidden>
																			</td>
																			
																			
																			<td></td>
																		</tr>
																		
																		<tr>
																			<td></td>
																			<td></td>
																			<td></td>
																			<td><s:label value="Nature of Supply"></s:label></td>
																			<td><s:select  name="applicationSubType" id="txfdApplicationSubType"  headerKey="1" list="#{'PM':'Permanent','C':'Construction','T':'Temporary','A':'Augmentation','M':'Maintenance'}" onchange="disableDuration()"/>
																			<s:hidden id="hiddenApplicationType" name="hiddenApplicationType"></s:hidden>
																			<td></td>
																		</tr>
																		<tr>
																			<td></td>
																			
																			<td><s:label value="CostCenter"></s:label></td>
																			<td><s:textfield name="costCenterNo" id="txfdCostCenterNo" size="25" onkeypress="return onlyLetters(event)"></s:textfield></td>
																			
																			<td></td>
																			
																		</tr>
																		
																		
																		<tr>
																			<td></td>
																			<td><s:label value="Description"></s:label></td>
																			<td><s:textarea name="description" id="txfdDescription" cols="30" rows="1"></s:textarea></td>
																			<td><s:label value="Consumer Ref"></s:label></td>
																			<td><s:textarea name="consumerRef" id="consumerRef" cols="30" rows="1"></s:textarea></td>
																			<td></td>
																		</tr>
																		<tr>
																			<td></td>
																			<td><s:label value="Representative 1"></s:label></td>
																			<td><s:textarea name="repres1" id="repres1" cols="30" rows="2" onblur="javascript:{this.value = this.value.toUpperCase(); }"></s:textarea></td>
																			<td><s:label value="Contact No Representative 1 "></s:label></td>
																			<td><s:textfield name="repres1Con" id="repres1Con" size="10" maxlength="10" onkeypress="return onlyNumbersWithoutSpaceBar(event)"></s:textfield></td>
																		</tr>
																		<tr>
																			<td></td>
																			<td><s:label value="Representative 2"></s:label></td>
																			<td><s:textarea name="repres2" id="repres2" cols="30" rows="2" onblur="javascript:{this.value = this.value.toUpperCase(); }"></s:textarea></td>
																			<td><s:label value="Contact No Representative 2 "></s:label></td>
																			<td><s:textfield name="repres2Con" id="repres2Con" size="10" maxlength="10" onkeypress="return onlyNumbersWithoutSpaceBar(event)"></s:textfield></td>
																		</tr>
																		<tr>
																			<td></td>
																			<td><s:label value="Scheme Name"></s:label></td>
																			<td><s:textarea name="schemaName" id="schemaName" cols="30" rows="2" onblur="javascript:{this.value = this.value.toUpperCase(); }"></s:textarea></td>
																			<td><s:label value="Scheme Extention Name"></s:label></td>
																			<td><s:textarea name="schemaNo" id="schemaNo" cols="30" rows="2" onblur="javascript:{this.value = this.value.toUpperCase(); }"></s:textarea></td>
																			
																			<td></td>
																		</tr>
																	<!-- vdfgdfgfg -->	
																	</tbody>

																</table>
																</td>
																<td valign="top">
																 <!-- <table width="100%" class="tldFromContent" border="0">
																	<thead>
																		<tr>
																			<th width="30%"></th>
																			<th width="*"></th>
																		</tr>
																	</thead>
																	<tbody>
																		<tr>
																			<td><s:label value="Application Type"></s:label></td>
																			<td ><s:radio name="applicationType" id="RadioApplicationType" list="#{'NC':'New Conn'}" value="'NC'" />
																			<s:select  name="applicationSubType" id="txfdApplicationSubType"  headerKey="1" list="#{'PM':'Permanent','C1':'Temp Cons1','C2':'Temp Cons2','C3':'Temp Cons3'}" onchange="disableDuration()"/>
																			<s:hidden id="hiddenApplicationType" name="hiddenApplicationType"></s:hidden>
																			
																			</td>
																		</tr>
																		<tr>
																			<td><s:label value="Duration" id="labelDuration"></s:label></td>
																			<td>
																			<s:select  name="durationType" id="txfdDurationType"  headerKey="1" list="#{'M':'Months','Y':'Years'}"/>
																			<s:textfield name="duration" id="txfdDuration" size="2" maxlength="2"></s:textfield>
																			
																			</td>
																			
																		</tr>
																		<tr>
																			<td><s:label value="From_To" id="labelFromDate"></s:label></td>
																			<td>
																			<sx:datetimepicker  name="fromDate" id="txfdFromDate" displayFormat="yyyy-MM-dd"  required="true" />
																			<sx:datetimepicker  name="toDate" id="txfdToDate" displayFormat="yyyy-MM-dd"  required="true" />
																			</td>
																			
																		</tr>
																		<tr>
																			<td><s:label value="Application No" id="labelAppReferenceNo"></s:label></td>
																			<td><s:textfield name="appReferenceNo" id="txfdAppReferenceNo" size="25"></s:textfield><s:submit theme="simple" align="right" value="Find No" method="findAppReferenceNo" id="btnFindAppReferenceNo" onclick="return checkAppIdNoNotNull()"></s:submit></td>
																		</tr>
																		<tr>
																			<td><s:label value="Allocated To" id="labelAllocatedTo"></s:label></td>
																			<td><s:textfield name="allocatedTo" id="txfdAllocatedTo" size="10"></s:textfield></td>
																		</tr>
																		<tr>
																			<td><s:label value="Confirm PIV" id="labelConfirmPIV"></s:label></td>
																			<td><s:radio name="confirmPiv" id="RadioConfirmPiv" list="#{'Y':'Yes','N':'No'}"  /><s:hidden id="hiddenConfirmPiv" name="hiddenConfirmPiv"></s:hidden></td>
																		</tr>
																		
																	</tbody>

																</table> -->
																
																</td>
															</tr>
															<tr>
																<td></td>
																<td></td>
															</tr>
														</tbody>

													</table>
												
													</td>
												</tr>
												
												<tr>
													<td class="tdHeadings" colspan="4">Location Detail</td>
												</tr>
												<tr>
													<td >
													<table width="100%" class="tldFromContent" border="0">
														<thead>
															<tr>
																<th width="90%"></th>
																<th width="*"></th>
															</tr>
														</thead>
														<tbody>
															<tr>
																<td valign="top">
																<table width="100%" class="tldFromContent" border="0">
																	<thead>
																		<tr>
																			<th width="3%"></th>
																			<th width="18%"></th>
																			<th width="30%"></th>
																			<th width="18%"></th>
																			<th width="25%"></th>
																			<th width="*%"></th>
																		</tr>
																	</thead>
																	<tbody>
																	
    																	<tr>
																			<td></td>
																			<td></td>
																			<td></td>
																			<!-- <td><s:label value="C S C"></s:label></td>
																			<td><s:textfield name="cscname" id="cscname" size="40" maxlength="40" onblur="javascript:{this.value = this.value.toUpperCase(); }"></s:textfield></td>
																			 -->
																			<td><s:label value="Postal Code"></s:label></td>
																			<td><s:textfield name="servicePostalCode" id="txfdServicePostalCode" size="5" maxlength="5" onkeypress="return onlyNumbersWithoutSpaceBar(event)"></s:textfield></td>
																			<td></td>
																		</tr>
																		<tr>
																			<td></td>
																			<td><s:label value="District"></s:label></td>
																			<td><s:textfield name="district" id="district" size="40" maxlength="40" onblur="javascript:{this.value = this.value.toUpperCase(); }"></s:textfield>
																			<s:if test="%{costCenterNo=='450.20' || costCenterNo=='450.00' || costCenterNo=='450.30' || costCenterNo=='430.00' || costCenterNo=='430.10' || costCenterNo=='430.20' || costCenterNo=='430.25' || costCenterNo=='430.30' || costCenterNo=='430.35' || costCenterNo=='530.30'}">
																			<s:select name="districtCode" id="districtCode" list="districtCodeList" onchange="loadCodeName(this.value,'DISTRICT')"/>
																			</s:if></td>
																			<td><s:label value="Devisional Sectrait"></s:label></td>
																			<td><s:textfield name="devSec" id="devSec" size="40" maxlength="40" onblur="javascript:{this.value = this.value.toUpperCase(); }"></s:textfield>
																			
																			<td></td>
																		</tr>
																		<tr>
																			<td></td>
																			
																			<td><s:label value="GS Division"></s:label></td>
																			<td><s:textfield name="gsDivision" id="gsDivision" size="40" maxlength="40" onblur="javascript:{this.value = this.value.toUpperCase(); }"></s:textfield></td>
																			<td><s:label value="Electorate"></s:label></td>
																			<td><s:textfield name="electrate" id="electrate" size="40" maxlength="40" onblur="javascript:{this.value = this.value.toUpperCase(); }"></s:textfield>
																			<s:if test="%{costCenterNo=='450.20' || costCenterNo=='450.00' || costCenterNo=='450.30' || costCenterNo=='430.00' || costCenterNo=='430.10' || costCenterNo=='430.20' || costCenterNo=='430.25' || costCenterNo=='430.30' || costCenterNo=='430.35' || costCenterNo=='530.30'}">
																			<s:select name="electorateCode" id="electorateCode" list="electorateCodeList" onchange="loadCodeName(this.value,'ELECTORATE')"/>
																			</s:if></td>
																			
																			 <td><s:label value="AGA Division"></s:label></td>
																			<td><s:textfield name="agaDivision" id="agaDivision" size="40" maxlength="40" onblur="javascript:{this.value = this.value.toUpperCase(); }"></s:textfield></td>
																			<td></td>
																		</tr>
																		<tr>
																			<td></td>
																			<td><s:label value="C S C"></s:label></td>
																			<td><s:textfield name="cscname" id="cscname" size="40" maxlength="40" onblur="javascript:{this.value = this.value.toUpperCase(); }"></s:textfield>
																			<s:if test="%{costCenterNo=='450.20' || costCenterNo=='450.00' || costCenterNo=='450.30' || costCenterNo=='430.00' || costCenterNo=='430.10' || costCenterNo=='430.20' || costCenterNo=='430.25' || costCenterNo=='430.30' || costCenterNo=='430.35'}">
																			<s:select name="cscCode" id="cscCode" list="cscCodeList" onchange="loadCodeName(this.value,'CSC')"/>
																			</s:if></td>
																			<td></td>
																			<td></td>
																			<!-- <td><s:label value="Ownership"></s:label></td>
																			<td><s:radio name="ownership" id="RadioOwnership" list="#{'O':'Owner','R':'Rent','N':'Tenant'}" /><s:hidden id="hiddenOwnership" name="hiddenOwnership"></s:hidden></td> -->
																			<!--  <td><s:label value="Occupy / Owner Certified"></s:label></td>
																			<td><s:radio name="occupy_OwnerCertified" id="RadioOccupy_OwnerCertified" list="#{'Y':'Owner','N':'Tenant'}" /><s:hidden id="hiddenOccupy_OwnerCertified" name="hiddenOccupy_OwnerCertified"></s:hidden></td>-->
																			<td></td>
																		</tr>
																		
																		<!--  <tr>
																			<td></td>
																			<td><s:label value="Electrate"></s:label></td>
																			<td><s:textfield name="electrate" id="electrate" size="40" maxlength="40" onblur="javascript:{this.value = this.value.toUpperCase();}"></s:textfield></td>
																			<td><s:label value="Devisional Sector"></s:label></td>
																			<td><s:textfield name="devSec" id="devSec" size="40" maxlength="40" onblur="javascript:{this.value = this.value.toUpperCase(); }"></s:textfield>
																			<td></td>
																		</tr>
																		<tr>
																			<td></td>
																			<td><s:label value="GS Division"></s:label></td>
																			<td><s:textfield name="gsDivision" id="gsDivision" size="40" maxlength="40" onblur="javascript:{this.value = this.value.toUpperCase(); }"></s:textfield></td>
																			<td><s:label value="AGA Division"></s:label></td>
																			<td><s:textfield name="agaDivision" id="agaDivision" size="40" maxlength="40" onblur="javascript:{this.value = this.value.toUpperCase(); }"></s:textfield></td>
																			<td></td>
																		</tr>
																		<tr>
																			<td></td>
																			<td><s:label value="District"></s:label></td>
																			<td><s:textfield name="district" id="district" size="50" maxlength="50" onblur="javascript:{this.value = this.value.toUpperCase(); }"></s:textfield></td>
																			
																			<td></td>
																			<td></td>
																			
																		
																			<td></td>
																		</tr>-->
																	</tbody>

																</table>
																</td>
																<td valign="top">
																<!--<table width="100%" class="tldFromContent" border="0">
																	<thead>
																		<tr>
																			<th width="55%"></th>
																			<th width="*"></th>
																		</tr>
																	</thead>
																	<tbody>
																		<tr><td><s:label value="Ownership"></s:label></td>
																			<td><s:radio name="ownership" id="RadioOwnership" list="#{'O':'Ocupy','R':'Rent'}" /><s:hidden id="hiddenOwnership" name="hiddenOwnership"></s:hidden></td>
																			
																		</tr>
																		<tr>
																			<td><s:label value="Occupy / Owner Certified"></s:label></td>
																			<td><s:radio name="occupy_OwnerCertified" id="RadioOccupy_OwnerCertified" list="#{'Y':'Yes','N':'No'}" /><s:hidden id="hiddenOccupy_OwnerCertified" name="hiddenOccupy_OwnerCertified"></s:hidden></td>
																			
																		</tr>
																		<tr>
																			<td><s:label value="Is Governmenent Place"></s:label></td>
																			<td><s:radio name="isGovernmentPlace" id="RadioIsGovernmentPlace" list="#{'Y':'Yes','N':'No'}" /><s:hidden id="hiddenIsGovernmentPlace" name="hiddenIsGovernmentPlace"></s:hidden></td>
																			
																		</tr>
																		<tr>
																			<td></td>
																			<td></td>
																		</tr>
																	</tbody>
																</table>-->		
																</td>
															</tr>	
														</tbody>
													</table> 	
																										
													</td>
												</tr>
						
												<tr>
													<td class="tdHeadings" colspan="4">Other Details</td>
												</tr>
												<tr>
													<td>
													<table width="100%" class="tldFromContent" border="0">
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
																<td><s:label value="Prepared By"></s:label></td>
																<td><s:textfield name="preparedBy" id="txfdPreparedBy" readonly="true"
																	size="10"></s:textfield></td>
																<td><s:label value="Confirmed By"></s:label></td>
																<td><s:textfield name="confirmedBy"
																	id="txfdConfirmedBy" size="10"></s:textfield></td>
																<td></td>
															</tr>

														</tbody>

													</table>
													</td>
												</tr>
												<tr>
													<td id="tdBotton" colspan="3">
													
													<s:submit theme="simple" value="Save" method="save" id="btnSave" 	onclick="return confirm('Please Check The Entered Data - Area Code/CSC/Fund Source/Nature Of Supply Correct or Not?')"></s:submit> 
													
													
													<s:submit theme="simple" align="right" value="Modify" method="modify" id="btnModify" onclick="return validate_form()"></s:submit> 
												
													<!--<s:submit theme="simple" align="right" value="Next" method="next" id="btnNext" onclick="return validate_form()"></s:submit>-->
													
													<!--<s:if test="%{costCenterNo=='541.00' || costCenterNo=='542.00' || costCenterNo=='547.00' || costCenterNo=='548.00'}">
														<s:submit theme="simple" align="right" value="Generate" method="confirmCC" id="btnConfirm" onclick="return validate_form()"></s:submit>
													</s:if>
													<s:else>-->
													
													<s:submit theme="simple" align="right" value="Generate" method="confirm" id="btnConfirm" onclick="return validate_form()"></s:submit>  
													
													<!--</s:else>-->
													
													<applet codebase="../../PrintLib" code="applicationPrint.ApplicationFormApp" archive="PrintJar.jar" name="ApplicationApplet" width="1" height="1"  >
													</applet>
													<s:if test="%{state=='print'}">
													<s:submit theme="simple" align="right" value="Print" id="btnPrint" onclick="printApplication()" type="button"></s:submit> 
													</s:if>
													<s:if test="%{state=='changeServiceAdd'}">
													<s:submit theme="simple" align="right" value="Change Service Add " id="btnChangeServiceAdd" method="changeServiceAdd"></s:submit> 
													</s:if>
													<s:if test="%{state=='alterLoanApp'}">
													<s:submit theme="simple" align="right" value="Alter Loan App " id="btnAlterLoanApp" method="alterLoanApp"></s:submit> 
													</s:if>
													<s:submit theme="simple" align="right" value="Exit" method="close" id="btnClose"></s:submit> 
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