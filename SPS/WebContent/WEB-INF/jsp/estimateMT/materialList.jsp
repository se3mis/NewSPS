<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>NEW ESTIMATION</title>
<link rel="stylesheet" type="text/css" href="../../style/style.css" />
<script type="text/javascript" src="../../script/estimate.js"> </script>
<script type="text/javascript" src="../../script/javascript.js"> </script>
<script>
function pageClose()
{
	var isClose = document.getElementById("isMatListClose").value;
	if(isClose!=null && isClose=='Y')
	{
		window.opener.document.frmEstimate.submit();
		window.close();
	}
}
function show_details() 
{
	dojo.event.topic.publish("show_detail");
}
</script>
<s:head />
</head>
<body onload="pageClose()">
<table >
	<tbody>
		<tr>
			<td>
			header
			</td>
		</tr>
		<tr>
			<td align="center">
			<s:form name="frmTable" action="estimate" theme="simple">
			<s:hidden id="isMatListClose" name="isMatListClose"></s:hidden>
			<table width="95%">
				<tr>
					<td>
					Default Materials
					</td>
				</tr>
				<tr>
					<td>
						<!-- Deafault Material list table -->
						<table width="100%" border="1">
							<tr>
								<td>
								&nbsp;
								</td>
								<td>
								Mat Code
								</td>
								<td>
								Mat Name
								</td>
								<td>
								UOM
								</td>
								<td>
								Unit Price
								</td>
							</tr>
							<s:iterator value="defaultMatList" status="matStatus">
								<tr>
									<td><s:checkbox name="chkDefMatCode" id="chkDefMatCode%{top[0]}" fieldValue="%{top[0]}###%{top[2]}###%{top[3]}###%{top[1]}"></s:checkbox></td>
									<td><s:property value="top[0]" /></td>
									<td>
									<s:property value="top[1]" />
									</td>
									<td>
									<s:property value="top[2]" />
									</td>
									<td>
									<s:property value="top[3]" />
									</td>							
								</tr>
							</s:iterator>
							
						</table>
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td>Other Materials</td>
				</tr>
				<tr>
					<td>
						<!-- Other Material list table -->
						<table width="100%" border="1">
							<tr>
								<td>
								&nbsp;
								</td>
								<td>
								Mat Code
								</td>
								<td>
								Mat Name
								</td>
								<td>
								UOM
								</td>
								<td>
								Unit Price
								</td>
							</tr>
							<s:iterator value="matList" status="matStatus">
								<tr>
									<td><s:checkbox name="chkMatCode" id="chkMatCode%{top[0]}" fieldValue="%{top[0]}###%{top[2]}###%{top[3]}###%{top[1]}"></s:checkbox></td>
									<td><s:property value="top[0]" /></td>
									<td>
									<s:property value="top[1]" />
									</td>
									<td>
									<s:property value="top[2]" />
									</td>
									<td>
									<s:property value="top[3]" />
									</td>							
								</tr>
							</s:iterator>
							
						</table>
					</td>
				</tr>
				<tr>
					<td><s:submit theme="simple" value="Add to Material List" method="addMaterial" id="btnAddMat" onclick="javascript:show_details();return false;" ></s:submit></td>
				</tr>
				
			</table>
			</s:form>
			</td>
		</tr>
		<tr>
			<td>
			footer
			</td>
		</tr>
	</tbody>
</table>
</body>
</html>