


//block baakspace
//Created by Dileepa Waduge
function blockbackspace(event){
    event = event || window.event;
    var key = event.keyCode || event.which;
    if (key == 8){
        return false;
    }else {
        return true;
    }
}
//
//
var backspaceok = false;
function setOk()
{
    backspaceok=true;
}

function cancelBackspace(){
    event = event || window.event;
    var key = event.keyCode || event.which;
    // substitute appropriate keycode ASCII numbers
    if (key == 46 && backspaceok==false){
        // cancel the backspace
        event.cancelBubble = true;
        return false;
    }else{
        return true;// i edited jlksacxlaLD
    }
    backspaceok = false;
}
// Allow only numbers 0 to 9 (No Decimal) and backspace
// Created by Dileepa Waduge
function onlyNumbers(event){
    // alert(event.keyCode);
    event = event || window.event;
    // alert(event.which);
    if (event.which!="undefined"){
        if (event.which!=0){
            var key = event.keyCode || event.which;
            // alert(key);
            if ((key < 48 || key > 57)&& event.which!=0 && (key!=8)) {
                alert("Only Numbers");
                return false;
            }
            return true;
        }// equal to zero
        return true;
    }// equal to undefind
    return true;
}
// Allow only Letters a-z and A-Z.
// Created by Dileepa Waduge
function onlyLetters(event)
{
    event = event || window.event;
    if (event.which!="undefined"){
        if (event.which!=0){
            var key = event.keyCode || event.which;
            // alert(key);
            if ((key <65 || key > 90) && (key < 97 || key >122))
            {
                alert("Only Letters");
                // document.getElementById(id)
                return false;
            }
            return true;
        }// equal to zero
        return true;
    }// equal to undefind
    return true;

}

//Allow only Letters a-z and A-Z.
//Created by Dileepa Waduge
function onlyLettersWithoutSpaceBar(event)
{
 event = event || window.event;
 if (event.which!="undefined"){
     if (event.which!=0){
         var key = event.keyCode || event.which;
         // alert(key);
         if ((key <65 || key > 90) && (key < 97 || key >122) && (key!=8))
         {
             alert("Only Letters. No Space bar ");
             // document.getElementById(id)
             return false;
         }
         return true;
     }// equal to zero
     return true;
 }// equal to undefind
 return true;

}

// //Allow only numbers 0 to 9 (No Decimal) and backspace
// Created by Dileepa Waduge
function onlyNumbersWithoutSpaceBar(event){
    // alert(event.keyCode);
    event = event || window.event;
    // alert(event.which);
    if (event.which!="undefined"){
        if (event.which!=0){
            var key = event.keyCode || event.which;
            // alert(key);
            if ((key < 48 || key > 57)&& event.which!=0 && (key!=8)) {
                alert("Only Numbers. No Space bar");
                return false;
            }
            return true;
        }// equal to zero
        return true;
    }// equal to undefind
    return true;
}


// Allows only Numbers 0-9 and letters a-z and A-Z and backspace and space bar.
function onlyNumbersAndLetters(event){
    event = event || window.event;
    if (event.which!="undefined"){
        if (event.which!=0){
            var key = event.keyCode || event.which;
            // alert(key);
            // with backspace and space bar
            if ((key < 48 || key > 57) && (key <65 || key > 90) && (key <97 || key >122)&& (key!=32)&& (key!=8)){
                alert("Only Numbers and Letters");
                return false;
            }
            return true;
        }// equal to zero
        return true;
    }// equal to undefind
    return true;
}


// Allows only Numbers 0-9 and letters a-z and A-Z and backspace and space bar.
function onlyNumbersAndLettersWithoutspaceBar(event){
    event = event || window.event;
    if (event.which!="undefined"){
        if (event.which!=0){
            var key = event.keyCode || event.which;
            alert(key);
            // with backspace and space bar
            if ((key < 48 || key > 57) && (key <65 || key > 90) && (key <97 || key >122)&& (key!=8)){
                alert("Only Numbers and Letters.No Space bar");
                return false;
            }
            return true;
        }// equal to zero
        return true;
    }// equal to undefind
    return true;
}

// Allow only Numbers (0-9) and retrict to two decimal position
function restrictToTwoDecimalPossition(obj,event){
    event = event || window.event;
    if (event.which!="undefined"){
        if (event.which!=0){
            var key = event.keyCode || event.which;
            if( (key != 46) && (key < 48 || key > 57)){
                return false;
            }
            if(obj.value.indexOf('.')!=(-1) && key==46){
                return false;
            }
            if(key!=46){
                if(obj.value.indexOf('.')!=(-1)){
                    if(obj.value.substr(obj.value.indexOf('.')).length>2)// number
																			// of
																			// decimal
																			// places
                    {
                        return false;
                    }
                }else {
                    if(obj.value.length>10){
                        return false;
                    }
                }
            }
            if(obj.value.indexOf('.')!=(-1)){
                if(obj.value.substr(obj.value.indexOf('.')).length>2){
                    return false;
                }
            }
            return true;
        }// equal to zero
        return true;
    }// equal to undefind
    return true;
}

