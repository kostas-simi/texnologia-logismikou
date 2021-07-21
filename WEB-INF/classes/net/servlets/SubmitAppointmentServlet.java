package net.servlets;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.classes.AppointmentBean;
import net.classes.LoginBean;
import net.queries.database.LoginDao;
import net.queries.database.SubmitAppointmentDao;

@WebServlet("/SubmitAppointmentServlet")
public class SubmitAppointmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public SubmitAppointmentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    //Method doGet
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());			
	}
	//Method doPost
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		AppointmentBean catalogueBean = new AppointmentBean();
		SubmitAppointmentDao submitAppointmentDao = new SubmitAppointmentDao();
		LoginBean loginBean = new LoginBean();
		LoginDao loginDao = new LoginDao();
		
		System.out.println("In method post of submit appointment servlet!");
		String username_d = request.getParameter("username_d");
		String time_appointment = request.getParameter("time_appointment");
		String date_appointment = request.getParameter("date_appointment");
		String description = request.getParameter("description");
		try {
			loginDao.select_userlog(loginBean);
			catalogueBean.setUsername_d(username_d);
			catalogueBean.setTime_appointment(time_appointment);
			catalogueBean.setDate_appointment(date_appointment);
			catalogueBean.setDescription(description);
			submitAppointmentDao.submit(catalogueBean, loginBean);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		response.sendRedirect("CatalogueServlet");
		
	}

}
