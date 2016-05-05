var poleRowCount = 1;
var strutsRowCount = 1;
var stayRowCount = 1;
var serLenRowCount = 1;

var poleRemoveList = "";
var strutsRemoveList = "";
var stayRemoveList = "";
var serLenRemoveList = "";

var poleList;
var strutList;
var stayList;
var serlenList;

var totalWireLength = 0;
var totalexistingwireLength = 0;

function checkAppNoNotNull(){
	if((document.getElementById("txfdappNo").value==null)||(document.getElementById("txfdappNo").value=="") ){
		alert("Please key in Application No.!");
		document.getElementById("txfdappNo").select();
		return false;
	}
	else return true;
}

function validate_form(){
		
	if((document.getElementById("hid_isValueFilled").value==null)||(document.getElementById("hid_isValueFilled").value=="") ||(document.getElementById("txfdappNo").value==null)||(document.getElementById("txfdappNo").value=="")){
		alert("Please fill the details");
		document.getElementById("hid_isValueFilled").select();
		return false;
	} 	
	else {
		var answer = confirm ("Are you sure you want to update and print the service estimate?");
		if (!answer)
			return false;
	}
	return true;
}

function clearForm(){
	document.getElementById("statusMsg").value="";
	document.getElementById("statusMsgErr").value="";
	document.getElementById("txfdappName").value="";
	document.getElementById("txfdappArea").value="";
	document.getElementById("txfdappAddress").value="";
	document.getElementById("txfdappCSC").value="";
	document.getElementById("txfdappTphone").value="";
	//document.getElementById("txfdappNo").value="";
	document.getElementById("txfdappSerType").value="";
	document.getElementById("txfdappTariff").value="";
	document.getElementById("hid_isValueFilled").value="";
	
	document.getElementById("noferectionPoles").value="";
	document.getElementById("erectionPolesDesc").value="";
	document.getElementById("nofExacErec").value="";
	document.getElementById("ExacErecDesc").value="";
	document.getElementById("nofAddWireSpan").value="";
	document.getElementById("nofAddWireSpanDesc").value="";
	document.getElementById("noOfconductor").value="";
	document.getElementById("conductorAmountDesc").value="";
	
	document.getElementById("txfdsubstation").value="";
	document.getElementById("txfdsin").value="";
	document.getElementById("txfddistanceFrmSS").value="";
	document.getElementById("txfdphase").value="";
	document.getElementById("txfdtransformerCapacity").value="";
	document.getElementById("txfdpoleNo").value="";
	document.getElementById("txfdtransformerLoad").value="";
	document.getElementById("txfdtransformerPeakLoad").value="";
	document.getElementById("txfdfeederControltype").value="";
	document.getElementById("nofPoles").value="";	
	
	document.getElementById("txfdappNo").readOnly = false;
	
	document.getElementById("hid_RemoveStruts").value="";
	document.getElementById("hid_RemoveStays").value="";
	document.getElementById("hid_RemoveSerLengths").value="";
	document.getElementById("txfdttotalLength").value="";
	document.getElementById("txfdneighborsAcct").value="";
	
	document.getElementById("hid_SavedWireLengths").value="";
	
	
	strutsRemoveList = "";
	stayRemoveList = "";
	serLenRemoveList = "";
	poleRemoveList = "";
	
	removeAll();
		
	return false;
}

function on_Load(){
			
	if(document.getElementById("hid_isValueFilled").value!="" && document.getElementById("hid_isValueFilled").value.length>0 && document.getElementById("hid_isValueFilled").value=="filled"){
		setControls(false);		
		document.getElementById("txfdappNo").readOnly = true;
		if(document.getElementById("txfdttotalLength").value!="" && document.getElementById("txfdttotalLength").value.length>0){
			totalWireLength = Number(document.getElementById("txfdttotalLength").value);
			totalexistingwireLength = Number(document.getElementById("txfdttotalLength").value);
		}		
		
		if ((document.getElementById("RadioisLoopTypeN").checked && document.getElementById("RadiowireTypeUG").checked)){
			document.getElementById("tdLableCableType").style.visibility = "visible";
			document.getElementById("tdListCableType").style.visibility = "visible";
			document.getElementById("cableTypeList").value = document.getElementById("hid_CableType").value;
		}
		
		if(document.getElementById("hid_AddedStrutList").value!="" && document.getElementById("hid_AddedStrutList").value.length>0)
			strutList=document.getElementById("hid_AddedStrutList").value.split(",");
		else
			strutList = new Array();
		
		if(document.getElementById("hid_AddedStayList").value!="" && document.getElementById("hid_AddedStayList").value.length>0)
			stayList=document.getElementById("hid_AddedStayList").value.split(",");
		else
			stayList = new Array();		
				
		if(document.getElementById("hid_AddedSerLengthsList").value!="" && document.getElementById("hid_AddedSerLengthsList").value.length>0)
			serlenList=document.getElementById("hid_AddedSerLengthsList").value.split(",");
		else
			serlenList= new Array();
		
		if(document.getElementById("hid_AddedPoleList").value!="" && document.getElementById("hid_AddedPoleList").value.length>0)
			poleList=document.getElementById("hid_AddedPoleList").value.split(",");
		else
			poleList= new Array();	
		
		document.getElementById('insStaysTypeList').selectedIndex = 1;		
		if(document.getElementById("RadiowireTypeOH").checked ){
			document.getElementById("tdLableIsConversion").style.visibility = "visible";
			document.getElementById("tdRadioIsConversion").style.visibility = "visible";
			
			
			if(document.getElementById("hid_ConversionLength").value!= null && document.getElementById("hid_ConversionLength").value.length>0){
				document.getElementById("RadiowireisConversionY").checked = true;
				document.getElementById("tdLableConversionLength").style.visibility = "visible";
				document.getElementById("tdtxtConversionlength").style.visibility = "visible";	
				document.getElementById("tdtxtConversionlength").value = document.getElementById("hid_ConversionLength").value;
			}else{
				document.getElementById("RadiowireisConversionN").checked = true;
				document.getElementById("tdLableConversionLength").style.visibility = "hidden";
				document.getElementById("tdtxtConversionlength").style.visibility = "hidden";	
			}						
		}else{
			document.getElementById("tdLableIsConversion").style.visibility = "hidden";
			document.getElementById("tdRadioIsConversion").style.visibility = "hidden";
			document.getElementById("tdLableConversionLength").style.visibility = "hidden";
			document.getElementById("tdtxtConversionlength").style.visibility = "hidden";
			document.getElementById("RadiowireisConversionN").checked = true;
		}	
						
	}
	else
	{
		
		if(document.getElementById("hid_isValueFilled").value!="" && document.getElementById("hid_isValueFilled").value.length>0 && document.getElementById("hid_isValueFilled").value!="cantsave")
			clearForm();
		document.getElementById("RadioisLoopTypeN").checked = true;		
		
	
		setControls(true);
		totalWireLength = 0;
		totalexistingwireLength = 0;
	}
	
	  var tbl1 = document.getElementById('list_table1');
	  var lastRow1 = tbl1.rows.length;
	  poleRowCount = lastRow1;
	  
	  var tbl2 = document.getElementById('list_table2');
	  var lastRow2 = tbl2.rows.length;
	  strutsRowCount = lastRow2;
	  
	  var tbl3 = document.getElementById('list_table3');
	  var lastRow3 = tbl3.rows.length;
	  stayRowCount = lastRow3;
	  
	  var tbl4 = document.getElementById('list_table4');
	  var lastRow4 = tbl4.rows.length;
	  serLenRowCount = lastRow4;
}

