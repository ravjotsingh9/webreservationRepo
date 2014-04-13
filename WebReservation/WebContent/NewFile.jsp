<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en"><head>
<meta charset="utf-8">
<title>JavaScript Form Validation using a sample registration form</title>
<meta name="keywords" content="example, JavaScript Form Validation, Sample registration form" />
<meta name="description" content="This document is an example of JavaScript Form Validation using a sample registration form. " />
<link rel='stylesheet' href='js-form-validation.css' type='text/css' />
<script src="registration-form-validation.js"></script>
</head>
<body onLoad="document.registration.userid.focus();">
<h1>Registration Form</h1>
<p>Use tab keys to move from one input field to the next.</p>
<form name='registration' onSubmit="return formValidation();">
<ul>
<li><label for="userid">User id:</label> </li> 
	<li><input type="text" name="userid" size="12" /></li>  
	<li><label for="passid">Password:</label></li>
	<li><input type="password" name="passid" size="12" /></li>  
	<li><label for="username">Name:</label></li>
	<li><input type="text" name="username" size="50" /> </li>  
	<li><label for="phonenumber">Phone Number:</label> </li>
	<li><input type="text" name="phone" />  </li>
	<li><label for="email">Email:</label></li>
	<li><input type="text" name="email" size="50" /></li>
	<li><label id="additional equipment">Additional Equipment:</label></li>
	<li><input type="radio" name="yes" value="yes" /><span>Yes</span>  </li>
	<li><input type="radio" name="no" value="no" /><span>No</span>  </li>
	<li><label>Language:</label>  </li>
	<li><input type="checkbox" name="en" value="en" checked /><span>English</span></li>  
	<li><input type="checkbox" name="nonen" value="noen" /><span>Non English</span></li>  
	<li><label for="desc">About:</label></li>
	<li><textarea name="desc" id="desc"></textarea></li>
	<li><input type="submit" name="submit" value="Submit" /></li>
	</ul>  
  </ul>
</form>
</body>
</html>
