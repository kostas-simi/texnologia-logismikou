<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
 <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
<ul>
  <li><a  href="#profile">Profile</a></li>
  <li><a class="active" href="BookAppointmentServlet">Book Appointment</a></li>
  <li><a href="CatalogueServlet">Catalogue</a></li>
  <li><a onclick="document.getElementById('id09').style.display='block'">Online Help</a></li>
  <li><a href="login.jsp">Log Out</a></li>
</ul>
<br>
<br> 
<br>
<br>
<!-- Book Appointment Form -->
<div>
  <form style= "background-color: #333;" action="<%=request.getContextPath()%>/SubmitAppointmentServlet" method="post">
    <div class="container">      
       <label for="username_d"><b>Doctor Username</b></label>
      <input type="text" placeholder="Enter Doctor Username" name="username_d" id="username_d" required>
       <label for="time_appointment"><b>Time of Appointment</b></label>
      <input type="text" placeholder="Enter Time" name="time_appointment" id="time_appointment" required>
       <label for="date_appointment"><b>Date of Appointment</b></label>
      <input type="text" placeholder="Enter Date" name="date_appointment" id="date_appointment" required>
 	  <label for="description"><b>Description</b></label>
      <input type="text" placeholder="Enter Description" name="description" id="description" required>
      <button type="submit" formmethod="post" style="width:auto;">Submit Appointment</button>             
    </div>
    </form>
    <form action="<%=request.getContextPath()%>/BookAppointmentServlet">
    <div class="container1" style="background-color:#333">
      <button type="submit" class="cancelbtn">Cancel</button>
    </div>
    </form>
  
</div>
<!-- Online Help -->
<div id="id09" class="modal">
  <form class="modal-content animate" action="<%=request.getContextPath()%>/LoginServlet" method="post">
    <div class="imgcontainer1">
      <span onclick="document.getElementById('id09').style.display='none'" class="close" title="Close Modal">&times;</span>
    </div>
    <div class="container">
      <label for="info"><b>This is the Book Appointment Page.</b></label>
      <br>
      <label for="info"><b>Here you can search for a doctor by postal code or department.</b></label>
      <br>
      <label for="info"><b>Click Search after entering something on the search bar. The results are going to be shown below the search bar in a table.</b></label>
      <br>
      <label for="info"><b>Book Appointment: click on this button to book an appointment. Type time, date and description and confirm your appointment by clicking on submit.</b></label>     
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