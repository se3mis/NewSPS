/**
 * struts-layout item swapper javascript functions
 */

/**
 * Remove empty options from a select object.
 */

 var defaultListSize;
 var selectedListSize;
 var optionArray=new Array();
 var selectedArray=new Array();


function on_load(){
	
	if(document.getElementById('stdLayoutSwapHS0'))
	{
		defaultListSize = document.getElementById('stdLayoutSwapHS0').length;	
		for(var i=0;i<defaultListSize;i++){			
			optionArray[i]=document.getElementById('stdLayoutSwapHS0').options[i].value ;		
		}
	}	
}

function BumpUp(box)  {
	for(var i=0; i<box.options.length; i++) {
		box.options[i].selected = true;
		if(box.options[i].value == "")  {
			for(var j=i; j<box.options.length-1; j++)  {
				box.options[j].value = box.options[j+1].value;
				box.options[j].text = box.options[j+1].text;				
				box.options[j].selected = true;				
			}
			box.options.length -= 1;
			i--;
		}
	}
}

/**
 * When swapping lines between two tables, select and unselect the given tr.
 */
function prepareSwap(line, aClass) {
	
	if (line.selectedForSwap && line.selectedForSwap==true) {
		line.selectedForSwap = false;
	} else {
		line.selectedForSwap = true;
	}

	var oTD = line.getElementsByTagName("TD");
	for (i=0;i<oTD.length;i++) {
		var className = oTD[i].className;
		var oldClassName = oTD[i].oldClassName ? oTD[i].oldClassName : aClass;		
		
		oTD[i].className = oldClassName;
		oTD[i].oldClassName = className;	
	}
}

/**
 * Move the selected rows from the sSelectFrom table to the sSelectTo table.
 */
function swap(sSelectFrom, from, sSelectTo, to) {
	var selectFrom = document.getElementById(sSelectFrom).getElementsByTagName("TBODY").item(0);
	var selectTo = document.getElementById(sSelectTo).getElementsByTagName("TBODY").item(0); 
	var oTRsFrom = selectFrom.getElementsByTagName("TR");
	var aSelectedTRs = new Array();
	var h=0;
	
	// look for selected lines.
	for (var y=0;y<oTRsFrom.length;y++) {
		thisTR = oTRsFrom.item(y);
		if (thisTR.selectedForSwap && thisTR.selectedForSwap==true) {
  			aSelectedTRs[h]=y;
			h++;
		}
	}
	
	// move selected lines.
	for (var y=aSelectedTRs.length-1;y>=0;y--) {
		thisTR = oTRsFrom.item(aSelectedTRs[y]);
		if (thisTR.selectedForSwap && thisTR.selectedForSwap==true) {
			// move the line.
			selectTo.insertBefore(thisTR,selectTo.getElementsByTagName("TR").item(1));			
				
			//selecting the item
			// move the option on the hidden selects.
			var nameAZ = aSelectedTRs[y]-1;
			var selectAZ = document.getElementById(from);			
			var selectToAZ = document.getElementById(to);
			
			if(from=='stdLayoutSwapHS0'){
							
							
				var tempArray=new Array();
				tempArray[0]=optionArray[nameAZ];
				optionArray.splice(nameAZ, 1);	
				selectedArray = tempArray.concat(selectedArray);
			
			}else{
						
				
				var tempArray=new Array();
				tempArray[0]=selectedArray[nameAZ];
				selectedArray.splice(nameAZ, 1);
				optionArray = tempArray.concat(optionArray);
				
			}
						
			//deselect the line.
			prepareSwap(thisTR);
		}
	}
		
	
	return false;
}

function jobClose()
{	
	var selNo='';
	var enteredDate;
	if(selectedArray.length<1 ){
		alert('Please select at least one job to close');
		return false;
	}else
	{		
		if(document.getElementById("datepicker") ){
			
			var today =new Date();
			enteredDate =  getDateString();
						
			if(enteredDate==undefined){
				alert('Please enter a correct appointment date which is ahead of todays date');
				return false;
			}	
			
			if (enteredDate>today && enteredDate!=today)
			 {
				alert('Please enter a correct appointment date which is today or before');
				return false;
			 }
		}	
	}
	
	
		var answer = confirm ("Are you sure you want to close selected jobs?");
		if (!answer)
			return false;	
		
		for(var i=0;i<selectedArray.length;i++)
		{
			if(i==0)
				selNo=selectedArray[i];
			else
				selNo=selNo+","+selectedArray[i];
				
		}
		if(document.getElementById('txtdselectedNo'))
			document.getElementById('txtdselectedNo').value=selNo;	
		
		if(document.getElementById('txtdclosingDate') && document.getElementById('datepicker'))
			document.getElementById('txtdclosingDate').value=enteredDate;	
				
			return true;
	
}


function getDateString() {
	
	  var picker = dojo.widget.byId("datepicker");
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

function setJobNos(){
	document.forms[0].submit();
}