<%@page import="javax.swing.text.html.parser.Parser"%>
<%@page import="org.reservation.module.model.VehicleListBeanModel"%>
<%@ page import="java.util.List,java.util.ArrayList,java.util.Iterator"%>
<%@ page import="org.reservation.module.model.VehicleBeanModel" %>  
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:useBean id="vehicles" class="org.reservation.module.model.VehicleListBeanModel" scope="request"></jsp:useBean>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<script src="registration-form-validation.js"></script>
<script type="text/javascript">
    function populate(slct1, slct2) {
        var s1 = document.getElementById(slct1);
        var s2 = document.getElementById(slct2);
        s2.innerHTML = "";
        if (s1.value == "Yes") {
            var optionArray = ["Child Seat", "Ski Rack", "ETCCC"];
        } else if (s1.value == "No") {
           // var optionArray = ["Subcat2", "Subcat2.1", "Subcat2.2"];
        	//} else if (s1.value == "Cat3") {
          	//  var optionArray = ["Subcat3", "Subcat3.1", "Subcat3.3"];
    	}

    for (var option in optionArray) {
        if (optionArray.hasOwnProperty(option)) {
            var pair = optionArray[option];
            var checkbox = document.createElement("input");
            checkbox.type = "checkbox";
            checkbox.name = pair;
            checkbox.value = pair;
            //s2.appendChild(document.createElement("br"));
            s2.appendChild(checkbox);
    
            var label = document.createElement("label");
            label.htmlFor = pair;
            label.appendChild(document.createTextNode(pair));

            s2.appendChild(label);
    //        s2.appendChild(document.createElement("br"));    
        }
    }
}
</script>

<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
var jquery_latest = $.noConflict(true);
</script>
        <script>
     //   jquery_latest(document).ready(function() {                        // When the HTML DOM is ready loading, then execute the following function...
     //   	jquery_latest('#getpoints').click(function() {               // Locate HTML DOM element with ID "somebutton" and assign the following function to its "click" event...
     //   		jquery_latest.get('SearchCar',jquery_latest("#memNo"), function(responseText) {         // Execute Ajax GET request on URL of "someservlet" and execute the following function with Ajax response text...
     //   			jquery_latest('#showpoints').text(responseText);         // Locate HTML DOM element with ID "somediv" and set its text content with the response text.
    //    			jquery_latest('#pointslbl').text("Points Available:");
   //                 });
  //              });
  //          });
        </script>
<script>
jquery_latest(document).ready(function() {                        // When the HTML DOM is ready loading, then execute the following function...

	   	jquery_latest('#getpoints').click(function() {               // Locate HTML DOM element with ID "somebutton" and assign the following function to its "click" event...
	   		//handling for illegal membership number
	   		var memNum = document.getElementById("memNo");
	   		if(formembership(memNum)==false){
	   			return false;
	   		}
    		jquery_latest.ajax({
  				type: 'GET',
  				url: 'SearchCar',
  				data: { txt1: jquery_latest("#memNo").val()},
  				error: function (request, status, error) {
  			        alert(request.responseText);
  			    },
  				success: function(responseText) {
	  				jquery_latest('#showpoints').text(responseText);         // Locate HTML DOM element with ID "somediv" and set its text content with the response text.
					jquery_latest('#pointslbl').text("Points Available:");
					jquery_latest('#getpoints').text("Retreiving...");
  				},
  				dataType: 'JSON'
			});
		});
});

jquery_latest(document).ready(function() {                        // When the HTML DOM is ready loading, then execute the following function...
	jquery_latest('#Search').click(function() {               // Locate HTML DOM element with ID "somebutton" and assign the following function to its "click" event...
		//validation for entries
		var opt = document.getElementById("category");
		var pick = memNum = document.getElementById("pickupdatetimepicker");
   		var drop = document.getElementById("dropdatetimepicker");
		if(forsearch(opt, pick, drop)==false){
			return false;
		}
		
		jquery_latest.ajax({
  				type: 'POST',
  				url: 'SearchCar',
  				data: { id:'1',
  						category: jquery_latest("#category").val(), 
  						type: jquery_latest("#type").val(),
  						ptime: jquery_latest("#pickupdatetimepicker").val(),
  						dtime: jquery_latest("#dropdatetimepicker").val(),
  						loc: jquery_latest("#branchlocation").val()
  						},
  				success: function(data) {  					
  					//jquery_latest('#SearchResult').text(data) ;
  					//var result = jquery_latest('<div />').append(responseText).find('#result').html();
  					jquery_latest('#SearchResult').html(data);
  					
  				},
  				dataType: 'HTML'
			});
		});
});

