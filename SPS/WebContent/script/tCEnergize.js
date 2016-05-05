function on_Load(){	
	if(document.getElementById("listjobNo").selectedIndex==0)
		setCtrlsForJob(true);
	else
		setCtrlsForJob(false);
	
	if(document.getElementById("hid_isFind").value.trim()!="" && document.getElementById("hid_isFind").value.trim().length>0){
		document.getElementById("btnEnergize").value="Finish";		
		
	}else{		
		document.getElementById("btnEnergize").value="Energize";
	}
	
	if(document.getElementById("hid_isFind").value.trim()!="" && document.getElementById("hid_isFind").value.trim().length>0)
		disableFilled();
	else
		diasbleFinish();
	
}

function setDetails(){
	document.forms[0].submit();
}

function setCtrlsForJob(state){		
	
	document.getElementById("txtdservicegivenDate").childNodes[1].disabled = state;
	dojo.widget.byId("txtdservicegivenDate").disabled = state;

	document.getElementById("txtdgivenlinemanName").readOnly = state;
	document.getElementById("txtdmatNoticeNo").readOnly = state;

	document.getElementById("txtdmatNoticeDate").childNodes[1].disabled = state;
	dojo.widget.byId("txtdmatNoticeDate").disabled = state;
	
	document.getElementById("txtdmeterNumber1").readOnly = state;
	document.getElementById("txtdinitialReading1").readOnly = state;
	document.getElementById("txtdfinalmeterReading1").readOnly = state;
	
	document.getElementById("txtdservicediscntDate").childNodes[1].disabled = state;
	dojo.widget.byId("txtdservicediscntDate").disabled = state;
	
	document.getElementById("txtddiscntlinemanName").readOnly = state;
	
	document.getElementById("txtdapplicationsendtoREDate").childNodes[1].disabled = state;
	dojo.widget.byId("txtdapplicationsendtoREDate").disabled = state;
	
	document.getElementById("txtdchargedAmt").readOnly = state;		
	document.getElementById("txtdsignatureES").readOnly = state;	

	
	var phase = document.getElementById("txtdPhase").value.trim();
	if(state){
		document.getElementById("txtdmeterNumber2").style.visibility = "hidden";
		document.getElementById("txtdmeterNumber3").style.visibility = "hidden";		
		
		document.getElementById("txtdinitialReading2").style.visibility = "hidden";
		document.getElementById("txtdinitialReading3").style.visibility = "hidden";
		
		document.getElementById("txtdfinalmeterReading2").style.visibility = "hidden";
		document.getElementById("txtdfinalmeterReading3").style.visibility = "hidden";	
		
		document.getElementById("lblmeterNumber2").style.visibility = "hidden";
		document.getElementById("lblmeterNumber3").style.visibility = "hidden";
		
		document.getElementById("lblinitialReading2").style.visibility = "hidden";
		document.getElementById("lblinitialReading3").style.visibility = "hidden";
		
		document.getElementById("lblfinalReading2").style.visibility = "hidden";
		document.getElementById("lblfinalReading3").style.visibility = "hidden";
		
	}else if(phase=="Three Phase"){
		
		document.getElementById("txtdmeterNumber2").style.visibility = "visible";
		document.getElementById("txtdmeterNumber3").style.visibility = "visible";		
		
		document.getElementById("txtdinitialReading2").style.visibility = "visible";
		document.getElementById("txtdinitialReading3").style.visibility = "visible";
		
		document.getElementById("txtdfinalmeterReading2").style.visibility = "visible";
		document.getElementById("txtdfinalmeterReading3").style.visibility = "visible";	
		
		document.getElementById("lblmeterNumber2").style.visibility = "visible";
		document.getElementById("lblmeterNumber3").style.visibility = "visible";
		
		document.getElementById("lblinitialReading2").style.visibility = "visible";
		document.getElementById("lblinitialReading3").style.visibility = "visible";
		
		document.getElementById("lblfinalReading2").style.visibility = "visible";
		document.getElementById("lblfinalReading3").style.visibility = "visible";
	}	
	
	if(phase=="Three Phase"){
		
		document.getElementById("txtdmeterNumber2").readOnly = state;
		document.getElementById("txtdmeterNumber3").readOnly = state;	
		
		document.getElementById("txtdinitialReading2").readOnly = state;
		document.getElementById("txtdinitialReading3").readOnly = state;	
		
	}
}

function disableFilled(){
	var phase = document.getElementById("txtdPhase").value.trim();
	
	document.getElementById("txtdservicegivenDate").childNodes[1].disabled = true;
	dojo.widget.byId("txtdservicegivenDate").disabled = true;

	document.getElementById("txtdgivenlinemanName").readOnly = true;
	document.getElementById("txtdmatNoticeNo").readOnly = true;

	document.getElementById("txtdmatNoticeDate").childNodes[1].disabled = true;
	dojo.widget.byId("txtdmatNoticeDate").disabled = true;
	
	document.getElementById("txtdmeterNumber1").readOnly = true;
	document.getElementById("txtdinitialReading1").readOnly = true;		
	
	if(phase=="Three Phase"){
		
		document.getElementById("txtdmeterNumber2").readOnly = true;
		document.getElementById("txtdmeterNumber3").readOnly = true;	
		
		document.getElementById("txtdinitialReading2").readOnly = true;
		document.getElementById("txtdinitialReading3").readOnly = true;	
		
	}
}

