<%@ taglib prefix="s" uri="/struts-tags"%>

<s:if test="labourList!=null">

<table width="85%"  bgcolor="#93C9FF">
				<tr>
					<td><font color="#006BD7"><b>Materials</br></br></b></font></td>
				</tr>
				<tr><td><s:a id="addLabourLink" href="javascript:add_labourCodes('add')">Add to Resource List</s:a>&nbsp;&nbsp;&nbsp;<a href="javascript:hide_list()">Hide List</a></td></tr>
				<tr>
					<td>
						<!-- Other Material list table -->
						<table width="100%" class="tldFromContent"  border="1">
							<tr>
								<th>
								&nbsp;
								</th>
								<th>
								Labour Code
								</th>
								<th>
								Description
								</th>
								
								<th>
								Unit Labour Hrs.
								</th>
							</tr>
							<s:iterator value="labourList" status="labourStatus">
								<tr>
									<td><s:checkbox name="chkLabCode" id="chkLabCode%{labourCode}" fieldValue="%{#labourStatus.index}###%{labourCode}###%{unitLabourHrs}###%{unitPrice}###%{labourName}" theme="simple"></s:checkbox></td>
									<td><s:property value="labourCode" /></td>
									<td>
									<s:property value="labourName" />
									</td>
									
									
									</td>
									
									<td align="right"><s:property  value="unitLabourHrs" />
									
									</td>							
								</tr>
							</s:iterator>
							
						</table>
					</td>
				</tr>
				
				<tr><td><s:a id="addLabourLink" href="javascript:add_labourCodes('add')">Add to Resource List</s:a>&nbsp;&nbsp;&nbsp;<a href="javascript:hide_list()">Hide List</a></td></tr>
			</table>
	
			</s:if>
			
			
