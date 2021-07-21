package net.servlets;
import java.io.IOException;
import net.classes.*;
import net.queries.database.*;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.classes.UserBean;
import net.queries.database.EditUserDao;
import net.queries.database.LoginDao;
import javax.servlet.annotation.*;


@WebServlet("/CatalogueServlet")
public class CatalogueServlet extends HttpServlet
{	
	private static final long serialVersionUID = 1L;
	private CatalogueDao catalogueDao;
	private AppointmentBean catalogueBean;
	private LoginBean loginBean;
	private LoginDao loginDao;

    public void init() {
        catalogueDao = new CatalogueDao();     
        catalogueBean = new AppointmentBean();
        loginBean = new LoginBean();
        loginDao = new LoginDao();
    }
    //Method doGet
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
    	    	
    	HttpSession session1 = request.getSession();             			
		try {
			
			loginDao.select_userlog(loginBean);					
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
       
        // send HTML page to client
        out.println("<html>");
        out.println("<head>");
        out.println("<link rel=\"stylesheet\" type=\"text/css\" href='style.css'>");
        out.println("</head>");
        out.println("<body>");
        out.println("<ul>");
        out.println("<li><a  href=\"#profile\">Profile</a></li>");
        try {
			loginDao.select_userlog(loginBean);
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        if(loginBean.getProperty().equals("Patient")) {
        out.println("<li><a href=\"BookAppointmentServlet\">Book Appointment</a></li>");
        }
        out.println("<li><a class=\"active\" href=\"#catalogue\">Catalogue</a></li>");
        out.println("<li><a onclick=\"document.getElementById('id09').style.display='block'\">Online Help</a></li>");        
        out.println("<li><a href=\"login.jsp\">Log Out</a></li>");
        out.println("</ul");
        out.println("<br>\r\n" + 
        		"<br> \r\n" +
        		"<br> \r\n" +         		
        		"<h1>Catalogue</h1>\r\n" + 
        		"<br>\r\n" + 
        		"<h2>All your booked appointments in one place!</h2>\r\n" + 
        		"<br>\r\n" + 
        		"<br>\r\n" + 
        		"<div class='catalogue' style=\"display: block;\">\r\n" + 
        		"<form action='Catalogue2Servlet' method='post'>\r\n"+
        		"<table style='width: 60%;'class=\"table\">\r\n" + 
        		"<thead class=\"thead\">\r\n" + 
        		"<tr class=\"headerrow\">\r\n" + 
        		"<th style= 'text-align: center'>Patient</th>\r\n" + 
        		"<th style= 'text-align: center'>Doctor</th>\r\n" + 
        		"<th style= 'text-align: center'>Time of Appointment</th>\r\n" + 
        		"<th style= 'text-align: center'>Date of Appointment</th>\r\n" + 
        		"<th style= 'text-align: center'>Description</th>\r\n" + 
        		"<th style= 'text-align: center'>Status</th>\r\n" + 
        		"</tr>\r\n" +
        		"</thead>\r\n" + 
        		"<tbody class=\"cataloguebody\"></tbody>\r\n");      
        //Dynamically printing a table with the appointments
		try {
			ArrayList <String> catalogueList = new ArrayList<>();
			catalogueDao.printCatalogue(loginBean,catalogueList);	
			int count=0;
											    							    		
			out.println("<tr>\r\n");
			for(String catalogue : catalogueList){
				count++;
				out.println("<td style= 'text-align: center; color: white'>"+catalogue+"</td>\r\n");
				//Cancel button: dynamically appearing if an appointment is marked as "active"
				if(catalogue.equals("active")) {
				out.println("<td style= 'text-align: center; color: white'>");
				out.println("<button type='submit' name='cancel' formmethod='post'>Cancel Appointment</button>");
				out.println("</td>\r\n");
				}
				//Changes row after every appointment
				if(count==6) {
					 out.println("</tr>\r\n");
					 out.println("<tr>\r\n");
					 count=0;
				}  		
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        //Online Help    
        out.println("</table>");
        out.println("<div id=\"id09\" class=\"modal\">\r\n" + 
        		"  <form class=\"modal-content animate\" action=\"<%=request.getContextPath()%>/LoginServlet\" method=\"post\">\r\n" + 
        		"    <div class=\"imgcontainer1\">\r\n" + 
        		"      <span onclick=\"document.getElementById('id09').style.display='none'\" class=\"close\" title=\"Close Modal\">&times;</span>\r\n" + 
        		"    </div>\r\n" + 
        		"    <div class=\"container\">\r\n" + 
        		"      <label for=\"info\"><b>This is the Profile Information Page.</b></label>\r\n" + 
        		"      <br>\r\n" + 
        		"      <label for=\"info\"><b>Here you can see all your personal information.</b></label>\r\n" + 
        		"      <br>\r\n" + 
        		"      <label for=\"info\"><b>Edit Profile: click on the button and a form will appear where you can change some profile information. Please fill only the fields you want to change. Click on Save Changes to update your pofile information.</b></label>\r\n" + 
        		"      <br>\r\n" + 
        		"      <label for=\"info\"><b>Delete Profile: if you want to delete your profile click on this button and later confirm your decision. You will not be able to log in afterwards. Your profile is going to be permanently deleted.</b></label>     \r\n" + 
        		"      <br>\r\n" + 
        		"      <label for=\"info\"><b>Menu Bar: click on the action you want to do.</b></label>  \r\n" + 
        		"    </div>\r\n" + 
        		"    <div class=\"container1\" style=\"background-color:#f1f1f1\">\r\n" + 
        		"      <button type=\"button\" onclick=\"document.getElementById('id09').style.display='none'\" class=\"cancelbtn\">Cancel</button>\r\n" + 
        		"    </div>\r\n" + 
        		"  </form>\r\n" + 
        		"</div>");
        out.println("</form>");
        out.println("</body></html>");        
    }       
}

