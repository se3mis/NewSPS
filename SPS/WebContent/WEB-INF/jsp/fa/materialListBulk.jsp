<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>

<!-- Materials list -->

<table>
	<s:if test="categorisedMatList!=null">
	<tr><td><b>Categorised Materials</b></td></tr>
	<tr>
		<td>
			<table class="tldFromContent"  border="1" width="100%">
				<tr>
					<th width="10%">FA Sub Cat.</th>
					<th width="10%">Mat. Code</th>
					<th width="33%">Mat. Name</th>
					<th width="5%">UOM</th>
					<th width="10%">Unit Price</th>
					<th width="7%">Commited Qty.</th>
					<th width="10%">Commited Cost</th>
					<s:if test="%{isVerify != 'true' && isTransfer != 'true'}">
					<th>&nbsp;</th>
					</s:if>
				</tr>
				  
				
				<s:set name="calcTotalAmt" value="0"/>
				<s:set name="calcTotalCusAmt" value="0"/>
				<s:set name="itemCodes" value="###"/>
				<s:set name="tmpManCat" value="@"/>
				<s:iterator value="categorisedMatList" status="matStatus">
					<s:if test="%{resType=='MAT-COST'}">
						
						<s:if test="#tmpManCat!=faManCatCode">
							<tr>
								<s:set name="tmpManCat" value="%{faManCatCode}"/>
								<td colspan="7" >
									<b><s:property value="faManCatDesc" /></b>
								</td>
							</tr>
						</s:if>
						<tr>
							
							
							<td>
								<s:property value="faSubCatDesc" />
							</td>
							<td><s:property value="resCd" /></td>
							<td><s:property value="matNm" /></td>
							
							<td><s:property value="uom" /></td>
							<td align="right"><s:text name="format.currency"><s:param value="unitPrice"/></s:text></td>
							<td  align="right">
								<s:property value="commitedQty" />
								<!-- <s:textfield name="txtSelQty" maxlength="5" size="5" id="txtSelQty%{resCd}"  value="%{estimateQty}"  theme="simple" onkeypress="return restrictToTwoDecimalPossition(this,event);"  onkeyup="numericValidate(this,event);calculateMatCost('Sel','%{resCd}','%{unitPrice}')"  cssStyle="text-align:right"></s:textfield> -->
							</td>
							<td  align="right">
								<s:property value="%{getText('format.currency',{commitedCost})}" />
								<!-- <s:if test="estimateCost!=NULL">
									<s:textfield name="txtSelMatCost"  size="12" readonly="true" id="txtSelMatCost%{resCd}"  theme="simple" value="%{getText('format.currency',{estimateCost})}" cssStyle="text-align:right"  ></s:textfield>
								</s:if>
								<s:else>
									<s:textfield name="txtSelMatCost"  size="12" readonly="true" id="txtSelMatCost%{resCd}"  theme="simple" value="" cssStyle="text-align:right"  ></s:textfield>
								</s:else>
								-->	
							</td>
							<s:if test="%{isVerify != 'true' && isTransfer != 'true'}">
							<td>
								 	<s:a href="javascript:remove_mats('%{resCd}','%{faManCatCode}')" id="matRemoveLink">Remove</s:a>
								 </td>
							</s:if>
							<s:if test="estimateCost!=NULL">
								<s:set name="calcTotalAmt" value="#calcTotalAmt+estimateCost"/>
							</s:if>
							<s:if test="customerCost!=NULL">
								<s:set name="calcTotalCusAmt" value="#calcTotalCusAmt+customerCost"/>
							</s:if>
							<s:set name="itemCodes" value="%{#itemCodes}+@@@@@+%{resCd}"/>
						</tr>
					</s:if>
				</s:iterator>
				<!--  
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
					<td colspan="6"  align="right"><b>Total Cost</b></td>
					<td align="right"><b><s:textfield name="totalDetailCost" size="12" readonly="true" id="totalDetailCost"  theme="simple" cssStyle="text-align:right" value="%{getText('format.currency',{#calcTotalAmt})}" ></s:textfield></b></td>
				</tr>
				-->
			</table>
		</td>
	</tr>
	</s:if>
	<s:if test="unCategorisedMatList!=null && unCategorisedMatList.size()>0">
		<tr><td>&nbsp;</td></tr>
		<tr><td><b>Uncategorised Materials</b></td></tr>
		<tr>
			<td>
				<table class="tldFromContent"  border="1" width="100%">
					<tr>
						<th width="10%">FA Sub Cat.</th>
						<th width="10%">Mat. Code</th>
						<th width="33%">Mat. Name</th>
						<th width="10%">FA Cat.</th>
						<th width="5%">UOM</th>
						<th width="10%">Unit Price</th>
						<th width="10%">Commited Qty</th>
						<s:if test="%{isVerify != 'true' && isTransfer != 'true'}">
						<th width="7%"> Qty to Move</th>
						
						<th width="1%"></th>
						</s:if>
					</tr>
					  
					
					<s:set name="calcTotalAmt" value="0"/>
					<s:set name="calcTotalCusAmt" value="0"/>
					<s:set name="itemCodes" value="###"/>
					<s:set name="tmpManCat" value="@"/>
					<s:iterator value="unCategorisedMatList" status="matStatus">
						<s:if test="%{resType=='MAT-COST'}">
							
							
							<tr>
								<td>
									<s:property value="faSubCatDesc" />
								</td>
								<td><s:property value="resCd" /></td>
								<td><s:property value="matNm" /></td>
								<td><s:select theme="simple"  name="faManCatCode" id="faManCatCode%{resCd}"  list="faManCatList"  listKey="name" listValue="value" headerKey="-1" headerValue="Select FA Category"/></td>
								<td><s:property value="uom" /></td>
								<td align="right"><s:text name="format.currency"><s:param value="unitPrice"/></s:text></td>
								<td align="right">
									<s:property value="%{commitedQty}" />
								</td>
								<s:if test="%{isVerify != 'true' && isTransfer != 'true'}">
								<td  align="right">
									<s:textfield name="faMoveQty" maxlength="5" size="5" id="faMoveQty%{resCd}"  value=""  theme="simple" onkeypress="return restrictToTwoDecimalPossition(this,event);"  onkeyup="numericValidate(this,event);"  cssStyle="text-align:right"></s:textfield>
								</td>
								</s:if>
								<!-- 
								<td  align="right">
									<s:if test="estimateCost!=NULL">
										<s:textfield name="txtSelMatCost"  size="12" readonly="true" id="txtSelMatCost%{resCd}"  theme="simple" value="%{getText('format.currency',{estimateCost})}" cssStyle="text-align:right"  ></s:textfield>
									</s:if>
									<s:else>
										<s:textfield name="txtSelMatCost"  size="12" readonly="true" id="txtSelMatCost%{resCd}"  theme="simple" value="" cssStyle="text-align:right"  ></s:textfield>
									</s:else>	
								</td>
								 -->
								 <s:if test="%{isVerify != 'true' && isTransfer != 'true'}">
								 <td>
								 	<s:a href="javascript:move_mats('%{resCd}')" id="matMoveLink">Move</s:a>
								 </td>
								 </s:if>
								<s:if test="estimateCost!=NULL">
									<s:set name="calcTotalAmt" value="#calcTotalAmt+estimateCost"/>
								</s:if>
								<s:if test="customerCost!=NULL">
									<s:set name="calcTotalCusAmt" value="#calcTotalCusAmt+customerCost"/>
								</s:if>
								<s:set name="itemCodes" value="%{#itemCodes}+@@@@@+%{resCd}"/>
							</tr>
						</s:if>
					</s:iterator>
					<!-- 
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
						<td colspan="6"  align="right"><b>Total Cost</b></td>
						<td align="right"><b><s:textfield name="totalDetailCost" size="12" readonly="true" id="totalDetailCost"  theme="simple" cssStyle="text-align:right" value="%{getText('format.currency',{#calcTotalAmt})}" ></s:textfield></b></td>
					</tr>
					-->
				</table>
			</td>
		</tr>
	</s:if>
</table>
