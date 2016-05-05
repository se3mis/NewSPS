<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>

<s:if test="nplMatList!=null">

<table width="85%" bgcolor="#93C9FF">
				<tr>
					<td><font color="#006BD7"><b>NPL Materials</br></br></b></font></td>
				</tr>
				<tr>
					<td>
						<s:a id="addNPLLink" href="javascript:add_npl_costs()">Add to Resource List</s:a>
						&nbsp;&nbsp;&nbsp;<a href="javascript:hide_list()">Hide List</a>
						
						
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
								Mat Code
								</th>
								<th>
								Mat Name
								</th>
								<th>
								UOM
								</th>
								<th>
								Unit Price
								</th>
							</tr>
							<s:iterator value="nplMatList" status="matStatus">
								<tr>
									<s:set  var="up" value="top[3]"/>
									<td><s:checkbox name="chkNplMatCode" id="chkNplMatCode%{top[0]}" fieldValue="%{#matStatus.index}###%{top[0]}###%{top[2]}###%{top[3]}###%{top[1]}" theme="simple"></s:checkbox></td>
									<td><s:property value="top[0]" /></td>
									<td>
									<s:property value="top[1]" />
									</td>
									<td>
									<s:property value="top[2]" />
									</td>
									<td align="right"><s:property  value="top[3]" />
									
									</td>												
								</tr>
							</s:iterator>
							
						</table>
					</td>
				</tr>
				
				<tr>
					<td>
						<s:a id="addNPLLink" href="javascript:add_npl_costs()">Add to Resource List</s:a>
						&nbsp;&nbsp;&nbsp;<a href="javascript:hide_list()">Hide List</a>
						
						
					</td>
				</tr>
				
			</table>
	
			</s:if>
			