function setControls(state){
	
	document.getElementById("excavationDetailsList").disabled = state;
	document.getElementById("excavationAmountAdd").disabled = state;
	document.getElementById("poleTypeList").disabled = state;
	document.getElementById("fromConductorList").disabled = state;
	document.getElementById("toConductorList").disabled = state;
	document.getElementById("polePointList").disabled = state;
	
	document.getElementById("nofPoles").readOnly = state;	
	document.getElementById("erectionDetailsList").disabled = state;
	document.getElementById("noferectionPoles").readOnly = state;
	document.getElementById("erectionPolesDesc").readOnly = state;
	document.getElementById("erectionAmountAdd").disabled = state;
	
	document.getElementById("insStaysDetailsList").disabled = state;
	document.getElementById("nofExacErec").readOnly = state;
	document.getElementById("ExacErecDesc").readOnly = state;
	document.getElementById("insStaysAmountAdd").disabled = state;
	
	document.getElementById("wireDetailsList").disabled = state;
	document.getElementById("nofAddWireSpan").readOnly = state;
	document.getElementById("nofAddWireSpanDesc").readOnly = state;
	document.getElementById("wireAmountAdd").disabled = state;
	
	document.getElementById("conductorDetailsList").disabled = state;
	document.getElementById("noOfconductor").readOnly = state;
	document.getElementById("conductorAmountDesc").readOnly = state;
	document.getElementById("conductorAmountAdd").disabled = state;
	
	document.getElementById("txfdsubstation").readOnly = state;
	document.getElementById("txfdsin").readOnly = state;
	document.getElementById("txfddistanceFrmSS").readOnly = state;
	document.getElementById("txfdphase").readOnly = state;
	document.getElementById("txfdtransformerCapacity").readOnly = state;
	document.getElementById("txfdpoleNo").readOnly = state;
	document.getElementById("txfdtransformerLoad").readOnly = state;
	document.getElementById("txfdtransformerPeakLoad").readOnly = state;
	document.getElementById("txfdfeederControltype").readOnly = state;
	document.getElementById("noOfSpans").readOnly = state;
	document.getElementById("insStaysTypeList").disabled = state;	
	document.getElementById("btnSave").disabled = state;	
	document.getElementById("txtdistancetoServicePlace").readOnly = state;
	document.getElementById("txtConversionlength").readOnly = state;
	
}

function setDetailsToTable(categoryVal,listID,qtyID,descrID,msg,btnID){
	
	if(document.getElementById(listID)){
			if(document.getElementById(listID).value=='-1'){
				alert('Please select a '+msg+' type');
				return false;
			}else
			{
				var selIndex = document.getElementById(listID).selectedIndex;
				var selectedText = document.getElementById(listID).options[selIndex].text;
				
				var categoryID = document.getElementById(listID).value;
								
				if(document.getElementById(qtyID)){
					if(document.getElementById(qtyID).value==''){
						alert('Please enter no of '+msg);
						return false;
					}else{
						quantity = document.getElementById(qtyID).value;
					}
			}	
				
				if(strutList!=null && strutList.length>0){
					for(i=0;i<strutList.length;i++){
						var removeItem = categoryID;
						if(removeItem==strutList[i]){
							alert("The type already exists");
							return false;
						}						
					}
					strutList[strutList.length]=categoryID;
				}else
					strutList[0]=categoryID;
						
			if(document.getElementById(descrID))				
				description = document.getElementById(descrID).value;
							
			createTableRow(categoryVal,selectedText,quantity,description,categoryID,btnID);
						
			return false;

		}
		
	}
}

function setStayDetailsToTable(listID,qtyID,descrID,msg,btnID,stayctrlTypeId){
	
	if(document.getElementById(listID)){
			if(document.getElementById(listID).value=='-1'){
				alert('Please select a '+msg+' type');
				return false;
			}else if(document.getElementById(stayctrlTypeId).value=='-1'){
				alert('Please select a stay control type');
				return false;
			}
			{
				var selIndex = document.getElementById(listID).selectedIndex;
				var selectedText = document.getElementById(listID).options[selIndex].text;			
				
				var stayControlType = document.getElementById(stayctrlTypeId).value;
				var categoryID = document.getElementById(listID).value;
								
				if(document.getElementById(qtyID)){
					if(document.getElementById(qtyID).value==''){
						alert('Please enter no of '+msg);
						return false;
					}else{
						quantity = document.getElementById(qtyID).value;
					}
			}	
				
				if(stayList!=null && stayList.length>0){
					for(i=0;i<stayList.length;i++){
						var removeItem = categoryID+stayControlType;
						if(removeItem==stayList[i]){
							alert("The type already exists");
							return false;
						}						
					}
					stayList[stayList.length]=categoryID+stayControlType;
				}else
					stayList[0]=categoryID+stayControlType;
						
			if(document.getElementById(descrID))				
				description = document.getElementById(descrID).value;
							
			createStayTableRow(selectedText,quantity,description,categoryID,btnID,stayControlType);
						
			return false;

		}
		
	}
}




function setServiceLengthDetailsToTable(listID,qtyID,descrID,msg,btnID){
	
	if(document.getElementById(listID)){
			if(document.getElementById(listID).value=='-1'){
				alert('Please select a '+msg+' type');
				return false;
			}else
			{
				var selIndex = document.getElementById(listID).selectedIndex;
				var selectedText = document.getElementById(listID).options[selIndex].text;
				
				var categoryID = document.getElementById(listID).value;
								
				if(document.getElementById(qtyID)){
					if(document.getElementById(qtyID).value==''){
						alert('Please enter wire meter length');
						return false;
					}else{
						quantity = document.getElementById(qtyID).value;
					}
			}				
				
			if(serlenList!=null && serlenList.length>0){
				for(i=0;i<serlenList.length;i++){
					var removeItem = categoryID;
					if(removeItem==serlenList[i]){
						alert("The type already exists");
						return false;
					}						
				}
				serlenList[serlenList.length]=categoryID;
			}else
				serlenList[0]=categoryID;
				
			if(document.getElementById(descrID))				
				description = document.getElementById(descrID).value;
				
			totalWireLength = totalWireLength + Number(quantity);
			document.getElementById('txfdttotalLength').value = totalWireLength;
			createServiceLengthTableRow(selectedText,quantity,description,categoryID,btnID);
						
			return false;

		}
		
	}
}


