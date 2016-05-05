function checkAppNoNotNull(){
	if((document.getElementById("txtdestimateNo").value==null)||(document.getElementById("txtdestimateNo").value=="") ){
		alert("Please specify an application number to find");
		document.getElementById("txtdestimateNo").select();
		return false;
	}
	else 
		return true;
}

function setIssueClearDates(ctrl){
	
	var ctrlId = ctrl.id;	
	
	if(ctrlId=="chkdeNotice"){
		if(ctrl.checked == true){
			toggleDenoticectrl("visible");
		}else{
			toggleDenoticectrl("hidden");
		}
	}else if(ctrlId=="chkdRDA"){
		if(ctrl.checked == true){
			toggleRDActrl("visible");
		}else{
			toggleRDActrl("hidden");
		}
	}else if(ctrlId=="chkPSD"){
		if(ctrl.checked == true){
			togglePSDctrl("visible");
		}else{
			togglePSDctrl("hidden");
		}
	}else if(ctrlId=="chkMuncipal"){
		if(ctrl.checked == true){
			toggleMuncipalctrl("visible");
		}else{
			toggleMuncipalctrl("hidden");
		}
	}else if(ctrlId=="chkPolice"){
		if(ctrl.checked == true){
			togglePolicectrl("visible");
			
		}else{
			togglePolicectrl("hidden");
		}
	}	
}

function setFormCtrls(){	
	
	if(document.getElementById('hid_isNew') && document.getElementById('hid_isNew').value!="" && document.getElementById('hid_isNew').value.length>0)
	{		
		document.getElementById('btnFindApplicationNo').disabled = true;
		document.getElementById('btnsave').value = "Save";
	}
	else{
		document.getElementById('btnFindApplicationNo').disabled = false;
		document.getElementById('btnsave').value = "Update";	
				
		if(document.getElementById('hid_isFind') && document.getElementById('hid_isFind').value!="" && document.getElementById('hid_isFind').value.length>0)
			document.getElementById('btnsave').disabled = false;
		else
			document.getElementById('btnsave').disabled = true;
		
	}	
	
	if(document.getElementById("hid_DnoticeChk").value!="" && document.getElementById("hid_DnoticeChk").value.length>0){
		document.getElementById("chkdeNotice").checked = true;
		toggleDenoticectrl("visible");
	}else{
		toggleDenoticectrl("hidden");
		document.getElementById("chkdeNotice").checked = false;
	}
		

	if(document.getElementById("hid_RDAChk").value!="" && document.getElementById("hid_RDAChk").value.length>0){
		document.getElementById("chkdRDA").checked = true;
		toggleRDActrl("visible");
	}else{
		toggleRDActrl("hidden");
		document.getElementById("chkdRDA").checked = false;		
	}
		
	
	if(document.getElementById("hid_PSDChk").value!="" && document.getElementById("hid_PSDChk").value.length>0){
		document.getElementById("chkPSD").checked = true;
		togglePSDctrl("visible");
	}else{
		togglePSDctrl("hidden");
		document.getElementById("chkPSD").checked = false;		
	}
		
	
	if(document.getElementById("hid_MuncipalChk").value!="" && document.getElementById("hid_MuncipalChk").value.length>0){
		document.getElementById("chkMuncipal").checked = true;
		toggleMuncipalctrl("visible");
	}else{
		toggleMuncipalctrl("hidden");
		document.getElementById("chkMuncipal").checked = false;		
	}
		
	
	if(document.getElementById("hid_PoliceChk").value!="" && document.getElementById("hid_PoliceChk").value.length>0){
		document.getElementById("chkPolice").checked = true;
		togglePolicectrl("visible");
	}else{
		togglePolicectrl("hidden");
		document.getElementById("chkPolice").checked = false;
	}
		
	
}

function reSetForm(){
	
	document.getElementById("hid_isFind").value="";
	document.getElementById("hid_DnoticeChk").value="";
	document.getElementById("hid_RDAChk").value="";
	document.getElementById("hid_PSDChk").value="";
	document.getElementById("hid_MuncipalChk").value="";
	document.getElementById("hid_PoliceChk").value="";
		
	document.getElementById("found_hid_DnoticeChk").value="";
	document.getElementById("found_hid_RDAChk").value="";
	document.getElementById("found_hid_PSDChk").value="";
	document.getElementById("found_hid_MuncipalChk").value="";
	document.getElementById("found_hid_PoliceChk").value="";
	
	document.getElementById("chkdeNotice").checked = false;
	document.getElementById("chkdRDA").checked= false;
	document.getElementById("chkPSD").checked= false;
	document.getElementById("chkMuncipal").checked= false;
	document.getElementById("chkPolice").checked= false;
	
	var picker1 = dojo.widget.byId("txtdDenoticeIssuedDate");
	picker1.setValue("");
	
	var picker2 = dojo.widget.byId("txtDenoticeClearedDate");
	picker2.setValue("");
	
	var picker3 = dojo.widget.byId("txtdIssuedDateRDA");
	picker3.setValue("");
	
	var picker4 = dojo.widget.byId("txtClearedDateRDA");
	picker4.setValue("");
	
	var picker5 = dojo.widget.byId("txtdIssuedDatePSD");
	picker5.setValue("");
	
	var picker6 = dojo.widget.byId("txtClearedDatePSD");
	picker6.setValue("");
	
	var picker7 = dojo.widget.byId("txtdIssuedDateMuncipal");
	picker7.setValue("");
	
	var picker8 = dojo.widget.byId("txtClearedDateMuncipal");
	picker8.setValue("");
	
	var picker9 = dojo.widget.byId("txtdIssuedDatePolice");
	picker9.setValue("");
	
	var picker10 = dojo.widget.byId("txtClearedDatePolice");
	picker10.setValue("");

	document.getElementById("btnFindApplicationNo").disabled=false;
	document.getElementById("txtdestimateNo").value = "";
	document.getElementById("txtdestimateNo").readOnly = false;	
	document.getElementById("btnsave").value="Save";
	
	document.getElementById("statusMsg").value="";
	document.getElementById("statusMsgErr").value="";
	
	toggleDenoticectrl("hidden");
	toggleRDActrl("hidden");	
	togglePSDctrl("hidden");
	toggleMuncipalctrl("hidden");
	togglePolicectrl("hidden");
	
	return false;
}

