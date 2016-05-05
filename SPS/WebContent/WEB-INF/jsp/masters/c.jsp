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
                                                    <td width="736px" height="70px"><img  src="../../image/Heading_Left.PNG" width="736" height="70" alt="Heading_Left"/>
                                                    </td>
                                                    <td width="*" bgcolor="#226B9C"></td>
                                                    <td width="198px" height="70px"><img src="../../image/Heading_Right.PNG" width="198" height="70" alt="Heading_Right"/>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td height="15px" bgcolor="#000033"></td>
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
                                                    <td><a href="<s:url action="logout"/>">Logout</a> </td>
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
                                                                        <td><s:label value="Cost Center"></s:label></td>
                                                                        <!-- td><!-- s:select  name="costCenter" headerKey="01" list="costCenterList"/--></td-->
                                                                        <td><s:textfield  name="costCenterNo" id="txfdCostCenterNo"/></td>
                                                                        <td><s:property value="message"></s:property></td>
                                                                    </tr>
                                                                   	<!--   <tr>
                                                                        <td><s:label value="Year"></s:label></td>
                                                                        td><s:property value="message"></s:property></td>
                                                                    </tr>
                                                                     -->
                                                                    <tr>
                                                                   
                                                                        <td><s:label value="SMC Type"></s:label></td>
                                                                        <td><s:select  name="smcType" headerKey="1" list="#{'NC':'NC','CR':'CR','TM':'TM','MT':'ML'}"/></td>
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
                                        <table id="tblFooter" class="tblFooter" border="1"  cellpadding="0" cellspacing="0" >
                                            <tbody>
                                                <tr>
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