function ToUpper(ctrl){  
	var t = ctrl.value;
	ctrl.value = t.toUpperCase();
}

function upperCase(event){  
	event = event || window.event;
    if (event.which!="undefined"){
        if (event.which!=0){
            var key = event.keyCode || event.which;
            // alert(key);
            if ((key <65 || key > 90) && (key < 97 || key >122))
            {
                return false;
            }
            return true;
        }// equal to zero
        return true;
    }// equal to undefind
    return true;
}

function noSpaceBar(event){
    // alert(event.keyCode);
    event = event || window.event;
    // alert(event.which);
    if (event.which!="undefined"){
        if (event.which!=0){
            var key = event.keyCode || event.which;
            // alert(key);
            if (event.which!=0 && (key!=8)) {
                alert("on Space bar allowed");
                return false;
            }
            return true;
        }// equal to zero
        return true;
    }// equal to undefind
    return true;
}

function desableButtons()
{// alert("script is running.....");
    // alert(document.getElementById("btnModify"));
    // alert(document.getElementById("state").value);
    if (document.getElementById("state").value=="first"){
        // alert("inside first state");
        document.getElementById("btnModify").disabled=true;
        document.getElementById("btnDelete").disabled=true;
    // document.getElementById("btnClear").disabled=true;
    }else {
        // alert("inside second state");
        // document.getElementById("btnFind").disabled=true;
        document.getElementById("btnFindByPointTypeId").disabled=true;
        document.getElementById("btnFindBySeqNo").disabled=true;
        document.getElementById("btnAddNew").disabled=true;
    }
}

function desableButtonsUser()
{// alert("script is running.....");
    // alert(document.getElementById("btnModify"));
    // alert(document.getElementById("state").value);
    if (document.getElementById("state").value=="first"){
        // alert("inside first state");
        document.getElementById("btnModify").disabled=true;
        document.getElementById("btnDelete").disabled=true;
    // document.getElementById("btnClear").disabled=true;
    }else {
        // alert("inside second state");
        // document.getElementById("btnFind").disabled=true;
        document.getElementById("btnFindByUserId").disabled=true;
        document.getElementById("btnFindBySeqNo").disabled=true;
        document.getElementById("btnAddNew").disabled=true;
    }
}
function ckCombineLineSection(){
    var x=document.getElementById("singleCombined").selectedIndex;
    alert(x);
    alert(document.getElementsByTagName("option")[x].value);
    if (document.getElementsByTagName("option")[x].value=="SINGLE"){
        alert("single");
        onloadLineSection();



    }else if (document.getElementsByTagName("option")[x].value=="COMBINED RUN"){
        alert("Conbined");
        enableCombinedSetting();
        defaultCombinedSetting();

    }   //

}
function disableSelectLineSection(){

    var x=document.getElementById("sectionType").selectedIndex;
    alert(x);
    if (x==0){// LV
        document.getElementById("valtage").disabled=true;
        document.getElementById("conductorType").options[0]=new Option("FLY", "FLY", true, false);
        document.getElementById("conductorType").options[1]=new Option("WASP", "WASP", true, false);
        document.getElementById("conductorType").options[2]=new Option("ABC 50 4 Wires", "ABC 50 4 Wires", true, false);
        document.getElementById("conductorType").options[3]=new Option("ABC 50 5 Wires", "ABC 50 5 Wires", true, false);
        document.getElementById("conductorType").options[4]=new Option("ABC 70 4 Wires", "ABC 70 4 Wires", true, false)
        document.getElementById("conductorType").options[5]=new Option("ABC 70 5 Wires", "ABC 70 5 Wires", true, false)
        document.getElementById("phaseType").disabled=false;
    }else if (x==1){// HV
        document.getElementById("valtage").disabled=false;
        document.getElementById("conductorType").options[0]=new Option("RECOON", "RECOON", true, false);
        document.getElementById("conductorType").options[1]=new Option("LYNX", "LYNX", true, false);
        document.getElementById("conductorType").options[2]=new Option("ELM", "ELM", true, false);
        document.getElementById("conductorType").options[3]=new Option("WEASES", "WEASES", true, false);
        document.getElementById("conductorType").options[4]=null;
        document.getElementById("conductorType").options[4]=null;// When you
																	// set 4 to
																	// null 5
																	// will come
																	// to 4.
        document.getElementById("phaseType").disabled=true;
    }else {
        document.getElementById("valtage").disabled=false;
    }


// if
// (document.getElementById("frmContent").getElementsByTagName(sectionType).value=="LV"){
// document.getElementById("valtage").disabled=true;
// }else{
// alert("inmside num");
// alert(event.keyCode);
// }
}
function alertValue()
{
    var x=document.getElementById("mySelect").selectedIndex;
    alert(document.getElementsByTagName("option")[x].value);
}