function setPoleDetailsToTable(btnID){
	
	if(document.getElementById('excavationDetailsList')){
			if(document.getElementById('excavationDetailsList').value=='-1'){
				alert('Please select material code');
				return false;
			}else if(document.getElementById('poleTypeList').value=='-1'){
				alert('Please select a pole type');
				return false;
			}else if(document.getElementById('fromConductorList').value=='-1'){
				alert('Please select from conductor type');
				return false;
			}else if(document.getElementById('toConductorList').value=='-1'){
				alert('Please select to conductor type');
				return false;
			}else if(document.getElementById('polePointList').value=='-1'){
				alert('Please select a pointer type');
				return false;
			}else if(document.getElementById('nofPoles').value.length==0){
				alert('Please specify the amount');
				return false;
			}else  		
			{
				var selIndex = document.getElementById('excavationDetailsList').selectedIndex;
				var selectedText = document.getElementById('excavationDetailsList').options[selIndex].text;				
				var categoryID = document.getElementById('excavationDetailsList').value;
					
				
				var poleType = document.getElementById('poleTypeList').value;
				var fromConductor = document.getElementById('fromConductorList').value;
				var toConductor = document.getElementById('toConductorList').value;
				var qty = document.getElementById('nofPoles').value;
				var pointerType = document.getElementById('polePointList').value;
				
				if(poleList!=null && poleList.length>0){
					for(i=0;i<poleList.length;i++){
						var removeItem = categoryID+poleType+fromConductor+toConductor+pointerType;
						if(removeItem==poleList[i]){
							alert("The type already exists");
							return false;
						}						
					}
					poleList[poleList.length]=categoryID+poleType+fromConductor+toConductor+pointerType;
				}else
					poleList[0]=categoryID+poleType+fromConductor+toConductor+pointerType;
				
				
			}							
							
			createPoleTableRow(categoryID,selectedText,poleType,fromConductor,toConductor,pointerType,qty,btnID);
						
			return false;

		}
		
	}



function createStayTableRow(item,qty,des,categoryID,btnID,stayControlType){		  
	
	  
	
	  var tbl = document.getElementById('list_table3');
	  var lastRow = stayRowCount;
	  // if there's no header row in the table, then iteration = lastRow + 1
	  var iteration = lastRow;
	  var row = tbl.insertRow(tbl.rows.length);	 
	  row.id = 'rowIDStay_'+iteration;
	 
	  var cell1 = row.insertCell(0);
	  var el1 = document.createElement('input');
	  el1.type = 'text';
	  el1.name = 'txtStayRowcategoryID' + iteration;
	  el1.id = 'txtStayRowcategoryID' + iteration;
	  el1.value = categoryID;
	  el1.disabled = true;
	  el1.size = '200px';
	  cell1.appendChild(el1);
	  
	  var cell2 = row.insertCell(1);
	  var el2 = document.createElement('input');
	  el2.type = 'text';
	  el2.name = 'txtStayRowitem' + iteration;
	  el2.id = 'txtStayRowitem' + iteration;
	  el2.size = '80px';
	  el2.disabled = true;
	  el2.value=item;
	  cell2.appendChild(el2);
	
	  var cell3 = row.insertCell(2);
	  var el3 = document.createElement('input');
	  el3.type = 'text';
	  el3.name = 'txtStayControlRowitem' + iteration;
	  el3.id = 'txtStayControlRowitem' + iteration;
	  el3.size = '80px';
	  el3.disabled = true;
	  el3.value=stayControlType;
	  cell3.appendChild(el3);	    
	  
	  var cell4 = row.insertCell(3);
	  var el4 = document.createElement('input');
	  el4.type = 'text';
	  el4.name = 'txtStayRowqty' + iteration;
	  el4.id = 'txtStayRowqty' + iteration;
	  el4.value=qty;
	  el4.disabled = false;
	  el4.onkeypress = onlyNumbers;
	  el4.size = '100px';
	  cell4.appendChild(el4);
	  
	  var cell5 = row.insertCell(4);
	  var el5 = document.createElement('input');
	  el5.type = 'text';
	  el5.name = 'txtStayRowdes' + iteration;
	  el5.id = 'txtStayRowdes' + iteration;
	  el5.value=des;
	  el5.disabled = true;
	  el5.size = '100px';
	  cell5.appendChild(el5);	
	  
	  var cell6 = row.insertCell(5);
	  var el6 = document.createElement('input');
	  el6.type = 'button';
	  el6.name = 'txtRow' + iteration;
	  el6.id = iteration+'_'+btnID;
	  el6.value='Remove';
	  el6.setAttribute('onclick', 'removeStayItem(this)'); 
	  el6.size = '100px';
	  cell6.appendChild(el6);	
	  
	  stayRowCount++;
	  
	  return false;
}

function createServiceLengthTableRow(item,qty,des,categoryID,btnID){		  
	
	 
	  var tbl = document.getElementById('list_table4');
	  var lastRow = serLenRowCount;
	  // if there's no header row in the table, then iteration = lastRow + 1
	  var iteration = lastRow;
	  var row = tbl.insertRow(tbl.rows.length);	 
	  row.id = 'rowIDSer_'+iteration;
	 
	  var cell1 = row.insertCell(0);
	  var el1 = document.createElement('input');
	  el1.type = 'text';
	  el1.name = 'txtSerRowcategoryID' + iteration;
	  el1.id = 'txtSerRowcategoryID' + iteration;
	  el1.value = categoryID;
	  el1.disabled = true;
	  el1.size = '200px';
	  cell1.appendChild(el1);
	
	  var cell2 = row.insertCell(1);
	  var el2 = document.createElement('input');
	  el2.type = 'text';
	  el2.name = 'txtSerRowitem' + iteration;
	  el2.id = 'txtSerRowitem' + iteration;
	  el2.size = '80px';
	  el2.disabled = true;
	  el2.value=item;
	  cell2.appendChild(el2);
	  
	  var cell3 = row.insertCell(2);
	  var el3 = document.createElement('input');
	  el3.type = 'text';
	  el3.name = 'txtSerRowqty' + iteration;
	  el3.id = 'txtSerRowqty' + iteration;
	  el3.onkeypress = onlyNumbers;
	  el3.onchange= setTotal;
	  el3.value=qty;
	  el3.disabled = false;
	  el3.size = '100px';
	  cell3.appendChild(el3);
	  
	  var cell4 = row.insertCell(3);
	  var el4 = document.createElement('input');
	  el4.type = 'text';
	  el4.name = 'txtSerRowdes' + iteration;
	  el4.id = 'txtSerRowdes' + iteration;
	  el4.value=des;
	  el4.disabled = true;
	  el4.size = '100px';
	  cell4.appendChild(el4);	
	  
	  var cell5 = row.insertCell(4);
	  var el5 = document.createElement('input');
	  el5.type = 'button';
	  el5.name = 'txtRow' + iteration;
	  el5.id = iteration+'_'+btnID;
	  el5.value='Remove';
	  el5.setAttribute('onclick', 'removeServiceLengthItem(this)'); 
	  el5.size = '100px';
	  cell5.appendChild(el5);	
	    
	  serLenRowCount++;
	  return false;
}

