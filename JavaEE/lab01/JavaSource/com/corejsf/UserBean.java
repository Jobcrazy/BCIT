package com.corejsf;

import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;

@Named("user")
@SessionScoped
public class UserBean implements Serializable {
	private String firstName;
	private String lastName;
	private String password;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String newValue) {
		firstName = newValue;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String newValue) {
		lastName = newValue;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String newValue) {
		password = newValue;
	}
}