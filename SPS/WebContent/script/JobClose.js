function validate_form(){
	
	var enteredDate;
	if(document.getElementById("listcostCenter") && document.getElementById("listcostCenter").selectedIndex==0){
		alert('Please select a cost center');
		return false;
	}
	
	if(document.getElementById("listjobNo") && document.getElementById("listjobNo").selectedIndex==0){
		alert('Please select a job number to close');
		return false;
	}		
		
	if(document.getElementById("datepicker") ){
		
		var today =new Date();
		enteredDate =  getDateString();
		
					
		if(enteredDate==undefined){
			alert('Please enter a correct appointment date which is ahead of todays date');
			return false;
		}	
		
		if (enteredDate>today && enteredDate!=today)
		 {
			alert('Please enter a correct appointment date which is today or before');
			return false;
		 }
	}
	
	var answer = confirm ("Are you sure you want to close the job?");
	if (!answer)
		return false;	
	
	if(document.getElementById('txtdclosingDate') && document.getElementById('datepicker'))
		document.getElementById('txtdclosingDate').value=enteredDate;	
	
	return true;
}


function setJobNos(){
	document.forms[0].submit();
}

function on_Load(){	
	
	if(document.getElementById("datepicker"))
		document.getElementById("datepicker").value="";
	
}


function getDateString() {
	
	  var picker = dojo.widget.byId("datepicker");
	  var stringValue = picker.getValue();
	  var splitIndex = stringValue.indexOf("T");
	  var dateLength = stringValue.length;
	  var formattedString;
	  var myDate=new Date();
	  myDate.setFullYear(3000,10,10);
	  	  
	  if(splitIndex<dateLength){
		  var dateStr = stringValue.substring(0, splitIndex);			
		  var dateArray = dateStr.split("-");
		  
		  if(dateArray.length>2){
			  
			 myDate.setFullYear(dateArray[0],dateArray[1]-1,dateArray[2]);
			 			  
			   
		  }
	  } 
	  	
	  return myDate;
}
