<%@page import="javax.swing.text.html.parser.Parser"%>
<%@page import="org.reservation.module.model.VehicleListBeanModel"%>
<%@ page import="java.util.List,java.util.ArrayList,java.util.Iterator"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"  %>
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

</head>

<body bgcolor="#FFFFFF">
<form action="SearchCar" method="post">
<div align="center">
  <center>
  <table border="0" width="90%" cellspacing="0" cellpadding="0" background="img/bkg2.gif">
    <tr>
      <td width="5%" valign="top" height="53"><font face="Arial Black" size="4">SITELOGO</font></td>
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
             
<p align="right"><b><font size="1" face="Verdana">Your Link&nbsp;&nbsp; <font color="#FF0000">|</font>&nbsp;&nbsp;
Your Link&nbsp;&nbsp; <font color="#FF0000">|</font>&nbsp;&nbsp; Your Link&nbsp;&nbsp;
<font color="#FF0000">|</font>&nbsp;&nbsp; Your Link&nbsp;&nbsp; <font color="#FF0000">|</font>&nbsp;&nbsp;
Your Link</font></b>
             
</td>
        </tr>
      </table>
    </td>
  </tr>
  </table>
</div>
<p align="center"><font face="Arial" size="2">PLACE YOUR BANNER HERE</font>

</p>
<div align="center">
  <center>
  <table border="0" width="90%" cellspacing="0" cellpadding="0">
    <tr>
      <td width="25%" valign="top">
        <table border="1" width="100%" bgcolor="#FF0000" cellspacing="0" cellpadding="0" bordercolor="#000000">
          <tr>
            <td width="100%"><b><font color="#FFFFFF" face="Arial" size="2">&nbsp;
              Car Search
              </font></b></td>
          </tr>
        </table>
        <table >
		<tr>
			<td>Category: CAR</td>
		</tr>
		<tr>
			<td><font face="Arial" size="2">
				Type: <select name= "cartypes">
					<option value="economy">Economy</option>
					<option value="compact">Compact</option>
					<option value="midsize">Mid-size</option>
					<option value="standard">Standard</option>
					<option value="fullsize">Full-size</option>
					<option value="premium">Premium</option>
					<option value="luxury">Luxury</option>
					<option value="suv">SUV</option>
					<option value="van">Van</option>
				</select> 
				</font>
			</td>
		</tr>
		<tr>
			<td>
				<label>Pickup Date and Time</label><br>
				<input id="pickupdatetimepicker" type="text" size="20" onmouseover="javascript:$('#pickupdatetimepicker').datetimepicker();">				
			</td>
		</tr>
		<tr>
			<td>
				<label>Drop Date and Time</label><br>
				<input id="dropdatetimepicker" type="text" size="20" onmouseover="javascript:$('#dropdatetimepicker').datetimepicker();">
			</td>
		</tr>
		<tr>
			<td>
				Location: <select name= "branchlocation">
					<option value="vancouver">Vancouver</option>
				</select> 
			</td>
		</tr>
		<tr>
			<td>
				<input type="submit" value="Submit" name="submit">
			</td>
		</tr>
	</table>&nbsp;
        <table border="1" width="100%" bgcolor="#FF0000" cellspacing="0" cellpadding="0" bordercolor="#000000">
          <tr>
            <td width="100%"><b><font color="#FFFFFF" face="Arial" size="2">&nbsp;
              Hot Spots
              </font></b></td>
          </tr>
        </table>
            <p align="left"><font face="Arial" size="2"><u><img border="0" src="img/bullet.gif" width="10" height="12"></u>&nbsp;
            <u>Place Link 1 Here<br>
            <img border="0" src="img/bullet.gif" width="10" height="12"></u>&nbsp;
            <u>Place Link 2 Here<br>
            <img border="0" src="img/bullet.gif" width="10" height="12"></u>&nbsp;
            <u>Place Link 3 Here<br>
            <img border="0" src="img/bullet.gif" width="10" height="12"></u>&nbsp;
            <u>Place Link 4 Here<br>
            <img border="0" src="img/bullet.gif" width="10" height="12"></u>&nbsp;
            <u>Place Link 5 Here<br>
            </u>&nbsp;</font>
        <table border="1" width="100%" bordercolor="#000000" cellspacing="0" cellpadding="0" bgcolor="#FF0000">
          <tr>
            <td width="100%"><b><font color="#FFFFFF" face="Arial" size="2">&nbsp;
              Sponsors
              </font></b></td>
          </tr>
        </table>
            <p align="left"><font face="Arial" size="2"><u><img border="0" src="img/bullet.gif" width="10" height="12"></u>&nbsp;
            <u>Place Sponsor 1 Here<br>
            <img border="0" src="img/bullet.gif" width="10" height="12"></u>&nbsp;
            <u>Place Sponsor 2 Here<br>
            <img border="0" src="img/bullet.gif" width="10" height="12"></u>&nbsp;
            <u>Place Sponsor 3 Here<br>
            <img border="0" src="img/bullet.gif" width="10" height="12"></u>&nbsp;
            <u>Place Sponsor 4 Here<br>
            <img border="0" src="img/bullet.gif" width="10" height="12"></u>&nbsp;
            <u>Place Sponsor 5 Here</u></font>
        <p>&nbsp;</p>
        <p>&nbsp;</p>
        <p>&nbsp;</p>
        <p>&nbsp;</td>
  
      <td width="50%" valign="top">
        <div align="center">
          <table border="0" width="95%" bgcolor="#FFCCCC" cellspacing="0" cellpadding="0">
            <tr>
              <td>
                <p align="left"><b><font face="Arial" color="#000000" size="2">&nbsp;
                Search The Web</font></b></td>
            </tr>
          </table>
        </div>
  
        <div align="center">
  
          <table border="0" width="95%" bordercolor="#FF0000" cellspacing="0" cellpadding="0" bgcolor="#FFCCCC">
            <tr>
              <td width="100%">
  
  <table cellpadding="0" cellspacing="0" border="0" bordercolor="#000000" bgcolor="#FFCCCC" width="75%">
    <tr>
      <td>&nbsp;
      <div id="content" style="height:500px;width:80%;float:right;">



