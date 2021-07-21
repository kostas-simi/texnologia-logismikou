package net.queries.database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import net.classes.LoginBean;

public class LoginDao {
	//Function for validation: checks correlation between user name and password
	public boolean validate(LoginBean loginBean) throws ClassNotFoundException {
        boolean status = false;
        Class.forName("com.mysql.jdbc.Driver");
        //Connection with the Database
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_medical_appointments?useLegacyDatetimeCode=false&serverTimezone=UTC&allowPublicKeyRetrieval=true&useSSL=false", "root", "Zzr36531");
            //SQL Query
            PreparedStatement preparedStatement = connection.prepareStatement("select * from users where username = ? and password = ? ")
            ) {
            preparedStatement.setString(1, loginBean.getUsername());
            preparedStatement.setString(2, loginBean.getPassword());
            System.out.println(preparedStatement);
            //Execute Query
            ResultSet rs = preparedStatement.executeQuery();
            status = rs.next();

        } catch (SQLException e) {
            printSQLException(e);
        }
        return status;
    }
	//Function for saving temporarily the login information to the user log table
	public int insert_userlog(LoginBean loginBean) throws ClassNotFoundException {

		Class.forName("com.mysql.jdbc.Driver");
		 //Connection with the Database
		 try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_medical_appointments?useLegacyDatetimeCode=false&serverTimezone=UTC&allowPublicKeyRetrieval=true&useSSL=false", "root", "Zzr36531");
				//SQL Query
				//First Prepare Statement: Clearing the user log table
				PreparedStatement preparedStatement1 = connection.prepareStatement("delete from userlog");	
	            PreparedStatement preparedStatement = connection.prepareStatement("insert into userlog (username, property) values (?,?)");						
	            ){
			    //Executing the Prepared Statements
			    preparedStatement1.executeUpdate();	
			    System.out.println(preparedStatement1);
			 	preparedStatement.setString(1, loginBean.getUsername());
			 	preparedStatement.setString(2, loginBean.getProperty());	
	            System.out.println(preparedStatement);
	            preparedStatement.executeUpdate();	  

		 }catch(SQLException e){
			 printSQLException(e);
		 }
		return 0;
	}
    //Function for reading the login information from the user log table
	public int select_userlog(LoginBean loginBean) throws ClassNotFoundException {

		Class.forName("com.mysql.jdbc.Driver");
		 //Connection with the Database
		 try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_medical_appointments?useLegacyDatetimeCode=false&serverTimezone=UTC&allowPublicKeyRetrieval=true&useSSL=false", "root", "Zzr36531");
				//SQL Query
				PreparedStatement preparedStatement = connection.prepareStatement("select * from userlog");						
	            ){			    
	            ResultSet rs1;
				rs1 = preparedStatement.executeQuery();	
				while(rs1.next()) {
				loginBean.setUsername(rs1.getString(1));
				loginBean.setProperty(rs1.getString(2));						
				}
	            
		 }catch(SQLException e){
			 printSQLException(e);
		 }
		return 0;
	}
	//Function for checking the property of the user: Doctor or Patient
    public String propertyCheck(LoginBean loginBean,String username, String password) throws ClassNotFoundException {
        String property = "";

        Class.forName("com.mysql.jdbc.Driver");
        //Connection with the Database
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_medical_appointments?useLegacyDatetimeCode=false&serverTimezone=UTC&allowPublicKeyRetrieval=true&useSSL=false", "root", "Zzr36531");
        	//SQL Query
        	PreparedStatement preparedStatement = connection.prepareStatement("select property from users where username = ? and password = ? ")) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            rs.next();
            property = rs.getString(1);
            System.out.println("Property of "+username+": "+property);
            rs.close();
            return property;

        } catch (SQLException e) {
            printSQLException(e);
        }
        return property;
    }
    
    //Function for handling Exceptions
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

