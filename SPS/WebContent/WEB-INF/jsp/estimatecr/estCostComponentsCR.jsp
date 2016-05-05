<%@ taglib prefix="s" uri="/struts-tags"%>

<table border="0">
	<tr>
		<td>Fixed Cost</td>
		<td>:</td>
		<td><s:textfield theme="simple" name="fixedCost" id="fixedCost" onkeyup="numericValidate(this,event);calculateTotalCost(this,event,false)" onblur="formatValue(this)"  cssStyle="text-align:right" readonly="true" /></td>
	</tr>
	<tr>
		
		<td>Variable Cost</td>
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
		<td>Material Cost</td>
		<td>:</td>
		<td><s:textfield theme="simple" name="otherCost" id="otherCost"   onkeyup="numericValidate(this,event);calculateTotalCost(this,event,false)" onblur="formatValue(this)" cssStyle="text-align:right" readonly="true" /></td>
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
	<tr>
		<td><b>Estimated Total Cost</b></td>
		<td>:</td>
		<td><s:textfield theme="simple"  name="totalCost" readonly="true"
			id="totalCost"  cssStyle="text-align:right;font:bold" /></td>
	</tr>
	
	

</table>