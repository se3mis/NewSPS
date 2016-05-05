<%@ taglib prefix="s" uri="/struts-tags"%>
<s:if test="%{isPoleMatParamClicked == 'true'}">
	<table width="85%"  bgcolor="#93C9FF">
		<tr>
			<td><font color="#006BD7"><b>Select Poles</br></br></b></font></td>
		</tr>
		<tr>
			<td align="left">
				<table width="100%"  bgcolor="#93C9FF" class="tldFromContent"  border="1">
					<tr>
						<td>
							<table>
								<tr>
									<td width="20%">Pole Type</td>
									<td width="1%">:</td>
									<td width="79%"><s:select theme="simple" name="poleType" id="poleType" headerKey=""  headerValue="Select Pole Type" list="#{'TAPPING':'Tapping','INTERMEDIATE':'Intermediate'}" onchange="enableConductors()"  />
									 with Conductor <s:select theme="simple" name="fromConductor" id="fromConductor" headerKey=""  headerValue="Conductor Type" list="#{'FLY':'FLY','ABC':'ABC'}" />
									</td>
								</tr>
								<tr>
									<td nowrap="nowrap">Conntecting Conductor</td>
									<td>:</td>
									<td>
									<s:select theme="simple" name="toConductor" id="toConductor" headerKey=""  headerValue="Conductor Type" list="#{'FLY':'FLY','ABC':'ABC'}"   disabled="true" />
									</td>
								</tr>
								<tr>
									<td>With a pole?</td>
									<td>:</td>
									<td>
									<s:radio theme="simple" name="isWithPole" id="isWithPole" list="#{'Y':'Yes','N':'No'}" onchange="enablePoles()" />
									<s:select theme="simple" name="poleCode" id="poleCode" headerKey=""  headerValue="Select Pole" list="poleList" disabled="true"  />
									</td>
								</tr>
								<tr>
									<td nowrap="nowrap">No of Points/Poles </td>
									<td>:</td>
									<td>
									CEB paid <s:textfield theme="simple" name="noOfPole" id="noOfPole" maxlength="2" size="5" onkeyup="numericValidate(this,event);" />
				 					Cutomer paid <s:textfield theme="simple" name="noOfCusPole" id="noOfCusPole" maxlength="2" size="5" onkeyup="numericValidate(this,event);" />
				 					</td>
								</tr>
								<tr>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
									<td>
									<s:a theme="simple" id="addPoleMatLink" href="javascript:add_pole_materials()">Add to Resource List</s:a>
									</td>
								</tr>
								<tr>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</s:if>		

