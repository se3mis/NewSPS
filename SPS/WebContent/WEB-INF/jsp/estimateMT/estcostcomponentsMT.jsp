<%@ taglib prefix="s" uri="/struts-tags"%>

<table border="0">
	<tr>
		<td>Fixed Cost <font color="red">*</font></td>
		<td>:</td>
		<td><s:textfield theme="simple" name="fixedCost" id="fixedCost" onkeyup="numericValidate(this,event);calculateTotalCost(this,event,false)" onblur="formatValue(this)"  cssStyle="text-align:right" readonly="true" /></td>
	</tr>
	<tr>
		
		<td>Variable Cost  <font color="red">*</font></td>
		<td>:</td>
		<td><s:textfield theme="simple" name="variableCost" id="variableCost"  onkeyup="numericValidate(this,event);calculateTotalCost(this,event,false)" onblur="formatValue(this)"  cssStyle="text-align:right" readonly="true" /></td>
	</tr>
	<tr>
		<td>Sub Total</td>
		<td>:</td>
		<td><s:textfield theme="simple" name="subTotal" readonly="true"
			id="subTotal"  cssStyle="text-align:right" /></td>
	</tr>
	<tr>
		<td>Other Cost</td>
		<td>:</td>
		<td><s:textfield theme="simple" name="otherCost" id="otherCost"   onkeyup="numericValidate(this,event);calculateTotalCost(this,event,false)" onblur="formatValue(this)" cssStyle="text-align:right"  readonly="true"/>(Material)&nbsp;&nbsp;<s:a href="javascript:copyOtherCost()">Add other cost</s:a></td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td>:</td>
		<td><s:textfield theme="simple" name="otherLabourCost" id="otherLabourCost"   onkeyup="numericValidate(this,event);calculateTotalCost(this,event,false)" onblur="formatValue(this)" cssStyle="text-align:right"  readonly="true"/>(Labour)</td>
	</tr>
	<tr>
		<td>Capital Cost</td>
		<td>:</td>
		<td><s:textfield theme="simple" name="capitalCost" id="capitalCost"  onkeyup="numericValidate(this,event);calculateTotalCost(this,event,false)" onblur="formatValue(this)"  cssStyle="text-align:right" readonly="true" /></td>
	</tr>
	<tr>
		<td>Conversion Cost</td>
		<td>:</td>
		<td><s:textfield theme="simple" name="convCost" id="convCost"  onkeyup="numericValidate(this,event);calculateTotalCost(this,event,false)" onblur="formatValue(this)"  cssStyle="text-align:right"  readonly="true"/></td>
	</tr>
	<tr>
		<td>Tax Amount</td>
		<td>:</td>
		<td><s:textfield theme="simple" name="taxAmount" id="taxAmount"  onkeyup="numericValidate(this,event);calculateTotalCost(this,event,false)" onblur="formatValue(this)"  cssStyle="text-align:right" readonly="true" /></td>
	</tr>
	<tr>
		<td>Security Deposit</td>
		<td>:</td>
		<td><s:textfield theme="simple" name="secDeposit" id="secDeposit"  onkeyup="numericValidate(this,event);calculateTotalCost(this,event,false)" onblur="formatValue(this)"  cssStyle="text-align:right" readonly="true" /></td>
	</tr>
	<s:if test="%{costCenterNo == '541.00' || costCenterNo == '542.00' || costCenterNo == '547.00' || costCenterNo == '548.00' || appSubTypeDB == 'C1' || appSubTypeDB == 'C2' || appSubTypeDB == 'C3'}">
	<tr>
		<td>Risk Deposit</td>
		<td>:</td>
		<td><s:textfield theme="simple" name="addlDeposit" id="addlDeposit"  onkeyup="numericValidate(this,event);calculateTotalCost(this,event,false)" onblur="formatValue(this);calculateTotalCost(this,event,false)"  cssStyle="text-align:right" /></td>
	</tr>
	</s:if>
	<tr>
		<td><b>Estimated Total Cost</b></td>
		<td>:</td>
		<td><s:textfield theme="simple"  name="totalCost" readonly="true"
			id="totalCost"  cssStyle="text-align:right;font:bold" /></td>
	</tr>
	
	

</table>
