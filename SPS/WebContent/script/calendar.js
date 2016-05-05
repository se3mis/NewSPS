var isChecked=false;

function addAppointment(){
	
	return true;
}

function setSelectedList(ctrl)
{ 	 
	 if(ctrl.checked == true)
		 isChecked=true;
	 else
		 isChecked=false;
	
	 for (i=0; i<document.forms[0].length; i++)
	     document.forms[0].elements[i].checked = false; 
	
	 if(isChecked)
		 ctrl.checked = true;
	 else
		 ctrl.checked = false;
}

function unLoad(){
	 for (i=0; i<document.forms[0].length; i++)
	     document.forms[0].elements[i].checked = false; 
	 
	 if(document.getElementById('hid_RefNo'))
		 document.getElementById('hid_RefNo').value="";
	 	
}

function validate_Edit(){
	
	if(!isChecked)	
	{
		alert("Please select an appointment");
		return false;
	}else
		{
			for (i=0; i<document.forms[0].length; i++){
				if(document.forms[0].elements[i].checked == true)
					{
					var apptId = document.forms[0].elements[i].id;
					
					var rowNameID = 'rowName_'+apptId;
					var rowDesID = 'rowDes_'+apptId;
					var rowTimeID = 'rowTim_'+apptId;
					var rowApptType='rowAptType_'+apptId;
					var rowCC = 'rowCosCen_'+apptId;
					
					var usernm;
					var desc;
					var time;
					var apptType;
					var seldate;
					var costCen;
					
					var picker = dojo.widget.byId("appointmentDate");
					var stringValue = picker.getValue();
					
					
					if(document.getElementById(rowNameID))
						usernm = document.getElementById(rowNameID).innerHTML;			
					if(document.getElementById(rowDesID))
						desc = document.getElementById(rowDesID).innerHTML;		
					if(document.getElementById(rowTimeID))
						time = document.getElementById(rowTimeID).innerHTML;
					if(document.getElementById(rowApptType))
						apptType = document.getElementById(rowApptType).innerHTML;	
					if(document.getElementById("selectedDate"))
						seldate = document.getElementById("selectedDate").value;
					if(document.getElementById(rowCC))
						costCen = document.getElementById(rowCC).innerHTML;		
												
					var urlStr = "/SPS/calendarBS/web/editEntryBS?userNam="+usernm+"&descr="+desc+"&timeval="+time+"&apptId="+apptId+"&apptType="+apptType+"&seldate="+seldate+"&costCen="+costCen;
					var answer = window.showModalDialog(urlStr,"", "dialogWidth:500px; dialogHeight:400px;dialogleft:200px;dialogtop:100px");
										
					break;
				}		
			   
			document.forms[0].submit();
			
			}
	}
	return true;
}

function addNewAppointment(){
	
	if(document.getElementById("selectedDate")){
		var selDate = document.getElementById("selectedDate").value;
		document.forms[0].action="/SPS/calendarBS/web/diaryEntryBS?selDate="+selDate;
		document.forms[0].submit();
	}
}

function ChangeStatus_Edit(){
	//alert("hi");
	if(!isChecked)	
	{
		alert("Please select an appointment");
		return false;
	}else{
			for (i=0; i<document.forms[0].length; i++){
				if(document.forms[0].elements[i].checked == true)
					{
					var apptId = document.forms[0].elements[i].id;
					
					var rowStatusID = 'rowStatus_'+apptId;
					var rowDesID = 'rowDes_'+apptId;
					var rowCC = 'rowCosCen_'+apptId;
					var desc;
					var status;
					var costCen;
					
					
					var picker = dojo.widget.byId("appointmentDate");
					var stringValue = picker.getValue();
					
					if(document.getElementById(rowDesID))
						desc = document.getElementById(rowDesID).innerHTML;		
					
					if(document.getElementById(rowStatusID))
						status = document.getElementById(rowStatusID).innerHTML;		
					
					if(document.getElementById(rowCC))
						costCen = document.getElementById(rowCC).innerHTML;		
				
					
					var urlStr = "/SPS/calendarBS/web/changeStatusBS?desc="+desc+"&status="+status+"&apptId="+apptId+"&costCen="+costCen;
					var answer = window.showModalDialog(urlStr,"", "dialogWidth:500px; dialogHeight:350px;dialogleft:200px;dialogtop:100px");
					
					
					break;
				}		
			     
			document.forms[0].submit();
			
			}
	}
	return true;
}

function on_Load(){		
		
		if(document.getElementById("hid_FrmFind") &&  document.getElementById("hid_FrmFind").value!="" &&  document.getElementById("hid_FrmFind").value=="true"){
			if(document.getElementById("btnChangeStatus"))
				document.getElementById("btnChangeStatus").disabled=true;
			else
				document.getElementById("btnChangeStatus").disabled=false;	
		
		return;
	}
	
	if(document.getElementById("hid_FrmFind") &&  document.getElementById("hid_FrmFind").value!="" &&  document.getElementById("hid_FrmFind").value=="true"){
		if(document.getElementById("btnEdit"))
			document.getElementById("btnEdit").disabled=true;
		else
			document.getElementById("btnEdit").disabled=false;
		
		if(document.getElementById("btnChangeStatus"))
			document.getElementById("btnChangeStatus").disabled=true;
		else
			document.getElementById("btnChangeStatus").disabled=false;
	}
	
}