function  onloadLineSection(){
    alert("on load");
    desableCombinedSetting();
    defaultSingleSetting();

}
function desabalePhaseType()
{
    var x=document.getElementById("jobCategory").selectedIndex;
    // var y=document.getElementById("sectionType").selectedIndex;

    alert(x);
    alert("y"+y);
    // alert(document.getElementsByTagName("option")[x].value);
    if (x==0){
        alert("New");

        document.getElementById("jobType").disabled=true;
        if (y==0){
        document.getElementById("phaseType").disabled=false;
        }
   }else {
        document.getElementById("jobType").disabled=false;
        document.getElementById("phaseType").disabled=true;
    }


}
function desabaleCHVPhaseType()
{
    var x=document.getElementById("chvJobCategory").selectedIndex;
    var y=document.getElementById("sectionType").selectedIndex;
    alert(x);
    // alert(document.getElementsByTagName("option")[x].value);
    if (x==0){
        // alert("1 to 3 or 2 to 3");
        document.getElementById("chvJobType").disabled=true;



    }else {
        document.getElementById("chvPhaseType").disabled=false;
        document.getElementById("chvJobType").disabled=false;
    }


}
function desabaleConversionType(){
    var x=document.getElementById("chvJobCategory").selectedIndex;
    if (x==0){
        document.getElementById("chvJobType").disabled=true;
    }else{
       document.getElementById("chvJobType").disabled=false;
    }

}
function desableCombinedSetting(){
    document.getElementById("chvSectionType").disabled=true;
    document.getElementById("chvValtage").disabled=true;
    document.getElementById("chvCircuitType").disabled=true;
    document.getElementById("chvJobCategory").disabled=true;
    document.getElementById("chvJobType").disabled=true;
    document.getElementById("chvPhaseType").disabled=true;
    document.getElementById("chvConductorType").disabled=true;
    document.getElementById("chvMaterialCost").disabled=true;
    document.getElementById("chvLabourCost").disabled=true;
}

function enableCombinedSetting(){
    document.getElementById("chvSectionType").disabled=false;
    document.getElementById("chvValtage").disabled=false;
    document.getElementById("chvCircuitType").disabled=false;
    document.getElementById("chvJobCategory").disabled=false;
    document.getElementById("chvJobType").disabled=false;
    document.getElementById("chvPhaseType").disabled=false;
    document.getElementById("chvConductorType").disabled=false;
}

function defaultSingleSetting(){
    document.getElementById("valtage").disabled=true;
    //
    document.getElementById("sectionType").options[0]=new Option("LV", "LV", true, false);
    document.getElementById("sectionType").options[1]=new Option("HV", "HV", true, false);
    document.getElementById("sectionType").options[0].selected=true;
    //
    document.getElementById("phaseType").options[0]=new Option("3 Ph", "3 Ph", true, false);
    document.getElementById("phaseType").options[1]=new Option("2 Ph", "2 Ph", true, false);
    document.getElementById("phaseType").options[2]=new Option("1 Ph", "1 Ph", true, false);
    document.getElementById("phaseType").options[0].selected=true;
    // document.getElementById("phaseType").options[0]=new Option("LV", "LV",
	// true, false);
    // document.getElementById("phaseType").options[1]=new Option("HV", "HV",
	// true, false);
    // alert("fsjfj");
    document.getElementById("jobCategory").options[0].selected=true;
    //
    document.getElementById("jobType").disabled=true;
    document.getElementById("jobType").options[0].selected=true;
    // alert("222");
    document.getElementById("phaseType").disabled=false;
    //
    document.getElementById("conductorType").options[0]=new Option("FLY", "FLY", true, false);
    document.getElementById("conductorType").options[1]=new Option("WASP", "WASP", true, false);
    document.getElementById("conductorType").options[2]=new Option("ABC 50 4 Wires", "ABC 50 4 Wires", true, false);
    document.getElementById("conductorType").options[3]=new Option("ABC 50 5 Wires", "ABC 50 5 Wires", true, false);
    document.getElementById("conductorType").options[4]=new Option("ABC 70 4 Wires", "ABC 70 4 Wires", true, false)
    document.getElementById("conductorType").options[5]=new Option("ABC 70 5 Wires", "ABC 70 5 Wires", true, false);
    document.getElementById("conductorType").options[0].selected=true;
}

