package management_System;

import java.util.Scanner;
import java.util.ArrayList;

public class User_Manager {
	
	protected static ArrayList<Student>all_students;
	protected static User admin = new Admin("Admin", "Admin001", "default", "default");
	
	public User_Manager (ArrayList<Student>all_students, User admin) {
		User_Manager.all_students = all_students;
		User_Manager.admin = admin;
	}
	
	/* addStudent takes String input via the CLI for all student information, then instantiates
	 * a new Student object with that information and adds it to the ArrayList of Students. 
	 * addStudent also takes advantage of the overloaded User constructor to input a birthday
	 * in case the User wishes to enter it. */
	
	public static void add_student() {
		Scanner c = new Scanner(System.in);
		System.out.println("Enter your username:");
		String username1 = c.nextLine();
		System.out.println("Enter your password:");
		String password1 = c.nextLine();
		System.out.println("Enter your first name:");
		String first_name1 = c.nextLine();
		System.out.println("Enter your last name:");
		String last_name2 = c.nextLine();
		System.out.println("Would you like to enter a birthday (Y/N)?");
		String birthdayChoice = c.nextLine();
		
		if(birthdayChoice.equals("Y")) {
			System.out.println("Enter your birthday in the format MM-DD-YYYY:");
			String birthday = c.nextLine();
			Student student = new Student(username1, password1, first_name1, last_name2, birthday);
			all_students.add(student);
		}
		
		else {
			Student student = new Student(username1, password1, first_name1, last_name2);
			all_students.add(student);
		}
	}
	
	/* addAdmin takes String input via the CLI for the admin's first and last name, then instantiates a
	 * new Admin object with that information as well as the username "Admin" and password "Admin001". */
	
	public static void add_admin() {
		Scanner c = new Scanner(System.in);
		System.out.println("Enter your new first name:");
		String first_name = c.nextLine();
		System.out.println("Enter your new last name:");
		String last_name = c.nextLine();
		admin = new Admin("Admin", "Admin001", first_name, last_name);
	}
	
	/* login takes a username and password input from the user and checks against every username-password 
	 * combination for each Student and returns the Student that matches (making it "in_session"). login
	 * works easier for Admin, as it checks against the preset Admin username-password combo to login,
	 * returning the single Admin object as being "in_session". if neither match, the method returns null,
	 * and prints "We can't find this user." to the CLI. */
	
	public static User login(Scanner scanner) {
		System.out.println("Username:");
		String username = scanner.nextLine();
		System.out.println("Password:");
		String password = scanner.nextLine();
		User in_session = null;
		if (username.equals("Admin") && password.equals("Admin001")) {
			in_session = admin;
			return in_session;
		}else {
			for (Student a: all_students) {
				if (a.username.equals(username) && a.password.equals(password)) {
					in_session = a;
					return in_session;	
				}
			}
			System.out.println("we can't find this user");
			return null;		
		}
	}
	
}


