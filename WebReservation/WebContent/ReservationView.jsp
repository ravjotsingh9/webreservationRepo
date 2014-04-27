<%@ page import= "org.reservation.module.controller.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel='stylesheet' href='js-form-validation.css' type='text/css' />
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
<div align="center">
          <table border="0" width="50%" bgcolor="#7f8bb7" cellspacing="0" cellpadding="0">
            <tr>
              <td>
                <p align="left"><b><font face="Arial" color="#000000" size="3">
                Complete your reservation</font></b></td>
            </tr>
          </table>

        <table border="0" bordercolor="#7f8bb7" cellspacing="0" cellpadding="0" bgcolor="#f3f5f8">
        <tr>

        <td rowspan="7" colspan="1" style="height:30px;width:150px;background-color:#d2d9e4"><font size="2" face="Verdana"><label for="lblcost">Estimated Cost:</label></font>
		<br><label for="cost">123</label></td>
		<td>
    	<table border="0" align="left">
    	<tr>	
		<td><font size="2" face="Verdana"><label for="username">Name:</label></font></td>
		<td><input type="text" name="username" size="20" /></td>
		</tr>
		<tr>
		<td><font size="2" face="Verdana"><label for="zip">Phone Number:</label></font></td>
		<td><input type="text" name="zip" /></td>
		</tr>
		<tr>
		<td><font size="2" face="Verdana"><label for="email">Email Address:</label></font></td>
		<td><input type="text" name="email" size="20" /></td>
		</tr>
		<tr>
		<td><font size="2" face="Verdana"><label for="address">Residential Address:</label></font></td>
		<td><textarea rows="2" cols="3" name="desc" id="desc" style="height: 40px;width:150px"></textarea></td>
		</tr>
		<tr>
		<td><font size="2" face="Verdana"><label id="additional equipment">Additional Equipment:</label></font></td>
		<td>
		<input type="checkbox" id="childseat" name="childseat" value="childseat">Child Seat
		<input type="checkbox" id="skirack" name="skirack" value="skirack">Ski Rack
		<select id="slct1" name="slct1" onchange="populate(this.id, 'slct2')">
	   	<option value=""></option>
   		<option value="Yes">Yes</option>
   		<option value="No">No</option>
		</select>
		</td>
		</tr>
		<tr>
		<td></td>
		<td id="slct2"></td>
		</tr>
		<tr>
		<td></td>
		<td align="left" valign="top"><input type="button" name="submit" value="Submit" />
		</td>
		</tr>
		</table>
    
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