function defaultCombinedSetting(){
    document.getElementById("chvPhaseType").options[0]=new Option("3 Ph", "3 Ph", true, false);
    document.getElementById("chvPhaseType").options[1]=new Option("2 Ph", "2 Ph", true, false);
    document.getElementById("chvPhaseType").options[2]=new Option("1 Ph", "1 Ph", true, false);
    document.getElementById("chvConductorType").options[0]=new Option("RECOON", "RECOON", true, false);
    document.getElementById("chvConductorType").options[1]=new Option("LYNX", "LYNX", true, false);
    document.getElementById("chvConductorType").options[2]=new Option("ELM", "ELM", true, false);
    document.getElementById("chvConductorType").options[3]=new Option("WEASES", "WEASES", true, false);
    document.getElementById("chvConductorType").options[4]=null;
    document.getElementById("chvConductorType").options[4]=null;// When you set
																// 4 to null 5
																// will come to
																// 4.
    //
    document.getElementById("chvPhaseType").disabled=true;
    // document.getElementById("chvPhaseType").disabled=false;
    document.getElementById("chvJobCategory").options[0].selected=true;

    //
    document.getElementById("chvJobType").disabled=true;
    document.getElementById("chvJobType").options[0].selected=true;
    // document.getElementById("chvPhaseType").options[0].selected=true;
    document.getElementById("chvConductorType").options[0].selected=true;
    //
    document.getElementById("chvMaterialCost").disabled=false;
    document.getElementById("chvLabourCost").disabled=false;
    //
    document.getElementById("sectionType").options[0]=new Option("LV", "LV", true, false);
    document.getElementById("sectionType").options[1]=null;
    document.getElementById("sectionType").readonly=true;
    //
    document.getElementById("conductorType").options[0]=new Option("FLY", "FLY", true, false);
    document.getElementById("conductorType").options[1]=new Option("WASP", "WASP", true, false);
    document.getElementById("conductorType").options[2]=new Option("ABC 50 4 Wires", "ABC 50 4 Wires", true, false);
    document.getElementById("conductorType").options[3]=new Option("ABC 50 5 Wires", "ABC 50 5 Wires", true, false);
    document.getElementById("conductorType").options[4]=new Option("ABC 70 4 Wires", "ABC 70 4 Wires", true, false)
    document.getElementById("conductorType").options[5]=new Option("ABC 70 5 Wires", "ABC 70 5 Wires", true, false)
    //
    document.getElementById("valtage").disabled=true;
    //
    document.getElementById("phaseType").disabled=false;
    document.getElementById("jobCategory").options[0].selected=true;
    //
    document.getElementById("phaseType").options[0].selected=true;
    document.getElementById("conductorType").options[0].selected=true;
    document.getElementById("jobType").options[0].selected=true;
//


}
function  onloadLineSection123(){
    alert("on load");
    desableCombinedSetting();
    defaultSingleSetting();

}


function num(event){
    alert("inmside num");
    alert(event.keyCode);
}

function IsNumeric(strString)
//  check for valid numeric strings	
{
	
var strValidChars = "0123456789.-";
var strChar;
var blnResult = true;

if (strString.length == 0) return false;

//  test strString consists of valid characters listed above
for (i = 0; i < strString.length && blnResult == true; i++)
   {
   strChar = strString.charAt(i);
   if (strValidChars.indexOf(strChar) == -1)
      {
      blnResult = false;
      }
   }
return blnResult;
}

function formatCurrency(num) {
	num = num.toString().replace(/\$|\,/g,'');
	if(isNaN(num))
	num = "0";
	sign = (num == (num = Math.abs(num)));
	num = Math.floor(num*100+0.50000000001);
	cents = num%100;
	num = Math.floor(num/100).toString();
	if(cents<10)
	cents = "0" + cents;
	for (var i = 0; i < Math.floor((num.length-(1+i))/3); i++)
	num = num.substring(0,num.length-(4*i+3))+','+
	num.substring(num.length-(4*i+3));
	return (((sign)?'':'-') + num + '.' + cents);
}

function formatValue(obj)
{
	var objVal = obj.value;
	obj.value = formatCurrency(objVal);
}

function numericValidate(obj,event)
{
	var key = event.keyCode;
	
	if((key>=48 && key<=57)|| key==190)	{}
	else
	{
		var val = obj.value.toUpperCase();
		var keyVal = String.fromCharCode ( event.keyCode )
		var index = val.indexOf(keyVal);
		if(index!=-1)
		{
			obj.value = val.substring(0,index)+val.substring(index+1);
		}
	}

}

