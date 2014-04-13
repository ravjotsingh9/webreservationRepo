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

<link rel="stylesheet" type="text/css" href="datetimepicker-master/jquery.datetimepicker.css">
<script src="datetimepicker-master/jquery.js"></script>
<script src="datetimepicker-master/jquery.datetimepicker.js"></script>
<title>Web Templates by LINE9.com</title>
<style TYPE="text/css"><!--
    A:link {text-decoration: none; color: #000000}
    a:visited {text-decoration: none; color: #000000}
    a:active {text-decoration: none; color: #000000}
    a:hover{color: #FF0000}
--></style>
<script>
var items = [
             {
                 name: '---',
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
        $("<option />")
        .attr("value", this.value)
        .html(this.name)
        .appendTo("#firstmenu");
        temp[this.value] = this.subitems;
    });
    
    $("#firstmenu").change(function(){
        var value = $(this).val();
        var menu = $("#secondmenu");
        
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


</head>

<body bgcolor="#FFFFFF" onload="">
<form action="SearchCar" method="post">
<div align="center">
  <center>
  <table border="0" width="90%" cellspacing="0" cellpadding="0" background="img/bkg2.gif">
  <tr>
  
  </tr>
    <tr>
      <td width="5%" valign="top" height="53"><font face="Arial Black" size="4"><img alt="SUPPERRENT" src="images/logoS.jpg"></img</font></td>
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
        <table >
		<tr>
			<td><font face="Arial" size="2">
			<label>Select One:</label>
			</font>
			</td>
			<td>
			<select id="firstmenu" name="car">
			</select>
			<br/>
		</tr>
		<tr>
			<td><font face="Arial" size="2">
				<label>Type:</label>
				</font>
				</td>
				<td> 
				
				<select id="secondmenu" name="truck">
				</select>
				</td>	
			</td>
		</tr>
		<tr>
			<td><font face="Arial" size="2">
				<label>Pickup Date-Time:</label>
				</font>
				</td>
				<td>
				<input id="pickupdatetimepicker" type="text" size="20" onmouseover="javascript:$('#pickupdatetimepicker').datetimepicker();">
								
			</td>
		</tr>
		<tr>
			<td><font face="Arial" size="2">
				<label>Drop Date-Time:</label>
				</font>
				</td>
				<td>
				<input id="dropdatetimepicker" type="text" size="20" onmouseover="javascript:$('#dropdatetimepicker').datetimepicker();">
			</td>
		</tr>
		<tr>
			<td><font face="Arial" size="2">
				<label>Location:</label>
				</font>
				</td> 
				<td>
				<select name= "branchlocation">
					<option value="vancouver">Vancouver</option>
				</select> 
			</td>
		</tr>
		<tr>
		<td></td>
			<td align="left">
				<input type="submit" value="Submit" name="submit" >
			</td>
		</tr>
	</table>&nbsp;
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
             <table>
            	<tr>
            		<td><font face="Arial" size="2">
            			<label>Conformation No.:</label>
            			</font>
            		</td>
            		<td>
            			<input type="text" name="txtCon"/> 
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
            			<input type="text" name="txtPh"/>
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
            		</td>
            		<td>
            			<input type="submit" name="CancelReservationbtn" value="Submit"></input>
            		</td>
            	</tr>
            	<tr height="25px">
            	<td></td>
            	</tr>
            </table>
        </td>
  
      <td width="50%" valign="top">
        <div align="center">
          <table border="0" width="95%" bgcolor="#f2f4f7" cellspacing="0" cellpadding="0" >
            <tr>
              <td>
                <p align="left"><b><font face="Arial" color="#000000" size="2">&nbsp;
                Search Result</font></b></td>
            </tr>
          </table>
        </div>
  
        <div align="center">
  
          <table border="0" width="95%" bordercolor="#FF0000" cellspacing="0" cellpadding="0" bgcolor="#f3f5f8">
            <tr>
              <td width="100%">
  
  <table cellpadding="0" cellspacing="0" border="0" bordercolor="#000000"  width="75%">
    <tr>
      <td>&nbsp;
      
 <div id="content" style="width:100%;float:right;overflow: scroll;height: 400px;" >

<table  border="1" width="525" align="left" bordercolor="#dae0ea">
	<tr style="height:30px;width:50px;background-color:#d2d9e4 ">
		<td><font size="2" face="Verdana">Regitration No.</font></td>
		<td><font size="2" face="Verdana">Category</font></td>
		<td><font size="2" face="Verdana">Brand</font></td>
		<td><font size="2" face="Verdana">Type</font></td>
	</tr>
	<% VehicleListBeanModel vehicle = (VehicleListBeanModel) request.getAttribute("vehicles");
	ArrayList<VehicleBeanModel> vehlist = vehicle.getVehlist();
	VehicleBeanModel veh= new VehicleBeanModel();
	int len = vehlist.size();
	int index=0;
	while(len>index)
	{ 
		veh = vehlist.get(index);
	%>

		<tr style="height:25px;width:50px;">
		
		<td><font size="2" face="Verdana" color="#62799e"><% out.write(Integer.toString(veh.getRegNo())); %></font></td>
		<td><font size="2" face="Verdana" color="#62799e"><% out.write(veh.getCategory()); %></font></td>
		<td><font size="2" face="Verdana" color="#62799e"><% out.write(veh.getBrand()); %></font></td>
		<td><font size="2" face="Verdana" color="#62799e"><% out.write(veh.getType()); %></font></td>
		    	
	    </tr>
		
		<% index++;
	}
	vehicle.clearVehlist();
	%>
		
</table>
</div>
     
 </table>

      <div id="aftercontent" style="width:80%;height:2px;float:right;"></div>
  </td>
            </tr>
          </table>
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
          	<table>
            	<tr>
            		<td><font face="Arial" size="2">
            			<label>Membership No.:</label>
            			</font>
            		</td>
            		<td>
            			<input type="text" name="txt1"/> 
            		</td>
            	</tr>
            	<tr>
            		<td>
            		</td>
            		<td>
            		</td>
            	</tr>
            	<tr>
            		<td>
            		</td>
            		<td>
            			<input type="submit" name="firstForm" value="Submit"></input>
            		</td>
            	</tr>
            	<tr height="25px">
            	<td></td>
            	</tr>
            </table>
        
        <table border="0" width="100%" bgcolor="#7f8bb7" cellspacing="0" cellpadding="0" bordercolor="#000000">
          <tr>
            <td width="100%" height="25px"><b><font color="#FFFFFF" face="Arial" size="2">&nbsp;
              Deal Of The Month
              </font></b></td>
          </tr>
        </table>
            <p align="center"><font face="Arial" size="2"><u><img border="0" src="images/likeUs.png" ></u>&nbsp;
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
