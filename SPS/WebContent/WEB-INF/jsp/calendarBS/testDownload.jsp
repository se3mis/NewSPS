<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
 
<body>
<h1>Struts 2 download file example</h1>
<s:form action="download">
<s:submit value="Generate" method="generate"></s:submit>
<s:submit value="Exit" method="close"></s:submit>
<s:hidden id="hid_FileName" name="hid_FileName"></s:hidden>
<!--<s:url id="fileDownload" namespace="/calendar/web" action="download" ></s:url>-->
<!-- -->
<!--<h4> <s:a href="%{fileDownload}">Download file</s:a>-->
<!--</h4>-->
 </s:form>
</body>
</html>