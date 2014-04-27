function formValidation()
{
var uid = document.registration.userid;
var passid = document.registration.passid;
var uname = document.registration.username;
var uadd = document.registration.address;
var ucountry = document.registration.country;
var uzip = document.registration.zip;
var uemail = document.registration.email;
var umsex = document.registration.msex;
var ufsex = document.registration.fsex; 
//if(userid_validation(uid,5,12))
//{
//if(passid_validation(passid,7,12))
//{
if(allLetter(uname))
{
//if(alphanumeric(uadd))
//{ 
//if(countryselect(ucountry))
//{
if(allnumeric(uzip, 10))
{
if(ValidateEmail(uemail))
{
if(alphanumeric(uadd)){
//if(validsex(umsex,ufsex))
//{
}
} 
}
}
//} 
//}
//}
//}
//}
return true;
}

//-------------------for search
var e;
var pick;
var drop;
function forsearch(e, pick, drop){
    var strUser = e.options[e.selectedIndex].value;
    //var strUser1 = e.options[e.selectedIndex].text;
    if(strUser==0)
    {
        alert("Please select a category");
        return false;
    }
    var p = pick.value.length;
    var d = drop.value.length;
    if((p != 0) && (d != 0)){
    	return true;
    }else{
    	alert("Please enter the pickup and drop date");
        return false;
    }
    return true;
}

//-------------for cancellation with ph and date

var conf;
var ph;
var dt;
function forcancel(conf, ph, dt){
	var numbers = /^[0-9]+$/;
	var uzip_len = conf.value.length;
	var ph_len = ph.value.length;
	var dt_len = dt.value.length;
	if((uzip_len ==0)&&(ph_len ==0)&&(dt_len ==0)){
		alert("Either enter confirmation number or (pickdate &  phoneno)");
		return false;
	}
	if((uzip_len !=0)&&(ph_len !=0)&&(dt_len !=0)){
		alert("Either enter confirmation number or (pickdate &  phoneno)");
		return false;
	}else if((uzip_len != 0)){
		if(conf.value.match(numbers)){
			return true;
		}else{
		alert('Confirmation number must have numeric only');
		conf.focus();
		return false;
		}
	}else if((ph_len != 0)){
		if(dt_len == 0){
			alert('Also enter pickup date');
			dt.focus();
			return false;
		}else if (fordatetime(dt)==false){
			alert('Invalid pickup date');
			dt.focus();
			return false;
		}else if(allnumeric(ph)==false){
			return false;
		}
	}else if((dt_len != 0)){
		if(ph_len == 0){
			alert('Also enter phonenumber');
			ph.focus();
			return false;
		}else if (fordatetime(dt)==false){
			alert('Invalid pickup date');
			dt.focus();
			return false;
		}else if(allnumeric(ph)==false){
			return false;
		}
	}
	
	return true;
}

//not required
function userid_validation(uid,mx,my)
{
var uid_len = uid.value.length;
if (uid_len == 0 || uid_len >= my || uid_len < mx)
{
alert("User Id should not be empty / length be between "+mx+" to "+my);
uid.focus();
return false;
}
return true;
}

//not required
function passid_validation(passid,mx,my)
{
var passid_len = passid.value.length;
if (passid_len == 0 ||passid_len >= my || passid_len < mx)
{
alert("Password should not be empty / length be between "+mx+" to "+my);
passid.focus();
return false;
}
return true;
}

//-------------for name field in reservation form--------------------
function allLetter(uname)
{ 
var letters = /^[A-Za-z]+$/;
var uname_len = uname.value.length;
if(uname_len == 0){
	alert("Name should not be empty");
	uid.focus();
	return false;
}else if(uname.value.match(letters)){
		return true;
}else{
	alert('Username must have alphabet characters only');
	uname.focus();
	return false;
}
}

//not required
function passid_validation(passid,mx,my)
{
var passid_len = passid.value.length;
if (passid_len == 0 ||passid_len >= my || passid_len < mx)
{
alert("Password should not be empty / length be between "+mx+" to "+my);
passid.focus();
return false;
}
return true;

}

//--------------address required
function alphanumeric(uadd)
{ 
var letters = /^[0-9a-zA-Z]+$/;
if(uadd.value.match(letters))
{
return true;
}
else
{
alert('User address must have alphanumeric characters only');
uadd.focus();
return false;
}
}
function countryselect(ucountry)
{
if(ucountry.value == "Default")
{
alert('Select your country from the list');
ucountry.focus();
return false;
}
else
{
return true;
}
}

//------------------for phone no in reservation form
function allnumeric(uzip)
{ 
var numbers = /^[0-9]+$/;
var uzip_len = uzip.value.length;
if(uzip_len == 0){
	alert('Phone number field must not be empty');
	uzip.focus();
	return false;	
}else if(uzip_len<10 || uzip_len>10){
	alert('Phone number must be of 10 digits');
	uzip.focus();
	return false;
}else if(uzip.value.match(numbers)){
	return true;
}else{
	alert('Phone number must have numeric characters only');
	uzip.focus();
	return false;
}
}
//--------------

//----------------for email in reservation form
function ValidateEmail(uemail)
{
var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
var uemail_len = uemail.value.length;
if(uemail_len ==0){
	alert("You have entered an invalid email address!");
	uemail.focus();
	return false;
}else if(uemail.value.match(mailformat)){
	return true;
}else{
	alert("You have entered an invalid email address!");
	uemail.focus();
	return false;
}
}

//not required
function validsex(umsex,ufsex)
{
x=0;

if(umsex.checked) 
{
x++;
}
if(ufsex.checked)
{
x++; 
}

if (x==2)
{
alert('Both Male/Female are checked');
ufsex.checked=false;
umsex.checked=false;
umsex.focus();
return false;
}

if(x==0)
{
alert('Select Male/Female');
umsex.focus();
return false;
}
else
{
alert('Form Succesfully Submitted');
window.location.reload();
return true;
}
}
var memNo;
//-----------for membership
function formembership(memNo){
	var numbers = /^[0-9]+$/;
	var uzip_len = memNo.value.length;
	if(uzip_len == 0){
		alert('No membership number given');
		uzip.focus();
		return false;	
	}else if(memNo.value.match(numbers)){
		return true;
	}else{
		alert('Membership number must have numeric characters only');
		uzip.focus();
		return false;
	}
	
}

//------------datetime picker
var ctrl;
function fordatetime(ctrl){
	var v = ctrl.value;
	var v_len = ctrl.value.length;
	/*
	if (v.replace(/^\\s+|\\s+$/, '').length != 0 && isNaN(Date.parse(v))) { 
	      //alert('Invalid value for pick-up date!'); 
	      //ctrl.focus();
	      return false; 
	}
	*/
	if((v_len != 0)){
		if(!(isNaN(Date.parse(v)))){
		return true;
		}
		return false;
	}
	return false;
}