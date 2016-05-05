function closeForm(){
	window.close();
	return false;
}

function on_Load(){
	if(document.getElementById("hid_appontId")){
		if(document.getElementById("hid_appontId").value==''){	
			if(document.getElementById("btnEdit"))
				document.getElementById("btnEdit").disabled = true;
		}
	}
}

function addAppointment(){	
	var enteredString;
	if(document.getElementById("listuserName")){
		if(document.getElementById("listuserName").selectedIndex=='0'){
			alert('Please select a user for the schedule');
			return false;
		}
	}
	
	if(document.getElementById("listsessionName")){
		if(document.getElementById("listsessionName").selectedIndex=='0'){
			alert('Please select a session for the schedule');
			return false;
		}
	}	
	
	
	if(document.getElementById("appointmentDate") ){
			
		var today =new Date();
		enteredDate =  getDateString();
		
		if(enteredDate==undefined){
			alert('Please enter a correct appointment date which is ahead of todays date');
			return false;
		}	
		
		if (enteredDate<today && enteredDate!=today)
		 {
			alert('Please enter a correct appointment date which is ahead of todays date');
			return false;
		 }
	}	
	if(document.getElementById("txtDescription")){
		if(document.getElementById("txtDescription").value==''){
			var answer = confirm ("Are you sure you want to update the appointment without description?");
			if (!answer)
				return false;	
			
		}
	}	
	if(document.getElementById("hid_AptDate")){
		document.getElementById("hid_AptDate").value=enteredDate;
	}
	
	return true;

}



function getDateString() {
	
	  var picker = dojo.widget.byId("appointmentDate");
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