function diasbleFinish(){
	var phase = document.getElementById("txtdPhase").value.trim();				
	document.getElementById("txtdfinalmeterReading1").readOnly = true;
	
	if(phase=="Three Phase"){
			
		document.getElementById("txtdfinalmeterReading2").readOnly = true;
		document.getElementById("txtdfinalmeterReading3").readOnly = true;				
	}	
	document.getElementById("txtdservicediscntDate").childNodes[1].disabled = true;
	dojo.widget.byId("txtdservicediscntDate").disabled = true;
	
	document.getElementById("txtddiscntlinemanName").readOnly = true;	
	document.getElementById("txtdchargedAmt").readOnly = true;
	
	document.getElementById("txtdsignatureESDate").childNodes[1].disabled = true;
	dojo.widget.byId("txtdsignatureESDate").disabled = true;
	
	document.getElementById("txtdapplicationsendtoREDate").childNodes[1].disabled = true;
	dojo.widget.byId("txtdapplicationsendtoREDate").disabled = true;

}

function validate_form(){
	
	var isInitial;
	var phase = document.getElementById("txtdPhase").value.trim();
	if(document.getElementById("hid_isFind").value.trim()!="" && document.getElementById("hid_isFind").value.trim().length>0)
		isInitial =false;
	else
		isInitial =true;
	
	if(checkDate("txtdservicegivenDate", "Service given")==false)
		return false;
	
	if(document.getElementById("txtdgivenlinemanName").value.trim()=="" || document.getElementById("txtdgivenlinemanName").value.trim().length==0){
		alert("Please enter service connected Line's man name");
		return false;
	}
	
	if(document.getElementById("txtdmatNoticeNo").value.trim()=="" || document.getElementById("txtdmatNoticeNo").value.trim().length==0){
		alert("Please enter Material notice number");
		return false;
	}
	
	if(checkDate("txtdmatNoticeDate", "Material notice")==false)
		return false;
	
	if(document.getElementById("txtdmeterNumber1").value.trim()=="" || document.getElementById("txtdmeterNumber1").value.trim().length==0){
		alert("Please enter meter number1");
		return false;
	}
	
	if(phase=="Three Phase"){
		
		if(document.getElementById("txtdmeterNumber2").value.trim()=="" || document.getElementById("txtdmeterNumber2").value.trim().length==0){
			alert("Please enter meter number2");
			return false;
		}
		
		if(document.getElementById("txtdmeterNumber3").value.trim()=="" || document.getElementById("txtdmeterNumber3").value.trim().length==0){
			alert("Please enter meter number3");
			return false;
		}
	}
	
	if(document.getElementById("txtdinitialReading1").value.trim()=="" || document.getElementById("txtdinitialReading1").value.trim().length==0){
		alert("Please enter initial meter reading1");
		return false;
	}
	
	if(phase=="Three Phase"){
		if(document.getElementById("txtdinitialReading2").value.trim()=="" || document.getElementById("txtdinitialReading2").value.trim().length==0){
			alert("Please enter initial meter reading2");
			return false;
		}
		
		if(document.getElementById("txtdinitialReading3").value.trim()=="" || document.getElementById("txtdinitialReading3").value.trim().length==0){
			alert("Please enter initial meter reading3");
			return false;
		}
	}
	
	if(!isInitial){
		if(document.getElementById("txtdfinalmeterReading1").value.trim()=="" || document.getElementById("txtdfinalmeterReading1").value.trim().length==0){
			alert("Please enter final meter reading1");
			return false;
		}
		
		if(phase=="Three Phase"){
			if(document.getElementById("txtdfinalmeterReading2").value.trim()=="" || document.getElementById("txtdfinalmeterReading2").value.trim().length==0){
				alert("Please enter final meter reading2");
				return false;
			}
			
			if(document.getElementById("txtdfinalmeterReading3").value.trim()=="" || document.getElementById("txtdfinalmeterReading3").value.trim().length==0){
				alert("Please enter final meter reading3");
				return false;
			}
		}
		
		if(checkDate("txtdservicediscntDate", "Service disconnected")==false)
			return false;
		
		if(document.getElementById("txtddiscntlinemanName").value.trim()=="" || document.getElementById("txtddiscntlinemanName").value.trim().length==0){
			alert("Please enter service disconnected Line's man name");
			return false;
		}
		
		if(document.getElementById("txtdunitsUsed").value.trim()=="" || document.getElementById("txtdunitsUsed").value.trim().length==0){
			alert("Please enter units used");
			return false;
		}
		
		if(document.getElementById("txtdchargedAmt").value.trim()=="" || document.getElementById("txtdchargedAmt").value.trim().length==0 ||document.getElementById("txtdchargedAmt").value.trim()=="0.00"){
			alert("Please enter correct charged amount");
			return false;
		}	
	}
	
//	if(checkDate("txtdapplicationsendtoREDate","Application send to R.E.O")==false)
//		return false;
	
	var answer = confirm ("Are you sure you want to save the data?");
	if (!answer)
		return false;	
	
	
	return true;
}

