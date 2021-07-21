package net.queries.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import net.classes.UserBean;

public class BookAppointmentDao {
	//Function for searching by postal code
	public int searchByPostalCode(UserBean userBean, ArrayList<ArrayList<String>> bookAppointmentList) throws ClassNotFoundException{
		Class.forName("com.mysql.jdbc.Driver");
		int count_of_records = 0;
		//Connection with the Database
		try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_medical_appointments?useLegacyDatetimeCode=false&serverTimezone=UTC&allowPublicKeyRetrieval=true&useSSL=false", "root", "Zzr36531");
			    //SQL Queries
				PreparedStatement preparedStatement = connection.prepareStatement("select department from doctors where username in (select username from users where property='doctor' and postalcode=?)");
				PreparedStatement preparedStatement2 = connection.prepareStatement("select username, name, surname, phonenumber, address, email from users where property='doctor' and postalcode=?");
				PreparedStatement preparedStatement3 = connection.prepareStatement("select count(username) from users where property='doctor' and postalcode=?");
				){
			preparedStatement.setString(1, userBean.getPostalcode());
			System.out.println(preparedStatement);
			preparedStatement2.setString(1, userBean.getPostalcode());
			System.out.println(preparedStatement2);
			preparedStatement3.setString(1, userBean.getPostalcode());
			System.out.println(preparedStatement3);	
			
			ResultSet rs1,rs2,rs3;			
			rs1 = preparedStatement.executeQuery();
			rs2 = preparedStatement2.executeQuery();
			rs3 = preparedStatement3.executeQuery();			
			//Getting the number of records 
			
			while(rs3.next()) {
				count_of_records = rs3.getInt(1);
				System.out.println("Number of records is: "+count_of_records);
			}
			//Initializing array list rows
			for(int i=0; i<count_of_records; i++) {
				bookAppointmentList.add(new ArrayList<String>());
			}
							
			int j=0;
			while(rs2.next()) {
				bookAppointmentList.get(j).add(rs2.getString(1));
				bookAppointmentList.get(j).add(rs2.getString(2));
				bookAppointmentList.get(j).add(rs2.getString(3));
				bookAppointmentList.get(j).add(rs2.getString(4));
				bookAppointmentList.get(j).add(rs2.getString(5));
				bookAppointmentList.get(j).add(rs2.getString(6));	
				rs1.next();
				bookAppointmentList.get(j).add(rs1.getString(1));	
				j++;
			}
			System.out.println("J is: "+j);
			
		}catch(SQLException e) {
			printSQLException(e);
		}
		return count_of_records;
	}
	//Function for searching by department
	public int searchByDepartment(UserBean userBean, ArrayList<ArrayList<String>> bookAppointmentList) throws ClassNotFoundException{
		Class.forName("com.mysql.jdbc.Driver");
		int count_of_records = 0;
		//Connection with the Database
		try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_medical_appointments?useLegacyDatetimeCode=false&serverTimezone=UTC&allowPublicKeyRetrieval=true&useSSL=false", "root", "Zzr36531");
			    //SQL Queries
				PreparedStatement preparedStatement = connection.prepareStatement("select username, name, surname, phonenumber, address, postalcode, email from users where property='doctor' and username in(select username from doctors where department=?)");
				PreparedStatement preparedStatement2 = connection.prepareStatement("select count(username) from doctors where department=?");
				){
			preparedStatement.setString(1, userBean.getDepartment());
			System.out.println(preparedStatement);			
			preparedStatement2.setString(1, userBean.getDepartment());
			ResultSet rs1,rs2;			
			rs1 = preparedStatement.executeQuery();				
			//Getting the number of records 
			rs2 = preparedStatement2.executeQuery();
			while(rs2.next()) {
				count_of_records = rs2.getInt(1);
				System.out.println("Number of records is: "+count_of_records);
			}
			//Initializing array list rows
			for(int i=0; i<count_of_records; i++) {
				bookAppointmentList.add(new ArrayList<String>());
			}
									
			int j=0;
			while(rs1.next()) {
				bookAppointmentList.get(j).add(rs1.getString(1));
				bookAppointmentList.get(j).add(rs1.getString(2));
				bookAppointmentList.get(j).add(rs1.getString(3));
				bookAppointmentList.get(j).add(rs1.getString(4));
				bookAppointmentList.get(j).add(rs1.getString(5));
				bookAppointmentList.get(j).add(rs1.getString(6));
				bookAppointmentList.get(j).add(rs1.getString(7));
				bookAppointmentList.get(j).add(userBean.getDepartment());
				j++;
			}
			
		}catch(SQLException e) {
			printSQLException(e);
		}
		return count_of_records;
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
