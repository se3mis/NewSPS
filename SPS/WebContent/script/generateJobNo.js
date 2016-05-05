function buttonLoader() {
	document.getElementById("btnGenerateJobNo").disabled = false;
	//document.getElementById("errorMessage").style.visibility="hidden";
	//document.getElementById("errorMessage").value="ovkjsdfsdf";
	//alert("state "+document.getElementById("state").value);
	//alert("hiddenSecondState "+document.getElementById("hiddenSecondState").value);
	if (document.getElementById("state").value == "NEW") {
		//alert("1");
		//document.getElementById("btnGenerateJobNo").disabled = false;
		//alert("2");
		//document.getElementById("btnModify").disabled = true;
		//alert("3");
		//document.getElementById("RadioEntitled4LoanN").checked =true;
		//alert("4");
		//document.getElementById("RadioEntitled4LoanY").disabled =true;//later
		//if(document.getElementById("errorMessage").value.length==0){
			//alert("no error");
		//	document.getElementById("btnConfirm").disabled = true;
		//}else {
			//alert("error");
		//}
		//disableLoanDetails();
		
	} else if (document.getElementById("state").value == "GENERATED") {
		//document.getElementById("btnNext").disabled = true;
		//document.getElementById("btnGenerateJobNo").disabled = true;
		//document.getElementById("btnModify").disabled = true;
		//document.getElementById("RadioEntitled4LoanN").checked =true;
		//disableLoanDetails();
		//alert("state");
		//document.getElementById("RadioEntitled4LoanY").disabled =true;//later
		//document.getElementById("RadioEntitled4LoanN").checked =true;//later
		//disableLoanDetails();
		//if (document.getElementById("hiddenSecondState").value == "findIdNo"){
			//document.getElementById("btnNext").disabled = true;
		//	document.getElementById("btnSave").disabled = true;
		//	document.getElementById("btnModify").disabled = false;
			//if (document.getElementById("RadioEntitled4LoanN").checked ==true){
			//	//alert("N");
			//	//disableLoanDetails();
			//}else{
			//	//alert("Y");
			//}
			
		//}else {
			
		//}
	}  else if (document.getElementById("state").value == "SELECTED") {
		//document.getElementById("btnNext").disabled = true;
	//	document.getElementById("btnGenerateJobNo").disabled = false;
		//document.getElementById("btnModify").disabled = true;
		//document.getElementById("RadioEntitled4LoanN").checked =true;
		//disableLoanDetails();
		//alert("state");
		//document.getElementById("RadioEntitled4LoanY").disabled =true;//later
		//document.getElementById("RadioEntitled4LoanN").checked =true;//later
		//disableLoanDetails();
		//if (document.getElementById("hiddenSecondState").value == "findIdNo"){
			//document.getElementById("btnNext").disabled = true;
		//	document.getElementById("btnSave").disabled = true;
		//	document.getElementById("btnModify").disabled = false;
			//if (document.getElementById("RadioEntitled4LoanN").checked ==true){
			//	//alert("N");
			//	//disableLoanDetails();
			//}else{
			//	//alert("Y");
			//}
			
		//}else {
			
		//}
	} 
	
	
}