<table  border= 1 >
	<% VehicleListBeanModel vehicle = (VehicleListBeanModel) request.getAttribute("vehicles");
	ArrayList<VehicleBeanModel> vehlist = vehicle.getVehlist();
	VehicleBeanModel veh= new VehicleBeanModel();
	int len = vehlist.size();
	int index=0;
	while(len>index)
	{
		veh = vehlist.get(index);
	%>

		<tr style="height:10px;width:50px;">
		<td><font size="2" face="Verdana"><% out.write(Integer.toString(veh.getRegNo())); %></font></td>
		<td><font size="2" face="Verdana"><% out.write(veh.getCategory()); %></font></td>
		<td><font size="2" face="Verdana"><% out.write(veh.getBrand()); %></font></td>
		<td><font size="2" face="Verdana"><% out.write(veh.getType()); %></font></td>
		    	
	    </tr>
		
		<% index++;
	}
	vehicle.clearVehlist();
	%>
		
		
	
</table>

</div>
</div>
      
  </table>


                  
            <table border="0" width="100%" cellspacing="0" cellpadding="0">
              <tr>
                <td width="100%">
                  <p align="left"><b><i><font face="Arial" size="1" color="#000000">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br>
                  </font><font face="Arial" color="#000000" size="2">&nbsp; NAVIGATION</font><font face="Arial" size="3" color="#000000"><br>
                  </font><font color="#000000" face="Arial" size="1">&nbsp;</font></i></b></td>
              </tr>
            </table>
            <table border="0" width="100%" cellspacing="0" cellpadding="0">
              <tr>
                <td width="54%" valign="top">
                  <blockquote>
                    <p><font face="Arial" size="2"><u>Auction<br>
                    Classifieds<br>
                    Employment<br>
                    Entertainment<br>
                    Local Links</u></font></p>
                  </blockquote>
                </td>
                <td width="46%" valign="top"><font face="Arial" size="2"><u>Message
                  Boards<br>
                  Personals<br>
                  Services<br>
                  Shopping</u></font></td>
              </tr>
            </table>
  <center>
          <center>
          <center>
  <center>
  </center></center></center></center></td>
            </tr>
          </table>
        </div>
  <table border="0" width="100%" cellspacing="0" cellpadding="0">
    <tr>
      <td width="100%">&nbsp;</td>
    </tr>
  </table>
  <div align="center">
    <center>
    <table border="1" width="95%" bgcolor="#FF0000" bordercolor="#000000" cellspacing="0" cellpadding="0">
      <tr>
        <td width="100%"><b><font color="#FFFFFF" face="Arial" size="2">&nbsp;
          Your Text Headline
              </font></b></td>
      </tr>
    </table>
    </center>
  </div>
        <p align="justify" style="margin-left: 10; margin-right: 10"><font face="Arial" size="2">Place
        your text here... Place your text here... Place your text here... Place
        your text here... Place your text here... Place your text here... Place
        your text here... Place your text here... Place your text here... Place
        your text here... Place your text here... Place your text here...&nbsp;</font><br>
        <div align="center">
          <center>
          <table border="1" width="95%" bordercolor="#000000" cellspacing="0" cellpadding="0" bgcolor="#FF0000">
            <tr>
              <td width="100%"><b><font color="#FFFFFF" face="Arial" size="2">&nbsp;
                Site Update Info
              </font></b></td>
            </tr>
          </table>
          </center>
        </div>
        <p align="justify" style="margin-left: 10; margin-right: 10"><font face="Arial" size="2">Place
        your site update info here... Place your site update info here... Place
        your site update info here... Place your site update info here... Place
        your site update info here... Place your site update info here... Place
        your site update info here...&nbsp;<br>
        &nbsp;</font></td>
  <center>
      <td width="25%" valign="top">
        <table border="1" width="100%" bgcolor="#FF0000" cellspacing="0" cellpadding="0" bordercolor="#000000">
          <tr>
            <td width="100%"><b><font color="#FFFFFF" face="Arial" size="2">&nbsp;
              </font><font color="#000000"><font face="Arial" size="2">
              </font></font><font face="Arial" color="#FFFFFF" size="2">Deals Of The Month</font></b></td>
          </tr>
        </table>
            <p align="left"><font face="Arial" size="2"><u><img border="0" src="img/bullet.gif" width="10" height="12"></u>&nbsp;
            <u>Place Link 1 Here<br>
            <img border="0" src="img/bullet.gif" width="10" height="12"></u>&nbsp;
            <u>Place Link 2 Here<br>
            <img border="0" src="img/bullet.gif" width="10" height="12"></u>&nbsp;
            <u>Place Link 3 Here<br>
            <img border="0" src="img/bullet.gif" width="10" height="12"></u>&nbsp;
            <u>Place Link 4 Here<br>
            <img border="0" src="img/bullet.gif" width="10" height="12"></u>&nbsp;
            <u>Place Link 5 Here<br>
            </u>&nbsp;</font>
        <table border="1" width="100%" bgcolor="#FF0000" cellspacing="0" cellpadding="0" bordercolor="#000000">
          <tr>
            <td width="100%"><b><font color="#FFFFFF" face="Arial" size="2">&nbsp;
              Upcoming Events
              </font></b></td>
          </tr>
        </table>
            <p align="left"><font face="Arial" size="2"><u><img border="0" src="img/bullet.gif" width="10" height="12"></u>&nbsp;
            <u>Place Link 1 Here<br>
            <img border="0" src="img/bullet.gif" width="10" height="12"></u>&nbsp;
            <u>Place Link 2 Here<br>
            <img border="0" src="img/bullet.gif" width="10" height="12"></u>&nbsp;
            <u>Place Link 3 Here<br>
            <img border="0" src="img/bullet.gif" width="10" height="12"></u>&nbsp;
            <u>Place Link 4 Here<br>
            <img border="0" src="img/bullet.gif" width="10" height="12"></u>&nbsp;
            <u>Place Link 5 Here<br>
            </u>&nbsp;</font>
        <table border="1" width="100%" bordercolor="#000000" cellspacing="0" cellpadding="0" bgcolor="#FF0000">
          <tr>
            <td width="100%"><b><font color="#FFFFFF" face="Arial" size="2">&nbsp;
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
        <p>&nbsp;</td>
    </tr>
  </table>
  </center>
