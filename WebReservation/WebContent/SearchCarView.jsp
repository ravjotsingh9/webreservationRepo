<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script language="javascript" type="text/javascript" src="datetimepicker.js">
//Date Time Picker script 
</script>
<style>
table,td,th
{
border:0px solid black;
}
table
{
width:100%;
}
th
{
height:50px;
}
</style>
<title>Car Reservation</title>
</head>
<body>
<div id="container" style="width:100%; height:100%;">

	<div id="header" style="background-color:#FFA500;">
	<h1><center>SUPER RENT</center></h1>
	</div>
	<div id="header1" style="background-color:#FFA500;">
	<form>
	<table>
	<tr>
		<td>Category: CAR</td>
		<td>
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
		</td>
		<td>
			<label>Pickup Date and Time</label>
			<input id="demo3" type="text" size="20" disabled>
			<a href="javascript:NewCal('demo3','ddmmmyyyy',true,24)">
			<img src="images/cal.gif" width="16" height="16" border="0" alt="Pick a date"></a>
		</td>
		<td>
			<label>Drop Date and Time</label>
			<input id="demo4" type="text" size="20" disabled>
			<a href="javascript:NewCal('demo4','ddmmmyyyy',true,24)">
			<img src="images/cal.gif" width="16" height="16" border="0" alt="Pick a date"></a>
		</td>
		<td>
			Location: <select name= "branchlocation">
  				<option value="vancouver">Vancouver</option>
  			</select> 
		</td>
		<td>
			<input type="submit" value="Submit" name="submit">
		</td>
	</table>
</form>
</div>
<div id="menu" style="background-color:#FFD700;height:500px;width:20%;float:left;">
<b>Menu</b><br>
<a href="">Cancel Reservation</a><br>
<a href="">Club Member Login</a><br>
<a href="">Check Points</a></div>

<div id="content" style="background-color:#EEEEEE;height:500px;width:80%;float:right;">
Content goes here</div>

<div id="footer" style="background-color:#FFA500;clear:both;text-align:center;">
Copyright © www.superrent.com</div>
</div>
</body>
</html>