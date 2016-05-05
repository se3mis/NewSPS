<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript" src="../../script/estimate.js"></script>
<sx:head parseContent="true"/>
</head>
<body>
    <h1>Add New Material List</h1>
    <s:label name="stateName" value="Select State Name:" />
    <sx:autocompleter  list="state" name="StateName" onblur="javascript:{this.value = this.value.toUpperCase(); }" onselect="testing()" onclick="testing()" ondblclick="testing()" onfocus="testing()"/>
  	<s:textfield name="estimatedQuantity" id="txfdEstimatedQuantity"  size="6" cssStyle="text-align: right;" onclick="testing()"/>
</body>
</html>