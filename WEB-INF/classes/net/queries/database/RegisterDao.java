package net.queries.database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import net.classes.UserBean;

public class RegisterDao {
	
//Function for registering a new user
public int registerUser(UserBean registerBean) throws ClassNotFoundException{
	
		Class.forName("com.mysql.jdbc.Driver");
		//Connection with the Database
		 try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_medical_appointments?useLegacyDatetimeCode=false&serverTimezone=UTC&allowPublicKeyRetrieval=true&useSSL=false", "root", "Zzr36531");
	           //SQL Queries
			    PreparedStatement preparedStatement = connection.prepareStatement("insert into users (username, name, surname, password, property, ssn, phonenumber, address, postalcode, email) values (?,?,?,?,?,?,?,?,?,?)");
				PreparedStatement preparedStatement2 = connection.prepareStatement ("insert into doctors(username, department) values (?,?)");
	            ){
			 	//TABLE USERS
			 	preparedStatement.setString(1, registerBean.getUsername());
	            preparedStatement.setString(2, registerBean.getName());
	            preparedStatement.setString(3, registerBean.getSurname());
	            preparedStatement.setString(4, registerBean.getPassword());	
	            preparedStatement.setString(5, registerBean.getProperty());
	            preparedStatement.setFloat(6, registerBean.getSsn());
	            preparedStatement.setString(7, registerBean.getPhoneNumber());
	            preparedStatement.setString(8, registerBean.getAddress());
	            preparedStatement.setString(9, registerBean.getPostalcode());
	            preparedStatement.setString(10, registerBean.getEmail());
	           
	            System.out.println(preparedStatement);
	            //Executing the Prepare Statement
	            preparedStatement.executeUpdate();
	            
	            if (registerBean.getProperty().equals("Patient")) {
	            	System.out.println("Patient. no need to insert to the doctors table");
	            } else if (registerBean.getProperty().equals("Doctor")) {
	            	//TABLE DOCTORS
	            	preparedStatement2.setString(1, registerBean.getUsername());
	 	            preparedStatement2.setString(2, registerBean.getDepartment());
	            	System.out.println(preparedStatement2);
	            	//Executing the Prepare Statement
	            	preparedStatement2.executeUpdate();
	            }
	            	
		 }catch(SQLException e){
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
