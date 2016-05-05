function setContractors(){
	document.forms[0].submit();
}

function setContractorDetails()
{	
	document.getElementById("listContractorID").selectedIndex =document.getElementById("listContractor").selectedIndex;
	document.forms[0].submit(); 
}

function setJobDetails()
{		
	document.forms[0].submit(); 	
}

function on_load(){	
	if(document.getElementById("listContractorID")&& document.getElementById("listjobNumber")){
		if(document.getElementById("listContractorID").selectedIndex>0 && document.getElementById("listjobNumber").selectedIndex>0)
			toggleCtrls(false);
		else
			toggleCtrls(true);
	}
}


function toggleCtrls(state){
	
	if(document.getElementById('btnBill'))
		document.getElementById('btnBill').disabled = state;
	
	dojo.widget.byId("datepicker").disabled = state;
}

function saveBillValues(){
	if(document.getElementById('billReferenceNo') && document.getElementById('billReferenceNo').value == ""){
		alert("Please specify a bill reference number");
		return false;
	}else if(document.getElementById("datepicker") ){
		
		var today =new Date();
		enteredDate =  getDateString();
		
		if(enteredDate==undefined){
			alert('Please enter a correct bill date');
			return false;
		} else if (enteredDate>today && enteredDate!=today)
		 {
			alert('Please enter a correct bill date');
			return false;
		 }
		
		var answer = confirm ("Are you sure you want to bill the contractor?");
		if (!answer)
			return false;	
		
	} 	
	
}


function getDateString() {
	
	  var picker = dojo.widget.byId("datepicker");
	  var stringValue = picker.getValue();
	  var splitIndex = stringValue.indexOf("T");
	  var dateLength = stringValue.length;
	  var formattedString;
	  var myDate=new Date();
	  myDate.setFullYear(1000,10,10);
	  
	  if(splitIndex<dateLength){
		  var dateStr = stringValue.substring(0, splitIndex);			
		  var dateArray = dateStr.split("-");
		  
		  if(dateArray.length>2){
			  
			 myDate.setFullYear(dateArray[0],dateArray[1]-1,dateArray[2]);
			 			  
			   
		  }
	  } 
	  	
	  return myDate;
}

function clearTable(){
	
}