function setTotal(event){
	
	var tempLength = 0;
	tempLength= totalexistingwireLength;
	for (l=1; l<=serLenRowCount -1; l++) {			  		  
		  var serviceLenQuantityRowID = 'txtSerRowqty'+(l);	 
		  var serviceLenquantity="";			  
		  if(document.getElementById(serviceLenQuantityRowID) && document.getElementById(serviceLenQuantityRowID)!=null)
			  serviceLenquantity =document.getElementById(serviceLenQuantityRowID).value;
			
		  tempLength = tempLength + Number(serviceLenquantity);
	 	}	
	totalWireLength = tempLength;
	document.getElementById('txfdttotalLength').value = totalWireLength;
	
}

function createTableRow(category,item,qty,des,categoryID,btnID){		  
	
	  var tbl = document.getElementById('list_table2');
	  var lastRow = strutsRowCount;
	  // if there's no header row in the table, then iteration = lastRow + 1
	  var iteration = lastRow;
	  var row = tbl.insertRow(tbl.rows.length);	 
	  row.id = 'rowID_'+iteration;
	 
	  var cell1 = row.insertCell(0);
	  var el1 = document.createElement('input');
	  el1.type = 'text';
	  el1.name = 'txtRowcategoryID' + iteration;
	  el1.id = 'txtRowcategoryID' + iteration;
	  el1.value = categoryID;
	  el1.disabled = true;
	  el1.size = '200px';
	  cell1.appendChild(el1);
	
	  var cell2 = row.insertCell(1);
	  var el2 = document.createElement('input');
	  el2.type = 'text';
	  el2.name = 'txtRowitem' + iteration;
	  el2.id = 'txtRowitem' + iteration;
	  el2.size = '80px';
	  el2.disabled = true;
	  el2.value=item;
	  cell2.appendChild(el2);
	  
	  var cell3 = row.insertCell(2);
	  var el3 = document.createElement('input');
	  el3.type = 'text';
	  el3.name = 'txtRowqty' + iteration;
	  el3.id = 'txtRowqty' + iteration;
	  el3.onkeypress = onlyNumbers;
	  el3.value=qty;
	  el3.disabled = false;
	  el3.size = '100px';
	  cell3.appendChild(el3);
	  
	  var cell4 = row.insertCell(3);
	  var el4 = document.createElement('input');
	  el4.type = 'text';
	  el4.name = 'txtRowdes' + iteration;
	  el4.id = 'txtRowdes' + iteration;
	  el4.value=des;
	  el4.disabled = true;
	  el4.size = '100px';
	  cell4.appendChild(el4);	
	  
	  var cell5 = row.insertCell(4);
	  var el5 = document.createElement('input');
	  el5.type = 'button';
	  el5.name = 'txtRow' + iteration;
	  el5.id = iteration+'_'+btnID;
	  el5.value='Remove';
	  el5.setAttribute('onclick', 'removeItem(this)'); 
	  el5.size = '100px';
	  cell5.appendChild(el5);	
	    
	  strutsRowCount++;
	  return false;
}

function createPoleTableRow(category,selectedText,poleType,fromCon,toCon,pointerType,quantity,btnID){		  
	
	 	
	  var tbl = document.getElementById('list_table1');
	  var lastRow = poleRowCount;
	  // if there's no header row in the table, then iteration = lastRow + 1
	  var iteration = lastRow;
	  var row = tbl.insertRow(tbl.rows.length);	 
	  row.id = 'rowIDPole_'+iteration;
	 
	  var cell1 = row.insertCell(0);
	  var el1 = document.createElement('input');
	  el1.type = 'text';
	  el1.name = 'txtPoleRowcategory' + iteration;
	  el1.id = 'txtPoleRowcategory' + iteration;
	  el1.value = category;
	  el1.disabled = true;
	  el1.size = '200px';
	  cell1.appendChild(el1);
	  
	  var cell2 = row.insertCell(1);
	  var el2 = document.createElement('input');
	  el2.type = 'text';
	  el2.name = 'txtPoleTypeRowCatName' + iteration;
	  el2.id = 'txtPoleTypeRowCatName' + iteration;
	  el2.size = '80px';
	  el2.disabled = true;
	  el2.value=selectedText;
	  cell2.appendChild(el2);
	
	  
	  var cell3 = row.insertCell(2);
	  var el3 = document.createElement('input');
	  el3.type = 'text';
	  el3.name = 'txtPoleTypeRowitem' + iteration;
	  el3.id = 'txtPoleTypeRowitem' + iteration;
	  el3.size = '80px';
	  el3.disabled = true;
	  el3.value=poleType;
	  cell3.appendChild(el3);	 
	   
	  var cell4 = row.insertCell(3);
	  var el4 = document.createElement('input');
	  el4.type = 'text';
	  el4.name = 'txtPoleRowfromCon' + iteration;
	  el4.id = 'txtPoleRowfromCon' + iteration;
	  el4.value=fromCon;
	  el4.disabled = true;
	  el4.size = '100px';
	  cell4.appendChild(el4);
	  
	  var cell5 = row.insertCell(4);
	  var el5 = document.createElement('input');
	  el5.type = 'text';
	  el5.name = 'txtPoleRowtoCon' + iteration;
	  el5.id = 'txtPoleRowtoCon' + iteration;
	  el5.value=toCon;
	  el5.disabled = true;
	  el5.size = '100px';
	  cell5.appendChild(el5);	
	  
	  var cell6 = row.insertCell(5);
	  var el6 = document.createElement('input');
	  el6.type = 'text';
	  el6.name = 'txtPointerType' + iteration;
	  el6.id = 'txtPointerType' + iteration;
	  el6.value=pointerType;
	  el6.disabled = true;
	  el6.size = '100px';
	  cell6.appendChild(el6);	
	  
	  var cell7 = row.insertCell(6);
	  var el7 = document.createElement('input');
	  el7.type = 'text';
	  el7.name = 'txtPoleRowQuantity' + iteration;
	  el7.id = 'txtPoleRowQuantity' + iteration;
	  el7.onkeypress = onlyNumbers;
	  el7.value=quantity;
	  el7.disabled = false;
	  el7.size = '100px';
	  cell7.appendChild(el7);  
	  
	  
	  var cell8 = row.insertCell(7);
	  var el8 = document.createElement('input');
	  el8.type = 'button';
	  el8.name = 'txtRow' + iteration;
	  el8.id = iteration+'_'+btnID;
	  el8.value='Remove';
	  el8.setAttribute('onclick', 'removePoleItem(this)'); 
	  el8.size = '100px';
	  cell8.appendChild(el8);	
	    
	  poleRowCount++;
	  return false;
}


