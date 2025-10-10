package management_System;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.Scanner;
import java.util.ArrayList;

public class Program {
	
	protected static User in_session = null;
	public static void program() {
		
		/* loading the courses from either the serialized file; if it doesn't exist, load from 
		 * the "MyUniversityCourses.csv file. if that file isn't found (hopefully not the case)
		 * then print to the CLI "No CSV file found. */
		
		try {
			FileInputStream fis = new FileInputStream("course.ser");
			ObjectInputStream ois = new ObjectInputStream(fis);
			ReadCSV.courses = (ArrayList<Course>)ois.readObject();
			ois.close();
			fis.close();
		}catch(Exception e) {
			System.out.println("No saved courses found, loading from CSV.");
			try {
				ReadCSV.courses = ReadCSV.read();
			}catch(Exception e3) {
				System.out.println("No CSV file found.");
				e3.printStackTrace();
				ReadCSV.courses = new ArrayList<Course>();
			}
		}
		
		/* attempt to load students from the serialized file "students.ser", if it doesn't exist then
		 * print to CLI "No saved students." and create an empty ArrayList of Students. */
		try {
			FileInputStream fis1 = new FileInputStream("students.ser");
			ObjectInputStream ois1 = new ObjectInputStream(fis1);
			User_Manager.all_students = (ArrayList<Student>)ois1.readObject();
			ois1.close();
			fis1.close();
		}catch (Exception e1) {
			System.out.println("No saved students.");
			User_Manager.all_students = new ArrayList<Student>();
		}
		
		/* attempt to load the admin from "admin.ser", if it doesn't exist then print to the CLI 
		 * "No saved admin." and initialize the Admin object in the user manager class as null. */
		try {
			FileInputStream fis2 = new FileInputStream("admin.ser");
			ObjectInputStream ois2 = new ObjectInputStream(fis2);
			User_Manager.admin = (Admin)ois2.readObject();
			ois2.close();
			fis2.close();
		}catch (Exception e2) {
			System.out.println("No saved admin");
			User_Manager.admin = null;
		}
		
		/* while(true) loop that operates as the menu, use CLI to first type a letter from A-C in order
		 * to perform the following operations: login, create account, or close the system. */
		Scanner c = new Scanner (System.in);
		while (true) {
	
			System.out.println("Do you want: A. Login B. Create New Account, C. Close the System");
			String answer = c.nextLine();
			switch (answer) {
			case "A":
				in_session = User_Manager.login(c);
				if (in_session != null) {
					in_session.menu(c);
					in_session = null;
				}else {
					System.out.println("Login failed!");
				}
				break;
			case "B":
				System.out.println("Are you a: A. Student B. Admin");
				String answer1 = c.nextLine();
				switch (answer1) {
				case "A":
					User_Manager.add_student();
					break;
				case "B":
					User_Manager.add_admin();
					break;
				default:
					System.out.println("Invalid input.");
				}
				break;
			case "C":
				System.out.println("Exit the System");
				try {
					//save ArrayList of Courses as a serialized file
					FileOutputStream fos = new FileOutputStream("course.ser");
					ObjectOutputStream oos = new ObjectOutputStream(fos);
					oos.writeObject(ReadCSV.courses);
					oos.close();
					fos.close();
					
					//save ArrayList of Students as a serialized file
					FileOutputStream fos1 = new FileOutputStream("students.ser");
					ObjectOutputStream oos1 = new ObjectOutputStream(fos1);
					oos1.writeObject(User_Manager.all_students);
					oos1.close();
					fos1.close();
					
					//save Admin object as a serialized file
					FileOutputStream fos2 = new FileOutputStream("admin.ser");
					ObjectOutputStream oos2 = new ObjectOutputStream(fos2);
					oos2.writeObject(User_Manager.admin);
					oos2.close();
					fos2.close();
					
				}
				catch (IOException e) {
					e.printStackTrace();	
				}
				return;
			default:
				System.out.println("Invalid Choice");
				
			}
		   }
		 }
}


