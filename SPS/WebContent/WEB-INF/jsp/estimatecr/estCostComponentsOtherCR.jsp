<%@ taglib prefix="s" uri="/struts-tags"%>

<table border="0">
	
	<s:if test="%{costCenterNo =='514.20' || costCenterNo =='548.00' || costCenterNo =='548.10' || costCenterNo =='548.20'}">
	<tr> 
		<td>Fixed Cost</td>
		<td>:</td>
		<td><s:textfield theme="simple" name="fixedCost" id="fixedCost" onkeyup="numericValidate(this,event);calculatingTotalCost(this,event,false)" onblur="formatValue(this);calculateTotalCost(this,event,false)"  cssStyle="text-align:right" /></td>
	</tr>
	<tr>
	</s:if>
	<s:else>
	<tr>
		<td>Fixed Cost</td>
		<td>.:</td>
		<td><s:textfield theme="simple" name="fixedCost" id="fixedCost" onblur="formatValue(this)"  cssStyle="text-align:right" readonly="true" /></td>
	</tr>
	</s:else>
	<tr>
		
		<td>Variable Cost</td>
		<td>:</td>
		<td><s:textfield theme="simple" name="variableCost" id="variableCost"  onblur="formatValue(this)"  cssStyle="text-align:right" readonly="true" /></td>
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
		<td><s:textfield theme="simple" name="otherCost" id="otherCost"   onblur="formatValue(this)" cssStyle="text-align:right" readonly="true" /></td>
	</tr>
	<tr>
		<td>Labour Cost</td>
		<td>:</td>
		<td><s:textfield readonly="true" theme="simple" name="labourCost" id="labourCost"  onblur="formatValue(this)"  cssStyle="text-align:right" /></td>
	</tr>
	<tr>
		<td>Transport Cost</td>
		<td>:</td>
		<td><s:textfield readonly="true" theme="simple" name="transportCost" id="transportCost"  onblur="formatValue(this)"  cssStyle="text-align:right" /></td>
	</tr>
	<tr>
		<td>Overhead Cost</td>
		<td>:</td>
		<td><s:textfield readonly="true" theme="simple" name="overheadCost" id="overheadCost"  onblur="formatValue(this)"  cssStyle="text-align:right" /></td>
	</tr> 
	<tr>
		<td>Conversion Cost</td>
		<td>:</td>
		<td><s:textfield theme="simple" name="convCost" id="convCost" onblur="formatValue(this)"  cssStyle="text-align:right"  readonly="true"/></td>
	</tr>
	<tr>
		<td>Contingency Cost</td>
		<td>:</td>
		<td><s:textfield theme="simple" name="contingencyCost" id="contingencyCost" onblur="formatValue(this)"  cssStyle="text-align:right"  readonly="true"/> * <s:textfield theme="simple" name="contingencyPercent" id="contingencyPercent" size="3" maxlength="2" onkeyup="numericValidate(this,event);calculatingTotalCost(this,event,false)"  cssStyle="text-align:right"   ></s:textfield> %</td>
	</tr>
	<tr>
		<td>Board Charge</td>
		<td>:</td>
		<td><s:textfield theme="simple" name="boardCharge" id="boardCharge"  onblur="formatValue(this)"  cssStyle="text-align:right"   readonly="true"/> * <s:textfield theme="simple"  name="boardChargePercent" id="boardChargePercent" size="3" maxlength="2"  onkeyup="numericValidate(this,event);calculatingTotalCost(this,event,false)"  cssStyle="text-align:right"  ></s:textfield> %</td>
	</tr>
	<tr>
		<td>Tax Amount</td>
		<td>:</td>
		<td><s:textfield theme="simple" name="taxAmount" id="taxAmount"  onblur="formatValue(this)"  cssStyle="text-align:right" readonly="true" /></td>
	</tr>
	<tr>
		<td>Security Deposit</td>
		<td>:</td>
		<td><s:textfield theme="simple" name="secDeposit" id="secDeposit"  onblur="formatValue(this)"  cssStyle="text-align:right" readonly="true" /></td>
	</tr>
	<tr>
		<td><b>Estimated Total Cost</b></td>
		<td>:</td>
		<td><s:textfield theme="simple"  name="totalCost" readonly="true"
			id="totalCost"  cssStyle="text-align:right;font:bold" /></td>
	</tr>
	
	

</table>