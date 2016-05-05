<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>

<html>
  <head>
    <title>Material List</title>
    <script type="text/javascript" src="../../script/estimate.js"></script>
    <script type="text/javascript">
    function testing(){
    	alert("sdljsdkjfnsdjkfndf");
    }
	</script>
    <sx:head parseContent="true"/>

  </head>
  <body>
    <h1>Add New Material List</h1>
    <s:label name="stateName" value="Select State Name:" />
    <sx:autocompleter  list="state" name="StateName" onblur="javascript:{this.value = this.value.toUpperCase(); }" onselect="testing()"/>
  </body>
</html>