<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>

<s:if test="otherCostList!=null">

<table width="85%" bgcolor="#93C9FF">
				<tr>
					<td><font color="#006BD7"><b>Other Resources</br></br></b></font></td>
				</tr>
				<tr>
					<td>
						<s:a id="addCostLink" href="javascript:add_other_costs()">Add to Resource List</s:a>&nbsp;&nbsp;&nbsp;<a href="javascript:hide_list()">Hide List</a>
						
						
					</td>
				</tr>
				<tr>
					<td>
						<!-- Other Material list table -->
						<table width="100%"  class="tldFromContent"  border="1">
							<tr>
								<th>
								&nbsp;
								</th>
								<th>
								Resource Code
								</th>
								<th>
								Resource Type
								</th>
								<th>
								Resource Category
								</th>
								<th>
								Resource Name
								</th>
								<th>
								Est. Cost
								</th>
							</tr>
							<s:iterator value="otherCostList" status="costStatus">
								<tr>
									<td><s:checkbox name="chkResType" id="chkResType%{top[0]}" fieldValue="%{#costStatus.index}###%{top[0]}###%{top[1]}###%{top[2]}###%{top[3]}" theme="simple"></s:checkbox></td>
									<td><s:property value="top[0]" /></td>
									<td><s:property value="top[1]" /></td>
									<td><s:property value="top[2]" /></td>
									<td><s:property value="top[3]" /></td>
									<td><s:textfield name="txtOtherCost"  id="txtOtherCost%{top[0]}"  value="%{top[4]}"  theme="simple"  cssStyle="text-align:right" onkeyup="numericValidate(this,event);" onblur="formatValue(this)"  ></s:textfield></td>
															
								</tr>
							</s:iterator>
							
						</table>
					</td>
				</tr>
				
				<tr>
					<td>
						<s:a id="addCostLink" href="javascript:add_other_costs()">Add to Resource List</s:a>&nbsp;&nbsp;&nbsp;<a href="javascript:hide_list()">Hide List</a>
						
						
					</td>
				</tr>
				
			</table>
	
			</s:if>
			

