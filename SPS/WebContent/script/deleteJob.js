function validate_form()
{	
	 var referenceNo = document.getElementById("listreferenceNo").value;
	 if(referenceNo=='-1')
	 {
		 alert('Please select a Reference Number to update');
		 return false;
	 }else{
		 var answer = confirm ("Are you sure you want to delete the estimate?");
			if (!answer)
				return false;
	 }		
}

function setDetails()
{
	if(document.getElementById("listReference") && document.getElementById("listreferenceNo"))
		document.getElementById("listReference").selectedIndex =document.getElementById("listreferenceNo").selectedIndex;
			
	if(document.getElementById("listReference") && document.getElementById("listReference").selectedIndex>0){
		var selecetedRef =document.getElementById("listReference").value;
		var selecetedRefArray = selecetedRef.split(",");
		if(selecetedRefArray.length>3){
			
			if(document.getElementById("txfdestimateDate"))
				document.getElementById("txfdestimateDate").value = selecetedRefArray[2];
			
			if(document.getElementById("txfdestimateCategory"))
				document.getElementById("txfdestimateCategory").value = selecetedRefArray[3];
		}
	}else{
		
		if(document.getElementById("txfdestimateDate"))
			document.getElementById("txfdestimateDate").value = "";
		
		if(document.getElementById("txfdestimateCategory"))
			document.getElementById("txfdestimateCategory").value = "";
	
	}	
}

function retrieveURL(url) 
{
if (window.XMLHttpRequest) 
{ 
// Non-IE browsers
			req = new XMLHttpRequest();       
			req.onreadystatechange = processStateChange;
			try {
				 req.open("GET", url, true);
			} catch (e) {
				 alert(e);
			}
			req.send(null);
		} else if (window.ActiveXObject) { // IE
		
			 req = new ActiveXObject("Microsoft.XMLHTTP");
			if (req) {
				 req.onreadystatechange = processStateChange;
				 req.open("GET", url, true);
				 req.send();
			 }
		}
  }

function processStateChange() {
	if (req.readyState == 4) { // Complete
	if (req.status == 200) { // OK response
		alert("success");		
		document.getElementById("txfdestimateDate").innerHTML = req.responseText;
		
	} else {
		 alert("Problem: " + req.statusText);
	 }
 }
}

function toggleButtons(state){
	
	if(document.getElementById("btnSave"))
		document.getElementById("btnSave").disabled=state;
	if(document.getElementById("btnClose"))
		document.getElementById("btnClose").disabled=state;
	if(document.getElementById("btnReset"))
		document.getElementById("btnReset").disabled=state;
}


function on_Load(){
	
	if(document.getElementById("listReference") && document.getElementById("listreferenceNo")){
		document.getElementById("listReference").selectedIndex = 0;
		document.getElementById("listreferenceNo").selectedIndex = 0;
	}
	
}