function validate_form(){
	
	if((document.getElementById("txtdestimateNo").value==null)||(document.getElementById("txtdestimateNo").value=="") ){
		alert("Please specify estimate number");
		document.getElementById("txtdestimateNo").select();
		return false;
	}
	var dnotperchkd = document.getElementById("chkdeNotice").checked || document.getElementById("chkdRDA").checked || document.getElementById("chkPSD").checked || document.getElementById("chkMuncipal").checked ||document.getElementById("chkPolice").checked;
	
	if(dnotperchkd == false){
		alert("Please specify at least one permission or D-Notice to save");
		return false;
	}
	
	var dnoticeChk = document.getElementById("chkdeNotice").checked;
	var RDAChk = document.getElementById("chkdRDA").checked;
	var PSDChk = document.getElementById("chkPSD").checked;
	var muncipalChk = document.getElementById("chkMuncipal").checked;
	var policeChk = document.getElementById("chkPolice").checked;
	
	if(dnoticeChk == true){
		if(checkDate("txtdDenoticeIssuedDate", true,"DeNotice")==false)
			return false;
		if(checkDate("txtDenoticeClearedDate", false,"DeNotice")==false)
			return false;		
	}
	if(RDAChk == true){
		if(checkDate("txtdIssuedDateRDA", true,"RDA")==false)
			return false;
		if(checkDate("txtClearedDateRDA", false,"RDA")==false)
			return false;		
	}
	if(PSDChk == true){
		if(checkDate("txtdIssuedDatePSD", true,"PSD")==false)
			return false;
		if(checkDate("txtClearedDatePSD", false,"PSD")==false)
			return false;		
	}
	if(muncipalChk == true){
		if(checkDate("txtdIssuedDateMuncipal", true,"Municipal")==false)
			return false;
		if(checkDate("txtClearedDateMuncipal", false,"Municipal")==false)
			return false;		
	}
	if(policeChk == true){
		if(checkDate("txtdIssuedDatePolice", true,"Police")==false)
			return false;
		if(checkDate("txtClearedDatePolice", false,"Police")==false)
			return false;		
	}
	
	var confirmMsg;
	if(document.getElementById("btnsave").value=="Save")
		confirmMsg = "Are you sure you want to save the data?";
	else
		confirmMsg = "Are you sure you want to update the data?";
	
	var answer = confirm (confirmMsg);
	if (!answer)
		return false;
	else		
		return true; 
}

function toggleDenoticectrl(state){
	document.getElementById("lbldDenoticeIssuedDate").style.visibility = state;
	document.getElementById("txtdDenoticeIssuedDate").style.visibility = state;
	document.getElementById("lbldDenoticeClearedDate").style.visibility = state;
	document.getElementById("txtDenoticeClearedDate").style.visibility = state;
}

function toggleRDActrl(state){
	document.getElementById("lbldRDAIssuedDate").style.visibility =state;
	document.getElementById("txtdIssuedDateRDA").style.visibility = state;
	document.getElementById("lbldRDAClearedDate").style.visibility = state;
	document.getElementById("txtClearedDateRDA").style.visibility = state;
}

function togglePSDctrl(state){
	document.getElementById("lbldPSDIssuedDate").style.visibility = state;
	document.getElementById("txtdIssuedDatePSD").style.visibility = state;
	document.getElementById("lbldPSDClearedDate").style.visibility = state;
	document.getElementById("txtClearedDatePSD").style.visibility = state;
}

function toggleMuncipalctrl(state){
	document.getElementById("lbldMuncipalIssuedDate").style.visibility = state;
	document.getElementById("txtdIssuedDateMuncipal").style.visibility = state;
	document.getElementById("lbldMuncipalClearedDate").style.visibility = state;
	document.getElementById("txtClearedDateMuncipal").style.visibility = state;
}

function togglePolicectrl(state){
	document.getElementById("lbldPoliceIssuedDate").style.visibility = state;
	document.getElementById("txtdIssuedDatePolice").style.visibility = state;
	document.getElementById("lbldPoliceClearedDate").style.visibility = state;
	document.getElementById("txtClearedDatePolice").style.visibility = state;

}

function checkDate(dtCtrl, isIssue,alertTxt){
	
	if(document.getElementById(dtCtrl) ){		
		var today =new Date();
		enteredDate =  getDateString(dtCtrl,isIssue);		
		if(enteredDate==undefined){
			alert('Please enter a correct  date ');
			return false;
		}
		
		if(enteredDate=="cleardateNull"){
			return true;
		}
		
		if (enteredDate>today && enteredDate!=today)
		 {
			if(isIssue==true)
				alert('Please enter a correct '+alertTxt+ ' issue date which is today or before');
			else
				alert('Please enter a correct '+alertTxt+ ' clear date which is today or before');
			return false;
		 }
		return true;
	}
}

function getDateString(dtCtrl,isIssue) {
	
	  var picker = dojo.widget.byId(dtCtrl);
	  var stringValue = picker.getValue();
	  
	  if(isIssue==false && (stringValue=="" || stringValue.length==0))
		  return "cleardateNull";
	  
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
