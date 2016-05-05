<%@ include file="/WEB-INF/jsp/include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Main Menu</title>
       	<link rel="stylesheet" type="text/css" href="../../style/style.css" />
        <script type="text/javascript" src="../../script/mainMenu.js"></script>
        <script type="text/javascript" src="../../script/jquery-1.9.1.js"></script>
        <script type="text/javascript" src="../../script/jquery-ui-1.10.2.custom.js"></script>
        <link href="../../style/jquery-ui-1.10.2.custom.css" rel="stylesheet">
        
        <script>

        $(function() {
			
			$( "#accordion" ).accordion();
			$("#accordion").accordion({ header: "h3", collapsible: true, active: false });
			});
    
        //Standard Estimate to be validated
		$(document).ready(function(){
			$('#hAppEstimateTobeValidate').click(function(){
				/*
				$('#jaximage').fadeIn("slow");*/
				$('#appEstimateTobeValidate').css("display", "none");
				$.ajax({
					type:'post',
					url: 'security/web/appEstimateTobeValidate',
					//data: {udata: postdata},
					success: function(data){
						//$('#jaximage').css("display", "none");
						$('#appEstimateTobeValidate').html(data).fadeIn("fast");

					}
				});

			});
		});

		//Application ToBe Allocatted
		$(document).ready(function(){
			$('#hAppToBeAllocatedList').click(function(){
				/*
				$('#jaximage').fadeIn("slow");*/
				$('#appToBeAllocatedList').css("display", "none");
				$.ajax({
					type:'post',
					url: 'security/web/appToBeAllocatedList',
					//data: {udata: postdata},
					success: function(data){
						//$('#jaximage').css("display", "none");
						$('#appToBeAllocatedList').html(data).fadeIn("fast");

					}
				});

			});
		});

		//setToBeReccomendWECon
		$(document).ready(function(){
			$('#hSetToBeRecommendWECon').click(function(){
				/*
				$('#jaximage').fadeIn("slow");*/
				$('#setToBeRecommendWECon').css("display", "none");
				$.ajax({
					type:'post',
					url: 'security/web/setToBeRecommendWECon',
					//data: {udata: postdata},
					success: function(data){
						//$('#jaximage').css("display", "none");
						$('#setToBeRecommendWECon').html(data).fadeIn("fast");

					}
				});

			});
		});

		//Application ToBe Send For Con MNT
		$(document).ready(function(){
			$('#hStdSendForConsMainList').click(function(){
				/*
				$('#jaximage').fadeIn("slow");*/
				$('#stdSendForConsMainList').css("display", "none");
				$.ajax({
					type:'post',
					url: 'security/web/stdSendForConsMainList',
					//data: {udata: postdata},
					success: function(data){
						//$('#jaximage').css("display", "none");
						$('#stdSendForConsMainList').html(data).fadeIn("fast");

					}
				});

			});
		});

		//Standard Estimate To Be Entered
		$(document).ready(function(){
			$('#hStdTobeEnteredStdEstimates').click(function(){
				/*
				$('#jaximage').fadeIn("slow");*/
				$('#stdTobeEnteredStdEstimates').css("display", "none");
				$.ajax({
					type:'post',
					url: 'security/web/stdTobeEnteredStdEstimates',
					//data: {udata: postdata},
					success: function(data){
						//$('#jaximage').css("display", "none");
						$('#stdTobeEnteredStdEstimates').html(data).fadeIn("fast");

					}
				});

			});
		});

		//Work Estimate To Be Entered
		$(document).ready(function(){
			$('#hStdTobeEnteredStdEstimates').click(function(){
				/*
				$('#jaximage').fadeIn("slow");*/
				$('#stdTobeEnteredStdEstimates').css("display", "none");
				$.ajax({
					type:'post',
					url: 'security/web/stdTobeEnteredStdEstimates',
					//data: {udata: postdata},
					success: function(data){
						//$('#jaximage').css("display", "none");
						$('#stdTobeEnteredStdEstimates').html(data).fadeIn("fast");

					}
				});

			});
		});

		//Work Estimate To Be Entered
		$(document).ready(function(){
			$('#hWorSetTobeEnteredConEstimates').click(function(){
				/*
				$('#jaximage').fadeIn("slow");*/
				$('#worSetTobeEnteredConEstimates').css("display", "none");
				$.ajax({
					type:'post',
					url: 'security/web/worSetTobeEnteredConEstimates',
					//data: {udata: postdata},
					success: function(data){
						//$('#jaximage').css("display", "none");
						$('#worSetTobeEnteredConEstimates').html(data).fadeIn("fast");

					}
				});

			});
		});

		//Std Issue PIV II
		$(document).ready(function(){
			$('#hAppSetPIVIIList').click(function(){
				/*
				$('#jaximage').fadeIn("slow");*/
				$('#appSetPIVIIList').css("display", "none");
				$.ajax({
					type:'post',
					url: 'security/web/appSetPIVIIList',
					//data: {udata: postdata},
					success: function(data){
						//$('#jaximage').css("display", "none");
						$('#appSetPIVIIList').html(data).fadeIn("fast");

					}
				});

			});
		});

		//Std Approved Estimate
		$(document).ready(function(){
			$('#hStdApprovedEstimates').click(function(){
				/*
				$('#jaximage').fadeIn("slow");*/
				$('#stdApprovedEstimates').css("display", "none");
				$.ajax({
					type:'post',
					url: 'security/web/stdApprovedEstimates',
					//data: {udata: postdata},
					success: function(data){
						//$('#jaximage').css("display", "none");
						$('#stdApprovedEstimates').html(data).fadeIn("fast");

					}
				});

			});
		});

		//Get Active Appointments
		$(document).ready(function(){
			$('#hStdSetAtiveAppoinments').click(function(){
				/*
				$('#jaximage').fadeIn("slow");*/
				$('#stdSetAtiveAppoinments').css("display", "none");
				$.ajax({
					type:'post',
					url: 'security/web/stdSetAtiveAppoinments',
					//data: {udata: postdata},
					success: function(data){
						//$('#jaximage').css("display", "none");
						$('#stdSetAtiveAppoinments').html(data).fadeIn("fast");

					}
				});

			});
		});
		
		//rejectedWorkEstimate
		$(document).ready(function(){
			$('#hRejectedWorkEstimate').click(function(){
				/*
				$('#jaximage').fadeIn("slow");*/
				$('#rejectedWorkEstimate').css("display", "none");
				$.ajax({
					type:'post',
					url: 'security/web/rejectedWorkEstimate',
					//data: {udata: postdata},
					success: function(data){
						//$('#jaximage').css("display", "none");
						$('#rejectedWorkEstimate').html(data).fadeIn("fast");

					}
				});

			});
		});


		//toBeAllocatedConsMainList
		$(document).ready(function(){
			$('#hToBeAllocatedConsMainList').click(function(){
				/*
				$('#jaximage').fadeIn("slow");*/
				$('#toBeAllocatedConsMainList').css("display", "none");
				$.ajax({
					type:'post',
					url: 'security/web/toBeAllocatedConsMainList',
					//data: {udata: postdata},
					success: function(data){
						//$('#jaximage').css("display", "none");
						$('#toBeAllocatedConsMainList').html(data).fadeIn("fast");

					}
				});

			});
		});

		//toBeAllocatedPLList
		$(document).ready(function(){
			$('#hToBeAllocatedPLList').click(function(){
				//alert("hiiii");
				/*
				$('#jaximage').fadeIn("slow");*/
				$('#toBeAllocatedPLList').css("display", "none");
				$.ajax({
					type:'post',
					url: 'security/web/toBeAllocatedPLList',
					//data: {udata: postdata},
					success: function(data){
						//$('#jaximage').css("display", "none");
						$('#toBeAllocatedPLList').html(data).fadeIn("fast");

					}
				});

			});
		});

		//ActiveAppointmentsCon
		$(document).ready(function(){
			$('#hActiveAppointmentsCon').click(function(){
				/*
				$('#jaximage').fadeIn("slow");*/
				$('#activeAppointmentsCon').css("display", "none");
				$.ajax({
					type:'post',
					url: 'security/web/activeAppointmentsCon',
					//data: {udata: postdata},
					success: function(data){
						//$('#jaximage').css("display", "none");
						$('#activeAppointmentsCon').html(data).fadeIn("fast");

					}
				});

			});
		});

		//setToBeValidateWECon
		$(document).ready(function(){
			$('#hSetToBeValidateWECon').click(function(){
				/*
				$('#jaximage').fadeIn("slow");*/
				$('#setToBeValidateWECon').css("display", "none");
				$.ajax({
					type:'post',
					url: 'security/web/setToBeValidateWECon',
					//data: {udata: postdata},
					success: function(data){
						//$('#jaximage').css("display", "none");
						$('#setToBeValidateWECon').html(data).fadeIn("fast");

					}
				});

			});
		});

		//setToBeValidateWEConWPSII
		$(document).ready(function(){
			$('#hSetToBeValidateWEConWPSII').click(function(){
				/*
				$('#jaximage').fadeIn("slow");*/
				$('#setToBeValidateWEConWPSII').css("display", "none");
				$.ajax({
					type:'post',
					url: 'security/web/setToBeValidateWEConWPSII',
					//data: {udata: postdata},
					success: function(data){
						//$('#jaximage').css("display", "none");
						$('#setToBeValidateWEConWPSII').html(data).fadeIn("fast");

					}
				});

			});
		});

		//setToBeAllocateWEConMntItself
		$(document).ready(function(){
			$('#hToBeAllocatedConsMainListItself').click(function(){
				/*
				$('#jaximage').fadeIn("slow");*/
				$('#toBeAllocatedConsMainListItself').css("display", "none");
				$.ajax({
					type:'post',
					url: 'security/web/toBeAllocatedConsMainListItself',
					//data: {udata: postdata},
					success: function(data){
						//$('#jaximage').css("display", "none");
						$('#toBeAllocatedConsMainListItself').html(data).fadeIn("fast");

					}
				});

			});
		});
		

		
		</script>
    </head>
    <body >
        <table id="tblOuter"   width="100%" border="0" class="tblOuter">
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

                                    <td>
                                        <table width="100*" class="tblWelcome" border="0" cellpadding="0" cellspacing="0">
                                            <thead>
                                                <tr>
                                                    <th width="60%"></th><!-- menu -->
                                                    <th width="9%"></th>
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
                                                                   
                                                                    <s:if test="%{loggedInUserLevel=='ADMIN' || costCenterNo=='501.20' ||costCenterNo=='430.20' || costCenterNo=='430.25' || costCenterNo=='430.30' || costCenterNo=='430.35'}">
                                                                    <li>
                                                                        <a class="item-primary" href="#" onclick="return false"> Masters </a>
																		   <ul>
																			   <s:if test="%{loggedInUserLevel=='ADMIN' || loggedInUserLevel=='HELPDESK' || loggedInUserLevel=='CE' || loggedInUserLevel=='EE'}">
																					<li>
																						<a href="Masters" onclick="return false">Add Pegging Data</a>
																						<ul style="padding-top :0px; ">
																							<s:if test="%{loggedInUserLevel=='ADMIN'  || loggedInUserLevel=='HELPDESK' || loggedInUserLevel=='EE' || loggedInUserLevel=='CE'}">
																							<li>
																								 <a href="<s:url namespace="/masters/web" action="masterLoadPeggingPage"/>">Add Pegging Data</a>
																							</li>
																							</s:if>
																						   
																							
																						</ul>
																					</li>
																				</s:if>
																				 <li>
                                                                                <a href="Application" onclick="return false">New Contractor</a>
                                                                                <ul style="padding-top :0px; ">
                                                                                    <s:if test="%{loggedInUserLevel=='ADMIN'  || loggedInUserLevel=='TECH'  || loggedInUserLevel=='HELPDESK'}">
                                                                                    <li>
                                                                               			 <a href="<s:url namespace="/masters/web" action="AddNewContractor"/>">Add</a>
                                                                            		</li>
                                                                            		<li>
                                                                               			 <a href="<s:url namespace="/masters/web" action="UpdateContractor"/>">Update</a>
                                                                            		</li>
                                                                            		</s:if>
		                                                                            <li>
                                                                                		<a href="<s:url namespace="/masters/web" action="ContractorList"/>">View<font class="newLinks" >   New</font></a>
                                                                           			</li>
		                                                                            
		                                                                        </ul>
                                                                            </li>
                                                                          
																		   </ul>
                                                                        	
                                                                        	
                                                                         </s:if>
                                                                        <s:if test="%{(branchType=='COMMERCIAL' || branchType=='EM' || branchType=='DM'  || branchType=='CONSTRUCTION'  || branchType=='PH' || branchType=='AC' ||branchType=='AU' || loggedInUserLevel=='ADMIN') && loggedInUserLevel!='ACCNT'}">
                                                                      
                                                                        <li>
                                                                        <a class="item-primary" href="#" onclick="return false">Application</a>
                                                                        <ul style="width:150px;">
                                                                        	<li>
                                                                                <a href="Applicant" onclick="return false">Customer</a>
                                                                                <ul style="padding-top :0px; ">
                                                                                    <li>
		                                                                                <a href="<s:url namespace="/applicationBS/web" action="newCustomerBS"/>">New Customer</a>
		                                                                            </li>
		                                                                            <li>
		                                                                                <a href="<s:url namespace="/applicationBS/web" action="modifyCustomerBS"/>">Modify Customer</a>
		                                                                            </li>
		                                                                            <li>
		                                                                                <a href="<s:url namespace="/applicationBS/web" action="generateIdNoBS"/>">Generate Id No</a>
		                                                                            </li>
		                                                                            <li>
		                                                                                <a href="<s:url namespace="/applicationBS/web" action="changeIdNoDirectBS"/>">Change Id No<font class="newLinks" >   New</font></a>
		                                                                            </li>
		                                                                            
		                                                                        </ul><!-- 3rd -->
                                                                            </li>
                                                                            <s:if test="%{ smcType=='PF' ||smcType=='DB' || smcType=='PH' || smcType=='AC' ||smcType=='RE' || smcType=='AC' ||smcType=='PL' ||smcType=='R8' || smcType=='RI' || smcType=='EM' || smcType=='DM' || smcType=='CN' || smcType=='PH'  || smcType=='DE'  || smcType=='AU' || smcType=='CP' }">
                                                                            <li>
                                                                                <a href="Application" onclick="return false">Application</a>
                                                                                <ul style="padding-top :0px; ">
                                                                                     <li>
		                                                                                <a href="<s:url namespace="/applicationBS/web" action="newApplicationRE"/>">New Application</a>
		                                                                            </li>
		                                                                            <li>
		                                                                                <a href="<s:url namespace="/applicationBS/web" action="modifyApplicationREBS"/>" >Modify Application</a>
		                                                                            </li>
		                                                                            <li>
		                                                                                <a href="<s:url namespace="/applicationBS/web" action="viewApplicationREBS"/>">View Application</a>
		                                                                            </li>
		                                                                            <li>
		                                                                                <a href="<s:url namespace="/applicationBS/web" action="confirmApplicationREBS"/>">Generate App No</a>
		                                                                            </li>
		                                                                            <li>
		                                                                                <a href="<s:url namespace="/applicationBS/web" action="printApplicationREBS"/>">Print Application<font class="newLinks" >   New</font></a>
		                                    
		                                     										<li>
		                                                                                <a href="<s:url namespace="/applicationBS/web" action="changeServiceAddBS"/>">Change Service Add<font class="newLinks" >   New</font></a>
		                                                                            </li>                                        </li>
		                                                                           
		                                                                           
		                                                                        </ul><!-- 3rd -->
                                                                            </li>
                                                                            </s:if>
                                                                            <s:if test="%{smcType=='LN' || smcType=='BS' || smcType=='RD' || smcType=='RD'}">
                                                                            <li>
                                                                                <a href="Application" onclick="return false">Application</a>
                                                                                <ul style="padding-top :0px; ">
                                                                                     <li>
		                                                                                <a href="<s:url namespace="/applicationBS/web" action="newApplicationBS"/>">New Application</a>
		                                                                            </li>
		                                                                           
		                                                                            <li>
		                                                                                <a href="<s:url namespace="/applicationBS/web" action="modifyApplicationBS"/>" >Modify Application</a>
		                                                                            </li>
		                                                                            <li>
		                                                                                <a href="<s:url namespace="/applicationBS/web" action="viewApplicationBS"/>">View Application</a>
		                                                                            </li>
		                                                                            <li>
		                                                                                <a href="<s:url namespace="/applicationBS/web" action="confirmApplicationBS"/>">Generate App No</a>
		                                                                            </li>
		                                                                            <li>
		                                                                                <a href="<s:url namespace="/applicationBS/web" action="printApplicationBS"/>">Print Application<font class="newLinks" >   New</font></a>
		                                                                            </li>
		                                                                             <li>
		                                                                                <a href="<s:url namespace="/applicationBS/web" action="changeServiceAddBS"/>">Change Service Add<font class="newLinks" >   New</font></a>
		                                                                            </li>
		                                                                            
		                                                                           
		                                                                           
		                                                                        </ul><!-- 3rd -->
                                                                            </li>
                                                                            </s:if>
                                                                           
                                                                            <s:if test="%{smcType=='BS' || smcType=='RD' || smcType=='RD'}">
                                                                                           <li>
                                                                                <a href="PIV" onclick="return false">PIV</a>
                                                                                <ul style="padding-top :0px; ">
                                                                                     <li>
		                                                                                <a href="<s:url namespace="/applicationBS/web" action="newPivBS"/>">Issue New PIV</a>
		                                                                            </li>
                                                                                    <li>
		                                                                                <a href="<s:url namespace="/applicationBS/web" action="confirmPivBS"/>">Confirm PIV</a>
		                                                                            </li>
		                                                                            <li>
		                                                                                <a href="<s:url namespace="/applicationBS/web" action="viewPivBS"/>">View PIV</a>
		                                                                            </li>
		                                                                            <li>
		                                                                                <a href="<s:url namespace="/applicationBS/web" action="modifyPivBS"/>">Modify PIV<font class="newLinks" >   New</font></a>
		                                                                            </li>
		                                                                            <li>
		                                                                                <a href="<s:url namespace="/applicationBS/web" action="printPivBS"/>">Print PIV</a>
		                                                                            </li>
		                                                                            
		                                                                            <li>
		                                                                                <a href="<s:url namespace="/applicationBS/web" action="changePivIdNoAppDirectBS"/>">change Piv 1 IdNo<font class="newLinks" >   New</font></a>
		                                                                            </li>
		                                                                           
		                                                                        </ul><!-- 3rd -->
                                                                            </li>
                                                                            </s:if>
                                                                            
                                                                             <s:if test="%{smcType=='PF' || smcType=='DB' || smcType=='RE' || smcType=='AC' }">
                                                                                           <li>
                                                                                <a href="PIV" onclick="return false">PIV</a>
                                                                                <ul style="padding-top :0px; ">
                                                                                     <li>
		                                                                                <a href="<s:url namespace="/applicationBS/web" action="newPivBS"/>">Issue New PIV</a>
		                                                                            </li>
                                                                                    <li>
		                                                                                <a href="<s:url namespace="/applicationBS/web" action="confirmPivBS"/>">Confirm PIV</a>
		                                                                            </li>
		                                                                            <li>
		                                                                                <a href="<s:url namespace="/applicationBS/web" action="viewPivBS"/>">View PIV</a>
		                                                                            </li>
		                                                                            <li>
		                                                                                <a href="<s:url namespace="/applicationBS/web" action="modifyPivBS"/>">Modify PIV<font class="newLinks" >   New</font></a>
		                                                                            </li>
		                                                                            <li>
		                                                                                <a href="<s:url namespace="/applicationBS/web" action="printPivBS"/>">Print PIV</a>
		                                                                            </li>
		                                                                           
		                                                                            <li>
		                                                                                <a href="<s:url namespace="/applicationBS/web" action="changePivIdNoAppDirectBS"/>">change Piv 1 IdNo<font class="newLinks" >   New</font></a>
		                                                                            </li>
		                                                                           
		                                                                        </ul><!-- 3rd -->
                                                                            </li>
                                                                            </s:if>
                                                                            
                                                                             <s:if test="%{smcType=='LN'}">
                                                                                           <li>
                                                                                <a href="PIV" onclick="return false">PIV</a>
                                                                                <ul style="padding-top :0px; ">
                                                                                     <li>
		                                                                                <a href="<s:url namespace="/applicationBS/web" action="newPivBS"/>">Issue New PIV</a>
		                                                                            </li>
                                                                                    <li>
		                                                                                <a href="<s:url namespace="/applicationBS/web" action="confirmPivBS"/>">Confirm PIV</a>
		                                                                            </li>
		                                                                            <li>
		                                                                                <a href="<s:url namespace="/applicationBS/web" action="viewPivBS"/>">View PIV</a>
		                                                                            </li>
		                                                                            <li>
		                                                                                <a href="<s:url namespace="/applicationBS/web" action="modifyPivBS"/>">Modify PIV<font class="newLinks" >   New</font></a>
		                                                                            </li>
		                                                                            <li>
		                                                                                <a href="<s:url namespace="/applicationBS/web" action="printPivBS"/>">Print PIV</a>
		                                                                            </li>
		                                                                           
		                                                                            <li>
		                                                                                <a href="<s:url namespace="/applicationBS/web" action="changePivIdNoAppDirectBS"/>">change Piv 1 IdNo<font class="newLinks" >   New</font></a>
		                                                                            </li>
		                                                                           
		                                                                        </ul><!-- 3rd -->
                                                                            </li>
                                                                           
                                                                              </s:if>
                                                                             
	                                                                             <li>
		                                                                             <a href="<s:url namespace="/applicationBS/web" action="loadAppSearchPageBS"/>">Search Application<font class="newLinks" >   New</font></a>
		                                                                      	</li>
		                                                                            
		                                                                      	<li>
		                                                                             <a href="<s:url namespace="/applicationBS/web" action="loadAppStatusPageBS"/>">Application Status<font class="newLinks" >   New</font></a>
		                                                                     	</li>
		                                                                     	
                                                                        </ul><!-- 2nd -->
                                                                    </li>
                                                                   
                                                                   </s:if>
                                                                     <s:if test="%{branchType=='CONSTRUCTION' || branchType=='DM' || branchType=='EM' || branchType=='MU' || branchType=='PH' || branchType=='AC' ||branchType=='AU' || branchType=='GI' 
                                                                     					|| loggedInUserLevel=='ADMIN' }">
                                                                    <li>
                                                                        <a class="item-primary" href="#" onclick="return false">Search </a>
                                                                        
                                                                                <ul style="padding-top :0px; ">
                                                                                  
                                                                                    <li>
		                                                                             	<a href="<s:url namespace="/estimate/web" action="pegSchedule!workEstiSearch"/>">Search Estimate<font class="newLinks" >   New</font></a>
		                                                                      		</li>
		                                                                            
		                                                                      		<li>
				                                                                         <a href="<s:url namespace="/estimate/web" action="jobSearch"/>">Search Job<font class="newLinks" >   New</font></a>
				                                                                    </li>
                                                                            	
		                                                                        </ul>
                                                                       </li>
                                                                     
                                                                    </s:if>
                                                                    <s:if test="%{loggedInUserLevel!='BILL' && loggedInUserLevel!='ACCNT' || loggedInUserLevel=='ADMIN'}">
	                                                                    <li>                                                                        
	                                                                        <a class="item-primary">Schedule</a>
	                                                                        <ul style="width:150px;">
	                                                                            <!-- <li>
	                                                                                <a href="Applicant" onclick="return false">Appointmnet</a>
	                                                                                <ul style="padding-top :0px; ">
	                                                                                    <li>
	                                                                            			<a  href="<s:url namespace="/calendar/web" action="diaryEntry"/>">New Appointmnet</a>
	                                                                            		</li>
			                                                                            <li>
	                                                                            			<a  href="<s:url namespace="/calendar/web" action="schedule"/>">Edit/Change Status</a>
	                                                                            		</li>
	                                                                            		<li>
	                                                                            			<a  href="<s:url namespace="/calendar/web" action="createVisitedAppointments"/>">Create Appointments<font class="newLinks" >   New</font></a>
	                                                                            		</li>
	                                                                            		<li>
	                                                                            			<a  href="<s:url namespace="/calendar/web" action="activeFailed"/>">Activate Failed</a>
	                                                                            		</li>
	                                                                            		
			                                                                        </ul>
		                                                                            </li> -->
		                                                                           
		                                                                          		<s:if test="%{(branchType=='COMMERCIAL' || loggedInUserLevel=='ADMIN' || loggedInUserLevel=='TECH') && (loggedInUserLevel=='DEO' || loggedInUserLevel=='ES' || loggedInUserLevel=='EE' || loggedInUserLevel=='PE')}">
						                                                                         <s:if test="%{smcType=='BS' || smcType=='RD' || smcType=='RD' || smcType=='LN' ||smcType =='PF' || smcType =='DB' || smcType=='RE' || smcType=='AC' || smcType=='PH' || smcType=='AC' || smcType=='PL' || smcType=='R8' || smcType=='RI' || smcType=='CP'}">
						                                                                             <li>
					                                                                                <a href="ApplicantBS" onclick="return false">Allocate</a>
					                                                                                <ul style="padding-top :0px; ">
					                                                                                    <li>
					                                                                            			<a  href="<s:url namespace="/calendarBS/web" action="diaryEntryBS"/>">New Allocation</a>
					                                                                            		</li>
							                                                                            <li>
					                                                                            			<a  href="<s:url namespace="/calendarBS/web" action="scheduleBS"/>">Edit/Change Allocation</a>
					                                                                            		</li>
					                                                                            		<!-- <li>
					                                                                            			<a  href="<s:url namespace="/calendarBS/web" action="createVisitedAppointmentsBS"/>">Create Allocation<font class="newLinks" >   New</font></a>
					                                                                            		</li>
					                                                                            		<li>
					                                                                            			<a  href="<s:url namespace="/calendarBS/web" action="activeFailedBS"/>">Activate Failed</a>
					                                                                            		</li> -->
					                                                                            		
							                                                                        </ul><!-- 3rd -->
						                                                                            </li>
																									
						                                                                            </s:if>
						                                                                             
																							</s:if>
		                                                                           
				                                                                            <s:if test="%{(branchType=='COMMERCIAL'  || loggedInUserLevel=='ADMIN' || loggedInUserLevel=='TECH') }">
					                                                                            <s:if test="%{loggedInUserLevel=='EE' || loggedInUserLevel=='DEO' || loggedInUserLevel=='ES' || loggedInUserLevel=='PE'}">
						                                                                           	<li>
						                                                                                <a href="sendForCM" onclick="return false">Send to Construction Maintenance</a>
						                                                                                <ul style="padding-top :0px; ">
						                                                                                   	
						                                                                                   
						                                                                                    <li>
						                                                                            			<a href="<s:url namespace="/estimate/web" action="estimateBS!sendForCM"/>">Send Constru & Maint</a>
						                                                                            		</li> 
						                                                                            		    <li>
						                                                                            			<a href="<s:url namespace="/estimate/web" action="estimateBS!sendBatchVise"/>">Send Constru & Maint Batch Vise </a>
						                                                                            		</li> 
						                                                                            		             
						                                                                            		<li>
						                                                                            			<!-- <a href="<s:url namespace="/estimate/web" action="estimateCancellation"/>">Estimate Cancellation <font class="newLinks" >   New</font></a>-->
						                                                                            		</li>
						                                                                            		
						                                                                            		
						                                                                            		
						                                                                            	</ul><!-- 3rd -->
						                                                                            </li>
				                                                                            	</s:if>
				                                                                            </s:if>
				                                                                            <s:if test="%{branchType=='CONSTRUCTION' || branchType=='DM'  || branchType=='EM' || branchType=='MU' || branchType=='PH' || branchType=='AC' || branchType=='AU' ||  loggedInUserLevel=='ADMIN'}"> 
																								<s:if test="%{loggedInUserLevel=='DEO' || loggedInUserLevel=='ES' || loggedInUserLevel=='EE' || loggedInUserLevel=='CE' || loggedInUserLevel=='TECH'}">
						                                                                             <li>
					                                                                                <a href="ApplicantBS" onclick="return false">Allocate(CN & DM)</a>
					                                                                                <ul style="padding-top :0px; ">
					                                                                                    <li>
					                                                                            			<a  href="<s:url namespace="/calendarBS/web" action="diaryEntryCMBS"/>">New Allocation</a>
					                                                                            		</li>
					                                                                            		 <li>
					                                                                            			<a  href="<s:url namespace="/calendarBS/web" action="diaryEntryCMRE"/>">Scheme Allocation</a>
					                                                                            		</li>
							                                                                            <li>
					                                                                            			<a  href="<s:url namespace="/calendarBS/web" action="scheduleCMBS"/>">Edit/Change Allocation</a>
					                                                                            		</li>
					                                                                            		<li>
					                                                                            			<a  href="<s:url namespace="/calendarBS/web" action="createVisitedAppointmentsBS"/>">Create Allocation<font class="newLinks" >   New</font></a>
					                                                                            		</li>
					                                                                            		<li>
					                                                                            			<a  href="<s:url namespace="/calendarBS/web" action="activeFailedBS"/>">Activate Failed</a>
					                                                                            		</li>
					                                                                            		
							                                                                        </ul><!-- 3rd -->
						                                                                            </li>
																									
					                                                                            </s:if>
					                                                                           
			                                                                            	</s:if>
		                                                                           
	                                                                            
																
	                                                                                                                                      
	                                                                        </ul>         
	                                                                    </li>
                                                                    </s:if>
                                                                    
                                                                  
                                                                  
                                                                    <li>
                                                                        <a class="item-primary" href="#">Estimation</a>
                                                                        <ul style="width:150px;">
                                                                        	
                                                                            <s:if test="%{branchType=='COMMERCIAL' || loggedInUserLevel=='DGM' || loggedInUserLevel=='ADMIN' || loggedInUserLevel=='TECH'}"> 
	                                                                            <s:if test="%{(smcType=='BS' || smcType=='RD' || smcType=='LN' ||smcType =='PF' || smcType =='DB' || smcType=='PH' || smcType=='AC' ||smcType=='RE' || smcType=='AC' || smcType=='R8' || smcType=='RI' || smcType=='PL' || smcType=='CP')}">
		                                                                            	<li>                                                                           		 
		                                                                            		<a  href="<s:url namespace="/estimate/web" action="serviceEstimateBS"/>">Service Estimate</a>
		                                                                            		<ul style="padding-top :0px; ">
		                                                                            		<s:if test="%{loggedInUserLevel=='DEO' || loggedInUserLevel=='ES' || loggedInUserLevel=='EE' || loggedInUserLevel=='PE' || loggedInUserLevel=='ADMIN' || loggedInUserLevel=='TECH'}">
			                                                                                    <li>
			                                                                            			<a  href="<s:url namespace="/estimate/web" action="estimateBS!init"/>">New Entry</a>
			                                                                            		</li>
			                                                                            		<li>
			                                                                            			<a  href="<s:url namespace="/estimate/web" action="estimateBS!initUpdate"/>">Modify Entry  <font class="newLinks" >   Upload Sketch</font></a>
			                                                                            		</li>
			                                                                            		<li>
			                                                                            			<a  href="<s:url namespace="/estimate/web" action="estimateBS!View"/>">View</a>
			                                                                            		</li>
			                                                                            		<li>
			                                                                            			<a  href="<s:url namespace="/estimate/web" action="estimateBS!sendForValidation"/>">Send For Validation</a>
			                                                                            		</li>
			                                                                            		</s:if>
			                                                                            		<s:if test="%{loggedInUserLevel=='DGM' || loggedInUserLevel=='PCE' || loggedInUserLevel=='EE' || loggedInUserLevel=='PE' ||loggedInUserLevel=='CE' || loggedInUserLevel=='ADMIN' || loggedInUserLevel=='TECH'}">
			                                                                                
			                                                                            		<li>
			                                                                            			<a  href="<s:url namespace="/estimate/web" action="estimateBS!Cancel"/>">Estimate Cancellation <font class="newLinks" >   New</font></a>
			                                                                            		</li>
			                                                                            		<li>
			                                                                            			<a  href="<s:url namespace="/estimate/web" action="estimateBS!ChangePS"/>">Change Post DeptID & Status <font class="newLinks" >   New</font></a>
			                                                                            		</li>
			                                                                            		</s:if>
			                                                                            	
			                                                                            	
			                                                                            	<s:if test="%{loggedInUserLevel=='EE' || loggedInUserLevel=='PE' || loggedInUserLevel=='CE' || loggedInUserLevel=='DGM' || loggedInUserLevel=='ADMIN'}">
				                                                                            		<li>
				                                                                            			<a  href="<s:url namespace="/estimate/web" action="estimateBS!standardEstiValidate"/>">Standard Estimate Validate</a>
				                                                                            		</li>
			                                                                            	
				                                                                            		<li>
				                                                                            			<a  href="<s:url namespace="/estimate/web" action="estimateBS!sendForApprovalCancel"/>">Approval Cancel</a>
				                                                                            			
				                                                                            		</li>
				                                                                            		<li>
			                                                                            			<a  href="<s:url namespace="/estimate/web" action="estimateBS!sendForValidation"/>">Send For Validation</a>
			                                                                            		</li>
			                                                                            	</s:if>
			                                                                            
		                                                                        		</ul>
	                                                                            	</li>
	                                                                        	</s:if>
	                                                                        </s:if>
	                                                                         <s:if test="%{(branchType=='PL'  || branchType=='CONSTRUCTION'  || branchType=='DM' || branchType=='EM' || branchType=='MU' || branchType=='AU' || branchType=='PH' || branchType=='AC' ||branchType=='GI') && (loggedInUserLevel=='ADMIN' || loggedInUserLevel=='TECH' || loggedInUserLevel=='DEO' || loggedInUserLevel=='ES' || loggedInUserLevel=='EE' || loggedInUserLevel=='CE' || loggedInUserLevel=='DGM' ) }">  
																							                                                                            <li>
	                                                                            	<a href="Appointmnet" onclick="return false">Pegging Schedule</a>
	                                                                            	<ul>
	                                                                            	 	 <s:if test="%{loggedInUserLevel=='DEO' || loggedInUserLevel=='ADMIN'}">
										 
		                                                                            		<li>
		                                                                            			<a  href="<s:url namespace="/estimate/web" action="pegSchedule!initPegDEO"/>">Entry I</a>
		                                                                            		</li>
																						</s:if>
																						<s:if test="%{loggedInUserLevel=='DEO' || loggedInUserLevel=='ES' || loggedInUserLevel=='EE' || loggedInUserLevel=='CE' || loggedInUserLevel=='ADMIN'}">	 
		                                                                            		<li>
		                                                                            			<a  href="<s:url namespace="/estimate/web" action="pegSchedule!init"/>">Entry II </a>
		                                                                            		</li>
																						    <li>
		                                                                            			<!-- <a  href="<s:url namespace="/estimate/web" action="pegScheduleNew!init"/>">Entry II <font class="newLinks" >   New</font> </a>-->
		                                                                            		</li>
																							
																							<li>
																								<a  href="<s:url namespace="/estimate/web" action="pegSchedule!initWithOutCR"/>">Entry without Comm-Ref</a>
																							</li>
																							
																							<li>
																								<a  href="<s:url namespace="/estimate/web" action="pegSchedule!initUpdate"/>">Modify </a>
																							</li>
																							
																							<li>
																								<a  href="<s:url namespace="/estimate/web" action="pegSchedule!initUpdateRebate"/>">Modify <font class="newLinks" >   New(Rebate)/Upload Sketch</font></a>
																							</li>
																							
																							
																							<li>
																								<a  href="<s:url namespace="/estimate/web" action="pegSchedule!initItembyitemDelete"/>">Delete </a>
																							</li>
																							<li>
																								<a  href="<s:url namespace="/estimate/web" action="pegSchedule!updateDetailEstimationItembyitemView"/>">View <font class="newLinks" > (View Sketch)</font> </a>
																							</li>
																							<li>
																								<a  href="<s:url namespace="/estimate/web" action="pegSchedule!updateDetailEstimationItembyitemPrint"/>">Print <font class="newLinks" > (Print Sketch)</font></a>
																							</li>
																							<li>
																								<a  href="<s:url namespace="/estimate/web" action="pegSchedule!sendForWEValidation"/>">Send For Validation</a>
																							</li>
																							<li>
																								<a  href="<s:url namespace="/estimate/web" action="pegSchedule!sendForWEPLCOM"/>">Send For Recommendation</a>
																							</li>
																							<li>
																							<a href="<s:url namespace="/estimate/web" action="change!CodeNo"/>">Change Code No <font class="newLinks" >   New</font></a>
																							</li>
																						</s:if>
			                                                                            <s:if test="%{loggedInUserLevel=='PCE' ||loggedInUserLevel=='CE' || loggedInUserLevel=='ES' || loggedInUserLevel=='EE' || loggedInUserLevel=='DGM' || loggedInUserLevel=='ADMIN'}">
																							<li>
																								<a  href="<s:url namespace="/estimate/web" action="itemByItem!workEstiValidateMainMenu"/>">PegSch Estimate Validate </a>
																							</li>
																																											 
																							<li>
																								<a  href="<s:url namespace="/estimate/web" action="estimateBS!sendForApprovalCancelConMain"/>">Cancel Approval</a></li>
																								<li>
																								<a  href="<s:url namespace="/estimate/web" action="pegSchedule!workEstiCancel"/>">Cancel Estimate <font class="newLinks" >   New</font></a>
																							
																							</li>
																						</s:if>
																						 <s:if test="%{loggedInUserLevel=='ADMIN'}">
																						<li>
																								<a  href="<s:url namespace="/estimate/web" action="update!initPriceUpdate"/>">Modify for New Price</a>
																							</li>
																						</s:if>
	                                                                            	</ul>
	                                                                            	
	                                                                            	
	                                                                            </li>
	                                                                            <li>
	                                                                            	<a href="Appointmnet" onclick="return false">Work Estimate Item By Item</a>
	                                                                            	<ul>
	                                                                            		
	                                                                            		 <s:if test="%{(costCenterNo=='520.20' || costCenterNo=='520.30' 
																						 || costCenterNo=='521.20'  || costCenterNo=='521.40'  
																						 || costCenterNo=='521.10' || costCenterNo=='514.20'  || costCenterNo=='514.80' || costCenterNo=='514.00' || costCenterNo=='514.90'
																						|| costCenterNo=='521.10' || costCenterNo=='521.20' 
																						|| costCenterNo=='975.20'  || costCenterNo=='975.30'
																						|| costCenterNo=='521.30' || costCenterNo=='521.40' || costCenterNo=='521.50' 
																						|| costCenterNo=='522.10' || costCenterNo=='522.20' 
																						|| costCenterNo=='522.40' || costCenterNo=='522.50' || costCenterNo=='522.60' 
																						|| costCenterNo=='523.10' || costCenterNo=='523.40' 
																						|| costCenterNo=='523.60' || costCenterNo=='523.70' 
																						|| costCenterNo=='514.10' || costCenterNo=='514.00' || costCenterNo=='514.30'
																						|| costCenterNo=='514.40' || costCenterNo=='514.50' || costCenterNo=='514.80' || costCenterNo=='514.90'
																						|| costCenterNo=='524.10' || costCenterNo=='524.20' 
																						|| costCenterNo=='524.30' || costCenterNo=='524.40' || costCenterNo=='524.50' || costCenterNo=='420.90'
																						|| costCenterNo=='525.10' || costCenterNo=='525.20' || costCenterNo=='525.30' 
																						|| costCenterNo=='526.10' || costCenterNo=='526.20' || costCenterNo=='526.30' || costCenterNo=='526.40' || costCenterNo=='554.00' ||costCenterNo=='527.10'  || costCenterNo=='527.20' || costCenterNo=='527.30' || costCenterNo=='527.40' ||loggedInUserLevel=='TECH') }">
		                                                                            		<li>
		                                                                            			<a  href="<s:url namespace="/estimate/web" action="pegSchedule!initForFFJobs"/>">Entry First Fifty</a>
		                                                                            		</li>
	                                                                            		 </s:if>
	                                                                            		<!--  <li>
		                                                                            			<a  href="<s:url namespace="/estimate/web" action="pegSchedule!initForFFJobs"/>">Entry First Fifty</a>
		                                                                            		</li>-->
																						  <s:if test="%{loggedInUserLevel=='TECH' || loggedInUserLevel=='DEO' || loggedInUserLevel=='ES' || loggedInUserLevel=='EE' || loggedInUserLevel=='CE' || loggedInUserLevel=='ADMIN'}">
																							<li>
																								<a  href="<s:url namespace="/estimate/web" action="pegSchedule!initItembyitem"/>">Entry </a>
																							</li>
																							<li>
																								<a  href="<s:url namespace="/estimate/web" action="pegSchedule!initItembyitemWithOutCR"/>">Entry without Comm Ref</a>
																							</li>
																							<li>
																								<a  href="<s:url namespace="/estimate/web" action="pegSchedule!initUpdateItembyitem"/>">Modify </a>
																							</li>
																							<li>
																								<a  href="<s:url namespace="/estimate/web" action="pegSchedule!initUpdateItembyitemRebate"/>">Modify <font class="newLinks" >   New(Rebate)/Upload Sketch</font> </a>
																							</li>
																							
																							<li>
																								<a  href="<s:url namespace="/estimate/web" action="pegSchedule!initItembyitemDelete"/>">Delete </a>
																							</li>
																							<li>
																								<a  href="<s:url namespace="/estimate/web" action="pegSchedule!updateDetailEstimationItembyitemView"/>">View <font class="newLinks" >(View Sketch)</font></a>
																							</li>
																							<li>
																								<a  href="<s:url namespace="/estimate/web" action="pegSchedule!updateDetailEstimationItembyitemPrint"/>">Print <font class="newLinks" >(Print Sketch)</font></a>
																							</li>
																							<li>
																								<a  href="<s:url namespace="/estimate/web" action="itemByItem!sendForWEValidation"/>">Send For Validation</a>
																							</li>
																						    </s:if>
			                                                                           
																					     <s:if test="%{loggedInUserLevel=='CE' || loggedInUserLevel=='DGM' || loggedInUserLevel=='EE' || loggedInUserLevel=='ES' || loggedInUserLevel=='ADMIN'}">
				                                                                            <li>
				                                                                            	<a  href="<s:url namespace="/estimate/web" action="itemByItem!workEstiValidateMainMenu"/>">Work Estimate Validate</a>
				                                                                            </li>
			                                                                          
		                                                                            		<li>
		                                                                            			<a  href="<s:url namespace="/estimate/web" action="estimateBS!sendForApprovalCancelConMain"/>">Cancel Approval</a>
		                                                                            		</li>
			                                                                            </s:if>
																						
	                                                                            	</ul>
	                                                                            	
	                                                                            	
	                                                                            </li> 
	                                                                        </s:if>
                                                                            <s:if test="%{(branchType=='COMMERCIAL' || branchType=='EM'  || loggedInUserLevel=='ADMIN' || loggedInUserLevel=='TECH') && (loggedInUserLevel=='DEO' || loggedInUserLevel=='ACCNTREV')}"> 
                                                                           	<s:if test="%{smcType=='BS' || smcType=='RD' || smcType=='LN' || smcType == 'PF' || smcType == 'DB' || smcType=='RE' || smcType=='AC' || smcType=='R8' || smcType=='RI' || smcType=='PH' || smcType=='AC' ||smcType=='CP'}">
                                                                              <li>
                                                                                <a href="PIV" onclick="return false">PIV II</a>
                                                                                 <ul style="padding-top :0px; ">
                                                                                     <li>
		                                                                                <a href="PIV_Sec_Deposit" onclick="return false">PIV II Security Deposit</a>
						                                                                        <ul style="padding-top :0px; ">
				                                                                                     <li>
						                                                                             <s:url id="page_url" var="page_url" namespace="/pivBS/web" action="newPivIISeDepoBS">
																									<s:param name="pivSubType">secDeposit</s:param>
																										</s:url>
																										<a href="<s:property value="#page_url" />" >Issue New PIV</a>
						                                                                            </li>
						                                                                            
				                                                                                    <li>
						                                                                                <s:url id="page_url" var="page_url" namespace="/pivBS/web" action="confirmPivIIBS">
																											<s:param name="pivSubType">secDeposit</s:param>
																										</s:url>
																										<a href="<s:property value="#page_url" />" >Confirm PIV</a>
						                                                                            </li>
						                                                                            
						                                                                            <li>
						                                                                                <s:url id="page_url" var="page_url" namespace="/pivBS/web" action="viewPivIIBS">
																											<s:param name="pivSubType">secDeposit</s:param>
																										</s:url>
																										<a href="<s:property value="#page_url" />" >View PIV</a>
						                                                                            </li>
						                                                                            <li>
						                                                                                <s:url id="page_url" var="page_url" namespace="/pivBS/web" action="modifyPivIIBS">
																											<s:param name="pivSubType">secDeposit</s:param>
																										</s:url>
																										<a href="<s:property value="#page_url" />" >Modify PIV<font class="newLinks" >   New</font></a>
						                                                                            </li>
						                                                                            <li>
						                                                                            	<s:url id="page_url" var="page_url" namespace="/pivBS/web" action="printPivIIBS">
																											<s:param name="pivSubType">secDeposit</s:param>
																										</s:url>
																										<a href="<s:property value="#page_url" />" >Print PIV</a>
						                                                                                
						                                                                            </li>
						                                                                           
						                                                                           
						                                                                        </ul>
		                                                                            </li>
                                                                                    <li>
		                                                                                <a href="PIVII_StanEst" onclick="return false">PIV II Standard Estimate</a>
		                                                                                		 <ul style="padding-top :0px; ">
				                                                                                     <li>
						                                                                             <s:url id="page_url" var="page_url" namespace="/pivBS/web" action="newPivIIBS">
																										<s:param name="pivSubType">STEstimate</s:param>
																										</s:url>
																										<a href="<s:property value="#page_url" />" >Issue New PIV</a>
						                                                                            </li>
				                                                                                    <li>
						                                                                                <s:url id="page_url" var="page_url" namespace="/pivBS/web" action="confirmPivIIBS">
																										<s:param name="pivSubType">STEstimate</s:param>
																										</s:url>
																										<a href="<s:property value="#page_url" />" >Confirm PIV</a>
						                                                                            </li>
						                                                                            <li>
						                                                                                <s:url id="page_url" var="page_url" namespace="/pivBS/web" action="viewPivIIBS">
																										<s:param name="pivSubType">STEstimate</s:param>
																										</s:url>
																										<a href="<s:property value="#page_url" />" >View PIV</a>
						                                                                            </li>
						                                                                            <li>
						                                                                                <s:url id="page_url" var="page_url" namespace="/pivBS/web" action="modifyPivIIBS">
																										<s:param name="pivSubType">STEstimate</s:param>
																										</s:url>
																										<a href="<s:property value="#page_url" />" >Modify PIV<font class="newLinks" >   New</font></a>
						                                                                            </li>
						                                                                            <li>
						                                                                            	<s:url id="page_url" var="page_url" namespace="/pivBS/web" action="printPivIIBS">
																										<s:param name="pivSubType">STEstimate</s:param>
																										</s:url>
																										<a href="<s:property value="#page_url" />" >Print PIV</a>
						                                                                                
						                                                                            </li>
						                                                                           
						                                                                           
						                                                                        </ul>
		                                                                            </li>
		                                                                            <li>
		                                                                                <a href="Total" onclick="return false">PIV II Total</a>
		                                                                                		 <ul style="padding-top :0px; ">
				                                                                                     <li>
						                                                                             <s:url id="page_url" var="page_url" namespace="/pivBS/web" action="newPivIIBS">
																										<s:param name="pivSubType">total</s:param>
																										</s:url>
																										<a href="<s:property value="#page_url" />" >Issue New PIV</a>
						                                                                            </li>
				                                                                                    <li>
						                                                                                <s:url id="page_url" var="page_url" namespace="/pivBS/web" action="confirmPivIIBS">
																											<s:param name="pivSubType">total</s:param>
																										</s:url>
																										<a href="<s:property value="#page_url" />" >Confirm PIV</a>
						                                                                            </li>
						                                                                            <li>
						                                                                                <s:url id="page_url" var="page_url" namespace="/pivBS/web" action="viewPivIIBS">
																											<s:param name="pivSubType">total</s:param>
																										</s:url>
																										<a href="<s:property value="#page_url" />" >View PIV</a>
						                                                                            </li>
						                                                                            <li>
						                                                                                <s:url id="page_url" var="page_url" namespace="/pivBS/web" action="modifyPivIIBS">
																											<s:param name="pivSubType">total</s:param>
																										</s:url>
																										<a href="<s:property value="#page_url" />" >Modify PIV<font class="newLinks" >   New</font></a>
						                                                                            </li>
						                                                                            <li>
						                                                                            	<s:url id="page_url" var="page_url" namespace="/pivBS/web" action="printPivIIBS">
																											<s:param name="pivSubType">total</s:param>
																										</s:url>
																										<a href="<s:property value="#page_url" />" >Print PIV</a>
						                                                                                
						                                                                            </li>
						                                                                           
						                                                                           
						                                                                        </ul>
		                                                                            </li>
		                                                                           
		                                                                           
		                                                                           
		                                                                        </ul>
		                                                                        
                                                                           
                                                                            </li>
                                                                            <li>
                                                                                <a href="PIV" onclick="return false">PIVIII</a>
                                                                                 <ul style="padding-top :0px; ">
                                                                                     <li>
		                                                                                <a href="PIV_Sec_Deposit" onclick="return false">PIV III </a>
						                                                                        <ul style="padding-top :0px; ">
				                                                                                     <li>
						                                                                             <s:url id="page_url" var="page_url" namespace="/pivBS/web" action="newPivIIBS">
																										<s:param name="pivSubType">secDeposit</s:param>
																										</s:url>
																										<a href="<s:property value="#page_url" />" >Issue New PIV</a>
						                                                                            </li>
				                                                                                    <li>
						                                                                                <s:url id="page_url" var="page_url" namespace="/pivBS/web" action="confirmPivIIBS">
																											<s:param name="pivSubType">secDeposit</s:param>
																										</s:url>
																										<a href="<s:property value="#page_url" />" >Confirm PIV</a>
						                                                                            </li>
						                                                                            <li>
						                                                                                <s:url id="page_url" var="page_url" namespace="/pivBS/web" action="viewPivIIBS">
																											<s:param name="pivSubType">secDeposit</s:param>
																										</s:url>
																										<a href="<s:property value="#page_url" />" >View PIV</a>
						                                                                            </li>
						                                                                            <li>
						                                                                                <s:url id="page_url" var="page_url" namespace="/pivBS/web" action="modifyPivIIBS">
																											<s:param name="pivSubType">secDeposit</s:param>
																										</s:url>
																										<a href="<s:property value="#page_url" />" >Modify PIV<font class="newLinks" >   New</font></a>
						                                                                            </li>
						                                                                            <li>
						                                                                            	<s:url id="page_url" var="page_url" namespace="/pivBS/web" action="printPivIIBS">
																											<s:param name="pivSubType">secDeposit</s:param>
																										</s:url>
																										<a href="<s:property value="#page_url" />" >Print PIV</a>
						                                                                                
						                                                                            </li>
						                                                                           
						                                                                           
						                                                                        </ul>
		                                                                            </li>
                                                                    
		                                                                            
		                                                                           
		                                                                           
		                                                                        </ul>
		                                                                        
                                                                           
                                                                            </li>
                                                                            </s:if>
                                                                            </s:if>
																	
																			
                                                                             <s:if test="%{branchType=='CONSTRUCTION'  || branchType=='DM'  || branchType=='EM' || branchType=='MU' || branchType=='AU' ||branchType=='PH' || branchType=='AC' ||branchType=='GI' || loggedInUserLevel=='ADMIN' || loggedInUserLevel=='TECH'}">  
	                                                                            <li>
	                                                                                <a href="<s:url namespace="/estimate/web" action="standardCostDetail"/>">Manage Jobs</a>
	                                                                                
	                                                                                <ul style="width:150px;">
	                                                                                 	<s:if test="%{loggedInUserLevel=='ADMIN' || loggedInUserLevel=='ACCNT' || loggedInUserLevel=='TECH' }"> 
		                                                                                	<li> 
				                                                                            	<a href="<s:url namespace="/estimate/web" action="jobCreatorDirect"/>">Job Creator<font class="newLinks" >   New</font></a>
				                                                                            </li>
				                                                                            </s:if>
				                                                                        <s:if test="%{loggedInUserLevel=='ADMIN' }"> 
		                                                                                <li>
				                                                                                <a href="<s:url namespace="/job/web" action="JournalJob"/>">Journal Jobs</a>
				                                                                        </li>
				                                                                            
				                                                                           
			                                                                            </s:if>
			                                                                            <s:if test="%{loggedInUserLevel=='ADMIN' || loggedInUserLevel=='DEO' || loggedInUserLevel=='ES' || loggedInUserLevel=='TECH' || loggedInUserLevel=='EE'}"> 
				                                                                            <li>
				                                                                                <a href="<s:url namespace="/jobBS/web" action="jobBS!init"/>">View/Print Job</a>
				                                                                            </li>
				                                                                            <li>
				                                                                                <a href="<s:url namespace="/jobBS/web" action="reviseJobBS!newDirect"/>">View</a>
				                                                                            </li>
				                                                                            <li>
				                                                                                <a href="<s:url namespace="/jobBS/web" action="reviseJobBS!init"/>">Revise Jobs</a>
				                                                                            </li>
				                                                                          
				                                                                            <li>
				                                                                                <a href="<s:url namespace="/jobBS/web" action="reviseJobBS!initUpdate"/>">Update Revise Jobs</a>
				                                                                            </li>
				                                                                            <li>
				                                                                                <a href="<s:url namespace="/jobBS/web" action="reviseJobBS!sendToApprovalBS"/>">Send For Validation</a>
				                                                                            </li>
				                                                                             <li>
				                                                                                <a href="<s:url namespace="/jobBS/web" action="jobBS!existingJob"/>">Existing Jobs</a>
				                                                                            </li>
				                                                                             
			                                                                            </s:if>
			                                                                            
			                                                                                  <s:if test="%{loggedInUserLevel=='ADMIN' || loggedInUserLevel=='ACCT' || loggedInUserLevel=='TECH' || loggedInUserLevel=='EE'}"> 
				                                                                            <li>
				                                                                                <a href="<s:url namespace="/jobBS/web" action="softcloseEE"/>">Soft Close EE</a>
				                                                                            </li>
				                                                                            <li>
				                                                                                <a href="<s:url namespace="/jobBS/web" action="softclosePE"/>">Soft Close PE</a>
				                                                                            </li>
				                                                                            <li>
				                                                                                <a href="<s:url namespace="/jobBS/web" action="jobcloseACCT"/>">Job Close ACCT</a>
				                                                                            </li>
				                                                                       
				                                                                       
				                                                                             
			                                                                            </s:if>
			                                                                            <s:if test="%{loggedInUserLevel=='PCE' ||loggedInUserLevel=='CE' || loggedInUserLevel=='EE' || loggedInUserLevel=='DGM'}">
			                                                                            <li>
			                                                                            	<a href="<s:url namespace="/jobBS/web" action="reviseJobBS!ReviseValidationBS"/>">Approve Revised Job</a>
			                                                                           	</li>
			                                                                           </s:if>
			                                                                            <li>
