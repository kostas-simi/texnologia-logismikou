package net.servlets;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.classes.UserBean;
import net.queries.database.EditUserDao;


@WebServlet("/EditUserServlet")
public class EditUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EditUserDao editDao;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	
    public void init() {
        editDao = new EditUserDao();
    }
    //Method doPost
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.print("In doPost");	
		String action =request.getParameter("action");
		if(action.equals("save")){
			String username = request.getParameter("username");
			String password2 = request.getParameter("password");		
			String phonenumber2 = request.getParameter("phonenumber");
			String address2 = request.getParameter("address");
			String postalcode2 = request.getParameter("postalcode");
			String email2= request.getParameter("email");		
			
			UserBean saveBean = new UserBean();
			saveBean.setUsername(username);		
			System.out.println("\nUsername is: "+username);
			
			try {
				
				HttpSession session1 = request.getSession();
				if(!password2.equals(null)) {
					saveBean.setPassword(password2);
					System.out.println("\nPassword is: "+password2);
				}else {
					System.out.println("Mhn to afineis keno!");
				}
				if(!phonenumber2.equals(null)) {
					saveBean.setPhoneNumber(phonenumber2);
					System.out.println("\nPhonenumber is: "+phonenumber2);
				}else {
					System.out.println("Mhn to afineis keno!");
				}
				if(!address2.equals(null)) {
					saveBean.setAddress(address2);
				}else {
					System.out.println("Mhn to afineis keno!");
				}
				if(!postalcode2.equals(null)) {
					saveBean.setPostalcode(postalcode2);
				}else {
					System.out.println("Mhn to afineis keno!");
				}
				if(!email2.equals(null)) {
					saveBean.setEmail(email2);
				}else {
					System.out.println("Mhn to afineis keno!");
				}
				editDao.SaveUser(saveBean);
				editDao.EditUser(saveBean);
				session1.setAttribute("password",saveBean.getPassword());
				session1.setAttribute("username", saveBean.getUsername());
				session1.setAttribute("name", saveBean.getName());
				session1.setAttribute("password", saveBean.getPassword());
				session1.setAttribute("property", saveBean.getProperty());
				session1.setAttribute("ssn", saveBean.getSsn());
				session1.setAttribute("phonenumber", saveBean.getPhoneNumber());
				session1.setAttribute("address", saveBean.getAddress());
				session1.setAttribute("postalcode", saveBean.getPostalcode());
				session1.setAttribute("email", saveBean.getEmail());
							
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			response.sendRedirect("doctor.jsp");
			
		}else if(action.equals("delete")) {
			String username = request.getParameter("username");
			String property = request.getParameter("property");
			UserBean deleteBean = new UserBean();
			deleteBean.setUsername(username);
			deleteBean.setProperty(property);
			System.out.println("\nUsername(delete) is: "+username);
			System.out.println("\nProperty(delete)is:"+property); 
			try {
				System.out.println("Inside try catch");
				editDao.DeleteUser(deleteBean);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			response.sendRedirect("login.jsp");
		}
		
		
        
	}	

}

