<%@ taglib prefix="s" uri="/struts-tags"%>


		
				<!-- Materials list -->
		<table class="tldFromContent"  border="1" width="100%">
		<tr>
			<th>&nbsp;</th>
			<th>Material Code</th>
			<th>Material Name</th>
			<th>UOM</th>
			<th>Unit Price</th>
			<th>Est. Qty.</th>
			<th>Est. Cost</th>
		</tr>
		<s:if test="defaultMatList!=null">
			<s:iterator value="defaultMatList" status="matStatus">
				<tr>
					<td><s:checkbox name="chkDefMatCode" id="chkDefMatCode%{top[0]}" fieldValue="%{top[0]}" theme="simple"></s:checkbox></td>
					<td><s:property value="top[0]" /></td>
					<td><s:property value="top[1]" /></td>
					<td><s:property value="top[2]" /></td>
					<td><s:property value="top[3]" /></td>
					<td><s:textfield name="txtDefQty"  id="txtDefQty%{top[0]}" value="%{top[4]}"   theme="simple" onkeyup="calculateMatCost('Def','%{top[0]}','%{top[3]}')" ></s:textfield></td>
					<td><s:textfield name="txtDefMatCost" readonly="true"  id="txtDefMatCost%{top[0]}"  theme="simple" value="%{top[5]}"></s:textfield></td>
				</tr>
			</s:iterator>
		</s:if>
		</table>
		
		

