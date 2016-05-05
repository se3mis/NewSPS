/**
 * struts-layout item swapper javascript functions
 */

/**
 * Remove empty options from a select object.
 */


 var checkedEstimations=0;
 
 function setSelectedList(ctrl)
 {
 	if(ctrl.checked == true)
 		checkedEstimations=checkedEstimations+1;
 	else
 		checkedEstimations=checkedEstimations-1;
 }

function jobClose()
{	
	var enteredDate;
	if(checkedEstimations==0){
		alert('Please select at least one job to close');
		return false;
	}else
	{		
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
		}	
	
		var answer = confirm ("Are you sure you want to close selected jobs?");
		if (!answer)
			return false;			
			
		if(document.getElementById('txtdclosingDate') && document.getElementById('datepicker'))
			document.getElementById('txtdclosingDate').value=enteredDate;	
				
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

function resetCheck(){
	checkedEstimations=0;
}

function setJobNos(){
	document.forms[0].submit();
}