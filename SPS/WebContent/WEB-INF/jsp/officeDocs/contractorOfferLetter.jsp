<%@ include file="/WEB-INF/jsp/include.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Construction Letters</title>
<link rel="stylesheet" type="text/css" href="../../style/style.css" />
<script type="text/javascript" src="../../script/letter.js"> </script>
<script type="text/javascript" src="../../script/javascript.js"> </script>
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

<script type="text/javascript"><!--
Ext.onReady(function() {
$('#expectedDateofFinish').datePicker({clickInput:true});
loadEstimateNumForContractorLetter();
//loadContractorForContractorLetter();
});
function loadEstimateNumForContractorLetter(){
	$("#applicationRefs1 select option:gt(0)").remove();
	 $.getJSON("sps/loadEstimateNumForContractorLetter.ajax", {ajax_command:"loadEstimateNumForContractorLetter",ajax_ieCacheFix:new Date().getTime()}, function(json) {

       if (json.estimateNumbers != null && json.estimateNumbers.length > 0) {
           for (x = 0; x < json.estimateNumbers.length; x++) {
            
               $("#applicationRefs1 select").append($('<option></option>').val(json.estimateNumbers[x].id).html(json.estimateNumbers[x].name));
           }
       }
      
  	// $('#applicationRefs1').val(json.categoryCode);

  
	 });
	
	
}
function loadContractorForContractorLetter(){
	$("#ContractorList select option:gt(0)").remove();
	 $.getJSON("sps/loadEstimateNumForContractorLetter.ajax", {ajax_command:"loadContractorForContractorLetter",ajax_ieCacheFix:new Date().getTime()}, function(json) {

       if (json.contractors != null && json.contractors.length > 0) {
           for (x = 0; x < json.contractors.length; x++) {
            
               $("#ContractorList select").append($('<option></option>').val(json.contractors[x].id).html(json.contractors[x].name));
           }
       }
      
  	// $('#applicationRefs1').val(json.categoryCode);

  
	 });
	
	
}
function submitForm()
{
	document.forms["estRefForm"].submit();
 
}
function loadConstructionRefsContarctorOfferLetter(estimateNo){
	$("#constructionEstimate select option:gt(0)").remove();
	//$("#allocatedIDs select option:gt(0)").remove();
	 $.getJSON("sps/loadConstructonRefreForContractorLetter.ajax?ApplicationNumber="+estimateNo.value, {ajax_command:"loadConstructonRefreForContractorLetter",ajax_ieCacheFix:new Date().getTime()}, function(json) {

       if (json.estimateNumbers != null && json.estimateNumbers.length > 0) {
           for (x = 0; x < json.estimateNumbers.length; x++) {
            
               $("#constructionEstimate select").append($('<option></option>').val(json.estimateNumbers[x].id).html(json.estimateNumbers[x].name));
           }
       }
      /* if (json.allocatedIds != null && json.allocatedIds.length > 0) {
           for (x = 0; x < json.allocatedIds.length; x++) {
            
               $("#allocatedIDs select").append($('<option></option>').val(json.allocatedIds[x].id).html(json.allocatedIds[x].name));
           }
       }*/
  	// $('#applicationRefs1').val(json.categoryCode);

  
	 });
	
	
}
function loadEstimateDetailsForContarctorOfferLetter(estimateNo){
	//$("#constructionEstimate select option:gt(0)").remove();
	//$("#allocatedIDs select option:gt(0)").remove();
	 $.getJSON("sps/loadEstimateDetailsForContractorLetter.ajax?constructionId="+estimateNo.value, {ajax_command:"loadEstimateDetailsForContractorLetter",ajax_ieCacheFix:new Date().getTime()}, function(json) {
		if(json.status != null ){
			 alert(json.status);
		}
		
		 $('#contractor').val(json.contractorId);
		 $('#allocatedId').val(json.entryBy);
		 $('#jobNumber').val(json.projectNo);
		 

		 
      /* if (json.estimateNumbers != null && json.estimateNumbers.length > 0) {
           for (x = 0; x < json.estimateNumbers.length; x++) {
            
               $("#constructionEstimate select").append($('<option></option>').val(json.estimateNumbers[x].id).html(json.estimateNumbers[x].name));
           }
       }
       if (json.allocatedIds != null && json.allocatedIds.length > 0) {
           for (x = 0; x < json.allocatedIds.length; x++) {
            
               $("#allocatedIDs select").append($('<option></option>').val(json.allocatedIds[x].id).html(json.allocatedIds[x].name));
           }
       }*/
  	// $('#applicationRefs1').val(json.categoryCode);

  
	 });
	
	
}
</script>
<s:head />
<sx:head />
</head>
<body onLoad="buttonLoader()">
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
					<!--tr>
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
									<td><a href="<s:url action="logout"/>">Logout</a></td>
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
									<td><s:form action="loadContractorOfferLetter"
										method="POST" validate="true" theme="simple" name="estRefForm">
										<table width="100%" class="tldFromContent" border="0">
											<tr>
												<td class="listHeader" colspan="9">
												<table width="118%" border="0">
													<tr>
														<td width="33%" class="listHeader">Contractor Offer Letter</td>
														<td width="67%"><span style="background-color: lime;">
														<s:property value="lblSuccess" /> </span> <span
															style="background-color: red;"> <s:property
															value="lblError" /> </span></td>
													</tr>
												</table>
												</td>

											</tr>


											<tr>
												<td>&nbsp;</td>
												<td>&nbsp;</td>
												<td>&nbsp;</td>
											</tr>
											<tr>
												<td width="18%">Cost Center No</td>
												<td width="1%">:</td>
												<td width="81%"><s:property value="costCenter" />
												<s:hidden name="costCenter" value="%{costCenter}" />
												</td>
											</tr>
											
											<tr>
												<td>Application Number</td>
												<td>:</td>
												<td>
												<div id="applicationRefs1">
													<select name="selectedEstNumber" id="selectedEstNumber" onchange="loadConstructionRefsContarctorOfferLetter(this)"><option value="-1">--Select--</option>
													</select>
												</div>
								
												</td>
											</tr>
											
											<tr>
												<td>Construction Reference</td>
												<td>:</td>
												<td>
												
													<div id="constructionEstimate">
													<select name="selectedConstructionEstNumber" id="selectedConstructionEstNumber" onchange="loadEstimateDetailsForContarctorOfferLetter(this)"><option value="-1">--Select--</option>
													</select>
												</div>
												</td>
											</tr>
											
											
											
											<tr>
												<td>Contractor</td>
												<td>:</td>
												<td>
												<s:textfield name="contractor" id="contractor" size="50" ></s:textfield>
												<!-- <div id="ContractorList">
													<select name="contractor" id="contractor" ><option value="-1">--Select--</option>
													</select>
												</div> -->
												
													
												</td>	
											</tr>
																									
											<tr>
													<td>Allocated To(Electrical Suprintendent Entry ID)</td>
													<td>:</td>
													<td>
													<s:textfield name="allocatedId" id="allocatedId" size="50" readonly="true"></s:textfield>
													<!-- <div id="allocatedIDs">
														<select name="allocatedId" id="allocatedId" ><option value="-1">--Select--</option>
														</select>
													</div> -->
												
													</td>
											</tr>	
											<tr>
												<td>Expected Date of Completion</td>
												<td>:</td>
												<td><input type="text"  name="expectedDateofFinish" id="expectedDateofFinish" readOnly="true"/>
												</td>
											</tr>
										
											<!--  <tr>
												<td width="18%">Found Source</td>
												<td width="1%">:</td>
												<td width="81%"><s:property value="fundSource" />
												<s:hidden name="fundSource" value="%{fundSource}" />
												</td>
											</tr>-->
											<tr>
												<td width="18%">Job Number</td>
												<td width="1%">:</td>
												<td width="81%"><s:textfield name="jobNumber" id="jobNumber" size="50" ></s:textfield>
												</td>
											</tr>
											<!--  <tr>
												<td>Work Scope</td>
												<td>:</td>
												<td><s:textarea name="workScope" cols="30" rows="2"></s:textarea>
												</td>
											</tr>-->
											<tr>
												<td colspan="3"><s:submit theme="simple" type="submit"	value="Download Letter" method="GenerateContracOfferLetter">
												</s:submit> <s:submit theme="simple" type="submit"	value="Download Letter For Estimate Created By Construction Itself" method="GenerateContracOfferLetterConitSelf">
												</s:submit>
												<s:submit theme="simple" type="submit"	value="Download Letter with Header" method="GenerateContracOfferLetterWithHeader">
												</s:submit> 
												<s:submit theme="simple" type="submit"	value="Download Letter WithOut Header" method="GenerateContracOfferLetterWithOutHeader">
												</s:submit>
												
												<s:submit theme="simple" align="right" value="Exit"
													type="submit" method="Exit" id="btnClose"></s:submit></td>
											</tr>
											
											
										</table>

									</s:form></td>
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
									<td>@ Ceylon Electricity Board</td>
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
