package net.queries.database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import net.classes.UserBean;

public class EditUserDao {

//Function for editing the profile information of a user
public int EditUser(UserBean editBean) throws ClassNotFoundException{
		
		Class.forName("com.mysql.jdbc.Driver");
		//Connection with the Database
		try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_medical_appointments?useLegacyDatetimeCode=false&serverTimezone=UTC&allowPublicKeyRetrieval=true&useSSL=false", "root", "Zzr36531");
				//SQL Queries
				PreparedStatement preparedStatement = connection.prepareStatement("select * from users where username = ?");
				PreparedStatement preparedStatement2 = connection.prepareStatement("select department from doctors where username = ?");
				){
			preparedStatement.setString(1, editBean.getUsername());
			System.out.println(preparedStatement);
			preparedStatement2.setString(1, editBean.getUsername());
			System.out.println(preparedStatement2);							
			
			ResultSet rs1,rs2;
			rs1 = preparedStatement.executeQuery();	
			while(rs1.next()) {
			editBean.setUsername(rs1.getString(1));
			editBean.setName(rs1.getString(2));		
			editBean.setSurname(rs1.getString(3));
			editBean.setPassword(rs1.getString(4));
			editBean.setProperty(rs1.getString(5));
			editBean.setSsn(rs1.getInt(6));
			editBean.setPhoneNumber(rs1.getString(7));
			editBean.setAddress(rs1.getString(8));
			editBean.setPostalcode(rs1.getString(9));
			editBean.setEmail(rs1.getString(10));
			}
			System.out.println("Statements Completed");
			
			//Doctor
			rs2 = preparedStatement2.executeQuery();
			while(rs2.next()) {
				editBean.setDepartment(rs2.getString(1));
			}
		}catch(SQLException e) {
			printSQLException(e);
		}
		return 0;
	}

//Function for saving changes 
public int SaveUser(UserBean saveBean) throws ClassNotFoundException{
	Class.forName("com.mysql.jdbc.Driver");
	//Connection with the Database
	try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_medical_appointments?useLegacyDatetimeCode=false&serverTimezone=UTC&allowPublicKeyRetrieval=true&useSSL=false", "root", "Zzr36531");
		    //SQL Queries
			PreparedStatement preparedStatement = connection.prepareStatement("update users set password = ? where username = ?");
			PreparedStatement preparedStatement2 = connection.prepareStatement("update users set phonenumber = ? where username = ?");
			PreparedStatement preparedStatement3 = connection.prepareStatement("update users set address = ? where username = ?");
			PreparedStatement preparedStatement4 = connection.prepareStatement("update users set postalcode = ? where username = ?");
			PreparedStatement preparedStatement5 = connection.prepareStatement("update users set email = ? where username = ?");
			){
		//#1
		preparedStatement.setString(1, saveBean.getPassword());
		preparedStatement.setString(2, saveBean.getUsername());		
		System.out.println(preparedStatement);
		preparedStatement.executeUpdate();
		//#2
		preparedStatement2.setString(1, saveBean.getPhoneNumber());
		preparedStatement2.setString(2, saveBean.getUsername());		
		System.out.println(preparedStatement2);
		preparedStatement2.executeUpdate();
		//#3
		preparedStatement3.setString(1, saveBean.getAddress());
		preparedStatement3.setString(2, saveBean.getUsername());		
		System.out.println(preparedStatement3);
		preparedStatement3.executeUpdate();
		//#4
		preparedStatement4.setString(1, saveBean.getPostalcode());
		preparedStatement4.setString(2, saveBean.getUsername());		
		System.out.println(preparedStatement4);
		preparedStatement4.executeUpdate();
		//#5
		preparedStatement5.setString(1, saveBean.getEmail());
		preparedStatement5.setString(2, saveBean.getUsername());		
		System.out.println(preparedStatement5);
		preparedStatement5.executeUpdate();
		
		
	}catch(SQLException e) {
		printSQLException(e);
	}
	
	return 0;
}
//Function for deleting a user 
public int DeleteUser(UserBean deleteBean) throws ClassNotFoundException{
	Class.forName("com.mysql.jdbc.Driver");
	//Connection with the Database
	try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_medical_appointments?useLegacyDatetimeCode=false&serverTimezone=UTC&allowPublicKeyRetrieval=true&useSSL=false", "root", "Zzr36531");
		    //SQL Queries
			PreparedStatement preparedStatement = connection.prepareStatement("delete from users where username = ?");
			PreparedStatement preparedStatement2 = connection.prepareStatement("delete from doctors where username = ?");
			PreparedStatement preparedStatement3 = connection.prepareStatement("delete from appointment where username_d = ?");
			PreparedStatement preparedStatement4 = connection.prepareStatement("delete from appointment where username_p = ?");
			){		
		
		if (deleteBean.getProperty().equals("Doctor")) {
			//#3
			preparedStatement3.setString(1, deleteBean.getUsername());
			System.out.println(preparedStatement3);
			preparedStatement3.executeUpdate();
			//#2
			preparedStatement2.setString(1, deleteBean.getUsername());
			System.out.println(preparedStatement2);
			preparedStatement2.executeUpdate();
			
		} else if (deleteBean.getProperty().equals("Patient"))
		{
			preparedStatement4.setString(1, deleteBean.getUsername());
			System.out.println(preparedStatement4);
			preparedStatement4.executeUpdate();
		}
		
		//#1
		preparedStatement.setString(1, deleteBean.getUsername());
		System.out.println(preparedStatement);
		preparedStatement.executeUpdate();
		
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
