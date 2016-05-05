<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>

<!-- Materials list -->
<s:if test="selectMatList!=null">
<table class="tldFromContent"  border="1" width="100%">
	<tr>
		<s:if test="%{isUndoReject != 'true'}">
			<th width="2%">&nbsp;</th>
		</s:if>
		<th width="10%">Res. Code</th>
		<th width="10%">Res. Type</th>
		<th width="5%">Res. Cat</th>
		<th width="33%">Res. Name</th>
		<th width="5%">UOM</th>
		<th width="10%">Unit Price</th>
		<th width="7%">Qty.</th>
		<th width="10%">Cost</th>
		<th width="7%">Maintenance Qty.</th>
		<th width="10%">Maintenance Cost</th>
		
	</tr>
	<s:set name="calcTotalAmt" value="0"/>
	<s:set name="calcTotalMntAmt" value="0"/>
	<s:iterator value="selectMatList" status="matStatus">
		<tr>
			<s:if test="%{isUndoReject != 'true'}">
			<td>
			<s:if test="%{resCd!='LABOUR' && resCd!='OVERHEAD' && resCd!='TRANSPORT'}">
				<s:checkbox name="chkSelMatCode" id="chkSelMatCode%{resCd}" fieldValue="%{#matStatus.index}###%{resCd}###%{uom}###%{unitPrice}###%{matNm}" theme="simple"></s:checkbox>
				
			</s:if>	
			</td>
			
			</s:if>
			<td><s:property value="resCd" /></td>
			<td><s:property value="resType" /></td>
			<td><s:property value="resCat" /></td>
			<td><s:property value="matNm" /></td>
			<td><s:property value="uom" /></td>
			<td align="right"><s:text name="format.currency"><s:param value="unitPrice"/></s:text></td>
			<td>
				<s:if test="%{resCd=='LABOUR' || resCd=='OVERHEAD' }">
					<s:textfield readonly="true" name="txtSelQty" maxlength="5" size="5" id="txtSelQty%{resCd}"  value="%{estimateQty}"  theme="simple"  onkeyup="numericValidate(this,event);calculateMatCost('Sel','%{resCd}','%{unitPrice}');"  cssStyle="text-align:right" ></s:textfield>
				</s:if>
				<s:else>
					<s:textfield name="txtSelQty" maxlength="5" size="5" id="txtSelQty%{resCd}"  value="%{estimateQty}"  theme="simple"  onkeyup="numericValidate(this,event);calculateMatCost('Sel','%{resCd}','%{unitPrice}');"  cssStyle="text-align:right" ></s:textfield>
				</s:else>
			</td>
			<td  align="right">
				<s:if test="estimateCost!=NULL">
					<s:textfield name="txtSelMatCost"  size="12" readonly="true" id="txtSelMatCost%{resCd}"  theme="simple" value="%{getText('format.currency',{estimateCost})}" cssStyle="text-align:right"  ></s:textfield>
				</s:if>
				<s:else>
					<s:textfield name="txtSelMatCost"  size="12" readonly="true" id="txtSelMatCost%{resCd}"  theme="simple" value="" cssStyle="text-align:right"  ></s:textfield>
				</s:else>	
			</td>
			<s:if test="estimateCost!=NULL">
				<s:set name="calcTotalAmt" value="#calcTotalAmt+estimateCost"/>
			</s:if>
			<s:if test="mntCost!=NULL">
				<s:set name="calcTotalMntAmt" value="#calcTotalMntAmt+mntCost"/>
			
			</s:if>
			<td align="right">
				<s:hidden name="savedMntQty"  id="savedMntQty%{resCd}" value="%{mntQty}"></s:hidden>
				<s:if test="%{resCd=='LABOUR' || resCd=='OVERHEAD' || resCd=='TRANSPORT'}">
					<s:textfield name="txtMntQty"  maxlength="5" size="5" id="txtMntQty%{resCd}"  value="%{mntQty}"  theme="simple" cssStyle="text-align:right" readonly="true" ></s:textfield>
				</s:if>
				<s:else>
					<s:textfield name="txtMntQty"  maxlength="5" size="5" id="txtMntQty%{resCd}"  value="%{mntQty}"  theme="simple"  cssStyle="text-align:right" onkeypress="return restrictToTwoDecimalPossition(this,event);" onkeyup="numericValidate(this,event);calculateOtherCRMaintenanceCost(this,event,'Mnt','%{resCd}','%{unitPrice}')"></s:textfield>
				</s:else>
			</td>
			<td  align="right">
				
					<s:if test="mntCost!=NULL">
						<s:textfield name="txtSelMntCost"  size="12" readonly="true" id="txtSelMntCost%{resCd}"  theme="simple" value="%{getText('format.currency',{mntCost})}" cssStyle="text-align:right"  ></s:textfield>
					</s:if>
					<s:else>
						<s:textfield name="txtSelMntCost"  size="12" readonly="true" id="txtSelMntCost%{resCd}"  theme="simple" value="" cssStyle="text-align:right"  ></s:textfield>
					</s:else>	
				
			</td>
		</tr>
	</s:iterator>
	
	<tr>
	<s:hidden name="itemCodeList" id="itemCodeList" ></s:hidden>
	<s:hidden name="itemDescList" id="itemDescList" ></s:hidden>
	<s:hidden name="priceList" id="priceList" ></s:hidden>
	<s:hidden name="uomList" id="uomList" ></s:hidden>
	<s:hidden name="qtyList" id="qtyList" ></s:hidden>
	<s:hidden name="costList" id="costList" ></s:hidden>
	<s:hidden name="totalMatCost" id="totalMatCost" ></s:hidden>
	<s:hidden name="nonMatCodeList" id="nonMatCodeList" ></s:hidden>
	<s:hidden name="nonMatCostList" id="nonMatCostList" ></s:hidden>
		<s:if test="%{isUndoReject != 'true'}">
		<td colspan="8"  align="right"><b>Total Cost</b></td>
		</s:if>
		<s:else>
		<td colspan="7"  align="right"><b>Total Cost</b></td>
		</s:else>
		<td align="right"><b><s:textfield name="totalDetailCost"  size="12" readonly="true" id="totalDetailCost"  theme="simple" cssStyle="text-align:right" value="%{getText('format.currency',{#calcTotalAmt})}" ></s:textfield></b></td>
		<td>&nbsp;</td>
		<td align="right"><b><s:textfield name="totalMntCost" size="12" readonly="true" id="totalMntCost"  theme="simple" cssStyle="text-align:right" value="%{getText('format.currency',{#calcTotalMntAmt})}" ></s:textfield></b></td>
	</tr>
	
	
</table>
</s:if>
<s:else>
<table class="tldFromContent"  border="1" width="100%">
	<tr>
		<th>Resource Code</th>
		<th>Resource Type</th>
		<th>Resource Category</th>
		<th>Resource Name</th>
		<th>UOM</th>
		<th>Unit Price</th>
		<th>Qty.</th>
		<th>Cost</th>
		<th>Qty.</th>
		<th>Cost</th>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
	</tr>
</table>
</s:else>
		