function checkDate(dtCtrl, alertTxt){
	if(document.getElementById(dtCtrl)){		
		var today =new Date();
		enteredDate =  getDateString(dtCtrl);	
		
		if(enteredDate==undefined){
			alert('Please enter a correct  date ');
			return false;
		}
	
		
		if (enteredDate>today && enteredDate!=today)
		 {	
			alert('Please enter a correct '+alertTxt+ ' date which is today or before');
			return false;
		 }
		return true;
	}
}

function getDateString(dtCtrl) {
	
	  var picker = dojo.widget.byId(dtCtrl);
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

function setUnits(ctrl){
	
	var ctrlid = ctrl.id;
	var bfrValue= document.getElementById("txtdunitsUsed").value.trim();
	var totUnitsUsed;
	if(ctrl.value.trim()!="" && ctrl.value.trim().length>0){			
		
		if(ctrlid=="txtdfinalmeterReading1"){
			var initialReading1=Number(document.getElementById("txtdinitialReading1").value.trim());
			var finalReading1=Number(ctrl.value);
			if(initialReading1>finalReading1){
				alert("Final reading1 should be greater than initial reading1");
				ctrl.value=bfrValue;
				ctrl.focus();	
				
			}
			
		}else if(ctrlid=="txtdfinalmeterReading2"){
			var initialReading2=Number(document.getElementById("txtdinitialReading2").value.trim());
			var finalReading2=Number(ctrl.value);
			if(initialReading2>finalReading2){
				alert("Final reading2 should be greater than initial reading2");
				ctrl.value=bfrValue;
				ctrl.focus();	
				
			}
		}else if(ctrlid=="txtdfinalmeterReading3"){
			var initialReading3=Number(document.getElementById("txtdinitialReading3").value.trim());
			var finalReading3=Number(ctrl.value);
			if(initialReading3>finalReading3){
				alert("Final reading3 should be greater than initial reading3");
				ctrl.value=bfrValue;
				ctrl.focus();	
				
			}			
		}
		
		var phase = document.getElementById("txtdPhase").value.trim();
		if(phase=="Single Phase"){
			
			var initialReading1=Number(document.getElementById("txtdinitialReading1").value.trim());
			var finalReading1=Number(document.getElementById("txtdfinalmeterReading1").value.trim());

			totUnitsUsed = finalReading1-initialReading1;
		}else{
			var initialReading1=Number(document.getElementById("txtdinitialReading1").value.trim());
			var finalReading1=Number(document.getElementById("txtdfinalmeterReading1").value.trim());

			var initialReading2=Number(document.getElementById("txtdinitialReading2").value.trim());
			var finalReading2=Number(document.getElementById("txtdfinalmeterReading2").value.trim());

			var initialReading3=Number(document.getElementById("txtdinitialReading3").value.trim());
			var finalReading3=Number(document.getElementById("txtdfinalmeterReading3").value.trim());

			if(finalReading1==0)
				initialReading1 =0;
			if(finalReading2==0)
				initialReading2 =0;
			if(finalReading3==0)
				initialReading3=0;	
			
			totUnitsUsed = finalReading1-initialReading1+finalReading2-initialReading2+finalReading3-initialReading3;			
		}		
		document.getElementById("txtdunitsUsed").value = totUnitsUsed;
	}
}
      
function setBalanceAmt(ctrl){
	var estimateAmount;
	var damageAmt;
	var chargedAmt;
	if(document.getElementById("txtdestimateAmt") && document.getElementById("txtdestimateAmt").value.trim()!="")
		estimateAmount = Number(document.getElementById("txtdestimateAmt").value.trim());
	if(document.getElementById("txtddamageAmt") && document.getElementById("txtddamageAmt").value.trim()!="")
		damageAmt = Number(document.getElementById("txtddamageAmt").value.trim());

	if(document.getElementById("txtdchargedAmt") && document.getElementById("txtdchargedAmt").value.trim()!="" && document.getElementById("txtdchargedAmt").value.trim()!="0.00"){
		var formatChargedAmt = document.getElementById("txtdchargedAmt").value.trim().replace(",", "");
		chargedAmt = Number(formatChargedAmt);
		
		document.getElementById("txtdbalanceAmt").value = chargedAmt+damageAmt-estimateAmount;
		
	}
}