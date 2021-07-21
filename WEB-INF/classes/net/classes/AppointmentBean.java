package net.classes;
import java.io.Serializable;

public class AppointmentBean implements Serializable{
	private static final long serialVersionUID = 1L;
	private String username_p;
	private String username_d;
	private String time_appointment;
	private String date_appointment;
	private String description;
	private String status_appointment;
	
	//Getters and Setters
	public String getUsername_p() {
		return username_p;
	}
	public void setUsername_p(String username_p) {
		this.username_p = username_p;
	}
	public String getUsername_d() {
		return username_d;
	}
	public void setUsername_d(String username_d) {
		this.username_d = username_d;
	}
	public String getTime_appointment() {
		return time_appointment;
	}
	public void setTime_appointment(String time_appointment) {
		this.time_appointment = time_appointment;
	}
	public String getDate_appointment() {
		return date_appointment;
	}
	public void setDate_appointment(String date_appointment) {
		this.date_appointment = date_appointment;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getStatus_appointment() {
		return status_appointment;
	}
	public void setStatus_appointment(String status_appointment) {
		this.status_appointment = status_appointment;
	}
}
