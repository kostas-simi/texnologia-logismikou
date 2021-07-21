<%@ page import = "net.servlets.LoginServlet" language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
 <link rel="stylesheet" type="text/css" href="style.css">
</head>

<body>
<ul>
  <li><a class="active" href="#profile">Profile</a></li>
  <li><a href="BookAppointmentServlet">Book Appointment</a></li>
  <li><a href="CatalogueServlet">Catalogue</a></li>
  <li><a onclick="document.getElementById('id09').style.display='block'">Online Help</a></li>
  <li><a href="login.jsp">Log Out</a></li>
</ul>
<br>
<br> 

<form class="form1" action="<%=request.getContextPath()%>/EditUserServlet" method="post">
    <div class="container"> 
    <% Object username = session.getAttribute("username"); %>
      <h1 class="register">Welcome <% out.print(username); %>, </h1>
      <hr>
    </div>
 
<h2>Profile Information:</h2>
<br>
<!-- Profile Information -->
 <div class="container">
      <label for="uname"><b>Username:</b></label>
    <% Object username1 = session.getAttribute("username"); %>
      <label style="color:white" class="container"><% out.print(username1); %> </label>            
      <br>
      <br>
     <label for="name"><b>Name:</b></label>
    <% Object name1 = session.getAttribute("name"); %>
      <label style="color:white" class="container"><% out.print(name1); %> </label>      
      <br>
      <br>
     <label for="surname"><b>Surname:</b></label>
    <% Object surname1 = session.getAttribute("surname"); %>
      <label style="color:white" class="container"><% out.print(surname1); %> </label>      
      <br>
      <br>
     <label for="password"><b>Password:</b></label>
    <% Object password1 = session.getAttribute("password"); %>
      <label style="color:white" class="container"><% out.print(password1); %> </label>
      <br>
      <br>
     <label for="property"><b>Property:</b></label>
    <% Object property1 = session.getAttribute("property"); %>
      <label style="color:white" class="container"><% out.print(property1); %> </label>      
      <br>
      <br>
     <label for="ssn"><b>SSN:</b></label>
    <% Object ssn1 = session.getAttribute("ssn"); %>
      <label style="color:white" class="container"><% out.print(ssn1); %> </label>      
      <br>
      <br>
     <label for="phonenumber"><b>Phone Number:</b></label>
    <% Object phonenumber1 = session.getAttribute("phonenumber"); %>
      <label style="color:white" class="container"><% out.print(phonenumber1); %> </label>      
      <br>
      <br>
     <label for="address"><b>Address:</b></label>
    <% Object address1 = session.getAttribute("address"); %>
      <label style="color:white" class="container"><% out.print(address1); %> </label>      
      <br>
      <br>
     <label for="postalcode"><b>Postal Code:</b></label>
    <% Object postalcode1 = session.getAttribute("postalcode"); %>
      <label style="color:white" class="container"><% out.print(postalcode1); %> </label>      
      <br>
      <br>
     <label for="email"><b>Email:</b></label>
    <% Object email1 = session.getAttribute("email"); %>
      <label style="color:white" class="container"><% out.print(email1); %> </label>      
      <br>
      <br>
 </div> 
 </form>
<!-- Edit Profile -->
<div id="id01" class="modal">
  
  <form class="modal-content animate" action="<%=request.getContextPath()%>/EditUserServlet" method="post">
    <div class="imgcontainer1">
      <span onclick="document.getElementById('id01').style.display='none'" class="close" title="Close Modal">&times;</span>
     
    </div>

    <div class="container">
    <h2>Please fill only the fields you want to change</h2>  
      <input type="hidden" name="action" id="action" value="save">          
      <input type="hidden" placeholder="<% out.print(username1); %>" name="username" id="username" value="<% out.print(username1); %>" >   
      <label for="password"><b>Password</b></label>
      <input type="password" placeholder="Enter Password" name="password" id="password" value="<% out.print(password1); %>">
      <label for="phonenumber"><b>Phone Number</b></label>
      <input type="text" placeholder="Enter Phone Number" name="phonenumber" id="phonenumber" value="<% out.print(phonenumber1); %>">
      <label for="address"><b>Address</b></label>
      <input type="text" placeholder="Enter Address" name="address" id="address" value="<% out.print(address1); %>">
      <label for="postalcode"><b>Postal Code</b></label>
      <input type="text" placeholder="Enter Postal Code" name="postalcode" id="postalcode" value="<% out.print(postalcode1); %>">
       <label for="email"><b>Email</b></label>
      <input type="text" placeholder="Enter Email" name="email" id="email" value="<% out.print(email1); %>">
      
      <button type="submit" formmethod="post" style="width:auto;">Save Changes</button>
    </div>
    <div class="container1" style="background-color:#f1f1f1">
      <button type="button" onclick="document.getElementById('id01').style.display='none'" class="cancelbtn">Cancel</button>
    </div>    
  </form>
</div>
<!-- Delete Profile -->
<div id="id03" class="modal">
 <form class="modal-content animate" action="<%=request.getContextPath()%>/EditUserServlet" method="post">
    <div class="imgcontainer1">
      <span onclick="document.getElementById('id03').style.display='none'" class="close" title="Close Modal">&times;</span>
     
    </div>
    <div class="container">
    <h2>Are you sure you want to delete your profile?</h2> 
     <input type="hidden" name="action" id="action" value="delete">
     <input type="hidden" placeholder="<% out.print(username1); %>" name="username" id="username" value="<% out.print(username1); %>" >
     <input type="hidden" placeholder="<% out.print(property1); %>" name="property" id="property" value="<% out.print(property1); %>" >                
      <label for="password"><b>Your profile is going to be deleted permanently.</b></label>
      <br>
      <br>
      <button type="submit" formmethod="post" style="width:auto;">Yes, I'm sure</button>
    </div>
    <div class="container1" style="background-color:#f1f1f1">
      <button type="button" onclick="document.getElementById('id03').style.display='none'" class="cancelbtn">Cancel</button>
    </div>    
</form>
</div>

<!-- Buttons -->
<button  onclick="document.getElementById('id01').style.display='block'" style="width:auto;">Edit Profile</button>
<button  onclick="document.getElementById('id03').style.display='block'" style="width:auto;">Delete Profile</button>

<!-- Online Help -->
<div id="id09" class="modal">
  <form class="modal-content animate" action="<%=request.getContextPath()%>/LoginServlet" method="post">
    <div class="imgcontainer1">
      <span onclick="document.getElementById('id09').style.display='none'" class="close" title="Close Modal">&times;</span>
    </div>
    <div class="container">
      <label for="info"><b>This is the Profile Information Page.</b></label>
      <br>
      <label for="info"><b>Here you can see all your personal information.</b></label>
      <br>
      <label for="info"><b>Edit Profile: click on the button and a form will appear where you can change some profile information. Please fill only the fields you want to change. Click on Save Changes to update your pofile information.</b></label>
      <br>
      <label for="info"><b>Delete Profile: if you want to delete your profile click on this button and later confirm your decision. You will not be able to log in afterwards. Your profile is going to be permanently deleted.</b></label>     
      <br>
      <label for="info"><b>Menu Bar: click on the action you want to do.</b></label>  
    </div>
    <div class="container1" style="background-color:#f1f1f1">
      <button type="button" onclick="document.getElementById('id09').style.display='none'" class="cancelbtn">Cancel</button>
    </div>
  </form>
</div>
</body>
</html>