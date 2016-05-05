<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>

<!-- Materials list -->
<s:hidden id="isRemoveFinish" name="isRemoveFinish"></s:hidden>
<s:if test="selectLabList!=null">
<table class="tldFromContent"  border="1" width="100%">
	<tr>
		<s:if test="%{isUndoReject != 'true'}">
			<th width="2%">&nbsp;</th>
		</s:if>
		<th>Labour Code</th>
		<th>Description</th>
		<th>Unit Labour Hrs.</th>
		<th>Labour Rate</th>
		<th>Qty.</th>
		<th>Total Labour Hrs.</th>
		<th>Labour Cost</th>
	</tr>
	<s:set name="calcTotalAmt" value="0"/>
	<s:set name="calcTotalHrs" value="0"/>
	<s:iterator value="selectLabList" status="matStatus">
		<tr>
			<s:if test="%{isUndoReject != 'true'}">
			<td><s:checkbox name="chkSelLabCode" id="chkSelLabCode%{resCd}" fieldValue="%{#matStatus.index}###%{labourCode}###%{unitPrice}###%{labourName}" theme="simple"></s:checkbox></td>
			</s:if>
			<td><s:property value="labourCode" /></td>
			<td><s:property value="description" /></td>
			<td align="right"><s:textfield readonly="true" name="txtSelUnitLabHr" maxlength="5" size="5" id="txtSelUnitLabHr%{labourCode}"  value="%{unitLabourHrs}"  theme="simple"  cssStyle="text-align:right" ></s:textfield></td>
			<td align="right"><s:property value="cebUnitPrice" /></td>
			<td align="right"><s:textfield name="txtSelLabQty" maxlength="5" size="5" id="txtSelLabQty%{labourCode}"  value="%{itemQty}"  theme="simple"  onkeyup="numericValidate(this,event);calculateLabourCostNew('%{labourCode}');cal_labour_cost()"  cssStyle="text-align:right" ></s:textfield></td>
			<td  align="right">
				<s:if test="labourHours!=NULL">
					<s:textfield name="txtSelLabHrs"  size="12" readonly="true" id="txtSelLabHrs%{labourCode}"  theme="simple" value="%{getText('format.currency',{labourHours})}" cssStyle="text-align:right"  ></s:textfield>
				</s:if>
				<s:else>
					<s:textfield name="txtSelLabHrs"  size="12" readonly="true" id="txtSelLabHrs%{labourCode}"  theme="simple" value="" cssStyle="text-align:right"  ></s:textfield>
				</s:else>	
			</td>
			<td  align="right">
				<s:if test="cebLabourCost!=NULL">
					<s:textfield name="txtSelLabCost"  size="12" readonly="true" id="txtSelLabCost%{labourCode}"  theme="simple" value="%{getText('format.currency',{cebLabourCost})}" cssStyle="text-align:right"  ></s:textfield>
				</s:if>
				<s:else>
					<s:textfield name="txtSelLabCost"  size="12" readonly="true" id="txtSelLabCost%{labourCode}"  theme="simple" value="" cssStyle="text-align:right"  ></s:textfield>
				</s:else>	
			</td>
			
			<s:if test="cebLabourCost!=NULL">
				<s:set name="calcTotalAmt" value="#calcTotalAmt+cebLabourCost"/>
			</s:if>
			<s:if test="labourHours!=NULL">
				<s:set name="calcTotalHrs" value="#calcTotalHrs+labourHours"/>
			</s:if>
			
		</tr>
	</s:iterator>
	
	<tr>
		<s:if test="%{isUndoReject != 'true'}">
		<td colspan="6"  align="right"><b>Total Cost</b></td>
		</s:if>
		<s:else>
		<td colspan="5"  align="right"><b>Total Cost</b></td>
		</s:else>
		<td align="right"><b><s:textfield name="totalLabHrs"  size="12" readonly="true" id="totalLabHrs"  theme="simple" cssStyle="text-align:right" value="%{getText('format.currency',{#calcTotalHrs})}" ></s:textfield></b></td>
		<td align="right"><b><s:textfield name="totalLabCost"  size="12" readonly="true" id="totalLabCost"  theme="simple" cssStyle="text-align:right" value="%{getText('format.currency',{#calcTotalAmt})}" ></s:textfield></b></td>
		<td>&nbsp;</td>
	</tr>
	
	
</table>

</s:if>
<s:else>
<table class="tldFromContent"  border="1" width="100%">
	<tr>
		<th>&nbsp;</th>
		<th>Labour Code</th>
		<th>Description</th>
		<th>Unit Price</th>
		<th>Qty.</th>
		<th>Labour Cost</th>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		
	</tr>
</table>
</s:else>
		