jquery_latest(document).ready(function() {                        // When the HTML DOM is ready loading, then execute the following function...
	jquery_latest('#CancelReservationbtn').click(function() {               // Locate HTML DOM element with ID "somebutton" and assign the following function to its "click" event...
		//handling for invalid entry
		conf = document.getElementById("conf");
		dt = document.getElementById("pickupdatetime");
		ph = document.getElementById("ph");
		if((forcancel(conf, ph, dt)==false)){
				return false;
			}		
		
		jquery_latest.ajax({
  				type: 'POST',
  				url: 'SearchCar', 
  				data: { id:'2', 
  						ph:jquery_latest("#ph").val(),
  						ptime:jquery_latest("#pickupdatetime").val(),
  						conf:jquery_latest("#conf").val()
  						},
  				success: function(responseText) {  					
  					jquery_latest("#cancelResult").text(responseText);
  					//var result = jquery_latest('<div />').append(responseText).find('#result').html();
  					//jquery_latest('#SearchResult').html(data);
  					jquery_latest('#Status').text("Status:");
  					
  				},
  				dataType: 'text'
			});
		});
});


function reserve(regNum)
{
	var hdnptime= document.getElementById("hptime");
	var hdndtime= document.getElementById("hdtime");
		jquery_latest.ajax({
  				type: 'POST',
  				url: 'SearchCar', 
  				data: { id:'3', 
  						regNo:regNum,
  						ptime: jquery_latest("#pickupdatetimepicker").val(),
  					//	ptime:hdnptime.value,
  						dtime: jquery_latest("#dropdatetimepicker").val(),
  					//	dtime:hdndtime.value
  						},
  				success: function(data) {  					
  					jquery_latest('#SearchResult').html(data);
  					//var result = jquery_latest('<div />').append(responseText).find('#result').html();
  					//jquery_latest('#SearchResult').html(data);
  					//jquery_latest('#Status').text("Status:");
  					
  				},
  				dataType: 'HTML'
		});		
}




/*
//reserve it


function reserveit(){               // Locate HTML DOM element with ID "somebutton" and assign the following function to its "click" event...
		//validation for entries

//		var opt = document.getElementById("hdcategory");
//		var pick = memNum = document.getElementById("hdptime");
//		var drop = document.getElementById("hddtime");
//		if(forsearch(opt, pick, drop)==false){
//			return false;
//		}
		
		var cat= jquery_latest("hdcategory").val();
		var car="car";
		if(cat.toLowerCase() == car.toLowerCase())
		{
			jquery_latest.ajax({
	  				type: 'POST',
	  				url: 'SearchCar',
	  				data: { id:'4',
	  						regNo: jquery_latest("#hdregnum").val(), 
	  						name: jquery_latest("#username").val(),
	  						email: jquery_latest("#email1").val(),
	  						add: jquery_latest("#desc").val(),
	  						ph: jquery_latest("#phone").val(),
	  						childseat: jquery_latest("#childseat").checked,
	  						skirack: jquery_latest("#skirack").checked,
	  						ptime: jquery_latest("#hdptime").val(),
	  						dtime: jquery_latest("#hddtime").val()
	  						},
	  				success: function(data) {  					
	  					//jquery_latest('#SearchResult').text(data) ;
	  					//var result = jquery_latest('<div />').append(responseText).find('#result').html();
	  					jquery_latest('#SearchResult').html(data);
	  					
	  				},
	  				dataType: 'HTML'
				});
		}
		else
		{
			jquery_latest.ajax({
				type: 'POST',
				url: 'SearchCar',
				data: { id:'5', //truck
						regNo: jquery_latest("#hdregnum").val(), 
						name: jquery_latest("#username").val(),
						email: jquery_latest("#email1").val(),
						add: jquery_latest("#desc").val(),
						ph: jquery_latest("#phone").val(),
						cartow: jquery_latest("#cartow").checked,
						liftgate: jquery_latest("#liftgate").checked,
						ptime: jquery_latest("#hdptime").val(),
						dtime: jquery_latest("#hddtime").val()
						},
				success: function(data) {  					
					//jquery_latest('#SearchResult').text(data) ;
					//var result = jquery_latest('<div />').append(responseText).find('#result').html();
					jquery_latest('#SearchResult').html(data);
					
				},
				dataType: 'HTML'
			});
		}
	
}


*/

