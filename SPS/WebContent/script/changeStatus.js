function closeForm(){
	window.close();
	return false;
}

function on_Load(){
	if(document.getElementById("hid_appontId")){
		if(document.getElementById("hid_appontId").value==''){	
			if(document.getElementById("btnChangeStatus"))
				document.getElementById("btnChangeStatus").disabled = true;
		}
	}
}

function changeAptStatus(){
	
	if(document.getElementById("listappointmentStatus")){
		if(document.getElementById("listappointmentStatus").selectedIndex=='0'){
			alert('Please select a status to update');
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
	
	
	return true;

}