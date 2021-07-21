package net.servlets;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.classes.LoginBean;
import net.classes.UserBean;
import net.queries.database.EditUserDao;
import net.queries.database.LoginDao;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private LoginDao loginDao;
    private EditUserDao editDao;

    public void init() {
        loginDao = new LoginDao();
        editDao = new EditUserDao();
    }
    //Method doPost
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String property;
        LoginBean loginBean = new LoginBean();
        UserBean editBean = new UserBean();
        loginBean.setUsername(username);
        loginBean.setPassword(password);
        editBean.setUsername(loginBean.getUsername());

        try {
            if (loginDao.validate(loginBean)) {
                HttpSession session = request.getSession();                 
                 property = loginDao.propertyCheck(loginBean,username,password);
                 //Patient
                 if(property.equals("Patient")){
                	 try {
                		loginBean.setProperty(property);
                		loginDao.insert_userlog(loginBean);
              			editDao.EditUser(editBean);
              			HttpSession session1 = request.getSession();             			
              			session1.setAttribute("username", editBean.getUsername());
              			session1.setAttribute("name", editBean.getName());
              			session1.setAttribute("surname", editBean.getSurname());
              			session1.setAttribute("password", editBean.getPassword());
              			session1.setAttribute("property", editBean.getProperty());              			
              			session1.setAttribute("ssn", editBean.getSsn());
              			session1.setAttribute("phonenumber", editBean.getPhoneNumber());
              			session1.setAttribute("address", editBean.getAddress());
              			session1.setAttribute("postalcode", editBean.getPostalcode());
              			session1.setAttribute("email", editBean.getEmail());
              			System.out.println("patient menu loading");
              			response.sendRedirect("patient.jsp");
              			
              		} catch (ClassNotFoundException e1) {
              			// TODO Auto-generated catch block
              			e1.printStackTrace();
              			}                 		                	                 
                 //Doctor 
                 }else if (property.equals("Doctor")) {                	                 	 
             		try {
             			loginBean.setProperty(property);
             			loginDao.insert_userlog(loginBean);
             			editDao.EditUser(editBean);
             			HttpSession session1 = request.getSession();             			
             			session1.setAttribute("username", editBean.getUsername());
             			session1.setAttribute("name", editBean.getName());
             			session1.setAttribute("surname", editBean.getSurname());
             			session1.setAttribute("password", editBean.getPassword());
             			session1.setAttribute("property", editBean.getProperty());
             			session1.setAttribute("department", editBean.getDepartment());
             			session1.setAttribute("ssn", editBean.getSsn());
             			session1.setAttribute("phonenumber", editBean.getPhoneNumber());
             			session1.setAttribute("address", editBean.getAddress());
             			session1.setAttribute("postalcode", editBean.getPostalcode());
             			session1.setAttribute("email", editBean.getEmail());
             			System.out.println("doctor menu loading");
             			response.sendRedirect("doctor.jsp");
             			
             		} catch (ClassNotFoundException e1) {
             			// TODO Auto-generated catch block
             			e1.printStackTrace();
             			}
             		}
                 else System.out.println("Something Wrong with user property");
            } else {
                HttpSession session = request.getSession();
                session.setAttribute("user", username);
                response.sendRedirect("login.jsp");
             
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}