</div>
<hr color="#FF0000" size="3" width="90%">
<div align="center">
  <center>
  <table border="0" width="90%" cellspacing="0" cellpadding="0">
    <tr>
      <td width="100%">
<p align="center"><font size="1" face="Verdana"><b>Main Site Navigation:</b><u><br>
Your Link</u>&nbsp;&nbsp; <font color="#000000">|</font>&nbsp;&nbsp; <u>Your
Link</u>&nbsp;&nbsp; <font color="#000000">|</font>&nbsp;&nbsp; <u>Your Link</u>&nbsp;&nbsp;<font color="#000000"> |&nbsp;</font>&nbsp;
<u>Your Link</u> &nbsp; <font color="#000000">|</font>&nbsp;&nbsp; <u>Your Link</u></font>

</p>
<p align="center"><font face="Verdana" size="1">&nbsp;<b>Site Navigation:</b><u><br>
Your Link</u>&nbsp;&nbsp; |&nbsp;&nbsp; <u>Your Link</u>&nbsp;&nbsp;
|&nbsp;&nbsp; <u>Your Link</u>&nbsp;&nbsp; |&nbsp;&nbsp; <u>Your Link</u>&nbsp;&nbsp;
|&nbsp;&nbsp; <u>Your Link<br>
Your Link</u>&nbsp;&nbsp; |&nbsp;&nbsp; <u>Your Link</u>&nbsp;&nbsp;
|&nbsp;&nbsp; <u>Your Link</u>&nbsp;&nbsp; |&nbsp;&nbsp; <u>Your Link</u></font>

</p>
      </td>
    </tr>
  </table>
  </center>
</div>
<p align="center"><font face="Arial" size="1" color="#000000"><strong><b>© Copyright 2000
All Rights Reserved YOURDOMAIN.COM</b></strong></font></p>

</form>
</body>
</html>
