package net.servlets;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.classes.UserBean;
import net.queries.database.BookAppointmentDao;
import java.util.ArrayList;
import java.util.regex.Matcher;

@WebServlet("/BookAppointmentServlet")
public class BookAppointmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;	
    public BookAppointmentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    //Method doGet
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		PrintWriter out = response.getWriter();
        // send HTML page to client
        out.println("<html>\r\n" + 
        		"<head>\r\n" + 
        		"<meta charset=\"ISO-8859-1\">\r\n" + 
        		"<link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\">\r\n" + 
        		"\r\n" + 
        		"\r\n" + 
        		"\r\n" + 
        		"</head>\r\n" + 
        		"<body>\r\n" + 
        		"<ul>\r\n" + 
        		"  <li><a  href=\"#profile\">Profile</a></li>\r\n" + 
        		"  <li><a class=\"active\" href=\"BookAppointmentServlet\">Book Appointment</a></li>\r\n" + 
        		"  <li><a href=\"CatalogueServlet\">Catalogue</a></li>\r\n" + 
        		"   <li><a onclick=\"document.getElementById('id09').style.display='block'\">Online Help</a></li>"+
        		"  <li><a href=\"login.jsp\">Log Out</a></li>\r\n" + 
        		"</ul>\r\n" + 
        		"<br>\r\n" + 
        		"<br> \r\n" + 
        		"<br>\r\n" + 
        		"<br>\r\n" + 
        		"<div class='container'>"+
        		"<form action=\"BookAppointmentServlet\" method=\"post\" role=\"search\">\r\n" + 
        		"  <h2>Search for a Doctor: </h2>\r\n" + 
        		"  <input id=\"search\" name=\"search\" type=\"text\" placeholder=\"Enter Postal Code or Department\" style=\"width: 500px;\" required />\r\n" + 
        		"  <button type=\"submit\" formmethod=\"post\" style=\"width: 80px;\">Search</button>    \r\n" + 
        		"</form>\r\n" + 
        		"\r\n" +
        		"<br>\r\n" + 
        		"<br>\r\n" + 
        		"<div class='catalogue' style=\"display: block;\">\r\n" + 
        		"<table style='width: 50%;'class=\"table\">\r\n" + 
        		"<thead class=\"thead\">\r\n" + 
        		"<tr class=\"headerrow\">\r\n" + 
        		"<th style= 'text-align: center'>Username</th>\r\n" + 
        		"<th style= 'text-align: center'>Name</th>\r\n" + 
        		"<th style= 'text-align: center'>Surname</th>\r\n" + 
        		"<th style= 'text-align: center'>Phone Number</th>\r\n" + 
        		"<th style= 'text-align: center'>Address</th>\r\n" + 
        		"<th style= 'text-align: center'>Postal Code</th>\r\n" + 
        		"<th style= 'text-align: center'>Email</th>\r\n" + 
        		"<th style= 'text-align: center'>Department</th>\r\n" +
        		"</tr>\r\n" +
        		"</thead>\r\n" + 
        		"<form action='/SubmitAppointmentServlet' method='get'>\r\n"+
        		"<tbody class=\"cataloguebody\"></tbody>\r\n");   
        //Online Help
        out.println("<div id=\"id09\" class=\"modal\">\r\n" + 
        		"  <form class=\"modal-content animate\" action=\"<%=request.getContextPath()%>/LoginServlet\" method=\"post\">\r\n" + 
        		"    <div class=\"imgcontainer1\">\r\n" + 
        		"      <span onclick=\"document.getElementById('id09').style.display='none'\" class=\"close\" title=\"Close Modal\">&times;</span>\r\n" + 
        		"    </div>\r\n" + 
        		"    <div class=\"container\">\r\n" + 
        		"      <label for=\"info\"><b>This is the Book Appointment Page.</b></label>\r\n" + 
        		"      <br>\r\n" + 
        		"      <label for=\"info\"><b>Here you can search for a doctor by postal code or department.</b></label>\r\n" + 
        		"      <br>\r\n" + 
        		"      <label for=\"info\"><b>Click Search after entering something on the search bar. The results are going to be shown below the search bar in a table.</b></label>\r\n" + 
        		"      <br>\r\n" + 
        		"      <label for=\"info\"><b>Book Appointment: click on this button to book an appointment. Type time, date and description and confirm your appointment by clicking on submit.</b></label>     \r\n" + 
        		"      <br>\r\n" + 
        		"      <label for=\"info\"><b>Menu Bar: click on the action you want to do.</b></label>  \r\n" + 
        		"    </div>\r\n" + 
        		"    <div class=\"container1\" style=\"background-color:#f1f1f1\">\r\n" + 
        		"      <button type=\"button\" onclick=\"document.getElementById('id09').style.display='none'\" class=\"cancelbtn\">Cancel</button>\r\n" + 
        		"    </div>\r\n" + 
        		"  </form>\r\n" + 
        		"</div>");
        out.println("</body>\r\n" + 
        		"</html>\r\n");
	}

	//Method doPost
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		System.out.println("In Post method");
		String search_input = request.getParameter("search");
		System.out.println(search_input);
		UserBean userBean = new UserBean();
		Pattern pattern1 = Pattern.compile("[0-9][0-9][0-9][0-9][0-9]", Pattern.CASE_INSENSITIVE);
		Matcher matcher1 =pattern1.matcher(search_input);
		boolean matchFound1 = matcher1.find();
		Pattern pattern2 = Pattern.compile("[a-z]", Pattern.CASE_INSENSITIVE);
		Matcher matcher2 = pattern2.matcher(search_input);
		boolean matchFound2 = matcher2.find();
		PrintWriter out = response.getWriter();
		int count_of_records=0;		
		BookAppointmentDao bookappointmentdao = new BookAppointmentDao();
		//Search by postal code
		if(matchFound1) {		
			ArrayList<ArrayList<String>> bookAppointmentList = new ArrayList<>();	
			userBean.setPostalcode(search_input);
			System.out.println("In if set postal code");								
			try {
				count_of_records = bookappointmentdao.searchByPostalCode(userBean, bookAppointmentList);
				for (int i=0; i < count_of_records; i++) {
					for (int j=0; j<7; j++) {
						System.out.println(bookAppointmentList.get(i).get(j));
						out.println("<td style= 'text-align: center; color: white'>"+bookAppointmentList.get(i).get(j)+"</td>\r\n");						
						if(j==4) {
							out.println("<td style= 'text-align: center; color: white'>"+userBean.getPostalcode()+"</td>\r\n");
						}
						
					}															
					out.println("</tr>\r\n");
					out.println("<tr>\r\n");											
					
				}
					
				out.println("<br>");
				out.println("<br>");		
				out.println("<br>");	
				out.println("<tr></tr>");	
				out.println("<form action='submitappointment.jsp'>");
				out.println("<td style= 'text-align: center; color: white'> <button type='submit'>Book Appointment</button> </td>\r\n");
				out.println("</form>");
				out.println("</div>");
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("After try catch code");
		//Search by department
		}else if(matchFound2){
			ArrayList<ArrayList<String>> bookAppointmentList = new ArrayList<>();	
			userBean.setDepartment(search_input);
			System.out.println("In if set department");
			try {
				count_of_records = bookappointmentdao.searchByDepartment(userBean, bookAppointmentList);
				for (int i=0; i < count_of_records; i++) {
					for (int j=0; j<8; j++) {
						System.out.println(bookAppointmentList.get(i).get(j));
						out.println("<td style= 'text-align: center; color: white'>"+bookAppointmentList.get(i).get(j)+"</td>\r\n");												
						
					}					
					out.println("</tr>\r\n");
					out.println("<tr>\r\n");
				}
				out.println("<br>");
				out.println("<br>");		
				out.println("<br>");	
				out.println("<tr></tr>");
				out.println("<form action='submitappointment.jsp'>");
				out.println("<td style= 'text-align: center; color: white'> <button type='submit'>Book Appointment</button> </td>\r\n");
				out.println("</form>");
				out.println("</div>");
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
		
		}else {
			//no results
			System.out.println("No search results.");	
			out.println("<h3>No search results. Please try again with a different input.<h3>");
		}		

	}
}
