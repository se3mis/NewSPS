<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Estimate</title>
<sx:head parseContent="true"/>
<link rel="stylesheet" type="text/css" href="../../style/style.css" />
<link rel="stylesheet" type="text/css" href="../../style/dojo.css" />
<link rel="stylesheet" type="text/css" href="../../style/tundra.css" />

<script type="text/javascript" src="../../script/estimate.js"> </script>
<script type="text/javascript" src="../../script/javascript.js"> </script>
<script type="text/javascript" src="../../script/dojo.js"></script>
<script type="text/javascript" src="../../script/jquery.js"></script>
<script type="text/javascript">
$(document).ready(function()
{
  //hide the all of the element with class msg_body
  $(".msg_body").hide();
  //toggle the componenet with class msg_body
  $(".msg_head").click(function()
  {
    $(this).next(".msg_body").slideToggle(500);
    swapImage();
    
    
  });
});
</script>
<script language="javascript" type="text/javascript">
<!-- 
//Browser Support Code
/*function ajaxFunction(){
	var ajaxRequest;  // The variable that makes Ajax possible!
	alert("1");
	try{
		alert("12");
		// Opera 8.0+, Firefox, Safari
		ajaxRequest = new XMLHttpRequest(); 
		alert(ajaxRequest);
		alert("13");
	} catch (e){
		// Internet Explorer Browsers
		try{
			ajaxRequest = new ActiveXObject("Msxml2.XMLHTTP");
		} catch (e) {
			try{
				ajaxRequest = new ActiveXObject("Microsoft.XMLHTTP");
			} catch (e){
				// Something went wrong
				alert("Your browser broke!");
				return false;
			}
		}
		alert("2");
	}
	alert("33");
	// Create a function that will receive data sent from the server
	ajaxRequest.onreadystatechange = function(){
		alert("3");
		if(ajaxRequest.readyState == 4){
			var ajaxDisplay = document.getElementById('msg_list');
			ajaxDisplay.innerHTML = ajaxRequest.responseText;
		}
	};
	alert("44");
	//var age = document.getElementById('age').value;
	//var wpm = document.getElementById('wpm').value;
	//var sex = document.getElementById('sex').value;
	//var queryString = "?age=" + age + "&wpm=" + wpm + "&sex=" + sex;
	ajaxRequest.open("GET", "/estimateList.jsp" + queryString, true);
	alert("55");
	ajaxRequest.send(null);
	alert("66"); 
}*/

function ajax1()
{   
	alert("44");
    xmlHttp=GetXmlHttpObject();
    
    if (xmlHttp==null)
    {
        alert ("Your browser does not support AJAX!");
        return;
    } 
    //var url="http://localhost:8084/TestAjax/NewServletdc";
    url="http://localhost:9090/SMC/TestServlet";
    //alert(address);
    //var url="/TerminalListenerWeb/NewServletdc?q="+address;
    
    //url=url+"?q="+str;
    //url=url+"&sid="+Math.random();
    xmlHttp.onreadystatechange=stateChanged;
    xmlHttp.open("POST",url,true);
    xmlHttp.send(null);
}

function stateChanged() 
alert("55");
{ 
    if (xmlHttp.readyState==4)
    { 
        document.getElementById("msg_list").innerHTML=xmlHttp.responseText;
        //document.myForm.txtHint.value=xmlHttp.responseText;
    }
}

function GetXmlHttpObject()
{
    var xmlHttp=null;
    try
    {
        // Firefox, Opera 8.0+, Safari
        xmlHttp=new XMLHttpRequest();
    }
    catch (e)
    {
        // Internet Explorer
        try
        {
            xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");
        }
        catch (e)
        {
            xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
        }
    }
    return xmlHttp;
} 

//-->
</script>
<script type="text/javascript">
		  dojo.require("dojo.event.*");
		  dojo.require("dojo.widget.*");
		  dojo.require("dojo.widget.Button");

		  function helloPressed()
		  {
			alert('Click on the Hello World Button');
		  }

		  function init()
		  {
			var helloButton = dojo.widget.byId('helloButton');
			dojo.event.connect(helloButton, 'onClick', 'helloPressed')
		  }

		dojo.addOnLoad(init);
    </script>

    <script type="text/javascript" 
  src="dojo.xd.js" djConfig="parseOnLoad: true"></script>

  <script type="text/javascript">
       dojo.require("dojo.parser");
           dojo.require("dijit.layout.ContentPane");
           dojo.require("dijit.layout.TabContainer");
           dojo.require("dijit.form.Button");
     </script>