function removeItem(ctrl){
		
	var idArray=ctrl.id.split("_");

	var ctrlID = idArray[0];
	var btnID = idArray[1];	
	
	var ctrlID = idArray[0];
	var btnID = idArray[1];		
	
	var rowcategoryID = 'tdStrutsCategoryId_'+ctrlID; 
	
	if(document.getElementById(rowcategoryID) && document.getElementById(rowcategoryID)!=null){
		var categoryID =document.getElementById(rowcategoryID).innerHTML;
		
		 if(strutsRemoveList=="")
			  strutsRemoveList = categoryID;
		 else
			  strutsRemoveList = strutsRemoveList+":"+categoryID;
		
	}
	
	var removeIndex=Number(ctrlID)-1;
	if(strutList==undefined){
		alert("Cant remove");
		return false;
	}else{
		if(removeIndex>=0 && strutList.length>removeIndex){
			strutList[removeIndex]="";
		}
	}
	
	
	

	var tbl = document.getElementById('list_table2');
	if(tbl){				
		var rwid = 'rowID_'+ctrlID;
		var row = document.getElementById(rwid);	
		if(row)
			  row.parentNode.removeChild(row);					
		}						
		
	return false;
}

function removePoleItem(ctrl){
	
	var idArray=ctrl.id.split("_");

	var ctrlID = idArray[0];
	var btnID = idArray[1];		
	
	var rowcategoryID = 'tdPoleCategoryId_'+ctrlID; 
	var rowpoleTypeID = 'tdPoleType_'+ctrlID; 
	var rowPoleFromConID = 'tdPolefromConductor_'+ctrlID; 
	var rowPoleToConID = 'tdPoletoConductor_'+ctrlID; 
	var rowPolePointerID = 'tdPolePointerType_'+ctrlID; 
	
	var categoryID ="";
	var poleType ="";
	var fromCon ="";
	var toCon ="";
	var pointerType = "";

	
	if(document.getElementById(rowcategoryID) && document.getElementById(rowcategoryID)!=null){
		 categoryID =document.getElementById(rowcategoryID).innerHTML;
		
	}
	if(document.getElementById(rowpoleTypeID) && document.getElementById(rowpoleTypeID)!=null){
		poleType =document.getElementById(rowpoleTypeID).innerHTML;
		
	}
	if(document.getElementById(rowPoleFromConID) && document.getElementById(rowPoleFromConID)!=null){
		fromCon =document.getElementById(rowPoleFromConID).innerHTML;
		
	}
	if(document.getElementById(rowPoleToConID) && document.getElementById(rowPoleToConID)!=null){
		toCon =document.getElementById(rowPoleToConID).innerHTML;
		
	}
	if(document.getElementById(rowPolePointerID) && document.getElementById(rowPolePointerID)!=null){
		pointerType =document.getElementById(rowPolePointerID).innerHTML;
		
	}
	
	
	
	 if(poleRemoveList=="")
		 poleRemoveList = categoryID+","+poleType+","+fromCon+","+toCon+","+pointerType;
	 else
		 poleRemoveList = poleRemoveList+":"+categoryID+","+poleType+","+fromCon+","+toCon+","+pointerType;
	
	var removeIndex=Number(ctrlID)-1;
	if(poleList==undefined){
		alert("Cant remove");
		return false;
	}else{
		if(removeIndex>=0 && poleList.length>removeIndex){
			poleList[removeIndex]="";
		}
		
	}
	
	
	
	var tbl = document.getElementById('list_table1');
	if(tbl){				
		var rwid = 'rowIDPole_'+ctrlID;
		var row = document.getElementById(rwid);	
		if(row)
			  row.parentNode.removeChild(row);					
		}						
		
	return false;
}


function removeStayItem(ctrl){
	
	var idArray=ctrl.id.split("_");

	var ctrlID = idArray[0];
	var btnID = idArray[1];	
	
	var rowcategoryID = 'tdStayCategoryId_'+ctrlID; 
	var rowcategoryCtrlID = 'tdStayCtrlCategoryId_'+ctrlID; 
	
	if(document.getElementById(rowcategoryID) && document.getElementById(rowcategoryID)!=null && document.getElementById(rowcategoryCtrlID) && document.getElementById(rowcategoryCtrlID)!=null){
		var categoryID =document.getElementById(rowcategoryID).innerHTML;
		var categoryCtrlID =document.getElementById(rowcategoryCtrlID).innerHTML;
		 if(stayRemoveList=="")
			 stayRemoveList = categoryID+","+categoryCtrlID;
		 else
			 stayRemoveList = stayRemoveList+":"+categoryID+","+categoryCtrlID;
	}

	var removeIndex=Number(ctrlID)-1;
	if(stayList==undefined){
		alert("Cant remove");
		return false;
	}else
	{
		if(removeIndex>=0 && stayList.length>removeIndex){
			stayList[removeIndex]="";
		}
	}
	
	var tbl = document.getElementById('list_table3');
	if(tbl){				
		var rwid = 'rowIDStay_'+ctrlID;
		var row = document.getElementById(rwid);	
		if(row)
			  row.parentNode.removeChild(row);					
		}						
		
	return false;
}

function removeServiceLengthItem(ctrl){
	
	var idArray=ctrl.id.split("_");

	var ctrlID = idArray[0];
	var btnID = idArray[1];	
	
	var rowcategoryID = 'tdSerLengthCategoryId_'+ctrlID; 
	var rowItemID = 'tdSerLengthItemId_'+ctrlID; 
	
	var removeIndex=Number(ctrlID)-1;
	if(serlenList==undefined){
		alert("Cant remove");
		return false;
	}else{
		if(removeIndex>=0 && serlenList.length>removeIndex){
			serlenList[removeIndex]="";
		}
	}

	
	if(document.getElementById(rowcategoryID) && document.getElementById(rowcategoryID)!=null){
		var categoryID =document.getElementById(rowcategoryID).innerHTML;
		
		 if(serLenRemoveList=="")
			 serLenRemoveList = categoryID;
		 else
			 serLenRemoveList = serLenRemoveList+":"+categoryID;
	}
	
	// Set total service wire length
	// Exixsting row	
	var serviceLenQuantityID = 'tdSerLengthQuantityId_'+ctrlID; 	
	if(document.getElementById(serviceLenQuantityID) && document.getElementById(serviceLenQuantityID)!=null){
		var serLengthQuantity =document.getElementById(serviceLenQuantityID).innerHTML;		
		totalWireLength = totalWireLength - Number(serLengthQuantity);
		totalexistingwireLength = totalexistingwireLength - Number(serLengthQuantity);
		document.getElementById('txfdttotalLength').value = totalWireLength;
		
	}
	
	// Newly added row
	var serviceLenQtyRowID  = 'txtSerRowqty'+ctrlID;
	if(document.getElementById(serviceLenQtyRowID) && document.getElementById(serviceLenQtyRowID)!=null){
		  var serLenQty =document.getElementById(serviceLenQtyRowID).value;		 
		  totalWireLength = totalWireLength - Number(serLenQty);
		  document.getElementById('txfdttotalLength').value = totalWireLength;
		
	}
	
	
	var tbl = document.getElementById('list_table4');
	if(tbl){				
		var rwid = 'rowIDSer_'+ctrlID;
		var row = document.getElementById(rwid);	
		if(row)
			  row.parentNode.removeChild(row);					
		}						
		
	
	
	return false;
}

