package management_System;
import java.io.Serializable;
import java.util.Scanner;

/* abstract superclass that has two subclasses: Admin and Student that implement certain things */
public abstract class User implements Serializable {
	protected String username;
	protected String password;
	protected String first_name;
	protected String last_name;
	protected String birthday;
	
	protected User() {}
	
	//overloaded constructors, one with birthday and one with everything besides birthday
	public User (String username, String password, String first_name, String last_name) {
		this.username = username;
		this.password = password;
		this.first_name = first_name;
		this.last_name = last_name;
	}
	
	public User (String username, String password, String first_name, String last_name, String birthday) {
		this.username = username;
		this.password = password;
		this.first_name = first_name;
		this.last_name = last_name;
		this.birthday = birthday;
	}
	
	//menu that depends on the type of User (Admin vs Student) that the object takes on
	public abstract void menu(Scanner scanner);

}
	
	

