<%@ page import = "net.servlets.RegisterServlet" language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
 <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
<a  href="/p18022-p18046-p18041-p18051-p18135.pdf" target="_blank" style="width:auto;">User Manual</a>
<button  onclick="document.getElementById('id09').style.display='block'" style="width:auto; float: right;">Online Help</button>
<br>
<br>
<br>
<br>
<h1 style = "text-align:center;">Welcome to the No 1 Website for Booking Medical Appointments!</h1>
<br>
<h2 style = "text-align:center;color: white">Already Have An Account? Click to Login</h2>
<!-- Login Button -->
<button  style = "text-align:center; width: 110px; margin-left: 45%; margin-right: 45%" onclick="document.getElementById('id01').style.display='block'">Login</button>
<!-- Online Help -->
<div id="id09" class="modal">
  <form class="modal-content animate" action="<%=request.getContextPath()%>/LoginServlet" method="post">
    <div class="imgcontainer1">
      <span onclick="document.getElementById('id09').style.display='none'" class="close" title="Close Modal">&times;</span>
    </div>
    <div class="container">
      <label for="info"><b>This is the Login and Register Page.</b></label>
      <br>
      <label for="info"><b>If you already have an account: click on the Login button and type your username and password.</b></label>
      <br>
      <label for="info"><b>If you don't have  an account: click on the Register button and type your personal information.</b></label>
      <br>
      <label for="info"><b>Forgot Password? Don't worry! We are gonna generate a new password for you and you can later change it through Edit Profile.</b></label>     
      <br>
      <label for="info"><b>User Manual: you can click the button and you automatically going to transfer to the User Manual, a pdf full of information about how this web application works.</b></label>  
    </div>
    <div class="container1" style="background-color:#f1f1f1">
      <button type="button" onclick="document.getElementById('id09').style.display='none'" class="cancelbtn">Cancel</button>
    </div>
  </form>
</div>
<!-- Login Form -->
<div id="id01" class="modal">
  <form class="modal-content animate" action="<%=request.getContextPath()%>/LoginServlet" method="post">
    <div class="imgcontainer1">
      <span onclick="document.getElementById('id01').style.display='none'" class="close" title="Close Modal">&times;</span>
    </div>
    <div class="container">
      <label for="username"><b>Username</b></label>
      <input type="text" placeholder="Enter Username" name="username" id="username" required>
      <label for="password"><b>Password</b></label>
      <input type="password" placeholder="Enter Password" name="password" id="password" required>
      <button type="submit" formmethod="post" style="width:auto;">Login</button>             
    </div>
    <div class="container1" style="background-color:#f1f1f1">
      <button type="button" onclick="document.getElementById('id01').style.display='none'" class="cancelbtn">Cancel</button>
     
    </div>
  </form>
</div>

<h2  style = "text-align:center;color: white">...Or Create New Account. Register Now</h2>
<!-- Register Button -->
<button  style = "text-align:center; width: 110px; margin-left: 45%; margin-right: 45%" onclick="document.getElementById('id02').style.display='block'" style="width:auto;">Register</button>
<!-- Register Form -->
<div id="id02" class="modal">
  <form class="modal-content animate" action="<%=request.getContextPath()%>/RegisterServlet" method="post">
    <div class="imgcontainer">
      <span onclick="document.getElementById('id02').style.display='none'" class="close" title="Close Modal">&times;</span>
    </div>
    <div class="container">
      <label for="username"><b>Username</b></label>
      <input type="text" placeholder="Enter Username" name="username" id="username" required>
	  <label for="name"><b>Name</b></label>
      <input type="text" placeholder="Enter Name" name="name" id="name" required>
      <label for="surname"><b>Surname</b></label>
      <input type="text" placeholder="Enter Surname" name="surname" id="surname" required>
      <label for="psw"><b>Password</b></label>
      <input type="password" placeholder="Enter Password" name="psw" id="psw" required>
      <label for="property"><b>Property (Patient or Doctor)</b></label>
      <input id = "property" type="text" placeholder="Enter Property" name="property" id="property" required>
      <label for="department"><b>Department (for Doctors only)</b></label>
      <input id="department" type="text" placeholder="Enter Department" name="department" id="department">
      <label for="ssn"><b>SSN</b></label>
      <input type="text" placeholder="Enter SSN" name="ssn" id="ssn" required>
      <label for="phonenumber"><b>Phone Number</b></label>
      <input type="text" placeholder="Enter Phone Number" name="phonenumber" id="phonenumber" required>
      <label for="address"><b>Address</b></label>
      <input type="text" placeholder="Enter Address" name="address" id="address" required>
      <label for="postalcode"><b>Postal Code</b></label>
      <input type="text" placeholder="Enter Postal Code" name="postalcode" id="postalcode" required>
      <label for="email"><b>Email</b></label>
      <input type="text" placeholder="Enter Email" name="email" id="email" required>
      <br>
      <input type="checkbox" id="terms" name="terms" value="terms" required>
      <label for="terms">Accepting Terms and Conditions</label>
      <br>
      <br>
      <button type="submit" formmethod="post" style="width:auto;">Create New Account</button>
    </div>
    <div class="container" style="background-color:#f1f1f1">
      <button type="button" onclick="document.getElementById('id02').style.display='none'" class="cancelbtn">Cancel</button>
    </div>
  </form>
</div>
</body>
</html>
