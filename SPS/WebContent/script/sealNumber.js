function setSealNumber(){	
	if(document.getElementById("sealNum")){
		var sealNumber=document.getElementById("sealNum").value ;
		 if(sealNumber.lenght==0 || sealNumber==''){
			 alert("Please enter a valid seal number");
			  return false;
		 }
		 else
			 window.returnValue = sealNumber;
	}
	
	window.close();
}

function closeWindow(){
	window.returnValue = 'cancel';
	window.close();
}