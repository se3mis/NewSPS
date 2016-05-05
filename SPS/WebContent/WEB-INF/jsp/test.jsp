<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<html>

<head>
<title>button</title>
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
    <script type="text/javascript">
      dojo.require("dojo.event.*");
      dojo.require("dojo.widget.*");
      dojo.require("dojo.widget.Button");

      function helloPressed()
      {
      alert('Click on the Hello World Button');
      document.getElementById('txfdEstimatedQuantity').value="50";
      }

      function init()
      {
      var helloButton = dojo.widget.byId('helloButton');
      dojo.event.connect(helloButton, 'onClick', 'helloPressed')
      }

    dojo.addOnLoad(init);
    </script>
    <script type="text/javascript">
      dojo.require("dojo.parser");
      dojo.require("dijit.form.CheckBox");
    </script>
   

      <script type="text/javascript" src="dojo.xd.js" djConfig="parseOnLoad: true"></script>

      <!-- combo box -->
      <script type="text/javascript">
        dojo.require("dojo.parser");
        dojo.require("dijit.form.FilteringSelect");
      </script>


</head>

<body bgcolor="#FFFFCC">

<p align="center"><font size="6" color="#800000">Welcome to Roseindia Dojo Project</font></p>


<br>
<h2>Check box</h2>
    <input id="cb" dojotype="dijit.form.CheckBox" name="developer" 
                           checked="checked" value="on" type="checkbox" />
    <label for="cb"> Are you a Developer </label> 
<h2>Auto Completer Combo box</h2>
    <select dojoType="dijit.form.FilteringSelect" name="sname" 
    autocomplete="false" value="Vinod">
      <option value="Vinod">Vinod</option>
      <option value="Vikash" >Vikash</option>
      <option value="Deepak" >Deepak</option>
      <option value="DeepakSir" >Deepak Sir</option>
      <option value="Arun" >Arun</option>
      <option value="Amar" >Amar</option>
      <option value="Aman" >Aman</option>
    </select>

<div class="msg_list">
										<p class="msg_head">Header-1 </p>
										<div class="msg_body">
										<button dojoType="Button" widgetId="helloButton" onClick="helloPressed();">Hello World!</button>
<s:textfield name="estimatedQuantity" id="txfdEstimatedQuantity"  size="6" cssStyle="text-align: right;"/><br>
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
    
</body>

</html>