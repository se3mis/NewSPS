function on_load(){
	
	if(document.getElementById("listContractor").selectedIndex && document.getElementById("listContractor").selectedIndex!=-1)
		{
		document.getElementById("btnDeAllocate").disabled = false;	
		if(document.getElementById("btnFinish"))
			document.getElementById("btnFinish").disabled = false;
		}
	else{
		document.getElementById("datepicker").disabled = true;
		document.getElementById("btnDeAllocate").disabled = true;
		if(document.getElementById("btnFinish"))
			document.getElementById("btnFinish").disabled = true;
		document.getElementById("datepicker").value = "";		
	}
}

function setContractorDetails()
{	
	document.getElementById("listContractorID").selectedIndex =document.getElementById("listContractor").selectedIndex;
	document.forms[0].submit(); 
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
			  var amtrowID = 'tdamtrow_'+(i-1);
			  
			  chkcount++;
			  
			  var elementJob =document.getElementById(jobrowID).innerHTML;
			  var elementAmount =document.getElementById(amtrowID).innerHTML;		  
			  
			  if(selectedString=="")
				  selectedString = elementJob+","+elementAmount;
			  else
				  selectedString = selectedString+":"+elementJob+","+elementAmount;
		  }		    	
	 }
	  
	  if(chkcount==0){
		  alert("Please select at least one job");
		  return false;
	  }
	  
	  var answer = confirm ("Are you sure you want to de allocate selected jobs?");
		if (!answer)
			return false;	
	  
	if(document.getElementById('refList'))
		document.getElementById('refList').value=selectedString;
	
	
	return true;
	
}

function finishJobs(){
	
	
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
			  var amtrowID = 'tdamtrow_'+(i-1);
			  
			  chkcount++;
			  
			  var elementJob =document.getElementById(jobrowID).innerHTML;
			  var elementAmount =document.getElementById(amtrowID).innerHTML;		  
			  
			  if(selectedString=="")
				  selectedString = elementJob+","+elementAmount;
			  else
				  selectedString = selectedString+":"+elementJob+","+elementAmount;
		  }		    	
	  }
	  
	  if(chkcount==0){
		  alert("Please select at least one job");
		  return false;
	  }
		 
	var answer = confirm ("Are you sure you want to finish the selected jobs?");
		if (!answer)
			return false;	
	 
	if(document.getElementById('refList'))
		document.getElementById('refList').value=selectedString;
	
	
	return true;
	
}