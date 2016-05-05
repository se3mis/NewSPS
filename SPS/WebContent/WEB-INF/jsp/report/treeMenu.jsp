<%@ include file="/WEB-INF/jsp/include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>

</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<s:head />
<sx:head />
<script language="JavaScript" type="text/javascript">

</script> 

</head>

<body>

<s:form  action="para" method="POST" theme="simple">
	<table class="tblWelcome" border="0" cellpadding="0" cellspacing="0">
    	<thead>
        	<tr>
            	<th width="30%"></th>
            	<th width="*"></th>
            </tr>
       	</thead>
        <tbody>
        	
            <tr class="tldFromContent">
            	<td>
            		
            		
	
		<sx:tree rootNode="%{rootNode}" 
			name="selectedNode" nodeIdProperty="id"    
			nodeTitleProperty="name" childCollectionProperty="children" selectedNotifyTopics="treeSelected"  expandIconSrcPlus="../../image/plus.png" expandIconSrcMinus="../../image/minus.png" 
			 iconHeight="25" iconWidth="25" showRootGrid="false"  
			 gridIconSrcC="../../image/child.jpg" blankIconSrc="../../image/child.jpg" gridIconSrcV="../../image/vertical.png" >
				</sx:tree>
		
	     
		
		
	
		<s:hidden id="reportName" name="reportName" value="rgertert"></s:hidden>
		
            	</td> 
            	

            </tr>
           
        </tbody>
    </table>
	
</s:form>
	
</body>
</html>