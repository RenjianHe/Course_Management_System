package management_System;
import java.util.Scanner;
import java.util.ArrayList;

public interface Admin_Actions {
	
	/* interface that provides the method stubs for all operations that the Admin class implements */
	abstract void add_course(ArrayList<Course>courses,Scanner scanner);
	abstract void del_course(ArrayList<Course>courses,Scanner scanner);
	abstract void edit(ArrayList<Course>courses,Scanner scanner);
	abstract void display_info(ArrayList<Course>courses,Scanner scanner);
	abstract void register(ArrayList<Course>courses,Scanner scanner);
	
	abstract void view_full(ArrayList<Course>courses);
	abstract void write_file(ArrayList<Course>courses);
	abstract void roster(ArrayList<Course>courses, Scanner scanner);
	abstract void view_student(ArrayList<Course>courses, Scanner scanner);

}
