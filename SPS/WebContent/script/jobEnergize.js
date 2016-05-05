function validate_form(){
	
	 var jobNo = document.getElementById("listjobNo").value;
	 if(jobNo=='-1')
	 {
		 alert('Please select a job Number to energize');
		 return false;
	 }
	 
	 var selectedPhaseId = document.getElementById("listphase").selectedIndex;
	 if(selectedPhaseId=="0")
	 {
		 alert('Please select a phase');
		 return false;
	 }
		 
		 var answer = confirm ("Are you sure you want to energize the job?");
			if (!answer)
				return false;
	 	
}

function setSealNos(){
	
	var selectedPhaseId = document.getElementById("listphase").selectedIndex;
	
	if(selectedPhaseId=="0"){
		document.getElementById("trPhase1").style.visibility = "hidden";
		document.getElementById("trPhase2").style.visibility = "hidden";
		document.getElementById("trPhase3").style.visibility = "hidden";
	}else if(selectedPhaseId=="1"){
		document.getElementById("trPhase1").style.visibility = "visible";
		document.getElementById("trPhase2").style.visibility = "hidden";
		document.getElementById("trPhase3").style.visibility = "hidden";
	}else if(selectedPhaseId=="2"){
		document.getElementById("trPhase1").style.visibility = "visible";
		document.getElementById("trPhase2").style.visibility = "visible";
		document.getElementById("trPhase3").style.visibility = "hidden";
	}else if(selectedPhaseId=="3"){
		document.getElementById("trPhase1").style.visibility = "visible";
		document.getElementById("trPhase2").style.visibility = "visible";
		document.getElementById("trPhase3").style.visibility = "visible";
	}
}

function setContractorId(){
	if(document.getElementById("listcontractorId") && document.getElementById("listjobNo"))
		document.getElementById("listcontractorId").selectedIndex =document.getElementById("listjobNo").selectedIndex;
	
	if(document.getElementById("listjobNo")){
		 if(document.getElementById("listjobNo").selectedIndex==0){
			document.getElementById("listphase").selectedIndex = 0;
			setSealNos();
			document.getElementById("listphase").disabled=true;
			 
		 }				
		 else
			 document.getElementById("listphase").disabled=false;	 
	}

}

function clearForm(){
	
	if(document.getElementById("txtdSealNumber1"))
		document.getElementById("txtdSealNumber1").value="";
	if(document.getElementById("txtdSealNumber2"))
		document.getElementById("txtdSealNumber2").value="";
	if(document.getElementById("txtdSealNumber3"))
		document.getElementById("txtdSealNumber3").value="";
	if(document.getElementById("txtdSealNumber4"))
		document.getElementById("txtdSealNumber4").value="";
	if(document.getElementById("txtdSealNumber5"))
		document.getElementById("txtdSealNumber5").value="";
	if(document.getElementById("txtdSealNumber6"))
		document.getElementById("txtdSealNumber6").value="";
	if(document.getElementById("txtdSealNumber7"))
		document.getElementById("txtdSealNumber7").value="";
	if(document.getElementById("txtdSealNumber8"))
		document.getElementById("txtdSealNumber8").value="";
	if(document.getElementById("txtdSealNumber9"))
		document.getElementById("txtdSealNumber9").value="";
	
	document.getElementById("listjobNo").selectedIndex = 0;
	setContractorId();
	
}
