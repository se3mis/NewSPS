<%--
    Document   : login
    Created on : Jul 15, 2009, 10:31:01 AM
    Author     : Dileepa Waduge
--%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/jsp/include.jsp" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
        <link rel="stylesheet" type="text/css" href="../../style/style.css" />

        <s:head />
    </head>
    <body >
        <table id="tblOuter" class="tblOuter"  >
            <tbody>
                <tr>
                    <td>
                        <table id="tblInner" class="tblInner" >
                            <tbody>
                                <tr>
                                    <td id="tdHeader" >
                                        <table id="tblHeader" class="tblHeader" cellpadding="0" cellspacing="0" >
                                            <tbody>
                                                <tr>
                                                    <td width="736px" height="87px"><img src="../../image/Heading_Left.PNG" width="736" height="87" alt="Heading_Left"/>
                                                    </td>
                                                    <td width="*" bgcolor="#226B9C"></td>
                                                    <td width="198px" height="87px"><img src="../../image/Heading_Right.PNG" width="198" height="87" alt="Heading_Right"/>
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
                                    <td>

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
                                                    <td><s:form action="test123" method="POST" validate="true" theme="simple">
                                                        <table width="100%"  class="tldFromContent">
                                                            <thead>
                                                                <tr>
                                                                    <th width="10%"></th>
                                                                    <th width="15%"></th>
                                                                    <th width="20%"></th>
                                                                    <th width="*"></th>
                                                                </tr>
                                                            </thead>
                                                            <tbody>
                                                                <tr>
                                                                    <td><s:label value="User Name"></s:label></td>
                                                                    <td><s:textfield name="userName" size="10" ></s:textfield></td>
                                                                    <td class="tdMsgStyle"><s:property  value="msgUserId"></s:property></td>
                                                                    <td></td>
                                                                </tr>
                                                                <tr>
                                                                    <td><s:label value="Password"></s:label></td>
                                                                    <td><s:password name="password" size="10" ></s:password></td>
                                                                    <td class="tdMsgStyle"><s:property value="msgPassword"></s:property></td>
                                                                    <td></td>
                                                                </tr>

                                                                <tr>
                                                                    <td colspan="3">
                                                                            <s:submit theme="simple" value="enter" method="enter" id="btnNEnter"></s:submit>
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
                                                    <td>This is footer</td>
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