function saveValues(){
	
	 var tbl1 = document.getElementById('list_table1');
	 var lastRow1 = tbl1.rows.length;
	  	  
	 var tbl2 = document.getElementById('list_table2');
	 var lastRow2 = tbl2.rows.length;
	 	  
	 var tbl3 = document.getElementById('list_table3');
	 var lastRow3 = tbl3.rows.length;
	  	  
	 var tbl4 = document.getElementById('list_table4');
	 var lastRow4 = tbl4.rows.length;	  	 
	 
	 
	 var existingWireQuantities = "";
	 	for(a=0; a<=serLenRowCount-1; a++){
	 		var servicerowQtyID = 'tdSerLengthQuantityId_'+a;  		
	 		 
	 		 if(document.getElementById(servicerowQtyID) && document.getElementById(servicerowQtyID)!=null){
	 				var serviceWireqty =document.getElementById(servicerowQtyID).innerHTML;		
	 				 if(existingWireQuantities=="")
	 					 existingWireQuantities = serviceWireqty;
	 				  else
	 					  existingWireQuantities = existingWireQuantities+","+serviceWireqty; 			 
	 			
	 			}
	 	}
	 	 
	  if(document.getElementById("hid_isValueFilled").value=="" && document.getElementById("hid_isValueFilled").value.length==0){
		 	alert("Please fill the details");
		 	return false;
	  }else if((document.getElementById("txfdttotalLength").value==null)||(document.getElementById("txfdttotalLength").value=="" ) || (document.getElementById("txfdttotalLength").value=="" )){
		 	alert("Please mention the service wire length which can be 0");
		 	document.getElementById("txfdttotalLength").select();
		 	return false;
	  }else if(document.getElementById("noOfSpans").value=="" || document.getElementById("noOfSpans").value.length==0){
		  	alert("Please fill number of spans");
		  	return false;
	  }else if(document.getElementById("txfdsin").value=="" || document.getElementById("txfdsin").value.length==0){
		  	alert("Please mention the SIN number");
		  	return false;
	  }else if(document.getElementById("RadiowireTypeOH").checked && (document.getElementById("noOfSpans").value=="" || document.getElementById("noOfSpans").value=="0" || document.getElementById("noOfSpans").value.length==0)){
		  	alert("Please specify number of spans greater than 0 for OH");
		  	return false;
	  }else if ((document.getElementById("txtdistancetoServicePlace").value==null)||(document.getElementById("txtdistancetoServicePlace").value=="")){
			alert("Please fill the Distance to service place");
			document.getElementById("txtdistancetoServicePlace").select();
			return false;
	  }else if ((document.getElementById("RadioisLoopTypeN").checked && document.getElementById("RadiowireTypeUG").checked)){
		  if(document.getElementById("cableTypeList").value =='-1'){
			  alert("Please select a cable type");
			  return false;
		  }
	  }else if (!((document.getElementById("RadioisLoopTypeN").checked || document.getElementById("RadioisLoopTypeY").checked))){
		  alert("Please select is loop or not");
		  return false;
	  }else if(document.getElementById("RadiowireTypeOH").checked && document.getElementById("txfdappSerType").value=="Three Phase"){
		  if(document.getElementById("RadiowireisConversionY").checked ==false && document.getElementById("RadiowireisConversionN").checked ==false){
			  alert("Please specify whether a conversion is required");
			  return false;
		  }else if(document.getElementById("RadiowireisConversionY").checked && (document.getElementById("txtConversionlength").value=="" || document.getElementById("txtConversionlength").value=="0" || document.getElementById("txtConversionlength").value.length==0)){
			  alert("Please specify the conversion length greater than 0");
			  return false;
		  }
	  }
	 	
	 var selectedStringPole="";	
	 var selectedStringStruts="";	
	 var selectedStringStays="";	
	 var selectedStringSerLength="";	
	 
	 for (i=1; i<=poleRowCount-1; i++) {	
		  			  
			  var catogoryRowID = 'txtPoleRowcategory'+(i);
			  var itemRowID = 'txtPoleTypeRowitem'+(i);
			  var poleFromConRowID = 'txtPoleRowfromCon'+(i);
			  var poleFromToRowID = 'txtPoleRowtoCon'+(i);
			  var poleTypeRowId	= 'txtPoleTypeRowitem'+(i);
			  var poleQtyRowID = 'txtPoleRowQuantity'+(i);	
			  var polePointerRowID = 'txtPointerType'+(i);
			 			  			  
			  var categoryID="";
			  var itemName="";
			  var poleFromCon="";
			  var poleToCon="";
			  var itemQty="";
			  var poleType="";
			  var pointerType="";
			 			  
			  if(document.getElementById(catogoryRowID) && document.getElementById(catogoryRowID)!=null)
				  categoryID =document.getElementById(catogoryRowID).value;
			  
			  if(document.getElementById(itemRowID) && document.getElementById(itemRowID)!=null)			 
				  itemName = document.getElementById(itemRowID).value;
			 
			  if(document.getElementById(poleFromConRowID) && document.getElementById(poleFromConRowID)!=null)
				  poleFromCon =document.getElementById(poleFromConRowID).value;
			  
			  if(document.getElementById(poleFromToRowID) && document.getElementById(poleFromToRowID)!=null)
				  poleToCon =document.getElementById(poleFromToRowID).value;
			  
			  if(document.getElementById(poleTypeRowId) && document.getElementById(poleTypeRowId)!=null)
				  poleType =document.getElementById(poleTypeRowId).value;
			 			  
			  if(document.getElementById(poleQtyRowID) && document.getElementById(poleQtyRowID)!=null)
				  itemQty =document.getElementById(poleQtyRowID).value;			 	  
			 
			  
			  if(document.getElementById(polePointerRowID) && document.getElementById(polePointerRowID)!=null)
				  pointerType =document.getElementById(polePointerRowID).value;			 	  
			
			  
			 	if(categoryID.length > 0 && itemQty.length <= 0){
			  		alert("Pole quantity can not be empty for pole type "+itemName);
			  		return false;
			  	}
			  
			  if(categoryID.length>0 && itemName.length>0 && poleFromCon.length>0 && poleToCon.length>0 && itemQty.length>0 && poleType.length>0){
				  if(selectedStringPole=="")
					  selectedStringPole = categoryID+","+itemName+","+poleFromCon+","+poleToCon+","+itemQty+","+poleType+","+pointerType;
				  else
					  selectedStringPole = selectedStringPole+":"+categoryID+","+itemName+","+poleFromCon+","+poleToCon+","+itemQty+","+poleType+","+pointerType;
			  }			 		     	
	  }	 	 	 
	 	 
	 for (j=1; j<=strutsRowCount-1; j++) {	
			  
		  var strutsCatogoryRowID = 'txtRowcategoryID'+(j);
		  var strutsItemRowID = 'txtRowitem'+(j);
		  var strutsQuantityRowID = 'txtRowqty'+(j);
		  var strutsDescriptionRowID = 'txtRowdes'+(j);		  	  			  			  
		 			  			  
		  var strutscategoryID="";
		  var strutsitemName="";
		  var strutsquantity="";
		  var strutsDescription="";
		  
		 			  
		  if(document.getElementById(strutsCatogoryRowID) && document.getElementById(strutsCatogoryRowID)!=null)
			  strutscategoryID =document.getElementById(strutsCatogoryRowID).value;
		  
		  if(document.getElementById(strutsItemRowID) && document.getElementById(strutsItemRowID)!=null)			 
			  strutsitemName = document.getElementById(strutsItemRowID).value;
		 
		  if(document.getElementById(strutsQuantityRowID) && document.getElementById(strutsQuantityRowID)!=null)
			  strutsquantity =document.getElementById(strutsQuantityRowID).value;
		  
		  if(document.getElementById(strutsDescriptionRowID) && document.getElementById(strutsDescriptionRowID)!=null)
			  strutsDescription =document.getElementById(strutsDescriptionRowID).value;	 	  
		  	
		  	if(strutscategoryID.length >0 && strutsquantity.length <= 0){
		  		alert("Struts quantity can not be empty for item "+strutsitemName);
		  		return false;
		  	}
		 
		  if(strutscategoryID.length>0 && strutsitemName.length>0 && strutsquantity.length>0 ){
			  if(selectedStringStruts=="")
				  selectedStringStruts = strutscategoryID+","+strutsitemName+","+strutsquantity+","+strutsDescription;
			  else
				  selectedStringStruts = selectedStringStruts+":"+strutscategoryID+","+strutsitemName+","+strutsquantity+","+strutsDescription;
		  }			 		     	
	 }	 
	 for (k=1; k<=stayRowCount -1; k++) {	
		  
		  var stayCatogoryRowID = 'txtStayRowcategoryID'+(k);
		  var stayItemRowID = 'txtStayRowitem'+(k);
		  var stayCtrlItemRowID = 'txtStayControlRowitem'+(k);
		  var stayQuantityRowID = 'txtStayRowqty'+(k);
		  var stayDescriptionRowID = 'txtStayRowdes'+(k);		  	  			  			  
		 			  			  
		  var staycategoryID="";
		  var stayitemName="";
		  var stayquantity="";
		  var staydescription="";
		  var stayCtrlItemtype="";
		  
		 			  
		  if(document.getElementById(stayCatogoryRowID) && document.getElementById(stayCatogoryRowID)!=null)
			  staycategoryID =document.getElementById(stayCatogoryRowID).value;
		  
		  if(document.getElementById(stayItemRowID) && document.getElementById(stayItemRowID)!=null)			 
			  stayitemName = document.getElementById(stayItemRowID).value;
		 
		  if(document.getElementById(stayQuantityRowID) && document.getElementById(stayQuantityRowID)!=null)
			  stayquantity =document.getElementById(stayQuantityRowID).value;
		  
		  if(document.getElementById(stayDescriptionRowID) && document.getElementById(stayDescriptionRowID)!=null)
			  staydescription =document.getElementById(stayDescriptionRowID).value;	 	  
		  	
		  if(document.getElementById(stayCtrlItemRowID) && document.getElementById(stayCtrlItemRowID)!=null)
			  stayCtrlItemtype =document.getElementById(stayCtrlItemRowID).value;	 	  
		
		  
			if(staycategoryID.length >0 && stayquantity.length <= 0){
		  		alert("Stay quantity can not be empty for item "+stayitemName);
		  		return false;
		  	}
		  
		  if(staycategoryID.length>0 && stayitemName.length>0 && stayquantity.length>0 ){
			  if(selectedStringStays=="")
				  selectedStringStays = staycategoryID+","+stayitemName+","+stayquantity+","+staydescription+","+stayCtrlItemtype;
			  else
				  selectedStringStays = selectedStringStays+":"+staycategoryID+","+stayitemName+","+stayquantity+","+staydescription+","+stayCtrlItemtype;
		  }			 		     	
	 }	 
	 	
	 
	 for (l=1; l<=serLenRowCount -1; l++) {	
		  
		  var serviceLenCatogoryRowID = 'txtSerRowcategoryID'+(l);
		  var serviceLenItemRowID = 'txtSerRowitem'+(l);
		  var serviceLenQuantityRowID = 'txtSerRowqty'+(l);
		  var serviceLenDescriptionRowID = 'txtSerRowdes'+(l);			    
		 			  			  
		  var serviceLencategoryID="";
		  var serviceLenitemName="";
		  var serviceLenquantity="";
		  var serviceLendescription="";		  
		 			  
		  if(document.getElementById(serviceLenCatogoryRowID) && document.getElementById(serviceLenCatogoryRowID)!=null)
			  serviceLencategoryID =document.getElementById(serviceLenCatogoryRowID).value;
		  
		  if(document.getElementById(serviceLenItemRowID) && document.getElementById(serviceLenItemRowID)!=null)			 
			  serviceLenitemName = document.getElementById(serviceLenItemRowID).value;
		  
		  if(document.getElementById(serviceLenQuantityRowID) && document.getElementById(serviceLenQuantityRowID)!=null)
			  serviceLenquantity =document.getElementById(serviceLenQuantityRowID).value;
		  
		  if(document.getElementById(serviceLenDescriptionRowID) && document.getElementById(serviceLenDescriptionRowID)!=null)
			  serviceLendescription =document.getElementById(serviceLenDescriptionRowID).value;	 	  
		  			 
		  if(serviceLencategoryID.length>0 && serviceLenquantity.length <= 0){
		  		alert("Service wire length can not be empty for item "+serviceLenitemName);
		  		return false;
		  	}
		  
		  if(serviceLencategoryID.length>0 && serviceLenitemName.length>0 && serviceLenquantity.length>0 ){
			  if(selectedStringSerLength=="")
				  selectedStringSerLength = serviceLencategoryID+","+serviceLenitemName+","+serviceLenquantity+","+serviceLendescription;
			  else
				  selectedStringSerLength = selectedStringSerLength+":"+serviceLencategoryID+","+serviceLenitemName+","+serviceLenquantity+","+serviceLendescription;
		  }			 		     	
	 	}	 
	 
	 	 
	  var answer = confirm ("Are you sure you want to save the details?");
	  if (!answer)
	  		return false;	
	  
	  if(document.getElementById('hid_selectedStringPoles'))
	  		document.getElementById('hid_selectedStringPoles').value=selectedStringPole;
		
	  if(document.getElementById('hid_selectedStringStruts'))
		  	document.getElementById('hid_selectedStringStruts').value=selectedStringStruts;
		
	  if(document.getElementById('hid_selectedStringStays'))
		  	document.getElementById('hid_selectedStringStays').value=selectedStringStays;
		
	  if(document.getElementById('hid_SavedWireLengths'))
		  	document.getElementById('hid_SavedWireLengths').value=existingWireQuantities;		
	  
	  if(document.getElementById('hid_selectedStringSerLengths'))
		  	document.getElementById('hid_selectedStringSerLengths').value=selectedStringSerLength;
	  
	  if(document.getElementById('hid_RemoveStruts'))
	  		document.getElementById('hid_RemoveStruts').value=strutsRemoveList;
		
	  if(document.getElementById('hid_RemoveStays'))
		  	document.getElementById('hid_RemoveStays').value=stayRemoveList;
		
	  if(document.getElementById('hid_RemoveSerLengths'))
		  	document.getElementById('hid_RemoveSerLengths').value=serLenRemoveList;
		
	  if(document.getElementById('hid_RemovePoles'))
		  	document.getElementById('hid_RemovePoles').value=poleRemoveList;
	  
	
	return true;	
}

