package management_System;
import java.io.Serializable;
import java.util.Scanner;
import java.util.ArrayList;

public class Student extends User implements Student_Action, Serializable{
	
	/* overloaded constructor, one that allows for the usage of a birthday and the other with all fields
	 * other than the birthday */
	public Student(String username, String password, String first_name, String last_name) {
		super(username, password, first_name, last_name); 
	}
	
	public Student(String username, String password, String first_name, String last_name, String birthday) {
		super(username, password, first_name, last_name, birthday); 
	}
	
	/* allows User to view the list of all courses that exist and their corresponding information */
	public void view_courses(ArrayList<Course>courses) {
		for (Course a: courses) {
			System.out.println("Course Name: " + a.name + "; Course ID: " + a.id + "; Maximum Number of Students: " + a.max + "; Current Number of Students: " + a.num + "; Course Instructor: " + a.instructor + "; Course Section Number:" + a.sec_num + "; Course Location: " + a.location);
		}
		   
	}

	/* allows User to view the list of all course information for courses that are not currently full */
	@Override
	public void view_not_full(ArrayList<Course>courses) {
		for (Course a: courses) {
			if (a.num < a.max) {
				System.out.println("Course Name: " + a.name + "; Course ID: " + a.id + "; Maximum Number of Students: " + a.max + "; Current Number of Students: " + a.num + "; Course Instructor: " + a.instructor + "; Course Section Number:" + a.sec_num + "; Course Location: " + a.location);
			}
		}
	}

	/* allows User to register for a course as long as they're registered as a student, they
	 * aren't already registered for the course, the course exists, and the course isn't full. */
	@Override
	public void register1(ArrayList<Course>courses, Scanner scanner) {
		System.out.println("Name of the course you want to register for: ");
		String name_temp = scanner.nextLine();
		System.out.println("Section number of the course you want to register for: ");
		String sec_temp = scanner.nextLine();
		System.out.println ("Enter your full name: ");
		String student_temp = scanner.nextLine();
		Student student_to_register = null;
		for (Student a: User_Manager.all_students) {
			String veri = a.first_name + " " + a.last_name;
			if (veri.equals(student_temp)) {
				student_to_register = a;
				break;
			}
		}
		if (student_to_register == null) {
			System.out.println("Cannot find a record of you with this name.");
			return;
		}
		
		boolean coursefound = false;
		for (Course a:courses) {
			if (a.name.equals(name_temp) && a.sec_num.equals(sec_temp)) {
				coursefound = true;
				if (a.students.contains(student_temp)) {
					System.out.println("You are already registered on this course.");
				}
				else {
					if (a.num >= a.max) {
						System.out.println("Course is full!");
					}
					else {
						a.students_objects.add(student_to_register); 
						a.students.add(student_temp);
						a.num++;
						System.out.println("You are registered!");
					}
				}
				break;
				
			}
		}
		if (!coursefound) {
			System.out.println("Course not found.");
		}
		
	}
	
	/* allows User to withdraw from a course as long as they're registered as a student, they
	 * are already registered for the course, and the course exists. */
	@Override
	public void withdraw(ArrayList<Course>courses, Scanner scanner) {
		System.out.println("Name of the course you want to withdraw from:");
		String name_temp = scanner.nextLine();
		System.out.println("Section number of the course you want to withdraw from:");
		String sec_temp = scanner.nextLine();
		System.out.println ("Enter your full name:");
		String student_temp = scanner.nextLine();
		Student student_to_withdraw = null;
		
		for (Student a: User_Manager.all_students) {
			String veri = a.first_name + " " + a.last_name;
			if (veri.equals(student_temp)) {
				student_to_withdraw = a;
				break;
			}
		}
		
		if (student_to_withdraw == null) {
			System.out.println("Cannot find a record of you with this name.");
			return;
		}
		
		boolean coursefound = false;
		for (Course a:courses) {
			if (a.name.equals(name_temp) && a.sec_num.equals(sec_temp)) {
				coursefound = true;
				if (a.students.contains(student_temp)) {
					a.students_objects.remove(student_to_withdraw);
					a.students.remove(student_temp);
					a.num--;
					System.out.println("You've withdrawn!");
				}
				else {
					System.out.println("You are not in the course.");
				}
				break;
			}
		}
		
		if (!coursefound) {
			System.out.println("Course not found!");
		}
	}
	
	/* allows the User to view all courses that they are registered for. */
	public void view_all_course(ArrayList<Course>courses, Student b) {
		String veri = b.first_name + " " + b.last_name;
		for (Course a:courses) {
			if (a.students.contains(veri)) {
				System.out.println(a.name + " " + a.id);
			}  
		}
	}
	
	/* opens a menu of 6 options labeled 1-6 that are the following: "view all courses", "view all courses
	 * that are full", "register for a course", "withdraw from a course", "view all registered courses", 
	 * and "exit" from the menu. if you select, it goes back to the A-C menu that runs at program start */
	@Override
	public void menu(Scanner scanner) {
		boolean running = true;
		while (running) {
			System.out.println("Course Management:\n1.View all Courses\n2.View all Courses that are not full\n3.Register on a Course\n4.Withdraw from a Course\n5.View all Courses I'm registered in\n6.Exit");
			String answer = scanner.nextLine();
			switch (answer) {
			   case "1": view_courses(ReadCSV.courses); break;
			   case "2": view_not_full(ReadCSV.courses); break;
			   case "3": register1(ReadCSV.courses, scanner); break;
			   case "4": withdraw(ReadCSV.courses, scanner); break;
			   case "5": view_all_course(ReadCSV.courses, this); break;
			   case "6": System.out.println("Logging Out"); running = false; break;
			   default: System.out.println("Invalid Choice");
			}
		}
	}
}
