function addAppointment(){	
	var enteredString;
	if(document.getElementById("listuserName")){
		if(document.getElementById("listuserName").selectedIndex=='0'){
			alert('Please select an allocated user for the schedule');
			return false;
		}
	}
	
	if(document.getElementById("listapplicationNumber")){
		if(document.getElementById("listapplicationNumber").selectedIndex=='0'){
			alert('Please select an application number for the schedule');
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
			var answer = confirm ("Are you sure you want to put the appointment without description?");
			if (!answer)
				return false;	
			
		}
	}	
	if(document.getElementById("hid_AptDate")){
		var picker = dojo.widget.byId("appointmentDate");
		var stringValue = picker.getValue();
		document.getElementById("hid_AptDate").value=stringValue;
	}
	if(document.getElementById("branchType") == 'EM' || document.getElementById("branchType") == 'DM' || document.getElementById("branchType") == 'MU'){
		
		
	}else if(document.getElementById("construcRef")){
		if(document.getElementById("construcRef").value==''){
			alert('Please generate/select construction reference');
			return false;	
			
		}
	}
	return true;

}

function isValidDate(sText) {	
	alert(sText);
	var reDate =/^(0[1-9]|1[012])[- \/.](0[1-9]|[12][0-9]|3[01])[- \/.](19|20)\d\d$/;
    var x = reDate.test(sText);
    alert(x);
    return x;
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

function clearForm(){	
	if(document.getElementById("statusMsg")){		
		document.getElementById("statusMsg").value="";
	}
	
	return false;
	
}

function findAppointmentForDate(){
	
	if(document.getElementById("listuserName")){
		if(document.getElementById("listuserName").selectedIndex=='0'){
			alert('Please select an allocated user for the schedule');
			return false;
		}
	}
	
	if(document.getElementById("appointmentDate") ){
		enteredDate =  getDateString();	
		
		if(enteredDate==undefined){
			alert('Please enter a correct appointment date to find');
			return false;
		}	
	}
	
	var picker = dojo.widget.byId("appointmentDate");
	var stringValue = picker.getValue();
	var dateStr;
	
	if(stringValue.length>9)
		dateStr = stringValue.substring(0, 10);	
	else if(stringValue==null || stringValue==''|| stringValue.length==0){
		var d = new Date();
		dateStr = d.getFullYear()+"-"+(d.getMonth()+1)+"-"+d.getDate();
	}
			
	var userID = document.getElementById("listuserName").value;  
	document.forms[0].action="/SMC/calendar/web/schedule?selDate="+dateStr+"&allocateUser="+userID;
	document.forms[0].submit();
	
}