//reservecar


function reservecar(){               // Locate HTML DOM element with ID "somebutton" and assign the following function to its "click" event...
		//validation for entries
		/*
		var opt = document.getElementById("hdcategory");
		var pick = memNum = document.getElementById("hdptime");
		var drop = document.getElementById("hddtime");
		if(forsearch(opt, pick, drop)==false){
			return false;
		}
		*/
			var x="";
			var y="";
			if(jquery_latest("#childseat").is(":checked"))
				{
					x= "childseat";
				
				}
			else
				{
					x= "nothing";
				}
			if(jquery_latest("#skirack").is(":checked"))
			{
				y= "skirack";
			
			}
			else
			{
				y= "nothing";
			}
			
			jquery_latest.ajax({
		  				type: 'POST',
		  				url: 'SearchCar',
		  				data: { id:'4',
	  							regNo: jquery_latest("#hdregnum").text(), 
	  							name: jquery_latest("#username").val(),
	  							email: jquery_latest("#email1").val(),
	  							add: jquery_latest("#desc").val(),
	  							ph: jquery_latest("#phone").val(),
	  							childseat:x  ,
	  							skirack: y,
	  							ptime: jquery_latest("#hdptime").text(),
	  							dtime: jquery_latest("#hddtime").text()
		  						},
		  				success: function(data) {  					
		  					//jquery_latest('#SearchResult').text(data) ;
		  					//var result = jquery_latest('<div />').append(responseText).find('#result').html();
		  					jquery_latest('#SearchResult').html(data);
		  					
		  				},
		  				dataType: 'HTML'
					});
		
}



//reservetruck


function reservetruck(){               // Locate HTML DOM element with ID "somebutton" and assign the following function to its "click" event...
		//validation for entries
		/*
		var opt = document.getElementById("hdcategory");
		var pick = memNum = document.getElementById("hdptime");
		var drop = document.getElementById("hddtime");
		if(forsearch(opt, pick, drop)==false){
			return false;
		}
		*/
			var x="";
			var y="";
			if(jquery_latest("#cartow").is(":checked"))
				{
					x= "cartow";
				
				}
			else
				{
					x= "nothing";
				}
			if(jquery_latest("#liftgate").is(":checked"))
			{
				y= "liftgate";
			
			}
			else
			{
				y= "nothing";
			}
			
			jquery_latest.ajax({
		  				type: 'POST',
		  				url: 'SearchCar',
		  				data: { id:'4',
	  							regNo: jquery_latest("#hdregnum").text(), 
	  							name: jquery_latest("#username").val(),
	  							email: jquery_latest("#email1").val(),
	  							add: jquery_latest("#desc").val(),
	  							ph: jquery_latest("#phone").val(),
	  							childseat:x  ,
	  							skirack: y,
	  							ptime: jquery_latest("#hdptime").text(),
	  							dtime: jquery_latest("#hddtime").text()
		  						},
		  				success: function(data) {  					
		  					//jquery_latest('#SearchResult').text(data) ;
		  					//var result = jquery_latest('<div />').append(responseText).find('#result').html();
		  					jquery_latest('#SearchResult').html(data);
		  					
		  				},
		  				dataType: 'HTML'
					});
	
}


</script>



<link rel="stylesheet" type="text/css" href="datetimepicker-master/jquery.datetimepicker.css">
<script src="datetimepicker-master/jquery.js"></script>
<script src="datetimepicker-master/jquery.datetimepicker.js"></script>

