package net.classes;
import java.io.Serializable;

public class LoginBean implements Serializable{

	private static final long serialVersionUID = 1L;
	private String username;
    private String password;
    private String property;
    
    //Getters and Setters
    public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
