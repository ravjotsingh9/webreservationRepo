<%@ page import= "org.reservation.module.controller.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel='stylesheet' href='js-form-validation.css' type='text/css' />
<script src="registration-form-validation.js"></script>

<title>Make Reservation</title>
<style TYPE="text/css"><!--
    A:link {text-decoration: none; color: #000000}
    a:visited {text-decoration: none; color: #000000}
    a:active {text-decoration: none; color: #000000}
    a:hover{color: #FF0000}
--></style>

</head>

<body bgcolor="#FFFFFF" onLoad="registration-form-validation.js">
<form name="registration" onSubmit="return formValidation();">
<div align="center">
  
  <table border="0" width="90%" cellspacing="0" cellpadding="0" background="img/bkg2.gif">
    <tr>
      <td width="5%" valign="top" height="53"><font face="Arial Black" size="4">SITELOGO</font></td>
      <td width="95%" valign="bottom">
        <table border="0" width="100%" cellspacing="0" cellpadding="0" height="25">
          <tr>
            <td width="100%" valign="top"></td>
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
          <table border="0" width="50%" bgcolor="#FFCCCC" cellspacing="0" cellpadding="0">
            <tr>
              <td>
                <p align="left"><b><font face="Arial" color="#000000" size="2">&nbsp;
                Complete your reservation</font></b></td>
            </tr>
          </table>

  

          <table border="0" width="50%" bordercolor="#FF0000" cellspacing="0" cellpadding="0" bgcolor="#FFCCCC">
            <tr>
              <td width="100%">
      
	<ul type="disc"> 
	<li><label for="userid">User id:</label> </li> 
	<li><input type="text" name="userid" size="12" /></li>  
	<li><label for="passid">Password:</label></li>
	<li><input type="password" name="passid" size="12" /></li>  
	<li><label for="username">Name:</label></li>
	<li><input type="text" name="username" size="50" /> </li>  
	<li><label for="zip">Phone Number:</label> </li>
	<li><input type="text" name="zip" />  </li>
	<li><label for="email">Email:</label></li>
	<li><input type="text" name="email" size="50" /></li>
	<li><label id="additional equipment">Additional Equipment:</label></li>
	<li><input type="radio" name="sex" value="yes" /><span>Yes</span>  </li>
	<li><input type="radio" name="sex" value="no" /><span>No</span>  </li>
	<li><label>Language:</label>  </li>
	<li><input type="checkbox" name="en" value="en" checked /><span>English</span></li>  
	<li><input type="checkbox" name="nonen" value="noen" /><span>Non English</span></li>  
	<li><label for="desc">About:</label></li>
	<li><textarea name="desc" id="desc"></textarea></li>
	<li><input type="submit" name="submit" value="Submit" /></li>
	</ul>  
  
                  
           
 </td>
     </tr>
  </table>
</div>
<hr color="#FF0000" size="3" width="90%">
<div align="center">
</div>
<p align="center"><font face="Arial" size="1" color="#000000"><strong><b>© UnderTraining SUPERRENT.COM</b></strong></font></p>

</form>
</body>
</html>
