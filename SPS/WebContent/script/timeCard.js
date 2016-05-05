
function validate_form(){
	
	if(document.getElementById("txtdjobNo").value.trim()==""){
		alert('Please specify a Job Number');
		return false;
	}
		
	if(document.getElementById("listworkType") && document.getElementById("listworkType").selectedIndex==0){
		alert('Please select a working type');
		return false;
	}
		
	if(document.getElementById("txtdemployeeNo").value.trim()==""){
		alert('Please specify the employee number');
		return false;
	}
	
	if(document.getElementById("listdateType") && document.getElementById("listdateType").selectedIndex==0){
		alert('Please select a date type');
		return false;
	}
		
	if(document.getElementById("datepicker") ){
		
		var today =new Date();
		enteredDate =  getDateString();
		
					
		if(enteredDate==undefined){
			alert('Please enter a correct  date');
			return false;
		}	
		
		if (enteredDate>today && enteredDate!=today)
		 {
			alert('Please enter a correct date which is today or before');
			return false;
		 }
	}
	
	
	if(document.getElementById("txtdname").value.trim()==""){
		alert('Please specify the name');
		return false;
	}
	
	
	if(document.getElementById("txtddesignation").value.trim()==""){
		alert('Please specify the designation');
		return false;
	}
	
	var answer = confirm ("Are you sure you want to save the data?");
	if (!answer)
		return false;	
	
	
	return true;
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