<a href="<s:url namespace="/rebate/web" action="rebate"/>">Add Rebate</a>
</li>
<li>
<a href="<s:url namespace="/rebate/web" action="updaterebate"/>">Modify Rebate</a>
</li>
																				</ul>
	                                                                            </li>
																				<s:if test="%{loggedInUserLevel=='AE' || loggedInUserLevel=='EE' || loggedInUserLevel=='CE' || loggedInUserLevel=='ACCNT' || loggedInUserLevel=='TECH'  || loggedInUserLevel=='HELPDESK' || loggedInUserLevel=='ADMIN'}"> 
			                                                                           	<li>
			                                                                                <a href="Job Closer" onclick="return false">Job Closer</a>
			                                                                                <ul style="padding-top :0px; ">
			                                                                                   	
			                                                                                   <s:if test="%{loggedInUserLevel=='EE' || loggedInUserLevel=='CE' || loggedInUserLevel=='TECH' || loggedInUserLevel=='ADMIN' }"> 
			                                                                                    <li>
			                                                                            			<a href="<s:url namespace="/jobBS/web" action="JobCloseBS"/>">Soft Close Jobs</a>
			                                                                            		</li> 
			                                                                            		<li>
			                                                                            			<a href="<s:url namespace="/jobBS/web" action="softjobCloseGroupBS"/>">Soft Close Group</a>
			                                                                            		</li>
																								</s:if>
																								<s:if test="%{loggedInUserLevel=='TECH' || loggedInUserLevel=='ADMIN' || loggedInUserLevel=='ACCNT'}"> 
			                                                                                    <li>
			                                                                            			<a href="<s:url namespace="/jobBS/web" action="HardJobCloseBS"/>">Job Closer</a>
			                                                                            		</li>
			                                                                            		<li>
			                                                                            			<a href="<s:url namespace="/jobBS/web" action="jobCloseGroupBS"/>">Close Jobs Group</a>
			                                                                            		</li>
																								</s:if>
			                                                                            		
			                                                                            		<s:if test="%{loggedInUserLevel=='TECH' || loggedInUserLevel=='ADMIN' || loggedInUserLevel=='ACCNT'}"> 
			                                                                            		<li>
			                                                                            			<a href="<s:url namespace="/jobBS/web" action="forceJobCloseBS"/>">Forced Job Close</a>
			                                                                            		</li>
			                                                                            		</s:if>
			                                                                            		
			                                                                            	</ul>
			                                                                            </li>
                                                                            </s:if>
	                                                                             <li>
	                                                                                <a href="Job Contractor" onclick="return false">Job Contractor</a>
	                                                                                <ul style="padding-top :0px; ">
	                                                                                   
	                                                                            	<s:if test="%{(loggedInUserLevel=='ADMIN' || loggedInUserLevel=='DEO' || loggedInUserLevel=='ES' || loggedInUserLevel=='EE' ||loggedInUserLevel=='TECH'  || loggedInUserLevel=='HELPDESK')}">
	                                                                                    <li>
	                                                                               			 <a href="<s:url namespace="/masters/web" action="AddNewContractor"/>">Add</a>
	                                                                            		</li>
	                                                                            		<li>
	                                                                               			 <a href="<s:url namespace="/masters/web" action="UpdateContractor"/>">Update</a>
	                                                                            		</li>
	                                                                            	
			                                                                            <li>
	                                                                                		<a href="<s:url namespace="/masters/web" action="ContractorList"/>">View<font class="newLinks" >   New</font></a>
	                                                                           			</li>
	                                                                                    <li>
	                                                                                		<a href="<s:url namespace="/estimate/web" action="newJobAllocationBS"/>">Job Allocation</a>
	                                                                            		</li>
	                                                                            		<li>
	                                                                                		<a href="<s:url namespace="/jobBS/web" action="newJobDeAllocationBS"/>">Job De Allocation</a>
	                                                                            		</li>
	                                                                            	</s:if>
	                                                                            	
	                                                                            		
	                                                                            		
	                                                                            	   <li>
	                                                                            			<a href="<s:url namespace="/job/web" action="billContractor"/>">Bill Contractor</a>
	                                                                            		</li>
	                                                                            		 <li>
																							<a href="<s:url namespace="/contractor/web" action="billContractor"/>">Contractor Bill</a>
																						</li>
																						<li>
	                                                                                          <a href="<s:url namespace="/contractor/web" action="billContractorManage"/>">Contractor Bill Manage</a>
	                                                                                    </li>
																						
	                                                                            		
	                                                                            	</ul>
	                                                                            </li>
                                                                            </s:if>
                                                                        </ul>
                                                                    </li>
                                                                  