function removeAll(){
		 
	 for (i=1; i<=poleRowCount; i++) {	
		 var rwid = 'rowIDPole_'+i;
		 var row = document.getElementById(rwid);	
			if(row)
				  row.parentNode.removeChild(row);					
			}	
	 
	 for (j=1; j<=strutsRowCount; j++) {	
		 var rwid = 'rowID_'+j;
		 var row = document.getElementById(rwid);	
			if(row)
				  row.parentNode.removeChild(row);					
			}
	 
	 for (k=1; k<=stayRowCount; k++) {	
		 var rwid = 'rowIDStay_'+k;
		 var row = document.getElementById(rwid);	
			if(row)
				  row.parentNode.removeChild(row);					
			}
	 
	 for (l=1; l<=serLenRowCount; l++) {	
		 var rwid = 'rowIDSer_'+l;
		 var row = document.getElementById(rwid);	
			if(row)
				  row.parentNode.removeChild(row);					
			}
	 
	  setControls(true);
	  if(document.getElementById('hid_selectedStringPoles'))
		  	document.getElementById('hid_selectedStringPoles').value="";
			
		  if(document.getElementById('hid_selectedStringStruts'))
			  	document.getElementById('hid_selectedStringStruts').value="";
			
		  if(document.getElementById('hid_selectedStringStays'))
			  	document.getElementById('hid_selectedStringStays').value="";
			
		  if(document.getElementById('hid_selectedStringSerLengths'))
			  	document.getElementById('hid_selectedStringSerLengths').value="";
	 return false;
}
		
