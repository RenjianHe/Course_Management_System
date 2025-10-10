package management_System;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

/* this class allows for the creation of an Object that contains the ArrayList of courses and 
 * contains a single method that reads the CSV (full definition in comment below). */

public class ReadCSV {
	public static ArrayList<Course>courses = new ArrayList<>();
	public static ArrayList<Course> read() throws FileNotFoundException {
		File file = new File ("MyUniversityCourses.csv");
		Scanner scanner = new Scanner(file);
		
		if (scanner.hasNextLine()) {
			scanner.nextLine();
		}
		
		// reads the list of all courses from the CSV file using a scanner and adds each one to the ArrayList
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			String [] values = line.split(",");
			int max2 = Integer.parseInt(values[2]);
			int current = Integer.parseInt(values[3]);
			ArrayList<Student>students1 = new ArrayList<>();
			ArrayList<String>students = new ArrayList<>();
			Course course1 = new Course(values[0], values[1], max2, current, students1, students, values[5], values[6], values[7]);
			courses.add(course1);
		}
		
		scanner.close();
		return courses;
	}
}