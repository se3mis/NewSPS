<%--
    Document   : selectCostCenter
    Created on : Jul 14, 2009, 9:55:53 AM
    Author     : Dileepa Waduge
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Select Cost Center</title>
        <link rel="stylesheet" type="text/css" href="../../style/style.css" />
    </head>
    <body >
        <table id="tblOuter" class="tblOuter">
            <tbody>
                <tr>
                    <td>
                        <table id="tblInner" class="tblInner">
                            <tbody>
                                <tr>
                                    <td id="tdHeader" >
                                        <table id="tblHeader" class="tblHeader" border="0" align="center" cellpadding="0" cellspacing="0" >
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
                                                    <td></td>
                                                    <td class="tdWelcomeLabel">Welcome</td>
                                                    <td class="tdWelcomeUser"><s:property value="loggedInUserId"></s:property></td>
                                                    <td class="tdLogOut"><a href="<s:url action="logout"/>">Logout</a> </td>
                                                </tr>
                                            </tbody>
                                        </table>

                                    </td>
                                </tr>
                                <tr>
                                    <td >
                                    </td>
                                </tr>
                                <tr>
                                    <td id="tdContent">
                                        <table class="tblContent" border="1" cellpadding="0" cellspacing="0" >
                                            <thead>
                                                <tr>
                                                    <th></th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <tr>
                                                    <td><s:form action="selectCostCenter" method="POST" validate="true" theme="simple">
                                                            <table width="100%"  class="tldFromContent">
                                                                <thead>
                                                                    <tr>
                                                                        <th width="10%"></th>
                                                                        <th width="15%"></th>
                                                                        <th width="*"></th>

                                                                    </tr>
                                                                </thead>
                                                                <tbody>
                                                                	<tr>
																	<td class="tdHeadings">COST CENTER/</td>
																	<td class="tdHeadings">TYPE</td>
																	<td class="tdMsgStyle"><s:property value="errorMessage"></s:property></td>
																	<td></td>
																	</tr>
                                                                    <tr>
                                                                        <td><s:label value="Cost Center"></s:label></td>
                                                                        <td><s:textfield  name="costCenterNo" id="txfdCostCenterNo"/></td>
                                                                        <td><s:property value="message"></s:property></td>
                                                                        <td></td>
                                                                    </tr>
                                                                   	<!--   <tr>
                                                                        <td><s:label value="Year"></s:label></td>
                                                                        td><s:property value="message"></s:property></td>
                                                                    </tr>
                                                                     -->
                                                                  
                                                                    <tr>
                                                                   
																		     <td><s:label value="Job Type"></s:label></td>
																		   
                                                                       
                                                                        <td>
                                                                        
                                                                        
                                                                            
                                                                        
	                                                                        <s:if test="%{branchType=='COMMERCIAL' }"> 
																				
                                                                        		<s:select  name="smcType" headerKey="1" list="#{'DE':'--Please Select--','BS':'Bulk Supply','RE':'RE Cost Paid(DCB/PCB)','RI':'RE-Projects','LN':'Land','PL':'Planing & Development','AC':'AC Unit','CP':'Other','CN':'Construction/CSC','DM':'Maintenance','EM':'Energy Management','VSL':'VSL','PF':'PCF Jobs','DB':'DCB Jobs
                                                                        		','R8':'RE8 Jobs ','AC':'AC Unit '}"/>
                                                                        
															   	 			</s:if>	
															   	 			
															   	 																		   	 			
															   	 			<s:elseif test="%{  costCenterNo=='430.10' }"> 
																				
                                                                        		<s:select  name="smcType" headerKey="1" list="#{'EM':'Energy Management'}"/>
                                                                        
															   	 			</s:elseif>	
															   	 			<s:elseif test="%{  costCenterNo=='501.40' }"> 
																				
                                                                        		<s:select  name="smcType" headerKey="1" list="#{'EM':'Energy Management'}"/>
                                                                        
															   	 			</s:elseif>
															   	 			<s:elseif test="%{  costCenterNo=='510.80' }"> 
																				
                                                                        		<s:select  name="smcType" headerKey="1" list="#{'EM':'Energy Management'}"/>
                                                                        
															   	 			</s:elseif>
															   	 			<s:elseif test="%{  costCenterNo=='536.40'  || costCenterNo=='535.40'  ||costCenterNo=='534.40'  || costCenterNo=='433.00'  || costCenterNo=='434.60' || costCenterNo=='430.90'"> 
																				
                                                                        		<s:select  name="smcType" headerKey="1" list="#{'AU':'Area Unit'}"/>
                                                                        
															   	 			</s:elseif>	
															   	 			
															   	 																	   	 			
															   	 			<s:elseif test="%{  costCenterNo=='975.30' }"> 
																				
                                                                        		<s:select  name="smcType" headerKey="1" list="#{'AU':'Project Unit'}"/>
                                                                        
															   	 			</s:elseif>	
															   	 			
															   	 		
															   	 			<s:elseif test="%{ costCenterNo=='520.00' || costCenterNo=='440.00' || costCenterNo=='420.00'}"> 
																				
                                                                        		<s:select  name="smcType" headerKey="1" list="#{'DE3':'--Please Select--','CN':'Construction','DM':'Maintenance','VSL':'VSL'}"/>
                                                                        
															   	 			</s:elseif>	
															   	 			<s:elseif test="%{ costCenterNo=='543.00' || costCenterNo=='543.10' || costCenterNo=='543.20' || costCenterNo=='543.40' ||  costCenterNo=='430.80'}"> 
																				
                                                                        		<s:select  name="smcType" headerKey="1" list="#{'TCI':'TCI','TPJ':'TPJ','TPV':'TPV','TPL':'TPL','TPC':'TPC','MNT':'MNT'}"/>
                                                                        
															   	 			</s:elseif>	
															   	 			 <s:elseif test="%{costCenterNo=='540.00' ||  
															   	 			 					costCenterNo=='543.00' || costCenterNo=='543.10' || 
															   	 			 					costCenterNo=='543.20' || costCenterNo=='543.40' || 
															   	 			 					costCenterNo=='540.14' || costCenterNo=='540.10' ||
															   	 			 					costCenterNo=='540.12' || costCenterNo=='540.70' ||
															   	 			 					costCenterNo=='540.70' || costCenterNo=='545.05' || 
															   	 			 					costCenterNo=='545.10' || costCenterNo=='545.20' || 
															   	 			 					costCenterNo=='545.21' || costCenterNo=='545.22' || 
															   	 			 					costCenterNo=='545.23' || costCenterNo=='544.00' || 
															   	 			 					costCenterNo=='540.30' || costCenterNo=='544.10' || 
																								costCenterNo=='544.21' || costCenterNo=='544.22' || 
																								costCenterNo=='544.23' || costCenterNo=='544.24' ||
																								costCenterNo=='544.30' || costCenterNo=='544.31' || 
																								costCenterNo=='544.32' || costCenterNo=='540.20' }">
																		    	
																		    	<s:select  name="smcType" headerKey="1" list="#{'DE1':'--Please Select--','GI':'Government Installation','CN':'Construction','PL':'Planning','BS':'Bulk Supply','OP':'Operation Unit','DM':'Maintenance','DE2':'Project','DE3':'Other'}"/>
                                                                        	
																		    </s:elseif>
																		   <s:elseif test="%{branchType=='AU'}">
																		    		<s:select  name="smcType" headerKey="1" list="#{'AU':'Area Unit'}"/>
																		    </s:elseif>
																		    <s:elseif test="%{branchType=='PH'}">
																		    		<s:select  name="smcType" headerKey="1" list="#{'PH':'PHM Unit'}"/>
																		    </s:elseif>
																		    <s:elseif test="%{branchType=='AC'}">
																		    		<s:select  name="smcType" headerKey="1" list="#{'AC':'AC Unit'}"/>
																		    </s:elseif>
																		    <s:elseif test="%{branchType=='CONSTRUCTION'}">
																		    		<s:select  name="smcType" headerKey="1" list="#{'CN':'Construction'}"/>
																		    </s:elseif>
																		    <s:elseif test="%{costCenterNo=='540.14' || costCenterNo=='540.10' || costCenterNo=='430.70'}">
																		    		<s:select  name="smcType" headerKey="1" list="#{'PL':'Planning'}"/>
																		    </s:elseif>
																		     <s:elseif test="%{costCenterNo=='540.12' }">
																		    		<s:select  name="smcType" headerKey="1" list="#{'BS':'Bulk'}"/>
																		    </s:elseif>
																		     <s:elseif test="%{costCenterNo=='540.70' }">
																		    		<s:select  name="smcType" headerKey="1" list="#{'DE':'Project'}"/>
																		    </s:elseif>
																		    <s:elseif test="%{costCenterNo=='545.05' || costCenterNo=='545.10' || costCenterNo=='545.20' || costCenterNo=='545.21' || costCenterNo=='545.22' || costCenterNo=='545.23' || costCenterNo=='430.50'}">
																		    		<s:select  name="smcType" headerKey="1" list="#{'OP':'Operation Unit'}"/>
																		    </s:elseif>
																		     <s:elseif test="%{ branchType=='DM' || costCenterNo=='510.30'  || costCenterNo=='550.30' || costCenterNo=='520.30'   || costCenterNo=='420.30'  || costCenterNo=='440.30' || costCenterNo=='450.30' || costCenterNo=='544.00' || costCenterNo=='540.30' || costCenterNo=='544.10' || 
																								costCenterNo=='544.21' || costCenterNo=='544.22' || costCenterNo=='544.23' || costCenterNo=='544.24' ||
																								costCenterNo=='544.30' || costCenterNo=='544.31' || costCenterNo=='544.32' || costCenterNo=='430.30' || costCenterNo=='430.35' }">
																		    		<s:select  name="smcType" headerKey="1" list="#{'DM':'Maintenance'}"/>
																		    </s:elseif>
																		    <s:elseif test="%{costCenterNo=='521.00'  || costCenterNo=='521.20'  || costCenterNo=='521.40'  || costCenterNo=='521.10' || costCenterNo=='514.20'  || costCenterNo=='514.10' || costCenterNo=='514.30' || costCenterNo=='514.40' || costCenterNo=='514.50' || costCenterNo=='514.80' || costCenterNo=='514.90' || costCenterNo=='514.00' || costCenterNo=='521.10' || costCenterNo=='521.20' ||
																									costCenterNo=='521.30' || costCenterNo=='521.40' || costCenterNo=='521.50' || costCenterNo=='522.00'  ||
																									costCenterNo=='522.10' || costCenterNo=='522.20' ||
																									costCenterNo=='522.40' || costCenterNo=='522.50' || costCenterNo=='522.60' ||
																									costCenterNo=='523.10' || costCenterNo=='523.40' || costCenterNo=='523.00' ||
																									costCenterNo=='523.60' || costCenterNo=='523.70' ||
																									costCenterNo=='524.10' || costCenterNo=='524.20' || costCenterNo=='524.00' ||
																									costCenterNo=='524.30' || costCenterNo=='524.40' || costCenterNo=='524.50' ||
																									costCenterNo=='525.10' || costCenterNo=='525.20' || costCenterNo=='525.30' || costCenterNo=='525.00'  ||
																									costCenterNo=='526.10' || costCenterNo=='526.20' || costCenterNo=='526.30' || costCenterNo=='526.40' || costCenterNo=='526.00' || costCenterNo=='527.10' || costCenterNo=='527.20' || costCenterNo=='527.30' || costCenterNo=='527.40'}">
																		    		<s:select  name="smcType" headerKey="1" list="#{'VSL':'VSL','DE':'AUG'}"/>
																		    </s:elseif>
																		    
																		    
																	    </td>
                                                                        <td><s:property value="message"></s:property></td>
                                                                        <td></td>
                                                                    </tr>
                                                                 
                                                                    <tr>
                                                                        <td colspan="3">
                                                                            <s:submit theme="simple" value="Enter" ></s:submit>
                                                                            <s:reset theme="simple" align="right" value="Clear" />
                                                                        </td>
                                                                    </tr>
                                                                </tbody>
                                                            </table>
                                                        </s:form>




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