function callPrintEstimate()  
{ 
	var appName = document.getElementById("txfdappName").value;
	var area  = document.getElementById("txfdappArea").value;
	var appSCS  = document.getElementById("txfdappCSC").value;
    
    var address = document.getElementById("txfdappAddress").value;
    
    var street;
    var suburb;
    var city;
    
    var addArray = address.split(";");
    
    if(addArray.length>2){
    	street =   addArray[0];
    	suburb =  addArray[1];
    	city =  addArray[2];
    	  
    }
    
    var neighborAcct = document.getElementById("txfdneighborsAcct").value;
    var tPhoneNo = document.getElementById("txfdappTphone").value;
    var applicationNo  = document.getElementById("txfdappNo").value;
    var serviceType  = document.getElementById("txfdappSerType").value+"/"+document.getElementById("txfdconnectionType").value;
    var tariff  = document.getElementById("txfdappTariff").value;
    var subStation  = document.getElementById("txfdsubstation").value;
    var distancesFromSS  = document.getElementById("txfddistanceFrmSS").value;
    var transformerCapacity =document.getElementById("txfdtransformerCapacity").value;
    var transformerLoad = document.getElementById("txfdtransformerLoad").value;
    var peakLoad  = document.getElementById("txfdtransformerPeakLoad").value;
    var feederCtrlType = document.getElementById("txfdfeederControltype").value;
    var sin  = document.getElementById("txfdsin").value;
    var phase  =document.getElementById("txfdphase").value;
    var noOfPoles  = document.getElementById("txfdpoleNo").value;
    

    
    document.serviceEstimateApplet.print(appName,area,appSCS,street,suburb,city,"",applicationNo,tPhoneNo,serviceType,
    		tariff,subStation,distancesFromSS,transformerCapacity,transformerLoad,peakLoad,feederCtrlType,sin,phase,noOfPoles,neighborAcct
    		); 
   
} 


function setCableType(){
		
	if(document.getElementById("RadioisLoopTypeN").checked){
		if(document.getElementById("RadiowireTypeUG").checked){
			document.getElementById("tdLableCableType").style.visibility = "visible";
			document.getElementById("tdListCableType").style.visibility = "visible";
		}else{
			document.getElementById("tdLableCableType").style.visibility = "hidden";
			document.getElementById("tdListCableType").style.visibility = "hidden";
		}
	}else{
		document.getElementById("tdLableCableType").style.visibility = "hidden";
		document.getElementById("tdListCableType").style.visibility = "hidden";
	}
	
	if(document.getElementById("RadiowireTypeOH").checked){
		document.getElementById("tdLableIsConversion").style.visibility = "visible";
		document.getElementById("tdRadioIsConversion").style.visibility = "visible";
		if(document.getElementById("RadiowireisConversionY").checked){
			document.getElementById("tdLableConversionLength").style.visibility = "visible";
			document.getElementById("tdtxtConversionlength").style.visibility = "visible";
		}else{
			document.getElementById("tdLableConversionLength").style.visibility = "hidden";
			document.getElementById("tdtxtConversionlength").style.visibility = "hidden";
		}
		
	}else{
		document.getElementById("tdLableIsConversion").style.visibility = "hidden";
		document.getElementById("tdRadioIsConversion").style.visibility = "hidden";
		document.getElementById("tdLableConversionLength").style.visibility = "hidden";
		document.getElementById("tdtxtConversionlength").style.visibility = "hidden";
	}
}

function setConversionLength(){
	if(document.getElementById("RadiowireisConversionY").checked){
		document.getElementById("tdLableConversionLength").style.visibility = "visible";
		document.getElementById("tdtxtConversionlength").style.visibility = "visible";
	}else{
		document.getElementById("tdLableConversionLength").style.visibility = "hidden";
		document.getElementById("tdtxtConversionlength").style.visibility = "hidden";
	}
}

function setDecimalPoints(obj){	    
	       if(obj.value.indexOf('.')==(-1)){
	    	   if(obj.value.length>2){
	    		   obj.value =obj.value.substr(0,2)+"."+ obj.value.substr(2,2);
	    	   }else
	    		   obj.value =obj.value+".00";
	                
	          }  
	    return true;
}

function clearFields(){
	clearForm();
	return true;
}