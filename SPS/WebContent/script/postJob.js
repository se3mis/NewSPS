var checkedEstimations=0;

function validate_form(){
	if(checkedEstimations==0)	
	{
		alert("Please select a job to post");
		return false;
	}else
	{
		var answer = confirm ("Are you sure you want to update?");
		if (!answer)
			return false;		
	}
}

function setSelectedList(ctrl)
{
	if(ctrl.checked == true)
		checkedEstimations=checkedEstimations+1;
	else
		checkedEstimations=checkedEstimations-1;
}

function unLoad(){
	 for (i=0; i<document.forms[0].length; i++)
	     document.forms[0].elements[i].checked = false; 
	 	
}

function resetCheck(){
	checkedEstimations=0;
}

function toggleButtons(state){
	if(document.getElementById("btnSave"))
		document.getElementById("btnSave").disabled=state;
	if(document.getElementById("btnClose"))
		document.getElementById("btnClose").disabled=state;
}

