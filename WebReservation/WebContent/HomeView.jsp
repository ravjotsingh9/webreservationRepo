<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome To SuperRent </title>
</head>
<body>
<form method="get" action="SearchCar" name="form">
	<input type="hidden" name="hbtn" />
	<table>
	<tr height="200">
		<td valign="middle" align="center"><a href="HomeView.jsp"><img style="border:1;" src="images/superRent2.jpg" alt="some_text" ></a></td>
		<td></td>
	</tr>
	<tr>
		<td valign="middle"><input name="btn1" value="1" type="image" src="images/car0.jpg" 
		onclick="{document.forms[0].hbtn.value=this.value;document.forms[0].submit();}"
		alt="submit form"></td>
		<td valign="middle"><input name="btn2" value="2" type="image" src="images/truck0.jpg" 
		onclick="{document.forms[0].hbtn.value=this.value;document.forms[0].submit();}"
		alt="submit form"></td>
	</tr>
	</table>
</form>
</body>
</html>