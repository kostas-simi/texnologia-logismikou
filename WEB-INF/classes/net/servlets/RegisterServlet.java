package net.servlets;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.classes.UserBean;
import net.queries.database.RegisterDao;


@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RegisterDao registerDao;
	
	public void init() {
        registerDao = new RegisterDao();
    }
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    //Method doPost
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {				
		 String username = request.getParameter("username");
		 String name = request.getParameter("name");
		 String surname = request.getParameter("surname");
		 String property = request.getParameter("property");
		 String department = request.getParameter("department");
		 String ssn = request.getParameter("ssn");
		 String phonenumber = request.getParameter("phonenumber");
		 String address = request.getParameter("address");
		 String postalcode = request.getParameter("postalcode");
		 String email = request.getParameter("email");
		 String psw = request.getParameter("psw");
		
	     UserBean registerBean = new UserBean();
	     registerBean.setUsername(username);
	     registerBean.setName(name);
	     registerBean.setSurname(surname);
	     registerBean.setProperty(property);
	     registerBean.setDepartment(department);
	     registerBean.setPhoneNumber(phonenumber);
	     registerBean.setAddress(address);
	     registerBean.setPostalcode(postalcode);
	     registerBean.setEmail(email);
	     registerBean.setPassword(psw);
	     int ssnParsed;
	     try {
	    	 ssnParsed = Integer.parseInt(ssn);
	     }catch(NumberFormatException e) {
	    	 ssnParsed = 0;
	     }
	     registerBean.setSsn(ssnParsed);
	     
	     registerBean.setPassword(psw);
	     	     	     
	     try {
			registerDao.registerUser(registerBean);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
	    HttpSession session = request.getSession();
		 try {
		 //Patient
	     if(registerBean.getProperty().equals("Patient")){
	    	 session = request.getSession();
	         session.setAttribute("property",property);
	    	 System.out.println("patient menu loading");
	    	 request.setAttribute("username", username);
	    	 session.setAttribute("username",username);
	    	 response.sendRedirect("patient.jsp");
	     }else if (registerBean.getProperty().equals("Doctor")) {
	     //Doctor
	    	 session = request.getSession();
	    	 System.out.println("doctor menu loading");
	    	 request.setAttribute("username", username);
	    	 session.setAttribute("username",username);
	    	 request.setAttribute("name", name);
	    	 session.setAttribute("name",name);
	    	 response.sendRedirect("doctor.jsp");}
	     else System.out.println("Something Wrong with user property");
         
		 } catch(Exception e){
			 e.printStackTrace();
				
		 }
    }
}