<title>SupperRent</title>
<style TYPE="text/css"><!--
    A:link {text-decoration: none; color: #000000}
    a:visited {text-decoration: none; color: #000000}
    a:active {text-decoration: none; color: #000000}
    a:hover{color: #FF0000}
--></style>


<script>



var items = [
             {
                 name: 'Select One',
                 value:'',
                 subitems: []
             },
             {
                 name:'Car', 
                 value: 'car', 
                 subitems: [
                     {name: 'Economy', value: 'economy'},
					 {name: 'Compact', value: 'compact'},
					 {name:'Mid-size', value: 'midsize'},
					 {name:'Standard', value: 'standard'},
					 {name:'Full-size', value: 'fullsize'},
					 {name:'Premium', value: 'premium'},
					 {name:'Luxury', value: 'luxury'},
					 {name:'SUV', value: 'suv'},
					 {name:'Van', value: 'van'}
                 ]
             },
             {
                 name: 'Truck',
                 value: 'truck',
                 subitems: [
                     {name: '24-foot', value: '24foot'},
					 {name: '15-foot', value: '15foot'},
					 {name:'12-foot', value: '12foot'},
					 {name:'Box Trucks', value: 'boxtrucks'},
					 {name:'Cargo Vans', value: 'cargovans'}
                 ]
             }
         ];
$(function(){
    var temp = {};
    
    $.each(items, function(){
        $("<option  />")
        .attr("value", this.value)
        .html(this.name)
        .appendTo("#category");
        temp[this.value] = this.subitems;
    });
    
    $("#category").change(function(){
        var value = $(this).val();
        var menu = $("#type");
        
        menu.empty();
        $.each(temp[value], function(){
            $("<option />")
            .attr("value", this.value)
            .html(this.name)
            .appendTo(menu);
        });
    }).change();
});

</script>

<script type="text/javascript">




</script>

</head>

<body bgcolor="#FFFFFF" onload="">
<form action="SearchCar" method="post">
<div align="center">
  <center>
  <table border="0" width="90%" cellspacing="0" cellpadding="0" background="img/bkg2.gif">
  <tr>
  
  </tr>
    <tr>
      <td width="5%" valign="top" height="53"><font face="Arial Black" size="4">
      <img alt="SUPPERRENT" src="images/logoS.jpg" border="1"  style="border-width:2px;border-color:#7f8bb7;"></img>
      </font></td>
      </center>
      <td width="95%" valign="bottom">
        <table border="0" width="100%" cellspacing="0" cellpadding="0" height="25">
          <tr>
            <td width="100%" valign="top">
              <SCRIPT language=Javascript1.2>
<!--
var tags_before_clock = "<p align='right'><b><font face='verdana' size='1' color='#000000'>"
var tags_middle_clock = "at"
var tags_after_clock  = "</font></b></p>"

if(navigator.appName == "Netscape") {
document.write('<layer id="clock"></layer><br>');
}

if (navigator.appVersion.indexOf("MSIE") != -1){
document.write('<span id="clock"></span>');
}

DaysofWeek = new Array()
  DaysofWeek[0]="Sunday - "
  DaysofWeek[1]="Monday - "
  DaysofWeek[2]="Tuesday - "
  DaysofWeek[3]="Wednesday - "
  DaysofWeek[4]="Thursday - "
  DaysofWeek[5]="Friday - "
  DaysofWeek[6]="Saturday - "

Months = new Array()
  Months[0]="January"
  Months[1]="February"
  Months[2]="March"
  Months[3]="April"
  Months[4]="May"
  Months[5]="June"
  Months[6]="July"
  Months[7]="August"
  Months[8]="September"
  Months[9]="October"
  Months[10]="November"
  Months[11]="December"

function upclock(){
var dte = new Date();
var hrs = dte.getHours();
var min = dte.getMinutes();
var sec = dte.getSeconds();
var day = DaysofWeek[dte.getDay()]
var date = dte.getDate()
var month = Months[dte.getMonth()]
var year = dte.getFullYear()

var col = ":";
var spc = " ";
var com = ",";
var apm;

if (date == 1 || date == 21 || date == 31)
  {ender = "st"}
else
if (date == 2 || date == 22)
  {ender = "nd"}
else
if (date == 3 || date == 23)
  {ender = "rd"}

else
  {ender = "th"}

if (12 < hrs) {
apm="<font size='1'> pm</font>";
hrs-=12;
}

else {
apm="<font size='1'> am</font>";
}

if (hrs == 0) hrs=12;
if (hrs<=9) hrs="0"+hrs;
if (min<=9) min="0"+min;
if (sec<=9) sec="0"+sec;

if(navigator.appName == "Netscape") {
document.clock.document.write(tags_before_clock+day+month+hrs+col+min+col+sec+apm+spc+tags_middle_clock+spc+day+spc+month+spc+date+ender+spc+year+tags_after_clock);
document.clock.document.close();
}

if (navigator.appVersion.indexOf("MSIE") != -1){
clock.innerHTML = tags_before_clock+day+month+spc+date+ender+spc+year+spc+tags_middle_clock+spc+hrs+col+min+col+sec+apm+spc+tags_after_clock;
}
}

setInterval("upclock()",1000);
//-->
</SCRIPT>
</td>
          </tr>
        </table>
      <table border="0" width="100%" cellspacing="0" cellpadding="0">
        <tr>
          <td width="100%">
             
<p align="right"><b><font size="1" face="Verdana" color="">About Us&nbsp;&nbsp; <font color="#FF0000">|</font>&nbsp;&nbsp;
SupperRent Locations&nbsp;&nbsp; <font color="#FF0000">|</font>&nbsp;&nbsp; Contact Us&nbsp;&nbsp;
<font color="#FF0000">|</font>&nbsp;&nbsp; Your Link&nbsp;&nbsp; <font color="#FF0000">|</font>&nbsp;&nbsp;
Your Link</font></b>
             
</td>
        </tr>
      </table>
    </td>
  </tr>
  </table>
</div>

<p align="center"><font face="Arial" size="2"></font><!-- banner -->

</p>

<div align="center" >
  <center>
  <table border="0" width="90%" cellspacing="0" cellpadding="0" height="450px">
    <tr>
      <td width="25%" valign="top">
        <table border="0" width="100%" bgcolor="#7f8bb7" cellspacing="0" cellpadding="0" bordercolor="#000000">
          <tr>
            <td width="100%" height="25px"><b><font color="#FFFFFF" face="Arial" size="2">&nbsp;
              Search Vehicle
              </font></b></td>
          </tr>
        </table>
        <div style="border:1px solid #d3dae5;">
        <table >
		<tr>
			<td><font face="Arial" size="2">
			<label >Select One:</label>
			</font>
			</td>
			<td>
			<select id="category" name="category">
			</select>
			<br/>
		</tr>
		<tr>
			<td><font face="Arial" size="2">
				<label>Type:</label>
				</font>
				</td>
				<td> 
				
				<select id="type" name="type">
				</select>
				</td>	
			
		</tr>
		<tr>
			<td><font face="Arial" size="2">
				<label>Pickup Date-Time:</label>
				</font>
				</td>
				<td>
				<input id="pickupdatetimepicker" name="pickupdatetimepicker" type="text" size="20" onmouseover="javascript:$('#pickupdatetimepicker').datetimepicker();" >
								
			</td>
		</tr>
		<tr>
			<td><font face="Arial" size="2">
				<label>Drop Date-Time:</label>
				</font>
				</td>
				<td>
				<input id="dropdatetimepicker" name="dropdatetimepicker" type="text" size="20" onmouseover="javascript:$('#dropdatetimepicker').datetimepicker();" >
			</td>
		</tr>
		<tr>
			<td><font face="Arial" size="2">
				<label>Location:</label>
				</font>
				</td> 
				<td>
				<select name= "branchlocation" id="branchlocation">
					<option value="vancouver" >Vancouver</option>
				</select> 
			</td>
		</tr>
		<tr>
		<td></td>
			<td align="left">
				<input type="button" id="Search" name="Search" value="Submit"></input>
			</td>
		</tr>
	</table></div> &nbsp;

	<!--  
        <table border="0" width="100%" bgcolor="#7f8bb7" cellspacing="0" cellpadding="0" bordercolor="#000000">
          <tr>
            <td width="100%" height="25px"><b><font color="#FFFFFF" face="Arial" size="2" >&nbsp;
              Deals Of The Month
              </font></b></td>
          </tr>
        </table>
        
        	<p align="left"><font face="Arial" size="2"><u><img border="0" src="images/membership1.jpg" ></u>&nbsp;
            &nbsp;</font>
            -->
        <table border="0" width="100%" bordercolor="#000000" cellspacing="0" cellpadding="0" bgcolor="#7f8bb7">
          <tr height="25px">
            <td width="100%"><b><font color="#FFFFFF" face="Arial" size="2" >&nbsp;
              Cancel Reservation
              </font></b></td>
          </tr>
        </table>    
        <div style="border:1px solid #d3dae5;">
             <table>
            	<tr>
            		<td><font face="Arial" size="2">
            			<label>Conformation No.:</label>
            			</font>
            		</td>
            		<td>
            			<input type="text" name="conf" id="conf"/> 
            		</td>
            	</tr>
            	<tr>
            		<td>
            		</td>
            		<td><font face="Arial" size="3">
            		<label>Or</label>
            		</font>
            		</td>
            	</tr>
            	<tr>
            		<td><font face="Arial" size="2">
            			<label>Phone No.:</label>
            			</font>
            		</td>
            		<td>
            			<input type="text" name="ph" id="ph"/>
            		</td>
            	</tr>
            	<tr>
            		<td><font face="Arial" size="2">
            			<label>PickUp Date-Time:</label>
            			</font>
            		</td>
            		<td>
            			<input id="pickupdatetime" type="text" size="20" onmouseover="javascript:$('#pickupdatetime').datetimepicker();">
            		</td>
            	</tr>
            	<tr>
            		<td>
            		<font face="Arial" size="2">
            				<label id="Status"></label>
            			</font>
            		</td>
            		<td width=60%>
            			
            				<label id="cancelResult"></label>
            			
            		</td>
            	</tr>
            	<tr>
            		<td>
            		</td>
            		<td>
            			<input type="button" name="CancelReservationbtn" id="CancelReservationbtn" value="Cancel" ></input>
            		</td>
            	</tr>
            	
            </table>
            </div>
        </td>
  
      <td width="50%" valign="top">
        
        <div align="center">
  
          <table border="0" width="95%" bordercolor="#FF0000" cellspacing="0" cellpadding="0" bgcolor="#f3f5f8">
            <tr>
              <td >
  <!--  
  <table cellpadding="0" cellspacing="0" border="0" bordercolor="#000000"  width="90%">
    <tr>
      <td>
-->
            
 <div id="SearchResult" style="width:100%;float:right;overflow: scroll;height: 400px;" >
<!--  
<table  border="1" width="535" align="left" bordercolor="#dae0ea">
	<tr style="height:30px;width:50px;background-color:#d2d9e4 ">
		
		<td><font size="2" face="Verdana">Category</font></td>
		<td><font size="2" face="Verdana">Brand</font></td>
		<td><font size="2" face="Verdana">Type</font></td>
		<td><font size="2" face="Verdana">Reserve Now</font></td>
	</tr>
	<% /* VehicleListBeanModel vehicle = (VehicleListBeanModel) request.getAttribute("vehicles");
	ArrayList<VehicleBeanModel> vehlist = vehicle.getVehlist();
	VehicleBeanModel veh= new VehicleBeanModel();
	int len = vehlist.size();
	int index=0;
	while(len>index)
	{ 
		veh = vehlist.get(index); */
	%>

		<tr style="height:25px;width:50px;">
		
		<td><font size="2" face="Verdana" color="#62799e"><% //out.write(veh.getCategory()); %></font></td>
		<td><font size="2" face="Verdana" color="#62799e"><% //out.write(veh.getBrand()); %></font></td>
		<td><font size="2" face="Verdana" color="#62799e"><% //out.write(veh.getType()); %></font></td>
		<td><font size="2" face="Verdana" color="#62799e" >
		<a href=ReservationView.jsp?reg=<% //out.write(Integer.toString(veh.getRegNo())); %>>
		<input type="button" value="Reserve Now"></input>
		</a>
		</font></td>    	
	    </tr>
		
		<% /* index++;
	}
	vehicle.clearVehlist(); */
	%>
		
</table>


</div>
-->
<table>
<tr>
<td width=5%></td>
<td>
<h3><font color="#3879b5" >Economy</font></h3>
<p><font  face="Arial" size="2" >
Economy rentals are perfect for individuals or families who are looking for great deals on vehicles, regardless of their vacation destination. Economy cars offer enough room for the entire family yet come with the fuel efficiency of a compact vehicle. Whether you plan on taking the family to a relative's house or need to rent a car at your destination airport during a business trip, an economy car rental is the way to go.
</font>
</p>
<h3><font color="#3879b5" >Compact</font></h3>
<p ><font face="Arial" size="2" >
Saving money on your next vacation should be a top priority. Whether you plan on going for a short road trip with friends or on a week-long getaway with family, a compact car rental is your best bet.
A compact rental car offers more value than many other types of vehicles. Smaller engines and lighter weight combine for better fuel economy while still offering enough room to pack in your entire family. Most of the compact rental car types come with affordable prices that many other car types can't beat.
</font>
</p>
<h3><font color="#3879b5" >Mid-size</font></h3>
<p ><font face="Arial" size="2" >
Saving money on your next vacation should be a top priority. Whether you plan on going for a short road trip with friends or on a week-long getaway with family, a compact car rental is your best bet.
A compact rental car offers more value than many other types of vehicles. Smaller engines and lighter weight combine for better fuel economy while still offering enough room to pack in your entire family. Most of the compact rental car types come with affordable prices that many other car types can't beat.
</font>
</p>
<h3><font color="#3879b5" >Standard</font></h3>
<p ><font face="Arial" size="2" >
Saving money on your next vacation should be a top priority. Whether you plan on going for a short road trip with friends or on a week-long getaway with family, a compact car rental is your best bet.
A compact rental car offers more value than many other types of vehicles. Smaller engines and lighter weight combine for better fuel economy while still offering enough room to pack in your entire family. Most of the compact rental car types come with affordable prices that many other car types can't beat.
</font>
</p>
<h3><font color="#3879b5" >Full-size</font></h3>
<p ><font face="Arial" size="2" >
Saving money on your next vacation should be a top priority. Whether you plan on going for a short road trip with friends or on a week-long getaway with family, a compact car rental is your best bet.
A compact rental car offers more value than many other types of vehicles. Smaller engines and lighter weight combine for better fuel economy while still offering enough room to pack in your entire family. Most of the compact rental car types come with affordable prices that many other car types can't beat.
</font>
</p>
<h3><font color="#3879b5" >Premium</font></h3>
<p ><font face="Arial" size="2" >
Saving money on your next vacation should be a top priority. Whether you plan on going for a short road trip with friends or on a week-long getaway with family, a compact car rental is your best bet.
A compact rental car offers more value than many other types of vehicles. Smaller engines and lighter weight combine for better fuel economy while still offering enough room to pack in your entire family. Most of the compact rental car types come with affordable prices that many other car types can't beat.
</font>
</p>
<h3><font color="#3879b5" >Luxary</font></h3>
<p ><font face="Arial" size="2" >
Saving money on your next vacation should be a top priority. Whether you plan on going for a short road trip with friends or on a week-long getaway with family, a compact car rental is your best bet.
A compact rental car offers more value than many other types of vehicles. Smaller engines and lighter weight combine for better fuel economy while still offering enough room to pack in your entire family. Most of the compact rental car types come with affordable prices that many other car types can't beat.
</font>
</p>
<h3><font color="#3879b5" >SUV</font></h3>
<p ><font face="Arial" size="2" >
Saving money on your next vacation should be a top priority. Whether you plan on going for a short road trip with friends or on a week-long getaway with family, a compact car rental is your best bet.
A compact rental car offers more value than many other types of vehicles. Smaller engines and lighter weight combine for better fuel economy while still offering enough room to pack in your entire family. Most of the compact rental car types come with affordable prices that many other car types can't beat.
</font>
</p>
<h3><font color="#3879b5" >Van</font></h3>
<p ><font face="Arial" size="2" >
Saving money on your next vacation should be a top priority. Whether you plan on going for a short road trip with friends or on a week-long getaway with family, a compact car rental is your best bet.
A compact rental car offers more value than many other types of vehicles. Smaller engines and lighter weight combine for better fuel economy while still offering enough room to pack in your entire family. Most of the compact rental car types come with affordable prices that many other car types can't beat.
</font>
</p>






</td>
<td width=5%></td>
</tr>
</table>
</div>     
 </table>

      <div id="aftercontent" style="width:80%;height:2px;float:right;"></div>
      <!-- 
  </td>
            </tr>
          </table>
           -->
        </div>
 <div id="aftercontent" style="width:80%;height:50px;float:right;"></div> 
  
        <div align="center">
          <center>
          <table border="0" width="95%" bordercolor="#000000" cellspacing="0" cellpadding="0" bgcolor="#7f8bb7">
          </table>
          </center>
        </div>
   </td>
  <center>
      <td width="25%" valign="top">
        <table border="0" width="100%" bgcolor="#7f8bb7" cellspacing="0" cellpadding="0" bordercolor="#000000">
          <tr>
            <td width="100%" height="25px"><b><font color="#FFFFFF" face="Arial" size="2">&nbsp;
              </font><font color="#000000"><font face="Arial" size="2">
              </font></font><font face="Arial" color="#FFFFFF" size="2">View Your Redeemable Points</font></b></td>
          </tr>
        </table>
        <div style="border:1px solid #d3dae5;">
          	<table>
            	<tr>
            		<td><font face="Arial" size="2">
            			<label>Membership No.:</label>
            			</font>
            		</td>
            		<td>
            			<input type="text" name="txt1" id="memNo"/> 
            		</td>
            	</tr>
            	<tr>
            		<td><font face="Arial" size="2">
            			<label id="pointslbl"></label>
            			</font>
            		</td>
            		<td>
            		<div id="showpoints" style="font: Arial;font-size: 2"></div>
            		</td>
            	</tr>
            	<tr>
            		<td>
            		</td>
            		<td>
            			<input type="button" id="getpoints" name="getpoints" value="Submit" ></input>
            		</td>
            	</tr>
         
            </table>
        </div>
	<div style="height: 25"> &nbsp; </div>
        <table border="0" width="100%" bgcolor="#7f8bb7" cellspacing="0" cellpadding="0" bordercolor="#000000">
          <tr>
            <td width="100%" height="25px"><b><font color="#FFFFFF" face="Arial" size="2">&nbsp;
              Deal Of The Month
              </font></b></td>
          </tr>
        </table>
            <p align="center"><font face="Arial" size="2"><a href="https://www.facebook.com/supperrent" target="_blank"><img border="0" src="images/likeUs.png" ></a>&nbsp;
            </font>
            <!-- 
        <table border="0" width="100%" bordercolor="#000000" cellspacing="0" cellpadding="0" bgcolor="#7f8bb7">
          <tr>
            <td width="100%" height="25px"><b><font color="#FFFFFF" face="Arial" size="2">&nbsp;
              Upcoming Events
              </font></b></td>
          </tr>
        </table>
        <p><font face="Arial" size="2"><u><img border="0" src="img/bullet.gif" width="10" height="12"></u>&nbsp;
        <u>Place Link 1 Here<br>
        <img border="0" src="img/bullet.gif" width="10" height="12"></u>&nbsp;
        <u>Place Link 2 Here<br>
        <img border="0" src="img/bullet.gif" width="10" height="12"></u>&nbsp;
        <u>Place Link 3 Here<br>
        <img border="0" src="img/bullet.gif" width="10" height="12"></u>&nbsp;
        <u>Place Link 4 Here<br>
        <img border="0" src="img/bullet.gif" width="10" height="12"></u>&nbsp;
        <u>Place Link 5 Here<br>
        </u>&nbsp;</font></p>
        <p>&nbsp; -->
        </td>
    </tr>
  </table>
  </center>
</div>

<hr color="#FF0000" size="3" width="90%">

<p align="center"><font face="Arial" size="1" color="#000000"><strong><b>© Copyright 2000
All Rights Reserved SUPPERRENT.COM</b></strong></font></p>

</form>

</body>
</html>