<s:head />
</head>
<body onload="buttonLoader()">
<table id="tblOuter" class="tblOuter">
	<tbody>
		<tr>
			<td>
			<table id="tblInner" class="tblInner">
				<tbody>
					<tr>
						<td id="tdHeader">
						<table id="tblHeader" class="tblHeader" cellpadding="0"
							cellspacing="0">
							<tbody>
								<tr>
									<td width="736px" height="70px"><img src="../../image/Heading_Left.PNG" width="736" height="70" alt="Heading_Left" /></td>
									<td width="*" bgcolor="#226B9C"></td>
									<td width="198px" height="70px"><img src="../../image/Heading_Right.PNG" width="198" height="70" alt="Heading_Right" /></td>
								</tr>
								<tr>
									<td height="15px" bgcolor="#000033"></td>
									<td width="*" bgcolor="#000033"></td>
									<td width="*" bgcolor="#000033"></td>

								</tr>
							</tbody>
						</table>
						</td>
					</tr>
					<!--tr>
						<td></td>
					</tr-->
					<tr>
						<td id="tdWelcome">
						<table class="tblWelcome" border="0" cellpadding="0" cellspacing="0">
							<thead>
								<tr>
									<th width="70%"></th>
									<th width="10%"></th>
									<th width="10%"></th>
									<th width="*"></th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td class="tdPath"><s:property value="path"></s:property></td>
									<td class="tdWelcomeLabel">Welcome</td>
									<td class="tdWelcomeUser"><s:property value="loggedInUserId"></s:property></td>
									<td><a href="<s:url action="logout"/>">Logout</a></td>
								</tr>
							</tbody>
						</table>
						</td>
					</tr>
					<tr>
						<td>
						</td>
					</tr>
					<tr>
						<td id="tdContent">
						<table class="tblContent" border="1" cellpadding="0"
							cellspacing="0">
							<thead>
								<tr>
									<th></th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td><s:form action="estimateList" method="GET"   validate="true" theme="simple">
										<table width="100%" class="tldFromContent" border="0">
											<thead>
												<tr>
													<th></th>

												</tr>
											</thead>
											<tbody>
												<tr>
													<td>
													<table width="100%" border="0"  >
														<thead>
															<tr>
																<th width="40%"></th>
																<th width="10%"></th>
																<th width="*"></th>
																
															</tr>
														</thead>
														<tbody>
															<tr>
																<td class="tdHeadings">NEW STANDARD ESTIMATE LIST_CEYLON ELECTRICITY BOARD</td>
																<td></td>
																<td class="tdMsgStyle"><s:property  value="errorMessage" ></s:property></td>
															</tr>
														</tbody>

													</table>
												</td>
												</tr>
												<tr>
													<td></td>
												</tr>
												<tr>
													<td>
													</td>
												</tr>
												<tr>
													<td>
													</td>
												</tr>
												
												
												<tr>
													<td>
													    <table class="tblList"  border="1" cellpadding="0" cellspacing="0" >
				                                            <thead>
				                                                <tr>
				                                                    <th></th>
				                                                </tr>
				                                            </thead>
				                                            <tbody>
				                                                <tr>
				                                                    <td>
				                                                        <div class="listDivStyleBig" >
				                                                            <table id="tdList" width="100%" border="0" align="center" bgcolor="#FBFFFF" cellpadding="0" cellspacing="0" style="border-collapse:collapse">
				                                                                <tbody>
				                                                                    <tr>
				                                                                        <td align="center"class="listHeader">Estimate Details
				                                                                        <s:hidden name="list" id="list"></s:hidden>
				                                                                            <s:if test="list.size > 0">
				                                                                                <table id="list_table" width="95%" align="center" border="1" style="border-color:#000033;border-collapse:collapse"   >
				                                                                                    <tr class="listColumnHeadingStyle">
				                                                                                        <td align="center">id<s:hidden name="msgString" id="msgString"></s:hidden><s:hidden name="deleteString" id="deleteString"></s:hidden></td>
				                                                                                        <td align="center">Res. Code</td>
				                                                                                        <td align="center">Res. Type</td>
				                                                                                        <td align="center">Res. Category</td>
				                                                                                        <td align="center">Tolerance</td>
				                                                                                        <td align="center">uom</td>
				                                                                                        <td align="center">Est. Quantity</td>
				                                                                                        <td align="center">Unit Price</td>
				                                                                                        <td align="center">Estimated Cost</td>
				                                                                                        <td align="center">..<s:textfield name="focus" id="txfdfocus"  size="1" cssStyle="text-align: right;"> </s:textfield></td>
				                                                                                        
				                                                                                        
				
				                                                                                    </tr>
				                                                                                    <%int i=0; %>
				                                                                                    <s:iterator value="list"  id="listColumnStyle">
				                                                                                        <tr id="row_<s:property value="resourceCode"/>" class="listRowStyle">
				                                                                                            <td align="center">
				                                                                                                <s:checkbox name="checkbox" id="cb" onclick="setDeleteList()" ></s:checkbox>
				                                                                                            </td>
				                                                                                            <td align="center">
				                                                                                                <s:property value="resourceCode" />
				                                                                                            </td>
				                                                                                            <td align="center">
				                                                                                               <s:property value="resourceType" />
				                                                                                            </td>
				                                                                                            <td align="center">
				                                                                                               <s:property value="resourceCategory" />
				                                                                                            </td >
				                                                                                            <td align="center">
				                                                                                               <s:property value="tolerance" />
				                                                                                            </td>
				                                                                                            <td align="right">
				                                                                                               <s:property value="uom" />
				                                                                                            </td >
				                                                                                            <td align="right">
				                                                                                                <!-- s:property value="estimatedQuantity" /-->
				                                                                                                <s:textfield name="estimatedQuantity" id="txfdEstimatedQuantity"  size="6" cssStyle="text-align: right;" onfocus="getCode(this);" onchange="getCode(this)" onkeypress="getCode(this);"/>
				                                                                                            </td>
				                                                                                            <td align="right">
				                                                                                               <s:property value="unitPrice"  />
				                                                                                               <!--s:textfield name="unitPrice" id="txfdUnitPrice"  size="10" cssStyle="text-align: right;" disabled="true"-->
				                                                                                            </td >
				                                                                                            <td align="right">
				                                                                                                <!-- s:property value="estimatedCost" /-->
				                                                                                                <s:textfield name="estimatedCost" id="txfdEstimatedCost"  size="10" cssStyle="text-align: right;" disabled="true"> </s:textfield>
				                                                                                            </td>
				                                                                                            <td>
				                                                                                                <s:submit name="update" value=".." onclick="function1(this);"></s:submit>
				                                                                                                <s:submit name="v" value="A"  onclick = "alert(getRow(this));"></s:submit>
				                                                                                                
				                                                                                            </td>
				
				                                                                                            <!--td--><!--/td-->
				                                                                                        </tr>
				                                                                                        <%i++; %>
				                                                                                    </s:iterator>
				                                                                                </table>
				                                                                            </s:if>
				                                                                        </td>
				                                                                    </tr>
				                                                                </tbody>
				                                                            </table>
				                                                        </div>
				                                                    </td>
				                                                </tr>
				                                                <tr>
				                                                    <td></td>
				                                                </tr>
				                                            </tbody>
				                                        </table>
													
													</td>
												</tr>
												<tr>
													<td id="tdBotton" colspan="3">
													<s:submit theme="simple" value="Save" method="save" id="btnSave" onclick="return validate_form()"></s:submit>
													<s:submit theme="simple" align="right" value="Add" method="add" id="btnAdd" onclick="return addWindow()"></s:submit>
													<s:submit theme="simple" align="right" value="Update" method="update" id="btnUpdate"></s:submit>
													<s:submit theme="simple" align="right" value="Delete" method="delete" id="btnDelete" onclick="return displayDeleteList()"></s:submit>
													<s:submit theme="simple" align="right" value="Next" method="next" id="btnNext" onclick="return validate_form()"></s:submit>
													<s:submit theme="simple" align="right" value="Allocate" method="allocate" id="btnAllocate" onclick="return checkAllocatedToNotNull()"></s:submit>
													<s:submit theme="simple" align="right" value="Close" method="close" id="btnClose"></s:submit>
													<s:submit theme="simple" align="right" value="List" method="list" id="btnList"></s:submit>
													<s:reset theme="simple" align="right" value="Clear" />
													<s:hidden id="state" name="state"></s:hidden>
													<s:hidden id="hiddenSecondState" name="hiddenSecondState"></s:hidden>
													<s:hidden id="path" name="path"></s:hidden>
													<s:hidden id="status" name="status"></s:hidden>
													<s:hidden id="errorMessage" name="errorMessage"></s:hidden>
													</td>
									
												</tr>
												
											</tbody>
										</table>

									</s:form></td>
								</tr>
								
								<tr>
									<td></td>
								</tr>
							</tbody>
						</table>

						</td>
					</tr>
					<tr>
						<td id="tdFooter">
						<table class="tblFooter" border="1" cellpadding="0"
							cellspacing="0">

							<tbody>
								<tr>
									<td><div class="msg_list">
										<p class="msg_head">Header-1 </p>
										<div class="msg_body">
										orem ipsum dolor sit amet, consectetuer adipiscing elit orem ipsum dolor sit amet, consectetuer adipiscing elit
										</div>
										<p class="msg_head">Header-2</p>
										<div class="msg_body">
										orem ipsum dolor sit amet, consectetuer adipiscing elit orem ipsum dolor sit amet, consectetuer adipiscing elit
										</div>
										<p class="msg_head">Header-3</p>
										<div class="msg_body">
										orem ipsum dolor sit amet, consectetuer adipiscing elit orem ipsum dolor sit amet, consectetuer adipiscing elit
										</div>
										</div>
									This is footer</td>
								</tr>
							</tbody>
						</table>
						</td>
					</tr>
				</tbody>
			</table>


			</td>
		</tr>
	</tbody>
</table>


</body>
</html>