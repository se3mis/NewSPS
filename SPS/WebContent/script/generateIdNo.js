function validate_required(fieldValue,alerttxt){

  if (fieldValue==null||fieldValue=="")
    {
    alert(alerttxt);
    return false;
  }else{
    return true;
    }
  
}

function checkIdNoNotNull(){
	if((document.getElementById("txfdGeneratedId").value==null)||(document.getElementById("txfdGeneratedId").value=="") ){
		alert("id must be Generated!");
		document.getElementById("txfdIdNo").select();
		return false;
	}
	else return true;
}

function validate_form(){
	if(validate_required(document.getElementById("txfdGeneratedId").value,"Id Number must be Generated!")==false){
		//document.getElementById("txfdGeneratedId").select();
		return false;
	}
		
 }

function buttonLoader() {
	
}