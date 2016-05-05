function createTableRow(){		  
	
	if(document.getElementById("listContractor").selectedIndex && document.getElementById("listContractor").selectedIndex!=-1)
	  		{
			  var tbl = document.getElementById('list_table');
			  var lastRow = tbl.rows.length;
			  // if there's no header row in the table, then iteration = lastRow + 1
			  var iteration = lastRow;
			  var row = tbl.insertRow(lastRow);	  
			 
			  var cell1 = row.insertCell(0);
			  var el1 = document.createElement('input');
			  el1.type = 'text';
			  el1.name = 'txtRow' + iteration;
			  el1.id = 'txtRow' + iteration;
			  el1.size = '80px';
			  cell1.appendChild(el1);
			  
			  var cell2 = row.insertCell(1);
			  var el2 = document.createElement('input');
			  el2.type = 'text';
			  el2.name = 'txtRow' + iteration;
			  el2.id = 'txtRow' + iteration;
			  el2.size = '100px';
			  cell2.appendChild(el2);
			  
			  var cell3 = row.insertCell(2);
			  var el3 = document.createElement('input');
			  el3.type = 'text';
			  el3.name = 'txtRow' + iteration;
			  el3.id = 'txtRow' + iteration;
			  el3.size = '120px';
			  cell3.appendChild(el3);
			  
			  var cell4 = row.insertCell(3);
			  var el4 = document.createElement('input');
			  el4.type = 'text';
			  el4.name = 'txtRow' + iteration;
			  el4.id = 'txtRow' + iteration;
			  el4.size = '120px';
			  cell4.appendChild(el4);			  		  
			  
			  el1.focus();
	  		}	    
	  return false;
}

function prepareSwap(line, aClass) {
	
	if (line.selectedForSwap && line.selectedForSwap==true) {
		line.selectedForSwap = false;
	} else {
		line.selectedForSwap = true;
	}

	var oTD = line.getElementsByTagName("TD");
	for (i=0;i<oTD.length;i++) {
		var className = oTD[i].className;		
		var oldClassName = oTD[i].oldClassName ? oTD[i].oldClassName : aClass;	
						
		oTD[i].className = oldClassName;
		oTD[i].oldClassName = className;	
		break;
	}
}

function removeRowFromTable()
{
  var tbl = document.getElementById('list_table');
  var lastRow = tbl.rows.length;
  for(var i=1;i<lastRow;i++)
	  tbl.deleteRow(lastRow-i);
    
}

function on_load(){
	
	var tbl = document.getElementById('list_table');
	var tblLength = tbl.rows.length ;
	var i;	
	
	if(document.getElementById("listContractor").selectedIndex && document.getElementById("listContractor").selectedIndex!=-1)
		{
		//document.getElementById("datepicker").disabled = false;
		document.getElementById("btnAllocate").disabled = false;		
		}
	else{
		document.getElementById("datepicker").disabled = true;
		document.getElementById("btnAllocate").disabled = true;
		document.getElementById("datepicker").value = "";
		}	
	
	 if(tblLength>0){		 
		 var elementallcID='tdallorow_0';
		 var allocateCell =document.getElementById(elementallcID);
		 if(allocateCell)
			 allocateCell.focus();
	 }	
}

function setContractorDetails()
{	
	document.getElementById("listContractorID").selectedIndex =document.getElementById("listContractor").selectedIndex;
	document.forms[0].submit(); 
}

function clearForm(){
	if(document.getElementById("txfdjobInHand"))
		document.getElementById("txfdjobInHand").value = "";
	
	if(document.getElementById("txfdtotalAmount"))
		document.getElementById("txfdtotalAmount").value = "";	
	
	if(document.getElementById("datepicker"))
		document.getElementById("datepicker").value = "";	
}

function setDate(state){	
	
	 var tbl = document.getElementById('list_table');
	    var lastRow = tbl.rows.length - 1;
	    var i;
	    for (i=1; i<=lastRow; i++) {
	    	if(state)
	    		tbl.rows[lastRow].cells[5].firstChild.value=document.getElementById("datepicker").value;
	    	else
	    		tbl.rows[lastRow].cells[5].firstChild.value="";	          
	      }

	return false;
}

function toggleElements(state){
	
	if(document.getElementById("listContractor"))
		document.getElementById("listContractor").disabled = state;
	
	if(document.getElementById("datepicker"))
		document.getElementById("datepicker").disabled = state;
	
	if(document.getElementById("txfdtotalAmount"))
		document.getElementById("txfdtotalAmount").disabled = state;
	
	if(document.getElementById("txfdjobInHand"))
		document.getElementById("txfdjobInHand").disabled = state;		
	
}

function validate_Form(){
	
}

function saveValues(){
		
	 var tbl = document.getElementById('list_table');
	 var lastRow = tbl.rows.length - 1;
	 var i;	
	 var chkcount=0;
	 var selectedString="";
	  for (i=1; i<=lastRow; i++) {	
		  var chkrow = 'tdchkrow_'+(i-1);
		  var chk =document.getElementById(chkrow).checked;
		  		  
		  if(chk==true){
			  
			  var jobrowID = 'tdjobrow_'+(i-1);
			  var consumerNameRowID = 'tdnamerow_'+(i-1);
			  var amountRowID = 'tdamtrow_'+(i-1);
			  chkcount++;
			  
			  var elementJob =document.getElementById(jobrowID).innerHTML;
			  var elementName =document.getElementById(consumerNameRowID).innerHTML;
			  var elementAmount =document.getElementById(amountRowID).innerHTML;
			  
			  if(selectedString=="")
				  selectedString = elementJob+";"+elementName+";"+elementAmount;
			  else
				  selectedString = selectedString+":"+elementJob+";"+elementName+";"+elementAmount;	 			  
			  
		  }		    	
	 }
	  
	  if(chkcount==0){
		  alert("Please select at least one job");
		  return false;
	  }

	  var answer = confirm ("Are you sure you want to allocate selected jobs?");
		if (!answer)
			return false;
			
	document.getElementById('refList').value=selectedString;
	return true;
	
}
