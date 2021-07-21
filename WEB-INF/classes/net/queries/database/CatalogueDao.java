package net.queries.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import net.classes.LoginBean;


public class CatalogueDao {
	//Function for printing all the appointments a user has
	public int printCatalogue(LoginBean loginBean, ArrayList<String> catalogueList) throws ClassNotFoundException{
		
		Class.forName("com.mysql.jdbc.Driver");
		//Connection with the Database
		try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_medical_appointments?useLegacyDatetimeCode=false&serverTimezone=UTC&allowPublicKeyRetrieval=true&useSSL=false", "root", "Zzr36531");
			    //SQL Queries
				PreparedStatement preparedStatement = connection.prepareStatement("select * from appointment where username_d = ?");
				PreparedStatement preparedStatement2 = connection.prepareStatement("select * from appointment where username_p = ?");
				){
			preparedStatement.setString(1, loginBean.getUsername());
			System.out.println(preparedStatement);
			preparedStatement2.setString(1, loginBean.getUsername());
			System.out.println(preparedStatement2);	
			
			ResultSet rs1;
			if (loginBean.getProperty().equals("Doctor")) {
				rs1 = preparedStatement.executeQuery();	
				System.out.println(rs1);
				System.out.println("Statement Doctor Appointments Completed.");
			} else if (loginBean.getProperty().equals("Patient")) {				
				rs1 = preparedStatement2.executeQuery();
				System.out.println(rs1);				
				System.out.println("Statements Patient Appointments Completed.");
			}else {
				rs1=null;
			}
			while(rs1.next()) {
				catalogueList.add(rs1.getString(2));
	    		catalogueList.add(rs1.getString(3));
	    		catalogueList.add(rs1.getString(4));
	    		catalogueList.add(rs1.getString(5));
	    		catalogueList.add(rs1.getString(6));
	    		catalogueList.add(rs1.getString(7));
			}
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
	//Function for changing the status of an appointment if it is cancelled
	public int changeStatus(LoginBean loginBean) throws ClassNotFoundException{
		Class.forName("com.mysql.jdbc.Driver");
		try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_medical_appointments?useLegacyDatetimeCode=false&serverTimezone=UTC&allowPublicKeyRetrieval=true&useSSL=false", "root", "Zzr36531");
				PreparedStatement preparedStatement = connection.prepareStatement("update appointment set status_appointment='cancelled' where username_p=? and status_appointment='active'");
				PreparedStatement preparedStatement2 = connection.prepareStatement("update appointment set status_appointment='cancelled' where username_d=? and status_appointment='active'");		
			){
			if(loginBean.getProperty().equals("Patient")) {
				preparedStatement.setString(1, loginBean.getUsername());
				System.out.println(preparedStatement);
				preparedStatement.executeUpdate();	
			}else if(loginBean.getProperty().contentEquals("Doctor")) {
				preparedStatement2.setString(1, loginBean.getUsername());
				System.out.println(preparedStatement2);
				preparedStatement2.executeUpdate();	
			}
		}catch(SQLException e) {
			printSQLException(e);
		}						
			
		return 0;
	}
}