<!--  
                                                                    <li>
                                                                        <a class="item-primary" href="#">Manage Jobs</a>
                                                                        <ul style="width:150px;">
                                                                            <li>
                                                                                <a href="<s:url namespace="/jobBS/web" action="reviseJobBS!init"/>">Revise Jobs</a>
                                                                            </li>
                                                                            <li>
                                                                                <a href="<s:url namespace="/jobBS/web" action="reviseJobBS!initUpdate"/>">Update Revise Jobs</a>
                                                                            </li>
                                                                            <li>
                                                                                <a href="<s:url namespace="/jobBS/web" action="reviseJobBS!sendToApprovalBS"/>">Send For Validation</a>
                                                                            </li>
                                                                            
                                                                            <li>
                                                                                <a href="<s:url namespace="/jobBS/web" action="printDirect"/>">View/Print Job<font class="newLinks" >   New</font></a>
                                                                            </li>
                                                                            
                                                                            <s:if test="%{loggedInUserLevel!='DEO'}">
                                                                            <li>
                                                                            	<a href="<s:url namespace="/jobBS/web" action="reviseJobBS!ReviseValidationBS"/>">Approve Revised Job</a>
                                                                           	</li>
                                                                           	</s:if>
                                                                           	<li>
                                                                            	<a href="<s:url namespace="/jobBS/web" action="reviseJobBS!undoDirect"/>">Undo Rejected Jobs</a>
                                                                           	</li>
                                                                        	<li>
                                                                                <a href="Progress_Monitoring" onclick="return false">Progress Monitoring</a>
                                                                                <ul style="padding-top :0px; ">
                                                                                    <li>
                                                                                		<a href="<s:url namespace="/progressMonitoring/web" action="progressMonitoring!initialData"/>">Initial Data</a>
                                                                            		</li>
                                                                            		<li>
		                                                                                <a href="<s:url namespace="/progressMonitoring/web" action="progressMonitoring!addProgress "/>">Update Progress</a>
		                                                                            </li>
		                                                                          
                                                                            	</ul>
                                                                            </li>
                                                                           	
                                                                           	<li>
                                                                                <a href="Job Contractor" onclick="return false">Job Contractor</a>
                                                                                <ul style="padding-top :0px; ">
                                                                                    <li>
                                                                                		<a href="<s:url namespace="/jobBS/web" action="newJobAllocationBS"/>">Job Allocation</a>
                                                                            		</li>
                                                                            		<li>
                                                                                		<a href="<s:url namespace="/jobBS/web" action="newJobDeAllocationBS"/>">Job De Allocation</a>
                                                                            		</li>
                                                                            		 
                                                                            		<s:if test="%{smcType=='NC'}">
                                                                            		<li>
                                                                                		<a href="<s:url namespace="/job/web" action="jobEnergize"/>">Job Energize</a>
                                                                            		</li>
                                                                            		</s:if>
                                                                            		<s:if test="%{smcType=='CR'}">
                                                                            		<li>
                                                                                		<a href="<s:url namespace="/job/web" action="cRjobFinish"/>">CR Job Finish</a>
                                                                            		</li>
                                                                            		</s:if>
                                                                            		<s:if test="%{smcType=='NC' && costCenterNo=='514.20'}">
                                                                            		<li>
                                                                                		<a href="<s:url namespace="/job/web" action="cRjobFinish1"/>">CR Job Finish</a>
                                                                            		</li>
                                                                            		</s:if>
                                                                            		
                                                                            		
                                                                            	  <li>
                                                                            			<a href="<s:url namespace="/job/web" action="billContractor"/>">Bill Contractor</a>
                                                                            		</li>
                                                                            		 <li>
																						<a href="<s:url namespace="/contractor/web" action="billContractor"/>">Contractor Bill</a>
																					</li>
																					<li>
                                                                                          <a href="<s:url namespace="/contractor/web" action="billContractorManage"/>">Contractor Bill Manage</a>
                                                                                    </li>
																					
                                                                            		
                                                                            	</ul>
                                                                            </li>
                                                                           	<s:if test="%{loggedInUserLevel=='AE' || loggedInUserLevel=='ACCNT' || loggedInUserLevel=='TECH'  || loggedInUserLevel=='HELPDESK' || loggedInUserLevel=='ADMIN'}"> 
                                                                           	<li>
                                                                                <a href="Job Closer" onclick="return false">Job Closer</a>
                                                                                <ul style="padding-top :0px; ">
                                                                                   	
                                                                                   	<s:if test="%{loggedInUserLevel=='EE' || loggedInUserLevel=='CE' || loggedInUserLevel=='ADMIN'}"> 
                                                                                    <li>
                                                                            			<a href="<s:url namespace="/jobBS/web" action="JobCloseBS"/>">Soft Close Jobs</a>
                                                                            		</li> 
                                                                            		<li>
                                                                            			<a href="<s:url namespace="/jobBS/web" action="softjobCloseGroupBS"/>">Soft Close Group</a>
                                                                            		</li>
                                                                            		</s:if>                                                                                                                                                     
                                                                                    <s:if test="%{loggedInUserLevel=='ACCNT' || loggedInUserLevel=='ADMIN'}">
                                                                                    <li>
                                                                            			<a href="<s:url namespace="/jobBS/web" action="HardJobCloseBS"/>">Job Closer</a>
                                                                            		</li>
                                                                            		<li>
                                                                            			<a href="<s:url namespace="/jobBS/web" action="jobCloseGroupBS"/>">Close Jobs Group</a>
                                                                            		</li>
                                                                            		</s:if>
                                                                            		<s:if test="%{loggedInUserLevel=='TECH' || loggedInUserLevel=='ADMIN'}"> 
                                                                            		<li>
                                                                            			<a href="<s:url namespace="/jobBS/web" action="forceJobCloseBS"/>">Forced Job Close</a>
                                                                            		</li>
                                                                            		</s:if>
                                                                            		
                                                                            	</ul>
                                                                            </li>
                                                                            </s:if>
                                                                            
                                                                            <s:if test="%{smcType=='NC'}">
                                                                            <li>
                                                                            	<a href="#">NC Job Finalise<font class="newLinks" >   New</font></a>
		                                                                            	<ul style="padding-top :0px; ">                                                                            				
                                                                            			<li>
                                                                                			<a href="<s:url namespace="/job/web" action="smcCardDirect"/>">SMC Card<font class="newLinks" >   New</font></a>
                                                                            			</li>
                                                                            			<li>
                                                                                			<a href="<s:url namespace="/job/web" action="nCjobFinish"/>">NC Job Finish<font class="newLinks" >   New</font></a>
                                                                            			</li>
                                                                            			</ul>
     																				                                                                                
 																			</li>
                                                                            
                                                                            </s:if>
                                                                            <s:if test="%{smcType=='CR'}">
                                                                            <li>
                                                                            	<a href="#">CR Job Finalise<font class="newLinks" >   New</font></a>
		                                                                            	<ul style="padding-top :0px; ">                                                                            				
                                                                            				<li>
                                                                                				<a href="<s:url namespace="/job/web" action="smcCardDirect"/>">SMC Card<font class="newLinks" >   New</font></a>
                                                                            				</li>
                                                                            				<li>
                                                                                				<a href="<s:url namespace="/job/web" action="cRjobFinish"/>">CR Job Finish<font class="newLinks" >   New</font></a>
                                                                            				</li>
                                                                            				</ul>
     																				                                                                                
 																			</li>
                                                                            
                                                                            </s:if>
                                                                           
                                                                            <s:if test="%{smcType=='TC'}">
                                                                            		<li>
                                                                                		<a href="<s:url namespace="/job/web" action="tCEnergize"/>">Temporary Job Energize</a>
                                                                            		</li>
                                                                            </s:if>
                                                                            
                                                                           <li>
                                                                                <a href="<s:url namespace="/job/web" action="timeCard"/>">SMC Time Card</a>
                                                                            </li>
                                                                            <s:if test="%{loggedInUserLevel=='ADMIN' || loggedInUserLevel=='BILL' || loggedInUserLevel=='DEO' || loggedInUserLevel=='EA' || loggedInUserLevel=='ES'  || loggedInUserLevel=='TECH'  || loggedInUserLevel=='HELPDESK'}"> 
                                                                            <li>
                                                                            	<a href="#">Billing<font class="newLinks" >   New</font></a>
		                                                                            	<ul style="padding-top :0px; ">                                                                            				
                                                                            				<li>
                                                                                				<a href="<s:url namespace="/job/web" action="SendToBill"/>">Send to Billing<font class="newLinks" >   New</font></a>
                                                                           					 </li>
                                                                            				<li>
                                                                                				<a href="<s:url namespace="/job/web" action="accNoInfoDirect"/>">Acc No Info<font class="newLinks" >   New</font></a>
                                                                            				</li>
                                                                            				<li>
                                                                                				<a href="<s:url namespace="/job/web" action="billingErrorsDirect"/>">Billing Errors<font class="newLinks" >   New</font></a>
                                                                           					 </li>
                                                                            				
                                                                            			</ul>
     																				                                                                                
 																			</li>
                                                                            
                                                                            </s:if>
                                                                            
                                                                           
                                                                            
                                                                            
                                                                        </ul>
                                                                    </li>-->
                                                                    <s:if test="%{loggedInUserLevel!='BILL' && loggedInUserLevel!='ACCNT' || loggedInUserLevel=='ADMIN'}">
																		<li><a class="item-primary" href="#"
																				onclick="return false">Progress Monitoring</a>
																				<ul style="width: 150px;">
																					 <li><a href="<s:url namespace="/progressMonitoring/web" action="addProgress"/>">Add Initial Data</a></li>
																						<li><a href="<s:url namespace="/progressMonitoring/web" action="modifyProgress"/>">Add/Modify Progress</a></li>
			
																						
			
																				</ul>
																		</li>
																	</s:if>
							<s:if test="%{loggedInUserLevel!='BILL' && loggedInUserLevel!='ACCNT' || loggedInUserLevel=='ADMIN' || loggedInUserLevel=='TECH'}">
                                                                    <li>
                                                                    	<a class="item-primary" href="#" onclick="return false">Letters</a>
																		<ul style="width: 150px;">
																		<!-- <li>
																			<a href="<s:url namespace="/officeDocs/web" action="loadestimateReferencePage"/>">Standard Estimate Detail</a>
																		</li>
																		<li>
																			<a href="<s:url namespace="/officeDocs/web" action="loadconsumerVisitingPage"/>">Standard Estimate Summary</a>
																		</li>
																		<li>
																			<a href="<s:url namespace="/officeDocs/web" action="loadRequestForBulkSupplyPage"/>">Dispatch Of PIV(RE)</a>
																		</li>
																		<li>
																			<a href="<s:url namespace="/officeDocs/web" action="loadConsumerEnergizePage"/>">Dispatch Of PIV(BS/LN)</a> 
																		</li> -->
																			<li> 
																				 <a href="<s:url namespace="/estimate/web" action="estimateBS!View"/>">Standard Estimate Details</a>
																			</li>
																			<li> 
																				 <a href="<s:url namespace="/officeDocs/web" action="loadConditionsHTDoc"/>">HT Metering Conditions</a>
																			</li>
																			<li> 
																				 <a href="<s:url namespace="/officeDocs/web" action="loadConditionsLTDoc"/>">LT Metering Conditions</a>
																			</li>
																			
																			
																			<li><a href="Application" onclick="return false">Con/Mnt/Area</a>
																			 <ul style="padding-top :0px; ">
																			 <li> 
																				 <a href="<s:url namespace="/officeDocs/web" action="loadAllocationOfConstructionJob"/>">Allocation Of CON Job</a>
																			</li>
																			<li> 
																				 <a href="<s:url namespace="/officeDocs/web" action="loadAllocationOfConstructionJobCon"/>">Allocation Of CON Job-Originated by Construction </a>
																			</li>
																			<li> 
																				 <a href="<s:url namespace="/officeDocs/web" action="loadEstimateVariance"/>">Estimate Variance</a>
																			</li>
																			<li> 
																				 <a href="<s:url namespace="/officeDocs/web" action="loadAllocationJobNumber"/>">Allocation Of Job Number</a>
																			</li>
																																						
																			<li> 
																				 <a href="<s:url namespace="/officeDocs/web" action="loadContractorOfferLetter"/>">Contractor Offer</a>
																			</li>
																			<li> 
																				 <a href="<s:url namespace="/officeDocs/web" action="loadCompletionOfConstructionJob"/>">Completion of Construction Job</a>
																			</li>
																			<li> 
																				 <a href="<s:url namespace="/officeDocs/web" action="loadhandingOverJob"/>">HandingOver Completed Job</a>
																			</li>
																			<li> 
																				 <a href="<s:url namespace="/officeDocs/web" action="loadTransferConstructionToAssets"/>">Job Transfering To Assets</a>
																			</li>
																			
																			<li> 
																				 <a href="<s:url namespace="/officeDocs/web" action="loadCompletionReport"/>">Completion Report</a>
																			</li>
																			
																			<li> 
																				 <a href="<s:url namespace="/officeDocs/web" action="loadJobCancelletion"/>">Job Cancellation</a>
																			</li>
																			 </ul>
																			</li>
																																																							
																			
																			<li><a href="Application" onclick="return false">Maintenace</a>
																			<ul style="padding-top :0px; ">
																			
																			<li> 
																				 <a href="<s:url namespace="/officeDocs/web" action="loadAllocationOfConstructionJob"/>">Allocation Of MNT Job</a>
																			</li>
																			<li> 
																				 <a href="<s:url namespace="/officeDocs/web" action="loadAllocationOfConstructionJobCon"/>">Allocation Of MNT Job-Originated by MNT </a>
																			</li>
																			<li> 
																				 <a href="<s:url namespace="/officeDocs/web" action="loadEstimateVariance"/>">Estimate Variance</a>
																			</li>
																			<li> 
																				 <a href="<s:url namespace="/officeDocs/web" action="loadAllocationJobNumber"/>">Allocation Of MNT Job Number</a>
																			</li>
																			<li> 
																				 <a href="<s:url namespace="/officeDocs/web" action="loadAllocationMNTJobNumber"/>">Allocation Of MNT/SAB JobNumber</a>
																			</li>
																			<li> 
																				 <a href="<s:url namespace="/officeDocs/web" action="loadContractorOfferLetter"/>">Contractor Offer</a>
																			</li>
																			<li> 
																				 <a href="<s:url namespace="/officeDocs/web" action="loadCebOfferMainJob"/>">CEB Offer of a Main Job</a>
																			</li>
																			<li> 
																				 <a href="<s:url namespace="/officeDocs/web" action="loadCompletionOfConstructionJob"/>">Completion of MNT Job</a>
																			</li>
																			<li> 
																				 <a href="<s:url namespace="/officeDocs/web" action="loadhandingOverJob"/>">HandingOver Completed Job</a>
																			</li>
																			<li> 
																				 <a href="<s:url namespace="/officeDocs/web" action="loadTransferConstructionToAssets"/>">Job Transfering To Assets</a>
																			</li>
																			
																			<li> 
																				 <a href="<s:url namespace="/officeDocs/web" action="loadCompletionReport"/>">Completion Report</a>
																			</li>
																			
																			<li> 
																				 <a href="<s:url namespace="/officeDocs/web" action="loadJobCancelletion"/>">Job Cancellation</a>
																			</li>
																			</ul>
																			</li>	
																			
																			<li><a href="Application" onclick="return false">Area</a>
																			 <ul style="padding-top :0px; ">
																			 
																			<li> 
																				 <a href="<s:url namespace="/officeDocs/web" action="loadAllocationJobNumberArea"/>">Allocation Of Job Number</a>
																			</li>
																			
																			</ul>
																			</li>																																	
																																
																																
																																				
																			
														
																		<!-- 
																		<li>
																			<a href="<s:url namespace="/officeDocs/web" action="loadestimateReferencePage"/>">Reinspection Letter</a>
																		</li>
																		<li>
																			<a href="<s:url namespace="/officeDocs/web" action="loadconsumerVisitingPage"/>">Consumer Site Visiting</a>
																		</li>
																		<li>
																			<a href="<s:url namespace="/officeDocs/web" action="loadRequestForBulkSupplyPage"/>">Request for bulk supply</a>
																		</li>
																		<li>
																			<a href="<s:url namespace="/officeDocs/web" action="loadConsumerEnergizePage"/>">Consumer Energize</a> 
																		</li>
																		<li> 
																			 <a href="<s:url namespace="/officeDocs/web" action="loadEstimateAmountPage"/>">Estimate Quotation</a>
																		</li> -->
																			
																		</ul>
																	</li> 
																	</s:if>
																	
																	<s:if test="%{loggedInUserLevel=='ADMIN'}">
                                                                    	<li>
                                                                        	<a class="item-primary" href="#">Plant & Equipment</a>
                                                                            <ul style="width:150px;">
                                                                       			<li>
                                                                               		<a href="Service Estimate" onclick="return false">Ordinary</a>
                                                                             		<ul style="padding-top :0px.; ">
	                                                                                    <li>
	                                                                                		<a href="<s:url namespace="/fa/web" action="PlantEquip"/>">Mat. Categorisation</a>
                                                                               				<a href="<s:url namespace="/fa/web" action="PlantEquip!verify"/>">Verify</a>
                                                                               				<a href="<s:url namespace="/fa/web" action="PlantEquip!transfer"/>">Transfer</a>
	                                                                            		</li>
	                                                                            		
                                                                         			</ul>
                                                                         		</li>
                                                                         		<li>
                                                                               		<a href="Service Estimate" onclick="return false">Bulk</a>
                                                                             		<ul style="padding-top :0px.; ">
	                                                                                    <li>
	                                                                                		<a href="<s:url namespace="/fa/web" action="PlantEquipBulk"/>">Mat. Categorisation</a>
	                                                                                		<a href="<s:url namespace="/fa/web" action="PlantEquipBulk!verify"/>">Verify</a>
	                                                                                		<a href="<s:url namespace="/fa/web" action="PlantEquipBulk!transfer"/>">Transfer</a>
	                                                                            		</li>
                                                                         			</ul>
                                                                         		</li>
                                                                         		
                                                                       		</ul>
                                                                         	
                                                                    	</li>
                                                                    </s:if>
                                                                    
                                                               		<s:if test="%{loggedInUserLevel!='BILL' && loggedInUserLevel!='ACCNT' || loggedInUserLevel=='ADMIN' || loggedInUserLevel=='TECH' || branchType=='PH'  || branchType=='AC'  ||branchType=='CONSTRUCTION'  || branchType=='DM'}">
                                                                    <li>
                                                                    	<a class="item-primary" href="#" onclick="return false">Reports</a>
																		<ul style="width: 150px;">
	
																			<li> 
																				 <a href="<s:url namespace="/officeDocs/web" action="summaryofInProgressConstructionNew"/>">Summary Of InPrograss Jobs</a>
																			</li>
																		    <li> 
																				 <a href="<s:url namespace="/officeDocs/web" action="statusReportCon"/>">Status Report Con/Mnt</a>
																			</li>
																			<li> 
																				 <a href="<s:url namespace="/officeDocs/web" action="statusReportCom"/>">Status Report Commercial</a>
																			</li>
																			<s:if test="%{loggedInUserLevel=='ADMIN'}">
																			<li>
																				<a href="<s:url namespace="/report/web" action="report"/>">Reports</a>
																			</li>
																			</s:if>
																			
																		</ul>
																	</li> 
																	</s:if>
                                                                    
                                                                     <li>
                                                                    	<a class="item-primary" href="#" onclick="return false">Help</a>
																		<ul style="width: 150px;">
																			<li> 
																				 <a href="<s:url namespace="/officeDocs/web" action="estmateCancellationNew"/>"> <font class="newLinks" >   New Help</font></a>
																			</li>
																			
																		    
                                                                        
																		</ul>
																	</li> 
																	
                                                                    
                                                                    
                                                                </ul>
                                                            </div>
                                                    </s:form></td>
                                                    <td class="tdWelcomeLabel">Welcome</td>
                                                    <td class="tdWelcomeUser"><s:property value="loggedInUserId"></s:property></td>
                                                    <td class="tdLogOut"><a href="<s:url action="changeCC"/>"><s:property value="costCenterNo"></s:property></a> </td>
                                                    <td class="tdLogOut"><a href="<s:url action="changeSMCType"/>"><s:property value="smcType"></s:property></a> </td>
                                                    <td class="tdLogOut"><a href="<s:url action="logout"/>">Logout</a> </td>
                                                </tr>
                                                <tr>
                                                	<td></td>
                                                    <td></td>
                                                    <td></td>
                                                    <td></td>
                                                    <td></td>
                                                    <td></td>
                                                </tr>
                                            </tbody>
                                        </table>


                                    </td>

                                </tr>
                                 <tr><td>
                                 <table width="100%" border="0" >
                                <tr ><td class="tblMenuHeading">Messages </td></tr>
                                <tr><td><table class="tldFromContent" border="1"  width="100%">
                                <tr class="tblMenuColorMsg" height="25px"><!--<td><strong>Msg Id</strong> </td>-->
                                <td><strong>Message </strong> </td>
                                </tr>
                                <tr class="tblMenuColorMsg" height="25px">
                                <td><strong>1.   MIS HELP DESK new telephone numbers: 0112 390 854 and 0112 481250. Please use these two numbers to contact MIS Help desk </strong> </td>
                                </tr>
                                <tr class="tblMenuColorMsg" height="25px">
                                <td><strong>2.   Circuler No : 2015/DCC/COM-3 has been implemented.Please see the help document for more details (Help -->New Help)</strong> </td>
                                </tr>
                                </table>
                                </td>
                                </tr>
                                <tr>
                                <td>&nbsp;</td>
                                </tr>
                                </table>
                                </td>
                                </tr>
                                
                        
                                       	
                                <tr class="tdContent">
                                	<td class="tdMsgStyle">
                                		<s:property value="errorMessage"></s:property>
                                	</td>
                                
                                </tr>
                                <tr>
                                	
									<td>
									<table width="100%" border="0" align="center"
							 cellpadding="0" cellspacing="0" class="tblContent" >
							<tbody>
							
							<s:if test="%{branchType=='COMMERCIAL' || loggedInUserLevel=='ADMIN' || loggedInUserLevel=='TECH'}"> 
							<tr>
									
									
									<td>
									
										<s:if test="ativeAppoinmentsList != NULL">
									
									    </s:if>
									</td>
									
								</tr>
								<tr>
									<td>
									
										<s:if test="tobeEnteredStdEstimates != NULL">
							
									</s:if>
									</td>
								</tr>
								<tr>
									<td>
									<div id="accordion">
									<s:if test="%{(smcType=='CP' || smcType=='PL' || smcType=='BS' || smcType=='RD' || smcType=='LN' || smcType =='PF' ||smcType =='DB' || smcType=='RE' || smcType=='AC' || smcType=='R8' || smcType=='RI' ) &&  (loggedInUserLevel=='PCE' || loggedInUserLevel=='DGM' ||loggedInUserLevel=='CE' || loggedInUserLevel=='PE' || loggedInUserLevel=='EE' || loggedInUserLevel=='DEO' || loggedInUserLevel=='ES' || loggedInUserLevel=='ADMIN' || loggedInUserLevel=='TECH')}">
									   
									  
										
											
											<s:if test="%{loggedInUserLevel=='ES' || loggedInUserLevel=='EE'  || loggedInUserLevel=='PE' || loggedInUserLevel=='ADMIN' || loggedInUserLevel=='TECH'}">
												<!-- td class="tblMenuHeading">Standard Estimates to be Validated</td-->
												
												<h3 id="hAppEstimateTobeValidate">Standard Estimates to be Validated (<s:property value="%{tobeValidateListCount}"/>) </h3>
											       <div>
													<div style="display:none;" id="appEstimateTobeValidate"></div>
												   </div>
												   
											</s:if>
											<s:if test="%{loggedInUserLevel=='PCE' || loggedInUserLevel=='CE'  || loggedInUserLevel=='DGM' || loggedInUserLevel=='ADMIN' || loggedInUserLevel=='TECH'}">
												<!-- td class="tblMenuHeading">Standard Estimates to be Approved</td-->
												
												<h3 id="hAppEstimateTobeValidate">Standard Estimates to be Approved (<s:property value="%{tobeValidateListCount}"/>) </h3>
											       <div>
													<div style="display:none;" id="appEstimateTobeValidate"></div>
												   </div>
												   
											</s:if>
											
											
										
										
									 
									  
									     
									   <s:if test="%{(smcType=='CP' || smcType=='PL' || smcType=='BS' || smcType=='RD' || smcType=='LN' || smcType == 'PF' || smcType == 'DB' || smcType=='RE' || smcType=='AC' || smcType=='R8' || smcType=='RI' ) &&  (loggedInUserLevel=='PE' ||  loggedInUserLevel=='CE' || loggedInUserLevel=='EE' || loggedInUserLevel=='DEO' || loggedInUserLevel=='ES' || loggedInUserLevel=='ADMIN' || loggedInUserLevel=='TECH')}">
										
										<s:if test="toBeAllocatedList != NULL">
										    <!-- td class="tblMenuHeading">Standard Estimates to be Approved</td-->
												
												<h3 id="hAppToBeAllocatedList">To Be Allocated (<s:property value="%{toBeAllocatedListCount}"/>) </h3>
												   <div>
													<div style="display:none;" id="appToBeAllocatedList"></div>
												   </div>
												  
										
									   </s:if>	
									<s:if test="needToIssuePivIIList != NULL">
											<s:if test="%{loggedInUserLevel=='DEO' || loggedInUserLevel=='TECH'}">
												
												<h3 id="hAppSetPIVIIList">Pending Standard Estimates to be issued PIVII (<s:property value="%{needToIssuePivIIListCount}"/>) </h3>
												   <div>
													<div style="display:none;" id="appSetPIVIIList"></div>
												   </div>
											</s:if>
									
									</s:if>	
									
											<s:if test="approvedEstimateList != NULL">
											<s:if test="%{loggedInUserLevel=='DEO' || loggedInUserLevel=='TECH'}">
												
												<h3 id="hStdApprovedEstimates">Standard Estimates to be Issued Award Letter (<s:property value="%{approvedEstimateListCount}"/>) </h3>
												   <div>
													<div style="display:none;" id="stdApprovedEstimates"></div>
												   </div>
											</s:if>
											<s:if test="%{loggedInUserLevel=='CE'  || loggedInUserLevel=='DGM' || loggedInUserLevel=='ES' || loggedInUserLevel=='EE'  || loggedInUserLevel=='PE' || loggedInUserLevel=='TECH'}">	
												<h3 id="hStdApprovedEstimates">Approved Standard Estimates (<s:property value="%{approvedEstimateListCount}"/>) </h3>
												   <div>
													<div style="display:none;" id="stdApprovedEstimates"></div>
												   </div>
											</s:if>
											
									
									</s:if>	
								    </s:if>		
									</s:if>
									<s:if test="%{(smcType=='CP' || smcType=='PL' || smcType=='BS' || smcType=='RD' || smcType=='LN' || smcType == 'PF' || smcType == 'DB' || smcType=='RE' || smcType=='AC' || smcType=='R8' || smcType=='RI' ) &&  (loggedInUserLevel=='PE' || loggedInUserLevel=='EE' || loggedInUserLevel=='DEO' || loggedInUserLevel=='ES' || loggedInUserLevel=='ADMIN' || loggedInUserLevel=='TECH')}">
									<s:if test="sEsendForConMainList != NULL">
										<h3 id="hStdSendForConsMainList">Standard Estimates : To be sent for construction and maintenance (<s:property value="%{sEsendForConMainListCount}"/>) </h3>
												   <div>
													<div style="display:none;" id="stdSendForConsMainList"></div>
												   </div>
									
									</s:if>	
									<s:if test="tobeEnteredStdEstimates != NULL">
							            <h3 id="hStdTobeEnteredStdEstimates">Standard Estimate to be Entered (<s:property value="%{tobeEnteredStdEstimatesCount}"/>) </h3>
												   <div>
													<div style="display:none;" id="stdTobeEnteredStdEstimates"></div>
												   </div>
									</s:if>
									<s:if test="ativeAppoinmentsList != NULL">
									
							            <h3 id="hStdSetAtiveAppoinments">Active Appointments(<s:property value="%{ativeAppoinmentsListCount}"/>) </h3>
												   <div>
													<div style="display:none;" id="stdSetAtiveAppoinments"></div>
												   </div>
									</s:if>
									
									
									</s:if>	
									
									
									</div>
									</td>
								</tr>
									
								<tr>
									<td>
									<s:if test="%{smcType=='CP' && (loggedInUserLevel=='EE' || loggedInUserLevel=='DEO' || loggedInUserLevel=='ES' || loggedInUserLevel=='ADMIN' || loggedInUserLevel=='TECH')}">
										<s:if test="toBeAllocatedList != NULL">
											<table width="100%" border="0">
												<tr>
													<td class="tblMenuHeading">Standard Estimates : To be sent for construction and maintenance</td>
												</tr>
												<tr>
													<td>
													<table class="tldFromContent" border="1">
	
														<tr class="tblMenuColor" height="25px">
															<td width="25%"><strong>Estimate Number</strong></td>
															<td><strong>Cost Center</strong></td>
															<td><strong>Application Type</strong></td>
															<td><strong>Service Depot Name</strong></td>
															<td><strong>Proposer</strong></td>
															<td><strong>Schema Name</strong></td>
														
															
														</tr>
														<%int i=0; %> 
														<s:iterator value="toBeAllocatedList">
															<tr bgcolor="#CCCCCC">
																<s:url id="page_url" namespace="/estimate/web" action="sendForConsMainFMainMenu">
																	
	                                                                <s:param name="estNo" value="%{estimateNo}" />
	                                                                 
                                                                     <s:param name="costCen" value="%{costCenter}" />
																</s:url>
																<td align="right" id="unAttApptRefNo_<%= i %>"><s:a href="%{page_url}"><s:property value="estimateNo" /></s:a></td>
															
																
																
																<td align="right"><s:property value="costCenter" /></td>
																<td align="right"><s:property value="applicationType" /></td>
																
																<td align="right"><s:property value="area" /></td>
																
																<td align="right"><s:property value="proposer" /></td>
																
																<td align="right"><s:property value="schemeName" /><s:property value="schemeNo" /></td>
																
																															 
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
									</s:if>
									</td>
								</tr>
								<tr>
									<td>
									<s:if test="%{(smcType=='PL' || smcType=='BS' || smcType=='RD' || smcType=='LN' || smcType == 'PF' || smcType == 'DB' || smcType=='RE' || smcType=='AC' || smcType=='R8' || smcType=='RI' ) &&  (loggedInUserLevel=='PE' || loggedInUserLevel=='EE' || loggedInUserLevel=='DEO' || loggedInUserLevel=='ES' || loggedInUserLevel=='ADMIN' || loggedInUserLevel=='TECH')}">
									<s:if test="sEsendForConMainList != NULL">
							
									</s:if>
									</s:if></td>
								</tr>
								<tr>
									<td>
									<s:if test="%{(smcType=='PL' &&  loggedInUserLevel=='PE') || loggedInUserLevel=='EE' || loggedInUserLevel=='DEO' || loggedInUserLevel=='ES' || loggedInUserLevel=='ACCNTREV' || loggedInUserLevel=='ADMIN' || loggedInUserLevel=='TECH'}">
									<s:if test="conMainSentList != NULL">
										<table width="100%" border="0">
											<tr>
												<td class="tblMenuHeading">Standard Estimates : Sent For Construction And Maintenance</td>
											</tr>
											<tr>
												<td>
												<table class="tldFromContent" border="1">

													<tr class="tblMenuColor" height="25px">
														<td><strong>Estimate Number</strong></td>
														<td><strong>Cost Center</strong></td>
														<td><strong>Estimate Date</strong></td>
														<td><strong>Standard Cost</strong></td>
														<td><strong>Entered By</strong></td>
														<td><strong>Approved By</strong></td>
														<td><strong>Approved Date</strong></td>
														
													<%int i=0; %>
													<s:iterator value="conMainSentList">
														<tr bgcolor="#CCCCCC">
															
                                                             <s:url id="page_url" namespace="/estimate/web" action="estimateBS!View">
                                                             <s:param name="estNo" value="%{id.stdNo}" />
                                                             <s:param name="costCen" value="%{id.deptId}" />
                                                             </s:url>
                                                          	
															<td align="right" id="tdApprestNo_<%= i %>"><s:a href="%{page_url}"><s:property value="id.stdNo" /></s:a></td>

															<td align="right" id="estAprCostCenNo_<%= i %>"><s:property value="id.deptId" /></td>
															<td align="right"><s:text name="format.date"><s:param value="entryDate" /></s:text></td>
															<td align="right"><s:text name="format.currency"><s:param value="totalCost" /></s:text></td>
															<td align="right"><s:property value="entryBy" /></td>
															<td align="right"><s:property value="approvedBy" /></td>
															<td align="right"><s:property value="approvedDate" /></td>
															
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
									</s:if></td>
								</tr>
							<!-- 	<tr>
									<td><s:if test="unAttendedAppoinments != NULL">
										<table width="100%" border="0">
											<tr>
												<td class="tblMenuHeading">Un Attended Appointments</td>
											</tr>
											<tr>
												<td>
												<table class="tldFromContent" border="1">

													<tr class="tblMenuColor" height="25px">
														<td><strong>Reference Number</strong></td>
														<td><strong>Allocated By </strong></td>
														<td><strong>Allocated To</strong></td>
														<td><strong>Appointment Date</strong></td>
														<td><strong>Time Session</strong></td>
														<td><strong>Description</strong></td>
													
													</tr>
													<%int i=0; %> 
													<s:iterator value="unAttendedAppoinments">
														<tr bgcolor="#CCCCCC">
															<s:url id="page_url" namespace="/calendar/web" action="schedule">
																<s:param name="selDateFromMenu" value="%{appointmentDate}" />
                                                                <s:param name="allocateUser" value="%{allocatedTo}" />
                                                                <s:param name="estNo" value="%{referenceNo}" />
															</s:url>
															<td align="right" id="unAttApptRefNo_<%= i %>"><s:a href="%{page_url}"><s:property value="referenceNo" /></s:a></td>
														
															<td align="right"><s:property value="allocatedBy" /></td>

															<td align="right" id="unAttApptAllTo_<%= i %>"><s:property value="allocatedTo" /></td>

															<td align="right" id="unAttApptAptDate_<%= i %>"><s:text name="format.date">
															 <s:param value="appointmentDate" />
															</s:text></td>
															<td align="right"><s:property value="timeSession" /></td>
															 
															<td align="right"><s:property value="description" /></td>
																														 
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
														<td><strong>Allocated To</strong></td>
														<td><strong>Appointment Date</strong></td>
														<td><strong>Time Session</strong></td>
														<td><strong>Description</strong></td>												 


													</tr>
													<s:iterator value="failedAppoinments">
														<tr bgcolor="#CCCCCC">
															<td align="right"><s:property value="referenceNo" /></td>
															<td align="right"><s:property value="allocatedBy" /></td>

															<td align="right"><s:property value="allocatedTo" /></td>

															<td align="right"><s:text name="format.date">
																<s:param value="appointmentDate" />
															</s:text></td>
															<td align="right"><s:property value="timeSession" /></td>
															 
															<td align="right"><s:property value="description" /></td>
															 
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
								</tr> -->
								<tr>
									<td>
									
									<s:if test="rejectedList != NULL">
									<table width="100%" border="0">
										<tr>
											<td class="tblMenuHeading">Rejected Standard Estimates</td>
										</tr>
										<tr>
											<td>
											<table class="tldFromContent" border="1">

												<tr class="tblMenuColor" height="25px">	
													<td><strong>Estimate Number</strong></td>
													<td><strong>Cost Center</strong></td>	
													<td><strong>Proposer</strong></td>	
													
													<td><strong>Scheme Name</strong></td>
													<td><strong>Electorate</strong></td>
													<td><strong>Representative</strong></td>
													<td><strong>Representative Contact</strong></td>														
													<td><strong>Estimate Date</strong></td>													
													<td><strong>Standard Cost</strong></td>
												
													<td><strong>Rejected By</strong></td>
													<td><strong>Rejected Date</strong></td>
													<td><strong>Rejected Reason</strong></td>
																								
												</tr>
												<%int i=0; %>
												<s:iterator value="rejectedList">
													<tr bgcolor="#CCCCCC">													
													
														<s:url id="page_url" namespace="/estimate/web" action="estimateBS!undoReject">
														<s:param name="estNo" value="%{estimateNo}" />
														<s:param name="costCen" value="%{costCenter}" />
														</s:url>
                                                        
														<td align="right" id="tdApprestNo_<%= i %>"><s:a href="%{page_url}"><s:property value="estimateNo" /></s:a></td>
														<td align="right" id="rejcostCenNo_<%= i %>"><s:property value="costCenter" /></td>	
														<td align="right"><s:property value="proposer" /></td>
														
														<td align="right"><s:property value="schemeName" />&nbsp;<s:property value="schemeNo" /></td>	
														<td align="right"><s:property value="electorate" /></td>
														<td align="right"><s:property value="representative1" /></td>
														<td align="right"><s:property value="representativeContact1" /></td>																							
														<td align="right"><s:text name="format.date" ><s:param value="estimateDate"/></s:text> </td>
														<td align="right"><s:text name="format.currency" ><s:param value="standardCost"/></s:text></td>
													
														<td align="right"><s:property value="rejectedBy" /></td>
														<td align="right"><s:text name="format.date" ><s:param value="rejectedDate"/></s:text> </td>
														<td align="right"><s:property value="rejectedReason" /></td>
													
													</tr>
													<%i++;%>
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
													<td><strong>Work Estimate Cost</strong></td>
													
													<td><strong>Rejected By</strong></td>
													<td><strong>Rejected Date</strong></td>
													<td><strong>Rejected Reason</strong></td>
													
												</tr>
												<%int i=0; %>
												<s:iterator value="rejectedRevisedJobsList">
													<tr bgcolor="#CCCCCC">
													
														<s:url id="page_url" namespace="/job/web" action="ReviseJob">
																<s:param name="prjNo" value="%{projectNo}" />
                                                                <s:param name="costCen" value="%{id.DeptId}" />
														</s:url>
														<td align="right" id="tdPrjtNo_<%= i %>"><s:a href="%{page_url}"><s:property value="projectNo" /></s:a></td>
														<td id="rejRevCostCenNo_<%= i %>" align="right"><s:property value="id.DeptId" /></td>
														<td align="right"><s:property value="id.estimateNo" /></td>
														<td align="right"><s:text name="format.date"><s:param value="etimateDt"/></s:text> </td>
														<td align="right"><s:text name="format.currency"><s:param value="stdCost"/></s:text></td>
													
														<td align="right"><s:property value="rejctUid" /></td>
														<td align="right"><s:text name="format.date" ><s:param value="rejctDt"/></s:text> </td>
														<td align="right"><s:property value="rejectReason" /></td>											
														
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
									
									<s:if test="approveList != NULL">
										<table width="100%" border="0">
											<tr>
												<td class="tblMenuHeading">Standard Estimates Under Approval Process</td>
											</tr>
											<tr>
												<td>
												<table class="tldFromContent" border="1">

													<tr class="tblMenuColor" height="25px">
														<td width="20%"><strong>Estimate Number</strong></td>
														<td><strong>Cost Center</strong></td>
														<td><strong>Proposer</strong></td>	
															
														<td><strong>Scheme Name</strong></td>
														<td><strong>Electorate</strong></td>
														<td><strong>Representative</strong></td>
														<td><strong>Representative Contact</strong></td>			
														<td><strong>Estimate Date</strong></td>
														<td><strong>Standard Cost</strong></td>
														<td><strong>Entered By</strong></td>
														<td><strong>Fund Id</strong></td>
														
																							
													</tr>
													<%int i=0; %>
													<s:iterator value="approveList">
														<tr bgcolor="#CCCCCC">
															
                                                             <s:url id="page_url" namespace="/estimate/web" action="estimateBS!View">
                                                             <s:param name="estNo" value="%{estimateNo}" />
                                                             <s:param name="costCen" value="%{costCenter}" />
                                                             </s:url>
                                                          	
															<td align="left" id="tdApprestNo_<%= i %>"><s:a href="%{page_url}"><s:property value="estimateNo" /></s:a></td>

															<td align="left" id="estAprCostCenNo_<%= i %>"><s:property value="costCenter" /></td>
															<td align="left"><s:property value="proposer" /></td>
														
															<td align="left"><s:property value="schemeName" />&nbsp;<s:property value="schemeNo" /></td>
															<td align="left"><s:property value="electorate" /></td>
															<td align="left"><s:property value="representative1" /></td>
															<td align="left"><s:property value="representativeContact1" /></td>													
															<td align="left"><s:text name="format.date"><s:param value="estimateDate" /></s:text></td>
															<td align="left"><s:text name="format.currency"><s:param value="standardCost" /></s:text></td>
															<td align="left"><s:property value="enterBy" /></td>
															<td align="left"><s:property value="fundId" /></td>
														
														
															
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
									<td>
									<div id="accordion">
									<s:if test="needToIssuePivIIList != NULL">
										<table width="100%" border="0">
											<tr>
										
											
											
											</tr>
											<tr>
												<td>
												
												</td>
											</tr>
											<tr>
												<td>&nbsp;</td>
											</tr>
											
										</table>
									</s:if>
									</div>
								
									</td>
								</tr>
								<tr>
									<td>
									
									<s:if test="needToConfirmByCashPivIIList != NULL">
										<table width="100%" border="0">
											<tr>
											<s:if test="%{loggedInUserLevel=='DEO' || loggedInUserLevel=='TECH'}">
												<td class="tblMenuHeading">Pending Standard Estimates to be confirmed PIVII</td>
											</s:if>
											
											
											</tr>
											<tr>
												<td>
												<table class="tldFromContent" border="1">

													<tr class="tblMenuColor" height="25px">
											
														<td width="20%"><strong>PIV Number</strong></td>
														<td><strong>Cost Center</strong></td>
														<td width="20%"><strong>Estimate No</strong></td>
														<td><strong>Proposer</strong></td>		
													
														<td><strong>Scheme Name</strong></td>
														<td><strong>Electorate</strong></td>
														<td><strong>Representative</strong></td>
														<td><strong>Representative Contact</strong></td>		
														<td><strong>Approved Date</strong></td>
														<td><strong>Standard Cost</strong></td>
														<td><strong>Approved By</strong></td>
														<td><strong>Fund Id</strong></td>
														
																																								
													</tr>
													<%int i=0; %>													
													<s:iterator value="needToConfirmByCashPivIIList">
														<tr bgcolor="#CCCCCC">														
														
                                                                  <s:url id="page_url" namespace="/pivBS/web" action="confirmPivIIBS">
                                                                  <s:param name="pivNo" value="%{pivNo}" />
                                                                  <s:param name="costCen" value="%{costCenter}" />
                                                                  </s:url>
                                                          	
															<td align="right" id="tdApprestNo_<%= i %>"><s:a href="%{page_url}"><s:property value="pivNo" /></s:a></td>

															<td align="left" id="estAprCostCenNo_<%= i %>"><s:property value="costCenter" /></td>
															<td align="left"><s:property value="estimateNo" /></td>
															<td align="left"><s:property value="proposer" /></td>
															
															<td align="left"><s:property value="schemeName" />&nbsp;<s:property value="schemeNo" /></td>												
															<td align="left"><s:property value="electorate" /></td>
															<td align="left"><s:property value="representative1" /></td>
															<td align="left"><s:property value="representativeContact1" /></td>	
															<td align="left"><s:text name="format.date"><s:param value="estimateDate" /></s:text></td>
															<td align="left"><s:text name="format.currency"><s:param value="standardCost" /></s:text></td>
															<td align="left"><s:property value="enterBy" /></td>
															<td align="left"><s:property value="fundId" /></td>
															
															
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
									
									<s:if test="needToConfirmByChequePivIIList != NULL">
										<table width="100%" border="0">
											<tr>
											<s:if test="%{loggedInUserLevel=='ACCNTREV' || loggedInUserLevel=='TECH'}">
												<td class="tblMenuHeading">Pending Standard Estimates to be confirmed PIVII</td>
											</s:if>
											
											
											</tr>
											<tr>
												<td>
												<table class="tldFromContent" border="1">

													<tr class="tblMenuColor" height="25px">
											
														<td width="25%"><strong>PIV Number</strong></td>
														<td><strong>Cost Center</strong></td>
														<td width="20%"><strong>Estimate No</strong></td>
														<td><strong>Proposer</strong></td>		
													
														<td><strong>Scheme Name</strong></td>
														<td><strong>Electorate</strong></td>
														<td><strong>Representative</strong></td>
														<td><strong>Representative Contact</strong></td>		
														<td><strong>Approved Date</strong></td>
														<td><strong>Standard Cost</strong></td>
														<td><strong>Approved By</strong></td>
														<td><strong>Fund Id</strong></td>
														
																																								
													</tr>
													<%int i=0; %>													
													<s:iterator value="needToConfirmByChequePivIIList">
														<tr bgcolor="#CCCCCC">														
														
                                                                  <s:url id="page_url" namespace="/pivBS/web" action="confirmPivIIBS">
                                                                  <s:param name="pivNo" value="%{pivNo}" />
                                                                  <s:param name="costCen" value="%{costCenter}" />
                                                                  </s:url>
                                                          	
															<td align="left" id="tdApprestNo_<%= i %>"><s:a href="%{page_url}"><s:property value="pivNo" /></s:a></td>

															<td align="left" id="estAprCostCenNo_<%= i %>"><s:property value="costCenter" /></td>
															<td align="left"><s:property value="estimateNo" /></td>
															<td align="left"><s:property value="proposer" /></td>
															
															<td align="left"><s:property value="schemeName" />&nbsp;<s:property value="schemeNo" /></td>												
															<td align="left"><s:property value="electorate" /></td>
															<td align="left"><s:property value="representative1" /></td>
															<td align="left"><s:property value="representativeContact1" /></td>	
															<td align="left"><s:text name="format.date"><s:param value="estimateDate" /></s:text></td>
															<td align="left"><s:text name="format.currency"><s:param value="standardCost" /></s:text></td>
															<td align="left"><s:property value="enterBy" /></td>
															<td align="left"><s:property value="fundId" /></td>
															
															
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
									
								
								
									</td>
								</tr>
								</s:if>
								<tr>
									<td>
									
									<s:if test="%{(branchType=='CONSTRUCTION'  || branchType=='DM'  || branchType=='EM' || branchType=='AU' ||branchType=='PH' || branchType=='AC' ||branchType=='MU' || branchType=='GI' || loggedInUserLevel=='ADMIN') && (loggedInUserLevel=='DEO' 
														|| loggedInUserLevel=='EE' || loggedInUserLevel=='ES' || loggedInUserLevel=='TECH') }"> 
										<s:if test="ativeAppoinmentsListConMain != NULL">
										
									</s:if>
									</s:if></td>
								</tr>
								<tr>
									<td>
									<div id="accordion">
									
									<s:if test="%{(branchType=='CONSTRUCTION'  || branchType=='DM'  || branchType=='EM' || branchType=='PH' || branchType=='AC' || branchType=='AU' || branchType=='MU' || branchType=='GI' || loggedInUserLevel=='ADMIN') && (loggedInUserLevel=='DEO' 
														|| loggedInUserLevel=='EE' || loggedInUserLevel=='ES' || loggedInUserLevel=='TECH') }"> 
										<s:if test="ativeAppoinmentsListConMain != NULL">
										 <h3 id="hActiveAppointmentsCon">Active Appointments (<s:property value="%{ativeAppoinmentsListConMainCount}"/>) </h3>
												   <div>
													<div style="display:none;" id="activeAppointmentsCon"></div>
												   </div>
									</s:if>
									</s:if>
									
									<s:if test="%{(branchType=='CONSTRUCTION'  || branchType=='DM'  || branchType=='EM' || branchType=='MU' || branchType=='PH' || branchType=='AC' ||branchType=='AU' || branchType=='GI' || loggedInUserLevel=='ADMIN') && (loggedInUserLevel=='DEO' 
														|| loggedInUserLevel=='EE' || loggedInUserLevel=='ES' || loggedInUserLevel=='TECH') }"> 
										<s:if test="tobeEnteredDetailEstimates != NULL">
						                   <h3 id="hWorSetTobeEnteredConEstimates">Detail Estimate to be Entered (<s:property value="%{tobeEnteredDetailEstimatesCount}"/>) </h3>
												   <div>
													<div style="display:none;" id="worSetTobeEnteredConEstimates"></div>
												   </div>
									</s:if>
									</s:if>
									
									<s:if test="%{(branchType=='CONSTRUCTION'  || branchType=='DM' || branchType=='EM' || branchType=='MU' || branchType=='PH' || branchType=='AC' ||branchType=='AU' || branchType=='GI'
												|| loggedInUserLevel=='ADMIN') && (loggedInUserLevel=='DEO' || loggedInUserLevel=='ES' || loggedInUserLevel=='EE'  || loggedInUserLevel=='CE'  || loggedInUserLevel=='PE' || loggedInUserLevel=='DGM' || loggedInUserLevel=='TECH')}">
																				
									<s:if test="rejectedWorkEList != NULL">
			                             <h3 id="hRejectedWorkEstimate">Rejected Detail Estimates(<s:property value="%{rejectedWorkEListCount}"/>) </h3>
												   <div>
													<div style="display:none;" id="rejectedWorkEstimate"></div>
												   </div>
									</s:if>
									</s:if>
										
										<s:if test="%{(branchType=='CONSTRUCTION'  || branchType=='DM' || branchType=='EM' || branchType=='MU' || branchType=='PH' || branchType=='AC' ||branchType=='AU' || branchType=='GI' ) && (loggedInUserLevel=='DEO' || loggedInUserLevel=='ES' || loggedInUserLevel=='EE'  || loggedInUserLevel=='CE'  || loggedInUserLevel=='PE' || loggedInUserLevel=='DGM' || loggedInUserLevel=='TECH' || loggedInUserLevel=='ADMIN')}">
								
											<s:if test="toBeAllocatedConMainList != NULL">
											
											 <h3 id="hToBeAllocatedConsMainList">To Be Allocated(<s:property value="%{toBeAllocatedConMainListCount}"/>) </h3>
												   <div>
													<div style="display:none;" id="toBeAllocatedConsMainList"></div>
												   </div>
											
										</s:if>
										
										<s:if test="toBeAllocatedPlanningList != NULL">
											
											 <h3 id="hToBeAllocatedPLList">To Be Allocated PL List(<s:property value="%{toBeAllocatedPLListCount}"/>) </h3>
												   <div>
													<div style="display:none;" id="toBeAllocatedPLList"></div>
												   </div>
											
										</s:if>
										
										<s:if test="toBeAllocatedList != NULL">
										<h3 id="hToBeAllocatedConsMainListItself">To Be Allocated CON/MNT(<s:property value="%{toBeAllocatedListCount}"/>) </h3>
												   <div>
													<div style="display:none;" id="toBeAllocatedConsMainListItself"></div>
												   </div>
											
										</s:if>
									</s:if>	
									
									
									<s:if test="%{(smcType=='PL'  ||smcType=='CN'  ||branchType=='CONSTRUCTION'  || branchType=='DM'  || branchType=='EM' || branchType=='MU' || branchType=='PH' || branchType=='AC' ||branchType=='AU' || branchType=='GI' || loggedInUserLevel=='ADMIN') && (loggedInUserLevel=='PCE'  || loggedInUserLevel=='ADMIN' ||loggedInUserLevel=='ES'  || loggedInUserLevel=='EE'  || loggedInUserLevel=='CE'  || loggedInUserLevel=='PE' || loggedInUserLevel=='DGM' || loggedInUserLevel=='AGM' ||loggedInUserLevel=='TECH')}">
								     								  
								  
									<s:if test="tobeValidateWorkEsList != NULL">
									       
									
									       <s:if test="%{ (costCenterNo=='501.20') }">
									       
									       <s:if test="%{loggedInUserLevel=='ES' || loggedInUserLevel=='EE'  || loggedInUserLevel=='PE'}">
												
												
												<h3 id="hSetToBeValidateWEConWPSII">Detail Estimates to be Validated(<s:property value="%{tobeValidateWorkEsListCount}"/>) </h3>
												   <div>
													<div style="display:none;" id="setToBeValidateWEConWPSII"></div>
												   </div>
											</s:if>
											<s:if test="%{loggedInUserLevel=='PCE'  || loggedInUserLevel=='CE'  || loggedInUserLevel=='DGM' || loggedInUserLevel=='AGM' || loggedInUserLevel=='ADMIN'}">
												
												<h3 id="hSetToBeValidateWEConWPSII">Detail Estimates to be Approved(<s:property value="%{tobeValidateWorkEsListCount}"/>) </h3>
												   <div>
													<div style="display:none;" id="setToBeValidateWEConWPSII"></div>
												   </div>
											</s:if>
								
									       </s:if>
									       <s:else>
									       
									       <s:if test="%{loggedInUserLevel=='ES' || loggedInUserLevel=='EE'  || loggedInUserLevel=='PE'}">
												
												
												<h3 id="hSetToBeValidateWECon">Detail Estimates to be Validated(<s:property value="%{tobeValidateWorkEsListCount}"/>) </h3>
												   
												   <div>
													<div style="display:none;" id="setToBeValidateWECon"></div>
												   </div>
											</s:if>
											<s:if test="%{loggedInUserLevel=='PCE'  || loggedInUserLevel=='CE'  || loggedInUserLevel=='DGM' || loggedInUserLevel=='AGM' || loggedInUserLevel=='ADMIN'}">
												
												<h3 id="hSetToBeValidateWECon">Detail Estimates to be Approved(<s:property value="%{tobeValidateWorkEsListCount}"/>) </h3>
												   <div>
													<div style="display:none;" id="setToBeValidateWECon"></div>
												   </div>
												   <s:if test="%{ (costCenterNo=='440.20') }">
												   <s:if test="toBeAllocatedConMainList != NULL">
											
											 <h3 id="hToBeAllocatedConsMainList">To Be Allocated(<s:property value="%{toBeAllocatedConMainListCount}"/>) </h3>
												   <div>
													<div style="display:none;" id="toBeAllocatedConsMainList"></div>
												   </div>
											
										</s:if>
										</s:if>
											</s:if>
									        </s:else>
									        
									</s:if>
									<s:if test="tobeRecommendWorkEsList != NULL">
									<s:if test="%{ (costCenterNo=='501.20') }">
											 	<s:if test="%{loggedInUserLevel=='PCE' || loggedInUserLevel=='CE' }">
											 	    <h3 id="hSetToBeRecommendWECon">Detail Estimates to be Recommend(<s:property value="%{tobeRecommendWorkEsListCount}"/>) </h3>
												   <div>
													<div style="display:none;" id="setToBeRecommendWECon"></div>
												   </div>
											 	</s:if>
											 </s:if>
									
									</s:if>	
									</s:if>
									
																
									</div>
									</td>
								</tr>
								<tr>
									<td>
									
									
									
									
									</td>
								</tr>
								<tr>
											<td>
									</td>
										</tr>
										<tr>
											<td>
										<!--<s:if test="%{(branchType=='CONSTRUCTION'  || branchType=='DM'  || branchType=='EM' || branchType=='MU' || branchType=='AU' || branchType=='GI' ) && (loggedInUserLevel=='ADMIN' || loggedInUserLevel=='DEO' || loggedInUserLevel=='ES' || loggedInUserLevel=='EE'  || loggedInUserLevel=='CE'  || loggedInUserLevel=='PE' || loggedInUserLevel=='DGM' || loggedInUserLevel=='TECH')}">
								
											<s:if test="toBeAllocatedList != NULL">
												<table width="100%" border="0">
													<tr>
														<td class="tblMenuHeading">To Be Allocated CON/MNT</td>
													</tr>
													<tr>
														<td>
														<table class="tldFromContent" border="1">
		
															<tr class="tblMenuColor" height="25px">
														<td width="25%"><strong>Estimate Number</strong></td>
														<td><strong>Cost Center</strong></td>
														<td><strong>Application Type</strong></td>
														<td><strong>Service Depot Name</strong></td>
														<td><strong>Proposer</strong></td>
														<td><strong>Schema Name</strong></td>
													
														
													</tr>
													<%int i=0; %> 
													<s:iterator value="toBeAllocatedList">
														<tr bgcolor="#CCCCCC">
															<s:url id="page_url" namespace="/calendarBS/web" action="diaryEntryCMBS!directFromMain">
																
                                                                <s:param name="estNo" value="%{estimateNo}" />
															</s:url>
															<td align="left" id="unAttApptRefNo_<%= i %>"><s:a href="%{page_url}"><s:property value="estimateNo" /></s:a></td>
														
															
															
															<td align="left"><s:property value="costCenter" /></td>
															<td align="left"><s:property value="applicationType" /></td>
															
															<td align="left"><s:property value="area" /></td>
															
															<td align="left"><s:property value="proposer" /></td>
															
															<td align="left"><s:property value="schemeName" /><s:property value="schemeNo" /></td>
															
																														 
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
											</s:if>--></td>
										</tr>
												<tr>
									<td>
				
									
									</td>
								</tr>	
										
										
								<tr>
									<td>
																		
									</td>
								</tr>
								
					
								
																
								
								<tr>
									<td>
									<s:if test="%{(branchType=='CONSTRUCTION'  || branchType=='DM'  || branchType=='EM' || branchType=='MU' || branchType=='PH'  || branchType=='AC'  ||branchType=='AU'  || branchType=='GI' || loggedInUserLevel=='ADMIN') && (loggedInUserLevel=='ADMIN' ||loggedInUserLevel=='DEO' || loggedInUserLevel=='ES' || loggedInUserLevel=='EE'  || loggedInUserLevel=='CE'  || loggedInUserLevel=='PE' || loggedInUserLevel=='DGM' || loggedInUserLevel=='TECH')}">
									<s:if test="tobeApprovedWorkEsList != NULL">
									<table width="100%" border="0">
										<tr>
											<td class="tblMenuHeading">Detail Estimates Under Approval Process</td>
										</tr>
										<tr>
											<td>
											<table class="tldFromContent" border="1">

												<tr class="tblMenuColor" height="25px">	
													<td><strong>Estimate Number</strong></td>
													<td><strong>Cost Center</strong></td>													
																									
													<td><strong>Work Estimate Cost</strong></td>
													
													<td><strong>Entry By</strong></td>
													<td><strong>Entry Date</strong></td>
													<td><strong>Description</strong></td>		
																									
												</tr>
												<%int i=0; %>
												<s:iterator value="tobeApprovedWorkEsList">
													<tr bgcolor="#CCCCCC">													
													
														<s:url id="page_url" namespace="/estimate/web" action="pegSchedule!workEstiView">
														<s:param name="estNo" value="%{id.estimateNo}" />
														<s:param name="costCen" value="%{id.deptId}" />
														</s:url>
                                                        
														<td align="right" id="tdApprestNo_<%= i %>"><s:a href="%{page_url}"><s:property value="id.estimateNo" /></s:a></td>
														<td align="right" id="rejcostCenNo_<%= i %>"><s:property value="id.deptId" /></td>														
														
														<td align="right"><s:text name="format.currency" ><s:param value="stdCost"/></s:text></td>
														
														<td align="right"><s:property value="entBy" /></td>
														<td align="right"><s:text name="format.date" ><s:param value="entDt"/></s:text> </td>
													    <td align="right"><s:property value="descr" /></td>
																											
													</tr>
													<%i++;%>
												</s:iterator>
											</table>

											</td>
										</tr>
										<tr>
											<td>&nbsp;</td>
										</tr>
									</table>
									</s:if>
									</s:if>
									
									</td>
								</tr>
								<tr>
									<td>
									<s:if test="%{(branchType=='CONSTRUCTION'  || branchType=='DM'  || branchType=='EM' || branchType=='MU' || branchType=='PH' || branchType=='AC' || branchType=='AU' || branchType=='GI' || loggedInUserLevel=='ADMIN') && (loggedInUserLevel=='DEO' || loggedInUserLevel=='ES' || loggedInUserLevel=='EE'  || loggedInUserLevel=='CE'  || loggedInUserLevel=='PE' || loggedInUserLevel=='DGM' || loggedInUserLevel=='TECH')}">
									<s:if test="approvedWorkEstimateList != NULL">
										<table width="100%" border="0">
											<tr>
												<td class="tblMenuHeading">Approved Detail Estimates</td>
											</tr>
											<tr>
												<td>
												<table class="tldFromContent" border="1">

													<tr class="tblMenuColor" height="25px">
											
														<td><strong>Estimate Number</strong></td>
														<td><strong>Cost Center</strong></td>
														<td><strong>Approved Date</strong></td>
														<td><strong>Work Estimate Cost</strong></td>
														<td><strong>Approved By</strong></td>
														<td><strong>Description</strong></td>																
													</tr>
													<%int i=0; %>													
													<s:iterator value="approvedWorkEstimateList">
														<tr bgcolor="#CCCCCC">														
														
                                                                  <s:url id="page_url" namespace="/estimate/web" action="pegSchedule!workEstiView">
                                                                  <s:param name="estNo" value="%{id.estimateNo}" />
                                                                  <s:param name="costCen" value="%{id.deptId}" />
                                                                  </s:url>
                                                          	
															<td align="right" id="tdApprestNo_<%= i %>"><s:a href="%{page_url}"><s:property value="id.estimateNo" /></s:a></td>

															<td align="right" id="estAprCostCenNo_<%= i %>"><s:property value="id.deptId" /></td>
															<td align="right"><s:text name="format.date"><s:param value="aprDt3" /></s:text></td>
															<td align="right"><s:text name="format.currency"><s:param value="stdCost" /></s:text></td>
															<td align="right"><s:property value="aprUid3" /></td>
															<td align="right"><s:property value="descr" /></td>
															
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
									</s:if>
									</td>
								</tr>
								<tr>
									<td>
									<s:if test="%{(branchType=='CONSTRUCTION'  || branchType=='DM'  || branchType=='EM' || branchType=='MU' || branchType=='PH' || branchType=='AC' || branchType=='AU' || branchType=='GI' || loggedInUserLevel=='ADMIN') && (loggedInUserLevel=='DEO' || loggedInUserLevel=='ES' || loggedInUserLevel=='EE' || loggedInUserLevel=='TECH')}">
									<s:if test="rejectedRevisedJobsList != NULL">
										<table width="100%" border="0">
											<tr>
												<td class="tblMenuHeading">Rejected Revised Jobs</td>
											</tr>
											<tr>
												<td>
													<table class="tldFromContent" border="1">
														
															<tr class="tblMenuColor" height="25px">
																<td><strong>Project</strong></td>
																<td><strong>Cost Center</strong></td>
																<td><strong>Estimate Number</strong></td>													
																<td><strong>Estimate Date</strong></td>													
																<td><strong>Work Estimate Cost</strong></td>
																<td><strong>Description</strong></td>
																<td><strong>Rejected By</strong></td>
																<td><strong>Rejected Date</strong></td>
																<td><strong>Rejected Reason</strong></td>
																
															</tr>
															<%int i=0; %>
															<s:iterator value="rejectedRevisedJobsList">
																<tr bgcolor="#CCCCCC">
																
																	<s:url id="page_url" namespace="/jobBS/web" action="reviseJobBS!undoDirect">
																			<s:param name="projectNo" value="%{projectNo}" />
			                                                                <s:param name="costCenter" value="%{id.DeptId}" />
																	</s:url>
																	<td align="right" id="tdPrjtNo_<%= i %>"><s:a href="%{page_url}"><s:property value="projectNo" /></s:a></td>
																	<td id="rejRevCostCenNo_<%= i %>" align="right"><s:property value="id.DeptId" /></td>
																	<td align="right"><s:property value="id.estimateNo" /></td>
																	<td align="right"><s:text name="format.date"><s:param value="etimateDt"/></s:text> </td>
																	<td align="right"><s:text name="format.currency"><s:param value="stdCost"/></s:text></td>
																	<td align="right"><s:property value="descr.substring(0,40)" /></td>
																	<td align="right"><s:property value="rejctUid" /></td>
																	<td align="right"><s:text name="format.date" ><s:param value="rejctDt"/></s:text> </td>
																	<td align="right"><s:property value="rejectReason" /></td>											
																	
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
									</s:if>
									</td>
								</tr>
								<tr>
									<td>
									<s:if test="%{(branchType=='CONSTRUCTION'  || branchType=='DM'  || branchType=='EM' || branchType=='MU' || branchType=='PH' || branchType=='AC' ||branchType=='AU' || branchType=='GI' || loggedInUserLevel=='ADMIN') && ( loggedInUserLevel=='DEO'  || loggedInUserLevel=='ES' || loggedInUserLevel=='EE' || loggedInUserLevel=='TECH')}">
									<s:if test="toBeApprovedRevisedJobsList != NULL">
										<table width="100%" border="0">
											<tr>
												<td class="tblMenuHeading">Revised Jobs under Approval Process</td>
											</tr>
											<tr>
												<td>
												<table class="tldFromContent" border="1">

													<tr class="tblMenuColor" height="25px">
													<td><strong>Project Number</strong></td>
													<td><strong>Cost Center</strong></td>
													<td><strong>Estimate Number</strong></td>													
													<td><strong>Estimate Date</strong></td>													
													<td><strong>Work Estimate Cost</strong></td>												
													</tr>
													<%int i=0; %> 
													<s:iterator value="toBeApprovedRevisedJobsList">
														<tr bgcolor="#CCCCCC">
													
															<s:url id="page_url" namespace="/jobBS/web" action="reviseJobBS!newDirect">
																<s:param name="projectNo" value="%{projectNo}" />
                                                                <s:param name="costCenter" value="%{id.DeptId}" />
															</s:url>
														<td align="right" id="tdRevPrjtNo_<%= i %>"><s:a href="%{page_url}"><s:property value="projectNo" /></s:a></td>
														<td id="tdRevCostCenNo_<%= i %>" align="right"><s:property value="id.DeptId" /></td>
														<td align="right"><s:property value="id.estimateNo" /></td>
														<td align="right"><s:text name="format.date"><s:param value="etimateDt"/></s:text> </td>
														<td align="right"><s:text name="format.currency"><s:param value="stdCost"/></s:text></td>	
																																			
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
									</s:if>
									</td>
								</tr>
								<tr>
									<td>
									<s:if test="%{( branchType=='CONSTRUCTION'  || branchType=='DM'  || branchType=='EM' || branchType=='MU' || branchType=='PH' || branchType=='AC' ||branchType=='AU' || branchType=='GI' || loggedInUserLevel=='ADMIN') &&(loggedInUserLevel=='PCE' ||loggedInUserLevel=='EE' || loggedInUserLevel=='CE'  || loggedInUserLevel=='PE' || loggedInUserLevel=='DGM' || loggedInUserLevel=='TECH')}">
									<s:if test="tobeValidateRevisedJobsList != NULL">
										<table width="100%" border="0">
											<tr>
												<td class="tblMenuHeading">Revised Jobs to be Approved</td>
											</tr>
											<tr>
												<td>
												<table class="tldFromContent" border="1">

													<tr class="tblMenuColor" height="25px">
														<td><strong>Project Number</strong></td>
														<td><strong>Cost Center</strong></td>
														<td><strong>Estimate Number</strong></td>													
														<td><strong>Estimate Date</strong></td>													
														<td><strong>Work Estimate Cost</strong></td>													
													</tr>
													<%int i=0; %> 
													<s:iterator value="tobeValidateRevisedJobsList">
														<tr bgcolor="#CCCCCC">
													
															<s:url id="page_url" namespace="/jobBS/web" action="reviseJobBS!ReviseValidationBS">
																<s:param name="projectNo" value="%{projectNo}" />
                                                                <s:param name="costCenter" value="%{id.DeptId}" />
															</s:url>
														<td align="right" id="tdRevPrjtNo_<%= i %>"><s:a href="%{page_url}"><s:property value="projectNo" /></s:a></td>
														<td id="tdRevCostCenNo_<%= i %>" align="right"><s:property value="id.DeptId" /></td>
														<td align="right"><s:property value="id.estimateNo" /></td>
														<td align="right"><s:text name="format.date"><s:param value="etimateDt"/></s:text> </td>
														<td align="right"><s:text name="format.currency"><s:param value="stdCost"/></s:text></td>	
																																			
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
									</s:if>
									</td>
								</tr>
								<tr>
									<td>
									<s:if test="%{(branchType=='CONSTRUCTION'  || branchType=='DM'  || branchType=='EM' || branchType=='MU' || branchType=='PH' || branchType=='AC' ||  branchType=='AU' || branchType=='GI' || loggedInUserLevel=='ADMIN')&& ( loggedInUserLevel=='DEO' || loggedInUserLevel=='ES' || loggedInUserLevel=='EE'  || loggedInUserLevel=='DGM' || loggedInUserLevel=='TECH'}">
									<s:if test="revisedJobsList != NULL">
									<table width="100%" border="0">
										<tr>
											<td class="tblMenuHeading">Revised Jobs</td>
										</tr>
										<tr>
											<td>
											<table class="tldFromContent" border="1">

												<tr class="tblMenuColor" height="25px">
													<td><strong>Project Number</strong></td>
													<td><strong>Cost Center</strong></td>
													<td><strong>Estimate Number</strong></td>													
													<td><strong>Estimate Date</strong></td>													
													<td><strong>Work Estimate Cost</strong></td>																							

												</tr>
												<%int i=0; %>
												<s:iterator value="revisedJobsList">
													<tr bgcolor="#CCCCCC">
													
															<s:url id="page_url" namespace="/jobBS/web" action="reviseJobBS!undoDirect">
																<s:param name="projectNo" value="%{projectNo}" />
                                                                <s:param name="costCenter" value="%{id.DeptId}" />
															</s:url>
														<td align="right" id="tdRevPrjtNo_<%= i %>"><s:a href="%{page_url}"><s:property value="projectNo" /></s:a></td>
														<td id="tdRevCostCenNo_<%= i %>" align="right"><s:property value="id.DeptId" /></td>
														<td align="right"><s:property value="id.estimateNo" /></td>
														<td align="right"><s:text name="format.date"><s:param value="etimateDt"/></s:text> </td>
														<td align="right"><s:text name="format.currency"><s:param value="stdCost"/></s:text></td>	
																																			
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
									</s:if>
									</td>
								</tr>
									<tr>
									<td>
									<s:if test="%{(branchType=='CONSTRUCTION'  || branchType=='DM'  || branchType=='EM' || branchType=='MU' || branchType=='AU' || branchType=='PH' || branchType=='AC' ||branchType=='GI' || loggedInUserLevel=='ADMIN' || loggedInUserLevel=='TECH')&& ( loggedInUserLevel=='ACCNT' || loggedInUserLevel=='TECH') }">
									<s:if test="tobeCreateJobNos != NULL">
									<table width="100%" border="0">
										<tr>
											<td class="tblMenuHeading">to be assigned job number</td>
										</tr>
										<tr>
											<td>
											<table class="tldFromContent" border="1">

												<tr class="tblMenuColor" height="25px">
													<td><strong>Estimate Number</strong></td>
													<td><strong>Cost Center</strong></td>
																									
													<td><strong>Estimate Date</strong></td>													
													<td><strong>Detail Cost</strong></td>	
																																	

												</tr>
												<%int i=0; %>
												<s:iterator value="tobeCreateJobNos">
													<tr bgcolor="#CCCCCC">
													
															<s:url id="page_url" namespace="/estimate/web" action="jobCreatorDirectdashBoard">
																<s:param name="estNo" value="%{id.estimateNo}" />
                                                                <s:param name="costCen" value="%{id.DeptId}" />
															</s:url>
														<td align="right" id="tdRevPrjtNo_<%= i %>"><s:a href="%{page_url}"><s:property value="id.estimateNo" /></s:a></td>
														<td id="tdRevCostCenNo_<%= i %>" align="right"><s:property value="id.DeptId" /></td>
													
														<td align="right"><s:text name="format.date"><s:param value="etimateDt"/></s:text> </td>
														<td align="right"><s:text name="format.currency"><s:param value="stdCost"/></s:text></td>
														
																																			
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
									</s:if>
									</td>
								</tr>
								<tr>
									<td>
									<s:if test="%{(branchType=='CONSTRUCTION'  || branchType=='DM'  || branchType=='EM' || branchType=='MU' || branchType=='PH' || branchType=='AC' ||branchType=='AU' || branchType=='GI' || loggedInUserLevel=='ADMIN')&& ( loggedInUserLevel=='ACCNT') }">
									<s:if test="tobeCreateJobNosVSL != NULL">
									<table width="100%" border="0">
										<tr>
											<td class="tblMenuHeading">to be assigned job number-First Fifty</td>
										</tr>
										<tr>
											<td>
											<table class="tldFromContent" border="1">

												<tr class="tblMenuColor" height="25px">
													<td><strong>Estimate Number</strong></td>
													<td><strong>Cost Center</strong></td>
																									
													<td><strong>Estimate Date</strong></td>													
													<td><strong>Detail Cost</strong></td>	
																																			

												</tr>
												<%int i=0; %>
												<s:iterator value="tobeCreateJobNosVSL">
													<tr bgcolor="#CCCCCC">
													
															<s:url id="page_url" namespace="/estimate/web" action="jobCreatorDirectdashBoard">
																<s:param name="estNo" value="%{id.estimateNo}" />
                                                                <s:param name="costCen" value="%{id.DeptId}" />
															</s:url>
														<td align="right" id="tdRevPrjtNo_<%= i %>"><s:a href="%{page_url}"><s:property value="id.estimateNo" /></s:a></td>
														<td id="tdRevCostCenNo_<%= i %>" align="right"><s:property value="id.DeptId" /></td>
													
														<td align="right"><s:text name="format.date"><s:param value="etimateDt"/></s:text> </td>
														<td align="right"><s:text name="format.currency"><s:param value="stdCost"/></s:text></td>
														
																																			
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
									</s:if>
									</td>
								</tr>
								<tr>
									<td>
									<s:if test="%{(branchType=='CONSTRUCTION'  || branchType=='DM'  || branchType=='EM' || branchType=='MU' || branchType=='PH' || branchType=='AC' ||branchType=='AU' || branchType=='GI' || loggedInUserLevel=='ADMIN') && (loggedInUserLevel=='DEO' || loggedInUserLevel=='ES' || loggedInUserLevel=='EE'  || loggedInUserLevel=='DGM'  || loggedInUserLevel=='CE')}">
									<s:if test="approvedRevisedJobsList != NULL">
									<table width="100%" border="0">
										<tr>
											<td class="tblMenuHeading">Approved Revised Jobs</td>
										</tr>
										<tr>
											<td>
											<table class="tldFromContent" border="1">

												<tr class="tblMenuColor" height="25px">
													<td><strong>Project Number</strong></td>
													<td><strong>Cost Center</strong></td>
													<td><strong>Estimate Number</strong></td>													
													<td><strong>Estimate Date</strong></td>													
													<td><strong>Work Estimate Cost</strong></td>																							

												</tr>
												<%int i=0; %>
												<s:iterator value="approvedRevisedJobsList">
													<tr bgcolor="#CCCCCC">
													
															<s:url id="page_url" namespace="/jobBS/web" action="reviseJobBS!newDirect">
																<s:param name="projectNo" value="%{projectNo}" />
                                                                <s:param name="costCenter" value="%{id.DeptId}" />
															</s:url>
														<td align="right" id="tdRevPrjtNo_<%= i %>"><s:a href="%{page_url}"><s:property value="projectNo" /></s:a></td>
														<td id="tdRevCostCenNo_<%= i %>" align="right"><s:property value="id.DeptId" /></td>
														<td align="right"><s:property value="id.estimateNo" /></td>
														<td align="right"><s:text name="format.date"><s:param value="etimateDt"/></s:text> </td>
														<td align="right"><s:text name="format.currency"><s:param value="stdCost"/></s:text></td>	
																																			
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
									</s:if>
									</td>
								</tr>
								
								
								
								
							</tbody>
						</table>

									</td>
								</tr>
                                <tr>
                                    <td id="tdFooter">
                                        <table id="tdFooter" width="100%" border="0" align="center" bgcolor="#D4E9F8" cellpadding="0" cellspacing="0" style="border-collapse:collapse">
                                            <tbody>
                                            	<tr>
                                                    <td class="tdMsgStyle"><s:property value="errorMessage"></s:property></td>
                                                </tr>
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
