var checkedApplications=0;

function validate_form(){
	if(checkedApplications==0)	
	{
		alert("Please select at least one application");
		return false;
	}else if(document.getElementById("listuserName")){
		if(document.getElementById("listuserName").selectedIndex=='0'){
			alert('Please select an allocated user for the schedule');
			return false;
		}
	}
	
	
	var answer = confirm ("Are you sure you want to create appointments?");
		if (!answer)
			return false;		
	
}

function setSelectedList(ctrl)
{
	if(ctrl.checked == true)
		checkedApplications=checkedApplications+1;
	else
		checkedApplications=checkedApplications-1;
}

function unLoad(){
	 for (i=0; i<document.forms[0].length; i++)
	     document.forms[0].elements[i].checked = false; 
	 	
}

function resetCheck(){
	checkedApplications=0;
}

function toggleButtons(state){
	if(document.getElementById("btnSave"))
		document.getElementById("btnSave").disabled=state;
	if(document.getElementById("btnClose"))
		document.getElementById("btnClose").disabled=state;
}

