<%@ include file="/WEB-INF/jsp/include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<s:head />
<sx:head />
</head>

<body>
fwertertyrtyrtyrtyrtyrtyrty
<s:form  action="MainMenu" method="POST">
	<sx:tree rootNode="%{rootNode}" 
		name="selectedNode" nodeIdProperty="id"    
		nodeTitleProperty="name" childCollectionProperty="children"></sx:tree>
	</s:form>
<iframe name="get_content" id="get_content" src="about:blank" width="1px" height="1px"  scrolling="no"></iframe>
<iframe src ="mainHelp" width="100%" height="300" name="i" id="i">
  <p>Your browser does not support iframes.</p>
</iframe>
<br />
<div id="m">
	<div class="navcontainer">
		<ul id="navlist">
			<li><a href="mainHelp" title="Total Hits" target="i">Total Hits/Link</a></li>
			<li><a href="test2.asp" title="AdvaMed Code of Ethics"  target="i">AdvaMed Code of Ethics</a></li>
		</ul>
	</div>
	<div id="mainDiv"></div>
</div>
<br />
	
</body>
</html>