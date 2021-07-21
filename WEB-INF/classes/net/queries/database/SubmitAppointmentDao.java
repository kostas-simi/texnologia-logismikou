package net.queries.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import net.classes.AppointmentBean;
import net.classes.LoginBean;

public class SubmitAppointmentDao {
	//Function for submitting a new appointment at the appointment table
	public int submit(AppointmentBean catalogueBean, LoginBean loginBean) throws ClassNotFoundException{
		Class.forName("com.mysql.jdbc.Driver");
		//Connection with the Database
		try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_medical_appointments?useLegacyDatetimeCode=false&serverTimezone=UTC&allowPublicKeyRetrieval=true&useSSL=false", "root", "Zzr36531");
			    //SQL Queries
				PreparedStatement preparedStatement = connection.prepareStatement("insert into appointment(username_p, username_d, time_appointment, date_appointment, description, status_appointment) values (?,?,?,?,?,'active')");				
				){
			preparedStatement.setString(1, loginBean.getUsername());
			preparedStatement.setString(2, catalogueBean.getUsername_d());
			preparedStatement.setString(3, catalogueBean.getTime_appointment());
			preparedStatement.setString(4, catalogueBean.getDate_appointment());
			preparedStatement.setString(5, catalogueBean.getDescription());
			System.out.println(preparedStatement.executeUpdate());
			System.out.println(preparedStatement);
			
		}catch(SQLException e) {
			printSQLException(e);
		}
		return 0;
	}
	
	//Function for handling exceptions
		private void printSQLException(SQLException ex) {
	        for (Throwable e: ex) {
	            if (e instanceof SQLException) {
	                e.printStackTrace(System.err);
	                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
	                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
	                System.err.println("Message: " + e.getMessage());
	                Throwable t = ex.getCause();
	                while (t != null) {
	                    System.out.println("Cause: " + t);
	                    t = t.getCause();
	                }
	            }
	      }
		}

}
